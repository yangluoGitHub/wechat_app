package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class timeout_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");

     String zh_mess1 = "会话超时";String tw_mess1 = "會話超時";String cn_mess1 = "Session Timeout";
     String zh_mess2 = "请重新登录!";String tw_mess2 = "請重新登錄!";String cn_mess2 = "Please re-login!";
     String zh_mess3 = "提示信息";String tw_mess3 = "提示信息";String cn_mess3 = "Tips";
     String zh_close = "关 闭";String tw_close="關 閉";String cn_close="Close";

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gbk\" />\r\n");
      out.write("<link href=\"../styles/common.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("<title>");
      out.print(zh_mess1 );
      out.write('，');
      out.print(zh_mess2 );
      out.write("!</title>\r\n");
      out.write("<script language=\"JavaScript\" for=\"document\" event=\"onkeydown\">\r\n");
      out.write("\tif(event.keyCode==13){\r\n");
      out.write("\t\tdocument.getElementById('subButton').click();\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<br><br>\r\n");
      out.write("<div align=\"center\" style=\"font-size:14px;\"><b>");
      out.print(zh_mess1 );
      out.write("</b></div>\r\n");
      out.write("<table align=\"center\" width=\"450\" border=\"0\" cellspacing=\"1\" cellpadding=\"4\" class=\"table1\">\r\n");
      out.write("\r\n");
      out.write("\t<tr class=\"tr6\">\r\n");
      out.write("\t\t<td align=\"left\"><img src=\"../images/notify.gif\">");
      out.print(zh_mess3 );
      out.write("</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\r\n");
      out.write("    <br>\r\n");
      out.write("\t<tr class=\"tr3\">\r\n");
      out.write("\t\t<td align=\"center\">");
      out.print(zh_mess2 );
      out.write("</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" <tr class=\"tr4\">\r\n");
      out.write(" <td nowrap align=\"center\">\r\n");
      out.write("\t<c:choose>\r\n");
      out.write("\t<c:when test=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${backFlag==0}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("\t</c:when>\r\n");
      out.write("\t<c:otherwise>\r\n");
      out.write("\t   <p align=\"center\">\r\n");
      out.write("\t\t<input class=\"button\" id=\"subButton\" type=\"button\" value='");
      out.print(zh_close );
      out.write("' onclick=\"javascript:top.location='login.do?action=logout'\">\r\n");
      out.write("\t   </p> \r\n");
      out.write("\t</c:otherwise>\r\n");
      out.write("\t</c:choose>\r\n");
      out.write("\t</td>\r\n");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
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
}
