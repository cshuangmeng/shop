package com.gaoling.admin.shop.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.admin.shop.dao.BdDao;
import com.gaoling.admin.shop.entity.BdInfo;
import com.gaoling.admin.util.DataUtil;
import com.gaoling.admin.util.DateUtil;


@Service
public class BdService {
	
	@Autowired
	private BdDao bdDao;

	//加载所有BD人员信息
	public List<HashMap<String,Object>> loadAllBdFullName(){
		return bdDao.loadAllBdFullName();
	}
	
	//新增BD信息
	public int addBd(BdInfo bd){
		bd.setBdId(DataUtil.buildUUID());
		bd.setCreateTime(DateUtil.getCurrentTime());
		return bdDao.addBd(bd);
	}
	
	//更新BD信息
	public void updateBd(BdInfo bd){
		bdDao.updateBd(bd);
	}
	
	//获取BD详细信息
	public BdInfo getBd(int id){
		return bdDao.getBd(id);
	}
	
	//获取所有BD详细信息
	public List<BdInfo> loadAllBd(){
		return bdDao.loadAllBd();
	}
	
	//通过手机号码获取BD详细信息
	public BdInfo loadBdByUsername(String username){
		return bdDao.loadBdByUsername(username);
	}
	
	//通过fullname 获取BD详细信息
	public BdInfo loadBdByFullName(String fullName){
		return bdDao.getBdInfo(fullName);
	}
	
}
