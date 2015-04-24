<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page language="java"%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page import="com.weili.wechat.common.UserSession"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<script type="text/javascript" src="../pages/tree/js/tree2.js"></script>
<script type="text/javascript" src="../pages/Mtree/MzTreeView10.js"></script>
<link rel="StyleSheet" href="../pages/tree/css/tree.css" type="text/css">
<link href="../styles/common.css" rel="stylesheet" type="text/css">
<title></title>


<script language=javascript>

var wp=window.parent;
var cf=wp.document.getElementById("orgFrame");
//var orgTypeId=wp.document.getElementById("orgTypeId").value;
function resizeCalendar(){
  cf.width=180;
  cf.height=300;
//  setGradient('orgTree','#F2FDDB','#E8F5FE',1); 
//  setGradient('close','#F2FDDB','#E8F5FE',1); 
//  setGradient('sertch','#F2FDDB','#E8F5FE',1); 
//  setGradient('tr','#F2FDDB','#E8F5FE',1); 
  
}

</script>

<%-- 
<script> 

var setGradient = (function(){ 

//private variables; 
var p_dCanvas = document.createElement('canvas'); 
var p_useCanvas = !!( typeof(p_dCanvas.getContext) == 'function'); 
var p_dCtx = p_useCanvas?p_dCanvas.getContext('2d'):null; 
var p_isIE = /*@cc_on!@*/false; 


//test if toDataURL() is supported by Canvas since Safari may not support it 

try{ p_dCtx.canvas.toDataURL() }catch(err){ 
p_useCanvas = false ; 
}; 

if(p_useCanvas){ 

return function (dEl , sColor1 , sColor2 , bRepeatY ){ 

if(typeof(dEl) == 'string') dEl = document.getElementById(dEl); 
if(!dEl) return false; 
var nW = dEl.offsetWidth; 
var nH = dEl.offsetHeight; 
p_dCanvas.width = nW; 
p_dCanvas.height = nH; 


var dGradient; 
var sRepeat; 
// Create gradients 
if(bRepeatY){ 
dGradient = p_dCtx.createLinearGradient(0,0,nW,0); 
sRepeat = 'repeat-y'; 
}else{ 
dGradient = p_dCtx.createLinearGradient(0,0,0,nH); 
sRepeat = 'repeat-x'; 
} 

dGradient.addColorStop(0,sColor1); 
dGradient.addColorStop(1,sColor2); 

p_dCtx.fillStyle = dGradient ; 
p_dCtx.fillRect(0,0,nW,nH); 
var sDataUrl = p_dCtx.canvas.toDataURL('image/png'); 

with(dEl.style){ 
backgroundRepeat = sRepeat; 
backgroundImage = 'url(' + sDataUrl + ')'; 
backgroundColor = sColor2; 
}; 
} 
}else if(p_isIE){ 

p_dCanvas = p_useCanvas = p_dCtx = null; 
return function (dEl , sColor1 , sColor2 , bRepeatY){ 
if(typeof(dEl) == 'string') dEl = document.getElementById(dEl); 
if(!dEl) return false; 
dEl.style.zoom = 1; 
var sF = dEl.currentStyle.filter; 
dEl.style.filter += ' ' + ['progid:DXImageTransform.Microsoft.gradient( GradientType=', +(!!bRepeatY ),',enabled=true,startColorstr=',sColor1,', endColorstr=',sColor2,')'].join(''); 

}; 

}else{ 

p_dCanvas = p_useCanvas = p_dCtx = null; 
return function(dEl , sColor1 , sColor2 ){ 

if(typeof(dEl) == 'string') dEl = document.getElementById(dEl); 
if(!dEl) return false; 
with(dEl.style){ 
backgroundColor = sColor2; 
}; 
//alert('your browser does not support gradient effet'); 
} 
} 
})(); 

</script>
--%>
</head>
<script type='text/javascript' src='../dwr/interface/OrgSelectService.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>

<input type="hidden" id="orgTypeId" value="<%=request.getParameter("orgTypeId")%>"/>
<input type="hidden" id="oImg" value="<%=request.getParameter("oImg")%>"/>
<input type="hidden" id="orgNo" value="<%=request.getParameter("orgNo")%>"/>

<script type="text/javascript">
//模糊查询
function qryOrgByFilter(orgNo){
		//var orgNo=document.getElementById("org").value;
		
		var filter=document.getElementById("filter").value;
		var orgTypeId = document.getElementById("orgTypeId").value;
		//var orgNo = document.getElementById("orgNo").value;
		//if(orgNo==null || orgNo=="" || orgNo == "null"){
			//OrgSelectService.qryOrgByFilter(filter,orgTypeId,buildTree1);
		//}else{
			OrgSelectService.qryOrgByOrgNo(filter,orgTypeId,orgNo,buildTree);
		//}
			
		
}
//建机构树
function buildTree(data){
	if(data == null || data.length == 0) {
		document.getElementById("tr").innerHTML = "No Result";
		return ;
	}
	d = new dTree('d');
	d.config.useIcons = false;
	//var orgNo=document.getElementById("org").value;	
	for(var i = 0; i<data.length; i++) {
		var no = data[i][0];
		var father = data[i][1];
		var name = data[i][2];
		if(i!=0){
			//if(no == orgNo) {
				//d.add(no,-1,name,"javascript:setOrg('"+no+"','"+name+"')",'','workarea');
			//} else {
				d.add(no,father,name,"javascript:setOrg('"+no+"','"+name+"')",'','workarea');
			//}
		} else {
			d.add(no,-1,name,"javascript:setOrg('"+no+"','"+name+"')",'','workarea');
		}
	}
	document.getElementById("tr").innerHTML = d;
	d.openAll();
}

