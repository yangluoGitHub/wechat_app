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
<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="Main.input_new_psw" value='<%=resource.srcStr("Main.input_new_psw")%>'/>
<input type="hidden" id="System.input_sure_psw" value='<%=resource.srcStr("System.input_sure_psw")%>'/>
<input type="hidden" id="Main.different_psw" value='<%=resource.srcStr("Main.different_psw")%>'/>
<input type="hidden" id="System.password_less_than" value='<%=resource.srcStr("System.password_less_than")%>'/>
</div>

<script language="JavaScript">
function checkForm(){


  if(isNull($F('passwd'))){
    alert(document.getElementById('Main.input_new_psw').value);
    return false;
  }
  if(isNull($F('repasswd'))){
    alert(document.getElementById('System.input_sure_psw').value);
    return false;
  }
  if($F('passwd') != $F('repasswd')){
    alert(document.getElementById('Main.different_psw').value);
    return false;
  }
  if(($F('passwd').length<4)){
    alert(document.getElementById('System.password_less_than').value);
    return false;
  }

  return true;
}

</script>

<br>


<form name="form1" method="post" action="op.do?action=resetPasswd">

<input type="hidden" name="no" value="<c:out value="${op.no}"/>">

<table align="center" width="650" border="0" cellspacing="1" cellpadding="4" class="table1">
  <tr class="tr1">
    <td nowrap colspan="2" align="center"><%=resource.srcStr("System.reset_psw")%></td>
  </tr> 
  <tr class="tr3">
    <td nowrap width="100"><%=resource.srcStr("Login.account") %>：</td>
    <td nowrap><c:out value="${op.no}"/></td> 
  </tr> 
  <tr class="tr3">
    <td nowrap width="100"><%=resource.srcStr("System.name") %>：</td>
    <td nowrap><c:out value="${op.name}"/></td> 
  </tr>   
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("Main.new_psw") %>：</td>
    <td nowrap><input type="password" name="passwd" class="pane">&nbsp;*（<%=resource.srcStr("System.password_less_than") %>）</td> 
  </tr> 
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("Main.sure_new_psw") %>：</td>
    <td nowrap><input type="password" name="repasswd" class="pane">&nbsp;*</td> 
  </tr>   


</table>


    <p align="center">
      <input class="button" type="submit" value='<%=resource.srcStr("Main.submit")%>' onclick="return checkForm()">&nbsp;
      <input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.history.back();">
    </p>

</form>

</body>
</html>




