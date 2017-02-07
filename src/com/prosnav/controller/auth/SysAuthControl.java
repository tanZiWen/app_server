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

import com.prosnav.auth.AuthPassport;
import com.prosnav.model.TDict;
import com.prosnav.service.impl.SysService;
import com.prosnav.service.impl.UserService;
//import com.prosnav.utils.MessageFR;
import com.prosnav.utils.ParameterFR;
import com.prosnav.utils.StateCode;
import com.prosnav.utils.StringUtilFR;

@Controller
@RequestMapping("/app/auth")
public class SysAuthControl {

	@Resource
	private ParameterFR parameterFR;
	
	@Resource
	public UserService userService;
	
	@Resource
	private SysService sysService;
	
	private static Logger log = LoggerFactory.getLogger(SysAuthControl.class);
	
	/**
	 * 修改密码
	 * */
	@AuthPassport
	@RequestMapping(value = "/modifyUserByPwd", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResponseEntity<String> modifyUserByPwd(@RequestBody String json, HttpServletRequest request) throws Exception {
		
		log.info("===============================================修改密码===============================================");
		HashMap<String, TDict> dictMap = sysService.findDictMap();
		json = request.getAttribute(parameterFR.JSON).toString();
		log.info("======" + json + "======");
		JSONObject result =  new JSONObject();
		try {			
			JSONObject jsonObject = JSONObject.fromObject(json);			
			result = userService.modifyUserByPwd(jsonObject, dictMap);
		} catch (Exception e) {
			result.put(parameterFR.DESC, dictMap.get("ERROR_JSON").getDictDesc().toString());
			result.put(parameterFR.STATE, StateCode.str_801);
		}
		StringUtilFR.addToekn(result, request, parameterFR);
		log.info("======" + result.toString() + "======");
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);		
	}	
}