/**
* 审核流程控制
* @author - hjtang
* @since 2007.07.19
*/



/**
*获取系统中已经存在的审核流程	
*/
function loadCatalogClue(clue)
{	
	if($('orgNo').value=="")
	{	
		$('approvalCatalogClue'+clue).style.display='none' ;
		return false;
	}
	else
	{		
		$('approvalCatalogClue'+clue).style.display='block';
	}
	$('catalogClue'+clue).innerHTML="<DIV id=filetable><tr class=tr1>Loading...</tr></DIV>";
	var pars="orgNo="+$('orgNo').value+"&approvalCatalog="+clue+"&"+new Date();
	var myAjax = new Ajax.Updater('catalogClue'+clue,'audit.do?action=getExistAuditClue',{method: 'get', parameters: pars,evalScripts:true});
}

/**
* 删除单步审核流程
*/
function deleteCatalogClue(logicId,clue)
{
  if(!confirm(document.getElementById('Main.sure_delete').value))
  {
     return false;
  }	
  var pars="logicId="+logicId+"&"+new Date();		
  var myAjax = new Ajax.Request('audit.do?action=del',{method: 'get', parameters: pars,onComplete: updateDateResult	});		
  setTimeout("loadCatalogClue("+clue+")",100);
}

/**
* 操作成功、失败提示信息显示
*/	
function updateDateResult(originalRequest)
{
	alert(originalRequest.responseText);
}
	
/**
*更新审核流程
*/	
function updateCatalogClue(logicId,index,clue)
{	
	if($('orgNo').value==""||$('approvalOrg'+clue+index).value==""||$('approvalRoler'+clue+index).value=="")
	{
		alert(document.getElementById('script.selectDepartment').value);
		return false;
	}	
	
	var pars="logicId="+logicId+"&subNo="+$('subNo'+clue+index).value+"&approvalOrg="+$('approvalOrg'+clue+index).value+"&approvalRoler="+$('approvalRoler'+clue+index).value+"&"+new Date();
		
	var myAjax = new Ajax.Request('audit.do?action=update',{method: 'get', parameters: pars, onComplete: updateDateResult});
  		
  	setTimeout("loadCatalogClue("+clue+")",100);
}

/**
* 添加单步审核流程
*/
function addCatalogClue(clue)
{	
	if($('orgNo').value==""||$('approvalOrg'+clue).value==""||$('approvalRoler'+clue).value=="")
	{
		alert(document.getElementById('script.selectDepartment').value);
		return false;
	}	
	
	var pars="orgNo="+$('orgNo').value+"&approvalCatalog="+clue+"&approvalOrg="+$("approvalOrg"+clue).value+"&roleNo="+$("approvalRoler"+clue).value+"&subNo="+$('subNo'+clue).value+"&"+new Date();

	var myAjax = new Ajax.Request('audit.do?action=add',{method: 'get',parameters: pars,onComplete: updateDateResult});		
	
	setTimeout("loadCatalogClue("+clue+")",100);
		
}

/*
*取得三类(全屏等待插卡、交易等待、文字)广告的流程
*/
function loadAllApproval()
{
	if($('orgNo').value=="")
	{
		$('approvalCatalogClue0').style.display='none' ;
		$('approvalCatalogClue1').style.display='none' ;
		$('approvalCatalogClue2').style.display='none' ;
		return false;
	}
	loadCatalogClue(0);
	loadCatalogClue(1);
	loadCatalogClue(2);
	return true;
}