package com.kcx.support.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kcx.support.common.DataUtil;
import com.kcx.support.dao.UserMapper;
import com.kcx.support.pojo.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	//查询用户
	public List<User> queryUsers(Map<Object,Object> param){
		return userMapper.queryUsers(param);
	}
	
	//查询用户
	public User getUser(int id){
		List<User> users=queryUsers(DataUtil.mapOf("id",id));
		return users.size()>0?users.get(0):null;
	}
	
}
