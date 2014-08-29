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
<!--javascript ���ʻ���ʶ��-->
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
function isMenusFileValid(fileName)
{
	if(fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="jar")
	{	
		return true;
	}
	else
	{
		return false;
	}
}
function checkForm(){
	var phoneNumCheck = /\d+\-\d+/g;
	var phoneNumCheckMsg = "�绰/�������ֻ���������ֺ�-���������ֿ�ʼ�ͽ���";
  if(isNull($F('functionName'))){
    alert("������˵������ƣ�");
    return false;
  }
  if(getLength($F('functionName')) > 80) {
  	alert("���Ʋ��ó���80�ֽ�!");
  	$('name').select();
  	return false;
  }
   if(isNull($('path').value)){
    alert("���ϴ�Դ�ļ���");  
   return false;
  }
   if(!isMenusFileValid($('path').value))
  {
	alert("�����ļ���ʽ����ȷ������Ϊ.jar�ļ�");
	return false;
  }
  return true;
}
</script>
<br>

<form name="form1" method="post" action="menuFunction.do?action=add">
<table id="infoTable" align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1" >
 <tr class="tr1">
    <td nowrap colspan="2" align="center">��ӹ��ںŲ˵���Ϣ</td>
  </tr>  
  <tr class="tr3">
    <td nowrap width="120">�˵����ܵ����ƣ�</td>
    <td nowrap><input type="text" name="functionName"maxlength="80">&nbsp;*</td> 
  </tr> 
  <tr class="tr3">
    <td nowrap>���ͣ�</td>
    <td nowrap>
    <select name="functionType" size="1">
		<option value="1" selected>������Ϣ</option>
		<option value="2">��ת��ҳ</option>
		<option value="3">����������</option>		
	</select>		
    </td> 
  </tr>
  <tr class="tr3">
     <td nowrap >Դ�ļ�:&nbsp;<Font color="red">(*)</font></td>
	 <td nowrap  title='�ϴ�Դ�ļ�' ><input type="file" name="path"  id="path" style="width:420px;" value="" contentEditable='false'></td>
  </tr>
  <tr class="tr3">
    <td nowrap width="120">������</td>
    <td nowrap>
    <textarea name="describe" id="describe" style="width:420px;" cols=40 rows=4></textarea>&nbsp;*
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




