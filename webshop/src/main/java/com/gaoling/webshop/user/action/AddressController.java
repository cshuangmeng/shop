package com.gaoling.webshop.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaoling.webshop.common.AppConstant;
import com.gaoling.webshop.system.pojo.Result;
import com.gaoling.webshop.user.pojo.Address;
import com.gaoling.webshop.user.service.AddressService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/address")
@CrossOrigin(methods = RequestMethod.POST, origins = AppConstant.TRUST_CROSS_ORIGINS)
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	//加载用户地址
	@RequestMapping("/list")
	public String myAddresses(Model model){
		System.out.println(JSONObject.fromObject(addressService.loadMyAddresses()));
		model.addAttribute("result", addressService.loadMyAddresses());
		return "user/addressList";
	}
	
	//添加用户地址
	@RequestMapping("/save")
	@ResponseBody
	public Result saveAddresses(@ModelAttribute Address address){
		return addressService.saveNewAddress(address);
	}
	
	//获取地址详情
	@RequestMapping("/detail")
	@ResponseBody
	public Result getAddresses(@RequestParam(defaultValue="0")String id){
		Result result=null;
		try {
			result=addressService.getAddressInfo(Integer.parseInt(id));
		} catch (Exception e) {
			result=addressService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//更新用户地址
	@RequestMapping("/update")
	@ResponseBody
	public Result updateAddresses(@ModelAttribute Address address){
		return addressService.updateOldAddress(address);
	}
	
}
