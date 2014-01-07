var map;
var overlay=null;
var infoWindow;

var _markerImage='/images/spidc/maps/pin.png';

var boundPolygons;
var hiwayRoutes = null;
var selectPolygons=new google.maps.MVCArray();

var portCodes;
var portMarkers;
var markerMgr;
var showPortMarkers = true;
var cntCode;

var routePt = [];		// 동일지점 경로 포인트 2012.12.13
/*
코드값 테입르 참조
39	제주특별자치도
38	경상남도
37	경상북도
36	전라남도
35	전라북도
34	충청남도
33	충청북도
32	강원도
31	경기도
26	울산광역시
25	대전광역시
24	광주광역시
23	인천광역시
22	대구광역시
21	부산광역시
11	서울특별시

*/
var cityDoCode = [
                  {'name':'강원도	', 'code':7,table:'32'},
                  {'name':'경기도	', 'code':8, table:'31'},
                  {'name':'경상남도', 'code':1,table:'38'},
                  {'name':'경상북도', 'code':2,table:'37'},
                  {'name':'광주광역시', 'code':11,table:'24'},
                  {'name':'대구광역시', 'code':13,table:'22'},
                  {'name':'대전광역시', 'code':10,table:'25'},
                  {'name':'부산광역시', 'code':14,table:'21'},
                  {'name':'서울특별시', 'code':15,table:'11'},
                  {'name':'울산광역시', 'code':9,table:'26'},
                  {'name':'인천광역시', 'code':12,table:'23'},
                  {'name':'전라남도', 'code':3,table:'36'},
                  {'name':'전라북도', 'code':4,table:'35'},
                  {'name':'제주특별자치도', 'code':0,table:'39'},
                  {'name':'충청남도', 'code':5,table:'34'},
                  {'name':'충청북도', 'code':6,table:'33'}
              ];
              

var selectRegionOptions = {
	clickable: true,
	strokeColor: "#000000",
	strokeOpacity: 0.8,
	strokeWeight: 1,
	fillColor: "#0000FF",
	fillOpacity: 0.6
};

var mouseOverOptions = {
		clickable: true,
		strokeColor: "#FFFFFF",
		strokeOpacity: 1,
		strokeWeight: 3,
		fillColor: "#000000",
		fillOpacity: 0.6
	};

var defaultRegionOptions = {
    clickable: true,
    strokeColor: "#FFFFFF",
    strokeOpacity: 0.8,
    strokeWeight: 1,
    fillColor: "#FFFFFF",
    fillOpacity: 0.25
};

var selectHighwayRouteOptions = {
	    clickable: true,
	    strokeColor: "#FF00FF",
	    strokeOpacity: 0.8,
	    strokeWeight: 8
};

var defaultHighwayRouteOptions = {
	    clickable: true,
	    strokeColor: "#0000FF",
	    strokeOpacity: 1,
	    strokeWeight: 5
};

var mouseOverRegionOptions = {
		clickable: true,
		strokeColor: "#FFFFFF",
		strokeOpacity: 1,
		strokeWeight: 3,
		fillColor: "#0000FF",
		fillOpacity: 0.95
};

var contentString;

function setSelectStrokeColor(color) {
	selectRegionOptions.strokeColor=color;
}

function setSelectStrokeOpacity(opacity) {
	selectRegionOptions.strokeOpacity =opacity;
}

function setSelectFillColor(color) {
	selectRegionOptions.fillColor=color;
}

function setSelectStrokeColor(color) {
	selectRegionOptions.strokeColor=color;
}

function setSelectStrokeOpacity(opacity) {
	selectRegionOptions.strokeOpacity =opacity;
}

function setSelectFillOpacity(opacity) {
	selectRegionOptions.fillOpacity =opacity;
}

function setDefaultStrokeColor(color) {
	defaultRegionOptions.strokeColor=color;
}

function setDefaultStrokeOpacity(opacity) {
	selectRegionOptions.strokeOpacity =opacity;
}

function setDefaultFillColor(color) {
	defaultRegionOptions.fillColor=color;
}

function setSelectFillOpacity(opacity) {
	selectRegionOptions.fillOpacity =opacity;
}

function setMouseOverStrokeColor(color) {
	mouseOverOptions.strokeColor=color;
}

function setMouseOverStrokeOpacity(opacity) {
	mouseOverOptions.strokeOpacity =opacity;
}

function setMouseOverStrokeWeight(weight) {
	mouseOverOptions.strokeWeight =weight;
}

function setHiwayDefaultStrokeColor(color) {
	defaultHighwayOptions.strokeColor=color;
}

function setHiwayDefaultStrokeOpacity(opacity) {
	selectHighwayOptions.strokeOpacity =opacity;
}

function setHiwaySelectStrokeColor(color) {
	defaultHighwayOptions.strokeColor=color;
}

function setHiwaySelectStrokeOpacity(opacity) {
	selectHighwayOptions.strokeOpacity =opacity;
}

function setMapZoom(zoom) {
	map.setZoom(zoom);
}

function getMapZoom() {
	return map.getZoom();
} 

function setMapCenter(lat, lng) {
	map.setCenter(new google.maps.LatLng(lat, lng));
}
/*
 * select mode to (CityDo:Highway:Train:Ports:Airports)
 */
var _selectMode="";
var _useToContinentalButton=false;
var _useToCityDoButton=false;
var __do_callback=false;
var __cancel_callback=false;
var _portsData;
var _beforeSelectMode="";

function setSelectMode(mode) {
//	alert('set select mode is '+mode)
	if(_selectMode=="Ports" || _selectMode=="Airports") {
		if(_portsData!=null) {
			for(var i=0; i<_portsData.getLength(); i++) {
				var obj=_portsData.getAt(i);
				obj.setMap(null);
			}
		}
	}
	if(mode=="Ports" || mode=="Airports") {
		_portsData = new google.maps.MVCArray();
		_portsData.clear();
	}
	_beforeMode=_selectMode;
	_selectMode=mode;
	if(_useToCityDoButton && _selectMode!='CityDo') {
		$('#btnSetCityDo').css('display', 'block');
	}
	else $('#btnSetCityDo').css('display', 'none');
	if(__do_callback) __cancel_callback=true;

	statsData=[];
}

var hiwayNo=null;
var hiwayObjects=null;

function drawHighway(hiwayId, weight, color, opacity) {
	var polyPath;
	var j;
	clearHighway();
	for(var i=0; i<hiway.length; i++) {
		if(hiway[i].hiwayId==hiwayId) {
			var d;
			var selHiway;
			selHiway=hiway[i];
			hiwayObjects= [];
    		for(j=0; j<selHiway.entity.length; j++) {
    			polypath=[];
    			for(k=0; k<selHiway.entity[j].length/2; k++) {
    				polypath[polypath.length]=new google.maps.LatLng(selHiway.entity[j][k*2], selHiway.entity[j][k*2+1]);
    			}
			polyline=new google.maps.Polyline({
				path: polypath,
				clickable: false,
				strokeColor: color,
				strokeOpacity: opacity,
				strokeWeight: weight
			});
			polyline.setMap(map);
			//google.maps.event.addListener(polyline, 'mouseover', mouseoverHighwayEvent);
			//google.maps.event.addListener(polyline, 'mouseout', mouseoutHighwayEvent);
			hiwayObjects[hiwayObjects.length]=polyline;
			}
		}
	}
}

function clearHighway() {
	if(hiwayObjects!=null) {
		for(var i=0; i<hiwayObjects.length; i++) {
			hiwayObjects[i].setMap(null);
			hiwayObjects[i]=null;
		}
		hiwayObjects=null;
	}
}

function showToCityDoButton() {
	_useToCityDoButton=true;
}

function hideToCityDoButton() {
	_useToCityDoButton=false;
}


/* 통계 데이타 */
var stats={};
var statsData=[];

