package com.gaoling.shop.pay.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.pay.service.WeiXinService;
import com.gaoling.shop.system.pojo.Result;

@RestController
@RequestMapping("/wx")
@CrossOrigin(methods = RequestMethod.POST, origins = AppConstant.TRUST_CROSS_ORIGINS)
public class WeiXinController {

	@Autowired
	private WeiXinService weiXinService;

	// 参数签名
	@RequestMapping("/sign")
	public Result weiXinSign(@RequestParam("url") String url, HttpServletRequest request) {
		Result result=null;
		try {
			result=weiXinService.getRequestSign(url);
		} catch (Exception e) {
			result=weiXinService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	// 获取openId
	@ResponseBody
	@RequestMapping("/oa")
	public Result getOAAccessToken(@RequestParam String code, HttpServletRequest request) {
		Result result=null;
		try {
			result=weiXinService.getOAAccessToken(code);
		} catch (Exception e) {
			result=weiXinService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	// 通过微信发送的服务器合法性验证
	@ResponseBody
	@RequestMapping("/event")
	public String getUserTextOrEventData(HttpServletRequest request) {
		try {
			return weiXinService.getUserTextOrEventData(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 通过微信发送的服务器合法性验证
	@ResponseBody
	@RequestMapping("/share")
	public Result getShareConfig(HttpServletRequest request) {
		try {
			return weiXinService.getShareConfig(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
