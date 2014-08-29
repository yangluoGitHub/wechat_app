<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page import="java.util.*" %>
<%@ page import="com.weili.wechat.vo.*" %>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp"%>
<%@ page import="com.weili.wechat.common.UserSession"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="../pages/tree/js/tree2.js"></script>
<link href="../pages/tree/css/tree.css" rel="StyleSheet" type="text/css">
</head>

<body>
<%@ include file="../../scripts/common.jsp" %>
<script type="text/javascript">
var j$ = jQuery.noConflict();

j$().ready(function(){
	var upaList = j$("#upaList").val();
	j$("input[name='checkPA']").each(function(){
		var id = j$(this).val().split("|")[0];
		if(upaList.indexOf(id)>0){
			j$(this).attr("checked","checked");	
		}
	});
});

</script>
<br>
<form name="form1">
<input name="status" value="1" type="hidden"/>
<input id="upaList" value="${upaList}" type="hidden"/>
<table align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1">
  <tr class="tr1">
    <td nowrap colspan="2" align="center">
    <c:if test="${roleNo_Query==100}"><%=resource.srcStr("System.detail_management")%></c:if>
    <c:if test="${roleNo_Query==200}"><%=resource.srcStr("System.detail_staff")%></c:if>
    <c:if test="${roleNo_Query!=100 && roleNo_Query!=200}"><%=resource.srcStr("System.detail_sysuser_info")%></c:if>
    </td>
  </tr> 
 		<c:choose>
		    <c:when test="${roleNo_Query ==100 || roleNo_Query ==200}">
			    <input type="hidden" name="no" value="<c:out value="${user.no}"/>">
			</c:when>
		    <c:otherwise>
  <tr class="tr3">
    <td nowrap width="100"><%=resource.srcStr("Main.no") %>：</td>
    <td nowrap>
    	<input type="hidden" name="no" value="<c:out value="${user.no}"/>" readonly>
    	<c:out value="${user.no}"/>
    </td> 
  </tr>
		    </c:otherwise>
		</c:choose>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.name") %>：</td>
    <td nowrap>
    	<input style="width=150px" type="hidden" name="name" readonly class="pane" value="<c:out value="${user.name}"/>" >
    	<c:out value="${user.name}"/>
    </td> 
  </tr>
  		<c:choose>
		    <c:when test="${roleNo_Query ==100 || roleNo_Query ==200}">
			   <input type="hidden" name="roleNo" value="<c:out value="${user.no}"/>">
			</c:when>
		    <c:otherwise>  
    <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.role") %>：</td>
    <td nowrap>
		<input type="hidden" readonly name="roleNo" style="width:240px" value="<c:out value="${user.role.name}"/>">
		<c:out value="${user.role.name}"/>
    </td> 
  </tr>
  			</c:otherwise>
  		</c:choose>  
   <tr class="tr3">
    <td nowrap>用户状态：</td>
    <td nowrap>
    	<c:if test="${user.status==0}">停用</c:if>
    	<c:if test="${user.status==1}">启用</c:if>
    	<c:if test="${user.status==2}">锁定</c:if>
    </td>
  </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.fixed_telephone") %>：</td>
    <td nowrap>
    	<input type="hidden" name="telephone" readonly value="<c:out value="${user.telephone}"/>" style="ime-mode:disabled" >
    	<c:out value="${user.telephone}"/>
    </td> 
  </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.mobile") %>：</td>
    <td nowrap>
    	<input type="hidden" name="mobile" readonly value="<c:out value="${user.mobile}"/>" style="ime-mode:disabled" >
    	<c:out value="${user.mobile}"/>
    </td> 
  </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.email") %>：</td>
    <td nowrap>
    	<input type="hidden" name="email" readonly value="<c:out value="${user.email}"/>" >
    	<c:out value="${user.email}"/>	
    </td> 
  </tr>
  <tr class="tr3">
    <td nowrap>签到标志：</td>
    <td nowrap>
        <c:if test="${user.signFlag==0}">签退</c:if>
        <c:if test="${user.signFlag==1}">签到</c:if>
    </td>
  </tr>
  <tr class="tr3">
    <td nowrap>公众号权限：</td>
     <td nowrap>
     	<c:forEach items="${paList}" var="pa">
     		<input type="checkbox" name="checkPA" disabled="true" value="${pa[0]}|${pa[1]}"/><c:out value="${pa[1]}"></c:out><br>
     	</c:forEach>
     </td>
  </tr>
  <tr class="tr3">
    <td nowrap>默认公众号：</td>
    <td nowrap>
        <c:out value="${user.wechatName}"></c:out>
    </td>
  </tr>
</table>

<p align="center">
	<input class="button" type="button" value='<%=resource.srcStr("Main.back")%>' onclick="javascript:window.history.back();">
</p>
</form>
</body>
</html>

<!--javascript 国际化标识符-->
<div id="javascriptI18n">
</div>
