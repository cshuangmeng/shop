package com.gaoling.shop.system.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DictInfoDao {

	String queryDictValue(String name);
	List<Map<String,Object>> queryDicts(@Param("param")Map<String,Object> param);
	int updateDictValue(@Param("set")Map<String,Object> set,@Param("param")Map<String,Object> param);
	int deleteDict(@Param("name")String name,@Param("parentId")Integer parentId);
	int insertDictValue(@Param("name")String name,@Param("value")String value,@Param("parentId")int parentId
			,@Param("createTime")Date createTime,@Param("state")Integer state,@Param("remark")String remark
			,@Param("orderIndex")int orderIndex);
	
}
