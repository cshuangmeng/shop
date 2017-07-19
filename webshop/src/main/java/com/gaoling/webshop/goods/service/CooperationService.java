package com.gaoling.webshop.goods.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.webshop.common.DateUtil;
import com.gaoling.webshop.goods.dao.CooperationDao;
import com.gaoling.webshop.goods.pojo.Cooperation;
import com.gaoling.webshop.system.pojo.Result;
import com.gaoling.webshop.system.service.CommonService;

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
