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
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/interface/menuFunctionService.js'></script>

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
	var upaList = j$("#upaidList").val();
	j$("input[name='checkPA']").each(function(){
		var id = j$(this).val().split("|")[0];
		if(upaList.indexOf(id)>0){
			j$(this).attr("checked","checked");	
		}
	});
}); 


function isUniqueFuncName(name, id){
	
	var res;
	DWREngine.setAsync(false);
	menuFunctionService.isUniqueFuncName(name, id, function(data){
		res = data;
	});
	DWREngine.setAsync(true);
	return res;
}
function checkForm(){
	var funcName = j$("#wechatletName").val();
	if(isNull(j$("#wechatletName").val())){
		alert("请输入功能点名称");  
		return false;
	}
	if(!isUniqueFuncName(funcName, j$.trim($('id').value))){
		alert("功能点名称已存在，请确认后重新输入！");
		j$("#wechatletName").select();
		return false;
	}
	if(isNull(j$("#classname").val())){
		alert("请输入类全路径");  
		return false;
	}
	return true;
}

</script>
<br>

<form name="form1" method="post" action="menuFunction.do?action=mod" enctype="multipart/form-data">
<input type="hidden" name="id" value="${wechatFunction.id}">
<input id="upaidList" type="hidden" value="${pubFunIdList}"/>
<table id="infoTable" align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1" >
 <tr class="tr1">
    <td nowrap colspan="2" align="center">修改业务功能</td>
  </tr>  
  <tr class="tr3">
    <td nowrap width="120">业务功能名称：</td>
    <td nowrap><input type="text" name="wechatletName" id="wechatletName" maxlength="36" value="${wechatFunction.wechatletName}">&nbsp;*</td> 
  </tr>
  <tr class="tr3">
    <td nowrap width="120">描述：</td>
    <td nowrap><input type="text" name="description" id="description" maxlength="256" value="${wechatFunction.description}"></td> 
  </tr>
  
  <tr class="tr3">
    <td nowrap width="120">类全路径：</td>
    <td nowrap><input type="text" name="classname" id="classname" maxlength="128" value="${wechatFunction.classname}">&nbsp;*</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>公众号权限：</td>
     <td nowrap>
     	<c:if test="${empty paList}">
     		<font color="red">尚无公众号可选</font>
     	</c:if>
     	<c:forEach items="${paList}" var="pa">
     		<input type="checkbox" name="checkPA" value="${pa[0]}|${pa[1]}"/><c:out value="${pa[1]}"></c:out><br>
     	</c:forEach>
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




