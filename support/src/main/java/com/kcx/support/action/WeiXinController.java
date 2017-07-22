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
import com.kcx.support.service.WeiXinService;

@RestController
@RequestMapping("/wx")
@CrossOrigin(methods = RequestMethod.POST, origins = AppConstant.TRUST_CROSS_ORIGINS)
public class WeiXinController {

	@Autowired
	private WeiXinService weiXinService;

	// 参数签名
	@RequestMapping("/sign")
	public Map<String,Object> weiXinSign(@RequestParam("url") String url, HttpServletRequest request) {
		return weiXinService.getRequestSign(url);
	}
	
}
