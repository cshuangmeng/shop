package com.gaoling.admin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaoling.admin.shop.entity.Adgroup;
import com.gaoling.admin.shop.entity.JsonResult;
import com.gaoling.admin.shop.service.AdgroupService;


@Controller
public class AdGroupController {
	
	@Autowired
	private AdgroupService adgroupService;

	//进入广告管理首页
	@RequestMapping("/manager/ad/{prefix}")
	public String index(@PathVariable String prefix){
		return "ad/"+prefix;
	}
	
	//新增广告组
	@RequestMapping("/manager/ad/editGroup")
	public String addAdgroup(@RequestParam(defaultValue="0") int gid,Model model){
		try {
			if(gid>0){
				model.addAttribute("adgroup", adgroupService.getAdgroup(gid));
			}
			return "ad/addGroup";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/error";
	}
	
	//提交广告组
	@RequestMapping("/manager/ad/updateGroup")
	public String updateAdgroup(@ModelAttribute Adgroup adgroup){
		try {
			if(adgroup.getId()>0){
				adgroupService.updateAdgroup(adgroup);
			}else{
				adgroupService.addAdgroup(adgroup);
			}
			return "redirect:/manager/ad/index";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/error";
	}
	
	//查询广告组
	@ResponseBody
	@RequestMapping("/manager/ad/queryGroup")
	public JsonResult queryAdgroup(@ModelAttribute Adgroup adgroup){
		JsonResult json=new JsonResult();
		try {
			json.setData(adgroupService.queryAdgroup(adgroup));
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
	//获取广告详情
	@RequestMapping("/manager/ad/groupDetail")
	public String getAdgroup(@RequestParam int gid,Model model){
		try {
			model.addAttribute("adgroup", adgroupService.getAdgroup(gid));
			return "ad/groupDetail";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/error";
	}
	
	//删除广告组
	@ResponseBody
	@RequestMapping("/manager/ad/delGroup")
	public JsonResult deleteAdgroup(@RequestParam int gid){
		JsonResult json=new JsonResult();
		try {
			adgroupService.deleteAdgroup(gid);
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
	//更改广告组状态
	@ResponseBody
	@RequestMapping("/manager/ad/groupStatus")
	public JsonResult changeAdgroupStatus(@RequestParam int gid,@RequestParam int status){
		JsonResult json=new JsonResult();
		try {
			adgroupService.updateAdgroupStatus(gid, status);
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
}
