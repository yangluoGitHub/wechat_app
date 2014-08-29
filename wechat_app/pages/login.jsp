<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@page import="com.weili.wechat.common.Resource"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.weili.wechat.common.GetResource"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<% 
   Resource resource =  new Resource("zh_CN");
   if( session.getAttribute("locale") != null )
   resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());
%>
<html>
<head>
<title><%=resource.srcStr("Login.op_server_login") %></title>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
<style type="text/css">
body {
	margin:0 auto;
	background-position: center;
	background-repeat:no-repeat;
	background-image: url(../images/login/background.png);
	background-attachment: fixed ;
}
#form1{
	position: absolute
}

#user{
	position:absolute;
	top:45%;
	left:40%;
	width: 390px;
	height: 200px;
}
#accountTips{
	position:absolute;
	top:9%;
	left:87%;
	width: 90px;
	height: 20px;
}
#passwdTips{
	position:absolute;
	top:30%;
	left:87%;
	width: 90px;
	height: 20px;
}
.login {
 	margin-left:98px;
	padding:3px 0 3px 0;
 	width:100px;
    height:30px;
    cursor:hand;
    background:url(../images/login/login.png);
    background-repeat: no-repeat;
	display: inline-block;		/*��ֹCSS��a��ǩ����ͼƬ������ȫ��ʾ*/
}
.login:link {color:#fff;}
.login:visited {	color: #fff;}
.login:active {	color: #fff;}
.login:hover {
	left: 0px;
	position: relative; top: 0px;
	background:url(../images/login/login(click).png);
	background-repeat: no-repeat;
	display: inline-block;		/*��ֹCSS��a��ǩ����ͼƬ������ȫ��ʾ*/
}

.cancle {
 	margin-left:-1px;
	padding:3px 0 3px 0;
 	width:100px;
    height:30px;
    cursor:hand;
    background:url(../images/login/reset.png);
    background-repeat: no-repeat;
	display: inline-block;		/*��ֹCSS��a��ǩ����ͼƬ������ȫ��ʾ*/
}

.cancle:link {
	color:#fff;
	}
.cancle:visited {
	color: #fff;
	}
.cancle:active {
	color: #fff;
	}
.cancle:hover {
	left: 0px;
	position: relative; top: 0px;
	background:url(../images/login/reset(click).png);
	background-repeat: no-repeat;
	display: inline-block;		/*��ֹCSS��a��ǩ����ͼƬ������ȫ��ʾ*/
}

#account{
	margin-top:10px;
	margin-left:25px;
	width:210px;
	height:22px;
	line-height:18px;
	border:1px inset;
	background-color:#DAE3F0;

}
#password{
	margin-top:10px;
	margin-left:33px;
	width:210px;
	height:22px;
	line-height:18px;
	border:1px inset;
	background-color:#DAE3F0;
}
span a{
	font-size: 16px;
	font-family:����;
 	text-decoration:none;
 	margin-left:0px;
 	text-align:center;
	padding:3px 0 3px 0;
	width:70px;
    height:22px;
    cursor:hand;
    color:#3BA7D0;
    background:;
}
span a:hover{
	left: 0px; color:#1362AB; position: relative; top: 0px;text-decoration: underline;
	background:;
} 
</style>
<script language="javascript">
  var allowFlag = false;
function leave(){
    if(! allowFlag){
      history.go(0);
    }
}
function checkForm(){
	if(form1.account.value == ""){
	  document.getElementById("accountTip").innerHTML=document.getElementById("Login.input_server_id").value;
	  document.getElementById("passwdTip").innerHTML="";
	  //alert(document.getElementById("Login.input_server_id").value);
	  form1.account.focus();
	  return false;
	}else{
		document.getElementById("accountTip").innerHTML="";
	}
	if(form1.password.value == ""){
	  document.getElementById("passwdTip").innerHTML=document.getElementById("Main.input_psw").value;
	  //alert(document.getElementById("Main.input_psw").value);
	  form1.password.focus();
	  return false;
	}else{
	document.getElementById("passwdTip").innerHTML="";
	}
	document.getElementById("loadon").disabled = true;
	document.getElementById("exceptionTip").innerHTML = "���ڵ�½ϵͳ�����Ժ�...";
	allowFlag = true;
	form1.submit();
	return true;
}

function Clear(){
	document.getElementById("account").value = '';
	document.getElementById("password").value = '';
	document.form1.account.focus();
}

//�����ղ�
function addFavorite(){
	if(document.all){
		window.external.addFavorite(window.location.href,'΢�Ź��ںŹ���ƽ̨');
	}else if(window.sidebar){
		window.sidebar.addPanel('΢�Ź��ںŹ���ƽ̨',window.location.href,"");
	}
}
//ȥ���������ӻ�ͼƬ�����������߿�
function bluring(){
	if(event.srcElement.tagName=="A"||event.srcElement.tagName=="IMG"){
		document.body.focus();
	}
}
document.onfocusin=bluring;
</script>
</head>

<body onload="javascript:form1.account.focus();">
<form name="form1" method="post" action="login.do?action=login" >
<input type="hidden" name="language" value="cn" >
<div id="user" style="top: 240px;">
	<table align="center">
		<tr>
			<td><font style="font-family: "΢���ź�, ����";" size="+1">�û���:</font><input type="text" id="account"  name="account" style="font-size:16px" onfocus="this.style.backgroundColor='#FFFFCC'" onblur="this.style.backgroundColor='#DAE3F0'" value="<c:if test="${!empty account}"><c:out value="${account}"/></c:if>">
			</td>
				
		</tr>
		<tr>
			<td><font style="font-family: "΢���ź�, ����";" size="+1">��&nbsp;��:</font><input type="password" id="password" name="password" onfocus="this.style.backgroundColor='#FFFFCC'" onblur="this.style.backgroundColor='#DAE3F0'" onKeyDown="if(event.keyCode==13) checkForm();">       
			</td>
		</tr>
	</table>
  <br />
	<table align="center">
		<tr>
        	<td>
				<a class="login" id="loadon" href="#"  onclick="javascript:checkForm();return false;"></a>&nbsp;&nbsp;&nbsp;
			</td>
			<td>
            	<a class="cancle" href="#" onclick="javascript:Clear();return false;"></a>&nbsp;&nbsp;&nbsp;
            </td>
			<!--
			<td><span><a href="#" onclick="addFavorite();">�����ղ�</a></span></td>
         	 -->
		</tr>
	</table>
	<div id="accountTips">
	<span id="accountTip" style="color: red;" ></span>
	</div>
	<div id="passwdTips">
	<span id="passwdTip" style="color: red;"></span>
	</div>
<div style="text-align: center;">
	<b id="exceptionTip" style="color:red;">
		<c:if test="${!empty message}">
			<c:out value="${message}"/>
		</c:if>
	</b>
</div>
</div>
</form>
</body>
</html>

<!--javascript ���ʻ���ʶ��-->
<div name="javascriptI18n">
<input type="hidden" id="Main.input_psw" value='<%=resource.srcStr("Main.input_psw")%>'>
<input type="hidden" id="Login.input_server_id" value='<%=resource.srcStr("Login.input_server_id")%>'>
</div>

