package com.gaoling.admin.shop.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.admin.shop.dao.AreaDao;
import com.gaoling.admin.shop.entity.AreaInfo;


@Service
public class AreaService {
	
	@Autowired
	private AreaDao areaDao;

	//读取地市列表
	public List<HashMap<String,Object>> loadCityList(){
		return areaDao.loadCityList();
	}
	
	//读取行政区划列表
	public List<HashMap<String,Object>> loadRegionList(int cityCode){
		return areaDao.loadRegionList(cityCode);
	}
	
	//获取地区信息
	public AreaInfo getAreaByAreacode(int areacode){
		return areaDao.getAreaByAreacode(areacode);
	}
	
	//依据区域名称获取地区信息
	public AreaInfo getAreaByRegion(String region){
		return areaDao.getAreaByRegion(region);
	}
	
}
