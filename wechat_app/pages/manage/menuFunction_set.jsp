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
<title>业务功能配置</title>

<script language="JavaScript" src="../scripts/jquery.js"
	type="text/javascript"></script>
	<script type="text/javascript" src="../scripts/jquery.json.js"></script>
<script type="text/javascript" src="../scripts/jquery-ui.js"></script>
<script language="JavaScript" src="../scripts/common.js"
	type="text/javascript"></script>


<link href="../styles/common.css" rel="stylesheet" type="text/css">
<link href="../styles/displaytag.css" rel="stylesheet" type="text/css">

<style type="text/css">
	.graybutton{
			background-color: #C0C0C0;
			text-align: center;
			height: 25px;
			width: 60px;
			font-family: 微软雅黑;
			font-size: 12px;
			color: white;
			border: 0px;
		}
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
	$().ready(function(){
	//更改背景色
	$("#item").rowHilight();
	
	//已审批的明细前的checkbox checked & disabled
	$(".auditStatus").each(
			function() {
				//alert(j$(this).val());
				if ($(this).val() == 1) {
					//j$(this).parents("tr:first").find(":checkbox")
						//	.attr("disabled", true).attr("checked",true);
					$(this).parents("tr:first").find(":checkbox")
					.attr("checked",true);
				}
			});

	//全选
	$("#checkAllButton").click(
			function() {
				$("#item :checkbox").filter(":enabled").not(
						":checked").attr("checked", true);

				var checkedbox2 = $("#item :checkbox").filter(
						":enabled").filter(":checked");
				if (checkedbox2.length == 0) {
					$("#auditButton").attr("disabled", true).attr("class","graybutton");
				} else {
					$("#auditButton").attr("disabled", false).attr("class","button");
				}

			});

	//反选
	$("#checkRevButton").click(
			function() {
				var checkedbox = $("#item :checkbox").filter(
						":enabled").filter(":checked");
				var unCheckedbox = $("#item :checkbox").filter(
						":enabled").not(":checked");
				unCheckedbox.attr("checked", true);
				checkedbox.attr("checked", false);

				var checkedbox2 = $("#item :checkbox").filter(
						":enabled").filter(":checked");
				if (checkedbox2.length == 0) {
					$("#auditButton").attr("disabled", true).attr("class","graybutton");
				} else {
					$("#auditButton").attr("disabled", false).attr("class","button");
				}
			});

	$("#item").on("click",":checkbox",
			function() {
				var checkedbox2 = $("#item :checkbox").filter(
						":enabled").filter(":checked");
				if (checkedbox2.length == 0) {
					$("#auditButton").attr("disabled", true).attr("class","graybutton");
				} else {
					$("#auditButton").attr("disabled", false).attr("class","button");
				}
			});
	
	/**
	
	$("#auditButton").click(function() {
		//var ret = window.confirm('确认发布菜单？');
		var ret = true;
		var func_data = "";
		
		var checkedbox = $("#item :checkbox").filter(
		":enabled").filter(":checked");
		for(var i = 0; i < checkedbox.length; i++){
			func_data = func_data + "|" +checkedbox[i].value;
		}
		postBody = func_data.length ? func_data.substring(1) : '';
		
		//alert(postBody);
		if (ret) {
			$.ajax({
				url : 'menuFunction.do?action=configureFunction',
				type : 'POST',
				data : {
					id : $("#wechatId").val(),
					data : postBody
				},
				success : function(respTxt) {
					if (respTxt == 'no_data')
						XIDialog.alert('您尚未编辑菜单内容。');
					else if (respTxt == 'error')
						window.alert('配置失败，请稍后再试！');
					else
						window.alert('配置成功！');
				}
			});
		}
		
		
		return false;
	});**/
});
	
function configureFunc(){
	var func_data = "";
	
	var checkedbox = $("#item :checkbox").filter(
	":enabled").filter(":checked");
	for(var i = 0; i < checkedbox.length; i++){
		func_data = func_data + "|" +checkedbox[i].value;
	}
	postBody = func_data.length ? func_data.substring(1) : '';
	var id = $("#wechatId").val();
	window.location="menuFunction.do?action=configureFunction&id="+id +"&data=" + postBody;
}



</script>
	
	<input type="hidden" id="wechatId" name="wechatId" value="${wechatId}"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="3"
			class="table1" style="word-break: break-all">
			<tr class="tr1">
				<td colspan="3">
					<b>业务功能配置：</b>
				</td>
			</tr>
			<tr class="tr3">
				<td nowrap colspan="3">
					<input type="button" id="checkAllButton" class="button" value="全选" />
					<input type="button" id="checkRevButton" class="button" value="反选" />
					&nbsp;|
					<input type="button" id="auditButton" class="graybutton" value="配置"
						disabled="disabled" onclick="javascript:configureFunc()"/>
				</td>
			</tr>
		</table>
	<form name="form1" method="post" action="">
		
	</form>
	<table width="100%" border="0" cellspacing="0" cellpadding="3"
		class="table1" style="word-break:break-all">
		<tr class="tr1">
			<td nowrap colspan="8">查询结果：&nbsp;&nbsp;&nbsp;</td>
		</tr>
	</table>

	<display:table name="requestScope.retList" id="item"
		requestURI="menuFunction.do" cellspacing="1" cellpadding="3" class="table1"
		style="word-break:break-all" pagesize="15">
		
		<display:column title='操作' class="tr3">
				<input type="checkbox" name="wechatFunc" value="${item.id}" />
		</display:column>
		<display:column title='配置' class="tr3">
					<input type="hidden" class="auditStatus" value="${item.selected}" />
					<c:if test="${item.selected==0}">
						<font color="red">未配置</font>
					</c:if>
					<c:if test="${item.selected==1}">
						<font color="green">已配置</font>
					</c:if>
				</display:column>
		<display:column title='业务功能名称'>
				<c:out value="${item.wechatletName}" />
		</display:column>
		<display:column title='描述'>
				<c:out value="${item.description}" />
		</display:column>
		<display:column title='处理类标识'>
				<c:out value="${item.classname}" /> 
		</display:column>
	</display:table>

</body>
</html>





