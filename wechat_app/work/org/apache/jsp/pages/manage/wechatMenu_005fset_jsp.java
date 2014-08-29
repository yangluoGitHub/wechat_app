package org.apache.jsp.pages.manage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.weili.wechat.common.Resource;
import com.weili.wechat.common.GetResource;

public final class wechatMenu_005fset_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      			"../error.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("\r\n");
 Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gbk\" />\r\n");
      out.write("<link type=\"text/css\"  rel=\"stylesheet\" href=\"../styles/apiUser.css\"/>\r\n");
      out.write("<link type=\"text/css\"  rel=\"stylesheet\" href=\"../styles/account.css\"/>\r\n");
      out.write("\r\n");
      out.write("<!-- 操作对话框式样 -->\r\n");
      out.write("<link type=\"text/css\"  rel=\"stylesheet\" href=\"../styles/css.css\"/>\r\n");
      out.write("\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(".Menuboxtwo{padding:10px}\r\n");
      out.write(".Menuboxtwo{padding:5px}\r\n");
      out.write(".Menuboxtwo{border-color:#368ee0}\r\n");
      out.write(".Menuboxtwo{border:2px solid #ddd;border-top:0}\r\n");
      out.write(".Menuboxtwo{*zoom:1;padding:20px;background:#fff}\r\n");
      out.write(".alert{padding:8px 35px 8px 14px;margin-bottom:20px;text-shadow:0 1px 0 rgba(255,255,255,0.5);background-color:#fcf8e3;border:1px solid #fbeed5;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px}\r\n");
      out.write("</style>\r\n");
      out.write("<title></title>\r\n");
      out.write("\r\n");
      out.write("<script type='text/javascript' src='../dwr/engine.js'></script>\r\n");
      out.write("<script type='text/javascript' src='../dwr/interface/weMenuService.js'></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"../scripts/jquery.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"../scripts/jquery.json.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"../scripts/jquery-ui.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"../scripts/weixin.bind.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"../scripts/dialog.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"../scripts/jquery.blockUI.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script language=\"JavaScript\" src=\"../scripts/json2.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script language=\"JavaScript\" src=\"../scripts/jquery.autocomplete.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\r\n");
      out.write("<link href=\"../styles/jquery.autocomplete.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body style=\"overflow-x: hidden;\">\r\n");
      out.write("\r\n");
      out.write("<input type=\"hidden\" id=\"accIdField\" name=\"accIdField\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wechatId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\r\n");
      out.write("<div style=\"height:100%;width:100%; float:left; margin-top:0px;\">\r\n");
      out.write("\t\t\t        <div style=\"width:100%; margin:0px 0 5px 0;\">\r\n");
      out.write("\t\t\t            <div style=\"height:29px;\">\r\n");
      out.write("\t\t\t\t\t         <div class=\"left\"><img src=\"../images/wechat_menu/title_left.gif\"  style=\"width:1%;\"/></div>\r\n");
      out.write("\t\t\t\t\t\t     <div class=\"title_mid left\" style=\"width:98%;\">自定义菜单</div>\r\n");
      out.write("\t\t\t\t\t\t     <div class=\"right\"><img src=\"../images/wechat_menu/title_right.gif\" style=\"width:1%;\"/></div>\r\n");
      out.write("\t\t\t\t\t    </div>\r\n");
      out.write("\t\t\t\t\t    \r\n");
      out.write("\t\t\t\t\t      <div class=\"right_con\" style=\"width:100%;padding:0;\">\r\n");
      out.write("\t\t\t\t\t\t   <div class=\"Menuboxtwo\" >\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"alert\" >\r\n");
      out.write("\t\t\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p>一级菜单最多只能开启3个，二级子菜单最多开启5个！</p>\r\n");
      out.write("                                <p>先保存再发布，默认发布最近一次保存的！</p>\r\n");
      out.write("                                <!-- <p>当您为自定义菜单填写链接地址时请填写以\"http://\"开头，这样可以保证用户手机浏览的兼容性更好！</p> -->\r\n");
      out.write("                                <p>创建自定义菜单后，由于微信客户端缓存，需要24小时微信客户端才会展现出来。建议测试时可以尝试取消关注公众账号后，再次关注，则可以看到创建后的效果！</p>\r\n");
      out.write("\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<div id=\"jsweb_c4\" class=\"Contentboxdiv1\" style=\"padding:0;\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"CMOP-custom CM-dsp\" >\r\n");
      out.write("\t\t\t\t\t        \t<div style=\"FLOAT: left; margin-left:100px;\">\r\n");
      out.write("\t\t\t\t\t        \t<div ><p><a id=\"addRootMenuBtn\" href=\"#c4\" class=\"wx-button06\"></a></p><p class=\"CM-cl03\">主菜单名最多5个字，子菜单名最多12个字。</p></div>\r\n");
      out.write("\t\t\t\t\t            <div >\r\n");
      out.write("\t\t\t\t\t            \r\n");
      out.write("\t\t\t\t\t            \t<div class=\"CMOP-customleft f_l\">\r\n");
      out.write("\t\t\t\t\t                \t<div class=\"CMOP-ct01\">\r\n");
      out.write("\t\t\t\t\t                        <p style=\"width:112px;\">菜单名</p>\r\n");
      out.write("\t\t\t\t\t                        <p style=\"width:161px;\">业务功能名称或者URL</p>\r\n");
      out.write("\t\t\t\t\t                        <p style=\"width:160px;background:none;\">操作</p>\r\n");
      out.write("\t\t\t\t\t                    </div>\r\n");
      out.write("\t\t\t\t\t                    \r\n");
      out.write("\t\t\t\t\t                    <div class=\"CMOP-ct02 ov_fl\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<table width=\"431\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t  <tbody id=\"menuContainer\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t  \t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t  \t  ");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t  \t\t<!-- \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t  <tr name=\"rootMenu\" state=\"display\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        <td width=\"111\" height=\"30\" align=\"center\" valign=\"middle\" bgcolor=\"#f8f8f8\" class=\"CMOP-cttd01\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t<div><span title=\"地方法\"  name=\"menuName\">地方法</span><input name=\"menuName_i\" value=\"\" style=\"display:none;width:100px;\"/></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        </td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        <td width=\"161\" height=\"30\" align=\"center\" valign=\"middle\" bgcolor=\"#f8f8f8\" class=\"CMOP-cttd01\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t<div><span title=\"111\"  name=\"menuKey\">111</span><input name=\"menuKey_i\" value=\"\" style=\"display:none;width:150px;\"/></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        </td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        <td width=\"124\" height=\"30\" align=\"right\" valign=\"middle\" bgcolor=\"#f8f8f8\" class=\"CMOP-cttd03\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t<a style=\"margin-right:15px;\" href=\"#c4\" class=\"CM-cta03\"></a> <a href=\"#c4\" class=\"CM-cta02\"></a> <a href=\"#c4\" class=\"CM-cta01\"></a>　\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        </td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t      </tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t      \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t     \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t  <tr name=\"rootMenu\" state=\"display\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        <td width=\"111\" height=\"30\" align=\"center\" valign=\"middle\" bgcolor=\"#f8f8f8\" class=\"CMOP-cttd01\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t<div><span title=\"威武的\"  name=\"menuName\">威武的</span><input name=\"menuName_i\" value=\"\" style=\"display:none;width:100px;\"/></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        </td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        <td width=\"161\" height=\"30\" align=\"center\" valign=\"middle\" bgcolor=\"#f8f8f8\" class=\"CMOP-cttd01\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t<div><span title=\"丰富的\"  name=\"menuKey\">丰富的</span><input name=\"menuKey_i\" value=\"\" style=\"display:none;width:150px;\"/></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        </td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        <td width=\"124\" height=\"30\" align=\"right\" valign=\"middle\" bgcolor=\"#f8f8f8\" class=\"CMOP-cttd03\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t<a style=\"margin-right:15px;\" href=\"#c4\" class=\"CM-cta03\"></a> <a href=\"#c4\" class=\"CM-cta02\"></a> <a href=\"#c4\" class=\"CM-cta01\"></a>　\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        </td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t      </tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t      \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t     \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t  <tr name=\"rootMenu\" state=\"display\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        <td width=\"111\" height=\"30\" align=\"center\" valign=\"middle\" bgcolor=\"#f8f8f8\" class=\"CMOP-cttd01\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t<div><span title=\"12\"  name=\"menuName\">12</span><input name=\"menuName_i\" value=\"\" style=\"display:none;width:100px;\"/></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        </td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        <td width=\"161\" height=\"30\" align=\"center\" valign=\"middle\" bgcolor=\"#f8f8f8\" class=\"CMOP-cttd01\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t<div><span title=\"3232\"  name=\"menuKey\">3232</span><input name=\"menuKey_i\" value=\"\" style=\"display:none;width:150px;\"/></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        </td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        <td width=\"124\" height=\"30\" align=\"right\" valign=\"middle\" bgcolor=\"#f8f8f8\" class=\"CMOP-cttd03\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t<a style=\"margin-right:15px;\" href=\"#c4\" class=\"CM-cta03\"></a> <a href=\"#c4\" class=\"CM-cta02\"></a> <a href=\"#c4\" class=\"CM-cta01\"></a>　\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t        </td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t      </tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t      \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t      -->\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t     \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t  </tbody>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t                  </div>\r\n");
      out.write("\t\t\t\t\t                  \r\n");
      out.write("\t\t\t\t\t                   <div class=\"CMOP-ct04\" style=\"margin-top:10px\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<a href=\"#c4\" id=\"saveMenuBtn\" class=\"wx-button04 f_l\">保　存</a>\r\n");
      out.write("\t\t\t\t\t                   \t<a href=\"#c4\" id=\"deployMenuBtn\" style=\"margin-left:100px\" class=\"wx-button04 f_1\">发　布</a>\r\n");
      out.write("\t\t\t\t\t                   \t</div>\r\n");
      out.write("\t\t\t\t\t                   <p>&nbsp;</p> <p>&nbsp;</p> <p>&nbsp;</p> <p>&nbsp;</p> <p>&nbsp;</p> <p>&nbsp;</p> <p>&nbsp;</p> <p>&nbsp;</p> <p>&nbsp;</p>\r\n");
      out.write("\t\t\t\t\t              </div>\r\n");
      out.write("\t\t\t\t\t              </div>\r\n");
      out.write("\t\t\t\t\t              </div>\r\n");
      out.write("\t\t\t\t\t                <div class=\"CMOP-customright f_l ov_fl\" style=\"margin-left:100px\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"weixin-menucn f_l\" >\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div id=\"previewCont\" style=\"display:none;\" class=\"weixin-ul-one1 ov_fl\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"weixin-ul-one2\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"weixin-ul-one3\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<ul id=\"previewSubItems\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<ul id=\"previewRootItems\" class=\"weixin-ul-two\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t                </div>\r\n");
      out.write("\t\t\t\t\t            \r\n");
      out.write("\t\t\t\t\t        </div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t </div>\r\n");
      out.write("\t <div style=\"display:none\" id=\"message-dialog\" title=\"提示\">\r\n");
      out.write("  <p><span class=\"\" style=\"float: left; margin: 0 7px 20px 0;\"></span></p>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("<script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\t\t");
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
    // /pages/manage/wechatMenu_set.jsp(90,15) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${retList}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /pages/manage/wechatMenu_set.jsp(90,15) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("menuItemVOlist");
    // /pages/manage/wechatMenu_set.jsp(90,15) name = varStatus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVarStatus("status");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t  \t\t");
          if (_jspx_meth_c_005fforEach_005f1(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t  \t\t\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t  \t");
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

  private boolean _jspx_meth_c_005fforEach_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /pages/manage/wechatMenu_set.jsp(91,14) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${menuItemVOlist}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /pages/manage/wechatMenu_set.jsp(91,14) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setVar("item");
    // /pages/manage/wechatMenu_set.jsp(91,14) name = varStatus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setVarStatus("varStatus");
    int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
      if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t  \t\t    ");
          if (_jspx_meth_c_005fif_005f0(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
            return true;
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t  \t\t    ");
          if (_jspx_meth_c_005fif_005f1(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
            return true;
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t  \t\t    \r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t  \t\t");
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
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f1);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /pages/manage/wechatMenu_set.jsp(92,18) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.menuLevel == 1}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t  \t\t    \t<tr name=\"rootMenu\" state=\"display\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t        <td width=\"111\" height=\"30\" align=\"center\" valign=\"middle\" bgcolor=\"#f8f8f8\" class=\"CMOP-cttd01\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t<div><span title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.name }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"  name=\"menuName\">");
        if (_jspx_meth_c_005fout_005f0(_jspx_th_c_005fif_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
          return true;
        out.write("</span><input name=\"menuName_i\" value=\"\" style=\"display:none;width:100px;\"/></div>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t        </td>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t        <td width=\"161\" height=\"30\" align=\"center\" valign=\"middle\" bgcolor=\"#f8f8f8\" class=\"CMOP-cttd01\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t<div><span title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.wechatFuncName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\"  name=\"menuKey\">");
        if (_jspx_meth_c_005fout_005f1(_jspx_th_c_005fif_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
          return true;
        out.write("</span><input id=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" name=\"menuKey_i\" value=\"\" onkeyup=\"completeCon(this)\" onblur =\"blurFunc(this)\" style=\"display:none;width:150px;\"/></div>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t        </td>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t        <td width=\"160\" height=\"30\" align=\"right\" valign=\"middle\" bgcolor=\"#f8f8f8\" class=\"CMOP-cttd03\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t<a style=\"margin-right:15px;\" href=\"#c4\" class=\"CM-cta03\"></a> <a href=\"#c4\" class=\"CM-cta02\"></a> <a href=\"#c4\" class=\"CM-cta01\"></a>　\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t        </td>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t      </tr>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t  \t\t    ");
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

  private boolean _jspx_meth_c_005fout_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /pages/manage/wechatMenu_set.jsp(95,71) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.name}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f1 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /pages/manage/wechatMenu_set.jsp(98,79) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.wechatFuncName}${item.wechatUrl}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f1 = _jspx_th_c_005fout_005f1.doStartTag();
    if (_jspx_th_c_005fout_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /pages/manage/wechatMenu_set.jsp(105,18) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.menuLevel == 2}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t  \t\t    \t<tr name=\"menuItem\" state=\"display\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t<td width=\"111\" height=\"30\" align=\"center\" valign=\"middle\" class=\"CMOP-cttd01 CM-cl03\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t\t<div><span title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.name }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" name=\"menuName\">");
        if (_jspx_meth_c_005fout_005f2(_jspx_th_c_005fif_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
          return true;
        out.write("</span><input name=\"menuName_i\" value=\"\" style=\"display:none;width:100px;border:1px solid #f8f8f8;\"/></div>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t</td>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t<td width=\"161\" height=\"30\" align=\"center\" valign=\"middle\" class=\"CMOP-cttd01 CM-cl03\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t\t<div><span title=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.wechatFuncName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" name=\"menuKey\">");
        if (_jspx_meth_c_005fout_005f3(_jspx_th_c_005fif_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
          return true;
        out.write("</span><input id=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" name=\"menuKey_i\" value=\"\" onkeyup=\"completeCon(this)\" onblur =\"blurFunc(this)\" style=\"display:none;width:150px;border:1px solid #f8f8f8;\"/></div>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t</td>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t<td width=\"160\" height=\"30\" align=\"right\" valign=\"middle\" class=\"CMOP-cttd03\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t\t<a style=\"margin-right:15px;\" href=\"#c4\" class=\"CM-cta05\"></a> <a href=\"#c4\" class=\"CM-cta04\"></a>　\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t        \t</td>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t\t      \t</tr>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t  \t\t    ");
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

  private boolean _jspx_meth_c_005fout_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f2 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f1);
    // /pages/manage/wechatMenu_set.jsp(108,71) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f2.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.name}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f2 = _jspx_th_c_005fout_005f2.doStartTag();
    if (_jspx_th_c_005fout_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f3 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f1);
    // /pages/manage/wechatMenu_set.jsp(111,79) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f3.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.wechatFuncName}${item.wechatUrl}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f3 = _jspx_th_c_005fout_005f3.doStartTag();
    if (_jspx_th_c_005fout_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f3);
    return false;
  }
}
