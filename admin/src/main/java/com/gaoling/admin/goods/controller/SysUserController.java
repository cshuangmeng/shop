package com.gaoling.admin.goods.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gaoling.admin.goods.entity.JsonResult;
import com.gaoling.admin.goods.entity.SysUser;
import com.gaoling.admin.goods.service.SysUserService;

@Controller
public class SysUserController {
	@Autowired
	private SysUserService userService;
	
	// 进入用户管理页面
	@RequestMapping("/auth/user")
	public String index() {
		return "auth/user";
	}

	//用户登录
	@RequestMapping("/loginSubmit")
	public String shiroLogin(@RequestParam String username, @RequestParam String pwd
			,RedirectAttributes attributes, Locale locale) {
		return userService.login(username, pwd, attributes, locale);
	}

	//删除用户
	@RequestMapping("/auth/user/del")
	@ResponseBody
	public JsonResult delete(@RequestParam int uid) {
		JsonResult json = new JsonResult();
		try {
			userService.deleteUser(uid);
		} catch (Exception e) {
			e.printStackTrace();
			json.setResult(1);
		}
		return json;
	}

	// 新增或更新后台管理人员
	@ResponseBody
	@RequestMapping("/auth/user/update")
	public JsonResult addOrUpdate(@ModelAttribute SysUser user) {
		JsonResult json = new JsonResult();
		try {
			SysUser u=userService.getUserByUsername(user.getUsername());
			if(user.getId()>0){
				if(null==u||user.getId()==u.getId()){
					userService.updateUser(user);
				}else{
					json.setResult(2);
				}
			}else if(null==u){
				userService.addUser(user);
			}else{
				json.setResult(2);
			}
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}

	// 加载所有用户人员列表
	@ResponseBody
	@RequestMapping("/auth/user/list")
	public JsonResult loadAllUser() {
		JsonResult json = new JsonResult();
		try {
			json.setData(userService.loadAllUsers());
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}

	// 加载指定用户人员信息
	@ResponseBody
	@RequestMapping("/auth/user/info")
	public JsonResult loadUser(@RequestParam int uid) {
		JsonResult json = new JsonResult();
		try {
			json.setData(userService.getUser(uid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	// 为用户分配角色
	@ResponseBody
	@RequestMapping("/auth/user/roles")
	public JsonResult addRoleForUser(@RequestParam int uid,@RequestParam String rids) {
		JsonResult json = new JsonResult();
		try {
			userService.addRoleForUser(uid, rids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
}
