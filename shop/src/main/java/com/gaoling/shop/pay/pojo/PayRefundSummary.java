package com.gaoling.shop.pay.pojo;

public class PayRefundSummary {

	private int id;
	private float amount;
	private float cash;
	private float refundCash;
	private int coin;
	private int refundCoin;
	private int point;
	private int refundPoint;
	private int tradeType;
	private int tradeId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getCash() {
		return cash;
	}

	public void setCash(float cash) {
		this.cash = cash;
	}

	public float getRefundCash() {
		return refundCash;
	}

	public void setRefundCash(float refundCash) {
		this.refundCash = refundCash;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public int getRefundCoin() {
		return refundCoin;
	}

	public void setRefundCoin(int refundCoin) {
		this.refundCoin = refundCoin;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getRefundPoint() {
		return refundPoint;
	}

	public void setRefundPoint(int refundPoint) {
		this.refundPoint = refundPoint;
	}

	public int getTradeType() {
		return tradeType;
	}

	public void setTradeType(int tradeType) {
		this.tradeType = tradeType;
	}

	public int getTradeId() {
		return tradeId;
	}

	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}

	// 交易类型
	public static enum TRADE_TYPE_ENUM {
		GOODSPAY(1), INVITE(2), GOODSREFUND(3), REWARD(4), CASHEXCHANGE(5);
		private int state;

		private TRADE_TYPE_ENUM(int state) {
			this.state = state;
		}

		public int getState() {
			return state;
		}
	}

}
