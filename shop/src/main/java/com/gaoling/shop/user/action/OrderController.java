package com.gaoling.shop.user.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaoling.shop.user.pojo.Order;
import com.gaoling.shop.user.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@RequestMapping("/index")
	public List<Order> index(){
		return orderService.queryAllOrder();
	}
	
}
