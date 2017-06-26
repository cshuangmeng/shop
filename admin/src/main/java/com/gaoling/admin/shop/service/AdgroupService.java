package com.gaoling.admin.shop.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.admin.shop.dao.AdgroupDao;
import com.gaoling.admin.shop.entity.Adgroup;
import com.gaoling.admin.util.DateUtil;


@Service
public class AdgroupService {
	
	@Autowired
	private AdgroupDao adgroupDao;
	@Autowired
	private AdcreativeService adcreativeService;
	
	//新增广告组
	public int addAdgroup(Adgroup adgroup){
		adgroup.setCreateTime(DateUtil.getCurrentTime());
		return adgroupDao.addAdgroup(adgroup);
	}
	
	//更新广告组
	public void updateAdgroup(Adgroup adgroup){
		adgroup.setUpdateTime(DateUtil.getCurrentTime());
		adgroupDao.updateAdgroup(adgroup);
	}
	
	//删除广告组
	public void deleteAdgroup(int id){
		adcreativeService.deleteAdcreativeByGroupId(id);
		adgroupDao.deleteAdgroup(id);
	}
	
	//加载广告组
	public HashMap<String,Object> getAdgroup(int id)throws Exception{
		return adgroupDao.getAdgroup(id);
	}
	
	//更新广告组状态
	public void updateAdgroupStatus(@Param("id")int id,@Param("status")int status){
		adgroupDao.updateAdgroupStatus(id, status);
	}
	
	//查询广告组列表
	public List<Adgroup> queryAdgroup(Adgroup adgroup){
		return adgroupDao.queryAdgroup(adgroup);
	}
	
}
