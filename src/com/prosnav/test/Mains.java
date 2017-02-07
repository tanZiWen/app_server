
package com.prosnav.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

//import com.uwitec.model.TUser;
//import com.uwitec.service.LoginService;


/**
 * 测试
 * */
public class Mains {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "ApplicationContext.xml" });
		context.start();
//		DemoServer  demoService = (DemoServer) context.getBean("demoService");				
		
		
//		LoginService  loginService = (LoginService) context.getBean("loginService");
//		com.uwitec.service.test.DemoServer demoServer = (DemoServer) context.getBean("demoService");
//		
//		User user = new User();
//		user.setUserName("111");
//		user.setUserAge(111);
//		System.out.println("=================" + demoService.findUserByUserNameAndUserAge(user).getUserId());
		
//		String userLoginUser = "uwitec";
//		String userLoginPassword = "uwitec";
//		String dictValue = "provider";
//		TUser user = new TUser();
//		user.setUserLoginUser(userLoginUser);
//		user.setUserLoginPassword(userLoginPassword);		
//		
//		if(StringUtils.isNotBlank(dictValue)){
//			if("provider".equalsIgnoreCase(dictValue)){
//				TUser u= loginService.loginProviderBiz(user);
//				System.out.println("=========================" + u.getUserId());
//				System.out.println("=========================" + u.getUserLoginUser());
//			}if("device_user".equalsIgnoreCase(dictValue)){
//				loginService.loginUserBiz(user);
//			}
//		}
		
		
		try {
			//GET
//			System.out.println(new HttpRequestor().doGet("http://restapi.amap.com/v3/geocode/regeo?"
//					+ "key=0c2d9efe9ab254e25a73944d21f55fec&location=121.4736999111,31.2303950509&radius=1000&extensions=base&batch=false&roadlevel=1"));
			
			//POST
//			JSONObject obj = new JSONObject();
//			obj.put("param1", "");
//			obj.put("param2", "");
//			obj.put("type", "1");
//			String str = new HttpRequestor().doPost("http://192.168.1.109:8045/UWITECBridge/phoneConference", obj);
//			System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
