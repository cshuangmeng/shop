package com.gaoling.admin.shop.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.admin.shop.entity.DictInfo;


@Repository
public interface DictInfoDao {

	int addDict(DictInfo dict);
	void updateDict(DictInfo dict);
	void deleteDict(int id);
	DictInfo getDict(int id);
	DictInfo getDictByName(@Param("dictCode")String dictCode,@Param("dictName")String dictName);
	List<DictInfo> loadAllDicts();
	List<HashMap<String,Object>> loadDicts(String dictCode);
	String getDictName(@Param("dictCode")String dictCode,@Param("dictValue")String dictValue);
	
}
