package com.prosnav.dao;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.prosnav.dao.impl.BaseDAOImpl;
import com.prosnav.model.TAnswer;
import com.prosnav.utils.LogUtils;

@Service
public class MapperTAnswerDAO {

private static Logger log = LoggerFactory.getLogger(MapperTAnswerDAO.class);
	
	@Resource(name = "baseDAOImpl")
	private BaseDAOImpl baseDAO;
	
	@SuppressWarnings("unchecked")
	public List<TAnswer> findAnswerByUser(TAnswer answer){		
		List<TAnswer> list = null;		
		try {
			list = (List<TAnswer>) this.baseDAO.findForList("mapperTAnswer.TAnswerMapper.findAnswerByUser",answer);
			return list;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return list;
		}
	}
	
	public int save(TAnswer answer){
		int count = 0;
		try {
			count = this.baseDAO.update("mapperTAnswer.TAnswerMapper.save", answer);
			return count;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return -1;
		}
	}
}
