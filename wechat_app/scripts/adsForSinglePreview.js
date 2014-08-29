
/**
* 跨平台广告信息预览
* @auther hjtang
*/

function addResponsorinfo()
{
	var isVT=isValidTimeItem();
	if(!isVT)
	{
		alert(document.getElementById('script.IntervelError').value);
		return false;
	}
	var isVF=isValidFile();
	if(isVF==2)
	{
			
		alert(document.getElementById('script.adsForSinglePreview.validFile').value);
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
	
	if(debug)alert("startTime:"+startTime+" endTime:"+endTime);
	if(form1.desSelList.options.length==0)
	{
		alert(document.getElementById('script.adsForSinglePreview.adsDev').value);
		return false;
	}
	selectAll0(document.form1.desSelList);
	form1.submit();
	return true;
}
var imgNo=0;
var timeOut=3000;
var imageArr = new Array();
function preview()
{
	if(parseInt(row)==0)
	{
		alert(document.getElementById('script.adsForSinglePreview.noPreview').value);
		return false;
	}
	previewPic();
	if(parseInt(fresh)==1)
	{
		setTimeout("preview()",timeOut);
	}
}

/**获取需要预览的媒体文件*/

function getFirstFile()
{
	for(var n=0;n<row+1;n++)
	{
		var tempStr="file"+n;
		if(document.getElementsByName(tempStr)[0]&&document.getElementsByName(tempStr)[0].value.length!=0)
		{
			return document.getElementsByName(tempStr)[0].value;
			break;
		}		
	}
	return "";
}

function isValidFileName(fileName)
{
	if(fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="jpg"
		||fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="gif"
		||fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="avi"
		||fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="wma"
		||fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="asf"
		||fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="swf")
		return true;
		else return false;
}

function isValidShowTime(showTime)
{
	var isValid=true;
	if(showTime==null||showTime=="")isValid=false;
	if(isNaN(showTime))isValid=false;
	if(parseInt(showTime)<1)isValid=false;
	return isValid;
}
function isValidTimeItem()
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
	//alert("timeRow"+timeRow);
	for(var i=2;i<timeRow;i++)
	{
		if(!document.getElementById("startTime_s"+i))
		continue;
		else
		{
			startTime_sObj=document.getElementById("startTime_s"+i);
			startTime_fObj=document.getElementById("startTime_f"+i);
			startTime_mObj=document.getElementById("startTime_m"+i);
			endTime_sObj  =document.getElementById("endTime_s"+i);
			endTime_fObj  =document.getElementById("endTime_f"+i);
			endTime_mObj  =document.getElementById("endTime_m"+i);
			
			if(!startTime_sObj||!startTime_fObj||!startTime_mObj||!endTime_sObj||!endTime_fObj||!endTime_mObj)continue;
			
			tempStartTime=startTime_sObj.value+""+startTime_fObj.value+startTime_mObj.value;
			tempEndTime=endTime_sObj.value+""+endTime_fObj.value+endTime_mObj.value;
			
			if(tempStartTime>=tempEndTime)
			{
				isValid = false;
				break;
			}
		}
	}
	//alert("isValid  "+isValid);
	return isValid;
	
}
function checkedFileName(value)
{
	if(!isValidFileName(value))
	{
		alert(document.getElementById('script.adsForSinglePreview.validFile').value);
		return false;
	}
	//如果是图片文件先放到缓存
	
	var adsFileType=distinguishAdsFile(value);
	if(adsFileType=="pic")
	{
		var imgObj = new Image();
		imgObj.src=value;
		imageArr[imageArr.length] = imgObj; 
	}
	
}
function isValidFile()
{//avi、wma、asf、swf、jpg和gif
	var isValid = 1;
	for(var n=0;n<row+1;n++)
	{
		var tempStr="file"+n;
		if(document.getElementsByName(tempStr)[0])
		{
			if(document.getElementsByName(tempStr)[0].value.length!=0)
			{
				if(!isValidFileName(document.getElementsByName(tempStr)[0].value))
				{
					isValid=2;
					break;
				}
				tempStr="showtime"+n;
				if(!isValidShowTime(document.getElementsByName(tempStr)[0].value))
				{
					isValid=3;
					break;
				}
			}
			
		}
		
	}
	return isValid;
}
function previewPic()
{
	imgNo++;
	if(imgNo>row)imgNo=1;
   
	for(var n=imgNo;n<row+1;n++)
	{
		var tempStr="file"+imgNo;
		if(document.getElementsByName(tempStr)[0])
		{
			imgObj= new Image();
			imgObj.src = document.getElementsByName(tempStr)[0].value; 
			//previewImgs.src=document.getElementsByName(tempStr)[0].value;
			showImgs.innerHTML="<img id=\"previewImgs\" src=\""+document.getElementsByName(tempStr)[0].value+"\" style=\" width:"+imgObj.width+"; height:"+imgObj.height+" ; overflow:scroll;filter: RevealTrans(Duration=4.0, Transition=23);\">";
			//previewImgs.src=document.getElementsByName(tempStr)[0].value;
			//previewImgs.style.width=imgObj.width;
			//previewImgs.style.height=imgObj.height;
			tempStr="showtime"+imgNo;
			timeOut=parseInt(document.getElementsByName(tempStr)[0].value)*1000;
			break;
		}
		
	}
	return "";
}
var row=0;
var delNum = 0;
var fresh=0;
function addFileItem(picNum)
{	
	var picTotal=picNum
	if(isNaN(picTotal))picTotal=10;
	if(parseInt(row)-parseInt(delNum)>=picTotal)

	{
		alert(document.getElementById('script.adsForSinglePreview.accessoryLimit').value+picTotal+document.getElementById('script.adsForSinglePreview.addOnly').value+picTotal+document.getElementById('script.adsForSinglePreview.fileQuantity').value)
		return ;
	}
	row++;
	//t1.insertRow().insertCell().innerHTML = "&nbsp;&nbsp;交易代码:&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"text\" maxlength=\"6\" onkeyup=\"value=value.replace(/[&%#]/g,'')\" name=\"row"+row+"\" values=\"\" class=\"inputHot\"><input ButtonNO=\""+row+"\" type=\"button\" name=\"tt\" class=\"bottonDel\" value=\"删除\" onClick=\"del("+row+");\">";
	if(parseInt(row)<10)
	fileTable.insertRow().insertCell().innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;"+document.getElementById('script.accessory').value+row+":&nbsp;&nbsp;<input type=file name=file"+row+" id=file"+row+" class=inputFile3 onchange=\"checkedFileName(this.value)\">&nbsp;<input id=\""+row+"bnt\" type=\"button\" name=\"tt\" class=\"bottonDel\" value=\""+document.getElementById('Main.delete').value+"\" onClick=\"del("+row+");\">&nbsp;&nbsp;"+document.getElementById('script.playTime').value+"：<input type=text class=inputSecond name=showtime"+row+" id=showtime"+row+"  onKeyPress=\"if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false\">"+document.getElementById('Main.sec').value;
	else
	fileTable.insertRow().insertCell().innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;"+document.getElementById('script.accessory').value+row+":&nbsp;&nbsp;<input type=file name=file"+row+" id=file"+row+" class=inputFile3 onchange=\"checkedFileName(this.value)\" >&nbsp;<input id=\""+row+"bnt\" type=\"button\" name=\"tt\" class=\"bottonDel\" value=\""+document.getElementById('Main.delete').value+"\" onClick=\"del("+row+");\">&nbsp;&nbsp;"+document.getElementById('script.playTime').value+"：<input type=text class=inputSecond name=showtime"+row+" id=showtime"+row+"  onKeyPress=\"if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false\">"+document.getElementById('Main.sec').value;
	document.getElementById('fileItemNum').value=row;
}
function del(line)
{

	var c = document.getElementsByName("tt");
	var len=c.length;
	var temStr="bnt";
	var tempId="";
	for(var i=0; i<len; i++)
	{
		tempId=line+temStr+"";
		//alert("tempId:"+tempId+"  c[i].id:"+c[i].id);
		if(c[i].id==tempId)//c[i].ButtonNO&&c[i].ButtonNO==line
		{
			fileTable.deleteRow(i);
			delNum++;
			break;
		}
	}
}
var timeRow=1;
var delTimeNum=0;
function addTimeItem()
{	
	var tempStr="";
	var timeItem="";
	if(parseInt(timeRow)-parseInt(delTimeNum)>=10)
	{
		alert(document.getElementById('script.adsForSinglePreview.timeDivision').value)
		return ;
	}
	timeRow++;
	timeRow < 10 ? timeItem=" "+timeRow : timeItem=""+timeRow ;
	tempStr+="&nbsp;&nbsp;"+document.getElementById('Main.begin_time').value+timeItem+":&nbsp;<select name=startTime_s"+timeRow+" id=startTime_s"+timeRow+"></select>"+document.getElementById('Main.hour').value+"&nbsp;<select name=startTime_f"+timeRow+" id=startTime_f"+timeRow+"></select>"+document.getElementById('Main.min').value+"&nbsp;<select name=startTime_m"+timeRow+" id=startTime_m"+timeRow+"></select>"+document.getElementById('Main.sec').value;
	tempStr+="&nbsp;&nbsp;&nbsp;&nbsp;"+document.getElementById('script.stopTime').value+timeItem+":&nbsp;<select name=endTime_s"+timeRow+" id=endTime_s"+timeRow+"></select>"+document.getElementById('Main.hour').value+"&nbsp;<select name=endTime_f"+timeRow+" id=endTime_f"+timeRow+"></select>"+document.getElementById('Main.min').value+"&nbsp;<select name=endTime_m"+timeRow+" id=endTime_m"+timeRow+"></select>"+document.getElementById('Main.sec').value					
	tempStr+="&nbsp;&nbsp;<input class=bottonDel type=button name=\"addTime\" id=addTime"+timeRow+" value=\""+document.getElementById('Main.delete').value+"\" onClick=\"delTimeItem("+timeRow+");\">";						
	timeTable.insertRow().insertCell().innerHTML = tempStr;
	document.getElementById('timeItemNum').value=timeRow;
	setTimeSelectOptions(timeRow);
}
function setTimeSelectOptions(timeRow)
{
	var rowNo=timeRow;
	var startSelectObj = document.getElementById("startTime_s"+rowNo);
	var endSelectObj = document.getElementById("endTime_s"+rowNo);
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
		 endSelectObj.value="23";
	}
	
	startSelectObj = document.getElementById("startTime_f"+rowNo);
	endSelectObj = document.getElementById("endTime_f"+rowNo);
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
	startSelectObj = document.getElementById("startTime_m"+rowNo);
	endSelectObj = document.getElementById("endTime_m"+rowNo);
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
function delTimeItem(line)
{

	var c = document.getElementsByName("addTime");
	var len=c.length;
	var tempId="";
	for(var i=0; i<len+1; i++)
	{
		tempId="addTime"+line;
		//alert("tempId:"+tempId+"  c[i].id:"+c[i].id);
		if(c[i].id==tempId)//c[i].ButtonNO&&c[i].ButtonNO==line
		{
			timeTable.deleteRow(i);
			delTimeNum++;
			break;
		}
	}
}
function initInputFile()
{
	var c = document.getElementsByName("tt");
	var len=c.length;
	for(var i=0; i<len; i++)
			fileTable.deleteRow(0);
	delNum=0;
	row=0;
}
function vidio()
{
	document.getElementById("WindowsMediaPlayer1").url=getFirstFile();
}
function previewAll()
{
	
	if(form1.adsType.value=="")
	{
		alert(document.getElementById('script.adsForSinglePreview.advType').value);
		return false;
	}
	
	if(parseInt(form1.adsType.value)==1||parseInt(form1.adsType.value)==0)
	{
		var firstFileUrl=getFirstFile();
		if(!firstFileUrl)
		{ 
			alert(document.getElementById('script.adsForSinglePreview.noAccessory').value); 
			return false;
		}
		if(firstFileUrl==null||firstFileUrl=="")
		{ 
			alert(document.getElementById('script.adsForSinglePreview.noAccessory').value); 
			return false;
		}
		
		
		
		var isVF=isValidFile();
		if(isVF==2)
		{
				
			alert(document.getElementById('script.adsForSinglePreview.validFile').value);
			return false;
		}
		if(isVF==3)
		{
				
			alert(document.getElementById('script.inputPlayTimeError').value);
			return false;
		}
		
		
		if(document.getElementById("adsMaker")!=null)
		document.getElementById("adsMaker").style.display='none';
		
		if(firstFileUrl.substring(firstFileUrl.length-3,firstFileUrl.length).toLowerCase()=="jpg"
		||firstFileUrl.substring(firstFileUrl.length-3,firstFileUrl.length).toLowerCase()=="gif")
		{
			document.getElementById("player").style.display='none';
			imgs.style.display='';
			document.getElementById("gotoImgsHere").click();
			//imgs.innerHTML="<img id=\"previewImgs\" src=\""+getFirsePic()+"\" style=\" width:auto; height:auto ; overflow:scroll\">";
			fresh=1;
			preview();
		}
		else
		{	
			imgs.style.display='none';
			document.getElementById("player").style.display='';
			document.getElementById("gotoPlayer").click();
			vidio();
		}
	}
	if(parseInt(form1.adsType.value)==2)
	{
		document.getElementById("adsAnnounce").style.display='';
		showFile();
	}
}

