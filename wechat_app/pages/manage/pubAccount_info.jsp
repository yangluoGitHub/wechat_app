<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>

<link href="../styles/jquery-ui.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/jquery-ui.min.js" type="text/javascript"></script>
<link href="../styles/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript">
$(function() {
    $("#tabs").tabs({
      event: "click"
    });
  });
</script>
</head>
<body>
<div id="tabs">
  <ul>
    <li><a href="#tabs-1">�˺���Ϣ</a></li>
    <li><a href="#tabs-2">URL&Token</a></li>
    <li><a href="#tabs-3">��ϵ��</a></li>
  </ul>
  <div id="tabs-1">
     <p><br/></p>
     <p>
        <tr class="tr3">
    	<td nowrap width="120">���ں����ƣ�</td>
    	<td nowrap><c:out value="${publicAccount.wechatName}"/></td> 
  	</tr> 
  </p>
  <p>
  	<tr class="tr3">
    	<td nowrap>΢�źţ�</td>
    	<td nowrap><c:out value="${publicAccount.wechatId}"/></td> 
  	</tr>
  </p>
  <p>
  	<tr class="tr3">
    	<td nowrap>΢�ź�ԭʼID��</td>
    	<td nowrap><c:out value="${publicAccount.id}"/></td> 
  	</tr>
   </p>
   <p><tr class="tr3">
    <td nowrap>���ں����ͣ�</td>
    <td nowrap>
		<c:if test="${publicAccount.wechatType == 1}"><c:out value="�����"/></c:if>
		<c:if test="${publicAccount.wechatType == 2}"><c:out value="���ĺ�"/></c:if>
	</td> 
  </tr></p>
  </div>
  <div id="tabs-2">
  	<p><br/></p>
  	<p><tr class="tr3">
    <td nowrap>URL��</td>
    <td nowrap><c:out value="${publicAccount.url}"/></td> 
  </tr></p>
  	<p><tr class="tr3">
    <td nowrap>TOKEN��</td>
    <td nowrap><c:out value="${publicAccount.token}"/></td> 
  </tr></p>
  </div>
  <div id="tabs-3">
  	<p><br/></p>
  	<p></tr>
    <tr class="tr3">
    <td nowrap>�����ߣ�</td>
    <td nowrap><c:out value="${publicAccount.creator}"/></td> 
  </tr></p>
  </div>
</div>
</body>
</html>
