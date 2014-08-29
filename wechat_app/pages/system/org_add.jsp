<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>

<%@ page import="java.util.List" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<script type='text/javascript' src='../dwr/interface/orgService.js'></script>
<script type='text/javascript' src='../dwr/interface/stationService.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/orgSelector.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
</head>

<body>
<!--javascript ���ʻ���ʶ��-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<%@ include file="../../scripts/orgSelector.jsp" %>
<input type="hidden" id="System.have_special_char" value='<%=resource.srcStr("System.have_special_char")%>'/>
<input type="hidden" id="System.num_institution" value='<%=resource.srcStr("System.num_institution")%>'/>
<input type="hidden" id="System.input_maintainer_name" value='<%=resource.srcStr("System.input_maintainer_name")%>'/>
<input type="hidden" id="System.orgname_longer_than" value='<%=resource.srcStr("System.orgname_longer_than")%>'/>
<input type="hidden" id="System.addr_length_than" value='<%=resource.srcStr("System.addr_length_than")%>'/>
<input type="hidden" id="System.contact_longer_than" value='<%=resource.srcStr("System.contact_longer_than")%>'/>
<input type="hidden" id="System.tel_longer_than15" value='<%=resource.srcStr("System.tel_longer_than15")%>'/>
<input type="hidden" id="System.tel_shorter_than" value='<%=resource.srcStr("System.tel_shorter_than")%>'/>
<input type="hidden" id="System.phone_length" value='<%=resource.srcStr("System.phone_length")%>'/>
<input type="hidden" id="System.fax_longer_than" value='<%=resource.srcStr("System.fax_longer_than")%>'/>
<input type="hidden" id="System.fax_shorter_than" value='<%=resource.srcStr("System.fax_shorter_than")%>'/>
<input type="hidden" id="System.input_error_email" value='<%=resource.srcStr("System.input_error_email")%>'/>
<input type="hidden" id="System.email_longer_than" value='<%=resource.srcStr("System.email_longer_than")%>'/>
<input type="hidden" id="Main.select" value='<%=resource.srcStr("Main.select")%>'/>
<input type="hidden" id="System.parent_body" value='<%=resource.srcStr("System.parent_body")%>'/>
<input type="hidden" id="System.input_institution" value='<%=resource.srcStr("System.input_institution")%>'/>
<input type="hidden" id="System.institution_digital" value='<%=resource.srcStr("System.institution_digital")%>'/>
<input type="hidden" id="System.input_orgname" value='<%=resource.srcStr("System.input_orgname")%>'/>
<input type="hidden" id="System.select_org_type" value='<%=resource.srcStr("System.select_org_type")%>'/>
<input type="hidden" id="System.select_parent_org" value='<%=resource.srcStr("System.select_parent_org")%>'/>
<input type="hidden" id="System.select_parent_org" value='<%=resource.srcStr("System.select_parent_org")%>'/>
<input type="hidden" id="System.addr_empty" value='<%=resource.srcStr("System.addr_empty")%>'/>
<input type="hidden" id="System.input_error_tel" value='<%=resource.srcStr("System.input_error_tel")%>'/>
<input type="hidden" id="System.input_error_phone" value='<%=resource.srcStr("System.input_error_phone")%>'/>
<input type="hidden" id="System.input_error_fax" value='<%=resource.srcStr("System.input_error_fax")%>'/>
<input type="hidden" id="System.exists_org" value='<%=resource.srcStr("System.exists_org")%>'/>
<input type="hidden" id="System.input_orgno" value='<%=resource.srcStr("System.input_orgno")%>'/>
<input type="hidden" id="System.orgno_longer_than" value='<%=resource.srcStr("System.orgno_longer_than")%>'/>
<input type="hidden" id="System.select_org_status" value='<%=resource.srcStr("System.select_org_status")%>'/>
<input type="hidden" id="bankOrgNo" name="bankOrgNo"/>
</div>
<script language="JavaScript">
var j$ = jQuery.noConflict();
j$().ready(function(){
	//���ݻ�������������������
	j$("#_orgGrade").change(function(){
		if (j$(this).val() == 4 || j$(this).val() == 5) {    //���������ǡ����㡱
			j$("#addressRegionTypeNoTr").show();
		
		} else {
			j$("#addressRegionTypeNoTr").hide();
		}
	}).change();
});
function checkType(){
	var tmpType = document.getElementById("orgType");
	var tmpUpper1 = document.getElementById("upper1");
	if(tmpType.value == 1){
 		tmpUpper1.disabled = true;
 		tmpUpper1.value="";
 	}
 	else{
 	tmpUpper1.disabled = false;
 	}	
}
function setOrg(obj1,obj2){
	$("orgFrame").style.display="none";    
  	$('_orgNo').value=obj1;
  	$('_orgName').value=obj2;
}
function showOrg(){
	if($("orgFrame").style.display=="block"){
		$("orgFrame").style.display="none";   
	}else{
		showOrgSelectorByOrgNo('dimg',j$("#bankOrgNo").val());
	}
}
function checkForm()
{
  //������ż��
  if(isNull(form.orgNo.value))
  {
    alert(document.getElementById('System.input_orgno').value);
    form.orgNo.select();
    return false;
  }
  if(getLength(form.orgNo.value)!=8 && form.orgType.value=="1"){
	  alert("�������ֻ��Ϊ8λ���֣����������룡");
	  form.orgNo.select();
	  return false;
  }
  //���������
  if(isNull(form.orgName.value))
  {
    alert(document.getElementById('System.input_orgname').value);
    form.orgName.select();
    return false;
  } 
  if(getLength(form.orgName.value)>80)
  {
    alert(document.getElementById('System.orgname_longer_than').value);
    form.orgName.select();
    return false;
  }
  if(!isNull(form.orgName.value))
  {
    var parten;
    parten = /^[a-zA-Z0-9\u4e00-\u9fa5][a-zA-Z0-9_\-\.\(\)\u4e00-\u9fa5\U+2100-U+214F]{0,79}$/;
    if(!parten.exec(form.orgName.value)) 
    {
      alert('�������Ʊ����ɺ��֡���ĸ�����֡��»��ߡ����ߡ���ţ�Ӣ����κź�Ӣ���ҹκ����\n����ͷֻ���Ǻ��֡����ֻ�����ĸ');
      form.orgName.select();
 	  return false;
 	}
  }
  //�������ͼ��
  if(isNull(form.orgType.value))
  {
    alert(document.getElementById('System.select_org_type').value);
    form._orgName.select();
    return false;
  } 
  //����������
  if(isNull(form.orgGrade.value)){
    alert("��ѡ���������!");
    form.orgGrade.focus();
    return false;
  }
  if($F('areaNo')=="" ){
	alert("��ѡ�����ڳ��У�");
	$('areaNo').focus();
	return false;
  }
  //����������
  if ((j$('#_orgGrade').val() == 4 || j$('#_orgGrade').val() == 5) && j$('#addressRegionTypeNo').val().length == 0)//�����������ǡ����㡱
  {    
	alert("��ѡ����������");
 	return false;
  }
  //��������״̬���
  if(isNull(form.status.value))
  {
    alert(document.getElementById('System.select_org_status').value);
    form.status.select();
    return false;
  } 
  if($F('orgType_Query')==1&&isNull(form.upper1.value)){
   alert(document.getElementById('System.select_parent_org').value);
   form._orgName.focus();
   return false;
  }
  if($F('orgType_Query')==1 ){
  	if(isNull(form.upper1.value) && form.orgGrade.value != '1'){
		alert(document.getElementById('System.select_parent_org').value);
    	form._orgName.select();
    	return false;
  	}
  }
	if(form.x.value=="0.0" || form.y.value=="0.0" || isNull(form.x.value) || isNull(form.y.value)){
		alert("������д������γ��ֵ!");
		return false;
	}
	if(!isNull(form.address.value))
	{
        if(getLength(form.address.value)>80)
        {
            alert(document.getElementById('System.addr_length_than').value);
            form.address.select();
            return false;
        }
  	}else if($F('orgType_Query')==1){
		alert("��ַ����Ϊ�գ����������룡");
        form.address.select();
        return false;
  	}
  if($F('orgType_Query')==2 ){
  	if(!isNull(form.linkMan.value)){
  		if(!isNull($F('linkMan'))){
         var parten;
		 parten = /^[a-zA-Z0-9\u4e00-\u9fa5][a-zA-Z0-9_\-\.\u4e00-\u9fa5]{0,9}$/;
		 if(!parten.exec($F('linkMan'))) {
			       alert('��ϵ�˱����ɺ��֡���ĸ�����֡��»��ߡ����ߡ�������\n����ͷֻ���Ǻ��֡����ֻ�����ĸ');
			  	   form.linkMan.select();
		  	  	   return false;
		}  
  		}
        if(getLength(form.linkMan.value)>10){
            alert(document.getElementById('System.contact_longer_than').value);
            form.linkMan.select();
            return false;
        }
 	 } 
    if(!isNull(form.telephone.value)){	
	  if(!phoneCheck(form.telephone)){ 
	      alert(document.getElementById('System.input_error_tel').value);
	      form.telephone.select();
	      return false;
	   }
  	  if(getLength(form.telephone.value)>15){
    		alert(document.getElementById('System.tel_longer_than15').value);
    		form.telephone.select();
    		return false;
  		 }
  	  if(getLength(form.telephone.value)<7){
    		alert(document.getElementById('System.tel_shorter_than').value);
    		form.telephone.select();
   	 		return false;
  		 }
	}
    if(!isNull(form.mobile.value)){	
	  if(!mobileCheck(form.mobile)){ 
	      alert(document.getElementById('System.input_error_phone').value);
	      form.mobile.select();
	      return false;
	   }
  	  if(getLength(form.mobile.value)<11){
    		alert(document.getElementById('System.phone_length').value);
    		form.mobile.select();
    		return false;
  		 }
	}
  if(!isNull(form.fax.value)){	
	  if(!phoneCheck(form.fax)){ 
	      alert(document.getElementById('System.input_error_fax').value);
	      form.fax.select();
	      return false;
	   }
  	  if(getLength(form.fax.value)>15){
    		alert(document.getElementById('System.fax_longer_than').value);
    		form.fax.select();
    		return false;
  		 }
  	  if(getLength(form.fax.value)<7){
    		alert(document.getElementById('System.fax_shorter_than').value);
    		form.fax.select();
   	 		return false;
  		 }
	}
	if(!isNull(form.email.value)){
    	var checkmail = form.email.value;
	    reEmail=/\w+\@\w+\.\w+/gi ;//�����ʽ�������Ҫ���ͨ��
      	var isOk=reEmail.test(checkmail);
   	    if(!isOk){
           alert(document.getElementById('System.input_error_email').value);
	       return false;
	   }
  	}
  	if(!isNull(form.email.value)){
        if(getLength(form.email.value)>40){
            alert(document.getElementById('System.email_longer_than').value);
            form.email.select();
            return false;
        }
  }
  }
  	form.submit();
}


