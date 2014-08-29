<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page import="java.text.DecimalFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<script type='text/javascript' src='../dwr/interface/orgService.js'></script>
<script type='text/javascript' src='../dwr/interface/stationService.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/orgSelector.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
</head>

<body>
<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<%@ include file="../../scripts/orgSelector.jsp" %>
<input type="hidden" id="System.maintainer_length_than" value='<%=resource.srcStr("System.maintainer_length_than")%>'/>
<input type="hidden" id="System.have_special_char" value='<%=resource.srcStr("System.have_special_char")%>'/>
<input type="hidden" id="System.input_maintainer_name" value='<%=resource.srcStr("System.input_maintainer_name")%>'/>
<input type="hidden" id="System.orgname_longer_than" value='<%=resource.srcStr("System.orgname_longer_than")%>'/>
<input type="hidden" id="System.addr_length_than" value='<%=resource.srcStr("System.addr_length_than")%>'/>
<input type="hidden" id="System.contact_longer_than" value='<%=resource.srcStr("System.contact_longer_than")%>'/>
<input type="hidden" id="System.tel_shorter_than" value='<%=resource.srcStr("System.tel_shorter_than")%>'/>
<input type="hidden" id="System.phone_length" value='<%=resource.srcStr("System.phone_length")%>'/>
<input type="hidden" id="System.fax_longer_than" value='<%=resource.srcStr("System.fax_longer_than")%>'/>
<input type="hidden" id="System.fax_shorter_than" value='<%=resource.srcStr("System.fax_shorter_than")%>'/>
<input type="hidden" id="System.input_error_email" value='<%=resource.srcStr("System.input_error_email")%>'/>
<input type="hidden" id="System.email_longer_than" value='<%=resource.srcStr("System.email_longer_than")%>'/>
<input type="hidden" id="System.parent_body" value='<%=resource.srcStr("System.parent_body")%>'/>
<input type="hidden" id="System.input_institution" value='<%=resource.srcStr("System.input_institution")%>'/>
<input type="hidden" id="System.input_orgname" value='<%=resource.srcStr("System.input_orgname")%>'/>
<input type="hidden" id="System.select_org_type" value='<%=resource.srcStr("System.select_org_type")%>'/>
<input type="hidden" id="System.select_parent_org" value='<%=resource.srcStr("System.select_parent_org")%>'/>
<input type="hidden" id="System.select_parent_org" value='<%=resource.srcStr("System.select_parent_org")%>'/>
<input type="hidden" id="System.addr_empty" value='<%=resource.srcStr("System.addr_empty")%>'/>
<input type="hidden" id="System.input_error_tel" value='<%=resource.srcStr("System.input_error_tel")%>'/>
<input type="hidden" id="System.input_error_phone" value='<%=resource.srcStr("System.input_error_phone")%>'/>
<input type="hidden" id="System.input_error_fax" value='<%=resource.srcStr("System.input_error_fax")%>'/>
</div>
<script language="JavaScript">
var j$ = jQuery.noConflict();
j$().ready(function(){
	//根据机构级别联动区域类型
	j$("#_orgGrade").change(function(){
		if (j$(this).val() == 4 || j$(this).val() == 5) {    //机构级别是“网点”
		    j$("#addressRegionTypeNoTr").show();
		} else {
			j$("#addressRegionTypeNoTr").hide();
		}
	}).change();
});

