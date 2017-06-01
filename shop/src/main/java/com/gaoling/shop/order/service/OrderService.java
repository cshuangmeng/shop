package com.gaoling.shop.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.shop.order.dao.OrderDao;
import com.gaoling.shop.order.pojo.Order;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;

@Service
public class OrderService extends CommonService{

	@Autowired
	private OrderDao orderDao;
	
	//用户下单
	public Result newOrder(){
		
	}

	//查询订单信息
	public List<Order> queryOrders(Map<Object,Object> param){
		return orderDao.queryOrders(param);
	}
	
	//保存订单信息
	public void addOrder(Order order){
		orderDao.addOrder(order);
	}
	
	//修改订单信息
	public void updateOrder(Order order){
		orderDao.updateOrder(order);
	}
	
}
