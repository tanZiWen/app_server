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
//import com.prosnav.utils.LogUtils;
//
//@Service
//public class MapperAppUser {
//
//	@Resource(name = "gindbBaseDAOImpl")
//	private GindbBaseDAOImpl gindbBaseDAOImpl;
//
//	private static Logger log = LoggerFactory.getLogger(MapperAppUser.class);
//	
//	@SuppressWarnings("unchecked")
//	public List<AppUser> findAppUser(){
//		List<AppUser> list = null;
//		try {
//			list = (List<AppUser>) this.gindbBaseDAOImpl.findForList("mapperAppUser.AppUserMapper.findAppUser", null);
//			return list;
//		} catch (Exception e) {
//			log.error(LogUtils.errorStr + e.getMessage());
//			return list;
//		}
//	}		
//}