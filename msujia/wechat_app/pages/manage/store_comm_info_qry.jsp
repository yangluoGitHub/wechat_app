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

<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/interface/storeInfoService.js'></script>

<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/jquery.autocomplete.min.js" type="text/javascript"></script>

<link href="../styles/jquery.autocomplete.css" rel="stylesheet" type="text/css">
<link href="../styles/common.css" rel="stylesheet" type="text/css">
<link href="../styles/displaytag.css" rel="stylesheet" type="text/css">
</head>


<body onload="javascript:init();">
<!--javascript ���ʻ���ʶ��-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="Main.sure_delete" value='<%=resource.srcStr("Main.sure_delete")%>'/>
</div>

<script language="JavaScript">
function checkForm(){
	return true;
}
function restore(){
	  $('storeNo').value = "";
	  $('storeName').value = "";
}
function del(obj){
	  if(confirm(document.getElementById('Main.sure_delete').value)){
	    window.location="storeCommInfo.do?action=del&id="+obj;
	  }
}
function mod(obj){
	  window.location="storeCommInfo.do?action=modPage&id="+obj;
}
function completeStoreName(obj){
	var inputStr = $.trim(obj.value);
	var cto = $("#"+obj.id);
	//alert(obj.id);
	if(inputStr.length < 2){
		return false;
	}
	DWREngine.setAsync(false);
	storeInfoService.qryStoreInfo(inputStr, function(data){
		var jsonArray = JSON.parse(data);
		cto.autocomplete(jsonArray,{
			minChars:1,
			max:10,
			cacheLength:1,
			matchContains:true,
			formatItem:function(jsonArray,i,max){
				//return jsonArray.value+"|"+jsonArray.label;
				return jsonArray.label;
			},
			formatResult:function(jsonArray){
//				return jsonArray.label+"|"+jsonArray.value;
				return jsonArray.label;
			}
		});
	});
	DWREngine.setAsync(true);
	
}

	//��imageDestͼƬ�Ĵ�С����������,�ʺ���ʾ�ڿ�W�͸�H�������� 
	function ResizeImage(imageDest, W, H) {
		//��ʾ����W,�߶�H 
		var image = new Image();
		image.src = imageDest.src;
		if (image.width > 0 && image.height > 0) {
			//�Ƚ��ݺ�� 
			if (image.width / image.height >= W / H)//�����ʾ�򣺿�>�� 
			{
				if (image.width > W) //��ȴ�����ʾ����W��Ӧѹ���߶� 
				{
					imageDest.width = W;
					imageDest.height = (image.height * W) / image.width;
				} else //������ڻ������ʾ����W��ͼƬ��ȫ��ʾ 
				{
					imageDest.width = image.width;
					imageDest.height = image.height;
				}
			} else//ͬ�� 
			{
				if (image.height > H) {
					imageDest.height = H;
					imageDest.width = (image.width * H) / image.height;
				} else {
					imageDest.width = image.width;
					imageDest.height = image.height;
				}
			}
		}
	}
	
	//��ҳ��������ָ��id��ͼƬ���������� 
	function RsizeAllImageById(id, W, H) {
		var imgs = document.getElementsByTagName("img");
		for ( var i = 0; i < imgs.length; i++) {
			if (imgs[i].id == id) {
				ResizeImage(imgs[i], W, H);
			}
		}
	}
	function init() { 
		RsizeAllImageById("pictureLink", 150, 113); 
	} 

	function detail(obj) {
		window.location = "storeCommInfo.do?action=detail&id=" + obj;
	}
	function go() {
		var pageNum = document.getElementById('curPages').value;
		//alert(pageNum);//��ȡ��תҳ��
		var obj = document.getElementsByTagName("a");
		var herf = new String("");
		var k = obj.length - 1;
		var ahref = obj[k]; //��ȡҳ�������һ�����ӵ�ַ
		var _ahref = obj[k - 1]; //��ȡҳ���ϵ����ڶ������ӵ�ַ
		var change = 0;//��ĩҳ��ַ������ֵ=0
		herf = "" + ahref;
		_herf = "" + _ahref;
		//alert(herf);
		var m = herf.split("-p=");
		var k = _herf.split("-p=");
		if (k.length > 1) {
			var j = k[1].split("&");
			if (parseInt(j[0]) == 1) {
				change = 1;//�������ڶ�����������ҳʱ������ֵ=1
			}
		}
		if (m.length > 1) {
			var n = m[1].split("&");
			var totalNum = n[0];
			totalNum = parseInt(totalNum) + change;
			if (isNull(pageNum)) {
				alert(document.getElementById('Main.qryEmptyPrompt').value);
				document.getElementById('curPages').select();
				return false;
			} else if (!isInteger(pageNum)) {
				alert(document.getElementById('Main.input_correct_page').value);
				document.getElementById('curPages').select();
				return false;
			} else if (parseInt(pageNum) <= 0) {
				alert(document.getElementById('Main.pageLowerLimitPrompt').value);
				document.getElementById('curPages').select();
				return false;
			} else if (parseInt(pageNum) > parseInt(totalNum)) {
				alert(document.getElementById('Main.pageUpperLimitPrompt').value);
				document.getElementById('curPages').select();
				return false;
			}
		}
		herf = herf.replace(/d-(\d+)-p=(\d+)/g, "d-$1-p=" + pageNum);//�õ���תҳ��ַ
		//alert(herf);
		window.location = herf;//ת����תҳ
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
    <td nowrap><b>��Ʒ����</b></td> 
    <td nowrap align="right">
       <input class="button" type="button" value='�����Ʒ' onclick="javascript:window.location='storeCommInfo.do?action=addPage'" style="width:110px;">&nbsp;
    </td>
  </tr>
</table>
<br>
<form name="form1" method="post" action="storeCommInfo.do?action=qry">
<table width="100%" border="0" cellspacing="1" cellpadding="3" class="table1">
  	<tr class="tr1">
    <td nowrap colspan="12">
      <%=resource.srcStr("System.query") %>��
    </td>
  	</tr>
    <tr class="tr3">
    <td nowrap>�ŵ����ƣ�</td>
    <td nowrap>
	<input type="text" id="storeName" name="storeName" onkeyup="completeStoreName(this)" style="width:130px;" maxlength="16" /></td>
    <td nowrap>��Ʒ���ƣ�</td>
    <td nowrap>
	<input type="text" name="commName" style="width:130px;" maxlength="16" value="<c:out value="${commName}"/>" /></td>      	
	</td>
	<td nowrap>�������ƣ�</td>
    <td nowrap>
	<input type="text" name="commName" style="width:130px;" maxlength="16" value="<c:out value="${commName}"/>" /></td>      	
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
      <%=resource.srcStr("System.results") %>��&nbsp;&nbsp;&nbsp;
    </td>
  </tr>
</table>

<display:table name="requestScope.storeCommInfoList" id="commInfo" requestURI="storeCommInfo.do" cellspacing="1" cellpadding="3" class="table1" style="word-break:break-all" pagesize="15">
	<display:column title='<%=resource.srcStr("Main.op")%>'>
			<a href="javascript:mod('<c:out value="${commInfo.id}"/>')"><%=resource.srcStr("System.mod")%></a>&nbsp;
        	<a href="javascript:del('<c:out value="${commInfo.id}"/>')"><%=resource.srcStr("Main.delete")%></a>
    </display:column>
    <display:column title='��Ʒ����'>
    	<span style="align:center;width:100;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">
    		<a href="javascript:detail('<c:out value="${commInfo.id}"/>')"><c:out value="${commInfo.name}"/></a>
    	</span>
    </display:column>
    <display:column title='ͼƬ'>
    	<span style="align:center;width:100;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">
    		<img src="../${commInfo.pictureLink }" title="${commInfo.name}" name="pictureLink" width="150" height="113" border="0" id="pictureLink" />
    	</span>
    </display:column>
    
	<display:column title='��Ʒ����'>
		<span style="align:center;width:100;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">
			<a href="javascript:detail_comm_cf('<c:out value="${commInfo.storeCommClassificationInfo.id}"/>')"><c:out value="${commInfo.storeCommClassificationInfo.commClassification}"/>
		</span>
	</display:column>
	<display:column title='ԭ��'>
		<span style="align:center;width:100;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">
			<c:out value="${commInfo.price}"/>
		</span>	
	</display:column>
	<display:column title='�Żݼ�'>
		<span style="align:center;width:100;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">
			<c:out value="${commInfo.preferentialPrice}"/>
		</span>	
	</display:column>
	<display:column title='���'>
		<span style="align:center;width:100;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">
			<c:out value="${commInfo.onhand}"/>
		</span>	
	</display:column>
	<display:column title='�Ƿ��ϼ�'>
		<span style="align:center;width:100;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">
			<c:out value="${commInfo.onShelves}"/>
		</span>	
	</display:column>
	<display:column title='�����ŵ�'>
		<span style="align:center;width:100;white-space:nowrap;overflow:hidden;text-overflow:ellipsis">
			<a href="javascript:detail_store_info('<c:out value="${commInfo.storeInfo.id}"/>')"><c:out value="${commInfo.storeInfo.storeName}"/>
		</span>	
	</display:column>
</display:table>
</body>
</html>





