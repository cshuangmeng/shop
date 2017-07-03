package com.gaoling.admin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaoling.admin.goods.pojo.JsonResult;
import com.gaoling.admin.system.pojo.SysMenu;
import com.gaoling.admin.system.service.SysMenuService;

@Controller
public class SysMenuController {
	
	@Autowired
	private SysMenuService menuService;

	// 进入菜单管理首页
	@RequestMapping("/auth/menu")
	public String index() {
		return "auth/menu";
	}
	
	//加载所有菜单
	@ResponseBody
	@RequestMapping("/auth/menu/list")
	public JsonResult loadAllMenus() {
		JsonResult json = new JsonResult();
		try {
			json.setData(menuService.loadAllMenus());
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}

	// 增加或更新菜单
	@ResponseBody
	@RequestMapping("/auth/menu/update")
	public JsonResult updateMenu(@ModelAttribute SysMenu menu) {
		JsonResult json = new JsonResult();
		try {
			SysMenu m=menuService.getMenuByName(menu.getMenuName());
			if (menu.getId() > 0) {
				if(null==m||m.getId()==menu.getId()){
					menuService.updateMenu(menu);
				}else{
					json.setResult(2);
				}
			} else if(null==m){
				menuService.addMenu(menu);
			}else{
				json.setResult(2);
			}
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}

	// 删除菜单
	@ResponseBody
	@RequestMapping("/auth/menu/del")
	public JsonResult deleteMenu(@RequestParam int mid) {
		JsonResult json = new JsonResult();
		try {
			menuService.deleteMenu(mid);
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}

	// 加载指定菜单信息
	@ResponseBody
	@RequestMapping("/auth/menu/info")
	public JsonResult getMenuById(@RequestParam int mid) {
		JsonResult json = new JsonResult();
		try {
			json.setData(menuService.getMenu(mid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	// 加载角色的菜单
	@ResponseBody
	@RequestMapping("/auth/menu/role")
	public JsonResult loadMenusOfRole(@RequestParam int rid){
		JsonResult json=new JsonResult();
		try {
			json.setData(menuService.loadMenusOfRole(rid));
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
	// 加载子菜单
	@ResponseBody
	@RequestMapping("/auth/menu/son")
	public JsonResult loadSonMenus(@RequestParam(defaultValue="0") int parentId){
		JsonResult json=new JsonResult();
		try {
			json.setData(menuService.getMenus(parentId));
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
}