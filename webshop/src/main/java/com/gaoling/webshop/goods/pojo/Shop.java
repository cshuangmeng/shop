package com.gaoling.webshop.goods.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gaoling.webshop.common.AppConstant;

public class Shop {

	private int id;
	private String name;
	@JsonProperty("headImg")
	private String fullHeadImg;
	@JsonIgnore
	private String headImg;
	@JsonProperty("infoImgs")
	private String fullInfoImgs;
	@JsonIgnore
	private String infoImgs;
	private int areaId;
	private int isShow;
	private int flowers;
	private int followers;
	private String areaName;
	private String address;
	@JsonIgnore
	private String contact;
	@JsonIgnore
	private String telephone;
	private String introduction;
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

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getFlowers() {
		return flowers;
	}

	public void setFlowers(int flowers) {
		this.flowers = flowers;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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

	public String getInfoImgs() {
		return infoImgs;
	}

	public void setInfoImgs(String infoImgs) {
		this.infoImgs = infoImgs;
	}

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
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

	public Map<String, Object> getExtras() {
		return extras;
	}

}
