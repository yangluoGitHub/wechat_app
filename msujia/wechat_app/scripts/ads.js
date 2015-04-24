/**
* 处理与广告相关的动态效果操作
* @auther hjtang
* @since 2007-07-12
*/
var adsSum=0; 
var delAdsSum=0;
var timeSumArr = new Array();
var fileSumArr = new Array();
var delTimeSumArr = new Array();
var delFileSumArr = new Array();
var imageArr = new Array();
timeSumArr[0]=1;
fileSumArr[0]=0;
delTimeSumArr[0]=0;
delFileSumArr[0]=0;

var tempAdsNo=0;

/**
*添加一种新类型的广告
*/
function addAds()
{
	var lastAdsSum=adsSum;
	adsSum++;
	
	if($('adsType').value=="")return false;
	
	//如果是通告
	if(parseInt($('adsType').value)==2)
	{
		new Insertion.After('announceSetAds'+lastAdsSum, addAdsAnnounceDiv());
		timeSumArr[lastAdsSum]=1;
		new Insertion.Bottom('ads'+adsSum+'TimeZone', addAdsTimeDiv()); 
		setAdsTimeSelectOptions(adsSum,1);
		//setAdsDateSelectOptions(adsSum,1)
		$('adsTotal').value=adsSum;		
		return true;
	}
	
	
	//如果是全屏广告或者交易广告
	fileSumArr[lastAdsSum]=0;
	delFileSumArr[lastAdsSum]=0;
	timeSumArr[lastAdsSum]=1;
	delTimeSumArr[lastAdsSum]=0;
	
	new Insertion.After('fileSetAds'+lastAdsSum, addAdsFileDiv());  
	new Insertion.Bottom('ads'+adsSum+'TimeZone', addAdsTimeDiv());  
	addAdsFileItems('fileTable'+adsSum,adsSum);
	setAdsTimeSelectOptions(adsSum,1);
	//setAdsDateSelectOptions(adsSum,1);
	$('adsTotal').value=adsSum;
	//alert($('fileSetAds'+adsSum).innerHTML);
	return true;
}

/**
*页面初始化，将一些变量初始化
*/
function init()
{
	timeSumArr.length=0;
	fileSumArr.length=0;
	delTimeSumArr.length=0;
	delFileSumArr.length=0;
	imageArr.length=0;
	adsSum=0;
	delAdsSum=0;
	
	$('fileSet').innerHTML="&nbsp;";
	$('announceSet').innerHTML="&nbsp;";
	//$('timeSet').innerHTML="&nbsp;";
}

/**
*设定广告类型，生成不同的操作界面
*/
function setAdsType(value)
{	
	init();
	if(value=="")
	{
		$("addNewAdsBnt").style.display='none';
		$("submitBnt").style.display='none';
	}
	else 
	{
		$("addNewAdsBnt").style.display='';
		$("submitBnt").style.display='';
		
	}
	
	if(parseInt(value)==2)
	{//是通告
		$("adsFile").style.display='none';
		$("adsAnnounce").style.display='';
		//$("adsComment").style.display='';
		
		//初始化通告1
		adsSum++;
		
		timeSumArr[0]=1;
		delTimeSumArr[0]=0;
		
		new Insertion.Bottom('announceSet', addAdsAnnounceDiv());
		new Insertion.Bottom('ads'+adsSum+'TimeZone', addAdsTimeDiv());
		setAdsTimeSelectOptions(adsSum,1)
		//setAdsDateSelectOptions(adsSum,1);
		$('adsTotal').value=adsSum;
		return true;
	}
	else if(parseInt(value)==1||parseInt(value)==0)
	{
		$("adsFile").style.display='';
		$("adsAnnounce").style.display='none';
		//$("adsComment").style.display='';
		//fresh=0;
		//初始化广告1
		adsSum++;
		
		fileSumArr[0]=0;
		delFileSumArr[0]=0;
		timeSumArr[0]=1;
		delTimeSumArr[0]=0;
		
		new Insertion.Bottom('fileSet', addAdsFileDiv());  
		new Insertion.Bottom('ads'+adsSum+'TimeZone', addAdsTimeDiv());
		var tempStr='fileTable'+adsSum;
		addAdsFileItems(tempStr,adsSum);
		setAdsTimeSelectOptions(adsSum,1)
		
		//setAdsDateSelectOptions(adsSum,1)
		$('adsTotal').value=adsSum;
		//alert($('fileSetAds'+adsSum).innerHTML);
	}
	else
	{
		$("adsFile").style.display='none';
		$("adsAnnounce").style.display='none';
		$("adsType").value='';
		$("pushType").value='0';
		$('submitBnt').style.display='none';
	}
}
window.onload = function (){
	
//	init();
}

/**
*增加广告文件层，采用动态生成DIV层字符串，插入到FORM中，用于生成媒介（图片、视频）广告层
*/
function addAdsFileDiv()
{
	var divTempStr = "";
	divTempStr += "<div id=fileSetAds"+adsSum+">";
	divTempStr += "	  <table width=100%  cellSpacing=0 cellPadding=0>";
	if(adsSum>1)
   {divTempStr += "		<tr><td colspan=4 class=tableTdSolitLine>&nbsp; </td></tr>";}
	divTempStr += "		<tr class=tr1>";
	divTempStr += "		    <td ><b>"+document.getElementById('Version.time_adv').value+adsSum+document.getElementById('script.ads.fileSelection').value+document.getElementById('script.fileAtMost1').value+picNum+document.getElementById('script.fileAtMost2').value+":</b></td>";
	divTempStr += "			<td >";
	divTempStr += "				<input type=hidden name=fileItemNum"+adsSum+" id=fileItemNum"+adsSum+">";
	divTempStr += "				<input class=button type=button name=addFile"+adsSum+" id=addFile"+adsSum+" title="+document.getElementById('script.ads.addFile').value+"&#10"+document.getElementById('script.ads.play').value+" value="+document.getElementById('script.ads.addAccessory').value+" onClick=\"addAdsFileItems('fileTable"+adsSum+"',"+adsSum+");\">";
	divTempStr += "				<input class=button style=display:none type=button name=delAds"+adsSum+"Bnt id=delAds"+adsSum+"Bnt title="+document.getElementById('script.ads.delThis').value+" value="+document.getElementById('Version.delete_adv').value+" onClick=\"delAds("+adsSum+")\">";
	divTempStr += "				<input class=button style=display:none type=button name=previewAds"+adsSum+" id=previewAds"+adsSum+" value="+"\""+document.getElementById('script.ads.preview').value+"\""+" title="+document.getElementById('script.ads.simpleEffect').value+" onClick=\"previewOperate("+adsSum+");\">";
	divTempStr += "			</td>";
	divTempStr += "		</tr>";
	divTempStr += "		<tr class=tr2>";
	divTempStr += "			<td colspan=2 class=td2>";
	divTempStr += "			</td>";
	divTempStr += "		</tr>";
	divTempStr += "		<tr>";
	divTempStr += "		<td colspan=2 class=td12>";
	divTempStr += "		  <div><table width=100% id=fileTable"+adsSum+"  cellSpacing=0 cellPadding=0></table></div>";
	divTempStr += "		</td>";
	divTempStr += "		</tr>";
	divTempStr += "		<tr>";
	divTempStr += "			<td colspan=2>";
	divTempStr += "				<div id=ads"+adsSum+"TimeZone>&nbsp;</div>";
	divTempStr += "			</td>";
	divTempStr += "		</tr>";
	divTempStr += "	  </table>";
	divTempStr += "</div>";
	return divTempStr;
}

