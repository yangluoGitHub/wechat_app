package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.weili.wechat.common.Resource;
import java.util.List;
import java.util.Iterator;
import com.weili.wechat.common.UserSession;
import com.weili.wechat.vo.Menu;
import com.weili.wechat.common.GetResource;

public final class menu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	UserSession userSession = (UserSession) session.getAttribute("userSession");
	if (userSession == null) {

      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t   window.top.location=\"timeout.jsp\";\r\n");
      out.write("\t </script>\r\n");

	//response.sendRedirect("timeout.jsp");
		return;
	}
	Resource resource = (Resource) GetResource.getOneResource(
			application, session.getAttribute("locale").toString());

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gbk\" />\r\n");
      out.write("<title></title>\r\n");
      out.write("<link rel=\"StyleSheet\" href=\"tree/css/tree.css\" type=\"text/css\">\r\n");
      out.write("<!-- <script type=\"text/javascript\" src=\"tree/js/tree.js\"></script> -->\r\n");
      out.write("\t\t<script language=\"JavaScript\" src=\"../scripts/jquery.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t\t<script type='text/javascript' src='../dwr/engine.js'></script>\r\n");
      out.write("\t\t<script type='text/javascript' src='../dwr/util.js'></script>\r\n");
      out.write("\t\t<script type='text/javascript' src='../dwr/interface/accountService.js'></script>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("body {\r\n");
      out.write("\tmargin-left: 0px;\r\n");
      out.write("\tmargin-top: 0px;\r\n");
      out.write("\tmargin-right: 0px;\r\n");
      out.write("\tmargin-bottom: 0px;\r\n");
      out.write("\theight: 100%;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#image {\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("\tmargin-top: 25px;\r\n");
      out.write("\tpadding-left: 13px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#mm {\r\n");
      out.write("\tmargin-left: 25px;\r\n");
      out.write("\tmargin-top: 0;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("a {\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".normal {\r\n");
      out.write("\tbackground-image: url(../images/sidebutton01.png);\r\n");
      out.write("\tbackground-repeat: no-repeat;\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("\tcolor: #1456A0;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".over {\r\n");
      out.write("\tbackground-image: url(../images/sidebutton01_1.png);\r\n");
      out.write("\tbackground-repeat: no-repeat;\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("\tcursor: hand;\r\n");
      out.write("\tcolor: #FFF;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".down {\r\n");
      out.write("\tbackground-image: url(../images/sidebutton01_1.png);\r\n");
      out.write("\tbackground-repeat: no-repeat;\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("\tcolor: #FFF;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#menuleft {\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\tfloat: left;\r\n");
      out.write("\theight: 100%;\r\n");
      out.write("\tbackground-color: #fff;\r\n");
      out.write("\tz-index: 2;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#menuright {\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\tfloat: right;\r\n");
      out.write("\theight: 100%;\r\n");
      out.write("\twidth: 198px;\r\n");
      out.write("\tleft: 0px;\r\n");
      out.write("\tbackground-image: url(../images/menu/side-bg02.png);\r\n");
      out.write("\tz-index: 1;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".u2 {\r\n");
      out.write("\tmargin-left: 0px;\r\n");
      out.write("\tmargin-top: 50px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".u2 li{\r\n");
      out.write("\tborder: 0px;\r\n");
      out.write("\tlist-style-type: none;\r\n");
      out.write("\theight: 111px;\r\n");
      out.write("\twidth: 26px;\r\n");
      out.write("\tfont-size: 14px;\r\n");
      out.write("\tfont-family: \"微软雅黑, 宋体\";\r\n");
      out.write("\tmargin-top: 0px;\r\n");
      out.write("\tpadding: 25px 0 0 0;\r\n");
      out.write("\tline-height: 100%;\r\n");
      out.write("}\r\n");
      out.write(".u1 li {\r\n");
      out.write("\twidth:171px;\r\n");
      out.write("\t/* background-image: url(../images/line2.png);\r\n");
      out.write("\tbackground-repeat: no-repeat;\r\n");
      out.write("\tbackground-position:bottom; */\r\n");
      out.write("\theight: 25px;\r\n");
      out.write("\tlist-style-type: none;\r\n");
      out.write("\tfont-size: 16px;\r\n");
      out.write("\tfont-family: \"微软雅黑, 宋体\";\r\n");
      out.write("\t\r\n");
      out.write("\t/* margin-left: 10px; */\r\n");
      out.write("}\r\n");
      out.write("li a{\r\n");
      out.write("\tcolor:white;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("a { color:white; white-space:nowrap;}\r\n");
      out.write(".jsonTree { font-size: 15px; font-family: \"微软雅黑, 宋体\"; }\r\n");
      out.write(".jsonTree a { text-decoration:none; }\r\n");
      out.write(".jsonTree dl { padding:0; margin:0; }\r\n");
      out.write(".jsonTree dt { margin-left:-20px;color: white; cursor:pointer; padding:10 28px; height:2em;/* line-height:2em; border-radius:5px; */ background-image: url(../images/line2.png);background-repeat: no-repeat;background-position:bottom; }\r\n");
      out.write(".jsonTree dd { margin:0;height:2em; }\r\n");
      out.write(".jsonTree dl dd { padding-left:2em;  margin-left:10px; font-size: 13px;}\r\n");
      out.write(".jsonTree dl dd a { display:block; padding:3px; border-radius:5px; }\r\n");
      out.write(".jsonTree-icon {background-image: url(../images/spot.png);background-repeat: no-repeat;}\r\n");
      out.write(".jsonTree-show dd { display:block; }\r\n");
      out.write(".jsonTree-hide dd { display:none; }\r\n");
      out.write(".jsonTree-show dt { left:-10px;background:#7EABCA; }\r\n");
      out.write(".jsonTree a:hover .jsonTree-name { text-decoration:none; }\r\n");
      out.write(".jsonTree .jsonTree-name { padding-left: 5px;  }\r\n");
      out.write("</style>\r\n");
      out.write("<script language=\"JavaScript\" >\r\n");
      out.write("function toWorkarea(link){\r\n");
      out.write("\ttop.workarea.location=link;\r\n");
      out.write("}\r\n");
      out.write("var jsonTree = function (wrap, obj) {\r\n");
      out.write("\tif (typeof wrap === 'string') wrap = document.getElementById(wrap);\r\n");
      out.write("\twrap.innerHTML = jsonTree.html(obj);\r\n");
      out.write("\tjsonTree.event(wrap);\r\n");
      out.write("};\r\n");
      out.write("// 生成UI\r\n");
      out.write("jsonTree.html = function (obj) {\r\n");
      out.write("\tvar html = [], i, length,\r\n");
      out.write("\t\ttoString = Object.prototype.toString,\r\n");
      out.write("\t\tisArray = function (obj) {\r\n");
      out.write("\t\t\treturn toString.call(obj) === '[object Array]';\r\n");
      out.write("\t\t};\r\n");
      out.write("\t\t\r\n");
      out.write("\tvar each = function (obj, parent) {\r\n");
      out.write("\t\tif (isArray(obj)) {\r\n");
      out.write("\t\t\tlength = obj.length;\r\n");
      out.write("\t\t\tfor (i = 0; i < length; i ++) {\r\n");
      out.write("\t\t\t\thtml.push('<dd><a href=\"');\r\n");
      out.write("\t\t\t\thtml.push(obj[i]['url']);\r\n");
      out.write("\t\t\t\thtml.push('\">');\r\n");
      out.write("\t\t\t\thtml.push('<img name=\"spot\" src=\"../images/spot.png\" style=\"border:2px #8FC3FE solid;\" \"/><span sytle=\"padding-left: 5px;\" class=\"jsonTree-name\" >');\r\n");
      out.write("\t\t\t\thtml.push(obj[i]['name']);\r\n");
      out.write("\t\t\t\thtml.push('</span></a></dd>');\r\n");
      out.write("\t\t\t};\r\n");
      out.write("\t\t} else if (typeof obj === 'object') {\r\n");
      out.write("\t\t\tfor (i in obj) {\r\n");
      out.write("\t\t\t\thtml.push('<dd><dl class=\"jsonTree-hide\"><dt><center>');\r\n");
      out.write("\t\t\t\thtml.push(i);\r\n");
      out.write("\t\t\t\thtml.push('</center></dt>');\r\n");
      out.write("\t\t\t\thtml.push(each(obj[i], i));\r\n");
      out.write("\t\t\t\thtml.push('</dl></dd>');\r\n");
      out.write("\t\t\t};\r\n");
      out.write("\t\t};\r\n");
      out.write("\t\treturn html;\r\n");
      out.write("\t};\r\n");
      out.write("\treturn '<dl class=\"jsonTree\">' + each(obj).join('') + '</dl>';\r\n");
      out.write("};\r\n");
      out.write("// 绑定行为\t\r\n");
      out.write("jsonTree.event = function (wrap) {\r\n");
      out.write("\twrap.onclick = function (event) {\r\n");
      out.write("\t\tevent = event || window.event;\r\n");
      out.write("\t\tvar link, bar,\r\n");
      out.write("\t\t\ttarget = event.target || event.srcElement,\r\n");
      out.write("\t\t\tparentNode = target.parentNode;\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tif (target.nodeName === 'DT') bar = target;\r\n");
      out.write("\t\tif (target.nodeName === 'A') link = target;\r\n");
      out.write("\t\tif (parentNode.nodeName === 'DT') bar = parentNode;\r\n");
      out.write("\t\tif (parentNode.nodeName ===  'A') link = parentNode;\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tif (bar) {\r\n");
      out.write("\t\t\t$('DT').each(function(){\r\n");
      out.write("\t\t\t\tif(this.parentNode.className == 'jsonTree-show'){\r\n");
      out.write("\t\t\t\t\tthis.parentNode.className = 'jsonTree-hide';\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\tbar.parentNode.className = bar.parentNode.className === 'jsonTree-show' ?\r\n");
      out.write("\t\t\t\t'jsonTree-hide' :\r\n");
      out.write("\t\t\t\t'jsonTree-show';\r\n");
      out.write("\t\t} \r\n");
      out.write("\t\tif(link){\r\n");
      out.write("\t\t\tvar thisImg = $(link).children().first()[0];\r\n");
      out.write("\t\t\tthisImg.style.border= '2px #1456A0 solid';\r\n");
      out.write("\t\t\t$(\"img[name='spot']\").not(thisImg).css('border','2px #8FC3FE solid');\r\n");
      out.write("\t\t}\r\n");
      out.write("\t};\r\n");
      out.write("};\r\n");
      out.write("\r\n");
      out.write("var lastObj = null;\r\n");
      out.write("var downObj = null;\r\n");
      out.write("\r\n");
      out.write("function mouseover(obj){  \r\n");
      out.write("  if(lastObj != null){\r\n");
      out.write("    lastObj.className = \"normal\";\r\n");
      out.write("  }\r\n");
      out.write("  obj.className = \"over\";\r\n");
      out.write("  lastObj = obj;\r\n");
      out.write("  if(downObj != null){\r\n");
      out.write("    downObj.className = \"down\";\r\n");
      out.write("  }\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function mouseout(obj){\r\n");
      out.write("  if(obj != downObj){\r\n");
      out.write("    obj.className = \"normal\";\r\n");
      out.write("  }\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function mousedown(obj){\r\n");
      out.write("  if(downObj != null){\r\n");
      out.write("    downObj.className = \"normal\";\r\n");
      out.write("  }\r\n");
      out.write("  obj.className = \"down\";\r\n");
      out.write("  downObj = obj;\r\n");
      out.write("  \r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function goModule(obj){\r\n");
      out.write("   // top.mid.showMenu();\r\n");
      out.write("    top.menu.location=\"menu.jsp?module=\"+obj+\"&sid=\"+'");
      out.print(request.getParameter("sid"));
      out.write("'; \r\n");
      out.write("    if(obj == 'A'){\r\n");
      out.write("    \ttop.workarea.location=\"account.do?action=pubAccountInfo\";\r\n");
      out.write("    }else{\r\n");
      out.write("    \ttop.workarea.location=\"welcome.jsp\";\r\n");
      out.write("    }\r\n");
      out.write("   // top.workarea.location=\"welcome.jsp\";\r\n");
      out.write("   ");
      out.write("\r\n");
      out.write("    top.header.location=\"header.jsp?module=\"+obj+\"&sid=\"+'");
      out.print(request.getParameter("sid"));
      out.write("'; \r\n");
      out.write("}\r\n");
      out.write("function goModPasswd(){\r\n");
      out.write("    top.mid.showMenu();\r\n");
      out.write("    top.menu.location=\"menu.jsp?module=\";\r\n");
      out.write("    top.workarea.location=\"op.do?action=modPasswdPage\";\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function goHelp(){\r\n");
      out.write("    top.mid.showMenu();\r\n");
      out.write("    top.menu.location=\"menu.jsp?module=\";\r\n");
      out.write("    top.workarea.location=\"./help/helpindex.jsp\";\r\n");
      out.write("    \r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function showHelp(){\r\n");
      out.write("\twindow.open(\"./help/help.jsp\",\"help\",\"height=605, width=930, top=50, left=40, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no\");\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function qryLogo(){\r\n");
      out.write("\tvar logo2 =\"\";\r\n");
      out.write("\tDWREngine.setAsync(false);\r\n");
      out.write("\taccountService.getLogoLocation(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userSession.wechatId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\",\r\n");
      out.write("\tfunction result(data) {\r\n");
      out.write("\t\tlogo2 = data;\r\n");
      out.write("\t});\r\n");
      out.write("\tDWREngine.setAsync(true);\r\n");
      out.write("\treturn logo2;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<body topmargin=\"0\" marginheight=\"0\" leftmargin=\"0px\" >\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\t<div id=\"menuleft\">\r\n");
      out.write("\t\t\t<div class=\"main\">\r\n");
      out.write("\t\t\t\t<ul class=\"u2\">\r\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write(" \t\tvar mod='");
      out.print(request.getParameter("module"));
      out.write("';\r\n");
      out.write("\t\t\tif (mod!=null&&mod!='W')\r\n");
      out.write("        \tmousedown(document.getElementById(mod));\r\n");
      out.write("  \t\t</script>\r\n");
      out.write("\t\t");

			String module = request.getParameter("module");
			String sid = request.getParameter("sid");
			if (sid != null && sid != "" && !sid.equals(session.getId())) {
		
      out.write("\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("             window.top.location=\"loginagain.jsp\";\r\n");
      out.write("         </script>\r\n");
      out.write("\t\t");

			}
			module = module == null ? "" : module;
			if (module.equals("W")) {
		
      out.write("\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("             self.location='menu_image.jsp?sid='+'");
      out.print(sid);
      out.write("';\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t\t");

			}
			List menuList = userSession.getMenuList();
		
      out.write("\r\n");
      out.write("\t<div id=\"menuright\">\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div id=\"image\">\r\n");
      out.write("\t\t\t");

				if (module.equals("A")) {
			
      out.write("\r\n");
      out.write("\t\t\t<img id=\"logo\" >\r\n");
      out.write("\t\t\t<script type=\"text/javascript\" >\r\n");
      out.write("\t\t\t\t$(\"#logo\").attr(\"src\", \"../images/menu/\" + qryLogo() + \"\");\r\n");
      out.write("\t\t\t</script>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t");

				} else if (module.equals("B")) {
			
      out.write("\r\n");
      out.write("\t\t\t<img src=\"../images/plan.png\">\r\n");
      out.write("\t\t\t");

				} else if (module.equals("E")) {
			
      out.write("\r\n");
      out.write("\t\t\t<img src=\"../images/menu/icon03.png\">\r\n");
      out.write("\t\t\t");

				} else if (module.equals("F")) {
			
      out.write("\r\n");
      out.write("\t\t\t<img src=\"../images/menu/icon02.png\">\r\n");
      out.write("\t\t\t");

				}
			
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div id=\"mm\">\r\n");
      out.write("\t\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\t\t//d = new dTree('d');\r\n");
      out.write("\t\t\t\t//d.config.folderLinks = false;\r\n");
      out.write("\t\t\t\t//document.write(d);\r\n");
      out.write("\t\t\t\tvar _data = {\r\n");
      out.write("\t\t\t");

			String no;
			StringBuffer sb = new StringBuffer();
			StringBuffer sb1 = new StringBuffer();
			Iterator it = menuList.iterator();
			while (it.hasNext()) {

				Menu menu = (Menu) it.next();
				no = menu.getNo();
				if (no.startsWith(module)) {
					
					if (no.length() == 3){
						sb1.append("\"").append(menu.getName()).append("\":[");
						for(Object o : menuList){
							Menu m = (Menu)o;
							if(m.getSuperMenu().getNo() == no){
								sb1.append("{\"name\":\"").append(m.getName()).append("\",\"url\":\"javascript:toWorkarea('").append(m.getURL())
								.append("')\"},");
							}
						}
						if(sb1.length()>0) sb1.delete(sb1.length()-1,sb1.length());
						sb1.append("],");
					}
					
				}

			}
			if(sb1.length()>0)
				sb1.delete(sb1.length()-1,sb1.length());
			out.print(sb1.toString());
      out.write(" \r\n");
      out.write("\t\t\t};\r\n");
      out.write("\t\t\tjsonTree('mm',_data);         \r\n");
      out.write("\t\t\t</script>\r\n");
      out.write("\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
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
    // /pages/menu.jsp(300,5) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userSession.menuList}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /pages/menu.jsp(300,5) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("menu");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t");
          if (_jspx_meth_c_005fif_005f0(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("\r\n");
          out.write("\t\t\t\t\t");
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

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /pages/menu.jsp(301,6) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${menu.level==1}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t        \t<li id=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${menu.no}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" class=\"normal\" onmouseover=\"mouseover(this)\" \r\n");
        out.write("\t\t\t\t        \t\tonmouseout=\"mouseout(this)\" onclick=\"mousedown(this);goModule('");
        if (_jspx_meth_c_005fout_005f0(_jspx_th_c_005fif_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
          return true;
        out.write("')\">\r\n");
        out.write("\t\t\t\t        \t\t<p title='");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${menu.name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("'><b>");
        if (_jspx_meth_c_005fout_005f1(_jspx_th_c_005fif_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
          return true;
        out.write("</b></p>\r\n");
        out.write("\t\t\t\t        \t</li>\r\n");
        out.write("\t\t\t\t      \t");
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
    // /pages/menu.jsp(303,77) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
    // /pages/menu.jsp(304,41) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
