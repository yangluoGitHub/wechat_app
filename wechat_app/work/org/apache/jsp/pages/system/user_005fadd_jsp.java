package org.apache.jsp.pages.system;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.weili.wechat.common.Resource;
import com.weili.wechat.common.GetResource;
import java.util.*;
import com.weili.wechat.vo.*;

public final class user_005fadd_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/pages/system/../../scripts/common.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=gbk");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"../error.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
 Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gbk\" />\r\n");
      out.write("<title></title>\r\n");
      out.write("<script type='text/javascript' src='../dwr/engine.js'></script>\r\n");
      out.write("<script type='text/javascript' src='../dwr/interface/userService.js'></script>\r\n");
      out.write("<script type='text/javascript' src='../dwr/interface/roleService.js'></script>\r\n");
      out.write("\r\n");
      out.write("<script language=\"JavaScript\" src=\"../scripts/jquery.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script language=\"JavaScript\" src=\"../scripts/json2.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"../scripts/jquery-ui.min.js\"></script>\r\n");
      out.write("<link href=\"../styles/jquery-ui.css\" rel=\"stylesheet\"type=\"text/css\"/>\r\n");
      out.write("\r\n");
      out.write("<script language=\"JavaScript\" src=\"../scripts/common.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script language=\"JavaScript\" src=\"../scripts/prototype.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<link href=\"../styles/common.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"../pages/tree/js/tree2.js\"></script>\r\n");
      out.write("<link href=\"../pages/tree/css/tree.css\" rel=\"StyleSheet\" type=\"text/css\">\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>&nbsp; \r\n");
      out.write(" \r\n");
      out.write("<!--javascript 国际化标识符-->\r\n");
      out.write("<div id=\"javascriptI18n\">\r\n");
      out.write("<input type=\"hidden\" id=\"script.common.dateFormat\" value=\"");
      out.print(resource.srcStr("script.common.dateFormat") );
      out.write("\" />\r\n");
      out.write("<input type=\"hidden\" id=\"script.common.yearLimit\" value=\"");
      out.print(resource.srcStr("script.common.yearLimit") );
      out.write("\" />\r\n");
      out.write("<input type=\"hidden\" id=\"script.common.monthLimit\" value=\"");
      out.print(resource.srcStr("script.common.monthLimit") );
      out.write("\" />\r\n");
      out.write("<input type=\"hidden\" id=\"script.common.DateLimit\" value=\"");
      out.print(resource.srcStr("script.common.DateLimit") );
      out.write("\" />\r\n");
      out.write("<input type=\"hidden\" id=\"script.common.notSelect\" value=\"");
      out.print(resource.srcStr("script.common.notSelect") );
      out.write("\" />\r\n");
      out.write("<input type=\"hidden\" id=\"script.common.invalidCharacter\" value=\"");
      out.print(resource.srcStr("script.common.invalidCharacter") );
      out.write("\" />\r\n");
      out.write("<input type=\"hidden\" id=\"script.common.ipAddressError\" value=\"");
      out.print(resource.srcStr("script.common.ipAddressError") );
      out.write("\" />\r\n");
      out.write("<input type=\"hidden\" id=\"script.common.ipError\" value=\"");
      out.print(resource.srcStr("script.common.ipError") );
      out.write("\" />\r\n");
      out.write("<input type=\"hidden\" id=\"script.common.ipLimit\" value=\"");
      out.print(resource.srcStr("script.common.ipLimit") );
      out.write("\" />\r\n");
      out.write("<input type=\"hidden\" id=\"script.common.inputLengthLimit\" value=\"");
      out.print(resource.srcStr("script.common.inputLengthLimit") );
      out.write("\" />\r\n");
      out.write("<input type=\"hidden\" id=\"System.fault_email_format\" value=\"");
      out.print(resource.srcStr("System.fault_email_format") );
      out.write("\" />\r\n");
      out.write("<input type=\"hidden\" id=\"script.common.no\" value=\"");
      out.print(resource.srcStr("script.common.no") );
      out.write("\" />\r\n");
      out.write("\r\n");
      out.write("<input type=\"hidden\" id=\"System.input_tel\" value='");
      out.print(resource.srcStr("System.input_tel"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.input_no\" value='");
      out.print(resource.srcStr("System.input_no"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.no_length\" value='");
      out.print(resource.srcStr("System.no_length"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.user_no_digital\" value='");
      out.print(resource.srcStr("System.user_no_digital"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.select_own_org\" value='");
      out.print(resource.srcStr("System.select_own_org"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.input_name\" value='");
      out.print(resource.srcStr("System.input_name"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.username_length\" value='");
      out.print(resource.srcStr("System.username_length"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.select_role\" value='");
      out.print(resource.srcStr("System.select_role"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.select_second_role\" value='");
      out.print(resource.srcStr("System.select_second_role"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.select_third_role\" value='");
      out.print(resource.srcStr("System.select_third_role"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.tel_longer_than15\" value='");
      out.print(resource.srcStr("System.tel_longer_than15"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.tel_shorter_than\" value='");
      out.print(resource.srcStr("System.tel_shorter_than"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.phone_length\" value='");
      out.print(resource.srcStr("System.phone_length"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.input_mobile\" value='");
      out.print(resource.srcStr("System.input_mobile"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.error_email_format\" value='");
      out.print(resource.srcStr("System.error_email_format"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.email_longer_than\" value='");
      out.print(resource.srcStr("System.email_longer_than"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.reset_role\" value='");
      out.print(resource.srcStr("System.reset_role"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.input_error_tel\" value='");
      out.print(resource.srcStr("System.input_error_tel"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.input_error_phone\" value='");
      out.print(resource.srcStr("System.input_error_phone"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.exists_user_no\" value='");
      out.print(resource.srcStr("System.exists_user_no"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.select_own_maintainer\" value='");
      out.print(resource.srcStr("System.select_own_maintainer"));
      out.write("'/>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script language=\"JavaScript\">\r\n");
      out.write("var j$ = jQuery.noConflict();\r\n");
      out.write("\r\n");
      out.write("j$().ready(function(){\r\n");
      out.write("\t j$(\"input[name='checkPA']\").change(function(){\r\n");
      out.write("\t \tvar val = j$(this).val().split(\"|\");\r\n");
      out.write("\t \tvar value = val[0];\r\n");
      out.write("\t \tvar text = val[1];\r\n");
      out.write("\t \tif(j$(this).attr(\"checked\") == \"checked\"){\r\n");
      out.write("\t \t\taddSelect(value,text);\r\n");
      out.write("\t \t}else{\r\n");
      out.write("\t \t\tdelSelect(value);\r\n");
      out.write("\t \t}\r\n");
      out.write("\t });\r\n");
      out.write("});\r\n");
      out.write("function addSelect(value,text){\r\n");
      out.write("\tj$(\"#wechatId option\").each(function(){\r\n");
      out.write("\t\tif(j$(this).val() == value){\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\tj$(\"#wechatId\").append(\r\n");
      out.write("\t\tj$(\"<option></option>\").val(value).html(text)\r\n");
      out.write("\t);\r\n");
      out.write("}\r\n");
      out.write("function delSelect(value){\r\n");
      out.write("\t j$(\"#wechatId option\").each(function(){\r\n");
      out.write("\t \tif(j$(this).val() == value){\r\n");
      out.write("\t \t\tj$(this).remove();\r\n");
      out.write("\t \t}\r\n");
      out.write("\t });\r\n");
      out.write("}\r\n");
      out.write("function checkForm(){\r\n");
      out.write("  //用户名检查\r\n");
      out.write("  if(isNull(j$('#no').val())){\r\n");
      out.write("    alert(document.getElementById('System.input_no').value);\r\n");
      out.write("    $(\"no\").select();\r\n");
      out.write("    form1.no.focus();\r\n");
      out.write("    return false;\r\n");
      out.write("  }\r\n");
      out.write("  if((getLength(form1.no.value)<4||getLength(form1.no.value)>20)&&($F('roleNo_Query')!=100)&&($F('roleNo_Query')!=200)){\r\n");
      out.write("    \t\talert(document.getElementById('System.no_length').value);\r\n");
      out.write("    \t\tform1.no.select();\r\n");
      out.write("    \t\tform1.no.focus();\r\n");
      out.write("   \t \t\treturn false;\r\n");
      out.write("  \t\t }\r\n");
      out.write("  if(!isNull(form1.no.value)){\r\n");
      out.write("\t   var parten;\r\n");
      out.write("\t   parten = /^[a-zA-Z0-9][a-zA-Z0-9_\\-\\.]{0,19}$/;\r\n");
      out.write("\t   if(!parten.exec($F('no'))) {\r\n");
      out.write("\t\t\t       alert('用户名必须由字母、数字、下划线、横线、点号组成\\n，开头只能是字母或者数字');\r\n");
      out.write("\t\t\t  \t   form1.no.select();\r\n");
      out.write("\t\t  \t  \t   return false;\r\n");
      out.write("\t\t}  \r\n");
      out.write("  }\r\n");
      out.write("  //姓名检查\r\n");
      out.write("  if(isNull(j$('#name').val())){\r\n");
      out.write("    alert(document.getElementById('System.input_name').value);\r\n");
      out.write("    $(\"name\").select();\r\n");
      out.write("    return false;\r\n");
      out.write("  }\r\n");
      out.write("  if(getLength(form1.name.value)>20 || getLength(form1.name.value)<1){\r\n");
      out.write("    \t\talert(document.getElementById('System.username_length').value);\r\n");
      out.write("    \t\tform1.name.select();\r\n");
      out.write("   \t \t\treturn false;\r\n");
      out.write("  \t\t }\r\n");
      out.write("  if(!isNull(j$('#name').val())){\r\n");
      out.write("         var parten;\r\n");
      out.write("\t\t parten = /^[a-zA-Z0-9\\u4e00-\\u9fa5][a-zA-Z0-9_\\-\\.\\u4e00-\\u9fa5]{0,19}$/;\r\n");
      out.write("\t\t if(!parten.exec($F('name'))) {\r\n");
      out.write("\t\t\t       alert('姓名必须由汉字、字母、数字、下划线、横线、点号组成\\n，开头只能是汉字、数字或者字母');\r\n");
      out.write("\t\t\t  \t   form1.name.select();\r\n");
      out.write("\t\t  \t  \t   return false;\r\n");
      out.write("\t\t}  \r\n");
      out.write("  }  \t\t \r\n");
      out.write("  //角色检查\r\n");
      out.write("  if(isNull(j$('#roleNo').val())){\r\n");
      out.write("    alert(document.getElementById('System.select_role').value);\r\n");
      out.write("    $(\"roleNo\").focus();\r\n");
      out.write("    return false;\r\n");
      out.write("  }\r\n");
      out.write("  //手机号码检查\r\n");
      out.write("  if(!isNull(form1.mobile.value)){\r\n");
      out.write("  \tif(!mobileCheck(form1.mobile)){ \r\n");
      out.write("\t\talert(document.getElementById('System.input_error_phone').value);\r\n");
      out.write("\t\tform1.mobile.select();\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("  \tif(getLength(form1.mobile.value)<11){\r\n");
      out.write("    \talert(document.getElementById('System.phone_length').value);\r\n");
      out.write("    \tform1.mobile.select();\r\n");
      out.write("    \treturn false;\r\n");
      out.write("  \t}\r\n");
      out.write("  }\r\n");
      out.write("  //email检查\r\n");
      out.write("  if(!isNull(form1.email.value)){\r\n");
      out.write("  \tvar checkmail = document.getElementById(\"email\").value;\r\n");
      out.write("\treEmail=/\\w+\\@\\w+\\.\\w+/gi ;//正则表式如果符合要求就通过\r\n");
      out.write("   \tvar isOk=reEmail.test(checkmail);\r\n");
      out.write("   \tif(!isOk){\r\n");
      out.write("       alert(document.getElementById('System.error_email_format').value);\r\n");
      out.write("\t   return false;\r\n");
      out.write("\t}\r\n");
      out.write("\tif(getLength(form1.email.value)>40){\r\n");
      out.write("        alert(document.getElementById('System.email_longer_than').value);\r\n");
      out.write("        form1.email.select();\r\n");
      out.write("        return false;\r\n");
      out.write("    }\r\n");
      out.write("  }\r\n");
      out.write("  //办公电话检查\r\n");
      out.write("  if(!isNull(form1.telephone.value))\r\n");
      out.write("  {\r\n");
      out.write("\t  if(!phoneCheck(form1.telephone))\r\n");
      out.write("\t  { \r\n");
      out.write("\t  \talert(document.getElementById('System.input_error_tel').value);\r\n");
      out.write("\t  \tform1.telephone.select();\r\n");
      out.write("\t  \treturn false;\r\n");
      out.write("\t  }\r\n");
      out.write("  \t  if(getLength(form1.telephone.value)>15)\r\n");
      out.write("  \t  {\r\n");
      out.write("      \talert(document.getElementById('System.tel_longer_than15').value);\r\n");
      out.write("    \tform1.telephone.select();\r\n");
      out.write("    \treturn false;\r\n");
      out.write("\t  }\r\n");
      out.write("  \t  if(getLength(form1.telephone.value)<7)\r\n");
      out.write("  \t  {\r\n");
      out.write("\t\talert(document.getElementById('System.tel_shorter_than').value);\r\n");
      out.write("    \tform1.telephone.select();\r\n");
      out.write("   \t \treturn false;\r\n");
      out.write("\t  }\r\n");
      out.write("  }\r\n");
      out.write("  else if(j$('roleNo').val()==100 || j$('roleNo').val()==200)\r\n");
      out.write("  {\r\n");
      out.write("\talert(document.getElementById('System.input_tel').value);\r\n");
      out.write("    $(\"telephone\").select();\r\n");
      out.write("    return false;\r\n");
      out.write("  }\r\n");
      out.write("  \r\n");
      out.write("  if($F('roleNo')!=100 && $F('roleNo')!=200){\r\n");
      out.write("\t  \t//判断用户是否存在\r\n");
      out.write("\t  \tDWREngine.setAsync(false);\r\n");
      out.write("\t \tuserService.checkIsExist($(\"no\").value,\r\n");
      out.write("\t\t\tfunction checkIsExist(data){\r\n");
      out.write("\t\t\t\tif (data){\r\n");
      out.write("\t\t\t\t\talert(document.getElementById('System.exists_user_no').value);\r\n");
      out.write("\t\t\t\t\tform1.no.select();\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\tform1.submit();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t \tDWREngine.setAsync(true);\r\n");
      out.write("\t  \r\n");
      out.write("  }else{\r\n");
      out.write("\t  form1.submit();\r\n");
      out.write("  }\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function myrefresh(){\r\n");
      out.write("     if($(\"tmpfirst\").value==1){\r\n");
      out.write("\t  \t $(\"tmpfirst\").value=0;\r\n");
      out.write("\t }\r\n");
      out.write("} \r\n");
      out.write(" \r\n");
      out.write("var roleNum=0;\r\n");
      out.write("function addRole(){\r\n");
      out.write("\tj$(\"#div\" + (roleNum+1)).show();\r\n");
      out.write("\troleNum++;\r\n");
      out.write("}\r\n");
      out.write("function resetRole(){\r\n");
      out.write("\tj$(\"#div\" + roleNum).hide();\r\n");
      out.write("\tj$(\"#roleNo\" + roleNum).val('');\r\n");
      out.write("\troleNum--;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function select(flag){\r\n");
      out.write("\tj$(\":checkbox\").each(function(){\r\n");
      out.write("\t\tj$(this).attr(\"checked\", flag);\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<br>\r\n");
      out.write("\r\n");

	String roleNo_Query=request.getParameter("roleNo_Query");
	request.setAttribute("roleNo_Query",roleNo_Query);
	String title = request.getParameter("title");

      out.write("\r\n");
      out.write("<form name=\"form1\" method=\"post\" action=\"user.do?action=add&opNoCondition=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${opNoCondition }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("&roleNoCondition=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roleNoCondition }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"tmpfirst\" value=\"0\">\r\n");
      out.write("<input type=\"hidden\" name=\"roleNo_Query\" id=\"roleNo_Query\" value='");
      out.print( roleNo_Query);
      out.write("'>\r\n");
      out.write("<input type=\"hidden\" name=\"title\" value='");
      out.print( title);
      out.write("'>\r\n");
      out.write("<table align=\"center\" width=\"650\" border=\"0\" cellspacing=\"1\" cellpadding=\"4\" class=\"table1\">\r\n");
      out.write("  <tr class=\"tr1\">\r\n");
      out.write("    <td nowrap colspan=\"2\" align=\"center\">");
      out.print(resource.srcStr("System.add"));
      out.print(title );
      out.write("</td>\r\n");
      out.write("  </tr> \r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap width=\"100\">用户名：</td>\r\n");
      out.write("    <td nowrap><input type=\"text\" name=\"no\" id=\"no\" class=\"pane\" style=\"ime-mode:disabled\" onkeydown=\"if(event.keyCode==13)event.keyCode=9\" maxlength=\"20\">&nbsp;*</td> \r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>");
      out.print(resource.srcStr("System.name") );
      out.write("：</td>\r\n");
      out.write("    <td nowrap><input type=\"text\" name=\"name\" id=\"name\" class=\"pane\"  maxLength=\"20\">&nbsp;*</td> \r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>");
      out.print(resource.srcStr("System.role") );
      out.write("：</td>\r\n");
      out.write("    <td nowrap>\r\n");
      out.write("\t  <div>\r\n");
      out.write("\t      <select name=\"roleNo\" id=\"roleNo\" size=\"1\">\r\n");
      out.write("\t      \t<option value=\"\"></option>\r\n");
      out.write("\t\t      ");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t      </select>&nbsp;*\r\n");
      out.write("      </div>\r\n");
      out.write("    </td> \r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>");
      out.print(resource.srcStr("System.fixed_telephone") );
      out.write("：</td>\r\n");
      out.write("    <td nowrap><input type=\"text\" name=\"telephone\" class=\"pane\" maxLength=15 style=\"ime-mode:disabled\" onkeydown=\"if(event.keyCode==13)event.keyCode=9\" onKeyPress=\"if (event.keyCode!=45 && (event.keyCode<48 || event.keyCode>57)) event.returnValue=false\">&nbsp;");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("</td> \r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>");
      out.print(resource.srcStr("System.mobile") );
      out.write("：</td>\r\n");
      out.write("    <td nowrap><input type=\"text\" name=\"mobile\" class=\"pane\" maxlength=\"20\" style=\"ime-mode:disabled\" onkeydown=\"if(event.keyCode==13)event.keyCode=9\" onKeyPress=\"if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false\"></td> \r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>");
      out.print(resource.srcStr("System.email") );
      out.write("：</td>\r\n");
      out.write("    <td nowrap><input type=\"text\" id=\"email\" name=\"email\" class=\"pane\" maxLength=\"40\" ></td> \r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>公众号权限：</td>\r\n");
      out.write("     <td nowrap>\r\n");
      out.write("     \t");
      if (_jspx_meth_c_005fif_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("     \t");
      if (_jspx_meth_c_005fforEach_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("     </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  ");
      if (_jspx_meth_c_005fif_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("<p align=\"center\">\r\n");
      out.write("\t<input class=\"button\" type=\"button\" id=\"submitBtn\" value='");
      out.print(resource.srcStr("Main.submit"));
      out.write("' onclick=\"checkForm()\">&nbsp;\r\n");
      out.write("\t<input class=\"button\" type=\"button\" value='");
      out.print(resource.srcStr("Main.cancel"));
      out.write("' onclick=\"javascript:window.location='user.do?action=qry&opNo=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${opNoCondition }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("&roleNo_Query=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roleNoCondition }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\">\r\n");
      out.write("</p>\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fforEach_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /pages/system/user_add.jsp(272,8) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roleList}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /pages/system/user_add.jsp(272,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("role");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t        <option value=\"");
          if (_jspx_meth_c_005fout_005f0(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write('"');
          out.write('>');
          if (_jspx_meth_c_005fout_005f1(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("</option>\r\n");
          out.write("\t\t      ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /pages/system/user_add.jsp(273,25) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${role[0]}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f1 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /pages/system/user_add.jsp(273,54) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${role[1]}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f1 = _jspx_th_c_005fout_005f1.doStartTag();
    if (_jspx_th_c_005fout_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /pages/system/user_add.jsp(281,260) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roleNo_Query ==100 || roleNo_Query ==200}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest_005fnobody.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest_005fnobody.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent(null);
    // /pages/system/user_add.jsp(294,6) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty paList}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("     \t\t<font color=\"red\">尚无公众号可选</font>\r\n");
        out.write("     \t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f1.setParent(null);
    // /pages/system/user_add.jsp(297,6) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${paList}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /pages/system/user_add.jsp(297,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setVar("pa");
    int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
      if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("     \t\t<div style=\"position: relative;float: left;margin-bottom: 2px;margin-left: 10px\">\r\n");
          out.write("     \t\t<p >\r\n");
          out.write("     \t\t<img id=\"logo\" src=\"../images/menu/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pa[2]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\">\r\n");
          out.write("     \t\t<input style=\"position: relative;float: left;top: -12px;left: 30px;\" type=\"checkbox\" name=\"checkPA\" value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pa[0]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write('|');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pa[1]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\"/></p>\r\n");
          out.write("     \t\t</div>\r\n");
          out.write("     \t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f1.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f1);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f2.setParent(null);
    // /pages/system/user_add.jsp(306,2) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty paList}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
    if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("  <tr class=\"tr3\">\r\n");
        out.write("    <td nowrap>默认公众号：</td>\r\n");
        out.write("    <td nowrap>\r\n");
        out.write("    \t<select id=\"wechatId\" name=\"wechatId\" style=\"width: 180px\">\r\n");
        out.write("    \t\t<option value=\"\">---请选择---</option>\r\n");
        out.write("    \t</select>&nbsp;\r\n");
        out.write("    </td> \r\n");
        out.write("  </tr>\r\n");
        out.write("  ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
    return false;
  }
}
