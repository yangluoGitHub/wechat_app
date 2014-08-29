<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>
<%@ page import="java.util.Set"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>



<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
	<title></title>
	<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
	<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
	<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
	<script type='text/javascript' src='../dwr/engine.js'></script>
	<script type='text/javascript' src='../dwr/util.js'></script>
	<script type='text/javascript' src='../dwr/interface/roleService.js'></script>
	
	<link href="../styles/common.css" rel="stylesheet" type="text/css">
	<link href="../styles/displaytag.css" rel="stylesheet" type="text/css">
</head>

 
<body>
<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="Main.sure_delete" value='<%=resource.srcStr("Main.sure_delete")%>'/>
<input type="hidden" id="System.sure_reset_psw" value='<%=resource.srcStr("System.sure_reset_psw")%>'/>
<input type="hidden" id="Main.pageLowerLimitPrompt" value='<%=resource.srcStr("Main.pageLowerLimitPrompt")%>'/>
<input type="hidden" id="System.delete_self" value='<%=resource.srcStr("System.delete_self")%>'/>
<input type="hidden" id="Main.qryEmptyPrompt" value='<%=resource.srcStr("Main.qryEmptyPrompt")%>'/>
<input type="hidden" id="Main.input_correct_page" value='<%=resource.srcStr("Main.input_correct_page")%>'/>
<input type="hidden" id="Main.pageUpperLimitPrompt" value='<%=resource.srcStr("Main.pageUpperLimitPrompt")%>'/>
<input type="hidden" id="Login.op_no_per" value='<%=resource.srcStr("Login.op_no_per")%>'/>
</div>

<script language="JavaScript">
var j$ = jQuery.noConflict();
j$().ready(function(){
	j$("#user").rowHilight();
});

//增加一条记录

function add(){
  	form1.action="user.do?action=addPage&opNoCondition="+j$("#opNo").val()+"&roleNoCondition="+j$("#roleNo_Query").val();
    form1.submit();
}

//删除一条记录

function del(userNo,roleNo,userName){
   if( $('roleNo_Query').value ==200) {
     <%  Set authButton = (Set)request.getSession().getAttribute("_Auth_Button");
     if (!authButton.contains("user.do?action=del&roleNo_Query=200") ) { %>
     alert(document.getElementById('Login.op_no_per').value);return ;
     <%  } %> 
  } else
  if( $('roleNo_Query').value ==100) {
     <%  Set authButton1 = (Set)request.getSession().getAttribute("_Auth_Button");
     if (!authButton1.contains("user.do?action=del&roleNo_Query=100") ) { %>
     alert(document.getElementById('Login.op_no_per').value);return ;
     <%  } %> 
  } else {
     <%  Set authButton2 = (Set)request.getSession().getAttribute("_Auth_Button");
     if (!authButton2.contains("user.do?action=del") ) { %>
     alert(document.getElementById('Login.op_no_per').value);return ;
     <%  } %> 
  }
  if(confirm(document.getElementById('Main.sure_delete').value)){
  	if(userNo == $('OpAccount').value){
  		alert(document.getElementById('System.delete_self').value);
  		return false;
  	}
  	form1.action="user.do?action=del&no="+userNo+"&roleNo="+roleNo+"&userName="+userName+"&opNoCondition="+j$("#opNo").val()+"&roleNoCondition="+j$("#roleNo_Query").val();
  	form1.submit();
  }
}

//进入修改页面
function mod(obj){
  	form1.action="user.do?action=modPage&no="+obj+"&opNoCondition="+j$("#opNo").val()+"&roleNoCondition="+j$("#roleNo_Query").val();
	form1.submit();
}

//进入详细信息页面
function detail(obj1,obj2){
	form1.action="user.do?action=detail&no="+obj1+"&roleNo="+obj2+"&opNoCondition="+j$("#opNo").val()+"&roleNoCondition="+j$("#roleNo_Query").val();
	form1.submit();
}

