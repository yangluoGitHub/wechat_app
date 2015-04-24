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
  return true;
}
</script>
<br>

<form name="form1" method="post" action="storeCommInfo.do?action=mod" enctype="multipart/form-data">
<table id="infoTable" align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1" >
<input type="hidden" id="id" name="id" value='${commInfoVO.id }'/>
<input type="hidden" id="storeId" name="storeId" value='${commInfoVO.storeInfoVO.id }'/>
<input type="hidden" id="oldPictureLink" name="oldPictureLink" value='${commInfoVO.pictureLink }'/>
 <tr class="tr1">
    <td nowrap colspan="2" align="center">修改商品信息</td>
  </tr>  
  <tr class="tr3">
    <td nowrap width="120">商品名称：</td>
    <td nowrap><input type="text" name="commName" maxlength="64" value="${commInfoVO.name }">&nbsp;*不超过60个字符</td> 
  </tr> 
   <tr class="tr3">
    <td nowrap>商品分类：</td>
    <td nowrap>
					<select name="commClassificationId" class="inputline" style="width: 153px"
						<option value="">
							------请选择----
						</option>
						<c:forEach var="commCf" items="${commCfList}">
							<option value="<c:out value='${commCf[0]}'/>" ${commCf[0] == commInfoVO.storeCommClassificationInfoVO.id ? 'selected' : ''}><c:out value="${commCf[2]}"/></option>
						</c:forEach>
					</select>
				</td>
  </tr>
  <tr class="tr3">
    <td nowrap>原价：</td>
    <td nowrap><input type="text" name="price"  maxlength="5" value="${commInfoVO.price }">&nbsp;*元</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>优惠价：</td>
    <td nowrap><input type="text" name="preferentialPrice"  maxlength="5" value="${commInfoVO.preferentialPrice }">&nbsp;*元</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>库存数量：</td>
    <td nowrap><input type="text" name="onhand"  maxlength="5" value="${commInfoVO.onhand }">&nbsp;*个数</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>上传新图片：</td>
    <td nowrap><input type="file" name="file" id="file">&nbsp;*.png.jpg</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>是否上架：</td>
    <td nowrap>
    <select id="onShelves" name="onShelves" style="width: 108px">
    		<option <c:out value="${commInfoVO.onShelves==0?'selected':''}"/> value="0">不上架</option>
    		<option <c:out value="${commInfoVO.onShelves==1?'selected':''}"/> value="1">上架</option>
    	</select>&nbsp;*
    </td> 
  </tr>
</table>

    <p align="center">
      <input class="button" type="submit" value='<%=resource.srcStr("Main.submit")%>' onclick="return checkForm()">
      <input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.history.back();">
    </p>

</form>

</body>
</html>