/**
*增加时间播放层，用于控制广告的不同播放时间段
*/
function addAdsTimeDiv()
{
	var divTempStr = "";
	divTempStr +="<div id=timeSetAds"+adsSum+" width=100% >";
	divTempStr +="	 <table width=100%  cellSpacing=0 cellPadding=0>";
	divTempStr +="		<tr>";
	divTempStr +="		   <td class=td2>"+document.getElementById('Version.time_adv').value+adsSum+document.getElementById('script.ads.timeSetting').value+"("+document.getElementById('script.ads.atMost').value+picNum +document.getElementById('script.ads.durationNo').value+")"+"：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+document.getElementById('script.ads.playWay').value+"：";
	divTempStr +="			  <select name=timeType"+adsSum+" size=1 onchange=setPlayType(this.value,"+adsSum+");>";
	divTempStr +="				 <option value=0 selected>"+document.getElementById('script.ads.playForever').value+"</option>";
	divTempStr +="				 <option value=1>"+document.getElementById('script.ads.tempPlay').value+"</option>";
	divTempStr +="			  </select>";   
	divTempStr +="			  <input class=button type=button name=addTime"+adsSum+" id=addTime"+adsSum+" style=\"width:75\" value="+document.getElementById('script.ads.addTime').value+" title=\""+document.getElementById('script.ads.setDifferTime').value+"&#10"+document.getElementById('script.ads.playDifferTime').value+"\" onClick=\"addAdsTimeItems('timeTable"+adsSum+"',"+adsSum+");\">";
	divTempStr +="			  <input type=hidden name=timeItemNum"+adsSum+" id=timeItemNum"+adsSum+" value=1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	divTempStr +="		   </td>";
	divTempStr +="		</tr>";
	divTempStr +="		<tr>";
	divTempStr +="		<td class=tableTdCenter>";
	divTempStr +="		<div>";
	divTempStr +="			<table width=100% id=timeTable"+adsSum+"  cellSpacing=0 cellPadding=1>";
	divTempStr +="			  <tr>";		
	divTempStr +="			     <td id=startDate"+adsSum+" style=display:none class=tableTdCenter>&nbsp;"+document.getElementById('script.ads.playStartDate').value+":";
	divTempStr +="					<input type=\"text\" style=\"width:90px;\" class=\"pane\" name=\"startDate"+adsSum+"1\" id=\"date3\" readonly>       ";
	divTempStr +="					<a onclick=\"event.cancelBubble=true;\" href=\"javascript:showCalendar('dimg1"+adsSum+"1',true,'startDate"+adsSum+"1');\">";
	divTempStr +="					<img name=\"showCalendar\" id=\"dimg1"+adsSum+"1\" align=\"absmiddle\" src=\"../images/calendar.gif\" border=\"0\"></a>";
	divTempStr +="					&nbsp;&nbsp;&nbsp;"+document.getElementById('script.ads.playEndDate').value+":";
	divTempStr +="					<input type=\"text\" style=\"width:90px;\" class=\"pane\" name=\"endDate"+adsSum+"1\" id=\"date3\" readonly>     ";
	divTempStr +="					<a onclick=\"event.cancelBubble=true;\" href=\"javascript:showCalendar('dimg2"+adsSum+"1',true,'endDate"+adsSum+"1');\">";
	divTempStr +="					<img name=\"showCalendar\"  id=\"dimg2"+adsSum+"1\" align=\"absmiddle\" src=\"../images/calendar.gif\" border=\"0\"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	divTempStr +="				</td>";
	divTempStr +="			 <tr>";
	divTempStr +="				<td class=tableTdCenter>&nbsp;&nbsp;+"+document.getElementById('Main.begin_time').value+" 1:";
	divTempStr +="					<select name=startTime_s"+adsSum+"1 id=startTime_s"+adsSum+"1></select>"+document.getElementById('Main.hour').value;
	divTempStr +="					<select name=startTime_f"+adsSum+"1 id=startTime_f"+adsSum+"1></select>"+document.getElementById('Main.min').value;
	divTempStr +="					<select name=startTime_m"+adsSum+"1 id=startTime_m"+adsSum+"1></select>"+document.getElementById('Main.sec').value;
	divTempStr +="					&nbsp;&nbsp;&nbsp;"+document.getElementById('script.stopTime')+" 1:";
	divTempStr +="					<select name=endTime_s"+adsSum+"1 id=endTime_s"+adsSum+"1></select>"+document.getElementById('Main.hour').value;
	divTempStr +="					<select name=endTime_f"+adsSum+"1 id=endTime_f"+adsSum+"1></select>"+document.getElementById('Main.min').value;
	divTempStr +="					<select name=endTime_m"+adsSum+"1 id=endTime_m"+adsSum+"1></select>"+document.getElementById('Main.sec').value+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	divTempStr +="				</td>";
	divTempStr +="			</tr>";
	divTempStr +="		   </table>";
	divTempStr +="		</div>";
	divTempStr +="		</td>";
	divTempStr +="		</tr>";
	divTempStr +="	</table>";
	divTempStr +="</div>";
	return divTempStr;
}

