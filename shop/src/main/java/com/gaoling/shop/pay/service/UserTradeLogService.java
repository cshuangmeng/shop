package com.gaoling.shop.pay.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.order.pojo.Order;
import com.gaoling.shop.order.service.OrderService;
import com.gaoling.shop.pay.dao.UserTradeLogDao;
import com.gaoling.shop.pay.pojo.PayRefundSummary;
import com.gaoling.shop.pay.pojo.UserTradeLog;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;
import com.gaoling.shop.user.pojo.User;
import com.gaoling.shop.user.service.UserService;

import net.sf.json.JSONObject;

@Service
public class UserTradeLogService extends CommonService{

	@Autowired
	private UserTradeLogDao userTradeLogDao;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	
	//保存流水记录
	public void addUserTradeLog(UserTradeLog userTradeLog){
		userTradeLogDao.addUserTradeLog(userTradeLog);
	}
	
	//查询用户流水记录
	public Result queryUserTradeLogs(String uuid){
		//检查参数
		if(StringUtils.isEmpty(uuid)){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//加载用户
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		List<Map<String,Object>> logs=userTradeLogDao.queryUserTradeLogs(user.getId());
		JSONObject config=JSONObject.fromObject(getString("user_trade_log_config"));
		for(Map<String,Object> log:logs){
			int tradeType=Integer.parseInt(log.get("tradeType").toString());
			String goodsName=!DataUtil.isEmpty(log.get("goodsName"))?log.get("goodsName").toString():"";
			int tradeId=Integer.parseInt(log.get("tradeId").toString());
			//拼装消费名称
			if(tradeType==PayRefundSummary.TRADE_TYPE_ENUM.INVITE.getState()){
				log.put("item", config.get("invite_title"));
				log.put("desc",config.get("pay_success_info"));
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
			}
		}
		return putResult(DataUtil.mapOf("logs",logs));
	}
	
}
