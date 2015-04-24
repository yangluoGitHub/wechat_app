<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page import="java.util.Date"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>

<head>
<meta http-equiv="Content-Type" content="text/html;charset=gbk" />
<title></title>
</head>
<script type='text/javascript' src='../dwr/interface/orgService.js'></script>
<script type='text/javascript' src='../dwr/interface/projectTypeService.js'></script>
<script type='text/javascript' src='../dwr/interface/devTypeService.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>

<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/calendar3.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">

<body onload="javascript:getOrgName();getRvcProjectTypeName();getOrgName2();getTypeName();">
<%@ include file="../../scripts/common.jsp" %>
<script language="JavaScript">


function getOrgName(){
		orgService.getOrgByNo(form.orgNo.value,
			function getOrg(data){
			    if(data!=null)
				form.orgName.value=data.name;
			}
		);
}

 function getRvcProjectTypeName(){
		projectTypeService.qryProjectTypeById(form.rvcProjectTypeNo.value,
			function getRvcProjectType(data){
			    if(data!=null)
				form.rvcProjectTypeName.value=data.groupvalue;
			}
		);
}

function getOrgName2(){
		orgService.getOrgByNo(form.devserviceNo.value,
			function getOrg(data){
			    if(data!=null)
				form.devserviceName.value=data.name;
			}
		);
}

function getTypeName(){
		devTypeService.qryDevTypeById($("devType").value,
			function getType(data){
			    if(data!=null)
				form.devTypeName.value=data.name;
			}
		);	
}

function audit(){
    form.action="operateAudit.do?action=audit";
    form.submit();
}

function refuse(){
    form.action="operateAudit.do?action=refuse";
    form.submit();
}
</script>



<!-- 个人在jsp开发的过程中,css,字体,颜色不需要改变, 需要改变仅仅是table的width,为统一起见,查询页width=500,查询列表页width="750" -->
<!-- action请求 -->
<form name="form" method="post" action="" >
<input type="hidden" name="flag" value='<%=request.getParameter("flag") %>'>
<input type="hidden" name="userId" value='<c:out value="${logvo.userId}"/>'>
<input type="hidden" name="date" value='<c:out value="${logvo.date}"/>'>
<input type="hidden" name="cashboxLimit" value='<c:out value="${objectvo.cashboxLimit}"/>'>
<%
	request.setAttribute("flag",request.getParameter("flag"));
