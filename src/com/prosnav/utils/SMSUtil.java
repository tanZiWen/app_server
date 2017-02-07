package com.prosnav.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosnav.model.TDict;
import com.prosnav.utils.http.HttpUtils;

public class SMSUtil {

//	private static Logger log = LoggerFactory.getLogger(SMSUtil.class);
//	
//	/** 单条短信发送,智能匹配短信模板
//	 *
//	 * @param apikey 成功注册后登录云片官网,进入后台可查看
//	 * @param text   需要使用已审核通过的模板或者默认模板
//	 * @param mobile 接收的手机号,仅支持单号码发送
//	 * @return json格式字符串
//	 */	
//	public static String singleSend(String mobile, String verifiCode, ParameterFR parameterFR, HashMap<String, TDict> dictMap) {
//		String str = null;
//	    Map<String, String> params = new HashMap<String, String>();
//	    String content = dictMap.get("SMS_CONTENT").getDictDesc();
//    	content = content.replace("#code#", verifiCode);
//	    
//	    params.put(parameterFR.SMS_APIKEY, dictMap.get("APIKEY").getDictDesc());
//	    log.info("模版:======" + content);
//	    log.info("验证码:======" + verifiCode);
//	    try {
//			params.put(parameterFR.SMS_TEXT, URLEncoder.encode(content, "UTF-8"));
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	    params.put(parameterFR.SMS_MOBILE, mobile);
//	    try {
//	    	str = HttpUtils.sendPost(parameterFR.SMS_URL, params);
//	    	return str;
//		} catch (Exception e) {
//			log.info(e.getMessage());
//			return str;
//		}
//	}
}