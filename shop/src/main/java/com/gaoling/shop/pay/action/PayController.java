package com.gaoling.shop.pay.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaoling.shop.common.XMLUtil;
import com.gaoling.shop.pay.service.PayService;

@RestController
@RequestMapping("/pay")
public class PayController {

	@Autowired
	private PayService payService;
	
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
	
}
