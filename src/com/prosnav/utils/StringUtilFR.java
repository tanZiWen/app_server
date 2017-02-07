package com.prosnav.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public class StringUtilFR {

//	public static String getZh(String str) throws UnsupportedEncodingException {
//		return new String(str.getBytes("ISO-8859-1"), "UTF-8");
//	}
	
	public static String getGBKZh(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("GBK"), "UTF-8");
	}	

	public static String getZh(String str){
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}	
	
	public static boolean getIsScore(String str, int score) {
		// str = "(a >= 0 && a <= 5)";
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		engine.put("x", score);
		Object result;
		try {
			result = engine.eval(str);
			// System.out.println("结果类型:" + result.getClass().getName() +
			// ",计算结果:" + result);
			return (boolean) result;
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getFileTime());
	}

	public static String getTime(Date date) {
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(sdf.format(date));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.format(date));
//        sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return sdf.format(date);
    }
	
	
	public static String getFileTime(){
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		String time=format.format(date);
		return time;
	}
	
	public static int getVerifyCode() {
		return (int)((Math.random()*9+1)*100000);
	}
	
	public static void addToekn(JSONObject result, HttpServletRequest request, ParameterFR parameterFR){
		if(null != request.getAttribute(parameterFR.TOKEN)){
			result.put(parameterFR.TOKEN, request.getAttribute(parameterFR.TOKEN));
		}
	}
	
	public static String getLocalTempDirImg(HttpServletRequest request){
		return request.getSession().getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "static" + File.separator + "tempImg";
	}
}