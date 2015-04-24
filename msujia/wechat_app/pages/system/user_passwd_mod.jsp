<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp"%>
<%@ page import="com.weili.wechat.common.UserSession"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<link rel="StyleSheet" href="tree/css/tree.css" type="text/css">
<link href="../styles/common.css" rel="stylesheet" type="text/css">
</head>

<body>
<!--javascript ���ʻ���ʶ��-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="Main.input_old_psw" value='<%=resource.srcStr("Main.input_old_psw")%>'/>
<input type="hidden" id="Main.input_new_psw" value='<%=resource.srcStr("Main.input_new_psw")%>'/>
<input type="hidden" id="Login.psw_less_8" value='<%=resource.srcStr("Login.psw_less_8")%>'/>
<input type="hidden" id="Main.psw_longer_16" value='<%=resource.srcStr("Main.psw_longer_16")%>'/>
<input type="hidden" id="Main.please_sure_new_psw" value='<%=resource.srcStr("Main.please_sure_new_psw")%>'/>
<input type="hidden" id="Main.different_psw" value='<%=resource.srcStr("Main.different_psw")%>'/>
</div>

<script language="JavaScript">
function checkForm(){
  if(isNull(form1.oldpasswd.value)){
    alert(document.getElementById('Main.input_old_psw').value);
    form1.oldpasswd.select();
    form1.oldpasswd.focus();
    return false;
  }
  if(isNull(form1.passwd.value)){
  	form1.passwd.select();
    form1.passwd.focus();
    alert(document.getElementById('Main.input_new_psw').value);
    return false;
  }
    if(($F('passwd').length<8)){
    alert(document.getElementById('Login.psw_less_8').value);
    form1.passwd.select();
    form1.passwd.focus();
    return false;
  }
  if(($F('passwd').length>16)){
    alert(document.getElementById('Main.psw_longer_16').value);
    form1.passwd.select();
    form1.passwd.focus();
    return false;
  }
   if(!checkPwd($('passwd'))){
    alert("�������д��ڷǷ��ַ�_�����������룡");
    form1.newpasswd.select();
    form1.newpasswd.focus();
    form1.passwd.value='';
    return false;
  }
  if(isNull(form1.newpasswd.value)){
  	form1.newpasswd.select();
    form1.newpasswd.focus();
    alert(document.getElementById('Main.please_sure_new_psw').value);
    return false;
  }
  if($F('passwd') != $F('newpasswd')){
    alert(document.getElementById('Main.different_psw').value);
    form1.newpasswd.select();
    form1.newpasswd.focus();
    return false;
  }

  return true;
}
function   fucCheck(obj)   
  {   
    var   i,j,k,strTemp1,strTemp2;   
    strTemp1="0123456789";
    strTemp2="abcdefghijklmnopqrstuvwxyz"; 
    for(i=0;i<obj.length;i++)   
    {   
      j=strTemp1.indexOf(obj.charAt(i));   
      if(j==-1)   
     {   
      //˵�����ַ���������   
        break;  
     }
    } 
    for(i=0;i<obj.length;i++)   
    {   
      k=strTemp2.indexOf(obj.charAt(i));   
      if(k==-1){
      //˵�����ַ�������ĸ   
        break; 
       } 
    } 
     if(j==-1&&k==-1){
     	return 0;
     }else{
     	return 1;
     }         
    //˵���������ֺ���ĸ����� 
//    alert("���벻����Ҫ�����������룡");   
//    form1.passwd.select();
//    form1.passwd.focus();  
  }   


function  checkPwd(obj)
{
    strRef = "_";
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

<form name="form1" method="post" action="user.do?action=modPasswd">

<input type="hidden" name="no" value="<c:out value="${userSession.account}"/>">
<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
 <tr>
  <td nowrap><b><%=resource.srcStr("System.mod_psw") %>��</b></td>
 </tr> 
</table>
 <br>
<table align="center" width="650" border="0" cellspacing="1" cellpadding="4" class="table1">
  <!--  <tr class="tr1">
    <td nowrap colspan="2" align="center"><%=resource.srcStr("System.mod_psw")%></td>
  </tr> -->
  <tr class="tr3">
    <td nowrap width="100"><%=resource.srcStr("Login.account") %>��</td>
    <td nowrap><c:out value="${userSession.account}"/></td> 
  </tr> 
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("Login.old_psw") %>��</td>
    <td nowrap><input type="password" name="oldpasswd" class="pane" maxLength="16">&nbsp;*</td> 
  </tr>  
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("Main.new_psw") %>��</td>
    <td nowrap><input type="password" name="passwd" class="pane" maxLength="16" onKeyUp="if(/\W/.test(this.value)){alert('<%=resource.srcStr("system.passwdMessage4")%>');this.value='';}" onblur="if(fucCheck(this.value)&&this.value!=''){alert('<%=resource.srcStr("Main.have_digital") %>');this.value='';this.select();}">&nbsp;*��<%=resource.srcStr("Main.have_digital_psw") %>��</td> 
  </tr> 
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("Main.sure_new_psw") %>��</td>
    <td nowrap><input type="password" name="newpasswd" class="pane" maxLength="16" onKeyUp="if(/\W/.test(this.value)){alert('<%=resource.srcStr("system.passwdMessage4")%>');this.value='';}" onblur="if(fucCheck(this.value)&&this.value!=''){alert('<%=resource.srcStr("Main.have_digital") %>');this.value='';this.select();}">&nbsp;*</td> 
  </tr>   


</table>


<p align="center">
      <input class="button" type="submit" value='<%=resource.srcStr("Main.submit")%>' onclick="return checkForm()">&nbsp;
      <input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.history.back();">
    </p>

</form>

</body>
</html>




