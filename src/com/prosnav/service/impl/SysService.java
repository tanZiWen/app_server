package com.prosnav.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.prosnav.dao.MapperTAnswerDAO;
import com.prosnav.dao.MapperTDictDAO;
import com.prosnav.dao.MapperTVerifyLogDAO;
import com.prosnav.dao.MapperTempCustDAO;
import com.prosnav.dao.MapperTokenDAO;
import com.prosnav.dao.MapperUserDAO;
//import com.prosnav.dao.gindb.MapperAssessmentDAO;
import com.prosnav.model.Cust;
import com.prosnav.model.TAnswer;
import com.prosnav.model.TDict;
import com.prosnav.model.TToken;
import com.prosnav.model.TUser;
import com.prosnav.model.TVerifyLog;
import com.prosnav.model.TempCust;
import com.prosnav.service.ISysService;
import com.prosnav.utils.Base64FR;
import com.prosnav.utils.ErrorDB;
import com.prosnav.utils.MD5Util;
import com.prosnav.utils.ParameterFR;
import com.prosnav.utils.StateCode;
import com.prosnav.utils.StringUtilFR;
import com.prosnav.utils.TimeFR;
import com.prosnav.utils.http.HttpUtils;

@Service
public class SysService implements ISysService{
		
	@Resource
	private MapperTDictDAO mapperTDictDAO;
	@Resource
	private MapperTAnswerDAO mapperTAnswerDAO;
	@Resource
	private ParameterFR parameterFR;
	@Resource
	private MapperUserDAO mapperUserDAO;
	@Resource
	private MapperTVerifyLogDAO mapperTVerifyLogDAO;
	@Resource
	private MapperTokenDAO mapperTokenDAO;
	@Resource
	private MapperTempCustDAO mapperTempCustDAO;
	private static Logger log = LoggerFactory.getLogger(SysService.class);
	
	public HashMap<String, TDict>findDictMap(){
		List<String> wList = new ArrayList<String>();
		wList.add("MOBILE_TYPE");
		wList.add("USER_TYPE");
		wList.add("MESSAGE_TYPE");
		wList.add("RISK_LEVEL");
		wList.add("SMS_TYPE");
		wList.add("VERIFY_TYPE");
		wList.add("TOKEN_TYPE");
		wList.add("SERVER_TYPE");
		wList.add("USER_LEVEL");		
		
		Map<String, Object> params = new HashMap<String, Object>();		
		params.put("list", wList);
		HashMap<String, TDict> dictMap = mapperTDictDAO.findDictByType(params);
		
		return dictMap;
	}	
	
	public TDict findDictRiskList(int score){
		List<String> wList = new ArrayList<String>();
		wList.add("RISK_LEVEL");
		
		Map<String, Object> params = new HashMap<String, Object>();		
		params.put("list", wList);
		List<TDict> list = mapperTDictDAO.findDictByTypeRisk(params);
				
		for (TDict tDict : list) {
			if(null != tDict.getDictFomula()){
				if(StringUtilFR.getIsScore(tDict.getDictFomula(), score)){					
					return tDict;
				}
			}			
		}
		
		return null;
	}
	
	@SuppressWarnings("static-access")
	public int findScore(String phone, String idCard){		
		TAnswer answer = new TAnswer();
		answer.setPhone(phone);
		answer.setIdCard(idCard);
		answer.setAnswerDictIsDel(false);
		List<TAnswer> list = mapperTAnswerDAO.findAnswerByUser(answer);
		if(null != list){		
			if(0 == list.size()){
				return -1;
			}else{
				JSONArray array = new JSONArray().fromObject(list.get(0).getAnswerJson());				
				return Integer.parseInt(new JSONObject().fromObject(array.get(array.size() - 1)).getString("score"));
			}			
						
		}else{
			return -1;
		}
	}

