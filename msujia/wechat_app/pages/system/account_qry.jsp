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
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
<link href="../styles/displaytag.css" rel="stylesheet" type="text/css">
</head>


<body>
<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="Main.sure_delete" value='<%=resource.srcStr("Main.sure_delete")%>'/>
</div>

<script language="JavaScript">
function checkForm(){
	return true;
}
function restore(){
	  $('wechatName').value = "";
	  $('wechatId').value = "";
}
function del(obj){
	  if(confirm(document.getElementById('Main.sure_delete').value)){
	    window.location="account.do?action=del&id="+obj;
	  }
}
function mod(obj){
	  window.location="account.do?action=modPage&id="+obj;
}
function detail(obj){
	  window.location="account.do?action=detail&id="+obj;
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
		    herf = herf.replace(/d-(\d+)-p=(\d+)/g,"d-$1-p="+pageNum);//得到跳转页地址
		    //alert(herf);
		    window.location=herf;//转到跳转页
	}
</script>

<input type="hidden" id="Main.sure_delete" value='<%=resource.srcStr("Main.sure_delete")%>'/>
<input type="hidden" id="Main.pageLowerLimitPrompt" value='<%=resource.srcStr("Main.pageLowerLimitPrompt")%>'/>
<input type="hidden" id="Main.qryEmptyPrompt" value='<%=resource.srcStr("Main.qryEmptyPrompt")%>'/>
<input type="hidden" id="Main.input_correct_page" value='<%=resource.srcStr("Main.input_correct_page")%>'/>
<input type="hidden" id="Main.pageUpperLimitPrompt" value='<%=resource.srcStr("Main.pageUpperLimitPrompt")%>'/>
<input type="hidden" id="Login.op_no_per" value='<%=resource.srcStr("Login.op_no_per")%>'/>

<table width="100%" style="border:0px;border-bottom:1px solid #cccccc;">
  <tr> 
    <td nowrap><b>公众号管理：</b></td> 
    <td nowrap align="right">
       <input class="button" type="button" value='添加公众号' onclick="javascript:window.location='account.do?action=addPage'" style="width:110px;">&nbsp;
    </td>
  </tr>
</table>
<br>
<form name="form1" method="post" action="account.do?action=qry">
<table width="100%" border="0" cellspacing="1" cellpadding="3" class="table1">
  	<tr class="tr1">
    <td nowrap colspan="12">
      <%=resource.srcStr("System.query") %>：
    </td>
  	</tr>
    <tr class="tr3">
    <td nowrap>公众号名称：</td>
    <td nowrap>
	<input type="text" name="wechatName" style="width:130px;" maxlength="16" value="<c:out value="${actName}"/>" /></td>
    <td nowrap>微信号：</td>
    <td nowrap>
	<input type="text" name="wechatId" style="width:130px;" maxlength="16" value="<c:out value="${wechatNo}"/>" /></td>      	
	</td>
	</tr>
  <tr>
	  <td nowrap class="tr4" colspan="10" align="center">
	  <input class="button" type="submit" value='<%=resource.srcStr("Main.query")%>' onClick="return checkForm()">&nbsp;
      <input class="button" type="button" value='<%=resource.srcStr("Main.reset")%>' onclick="restore()">&nbsp;
    </td> 
  </tr>    
</table>
</form>

<table width="100%" border="0" cellspacing="0" cellpadding="3" class="table1" style="word-break:break-all">
  <tr class="tr1">
    <td nowrap colspan="8">
      <%=resource.srcStr("System.results") %>：&nbsp;&nbsp;&nbsp;
    </td>
  </tr>
</table>

<display:table name="requestScope.accountList" id="account" requestURI="account.do" cellspacing="1" cellpadding="3" class="table1" style="word-break:break-all" pagesize="15">
	<display:column title='<%=resource.srcStr("Main.op")%>'>
			<a href="javascript:mod('<c:out value="${account.id}"/>')"><%=resource.srcStr("System.mod")%></a>&nbsp;
        	<a href="javascript:del('<c:out value="${account.id}"/>')"><%=resource.srcStr("Main.delete")%></a>
    </display:column>
    <display:column title='公众号名称'>
    	<span style="align:center;width:100;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">
    		<a href="javascript:detail('<c:out value="${account.id}"/>')"><c:out value="${account.wechatName}"/></a>
    	</span>
    </display:column>
	<display:column title='微信号'>
		<span style="align:center;width:100;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">
			<c:out value="${account.wechatId}"/>
		</span>	
	</display:column>	
	<display:column title='微信号原始ID'>
		<span style="align:center;width:100;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">
			<c:out value="${account.id}"/>
		</span>
	</display:column>
	<display:column title='创建者'><c:out value="${account.creator}"/></display:column>	   	 
	<%--
	<display:column title='原始ID'><c:out value="${account.oldid}"/></display:column>
	<display:column title='认证情况'><c:out value="${account.null2}"/></display:column>	
	 --%>   	 	
</display:table>

</body>
</html>





