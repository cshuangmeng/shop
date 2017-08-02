package com.gaoling.webshop.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaoling.webshop.common.AppConstant;
import com.gaoling.webshop.common.ThreadCache;
import com.gaoling.webshop.common.VerifyCodeUtil;
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
	
	//下发短信验证码
	@RequestMapping("/code")
	@ResponseBody
	public Result sendCode(@RequestParam(required=false) String cellphone
			,@RequestParam(required=false) String code,HttpServletRequest request){
		Result result=null;
		try {
			String verifyCode=(String)request.getSession().getAttribute(AppConstant.STORE_VERIFY_CODE_NAME);
			result=userService.sendCode(cellphone,verifyCode,code);
			if(result.getCode()==AppConstant.SUCCESS){
				request.getSession().removeAttribute(AppConstant.STORE_VERIFY_CODE_NAME);
			}
		} catch (Exception e) {
			result=userService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//下发图形验证码
	@RequestMapping("/verify")
	public void verify(HttpServletRequest request,HttpServletResponse response){
		try {
			String code=VerifyCodeUtil.generateVerifyCode(4);
			request.getSession();
			request.getSession().setAttribute(AppConstant.STORE_VERIFY_CODE_NAME, code);
			VerifyCodeUtil.outputImage(AppConstant.VERIFY_CODE_WIDTH, AppConstant.VERIFY_CODE_HEIGHT, response.getOutputStream(), code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//用户注册
	@RequestMapping("/register")
	@ResponseBody
	public Result register(@RequestParam(required=false) String code
			,@RequestParam(required=false) String cellphone,@RequestParam(required=false) String openId
			,@RequestParam(required=false) String password)throws Exception{
		Result result=null;
		try {
			result=userService.register(openId, code, cellphone, password);;
		} catch (Exception e) {
			result=userService.putResult(AppConstant.SYSTEM_ERROR_CODE);
		}
		return result;
	}
	
	//用户登录
	@RequestMapping("/login")
	public String login(@RequestParam(required=false) String code,@RequestParam(required=false) String cellphone
			,@RequestParam(required=false) String password,Model model
			,HttpServletRequest request){
		Result result=userService.login(code, cellphone, password);
		if(result.getCode()!=AppConstant.SUCCESS){
			if(StringUtils.isNotEmpty(code)){
				model.addAttribute("openId", result.getData());
				return "/register";
			}else{
				model.addAttribute("result", result);
				model.addAttribute("cellphone", cellphone);
				model.addAttribute("password", password);
				return "login";
			}
		}else{
			request.getSession().setAttribute(AppConstant.STORE_USER_PARAM_NAME, (User)result.getData());
		}
		return "redirect:/index";
	}
	
	//用户重置密码
	@RequestMapping("/reset")
	@ResponseBody
	public Result resetPassword(@RequestParam(required=false) String code,@RequestParam(required=false) String cellphone
			,@RequestParam(required=false) String password,Model model
			,HttpServletRequest request){
		return userService.resetPassword(code, cellphone, password);
	}
	
	//用户登出
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:/login";
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
	@ResponseBody
	public Result addShoppingCar(@RequestParam(defaultValue="0")String goodsId,@RequestParam(defaultValue="0")String amount){
		Result result=null;
		try {
			result=shoppingCarService.addGoodsToShoppingCar(Integer.parseInt(goodsId), Integer.parseInt(amount));
		} catch (Exception e) {
			result=userService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//删除购物车内的商品
	@RequestMapping("/dropGoods")
	@ResponseBody
	public Result dropShoppingCar(@RequestParam(defaultValue="0")String goodsId){
		Result result=null;
		try {
			result=shoppingCarService.removeGoodsFromShoppingCar(Integer.parseInt(goodsId));
		} catch (Exception e) {
			result=userService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//一次添加多个商品至购物车
	@RequestMapping("/addMultiGoods")
	@ResponseBody
	public Result addMultiShoppingCar(@RequestParam(required=false)String items){
		Result result=null;
		try {
			result=shoppingCarService.addMultiGoodsToShoppingCar(items);
		} catch (Exception e) {
			result=userService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//下发用户交易记录
	@RequestMapping("/account")
	public String userTradeLog(Model model){
		Result result=userTradeLogService.queryUserTradeLogs();
		System.out.println(JSONObject.fromObject(result));
		model.addAttribute("result", result);
		return "user/myAccount";
	}
	
}
