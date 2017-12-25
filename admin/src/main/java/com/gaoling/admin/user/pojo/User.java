package com.gaoling.admin.user.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gaoling.admin.util.AppConstant;

public class User {

	private int id;
	private String uuid;
	private String nickname;
	private String openId;
	private String webId;
	private String unionId;
	@JsonProperty("headImg")
	private String fullHeadImg;
	@JsonIgnore
	private String headImg;
	private String cellphone;
	@JsonIgnore
	private String password;
	private int coin;
	private int point;
	private int state;
	private int cashExchangeEnable;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date loginTime;
	private Map<String, Object> extras = new HashMap<String, Object>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getWebId() {
		return webId;
	}

	public void setWebId(String webId) {
		this.webId = webId;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCashExchangeEnable() {
		return cashExchangeEnable;
	}

	public void setCashExchangeEnable(int cashExchangeEnable) {
		this.cashExchangeEnable = cashExchangeEnable;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Map<String, Object> getExtras() {
		return extras;
	}

	public String getFullHeadImg() {
		StringBuffer urls = new StringBuffer();
		if (StringUtils.isNotEmpty(headImg)) {
			for (String images : headImg.split(",")) {
				urls.append(urls.length() > 0 ? "," : "");
				urls.append(AppConstant.OSS_CDN_SERVER + images);
			}
		}
		return urls.toString().length() > 0 ? urls.toString() : headImg;
	}

	// 用户状态
	public static enum STATE_TYPE_ENUM {
		INACTIVE(0), ACTIVATED(1), DISABLED(2);
		private int state;

		private STATE_TYPE_ENUM(int state) {
			this.state = state;
		}

		public int getState() {
			return state;
		}
	}

}
