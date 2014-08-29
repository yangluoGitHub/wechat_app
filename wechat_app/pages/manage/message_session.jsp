<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/messageService.js'></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
<link href="../styles/displaytag.css" rel="stylesheet" type="text/css">
</head>

<body>
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
</div>

<script language="JavaScript">
var j$ = jQuery.noConflict();
function sendMessage(){
	var msg = j$("#sendMsg").text();
	DWREngine.setAsync(false);
	var openId = '${openId}';
	var wechatId = '${sessionScope.userSession.wechatId}';
	DWREngine.setAsync(false);
	messageService.sendMessage(wechatId, openId, msg, function(data){
		if(data == null || data.length == 0) {
			alert("通信失败");
			return;
		} 
		if(data[0] == "success"){
			alert("发送成功");
			var htmlTr = "<tr><td align='center'>"+wechatId+"</td><td align='center'>text</td><td align='center'>"+msg+"</td><td align='center'>"+data[1]+"</td></tr>"
			var tr = j$("#messageTable tr").eq(0);
			j$(tr).after(htmlTr);
			j$("#sendMsg").text("");
		}else{
			alert(data[1]);
		}
	});
	DWREngine.setAsync(true);
}
function go(){
			var pageNum = document.getElementById('curPages').value;
			//alert(pageNum);//获取跳转页码
			var obj=document.getElementsByTagName("a");
			var herf=new String("");
			var k = obj.length - 1 ;
		    var ahref=obj[k]; //获取页面上最后一个链接地址
		    var _ahref=obj[k-1]; //获取页面上倒数第二个链接地址
		    var change = 0;//对末页地址的修正值=0
		    herf=""+ahref;
		    _herf=""+_ahref;
		    //alert(herf);
		    var m=herf.split("-p=");
		    var k=_herf.split("-p=");
		   	if(k.length>1){
		   		var j = k[1].split("&");
		   		if(parseInt(j[0])==1){
		   			change = 1;//当倒数第二个链接是首页时，修正值=1
		   		}
		   	}
		    if(m.length>1){
		    	var n = m[1].split("&");
		    	var totalNum = n[0];
		    	totalNum = parseInt(totalNum) + change;
			    if(isNull(pageNum))
				{	
					alert(document.getElementById('Main.qryEmptyPrompt').value);
					document.getElementById('curPages').select();
					return false;
				}
				else if(!isInteger(pageNum))
				{	
					alert(document.getElementById('Main.input_correct_page').value);
					document.getElementById('curPages').select();
					return false;	
				}
				else if(parseInt(pageNum)<=0)
				{	
					alert(document.getElementById('Main.pageLowerLimitPrompt').value);
					document.getElementById('curPages').select();
					return false;
				}
				else if(parseInt(pageNum)>parseInt(totalNum))
				{	
					alert(document.getElementById('Main.pageUpperLimitPrompt').value);
					document.getElementById('curPages').select();
					return false;
				}
		    }
		    herf = herf.replace(/d-(\d+)-p=(\d+)/g,"d-$1-p="+pageNum);//得到跳转页地址
		    //alert(herf);
		    window.location=herf;//转到跳转页
	}
</script>
<br>

<table id="sendTable" align="center" width="450" border="0" cellspacing="1" cellpadding="5" class="table1" >
	<tr class="tr1">
    	<td nowrap align="center">与【${openId}】的聊天</td>
	</tr>  
	<tr class="tr3">
		<td align="center"><textarea rows="5" cols="100" id="sendMsg" name="sendMsg"></textarea></td> 
	</tr>
	<tr class="tr3">
		<td align="center"><input class="button" type="button" value="发送" onclick="javascript:sendMessage()" > </td>
	</tr>
</table>

<display:table htmlId="messageTable" name="requestScope.msgList" id="list" requestURI="message.do" cellspacing="1" cellpadding="3" class="table1"
		style="word-break:break-all" pagesize="15">
	<display:column title='发送者'>
		<c:if test="${list.messageMode == 0}">
			<c:out value="${list.openId}"/>
		</c:if>
		<c:if test="${list.messageMode == 1}">
			<c:out value="${list.wechatId}"/>
		</c:if>
	</display:column>
	<display:column title='消息类型'>
		<c:out value="${list.messageType}" />
	</display:column>
	<display:column title='内容'>
		<c:out value="${list.content}" />
	</display:column>
	<display:column title='时间'>
		<c:out value="${list.createDate} ${list.createTime} " /> 
	</display:column>
</display:table>
</body>
</html>




