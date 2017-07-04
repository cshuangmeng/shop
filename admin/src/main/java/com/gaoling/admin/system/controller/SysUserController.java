package com.gaoling.admin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaoling.admin.system.pojo.Result;
import com.gaoling.admin.system.pojo.SysUser;
import com.gaoling.admin.system.service.SysUserService;
import com.gaoling.admin.util.AppConstant;

@Controller
@RequestMapping("/user")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	
	// 进入用户管理页面
	@RequestMapping("/index")
	public String index() {
		return "/user/index";
	}

	//删除用户
	@RequestMapping("/del")
	@ResponseBody
	public Result delete(@RequestParam int uid) {
		Result result;
		try {
			sysUserService.deleteUser(uid);
			result=sysUserService.putResult();
		} catch (Exception e) {
			result=sysUserService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}

	// 新增或更新后台管理人员
	@ResponseBody
	@RequestMapping("/update")
	public Result addOrUpdate(@ModelAttribute SysUser user) {
		Result result;
		try {
			SysUser u=sysUserService.getUserByUsername(user.getUsername());
			if(user.getId()>0){
				if(null==u||user.getId()==u.getId()){
					sysUserService.updateUser(user);
				}
			}else if(null==u){
				sysUserService.addUser(user);
			}
			result=sysUserService.putResult();
		} catch (Exception e) {
			result=sysUserService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}

	// 加载所有用户人员列表
	@ResponseBody
	@RequestMapping("/list")
	public Result loadAllUser() {
		Result result;
		try {
			result=sysUserService.putResult(sysUserService.loadAllUsers());
		} catch (Exception e) {
			result=sysUserService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}

	// 加载指定用户人员信息
	@ResponseBody
	@RequestMapping("/info")
	public Result loadUser(@RequestParam int uid) {
		Result result;
		try {
			result=sysUserService.putResult(sysUserService.getUser(uid));
		} catch (Exception e) {
			result=sysUserService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
	// 为用户分配角色
	@ResponseBody
	@RequestMapping("/roles")
	public Result addRoleForUser(@RequestParam int uid,@RequestParam String rids) {
		Result result;
		try {
			sysUserService.addRoleForUser(uid, rids);
			result=sysUserService.putResult();
		} catch (Exception e) {
			result=sysUserService.putResult(AppConstant.SYSTEM_ERROR_CODE);
			e.printStackTrace();
		}
		return result;
	}
	
}
