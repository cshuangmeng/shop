package com.gaoling.shop.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.shop.user.pojo.Area;

@Repository
public interface AreaDao {

	List<Area> queryAreas(@Param("param")Map<Object,Object> param);
	
}
