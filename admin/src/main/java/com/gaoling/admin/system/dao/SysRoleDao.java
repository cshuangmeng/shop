package com.gaoling.admin.system.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.admin.system.pojo.SysRole;

@Repository
public interface SysRoleDao {
	
	int addRole(SysRole role);
	void updateRole(SysRole role);
	void deleteRole(int id);
	SysRole getRole(int id);
	SysRole getRoleByRoleName(String roleName);
	void addMenuForRole(@Param("rid")int rid,@Param("mid")int mid);
	void deleteMenusOfRole(@Param("rid")int rid,@Param("mid")int mid);
	List<HashMap<String,Object>> getRolesOfUser(int uid);
	List<SysRole> getAllRoles(@Param("enabled")int enabled);
		
}