/*
 * 통계값 입력 함수 목록
 */

/*
 * 통계 변수 초기화
 */
function initStats(title, desc, stepinfo, unit, reverse) {
	stats.Title = title;
	stats.Describe=desc;
	stats.max=0;
	stats.stepNum=0;
	stats.stepInfo=stepinfo;
	stats.stepMeasure=unit;
	stats.stepValues=[];

	$('#legendBox').css('display', 'none');

	clearHiwayRoute();
	
	if(reverse==null || reverse==false) stats.reverse=false;
	else stats.reverse=true;
	
//	if(_beforeSelectMode=="Ports" || _beforeSelectMode=="Airports") {
//		for (var k in statsData) {
//			if (statsData.hasOwnProperty(k)) {
//				statsData[k].marker.setMap(null);
//				statsData[k].label.setMap(null);
//				google.maps.event.removeListener(statsData[k].marker, 'click', selectPortEvent);
//				google.maps.event.addListener(portCircle, 'mouseover', mouseoverCityDoEvent);
//				google.maps.event.addListener(portCircle, 'mouseout', mouseoutCityDoEvent);
//			}
//		}
//	}
	
}

function addStatsStep(max, rgb, label) {
	var obj = {'max':parseInt(max), 'rgb':rgb,  'label':label};
	if(_selectMode=='Ports'||_selectMode=='Airports') {
		var pinColor;
		if(rgb.charAt(0)=='#') pinColor=rgb.substr(1);
		else pinColor=rgb;
		obj.markerImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
	            new google.maps.Size(21, 34),
	            new google.maps.Point(0,0),
	            new google.maps.Point(10, 34));
		obj.markerShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
	            new google.maps.Size(40, 37),
	            new google.maps.Point(0, 0),
	            new google.maps.Point(12, 35)); 
	}
	stats.stepValues[stats.stepValues.length]=obj;
}

function getStepNum(value) {
	var vv=parseInt(value);
	for(var i=0; i<stats.stepValues.length; i++) {
		if(value<stats.stepValues[i].max) return i;
	}
	return stats.stepValues.length;
}

function addStatsData(ctcd, value) {
	statsData[ctcd]={};
	var strValue = new String(value);
	statsData[ctcd].resultNum = cfn_addCommaNum(strValue);
	statsData[ctcd].stepNo = getStepNum(value);
	if(stats.max<value) stats.max=value;
}

function addStatsPortData(port, name, latLng, value) {
	if(port==null) return -1;
	if(port.length<3) return -2;
	statsData[port]={};
	var strValue = new String(value);
	statsData[port].resultNum = cfn_addCommaNum(strValue);
	statsData[port].stepNo = getStepNum(value);
	statsData[port].portNm = name;
	statsData[port].latLng = new google.maps.LatLng(latLng.lat, latLng.lng);
	if(stats.max<value) stats.max=value;
}

function addStatsAirportData(ctcd, port, name, latLng, value) {
	if(ctcd==null || port==null) return -1;
	if(ctcd.length<2 || port.length<3) return -2;
	statsData[ctcd+port]={};
	var strValue = new String(value);
	statsData[ctcd+port].resultNum = cfn_addCommaNum(strValue);
	statsData[ctcd+port].stepNo = getStepNum(value);
	statsData[ctcd+port].portNm = name;
	statsData[ctcd+port].latLng = new google.maps.LatLng(latLng.lat, latLng.lng);
	if(stats.max<value) stats.max=value;
}

function addStatsTrainportData(ctcd, port, name, latLng, value) {
	if(ctcd==null || port==null) return -1;
	if(ctcd.length<2 || port.length<6) return -2;
	statsData[ctcd+port]={};
	var strValue = new String(value);
	statsData[ctcd+port].resultNum = cfn_addCommaNum(strValue);
	statsData[ctcd+port].stepNo = getStepNum(value);
	statsData[ctcd+port].portNm = name;
	statsData[ctcd+port].latLng = new google.maps.LatLng(latLng.lat, latLng.lng);
	if(stats.max<value) stats.max=value;
}

//Define the overlay, derived from google.maps.OverlayView
function Label(opt_options) {
     // Initialization
     this.setValues(opt_options);
 
     // Here go the label styles
     var span = this.span_ = document.createElement('span');
     span.style.cssText = 'position: relative; left: -50%; top: 2px; ' +
                          'white-space: nowrap;color:#000000;' +
                          'padding: 2px;font-family: 굴림; font-weight: bold;' +
                          'font-size: 12px;';
 
     var div = this.div_ = document.createElement('div');
     div.appendChild(span);
     div.style.cssText = 'position: absolute; display: none';
};
 
Label.prototype = new google.maps.OverlayView;
 
Label.prototype.onAdd = function() {
     var pane = this.getPanes().overlayImage;
     pane.appendChild(this.div_);
 
     var me = this;
     this.listeners_ = [
          google.maps.event.addListener(this, 'position_changed',
               function() { me.draw(); }),
          google.maps.event.addListener(this, 'text_changed',
               function() { me.draw(); }),
          google.maps.event.addListener(this, 'zindex_changed',
               function() { me.draw(); })
     ];
};
 
Label.prototype.onRemove = function() {
     this.div_.parentNode.removeChild(this.div_);
 
     for (var i = 0, I = this.listeners_.length; i < I; ++i) {
          google.maps.event.removeListener(this.listeners_[i]);
     }
};
 
Label.prototype.draw = function() {
     var projection = this.getProjection();
     var position = projection.fromLatLngToDivPixel(this.get('position'));
     var div = this.div_;
     div.style.left = position.x + 'px';
     div.style.top = position.y + 'px';
     div.style.display = 'block';
     div.style.zIndex = this.get('zIndex'); //ALLOW LABEL TO OVERLAY MARKER
     this.span_.innerHTML = this.get('text').toString();
};

function makeStatsOptions() {
	$('#legendBox').empty();
	if(stats.stepValues.length<=0) return;
	var pnode=document.createElement('div');
	$('#legendBox').css('display', 'block');
//	pnode.innerHTML = stats.Title;
	if(stats.reverse==true) {
		$.each(stats.stepValues, function(key, value) {
			var node=document.createElement('div');
			node.className="legendItem"; 
			//node.innerHTML='<span class="legendLabel" style="background-color: '+value.rgb+';">'+value.label+'</span>';
			//node.innerHTML='<span class="legendItemSelect" ><image id = "legendItem_'+stats.stepValues+'" src="/images/spidc/maps/btn_pop_blank.png" align="absmiddle"></span><span style="background-color: '+value.rgb+';" class="legendColor">&nbsp;</span><span  class="legendLabel">'+value.label+'</span>';
			node.innerHTML='<span class="legendItemIndicator" >HERE</span><span style="background-color: '+value.rgb+';" class="legendColor">&nbsp;</span><span  class="legendLabel">'+value.label+'</span>';
			//node.css('background-color', value.rgb);
			$('#legendBox').append(node);
			var opts = {
					clickable: true,
					strokeColor: defaultRegionOptions.strokeColor,
					strokeOpacity: defaultRegionOptions.strokeOpacity,
					strokeWeight: defaultRegionOptions.strokeWeight,
					fillColor: value.rgb,
					fillOpacity: defaultRegionOptions.fillOpacity
			};
			var selOpts = {
					clickable: true,
					strokeColor: selectRegionOptions.strokeColor,
					strokeOpacity: selectRegionOptions.strokeOpacity,
					strokeWeight: selectRegionOptions.strokeWeight,
					fillColor: value.rgb,
					fillOpacity: selectRegionOptions.fillOpacity
			};
			value['htmlNode']=node;
			value['polygonOptions']=opts;
			value['selPolygonOpyions']=selOpts;
		});
	}
	else {
		
		for(var i=stats.stepValues.length-1; i>=0; i--) {
			var node=document.createElement('div');
			var value = stats.stepValues[i];
			node.className="legendItem"; 
			//node.innerHTML='<span class="legendLabel" style="background-color: '+value.rgb+';">'+value.label+'</span>';
			//node.innerHTML='<span class="legendItemSelect" ><image id = "legendItem_'+i+'" src="/images/spidc/maps/btn_pop_blank.png" align="absmiddle"></span><span style="background-color: '+value.rgb+';" class="legendColor">&nbsp;</span><span  class="legendLabel">'+value.label+'</span>';
			node.innerHTML='<span class="legendItemIndicator">HERE</span><span style="background-color: '+value.rgb+';" class="legendColor">&nbsp;</span><span  class="legendLabel">'+value.label+'</span>';
			//node.css('background-color', value.rgb);
			$('#legendBox').append(node);
			var opts = {
					clickable: true,
					strokeColor: defaultRegionOptions.strokeColor,
					strokeOpacity: defaultRegionOptions.strokeOpacity,
					strokeWeight: defaultRegionOptions.strokeWeight,
					fillColor: value.rgb,
					fillOpacity: defaultRegionOptions.fillOpacity
			};
			var selOpts = {
					clickable: true,
					strokeColor: selectRegionOptions.strokeColor,
					strokeOpacity: selectRegionOptions.strokeOpacity,
					strokeWeight: selectRegionOptions.strokeWeight,
					fillColor: value.rgb,
					fillOpacity: selectRegionOptions.fillOpacity
			};
			value['htmlNode']=node;
			value['polygonOptions']=opts;
			value['selPolygonOpyions']=selOpts;
		}
	}
}

