package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.weili.wechat.common.Resource;
import com.weili.wechat.common.GetResource;

public final class calendar_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    Throwable exception = org.apache.jasper.runtime.JspRuntimeLibrary.getThrowable(request);
    if (exception != null) {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
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
 Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gbk\" />\r\n");
      out.write("<title></title>\r\n");
      out.write("\r\n");
      out.write("<style type=text/css>\r\n");
      out.write("td{font-size:12;font-family:arial;text-align:center}\r\n");
      out.write("td.dt{font-size:11;font-family:arial;text-align:center}\r\n");
      out.write("a{color:blue}\r\n");
      out.write("a:hover{color:red}\r\n");
      out.write("a.bt{color:#888888}\r\n");
      out.write("</style>\r\n");
      out.write("<script language=javascript>\r\n");
      out.write("<!--//\r\n");
      out.write("var str='',i,j,yy,mm,openbound,callback;\r\n");
      out.write("var fld1,fld2;\r\n");
      out.write("var wp=window.parent;\r\n");
      out.write("var cf=wp.document.getElementById(\"CalFrame\");\r\n");
      out.write("var fld,curday,today=new Date();\r\n");
      out.write("today.setHours(0);today.setMinutes(0);today.setSeconds(0);today.setMilliseconds(0);\r\n");
      out.write("//var lastyear=today.getYear(),lastmonth=today.getMonth();\r\n");
      out.write("function parseDate(s)\r\n");
      out.write("{\r\n");
      out.write("\tvar reg=new RegExp(\"[^0-9-]\",\"\")\r\n");
      out.write("\tif(s.search(reg)>=0)return today;\r\n");
      out.write("\tvar ss=s.split(\"-\");\r\n");
      out.write("\tif(ss.length!=3)return today;\r\n");
      out.write("\tif(isNaN(ss[0])||isNaN(ss[1])||isNaN(ss[2]))return today;\r\n");
      out.write("\treturn new Date(parseFloat(ss[0]),parseFloat(ss[1])-1,parseFloat(ss[2]));\r\n");
      out.write("}\r\n");
      out.write("function resizeCalendar(){cf.width=180;cf.height=197;}\r\n");
      out.write("function initCalendar()\r\n");
      out.write("{\r\n");
      out.write("\tif(fld1&&fld1.value.length>0){curday=parseDate(fld1.value);}\r\n");
      out.write("\telse if(fld2&&fld2.value.length>0){curday=parseDate(fld2.value);}\r\n");
      out.write("\telse curday=today;\r\n");
      out.write("\tdrawCalendar(curday.getFullYear(),curday.getMonth());\r\n");
      out.write("}\r\n");
      out.write("function drawCalendar(y,m)\r\n");
      out.write("{\r\n");
      out.write("\tvar x=new Date(y,m,1),mv=x.getDay(),d=x.getDate(),de;\r\n");
      out.write("\tyy=x.getFullYear();mm=x.getMonth();\r\n");
      out.write("        var y1 = document.getElementById(\"y1\");\r\n");
      out.write("        var m1 = document.getElementById(\"m1\");\r\n");
      out.write("        y1.value=yy;\r\n");
      out.write("        m1.value=mm+1>9?mm+1:\"0\"+(mm+1)\r\n");
      out.write("\tfor(var i=1;i<=mv;i++)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tde=document.getElementById(\"d\"+i);\r\n");
      out.write("\t\tde.innerHTML=\"\";\r\n");
      out.write("\t\tde.bgColor=\"\";\r\n");
      out.write("\t}\r\n");
      out.write("\twhile(x.getMonth()==mm)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tde=document.getElementById(\"d\"+(d+mv));\r\n");
      out.write("\t\tif(x.getTime()==curday.getTime())\r\n");
      out.write("\t\t\tde.bgColor=\"#dddddd\";\r\n");
      out.write("\t\telse\r\n");
      out.write("\t\t\tde.bgColor=\"white\";\r\n");
      out.write("\t\tif(x.getTime()==today.getTime())\r\n");
      out.write("\t\t\tde.innerHTML=\"<a href=javascript:setDate(\"+d+\");><font color=red>\"+d+\"</font></a>\";\r\n");
      out.write("\t\telse if(x.getTime()<today.getTime())\r\n");
      out.write("\t\t\tif(openbound){de.innerHTML=\"<a href=javascript:setDate(\"+d+\"); class=bt>\"+d+\"</a>\";}\r\n");
      out.write("\t\t\telse{de.innerHTML=\"<font color=#888888>\"+d+\"</font>\";}\r\n");
      out.write("\t\telse\r\n");
      out.write("\t\t\tde.innerHTML=\"<a href=javascript:setDate(\"+d+\");>\"+d+\"</a>\";\r\n");
      out.write("\t\tx.setDate(++d);\r\n");
      out.write("\t}\r\n");
      out.write("\twhile(d+mv<=42)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tde=document.getElementById(\"d\"+(d+mv));\r\n");
      out.write("\t\tde.innerHTML=\"\";\r\n");
      out.write("\t\tde.bgColor=\"\";\r\n");
      out.write("\t\td++;\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function setDate(d)\r\n");
      out.write("{\r\n");
      out.write("\twp.hideCalendar();\r\n");
      out.write("        var tmp1 = mm+1;\r\n");
      out.write("        var tmp2 = d;\r\n");
      out.write("        if(tmp1<10){\r\n");
      out.write("          tmp1 = \"0\"+tmp1;\r\n");
      out.write("        }\r\n");
      out.write("        if(tmp2<10){\r\n");
      out.write("          tmp2 = \"0\"+tmp2;\r\n");
      out.write("        }\r\n");
      out.write("\tvar dstr=yy+\"-\"+tmp1+\"-\"+tmp2;\r\n");
      out.write("\tif(callback&&callback.length>0){eval(\"wp.\"+callback+\"(\\\"\"+dstr+\"\\\")\");}\r\n");
      out.write("\telse{fld1.value=dstr;fld1.select();}\r\n");
      out.write("}\r\n");
      out.write("//-->\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body topmargin=0 leftmargin=0 bottommargin=0 rightmargin=0 bgcolor=white onload=\"resizeCalendar();\">\r\n");
      out.write("\r\n");
      out.write("<table width=180 id=tbl0 bgcolor=#336699 border=0 cellpadding=1 cellspacing=0>\r\n");
      out.write("<tr><td nowrap>\r\n");
      out.write("\r\n");
      out.write("<table width=100% border=0 cellpadding=2 cellspacing=1 bgcolor=white>\r\n");
      out.write("<tr bgcolor=#bbbbbb>\r\n");
      out.write("<td nowrap width=16 id=prev><a href=javascript:drawCalendar(yy,mm-1);><img src=../images/calendarPrev.gif border=0 width=\"16\" height=\"16\"></a></td>\r\n");
      out.write("<td nowrap>\r\n");
      out.write("<select name=\"y1\" id=\"y1\" style=\"width:55px;height:20px;\" onchange=\"drawCalendar(this.value,mm)\">\r\n");
      out.write("<option value=\"1990\">1990</option>\r\n");
      out.write("<option value=\"1991\">1991</option>\r\n");
      out.write("<option value=\"1992\">1992</option>\r\n");
      out.write("<option value=\"1993\">1993</option>\r\n");
      out.write("<option value=\"1994\">1994</option>\r\n");
      out.write("<option value=\"1995\">1995</option>\r\n");
      out.write("<option value=\"1996\">1996</option>\r\n");
      out.write("<option value=\"1997\">1997</option>\r\n");
      out.write("<option value=\"1998\">1998</option>\r\n");
      out.write("<option value=\"1999\">1999</option>\r\n");
      out.write("<option value=\"2000\">2000</option>\r\n");
      out.write("<option value=\"2001\">2001</option>\r\n");
      out.write("<option value=\"2002\">2002</option>\r\n");
      out.write("<option value=\"2003\">2003</option>\r\n");
      out.write("<option value=\"2004\">2004</option>\r\n");
      out.write("<option value=\"2005\">2005</option>\r\n");
      out.write("<option value=\"2006\">2006</option>\r\n");
      out.write("<option value=\"2007\">2007</option>\r\n");
      out.write("<option value=\"2008\">2008</option>\r\n");
      out.write("<option value=\"2009\">2009</option>\r\n");
      out.write("<option value=\"2010\">2010</option>\r\n");
      out.write("<option value=\"2011\">2011</option>\r\n");
      out.write("<option value=\"2012\">2012</option>\r\n");
      out.write("<option value=\"2013\">2013</option>\r\n");
      out.write("<option value=\"2014\">2014</option>\r\n");
      out.write("<option value=\"2015\">2015</option>\r\n");
      out.write("<option value=\"2016\">2016</option>\r\n");
      out.write("<option value=\"2017\">2017</option>\r\n");
      out.write("<option value=\"2018\">2018</option>\r\n");
      out.write("<option value=\"2019\">2019</option>\r\n");
      out.write("<option value=\"2020\">2020</option>\r\n");
      out.write("<option value=\"2021\">2021</option>\r\n");
      out.write("<option value=\"2022\">2022</option>\r\n");
      out.write("<option value=\"2023\">2023</option>\r\n");
      out.write("<option value=\"2024\">2024</option>\r\n");
      out.write("<option value=\"2025\">2025</option>\r\n");
      out.write("<option value=\"2026\">2026</option>\r\n");
      out.write("<option value=\"2027\">2027</option>\r\n");
      out.write("<option value=\"2028\">2028</option>\r\n");
      out.write("<option value=\"2029\">2029</option>\r\n");
      out.write("<option value=\"2030\">2030</option>\r\n");
      out.write("</select>\r\n");
      out.write("<select name=\"m1\" id=\"m1\" style=\"width:40px;height:20px;\" onchange=\"drawCalendar(yy,this.value-1)\">\r\n");
      out.write("<option value=\"01\">1</option>\r\n");
      out.write("<option value=\"02\">2</option>\r\n");
      out.write("<option value=\"03\">3</option>\r\n");
      out.write("<option value=\"04\">4</option>\r\n");
      out.write("<option value=\"05\">5</option>\r\n");
      out.write("<option value=\"06\">6</option>\r\n");
      out.write("<option value=\"07\">7</option>\r\n");
      out.write("<option value=\"08\">8</option>\r\n");
      out.write("<option value=\"09\">9</option>\r\n");
      out.write("<option value=\"10\">10</option>\r\n");
      out.write("<option value=\"11\">11</option>\r\n");
      out.write("<option value=\"12\">12</option>\r\n");
      out.write("</select>\r\n");
      out.write("</td>\r\n");
      out.write("\r\n");
      out.write("<td nowrap width=16 id=next><a href=javascript:drawCalendar(yy,mm+1);><img src=../images/calendarNext.gif border=0 width=\"16\" height=\"16\"></a></td>\r\n");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<table width=100% border=0 bgcolor=white cellpadding=0 cellspacing=2>\r\n");
      out.write("<tr height=18>\r\n");
      out.write("<td nowrap><font color=red>");
      out.print(resource.srcStr("Login.day"));
      out.write("</font></td>\r\n");
      out.write("<td nowrap>");
      out.print(resource.srcStr("Main.one"));
      out.write("</td>\r\n");
      out.write("<td nowrap>");
      out.print(resource.srcStr("System.two"));
      out.write("</td>\r\n");
      out.write("<td nowrap>");
      out.print(resource.srcStr("Main.three"));
      out.write("</td>\r\n");
      out.write("<td nowrap>");
      out.print(resource.srcStr("Login.four"));
      out.write("</td>\r\n");
      out.write("<td nowrap>");
      out.print(resource.srcStr("System.five"));
      out.write("</td>\r\n");
      out.write("<td nowrap><font color=green>");
      out.print(resource.srcStr("Main.six") );
      out.write("</font></td>\r\n");
      out.write("</tr>\r\n");
      out.write("\r\n");
      out.write("<tr height=1><td colspan=7 bgcolor=gray></td></tr>\r\n");
      out.write("\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("<!--//\r\n");
      out.write("for(i=0;i<6;i++)\r\n");
      out.write("{\r\n");
      out.write("\tstr+=\"<tr height=18>\";\r\n");
      out.write("\tfor(j=1;j<=7;j++)str+=\"<td id=d\"+(i*7+j)+\" class=dt></td>\";\r\n");
      out.write("\tstr+=\"</tr>\";\r\n");
      out.write("}\r\n");
      out.write("document.write(str);\r\n");
      out.write("//-->\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<tr height=1><td colspan=7 bgcolor=gray></td></tr>\r\n");
      out.write("<tr height=18><td colspan=7><a href=\"javascript:wp.hideCalendar();\">");
      out.print(resource.srcStr("Version.close"));
      out.write("</a></td></tr>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</td></tr></table>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script language=javascript>\r\n");
      out.write("<!--//\r\n");
      out.write("var bCalLoaded=true;\r\n");
      out.write("//-->\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!--javascript 国际化标识符-->\r\n");
      out.write("<div name=\"javascriptI18n\">\r\n");
      out.write("</div>\r\n");
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
