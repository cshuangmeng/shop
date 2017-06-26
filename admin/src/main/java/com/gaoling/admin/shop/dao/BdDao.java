package com.gaoling.admin.shop.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gaoling.admin.shop.entity.BdInfo;


@Repository
public interface BdDao {

	int addBd(BdInfo bd);
	void updateBd(BdInfo bd);
	BdInfo getBd(int id);
	List<BdInfo> loadAllBd();
	List<HashMap<String,Object>> loadAllBdFullName();
	BdInfo loadBdByUsername(String username);
	BdInfo getBdInfo(String fullname);
	
}
