<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page language="java"%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page isErrorPage="true" %>

<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>403</title>
</head>

<body>



<br><br><br>

<table align="center" border="0" cellspacing="0" cellpadding="0" style="border:2px ridge #ff0000;">
<tr>
<td align="center" width="400" height="120" style="word-break: break-all;">
<%=resource.srcStr("Login.page_no_per") %>

</td>
</tr>
</table>
 
</body>
</html>






<!--javascript 国际化标识符-->
<div name="javascriptI18n">
</div>
