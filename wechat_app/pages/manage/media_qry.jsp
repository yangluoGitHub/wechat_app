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
	
});	
function checkForm(){
	return true;
}
function restore(){
	$('name').value = "";
}
function del(obj){
	   if(confirm(document.getElementById('Main.sure_delete').value)){
	    window.location="media.do?action=del&id="+obj;
	  } 
}
function mod(id){
	window.location="media.do?action=modPage&id="+id;
}

function previewImage(id){
	window.open("media.do?action=previewImage&id="+id, "预览图片", "height=500, width=800, scrollbars=yes, resizable=no");
}

function checkStartDate(checkedDate) {
	if (!check2Date(checkedDate.value, getDateStr())) {
		checkedDate.value = j$("#tempDate1").val();
		alert("查询起始日期不得晚于当前日期!");
		return false;
	} 
	
	if(!isNull(j$("#endDate").val())) {
		if (!check2Date(checkedDate.value, j$("#endDate").val())) {
			checkedDate.value = j$("#tempDate1").val();
			alert("查询起始日期不得晚于结束日期!");
			return false;
		}
	}
	j$("#tempDate1").val(checkedDate.value);
}

//检查结束时间合法性
function checkEndDate(checkedDate) {
	if(!isNull(j$("#startDate").val())) {
		if (!check2Date(j$("#startDate").val(), checkedDate.value)) {
			checkedDate.value = j$("#tempDate2").val();
			alert("查询结束日期不得早于开始日期!");
			return false;
		}
	}
	j$("#tempDate2").val(checkedDate.value); 
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
			<td nowrap><b>多媒体查询：</b>
			</td>
			<td nowrap align="right"><input class="button" type="button"
				value="新增资源"
				onclick="javascript:window.location='media.do?action=addPage'"
				style="width:110px;">&nbsp;</td>
		</tr>
	</table>
	<br>
	<form name="form1" method="post" action="media.do?action=qry">
		<input type="hidden" name="tempDate1" id="tempDate1" value="${startDate}">
		<input type="hidden" name="tempDate2" id="tempDate2" value="${endDate}">
		<table width="100%" border="0" cellspacing="1" cellpadding="3"
			class="table1">
			<tr class="tr1">
				<td nowrap colspan="12"><%=resource.srcStr("System.query")%>：</td>
			</tr>
			<tr class="tr3">
				<td nowrap>资源名称：</td>
				<td nowrap><input type="text" name="mediaName" style="width:130px;" value="${mediaName}"
					maxlength="16" /></td>
				<td nowrap>起始日期:</td>
				<td><input type="text" id="startDate" name="startDate"
					maxlength="20" value="<c:out value='${startDate}'/>" readonly
					onPropertyChange="javascript:checkStartDate(this)"> <a
					onclick="event.cancelBubble=true;"
					href="javascript:showCalendar('dimg1',true,'startDate')"> <img
						id="dimg1" align="absmiddle" src="../images/calendar.png"
						border="0"> </a></td>
				<td nowrap>结束日期:</td>
				<td>
					<div style="float:left">
						<input type="text" id="endDate" name="endDate" maxlength="20"
							value="<c:out value='${endDate}'/>" readonly
							onPropertyChange="javascript:checkEndDate(this)"> <a
							onclick="event.cancelBubble=true;"
							href="javascript:showCalendar('dimg2',true,'endDate')"> <img
							id="dimg2" align="absmiddle" src="../images/calendar.png"
							border="0"> </a>
					</div></td>
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

	<display:table name="requestScope.list" id="list"
		requestURI="media.do" cellspacing="1" cellpadding="3" class="table1"
		style="word-break:break-all" pagesize="15">
		<display:column title='<%=resource.srcStr("Main.op")%>'>
		    <a href="javascript:mod('<c:out value="${list.id}"/>')"><%=resource.srcStr("System.mod")%></a>&nbsp;
        	<a href="javascript:del('<c:out value="${list.id}"/>')"><%=resource.srcStr("Main.delete")%></a>
        	<c:if test="${list.mediaType eq 'image'}">
        		<a href="javascript:previewImage('<c:out value="${list.id}"/>')">预览</a>
        	</c:if>
		</display:column>
		<display:column title='名称'>
				<c:out value="${list.mediaName}" />
		</display:column>
		<display:column title='类型'>
			<c:if test="${list.mediaType eq 'image'}">图片</c:if>
			<c:if test="${list.mediaType eq 'voice'}">声音</c:if>
			<c:if test="${list.mediaType eq 'video'}">视频</c:if>
		</display:column>
		<display:column title='描述'>
				<c:out value="${list.describe}" />
		</display:column>
		<display:column title='更新时间'>
				<c:out value="${list.createDate} ${list.createTime} " /> 
		</display:column>
		<display:column title='创建者'>
				<c:out value="${list.creator}" /> 
		</display:column>
	</display:table>

</body>
</html>





