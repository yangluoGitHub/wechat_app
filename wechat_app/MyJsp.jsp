<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
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
   <% 
    	String code = (String)request.getAttribute("code");
    	String openId = (String)request.getAttribute("openId");
    	MOpTableVO vo =(MOpTableVO)request.getAttribute("mOpTableVO");
	%>
    This is my JSP page. <br>
    <tr><td>code=<%=code%></td></tr><br>
    <tr><td>openId=<%=openId%></td></tr><br>
    <tr><td>openId=<%=vo.name%></td></tr><br>
    <tr><td>openId=<%=vo.mobile%></td></tr><br>
    <tr><td>openId=<%=vo.openId%></td></tr><br>
  </body>
</html>
