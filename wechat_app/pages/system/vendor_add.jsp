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
<link href="../styles/common.css" rel="stylesheet" type="text/css">
</head>

<body>
<!--javascript ���ʻ���ʶ��-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="System.input_brand_name" value='<%=resource.srcStr("System.input_brand_name")%>'/>
<input type="hidden" id="System.brand_name_longer" value='<%=resource.srcStr("System.brand_name_longer")%>'/>
</div>

<script language="JavaScript">
function checkForm(){
  if(isNull($F('name'))){
    alert(document.getElementById('System.input_brand_name').value);
    return false;
  }
  if(getLength(form1.name.value)>20){
    alert(document.getElementById('System.brand_name_longer').value);
    form1.name.select();
    return false;
  }
  if(!isNull($F('name'))){
         var parten;
		 parten = /^[a-zA-Z0-9\u4e00-\u9fa5][a-zA-Z0-9_\-\.\u4e00-\u9fa5]{0,19}$/;
		 if(!parten.exec($F('name'))) {
			       alert('Ʒ�����Ʊ����ɺ��֡���ĸ�����֡��»��ߡ����ߡ�������\n����ͷֻ���Ǻ��֡����ֻ�����ĸ');
			  	   form1.name.select();
		  	  	   return false;
		}  
  }
  form1.submit();
  return true;
}
</script>



<br>


<form name="form1" method="post" action="vendor.do?action=add">

<table align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1">
 ��<tr class="tr1">
    <td nowrap colspan="2" align="center"><%=resource.srcStr("System.add_dev_brand") %></td>
  </tr>  
  <tr class="tr3">
    <td nowrap width="150"><%=resource.srcStr("System.brand_name") %>��</td>
    <td nowrap><input type="text" name="name" class="pane"  maxLength="20">&nbsp;*</td> 
  </tr> 
  <!-- tr class="tr3">
    <td nowrap>�����̹��һ������</td>
    <td nowrap><input type="text" name="country" class="pane" onblur="checktext($(country),30);">&nbsp;</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>�����̵�ַ��</td>
    <td nowrap><input type="text" name="address" class="pane" onblur="checktext($(address),80);">&nbsp;</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>����������1��</td>
    <td nowrap><input type="text" name="hotline1" class="pane" onKeyPress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;" onblur="checktext($(hotline1),20);">&nbsp;</td> 
  </tr>
  <tr class="tr3">
    <td nowrap>����������2��</td>
    <td nowrap><input type="text" name="hotline2" class="pane" onKeyPress="if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;" onblur="checktext($(hotline2),20);">&nbsp;</td> 
  </tr>
  <tr class="tr3"> 
	<td nowrap>������״̬��</td>
    <td nowrap>
      <select name="status" size="1">
          <option value="1" selected>�豸��Ӧ</option>
          <option value="2">�豸����</option>
		  <option value="3">�豸����</option>
  	  </select>
  </tr>	-->  
</table>

    <p align="center">
      <input class="button" type="button" value='<%=resource.srcStr("Main.submit")%>' onclick="checkForm()">&nbsp;
      <input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.location='vendor.do?action=qry'">
    </p>


</form>

</body>
</html>




