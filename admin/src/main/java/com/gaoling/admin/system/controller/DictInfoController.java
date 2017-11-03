package com.gaoling.admin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaoling.admin.system.pojo.Result;
import com.gaoling.admin.system.service.DictInfoService;

@Controller
@RequestMapping("/dict")
public class DictInfoController {

	@Autowired
	private DictInfoService dictInfoService;
	
	//进入字典相关页面
	@RequestMapping("/{page}")
	public String index(@PathVariable String page){
		return "/other/"+page;
	}
	
	//加载新订单通知手机号
	@RequestMapping("/listNoticePhone")
	@ResponseBody
	public Result getNewOrderNoticePhones(){
		return dictInfoService.putResult(dictInfoService.getNewOrderNoticePhones());
	}
	
	//添加新订单通知手机号
	@RequestMapping("/addNoticePhone")
	@ResponseBody
	public Result addNewNoticePhone(@RequestParam String contact,@RequestParam String telephone){
		return dictInfoService.addNewNoticePhone(contact, telephone);
	}
	
	//删除新订单通知手机号
	@RequestMapping("/delNoticePhone")
	@ResponseBody
	public Result deleteNoticePhone(@RequestParam String telephone){
		return dictInfoService.deleteNoticePhone(telephone);
	}
	
	
}
