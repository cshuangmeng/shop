package com.gaoling.admin.shop.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gaoling.admin.shop.entity.AreaInfo;


@Repository
public interface AreaDao {

	List<HashMap<String,Object>> loadCityList();
	List<HashMap<String,Object>> loadRegionList(int cityCode);
	AreaInfo getAreaByAreacode(int areacode);
	AreaInfo getAreaByRegion(String region);
	
}
