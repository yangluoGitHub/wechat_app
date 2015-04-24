<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.weili.wechat.common.GetResource"%>
<%Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString()); %>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body>
<!--javascript 国际化标识符-->
<div name="javascriptI18n">
<input type="hidden" name="passtxDate" id="passtxDate" value='<c:out value="${passtxDate}"/>' >
<input type="hidden" name="message1" id="message1" value='<%=resource.srcStr("system.passwdMessage") %>' >
<input type="hidden" name="message2" id="message2" value='<%=resource.srcStr("system.passwdMessage1") %>' >
</div>
<script>
var day = document.getElementById("passtxDate").value;
if(day > 0 ) {
   alert(document.getElementById("message1").value+day+document.getElementById("message2").value);
}
window.location="homepage.jsp" ;
</script>
</body>
</html>

<!--javascript 国际化标识符-->
<div name="javascriptI18n">
</div>
