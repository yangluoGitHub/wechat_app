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
<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/prototype.js" type="text/javascript"></script>
<script type="text/javascript" src="../resource/js/dtree.js"></script>
<script type="text/javascript" src="../resource/js/op.js"></script>
<link href="../resource/css/dtree.css" rel="StyleSheet" type="text/css">
</head>

<body>

<!--javascript 国际化标识符-->
<div id="javascriptI18n">
<%@ include file="../../scripts/common.jsp" %>
<input type="hidden" id="System.input_role_name" value='<%=resource.srcStr("System.input_role_name")%>'/>
<input type="hidden" id="System.menu_sys_fun" value='<%=resource.srcStr("System.menu_sys_fun")%>'/>
</div>



<script language="JavaScript">
tree = new dTree('tree');
tree.add('0','-1','','#','','');



function checkForm(){
  if(isNull($F('roleName'))){
    alert(document.getElementById('System.input_role_name').value);
    return false;
  }
  return true;
}
</script>

<br>

<form name="form1" method="post">

<table align="center" width="850" border="0" cellspacing="1" cellpadding="3" class="table1">
  <tr class="tr1">
    <td nowrap colspan="2" align="center"><%=resource.srcStr("System.detail_role_info")%></td>
  </tr>
  <tr class="tr3">
    <td nowrap width="100"><%=resource.srcStr("System.role_name") %>：</td>
    <td nowrap><input readonly style="width:153;" type="text" name="roleName" class="paneGray" value="<c:out value="${role.name}"/>">&nbsp;</td> 
  </tr>  
  <tr class="tr3">
    <td nowrap width="100">角色类型：</td>
    <td nowrap>
    <c:if test="${role.catalog == 1}">
    <input type="text" class="paneGray" name="catalog" style="width:153;"  readonly  value='超级操作员'></c:if>
    <c:if test="${role.catalog == 2}">
    <input type="text" class="paneGray" name="catalog" style="width:153;"  readonly  value='预设操作员'></c:if>
    <c:if test="${role.catalog == 3}">
    <input type="text" class="paneGray" name="catalog" style="width:153;"  readonly  value='其他操作员'></c:if>
    </td> 
  </tr>
  <tr class="tr3">
    <td nowrap><%=resource.srcStr("System.role_des") %>：</td>
    <td nowrap><textarea readonly name="note" style="width:400px;height:50px;" onblur="checktext($(note),200);"><c:out value="${role.note}"/></textarea></td> 
  </tr>
<tr class="tr3">
    <td nowrap valign="top"><br><%=resource.srcStr("System.role_function") %>：</td>
    <td nowrap><a href="#" onclick="javascript: d.openAll();"><%=resource.srcStr("System.expand")%></a> | <a href="#" onclick="javascript: d.closeAll();"><%=resource.srcStr("System.gather") %></a> 
    <br><br>
    <script type="text/javascript">
        d = new dTree('d');
		d.config.folderLinks=false;
		//id, pid, name, url, title, target, icon, iconOpen, open
		d.add('0','-1',document.getElementById("System.menu_sys_fun").value,'#',document.getElementById("System.menu_sys_fun").value,'');
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
</table>

<p align="center">
      <input class="button" type="button" value='<%=resource.srcStr("Main.back")%>' onclick="javascript:window.history.back();">
    </p>

</form>


</body>
</html>



