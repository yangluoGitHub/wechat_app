<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<select name=<c:out value="${selectName}"/> id=<c:out value="${selectName}"/>>
	 <option value=""></option>
	 <c:forEach items='${selectList}' var='item' step="1">
	 <option value="<c:out value='${item.no}'/>"><c:out value='${item.name}'/></option>			
	 </c:forEach>
</select>