<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource,java.util.Set"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=gbk" />
<title></title>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/calendar.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/orgSelector.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
<link href="../styles/displaytag.css" rel="stylesheet" type="text/css">

<style type="text/css">

.trr {
	background-color: #ffffff;
}

#divPageMask{
	background-color:white; 
	filter:alpha(opacity=50);
	left:0px;
	position:absolute;
	top:0px;
}

#divOpenWinImage {
	background-color:#EEEEEE;
	position: absolute;
	left:0px;
	top:0px;
	border:1px solid #000;
	display: none;
	z-index:9999; 
	width:600px;
	height:300px;
	overflow:scroll;
}
#previewImage{
	background-color:#EEEEEE;
	position: absolute;
	left:0px;
	top:0px;
	border:1px solid #000; 
	display: none;
	z-index:9999; 
	width:600px;
	height:300px;
	overflow:scroll;
}
#divOpenWinImageText {
	background-color:#EEEEEE;
	position: absolute;
	left:0px;
	top:0px;
	border:1px solid #000; 
	display: none;
	z-index:9999; 
	width:600px;
	height:300px;
	overflow:scroll;
}

</style>
</head>

<body>
<!--javascript ���ʻ���ʶ��-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="Main.sure_delete" value='<%=resource.srcStr("Main.sure_delete")%>'/>
<input type="hidden" id="Login.op_no_per" value='<%=resource.srcStr("Login.op_no_per")%>'/>
</div>
<script language="JavaScript">
var j$ = jQuery.noConflict();
var init;
var array;
j$().ready(function(){
	init = true;
	var tmpImgText = j$("#tmp_multiImageText").val();
	if(tmpImgText.length == 0){
		return;
	}
	var tmpArr = tmpImgText.split("|");
	var tmp = tmpArr;
	j$("input[name='imageTextNoCheckbox']").each(function(){
		var value = j$(this).val();
		var id = value.split("|")[0];
		var index = j$.inArray(id,tmpArr);
		if(index > -1){
			j$(this).attr("checked","checked");
			tmp[index]=value;
		}
	});
	array = tmp;
	closeImageText();
}); 

function addImageText(){
	j$("#imageText input[name='imageTextNoCheckbox']").removeAttr("checked");
	 //����select�ؼ�
    DispalySelect(0);
    //��ʾ���ֲ�
    document.getElementById("divPageMask").style.display = "block";
    //�������ֲ�
    resizeMask();
    window.onResize = resizeMask;
    //��ʾ��������
    document.getElementById("divOpenWinImageText").style.display = "block";
}

function closeImageText() {
	if(!init){
		array = new Array();
		j$("input[name='imageTextNoCheckbox']").each(function(){
			var chk = j$(this).attr("checked");
			if(chk == "checked") {
				var temp = j$(this).val();
				if (j$.inArray(temp, array) < 0) {
					array.push(temp);
				}
			}
		});
		if(array.length>10){
			alert("������9����������ѡ��");
			return false;
		}
	}
	init = false;
	var table = j$("#multiImageText");
	table.empty();
	
	var head_html="<tr class='tr2'><td nowrap width='10%' align='center'>����</td><td nowrap width='10%' align='center'>˳��</td>"
		+"<td nowrap width='40%'>ͼ��ID</td><td nowrap width='40%'>ͼ�ı���</td></tr>";
	if(array.length > 0){
		table.append(head_html);
	}
	for (var i = 0; i < array.length ; i++) {
		var id = array[i].split("|")[0];
		var title = array[i].split("|")[1];
		var control_html = "<td nowrap align='center'>" + "<img alt='����һλ' src='../images/others/route/up1.png' style='cursor:pointer;' onclick='javascript:sortedUp(\""+id+"\");'/>"
			+ "<img alt='����һλ' src='../images/others/route/down1.png' style='cursor:pointer;' onclick='javascript:sortedDown(\""+id+"\");'/>" +"</td>";
		var tr_start_html="<tr id='sorted_"+id+"_tr' class='tr3' >";
		var tr_end_html="</tr>";
		var order_html="<td nowrap align='center'>"+(i+1)+"</td>";
		var id_html="<td nowrap>"+id+"<input type='hidden' id='"+id+"_sortNo' name='"+id+"_sortNo' value='"+(i+1)+"'/>"+"<input type='hidden' id='"+id+"_resID' name='"+id+"_resID' value='"+id+"'/>"+"</td>";
		var title_html="<td nowrap>"+title+"</td>";
		var final_html=tr_start_html+control_html+order_html+id_html+title_html+tr_end_html;
		table.append(final_html);
	}
	
    //��ʾselect�ؼ�
    DispalySelect(1);
    //�������ֲ�
    divPageMask.style.width = "0px";
    divPageMask.style.height = "0px";
    divOpenWinImageText.style.display = "none";
    window.onResize = null;
   document.getElementById("divOpenWinImageText").style.display = "none";
}

