package com.gaoling.admin.goods.dao;

import org.springframework.stereotype.Repository;

import com.gaoling.admin.goods.entity.Cooperation;

@Repository
public interface CooperationDao {

	void addCooperation(Cooperation cooperation);
	
}
