var adsCount=1;
function insertAdsDiv()
{
	var adsDiv = document.getElementById("adsDiv");
	var tempDiv = "";
	var type = document.getElementById("adsType").value;
	if(type=="2" || type=="3")
	{
		tempDiv = getDivText2(type);
	}
	else
	{
		tempDiv = getDivText1();
	}
	adsDiv.innerHTML = tempDiv;
	/*初始化操作*/
	var preview = document.getElementById("preview");
	preview.innerHTML = "";
	insertTimeRow();
	if(type=="2" || type=="3")
	{
		//announcePreviewShow();
		return;
	}
	document.getElementById("showTimeRow").style.display="";
}

function insertTimeRow()
{
	var timeTB = document.getElementById("timeTB");
	if(timeTB.rows.length>=3)
	{
		alert(document.getElementById('script.singlefileads.limitPrompt').value);
		return ;
	}
	R = timeTB.insertRow();
	td = R.insertCell(); 
	td.className = "tdi";
	var tempRow = "";
	tempRow += document.getElementById('Main.begin_time').value+"：<select name='startHour'>";
	for(i=0;i<24;i++)
	{
		tempRow += "<option value='"+left(i)+"'>"+left(i);
	}
	tempRow += "</select> "+document.getElementById('Main.hour').value+"<select name='startMin'>";
	for(i=0;i<60;i++)
	{
		tempRow += "<option value='"+left(i)+"'>"+left(i);
	}
	tempRow += "</select> "+document.getElementById('Main.min').value+"<select name='startSec'>";
	for(i=0;i<60;i++)
	{
		tempRow += "<option value='"+left(i)+"'>"+left(i);
	}
	tempRow += "</select> "+document.getElementById('Main.sec').value;
	
	tempRow += document.getElementById('Main.end_time').value+"：<select name='endHour'>";
	for(i=0;i<23;i++)
	{
		tempRow += "<option value='"+left(i)+"'>"+left(i);
	}
	tempRow += "<option value='23' selected>23</select> "+document.getElementById('Main.hour').value;
	tempRow += "<select name='endMin'>";
	for(i=0;i<59;i++)
	{
		tempRow += "<option value='"+left(i)+"'>"+left(i);
	}
	tempRow += "<option value='59' selected>59</select> "+document.getElementById('Main.min').value;
	tempRow += "<select name='endSec'>";
	for(i=0;i<59;i++)
	{
		tempRow += "<option value='"+left(i)+"'>"+left(i);
	}
	tempRow += "<option value='59' selected>59</select> "+document.getElementById('Main.sec').value;
	tempRow += "<input class='btn' type='button' value='"+document.getElementById('script.singlefileads.delTime').value+"' onclick='delTimeRow(this.parentElement.parentElement.rowIndex)'>&nbsp";
	tempRow += "<input class='btn' type='button' value='"+document.getElementById('script.singlefileads.addTime').value+"' onclick='insertTimeRow()'>";
	td.innerHTML = tempRow;
}
function delTimeRow(obj)
{
	var timeTB = document.getElementById("timeTB");
	if(timeTB.rows.length==1)
	{
		alert(document.getElementById('script.singlefileads.cannotDelAll').value);
		return ;
	}
	timeTB.deleteRow(obj);
}
function insertFileRow()
{
	var mutiFile = document.getElementById("mutiFile");
	if(adsCount>=3)
	{
		alert("最多只能3个附件");
		return ;
	}
	adsCount = adsCount + 1;
	R = mutiFile.insertRow();
	td = R.insertCell(); 
	td.className = "tdi";
	var tempRow = "";
	tempRow += "<input type='file' name='adsFile"+adsCount+"' style='width:400px;' contentEditable='false' onchange='checkFile(this);'>";
	td.innerHTML = tempRow;
}
function delFileRow()
{
	var mutiFile = document.getElementById("mutiFile");
	if(mutiFile.rows.length<=1)
	{
		alert("至少要保留一个附件");
		return ;
	}
	var obj = document.getElementById('adsFile'+adsCount);
	adsCount = adsCount -1;
	mutiFile.deleteRow(obj.parentElement.parentElement.rowIndex);
}
var test=0;
function checkFile(obj)
{
	//stopPlayeVideo();
	//var preview = document.getElementById("preview");
	//preview.innerHTML = "";
	fileUrl = obj.value;
	if(fileUrl==null||fileUrl=="")
	{
		return;
	}
	fileType = fileUrl.substring(fileUrl.lastIndexOf("."),fileUrl.length);
	fileType = fileType.toLowerCase();
	if(fileType!=".jpg")
	{
		alert(document.getElementById('script.singlefileads.selectRight').value);
		//obj.select();
   		//document.execCommand('delete');
   		obj.parentElement.innerHTML+="";
		return;
	}
	//previewInit();
}

