package com.gaoling.admin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaoling.admin.shop.entity.DictInfo;
import com.gaoling.admin.shop.entity.JsonResult;
import com.gaoling.admin.shop.service.DictInfoService;

@Controller
public class DictInfoController {

	@Autowired
	private DictInfoService dictInfoService;
	
	//进入字典维护页面
	@RequestMapping("/data/dict")
	public String index(){
		return "data/dict";
	}
	
	//下发字典列表
	@ResponseBody
	@RequestMapping("/dict/option")
	public JsonResult loadDictOptions(@RequestParam String code){
		JsonResult json=new JsonResult();
		try {
			json.setData(dictInfoService.loadDicts(code));
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
	//加载所有字典信息
	@ResponseBody
	@RequestMapping("/data/dict/list")
	public JsonResult loadDictOptions(){
		JsonResult json=new JsonResult();
		try {
			json.setData(dictInfoService.loadAllDicts());
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
	//获取字典信息
	@ResponseBody
	@RequestMapping("/data/dict/info")
	public JsonResult getDictOptions(@RequestParam int id){
		JsonResult json=new JsonResult();
		try {
			json.setData(dictInfoService.getDict(id));
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
	//保存字典
	@ResponseBody
	@RequestMapping("/data/dict/update")
	public JsonResult editDictOptions(@ModelAttribute DictInfo dict){
		JsonResult json=new JsonResult();
		try {
			DictInfo di=dictInfoService.getDictByName(dict.getDictCode(), dict.getDictValue());
			if(dict.getId()>0){
				//判断字典值是否重复
				if(null==di||dict.getId()==di.getId()){
					dictInfoService.updateDict(dict);
				}else{
					json.setResult(2);
				}
			}else if(null==di){
				dictInfoService.addDict(dict);
			}else{
				json.setResult(2);
			}
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
	//删除字典
	@ResponseBody
	@RequestMapping("/data/dict/del")
	public JsonResult delDictOptions(@RequestParam int id){
		JsonResult json=new JsonResult();
		try {
			dictInfoService.deleteDict(id);
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
}
