package com.prosnav.model;

/***********************************************************************
 * Module:  TDict.java
 * Author:  user
 * Purpose: Defines the Class TDict
 ***********************************************************************/

/**
 * 字典
 * 
 * 用户类型(SYSTEM_MG：帆茂内部系统；tourist：游客、register:注册用户、investor：:合格投资者用户，VIP1，VIP2,
 * VIP3)
 * 风险状态：nottest：未测试；security：安全型、conservative：保守型、fitness：稳健型、diligence：激进型
 * （测试后有效期为1年，到期时后需要提示再次风测） 是否删除（1：是；0：否） 咨询类型（1：公告；2：品味；3：视野；4：风险揭示） 系统字段类型(
 * investorshow：自述合格投资者； riskbook：私募投资基金风险揭示书； commitment：私募投资保密承诺函；
 * servicetel：客服电话； about：关于我们； ) 验证码类型（1：找回密码；2：注册；3：更改手机号） 合格投资者认定(1：是；0：否)
 * 手机类型 （JAVA,IOS）
 * 
 * @pdOid 5857434c-503a-40d2-b388-3535506dbaf9
 */
public class TDict {
	/** @pdOid bebea13a-9952-4b3b-af04-c589c6379d8b */
	public java.lang.String dictId;
	/**
	 * VALUE
	 * 
	 * @pdOid b7b0c677-abeb-41cb-bc18-f2c03897b123
	 */
	public java.lang.String dictValue;
	/**
	 * 字典描述
	 * 
	 * @pdOid 4d14ed19-c00a-4a95-b643-ebda7fea237c
	 */
	public java.lang.String dictDesc;
	/**
	 * 计算公式
	 * 
	 * 风险状态字符串(x<=26===安全型,x>=27 && x<=40===保守型,x>=41 && x<=61===稳健型,x>=62 &&
	 * x<=81===积极型,x>=82===激进型)
	 * 
	 * @pdOid d2d424b7-a185-4f93-ba2e-eae92bdf58cd
	 */
	public java.lang.String dictFomula;

	/**
	 * 字典类型
	 * */
	private String dictType;

//	private String riskLevelValue;
//	private String riskLevelDesc;
	private String userTypeValue;
	private String userTypeDesc;
	private String mobileTypeValue;
	private String mobileTypeDesc;

	public String getMobileTypeValue() {
		return mobileTypeValue;
	}

	public void setMobileTypeValue(String mobileTypeValue) {
		this.mobileTypeValue = mobileTypeValue;
	}

	public String getMobileTypeDesc() {
		return mobileTypeDesc;
	}

	public void setMobileTypeDesc(String mobileTypeDesc) {
		this.mobileTypeDesc = mobileTypeDesc;
	}

//	public String getRiskLevelValue() {
//		return riskLevelValue;
//	}
//
//	public void setRiskLevelValue(String riskLevelValue) {
//		this.riskLevelValue = riskLevelValue;
//	}
//
//	public String getRiskLevelDesc() {
//		return riskLevelDesc;
//	}
//
//	public void setRiskLevelDesc(String riskLevelDesc) {
//		this.riskLevelDesc = riskLevelDesc;
//	}

	public String getUserTypeValue() {
		return userTypeValue;
	}

	public void setUserTypeValue(String userTypeValue) {
		this.userTypeValue = userTypeValue;
	}

	public String getUserTypeDesc() {
		return userTypeDesc;
	}

	public void setUserTypeDesc(String userTypeDesc) {
		this.userTypeDesc = userTypeDesc;
	}

	public java.lang.String getDictId() {
		return dictId;
	}

	public void setDictId(java.lang.String dictId) {
		this.dictId = dictId;
	}

	public java.lang.String getDictValue() {
		return dictValue;
	}

	public void setDictValue(java.lang.String dictValue) {
		this.dictValue = dictValue;
	}

	public java.lang.String getDictDesc() {
		return dictDesc;
	}

	public void setDictDesc(java.lang.String dictDesc) {
		this.dictDesc = dictDesc;
	}

	public java.lang.String getDictFomula() {
		return dictFomula;
	}

	public void setDictFomula(java.lang.String dictFomula) {
		this.dictFomula = dictFomula;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

}