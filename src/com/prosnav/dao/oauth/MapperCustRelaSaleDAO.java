//package com.prosnav.dao.oauth;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import com.prosnav.dao.gindb.MapperAppUser;
//import com.prosnav.model.oauth.CustRelaSale;
//import com.prosnav.utils.LogUtils;
//
//@Service
//public class MapperCustRelaSaleDAO {
//
//	@Resource(name = "oauthBaseDAOImpl")
//	private OauthBaseDAOImpl oauthBaseDAOImpl;
//
//	private static Logger log = LoggerFactory.getLogger(MapperAppUser.class);
//	
//	@SuppressWarnings("unchecked")
//	public List<CustRelaSale> findCustRelaSale(CustRelaSale custRelaSale){
//		List<CustRelaSale> list = null;
//		try {
//			list = (List<CustRelaSale>) this.oauthBaseDAOImpl.findForList("mapperCustRelaSale.CustRelaSaleMapper.findCustRelaSale", custRelaSale);
//			return list;
//		} catch (Exception e) {
//			log.error(LogUtils.errorStr + e.getMessage());
//			return list;
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<CustRelaSale> findCustOrder(CustRelaSale custRelaSale){
//		List<CustRelaSale> list = null;
//		try {
//			list = (List<CustRelaSale>) this.oauthBaseDAOImpl.findForList("mapperCustRelaSale.CustRelaSaleMapper.findCustOrder", custRelaSale);
//			return list;
//		} catch (Exception e) {
//			log.error(LogUtils.errorStr + e.getMessage());
//			return list;
//		}
//	}
//}
