package com.gaoling.admin.goods.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.admin.goods.entity.SysMenu;


@Repository
public interface SysMenuDao {

	int addMenu(SysMenu menu);
	void deleteMenu(int id);
	SysMenu getMenu(int id);
	SysMenu getMenuByName(String menuName);
	void updateMenu(SysMenu menu);
	List<SysMenu> getMenus(@Param("parentId")int parentId);
	List<HashMap<String,Object>> loadAllMenus();
	List<HashMap<String,Object>> getMenusOfRole(int rid);
	
}
