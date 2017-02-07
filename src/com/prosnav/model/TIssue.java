package com.prosnav.model;

/***********************************************************************
 * Module:  TIssue.java
 * Author:  user
 * Purpose: Defines the Class TIssue
 ***********************************************************************/

/**
 * 问题（涵盖版本号）
 * 
 * @pdOid 52d98983-f19d-449a-9ad9-14704e11087e
 */
public class TIssue {
	/** @pdOid a17de626-9c6c-42f8-9371-99c0b1999bc4 */
	private java.lang.String issueId;
	/**
	 * 问题JSON字典
	 * 
	 * { "1": { "issuename": "阿萨德快接啊肯德基啊", "answer": { "an1:A:SDDDDDD": "5分",
	 * "an2:B:DDDDDD": "3分", "an3:C:DDDDDD": "2分", "an4:D:DDDDDD": "1分" } },
	 * "2": { "issuename": "阿萨德快接啊肯德基啊", "answer": { "an1:A:SDDDDDD": "5分",
	 * "an2:B:SDDDDDD": "3分", "an3:C:SDDDDDD": "2分", "an4:D:SDDDDDD": "1分" } } }
	 * 
	 * @pdOid 4c8c8470-4568-475e-a962-9f354b987044
	 */
	private java.lang.String issueDictJson;
	/**
	 * 问题版本
	 * 
	 * @pdOid 301bef7e-2c1b-46ef-a2d9-cf22983db163
	 */
	private java.lang.String issueVersion;
	/**
	 * 问题创建时间
	 * 
	 * @pdOid 1e218d82-cea6-41d5-a1b8-c94329702e99
	 */
	private java.util.Date issueCreateTime;
	/** @pdOid 3b55f9e2-734b-4a47-ae91-b0e0ef9684d0 */
	private java.lang.String issueIsDel;

	public java.lang.String getIssueId() {
		return issueId;
	}

	public void setIssueId(java.lang.String issueId) {
		this.issueId = issueId;
	}

	public java.lang.String getIssueDictJson() {
		return issueDictJson;
	}

	public void setIssueDictJson(java.lang.String issueDictJson) {
		this.issueDictJson = issueDictJson;
	}

	public java.lang.String getIssueVersion() {
		return issueVersion;
	}

	public void setIssueVersion(java.lang.String issueVersion) {
		this.issueVersion = issueVersion;
	}

	public java.util.Date getIssueCreateTime() {
		return issueCreateTime;
	}

	public void setIssueCreateTime(java.util.Date issueCreateTime) {
		this.issueCreateTime = issueCreateTime;
	}

	public java.lang.String getIssueIsDel() {
		return issueIsDel;
	}

	public void setIssueIsDel(java.lang.String issueIsDel) {
		this.issueIsDel = issueIsDel;
	}

}