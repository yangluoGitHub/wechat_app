/**
* ������̿���
* @author - hjtang
* @since 2007.07.19
*/



/**
*��ȡϵͳ���Ѿ����ڵ��������	
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
* ɾ�������������
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
* �����ɹ���ʧ����ʾ��Ϣ��ʾ
*/	
function updateDateResult(originalRequest)
{
	alert(originalRequest.responseText);
}
	
/**
*�����������
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
* ��ӵ����������
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
*ȡ������(ȫ���ȴ��忨�����׵ȴ�������)��������
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