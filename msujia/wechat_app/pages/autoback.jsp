<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page language="java"%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>

<!--javascript 国际化标识符-->
<div name="javascriptI18n">
<input type="hidden" id="Login.op_no_per" value='<%=resource.srcStr("Login.op_no_per")%>'/>
</div>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<script language="JavaScript">
alert(document.getElementById('Login.op_no_per').value)
window.history.back();
</script>
</head>

<body>
</body>
</html>






