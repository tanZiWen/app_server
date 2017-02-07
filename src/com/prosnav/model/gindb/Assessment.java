package com.prosnav.model.gindb;

public class Assessment {

	/**
	 * 客户手机号
	 * */
	private String mobile;
	
	/**
	 * 客户名称
	 * */
	private String name;
	
	/**
	 * 客户分数
	 * */
	private String score;
	
	private String crt;
	
	private String email;
	
	private String concat;
	
	public String getConcat() {
		return concat;
	}
	public void setConcat(String concat) {
		this.concat = concat;
	}
	public String getCrt() {
		return crt;
	}
	public void setCrt(String crt) {
		this.crt = crt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
}