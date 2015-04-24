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
  if(isNull($F('storeName'))){
    alert("�������ŵ����ƣ�");
    return false;
  }
  if(getLength($F('storeName')) > 60) {
  	alert("���Ʋ��ó���60�ֽ�!");
  	$('storeName').select();
  	return false;
  }
  if(isNull($F('storeNo'))){
    alert("�������ŵ��ţ�");
    return false;
  }
  return true;
}
</script>
<br>

<form name="form1" method="post" action="storeInfo.do?action=add">
<table id="infoTable" align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1" >
 <tr class="tr1">
    <td nowrap colspan="2" align="center">����ŵ���Ϣ</td>
  </tr>  
  <tr class="tr3">
    <td nowrap width="120">�ŵ����ƣ�</td>
    <td nowrap><input type="text" name="storeName" maxlength="64">&nbsp;*������60���ַ�</td> 
  </tr> 
  <tr class="tr3">
    <td nowrap>�ŵ��ţ�</td>
    <td nowrap><input type="text" name="storeNo" maxlength="64">&nbsp;*��ĸ�����֡��»�����ɣ�������60�ַ�</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>�ŵ�绰��</td>
    <td nowrap><input type="text" name="phone"  maxlength="20">&nbsp;*</td> 
  </tr>
   <tr class="tr3">
    <td nowrap>�ŵ����ͣ�</td>
    <td nowrap>
					<select name="storeClassificationId" class="inputline" style="width: 153px"
						<option value="">
							----------
						</option>
						<c:forEach var="scf" items="${scfList}">
							<option value='<c:out value="${scf.id}"/>'>
								<c:out value="${scf.clName}" />
							</option>
						</c:forEach>
					</select>
				</td>
  </tr>
  <tr class="tr3">
    <td nowrap>Ӫҵʱ�䣺</td>
    <td nowrap><input type="text" name="businessHours"  maxlength="128">&nbsp;*����10:00������6:00</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>���ͼۣ�</td>
    <td nowrap><input type="text" name="flagFallPrice"  maxlength="5">&nbsp;*Ԫ</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>���ͷѣ�</td>
    <td nowrap><input type="text" name="deliveryCharges"  maxlength="20">&nbsp;*Ԫ</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>Ԥ���ʹ�ʱ�䣺</td>
    <td nowrap><input type="text" name="deliveryTime"  maxlength="5">&nbsp;*����</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>����뾶��</td>
    <td nowrap><input type="text" name="serviceRadius"  maxlength="36">&nbsp;*KM</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>��������</td>
    <td nowrap><input type="text" name="deliveryArea"  maxlength="128">&nbsp;*</td> 
  </tr>
    <tr class="tr3">
    <td nowrap>��¼���룺</td>
    <td nowrap><input type="text" name="passwd"  maxlength="50">&nbsp;*</td> 
  </tr>
    <tr class="tr3">
    <td nowrap>�ŵ��ַ��</td>
    <td nowrap><input type="text" name="address"  maxlength="200">&nbsp;*</td> 
  </tr>
  </tr>
    <tr class="tr3">
    <td nowrap>�Ƿ����ã�</td>
    <td nowrap>
    <select name="onLine" size="1">
		<option value="0" selected>������</option>
		<option value="1">����</option>
	</select>
	&nbsp;*</td> 
  </tr>
  
</table>

    <p align="center">
      <input class="button" type="submit" value='<%=resource.srcStr("Main.submit")%>' onclick="return checkForm()">
      <input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.history.back();">
    </p>

</form>

</body>
</html>




