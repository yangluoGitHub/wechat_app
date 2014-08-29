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
<script language="JavaScript">
var j$ = jQuery.noConflict();
j$().ready(function(){
	
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
		alert("�����빦�ܵ�����");  
		return false;
	}
	//alert(isUniqueFuncName(funcName, ''));
	if(!isUniqueFuncName(funcName, '')){
		alert("���ܵ������Ѵ��ڣ���ȷ�Ϻ��������룡");
		j$("#wechatletName").select();
		return false;
	}
	if(isNull(j$("#classname").val())){
		alert("��������ȫ·��");  
		return false;
	}
	
	return true;
}

</script>
<br>
<form name="form1" method="post" action="menuFunction.do?action=add" enctype="multipart/form-data">
<table id="infoTable" align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1" >
 <tr class="tr1">
    <td nowrap colspan="2" align="center">����ҵ����</td>
  </tr>  
  <tr class="tr3">
    <td nowrap width="120">���ܵ����ƣ�</td>
    <td nowrap><input type="text" name="wechatletName" id="wechatletName" maxlength="36">&nbsp;*</td> 
  </tr>
  <tr class="tr3">
    <td nowrap width="120">������</td>
    <td nowrap><input type="text" name="description" id="description" maxlength="256"></td> 
  </tr>
  <tr class="tr3">
    <td nowrap width="120">��ȫ·����</td>
    <td nowrap><input type="text" name="classname" id="classname" maxlength="128">&nbsp;*</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>���ں�Ȩ�ޣ�</td>
     <td nowrap>
     	<c:if test="${empty paList}">
     		<font color="red">���޹��ںſ�ѡ</font>
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
