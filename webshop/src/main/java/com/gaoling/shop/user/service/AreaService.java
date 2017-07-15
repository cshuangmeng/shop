package com.gaoling.shop.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;
import com.gaoling.shop.user.dao.AreaDao;
import com.gaoling.shop.user.pojo.Area;

@Service
public class AreaService extends CommonService{

	@Autowired
	private AreaDao areaDao;
	
	//查询子级地区
	public Result queryCildAreas(int parentId){
		return putResult(queryAreas(DataUtil.mapOf("parentId",parentId)));
	}
	
	//按条件查询地区
	public List<Area> queryAreas(Map<Object,Object> param){
		return areaDao.queryAreas(param);
	}
	
}
