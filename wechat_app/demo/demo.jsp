<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Easy-Demo</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	

	<link rel="stylesheet" href="css/style.css">

	<script type="text/javascript" src="js/jquery-2.1.0.min.js"></script>
	<script type="text/javascript" src="js/easyform.js"></script>

  </head>
  
  <body>

<div class="form-div">
    <form id="reg-form" action="register.do?action=register" method="post">

        <table>
            <tr>
                <td>手机号</td>
                <td><input name="uid" type="text" id="uid" easyform="length:4-16;char-normal;real-time;" message="手机号11wei数字" easytip="disappear:lost-focus;theme:blue;" ajax-message="用户名已存在!">
                </td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input name="psw1" type="password" id="psw1" easyform="length:6-16;" message="密码必须为6—16位" easytip="disappear:lost-focus;theme:blue;"></td>
            </tr>
            <tr>
                <td>确认密码</td>
                <td><input name="psw2" type="password" id="psw2" easyform="length:6-16;equal:#psw1;" message="两次密码输入要一致" easytip="disappear:lost-focus;theme:blue;"></td>
            </tr>
        </table>

		<div class="buttons">
			<input value="注 册" type="submit" style="margin-right:20px; margin-top:20px;">
			<input value="我有账号，我要登录" type="button" style="margin-right:45px; margin-top:20px;">
        </div>
		
        <br class="clear">
    </form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#reg-form').easyform();
});
</script>

</body>
</html>
