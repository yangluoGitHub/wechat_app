package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class welcome_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

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
      response.setContentType("text/html; charset=GBK");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<HTML xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<HEAD>\r\n");
      out.write("<META http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\">\r\n");
      out.write("\r\n");
      out.write("<link href=\"../styles/jquery-ui.css\" rel=\"stylesheet\"type=\"text/css\"/>\r\n");
      out.write("<script language=\"JavaScript\" src=\"../scripts/jquery.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script language=\"JavaScript\" src=\"../scripts/json2.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"../scripts/jquery-ui.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script language=\"JavaScript\" src=\"../scripts/common.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script language=\"JavaScript\" src=\"../scripts/prototype.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<link href=\"../styles/common.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("<TITLE>welcome page</TITLE>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write(".normal{\r\n");
      out.write("\tposition:absolute;\r\n");
      out.write("\tmargin:0 auto;\r\n");
      out.write("\ttop:50%;\r\n");
      out.write("\tleft:50%;\r\n");
      out.write("\tmargin:-340px 0 0 -540px;\r\n");
      out.write("\twidth: 1080px;\r\n");
      out.write("\theight: 685px;\r\n");
      out.write("}\r\n");
      out.write(".little{\r\n");
      out.write("\tposition: relative;\r\n");
      out.write("\twidth: 90%;\r\n");
      out.write("\theight: 90%;\r\n");
      out.write("\ttop: 5%;\r\n");
      out.write("\toverflow: hidden;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</HEAD>\r\n");
      out.write("\r\n");
      out.write("<BODY>\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("var j$ = jQuery.noConflict();\r\n");
      out.write("var userNo = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userSession.account}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("</script>\r\n");
      out.write("<div style=\"border: 0;width: 100%;height: 100%;text-align: center;\">\r\n");
      out.write("<!--  \r\n");
      out.write("\t<marquee scrollAmount=\"4\" direction=\"left\" onmouseover=\"this.stop();\" onmouseout=\"this.start()\">\r\n");
      out.write("\t\t<p style=\"font-family: 黑体;color: gray\"><font size=\"3px\" >");
      if (_jspx_meth_c_005fout_005f0(_jspx_page_context))
        return;
      out.write("，您好！欢迎使用TSP加钞管理系统！</font></p>\r\n");
      out.write("\t</marquee>\r\n");
      out.write("-->\r\n");
      out.write("\t<div id=\"wel\" class=\"little\">\r\n");
      out.write("\t\t<img id=\"welimg\" src=\"../images/img01.png\" border=\"0\" style=\"text-align: center;\">\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tif(screen.width > 1280 && screen.height > 900){\r\n");
      out.write("\t\tvar wel = document.getElementById(\"wel\");\r\n");
      out.write("\t\tvar welimg = document.getElementById(\"welimg\");\r\n");
      out.write("\t\twelimg.style.width = 1080+\"px\";\r\n");
      out.write("\t\twelimg.style.height = 685+\"px\";\r\n");
      out.write("\t\twel.className = \"normal\";\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("</BODY>\r\n");
      out.write("</HTML>\r\n");
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
    // /pages/welcome.jsp(45,59) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userSession.name}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }
}
