//
//package com.prosnav.dao.oauth;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.apache.ibatis.session.ExecutorType;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;
//
//import com.prosnav.dao.BaseDAO;
//import com.prosnav.dao.impl.BaseDAOImpl;
//import com.prosnav.utils.LogUtils;
//
//
///**
// * 数据层父类实现
// * */
//@Repository("oauthBaseDAOImpl")
//public class OauthBaseDAOImpl implements BaseDAO {
//    
//	private static Logger log = LoggerFactory.getLogger(BaseDAOImpl.class);
//	@Resource(name = "sqlSessionTemplateoauth")
//    private SqlSessionTemplate sqlSessionTemplateoauth;
//	
//    public int save(String str, Object obj) throws Exception {
//        return sqlSessionTemplateoauth.insert(str, obj);
//    }
//    
//    public int batchSave(String str, List<?> objs) throws Exception {
//        return sqlSessionTemplateoauth.insert(str, objs);
//    }
//
//    public int update(String str, Object obj) throws Exception {
//        return sqlSessionTemplateoauth.update(str, obj);
//    }
//
//    public void batchUpdate(String str, List<?> objs) throws Exception {
//        SqlSessionFactory sqlSessionFactory = sqlSessionTemplateoauth.getSqlSessionFactory();
//        // 批量执行器
//        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
//        try {
//            if (objs != null) {
//                for (int i = 0, size = objs.size(); i < size; i++) {
//                    sqlSession.update(str, objs.get(i));
//                }
//                sqlSession.flushStatements();
//                sqlSession.commit();
//                sqlSession.clearCache();
//            }
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    public int batchDelete(String str, List<?> objs) throws Exception {
//        return sqlSessionTemplateoauth.delete(str, objs);
//    }
//    
//    public int delete(String str, Object obj) throws Exception {
//        return sqlSessionTemplateoauth.delete(str, obj);
//    }
//    
//    public Object findForObject(String str, Object obj) throws Exception {
//        return sqlSessionTemplateoauth.selectOne(str, obj);
//    }
//    
//	public Object findForList(String str, Object obj) throws Exception {
//		return sqlSessionTemplateoauth.selectList(str, obj);
//	}
//
//	
//	public Object findForMap(String str, Object obj, String key, String value) throws Exception {
//		return sqlSessionTemplateoauth.selectMap(str, obj, key);
//	}
//	
//	@Override
//	public int[]  batchExecute(List<String> sqlList) {
//		SqlSessionFactory sqlSessionFactory = sqlSessionTemplateoauth.getSqlSessionFactory();
//		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
//		Connection conn = sqlSession.getConnection();
//        Statement stmt;
//		try {
//			stmt = conn.createStatement();
//			for (String string : sqlList) {
//				stmt.addBatch(string);
//			}
//	        
//	        int[] count = stmt.executeBatch();
//	        sqlSession.commit();
//	        return count;
//	        
//		} catch (SQLException e) {
//			log.error(LogUtils.errorStr + e.getMessage());
//			e.printStackTrace();
//		} 
//       
//		return null;
//	}
//}
