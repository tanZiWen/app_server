package com.prosnav.model;

import java.util.Date;

/***********************************************************************
 * Module:  TVerifyLog.java
 * Author:  user
 * Purpose: Defines the Class TVerifyLog
 ***********************************************************************/


/**
 * 手机验证码
 * 
 * @pdOid c2b5acf2-ae9f-448f-95be-0b9ce52c3468
 */
public class TVerifyLog {
	/** @pdOid 5011e917-e2f3-41d4-937e-bdf9669ed2e8 */
	private java.lang.String verifyId;
	/**
	 * 验证码类型（1：找回密码；2：注册；3：更改手机号）
	 * 
	 * @pdOid ffaad91b-87a6-480b-902b-6cdad49a64e5
	 */
	private java.lang.String verifyDictType;
	/** @pdOid 4e49232d-3e9a-4c3f-963d-f8ca4063cd0b */
	private java.util.Date verifyCreateTime;
	
	private Date verifyFailureTime;

	private String responseContent;
	
	private String phone;
	
	private String verifyCode;
	
	private String verifyState;
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVerifyState() {
		return verifyState;
	}

	public void setVerifyState(String verifyState) {
		this.verifyState = verifyState;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Date getVerifyFailureTime() {
		return verifyFailureTime;
	}

	public void setVerifyFailureTime(Date verifyFailureTime) {
		this.verifyFailureTime = verifyFailureTime;
	}

	public String getResponseContent() {
		return responseContent;
	}

	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}

//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}

	public java.lang.String getVerifyId() {
		return verifyId;
	}

	public void setVerifyId(java.lang.String verifyId) {
		this.verifyId = verifyId;
	}

	public java.lang.String getVerifyDictType() {
		return verifyDictType;
	}

	public void setVerifyDictType(java.lang.String verifyDictType) {
		this.verifyDictType = verifyDictType;
	}

	public java.util.Date getVerifyCreateTime() {
		return verifyCreateTime;
	}

	public void setVerifyCreateTime(java.util.Date verifyCreateTime) {
		this.verifyCreateTime = verifyCreateTime;
	}

}