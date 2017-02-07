package com.prosnav.dao;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.prosnav.dao.impl.BaseDAOImpl;
import com.prosnav.model.TUser;
import com.prosnav.utils.LogUtils;

@Service
public class MapperUserDAO {

	private static Logger log = LoggerFactory.getLogger(MapperTokenDAO.class);
	
	@Resource(name = "baseDAOImpl")
	private BaseDAOImpl baseDAO;
	
	@SuppressWarnings("unchecked")
	public List<TUser> findloginNameAndLoginPwd(TUser user){
		List<TUser> list = null;
		try {
			list = (List<TUser>) this.baseDAO.findForList("mapperUser.TUserMapper.findloginNameAndLoginPwd", user);
			return list;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return list;
		}
	}
	
	@SuppressWarnings("unchecked")
	public int findUserAndPwdAndDevice(TUser user){
		List<TUser> list = null;
		try {
			list = (List<TUser>) this.baseDAO.findForList("mapperUser.TUserMapper.findUserAndPwdAndDevice", user);
			return list.size();
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return -1;
		}
	}
	
	@SuppressWarnings("unchecked")
	public int findLoginName(String loginName){
		List<TUser> list = null;
		try {
			list = (List<TUser>) this.baseDAO.findForList("mapperUser.TUserMapper.findLoginName", loginName);
			return list.size();
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return -1;
		}
	}
	
	public TUser findLoginNameForUser(String loginName){
		TUser user = null;
		try {
			user = (TUser) this.baseDAO.findForObject("mapperUser.TUserMapper.findLoginNameForUser", loginName);
			return user;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return user;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TUser> findLoginNameForList(String loginName){
		List<TUser> list = null;
		try {
			list = (List<TUser>) this.baseDAO.findForList("mapperUser.TUserMapper.findLoginName", loginName);
			return list;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return list;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TUser> findLoginNameForDict(String loginName){
		List<TUser> list = null;
		try {
			list = (List<TUser>) this.baseDAO.findForList("mapperUser.TUserMapper.findLoginNameForDict", loginName);
			return list;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return list;
		}
	}
	
	public int add(TUser user){
		int count = 0;
		try {
			count = this.baseDAO.save("mapperUser.TUserMapper.save", user);
			return count;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return -1;
		}
	}
	
	public int updateEmailApp(TUser user){
		int count = 0;
		try {
			count = this.baseDAO.save("mapperUser.TUserMapper.updateEmailApp", user);
			return count;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return -1;
		}
	}
	
	public int updateUserByLoginName(TUser user){
		int count = 0;
		try {
			count = this.baseDAO.update("mapperUser.TUserMapper.updateUserByLoginName", user);
			return count;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return -1;
		}
	}
	
	public int updateUserByEmail(TUser user){
		int count = 0;
		try {
			count = this.baseDAO.save("mapperUser.TUserMapper.updateUserByEmail", user);
			return count;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return -1;
		}
	}
	
	public int updateUserByNickName(TUser user){
		int count = 0;
		try {
			count = this.baseDAO.save("mapperUser.TUserMapper.updateUserByNickName", user);
			return count;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return -1;
		}
	}
	
	public int updateUserByBlood(TUser user){
		int count = 0;
		try {
			count = this.baseDAO.save("mapperUser.TUserMapper.updateUserByBlood", user);
			return count;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return -1;
		}
	}
	
	public int updateUserByLoginPwd(TUser user){
		int count = 0;
		try {
			count = this.baseDAO.save("mapperUser.TUserMapper.updateUserByLoginPwd", user);
			return count;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return -1;
		}
	}
	
	public int uploadIcon(TUser user){
		int count = 0;
		try {
			count = this.baseDAO.save("mapperUser.TUserMapper.uploadIcon", user);
			return count;
		} catch (Exception e) {
			log.error(LogUtils.errorStr + e.getMessage());
			return -1;
		}
	}
	
}
