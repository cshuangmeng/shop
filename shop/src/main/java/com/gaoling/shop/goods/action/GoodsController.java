package com.gaoling.shop.goods.action;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.goods.service.GoodsService;
import com.gaoling.shop.system.pojo.Result;

@RestController
@RequestMapping("/goods")
@CrossOrigin(methods = RequestMethod.POST, origins = AppConstant.TRUST_CROSS_ORIGINS)
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	
	//加载商品列表
	@RequestMapping("/list")
	public Result loadGoods(@RequestParam HashMap<Object,Object> param){
		Result result=null;
		try {
			result=goodsService.loadGoods(param);
		} catch (Exception e) {
			result=goodsService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//加载商品详情
	@RequestMapping("/info")
	public Result loadGoodsDetail(@RequestParam(defaultValue="0")String id){
		Result result=null;
		try {
			result=goodsService.loadGoodsDetail(Integer.parseInt(id));
		} catch (Exception e) {
			result=goodsService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
}
