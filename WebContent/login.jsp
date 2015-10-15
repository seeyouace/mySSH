<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
</head>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<body>
	<form action="login">
        用户名：<input type="text" name="username" id="username"/>
		密码：<input type="password" name="password" id="password"/>
		<input type="submit" id="login" value="登录"/>
	</form>
</body>

</html>