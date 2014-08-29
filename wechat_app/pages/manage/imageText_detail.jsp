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
	<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
		<tr>
			<td nowrap><b>图文详细信息:</b></td>
			<td nowrap align="right"><input class="button" type="button"
				value="返回"
				onclick="javascript:history.back()" style="width:100px;">&nbsp;</td>
		</tr>
	</table>
	<br>
	<table align="center" width="650" border="0" cellspacing="1"
		cellpadding="5" class="table1">
		<tr class="tr1">
			<td nowrap colspan="2" align="center"><b>主图文信息</b></td>
		</tr>
		<tr class="tr3" align="left">
			<td nowrap width="120">标题：</td>
			<td><c:out value="${mainResource.resourceTittle}" /></td>
		</tr>
		<tr class="tr3" align="left">
			<td nowrap width="120">封面：</td>
			<td><img src="${pageContext.request.contextPath}/${mainMedia.mediaPath}" width="360px"></td>
		</tr>
		<tr class="tr3" align="left">
			<td nowrap width="120">内容：</td>
			<td><c:out value="${mainResource.resourceContent}" escapeXml="false"/></td>
		</tr>
		<tr class="tr3" align="left">
			<td nowrap width="120">外链：</td>
			<td><a target="_blank" href="${mainResource.outLink}"><c:out value="${mainResource.outLink}" /></td>
		</tr>
	</table>
	<c:if test="${isMulti == true}">
		<c:forEach items="${resources}" var="item" varStatus="a">
			<table align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1">
				<tr class="tr1">
			<td nowrap colspan="2" align="center"><b>图文信息[${a.index+1}]</b></td>
		</tr>
		<tr class="tr3" align="left">
			<td nowrap width="120">标题：</td>
			<td>${item.resource.resourceTittle}</td>
		</tr> 
		<tr class="tr3" align="left">
			<td nowrap width="120">封面：</td>
			<td><img src="${pageContext.request.contextPath}/${item.media.mediaPath}" width="360px"></td>
		</tr>
		<tr class="tr3" align="left">
			<td nowrap width="120">内容：</td>
			<td><c:out value="${item.resource.resourceContent}" escapeXml="false"/></td>
		</tr>
		<tr class="tr3" align="left">
			<td nowrap width="120">外链：</td>
			<td><a target="_blank" href="${item.resource.outLink}"><c:out value="${item.resource.outLink}" /></a></td>
		</tr>
			</table>
		</c:forEach>
	</c:if>
	
</body>
</html>
