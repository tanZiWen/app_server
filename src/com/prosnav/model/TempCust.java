package com.prosnav.model;

/***********************************************************************
 * Module:  TempCust.java
 * Author:  user
 * Purpose: Defines the Class TempCust
 ***********************************************************************/

/**
 * 临时客户关键信息
 * 
 * @pdOid 30506189-f86c-442f-bac5-2cfef81d1fc7
 */
public class TempCust {
	/** @pdOid 5eb0b093-7e9e-4480-8968-afd8f837b3ff */
	private java.lang.String tempCustId;
	/**
	 * 客户关键信息JSON 
	 * 
	 * {
			"custId":"value",
			"custCell":"value",
			"custIdnum":"value",
			"custEmail":"value",
			"salesName":"value",
			"salesId":"value",
			"salesPhone":"value",
			"isInvestor":false
			}
	 * 
	 * @pdOid 9519c8ea-071a-4a8b-bbfb-d0c26e254f02
	 */
	private java.lang.String custJson;
	/**
	 * TOKEN创建时间
	 * 
	 * @pdOid b0941dd8-6890-413b-aa4e-3ac4c38189bc
	 */
	private java.util.Date createTime;
	/**
	 * TOKEN修改时间
	 * 
	 * @pdOid 59d89566-eb1a-438d-b650-b96b61bef781
	 */
	private java.util.Date updateTime;
	private String phone;
	private String idCard;
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public java.lang.String getTempCustId() {
		return tempCustId;
	}

	public void setTempCustId(java.lang.String tempCustId) {
		this.tempCustId = tempCustId;
	}

	public java.lang.String getCustJson() {
		return custJson;
	}

	public void setCustJson(java.lang.String custJson) {
		this.custJson = custJson;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
}