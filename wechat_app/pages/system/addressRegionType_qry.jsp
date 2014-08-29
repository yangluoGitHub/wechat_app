<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%>
<%@ page import="com.weili.wechat.common.UserSession"%>
<%
Resource resource = (Resource) GetResource.getOneResource(application, session.getAttribute("locale").toString());
UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=gbk" />
		<title>�豸�����ѯ</title>
		<script type='text/javascript' src='../dwr/interface/devBaseInfoService.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
		<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
		<link href="../styles/common.css" rel="stylesheet" type="text/css">
		<link href="../styles/displaytag.css" rel="stylesheet" type="text/css">
		<link href="../styles/jquery-ui.css" rel="stylesheet"type="text/css"/>
  		<script src="../scripts/jquery-ui.min.js"></script>
  		<script language="JavaScript" src="../scripts/json2.js" type="text/javascript"></script>
	</head>


<script language=JavaScript>
$().ready(function(){

});
//��ѯ��������
function restore(){
	$('#districtNo').add($('#districtName')).val("");
}

function add(){
	window.location="addressRegionType.do?action=intoAddPage";
}

function mod(districtNo){
	window.location="addressRegionType.do?action=intoModPage&districtNo="+districtNo;
}

function del(districtNo){
	if (window.confirm("ȷ��ɾ����")) {
    	location.href = "addressRegionType.do?action=del&districtNo="+districtNo;
	}
}
function delComplete(districtNo){
	if (window.confirm("ȷ��ɾ����")) {
    	location.href = "addressRegionType.do?action=delComplete&districtNo="+districtNo;
	}
}

function go(){
	var pageNum = document.getElementById('curPages').value;
	//alert(pageNum);//��ȡ��תҳ��
	var obj=document.getElementsByTagName("a");
	var herf=new String("");
	var k = obj.length - 1 ;
    var ahref=obj[k]; //��ȡҳ�������һ�����ӵ�ַ
    var _ahref=obj[k-1]; //��ȡҳ���ϵ����ڶ������ӵ�ַ
    var change = 0;//��ĩҳ��ַ������ֵ=0
    herf=""+ahref;
    _herf=""+_ahref;
    //alert(herf);
    var m=herf.split("-p=");
    var k=_herf.split("-p=");
   	if(k.length>1){
   		var j = k[1].split("&");
   		if(parseInt(j[0])==1){
   			change = 1;//�������ڶ�����������ҳʱ������ֵ=1
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
    herf = herf.replace(/&d-(\d+)-p=(\d+)/g,"&d-$1-p="+pageNum);//�õ���תҳ��ַ
    //alert(herf);
    window.location=herf;//ת����תҳ
}
</script>

	<body>

		<!--javascript ���ʻ���ʶ��-->
		<div id="javascriptI18n">
			<input type="hidden" id="Main.pageLowerLimitPrompt" value='<%=resource.srcStr("Main.pageLowerLimitPrompt")%>' />
			<input type="hidden" id="Main.qryEmptyPrompt" value='<%=resource.srcStr("Main.qryEmptyPrompt")%>' />
			<input type="hidden" id="Main.input_correct_page" value='<%=resource.srcStr("Main.input_correct_page")%>' />
			<input type="hidden" id="Main.pageUpperLimitPrompt" value='<%=resource.srcStr("Main.pageUpperLimitPrompt")%>' />
		</div>
		
		<!--title-->
		<table width="100%" style="border: 0px; border-bottom: 1px solid #cccccc;">
			<tr>
				<td nowrap align="left"><b>�豸������Ϣ��</b></td>
				<td nowrap align="right">
		             <input class="button" type="button" value='�� ��' style="width: 6em" onclick="add()">
		        </td>
			</tr>
		</table>
		
		<br>

		<!-- ��ѯ����  -->
		<form name="form" method="post" action="addressRegionType.do?action=qry">
			<table id="qryTable" width="100%" border="0" cellspacing="1" cellpadding="3" class="table1">
				<tr class="tr1"><td nowrap colspan="5"><%=resource.srcStr("System.query")%>��</td></tr>
				<tr class="tr3">
					<td nowrap>�����ţ�</td>
					<td><input type="text" name="districtNo" id="districtNo" value="${districtNo}" maxlength="32"/></td>
					<td nowrap>�������ƣ�</td>
					<td><input type="text" name="districtName" id="districtName" value="${districtName}" maxlength="20"/></td>
					<td nowrap align="center">
						<input type="submit" class="button" value='<%=resource.srcStr("Main.query")%>'>&nbsp;&nbsp;
						<input type="button" class="button" value='<%=resource.srcStr("Main.reset")%>' onclick="restore()">
					</td>
				</tr>
			</table>
		</form>
		<br>

		<!-- ��ѯ��� -->
		<table width="100%" border="0" cellspacing="0" cellpadding="3" class="table1" style="white-space: nowrap">
			<tr class="tr1"><td nowrap colspan="3"><%=resource.srcStr("System.results")%>��</td></tr>
		</table>
		<display:table name="requestScope.resultList" id="item" requestURI="addressRegionType.do" cellspacing="1" cellpadding="3" class="table1" style="word-break:break-all" pagesize="15">
			<display:column title='����'>
				<a href="javaScript:mod('${item.no}')">�޸�</a>
    			<a href="javaScript:del('${item.no}')">ɾ��</a> 
    			<!-- <a href="javaScript:delComplete('${item.no}')">ǿ��ɾ��</a> --> 
			</display:column>
			<display:column title='������'>
				<c:out value="${item.no}"/>
			</display:column>
			<display:column title='��������'>
				<c:out value="${item.name}"/>
			</display:column>
		</display:table>

	</body>
</html>
