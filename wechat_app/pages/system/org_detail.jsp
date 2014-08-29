<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<script type='text/javascript' src='../dwr/interface/orgService.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>

<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/orgSelector.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
</head>

<body>
<%@ include file="../../scripts/common.jsp" %>
<%@ include file="../../scripts/orgSelector.jsp" %>
<br>
<script language="JavaScript">
var j$ = jQuery.noConflict();
j$().ready(function(){
	j$("#_orgGrade").change(function(){
		if (j$(this).val() == 4 || j$(this).val() == 5) {    //机构级别是“网点”
			j$("#addressRegionTypeNoTr").show();
		
		} else {
			j$("#addressRegionTypeNoTr").hide();
		}
	}).change();
})
	
</script>
<form name="form">

		<table align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1">
			<tr class="tr1">
			    <td nowrap colspan="2" align="center">
			    <c:if test="${orgType_Query == 1}"><%=resource.srcStr("System.detail_bank")%></c:if>
			    <c:if test="${orgType_Query != 1}"><%=resource.srcStr("System.detail_maintainer_info")%></c:if>
			    </td>
			</tr>
 		<c:choose>
		    <c:when test="${orgType_Query == 1}">	
       			<tr class="tr3">
					<td width="30%" align="right"><%=resource.srcStr("System.org_no") %>：</td>
					<td width="70%">
						<c:out value="${org.no}"/>
					</td>
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
			<td >
				<c:out value="${org.name}"/>
			</td>
		</tr>
 		<c:choose>
		    <c:when test="${orgType_Query == 1}">
		    	<input type="hidden" name="orgType" value="1">
		    		<tr class="tr3">
		    			<td align="right">所属城市：</td>
		    			<td><c:out value="${org.region.name}"/></td>
		    		</tr>
					<tr class="tr3">
						<td align="right"><%=resource.srcStr("System.parent_org") %>：</td>
						<td><c:out value="${org.superOrg.name}"/></td>
					</tr>
					<tr class="tr3">
						<td align="right"><%=resource.srcStr("System.org_level") %>：</td>
						<td>
							<input type="hidden" class="pane" name="orgGrade" id="_orgGrade" value='<c:out value="${org.orgGrade.no}"/>'>
							<c:out value="${org.orgGrade.name}"/>
						</td>								
					</tr>
					<tr class="tr3">
					  <td align="right">机构状态：</td>
					   <td>
						   <c:if test="${org.status==1}">启用</c:if>
						   <c:if test="${org.status==2}">停用</c:if>
					   </td>
					</tr>
					<tr class="tr3">
						<td align="right">经度：</td>
						<td><c:if test="${org.x!=0.0 }"><fmt:formatNumber value="${org.x}" pattern='##########.##########'/></c:if></td>
					</tr>
					<tr class="tr3">
						<td align="right">纬度：</td>
						<td><c:if test="${org.y!=0.0 }"><fmt:formatNumber value="${org.y}" pattern='##########.##########'/></c:if></td>
					</tr>	
					<tr class="tr3">
						<td align="right">地址：</td>
						<td><c:out value="${org.address}"/></td>
					</tr>
			</c:when>
		    <c:otherwise>			
				<tr class="tr3">
					<td align="right"><%=resource.srcStr("System.address_nb")%>：</td>
					<td ><input type="text" name="address" readonly value='<c:out value="${org.address}"/>' style="width:400px;"></td>
				</tr>		    	    						
				<tr class="tr3">
					<td align="right"><%=resource.srcStr("System.contact_nb") %>：</td>
					<td ><input type="text" name="linkMan" readonly value='<c:out value="${org.linkMan}"/>'></td>
				</tr>							
				<tr class="tr3">
					<td align="right"><%=resource.srcStr("System.phone_nb")%>：</td>
					<td ><input type="text" name="telephone" value='<c:out value="${org.telephone}"/>' style="ime-mode:disabled" readonly></td>
				</tr>							
       					<tr class="tr3">
					<td align="right"><%=resource.srcStr("System.mobile_phone_nb") %>：</td>
					<td ><input type="text" name="mobile" value='<c:out value="${org.mobile}"/>' style="ime-mode:disabled" readonly></td>
				</tr>																			
       					<tr class="tr3">
					<td align="right"><%=resource.srcStr("System.fax_num") %>：</td>
					<td ><input type="text" name="fax" value='<c:out value="${org.fax}"/>' readonly></td>
				</tr>
       					<tr class="tr3">
					<td align="right"><%=resource.srcStr("System.email") %>：</td>
					<td ><input type="text" name="email" value='<c:out value="${org.email}"/>' readonly></td>
				</tr>
		    </c:otherwise>
		</c:choose>					

		</table>    
		<p align="center">
 			<input class="button" type="button" value='<%=resource.srcStr("Main.back")%>' onclick="javascript:window.history.back();">
		</p>
</form>
</body>
</html>   	  

<!--javascript 国际化标识符-->
<div id="javascriptI18n">
</div>
