package com.prosnav.auth;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.prosnav.service.impl.LoginService;
import com.prosnav.service.impl.SysService;
import com.prosnav.utils.ParameterFR;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Resource
	private LoginService loginService;	
	@Resource
	private ParameterFR parameterFR;
	@Resource
	private SysService sysService;
		
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {				
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);

			// 没有声明需要权限,或者声明不验证权限
			if (authPassport == null || authPassport.validate() == false) {
				return true;
			} else {
				String loginName = "";
				String token = "";				
				boolean bool = false;
				boolean isTokenId = false;
				JSONObject json = null;
				String submitMehtod = request.getMethod();
				// GET
		        if (submitMehtod.equals("GET")) {
//		            System.out.println(new String(request.getQueryString().getBytes("iso-8859-1"),"utf-8").replaceAll("%22", "\""));
		        	token = request.getParameter(parameterFR.TOKEN);
		        	loginName = request.getParameter(parameterFR.LOGIN_NAME);
		        	if(null == token || null == loginName){
		        		//没有传值正确
						response.sendRedirect("errorTokenLoginName");
						return false;
		        	}
		        // POST
		        }
		        else {
		        	String requestJson = getRequestPostStr(request);
		        	json = JSONObject.fromObject(requestJson);
					try {
						loginName = json.getString(parameterFR.LOGIN_NAME).toString();
						token = json.getString(parameterFR.TOKEN).toString();						
					} catch (Exception e) {
						//没有传值正确
						response.sendRedirect("errorTokenLoginName");
						return false;
					}										
		        }
		        
		        isTokenId = sysService.isTokenNo(loginName, token);		               
				if (isTokenId){// 如果验证成功返回true（这里直接写false来模拟验证失败的处理）
					bool = sysService.isToken(loginName, token);
					if(bool){												
						if(submitMehtod.equals("GET")) {
							request.setAttribute(parameterFR.TOKEN, token);
						}else{
							request.setAttribute(parameterFR.TOKEN, token);
							request.setAttribute(parameterFR.JSON, json);
						}												
						return true;
					}else{
						//token失效
						if(submitMehtod.equals("GET")) {
							request.setAttribute(parameterFR.TOKEN, sysService.getToken(loginName, token));
						}else{
							request.setAttribute(parameterFR.TOKEN, sysService.getToken(loginName, token));
							request.setAttribute(parameterFR.JSON, json);
						}		
						return true;
					}
				}else{// 如果验证失败
					//TOKEN不匹配					
					if(submitMehtod.equals("GET")) {
						request.getRequestDispatcher("/app/auth/overdueNo").forward(request, response); 
					}else{
						response.sendRedirect("overdueNo");
					}
					return false;
				}
			}
		} else
			return true;
	}
	
	/**      
     * 描述:获取 post 请求的 byte[] 数组
     * @param request
     * @return
     * @throws IOException      
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    /**
     * 描述:获取 post 请求内容
     * @param request
     * @return
     * @throws IOException      
     */
    public static String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }
}