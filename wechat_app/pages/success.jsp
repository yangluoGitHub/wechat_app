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
<script>
function CaseBack()
{
	location.href = '<c:out value="${backpath}"/>';
}

function document.oncontextmenu(){
event.returnValue=false;
}
</script>
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
		<%out.print(resource.srcStr((String)request.getAttribute("message")));%>
		</td>
	</tr>


 <tr class="tr4">
 <td nowrap align="center">
	<c:choose>
	<c:when test="${backFlag==0}">
	</c:when>
	<c:otherwise>
	   <p align="center">
		 <input class="button" type="button" value='<%=resource.srcStr("Main.back")%>' onclick="javascript:CaseBack();">
	   </p> 
	</c:otherwise>
	</c:choose>
	</td>
</tr>
</table>
</body>
</html>






<!--javascript 国际化标识符-->
<div name="javascriptI18n">
</div>