/**显示文件*/
function showFile()
{
	
	//textStr=getfile(getFirstFile());
	document.getElementById("content").innerHTML=getAdsContent();	
}
function gotoTop()
{
	document.getElementById("gotoTop").click();
	fresh=0;
	document.getElementById("WindowsMediaPlayer1").url="";
	imgs.style.display='none';
	document.getElementById("player").style.display='none';
	document.getElementById("adsMaker").style.display='';
}
function getfile(filespec)
{	
   var fso, f, text, ForReading;
   ForReading = 1, 
   text = "";   
   fso = new ActiveXObject("Scripting.FileSystemObject");  
   f = fso.OpenTextFile(filespec, ForReading, false);
   while (!f.AtEndOfStream)
      text += f.ReadLine();
   f.Close();
   return(text);
}
function reduceSpeed()
{
	if(isNaN(form1.time.value))
	{
		alert(document.getElementById('script.adsForSinglePreview.validValue').value);
		return ;
	}
	if(parseInt(form1.time.value)>10)
	{
		form1.time.value=parseInt(form1.time.value)-10;
		setSpeed();
	}
	else
	{
		alert(document.getElementById('script.canNotDecrease').value);
		return ;
	}
}
function addSpeed()
{
	if(!isNaN(form1.time.value))
	{
		form1.time.value=parseInt(form1.time.value)+10;
		setSpeed();
	}
	else
	{
		alert(document.getElementById('script.adsForSinglePreview.validValue').value);
		return ;
	}
}
function setSpeed()
{
	if(isNaN(form1.time.value))
	{
		alert(document.getElementById('script.adsForSinglePreview.validValue').value);
		return ;
	}
	//mrq.scrollDelay=parseInt(form1.time.value);
}