	@Override
	public JSONObject sendSMS(String phone, String type) {
		phone = Base64FR.getBase64(phone);
		HashMap<String, TDict> dictMap = this.findDictMap();
		String verifiCode = String.valueOf(StringUtilFR.getVerifyCode());			
	    Map<String, String> params = new HashMap<String, String>();
	    String content = dictMap.get("SMS_CONTENT").getDictDesc();
    	content = content.replace("#code#", verifiCode);
    	JSONObject result = new JSONObject();    	
    	HashMap<String, String> map = null;
    	
		TVerifyLog verify = new TVerifyLog();
		verify.setPhone(phone);
		verify.setVerifyState(dictMap.get("VERIFY_SUCCESS").getDictId());
		
//		TVerifyLog verifyLogHi = mapperTVerifyLogDAO.findVerifyByUserId(verify);
//		if(verifyLogHi != null){
//			if(new Date().getTime() < verifyLogHi.getVerifyFailureTime().getTime()){
//				//频繁操作验证码，5分钟有效时间
//				result.put(parameterFR.DESC, dictMap.get("VERIFY_ERROR_TIME").getDictDesc());
//		    	result.put(parameterFR.STATE, StateCode.str_7102);
//		    	return result;
//			}
//		}
		
		//验证码发送
		TVerifyLog verifyLog = new TVerifyLog();
		verifyLog.setPhone(phone);
		Date date = new Date();//获取当前时间    
		Calendar calendar = Calendar.getInstance();    
		calendar.setTime(date);    
		calendar.add(Calendar.MINUTE, + Integer.parseInt(dictMap.get("VERIFY_MINUTE_TIME").getDictDesc()));//验证码有效时间5分钟
		verifyLog.setVerifyCreateTime(date);
		verifyLog.setVerifyFailureTime(calendar.getTime());
		if("register_v".equalsIgnoreCase(type)){
			verifyLog.setVerifyDictType(dictMap.get(type).getDictId());
		}else if("pwdfind_v".equalsIgnoreCase(type)){
			verifyLog.setVerifyDictType(dictMap.get(type).getDictId());
		}			
					
		verifyLog.setVerifyId(UUID.randomUUID().toString());
		
		params.put(parameterFR.SMS_APIKEY, dictMap.get("APIKEY").getDictDesc());
		log.info("模版:======" + content);
		log.info("验证码:======" + verifiCode);
		try {
			params.put(parameterFR.SMS_TEXT, URLEncoder.encode(content, "UTF-8"));
			params.put(parameterFR.SMS_MOBILE, Base64FR.getFromBase64(phone));
		} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}	 	    	
	    try {
	    	map = HttpUtils.sendPost(parameterFR.SMS_URL, params);
	    	JSONObject json =  JSONObject.fromObject(map.get("message"));
	    	if("200".equalsIgnoreCase(map.get("code").toString())){
	    		log.info(dictMap.get("VERIFY_SUCCESS").getDictDesc());
	    		verifyLog.setResponseContent(map.get("message").toString());
		    	verifyLog.setVerifyState(dictMap.get("VERIFY_SUCCESS").getDictId());
		    	verifyLog.setVerifyCode(String.valueOf(verifiCode));			    	
		    	
		    	result.put(parameterFR.DESC, json.get("detail"));
		    	result.put(parameterFR.SMS_CODE, verifiCode);
		    	result.put(parameterFR.STATE, StateCode.str_7100);
	    	}else{
	    		log.info(dictMap.get("VERIFY_ERROR").getDictDesc());
	    		verifyLog.setResponseContent(map.get("message").toString());		    		
	    		verifyLog.setVerifyState(dictMap.get("VERIFY_ERROR").getDictId());
	    		
		    	result.put(parameterFR.DESC, json.get("detail"));
		    	result.put(parameterFR.SMS_CODE, "");
		    	result.put(parameterFR.STATE, StateCode.str_7101);
	    	}
		} catch (Exception e) {
			log.info(e.getMessage());
			log.info(dictMap.get("VERIFY_ERROR").getDictDesc());
    		verifyLog.setResponseContent(e.getMessage());
    		verifyLog.setVerifyState(dictMap.get("VERIFY_ERROR").getDictId());
    		
	    	result.put(parameterFR.DESC, dictMap.get("VERIFY_ERROR").getDictDesc());
	    	result.put(parameterFR.SMS_CODE, "");
	    	result.put(parameterFR.STATE, StateCode.str_7101);
		}		    
	   
