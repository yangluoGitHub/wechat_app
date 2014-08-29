<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page import="java.util.*" %>
<%@ page import="com.weili.wechat.vo.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>

<script type='text/javascript' src='../dwr/interface/roleService.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>

<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<script type="text/javascript" src="../resource/js/dtree.js"></script>
<script type="text/javascript" src="../resource/js/op.js"></script>
<link href="../resource/css/dtree.css" rel="StyleSheet" type="text/css">
</head>

<body onload="chooseRoleButton(form1.orgType.value);">
<%@ include file="../../scripts/common.jsp" %>
<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<input type="hidden" id="System.role_name_longer" value='<%=resource.srcStr("System.role_name_longer")%>'/>
<input type="hidden" id="System.remark_longer" value='<%=resource.srcStr("System.remark_longer")%>'/>
<input type="hidden" id="System.exists_role" value='<%=resource.srcStr("System.exists_role")%>'/>
<input type="hidden" id="System.input_role_name" value='<%=resource.srcStr("System.input_role_name")%>'/>
<input type="hidden" id="System.select_role_type" value='<%=resource.srcStr("System.select_role_type")%>'/>
<input type="hidden" id="System.menu_sys_fun" value='<%=resource.srcStr("System.menu_sys_fun")%>'/>
<input type="hidden" id="System.menu_selectRole" value='<%=resource.srcStr("System.menu_selectRole")%>'/>
<input type="hidden" id="system.checkRoleChar" value='<%=resource.srcStr("system.checkRoleChar")%>'/>
<input type="hidden" id="system.rolemeg1" value='<%=resource.srcStr("system.rolemeg1")%>'/>
</div>


<script language="JavaScript">
var j$ = jQuery.noConflict();
j$().ready(function(){
	j$("#roleName").blur(function(){
		var roleName = j$.trim(j$("#roleName").val());
		if(roleName == '${role.name}') return;
		roleService.checkIsExistByName(roleName, function(data){
			if(data){
				alert("该角色名称已存在！");
				j$("#roleName").val("");
				j$("#roleName").focus();
			}
		});
	});
});

tree = new dTree('tree');
tree.add('0','-1','','#','','');

function check(obj1,obj2){
	if(document.getElementById(obj1).checked == true){
		document.getElementById(obj2.value).checked = true;
	}
	
}
function check1(obj){
	if(document.getElementById(obj.value).checked == false){
			roleService.qryButtonByMenu(obj.value,
				function checked(data){
					if(data.length>0){
						for(i=0;i<data.length;i++){
							//alert(data[i]);
							document.getElementById(data[i].no).checked = false;
						}					
					}
				}
		);
	}
}

</script>

<br>

<form name="form1" method="post" action="role.do?action=mod">

<input type="hidden" name="roleNo" value="<c:out value="${role.no}"/>">
<input type="hidden" name="roleCatalog" value="<c:out value="${role.catalog}"/>"><!-- 角色类型:其他操作员 -->
<%-- <input type="hidden" id="orgType" name="orgType" value="${role.orgType }"/>
 --%>
<table align="center" width="850" border="0" cellspacing="1" cellpadding="3" class="table1">
  <tr class="tr1">
    <td nowrap colspan="2" align="center"><%=resource.srcStr("System.mod_role_info")%></td>
  </tr>  
  <tr class="tr3">
    <td nowrap width="100"><%=resource.srcStr("System.role_name") %>：</td>
    <td nowrap><input type="hidden" name="beforeRoleName" value='<c:out value="${role.name}"/>' /><input id="roleName" type="text" name="roleName" class="pane" style="width:153;" value='<c:out value="${role.name}"/>' onKeyPress="if(event.keyCode<48 || event.keyCode ==92) event.returnValue=false" maxLength="40">&nbsp;*</td> 
  </tr>
  <!-- 
  <tr class="tr3">
    <td nowrap>角色级别：</td>
    <td>
	<SELECT  id="orgType" name="orgType" style="width:153;" onchange="chooseRoleButton(this.value)">
		<option value=''>-------请选择--------</option>
			<c:forEach items="${sysOrgGradeList}" var="sysOrgGrade">
				<option value='<c:out value="${sysOrgGrade.no}" />' <c:if test="${sysOrgGrade.no==role.orgType}">selected</c:if>><c:out value="${sysOrgGrade.name}角色" /></option>					
			</c:forEach>
	</SELECT>&nbsp;*
	</td>
  </tr>
   -->
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.role_des") %>：</td>
    <td nowrap><textarea name="note" style="width:400px;height:50px;"><c:out value="${role.note}"/></textarea></td> 
  </tr>
