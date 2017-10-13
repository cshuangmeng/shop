package com.gaoling.admin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaoling.admin.system.pojo.Result;
import com.gaoling.admin.system.pojo.SysMenu;
import com.gaoling.admin.system.service.SysMenuService;
import com.gaoling.admin.util.AppConstant;

@Controller
@RequestMapping("/auth/menu")
public class SysMenuController {
	
	@Autowired
	private SysMenuService sysMenuService;

	// 进入菜单管理首页
	@RequestMapping("")
	public String index() {
		return "/auth/menu";
	}
	
	//加载所有菜单
	@ResponseBody
	@RequestMapping("/list")
	public Result loadAllMenus() {
		Result result;
		try {
			result=sysMenuService.putResult(sysMenuService.loadAllMenus());
		} catch (Exception e) {
			result=sysMenuService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}

	// 增加或更新菜单
	@ResponseBody
	@RequestMapping("/update")
	public Result updateMenu(@ModelAttribute SysMenu menu) {
		Result result;
		try {
			SysMenu m=sysMenuService.getMenuByName(menu.getName());
			if (menu.getId() > 0) {
				if(null==m||m.getId()==menu.getId()){
					sysMenuService.updateMenu(menu);
				}
			} else if(null==m){
				sysMenuService.addMenu(menu);
			}
			result=sysMenuService.putResult();
		} catch (Exception e) {
			result=sysMenuService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}

	// 删除菜单
	@ResponseBody
	@RequestMapping("/del")
	public Result deleteMenu(@RequestParam int mid) {
		Result result;
		try {
			sysMenuService.deleteMenu(mid);
			result=sysMenuService.putResult();
		} catch (Exception e) {
			result=sysMenuService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}

	// 加载指定菜单信息
	@ResponseBody
	@RequestMapping("/info")
	public Result getMenuById(@RequestParam int mid) {
		Result result;
		try {
			result=sysMenuService.putResult(sysMenuService.getMenu(mid));
		} catch (Exception e) {
			result=sysMenuService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	// 加载角色的菜单
	@ResponseBody
	@RequestMapping("/role")
	public Result loadMenusOfRole(@RequestParam int rid){
		Result result;
		try {
			result=sysMenuService.putResult(sysMenuService.loadMenusOfRole(rid));
		} catch (Exception e) {
			result=sysMenuService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	// 加载子菜单
	@ResponseBody
	@RequestMapping("/son")
	public Result loadSonMenus(@RequestParam(defaultValue="0") int parentId){
		Result result;
		try {
			result=sysMenuService.putResult(sysMenuService.getMenus(parentId));
		} catch (Exception e) {
			result=sysMenuService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
}