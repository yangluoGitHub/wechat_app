<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page import="java.util.Date"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<head>
<meta http-equiv="Content-Type" content="text/html;charset=gbk" />
<title></title>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/calendar3.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">

<script language="JavaScript">

//����һ����¼

function audit(userId,date){
    form.action="operateAudit.do?action=audit&userId="+userId+"&date="+date;
    form.submit();
}

</script>

</head>

<!-- ������jsp�����Ĺ�����,css,����,��ɫ����Ҫ�ı�, ��Ҫ�ı������table��width,Ϊͳһ���,��ѯҳwidth=500,��ѯ�б�ҳwidth="750" -->
<form name="form" action="" method=post>
<input type="hidden" name="userId" value="<%=request.getParameter("userId") %>">
<input type="hidden" name="module" value="<%=request.getParameter("module") %>">
<input type="hidden" name="operate" value="<%=request.getParameter("operate") %>">
<input type="hidden" name="startDate" value="<%=request.getParameter("startDate") %>">
<input type="hidden" name="endDate" value="<%=request.getParameter("endDate") %>">
<input type="hidden" name="flag" value="<%=request.getParameter("flag") %>">
<%
	request.setAttribute("flag",request.getParameter("flag"));
 %>
	<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
			 <tr>
			  <td nowrap><b>
			  <c:if test="${flag==1}">�鿴���˴���˲����嵥:</c:if>
			  <c:if test="${flag==2}">������˴���˲����嵥:</c:if>
			  </b></td>			    
			 </tr>
			  
	</table>
	
	<br>
	
	<table width="750" align="center" border="0" cellspacing="1" cellpadding="3" class="table1">
          			<tr class="tr1">
								<td nowrap align="center" width="10%" class="title">����Ա���</td>
								<td nowrap align="center" width="15%" class="title">����Ա</td>
								<td nowrap align="center" width="10%" class="title">����</td>
								<td nowrap align="center" width="15%" class="title">ģ��</td>
								<td nowrap align="center" width="20%" class="title">����ʱ��</td>
								<td nowrap align="center" width="15%" class="title">
									<c:if test="${flag==1}">�鿴</c:if>
			  						<c:if test="${flag==2}">���</c:if>
			  					</td>
					</tr>
					<c:forEach var="audit" items="${auditList}"  varStatus="status">
								<tr class="tr3">
									<td nowrap align="center"><c:out value="${audit.userId}"/></td>
  									<td nowrap align="center"><c:out value="${audit.userName}"/></td> 
  									<td nowrap align="center"><c:out value="${audit.operate.name}"/></td>	
  									<td nowrap align="center"><c:out value="${audit.module.name}"/></td>									
  									<td nowrap align="center"><c:out value="${audit.date}"/></td>							
									<td nowrap align="center"><a href="javascript:window.location='operateAudit.do?action=qry_Detail&userId=<c:out value="${audit.userId}"/>&date=<c:out value="${audit.date}"/>&flag=<c:out value="${flag}"/>'">									<c:if test="${flag==1}">�鿴</c:if>
			  						<c:if test="${flag==2}">���</c:if></a></td>	
  								</tr>
					</c:forEach>
						
		</table>
		
		<br> 
		
		<table width="100%" >
			 <tr>
        			<td align="center">
        			<!-- 
        				<input type="button" class="button" style="width:90px;" value="����" onclick="javascript:window.location='operateAudit.do?action=qry&flag=<%=request.getParameter("flag") %>'">&nbsp;
        			 -->
        			</td>
        	</tr>
			  
		</table>
				
</form>    	  