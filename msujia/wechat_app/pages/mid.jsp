<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.weili.wechat.common.GetResource"%>
<% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());
	if (resource==null)
   resource= new Resource("zh_CN");
%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=GBK">
<TITLE></TITLE>
</HEAD>
<style type="text/css">
body{
	margin:0;
	padding:0;
	background-color:#fff;
}
.show{
	position:absolute;
	top:50%;
	width:8px;
	visibility:hidden;
}
.hide{
	position:absolute;
	top:50%;
	width:8px;
}
</style>
<BODY lefgmargin="0" rightmargin="0" topmargin="0" bottommargin="0" marginwidth="0">

<script language="JavaScript">

var isHide = false;

function hideMenu(){
  var frame = top.document.getElementById("frameset3");
  var image = document.getElementById("hideImg");
  var image2 = document.getElementById("ShowImg");
  image2.style.visibility = "visible";
  frame.cols = "0,*";
  image.style.visibility = "hidden";
  isHide = true;
  return;
}


function showMenu(){
  var frame = top.document.getElementById("frameset3");
  var image = document.getElementById("ShowImg");
  image.style.visibility = "hidden";
  var image2 = document.getElementById("hideImg");
  image2.style.visibility = "visible";
  frame.cols = "193,*";
  isHide = false;
  return;
}

function hideOrShowMenu(){
  if(isHide){
    showMenu();
  }else{
    hideMenu();
  }
}
</script>
  <div id="hideorshow">
     <div class="hide">
       <img id="hideImg" title='隐藏菜单栏' onmouseover="javascript:src='../images/hidden02.png'" onmouseout="javascript:src='../images/hidden01.png'" style="cursor:hand;" onClick="hideOrShowMenu();"  src="../images/hidden01.png" border="0">
     </div>
     <div class="show">
       <img id="ShowImg" title='显示菜单栏' onmouseover="javascript:src='../images/show02.png'" onmouseout="javascript:src='../images/show01.png'" style="cursor:hand;" onClick="hideOrShowMenu();"  src="../images/show01.png" border="0">
     </div>
  </div>
</BODY>
</HTML>

<!--javascript 国际化标识符-->
<div name="javascriptI18n">
<input type="hidden" id="Login.show_menu" value='<%=resource.srcStr("Login.show_menu")%>'/>
<input type="hidden" id="Login.hide_menu" value='<%=resource.srcStr("Login.hide_menu")%>'/>
</div>
