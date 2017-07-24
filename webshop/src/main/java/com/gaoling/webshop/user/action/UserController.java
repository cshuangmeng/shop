package com.gaoling.webshop.user.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gaoling.webshop.common.AppConstant;
import com.gaoling.webshop.common.ThreadCache;
import com.gaoling.webshop.pay.service.UserTradeLogService;
import com.gaoling.webshop.system.pojo.Result;
import com.gaoling.webshop.system.service.CommonService;
import com.gaoling.webshop.user.pojo.User;
import com.gaoling.webshop.user.service.ShoppingCarService;
import com.gaoling.webshop.user.service.UserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController extends CommonService{
	
	@Autowired
	private UserService userService;
	@Autowired
	private ShoppingCarService shoppingCarService;
	@Autowired
	private UserTradeLogService userTradeLogService;
	
	//下发验证码
	@RequestMapping("/code")
	public Result sendCode(@RequestParam(required=false) String mobile){
		Result result=null;
		try {
			result=userService.sendCode(mobile);
		} catch (Exception e) {
			result=userService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//用户注册
	@RequestMapping("/register")
	public Result register(@RequestParam(required=false) String code
			,@RequestParam(required=false) String cellphone,@RequestParam(defaultValue="0") int platform
			,@RequestParam(required=false) String openId,@RequestParam(required=false) String password){
		Result result=null;
		try {
			result=userService.register(code, cellphone, openId, password, platform);
		} catch (Exception e) {
			result=userService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//用户登录
	@RequestMapping("/login")
	public String login(@RequestParam(required=false) String unionId,@RequestParam(required=false) String cellphone
			,@RequestParam(required=false) String password,@RequestParam(defaultValue="0") int platform,Model model
			,HttpServletRequest request){
		Result result=userService.login(unionId, cellphone, password, platform);
		if(result.getCode()!=AppConstant.SUCCESS){
			model.addAttribute("result", result);
			model.addAttribute("cellphone", cellphone);
			model.addAttribute("password", password);
			return "login";
		}else{
			request.getSession().setAttribute(AppConstant.STORE_USER_PARAM_NAME, (User)result.getData());
		}
		return "redirect:/index";
	}
	
	//我的购物车
	@RequestMapping("/car")
	public String myShoppingCar(Model model){
		User user=(User)ThreadCache.getData(AppConstant.STORE_USER_PARAM_NAME);
		Result result=shoppingCarService.queryMyShoppingCar(user.getUuid());
		System.out.println(JSONObject.fromObject(result));
		model.addAttribute("result", result);
		return "order/shoppingCar";
	}
	
	//增加指定数量的商品至购物车中
	@RequestMapping("/addGoods")
	public Result addShoppingCar(@RequestParam(required=false) String uuid
			,@RequestParam(defaultValue="0")String goodsId,@RequestParam(defaultValue="0")String amount){
		Result result=null;
		try {
			result=shoppingCarService.addGoodsToShoppingCar(Integer.parseInt(goodsId), uuid, Integer.parseInt(amount));
		} catch (Exception e) {
			result=userService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//删除购物车内的商品
	@RequestMapping("/dropGoods")
	public Result dropShoppingCar(@RequestParam(required=false) String uuid,@RequestParam(defaultValue="0")String goodsId){
		Result result=null;
		try {
			result=shoppingCarService.removeGoodsFromShoppingCar(uuid, Integer.parseInt(goodsId));
		} catch (Exception e) {
			result=userService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//一次添加多个商品至购物车
	@RequestMapping("/addMultiGoods")
	public Result addMultiShoppingCar(@RequestParam(required=false)String uuid,@RequestParam(required=false)String items){
		Result result=null;
		try {
			result=shoppingCarService.addMultiGoodsToShoppingCar(items, uuid);
		} catch (Exception e) {
			result=userService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//下发用户交易记录
	@RequestMapping("/tradeLog")
	public Result userTradeLog(@RequestParam(required=false)String uuid){
		Result result=null;
		try {
			result=userTradeLogService.queryUserTradeLogs(uuid);
		} catch (Exception e) {
			result=userService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
}
