package com.gaoling.shop.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.shop.order.pojo.Order;

@Repository
public interface OrderDao {

	Order getOrder(@Param("id")int id,@Param("lock")boolean lock);
	List<Order> queryOrders(@Param("param")Map<Object,Object> param);
	List<Map<String,Object>> queryOrderList(@Param("userId")int userId,@Param("state")int state);
	void addOrder(Order order);
	void updateOrder(Order order);
	
}