/**设定播放日期*/
function setPlayType(playType,playNum)
{
	if(playType==0)
	{
		document.getElementById("startDate"+playNum).style.display='none';
	}
	else
	{
		document.getElementById("startDate"+playNum).style.display='';
	}
}
/**
* 生成跑马灯文字广告层
*/
function addAdsAnnounceDiv()
{
	var divTempStr = "";
	divTempStr +="<div id=announceSetAds"+adsSum+">";
	divTempStr +="	 <table width=100%  cellSpacing=0 cellPadding=0>";
	divTempStr +="	  <tr class=tr1>";
	divTempStr +="		 <td class=td1><b>"+document.getElementById('Version.notice_text').value+adsSum+":</b> </td>";
	divTempStr +="		 <td class=td1>";
	//divTempStr +="			<input class=button type=button name=bntPreview id=bntPreview value='预  览' title=广告简单效果查看 onClick=\"previewAnnounce("+adsSum+");\">";
	divTempStr +="			<input style=display:none class=button type=button name=delAds"+adsSum+"Bnt id=delAds"+adsSum+"Bnt title="+document.getElementById('script.ads.delThis').value+" value="+document.getElementById('Version.delete_adv').value+" onClick=\"delAds("+adsSum+")\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;";
	divTempStr +="		 </td>";
	divTempStr +="	  </tr>";
	divTempStr +="	  <tr class=tr1>";
	divTempStr +="		  <td class=td2></td>";
	divTempStr +="		  <td class=td2>&nbsp;</td> ";
	divTempStr +="	  </tr>";
	/*
	divTempStr +="	  <tr class=tr1>";
	divTempStr +="		  <td class=td2>滚动间隔：</td>";
	divTempStr +="		  <td class=td2><input type=button value=+ onClick=\"addSpeed("+adsSum+");\" title=\"增加滚动时间间隔，减慢滚动速度\" class=bntAddDel>&nbsp;<input type=text name=time"+adsSum+"  id=time"+adsSum+" class=input30 value=10 onChange=\"setSpeed("+adsSum+")\" >&nbsp;<input type=button onClick=\"reduceSpeed("+adsSum+");\" title=\"减少滚动时间间隔，加快滚动速度\" value=- class=bntAddDel>毫秒</td>";
	divTempStr +="	  </tr>";
	divTempStr +="	  <tr>";
	divTempStr +="		  <td class=td2 nowrap>颜色：</td>";
	divTempStr +="		  <td class=td2>		  ";
	divTempStr +="			 <span id=colorSpan"+adsSum+"><input type=text ondbclick=\"doDialog("+adsSum+");\" style=\" background-color:#0000ff; width:60px\" readonly></span>";
	divTempStr +="				 <a href=\"javascript:doDialog("+adsSum+");\" title=\"点击，显示颜色选择框\" >设置</a><input type=hidden name=color"+adsSum+"  id=color"+adsSum+" value=\"#0000ff\"></td>";
	divTempStr +="	  </tr>";
	divTempStr +="	  <tr>";
	divTempStr +="		  <td class=td2>字体：</td>";
	divTempStr +="		  <td class=td2>";
	divTempStr +="			 <select name=fontFamily"+adsSum+" id=fontFamily"+adsSum+">";
	divTempStr +="				 <option value=\"宋体\">宋体</option>";
	divTempStr +="				 <option value=\"宋体-18030\" selected>宋体-18030</option>";
	divTempStr +="				 <option value=\"新宋体-18030\">新宋体-18030</option>";
	divTempStr +="				 <option value=\"新宋体\">新宋体</option>";
	divTempStr +="				 <option value=\"黑体\">黑体</option>";
	divTempStr +="				 <option value=\"幼圆\">幼圆</option>";
	divTempStr +="				 <option value=\"方正舒体\">方正舒体</option>";
	divTempStr +="				 <option value=\"方正姚体\">方正姚体</option>";
	divTempStr +="				 <option value=\"方正姚体\">方正姚体</option>";
	divTempStr +="				 <option value=\"Arial, Helvetica, sans-serif\">Arial, Helvetica,sans-serif</option>";
	divTempStr +="				 <option value=\"Times New Roman, Times, serif\">Times NewRoman,Times,serif</option>";
	divTempStr +="				 <option value=\"Courier New, Courier, mono\">Courier New,Courier,mono</option>";
	divTempStr +="				 <option value=\"Georgia, Times New Roman, Times, serif\">Georgia,Times New Roman,Times,serif</option>";
	divTempStr +="				 <option value=\"Verdana, Arial, Helvetica, sans-serif\">Verdana,Arial,Helvetica,sans-serif</option>";
	divTempStr +="				 <option value=\"Geneva, Arial, Helvetica, sans-serif\">Geneva,Arial,Helvetica,sans-serif</option>";
	divTempStr +="			</select>			      </td>";
	divTempStr +="	  </tr>";
	divTempStr +="	  <tr>";
	divTempStr +="		  <td class=td2 nowrap>字体大小：</td>";
	divTempStr +="		  <td class=td2>";
	divTempStr +="		    <select name=fontSize"+adsSum+" id=fontSize"+adsSum+" >";
	divTempStr +="			   <option value=\"10px\">10</option>";
	divTempStr +="			   <option value=\"11px\">11</option>";
	divTempStr +="			   <option value=\"12px\">12</option>";
	divTempStr +="		       <option value=\"14px\">14</option>";
	divTempStr +="			   <option value=\"16px\">16</option>";
	divTempStr +="			   <option value=\"18px\">18</option>";
	divTempStr +="			   <option value=\"20px\">20</option>";
	divTempStr +="			   <option value=\"22px\">22</option>";
	divTempStr +="			   <option value=\"24px\">24</option>";
	divTempStr +="			   <option value=\"36px\" selected>36</option>";		    
	divTempStr +="			</select>px			   "; 
	divTempStr +="		  </td>";
	divTempStr +="		  </tr>";
	*/
	divTempStr +="		  <tr>";
	divTempStr +="		  <td class=td2>"+document.getElementById('script.txtContent').value+"：</td>";
	divTempStr +="		  <td class=td2 >";
	divTempStr +="			 <textarea name=announceText"+adsSum+" cols=70 id=announceText"+adsSum+" >"+document.getElementById('script.ads.textNoticeHere').value+adsSum+document.getElementById('script.content').value+"</textarea>";
	divTempStr +="		  </td>";
	divTempStr +="		 </tr>";
	divTempStr +="		 <tr>";
	divTempStr +="			 <td  colspan=4 class=td2>";
	divTempStr += "				 <div id=ads"+adsSum+"TimeZone>&nbsp;</div>";
	divTempStr +="			 </td>";
	divTempStr +="		 </tr>";
	divTempStr +="		 <tr>";
	divTempStr +="			 <td  colspan=4 class=td2>";
	divTempStr +="			 </td>";
	divTempStr +="		 </tr>";
	divTempStr +="	 </table>";
	divTempStr +=" </div>";
	return divTempStr;
}
/**
* 增加广告附件输入选择框
*/
function addAdsFileItem(tableId,adsNo,fileNo)
{	
	var tableObj=$(tableId);
	
	if(tableObj==null)
	{
		alert("addAdsFileItem() return false");
		return false;
	}
	if(parseInt(fileNo)<10)
	{
		tableObj.insertRow().insertCell().innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;"+document.getElementById('script.accessory').value+fileNo+":&nbsp;&nbsp;<input type=file name=file"+adsNo+fileNo+" id=file"+adsNo+fileNo+" class=inputFile3 onchange=\"pretreatAdsFileName(this.value)\">&nbsp;<input style=display:none id=\""+adsNo+fileNo+"bnt\" type=\"button\" name=delBnt"+adsNo+" class=button value=\""+document.getElementById('Main.delete').value+"\" onClick=\"delAdsFileItems('"+tableId+"',"+adsNo+","+fileNo+");\">&nbsp;&nbsp;"+document.getElementById('script.playTime').value+"：<input type=text class=inputSecond name=showtime"+adsNo+fileNo+" id=showtime"+adsNo+fileNo+"  onKeyPress=\"if ((event.keyCode<48 || event.keyCode>57)){event.returnValue=false;alert('"+document.getElementById('script.ads.invalidNumber').value+"');}\">"+document.getElementById('Main.sec').value;
	}
	else
	{
		tableObj.insertRow().insertCell().innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;"+document.getElementById('script.accessory').value+fileNo+":&nbsp;&nbsp;<input type=file name=file"+adsNo+fileNo+" id=file"+adsNo+fileNo+" class=inputFile3 onchange=\"pretreatAdsFileName(this.value)\" >&nbsp;<input style=display:none id=\""+adsNo+fileNo+"bnt\" type=\"button\" name=delBnt"+adsNo+" class=button value=\""+document.getElementById('Main.delete').value+"\" onClick=\"delAdsFileItems('"+tableId+"',"+adsNo+","+fileNo+");\">&nbsp;&nbsp;+"document.getElementById('script.playTime').value+"：<input type=text class=inputSecond name=showtime"+adsNo+fileNo+" id=showtime"+adsNo+fileNo+"  onKeyPress=\"if ((event.keyCode<48 || event.keyCode>57)){event.returnValue=false;alert('"+document.getElementById('script.ads.invalidNumber').value+"');}\">"+document.getElementById('Main.sec').value;
	}
	return true ;
}

