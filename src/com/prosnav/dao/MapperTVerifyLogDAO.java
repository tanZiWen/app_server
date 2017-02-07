package com.prosnav.dao;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.prosnav.dao.impl.BaseDAOImpl;
import com.prosnav.model.TVerifyLog;
import com.prosnav.utils.LogUtils;

@Service
public class MapperTVerifyLogDAO {

private static Logger log = LoggerFactory.getLogger(MapperTVerifyLogDAO.class);
	
	@Resource(name = "baseDAOImpl")
	private BaseDAOImpl baseDAO;
	
	public int save(TVerifyLog verifyLog){
		int count = 0;
		try {
			count = this.baseDAO.save("mapperTVerifyLog.TVerifyLogMapper.save", verifyLog);
			return count;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return -1;
		}
	}
		
	public TVerifyLog findVerifyByUserId(TVerifyLog verifyLog){
		TVerifyLog tVerifyLog = null;
		try {
			tVerifyLog = (TVerifyLog) this.baseDAO.findForObject("mapperTVerifyLog.TVerifyLogMapper.findVerifyByUserId", verifyLog);
			return tVerifyLog;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return tVerifyLog;
		}
	}
	
	public TVerifyLog findVerifyByUserIdAndCode(TVerifyLog verifyLog){
		TVerifyLog tVerifyLog = null;
		try {
			tVerifyLog = (TVerifyLog) this.baseDAO.findForObject("mapperTVerifyLog.TVerifyLogMapper.findVerifyByUserIdAndCode", verifyLog);
			return tVerifyLog;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return tVerifyLog;
		}
	}
}