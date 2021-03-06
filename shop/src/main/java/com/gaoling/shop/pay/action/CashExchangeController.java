package com.gaoling.shop.pay.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.pay.service.CashExchangeLogService;
import com.gaoling.shop.system.pojo.Result;

@Controller
@RequestMapping("/cash")
@CrossOrigin(methods = RequestMethod.POST, origins = AppConstant.TRUST_CROSS_ORIGINS)
public class CashExchangeController {

	@Autowired
	private CashExchangeLogService cashExchangeLogService;
	
	//我的部落币详情
	@ResponseBody
	@RequestMapping("/myCoin")
	public Result orderDetail(@RequestParam(required=false)String uuid){
		Result result=null;
		try {
			result=cashExchangeLogService.getMyCoinInfo(uuid);
		} catch (Exception e) {
			result=cashExchangeLogService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//部落币提现
	@ResponseBody
	@RequestMapping("/exchange")
	public Result coinCashExchange(@RequestParam(required=false)String uuid,@RequestParam(defaultValue="0")Integer coin){
		Result result=null;
		try {
			result=cashExchangeLogService.userOperateCashExchange(uuid, coin);
		} catch (Exception e) {
			result=cashExchangeLogService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//加载部落币提现记录
	@ResponseBody
	@RequestMapping("/exchangeHistory")
	public Result exchangeHistory(@RequestParam(required=false)String uuid){
		Result result=null;
		try {
			result=cashExchangeLogService.queryCashExchangeHistory(uuid);
		} catch (Exception e) {
			result=cashExchangeLogService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//定时执行用户提现申请
	@ResponseBody
	@RequestMapping("/doExchange")
	public Result executeCashExchangeOntime(@RequestParam(required=false)String uuid){
		Result result=null;
		try {
			result=cashExchangeLogService.executeCashExchangeOntime();
		} catch (Exception e) {
			result=cashExchangeLogService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
}
