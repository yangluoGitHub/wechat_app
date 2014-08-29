<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>
<%@ page import="java.util.Set"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
	<title></title>
	<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
	<script type='text/javascript' src='../dwr/interface/devTypeService.js'></script>
	<script type='text/javascript' src='../dwr/interface/orgService.js'></script>
	<script type='text/javascript' src='../dwr/engine.js'></script>
	<script type='text/javascript' src='../dwr/util.js'></script>
	
	<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
	<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
	<script language="JavaScript" src="../scripts/orgSelector.js" type="text/javascript"></script>
	<link href="../styles/common.css" rel="stylesheet" type="text/css">
	<link href="../styles/displaytag.css" rel="stylesheet" type="text/css">
</head>

<script language="JavaScript">
var j$ = jQuery.noConflict();
j$().ready(function(){
	j$("#item").rowHilight();
});

function checkForm(){
	return true;
}
function del(obj){
  <%  Set authButton = (Set)request.getSession().getAttribute("_Auth_Button");
     if (!authButton.contains("devbaseinfo.do?action=del") ) { %>
     alert(document.getElementById('Login.op_no_per').value);return ;
  <%  } %> 
  
  if(confirm(document.getElementById('Main.sure_delete').value)){
    window.location="devbaseinfo.do?action=del&no="+obj;
  }
}

function mod(obj){
  window.location="devbaseinfo.do?action=modPage&no="+obj;
}

function detail(obj){
  window.location="devbaseinfo.do?action=detailPage&no="+obj;
}

function setOrg(obj1,obj2){
  $("orgFrame").style.display="none";    
  $('_orgNo').value=obj1;
  $('_orgName').value=obj2;
}
//显示机构的详细信息
function org_detail(obj1,obj2){
	window.location="org.do?action=detail&no="+obj1+"&orgType="+obj2;
}

function restore(){
  $('devNoFilter').value = "";
  $('orgNoFilter').value = "";
  $('orgName').value = "";
  $('devTypeFilter').value = "";
}

function listDevType(data){
	DWRUtil.removeAllOptions($("devTypeFilter"));
	DWRUtil.addOptions($("devTypeFilter"),[{no:'',name:''}],"no","name");
	for(i=0;i<data.length;i++){
		DWRUtil.addOptions($("devTypeFilter"),[{name:data[i].name,no:data[i].no}],"no","name");
	}
}
function qrydevType(){
	devTypeService.qryDevTypebyVendor($('devVendorFilter').value,listDevType);
}

