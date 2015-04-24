<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=GBK">

<link href="../styles/jquery-ui.css" rel="stylesheet"type="text/css"/>
<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/json2.js" type="text/javascript"></script>
<script src="../scripts/jquery-ui.min.js"></script>

<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
<TITLE>welcome page</TITLE>
<style type="text/css">
.normal{
	position:absolute;
	margin:0 auto;
	top:50%;
	left:50%;
	margin:-340px 0 0 -540px;
	width: 1080px;
	height: 685px;
}
.little{
	position: relative;
	width: 90%;
	height: 90%;
	top: 5%;
	overflow: hidden;
}
</style>
</HEAD>

<BODY>
<script language="javascript">
var j$ = jQuery.noConflict();
var userNo = "${userSession.account}";
</script>
<div style="border: 0;width: 100%;height: 100%;text-align: center;">
<!--  
	<marquee scrollAmount="4" direction="left" onmouseover="this.stop();" onmouseout="this.start()">
		<p style="font-family: 黑体;color: gray"><font size="3px" ><c:out value="${userSession.name}"/>，您好！欢迎使用TSP加钞管理系统！</font></p>
	</marquee>
-->
	<div id="wel" class="little">
		<img id="welimg" src="../images/img01.png" border="0" style="text-align: center;">
	</div>
</div>
<script type="text/javascript">
	if(screen.width > 1280 && screen.height > 900){
		var wel = document.getElementById("wel");
		var welimg = document.getElementById("welimg");
		welimg.style.width = 1080+"px";
		welimg.style.height = 685+"px";
		wel.className = "normal";
	}
</script>
</BODY>
</HTML>
