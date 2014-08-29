<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=gbk" />
	<title></title>
	<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
	<script language="JavaScript" src="../scripts/json2.js" type="text/javascript"></script>
	<script type='text/javascript' src='../dwr/interface/orgService.js'></script>
	<script type='text/javascript' src='../dwr/interface/devTypeService.js'></script>
	<script type='text/javascript' src='../dwr/interface/addnotesLineService.js'></script>
	<SCRIPT type=text/javascript src="../dwr/interface/devBaseInfoService.js"></SCRIPT>
	<script type='text/javascript' src='../dwr/engine.js'></script>
	<script type='text/javascript' src='../dwr/util.js'></script>
	<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
	<script language="JavaScript" src="../scripts/calendar.js" type="text/javascript"></script>
	<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
	<script language="JavaScript" src="../scripts/orgSelector.js" type="text/javascript"></script>
	<link href="../styles/common.css" rel="stylesheet" type="text/css">
</head>
<body >
<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<%@ include file="../../scripts/orgSelector.jsp" %>
<input type="hidden" id="Main.input_dev_no" value='<%=resource.srcStr("Main.input_dev_no")%>'/>
<input type="hidden" id="System.dev_no_special" value='<%=resource.srcStr("System.dev_no_special")%>'/>
<input type="hidden" id="System.input_dev_ip" value='<%=resource.srcStr("System.input_dev_ip")%>'/>
<input type="hidden" id="System.error_ip" value='<%=resource.srcStr("System.error_ip")%>'/>
<input type="hidden" id="System.input_dev_address" value='<%=resource.srcStr("System.input_dev_address")%>'/>
<input type="hidden" id="System.address_longer_enter" value='<%=resource.srcStr("System.address_longer_enter")%>'/>
<input type="hidden" id="System.select_own_org" value='<%=resource.srcStr("System.select_own_org")%>'/>
<input type="hidden" id="System.select_whether_inbank" value='<%=resource.srcStr("System.select_whether_inbank")%>'/>
<input type="hidden" id="System.select_Business_type" value='<%=resource.srcStr("System.select_Business_type")%>'/>
<input type="hidden" id="System.select_dev_status" value='<%=resource.srcStr("System.select_dev_status")%>'/>
<input type="hidden" id="System.select_dev_maintainer" value='<%=resource.srcStr("System.select_dev_maintainer")%>'/>
<input type="hidden" id="System.select_install_type" value='<%=resource.srcStr("System.select_install_type")%>'/>
<input type="hidden" id="System.serial_longer_enter" value='<%=resource.srcStr("System.serial_longer_enter")%>'/>
<input type="hidden" id="System.error_startclose_time" value='<%=resource.srcStr("System.error_startclose_time")%>'/>
<input type="hidden" id="System.shutdown_than_24" value='<%=resource.srcStr("System.shutdown_than_24")%>'/>
<input type="hidden" id="System.select_startclose_time" value='<%=resource.srcStr("System.select_startclose_time")%>'/>
<input type="hidden" id="System.start_less_8" value='<%=resource.srcStr("System.start_less_8")%>'/>
<input type="hidden" id="System.min_sec_min" value='<%=resource.srcStr("System.min_sec_min")%>'/>
<input type="hidden" id="Main.select_dev_no" value='<%=resource.srcStr("Main.select_dev_no")%>'/>
<input type="hidden" id="System.virtual_must_digital" value='<%=resource.srcStr("System.virtual_must_digital")%>'/>
<input type="hidden" id="System.serial_must_digital" value='<%=resource.srcStr("System.serial_must_digital")%>'/>
<input type="hidden" id="Main.select" value='<%=resource.srcStr("Main.select")%>'/>
<input type="hidden" id="System.boothour_max" value='<%=resource.srcStr("System.boothour_max")%>'/>
<input type="hidden" id="System.min_sec_max" value='<%=resource.srcStr("System.min_sec_max")%>'/>
<input type="hidden" id="System.boothour_min" value='<%=resource.srcStr("System.boothour_min")%>'/>
<input type="hidden" id="money.rmbLevelErr" value='<%=resource.srcStr("money.rmbLevelErr")%>'/>
<input type="hidden" id="money.hongkongLevelErr" value='<%=resource.srcStr("money.hongkongLevelErr")%>'/>
<input type="hidden" id="system.rmoneyMax" value='<%=resource.srcStr("system.rmoneyMax")%>'/>
<input type="hidden" id="system.hkmoneyMax" value='<%=resource.srcStr("system.hkmoneyMax")%>'/>
<input type="hidden" id="money.rmbLevel" value='<%=resource.srcStr("money.rmbLevel")%>'/>
<input type="hidden" id="bankOrgNo" name="bankOrgNo"/>
</div>

