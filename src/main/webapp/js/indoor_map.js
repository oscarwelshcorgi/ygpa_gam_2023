//var gis_engine_url = "http://192.168.200.61:8080/G2DataService/GService?";
var gis_engine_url = "http://192.168.0.71:8092/G2DataService/GService?";

var myLayout;
var mainLayout;
var mainGrid;
var map;
var baseLayer;

var _layers = [];

var _currentEditLayer = null;

var headerCell=null;
var categoryCell=null;
var propertyCell=null;

var editControls=null;
var panel=null;
var selectControl=null;
var DeleteFeature = new OpenLayers.Class(OpenLayers.Control, {
    initialize: function(layer, options) {
        OpenLayers.Control.prototype.initialize.apply(this, [options]);
        this.layer = layer;
        this.handler = new OpenLayers.Handler.Feature(
            this, layer, {click: this.clickFeature}
        );
    },
    clickFeature: function(feature) {
        // if feature doesn't have a fid, destroy it
        if(feature.fid == undefined) {
            this.layer.destroyFeatures([feature]);
        } else {
            feature.state = OpenLayers.State.DELETE;
            this.layer.events.triggerEvent("afterfeaturemodified",
                                           {feature: feature});
            feature.renderIntent = "select";
            this.layer.drawFeature(feature);
        }
    },
    setMap: function(map) {
        this.handler.setMap(map);
        OpenLayers.Control.prototype.setMap.apply(this, arguments);
    },
    CLASS_NAME: "OpenLayers.Control.DeleteFeature"
});


function initLayout() {
	myLayout = new dhtmlXLayoutObject({
	    parent: document.body,    // id or object for parent container
	    pattern: "5I",          // layout's pattern
	    skin: "dhx_skyblue",     // optional, layout's skin
	    offsets: {          // optional, offsets for fullscreen init
	        top:    10,     // you can specify all four sides
	        right:  10,     // or only the side where you want to have an offset
	        bottom: 10,
	        left:   10
	    },
	    cells: [
        	{
        		id: "a",
        		header: false,
        		height: 40,
        		fix_size: [null, true]
        	},
        	{
        		id: "b",
        		text: "층 선택",
        		header: true,
        		width: 150
        	},
        	{
        		id: "c",
        		text: "도면",
        		header: false
        	},
        	{
        		id: "d",
        		text: "속성정보",
        		header: true,
        		width: 200,
        		collapse: true
        	},
        	{
        		id: "e",
        		text: "조회 데이터",
        		header: false,
        		width: 200,
        		collapse: true
        	}
        ]
	});
	headerCell=myLayout.cells("a");

	categoryCell = myLayout.cells("b");

	propertyCell = myLayout.cells("d");

	mainGrid = myLayout.cells("e").attachGrid();
	mainGrid.setImagePath(context_root+"/js/codebase/imgs/");
	mainGrid.setHeader("ID,공간 정보 명,규격,등록자,등록일,수정자,수정일자");
	mainGrid.setInitWidths("100,250,300,100,120,100,120,100");
	mainGrid.setColAlign("right,center,center,center,center,center,center");
	mainGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro");
	mainGrid.setColSorting("int,str,str,str,str,str,str");
	mainGrid.init();

//	myLayout.setAutoSize("c", "b:d");
}

