document.write('<iframe id="orgFrame" name="orgFrame"  frameborder=0 src=../pages/share/wait.jsp? style=display:none;position:absolute;z-index:100></iframe>');
document.onclick=hideOrgSelector;
//onblur="hideOrgSelector()"

function showOrgSelector(sImg, noId, nameId)
{
	var cf=document.getElementById("orgFrame");
	if(cf.style.display=="block"){
		cf.style.display="none";
		return;
	}
	var wcf=window.frames.orgFrame;
	var oImg=document.getElementById(sImg);
	
	cf.src='station.do?action=orgSelect&imgId='+sImg + '&noId=' + noId + '&nameId=' + nameId;
	if(!oImg){alert(document.getElementById('script.controlObjectError').value);return;}

	if(!wcf.bCalLoaded){alert(document.getElementById('script.orgSelector.refresh').value);return;}
	
	var eT=-1,eL=-1,p=oImg;
	if(document.body.scrollTop!=0 || (document.documentElement.scrollTop==0 && document.documentElement.clientHeight==0))
	{
		var sT=document.body.scrollTop,sL=document.body.scrollLeft;
		var eH=oImg.height,eW=oImg.width;
		while(p&&p.tagName!="BODY"){eT+=p.offsetTop;eL+=p.offsetLeft;p=p.offsetParent;}
		cf.style.top=eT+eH + "px";
		cf.style.left=((document.body.clientWidth-(eL-sL)>=cf.width)?eL:eL+eW-cf.width) + "px";	
	}
	else
	{
		var sT=document.documentElement.scrollTop,sL=document.documentElement.scrollLeft;
		var eH=oImg.height,eW=oImg.width;
		while(p&&p.tagName!="BODY"){eT+=p.offsetTop;eL+=p.offsetLeft;p=p.offsetParent;}
		cf.style.top=eT+eH + "px";
		cf.style.left=((document.documentElement.clientWidth-(eL-sL)>=cf.width)?eL:eL+eW-cf.width) + "px";	
	}	
	cf.style.display="block";
	cf.contentWindow.focus();
}

function showOrgSelector_byOrgType(sImg,orgTypeId)
{
	
	if(orgTypeId==""){alert(document.getElementById('System.select_org_type').value);return;}
	var cf=document.getElementById("orgFrame");
	
	var wcf=window.frames.orgFrame;
	var oImg=document.getElementById(sImg);
	//cf.src='../pages/share/orgSelector.jsp?orgTypeId='+orgTypeId+'&oImg='+sImg;
	cf.src='org.do?action=orgSelect&orgTypeId='+orgTypeId+'&oImg='+sImg;
	//alert(cf.src)
	if(!oImg){alert(document.getElementById('script.controlObjectError').value);return;}

	if(!wcf.bCalLoaded){alert(document.getElementById('script.orgSelector.refresh').value);return;}
	if(cf.style.display=="block")
	{
	cf.style.display="none";
	return;
	}
	
	var eT=-1,eL=-1,p=oImg;
	if(document.body.scrollTop!=0 || (document.documentElement.scrollTop==0 && document.documentElement.clientHeight==0))
	{
		var sT=document.body.scrollTop,sL=document.body.scrollLeft;
		var eH=oImg.height,eW=oImg.width;
		while(p&&p.tagName!="BODY"){eT+=p.offsetTop;eL+=p.offsetLeft;p=p.offsetParent;}
		cf.style.top=eT+eH + "px";
		//cf.style.top=((document.body.clientHeight-(eT-sT)-eH>=cf.height)?eT+eH:eT-cf.height) + "px";
		cf.style.left=((document.body.clientWidth-(eL-sL)>=cf.width)?eL:eL+eW-cf.width) + "px";	
	}
	else
	{
		var sT=document.documentElement.scrollTop,sL=document.documentElement.scrollLeft;
		var eH=oImg.height,eW=oImg.width;
		while(p&&p.tagName!="BODY"){eT+=p.offsetTop;eL+=p.offsetLeft;p=p.offsetParent;}
		cf.style.top=eT+eH + "px";
		//cf.style.top=((document.documentElement.clientHeight-(eT-sT)-eH>=cf.height)?eT+eH:eT-cf.height) + "px";
		cf.style.left=((document.documentElement.clientWidth-(eL-sL)>=cf.width)?eL:eL+eW-cf.width) + "px";	
	}	
	cf.style.display="block";

}

