<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
<meta http-equiv="Content-Type" content="text/html;charset=gbk" />
<title></title>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/calendar.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>

<script type='text/javascript' src='../dwr/interface/auditService.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>

<link href="../styles/common.css" rel="stylesheet" type="text/css">
<script language="JavaScript">

function listMethod(data){	
	DWRUtil.removeAllOptions($("operate"));
	DWRUtil.addOptions($("operate"),[{name:'------请选择------',no:''}],"no","name");
	for(i=0;i<data.length;i++){
		DWRUtil.addOptions($("operate"),[{name:data[i].name,no:data[i].no}],"no","name");
	}
}

function qryMethod(){
	if ($("module")) {
		auditService.qryOperateByModule($("module").value,listMethod);
	}
}

function checkForm(){
	form.submit();
}


</script>
</head>

<body onload="qryMethod();">
<form name="form" method="post" action="operateAudit.do?action=qry_AuditList">		
<input type="hidden" name="flag" value="<%=request.getParameter("flag") %>">
<input type="hidden" name="userId" value="<c:out value="${userSession.account}" />">
		<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
			 <tr>
			  <td nowrap><b>操作审核:</b></td>
			 </tr>
			  
		</table>
		<br>
		<table width="650" align="center" border="0" cellspacing="1" cellpadding="3" class="table1">
							<tr class="tr1">
							    <td align="right">所属模块：</td>
									<td class="tr_bg2">
											<select name="module" class="inputline" style="width:153px" onChange='javascript:qryMethod()'>
													<option value="">
													-----所有模块-----
													</option>
													<c:forEach var="sysModule" items="${moduleList}">
													<option value='<c:out value="${sysModule.no}"/>'>
														<c:out value="${sysModule.name}" />
														</option>
													</c:forEach>
											</select>
									</td>
									
							</tr>							
							<tr class="tr1">
										<td align="right">操作名称：</td>
										<td class="tr_bg2">
												<select name="operate" class="inputline" style="width:153px">
													<option value="">
													-----所有操作-----
													</option>
												</select>
										</td>
							</tr>
							<tr class="tr1">
									<td align="right" class="tr_bg1">
										起始时间：
									</td>
									<td class="tr_bg2">
	    								<input type="text" class="pane" name="startDate" id="startDate" style="width:100px;" value="<%=com.weili.wechat.common.CalendarUtil.getSysTimeYMD()%>"><a onclick="event.cancelBubble=true;" href="javascript:showCalendar('dimg3',true,'startDate')"><img id="dimg3" align="absmiddle" src="../images/calendar.gif" border="0"></a>
									</td>
							</tr>
							<tr class="tr1">
									<td align="right" class="tr_bg1">
										起始时间：
									</td>
									<td class="tr_bg2">
	    								<input type="text" class="pane" name="endDate" id="endDate" style="width:100px;" value="<%=com.weili.wechat.common.CalendarUtil.getSysTimeYMD()%>"><a onclick="event.cancelBubble=true;" href="javascript:showCalendar('dimg3',true,'endDate')"><img id="dimg3" align="absmiddle" src="../images/calendar.gif" border="0"></a>
									</td>
							</tr>

	
		</table>
		<p align="center">
      		<input type="button" class="button" style="width:90px;" value="操作审核查询" onclick="return checkForm()">&nbsp;
      	</p>		
		
</form>
</body>