/**
*增加多个附件
*/
function addAdsFileItems(tableId,adsNo)
{
	fileSumArr[adsNo-1]++;
	
	if(fileSumArr[adsNo-1]-delFileSumArr[adsNo-1]>10)
	{
		alert(document.getElementById('script.ads.adsLimit').value);
		fileSumArr[adsNo-1]--;
		return false;
	}
	
	
	if(isNaN(fileSumArr[adsNo-1]))
	{
		alert('addAdsFileItems()中fileSumArr[adsNo-1]非数字');
		return false;
	}
	addAdsFileItem(tableId,adsNo,fileSumArr[adsNo-1]);
	$('fileItemNum'+adsNo).value=fileSumArr[adsNo-1];
}
/**
* 删除广告附件输入选择框
*/
function delAdsFileItem(tableId,adsNo,fileNo)
{
	var tableObj = $(tableId);
	var rowLen = 0 ;
	
	if(tableObj==null)return false;
	
	var delBntObjs = document.getElementsByName('delBnt'+adsNo);
	var tempId=adsNo+""+fileNo+"bnt";
	var isDel =false;
	var fileTableObj = $('fileTable'+adsNo);
	
	rowLen=delBntObjs.length;
	
	//for debug
	if(rowLen==0)
	{
		alert(document.getElementById('script.ads.findCounterpart').value);
		return false;
	}
	//alert("tempId:"+tempId+"  rowLen:"+rowLen);
	for(var i=0; i<rowLen; i++)
	{
		if(delBntObjs[i].id==tempId)
		{
			fileTableObj.deleteRow(i);
			isDel=true;
			delFileSumArr[adsNo-1]++;
			break;
		}
	}
	return isDel;
}
function delAdsFileItems(tableId,adsNo,fileNo)
{	
	delAdsFileItem(tableId,adsNo,fileNo);
}
/**
*增加广告时间段输入选择框
*/
function addAdsTimeItem(tableId,adsNo,timeNo)
{	
	var tempStr="";
	var timeItem="";
	var timeRow=timeNo;
	var timeTableObj=$(tableId);
	
	timeRow < 10 ? timeItem=" "+timeRow : timeItem=""+timeRow ;
	tempStr+="&nbsp;&nbsp;"+document.getElementById('Main.begin_time').value+timeItem+":&nbsp;<select name=startTime_s"+adsNo+timeRow+" id=startTime_s"+adsNo+timeRow+"></select>"+document.getElementById('Main.hour').value+"&nbsp;<select name=startTime_f"+adsNo+timeRow+" id=startTime_f"+adsNo+timeRow+"></select>"+document.getElementById('Main.min').value+"&nbsp;<select name=startTime_m"+adsNo+timeRow+" id=startTime_m"+adsNo+timeRow+"></select>"+document.getElementById('Main.sec').value;
	tempStr+="&nbsp;&nbsp;&nbsp;&nbsp;"+document.getElementById('script.stopTime').value+timeItem+":&nbsp;<select name=endTime_s"+adsNo+timeRow+" id=endTime_s"+adsNo+timeRow+"></select>"+document.getElementById('Main.hour').value+"&nbsp;<select name=endTime_f"+adsNo+timeRow+" id=endTime_f"+adsNo+timeRow+"></select>"+document.getElementById('Main.min').value+"&nbsp;<select name=endTime_m"+adsNo+timeRow+" id=endTime_m"+adsNo+timeRow+"></select>"+document.getElementById('Main.sec').value					
	//tempStr+="&nbsp;&nbsp;<input class=button type=button name=\"delTime"+adsNo+"\" id=delTime"+adsNo+timeRow+" value=\"删除\" onClick=\"delAdsTimeItem('timeTable"+adsNo+"',"+adsNo+","+timeNo+");\">";				
	
	timeTableObj.insertRow().insertCell().innerHTML = tempStr;
	
	setAdsTimeSelectOptions(adsNo,timeRow);
}
/**
* 功能介绍：增加第adsNo个广告的时间段select框
*/
function addAdsTimeItems(tableId,adsNo)
{
	timeSumArr[adsNo-1]++;
	if(timeSumArr[adsNo-1]-delTimeSumArr[adsNo-1]>10)
	{
		alert(document.getElementById('script.ads.adsLimit').value);
		timeSumArr[adsNo-1]--;
		return false;
	}
	
	if(isNaN(timeSumArr[adsNo-1]))
	{
		alert('addAdsTimeItems()中timeSumArr[adsNo-1]非数字');
		return false;
	}
	addAdsTimeItem(tableId,adsNo,timeSumArr[adsNo-1]);
	
	$('timeItemNum'+adsNo).value=timeSumArr[adsNo-1];
	return true;
}
/**
* 给第adsNo个广告的第timeRow行时间段添加select框的值
*/
function setAdsTimeSelectOptions(adsNo,timeRow)
{
	var rowNo=timeRow;
	var startSelectObj = document.getElementById("startTime_s"+adsNo+rowNo);
	var endSelectObj = document.getElementById("endTime_s"+adsNo+rowNo);
	for(i=0;i<24;i++)
	{
		 if(i<10) 
		 {
		 	startSelectObj.options.add(new Option("0"+i,"0"+i));
		 	endSelectObj.options.add(new Option("0"+i,"0"+i));
		 }
		 else
		 {
		 	startSelectObj.options.add(new Option(""+i,""+i));
		 	endSelectObj.options.add(new Option(""+i,""+i));
		 }
		 
	}
	endSelectObj.value="23";
	startSelectObj = document.getElementById("startTime_f"+adsNo+rowNo);
	endSelectObj = document.getElementById("endTime_f"+adsNo+rowNo);
	for(i=0;i<60;i++)
	{
		 if(i<10) 
		 {
		 	startSelectObj.options.add(new Option("0"+i,"0"+i));
		 	endSelectObj.options.add(new Option("0"+i,"0"+i));
		 }
		 else
		 {
		 	startSelectObj.options.add(new Option(""+i,""+i));
		 	endSelectObj.options.add(new Option(""+i,""+i));
		 }
	}
	endSelectObj.value="59";
	startSelectObj = document.getElementById("startTime_m"+adsNo+rowNo);
	endSelectObj = document.getElementById("endTime_m"+adsNo+rowNo);
	for(i=0;i<60;i++)
	{
		 if(i<10) 
		 {
		 	startSelectObj.options.add(new Option("0"+i,"0"+i));
		 	endSelectObj.options.add(new Option("0"+i,"0"+i));
		 }
		 else
		 {
		 	startSelectObj.options.add(new Option(""+i,""+i));
		 	endSelectObj.options.add(new Option(""+i,""+i));
		 }
	}
	endSelectObj.value="59";
}

