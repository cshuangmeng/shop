package com.gaoling.admin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaoling.admin.shop.entity.JsonResult;
import com.gaoling.admin.shop.service.AreaService;


@Controller
public class AreaController {
	
	@Autowired
	private AreaService areaService;
	
	//读取城市列表
	@ResponseBody
	@RequestMapping("/area/cityList")
	public JsonResult loadCityList(){
		JsonResult json=new JsonResult();
		try {
			json.setData(areaService.loadCityList());
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
	//读取区划列表
	@ResponseBody
	@RequestMapping("/area/regionList")
	public JsonResult loadCityList(int cityCode){
		JsonResult json=new JsonResult();
		try {
			json.setData(areaService.loadRegionList(cityCode));
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
}