	    mapperTVerifyLogDAO.save(verifyLog);		    	    
		return result;
	}
	
	@Override
	public JSONObject verifySMS(String phone, String verifyCode) {
		phone = Base64FR.getBase64(phone);
		HashMap<String, TDict> dictMap = this.findDictMap();
		JSONObject result = new JSONObject();
		TVerifyLog verifyLog = new TVerifyLog();
		verifyLog.setPhone(phone);
		verifyLog.setVerifyCode(verifyCode);
		
		verifyLog = mapperTVerifyLogDAO.findVerifyByUserIdAndCode(verifyLog);
		if(null != verifyLog){
			TVerifyLog verify = new TVerifyLog();
			verify.setPhone(phone);
			verify.setVerifyState(dictMap.get("VERIFY_SUCCESS").getDictId());
			
			if(verifyLog != null){
				if(new Date().getTime() < verifyLog.getVerifyFailureTime().getTime()){
			    	result.put(parameterFR.DESC, dictMap.get("IS_VERIFY_SUCCESS").getDictDesc());
			    	result.put(parameterFR.STATE, StateCode.str_7103);			    	
				}else{
					//======================================频繁操作验证码，5分钟有效时间======================================
					result.put(parameterFR.DESC, dictMap.get("VERIFY_ERROR_VR_TIME").getDictDesc());
			    	result.put(parameterFR.STATE, StateCode.str_7105);			    	
				}
			}
			
		}else{
			result.put(parameterFR.DESC, dictMap.get("IS_VERIFY_ERROR").getDictDesc());
	    	result.put(parameterFR.STATE, StateCode.str_7104);	    	
		}
		
		return result;
	}

	@Override
	public String getToken(String loginName, String tokenId) {
		JSONObject result = new JSONObject();
		HashMap<String, TDict> dictMap = this.findDictMap();
		TUser user = mapperUserDAO.findLoginNameForUser(loginName);
		
		log.info("=======================token刷新=======================");
		String token = MD5Util.string2MD5(loginName + TimeFR.getCurrTime());
		TToken tokenEn = new TToken();
		tokenEn.setNewTokenId(token);
		tokenEn.setTokenId(tokenId);
		Date date = new Date();//获取当前时间 
		Calendar calendar = Calendar.getInstance();    
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, + Integer.parseInt(dictMap.get("TOKEN_TIME_MINUTE").getDictDesc()));
		tokenEn.setValidTime(date);
		tokenEn.setFailureTime(calendar.getTime());
		tokenEn.setUpdateTime(date);
		tokenEn.setUser(user);
		
		int count = mapperTokenDAO.update(tokenEn);
		if(-1 == count){
			//DB存储失败
			ErrorDB.errorInDB(result, dictMap, parameterFR);
			return "";
		}
		
		return token;			
	}

	@Override
	public boolean syncRiskDataAll() {
//		File fileName = new File("d:/table.txt");
//		PrintWriter outFile = null;
//		try {
//			outFile = new PrintWriter(fileName);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
		boolean isBool = false;
//		List<Assessment> assessmentList = mapperAssessmentDAO.findInitData();
//		for (int b = 0; b < assessmentList.size(); b++) {
//			String issu = "[    {        'issuename': '1. 请问您的年龄为：',        'answer': [            'A. 18岁以下$$0',            'B. 18-30岁$$5',            'C. 31-50岁$$6',            'D. 51-65岁$$3',			'E. 高于65岁$$0'        ]    },    {        'issuename': '2. 请问您的学历是：',        'answer': [            'A. 高中及以下$$0',            'B. 中专或大专$$3',            'C. 本科$$5',            'D. 硕士及以上$$6'        ]    },{        'issuename': '3. 请问您的职业为：',        'answer': [            'A. 无固定职业$$1',            'B. 专业技术人员$$3',            'C. 一般企事业单位员工$$4',            'D. 金融行业一般从业人员$$6'			        ]    },{        'issuename': '4. 您的家庭可支配年收入为(折合人民币)：',        'answer': [            'A. 50万元以下$$1',            'B. 50-100万元$$2',            'C. 100-500万元$$3',            'D. 500-1000万元$$5',			'E. 1000万元以上$$6'        ]    },{        'issuename': '5. 在您每年的家庭可支配收入中，可用于金融投资(储蓄存款除外)的比例为：',        'answer': [            'A. 50%以上$$5',            'B. 25%-50%$$6',            'C. 10%-25%$$4',            'D. 10%以下$$1'        ]    },{        'issuename': '6. 您的投资知识可描述为：',        'answer': [            'A. 有限：基本没有金融方面的知识$$1',            'B. 一般：对金融产品及其相关风险具有基本的知识和理解$$4',            'C. 丰富：对金融产品及其相关风险具有丰富的知识和理解$$6'        ]    },{        'issuename': '7. 您的投资经验可描述为：',        'answer': [            'A. 除银行储蓄外，基本没有其他投资经验$$1',            'B. 购买过债券、保险等理财产品$$3',            'C. 参与过股票、基金等产品的交易$$5',            'D. 参与过权证、期货、期权等产品的交易$$6'        ]    },{        'issuename': '8. 您有多少年投资基金、股票、信托、私募证券或金融衍生产品等风险投资品的经验?',        'answer': [            'A. 没有经验$$1',            'B. 少于2年$$2',            'C. 2年-5年$$3',            'D. 5年-10年$$5',			'E. 10年以上$$6'        ]    },{        'issuename': '9. 您计划的投资期限是多久?',        'answer': [            'A. 1年以下$$0',            'B. 1年-3年$$3',            'C. 3年-5年$$5',            'D. 5年以上$$6'        ]    },{        'issuename': '10. 您的投资目的是?',        'answer': [            'A. 资产保值$$0',            'B. 资产稳健增长$$4',            'C. 资产迅速增长$$5'        ]    },{        'issuename': '11. 以下哪项描述最符合您的投资态度?',        'answer': [            'A. 厌恶风险，不希望本金损失，希望获得稳定回报$$0',            'B. 保守投资，不希望本金损失，愿意承担一定幅度的收益波动$$2',            'C. 寻求资金的较高收益和成长性，愿意为此承担有限本金损失$$5',            'D. 希望赚取高回报，愿意为此承担较大本金损失$$6'        ]    },{        'issuename': '12. 假设有两种投资：投资A预期获得10%的收益，可能承担的损失非常小；投资B预期获得30%的收益，但可能承担较大亏损。您会怎么支配您的投资？',        'answer': [            'A. 全部投资于收益较小且风险较小的A$$1',            'B. 同时投资于A和B，但大部分资金投资于收益较小且风险较小的A$$2',            'C. 同时投资于A和B，但大部分资金投资于收益较大且风险较大的B$$4',            'D. 全部投资于收益较大且风险较大的B$$5'			        ]    },{        'issuename': '13. 您认为自己能承受的最大投资损失是多少?',        'answer': [            'A. 10%以内$$1',            'B. 10%-30%$$3',            'C. 30%-50%$$4',            'D. 50%以上$$5'			        ]    },{        'issuename': '14. 进行等于或大于人民币一百万的投资后，您的主要的感受是：',        'answer': [            'A. 很高兴，对自己的决定很有信心$$5',            'B. 轻松，基本持乐观态度$$4',            'C. 基本没什么影响$$3',            'D. 比较担心投资结果$$2',			'E. 非常担心投资结果$$0'        ]    },{        'issuename': '15. 如果您需要把大量现金整天携带在身，您是否会感到：',        'answer': [            'A. 非常焦虑$$0',            'B. 有点焦虑$$3',            'C. 完全不会焦虑$$5'        ]    },{        'issuename': '16. 当您独自到外地游玩，遇到三岔路口，您会选择：',        'answer': [            'A. 仔细研究地图和路标$$5',            'B. 找别人问路$$4',            'C. 大致判断一下方向$$3',            'D. 也许会用掷骰子的方式决定$$1'			        ]    },{        'issuename': '17. 假如您前期用25元购入一支股票，该股票现在升到30元，而根据预测该股近期有一半机会升到35元，另一半机会跌到25元，您现在会：',        'answer': [            'A. 立刻卖出$$1',            'B. 部分卖出$$2',            'C. 继续持有$$4',            'D. 继续买入$$5'			        ]    },{        'issuename': '18. 同上题情况，该股现在已经跌到20元，而您估计该股近期有一半机会升回25元，另一半机会继续下跌到15元，您现在会：',        'answer': [            'A. 立刻卖出$$1',            'B. 部分卖出$$2',            'C. 继续持有$$4',            'D. 继续买入$$5'			        ]    }]";
//			JSONArray array = JSONArray.fromObject(issu);
//			int count = 0;
//			
//			for (int i = 0; i < array.size(); i++) {
//				count = 0;
//				JSONObject value = JSONObject.fromObject(array.get(i));
//				JSONArray list = JSONArray.fromObject(value.get("answer"));
//				for (int g = 0; g < list.size(); g++) {
//					String key = list.get(g).toString().split(". ")[0];
//					String score = list.get(g).toString().replace("$$", ",,,,").split(",,,,")[1];
//					String[] arr = assessmentList.get(b).getConcat().replace("||", ",,,,").split(",,,,");
//					
//					for (int j = 0; j < arr.length; j++) {
//						if(StringUtils.isBlank(arr[i].toString())){
//							count = 1;
//							break;
//						}
//						if(key.equalsIgnoreCase(arr[i])){
//							//找到对应答案
//							((JSONObject) array.get(i)).put("item", score);
//							
//							count = 1;
//							break;
//						}
//					}
//					if(count == 1){
//						break;
//					}
//				}
//			}
//			
//			int scoreCount = -1;
//			
//			String scores = assessmentList.get(b).getConcat().toString().replace("||", ",,,,").split(",,,,")[assessmentList.get(b).getConcat().toString().replace("||", ",,,,").split(",,,,").length - 1];
//			JSONObject score = new JSONObject();
//			score.put("score", scores);
//			array.add(score);	
//			
//			String email = "";
//			if(StringUtils.isNotBlank(assessmentList.get(b).getEmail())){
//				email = assessmentList.get(b).getEmail();
//			}
//			String dictDictValue = "";
//			scoreCount = this.findScore(Base64FR.getBase64(assessmentList.get(b).getMobile()), "身份证ID号");
//			
//			if(-1 != scoreCount){
//				TDict dict = this.findDictRiskList(Integer.parseInt(scores));
//				dictDictValue = dict.getDictValue();
//			}
//			
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
//			Date dates;
//			try {
//				dates = sdf.parse(assessmentList.get(b).getCrt());
//				Date date = dates;//获取当前时间 
//				Calendar calendar = Calendar.getInstance();    
//				calendar.setTime(date);    
//				calendar.add(Calendar.YEAR, + 3);		
//				
//				String outStr = " insert into t_answer (answer_id,issue_id,answer_json,answer_create_time,ANSWER_DUE_TIME,answer_dict_is_del,answer_level, phone, email, card_type,id_card,data_source) values " + 
//						"('" + UUID.randomUUID() + "'," + "'111'," + "'" + array.toString() + "'," 
//						+ "str_to_date('" + assessmentList.get(b).getCrt() + "','%Y-%m-%d %T'), str_to_date('" + StringUtilFR.getTime(calendar.getTime()) + "','%Y-%m-%d %T')" + ",'0'," + "'" + dictDictValue + "'" + ",'" 
//						+ Base64FR.getBase64(assessmentList.get(b).getMobile()) + "','" + email + "','','','wechat');\r\n";
//						
//						
//								outFile.write(outStr);
//				
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		
//		}
//		outFile.flush();
//		outFile.close();
//		isBool = true;
		return isBool;
	}

	@Override
	public JSONObject syncRiskDataForObject(JSONObject json) {
		return null;
	}

	@SuppressWarnings("static-access")
	@Override
	public Cust findTempCustByJsonForObj(String phone, String idCard) {
		phone = Base64FR.getFromBase64(phone);
		idCard = Base64FR.getFromBase64(idCard);
		TempCust tempCust = new TempCust();
		tempCust.setPhone(phone);
		tempCust.setIdCard(idCard);
		List<TempCust> list = mapperTempCustDAO.findTempCustByJson(tempCust);
		if(0 != list.size()){
			JSONObject obj = new JSONObject().fromObject(list.get(0).getCustJson());
	        Cust cust = (Cust)JSONObject.toBean(obj,Cust.class);
	        return cust;
		}
		
		return null;
	}
	
	@Override
	public Boolean isTokenNo(String loginName, String tokenID) {
		loginName = Base64FR.getBase64(loginName);
		TToken tokenEn = new TToken();
		TUser user = new TUser();
		tokenEn.setTokenId(tokenID);
		user.setLoginName(loginName);
		tokenEn.setUser(user);		
		List<TToken> List = mapperTokenDAO.findToken(tokenEn);		
		if(0 != List.size()){
			return true;
		}
		
		return false;
	}
	
	@Override
	public Boolean isToken(String loginName, String tokenID) {
		TToken tokens = new TToken();
		tokens.setTokenId(tokenID);
		tokens.setTokenDictIsDel(false);
		tokens.setCurrTime(TimeFR.getCurrTime());
		TUser user = new TUser();
		user.setLoginName(loginName);
		tokens.setUser(user);
		
		List<TToken> list = null;
		
		list = mapperTokenDAO.getTokenAndUser(tokens);
		if(0 != list.size()){
			return true;
		}
		
		return false;
	}
}