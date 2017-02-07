package com.prosnav.service;

import java.util.HashMap;

import com.prosnav.model.TDict;

import net.sf.json.JSONObject;

public interface ILoginService {

	/**
	 * 用户登录,获取TOKEN
	 * */
	public JSONObject login(JSONObject json, HashMap<String, TDict> dictMap);		
	
	/**
	 * 验证用户是否重复登录
	 * */
	public boolean isLogin(String loginName, String loginPwd, String deviceId);
	
	/**
	 * 退出,逻辑删除TOKEN
	 * */
	public JSONObject exit(String loginName, HashMap<String, TDict> dictMap);
}