function buildTree1(data){
	if(data == null || data.length == 0) {
		document.getElementById("tr").innerHTML = "No Result";
		return ;
	}
	d = new dTree('d');
	d.config.useIcons = false;
	//var orgNo=document.getElementById("org").value;	
	for(var i = 0; i<data.length; i++) {
		var no = data[i][0];
		var father = data[i][1];
		var name = data[i][2];
		if(father != null){
			//if(no == orgNo) {
				//d.add(no,-1,name,"javascript:setOrg('"+no+"','"+name+"')",'','workarea');
			//} else {
				d.add(no,father,name,"javascript:setOrg('"+no+"','"+name+"')",'','workarea');
			//}
		} else {
			d.add(no,-1,name,"javascript:setOrg('"+no+"','"+name+"')",'','workarea');
		}
	}
	document.getElementById("tr").innerHTML = d;
	d.openAll();
}
//保留未用到
function setOrgM(data){
	if(data == null || data.length == 0) {
		document.getElementById("tr").innerHTML = "No Result";
		return ;
	}
	var orgNo=document.getElementById("org").value;
	var tree = new MzTreeView('tree');
	alert(1);
  	tree.icons["css"] = "../Mtree/collection.gif";
	alert(2);
	for(var i = 0; i<data.length; i++) {
		var no = data[i][0];
		var father = data[i][1];
		var name = data[i][2];
		if(father!=null){
			if(no == orgNo) {
				tree.nodes["0_"+no] = "text:"+name+";icon:css;data:id="+no+";url:javascript:setOrg('"+no+"','"+name+"')";
			} else {
				tree.nodes[father+"_"+no] = "text:"+name+";icon:css;data:id="+no+";url:javascript:setOrg('"+no+"','"+name+"')";
			}
		} else {
			tree.nodes["0_"+no] = "text:"+name+";icon:css;data:id="+no+";url:javascript:setOrg('"+no+"','"+name+"')";
		}
	}
	document.getElementById("tr").innerHTML = tree.toString();
}

</script>
<body topmargin=0 leftmargin=0 bottommargin=0 rightmargin=0 bgcolor=white onload="resizeCalendar();">
<%
	UserSession user = (UserSession)session.getAttribute("userSession");
	String orgNo = user.getOrgNo();
%>

<div id="orgTree" class=""  style="background-color:#DBE5EE; border: 1px solid;position:absolute; left:0px; top:0px; width=180px; height=300px; overflow: auto; visibility:visible;z-index:auto;">
<div id="close" align="right" class=""><img src="../images/close.gif" onclick="javascript:wp.hideOrgSelector();"></div>
<div id="sertch" align="left" class=""><input type="text" name="filter" size="14" style="" onpropertychange="qryOrgByFilter('<%=orgNo %>');">&nbsp;<input type="submit" value="搜索" onclick="qryOrgByFilter('<%=orgNo %>');"></div><br>
<div id="tr" align="left" class=""></div>


<script language="JavaScript">

//function op(){ 
    //构造机构树
	d = new dTree('d');
	d.config.useIcons = false;	
	//var obj=new Object();
	//obj=d0;
	//j=0;
	//var tree="d"+j+"= new dTree('d"+j+"')";
	//var conf="d"+j+".config.useIcons = false";
	//var str="obj=d"+j;
	//eval(tree);
	//eval(str);
	//eval(conf);
<%          
    int orgType = 1;//1:银行 2：维护商
    //int orgTypeCur = Integer.parseInt((String)request.getParameter("orgTypeId"));
    StringBuffer sb = new StringBuffer();  
    java.util.TreeMap orgTreeMap = (java.util.TreeMap)session.getAttribute("_orgTreeMap");
    //if (orgType==1 && orgTypeCur==2 ){
   	//	orgTreeMap=(java.util.TreeMap)session.getAttribute("_orgTreeMap2");
   	//	sb.append("d.add('top',-1,'','','','workarea');");
   	//}
    int size = orgTreeMap.size();
    java.util.HashMap org;    
   
    for(int i=1;i<=size;i++){
    	org = (java.util.HashMap)orgTreeMap.get(String.valueOf(i));
    	String no = (String)org.get("no");    
    	Object father = org.get("father"); 
    	//if (orgType==1 && orgTypeCur==2 ){
	    //	if (father==null||father.toString().equals("")){
	    //		father="top";
	    //	}
	    //	sb.append("d.add('").append(no).append("','").append(father).append("','").append(org.get("name")).append("',\"javascript:setOrg('").append(no).append("','").append(org.get("name")).append("')\",'','workarea');");
	    //}else{
		    if(orgNo.equals(no)){
	    		sb.append("d.add('").append(no).append("',-1,'").append(org.get("name")).append("',\"javascript:setOrg('").append(no).append("','").append(org.get("name")).append("')\",'','workarea');");
	    	}else{
	    	    sb.append("d.add('").append(no).append("','").append(org.get("father")).append("','").append(org.get("name")).append("',\"javascript:setOrg('").append(no).append("','").append(org.get("name")).append("')\",'','workarea');");
	    	}
	    //}
    }
    out.print(sb.toString());
%>
	document.getElementById("tr").innerHTML = d;
//}

</script>

</div>



<script language=javascript>
var bCalLoaded=true;
</script>
</body>
</html>