/**获取广告文件内容*/
function getAdsContent()
{
	var tempStr;
	tempStr="<font style=\"font-family:"+form1.fontFamily.value+"; color:"+form1.color.value+";font-size:"+form1.fontSize.value+";font-weight:bold;\">"+form1.announce.value+"</font>";
	return tempStr;
}

function setAdsType(value)
{	
	if(parseInt(value)==2)
	{//是通告
		document.getElementById("adsFile").style.display='none';
		document.getElementById("adsAnnounce").style.display='';
		document.getElementById("adsComment").style.display='';
		
		imgs.style.display='none';
		document.getElementById("player").style.display='none';
		fresh=0;
		initInputFile();
		
	}
	else if(parseInt(value)==1||parseInt(value)==0)
	{
		document.getElementById("adsFile").style.display='';
		document.getElementById("adsAnnounce").style.display='none';
		document.getElementById("adsComment").style.display='';
		fresh=0;
		initInputFile();
		addFileItem(5);
	}
	else
	{
		document.getElementById("adsFile").style.display='none';
		document.getElementById("adsAnnounce").style.display='none';
	}
}
function looklll()
{	
	getfile(form1.look.value)	
}

function setColorValue(colorValue)
{
	//alert('dd');
	if(colorValue.length!=7)return false;
	form1.color.value=colorValue;
	document.getElementById("colorSpan").innerHTML="<input type=\"text\"  style=\" background-color:"+colorValue+"; width:60px;\" readonly>";
	//showFile();
}

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

