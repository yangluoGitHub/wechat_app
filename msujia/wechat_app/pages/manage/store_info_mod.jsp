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
	if(isNull($F('storeName'))){
    alert("请输入门店名称！");
    return false;
  }
  if(getLength($F('storeName')) > 60) {
  	alert("名称不得超过60字节!");
  	$('storeName').select();
  	return false;
  }
  if(isNull($F('storeNo'))){
    alert("请输入门店编号！");
    return false;
  }
  return true;
}
</script>
<br>

<form name="form1" method="post" action="storeInfo.do?action=mod">
<table id="infoTable" align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1" >
<input type="hidden" id="id" name="id" value='${sInfoVO.id }'/>
 <tr class="tr1">
    <td nowrap colspan="2" align="center">修改门店信息</td>
  </tr>  
  <tr class="tr3">
    <td nowrap width="120">门店名称：</td>
    <td nowrap><input type="text" name="storeName" maxlength="64" value="${sInfoVO.storeName }">&nbsp;*不超过60个字符</td> 
  </tr> 
  <tr class="tr3">
    <td nowrap>门店编号：</td>
    <td nowrap><input type="text" name="storeNo" maxlength="64" value="${sInfoVO.storeNo }">&nbsp;*字母和数字、下划线组成，不超过60字符</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>门店电话：</td>
    <td nowrap><input type="text" name="phone"  maxlength="20" value="${sInfoVO.phone }">&nbsp;*</td> 
  </tr>
   <tr class="tr3">
    <td nowrap>门店类型：</td>
    <td nowrap>
					<select name="storeClassificationId" class="inputline" style="width: 153px"
						<option value="">
							----------
						</option>
						<c:forEach var="scf" items="${scfList}">
							<option value="<c:out value='${scf.id}'/>" ${scf.id == sInfoVO.storeClVO1.id ? 'selected' : ''}><c:out value="${scf.clName}"/></option>
						</c:forEach>
					</select>
				</td>
  </tr>
  <tr class="tr3">
    <td nowrap>营业时间：</td>
    <td nowrap><input type="text" name="businessHours"  maxlength="128" value="${sInfoVO.businessHours }">&nbsp;*上午10:00到下午6:00</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>起送价：</td>
    <td nowrap><input type="text" name="flagFallPrice"  maxlength="5" value="${sInfoVO.flagFallPrice }">&nbsp;*元</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>配送费：</td>
    <td nowrap><input type="text" name="deliveryCharges"  maxlength="20" value="${sInfoVO.deliveryCharges }">&nbsp;*元</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>预计送达时间：</td>
    <td nowrap><input type="text" name="deliveryTime"  maxlength="5" value="${sInfoVO.deliveryTime }">&nbsp;*分钟</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>服务半径：</td>
    <td nowrap><input type="text" name="serviceRadius"  maxlength="36" value="${sInfoVO.serviceRadius }">&nbsp;*KM</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>配送区域：</td>
    <td nowrap><input type="text" name="deliveryArea"  maxlength="128" value="${sInfoVO.deliveryArea }">&nbsp;*</td> 
  </tr>
    <tr class="tr3">
    <td nowrap>登录密码：</td>
    <td nowrap><input type="text" name="passwd"  maxlength="50" value="${sInfoVO.passwd }">&nbsp;*</td> 
  </tr>
    <tr class="tr3">
    <td nowrap>门店地址：</td>
    <td nowrap><input type="text" name="address"  maxlength="200" value="${sInfoVO.address }">&nbsp;*</td> 
  </tr>
  </tr>
    <tr class="tr3">
    <td nowrap>是否启用：</td>
    <td nowrap>
    <select id="onLine" name="onLine" style="width: 108px">
    		<option <c:out value="${sInfoVO.onLine==0?'selected':''}"/> value="0">不启用</option>
    		<option <c:out value="${sInfoVO.onLine==1?'selected':''}"/> value="1">启用</option>
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




