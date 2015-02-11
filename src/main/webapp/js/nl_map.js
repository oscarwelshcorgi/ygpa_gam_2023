var myLayout;
var map;
var baseLayer;

function initLayout() {
	myLayout = new dhtmlXLayoutObject({
	    parent: document.body,    // id or object for parent container
	    pattern: "3T",          // layout's pattern
	    skin: "dhx_skyblue",     // optional, layout's skin
	    cells: [
        	{
        		id: "a",
        		header: false,
        		height: 50,
        		fix_size: [null, true]
        	},
        	{
        		id: "b",
        		text: "레이어",
        		header: true,
        		width: 220
        	},
        	{
        		id: "c",
        		text: "도면",
        		header: false,
        		fix_size: [null, true]
        	}
        ]
	});
	var formData = [
	                 {type:"settings",position:"label-left"},
	                 {type: "label", width:200, label: "국제물류센터 실내공간정보"},
	                 {type:"newcolumn"},
	                 {type: "input", width: 300, name: 'searchKeyword', label: '시설 검색:'},
	                 {type:"newcolumn"},
                     {type: "button", name:"seach", value:"검색"}
	             ];

	myLayout.cells("a").attachForm(formData);
	var layerForm = [
	                 {type:"settings",position:"label-right"},
	                 {type:"radio", name:"layer", value:"0", label:"0층",checked:true},
	                 {type:"radio", name:"layer", value:"1", label:"1층",checked:false},
	                 {type:"radio", name:"layer", value:"2", label:"2층",checked:false},
	                 {type:"radio", name:"layer", value:"r", label:"지붕층",checked:false}
	             ];
	myLayout.cells("b").attachForm(layerForm);
}

function initMap() {
	var initExtent = new OpenLayers.Bounds(0,0,380,130);
    OpenLayers.ImgPath = context_root+"/images/egovframework/ygpa/gam/maps/";
    OpenLayers.theme = context_root+"/js/theme/ygpa/";
    Proj4js.libPath = context_root+"/js/Proj4js/";

    $('#div_map').height(myLayout.cells("c").getHeight());

	myLayout.cells("c").attachObject("div_map");

	map = new OpenLayers.Map({
		div : "div_map",
		allOverlays : true,
		maxExtent : initExtent,
//		srsName : "EPSG:5186",
//	maxExtent : new OpenLayers.Bounds(1775863661, -357671466,
//	1861477578, -258878993)
//		theme : context_root+'/css/theme/ygpa/style.css'
	});


	var styles = new OpenLayers.StyleMap({
    	'default':{
            strokeColor: "#000000",
            strokeOpacity: 1,
            strokeWidth: 1,
            fillColor: "#FBFF9A",
            fillOpacity: 0.5,
            pointRadius: 6,
            pointerEvents: "visiblePainted",
            // label with \n linebreaks
//            label : "${JIBUN}",

            fontColor: "black",
            fontSize: "12px",
            fontFamily: "Courier New, monospace",
            fontWeight: "bold",
            labelAlign: "cm",
            labelXOffset: "2px",
            labelYOffset: "2px",
            labelOutlineColor: "white",
            labelOutlineWidth: 3
    },
    	'select': {
            strokeColor: "#00FF00",
            strokeOpacity: 1,
            strokeWidth: 3,
            fillColor: "#E92FFF",
            fillOpacity: 1,
            pointRadius: 6,
            pointerEvents: "visiblePainted",
            // label with \n linebreaks
//            label : "${JIBUN}",
            fontColor: "white",
            fontSize: "12px",
            fontFamily: "Courier New, monospace",
            fontWeight: "bold",
            labelAlign: "cm",
            labelXOffset: "2px",
            labelYOffset: "2px",
            labelOutlineColor: "black",
            labelOutlineWidth: 3
    	}
    });

//	add rules from the above lookup table
//	styles.addUniqueValueRules("default", "RP_TYPE", {
//		10 : {
//			strokeColor : "#000000",
//			strokeWidth : 2
//		},
//		12 : {
//			strokeColor : "#222222",
//			strokeWidth : 2
//		},
//		14 : {
//			strokeColor : "#444444",
//			strokeWidth : 2
//		},
//		16 : {
//			strokeColor : "#666666",
//			strokeWidth : 2
//		},
//		18 : {
//			strokeColor : "#888888",
//			strokeWidth : 2
//		},
//		19 : {
//			strokeColor : "#666666",
//			strokeWidth : 1
//		}
//	});

	baseLayerProtocol = new OpenLayers.Protocol.WFS.v1_1_0({
		version : "1.1.0",
		service : "WFS",
		url : gis_engine_url,
		featureNS : "http://geogate.g-inno.com/dataserver/datahouse",
		featurePrefix : "datahouse",
//		featureType : "GL_PL",
		featureType : "MRC_2F_1",
		srsName : "EPSG:5186",
		geometryName : "G2_SPATIAL"
	});
//    var basemap = new EMD.gis.OpenLayersVworld("배경지도", {
//    	isBaseLayer : true,
// 		displayInLayerSwitcher: false,
//    	maxExtent: initExtent,
//    	type: 'png',
//    	numZoomLevels: 19,
//    	zoomOffset: 3,
//    	baseurl: EMD.map_base_url,
//    	projection: new OpenLayers.Projection("EPSG:900913")
//    	});

//	EMD.protocols.assetsRentDetail = new OpenLayers.Protocol.WFS({
//		version: "1.1.0",
//		service: "WFS",
//		url: EMD.gis_engine_url,
//		featureNS: "http://geogate.g-inno.com/dataserver/datahouse",
//		featurePrefix: "datahouse",
//		featureType: "ASSETS_RENT_DETAIL_F",
//		srsName: "EPSG:5186",
//		geometryName: "G2_SPATIAL"
//	});

// 	EMD.userLayer.assetRentStats = new OpenLayers.Layer.Vector("임대현황", {
// 		minScale: 10000,
//		strategies: [new OpenLayers.Strategy.BBOX(), EMD.strategies.assetRentStats],
//        projection: new OpenLayers.Projection("EPSG:5186"),
//		protocol: EMD.protocols.assetsRentDetail,
//		styleMap: new OpenLayers.StyleMap(EMD.userStyle.assetRentStyle['default'])
// 	});

	var vectors = new OpenLayers.Layer.Vector("도면 정보", {
		strategies: [new OpenLayers.Strategy.Fixed()],
//        projection: new OpenLayers.Projection("EPSG:5186"),
 		displayInLayerSwitcher: false,
    	isBaseLayer : false,
		strategies : [ new OpenLayers.Strategy.BBOX() ],
		protocol : baseLayerProtocol,
    	maxExtent: initExtent,
		styleMap : styles
	});

	map.addLayers([vectors]);

	vectors.setVisibility(true);

    map.addControls(
    		[
    		 new OpenLayers.Control.MousePosition({
    			 prefix: '지도 좌표 : ',
    			 separator: ' | ',
    			 numDigits: 6,
//    			 displayProjection: new OpenLayers.Projection("EPSG:5186"),
    		 })
    		 ,new OpenLayers.Control.Scale()
    		 ]
    );
//	map.addControl(new OpenLayers.Control.LayerSwitcher());
//	map.zoomToMaxExtent();
	map.zoomToExtent(initExtent);

}

jQuery(document).ready(function() {

	initLayout();

	initMap();

	console.log('debug');

});