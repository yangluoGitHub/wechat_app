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
	j$(":text.phone").attr("maxlength", 30);
	j$(":text.mobile").attr("maxlength", 11);
	j$(":text.fax").attr("maxlength", 30);
	
	j$(":text.phone").add(":text.mobile").add(":text.fax")
		.keyup(function(){
			j$(this).val(j$(this).val().replace(/[^\d-]/g,''));
		})
		.keypress(function(){
			if ((event.keyCode < 48 || event.keyCode > 57) && event.keyCode != 45){
				event.returnValue = false;
			}		
		})
		.bind("beforepaste",function(){
			clipboardData.setData("text",clipboardData.getData("text").replace(/[^\d-]/g,""));
		});

});

function checkForm(){
	var phoneNumCheck = /\d+\-\d+/g;
	var phoneNumCheckMsg = "电话/传真号码只能输入数字和-，并以数字开始和结束";
	
  /* if(isNull($F('name'))){
    alert(document.getElementById('System.input_maintainer_name').value);
    return false;
  }
  if(getLength($F('name')) > 80) {
  	alert("维护商名称不得超过80字节!");
  	$('name').select();
  	return false;
  } */
  if(!isNull($F('linkman')) && getLength($F('linkman')) > 30) {
  	alert("联系人长度不得超过30字节!");
  	$('linkman').select();
  	return false;
  }
  if(!isNull($F('address')) && getLength($F('address')) > 80) {
  	alert("地址长度不得超过80字节!");
  	$('address').select();
  	return false;
  }
  if(!isNull($F('phone1')) && getLength($F('phone1')) > 30) {
  	alert("固话1长度不得超过30字节!");
  	$('phone1').select();
  	return false;
  } 
  if(!isNull($F('phone2')) && getLength($F('phone2')) > 30) {
  	alert("固话2长度不得超过30字节!");
  	$('phone2').select();
  	return false;
  }
  if(!isNull($F('mobile1')) && getLength($F('mobile1')) > 11) {
  	alert("手机1长度不得超过11字节!");
  	$('mobile1').select();
  	return false;
  }
  if(!isNull($F('mobile2')) && getLength($F('mobile2')) > 11) {
  	alert("手机2长度不得超过11字节!");
  	$('mobile2').select();
  	return false;
  } 
  if(!isNull($F('fax')) && getLength($F('fax')) > 30) {
  	alert("传真长度不得超过30字节!");
  	$('fax').select();
  	return false;
  }
  if(!isNull($F('email')) && getLength($F('email')) > 40) {
  	alert("电子邮箱长度不得超过40字节!");
  	$('email').select();
  	return false;
  }
  
  if(document.getElementById("email").value!=""){
    var checkmail = document.getElementById("email").value;
	reEmail=/\w+\@\w+\.\w+/gi ;//正则表式如果符合要求就通过
   	var isOk=reEmail.test(checkmail);
   	if(!isOk){
       alert(document.getElementById('System.fault_email_format').value);
       $('email').select();
	   return false;
	}
 }
  return true;
}
</script>
<br>

<form name="form1" method="post" action="devSrvCom.do?action=mod">
<input type="hidden" name="no" value="<c:out value="${devservicecompany.no}"/>">
<table align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1">
  <tr class="tr1">
    <td nowrap colspan="2" align="center"><%=resource.srcStr("System.mod_dev_maintainer")%></td>
  </tr>  
  <tr class="tr3">
    <td nowrap width="120"><%=resource.srcStr("System.maintainer_name") %>：</td>
    <td nowrap><c:out value="${devservicecompany.name}"/></td>
  </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.contact") %>：</td>
    <td nowrap><input type="text" name="linkman" value="<c:out value="${devservicecompany.linkman}"/>" maxlength="30"></td> 
  </tr>
<tr class="tr3">
    <td nowrap><%=resource.srcStr("System.address") %>：</td>
    <td nowrap><input type="text" name="address" value="<c:out value="${devservicecompany.address}"/>" style="width:300px;" maxlength="80"></td> 
  </tr>
<tr class="tr3">
    <td nowrap><%=resource.srcStr("System.fixed")%>1：</td>
    <td nowrap><input type="text" name="phone1" class="phone" value="<c:out value="${devservicecompany.phone1}"/>" ></td> 
  </tr>
<tr class="tr3">
    <td nowrap><%=resource.srcStr("System.fixed")%>2：</td>
    <td nowrap><input type="text" name="phone2" class="phone" value="<c:out value="${devservicecompany.phone2}"/>" ></td> 
  </tr>
<tr class="tr3">
    <td nowrap><%=resource.srcStr("System.mobile_phone") %>1：</td>
    <td nowrap><input type="text" name="mobile1" class="mobile" value="<c:out value="${devservicecompany.mobile1}"/>" ></td> 
</tr>
<tr class="tr3">
    <td nowrap><%=resource.srcStr("System.mobile_phone") %>2：</td>
    <td nowrap><input type="text" name="mobile2" class="mobile" value="<c:out value="${devservicecompany.mobile2}"/>" ></td> 
</tr>
<tr class="tr3">
    <td nowrap><%=resource.srcStr("System.fax") %>：</td>
    <td nowrap><input type="text" name="fax" class="fax" value="<c:out value="${devservicecompany.fax}"/>" ></td> 
  </tr>
<tr class="tr3">
    <td nowrap><%=resource.srcStr("System.email")%> ：</td>
    <td nowrap><input type="text" name="email" value="<c:out value="${devservicecompany.email}"/>" maxlength="40"></td>
    </tr>
</table>

    <p align="center">
      <input class="button" type="submit" value='<%=resource.srcStr("Main.submit")%>' onclick="return checkForm()">
      <input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.history.back();">
    </p>

</form>

</body>
</html>




