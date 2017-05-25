package com.gaoling.shop.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.user.pojo.Address;
import com.gaoling.shop.user.service.AddressService;

@RestController
@RequestMapping("/address")
@CrossOrigin(methods = RequestMethod.POST, origins = AppConstant.TRUST_CROSS_ORIGINS)
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	//加载用户地址
	@RequestMapping("/my")
	public Result myAddresses(@RequestParam(required=false)String uuid){
		Result result=null;
		try {
			result=addressService.loadMyAddresses(uuid);
		} catch (Exception e) {
			result=addressService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//添加用户地址
	@RequestMapping("/save")
	public Result saveAddresses(@RequestParam(required=false)String uuid,@ModelAttribute Address address){
		Result result=null;
		try {
			result=addressService.saveNewAddress(address,uuid);
		} catch (Exception e) {
			result=addressService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//更新用户地址
	@RequestMapping("/update")
	public Result updateAddresses(@RequestParam(required=false)String uuid,@ModelAttribute Address address){
		Result result=null;
		try {
			result=addressService.updateOldAddress(address,uuid);
		} catch (Exception e) {
			result=addressService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
}
