<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource,java.util.Set"%>
<%
	Resource resource = (Resource) GetResource.getOneResource(
			application, session.getAttribute("locale").toString());
%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=gbk" />
<title></title>

<script language="JavaScript" src="../scripts/common.js"
	type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js"
	type="text/javascript"></script>
<script language="JavaScript" src="../scripts/calendar.js"
	type="text/javascript"></script>
<script language="JavaScript" src="../scripts/jquery.js"
	type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
<link href="../styles/displaytag.css" rel="stylesheet" type="text/css">

<style type="text/css">
</style>
</head>

<body>
	<!--javascript 国际化标识符-->
	<div id="javascriptI18n">
		<%@ include file="../../scripts/common.jsp"%>
		<input type="hidden" id="Main.sure_delete"
			value='<%=resource.srcStr("Main.sure_delete")%>' />
	</div>

	<script language="JavaScript">
var j$ = jQuery.noConflict();
j$().ready(function(){
	j$("input[name='timeType'][value='${timeType}']").attr("checked",true);
});	
function checkForm(){
	return true;
}
function restore(){
	j$("#openId").val("");
	j$("input[name='timeType'][value='all']").attr("checked",true)
}

function openSession(openId){
	window.location="message.do?action=sessionPage&openId="+openId;
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

	<input type="hidden" id="Main.sure_delete"
		value='<%=resource.srcStr("Main.sure_delete")%>' />
	<input type="hidden" id="Main.pageLowerLimitPrompt"
		value='<%=resource.srcStr("Main.pageLowerLimitPrompt")%>' />
	<input type="hidden" id="Main.qryEmptyPrompt"
		value='<%=resource.srcStr("Main.qryEmptyPrompt")%>' />
	<input type="hidden" id="Main.input_correct_page"
		value='<%=resource.srcStr("Main.input_correct_page")%>' />
	<input type="hidden" id="Main.pageUpperLimitPrompt"
		value='<%=resource.srcStr("Main.pageUpperLimitPrompt")%>' />
	<input type="hidden" id="Login.op_no_per"
		value='<%=resource.srcStr("Login.op_no_per")%>' />

	<table width="100%" style="border:0px;border-bottom:1px solid #cccccc;">
		<tr>
			<td nowrap><b>消息查询：</b>
			</td>
		</tr>
	</table>
	<br>
	<form name="form1" method="post" action="message.do?action=qry">
		<table width="100%" border="0" cellspacing="1" cellpadding="3"
			class="table1">
			<tr class="tr1">
				<td nowrap colspan="12"><%=resource.srcStr("System.query")%>：</td>
			</tr>
			<tr class="tr3">
				<td nowrap>关注者: </td>
				<td><input type="text" name="openId" id="openId" value="${openId}"></td>
				<td nowrap>时间: </td>
				<td>
					<input type="radio" name="timeType" value="all" checked="true">全部&nbsp;
					<input type="radio" name="timeType" value="today">今天&nbsp;
					<input type="radio" name="timeType" value="yesterday">昨天&nbsp;
				</td>
			</tr>
			<tr>
				<td nowrap class="tr3" colspan="10" align="center"><input
					class="button" type="submit"
					value='<%=resource.srcStr("Main.query")%>'
					onClick="return checkForm()">&nbsp; <input class="button"
					type="button" value='<%=resource.srcStr("Main.reset")%>'
					onclick="restore()">&nbsp;</td>
			</tr>
		</table>
	</form>
	<table width="100%" border="0" cellspacing="0" cellpadding="3"
		class="table1" style="word-break:break-all">
		<tr class="tr1">
			<td nowrap colspan="8">查询结果：&nbsp;&nbsp;&nbsp;</td>
		</tr>
	</table>

	<display:table name="requestScope.msgList" id="list"
		requestURI="message.do" cellspacing="1" cellpadding="3" class="table1"
		style="word-break:break-all" pagesize="15">
		<display:column title='关注者'>
			 <a href="javascript:openSession('<c:out value="${list.openId}"/>')"><c:out value="${list.openId}" /></a>
		</display:column>
		<display:column title='类型'>
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





