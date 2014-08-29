<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk">
		<script type='text/javascript' src='../dwr/interface/orgService.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type='text/javascript' src='../dwr/util.js'></script>
		
		<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
		<script language="JavaScript" src="../scripts/calendar.js" type="text/javascript"></script>
		<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
		<script language="JavaScript" src="../scripts/orgSelector.js" type="text/javascript"></script>
		<link href="../styles/common.css" rel="stylesheet" type="text/css">
		
		<title></title>
	</head>

<script language="JavaScript">
var j$ = jQuery.noConflict();

</script>
	
<body>
<!--javascript ���ʻ���ʶ��-->
<div id="javascriptI18n">
</div>


<form name="infoForm" method="post" action="" >

<table align="center" id="dev1" width="780" border="0" cellspacing="1" cellpadding="5" class="table1">
  <tr class="tr1">
    <td nowrap colspan="2" align="center">${title }</td>
  </tr>
  <tr class="tr3">
    <td nowrap>���񵥱��:</td>
    <td nowrap>
		<c:out value="${objectvo.dispatchNo}"/>
	</td>
  </tr> 
  <tr class="tr3">
    <td nowrap>��·:</td>
    <td nowrap>
		<c:out value="${objectvo.routeNo}"/>
	</td>
  </tr> 
  <tr class="tr3"> 
    <td nowrap>�ӳ�����Ա1:</td>
    <td nowrap id="tagTypeMapTd">
    	<c:out value="${objectvo.addnotesOpName1}"></c:out>
    </td>     
  </tr>
  <tr class="tr3"> 
    <td nowrap>�ӳ�����Ա2:</td>
    <td nowrap id="tagStatusMapTd">
    	<c:out value="${objectvo.addnotesOpName2}"></c:out>
    </td>     
  </tr>
  <tr class="tr3"> 
    <td nowrap>�ӳ�����:</td>
    <td nowrap>
        <c:out value="${objectvo.addnotesDate}"/>
    </td>     
  </tr>
  <tr class="tr3"> 
    <td nowrap>�ӳ�ʱ��:</td>
    <td nowrap>
    	<c:if test="${objectvo.addnotesTime==1}">����</c:if>
    	<c:if test="${objectvo.addnotesTime==2}">����</c:if>
    	<c:if test="${objectvo.addnotesTime==3}">������</c:if>
    	<c:if test="${objectvo.addnotesTime==4}">����</c:if>
    </td>     
  </tr>
  <tr class="tr3"> 
    <td nowrap>������������:</td>
    <td nowrap>
        <c:out value="${objectvo.addDate}"/>
    </td>     
  </tr>
  <tr class="tr3"> 
    <td nowrap>��������ʱ��:</td>
    <td nowrap>
        <c:out value="${objectvo.addTime}"/>
    </td>     
  </tr>
  <tr class="tr3"> 
    <td nowrap>����������:</td>
    <td nowrap>
        <c:out value="${objectvo.addOpName}"/>
    </td>     
  </tr>
  <tr class="tr3"> 
    <td nowrap>��ע:</td>
    <td nowrap>
        <textarea rows="4" cols="23" name="notes" maxlength="100"><c:out value="${objectvo.note}"/></textarea>
    </td>     
  </tr>
  
</table>

<p align="center">
    <input class="button" type="button" value='<%=resource.srcStr("Main.back")%>' onclick="javascript:window.history.back();">
</p>

</form>

</body>
</html>