function getDivText1()
{
	var tempDiv = "";
	tempDiv += "<table width='100%' border='0' cellspacing='1' cellpadding='3' class='table1'>";
	
	var adsType = document.getElementById("adsType");
	var type = adsType.value;
	
	//var title =  (type=="0")?document.getElementById('script.singlefileads.addFullAds').value:document.getElementById('script.singlefileads.addtranceAds').value;
	
	//tempDiv += "<tr class='tr1'>";
	//tempDiv += "	<td colspan='2'>"+title+"</td>";
	//tempDiv += "</tr>";
	tempDiv += "<tr class=tr1>";
	tempDiv += "	<td class='td1' width='15%' align='left' nowrap>";
	tempDiv += "	&nbsp;"+document.getElementById('script.singlefileads.selectMedia').value+":";
	tempDiv += "	</td>";
	tempDiv += "	<td class='td2' nowrap><table id='mutiFile' width='100%' cellspacing='2' cellpadding='1'><tr><td>";
	tempDiv += "	<input type='file' name='adsFile1' style='width:400px;' contentEditable='false' onchange='checkFile(this);'>&nbsp;<input class='btn' type='button' name='' style='width:80px;' value='添加附件' onclick='insertFileRow()'>&nbsp;<input class='btn' type='button' name='' style='width:80px;' onclick='delFileRow()' value='删除附件'>";
	tempDiv += "	</td></tr></table></td>";
	tempDiv += "</tr>";
	
	tempDiv += getShareText();
	tempDiv += "</table>";	
	return tempDiv;
}

function getDivText2(type)
{
	var tempDiv = "";
	tempDiv += "<table width='100%' border='0' cellspacing='1' cellpadding='3' class='table1'>";
		
	tempDiv += "<tr class='tr1'>";
	if(type == 2)
		tempDiv += "	<td colspan='2'>"+document.getElementById('Version.notice_text').value+"</td>";
	else
		tempDiv += "	<td colspan='2'>"+document.getElementById('Version.announcement').value+"</td>";
		
	tempDiv += "</tr>";

	tempDiv += "<tr>";
	if(type == 2)
		tempDiv += "	<td class='td1'>&nbsp;"+document.getElementById('script.txtContent').value+"</td>";
	else
		tempDiv += "	<td class='td1'>&nbsp;"+document.getElementById('script.announcement').value+"</td>";		
	tempDiv += "	<td class='td2'>";
	tempDiv += "		<textarea name='announceText' rows='2' cols='100' id='announceText' onfocus='initAnnounceText(this)'>这里填写文字通告内容(200个字符以内...)</textarea>";
	tempDiv += "	</td>";
	tempDiv += "</tr>";
	
	tempDiv += getShareText();
	tempDiv += "</table>";	
	
	return tempDiv;
}

