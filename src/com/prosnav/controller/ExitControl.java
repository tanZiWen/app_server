package com.prosnav.controller;

import java.util.HashMap;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.prosnav.model.TDict;
import com.prosnav.service.impl.LoginService;
import com.prosnav.service.impl.SysService;
import com.prosnav.utils.ParameterFR;

@Controller
@RequestMapping("/app")
public class ExitControl {

	@Resource
	public LoginService loginService;
	@Resource
	private ParameterFR parameterFR;
//	@Resource
//	private MessageFR messageFR;
	
	@Resource
	private SysService sysService;
	
	private static Logger log = LoggerFactory.getLogger(ExitControl.class);
	
	/**
	 * 退出
	 * */	
	@RequestMapping(value = "/exit", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public ResponseEntity<String> exit(@RequestParam String loginname)
			throws Exception {
		
		log.info("===============================================退出===============================================");
		HashMap<String, TDict> dictMap = sysService.findDictMap();
		log.info("loginname======" + loginname + "======");
		JSONObject result = loginService.exit(loginname, dictMap);
		log.info("======" + result.toString()  + "======");
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
	}
}