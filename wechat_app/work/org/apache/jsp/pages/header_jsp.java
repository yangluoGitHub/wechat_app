package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
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
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\" />\r\n");
      out.write("\t\t<title></title>\r\n");
      out.write("\t\t<script type='text/javascript' src=\"../scripts/jquery.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<script type='text/javascript' src='../dwr/engine.js'></script>\r\n");
      out.write("\t\t<script type='text/javascript' src='../dwr/util.js'></script>\r\n");
      out.write("\t\t<script type='text/javascript' src='../dwr/interface/userService.js'></script>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("table {\r\n");
      out.write("\tfont-weight: bold;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("img{\r\n");
      out.write("\tposition: relative;\r\n");
      out.write("}\r\n");
      out.write(".content {\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\tleft: 86%;\r\n");
      out.write("\ttop: 71px;\r\n");
      out.write("\twidth: 100px;\r\n");
      out.write("\tborder: 0;\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("\tfont-size: 15px;\r\n");
      out.write("\tfont-family: 宋体;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("ul {\r\n");
      out.write("\tmargin: 0;\r\n");
      out.write("\tpadding: 0;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("ul li {\r\n");
      out.write("\tborder: 0px;\r\n");
      out.write("\tfloat: left;\r\n");
      out.write("\t/*margin-left: 0px;\r\n");
      out.write("\twidth: 34px;\r\n");
      out.write("\theight: 32px;\r\n");
      out.write("\tlist-style-type: none;\r\n");
      out.write("\tfont-size: 15px;*/\r\n");
      out.write("\tposition: relative;\r\n");
      out.write("\t\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".op {\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\tleft: 84%;\r\n");
      out.write("\ttop: 10px;\r\n");
      out.write("\twidth: 90px;\r\n");
      out.write("\tfont-size: 16px;\r\n");
      out.write("\tcolor: gold;\r\n");
      out.write("\tfont-family: 微软雅黑, 宋体;\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("body {\r\n");
      out.write("\tmargin: 0;\r\n");
      out.write("\tpadding: 0;\r\n");
      out.write("\tposition: relative;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".normal {\r\n");
      out.write("\tbackground-image: url(\"../images/menu.png\");\r\n");
      out.write("\tbackground-repeat: no-repeat;\r\n");
      out.write("\tcolor: #FFF;\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".over {\r\n");
      out.write("\tbackground-image: url(\"../images/menu_click.png\");\r\n");
      out.write("\tbackground-repeat: no-repeat;\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("\tcursor: hand;\r\n");
      out.write("\tcolor: #1456A0;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".down {\r\n");
      out.write("\tbackground-image: url(\"../images/menu_click.png\");\r\n");
      out.write("\tbackground-repeat: no-repeat;\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("\tcolor: #1456A0;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#menu {\r\n");
      out.write("\twidth: 100%;\r\n");
      out.write("\theight: 9px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".button {\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\tleft: 219px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".button p {\r\n");
      out.write("\tborder: 0px;\r\n");
      out.write("\tfont-size: 14px;\r\n");
      out.write("\tfont-family: \"微软雅黑, 宋体\";\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("\tfloat: left;\r\n");
      out.write("\twidth: 174px;\r\n");
      out.write("\theight: 37px;\r\n");
      out.write("\tpadding-left: 0;\r\n");
      out.write("\tpadding-right: 0;\r\n");
      out.write("\tpadding-top: 8px;\r\n");
      out.write("\tpadding-bottom:0px;\r\n");
      out.write("\tmargin-bottom: 0px;\r\n");
      out.write("\tmargin-left: 25px;\r\n");
      out.write("\tmargin-top: 60px;\r\n");
      out.write("\tmargin-right: 0px;\r\n");
      out.write("\tposition: relative;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("<script language=\"JavaScript\">\r\n");
      out.write("function refresh(){\r\n");
      out.write("\ttop.location.reload();\r\n");
      out.write("}\r\n");
      out.write("function backhome(){\r\n");
      out.write("\ttop.workarea.location=\"welcome.jsp\";\r\n");
      out.write("\ttop.menu.location=\"menu.jsp?module=W&sid=\"+'");
      out.print(request.getParameter("sid"));
      out.write("';\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("var lastObj = null;\r\n");
      out.write("var downObj = null;\r\n");
      out.write("var ps = document.getElementsByTagName(\"p\");\r\n");
      out.write("function mouseover(obj){  \r\n");
      out.write("  if(lastObj != null){\r\n");
      out.write("    lastObj.className = \"normal\";\r\n");
      out.write("  }\r\n");
      out.write("  obj.className = \"over\";\r\n");
      out.write("  lastObj = obj;\r\n");
      out.write("  obj.style.zIndex=2;\r\n");
      out.write("  for(var i=0;i<ps.length;i++){\r\n");
      out.write("    if(ps[i]==obj) continue;\r\n");
      out.write("    if(ps[i]==downObj) continue;\r\n");
      out.write("    ps[i].style.zIndex = 0;\r\n");
      out.write("  }\r\n");
      out.write("  if(downObj != null){\r\n");
      out.write("    downObj.className = \"down\";\r\n");
      out.write("  }\r\n");
      out.write("  \r\n");
      out.write("}\r\n");
      out.write("function mouseout(obj){\r\n");
      out.write("  if(obj != downObj){\r\n");
      out.write("    obj.className = \"normal\";\r\n");
      out.write("  }\r\n");
      out.write("}\r\n");
      out.write("function mousedown(obj){\r\n");
      out.write("  if(downObj != null){\r\n");
      out.write("    downObj.className = \"normal\";\r\n");
      out.write("  }\r\n");
      out.write("  obj.className = \"down\";\r\n");
      out.write("  downObj = obj;\r\n");
      out.write("  obj.style.zIndex = 3;\r\n");
      out.write("  for(var i=0;i<ps.length;i++){\r\n");
      out.write("    if(ps[i]==obj) continue;\r\n");
      out.write("    ps[i].style.zIndex = 0;\r\n");
      out.write("  }\r\n");
      out.write("  \r\n");
      out.write("}\r\n");
      out.write("function goModule(obj){\r\n");
      out.write("    //top.mid.showMenu();\r\n");
      out.write("    top.menu.location=\"menu.jsp?module=\"+obj+\"&sid=\"+'");
      out.print(request.getParameter("sid"));
      out.write("';\r\n");
      out.write("    //top.left.location=\"left.jsp?module=\"+obj; \r\n");
      out.write("    if(obj == 'A'){\r\n");
      out.write("    \ttop.workarea.location=\"account.do?action=pubAccountInfo\";\r\n");
      out.write("    }else{\r\n");
      out.write("    \ttop.workarea.location=\"welcome.jsp\";\r\n");
      out.write("    }\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function goModPasswd(){\r\n");
      out.write("    top.mid.showMenu();\r\n");
      out.write("    top.menu.location=\"menu.jsp?module=\";\r\n");
      out.write("    top.workarea.location=\"op.do?action=modPasswdPage\";\r\n");
      out.write("}\r\n");
      out.write("function goHelp(){\r\n");
      out.write("    top.mid.showMenu();\r\n");
      out.write("    top.menu.location=\"menu.jsp?module=\";\r\n");
      out.write("    top.workarea.location=\"./help/helpindex.jsp\";\r\n");
      out.write("    \r\n");
      out.write("}\r\n");
      out.write("function showHelp(){\r\n");
      out.write("\twindow.open(\"./help/help.jsp\",\"help\",\"height=605, width=930, top=50, left=40, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no\");\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function switchWechatID() {\r\n");
      out.write("\tDWREngine.setAsync(false);\r\n");
      out.write("\tuserService.qrySwitchableUserPubAccount('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userSession.account}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("', '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userSession.wechatId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("', function(data) {\r\n");
      out.write("\t\tif(data == null || data.length == 0) {\r\n");
      out.write("\t\t\talert(\"没有其他可管理的公众号!\");\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\tvar workDoc =  top.workarea.document;\r\n");
      out.write("\t\t\tvar div = workDoc.getElementById(\"userpaDiv\");\r\n");
      out.write("\t\t\tif(div && div.style.display == \"block\"){\r\n");
      out.write("\t\t\t\treturn;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(div && div.style.display == \"none\"){\r\n");
      out.write("\t\t\t\tdiv.style.display = \"block\";\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(!div) {\r\n");
      out.write("\t\t\t\tvar div = workDoc.createElement(\"div\");\r\n");
      out.write("\t\t\t\tvar width = workDoc.body.scrollWidth;\r\n");
      out.write("\t\t\t\tvar height = workDoc.body.scrollHeight;\r\n");
      out.write("\t\t\t\tdiv.id=\"userpaDiv\";\r\n");
      out.write("\t\t\t\tdiv.style.background=\"#FFFFFF\"; \r\n");
      out.write("\t\t\t\tdiv.style.filter=\"alpha(opacity=90)\";\r\n");
      out.write("\t\t\t\tdiv.style.left=\"0px\";\r\n");
      out.write("\t\t\t\tdiv.style.position=\"absolute\";\r\n");
      out.write("\t\t\t\tdiv.style.top=\"0px\";\r\n");
      out.write("\t\t\t\tdiv.style.width = width + \"px\"; \r\n");
      out.write("\t\t\t\tdiv.style.height = height+ \"px\"; \r\n");
      out.write("\t\t\t\tdiv.style.zIndex = \"100\";\r\n");
      out.write("\t\t\t\tdiv.style.textAlign = \"center\";\r\n");
      out.write("\t\t\t\tdiv.style.display=\"block\";\r\n");
      out.write("\t\t\t\tworkDoc.body.appendChild(div);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tvar formStart = \"<form id='paForm' action='user.do?action=switchPubAccount' method='post'>\";\r\n");
      out.write("\t\t\tvar optionHtml = \"\";\r\n");
      out.write("\t\t\tvar selectStart = \"<div style='font-size:15px;padding:180px;'><b>请选择要切换的公众号：</b><select id='userpa' name='userpa'>\";\r\n");
      out.write("\t\t\tvar selectEnd = \"</select>\";\r\n");
      out.write("\t\t\tvar buttonHtml = \"&nbsp;<input type='button' id='ok' value='确定'>&nbsp;\" + \r\n");
      out.write("\t\t\t\t\"<input type='button' id='cancle' value='取消'></div>\";\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tfor(var i = 0; i < data.length; i++) {\r\n");
      out.write("\t\t\t\tvar id = data[i][0];\r\n");
      out.write("\t\t\t\tvar name = data[i][1];\r\n");
      out.write("\t\t\t\toptionHtml += \"<option value='\"+id+\"'>\"+name+\"</option>\";\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tvar selectHtml = formStart+selectStart+optionHtml+selectEnd+buttonHtml+\"</form>\";\r\n");
      out.write("\t\t\tdiv.innerHTML=selectHtml;\r\n");
      out.write("\t\t\tvar cancle = workDoc.getElementById(\"cancle\");\r\n");
      out.write("\t\t\tcancle.onclick = function(){\r\n");
      out.write("\t\t\t\tvar ddd = workDoc.getElementById(\"userpaDiv\");\r\n");
      out.write("\t\t\t\tddd.style.display=\"none\";\r\n");
      out.write("\t\t\t};\r\n");
      out.write("\t\t\tvar ok = workDoc.getElementById(\"ok\");\r\n");
      out.write("\t\t\tvar form = workDoc.getElementById(\"paForm\");\r\n");
      out.write("\t\t\tok.onclick = function(){\r\n");
      out.write("\t\t\t\tvar select = workDoc.getElementById(\"userpa\");\r\n");
      out.write("\t\t\t\ttop.header.location.reload();\r\n");
      out.write("\t\t\t\ttop.menu.location.reload();\r\n");
      out.write("\t\t\t\tform.submit();\r\n");
      out.write("\t\t\t};\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\tDWREngine.setAsync(true);\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\t<img src=\"../images/title/top-bg.png\" id=\"headerimage\"\r\n");
      out.write("\t\tstyle=\"position:relative;width:100%;height:94px;float:left;\"\r\n");
      out.write("\t\tborder=\"0\" hspace=\"0\" vspace=\"0\" />\r\n");
      out.write("\t<img src=\"../images/title/zijin_logo.png\" id=\"bankname\"\r\n");
      out.write("\t\tstyle=\"position: absolute;left: 17px;top: 17px;\">\r\n");
      out.write("\t<img src=\"../images/title/title.png\" id=\"title\"\r\n");
      out.write("\t\tstyle=\"position: absolute;left: 220px;top: 13px;\">\r\n");
      out.write("<div id=\"header\">\r\n");
      out.write("  <div class=\"content\">\r\n");
      out.write("  <ul>\r\n");
      out.write("  \t<A href=\"#\" onclick=\"javascript:switchWechatID();\">\r\n");
      out.write("  \t\t<img alt=\"切换公众号管理\" title=\"切换公众号管理\" src=\"../images/title/switch.png\" border=\"0\" style=\"cursor: hand;  z-index:3;\" onmouseover=\"javascript:src='../images/title/switch_1.png'\" onmouseout=\"javascript:src='../images/title/switch.png'\">\r\n");
      out.write("  \t</A>\r\n");
      out.write("  \t<A href=\"#\" onclick=\"javascript:backhome();\">\r\n");
      out.write("  \t\t<img alt=\"首页\" title=\"首页\" src=\"../images/title/home.png\" border=\"0\" style=\"cursor: hand; margin-right: 0 px;z-index:2;\" onmouseover=\"javascript:src='../images/title/home_1.png'\" onmouseout=\"javascript:src='../images/title/home.png'\">\r\n");
      out.write("  \t</A>\r\n");
      out.write("  \t<A href=\"#\" onclick=\"javascript:top.location='login.do?action=logout&MENU&sid=");
      out.print(request.getParameter("sid"));
      out.write("'\">\r\n");
      out.write("  \t\t<img alt=\"退出\" title=\"退出\" src=\"../images/title/exit.png\" border=\"0\" style=\"cursor: hand;  z-index:1;\" onmouseover=\"javascript:src='../images/title/exit_1.png'\" onmouseout=\"javascript:src='../images/title/exit.png'\">\r\n");
      out.write("  \t</A> \r\n");
      out.write("  </ul>\r\n");
      out.write("  </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"button\">\r\n");
      out.write("\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tif(");
      out.print(request.getParameter("module"));
      out.write("!=null){\r\n");
      out.write("    \tmousedown(document.getElementById('");
      out.print(request.getParameter("module"));
      out.write("'));\r\n");
      out.write("\t}\r\n");
      out.write("\t//解决在1024*768的分辨率下面出现退出按钮位置靠后问题\r\n");
      out.write("\tvar content = document.getElementById(\"content\");\r\n");
      out.write("\tif (screen.width <= 1024) {\r\n");
      out.write("\t\tcontent.style.left = '80%';\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("</html>\r\n");
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
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /pages/header.jsp(290,2) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userSession.menuList}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /pages/header.jsp(290,2) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("menu");
    // /pages/header.jsp(290,2) name = varStatus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVarStatus("st");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t");
          if (_jspx_meth_c_005fif_005f0(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("\r\n");
          out.write("\t\t");
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
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /pages/header.jsp(291,3) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${menu.level==1}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t<p id=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${menu.no}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" class=\"normal\" onmouseover=\"mouseover(this)\" style=\"margin-left: -25 px; z-index:1;\"\r\n");
        out.write("\t\t\t\t\tonmouseout=\"mouseout(this)\"\r\n");
        out.write("\t\t\t\t\tonclick=\"mousedown(this);goModule('");
        if (_jspx_meth_c_005fout_005f0(_jspx_th_c_005fif_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
          return true;
        out.write("')\">\r\n");
        out.write("\t\t\t\t\t<b>");
        if (_jspx_meth_c_005fout_005f1(_jspx_th_c_005fif_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
          return true;
        out.write("</b>\r\n");
        out.write("\t\t\t\t</p>\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /pages/header.jsp(294,40) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${menu.no}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f1 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /pages/header.jsp(295,8) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${menu.name}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f1 = _jspx_th_c_005fout_005f1.doStartTag();
    if (_jspx_th_c_005fout_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
    return false;
  }
}
