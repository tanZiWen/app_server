package com.prosnav.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Encoder;

import com.prosnav.controller.ExitControl;
import com.prosnav.model.TDict;
import com.prosnav.utils.sftp.SftpClientUtil;

public class IOUtilsFR {
	
	private static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
//	private static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

	private static Logger log = LoggerFactory.getLogger(IOUtilsFR.class);
	
	public static void main(String[] args) {
//		System.out.println(getImageBinary());
//		String img = "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";
		base64StringToImage(getImageBinary());
	}
	
	/**
	 * 将二进制转换为图片
	 * 
	 * @param base64String
	 */
	public static void base64StringToImage(String String) {
		try {
//			byte[] bytes1 = decoder.decodeBuffer(base64String);
			byte[] bytes1 = String.getBytes();
//			ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
//			BufferedImage bi1 = ImageIO.read(bais);
//			File w2 = new File("e:/22.png");// 可以是jpg,png,gif格式
//			ImageIO.write(bi1, "png", w2);// 不管输出什么格式图片，此处不需改动		
			
			InputStream in = new ByteArrayInputStream(bytes1);
		     
            File file=new File("e:/22.png");//可以是任何图片格式.jpg,.png等
            FileOutputStream fos=new FileOutputStream(file);
               
            byte[] b = new byte[1024];
            int nRead = 0;
            while ((nRead = in.read(b)) != -1) {
                fos.write(b, 0, nRead);
            }
            fos.flush();
            fos.close();
            in.close();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将图片转换成二进制
	 * 
	 * @return
	 */
	public static byte[] getImageBinaryForByte() {
		File f = new File("e:/111.png");
		BufferedImage bi;
		try {
			bi = ImageIO.read(f);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "png", baos);  //经测试转换的图片是格式这里就什么格式，否则会失真
			byte[] bytes = baos.toByteArray();
			return bytes;
//			return encoder.encodeBuffer(bytes).trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将图片转换成二进制
	 * 
	 * @return
	 */
	public static String getImageBinary() {
		File f = new File("e:/111.png");
		BufferedImage bi;
		try {
			bi = ImageIO.read(f);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "png", baos);  //经测试转换的图片是格式这里就什么格式，否则会失真
			byte[] bytes = baos.toByteArray();
			return new String(bytes);
//			return encoder.encodeBuffer(bytes).trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

//	/**
//	 * 将二进制转换为图片
//	 * 
//	 * @param base64String
//	 */
//	public static String base64StringToImage(String String, String format, String createTempFileUrl, String syncFileUrl, HashMap<String, TDict> dictMap) {
//		try {
////			byte[] bytes1 = decoder.decodeBuffer(base64String);
//			byte[] bytes1 = String.getBytes();			
//			String fileName = StringUtilFR.getFileTime() + "." + format;
//			String tempFile = createTempFileUrl + File.separator + fileName;
//			ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
//			BufferedImage bi1 = ImageIO.read(bais);
//			File w2 = new File(tempFile);// 可以是jpg,png,gif格式
//			ImageIO.write(bi1, format, w2);// 不管输出什么格式图片，此处不需改动
//			
//			SftpClientUtil sftp = new SftpClientUtil(dictMap.get("url").getDictDesc(), 
//					Integer.parseInt(dictMap.get("port").getDictDesc()), 
//					dictMap.get("loginname").getDictDesc(), 
//					dictMap.get("loginpwd").getDictDesc());
//			sftp.connect();
////			sftp.uploadByDirectory("/usr/local/static/html");
//			sftp.upload(syncFileUrl, tempFile);				
//			sftp.disconnect();
//			System.out.println("aa");
//			
//			return dictMap.get("fileiconurl").getDictDesc() + fileName;
//			
//		} catch (Exception e) {
//			return null;
//		}
//	}
	
	
	/**
	 * 保存图片二进制上传到指定服务器
	 * */
	public static String SaveServer(String String, String format, String createTempFileUrl, String syncFileUrl, HashMap<String, TDict> dictMap){
		String fileName = StringUtilFR.getFileTime() + "." + format;
		String tempFile = createTempFileUrl + File.separator + fileName;
		int count = ImgErToFileUtil.saveToImgByStr(String, createTempFileUrl, fileName);
		try {
			if(1 == count){
				SftpClientUtil sftp = new SftpClientUtil(dictMap.get("url").getDictDesc(), 
						Integer.parseInt(dictMap.get("port").getDictDesc()), 
						dictMap.get("loginname").getDictDesc(), 
						dictMap.get("loginpwd").getDictDesc());
				sftp.connect();
//				sftp.uploadByDirectory("/usr/local/static/html");
				sftp.upload(syncFileUrl, tempFile);				
				sftp.disconnect();
				
				return dictMap.get("fileiconurl").getDictDesc() + fileName;
			}else{
				return null;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
		
	}
}