/**
 * 입력된 통계 데이터에 대한 통계 지도를 생성한다.
 */
function refreshStatsMap() {
	var errorCnt=0;
	var countriesCnt=0;
//	var invislbleCountriesCnt=0;
//	var invisibleCountries="";
	makeStatsOptions();
	if(_selectMode=="CityDo"){
		for(var i=0; i<boundPolygons.getLength(); i++) {
			var obj=boundPolygons.getAt(i);
			if(statsData[obj.ctcd]!=null) {
				var stepNo=statsData[obj.ctcd].stepNo;
				if(stepNo<stats.stepValues.length) {
					obj.setOptions(stats.stepValues[stepNo].polygonOptions);
					countriesCnt++;
				}
				else {
					// invisible
					var opt={};
					opt.fillOpacity=0;
					opt.clickable=false;
					obj.setOptions(opt);
//					invisibleCountries+=obj.ctcd+",";
//					invislbleCountriesCnt++;
				}
			}
			else {
				obj.setOptions({fillColor:'#ffffff', 
					fillOpacity:0, clickable: false, strokeWeight:0, strokeColor:0, strokeOpacity:0});
			}
		}
	}
	else if(_selectMode=="Ports"){
		for (var k in statsData) {
			if (statsData.hasOwnProperty(k)) {
				statsData[k].marker=new google.maps.Marker({
		            position: statsData[k].latLng, 
		            map: map,
		            icon: stats.stepValues[statsData[k].stepNo].markerImage,
		            shadow: stats.stepValues[statsData[k].stepNo].markerShadow,
		            title: statsData[k].resultNum+' '+stats.stepMeasure
		        });
				statsData[k].marker.code=k;
				statsData[k].marker.codeObj=statsData[k];
				statsData[k].label = new Label({
					map: map
				});
				statsData[k].label.set('zIndex', 1234);
				statsData[k].label.bindTo('position', statsData[k].marker, 'position');
				statsData[k].label.set('text', statsData[k].portNm);

				_portsData.push(statsData[k].marker);
				_portsData.push(statsData[k].label);
				google.maps.event.addListener(statsData[k].marker, 'click', selectPortEvent);
				google.maps.event.addListener(statsData[k].marker, 'mouseover', setMouseOverPort);
				google.maps.event.addListener(statsData[k].marker, 'mouseout', resetMouseOverPort);
			}
		}
	}
	else if(_selectMode=="Airports"){
		for (var k in statsData) {
			if (statsData.hasOwnProperty(k)) {
				statsData[k].marker=new google.maps.Marker({
		            position: statsData[k].latLng, 
		            map: map,
		            icon: stats.stepValues[statsData[k].stepNo].markerImage,
		            shadow: stats.stepValues[statsData[k].stepNo].markerShadow,
		            title: statsData[k].resultNum+' '+stats.stepMeasure
		        });
				statsData[k].marker.code=k;
				statsData[k].marker.codeObj=statsData[k];
				statsData[k].label = new Label({
					map: map
				});
				statsData[k].label.set('zIndex', 1234);
				statsData[k].label.bindTo('position', statsData[k].marker, 'position');
				statsData[k].label.set('text', statsData[k].portNm);

				_portsData.push(statsData[k].marker);
				_portsData.push(statsData[k].label);
				google.maps.event.addListener(statsData[k].marker, 'click', selectPortEvent);
				google.maps.event.addListener(statsData[k].marker, 'mouseover', setMouseOverPort);
				google.maps.event.addListener(statsData[k].marker, 'mouseout', resetMouseOverPort);
			}
		}
	}
	/*
	else if(_selectMode=="Highway"){
		for(var i=0; i<hiwayRoutes.getLength(); i++) {
			var obj=hiwayRoutes.getAt(i);
			if(statsData[obj.start_ctcd+'-'+obj.end_ctcd]!=null) {
				var stepNo=statsData[obj.start_ctcd+'-'+obj.end_ctcd].stepNo;
				if(stepNo<stats.stepValues.length) {
					obj.setOptions(stats.stepValues[stepNo].polylineOptions);
				}
				else {
					// invisible
					var opt={};
					opt.strokeOpacity=0;
					opt.fillOpacity=0;
					opt.clickable=false;
					obj.setOptions(opt);
				}
			}
			else {
				obj.setOptions({strokeWeight:0, strokeColor:0, strokeOpacity:0});
			}
		}
	}*/
//	alert('visible countries count is '+parseInt(countriesCnt)+'\ninvisibleCountries count is '+ parseInt(invislbleCountriesCnt));
	if(errorCnt!=0) alert(parseInt(errorCnt)+' errors occurs');
}

function getHiwayRoutePtCount() {
	var j=0; 
	var cnt=0;
	for(var i=0; i<hiwayRoutes.length; i++) {
			cnt += hiwayRoutes[i].getPath().length;
	}
	return cnt;
}

function getHiwayPtCount() {
	var j=0; 
	var cnt=0;
	for(var i=0; i<hiway.length; i++) {
		for(var j=0; j<hiway[i].entity.length; j++) {
			cnt += hiway[i].entity[j].length/2;
		}
	}
	return cnt;
}

function getCityDoPtCount() {
	var j=0; 
	var cnt=0;
	for(var i=0; i<boundPolygons.getLength(); i++) {
		var obj = boundPolygons.getAt(i);
		cnt+=obj.getPath().length;
	}
	return cnt;
}

var _last_over_continental="";

var _last_over_country="";

function setMouseOverCityDo(ctcd) {
	if(_last_over_country!="") resetMouseOverCityDo();
	_last_over_country=ctcd;
	if(statsData.hasOwnProperty(ctcd) && statsData[ctcd].stepNo<stats.stepValues.length)
		if(stats.stepValues[statsData[ctcd].stepNo].htmlNode!=null){
			stats.stepValues[statsData[ctcd].stepNo].htmlNode.className="legendItemSelect";
			//var regend ;
	 		//var objectID;
	 		//for (i = 0; i <   10 ;  i++){
	 			//regend = 'legendItem_' + i; 		
	 			//objectID = document.getElementById(regend);
	 			//objectID.src='/images/spidc/maps/btn_pop_blank.png'; 			
	 		//}
	 		//regend = 'legendItem_' + statsData[ctcd].stepNo ;		
	 		//objectID = document.getElementById(regend); 		
	 		//objectID.src=objectID.src='/images/spidc/maps/btn_pop.gif';
		}
	
	for(var i=0; i<boundPolygons.getLength(); i++) {
		var obj=boundPolygons.getAt(i);
		if(obj.ctcd==_last_over_country) {
			obj.setOptions({strokeWeight:mouseOverRegionOptions.strokeWeight
				,strokeOpacity:mouseOverRegionOptions.strokeOpacity
				,strokeColor:mouseOverRegionOptions.strokeColor});
		}
	}
}

