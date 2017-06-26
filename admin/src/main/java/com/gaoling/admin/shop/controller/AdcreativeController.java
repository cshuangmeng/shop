package com.gaoling.admin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gaoling.admin.shop.entity.Adcreative;
import com.gaoling.admin.shop.entity.JsonResult;
import com.gaoling.admin.shop.service.AdcreativeService;


@Controller
public class AdcreativeController {

	@Autowired
	private AdcreativeService adcreativeService;
	
	//提交广告素材
	@RequestMapping("/manager/ad/updateCreative")
	public String updateAdcreative(@ModelAttribute Adcreative adcreative
			,@RequestParam(required=false)MultipartFile imgFile){
		JsonResult json=new JsonResult();
		try {
			if(adcreative.getId()>0){
				adcreativeService.updateAdcreative(adcreative,imgFile);
			}else{
				adcreativeService.addAdcreative(adcreative,imgFile);
			}
			return "redirect:/manager/ad/groupDetail?gid="+adcreative.getGroupId();
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return "redirect:/error";
	}
	
	//加载广告组中所有素材
	@ResponseBody
	@RequestMapping("/manager/ad/groupCreative")
	public JsonResult getAdcreativeOfAdgroup(@RequestParam int gid){
		JsonResult json=new JsonResult();
		try {
			json.setData(adcreativeService.loadAdcreativeByGroupId(gid));
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
	//删除广告素材
	@ResponseBody
	@RequestMapping("/manager/ad/delCreative")
	public JsonResult deleteAdcreative(@RequestParam int cid){
		JsonResult json=new JsonResult();
		try {
			adcreativeService.deleteAdcreative(cid);
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
	//加载广告素材详情
	@ResponseBody
	@RequestMapping("/manager/ad/creativeDetail")
	public JsonResult getAdcreativeDetail(@RequestParam int cid){
		JsonResult json=new JsonResult();
		try {
			json.setData(adcreativeService.getAdcreative(cid));
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
}
