package com.gaoling.shop.goods.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.goods.service.BannerService;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;

@RestController
@RequestMapping("/banner")
public class BannerController extends CommonService{
	
	@Autowired
	private BannerService bannerService;
	
	//加载商品列表
	@RequestMapping("/wk")
	public Result banners(@RequestParam Integer index){
		Result result=null;
		try {
			result=bannerService.loadWKBanners(index);
		} catch (Exception e) {
			result=bannerService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
}
