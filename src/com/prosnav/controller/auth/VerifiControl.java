package com.prosnav.controller.auth;

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
import com.prosnav.service.impl.SysService;
import com.prosnav.service.impl.UserService;
//import com.prosnav.utils.MessageFR;
import com.prosnav.utils.ParameterFR;
import com.prosnav.utils.StateCode;

@Controller
@RequestMapping("/app/auth")
public class VerifiControl {

	@Resource
	private ParameterFR parameterFR;
	
	@Resource
	public UserService userService;
	
	@Resource
	private SysService sysService;
	
	private static Logger log = LoggerFactory.getLogger(VerifiControl.class);
	
	/**
	 * TOKEN不匹配
	 * */	
	@RequestMapping(value = "/overdueNo", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public ResponseEntity<String> overdueNo(@RequestBody String json, HttpServletRequest request) throws Exception {
		
		log.info("===============================================TOKEN不匹配===============================================");
		HashMap<String, TDict> dictMap = sysService.findDictMap();
		JSONObject result =  new JSONObject();
		result.put(parameterFR.DESC, dictMap.get("TOKEN_IS_NO").getDictDesc());
		result.put(parameterFR.STATE, StateCode.str_997);
		
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
	}
	
	/**
	 * TOKEN过期
	 * */	
	@RequestMapping(value = "/overdue", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public ResponseEntity<String> overdue(@RequestBody String json, HttpServletRequest request) throws Exception {
		
		log.info("===============================================TOKEN过期===============================================");
		HashMap<String, TDict> dictMap = sysService.findDictMap();
		JSONObject result =  new JSONObject();
		result.put(parameterFR.DESC, dictMap.get("TOKEN_END").getDictDesc());
		result.put(parameterFR.STATE, StateCode.str_999);
		
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
	}
	
	/**
	 * 参数错误
	 * */
	@RequestMapping(value = "/errorTokenLoginName", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public ResponseEntity<String> errorTokenLoginName(@RequestBody String json) throws Exception {
		
		log.info("===============================================参数错误===============================================");
		HashMap<String, TDict> dictMap = sysService.findDictMap();
		JSONObject result =  new JSONObject();
		result.put(parameterFR.DESC, dictMap.get("ERROR_PARAMETER_IS_NULL").getDictDesc());
		result.put(parameterFR.STATE, StateCode.str_998);
		
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
	}
}