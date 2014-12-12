package org.apache.jsp.pages.demo3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.weili.wechat.common.Resource;
import com.weili.wechat.common.GetResource;

public final class success_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
 
   Resource resource =  new Resource("zh_CN");
   if( session.getAttribute("locale") != null )
   resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("\r\n");
      out.write("    <head>\r\n");
      out.write("\r\n");
      out.write("        <meta charset=\"utf-8\">\r\n");
      out.write("        <title>Fullscreen Responsive Register Template</title>\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("        <meta name=\"description\" content=\"\">\r\n");
      out.write("        <meta name=\"author\" content=\"\">\r\n");
      out.write("\r\n");
      out.write("        <!-- CSS -->\r\n");
      out.write("        \r\n");
      out.write("        <link rel=\"stylesheet\" href=\"assets/bootstrap/css/bootstrap.min.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"assets/css/style.css\">\r\n");
      out.write("        \r\n");
      out.write("        <!-- Javascript -->\r\n");
      out.write("        <script type=\"text/javascript\" src=\"assets/js/jquery-1.8.2.min.js\"></script>\r\n");
      out.write("        <script type=\"text/javascript\" src=\"assets/bootstrap/js/bootstrap.min.js\"></script>\r\n");
      out.write("        <script type=\"text/javascript\" src=\"assets/js/jquery.backstretch.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->\r\n");
      out.write("        <!--[if lt IE 9]>\r\n");
      out.write("            <script src=\"http://html5shim.googlecode.com/svn/trunk/html5.js\"></script>\r\n");
      out.write("        <![endif]-->\r\n");
      out.write("\r\n");
      out.write("    </head>\r\n");
      out.write("\r\n");
      out.write("    <body>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"header\">\r\n");
      out.write("            <div class=\"container\">\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("                    <div class=\"logo span4\">\r\n");
      out.write("                        <h1><a href=\"\">iApp Register <span class=\"red\">.</span></a></h1>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"links span8\">\r\n");
      out.write("                        <a class=\"home\" href=\"\" rel=\"tooltip\" data-placement=\"bottom\" data-original-title=\"Home\"></a>\r\n");
      out.write("                        <a class=\"blog\" href=\"\" rel=\"tooltip\" data-placement=\"bottom\" data-original-title=\"Blog\"></a>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"register-container container\">\r\n");
      out.write("            <div class=\"row\">\r\n");
      out.write("                \r\n");
      out.write("                <div class=\"register span6\">\r\n");
      out.write("                    <form action=\"register.do?action=register\" method=\"post\">\r\n");
      out.write("                        <h2><span class=\"red\"><strong>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${message}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</strong></span></h2>\r\n");
      out.write("                        \r\n");
      out.write("                       \r\n");
      out.write("                    </form>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\t\t<div align=\"center\">Copyright &copy; 2014.Company name All rights reserved.</div>\r\n");
      out.write("        \r\n");
      out.write("\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("jQuery(document).ready(function() {\r\n");
      out.write("\r\n");
      out.write("    /*\r\n");
      out.write("        Background slideshow\r\n");
      out.write("    */\r\n");
      out.write("    alert('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${message}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("');\r\n");
      out.write("    $.backstretch([\r\n");
      out.write("      \"assets/img/backgrounds/1.jpg\"\r\n");
      out.write("    , \"assets/img/backgrounds/2.jpg\"\r\n");
      out.write("    , \"assets/img/backgrounds/3.jpg\"\r\n");
      out.write("    ], {duration: 3000, fade: 750});\r\n");
      out.write("\r\n");
      out.write("    /*\r\n");
      out.write("        Tooltips\r\n");
      out.write("    */\r\n");
      out.write("    $('.links a.home').tooltip();\r\n");
      out.write("    $('.links a.blog').tooltip();\r\n");
      out.write("\r\n");
      out.write("    /*\r\n");
      out.write("        Form validation\r\n");
      out.write("    */\r\n");
      out.write("    $('.register form').submit(function(){\r\n");
      out.write("        $(this).find(\"label[for='username']\").html('Username');\r\n");
      out.write("        $(this).find(\"label[for='password']\").html('Password');\r\n");
      out.write("        ////\r\n");
      out.write("        \r\n");
      out.write("        var username = $(this).find('input#username').val();\r\n");
      out.write("        var password = $(this).find('input#password').val();\r\n");
      out.write("        \r\n");
      out.write("        if(username == '') {\r\n");
      out.write("            $(this).find(\"label[for='username']\").append(\"<span style='display:none' class='red'> - Please enter a valid username.</span>\");\r\n");
      out.write("            $(this).find(\"label[for='username'] span\").fadeIn('medium');\r\n");
      out.write("            return false;\r\n");
      out.write("        }\r\n");
      out.write("        \r\n");
      out.write("        if(password == '') {\r\n");
      out.write("            $(this).find(\"label[for='password']\").append(\"<span style='display:none' class='red'> - Please enter a valid password.</span>\");\r\n");
      out.write("            $(this).find(\"label[for='password'] span\").fadeIn('medium');\r\n");
      out.write("            return false;\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
