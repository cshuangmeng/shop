package com.gaoling.webshop.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DictInfoDao {

	String queryDictValue(String name);
	List<Map<String,Object>> queryDicts(@Param("param")Map<String,Object> param);
	
}
