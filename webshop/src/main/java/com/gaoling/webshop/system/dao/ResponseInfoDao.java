package com.gaoling.webshop.system.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseInfoDao {

	String queryResponseTextCn(@Param("className")String className,@Param("code")int code);
	
}
