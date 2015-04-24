<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><%
	Resource resource = (Resource) GetResource.getOneResource(
			application, session.getAttribute("locale").toString());
%>
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
<script type='text/javascript' src='../dwr/interface/roleService.js'></script>
<script type='text/javascript' src='../dwr/interface/userService.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>

<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/calendar3.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">

<!--国际化标识符-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="System.delete_user" value='<%=resource.srcStr("System.delete_user")%>'/>
<input type="hidden" id="System.delete_user" value='<%=resource.srcStr("System.delete_user")%>'/>
</div>


<body>


<script language="JavaScript">
function audit(){
    form.action="operateAudit.do?action=audit";
    form.submit();
}

function refuse(){
    form.action="operateAudit.do?action=refuse";
    form.submit();
}
</script>



<!-- 个人在jsp开发的过程中,css,字体,颜色不需要改变, 需要改变仅仅是table的width,为统一起见,查询页width=500,查询列表页width="750" -->
<!-- action请求 -->
<form name="form" method="post" action="" >
<input type="hidden" name="flag" value='<%=request.getParameter("flag")%>'>
<input type="hidden" name="userId" value='<c:out value="${logvo.userId}"/>'>
<input type="hidden" name="date" value='<c:out value="${logvo.date}"/>'>
<input type="hidden" name="title" value='<c:out value="${title}"/>'>
<%
	request.setAttribute("flag", request.getParameter("flag"));
%>
	<table width="100%"
		style="border: 0px; border-bottom: 1px solid #cccccc">
		<tr>
			<td nowrap>
				<b><%=resource.srcStr("System.detail_info")%>:</b>
			</td>
		</tr>
	</table>
	<br>	
	<%
		request.setAttribute("mod_psw_user", resource.srcStr("System.mod_psw_user"));
		request.setAttribute("reset_user_psw", resource.srcStr("System.reset_user_psw"));
		request.setAttribute("delete_user", resource.srcStr("System.delete_user"));
	%>
	<table align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1">
	  <tr class="tr1">
	  	<c:choose>
		<c:when test='${title=="修改密碼系統用戶"}'>
			<td nowrap colspan="2" align="center"><c:out value="${mod_psw_user}"/></td>
		</c:when>
		<c:when test='${title=="重置密碼系統用戶"}'>
			<td nowrap colspan="2" align="center"><c:out value="${reset_user_psw }"/></td>
		</c:when>
		<c:otherwise>
		   <td nowrap colspan="2" align="center"><c:out value="${title}"/></td>
		</c:otherwise>
		</c:choose>
	    
	  </tr> 
	  <tr class="tr3">
	    <td nowrap><%=resource.srcStr("Main.no")%>：</td>
	    <td nowrap><input type="text" name="no" class="paneGray" value='<c:out value="${objectvo.no}"/>' readonly>&nbsp;</td> 
	  </tr>
	  <tr class="tr3">
	    <td nowrap><%=resource.srcStr("System.name")%>：</td>
	    <td nowrap><input type="text" name="name" class="paneGray" readonly value='<c:out value="${objectvo.name}"/>' >&nbsp;</td> 
	  </tr>
	  <c:if test='${title!=delete_user}'>
		  <tr class="tr3">
		    <td nowrap>
				 <%=resource.srcStr("System.role")%>：
			</td>
		    <td nowrap>
		        <input type="hidden" class="paneGray" name="roleNo" readonly style="width:260px;" value='<c:out value="${objectvo.role.no}"/>'>
				<input type="text" class="paneGray" name="roleName" readonly style="width:260px;" value="${objectvo.role.name }" >
				&nbsp;
		     </td> 
		  </tr>
		   <tr class="tr3">
		    <td nowrap>
				 工号：
			</td>
		    <td nowrap>
				<input type="text" class="paneGray" name="roleName" readonly style="width:260px;" value="${objectvo.no }" >
				&nbsp;
		     </td> 
		  </tr>
		  <tr class="tr3">
		    <td nowrap>
				 <%=resource.srcStr("Main.own_org")%>：
			</td>
		    <td nowrap>
		        <input type="hidden" class="paneGray" name="orgNo" readonly style="width:132px;" value='<c:out value="${objectvo.org.no}"/>'>
				<input type="text" class="paneGray" name="orgName" id="_orgName" readonly style="width:132px;" value="${objectvo.org.name }" >
				&nbsp;
		     </td> 
		  </tr>
		  <tr class="tr3">
		    <td nowrap><%=resource.srcStr("System.fixed_telephone")%>：</td>
		    <td nowrap><input type="text" name="telephone" class="paneGray" readonly value='<c:out value="${objectvo.telephone}"/>' >&nbsp;</td> 
		  </tr>
		  <tr class="tr3">
		    <td nowrap><%=resource.srcStr("System.mobile")%>：</td>
		    <td nowrap><input type="text" name="mobile" class="paneGray" readonly value='<c:out value="${objectvo.mobile}"/>' >&nbsp;</td> 
		  </tr>
		  <tr class="tr3">
		    <td nowrap><%=resource.srcStr("System.email")%>：</td>
		    <td nowrap><input type="text" name="email" class="paneGray" readonly value='<c:out value="${objectvo.email}"/>' >&nbsp;</td> 
		  </tr>
	  </c:if>
	</table>
	
	<br>
	
	<table width="100%">
		<tr>
   					<td align="center">
   						<c:if test="${flag==2}">
				<input class="button" type="button" value='<%=resource.srcStr("System.pass")%>' onclick="javascript:audit();">
				<input class="button" type="button" value='<%=resource.srcStr("System.refuse")%>' onclick="javascript:refuse();">
			</c:if>
   						<input class="button" type="button" value='<%=resource.srcStr("Main.back")%>' onclick="javascript:window.history.back();">
   					</td>
   				</tr>

	</table>	
</form> 
</body>  	  


