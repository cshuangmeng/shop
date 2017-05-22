package com.gaoling.shop.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.shop.user.dao.OrderDao;
import com.gaoling.shop.user.pojo.Order;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	
	//查询所有订单
	public List<Order> queryAllOrder(){
		return orderDao.queryAllOrder();
	}
	
}