</script>


<br>

<%
	String orgType_Query=request.getParameter("orgType_Query")==null?"":request.getParameter("orgType_Query");
	request.setAttribute("orgType_Query",orgType_Query);
	String title = request.getParameter("title");
	//System.out.println("title=["+title+"]");
%>
<form name="form" method="post" action="org.do?action=add">

<input type="hidden" name="title" value='<%= title%>'>
<input type="hidden" name="orgType_Query" value='<%=orgType_Query %>'>
<input type="hidden" name="tmpfirst" value="0">

		<table align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1">
			<tr class="tr1">
			    <td nowrap colspan="2" align="center"><%=resource.srcStr("System.add")%><%= title%><%=resource.srcStr("System.info")%>&nbsp;(<%=resource.srcStr("System.required") %>)</td>
			</tr>
			<tr class="tr3">
				<td align="right">������ţ�</td>
				<td ><input type="text" name="orgNo" size="8" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'')">&nbsp;*</td>
			</tr>
	     	<tr class="tr3">
				<td align="right">�������ƣ�</td>
				<td ><input type="text" name="orgName" style="width:200px;"  maxLength="80">&nbsp;*</td>
			</tr>
			<input type="hidden" name="orgType" value="1"><!-- ��������Ĭ��Ϊ���� -->
			<tr class="tr3">
				<td align="right">��������</td>
				<td>
					<select id="_orgGrade" name="orgGrade">
						<option value="">----��ѡ��----</option>
						<c:if test="${userSession.roleNo eq 10001}">
							<option value="1">����</option>
							<option value="2">����</option>
						</c:if>
						<option value="3">֧��</option>
						<option value="4">��������</option>
					</select>&nbsp;*
				</td>
			</tr>
			<tr class="tr3">
				<td align="right">�������У�</td>
				<td>
					<select id="areaNo" name="areaNo" class="pane">
						<option value="">--------��ѡ��--------</option>
						<c:forEach items="${areaList}" var="area">
							<option value="${area.no }">${area.name }</option>
						</c:forEach>
					</select>&nbsp;*
				</td>
			</tr>
			<tr class="tr3">
				<td align="right">�ϼ�������</td>
				<td>
			  		<input type="hidden" class="pane" name="upper1" id="_orgNo">
					<input type="text" class="paneGray" name="_orgName" id="_orgName" readonly style="width:120px;">
					<a onclick="event.cancelBubble=true;" href="javascript:showOrg();"><img id="dimg" align="absmiddle" src="../images/org.gif" border="0"></a>&nbsp;*
		   		</td>
			</tr>	
			<tr class="tr3">
				<td align="right">��������״̬��</td>
				<td>
					<SELECT  name="status" style="width:153;">
						<option value='1' selected>����</option>
						<option value='2'>ͣ��</option>
					</SELECT>
				</td>
			</tr>
			<tr class="tr3">
				<td align="right">�����б�־��</td>
				<td>
					<SELECT  name="awayFlag" style="width:153;">
						<option value='1' selected>����</option>
						<option value='2'>����</option>
					</SELECT>
				</td>
			</tr>
			<tr class="tr3">
				<td align="right">���ȣ�</td>
				<td>
					<input type="text" id="x" name="x" value="0.0" onkeyup="value=value.replace(/[^\d.]/g,'')" onblur="if(this.value.charAt(0)=='.'){alert('���ȸ�ʽ����ȷ!');this.select()}">&nbsp;*
					<!--a href="#" onclick="javascript:gainXY(); return false;"><img alt="��ȡ����" src="../images/map/map.png" style="border: 0px;vertical-align: bottom;"></a  -->
				</td>
			</tr>
			<tr class="tr3">
				<td align="right">γ�ȣ�</td>
				<td>
					<input type="text" id="y" name="y" value="0.0" onkeyup="value=value.replace(/[^\d.]/g,'')" onblur="if(this.value.charAt(0)=='.'){alert('γ�ȸ�ʽ����ȷ!');this.select()}">&nbsp;*
				</td>			
			</tr>	
			<tr class="tr3">
				<td align="right">��ַ��</td>
				<td ><input type="text" name="address" style="width:400px;" maxLength="80" onkeyup="value=value.replace(/[\s]/g,'')">&nbsp;*</td>
			</tr>
		</table>    
		<p align="center">
      		<input class="button" type="button" value='<%=resource.srcStr("Main.submit")%>' onclick="return checkForm()">&nbsp;
     		<input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.location='<c:out value='${menuURL}'/>';">
   		</p>
</form>
</body>
</html>