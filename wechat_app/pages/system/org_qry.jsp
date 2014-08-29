<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource,java.util.Set"%>
<% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<script type='text/javascript' src='../dwr/interface/orgService.js'></script>
<script type='text/javascript' src='../dwr/interface/clrCenterService.js'></script>
<script type='text/javascript' src='../dwr/interface/stationService.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>

<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
<link href="../styles/displaytag.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../scripts/orgSelector.js" type="text/javascript"></script>
</head>


<body onload="">
<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="Main.sure_delete" value='<%=resource.srcStr("Main.sure_delete")%>'/>
<input type="hidden" id="Main.pageLowerLimitPrompt" value='<%=resource.srcStr("Main.pageLowerLimitPrompt")%>'/>
<input type="hidden" id="Main.select" value='<%=resource.srcStr("Main.select")%>'/>
<input type="hidden" id="Main.qryEmptyPrompt" value='<%=resource.srcStr("Main.qryEmptyPrompt")%>'/>
<input type="hidden" id="Main.input_correct_page" value='<%=resource.srcStr("Main.input_correct_page")%>'/>
<input type="hidden" id="Main.pageUpperLimitPrompt" value='<%=resource.srcStr("Main.pageUpperLimitPrompt")%>'/>
<input type="hidden" id="Login.op_no_per" value='<%=resource.srcStr("Login.op_no_per")%>'/>
<input type="hidden" id="bankOrgNo" name="bankOrgNo"/>
</div>

<script language="JavaScript">
//增加一条记录
var j$ = jQuery.noConflict();
j$().ready(function(){
	j$("#org").rowHilight();
});
function add(){
    form1.action="org.do?action=addPage&orgType_Query="+$F('orgType_Query');
    form1.submit();
}

function del(orgNo,orgType,orgName,clrCenterNo){
	if($F('orgType_Query') ==1) {
	   <%  Set authButton = (Set)request.getSession().getAttribute("_Auth_Button");
	   if (!authButton.contains("org.do?action=del&orgType_Query=1") ) { %>
	   alert(document.getElementById('Login.op_no_per').value);return ;
	   <%  } %> 
	} else if($F('orgType_Query') ==2) {
	   <%  Set authButton1 = (Set)request.getSession().getAttribute("_Auth_Button");
	   if (!authButton1.contains("org.do?action=del&orgType_Query=2") ) { %>
	   alert(document.getElementById('Login.op_no_per').value);return ;
	   <%  } %> 
	}
  
  
	DWREngine.setAsync(false);
			form1.action = "org.do?action=del&orgType_Query=" + $F('orgType_Query') + "&no=" + orgNo
					+ "&orgType=" + orgType + "&orgName=" + orgName;
			form1.submit();

 	DWREngine.setAsync(true);
}
function mod(obj)
	{
		form1.action = "org.do?action=modPage&orgType_Query=" + $F('orgType_Query') + "&no=" + obj;
		form1.submit();
	}
function detail(obj1, obj2)
	{
		form1.action = "org.do?action=detail&no=" + obj1 + "&orgType=" + obj2;
		form1.submit();
	}

	//重置查询条件
function restore()
	{
		$('orgNo_Query').value = "";
		$('orgName_Query').value = "";
		$('orgGrade_Query').value = "";
		$('upper1Name').value = "";
		$('upper1No').value = "";
	}
function setOrg(obj1, obj2)
	{
		$("orgFrame").style.display = "none";
		$('upper1No').value = obj1;
		$('upper1Name').value = obj2;
	}

function listOrgGrade(data)
	{
		DWRUtil.removeAllOptions($("orgGrade_Query"));
		DWRUtil.addOptions($("orgGrade_Query"), [
		{
			name : "---" + document.getElementById('Main.select').value + "---",
			no : ''
		} ], "no", "name");
		for (i = 0; i < data.length; i++)
		{
			DWRUtil.addOptions($("orgGrade_Query"), [
			{
				name : data[i].name,
				no : data[i].no
			} ], "no", "name");
			if (form1.orgGrade.value == data[i].no)
			{
				form1.orgGrade_Query.selectedIndex = i + 1;
			}
		}
	}
function qryOrgGrade()
	{
		if ($("orgType_Query"))
		{
			orgService.qryOrgGrade($("orgType_Query").value, listOrgGrade);
		}
	}
function showOrg()
	{
		if ($("orgFrame").style.display == "block")
		{
			$("orgFrame").style.display = "none";
		}
		else
		{
			showOrgSelectorByOrgNo('dimg', j$("#bankOrgNo").val());
		}
	}
function go()
	{
		var pageNum = document.getElementById('curPages').value;
		//alert(pageNum);//获取跳转页码
		var obj = document.getElementsByTagName("a");
		var herf = new String("");
		var k = obj.length - 1;
		var ahref = obj[k]; //获取页面上最后一个链接地址
		var _ahref = obj[k - 1]; //获取页面上倒数第二个链接地址
		var change = 0;//对末页地址的修正值=0
		herf = "" + ahref;
		_herf = "" + _ahref;
		//alert(herf);
		var m = herf.split("-p=");
		var k = _herf.split("-p=");
		if (k.length > 1)
		{
			var j = k[1].split("&");
			if (parseInt(j[0]) == 1)
			{
				change = 1;//当倒数第二个链接是首页时，修正值=1
			}
		}
		if (m.length > 1)
		{
			var n = m[1].split("&");
			var totalNum = n[0];
			totalNum = parseInt(totalNum) + change;
			if (isNull(pageNum))
			{
				alert(document.getElementById('Main.qryEmptyPrompt').value);
				document.getElementById('curPages').select();
				return false;
			}
			else if (!isInteger(pageNum))
			{
				alert(document.getElementById('Main.input_correct_page').value);
				document.getElementById('curPages').select();
				return false;
			}
			else if (parseInt(pageNum) <= 0)
			{
				alert(document.getElementById('Main.pageLowerLimitPrompt').value);
				document.getElementById('curPages').select();
				return false;
			}
			else if (parseInt(pageNum) > parseInt(totalNum))
			{
				alert(document.getElementById('Main.pageUpperLimitPrompt').value);
				document.getElementById('curPages').select();
				return false;
			}
		}
		herf = herf.replace(/&d-(\d+)-p=(\d+)/g, "&d-$1-p=" + pageNum);//得到跳转页地址
		//alert(herf);
		window.location = herf;//转到跳转页
	}
