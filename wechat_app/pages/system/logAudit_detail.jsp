<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
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
</head>
<%@ include file="../../scripts/common.jsp" %>
<!-- ������jsp�����Ĺ�����,css,����,��ɫ����Ҫ�ı�, ��Ҫ�ı������table��width,Ϊͳһ���,��ѯҳwidth=500,��ѯ�б�ҳwidth="750" -->
<!-- action���� -->
<%
	com.weili.wechat.vo.LogAudit aLogAudit = (com.weili.wechat.vo.LogAudit)request.getAttribute("logAudit");
	//System.out.println(aLogAudit.getXml());
	request.setAttribute("temp",aLogAudit.getXml().replaceAll("&lt;","<").replaceAll("&gt;",">"));
%>
<form name="form" method="post" action="">
						<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
								 <tr>
								  <td nowrap><b><%=resource.srcStr("System.detail_op_info")%>:</b></td>			    
								 </tr>
								  
						</table>
						
						<br>	
			
						<table width="650" align="center" border="0" cellspacing="1" cellpadding="3" class="table1">	
          					<tr class="tr1">
								<td nowrap align="left"><pre><c:out value="${temp}"/></pre></td>	
							</tr>       					
						</table>
						
						<br>
						
						<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
			 			<tr>
        					<td align="center">
        						<input class="button" type="button" value='<%=resource.srcStr("Main.back")%>' onclick="javascript:window.history.back();">
        					</td>
        				</tr>
			  
						</table>	
</form>   	  


<!--javascript ���ʻ���ʶ��-->
<div id="javascriptI18n">
</div>
