package com.gaoling.shop.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;
import com.gaoling.shop.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends CommonService{
	
	@Autowired
	private UserService userService;

	//下发验证码
	@RequestMapping("/code")
	public Result sendCode(@RequestParam(required=false) String mobile){
		return userService.sendCode(mobile);
	}
	
	//用户注册
	@RequestMapping("/register")
	public Result register(@RequestParam(required=false) String code
			,@RequestParam(required=false) String mobile,@RequestParam(required=false) String openId){
		return userService.register(code, mobile, openId);
	}
	
	//下发验证码
	@RequestMapping("/login")
	public Result login(@RequestParam(required=false) String openId){
		return userService.login(openId);
	}
	
}