%>
						<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
								 <tr>
								  <td nowrap><b><%=resource.srcStr("System.detail_info")%>:</b></td>			    
								 </tr>
								  
						</table>
						
						<br>	
						
						<table align="center" width="750" border="0" cellspacing="1" cellpadding="6" class="table1">
 <tr class="tr1">
    <td nowrap colspan="4" align="center"><c:out value="${title}"/></td>
  </tr>
 <!--V端数据-->
   <!--<tr class="tr3">
    <td nowrap colspan="4" align="left"><b>V端数据</b></td>
  </tr>-->
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("Main.dev_no") %>：</td>
    <td nowrap> <input type="text" class="paneGray" name="no" readonly value='<c:out value="${objectvo.no}"/>'>&nbsp;</td>
    <td nowrap><%=resource.srcStr("System.devno_ip")%>：</td>
    <td nowrap"><input type="text" class="paneGray" name="ip" readonly value='<c:out value="${objectvo.ip}"/>'>&nbsp;</td>
  </tr>
   <tr class="tr3">
    <td nowrap><%=resource.srcStr("Main.own_org") %>：</td>
    <td nowrap>
    <input type="hidden" class="paneGray" name="orgNo" readonly value='<c:out value="${objectvo.orgNo}"/>'>
    <input type="text" class="paneGray" name="orgName" readonly>&nbsp;
    </td>
    <td nowrap><%=resource.srcStr("System.whether_in_bank") %>：</td>
    <td nowrap name="awayFlag">
    	<!--<c:if test="${devbaseinfo.awayFlag == 1}">在行自助服务区</c:if>
    	<c:if test="${devbaseinfo.awayFlag == 2}">单机离行自助服务点(<%=resource.srcStr("System.self")%>)</c:if>
    	<c:if test="${devbaseinfo.awayFlag == 3}">离行自助银行(<%=resource.srcStr("System.self")%>)-非银亭</c:if>
    	<c:if test="${devbaseinfo.awayFlag == 4}">离行自助银行(<%=resource.srcStr("System.self")%>)-银亭</c:if>
    	<c:if test="${devbaseinfo.awayFlag == 5}">单机离行自助服务点(<%=resource.srcStr("System.join")%>)</c:if>
    	<c:if test="${devbaseinfo.awayFlag == 6}">离行自助银行(<%=resource.srcStr("System.join")%>)-非银亭</c:if>
    	<c:if test="${devbaseinfo.awayFlag == 7}">离行自助银行(<%=resource.srcStr("System.join")%>)-银亭 </c:if>
    	<c:if test="${devbaseinfo.awayFlag == 8}">单机在行自助服务点</c:if>-->
    	<c:if test="${objectvo.awayFlag == 1}">
    	<input type="text" name="awayflag1" class="paneGray" readonly value='<%=resource.srcStr("System.in_bank")%>'>
    	</c:if>
    	<c:if test="${objectvo.awayFlag == 2}">
    	<input type="text" name="awayflag2" class="paneGray" readonly value='<%=resource.srcStr("System.out_bank")%>'>
    	</c:if>
	</td>
  </tr>
   <tr class="tr3">
  <td nowrap><%=resource.srcStr("Main.dev_address") %>：</td>
  <td nowrap colspan="3"><input type="text" class="paneGray" name="address" readonly style="width:400" value='<c:out value="${objectvo.address}"/>'>&nbsp;</td>
 </tr>
 
  <%-- <tr class="tr3">
    <td nowrap><%=resource.srcStr("Main.dev_type")%>:</td>
	<td>
	<input type="hidden" class="paneGray" name="devCatalog" readonly value=<c:out value="${objectvo.devCatalog}"/>>
	<input type="text" class="paneGray" name="devCatalogName" readonly>	
	</td>

    <td nowrap><%=resource.srcStr("Main.dev_brand")%>:</td>
	<td>
	<input type="hidden" class="paneGray" name="devVendor" readonly value=<c:out value="${objectvo.devVendor}"/>>
	<input type="text" class="paneGray" name="devVendorName" readonly>
	</td>
  </tr> --%>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("Main.devType")%>:</td>
	<td>
	<input type="hidden" class="paneGray" name="devType" readonly value='<c:out value="${objectvo.devTypeNo}"/>'>
	<input type="text" class="paneGray" name="devTypeName" readonly >
	</td>

    <td nowrap><%=resource.srcStr("System.dev_provider")%>:</td>
	<td>
	<input type="hidden" class="paneGray" name="devserviceNo" readonly value='<c:out value="${objectvo.devServiceCompanyNo}"/>'>
	<input type="text" class="paneGray" name="devserviceName" readonly >
	</td>
  </tr>
  <tr class="tr3">
  <td nowrap><%=resource.srcStr("System.install_type") %>：</td>
    <td nowrap name="setupType">
       <c:if test="${objectvo.setupType==0}">
       <input type="text" name="setuptype1" class="paneGray" readonly value='<%=resource.srcStr("System.hall")%>'></c:if>
       <c:if test="${objectvo.setupType==1}">
       <input type="text" name="setuptype2" class="paneGray" readonly value='<%=resource.srcStr("System.wall")%>'></c:if>
  </td>
  <td nowrap><%=resource.srcStr("Main.business_type") %>：</td>
    <td nowrap name="workType">
       <c:if test="${objectvo.workType==1}">
       <input type="text" name="worktype1" class="paneGray" readonly value='<%=resource.srcStr("System.self")%>'></c:if>
       <c:if test="${objectvo.workType==2}">
       <input type="text" name="worktype2" class="paneGray" readonly value='<%=resource.srcStr("System.join")%>'></c:if>
	</td>  
  </tr>
  <tr class="tr3">
  	<!--<td nowrap>终端号：</td>
  	<td nowrap><c:out value="${devbaseinfo.terminalNo}"/>&nbsp;</td>-->
  	<td nowrap><%=resource.srcStr("Main.virtualTeller") %>：</td>
  	<td nowrap colspan="3"><input type="text" class="paneGray" name="visualTellerNo" readonly value='<c:out value="${objectvo.virtualTellerNo}"/>'>&nbsp;</td>
  	<%-- <td nowrap><%=resource.srcStr("Main.dev_serial_number") %>：</td>
  	<td nowrap><input type="text" class="paneGray" name="serial" readonly value='<c:out value="${objectvo.serial}"/>'> &nbsp;</td> --%>
 </tr>
