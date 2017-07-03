package com.gaoling.admin.system.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.admin.system.pojo.SysUser;


@Repository
public interface SysUserDao {
	
	int addUser(SysUser user);
	void updateUser(SysUser user);
	void addRoleForUser(@Param("userId")int userId,@Param("roleId")int roleId);
	void delRoleForUser(@Param("userId")int userId,@Param("roleId")int roleId);
	SysUser getUser(int id);
	SysUser getUserByUsername(String username);
	List<HashMap<String,Object>> loadAllUsers();
	List<HashMap<String,Object>> loadMenusOfUser(int userId);
	List<HashMap<String,Object>> getUsersOfRole(int roleId);
	
}
