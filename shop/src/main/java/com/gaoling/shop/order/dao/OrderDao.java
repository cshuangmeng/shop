package com.gaoling.shop.order.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gaoling.shop.user.pojo.Address;

@Repository
public interface OrderDao {

	List<Address> queryAllOrder();
	
}
