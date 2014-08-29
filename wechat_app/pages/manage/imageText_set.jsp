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
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/interface/imageTextService.js'></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
</head>

<body >
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

function checkForm(){
  if(($F('function')=="")){
    alert("请选择业务功能！");
    return false;
  }
  return true;
}


</script>
<br>

<form name="form1" method="post" action="imageText.do?action=up">
<table id="infoTable" align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1" >
<input type="hidden" name="id" value="${id}">
<input type="hidden" name="multiresourceId" value="${wechatResource.multiresourceId}">
<input type="hidden" name="mediaPath" value="${originalMedia.mediaPath}">
<input type="hidden" name="describe" value="${originalMedia.describe}">
<input type="hidden" name="outlink" value="${wechatResource.outLink}">
<input type="hidden" name="imageId" value="${wechatResource.mediaId}">
 <tr class="tr1">
    <td nowrap colspan="2" align="center">配置业务功能信息信息</td>
  </tr>  
  <tr class="tr3" >
    <td nowrap width="120">标题：</td>
    <td nowrap><input type="text" name="topTitle" maxlength="80" readonly="readonly" style="background: #EFEFEF" value="${wechatResource.resourceTittle}">&nbsp;*</td> 
  </tr> 
    <tr class="tr3">
    <td nowrap>创建者：</td>
    <td nowrap><input type="text" name="creator"  readonly="readonly" style="background: #EFEFEF" value="${sessionScope.userSession.account}" ></td> 
  </tr>
  
  <tr class="tr3" style="height:200px;">
    <td nowrap>预览：</td>
    <td nowrap>
		    <div >
		    	<div style="width:360px;height: 200px; position:absolute;top:120px;margin-left: 30px;"   >
		   			 <img alt="${wechatResource.outLink}"   src="${pageContext.request.contextPath}/${originalMedia.mediaPath}" >	
		   		 </div>
		   		<div style="position:relative ;top:75px;left: 30px;">
					<input type="text" name="title" value="${wechatResource.resourceContent}" maxlength="80" readonly="readonly" style="background:black;width: 360px;height:50px;filter: alpha(opacity=50); color: white;font-size:20px;" ></td> 
		    	</div>
		  	</div>
	</td>
  </tr>

  	<c:forEach items="${resources}" var="item" varStatus="a">
  	 <tr class="tr3" height="80" >
    	<td nowrap width="100">预览：</td>
   		<td nowrap>
		<div  style="float:right;margin-left: 50px;margin-right: 30px;">
				<img style="width:80px;height:80px; " alt="${item.resource.outLink}" src="${pageContext.request.contextPath}/${item.media.mediaPath}" />
		</div>
		<div style="float: right;">
			<input style="height:80px;  width:300px;ont-size:20px;" type="text"  value="${item.resource.resourceContent}" maxlength="80" readonly="readonly"  >
		</div>

  		</td>
  	 </tr>	
	</c:forEach>	
 	
  <tr class="tr3">
    <td nowrap width="120">业务功能：</td>
     <td nowrap>
     	<c:if test="${empty wechatFunction}">
     		<font color="red">尚无业务功能可选</font>
     	</c:if>
     	<select name="function" size="1">
     	<!-- <option>--请选择业务功能--</option> -->
     	<option value="${resFunction}" ${resFunction !='' ?'selected':''}">${resFunction}</option>
     	
     	<c:forEach items="${wechatFunction}" var="pa">	
		     <option name = "${pa.wechatletName}">${pa.wechatletName}</option>
     	</c:forEach>
     	</select>&nbsp;*
     </td>
  </tr>
</table>



    <p align="center">
      <input class="button" type="submit" value='<%=resource.srcStr("Main.submit")%>' onclick="return checkForm()">
      <input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.history.back();">
    </p>

</form>

</body>
</html>




