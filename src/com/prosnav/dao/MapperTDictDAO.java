package com.prosnav.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.prosnav.dao.impl.BaseDAOImpl;
import com.prosnav.model.TDict;
import com.prosnav.utils.LogUtils;

@Service
public class MapperTDictDAO {

	private static Logger log = LoggerFactory.getLogger(MapperTDictDAO.class);
	
	@Resource(name = "baseDAOImpl")
	private BaseDAOImpl baseDAO;
		
	public TDict findDictById(String dictId){
		TDict dict = null;
		try {
			dict = (TDict) this.baseDAO.findForObject("mapperTDict.TDictMapper.findDictById", dictId);
			return dict;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return dict;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public HashMap<String, TDict> findDictByType(Map<String, Object> params){		
		List<TDict> list = null;
		HashMap<String, TDict> map = new HashMap<String, TDict>();
		try {
			list = (List<TDict>) this.baseDAO.findForList("mapperTDict.TDictMapper.findDictByType",params);
			for (TDict tDict : list) {
				map.put(tDict.getDictValue(), tDict);
			}
			
			return map;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TDict> findDictByTypeRisk(Map<String, Object> params){		
		List<TDict> list = null;
		try {
			list = (List<TDict>) this.baseDAO.findForList("mapperTDict.TDictMapper.findDictByType",params);
			return list;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return list;
		}
	}
	
}
