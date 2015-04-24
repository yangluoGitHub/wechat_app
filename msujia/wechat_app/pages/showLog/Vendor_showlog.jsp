<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page import="java.util.Date"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>

<head>
<meta http-equiv="Content-Type" content="text/html;charset=gbk" />
<title></title>

<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/calendar3.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="../../scripts/common.jsp" %>
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
<input type="hidden" name="flag" value='<%=request.getParameter("flag") %>'>
<input type="hidden" name="userId" value='<c:out value="${logvo.userId}"/>'>
<input type="hidden" name="date" value='<c:out value="${logvo.date}"/>'>
<%
	request.setAttribute("flag",request.getParameter("flag"));
%>
						<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
								 <tr>
								  <td nowrap><b><%=resource.srcStr("System.detail_info")%>:</b></td>			    
								 </tr>
								  
						</table>
						
						<br>	
						
						<table align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1">
						  <tr class="tr1">
						    <td nowrap colspan="2" align="center"><c:out value="${title}"/></td>
						  </tr> 
						  <tr class="tr3">
						    <td nowrap><%=resource.srcStr("System.brand_name") %>：</td>
						    <td nowrap><input type="text" name="name" class="paneGray" value='<c:out value="${objectvo.name}"/>' readonly>&nbsp;</td> 
						  </tr>
						  
						</table>
						
						<br>
						
						<table width="100%">
			 			<tr>
        					<td align="center">
        						<c:if test="${flag==2}">
									<input class="button" type="button" value='<%=resource.srcStr("System.pass")%>' onclick="javascript:audit();">
									<input class="button" type="button" value='<%=resource.srcStr("System.refuse") %>' onclick="javascript:refuse();">
								</c:if>
        						<input class="button" type="button" value='<%=resource.srcStr("Main.back")%>' onclick="javascript:window.history.back();">
        					</td>
        				</tr>
			  
						</table>	
</form> 
</body>  	  


<!--javascript 国际化标识符-->
<div id="javascriptI18n">
</div>
