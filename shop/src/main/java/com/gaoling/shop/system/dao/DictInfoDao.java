package com.gaoling.shop.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface DictInfoDao {

	String queryDictValue(String name);
	List<Map<String,Object>> queryDicts(Map<String,Object> param);
	
}
