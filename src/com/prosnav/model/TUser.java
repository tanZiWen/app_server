package com.prosnav.model;
/***********************************************************************
 * Module:  TUser.java
 * Author:  user
 * Purpose: Defines the Class TUser
 ***********************************************************************/


/**
 * 用户
 * 
 * @pdOid 58a0159f-9ccd-4ae6-8b6e-b413b9662841
 */
public class TUser {
	/** @pdOid 70c23b8a-5923-4201-ba3b-5cb9f92c363f */
	public java.lang.String userId;
	/**
	 * 登录帐号
	 * 
	 * @pdOid eba64354-98c7-4438-b5c7-74e0ddfd4b65
	 */
	public java.lang.String loginName;
	/**
	 * 登录密码
	 * 
	 * @pdOid ca9641fb-da13-423b-8e6e-87e348ac8054
	 */
	public java.lang.String loginPwd;
	/**
	 * 用户类型(SYSTEM_MG：帆茂内部系统；tourist：游客、register:注册用户、investor：:合格投资者用户)
	 * 
	 * @pdOid 85ebd55f-347e-4463-a4c4-9990ba72c00e
	 */
	public java.lang.String userType;
	/**
	 * 逻辑删除（1：是；0：否）
	 * 
	 * @pdOid 2de60888-b080-4e67-98a6-19a602cc0c97
	 */
	
	public boolean userDictIsDel;
	
	/**
	 * 是否是自述合格投资人
	 * */
	private boolean isDesc;
	
	/**
	 * 是否看过风险揭示书
	 * */
	private boolean isRiskBook;
	
	/**
	 * 是否签署过保密协议
	 * */
	private boolean isDeploy;

	/**
	 * 用户姓名
	 * 
	 * @pdOid a7a15a1a-7cc2-46cc-8194-14a6aec04932
	 */
	public java.lang.String userName;
	/**
	 * 昵称
	 * 
	 * @pdOid fce225c6-ab3d-409e-80ea-9183b09f230c
	 */
	public java.lang.String nickName;
	/**
	 * 身份证号码
	 * 
	 * @pdOid 5f4e6cf4-6665-4ace-97b1-e08ba71c5176
	 */
	public java.lang.String idCard;
	/**
	 * TOKEN创建时间
	 * 
	 * @pdOid 1e1abbde-dee7-4820-b2ec-cdd7f0ab8e7d
	 */
	public java.util.Date createTime;
	/**
	 * TOKEN修改时间
	 * 
	 * @pdOid bc305d7c-a976-465d-81a3-4558e3497b7b
	 */
	public java.util.Date updateTime;
	/**
	 * 登录时间
	 * 
	 * @pdOid 91b43dea-1eee-4c2d-bc14-f3be7bba3203
	 */
	public java.util.Date loginTime;
	
	/**
	 * 标记
	 * 	
	 */
	public String flag;
	
	public String cardType;
	
	

	//	/**
//	 * 风险级别
//	 * 
//	 * @pdOid 170cde98-6221-478b-8576-f2737d2307ea
//	 */
//	public String riskDictLevel;
	/**
	 * 用户头像
	 * 
	 * @pdOid a721d167-2bf1-401f-9b92-4b48c1e49c4f
	 */
	public java.lang.String icon;
	/**
	 * 邮箱
	 * 
	 * @pdOid 1df20035-27d4-45ea-a064-8d98c3e51488
	 */
	public java.lang.String email;
	/**
	 * 合格投资者认定(1：是；0：否)
	 * 
	 * @pdOid 88ad11f7-881f-4b02-a9d7-1868e8535ed3
	 */
	public java.lang.String approve;
	
	public TToken token;
	
	/**
	 * 生日
	 * */
	public String birthday;
	
	/**
	 * 星座
	 * */
	public String constellation;
	
	/**
	 * 生肖
	 * */
	public String zodiac;
	
	/**
	 * 血型
	 * */
	public String blood;

	/**
	 * 风险测试级别
	 * */
	public String deviceId;
	
	/**
	 * 风险测试级别
	 * */
	public String sysV;
	
	/**
	 * 风险测试级别
	 * */
	public String mobileDictType;
			
	private String saleName;
	private String salePhone;
	
	private String newLoginName;
	private String newLoginPwd;
		
	private String omsId;
	private String deviceSys;
	private boolean isAppUser;
	private boolean isSales;
	private boolean isMsgSend;
	private String userDictLevel;
		
	public String getUserDictLevel() {
		return userDictLevel;
	}

	public void setUserDictLevel(String userDictLevel) {
		this.userDictLevel = userDictLevel;
	}

	public String getOmsId() {
		return omsId;
	}

	public void setOmsId(String omsId) {
		this.omsId = omsId;
	}

	public String getDeviceSys() {
		return deviceSys;
	}

	public void setDeviceSys(String deviceSys) {
		this.deviceSys = deviceSys;
	}

	public boolean isAppUser() {
		return isAppUser;
	}

	public void setAppUser(boolean isAppUser) {
		this.isAppUser = isAppUser;
	}

	public boolean isSales() {
		return isSales;
	}

	public void setSales(boolean isSales) {
		this.isSales = isSales;
	}

	public boolean isMsgSend() {
		return isMsgSend;
	}

	public void setMsgSend(boolean isMsgSend) {
		this.isMsgSend = isMsgSend;
	}

	public boolean isDesc() {
		return isDesc;
	}

	public void setDesc(boolean isDesc) {
		this.isDesc = isDesc;
	}

	public boolean isRiskBook() {
		return isRiskBook;
	}

	public void setRiskBook(boolean isRiskBook) {
		this.isRiskBook = isRiskBook;
	}

