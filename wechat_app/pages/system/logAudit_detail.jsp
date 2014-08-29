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
<!-- 个人在jsp开发的过程中,css,字体,颜色不需要改变, 需要改变仅仅是table的width,为统一起见,查询页width=500,查询列表页width="750" -->
<!-- action请求 -->
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


<!--javascript 国际化标识符-->
<div id="javascriptI18n">
</div>
