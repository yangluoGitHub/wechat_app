<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title></title>
		<script type='text/javascript' src="../scripts/jquery.js" type="text/javascript"></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/interface/userService.js'></script>
<style type="text/css">
table {
	font-weight: bold;
}

img{
	position: relative;
}
.content {
	position: absolute;
	left: 86%;
	top: 71px;
	width: 100px;
	border: 0;
	text-align: center;
	font-size: 15px;
	font-family: 宋体;
}

ul {
	margin: 0;
	padding: 0;
}

ul li {
	border: 0px;
	float: left;
	/*margin-left: 0px;
	width: 34px;
	height: 32px;
	list-style-type: none;
	font-size: 15px;*/
	position: relative;
	
}

.op {
	position: absolute;
	left: 84%;
	top: 10px;
	width: 90px;
	font-size: 16px;
	color: gold;
	font-family: 微软雅黑, 宋体;
	text-align: center;
}

body {
	margin: 0;
	padding: 0;
	position: relative;
}

.normal {
	background-image: url("../images/menu.png");
	background-repeat: no-repeat;
	color: #FFF;
	text-decoration: none;
}

.over {
	background-image: url("../images/menu_click.png");
	background-repeat: no-repeat;
	text-decoration: none;
	cursor: hand;
	color: #1456A0;
}

.down {
	background-image: url("../images/menu_click.png");
	background-repeat: no-repeat;
	text-decoration: none;
	color: #1456A0;
}

#menu {
	width: 100%;
	height: 9px;
}

.button {
	position: absolute;
	left: 219px;
}

.button p {
	border: 0px;
	font-size: 14px;
	font-family: "微软雅黑, 宋体";
	text-align: center;
	float: left;
	width: 174px;
	height: 37px;
	padding-left: 0;
	padding-right: 0;
	padding-top: 8px;
	padding-bottom:0px;
	margin-bottom: 0px;
	margin-left: 25px;
	margin-top: 60px;
	margin-right: 0px;
	position: relative;
}
</style>

<script language="JavaScript">
function refresh(){
	top.location.reload();
}
function backhome(){
	top.workarea.location="welcome.jsp";
	top.menu.location="menu.jsp?module=W&sid="+'<%=request.getParameter("sid")%>';
}

var lastObj = null;
var downObj = null;
var ps = document.getElementsByTagName("p");
function mouseover(obj){  
  if(lastObj != null){
    lastObj.className = "normal";
  }
  obj.className = "over";
  lastObj = obj;
  obj.style.zIndex=2;
  for(var i=0;i<ps.length;i++){
    if(ps[i]==obj) continue;
    if(ps[i]==downObj) continue;
    ps[i].style.zIndex = 0;
  }
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
  obj.style.zIndex = 3;
  for(var i=0;i<ps.length;i++){
    if(ps[i]==obj) continue;
    ps[i].style.zIndex = 0;
  }
  
}
function goModule(obj){
    //top.mid.showMenu();
    top.menu.location="menu.jsp?module="+obj+"&sid="+'<%=request.getParameter("sid")%>';
    //top.left.location="left.jsp?module="+obj; 
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
</head>

<body>

	<img src="../images/title/top-bg.png" id="headerimage"
		style="position:relative;width:100%;height:94px;float:left;"
		border="0" hspace="0" vspace="0" />
	<img src="../images/title/weili_logo.png" id="bankname"
		style="position: absolute;left: 17px;top: 17px;">
	<img src="../images/title/title.png" id="title"
		style="position: absolute;left: 220px;top: 13px;">
<div id="header">
  <div class="content">
  <ul>
  	<A href="#" onclick="javascript:backhome();">
  		<img alt="首页" title="首页" src="../images/title/home.png" border="0" style="cursor: hand; margin-right: 0 px;z-index:2;" onmouseover="javascript:src='../images/title/home_1.png'" onmouseout="javascript:src='../images/title/home.png'">
  	</A>
  	<A href="#" onclick="javascript:top.location='login.do?action=logout&MENU&sid=<%=request.getParameter("sid")%>'">
  		<img alt="退出" title="退出" src="../images/title/exit.png" border="0" style="cursor: hand;  z-index:1;" onmouseover="javascript:src='../images/title/exit_1.png'" onmouseout="javascript:src='../images/title/exit.png'">
  	</A> 
  </ul>
  </div>
<%--
</div>
<div id="user">
	<div class="op">
  		<a href="#" onclick="javascript:top.workarea.location='user.do?action=detail&no=<c:out value='${userSession.account}'/>&MENU&sid=<%=request.getParameter("sid")%>'"><c:out value="${userSession.name}"/></a>
 	</div>
</div>
--%>

	<div class="button">
		<c:forEach items="${userSession.menuList}" var="menu" varStatus="st">
			<c:if test="${menu.level==1}">
				<p id="${menu.no}" class="normal" onmouseover="mouseover(this)" style="margin-left: -25 px; z-index:1;"
					onmouseout="mouseout(this)"
					onclick="mousedown(this);goModule('<c:out value="${menu.no}"/>')">
					<b><c:out value="${menu.name}" /></b>
				</p>
			</c:if>
		</c:forEach>
	</div>
</body>
<script type="text/javascript">
	if(<%=request.getParameter("module")%>!=null){
    	mousedown(document.getElementById('<%=request.getParameter("module")%>'));
	}
	//解决在1024*768的分辨率下面出现退出按钮位置靠后问题
	var content = document.getElementById("content");
	if (screen.width <= 1024) {
		content.style.left = '80%';
	}
</script>
</html>

