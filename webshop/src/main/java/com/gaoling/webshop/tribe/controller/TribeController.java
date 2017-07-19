package com.gaoling.webshop.tribe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaoling.webshop.common.AppConstant;
import com.gaoling.webshop.system.pojo.Result;
import com.gaoling.webshop.tribe.service.TribeService;

@RestController
@RequestMapping("/tribe")
@CrossOrigin(methods = RequestMethod.POST, origins = AppConstant.TRUST_CROSS_ORIGINS)
public class TribeController {
	
	@Autowired
	private TribeService tribeService;
	
	//加载部落成员信息
	@RequestMapping("/info")
	public Result queryTribeMembers(@RequestParam(required=false)String uuid){
		Result result=null;
		try {
			result=tribeService.getMyTribeInfo(uuid);
		} catch (Exception e) {
			result=tribeService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//修改部落名称
	@RequestMapping("/rename")
	public Result editTribeName(@RequestParam(required=false)String uuid,@RequestParam(required=false)String name){
		Result result=null;
		try {
			result=tribeService.editTribeName(uuid, name);
		} catch (Exception e) {
			result=tribeService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}

}
