<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page import="java.util.Date"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>

<head>
<meta http-equiv="Content-Type" content="text/html;charset=gbk" />
<title></title>
</head>
<script type='text/javascript' src='../dwr/interface/orgService.js'></script>
<script type='text/javascript' src='../dwr/interface/projectTypeService.js'></script>
<script type='text/javascript' src='../dwr/interface/devTypeService.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>

<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/calendar3.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">

<body>

<script language="JavaScript">
</script>

<!-- ������jsp�����Ĺ�����,css,����,��ɫ����Ҫ�ı�, ��Ҫ�ı������table��width,Ϊͳһ���,��ѯҳwidth=500,��ѯ�б�ҳwidth="750" -->
<!-- action���� -->
<form name="form" method="post" action="" >
<input type="hidden" name="flag" value='<%=request.getParameter("flag") %>'>
<input type="hidden" name="userId" value='<c:out value="${logvo.userId}"/>'>
<input type="hidden" name="date" value='<c:out value="${logvo.date}"/>'>
<%
	request.setAttribute("flag",request.getParameter("flag"));
%>
<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
		 <tr>
		  <td nowrap><b>��ϸ��Ϣ:</b></td>			    
		 </tr>
		  
</table>
<br>				
<table align="center" width="750" border="0" cellspacing="1" cellpadding="6" class="table1">
 <tr class="tr1">
    <td nowrap colspan="4" align="center"><c:out value="${title}"/></td>
  </tr>
  	<tr class="tr3">
		<td nowrap width="10%">�豸�ţ�</td>
		<td nowrap width="40%"><c:out value="${objectvo.devNo}"/></td>
		<td nowrap width="10%">ͣ�����</td>
		<td nowrap width="40%">
			<c:if test="${objectvo.stopdevType==0}">�ż�</c:if>
			<c:if test="${objectvo.stopdevType==1}">װ��</c:if>
			<c:if test="${objectvo.stopdevType==2}">ͣ��</c:if>
			<c:if test="${objectvo.stopdevType==3}">�豸����δ�޸�</c:if>
			<c:if test="${objectvo.stopdevType==9}">����</c:if>
		</td>
	</tr>
	<tr class="tr3">
		<td nowrap width="10%">ͣ����ʼ����:</td>
		<td nowrap width="40%"><c:out value="${objectvo.stopdevStartTime}"/></td>
		<td nowrap width="10%">ͣ����������:</td>
		<td nowrap width="40%"><c:out value="${objectvo.stopdevEndTime}"/></td>
	</tr>
	<tr class="tr3">
		<td nowrap>ͣ��ԭ��</td>
		<td nowrap colspan="3">
			<textarea style="width:500px;overflow:hidden;" readonly><c:out value='${objectvo.stopdevReason}'/></textarea>
		</td>
	</tr>
</table>	
<br>
<table width="100%">
	<tr>
	  	<td align="center">
	  		<input class="button" type="button" value="����" onclick="javascript:window.history.back();">
	  	</td>
  	</tr>

</table>	
</form> 
</body>  	  