<tr class="tr3">
    <td nowrap valign="top"><br><%=resource.srcStr("System.role_function") %>：</td>
    <td nowrap><a href="#" onclick="javascript: d.openAll();"><%=resource.srcStr("System.expand")%></a> | <a href="#" onclick="javascript: d.closeAll();"><%=resource.srcStr("System.gather") %></a> | <a href="#" onclick="javascript: select(true);chooseRoleButton(form1.orgType.value);"><%=resource.srcStr("Main.All-elects")%></a> | <a href="#" onclick="javascript: select(false);"><%=resource.srcStr("System.no_select")%></a>
    <br><br>
    <script type="text/javascript">
    	d = new dTree('d');
		d.config.folderLinks=false;
		//id, pid, name, url, title, target, icon, iconOpen, open		
		d.add('0','-1',document.getElementById('System.menu_sys_fun').value,'#',document.getElementById('System.menu_sys_fun').value,'');
		<%   
    		List menuList = (List)request.getAttribute("menuList");
    		List buttonList = (List)request.getAttribute("buttonList");
    		List menucheckedList = (List)request.getAttribute("menucheckedList");
    		List buttoncheckedList = (List)request.getAttribute("buttoncheckedList");
    		Menu aMenu = null;
    		Button aButton = null;
     		String buttons = "&nbsp;&nbsp;";
    		for(int i=0;i<menuList.size();i++){
    			aMenu = (Menu)menuList.get(i);
    			buttons = "&nbsp;&nbsp;";
    			for(int j=0;j< buttonList.size();j++){
    				aButton = (Button)buttonList.get(j);
    				if(aButton.getMenu().getNo().equals(aMenu.getNo())){
    					// add by zxgao 去掉删除清机中心和重置密码按钮，此权限只有系统管理员有。 button no = 69/10
    					if(aButton.getNo() == 69 || aButton.getNo() == 10) continue;
    					buttons = buttons + "<span style=\"border:0px;border-bottom:1px solid #cccccc;color=steelblue\"><input name=button id="+aButton.getNo()+" type=checkbox "+buttoncheckedList.get(j)+"  value="+aButton.getNo()+" onclick=change(this);check("+aButton.getNo().toString()+","+aMenu.getNo()+")>"+aButton.getName()+"</span>";
    	%>
    					document.write("<input type='hidden' name='id<%= aButton.getNo() %>' value='<%= aButton.getName() %>'>");
    					tree.add('<%= aButton.getNo() %>','<%=aMenu.getSuperMenu().getNo()%>','<%= aButton.getName() %>','#','','');
    	<%
    				}
    			}
    	%>
    	document.write("<input type='hidden' name='id<%= aMenu.getNo() %>' value='<%= aMenu.getName() %>'>");
    	d.add('<%= aMenu.getNo() %>','<%= aMenu.getSuperMenu().getNo() %>','<input type="checkbox" <%=menucheckedList.get(i)%> name="menu"  id="<%=aMenu.getNo() %>" onclick="change(this);check1(this)" level="<%= aMenu.getLevel() %>" value="<%= aMenu.getNo() %>" ><%= aMenu.getName() %><%= buttons %>','#','','');
    	tree.add('<%= aMenu.getNo() %>','<%= aMenu.getSuperMenu().getNo() %>','<%= aMenu.getName() %>','#','','');
    	<%
    		}
		%>
		document.write(d);
		d.openAll();
	</script>
    </td> 
  </tr>