<!--<tr class="tr3">
  	<td nowrap>设备购买日期：</td>
  	<td><c:out value="${devbaseinfo.buyDate}"/></td>
  	<td nowrap>设备安装日期：</td>
  	<td><c:out value="${devbaseinfo.installDate}"/></td>
 </tr>-->
 <%--<tr class="tr3">
  	<td nowrap><%=resource.srcStr("System.open_date") %>：</td>
  	<td><input type="text" class="paneGray" name="startdate" readonly value='<c:out value="${objectvo.startDate}"/>'></td>
  	<td nowrap><%=resource.srcStr("System.close_date") %>：</td>
  	<td><input type="text" class="paneGray" name="stopdate" readonly value='<c:out value="${objectvo.stopDate}"/>'></td>
  </tr> 
  <tr class="tr3">
   	<td nowrap><%=resource.srcStr("System.wedate") %>：</td>
   	<td><input type="text" class="paneGray" name="expiredate" readonly value='<c:out value="${objectvo.expireDate}"/>'></td>
   	<td><%=resource.srcStr("System.inspect_cycle") %>：</td>
   	<td nowrap><input type="text" class="paneGray" name="patrolPeriod" readonly value='<c:out value="${objectvo.patrolPeriod}"/>'>&nbsp;<%=resource.srcStr("System.day")%></td>
  </tr>
    <tr class="tr3">
    <td><%=resource.srcStr("System.open_time") %>：</td>
 	 <td><input type="text" class="paneGray" name="opentime" readonly value='<c:out value="${fn:substring(objectvo.openTime,0,2)}"/>:<c:out value="${fn:substring(objectvo.openTime,2,4)}"/>:<c:out value="${fn:substring(objectvo.openTime,4,6)}"/>'>&nbsp;</td>
  	<td><%=resource.srcStr("System.daily_shutdown") %>：</td>
  	<td><input type="text" class="paneGray" name="closetime" readonly value='<c:out value="${fn:substring(objectvo.closeTime,0,2)}"/>:<c:out value="${fn:substring(objectvo.closeTime,2,4)}"/>:<c:out value="${fn:substring(objectvo.closeTime,4,6)}"/>'>&nbsp;</td>
  </tr>


  <tr class="tr3">
   <!--<td nowrap>操作系统：</td>
   <td><c:out value="${os}"/>&nbsp;</td>-->
   <td nowrap><%=resource.srcStr("System.atmc_soft") %>:</td>
   <td>
   <input type="hidden" class="paneGray" name="rvcProjectTypeNo" readonly value='<c:out value="${objectvo.atmcSoft}"/>'>
   <input type="text" class="paneGray" name="rvcProjectTypeName" readonly >&nbsp;
   </td>
   <td nowrap><%=resource.srcStr("System.trans_pakage_size") %>：</td>
    <td nowrap><input type="text" name="commpacket" class="paneGray" readonly value="<c:out value="${objectvo.commPacket}"/>"></td>
  </tr>
  <tr class="tr3">
  <td nowrap><%=resource.srcStr("System.wired_wireless")%>：</td>
  <td nowrap name="netType">
  <c:if test="${objectvo.netType=='C'}">
  <input type="text" name="netType1" class="paneGray" readonly value='<%=resource.srcStr("System.wired")%>'></c:if>
  <c:if test="${objectvo.netType=='W'}">
  <input type="text" name="netType2" class="paneGray" readonly value='<%=resource.srcStr("System.wireless")%>'></c:if>
  </td>
  <td nowrap><%=resource.srcStr("System.non_cash_mark") %>：</td>
  <td nowrap>
  <c:if test="${objectvo.cashType=='1'}">
  <input type="text" name="cashType1" class="paneGray" readonly value='<%=resource.srcStr("System.cash")%>'></c:if>
  <c:if test="${objectvo.cashType=='2'}">
  <input type="text" name="cashType2" class="paneGray" readonly value='<%=resource.srcStr("System.non_cash")%>'></c:if>
  </td>
  </tr>--%>
  
  
