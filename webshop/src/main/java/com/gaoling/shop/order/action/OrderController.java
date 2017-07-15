package com.gaoling.shop.order.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.order.pojo.Order;
import com.gaoling.shop.order.service.OrderService;
import com.gaoling.shop.system.pojo.Result;

@RestController
@RequestMapping("/order")
@CrossOrigin(methods = RequestMethod.POST, origins = AppConstant.TRUST_CROSS_ORIGINS)
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	//用户下单
	@RequestMapping("/new")
	public Result newOrder(@RequestParam(required=false)String uuid,@RequestParam(required=false)String itemIds
			,@ModelAttribute Order param,HttpServletRequest request){
		Result result=null;
		try {
			result=orderService.newOrder(itemIds, uuid, param, DataUtil.getIpAddr(request));
		} catch (Exception e) {
			result=orderService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//订单确认
	@RequestMapping("/confirm")
	public Result confirmOrder(@RequestParam(required=false)String uuid,@RequestParam(required=false)String itemIds){
		Result result=null;
		try {
			result=orderService.confirmOrder(itemIds, uuid);
		} catch (Exception e) {
			result=orderService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//订单详情
	@RequestMapping("/info")
	public Result orderDetail(@RequestParam(required=false)String uuid,@RequestParam(defaultValue="0")String orderId){
		Result result=null;
		try {
			result=orderService.queryOrderDetail(uuid, Integer.parseInt(orderId));
		} catch (Exception e) {
			result=orderService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//订单列表
	@RequestMapping("/list")
	public Result orderList(@RequestParam(required=false)String uuid,@RequestParam(defaultValue="-1")String state){
		Result result=null;
		try {
			result=orderService.queryOrderList(uuid, Integer.parseInt(state));
		} catch (Exception e) {
			result=orderService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//订单再支付
	@RequestMapping("/pay")
	public Result orderPay(@RequestParam(required=false)String uuid,@RequestParam(defaultValue="0")String orderId,HttpServletRequest request)throws Exception{
		Result result=null;
		try {
			result=orderService.orderPay(uuid, Integer.parseInt(orderId), DataUtil.getIpAddr(request));
		} catch (Exception e) {
			result=orderService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//删除订单
	@RequestMapping("/delete")
	public Result deleteOrder(@RequestParam(required=false)String uuid,@RequestParam(defaultValue="0")String orderId)throws Exception{
		Result result=null;
		try {
			result=orderService.deleteOrder(uuid, Integer.parseInt(orderId));
		} catch (Exception e) {
			result=orderService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
}
