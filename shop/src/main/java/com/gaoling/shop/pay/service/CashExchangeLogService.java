package com.gaoling.shop.pay.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.common.DateUtil;
import com.gaoling.shop.common.RedisUtil;
import com.gaoling.shop.common.SMSUtil;
import com.gaoling.shop.common.ThreadCache;
import com.gaoling.shop.pay.dao.CashExchangeLogDao;
import com.gaoling.shop.pay.pojo.CashExchangeLog;
import com.gaoling.shop.pay.pojo.PayRefundSummary;
import com.gaoling.shop.pay.pojo.UserTradeLog;
import com.gaoling.shop.system.pojo.PayParam;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;
import com.gaoling.shop.tribe.pojo.Tribe;
import com.gaoling.shop.tribe.pojo.TribeMember;
import com.gaoling.shop.tribe.service.TribeMemberService;
import com.gaoling.shop.tribe.service.TribeService;
import com.gaoling.shop.user.pojo.User;
import com.gaoling.shop.user.service.UserService;

import net.sf.json.JSONObject;

@Service
public class CashExchangeLogService extends CommonService{

	@Autowired
	private CashExchangeLogDao cashExchangeLogDao;
	@Autowired
	private UserService userService;
	@Autowired
	private TribeService tribeService;
	@Autowired
	private UserTradeLogService userTradeLogService;
	@Autowired
	private TribeMemberService tribeMemberService;
	@Autowired
	private PayService payService;
	private Logger log=LoggerFactory.getLogger(getClass());
	
