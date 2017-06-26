package com.gaoling.admin.shop.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gaoling.admin.shop.entity.Adcreative;


@Repository
public interface AdcreativeDao {

	int addAdcreative(Adcreative adcreative);
	void updateAdcreative(Adcreative adcreative);
	void deleteAdcreative(int id);
	void deleteAdcreativeByGroupId(int groupId);
	HashMap<String,Object> getAdcreative(int id);
	List<HashMap<String,Object>> loadAdcreativeByGroupId(int groupId);
	
}