function showOrgSelectorByOrgNo(sImg,orgNo)
{
	var cf=document.getElementById("orgFrame");
	
	var wcf=window.frames.orgFrame;
	var oImg=document.getElementById(sImg);
	//cf.src='../pages/share/orgSelector.jsp?orgTypeId=1&oImg='+sImg;
	cf.src='org.do?action=orgSelect&orgTypeId=1&oImg='+sImg+ '&orgNo=' + orgNo;
	if(!oImg){alert(document.getElementById('script.controlObjectError').value);return;}

	if(!wcf.bCalLoaded){alert(document.getElementById('script.orgSelector.refresh').value);return;}
	if(cf.style.display=="block")
	{
      cf.style.display="none";
	  return;
	}
	
	var eT=-1,eL=-1,p=oImg;
	if(document.body.scrollTop!=0 || (document.documentElement.scrollTop==0 && document.documentElement.clientHeight==0))
	{
		var sT=document.body.scrollTop,sL=document.body.scrollLeft;
		var eH=oImg.height,eW=oImg.width;
		while(p&&p.tagName!="BODY"){eT+=p.offsetTop;eL+=p.offsetLeft;p=p.offsetParent;}
		cf.style.top=eT+eH + "px";
		//cf.style.top=((document.body.clientHeight-(eT-sT)-eH>=cf.height)?eT+eH:eT-cf.height) + "px";
		cf.style.left=((document.body.clientWidth-(eL-sL)>=cf.width)?eL:eL+eW-cf.width) + "px";	
	}
	else
	{
		var sT=document.documentElement.scrollTop,sL=document.documentElement.scrollLeft;
		var eH=oImg.height,eW=oImg.width;
		while(p&&p.tagName!="BODY"){eT+=p.offsetTop;eL+=p.offsetLeft;p=p.offsetParent;}
		cf.style.top=eT+eH + "px";
		//cf.style.top=((document.documentElement.clientHeight-(eT-sT)-eH>=cf.height)?eT+eH:eT-cf.height) + "px";
		cf.style.left=((document.documentElement.clientWidth-(eL-sL)>=cf.width)?eL:eL+eW-cf.width) + "px";	
	}	
	cf.style.display="block";

}

function showOrgSelector(sImg)
{
	
	var cf=document.getElementById("orgFrame");
	
	var wcf=window.frames.orgFrame;
	var oImg=document.getElementById(sImg);
	//cf.src='../pages/share/orgSelector.jsp?orgTypeId=1&oImg='+sImg;
	cf.src='org.do?action=orgSelect&orgTypeId=1&oImg='+sImg;
	if(!oImg){alert(document.getElementById('script.controlObjectError').value);return;}

	if(!wcf.bCalLoaded){alert(document.getElementById('script.orgSelector.refresh').value);return;}
	if(cf.style.display=="block")
	{
      cf.style.display="none";
	  return;
	}
	
	var eT=-1,eL=-1,p=oImg;
	if(document.body.scrollTop!=0 || (document.documentElement.scrollTop==0 && document.documentElement.clientHeight==0))
	{
		var sT=document.body.scrollTop,sL=document.body.scrollLeft;
		var eH=oImg.height,eW=oImg.width;
		while(p&&p.tagName!="BODY"){eT+=p.offsetTop;eL+=p.offsetLeft;p=p.offsetParent;}
		cf.style.top=eT+eH + "px";
		//cf.style.top=((document.body.clientHeight-(eT-sT)-eH>=cf.height)?eT+eH:eT-cf.height) + "px";
		cf.style.left=((document.body.clientWidth-(eL-sL)>=cf.width)?eL:eL+eW-cf.width) + "px";	
	}
	else
	{
		var sT=document.documentElement.scrollTop,sL=document.documentElement.scrollLeft;
		var eH=oImg.height,eW=oImg.width;
		while(p&&p.tagName!="BODY"){eT+=p.offsetTop;eL+=p.offsetLeft;p=p.offsetParent;}
		cf.style.top=eT+eH + "px";
		//cf.style.top=((document.documentElement.clientHeight-(eT-sT)-eH>=cf.height)?eT+eH:eT-cf.height) + "px";
		cf.style.left=((document.documentElement.clientWidth-(eL-sL)>=cf.width)?eL:eL+eW-cf.width) + "px";	
	}	
	cf.style.display="block";

}

function hideOrgSelector()
{
	var cf=document.getElementById("orgFrame");
	cf.style.display="none";
}

function setStation	(no ,name, imgId, noId, nameId ){
	if (typeof jQuery != 'undefined') {    //如果有jQuery
		//如果使用该函数的时候没有提供noId或nameId，或者两个ID不是惟一的，则视作全部在一个TD内
		if (noId == 'undefined' || nameId == 'undefined' ) {    
			var $Td = jQuery("#"+imgId).parents("td:first");  
			$Td.find(":hidden").val(no);
			$Td.find(":text").val(name);
		} else {
			jQuery("#"+noId).val(no);
			jQuery("#"+nameId).val(name);
		}
		jQuery("#orgFrame").hide('fast');
	} else {    //如果没有jQury，必须要有noId,nameId
		document.getElementById(noId).value = no;
		document.getElementById(nameId).value = name;
		document.getElementById("orgFrame").style.display="none";
	}
}