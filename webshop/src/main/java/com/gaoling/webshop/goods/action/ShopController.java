package com.gaoling.webshop.goods.action;

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
import com.gaoling.webshop.common.ThreadCache;
import com.gaoling.webshop.goods.pojo.Cooperation;
import com.gaoling.webshop.goods.pojo.ShopFollower;
import com.gaoling.webshop.goods.service.CooperationService;
import com.gaoling.webshop.goods.service.ShopService;
import com.gaoling.webshop.system.pojo.Result;
import com.gaoling.webshop.user.pojo.User;

@Controller
@RequestMapping("/shop")
@CrossOrigin(methods = RequestMethod.POST, origins = AppConstant.TRUST_CROSS_ORIGINS)
public class ShopController {

	@Autowired
	private ShopService shopService;
	@Autowired
	private CooperationService cooperationService;
	
	//加载门店信息
	@RequestMapping("/info")
	public String loadShopDetail(@RequestParam(defaultValue="0")String id,Model model){
		User user=(User)ThreadCache.getData(AppConstant.STORE_USER_PARAM_NAME);
		Result result=shopService.loadShopDetail(Integer.parseInt(id),null!=user?user.getUuid():null);
		model.addAttribute("result",result);
		return "shop/shopDetail";
	}
	
	//关注门店
	@RequestMapping("/sub")
	public Result subscribeShop(@RequestParam(defaultValue="0")String shopId,@RequestParam(required=false)String uuid,Model model){
		Result result=shopService.followShop(Integer.parseInt(shopId), uuid, ShopFollower.STATE_TYPE_ENUM.FOLLOWED.getState());
		model.addAttribute("result",result);
		return result;
	}
	
	//取消关注门店
	@RequestMapping("/unsub")
	public Result unsubscribeShop(@RequestParam(defaultValue="0")String shopId,@RequestParam(required=false)String uuid){
		Result result=null;
		try {
			result=shopService.followShop(Integer.parseInt(shopId), uuid, ShopFollower.STATE_TYPE_ENUM.CANCEL.getState());
		} catch (Exception e) {
			result=shopService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//商户合作
	@RequestMapping("/cooperate")
	@ResponseBody
	public Result cooperate(@ModelAttribute Cooperation cooperation){
		Result result=null;
		try {
			result=cooperationService.addCooperation(cooperation);
		} catch (Exception e) {
			result=shopService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
}
