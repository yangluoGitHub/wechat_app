//�������ֻ��һ������վ,����豸������,������߸������sougou��ͼ

//����go2map��js�ļ�
document.write('<script type="text/javascript" src="http://api.go2map.com/maps/js/api_v2.5.1.js"><\/script>');

//��ͼ���ö���
var myMap; 
var infoWindow; 

/**
 * Ĭ�ϳ�ʼ��:��ͼ&��Ϣ����
 * @param mapContainerId
 * @param centerX ����(��˹ͶӰ)
 * @param centerY γ��(��˹ͶӰ)
 * @returns {Boolean}
 */
function defInit(mapContainerId, centerX, centerY){
	if(!initMyMap(mapContainerId, null, centerX, centerY, null)){return false;} //��ͼ
	if(!initInfoWindow(null)){return false;} //��Ϣ����
	return true;
}

/**
 * ��ͼ��ʼ��
 * @param mapContainerId
 * @param zoom
 * @param centerX ����(��˹ͶӰ)
 * @param centerY γ��(��˹ͶӰ)
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
 * ��ʼ����Ϣ����
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
 * ��ͼ���������������ػ���ʾ
 * @param sf
 * @param array
 */
function displayArray(sf,array){try{for(var i=0;i<array.length;i++){array[i].setVisible(sf);}}catch(e){return;}}


/**
 * ����id��marker
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


// �й��߽�
var china_east_latLng=135.1;
var china_west_latLng=73.5;
var china_north_latLng=53.5;
var china_south_latLng=3.8;
var china_east_GaussProj=15044571.3;
var china_west_GaussProj=4649808.4;
var china_north_GaussProj=7058289.7;
var china_south_GaussProj=391988.9;

/**
 * �ж��Ƿ����й����ڣ��ֲڣ�
 * @param x ��˹ͶӰ�����ȣ�
 * @param y ��˹ͶӰ��γ�ȣ�
 * @returns {Boolean}
 */
function inChina4GaussProj(x,y){
	if(x!=null&&y!=null&&x>=china_west_GaussProj&&x<=china_east_GaussProj&&y>=china_south_GaussProj&&y<=china_north_GaussProj){return true;}
	else{return false;}
}

/**
 * �ж��Ƿ����й����ڣ��ֲڣ�
 * @param x ����
 * @param y γ��
 * @returns {Boolean}
 */
function inChina4LatLng(x,y){
	if(x!=null&&y!=null&&x>=china_west_latLng&&x<=china_east_latLng&&y>=china_south_latLng&&y<=china_north_latLng){return true;}
	else{return false;}
}
