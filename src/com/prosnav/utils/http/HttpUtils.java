package com.prosnav.utils.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {
	
	private static Logger log = LoggerFactory.getLogger(HttpUtils.class);
	
	@SuppressWarnings("rawtypes")
	public static HashMap<String, String> sendPost(String url, Map parameterMap) throws Exception{
		HashMap<String, String> maps = new HashMap<String, String>();
		
		
		StringBuffer parameterBuffer = new StringBuffer();
		StringBuffer resultBuffer = new StringBuffer();
		
        if (parameterMap != null) {
            Iterator iterator = parameterMap.keySet().iterator();
            String key = null;
            String value = null;
            while (iterator.hasNext()) {
                key = (String)iterator.next();
                if (parameterMap.get(key) != null) {
                    value = (String)parameterMap.get(key);
                } else {
                    value = "";
                }
                
                parameterBuffer.append(key).append("=").append(value);
                if (iterator.hasNext()) {
                    parameterBuffer.append("&");
                }
            }
        }
//        parameterBuffer.append(parameterMap.toString());
		// Post请求的url，与get不同的是不需要带参数
        URL postUrl = new URL(url);
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
       
        // 设置是否向connection输出，因为这个是post请求，参数要放在
        // http正文内，因此需要设为true
        connection.setDoOutput(true);
        // Read from the connection. Default is true.
        connection.setDoInput(true);
        // 默认是 GET方式
        connection.setRequestMethod("POST");
        
        // Post 请求不能使用缓存
        connection.setUseCaches(false);
        
        connection.setInstanceFollowRedirects(true);
        
        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
        // 进行编码
        connection.setRequestProperty("Accept","application/json");
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//        connection.setRequestProperty("charset", "gbk");
        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
        // 要注意的是connection.getOutputStream会隐含的进行connect。
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        // The URL-encoded contend
        // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
//        String content = "apikey=be35e44d46067cf4bea391d005738470&text=【帆茂投资】您的验证码是444&mobile=18516315054";
        
        
//        String content = "apikey=" + "be35e44d46067cf4bea391d005738470";
//        content +="&text="+URLEncoder.encode("【帆茂投资】您的验证码是444", "UTF-8");
//        content +="&mobile=18516315054";
        
        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
        
        log.info("======" + parameterBuffer.toString() + "======");
        out.writeBytes(parameterBuffer.toString());        
        
//        System.out.println(connection.getErrorStream());
        out.flush();
        out.close(); 
        
//        if (connection.getResponseCode() != 200) {
//            return null;
//        }

        BufferedReader reader = null;
        try {
        	reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		} catch (Exception e) {
			reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "utf-8"));
		}
        
        String line;
         
        while ((line = reader.readLine()) != null){
        	 resultBuffer.append(line);
        }
       
        reader.close();
        connection.disconnect();
        log.info("=================" + resultBuffer.toString() + "=================" + connection.getResponseCode());
        maps.put("message", resultBuffer.toString());
        maps.put("code", connection.getResponseCode()+"");
        
        return maps;
	}
	
	public static void main(String[] args) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
	    params.put("apikey", "be35e44d46067cf4bea391d005738470");
	    params.put("text", URLEncoder.encode("【帆茂投资】您的验证码是444"));
	    params.put("mobile", "18516315054");
		
		System.out.println(sendPost("https://sms.yunpian.com/v2/sms/single_send.json", params));
	}

}