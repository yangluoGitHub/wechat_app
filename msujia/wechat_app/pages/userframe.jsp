<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title></title>
<style type="text/css">
a {
	padding-top: 5px;
	border: 1px;
	font-size: 15px;
	text-decoration: none;
	color: #1456A0;
}

a:hover {
	color: #1456A0;
	text-decoration: underline;
}

.op {
	position: absolute;
	left: 81%;
	width: 198px;
	height: 38px;
	font-size: 14px;
	color: #1456A0;
	font-family: 微软雅黑, 宋体;
	text-align: center;
	padding-top: 5px;
	background-image: url(../images/welcome_user.png);
}
</style>
</head>

<body>
	<div id="user">
		<div class="op">
			<a href="#"
				onClick="javascript:top.workarea.location='user.do?action=detail&no=<c:out value='${userSession.account}'/>&MENU&sid=<%=request.getParameter("sid")%>'">欢迎您，<c:out
					value="${userSession.name}" /></a>
		</div>
	</div>
</body>
</html>