<script language="JavaScript">
var j$ = jQuery.noConflict();

j$().ready(function(){
	//样式
	j$(":text").addClass("pane");

	//只能输入数字的域
	j$("#rmbLevel").add(j$("#cassetteStantardSize")).add(j$("#devStantardSize")).add(j$("#arrivalMinutes")).add(j$("#commPacket")).add(j$("#maxAddClrPeriod")).add(j$("#addClrPeriod")).onlyNumber();

	//当设备型号变化时，变化钞箱标准容量 变化钞箱标准容量 根据型号显示/隐藏“是否开通循环”下拉框
	j$("#devType").change(function(){
		var devType = j$(this).val();
		if (devType.length == 0 ) {
			return false;
		}
		devTypeService.qryCatalogByType(devType, function(data){
			if (data == 10001) {    //atm
				j$("#cassetteStantardSize").val(2500);
			} else {
				j$("#cassetteStantardSize").val(2000);
			}
		});
	}).change();
	
	//只能输入数字的域
//	j$("#rmbLevel").add(j$("#cassetteStantardSize")).add(j$("#devStantardSize")).onlyNumber();
	
});

function checkForm(){
	if(isNull($F('no'))){
	 alert(document.getElementById('Main.input_dev_no').value);
		document.form1.no.select();
		document.form1.no.focus();
	 return false;
	}
	if(document.getElementById("no").value!=""){
		var tmp = document.getElementById("no").value;
		var aa = "%";
	 if(tmp.indexOf(aa) > 0){
			alert(document.getElementById('System.dev_no_special').value);
			document.form1.no.select();
			document.form1.no.focus();
			return false;
		}
	}
	if (j$("#ip").val() != "" ) {    //填写了IP
		if (checkip(j$("#ip")[0]) == false) {
			return false;
		}
	}
	if(isNull($F('address'))){
	 alert(document.getElementById('System.input_dev_address').value);
		document.form1.address.select();
		document.form1.address.focus();
	 return false;
	}
		if(!checktextLen(document.form1.address,80))
		{
			alert(document.getElementById('System.address_longer_enter').value);
			document.form1.address.select();
			document.form1.address.focus();
			return false;
		}
	if(isNull($F('orgNo'))){
	 alert("请选择所属网点");
	 return false;
	}
	if(isNull($F('awayFlag'))){
	 alert(document.getElementById('System.select_whether_inbank').value);
		document.form1.awayFlag.select();
	 return false;
	}
	if(isNull($F('devType'))){
	 alert(document.getElementById('Main.select_dev_no').value);
	 return false;
	}
	if(isNull($F('workType'))){
	 alert(document.getElementById('System.select_Business_type').value);
	 return false;
	}
	if(isNull($F('status'))){
	 alert(document.getElementById('System.select_dev_status').value);
	 return false;
	}
	if(isNull($F('devService'))){
	 alert(document.getElementById('System.select_dev_maintainer').value);
	 return false;
	}
	if(isNull($F('setupType'))){
	 alert(document.getElementById('System.select_install_type').value);
	 return false;
	}
	if(isNull($F('cassetteStantardSize'))){
	 alert('请输入钞箱标准容量!');
	 return false;
	}
	if(isNull($F('devStantardSize'))){
	 alert('请输入设备最大装钞容量!');
	 return false;
	}
	if(!isNull(form1.virtualTellerNo.value)){
	  for(var i=0;i<form1.virtualTellerNo.value.length;i++)
	  {
	      var point=form1.virtualTellerNo.value.charAt(i);
	      if(point<'0'||(point>'9'&&point<'A')||(point>'Z'&&point<'a')||(point>'z'))
	      {
	         alert(document.getElementById('System.virtual_must_digital').value);
	         form1.virtualTellerNo.select();
	         return false;
	      }
	  }
	  } else {
	 	 alert('请输入虚拟柜员号!');
	 	 form1.virtualTellerNo.select();
	 	 return;
	  }
	if(form1.rmbLevel != undefined) {
	if(isNull($F('rmbLevel'))){
	 alert(document.getElementById('money.rmbLevelErr').value);
		document.form1.rmbLevel.select();
		document.form1.rmbLevel.focus();
	 return false;
	}
	if($F('rmbLevel') <1 ){
		    alert('人民币必须大于零');
			document.form1.rmbLevel.select();
			document.form1.rmbLevel.focus();
		    return false;
		}
	if($F('rmbLevel') >9999999 ){
	 alert(document.getElementById('system.rmoneyMax').value);
		document.form1.rmbLevel.select();
		document.form1.rmbLevel.focus();
	 return false;
	}
	}

	form1.submit();
	return true;
}

