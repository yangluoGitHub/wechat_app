<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource,java.util.Set"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
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

function del(roleNo,roleName){
  <%  Set authButton = (Set)request.getSession().getAttribute("_Auth_Button");
     if (!authButton.contains("role.do?action=del") ) { %>
     alert(document.getElementById('Login.op_no_per').value);return ;
  <%  } %> 

  if(confirm(document.getElementById('Main.sure_delete').value)){
    window.location="role.do?action=del&roleNo="+roleNo+"&roleName="+roleName;
    //alert("该操作已提交至审核记录中,请等待审核！");
  }
}
function mod(obj){
  window.location="role.do?action=modPage&roleNo="+obj;
}

</script>



<table width="100%" style="border:0px;border-bottom:1px solid #cccccc;">
  <tr> 
    <td nowrap><b><%=resource.srcStr("System.management_role") %>：</b></td> 
    <td nowrap align="right">
       <input class="button" type="button" value='<%=resource.srcStr("System.add_role")%>' onclick="javascript:window.location='role.do?action=addPage'" style="width:100px;">&nbsp;
    </td>
  </tr>
</table>
<br/>
<table width="100%" border="0" cellspacing="1" cellpadding="4" class="table1" style="word-break:break-all">
  <tr class="tr1">
    <td nowrap align="center"><%=resource.srcStr("Main.op")%></td>
    <td nowrap align="center">角色类型</td>
    <td nowrap align="center"><%=resource.srcStr("System.role_name")%></td>
    <td nowrap align="center"><%=resource.srcStr("System.role_des")%></td>
  </tr>
  <c:forEach items="${roleList}" var="role"  step="1" >
      <tr class="tr3">      
      <td nowrap width="10%">  
          <c:if test="${role.catalog != 1}">
          	<a href="javascript:mod('<c:out value="${role.no}"/>')"><%=resource.srcStr("System.mod")%></a>&nbsp; 
            <a href="javascript:del('<c:out value="${role.no}"/>','<c:out value="${role.name}"/>')"><%=resource.srcStr("Main.delete")%></a>
          </c:if>
      </td>
      <td nowrap width="10%"><c:if test="${role.catalog==1}">超级操作员 </c:if><c:if test="${role.catalog==2}">预设操作员 </c:if><c:if test="${role.catalog==3}">其他操作员</c:if></td>
      <td nowrap width="25%"><a href="role.do?action=detail&roleNo=<c:out value="${role.no}"/>"><c:out value="${role.name}"/></a></td>
      <td nowrap width="55%"><c:out value="${role.note}"/></td>
	 </tr>
  </c:forEach>

</table>
</body>

</html>





