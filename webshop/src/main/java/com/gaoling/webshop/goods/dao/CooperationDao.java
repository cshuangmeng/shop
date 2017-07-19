package com.gaoling.webshop.goods.dao;

import org.springframework.stereotype.Repository;

import com.gaoling.webshop.goods.pojo.Cooperation;

@Repository
public interface CooperationDao {

	void addCooperation(Cooperation cooperation);
	
}
