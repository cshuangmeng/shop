package com.gaoling.admin.goods.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.admin.goods.dao.CooperationDao;
import com.gaoling.admin.goods.pojo.Cooperation;
import com.gaoling.admin.system.pojo.Result;
import com.gaoling.admin.system.service.CommonService;
import com.gaoling.admin.util.DateUtil;

@Service
public class CooperationService extends CommonService{

	@Autowired
	private CooperationDao cooperationDao;
	
	//企业申请合作
	public Result addCooperation(Cooperation cooperation){
		cooperation.setCreateTime(DateUtil.nowDate());
		cooperationDao.addCooperation(cooperation);
		return putResult();
	}
	
}
