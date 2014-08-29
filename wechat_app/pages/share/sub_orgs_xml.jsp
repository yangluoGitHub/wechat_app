<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<select name=<c:out value="projectOrg"/> id=<c:out value="projectOrg"/>>
	 <option value=""><%=resource.srcStr("Share.select_branch") %></option>
	 <c:forEach items='${selectList}' var='item' step="1">
	 <option value="<c:out value='${item[0]}'/>"><c:out value='${item[1]}'/></option>			
	 </c:forEach>
</select>