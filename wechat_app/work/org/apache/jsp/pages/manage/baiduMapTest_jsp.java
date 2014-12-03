package org.apache.jsp.pages.manage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.weili.wechat.common.Resource;
import com.weili.wechat.common.GetResource;

public final class baiduMapTest_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<head>  \r\n");
      out.write("<meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no\" />  \r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />  \r\n");
      out.write("\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("\t\tbody, html {width: 100%;height: 100%;margin:0;font-family:\"å¾®è½¯éé»\";}\r\n");
      out.write("\t\t#l-map{height:100%;width:78%;float:left;border-right:2px solid #bcbcbc;}\r\n");
      out.write("\t\t#r-result{height:100%;width:20%;float:left;}\r\n");
      out.write("\t\t#allmap {width: 80%;height: 100%;margin:0;font-family:\"å¾®è½¯éé»\";}\r\n");
      out.write("\t\t#side {width: 20%;height: 100%;float:right;margin:0;font-family:\"å¾®è½¯éé»\";}\r\n");
      out.write("\t</style>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"http://api.map.baidu.com/api?v=2.0&ak=PIbbXDlclwkSFHLiQuo6aWCU\"></script>\r\n");
      out.write("\t<title>æ·»å å¤ä¸ªæ æ³¨ç¹</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div id=\"page\" class=\"page\">\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"side\" class=\"side\" >\r\n");
      out.write("\t<table >\r\n");
      out.write("\t <tr><td id=\"åäº¬\" onclick=\"openInfo(this)\"><font color=\"red\">åäº¬</font></td></tr>\r\n");
      out.write("\t <tr><td id=\"ä¸æµ·\" onclick=\"openInfo(this)\"><font color=\"red\">ä¸æµ·</font></td></tr>\r\n");
      out.write("\t <tr><td id=\"åäº¬\" onclick=\"openInfo(this)\" ><font color=\"red\">åäº¬</font></td></tr>\r\n");
      out.write("\t \r\n");
      out.write("\t </table>\r\n");
      out.write("\t </div>\r\n");
      out.write("\t <div id=\"allmap\"></div>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t// ç¾åº¦å°å¾APIåè½\r\n");
      out.write("\tvar map = new BMap.Map(\"allmap\");\r\n");
      out.write("\tvar point = new BMap.Point(116.404, 39.915);\r\n");
      out.write("\tmap.centerAndZoom(point, 5);\r\n");
      out.write("\tmap.enableScrollWheelZoom();\r\n");
      out.write("\tmap.addControl(new BMap.NavigationControl());  \r\n");
      out.write("\tmap.setMapStyle({\r\n");
      out.write("  \tstyleJson:[\r\n");
      out.write("          {\r\n");
      out.write("                    \"featureType\": \"land\",\r\n");
      out.write("                    \"elementType\": \"geometry\",\r\n");
      out.write("                    \"stylers\": {\r\n");
      out.write("                              \"color\": \"#212121\"\r\n");
      out.write("                    }\r\n");
      out.write("          },\r\n");
      out.write("          {\r\n");
      out.write("                    \"featureType\": \"building\",\r\n");
      out.write("                    \"elementType\": \"geometry\",\r\n");
      out.write("                    \"stylers\": {\r\n");
      out.write("                              \"color\": \"#2b2b2b\"\r\n");
      out.write("                    }\r\n");
      out.write("          },\r\n");
      out.write("          {\r\n");
      out.write("                    \"featureType\": \"highway\",\r\n");
      out.write("                    \"elementType\": \"all\",\r\n");
      out.write("                    \"stylers\": {\r\n");
      out.write("                              \"lightness\": -42,\r\n");
      out.write("                              \"saturation\": -91\r\n");
      out.write("                    }\r\n");
      out.write("          },\r\n");
      out.write("          {\r\n");
      out.write("                    \"featureType\": \"arterial\",\r\n");
      out.write("                    \"elementType\": \"geometry\",\r\n");
      out.write("                    \"stylers\": {\r\n");
      out.write("                              \"lightness\": -77,\r\n");
      out.write("                              \"saturation\": -94\r\n");
      out.write("                    }\r\n");
      out.write("          },\r\n");
      out.write("          {\r\n");
      out.write("                    \"featureType\": \"green\",\r\n");
      out.write("                    \"elementType\": \"geometry\",\r\n");
      out.write("                    \"stylers\": {\r\n");
      out.write("                              \"color\": \"#1b1b1b\"\r\n");
      out.write("                    }\r\n");
      out.write("          },\r\n");
      out.write("          {\r\n");
      out.write("                    \"featureType\": \"water\",\r\n");
      out.write("                    \"elementType\": \"geometry\",\r\n");
      out.write("                    \"stylers\": {\r\n");
      out.write("                              \"color\": \"#181818\"\r\n");
      out.write("                    }\r\n");
      out.write("          },\r\n");
      out.write("          {\r\n");
      out.write("                    \"featureType\": \"subway\",\r\n");
      out.write("                    \"elementType\": \"geometry.stroke\",\r\n");
      out.write("                    \"stylers\": {\r\n");
      out.write("                              \"color\": \"#181818\"\r\n");
      out.write("                    }\r\n");
      out.write("          },\r\n");
      out.write("          {\r\n");
      out.write("                    \"featureType\": \"railway\",\r\n");
      out.write("                    \"elementType\": \"geometry\",\r\n");
      out.write("                    \"stylers\": {\r\n");
      out.write("                              \"lightness\": -52\r\n");
      out.write("                    }\r\n");
      out.write("          },\r\n");
      out.write("          {\r\n");
      out.write("                    \"featureType\": \"all\",\r\n");
      out.write("                    \"elementType\": \"labels.text.stroke\",\r\n");
      out.write("                    \"stylers\": {\r\n");
      out.write("                              \"color\": \"#313131\"\r\n");
      out.write("                    }\r\n");
      out.write("          },\r\n");
      out.write("          {\r\n");
      out.write("                    \"featureType\": \"all\",\r\n");
      out.write("                    \"elementType\": \"labels.text.fill\",\r\n");
      out.write("                    \"stylers\": {\r\n");
      out.write("                              \"color\": \"#8b8787\"\r\n");
      out.write("                    }\r\n");
      out.write("          },\r\n");
      out.write("          {\r\n");
      out.write("                    \"featureType\": \"manmade\",\r\n");
      out.write("                    \"elementType\": \"geometry\",\r\n");
      out.write("                    \"stylers\": {\r\n");
      out.write("                              \"color\": \"#1b1b1b\"\r\n");
      out.write("                    }\r\n");
      out.write("          },\r\n");
      out.write("          {\r\n");
      out.write("                    \"featureType\": \"local\",\r\n");
      out.write("                    \"elementType\": \"geometry\",\r\n");
      out.write("                    \"stylers\": {\r\n");
      out.write("                              \"lightness\": -75,\r\n");
      out.write("                              \"saturation\": -91\r\n");
      out.write("                    }\r\n");
      out.write("          },\r\n");
      out.write("          {\r\n");
      out.write("                    \"featureType\": \"subway\",\r\n");
      out.write("                    \"elementType\": \"geometry\",\r\n");
      out.write("                    \"stylers\": {\r\n");
      out.write("                              \"lightness\": -65\r\n");
      out.write("                    }\r\n");
      out.write("          },\r\n");
      out.write("          {\r\n");
      out.write("                    \"featureType\": \"railway\",\r\n");
      out.write("                    \"elementType\": \"all\",\r\n");
      out.write("                    \"stylers\": {\r\n");
      out.write("                              \"lightness\": -40\r\n");
      out.write("                    }\r\n");
      out.write("          },\r\n");
      out.write("          {\r\n");
      out.write("                    \"featureType\": \"boundary\",\r\n");
      out.write("                    \"elementType\": \"geometry\",\r\n");
      out.write("                    \"stylers\": {\r\n");
      out.write("                              \"color\": \"#8b8787\",\r\n");
      out.write("                              \"weight\": \"1\",\r\n");
      out.write("                              \"lightness\": -29\r\n");
      out.write("                    }\r\n");
      out.write("          }\r\n");
      out.write("]\r\n");
      out.write("\t});\r\n");
      out.write("\tvar opts = {\r\n");
      out.write("\t\t\t\twidth : 250,     // ä¿¡æ¯çªå£å®½åº¦\r\n");
      out.write("\t\t\t\theight: 80,     // ä¿¡æ¯çªå£é«åº¦\r\n");
      out.write("\t\t\t\ttitle : \"ä¿¡æ¯çªå£\" , // ä¿¡æ¯çªå£æ é¢\r\n");
      out.write("\t\t\t\tenableMessage:true//è®¾ç½®åè®¸ä¿¡æ¯çªåéç­æ¯\r\n");
      out.write("\t\t\t   };\r\n");
      out.write("\t// ç¼åèªå®ä¹å½æ°,åå»ºæ æ³¨\r\n");
      out.write("\tfunction addMarker(point, message){\r\n");
      out.write("\t  var myIcon = new BMap.Icon(\"http://developer.baidu.com/map/jsdemo/img/fox.gif\", new BMap.Size(300,157));\r\n");
      out.write("\t  var marker = new BMap.Marker(point);\r\n");
      out.write("\t  //var marker = new BMap.Marker(point,{icon:myIcon}); \r\n");
      out.write("\t     marker.addEventListener('click', function (e) {\r\n");
      out.write("          //alert('åå»ç¹çåæ ä¸ºï¼' + e.point.lng + ',' + e.point.lat);  // çå¬ç¹å»äºä»¶\r\n");
      out.write("          var p = e.target;\r\n");
      out.write("\t\tvar point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);\r\n");
      out.write("          var infoWindow = new BMap.InfoWindow(message,opts);  // åå»ºä¿¡æ¯çªå£å¯¹è±¡ \r\n");
      out.write("\t\t map.openInfoWindow(infoWindow,point); //å¼å¯ä¿¡æ¯çªå£\r\n");
      out.write("        });\r\n");
      out.write("\t  map.addOverlay(marker);\r\n");
      out.write("      //marker.addEventListener(\"click\",openInfo.bind(null,content));\r\n");
      out.write("\t  //marker.setAnimation(BMAP_ANIMATION_BOUNCE);\r\n");
      out.write("\t}\r\n");
      out.write("\t// éæºåå°å¾æ·»å 25ä¸ªæ æ³¨\r\n");
      out.write("\tvar cityArray= new Array(\"åäº¬\",\"ä¸æµ·\",\"åäº¬\",\"æ é¡\",\"æ·±å³\",\"å¹¿å·\",\"æè¨\",\"ä¹é²æ¨é½\",\"å°å·\",\"æé½\",\"éåº\",\"æ­å·\",\"æ­¦æ±\",\"è¥¿å®\");\r\n");
      out.write("\t//alert(cityArray[0]);\r\n");
      out.write("\tvar citylnglat = new Array(\"116.41667-39.91667\",\"121.48-31.22\",\"118.78333-32.05000\",\"120.18-31.34\",\r\n");
      out.write("\t\t\t\t\t\t\"114.06667-22.61667\",\"113.23333-23.16667\",\"91.08-29.39\",\"87.36-43.45\",\r\n");
      out.write("\t\t\t\t\t\t\"103.73333-36.03333\",\"104.06667-30.66667\",\"106.33-29.35\",\"120.20000-30.26667\",\r\n");
      out.write("\t\t\t\t\t\t\"114.31667-30.51667\",\"108.57-34.17\");\r\n");
      out.write("\tvar cityWeight = new Array(\"1000\",\"1100\",\"700\",\"700\",\"900\",\"900\",\"300\",\"400\",\"400\",\"700\",\"800\",\"800\",\"700\",\"600\");\r\n");
      out.write("\tvar bounds = map.getBounds();\r\n");
      out.write("\tvar sw = bounds.getSouthWest();\r\n");
      out.write("\tvar ne = bounds.getNorthEast();\r\n");
      out.write("\tvar lngSpan = Math.abs(sw.lng - ne.lng);\r\n");
      out.write("\tvar latSpan = Math.abs(ne.lat - sw.lat);\r\n");
      out.write("\tfor (var i = 0; i < 14; i ++) {\r\n");
      out.write("\t\t//var point = new BMap.Point(sw.lng + lngSpan * (Math.random() * 0.7), ne.lat - latSpan * (Math.random() * 0.7));\r\n");
      out.write("\t\tvar lnglat = citylnglat[i];\r\n");
      out.write("\t\tvar lnglatsplit = lnglat.split(\"-\");\r\n");
      out.write("\t\t//alert(lnglatsplit[0]);\r\n");
      out.write("\t\tvar point = new BMap.Point(lnglatsplit[0],lnglatsplit[1]);\r\n");
      out.write("\t\tvar message = cityArray[i] +\"å¸xxxç­å¼æ¯:\" + cityWeight[i];\r\n");
      out.write("\t\taddMarker(point, message);\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction getBoundary(){       \r\n");
      out.write("\t\tvar bdary = new BMap.Boundary();\r\n");
      out.write("\t\tbdary.get(\"ä¸­å½\", function(rs){       //è·åè¡æ¿åºå\r\n");
      out.write("\t\t\tmap.clearOverlays();        //æ¸é¤å°å¾è¦çç©       \r\n");
      out.write("\t\t\tvar count = rs.boundaries.length; //è¡æ¿åºåçç¹æå¤å°ä¸ª\r\n");
      out.write("\t\t\tfor(var i = 0; i < count; i++){\r\n");
      out.write("\t\t\t\tvar ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: \"#ff0000\"}); //å»ºç«å¤è¾¹å½¢è¦çç©\r\n");
      out.write("\t\t\t\tmap.addOverlay(ply);  //æ·»å è¦çç©\r\n");
      out.write("\t\t\t\tmap.setViewport(ply.getPath());    //è°æ´è§é         \r\n");
      out.write("\t\t\t}                \r\n");
      out.write("\t\t});   \r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t//setTimeout(function(){\r\n");
      out.write("\t//\tgetBoundary();\r\n");
      out.write("\t//}, 2000);\r\n");
      out.write("\t\tfunction openInfo(obj){\r\n");
      out.write("\t\tvar id = obj.id;\r\n");
      out.write("\t\t//alert(id);\r\n");
      out.write("\t\tvar index = getindex(cityArray, id);\r\n");
      out.write("\t\t//alert(index);\r\n");
      out.write("\t\tvar lnglatsplit = citylnglat[index].split(\"-\");\r\n");
      out.write("\t\tvar point = new BMap.Point(lnglatsplit[0], lnglatsplit[1]);\r\n");
      out.write("\t\tvar message = cityArray[index] +\"å¸xxxç­å¼æ¯:\" + cityWeight[index];\r\n");
      out.write("\t\tvar infoWindow = new BMap.InfoWindow(message,opts);  // å»ºä¿¡æ¯çªå£å¯¹è±¡ \r\n");
      out.write("\t\tmap.openInfoWindow(infoWindow,point); //å¼å¯ä¿¡æ¯çªå£\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction getindex(cityArray, id){\r\n");
      out.write("\t\tfor (var i = 0; i < cityArray.length; i++){\r\n");
      out.write("\t\r\n");
      out.write("\t  \t\tif (cityArray[i] == id){\r\n");
      out.write("\t  \t\t\treturn i;\r\n");
      out.write("\t  \t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn -1;\r\n");
      out.write("\t}\r\n");
      out.write("</script>");
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
