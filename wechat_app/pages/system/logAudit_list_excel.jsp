<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page import="java.util.Date"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=gbk" />

<%
	response.setContentType("application/vnd.ms-excel;charset=GBK"); 
     
	//String filename=resource.srcStr("System.log_info_list")+".xls";
	String filename="syslog.xls";
	filename = new String(filename.getBytes("GBK"),"iso8859-1"); 
	response.setHeader("Content-disposition","attachment; filename="+filename);
%>


<title><%=resource.srcStr("System.log_info_list")%></title>
<style type="text/css">
body{
	font-size: 13px;	
	color: #000000;
}
table{
	font-size: 13px;	
	color: #000000;
}
td {
	font-size: 13px;
	color: #000000;	
}
textarea{
	font-size: 13px;
	color: #000000;	
}
a {
	font-size: 13px; text-decoration: underline;
}
a:link {
	color: #3366cc
}
a:visited {
	color: #3366cc
}
a:active {
	color: #990000
}
a:hover {
	left: 1px; color: #ff9900; position: relative; top: 1px
}


/*
-----------------------------------------------------------------------------------
自定义样式，在相应的标签中需要通过标签的class属性调用
-----------------------------------------------------------------------------------
*/


.pane{  
	font-size: 13px;
	border: 1px solid #7788aa;
	height:21px;
	width:180px;
}
.paneGray{
	font-size: 13px;
	border: 1px solid #999999;
	height:21px;
	color:#999999;
	width:180px;
}
.paneGray_auto{
	font-size: 13px;
	border: 1px solid #999999;
	height:21px;
	color:#999999;
	width:153px;
}
.radio{
	height:12px;
}
.checkbox{
	height:20px;
}
.button{
  width:62px; border-left: #2c59aa 1px solid; border-right: #2c59aa 1px solid; border-top: #2c59aa 1px solid; border-bottom: #2c59aa 1px solid; padding-top: 2px; padding-right: 2px; padding-left: 2px; font-size: 12px; filter: progid:dximagetransform.microsoft.gradient(gradienttype=0, startcolorstr=#ffffff, endcolorstr=#c3daf5); cursor: hand; color: black;
}
.showDate{
  width:30px; border-left: #2c59aa 1px solid; border-right: #2c59aa 1px solid; border-top: #2c59aa 1px solid; border-bottom: #2c59aa 1px solid; padding-top: 2px; padding-right: 2px; padding-left: 2px; font-size: 12px; filter: progid:dximagetransform.microsoft.gradient(gradienttype=0, startcolorstr=#ffffff, endcolorstr=#c3daf5); cursor: hand; color: black;
}
.inputDateText{text-align:left;border: 1px solid #bbbbff;height:20px;width: 105px;
}
.table1{                            
    background-color: #aaaaaa;
}
.tr1{
	height:22px;
	background-color: #DBE5EE;
}
.tr2{
	height:22px;
	background-color: #ffffff;    
	font-weight:bold;
}
.tr3{
	height:22px;
	background-color: #ffffff;    
}
.tr4{
	height:22px;
	background-color: #eeeeee;    
}
.td1{
	height:22px;
	background-color: #E8EFF7;    
}
.td2{
	height:22px;
	background-color: #ffffff;    
}
.onmouseover{background-color: #ECF3F9;
}
.onmouseout{background-color: #ffffff;
}
.date{
	mso-number-format:"yyyy/m/d h:mm:ss";
}
</style>
</head>

<!-- 个人在jsp开发的过程中,css,字体,颜色不需要改变, 需要改变仅仅是table的width,为统一起见,查询页width=500,查询列表页width="750" -->
<body>   
<form name="form">
	<table width="100%" align="center">
			 <tr>
			  <td><b><%=resource.srcStr("System.log_info_list")%></b></td>			    
			 </tr>
			  
	</table>
	<table width="750" align="center" border="1" cellspacing="1" cellpadding="3" class="table1">
          			<tr class="tr1">
								<td nowrap align="center" width="10%" class="title"><%=resource.srcStr("System.user")%>ID</td>
								<td nowrap align="center" width="10%" class="title"><%=resource.srcStr("Login.account") %></td>
								<td nowrap align="center" width="10%" class="title"><%=resource.srcStr("Main.module")%></td>
								<td nowrap align="center" width="10%" class="title"><%=resource.srcStr("Main.op")%></td>
								<td nowrap align="center" width="30%" class="title"><%=resource.srcStr("Main.op_time")%></td>
								<!--<td nowrap align="center" width="20%" class="title"><%=resource.srcStr("System.detail_op_info")%></td>-->
					</tr>
					<c:forEach var="logAudit" items="${logAuditList}"  varStatus="status">
					           
								<tr class="tr3">
									<td nowrap align="center"><c:out value="${logAudit.userId}"/></td>
  									<td nowrap align="center"><c:out value="${logAudit.userName}"/></td> 
  									<td nowrap align="center"><c:out value="${logAudit.module.name}"/></td>	
  									<td nowrap align="center"><c:out value="${logAudit.operate.name}"/></td>									
  									<td nowrap align="center" class="date"><c:out value="${fn:substring(logAudit.date,0,19)}"/></td>
  									
  									<!--<td nowrap align="center"><a href="javascript:window.location='logAudit.do?action=qry_Detail&userId=<c:out value="${logAudit.userId}"/>&date=<c:out value="${logAudit.date}"/>'">操作详细</a></td>	-->
  								</tr>
					</c:forEach>
						
		</table>
		
</form> 
</body>
</html>  	  


<!--javascript 国际化标识符-->
<div id="javascriptI18n">
</div>
