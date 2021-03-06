package com.gaoling.webshop.pay.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.webshop.common.AppConstant;
import com.gaoling.webshop.common.DataUtil;
import com.gaoling.webshop.common.ThreadCache;
import com.gaoling.webshop.order.pojo.Order;
import com.gaoling.webshop.order.service.OrderService;
import com.gaoling.webshop.pay.dao.UserTradeLogDao;
import com.gaoling.webshop.pay.pojo.PayRefundSummary;
import com.gaoling.webshop.pay.pojo.UserTradeLog;
import com.gaoling.webshop.system.pojo.Result;
import com.gaoling.webshop.system.service.CommonService;
import com.gaoling.webshop.user.pojo.User;

import net.sf.json.JSONObject;

@Service
public class UserTradeLogService extends CommonService{

	@Autowired
	private UserTradeLogDao userTradeLogDao;
	@Autowired
	private OrderService orderService;
	
	//保存流水记录
	public void addUserTradeLog(UserTradeLog userTradeLog){
		userTradeLogDao.addUserTradeLog(userTradeLog);
	}
	
	//查询用户流水记录
	public Result queryUserTradeLogs(){
		//加载用户
		User user=(User)ThreadCache.getData(AppConstant.STORE_USER_PARAM_NAME);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		List<Map<String,Object>> logs=userTradeLogDao.queryUserTradeLogs(user.getId());
		JSONObject config=JSONObject.fromObject(getString("user_trade_log_config"));
		DecimalFormat df=new DecimalFormat("#.##");
		for(Map<String,Object> log:logs){
			int tradeType=Integer.parseInt(log.get("tradeType").toString());
			String goodsName=!DataUtil.isEmpty(log.get("goodsName"))?log.get("goodsName").toString():"";
			int tradeId=Integer.parseInt(log.get("tradeId").toString());
			int payWay=Integer.parseInt(log.get("payWay").toString());
			float amount=Float.parseFloat(log.get("amount").toString());
			//拼装消费名称
			if(tradeType==PayRefundSummary.TRADE_TYPE_ENUM.INVITE.getState()){
				log.put("item", config.get("invite_title"));
				log.put("desc",config.get("trade_success_info"));
			}else if(tradeType==PayRefundSummary.TRADE_TYPE_ENUM.GOODSPAY.getState()){
				List<Order> orders=orderService.queryOrders(DataUtil.mapOf("allOrders",true,"mainOrderId",tradeId));
				log.put("item", goodsName);
				if(orders.size()>1){
					log.put("item", String.format(config.getString("goods_title"),goodsName,orders.size()));
				}
				log.put("desc",config.get("pay_success_info"));
			}else if(tradeType==PayRefundSummary.TRADE_TYPE_ENUM.GOODSREFUND.getState()){
				log.put("item", goodsName);
				log.put("desc",config.get("refund_success_info"));
			}else if(tradeType==PayRefundSummary.TRADE_TYPE_ENUM.REWARD.getState()){
				log.put("item", payWay==AppConstant.COIN_PAY_WAY?config.get("coin_reward_title"):config.get("point_reward_title"));
				log.put("desc",config.get("trade_success_info"));
			}
			//组装金额
			if(payWay==AppConstant.COIN_PAY_WAY){
				log.put("amount", amount>=0?"+"+df.format(amount)+config.get("coin_name"):df.format(amount)+config.getString("coin_name"));
			}else if(payWay==AppConstant.POINT_PAY_WAY){
				log.put("amount", amount>=0?"+"+df.format(amount)+config.get("point_name"):df.format(amount)+config.getString("point_name"));
			}else{
				log.put("amount", amount>=0?"+"+df.format(amount)+config.get("cash_name"):df.format(amount)+config.getString("cash_name"));
			}
		}
		return putResult(logs);
	}
	
}
