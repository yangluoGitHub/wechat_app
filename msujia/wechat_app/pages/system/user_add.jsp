<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page import="java.util.*" %>
<%@ page import="com.weili.wechat.vo.*" %>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/interface/userService.js'></script>
<script type='text/javascript' src='../dwr/interface/roleService.js'></script>

<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/json2.js" type="text/javascript"></script>
<script src="../scripts/jquery-ui.min.js"></script>
<link href="../styles/jquery-ui.css" rel="stylesheet"type="text/css"/>

<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="../pages/tree/js/tree2.js"></script>
<link href="../pages/tree/css/tree.css" rel="StyleSheet" type="text/css">
</head>

<body>&nbsp; 
 
<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="System.input_tel" value='<%=resource.srcStr("System.input_tel")%>'/>
<input type="hidden" id="System.input_no" value='<%=resource.srcStr("System.input_no")%>'/>
<input type="hidden" id="System.no_length" value='<%=resource.srcStr("System.no_length")%>'/>
<input type="hidden" id="System.user_no_digital" value='<%=resource.srcStr("System.user_no_digital")%>'/>
<input type="hidden" id="System.select_own_org" value='<%=resource.srcStr("System.select_own_org")%>'/>
<input type="hidden" id="System.input_name" value='<%=resource.srcStr("System.input_name")%>'/>
<input type="hidden" id="System.username_length" value='<%=resource.srcStr("System.username_length")%>'/>
<input type="hidden" id="System.select_role" value='<%=resource.srcStr("System.select_role")%>'/>
<input type="hidden" id="System.select_second_role" value='<%=resource.srcStr("System.select_second_role")%>'/>
<input type="hidden" id="System.select_third_role" value='<%=resource.srcStr("System.select_third_role")%>'/>
<input type="hidden" id="System.tel_longer_than15" value='<%=resource.srcStr("System.tel_longer_than15")%>'/>
<input type="hidden" id="System.tel_shorter_than" value='<%=resource.srcStr("System.tel_shorter_than")%>'/>
<input type="hidden" id="System.phone_length" value='<%=resource.srcStr("System.phone_length")%>'/>
<input type="hidden" id="System.input_mobile" value='<%=resource.srcStr("System.input_mobile")%>'/>
<input type="hidden" id="System.error_email_format" value='<%=resource.srcStr("System.error_email_format")%>'/>
<input type="hidden" id="System.email_longer_than" value='<%=resource.srcStr("System.email_longer_than")%>'/>
<input type="hidden" id="System.reset_role" value='<%=resource.srcStr("System.reset_role")%>'/>
<input type="hidden" id="System.input_error_tel" value='<%=resource.srcStr("System.input_error_tel")%>'/>
<input type="hidden" id="System.input_error_phone" value='<%=resource.srcStr("System.input_error_phone")%>'/>
<input type="hidden" id="System.exists_user_no" value='<%=resource.srcStr("System.exists_user_no")%>'/>
<input type="hidden" id="System.select_own_maintainer" value='<%=resource.srcStr("System.select_own_maintainer")%>'/>
</div>

<script language="JavaScript">
var j$ = jQuery.noConflict();

