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
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
</head>

<body>
<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="System.input_maintainer_name" value='<%=resource.srcStr("System.input_maintainer_name")%>'/>
<input type="hidden" id="System.fault_email_format" value='<%=resource.srcStr("System.fault_email_format")%>'/>
</div>

<script language="JavaScript">
var j$ = jQuery.noConflict();
j$().ready(function(){
	j$(":text").addClass("pane");
	
});
function mainChange(value){ 
	
	//alert(j$("#pictureLink").width);
	//j$("#pictureLink").width=100;  
	//j$("#pictureLink").height=100;  
	//j$("#pictureLink").alt="";  
	//j$("#pictureLink").src=Value;  
	//j$("#pictureLink").style.display='block';  
  	
  } 
function checkForm(){
  if(isNull($F('commName'))){
    alert("请输入商品名称！");
    return false;
  }
  if(getLength($F('commName')) > 64) {
  	alert("名称不得超过64字节!");
  	$('storeName').select();
  	return false;
  }
  /*
  * check others
  */
  return true;
}
</script>
<br>

<form name="form1" method="post" action="storeCommInfo.do?action=add" enctype="multipart/form-data">
<table id="infoTable" align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1" >
 <tr class="tr1">
    <td nowrap colspan="2" align="center">添加商品信息</td>
  </tr>  
  <tr class="tr3">
    <td nowrap width="120">商品名称：</td>
    <td nowrap><input type="text" name="commName" maxlength="64">&nbsp;*不超过64个字符</td> 
  </tr> 
  <tr class="tr3">
    <td nowrap>商品分类：</td>
    <td nowrap>
			<select name="commClassificationId" class="inputline" style="width: 153px"
						<option value="">
							------请选择----
						</option>
						<c:forEach var="commCf" items="${commCfList}">
							<option value='<c:out value="${commCf[0]}"/>'>
								<c:out value="${commCf[2]}" />
							</option>
						</c:forEach>
					</select>
				</td>
  </tr>
  <tr class="tr3">
    <td nowrap>原价：</td>
    <td nowrap><input type="text" name="price"  maxlength="5">&nbsp;*</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>优惠价：</td>
    <td nowrap><input type="text" name="preferentialPrice"  maxlength="5">&nbsp;*</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>库存数量：</td>
    <td nowrap><input type="text" name="onhand"  maxlength="5">&nbsp;*个数</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>上传图片：</td>
    <td nowrap style="display:none"><img src=""  name="pictureLink" width="150" height="113" border="0" id="pictureLink" /></td>
    <td nowrap><input type="file" name="file" id="file" onChange="javascript:mainChange(this.value);">&nbsp;*.png.jpg</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>是否上架：</td>
    <td nowrap>
    <select name="onShelves" size="1">
		<option value="0" selected>不上架</option>
		<option value="1">上架</option>
	</select>
    &nbsp;*</td> 
  </tr>
  
</table>

    <p align="center">
      <input class="button" type="submit" value='<%=resource.srcStr("Main.submit")%>' onclick="return checkForm()">
      <input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.history.back();">
    </p>

</form>

</body>
</html>




