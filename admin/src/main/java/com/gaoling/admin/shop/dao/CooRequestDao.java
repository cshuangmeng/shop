package com.gaoling.admin.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.admin.shop.entity.CooRequest;


@Repository
public interface CooRequestDao {

	int getCooRequestSize(@Param("fullname")String fullname);
	List<CooRequest> loadCooRequestList(@Param("fullname")String fullname
			,@Param("offset") int offset, @Param("limit") int limit);

}
