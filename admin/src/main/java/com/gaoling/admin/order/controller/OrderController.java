package com.gaoling.admin.order.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaoling.admin.order.service.OrderService;
import com.gaoling.admin.system.pojo.Result;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	//进入商品相关页面
	@RequestMapping("/{page}")
	public String index(@PathVariable String page){
		return "/order/"+page;
	}
	
	//订单详情
	@RequestMapping("/detail")
	public String orderDetail(@RequestParam(defaultValue="0")String orderId,Model model){
		model.addAttribute("order", orderService.queryOrderDetail(Integer.parseInt(orderId)));
		return "order/detail";
	}
	
	//订单列表
	@RequestMapping("/list")
	@ResponseBody
	public Result orderList(@RequestParam Map<Object,Object> param){
		return orderService.putResult(orderService.queryOrderList(param));
	}
	
}
