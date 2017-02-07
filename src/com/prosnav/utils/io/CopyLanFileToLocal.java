package com.prosnav.utils.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

/**
 * 
 * <b>所属模块：</b>牧羊仒.测试<br/> 
 * <b>类名称：</b>CopyLanFileToLocal<br/> 
 * <b>类描述：</b> 读取局域网共享文件夹文件，到本地文件夹   <br/> 
 * <b>版本：</b>V1.0<br/> 
 * <b>创建人：</b><a href="mailto:han_huayi@163.com">牧羊仒</a><br/>  
 * <b>创建时间：</b>2016年6月8日 下午3:29:09<br/>
 */
public class CopyLanFileToLocal {

	public static void main(String[] args) {
        InputStream in = null ;
//        ByteArrayOutputStream out = null ;
        OutputStream out = null;
        try {
        	//目标文件名
            String fileName = "456.html";
        	
        	//本地文件
        	String localPath = "c:\\";
        	
            String host = "192.168.44.129";//远程服务器的地址
            String username = "root";//用户名
            String password = "server001";//密码
            String path = "/usr/local/static/html";//远程服务器共享文件夹名称
            
            String remoteUrl = "smb://" + username + ":" + password + "@" + host + path + (path.endsWith("/") ? "" : "/");
            
            //创建远程文件对象
            SmbFile remoteFile = new SmbFile(remoteUrl + "/" + fileName);
            remoteFile.connect(); 
            
            //创建文件流
            in = new BufferedInputStream(new SmbFileInputStream(remoteFile));
            out = new BufferedOutputStream(new FileOutputStream(new File(localPath+fileName)));
            
            //读取文件内容
            byte[] buffer = new byte[4096];
            int len = 0;
            while ((len = in.read(buffer, 0, buffer.length)) != - 1) {
                out.write(buffer, 0, len);
            }
            
            out.flush();
        }
        catch (Exception e) {
            String msg = "下载远程文件出错：" + e.getLocalizedMessage();
            System.out.println(msg);
        }
        finally {
            try {
                if(out != null) {
                    out.close();
                }
                if(in != null) {
                    in.close();
                }
            }
            catch (Exception e) {}
        }
	}
}
