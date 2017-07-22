package com.kcx.support.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kcx.support.common.AppConstant;
import com.kcx.support.common.DataUtil;
import com.kcx.support.common.DateUtil;
import com.kcx.support.common.ThreadCache;
import com.kcx.support.dao.UserPurchaseMapper;
import com.kcx.support.pojo.PayParam;
import com.kcx.support.pojo.User;
import com.kcx.support.pojo.UserPurchase;

@Service
public class UserPurchaseService {

	@Autowired
	private UserPurchaseMapper userPurchaseMapper;
	@Autowired
	private PayService payService;
	
	//生成订单
	public Map<String,Object> buildPurchase(int levelId,String ip)throws Exception{
		User user=(User)ThreadCache.getData(AppConstant.STORE_USER_PARAM_NAME);
		//保存订单
		UserPurchase userPurchase=new UserPurchase();
		userPurchase.setUserId(user.getId());
		userPurchase.setLevelId(levelId);
		userPurchase.setMoney(AppConstant.LEVEL_MONEY);
		userPurchase.setPlatform(String.valueOf(AppConstant.WEIXIN_PAY_WAY));
		userPurchase.setCreateTime(DateUtil.nowDate());
		userPurchase.setPurchaseNo(DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(3));
		addUserPurchase(userPurchase);
		//生成预支付信息
		PayParam pay=new PayParam();
		pay.setAmount(Math.round(userPurchase.getMoney()));
		pay.setBody(AppConstant.USERMP_PAY_MSG_BODY);
		pay.setIp(ip);
		pay.setAttach(String.valueOf(userPurchase.getId()));
		pay.setNonceStr(DataUtil.createLetters(32));
		pay.setOpenId(user.getOpenId());
		pay.setOperator(AppConstant.USERMP_MCH_ID);
		pay.setPayWay(AppConstant.WEIXIN_PAY_WAY);
		pay.setStartTime(DateUtil.getCurrentTime("yyyyMMddHHmmss"));
		pay.setTimestamp(String.valueOf(new Date().getTime()/1000));
		pay.setTradeNo(userPurchase.getPurchaseNo());
		pay.setTradeType(AppConstant.WEIXIN_TRADE_TYPE_JSAPI);
		Map<String,Object> payInfo=payService.operateUserPayRequest(pay);
		payInfo.put("orderId", userPurchase.getId());
		return payInfo;
	}
	
	//处理支付成功
	public boolean purchasePaySuccess(int orderId,String outTradeNo,int amount){
		UserPurchase up=getUserPurchase(orderId);
		//判断订单状态是否是待支付
		if(null!=up&&up.getState()==AppConstant.YES_OR_NO_ENUM.NO.getState()){
			up.setPayTime(DateUtil.nowDate());
			up.setPlatformBillNo(outTradeNo);
			up.setState(AppConstant.YES_OR_NO_ENUM.YES.getState());
			updateUserPurchase(up);
		}
		return true;
	}
	
	//查询订单状态
	public UserPurchase queryPurchase(int orderId){
		User user=(User)ThreadCache.getData(AppConstant.STORE_USER_PARAM_NAME);
		UserPurchase up=getUserPurchase(orderId);
		//判断订单状态是否是待支付
		if(null!=up&&up.getUserId()==user.getId()){
			return up;
		}
		return null;
	}
	
	//查询用户订单
	public List<UserPurchase> queryUserPurchases(Map<Object, Object> param) {
		return userPurchaseMapper.queryUserPurchases(param);
	}
	
	//查询订单
	public UserPurchase getUserPurchase(int id){
		List<UserPurchase> purchases=queryUserPurchases(DataUtil.mapOf("id",id));
		return purchases.size()>0?purchases.get(0):null;
	}

	//新增订单
	public void addUserPurchase(UserPurchase userPurchase) {
		userPurchaseMapper.addUserPurchase(userPurchase);
	}

	//更新订单
	public void updateUserPurchase(UserPurchase userPurchase) {
		userPurchaseMapper.updateUserPurchase(userPurchase);
	}
	
}
