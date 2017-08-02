package com.gaoling.webshop.system.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gaoling.webshop.goods.service.GoodsService;
import com.gaoling.webshop.system.pojo.Result;

import net.sf.json.JSONObject;

@Controller
public class SystemController {
	
	@Autowired
	private GoodsService goodsService;

	//跳转页面
	@RequestMapping("/{dir}/{page}")
	public String index(@PathVariable String dir,@PathVariable String page){
		return dir+"/"+page;
	}
	
	//跳转页面
	@RequestMapping("/{page}")
	public String index(@PathVariable String page){
		return page;
	}
	
	//首页
	@RequestMapping("/index")
	public String index(Model model){
		Result result=goodsService.loadIndexGoodsList();
		System.out.println(JSONObject.fromObject(result));
		model.addAttribute("result", result);
		return "index";
	}
	
}