function initMap(base_layer, edit_layer, floors, ext) {
	var initExtent = new OpenLayers.Bounds(ext[0],ext[1],ext[2],ext[3]);
    OpenLayers.ImgPath = context_root+"/images/egovframework/ygpa/gam/maps/";
    OpenLayers.theme = context_root+"/js/theme/ygpa/";
    Proj4js.libPath = context_root+"/js/Proj4js/";

    $('#div_map').height(myLayout.cells("c").getHeight());

    myLayout.cells("c").attachObject("div_map");
    myLayout.cells("c").progressOn();

	map = new OpenLayers.Map({
		div : "div_map",
		allOverlays : true,
		maxExtent : initExtent,
		srsName : "EPSG:5186",
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

	var editStyles = new OpenLayers.StyleMap({
    	'default':{
            strokeColor: "#FF00FF",
            strokeOpacity: 1,
            strokeWidth: 1,
            fillColor: "#00009A",
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

	baseLayerProtocol = new OpenLayers.Protocol.WFS.v1_1_0({
		version : "1.1.0",
		service : "WFS",
		url : gis_engine_url,
		  filter: new OpenLayers.Filter.Comparison({
				type: OpenLayers.Filter.Comparison.EQUAL_TO,
				property: "Layer",
				value: name
			}),
		featureNS : "http://geogate.g-inno.com/dataserver/datahouse",
		featurePrefix : "datahouse",
		featureType : base_layer,
		srsName : "EPSG:5186",
		geometryName : "G2_SPATIAL"
	});

	$.each(floors, function(key, value) {
		var l = new OpenLayers.Layer.Vector(value.name, {
	        projection: new OpenLayers.Projection("EPSG:5186"),
	 		displayInLayerSwitcher: false,
	    	isBaseLayer : false,
			strategies : [new OpenLayers.Strategy.BBOX() ],
			protocol : new OpenLayers.Protocol.WFS({
					version : "1.1.0",
					service : "WFS",
					url : gis_engine_url,
					featureNS : "http://geogate.g-inno.com/dataserver/datahouse",
					featurePrefix : "datahouse",
					featureType : base_layer,
					srsName : "EPSG:5186",
					geometryName : "G2_SPATIAL"
				}),
		    filter: new OpenLayers.Filter.Comparison({
				type: OpenLayers.Filter.Comparison.EQUAL_TO,
				property: "FL",
				value: value.layer
			  }),
	    	maxExtent: initExtent,
			styleMap : styles
		});
		l._floor=value.layer;

		l.events.register("loadend", myLayout.cells("c"), function(e) {
			this.progressOn();
		});
		l.events.register("loadend", myLayout.cells("c"), function(e) {
			this.progressOff();
		});
		_layers[_layers.length] = l;
	});

	var selLayer=[];
	$.each(floors, function(key, value) {
		var saveStrategy = new OpenLayers.Strategy.Save();
		var l = new OpenLayers.Layer.Vector(value.name, {
	        projection: new OpenLayers.Projection("EPSG:5186"),
	 		displayInLayerSwitcher: false,
	    	isBaseLayer : false,
			strategies : [saveStrategy, new OpenLayers.Strategy.BBOX() ],
			protocol : new OpenLayers.Protocol.WFS({
					version : "1.1.0",
					service : "WFS",
					url : gis_engine_url,
					featureNS : "http://geogate.g-inno.com/dataserver/datahouse",
					featurePrefix : "datahouse",
					featureType : edit_layer,
					srsName : "EPSG:5186",
					geometryName : "G2_SPATIAL"
				}),
		    filter: new OpenLayers.Filter.Comparison({
				type: OpenLayers.Filter.Comparison.EQUAL_TO,
				property: "FL",
				value: value.layer
			  }),
	    	maxExtent: initExtent,
			styleMap : editStyles
		});
		l._floor=value.layer;
		l._editLayer=true;
		l._saveStrategy=saveStrategy;
		l._saveStrategy.events.register('success', map, function(e) {
			alert('저장 되었습니다.');
		});
		l._saveStrategy.events.register('fail', map, function(e) {
			alert('저장 실패 하였습니다.');
		});
		l.events.register('beforefeatureselected', map, function(e){
			selectControl.unselectAll();
		});
		l.events.register('featureselected', map, function(e){
			this.events.triggerEvent('featureselected', e.feature);
		});
		l.events.register('featureunselected', map, function(e){
			this.events.triggerEvent('featureunselected', e.feature);
		});
		selLayer[selLayer.length] = l;

		_layers[_layers.length] = l;
	});


	var startFloor = floors[0].layer;
	map.addLayers(_layers);
	$.each(_layers, function(key, value) {
		if(value._floor==startFloor) {
			value.setVisibility(true);
			if(value._editLayer) _currentEditLayer=value;
		}
		else value.setVisibility(false);
	});

    selectControl = new OpenLayers.Control.SelectFeature(
    		selLayer,
			{
			    clickout: true, toggle: true,
			    multiple: true, hover: false,
//			    toggleKey: "ctrlKey", // ctrl key removes from selection
			    multipleKey: "shiftKey" // shift key adds to selection
			}
		);
 	selectControl.handlers.feature.stopDown = false;

 	map.addControls(
    		[
    		 new OpenLayers.Control.MousePosition({
    			 prefix: '지도 좌표 : ',
    			 separator: ' | ',
    			 numDigits: 6,
    			 displayProjection: new OpenLayers.Projection("EPSG:5186"),
    		 })
    		 ,new OpenLayers.Control.Scale()
    		 ,selectControl
    		 ]
    );
	selectControl.activate();

	map.zoomToExtent(initExtent);

	return map;
}

function switchFloor(name) {
	$.each(_layers, function(key, value) {
		if(value.getVisibility()==true) value.setVisibility(false);
	});

	$.each(_layers, function(key, value) {
		if(value._floor==name) {
			value.setVisibility(true);
			if(value._editLayer==true) {
				_currentEditLayer=value;
			}
		}
	});
}

function editLayer() {
	var layer=_currentEditLayer;
	var virtual = {
			    strokeColor: "#00ff00",
			    fillColor: "#00ff00",
			    strokeOpacity: 1,
			    strokeWidth: 2,
			    pointRadius: 5,
			    graphicName: "triangle"
			};
	editControls={
            addPoint: new OpenLayers.Control.DrawFeature(layer,
                OpenLayers.Handler.Point, {
				featureAdded: function(f) {
					console.log('add point');
//					EMD.gis.storeLayerObject(this.layer, f, null);
					f.attributes['FL']=this.layer._floor;
					if(this.layer!=f.layer)
						this.layer.addFeatures([f]);
				}
            }),
            addLine: new OpenLayers.Control.DrawFeature(layer,
                OpenLayers.Handler.Path, {
					featureAdded: function(f) {
						console.log('add line');
//						EMD.gis.storeLayerObject(this.layer, f, null);
						f.attributes['FL']=this.layer._floor;
						if(this.layer!=f.layer)
							this.layer.addFeatures([f]);
				}
            }),
            addFeature: new OpenLayers.Control.DrawFeature(layer,
                    OpenLayers.Handler.Polygon, {
				featureAdded: function(f) {
					console.log('add feature');
//					EMD.gis.storeLayerObject(this.layer, f, null);
					f.attributes['FL']=this.layer._floor;
					if(this.layer!=f.layer)
						this.layer.addFeatures([f]);
			}
            }),
    		editFeature: new OpenLayers.Control.ModifyFeature(layer,
            	    {
        			vertexRenderIntent: 'modify',
        			virtualStyle: virtual,
					mode: OpenLayers.Control.ModifyFeature.RESHAPE,
					handlerOptions: {holeModifier: "altKey"}
      	    }),
  	    	delFeature: new DeleteFeature(layer, {title: "삭제"})

			};
            for(var key in editControls) {
            	map.addControl(editControls[key]);
            }

            // show edit panel
    		var addPointBtn = new OpenLayers.Control.Button({
    			title: "점 추가",
    			displayClass: "olControlEditAddPoint",
    			type: OpenLayers.Control.TYPE_TOGGLE,
    			eventListeners: {
	    			activate: function() {
	    				for(var key in editControls) {
	    					if(key!='addPoint') editControls[key].button.deactivate();
	    				}
	    				editControls['addPoint'].activate();
	    			},
	    			deactivate: function() {
	    				for(var key in editControls) {
	    					var control=editControls[key];
	    					if(key=='addPoint') control.deactivate();
	    				}
	    			}
    			}
    		});
    		editControls.addPoint.button=addPointBtn;
    		var addLineBtn = new OpenLayers.Control.Button({
    			title: "선 추가",
    			displayClass: "olControlEditAddLine",
    			type: OpenLayers.Control.TYPE_TOGGLE,
    			eventListeners: {
	    			activate: function() {
	    				for(var key in editControls) {
	    					if(key!='addLine') editControls[key].button.deactivate();
	    				}
	    				editControls['addLine'].activate();
	    			},
	    			deactivate: function() {
	    				for(var key in editControls) {
	    					var control=editControls[key];
	    					if(key=='addLine') control.deactivate();
	    				}
	    			}
    			}
    		});
    		editControls.addLine.button=addLineBtn;
    		var addButton = new OpenLayers.Control.Button({
    			title: "추가",
    			displayClass: "olControlEditAdd",
    			type: OpenLayers.Control.TYPE_TOGGLE,
    			eventListeners: {
	    			activate: function() {
	    				for(var key in editControls) {
	    					if(key!='addFeature') editControls[key].button.deactivate();
	    				}
	    				editControls['addFeature'].activate();
	    			},
	    			deactivate: function() {
	    				for(var key in editControls) {
	    					var control=editControls[key];
	    					if(key=='addFeature') control.deactivate();
	    				}
	    			}
    			}
    		});
    		editControls.addFeature.button=addButton;
    		var editButton = new OpenLayers.Control.Button({
    			title: "편집",
    			displayClass: "olControlEditEdit",
    			type: OpenLayers.Control.TYPE_TOGGLE,
    			eventListeners: {
	    			activate: function() {
	    				for(var key in editControls) {
	    					if(key!='editFeature') editControls[key].button.deactivate();
	    				}
	    				editControls['editFeature'].activate();
	    			},
	    			deactivate: function() {
	    				for(var key in editControls) {
	    					var control=editControls[key];
	    					if(key=='editFeature') control.deactivate();
	    				}
	    			}
    			}
    		});
    		editControls.editFeature.button=editButton;
    		var delButton = new OpenLayers.Control.Button({
    			title: "삭제",
    			displayClass: "olControlEditDelete",
    			type: OpenLayers.Control.TYPE_TOGGLE,
    			eventListeners: {
	    			activate: function() {
	    				for(var key in editControls) {
	    					if(key!='delFeature') editControls[key].button.deactivate();
	    				}
	    				editControls['delFeature'].activate();
	    			},
	    			deactivate: function() {
	    				for(var key in editControls) {
	    					var control=editControls[key];
	    					if(key=='delFeature') control.deactivate();
	    				}
	    			}
    			}
    		});
    		editControls.delFeature.button=delButton;
    		var save = new OpenLayers.Control.Button({
    			title: "편집 완료",
    			layer: layer,
    			trigger: function() {
    				panel.deactivate();
    				map.removeControl(panel);
    				for(var key in editControls) {
    					editControls[key].deactivate();
    				}
    				selectControl.activate();
    				// 레이어를 저장한다.
    				this.layer._saveStrategy.save();
//    				EMD.gis.storeLayer(EMD._currentEditLayer);

    			},
    			displayClass: "olControlEditDone"
    		});

    		var cancel = new OpenLayers.Control.Button({
    			title: "편집 취소",
    			trigger: function() {
    				panel.deactivate();
    				map.removeControl(panel);
    				for(var key in editControls) {
    					editControls[key].deactivate();
    				}
    				selectControl.activate();
//    				EMD.gis.refreshLayer(EMD._currentEditLayer);
    			},
    			displayClass: "olControlEditCancel"
    		});

    		if(panel!=null) {
    			panel.deactivate();
    		}
    		panel = new OpenLayers.Control.Panel({
    			displayClass: 'mapEditToolBar'
    		});

    		selectControl.deactivate();		// 객체 정보를 표시 하지 않도록 변경한다.

    		panel.addControls([addPointBtn, addLineBtn, addButton, editButton, delButton, save, cancel]);
    		map.addControl(panel);
    		panel.activate();
}

function leftPad(n, c, digits) {
	  var zero = '';
	  n = n.toString();

	  if (n.length < digits) {
	    for (var i = 0; i < digits - n.length; i++)
	      zero += c;
	  }
	  return zero + n;
}

function getDate(d) {
	if(d==null) d=new Date();
	  var s =
	    leftPad(d.getFullYear(), '0', 4) + '-' +
	    leftPad(d.getMonth() + 1, '0', 2) + '-' +
	    leftPad(d.getDate(), '0', 2);
	  return s;
}

function saveFeature(feature) {
	if(_currentEditLayer!=null) {
		if(feature.state != OpenLayers.State.INSERT) {
			feature.state = OpenLayers.State.UPDATE;
			feature.attributes['UPD_DT']=getDate();
		}
		else {
			feature.attributes['REG_DT']=getDate();
		}
		_currentEditLayer._saveStrategy.save();
	}
}