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
<!--javascript ���ʻ���ʶ��-->
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
function isMenusFileValid(fileName)
{
	if(fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="jar")
	{	
		return true;
	}
	else
	{
		return false;
	}
}
function checkForm(){
	var phoneNumCheck = /\d+\-\d+/g;
	var phoneNumCheckMsg = "�绰/�������ֻ���������ֺ�-���������ֿ�ʼ�ͽ���";
   if(isNull($('jarFile').value)){
    alert("���ϴ�Դ�ļ���");  
   return false;
  }
   if(!isMenusFileValid($('jarFile').value))
  {
	alert("�����ļ���ʽ����ȷ������Ϊ.jar�ļ�");
    return false;
  }
  
  return true;
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
		    herf = herf.replace(/d-(\d+)-p=(\d+)/g,"d-$1-p="+pageNum);//�õ���תҳ��ַ
		    //alert(herf);
		    window.location=herf;//ת����תҳ
	}

</script>
<br>

<form name="form1" method="post" action="jarUp.do?action=add" enctype="multipart/form-data">
<table id="infoTable" align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1" >
 <tr class="tr1">
    <td nowrap colspan="2" align="center">���JAR��</td>
  </tr>  
  <tr class="tr3">
    <td nowrap width="120">�ϴ���������</td>
    <td nowrap><input type="text" name="sender" maxlength="80">&nbsp;*</td> 
  </tr>
  <tr class="tr3">
    <td nowrap width="120">jar�����ƣ�</td>
    <td nowrap><input type="text" name="name" maxlength="80">&nbsp;*</td> 
  </tr>
  <tr class="tr3">
     <td nowrap >Դ�ļ�:&nbsp;<Font color="red">(*)</font></td>
	 <td nowrap  title='�ϴ�Դ�ļ�' ><input type="file" name="jarFile"  id="jarFile" style="width:420px;" value="" contentEditable='false'></td>
  </tr>
</table>

    <p align="center">
      <input class="button" type="submit" value='<%=resource.srcStr("Main.submit")%>' onclick="return checkForm()">
      <input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.history.back();">
    </p>

</form>
</body>
</html>




