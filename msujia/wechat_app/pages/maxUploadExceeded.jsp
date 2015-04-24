<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page language="java"%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<link href="../styles/common.css" rel="stylesheet" type="text/css">
<script language="javascript">
function getback()
{
	alert(document.getElementById('Login.upload_than_max').value);
	try
	{
    	parent.window.unLockPage();
    	parent.window.reloadFiles();
	}catch  (e)
	{
   		window.history.back();
	} 
}
</script>
<title></title>
</head>
<body onload="getback()">
</body>
</html>
<!--javascript 国际化标识符-->
<div name="javascriptI18n">
<input type="hidden" id="Login.upload_than_max" value='<%=resource.srcStr("Login.upload_than_max")%>'/>
</div>

