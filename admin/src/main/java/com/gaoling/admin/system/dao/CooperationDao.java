package com.gaoling.admin.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.admin.system.pojo.Cooperation;

@Repository
public interface CooperationDao {

	List<Cooperation> queryCooperations(@Param("param")Map<String,Object> param);
	
}
