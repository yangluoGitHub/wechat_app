<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page language="java"%>
<%@ page contentType="text/html; charset=gbk"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<link href="../styles/common.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<title></title>
</head>
<body>
<br><br><br>

<table align="center" border="0" cellspacing="0" cellpadding="0" style="border:2px ridge #eeeeee;">
<tr>
<td align="center" width="400" height="120" style="word-break: break-all;">
	<%=resource.srcStr("Login.detail_no_per")%>
</td>
</tr>
</table>
 

<p align="center">
	<input class="button" type="button" value='<%=resource.srcStr("Version.close")%>' onclick="javascript:CloseWindow();">
</p> 


</body>
</html>






<!--javascript 国际化标识符-->
<div name="javascriptI18n">
</div>
