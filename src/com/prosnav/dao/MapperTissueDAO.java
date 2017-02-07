package com.prosnav.dao;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.prosnav.dao.impl.BaseDAOImpl;
import com.prosnav.model.TIssue;
import com.prosnav.utils.LogUtils;

@Service
public class MapperTissueDAO {

	private static Logger log = LoggerFactory.getLogger(MapperTissueDAO.class);
	
	@Resource(name = "baseDAOImpl")
	private BaseDAOImpl baseDAO;
	
	@SuppressWarnings("unchecked")
	public List<TIssue> findIssueForList(){		
		List<TIssue> list = null;		
		try {
			list = (List<TIssue>) this.baseDAO.findForList("mapperTissue.TissueMapper.findIssueForList",null);
			return list;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return list;
		}
	}
}
