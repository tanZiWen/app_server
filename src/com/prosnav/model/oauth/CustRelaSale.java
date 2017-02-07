package com.prosnav.model.oauth;

public class CustRelaSale {

	/**
	 * 客户ID
	 * */
	private String custId;
	
	/**
	 * 客户手机号
	 * */
	private String custCell;
	
	/**
	 * 客户身份号
	 * */
	private String custIdnum;
	
	/**
	 * 客户email
	 * */
	private String email;
	
	/**
	 * 销售（理财师）名称
	 * */
	private String salesName;
	
	/**
	 * 销售（理财师）ID
	 * */
	private String salesId;
	
	/**
	 * 销售（理财师）手机号
	 * */
	private String phone;	
	
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustCell() {
		return custCell;
	}
	public void setCustCell(String custCell) {
		this.custCell = custCell;
	}
	public String getCustIdnum() {
		return custIdnum;
	}
	public void setCustIdnum(String custIdnum) {
		this.custIdnum = custIdnum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSalesName() {
		return salesName;
	}
	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}
	public String getSalesId() {
		return salesId;
	}
	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}