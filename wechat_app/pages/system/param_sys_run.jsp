<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title><%=resource.srcStr("System.set_para")%></title>
<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<link href="../styles/common.css" rel="stylesheet" type="text/css">
</head>

<body>
<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="System.integer_para" value='<%=resource.srcStr("System.integer_para")%>'/>
<input type="hidden" id="System.para_less" value='<%=resource.srcStr("System.para_less")%>'/>
<input type="hidden" id="System.para_longer" value='<%=resource.srcStr("System.para_longer")%>'/>
<input type="hidden" id="System.integer_para" value='<%=resource.srcStr("System.integer_para")%>'/>
<input type="hidden" id="System.para_less" value='<%=resource.srcStr("System.para_less")%>'/>
<input type="hidden" id="System.para_longer" value='<%=resource.srcStr("System.para_longer")%>'/>
<input type="hidden" id="System.integer_para" value='<%=resource.srcStr("System.integer_para")%>'/>
<input type="hidden" id="System.para_less" value='<%=resource.srcStr("System.para_less")%>'/>
<input type="hidden" id="System.para_longer" value='<%=resource.srcStr("System.para_longer")%>'/>
<input type="hidden" id="system.passwdLessDate" value='<%=resource.srcStr("system.passwdLessDate")%>'/>
<input type="hidden" id="system.pdLongerMessage" value='<%=resource.srcStr("system.pdLongerMessage")%>'/>
<input type="hidden" id="system.parammessage1" value='<%=resource.srcStr("system.parammessage1")%>'/>
<input type="hidden" id="system.parammessage2" value='<%=resource.srcStr("system.parammessage2")%>'/>
<input type="hidden" id="system.parammessage3" value='<%=resource.srcStr("system.parammessage3")%>'/>

<!-- 新增 -->
<input type="hidden" id="system.parammessage4" value='<%=resource.srcStr("system.parammessage4")%>'/>
<input type="hidden" id="system.auditFlag.format" value='<%=resource.srcStr("system.auditFlag.format")%>'/>
</div>

<script language="JavaScript">
var j$ = jQuery.noConflict();
j$().ready(function(){
	j$("#passwordUpdateDate").add("#passwdtxDate").add("#passwdmaxCount").onlyNumber();
});


//更新参数
function updateParam(param1,param2){    

        if(param1 == "passwordUpdateDate"){
          if(! isInteger(param2)){
            alert(document.getElementById('System.integer_para').value);
            return false;
          }
          if(!(param2>=10 && param2<=1000)){
            alert(document.getElementById('system.parammessage2').value);
            form1.passwordUpdateDate.select();
            return false;
          }
          var passwdtxDate = document.getElementById('passwdtxDate').value;
          if(!isNull(passwdtxDate)) {
             if( parseInt(param2) <= parseInt(passwdtxDate) ){
                  alert(document.getElementById('system.pdLongerMessage').value);
                  form1.passwordUpdateDate.select();
                  return false;
             }
          }
        }
        if(param1 == "passwdtxDate"){
          if(! isInteger(param2)){
            alert(document.getElementById('System.integer_para').value);
            return false;
          }
          if(!(param2>=10 && param2 <=1000)){
            alert(document.getElementById('system.parammessage1').value);
            form1.passwdtxDate.select();
            return false;
          }      
          var passwordUpdateDate = document.getElementById('passwordUpdateDate').value;
          if(!isNull(passwordUpdateDate)) {
	          if(parseInt(param2) >= parseInt(passwordUpdateDate) ){
	            alert(document.getElementById('system.passwdLessDate').value);
	            form1.passwdtxDate.select();
	            return false;
	          }
	      }
        }
        
        //验证密码最大输入次数
        if(param1 == "passwdmaxCount"){
			if(!isInteger(param2)){
				alert(document.getElementById('System.integer_para').value) ;
				return false ;
			}
			if(!(param2>=1&&param2<=1000)){
				alert(document.getElementById('system.parammessage4').value);
	            form1.passwdmaxCount.select();
	            return false;
			}
        }
       
         if(param1 == "auditFlag"){
         	var group = document.getElementsByName('auditFlag');
   			for(var i = 0; i< group.length; i++)
       			if(group[i].checked) {
           			param2=group[i].value
           	}
          if(param2!="false" && param2!="true"){
            alert(document.getElementById('system.auditFlag.format').value);
            return false;
          }
        }
        
        CollectGarbage();
        var url = 'param.do';
        var pars ="action=setParam&catalog=1&paramName="+param1+"&paramValue="+param2+"&="+new Date();
        var myAjax = new Ajax.Request(url, {method: 'get', parameters: pars,evalScripts:true,onComplete:complete});   
}

