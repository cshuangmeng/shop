package com.kcx.support.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kcx.support.common.AppConstant;
import com.kcx.support.common.DataUtil;
import com.kcx.support.common.XMLUtil;
import com.kcx.support.pojo.UserPurchase;
import com.kcx.support.service.PayService;
import com.kcx.support.service.UserPurchaseService;

@RestController
@RequestMapping("/pay")
@CrossOrigin(methods = RequestMethod.POST, origins = AppConstant.TRUST_CROSS_ORIGINS)
public class PayController {

	@Autowired
	private PayService payService;
	@Autowired
	private UserPurchaseService userPurchaseService;
	
	//支付通知
	@RequestMapping(value="/notify",produces="application/xml;charset=UTF-8")
	public String operateUserPayNotify(HttpServletRequest request){
		try {
			return XMLUtil.createXMLString(payService.operateUserPayNotify(request), "xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//预支付
	@RequestMapping("/prepare")
	public Map<String,Object> preparePay(@RequestParam int levelId,HttpServletRequest request){
		try {
			return userPurchaseService.buildPurchase(levelId, DataUtil.getIpAddr(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//支付状态查询
	@RequestMapping("/query")
	public UserPurchase queryOrder(@RequestParam int orderId){
		try {
			return userPurchaseService.queryPurchase(orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