/**
* 给第adsNo个广告的第timeRow行时间段添加select框的值
*/
function setAdsDateSelectOptions(adsNo,timeRow)
{

	var today = new Date();
	var year=today.getYear();
	var month=today.getMonth()+1;
	var day=today.getDate();

	var rowNo=timeRow;
	var startSelectObj = document.getElementById("startDate_y"+adsNo+rowNo);
	var endSelectObj = document.getElementById("endDate_y"+adsNo+rowNo);
	for(i=year;i<year+10;i++)
	{
		 if(i<10) 
		 {
		 	startSelectObj.options.add(new Option("0"+i,"0"+i));
		 	endSelectObj.options.add(new Option("0"+i,"0"+i));
		 }
		 else
		 {
		 	startSelectObj.options.add(new Option(""+i,""+i));
		 	endSelectObj.options.add(new Option(""+i,""+i));
		 }
		 
	}
	startSelectObj.value=year;
	endSelectObj.value=year;
	startSelectObj = document.getElementById("startDate_m"+adsNo+rowNo);
	endSelectObj = document.getElementById("endDate_m"+adsNo+rowNo);
	for(i=1;i<13;i++)
	{
		 if(i<10) 
		 {
		 	startSelectObj.options.add(new Option("0"+i,"0"+i));
		 	endSelectObj.options.add(new Option("0"+i,"0"+i));
		 }
		 else
		 {
		 	startSelectObj.options.add(new Option(""+i,""+i));
		 	endSelectObj.options.add(new Option(""+i,""+i));
		 }
	}
	if(month<10)
	{
		startSelectObj.value="0"+month;
	}
	else
	{
		startSelectObj.value=""+month;
	}
	
	endSelectObj.value="12";
	startSelectObj = document.getElementById("startDate_d"+adsNo+rowNo);
	endSelectObj = document.getElementById("endDate_d"+adsNo+rowNo);
	for(i=1;i<32;i++)
	{
		 if(i<10) 
		 {
		 	startSelectObj.options.add(new Option("0"+i,"0"+i));
		 	endSelectObj.options.add(new Option("0"+i,"0"+i));
		 }
		 else
		 {
		 	startSelectObj.options.add(new Option(""+i,""+i));
		 	endSelectObj.options.add(new Option(""+i,""+i));
		 }
	}
	if(day<10)
	{
		startSelectObj.value="0"+day;
	}
	else
	{
		startSelectObj.value=""+day;
	}
	
	endSelectObj.value="31";
}

/**
*删除广告附件输入选择框
*/
function delAdsTimeItem(tableId,adsNo,timeNo)
{
	var tableObj = $(tableId);
	var rowLen = 0 ;
	
	if(tableObj==null)return false;
	
	var delBntObjs = document.getElementsByName('delTime'+adsNo);
	var tempId="delTime"+adsNo+""+timeNo;
	var isDel =false;
	
	rowLen=delBntObjs.length+1;//第一个没有删除键
	
	//for debug
	if(rowLen==0)
	{
		alert(document.getElementById('script.ads.findCounterpart').value);
		return false;
	}
	
	for(var i=1; i<rowLen; i++)
	{		
		if(delBntObjs[i-1].id==tempId)//第一个没有删除键
		{
			tableObj.deleteRow(i);
			isDel=true;
			delTimeSumArr[adsNo-1]++;
			break;
		}
	}
	return isDel;
}
/**
* 删除广告
*/
function delAds(no)
{
	if(!no)return false;
	var tempStr1="fileSetAds"+no;
	var tempStr2="timeSetAds"+no;
	var tempStr3="announceSetAds"+no;
	if(parseInt($('adsType').value)==2)
	{
		$(tempStr3).innerHTML="&nbsp;";
	}
	else
	{
		$(tempStr1).innerHTML="&nbsp;";
	}
	
	//$(tempStr2).innerHTML="&nbsp;";
	delAdsSum++;
}
/**
*是否合法的广告文件
*/
function isAdsFileValid(fileName)
{
	if(fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="jpg"
		||fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="avi"
		||fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="wma"
		||fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="asf"
		||fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="swf")
		return true;
		else return false;
}
/** 
*取第adsNo个广告的第一个有效文件路径
*/
function getAdsFirstFile(adsNo)
{
	var tempStr1="file"+adsNo;
	var tempStr2="";
	//alert(fileSumArr[adsNo-1]+"   "+adsNo);
	for(var n=0;n<fileSumArr[adsNo-1]+1;n++)
	{
		tempStr2=tempStr1+""+n;
		//alert(tempStr2);
		if(document.getElementsByName(tempStr2)[0]&&document.getElementsByName(tempStr2)[0].value.length!=0)
		{
			//alert(document.getElementsByName(tempStr2)[0].value);
			return document.getElementsByName(tempStr2)[0].value;
		}
	}
	return "";
}
/**
*showTime是否是一个有效值
*/
function isValidShowTime(showTime)
{
	//alert(showTime);
	var isValid=true;
	if(showTime==null||showTime=="")
	{
		alert(document.getElementById('script.ads.findCounterpart').value);
		isValid=false;
	}
	if(isNaN(showTime))
	{
		alert(document.getElementById('script.ads.timeInputNumber').value);
		isValid=false;
	}
	if(parseInt(showTime)<=0||parseInt(showTime)>60)
	{
		alert(document.getElementById('script.ads.adsInMinute').value);
		isValid=false;		
	}
	return isValid;
}
/** 
* 第adsNo个广告的临时播放日期是否有效
*/
function isAdsDateItemsValid(adsNo)
{
	var startDate_sObj=document.getElementById("startDate"+adsNo+"1");
	var endDate_sObj=document.getElementById("endDate"+adsNo+"1");
	var startDate=startDate_sObj.value;
	var endDate=endDate_sObj.value;
	if(startDate==null||startDate=="")
	{
		alert(document.getElementById('script.ads.selectNo').value+adsNo+document.getElementById('script.ads.selectStartDate').value);
		return false;
	}
	if(endDate==null||endDate=="")
	{
		alert(document.getElementById('script.ads.selectNo').value+adsNo+document.getElementById('script.ads.selectEndDate').value);
		return false;
	}
	startDate=startDate.substring(0,4)+startDate.substring(5,7)+startDate.substring(8,10);
	endDate=endDate.substring(0,4)+endDate.substring(5,7)+endDate.substring(8,10);
	var date=new Date();
	var month="0"+(date.getMonth()+1);
	month=month.substring(month.length-2);
	var day="0"+date.getDate();
	day=day.substring(day.length-2);
	var nowDate=date.getYear()+""+month+""+day;
    if(startDate<nowDate)
	{
		alert(document.getElementById('Main.the').value+adsNo+document.getElementById('script.ads.endDateError').value);
		return false;
	}	
	if(startDate>endDate)
	{
		alert(document.getElementById('Main.the').value+adsNo+document.getElementById('script.ads.startDateError').value);
		return false;
	}
	
	
	return true;
}
/** 
* 第adsNo个广告的播放时间段是否有效值
*/
function isAdsTimeItemsValid(adsNo)
{
	var isValid = true ;
	var startTime_sObj;
	var startTime_fObj;
	var startTime_mObj;
	var endTime_sObj;
	var endTime_fObj;
	var endTime_mObj;
	var tempStartTime;
	var tempEndTime;
	var timeRow=parseInt($('timeItemNum'+adsNo).value);
	//alert("timeRow"+timeRow);
	for(var i=1;i<timeRow+1;i++)
	{
		if(!document.getElementById("startTime_s"+adsNo+i))
		continue;
		else
		{
			startTime_sObj=document.getElementById("startTime_s"+adsNo+i);
			startTime_fObj=document.getElementById("startTime_f"+adsNo+i);
			startTime_mObj=document.getElementById("startTime_m"+adsNo+i);
			endTime_sObj  =document.getElementById("endTime_s"+adsNo+i);
			endTime_fObj  =document.getElementById("endTime_f"+adsNo+i);
			endTime_mObj  =document.getElementById("endTime_m"+adsNo+i);
			
			if(!startTime_sObj||!startTime_fObj||!startTime_mObj||!endTime_sObj||!endTime_fObj||!endTime_mObj)continue;
			
			tempStartTime=startTime_sObj.value+""+startTime_fObj.value+startTime_mObj.value;
			tempEndTime=endTime_sObj.value+""+endTime_fObj.value+endTime_mObj.value;
			
			if(tempStartTime>tempEndTime)
			{
				isValid = false;
				break;
			}
		}
	}
	//alert("isValid  "+isValid);
	return isValid;
}
/** 
*广告文件预处理，如果是图片文件先放到缓存
*/
function pretreatAdsFileName(fileUrl)
{
	if(!isAdsFileValid(fileUrl))
	{
		alert(document.getElementById('script.ads.selectValidFile').value);
		return false;
	}
	
	var adsFileType=distinguishAdsFile(fileUrl);
	if(adsFileType=="pic")
	{
		var imgObj = new Image();
		imgObj.src=fileUrl;
		imageArr[imageArr.length] = imgObj; 
	}
}

