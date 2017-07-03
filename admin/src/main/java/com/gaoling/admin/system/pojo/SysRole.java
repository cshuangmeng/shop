package com.gaoling.admin.system.pojo;

import java.util.Arrays;
import java.util.List;

public class SysRole {

	private int id;
	private String name;
	private int state;
	private String system;
	private String description;
	public final static List<Integer> SHOWSTATES=Arrays.asList(ROLE_STATE_ENUM.NORMAL.getState(),ROLE_STATE_ENUM.DISABLED.getState());

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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// 角色状态
	public static enum ROLE_STATE_ENUM {
		NORMAL(1), DISABLED(2), DELETED(3);
		private int state;

		private ROLE_STATE_ENUM(int state) {
			this.state = state;
		}

		public int getState() {
			return state;
		}
	}

}