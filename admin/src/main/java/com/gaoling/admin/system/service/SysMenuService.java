package com.gaoling.admin.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaoling.admin.system.dao.SysMenuDao;
import com.gaoling.admin.system.pojo.SysMenu;
import com.gaoling.admin.util.DataUtil;
import com.gaoling.admin.util.DateUtil;


@Service
public class SysMenuService extends CommonService {
	
	@Autowired
	private SysMenuDao sysMenuDao;

	//增加权限
	@Transactional
	public int addMenu(SysMenu menu){
		menu.setCreateTime(DateUtil.nowDate());
		return sysMenuDao.addMenu(menu);
	}
	
	//更新权限
	public void updateMenu(SysMenu menu){
		sysMenuDao.updateMenu(menu);
	}
	
	//删除权限
	@Transactional
	public void deleteMenu(int mid){
		SysMenu menu=getMenu(mid);
		menu.setState(SysMenu.MENU_STATE_ENUM.DELETED.getState());
		sysMenuDao.updateMenu(menu);
	}
	
	//查询指定权限
	public SysMenu getMenu(int id){
		return sysMenuDao.getMenu(id);
	}
	
	//查询所有权限
	public List<SysMenu> getMenus(int parentId){
		return sysMenuDao.getMenus(parentId);
	}
	
	//依据菜单名称获取菜单信息
	public SysMenu getMenuByName(String menuName){
		return sysMenuDao.getMenuByName(menuName);
	}
	
	//查询角色所拥有的菜单
	public List<HashMap<String,Object>> getMenusOfRole(int rid){
		return sysMenuDao.getMenusOfRole(rid);
	}
	
	//加载所有菜单
	public List<HashMap<String,Object>> loadAllMenus(){
		return sysMenuDao.loadAllMenus();
	}
	
	//拼装菜单对象
	public List<HashMap<String,Object>> loadMenusOfRole(int rid){
		List<HashMap<String,Object>> resultMap=new ArrayList<HashMap<String,Object>>();
		List<SysMenu> parentMenus=getMenus(0);
		HashMap<String,Object> parentMap=null;
		HashMap<String,Object> sonMap=null;
		List<HashMap<String,Object>> roleMenus=getMenusOfRole(rid);
		for(SysMenu parentMenu:parentMenus){
			parentMap=new HashMap<String,Object>();
			parentMap.put("id", parentMenu.getId());
			parentMap.put("text", parentMenu.getName());
			List<SysMenu> songMenus=getMenus(parentMenu.getId());
			List<HashMap<String,Object>> songMapList=new ArrayList<HashMap<String,Object>>();
			for(SysMenu songMenu:songMenus){
				sonMap=new HashMap<String,Object>();
				sonMap.put("id", songMenu.getId());
				sonMap.put("text", songMenu.getName());
				sonMap.put("attributes", DataUtil.saveObjectToMap("url", songMenu.getUrl()));
				//判断菜单的选中状态
				for(HashMap<String,Object> roleMenu:roleMenus){
					if(songMenu.getId()==Integer.parseInt(roleMenu.get("id").toString())){
						sonMap.put("checked", true);
					}
				}
				songMapList.add(sonMap);
			}
			parentMap.put("children", songMapList);
			resultMap.add(parentMap);
		}
		return resultMap;
	}
	
}
