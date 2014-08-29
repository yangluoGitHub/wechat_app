<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource,java.util.Set"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=gbk" />
<title></title>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/calendar.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/orgSelector.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
<link href="../styles/displaytag.css" rel="stylesheet" type="text/css">

<style type="text/css">

</style>
</head>

<body>
<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="Main.sure_delete" value='<%=resource.srcStr("Main.sure_delete")%>'/>
<input type="hidden" id="Login.op_no_per" value='<%=resource.srcStr("Login.op_no_per")%>'/>
</div>
<script language="JavaScript">
var j$ = jQuery.noConflict();
j$().ready(function(){
	
});

//增加一条记录
function add(){
  	form1.action="imageText.do?action=addPage";
    form1.submit();
}

function mod(obj){
	location.href = "imageText.do?action=modPage&id="+obj;
}

function del(obj){
	if(confirm("确认删除？")){
		location.href = "imageText.do?action=del&id="+obj;
	}
}

function set(obj){
	location.href = "imageText.do?action=set&id="+obj;
}

function preview(obj){
}

//进入详细信息页面
function detail(obj){
	form1.action="imageText.do?action=detail&id="+obj;
	form1.submit();
}

function restore(){
	window.location="imageText.do?action=qry";
}

function checkForm(){
	form1.submit();
}

//检查起始时间合法性
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
	if (!check2Date(checkedDate.value, getDateStr())) {
		checkedDate.value = j$("#tempDate2").val();
		alert("查询结束日期不得晚于当前日期!");
		return false;
	}
	
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

<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
 <tr>
  <td nowrap><b>图文消息查询:</b></td>
  <td nowrap align="right">
      <input class="button" type="button" value='<%=resource.srcStr("System.add")%>图文' onclick="javascript:add()" style="width:100px;">&nbsp;
   </td>
 </tr> 
</table>
<br>

<form name="form1" method="post" action="imageText.do?action=qry">
<input type="hidden" name="tempDate1" id="tempDate1" value="${startDate}">
<input type="hidden" name="tempDate2" id="tempDate2" value="${endDate}">

<table width="100%" border="0" cellspacing="1" cellpadding="3" class="table1">
  <tr class="tr1">
  	<td colspan="10"><%=resource.srcStr("System.query")%>:</td>
  </tr>
  <tr class="tr3">
  	<td nowrap>标题:</td>
    <td>
    	<input type="text" id="title" name="title" value="<c:out value="${title}"/>" maxlength="20">
	</td>
    <td nowrap>起始日期:</td>
    <td>
    	<input type="text" id="startDate" name="startDate" maxlength="20" value="<c:out value='${startDate}'/>" readonly onPropertyChange="javascript:checkStartDate(this)">
		<a onclick="event.cancelBubble=true;" href="javascript:showCalendar('dimg1',true,'startDate')">
		<img id="dimg1" align="absmiddle" src="../images/calendar.png" border="0"></a>
	</td>
	<td nowrap>结束日期:</td>
    <td>
		<div style="float:left">
    		<input type="text" id="endDate" name="endDate" maxlength="20" value="<c:out value='${endDate}'/>" readonly onPropertyChange="javascript:checkEndDate(this)">
				<a onclick="event.cancelBubble=true;" href="javascript:showCalendar('dimg2',true,'endDate')">
			<img id="dimg2" align="absmiddle" src="../images/calendar.png" border="0"></a>
        </div>	
	</td>
  </tr>
  <tr class="tr3" align="center">
    <td nowrap colspan="6">
       <input class="button" type="button" value='<%=resource.srcStr("Main.query")%>' onClick="checkForm()">&nbsp;
       <input class="button" type="button" value='<%=resource.srcStr("Main.reset")%>' onclick="restore()">&nbsp;
    </td>
  </tr>
</table>
</form>

<table width="100%" border="0" cellspacing="0" cellpadding="3" class="table1" style="word-break:break-all">
  <tr class="tr1">
    <td nowrap colspan="9">
      <%=resource.srcStr("System.results") %>：&nbsp;&nbsp;&nbsp;
    </td>
  </tr>
</table>
<display:table name="requestScope.list" id="item" sort="list" requestURI="imageText.do" cellspacing="1" cellpadding="3" class="table1" style="word-break:break-all" pagesize="15">
    <display:column title='<%=resource.srcStr("Main.op")%>' >
		<%-- <a href="javascript:preview('<c:out value="${item.id}"/>')">预览</a>&nbsp; --%>
        <a href="javascript:mod('<c:out value="${item.id}"/>')"><%=resource.srcStr("System.mod")%></a>&nbsp;
        <a href="javascript:del('<c:out value="${item.id}"/>')"><%=resource.srcStr("Main.delete")%></a>&nbsp;
	    <c:if test="${!empty item.multiresourceId}"><a href="javascript:set('<c:out value="${item.id}"/>')">配置业务功能</a></c:if>&nbsp;        
    </display:column>
    <display:column title='标题'>
    	<a href="javascript:detail('<c:out value="${item.id}"/>')"><c:out value="${item.resourceTittle}"/></a>
    </display:column>
    <display:column title='更新时间'>
    	<c:out value="${item.createDate} ${item.createTime}"/>
    </display:column>
     <display:column title='类型'>
    	<c:if test="${!empty item.multiresourceId}">多图文</c:if>
    	<c:if test="${empty item.multiresourceId}">单图文</c:if>
    </display:column>
	
</display:table>

</body>
</html>
