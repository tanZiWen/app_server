package com.prosnav.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParameterFR {

//	public static String LOGIN_NAME = "";
//	public static String LOGIN_PWD = "";
//	public static String TOKEN = "";
//	public static String STATE = "";	
//	public static String USER_NAME = "";
//	public static String NICK_NAME = "";
//	public static String ID_CARD = "";
//	public static String USER_TYPE = "";
//	public static String ICON = "";
//	public static String EMAIL = "";
//	public static String IS_RISK_TEST = "";
//	public static String APPROVE = "";
//	public static String BIRTHDAY = "";
//	public static String CONSTELLATION = "";
//	public static String ZODIAC = "";
//	public static String BLOOD = "";
//	public static String SERVOCETEL = "";
//	public static String DESC = "";
	
	/*
USER_NAME=username
NICK_NAME=nickname
ID_CARD=idcard
USER_TYPE=usertype
ICON=icon
EMAIL=email
IS_RISK_TEST=isrisktest
APPROVE=approve
BIRTHDAY=birthday
CONSTELLATION=constellation
ZODIAC=zodiac
BLOOD=blood
SERVOCETEL=servicetel
	 * */
	@Value("${LOGIN_NAME}")  
	public String LOGIN_NAME = "";
	
	@Value("${LOGIN_PWD}")  
	public String LOGIN_PWD = "";
	
	@Value("${TOKEN}")  
	public String TOKEN = "";
	
	@Value("${STATE}")  
	public String STATE = "";	
	
	@Value("${USER_NAME}")  
	public String USER_NAME = "";
	
	@Value("${NICK_NAME}")  
	public String NICK_NAME = "";
	
	@Value("${ID_CARD}")  
	public String ID_CARD = "";
	
	@Value("${USER_TYPE}")  
	public String USER_TYPE = "";
	
	@Value("${ICON}")  
	public String ICON = "";
	
	@Value("${EMAIL}")  
	public String EMAIL = "";
	
	@Value("${IS_RISK_TEST}")  
	public String IS_RISK_TEST = "";
	
	@Value("${APPROVE}")  
	public String APPROVE = "";
	
	@Value("${BIRTHDAY}")  
	public String BIRTHDAY = "";
	
	@Value("${CONSTELLATION}")  
	public String CONSTELLATION = "";
	
	@Value("${ZODIAC}")  
	public String ZODIAC = "";
	
	@Value("${BLOOD}")  
	public String BLOOD = "";
	
	@Value("${SERVOCETEL}")  
	public String SERVOCETEL = "";
	
	@Value("${DESC}")  
	public String DESC = "";
	
	@Value("${JSON}")  
	public String JSON = "";		
	
	@Value("${SMS_APIKEY}")
	public String SMS_APIKEY = "";
	
	@Value("${SMS_TEXT}")
	public String SMS_TEXT = "";
	
	@Value("${SMS_MOBILE}")
	public String SMS_MOBILE = "";
	
	@Value("${SMS_URL}")
	public String SMS_URL = "";
	
	@Value("${DEVICE_ID}")
	public String DEVICE_ID = "";
	
	@Value("${SYS_V}")
	public String SYS_V = "";
	
	@Value("${MOBILE_TYPE}")
	public String MOBILE_TYPE = "";
	
	@Value("${RISK_LEVEL_VALUE}")
	public String RISK_LEVEL_VALUE = "";
	
	@Value("${RISK_LEVEL_DESC}")
	public String RISK_LEVEL_DESC = "";
	
	@Value("${USER_TYPE_VALUE}")
	public String USER_TYPE_VALUE = "";
	
	@Value("${USER_TYPE_DESC}")
	public String USER_TYPE_DESC = "";
	
	@Value("${USER_SMS}")
	public String USER_SMS = "";
	
	@Value("${USER}")
	public String USER = "";
	
	@Value("${SMS_CODE}")
	public String SMS_CODE = "";
	
	@Value("${VALUE}")
	public String VALUE = "";
	
	@Value("${TYPE}")
	public String TYPE = "";
	
	@Value("${NEW_LOGIN_PWD}")
	public String NEW_LOGIN_PWD = "";
	
	@Value("${ICON_URL}")
	public String ICON_URL = "";
	
	@Value("${ICON_FORMAT}")
	public String ICON_FORMAT = "";
	
	@Value("${ICON_ICON}")
	public String ICON_ICON = "";
		
	@Value("${USER_DEVICE_SYS}")
	public String USER_DEVICE_SYS = "";
	
//	public static void init(SpringPropertyResourceReader springPropertyResourceReader){
//		LOGIN_NAME = springPropertyResourceReader.getProperty("LOGIN_NAME");
//		LOGIN_PWD = springPropertyResourceReader.getProperty("LOGIN_PWD");
//		TOKEN = springPropertyResourceReader.getProperty("TOKEN");
//		STATE = springPropertyResourceReader.getProperty("STATE");		
//		USER_NAME = springPropertyResourceReader.getProperty("USER_NAME");
//		NICK_NAME = springPropertyResourceReader.getProperty("NICK_NAME");
//		ID_CARD = springPropertyResourceReader.getProperty("ID_CARD");
//		USER_TYPE = springPropertyResourceReader.getProperty("USER_TYPE");
//		ICON = springPropertyResourceReader.getProperty("ICON");
//		EMAIL = springPropertyResourceReader.getProperty("EMAIL");
//		IS_RISK_TEST = springPropertyResourceReader.getProperty("IS_RISK_TEST");
//		APPROVE = springPropertyResourceReader.getProperty("APPROVE");
//		BIRTHDAY = springPropertyResourceReader.getProperty("BIRTHDAY");
//		CONSTELLATION = springPropertyResourceReader.getProperty("CONSTELLATION");
//		ZODIAC = springPropertyResourceReader.getProperty("ZODIAC");
//		BLOOD = springPropertyResourceReader.getProperty("BLOOD");
//		SERVOCETEL = springPropertyResourceReader.getProperty("SERVOCETEL");
//		DESC = springPropertyResourceReader.getProperty("DESC");
//	}
}
