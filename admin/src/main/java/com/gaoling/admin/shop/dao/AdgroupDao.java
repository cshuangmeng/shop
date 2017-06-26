package com.gaoling.admin.shop.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.admin.shop.entity.Adgroup;


@Repository
public interface AdgroupDao {

	int addAdgroup(Adgroup adgroup);
	void updateAdgroup(Adgroup adgroup);
	void deleteAdgroup(int id);
	HashMap<String,Object> getAdgroup(int id);
	void updateAdgroupStatus(@Param("id")int id,@Param("status")int status);
	List<Adgroup> queryAdgroup(Adgroup adgroup);
	
}
