package com.prosnav.dao;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.prosnav.dao.impl.BaseDAOImpl;
import com.prosnav.model.TToken;
import com.prosnav.utils.LogUtils;

@Service
public class MapperTokenDAO {

	private static Logger log = LoggerFactory.getLogger(MapperTokenDAO.class);
	
	@Resource(name = "baseDAOImpl")
	private BaseDAOImpl baseDAO;
	
	public int add(TToken tokenEn){
		int count = 0;
		try {
			count = this.baseDAO.save("mapperToken.TTokenMapper.add", tokenEn);
			return count;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return -1;
		}
	}
	
	public int update(TToken tokenEn){
		int count = 0;
		try {
			count = this.baseDAO.update("mapperToken.TTokenMapper.update", tokenEn);
			return count;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return -1;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TToken> getTokenAndUser(TToken tokenEn){
		List<TToken> list = null;
		try {
			list = (List<TToken>) this.baseDAO.findForList("mapperToken.TTokenMapper.getTokenAndUser", tokenEn);
			return list;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return list;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TToken> findToken(TToken tokenEn){
		List<TToken> list = null;
		try {
			list = (List<TToken>) this.baseDAO.findForList("mapperToken.TTokenMapper.findToken", tokenEn);
			return list;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return list;
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<TToken> getUserAndPwd(TToken tokenEn){
		List<TToken> list = null;
		try {
			list = (List<TToken>) this.baseDAO.findForList("mapperToken.TTokenMapper.getUserAndPwd", tokenEn);
			return list;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return list;
		}
	}
	
	public int exit(TToken tokenEn){
		int count = 0;
		try {
			count = this.baseDAO.update("mapperToken.TTokenMapper.exit", tokenEn);
			return count;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return -1;
		}
	}
}
