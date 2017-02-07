<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="top.jsp"%> 
<script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>
<body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <!-- <div>

                <h1 class="logo-name">login</h1>

            </div> -->
            <h3>设备厂商登录</h3>

            <form class="m-t" role="form" action="/loginUser" method="post">
                <div class="form-group">
                    <input  class="form-control" name="userLoginUser" placeholder="用户名" required="">
                </div>
                <div class="form-group">
                    <input type="password" name="userLoginPassword" class="form-control" placeholder="密码" required="">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>
                <input type="hidden" name="dictValue" value="provider"/>

                <p class="text-muted text-center"> <a href="login.html#"><small>忘记密码了？</small></a> | <a href="register.html">注册一个新账号</a>
                </p>

            </form>
        </div>
    </div>
    
    

</body>
</html>