<%@ page contentType="text/html; charset=gbk"%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
		<title></title>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type='text/javascript' src='../dwr/interface/matrixService.js'></script>
		<script language="JavaScript" src="../scripts/jquery.js" type="text/javascript"></script>
		<script language="JavaScript" src="../scripts/json2.js" type="text/javascript"></script>
		<script language="JavaScript" src="../scripts/common.js" type="text/javascript"></script>
		<script language="JavaScript" src="../scripts/map.js" type="text/javascript"></script>
		<link href="../styles/common.css" rel="stylesheet" type="text/css">
		<link href="../styles/map.css" rel="stylesheet" type="text/css">
	</head>
	
	<script language="JavaScript">
	//��ʼ��
	$().ready(function(){	
		$(":button").addClass("button");
							
		initMap(); //--��ͼ
		loadClrCenter(); //--�������
		if($("#hiddenOrgNo").val()!=null && $("#hiddenOrgNo").val()!=""){
			loadNetpoints(); //--����
		}
		
		// �����ͼ��ȡ��γ��
		sogou.maps.event.addListener(myMap, "click", function(event){
			// clickPoint && clickX && clickY
			var clickPoint = event.point;
			var clickX = clickPoint.x;
			var clickY = clickPoint.y;
			// ����γ����ʾ��ҳ����
			xyDisplay(clickPoint,clickX,clickY); 
		});
	});
	
	//��ͼ��ʼ��
	function initMap(){
		$("#mapContainer").empty();
		try{defInit("mapContainer",null,null);}catch(e){addExcepInfo("��ͼ����ʧ��");};
	}
	
	//�����������
	function loadClrCenter(){
		var clrCenterNo = $("#hiddenClrCenterNo").val();
		DWREngine.setAsync(false);
		matrixService.getClrCenter(clrCenterNo, function(data){
			if(data==null){addExcepInfo("��ѯ��������쳣");return;}
			var no=data[0]; var name=data[1]; var x=data[2]; var y=data[3];
			if(inChina4GaussProj(x,y)==false){addExcepInfo("[�������]"+name+":��γ�ȷǷ�");return;}
			// ��ͼ���
			if(myMap==null){return;}
			var curPoint = new sogou.maps.Point(x,y);
			myMap.panTo(curPoint);
			var id = "marker0"+no;
			var title = "[�������]"+name+"<br/>"+"����:"+x+"<br/>"+"γ��:"+y;
			var icon  = new sogou.maps.MarkerImage("../images/map/flag.png",
				new sogou.maps.Size(20,32),new sogou.maps.Point(0,0),new sogou.maps.Point(0,32),new sogou.maps.Size(20,32));
			var shadow = new sogou.maps.MarkerImage("../images/map/flag_shadow.png",
				new sogou.maps.Size(37, 32),new sogou.maps.Point(0,0),new sogou.maps.Point(0,32));
			var shape={coord: [1,1,1,20,18,20,18,1], type:"poly"};
			var marker = new sogou.maps.Marker({id:id, map:myMap, position:curPoint, icon:icon, shadow:shadow, shape:shape, title:title, visible:true});
		});
		DWREngine.setAsync(true);
	}

	//��������
	function loadNetpoints(){
		var clrCenterNo = $("#hiddenClrCenterNo").val();
		var orgNo = $("#hiddenOrgNo").val();
		DWREngine.setAsync(false);
		matrixService.getNetpointsOfClrCenter(clrCenterNo,orgNo, function(data){
			if(data==null){addExcepInfo("��ѯ�����쳣");return;}
			var item,no,name,x,y;
			var curPoint,id,title,marker;
			for(var i=0; i<data.length; i++){
				item=data[i]; no=item[0]; name=item[1]; x=item[2]; y=item[3];
				if(inChina4GaussProj(x,y)==false){addExcepInfo("[����]"+name+":��γ�ȷǷ�");continue;;}
				// ��ͼ���
				if(myMap==null){continue;}
				curPoint = new sogou.maps.Point(x,y);
				id = "marker1"+no;
				title = "[����]"+name+"<br/>"+"����:"+x+"<br/>"+"γ��:"+y;
				marker = new sogou.maps.Marker({id:id, map:myMap, position:curPoint, title:title, visible:true});
			}
		});
		DWREngine.setAsync(true);
	}
	
	//����쳣��Ϣ
	function addExcepInfo(excepInfo){
		$("#excepInfoDiv").append(
			$("<div></div>").html(excepInfo).addClass("mapFieldSetInnerDiv")
				.mouseenter(function(){$(this).addClass("onmouseover");}).mouseleave(function(){$(this).removeClass("onmouseover");})
		);
	}
	
	// ����γ����ʾ��ҳ����
	var xyMarker = null;
	function xyDisplay(clickPoint,clickX,clickY){
		// xyMarker
		if(xyMarker==null){
			var title = "";
			xyMarker = new sogou.maps.Marker({id:"xyMarker", map:myMap, position:clickPoint, title:title, visible:true});
		}else{
			xyMarker.setPosition(clickPoint);
		}
		// clickX && clickY span
		$("#clickX").html(clickX);
		$("#clickY").html(clickY);
	}

	//ȷ�Ͼ�γ��ֵ������дҳ��
	function confirmXY(){
		var x = $("#clickX").html(); //����
		var y = $("#clickY").html(); //γ��
		window.opener.document.getElementById("x").value=x;
		window.opener.document.getElementById("y").value=y;
		window.close();
	}
	
	</script>
	
	<body>
	
		<!-- default -->
		<div>
			<input type="hidden" id="hiddenOrgNo" name="hiddenOrgNo" value="${orgNo}"/>
			<input type="hidden" id="hiddenClrCenterNo" name="hiddenClrCenterNo" value="${clrCenterNo}"/>
		</div>
		
		<!-- title -->
		<table width="100%" style="border: 0px;">
			<tr>
				<td nowrap><b>�����ͼ��ȡ��γ��</b></td>
				<td nowrap align="right"><input type="button" value="�ر�" onclick="javascript:window.close();"></td>
			</tr>
		</table>
		
		<!-- mapContainer -->
  		<div id="mapContainer" style="float:left;width:73%;height:95%;"></div>
  		
  		<!-- xyContainer -->
  		<div id="xyContainer" style="float:right;width:25%;height:95%;">
  			<fieldset style="width:100%;height:50%;">
  				<legend><b>��γ����Ϣ</b></legend>
  				<div class="mapFieldSetDiv">
  					<div class="mapFieldSetInnerDiv" style="cursor:auto">���ȣ�<span id="clickX"></span></div>
  					<div class="mapFieldSetInnerDiv" style="cursor:auto">γ�ȣ�<span id="clickY"></span></div>
  					<div class="mapFieldSetInnerDiv" style="cursor:auto" align="center">
  						<input type="button" value="ȷ��" onclick="javascript:confirmXY();"/>
  					</div>
  				</div>
  			</fieldset>
  			<br>
  			<fieldset style="width:100%;height:45%;">
  				<legend><b>�쳣��Ϣ</b></legend>
  				<div id="excepInfoDiv" class="mapFieldSetDiv"></div>
  			</fieldset>
  		</div>
		
	</body>

</html>