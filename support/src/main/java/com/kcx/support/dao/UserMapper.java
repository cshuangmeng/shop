package com.kcx.support.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kcx.support.pojo.User;

@Repository
public interface UserMapper {

	List<User> queryUsers(@Param("param")Map<Object,Object> param);
	
}