</table>

<p align="center">
      <input class="button" type="button" value='<%=resource.srcStr("Main.submit")%>' onclick="checkForm();">&nbsp;
      <input class="button" type="button" value='<%=resource.srcStr("Main.cancel")%>' onclick="javascript:window.history.back();">
    </p>
<script type="text/javascript">

function  checkRole(obj)
{
    strRef = "#/\%&*@^$*;:~【】";
	strTmp = obj.value;
	str = "";
	for(i=0;i<strTmp.length;i++){
		if(strTmp.charAt(i) != ' '){
			str += strTmp.charAt(i);
			
		}
	}
	for (i=0;i<str.length;i++) {
		tempChar= str.substring(i,i+1);
		if (strRef.indexOf(tempChar,0)!=-1) {
			return false; 
		}  
	}
	return true;
}

function checkForm(){
  if(isNull($F('roleName'))){
    alert(document.getElementById('System.input_role_name').value);
    return false;
  }
   if(!isNull($("roleName").value)){
		  var parten = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/ ;
		  if(!parten.exec($("roleName").value)) {
		       alert(document.getElementById('system.rolemeg1').value);
		       form1.roleName.select();
		       return false;
		  }  
   }
  if(getLength(form1.roleName.value)>40){
    alert(document.getElementById('System.role_name_longer').value);
    form1.roleName.select();
    return false;
  }
  if(isNull($F('roleCatalog'))){
    alert(document.getElementById('System.select_role_type').value);
    return false;
  }
  if(getLength(form1.note.value)>200){
    alert(document.getElementById('System.remark_longer').value);
    form1.note.select();
    return false;
  }
  <%-- if(!isNull(form1.note.value)){
		 var parten = /^[a-zA-Z0-9\u4e00-\u9fa5][a-zA-Z0-9_\-\.\(\)\u4e00-\u9fa5]{0,199}$/;
		 if(!parten.exec(form1.note.value)) {
			   alert('角色备注必须由汉字、字母、数字、下划线、横线、点号，英文左刮号和英文右刮号组成\n，开头只能是汉字、数字或者字母');
			   form1.note.select();
		  	   return false;
		 } 

  } --%>
 
  roleService.checkRole($("roleNo").value,$("roleName").value,
	function checkRoleName(data){	
		if (!data){
			alert(document.getElementById('System.exists_role').value);
			$("roleName").select();
   			return false;
		}
	}
	)
	
	var flag = 0;
	for(var i=0;i < form1.menu.length; i++) {
	    if(form1.menu[i].checked) {
	        flag = 1;
	        break;
	    }
	}
	if(flag ==0)  {
	    alert(document.getElementById('System.menu_selectRole').value);
	   return false;
    }

    form1.submit();
  
}
//这里是选择系统管理和角色管理做的角色添加，修改，删除屏蔽操作
var f = document.getElementById("F");
f.onclick = function() {
	if(f.checked == true) {
		check1(f);
		change(f);
		chooseRoleButton(form1.orgType.value);
	} else {
		check1(f);
		change(f);
	}
};

//当机构级别为总站时，会显示角色增加，修改，删除权限按钮
//当机构级别为非总站时，会自动隐藏角色的增加，修改，删除按钮
function chooseRoleButton(obj) {
	var button7 = document.getElementById("7");
	var button8 = document.getElementById("8");
	var button9 = document.getElementById("9");
	var dv1 = button7.parentElement;  
	var dv2 = button8.parentElement;  
	var dv3 = button9.parentElement;  
	if(obj != 1) {
		dv1.style.display = 'none';
		dv2.style.display = 'none';
		dv3.style.display = 'none';
		button7.checked = false;
		button8.checked = false;
		button9.checked = false;
	} else {
		dv1.style.display = 'inline';
		dv2.style.display = 'inline';
		dv3.style.display = 'inline';
	}
}
</script>
</form>

</body>
</html>
