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
</head>

<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/calendar3.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">

<body >
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
	<table width="650" border="0" cellspacing="1" cellpadding="3" class="table1" align="center">
    	<tr class="tr1">
	    	<td nowrap colspan="4" align="center"><c:out value="${title}"/></td>
	    </tr> 
      	<tr class="tr1" style="height:30px;">
		  <td nowrap width="180"><%=resource.srcStr("System.para_name")%></td>
		  <td nowrap width="180"><%=resource.srcStr("System.para_value")%></td>		  
		  <td nowrap><%=resource.srcStr("System.para_explan") %></td>
		</tr>
		<c:if test="${objectvo.paramName == 'localIp'}">
	    	<tr class="tr3" style="height:30px;">
			  <td nowrap><%=resource.srcStr("System.v_ip") %>：</td>	  
			  <td nowrap><input type="text" class="paneGray" name="localIp" style="width:150px" value='<c:out value="${objectvo.paramValue}"/>' readonly></td>		  
			  <td nowrap></td>
			</tr>  	
    	</c:if>
		
		<c:if test="${objectvo.paramName == 'rvsIp'}">
			<tr class="tr3" style="height:30px;">
			  <td nowrap><%=resource.srcStr("System.sys_ip") %>：</td>	  
			  <td nowrap><input type="text" class="paneGray" name="rvsIp" style="width:150px" value='<c:out value="${objectvo.paramValue}"/>' readonly></td>
			  <td nowrap></td>
			</tr>	
		</c:if>
		<c:if test="${objectvo.paramName == 'monitorInterval'}">
			<tr class="tr3" style="height:30px;">
			  <td nowrap><%=resource.srcStr("System.dev_refresh") %>：</td>	  
			  <td nowrap><input type="text" class="paneGray" name="monitorInterval" style="width:150px" value='<c:out value="${objectvo.paramValue}"/>'readonly></td>
			  <td nowrap><%=resource.srcStr("System.unit_sec")%></td>
			</tr>
		</c:if>
		<c:if test="${objectvo.paramName == 'offlineInterval'}">
			<tr class="tr3" style="height:30px;">
			  <td nowrap><%=resource.srcStr("System.dev_inteval") %>：</td>	  
			  <td nowrap><input type="text" class="paneGray" name="offlineInterval" style="width:150px"  value='<c:out value="${objectvo.paramValue}"/>' readonly></td>
			  <td nowrap><%=resource.srcStr("System.unit_min")%></td>
			</tr>
		</c:if>
		<c:if test="${objectvo.paramName == 'cashWarnAmount'}">
	    	<tr class="tr3" style="height:30px;">
			  <td nowrap><%=resource.srcStr("System.alarm_box") %>：</td>	  
			  <td nowrap><input type="text" class="paneGray" name="cashWarnAmout" style="width:150px" value='<c:out value="${objectvo.paramValue}"/>' readonly></td>		  
			  <td nowrap><%=resource.srcStr("System.unit_yuan")%></td>
			</tr>  	
    	</c:if>
    	<c:if test="${objectvo.paramName == 'passwordUpdateDate'}">
	    	<tr class="tr3" style="height:30px;">
			  <td nowrap><%=resource.srcStr("system.passwordUpdateDate") %>：</td>	  
			  <td nowrap><input type="text" class="paneGray" name="passwordUpdateDate" style="width:150px" value='<c:out value="${objectvo.paramValue}"/>' readonly></td>		  
			  <td nowrap><%=resource.srcStr("System.unit_day")%></td>
			</tr>  	
    	</c:if>
    	<c:if test="${objectvo.paramName == 'passwdtxDate'}">
	    	<tr class="tr3" style="height:30px;">
			  <td nowrap><%=resource.srcStr("system.passwdtxDate") %>：</td>	  
			  <td nowrap><input type="text" class="paneGray" name="passwdtxDate" style="width:150px" value='<c:out value="${objectvo.paramValue}"/>' readonly></td>		  
			  <td nowrap><%=resource.srcStr("System.unit_day")%></td>
			</tr>  	
    	</c:if>
    	<c:if test="${objectvo.paramName == 'adviceImgSize'}">
	    	<tr class="tr3" style="height:30px;">
			  <td nowrap><%=resource.srcStr("system.adviceImgSize") %>：</td>	  
			  <td nowrap><input type="text" class="paneGray" name="adviceImgSize" style="width:150px" value='<c:out value="${objectvo.paramValue}"/>' readonly></td>		  
			  <td nowrap><%=resource.srcStr("System.unit_k")%></td>
			</tr>  	
    	</c:if>
    	<c:if test="${objectvo.paramName == 'passwdmaxCount'}">
	    	<tr class="tr3" style="height:30px;">
			  <td nowrap><%=resource.srcStr("system.passwdmaxCount") %>：</td>	  
			  <td nowrap><input type="text" class="paneGray" name="passwdmaxCount" style="width:150px" value='<c:out value="${objectvo.paramValue}"/>' readonly></td>		  
			  <td nowrap><%=resource.srcStr("System.unit_num")%></td>
			</tr>  	
    	</c:if>
    	<c:if test="${objectvo.paramName == 'auditFlag'}">
	    	<tr class="tr3" style="height:30px;">
			  <td nowrap><%=resource.srcStr("system.auditFlag") %>：</td>	
			  <td nowrap>
			  	<input type="radio" name="auditFlag" value="true" <c:out value="${auditFlag=='true'?'checked':''}"/>><%=resource.srcStr("system.auditFlag.yes") %>
			  	<input type="radio" name="auditFlag" value="false"<c:out value="${auditFlag=='false'?'checked':''}"/>><%=resource.srcStr("system.auditFlag.no") %>
			  </td>
			  <td nowrap><%=resource.srcStr("system.auditFlag.yes") %>/<%=resource.srcStr("system.auditFlag.no") %></td>
			</tr>  	
    	</c:if>
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
