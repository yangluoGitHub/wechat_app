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
<input type="hidden" id="Main.input_dev_type" value='<%=resource.srcStr("Main.input_dev_type")%>'/>
</div>

<script language="JavaScript">
function checkForm(){
  if(isNull($F('name'))){
    alert(document.getElementById('Main.input_dev_type').value);
    return false;
  }
   return true;
}
</script>

<br>


<form name="form1" method="post" action="devcatalog.do?action=mod">

<input type="hidden" name="no" value="<c:out value="${devcatalog.no}"/>">

<table align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1">
 <tr class="tr1">
    <td nowrap colspan="2" align="center"><%=resource.srcStr("System.mod_dev_type")%></td>
  </tr>  
  <tr class="tr3">
    
  
    <td nowrap><%=resource.srcStr("Main.dev_type") %>：</td>
    <td nowrap><input type="text" name="name" class="pane" value="<c:out value="${devcatalog.name}"/>" onblur="checktext($(name),10);">&nbsp;*</td> 
  </tr>
  <tr class="tr3">
  <td nowrap width="150"><%=resource.srcStr("System.description") %>：</td>
    <td nowrap><input type="text" name="enname" class="pane" value="<c:out value="${devcatalog.enname}"/>" onblur="checktext($(enname),30);">&nbsp;</td> 
  </tr>
</table>

    <p align="center">
      <input class="button" type="submit" value='<%=resource.srcStr("Main.submit")%>' onclick="return checkForm()">&nbsp;
      <input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.history.back();">
    </p>

</form>

</body>
</html>

