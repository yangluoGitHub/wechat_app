<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page 
language="java"
contentType="text/xml; charset=GBK"
pageEncoding="GBK"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<select name="srcSelList" style="width:96%;">
		<option value="" >${devNum}<%=resource.srcStr("Share.devices")%></option>
      	 <c:forEach items='${devList}' var='item'>
      				  <option value="<c:out value='${item[0]}'/>"><c:out value='${item[0]}'/> | <c:out value='${item[1]}'/> | <c:out value='${item[2]}'/> | <c:out value='${item[3]}'/></option>
		  </c:forEach>
</select><font color="red"> *</font>