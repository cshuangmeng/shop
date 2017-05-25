package com.gaoling.shop.goods.pojo;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gaoling.shop.common.AppConstant;

public class Goods {

	private int id;
	private String name;
	private int typeId;
	private int shopId;
	private int price;
	private int cashDiscount;
	private String details;
	@JsonProperty("images")
	private String fullImages;
	@JsonIgnore
	private String images;
	private int coinEnable;
	private int pointEnable;
	private int state;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCashDiscount() {
		return cashDiscount;
	}

	public void setCashDiscount(int cashDiscount) {
		this.cashDiscount = cashDiscount;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
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

	public String getFullImages() {
		StringBuffer urls=new StringBuffer();
		if(StringUtils.isNotEmpty(images)){
			for(String images:images.split(",")){
				urls.append(urls.length()>0?",":"");
				urls.append(AppConstant.OSS_CDN_SERVER+images);
			}
		}
		return urls.toString().length()>0?urls.toString():images;
	}

}
