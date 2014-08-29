
<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page language="java"%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.weili.wechat.common.UserSession"%>
<%@ page import="com.weili.wechat.vo.Menu"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.weili.wechat.common.GetResource"%>
<%
	UserSession userSession = (UserSession) session.getAttribute("userSession");
	if (userSession == null) {
%>
<script type="text/javascript">
	   window.top.location="timeout.jsp";
	 </script>
<%
	//response.sendRedirect("timeout.jsp");
		return;
	}
	Resource resource = (Resource) GetResource.getOneResource(
			application, session.getAttribute("locale").toString());
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<link rel="StyleSheet" href="tree/css/tree.css" type="text/css">
<!-- <script type="text/javascript" src="tree/js/tree.js"></script> -->
		<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/interface/accountService.js'></script>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	height: 100%;
}

#image {
	text-align: center;
	margin-top: 25px;
	padding-left: 13px;
}

#mm {
	margin-left: 25px;
	margin-top: 0;
}

a {
	text-decoration: none;
}

.normal {
	background-image: url(../images/sidebutton01.png);
	background-repeat: no-repeat;
	text-align: center;
	text-decoration: none;
	color: #1456A0;
}

.over {
	background-image: url(../images/sidebutton01_1.png);
	background-repeat: no-repeat;
	text-align: center;
	text-decoration: none;
	cursor: hand;
	color: #FFF;
}

.down {
	background-image: url(../images/sidebutton01_1.png);
	background-repeat: no-repeat;
	text-align: center;
	text-decoration: none;
	color: #FFF;
}

#menuleft {
	position: absolute;
	float: left;
	height: 100%;
	background-color: #fff;
	z-index: 2;
}

#menuright {
	position: absolute;
	float: right;
	height: 100%;
	width: 198px;
	left: 0px;
	background-image: url(../images/menu/side-bg02.png);
	z-index: 1;
}

.u2 {
	margin-left: 0px;
	margin-top: 50px;
}

.u2 li{
	border: 0px;
	list-style-type: none;
	height: 111px;
	width: 26px;
	font-size: 14px;
	font-family: "微软雅黑, 宋体";
	margin-top: 0px;
	padding: 25px 0 0 0;
	line-height: 100%;
}
.u1 li {
	width:171px;
	/* background-image: url(../images/line2.png);
	background-repeat: no-repeat;
	background-position:bottom; */
	height: 25px;
	list-style-type: none;
	font-size: 16px;
	font-family: "微软雅黑, 宋体";
	
	/* margin-left: 10px; */
}
li a{
	color:white;
}

