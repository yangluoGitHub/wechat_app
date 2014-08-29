<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page import="java.util.*" %>
<%@ page import="com.weili.wechat.vo.*" %>
<%@ page import="com.weili.wechat.common.StringUtil" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<script type="text/javascript" src="../resource/js/dtree.js"></script>
<script type="text/javascript" src="../resource/js/op.js"></script>
<link href="../resource/css/dtree.css" rel="StyleSheet" type="text/css">
</head>

<body onload='chooseRoleButton(document.getElementById("orgType").value);'>
<%@ include file="../../scripts/common.jsp" %>
<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<input type="hidden" id="System.menu_sys_fun" value='<%=resource.srcStr("System.menu_sys_fun")%>'/>
</div>


<script language="JavaScript">
tree = new dTree('tree');
tree.add('0','-1','','#','','');

function audit(){
    form.action="operateAudit.do?action=audit";
    form.submit();
}

function refuse(){
    form.action="operateAudit.do?action=refuse";
    form.submit();
}
</script>

<br>
<%
	Role role = (Role)request.getAttribute("objectvo");
	List menuList = (List)request.getAttribute("menuList");
    List buttonList = (List)request.getAttribute("buttonList");
	//List menuList = this.getRoleService().qryMenu();
	//List buttonList = this.getRoleService().qryButton();
//				List confmenuList = this.getRoleService().qryMenuByRole(Integer.valueOf(roleNo));
//				List confbuttonList = this.getRoleService().qryButtonByRole(Integer.valueOf(roleNo));

//				用列表存储被选择的菜单			
	StringBuffer confMenus = new StringBuffer();
	String f;
	for(Object o:role.getMenus()){
		Menu m = (Menu)o;
		f = StringUtil.parseString(m.getNo().toString());
		if(f.length() > 0){
			confMenus.append(f).append(",");
		}
	}			
	String menuchecked = confMenus.toString();
	List<String> menucheckedList = new ArrayList<String>(0);
	for(Object o:menuList){
		Menu m = (Menu)o;
		String t = menuchecked.indexOf(m.getNo()+",")>=0 ? "checked" : "";
		menucheckedList.add(t);
	}
				
//		用列表存储被选择的按钮
	StringBuffer confButtons = new StringBuffer();
	confButtons.append(",");//唯一性 ykliu add
	String s;
	for(Object o:role.getButtons()){
		Button b = (Button)o;
		s = StringUtil.parseString(b.getNo().toString());
		if(s.length() > 0){
			confButtons.append(s).append(",");
		}
	}			
	String buttonchecked = confButtons.toString();
	List<String> buttoncheckedList = new ArrayList<String>();
	for(Object o:buttonList){
		Button b = (Button)o;
		String c = buttonchecked.indexOf(","+b.getNo()+",")>=0 ? "checked" : "";
		buttoncheckedList.add(c);
	}
	
	request.setAttribute("menucheckedList",menucheckedList);
	request.setAttribute("buttoncheckedList",buttoncheckedList);
%>
<form name="form" method="post" action="" >
<input type="hidden" name="flag" value='<%=request.getParameter("flag") %>'>
<input type="hidden" name="userId" value='<c:out value="${logvo.userId}"/>'>
<input type="hidden" name="date" value='<c:out value="${logvo.date}"/>'>
<input type="hidden" name="title" value='<c:out value="${title}"/>'>
<%
	request.setAttribute("flag",request.getParameter("flag"));
%>
<table width="100%" style="border:0px;border-bottom:1px solid #cccccc">
	 <tr>
	  <td nowrap><b><%=resource.srcStr("System.detail_info")%>:</b></td>			    
	 </tr>
