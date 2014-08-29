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
<title>���ͼ���ز�</title>
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
<script language="JavaScript">
var j$ = jQuery.noConflict();

j$().ready(function(){
}); 

function addImageText(){
	j$("#imageText input[name='imageTextNoCheckbox']").removeAttr("checked");
	 //����select�ؼ�
    DispalySelect(0);
    //��ʾ���ֲ�
    divPageMask.style.display = "block";
    //�������ֲ�
    resizeMask();
    window.onResize = resizeMask;
    //��ʾ��������
    divOpenWinImageText.style.display = "block";
}

var array;
//��ͼ��ѡ��
function chooseImageText() {
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
	var table = j$("#multiImageText");
	table.empty();
	
	var head_html="<tr class='tr2'><td nowrap width='10%' align='center'>����</td><td nowrap width='10%' align='center'>˳��</td>"
		+"<td nowrap width='40%'>ͼ��ID</td><td nowrap width='40%'>ͼ�ı���</td></tr>";
	if(array.length > 0){
		table.append(head_html);
	}
	for (var i = 0; i < array.length ; i++) {
		//j$("#imageTextTd").append("<div id='HRimageTextTd"+array[i]+"'><hr color='#D7DBE6' width='99%'/></div>");
		//j$("#imageTextTd").append("<div id='imageTextTd"+array[i]+"'>"+j$("#imageText" + array[i]).val()+"&nbsp;&nbsp;&nbsp;<a href=\"javascript:del('imageTextTd" + array[i] + "')\">ɾ��</a></div>");
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
	closeSubWindow("divOpenWinImageText");
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
    divOpenWinImage.style.display = "block";
}

//ѡ��ͼƬ�ز�
function chooseImage() {
    closeSubWindow("divOpenWinImage");
    var value = j$("input[name='coverImage']:checked").val();
    if(value != null){
    	var id = value.split("|")[0];
    	var name = value.split("|")[1];
   		j$("#coverName").val(name);
   		j$("#cover").val(id);
    }
}

//�ر��Ӵ���div
function closeSubWindow(mydiv) {
    //��ʾselect�ؼ�
    DispalySelect(1);
    //�������ֲ�
    divPageMask.style.width = "0px";
    divPageMask.style.height = "0px";
    document.getElementById(mydiv).style.display = "none";
    window.onResize = null;
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
	previewImage.style.display = "none";
}
</script>

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
    	<input type="radio" name="coverImage" value="${media.id}|${media.mediaName}"/>
    </display:column>
    <display:column title='����' sortable="true" sortName="no">
    	<c:out value="${media.mediaName}"/>
    </display:column>
    <display:column title='����'>
    	<c:out value="${media.describe}"/>
    </display:column>
    <display:column title='����ʱ��'>
    	<c:out value="${media.createDate} ${media.createTime}"/>
    </display:column>
	<display:column title='<%=resource.srcStr("Main.op")%>' >
		<c:if test="${media.mediaResource == 0}">
			<a href="javascript:preview('${pageContext.request.contextPath}/${media.mediaPath}')">Ԥ��</a>
		</c:if>
		<c:if test="${media.mediaResource == 1}">
			<a href="javascript:preview('${media.mediaPath}')">Ԥ��</a>
		</c:if>
    </display:column>
</display:table>
<p align="center">
	<input name="button" type="button" class="button" onClick="chooseImage()" value='ȷ��'>&nbsp;
	<input name="button" type="button" class="button" onClick="closeSubWindow('divOpenWinImage');" value='ȡ��'>
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
    <display:column title='����ʱ��' sortable="true">
    	<c:out value="${res.createDate} ${res.createTime}"/>
    </display:column>
</display:table>
<p align="center">
	<input name="button" type="button" class="button" onClick="chooseImageText()" value='ȷ��'>&nbsp;
	<input name="button" type="button" class="button" onClick="closeSubWindow('divOpenWinImageText')" value='ȡ��'>
</p>
</div>
 
<div id="tbody"> 
<form name="form1" method="post" action="imageText.do?action=add">
<input type="hidden" id="multiImageTextInfo" name="multiImageTextInfo" value="" >
<table width="100%" border="0" cellspacing="1" cellpadding="3" class="table1">
	<tr class="tr3">
		<td width="10%" align="right">���⣺</td>
		<td width="90%"  align="left"><input type="text" id="title" name="title" value="${title}" style="width:400px;"></td>
	</tr>
	<tr class="tr3">
	  <td width="10%" align="right">���棺</td>
	  <td width="90%" align="left">
	    <input type="text" id="coverName" name="coverName" style="width:400px;" readonly="readonly" onclick="getImage();">
	    <input type="hidden" id="cover" name="cover" >
	  	<a href="#" onclick="getImage();">ѡ���ز�</a>&nbsp;
	  </td>
	</tr>
	<tr class="trr">
	  <td width="10%" align="right">����:</td>
	  <td width="90%" align="left"><FCK:editor instanceName="content" value="${content}" basePath="/fckeditor"></FCK:editor></td>
	</tr>
	<tr class="tr3">
		<td width="10%" align="right">ͼ������ :</td>
		<td width="90%"  align="left"><input type="text" id="outLink" name="outLink" value="${outLink}" style="width:400px;"></td>
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
	<input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.location='imageText.do?action=qry'" />
</p>
</form>			
</div>

</body>
</html>
