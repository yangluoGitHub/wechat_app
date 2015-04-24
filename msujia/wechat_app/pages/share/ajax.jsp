<%@ page 
language="java"
contentType="text/html; charset=gbk"
pageEncoding="gbk"
%>


<script language="JavaScript">

function findType(){		
	var pars="devVendor="+$('vendor').value+"&"+new Date();
	var myAjax = new Ajax.Updater('typeValue','baseqry.do?action=findType',{method: 'get', parameters: pars,evalScripts:true});
}

function findDev(){		
	var pars="orgNo="+document.form1.org.value+"&devType="+document.form1.type.value+"&devVendor="+document.form1.vendor.value+"&"+new Date();
	var myAjax = new Ajax.Updater('subDevObj','baseqry.do?action=findDev',{method: 'get', parameters: pars,evalScripts:true});
}

function findAll(){		
    var pars_typeValue="devVendor="+$('vendor').value+"&"+new Date();
	var pars_subDevObj="orgNo="+document.form1.org.value+"&devType="+document.form1.type.value+"&devVendor="+document.form1.vendor.value+"&"+new Date();
	var myAjax = new Ajax.Updater('typeValue','baseqry.do?action=findType',{method: 'get', parameters: pars_typeValue,evalScripts:true});
    var myAjax_1 = new Ajax.Updater('subDevObj','baseqry.do?action=findDev',{method: 'get', parameters: pars_subDevObj,evalScripts:true});
}

function findType_card(){		
	var pars="devVendor="+$('vendorNo').value+"&"+new Date();
	var myAjax = new Ajax.Updater('typeValue','baseqry.do?action=findType_card',{method: 'get', parameters: pars,evalScripts:true});
}

function findDev_crt(){		
	var pars="orgNo="+document.form1.org.value+"&devType="+document.form1.type.value+"&devVendor="+document.form1.vendor.value+"&"+new Date();
	var myAjax = new Ajax.Updater('subDevObj','baseqry.do?action=findDev_crt',{method: 'get', parameters: pars,evalScripts:true});
}

function findAll_crt(){		
    var pars_typeValue="devVendor="+$('vendor').value+"&"+new Date();
	var pars_subDevObj="orgNo="+document.form1.org.value+"&devType="+document.form1.type.value+"&devVendor="+document.form1.vendor.value+"&"+new Date();
	var myAjax = new Ajax.Updater('typeValue','baseqry.do?action=findType_crt',{method: 'get', parameters: pars_typeValue,evalScripts:true});
    var myAjax_1 = new Ajax.Updater('subDevObj','baseqry.do?action=findDev_crt',{method: 'get', parameters: pars_subDevObj,evalScripts:true});
}

function findDev(srcSelList){		
	var pars="orgNo="+document.form1.org.value+"&devType="+document.form1.type.value+"&devVendor="+document.form1.vendor.value+"&srcSelList="+srcSelList+"&"+new Date();
	var myAjax = new Ajax.Updater('subDevObj','baseqry.do?action=findDev',{method: 'get', parameters: pars,evalScripts:true});
}
</script>