	//用户提现
	@Transactional
	public Result userOperateCashExchange(String uuid,int coin){
		//锁定用户账户
		if(StringUtils.isEmpty(uuid)||coin<=0){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		user=userService.getUser(user.getId(), true);
		//检查用户是否具有提现特权
		if(user.getCashExchangeEnable()!=AppConstant.YES_OR_NO_ENUM.YES.getState()){
			log.info("用户"+user.getUuid()+"尚未获得提现权限,结束流程");
			return putResult(AppConstant.HAVE_NO_RIGHT);
		}
		//检查部落币是否充足
		Tribe tribe=tribeService.getTribeByUserId(user.getId());
		if(null==tribe){
			log.info("还未拥有部落,结束流程");
			return putResult(AppConstant.TRIBE_NOT_EXISTS);
		}
		if(coin>user.getCoin()){
			log.info("用户部落币不足,结束流程");
			return putResult(AppConstant.COIN_BALANCE_INADEQUATE);
		}
		JSONObject config=getCurLevelRatio(tribe.getLevel());
		if(null==config){
			log.info("该部落等级未配置提现比率,结束流程");
			return putResult(AppConstant.EXCHANGE_RATIO_INCORRECT);
		}
		float cash=new BigDecimal(coin).multiply(new BigDecimal(config.getString("ratio"))).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		if(cash<Float.parseFloat(getString("cash_exchange_at_least"))){
			log.info("提现金额不满足至少金额,结束流程");
			return putResult(AppConstant.EXCHANGE_CASH_INCORRECT);
		}
		//微信默认每个用户每天最多可付款10次
		List<CashExchangeLog> logs=selectCashExchangeLog(DataUtil.mapOf("userId",user.getId()
				,"createTime",DateFormatUtils.format(DateUtil.nowDate(), "yyyy-MM-dd")));
		if(logs.size()>=getInteger("cash_exchange_frequency")){
			log.info("已经超过每日最多提现次数,结束流程");
			return putResult(AppConstant.OUT_OF_FREQUENCY);
		}
		//扣除部落币
		user.setCoin(user.getCoin()-coin);
		userService.updateUser(user);
		//保存提现申请信息
		CashExchangeLog log=new CashExchangeLog();
		log.setUserId(user.getId());
		log.setTribeId(tribe.getId());
		log.setLevel(tribe.getLevel());
		log.setTradeNo(DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(3));
		log.setCashExchangeRatio(Float.valueOf(config.getString("ratio")));
		log.setCoin(coin);
		log.setCash(cash);
		log.setCoinBalance(user.getCoin());
		log.setCreateTime(DateUtil.nowDate());
		insertCashExchangeLog(log);
		//保存交易流水信息
		UserTradeLog trade=new UserTradeLog();
		trade.setUserId(user.getId());
		trade.setCash(cash);
		trade.setCoin(-coin);
		trade.setTradeType(PayRefundSummary.TRADE_TYPE_ENUM.CASHEXCHANGE.getState());
		trade.setTradeNo(DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(3));
		trade.setTradeId(log.getId());
		trade.setCoinBalance(log.getCoinBalance());
		trade.setPointBalance(user.getPoint());
		trade.setCreateTime(DateUtil.nowDate());
		trade.setRemark("");
		userTradeLogService.addUserTradeLog(trade);
		return putResult();
	}
	
	//查询提现记录
	public Result queryCashExchangeHistory(String uuid){
		//检查参数
		if(StringUtils.isEmpty(uuid)){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//加载用户
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		List<CashExchangeLog> history=selectCashExchangeLog(DataUtil.mapOf("userId",user.getId(),"orderBy","create_time desc"));
		List<Map<Object,Object>> list=history.stream().map(i->DataUtil.mapOf("state",i.getState()
				,"amount",i.getCash(),"date",DateFormatUtils.format(i.getCreateTime(), "yyyy-M-d"))).collect(Collectors.toList());
		return putResult(list);
	}
	
	//获取部落币详情
	public Result getMyCoinInfo(String uuid){
		//检查参数
		if(StringUtils.isEmpty(uuid)){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//加载用户
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		Map<String,Object> result=DataUtil.mapOf("coin",user.getCoin());
		//部落当前等级
		Tribe tribe=tribeService.getTribeByUserId(user.getId());
		int level=null!=tribe?tribe.getLevel():-1;
		result.put("level", level);
		//升级到下个等级所需的成员数
		int curMemberSum=0;
		if(null!=tribe){
			List<TribeMember> members=tribeMemberService.queryTribeMembers(DataUtil.mapOf("tribeId",tribe.getId()
					,"state",TribeMember.STATE_TYPE_ENUM.JOINED.getState()));
			curMemberSum=members.size();
		}
		JSONObject config=getCurLevelRatio((level>-1?level:0)+1);
		if(null!=config){
			int needMemberSum=config.getInt("member")-curMemberSum+1;
			result.put("needMemberSum",needMemberSum);
			result.put("nextLevel", (level>-1?level:0)+1);
			result.put("nextRatio", config.get("ratio"));
		}
		//当前等级可以提现的现金金额
		config=getCurLevelRatio(level>-1?level:0);
		if(null!=config){
			float cash=new BigDecimal(user.getCoin()).multiply(new BigDecimal(config.getString("ratio"))).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			result.put("ratio", config.get("ratio"));
			result.put("cash", cash);
		}
		return putResult(result);
	}
	
	//定时操作提现申请
	@Transactional
	public Result executeCashExchangeOntime(){
		//获取待处理提现申请
		List<CashExchangeLog> allLogs=selectCashExchangeLog(DataUtil.mapOf("state"
				,CashExchangeLog.STATE_TYPE_ENUM.DAICHULI.getState(),"orderBy","create_time"));
		//为了满足微信的提现要求标准,每次每个用户只处理一次提现
		List<CashExchangeLog> cLogs=allLogs.stream().filter(c->{
			Optional<CashExchangeLog> ip=allLogs.stream().filter(i->i.getUserId()==c.getUserId()
					&&i.getId()!=c.getId()&&c.getId()>i.getId()).findFirst();
			return !ip.isPresent();
		}).collect(Collectors.toList());
		PayParam param=new PayParam();
		User user=null;
		int success=0,failure=0,sendNotice=0;
		for(CashExchangeLog cLog:cLogs){
			log.info("开始处理id="+cLog.getId()+",cash="+cLog.getCash()+"提现申请");
			user=userService.getUser(cLog.getUserId(), false);
			param.setOutTradeNo(cLog.getTradeNo());
			param.setBody(getString("cash_exchange_pay_title"));
			param.setNonceStr(DataUtil.createNums(6));
			param.setIp(ThreadCache.getData(AppConstant.CLIENT_IP).toString());
			param.setAmount(cLog.getCash());
			param.setOpenId(user.getOpenId());
			param.setPayWay(AppConstant.WEIXIN_PAY_WAY);
			JSONObject json=payService.operateUserTransferRequest(param);
			if(null!=json){
				if(json.getString("return_code").equalsIgnoreCase("SUCCESS")&&json.getString("result_code").equalsIgnoreCase("SUCCESS")){
					//更新操作状态
					cLog.setState(CashExchangeLog.STATE_TYPE_ENUM.YICHULI.getState());
					cLog.setOperateTime(DateUtil.nowDate());
					updateCashExchangeLogById(cLog);
					success++;
				}else if(!json.getString("err_code").equalsIgnoreCase("SYSTEMERROR")){//系统繁忙异常不能重置交易单号
					cLog.setTradeNo(DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(3));
					updateCashExchangeLogById(cLog);
					//账户余额不足时发送短信提醒
					if(json.getString("err_code").equalsIgnoreCase("NOTENOUGH")){
						sendNotice=1;
					}
					failure++;
				}
			}
		}
		//发送余额不足短信提醒
		JSONObject config=JSONObject.fromObject(getString("exchange_notice_config"));
		if(sendNotice>0){
			//检查是否满足发送条件
			String lastSendTimestamp=RedisUtil.get(config.getString("last_timestamp_key"));
			boolean enable=true;
			if(StringUtils.isNotEmpty(lastSendTimestamp)){
				enable=DateUtils.addHours(new Date(Long.valueOf(lastSendTimestamp))
						, config.getInt("effect_hour_duration")).compareTo(DateUtil.nowDate())<0;
			}
			if(enable&&!DataUtil.isEmpty(config.get("allow_range"))){
				String date=DateFormatUtils.format(DateUtil.nowDate(), config.getString("allow_range_pattern"));
				String beginTime=config.getString("allow_range").split("\\-")[0];
				String endTime=config.getString("allow_range").split("\\-")[1];
				enable=date.compareTo(beginTime)>=0&&date.compareTo(endTime)<=0;
			}
			if(enable){
				if(!DataUtil.isEmpty(config.get("mobile"))){
					for(String mobile:config.getString("mobile").split(",")){
						SMSUtil.sendAccountNotEnoughNotice(mobile);
					}
					RedisUtil.set(config.getString("last_timestamp_key"), String.valueOf(DateUtil.nowDate().getTime()));
				}
			}
		}else if(cLogs.size()>0&&cLogs.size()==success){//账户余额不足异常恢复
			RedisUtil.delete(config.getString("last_timestamp_key"));
		}
		log.info("本次提现申请处理完成,总数:"+cLogs.size()+",处理成功数:"+success+"，处理失败数:"+failure);
		return putResult();
	}
	
	//获取部落所处等级对应的成员数和提现比率
	public JSONObject getCurLevelRatio(int level){
		JSONObject json=JSONObject.fromObject(getString("tribe_level_cash_mapper"));
		return DataUtil.isJSONObject(json.get(String.valueOf(level)))?json.getJSONObject(String.valueOf(level)):null;
	}
	
	//重新计算部落等级
	@SuppressWarnings("unchecked")
	public int resetTribeLevel(int tribeId){
		Tribe tribe=tribeService.getTribe(tribeId);
		int level=0;
		if(null!=tribe){
			List<TribeMember> members=tribeMemberService.queryTribeMembers(DataUtil.mapOf("tribeId",tribe.getId()
					,"state",TribeMember.STATE_TYPE_ENUM.JOINED.getState()));
			JSONObject json=JSONObject.fromObject(getString("tribe_level_cash_mapper"));
			for (Iterator<String> iterator = json.keys(); iterator.hasNext();) {
				String key = iterator.next();
				if(json.getJSONObject(key).getInt("member")<members.size()&&Integer.valueOf(key)>level){
					level=Integer.valueOf(key);
				}
			}
		}
		return level;
	}
	
	//查询提现记录
	public List<CashExchangeLog> selectCashExchangeLog(Map<String, Object> param) {
		return cashExchangeLogDao.selectCashExchangeLog(param);
	}
	
	//保存提现记录
	@Transactional
	public int insertCashExchangeLog(CashExchangeLog cashExchangeLog) {
		return cashExchangeLogDao.insertCashExchangeLog(cashExchangeLog);
	}
	
	//更新提现记录
	@Transactional
	public int updateCashExchangeLogById(CashExchangeLog cashExchangeLog) {
		return cashExchangeLogDao.updateCashExchangeLog(cashExchangeLog,DataUtil.mapOf("id",cashExchangeLog.getId()));
	}
	
}
