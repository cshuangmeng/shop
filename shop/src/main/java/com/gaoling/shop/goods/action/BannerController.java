package com.gaoling.shop.goods.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	@CrossOrigin(origins="*",methods=RequestMethod.POST)
	public Result banners(@RequestParam Integer index,@RequestParam(required=false)String system
			,@RequestParam(required=false) String appType){
		Result result=null;
		try {
			result=bannerService.loadWKBanners(index,system,appType);
		} catch (Exception e) {
			result=bannerService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//加载商品列表
	@RequestMapping("/upload")
	@CrossOrigin(origins="*",methods=RequestMethod.POST)
	public Result uploadBanners(@RequestParam(required=false) MultipartFile[] launch
			,@RequestParam(required=false) MultipartFile[] top,@RequestParam(required=false) MultipartFile[] bottom,
			@RequestParam String[] target,@RequestParam String[] url,@RequestParam String appType
			,@RequestParam String[] key){
		Result result=null;
		try {
			result=bannerService.uploadBanner(appType,launch, top, bottom, target, url, key);
		} catch (Exception e) {
			result=bannerService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
}
