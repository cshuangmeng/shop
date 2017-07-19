package com.gaoling.webshop.goods.pojo;

import java.util.Date;

public class ShopFollower {

	private int id;
	private int shopId;
	private int userId;
	private Date subTime;
	private Date cancelTime;
	private int state;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getSubTime() {
		return subTime;
	}

	public void setSubTime(Date subTime) {
		this.subTime = subTime;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	// 关注状态
	public static enum STATE_TYPE_ENUM {
		CANCEL(0), FOLLOWED(1);
		private int state;

		private STATE_TYPE_ENUM(int state) {
			this.state = state;
		}

		public int getState() {
			return state;
		}
	}

}