</table>
<input type="hidden" name="orgType" value='<c:out value="${objectvo.orgType}"/>' />
<table align="center" width="100%" border="0" cellspacing="1" cellpadding="3" class="table1">
  <tr class="tr1">
    <td colspan="2" align="center"><c:out value="${title}"/></td>
  </tr>  
  <tr class="tr3">
    <td nowrap width="100">角色级别：</td>
    <td nowrap>
    <c:if test="${objectvo.orgType == 1}">
    <input type="text" class="paneGray" name="roleName" style="width:153;"  readonly  value='总行'></c:if>
    <c:if test="${objectvo.orgType == 2}">
    <input type="text" class="paneGray" name="roleName" style="width:153;"  readonly  value='分行'></c:if>
    <c:if test="${objectvo.orgType == 3}">
    <input type="text" class="paneGray" name="roleName" style="width:153;"  readonly  value='支行'></c:if>
    </td> 
  </tr>
  <tr class="tr3">
    <td nowrap width="100"><%=resource.srcStr("System.role_name") %>：</td>
    <td nowrap><input type="text" class="paneGray" name="roleName" style="width:153;"  readonly  value='<c:out value="${objectvo.name}"/>'></td> 
  </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.role_des") %>：</td>
    <td nowrap><textarea readonly name="note" class="paneGray" style="width:400px;height:50px;" ><c:out value="${objectvo.note}"/></textarea></td> 
  </tr>
  <%request.setAttribute("role", resource.srcStr("System.delete_role")); %>
<c:if test="${title!=role}">
<tr class="tr3">
    <td nowrap valign="top"><br><%=resource.srcStr("System.role_function") %>：</td>
    <td nowrap><a href="#" onclick="javascript: d.openAll();"><%=resource.srcStr("System.expand")%></a> | <a href="#" onclick="javascript: d.closeAll();"><%=resource.srcStr("System.gather") %></a> 
    <br><br>
    <script type="text/javascript">
    	d = new dTree('d');
		d.config.folderLinks=false;
		//id, pid, name, url, title, target, icon, iconOpen, open		
		d.add('0','-1',document.getElementById('System.menu_sys_fun').value,'#',document.getElementById('System.menu_sys_fun').value,'');
		<%   
    		//List menuList = (List)request.getAttribute("menuList");
    		//List buttonList = (List)request.getAttribute("buttonList");
    		//List menucheckedList = (List)request.getAttribute("menucheckedList");
    		//List buttoncheckedList = (List)request.getAttribute("buttoncheckedList");
    		Menu aMenu = null;
    		Button aButton = null;
     		String buttons = "&nbsp;&nbsp;";
    		for(int i=0;i<menuList.size();i++){
    			aMenu = (Menu)menuList.get(i);
    			buttons = "&nbsp;&nbsp;";
    			for(int j=0;j< buttonList.size();j++){
    				aButton = (Button)buttonList.get(j);
    				if(aButton.getMenu().getNo().equals(aMenu.getNo())){
    					buttons = buttons + "<span style=\"border:0px;border-bottom:1px solid #cccccc;color=steelblue\"><input name=button id="+aButton.getNo()+" type=checkbox disabled "+buttoncheckedList.get(j)+"  value="+aButton.getNo()+" onclick=change(this);>"+aButton.getName()+"</span>";
    	%>
    					tree.add('<%= aButton.getNo() %>','<%= aButton.getMenu().getNo() %>','<%= aButton.getName() %>','#','','');
    	<%
    				}
    			}
    	%>
    	d.add('<%= aMenu.getNo() %>','<%= aMenu.getSuperMenu().getNo() %>','<input type="checkbox" <%=menucheckedList.get(i)%> disabled name="menu"  id="<%=aMenu.getNo() %>" onclick="change(this);" level="<%= aMenu.getLevel() %>" value="<%= aMenu.getNo() %>" ><%= aMenu.getName() %><%= buttons %>','#','','');
    	tree.add('<%= aMenu.getNo() %>','<%= aMenu.getSuperMenu().getNo() %>','<%= aMenu.getName() %>','#','','');
    	<%
    		}
		%>
		document.write(d);
		d.openAll();
	</script>
    </td> 
  </tr>
</c:if>
</table>

<table width="100%">
	<tr>
	 	<td align="center">
		 	<c:if test="${flag==2}">
				<input class="button" type="button" value='<%=resource.srcStr("System.pass")%>' onclick="javascript:audit();">
				<input class="button" type="button" value='<%=resource.srcStr("System.refuse") %>' onclick="javascript:refuse();">
			</c:if>
	  		<input class="button" type="button" value='<%=resource.srcStr("Main.back")%>' onclick="javascript:window.history.back();">
	  	</td>
	</tr>
</table>

</form>
<script language="JavaScript">
//当机构级别为总行时，会显示角色增加，修改，删除权限按钮
//当机构级别为分行或者支行时，会自动隐藏角色的增加，修改，删除按钮
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
	}
}
</script>
</body>
</html>
