<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
	<title></title>
	<script type='text/javascript' src='../dwr/interface/sysParamService.js'></script>
	<script type='text/javascript' src='../dwr/engine.js'></script>
	<script type='text/javascript' src='../dwr/util.js'></script>
	
	<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
	<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
	<link href="../styles/common.css" rel="stylesheet" type="text/css">
</head>

<body>
<!--JavaScript 国际化标识符-->
	<div id="javascriptI18n">
		<%@ include file="../../scripts/common.jsp" %>
		<input type="hidden" id="System.have_special_char" value='<%=resource.srcStr("System.have_special_char")%>'/>
		<input type="hidden" id="System.num_institution" value='<%=resource.srcStr("System.num_institution")%>'/>
		<input type="hidden" id="System.input_maintainer_name" value='<%=resource.srcStr("System.input_maintainer_name")%>'/>
		<input type="hidden" id="System.orgname_longer_than" value='<%=resource.srcStr("System.srvname_longer_than")%>'/>
		<input type="hidden" id="System.addr_length_than" value='<%=resource.srcStr("System.addr_length_than")%>'/>
		<input type="hidden" id="System.contact_longer_than" value='<%=resource.srcStr("System.contact_longer_than")%>'/>
		<input type="hidden" id="System.tel_longer_than15" value='<%=resource.srcStr("System.tel_longer_than15")%>'/>
		<input type="hidden" id="System.tel_shorter_than" value='<%=resource.srcStr("System.tel_shorter_than")%>'/>
		<input type="hidden" id="System.phone_length" value='<%=resource.srcStr("System.phone_length")%>'/>
		<input type="hidden" id="System.fax_longer_than" value='<%=resource.srcStr("System.fax_longer_than")%>'/>
		<input type="hidden" id="System.fax_shorter_than" value='<%=resource.srcStr("System.fax_shorter_than")%>'/>
		<input type="hidden" id="System.input_error_email" value='<%=resource.srcStr("System.input_error_email")%>'/>
		<input type="hidden" id="System.email_longer_than" value='<%=resource.srcStr("System.email_longer_than")%>'/>
		<input type="hidden" id="Main.select" value='<%=resource.srcStr("Main.select")%>'/>
		<input type="hidden" id="System.parent_body" value='<%=resource.srcStr("System.srv_parent_body")%>'/>
		<input type="hidden" id="System.input_institution" value='<%=resource.srcStr("System.input_srv_institution")%>'/>
		<input type="hidden" id="System.institution_digital" value='<%=resource.srcStr("System.srv_institution_digital")%>'/>
		<input type="hidden" id="System.input_orgname" value='<%=resource.srcStr("System.input_srvname")%>'/>
		<input type="hidden" id="System.select_org_type" value='<%=resource.srcStr("System.select_srv_type")%>'/>
		<input type="hidden" id="System.select_parent_org" value='<%=resource.srcStr("System.select_parent_srv")%>'/>
		<input type="hidden" id="System.addr_empty" value='<%=resource.srcStr("System.addr_empty")%>'/>
		<input type="hidden" id="System.input_error_tel" value='<%=resource.srcStr("System.input_error_tel")%>'/>
		<input type="hidden" id="System.input_error_phone" value='<%=resource.srcStr("System.input_error_phone")%>'/>
		<input type="hidden" id="System.input_error_fax" value='<%=resource.srcStr("System.input_error_fax")%>'/>
		<input type="hidden" id="System.exists_org" value='<%=resource.srcStr("System.exists_org")%>'/>
		<input type="hidden" id="System.maintainer_length_than" value='<%=resource.srcStr("System.maintainer_length_than")%>'/>
	</div>
	
<script language="JavaScript">
var j$ = jQuery.noConflict();

j$().ready(function(){
	j$("#paramName").blur(function(){
		if(j$("#paramName").val()!='${sysParam.paramName}'){
			sysParamService.qrySysParamByParamName(j$("#paramName").val(),function(data){
				if(data!=null && data.length>0){
					alert("系统参数名已存在");
					j$("#paramName").val("");
				}
			});
		}
	});
});

function checkForm(){
	if(isNull(j$("#catalog").val())){
		alert("请选择参数类别");
		return false;
	}
	if(isNull(j$("#paramName").val())){
		alert("请填写参数名");
		return false;
	}else{
		if(getLength(j$("#paramName").val())>80){
			alert("参数名长度应小于80个字节");
			return false;
		}
	}
	if(!isNull(j$("#paramValue").val()) && getLength(j$("#paramValue").val())>80){
		alert("参数值长度应小于80个字节");
		return false;
	}
	if(!isNull(j$("#statement").val()) && getLength(j$("#statement").val())>100){
		alert("参数说明长度应小于100个字节");
		return false;
	}
	if(!isNull(j$("#description").val()) && getLength(j$("#description").val())>100){
		alert("描述长度应小于100个字节");
		return false;
	}
	document.form.submit();
	return true;
}
</script>


<br>
<form name="form" method="post" action="param.do?action=modSysparam">
	<input type="hidden" name="logicId" value="${sysParam.logicId }">
	<table align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1">
		<tr class="tr1">
		    <td nowrap colspan="2" align="center">修改系统参数信息</td>
		</tr>
		<tr class="tr3">
		<td align="right">参数类别:</td>
			<td>
				<input type="hidden" id="catalog" name="catalog" value="${sysParam.sysParamCatalog.catalog }" />${sysParam.sysParamCatalog.catalogName }
				<%-- <select id="catalog" name="catalog">
		  			<option value="">===请选择===</option>
		  			<c:forEach items="${sysParamCatalogList}" var="sysParamCatalog">
		  				<option value="${sysParamCatalog.catalog }" <c:out value="${sysParamCatalog.catalog==sysParam.sysParamCatalog.catalog?'selected':'' }"/>>${sysParamCatalog.catalogName }</option>
		  			</c:forEach>
		  		</select>&nbsp;* --%>
			</td>
		</tr>
		<tr class="tr3">
			<td align="right">参数名:</td>
			<td>
				<input type="hidden" name="paramName" id="paramName" style="width:200px;" value="${sysParam.paramName }">${sysParam.paramName }
			</td>
		</tr>
     	<tr class="tr3">
			<td align="right">参数值：</td>
			<td >
				<input type="text" name="paramValue" id="paramValue" style="width:200px;" value="${sysParam.paramValue }" onkeyup="value=value.replace(/[^\w]/g,'')">&nbsp;
			</td>
		</tr>
		<tr class="tr3">
			<td align="right">参数说明：</td>
			<td >
				<input type="text" name="statement" id="statement" style="width:200px;" value="${sysParam.statement }">&nbsp;
			</td>
		</tr>
		<tr class="tr3">
			<td align="right">描述：</td>
			<td >
				<input type="text" name="description" id="description" style="width:200px;" value="${sysParam.description }">&nbsp;
			</td>
		</tr> 	
	
	
	</table>    
	<p align="center">
    	<input class="button" type="button" value='<%=resource.srcStr("Main.submit")%>' onclick="return checkForm()">&nbsp;
   		<input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.location='param.do?action=qrySysparam';">
 	</p>
</form>
</body>
</html>