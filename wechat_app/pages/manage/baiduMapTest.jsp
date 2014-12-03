<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="com.weili.wechat.common.Resource"%>
<%@ page import="com.weili.wechat.common.GetResource"%><% Resource resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());%>
<%@ page language="java"%>
<%@ page errorPage="../error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>

<head>  
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  

<style type="text/css">
		body, html {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#l-map{height:100%;width:78%;float:left;border-right:2px solid #bcbcbc;}
		#r-result{height:100%;width:20%;float:left;}
		#allmap {width: 80%;height: 100%;margin:0;font-family:"微软雅黑";}
		#side {width: 20%;height: 100%;float:right;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=PIbbXDlclwkSFHLiQuo6aWCU"></script>
	<title>添加多个标注点</title>
</head>
<body>
<div id="page" class="page">
	
	<div id="side" class="side" >
	<table >
	 <tr><td id="北京" onclick="openInfo(this)"><font color="red">北京</font></td></tr>
	 <tr><td id="上海" onclick="openInfo(this)"><font color="red">上海</font></td></tr>
	 <tr><td id="南京" onclick="openInfo(this)" ><font color="red">南京</font></td></tr>
	 
	 </table>
	 </div>
	 <div id="allmap"></div>
</div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(116.404, 39.915);
	map.centerAndZoom(point, 5);
	map.enableScrollWheelZoom();
	map.addControl(new BMap.NavigationControl());  
	map.setMapStyle({
  	styleJson:[
          {
                    "featureType": "land",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#212121"
                    }
          },
          {
                    "featureType": "building",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#2b2b2b"
                    }
          },
          {
                    "featureType": "highway",
                    "elementType": "all",
                    "stylers": {
                              "lightness": -42,
                              "saturation": -91
                    }
          },
          {
                    "featureType": "arterial",
                    "elementType": "geometry",
                    "stylers": {
                              "lightness": -77,
                              "saturation": -94
                    }
          },
          {
                    "featureType": "green",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#1b1b1b"
                    }
          },
          {
                    "featureType": "water",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#181818"
                    }
          },
          {
                    "featureType": "subway",
                    "elementType": "geometry.stroke",
                    "stylers": {
                              "color": "#181818"
                    }
          },
          {
                    "featureType": "railway",
                    "elementType": "geometry",
                    "stylers": {
                              "lightness": -52
                    }
          },
          {
                    "featureType": "all",
                    "elementType": "labels.text.stroke",
                    "stylers": {
                              "color": "#313131"
                    }
          },
          {
                    "featureType": "all",
                    "elementType": "labels.text.fill",
                    "stylers": {
                              "color": "#8b8787"
                    }
          },
          {
                    "featureType": "manmade",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#1b1b1b"
                    }
          },
          {
                    "featureType": "local",
                    "elementType": "geometry",
                    "stylers": {
                              "lightness": -75,
                              "saturation": -91
                    }
          },
          {
                    "featureType": "subway",
                    "elementType": "geometry",
                    "stylers": {
                              "lightness": -65
                    }
          },
          {
                    "featureType": "railway",
                    "elementType": "all",
                    "stylers": {
                              "lightness": -40
                    }
          },
          {
                    "featureType": "boundary",
                    "elementType": "geometry",
                    "stylers": {
                              "color": "#8b8787",
                              "weight": "1",
                              "lightness": -29
                    }
          }
]
	});
	var opts = {
				width : 250,     // 信息窗口宽度
				height: 80,     // 信息窗口高度
				title : "信息窗口" , // 信息窗口标题
				enableMessage:true//设置允许信息窗发送短息
			   };
	// 编写自定义函数,创建标注
	function addMarker(point, message){
	  var myIcon = new BMap.Icon("http://developer.baidu.com/map/jsdemo/img/fox.gif", new BMap.Size(300,157));
	  var marker = new BMap.Marker(point);
	  //var marker = new BMap.Marker(point,{icon:myIcon}); 
	     marker.addEventListener('click', function (e) {
          //alert('单击点的坐标为：' + e.point.lng + ',' + e.point.lat);  // 监听点击事件
          var p = e.target;
		var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
          var infoWindow = new BMap.InfoWindow(message,opts);  // 创建信息窗口对象 
		 map.openInfoWindow(infoWindow,point); //开启信息窗口
        });
	  map.addOverlay(marker);
      //marker.addEventListener("click",openInfo.bind(null,content));
	  //marker.setAnimation(BMAP_ANIMATION_BOUNCE);
	}
	// 随机向地图添加25个标注
	var cityArray= new Array("北京","上海","南京","无锡","深圳","广州","拉萨","乌鲁木齐","兰州","成都","重庆","杭州","武汉","西安");
	//alert(cityArray[0]);
	var citylnglat = new Array("116.41667-39.91667","121.48-31.22","118.78333-32.05000","120.18-31.34",
						"114.06667-22.61667","113.23333-23.16667","91.08-29.39","87.36-43.45",
						"103.73333-36.03333","104.06667-30.66667","106.33-29.35","120.20000-30.26667",
						"114.31667-30.51667","108.57-34.17");
	var cityWeight = new Array("1000","1100","700","700","900","900","300","400","400","700","800","800","700","600");
	var bounds = map.getBounds();
	var sw = bounds.getSouthWest();
	var ne = bounds.getNorthEast();
	var lngSpan = Math.abs(sw.lng - ne.lng);
	var latSpan = Math.abs(ne.lat - sw.lat);
	for (var i = 0; i < 14; i ++) {
		//var point = new BMap.Point(sw.lng + lngSpan * (Math.random() * 0.7), ne.lat - latSpan * (Math.random() * 0.7));
		var lnglat = citylnglat[i];
		var lnglatsplit = lnglat.split("-");
		//alert(lnglatsplit[0]);
		var point = new BMap.Point(lnglatsplit[0],lnglatsplit[1]);
		var message = cityArray[i] +"市xxx热值是:" + cityWeight[i];
		addMarker(point, message);
	}
	function getBoundary(){       
		var bdary = new BMap.Boundary();
		bdary.get("中国", function(rs){       //获取行政区域
			map.clearOverlays();        //清除地图覆盖物       
			var count = rs.boundaries.length; //行政区域的点有多少个
			for(var i = 0; i < count; i++){
				var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000"}); //建立多边形覆盖物
				map.addOverlay(ply);  //添加覆盖物
				map.setViewport(ply.getPath());    //调整视野         
			}                
		});   
	}

	//setTimeout(function(){
	//	getBoundary();
	//}, 2000);
		function openInfo(obj){
		var id = obj.id;
		//alert(id);
		var index = getindex(cityArray, id);
		//alert(index);
		var lnglatsplit = citylnglat[index].split("-");
		var point = new BMap.Point(lnglatsplit[0], lnglatsplit[1]);
		var message = cityArray[index] +"市xxx热值是:" + cityWeight[index];
		var infoWindow = new BMap.InfoWindow(message,opts);  // 建信息窗口对象 
		map.openInfoWindow(infoWindow,point); //开启信息窗口
	}
	function getindex(cityArray, id){
		for (var i = 0; i < cityArray.length; i++){
	
	  		if (cityArray[i] == id){
	  			return i;
	  		}
		}
		return -1;
	}
</script>