package com.prosnav.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.prosnav.dao.MapperTDictDAO;
import com.prosnav.dao.MapperUserDAO;
import com.prosnav.model.Cust;
import com.prosnav.model.TDict;
import com.prosnav.model.TUser;
import com.prosnav.model.mobile.UserInfo;
import com.prosnav.service.IUserService;
import com.prosnav.utils.Base64FR;
import com.prosnav.utils.ConfigFR;
import com.prosnav.utils.ErrorDB;
import com.prosnav.utils.IOUtilsFR;
import com.prosnav.utils.MD5Util;
import com.prosnav.utils.ParameterFR;
import com.prosnav.utils.StateCode;
import com.prosnav.utils.StringUtilFR;

@Service
public class UserService implements IUserService {

	@Resource
	private ParameterFR parameterFR;
	@Resource
	private ConfigFR configFR;
	@Resource 
	private MapperUserDAO mapperUserDAO;
	@Resource
	private MapperTDictDAO mapperTDictDAO;
	@Resource
	private SysService sysService;	
	private static Logger log = LoggerFactory.getLogger(LoginService.class);
	
	@Override
	public JSONObject findUser(String loginName, String idCard, HashMap<String, TDict> dictMap, boolean isLogin) {
		int count = 0;
		JSONObject result = new JSONObject();
		String email = "";
		String saleName = "";
		String salePhone = "";
		boolean isQualification = false;
		boolean isRiskTest = false;
		TUser user = null;
		
		if(!isLogin){
			loginName = Base64FR.getBase64(loginName);
			idCard = Base64FR.getBase64(idCard);
		}
		
		//======================================通过用户名（手机号），身份证号获取用户是否合格投资者======================================
		Cust cust = sysService.findTempCustByJsonForObj(loginName, idCard);		
		if(null != cust){
			isQualification = Boolean.parseBoolean(cust.getIsInvestor());
			email = cust.getCustEmail();
			saleName = cust.getSalesName();
			salePhone = cust.getSalesPhone();
		}else{
			log.error("目前合规临时表中没有此数据");			
		}
		
		//======================================判断用户是否存在======================================
		List<TUser> userList = mapperUserDAO.findLoginNameForDict(loginName);
		if(null == userList){
			//DB存储失败
			ErrorDB.errorInDB(result, dictMap, parameterFR);
			return result;
		}
		
		if(0 != userList.size()){
			user = userList.get(0);
			
			if(isQualification){					
				if(null == dictMap.get("investor")){
					log.error("字典中没有找到用户investor类型");
				}else{
					user.setUserType(dictMap.get("investor").getDictId());
				}
			}else{
				if(null == dictMap.get("register")){
					log.error("字典中没有找到用户register类型");
				}else{
					user.setUserType(dictMap.get("register").getDictId());
				}			
			}			
			user.setEmail(email);
			
			//======================================更新用户类型（合格投资者），用户email======================================
			count = mapperUserDAO.updateEmailApp(user);
			if(-1 == count){
				//DB存储失败
				ErrorDB.errorInDB(result, dictMap, parameterFR);
				return result;
			}
		}else{
			log.error(loginName + "=======" + dictMap.get("USER_IS_NULL").getDictDesc() + "=======");			
			result.put(parameterFR.DESC, dictMap.get("USER_IS_NULL").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_3102);
			return result;
		}
		
		//======================================重新获取用户信息=================================================
		List<TUser> newUserList = mapperUserDAO.findLoginNameForDict(loginName);
		user = newUserList.get(0);
		
		int score = -1;
		UserInfo userInfo = new UserInfo();
		userInfo.setBirthday(user.getBirthday());
		userInfo.setCreateTime(StringUtilFR.getTime(user.getCreateTime()));
		userInfo.setConstellation(user.getConstellation());
		
		if(StringUtils.isNoneBlank(user.getUserType())){
			userInfo.setUserTypeValue(user.getDict().getUserTypeValue());			
			userInfo.setUserTypeDesc(user.getDict().getUserTypeDesc());
		}else{
			userInfo.setUserTypeValue("");			
			userInfo.setUserTypeDesc("");
		}
		
		//======================================通过用户名（手机号），身份证号匹配是否该用户做过风险测试======================================
		score = sysService.findScore(user.getLoginName(), user.getIdCard());		
		if(-1 != score){
			TDict dict = sysService.findDictRiskList(score);
			userInfo.setRisklevelValue(dict.getDictValue());
			userInfo.setRisklevelDesc(dict.getDictDesc());
			isRiskTest = true;
		}else{
			userInfo.setRisklevelValue("");
			userInfo.setRisklevelValue("");			
		}
		
		userInfo.setRiskTest(isRiskTest);
		userInfo.setSaleName(saleName);
		userInfo.setZodiac(user.getZodiac());
		userInfo.setNickName(user.getNickName());
		userInfo.setUserId(user.getUserId());
		userInfo.setUserName(user.getUserName());
		userInfo.setDeviceId(user.getDeviceId());
		userInfo.setLoginName(Base64FR.getFromBase64(user.getLoginName()));
		userInfo.setIcon(user.getIcon());
		userInfo.setIdCard(Base64FR.getFromBase64(user.getIdCard()));		
		userInfo.setApprove(isQualification);		
		userInfo.setSalePhone(salePhone);
		userInfo.setBlood(user.getBlood());
		userInfo.setFlag(user.getFlag());
		userInfo.setEmail(user.getEmail());
		userInfo.setSysV(user.getSysV());
		
		result.put(parameterFR.JSON, userInfo);
		result.put(parameterFR.STATE, StateCode.str_200);
		return result;
	}

