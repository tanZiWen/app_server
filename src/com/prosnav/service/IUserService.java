package com.prosnav.service;

import java.util.HashMap;

import com.prosnav.model.TDict;

import net.sf.json.JSONObject;

public interface IUserService {
	/**
	 * 获取用户信息
	 * */
	public JSONObject findUser(String loginName, String idCard, HashMap<String, TDict> dictMap, boolean isLogin);
	
	/**
	 * 修改手机号、邮箱、昵称、血型
	 * */
	public JSONObject modifyUser(JSONObject json, HashMap<String, TDict> dictMap);
	
	/**
	 * 修改密码携带信验证码短
	 * */
	public JSONObject modifyUserByPwdSms(JSONObject json, HashMap<String, TDict> dictMap);
	
	/**
	 * 修改密码
	 * */
	public JSONObject modifyUserByPwd(JSONObject json, HashMap<String, TDict> dictMap);
	
	/**
	 * 上传头像
	 * */
	public JSONObject uploadIcon(JSONObject json, HashMap<String, TDict> dictMap, String localTempDirImg);
}