/**
* 减少文字广告滚动速度
*/
function reduceSpeed(adsNo)
{
	if(isNaN($('time'+adsNo).value))
	{
		alert(document.getElementById('script.ads.validValue').value);
		return ;
	}
	if(parseInt($('time'+adsNo).value)>10)
	{
		$('time'+adsNo).value=parseInt($('time'+adsNo).value)-10;
	}
	else
	{
		alert(document.getElementById('script.canNotDecrease').value);
		return ;
	}
}

/**
* 增加文字广告滚动速度
*/
function addSpeed(adsNo)
{
	if(!isNaN($('time'+adsNo).value))
	{
		$('time'+adsNo).value=parseInt($('time'+adsNo).value)+10;
	}
	else
	{
		alert(document.getElementById('script.ads.validValue').value);
		return ;
	}
}


function doDialog(adsNo)
{
	//window.open('color.html',"","height=300, width=330");
	tempAdsNo=adsNo;
	dWin=showModelessDialog('version/color_set.html',window,'status:no;dialogWidth:320px;dialogHeight:300px');
}

/**
* 设定文字广告的字体色彩
*/
function setColorValue(colorValue)
{
	//alert(tempAdsNo);
	if(colorValue.length!=7)return false;
	document.getElementById("color"+tempAdsNo).value=colorValue;
	document.getElementById("colorSpan"+tempAdsNo).innerHTML="<input type=\"text\"  style=\" background-color:"+colorValue+"; width:60px;\" readonly>";
}

/**
* 预览广告的参数变量定义
*/
var arrPicName =  new Array();
var arrPicShowTime = new Array();
var arrVideoName = new Array();
var arrVideoShowTime = new Array();
var arrFlashName = new Array();
var arrFlashShowTime = new Array();
var isPreview=0;
var debug=!true;
var cnt=2;
var displayUUID=1;
var flashObjStr="<object id=flashPreview classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0\" width=\"480\" height=\"360\">"; 
	flashObjStr+="<param name=\"movie\" value=\"\">";
	flashObjStr+="<PARAM NAME=\"AllowScriptAccess\" VALUE=\"never\">";
	flashObjStr+="<param NAME=\"play\" VALUE=\"false\">";
	flashObjStr+="<param name=\"quality\" value=\"high\">" ;
	flashObjStr+="<embed src=\"\" quality=\"high\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" type=\"application/x-shockwave-flash\" width=\"550\" height=\"400\" AllowScriptAccess=\"never\"></embed>"; 
	flashObjStr+="</object>";

