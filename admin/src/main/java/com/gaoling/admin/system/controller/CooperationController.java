package com.gaoling.admin.system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaoling.admin.system.pojo.Result;
import com.gaoling.admin.system.service.CooperationService;

@Controller
@RequestMapping("/coo")
public class CooperationController {

	@Autowired
	private CooperationService cooperationService;
	
	//进入合作相关页面
	@RequestMapping("/{page}")
	public String index(@PathVariable String page){
		return "/other/"+page;
	}
	
	//加载合作申请
	@RequestMapping("/list")
	@ResponseBody
	public Result queryCooperations(@RequestParam Map<String,Object> param){
		return cooperationService.putResult(cooperationService.queryCooperations(param));
	}
	
}
