//针对有且只有一个服务站,多个设备覆盖物,多个折线覆盖物的sougou地图

//引用go2map的js文件
document.write('<script type="text/javascript" src="http://api.go2map.com/maps/js/api_v2.5.1.js"><\/script>');

//地图公用对象
var myMap; 
var infoWindow; 

/**
 * 默认初始化:地图&信息窗口
 * @param mapContainerId
 * @param centerX 经度(高斯投影)
 * @param centerY 纬度(高斯投影)
 * @returns {Boolean}
 */
function defInit(mapContainerId, centerX, centerY){
	if(!initMyMap(mapContainerId, null, centerX, centerY, null)){return false;} //地图
	if(!initInfoWindow(null)){return false;} //信息窗口
	return true;
}

/**
 * 地图初始化
 * @param mapContainerId
 * @param zoom
 * @param centerX 经度(高斯投影)
 * @param centerY 纬度(高斯投影)
 * @param mapTypeId
 * @returns {Boolean}
 */
function initMyMap(mapContainerId, zoom, centerX, centerY, mapTypeId){
	var mapContainer = document.getElementById(mapContainerId);
	if(mapContainer==null){return false;}
	if(zoom==null){zoom=11;}
	if(centerX==null||centerY==null){centerX=12957935.425698949;centerY=4824070.996907527;}
	if(mapTypeId==null){mapTypeId=sogou.maps.MapTypeId.ROADMAP;}
	
	myMap = new sogou.maps.Map(mapContainer, 
			{zoom:zoom, center:new sogou.maps.Point(centerX,centerY), mapTypeId:mapTypeId});
	return true;
}

/**
 * 初始化信息窗口
 * @param defIWContent
 * @returns {Boolean}
 */
function initInfoWindow(defIWContent){
	if(defIWContent==null){defIWContent="";}
	infoWindow = new sogou.maps.InfoWindow({content:defIWContent});
	infoWindow.open(myMap);
	infoWindow.close();
	return true;
}

/**
 * 地图基本对象数组隐藏或显示
 * @param sf
 * @param array
 */
function displayArray(sf,array){try{for(var i=0;i<array.length;i++){array[i].setVisible(sf);}}catch(e){return;}}


/**
 * 根据id找marker
 * @param id
 * @param markers
 * @returns
 */
function findMarkerById(id,markers){
	try{
		var marker;
		for(var i=0;i<markers.length;i++){
			marker=markers[i];
			if(marker.id==id){return marker;}
		}
		return null;
	}catch(e){return null;}
}


// 中国边界
var china_east_latLng=135.1;
var china_west_latLng=73.5;
var china_north_latLng=53.5;
var china_south_latLng=3.8;
var china_east_GaussProj=15044571.3;
var china_west_GaussProj=4649808.4;
var china_north_GaussProj=7058289.7;
var china_south_GaussProj=391988.9;

/**
 * 判断是否在中国境内（粗糙）
 * @param x 高斯投影（经度）
 * @param y 高斯投影（纬度）
 * @returns {Boolean}
 */
function inChina4GaussProj(x,y){
	if(x!=null&&y!=null&&x>=china_west_GaussProj&&x<=china_east_GaussProj&&y>=china_south_GaussProj&&y<=china_north_GaussProj){return true;}
	else{return false;}
}

/**
 * 判断是否在中国境内（粗糙）
 * @param x 经度
 * @param y 纬度
 * @returns {Boolean}
 */
function inChina4LatLng(x,y){
	if(x!=null&&y!=null&&x>=china_west_latLng&&x<=china_east_latLng&&y>=china_south_latLng&&y<=china_north_latLng){return true;}
	else{return false;}
}
