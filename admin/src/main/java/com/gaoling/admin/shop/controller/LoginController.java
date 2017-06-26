package com.gaoling.admin.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	//进入管理员登录页面
	@RequestMapping("/login")
	public String login(Model model){
		return "login";
	}
	
	//页面未找到跳转
	@RequestMapping("/noFound")
	public String noFound(){
		return "noFound";
	}
	
	//服务器错误跳转
	@RequestMapping("/error")
	public String error(){
		return "error";
	}
	
	//拒绝访问
	@RequestMapping("/denied")
	public String denied(){
		return "denied";
	}
	
	//首页
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
}
