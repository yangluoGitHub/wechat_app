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
  if(isNull($F('devVendor'))){
    alert(document.getElementById('System.select_own_brand').value);
    form1.devVendor.focus();
	return false;
  }
  if(isNull($F('devCatalog'))){
    alert(document.getElementById('System.select_own_type').value);
    form1.devCatalog.focus();
    return false;
  }
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

<form name="form1" method="post" action="type.do?action=add">
<table width="650" align="center" border="0" cellspacing="1" cellpadding="6" class="table1">
 <tr class="tr1">
    <td nowrap colspan="2" align="center"><%=resource.srcStr("System.add_dev_typenum")%></td>
  </tr>  
  <tr class="tr3">
    <td nowrap width="100"><%=resource.srcStr("Main.devType") %>：</td>
    <td nowrap><input style="width:153;" type="text" name="name" class="pane" maxLength="20">&nbsp;*</td> 
  </tr>
  <tr class="tr3">	
    <td nowrap><%=resource.srcStr("System.own_brand") %>：</td>
     <td nowrap>
	  <select name="devVendor" style="width:153;">
	    	<option value="">------<%=resource.srcStr("Main.select")%>------</option>         
         	<c:forEach items="${vendorList}" var="vendor"  step="1" >
	      	<option value="<c:out value="${vendor.no}"/>"><c:out value="${vendor.name}"/></option>
	       	</c:forEach>
	   </select>&nbsp;*
   </td>
  </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.own_type") %>：</td>
     <td nowrap>
	  <select name="devCatalog" style="width:153;">
	    	<option value="">------<%=resource.srcStr("Main.select")%>------</option>         
         	<c:forEach items="${catalogList}" var="catalog"  step="1" >
	      	<option value="<c:out value="${catalog.no}"/>"><c:out value="${catalog.name}"/></option>
	       	</c:forEach>
	   </select>&nbsp;*
   </td>
  </tr>
  <!-- tr class="tr3">
    <td nowrap>设备规格：</td>
    <td nowrap><input type="text" name="spec" class="pane" onblur="checktext($(spec),20);">&nbsp;</td> 
  	</tr>
  <tr class="tr3">
    <td nowrap>设备重量：</td>
    <td nowrap><input type="text" name="weight" class="pane" onKeyPress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;">&nbsp;</td>
  </tr>
  <tr class="tr3">
    <td nowrap>平均功率：</td>
    <td nowrap><input type="text" name="watt" class="pane" onKeyPress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;">&nbsp;</td> 
  </tr>  -->
</table>
	
	<p align="center">
      <input class="button" type="submit" value='<%=resource.srcStr("Main.submit")%>' onclick="return checkForm()">&nbsp;
      <input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.history.back();">
    </p>

</form>

</body>
</html>




