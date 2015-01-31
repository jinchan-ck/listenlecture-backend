<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<base href="<%=basePath%>">

<title>list user</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>

<div>
		<a href="index.jsp">返回首页</a>
	</div>
	
	<h3>Simple Iterator</h3>
	<ol>
		<s:iterator value="userList">
			<li><s:property /></li>
		</s:iterator>
	</ol>

	<h3>Iterator with IteratorStatus</h3>
	<table>
		<s:iterator value="userList" status="userListStatus">
			<tr>
				<s:if test="#userListStatus.even == true">
					<td style="background: #CCCCCC"><s:property /></td>
				</s:if>
				<s:elseif test="#userListStatus.first == true">
					<td><s:property /> (This is first value)</td>
				</s:elseif>
				<s:else>
					<td><s:property /></td>
				</s:else>
			</tr>
		</s:iterator>
	</table>
</body>
</html>
