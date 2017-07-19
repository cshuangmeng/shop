package com.gaoling.webshop.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.webshop.user.pojo.User;

@Repository
public interface UserDao {

	User getUser(@Param("id")int id,@Param("lock")boolean lock);
	List<User> queryUsers(@Param("param")Map<Object,Object> param);
	void addUser(User user);
	void updateUser(User user);
	
}
