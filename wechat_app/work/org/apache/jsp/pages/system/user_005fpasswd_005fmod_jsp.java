package org.apache.jsp.pages.system;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.weili.wechat.common.Resource;
import com.weili.wechat.common.GetResource;
import com.weili.wechat.common.UserSession;

public final class user_005fpasswd_005fmod_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/pages/system/../../scripts/common.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.release();
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
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gbk\" />\r\n");
      out.write("<title></title>\r\n");
      out.write("<script language=\"JavaScript\" src=\"../scripts/common.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script language=\"JavaScript\" src=\"../scripts/prototype.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<link rel=\"StyleSheet\" href=\"tree/css/tree.css\" type=\"text/css\">\r\n");
      out.write("<link href=\"../styles/common.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
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
      out.write("<input type=\"hidden\" id=\"Main.input_old_psw\" value='");
      out.print(resource.srcStr("Main.input_old_psw"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"Main.input_new_psw\" value='");
      out.print(resource.srcStr("Main.input_new_psw"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"Login.psw_less_8\" value='");
      out.print(resource.srcStr("Login.psw_less_8"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"Main.psw_longer_16\" value='");
      out.print(resource.srcStr("Main.psw_longer_16"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"Main.please_sure_new_psw\" value='");
      out.print(resource.srcStr("Main.please_sure_new_psw"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"Main.different_psw\" value='");
      out.print(resource.srcStr("Main.different_psw"));
      out.write("'/>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script language=\"JavaScript\">\r\n");
      out.write("function checkForm(){\r\n");
      out.write("  if(isNull(form1.oldpasswd.value)){\r\n");
      out.write("    alert(document.getElementById('Main.input_old_psw').value);\r\n");
      out.write("    form1.oldpasswd.select();\r\n");
      out.write("    form1.oldpasswd.focus();\r\n");
      out.write("    return false;\r\n");
      out.write("  }\r\n");
      out.write("  if(isNull(form1.passwd.value)){\r\n");
      out.write("  \tform1.passwd.select();\r\n");
      out.write("    form1.passwd.focus();\r\n");
      out.write("    alert(document.getElementById('Main.input_new_psw').value);\r\n");
      out.write("    return false;\r\n");
      out.write("  }\r\n");
      out.write("    if(($F('passwd').length<8)){\r\n");
      out.write("    alert(document.getElementById('Login.psw_less_8').value);\r\n");
      out.write("    form1.passwd.select();\r\n");
      out.write("    form1.passwd.focus();\r\n");
      out.write("    return false;\r\n");
      out.write("  }\r\n");
      out.write("  if(($F('passwd').length>16)){\r\n");
      out.write("    alert(document.getElementById('Main.psw_longer_16').value);\r\n");
      out.write("    form1.passwd.select();\r\n");
      out.write("    form1.passwd.focus();\r\n");
      out.write("    return false;\r\n");
      out.write("  }\r\n");
      out.write("   if(!checkPwd($('passwd'))){\r\n");
      out.write("    alert(\"新密码中存在非法字符_，请重新输入！\");\r\n");
      out.write("    form1.newpasswd.select();\r\n");
      out.write("    form1.newpasswd.focus();\r\n");
      out.write("    form1.passwd.value='';\r\n");
      out.write("    return false;\r\n");
      out.write("  }\r\n");
      out.write("  if(isNull(form1.newpasswd.value)){\r\n");
      out.write("  \tform1.newpasswd.select();\r\n");
      out.write("    form1.newpasswd.focus();\r\n");
      out.write("    alert(document.getElementById('Main.please_sure_new_psw').value);\r\n");
      out.write("    return false;\r\n");
      out.write("  }\r\n");
      out.write("  if($F('passwd') != $F('newpasswd')){\r\n");
      out.write("    alert(document.getElementById('Main.different_psw').value);\r\n");
      out.write("    form1.newpasswd.select();\r\n");
      out.write("    form1.newpasswd.focus();\r\n");
      out.write("    return false;\r\n");
      out.write("  }\r\n");
      out.write("\r\n");
      out.write("  return true;\r\n");
      out.write("}\r\n");
      out.write("function   fucCheck(obj)   \r\n");
      out.write("  {   \r\n");
      out.write("    var   i,j,k,strTemp1,strTemp2;   \r\n");
      out.write("    strTemp1=\"0123456789\";\r\n");
      out.write("    strTemp2=\"abcdefghijklmnopqrstuvwxyz\"; \r\n");
      out.write("    for(i=0;i<obj.length;i++)   \r\n");
      out.write("    {   \r\n");
      out.write("      j=strTemp1.indexOf(obj.charAt(i));   \r\n");
      out.write("      if(j==-1)   \r\n");
      out.write("     {   \r\n");
      out.write("      //说明有字符不是数字   \r\n");
      out.write("        break;  \r\n");
      out.write("     }\r\n");
      out.write("    } \r\n");
      out.write("    for(i=0;i<obj.length;i++)   \r\n");
      out.write("    {   \r\n");
      out.write("      k=strTemp2.indexOf(obj.charAt(i));   \r\n");
      out.write("      if(k==-1){\r\n");
      out.write("      //说明有字符不是字母   \r\n");
      out.write("        break; \r\n");
      out.write("       } \r\n");
      out.write("    } \r\n");
      out.write("     if(j==-1&&k==-1){\r\n");
      out.write("     \treturn 0;\r\n");
      out.write("     }else{\r\n");
      out.write("     \treturn 1;\r\n");
      out.write("     }         \r\n");
      out.write("    //说明不是数字和字母的组合 \r\n");
      out.write("//    alert(\"密码不符合要求，请重新输入！\");   \r\n");
      out.write("//    form1.passwd.select();\r\n");
      out.write("//    form1.passwd.focus();  \r\n");
      out.write("  }   \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function  checkPwd(obj)\r\n");
      out.write("{\r\n");
      out.write("    strRef = \"_\";\r\n");
      out.write("\tstrTmp = obj.value;\r\n");
      out.write("\tstr = \"\";\r\n");
      out.write("\tfor(i=0;i<strTmp.length;i++){\r\n");
      out.write("\t\tif(strTmp.charAt(i) != ' '){\r\n");
      out.write("\t\t\tstr += strTmp.charAt(i);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tfor (i=0;i<str.length;i++) {\r\n");
      out.write("\t\ttempChar= str.substring(i,i+1);\r\n");
      out.write("\t\tif (strRef.indexOf(tempChar,0)!=-1) {\r\n");
      out.write("\t\t\treturn false; \r\n");
      out.write("\t\t}  \r\n");
      out.write("\t}\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<form name=\"form1\" method=\"post\" action=\"user.do?action=modPasswd\">\r\n");
      out.write("\r\n");
      out.write("<input type=\"hidden\" name=\"no\" value=\"");
      if (_jspx_meth_c_005fout_005f0(_jspx_page_context))
        return;
      out.write("\">\r\n");
      out.write("<table width=\"100%\" style=\"border:0px;border-bottom:1px solid #cccccc\">\r\n");
      out.write(" <tr>\r\n");
      out.write("  <td nowrap><b>");
      out.print(resource.srcStr("System.mod_psw") );
      out.write("：</b></td>\r\n");
      out.write(" </tr> \r\n");
      out.write("</table>\r\n");
      out.write(" <br>\r\n");
      out.write("<table align=\"center\" width=\"650\" border=\"0\" cellspacing=\"1\" cellpadding=\"4\" class=\"table1\">\r\n");
      out.write("  <!--  <tr class=\"tr1\">\r\n");
      out.write("    <td nowrap colspan=\"2\" align=\"center\">");
      out.print(resource.srcStr("System.mod_psw"));
      out.write("</td>\r\n");
      out.write("  </tr> -->\r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap width=\"100\">");
      out.print(resource.srcStr("Login.account") );
      out.write("：</td>\r\n");
      out.write("    <td nowrap>");
      if (_jspx_meth_c_005fout_005f1(_jspx_page_context))
        return;
      out.write("</td> \r\n");
      out.write("  </tr> \r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>");
      out.print(resource.srcStr("Login.old_psw") );
      out.write("：</td>\r\n");
      out.write("    <td nowrap><input type=\"password\" name=\"oldpasswd\" class=\"pane\" maxLength=\"16\">&nbsp;*</td> \r\n");
      out.write("  </tr>  \r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>");
      out.print(resource.srcStr("Main.new_psw") );
      out.write("：</td>\r\n");
      out.write("    <td nowrap><input type=\"password\" name=\"passwd\" class=\"pane\" maxLength=\"16\" onKeyUp=\"if(/\\W/.test(this.value)){alert('");
      out.print(resource.srcStr("system.passwdMessage4"));
      out.write("');this.value='';}\" onblur=\"if(fucCheck(this.value)&&this.value!=''){alert('");
      out.print(resource.srcStr("Main.have_digital") );
      out.write("');this.value='';this.select();}\">&nbsp;*（");
      out.print(resource.srcStr("Main.have_digital_psw") );
      out.write("）</td> \r\n");
      out.write("  </tr> \r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>");
      out.print(resource.srcStr("Main.sure_new_psw") );
      out.write("：</td>\r\n");
      out.write("    <td nowrap><input type=\"password\" name=\"newpasswd\" class=\"pane\" maxLength=\"16\" onKeyUp=\"if(/\\W/.test(this.value)){alert('");
      out.print(resource.srcStr("system.passwdMessage4"));
      out.write("');this.value='';}\" onblur=\"if(fucCheck(this.value)&&this.value!=''){alert('");
      out.print(resource.srcStr("Main.have_digital") );
      out.write("');this.value='';this.select();}\">&nbsp;*</td> \r\n");
      out.write("  </tr>   \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<p align=\"center\">\r\n");
      out.write("      <input class=\"button\" type=\"submit\" value='");
      out.print(resource.srcStr("Main.submit"));
      out.write("' onclick=\"return checkForm()\">&nbsp;\r\n");
      out.write("      <input class=\"button\" type=\"button\" value='");
      out.print(resource.srcStr("Main.cancel"));
      out.write("' onclick=\"javascript:window.history.back();\">\r\n");
      out.write("    </p>\r\n");
      out.write("\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
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

  private boolean _jspx_meth_c_005fout_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent(null);
    // /pages/system/user_passwd_mod.jsp(137,38) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userSession.account}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f1 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f1.setParent(null);
    // /pages/system/user_passwd_mod.jsp(150,15) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userSession.account}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f1 = _jspx_th_c_005fout_005f1.doStartTag();
    if (_jspx_th_c_005fout_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
    return false;
  }
}