/**
* 区别广告文件类型
*/
function distinguishAdsFile(fileName)
{
	if(fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="jpg")
	{
		return "pic";
	}
	if(fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="avi"
		||fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="wma"
		||fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="asf"
		)
	{
		return "video"
	}
	if(fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="swf")
	{
		return "flash";
	}
	return "other";
}
/**
* 广告文件及其播放时间，是否可预览或者提交
*/
function isAdsFileReady(adsNo)
{//avi、wma、asf、swf、jpg
	var isReady = 1;
	var tempStr1="file"+adsNo;
	var tempStr2="";
	var tempStr3="showtime"+adsNo;
	for(var n=0;n<fileSumArr[adsNo-1]+1;n++)
	{
		tempStr2=tempStr1+""+n;
		if(document.getElementsByName(tempStr2)[0])
		{
			if(document.getElementsByName(tempStr2)[0].value.length!=0)
			{
				//文件类型检测
				if(!isAdsFileValid(document.getElementsByName(tempStr2)[0].value))
				{
					isReady=2;
					break;
				}
				
				//播放时间检测
				tempStr2=tempStr3+""+n;
				if(!isValidShowTime(document.getElementsByName(tempStr2)[0].value))
				{
					isReady=3;
					break;
				}
			}
			
		}
		
	}
	return isReady;
}
/**
* 读取识别广告文件，并存储信息
*/
function getAdsFile(adsNo)
{//存储文件路径及其播放时间
	//清零
	arrPicName.length = 0;
	arrPicShowTime.length = 0;
	arrVideoName.length = 0;
	arrVideoShowTime.length = 0;
	arrFlashName.length = 0;
	arrFlashShowTime.length = 0;
		
	//合法性校验	
	var isVF=isAdsFileReady(adsNo);

	if(isVF==2)
	{
				
		alert(document.getElementById('script.ads.selectValidFile').value);
		return false;
	}
	if(isVF==3)
	{
		return false;
	}
	
	//读取有关信息
	var tempStr1="file"+adsNo;
	var tempStr2="";
	var tempStr3="showtime"+adsNo;
	var adsType="";
	//alert(fileSumArr[adsNo-1]);
	for(var n=0;n<fileSumArr[adsNo-1]+1;n++)
	{
		tempStr2=tempStr1+""+n;
		if(document.getElementsByName(tempStr2)[0]&&document.getElementsByName(tempStr2)[0].value.length!=0)
		{
			adsType=distinguishAdsFile(document.getElementsByName(tempStr2)[0].value);
			//alert(adsType);
			if(adsType=="pic")
			{
				arrPicName[arrPicName.length]=n;
				tempStr2=tempStr3+""+n;
				arrPicShowTime[arrPicShowTime.length]=document.getElementsByName(tempStr2)[0].value;
			}
			if(adsType=="video")
			{
				arrVideoName[arrVideoName.length]=n;
				tempStr2=tempStr3+""+n;
				arrVideoShowTime[arrVideoShowTime.length]=document.getElementsByName(tempStr2)[0].value;
			}
			//Flash暂时播放不了
			/*if(adsType=="flash")
			{
				arrFlashName[arrFlashName.length]=n;
				tempStr2=tempStr3+""+n;
				arrFlashShowTime[arrFlashShowTime.length]=document.getElementsByName(tempStr2)[0].value;
			}*/
		}
	}
	//alert("arrPicName.length:"+arrPicName.length+"   arrVideoName.length:"+arrVideoName.length+"  arrFlashName.length:"+arrFlashName.length);
	if(arrPicName.length>0||arrVideoName.length>0||arrFlashName.length)
	return true;
	else return false ;
}
/**
* 预览操作
*/
function previewOperate(adsNo)
{

	//如果是通告
	if(parseInt(form1.adsType.value)==2)
	{
		document.getElementById("adsAnnounce").style.display='';
		showFile();
		return true;
	}
	
	//是否已经选择了文件
	var firstFileUrl=getAdsFirstFile(adsNo);
	if(!firstFileUrl)
	{ 
		alert(document.getElementById('script.ads.noValidAds').value); 
		return false;
	}
	
	//读取文件信息
	if(!getAdsFile(adsNo))return false;
		
	//装载Flash播放器
	if(arrFlashName.length > 0)document.getElementById('flashDiv').innerHTML = flashObjStr;
	
	if(debug)alert("displayOperate()");
	displayUUID++;
	isPreview=1;

	display(adsNo,displayUUID);
	
}

/**
* 预览返回
*/
function displayReturn()
{
	if(debug)alert("displayReturn()");
	isPreview=0;
	//TODO
	//清零
	arrPicName.length = 0;
	arrPicShowTime.length = 0;
	arrVideoName.length = 0;
	arrVideoShowTime.length = 0;
	arrFlashName.length = 0;
	arrFlashShowTime.length = 0;
	
	$('submitBnt').style.display='';
	document.getElementById('adsMaker').style.display='';
	document.getElementById('player').style.display='none';
	document.getElementById('imgs').style.display='none';
	document.getElementById('flashPlayer').style.display='none';
	document.getElementById('WindowsMediaPlayer1').url="";
	document.getElementById('flashDiv').innerHTML = "";
	//document.getElementById('flashPreview').movie="";
	
}

/**
* 图片和视频预览
*/
function display(adsNo,checkedUUID)
{

	if(debug)alert("displayUUID:"+displayUUID+"  checkedUUID:"+checkedUUID);
	var timeOut=0;
	if(displayUUID!=checkedUUID)return false;
	if(debug)alert("display()  arrPicName");
	if(arrPicName.length!=0)
	{
		for(var i=0;i<arrPicName.length;i++)
		{
			setTimeout("displayPic("+adsNo+",'"+arrPicName[i]+"',"+displayUUID+")",timeOut*1000);
			timeOut+=parseInt(arrPicShowTime[i]);
		}

	}
	if(debug)alert("display()  arrFlashName");
	if(arrFlashName.length)
	{
		for(var i=0;i<arrFlashName.length;i++)
		{
			setTimeout("displayFlash("+adsNo+",'"+arrFlashName[i]+"',"+displayUUID+")",timeOut*1000);
			timeOut+=parseInt(arrFlashShowTime[i]);
		}
	}
	if(debug)alert("display()  arrVideoName");
	if(arrVideoName.length)
	{
		for(var i=0;i<arrVideoName.length;i++)
		{
			setTimeout("displayVideo("+adsNo+",'"+arrVideoName[i]+"',"+displayUUID+")",timeOut*1000);
			timeOut+=parseInt(arrVideoShowTime[i]);
		}
	}
	if(debug)alert("display()  end");
	if(isPreview==1)
	{
		if(debug)alert("display()  end timeOut:"+timeOut);
		setTimeout("display("+adsNo+","+displayUUID+")",timeOut*1000);
	}
}

/**
* 显示图片广告
*/
function displayPic(adsNo,PicFileName,checkedUUID)
{
	
	if(isPreview==0||arrPicName.length == 0)return false;
	if(displayUUID!=checkedUUID)return false;
	var fileStr="file"+adsNo+PicFileName;
	if(debug)alert(PicFileName);
	
	$('submitBnt').style.display='none';
	document.getElementById('adsMaker').style.display='none';
	document.getElementById('player').style.display='none';
	document.getElementById('imgs').style.display='';
	document.getElementById('WindowsMediaPlayer1').url="";
	document.getElementById('flashPlayer').style.display='none';
	if(document.getElementById('flashPreview'))document.getElementById('flashPreview').movie="";

	//var imgObj= new Image();
	//imgObj.src = document.getElementsByName(fileStr)[0].value; 
	
	document.getElementById('previewImgs').filters.revealTrans.apply();
	document.getElementById('previewImgs').filters.revealTrans.Transition=cnt;
	cnt++;
	if(cnt>23)cnt=2;
	document.getElementById('previewImgs').src=document.getElementsByName(fileStr)[0].value;
	//document.getElementById('showImgs').innerHTML="<img id=\"previewImgs\" src=\""+imgObj.src+"\" style=\" width:800px; height:600px ; overflow:scroll;\">";
	//previewImgs.src=document.getElementsByName(tempStr)[0].value;
	 
	document.getElementById('previewImgs').filters.revealTrans.play();
}
/**
* 视频广告预览
*/
function displayVideo(adsNo,videoFileName,checkedUUID)
{
	if(isPreview==0||arrVideoName.length == 0)return false;
	if(displayUUID!=checkedUUID)return false;
	var fileStr="file"+adsNo+videoFileName;
	$('submitBnt').style.display='none';
	document.getElementById('adsMaker').style.display='none';
	document.getElementById('player').style.display='';
	document.getElementById('imgs').style.display='none';
	document.getElementById('WindowsMediaPlayer1').url=document.getElementsByName(fileStr)[0].value;
	document.getElementById('flashPlayer').style.display='none';
	if(document.getElementById('flashPreview'))document.getElementById('flashPreview').movie="";
}

