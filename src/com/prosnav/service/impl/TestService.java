
//package com.prosnav.service.impl;
//
//import java.io.File;
//import java.util.List;
//import java.util.UUID;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//
//import com.prosnav.dao.impl.DemoServerImpl;
//import com.prosnav.model.User;
//import com.prosnav.service.ITestService;
//import com.prosnav.utils.SpringPropertyResourceReader;
//import com.prosnav.utils.sftp.SftpClientUtil;
//
///**
// * 客户端服务层
// * */
//@Service
//public class TestService implements ITestService {
//	
//	@Resource
//	private DemoServerImpl demoServerImpl;
//	
//	@Resource
//	private SpringPropertyResourceReader springPropertyResourceReader;
//	
//	public void save(){
//		User user = new User();
//		user.setUserId(UUID.randomUUID().toString());
//		user.setUserName("阿斯达岁的");
//		user.setUserAge(15);
//		
//		System.out.println(springPropertyResourceReader.getProperty("TEST"));
//		
//		demoServerImpl.saveUser(user);
//	}
//	
//	
//	public void test(){				
//		List<User> list = demoServerImpl.findUserAll();
//		if(0 != list.size()){
//			System.out.println(list.get(0).getUs().getUsSex());
//			System.out.println(list.size());
//		}
//	}
//	
//	public void upload(){				
//		SftpClientUtil sftp = new SftpClientUtil("192.168.44.129", 22, "root", "server001");
//		try {
//			sftp.connect();
////			sftp.uploadByDirectory("/usr/local/static/html");
//			sftp.upload(springPropertyResourceReader.getProperty("TARGET_DIRECTORY"), 
//					springPropertyResourceReader.getProperty("LOCAL_DIRECTORY") + File.separator+ "456.html");
//			System.out.println("完成。");
//			sftp.disconnect();			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}			
//	}
//	
//	public static void main(String[] args) {
//		System.out.println(File.separator);
//	}
//}
