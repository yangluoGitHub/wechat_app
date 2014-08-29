<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=gbk" />
<title></title>
<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>

<link href="../styles/common.css" rel="stylesheet" type="text/css">

<%!
public String getTime(String str)
{
	if(str.length()!=6) return "";

	String hour = str.substring(0,2);
	String minute = str.substring(2,4);
	String second = str.substring(4,6);
	String s= hour+":"+minute+":"+ second;

	return s;
}

%>
</head>
<script type="text/javascript">
var j$ = jQuery.noConflict();

j$().ready(function(){

});

</script>
<body>
<%@ include file="../../scripts/common.jsp" %>

<br>

<input type="hidden" id="clrTimeList" value="${clrTimeList}" />
<form name="form1" method="post" action="devbaseinfo.do?action=detail">
<table align="center" width="700" border="0" cellspacing="1" cellpadding="6" class="table1">
 <tr class="tr5">
    <td nowrap colspan="4">设备基本信息</td>
  </tr> 
  <tr class="tr3">
    <td nowrap width="100"><%=resource.srcStr("Main.dev_no") %>：</td>
    <td nowrap>
    	<c:out value="${devbaseinfo.no}" />
    </td>
  </tr> 
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.devno_ip") %>：</td>
    <td nowrap>
    	<c:if test="${devbaseinfo.ip == null}">
    		<c:out value="不详"/>
    	</c:if>
    	<c:if test="${devbaseinfo.ip != null}">
    		<c:out value="${devbaseinfo.ip}"/>
    	</c:if>
    </td> 
  </tr>
 <tr class="tr3">
    <td nowrap>所属网点：</td>
    <td nowrap><c:out value="${orgName}"/></td>
	</tr>
  <tr class="tr3">
	<td nowrap><%=resource.srcStr("Main.dev_address") %>：</td>
  	<td nowrap><c:out value="${devbaseinfo.address}"/></td>
 </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.whether_in_bank") %>：</td>
    <td nowrap>
    	<c:if test="${devbaseinfo.awayFlag == 1}">
    		<%=resource.srcStr("System.in_bank")%>
    	</c:if>
    	<c:if test="${devbaseinfo.awayFlag == 2}">
    		<%=resource.srcStr("System.out_bank")%>
    	</c:if>
	</td>
  </tr> 
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("Main.devType") %>：</td>
    <td nowrap>
		<c:out value="${typeName}"/>
    </td>
  </tr> 
  <tr class="tr3">
  	<td nowrap>设备坐标：</td>
	<td nowrap>
		x:<fmt:formatNumber value="${devbaseinfo.x}" pattern="##########.##########"/>, y:<fmt:formatNumber value="${devbaseinfo.y}" pattern="##########.##########"/>
	</td>
  </tr>
  <tr class="tr3">
  	<td nowrap>设备状态：</td>
  	<td>
  		<c:if test="${devbaseinfo.status==1}">启用</c:if>
  		<c:if test="${devbaseinfo.status==2}">停用</c:if>
  		<c:if test="${devbaseinfo.status==3}">待运营</c:if>
  		<c:if test="${devbaseinfo.status==4}">已撤销</c:if>
  	</td>
  </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.install_type") %>：</td>
    <td nowrap>
    	<c:if test="${devbaseinfo.setupType == 0}">
    		<%=resource.srcStr("System.hall")%>
    	</c:if>
    	<c:if test="${devbaseinfo.setupType == 1}">
    		<%=resource.srcStr("System.wall")%>
    	</c:if>
    </td>
  </tr> 
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.wired_wireless")%>：</td>
    <td nowrap>
  	<c:if test="${devbaseinfo.netType=='C'}"><%=resource.srcStr("System.wired")%></c:if>
  	<c:if test="${devbaseinfo.netType=='W'}"><%=resource.srcStr("System.wireless")%></c:if>
  </td>
 </tr>
 <tr class="tr3">
 	<td nowrap><%=resource.srcStr("System.devservice_com") %>：</td>
 	<td nowrap><c:out value="${devbaseinfo.devServiceCompany.name}"/></td>	
  </tr> 
  <tr class="tr3">
  	<td nowrap><%=resource.srcStr("Main.virtualTeller") %>：</td>
  	<td nowrap><c:out value="${devbaseinfo.virtualTellerNo}"/></td>
 </tr>
 <tr class="tr3">
    <td nowrap><%=resource.srcStr("Main.business_type") %>：</td>
    <td nowrap>
    	<c:if test="${devbaseinfo.workType == 1}">
    		<%=resource.srcStr("System.self")%>
    	</c:if>
    	<c:if test="${devbaseinfo.workType == 2}">
    		<%=resource.srcStr("System.join")%>
    	</c:if>
	</td>
  </tr> 
  <tr class="tr3">
	<td nowrap>钞箱报警金额(元)：</td>
  	<td nowrap>
  		<c:out value="${devbaseinfo.cashboxLimit}"/>
  	</td>
 </tr>
 <tr class="tr3">
 	<td nowrap>钞箱标准容量(张):</td>
	<td nowrap>
		<c:out value="${devbaseinfo.cassetteStantardSize}"/>
	</td>
  </tr> 
  <tr class="tr3">
	<td nowrap>设备最大装钞容量(万元)：</td>
	<td nowrap><c:out value="${devbaseinfo.devStantardSize}" /></td>
 </tr>
</table>
<br />
<c:if test="${empty backflag}">
<p align="center">
      <input class="button" type="button" value='<%=resource.srcStr("Main.back")%>' onClick="javascript:window.history.back();">
</p>
</c:if>
</form>

</body>
</html>