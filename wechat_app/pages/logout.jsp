<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java"%>

<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body>
<%
	request.getSession().removeAttribute("_examineNum_wd");
	request.getSession().removeAttribute("_examineNum_device");
%>
<script>
top.location="login.jsp"
</script>

</body>
</html>

<!--javascript 国际化标识符-->
<div name="javascriptI18n">
</div>
