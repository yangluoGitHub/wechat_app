<%@ page language="java"%>
<%@ page contentType="text/html; charset=gbk"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
</head>

<body>

<input type="hidden" value="${seccFlag}" id="seccFlag" name="seccFlag"/>
<input type="hidden" value="${message}" id="message" name="message"/>
<script type="text/javascript">
function show()
{
	alert($("message").value);
}
show();
</script>

</body>
</html>