function resetMouseOverCityDo() {
	var ctcd=_last_over_country;
	if(statsData.hasOwnProperty(ctcd) && statsData[ctcd].stepNo<stats.stepValues.length)
		if(stats.stepValues[statsData[ctcd].stepNo].htmlNode!=null) stats.stepValues[statsData[ctcd].stepNo].htmlNode.className="legendItem";

	for(var i=0; i<boundPolygons.getLength(); i++) {
		var obj=boundPolygons.getAt(i);
		if(obj.ctcd==_last_over_country) {
			obj.setOptions({strokeWeight:defaultRegionOptions.strokeWeight
				,strokeOpacity:defaultRegionOptions.strokeOpacity
				,strokeColor:defaultRegionOptions.strokeColor});
		}
	}
	_last_over_country="";
}

var _last_over_port="";

function setMouseOverPort(prtCd) {
	_last_over_port=prtCd;
	if(statsData.hasOwnProperty(prtCd) && statsData[prtCd].stepNo<stats.stepValues.length)
		if(stats.stepValues[statsData[prtCd].stepNo].htmlNode!=null) stats.stepValues[statsData[prtCd].stepNo].htmlNode.className="legendItemSelect";
	
}

function resetMouseOverPort() {
	if(statsData.hasOwnProperty(_last_over_port) && statsData[_last_over_port].stepNo<stats.stepValues.length)
		if(stats.stepValues[statsData[_last_over_port].stepNo].htmlNode!=null) stats.stepValues[statsData[_last_over_port].stepNo].htmlNode.className="legendItem";
	_last_over_country="";
}

function setSelectRegion(country) {
	_selectCountryCode=country;
	for(var i=0; i<boundPolygons.getLength(); i++) {
		var obj=boundPolygons.getAt(i);
		if(obj.ctcd==country) {
//			var opt=obj.getOptions();
//			opt.fillOpacity=1;
			obj.setOptions({fillOpacity:selectRegionOptions.fillOpacity
				,strokeWeight:selectRegionOptions.strokeOpacity
				,strokeColor:selectRegionOptions.strokeColor
				});
		}
		else {
//			var opt=obj.getOptions();
//			opt.fillOpacity=0.35;
			obj.setOptions({fillOpacity:defaultRegionOptions.fillOpacity
				,strokeWeight:defaultRegionOptions.strokeOpacity
				,strokeColor:defaultRegionOptions.strokeColor
				});
			}
	}
}

function setUnselectCountry(country) {
	for(var i=0; i<boundPolygons.getLength(); i++) {
		var obj=boundPolygons.getAt(i);
		if(obj.ctcd==_selectCountryCode) {
			obj.setOptions({fillOpacity:defaultRegionOptions.fillOpacity
				,strokeWeight:defaultRegionOptions.strokeOpacity
				,strokeColor:defaultRegionOptions.strokeColor
				});
			}
	}
	_selectCountryCode="";
}

function objectToString(o) {
    var parse = function(_o) {
         var a = [], t;
         for (var p in _o) {
              if (_o.hasOwnProperty(p)) {
                   t = _o[p];
                   if (t && typeof t == "object") {
                        a[a.length] = p + ":{ " + arguments.callee(t).join(", ") + "}";
                   }
                   else {
                        if (typeof t == "string") {
                             a[a.length] = [ p + ": \"" + t.toString() + "\"" ];
                        }
                        else {
                             a[a.length] = [ p + ": " + t.toString()];
                        }
                   }
              }
         }
         return a;
    }
    return "{" + parse(o).join(", ") + "}";

}

var _selectCountryCode="";
var _selectContinentalCode="";

function cancelSelectData() {
	if(infowindow!=null) {
		infowindow.close(map);
	}
	if(_selectCountryCode!="") setUnselectCountry(_selectCountryCode);
	if(_selectContinentalCode!="") setUnselectContinental(_selectContinentalCode);
}

function selectContinental(cont, latLng, desc) {
	var pos=new google.maps.LatLng(latLng.lat, latLng.lng);
	setSelectContinental(this.cont);
	map.setCenter(pos);

	infowindow.setContent(desc);
	infowindow.setPosition(pos);
	infowindow.open(map);
}

function selectCountry(ctcd, latLng, desc) {
	var pos=new google.maps.LatLng(latLng.lat, latLng.lng);
	setSelectRegion(this.ctcd);
	map.setCenter(pos);

	infowindow.setContent(desc);
	infowindow.setPosition(pos);
	infowindow.open(map);
}

var _showDefaultInfoWindow = true;

function setDefaultInfoWindow(value) {
	_showDefaultInfoWindow=value;
}

function mousemoveCityDoEvent(e) {
	var lat, lng;
	lat = e.latLng.lat().toString();
	lng = e.latLng.lng().toString();
	document.getElementById('map_loc').innerHTML=lat.substring(0, lat.indexOf('.')+5)+","+lng.substring(0, lng.indexOf('.')+5);
}

function mouseoverCityDoEvent(e) {
	if(_selectMode=="CityDo"){
		setMouseOverCityDo(this.ctcd);
	}
}

function mouseoutCityDoEvent(e) {
	if(_selectMode=="CityDo"){
		resetMouseOverCityDo();
	}
}

function selectPortEvent(e) {
	if(parent.cb_selectPort!=null) {
		__do_callback=true;
		parent.cb_selectPort(this.port);
		__do_callback=false;
		if(__cancel_callback) {
			__cancel_callback=false;
			return;
		}
	}
	
	//contentString="<div id='infoContents'><span><image src='/images/spidc/maps/title_dot.gif' align='middle'>&nbsp;&nbsp;</span>";
	contentString="<div id='infoContents'>";
	contentString += "<div class='h1'>"+this.codeObj.portNm+"</div></div><br style='line-height:6px'>";
	contentString += "<div id='infoList'>";
	//contentString += "<div id='infoItem'><span><image src='/images/spidc/maps/ico_board_tit.gif'>&nbsp;&nbsp;</span><span class='title'>"+stats.stepInfo+" : </span><span class='value'>"+ this.codeObj.resultNum+" "+ stats.stepMeasure+"</span></div>";
	contentString += "<div id='infoItem'><span class='title'>"+stats.stepInfo+" : </span><span class='value'>"+ this.codeObj.resultNum+" "+ stats.stepMeasure+"</span></div>";
	contentString += "</div>";

	infowindow.setContent(contentString);
	infowindow.setPosition(this.codeObj.latLng);
	infowindow.open(map);
}

var _clickZoomToCityDo=false;

function onClickZoomToCountry() {
	var ctcd=_selectContinentalCode;
	_clickZoomToCityDo=true;
	cancelSelectData();
	if(parent.cb_enterCountry!=null) {
		__do_callback=true;
		parent.cb_enterCountry(ctcd);
		__do_callback=false;
		if(__cancel_callback) {
			__cancel_callback=false;
			return;
		}
	}
}

