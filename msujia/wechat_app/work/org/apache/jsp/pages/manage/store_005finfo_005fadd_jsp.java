package org.apache.jsp.pages.manage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.weili.wechat.common.Resource;
import com.weili.wechat.common.GetResource;

public final class store_005finfo_005fadd_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/pages/manage/../../scripts/common.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
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
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gbk\" />\r\n");
      out.write("<title></title>\r\n");
      out.write("<script language=\"JavaScript\" src=\"../scripts/common.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script language=\"JavaScript\" src=\"../scripts/prototype.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script language=\"JavaScript\" src=\"../scripts/jquery.js\" type=\"text/javascript\"></script>\r\n");
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
      out.write("<input type=\"hidden\" id=\"System.input_maintainer_name\" value='");
      out.print(resource.srcStr("System.input_maintainer_name"));
      out.write("'/>\r\n");
      out.write("<input type=\"hidden\" id=\"System.fault_email_format\" value='");
      out.print(resource.srcStr("System.fault_email_format"));
      out.write("'/>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script language=\"JavaScript\">\r\n");
      out.write("var j$ = jQuery.noConflict();\r\n");
      out.write("j$().ready(function(){\r\n");
      out.write("\tj$(\":text\").addClass(\"pane\");\r\n");
      out.write("\t\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("function checkForm(){\r\n");
      out.write("  if(isNull($F('storeName'))){\r\n");
      out.write("    alert(\"请输入门店名称！\");\r\n");
      out.write("    return false;\r\n");
      out.write("  }\r\n");
      out.write("  if(getLength($F('storeName')) > 60) {\r\n");
      out.write("  \talert(\"名称不得超过60字节!\");\r\n");
      out.write("  \t$('storeName').select();\r\n");
      out.write("  \treturn false;\r\n");
      out.write("  }\r\n");
      out.write("  if(isNull($F('storeNo'))){\r\n");
      out.write("    alert(\"请输入门店编号！\");\r\n");
      out.write("    return false;\r\n");
      out.write("  }\r\n");
      out.write("  return true;\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<br>\r\n");
      out.write("\r\n");
      out.write("<form name=\"form1\" method=\"post\" action=\"storeInfo.do?action=add\" enctype=\"multipart/form-data\">\r\n");
      out.write("<table id=\"infoTable\" align=\"center\" width=\"650\" border=\"0\" cellspacing=\"1\" cellpadding=\"5\" class=\"table1\" >\r\n");
      out.write(" <tr class=\"tr1\">\r\n");
      out.write("    <td nowrap colspan=\"2\" align=\"center\">添加门店信息</td>\r\n");
      out.write("  </tr>  \r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap width=\"120\">门店名称：</td>\r\n");
      out.write("    <td nowrap><input type=\"text\" name=\"storeName\" maxlength=\"64\">&nbsp;*不超过60个字符</td> \r\n");
      out.write("  </tr> \r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>门店编号：</td>\r\n");
      out.write("    <td nowrap><input type=\"text\" name=\"storeNo\" maxlength=\"64\">&nbsp;*字母和数字、下划线组成，不超过60字符</td> \r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>门店电话：</td>\r\n");
      out.write("    <td nowrap><input type=\"text\" name=\"phone\"  maxlength=\"20\">&nbsp;*</td> \r\n");
      out.write("  </tr>\r\n");
      out.write("   <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>门店类型：</td>\r\n");
      out.write("    <td nowrap>\r\n");
      out.write("\t\t\t\t\t<select name=\"storeClassificationId\" class=\"inputline\" style=\"width: 153px\"\r\n");
      out.write("\t\t\t\t\t\t<option value=\"\">\r\n");
      out.write("\t\t\t\t\t\t\t----------\r\n");
      out.write("\t\t\t\t\t\t</option>\r\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>营业时间：</td>\r\n");
      out.write("    <td nowrap><input type=\"text\" name=\"businessHours\"  maxlength=\"128\">&nbsp;*上午10:00到下午6:00</td> \r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>起送价：</td>\r\n");
      out.write("    <td nowrap><input type=\"text\" name=\"flagFallPrice\"  maxlength=\"5\">&nbsp;*元</td> \r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>配送费：</td>\r\n");
      out.write("    <td nowrap><input type=\"text\" name=\"deliveryCharges\"  maxlength=\"20\">&nbsp;*元</td> \r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>预计送达时间：</td>\r\n");
      out.write("    <td nowrap><input type=\"text\" name=\"deliveryTime\"  maxlength=\"5\">&nbsp;*分钟</td> \r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>服务半径：</td>\r\n");
      out.write("    <td nowrap><input type=\"text\" name=\"serviceRadius\"  maxlength=\"36\">&nbsp;*KM</td> \r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>配送区域：</td>\r\n");
      out.write("    <td nowrap><input type=\"text\" name=\"deliveryArea\"  maxlength=\"128\">&nbsp;*</td> \r\n");
      out.write("  </tr>\r\n");
      out.write("    <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>登录密码：</td>\r\n");
      out.write("    <td nowrap><input type=\"text\" name=\"passwd\"  maxlength=\"50\">&nbsp;*</td> \r\n");
      out.write("  </tr>\r\n");
      out.write("    <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>门店地址：</td>\r\n");
      out.write("    <td nowrap><input type=\"text\" name=\"address\"  maxlength=\"200\">&nbsp;*</td> \r\n");
      out.write("  </tr>\r\n");
      out.write("   <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>门店logo：</td>\r\n");
      out.write("    <td nowrap><input type=\"file\" name=\"storeLogo\" id=\"storeLogo\" >&nbsp;*.png.jpg</td> \r\n");
      out.write("  </tr>\r\n");
      out.write("    <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap>是否启用：</td>\r\n");
      out.write("    <td nowrap>\r\n");
      out.write("    <select name=\"onLine\" size=\"1\">\r\n");
      out.write("\t\t<option value=\"0\" selected>不启用</option>\r\n");
      out.write("\t\t<option value=\"1\">启用</option>\r\n");
      out.write("\t</select>\r\n");
      out.write("\t&nbsp;*</td> \r\n");
      out.write("  </tr>\r\n");
      out.write("  \r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("    <p align=\"center\">\r\n");
      out.write("      <input class=\"button\" type=\"submit\" value='");
      out.print(resource.srcStr("Main.submit"));
      out.write("' onclick=\"return checkForm()\">\r\n");
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

  private boolean _jspx_meth_c_005fforEach_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /pages/manage/store_info_add.jsp(77,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("scf");
    // /pages/manage/store_info_add.jsp(77,6) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${scfList}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t<option value='");
          if (_jspx_meth_c_005fout_005f0(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("'>\r\n");
          out.write("\t\t\t\t\t\t\t\t");
          if (_jspx_meth_c_005fout_005f1(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t</option>\r\n");
          out.write("\t\t\t\t\t\t");
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
    // /pages/manage/store_info_add.jsp(78,22) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${scf.id}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
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
    // /pages/manage/store_info_add.jsp(79,8) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${scf.clName}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f1 = _jspx_th_c_005fout_005f1.doStartTag();
    if (_jspx_th_c_005fout_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
    return false;
  }
}
