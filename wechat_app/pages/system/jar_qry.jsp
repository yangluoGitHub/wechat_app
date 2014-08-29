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
<script language="JavaScript" src="../scripts/orgSelector.js"
	type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
<link href="../styles/displaytag.css" rel="stylesheet" type="text/css">

<style type="text/css">
</style>
</head>

<body>
	<!--javascript ���ʻ���ʶ��-->
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
	    window.location="jarUp.do?action=del&id="+obj;
	  } 
}
function mod(obj){
	//alert("1233333");  
	// window.location="jarUp.do?action=modPage&id="+obj; 
}
//�����ʼʱ��Ϸ���
 function checkStartDate(checkedDate) {
	if (!check2Date(checkedDate.value, getDateStr())) {
		checkedDate.value = j$("#tempDate1").val();
		alert("��ѯ��ʼ���ڲ������ڵ�ǰ����!");
		return false;
	} 
	
	if(!isNull(j$("#endDate").val())) {
		if (!check2Date(checkedDate.value, j$("#endDate").val())) {
			checkedDate.value = j$("#tempDate1").val();
			alert("��ѯ��ʼ���ڲ������ڽ�������!");
			return false;
		}
	}
	j$("#tempDate1").val(checkedDate.value);
}

//������ʱ��Ϸ���
function checkEndDate(checkedDate) {
	/* if (!check2Date(checkedDate.value, getDateStr())) {
		checkedDate.value = j$("#tempDate2").val();
		alert("��ѯ�������ڲ������ڵ�ǰ����!");
		return false;
	} */
	if(!isNull(j$("#startDate").val())) {
		if (!check2Date(j$("#startDate").val(), checkedDate.value)) {
			checkedDate.value = j$("#tempDate2").val();
			alert("��ѯ�������ڲ������ڿ�ʼ����!");
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
			<td nowrap><b>jar������</b>
			</td>
			<td nowrap align="right"><input class="button" type="button"
				value='�ϴ�Jar��'
				onclick="javascript:window.location='jarUp.do?action=addPage'"
				style="width:110px;">&nbsp;</td>
		</tr>
	</table>
	<br>
	<form name="form1" method="post" action="jarUp.do?action=qry">
		<input type="hidden" name="tempDate1" id="tempDate1" value="${startDate}">
		<input type="hidden" name="tempDate2" id="tempDate2" value="${endDate}">
		<table width="100%" border="0" cellspacing="1" cellpadding="3"
			class="table1">
			<tr class="tr1">
				<td nowrap colspan="12"><%=resource.srcStr("System.query")%>��</td>
			</tr>
			<tr class="tr3">
				<td nowrap>jar�����ƣ�</td>
				<td nowrap><input type="text" name="name" style="width:130px;"
					maxlength="16" /></td>
				<td nowrap>��ʼ����:</td>
				<td><input type="text" id="startDate" name="startDate"
					maxlength="20" value="<c:out value='${startDate}'/>" readonly
					onPropertyChange="javascript:checkStartDate(this)"> <a
					onclick="event.cancelBubble=true;"
					href="javascript:showCalendar('dimg1',true,'startDate')"> <img
						id="dimg1" align="absmiddle" src="../images/calendar.png"
						border="0"> </a></td>
				<td nowrap>��������:</td>
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
				<td nowrap class="tr4" colspan="10" align="center"><input
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
			<td nowrap colspan="8">�ϴ���¼��ѯ��&nbsp;&nbsp;&nbsp;</td>
		</tr>
	</table>

	<display:table name="requestScope.jarList" id="jarList"
		requestURI="jarUp.do" cellspacing="1" cellpadding="3" class="table1"
		style="word-break:break-all" pagesize="15">
		<display:column title='<%=resource.srcStr("Main.op")%>'>
		<%-- <a href="javascript:mod('<c:out value="${jarList.id}"/>')"><%=resource.srcStr("System.mod")%></a>&nbsp; --%>
        	<a href="javascript:del('<c:out value="${jarList.id}"/>')"><%=resource.srcStr("Main.delete")%></a>
		</display:column>
		<display:column title='jar������'>
			<span
				style="align:center;width:100;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">
				<c:out value="${jarList.name}" /> </span>
		</display:column>
		<display:column title='�ϴ�ʱ��'>
			<span
				style="align:center;width:200;white-space:nowrap;overflow:hidden;text-overflow:clip">
				<c:out value="${jarList.upDate}" /> </span>
		</display:column>
		<display:column title='�ϴ���'>
			<span
				style="align:center;width:100;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">
				<c:out value="${jarList.upSender}" /> </span>
		</display:column>

	</display:table>

</body>
</html>





