package com.gaoling.admin.shop.dao;

import org.springframework.stereotype.Repository;

import com.gaoling.admin.shop.entity.StoreDetail;


@Repository
public interface StoreDetailDao {

	int addStoreDetail(StoreDetail storeDetail);
	void updateStoreDetail(StoreDetail storeDetail);
	StoreDetail getStoreDetailByStoreId(int storeId);
	
	
}
