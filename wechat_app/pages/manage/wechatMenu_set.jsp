<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<link type="text/css"  rel="stylesheet" href="../styles/apiUser.css"/>
<link type="text/css"  rel="stylesheet" href="../styles/account.css"/>

<!-- �����Ի���ʽ�� -->
<link type="text/css"  rel="stylesheet" href="../styles/css.css"/>

<style type="text/css">


.Menuboxtwo{padding:10px}
.Menuboxtwo{padding:5px}
.Menuboxtwo{border-color:#368ee0}
.Menuboxtwo{border:2px solid #ddd;border-top:0}
.Menuboxtwo{*zoom:1;padding:20px;background:#fff}
.alert{padding:8px 35px 8px 14px;margin-bottom:20px;text-shadow:0 1px 0 rgba(255,255,255,0.5);background-color:#fcf8e3;border:1px solid #fbeed5;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px}
</style>
<title></title>

<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/interface/weMenuService.js'></script>

<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jquery.json.js"></script>
<script type="text/javascript" src="../scripts/jquery-ui.js"></script>
<script type="text/javascript" src="../scripts/weixin.bind.js"></script>
<script type="text/javascript" src="../scripts/dialog.js"></script>
<script type="text/javascript" src="../scripts/jquery.blockUI.js"></script>

<script language="JavaScript" src="../scripts/json2.js" type="text/javascript"></script>
<script language="JavaScript" src="../scripts/jquery.autocomplete.min.js" type="text/javascript"></script>

<link href="../styles/jquery.autocomplete.css" rel="stylesheet" type="text/css">

</head>
<body style="overflow-x: hidden;">

<input type="hidden" id="accIdField" name="accIdField" value="${wechatId}"/>

<div style="height:100%;width:100%; float:left; margin-top:0px;">
			        <div style="width:100%; margin:0px 0 5px 0;">
			            <div style="height:29px;">
					         <div class="left"><img src="../images/wechat_menu/title_left.gif"  style="width:1%;"/></div>
						     <div class="title_mid left" style="width:98%;">�Զ���˵�</div>
						     <div class="right"><img src="../images/wechat_menu/title_right.gif" style="width:1%;"/></div>
					    </div>
					    
					      <div class="right_con" style="width:100%;padding:0;">
						   <div class="Menuboxtwo" >
							<div class="alert" >
							<ul>
								<p>һ���˵����ֻ�ܿ���3���������Ӳ˵���࿪��5����</p>
                                <p>�ȱ����ٷ�����Ĭ�Ϸ������һ�α���ģ�</p>
                                <!-- <p>����Ϊ�Զ���˵���д���ӵ�ַʱ����д��"http://"��ͷ���������Ա�֤�û��ֻ�����ļ����Ը��ã�</p> -->
                                <p>�����Զ���˵�������΢�ſͻ��˻��棬��Ҫ24Сʱ΢�ſͻ��˲Ż�չ�ֳ������������ʱ���Գ���ȡ����ע�����˺ź��ٴι�ע������Կ����������Ч����</p>
							</ul>
							</div>
							</div>
								
						
						
						<div id="jsweb_c4" class="Contentboxdiv1" style="padding:0;">
							<div class="CMOP-custom CM-dsp" >
					        	<div style="FLOAT: left; margin-left:100px;">
					        	<div ><p><a id="addRootMenuBtn" href="#c4" class="wx-button06"></a></p><p class="CM-cl03">���˵������5���֣��Ӳ˵������12���֡�</p></div>
					            <div >
					            
					            	<div class="CMOP-customleft f_l">
					                	<div class="CMOP-ct01">
					                        <p style="width:112px;">�˵���</p>
					                        <p style="width:161px;">ҵ�������ƻ���URL</p>
					                        <p style="width:160px;background:none;">����</p>
					                    </div>
					                    
					                    <div class="CMOP-ct02 ov_fl">
											<table width="431" border="0" cellspacing="0" cellpadding="0">
											  <tbody id="menuContainer">
										  		
										  	  <c:forEach items="${retList}" var="menuItemVOlist"  varStatus="status">
										  		<c:forEach items="${menuItemVOlist}" var="item" varStatus="varStatus">
										  		    <c:if test="${item.menuLevel == 1}">
										  		    	<tr name="rootMenu" state="display">
												        <td width="111" height="30" align="center" valign="middle" bgcolor="#f8f8f8" class="CMOP-cttd01">
												        	<div><span title="${item.name }"  name="menuName"><c:out value="${item.name}"></c:out></span><input name="menuName_i" value="" style="display:none;width:100px;"/></div>
												        </td>
												        <td width="161" height="30" align="center" valign="middle" bgcolor="#f8f8f8" class="CMOP-cttd01">
												        	<div><span title="${item.wechatFuncName}"  name="menuKey"><c:out value="${item.wechatFuncName}${item.wechatUrl}"></c:out></span><input id="${item.id}" name="menuKey_i" value="" onkeyup="completeCon(this)" onblur ="blurFunc(this)" style="display:none;width:150px;"/></div>
												        </td>
												        <td width="160" height="30" align="right" valign="middle" bgcolor="#f8f8f8" class="CMOP-cttd03">
												        	<a style="margin-right:15px;" href="#c4" class="CM-cta03"></a> <a href="#c4" class="CM-cta02"></a> <a href="#c4" class="CM-cta01"></a>��
												        </td>
												      </tr>
										  		    </c:if>
										  		    <c:if test="${item.menuLevel == 2}">
										  		    	<tr name="menuItem" state="display">
												        	<td width="111" height="30" align="center" valign="middle" class="CMOP-cttd01 CM-cl03">
												        		<div><span title="${item.name }" name="menuName"><c:out value="${item.name}"></c:out></span><input name="menuName_i" value="" style="display:none;width:100px;border:1px solid #f8f8f8;"/></div>
												        	</td>
												        	<td width="161" height="30" align="center" valign="middle" class="CMOP-cttd01 CM-cl03">
												        		<div><span title="${item.wechatFuncName}" name="menuKey"><c:out value="${item.wechatFuncName}${item.wechatUrl}"></c:out></span><input id="${item.id}" name="menuKey_i" value="" onkeyup="completeCon(this)" onblur ="blurFunc(this)" style="display:none;width:150px;border:1px solid #f8f8f8;"/></div>
												        	</td>
												        	<td width="160" height="30" align="right" valign="middle" class="CMOP-cttd03">
												        		<a style="margin-right:15px;" href="#c4" class="CM-cta05"></a> <a href="#c4" class="CM-cta04"></a>��
												        	</td>
												      	</tr>
										  		    </c:if>
										  		    
										  		</c:forEach>
										  		
										  	</c:forEach>
										  		<!-- 
													  <tr name="rootMenu" state="display">
												        <td width="111" height="30" align="center" valign="middle" bgcolor="#f8f8f8" class="CMOP-cttd01">
												        	<div><span title="�ط���"  name="menuName">�ط���</span><input name="menuName_i" value="" style="display:none;width:100px;"/></div>
												        </td>
												        <td width="161" height="30" align="center" valign="middle" bgcolor="#f8f8f8" class="CMOP-cttd01">
												        	<div><span title="111"  name="menuKey">111</span><input name="menuKey_i" value="" style="display:none;width:150px;"/></div>
												        </td>
												        <td width="124" height="30" align="right" valign="middle" bgcolor="#f8f8f8" class="CMOP-cttd03">
												        	<a style="margin-right:15px;" href="#c4" class="CM-cta03"></a> <a href="#c4" class="CM-cta02"></a> <a href="#c4" class="CM-cta01"></a>��
												        </td>
												      </tr>
												      
											     
													  <tr name="rootMenu" state="display">
												        <td width="111" height="30" align="center" valign="middle" bgcolor="#f8f8f8" class="CMOP-cttd01">
												        	<div><span title="�����"  name="menuName">�����</span><input name="menuName_i" value="" style="display:none;width:100px;"/></div>
												        </td>
												        <td width="161" height="30" align="center" valign="middle" bgcolor="#f8f8f8" class="CMOP-cttd01">
												        	<div><span title="�ḻ��"  name="menuKey">�ḻ��</span><input name="menuKey_i" value="" style="display:none;width:150px;"/></div>
												        </td>
												        <td width="124" height="30" align="right" valign="middle" bgcolor="#f8f8f8" class="CMOP-cttd03">
												        	<a style="margin-right:15px;" href="#c4" class="CM-cta03"></a> <a href="#c4" class="CM-cta02"></a> <a href="#c4" class="CM-cta01"></a>��
												        </td>
												      </tr>
												      
											     
													  <tr name="rootMenu" state="display">
												        <td width="111" height="30" align="center" valign="middle" bgcolor="#f8f8f8" class="CMOP-cttd01">
												        	<div><span title="12"  name="menuName">12</span><input name="menuName_i" value="" style="display:none;width:100px;"/></div>
												        </td>
												        <td width="161" height="30" align="center" valign="middle" bgcolor="#f8f8f8" class="CMOP-cttd01">
												        	<div><span title="3232"  name="menuKey">3232</span><input name="menuKey_i" value="" style="display:none;width:150px;"/></div>
												        </td>
												        <td width="124" height="30" align="right" valign="middle" bgcolor="#f8f8f8" class="CMOP-cttd03">
												        	<a style="margin-right:15px;" href="#c4" class="CM-cta03"></a> <a href="#c4" class="CM-cta02"></a> <a href="#c4" class="CM-cta01"></a>��
												        </td>
												      </tr>
												      
											      -->
											     
											  </tbody>
											</table>
					
					                  </div>
					                  
					                   <div class="CMOP-ct04" style="margin-top:10px">
										<a href="#c4" id="saveMenuBtn" class="wx-button04 f_l">������</a>
					                   	<a href="#c4" id="deployMenuBtn" style="margin-left:100px" class="wx-button04 f_1">������</a>
					                   	</div>
					                   <p>&nbsp;</p> <p>&nbsp;</p> <p>&nbsp;</p> <p>&nbsp;</p> <p>&nbsp;</p> <p>&nbsp;</p> <p>&nbsp;</p> <p>&nbsp;</p> <p>&nbsp;</p>
					              </div>
					              </div>
					              </div>
					                <div class="CMOP-customright f_l ov_fl" style="margin-left:100px">
										<div class="weixin-menucn f_l" >
											<div id="previewCont" style="display:none;" class="weixin-ul-one1 ov_fl">
												<div class="weixin-ul-one2">
												<div class="weixin-ul-one3">
												<ul id="previewSubItems">
												</ul>
												</div>
												</div>
											</div>
										<ul id="previewRootItems" class="weixin-ul-two">
										</ul>
										</div>
					                </div>
					            
					        </div>
						</div>
					</div>
				</div>
	 </div>
	 <div style="display:none" id="message-dialog" title="��ʾ">
  <p><span class="" style="float: left; margin: 0 7px 20px 0;"></span></p>
</div>

		
<script>


</script>
</body>
</html>
		