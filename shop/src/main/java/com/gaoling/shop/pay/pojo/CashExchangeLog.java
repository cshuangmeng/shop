package com.gaoling.shop.pay.pojo;

import java.util.Date;

public class CashExchangeLog {

	private int id;
	private int userId;
	private int tribeId;
	private int level;
	private String tradeNo;
	private float cashExchangeRatio;
	private int coin;
	private float cash;
	private int coinBalance;
	private int state;
	private Date operateTime;
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTribeId() {
		return tribeId;
	}

	public void setTribeId(int tribeId) {
		this.tribeId = tribeId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public float getCashExchangeRatio() {
		return cashExchangeRatio;
	}

	public void setCashExchangeRatio(float cashExchangeRatio) {
		this.cashExchangeRatio = cashExchangeRatio;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public float getCash() {
		return cash;
	}

	public void setCash(float cash) {
		this.cash = cash;
	}

	public int getCoinBalance() {
		return coinBalance;
	}

	public void setCoinBalance(int coinBalance) {
		this.coinBalance = coinBalance;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	// 处理状态
	public static enum STATE_TYPE_ENUM {
		DAICHULI(0), YICHULI(1), YIQUXIAO(2);
		private int state;

		private STATE_TYPE_ENUM(int state) {
			this.state = state;
		}

		public int getState() {
			return state;
		}
	}

}