/**
* Flash广告预览
*/
function displayFlash(adsNo,flashFileName,checkedUUID)
{
	if(isPreview==0||arrFlashName.length == 0)return false;
	if(displayUUID!=checkedUUID)return false;
	var fileStr="file"+adsNo+flashFileName;
	document.getElementById('adsMaker').style.display='none';
	document.getElementById('player').style.display='none';
	document.getElementById('imgs').style.display='none';
	document.getElementById('WindowsMediaPlayer1').url="";
	document.getElementById('flashPlayer').style.display='';
	if(debug)alert('displayFlash()');
	//alert(document.getElementById('flashDiv').innerHTML);
	
	document.getElementById('flashDiv').innerHTML="<embed src="+document.getElementsByName(fileStr)[0].value+" quality=\"high\" showcontrols=false autostart=true loop=true type=\"application/x-shockwave-flash\" width=550 height=400 AllowScriptAccess=\"never\"></embed>";
	//alert(document.getElementById('flashDiv').innerHTML);
	//document.getElementById('flashPreview').movie=document.getElementsByName(fileStr)[0].value;
	//
}

/**
* 提交广告
*/
function submitAds()
{
	if(adsSum==delAdsSum)
	{
		alert(document.getElementById('script.ads.noAds').value);
		return false;
	}
	
	if(parseInt($('adsType').value)!=2)
	{
		var hasAdsFile=false;
		var tempStr1="";
		for(var i=1;i<adsSum+1;i++)
		{
			tempStr1=getAdsFirstFile(i);
			//alert(tempStr1);
			if(tempStr1.length>0)
			{
				hasAdsFile=true;				
			}
			else
			{	
				hasAdsFile=false;
				break;			
			}
			
			var isVF=isAdsFileReady(i);
			if(isVF==2)
			{
						
				alert(document.getElementById('script.ads.selectValidAds').value+i+document.getElementById('script.ads.supportedFiles').value);
				return false;
			}
			if(isVF==3)
			{
				return false;
			}
		}
		
		
		if(!hasAdsFile)
		{
			alert(document.getElementById('script.ads.selectAccessory').value);
			return false;
		}
	}
	//alert('timeRow');
	//判断播放时间是否正确
	for(var i=1;i<adsSum+1;i++)
	{
	    var timeType=document.getElementById('timeType'+i).value;
	    if(timeType=="1"&&!isAdsDateItemsValid(i))
	    {
	    	return false;
	    }
		var isVT=isAdsTimeItemsValid(i);
		if(!isVT)
		{
			alert(document.getElementById('script.ads.ads').value+i+document.getElementById('script.ads.startEndTimeError').value);
			return false;
		}
		
		if(parseInt($('adsType').value)==2&&($('announceText'+i).value.length<=0||getLength(removeSpace($('announceText'+i).value))>200))
		{
			alert(document.getElementById('script.ads.horseLight').value+i+document.getElementById('script.ads.contentTxtLengthLimit').value);
			return false;
		}
		if(parseInt($('adsType').value)==2&&($('announceText'+i).value).indexOf('\|')!=-1)
		{
			alert(document.getElementById('script.ads.horseLight').value+i+document.getElementById('script.ads.contentTxtError').value\"|\"！);
			return false;
		}
	}
	/*  
	var isVT=isValidTimeItem();
	if(!isVT)
	{
		alert(document.getElementById('script.IntervelError').value);
		return false;
	}
	var isVF=isValidFile();
	if(isVF==2)
	{
			
		alert(document.getElementById('script.ads.selectValidFile').value);
		return false;
	}
	if(isVF==3)
	{
			
		alert(document.getElementById('script.inputPlayTimeError').value);
		return false;
	}
	var startTime=document.getElementById('startTime_s1').value+""+document.getElementById('startTime_f1').value+document.getElementById('startTime_m1').value;
	var endTime=document.getElementById('endTime_s1').value+""+document.getElementById('endTime_f1').value+document.getElementById('endTime_m1').value;;
	
	
	if(startTime>=endTime)
	{
		if(startTime=='000000'&&startTime=='000000')
		{
			alert(document.getElementById('script.adsStartTimeEndTime').value);
		}
		else
		{
			alert(document.getElementById('script.adsTimeInputError').value);	
			return false;
		}
	}
	*/
	
	//var len = $F('adsDesc').length;
	if(getLength(removeSpace($F('adsDesc')))>200)
	{
		alert(document.getElementById('script.ads.adsDescLengthLimit').value);
		return false;
	}	  
	  
	$('adsTotal').value=adsSum;
	form1.submit();
	return true;
}

/**
* 文字广告浏览
*/
function previewAnnounce(adsNo)
{
	if($('announceText'+adsNo).value.length==0)
	{
		alert(document.getElementById('script.ads.invalidContent').value);
		return false;
	}
	
	var tempStr;
	tempStr="<font style=\"font-family:"+$('fontFamily'+adsNo).value+"; color:"+$('color'+adsNo).value+";font-size:"+$('fontSize'+adsNo).value+";font-weight:bold;\">"+$('announceText'+adsNo).value+"</font>";
	$("content").innerHTML=tempStr;
	
	if(isNaN($('time'+adsNo).value))
	{
		alert(document.getElementById('script.ads.validValue').value);
		return false;
	}
	
	$("mrq").scrollDelay=parseInt($('time'+adsNo).value);
	return true;
}

//返回字符串字节长度（1个汉字占2个字节）


function getByteLength(sString)
{
  var sStr,iCount,i,strTemp ; 

  iCount = 0 ;
  
  sStr = sString.split("");
  
  for (i = 0 ; i < sStr.length ; i ++){
  
    strTemp = escape(sStr[i]); 
    
    if (strTemp.indexOf("%u",0) == -1) {   // 表示是汉字
      iCount = iCount + 1 ;
    } 
    else {
      iCount = iCount + 2 ;
    }
  }
  return iCount ;
}



//除去字符串前后的空格

function removeSpace(str)
{
  var returnStr = str;

  while(returnStr.charAt(0)==" "){
	returnStr=returnStr.substring(1,returnStr.length);
  }
  while(returnStr.charAt(returnStr.length-1)==" "){
	returnStr=returnStr.substring(0,returnStr.length-1);
  }
  
  return returnStr;
}