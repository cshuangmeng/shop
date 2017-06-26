package com.gaoling.admin.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.admin.shop.dao.StoreDetailDao;
import com.gaoling.admin.shop.entity.StoreDetail;


@Service
public class StoreDetailService {
	
	@Autowired
	private StoreDetailDao storeDetailDao;

	//新增商户详细信息
	public int addStoreDetail(StoreDetail storeDetail){
		return storeDetailDao.addStoreDetail(storeDetail);
	}
	
	//更新商户详细信息
	public void updateStoreDetail(StoreDetail storeDetail){
		storeDetailDao.updateStoreDetail(storeDetail);
	}
	
	//通过商户ID查询商户详细信息
	public StoreDetail getStoreDetailByStoreId(int storeId){
		return storeDetailDao.getStoreDetailByStoreId(storeId);
	}
	
}