function loadType(){		
	var pars="devVendor="+$('devVendor').value+"&"+new Date();
	var myAjax = new Ajax.Updater('typeValue','devbaseinfo.do?action=loadType',{method: 'get', parameters: pars,evalScripts:true});
}

function getNextOrgGradebyOrgNo(){
	if ($("_orgNo")) {
		orgService.getNextOrgGradebyOrgNo($("_orgNo").value,$("orgType").value,
		function setData(data){	
			if ( data !=null ){
			}else{
				alert("请选择网点");
				$("_orgNo").value='';
				$("_orgName").value='';
				form1.address.value = '';
				//$("orgGrade").value='';
				//$("orgGradeName").value='';
				form1._orgName.select();
				return false;
			}
		});
	}
}
function setOrg(obj1,obj2){
	DWREngine.setAsync(false);
	if($('_orgNo').value!=obj1){
		orgService.getOrgByNo(obj1,
			function getaddress(data){
			    if(data!=null)
				form1.address.value=data.address;
			}
		);
	}
	$("orgFrame").style.display="none";    
	$('_orgNo').value=obj1;
	$('_orgName').value=obj2;
	getNextOrgGradebyOrgNo();
	if($("_orgNo").value!="" && $("_orgNo").value!="${org.no}"){
  		//orgService.qryDevNumsErrorType($("_orgNo").value, "${org.no}", function(data){
  		//	if(data != ""){
	  	//		if(data == "newTooMany"){
	  	//			alert("若修改将导致网点"+$("_orgName").value+"("+$("_orgNo").value+")设备数过多，请选择其他网点或修改网点类型！");
	  	//		}else if(data == "oldTooLittle"){
	  	//			alert("若修改将导致网点"+$("_orgName").value+"("+$("_orgNo").value+")设备数过少，请选择其他网点或修改网点类型！");
	  	//		}else{
	  	//		alert("若修改将导致网点"+$("_orgName").value+"("+$("_orgNo").value+")设备数过多，网点"+$("_orgName").value+"("+$("_orgNo").value+")设备数过少，请选择其他网点或修改网点类型！");
	  	//		}
	  	//		$("_orgNo").value = "";
		//		$("_orgName").value = "";
		//		form1.address.value = "";
		//		form1._orgName.select();
  		//	}
  		//});
  	}
	DWREngine.setAsync(true);
}
function setMercId(obj)
{
	document.form1.mercId.value=obj.value;
}
function addTime(obj)
{
	if(obj==1){
		if($('openTime_h').value>=23){
			alert(document.getElementById('System.boothour_max').value+"23");
			document.form1.openTime_h.select();
		}
		else{
			$('openTime_h').value++;
			if($('openTime_h').value.length==1){
				$('openTime_h').value="0"+$('openTime_h').value;
			}
		}
	}
	else if(obj==-1){
		if($('closeTime_h').value>=24){
			alert(document.getElementById('System.boothour_max').value+"24");
			document.form1.closeTime_h.select();
		}
		else{
			$('closeTime_h').value++;
			if($('closeTime_h').value.length==1){
				$('closeTime_h').value="0"+$('closeTime_h').value;
			}
		}
	}
	else{
		if(obj.value>=59){
			alert(document.getElementById('System.min_sec_max').value+"59");
			obj.select();
		}
		else{
			obj.value++;
			if(obj.value.length==1){
				obj.value="0"+obj.value;
			}
		}
	}
}
function subTime(obj)
{
	if(obj==1){
		if($('openTime_h').value<=00){
			alert(document.getElementById('System.boothour_min').value+"00");
			document.form1.openTime_h.select();
		}
		else{
			$('openTime_h').value--;
			if($('openTime_h').value.length==1){
				$('openTime_h').value="0"+$('openTime_h').value;
			}
		}
	}
	else if(obj==-1){
		if($('closeTime_h').value<=00){
			alert(document.getElementById('System.boothour_min').value+"00");
			document.form1.closeTime_h.select();
		}
		else{
			$('closeTime_h').value--;
			if($('closeTime_h').value.length==1){
				$('closeTime_h').value="0"+$('closeTime_h').value;
			}
		}
	}
	else{
		if(obj.value<=00){
			alert(document.getElementById('System.min_sec_min').value+"00");
			obj.select();
		}
		else{
			obj.value--;
			if(obj.value.length==1){
				obj.value="0"+obj.value;
			}
		}
	}
}
function showOrg(){
if($("orgFrame").style.display=="block"){
		$("orgFrame").style.display="none";   
	}else{
//		if($("tmpfirst").value==0){
//	  		$("tmpfirst").value=1;
			showOrgSelectorByOrgNo('dimg',j$("#bankOrgNo").val());
//	  	}else{
//	  		$("orgFrame").style.display="block";   
//	  	}
	}
}
function packetChange(){
	if($("netType").value=="C"){
		$("commPacket").value="8000";
		$("zipType").value="0"
	}
	else{
		$("commPacket").value="256";
		$("zipType").value="3"
	}
}
function addRow(val1)   
{  
   var newRow = document.all("dev1").insertRow();
   newRow.style.background = "ffffff";
   var oCell = newRow.insertCell(); 
   var val = document.getElementById('money.rmbLevel').value; 
   oCell.innerHTML = '钞箱报警金额<br/>(人民币):';
   oCell = newRow.insertCell();
   oCell.colSpan="3";
   oCell.innerHTML = "<input type='text' id='rmbLevel' name='rmbLevel' onKeyUp=\"if(checkRmb(this) && event.keyCode != 8 ) {alert('只能输入数字');value='';} \" onKeyPress='if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false' maxlength='7' value='" + document.getElementById('rmb').value +  "' />&nbsp;*&nbsp;(单位：元)";
}
function removeRow(src)   
{   
 var oRow = src.parentElement.parentElement;     
 document.all("dev1").deleteRow(oRow.rowIndex);     
}   
function  checkRmb(obj)
{
 strRef = "0123456789";
	strTmp = obj.value;
	str = "";
	for(i=0;i<strTmp.length;i++){
		if(strTmp.charAt(i) != ' '){
			str += strTmp.charAt(i);
		}
	}
	for (i=0;i<str.length;i++) {
		tempChar= str.substring(i,i+1);
		if (strRef.indexOf(tempChar,0)!=-1) {
			return false; 
		}  
	}
	return true;
}
function loadMoney(flag) {

 if(flag != 1) {
     removeRow(form1.rmbLevel);
 } else {
     var sval = '';
     if(form1.rmbLevel != undefined) {
             removeRow(form1.rmbLevel);
             sval = form1.rmbLevel.value;
     }
     addRow(sval);
 }
}
function BeginloadMoney() {
 var val = document.getElementById("cashType").value;
 if(val != 1) {
     removeRow(form1.rmbLevel);
 } 
}
</script>
<body >
<!--javascript 国际化标识符-->
<br>
<form name="form1" method="post" action="devbaseinfo.do?action=mod" onclick="javascript:hideCalendar()">
<input type="hidden" class="pane" name="tmpfirst" value="0">
<input type="hidden" name="orgType" value="1">
<input type="hidden" name="devClrString" id="devClrString"/>