function selectCityDoEvent(e) {
	e.stop();
	if(_clickZoomToCityDo==true) {
		_clickZoomToCityDo=false;
		return;
	}
	if(_selectMode!="CityDo") return;
	if(parent.cb_selectCountry!=null) {
		__do_callback=true;
		parent.cb_selectCountry(this.ctcd);
		__do_callback=false;
		if(__cancel_callback) {
			__cancel_callback=false;
			return;
		}
	}

	if(_showDefaultInfoWindow==false) return;
	//contentString="<div id='infoContents'><span><image src='/images/spidc/maps/title_dot.gif' align='middle'>&nbsp;&nbsp;</span>";
	contentString="<div id='infoContents'>";
	contentString += "<div class='h1'>"+this.citydoKorNm+"</div></div><br style='line-height:6px'>";
	contentString += "<div id='infoList'>";
	if(parent.getInfo_Country!=null) {
		var gi=parent.getInfo_Country(this.ctcd);
		for(var i=0; i<gi.length; i++) {
			//contentString += "<div id='infoItem'><span><image src='/images/spidc/maps/ico_board_tit.gif'>&nbsp;&nbsp;</span><span class='title'>"+gi[i].title+":</span><span class='value'>"+gi[i].value+"</span></div>";
				contentString += "<div id='infoItem'><span class='title'>"+gi[i].title+":</span><span class='value'>"+gi[i].value+"</span></div>";
		}
	}
	else {
		//contentString += "<div id='infoItem'><span><image src='/images/spidc/maps/ico_board_tit.gif'>&nbsp;&nbsp;</span><span class='title'>"+stats.stepInfo+" : </span><span class='value'>"+ statsData[this.ctcd].resultNum+" "+ stats.stepMeasure+"</span></div>";
		contentString += "<div id='infoItem'><span class='title'>"+stats.stepInfo+" : </span><span class='value'>"+ statsData[this.ctcd].resultNum+" "+ stats.stepMeasure+"</span></div>";
	}	
	contentString += "</div>";
	//contentString += "<image align='right' margin='5px' id='zoomToCntry' src = '/images/spidc/maps/btn_go_on.gif'  onClick='onClickZoomToCountry();'/>";
	setSelectRegion(this.ctcd);
	contentString +="</div>";
	infowindow.setContent(contentString);
	infowindow.setPosition(e.latLng);
	infowindow.open(map);
	//map.setZoom(8);
	map.setCenter(new google.maps.LatLng(this.centerLat, this.centerLng));
}

function mouseoverHighwayRouteEvent(e) {
//	if(_selectMode=="CityDo"){
//		setMouseOverCityDo(this.ctcd);
//	}
}

function mouseoutHighwayRouteEvent(e) {
//	if(_selectMode=="CityDo"){
//		resetMouseOverCityDo();
//	}
}

function selectHighwayRouteEvent(e) {
	e.stop();
//	if(_clickZoomToCityDo==true) {
//		_clickZoomToCityDo=false;
//		return;
//	}
//	if(_selectMode!="CityDo") return;
//	if(parent.cb_selectHighway!=null) {
//		__do_callback=true;
//		parent.cb_selectCountry(this.ctcd);
//		__do_callback=false;
//		if(__cancel_callback) {
//			__cancel_callback=false;
//			return;
//		}
//	}
//
//	if(_showDefaultInfoWindow==false) return;
//	contentString="<div id='infoContents'>";
//	contentString += "<h1>"+this.citydoKorNm+"</h1>";
////	contentString += "<h1>"+stats.Title+"</h1>";
//
//	contentString += "<div id='infoList'>";
//	if(parent.getInfo_Country!=null) {
//		var gi=parent.getInfo_Country(this.ctcd);
//		for(var i=0; i<gi.length; i++) {
//			contentString += "<div id='infoItem'><span class='title'>"+gi[i].title+":</span><span class='value'>"+gi[i].value+"</span></div>";
//		}
//	}
//	else {
////		contentString += "<div id='infoItem'><span class='title'>"+gi[i].title+":</span><span class='value'>"+gi[i].value+"</span></div>";
//		contentString += "<div id='infoItem'><span class='caption'>"+stats.stepInfo +":</span> <span class='value'>"+ statsData[this.ctcd].resultNum+" "+ stats.stepMeasure+"</span></div>";
//	}
//	contentString += "</div>";
//	contentString += "<input type='button' id='zoomToCntry' width='120' value='바로가기' onClick='onClickZoomToCountry();'/>"; 
//	setSelectRegion(this.ctcd);
//	contentString +="</div>";
//	infowindow.setContent(contentString);
//	infowindow.setPosition(e.latLng);
//	infowindow.open(map);
//	map.setZoom(8);
//	map.setCenter(new google.maps.LatLng(this.centerLat, this.centerLng));
}

function clearHiwayRoute() {
    if(hiwayRoutes!=null) {
		//for(var i=0; i<hiwayRoutes.length; i++) {
			//hiwayRoutes[i].setOptions({strokeOpacity: 0});
		//}
		$.each(hiwayRoutes, function(key, value){
			value.setOptions({strokeOpacity:0});
		});
		$.each(routePt, function(key, value){
			value.setMap(null);
			});
    }
}

//function showAllHiwayRoute() {
//	
//}

/*
 * Layer Control
 */

var _layers = [];

function newLayer(name, fillColor, fillOpacity, strokeColor, strokeOpacity, strokeWeight, visible, clickable, minZoom, maxZoom) {
	var n=_layers.length;
	_layers[_layers.length] = {'name': name, 'clickable':clickable, 'fc':fillColor, 'fo':fillOpacity, 'sc':strokeColor, 'so':strokeOpacity, 'sw':strokeWeight, 'visible':visible, 'hidden':false, 'minZoom':minZoom, 'maxZoom':maxZoom, 'objs':new google.maps.MVCArray()};
	return n;
}

function removeLayer(name) {
	var id=getLayer(name);
	if(id<0) return;
	for(var i=0; i<_layers[id].objs.getLength(); i++) {
		var objs =  _layers[id].objs.getAt(i);
		objs.setMap(null);
	}
	_layers[id].objs.clear();
	_layers.splice(id, 1);
}

function removeLayerObjects(name) {
	var id=getLayer(name);
	if(id<0) return;
	for(var i=0; i<_layers[id].objs.getLength(); i++) {
		var objs =  _layers[id].objs.getAt(i);
		objs.setMap(null);
	}
	_layers[id].objs.clear();
}

function getLayer(name) {
	for(var i=0; i<_layers.length; i++) {
		if(_layers[i].name==name) return i;
	}
	return -1;
}

function _getLayerPolygonOptions(layer) {
	return {'clickable':layer.clickable, 'fillColor':layer.fc, 'fillOpacity':layer.fo, 'storkeColor':layer.sc, 'strokeOpacity':layer.so, 'strokeWeight':layer.sw, 'visible':layer.visible};
}

function modifyLayer(name, fillColor, fillOpacity, strokeColor, strokeOpacity, strokeWeight, visible, clickable, minZoom, maxZoom) {
	var id=getLayer(name);
	if(id<0) return;
	_layers[id].fc = fillColor;
	_layers[id].fo = fillOpacity/100;
	_layers[id].sc = strokeColor;
	_layers[id].so = strokeOpacity/100;
	_layers[id].sw = strokeWeight;
	_layers[id].visible = visible;
	_layers[id].clickable = clickable;
	var opts = _getLayerPolygonOptions(_layers[id]);
	for(var i=0; i<_layers[id].objs.getLength(); i++) {
		var objs =  _layers[id].objs.getAt(i);
		objs.setOptions(opts);
//		objs.setVisible(visible);
	}
	return n;
}

function setVisibleLayer(name, visible) {
	var id=getLayer(name);
	if(id<0) return;
	_layers[id].visible = visible;
	for(var i=0; i<_layers[id].objs.getLength(); i++) {
		var objs =  _layers[id].objs.getAt(i);
		if(visible && map.getZoom()>=_layers[id].minZoom && map.getZoom()<=_layers[id].maxZoom) objs.setVisible(true);
		else {
			if(visible) _layers[id].hidden=true;
			objs.setVisible(false);
		}
	}
	return n;
}

