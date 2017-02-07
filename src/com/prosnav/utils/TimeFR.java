package com.prosnav.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFR {

	public static String getCurrTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}
}
