package com.gaoling.shop.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.shop.user.dao.UserDao;

@Service
public class OrderService {

	@Autowired
	private UserDao orderDao;

	
}
