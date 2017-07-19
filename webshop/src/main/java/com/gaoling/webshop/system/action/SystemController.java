package com.gaoling.webshop.system.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gaoling.webshop.goods.service.GoodsService;

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
		model.addAttribute("result", goodsService.loadIndexGoodsList());
		return "index";
	}
	
}
