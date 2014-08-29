<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
</head>

<body>
<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="System.input_dev_typenum" value='<%=resource.srcStr("System.input_dev_typenum")%>'/>
<input type="hidden" id="System.devtype_name_than" value='<%=resource.srcStr("System.devtype_name_than")%>'/>
<input type="hidden" id="System.select_own_brand" value='<%=resource.srcStr("System.select_own_brand")%>'/>
<input type="hidden" id="System.select_own_type" value='<%=resource.srcStr("System.select_own_type")%>'/>
</div>

<script language="JavaScript">
function checkForm(){
	
  if(isNull($F('name'))){
    alert(document.getElementById('System.input_dev_typenum').value);
    return false;
  }

  if(getLength(form1.name.value)>20){
    alert(document.getElementById('System.devtype_name_than').value);
    form1.name.select();
    return false;
  }
  if(!isNull($F('name'))){
         var parten;
		 parten = /^[a-zA-Z0-9\u4e00-\u9fa5][a-zA-Z0-9_\-\.\u4e00-\u9fa5]{0,19}$/;
		 if(!parten.exec($F('name'))) {
			       alert('设备型号必须由汉字、字母、数字、下划线、横线、点号组成\n，开头只能是汉字、数字或者字母');
			  	   form1.name.select();
		  	  	   return false;
		}  
  }
  form1.submit();
  return true;
}

function  checkDevType(obj)
{
    strRef = "#/\%&*@^$*;:~";
	strTmp = obj.value;
	str = "";
	for(i=0;i<strTmp.length;i++){
		if(strTmp.charAt(i) != ' '){
			str += strTmp.charAt(i);
		}
	}
	for (i=0;i<str.length;i++) {
		tempChar= str.substring(i,i+1);
		if (strRef.indexOf(tempChar,0)!=-1) {
			return false; 
		}  
	}
	return true;
}
</script>

<br>
<form name="form1" method="post" action="type.do?action=mod">
<input type="hidden" name="no" value="<c:out value="${devType.no}"/>">
<input type="hidden" name="name_old" value="<c:out value="${devType.name}"/>">
<table align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1">
 <tr class="tr1">
    <td nowrap colspan="2" align="center"><%=resource.srcStr("System.mod_dev_typeinfo")%></td>
  <tr class="tr3">
      <td nowrap width="100"><%=resource.srcStr("Main.devType") %>：</td>
      <td><input type="text" name="name" id="name" maxlength="20" onKeydown="if(event.keyCode== '13') event.returnValue = false;" value="<c:out value="${devType.name}"/>">&nbsp;*</td>
  </tr> 
  <tr class="tr3">
      <td><%=resource.srcStr("System.own_brand") %>：</td>
      <td><c:out value="${devType.devVendorTable.name}" /></td>
  </tr>   
  <tr class="tr3">
      <td><%=resource.srcStr("System.own_type") %>：</td>
      <td><c:out value="${devType.devCatalogTable.name}" /></td>
  </tr>   
</table>
    <p align="center">
      <input class="button" type="button" value='<%=resource.srcStr("Main.submit")%>' onclick="javascript:checkForm();">&nbsp;
      <input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.history.back();">
    </p>
</form>
</body>
</html>


