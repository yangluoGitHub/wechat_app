<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
</head>

<body>
<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="System.input_maintainer_name" value='<%=resource.srcStr("System.input_maintainer_name")%>'/>
<input type="hidden" id="System.fault_email_format" value='<%=resource.srcStr("System.fault_email_format")%>'/>
</div>

<script language="JavaScript">
var j$ = jQuery.noConflict();
j$().ready(function(){
	j$(":text").addClass("pane");
	
});
</script>
<br>

<table id="infoTable" align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1" >
 <tr class="tr1">
    <td nowrap colspan="2" align="center">公众号详细信息</td>
  </tr>  
  <tr class="tr3">
    <td nowrap width="120">公众号名称：</td>
    <td nowrap><c:out value="${publicAccount.wechatName}"/></td> 
  </tr> 
  <tr class="tr3">
    <td nowrap>微信号：</td>
    <td nowrap><c:out value="${publicAccount.wechatId}"/></td> 
  </tr>
  <tr class="tr3">
    <td nowrap>微信号原始ID</td>
    <td nowrap><c:out value="${publicAccount.id}"/></td> 
  </tr>
  <tr class="tr3">
    <td nowrap>公众号类型：</td>
    <td nowrap>
		<c:if test="${publicAccount.wechatType == 1}"><c:out value="服务号"/></c:if>
		<c:if test="${publicAccount.wechatType == 2}"><c:out value="订阅号"/></c:if>
	</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>URL:：</td>
    <td nowrap><c:out value="${publicAccount.url}"/></td> 
  </tr>
  <tr class="tr3">
    <td nowrap>TOKEN：</td>
    <td nowrap><c:out value="${publicAccount.token}"/></td> 
  </tr>
  <tr class="tr3">
    <td nowrap>APPID:：</td>
    <td nowrap><c:out value="${publicAccount.appid}"/></td> 
  </tr>
  <tr class="tr3">
    <td nowrap>AppSecret:：</td>
    <td nowrap><c:out value="${publicAccount.appsecret}"/></td> 
  </tr>
    <tr class="tr3">
    <td nowrap>创建者:：</td>
    <td nowrap><c:out value="${publicAccount.creator}"/></td> 
  </tr>
  
</table>
    <p align="center">
      <input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.history.back();">
    </p>


</body>
</html>




