package com.prosnav.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.prosnav.dao.MapperTAnswerDAO;
import com.prosnav.dao.MapperTDictDAO;
import com.prosnav.dao.MapperTokenDAO;
import com.prosnav.dao.MapperUserDAO;
import com.prosnav.model.TDict;
import com.prosnav.model.TToken;
import com.prosnav.model.TUser;
import com.prosnav.service.ILoginService;
import com.prosnav.utils.Base64FR;
import com.prosnav.utils.ConfigFR;
import com.prosnav.utils.ErrorDB;
import com.prosnav.utils.MD5Util;
import com.prosnav.utils.ParameterFR;
import com.prosnav.utils.StateCode;
import com.prosnav.utils.TimeFR;

@Service
public class LoginService implements ILoginService {

	@Resource
	private ParameterFR parameterFR;
	@Resource
	private ConfigFR configFR;
	@Resource
	private MapperTokenDAO mapperTokenDAO;
	@Resource 
	private MapperUserDAO mapperUserDAO;
	
	@Resource
	private MapperTDictDAO mapperTDictDAO;
	
	@Resource
	private SysService sysService;
	
	@Resource
	private MapperTAnswerDAO mapperTAnswerDAO;
	
	@Resource
	private UserService userService;
	
	private static Logger log = LoggerFactory.getLogger(LoginService.class);
	
	@Override
	public JSONObject login(JSONObject json, HashMap<String, TDict> dictMap) {
		JSONObject result = new JSONObject();
		int count = 0;
		
//		sysService.syncRiskDataAll();
		
		if(StringUtils.isBlank(json.get(parameterFR.LOGIN_NAME).toString()) || StringUtils.isBlank(json.get(parameterFR.LOGIN_PWD).toString()) 
				|| StringUtils.isBlank(json.get(parameterFR.DEVICE_ID).toString())){
			log.info(dictMap.get("VALUE_IS_NULL").getDictDesc());
			result.put(parameterFR.DESC, dictMap.get("VALUE_IS_NULL").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_800);
			return result;
		}
		
		String loginName = json.get(parameterFR.LOGIN_NAME).toString();
		loginName = Base64FR.getBase64(loginName);
		String loginPwd = MD5Util.string2MD5(json.get(parameterFR.LOGIN_PWD).toString());
		String deviceId = json.get(parameterFR.DEVICE_ID).toString();
		
		TUser user = new TUser();
		List<TUser> list = null; 
		user.setLoginName(loginName);
		user.setLoginPwd(loginPwd);
		user.setDeviceId(deviceId);
		
		//===================================================
		log.info("判断用户名密码是否错误");
		list = mapperUserDAO.findloginNameAndLoginPwd(user);
		if(null == list){
			//DB存储失败
			ErrorDB.errorInDB(result, dictMap, parameterFR);
			return result;
		}
		
		if(0 == list.size()){
			//用户名密码错误
			log.info(dictMap.get("NOT_NAME_PWD").getDictDesc());
			result.put(parameterFR.DESC, dictMap.get("NOT_NAME_PWD").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_902);
			return result;
		}
		//===================================================
		
		
		//===================================================
		log.info("判断用户是否已登录");
		boolean bool = this.isLogin(loginName, loginPwd, deviceId);
		if(bool){
			log.info(dictMap.get("IS_LOGIN").getDictDesc());
			result.put(parameterFR.DESC, dictMap.get("IS_LOGIN").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_901);
			return result;
		}
		//===================================================
		
		
		//====================没有设备ID放行，有则为校验===============================
		if(StringUtils.isNoneBlank(list.get(0).getDeviceId())){
			log.info("判断用户、密码、设备号是否相同,是否注册同一设备登录");
			count = mapperUserDAO.findUserAndPwdAndDevice(user);
			if(-1 == count){
				//DB存储失败
				ErrorDB.errorInDB(result, dictMap, parameterFR);
				return result;
			}
			
			if(0 == count){
				log.info(dictMap.get("LOGIN_DEVICE_IS_NOT").getDictDesc());
				result.put(parameterFR.DESC, dictMap.get("LOGIN_DEVICE_IS_NOT").getDictDesc());
				result.put(parameterFR.STATE, StateCode.str_903);
				return result;
			}
		}
		//===================================================
		log.info("用户登录");
		if(0 != list.size()){
			//getToken
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
			tokenEn.setUser(list.get(0));			
			mapperTokenDAO.add(tokenEn);
			if(-1 == count){
				//DB存储失败
				ErrorDB.errorInDB(result, dictMap, parameterFR);
				return result;
			}
			
			if(0 != count){
				user = list.get(0);
				result = userService.findUser(loginName, user.getIdCard(), dictMap, true);
				result.put("token", token);
				return result;
			}			
		}
		
		return result;
	}

	@Override
	public boolean isLogin(String loginName, String loginPwd, String deviceId) {
		loginName = Base64FR.getBase64(loginName);
		TToken tokens = new TToken();		
		tokens.setTokenDictIsDel(false);
		tokens.setCurrTime(TimeFR.getCurrTime());
		TUser user = new TUser();
		user.setLoginName(loginName);
		user.setLoginPwd(loginPwd);
		user.setDeviceId(deviceId);
		tokens.setUser(user);
		
		List<TToken> list = null;
		boolean bool = false;
		
		list = mapperTokenDAO.getUserAndPwd(tokens);
		if(0 != list.size()){
			bool = true;
		}
		
		return bool;
	}
	
	@Override
	public JSONObject exit(String loginName, HashMap<String, TDict> dictMap) {
		JSONObject result = new JSONObject();
		loginName = Base64FR.getBase64(loginName);
		TToken tokenEn = new TToken();
		TUser user = new TUser();
		tokenEn.setTokenDictIsDel(true);
		user.setLoginName(loginName);		
		tokenEn.setUser(user);
		
		if(StringUtils.isBlank(loginName)){
			log.info(dictMap.get("VALUE_IS_NULL").getDictDesc());
			result.put(parameterFR.DESC, dictMap.get("VALUE_IS_NULL").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_800);
			return result;
		}		
		
		int count = mapperTokenDAO.exit(tokenEn);
		if(-1 == count){
			//DB存储失败
			ErrorDB.errorInDB(result, dictMap, parameterFR);
			return result;
		}
		
		log.info("用户已退出update======count=====" + count);
		result.put(parameterFR.DESC, dictMap.get("EXIT").getDictDesc());
		result.put(parameterFR.STATE, StateCode.str_200);
		return result;
	}	
	
	public static void main(String[] args) {
		Date date = new Date();//获取当前时间 
		Calendar calendar = Calendar.getInstance();    
		calendar.setTime(date);    
		calendar.add(Calendar.MINUTE, + 120);//token有效24小时
		System.out.println(calendar.getTime());
	}
}
