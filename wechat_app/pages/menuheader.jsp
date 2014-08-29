<%@ page import="com.weili.wechat.common.Resource"%>
<?xml version="1.0" encoding="GBK" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>Insert title here</title>
<style type="text/css">
body{
	margin:0;
	padding:0;
	position:relative;
}
.normal {
	color:#7061b0;
	text-decoration:none;
}
.over {
    background-image:url("../images/menu_click.png");
    background-repeat:no-repeat;
    text-decoration:none;
    cursor:hand;
    color: white;    
}
.down {
    background-image:url("../images/menu_click.png");
    background-repeat:no-repeat;
    text-decoration:none;
    color: white;
}

#menu{
	width:100%;
	height:9px;
}
.button{
	position:absolute;
	left: 14%;
}
.button p{
	border:0px;
	margin:10px 30px 0 30px;
	padding:11px 0 11px 0;
	font-size:15px;
	font-family:黑体;
	text-align:center;
	float:left;
	width:130px;
	height:37px;
}
</style>

<script language="JavaScript">

var lastObj = null;
var downObj = null;

function mouseover(obj){  
  if(lastObj != null){
    lastObj.className = "normal";
  }
  obj.className = "over";
  lastObj = obj;
  if(downObj != null){
    downObj.className = "down";
  }
}

function mouseout(obj){
  if(obj != downObj){
    obj.className = "normal";
  }
}

function mousedown(obj){
  if(downObj != null){
    downObj.className = "normal";
  }
  obj.className = "down";
  downObj = obj;
  
}
</script>


<script language="JavaScript">
function refresh(){
top.location.reload();
}

function goModule(obj){
    //top.mid.showMenu();
    top.menu.location="menu.jsp?module="+obj+"&sid="+'<%=request.getParameter("sid")%>';
    //top.left.location="left.jsp?module="+obj; 
    
    if(obj == "A"){
    	top.workarea.location="account.do?action=pubAccountInfo";
    }else{
    	top.workarea.location="welcome.jsp";
    }
}
function goModPasswd(){
    top.mid.showMenu();
    top.menu.location="menu.jsp?module=";
    top.workarea.location="op.do?action=modPasswdPage";
}

function goHelp(){
    top.mid.showMenu();
    top.menu.location="menu.jsp?module=";
    top.workarea.location="./help/helpindex.jsp";
    
}

function showHelp(){
	window.open("./help/help.jsp","help","height=605, width=930, top=50, left=40, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no");
}
  
</script>
</head>
<body>
<%--
<div id="menu">

<img src="../images/menuheaderbackground.jpg" style="width:100%;height:59px;float:left;z-index:-1;">
--%>
	<div class="button">
	       <c:forEach items="${userSession.menuList}" var="menu">
			<c:if test="${menu.level==1}">
	        	<p id="${menu.no}" class="normal" onmouseover="mouseover(this)" onmouseout="mouseout(this)" onclick="mousedown(this);goModule('<c:out value="${menu.no}"/>')"><b><c:out value="${menu.name}"/></b></p>
	      	</c:if>
	      </c:forEach>
	</div>
     <script type="text/javascript">
     if(<%=request.getParameter("module")%>!=null){
        mousedown(document.getElementById('<%=request.getParameter("module")%>'));
        }
  </script> 
</body>
</html>