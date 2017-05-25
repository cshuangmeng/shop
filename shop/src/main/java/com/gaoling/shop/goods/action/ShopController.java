package com.gaoling.shop.goods.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.goods.service.ShopService;
import com.gaoling.shop.system.pojo.Result;

@RestController
@RequestMapping("/shop")
@CrossOrigin(methods = RequestMethod.POST, origins = AppConstant.TRUST_CROSS_ORIGINS)
public class ShopController {

	@Autowired
	private ShopService shopService;
	
	//加载门店信息
	@RequestMapping("/info")
	public Result loadShopDetail(@RequestParam(defaultValue="0")String id){
		Result result=null;
		try {
			result=shopService.loadShopDetail(Integer.parseInt(id));
		} catch (Exception e) {
			result=shopService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
}
