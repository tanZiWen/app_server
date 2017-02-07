package com.prosnav.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.prosnav.dao.MapperTDictDAO;
import com.prosnav.dao.MapperTokenDAO;
import com.prosnav.dao.MapperUserDAO;
import com.prosnav.model.Cust;
import com.prosnav.model.TDict;
import com.prosnav.model.TToken;
import com.prosnav.model.TUser;
import com.prosnav.service.IRegisterService;
import com.prosnav.utils.Base64FR;
import com.prosnav.utils.ConfigFR;
import com.prosnav.utils.ErrorDB;
import com.prosnav.utils.IdCardUtils;
import com.prosnav.utils.IdcardValidator;
import com.prosnav.utils.MD5Util;
import com.prosnav.utils.ParameterFR;
import com.prosnav.utils.StateCode;
import com.prosnav.utils.StringUtilFR;
import com.prosnav.utils.TimeFR;

@Service
public class RegisterService implements IRegisterService {
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
	@Resource
	private UserService userService;	
	@Resource
	private MapperTokenDAO mapperTokenDAO;	
	private static Logger log = LoggerFactory.getLogger(LoginService.class);
	
	@SuppressWarnings("unused")
	@Override
	public JSONObject register(JSONObject json, HashMap<String, TDict> dictMap) {
		JSONObject result = new JSONObject();
		int count = 0;		
		
		if(StringUtils.isBlank(json.get(parameterFR.USER_NAME).toString()) 
				|| StringUtils.isBlank(json.get(parameterFR.ID_CARD).toString()) 
				|| StringUtils.isBlank(json.get(parameterFR.LOGIN_NAME).toString())				
				|| StringUtils.isBlank(json.get(parameterFR.LOGIN_PWD).toString())
				|| StringUtils.isBlank(json.get(parameterFR.SYS_V).toString())
				|| StringUtils.isBlank(json.get(parameterFR.MOBILE_TYPE).toString())
				|| StringUtils.isBlank(json.get(parameterFR.DEVICE_ID).toString())){
			
			log.info(dictMap.get("VALUE_IS_NULL").getDictDesc());
			result.put(parameterFR.DESC, dictMap.get("VALUE_IS_NULL").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_800);
			return result;
		}
		
		String deviceId = json.get(parameterFR.DEVICE_ID).toString();
		String sysV = json.get(parameterFR.SYS_V).toString();
		String mobileType = json.get(parameterFR.MOBILE_TYPE).toString();
		String userName = json.get(parameterFR.USER_NAME).toString();
		userName = StringUtilFR.getZh(userName);
		String smsCode = json.get(parameterFR.SMS_CODE).toString();
		String loginName = json.get(parameterFR.LOGIN_NAME).toString();
		loginName = Base64FR.getBase64(loginName);
		
		//======================================验证码校验======================================
		if("android".equalsIgnoreCase(mobileType)){
			JSONObject vJson = sysService.verifySMS(Base64FR.getFromBase64(loginName), smsCode);
			if(StateCode.str_7104.equalsIgnoreCase(vJson.get(parameterFR.STATE).toString())){
				//验证码错误
				return vJson;
			}
			if(StateCode.str_7105.equalsIgnoreCase(vJson.get(parameterFR.STATE).toString())){
				//超时
				return vJson;
			}
		}		
		
		String idCard = json.get(parameterFR.ID_CARD).toString();
		idCard = Base64FR.getBase64(idCard);
		String loginPwd = json.get(parameterFR.LOGIN_PWD).toString();
		loginPwd = MD5Util.string2MD5(loginPwd);
		String email = "";		
		String saleName = "";
		String salePhone = "";
		boolean isQualification = false;
		String approve = "0";
		String omsId = "";
		
				
		//======================================验证身份证号码是否合法======================================
		if(!IdcardValidator.isValidatedAllIdcard(Base64FR.getFromBase64(idCard))){
			result.put(parameterFR.DESC, dictMap.get("IDCARD_IS_ERROR").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_1103);
			return result;
		}		
		
		//======================================通过手机号，身份证号判断是否合格投资者======================================
		Cust cust = sysService.findTempCustByJsonForObj(loginName, idCard);		
		if(null != cust){
			omsId = cust.getCustId();
			isQualification = true;
		}else{
			log.error("目前合规临时表中没有此数据===============================OMS_ID为空");			
		}
		
		//======================================判断该用户是否在APP本地中======================================
		count = mapperUserDAO.findLoginName(loginName);
		if(-1 == count){
			//DB存储失败
			ErrorDB.errorInDB(result, dictMap, parameterFR);
			return result;
		}
		
		if(0 != count){
			log.info(dictMap.get("IS_PHONE").getDictDesc());
			result.put(parameterFR.DESC, dictMap.get("IS_PHONE").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_1101);
			return result;
		}		
		
		//======================================存储用户到APP本地======================================
		TUser user = new TUser();
		user.setUserId(UUID.randomUUID().toString());
		user.setLoginName(loginName);
		user.setLoginPwd(loginPwd);
		
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
		user.setUserDictIsDel(false);
		user.setUserName(userName);		
		user.setIdCard(idCard);
		user.setCreateTime(new Date());
		user.setEmail(email);
		user.setApprove(approve);						
		user.setUserDictLevel(dictMap.get("user_no").getDictId());
		
		//生日
		user.setBirthday(IdCardUtils.getBirthByIdCard(Base64FR.getFromBase64(idCard)));		
		//星座
		user.setConstellation(IdCardUtils.getConstellationById(Base64FR.getFromBase64(idCard)));		
		//生效
		user.setZodiac(IdCardUtils.getZodiacById(Base64FR.getFromBase64(idCard)));		
		//设备ID
		user.setDeviceId(deviceId);		
		//系统版本		
		user.setSysV(sysV);		
		//身份类型
		user.setCardType("idcard");				
		//手机类型
		if(null == dictMap.get(mobileType)){
			log.error(mobileType + "====================" + "手机类型字典中没有找到");
		}else{
			user.setMobileDictType(dictMap.get(mobileType).getDictId());
		}
		user.setDesc(true);
		user.setRiskBook(true);
		user.setDeploy(true);
		user.setOmsId(omsId);		
		count = mapperUserDAO.add(user);
		if(-1 == count){
			//DB存储失败
			ErrorDB.errorInDB(result, dictMap, parameterFR);
			return result;
		}
		
		if(0 != count){
			//======================================成功直接登录,获取TOKEN======================================
			String token = "";
			
			log.info("token存储");
			token = MD5Util.string2MD5(loginName + TimeFR.getCurrTime());
			String toId = UUID.randomUUID().toString();
			
			TToken tokenEn = new TToken();
			tokenEn.setToId(toId);
			tokenEn.setTokenId(token);
			Date date = new Date();//获取当前时间 
			Calendar calendar = Calendar.getInstance();    
			calendar.setTime(date);    
			calendar.add(Calendar.MINUTE, + Integer.parseInt(dictMap.get("TOKEN_TIME_MINUTE").getDictDesc()));
			tokenEn.setValidTime(date);
			tokenEn.setFailureTime(calendar.getTime());
			tokenEn.setCreateTime(new Date());
			tokenEn.setTokenDictIsDel(false);
			tokenEn.setUser(user);
			mapperTokenDAO.add(tokenEn);
			if(-1 == count){
				//DB存储失败
				ErrorDB.errorInDB(result, dictMap, parameterFR);
				return result;
			}
			
			if(0 != count){
				//======================================获取用户最新数据======================================
				result = userService.findUser(loginName, user.getIdCard(), dictMap, true);
				result.put("token", token);
				return result;
			}
		}
		
		return result;
	}
}