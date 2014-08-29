<%@ page language="java" contentType="text/html; charset=gbk" errorPage="../error.jsp"%>
<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%>
<%
Resource resource = (Resource) GetResource.getOneResource(application, session.getAttribute("locale").toString());
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=gbk" />
		<title></title>
		<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
		<script language="JavaScript" src="../scripts/json2.js" type="text/javascript"></script>
		
		<script type='text/javascript' src='../dwr/interface/orgService.js'></script>
		<script type='text/javascript' src='../dwr/interface/devTypeService.js'></script>
		<SCRIPT type=text/javascript src="../dwr/interface/addnotesLineService.js"></SCRIPT>
		<SCRIPT type=text/javascript src="../dwr/interface/stationService.js"></SCRIPT>
		<SCRIPT type=text/javascript src="../dwr/interface/devBaseInfoService.js"></SCRIPT>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type='text/javascript' src='../dwr/util.js'></script>
		
		<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
		<script language="JavaScript" src="../scripts/calendar.js" type="text/javascript"></script>
		<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
		<script language="JavaScript" src="../scripts/orgSelector.js" type="text/javascript"></script>
		<link href="../styles/common.css" rel="stylesheet" type="text/css">

<script language="JavaScript">
var j$ = jQuery.noConflict();

j$().ready(function(){
	//j$("#addServiceDate").val(getDateStr());

	//只能输入数字的域
	j$("#rmbLevel").add(j$("#cassetteStantardSize")).add(j$("#devStantardSize")).add(j$("#arrivalMinutes")).add(j$("#commPacket")).onlyNumber();

	//当设备型号变化时，变化钞箱标准容量 根据型号显示/隐藏“是否开通循环”下拉框
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

	//样式
	j$(":text").addClass("pane");
});

