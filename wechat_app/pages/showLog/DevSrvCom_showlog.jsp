<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%>
<%
	Resource resource = (Resource) GetResource.getOneResource(
			application, session.getAttribute("locale").toString());
%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
		<title></title>
		<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
		<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
		<link href="../styles/common.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		<!--javascript 国际化标识符-->
		<div id="javascriptI18n">
			<%@ include file="../../scripts/common.jsp"%>
			<input type="hidden" id="System.input_maintainer_name" value='<%=resource.srcStr("System.input_maintainer_name")%>' />
			<input type="hidden" id="System.fault_email_format" value='<%=resource.srcStr("System.fault_email_format")%>' />
		</div>

		<script language="JavaScript">
</script>
		<br>
	<form name="form1" method="post" action="devSrvCom.do?action=mod">
		<input type="hidden" name="no" value="<c:out value="${objectvo.no}"/>">
		<table align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1">
			<tr class="tr1">
				<td nowrap colspan="2" align="center"><%=resource.srcStr("System.mod_dev_maintainer")%></td>
			</tr>
			<tr class="tr3">
				<td nowrap width="120"><%=resource.srcStr("System.maintainer_name")%>： </td>
				<td nowrap>
					<c:out value="${objectvo.name}" />
				</td>
			</tr>
			<tr class="tr3">
				<td nowrap><%=resource.srcStr("System.contact")%>：</td>
				<td nowrap>
					<c:out value="${objectvo.linkman}" />
				</td>
			</tr>
			<tr class="tr3">
				<td nowrap><%=resource.srcStr("System.address")%>：</td>
				<td nowrap>
					<c:out value="${objectvo.address}"/>
				</td>
			</tr>
			<tr class="tr3">
				<td nowrap><%=resource.srcStr("System.fixed")%>1：</td>
				<td nowrap>
					<c:out value="${objectvo.phone1}"/>
				</td>
			</tr>
			<tr class="tr3">
				<td nowrap><%=resource.srcStr("System.fixed")%>2：</td>
				<td nowrap>
					${objectvo.phone2}
				</td>
			</tr>
			<tr class="tr3">
				<td nowrap><%=resource.srcStr("System.mobile_phone")%>1：</td>
				<td nowrap>
					<c:out value="${objectvo.mobile1}"/>
				</td>
			</tr>
			<tr class="tr3">
				<td nowrap><%=resource.srcStr("System.mobile_phone")%>2：</td>
				<td nowrap>
					<c:out value="${objectvo.mobile2}"/>
				</td>
			</tr>
			<tr class="tr3">
				<td nowrap><%=resource.srcStr("System.fax")%>：</td>
				<td nowrap>
					<c:out value="${objectvo.fax}"/>
				</td>
			</tr>
			<tr class="tr3">
				<td nowrap><%=resource.srcStr("System.email")%>：</td>
				<td nowrap>
					<c:out value="${objectvo.email}"/>
				</td>
			</tr>
		</table>

		<p align="center">
			<input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.history.back();">
		</p>

	</form>

	</body>
</html>