	public boolean isDeploy() {
		return isDeploy;
	}

	public void setDeploy(boolean isDeploy) {
		this.isDeploy = isDeploy;
	}

	public void setUserDictIsDel(boolean userDictIsDel) {
		this.userDictIsDel = userDictIsDel;
	}
	
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getNewLoginPwd() {
		return newLoginPwd;
	}

	public void setNewLoginPwd(String newLoginPwd) {
		this.newLoginPwd = newLoginPwd;
	}

	public String getNewLoginName() {
		return newLoginName;
	}

	public void setNewLoginName(String newLoginName) {
		this.newLoginName = newLoginName;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public String getSalePhone() {
		return salePhone;
	}

	public void setSalePhone(String salePhone) {
		this.salePhone = salePhone;
	}

	public TDict dict;

	public TDict getDict() {
		return dict;
	}

	public void setDict(TDict dict) {
		this.dict = dict;
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
//
//	public String getUserTypeValue() {
//		return userTypeValue;
//	}
//
//	public void setUserTypeValue(String userTypeValue) {
//		this.userTypeValue = userTypeValue;
//	}
//
//	public String getUserTypeDesc() {
//		return userTypeDesc;
//	}
//
//	public void setUserTypeDesc(String userTypeDesc) {
//		this.userTypeDesc = userTypeDesc;
//	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getSysV() {
		return sysV;
	}

	public void setSysV(String sysV) {
		this.sysV = sysV;
	}

	public String getMobileDictType() {
		return mobileDictType;
	}

	public void setMobileDictType(String mobileDictType) {
		this.mobileDictType = mobileDictType;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public String getZodiac() {
		return zodiac;
	}

	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

//	public String getServocetel() {
//		return servocetel;
//	}
//
//	public void setServocetel(String servocetel) {
//		this.servocetel = servocetel;
//	}
	
	
	public TToken getToken() {
		return token;
	}

	public void setToken(TToken token) {
		this.token = token;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @pdRoleInfo migr=no name=TVerifyLog assc=relationship3
	 *             coll=java.util.Collection impl=java.util.HashSet mult=0..*
	 */
	public java.util.Collection<TVerifyLog> tVerifyLog;

	/** @pdGenerated default getter */
	public java.util.Collection<TVerifyLog> getTVerifyLog() {
		if (tVerifyLog == null)
			tVerifyLog = new java.util.HashSet<TVerifyLog>();
		return tVerifyLog;
	}

	/** @pdGenerated default iterator getter */
	@SuppressWarnings("rawtypes")
	public java.util.Iterator getIteratorTVerifyLog() {
		if (tVerifyLog == null)
			tVerifyLog = new java.util.HashSet<TVerifyLog>();
		return tVerifyLog.iterator();
	}

	/**
	 * @pdGenerated default setter
	 * @param newTVerifyLog
	 */
	public void setTVerifyLog(java.util.Collection<TVerifyLog> newTVerifyLog) {
		removeAllTVerifyLog();
		for (@SuppressWarnings("rawtypes")
		java.util.Iterator iter = newTVerifyLog.iterator(); iter.hasNext();)
			addTVerifyLog((TVerifyLog) iter.next());
	}

	/**
	 * @pdGenerated default add
	 * @param newTVerifyLog
	 */
	public void addTVerifyLog(TVerifyLog newTVerifyLog) {
		if (newTVerifyLog == null)
			return;
		if (this.tVerifyLog == null)
			this.tVerifyLog = new java.util.HashSet<TVerifyLog>();
		if (!this.tVerifyLog.contains(newTVerifyLog))
			this.tVerifyLog.add(newTVerifyLog);
	}

	/**
	 * @pdGenerated default remove
	 * @param oldTVerifyLog
	 */
	public void removeTVerifyLog(TVerifyLog oldTVerifyLog) {
		if (oldTVerifyLog == null)
			return;
		if (this.tVerifyLog != null)
			if (this.tVerifyLog.contains(oldTVerifyLog))
				this.tVerifyLog.remove(oldTVerifyLog);
	}

	/** @pdGenerated default removeAll */
	public void removeAllTVerifyLog() {
		if (tVerifyLog != null)
			tVerifyLog.clear();
	}

	public java.lang.String getUserId() {
		return userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getLoginName() {
		return loginName;
	}

	public void setLoginName(java.lang.String loginName) {
		this.loginName = loginName;
	}

	public java.lang.String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(java.lang.String loginPwd) {
		this.loginPwd = loginPwd;
	}
	
	
	public java.lang.String getUserType() {
		return userType;
	}

	public void setUserType(java.lang.String userType) {
		this.userType = userType;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getNickName() {
		return nickName;
	}

	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}

	public java.lang.String getIdCard() {
		return idCard;
	}

	public void setIdCard(java.lang.String idCard) {
		this.idCard = idCard;
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

	public java.util.Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(java.util.Date loginTime) {
		this.loginTime = loginTime;
	}
	

//	public String getRiskDictLevel() {
//		return riskDictLevel;
//	}
//
//	public void setRiskDictLevel(String riskDictLevel) {
//		this.riskDictLevel = riskDictLevel;
//	}

	public boolean isUserDictIsDel() {
		return userDictIsDel;
	}

	public java.lang.String getIcon() {
		return icon;
	}

	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getApprove() {
		return approve;
	}

	public void setApprove(java.lang.String approve) {
		this.approve = approve;
	}

	public java.util.Collection<TVerifyLog> gettVerifyLog() {
		return tVerifyLog;
	}

	public void settVerifyLog(java.util.Collection<TVerifyLog> tVerifyLog) {
		this.tVerifyLog = tVerifyLog;
	}

}