//更新结果

function complete(originalRequest){
    if(originalRequest.responseText.length>100){//session超时
        form1.action ='param.do?action=getParam&catalog=1';
        form1.submit();
   }else{
      alert(originalRequest.responseText);
   }
}
function isip(s){ 
	var check=function(v){try{return (v<=255 && v>=0)}catch(x){return false}}; 
	var re=s.split(".") 
	return (re.length==4)?(check(re[0]) && check(re[1]) && check(re[2]) && check(re[3])):false 
} 
</script>

<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
 <tr>
  <td nowrap><b><%=resource.srcStr("System.set_para")%></b></td>
 </tr> 
</table>
 <br>

<form name="form1" method="post">
<table width="650" border="0" cellspacing="1" cellpadding="3" class="table1" align="center">

        <tr class="tr1" >
		  <td nowrap width="180"><%=resource.srcStr("System.para_name")%></td>
		  <td nowrap width="180"><%=resource.srcStr("System.para_value")%></td>		  
		  <td nowrap><%=resource.srcStr("System.para_explan") %></td>
		  <td nowrap width="90"><%=resource.srcStr("Main.op")%></td>
		</tr>
		<tr class="tr3" >
		  <td nowrap><%=resource.srcStr("system.passwordUpdateDate") %>：</td>	  
		  <td nowrap><input type="text" name="passwordUpdateDate" id='passwordUpdateDate' style="width:150px" value="<c:out value="${passwordUpdateDate}"/>" maxLength="4"></td>
		  <td nowrap><%=resource.srcStr("System.unit_day")%></td>
		  <td nowrap>
		    <input type="button" class="button" value='<%=resource.srcStr("System.set")%>' style="width:50px;" onclick="updateParam('passwordUpdateDate',form1.passwordUpdateDate.value)">
		  </td>		  
		</tr>
		
        <tr class="tr3" >
		  <td nowrap><%=resource.srcStr("system.passwdtxDate") %>：</td>	  
		  <td nowrap><input type="text" name="passwdtxDate" id='passwdtxDate' style="width:150px" value="<c:out value="${passwdtxDate}"/>" maxLength="4"></td>
		  <td nowrap><%=resource.srcStr("System.unit_day")%></td>
		  <td nowrap>
		    <input type="button" class="button" value='<%=resource.srcStr("System.set")%>' style="width:50px;" onclick="updateParam('passwdtxDate',form1.passwdtxDate.value)">
		  </td>		  
		</tr>
		<tr class="tr3" >
		  <td nowrap><%=resource.srcStr("system.passwdmaxCount") %>：</td>	  
		  <td nowrap>
		  	<input type="text" name="passwdmaxCount" id="passwdmaxCount" style="width:150px" value="<c:out value="${passwdmaxCount}"/>" maxLength="5">
		  </td>
		  <td nowrap><%=resource.srcStr("System.unit_num")%></td>
		  <td nowrap>
		    <input type="button" class="button" value='<%=resource.srcStr("System.set")%>' style="width:50px;" onclick="updateParam('passwdmaxCount',form1.passwdmaxCount.value)">
		  </td>		  
		</tr>
		<%-- 
		 <tr class="tr3" >
		  <td nowrap><%=resource.srcStr("system.auditFlag") %>：</td>	  
		  <td nowrap>
		  	<input type="radio" name="auditFlag" value="true" <c:out value="${auditFlag=='true'?'checked':''}"/>><%=resource.srcStr("system.auditFlag.yes") %>
		  	<input type="radio" name="auditFlag" value="false"<c:out value="${auditFlag=='false'?'checked':''}"/>><%=resource.srcStr("system.auditFlag.no") %>
		  </td>
		  <td nowrap><%=resource.srcStr("system.auditFlag.yes") %>/<%=resource.srcStr("system.auditFlag.no") %></td>
		  <td nowrap>
		    <input type="button" class="button" value='<%=resource.srcStr("System.set")%>' style="width:50px;" onclick="updateParam('auditFlag',form1.auditFlag.value)">
		  </td>		  
		</tr>
		 --%>
</table>
</form>
</body>
</html>