function checkForm(){
	if(isNull($F('no'))){
		alert('<%=resource.srcStr("Main.input_dev_no")%>');
		document.form1.no.select();
		document.form1.no.focus();
    	return false;
  	} 
  	if(getByteLength($F('no')) < 4){
		alert("设备号长度必须大于等于4，请重新输入！");
		return false;
  	}
  	if (j$("#ip").val() != "" ) {    //填写了IP
  		if (checkip(j$("#ip")[0]) == false) {
			return false;
		}
	}
   	if(isNull($F('orgNo'))){
       alert("请选择所属网点");
       return false;
   	}
   	if(isNull($F('address'))){
       alert(document.getElementById('System.input_dev_address').value);
	   document.form1.address.select();
	   document.form1.address.focus();
       return false;
   	}
   	if(!checktextLen(document.form1.address,80)){
	   alert(document.getElementById('System.address_longer_enter').value);
	   document.form1.address.select();
	   document.form1.address.focus();
	   return false;
	}
   	if(isNull($F('awayFlag'))){
       alert(document.getElementById('System.select_whether_inbank').value);
	   document.form1.awayFlag.select();
       return false;
   	}
   	if(isNull($F('setupType'))){
       altert(document.getElementById('System.select_install_type').value);
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
   	if(!isNull(form1.virtualTellerNo.value)){
       for(var i=0;i<form1.virtualTellerNo.value.length;i++){
          var point=form1.virtualTellerNo.value.charAt(i);
          if(point<'0'||(point>'9'&&point<'A')||(point>'Z'&&point<'a')||(point>'z')){
             alert(document.getElementById('System.virtual_must_digital').value);
             form1.virtualTellerNo.select();
             return false;
          }
        }
    } else {
    	 alert(document.getElementById('System.virtual_must_digital').value);
    	 form1.virtualTellerNo.select();
    	 return;
    }
  	
  	var cassetteStantardSize = $('cassetteStantardSize').value;
  	if(isNull(cassetteStantardSize)){
      	alert("钞箱标准容量不能为空");
      	return false;
  	}
  	cassetteStantardSize = parseInt(cassetteStantardSize,10);
  	if(cassetteStantardSize<=0){
      	alert("钞箱标准容量必须是正整数");
      	return false;
  	}
  	var devStantardSize = $('devStantardSize').value;
  	if(isNull(devStantardSize)){
      alert("设备最大加钞量不能为空");
      return false;
  }
	
	form1.submit();
	return true;
}

function getNextOrgGradebyOrgNo(){
	if ($("_orgNo")) {
		DWREngine.setAsync(false);
		orgService.getNextOrgGradebyOrgNo($("_orgNo").value,$("orgType").value,
		function setData(data){	
			alert(data);
			if (data !=null){
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
		DWREngine.setAsync(true);
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
  	//getNextOrgGradebyOrgNo();
  	//if($("_orgNo").value!=""){
  	//	orgService.getDevNumsErrorType($("_orgNo").value, function(data){
  	//		if(data != ""){
	// 			if(data == "tooManyDevs"){
	//  				alert("网点"+$("_orgName").value+"("+$("_orgNo").value+")下只能有一台设备并且已存在设备，请选择其他网点或修改网点类型！");
	//  			}
	//  			$("_orgNo").value = "";
	////			$("_orgName").value = "";
	//			form1.address.value = "";
	//			form1._orgName.select();
  	//		}
  	//	});
  	//}
  	DWREngine.setAsync(true);
}
function loadType(){		
	var pars="devVendor="+$('devVendor').value+"&"+new Date();
	var myAjax = new Ajax.Updater('typeValue','devbaseinfo.do?action=loadType',{method: 'get', parameters: pars,evalScripts:true});
}
function setMercId(obj){
	document.form1.mercId.value=obj.value;
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
		$("zipType").value="0";
	}else{
		$("commPacket").value="256";
		$("zipType").value="3";
	}
}
   //纠正加钞周期
   function rectifyAddClrPeriod(obj){
       var tempValue = obj.value.replace(/[^\d]\./g,'');
       if(isNull(tempValue)){
          obj.value = '';
       }else{
          var indexOfDot = tempValue.indexOf('.');
          if(indexOfDot==-1){
              obj.value = parseInt(tempValue,10);
          }else{
              if(indexOfDot==0){
                  tempValue = '0'+tempValue,10;
              }
              var tempValueArray = tempValue.split('.');
              if(isNull(tempValueArray[1])){
                  obj.value = parseInt(tempValueArray[0],10)+'.';
              }else{
                  obj.value = parseInt(tempValueArray[0],10)+'.'+tempValueArray[1].substring(0,1);
              }
          }  
       }
   }
</script>
</head>
<body>
		<!--javascript 国际化标识符-->
		<div id="javascriptI18n">
			<%@ include file="../../scripts/common.jsp"%>
			<%@ include file="../../scripts/orgSelector.jsp"%>
			<input type="hidden" id="System.dev_no_digital" value='<%=resource.srcStr("System.dev_no_digital")%>' />
			<input type="hidden" id="Main.input_dev_no" value='<%=resource.srcStr("Main.input_dev_no")%>' />
			<input type="hidden" id="System.dev_length_error" value='<%=resource.srcStr("System.dev_length_error")%>' />
			<input type="hidden" id="System.dev_no_special" value='<%=resource.srcStr("System.dev_no_special")%>' />
			<input type="hidden" id="System.input_dev_ip" value='<%=resource.srcStr("System.input_dev_ip")%>' />
			<input type="hidden" id="System.dev_no_digital" value='<%=resource.srcStr("System.dev_no_digital")%>' />
			<input type="hidden" id="System.error_ip" value='<%=resource.srcStr("System.error_ip")%>' />
			<input type="hidden" id="System.input_dev_address" value='<%=resource.srcStr("System.input_dev_address")%>' />
			<input type="hidden" id="System.address_longer_enter" value='<%=resource.srcStr("System.address_longer_enter")%>' />
			<input type="hidden" id="System.select_own_org" value='<%=resource.srcStr("System.select_own_org")%>' />
			<input type="hidden" id="System.select_whether_inbank" value='<%=resource.srcStr("System.select_whether_inbank")%>' />
			<input type="hidden" id="System.select_install_type" value='<%=resource.srcStr("System.select_install_type")%>' />
			<input type="hidden" id="Main.select_dev_no" value='<%=resource.srcStr("Main.select_dev_no")%>' />
			<input type="hidden" id="System.select_Business_type" value='<%=resource.srcStr("System.select_Business_type")%>' />
			<input type="hidden" id="System.select_dev_status" value='<%=resource.srcStr("System.select_dev_status")%>' />
			<input type="hidden" id="System.select_dev_maintainer" value='<%=resource.srcStr("System.select_dev_maintainer")%>' />
			<input type="hidden" id="System.virtual_must_digital" value='<%=resource.srcStr("System.virtual_must_digital")%>' />
			<input type="hidden" id="System.serial_must_digital" value='<%=resource.srcStr("System.serial_must_digital")%>' />
			<input type="hidden" id="System.error_startclose_time" value='<%=resource.srcStr("System.error_startclose_time")%>' />
			<input type="hidden" id="System.shutdown_than_24" value='<%=resource.srcStr("System.shutdown_than_24")%>' />
			<input type="hidden" id="System.select_startclose_time" value='<%=resource.srcStr("System.select_startclose_time")%>' />
			<input type="hidden" id="System.start_less_8" value='<%=resource.srcStr("System.start_less_8")%>' />
			<input type="hidden" id="System.boothour_max" value='<%=resource.srcStr("System.boothour_max")%>' />
			<input type="hidden" id="System.min_sec_max" value='<%=resource.srcStr("System.min_sec_max")%>' />
			<input type="hidden" id="System.boothour_min" value='<%=resource.srcStr("System.boothour_min")%>' />
			<input type="hidden" id="System.min_sec_min" value='<%=resource.srcStr("System.min_sec_min")%>' />
			<input type="hidden" id="money.rmbLevelErr" value='<%=resource.srcStr("money.rmbLevelErr")%>' />
			<input type="hidden" id="money.hongkongLevelErr" value='<%=resource.srcStr("money.hongkongLevelErr")%>' />
			<input type="hidden" id="system.rmoneyMax" value='<%=resource.srcStr("system.rmoneyMax")%>' />
			<input type="hidden" id="system.hkmoneyMax" value='<%=resource.srcStr("system.hkmoneyMax")%>' />
			<input type="hidden" id="money.rmbLevel" value='<%=resource.srcStr("money.rmbLevel")%>' />
			<input type="hidden" id="System.virtual_must_digital" value='<%=resource.srcStr("System.virtual_must_digital")%>' />
			<input type="hidden" id="bankOrgNo" name="bankOrgNo" value="${orgNo}"/>
			<input type="hidden" id="addnotesLine" name="addnotesLine">
		</div>

		<br />
		<form name="form1" method="post" action="devbaseinfo.do?action=add" onclick="javascript:hideCalendar()">
			<input type="hidden" name="tmpfirst" value="0" />
			<input type="hidden" name="operateStatus" value="1" />
			<input type="hidden" name="zipType" value="0" />
			<input type="hidden" name="orgType" value="1">
			<input type="hidden" name="devClrString" id="devClrString"/>
			
			<table id="dev1" align="center" width="880" border="0" cellspacing="1" cellpadding="5" class="table1">
				<tr class="tr5">
					<td nowrap colspan="4">设备基本信息</td>
				</tr>
				<tr class="tr3">
					<td nowrap>设备编号：</td>
					<td nowrap>
						<input type="text" name="no" style="ime-mode: disabled; width: 130px;" onkeyup="value=value.replace(/[^\d]/g,'');" 
						onkeydown="if(event.keyCode==13)event.keyCode=9" maxlength="16" />&nbsp;*
					</td>
				</tr>
				<tr class="tr3">
					<td nowrap><%=resource.srcStr("System.devno_ip")%>：
					</td>
					<td nowrap>
						<input type="text" name="ip" id="ip" class="pane">
					</td>
				</tr>
				<tr class="tr3">
				    <td nowrap>所属网点：</td>
					<td nowrap>
						<input type="hidden" name="orgNo" id="_orgNo">
						<input type="text" class="paneGray" name="orgName" id="_orgName" readonly style="width:120px;">
						<a onclick="event.cancelBubble=true;" href="javascript:showOrg()">
						<img id="dimg" align="absmiddle" src="../images/org.gif" border="0"></a>&nbsp;*
					</td>
				</tr>
				<tr class="tr3">
					<td nowrap><%=resource.srcStr("Main.dev_address")%>：</td>
					<td nowrap>
						<input type="text" name="address" class="pane" style="width: 400px;" maxlength="80" />&nbsp;*
					</td>
				</tr>
				<tr class="tr3">
					<td nowrap><%=resource.srcStr("设备坐标")%>：</td>
					<td nowrap>
						x:<input type="text" name="x" class="pane" />&nbsp;
						y:<input type="text" name="y" class="pane" />
					</td>
				</tr>
				<tr class="tr3">
					<td nowrap>设备状态：</td>
					<td nowrap>
						<select name="status" size="1">
							<option value="1" selected>启用</option>
							<option value="2">停用</option>
							<option value="3">待运营</option>
							<option value="4">已撤销</option>
						</select>
					</td>
				</tr>
				<tr class="tr3">
					<td nowrap><%=resource.srcStr("System.whether_in_bank")%>：</td>
					<td nowrap>
						<select name="awayFlag" size="1">
							<option value="1"><%=resource.srcStr("System.in_bank")%></option>
							<option value="2" selected><%=resource.srcStr("System.out_bank")%></option>
						</select>&nbsp;*
					</td>
				</tr>
				<tr class="tr3">
					<td nowrap><%=resource.srcStr("Main.devType")%>：
					</td>
					<td nowrap>
						<select name="devType" id="devType" size="1">
							<c:forEach items="${typeList}" var="item" step="1">
								<option value="<c:out value="${item[0]}"/>">
									<c:out value="${item[1]}" />
								</option>
							</c:forEach>
						</select>&nbsp;*
					</td>
				</tr>
				<tr class="tr3">
					<td nowrap><%=resource.srcStr("Main.business_type")%>：</td>
					<td nowrap>
						<select name="workType" size="1">
							<option value="1" selected><%=resource.srcStr("System.self")%></option>
							<option value="2"><%=resource.srcStr("System.join")%></option>
						</select>&nbsp;*
					</td>
				</tr>
				<tr class="tr3">
					<td nowrap><%=resource.srcStr("System.install_type")%>：</td>
					<td nowrap>
						<select name="setupType" size="1">
							<option value="0" selected><%=resource.srcStr("System.hall")%></option>
							<option value="1"><%=resource.srcStr("System.wall")%></option>
						</select>&nbsp;*
					</td>
				</tr>
				<tr class="tr3">
					<td nowrap><%=resource.srcStr("System.wired_wireless")%>：</td>
					<td nowrap>
						<select name="netType" size="1" onchange="packetChange()">
							<option value="C" selected><%=resource.srcStr("System.wired")%></option>
							<option value="W"><%=resource.srcStr("System.wireless")%></option>
						</select>
					</td>
				</tr>
				<tr class="tr3">
				<td nowrap><%=resource.srcStr("System.devservice_com")%>：</td>
					<td nowrap>
						<select name="devService" size="1">
							<c:forEach items="${serviceList}" var="item" step="1">
								<option value="<c:out value="${item[0]}"/>" selected>
									<c:out value="${item[1]}" />
								</option>
							</c:forEach>
						</select>&nbsp;*
					</td>
				</tr>
				<tr class="tr3">
					<td nowrap><%=resource.srcStr("Main.virtualTeller")%>：</td>
					<td nowrap>
						<input type="text" name="virtualTellerNo" id="virtualTellerNo" maxlength="5" value="00000" />&nbsp;*
					</td>
				</tr>
				<tr class="tr3">
					<td nowrap>钞箱标准容量：</td>
					<td nowrap>
						<input type="text" name="cassetteStantardSize" id="cassetteStantardSize" 
							maxlength="5" value="2000" />&nbsp;*(单位：张)
					</td>
				</tr>
				<tr class="tr3">
					<td nowrap>设备最大装钞容量：</td>
					<td nowrap>
						<input type="text" name="devStantardSize" id="devStantardSize" 
							maxlength="5" value="80" />&nbsp;*(单位：万元)
					</td>
				</tr>
				<tr class="tr3">
				    <td nowrap>钞箱报警金额：</td>
					<td nowrap>
						<input type="text" name="rmbLevel" id="rmbLevel" maxlength="7" value="20000">&nbsp;*(单位：元)
					</td>
				</tr>
			</table>
			<br/>

			<p align="center">
				<input class="button" type="button" value='<%=resource.srcStr("Main.submit")%>' onClick="return checkForm()" />&nbsp;
				<input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>'  onclick="javascript:window.location='devbaseinfo.do?action=qry'" />
			</p>

		</form>
	</body>
</html>
