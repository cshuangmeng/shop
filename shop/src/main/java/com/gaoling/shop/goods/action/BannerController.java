package com.gaoling.shop.goods.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	
	//加载App类型
	@RequestMapping("/login")
	@CrossOrigin(origins="*",methods=RequestMethod.POST)
	public Result bannerUserLogin(@RequestParam(required=false)String username,@RequestParam(required=false)String password){
		Result result=null;
		try {
			result=bannerService.bannerUserLogin(username, password);
		} catch (Exception e) {
			result=bannerService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//加载App类型
	@RequestMapping("/apps")
	@CrossOrigin(origins="*",methods=RequestMethod.POST)
	public Result getAppList(@RequestParam(required=false)String token){
		Result result=null;
		try {
			result=bannerService.getAppList(token);
		} catch (Exception e) {
			result=bannerService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//加载App类型
	@RequestMapping("/editApp")
	@CrossOrigin(origins="*",methods=RequestMethod.POST)
	public Result editApp(@RequestParam(required=false)String token,@RequestParam Integer id,@RequestParam String name,@RequestParam String value
			,@RequestParam Integer state,@RequestParam String remark){
		Result result=null;
		try {
			result=bannerService.editApp(token, id, name, value, state, remark);
		} catch (Exception e) {
			result=bannerService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//加载App类型
	@RequestMapping("/editAppState")
	@CrossOrigin(origins="*",methods=RequestMethod.POST)
	public Result editAppState(@RequestParam(required=false)String token,@RequestParam Integer id,@RequestParam Integer state){
		Result result=null;
		try {
			result=bannerService.editAppState(token, id, state);
		} catch (Exception e) {
			result=bannerService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//加载Banner列表
	@RequestMapping("/banners")
	@CrossOrigin(origins="*",methods=RequestMethod.POST)
	public Result getBannerList(@RequestParam(required=false)String token){
		Result result=null;
		try {
			result=bannerService.getBannerList(token);
		} catch (Exception e) {
			result=bannerService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//编辑Banner
	@RequestMapping("/editBanner")
	@CrossOrigin(origins="*",methods=RequestMethod.POST)
	public Result editBanner(@RequestParam(required=false)String token
			,@RequestParam(required=false) Integer bannerId,@RequestParam(required=false) String appType
			,@RequestParam(required=false) Integer index,@RequestParam(required=false) String platform
			,@RequestParam(required=false) String target,@RequestParam(required=false) String url
			,@RequestParam(required=false) String remark,@RequestParam(required=false) Integer state
			,HttpServletRequest request){
		Result result=null;
		try {
			MultipartFile file=null;
			if(request instanceof MultipartHttpServletRequest){
				file=((MultipartHttpServletRequest)request).getFile("file");
			}
			result=bannerService.editBanner(token, bannerId, file, appType, index, platform, target, url, remark, state);
		} catch (Exception e) {
			result=bannerService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//编辑Banner
	@RequestMapping("/editBannerState")
	@CrossOrigin(origins="*",methods=RequestMethod.POST)
	public Result editBannerState(@RequestParam(required=false)String token,@RequestParam Integer id,@RequestParam Integer state){
		Result result=null;
		try {
			result=bannerService.editBannerState(token, id, state);
		} catch (Exception e) {
			result=bannerService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
}
