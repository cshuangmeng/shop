package com.gaoling.admin.goods.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaoling.admin.system.pojo.Result;
import com.gaoling.admin.system.service.CommonService;
import com.gaoling.admin.util.AppConstant;

@RestController
@RequestMapping("/util")
public class CommonController extends CommonService{

	//查询字典集合
	@RequestMapping("/dicts")
	public Result getDictList(@RequestParam String parentName){
		Result result=null;
		try {
			result=putResult(getDicts(parentName));
		} catch (Exception e) {
			result=putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
}