/**判断文件类型*/

function distinguishAdsFile(fileName)
{
	if(fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="jpg"
		||fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="gif")
		return "pic";
	if(fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="avi"
		||fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="wma"
		||fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="asf"
		)
		return "video"
	if(fileName.substring(fileName.length-3,fileName.length).toLowerCase()=="swf")
		return "flash";
	return "other";
}

/**获取广告媒体文件*/
function getAdsFile()
{
	//清零
	arrPicName.length = 0;
	arrPicShowTime.length = 0;
	arrVideoName.length = 0;
	arrVideoShowTime.length = 0;
	arrFlashName.length = 0;
	arrFlashShowTime.length = 0;
	
	//合法性校验	
	var isVF=isValidFile();
	if(isVF==2)
	{
				
		alert(document.getElementById('script.adsForSinglePreview.validFile').value);
		window.close();
		return false;
	}
	if(isVF==3)
	{
				
		alert(document.getElementById('script.inputPlayTimeError').value);
		window.close();
		return false;
	}
	
	for(var n=0;n<row+1;n++)
	{
		var tempStr="file"+n;
		if(document.getElementsByName(tempStr)[0]&&document.getElementsByName(tempStr)[0].value.length!=0)
		{
			var adsType=distinguishAdsFile(document.getElementsByName(tempStr)[0].value);
			
			if(adsType=="pic")
			{
				arrPicName[arrPicName.length]=n;
				tempStr="showtime"+n;
				arrPicShowTime[arrPicShowTime.length]=document.getElementsByName(tempStr)[0].value;
			}
			if(adsType=="video")
			{
				arrVideoName[arrVideoName.length]=n;
				tempStr="showtime"+n;
				arrVideoShowTime[arrVideoShowTime.length]=document.getElementsByName(tempStr)[0].value;
			}
			if(adsType=="flash")
			{
				arrFlashName[arrFlashName.length]=n;
				tempStr="showtime"+n;
				arrFlashShowTime[arrFlashShowTime.length]=document.getElementsByName(tempStr)[0].value;
			}
		}
	}
	if(arrPicName.length>0||arrVideoName.length>0||arrFlashName.length>0)
	return true;
	else return false ;
}

