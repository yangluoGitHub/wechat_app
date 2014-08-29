package org.apache.jsp.pages.manage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.weili.wechat.common.Resource;
import com.weili.wechat.common.GetResource;

public final class media_005fmod_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/pages/manage/../../scripts/common.jsp");
  }

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
      out.write("//var imageSuffix = [\"jpg\",\"png\",\"gif\",\"jpeg\",\"bmp\"];\r\n");
      out.write("//var videoSuffix = [\"avi\",\"mp4\"];\r\n");
      out.write("//var voiceSuffix = [\"mp3\",\"amr\"];\r\n");
      out.write("var j$ = jQuery.noConflict();\r\n");
      out.write("/* j$().ready(function(){\r\n");
      out.write("\tvar res = j$(\"input[name='mediaResource']:checked\").val();\r\n");
      out.write("\tif(res == \"0\"){\r\n");
      out.write("\t\tj$(\"#localTr\").show();\r\n");
      out.write("\t\tj$(\"#linkTr\").hide();\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tj$(\"#linkTr\").show();\r\n");
      out.write("\t\tj$(\"#localTr\").hide();\r\n");
      out.write("\t}\r\n");
      out.write("\tj$(\"input[name='mediaResource']\").change(function(){\r\n");
      out.write("\t\tif(j$(this).val()==0){\r\n");
      out.write("\t\t\tj$(\"#localTr\").show();\r\n");
      out.write("\t\t\tj$(\"#linkTr\").hide();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(j$(this).val()==1){\r\n");
      out.write("\t\t\tj$(\"#localTr\").hide();\r\n");
      out.write("\t\t\tj$(\"#linkTr\").show();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}); */\r\n");
      out.write("function checkForm(){\r\n");
      out.write("\t/* var res = j$(\"input[name='mediaResource']:checked\").val();\r\n");
      out.write("\tvar type = j$(\"#mediaType\").val();\r\n");
      out.write("\tif(res == \"0\"){\r\n");
      out.write("\t\tif(isNull(j$(\"#file\").val())){\r\n");
      out.write("\t\t\talert(\"请选择要上传的文件\");  \r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tvar filename = j$(\"#file\").val();\r\n");
      out.write("\t\tif(!checkSuffix(filename,type)){\r\n");
      out.write("\t\t\talert(\"上传资源与资源类型不符\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tif(res == \"1\"){\r\n");
      out.write("\t\tif(isNull(j$(\"#link\").val())){\r\n");
      out.write("\t\t\talert(\"请输入资源链接\");  \r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tvar filename = j$(\"#link\").val();\r\n");
      out.write("\t\tif(!checkSuffix(filename,type)){\r\n");
      out.write("\t\t\talert(\"链接资源与资源类型不符\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t} */\r\n");
      out.write("\tif(isNull(j$(\"#mediaName\").val())){\r\n");
      out.write("\t\talert(\"请输入资源名称\");  \r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("function checkSuffix(filename,type){\r\n");
      out.write("\tvar i = filename.lastIndexOf(\".\");\r\n");
      out.write("\tvar suffix = filename.substring(i + 1);\r\n");
      out.write("\tif(type == \"image\"){\r\n");
      out.write("\t\tif(j$.inArray(suffix,imageSuffix) >-1){\r\n");
      out.write("\t\t\treturn true;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tif(type == \"video\"){\r\n");
      out.write("\t\tif(j$.inArray(suffix,videoSuffix) >-1){\r\n");
      out.write("\t\t\treturn true;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tif(type == \"voice\"){\r\n");
      out.write("\t\tif(j$.inArray(suffix,voiceSuffix) >-1){\r\n");
      out.write("\t\t\treturn true;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\treturn false;\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<br>\r\n");
      out.write("\r\n");
      out.write("<form name=\"form1\" method=\"post\" action=\"media.do?action=mod\" enctype=\"multipart/form-data\">\r\n");
      out.write("<input type=\"hidden\" name=\"id\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${media.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("<table id=\"infoTable\" align=\"center\" width=\"650\" border=\"0\" cellspacing=\"1\" cellpadding=\"5\" class=\"table1\" >\r\n");
      out.write(" <tr class=\"tr1\">\r\n");
      out.write("    <td nowrap colspan=\"2\" align=\"center\">修改资源</td>\r\n");
      out.write("  </tr>  \r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap width=\"120\">名称：</td>\r\n");
      out.write("    <td nowrap><input type=\"text\" name=\"mediaName\" id=\"mediaName\" maxlength=\"80\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${media.mediaName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">&nbsp;*</td> \r\n");
      out.write("  </tr>\r\n");
      out.write("  ");
      out.write("\r\n");
      out.write(" <!--  <tr class=\"tr3\">\r\n");
      out.write("  \t<td nowrap>资源来源</td>\r\n");
      out.write("  \t<td nowrap>\r\n");
      out.write("  \t<input type=\"radio\" name=\"mediaResource\"  value=\"0\" checked=\"checked\">本地上传 &nbsp;\r\n");
      out.write("  \t<input type=\"radio\" name=\"mediaResource\"  value=\"1\">外链\r\n");
      out.write("\t</td> \r\n");
      out.write("  </tr> -->\r\n");
      out.write("   ");
      out.write("\r\n");
      out.write("  <tr class=\"tr3\">\r\n");
      out.write("    <td nowrap width=\"120\">资源描述：</td>\r\n");
      out.write("    <td nowrap><input type=\"text\" name=\"describe\" id=\"describe\" maxlength=\"256\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${media.describe}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></td> \r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
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
}
