<%@ page import="com.weili.wechat.common.GetResource"%>
<%@ page import="com.weili.wechat.common.Resource"%>
<% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
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
	<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
	<link href="../styles/common.css" rel="stylesheet" type="text/css">
	<link href="../styles/displaytag.css" rel="stylesheet" type="text/css">
</head>

<body onload="">
<!--javascript ���ʻ���ʶ��-->
<div id="javascriptI18n">
	<%@ include file="../../scripts/common.jsp" %>
	<input type="hidden" id="Main.sure_delete" value='<%=resource.srcStr("Main.sure_delete")%>'/>
	<input type="hidden" id="Main.pageLowerLimitPrompt" value='<%=resource.srcStr("Main.pageLowerLimitPrompt")%>'/>
	<input type="hidden" id="Main.select" value='<%=resource.srcStr("Main.select")%>'/>
	<input type="hidden" id="Main.qryEmptyPrompt" value='<%=resource.srcStr("Main.qryEmptyPrompt")%>'/>
	<input type="hidden" id="Main.input_correct_page" value='<%=resource.srcStr("Main.input_correct_page")%>'/>
	<input type="hidden" id="Main.pageUpperLimitPrompt" value='<%=resource.srcStr("Main.pageUpperLimitPrompt")%>'/>
	<input type="hidden" id="Login.op_no_per" value='<%=resource.srcStr("Login.op_no_per")%>'/>
</div>

<script language="JavaScript">
var j$ = jQuery.noConflict();

j$().ready(function(){
	//��������и���
	j$("#item tr:gt(0)")
	.mouseenter(function(){
		j$(this).children().addClass("onmouseover");
	}) 
	.mouseleave(function(){
		j$(this).children().removeClass("onmouseover"); 
	
	});

	//���ϵͳ����
	j$("#addButton").click(function(){
		window.location = "param.do?action=intoAddSysPage";
	});
});

function mod(obj){
	location.href = "param.do?action=intoModSysPage&logicId="+obj;
}
function del(obj){
	if(confirm("ȷ��ɾ����")){
		location.href = "param.do?action=delSysparam&logicId="+obj;
	}
}

//���ò�ѯ����
function restore(){
	j$("#paramName").add(j$("#catalog")).val("");
}

function go(){
	var pageNum = document.getElementById('curPages').value;
	//alert(pageNum);//��ȡ��תҳ��
	var obj=document.getElementsByTagName("a");
	var herf=new String("");
	var k = obj.length - 1 ;
    var ahref=obj[k]; //��ȡҳ�������һ�����ӵ�ַ
    var _ahref=obj[k-1]; //��ȡҳ���ϵ����ڶ������ӵ�ַ
    var change = 0;//��ĩҳ��ַ������ֵ=0
    herf=""+ahref;
    _herf=""+_ahref;
    //alert(herf);
    var m=herf.split("-p=");
    var k=_herf.split("-p=");
   	if(k.length>1){
   		var j = k[1].split("&");
   		if(parseInt(j[0])==1){
   			change = 1;//�������ڶ�����������ҳʱ������ֵ=1
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
    herf = herf.replace(/&d-(\d+)-p=(\d+)/g,"&d-$1-p="+pageNum);//�õ���תҳ��ַ
    //alert(herf);
    window.location=herf;//ת����תҳ
}
</script>

<form name="form1" method="post" action="param.do?action=qrySysparam">

<!-- <table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
	<tr>
		<td nowrap><b>ϵͳ������Ϣ:</b></td>
	    <td nowrap align="right">
			<input class="button" type="button" style="width:100px;" id="addButton" value='��Ӳ���'>&nbsp;
		</td>
	</tr> 
</table> -->
 <br>
<table id="qryTable" width="100%" border="0" cellspacing="1" cellpadding="3" class="table1">		    
 <tr class="tr1">
  	<td colspan="5"><%=resource.srcStr("System.query")%>:</td>
  </tr>
  <tr class="tr3">
  	<td nowrap>�������</td>
  	<td nowrap>
  		<select id="catalog" name="catalog">
  			<option value="">===��ѡ��===</option>
  			<c:forEach items="${sysParamCatalogList}" var="sysParamCatalog">
  				<option value="${sysParamCatalog.catalog }" <c:out value="${sysParamCatalog.catalog==catalog?'selected':'' }"/>>${sysParamCatalog.catalogName }</option>
  			</c:forEach>
  		</select>
  	</td>
	<td nowrap>��������</td>
	<td nowrap>
		<input type="text" name="paramName" id="paramName" maxlength="80" value='${paramName}'>
	</td>
    <td nowrap align="center">    	
		<input class="button" type="submit" value='<%=resource.srcStr("Main.query")%>'>&nbsp;
    	<input class="button" type="button" value='<%=resource.srcStr("Main.reset")%>' onclick="restore()">&nbsp;
    </td>
  </tr> 
 </table>   		    
</form>


<table width="100%" border="0" cellspacing="0" cellpadding="3" class="table1" style="word-break:break-all">
  <tr class="tr1">
    <td nowrap colspan="7">
      <%=resource.srcStr("System.results") %>��&nbsp;&nbsp;&nbsp;
    </td>
  </tr>
</table>
<display:table name="requestScope.resultList" id="item" requestURI="param.do" cellspacing="1" cellpadding="3" class="table1" style="word-break:break-all" pagesize="15">
	<!-- item = SrvStationVO -->
	<display:column title='����'>
		<a href="javascript:mod('<c:out value="${item[0].logicId}"/>')"><%=resource.srcStr("System.mod")%></a>&nbsp;
       <%-- 	<a href="javascript:del('<c:out value="${item[0].logicId}"/>')"><%=resource.srcStr("Main.delete")%></a>  --%>
    </display:column>    
   	<display:column title='<%=resource.srcStr("���")%>'>
   		<c:out value="${item[0].logicId}"/>
   	</display:column>	   	 
	<display:column title='<%=resource.srcStr("�������")%>'>
		<c:out value="${item[1].catalogName}"/>
	</display:column>	   	 
	<display:column title='<%=resource.srcStr("������")%>'>
		<c:out value="${item[0].paramName}"/>
	</display:column>	   	 			    
	<display:column title='<%=resource.srcStr("����ֵ")%>'>
		<c:out value="${item[0].paramValue}"/>
	</display:column>	   	 
	<display:column title='<%=resource.srcStr("����˵��")%>' maxLength="15">
		<c:out value="${item[0].statement}"/>
	</display:column>	   	 	
	<display:column title='<%=resource.srcStr("����")%>' maxLength="15">
		<c:out value="${item[0].description}"/>
	</display:column>	   	 
</display:table>

</body>

</html>
