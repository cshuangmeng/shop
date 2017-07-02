package com.gaoling.admin.goods.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaoling.admin.goods.dao.SysRoleDao;
import com.gaoling.admin.goods.entity.SysRole;
import com.gaoling.admin.util.DataUtil;
import com.gaoling.admin.util.DateUtil;


@Service
public class SysRoleService {
	
	@Autowired
	private SysRoleDao roleDao;
	@Autowired
	private SysUserService userService;

	// 新增role信息
	public void addRole(SysRole role) {
		role.setRoleId(DataUtil.buildUUID());
		role.setCreateTime(DateUtil.getCurrentTime());
		roleDao.addRole(role);
	}

	// 更新role信息
	public void updateRole(SysRole role) {
		roleDao.updateRole(role);
	}

	// 获取role详细信息
	public SysRole getRole(int rid) {
		return roleDao.getRole(rid);
	}
	
	//通过角色名称查询角色
	public SysRole getRoleByRoleName(String roleName){
		return roleDao.getRoleByRoleName(roleName);
	}
	
	// 删除角色所拥有的菜单
	public void deleteMenusOfRole(int rid,int mid){
		roleDao.deleteMenusOfRole(rid, mid);
	}
	
	// 删除角色
	@Transactional
	public void deleteRole(int rid) {
		deleteMenusOfRole(rid, 0);
		userService.delRoleForUser(0, rid);
		roleDao.deleteRole(rid);
	}
	
	// 为角色分配菜单
	public void addMenuForRole(int rid,String mids){
		deleteMenusOfRole(rid, 0);
		for(String mid:mids.split(",")){
			roleDao.addMenuForRole(rid, Integer.parseInt(mid));
		}
	}
	
	// 加载用户所拥有的角色
	public List<HashMap<String,Object>> getRolesOfUser(int uid){
		return roleDao.getRolesOfUser(uid);
	}
	
	// 加载角色列表
	public List<SysRole> getAllRoles(int enabled){
		return roleDao.getAllRoles(enabled);
	}

}
