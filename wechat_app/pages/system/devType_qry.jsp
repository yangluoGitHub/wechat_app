<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource,java.util.Set"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.weili.wechat.hibernate.*" %>


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
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="Main.sure_delete" value='<%=resource.srcStr("Main.sure_delete")%>'/>
<input type="hidden" id="Login.op_no_per" value='<%=resource.srcStr("Login.op_no_per")%>'/>
</div>
<script language="JavaScript">
function del(obj){
   <%  Set authButton = (Set)request.getSession().getAttribute("_Auth_Button");
     if (!authButton.contains("type.do?action=del") ) { %>
     alert(document.getElementById('Login.op_no_per').value);return ;
   <%  } %> 
 
   if(confirm(document.getElementById('Main.sure_delete').value)){
     window.location="type.do?action=del&no="+obj;
    }
}
function mod(obj){
  window.location="type.do?action=modPage&no="+obj;
}

function restore(){
  $('devTypeFilter').value = "";
  $('devVendorFilter').value= "";
  $('devCatalogFilter').value = "";
}
</script>

<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
 <tr>
  <td nowrap><b><%=resource.srcStr("System.management_devnum_type")%>:</b></td>
    <td nowrap align="right">
  	 <input name="submit" type="button" class="button" style="width:100px;" onClick="javascript:window.location='type.do?action=addPage'" value='<%=resource.srcStr("System.add_dev_typenum")%>'>&nbsp;
   </td>
 </tr> 
</table>

<br>

<table width="650" align="center" border="0" cellspacing="1" cellpadding="3" class="table1">
  <tr class="tr1">
    <td nowrap width="100"><%=resource.srcStr("Main.op")%></td>
    <td nowrap><%=resource.srcStr("Main.serial_number")%></td>
    <td nowrap><%=resource.srcStr("Main.devType")%></td>
    <td nowrap><%=resource.srcStr("System.own_brand") %></td>
    <td nowrap><%=resource.srcStr("System.own_type") %></td>
  </tr>
<%
	HashMap vendorMap = (HashMap)request.getAttribute("vendorMap");
	HashMap catalogMap = (HashMap)request.getAttribute("catalogMap");
	List devTypeList = (List)request.getAttribute("devTypeList");
	int j=0;
	for(int i=0;i<devTypeList.size();i++){
	DevTypeTable devType = (DevTypeTable)devTypeList.get(i);
	
%>
	<tr class="tr3">      
      <td nowrap>
        <a href="javascript:mod('<%=devType.getNo() %>')"><%=resource.srcStr("System.mod")%></a>&nbsp;
        <a href="javascript:del('<%=devType.getNo() %>')"><%=resource.srcStr("Main.delete")%></a>
      </td>
      <td nowrap><%=++j %></td>        
      <td nowrap><%=devType.getName() %></td>     
	  <td nowrap><%=vendorMap.get(Integer.valueOf(devType.getDevVendorTable().getNo())) %></td>
	  <td nowrap><%=catalogMap.get(Integer.valueOf(devType.getDevCatalogTable().getNo())) %></td>
    </tr>
<% 
	}
%> 
</table>


</body>

</html>




