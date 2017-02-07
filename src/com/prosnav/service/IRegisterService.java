package com.prosnav.service;

import java.util.HashMap;

import com.prosnav.model.TDict;

import net.sf.json.JSONObject;

public interface IRegisterService {
	
	/**
	 * 注册
	 * */
	public JSONObject register(JSONObject json, HashMap<String, TDict> dictMap);
}