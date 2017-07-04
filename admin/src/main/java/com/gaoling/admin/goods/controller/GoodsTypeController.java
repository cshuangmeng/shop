package com.gaoling.admin.goods.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaoling.admin.goods.service.GoodsTypeService;
import com.gaoling.admin.system.pojo.Result;
import com.gaoling.admin.system.service.CommonService;
import com.gaoling.admin.util.AppConstant;

@Controller
@RequestMapping("/gtype")
public class GoodsTypeController extends CommonService{
	
	@Autowired
	private GoodsTypeService goodsTypeService;

	//商品类别列表
	@ResponseBody
	@RequestMapping("/list")
	public Result list(@RequestParam Map<Object,Object> param){
		Result result=null;
		try {
			result=putResult(goodsTypeService.loadGoodsTypes());
		} catch (Exception e) {
			result=putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
}