	@Override
	public JSONObject modifyUser(JSONObject json, HashMap<String, TDict> dictMap) {
		JSONObject result = new JSONObject();
		int count = 0;
		
		if(StringUtils.isBlank(json.get(parameterFR.LOGIN_NAME).toString()) 
				|| StringUtils.isBlank(json.get(parameterFR.VALUE).toString()) 
				|| StringUtils.isBlank(json.get(parameterFR.TYPE).toString())){
			
			log.info(dictMap.get("VALUE_IS_NULL").getDictDesc());
			result.put(parameterFR.DESC, dictMap.get("VALUE_IS_NULL").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_800);
			return result;
		}
		
		try {
			String loginName = json.get(parameterFR.LOGIN_NAME).toString();
			loginName = Base64FR.getBase64(loginName);
			String value = json.get(parameterFR.VALUE).toString();
			String type = json.get(parameterFR.TYPE).toString();
			TUser user = new TUser();
			
			user.setLoginName(loginName);
			if("MODIFY_BLOOD".equalsIgnoreCase(type)){
				user.setBlood(value);
				count = mapperUserDAO.updateUserByBlood(user);
			}else if("MODIFY_NICK_NAME".equalsIgnoreCase(type)){
				user.setNickName(StringUtilFR.getZh(value));
				count = mapperUserDAO.updateUserByNickName(user);
			}else if("MODIFY_EMAIL".equalsIgnoreCase(type)){
				user.setEmail(value);
				count = mapperUserDAO.updateUserByEmail(user);
			}else if("MODIFY_PHONE".equalsIgnoreCase(type)){
				user.setNewLoginName(Base64FR.getBase64(value));
				count = mapperUserDAO.updateUserByLoginName(user);
			}else{
				result.put(parameterFR.DESC, dictMap.get("NO_BIZ").getDictDesc());
				result.put(parameterFR.STATE, StateCode.str_6103);
				return result;
			}
			
			if(-1 == count){
				//DB存储失败
				ErrorDB.errorInDB(result, dictMap, parameterFR);
				return result;
			}
			
			result.put(parameterFR.DESC, dictMap.get("MODIFY_SUCCESS").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_6100);
			return result;
		} catch (Exception e) {
			result.put(parameterFR.DESC, dictMap.get("MODIFY_ERROR").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_6102);
			return result;
		}
		
	}

	@Override
	public JSONObject modifyUserByPwdSms(JSONObject json, HashMap<String, TDict> dictMap) {
		JSONObject result = new JSONObject();
		int count = 0;
		String loginName = "";
		
		if(StringUtils.isBlank(json.get(parameterFR.SMS_CODE).toString())
				|| StringUtils.isBlank(json.get(parameterFR.MOBILE_TYPE).toString())
				|| StringUtils.isBlank(json.get(parameterFR.LOGIN_NAME).toString())){
			
			log.info(dictMap.get("VALUE_IS_NULL").getDictDesc());
			
			result.put(parameterFR.DESC, dictMap.get("VALUE_IS_NULL").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_800);
			return result;
		}
		
		String smsCode = json.get(parameterFR.SMS_CODE).toString();
		String mobileType = json.get(parameterFR.MOBILE_TYPE).toString();
		loginName = json.get(parameterFR.LOGIN_NAME).toString();
		loginName = Base64FR.getBase64(loginName);
		
		//验证码校验
		if("android".equalsIgnoreCase(mobileType)){
			JSONObject vJson = sysService.verifySMS(loginName, smsCode);
			if(StateCode.str_7104.equalsIgnoreCase(vJson.get(parameterFR.STATE).toString())){
				//验证码错误
				return vJson;
			}				
			if(StateCode.str_7105.equalsIgnoreCase(vJson.get(parameterFR.STATE).toString())){
				//超时
				return vJson;
			}
		}
		
		if(StringUtils.isBlank(json.get(parameterFR.LOGIN_NAME).toString())
				|| StringUtils.isBlank(json.get(parameterFR.NEW_LOGIN_PWD).toString())){
			
			log.info(dictMap.get("VALUE_IS_NULL").getDictDesc());
			
			result.put(parameterFR.DESC, dictMap.get("VALUE_IS_NULL").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_800);
			return result;
		}
		
		loginName = json.get(parameterFR.LOGIN_NAME).toString();		
		String newLoginPwd = json.get(parameterFR.NEW_LOGIN_PWD).toString();			
		newLoginPwd = MD5Util.string2MD5(newLoginPwd);		
		TUser user = new TUser();
		user.setLoginName(loginName);		
		
		List<TUser> list = mapperUserDAO.findLoginNameForDict(loginName);
		
		if(0 == list.size()){
			log.error(loginName + "=======" + dictMap.get("USER_IS_NULL").getDictDesc() + "=======");			
			result.put(parameterFR.DESC, dictMap.get("USER_IS_NULL").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_3102);
			return result;
		}
		
		try {
			user.setNewLoginPwd(newLoginPwd);
			count = mapperUserDAO.updateUserByLoginPwd(user);
			
			if(-1 == count){
				//DB存储失败
				ErrorDB.errorInDB(result, dictMap, parameterFR);
				return result;
			}
			
			result.put(parameterFR.DESC, dictMap.get("MODIFY_SUCCESS").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_6100);
			return result;
		} catch (Exception e) {
			result.put(parameterFR.DESC, dictMap.get("MODIFY_ERROR").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_6102);
			
			return result;
		}
	}

