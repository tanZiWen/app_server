package com.prosnav.controller;

import java.util.HashMap;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.prosnav.model.TDict;
import com.prosnav.service.impl.SysService;
import com.prosnav.service.impl.UserService;
import com.prosnav.utils.ParameterFR;
import com.prosnav.utils.StateCode;

@Controller
@RequestMapping("/app")
public class SysControl {

	@Resource
	private ParameterFR parameterFR;
//	@Resource
//	private MessageFR messageFR;
	
	@Resource
	public UserService userService;
	
	@Resource
	private SysService sysService;
	
	private static Logger log = LoggerFactory.getLogger(SysControl.class);
	
	/**
	 * 注册,申请验证码
	 * */	
	@RequestMapping(value = "/registerSMS", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public ResponseEntity<String> registerSMS(@RequestParam String phone)
			throws Exception {
		
		log.info("===============================================注册,申请验证码===============================================");
		log.info("mobile======" + phone + "======");
		JSONObject result = sysService.sendSMS(phone, "register_v");
		log.info("======" + result.toString()  + "======");
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
	}
	
	/**
	 * 找回密码,申请验证码
	 * */	
	@RequestMapping(value = "/findPwdSMS", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public ResponseEntity<String> findPwdSMS(@RequestParam String phone, @RequestParam String token)
			throws Exception {
		
		log.info("===============================================找回密码,申请验证码===============================================");
		log.info("loginname======" + phone + "======");
		log.info("token======" + token + "======");
		JSONObject result = sysService.sendSMS(phone, "pwdfind_v");
		log.info("======" + result.toString()  + "======");
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
	}
	
	/**
	 * 验证码校验
	 * */
	@RequestMapping(value = "/verifySMS", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public ResponseEntity<String> verifySMS(@RequestParam String phone, @RequestParam String smscode)
			throws Exception {
		
		log.info("===============================================验证码校验===============================================");
		log.info("loginname======" + phone + "======");
		log.info("token======" + smscode + "======");		
		JSONObject result = sysService.verifySMS(phone ,smscode);
		log.info("======" + result.toString()  + "======");
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
	}
	
	/**
	 * 找回，修改密码
	 * */	
	@RequestMapping(value = "/findModifyUserByPwd", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResponseEntity<String> findModifyUserByPwd(@RequestBody String json) throws Exception {
		
		log.info("===============================================找回，修改密码===============================================");
		HashMap<String, TDict> dictMap = sysService.findDictMap();
		log.info("======" + json + "======");
		JSONObject result =  new JSONObject();
		try {			
			JSONObject jsonObject = JSONObject.fromObject(json);			
			result = userService.modifyUserByPwdSms(jsonObject, dictMap);
		} catch (Exception e) {
			result.put(parameterFR.DESC, dictMap.get("ERROR_JSON").getDictDesc().toString());
			result.put(parameterFR.STATE, StateCode.str_801);
		}		
		log.info("======" + result.toString() + "======");
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);		
	}
}
