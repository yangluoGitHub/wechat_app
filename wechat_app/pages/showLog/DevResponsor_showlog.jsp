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

<script type='text/javascript' src='../dwr/interface/devResponsorService.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>

<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/calendar3.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="../../scripts/common.jsp" %>
<script language="JavaScript">

function getResponsorName(){
		devResponsorService.qryDevResponsorBydevNo(form.devNo.value,
			function getResponsor(data){
				for(i=0;i<data.length;i++){
					if(data[i][0]==1){
						if(data[i][1]==1){
							form.admin1.value=data[i][2];
						}else if (data[i][1]==2){
							form.admin2.value=data[i][2];
						}else if (data[i][1]==3){
							form.admin3.value=data[i][2];
						}else{
							form.admin4.value=data[i][2];
						}
					}else{
						if(data[i][1]==1){
							form.service1.value=data[i][2];
						}else if (data[i][1]==2){
							form.service2.value=data[i][2];
						}else if (data[i][1]==3){
							form.service3.value=data[i][2];
						}
					}
				}
			}
		);
}
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

<c:set var="i" value="${1}"/> 
<c:forEach var="userArray" items="${objectvo}"  varStatus="status">
	<c:forEach var="user" items="${userArray}"  varStatus="status">
		<c:choose>
			<c:when test="${i==1}">
				<c:set var="admin1" value="${user}"/> 
			</c:when> 
			<c:when test="${i==2}">
				<c:set var="service1" value="${user}"/> 
			</c:when> 
			<c:when test="${i==3}">
				<c:set var="admin2" value="${user}"/> 
			</c:when> 
			<c:when test="${i==4}">
				<c:set var="service2" value="${user}"/>
			</c:when> 
			<c:when test="${i==5}">
				<c:set var="admin3" value="${user}"/> 
			</c:when> 
			<c:when test="${i==6}">
				<c:set var="service3" value="${user}"/>
			</c:when> 
			<c:when test="${i==7}">
				<c:set var="admin4" value="${user}"/> 
			</c:when> 
			<c:when test="${i==8}">
				<c:set var="devNo" value="${user}"/> 
			</c:when> 
		</c:choose>
		<c:set var="i" value="${i+1}"/> 
	</c:forEach>
</c:forEach>
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
						    <!--  <input type="hidden" name="devNo" value='<c:out value="${tmpdevNo}"/> '>-->
						    <td nowrap colspan="2"><b><%=resource.srcStr("Main.dev_no")%>:<c:out value="${devNo}"/></b> </td> 
						  </tr>
						<tr class="tr3">
						   <td nowrap width="300">
								 <%=resource.srcStr("System.management_first") %>：
						        <input type="text" name="admin1" class="paneGray" readonly value='<c:out value="${admin1}"/>' >&nbsp; </td>
							    <td width="300">
							    <%=resource.srcStr("System.staff_first")%>：
							    <input type="text" name="service1" class="paneGray" readonly value='<c:out value="${service1}"/>' >&nbsp; </td>
						  </tr>
						  <tr class="tr3">
							    <td nowrap width="300">
									 <%=resource.srcStr("System.management_second") %>：
							        <input type="text" name="admin2" class="paneGray" readonly value='<c:out value="${admin2}"/>' >&nbsp; </td>
							    <td width="300">
							    <%=resource.srcStr("System.staff_second")%>：
							    <input type="text" name="service2" class="paneGray" readonly value='<c:out value="${service2}"/>' >&nbsp; </td>
						  </tr>
						  <tr class="tr3">
							    <td nowrap width="300">
									 <%=resource.srcStr("System.management_third") %>：
							        <input type="text" name="admin3" class="paneGray" readonly value='<c:out value="${admin3}"/>' >&nbsp; </td>
							    <td width="300">
							    <%=resource.srcStr("System.staff_third")%>：
							    <input type="text" name="service3" class="paneGray" readonly value='<c:out value="${service3}"/>' >&nbsp; </td>
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
