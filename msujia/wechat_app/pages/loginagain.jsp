<%@ page language="java"%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page import="com.weili.wechat.common.UserSession"%>
<%
UserSession us = (UserSession)session.getAttribute("userSession");
String newuser = "";
if(us != null){
	newuser = us.getAccount();
}
%>

<%
     String zh_mess1 = "�����û���¼";String tw_mess1 = "�����Ñ����";String cn_mess1 = "A new user logs on";
     String zh_mess2 = "��ʾ��Ϣ";String tw_mess2 = "��ʾ��Ϣ";String cn_mess2 = "Tips";
     String zh_mess3 = "�����Ñ�";String tw_mess3 = "�����û�";String cn_mess3 = "New user!";
     String zh_mess4 = "�ڱ��������¼����ǰ�û���ǿ���˳���";String tw_mess4 = "�ڱ��g�[����䛣���ǰ�Ñ��я����˳�!";String cn_mess4 = "In this browser, log on, the current user has been forced to withdraw";
     String zh_exit = "�˳�";String tw_exit="�˳�";String cn_exit="exit";
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<link href="../styles/common.css" rel="stylesheet" type="text/css">
<title><%=zh_mess1 %>!</title>
</head>

<body>



<br><br>



<div align="center" style="font-size:14px;"><b><%=zh_mess1 %></b></div>
<table align="center" width="450" border="0" cellspacing="1" cellpadding="4" class="table1">

	<tr class="tr1">
		<td align="left"><img src="../images/notify.gif"><%=zh_mess2 %></td>
	</tr>

    <br>
	<tr class="tr3">
		<td align="center"><%=zh_mess3 %><%=newuser %><%=zh_mess4 %></td>
	</tr>


 <tr class="tr4">
 <td nowrap align="center">
	<c:choose>
	<c:when test="${backFlag==0}">
	</c:when>
	<c:otherwise>
	   <p align="center">
		<input class="button" type="button" value='<%=zh_exit %>' onclick="javascript:top.close()">
	   </p> 
	</c:otherwise>
	</c:choose>
	</td>
</tr>
</table>
</body>
</html>
