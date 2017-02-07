package com.prosnav.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SecurityUtil {

	/**
	 * 
	 *  @Description    : 身份验证token值算法：
	 *  				  算法是：将特定的某几个参数一map的数据结构传入，
	 *  				  进行字典序排序以后进行md5加密,32位小写加密；
	 *  @Method_Name    : authentication
	 *  @param token		请求传过来的token
	 *  @param srcData   约定用来计算token的参数
	 *  @return 
	 */
	public static String authentication(Map<String , Object > srcData){
		//排序，根据keyde 字典序排序
		if(null == srcData){
			return null;
		}
		List<Map.Entry<String,Object>> list = new ArrayList<Map.Entry<String,Object>>(srcData.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Object>>(){
			//升序排序
			@Override
			public int compare(Entry<String, Object> arg0, Entry<String, Object> arg1) {
				return arg0.getKey().compareTo(arg1.getKey());
			}
		});
		
		StringBuffer srcSb = new StringBuffer();
		for(Map.Entry<String , Object>srcAtom : list){
			srcSb.append(String.valueOf(srcAtom.getValue()));
		}
//		System.out.println("身份验证加密前字符串："+srcSb.toString());
		//计算token
		String token = MD5Util.md5(srcSb.toString());
//		System.out.println(cToken);//for test
		return token;
	}
	
	public static void main(String[] args) {
		Map<String , Object > map = new HashMap<String, Object>();
		map.put("aaaa", "aaaaaa");
		System.out.println(authentication(map));
		System.out.println(MD5Util.string2MD5("aaaaaa"));
		System.out.println("解密的：" + MD5Util.convertMD5(MD5Util.convertMD5("aaaa")));
	}
	
}