<%
if(session.getAttribute("locale")==null||session.getAttribute("locale")=="") 
{ 
out.println("<SCRIPT language=JavaScript>");
out.println("window.top.location='login.jsp'");
out.println("</script>"); 
return;
}
 %>
<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%>
<% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.weili.wechat.common.*"%>
<html>
<head>
<%
	if (resource==null){
  %>
<script language="JavaScript">
   window.top.location="timeout.jsp";
</script>
<%
return;}
%>
<title><%=resource.srcStr("Login.op_server")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
</head>
 
<% 
	UserSession userSession = (UserSession)request.getSession().getAttribute("userSession");

	String loginStr = "";
	if(userSession != null){
		if(userSession.getOnline_flag()==-1){
			loginStr = resource.srcStr("Login.first_login");
		}else{
			loginStr = resource.srcStr("Login.previous_login_time") + ":" + userSession.getLoginTime()+" "+ resource.srcStr("Login.previous_login_ip") + ":"+ userSession.getLoginIp();
		}
	}else{
		loginStr = resource.srcStr("Login.exit_user");
	}
	String sid = request.getSession().getId();
 %>
 
 <script language="javascript">
 	window.status = '<%=loginStr%>';
 </script>
<frameset id="frameset1" rows="94,*" framespacing="0" frameborder="no" bordercolor="cccccc">
	<frame name="header" src='header.jsp?sid=<%=sid %>' marginwidth="0" marginheight="0" frameborder="0" scrolling="no" noresize>          
	<frameset id="frameset3" cols="215,*"  framespacing="0" frameborder="no">
		<frame name="menu" src='menu.jsp?module=W&sid=<%=sid %>' marginwidth="8" marginheight="0" frameborder="0" scrolling="auto" noresize>
		<frameset id="frameset5" rows="40,*" framespacing="0" frameborder="no" bordercolor="cccccc">
			<frame name="userarea" src='userframe.jsp?sid=<%=sid %>' marginwidth="0" marginheight="5" frameborder="0" scrolling="no" noresize>
			<frame name="workarea" src='welcome.jsp' marginwidth="8" marginheight="10" frameborder="0" scrolling="auto" noresize>
		</frameset>
	</frameset>
</frameset><noframes></noframes>

<!--javascript 国际化标识符-->
<div id="javascriptI18n">
</div>
</html>
