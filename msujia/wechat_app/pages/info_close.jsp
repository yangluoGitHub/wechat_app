<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page language="java"%>
<%@ page contentType="text/html; charset=gbk"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<link href="../styles/common.css" rel="stylesheet" type="text/css">
<title></title>
</head>

<body>



<br><br>

<div align="center" style="font-size:14px;"><b><%=resource.srcStr("Main.system-prompt")%></b></div>
<table align="center" width="450" border="0" cellspacing="1" cellpadding="4" class="table1">

	<tr class="tr1">
		<td align="left"><img src="../images/notify.gif"><%=resource.srcStr("Main.prompting-message")%></td>
	</tr>

    <br>
	<tr class="tr3">
		<td align="center">
		<c:out value="${message}"/>
		</td>
	</tr>


 <tr class="tr4">
 <td nowrap align="center">
<input type="button" onClick="javascript:window.close()" value='<%=resource.srcStr("Version.close")%>' class="button">
	</td>
</tr>
</table>
</body>
</html>






<!--javascript 国际化标识符-->
<div name="javascriptI18n">
</div>
