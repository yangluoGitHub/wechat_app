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
	//初始化
	$().ready(function(){	
		$(":button").addClass("button");
							
		initMap(); //--地图
		loadClrCenter(); //--清机中心
		if($("#hiddenOrgNo").val()!=null && $("#hiddenOrgNo").val()!=""){
			loadNetpoints(); //--网点
		}
		
		// 点击地图获取经纬度
		sogou.maps.event.addListener(myMap, "click", function(event){
			// clickPoint && clickX && clickY
			var clickPoint = event.point;
			var clickX = clickPoint.x;
			var clickY = clickPoint.y;
			// 将经纬度显示到页面上
			xyDisplay(clickPoint,clickX,clickY); 
		});
	});
	
	//地图初始化
	function initMap(){
		$("#mapContainer").empty();
		try{defInit("mapContainer",null,null);}catch(e){addExcepInfo("地图加载失败");};
	}
	
	//加载清机中心
	function loadClrCenter(){
		var clrCenterNo = $("#hiddenClrCenterNo").val();
		DWREngine.setAsync(false);
		matrixService.getClrCenter(clrCenterNo, function(data){
			if(data==null){addExcepInfo("查询清机中心异常");return;}
			var no=data[0]; var name=data[1]; var x=data[2]; var y=data[3];
			if(inChina4GaussProj(x,y)==false){addExcepInfo("[清机中心]"+name+":经纬度非法");return;}
			// 地图相关
			if(myMap==null){return;}
			var curPoint = new sogou.maps.Point(x,y);
			myMap.panTo(curPoint);
			var id = "marker0"+no;
			var title = "[清机中心]"+name+"<br/>"+"经度:"+x+"<br/>"+"纬度:"+y;
			var icon  = new sogou.maps.MarkerImage("../images/map/flag.png",
				new sogou.maps.Size(20,32),new sogou.maps.Point(0,0),new sogou.maps.Point(0,32),new sogou.maps.Size(20,32));
			var shadow = new sogou.maps.MarkerImage("../images/map/flag_shadow.png",
				new sogou.maps.Size(37, 32),new sogou.maps.Point(0,0),new sogou.maps.Point(0,32));
			var shape={coord: [1,1,1,20,18,20,18,1], type:"poly"};
			var marker = new sogou.maps.Marker({id:id, map:myMap, position:curPoint, icon:icon, shadow:shadow, shape:shape, title:title, visible:true});
		});
		DWREngine.setAsync(true);
	}

	//加载网点
	function loadNetpoints(){
		var clrCenterNo = $("#hiddenClrCenterNo").val();
		var orgNo = $("#hiddenOrgNo").val();
		DWREngine.setAsync(false);
		matrixService.getNetpointsOfClrCenter(clrCenterNo,orgNo, function(data){
			if(data==null){addExcepInfo("查询网点异常");return;}
			var item,no,name,x,y;
			var curPoint,id,title,marker;
			for(var i=0; i<data.length; i++){
				item=data[i]; no=item[0]; name=item[1]; x=item[2]; y=item[3];
				if(inChina4GaussProj(x,y)==false){addExcepInfo("[网点]"+name+":经纬度非法");continue;;}
				// 地图相关
				if(myMap==null){continue;}
				curPoint = new sogou.maps.Point(x,y);
				id = "marker1"+no;
				title = "[网点]"+name+"<br/>"+"经度:"+x+"<br/>"+"纬度:"+y;
				marker = new sogou.maps.Marker({id:id, map:myMap, position:curPoint, title:title, visible:true});
			}
		});
		DWREngine.setAsync(true);
	}
	
	//添加异常信息
	function addExcepInfo(excepInfo){
		$("#excepInfoDiv").append(
			$("<div></div>").html(excepInfo).addClass("mapFieldSetInnerDiv")
				.mouseenter(function(){$(this).addClass("onmouseover");}).mouseleave(function(){$(this).removeClass("onmouseover");})
		);
	}
	
	// 将经纬度显示到页面上
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

	//确认经纬度值，并回写页面
	function confirmXY(){
		var x = $("#clickX").html(); //经度
		var y = $("#clickY").html(); //纬度
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
				<td nowrap><b>点击地图获取经纬度</b></td>
				<td nowrap align="right"><input type="button" value="关闭" onclick="javascript:window.close();"></td>
			</tr>
		</table>
		
		<!-- mapContainer -->
  		<div id="mapContainer" style="float:left;width:73%;height:95%;"></div>
  		
  		<!-- xyContainer -->
  		<div id="xyContainer" style="float:right;width:25%;height:95%;">
  			<fieldset style="width:100%;height:50%;">
  				<legend><b>经纬度信息</b></legend>
  				<div class="mapFieldSetDiv">
  					<div class="mapFieldSetInnerDiv" style="cursor:auto">经度：<span id="clickX"></span></div>
  					<div class="mapFieldSetInnerDiv" style="cursor:auto">纬度：<span id="clickY"></span></div>
  					<div class="mapFieldSetInnerDiv" style="cursor:auto" align="center">
  						<input type="button" value="确认" onclick="javascript:confirmXY();"/>
  					</div>
  				</div>
  			</fieldset>
  			<br>
  			<fieldset style="width:100%;height:45%;">
  				<legend><b>异常信息</b></legend>
  				<div id="excepInfoDiv" class="mapFieldSetDiv"></div>
  			</fieldset>
  		</div>
		
	</body>

</html>