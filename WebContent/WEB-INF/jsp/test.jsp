<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>
<c:set var="ctx" value="${pageContext.request.contextPath}" /> 
</head>
<body>
	<img src="http://192.168.44.129:8081/img/test.png" />
	
	
	<form enctype="multipart/form-data" action="/upload.so" method="post">  
<input type="hidden" name="test" value="上传">  
  
    选择文件1:  
    <input type="file" name="filename1" />  
    <br />  
    选择文件2:  
    <input type="file" name="filename2" />  
    <br />  
    选择文件3:  
    <input type="file" name="filename3" />  
    <br />  
    <input type="submit" value="上载" />  
   </form>  
	
</body>
</html>