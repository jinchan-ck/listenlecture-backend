<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <form action="lecture/user!addUser.action" method="get">
     <br><br><br><br>
   <div align="center">用户管理</div>
   <br><br><br><br>
    <table align="center" width="90%" border="0" cellspacing="0" cellpadding="0">
    <tr width=100% align="center"><td>姓名：<input type="text" name="user.username"></td></tr>
    <tr width=100% align="center"><td>	密码：<input type="text" name="user.password"></td></tr>
   <tr><td>&nbsp;</td></tr>
    <tr width=100% align="center"><td><input type="submit" value="添加">  <input type="reset" value="重置"></td> </tr>
    </table>
    </form>
  </body>
</html>