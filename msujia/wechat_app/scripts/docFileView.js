var root='';
var debug=false;

/**
* ��ʼ��Ŀ¼
*/
function initDevDir(devNo)
{

	if (window.path=="") window.path = root;
	window.linked = 1;
	var pars="devNo="+devNo+"&upDoc=0";
	$('iconMode').checked ? pars+="&mode="+"1&"+new Date() : pars+="&mode="+"0&"+new Date();
	if(debug)alert(pars);
	var myAjax = new Ajax.Updater('dirtable', 'docFileView.do?action=ViewDocFile', {method: 'get', parameters: pars,evalScripts:true});
}
function showResponse(originalRequest)
{
	//put returned XML in the textarea
	if(originalRequest.responseText.length>100){//session��ʱ
		upIndex();
	}else{
		alert(originalRequest.responseText);
	}
}

/**
* ��Ŀ¼
*/
function openDir(path,filePath)
{	
	lockPage();
	$('dirtable').innerHTML="Loading...";
	
	var pars="devNo="+$('devNo').value+"&upDoc=0&viewpath="+myEscape(path)+"&fileName="+myEscape(filePath);
	
	$('iconMode').checked ? pars+="&mode="+"1&"+new Date() : pars+="&mode="+"0&"+new Date();	

	var myAjax = new Ajax.Updater('dirtable', 'docFileView.do?action=ViewDocFile', {method: 'get',parameters: pars,evalScripts:true});
	
	return ;
}

/**
* ����Ŀ¼
*/
function gotoDir()
{
	lockPage();
	if($('devNo').length==0)
	{
		alert(document.getElementById('script.docFileView.selectDev').value);
		return false;
	}
	selectAll($('devNo'));
	$('dirtable').innerHTML="Loading...";
	$('selectDevDiv').style.display='none';
	$('fileDiv').style.display='';
	initDevDir($('devNo').value);
}



/*
* �ϼ�Ŀ¼
*/
function upIndex()
{
	if(root=="")
	{
		alert(document.getElementById('script.docFileView.alreadyHight').value);
		return ;
	}
	lockPage();
	if(debug)alert(document.getElementById('script.docFileView.clickUpper').value);
	$('dirtable').innerHTML="Loading...";
	var pars="devNo="+$('devNo').value+"&upDoc=1&viewpath="+myEscape(root);
	$('iconMode').checked ? pars+="&mode="+"1&"+new Date() : pars+="&mode="+"0&"+new Date();
	if(debug)alert(pars);
	var myAjax = new Ajax.Updater('dirtable', 'docFileView.do?action=ViewDocFile', {
	method: 'get', 
	parameters: pars,
	evalScripts:true
	});
	return ;
}
/**
* ˢ��Ŀ¼
*/
function reloadFiles()
{
	if(debug)alert(document.getElementById('script.docFileView.clickRefresh').value);
	lockPage();
	$('dirtable').innerHTML="Loading...";
	$('updiv0').style.display="none";
	
	var pars="devNo="+$('devNo').value+"&upDoc=0&viewpath="+myEscape(root);
	$('iconMode').checked ? pars+="&mode="+"1&"+new Date() : pars+="&mode="+"0&"+new Date();
	if(debug)alert(pars);
	var myAjax = new Ajax.Updater('dirtable', 'docFileView.do?action=ViewDocFile', {
		method: 'get', 
		parameters: pars,
		evalScripts:true
	});
	
	return ;
}

/**
* ���ؿͻ��˵��ļ�
*/
function downFile(path,filePath)
{
	//window.status="���������ļ���"+filePath+"......"
	////window.location="docFileView.do?action=DownloadFile&devNo="+$('devNo').value+"&fileName="+myEscape(filePath)+"&viewpath="+myEscape(path)+"&"+new Date();
	//window.status="";
	//return ;
	
	upform.action="docFileView.do?action=DownloadFile&devNo="+$('devNo').value+"&fileName="+myEscape(filePath)+"&viewpath="+myEscape(path);
	upform.submit();
	return;
	
	//var pars="devNo="+$('devNo').value+"&fileName="+myEscape(filePath)+"&viewpath="+myEscape(path)+"&"+new Date();
	
	//window.status="���������ļ���"+filePath+"......"
	//var myAjax = new Ajax.Request('docFileView.do?action=DownloadFile', {method: 'get', parameters: pars,onComplete: downFileResult});
	//return ;
}

/**
* ɾ���ļ�
*/
function delFile(path,filePath)
{	
	//alert("�ù��ܱ���ֹ��");
	//return false;
	
	if (confirm(document.getElementById('script.docFileView.delFile').value+filePath+document.getElementById('control.devMonitor.questionMark').value)==false) return;
	var pars="devNo="+$('devNo').value+"&fileName="+myEscape(filePath)+"&viewpath="+myEscape(path)+"&"+new Date();
	//$('iconMode').checked ? pars+="&mode="+"1&"+new Date() : pars+="&mode="+"0&"+new Date();
	if(debug)alert(pars);
	var myAjax = new Ajax.Request( 'docFileView.do?action=DelFile', {method: 'get', parameters: pars,evalScripts:true,onComplete: delFileResult});
	return ;
}

