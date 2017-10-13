package com.gaoling.admin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaoling.admin.system.pojo.Result;
import com.gaoling.admin.system.pojo.SysRole;
import com.gaoling.admin.system.service.SysRoleService;
import com.gaoling.admin.util.AppConstant;

@Controller
@RequestMapping("/auth/role")
public class SysRoleController {
	
	@Autowired
	private SysRoleService sysRoleService;

	// 进入角色界面
	@RequestMapping({"","/"})
	public String index() {
		return "/auth/role";
	}
	
	// 删除角色信息
	@ResponseBody
	@RequestMapping("/del")
	public Result deleteRole(@RequestParam int rid) {
		Result result;
		try {
			sysRoleService.deleteRole(rid);
			result=sysRoleService.putResult();
		} catch (Exception e) {
			result=sysRoleService.putResult(AppConstant.OPERATE_FAILURE);
			e.printStackTrace();
		}
		return result;
	}

	// 加载指定角色信息
	@ResponseBody
	@RequestMapping("/info")
	public Result roleDetail(@RequestParam int rid) {
		Result result;
		try {
			result=sysRoleService.putResult(sysRoleService.getRole(rid));
		} catch (Exception e) {
			result=sysRoleService.putResult(AppConstant.OPERATE_FAILURE);
			e.printStackTrace();
		}
		return result;
	}
	
	// 加载所有角色列表
	@ResponseBody
	@RequestMapping("/list")
	public Result getAllRoles(@RequestParam(defaultValue="-1")int enabled) {
		Result result;
		try {
			result=sysRoleService.putResult(sysRoleService.getAllRoles());
		} catch (Exception e) {
			result=sysRoleService.putResult(AppConstant.OPERATE_FAILURE);
			e.printStackTrace();
		}
		return result;
	}
	
	// 为角色分配菜单
	@ResponseBody
	@RequestMapping("/menus")
	public Result addMenuForRole(@RequestParam int rid,@RequestParam String mids) {
		Result result;
		try {
			sysRoleService.addMenuForRole(rid, mids);
			result=sysRoleService.putResult();
		} catch (Exception e) {
			result=sysRoleService.putResult(AppConstant.OPERATE_FAILURE);
			e.printStackTrace();
		}
		return result;
	}

	// 进入编辑角色页面
	@RequestMapping("/update")
	@ResponseBody
	public Result updateRole(@ModelAttribute SysRole role) {
		Result result;
		try {
			SysRole r = sysRoleService.getRoleByRoleName(role.getName());
			if (role.getId() > 0) {
				if(null==r||role.getId()==r.getId()){
					sysRoleService.updateRole(role);
				}
			} else if(null==r){
				sysRoleService.addRole(role);
			}
			result=sysRoleService.putResult();
		} catch (Exception e) {
			result=sysRoleService.putResult(AppConstant.OPERATE_FAILURE);
			e.printStackTrace();
		}
		return result;
	}
}
