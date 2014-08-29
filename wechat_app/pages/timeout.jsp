<%@ page language="java"%>
<%@ page contentType="text/html; charset=gbk"%>
<%
     String zh_mess1 = "会话超时";String tw_mess1 = "會話超時";String cn_mess1 = "Session Timeout";
     String zh_mess2 = "请重新登录!";String tw_mess2 = "請重新登錄!";String cn_mess2 = "Please re-login!";
     String zh_mess3 = "提示信息";String tw_mess3 = "提示信息";String cn_mess3 = "Tips";
     String zh_close = "关 闭";String tw_close="關 閉";String cn_close="Close";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<link href="../styles/common.css" rel="stylesheet" type="text/css">
<title><%=zh_mess1 %>，<%=zh_mess2 %>!</title>
<script language="JavaScript" for="document" event="onkeydown">
	if(event.keyCode==13){
		document.getElementById('subButton').click();
	}
</script>
</head>
<body>
<br><br>
<div align="center" style="font-size:14px;"><b><%=zh_mess1 %></b></div>
<table align="center" width="450" border="0" cellspacing="1" cellpadding="4" class="table1">

	<tr class="tr6">
		<td align="left"><img src="../images/notify.gif"><%=zh_mess3 %></td>
	</tr>

    <br>
	<tr class="tr3">
		<td align="center"><%=zh_mess2 %></td>
	</tr>


 <tr class="tr4">
 <td nowrap align="center">
	<c:choose>
	<c:when test="${backFlag==0}">
	</c:when>
	<c:otherwise>
	   <p align="center">
		<input class="button" id="subButton" type="button" value='<%=zh_close %>' onclick="javascript:top.location='login.do?action=logout'">
	   </p> 
	</c:otherwise>
	</c:choose>
	</td>
</tr>
</table>
</body>
</html>