</script>

<form name="form1" method="post" action="org.do?action=qry">

<%
	String orgType_Query=request.getParameter("orgType_Query")==null?"":request.getParameter("orgType_Query");
	request.setAttribute("orgType_Query",orgType_Query);
	String title = "";
	if(orgType_Query == null) 
		title=resource.srcStr("System.org_addrr");
	else if(orgType_Query.equals("1")){
		title=resource.srcStr("System.org_addrr");
	}else if(orgType_Query.equals("2")){
		title=resource.srcStr("System.devservice_com");
	}
%>

<input type="hidden" name="title" value='<%= title%>'>
<input type="hidden" name="orgType_Query" value='<%=orgType_Query %>'>
<input type="hidden" name="orgNo" value='<c:out value="${orgNo}"/>'>
<input type="hidden" name="orgGrade" value='<c:out value="${orgGrade}"/>'>

<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
 <tr>
  <td nowrap><b><%=title %><%=resource.srcStr("System.info")%>:</b></td>
    <td nowrap align="right">
  	 <input class="button" type="button" style="width:100px;" onClick="javascript:add()" value='<%=resource.srcStr("System.add")%><%=title %>'>&nbsp;
   </td>
 </tr> 
</table>
 <br>
<table width="100%" border="0" cellspacing="1" cellpadding="3" class="table1">		    
 <tr class="tr1">
  	<td colspan="10"><%=resource.srcStr("System.query")%>:</td>
  </tr>
  <tr class="tr3">
  	<td nowrap><%=resource.srcStr("System.org_no") %>：</td>
  	<td nowrap><input type="text" name="orgNo_Query" value='<c:out value="${orgNo}"/>' onkeyup="value=value.replace(/[\W]/g,'')" maxLength="20"></td>
	<td nowrap>机构名称：</td>
	<td nowrap><input type="text" name="orgName_Query" value='<c:out value="${orgName}"/>' maxLength="80"></td>
    <td nowrap>所属机构：</td>
  	<td nowrap colspan="3">
		<input type="hidden" class="pane" name="upper1No" id="upper1No" value="${upper1No }">
		<input type="text" class="paneGray" name="upper1Name" id="upper1Name" value="${upper1Name}" readonly style="width:120px;">
		<a onclick="event.cancelBubble=true;" href="javascript:showOrg();"><img id="dimg" align="absmiddle" src="../images/org.gif" border="0"></a>
	</td>
		<script language="JavaScript">
			qryOrgGrade();
		</script>
	<td nowrap><%=resource.srcStr("System.org_level") %>：</td>
	<td nowrap>
		<select name="orgGrade_Query" style="width:105px">
			<option value="">---<%=resource.srcStr("Main.select")%>---</option>    		
     	</select>&nbsp;&nbsp;  
     </td>
	</tr>
	<tr class="tr3">
    <td nowrap colspan="10" align="center">
		<input class="button" type="submit" value='<%=resource.srcStr("Main.query")%>'>
    	<input class="button" type="button" value='<%=resource.srcStr("Main.reset")%>' onclick="restore()">
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
<display:table name="requestScope.orgList" id="org" requestURI="org.do" cellspacing="1" cellpadding="3" class="table1" style="word-break:break-all" pagesize="15">
	<display:column title='<%=resource.srcStr("Main.op")%>' class="tr3">
			<a href="javascript:mod('<c:out value="${org.no}"/>')"><%=resource.srcStr("System.mod")%></a>&nbsp;
        	<a href="javascript:del('<c:out value="${org.no}"/>','<c:out value="${org.orgType.no}"/>','<c:out value="${org.name}"/>','<c:out value="${ org.clrCenterNo }"/>')">删除</a>      
    </display:column>    
	<display:column title='<%=resource.srcStr("System.org_name")%>' class="tr3">
			<a href="javascript:detail('<c:out value="${org.no}"/>','<c:out value="${orgType_Query}"/>')" ><c:out value="${org.name}"/><c:if test="${orgType_Query ==1}">(<c:out value="${org.no}"/>)</c:if>
  	</display:column>
	<display:column title='<%=resource.srcStr("System.org_level")%>' class="tr3"><c:out value="${org.orgGrade.name}"/></display:column>						    
	<display:column title='<%=resource.srcStr("System.org_addr")%>' class="tr3"><c:out value="${org.address}"/></display:column>
	<display:column title='<%=resource.srcStr("System.parent_org")%>' class="tr3"><c:out value="${org.superOrg.name}"/></display:column>
	<display:column title='机构状态' class="tr3" ><c:if test="${org.status==1}">启用</c:if><c:if test="${org.status==2}">停用</c:if></display:column>
</display:table>

</body>

</html>
