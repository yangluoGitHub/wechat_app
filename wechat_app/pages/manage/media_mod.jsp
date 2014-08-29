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
<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="System.input_maintainer_name" value='<%=resource.srcStr("System.input_maintainer_name")%>'/>
<input type="hidden" id="System.fault_email_format" value='<%=resource.srcStr("System.fault_email_format")%>'/>
</div>

<script language="JavaScript">
//var imageSuffix = ["jpg","png","gif","jpeg","bmp"];
//var videoSuffix = ["avi","mp4"];
//var voiceSuffix = ["mp3","amr"];
var j$ = jQuery.noConflict();
/* j$().ready(function(){
	var res = j$("input[name='mediaResource']:checked").val();
	if(res == "0"){
		j$("#localTr").show();
		j$("#linkTr").hide();
	}else{
		j$("#linkTr").show();
		j$("#localTr").hide();
	}
	j$("input[name='mediaResource']").change(function(){
		if(j$(this).val()==0){
			j$("#localTr").show();
			j$("#linkTr").hide();
		}
		if(j$(this).val()==1){
			j$("#localTr").hide();
			j$("#linkTr").show();
		}
	});
}); */
function checkForm(){
	/* var res = j$("input[name='mediaResource']:checked").val();
	var type = j$("#mediaType").val();
	if(res == "0"){
		if(isNull(j$("#file").val())){
			alert("请选择要上传的文件");  
			return false;
		}
		var filename = j$("#file").val();
		if(!checkSuffix(filename,type)){
			alert("上传资源与资源类型不符");
			return false;
		}
	}
	if(res == "1"){
		if(isNull(j$("#link").val())){
			alert("请输入资源链接");  
			return false;
		}
		var filename = j$("#link").val();
		if(!checkSuffix(filename,type)){
			alert("链接资源与资源类型不符");
			return false;
		}
	} */
	if(isNull(j$("#mediaName").val())){
		alert("请输入资源名称");  
		return false;
	}
	return true;
}
function checkSuffix(filename,type){
	var i = filename.lastIndexOf(".");
	var suffix = filename.substring(i + 1);
	if(type == "image"){
		if(j$.inArray(suffix,imageSuffix) >-1){
			return true;
		}
	}
	if(type == "video"){
		if(j$.inArray(suffix,videoSuffix) >-1){
			return true;
		}
	}
	if(type == "voice"){
		if(j$.inArray(suffix,voiceSuffix) >-1){
			return true;
		}
	}
	return false;
}
</script>
<br>

<form name="form1" method="post" action="media.do?action=mod" enctype="multipart/form-data">
<input type="hidden" name="id" value="${media.id}">
<table id="infoTable" align="center" width="650" border="0" cellspacing="1" cellpadding="5" class="table1" >
 <tr class="tr1">
    <td nowrap colspan="2" align="center">修改资源</td>
  </tr>  
  <tr class="tr3">
    <td nowrap width="120">名称：</td>
    <td nowrap><input type="text" name="mediaName" id="mediaName" maxlength="80" value="${media.mediaName}">&nbsp;*</td> 
  </tr>
  <%-- <tr class="tr3">
    <td nowrap>资源类型：</td>
    <td nowrap>
	<select name="mediaType" id="mediaType" size="1" >
		<option value="image" ${media.mediaType eq 'image'?'selected':'' }>图片</option>
		<option value="voice" ${media.mediaType eq 'voice'?'selected':'' }>音频</option>
		<option value="video" ${media.mediaType eq 'video'?'selected':'' }>视频</option>
	</select>	
  </tr> --%>
 <!--  <tr class="tr3">
  	<td nowrap>资源来源</td>
  	<td nowrap>
  	<input type="radio" name="mediaResource"  value="0" checked="checked">本地上传 &nbsp;
  	<input type="radio" name="mediaResource"  value="1">外链
	</td> 
  </tr> -->
   <%-- <tr class="tr3">
    <td nowrap width="120">存储路径：</td>
    <td nowrap>${media.mediaPath}</td> 
  </tr>
  <tr class="tr3" id="localTr">
    <td nowrap width="120">本地资源：</td>
    <td nowrap><input type="file" name="file" id="file">&nbsp;*</td> 
  </tr>
  <tr class="tr3" id="linkTr">
    <td nowrap width="120">资源路径：</td>
    <td nowrap><input type="text" name="link" id="link" maxlength="128">&nbsp;*</td> 
  </tr> --%>
  <tr class="tr3">
    <td nowrap width="120">资源描述：</td>
    <td nowrap><input type="text" name="describe" id="describe" maxlength="256" value="${media.describe}"></td> 
  </tr>
</table>
    <p align="center">
      <input class="button" type="submit" value='<%=resource.srcStr("Main.submit")%>' onclick="return checkForm()">
      <input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.history.back();">
    </p>

</form>
</body>
</html>




