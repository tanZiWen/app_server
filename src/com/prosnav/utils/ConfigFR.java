package com.prosnav.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigFR {

	@Value("${APIKEY}")
	public String APIKEY = "";
	
	@Value("${SMS_CONTENT}")
	public String SMS_CONTENT = "";
}