package com.gaoling.admin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaoling.admin.shop.entity.BdInfo;
import com.gaoling.admin.shop.entity.JsonResult;
import com.gaoling.admin.shop.service.BdService;


@Controller
public class BdController {
	
	@Autowired
	private BdService bdService;
	
	//进入地推管理首页
	@RequestMapping("/data/bd")
	public String bdIndex(){
		return "data/bd";
	}
	
	//加载所有地推人员列表
	@ResponseBody
	@RequestMapping("/data/bd/list")
	public JsonResult loadAllBds(){
		JsonResult json=new JsonResult();
		try {
			json.setData(bdService.loadAllBd());
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
	//加载指定地推人员信息
	 @ResponseBody
	 @RequestMapping("/data/bd/info")
	 public JsonResult bdDetail(@RequestParam int bdId){
		 JsonResult json=new JsonResult();
		 try {
			json.setData(bdService.getBd(bdId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	 }
	
	//新增或更新地推人员
	@ResponseBody
	@RequestMapping("/data/bd/update")
	public JsonResult addOrUpdateBd(@ModelAttribute("bdInfo")BdInfo bdInfo){
		JsonResult json=new JsonResult();
		try {
			BdInfo bd=bdService.loadBdByUsername(bdInfo.getUsername());
			if(bdInfo.getId()>0){
				if(null==bd||bd.getId()==bdInfo.getId()){
					bdService.updateBd(bdInfo);
				}else{
					json.setResult(2);
				}
			}else{
				if(null==bd){
					bdService.addBd(bdInfo);
				}else{
					json.setResult(2);
				}
			}
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
}
