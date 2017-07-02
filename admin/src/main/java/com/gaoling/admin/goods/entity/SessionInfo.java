package com.gaoling.admin.goods.entity;

import java.util.HashMap;
import java.util.List;

public class SessionInfo {

	private int uid;
	private String userId;
	private String username;
	private String password;
	private String loginTime;
	private List<HashMap<String, Object>> menus;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
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

}
