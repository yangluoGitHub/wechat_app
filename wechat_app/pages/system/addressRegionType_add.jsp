<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
	<link href="../styles/jquery-ui.css" rel="stylesheet"type="text/css"/>
	<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
	<script language="JavaScript" src="../scripts/json2.js" type="text/javascript"></script>
	<script src="../scripts/jquery-ui.min.js"></script>
	
	<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
	<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
	<link href="../styles/common.css" rel="stylesheet" type="text/css">
	
	<script type='text/javascript' src='../dwr/interface/dispatchService.js'></script>
	<script type='text/javascript' src='../dwr/interface/addressRegionTypeService.js'></script>
	<script type='text/javascript' src='../dwr/engine.js'></script>
</head>

<body>
<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="System.input_dev_typenum" value='<%=resource.srcStr("System.input_dev_typenum")%>'/>
<input type="hidden" id="System.devtype_name_than" value='<%=resource.srcStr("System.devtype_name_than")%>'/>
<input type="hidden" id="System.select_own_brand" value='<%=resource.srcStr("System.select_own_brand")%>'/>
<input type="hidden" id="System.select_own_type" value='<%=resource.srcStr("System.select_own_type")%>'/>
</div>

<script language="JavaScript">
var j$ = jQuery.noConflict();

j$().ready(function(){
	//样式
	j$("#districtNo").onlyPositiveInteger();//只能输入正整数
	j$("#districtName").blur(function(){
		var districtNo = j$.trim(j$("#districtNo").val());
		var districtName = j$.trim(j$("#districtName").val());
		addressRegionTypeService.isExitedRegionName(districtNo,districtName,function(data){
			if(data == true){
				alert("该区域名称已存在！");
				j$("#districtName").val("");
				j$("#districtName").select();
				j$("#districtName").focus();
			}
		})
	});
});
function checkForm(){
  if(isNull(j$("#districtName").val()))
  {
 	alert("请填写区域名称！");
  	j$("#districtName").select();
  	j$("#districtName").focus();
  	return false;
  }
  else if( !checktextLen(document.form1.districtName,20) )
  {
  	alert("区域名称输入过长!");
  	j$("#districtName").select();
  	j$("#districtName").focus();
  	return false;
  }
  form1.submit();
  return true;
}
</script>

<br>

<form name="form1" method="post" action="addressRegionType.do?action=add">
<table width="650" align="center" border="0" cellspacing="1" cellpadding="6" class="table1">
 <tr class="tr1">
    <td nowrap colspan="2" align="center"><%=resource.srcStr("添加区域信息")%></td>
  </tr>  
  <tr class="tr3">
    <td nowrap>区域编号：</td>
    <td><input type="hidden" name="districtNo" id="districtNo" value="${districtNo}"/><c:out value="${districtNo}"/></td>
  </tr>
  <tr class="tr3">
    <td nowrap>区域名称：</td>
    <td><input type="text" name="districtName" id="districtName"  maxlength="20"/>&nbsp;*</td>
  </tr>
 </table>
 <p align="center">
      <input class="button" type="button" value='<%=resource.srcStr("Main.submit")%>' onclick="return checkForm()">&nbsp;
      <input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.history.back();">
  </p>

</form>

</body>
</html>




