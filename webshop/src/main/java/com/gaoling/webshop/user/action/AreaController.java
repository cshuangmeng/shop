package com.gaoling.webshop.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaoling.webshop.common.AppConstant;
import com.gaoling.webshop.system.pojo.Result;
import com.gaoling.webshop.user.service.AreaService;

@RestController
@RequestMapping("/area")
@CrossOrigin(methods = RequestMethod.POST, origins = AppConstant.TRUST_CROSS_ORIGINS)
public class AreaController {

	@Autowired
	private AreaService areaService;
	
	//加载地区
	@RequestMapping("/list")
	public Result list(@RequestParam(defaultValue="100000")String parentId){
		Result result=null;
		try {
			result=areaService.queryCildAreas(Integer.parseInt(parentId));
		} catch (Exception e) {
			result=areaService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
}