<%-- 
  <td nowrap><%=resource.srcStr("System.compression_way")%>:</td>
  <td nowrap>
 	<c:if test="${objectvo.zipType==0}">
	<input type="text" value='<%=resource.srcStr("System.not_compressed") %>' class="paneGray" readonly name="ziptype1"></c:if>
  	<c:if test="${objectvo.zipType==2}">
  	<input type="text" value='<%=resource.srcStr("System.zip_compression") %>' class="paneGray" readonly name="ziptype2"></c:if>
  	<c:if test="${objectvo.zipType==3}">
  	<input type="text" value='<%=resource.srcStr("System.gzip_compression") %>' class="paneGray" readonly name="ziptype3"></c:if>
  </td> 

  <c:if test="${objectvo.cashType=='1'}">
  <tr class="tr3" >
  <td nowrap><%=resource.srcStr("money.rmbLevel") %>：</td>
  <td nowrap colspan="3"><input type="text" name="rmbLevel" value='' class="paneGray" readonly></td>
  </tr>
  </c:if> --%>
 
  <!--
  <tr class="tr3">
  <td nowrap><%=resource.srcStr("money.hongkonglevel") %>：</td>
  <td nowrap colspan='3'><input type="text" name="hongkonglevel" value=''  class="paneGray" readonly></td>
  </tr>
  -->
<script language="javascript">
	var cashboxLimit = $('cashboxLimit').value;
	if(cashboxLimit!=null&&cashboxLimit!=""&&cashboxLimit!="|||") {
		var StringArray = cashboxLimit.split("|");
		$('rmbLevel').value = StringArray[1];
	//	$('hongkonglevel').value = StringArray[2];
	}
