//package com.prosnav.dao.gindb;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import com.prosnav.model.gindb.AppUser;
//import com.prosnav.model.gindb.Assessment;
//import com.prosnav.utils.LogUtils;
//
//@Service
//public class MapperAssessmentDAO {
//
//	@Resource(name = "gindbBaseDAOImpl")
//	private GindbBaseDAOImpl gindbBaseDAOImpl;
//
//	private static Logger log = LoggerFactory.getLogger(MapperAssessmentDAO.class);
//	
//	@SuppressWarnings("unchecked")
//	public List<AppUser> findScore(Assessment assessment){
//		List<AppUser> list = null;
//		try {
//			list = (List<AppUser>) this.gindbBaseDAOImpl.findForList("mapperAssessment.AssessmentMapper.findScore", assessment);
//			return list;
//		} catch (Exception e) {
//			log.error(LogUtils.errorStr + e.getMessage());
//			return list;
//		}
//	}	
//	
//	@SuppressWarnings("unchecked")
//	public List<Assessment> findInitData(){
//		List<Assessment> list = null;
//		try {
//			list = (List<Assessment>) this.gindbBaseDAOImpl.findForList("mapperAssessment.AssessmentMapper.findInitData", null);
//			return list;
//		} catch (Exception e) {
//			log.error(LogUtils.errorStr + e.getMessage());
//			return list;
//		}
//	}	
//}