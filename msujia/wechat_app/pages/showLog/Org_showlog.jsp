<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page import="java.util.Date"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>

<head>
<meta http-equiv="Content-Type" content="text/html;charset=gbk" />
<title></title>
</head>
<script type='text/javascript' src='../dwr/interface/orgService.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>

<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/calendar3.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">

<body onload="javascript:getOrgName();getOrgGradebyOrgNo();">
<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="System.delete_bank_org" value='<%=resource.srcStr("System.delete_bank_org")%>'/>
</div>
<script language="JavaScript">

function getOrgName(){
		//if (form.orgNo.value==""){
		//	orgService.getOrgByNo(form.no.value,
		//		function getOrg(data){
		//			if (data!=null){
		//				form.address.value=data.address;
		//				form.orgName.value=data.superOrg.name;
		//				form.orgGradeName.value=data.orgGrade.name;
		//			}
		//		}
		//	);
		//}else{
		//if (form.title.value!=document.getElementById('System.delete_bank_org').value){
			orgService.getOrgByNo(form.orgNo.value,
				function getOrg(data){
					if (data!=null){
						//$("orgName").innerHTML=data.name;
						form.orgName.value=data.name;
					}
				}
			);
			if($("oldObject").value!=""){
				orgService.getOrgByNo(form.orgNoOld.value,
					function getOrg(data){
						if (data!=null){
							//$("orgNameOld").innerHTML=data.name;
							form.orgName.value=data.name;
						} else {
							$("orgNameOld").value="";
						}
					}
				);
			}
		//}
}

function getOrgGradebyOrgNo(){
	//if (form.title.value!=document.getElementById('System.delete_bank_org').value){
		if($("orgGradeNo").value!=null&&$("orgGradeNo").value!=""){
			orgService.getOrgGradebyOrgGradeNo($("orgGradeNo").value,
				function listOrgGrade(data){	
					if (data!=null){
						$("orgGradeName").value=data.name;
					}
				}
				);
		}
		if($("oldObject").value!=""){
			if($("orgGradeNoOld").value!=null&&$("orgGradeNoOld").value!=""){
				orgService.getOrgGradebyOrgGradeNo($("orgGradeNoOld").value,
					function listOrgGrade(data){	
						if (data!=null){
							$("orgGradeNameOld").value=data.name;
						} else {
							$("orgGradeNameOld").value="";
						}
					}
					);
				}
			}
	//}
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
<input type="hidden" name="title" value='<c:out value="${title}"/>'>
<input type="hidden" name="oldObject" value='<c:out value="${oldObject}"/>'>
<%
	request.setAttribute("flag",request.getParameter("flag"));
%>
						<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
								 <tr>
								  <td nowrap><b><%=resource.srcStr("System.detail_info")%>:</b></td>			    
								 </tr>
								  
						</table>
						
						<br>	
						
						<table align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1">
						  <tr class="tr1">
						    <td nowrap colspan="2" align="center"><c:out value="${title}"/></td>
						  </tr> 
						  <!--<c:if test="${logvo.operate.no=='audit'||logvo.operate.no=='refuse'}">
							 <td nowrap>审核人：<c:out value="${logvo.userName}"/></td>
							 <td nowrap>审核时间：<c:out value="${logvo.date}"/></td>
						  </c:if>
						  <c:if test="${logvo.operate.no=='beAudit'||logvo.operate.no=='beRefuse'}">
							 <td nowrap>操作人：<c:out value="${logvo.userName}"/></td>
							 <td nowrap>操作时间：<c:out value="${logvo.date}"/></td>
						  </c:if>-->
						  
						  <tr class="tr3">
						    <td nowrap><%=resource.srcStr("System.org_no") %>：</td>
						    <td nowrap>
						    <input type="text" name="no" class="<c:out value="${oldObject!=null&&objectvo.no!=oldObject.no?'redSpan':'graySpan'}"/>" value='<c:out value="${objectvo.no}"/>' readonly>
						    <br>
						    <input type="text" name="oldno" class="graySpan" value='<c:out value="${oldObject.no}"/>' readonly style="display: <c:out value="${oldObject!=null&&objectvo.no!=oldObject.no?'':'none'}"/>">
						    </td> 
						  </tr>
						  <tr class="tr3">
						    <td nowrap><%=resource.srcStr("System.org_name") %>：</td>
						    <td nowrap>
						    <input type="text" name="name" class="<c:out value="${oldObject!=null&&objectvo.name!=oldObject.name?'redSpan':'graySpan'}"/>" value='<c:out value="${objectvo.name}"/>' readonly>
						    <br>		    
						    <input type="text" name="oldname" class="graySpan" value='<c:out value="${oldObject.name}"/>' readonly style="display: <c:out value="${oldObject!=null&&objectvo.name!=oldObject.name?'':'none'}"/>"></td>
						  </tr>
							  <tr class="tr3">
							    <td nowrap><%=resource.srcStr("System.parent_org") %>：</td>
							    <td nowrap>
							    <input type="hidden" name="orgNo" class="paneGray" readonly value='<c:out value="${objectvo.superOrg.no}"/>' >
								<input type="text" name="orgName" class="<c:out value="${oldObject!=null&&objectvo.superOrg.no!=oldObject.superOrg.no?'redSpan':'graySpan'}"/>" readonly >&nbsp; 
							    <br>
							    <input type="hidden" name="orgNoOld" class="paneGray" readonly value='<c:out value="${oldObject.superOrg.no}"/>' >
								<input type="text" name="orgNameOld" class="graySpan" readonly style="display: <c:out value="${oldObject!=null&&objectvo.superOrg.no!=oldObject.superOrg.no?'':'none'}"/>"> 
							    </td>
							  </tr>
							  <tr class="tr3">
							    <td nowrap><%=resource.srcStr("System.org_level") %>：</td>
							    <td nowrap>
							    <input type="hidden" name="orgGradeNo" class="paneGray" readonly value='<c:out value="${objectvo.orgGrade.no}"/>' >
							    <input type="text" name="orgGradeName" class="<c:out value="${oldObject!=null&&objectvo.orgGrade.no!=oldObject.orgGrade.no?'redSpan':'graySpan'}"/>" readonly >&nbsp;
							    <br>
							    <input type="hidden" name="orgGradeNoOld" class="paneGray" readonly value='<c:out value="${oldObject.orgGrade.no}"/>' >
							    <input type="text" name="orgGradeNameOld" class="graySpan" readonly style="display: <c:out value="${(oldObject!=null&&objectvo.orgGrade.no!=oldObject.orgGrade.no)?'':'none'}"/>">
							    </td> 
							  </tr>
							  <tr class="tr3">
							    <td nowrap><%=resource.srcStr("System.address") %>：</td>
							    <td nowrap>
						    	<input type="text" name="address" class="<c:out value="${oldObject!=null&&objectvo.address!=oldObject.address?'redSpan':'graySpan'}"/>" style="width: 320px" value='<c:out value="${objectvo.address}"/>' readonly>
							    <br>
						    	<input type="text" name="addressold" class="graySpan" style="width: 320px" value='<c:out value="${oldObject.address}"/>' readonly style="display: <c:out value="${oldObject!=null&&objectvo.address!=oldObject.address?'':'none'}"/>">
							    </td>						    
							  </tr>
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
<input type="hidden" id="System.delete_bank_org" value='<%=resource.srcStr("System.delete_bank_org")%>'/>
</div>