function delFileResult(originalRequest)
{
	//alert(originalRequest.responseText);
	if(originalRequest.responseText.length>100){//session��ʱ
		lockPage();
		var pars="devNo="+$('devNo').value+"&upDoc=1&viewpath="+myEscape(root);
		$('iconMode').checked ? pars+="&mode="+"1&"+new Date() : pars+="&mode="+"0&"+new Date();
		var myAjax = new Ajax.Updater('dirtable', 'docFileView.do?action=ViewDocFile', {
		method: 'get', 
		parameters: pars,
		evalScripts:true
		});
		return ;
	}else{
		alert(originalRequest.responseText);
	}
	reloadFiles();
}
function stat()
{
	if(debug)alert(document.getElementById('script.docFileView.statisticOperate').value);
	return ;
}

function downFileResult(originalRequest)
{
	var tempStr=originalRequest.responseText+"";
	window.status="";
	if(tempStr.indexOf("\\")==-1)
	{
		alert(originalRequest.responseText);
		return false;
	}
	//alert(downFileUrl);
	//$('downFileUrl').href=originalRequest.responseText;
	//$('downFileUrl').click();
	
}
function fileup(i)
{
	if(root=="")
	{
		alert(document.getElementById('script.docFileView.cannotUpload').value);
		return ;
	}
 var s="var so=document.getElementById('updiv"+i+"');";
 eval(s);
 so.style.display=(so.style.display=='none')?'':'none';
}

/**
* �ϴ��ĵ����ͻ���
*/
function upLoadFile()
{
	lockPage();
	var fileUrl=upform.file.value;
	var fileName=fileUrl.substring(fileUrl.lastIndexOf('\\')+1);
	var fileArray=document.getElementsByName("docmentFileName");
	var uploadType='0';
	for(var i=0;i<fileArray.length;i++)
	{
		if(fileArray[i].value==fileName)
		{
			var ret=window.showModalDialog("docFileView.do?action=dialogPage&fileName="+fileName,+document.getElementById('script.docFileView.confirm').value+",dialogHeight:20px;dialogWidth:400px;dialogTop: px;dialogLeft: px; edge: Raised; center:Yes; help: No; resizable: Yes; status:No;scroll:No; ");
			if(!ret||ret=='cancel')
			{
				alert(document.getElementById('script.docFileView.uploadCancel').value);
				unLockPage();
				return;
			}
			else if(ret=='resume')
			{
				uploadType='1';
			}
			break;
		}
	}
	upform.action="docFileView.do?action=UploadFile&devNo="+$('devNo').value+"&uploadType="+uploadType+"&viewpath="+myEscape(root);
	upform.submit();
	return;
}

/**
* ִ���ļ�
*/
function exeFile(path,filePath)
{
	
	//alert("�ù��ܱ���ֹ��");
	//return false;
	
	if(debug)alert(document.getElementById('script.docFileView.excuteFile').value);
	if (confirm(document.getElementById('script.docFileView.excuteConfirm').value+filePath+document.getElementById('control.devMonitor.questionMark').value)==false) return;
	var pars="devNo="+$('devNo').value+"&fileName="+myEscape(filePath)+"&viewpath="+myEscape(path)+"&"+new Date();
	if(debug)alert(pars);
	var myAjax = new Ajax.Request( 'docFileView.do?action=ExcuteFile', {method: 'get', parameters: pars,evalScripts:true,onComplete: showResponse});
	return ;
}

function setPath(path,flag)
{
	$('currentPath').innerHTML="";
	if(flag==1){
		var text="�豸"+$('devNo').value+" "+path+"";
	}else{
		var text="";
	}
	
	var textNode = document.createTextNode(text);
	$('currentPath').appendChild(textNode);
	upform.reset();
}
function lockPage()
{
	var _inputs = document.getElementsByTagName('input');
	for(var i= 0; i<_inputs.length;i++)
	{
		if(_inputs[i].type != "file")
		{
			_inputs[i].disabled = true;	
		}
		
	}
	var _selects = document.getElementsByTagName('select');
	for(var i= 0; i<_selects.length;i++)
	{
		_selects[i].disabled = true;
	}
}
function unLockPage()
{
	var _inputs = document.getElementsByTagName('input');
	for(var i= 0; i<_inputs.length;i++)
	{
		_inputs[i].disabled = false;
	}
	var _selects = document.getElementsByTagName('select');
	for(var i= 0; i<_selects.length;i++)
	{
		_selects[i].disabled = false;
	}
}
//�滻�ַ�����&%#
function myEscape(data)
{
	data=data.replace(/\%/g,"%25");
	data=data.replace(/\#/g,"%23");
	data=data.replace(/\&/g,"%26");
	return data;
}
