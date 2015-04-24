<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.weili.wechat.common.GetResource"%>
<% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
<style type="text/css">
body{
	margin:0;padding:0;
	background-image:url("../images/menu/side-bg01.png");
	background-repeat: no-repeat;
}
.ima{
	position:absolute;
	margin-top:120px;
	margin-left: 0;
	text-align: center;
}
.ima ul{
	position:absolute;
	list-style:none;
	text-align: center;
}
.ima ul li{
	text-align: center;
	margin-bottom:15px;
}
img{
	border:0;
	display:block;
	cursor: hand;	
}

.over {
    cursor:hand;    
}
</style>
</head>
<body>

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
function goModule(obj){
    //top.mid.showMenu();
    top.menu.location="menu.jsp?module="+obj+"&sid="+'<%=request.getParameter("sid")%>';
    //top.left.location="left.jsp?module="+obj;   
    //top.menuheader.location="menuheader.jsp?module="+obj+"&sid="+'<%=request.getParameter("sid")%>'; 
    top.header.location="header.jsp?module="+obj+"&sid="+'<%=request.getParameter("sid")%>'; 
    if(obj == 'A'){
    	//top.workarea.location="account.do?action=pubAccountInfo";
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
<div id="main">
<div class="ima">
<ul>
<c:forEach items="${userSession.menuList}" var="menu">
  <c:if test="${(menu.level==1)}">
	<c:if test="${(menu.no=='A')}">		 
		<li>
		<img src="../images/menu/menu1.png" id="pic01" onmouseover="javascript:src='../images/menu/menu1_1.png'" onmouseout="javascript:src='../images/menu/menu1.png'" onclick="goModule('A');">
		</li>
    </c:if>
    <c:if test="${(menu.no=='B')}">	
		<li>
		<img src="../images/dispatch1.png" id="pic02" onmouseover="javascript:src='../images/dispatch2.png'" onmouseout="javascript:src='../images/dispatch1.png'" onclick="goModule('B');">
		</li>
    </c:if>
    <c:if test="${(menu.no=='C')}">	
		<li>
		<img src="../images/dispatch1.png" id="pic03" onmouseover="javascript:src='../images/dispatch2.png'" onmouseout="javascript:src='../images/dispatch1.png'" onclick="goModule('C');">
		</li>
    </c:if>
    <c:if test="${(menu.no=='D')}">	
		<li>
		<img src="../images/pic0901.jpg" id="pic04" onmouseover="mouseover(this)" onmouseout="mouseout(this)" onclick="mousedown(this);goModule('D')">
		</li>
    </c:if>
    <c:if test="${(menu.no=='E')}">	
		<li>
		<img src="../images/menu/menu3.png" id="pic05" onmouseover="javascript:src='../images/menu/menu3_1.png'" onmouseout="javascript:src='../images/menu/menu3.png'" onclick="goModule('E');">
		</li>
    </c:if>
    <c:if test="${(menu.no=='F')}">	
		<li>
		<img src="../images/menu/menu2.png" id="pic06" onmouseover="javascript:src='../images/menu/menu2_1.png'" onmouseout="javascript:src='../images/menu/menu2.png'" onclick="goModule('F');">
		</li>
    </c:if>
    <c:if test="${(menu.no=='G')}">	
		<li>
		<img src="../images/pic0701.jpg" id="pic07" onmouseover="mouseover(this)" onmouseout="mouseout(this)" onclick="mousedown(this);goModule('G')">
		</li>
    </c:if>
     <c:if test="${(menu.no=='H')}">	
		<li>
		<img src="../images/pic0801.jpg" id="pic08" onmouseover="mouseover(this)" onmouseout="mouseout(this)" onclick="mousedown(this);goModule('H')">
		</li>
    </c:if>
  </c:if>
</c:forEach >
</ul>
</div>
</div>
</body>

</html>