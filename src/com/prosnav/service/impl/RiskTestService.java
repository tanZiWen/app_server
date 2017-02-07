package com.prosnav.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.prosnav.dao.MapperTAnswerDAO;
import com.prosnav.dao.MapperTissueDAO;
import com.prosnav.dao.MapperUserDAO;
import com.prosnav.model.TAnswer;
import com.prosnav.model.TDict;
import com.prosnav.model.TIssue;
import com.prosnav.model.TUser;
import com.prosnav.model.mobile.Issue;
import com.prosnav.model.mobile.RiskTest;
import com.prosnav.service.IRiskTestService;
import com.prosnav.utils.Base64FR;
import com.prosnav.utils.ErrorDB;
import com.prosnav.utils.ParameterFR;
import com.prosnav.utils.StateCode;

@Controller
public class RiskTestService implements IRiskTestService{

	@Resource
	private MapperTissueDAO mapperTissueDAO;	
	@Resource
	private ParameterFR parameterFR;	
	@Resource
	private MapperTAnswerDAO mapperTAnswerDAO;	
	@Resource
	private SysService sysService;	
	@Resource 
	private MapperUserDAO mapperUserDAO;	
	private static Logger log = LoggerFactory.getLogger(RiskTestService.class);
	
	@SuppressWarnings("static-access")
	@Override
	public JSONArray findIssue() {
		
		List<TIssue> list = mapperTissueDAO.findIssueForList();
		if(null != list){
			TIssue issue = list.get(0);
			JSONArray array = new JSONArray().fromObject(issue.getIssueDictJson());
	        return array;	        
		}
		
		return null;
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public JSONObject saveAnwer(JSONObject json, HashMap<String, TDict> dictMap) {
		JSONObject result = new JSONObject();
		int count = 0;
		int score = -1;
		
		if(StringUtils.isBlank(json.get(parameterFR.JSON).toString())
				||StringUtils.isBlank(json.get(parameterFR.LOGIN_NAME).toString())){
			
			log.info(dictMap.get("VALUE_IS_NULL").getDictDesc());
			result.put(parameterFR.DESC, dictMap.get("VALUE_IS_NULL").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_800);
			return result;
		}
		
		String loginName = json.get(parameterFR.LOGIN_NAME).toString();
//		String loginName = "18320866362";
		loginName = Base64FR.getBase64(loginName);
		String data = json.get(parameterFR.JSON).toString();
		data = Base64FR.getFromBase64(data);
		
		//===============================测试=================================
//		String data = "";
//		List<TAnswer> answerList = mapperTAnswerDAO.findAnswerByUser(answer1);
//		if(null != answerList){
//			data = mapperTAnswerDAO.findAnswerByUser(answer1).get(0).getAnswerJson();
//		}
		//============================正式========================================				
		TAnswer answer1 = new TAnswer();
		answer1.setPhone(loginName);
		answer1.setAnswerDictIsDel(false);
		
		JSONArray array = new JSONArray().fromObject(data);
		ArrayList<Issue> issueArray = (ArrayList<Issue>)JSONArray.toList(array,Issue.class);
		
		List<TIssue> list = mapperTissueDAO.findIssueForList();
		
		TAnswer answer = new TAnswer();
		TIssue tIssue = new TIssue();
		answer.setAnswerId(UUID.randomUUID().toString());
		tIssue.setIssueId(list.get(0).getIssueId());
		answer.setIssue(tIssue);
		
		Date date = new Date();//获取当前时间 
		Calendar calendar = Calendar.getInstance();    
		calendar.setTime(date);    
		calendar.add(Calendar.YEAR, + Integer.parseInt(dictMap.get("VERIFY_YEAR_TIME").getDictDesc()));
		
		answer.setAnswerJson(data);
		answer.setAnswerCreateTime(date);
		answer.setAnswerDueTime(calendar.getTime());
		answer.setAnswerDictIsDel(false);
		score = Integer.parseInt(issueArray.get(issueArray.size() -1 ).getScore());
		RiskTest riskTest = new RiskTest();
		if(-1 != score){
			TDict dict = sysService.findDictRiskList(score);
			answer.setAnswerLevel(dict.getDictValue());
			riskTest.setRisklevelValue(dict.getDictValue());
			riskTest.setRisklevelDesc(dict.getDictDesc());
		}else{
			answer.setAnswerLevel("");			
		}
		
		TUser user = mapperUserDAO.findLoginNameForUser(loginName);		
		if(null != user){			
			answer.setEmail(user.getEmail());
			answer.setCardType(user.getCardType());
			answer.setIdCard(user.getIdCard());
		}		
		answer.setPhone(loginName);		
		answer.setDataSource("app");
		
		count = mapperTAnswerDAO.save(answer);			
		if(-1 == count){
			//DB存储失败
			ErrorDB.errorInDB(result, dictMap, parameterFR);
			return result;
		}
		
		if(0 == count){
			log.info(dictMap.get("RISK_SAVE_ERROR").getDictDesc());
			result.put(parameterFR.DESC, dictMap.get("RISK_SAVE_ERROR").getDictDesc());
			result.put(parameterFR.STATE, StateCode.str_400);
			return result;
		}else{
			result.put(parameterFR.JSON, riskTest);
			result.put(parameterFR.STATE, StateCode.str_200);
			return result;
		}
	}
}