function showOrg(){
	if($("orgFrame").style.display=="block"){
		$("orgFrame").style.display="none";   
	}else{
		showOrgSelectorByOrgNo('dimg',j$("#bankOrgNo").val());
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
    //alert(herf);
    window.location=herf;//转到跳转页
}
</script>

<body>
<!--javascript 国际化标识符-->
	<div id="javascriptI18n">
		<%@ include file="../../scripts/common.jsp" %>
		<%@ include file="../../scripts/orgSelector.jsp" %>
		<input type="hidden" id="Main.sure_delete" value='<%=resource.srcStr("Main.sure_delete")%>'/>
		<input type="hidden" id="Main.pageLowerLimitPrompt" value='<%=resource.srcStr("Main.pageLowerLimitPrompt")%>'/>
		<input type="hidden" id="Main.qryEmptyPrompt" value='<%=resource.srcStr("Main.qryEmptyPrompt")%>'/>
		<input type="hidden" id="Main.input_correct_page" value='<%=resource.srcStr("Main.input_correct_page")%>'/>
		<input type="hidden" id="Main.pageUpperLimitPrompt" value='<%=resource.srcStr("Main.pageUpperLimitPrompt")%>'/>
		<input type="hidden" id="Login.op_no_per" value='<%=resource.srcStr("Login.op_no_per")%>'/>
        <input type="hidden" id="bankOrgNo" name="bankOrgNo" value="${defaultOrgNo}"/>
	</div>

<table width="100%" style="border:0px;border-bottom:1px solid #cccccc;">
  <tr> 
    <td nowrap><b><%=resource.srcStr("System.management_dev_info") %>：</b></td> 
    <td nowrap align="right">
       <input class="button" type="button" value='<%=resource.srcStr("System.add_device")%>' 
       onclick="javascript:window.location='devbaseinfo.do?action=addPage'" style="width:100px;">&nbsp;
    </td>
  </tr>
</table>


<form name="form1" method="post" action="devbaseinfo.do?action=qry">

	<input type="hidden" name="currentPage" value="<c:out value="${page.currentPage}"/>">
	<input type="hidden" name="pageSize" value="50">
	<input type="hidden" name="tmpfirst" value="0">
    <input type="hidden" name="orgNo" id="orgNo"/>
<table width="100%" border="0" cellspacing="1" cellpadding="3" class="table1">
 
  <tr class="tr1">
    <td nowrap colspan="12">
      <%=resource.srcStr("System.query") %>：
    </td>
  </tr>

  <tr class="tr3">
    <td nowrap><%=resource.srcStr("Main.dev_no") %>：</td>
    <td nowrap>
	<input type="text" name="devNoFilter" style="width:130px;" maxlength="16" value="<c:out value="${devNo}"/>" /></td>
	<!-- 
	<td nowrap><%=resource.srcStr("Main.own_station")%>：</td>
		<td><select id="clrCenterNo" name="clrCenterNo" class="pane">
			       <c:forEach items="${clrCenterTableList }" var="clrCenterTable">
			        	<option value="${clrCenterTable.clrCenterNo }" <c:out value="${clrCenterTable.clrCenterNo==clrCenterNo?'selected':'' }"/>>${clrCenterTable.centerName }</option>
			       </c:forEach>
			 </select>
	   </td> 
    -->
    <td nowrap><%=resource.srcStr("Main.own_org") %>：</td>
    <td nowrap>
      	<input type="hidden" name="orgNoFilter" id="_orgNo" value="<c:out value="${orgNoFilter}"/>">
		<input type="text" class="paneGray" name="orgName" id="_orgName" readonly style="width:120px;" value="<c:out value="${orgName}"/>">
		<a onclick="event.cancelBubble=true;" href="javascript:showOrg()"><img id="dimg" align="absmiddle" src="../images/org.gif" border="0"></a>
    </td>
    <td nowrap><%=resource.srcStr("Main.devType") %>：</td>
      <td nowrap>
      	<select name="devTypeFilter" size="1">
      		<option value="">--请选择--</option>
     		<c:forEach items="${devTypeList}" var="item">        
      			<option value="<c:out value="${item[0]}"/>" ${item[0] == devType ? 'selected': ''}><c:out value="${item[1]}"/></option>
      		</c:forEach>
      	</select>
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
    <td nowrap colspan="9">
      <%=resource.srcStr("System.results") %>：&nbsp;&nbsp;&nbsp;
    </td>
  </tr>
</table>
<c:if test="${empty devList}"><%=resource.srcStr("System.noresult")%></c:if>
<c:if test="${not empty devList}">

<display:table name="requestScope.devList" id="item" sort="list" requestURI="devbaseinfo.do" cellspacing="1" cellpadding="3" class="table1" style="word-break:break-all" pagesize="15">
	<display:column title='<%=resource.srcStr("Main.op")%>' >
        <%--
        <a href="javascript:chart('<c:out value="${item[0]}"/>')"><img src="../resource/img/chart.gif" title="查看现金使用率趋势图" border="0" alt="chart" /></a>
         --%>
        <a href="javascript:mod('<c:out value="${item[0]}"/>')"><%=resource.srcStr("System.mod")%></a>&nbsp;
        <a href="javascript:del('<c:out value="${item[0]}"/>')"><%=resource.srcStr("Main.delete")%></a>&nbsp;
    </display:column>
    <display:column title='<%=resource.srcStr("Main.dev_no")%>' sortable="true" sortName="devNo" >
    	<a href="javascript:detail('<c:out value="${item[0]}"/>')"><c:out value="${item[0]}"/></a>
    </display:column>
    <display:column title='所属网点' maxLength="15">
    	<a href="javascript:org_detail('<c:out value="${item[6]}"/>','1')"><c:out value="${item [1]}"/>(<c:out value="${item[6]}"/>)</a>
    </display:column>
    <display:column title='<%=resource.srcStr("Main.dev_address")%>' maxLength="15" >
    	<c:out value="${item [4]}"/>
    </display:column>
    <display:column title='设备型号'>
    	<c:out value="${item [3]}"/>
    </display:column>
    <display:column title="设备状态" sortable="true" sortName="status">
    	<c:if test="${item[8] == 1}">启用</c:if>
    	<c:if test="${item[8] == 2}">停用</c:if>
    	<c:if test="${item[8] == 3}">待运营</c:if>
    	<c:if test="${item[8] == 4}">已撤销</c:if>
    </display:column>
    <%-- <display:column title='设备坐标'>
    	<c:out value="${item[17]}" />,<c:out value="${item[18]}" />
    </display:column> --%>
    <%-- <display:column title='到达估计用时(分钟)' sortable="true" sortName="devType">
    	<c:out value="${item [15]}"/>
    </display:column>
    <display:column title='路径信息' sortable="true" sortName="devType" maxLength="15">
    	<c:out value="${item [16]}"/>
    </display:column> --%>
</display:table>

</c:if>
</body>

</html>