function getShareText()
{
	var tempDiv = "";

	tempDiv += "<tr class=tr1>";
	tempDiv += "<td class='td1' width='15%' align='left' nowrap>";
	tempDiv += "		&nbsp;"+document.getElementById('script.singlefileads.adsTime').value+":";
	tempDiv += "</td>";
	tempDiv += "<td class='td2' nowrap>";
	tempDiv += "<div id='everDiv' style='float:left'>";
	tempDiv += "<input type='text' style='width:90px;' class='pane' disabled><input type='hidden' name='startDate' id='startDate'>";
	tempDiv += "<img align='absmiddle' src='../images/calendar.gif' border='0' disabled>&nbsp;&nbsp;";
	tempDiv += "<input type='text' style='width:90px;' class='pane' disabled><input type='hidden' name='endDate' id='endDate'>";
	tempDiv += "<img align='absmiddle' src='../images/calendar.gif' border='0' disabled>";
	tempDiv += "</div>&nbsp;<input type='checkbox' id='forever' checked onclick='timeForever()'>"+document.getElementById('script.ads.playForever').value;
	tempDiv += "</td>";
	tempDiv += "</tr>";
	
	tempDiv += "<tr  class='tr1' id='showTimeRow' style='display:none'>";
	tempDiv += "	<td class='td1' width='15%' align='left' nowrap>";
	tempDiv += "		&nbsp;"+document.getElementById('script.singlefileads.adsDuration').value+":";
	tempDiv += "	</td>";
	tempDiv += "	<td class='td2' nowrap>";
	tempDiv += "		<input type='text' class='input1' name='showTime' id='showtime' value='10' 	maxlength='2' onKeyUp='numValid(this)'>"+document.getElementById('Main.sec').value;
	tempDiv += "		&nbsp;&nbsp;<span id='tips'>"+document.getElementById('script.singlefileads.DurationLimit').value+"</span>";
	tempDiv += "	</td>";
	tempDiv += "</tr>";
	
	tempDiv += "<tr  class=tr1>";
	tempDiv += "	<td class='td1' width='15%' align='left' nowrap>";
	tempDiv += "		&nbsp;"+document.getElementById('script.singlefileads.timeZone').value+":";
	tempDiv += "	</td>";
	tempDiv += "	<td class='td2' nowrap>";
	tempDiv += "		<table id='timeTB' width='100%' cellspacing='2' cellpadding='1'>";
	tempDiv += "		</table>";
	tempDiv += "	</td>";
	tempDiv += "</tr>";
	
	return tempDiv;
}
/*预览div内容*/
var fileUrl = "";
var fileType = "";
function announcePreviewShow()
{
	var preview = document.getElementById("preview");
	preview.innerHTML = "";
	
	var tempDiv = "";
	tempDiv += "<div class='div2'>"+document.getElementById('script.singlefileads.txtPreview').value+"</div>";
	tempDiv += "<div id='media'></div>";
	preview.innerHTML = tempDiv;
	
	var media = document.getElementById("media");
	var showDivText = "";
	showDivText += "<UL id='text'>";
	showDivText += "	<MARQUEE id='mrq' scrollDelay='80'>";
	showDivText += "	<LI  class='li'>"+document.getElementById('script.singlefileads.txtExample').value+"</LI>";
	showDivText += "	</MARQUEE>";
	showDivText += "</UL>";
	media.innerHTML = showDivText;
}

function previewInit()
{
	var preview = document.getElementById("preview");

	var tempDiv = "";
	if(fileType==".jpg")
	{
		tempDiv += "<div class='div2'><a href='#' onclick='previewShow()'>"+document.getElementById('script.singlefileads.clickPreview').value+"</a>("+document.getElementById('script.singlefileads.clickPath').value+fileUrl+")</div>";
		tempDiv += "<div id='media'  style='display:none' class='media'></div>";
	}
	else
	{
		tempDiv += "<div class='div2'><a href='#' onclick='previewShow()'>"+document.getElementById('script.singlefileads.clickVideoPreview').value+"</a>("+document.getElementById('script.singlefileads.videoFilePath').value+fileUrl+")</div>";
		tempDiv += "<div id='media' style='display:none'></div>";
	}
	preview.innerHTML = tempDiv;
	return;
}

