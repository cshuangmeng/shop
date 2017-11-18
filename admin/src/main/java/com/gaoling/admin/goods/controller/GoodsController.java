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

import com.gaoling.admin.goods.pojo.Goods;
import com.gaoling.admin.goods.service.GoodsService;
import com.gaoling.admin.system.pojo.Result;
import com.gaoling.admin.system.service.CommonService;
import com.gaoling.admin.util.AppConstant;

@Controller
@RequestMapping("/goods")
public class GoodsController extends CommonService{
	
	@Autowired
	private GoodsService goodsService;

	//进入商品相关页面
	@RequestMapping("/{page}")
	public String index(@PathVariable String page){
		return "/goods/"+page;
	}
	
	//商品列表
	@ResponseBody
	@RequestMapping("/list")
	public Result list(@RequestParam Map<Object,Object> param){
		Result result=null;
		try {
			result=putResult(goodsService.loadGoods(param));
		} catch (Exception e) {
			result=putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//审核商品
	@ResponseBody
	@RequestMapping("/examine")
	public Result examine(@RequestParam String goodsIds,@RequestParam int state){
		Result result=null;
		try {
			goodsService.examineGoods(goodsIds, state);
			result=putResult();
		} catch (Exception e) {
			result=putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//商品详情
	@RequestMapping("/detail")
	public String detail(@RequestParam int goodsId,Model model){
		model.addAttribute("goods", goodsService.getDetailGoods(goodsId));
		return "/goods/detail";
	}
	
	//商品详情
	@RequestMapping("/edit")
	public String edit(@RequestParam(defaultValue="0") int goodsId,Model model){
		if(goodsId>0){
			model.addAttribute("goods", goodsService.getEditingGoods(goodsId));
		}
		return "/goods/edit";
	}
	
	//新增或更新商品
	@RequestMapping("/submit")
	public String editSubmit(@ModelAttribute Goods goods,@RequestParam(required=false)MultipartFile logoImg
			,@RequestParam(required=false)MultipartFile[] infoImg,@RequestParam(required=false)MultipartFile[] detailImg
			,@RequestParam String params){
		goodsService.saveOrUpdateGoods(goods, logoImg, infoImg, detailImg, params);
		return "redirect:/goods/index";
	}
	
}
