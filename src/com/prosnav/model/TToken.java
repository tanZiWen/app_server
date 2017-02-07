package com.prosnav.model;

/***********************************************************************
 * Module:  TToken.java
 * Author:  user
 * Purpose: Defines the Class TToken
 ***********************************************************************/


/**
 * 用户身份TOKEN_MD5_32位颠倒加密
 * 
 * @pdOid 8d94bd74-6e01-4946-9fea-d622ba4ea918
 */
public class TToken {
	/** @pdOid d00b2a10-ef01-4e4d-aba2-aa4f7f230abd */
	public java.lang.String toId;
	/**
	 * 生成TOKEN_ID
	 * 
	 * @pdOid f066983e-26d2-4273-b2cd-d476524fa3ec
	 */
	public java.lang.String tokenId;
	/**
	 * 生效时间
	 * 
	 * @pdOid eb2b438b-17ff-46b4-951b-64dc5b6ecbc4
	 */
	public java.util.Date validTime;
	/**
	 * 失效时间
	 * 
	 * @pdOid 6e783f9f-a3c3-4f71-8b13-f642396b7421
	 */
	public java.util.Date failureTime;
	/**
	 * TOKEN创建时间
	 * 
	 * @pdOid 07c6bb6e-7210-4a0d-b17d-3d7ff09d6577
	 */
	public java.util.Date createTime;
	
	private String newTokenId;
	
	public String getNewTokenId() {
		return newTokenId;
	}

	public void setNewTokenId(String newTokenId) {
		this.newTokenId = newTokenId;
	}

	/**
	 * TOKEN修改时间
	 * 
	 * @pdOid 14fe669a-8a69-4332-8a85-f0c6064b1070
	 */
	public java.util.Date updateTime;
	
	public boolean tokenDictIsDel;
	
	public String currTime;

	public String getCurrTime() {
		return currTime;
	}

	public void setCurrTime(String currTime) {
		this.currTime = currTime;
	}

	public boolean isTokenDictIsDel() {
		return tokenDictIsDel;
	}

	public void setTokenDictIsDel(boolean tokenDictIsDel) {
		this.tokenDictIsDel = tokenDictIsDel;
	}

	public TUser user;
	
	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public java.lang.String getToId() {
		return toId;
	}

	public void setToId(java.lang.String toId) {
		this.toId = toId;
	}

	public java.lang.String getTokenId() {
		return tokenId;
	}

	public void setTokenId(java.lang.String tokenId) {
		this.tokenId = tokenId;
	}

	public java.util.Date getValidTime() {
		return validTime;
	}

	public void setValidTime(java.util.Date validTime) {
		this.validTime = validTime;
	}

	public java.util.Date getFailureTime() {
		return failureTime;
	}

	public void setFailureTime(java.util.Date failureTime) {
		this.failureTime = failureTime;
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