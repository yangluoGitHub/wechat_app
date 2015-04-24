<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page language="java"%>
<%@ page contentType="text/html; charset=gbk"%>
<%@ page isErrorPage="true" %>

<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>

<style type=text/css>
td{font-size:12;font-family:arial;text-align:center}
td.dt{font-size:11;font-family:arial;text-align:center}
a{color:blue}
a:hover{color:red}
a.bt{color:#888888}
</style>
<script language=javascript>
<!--//
var str='',i,j,yy,mm,openbound,callback;
var fld1,fld2;
var wp=window.parent;
var cf=wp.document.getElementById("CalFrame");
var fld,curday,today=new Date();
today.setHours(0);today.setMinutes(0);today.setSeconds(0);today.setMilliseconds(0);
//var lastyear=today.getYear(),lastmonth=today.getMonth();
function parseDate(s)
{
	var reg=new RegExp("[^0-9-]","")
	if(s.search(reg)>=0)return today;
	var ss=s.split("-");
	if(ss.length!=3)return today;
	if(isNaN(ss[0])||isNaN(ss[1])||isNaN(ss[2]))return today;
	return new Date(parseFloat(ss[0]),parseFloat(ss[1])-1,parseFloat(ss[2]));
}
function resizeCalendar(){cf.width=180;cf.height=197;}
function initCalendar()
{
	if(fld1&&fld1.value.length>0){curday=parseDate(fld1.value);}
	else if(fld2&&fld2.value.length>0){curday=parseDate(fld2.value);}
	else curday=today;
	drawCalendar(curday.getFullYear(),curday.getMonth());
}
function drawCalendar(y,m)
{
	var x=new Date(y,m,1),mv=x.getDay(),d=x.getDate(),de;
	yy=x.getFullYear();mm=x.getMonth();
        var y1 = document.getElementById("y1");
        var m1 = document.getElementById("m1");
        y1.value=yy;
        m1.value=mm+1>9?mm+1:"0"+(mm+1)
	for(var i=1;i<=mv;i++)
	{
		de=document.getElementById("d"+i);
		de.innerHTML="";
		de.bgColor="";
	}
	while(x.getMonth()==mm)
	{
		de=document.getElementById("d"+(d+mv));
		if(x.getTime()==curday.getTime())
			de.bgColor="#dddddd";
		else
			de.bgColor="white";
		if(x.getTime()==today.getTime())
			de.innerHTML="<a href=javascript:setDate("+d+");><font color=red>"+d+"</font></a>";
		else if(x.getTime()<today.getTime())
			if(openbound){de.innerHTML="<a href=javascript:setDate("+d+"); class=bt>"+d+"</a>";}
			else{de.innerHTML="<font color=#888888>"+d+"</font>";}
		else
			de.innerHTML="<a href=javascript:setDate("+d+");>"+d+"</a>";
		x.setDate(++d);
	}
	while(d+mv<=42)
	{
		de=document.getElementById("d"+(d+mv));
		de.innerHTML="";
		de.bgColor="";
		d++;
	}
}
function setDate(d)
{
	wp.hideCalendar();
        var tmp1 = mm+1;
        var tmp2 = d;
        if(tmp1<10){
          tmp1 = "0"+tmp1;
        }
        if(tmp2<10){
          tmp2 = "0"+tmp2;
        }
	var dstr=yy+"-"+tmp1+"-"+tmp2;
	if(callback&&callback.length>0){eval("wp."+callback+"(\""+dstr+"\")");}
	else{fld1.value=dstr;fld1.select();}
}
//-->
</script>

</head>

<body topmargin=0 leftmargin=0 bottommargin=0 rightmargin=0 bgcolor=white onload="resizeCalendar();">

<table width=180 id=tbl0 bgcolor=#336699 border=0 cellpadding=1 cellspacing=0>
<tr><td nowrap>

<table width=100% border=0 cellpadding=2 cellspacing=1 bgcolor=white>
<tr bgcolor=#bbbbbb>
<td nowrap width=16 id=prev><a href=javascript:drawCalendar(yy,mm-1);><img src=../images/calendarPrev.gif border=0 width="16" height="16"></a></td>
<td nowrap>
<select name="y1" id="y1" style="width:55px;height:20px;" onchange="drawCalendar(this.value,mm)">
<option value="1990">1990</option>
<option value="1991">1991</option>
<option value="1992">1992</option>
<option value="1993">1993</option>
<option value="1994">1994</option>
<option value="1995">1995</option>
<option value="1996">1996</option>
<option value="1997">1997</option>
<option value="1998">1998</option>
<option value="1999">1999</option>
<option value="2000">2000</option>
<option value="2001">2001</option>
<option value="2002">2002</option>
<option value="2003">2003</option>
<option value="2004">2004</option>
<option value="2005">2005</option>
<option value="2006">2006</option>
<option value="2007">2007</option>
<option value="2008">2008</option>
<option value="2009">2009</option>
<option value="2010">2010</option>
<option value="2011">2011</option>
<option value="2012">2012</option>
<option value="2013">2013</option>
<option value="2014">2014</option>
<option value="2015">2015</option>
<option value="2016">2016</option>
<option value="2017">2017</option>
<option value="2018">2018</option>
<option value="2019">2019</option>
<option value="2020">2020</option>
<option value="2021">2021</option>
<option value="2022">2022</option>
<option value="2023">2023</option>
<option value="2024">2024</option>
<option value="2025">2025</option>
<option value="2026">2026</option>
<option value="2027">2027</option>
<option value="2028">2028</option>
<option value="2029">2029</option>
<option value="2030">2030</option>
</select>
<select name="m1" id="m1" style="width:40px;height:20px;" onchange="drawCalendar(yy,this.value-1)">
<option value="01">1</option>
<option value="02">2</option>
<option value="03">3</option>
<option value="04">4</option>
<option value="05">5</option>
<option value="06">6</option>
<option value="07">7</option>
<option value="08">8</option>
<option value="09">9</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
</select>
</td>

<td nowrap width=16 id=next><a href=javascript:drawCalendar(yy,mm+1);><img src=../images/calendarNext.gif border=0 width="16" height="16"></a></td>
</tr>
</table>


<table width=100% border=0 bgcolor=white cellpadding=0 cellspacing=2>
<tr height=18>
<td nowrap><font color=red><%=resource.srcStr("Login.day")%></font></td>
<td nowrap><%=resource.srcStr("Main.one")%></td>
<td nowrap><%=resource.srcStr("System.two")%></td>
<td nowrap><%=resource.srcStr("Main.three")%></td>
<td nowrap><%=resource.srcStr("Login.four")%></td>
<td nowrap><%=resource.srcStr("System.five")%></td>
<td nowrap><font color=green><%=resource.srcStr("Main.six") %></font></td>
</tr>

<tr height=1><td colspan=7 bgcolor=gray></td></tr>

<script language="javascript">
<!--//
for(i=0;i<6;i++)
{
	str+="<tr height=18>";
	for(j=1;j<=7;j++)str+="<td id=d"+(i*7+j)+" class=dt></td>";
	str+="</tr>";
}
document.write(str);
//-->
</script>


<tr height=1><td colspan=7 bgcolor=gray></td></tr>
<tr height=18><td colspan=7><a href="javascript:wp.hideCalendar();"><%=resource.srcStr("Version.close")%></a></td></tr>
</table>


</td></tr></table>


<script language=javascript>
<!--//
var bCalLoaded=true;
//-->
</script>
</body>
</html>






<!--javascript 国际化标识符-->
<div name="javascriptI18n">
</div>
