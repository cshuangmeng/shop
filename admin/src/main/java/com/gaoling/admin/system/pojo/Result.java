package com.gaoling.admin.system.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class Result {

	private int code;
	private Object data;
	private String msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
