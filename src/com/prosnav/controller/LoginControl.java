package com.prosnav.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prosnav.model.TDict;
import com.prosnav.service.impl.LoginService;
import com.prosnav.service.impl.SysService;
import com.prosnav.utils.ParameterFR;
import com.prosnav.utils.StateCode;

/**
 * 客户端控制层
 * */
@Controller
@RequestMapping("/app")
public class LoginControl {

	@Resource
	public LoginService loginService;
	@Resource
	private ParameterFR parameterFR;
	@Resource
	private SysService sysService;
	
	private static Logger log = LoggerFactory.getLogger(LoginControl.class);
	
	/**
	 * 登录
	 * */	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResponseEntity<String> testPostJson(@RequestBody String json, HttpServletRequest request) throws Exception {
		
		log.info("===============================================登录===============================================");
		HashMap<String, TDict> dictMap = sysService.findDictMap();		
		log.info("======" + json + "======");
		JSONObject result =  new JSONObject();
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);
			result = loginService.login(jsonObject, dictMap);
		} catch (Exception e) {
			result.put(parameterFR.DESC, dictMap.get("ERROR_JSON").getDictDesc().toString());
			result.put(parameterFR.STATE, StateCode.str_801);
		}		
		log.info("======" + result.toString() + "======");
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
	}
}
