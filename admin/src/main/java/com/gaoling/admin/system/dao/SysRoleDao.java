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
	SysRole getRole(int id);
	SysRole getRoleByRoleName(String name);
	void addMenuForRole(@Param("roleId")int roleId,@Param("menuId")int menuId);
	void deleteMenusOfRole(@Param("roleId")int roleId,@Param("menuId")int menuId);
	List<HashMap<String,Object>> getRolesOfUser(int userId);
	List<SysRole> getAllRoles(@Param("states")List<Integer> states);
		
}
