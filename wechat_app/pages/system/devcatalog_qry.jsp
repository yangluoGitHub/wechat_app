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
<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="Main.sure_delete" value='<%=resource.srcStr("Main.sure_delete") %>'/>
</div>

<script language="JavaScript">
function del(obj){
  if(confirm(document.getElementById('Main.sure_delete').value)){
    window.location="devcatalog.do?action=del&no="+obj;
  }
}
function mod(obj){
  window.location="devcatalog.do?action=modPage&no="+obj;
}
</script>



<table width="100%" style="border:0px;border-bottom:1px solid #cccccc;">
  <tr> 
    <td nowrap><b><%=resource.srcStr("System.management_dev_type") %>：</b></td> 
    <td nowrap align="right">
       <input class="button" type="submit" value='<%=resource.srcStr("System.add_dev_type") %>' onclick="javascript:window.location='devcatalog.do?action=addPage'" style="width:100px;">&nbsp;
    </td>
  </tr>
</table>


<br>



<table align="center" width="650" border="0" cellspacing="1" cellpadding="3" class="table1">


  <tr class="tr1">
    <td nowrap width="120"><%=resource.srcStr("Main.op")%></td>
    <td nowrap width="80"><%=resource.srcStr("Main.serial_number")%></td>
    <td nowrap><%=resource.srcStr("Main.dev_type")%></td>
    <td nowrap width="250"><%=resource.srcStr("System.description") %></td>
  </tr>
  <c:set var="i" value="0" scope="request"/>
  <c:forEach items="${resultList}" var="item"  step="1" >
      <c:set var="i" value="${i+1}" scope="request"/> 
      <tr class="tr3">      
      <td nowrap>
        <a href="javascript:mod('<c:out value="${item.no}"/>')"><%=resource.srcStr("System.mod")%></a>&nbsp;
        <a href="javascript:del('<c:out value="${item.no}"/>')"><%=resource.srcStr("Main.delete")%></a>
      </td>        
      <td nowrap><c:out value="${i}"/></td> 
      <td nowrap><c:out value="${item.name}"/></td>
      <td nowrap><c:out value="${item.enname}"/></td>
	 </tr>
  </c:forEach>

</table>


</body>

</html>