function setAllVisibleLayer(name, vislble) {
	for(var id=0; id<_layers.length; id++) {
		_layers[id].visible = visible;
		for(var i=0; i<_layers[id].objs.getLength(); i++) {
			var objs =  _layers[id].objs.getAt(i);
			if(visible && map.getZoom()>=_layers[id].minZoom && map.getZoom()<=_layers[id].maxZoom) objs.setVisible(true);
			else objs.setVisible(false);
		}
	}
}

function setClickableLayer(name, clickable) {
	var id=getLayer(name);
	if(id<0) return;
	_layers[id].clickable = clickable;
	var opts = _getLayerPolygonOptions(_layers[id]);
	for(var i=0; i<_layers[id].objs.getLength(); i++) {
		var objs =  _layers[id].objs.getAt(i);
		objs.setOptions(opts);
	}
	return n;
}

function modifyLayerAlpha(layName, opaque) {
	var id=getLayer(name);
	if(id<0) return;
	_layers[id].fo = opaque;
	var opts = _getLayerPolygonOptions(_layers[id]);
	for(var i=0; i<_layers[id].objs.getLength(); i++) {
		var objs =  _layers[id].objs.getAt(i);
		objs.setOptions(opts);
	}
	return n;
}

function addPolygon(code, layerName, encodedPath) {
	var id=getLayer(layerName);
	if(id<0) return;
	var opts = _getLayerPolygonOptions(_layers[id]);
	opts.paths = google.maps.geometry.encoding.decodePath(encodedPath);
	opts.map = map;
	var obj = new google.maps.Polygon(opts);
	obj.setMap(map);
	_layers[id].objs.push(obj);
	addMapObjectEventListener(obj);
}

function addMapLabel(code, label, lat, lng) {
    var mapLabel = new MapLabel({
        text: label,
        position: new google.maps.LatLng(lat, lng),
        map: map,
        fontSize: 16,
        align: 'center'
      });

	addMapObjectEventListener(obj);

	return code+uid;
}

function addPolygonLabel(code, layerName, encodedPath, label) {
	var id=getLayer(layerName);
	if(id<0) return;
	var opts = _getLayerPolygonOptions(_layers[id]);
	opts.paths = google.maps.geometry.encoding.decodePath(encodedPath);
	opts.map = map;
	var obj = new google.maps.Polygon(opts);
	obj.setMap(map);
	obj.label=label;
    obj.labelObj = new MapLabel({
        text: label,
        position: new google.maps.LatLng(lat, lng),
        map: map,
        fontSize: 16,
        align: 'center'
      });

//	obj.labelObj = new InfoBox({
//		 content: obj.label
//			,boxStyle: {
//			   border: "1px solid black"
//			  ,textAlign: "center"
//		          ,backgroundColor:"white"
//			  ,fontSize: "8pt"
//			  ,width: "50px"
//			 }
//			,disableAutoPan: true
//			,pixelOffset: new google.maps.Size(-25, 0)
//			,position: map.getProjection().get
//			,closeBoxURL: ""
//			,isHidden: false
//			,enableEventPropagation: true
//		      });
//	obj.labelObj.open(map);
	_layers[id].objs.push(obj);
	addMapObjectEventListener(obj);
	addMapObjectEventListener(obj.labelObj);
}

function addPolygonS(code, layerName, encodedPath, fillColor, fillOpacity, strokeColor, strokeOpacity, strokeWeight) {
	var id=getLayer(layerName);
	if(id<0) return;
	
	var opts = _getLayerPolygonOptions(_layers[id]);
//	if(code=='2') alert(encodedPath);	// debug
	opts.paths = google.maps.geometry.encoding.decodePath(encodedPath);
	opts.map = map;
	opts.fillColor=fillColor;
	opts.fillOpacity=fillOpacity;
	opts.strokeColor=strokeColor;
	opts.strokeOpacity=strokeOpacity;
	opts.strokeWeight=strokeWeight;
	var obj = new google.maps.Polygon(opts);
	obj.code=code;
	obj.setMap(map);
	_layers[id].objs.push(obj);
	addMapObjectEventListener(obj);
	return code+": "+encodedPath;
}

var _addrObjectList;

function addAddrPoly(code, layerName, encodedPath, eupMyunDong, ree, lnm, lnmSub) {
	var label;
	var id=getLayer(layerName);
	var lat, lng;
	if(id<0) return;
	var opts = _getLayerPolygonOptions(_layers[id]);
	opts.paths = google.maps.geometry.encoding.decodePath(encodedPath);
	opts.map = map;
	var obj = new google.maps.Polygon(opts);
	obj.setMap(map);
	if(ree!='') {
		label=eupMyunDong+' '+ree;
	}
	else label=eupMyunDong;
	if(lnmSub!='') {
		label = label+' '+lnm+'-'+lnmSub;
	}
	else label = label+' '+lnm;
	obj.label=label;
	var w, e, s, n;
	n=opts.paths[i].lat;
	s=opts.paths[i].lat;
	e=opts.paths[i].lng;
	w=opts.paths[i].lng;
	for(var i=1; i<opts.paths.length; i++) {
		if(n<opts.paths[i].lat) n=opts.paths[i].lat;
		if(s>opts.paths[i].lat) s=opts.paths[i].lat;
		if(e<opts.paths[i].lng) e=opts.paths[i].lng;
		if(w>opts.paths[i].lng) w=opts.paths[i].lng;
	}
	lat=w+(e-w)/2;
	lng=s+(n-s)/2;
    obj.labelObj = new MapLabel({
        text: label,
        position: new google.maps.LatLng(lat, lng),
        map: map,
        fontSize: 16,
        align: 'center'
      });

	_layers[id].objs.push(obj);
	_addrObjectList[_addrObjectList.length] = obj;
	addMapObjectEventListener(obj);
	addMapObjectEventListener(obj.labelObj);
}

function gotoAddr(eupMyunDong, ree, lnm, lnmSub) {
	for(var i=0; i<_addrObjectList.length; i++) {
		var obj = _addrObjectList[i];
		if(obj.eupMyunDong==eupMyunDong
				&& obj.ree==ree
				&& obj.lnm==lnm
				&& obj.lnmSub==lnmSub) {
			map.panToBounds(obj.getBounds());
		}
	}
}

function addMapObjectEventListener(obj) {
	google.maps.event.addListener(obj, 'click', clickObjectEvent);
	google.maps.event.addListener(obj, 'mouseover', mouseoverObjectEvent);
	google.maps.event.addListener(obj, 'mousemove', mousemoveObjectEvent);
	google.maps.event.addListener(obj, 'mouseout', mouseoutObjectEvent);
}

function clickObjectEvent(e) {
	dispatchFlashEvent(new FlashEvent('click', obj.code));
}

function mouseoverObjectEvent(e) {
	dispatchFlashEvent(new FlashEvent('mouseover', obj.code));
}

function mousemoveObjectEvent(e) {
	dispatchFlashEvent(new FlashEvent('mousemove', obj.code));
}

function mouseoutObjectEvent(e) {
	dispatchFlashEvent(new FlashEvent('mouseout', obj.code));
}

  function getCityCode(code) {
	var n=code.indexOf('-');  
	var cd1=code.substring(0, n);
	var cd2=code.substring(n+1);
  	for(var i=0; i<cityDoCode.length; i++) {
  		if(cd1==cityDoCode[i].table) {
  			cd1=cityDoCode[i].code;
  			break;
  		}
  	}
  	  	for(var i=0; i<cityDoCode.length; i++) {
  	  		if(cd2==cityDoCode[i].table) {
  	  			cd2=cityDoCode[i].code;
  	  			break;
  	  		}
  	  	}
  	  		return cd1+'-'+cd2;
}
				    
