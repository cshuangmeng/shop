package com.gaoling.admin.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaoling.admin.system.pojo.Result;
import com.gaoling.admin.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	//进入商品相关页面
	@RequestMapping("/{page}")
	public String index(@PathVariable String page){
		return "/user/"+page;
	}
	
	//用户列表
	@RequestMapping("/list")
	@ResponseBody
	public Result orderList(@RequestParam Map<Object,Object> param){
		return userService.putResult(userService.queryUsers(param));
	}
	
	//赋予用户提现特权
	@RequestMapping("/right")
	@ResponseBody
	public Result giveRight(@RequestParam String userIds,@RequestParam Integer right){
		userService.giveUserCashExchangeRight(userIds, right);
		return userService.putResult();
	}
	
}