	@Override
	public JSONObject modifyUserByPwd(JSONObject json, HashMap<String, TDict> dictMap) {
		JSONObject result = new JSONObject();
		int count = 0;
		String loginName = "";
		
		if(StringUtils.isBlank(json.get(parameterFR.LOGIN_NAME).toString()) 
				|| StringUtils.isBlank(json.get(parameterFR.NEW_LOGIN_PWD).toString()) 
				|| StringUtils.isBlank(json.get(parameterFR.LOGIN_PWD).toString())){
			
			log.info(dictMap.get("VALUE_IS_NULL").getDictDesc());
			
			result.put(parameterFR.DESC, dictMap.get("VALUE_IS_NULL").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_800);
			return result;
		}
		
		loginName = json.get(parameterFR.LOGIN_NAME).toString();
		loginName = Base64FR.getBase64(loginName);
		String loginPwd = json.get(parameterFR.LOGIN_PWD).toString();
		String newLoginPwd = json.get(parameterFR.NEW_LOGIN_PWD).toString();		
		loginPwd = MD5Util.string2MD5(loginPwd);		
		newLoginPwd = MD5Util.string2MD5(newLoginPwd);
		
		TUser user = new TUser();
		user.setLoginName(loginName);
		user.setLoginPwd(loginPwd);
		
		List<TUser> list = mapperUserDAO.findloginNameAndLoginPwd(user);
		
		if(0 == list.size()){
			result.put(parameterFR.DESC, dictMap.get("PWD_IS_ERROR").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_6101);
			
			return result;
		}
		
		
		try {
			user.setNewLoginPwd(newLoginPwd);
			count = mapperUserDAO.updateUserByLoginPwd(user);
			
			if(-1 == count){
				//DB存储失败
				ErrorDB.errorInDB(result, dictMap, parameterFR);
				return result;
			}
			
			result.put(parameterFR.DESC, dictMap.get("MODIFY_SUCCESS").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_6100);
			return result;
		} catch (Exception e) {
			result.put(parameterFR.DESC, dictMap.get("MODIFY_ERROR").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_6102);
			
			return result;
		}
	}

	@Override
	public JSONObject uploadIcon(JSONObject json, HashMap<String, TDict> dictMap, String localTempDirImg) {				
		JSONObject result = new JSONObject();
		
		if(StringUtils.isBlank(json.get(parameterFR.ICON_ICON).toString()) 
				|| StringUtils.isBlank(json.get(parameterFR.ICON_FORMAT).toString())
				||StringUtils.isBlank(json.get(parameterFR.LOGIN_NAME).toString())){
			
			log.info(dictMap.get("VALUE_IS_NULL").getDictDesc());
			
			result.put(parameterFR.DESC, dictMap.get("VALUE_IS_NULL").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_800);
			return result;
		}
		
		String loginName = json.get(parameterFR.LOGIN_NAME).toString();
		loginName = Base64FR.getBase64(loginName);
		String iconUrl = IOUtilsFR.SaveServer(json.get(parameterFR.ICON_ICON).toString(), 
				json.get(parameterFR.ICON_FORMAT).toString(), localTempDirImg, dictMap.get("fileicon").getDictDesc(), dictMap);
		if(null != iconUrl){
			//上传成功
			TUser user = new TUser();
			user.setLoginName(loginName);
			user.setIcon(iconUrl);
			int count = mapperUserDAO.uploadIcon(user);
			
			if(-1 == count){
				//DB存储失败
				ErrorDB.errorInDB(result, dictMap, parameterFR);
				return result;
			}
			
			log.info(dictMap.get("UPLOAD_ICON_SUCCESS").getDictDesc());
			result.put(parameterFR.DESC, dictMap.get("UPLOAD_ICON_SUCCESS").getDictDesc());
			result.put(parameterFR.ICON_URL, iconUrl);
			result.put(parameterFR.STATE, StateCode.str_200);
			return result;
		}else{
			//上传失败
			log.info(dictMap.get("UPLOAD_ICON_ERROR").getDictDesc());
			result.put(parameterFR.DESC, dictMap.get("UPLOAD_ICON_ERROR").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_400);
			return result;
		}
	}
}