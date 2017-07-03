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
	void addRoleForUser(@Param("uid")int uid,@Param("rid")int rid);
	void deleteUser(int id);
	void delRoleForUser(@Param("uid")int uid,@Param("rid")int rid);
	SysUser getUser(int id);
	SysUser getUserByUsername(String username);
	List<HashMap<String,Object>> loadAllUsers();
	List<HashMap<String,Object>> loadMenusOfUser(int uid);
	List<HashMap<String,Object>> getUsersOfRole(int rid);
	
}
