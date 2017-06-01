package com.gaoling.shop.goods.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.shop.common.DateUtil;
import com.gaoling.shop.goods.dao.CooperationDao;
import com.gaoling.shop.goods.pojo.Cooperation;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;

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
