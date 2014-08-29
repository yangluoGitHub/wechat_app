<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/messageService.js'></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
<link href="../styles/displaytag.css" rel="stylesheet" type="text/css">
</head>

<body>
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
</div>

<script language="JavaScript">
var j$ = jQuery.noConflict();
function sendMessage(){
	var msg = j$("#sendMsg").text();
	DWREngine.setAsync(false);
	var openId = '${openId}';
	var wechatId = '${sessionScope.userSession.wechatId}';
	DWREngine.setAsync(false);
	messageService.sendMessage(wechatId, openId, msg, function(data){
		if(data == null || data.length == 0) {
			alert("ͨ��ʧ��");
			return;
		} 
		if(data[0] == "success"){
			alert("���ͳɹ�");
			var htmlTr = "<tr><td align='center'>"+wechatId+"</td><td align='center'>text</td><td align='center'>"+msg+"</td><td align='center'>"+data[1]+"</td></tr>"
			var tr = j$("#messageTable tr").eq(0);
			j$(tr).after(htmlTr);
			j$("#sendMsg").text("");
		}else{
			alert(data[1]);
		}
	});
	DWREngine.setAsync(true);
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
		    herf = herf.replace(/d-(\d+)-p=(\d+)/g,"d-$1-p="+pageNum);//�õ���תҳ��ַ
		    //alert(herf);
		    window.location=herf;//ת����תҳ
	}
</script>
<br>

<table id="sendTable" align="center" width="450" border="0" cellspacing="1" cellpadding="5" class="table1" >
	<tr class="tr1">
    	<td nowrap align="center">�롾${openId}��������</td>
	</tr>  
	<tr class="tr3">
		<td align="center"><textarea rows="5" cols="100" id="sendMsg" name="sendMsg"></textarea></td> 
	</tr>
	<tr class="tr3">
		<td align="center"><input class="button" type="button" value="����" onclick="javascript:sendMessage()" > </td>
	</tr>
</table>

<display:table htmlId="messageTable" name="requestScope.msgList" id="list" requestURI="message.do" cellspacing="1" cellpadding="3" class="table1"
		style="word-break:break-all" pagesize="15">
	<display:column title='������'>
		<c:if test="${list.messageMode == 0}">
			<c:out value="${list.openId}"/>
		</c:if>
		<c:if test="${list.messageMode == 1}">
			<c:out value="${list.wechatId}"/>
		</c:if>
	</display:column>
	<display:column title='��Ϣ����'>
		<c:out value="${list.messageType}" />
	</display:column>
	<display:column title='����'>
		<c:out value="${list.content}" />
	</display:column>
	<display:column title='ʱ��'>
		<c:out value="${list.createDate} ${list.createTime} " /> 
	</display:column>
</display:table>
</body>
</html>




