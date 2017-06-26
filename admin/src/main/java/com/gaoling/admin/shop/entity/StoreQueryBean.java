package com.gaoling.admin.shop.entity;

public class StoreQueryBean {

	private int areacode;
	private String typeId;
	private String storeName = "";
	private int bdId;
	private String examineState;
	private String createTimeB = "";
	private String createTimeE = "";
	private int pbTotalNoB;
	private int pbTotalNoE;

	public int getAreacode() {
		return areacode;
	}

	public void setAreacode(int areacode) {
		this.areacode = areacode;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getBdId() {
		return bdId;
	}

	public void setBdId(int bdId) {
		this.bdId = bdId;
	}

	public String getExamineState() {
		return examineState;
	}

	public void setExamineState(String examineState) {
		this.examineState = examineState;
	}

	public String getCreateTimeB() {
		return createTimeB;
	}

	public void setCreateTimeB(String createTimeB) {
		this.createTimeB = createTimeB;
	}

	public String getCreateTimeE() {
		return createTimeE;
	}

	public void setCreateTimeE(String createTimeE) {
		this.createTimeE = createTimeE;
	}

	public int getPbTotalNoB() {
		return pbTotalNoB;
	}

	public void setPbTotalNoB(int pbTotalNoB) {
		this.pbTotalNoB = pbTotalNoB;
	}

	public int getPbTotalNoE() {
		return pbTotalNoE;
	}

	public void setPbTotalNoE(int pbTotalNoE) {
		this.pbTotalNoE = pbTotalNoE;
	}

}
