package com.gaoling.webshop.order.action;

import javax.servlet.http.HttpServletRequest;

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
import com.gaoling.webshop.common.DataUtil;
import com.gaoling.webshop.order.pojo.Order;
import com.gaoling.webshop.order.service.OrderService;
import com.gaoling.webshop.system.pojo.Result;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/order")
@CrossOrigin(methods = RequestMethod.POST, origins = AppConstant.TRUST_CROSS_ORIGINS)
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	//用户下单
	@RequestMapping("/new")
	@ResponseBody
	public Result newOrder(@RequestParam(required=false)String itemIds,@ModelAttribute Order param,HttpServletRequest request){
		Result result=null;
		try {
			result=orderService.newOrder(itemIds, param, DataUtil.getIpAddr(request));
		} catch (Exception e) {
			result=orderService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//订单确认
	@RequestMapping("/confirm")
	public String confirmOrder(@RequestParam(required=false)String itemIds,Model model){
		Result result=orderService.confirmOrder(itemIds);
		System.out.println(JSONObject.fromObject(result));
		model.addAttribute("result", result);
		return "order/orderConfirm";
	}
	
	//订单详情
	@RequestMapping("/info")
	public Result orderDetail(@RequestParam(defaultValue="0")String orderId){
		Result result=null;
		try {
			result=orderService.queryOrderDetail(Integer.parseInt(orderId));
		} catch (Exception e) {
			result=orderService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	//订单列表
	@RequestMapping("/list")
	public String orderList(@RequestParam(defaultValue="-1")String state,@RequestParam(defaultValue="1")int page
			,Model model){
		Result result=orderService.queryOrderList(Integer.parseInt(state),page);
		System.out.println(JSONObject.fromObject(result));
		model.addAttribute("result", result);
		return "user/orderList";
	}
	
	//订单再支付
	@RequestMapping("/payWay")
	public String selectPayWay(@RequestParam(defaultValue="0")String orderId,HttpServletRequest request,Model model)throws Exception{
		Result result=orderService.orderPay(Integer.parseInt(orderId), DataUtil.getIpAddr(request));
		model.addAttribute("result", result);
		System.out.println(JSONObject.fromObject(result));
		return "order/payWay";
	}
	
	//删除订单
	@RequestMapping("/delete")
	@ResponseBody
	public Result deleteOrder(@RequestParam(defaultValue="0")String orderId)throws Exception{
		Result result=null;
		try {
			result=orderService.deleteOrder(Integer.parseInt(orderId));
		} catch (Exception e) {
			result=orderService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
}
