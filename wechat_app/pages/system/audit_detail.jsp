<%@ page contentType="text/html; charset=gb2312"%>
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

</head>

<!-- 个人在jsp开发的过程中,css,字体,颜色不需要改变, 需要改变仅仅是table的width,为统一起见,查询页width=500,查询列表页width="750" -->
<!-- action请求 -->
<form name="form" method="post" action="">
<input type="hidden" name="flag" value='<%=request.getParameter("flag") %>'>
<input type="hidden" name="userId" value='<c:out value="${audit.userId}"/>'>
<input type="hidden" name="date" value='<c:out value="${audit.date}"/>'>
<%
	request.setAttribute("flag",request.getParameter("flag"));
%>
						<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
								 <tr>
								  <td nowrap><b>详细信息:</b></td>			    
								 </tr>
								  
						</table>
						
						<br>	
			
						<table width="650" align="center" border="0" cellspacing="1" cellpadding="3" class="table1">	
          					<tr class="tr1">
								<td nowrap align="left"><PRE><c:out value="${audit.params}"/></PRE></td>	
							</tr>       					
						</table>
						
						<br>
						
						<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
			 			<tr>
        					<td align="center">
        						<c:if test="${flag==2}">
									<input class="button" type="button" value="通过" onclick="javascript:audit();">
									<input class="button" type="button" value="拒绝" onclick="javascript:refuse();">
								</c:if>
        						<input class="button" type="button" value="返回" onclick="javascript:window.history.back();">
        					</td>
        				</tr>
			  
						</table>	
</form>   	  