/**
* 预览广告信息
*/
function displayAds()
{

	//文字广告
	if(parseInt(form1.adsType.value)==2)
	{
		document.getElementById("adsAnnounce").style.display='';
		showFile();
		return false;
	}
	var firstFileUrl=getFirstFile();
	
	if(!firstFileUrl)
	{ 
		alert(document.getElementById('script.adsForSinglePreview.noAccessory').value); 
		window.close();
		return false;
	}
	if(firstFileUrl==null||firstFileUrl=="")
	{ 
		alert(document.getElementById('script.adsForSinglePreview.noAccessory').value); 
		window.close();
		return false;
	}
	if(!getAdsFile())
	{
		return false;
	}
	//if(arrFlashName.length > 0)
	//{
	//	document.getElementById('flashDiv').innerHTML = flashObjStr;
	//}	
	
	displayUUID++;
	isPreview=1;
	display(displayUUID);
}


/**显示控制*/
function display(checkedUUID)
{
	
	var timeOut=0;
	if(displayUUID!=checkedUUID)
	{
		return false;
	}

	//图片
	if(arrPicName.length!=0)
	{
		for(var i=0;i<arrPicName.length;i++)
		{
			setTimeout("displayPic('"+arrPicName[i]+"',"+displayUUID+")",timeOut*1000);
			timeOut+=parseInt(arrPicShowTime[i]);
		}

	}
	//FLASH
	if(arrFlashName.length)
	{
		for(var i=0;i<arrFlashName.length;i++)
		{
			setTimeout("displayFlash('"+arrFlashName[i]+"',"+displayUUID+")",timeOut*1000);
			timeOut+=parseInt(arrFlashShowTime[i]);
		}
	}
	
	//视频
	if(arrVideoName.length)
	{
		for(var i=0;i<arrVideoName.length;i++)
		{
			setTimeout("displayVideo('"+arrVideoName[i]+"',"+displayUUID+")",timeOut*1000);
			timeOut+=parseInt(arrVideoShowTime[i]);
		}
	}
	
	if(isPreview==1)
	{
		if(debug)alert("display()  end timeOut:"+timeOut);
		setTimeout("display("+displayUUID+")",timeOut*1000);
	}
}


/**图片显示控制*/