function sortedUp(id){
	var sortNo = parseInt(j$("#"+id+"_sortNo").val(),10);
	if(sortNo<=1){alert("�޷�����!");return;}
	sortedExchange(sortNo-1,sortNo);
}


function sortedDown(id){
	var sortNo = parseInt(j$("#"+id+"_sortNo").val(),10);
	var num = array.length;
	if(sortNo>=num){alert("�޷�����!");return;}
	sortedExchange(sortNo,sortNo+1);
}

function sortedExchange(sortNo1,sortNo2){
	var tr1 =  j$("#multiImageText input[name$='_sortNo'][value="+sortNo1+"]").parent().parent(); 
	var tr2 =  j$("#multiImageText input[name$='_sortNo'][value="+sortNo2+"]").parent().parent(); 
	tr1.find("td:eq(1)").html(sortNo2);
	tr2.find("td:eq(1)").html(sortNo1);
	tr1.find("input[name$='_sortNo']").val(sortNo2);
	tr2.find("input[name$='_sortNo']").val(sortNo1);
	var html1 = tr1.html();
	var html2 = tr2.html();
	tr1.html(html2);
	tr2.html(html1);
	var id1 = tr1.attr("id");
	var id2 = tr2.attr("id");
	tr1.attr("id",id2);
	tr2.attr("id",id1);
}

function getImage2(){
	var title = j$("#title").val();
	var cover = j$("#cover").val();
	var content = j$("#content").val();
	var outLink = j$("#outLink").val();
	var imageText1 = j$("#imageText1").val();
	var imageText2 = j$("#imageText2").val();
	var imageText3 = j$("#imageText3").val();
	
	var qryHref=title+";"+cover+";"+outLink
		+";"+imageText1+";"+imageText2+";"+imageText3;
	
	openWindow('imageText.do?action=getImage&content='+content+'&qryHref='+qryHref,'detail','650','450');
 // window.location="imageText.do?action=qryImages";
}

function getImage() {
    //����select�ؼ�
    DispalySelect(0);
    //��ʾ���ֲ�
    document.getElementById("divPageMask").style.display = "block";
    //�������ֲ�
    resizeMask();
    window.onResize = resizeMask;
    //��ʾ��������
    document.getElementById("divOpenWinImage").style.display = "block";
}
function closeImage() {
    //��ʾselect�ؼ�
    DispalySelect(1);
    //�������ֲ�
    divPageMask.style.width = "0px";
    divPageMask.style.height = "0px";
    divOpenWinImage.style.display = "none";
    window.onResize = null;
    var value = j$("input[name='coverImage']:checked").val();
    if(value != null){
    	var id = value.split("|")[0];
    	var name = value.split("|")[1];
   		j$("#coverName").val(name);
   		j$("#cover").val(id);
    }
    document.getElementById("divOpenWinImage").style.display = "none";
}
//ҳ������
function resizeMask() {
    divPageMask.style.width = document.body.scrollWidth;
    divPageMask.style.height = document.body.scrollHeight;
    divOpenWinImage.style.left = ((document.body.scrollWidth) / 10);
    divOpenWinImage.style.top = ((document.body.scrollHeight) / 10);
    
    divOpenWinImageText.style.left = ((document.body.scrollWidth) / 10);
    divOpenWinImageText.style.top = ((document.body.scrollHeight) / 10);
}
function DispalySelect(val) {  //��ʾ������select�ؼ�
    var dispalyType;
    var arrdispalyType = ["hidden", "visible"];
    var arrObjSelect = document.getElementsByTagName("select");
    for (i = 0; i < arrObjSelect.length; i++) {
        arrObjSelect[i].style.visibility = arrdispalyType[val];
    }
}

function showDiv() {
	document.getElementById("addDiv").style.display="";
	var radio = j$('input:radio:checked').val();//j$("#sendmsg").val();
}
function hideDiv() {
	document.getElementById("addDiv").style.display="none";
	var radio = j$('input:radio:checked').val();//j$("#sendmsg").val();
}

function checkForm() {
	if(isNull(j$("#title").val())) {
		alert("���ⲻ��Ϊ��");
		return false;
	}
	if(isNull(j$("#cover").val())) {
		alert("��ѡ�����");
		return false;
	}
	var multiImageTextInfo = "";
	j$("#multiImageText input[name$='_resID']").each(function(){
		var id = j$(this).val();
		multiImageTextInfo += id+"|";
	});
	multiImageTextInfo = multiImageTextInfo.substring(0, multiImageTextInfo.length-1);
	j$("#multiImageTextInfo").val(multiImageTextInfo);
	form1.submit();
	return true;
}

