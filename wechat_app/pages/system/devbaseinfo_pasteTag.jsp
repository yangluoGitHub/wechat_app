<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=gbk" />
<title>设备关联标签</title>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>

<script type="text/vbscript" src="../scripts/visitOcx.vbs"></script>

<link href="../styles/common.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function checkForm(){
	if(isNull($F('tagTid'))){
		alert("未关联任何标签!");
		return false;
	}
	form1.submit();
	return true;
}

function vbsCall(varEpc, varTid, varUerData){
	$('tagTid').value = varTid.replace(/[|]/g, '');
}

function readTagTid() {
	var commMode = $F('commMode');
	var ip = '10.2.7.242';
	var port = 50089;
	var comPort = 'COM1';
	if(commMode == 'TCP') {
		ip = $F('ip');
		port = $F('port');
	} else if(commMode == 'COM') {
		if(isNull($F('comPortNum'))) {
			alert("请填写串口号!");
			$('comPortNum').focus();
			return false;
		}
		comPort = "COM" + $F('comPortNum');
	}
	
	//调用 VBS方法
	JsCall(commMode, ip, port, comPort);
   
	//关闭连接
	closeConVBS();
}

function whetherCom() { //通讯方式是否为COM方式
	if($('commMode').value == 'COM') {
		document.getElementById('comPortTr').style.display = 'block';
	} else {
		document.getElementById('comPortTr').style.display = 'none';
	}
}
</script>

</head>
<OBJECT id="ZJRfid" style="width:0px;height:0px" classid="clsid:9971A2F9-F9D7-4A0D-A316-3F755E0964D2" codebase="../resource/cert/ZJRfid.CAB#version=1,0,0,2">
</OBJECT>
<body>
<%@ include file="../../scripts/common.jsp" %>

<br>
<form name="form1" method="post" action="devbaseinfo.do?action=pasteTag">
<input type="hidden" name="ip" value="${cardDispatcherIP}" />
<input type="hidden" name="port" value="${cardDispatcherPort}" />
<table align="center" width="750" border="0" cellspacing="1" cellpadding="6" class="table1">
 <tr class="tr1">
    <td nowrap colspan="4" align="center">设备关联标签</td>
  </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("Main.dev_no") %>：</td>
    <td nowrap>
    <input type="hidden" name="devNo" value="${devbaseinfo.no}" />
    <c:out value="${devbaseinfo.no}"/>&nbsp;</td>
    <td nowrap><%=resource.srcStr("System.devno_ip")%>：</td>
    <td nowrap"><c:out value="${devbaseinfo.ip}"/>&nbsp;</td>
  </tr>
   <tr class="tr3">
  <td nowrap><%=resource.srcStr("Main.dev_address") %>：</td>
  <td nowrap><c:out value="${devbaseinfo.address}"/>&nbsp;</td>
  <td nowrap><%=resource.srcStr("设备坐标") %>：</td>
  <td nowrap><c:out value="${devbaseinfo.x}"/>,<c:out value="${devbaseinfo.y}"/></td>
 </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("Main.own_org") %>：</td>
    <td nowrap><c:out value="${orgName}"/></td>
    <td nowrap><%=resource.srcStr("System.whether_in_bank") %>：</td>
    <td nowrap name="awayFlag">
    	<c:if test="${devbaseinfo.awayFlag == 1}"><%=resource.srcStr("System.in_bank")%></c:if>
    	<c:if test="${devbaseinfo.awayFlag == 2}"><%=resource.srcStr("System.out_bank")%></c:if>
	</td>
  </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("Main.devType")%>:</td>
	<td><c:out value="${typeName}"/></td>
	
	<td nowrap><%=resource.srcStr("Main.virtualTeller") %>：</td>
  	<td nowrap ><c:out value="${devbaseinfo.virtualTellerNo}"/>&nbsp;</td>
    
  </tr>

  <tr class="tr3">
   <td nowrap><%=resource.srcStr("System.install_type") %>：</td>
   <td nowrap name="setupType">
       <c:if test="${devbaseinfo.setupType==0}"><%=resource.srcStr("System.hall")%></c:if>
       <c:if test="${devbaseinfo.setupType==1}"><%=resource.srcStr("System.wall")%></c:if>
   </td>
   <td nowrap><%=resource.srcStr("System.compression_way")%>:</td>
  <td nowrap>
 	<c:if test="${devbaseinfo.zipType==0}"><%=resource.srcStr("System.not_compressed")%></c:if>
  	<c:if test="${devbaseinfo.zipType==2}"><%=resource.srcStr("System.zip_compression")%></c:if>
  	<c:if test="${devbaseinfo.zipType==3}"><%=resource.srcStr("System.gzip_compression")%></c:if>
  </td>
  </tr>
  <tr class="tr3">
  <td nowrap><%=resource.srcStr("System.wired_wireless")%>：</td>
    <td nowrap name="netType">
       <c:if test="${devbaseinfo.netType=='C'}"><%=resource.srcStr("System.wired")%></c:if>
       <c:if test="${devbaseinfo.netType=='W'}"><%=resource.srcStr("System.wireless")%></c:if>
	</td>
	<td nowrap><%=resource.srcStr("System.trans_pakage_size") %>：</td>
  <td nowrap><c:out value="${devbaseinfo.commPacket}"/></td>
  </tr>
 <tr class="tr1">
    <td nowrap>通讯方式:</td>
    <td colspan="3">
    	<select name="commMode" size="1" onchange="whetherCom()">
			<option value="TCP" checked>TCP</option>
      		<option value="COM">串口</option>
      	</select>&nbsp;*
  	</td>
  </tr>
  <tr class="tr3" id="comPortTr" style="display:none">
  <td nowrap>串口号:</td>
    <td colspan="3">
    	COM<input type="text" name="comPortNum" value="1" size="3" maxlength="3"
    		onkeyup="value=value.replace(/\D/g,'')" 
    		onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/\D/g,''))" />&nbsp;*
  	</td>
  </tr>
 <tr class="tr1">
    <td nowrap><font color="green">TID：</font></td>
    <td nowrap colspan="3">
    	<input type="text" name="tagTid" class="paneGray" readonly />&nbsp;*
    	<input type="button" value="读取" class="button" style="width: 35px;" onclick="return readTagTid()" />&nbsp;
		<input type="button" value="重置" class="button" style="width: 35px;" onclick="javascript:$('tagTid').value='';" />
    </td>
  </tr>
</table>

<p align="center">
	  <input class="button" type="button" value='<%=resource.srcStr("Main.submit")%>' onClick="return checkForm()" />&nbsp;
      <input class="button" type="button" value='<%=resource.srcStr("Main.back")%>' onClick="javascript:window.history.back();">
</p>

</form>

</body>
</html>