function displayPic(PicFileName,checkedUUID)
{
	if(isPreview==0||arrPicName.length == 0)
	{
		return false;
	}
	if(displayUUID!=checkedUUID)
	{	
		return false;
	}
	var fileStr="file"+PicFileName;
	if(debug)alert(PicFileName);
	document.getElementById('adsMaker').style.display='none';
	document.getElementById('player').style.display='none';
	document.getElementById('imgs').style.display='';
	document.getElementById('WindowsMediaPlayer1').url="";
	document.getElementById('flashPlayer').style.display='none';
	if(document.getElementById('flashPreview'))
	{
		document.getElementById('flashPreview').movie="";
	}
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

/**视频显示控制*/

function displayVideo(videoFileName,checkedUUID)
{
	if(isPreview==0||arrVideoName.length == 0)return false;
	if(displayUUID!=checkedUUID)return false;
	var fileStr="file"+videoFileName;
	document.getElementById('adsMaker').style.display='none';
	document.getElementById('player').style.display='';
	document.getElementById('imgs').style.display='none';
	document.getElementById('WindowsMediaPlayer1').url=document.getElementsByName(fileStr)[0].value;
	document.getElementById('flashPlayer').style.display='none';
	if(document.getElementById('flashPreview'))document.getElementById('flashPreview').movie="";
}

/**FLSAH显示控制*/

function displayFlash(flashFileName,checkedUUID)
{
	if(isPreview==0||arrFlashName.length == 0)return false;
	if(displayUUID!=checkedUUID)return false;
	var fileStr="file"+flashFileName;
	document.getElementById('adsMaker').style.display='none';
	document.getElementById('player').style.display='none';
	document.getElementById('imgs').style.display='none';
	document.getElementById('WindowsMediaPlayer1').url="";
	document.getElementById('flashPlayer').style.display='';
	if(debug)alert('displayFlash()');

	document.getElementById('flashDiv').innerHTML="<embed src="+document.getElementsByName(fileStr)[0].value+" quality=\"high\" showcontrols=false autostart=true loop=true type=\"application/x-shockwave-flash\" width=800 height=600 AllowScriptAccess=\"never\"></embed>";
	//alert(document.getElementById('flashDiv').innerHTML);
	//document.getElementById('flashPreview').movie=document.getElementsByName(fileStr)[0].value;
	//
}


/*---以前的预览程序，存在逻辑问题(2008.10.14)---*/
/**获取广告媒体文件*/
var changeFlag=true;
var arrMediaList = new Array();
var arrTypeList = new Array();
var arrTimeList = new Array();
function getAdsFile1()
{
	//清零
	arrMediaList.length = 0;
	arrTypeList.length = 0;
	arrTimeList.length = 0;
	
	//合法性校验	
	var isVF=isValidFile();
	if(isVF==2)
	{
				
		alert(document.getElementById('script.adsForSinglePreview.validFile').value);
		window.close();
		return false;
	}
	if(isVF==3)
	{
				
		alert(document.getElementById('script.inputPlayTimeError').value);
		window.close();
		return false;
	}
	var k=0;
	for(var n=0;n<row+1;n++)
	{
		var tempStr="file"+n;
		if(document.getElementsByName(tempStr)[0]&&document.getElementsByName(tempStr)[0].value.length!=0)
		{
			var adsType=distinguishAdsFile(document.getElementsByName(tempStr)[0].value);
			arrMediaList[k]=n;
			arrTypeList[k]=adsType;
			arrTimeList[k]=document.getElementsByName("showtime"+n)[0].value;
			k++;
		}
	}
	if(arrTypeList.length>0)
	return true;
	else return false ;
}
/**
* 预览广告信息
*/
function displayAds1()
{
	//文字广告
	if(parseInt(form1.adsType.value)==2)
	{
		document.getElementById("adsAnnounce").style.display='';
		showFile();
		return false;
	}
	var firstFileUrl=getFirstFile();
	
	if(!firstFileUrl)
	{ 
		alert(document.getElementById('script.adsForSinglePreview.noAccessory').value); 
		window.close();
		return false;
	}
	if(firstFileUrl==null||firstFileUrl=="")
	{ 
		alert(document.getElementById('script.adsForSinglePreview.noAccessory').value); 
		window.close();
		return false;
	}
	if(!getAdsFile1())
	{
		return false;
	}
	//if(arrFlashName.length > 0)
	//{
	//	document.getElementById('flashDiv').innerHTML = flashObjStr;
	//}	
	
	displayUUID++;
	isPreview=1;
	display1(displayUUID);
}


/**显示控制*/
function display1(checkedUUID)
{
	var timeOut=0;
	if(displayUUID!=checkedUUID)
	{
		return false;
	}

	//图片
	if(arrTypeList.length!=0)
	{
		for(var i=0;i<arrTypeList.length;i++)
		{
			if(arrTypeList[i]=="pic")
			{
				setTimeout("displayPic1('"+arrMediaList[i]+"',"+displayUUID+")",timeOut*1000);
			}
			else if(arrTypeList[i]=="flash")
			{
				setTimeout("displayFlash1('"+arrMediaList[i]+"',"+displayUUID+")",timeOut*1000);
			}
			else if(arrTypeList[i]=="video")
			{
				setTimeout("displayVideo1('"+arrMediaList[i]+"',"+displayUUID+")",timeOut*1000);
			}
			timeOut+=parseInt(arrTimeList[i]);
		}

	}
	if(isPreview==1)
	{
		if(debug)alert("display()  end timeOut:"+timeOut);
		setTimeout("display1("+displayUUID+")",timeOut*1000);
	}
}

/**图片显示控制*/

function displayPic1(PicFileName,checkedUUID)
{
	if(isPreview==0)
	{
		return false;
	}
	if(displayUUID!=checkedUUID)
	{	
		return false;
	}
	var fileStr="file"+PicFileName;
	if(debug)alert(PicFileName);
	document.getElementById('adsMaker').style.display='none';
	document.getElementById('player').style.display='none';
	document.getElementById('imgs').style.display='';
	document.getElementById('WindowsMediaPlayer1').url="";
	document.getElementById('flashPlayer').style.display='none';
	if(document.getElementById('flashPreview'))
	{
		document.getElementById('flashPreview').movie="";
	}
	if(changeFlag)
	{
		document.getElementById('showImgs').innerHTML="<img id=\"previewImgs\" alt=\""+document.getElementById('script.adsForSinglePreview.wait').value+"...\" src=\"\" style=\"width:800px; height:600px; overflow:scroll;filter: RevealTrans(Duration=2.0, Transition=23);\">";
		changeFlag=false;
	}
	//var imgObj= new Image();
	//imgObj.src = document.getElementsByName(fileStr)[0].value; 
	document.getElementById('previewImgs').filters.revealTrans.apply();
	document.getElementById('previewImgs').filters.revealTrans.Transition=23;
	//cnt++;
	//if(cnt>23)cnt=2;
	document.getElementById('previewImgs').src=document.getElementsByName(fileStr)[0].value;
	//document.getElementById('showImgs').innerHTML="<img id=\"previewImgs\" src=\""+imgObj.src+"\" style=\" width:800px; height:600px ; overflow:scroll;\">";
	//previewImgs.src=document.getElementsByName(tempStr)[0].value;
	 
	document.getElementById('previewImgs').filters.revealTrans.play();
}

/**视频显示控制*/

function displayVideo1(videoFileName,checkedUUID)
{
	if(isPreview==0)return false;
	if(displayUUID!=checkedUUID)return false;
	var fileStr="file"+videoFileName;
	document.getElementById('adsMaker').style.display='none';
	document.getElementById('player').style.display='';
	document.getElementById('imgs').style.display='none';
	document.getElementById('WindowsMediaPlayer1').url=document.getElementsByName(fileStr)[0].value;
	document.getElementById('flashPlayer').style.display='none';
	if(document.getElementById('flashPreview'))document.getElementById('flashPreview').movie="";
	changeFlag=true;
}

/**FLSAH显示控制*/

function displayFlash1(flashFileName,checkedUUID)
{
	if(isPreview==0)return false;
	if(displayUUID!=checkedUUID)return false;
	var fileStr="file"+flashFileName;
	document.getElementById('adsMaker').style.display='none';
	document.getElementById('player').style.display='none';
	document.getElementById('imgs').style.display='none';
	document.getElementById('WindowsMediaPlayer1').url="";
	document.getElementById('flashPlayer').style.display="";
	if(debug)alert('displayFlash()');

	document.getElementById('flashDiv').innerHTML="<embed src="+document.getElementsByName(fileStr)[0].value+" quality=\"high\" showcontrols=false autostart=true loop=true type=\"application/x-shockwave-flash\" width=800 height=600 AllowScriptAccess=\"never\"></embed>";
	changeFlag=true;
	//alert(document.getElementById('flashDiv').innerHTML);
	//document.getElementById('flashPreview').movie=document.getElementsByName(fileStr)[0].value;
	//
}
