package com.gaoling.admin.system.pojo;

import java.util.Date;

public class SysMenu {

	private int id;
	private String name;
	private String url;
	private String sort;
	private int parentId;
	private String icon;
	private int state;
	private int system;
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	// 菜单状态
	public static enum MENU_STATE_ENUM {
		NORMAL(1), DISABLED(2), DELETED(3);
		private int state;

		private MENU_STATE_ENUM(int state) {
			this.state = state;
		}

		public int getState() {
			return state;
		}
	}
}