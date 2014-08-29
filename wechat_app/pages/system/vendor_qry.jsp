<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource,java.util.Set"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=gbk" />
<title></title>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
</head>

<body>
<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<input type="hidden" id="Main.sure_delete" value='<%=resource.srcStr("Main.sure_delete")%>'/>
<input type="hidden" id="Login.op_no_per" value='<%=resource.srcStr("Login.op_no_per")%>'/>
</div>
<script language="JavaScript">
function del(obj1,obj2){
   <%  Set authButton = (Set)request.getSession().getAttribute("_Auth_Button");
     if (!authButton.contains("vendor.do?action=del") ) { %>
     alert(document.getElementById('Login.op_no_per').value);return ;
  <%  } %> 
   if(confirm(document.getElementById('Main.sure_delete').value)){
     window.location="vendor.do?action=del&no="+obj1+"&name="+obj2;
    }
}
function mod(obj){
  window.location="vendor.do?action=modPage&no="+obj;
}

function restore(){
  $('devVendorFilter').value = "";
  $('statusFilter').value = "";
}
</script>


<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
 <tr>
  <td nowrap><b><%=resource.srcStr("System.management_dev_brand")%>:</b></td>
    <td nowrap align="right">
  	 <input name="submit" type="button" class="button" style="width:100px;" onClick="javascript:window.location='vendor.do?action=addPage'" value='<%=resource.srcStr("System.add_dev_brand") %>'>&nbsp;
   </td>
 </tr> 
</table>


<br>

<table width="650" align="center" border="0" cellspacing="1" cellpadding="3" class="table1">
<tr class="tr1">
    <td nowrap width="100"><%=resource.srcStr("Main.op")%></td>
    <td nowrap width="50"><%=resource.srcStr("Main.serial_number")%></td>
    <td nowrap><%=resource.srcStr("System.brand_name")%></td>
    <!-- td nowrap>生产商国家或地区</td>
	<td nowrap>生产商地址</td>
	<td nowrap>生产商热线1</td>
	<td nowrap>生产商热线2</td>
	<td nowrap>生产商状态</td>-->
  </tr>

  <c:set var="i" value="0" scope="request"/>
  <c:forEach items="${resultList}" var="vendor"  step="1" >
      <c:set var="i" value="${i+1}" scope="request"/> 
      <tr class="tr3">      
      <td nowrap width="120">
        <a href="javascript:mod('<c:out value="${vendor.no}"/>')"><%=resource.srcStr("System.mod")%></a>&nbsp;
        <a href="javascript:del('<c:out value="${vendor.no}"/>','<c:out value="${vendor.name}"/>')"><%=resource.srcStr("Main.delete")%></a>
      </td>      
      <td nowrap><c:out value="${i}"/></td> 
      <td nowrap><c:out value="${vendor.name}"/></td>
      <!-- td nowrap><c:out value="${item.country}"/></td>
	  <td nowrap><c:out value="${item.address}"/></td>
	  <td nowrap><c:out value="${item.hotline1}"/></td>
	  <td nowrap><c:out value="${item.hotline2}"/></td>
	  <td nowrap>
	   <c:choose>
	    <c:when test="${item.status == 1}">设备供应</c:when>
	    <c:when test="${item.status == 2}">设备服役</c:when>
	    <c:when test="${item.status == 3}">设备退役</c:when>
	    <c:otherwise>&nbsp;</c:otherwise>
	  </c:choose>
	  </td>	-->

    </tr>
  </c:forEach>

</table>


</body>

</html>