function preview(url){
	var imgDiv = document.getElementById("previewImage");
	imgDiv.innerHTML="<p align='center'><img src='" + url + "'>"+"<br><input type='button' class='button' onClick='closePreviewImage()' value='�ر�'></p>";
	imgDiv.style.left = ((document.body.scrollWidth) / 10);
	imgDiv.style.top = ((document.body.scrollHeight) / 10);
	imgDiv.style.display="block";
}
function closePreviewImage(){
	document.getElementById("previewImage").style.display = "none";
}
</script>
<input type="hidden" id="tmp_multiImageText" value="${wechatResource.multiresourceId }">
<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
 <tr>
  <td nowrap>
  	<b>ͼ����Ϣ�༭:</b>
  </td>
 </tr> 
</table>
<br>
<div id="divPageMask"></div>
<div id="divOpenWinImage">
<display:table name="requestScope.mediaList" id="media" sort="list" requestURI="imageText.do" cellspacing="1" cellpadding="3" class="table1" style="word-break:break-all" pagesize="15">
    <display:column title='ѡ��'>
    	<input type="radio" name="coverImage" value="${media.id}"/>
    </display:column>
     <display:column title='���'>
    	<c:out value="${media.id}"/>
    </display:column>
    <display:column title='����' sortable="true" sortName="no" >
    	<c:out value="${media.mediaName}"/>
    </display:column>
    <display:column title='����'>
    	<c:out value="${media.describe}"/>
    </display:column>
	<display:column title='<%=resource.srcStr("Main.op")%>' >
		<c:if test="${media.mediaResource == 0 }">
			<a href="javascript:preview('${pageContext.request.contextPath}/${media.mediaPath}')">Ԥ��</a>
		</c:if>
		<c:if test="${media.mediaResource == 1 }">
			<a href="javascript:preview('${media.mediaPath}')">Ԥ��</a>
		</c:if>
    </display:column>
</display:table>
<p align="center">
	<input name="button" type="button" class="button" onClick="closeImage()" value='ȷ��'>
</p>
</div>
<div id="previewImage"></div>
<div id="divOpenWinImageText">
<display:table name="requestScope.resList" id="res" sort="list" requestURI="imageText.do" cellspacing="1" cellpadding="3" class="table1" style="word-break:break-all" pagesize="15">
    <display:column title='ѡ��'>
    	<input type="hidden" id="imageText${res.id}" value="${res.resourceTittle}">
    	<input type="checkbox" name="imageTextNoCheckbox" value="${res.id}|${res.resourceTittle}"/>
    </display:column>
    <display:column title='����'>
    	<c:out value="${res.resourceTittle}"/>
    </display:column>
    <display:column title='��������'>
    	<c:out value="${res.createDate}"/>
    </display:column>
    <display:column title='����ʱ��'>
    	<c:out value="${res.createTime}"/>
    </display:column>
</display:table>
<p align="center">
	<input name="button" type="button" class="button" onClick="closeImageText()" value='ȷ��'>
</p>
</div>
 
<div id="tbody"> 
<form name="form1" method="post" action="imageText.do?action=mod">
<input type="hidden" id="resid" name="resid" value="${wechatResource.id}" >
<input type="hidden" id="multiImageTextInfo" name="multiImageTextInfo" value="" >
<table width="100%" border="0" cellspacing="1" cellpadding="3" class="table1">
	<tr class="tr3">
		<td width="10%" align="right">���⣺</td>
		<td width="90%"  align="left"><input type="text" id="title" name="title" value="${wechatResource.resourceTittle}" style="width:400px;"></td>
	</tr>
	<tr class="tr3">
	  <td width="10%" align="right">���棺</td>
	  <td width="90%" align="left">
	  	<input type="text" id="coverName" name="coverName" value="${originalMedia.mediaName}" style="width:400px;" readonly="readonly" onclick="getImage();">
	    <input type="hidden" id="cover" name="cover" value="${originalMedia.id}">
	  	<a href="#" onclick="getImage();">ѡ���ز�</a>&nbsp;
	  	
	  </td>
	</tr>
	<tr class="trr">
	  <td width="10%" align="right">����:</td>
	  <td width="90%" align="left"><FCK:editor instanceName="content" value="${wechatResource.resourceContent}" basePath="/fckeditor"></FCK:editor></td>
	</tr>
	<tr class="tr3">
		<td width="10%" align="right">ͼ������ :</td>
		<td width="90%"  align="left"><input type="text" id="outLink" name="outLink" value="${wechatResource.outLink}" style="width:400px;"></td>
	</tr>
	<tr class="tr3">
	  <td width="10%" align="right">��ͼ�ģ�</td>
	  <td width="90%" align="left" id="imageTextTd">
	  	<a href="#" onclick="addImageText();">����ͼ��</a>
	  </td>
	</tr>
</table>
<div id="multiImageTextDiv">
	<br>
	<table id="multiImageText" width="50%" border="0" cellspacing="1" cellpadding="3" class="table1">
	</table>
</div>
<p align="center">
	<input class="button" type="button" value='<%=resource.srcStr("Main.submit")%>' onClick="return checkForm()" />&nbsp;
	<input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>'  onclick="javascript:window.location='imageText.do?action=qry'" />
</p>
</form>			
</div>

</body>
</html>
