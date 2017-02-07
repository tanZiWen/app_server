package com.prosnav.dao;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.prosnav.dao.impl.BaseDAOImpl;
import com.prosnav.model.TempCust;
import com.prosnav.utils.LogUtils;

@Service
public class MapperTempCustDAO {	
	@Resource(name = "baseDAOImpl")
	private BaseDAOImpl baseDAO;
	
	private static Logger log = LoggerFactory.getLogger(MapperTempCustDAO.class);
	
	@SuppressWarnings("unchecked")
	public List<TempCust> findTempCustByJson(TempCust tempCust){
		List<TempCust> list = null;
		try {
			list = (List<TempCust>) this.baseDAO.findForList("mapperTempCust.TempCustMapper.findTempCustByJson", tempCust);
			return list;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return list;
		}
	}
}