function previewShow()
{

	var media = document.getElementById("media");
	if(media.style.display=="")
	{
		media.style.display = "none";
		stopPlayeVideo();
		media.innerHTML = "";
		return;
	}
	media.style.display = "";
	if(fileType==".jpg")
	{
		try
		{
			media.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = fileUrl;
		}
		catch(e)
		{
			media.innerHTML = "<font color='red'>"+document.getElementById('script.singlefileads.previewPic').value+"</b>";
		}
		return;
	}
	var showDivText = "";
	showDivText += "<OBJECT id='player' name='player' height='450' width='600' classid=clsid:6BF52A52-394A-11D3-B153-00C04F79FAA6>";
	showDivText += "<PARAM NAME='URL' VALUE=''>";
	showDivText += "<PARAM NAME='rate' VALUE='1'>";
	showDivText += "<PARAM NAME='balance' VALUE='0'>";
	showDivText += "<PARAM NAME='currentPosition' VALUE='0'>";
	showDivText += "<PARAM NAME='defaultFrame' VALUE=''>";
	showDivText += "<PARAM NAME='playCount' VALUE='1'>";
	showDivText += "<PARAM NAME='autoStart' VALUE='1'>";
	showDivText += "<PARAM NAME='currentMarker' VALUE='0'>";
	showDivText += "<PARAM NAME='invokeURLs' VALUE='-1'>";
	showDivText += "<PARAM NAME='baseURL' VALUE=''>";
	showDivText += "<PARAM NAME='volume' VALUE='60'>";
	showDivText += "<PARAM NAME='mute' VALUE='0'>";
	showDivText += "<PARAM NAME='uiMode' VALUE='full'>";
	showDivText += "<PARAM NAME='stretchToFit' VALUE='1'>";
	showDivText += "<PARAM NAME='windowlessVideo' VALUE='0'>";
	showDivText += "<PARAM NAME='enabled' VALUE='-1'>";
	showDivText += "<PARAM NAME='enableContextMenu' VALUE='-1'>";
	showDivText += "<PARAM NAME='fullScreen' VALUE='0'>";
	showDivText += "<PARAM NAME='SAMIStyle' VALUE=''>";
	showDivText += "<PARAM NAME='SAMILang' VALUE=''>";
	showDivText += "<PARAM NAME='SAMIFilename' VALUE=''>";
	showDivText += "<PARAM NAME='captioningID' VALUE=''>";
	showDivText += "<PARAM NAME='enableErrorDialogs' VALUE='0'>";
	showDivText += "<PARAM NAME='_cx' VALUE='12700'>";
	showDivText += "<PARAM NAME='_cy' VALUE='9525'>";
	showDivText += "</OBJECT>";
	media.innerHTML = showDivText;
	document.getElementById('player').url = fileUrl;
	return;

}

function renewFileDiv()
{
	var fileDiv = document.getElementById("fileDiv");
	fileDiv.innerHTML = "<input type='file' name='adsFile' style='width:400px;' contentEditable='false' onchange='checkFile(this);' > ("+document.getElementById('script.singlefileads.jpgaviSupport').value+")";	
}

function refreshAnnounce(obj)
{
	var mrq = document.getElementById("mrq");
	mrq.innerHTML = "<LI class='li'>"+obj.value+"</LI>";	
}

function left(i)
{
	return i<10?"0"+i:i;
}

function getNewDate(type)
{
	var date = new Date();
	var year = date.getYear();
	var month = date.getMonth()+1;
	month = month>9?month:("0"+month);
	var day = date.getDate();
	day = day>9?day:("0"+day);
	if(type=="yyyy-MM-dd")
	{
		return year+"-"+month+"-"+day;
	} 
	return year+""+month+""+day;
}
/*2个全局变量，用于记录时间段的值*/
var startTimeZone="";
var endTimeZone="";

