package com.gaoling.shop.tribe.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.shop.tribe.pojo.Tribe;

@Repository
public interface TribeDao {

	List<Tribe> queryTribes(@Param("param")Map<Object,Object> param);
	void addTribe(Tribe tribe);
	void updateTribe(Tribe tribe);
	
	
}
