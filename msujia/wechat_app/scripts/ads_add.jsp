<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/calendar.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/singlefileads.js" type="text/javascript"></script>




<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<script language="javascript">
function submitAds()
{
	var adsDesc = form1.adsDesc.value;
	if(adsDesc==null||adsDesc=="")
	{
		alert(document.getElementById('Version.input_adv_brief').value);
		form1.adsDesc.focus()
		return;
	}
	if(adsDesc.length>200)
	{
		alert(document.getElementById('Version.adv_bridf_longer').value+(adsDesc.length-200)+document.getElementById('Version.some_char').value);
		form1.adsDesc.select();
		form1.adsDesc.focus();
		return;		
	}
	var type = form1.adsType.value;
	if(type=="2")
	{
		var announceText = form1.announceText.value;
		if(announceText==null||announceText=="")
		{
			alert(document.getElementById('Version.input_adv_content').value);
			form1.announceText.select();
			form1.announceText.focus();
			return;
		}
	}
	else
	{
		var adsFile = form1.adsFile.value;
		if(adsFile==null||adsFile=="")
		{
			alert(document.getElementById('Version.select_adv_file').value);
			return;
		}	
	}
	var showTime = form1.showTime.value;
	if(!showTime.isDigit()||showTime>60||showTime==0)
	{
		alert(document.getElementById('Version.error_adv_time').value);
		form1.showTime.select();
		form1.showTime.focus();
		return;
	}
	if(!timeValid())
	{
		return;
	}
	
	
	form1.action = "ads.do?action=add&command=view&startTimeZone="+startTimeZone+"&endTimeZone="+endTimeZone;

	form1.submit();
	
}

</script>

<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<input type="hidden" id="Version.adv_bridf_longer" value="<%=resource.srcStr("Version.adv_bridf_longer")%>"/>
<input type="hidden" id="Version.some_char" value="<%=resource.srcStr("Version.some_char")%>"/>
<input type="hidden" id="Version.error_adv_time" value="<%=resource.srcStr("Version.error_adv_time")%>"/>
<input type="hidden" id="Version.input_adv_brief" value="<%=resource.srcStr("Version.input_adv_brief")%>"/>
<input type="hidden" id="Version.input_adv_content" value="<%=resource.srcStr("Version.input_adv_content")%>"/>
<input type="hidden" id="Version.select_adv_file" value="<%=resource.srcStr("Version.select_adv_file")%>"/>
<%@ include file="../../scripts/common.jsp" %>
<%@ include file="../../scripts/calendar.jsp" %>
<%@ include file="../../scripts/singlefileads.jsp" %>
</div>



<link href="../styles/common.css" rel="stylesheet" type="text/css">
<style type="text/css">
.btn
{
	width:80px; 
	border-left: #2c59aa 1px solid;
	border-right: #2c59aa 1px solid; 
	border-top: #2c59aa 1px solid; 
	border-bottom: #2c59aa 1px solid;
	padding-top: 2px;
	padding-right: 2px; 
	padding-left: 2px; 
	font-size: 12px; 
	filter: progid:dximagetransform.microsoft.gradient(gradienttype=0, startcolorstr=#ffffff, endcolorstr=#c3daf5); 
	cursor: hand; 
	color: black;
}
.li
{
	list-style-type:none;
	font-family:宋体-18030;
	font-size:36px;
	color:#9A32CD;
	font-weight:bold;
}
.media
{
	filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);
	width:800px;
	height:600px;
}
.div1
{
	height:27px;
	text-align:center;
	margin-top:5px;
}
.div2
{
	margin-top:10px;
	margin-bottom:10px;
	font-family:宋体-18030;
	font-size:12px;
	color:#0000FF;
}
.input1
{
	border:none;
	border-bottom:1px solid #0000FF;
	width:30px;
}
</style>


</head>
<body onload="insertAdsDiv();">

<table width="100%" style="border:0px;border-bottom:1px solid #cccccc;">
  <tr> 
    <td nowrap><b><%=resource.srcStr("Version.cross_adv_product")%></b></td>
  </tr>
</table>
<form name="form1" method="post" action="" enctype="multipart/form-data">
<table width="100%" border="0" cellspacing="1" cellpadding="3" class="table1" >
   <tr>
		<td class="td1" nowrap width="15%">&nbsp;<%=resource.srcStr("Version.adv_type") %>：</td>
		<td class="td2" width="35%">
			<select id="adsType" name="adsType" size="1" onChange="insertAdsDiv()">
				<option value="0"><%=resource.srcStr("Version.wait_adv_card")%></option>
				<option value="1"><%=resource.srcStr("Version.trans_adv")%></option>
				<option value="2"><%=resource.srcStr("Version.notice_text")%></option>
			</select>       
		</td>
		<td class="td1" nowrap  width="15%" title="<%=resource.srcStr("Version.issued_to_atm") %>">&nbsp;<%=resource.srcStr("Version.issued_way") %>：</td>
		<td class="td2" width="35%">	  
			<select name="pushType" style="width:100px" >	              
				<option value="0"selected><%=resource.srcStr("Version.add_to")%></option>
				<option value="1"><%=resource.srcStr("Main.cover")%></option>		          
			</select> 
		</td>
	</tr>
	<tr class="tr3">
		<td  class="td1">&nbsp;<%=resource.srcStr("Version.adv_brief") %>：<Font color="red">*</font></td>
		<td  class="td2" colspan="3"><textarea name="adsDesc" rows="2" cols=80 id="adsDesc"></textarea></td>
	</tr>
 </table>
<div id="submitBnt" class="div1" >
<input class="button" type="button" value="<%=resource.srcStr("Main.submit")%>" onclick="submitAds();" title="<%=resource.srcStr("Version.submit_current_adv")%>">&nbsp;
<input class="button" type="button" value="<%=resource.srcStr("Main.reset")%>" onclick="form1.reset();insertAdsDiv();">
</div>
<div id="adsDiv">
</div>
<div id="preview">
</div>
</form>
<br>

</body>
</html>