function checkType(){
	var tmpType = document.getElementById("orgType");
	var tmpUpper1 = document.getElementById("upper1");
	if(tmpType.value == 1){
 		tmpUpper1.disabled = true;
 		tmpUpper1.value="";
 	}
 	else{
 	tmpUpper1.disabled = false;
 	}	
}
function setOrg(obj1,obj2){
	$("orgFrame").style.display="none";    
  	$('_orgNo').value=obj1;
  	$('_orgName').value=obj2;
}
function checkForm(){
  if(isNull(form.orgNo.value)){
    alert(document.getElementById('System.input_institution').value);
    form.orgNo.select();
    return false;
  }
  if(document.getElementById("orgNo").value!=""){
  	var tmp = document.getElementById("orgNo").value;
	var aa = "%";
	if(tmp.indexOf(aa) > 0){
		alert(document.getElementById('System.have_special_char').value);
		return false;
	}
  }
  if(getLength(form.orgNo.value)!=8 && form.orgType.value=="1"){
    alert("机构编号只能为8位数字，请重新输入！");
    form.orgNo.select();
    return false;
  }
  if(isNull(form.orgName.value)){
  if($F('orgType_Query')==1 ){
    alert(document.getElementById('System.input_orgname').value);
    }else{alert(document.getElementById('System.input_maintainer_name').value);}
    form.orgName.select();
    return false;
  } 
  if(getLength(form.orgName.value)>80){
  if($F('orgType_Query')==1 ){
    alert(document.getElementById('System.orgname_longer_than').value);
    }else{alert(document.getElementById('System.maintainer_length_than').value);}
    form.orgName.select();
    return false;
  }
  if(!isNull(form.orgName.value)){
		  var parten;
         if($F('orgType_Query')==1 ){
			  parten = /^[a-zA-Z0-9\u4e00-\u9fa5][a-zA-Z0-9_\-\.\(\)\u4e00-\u9fa5]{0,79}$/;
			  if(!parten.exec(form.orgName.value)) {
			       alert('机构名称必须由汉字、字母、数字、下划线、横线、点号，英文左刮号和英文右刮号组成\n，开头只能是汉字、数字或者字母');
			       form.orgName.select();
		  	  	   return false;
			  } 
		 } else {
		 	  parten = /^[a-zA-Z0-9\u4e00-\u9fa5][a-zA-Z0-9_\-\.\u4e00-\u9fa5]{0,79}$/;
		 	  if(!parten.exec(form.orgName.value)) {
			       alert('维护商名称必须由汉字、字母、数字、下划线、横线、点号组成\n，开头只能是汉字、数字或者字母');
			  	   form.orgName.select();
		  	  	   return false;
			  }  
		 }
  }
  if(isNull(form.orgType.value)){
    alert(document.getElementById('System.select_org_type').value);
   	form.orgType.focus();
    return false;
  } 
  if(isNull(form.orgGrade.value)){
    alert("请选择机构级别!");
    form.orgGrade.focus()
    return false;
  }
  if($F('areaNo')=="" ){
	alert("请选择所在城市！");
	$('areaNo').focus();
	return false;
  }
	//网点区域检查
 	if ((j$('#_orgGrade').val() == 4 || j$('#_orgGrade').val() == 5) && j$('#addressRegionTypeNo').val().length == 0) {    //当机构级别是“网点”
		alert("请选择网点区域");
 		return false;
	}
  if($F('orgType_Query')==1 ){
  	if(isNull(form.upper1.value) && form.orgGrade.value != '1'){
		alert(document.getElementById('System.select_parent_org').value);
    	form._orgName.select();
    	return false;
  	}
  }
	if(form.x.value=="0.0" || form.y.value=="0.0" || isNull(form.x.value) || isNull(form.y.value)){
		alert("请您填写机构经纬度值!");
		return false;
	}
	if(!isNull(form.address.value)){
        if(getLength(form.address.value)>80){
            alert(document.getElementById('System.addr_length_than').value);
            form.address.select();
            return false;
        }
//      var parten = /^[a-zA-Z0-9\u4e00-\u9fa5][a-zA-Z0-9_\-\.\(\)\u4e00-\u9fa5]{0,79}$/;
//		if(!parten.exec(form.address.value)) {
//		       alert('地址必须由汉字、字母、数字、下划线、横线、\n点号，英文左刮号和英文右刮号组成，开头只能是汉字、数字或者字母'); 
//		       form.address.select();
//		       return false;
//		}  
  	}else if($F('orgType_Query')==1){
		alert("地址不能为空，请重新输入！");
        form.address.select();
        return false;
  	}
  
  if($F('orgType_Query')==2 ){
  	if(!isNull(form.linkMan.value)){
        if(getLength(form.linkMan.value)>10){
            alert(document.getElementById('System.contact_longer_than').value);
            form.linkMan.select();
            return false;
        }
        if(!isNull($F('linkMan'))){
         var parten;
		 parten = /^[a-zA-Z0-9\u4e00-\u9fa5][a-zA-Z0-9_\-\.\u4e00-\u9fa5]{0,9}$/;
		 if(!parten.exec($F('linkMan'))) {
			       alert('联系人必须由汉字、字母、数字、下划线、横线、点号组成\n，开头只能是汉字、数字或者字母');
			  	   form.linkMan.select();
		  	  	   return false;
		}  
  		}
  }   
  if(!isNull(form.telephone.value)){	
	  if(!phoneCheck(form.telephone)){ 
	      alert(document.getElementById('System.input_error_tel').value);
	      form.telephone.select();
	      return false;
	   }
  	  if(getLength(form.telephone.value)>15){
    		alert(document.getElementById('System.tel_longer_than15').value);
    		form.telephone.select();
    		return false;
  		 }
  	  if(getLength(form.telephone.value)<7){
    		alert(document.getElementById('System.tel_shorter_than').value);
    		form.telephone.select();
   	 		return false;
  		 }
	}
    if(!isNull(form.mobile.value)){	
	  if(!mobileCheck(form.mobile)){ 
	      alert(document.getElementById('System.input_error_phone').value);
	      form.mobile.select();
	      return false;
	   }
  	  if(getLength(form.mobile.value)<11){
    		alert(document.getElementById('System.phone_length').value);
    		form.mobile.select();
    		return false;
  		 }
	}
  if(!isNull(form.fax.value)){	
	  if(!phoneCheck(form.fax)){ 
	      alert(document.getElementById('System.input_error_fax').value);
	      form.fax.select();
	      return false;
	   }
  	  if(getLength(form.fax.value)>15){
    		alert(document.getElementById('System.fax_longer_than').value);
    		form.fax.select();
    		return false;
  		 }
  	  if(getLength(form.fax.value)<7){
    		alert(document.getElementById('System.fax_shorter_than').value);
    		form.fax.select();
   	 		return false;
  		 }
	}
	if(!isNull(form.email.value)){
    	var checkmail = form.email.value;
	    reEmail=/\w+\@\w+\.\w+/gi ;//正则表式如果符合要求就通过
      	var isOk=reEmail.test(checkmail);
   	    if(!isOk){
           alert(document.getElementById('System.input_error_email').value);
	       return false;
	   }
  	}
  	if(!isNull(form.email.value)){
        if(getLength(form.email.value)>40){
            alert(document.getElementById('System.email_longer_than').value);
            form.email.select();
            return false;
        }
  }
  }

  form.submit();
}
function showOrg(){
	if($("orgFrame").style.display=="block"){
		$("orgFrame").style.display="none";   
	}else{
		showOrgSelectorByOrgNo('dimg',j$("#bankOrgNo").val());
	}
}

