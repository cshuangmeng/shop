package com.gaoling.admin.system.pojo;

import java.util.HashMap;
import java.util.List;

public class SessionInfo {

	private int userId;
	private String username;
	private String password;
	private String loginTime;
	private List<HashMap<String, Object>> menus;
	private List<HashMap<String, Object>> roles;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public List<HashMap<String, Object>> getMenus() {
		return menus;
	}

	public void setMenus(List<HashMap<String, Object>> menus) {
		this.menus = menus;
	}

	public List<HashMap<String, Object>> getRoles() {
		return roles;
	}

	public void setRoles(List<HashMap<String, Object>> roles) {
		this.roles = roles;
	}

}
