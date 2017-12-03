package com.gaoling.admin.goods.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gaoling.admin.goods.pojo.Shop;
import com.gaoling.admin.goods.service.ShopService;
import com.gaoling.admin.system.pojo.Result;
import com.gaoling.admin.system.service.CommonService;
import com.gaoling.admin.util.AppConstant;

@Controller
@RequestMapping("/shop")
public class ShopController extends CommonService{
	
	@Autowired
	private ShopService shopService;

	//进入商铺相关页面
	@RequestMapping("/{page}")
	public String index(@PathVariable String page){
		return "/shop/"+page;
	}
	
	//商铺列表
	@ResponseBody
	@RequestMapping("/list")
	public Result list(@RequestParam Map<Object,Object> param){
		Result result=null;
		try {
			result=putResult(shopService.queryShopsToMap(param));
		} catch (Exception e) {
			result=putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//审核商铺
	@ResponseBody
	@RequestMapping("/examine")
	public Result examine(@RequestParam String shopIds,@RequestParam int state){
		Result result=null;
		try {
			shopService.examineShop(shopIds, state);
			result=putResult();
		} catch (Exception e) {
			result=putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//商铺详情
	@RequestMapping("/detail")
	public String detail(@RequestParam int shopId,Model model){
		model.addAttribute("shop", shopService.getDetailShop(shopId));
		return "/shop/detail";
	}
	
	//商铺汇总
	@RequestMapping("/summary")
	@ResponseBody
	public Result summary(){
		Result result=null;
		try {
			result=shopService.statShopSummary();
		} catch (Exception e) {
			result=putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//商铺详情
	@RequestMapping("/edit")
	public String edit(@RequestParam(defaultValue="0") int shopId,Model model){
		if(shopId>0){
			model.addAttribute("shop", shopService.getShop(shopId));
		}
		return "/shop/edit";
	}
	
	//新增或更新商品
	@RequestMapping("/submit")
	public String edit(@ModelAttribute Shop shop,@RequestParam(required=false)MultipartFile logoImg
			,@RequestParam(required=false)MultipartFile[] infoImg)throws Exception{
		shopService.saveOrUpdateShop(shop, logoImg, infoImg);
		return "redirect:/shop/index";
	}
	
}
