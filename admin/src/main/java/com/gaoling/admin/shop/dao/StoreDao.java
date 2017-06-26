package com.gaoling.admin.shop.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gaoling.admin.shop.entity.StoreInfo;
import com.gaoling.admin.shop.entity.StoreQueryBean;


@Repository
public interface StoreDao {

	int addStore(StoreInfo store);
	void updateStore(StoreInfo store);
	StoreInfo getStore(int id);
	StoreInfo getStoreByStoreId(String storeId);
	List<HashMap<String,Object>> loadStoreWithQuery(StoreQueryBean query);
	StoreInfo getStoreByFullName(String fullName);
}