function getHiwayRoutes(code) {
	var c1, c2;
	c1 = code.substring(0, code.indexOf('.'));
	c2 = code.substring(code.indexOf('.')+1);
	for(var i=0; i<hiwayRoutes.length; i++) {
		if(hiwayRoutes[i].routeCd==code) return hiwayRoutes[i];
	}
	return null;
}

function getHiwayRoutesStartPt(code) {
	var c1, c2;
	for(var i=0; i<hiwayRoutes.length; i++) {
		c1 = hiwayRoutes[i].routeCd;
		if(c1.indexOf('-')>0) {
			c1 = c1.substring(0, c1.indexOf('-'));
			if(c1==code) return hiwayRoutes[i].getPath().getAt(0); 
		}
	}
	return null;
}

function showHiwayRoute(code, value) {
	var step =getStepNum(value);
	var color='#FF0000';
	var routes;
	code=getCityCode(code);
	var cds = code.split('-');
	if(cds[0]==cds[1]) {
		var pt = getHiwayRoutesStartPt(cds[0]);
		if(pt!=null) {
			routePt[routePt.length]=new google.maps.Marker({
				position: pt, 
				map: map,
				icon: stats.stepValues[step].markerImage,
				shadow: stats.stepValues[step].markerShadow,
				title: value+' '+stats.stepMeasure
			});
		}
	}
	else {
		if(step<stats.stepValues.length) color=stats.stepValues[step].rgb;
		routes = getHiwayRoutes(code);
		if(routes!=null) routes.setOptions({
			strokeOpacity: defaultHighwayRouteOptions.strokeOpacity,
			strokeColor: color
		});
	}
}

function hideHiwayRoute(code) {
	var routes;
	code=getCityCode(code);
	var cds = code.split('-');
	if(cds[0]==cds[1]) {
		if(routePt!=null && routePt.length>0) {
			$.each(routePt, function(key, value){
				value.setMap(null);
			});
			routePt=[];
		}
	}
	else {
		routes = getHiwayRoutes(code);
		if(routes!=null) routes.setOptions({
			strokeOpacity: 0
		});
	}
}

function enterCountryEvent(e) {
	e.stop();
	if(_selectMode!="CityDo") return;
	if(parent.cb_enterCountry!=null) {
		__do_callback=true;
		parent.cb_enterCountry(this.ctcd);
		__do_callback=false;
		if(__cancel_callback) {
			__cancel_callback=false;
			return;
		}
	}
}

function getMaxZoom() {
	return map.getMaxZoom();
}

function cfn_addCommaNum(value) {
	var str=String(value);
	var l=str.length;
	var pos=3;
	while(pos<str.length) {
		str = str.substr(0, str.length-pos)+","+str.substr(str.length-pos);
		pos+=4;
	}
	return str;
}

function changeMapZoom(delta) {
	var scl=map.getZoom()+delta;
	if(scl>19) scl=19;
	else if(scl<6) scl=6;
	map.setZoom(scl);
}


function getMousePosToLatLng(mpx, mpy) {
	return overlay.getProjection().fromContainerPixelToLatLng(new google.maps.Point(mp.x, mp.y));
}

// map system event
function mouseMove(mpx, mpy) {
	var mev = {
			  stop: null
			};

	mev.latLng = overlay.getProjection().fromContainerPixelToLatLng(new google.maps.Point(mpx, mpy));

	google.maps.event.trigger(map, 'mousemove', mev);
}

function mouseDragStart(mpx, mpy) {
	var mev = {
			  stop: null
			};

	mev.latLng = overlay.getProjection().fromPointToLatLng(new google.maps.Point(mpx, mpy));

	google.maps.event.trigger(map, 'dragstart', mev);
}

function mouseDrag(mpx, mpy) {
	var mev = {
			  stop: null
			};

	mev.latLng = overlay.getProjection().fromPointToLatLng(new google.maps.Point(mpx, mpy));

	google.maps.event.trigger(map, 'drag', mev);
}

function mouseDragEnd(mpx, mpy) {
	var mev = {
			  stop: null
			};

	mev.latLng = overlay.getProjection().fromPointToLatLng(new google.maps.Point(mpx, mpy));

	google.maps.event.trigger(map, 'dragend', mev);
}

function mouseOut(mpx, mpy) {
	var mev = {
			  stop: null
			};

	mev.latLng = overlay.getProjection().fromContainerPixelToLatLng(new google.maps.Point(mpx, mpy));

	google.maps.event.trigger(map, 'mouseout', mev);
}

function mouseOver(mpx, mpy) {
	var mev = {
			  stop: null
			};

	mev.latLng = overlay.getProjection().fromContainerPixelToLatLng(new google.maps.Point(mpx, mpy));
	google.maps.event.trigger(map, 'mouseover', mev);
	
}

function mouseClick(mpx, mpy) {
	var mev = {
			  stop: null
			};

	mev.latLng = overlay.getProjection().fromContainerPixelToLatLng(new google.maps.Point(mpx, mpy));
//	map.panTo(mev.latLng);
	google.maps.event.trigger(map, 'click', mev);
//	alert('Mouse Clicked (' + mpx +"," +mpy+") -> "+mev.latLng.toString());
}

function mouseDoubleClick(mpx, mpy) {
	var mev = {
			  stop: null
			};

	mev.latLng = overlay.getProjection().fromContainerPixelToLatLng(new google.maps.Point(mpx, mpy));

	map.panTo(mev.latLng);
	google.maps.event.trigger(map, 'dblclick', mev);
//	alert('Double Clicked (' + mpx +"," +mpy+")");
}

function setHybridMap() {
	map.setMapTypeId(google.maps.MapTypeId.HYBRID);
}

function setRoadMap() {
	map.setMapTypeId(google.maps.MapTypeId.ROADMAP);
}

// flash Event
function FlashEvent(type, value) {
	this.type=type;
	this.value=value;
}

FlashEvent.prototype = new Object;

FlashEvent.prototype.getType = function() {
	return this.type;
}

FlashEvent.prototype.getValue = function() {
	return this.value;
}

function dispatchFlashEvent(flashEvent){
    if(document.getElementById('noflash').document.Main.dispatchMapEvent!=null) {
    	document.getElementById('noflash').document.Main.dispatchMapEvent({type:flashEvent.getType(), value:flashEvent.getValue()});
    }
}

var flexInst;