j$().ready(function(){
	 j$("input[name='checkPA']").change(function(){
	 	var val = j$(this).val().split("|");
	 	var value = val[0];
	 	var text = val[1];
	 	if(j$(this).attr("checked") == "checked"){
	 		addSelect(value,text);
	 	}else{
	 		delSelect(value);
	 	}
	 });
});
function addSelect(value,text){
	j$("#wechatId option").each(function(){
		if(j$(this).val() == value){
			return;
		}
	});
	j$("#wechatId").append(
		j$("<option></option>").val(value).html(text)
	);
}
function delSelect(value){
	 j$("#wechatId option").each(function(){
	 	if(j$(this).val() == value){
	 		j$(this).remove();
	 	}
	 });
}
function checkForm(){
  //用户名检查
  if(isNull(j$('#no').val())){
    alert(document.getElementById('System.input_no').value);
    $("no").select();
    form1.no.focus();
    return false;
  }
  if((getLength(form1.no.value)<4||getLength(form1.no.value)>20)&&($F('roleNo_Query')!=100)&&($F('roleNo_Query')!=200)){
    		alert(document.getElementById('System.no_length').value);
    		form1.no.select();
    		form1.no.focus();
   	 		return false;
  		 }
  if(!isNull(form1.no.value)){
	   var parten;
	   parten = /^[a-zA-Z0-9][a-zA-Z0-9_\-\.]{0,19}$/;
	   if(!parten.exec($F('no'))) {
			       alert('用户名必须由字母、数字、下划线、横线、点号组成\n，开头只能是字母或者数字');
			  	   form1.no.select();
		  	  	   return false;
		}  
  }
  //姓名检查
  if(isNull(j$('#name').val())){
    alert(document.getElementById('System.input_name').value);
    $("name").select();
    return false;
  }
  if(getLength(form1.name.value)>20 || getLength(form1.name.value)<1){
    		alert(document.getElementById('System.username_length').value);
    		form1.name.select();
   	 		return false;
  		 }
  if(!isNull(j$('#name').val())){
         var parten;
		 parten = /^[a-zA-Z0-9\u4e00-\u9fa5][a-zA-Z0-9_\-\.\u4e00-\u9fa5]{0,19}$/;
		 if(!parten.exec($F('name'))) {
			       alert('姓名必须由汉字、字母、数字、下划线、横线、点号组成\n，开头只能是汉字、数字或者字母');
			  	   form1.name.select();
		  	  	   return false;
		}  
  }  		 
  //角色检查
  if(isNull(j$('#roleNo').val())){
    alert(document.getElementById('System.select_role').value);
    $("roleNo").focus();
    return false;
  }
  //手机号码检查
  if(!isNull(form1.mobile.value)){
  	if(!mobileCheck(form1.mobile)){ 
		alert(document.getElementById('System.input_error_phone').value);
		form1.mobile.select();
		return false;
	}
  	if(getLength(form1.mobile.value)<11){
    	alert(document.getElementById('System.phone_length').value);
    	form1.mobile.select();
    	return false;
  	}
  }
  //email检查
  if(!isNull(form1.email.value)){
  	var checkmail = document.getElementById("email").value;
	reEmail=/\w+\@\w+\.\w+/gi ;//正则表式如果符合要求就通过
   	var isOk=reEmail.test(checkmail);
   	if(!isOk){
       alert(document.getElementById('System.error_email_format').value);
	   return false;
	}
	if(getLength(form1.email.value)>40){
        alert(document.getElementById('System.email_longer_than').value);
        form1.email.select();
        return false;
    }
  }
  //办公电话检查
  if(!isNull(form1.telephone.value))
  {
	  if(!phoneCheck(form1.telephone))
	  { 
	  	alert(document.getElementById('System.input_error_tel').value);
	  	form1.telephone.select();
	  	return false;
	  }
  	  if(getLength(form1.telephone.value)>15)
  	  {
      	alert(document.getElementById('System.tel_longer_than15').value);
    	form1.telephone.select();
    	return false;
	  }
  	  if(getLength(form1.telephone.value)<7)
  	  {
		alert(document.getElementById('System.tel_shorter_than').value);
    	form1.telephone.select();
   	 	return false;
	  }
  }
  else if(j$('roleNo').val()==100 || j$('roleNo').val()==200)
  {
	alert(document.getElementById('System.input_tel').value);
    $("telephone").select();
    return false;
  }
  
  if($F('roleNo')!=100 && $F('roleNo')!=200){
	  	//判断用户是否存在
	  	DWREngine.setAsync(false);
	 	userService.checkIsExist($("no").value,
			function checkIsExist(data){
				if (data){
					alert(document.getElementById('System.exists_user_no').value);
					form1.no.select();
					return false;
				}else{
					form1.submit();
				}
			});
	 	DWREngine.setAsync(true);
	  
  }else{
	  form1.submit();
  }
}

function myrefresh(){
     if($("tmpfirst").value==1){
	  	 $("tmpfirst").value=0;
	 }
} 
 
