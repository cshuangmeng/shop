package com.gaoling.admin.system.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaoling.admin.system.dao.SysRoleDao;
import com.gaoling.admin.system.pojo.SysRole;


@Service
public class SysRoleService extends CommonService{
	
	@Autowired
	private SysRoleDao sysRoleDao;

	// 新增role信息
	public void addRole(SysRole role) {
		sysRoleDao.addRole(role);
	}

	// 更新role信息
	public void updateRole(SysRole role) {
		sysRoleDao.updateRole(role);
	}

	// 获取role详细信息
	public SysRole getRole(int rid) {
		return sysRoleDao.getRole(rid);
	}
	
	//通过角色名称查询角色
	public SysRole getRoleByRoleName(String roleName){
		return sysRoleDao.getRoleByRoleName(roleName);
	}
	
	// 删除角色所拥有的菜单
	public void deleteMenusOfRole(int rid,int mid){
		sysRoleDao.deleteMenusOfRole(rid, mid);
	}
	
	// 删除角色
	@Transactional
	public void deleteRole(int rid) {
		SysRole role=getRole(rid);
		role.setState(SysRole.ROLE_STATE_ENUM.DELETED.getState());
		sysRoleDao.updateRole(role);
	}
	
	// 为角色分配菜单
	public void addMenuForRole(int rid,String mids){
		deleteMenusOfRole(rid, 0);
		for(String mid:mids.split(",")){
			sysRoleDao.addMenuForRole(rid, Integer.parseInt(mid));
		}
	}
	
	// 加载用户所拥有的角色
	public List<HashMap<String,Object>> getRolesOfUser(int uid){
		return sysRoleDao.getRolesOfUser(uid);
	}
	
	// 加载角色列表
	public List<SysRole> getAllRoles(){
		return sysRoleDao.getAllRoles(SysRole.SHOWSTATES);
	}

}
