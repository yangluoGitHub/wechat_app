document.write('<iframe id=CalFrame name=CalFrame frameborder=0 src=../pages/calendar.jsp style=display:none;position:absolute;z-index:100></iframe>');
document.onclick=hideCalendar;

function showCalendar(sImg,bOpenBound,sFld1,sFld2,sCallback)
{
	var fld1,fld2;
	var cf=document.getElementById("CalFrame");
	var wcf=window.frames.CalFrame;
	var oImg=document.getElementById(sImg);
	if(!oImg){alert(document.getElementById('script.controlObjectError').value);return;}
	if(!sFld1){alert(document.getElementById('script.calendar.notAssign').value);return;}
	fld1=document.getElementById(sFld1);
	if(!fld1){alert(document.getElementById('script.calendar.inputNotExsit').value);return;}
	if(fld1.tagName!="INPUT"||fld1.type!="text"){alert(document.getElementById('script.calendar.typeError').value);return;}
	if(sFld2)
	{
		fld2=document.getElementById(sFld2);
		if(!fld2){alert(document.getElementById('script.calendar.referenceNotExsit').value);return;}
		if(fld2.tagName!="INPUT"||fld2.type!="text"){alert(document.getElementById('script.calendar.referenceTypeError').value);return;}
	}
	if(!wcf.bCalLoaded){alert(document.getElementById('script.calendar.loadFailed').value);return;}
	if(cf.style.display=="block"){cf.style.display="none";return;}
	
	var eT=-1,eL=-1,p=oImg;
	if(document.body.scrollTop!=0 || (document.documentElement.scrollTop==0 && document.documentElement.clientHeight==0))
	{
		var sT=document.body.scrollTop,sL=document.body.scrollLeft;
		var eH=oImg.height,eW=oImg.width;
		while(p&&p.tagName!="BODY"){eT+=p.offsetTop;eL+=p.offsetLeft;p=p.offsetParent;}
		cf.style.top=((document.body.clientHeight-(eT-sT)-eH>=cf.height)?eT+eH:eT-cf.height) + "px";
		cf.style.left=((document.body.clientWidth-(eL-sL)>=cf.width)?eL:eL+eW-cf.width) + "px";	
	}
	else
	{
		var sT=document.documentElement.scrollTop,sL=document.documentElement.scrollLeft;
		var eH=oImg.height,eW=oImg.width;
		while(p&&p.tagName!="BODY"){eT+=p.offsetTop;eL+=p.offsetLeft;p=p.offsetParent;}
		cf.style.top=((document.documentElement.clientHeight-(eT-sT)-eH>=cf.height)?eT+eH:eT-cf.height) + "px";
		cf.style.left=((document.documentElement.clientWidth-(eL-sL)>=cf.width)?eL:eL+eW-cf.width) + "px";	
	}	
	cf.style.display="block";
	
	wcf.openbound=bOpenBound;
	wcf.fld1=fld1;
	wcf.fld2=fld2;
	wcf.callback=sCallback;
	wcf.initCalendar();
}
function hideCalendar()
{
	var cf=document.getElementById("CalFrame");
	cf.style.display="none";
}