//打开地图页面，用户点击地图获取坐标
function gainXY(){
	openWindow("map.do?action=gainXYMap&clrCenterNo=" + j$("#clrCenterNo").val() + "&orgNo=" + j$("#orgNo").val(),"点击获取坐标",900,600);
}
</script>


<br>
<%
	String orgType_Query=request.getParameter("orgType_Query")==null?"":request.getParameter("orgType_Query");
	request.setAttribute("orgType_Query",orgType_Query);
	String title = request.getParameter("title");
	//System.out.println("title=["+title+"]");
%>
<form name="form" method="post" action="org.do?action=mod">
<input type="hidden" name="tmpfirst" value="0">
<input type="hidden" name="title" value='<%= title%>'>
<input type="hidden" name="orgType_Query" value='<%=orgType_Query %>'>
<input type="hidden" name="old_xml" value='<c:out value="${old_xml}"/>'>
<input type="hidden" id="bankOrgNo" name="bankOrgNo"/>

		<table align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1">
							<tr class="tr1">
							    <td nowrap colspan="2" align="center"><%=resource.srcStr("System.mod")%><%= title%><%=resource.srcStr("System.info")%>&nbsp;(<%=resource.srcStr("System.required") %>)</td>
							</tr>	
 		<c:choose>
		    <c:when test="${orgType_Query ==1}">	
          					<tr class="tr3">
								<td width="30%" align="right"><%=resource.srcStr("System.org_no") %>：</td>
								<td width="70%"><input type="text" id="orgNo" name="orgNo" class="paneGray" readonly value='<c:out value="${org.no}"/>' >&nbsp;*</td>
							</tr>	    	    
		    </c:when>
		    <c:otherwise>
		    	<input type="hidden" name="orgNo" value="<c:out value="${org.no}"/>">	
		    </c:otherwise>    
		</c:choose>							
          					<tr class="tr3">
          					<c:if test="${orgType_Query ==1}">
								<td align="right"><%=resource.srcStr("System.org_name") %>：</td>
							</c:if>
							<c:if test="${orgType_Query !=1}">
								<td align="right"><%=resource.srcStr("System.maintainer_name") %>：</td>
							</c:if>	
								<td ><input type="text" name="orgName" value='<c:out value="${org.name}"/>' style="width:200px;"  maxLength="80">&nbsp;*</td>
							</tr>
 		<c:choose>
		    <c:when test="${orgType_Query ==1}">
		    	<input type="hidden" name="orgType" value="1">
		    				<tr class="tr3">
								<td align="right">机构级别：</td>
								<td>
									<select id="_orgGrade" name="orgGrade">
									<option value="">----请选择----</option>
									<c:if test="${userSession.roleNo eq 10001}">
										<option value="1" ${org.orgGrade.no == 1 ? 'selected' : ''}>总行</option>
										<option value="2" ${org.orgGrade.no == 2 ? 'selected' : ''}>分行</option>
									</c:if>
										<option value="3" ${org.orgGrade.no == 3 ? 'selected' : ''}>支行</option>
										<option value="4" ${org.orgGrade.no == 4 ? 'selected' : ''}>自助银行</option>
									</select>&nbsp;*
								</td>								
							</tr>
		    				<tr class="tr3">
								<td align="right">所属城市：</td>
								<td>
									<select id="areaNo" name="areaNo" class="pane">
										<option value="">--------请选择--------</option>
										<c:forEach items="${areaList}" var="area">
											<option value="${area.no }" <c:if test="${area.no==org.region.no}">selected</c:if>>${area.name }</option>
										</c:forEach>
									</select>&nbsp;*
								</td>
							</tr>
							<tr class="tr3">
								<td align="right"><%=resource.srcStr("System.parent_org") %>：</td>
								<td>
									<input type="hidden" class="pane" name="upper1Old" value='<c:out value="${org.superOrg.no}"/>'>
									<input type="hidden" class="pane" name="orgNameOld" value='<c:out value="${org.name}"/>'>
							  		<input type="hidden" class="pane" name="upper1" id="_orgNo" value='<c:out value="${org.superOrg.no}"/>'>
									<input type="text" class="paneGray" name="_orgName" id="_orgName" readonly style="width:120px;" value='<c:out value="${org.superOrg.name}"/>'>
									<a onclick="event.cancelBubble=true;" href="javascript:showOrg();"><img id="dimg" align="absmiddle" src="../images/org.gif" border="0"></a>&nbsp;*
						   		</td>
							</tr>
							<tr class="tr3">
							  <td align="right">机构状态：</td>
							      <td><select name="status" id="status" class="pane">
										<option value='1' <c:if test="${org.status==1}"> selected </c:if>>启用</option>
										<option value='2' <c:if test="${org.status==2}"> selected </c:if>>停用</option>
			                         </select>
			                      </td>
							</tr>
							<tr class="tr3">
								<td align="right">在离行标志：</td>
								<td>
									<SELECT  name="awayFlag" style="width:153;">
										<option value='1' <c:if test="${org.awayFlag==1}"> selected </c:if>>在行</option>
										<option value='2' <c:if test="${org.awayFlag==2}"> selected </c:if>>离行</option>
									</SELECT>
								</td>
							</tr>
							<tr class="tr3">
								<td align="right">经度：</td>
								<td>
									<input type="hidden" name="oldX" value="<fmt:formatNumber value='${org.x}' pattern='##########.##########'/>"/>
									<input type="text" id="x" name="x" <c:if test="${org.x!=0.0}">value="<fmt:formatNumber value='${org.x}' pattern='##########.##########'/>"</c:if> onkeyup="value=value.replace(/[^\d.]/g,'')" onblur="if(this.value.charAt(0)=='.'){alert('经度格式不正确!');this.select()}">&nbsp;*
									<!-- <img alt="获取坐标" src="../images/map/map.png" onclick="javascript:gainXY();" style="border: 0px; vertical-align: bottom;"> -->
								</td>
							</tr>
							<tr class="tr3">
								<td align="right">纬度：</td>
								<td>
									<input type="hidden" name="oldY" value="<fmt:formatNumber value='${org.y}' pattern='##########.##########'/>"/>
									<input type="text" id="y" name="y" <c:if test="${org.y!=0.0}">value="<fmt:formatNumber value='${org.y}' pattern='##########.##########'/>"</c:if> onkeyup="value=value.replace(/[^\d.]/g,'')" onblur="if(this.value.charAt(0)=='.'){alert('纬度格式不正确!');this.select()}">&nbsp;*
								</td>			
							</tr>
							<tr class="tr3">
								<td align="right"><%=resource.srcStr("System.address_nb")%>：</td>
								<td ><input type="text" name="address" value='<c:out value="${org.address}"/>' style="width:400px;" maxLength="80" onkeyup="value=value.replace(/[\s]/g,'')">&nbsp;*</td>
							</tr>
			</c:when>
		    <c:otherwise>	
		    	<input type="hidden" name="orgType" value="2">
		    	<input type="hidden" name="orgGrade" value="<c:out value="${org.orgGrade.no}"/>">		
							<tr class="tr3">
								<td align="right"><%=resource.srcStr("System.address_nb")%>：</td>
								<td ><input type="text" name="address" value='<c:out value="${org.address}"/>' style="width:400px;" maxLength="80"></td>
							</tr>		    	    						
							<tr class="tr3">
								<td align="right"><%=resource.srcStr("System.contact_nb") %>：</td>
								<td ><input type="text" name="linkMan" value='<c:out value="${org.linkMan}"/>' maxLength="10"></td>
							</tr>							
							<tr class="tr3">
								<td align="right"><%=resource.srcStr("System.phone_nb")%>：</td>
								<td ><input type="text" name="telephone" value='<c:out value="${org.telephone}"/>' style="ime-mode:disabled" onkeydown="if(event.keyCode==13)event.keyCode=9" onKeyPress="if (event.keyCode!=45 && (event.keyCode<48 || event.keyCode>57)) event.returnValue=false" maxLength="15"></td>
							</tr>							
          					<tr class="tr3">
								<td align="right"><%=resource.srcStr("System.mobile_phone_nb") %>：</td>
								<td ><input type="text" name="mobile" value='<c:out value="${org.mobile}"/>' style="ime-mode:disabled" onkeydown="if(event.keyCode==13)event.keyCode=9" onKeyPress="if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false" maxLength="20"></td>
							</tr>																			
          					<tr class="tr3">
								<td align="right"><%=resource.srcStr("System.fax_num") %>：</td>
								<td ><input type="text" name="fax" value='<c:out value="${org.fax}"/>' style="ime-mode:disabled" onkeydown="if(event.keyCode==13)event.keyCode=9" onKeyPress="if (event.keyCode!=45 && (event.keyCode<48 || event.keyCode>57)) event.returnValue=false" maxLength="15"></td>
							</tr>
          					<tr class="tr3">
								<td align="right"><%=resource.srcStr("System.email") %>：</td>
								<td ><input type="text" name="email" value='<c:out value="${org.email}"/>' onkeyup="value=value.replace(/[\s]/g,'')" maxLength="40"></td>
							</tr>
							<input type="hidden" name="status" value="1">
		    </c:otherwise>
		</c:choose>					

		</table>    
							<p align="center">
      							<input class="button" type="button" value='<%=resource.srcStr("Main.submit")%>' onclick="return checkForm()">&nbsp;
      							 <c:if test="${orgType_Query ==1}">
     							 <input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.location='org.do?action=qry&orgType_Query=1'">
   							     </c:if>
   							     <c:if test="${orgType_Query !=1}">
     							 <input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.location='org.do?action=qry&orgType_Query=2'">
   							     </c:if>
   							</p>
   						

</form>
</body>
</html>   	  


