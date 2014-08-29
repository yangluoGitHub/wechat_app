<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%>
<% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page import="java.util.Date"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> 
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>

<head>
	<meta http-equiv="Content-Type" content="text/html;charset=gbk" />
	<title></title>
</head>
<script type='text/javascript' src='../dwr/interface/devTypeService.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>

<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/calendar3.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">

<body>
	<%@ include file="../../scripts/common.jsp"%>
<script language="JavaScript">
var j$ = jQuery.noConflict();

j$().ready(function(){
	var devTypeNo = j$("#devTypeNo").val();
	
//	devTypeService.qryDevTypeByNo(devTypeNo,function(data){
//		j$("#devTypeNo").val(data.name);
//	});
	
	devTypeService.qryDevVendorByNo(devTypeNo,function(data){
		j$(".devVendorTd").html(data.name);
	});
	devTypeService.qryDevCatalogByNo(devTypeNo,function(data){
		j$(".devCatalogTd").html(data.name);
	});
});
</script>



	<!-- 个人在jsp开发的过程中,css,字体,颜色不需要改变, 需要改变仅仅是table的width,为统一起见,查询页width=500,查询列表页width="750" -->
	<!-- action请求 -->
	<form name="form" method="post" action="">
		<input type="hidden" name="flag" value='<%=request.getParameter("flag") %>'>
		<input type="hidden" name="userId" value='<c:out value="${logvo.userId}"/>'>
		<input type="hidden" name="date" value='<c:out value="${logvo.date}"/>'>
		
		<table width="100%"
			style="border: 0px; border-bottom: 1px solid #cccccc">
			<tr>
				<td nowrap>
					<b><%=resource.srcStr("System.detail_info")%>:</b>
				</td>
			</tr>
		</table>
		<br>
		<c:if test="${fn:length(objectList) == 0 }" >
			<table align="center" width="650" border="0" cellspacing="1"
				cellpadding="5" class="table1">
				<tr class="tr1">
					<td nowrap colspan="2" align="center">
						<c:out value="${title}" />
					</td>
				</tr>
				<tr class="tr3">
					<td nowrap><%=resource.srcStr("Main.devType") %>：
					</td>
					<td nowrap>
						<input type="text" name="name" class="paneGray"
							value='<c:out value="${objectvo.name}"/>' readonly>
						&nbsp;
					</td>
				</tr>
				<tr class="tr3">
					<td nowrap><%=resource.srcStr("System.own_brand") %>：
					</td>
					<td nowrap>
						<input type="hidden" name="devCatalog" class="paneGray"
							value='<c:out value="${objectvo.devVendorTable.no}"/>' readonly>
						<c:out value="${objectvo.devVendorTable.name}"/>
					</td>
				</tr>
				<tr class="tr3">
					<td nowrap><%=resource.srcStr("System.own_type") %>：
					</td>
					<td nowrap>
						<input type="hidden" name="devCatalog" class="paneGray"
							value='<c:out value="${objectvo.devCatalogTable.no}"/>' readonly>
						<c:out value="${objectvo.devCatalogTable.name}"></c:out>
					</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${fn:length(objectList) != 0 }" >
			<table align="center" width="650" border="0" cellspacing="1"
				cellpadding="5" class="table1">
				<tr class="tr1">
					<td nowrap colspan="2" align="center">
						<c:out value="${title}" />
					</td>
				</tr>
				<tr class="tr3">
					<td nowrap><%=resource.srcStr("Main.devType") %>：
					</td>
					<td nowrap>
						<input type="text" name="devTypeNo" id="devTypeNo" class="paneGray" value='<c:out value="${objectvo}"/>' readonly>
						&nbsp;
					</td>
				</tr>
				<tr class="tr3">
					<td nowrap><%=resource.srcStr("System.own_brand") %>：
					</td>
					<td nowrap class="devVendorTd">
						<input type="hidden" name="devCatalog" class="paneGray"  readonly>
					</td>
				</tr>
				<tr class="tr3">
					<td nowrap><%=resource.srcStr("System.own_type") %>：
					</td>
					<td nowrap class="devCatalogTd">
						<input type="hidden" name="devCatalog" class="paneGray" ' readonly>
					</td>
				</tr>
			</table>
		</c:if>
		<br>
		<table width="100%">
			<tr>
				<td align="center">
					<c:if test="${flag==2}">
						<input class="button" type="button"
							value='<%=resource.srcStr("System.pass")%>'
							onclick="javascript:audit();">
						<input class="button" type="button"
							value='<%=resource.srcStr("System.refuse") %>'
							onclick="javascript:refuse();">
					</c:if>
					<input class="button" type="button"
						value='<%=resource.srcStr("Main.back")%>'
						onclick="javascript:window.history.back();">
				</td>
			</tr>
		</table>
	</form>
</body>

<!--javascript 国际化标识符-->
<div id="javascriptI18n">
</div>
