package com.gaoling.webshop.goods.action;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gaoling.webshop.common.AppConstant;
import com.gaoling.webshop.common.DataUtil;
import com.gaoling.webshop.goods.service.GoodsService;
import com.gaoling.webshop.goods.service.ShopService;
import com.gaoling.webshop.system.pojo.Result;

@Controller
@RequestMapping("/goods")
@CrossOrigin(methods = RequestMethod.POST, origins = AppConstant.TRUST_CROSS_ORIGINS)
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	@Autowired
	private ShopService shopService;
	
	//进入商品列表
	@RequestMapping("/list")
	public String goodsListIndex(@RequestParam HashMap<Object,Object> param,Model model){
		model.addAttribute("result",shopService.putResult(DataUtil.mapOf("shops",shopService.queryShops(DataUtil.mapOf()))));
		return "goods/goodsList";
	}
	
	//搜索商品
	@RequestMapping("/search")
	public String searchGoods(@RequestParam HashMap<Object,Object> param,Model model){
		model.addAttribute("result", goodsService.loadGoods(param));
		return "goods/goodsResult";
	}
	
	//加载商品详情
	@RequestMapping("/info")
	public String loadGoodsDetail(@RequestParam(defaultValue="0")String id,@RequestParam(required=false)String uuid,Model model){
		Result result=goodsService.loadGoodsDetail(Integer.parseInt(id),uuid);
		model.addAttribute("result",result);
		return "goods/goodsDetail";
	}
	
}
