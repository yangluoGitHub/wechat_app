<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
</head>


<body>
<%@ include file="../../scripts/common.jsp" %>
<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
 <tr>
  <td nowrap><b><%=resource.srcStr("System.sort_dev_module")%>:</b></td>
 </tr> 
</table>
 <br>

<!--  <br>
<div align="center" style="font-size:14px;"><b><%=resource.srcStr("System.sort_dev_module")%></b></div>
<br>-->

<table width="650" align="center" border="0" cellspacing="1" cellpadding="3" class="table1">
  <tr class="tr1">
    <td nowrap width="100"><%=resource.srcStr("Main.serial_number")%></td>
    <td nowrap width="100"><%=resource.srcStr("System.module_code")%></td>
    <td nowrap><%=resource.srcStr("System.module_name")%></td>
  </tr>
  
  <c:set var="i" value="0" scope="request"/>
  
  <c:forEach items="${resultList}" var="item"  step="1" >
     <c:set var="i" value="${i+1}" scope="request"/>
     <tr class="tr3">
      <td nowrap><c:out value="${i}"/></td>          
      <td nowrap><c:out value="${item.no}"/></td>
      <td nowrap><c:out value="${item.name}"/></td>
	 </tr>
  </c:forEach>

</table>


</body>

</html>




<!--javascript 国际化标识符-->
<div id="javascriptI18n">
</div>
