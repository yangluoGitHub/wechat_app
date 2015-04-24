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

function checkForm(){
  if(isNull($F('wechatName'))){
    alert("������΢�Ź��ں����ƣ�");
    return false;
  }
  if(getLength($F('wechatName')) > 80) {
  	alert("���Ʋ��ó���80�ֽ�!");
  	$('name').select();
  	return false;
  }
  if(isNull($F('wechatId'))){
    alert("������΢�ź����ƣ�");
    return false;
  }
   if(isNull($F('id'))){
    alert("������΢�ź�ԭʼID��");
    return false;
  }
  return true;
}
</script>
<br>

<form name="form1" method="post" action="account.do?action=add">
<table id="infoTable" align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1" >
 <tr class="tr1">
    <td nowrap colspan="2" align="center">��ӹ��ں���Ϣ</td>
  </tr>  
  <tr class="tr3">
    <td nowrap width="120">���ں����ƣ�</td>
    <td nowrap><input type="text" name="wechatName" maxlength="80">&nbsp;*</td> 
  </tr> 
  <tr class="tr3">
    <td nowrap>΢�źţ�</td>
    <td nowrap><input type="text" name="wechatId">&nbsp;*</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>΢�ź�ԭʼID��</td>
    <td nowrap><input type="text" name="id" style="width:300px;" maxlength="80">&nbsp;*</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>���ں����ͣ�</td>
    <td nowrap>
	<select name="wechatType" size="1">
		<option value="1" selected>�����</option>
		<option value="2">���ĺ�</option>
	</select>	
	</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>URL��</td>
    <td nowrap><input type="text" name="url"></td> 
  </tr>
  <tr class="tr3">
    <td nowrap>TOKEN��</td>
    <td nowrap><input type="text" name="token"></td> 
  </tr>
  <tr class="tr3">
    <td nowrap>APPID��</td>
    <td nowrap><input type="text" name="appid"></td> 
  </tr>
  <tr class="tr3">
    <td nowrap>AppSecret��</td>
    <td nowrap><input type="text" name="appsecret"></td> 
  </tr>
    <tr class="tr3">
    <td nowrap>�����ߣ�</td>
    <td nowrap><input type="text" name="creator" value="${sessionScope.userSession.account}" readonly="readonly"></td> 
  </tr>
  
</table>

    <p align="center">
      <input class="button" type="submit" value='<%=resource.srcStr("Main.submit")%>' onclick="return checkForm()">
      <input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.history.back();">
    </p>

</form>

</body>
</html>




