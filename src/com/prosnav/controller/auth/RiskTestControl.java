package com.prosnav.controller.auth;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prosnav.auth.AuthPassport;
import com.prosnav.model.TDict;
import com.prosnav.service.impl.RiskTestService;
import com.prosnav.service.impl.SysService;
import com.prosnav.utils.Base64FR;
import com.prosnav.utils.ParameterFR;
import com.prosnav.utils.StateCode;
import com.prosnav.utils.StringUtilFR;

@Service
@RequestMapping("/app/auth")
public class RiskTestControl {

	private static Logger log = LoggerFactory.getLogger(UserControl.class);
	
	@Resource
	private RiskTestService riskTestService;
	
	@Resource
	private SysService sysService;
	
	@Resource
	private ParameterFR parameterFR;
	
	/**
	 * 查询风险测试题
	 * */
	@AuthPassport
	@RequestMapping(value = "/findRiskTest", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public ResponseEntity<String> getUser(HttpServletRequest request)
			throws Exception {
		log.info("===============================================获取风险测试题===============================================");
		HashMap<String, TDict> dictMap = sysService.findDictMap();
		JSONArray resultArray =  new JSONArray();
		JSONObject result = new JSONObject();
		try {						
			resultArray = riskTestService.findIssue();
			result.put(parameterFR.JSON, Base64FR.getBase64(resultArray.toString()));
			result.put(parameterFR.STATE, StateCode.str_200);
			log.info("BASE64未进行编码======" + resultArray.toString() + "======");
			log.info("======" + result.toString() + "======");
		} catch (Exception e) {
			result.put(parameterFR.DESC, dictMap.get("ERROR_JSON").getDictDesc().toString());
			result.put(parameterFR.STATE, StateCode.str_801);
		}
		StringUtilFR.addToekn(result, request, parameterFR);
		log.info("======" + result.toString() + "======");
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);	
	}
	
	/**
	 * 保存风险测试题
	 * */	
	@AuthPassport
	@RequestMapping(value = "/saveAnwer", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResponseEntity<String> testPostJson(@RequestBody String json, HttpServletRequest request) throws Exception {		
		log.info("===============================================存储用户风险测试结果===============================================");
		HashMap<String, TDict> dictMap = sysService.findDictMap();		
		json = request.getAttribute(parameterFR.JSON).toString();
		log.info("======" + json + "======");
		JSONObject result =  new JSONObject();
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);
			result = riskTestService.saveAnwer(jsonObject, dictMap);
		} catch (Exception e) {
			result.put(parameterFR.DESC, dictMap.get("ERROR_JSON").getDictDesc().toString());
			result.put(parameterFR.STATE, StateCode.str_801);
		}
		StringUtilFR.addToekn(result, request, parameterFR);
		log.info("======" + result.toString() + "======");
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
	}
}