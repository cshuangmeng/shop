package com.gaoling.admin.goods.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gaoling.admin.util.AppConstant;

public class Goods {

	private int id;
	private String name;
	private int typeId;
	private int shopId;
	private float price;
	private float cashDiscount;
	private String details;
	@JsonProperty("headImg")
	private String fullHeadImg;
	@JsonIgnore
	private String headImg;
	@JsonProperty("infoImgs")
	private String fullInfoImgs;
	@JsonIgnore
	private String infoImgs;
	@JsonProperty("detailImgs")
	private String fullDetailImgs;
	@JsonIgnore
	private String detailImgs;
	private int coinEnable;
	private int pointEnable;
	private int state;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private Map<String, Object> extras = new HashMap<String, Object>();

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

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getCashDiscount() {
		return cashDiscount;
	}

	public void setCashDiscount(float cashDiscount) {
		this.cashDiscount = cashDiscount;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getCoinEnable() {
		return coinEnable;
	}

	public void setCoinEnable(int coinEnable) {
		this.coinEnable = coinEnable;
	}

	public int getPointEnable() {
		return pointEnable;
	}

	public void setPointEnable(int pointEnable) {
		this.pointEnable = pointEnable;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getInfoImgs() {
		return infoImgs;
	}

	public void setInfoImgs(String infoImgs) {
		this.infoImgs = infoImgs;
	}

	public String getDetailImgs() {
		return detailImgs;
	}

	public void setDetailImgs(String detailImgs) {
		this.detailImgs = detailImgs;
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

	public String getFullInfoImgs() {
		StringBuffer urls = new StringBuffer();
		if (StringUtils.isNotEmpty(infoImgs)) {
			for (String images : infoImgs.split(",")) {
				urls.append(urls.length() > 0 ? "," : "");
				urls.append(AppConstant.OSS_CDN_SERVER + images);
			}
		}
		return urls.toString().length() > 0 ? urls.toString() : infoImgs;
	}

	public String getFullDetailImgs() {
		StringBuffer urls = new StringBuffer();
		if (StringUtils.isNotEmpty(detailImgs)) {
			for (String images : detailImgs.split(",")) {
				urls.append(urls.length() > 0 ? "," : "");
				urls.append(AppConstant.OSS_CDN_SERVER + images);
			}
		}
		return urls.toString().length() > 0 ? urls.toString() : detailImgs;
	}

	public Map<String, Object> getExtras() {
		return extras;
	}

	// 商品状态
	public static enum STATE_TYPE_ENUM {
		SUBMITTED(0), PASSED(1), REFUSED(2);
		private int state;

		private STATE_TYPE_ENUM(int state) {
			this.state = state;
		}

		public int getState() {
			return state;
		}
	}

	// 商品类型
	public static enum GOODS_TYPE_ENUM {
		LIVE(1), WELCOME(2), HEALTH(3), PRESALE(4);
		private int type;

		private GOODS_TYPE_ENUM(int type) {
			this.type = type;
		}

		public int getType() {
			return type;
		}
	}

}
