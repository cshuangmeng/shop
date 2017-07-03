package com.gaoling.admin.system.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.admin.system.pojo.SysMenu;


@Repository
public interface SysMenuDao {

	int addMenu(SysMenu menu);
	SysMenu getMenu(int id);
	SysMenu getMenuByName(String name);
	void updateMenu(SysMenu menu);
	List<SysMenu> getMenus(@Param("parentId")int parentId);
	List<HashMap<String,Object>> loadAllMenus();
	List<HashMap<String,Object>> getMenusOfRole(int roleId);
	
}
