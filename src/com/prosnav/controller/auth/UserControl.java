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
import org.springframework.web.bind.annotation.RequestParam;

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
public class UserControl {

	@Resource
	private ParameterFR parameterFR;
//	@Resource
//	private MessageFR messageFR;
	
	@Resource
	public UserService userService;
	
	@Resource
	private SysService sysService;
	
	private static Logger log = LoggerFactory.getLogger(UserControl.class);
	
	/**
	 * 修改手机号、邮箱、昵称、血型
	 * */
	@AuthPassport
	@RequestMapping(value = "/modifyUserInfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResponseEntity<String> modifyUserInfo(@RequestBody String json, HttpServletRequest request) throws Exception {
		
		log.info("===============================================修改手机号、邮箱、昵称、血型===============================================");
		HashMap<String, TDict> dictMap = sysService.findDictMap();
		json = request.getAttribute(parameterFR.JSON).toString();
		log.info("======" + json + "======");
		JSONObject result =  new JSONObject();
		try {			
			JSONObject jsonObject = JSONObject.fromObject(json);			
			result = userService.modifyUser(jsonObject, dictMap);
		} catch (Exception e) {
			result.put(parameterFR.DESC, dictMap.get("ERROR_JSON").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_801);
		}
		StringUtilFR.addToekn(result, request, parameterFR);
		log.info("======" + result.toString() + "======");
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);		
	}
	
	/**
	 * 获取用户信息
	 * */
	@AuthPassport
	@RequestMapping(value = "/getUser", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public ResponseEntity<String> getUser(@RequestParam String loginname, @RequestParam String token, @RequestParam String idcard, HttpServletRequest request)
			throws Exception {
		
		log.info("===============================================获取用户信息===============================================");
		HashMap<String, TDict> dictMap = sysService.findDictMap();		
		log.info("loginname======" + loginname + "======");
		log.info("token======" + token + "======");
		JSONObject result = userService.findUser(loginname, idcard, dictMap, false);
		StringUtilFR.addToekn(result, request, parameterFR);
		log.info("======" + result.toString()  + "======");
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
	}
	
	
	/**
	 * 上传头像
	 * */
	@AuthPassport
	@RequestMapping(value = "/uploadIcon", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResponseEntity<String> uploadIcon(@RequestBody String json, HttpServletRequest request) throws Exception {
		
		log.info("===============================================上传头像===============================================");
		HashMap<String, TDict> dictMap = sysService.findDictMap();
		json = request.getAttribute(parameterFR.JSON).toString();
		log.info("======" + json + "======");
		JSONObject result =  new JSONObject();
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);			
			result = userService.uploadIcon(jsonObject, dictMap, StringUtilFR.getLocalTempDirImg(request));
		} catch (Exception e) {
			result.put(parameterFR.DESC, dictMap.get("ERROR_JSON").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_801);
		}
		StringUtilFR.addToekn(result, request, parameterFR);
		log.info("======" + result.toString() + "======");
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);		
	}
}