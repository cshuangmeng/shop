package com.gaoling.shop.goods.dao;

import org.springframework.stereotype.Repository;

import com.gaoling.shop.goods.pojo.Cooperation;

@Repository
public interface CooperationDao {

	void addCooperation(Cooperation cooperation);
	
}
