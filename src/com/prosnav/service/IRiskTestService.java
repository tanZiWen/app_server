package com.prosnav.service;

import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.prosnav.model.TDict;

public interface IRiskTestService {

	/**
	 * 查询风险测试题
	 * */
	public JSONArray findIssue();
	
	/**
	 * 存储风险测试答案
	 * */
	public JSONObject saveAnwer(JSONObject json, HashMap<String, TDict> dictMap);
}