//进入重置密码页面	
function resetPasswd(no,roleNo){
    <%  Set authButton4 = (Set)request.getSession().getAttribute("_Auth_Button");
     if (!authButton4.contains("user.do?action=resetPasswd") ) { %>
     alert(document.getElementById('Login.op_no_per').value);return ;
    <%  } %> 
  if(confirm(document.getElementById('System.sure_reset_psw').value)){
    form1.action="user.do?action=resetPasswd&no="+no+"&roleNo="+roleNo+"&opNoCondition="+j$("#opNo").val()+"&orgNoCondition="+j$("#_orgNo").val()+"&roleNoCondition="+j$("#roleNo_Query").val();
    form1.submit();  
  }
}

//进入解锁页面
function unlock(obj){
	if($('roleNo_Query').value!=100 && $('roleNo_Query').value!=200){
  		form1.action="user.do?action=unlock&no="+obj+"&opNoCondition="+j$("#opNo").val()+"&orgNoCondition="+j$("#_orgNo").val()+"&roleNoCondition="+j$("#roleNo_Query").val();
  	}else{
  		form1.action="user.do?action=unlock&roleNo_Query="+$('roleNo_Query').value+"&no="+obj+"&opNoCondition="+j$("#opNo").val()+"&orgNoCondition="+j$("#_orgNo").val()+"&roleNoCondition="+j$("#roleNo_Query").val();
  	}
	form1.submit();
}

function listRole(data){
	DWRUtil.removeAllOptions($("roleNo_Query"));
	DWRUtil.addOptions($("roleNo_Query"),[{no:'', name:''}],"no","name");
	if(data != null ) {
		for(i=0;i<data.length;i++){
			DWRUtil.addOptions($("roleNo_Query"),[{name:data[i].name,no:data[i].no}],"no","name");
		}
	}
} 

function restore(){
  $('opNo').value = "";
  $('orgNo_Query').value = "";
  $('orgName_Query').value= "";
  if($('roleNo_Query').value!=100 && $('roleNo_Query').value!=200){
  	 $('roleNo_Query').value = "";
  }
}
function go(){
	var pageNum = document.getElementById('curPages').value;
	//alert(pageNum);//获取跳转页码
	var obj=document.getElementsByTagName("a");
	var herf=new String("");
	var k = obj.length - 1 ;
    var ahref=obj[k]; //获取页面上最后一个链接地址
    var _ahref=obj[k-1]; //获取页面上倒数第二个链接地址
    var change = 0;//对末页地址的修正值=0
    herf=""+ahref;
    _herf=""+_ahref;
    //alert(herf);
    var m=herf.split("-p=");
    var k=_herf.split("-p=");
   	if(k.length>1){
   		var j = k[1].split("&");
   		if(parseInt(j[0])==1){
   			change = 1;//当倒数第二个链接是首页时，修正值=1
   		}
   	}
    if(m.length>1){
    	var n = m[1].split("&");
    	var totalNum = n[0];
    	totalNum = parseInt(totalNum) + change;
	    if(isNull(pageNum))
		{	
			alert(document.getElementById('Main.qryEmptyPrompt').value);
			document.getElementById('curPages').select();
			return false;
		}
		else if(!isInteger(pageNum))
		{	
			alert(document.getElementById('Main.input_correct_page').value);
			document.getElementById('curPages').select();
			return false;	
		}
		else if(parseInt(pageNum)<=0)
		{	
			alert(document.getElementById('Main.pageLowerLimitPrompt').value);
			document.getElementById('curPages').select();
			return false;
		}
		else if(parseInt(pageNum)>parseInt(totalNum))
		{	
			alert(document.getElementById('Main.pageUpperLimitPrompt').value);
			document.getElementById('curPages').select();
			return false;
		}
    }
    herf = herf.replace(/&d-(\d+)-p=(\d+)/g,"&d-$1-p="+pageNum);//得到跳转页地址
    window.location=herf;//转到跳转页
}

</script>



<form name="form1" method="post" action="user.do?action=qry">
	<input type="hidden" name="currentPage" value="<c:out value="${page.currentPage}"/>">
	<input type="hidden" name="pageSize" value="50">
	<input type="hidden" name="opName" value="<c:out value="${OpName}"/>">
	<input type="hidden" name="OpAccount" value="<c:out value="${OpAccount}"/>">

