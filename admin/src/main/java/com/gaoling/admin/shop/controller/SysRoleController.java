package com.gaoling.admin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaoling.admin.shop.entity.JsonResult;
import com.gaoling.admin.shop.entity.SysRole;
import com.gaoling.admin.shop.service.SysRoleService;

@Controller
public class SysRoleController {
	
	@Autowired
	private SysRoleService roleService;

	// 进入角色界面
	@RequestMapping("/auth/role")
	public String index() {
		return "auth/role";
	}
	
	// 删除角色信息
	@ResponseBody
	@RequestMapping("/auth/role/del")
	public JsonResult deleteRole(@RequestParam int rid) {
		JsonResult json = new JsonResult();
		try {
			roleService.deleteRole(rid);
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}

	// 加载指定角色信息
	@ResponseBody
	@RequestMapping("/auth/role/info")
	public JsonResult roleDetail(@RequestParam int rid) {
		JsonResult json = new JsonResult();
		try {
			json.setData(roleService.getRole(rid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	// 加载所有角色列表
	@ResponseBody
	@RequestMapping("/auth/role/list")
	public JsonResult getAllRoles(@RequestParam(defaultValue="-1")int enabled) {
		JsonResult json = new JsonResult();
		try {
			json.setData(roleService.getAllRoles(enabled));
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
	// 为角色分配菜单
	@ResponseBody
	@RequestMapping("/auth/role/menus")
	public JsonResult addMenuForRole(@RequestParam int rid,@RequestParam String mids) {
		JsonResult json = new JsonResult();
		try {
			roleService.addMenuForRole(rid, mids);
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}

	// 进入编辑角色页面
	@RequestMapping("/auth/role/update")
	@ResponseBody
	public JsonResult updateRole(@ModelAttribute SysRole role) {
		JsonResult json = new JsonResult();
		try {
			SysRole r = roleService.getRoleByRoleName(role.getRoleName());
			if (role.getId() > 0) {
				if(null==r||role.getId()==r.getId()){
					roleService.updateRole(role);
				}else{
					json.setResult(2);
				}
			} else if(null==r){
				roleService.addRole(role);
			}else{
				json.setResult(2);
			}
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
}
