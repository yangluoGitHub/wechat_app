<%@ page
	import="com.weili.wechat.common.Resource,com.weili.wechat.common.UserSession"%>
<%@ page import="com.weili.wechat.common.GetResource"%>
<% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
	<meta http-equiv="Content-Type" content="text/html;charset=gbk" />
	<title></title>
	<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
	<script language="JavaScript" src="../scripts/calendar.js" type="text/javascript"></script>
	<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
	<script language="JavaScript" src="../scripts/orgSelector.js" type="text/javascript"></script>

	<script type='text/javascript' src='../dwr/interface/logAuditService.js'></script>
	<script type='text/javascript' src='../dwr/engine.js'></script>
	<script type='text/javascript' src='../dwr/util.js'></script>

	<link href="../styles/common.css" rel="stylesheet" type="text/css">
	<script language="JavaScript">

function listMethod(data){	
	DWRUtil.removeAllOptions($("operate"));
	DWRUtil.addOptions($("operate"),[{name:"------"+document.getElementById('Main.select').value+"------",no:''}],"no","name");
	for(i=0;i<data.length;i++){
		DWRUtil.addOptions($("operate"),[{name:data[i].name,no:data[i].no}],"no","name");
	}
}

function qryMethod(){
	if ($("module")) {
		logAuditService.qryOperateByModule($("module").value,listMethod);
	}
}


function listUser(data){	
	DWRUtil.removeAllOptions($("userId"));
	DWRUtil.addOptions($("userId"),[{name:"------"+document.getElementById('Main.select').value+"------",no:''}],"no","name");
	for(i=0;i<data.length;i++){
		DWRUtil.addOptions($("userId"),[{name:data[i].name,no:data[i].no}],"no","name");
	}
}

function qryUser(){
	if (isNull($("_orgNo").value)) {
		 logAuditService.qryAllUserByRole('<%=((UserSession)request.getSession().getAttribute("userSession")).getOrgNo()%>', listUser);
	} else {
	     logAuditService.qryAllUserByRole($("_orgNo").value,listUser);
	}
}

function checkForm(){
 // if(isNull($F('_orgNo'))){
 //   alert("请选择机构");
 //   return false;
//  }
 // if(isNull($F('userId'))){
 //   alert("请选择用户");
 //   $("userId").focus();
 //   return false;
 // }  
  
  if($F('startDate')>$F('endDate')){
    alert(document.getElementById('System.start_less_end').value);
    return false;
  }  
	form.submit();
}

function setOrg(obj1,obj2){
	$("orgFrame").style.display="none";    
  	$('_orgNo').value=obj1;
  	$('_orgName').value=obj2;
  	qryUser();
}
function myrefresh(){
     if($("tmpfirst").value==1){
	  	 $("tmpfirst").value=0;
	 }
 } 
</script>
</head>

<body onload="qryMethod();qryUser()">
	<!--javascript 国际化标识符-->
	<div id="javascriptI18n">
		<%@ include file="../../scripts/common.jsp"%>
		<%@ include file="../../scripts/orgSelector.jsp"%>
		<input type="hidden" id="Main.select"
			value='<%=resource.srcStr("Main.select")%>' />
		<input type="hidden" id="System.start_less_end"
			value='<%=resource.srcStr("System.start_less_end")%>' />
		<input type="hidden" id="orgId" value="${orgId}" />
	</div>

	<form name="form" method="post" action="logAudit.do?action=qry_LogList">
		<input type="hidden" name="tmpfirst" value="0">
		<table width="100%"
			style="border: 0px; border-bottom: 1px solid #cccccc">
			<tr>
				<td nowrap>
					<b><%=resource.srcStr("System.management_op_log")%></b>
				</td>
			</tr>
		</table>
		<br>
		<table align="center" width="550" border="0" cellspacing="1"
			cellpadding="5" class="table1">
			<tr class="tr1">
				<td colspan="2" height="30" class="" align="center" nowrap>
					<b><%=resource.srcStr("System.management_op_log")%></b>
				</td>
			</tr>
			<c:choose>
				<c:when test="${flag ==1}">
					<tr class="tr3">
						<td align="right"><%=resource.srcStr("Main.own_org") %>：
						</td>
						<td>
							<input type="hidden" class="pane" name="upper1" id="_orgNo">
							<input type="text" class="paneGray" name="_orgName" id="_orgName" readonly style="width: 10em;">
							<a onclick="event.cancelBubble=true;" href="javascript:showOrgSelector('dimg','_orgNo','_orgName');">
							<img id="dimg" align="absmiddle" src="../images/org.gif" border="0"> </a>
						</td>
					</tr>
					<tr class="tr3">
						<td align="right"><%=resource.srcStr("Login.account") %>：
						</td>
						<td>
							<select name="userId" class="inputline" style="width: 153px">
								<option value="">
									------<%=resource.srcStr("Main.select")%>------
								</option>
							</select>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<input type="hidden" name="_orgNo"
						value="<c:out value="${userSession.orgNo}" />">
					<input type="hidden" name="upper1">
					<input type="hidden" name="userId"
						value="<c:out value="${userSession.account}" />">
				</c:otherwise>
			</c:choose>
			<tr class="tr3">
				<td align="right"><%=resource.srcStr("System.own_op") %>：
				</td>
				<td>
					<select name="module" class="inputline" style="width: 153px"
						onChange='javascript:qryMethod()'>
						<option value="">
							-----<%=resource.srcStr("fault.faultFilter.allModule")%>-----
						</option>
						<c:forEach var="sysModule" items="${moduleList}">
							<option value='<c:out value="${sysModule.no}"/>'>
								<c:out value="${sysModule.name}" />
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr class="tr3">
				<td align="right"><%=resource.srcStr("System.op_name") %>：
				</td>
				<td>
					<select name="operate" class="inputline" style="width: 153px">
						<option value="">
							-----<%=resource.srcStr("System.all_op")%>-----
						</option>
					</select>
				</td>
			</tr>
			<tr class="tr3">
				<td align="right">
					<%=resource.srcStr("Main.start_time") %>：
				</td>
				<td>
					<input type="text" class="pane" name="startDate" id="startDate"
						style="width: 120px;"
						value='<%=com.weili.wechat.common.CalendarUtil.getSysTimeYMD()%>'
						readonly>
					<a onclick="event.cancelBubble=true;"
						href="javascript:showCalendar('dimg2',true,'startDate')"><img
							id="dimg2" align="absmiddle" src="../images/calendar.gif"
							border="0">
					</a>
				</td>
			</tr>
			<tr class="tr3">
				<td align="right">
					<%=resource.srcStr("Main.end_time") %>：
				</td>
				<td>
					<input type="text" class="pane" name="endDate" id="endDate"
						style="width: 120px;"
						value='<%=com.weili.wechat.common.CalendarUtil.getSysTimeYMD()%>'
						readonly>
					<a onclick="event.cancelBubble=true;"
						href="javascript:showCalendar('dimg3',true,'endDate')"><img
							id="dimg3" align="absmiddle" src="../images/calendar.gif"
							border="0">
					</a>
				</td>
			</tr>
			<tr class="tr3">
				<td align="center" colspan="2">
					<input type="button" class="button" style="width: 90px;"
						value='<%=resource.srcStr("System.query_log")%>'
						onclick="return checkForm()">
					&nbsp;
				</td>
			</tr>
		</table>

	</form>
</body>
