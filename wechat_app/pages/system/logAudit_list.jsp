<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page import="java.util.Date"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=gbk" />
<title></title>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/calendar3.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
<link href="../styles/displaytag.css" rel="stylesheet" type="text/css">
</head>

<SCRIPT language=JavaScript>
function excel()
{
	var winname = window.open('', '_blank', 'top=10000'); 
    var strHTML = document.all.report.innerHTML; 
    winname.document.open('text/html', 'replace'); 
    winname.document.writeln(strHTML); 
    winname.document.execCommand('saveas','',document.getElementById('System.credit_assessment').value+'.xls'); 
    winname.close();
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
    herf = herf.replace(/&d-(\d+)-p=(\d+)/g,"&d-$1-p="+pageNum);//得到跳转页地址
    //alert(herf);
    window.location=herf;//转到跳转页
}
</SCRIPT>



<!-- 个人在jsp开发的过程中,css,字体,颜色不需要改变, 需要改变仅仅是table的width,为统一起见,查询页width=500,查询列表页width="750" -->
<body>   
<!--javascript 国际化标识符-->
	<div id="javascriptI18n">
		<%@ include file="../../scripts/common.jsp" %>
		<input type="hidden" id="Main.pageLowerLimitPrompt" value='<%=resource.srcStr("Main.pageLowerLimitPrompt")%>'/>
		<input type="hidden" id="System.credit_assessment" value='<%=resource.srcStr("System.credit_assessment")%>'/>
		<input type="hidden" id="Main.qryEmptyPrompt" value='<%=resource.srcStr("Main.qryEmptyPrompt")%>'/>
		<input type="hidden" id="Main.input_correct_page" value='<%=resource.srcStr("Main.input_correct_page")%>'/>
		<input type="hidden" id="Main.pageUpperLimitPrompt" value='<%=resource.srcStr("Main.pageUpperLimitPrompt")%>'/>
	</div>
	<form name="form" method="post"
		action="logAudit.do?action=excel_LogList">
		<input type="hidden" name="upper1" value=<c:out value="${upper1}"/>>
		<input type="hidden" name="userId" value=<c:out value="${userId}"/>>
		<input type="hidden" name="module" value=<c:out value="${module}"/>>
		<input type="hidden" name="operate" value=<c:out value="${operate}"/>>
		<input type="hidden" name="startDate"
			value=<c:out value="${startDate}"/>> <input type="hidden"
			name="endDate" value=<c:out value="${endDate}"/>>
		<div id='report'>
			<table width="100%"
				style="border:0px;border-bottom:1px solid #cccccc">
				<tr>
					<td nowrap><b><%=resource.srcStr("System.log_info_list")%>:</b>
					</td>
				</tr>

			</table>

			<br>

			<table width="100%" border="0" cellspacing="0" cellpadding="3"
				class="table1">
				<tr class="tr1">
					<td nowrap colspan="8"><%=resource.srcStr("System.results")%>：&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
			</table>

			<display:table name="logAuditList" id="logAudit"
				requestURI="logAudit.do" cellspacing="1" cellpadding="3"
				class="table1" style="word-break:break-all" pagesize="15">
				<display:column title='<%=resource.srcStr("System.user_id")%>'>
					<c:out value="${logAudit.userId}" />
				</display:column>
				<display:column title='<%=resource.srcStr("Login.account") %>'>
					<c:out value="${logAudit.userName}" />
				</display:column>
				<display:column title='<%=resource.srcStr("Main.module")%>'>
					<c:out value="${logAudit.module.name}" />
				</display:column>
				<display:column title='<%=resource.srcStr("Main.op")%>'>
					<c:out value="${logAudit.operate.name}" />
				</display:column>
				<display:column title='<%=resource.srcStr("Main.op_time")%>'>
					<c:out value="${logAudit.date}" />
				</display:column>
<%-- 				<c:if
					test="${logAudit.module.no!='com.weili.wechat.service.login.impl.LoginServiceImpl'
						&& logAudit.module.no!='com.weili.wechat.service.office.impl.AddnotesLineServiceImpl'
						&& logAudit.module.no!='com.weili.wechat.service.circulate.impl.OutCheckServiceImpl'
						&& logAudit.module.no!='com.weili.wechat.service.circulate.impl.InCheckServiceImpl'
						&& logAudit.module.no!='com.weili.wechat.service.circulate.impl.VirtualSignServiceImpl'
						&& logAudit.module.no!='com.weili.wechat.service.circulate.impl.VirtualRetrieveServiceImpl'}">
					<display:column title='<%=resource.srcStr("System.detail_op_info")%>'>
						<a href="javascript:window.location='logAudit.do?action=qry_Detail&userId=<c:out value="${logAudit.userId}"/>&date=<c:out value="${logAudit.date}"/>'">
							<%=resource.srcStr("System.detail_op")%>
						</a>
					</display:column>
				</c:if>
 --%>			
 			</display:table>
		</div>

		<table width="100%">
			<tr>
				<td align="center"><input class="button" type="submit"
					value='<%=resource.srcStr("System.export")%>EXCEL'> <input
					class="button" type="button"
					value='<%=resource.srcStr("Main.back")%>'
					onclick="javascript:window.history.back();">
				</td>
			</tr>

		</table>

	</form>
</body>
</html>  	  



