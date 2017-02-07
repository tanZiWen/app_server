package com.prosnav.utils;

import java.util.HashMap;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosnav.model.TDict;

public class ErrorDB {

	private static Logger log = LoggerFactory.getLogger(ErrorDB.class);
	
	public static JSONObject errorInDB(JSONObject result, HashMap<String, TDict> dictMap, ParameterFR parameterFR){		
		log.info(dictMap.get("ERROR_DB").getDictDesc());
		result.put(parameterFR.DESC, dictMap.get("ERROR_DB").getDictDesc());
		result.put(parameterFR.STATE, StateCode.str_900);
		
		return result;
	}
}