function loadingUPAmap() {
	
	flexInst = document.getElementById('Main');

    var latlng = new google.maps.LatLng(35.49361, 129.398946);
//	var statsMapstyles = [
//	                      {
//	                    	  "featureType": "administrative",
//	                    	  "elementType": "geometry",
//	                    	  "stylers": [
//	                    	              { "visibility": "true" }
//	                    	              ]
//	                      },
//	                          {
//	                        	    "featureType": "landscape",
//	                        	    "elementType": "geometry.fill",
//	                        	    "stylers": [
//	                        	      { "color": "#ffffff" }
//	                        	    ]
//	                        	  }
//	                      ];
//    var statsMap = new google.maps.StyledMapType(statsMapstyles,
//    	    {name: "SPIDC_STATS_MAP"});
//    
    var myOptions = {
		maxZoom: 19,
		minZoom: 6,
      zoom: 12,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.HYBRID,
      disableDefaultUI: true,
//      mapTypeId: 'SPIDC_STATS_MAP',
      mapTypeControl: false
    };
    map = new google.maps.Map(document.getElementById("map_canvas"),
        myOptions);
    
    overlay = new google.maps.OverlayView();
    overlay.draw = function() {};
    overlay.setMap(map);

    google.maps.event.addListener(map, 'click', function(e) {
    	var lat, lng;
    	lat = e.latLng.lat().toString();
    	lng = e.latLng.lng().toString();
//    	alert(lat.substring(0, lat.indexOf('.')+5)+","+lng.substring(0, lng.indexOf('.')+5));
    });
//    map.mapTypes.set('map_style', statsMap);
//    map.setMapTypeId('map_style');
    
//    google.maps.event.addListener(map, 'zoom_changed', function() {
//    	document.getElementById('map_scl').innerHTML=map.getZoom().toString();
//      });
    google.maps.event.addListener(map, 'mousemove', function(e) {
//    	var lat, lng;
//    	lat = e.latLng.lat().toString();
//    	lng = e.latLng.lng().toString();
    	dispatchFlashEvent(new FlashEvent('mapMouseMove', {'lat':e.latLng.lat(), 'lng':e.latLng.lng()}));
//    	document.getElementById('map_loc').innerHTML=lat.substring(0, lat.indexOf('.')+5)+","+lng.substring(0, lng.indexOf('.')+5);
      });
//    google.maps.event.addListener(map, 'center_changed', function() {
//    	var lat, lng;
//    	var latLng=map.getCenter();
//    	lat = latLng.lat().toString();
//    	lng = latLng.lng().toString();
//    	document.getElementById('map_ctn').innerHTML=lat.substring(0, lat.indexOf('.')+5)+","+lng.substring(0, lng.indexOf('.')+5);
//      });
    
    boundPolygons=new google.maps.MVCArray();
    hiwayRoutes= [];
    
    infowindow = new google.maps.InfoWindow();

//    portCodes = new google.maps.MVCArray();

    // Markers
    //markerMgr = new MarkerManager(map, {trackMarkers: true, maxZoom: 12});
    portMarkers = [];
			
//    setSelectMode("CityDo");	// CityDo:시도선택모드
//    initStats("화물량", "화물량 통계", "화물량 단위 Ton", "Ton");
//    addStatsStep(10000, "#0000FF", "     0~10,000");
//    addStatsStep(20000, "#2000DF", "10,000~20,000");
//    addStatsStep(30000, "#4000BF", "20,000~30,000");
//    addStatsStep(40000, "#BF0040", "30,000~40,000");
//    addStatsStep(50000, "#DF0020", "40,000~50,000");
//    addStatsStep(60000, "#ff0000", "50,000~60,000");
//    addStatsStep(70000, "#ff0000", "60,000~70,000");
//    addStatsStep(80000, "#ff0000", "70,000~80,000");
//    addStatsStep(90000, "#ff0000", "80,000~90,000");
//    addStatsStep(100000, "#ff0000", "90,000~100,000");
//    addStatsData('01', 0);
//    addStatsData('02', 0);
//    addStatsData('03', 0);
//    addStatsData('04', 0);
//    addStatsData('05', 0);
//    addStatsData('06', 0);
//    addStatsData('07', 0);
//    addStatsData('08', 0);
//    
    
    
    /*
      코드값 테입르 참조
     39	제주특별자치도
38	경상남도
37	경상북도
36	전라남도
35	전라북도
34	충청남도
33	충청북도
32	강원도
31	경기도
26	울산광역시
25	대전광역시
24	광주광역시
23	인천광역시
22	대구광역시
21	부산광역시
11	서울특별시
     
     */
//    $.each(regionKorea, function(key, value) {
//    	var i, j, k;
//    	var polypath;
//    	var boundPoly;
////    	citydoData[value.relm_cd]={};
////		citydoData[value.relm_cd].citydoKorNm=value.CITYDO_KOR_NM;
////		citydoData[value.relm_cd].citydoEngNm=value.CITYDO_ENG_NM;
////		citydoData[value.relm_cd].centerLaCrdnt=value.CENTER_LA_CRDNT;
////		citydoData[value.relm_cd].centerLoCrdnt=value.CENTER_LO_CRDNT;
////		citydoData[value.relm_cd].rgnCrdntS=value.RGN_CRDNT_S;
////		citydoData[value.relm_cd].rgnCrdntW=value.RGN_CRDNT_W;
////		citydoData[value.relm_cd].rgnCrdntN=value.RGN_CRDNT_N;
////		citydoData[value.relm_cd].rgnCrdntE=value.RGN_CRDNT_E;
////		citydoData[value.relm_cd].citydoBoundary = new google.maps.LatLngBounds(
////				new google.maps.LatLng(value.RGN_CRDNT_S, value.RGN_CRDNT_W)
////				, new google.maps.LatLng(value.RGN_CRDNT_N, value.RGN_CRDNT_E)
////		);
//    		for(j=0; j<value.entity.length; j++) {
//    			polypath=[];
//    			for(k=0; k<value.entity[j].length/2; k++) {
//    				polypath[polypath.length]=new google.maps.LatLng(value.entity[j][k*2], value.entity[j][k*2+1]);
//    			}
//    			value.entity[j]=null;	// clear memory
//    			boundPoly=new google.maps.Polygon({
//    				paths: polypath,
//    				clickable: true,
//    				strokeColor: defaultRegionOptions.strokeColor,
//    				strokeOpacity: defaultRegionOptions.strokeOpacity,
//    				strokeWeight: defaultRegionOptions.strokeWeight,
//    				fillColor: defaultRegionOptions.fillColor,
//    				fillOpacity: defaultRegionOptions.fillOpacity
//    			});
//    			boundPoly.ctcd=value.relmCd;
//    			boundPoly.citydoKorNm=value.citydoKorNm;
//    			boundPoly.centerLat=value.centerLat;
//    			boundPoly.centerLng=value.centerLng;
//    			boundPoly.setMap(map);
//    			google.maps.event.addListener(boundPoly, 'click', selectCityDoEvent);
//    			google.maps.event.addListener(boundPoly, 'mouseover', mouseoverCityDoEvent);
//    			google.maps.event.addListener(boundPoly, 'mousemove', mousemoveCityDoEvent);
//    			google.maps.event.addListener(boundPoly, 'mouseout', mouseoutCityDoEvent);
//    			boundPoly.relmCd=value.relm_cd;
//    			boundPolygons.push(boundPoly);
//    		}
//    		value.entity=null;
//    	}
//    );
    /*$.each(hiwayRoute, function(key, value) {
    	var i, j, k;
    	var polypath;
    	var polyline;
    	var route;
		for(j=0; j<value.dest_route.length; j++) {
			polypath=[];
			route = value.dest_route[j];
			for(k=0; k<route.pos.length/2; k++) {
				polypath[polypath.length]=new google.maps.LatLng(route.pos[k*2], route.pos[k*2+1]);
			}
			route.pos=null;
			polyline=new google.maps.Polyline({
				path: polypath,
				clickable: false,
				strokeColor: defaultHighwayRouteOptions.strokeColor,
				strokeOpacity: 0,
				strokeWeight: defaultHighwayRouteOptions.strokeWeight
			});
			polyline.setMap(map);
			google.maps.event.addListener(polyline, 'click', selectHighwayRouteEvent);
			google.maps.event.addListener(polyline, 'mouseover', mouseoverHighwayRouteEvent);
			google.maps.event.addListener(polyline, 'mouseout', mouseoverHighwayRouteEvent);
			polyline.routeCd=value.start_ctcd+'-'+route.end_ctcd;
			hiwayRoutes[hiwayRoutes.length]=polyline;
		}

    }
    		);
    
    $('#btnSetCityDo').click(function (e){
    	setSelectMode("Country");
    	if(parent.cb_changeMode!=null) {
    		cancelSelectData();
//    		__do_callback=true;
    		parent.cb_changeMode(_selectMode);
//    		__do_callback=false;
    	}
    });
    */
//     맵 초기화
//    if(parent.cb_mapInit!=null) {
//    	parent.cb_mapInit();
//    }
    dispatchFlashEvent(new FlashEvent('loadMapCompleted', ''));

    return "0";
  }