package com.prosnav.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test {

	public static void main(String[] args) {
//		File file = new File("e:/111.png");
//		byte[] bbb =  new byte[10240];
//		try {
//			InputStream a = new FileInputStream(file);
//			a.read(bbb);
//			System.out.println(bbb[0]);
//			System.out.println(Integer.toBinaryString(bbb[0]));	
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//System.out.println(decodeUnicode("啊啊"));
		
//		va.net.URLEncoder.encode(String)
//		System.out.println(URLEncoder.encode("阿斯达"));
//		System.out.println(URLDecoder.decode("%E5%A4%8F%E9%A3%9E"));
		
//		try {
//			System.out.println(Base64FR.getBase64("210104198707146115"));
//			System.out.println(URLEncoder.encode("18516315051", "UTF-8"));
//			System.out.println(URLDecoder.decode("%E5%A4%8F%E9%A3%9E", "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		String idCard = "411528198810051916";		
		System.out.println(IdcardValidator.isValidatedAllIdcard(idCard));		
		idCard = Base64FR.getBase64(idCard);
		
		//生日
		System.out.println(IdCardUtils.getBirthByIdCard(Base64FR.getFromBase64(idCard)));		
		//星座
		System.out.println(IdCardUtils.getConstellationById(Base64FR.getFromBase64(idCard)));		
		//生效
		System.out.println(IdCardUtils.getZodiacById(Base64FR.getFromBase64(idCard)));		
		
	}
	
	
	 public static String decodeUnicode(String theString) {    
		  
	     char aChar;    
	  
	      int len = theString.length();    
	  
	     StringBuffer outBuffer = new StringBuffer(len);    
	  
	     for (int x = 0; x < len;) {    
	  
	      aChar = theString.charAt(x++);    
	  
	      if (aChar == '\\') {    
	  
	       aChar = theString.charAt(x++);    
	  
	       if (aChar == 'u') {    
	  
	        // Read the xxxx    
	  
	        int value = 0;    
	  
	        for (int i = 0; i < 4; i++) {    
	  
	         aChar = theString.charAt(x++);    
	  
	         switch (aChar) {    
	  
	         case '0':    
	  
	         case '1':    
	  
	         case '2':    
	  
	         case '3':    
	  
	        case '4':    
	  
	         case '5':    
	  
	          case '6':    
	           case '7':    
	           case '8':    
	           case '9':    
	            value = (value << 4) + aChar - '0';    
	            break;    
	           case 'a':    
	           case 'b':    
	           case 'c':    
	           case 'd':    
	           case 'e':    
	           case 'f':    
	            value = (value << 4) + 10 + aChar - 'a';    
	           break;    
	           case 'A':    
	           case 'B':    
	           case 'C':    
	           case 'D':    
	           case 'E':    
	           case 'F':    
	            value = (value << 4) + 10 + aChar - 'A';    
	            break;    
	           default:    
	            throw new IllegalArgumentException(    
	              "Malformed   \\uxxxx   encoding.");    
	           }    
	  
	         }    
	          outBuffer.append((char) value);    
	         } else {    
	          if (aChar == 't')    
	           aChar = '\t';    
	          else if (aChar == 'r')    
	           aChar = '\r';    
	  
	          else if (aChar == 'n')    
	  
	           aChar = '\n';    
	  
	          else if (aChar == 'f')    
	  
	           aChar = '\f';    
	  
	          outBuffer.append(aChar);    
	  
	         }    
	  
	        } else   
	  
	        outBuffer.append(aChar);    
	  
	       }    
	  
	       return outBuffer.toString();    
	  
	      }   
}
