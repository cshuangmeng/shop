package com.gaoling.shop.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gaoling.shop.user.pojo.Order;

@Repository
public interface OrderDao {

	List<Order> queryAllOrder();
	
}
