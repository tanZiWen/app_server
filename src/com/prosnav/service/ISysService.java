package com.prosnav.service;

import java.util.HashMap;

import net.sf.json.JSONObject;

import com.prosnav.model.Cust;
import com.prosnav.model.TDict;

public interface ISysService {

	/**
	 * 获取字典
	 * */
	public HashMap<String, TDict>findDictMap();
	
	/**
	 * 字典公式处理
	 * */
	public TDict findDictRiskList(int score);
	
	/**
	 * 查询风测得分
	 * */
	public int findScore(String phone, String idCard);
	
	/**
	 * 生成存储短信验证码
	 * */
	public JSONObject verifySMS(String userId, String verifyCode);	
	
	/**
	 * 发送验证码，存储
	 * */	
	public JSONObject sendSMS(String mobile, String type);
	
	/**
	 * 刷新TOKEN
	 * */
	public String getToken(String loginName, String tokenId);
	
	/**
	 * 风测数据同步到本地
	 * */
	public boolean syncRiskDataAll();
	
	
	/**
	 * 一条风测数据同步到本地
	 * */
	public JSONObject syncRiskDataForObject(JSONObject json);
	
	/**
	 * 解析临时客户信息JSON得到关键字段
	 * */
	public Cust findTempCustByJsonForObj(String phone, String idCard);
	
	/**
	 * 验证token是否有效
	 * */
	public Boolean isToken(String loginName, String tokenID);
	
	/**
	 * 验证token是否匹配
	 * */
	public Boolean isTokenNo(String loginName, String tokenID);
}