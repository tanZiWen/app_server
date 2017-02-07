package com.prosnav.model;

/***********************************************************************
 * Module:  TAnswer.java
 * Author:  user
 * Purpose: Defines the Class TAnswer
 ***********************************************************************/

/**
 * 答案
 * 
 * @pdOid a4764e27-ce9c-46f1-b486-f7a55ceace9a
 */
public class TAnswer {
	/** @pdOid 05cefe16-11b6-48c0-86c0-de6cfb704b2c */
	private java.lang.String answerId;
	/** @pdOid 5bb19e67-05ed-4506-87f4-304207878fb8 */
	private java.lang.String answerJson;
	/** @pdOid 62b2a08f-3efc-444a-a1db-e31c8d7db0fd */
	private java.util.Date answerCreateTime;
	/**
	 * 到期时间（效期为1年）
	 * 
	 * @pdOid 11e1f22b-43d2-44cd-b55a-c28065ccb420
	 */
	private java.util.Date answerDueTime;
	/**
	 * 回答是否删除（1:是；0：否）
	 * 
	 * @pdOid fd8d5555-91d9-4c38-bf3d-3da7ac3b2f56
	 */
	private boolean answerDictIsDel;	
	/** @pdOid 2a094fe3-adbe-4b36-b824-f77d341fbbb7 */
	private java.lang.String answerLevel;
	/** @pdOid ed5f6be0-98d8-4a30-b928-23a48ade6508 */
	private java.lang.String phone;

	private String email;
	
	private TIssue issue;
	
	private String cardType;
	private String idCard;
	private String dataSource;

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public java.lang.String getPhone() {
		return phone;
	}

	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public java.lang.String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(java.lang.String answerId) {
		this.answerId = answerId;
	}

	public java.lang.String getAnswerJson() {
		return answerJson;
	}

	public void setAnswerJson(java.lang.String answerJson) {
		this.answerJson = answerJson;
	}

	public java.util.Date getAnswerCreateTime() {
		return answerCreateTime;
	}

	public void setAnswerCreateTime(java.util.Date answerCreateTime) {
		this.answerCreateTime = answerCreateTime;
	}

	public java.util.Date getAnswerDueTime() {
		return answerDueTime;
	}

	public void setAnswerDueTime(java.util.Date answerDueTime) {
		this.answerDueTime = answerDueTime;
	}
	
	public boolean isAnswerDictIsDel() {
		return answerDictIsDel;
	}

	public void setAnswerDictIsDel(boolean answerDictIsDel) {
		this.answerDictIsDel = answerDictIsDel;
	}

	public java.lang.String getAnswerLevel() {
		return answerLevel;
	}

	public void setAnswerLevel(java.lang.String answerLevel) {
		this.answerLevel = answerLevel;
	}

	public TIssue getIssue() {
		return issue;
	}

	public void setIssue(TIssue issue) {
		this.issue = issue;
	}

}