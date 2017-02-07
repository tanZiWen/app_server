package com.prosnav.utils.map;

import com.prosnav.utils.http.HttpRequestor;

public class AmapMapUtils {

	/**
	 * 高德经纬度反查地理位置
	 * */
	public String getAddress(String key, String longitude, String latitude){		
		try {
			String addressInfo = new HttpRequestor().doGet("http://restapi.amap.com/v3/geocode/regeo?"
					+ "key=" + key + "&location=" + longitude + "," + latitude + "&radius=1000&extensions=base&batch=false&roadlevel=1");
			return addressInfo;
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return "";
	}
}