a { color:white; white-space:nowrap;}
.jsonTree { font-size: 15px; font-family: "微软雅黑, 宋体"; }
.jsonTree a { text-decoration:none; }
.jsonTree dl { padding:0; margin:0; }
.jsonTree dt { margin-left:-20px;color: white; cursor:pointer; padding:10 28px; height:2em;/* line-height:2em; border-radius:5px; */ background-image: url(../images/line2.png);background-repeat: no-repeat;background-position:bottom; }
.jsonTree dd { margin:0;height:2em; }
.jsonTree dl dd { padding-left:2em;  margin-left:10px; font-size: 13px;}
.jsonTree dl dd a { display:block; padding:3px; border-radius:5px; }
.jsonTree-icon {background-image: url(../images/spot.png);background-repeat: no-repeat;}
.jsonTree-show dd { display:block; }
.jsonTree-hide dd { display:none; }
.jsonTree-show dt { left:-10px;background:#7EABCA; }
.jsonTree a:hover .jsonTree-name { text-decoration:none; }
.jsonTree .jsonTree-name { padding-left: 5px;  }
</style>
<script language="JavaScript" >
function toWorkarea(link){
	top.workarea.location=link;
}
var jsonTree = function (wrap, obj) {
	if (typeof wrap === 'string') wrap = document.getElementById(wrap);
	wrap.innerHTML = jsonTree.html(obj);
	jsonTree.event(wrap);
};
// 生成UI
jsonTree.html = function (obj) {
	var html = [], i, length,
		toString = Object.prototype.toString,
		isArray = function (obj) {
			return toString.call(obj) === '[object Array]';
		};
		
	var each = function (obj, parent) {
		if (isArray(obj)) {
			length = obj.length;
			for (i = 0; i < length; i ++) {
				html.push('<dd><a href="');
				html.push(obj[i]['url']);
				html.push('">');
				html.push('<img name="spot" src="../images/spot.png" style="border:2px #8FC3FE solid;" "/><span sytle="padding-left: 5px;" class="jsonTree-name" >');
				html.push(obj[i]['name']);
				html.push('</span></a></dd>');
			};
		} else if (typeof obj === 'object') {
			for (i in obj) {
				html.push('<dd><dl class="jsonTree-hide"><dt><center>');
				html.push(i);
				html.push('</center></dt>');
				html.push(each(obj[i], i));
				html.push('</dl></dd>');
			};
		};
		return html;
	};
	return '<dl class="jsonTree">' + each(obj).join('') + '</dl>';
};
// 绑定行为	
jsonTree.event = function (wrap) {
	wrap.onclick = function (event) {
		event = event || window.event;
		var link, bar,
			target = event.target || event.srcElement,
			parentNode = target.parentNode;
		
		if (target.nodeName === 'DT') bar = target;
		if (target.nodeName === 'A') link = target;
		if (parentNode.nodeName === 'DT') bar = parentNode;
		if (parentNode.nodeName ===  'A') link = parentNode;
		
		if (bar) {
			$('DT').each(function(){
				if(this.parentNode.className == 'jsonTree-show'){
					this.parentNode.className = 'jsonTree-hide';
				}
				
			});
			bar.parentNode.className = bar.parentNode.className === 'jsonTree-show' ?
				'jsonTree-hide' :
				'jsonTree-show';
		} 
		if(link){
			var thisImg = $(link).children().first()[0];
			thisImg.style.border= '2px #1456A0 solid';
			$("img[name='spot']").not(thisImg).css('border','2px #8FC3FE solid');
		}
	};
};

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

function goModule(obj){
   // top.mid.showMenu();
    top.menu.location="menu.jsp?module="+obj+"&sid="+'<%=request.getParameter("sid")%>'; 
    if(obj == 'A'){
    	top.workarea.location="account.do?action=pubAccountInfo";
    }else{
    	top.workarea.location="welcome.jsp";
    }
   // top.workarea.location="welcome.jsp";
   <%-- top.menuheader.location="menuheader.jsp?module="+obj+"&sid="+'<%=request.getParameter("sid")%>';  --%>
    top.header.location="header.jsp?module="+obj+"&sid="+'<%=request.getParameter("sid")%>'; 
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

function qryLogo(){
	var logo2 ="";
	DWREngine.setAsync(false);
	accountService.getLogoLocation("${userSession.wechatId}",
	function result(data) {
		logo2 = data;
	});
	DWREngine.setAsync(true);
	return logo2;
}

</script>
</head>


<body topmargin="0" marginheight="0" leftmargin="0px" >
	<%-- <img src="../images/left01.jpg" align="top">--%>
	<div id="menuleft">
			<div class="main">
				<ul class="u2">
					<c:forEach items="${userSession.menuList}" var="menu">
						<c:if test="${menu.level==1}">
				        	<li id="${menu.no}" class="normal" onmouseover="mouseover(this)" 
				        		onmouseout="mouseout(this)" onclick="mousedown(this);goModule('<c:out value="${menu.no}"/>')">
				        		<p title='${menu.name}'><b><c:out value="${menu.name}"/></b></p>
				        	</li>
				      	</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
	<script type="text/javascript">
 		var mod='<%=request.getParameter("module")%>';
			if (mod!=null&&mod!='W')
        	mousedown(document.getElementById(mod));
  		</script>
		<%
			String module = request.getParameter("module");
			String sid = request.getParameter("sid");
			if (sid != null && sid != "" && !sid.equals(session.getId())) {
		%>
		<script type="text/javascript">
             window.top.location="loginagain.jsp";
         </script>
		<%
			}
			module = module == null ? "" : module;
			if (module.equals("W")) {
		%>
		<script type="text/javascript">
             self.location='menu_image.jsp?sid='+'<%=sid%>';
		</script>
		<%
			}
			List menuList = userSession.getMenuList();
		%>
	<div id="menuright">
		
		
		<div id="image">
			<%
				if (module.equals("A")) {
			%>
			<img id="logo" >
			<script type="text/javascript" >
				$("#logo").attr("src", "../images/menu/" + qryLogo() + "");
			</script>
			
			<%
				} else if (module.equals("B")) {
			%>
			<img src="../images/plan.png">
			<%
				} else if (module.equals("E")) {
			%>
			<img src="../images/menu/icon03.png">
			<%
				} else if (module.equals("F")) {
			%>
			<img src="../images/menu/icon02.png">
			<%
				}
			%>
		</div>
		<div id="mm">
			<script type="text/javascript">
				//d = new dTree('d');
				//d.config.folderLinks = false;
				//document.write(d);
				var _data = {
			<%
			String no;
			StringBuffer sb = new StringBuffer();
			StringBuffer sb1 = new StringBuffer();
			Iterator it = menuList.iterator();
			while (it.hasNext()) {

				Menu menu = (Menu) it.next();
				no = menu.getNo();
				if (no.startsWith(module)) {
					
					if (no.length() == 3){
						sb1.append("\"").append(menu.getName()).append("\":[");
						for(Object o : menuList){
							Menu m = (Menu)o;
							if(m.getSuperMenu().getNo() == no){
								sb1.append("{\"name\":\"").append(m.getName()).append("\",\"url\":\"javascript:toWorkarea('").append(m.getURL())
								.append("')\"},");
							}
						}
						if(sb1.length()>0) sb1.delete(sb1.length()-1,sb1.length());
						sb1.append("],");
					}
					
				}

			}
			if(sb1.length()>0)
				sb1.delete(sb1.length()-1,sb1.length());
			out.print(sb1.toString());%> 
			};
			jsonTree('mm',_data);         
			</script>
	
		</div>
	</div>

</body>
</html>
