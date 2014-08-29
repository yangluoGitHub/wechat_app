<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=gbk" />
		<title></title>
		<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
		<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
        <script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
        <link href="../styles/common.css" rel="stylesheet" type="text/css">
	</head>
	<body>
	<table align="center" width="650" border="0" cellspacing="1"
		cellpadding="5" class="table1">
		<tr class="tr1">
			<td nowrap colspan="2" align="center">图片信息</td>
		</tr>
		<tr class="tr3" align="left">
			<td nowrap width="120">图片名称：</td>
			<td><c:out value="${media.mediaName}" /></td>
		</tr>
		<tr class="tr3" align="left">
			<td nowrap width="120">图片描述：</td>
			<td><c:out value="${media.describe}" /></td>
		</tr>
	</table>
	<br />
	<center>
		<input class="button" type="button" value="关闭"
			onclick="javascript:window.close();" />
		<hr>
		<c:if test="${media.mediaResource == 0 }">
			<img src="${pageContext.request.contextPath}/${media.mediaPath}">
		</c:if>
		<c:if test="${media.mediaResource == 1 }">
		<c:out value="${media.mediaPath}" />
			<img src="${media.mediaPath}">
		</c:if>
	</center>
</body>
</html>