var roleNum=0;
function addRole(){
	j$("#div" + (roleNum+1)).show();
	roleNum++;
}
function resetRole(){
	j$("#div" + roleNum).hide();
	j$("#roleNo" + roleNum).val('');
	roleNum--;
}

function select(flag){
	j$(":checkbox").each(function(){
		j$(this).attr("checked", flag);
	});
}
</script>

<br>

<%
	String roleNo_Query=request.getParameter("roleNo_Query");
	request.setAttribute("roleNo_Query",roleNo_Query);
	String title = request.getParameter("title");
%>
<form name="form1" method="post" action="user.do?action=add&opNoCondition=${opNoCondition }&roleNoCondition=${roleNoCondition }">
<input type="hidden" name="tmpfirst" value="0">
<input type="hidden" name="roleNo_Query" id="roleNo_Query" value='<%= roleNo_Query%>'>
<input type="hidden" name="title" value='<%= title%>'>
<table align="center" width="650" border="0" cellspacing="1" cellpadding="4" class="table1">
  <tr class="tr1">
    <td nowrap colspan="2" align="center"><%=resource.srcStr("System.add")%><%=title %></td>
  </tr> 
  <tr class="tr3">
    <td nowrap width="100">用户名：</td>
    <td nowrap><input type="text" name="no" id="no" class="pane" style="ime-mode:disabled" onkeydown="if(event.keyCode==13)event.keyCode=9" maxlength="20">&nbsp;*</td> 
  </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.name") %>：</td>
    <td nowrap><input type="text" name="name" id="name" class="pane"  maxLength="20">&nbsp;*</td> 
  </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.role") %>：</td>
    <td nowrap>
	  <div>
	      <select name="roleNo" id="roleNo" size="1">
	      	<option value=""></option>
		      <c:forEach items="${roleList}" var="role">
		        <option value="<c:out value="${role[0]}"/>"><c:out value="${role[1]}"/></option>
		      </c:forEach>
	      </select>&nbsp;*
      </div>
    </td> 
  </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.fixed_telephone") %>：</td>
    <td nowrap><input type="text" name="telephone" class="pane" maxLength=15 style="ime-mode:disabled" onkeydown="if(event.keyCode==13)event.keyCode=9" onKeyPress="if (event.keyCode!=45 && (event.keyCode<48 || event.keyCode>57)) event.returnValue=false">&nbsp;<c:if test="${roleNo_Query ==100 || roleNo_Query ==200}"></c:if></td> 
  </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.mobile") %>：</td>
    <td nowrap><input type="text" name="mobile" class="pane" maxlength="20" style="ime-mode:disabled" onkeydown="if(event.keyCode==13)event.keyCode=9" onKeyPress="if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false"></td> 
  </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.email") %>：</td>
    <td nowrap><input type="text" id="email" name="email" class="pane" maxLength="40" ></td> 
  </tr>
  <tr class="tr3">
    <td nowrap>公众号权限：</td>
     <td nowrap>
     	<c:if test="${empty paList}">
     		<font color="red">尚无公众号可选</font>
     	</c:if>
     	<c:forEach items="${paList}" var="pa">
     		<div style="position: relative;float: left;margin-bottom: 2px;margin-left: 10px">
     		<p >
     		<img id="logo" src="../images/menu/${pa[2]}">
     		<input style="position: relative;float: left;top: -12px;left: 30px;" type="checkbox" name="checkPA" value="${pa[0]}|${pa[1]}"/></p>
     		</div>
     	</c:forEach>
     </td>
  </tr>
  <c:if test="${!empty paList}">
  <tr class="tr3">
    <td nowrap>默认公众号：</td>
    <td nowrap>
    	<select id="wechatId" name="wechatId" style="width: 180px">
    		<option value="">---请选择---</option>
    	</select>&nbsp;
    </td> 
  </tr>
  </c:if>
</table>

<p align="center">
	<input class="button" type="button" id="submitBtn" value='<%=resource.srcStr("Main.submit")%>' onclick="checkForm()">&nbsp;
	<input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.location='user.do?action=qry&opNo=${opNoCondition }&roleNo_Query=${roleNoCondition }';">
</p>
</form>

</body>
</html>
