package com.gaoling.admin.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.admin.system.dao.CooperationDao;
import com.gaoling.admin.system.pojo.Cooperation;

@Service
public class CooperationService extends CommonService{

	@Autowired
	private CooperationDao cooperationDao;
	
	//查询企业申请合作
	public List<Cooperation> queryCooperations(Map<String,Object> param){
		return cooperationDao.queryCooperations(param);
	}
	
}