</script>
  <!--<tr class="tr3">
   <td nowrap><%=resource.srcStr("System.av_soft")%>:</td>
   <td><c:out value="${antiVirusSoft}"/>&nbsp;</td>
   <td nowrap>厂商sp<%=resource.srcStr("control.type")%>:</td>
   <td><c:out value="${sp}"/>&nbsp;</td>
  </tr>
  <tr class="tr3">
  	<td nowrap>银亭号(多台离行组号):</td>
   	<td nowrap>
	    <c:forEach items="${orgGroupList}" var="item"  step="1">
	    	<c:if test="${bankBarNo==item[0]}"><c:out value="${item[1]}"/></c:if>
		</c:forEach>
   	</td>
  	<td nowrap>外卡取现标志：</td>
   	<td nowrap>
		<c:if test="${forCrdFlag == 0}">不能外卡取现</c:if>
		<c:if test="${forCrdFlag == 1}">能外卡取现</c:if>
   	</td>
  </tr>-->
 <!--P端数据
   <tr class="tr3">
    <td nowrap colspan="4" align="left"><b>P端数据</b></td>
  </tr>
  <tr class="tr3">
    <td nowrap>是否受理EMV卡：</td>
    <td nowrap> 
	<c:if test="${atminfo.emvtyp == 0}">否</c:if>
	<c:if test="${atminfo.emvtyp == 1}">是</c:if>
	</td>
    <td nowrap>记账机构号：</td>
    <td nowrap><c:out value="${atminfo.nodNo}"/></td>
  </tr>
  <tr class="tr3">
    <td nowrap>商户号：</td>
    <td nowrap><c:out value="${atminfo.mercid}"/></td>
    <td nowrap>连接方式：</td>
    <td nowrap>
		<c:if test="${atminfo.contyp == 0}">B/S模式</c:if>
		<c:if test="${atminfo.contyp == 1}">新C/S模式</c:if>
		<c:if test="${atminfo.contyp == 2}">迪堡C/S</c:if>
		<c:if test="${atminfo.contyp == 3}">NCR C/S</c:if>
	</td>
  </tr>
  <tr class="tr3">
    <td nowrap>当前周期号：</td>
    <td nowrap><c:out value="${atminfo.batno}"/></td>
    <td nowrap>银联标准地区代码：</td>
    <td nowrap><c:out value="${atminfo.rarecd}"/></td>
  </tr>
  <tr class="tr3">
    <td nowrap>旧系统的设备号：</td>
    <td nowrap><c:out value="${atminfo.oldid}"/></td>
    <td nowrap>FIT表下传记录序号：</td>
    <td nowrap><c:out value="${atminfo.fitno}"/></td>
  </tr>
  <tr class="tr3">
    <td nowrap>地区码下传标志：</td>
    <td nowrap>
	<c:if test="${atminfo.regflg == 0}">未下传</c:if>
	<c:if test="${atminfo.regflg == 1}">已下传</c:if>
    <td nowrap>设备权限下传标志：</td>
    <td nowrap>
	<c:if test="${atminfo.trmflg == 0}">未下传</c:if>
	<c:if test="${atminfo.trmflg == 1}">已下传</c:if>
	</td>
  </tr>
  <tr class="tr3">
    <td nowrap>卡权限下传标志：</td>
    <td nowrap>
	<c:if test="${atminfo.crdflg == 0}">未下传</c:if>
	<c:if test="${atminfo.crdflg == 1}">已下传</c:if>
	</td>
	<td nowrap>分行号：</td>
    <td nowrap><c:out value="${atminfo.brNo}"/></td>
  </tr>
  <tr class="tr3">
    <td nowrap>所属离行网点：</td>
    <td nowrap><c:out value="${atminfo.offNod}"/></td>
    <td nowrap>转账级别：</td>
    <td nowrap>
	<c:if test="${atminfo.accLev == 0}">不支持转账</c:if>
	<c:if test="${atminfo.accLev == 1}">本行本地转账</c:if>
	<c:if test="${atminfo.accLev == 2}">本行异地转账</c:if>
	<c:if test="${atminfo.accLev == 3}">他行转账</c:if>
	</td>
  </tr>
  <tr class="tr3">
    <td nowrap>最大冲正次数：</td>
    <td nowrap colspan="3"><c:out value="${atminfo.crcmax}"/></td>
  </tr>-->
</table>
						
	<br>
	
	<table width="100%">
		<tr>
   			<td align="center">
   				<c:if test="${flag==2}">
					<input class="button" type="button" value='<%=resource.srcStr("System.pass")%>' onclick="javascript:audit();">
					<input class="button" type="button" value='<%=resource.srcStr("System.refuse") %>' onclick="javascript:refuse();">
				</c:if>
   				<input class="button" type="button" value='<%=resource.srcStr("Main.back")%>' onclick="javascript:window.history.back();">
   			</td>
   		</tr>
	</table>	
</form> 
</body>  	  


<!--javascript 国际化标识符-->
<div id="javascriptI18n">
</div>
