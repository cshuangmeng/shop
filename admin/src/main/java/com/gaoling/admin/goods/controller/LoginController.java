package com.gaoling.admin.goods.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gaoling.admin.system.service.SysUserService;

@Controller
public class LoginController {
	
	@Autowired
	private SysUserService userService;
	
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
	
	//用户登录
	@RequestMapping("/loginSubmit")
	public String shiroLogin(@RequestParam String username, @RequestParam String pwd
			,RedirectAttributes attributes, Locale locale) {
		return userService.login(username, pwd, attributes, locale);
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