<%
	String roleNo_Query=request.getParameter("roleNo_Query")==null?"":request.getParameter("roleNo_Query");
	//String orgNo_Query=request.getParameter("orgNo_Query")==null?"":request.getParameter("orgNo_Query");
	//String orgName_Query=request.getParameter("orgName_Query")==null?"":request.getParameter("orgName_Query");
	request.setAttribute("roleNo_Query",roleNo_Query);
	String title = resource.srcStr("System.user");
%>

<input type="hidden" name="title" value='<%= title%>'>

<table width="100%" style="border:0px;border-bottom:1px solid #cccccc;">
  <tr> 
    <td nowrap><b><%=title %><%=resource.srcStr("System.info") %>：</b></td> 
    <td nowrap align="right">
       <input class="button" type="button" value='<%=resource.srcStr("System.add")%><%=title %>' onclick="javascript:add()" style="width:100px;">&nbsp;
    </td>
  </tr>
</table>

<br>

<table width="100%" border="0" cellspacing="1" cellpadding="3" class="table1">
  <tr class="tr4">
	 <td nowrap>
	 	人员编号：
	 	<input type="text" id="opNo" name="opNo" value="${opNo }"/>
		
    	<%=resource.srcStr("System.role") %>：
    	<select id="roleNo_Query" name="roleNo_Query" style="width:100px">
	    	<option value=""></option>
	    	<c:forEach items="${roleList}" var="role">
	      		<option value="<c:out value="${role[0]}"/>" <c:out value="${role[0]==roleNo_Query ? 'selected' : ''}"/>><c:out value="${role[1]}"/></option>
	    	</c:forEach>
    	</select>&nbsp;&nbsp;  
        
    	<input class="button" type="submit" value='<%=resource.srcStr("Main.query")%>'>&nbsp;
    	<input class="button" type="button" value='<%=resource.srcStr("Main.reset")%>' onclick="restore();">
    </td>
  </tr>
</table>


<br>

<table width="100%" border="0" cellspacing="0" cellpadding="3" class="table1" style="word-break:break-all">
  <tr class="tr1">
    <td nowrap colspan="8">
		查询结果 ：&nbsp;&nbsp;&nbsp;
    </td>
  </tr>
</table>
<!-- 0:b.no, 1:b.name, 2:b.sysRole.no, 3:b.sysRole.name, 4:b.status -->
<display:table name="userList" id="user" requestURI="user.do" cellspacing="1" cellpadding="3" class="table1" style="word-break:break-all" pagesize="15">
	<display:column title='操作' class="tr3">
		<a href="#" onclick="javascript:mod('<c:out value="${user[0]}"/>');return false;">修改</a>&nbsp;
		<c:if test="${user[2] ne 10001 && user[0] ne userSession.account}">
        	<a href="#" onclick="javascript:del('<c:out value="${user[0]}"/>','<c:out value="${user[2]}"/>','<c:out value="${user[1]}"/>');return false;">删除</a>
		</c:if>
        <c:if test="${user[4] eq 2}">
        	<a href="#" onclick="javascript:unlock('<c:out value="${user[0]}"/>');return false;">解锁</a>
        </c:if>
	</display:column>
	 	<display:column title='登录名' class="tr3"><c:out value="${user[0]}"/></display:column>
	    <display:column title='姓名' class="tr3">
	    	<a href="javascript:detail('<c:out value="${user[0]}"/>','<c:out value="${roleNo_Query}"/>')" ><c:out value="${user[1]}"/></a>
	    </display:column>
	    <display:column title='所属角色' class="tr3"><c:out value="${user[3]}"/></display:column>
	    <display:column title='用户状态' class="tr3">
	    	<c:if test="${user[4]==0}"><c:out value="停用"/></c:if>
	    	<c:if test="${user[4]==1}"><c:out value="启用"/></c:if>
	    	<c:if test="${user[4]==2}"><c:out value="锁定"/></c:if>
	    </display:column>
	    <c:if test="${userSession.roleNo eq 10001}">
	    <display:column title='重置密码' class="tr3">
	    	<%-- <c:if test="${userSession.roleNo eq 10001 || userSession.roleNo eq 10002}"> --%>
	    		<a href="#" onclick="javascript:resetPasswd('<c:out value="${user[0]}"/>','<c:out value="${user[2]}"/>');return false;">重置密码</a>
	    </display:column>
	    </c:if>
</display:table>

</form>
</body>

</html>
