package com.gaoling.admin.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gaoling.admin.goods.pojo.SessionInfo;
import com.gaoling.admin.system.dao.SysUserDao;
import com.gaoling.admin.system.pojo.SysUser;
import com.gaoling.admin.util.AppConstant;
import com.gaoling.admin.util.DataUtil;
import com.gaoling.admin.util.DateUtil;


@Service
public class SysUserService {
	
	@Autowired
	private SysUserDao userDao;
	@Autowired
	private SysRoleService roleService;
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	// 新增后台管理人员
	public int addUser(SysUser user) {
		user.setCreateTime(DateUtil.getCurrentTime());
		return userDao.addUser(user);
	}

	// 更新人员
	public void updateUser(SysUser user) {
		userDao.updateUser(user);
	}

	// 获取指定的后台用户
	public SysUser getUser(int id) {
		return userDao.getUser(id);
	}
	
	// 加载所有用户
	public List<HashMap<String,Object>> loadAllUsers(){
		List<HashMap<String,Object>> users=userDao.loadAllUsers();
		for (HashMap<String,Object> user:users) {
			user.put("roles", roleService.getRolesOfUser(Integer.parseInt(user.get("id").toString())));
		}
		return users;
	}
	
	// 为用户分配角色
	public void delRoleForUser(int uid,int rid){
		userDao.delRoleForUser(uid,rid);
	}

	// 删除用户
	@Transactional
	public void deleteUser(int id) {
		SysUser user=getUser(id);
		user.setState(SysUser.USER_STATE_ENUM.DELETED.getState());
		userDao.updateUser(user);
	}
	
	// 通过登录名获取用户
	public SysUser getUserByUsername(String username){
		return userDao.getUserByUsername(username);
	}
	
	// 为用户分配角色
	@Transactional
	public void addRoleForUser(int uid,String rids){
		delRoleForUser(uid,0);
		for(String rid:rids.split(",")){
			userDao.addRoleForUser(uid,Integer.parseInt(rid));
		}
	}
	
	// 加载用户菜单
	public List<HashMap<String,Object>> loadMenusOfUser(int uid){
		return userDao.loadMenusOfUser(uid);
	}
	
	// 加载角色所拥有的用户
	public List<HashMap<String,Object>> getUsersOfRole(int rid){
		return userDao.getUsersOfRole(rid);
	}
	
	// 用户登录
	public String login(String username,String pwd,RedirectAttributes attributes, Locale locale){
		// 获取当前登录用户
		Subject currentUser = SecurityUtils.getSubject();
		// 判断当前用户是否登录
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(username, DataUtil.encodeWithSalt(pwd, username));
			// oss登录状态
			token.setRememberMe(true);
			try {
				currentUser.login(token);
			}catch (UnknownAccountException e){
				attributes.addFlashAttribute("message", messageSource.getMessage("login.error", null, locale));
				return "redirect:login";
			}catch (LockedAccountException e){
				attributes.addFlashAttribute("message", messageSource.getMessage("login.locked", null, locale));
				return "redirect:login";
			}catch (IncorrectCredentialsException e){
				attributes.addFlashAttribute("message", messageSource.getMessage("login.error", null, locale));
				return "redirect:login";
			}
		}
		SysUser user=getUser((int)currentUser.getPrincipal());
		//保存用户信息
		SessionInfo session=new SessionInfo();
		session.setUsername(username);
		session.setPassword(pwd);
		session.setLoginTime(DateUtil.getCurrentTime());
		session.setUserId(user.getId());
		session.setMenus(loadMenusOfUser(user.getId()));
		currentUser.getSession().setAttribute(AppConstant.SESSION_DATA_NAME, session);
		//跳转到欢迎页
		return "redirect:index";
	}
	
}
