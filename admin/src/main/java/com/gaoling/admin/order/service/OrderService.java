package com.gaoling.admin.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.admin.order.dao.OrderDao;
import com.gaoling.admin.order.pojo.Order;
import com.gaoling.admin.system.service.CommonService;
import com.gaoling.admin.util.DataUtil;

@Service
public class OrderService extends CommonService{

	@Autowired
	private OrderDao orderDao;
	
	//查询订单列表
	public List<Map<String,Object>> queryOrderList(Map<Object,Object> param){
		param.put("states", Order.NORMALORDERSTATES);
		return orderDao.queryOrderList(param);
	}
	
	//查询订单详情
	public Map<String,Object> queryOrderDetail(int id){
		List<Map<String,Object>> orders=orderDao.queryOrderList(DataUtil.mapOf("id",id));
		return orders.size()>0?orders.get(0):null;
	}
	
}