<table align="center" id="dev1" width="880" border="0" cellspacing="1" cellpadding="5" class="table1">
  <tr class="tr5">
    <td nowrap colspan="4"><%=resource.srcStr("System.mod_devbaseinfo")%></td>
  </tr> 
  <tr class="tr3">
    <td nowrap width="100"><%=resource.srcStr("Main.dev_no") %>：</td>
    <td nowrap>
    	<input type="hidden" name="no" value='${devbaseinfo.no}'>
    	<c:out value="${devbaseinfo.no}" />
    </td>
   </tr>
   <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.devno_ip") %>：</td>
    <td nowrap>
    <input type="hidden" name="oldIp" value="<c:out value="${devbaseinfo.ip}"/>">
    <input type="text" name="ip" id="ip" class="pane" value="<c:out value="${devbaseinfo.ip}"/>">
    </td> 
  </tr>
  <tr class="tr3">
  	<!--  <td nowrap>所属服务站：</td>
  	<td nowrap>
  		<input type="hidden" class="pane" name="srvStationNo" id="srvStationNo" value="${clrCenterNo}" style="width:100px">
  		<input type="text" class="paneGray" name="srvStationName" id="srvStationName" value= "${srvStationName }" readonly style="width:120px;">
  	</td>-->
    <td nowrap>所属网点：</td>
  	<td nowrap>
  		<input type="hidden" class="pane" name="orgNo" id="_orgNo" value="<c:out value="${org.no}"/>">
  		<input type="text" class="paneGray" name="orgName" id="_orgName" readonly style="width:120px;" value="<c:out value="${org.name}"/>">
  		<a onclick="event.cancelBubble=true;" href="javascript:showOrg()"><img id="dimg" align="absmiddle" src="../images/org.gif" border="0"></a>&nbsp;*
  	</td>
  </tr>
 <tr class="tr3">
	<td nowrap><%=resource.srcStr("Main.dev_address") %>：</td>
  	<td nowrap><input type="text" name="address"  style="width:400px;" class="pane" value="<c:out value="${devbaseinfo.address}"/>" maxlength="80">&nbsp;*</td>
 </tr>
 <tr class="tr3">
    <td nowrap><%=resource.srcStr("设备坐标") %>：</td>
  	<td nowrap>
  		x:<input type="text" name="x" class="pane" value="<fmt:formatNumber value='${devbaseinfo.x}' pattern='##########.##########'/>">&nbsp;
  		y:<input type="text" name="y" class="pane" value="<fmt:formatNumber value='${devbaseinfo.y}' pattern='##########.##########'/>">
  	</td>
  </tr>
  <tr class="tr3">
  	<td nowrap>设备状态：</td>
  	<td>
  		<select name="status">
  			<option value="1" <c:if test="${devbaseinfo.status==1}">selected</c:if>>启用</option>
  			<option value="2" <c:if test="${devbaseinfo.status==2}">selected</c:if>>停用</option>
  			<option value="3" <c:if test="${devbaseinfo.status==3}">selected</c:if>>待运营</option>
  			<option value="4" <c:if test="${devbaseinfo.status==4}">selected</c:if>>已撤销</option>
  		</select>
  	</td>
  </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.whether_in_bank") %>：</td>
    <td nowrap>
     <select name="awayFlag" size="1">
      <option value="1" ${devbaseinfo.awayFlag == 1 ? 'selected' : ''}><%=resource.srcStr("System.in_bank")%></option>
      <option value="2" ${devbaseinfo.awayFlag == 2 ? 'selected' : ''}><%=resource.srcStr("System.out_bank")%></option>
	</select>&nbsp;*
	</td> 
  </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("Main.devType") %>：</td>
    <td nowrap>
    <select name="devType" id="devType" size="1">	
	     <option value=""></option>         
         <c:forEach items="${typeList}" var="item"  step="1" >
	     <option value="<c:out value="${item[0]}"/>" ${item[0] == devbaseinfo.devTypeTable.no ? 'selected' : ''}><c:out value="${item[1]}"/></option>
	    </c:forEach>
	   </select>&nbsp;*
      </td>	
  </tr>
  <tr class="tr3">	
    <td nowrap><%=resource.srcStr("Main.business_type") %>：</td>
    <td nowrap>
      <select name="workType" size="1">
          <option value="1" ${devbaseinfo.workType == 1 ? 'selected' : ''}><%=resource.srcStr("System.self")%></option>
          <option value="2" ${devbaseinfo.workType == 2 ? 'selected' : ''}><%=resource.srcStr("System.join")%></option>
  	  </select>&nbsp;*
	</td>
  </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.install_type") %>：</td>
    <td nowrap>
      <select name="setupType" size="1">
          <option value="0" ${devbaseinfo.setupType == 0 ? 'selected' : ''}><%=resource.srcStr("System.hall")%></option>
          <option value="1" ${devbaseinfo.setupType == 1 ? 'selected' : ''}><%=resource.srcStr("System.wall")%></option>
      </select>&nbsp;*
  </td>
 </tr>
 <tr class="tr3"> 
 <td nowrap><%=resource.srcStr("System.wired_wireless")%>：</td>
  <td nowrap>
     <select name="netType" size="1" onchange="packetChange()">
         <option value="C" ${devbaseinfo.netType=="C" ? 'selected' : ''} ><%=resource.srcStr("System.wired")%></option>
         <option value="W" ${devbaseinfo.netType=="W" ? 'selected' : ''} ><%=resource.srcStr("System.wireless")%></option> 
     </select>
      </td> 
 </tr>
 
 <tr class="tr3">
 <td nowrap><%=resource.srcStr("System.devservice_com") %>：</td>
   <td nowrap>
     <select name="devService" size="1">
	   <option value=""></option>
	   <c:forEach items="${serviceList}" var="item"  step="1">
	   <option value="<c:out value="${item[0]}"/>" ${item[0] == devbaseinfo.devServiceCompany.no ? 'selected' : ''}>
	   <c:out value="${item[1]}"/></option>
	   </c:forEach>
	</select>&nbsp;*
    </td>
  </tr>
  <tr class="tr3"> 		
  <td nowrap><%=resource.srcStr("Main.virtualTeller") %>：</td>
  <td nowrap><input type="text" name="virtualTellerNo" class="pane" 
  value="<c:out value="${devbaseinfo.virtualTellerNo}"/>"  maxlength="5">&nbsp;*</td>  
 </tr>
 <tr class="tr3">
 	<td nowrap>钞箱标准容量:</td>
	<td nowrap>
			<input type="text" name="cassetteStantardSize" id="cassetteStantardSize" 
				maxlength="5" value='<c:out value="${devbaseinfo.cassetteStantardSize}"/>'>&nbsp;*(单位：张)
	</td>
  </tr>
  <tr class="tr3">	
	<td nowrap>设备最大装钞容量:</td>
	<td nowrap>
		<input type="text" name="devStantardSize" id="devStantardSize"
			maxlength="5" value='<c:out value="${devbaseinfo.devStantardSize}"/>' />&nbsp;*(单位：万元)</td>
 </tr>
 <tr class="tr3">
	<td nowrap>钞箱报警金额:</td>
  	<td nowrap>
  		<input type="text" name="rmbLevel" onKeyUp="value=value.replace(/[^\d]/g,'')" 
  		onKeyPress="if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false" maxlength="7" 
  		value='<c:out value="${devbaseinfo.cashboxLimit}"/>' >&nbsp;*(单位：元)
  	</td>
  </tr>
</table>
<br/>
	<p align="center">
   		<input class="button" type="button" value='<%=resource.srcStr("Main.submit")%>' onClick="checkForm();">&nbsp;
   		<input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.history.back()">
	</p>

</form>

</body>
</html>