function timeValid()
{
	/*判断广告有效期是否合法*/
	var timeForever = document.getElementById("forever");
	var startDate = document.getElementById("startDate");
	var endDate = document.getElementById("endDate");
	if(!timeForever.checked)
	{
		if(startDate.value==null||startDate.value=="")
		{
			alert(document.getElementById('script.singlefileads.startTimeSelect').value);
			return false;
		}
		if(getNewDate("yyyy-MM-dd")>startDate.value)
		{
			alert(document.getElementById('script.singlefileads.startTimeError').value);
			startDate.select();
			return false;
		}
		if(endDate.value==null||endDate.value=="")
		{
			alert(document.getElementById('script.singlefileads.endTimeSelect').value);
			return false;
		}
		if(startDate.value>endDate.value)
		{
			alert(document.getElementById('script.singlefileads.endTimeError').value);
			endDate.select();
			return false;
		}
	}
	else
	{
		startDate.value="0000-01-01";
		endDate.value="9999-12-31";
	}
	
	
	/*开始时间*/
	var startHours = document.getElementsByName("startHour");
	var startMins = document.getElementsByName("startMin");
	var startSecs = document.getElementsByName("startSec");
	/*结束时间*/
	var endHours = document.getElementsByName("endHour");
	var endMins = document.getElementsByName("endMin");
	var endSecs = document.getElementsByName("endSec");
	/*判断时间的合法性*/
	var length = startHours.length;
	var startTimeArray = new Array();
	var endTimeArray = new Array();
	for(i=0;i<length;i++)
	{
		var startTime = startHours[i].value+""+startMins[i].value+""+startSecs[i].value;
		var endTime = endHours[i].value+""+endMins[i].value+""+endSecs[i].value;
		startTimeArray[i] = startTime;
		endTimeArray[i] = endTime;
		if(endTime<=startTime)
		{
			alert(document.getElementById('Main.the').value+(i+1)+document.getElementById('script.singlefileads.startEndTimeError').value);
			return false;
		}
	}
	
	for(i=0;i<length;i++)
	{
		var srcStartTime = startTimeArray[i];
		var srcEndTime = endTimeArray[i];
		for(j=i+1;j<length;j++)
		{
			var deskStartTime = startTimeArray[j];
			var deskEndTime = endTimeArray[j];
			if(deskEndTime<=srcStartTime||deskStartTime>=srcEndTime)
			{
				continue;
			}
			alert(document.getElementById('script.singlefileads.duration').value+(i+1)+document.getElementById('script.singlefileads.andDuration').value+(j+1)+document.getElementById('script.singlefileads.durationError').value);
			return false;
		}
	}

	for(i=0;i<length;i++)
	{
		startTimeZone += startTimeArray[i]+"|";
		endTimeZone += endTimeArray[i]+"|";
	}
	return true;
}
function numValid(obj)
{
	var num = obj.value;
	var tips = document.getElementById("tips");
	if(num=="")
	{
		tips.innerHTML="<font color='red'>"+document.getElementById('script.singlefileads.durationEmpty').value+"</font>";
		return;
	}
	if(!num.isDigit()||num>60||num==0)
	{
		tips.innerHTML="<font color='red'>"+document.getElementById('script.singlefileads.durationErrorPrompt').value+"["+num+"]"+document.getElementById('script.singlefileads.wrong').value+"</font>";
		obj.select();
		obj.focus();
		return;
	}
	tips.innerHTML=document.getElementById('script.singlefileads.durationPrompt').value+num+document.getElementById('Main.sec').value;
}
function replaceAll(tObj,tStr)
{
	var regS = new RegExp(tStr,"gi");
	return	tObj.replace(regS,"");
}
function initAnnounceText(obj)
{
	if(obj.value==obj.defaultValue)
	{
		obj.value="";
	}
}
function stopPlayeVideo()
{
	var player = document.getElementById("player");
	if(player)
	{
		player.controls.stop();//停止播放，但是画面不消失
		player.url="";//将播放器的url置为空后,问题解决
	}
}
function timeForever()
{
	var obj = document.getElementById("forever");
	var everDiv = document.getElementById("everDiv");
	if(!obj.checked)
	{
		var tempDiv = "<input type='text' style='width:90px;' class='pane' name='startDate' id='startDate' readonly>";
	  	tempDiv += "<a onclick='event.cancelBubble=true;' href=\"javascript:showCalendar('dimg1',true,'startDate')\">";
		tempDiv += "<img name='showCalendar' id='dimg1' align='absmiddle' src='../images/calendar.gif' border='0' disabled></a>&nbsp;&nbsp;";
		tempDiv += "<input type='text' style='width:90px;' class='pane' name='endDate' id='endDate' readonly>";
	  	tempDiv += "<a onclick='event.cancelBubble=true;' href=\"javascript:showCalendar('dimg2',true,'endDate')\">";
		tempDiv += "<img name='showCalendar' id='dimg2' align='absmiddle' src='../images/calendar.gif' border='0' disabled></a>";
		everDiv.innerHTML = tempDiv;
		return;
	}
	var tempDiv = "<input type='text' style='width:90px;' class='pane' disabled><input type='hidden' name='startDate' id='startDate'>";
	tempDiv += "<img align='absmiddle' src='../images/calendar.gif' border='0' disabled>&nbsp;&nbsp;";
	tempDiv += "<input type='text' style='width:90px;' class='pane' disabled><input type='hidden' name='endDate' id='endDate'>";
	tempDiv += "<img align='absmiddle' src='../images/calendar.gif' border='0' disabled>";
	everDiv.innerHTML = tempDiv;
	return;
}
/*** 检查是否由数字组成 ***/
String.prototype.isDigit = function() 
{
	return (this=="")?false:(this.replace(/\d/g, "").length == 0);
}