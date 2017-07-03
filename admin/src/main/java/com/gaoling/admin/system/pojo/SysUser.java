package com.gaoling.admin.system.pojo;

public class SysUser {

	private int id;
	private String username;
	private String password;
	private int state;
	private int system;
	private String description;
	private String createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getSystem() {
		return system;
	}

	public void setSystem(int system) {
		this.system = system;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	// 用户状态
	public static enum USER_STATE_ENUM {
		NORMAL(1), DISABLED(2), DELETED(3);
		private int state;

		private USER_STATE_ENUM(int state) {
			this.state = state;
		}

		public int getState() {
			return state;
		}
	}

}