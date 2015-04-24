package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.weili.wechat.common.Resource;
import com.weili.wechat.common.GetResource;
import com.weili.wechat.common.*;

public final class homepage_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=GBK");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;


if(session.getAttribute("locale")==null||session.getAttribute("locale")=="") 
{ 
out.println("<SCRIPT language=JavaScript>");
out.println("window.top.location='login.jsp'");
out.println("</script>"); 
return;
}
 
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
 Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");

	if (resource==null){
  
      out.write("\r\n");
      out.write("<script language=\"JavaScript\">\r\n");
      out.write("   window.top.location=\"timeout.jsp\";\r\n");
      out.write("</script>\r\n");

return;}

      out.write("\r\n");
      out.write("<title>");
      out.print(resource.srcStr("Login.op_server"));
      out.write("</title>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\">\r\n");
      out.write("</head>\r\n");
      out.write(" \r\n");
 
	UserSession userSession = (UserSession)request.getSession().getAttribute("userSession");

	String loginStr = "";
	if(userSession != null){
		if(userSession.getOnline_flag()==-1){
			loginStr = resource.srcStr("Login.first_login");
		}else{
			loginStr = resource.srcStr("Login.previous_login_time") + ":" + userSession.getLoginTime()+" "+ resource.srcStr("Login.previous_login_ip") + ":"+ userSession.getLoginIp();
		}
	}else{
		loginStr = resource.srcStr("Login.exit_user");
	}
	String sid = request.getSession().getId();
 
      out.write("\r\n");
      out.write(" \r\n");
      out.write(" <script language=\"javascript\">\r\n");
      out.write(" \twindow.status = '");
      out.print(loginStr);
      out.write("';\r\n");
      out.write(" </script>\r\n");
      out.write("<frameset id=\"frameset1\" rows=\"94,*\" framespacing=\"0\" frameborder=\"no\" bordercolor=\"cccccc\">\r\n");
      out.write("\t<frame name=\"header\" src='header.jsp?sid=");
      out.print(sid );
      out.write("' marginwidth=\"0\" marginheight=\"0\" frameborder=\"0\" scrolling=\"no\" noresize>          \r\n");
      out.write("\t<frameset id=\"frameset3\" cols=\"215,*\"  framespacing=\"0\" frameborder=\"no\">\r\n");
      out.write("\t\t<frame name=\"menu\" src='menu.jsp?module=W&sid=");
      out.print(sid );
      out.write("' marginwidth=\"8\" marginheight=\"0\" frameborder=\"0\" scrolling=\"auto\" noresize>\r\n");
      out.write("\t\t<frameset id=\"frameset5\" rows=\"40,*\" framespacing=\"0\" frameborder=\"no\" bordercolor=\"cccccc\">\r\n");
      out.write("\t\t\t<frame name=\"userarea\" src='userframe.jsp?sid=");
      out.print(sid );
      out.write("' marginwidth=\"0\" marginheight=\"5\" frameborder=\"0\" scrolling=\"no\" noresize>\r\n");
      out.write("\t\t\t<frame name=\"workarea\" src='welcome.jsp' marginwidth=\"8\" marginheight=\"10\" frameborder=\"0\" scrolling=\"auto\" noresize>\r\n");
      out.write("\t\t</frameset>\r\n");
      out.write("\t</frameset>\r\n");
      out.write("</frameset><noframes></noframes>\r\n");
      out.write("\r\n");
      out.write("<!--javascript 国际化标识符-->\r\n");
      out.write("<div id=\"javascriptI18n\">\r\n");
      out.write("</div>\r\n");
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
