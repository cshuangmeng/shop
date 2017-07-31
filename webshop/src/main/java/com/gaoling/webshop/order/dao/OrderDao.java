package com.gaoling.webshop.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.webshop.order.pojo.Order;

@Repository
public interface OrderDao {

	Order getOrder(@Param("id")int id,@Param("lock")boolean lock);
	List<Order> queryOrders(@Param("param")Map<Object,Object> param);
	int queryOrderCount(@Param("param")Map<Object,Object> param);
	List<Map<String,Object>> queryOrderList(@Param("param")Map<Object,Object> param);
	void addOrder(Order order);
	void updateOrder(Order order);
	
}
