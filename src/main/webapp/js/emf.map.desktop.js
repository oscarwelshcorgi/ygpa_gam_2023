//
// EMF Solution Map Desktop Framework.
//
function removeWindow(rem_win) {
	rem_win.remove();
}

window.removeWindow = removeWindow;

var buttonIcons = [
			{label: '최초신청', icon:'ui-icon-check'},
			{label: '연장신청', icon:'ui-icon-clock'},
			{label: '신청삭제', icon:'ui-icon-minusthick'},
			{label: '결재요청', icon:'ui-icon-document'},
			{label: '자산등록', icon:'ui-icon-document'},
			{label: '지도보기', icon:'ui-icon-flag'},
			{label: '위치등록', icon:'ui-icon-pin-s'},
			{label: '자산추가', icon:'ui-icon-plusthick'},
			{label: '추가', icon:'ui-icon-plusthick'},
			{label: '삭제', icon:'ui-icon-trash'},
			{label: '저장', icon:'ui-icon-disk'},
			{label: '취소', icon:'ui-icon-arrowreturnthick-1-e'},
			{label: '적용', icon:'ui-icon-check'},
			{label: '업로드', icon:'ui-icon-arrowthickstop-1-n'},
			{label: '다운로드', icon:'ui-icon-arrowthickstop-1-s'},
			{label: '사용승낙', icon:'ui-icon-clipboard'},
			{label: '승낙취소', icon:'ui-icon-cancel'},
			{label: '맵조회', icon:'ui-icon-extlink'},
			{label: '엑셀', icon:'ui-icon-excel-down'},
			{label: '인쇄', icon:'ui-icon-print'},
			{label: '과태료', icon:'ui-icon-alert'}
     ];

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

OpenLayers.Projection.defaults = {
	    "EPSG:5181": {
	        units: "m"
	    },
		"EPSG:5186": {
	        units: "m"
	    }
	};

OpenLayers.DOTS_PER_INCH = 96;

// TileCache 클래스 상속하여 수정
OpenLayers.Layer.TileCacheCustom = OpenLayers.Class(OpenLayers.Layer.TileCache, {

	// 초기화 함수 오버라이딩
	initialize: function(name, url, layername, options) {
      this.layername = layername;
      OpenLayers.Layer.Grid.prototype.initialize.apply(this,
                                                       [name, url, {}, options]);
      this.extension = this.format.split('/')[1].toLowerCase();
		// jpg를 jpeg로 변경하는 부분을 막음
      // this.extension = (this.extension == 'jpg') ? 'jpeg' : this.extension;
  },

	// ImageMapper 에 맞게 영역 계산 하는 부분 수정
	getURL: function(bounds) {

      var res = this.map.getResolution();
      var bbox = this.maxExtent;
      var size = this.tileSize;
      /*
		 * var tileX = Math.round((bounds.left - bbox.left) / (res * size.w));
		 * var tileY = Math.round((bounds.bottom - bbox.bottom) / (res *
		 * size.h)); var tileZ = this.serverResolutions != null ?
		 */
      var tileX = bounds.bottom;
      var tileY = bounds.left;
      var tileZ = this.serverResolutions != null ?
          OpenLayers.Util.indexOf(this.serverResolutions, res) :
          this.map.getZoom();
      /**
		 * Zero-pad a positive integer. number - {Int} length - {Int}
		 *
		 * Returns: {String} A zero-padded string
		 */

      function zeroPad(number, length) {
          number = String(number);
          var zeros = [];
          for(var i=0; i<length; ++i) {
              zeros.push('0');
          }
          return zeros.join('').substring(0, length - number.length) + number;
      }
      var components = [
          this.layername,
          zeroPad(tileZ, 2),
          /**
			 * zeroPad(parseInt(203676.18 / 1000000), 3),
			 * zeroPad((parseInt(203676.18 / 1000) % 1000), 3),
			 * zeroPad((parseInt(203676.18) % 1000), 3),
			 * zeroPad(parseInt(197361.87 / 1000000), 3),
			 * zeroPad((parseInt(197361.87 / 1000) % 1000), 3),
			 * zeroPad((parseInt(197361.87 ) % 1000), 3) + '.' + this.extension
			 */
          zeroPad(parseInt(tileX / 1000000), 3),
          zeroPad((parseInt(tileX / 1000) % 1000), 3),
          zeroPad((parseInt(tileX) % 1000), 3),
          zeroPad(parseInt(tileY / 1000000), 3),
          zeroPad((parseInt(tileY / 1000) % 1000), 3),
          zeroPad((parseInt(tileY ) % 1000), 3) + '.' + this.extension
      ];
      var path = components.join('/');
		/*
		 * 버전정보가 설정 되어 있을 경우 버전 정보에 맞게 수정
		 */
		if(this.version) path += "?v=" + this.version;
      var url = this.url;
      if (url instanceof Array) {
          url = this.selectUrl(path, url);
      }
      url = (url.charAt(url.length - 1) == '/') ? url : url + '/';
      return url + path;
  },

	CLASS_NAME: "OpenLayers.Layer.TileCacheCustom"
});



// 인덱스 맵 이벤트 객체
OpenLayers.Control.indexMapCustom = OpenLayers.Class(OpenLayers.Control.ZoomBox, {

	// 기준 맵
  baseMap : null,

	// Box Handler 오버라이딩
  draw: function() {
      this.handler = new OpenLayers.Handler.BoxCustom( this,
                          {done: this.zoomBox}, {indexMap: true});
  },

	// 생성자 함수
  initialize: function (baseMap, options) {
  	this.baseMap = baseMap;
  	OpenLayers.Control.prototype.initialize.apply(this, [options]);
  },

	// zoomBox 에 따른 기준 지도 영역 이동
  zoomBox: function (position) {
  	if(position instanceof OpenLayers.Bounds) {
  		if (!this.out) {
              var minXY = this.map.getLonLatFromPixel(
                          new OpenLayers.Pixel(position.left, position.bottom));
              var maxXY = this.map.getLonLatFromPixel(
                          new OpenLayers.Pixel(position.right, position.top));
              var bounds = new OpenLayers.Bounds(minXY.lon, minXY.lat,
                                             maxXY.lon, maxXY.lat);
          } else {
              var pixWidth = Math.abs(position.right-position.left);
              var pixHeight = Math.abs(position.top-position.bottom);
              var zoomFactor = Math.min((this.map.size.h / pixHeight),
                  (this.map.size.w / pixWidth));
              var extent = this.map.getExtent();
              var center = this.map.getLonLatFromPixel(
                  position.getCenterPixel());
              var xmin = center.lon - (extent.getWidth()/2)*zoomFactor;
              var xmax = center.lon + (extent.getWidth()/2)*zoomFactor;
              var ymin = center.lat - (extent.getHeight()/2)*zoomFactor;
              var ymax = center.lat + (extent.getHeight()/2)*zoomFactor;
              var bounds = new OpenLayers.Bounds(xmin, ymin, xmax, ymax);
          }
  		this.baseMap.zoomToExtent(bounds, true);
  	}
  	else { // it's a pixel
  		this.baseMap.setCenter(this.map.getLonLatFromPixel(position), this.baseMap.numZoomLevels-1);
  	}
  },

  CLASS_NAME: 'OpenLayers.Control.indexMapCustom'
});

// 인덱스 맵에 따른 박스 그리기 핸들러 수정
OpenLayers.Handler.BoxCustom = OpenLayers.Class(OpenLayers.Handler.Box, {
	// 인덱스 맵에서 사용 여부
	indexMap : false,

	// 영역 박스 시작 부분 수정
	startBox: function (xy) {
		if(this.indexMap && this.zoomBox) this.removeBox();

	    this.zoomBox = OpenLayers.Util.createDiv('zoomBox',
	                                             this.dragHandler.start);
	    this.zoomBox.className = this.boxDivClassName;
	    this.zoomBox.style.zIndex = this.map.Z_INDEX_BASE["Popup"] - 1;
	    this.map.viewPortDiv.appendChild(this.zoomBox);

	    OpenLayers.Element.addClass(
	        this.map.viewPortDiv, "olDrawBox"
	    );
	},

	// 기준 지도 이동에 따른 색인도 영역 박스 다시 그림
	applyBox: function (bounds) {
		if(this.indexMap && this.zoomBox) this.removeBox();

		this.dragHandler.start = this.map.getPixelFromLonLat(new OpenLayers.LonLat(bounds.left, bounds.top));
		var endPixel = this.map.getPixelFromLonLat(new OpenLayers.LonLat(bounds.right, bounds.bottom));
		var width = endPixel.x - this.dragHandler.start.x;
		var height = endPixel.y - this.dragHandler.start.y;

		this.zoomBox = OpenLayers.Util.createDiv('zoomBox', this.dragHandler.start);
		this.zoomBox.className = this.boxDivClassName;
		this.zoomBox.style.zIndex = this.map.Z_INDEX_BASE["Popup"] - 1;
		this.map.viewPortDiv.appendChild(this.zoomBox);
		this.zoomBox.style.width = width + "px";
		this.zoomBox.style.height = height + "px";
  },

	// 영역 지정 완료 후 다시 그리거나 초기화 전 까지 삭제 안함
	endBox: function(end) {
	    var result;
	    if (Math.abs(this.dragHandler.start.x - end.x) > 5 ||
	        Math.abs(this.dragHandler.start.y - end.y) > 5) {
	        var start = this.dragHandler.start;
	        var top = Math.min(start.y, end.y);
	        var bottom = Math.max(start.y, end.y);
	        var left = Math.min(start.x, end.x);
	        var right = Math.max(start.x, end.x);
	        result = new OpenLayers.Bounds(left, bottom, right, top);
	    } else {
	        result = this.dragHandler.start.clone(); // i.e. OL.Pixel
	    }

		if(!this.indexMap) {
			this.removeBox();
		}

	    this.callback("done", [result]);
	},

	CLASS_NAME: "OpenLayers.Handler.BoxCustom"
});


// 면적 측정
OpenLayers.Handler.PolygonMeasureCustom = OpenLayers.Class(OpenLayers.Handler.Polygon, {
	popup : null,

	// 면적 계산
	measureArea : function() {
		// 현재 거리 측정에 사용된 geometry값의 복사본을 가져옴
		var geometry = this.geometryClone();

		// geometry.getArea() - geometry의 면적을 구함
// var subLength = geometry.getArea();
		var subLength = geometry.getGeodesicArea(EMD.map.projection);

		// 단위 계산을 위해 tempLength로 거리를 저장
		var tempLength = subLength;

		// tempLength 에 km의 제곱 단위를 적용
		tempLength *= Math.pow(OpenLayers.INCHES_PER_UNIT["m"] / OpenLayers.INCHES_PER_UNIT['km'], 2);

		// km의 제곱 단위를 적용 후 결과가 1 이상일 경우에만 km 제곱 단위를 사용
	    if(tempLength > 1) subLength = tempLength.toFixed(2) + "km" + "<sup>2</" + "sup>";
	    // 그렇지 않을 경우 m 단위를 사용
	    else subLength = subLength.toFixed(2) + "m" + "<sup>2</" + "sup>";

	    // 계산 결과 값을 리턴
		return subLength;
	},


	// 마우스 다운 이벤트
	mousedown: function(evt) {
		if (this.lastDown && this.lastDown.equals(evt.xy)) {
	       return false;
	    }
		if(this.lastDown == null) {
	    	// 멀티 라인일 경우 이전 측정 결과를 삭제 하지 않음
			if(!this.multiLine) {
				if(this.persist) { this.destroyFeature();  }
				this.removePopup();
			}
	        this.createFeature(evt.xy);
	    } else if((this.lastUp == null) || !this.lastUp.equals(evt.xy)) {
	        this.addPoint(evt.xy);
	    }
	    this.lastDown = evt.xy;
	    this.drawing = true;

		// 마우스 다운 시 생성 되도록 mouseup 의 소스를 이동
      if(this.freehandMode(evt)) {
          this.removePoint();
          this.finalize();
      } else {
          if(this.lastUp == null) {
             this.addPoint(evt.xy);
          }
          this.lastUp = evt.xy;
      }

		// point Feature를 나타낼 지도 좌표를 구함
	    var lonlat = this.map.getLonLatFromPixel(evt.xy);

		// point Feature를 나타낼 지도 좌표를 구함
  	var pointFeature = new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Point(lonlat.lon, lonlat.lat));
  	// point Feature 등록
  	this.layer.addFeatures(pointFeature);

		var popup;
	    /* 마우스 다운 이벤트 작동 수를 저장 */
	    if(!this.count) {
      	contentHtml = "<div class='olControlMeasurePopup'>시작</div>";
      	popup = new PopupCustom("measurePopup", lonlat, null, contentHtml, this.layer.map, new OpenLayers.Pixel(5,5));

			contentHtml = "<div class='olControlMeasurePopup'>"+ this.measureArea() +"</div>";
      	this.popup = new PopupCustom("measurePopup", lonlat, null, contentHtml, this.layer.map, new OpenLayers.Pixel(5,5));

      	this.map.addPopup(this.popup);

			this.popup.updateSize();
			this.popup.type = "measure";

	    	// 클릭 횟수 저장 변수를 생성 및 초기화
          this.count = 1;
	    }
	    else {
			if(this.count > 1) {
				contentHtml = "<div class='olControlMeasurePopup'>"+ this.measureArea() +"</div>";
	        	popup = new PopupCustom("measurePopup", lonlat, null, contentHtml, this.layer.map, new OpenLayers.Pixel(5,5));
			}

	        this.count += 1;
	    }

		if (popup) {
			this.map.addPopup(popup);
			popup.type = "measure";
			popup.updateSize();
		}

  	// 마우스 우클릭 일때 실행
  	if(evt.button == "2") {
			this.rightclick(evt);
	        return true;
		}

	    return false;
	},

	// 마우스 이동 이벤트
	mousemove: function (evt) {
      if(this.drawing) {
          if(this.mouseDown && this.freehandMode(evt)) {
              this.addPoint(evt.xy);
          } else {
              this.modifyFeature(evt.xy);

				// 팝업을 마우스 포인터를 따라 다니게 한다.
				if(this.popup) {
					contentHtml = "<div class='olControlMeasurePopup'>"+ this.measureArea() +"</div>";
					this.popup.setContentHTML(contentHtml);
					this.popup.updateSize();
	                this.popup.moveTo(evt.xy);
				}
          }
      }
      return true;
  },

	// 마우스 우클릭(종료) 이벤트
	rightclick: function(evt) {
  	this.dblclick(evt);
  	return false;
  },

	// 더블클릭 (종료) 이벤트
	dblclick: function(evt) {
		if(this.count < 3) {
			alert('면적은 3개 이상의 지점을 선택해야 합니다.');
			return false;
		}

		// 더블클릭시 클릭 카운트 초기화
		this.count = 0;
      if(!this.freehandMode(evt)) {
          var index = this.line.geometry.components.length - 1;
          this.line.geometry.removeComponent(this.line.geometry.components[index]);
          this.removePoint();
          this.finalize();
      }

		if(this.popup) {
			this.map.removePopup(this.popup);
			this.popup = null;
		}

      return false;
  },

	 // 컨트롤 비활성화
  deactivate: function() {
      if(!OpenLayers.Handler.prototype.deactivate.apply(this, arguments)) {
          return false;
      }
      // call the cancel callback if mid-drawing
      if(this.drawing) {
          this.cancel();
      }
      // this.destroyFeature();

      // If a layer's map property is set to null, it means that that layer
      // isn't added to the map. Since we ourself added the layer to the map
      // in activate(), we can assume that if this.layer.map is null it means
      // that the layer has been destroyed (as a result of map.destroy() for
      // example.

      // 컨트롤 비 활성 시 측정 결과 유지 여부
      if(!this.persistControl) {
      	this.layer.destroy(false);
			this.removePopup();
      }

      this.layer.prevLayer = true;

      this.layer = null;
      return true;
  },

	// 측정팝업 삭제
	removePopup : function() {
		var len = this.map.popups.length;
		for(var i=len-1; i >= 0; i--) {
			if(this.map.popups[i].type == "measure") {
				this.map.removePopup(this.map.popups[i]);
			}
		}
	},

  CLASS_NAME: "OpenLayers.Handler.PolygonMeasureCustom"
});


// 거리 측정
OpenLayers.Handler.PathMeasureCustom = OpenLayers.Class(OpenLayers.Handler.Path, {
	popup : null,

	// 거리 측정
	measureDistance : function() {
		// 현재 거리 측정에 사용된 geometry값의 복사본을 가져옴
		var geometry = this.geometryClone();

		// geometry.getLength() - geometry의 거리를 구함
// var subLength = geometry.getLength();
		var subLength = geometry.getGeodesicLength(EMD.map.projection);
		// 단위 계산을 위해 tempLength로 거리를 저장
  	var tempLength = subLength;

  	// tempLength 에 km 단위를 적용
  	tempLength *= (OpenLayers.INCHES_PER_UNIT["m"] / OpenLayers.INCHES_PER_UNIT['km']);

  	// km 단위를 적용 후 거리가 1km 이상일 경우 km 단위를 사용
      if(tempLength > 1) subLength = tempLength.toFixed(2) + "km";

      // 그렇지 않을 경우 m 단위를 사용
      else subLength = subLength.toFixed(2) + "m";

      // 계산 결과 값을 리턴
		return subLength;
	},

	// 마우스 다운 이벤트
	mousedown: function(evt) {
		if (this.lastDown && this.lastDown.equals(evt.xy)) {
	        return false;
	    }
		if(this.lastDown == null) {
	    	// 멀티 라인일 경우 이전 측정 결과를 삭제 하지 않음
			if(!this.multiLine) {
				if(this.persist) { this.destroyFeature();  }
				this.removePopup();
			}
	        this.createFeature(evt.xy);
	    } else if((this.lastUp == null) || !this.lastUp.equals(evt.xy)) {
	        this.addPoint(evt.xy);
	    }
	    this.lastDown = evt.xy;
	    this.drawing = true;

		// 마우스 다운 시 생성 되도록 mouseup 의 소스를 이동
      if(this.freehandMode(evt)) {
          this.removePoint();
          this.finalize();
      } else {
          if(this.lastUp == null) {
             this.addPoint(evt.xy);
          }
          this.lastUp = evt.xy;
      }

      // point Feature를 나타낼 지도 좌표를 구함
      var lonlat = this.map.getLonLatFromPixel(evt.xy);

      // point Feature 생성
  	var pointFeature = new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Point(lonlat.lon, lonlat.lat));
  	// point Feature 등록
  	this.layer.addFeatures(pointFeature);

		var popup;
  	// 처음 일 경우 시작 메시지 팝업창 생성
      if(!this.count) {
			var contentHtml = "<div class='olControlMeasurePopup'>시작</div>";
			popup = new PopupCustom("measurePopup", lonlat, null, contentHtml, this.layer.map, new OpenLayers.Pixel(5,5));

			contentHtml = "<div class='olControlMeasurePopup'>"+ this.measureDistance() +"</div>";
      	this.popup = new PopupCustom("measurePopup", lonlat, null, contentHtml, this.layer.map, new OpenLayers.Pixel(5,5));

      	this.map.addPopup(this.popup);

			this.popup.updateSize();
			this.popup.type = "measure";

	    	// 클릭 횟수 저장 변수를 생성 및 초기화
          this.count = 1;
	    }
      // 처음 클릭이 아닐 경우
	    else {
	    	contentHtml = "<div class='olControlMeasurePopup'>"+ this.measureDistance() + "</div>";
	    	popup = new PopupCustom("measurePopup", lonlat, null, contentHtml, this.layer.map, new OpenLayers.Pixel(5,5));
	    	// 클릭 횟수 증가
          this.count += 1;
	    }

		if (popup) {
			this.map.addPopup(popup);
			popup.type = "measure";
			popup.updateSize();
		}

      // 마우스 우클릭 일때 실행
      if(evt.button == "2") {
			this.rightclick(evt);
	        return true;
		}

      /* 거리 계산 결과 출력 팝업 추가 끝 */
      return false;
	},

	// 마우스 이동 이벤트
	mousemove: function (evt) {
      if(this.drawing) {
          if(this.mouseDown && this.freehandMode(evt)) {
              this.addPoint(evt.xy);
          } else {
              this.modifyFeature(evt.xy);

				// 팝업을 마우스 포인터를 따라 다니게 한다.
				if(this.popup) {
					contentHtml = "<div class='olControlMeasurePopup'>"+this.measureDistance()+"</div>";
					this.popup.setContentHTML(contentHtml);
					this.popup.updateSize();
	                this.popup.moveTo(evt.xy);
				}
          }
      }
      return true;
  },

	// 마우스 우클릭(종료) 이벤트
	rightclick: function(evt) {
  	this.dblclick(evt);
  	return false;
  },

	// 더블클릭(종료) 이벤트
	dblclick: function(evt) {
		// 더블클릭시 클릭 카운트 초기화
		this.count = 0;
      if(!this.freehandMode(evt)) {
          var index = this.line.geometry.components.length - 1;
          this.line.geometry.removeComponent(this.line.geometry.components[index]);
          this.removePoint();
          this.finalize();
      }

		if(this.popup) {
			this.map.removePopup(this.popup);
			this.popup = null;
		}

      return false;
  },

	 // 컨트롤 비 활성화
  deactivate: function() {
      if(!OpenLayers.Handler.prototype.deactivate.apply(this, arguments)) {
          return false;
      }
      // call the cancel callback if mid-drawing
      if(this.drawing) {
          this.cancel();
      }
      // this.destroyFeature();

      // If a layer's map property is set to null, it means that that layer
      // isn't added to the map. Since we ourself added the layer to the map
      // in activate(), we can assume that if this.layer.map is null it means
      // that the layer has been destroyed (as a result of map.destroy() for
      // example.

      // 컨트롤 비 활성 시 측정 결과 유지 여부
      if(!this.persistControl) {
      	this.layer.destroy(false);
			this.removePopup();
      }

      this.layer.prevLayer = true;

      this.layer = null;
      return true;
  },

	// 측정팝업 삭제
	removePopup : function() {
		var len = this.map.popups.length;
		for(var i=len-1; i >= 0; i--) {
			if(this.map.popups[i].type == "measure") {
				this.map.removePopup(this.map.popups[i]);
			}
		}
	},

  CLASS_NAME: "OpenLayers.Handler.PathMeasureCustom"
});


// 팝업
PopupCustom = OpenLayers.Class(OpenLayers.Popup, {

	// 선택한 위치와의 거리
	offsetPixel : null,

	initialize:function(id, lonlat, contentSize, contentHTML, map, offsetPixel) {
		if(offsetPixel) {
			this.offsetPixel = offsetPixel;
		}

      if (id == null) {
          id = OpenLayers.Util.createUniqueID(this.CLASS_NAME + "_");
      }

      this.id = id;
      this.lonlat = lonlat;

      this.contentSize = (contentSize != null) ? contentSize
                                : new OpenLayers.Size(
                                                 OpenLayers.Popup.WIDTH,
                                                 OpenLayers.Popup.HEIGHT);
      if (contentHTML != null) {
           this.contentHTML = contentHTML;
      }
      this.backgroundColor = OpenLayers.Popup.COLOR;
      this.opacity = OpenLayers.Popup.OPACITY;
      this.border = OpenLayers.Popup.BORDER;

      this.div = OpenLayers.Util.createDiv(this.id, null, null,
                                           null, null, null, "hidden");
      this.div.className = this.displayClass;

      var groupDivId = this.id + "_GroupDiv";
      this.groupDiv = OpenLayers.Util.createDiv(groupDivId, null, null,
                                                  null, "relative", null,
                                                  "hidden");

      var id = this.div.id + "_contentDiv";
      this.contentDiv = OpenLayers.Util.createDiv(id, null, this.contentSize.clone(),
                                                  null, "relative");
      this.contentDiv.className = this.contentDisplayClass;
      this.groupDiv.appendChild(this.contentDiv);
      this.div.appendChild(this.groupDiv);

		/*
		 * 닫기 버튼 사용 안할 것으로 판단되어 삭제 if (closeBox) {
		 * this.addCloseBox(closeBoxCallback); }
		 */

      this.registerEvents();
  },

	// 팝업 위치 반환
	getLonLat : function() {
		return this.lonlat;
	},

	// 이동에 따른 팝업 위치 변경
  moveTo: function(px) {
      if ((px != null) && (this.div != null)) {
			// x, y 좌표의 픽셀을 offset으로 지정한 값만큼 증가 시킴
	    	if(this.offsetPixel) {
				px = px.add(this.offsetPixel.x, this.offsetPixel.y);
	    	}

			this.div.style.left = px.x + "px";
      	this.div.style.top = px.y + "px";
      }
  },

	CLASS_NAME: "PopupCustom"
});

// 객체 선택
OpenLayers.Control.ClickFeature = OpenLayers.Class(OpenLayers.Control, {

	initialize: function(options) {
      OpenLayers.Control.prototype.initialize.apply(this, arguments);
  },

	/**
	 * APIMethod: activate
	 */
  activate: function() {
      if (OpenLayers.Control.prototype.activate.apply(this, arguments)) {
          this.map.events.register('mousemove', this, this.redraw);
          this.redraw();
          return true;
      } else {
          return false;
      }
  },

  /**
	 * APIMethod: deactivate
	 */
  deactivate: function() {
      if (OpenLayers.Control.prototype.deactivate.apply(this, arguments)) {
          this.map.events.unregister('mousemove', this, this.redraw);
          this.element.innerHTML = "";
          return true;
      } else {
          return false;
      }
  },

	CLASS_NAME : "OpenLayers.Control.ClickFeature"
});

// 클릭 이벤트
OpenLayers.Control.Click = OpenLayers.Class(OpenLayers.Control, {
              defaultHandlerOptions: {
                  'single': true,
                  'double': false,
                  'pixelTolerance': 0,
                  'stopSingle': false,
                  'stopDouble': false
              },

              initialize: function(options) {
                  this.handlerOptions = OpenLayers.Util.extend(
                      {}, this.defaultHandlerOptions
                  );
                  OpenLayers.Control.prototype.initialize.apply(
                      this, arguments
                  );
                  this.handler = new OpenLayers.Handler.Click(
                      this, {
                          'click': this.onClick,
                          'dblclick': this.onDblclick
                      }, this.handlerOptions
                  );
              },

              onClick: function(evt) {
                  var output = document.getElementById(this.key + "Output");
                  var msg = "click " + evt.xy;
                  output.value = output.value + msg + "\r\n";
              },

              onDblclick: function(evt) {
                  var output = document.getElementById(this.key + "Output");
                  var msg = "dblclick " + evt.xy;
                  output.value = output.value + msg + "\n";
              }

});
OpenLayers.Control.CustomGetFeature = OpenLayers.Class(OpenLayers.Control.GetFeature, {
	polygon : false,

	regularPolygon : false,

	all : false,

	map : null,

	initialize : function(options) {
		OpenLayers.Control.GetFeature.prototype.initialize.apply(this, [options]);

		if(this.polygon) {
      	this.handlers.polygon = new OpenLayers.Handler.Polygon(
      			this, {
      				done : this.selectPolygon
      			});
      }

      if(this.regularPolygon) {
      	this.handlers.regularPolygon = new OpenLayers.Handler.RegularPolygon(
      			this,{done : this.selectRegularPolygon},{
      				sides : 50,
      				persist : false
      			});
      }

      if(this.all) {
      	var bounds = this.map.getExtent();
      	this.setModifiers(this.map.events);
      	this.request(bounds);
      }
	},
  selectRegularPolygon : function(evt) {
  	var bounds = evt.getBounds();
      this.setModifiers(this.handlers.regularPolygon.evt);
      this.request(bounds);
      OpenLayers.Util.deletePopup(); // popup 메뉴를 삭제한다.

  },
  selectPolygon : function(evt) {
  	var bounds = evt.getBounds();
      this.setModifiers(this.handlers.polygon.evt);
      this.request(bounds);
  },
	CLASS_NAME: "OpenLayers.Control.CustomGetFeature"
});

/*
 * Copyright (c) 2006-2012 by OpenLayers Contributors (see authors.txt for full
 * list of contributors). Published under the 2-clause BSD license. See
 * license.txt in the OpenLayers distribution or repository for the full text of
 * the license.
 */
Proj4js.defs["EPSG:4326"]="+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs ";
Proj4js.defs["EPSG:5181"] = "+proj=tmerc +lat_0=38 +lon_0=127 +k=1 +x_0=200000 +y_0=500000 +ellps=GRS80 +units=m +no_defs";
Proj4js.defs["EPSG:5186"] = "+proj=tmerc +lat_0=38 +lon_0=127 +k=1 +x_0=200000 +y_0=600000 +ellps=GRS80 +units=m +no_defs";

// IE11 fixed 2015.07.23 by verstar
var _class = OpenLayers.Format.XML;

if(originalWriteFunction==undefined) {
	var originalWriteFunction = _class.prototype.write;

	var patchedWriteFunction = function()
	{
		var child = originalWriteFunction.apply( this, arguments );

		// IE11에서 XML로 WFS 요청시 xmlns:NS1="" 이렇게 요청하여 gis 엔진에서 오류가 발생하여 픽스 함.
		child = child.replace(new RegExp('xmlns:NS\\d+="" NS\\d+:', 'g'), '');

		return child;
	};

	_class.prototype.write = patchedWriteFunction;
}

var EMD = (function($, window, document, undefined) {
	var browser = $.browser;

	if (!browser) {
		var uaMatch = function( ua ) {
			ua = ua.toLowerCase();

			var match = /(chrome)[ \/]([\w.]+)/.exec( ua ) ||
				/(webkit)[ \/]([\w.]+)/.exec( ua ) ||
				/(opera)(?:.*version|)[ \/]([\w.]+)/.exec( ua ) ||
				/(msie) ([\w.]+)/.exec( ua ) ||
				ua.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec( ua ) ||
				[];

			return {
				browser: match[ 1 ] || "",
				version: match[ 2 ] || "0"
			};
		};

		var matched = uaMatch( navigator.userAgent );
		browser = {};

		if ( matched.browser ) {
			browser[ matched.browser ] = true;
			browser.version = matched.version;
		}

		// Chrome is Webkit, but Webkit is also Safari.
		if ( browser.chrome ) {
			browser.webkit = true;
		} else if ( browser.webkit ) {
			browser.safari = true;
		}
	}

  // Expose innards of EMD.
  return {
    go: function(ctr, mapBaseUrl, gisEngineUrl, serverUrl, frmwrkMenu) {
    	if(ctr.length==0 || ctr.substring(ctr.length-1)=='/') ctr=ctr.substring(0, ctr.length-1);
    	EMD.context_root=ctr;
    	EMD.gis_engine_url=gisEngineUrl;
    	EMD.serverUrl=serverUrl;

        OpenLayers.ImgPath = EMD.context_root+"/images/egovframework/ygpa/gam/maps/";
        OpenLayers.theme = EMD.context_root+"/js/theme/ygpa/";
        // Proj4js.libPath = EMD.context_root+"/js/Proj4js/";

        EMD.map_base_url=mapBaseUrl;

    	switch(serverUrl) {
    	case "http://lfitsvr.iptime.org":
    		EMD.vworldKey="B60CBFE2-E105-39B4-B7E7-C737F1512A16";
    		break;
    	case "http://192.168.0.40":
    		EMD.vworldKey="474AD190-F9E9-31C6-BE7C-15EFD66E5DAD";
    		break;
    	case "http://localhost":
    		EMD.vworldKey="5E7D605D-BD5A-3BEE-8C20-62172A86B4AA";
    		break;
		default:
			EMD.serverUrl="http://localhost";
			EMD.vworldKey="5E7D605D-BD5A-3BEE-8C20-62172A86B4AA";
			break;
    	}

    	EMD._frmwrkMenu = frmwrkMenu;

    	OpenLayers.ProxyHost = EMD.context_root+"/proxy.jsp?url=";

    	var auth_init = function() {
    		if(EMD._frmwrkMenu!=null) {
    			var mnu = EMD._frmwrkMenu;
    			for(var i=0; i<mnu.length; i++) {
    				switch(mnu[i].menuNo) {
    				case '2100000':	// start function
    					var sub = mnu[i].submenu;
    					if(sub instanceof Array) {
    						EMD.loadAtStartWindow=sub;
    					}
    					break;
    				case '2200000':	// utility function
    					var sub = mnu[i].submenu;
    					if(sub instanceof Array) {
    						for(var j=0; j<sub.length; j++) {
    							EMD.userFunc[sub[j].url]=sub[j].menuNm;
    							// EMD.util.create_window(sub[j].menuNm,
								// sub[j].url, null, {
    							// action: "loadStart"
    							// });
    						}
    					}
    					break;
    				case '2300000':	// popup function
    					var sub = mnu[i].submenu;
    					if(sub instanceof Array) {
    						for(var j=0; j<sub.length; j++) {
    							EMD.popupMenu[sub[j].progrmStrePath]=sub[j].url;
    							// EMD.util.create_window(sub[j].menuNm,
								// sub[j].url, null, {
    							// action: "loadStart"
    							// });
    						}
    					}
    					break;
    				case '2400000':	// layer display
    					var sub = mnu[i].submenu;
    					if(sub instanceof Array) {
    						for(var j=0; j<sub.length; j++) {
    							EMD.layerDisplay[sub[j].progrmStrePath]=sub[j].url;
    							// EMD.util.create_window(sub[j].menuNm,
								// sub[j].url, null, {
    							// action: "loadStart"
    							// });
    						}
    					}
    					break;
    				}
    			}
    		}
    		// map init after user info
        	for (var i in EMD.init) {
        		EMD.init[i]();
        	}
    	}
    	if($DEBUG) {
    		EMD.userinfo = {
    				name: '디버그',
    				authorities: ['ROLE_ADMIN'],
    				userId: 'TEST1',
    				quayGroupCd: '*',
    				mngFcltyCd: '*',
    				deptCd: '99999'
    		};

    		auth_init();

    		EMD.userLayer.gisArchFclty.setVisibility(true);
    		EMD.userLayer.gisCivilFclty.setVisibility(true);
    		EMD.userLayer.gisMechFclty.setVisibility(true);
    		EMD.userLayer.gisElecFclty.setVisibility(true);
    		EMD.userLayer.gisTeleFclty.setVisibility(true);
    	}
    	else {
    		$.ajax({
    			url: EMD.context_root+"/main/getUserInfo.do",
    			type: 'POST',
    			async: true,
    			dataType: 'json',
    			global: false,
    			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    			// contentType: "text/plain; charset=UTF-8",
    			data: ''
    		}).done(function(data) {
    			if(data.resultCode=="1") {
    				alert(data.message);
    				$.post(EMD.context_root+'/uat/uia/YGActionLogout.do').done(function() {
    					window.location.reload();
    				});
    				return;
    			}
//    			console.log(data);
    			EMD.userinfo = data.userInfo;
    			if(EMD.userLayer.gisArchFclty!=null && EMD.userinfo.mngFcltyCd=="A") {
    				EMD.userLayer.gisArchFclty.setVisibility(true);
    			}
    			if(EMD.userLayer.gisMechFclty!=null && EMD.userinfo.mngFcltyCd=="M") {
    				EMD.userLayer.gisMechFclty.setVisibility(true);
    			}
    			if(EMD.userLayer.gisElecFclty!=null && EMD.userinfo.mngFcltyCd=="E") {
    				EMD.userLayer.gisElecFclty.setVisibility(true);
    			}
    			if(EMD.userLayer.gisTeleFclty!=null && EMD.userinfo.mngFcltyCd=="I") {
    				EMD.userLayer.gisTeleFclty.setVisibility(true);
    			}
    			if(EMD.userLayer.gisCivilFclty!=null && EMD.userinfo.mngFcltyCd=="C") {
    				EMD.userLayer.gisCivilFclty.setVisibility(true);
    			}
    			$('#logUserName').text(EMD.userinfo.name);

//    			console.log(data.userCfg);
    			EMD.userCfg.startZoom = data.userCfg.startZoom;
    			EMD.userCfg.startLat = data.userCfg.startLat;
    			EMD.userCfg.startLon = data.userCfg.startLon;

    			auth_init();
    		});
    	}
    },
    _frmwrkMenu: null,
    context_root: '',
    wikiUrl: '',
    map_base_url: '',
    gis_engine_url: '',
    serverUrl: "",
    vworldKey: "",
    window_seq: 0,
    legendWin: null,
    loadAtStartWindow: [],
    win_x: 80,
    win_y: 40,
    drag_handle: null,	// window dragging
    dragging: false,
    drag_ui: null,
    userinfo: null,
    userCfg: {
    	startZoom: 12,
    	startLat: 260673,
    	startLon: 257195
    },
    popupCaller: null,
    popupId: null,
    mapPopupId: 0,
    popupParams: null,
    chart_id: 0,
    map: null,
    map_state: null,
    map_value: null,
    map_callback: null,
    gyhmap: null,
    wfs: null,
    lotarea: null,
    lotareaProtocol: null,
    panel: null,
    measurePanel: null,
    controls: {
    	measureDist: null,
    	measureArea: null
    },
    measureControls: null,
    editControls: [],
    _currentEditLayer: null,
    draw: null,
    edit: null,
    edit_win: null,
    del: null,
    info: null,
    save: null,
    cancel: null,
    saveStrategy:null,
    refreshStrategy: null,
    transaction: {
    	type: null,
    	callbackWindowId: null
	},
    map_panel: null,
    userLayer: {
    	custLand: null,
        landCode: null,
    	prtFclty: null,
    	locArea: null,
    	gisAssetsCd: null,
    	gisPrtFcltyCd: null,
    	gisArchFclty: null,
    	gisTeleFclty: null,
    	gisMechFclty: null,
    	gisElecFclty: null,
    	gisCivilFclty: null,
    	assetsRent: null,
    	assetsRentDetail: null,
    	fcltyMech: null,
    	fcltyIT: null,
    	fcltyCivil: null,
    	fcltyConst: null,
    	assetEPower: null,
    	assetRentStats: null,
    	gnrlRentStats: null,
    	cntrRentStats: null,
    	htldRentStats: null,
    	ccntRentStats: null,
    	trnpRentStats: null,
    	portArea: null,
    	quayGroup: null,
    	quayCd: null,
    	berthCd: null,
    	constructCd: null,
    	fcltyConstMarker: null,
    	fcltySocMarker: null,
    	fcltyMechMarker: null,
    	fcltyInfoTeleMarker: null,
    	fcltyElecPowerMarker: null
	},
	userStyle: {
		assetCdStyle: {
			'default': null,
			'useSttus': null,
			'notUsgArea': null,
			'feeSttus': null,
			'reqSttus': null,
			'evlDtls': null
		},
		assetRentStyle: {
			'default': null,
			'useSttus': null
		},
		assetsRentDetailStyle: {
			'default': null,
			'useSttus': null
		},
		quayStyle: {
			'default': null,
			'useSttus': null,
			'notUsgArea':null,
			'feeSttus': null,
			'reqSttus': null
		}
	},
	popup: {
		prtFcltyInfo:null,
		lotAreaInfo:null
	},
	popupMenu: {},
	layerDisplay: {},
	userFunc: {},
	startLogLotarea: false,
	lotareaStackCnt: 0,
	lotareaFeatures: [],
	symbols: [],
	protocols: {
    	prtFclty: null,
        landCode: null,
    	gisAssetsCd: null,
    	locArea:null,
    	assetsRent: null,
    	assetsRentDetail: null,
    	gisPrtFcltyCd: null
	},
	filters: {
		assetsRent: null
	},
	strategies: {
		assetsRent: null,
		assetsRentDetail: null,
		assetStats: null,
		assetRentStats: null,
		gnrlRentStats: null,
		htldRentStats: null,
		cntrRentStats: null,
		ccntRentStats: null,
		trnpRentStats: null,
	},
	saveLayer: {
		gisAssetsCd: null
	},
	selectControl: null,
	selectInfoControl: null,
	dragFeature: null,
// maxBounds: new OpenLayers.Bounds(-180,-90,180,90), // google boundary
	maxBounds: new OpenLayers.Bounds(-30000, -60000, 494288, 988576),
    maxRes: 0.0439453125,
    maxZoom:14,		// google has 19
    module_instance: [],
    init: {
      frame_breaker: function() {
        if (window.location !== window.top.location) {
          window.top.location = window.location;
        }
      },
      //
      // Initialize the clock.
      //
      clock: function() {
        var clock = $('#clock');

        if (!clock.length) {
          return;
        }

        // Date variables.
        var date_obj = new Date();
        var hour = date_obj.getHours();
        var minute = date_obj.getMinutes();
        var day = date_obj.getDate();
        var year = date_obj.getFullYear();
        var suffix = 'AM';

        // Array for weekday.
        var weekday;

        // Array for month.
        var month;

        weekday = [
        '일',
        '월',
        '화',
        '수',
        '목',
        '금',
        '토'
      ];
       month = [
          '1월',
          '2월',
          '3월',
          '4월',
          '5월',
          '6월',
          '7월',
          '8월',
          '9월',
          '10월',
          '11월',
          '12월'
        ];

        // Assign weekday, month, date, year.
        weekday = weekday[date_obj.getDay()];
        month = month[date_obj.getMonth()];

        // AM or PM?
        if (hour >= 12) {
          suffix = 'PM';
        }

        // Convert to 12-hour.
        if (hour > 12) {
          hour = hour - 12;
        }
        else if (hour === 0) {
          // Display 12:XX instead of 0:XX.
          hour = 12;
        }

        // Leading zero, if needed.
        if (minute < 10) {
          minute = '0' + minute;
        }

        // Build two HTML strings.
        var clock_time = weekday + ' ' + hour + ':' + minute + ' ' + suffix;
        var clock_date = month + ' ' + day + ', ' + year;

        // Shove in the HTML.
        clock.html(clock_time).attr('title', clock_date);

        // Update every 60 seconds.
        setTimeout(EMD.init.clock, 60000);
      },
      //
      // Initialize the desktop.
      //
      desktop: function() {
        // Alias to document.
        var d = $(document);

        $('#progress_dialog').dialog({
        	autoOpen: false,
        	width: 180,
        	height: 66,
        	modal: true,
        	dialogClass: "progress"
        });
        $('#file_upload_dialog').dialog({
        	autoOpen: false,
        	width: 640,
        	height: 480,
        	modal: true,
        	resizable: true,
        	dialogClass: "fileupload"
        });
        $('#pfPhoto_upload_dialog').dialog({
        	autoOpen: false,
        	width: 640,
        	height: 480,
        	modal: true,
        	resizable: true,
        	dialogClass: "fileupload"
        });
        $('#xlsfile_upload_dialog').dialog({
        	autoOpen: false,
        	width: 400,
        	height: 300,
        	modal: true,
        	resizable: false,
        	dialogClass: "fileupload"
        });
        // Cancel mousedown.
        d.mousedown(function(ev) {
// if (!$(ev.target).closest(tags).length || $(ev.target).is('#desktop')) {
          if(!$(ev.target).is("a.menu_trigger")
        		  && !$(ev.target).is("a")
        		  && !$(ev.target).is("button")
        		  && !$(ev.target).is("input")
        		  && !$(ev.target).is("select")
        		  && !$(ev.target).is("textarea")
        		  && !$(ev.target).is('tr') // ) {
        		  && ($(ev.target).is('#desktop') || $(ev.target).is('.window'))) {
            EMD.util.clear_active();
            ev.preventDefault();
            ev.stopPropagation();
          }
        });

        // Cancel right-click.
        d.on('contextmenu', function() {
          return false;
        });

        // Relative or remote links?
        d.on('click', 'a', function(ev) {
          var url = $(this).attr('href');
          this.blur();

          switch($(this).data('role')) {
          case 'StoreMap':
        	  var center=EMD.map.getCenter();

   			var src_proj = new OpenLayers.Projection("EPSG:900913");
  			var dest_proj = new OpenLayers.Projection("EPSG:5186");
  			var c = center.transform(src_proj, dest_proj);

        	  EMD.util.storeMapState(c.lon, c.lat, EMD.map.getZoom());
        	  break;
          case 'LoadModule':
        	  if($(this).data('url')=='/') return;
        	  if($(this).data('url')=='help') {
        		  EMD.util.showHelp(wikiUrl+'page=Main');
        		  return;
        	  }
        	  EMD.util.create_window($(this).data('progrm-file-nm'), $(this).text(), $(this).data('url'));
        	  break;
          case 'CloseAllWindow':
              EMD.util.clear_active();
        	  $('#desktop').find('.window').each(function() {
        		  EMD.closeWindow($(this));
        	  });
		      break;
          case 'MinimizeAllWindow':
              EMD.util.clear_active();
              if ($('div.window:visible').length) {
                  $('div.window').hide();
                }
        	  break;
          case 'logout':
              EMD.util.clear_active();
        	  $.post(EMD.context_root+'/uat/uia/YGActionLogout.do').done(function() {
        		  window.location.reload();
        	  });
        	  break;
          case 'getUserInfo':
              EMD.util.clear_active();
        	  EMD.init.user_init();
        	  break;
          case 'startLogLandCode':
              EMD.util.clear_active();
		      EMD.lotareaFeatures=[];
		      EMD.lotareaStackCnt=0;
		      EMD.startLogLotarea=true;
        	  break;
          case 'saveLandCode':
              EMD.util.clear_active();
		      EMD.startLogLotarea=false;
		      EMD.protocols.landCode.commit(EMD.lotareaFeatures, {callback:function(resp) {
		          if (resp.error) {
		            alert("error save lotarea");
		            return-1;
		          }
		          alert('lotarea saved.');
		        }});
		      EMD.lotareaFeatures=[];
        	  break;
    	  case 'ShowAllWindow':
              EMD.util.clear_active();
              $('#dock li:visible a').each(function() {
                  $($(this).attr('href')).show();
                });
        	  break;
          case 'SortingWindow':
        	  EMD.util.window_flat();
        	  EMD.win_x=80;
        	  EMD.win_y=40;
        	  $('#desktop').find('.window').each(function() {
        		  EMD.util.window_move($(this).attr('id'), EMD.win_x, EMD.win_y );
            	  EMD.win_x+=30;
            	  EMD.win_y+=20;
        	  });
          }
          if(url==null) return;
          if (url.match(/^#/)) {
            ev.preventDefault();
            ev.stopPropagation();
          }
          else {
            $(this).attr('target', '_blank');
          }
        });

        // Make top menus active.
        d.on('mousedown', 'a.menu_trigger', function() {
          if ($(this).next('ul.menu').is(':hidden')) {
            EMD.util.clear_active();
            $(this).addClass('active').next('ul.menu').show();
          }
          else {
            EMD.util.clear_active();
          }
        });

        // Transfer focus, if already open.
        d.on('mouseenter', 'a.menu_trigger', function() {
          if ($('ul.menu').is(':visible')) {
            EMD.util.clear_active();
            $(this).addClass('active').next('ul.menu').show();
          }
        });

        // Cancel single-click.
        d.on('mousedown', 'a.icon', function() {
          // Highlight the icon.
          EMD.util.clear_active();
          $(this).addClass('active');
        });

        // Respond to double-click.
        d.on('dblclick', 'a.icon', function() {
          // Get the link's target.
          var x = $(this).attr('href');
          var y = $(x).find('a').attr('href');

          // Show the taskbar button.
          if ($(x).is(':hidden')) {
            $(x).remove().appendTo('#dock');
            $(x).show('fast');
          }

          // Bring window to front.
          EMD.util.window_flat();
          $(y).addClass('window_stack').show();
        });

        // Make icons draggable.
        d.on('mouseenter', 'a.icon', function() {
          $(this).off('mouseenter').draggable({
            revert: true,
            containment: 'parent'
          });
        });

        // Taskbar buttons.
        d.on('click', '#dock a', function() {
          // Get the link's target.
          var x = $($(this).attr('href'));

          // Hide, if visible.
          if (x.is(':visible') && x.hasClass('window_stack')) {
        	  EMD.util.window_hide(x);
        	  $(this).removeClass('active');
// x.hide();
// $('#desktop').show();
          }
          else {
        	  if(x.hasClass('active')) {
            	  EMD.util.window_hide(x);
            	  $(this).removeClass('active');
        	  }
        	  else {
            	  EMD.util.window_show(x);
                  $('ul#dock > li > a.active').removeClass('active');
            	  $(this).addClass('active');
        	  }
            // Bring window to front.
// EMD.util.window_flat();
// x.show().addClass('window_stack');
          }
        });

        // Focus active window.
        d.on('mousedown', 'div.window', function() {
          // Bring window to front.
          EMD.util.window_flat();
          $(this).addClass('window_stack');
          $('ul#dock > li > a.active').removeClass('active');
          $('ul#dock > li > a[href=#'+$(this).attr('id')+']').addClass('active');
        });

        // Make windows draggable.
        d.on('mouseenter', 'div.window', function() {
          $(this).off('mouseenter').draggable({
            // Confine to desktop.
            // Movable via top bar only.
            cancel: 'a',
            containment: '#desktop',
            handle: 'div.window_top',
            start: function (event, ui) {
            	EMD.dragging=true;
        		EMD.drag_handle=this;
            },
        	drag: function (event, ui) {
        		EMD.drag_ui=ui;
        	},
        	stop: function (event, ui) {
        		EMD.dragging=false;
        		EMD.drag_ui=ui;
        		EMD.drag_handle=null;
        	}
          }).resizable({
        	containment: 'parent',
            minWidth: 400,
            minHeight: 200,
            start: function (event, ui) {
            	EMD.dragging=true;
        		EMD.drag_handle=this;
            },
        	stop: function (event, ui) {
        		EMD.dragging=false;
        		EMD.drag_ui=ui;
        		EMD.drag_handle=null;
        		EMD.util.window_resized(this);
        	}
          });
        });

        // Double-click top bar to resize, ala Windows OS.
        d.on('dblclick', 'div.window_top', function() {
          EMD.util.window_resize($(this).closest("div.window"));
        });

        // Double click top bar icon to close, ala Windows OS.
        d.on('dblclick', 'div.window_top img', function() {
          // Traverse to the close button, and hide its taskbar button.
          $($(this).closest('div.window_top').find('a.window_close').attr('href')).hide('fast');

          // Close the window itself.
          $(this).closest('div.window').hide();

          // Stop propagation to window's top bar.
          return false;
        });

        // Minimize the window.
        d.on('click', 'a.window_min', function() {
        	EMD.util.window_hide($($(this).attr('href')));

// var win_id=$(this).closest('div.window').attr('href');
// var dock_id = win_id.replace('window', 'dock');
// $('#'+win_id).effect('transfer', {to: '#'+dock_id, className:
// '.ui-effects-transfer'}, 500
// , function(){ $('#'+win).hide(); });
// $(this).closest('div.window').hide();
        });

        d.on('click', 'a.window_help', function() {
        	var href = $(this).attr('href');
        	if(href!=null && href.length>1) {
        		  EMD.util.showHelp(wikiUrl+'page='+href.substring(1));
        	}
        	else {
        		alert('존재하지 않는 매뉴얼 입니다.');
        	}

// var win_id=$(this).closest('div.window').attr('href');
// var dock_id = win_id.replace('window', 'dock');
// $('#'+win_id).effect('transfer', {to: '#'+dock_id, className:
// '.ui-effects-transfer'}, 500
// , function(){ $('#'+win).hide(); });
// $(this).closest('div.window').hide();
        });

        // Maximize or restore the window.
        d.on('click', 'a.window_resize', function() {
          EMD.util.window_resize($($(this).attr('href')));
        });

        // Close the window.
        d.on('click', 'a.window_close', function() {
        	EMD.util.window_close($($(this).attr('href')));
        });

        // Show desktop button, ala Windows OS.
        d.on('mousedown', '#show_desktop', function() {
          // If any windows are visible, hide all.
          if ($('div.window:visible').length) {
            $('div.window').hide();
// EMD.map.setCenter(new OpenLayers.LonLat(14211461.37, 4151377.18), 5);
// EMD.map.setCenter(new OpenLayers.LonLat(126.644, 34.397), 5);
          }
          else {
            // Otherwise, reveal hidden windows that are open.
            $('#dock li:visible a').each(function() {
              $($(this).attr('href')).show();
            });
          }
        });

        $('table.data').each(function() {
          // Add zebra striping, ala Mac OS X.
          $(this).find('tbody tr:odd').addClass('zebra');
        });

        d.on('mousedown', 'table.data tr', function() {
          // Clear active state.
          EMD.util.clear_active();

          // Highlight row, ala Mac OS X.
          $(this).closest('tr').addClass('active');
        });

		d.on('mouseenter', '#cat_menu', function() {
			$(this).addClass("showCategoryMenu", 1000);
		  });
		d.on('mouseout', '#cat_menu', function() {
			setTimeout(function() {
				$(this).removeClass("showCategoryMenu", 1000);
			});
		});

		// side bar
/*
 * $('#cat_menu').tabSlideOut({ tabHandle: '.handle', //class of the element
 * that will become your tab pathToTabImage:
 * EMD.context_root+'/images/egovframework/ygpa/gam/gui/contact_tab.gif', //path
 * to the image for the tab //Optionally can be set using css imageHeight:
 * '122px', //height of tab image //Optionally can be set using css imageWidth:
 * '40px', //width of tab image //Optionally can be set using css tabLocation:
 * 'left', //side of screen where tab lives, top, right, bottom, or left speed:
 * 300, //speed of animation action: 'click', //options: 'click' or 'hover',
 * action to trigger animation topPos: '200px', //position from the top/ use if
 * tabLocation is left or right leftPos: '20px', //position from left/ use if
 * tabLocation is bottom or top fixedPosition: false //options: true makes it
 * stick(fixed position) on scroll });
 */
      },
      wallpaper: function() {
        // Add wallpaper last, to prevent blocking.
        if ($('#desktop').length) {
// $('body').prepend('<img id="wallpaper" class="abs"
// src="assets/images/misc/wallpaper.jpg" />');
        }
      },
      map_init: function() {
// var mapOptions = {
// zoom: 8,
// center: new google.maps.LatLng(34.397, 126.644),
// mapTypeId: google.maps.MapTypeId.ROADMAP
// };
// var map = new google.maps.Map(document.getElementById('desktop'),
// mapOptions);



    	  // google map base
// EMD.map = new OpenLayers.Map({
// div: "desktop",
// projection: new OpenLayers.Projection("EPSG:5186"),
// displayProjection: new OpenLayers.Projection("EPSG:5186"),
// maxExtents: EMD.maxBounds,
// numZoomLevels: EMD.maxZoom,
// maxResolution: EMD.maxRes
// });

    	  // daum map base
// EMD.map = new OpenLayers.Map({
// div: "desktop",
// projection: new OpenLayers.Projection("EPSG:5181"),
// displayProjection: new OpenLayers.Projection("EPSG:4326"),
// units: "m",
// resolutions: [2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1, 0.5, 0.25],
// maxExtent: EMD.maxBounds,
// numZoomLevels: EMD.maxZoom
// });
//
// var basemap = new EMD.gis.OpenLayersDaum("Daum Map", { isBaseLayer : true });
//

		    var initleftbottomX ="-20037508.34";
		    var initleftbottomY ="-20037508.34";
		    var initrighttopX ="20037508.34";
		    var initrighttopY ="20037508.34";
		    initExtent = new OpenLayers.Bounds(initleftbottomX,initleftbottomY,initrighttopX,initrighttopY);

			// 거리, 면적 측정에 사용할 심볼을 설정
			var sketchSymbolizers = {
				"Point" : {
					pointRadius : 4,
					graphicName : "square",
					fillColor : "white",
					fillOpacity : 1,
					strokeWidth : 1,
					strokeOpacity : 1,
					strokeColor : "#333333"
				},
				"Line" : {
					strokeWidth : 3,
					strokeOpacity : 1,
					strokeColor : "#800080"
				},
				"Polygon" : {
					fill : null,
					strokeWidth : 3,
					strokeOpacity : 1,
					strokeColor : "#800080"
				}
			};

			var style = new OpenLayers.Style();
			style.addRules( [ new OpenLayers.Rule( {
				symbolizer : sketchSymbolizers
			}) ]);

			var styleMap = new OpenLayers.StyleMap( {
				"default" : style
			});

		    EMD.map = new OpenLayers.Map('desktop', {
		    	div: 'desktop',
		    	 projection: new OpenLayers.Projection("EPSG:900913"),
		    	 displayProjection: new OpenLayers.Projection("EPSG:5186"),
		    	 units: "m",
		    	 numZoomLevels:25,
// minResolution: 2445.9849047851562,
		    	 maxResolution: 2445.9849047851562,
// maxResolution: 156543.0399,
		    	 allOverlays: true,
		    	 maxExtent:  initExtent,
		    	 theme: EMD.context_root+'/css/theme/ygpa/style.css',
// restrictedExtent: new OpenLayers.Bounds(13406300,3747200,15400000,5360000),
// controls: []
		    	});

		    var basemap = new EMD.gis.OpenLayersVworld("배경지도", {
		    	isBaseLayer : true,
		 		displayInLayerSwitcher: false,
		    	maxExtent: initExtent,
		    	type: 'png',
		    	numZoomLevels: 21,
// zoomOffset: 3,
		    	baseurl: EMD.map_base_url,
		    	projection: new OpenLayers.Projection("EPSG:900913")
		    	});

// var basemap = new OpenLayers.Layer.TMS("배경지도",
// "http://xdworld.vworld.kr:8080/2d/Base/201310/",
// { isBaseLayer: true,
// maxExtent: initExtent,
// // layername: 'index',
// type: 'png',
// numZoomLevels: 16,
// buffer: 0,
// getURL: EMD.gis._getIndexBaseURL,
// projection: new OpenLayers.Projection("EPSG:900913")
// });

// var basemap = new OpenLayers.Layer.WMS(
// "vworld",
// "http://map.vworld.kr/js/wms.do?apiKey=5E7D605D-BD5A-3BEE-8C20-62172A86B4AA&domain=http://localhost",
// {
// layers:"LT_C_ADSIDO",
// format: 'image/png',
// version: '1.3.0',
// transparent: true,
// displayOutsideMaxExtent : true,
// CRS: 'ESPG:900913'
// },
// {
// singleTile: true
// , isBaseLayer: true
// , buffer: 0
// , ratio: 1.0
// , transitionEffect: 'resize'
// }
// );

// var basemap = new OpenLayers.Layer.Google(
// "Google Streets", // the default
// {numZoomLevels: 20}
// );
// var ghyb = new OpenLayers.Layer.Google(
// "Google Hybrid",
// {type: google.maps.MapTypeId.HYBRID, numZoomLevels: 20}
// );
// var gsat = new OpenLayers.Layer.Google(
// "Google Satellite",
// {type: google.maps.MapTypeId.SATELLITE, numZoomLevels: 22}
// );

			var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
		    renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;

/*
 * var shapeParams = OpenLayers.Util.getParameterString({ SERVICE: "WFS",
 * VERSION: "1.1.0", REQUEST: "GetFeature", BBOX:
 * '14210803,4151029,14211147,4151407',//bbox TYPENAME: 'LP_PA_CBND_BUBUN',
 * //wfs 할 레이어명 MAXFEATURES:"100", SRSNAME: "EPSG:900913", OUTPUT:
 * "text/xml;subType=gml/3.1.1/profiles/gmlsf/1.0.0/0", EXCEPTIONS: "text/xml"
 * });
 *
 * OpenLayers.loadURL("/proxy/proxy.jsp", "?url=" +
 * escape("http://map.vworld.kr/js/wfs.do?APIKEY=369C4265-766B-31D6-9469-8FB5ECC1BE17&DOMAIN=localhost:8080"+
 * shapeParams) , this, EMD.gis.getLotAreaFeature); //받아온 gml을 분석하여 표출
 */

// EMD.gyhmap = new OpenLayers.Layer.WMS("광양항",
// 'http://192.168.0.40:8080/G2DataService/GService?', {
// layers : ["GYH"],
// styles : [],
// format : "image/png",
// version : "1.3.0",
// CRS : "SR-ORG:6640"
// }, {
// singleTile : true,
// transitionEffect : "resize",
// ratio : 1
// });
// var vectorLayer = new OpenLayers.Layer.Vector("vectorLayer");

// EMD.gyhmap = new OpenLayers.Layer.Vector("광양항", {
// minScale: 30000,
// strategies: [new OpenLayers.Strategy.BBOX()],
// protocol: new OpenLayers.Protocol.WFS({
// version: "1.1.0",
// url: "http://192.168.0.40:8080/G2DataService/GService?", //
// Service=WFS&Version=1.1.0&Request=GetFeature&Outputformat=text/xml;subtype=gml/3.1.1&Resulttype=results&Maxfeatures=9999&Srsname=SR-ORG:6640&Typename=GYH88
// fearureType: "GYH88",
// Typename: "GYH88",
// // SRSNAME: "EPSG:90013"
// SRSNAME: "SR-ORG:6640"
// }),
// renderers: renderer
// });

// var saveStrategy = new OpenLayers.Strategy.Save();

		 	/*
			 * EMD.wfs = new OpenLayers.Layer.Vector("Editable Features",{
			 * strategies: [new OpenLayers.Strategy.BBOX(), saveStrategy],
			 * projection: new OpenLayers.Projection("EPSG:4326"), protocol: new
			 * OpenLayers.Protocol.WFS({ version: "1.1.0", srsName: "EPSG:4326",
			 * typeName: "ASSETS_CD_A", // propertyName: "G2_SPATIAL", url:
			 * EMD.gis_engine_url // featureNS : "http://opengeo.org", //
			 * featureType: "restricted", // geometryName: "the_geom", //
			 * schema:
			 * "http://demo.opengeo.org/geoserver/wfs/DescribeFeatureType?version=1.1.0&typename=og:restricted" })
			 * });
			 */

		 	if($DEBUG) {
			 	var wfs_options = {
			 			url: "http://map.vworld.kr/js/wfs.do?APIKEY="+EMD.vworldKey+"&domain="+EMD.serverUrl,
			 			params: {
			 				request: "GetFeature",
			 		        service: "wfs",
			 		        version: "1.1.0",
			 		        typeName: "lp_pa_cbnd_bubun",
			 		        srsName: "EPSG:900913",
			 			},
	 		        	format: new OpenLayers.Format.GML({
	 		               geometryName: "AG_GEOM"
	 		        	})
		 			};

			 	EMD.lotareaProtocol = new OpenLayers.Protocol.HTTP(wfs_options);

			 	EMD.lotarea = new OpenLayers.Layer.Vector("지번", {
			 		minScale: 5000,
					strategies: [new OpenLayers.Strategy.BBOX({
						ratio: 1
					})],
			        projection: new OpenLayers.Projection("EPSG:900913"),
					protocol: EMD.lotareaProtocol,
					styleMap: new OpenLayers.StyleMap({
		            	'default':{
			                strokeColor: "#000000",
			                strokeOpacity: 1,
			                strokeWidth: 1,
			                fillColor: "#FBFF9A",
			                fillOpacity: 0.5,
			                pointRadius: 6,
			                pointerEvents: "visiblePainted",
			                // label with \n linebreaks
			                label : "${jibun}",

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
			                label : "${JIBUN}",
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
		            }),
// renderers: renderer
			 	});
			 	// if prototype copy landcode layer
				EMD.lotarea.events.register('featuresadded', null, function(e) {
					if(!EMD.startLogLotarea) return;
					  var lotareaProj = new OpenLayers.Projection("EPSG:900913");
					  var features=[ ];
						for(var i=0; i<e.features.length; i++) {

							  var polygon= e.features[i].geometry.clone();
						      var poly = polygon.transform(
						    		  lotareaProj,EMD.map.getProjection()
						          );
						      var f=new OpenLayers.Feature.Vector(poly, e.features[i].attributes);
						      f.state=OpenLayers.State.INSERT;
						      features[features.length]=f;
// EMD.protocols.landCode.create(f);

						      EMD.lotareaStackCnt++;
					    		var resp=EMD.protocols.landCode.read({
					    			filter: new OpenLayers.Filter.Comparison({
												type: OpenLayers.Filter.Comparison.EQUAL_TO,
												property: "PNU",
												value: f.attributes.PNU
						    		}),
						    		data: {feature:f},
					    			callback: function(resp) {
					    				EMD.lotareaStackCnt--;
					    			    if(resp.error) {
					    			        // console.log('find error');
					    			        return 0;
					    			    }
					    			    if(resp.features.length==undefined || resp.features.length===0) {
					    			        var f = new OpenLayers.Feature();
					    			        f.geometry = resp.data.feature.geometry.clone();
					    			    	f.attributes = resp.data.feature.attributes;
					    			        EMD.protocols.landCode.create(f);
					    			        f.state = OpenLayers.State.INSERT;
				    			            var polygon = resp.data.feature.geometry;
// }});
				    			            f.geometry = polygon.transform(EMD.lotarea.projection, EMD.userLayer.landCode.projection);
				    			            EMD.lotareaFeatures[EMD.lotareaFeatures.length]=f;
// EMD.protocols.landCode.commit([v]);
					    			        var v = new OpenLayers.Feature();
				    			            v.geometry = polygon.transform(EMD.lotarea.projection, EMD.map.getProjection());
					    			    	v.attributes = resp.data.feature.attributes;
					    			          EMD.userLayer.landCode.addFeatures([v]);
					    			          EMD.userLayer.landCode.redraw();
// EMD.protocols.landCode.commit([v], {callback:function(resp) {
// if (resp.error) {
// //console.log("error");
// return-1;
// }
// $.each(resp.reqFeatures, function() {
// var polygon = this.geometry;
// this.geometry = polygon.transform(EMD.userLayer.landCode.projection,
// EMD.map.getProjection());
// });
// EMD.userLayer.landCode.addFeatures(resp.reqFeatures);
// EMD.userLayer.landCode.redraw();
// }});
					    			    }
					    			    else {

					    			    }

					    			    if(EMD.lotareaStackCnt==0) alert('stack cleared. '+EMD.lotareaFeatures.length+' count lotarea saved.');

					    			},
// scope: new OpenLayers.Strategy.BBOX()
					    		});
					    		resp.data={feature:f};
// EMD.userLayer.landCode.drawFeature(f);
						}
// EMD.userLayer.landCode.addFeatures(features);
// EMD.userLayer.landCode.redraw();
		//
// EMD.protocols.landCode.commit();

// EMD.saveStrategy.save();
					});
			 	EMD.lotarea.events.register('beforefeatureselected', null, function(e) {
	    			EMD.selectControl.unselectAll();
	                if(EMD.popup.lotAreaInfo!=null) {
// EMD.selectControl.unselectAll();
	                    EMD.map.removePopup(EMD.popup.lotAreaInfo);
	                    EMD.popup.lotAreaInfo.destroy();
	                    EMD.popup.lotAreaInfo=null;
	                }
			 	});
				EMD.lotarea.events.register('featureselected', null, function(e) {
	                var feature = e.feature;
	                EMD.gis.getBupjungDongNm(e.feature.attributes.PNU.substring(0, 10),{ feature: feature }, function(data) {
	                	if(data.feature==null || data.feature.geometry==null) alert('선택된 지번이 없습니다.');
	                	var locplc =data.resultList[0].name;
	                	var info = "<h2 class='infoClass'>지번 정보</h2><div style='font-size:.9em'><br>주소 : " + locplc+" "+data.feature.attributes.JIBUN;
	                	if(EMD.user.hasAuth('ROLE_ADMIN,ROLE_ASSET_MNGT')) {
	                		info += "<br /><br /><button id='addLotcodeToAsset' data-role='frmwrkButton' data-id='"+data.feature.id+"' >자산으로 등록</button>";
	                	}
	                	info +="</div>";
	                    var popup = new OpenLayers.Popup.FramedCloud("popup",
	                    		data.feature.geometry.bounds.getCenterLonLat(),
	                            new OpenLayers.Size(250,150),
	                            info,
	                            null,
	                            true
	                        );
	                    if(EMD.popup.lotAreaInfo!=null) {
	                        EMD.map.removePopup(EMD.popup.lotAreaInfo);
	                        EMD.popup.lotAreaInfo.destroy();
	                    }
	                    EMD.popup.lotAreaInfo = popup;
	                    EMD.map.addPopup(popup);
	                    $("#addLotcodeToAsset").button(
	  						  { icons: {
								  primary: 'ui-icon-plus'
							  },
							  text : true
							  }).click({feature: feature}, function(event) {
								  // 피처의 좌표계를 실제 5186 으로 변경한다.
								  var gisAssetsProj = new OpenLayers.Projection("EPSG:5186");

								  var polygon= event.data.feature.geometry.clone();
								      // alert(polygon);
// var bounds=polygon.getBounds();
							      var poly = polygon.transform(
							    		  event.data.feature.layer.projection,gisAssetsProj
							          );
							      var attr={
							    		  PRT_CD:'',
							    		  ASSETS_CD:'',
							    		  ASSETS_SCD:'',
							    		  ASSETS_SE_CD:'',
							    		  ASSETS_NM:locplc,
							    		  AR:'',
							    		  BJD_CODE:''
							      };
							      attr=event.data.feature.attributes;
							      var vec=new OpenLayers.Feature.Vector(poly, attr);

							      EMD.layers.gisAssetsCd.addFeatures(vec);
							      EMD.layers.gisAssetsCd.redraw();
							      EMD.protocols.gisAssetsCd.create(vec);
							      EMD.protocols.gisAssetsCd.commit([vec], {
			        			    	callback:function(resp) {
			    		    			    if(resp.error) {
			    		    			        // console.log('error');
			    		    			        return -1;
			    		    			    }
// var gisAssetsCdProjection = new OpenLayers.Projection("EPSG:5186");
// EMD.selectControl.unselectAll();
// // 피처의 좌표계를 지도의 좌표계로 변경한다.
// if(resp.reqFeatures.length!=undefined) {
// $.each(resp.reqFeatures, function() {
// var polygon= this.geometry;
// //alert(polygon);
// // var bounds=polygon.getBounds();
// this.geometry = polygon.transform(
// gisAssetsCdProjection,EMD.map.getProjection()
// );
// });
// }
// else {
// resp.reqFeatures.geometry = polygon.transform(
// gisAssetsCdProjection,EMD.map.getProjection()
// );
// }
// EMD.userLayer.gisAssetsCd.addFeatures(resp.reqFeatures);
// EMD.userLayer.gisAssetsCd.redraw();

// EMD.util.create_window('gamAssetCodeMngt', '자산정보추가',
// '/code/assets/gamAssetCodeMngt.do', null, {action: "addLotcodeFeature",
// feature: resp.reqFeatures});
			        			    	}
			        			    });
// EMD.saveStrategy.save();

// var polygon= newfeature.geometry;
// newfeature.geometry = polygon.transform(
// newfeature.layer.projection,EMD.map.getProjection()
// );
// EMD.gis.storeLayerObject(layer, newfeature, value);
// newfeature.state=OpenLayers.State.INSERT;
// layer.addFeatures([newfeature]);
// layer.redraw();
//
// EMD.protocols.gisAssetsCd.create(newfeature);
// EMD.protocols.gisAssetsCd.commit();
// EMD.saveStrategy.save();


	                    });
	                });
	            });
				EMD.lotarea.events.register('featureunselected', null, function(e) {
// var feature = e.feature;
	                if(EMD.popup.lotAreaInfo==null) return;
	                EMD.map.removePopup(EMD.popup.lotAreaInfo);
	                EMD.popup.lotAreaInfo.destroy();
	                EMD.popup.lotAreaInfo = null;
	            });
		 	}
		 	else {	// if not DEBUG

		 	}



// var geonuris = new OpenLayers.Layer.WMS("Overlay Layer",
// "http://192.168.220.128:8880/geonuris/wms?GDX=sample.xml&FIXED=TRUE&MAP_CRS=EPSG:4326&TEXT_ANTI=TRUE&ANTI=FALSE&LABEL=HIDE_OVERLAP"
// , {
// layers: "ROOT",
// transparent:'true',
// bgcolor:'0xFFFFFF'
// },{isBaseLayer:false, singleTile:true, 'reproject':true});

			// var saveStrategy = new OpenLayers.Strategy.Save();

			/*
			 * EMD.userLayer.landCode = new OpenLayers.Layer.Vector("lotcode", {
			 * strategies: [new OpenLayers.Strategy.Fixed()], protocol: new
			 * OpenLayers.Protocol.HTTP({ url: "gml/lotcode3.xml", format: new
			 * OpenLayers.Format.GML() }), styleMap: new OpenLayers.StyleMap({
			 * 'default':{ strokeColor: "#000000", strokeOpacity: 1,
			 * strokeWidth: 1, fillColor: "#303294", fillOpacity: 0.5,
			 * pointRadius: 6, pointerEvents: "visiblePainted", // label with \n
			 * linebreaks label : "${JIBUN}",
			 *
			 * fontColor: "white", fontSize: "12px", fontFamily: "Courier New,
			 * monospace", fontWeight: "bold", labelAlign: "cm", labelXOffset:
			 * "${xOffset}", labelYOffset: "${yOffset}", labelOutlineColor:
			 * "black", labelOutlineWidth: 1 }, 'select': { strokeColor:
			 * "#00FF00", strokeOpacity: 1, strokeWidth: 1, fillColor:
			 * "#303294", fillOpacity: 1, pointRadius: 6, pointerEvents:
			 * "visiblePainted", // label with \n linebreaks label : "${JIBUN}",
			 * fontColor: "black", fontSize: "12px", fontFamily: "Courier New,
			 * monospace", fontWeight: "bold", labelAlign: "cm", labelXOffset:
			 * "${xOffset}", labelYOffset: "${yOffset}", labelOutlineColor:
			 * "white", labelOutlineWidth: 3 } }), renderers:
			 * OpenLayers.Layer.Vector.prototype.renderers });
			 */
/*
 * EMD.userLayer.landCode = new OpenLayers.Layer.Vector("Land Code Layer", {
 * fillColor: "#F8FD7D", fillOpacity: 0.8, strokeColor: "#303030",
 * strokeOpacity: 0.8, strokeWidth: 1, pointRadius: 6, pointerEvents:
 * "visiblePainted", styleMap: new OpenLayers.StyleMap({ 'default':{
 * strokeColor: "#000000", strokeOpacity: 1, strokeWidth: 1, fillColor:
 * "#303294", fillOpacity: 0.5, pointRadius: 6, pointerEvents: "visiblePainted", //
 * label with \n linebreaks label : "${landNm}\n${lndCode}\n (${type})",
 *
 * fontColor: "${favColor}", fontSize: "12px", fontFamily: "Courier New,
 * monospace", fontWeight: "bold", labelAlign: "cm", labelXOffset: "${xOffset}",
 * labelYOffset: "${yOffset}", labelOutlineColor: "white", labelOutlineWidth: 3 },
 * 'select': { strokeColor: "#00FF00", strokeOpacity: 1, strokeWidth: 1,
 * fillColor: "#303294", fillOpacity: 1, pointRadius: 6, pointerEvents:
 * "visiblePainted", // label with \n linebreaks label :
 * "${landNm}\n${lndCode}\n (${type})", fontColor: "${favColor}", fontSize:
 * "12px", fontFamily: "Courier New, monospace", fontWeight: "bold", labelAlign:
 * "cm", labelXOffset: "${xOffset}", labelYOffset: "${yOffset}",
 * labelOutlineColor: "white", labelOutlineWidth: 3 } }), renderers:
 * OpenLayers.Layer.Vector.prototype.renderers
 *
 * });
 */

// EMD.wfs = new OpenLayers.Layer.Vector("User Drawing Layer"
// , {
// strategies: [new OpenLayers.Strategy.BBOX(), saveStrategy],
// projection: new OpenLayers.Projection("EPSG:4326")
// protocol: new OpenLayers.Protocol.WFS({
// version: "1.1.0",
// srsName: "EPSG:4326",
// url: "http://192.168.220.128:8880/geonuris/wfs",
// featureNS : "http://opengeo.org",
// featureType: "restricted",
// geometryName: "the_geom",
// schema:
// "http://demo.opengeo.org/geoserver/wfs/DescribeFeatureType?version=1.1.0&typename=og:restricted"
// })
// });

			EMD.userLayer.custLand = new OpenLayers.Layer.Vector("지도", {
		 		minScale: 20000,
		 		displayInLayerSwitcher: false,
				strategies: [new OpenLayers.Strategy.BBOX({
					ratio: 1
				})],
		        projection: new OpenLayers.Projection("EPSG:5186"),
				protocol: new OpenLayers.Protocol.WFS({
					version: "1.1.0",
					service: "WFS",
					url: EMD.gis_engine_url,
					featureNS: "http://geogate.g-inno.com/dataserver/datahouse",
					featurePrefix: "datahouse",
					featureType: "YGPA_LAND",
					srsName: "EPSG:5186",
					geometryName: "G2_SPATIAL"
				}),
				styleMap : new OpenLayers.StyleMap({'default':{
// strokeColor: "#000000",
// strokeOpacity: 1,
                    strokeWidth: 0,
	                fillColor: "#F5F1EF",
	                fillOpacity: 1
                }})
		 	});

			EMD.userLayer['wharfNm'] = new OpenLayers.Layer.Vector("부두명", {
		 		minScale: 90000,
		 		displayInLayerSwitcher: false,
				strategies: [new OpenLayers.Strategy.BBOX({
					ratio: 1
				})],
		        projection: new OpenLayers.Projection("EPSG:5186"),
				protocol: new OpenLayers.Protocol.WFS({
					version: "1.1.0",
					service: "WFS",
					url: EMD.gis_engine_url,
					featureNS: "http://geogate.g-inno.com/dataserver/datahouse",
					featurePrefix: "datahouse",
					featureType: "WHARF_NM",
					srsName: "EPSG:5186",
					geometryName: "G2_SPATIAL"
				}),
				styleMap : new OpenLayers.StyleMap({'default':{
                    strokeColor: "#00FF00",
                    strokeOpacity: 1,
                    strokeWidth: 3,
                    fillColor: "#FF5500",
                    fillOpacity: 0.5,
                    label : "${TEXT}",
                    fontColor: "blue",
                    fontSize: "12px",
                    fontFamily: "Nanum Gothic",
                    fontWeight: "bold",
                    labelAlign: "cm",
                    labelOutlineColor: "white",
                    labelOutlineWidth: 3
                }})
		 	});

			EMD.userLayer['fcltyNm'] = new OpenLayers.Layer.Vector("시설명", {
		 		minScale: 10000,
		 		displayInLayerSwitcher: false,
				strategies: [new OpenLayers.Strategy.BBOX({
					ratio: 1
				})],
		        projection: new OpenLayers.Projection("EPSG:5186"),
				protocol: new OpenLayers.Protocol.WFS({
					version: "1.1.0",
					service: "WFS",
					url: EMD.gis_engine_url,
					featureNS: "http://geogate.g-inno.com/dataserver/datahouse",
					featurePrefix: "datahouse",
					featureType: "FCLTY_NM",
					srsName: "EPSG:5186",
					geometryName: "G2_SPATIAL"
				}),
				styleMap : new OpenLayers.StyleMap({'default':{
                    strokeColor: "#00FF00",
                    strokeOpacity: 1,
                    strokeWidth: 3,
                    fillColor: "#FF5500",
                    fillOpacity: 0.5,
                    label : "${Text}",
                    fontColor: "white",
                    fontSize: "12px",
                    fontFamily: "Nanum Gothic",
                    fontWeight: "bold",
                    labelAlign: "cm",
                    labelOutlineColor: "black",
                    labelOutlineWidth: 3
                }})
		 	});

			EMD.protocols.landCode = new OpenLayers.Protocol.WFS({
				version: "1.1.0",
				service: "WFS",
				url: EMD.gis_engine_url,
				featureNS: "http://geogate.g-inno.com/dataserver/datahouse",
				featurePrefix: "datahouse",
				featureType: "LP_PA_CBND_BUBUN_A",
				srsName: "EPSG:5186",
				geometryName: "G2_SPATIAL"
			});

			EMD.protocols.gisAssetsCd = new OpenLayers.Protocol.WFS({
				version: "1.1.0",
				service: "WFS",
				url: EMD.gis_engine_url,
				featureNS: "http://geogate.g-inno.com/dataserver/datahouse",
				featurePrefix: "datahouse",
				featureType: "GIS_ASSETS_CD_E",
				srsName: "EPSG:5186",
				geometryName: "G2_SPATIAL"
			});

			EMD.protocols.locArea = new OpenLayers.Protocol.WFS({
				version: "1.1.0",
				service: "WFS",
				url: EMD.gis_engine_url,
				featureNS: "http://geogate.g-inno.com/dataserver/datahouse",
				featurePrefix: "datahouse",
				featureType: "LOC_AREA_E",
				srsName: "EPSG:5186",
				geometryName: "G2_SPATIAL"
			});

			// 자산임대 프로토콜
			EMD.protocols.assetsRent = new OpenLayers.Protocol.WFS({
				version: "1.1.0",
				service: "WFS",
				url: EMD.gis_engine_url,
				featureNS: "http://geogate.g-inno.com/dataserver/datahouse",
				featurePrefix: "datahouse",
				featureType: "ASSETS_RENT_E",
				srsName: "EPSG:5186",
				geometryName: "G2_SPATIAL"
			});

			EMD.protocols.assetsRentDetail = new OpenLayers.Protocol.WFS({
				version: "1.1.0",
				service: "WFS",
				url: EMD.gis_engine_url,
				featureNS: "http://geogate.g-inno.com/dataserver/datahouse",
				featurePrefix: "datahouse",
				featureType: "ASSETS_RENT_DETAIL_F",
				srsName: "EPSG:5186",
				geometryName: "G2_SPATIAL"
			});

			EMD.protocols.gisPrtFcltyCd = new OpenLayers.Protocol.WFS.v1_1_0({
				version: "1.1.0",
				service: "WFS",
				url: EMD.gis_engine_url,
				featureNS: "http://geogate.g-inno.com/dataserver/datahouse",
				featurePrefix: "datahouse",
				featureType: "FCLTY_E",
				srsName: "EPSG:5186",
				geometryName: "G2_SPATIAL"
			});

			EMD.saveStrategy = new OpenLayers.Strategy.Save();

			EMD.refreshStrategy = new OpenLayers.Strategy.Refresh();

			// EMD.userStyle.assetCdStyle['default'] = getDefaultAssetStyle;
			/*{
					'default':
						getDefaultAssetStyle,
					{
						strokeColor: "#000000",
						strokeOpacity: 1,
						strokeWidth: 1,
						fillColor: "#303294",
						fillOpacity: 0.5,
						pointRadius: 6,
						pointerEvents: "visiblePainted",
						// label with \n linebreaks
						label : "${ASSETS_NM}",
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
						fillColor: "#303294",
						fillOpacity: 1,
						pointRadius: 6,
						pointerEvents: "visiblePainted",
						// label with \n linebreaks
						label : "${ASSETS_NM}",
						fontColor: "white",
						fontSize: "12px",
						fontFamily: "Courier New, monospace",
						fontWeight: "bold",
						labelAlign: "cm",
						labelXOffset: "2px",
						labelYOffset: "2px",
						labelOutlineColor: "black",
						labelOutlineWidth: 3
					},
					'modify': {
						strokeOpacity: 1,
						strokeWidth: 1,
						fillOpacity: 0.5,
						pointRadius: 6,
						label : "",
						pointerEvents: "visiblePainted",
						fontColor: "black",
						fontSize: "12px",
						fontFamily: "Courier New, monospace",
						fontWeight: "bold",
					    strokeColor: "#ff0000",
					    fillColor: "#ff0000",
					    graphicName: "circle"
					}
		 	};
		 	*/

			// 자산 가치 평가내역 조회 스타일
			EMD.userStyle.assetCdStyle['evlDtls'] = {
					'default':{
						strokeColor: "#000000",
						strokeOpacity: 1,
						strokeWidth: 1,
						fillColor: "#73D7CF",
						fillOpacity: 0.8,
						pointRadius: 6,
						pointerEvents: "visiblePainted",
						// label with \n linebreaks
						label : "자산명:${ASSETS_NM}\n자본적지출금액:${assetBuyAmt}\n잔존금액:${curAmt}",
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
						strokeColor: "#76750A",
						strokeOpacity: 1,
						strokeWidth: 3,
						fillColor: "#8D1594",
						fillOpacity: 1,
						pointRadius: 6,
						pointerEvents: "visiblePainted",
						// label with \n linebreaks
						label : "자산명:${ASSETS_NM}\n자본적지출금액:${assetBuyAmt}\n잔존금액:${curAmt}",
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
		 	};

		 	EMD.userLayer.landCode = new OpenLayers.Layer.Vector("지번", {
		 		minScale: 9000,
				strategies: [new OpenLayers.Strategy.BBOX({
					ratio: 1
				})],
		        projection: new OpenLayers.Projection("EPSG:5186"),
				protocol: EMD.protocols.landCode,
				styleMap: new OpenLayers.StyleMap({
	            	'default':{
		                strokeColor: "#000000",
		                strokeOpacity: 1,
		                strokeWidth: 1,
		                fillColor: "#FBFF9A",
		                fillOpacity: 0.5,
		                pointRadius: 6,
		                pointerEvents: "visiblePainted",
		                // label with \n linebreaks
		                label : "[${JIBUN}]",

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
		                label : "!${JIBUN}",
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
	            })
		 	});
		 	EMD.protocols.landCode.layer=EMD.userLayer.landCode;	// 레이어 저장용
																	// 프로토콜을 지정
																	// 한다.

		 	EMD.userLayer.landCode.events.register('beforefeatureselected', null, function(e) {
    			EMD.selectControl.unselectAll();
                if(EMD.popup.lotAreaInfo!=null) {
// EMD.selectControl.unselectAll();
                    EMD.map.removePopup(EMD.popup.lotAreaInfo);
                    EMD.popup.lotAreaInfo.destroy();
                    EMD.popup.lotAreaInfo=null;
                }
		 	});
			EMD.userLayer.landCode.events.register('featureselected', null, function(e) {
                var feature = e.feature;
                EMD.gis.getBupjungDongNm(e.feature.attributes.PNU.substring(0, 10),{ feature: feature }, function(data) {
                	if(data.feature==null || data.feature.geometry==null) alert('선택된 지번이 없습니다.');
                	var locplc =data.resultList[0].name;
                	var info = "<h2 class='infoClass'>지번 정보</h2><div style='font-size:.9em'><br>주소 : " + locplc+" "+data.feature.attributes.JIBUN;
                	if(EMD.user.hasAuth('ROLE_ADMIN,ROLE_ASSET_MNGT')) {
                		info += "<br /><br /><button id='addLotcodeToAsset' data-role='frmwrkButton' data-id='"+data.feature.id+"' >자산으로 등록</button>";
                	}
                	info +="</div>";
                    var popup = new OpenLayers.Popup.FramedCloud("popup",
                    		data.feature.geometry.bounds.getCenterLonLat(),
                            new OpenLayers.Size(250,150),
                            info,
                            null,
                            true
                        );
                    if(EMD.popup.lotAreaInfo!=null) {
                        EMD.map.removePopup(EMD.popup.lotAreaInfo);
                        EMD.popup.lotAreaInfo.destroy();
                    }
                    EMD.popup.lotAreaInfo = popup;
                    EMD.map.addPopup(popup);
                    $("#addLotcodeToAsset").button(
  						  { icons: {
							  primary: 'ui-icon-plus'
						  },
						  text : true
						  }).click({feature: feature}, function(event) {
							  // 피처의 좌표계를 실제 5186 으로 변경한다.
							  var lotareaProj = new OpenLayers.Projection("EPSG:900913");
							  var gisAssetsProj = new OpenLayers.Projection("EPSG:5186");

							  var polygon= event.data.feature.geometry.clone();
						      var poly = polygon.transform(
						    		  lotareaProj,gisAssetsProj
						          );

						      var attr={
						    		  PRT_CD:'',
						    		  ASSETS_CD:'',
						    		  ASSETS_SCD:'',
						    		  ASSETS_SE_CD:'',
						    		  ASSETS_NM:'미정의',
						    		  AR:'',
						    		  BJD_CODE:event.data.feature.attributes.PNU
						      };
						      var vec=new OpenLayers.Feature.Vector(poly, attr);

// EMD.userLayer.gisAssetsCd.addFeatures(vec);
// EMD.userLayer.gisAssetsCd.redraw();
						      EMD.protocols.gisAssetsCd.create(vec);
						      vec.state = OpenLayers.State.INSERT;
// EMD.protocols.gisAssetsCd.commit();

	        			    EMD.protocols.gisAssetsCd.commit([vec], {
	        			    	callback:function(resp) {
	    		    			    if(resp.error) {
	    		    			        // console.log('error');
	    		    			        return -1;
	    		    			    }
	    		    			    var gisAssetsCdProjection = new OpenLayers.Projection("EPSG:5186");
	    		    			    EMD.selectControl.unselectAll();
	    							  // 피처의 좌표계를 지도의 좌표계로 변경한다.
	    		    			    if(resp.reqFeatures.length!=undefined) {
		    		    			    $.each(resp.reqFeatures, function() {
		    								  var polygon= this.geometry;
		    								  this.state="";
		    						      this.geometry = polygon.transform(
		    								    	 gisAssetsCdProjection,EMD.map.getProjection()
		    						          );
		    		    			    });
	    		    			    }
	    		    			    else {
	    		    			    	resp.reqFeatures.geometry = polygon.transform(
		    								    	 gisAssetsCdProjection,EMD.map.getProjection()
		    						          );
	    		    			    	resp.reqFeatures.state="";
	    		    			    }
	    					    	EMD.userLayer.gisAssetsCd.addFeatures(resp.reqFeatures);
	    					    	EMD.userLayer.gisAssetsCd.redraw();
	        			    	}
	        			    });
// EMD.saveStrategy.save();


// EMD.util.create_window('gamAssetCodeMngt', '자산정보추가',
// '/code/assets/gamAssetCodeMngt.do', null, {action: "addLotcodeFeature",
// feature: vec});
                    });
                });
            });
			EMD.userLayer.landCode.events.register('featureunselected', null, function(e) {
// var feature = e.feature;
                if(EMD.popup.lotAreaInfo==null) return;
                EMD.map.removePopup(EMD.popup.lotAreaInfo);
                EMD.popup.lotAreaInfo.destroy();
                EMD.popup.lotAreaInfo = null;
            });

		 	EMD.userLayer.locArea = new OpenLayers.Layer.Vector("위치", {
		 		// minScale: 81000,
		 		minScale: 3000,
				strategies: [new OpenLayers.Strategy.Fixed()],
		        projection: new OpenLayers.Projection("EPSG:5186"),
				protocol: EMD.protocols.locArea,
		 		displayInLayerSwitcher: false,
				styleMap: new OpenLayers.StyleMap({
	            	'default':{
		                strokeColor: "#FFFFFF",
		                strokeOpacity: 1,
		                strokeWidth: 1,
		                fillColor: "#45CEC9",
		                fillOpacity: 0.5,
		                pointRadius: 6,
		                pointerEvents: "visiblePainted",
		                // label with \n linebreaks
		                label : "[${LOC_NM}]",

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
		                strokeColor: "#000000",
		                strokeOpacity: 1,
		                strokeWidth: 3,
		                fillColor: "#E92FFF",
		                fillOpacity: 1,
		                pointRadius: 6,
		                pointerEvents: "visiblePainted",
		                // label with \n linebreaks
		                label : "!${LOC_NM}",
		                fontColor: "white",
		                fontSize: "12px",
		                fontFamily: "Courier New, monospace",
		                fontWeight: "bold",
		                labelAlign: "cm",
		                labelXOffset: "2px",
		                labelYOffset: "2px",
		                labelOutlineColor: "black",
		                labelOutlineWidth: 3
	            	}}, {extendDefault: false})
		 	});
		 	EMD.protocols.locArea.layer=EMD.userLayer.locArea;	// 레이어 저장용 프로토콜을
																// 지정 한다.


			EMD.userLayer.locArea.events.register('featureselected', null, function(e) {
                var feature = e.feature;
                /*
				 * EMD.gis.getBupjungDongNm(e.feature.attributes.PNU.substring(0,
				 * 10),{ feature: feature }, function(data) {
				 * if(data.feature==null || data.feature.geometry==null)
				 * alert('선택된 지번이 없습니다.'); var locplc =data.resultList[0].name;
				 * var info = "<h2 class='infoClass'>지번 정보</h2><div
				 * style='font-size:.9em'><br>주소 : " + locplc+"
				 * "+data.feature.attributes.JIBUN;
				 * if(EMD.user.hasAuth('ROLE_ADMIN,ROLE_ASSET_MNGT')) { info += "<br /><br /><button
				 * id='addLotcodeToAsset' data-role='frmwrkButton'
				 * data-id='"+data.feature.id+"' >자산으로 등록</button>"; } info +="</div>";
				 * var popup = new OpenLayers.Popup.FramedCloud("popup",
				 * data.feature.geometry.bounds.getCenterLonLat(), new
				 * OpenLayers.Size(250,150), info, null, true );
				 * if(EMD.popup.lotAreaInfo!=null) {
				 * EMD.map.removePopup(EMD.popup.lotAreaInfo);
				 * EMD.popup.lotAreaInfo.destroy(); } EMD.popup.lotAreaInfo =
				 * popup; EMD.map.addPopup(popup);
				 * $("#addLotcodeToAsset").button( { icons: { primary:
				 * 'ui-icon-plus' }, text : true }).click({feature: feature},
				 * function(event) { // 피처의 좌표계를 실제 5186 으로 변경한다. var
				 * lotareaProj = new OpenLayers.Projection("EPSG:900913"); var
				 * gisAssetsProj = new OpenLayers.Projection("EPSG:5186");
				 *
				 * var polygon= event.data.feature.geometry.clone(); var poly =
				 * polygon.transform( lotareaProj,gisAssetsProj );
				 *
				 * var attr={ PRT_CD:'', ASSETS_CD:'', ASSETS_SCD:'',
				 * ASSETS_NM:'미정의', AR:'',
				 * BJD_CODE:event.data.feature.attributes.PNU }; var vec=new
				 * OpenLayers.Feature.Vector(poly, attr);
				 *
				 * EMD.protocols.gisAssetsCd.create(vec); vec.state =
				 * OpenLayers.State.INSERT;
				 *
				 * EMD.protocols.gisAssetsCd.commit([vec], {
				 * callback:function(resp) { if(resp.error) {
				 * //console.log('error'); return -1; } var
				 * gisAssetsCdProjection = new
				 * OpenLayers.Projection("EPSG:5186");
				 * EMD.selectControl.unselectAll(); // 피처의 좌표계를 지도의 좌표계로 변경한다.
				 * if(resp.reqFeatures.length!=undefined) {
				 * $.each(resp.reqFeatures, function() { var polygon=
				 * this.geometry; this.state=""; this.geometry =
				 * polygon.transform(
				 * gisAssetsCdProjection,EMD.map.getProjection() ); }); } else {
				 * resp.reqFeatures.geometry = polygon.transform(
				 * gisAssetsCdProjection,EMD.map.getProjection() );
				 * resp.reqFeatures.state=""; }
				 * EMD.userLayer.gisAssetsCd.addFeatures(resp.reqFeatures);
				 * EMD.userLayer.gisAssetsCd.redraw(); } }); }); });
				 */
            });

			EMD.userLayer.locArea.events.register('featureunselected', null, function(e) {
				/*
				 * if(EMD.popup.lotAreaInfo==null) return;
				 * EMD.map.removePopup(EMD.popup.lotAreaInfo);
				 * EMD.popup.lotAreaInfo.destroy(); EMD.popup.lotAreaInfo =
				 * null;
				 */
            });

			var gisAssetsCdSaveStrategy = new OpenLayers.Strategy.Save();
			gisAssetsCdSaveStrategy.events.register('start', null, function(e) {
				EMD.util.showProgress();
			});
			gisAssetsCdSaveStrategy.events.register('success', null, function(e) {
				EMD.util.hideProgress();
				this.layer.refreshStrategy.refresh();
			});
			gisAssetsCdSaveStrategy.events.register('fail', null, function(e) {
				EMD.util.hideProgress();
				alert('자산코드 저장에 실패 하였습니다.');
			});
			var gisAssetsCdRefreshStrategy = new OpenLayers.Strategy.Refresh();

			EMD.userLayer.gisAssetsCd = new OpenLayers.Layer.Vector("자산정보", {
		 		minScale: 30000,
				strategies: [new OpenLayers.Strategy.Fixed(), EMD.saveStrategy, gisAssetsCdRefreshStrategy],
		        projection: new OpenLayers.Projection("EPSG:5186"),
				protocol: EMD.protocols.gisAssetsCd,
				styleMap: getAssetsCdStyleMap(),
				saveStrategy: gisAssetsCdSaveStrategy,
				refreshStrategy: gisAssetsCdRefreshStrategy
// renderers: renderer
		 	});

			EMD.saveLayer.gisAssetsCd=gisAssetsCdSaveStrategy;
		 	EMD.protocols.gisAssetsCd.layer=EMD.userLayer.gisAssetsCd;

//		 	EMD.userLayer.gisAssetsCd.add

			EMD.userLayer.gisAssetsCd.events.register('featuresadded', null, function(e) {
				// console.log(e.features);
				for(var i=0; i<e.features.length; i++) {
					var f = e.features[i];
					f.attributes['AS_TP']=f.attributes['ASSETS_CD'].substring(0, 1);
					f.attributes['WF_TP']=f.attributes['ASSETS_CD'].substring(1, 2);
					f.attributes['LO_TP']=f.attributes['ASSETS_CD'].substring(2, 3);
				}
				// console.log(e.features);
			});

			EMD.userLayer.gisAssetsCd.events.register('featureselected', null, function(e) {
				EMD.gis.openPopup(e.feature, "/maps/assets/gamAssetCdInfo.do", [
				       				       { name: 'gisAssetsPrtAtCode', value:e.feature.attributes.PRT_CD },
				    				       { name: 'gisAssetsCd', value:e.feature.attributes.ASSETS_CD },
				    				       { name: 'gisAssetsSubCd', value:e.feature.attributes.ASSETS_SCD },
				    				       { name: 'bjdCode', value:e.feature.attributes.BJD_CODE },
				    				       { name: 'ar', value:e.feature.attributes.AR }
				    				       ]);
            });
			EMD.userLayer.gisAssetsCd.events.register('featureunselected', null, function(e) {
                var feature = e.feature;
                if(feature.popup!=null) {
                    EMD.map.removePopup(feature.popup);
                    feature.popup.destroy();
                    feature.popup = null;
                }
            });

			var prtFcltyCdSymbol = {
				"Point" : {
					pointRadius : 16,
// externalGraphic:
// EMD.context_root+"/images/egovframework/ygpa/gam/maps/map_icon/icon_${FCLTY_CD}.png",
// graphicWidth: 32,
// graphicHeight: 37,
					fillColor : "white",
					fillOpacity : 1,
					strokeWidth : 1,
					strokeOpacity : 1,
					strokeColor : "#333333"
				},
				"Line" : {
					strokeWidth : 3,
					strokeOpacity : 1,
					strokeColor : "#800080"
				},
				"Polygon" : {
	                fillColor: "#45CEC9",
	                fillOpacity: 0.5,
					strokeWidth : 3,
					strokeOpacity : 1,
					strokeColor : "#800080"
				}
			};

			var fcltyCdRule = new OpenLayers.Rule({
				  filter: new OpenLayers.Filter.Comparison({
				      type: OpenLayers.Filter.Comparison.EQUAL_TO,
				      property: "FCLTY_SE",
				      value: "E",
				  }),
				  symbolizer: {
						"Point" : {
							pointRadius : 10,
							externalGraphic: EMD.context_root+"/images/egovframework/ygpa/gam/maps/map_icon/icon_${FCLTY_CD}.png",
							graphicWidth: 32,
							graphicHeight: 37,
							fillColor : "red",
							fillOpacity : 1,
							strokeWidth : 1,
							strokeOpacity : 1,
							strokeColor : "#333333"
						},
						"Line" : {
							strokeWidth : 3,
							strokeOpacity : 1,
							strokeColor : "#800080"
						},
						"Polygon" : {
			                fillColor: "#45CEC9",
			                fillOpacity: 0.5,
							strokeWidth : 1,
							strokeOpacity : 1,
							strokeColor : "#800080"
						}
					}
				});

			var gisPrtFcltyCdStyle = new OpenLayers.Style();
			gisPrtFcltyCdStyle.addRules( [ fcltyCdRule ]);

			// 시설별 레이어 정보 등록
			var archFilter = new OpenLayers.Strategy.Filter({
				filter: new OpenLayers.Filter.Comparison({
				      type: OpenLayers.Filter.Comparison.EQUAL_TO,
				      property: "FCLTY_SE",
				      value: "A"
				})
			});
			archFilter.deactivate();

			var gisArchFcltyStyle = new OpenLayers.Style();
			var rules=[];
			$.each(fcltyConstRuleSet, function() {
				$.each(this.symbolizer, function(key, val) {
					if(val.externalGraphic!=undefined || val.externalGraphic!=null) {
						val.externalGraphic=EMD.context_root+val.externalGraphic;
					}
				});
				rules[rules.length] = new OpenLayers.Rule({
					title: this.title,
					filter: this.filter,
					symbolizer: this.symbolizer
				});
			});
			gisArchFcltyStyle.addRules( rules );
			var gisArchFcltySaveStrategy = new OpenLayers.Strategy.Save();
			gisArchFcltySaveStrategy.events.register('start', null, function(e) {
				EMD.util.showProgress();
			});
			gisArchFcltySaveStrategy.events.register('success', null, function(e) {
				EMD.util.hideProgress();
				this.layer.refreshStrategy.refresh();
			});
			gisArchFcltySaveStrategy.events.register('fail', null, function(e) {
				EMD.util.hideProgress();
				alert('자산코드 저장에 실패 하였습니다.');
			});
			var gisArchFcltyRefreshStrategy = new OpenLayers.Strategy.Refresh();

		 	EMD.userLayer.gisArchFclty = new OpenLayers.Layer.Vector("건축 시설 정보", {
		 		minScale: 9000,
// strategies: [new OpenLayers.Strategy.BBOX({ratio:1}), EMD.saveStrategy,
// EMD.refreshStrategy ],
				strategies: [new OpenLayers.Strategy.Fixed(), gisArchFcltySaveStrategy, gisArchFcltyRefreshStrategy],
		        projection: new OpenLayers.Projection("EPSG:5186"),
				protocol: EMD.protocols.gisPrtFcltyCd,
				styleMap: new OpenLayers.StyleMap({
					"default" : gisArchFcltyStyle,
					'select': {
						strokeOpacity: 1,
						strokeWidth: 3,
						fillColor: "#303294",
						fillOpacity: 1,
						pointRadius: 6,
						pointerEvents: "visiblePainted",
						fontColor: "blue",
						fontSize: "12px",
						fontWeight: "bold",
						labelAlign: "cm",
						labelOutlineColor: "white",
						labelOutlineWidth: 3
					},
					'modify': {
						strokeOpacity: 1,
						strokeWidth: 1,
						fillOpacity: 0.5,
						pointRadius: 6,
						label : "",
						pointerEvents: "visiblePainted",
						fontColor: "black",
						fontSize: "12px",
						fontFamily: "Courier New, monospace",
						fontWeight: "bold",
					    strokeColor: "#ff0000",
					    fillColor: "#ff0000",
					    graphicName: "circle"
					}
					}, {extendDefault: false}),
					filter:new OpenLayers.Filter.Comparison({
					      type: OpenLayers.Filter.Comparison.EQUAL_TO,
					      property: "FCLTY_SE",
					      value: "A"
					}),
					saveStrategy: gisArchFcltySaveStrategy,
					refreshStrategy: gisArchFcltyRefreshStrategy
		 	});

			EMD.userLayer.gisArchFclty.events.register('featuresadded', null, function(e) {
			});

			EMD.userLayer.gisArchFclty.events.register('beforefeatureselected', null, function(e) {
    			EMD.selectControl.unselectAll();
                if(EMD.popup.lotAreaInfo!=null) {
                    EMD.map.removePopup(EMD.popup.lotAreaInfo);
                    EMD.popup.lotAreaInfo.destroy();
                    EMD.popup.lotAreaInfo=null;
                }
			});

			EMD.userLayer.gisArchFclty.events.register('featureselected', null, function(e) {
				EMD.gis.openPopup(e.feature, "/maps/fclty/gamArchFcltyInfo.do", [
   				       { name: 'gisAssetsPrtAtCode', value:e.feature.attributes.PRT_CD },
				       { name: 'gisAssetsCd', value:e.feature.attributes.ASSETS_CD },
				       { name: 'gisAssetsSubCd', value:e.feature.attributes.ASSETS_SCD },
				       { name: 'gisPrtFcltyCd', value:e.feature.attributes.FCLTY_CD },
				       { name: 'gisPrtFcltySeq', value:e.feature.attributes.FCLTY_SEQ },
				       { name: 'gisPrtFcltySe', value:e.feature.attributes.FCLTY_SE }
				       ]);
// console.log(e);
            });

			EMD.userLayer.gisArchFclty.events.register('featureunselected', null, function(e) {
                var feature = e.feature;
                if(feature.popup!=null) {
                    EMD.map.removePopup(feature.popup);
                    feature.popup.destroy();
                    feature.popup = null;
                }
            });

			var mechFilter = new OpenLayers.Strategy.Filter({
				filter: new OpenLayers.Filter.Comparison({
				      type: OpenLayers.Filter.Comparison.EQUAL_TO,
				      property: "FCLTY_SE",
				      value: "M"
				})
			});
			mechFilter.deactivate();

			gisMechFcltyStyle = new OpenLayers.Style();
			rules=[];
			$.each(fcltyMechRuleSet, function() {
				$.each(this.symbolizer, function(key, val) {
					if(val.externalGraphic!=undefined || val.externalGraphic!=null) {
						val.externalGraphic=EMD.context_root+val.externalGraphic;
					}
				});
				rules[rules.length] = new OpenLayers.Rule({
					title: this.title,
					filter: this.filter,
					symbolizer: this.symbolizer
				});
			});
			gisMechFcltyStyle.addRules( rules );
			var gisMechFcltySaveStrategy = new OpenLayers.Strategy.Save();
			gisMechFcltySaveStrategy.events.register('start', null, function(e) {
				EMD.util.showProgress();
			});
			gisMechFcltySaveStrategy.events.register('success', null, function(e) {
				EMD.util.hideProgress();
				this.layer.refreshStrategy.refresh();
			});
			gisMechFcltySaveStrategy.events.register('fail', null, function(e) {
				EMD.util.hideProgress();
				alert('자산코드 저장에 실패 하였습니다.');
			});
			var gisMechFcltyRefreshStrategy = new OpenLayers.Strategy.Refresh();

			EMD.userLayer.gisMechFclty = new OpenLayers.Layer.Vector("기계 시설 정보", {
		 		minScale: 9000,
				strategies: [new OpenLayers.Strategy.Fixed(), gisMechFcltySaveStrategy, gisMechFcltyRefreshStrategy],
		        projection: new OpenLayers.Projection("EPSG:5186"),
				protocol: EMD.protocols.gisPrtFcltyCd,
				styleMap: new OpenLayers.StyleMap( {
					"default" : gisMechFcltyStyle,
					'select': {
						strokeOpacity: 1,
						strokeWidth: 3,
						fillColor: "#303294",
						fillOpacity: 1,
						pointRadius: 6,
						pointerEvents: "visiblePainted",
						fontColor: "blue",
						fontSize: "12px",
						fontWeight: "bold",
						labelAlign: "cm",
						labelOutlineColor: "white",
						labelOutlineWidth: 3
					},
					'modify': {
						strokeOpacity: 1,
						strokeWidth: 1,
						fillOpacity: 0.5,
						pointRadius: 6,
						label : "",
						pointerEvents: "visiblePainted",
						fontColor: "black",
						fontSize: "12px",
						fontFamily: "Courier New, monospace",
						fontWeight: "bold",
					    strokeColor: "#ff0000",
					    fillColor: "#ff0000",
					    graphicName: "circle"
					}
					}, {extendDefault: false}),
					filter:new OpenLayers.Filter.Comparison({
					      type: OpenLayers.Filter.Comparison.EQUAL_TO,
					      property: "FCLTY_SE",
					      value: "M"
					}),
					saveStrategy: gisMechFcltySaveStrategy,
					refreshStrategy: gisMechFcltyRefreshStrategy
// renderers: renderer
		 	});

			EMD.userLayer.gisMechFclty.events.register('featuresadded', null, function(e) {
// e.feature.attribute['FCLTY_SE']='M';
			});

			EMD.userLayer.gisMechFclty.events.register('beforefeatureselected', null, function(e) {
    			EMD.selectControl.unselectAll();
                if(EMD.popup.lotAreaInfo!=null) {
// EMD.selectControl.unselectAll();
                    EMD.map.removePopup(EMD.popup.lotAreaInfo);
                    EMD.popup.lotAreaInfo.destroy();
                    EMD.popup.lotAreaInfo=null;
                }
			});

			EMD.userLayer.gisMechFclty.events.register('featureselected', null, function(e) {
				EMD.gis.openPopup(e.feature, "/maps/fclty/gamMechFcltyInfo.do", [
   				       { name: 'gisAssetsPrtAtCode', value:e.feature.attributes.PRT_CD },
				       { name: 'gisAssetsCd', value:e.feature.attributes.ASSETS_CD },
				       { name: 'gisAssetsSubCd', value:e.feature.attributes.ASSETS_SCD },
				       { name: 'gisPrtFcltyCd', value:e.feature.attributes.FCLTY_CD },
				       { name: 'gisPrtFcltySeq', value:e.feature.attributes.FCLTY_SEQ },
				       { name: 'gisPrtFcltySe', value:e.feature.attributes.FCLTY_SE }
				       ]);

// console.log(e);
            });

			EMD.userLayer.gisMechFclty.events.register('featureunselected', null, function(e) {
                var feature = e.feature;
                if(feature.popup!=null) {
                    EMD.map.removePopup(feature.popup);
                    feature.popup.destroy();
                    feature.popup = null;
                }
            });

			var ITFilter = new OpenLayers.Strategy.Filter({
				filter: new OpenLayers.Filter.Comparison({
				      type: OpenLayers.Filter.Comparison.EQUAL_TO,
				      property: "FCLTY_SE",
				      value: "I"
				})
			});
			ITFilter.deactivate();

			var gisTeleFcltyStyle = new OpenLayers.Style();
			rules=[];
			$.each(fcltyITRuleSet, function() {
				$.each(this.symbolizer, function(key, val) {
					if(val.externalGraphic!=undefined || val.externalGraphic!=null) {
						val.externalGraphic=EMD.context_root+val.externalGraphic;
					}
				});
				rules[rules.length] = new OpenLayers.Rule({
					title: this.title,
					filter: this.filter,
					symbolizer: this.symbolizer
				});
			});
			gisTeleFcltyStyle.addRules( rules );
			var gisTeleFcltySaveStrategy = new OpenLayers.Strategy.Save();
			gisTeleFcltySaveStrategy.events.register('start', null, function(e) {
				EMD.util.showProgress();
			});
			gisTeleFcltySaveStrategy.events.register('success', null, function(e) {
				EMD.util.hideProgress();
				this.layer.refreshStrategy.refresh();
			});
			gisTeleFcltySaveStrategy.events.register('fail', null, function(e) {
				EMD.util.hideProgress();
				alert('자산코드 저장에 실패 하였습니다.');
			});
			var gisTeleFcltyRefreshStrategy = new OpenLayers.Strategy.Refresh();

			EMD.userLayer.gisTeleFclty = new OpenLayers.Layer.Vector("정보통신 시설 정보", {
		 		minScale: 9000,
				strategies: [new OpenLayers.Strategy.Fixed(), gisTeleFcltySaveStrategy, gisTeleFcltyRefreshStrategy],
		        projection: new OpenLayers.Projection("EPSG:5186"),
				protocol: EMD.protocols.gisPrtFcltyCd,
				styleMap: new OpenLayers.StyleMap( {
					"default" : gisTeleFcltyStyle,
					'select': {
						strokeOpacity: 1,
						strokeWidth: 3,
						fillColor: "#303294",
						fillOpacity: 1,
						pointRadius: 6,
						pointerEvents: "visiblePainted",
						fontColor: "blue",
						fontSize: "12px",
						fontWeight: "bold",
						labelAlign: "cm",
						labelOutlineColor: "white",
						labelOutlineWidth: 3
					},
					'modify': {
						strokeOpacity: 1,
						strokeWidth: 1,
						fillOpacity: 0.5,
						pointRadius: 6,
						label : "",
						pointerEvents: "visiblePainted",
						fontColor: "black",
						fontSize: "12px",
						fontFamily: "Courier New, monospace",
						fontWeight: "bold",
					    strokeColor: "#ff0000",
					    fillColor: "#ff0000",
					    graphicName: "circle"
					}
					}, {extendDefault: false}),
					filter:new OpenLayers.Filter.Comparison({
					      type: OpenLayers.Filter.Comparison.EQUAL_TO,
					      property: "FCLTY_SE",
					      value: "I"
					}),
					saveStrategy: gisTeleFcltySaveStrategy,
					refreshStrategy: gisTeleFcltyRefreshStrategy
// renderers: renderer
		 	});

			EMD.userLayer.gisTeleFclty.events.register('featuresadded', null, function(e) {
// e.feature.attribute['FCLTY_SE']='I';
			});

			EMD.userLayer.gisTeleFclty.events.register('beforefeatureselected', null, function(e) {
    			EMD.selectControl.unselectAll();
                if(EMD.popup.lotAreaInfo!=null) {
// EMD.selectControl.unselectAll();
                    EMD.map.removePopup(EMD.popup.lotAreaInfo);
                    EMD.popup.lotAreaInfo.destroy();
                    EMD.popup.lotAreaInfo=null;
                }
			});

			EMD.userLayer.gisTeleFclty.events.register('featureselected', null, function(e) {
				EMD.gis.openPopup(e.feature, "/maps/fclty/gamTeleFcltyInfo.do", [
   				       { name: 'gisAssetsPrtAtCode', value:e.feature.attributes.PRT_CD },
				       { name: 'gisAssetsCd', value:e.feature.attributes.ASSETS_CD },
				       { name: 'gisAssetsSubCd', value:e.feature.attributes.ASSETS_SCD },
				       { name: 'gisPrtFcltyCd', value:e.feature.attributes.FCLTY_CD },
				       { name: 'gisPrtFcltySeq', value:e.feature.attributes.FCLTY_SEQ },
				       { name: 'gisPrtFcltySe', value:e.feature.attributes.FCLTY_SE }
				       ]);
// console.log(e);
            });

			EMD.userLayer.gisTeleFclty.events.register('featureunselected', null, function(e) {
                var feature = e.feature;
                if(feature.popup!=null) {
                    EMD.map.removePopup(feature.popup);
                    feature.popup.destroy();
                    feature.popup = null;
                }
            });

			var elecFilter = new OpenLayers.Strategy.Filter({
				filter: new OpenLayers.Filter.Comparison({
				      type: OpenLayers.Filter.Comparison.EQUAL_TO,
				      property: "FCLTY_SE",
				      value: "E"
				})
			});
			elecFilter.deactivate();

			var gisPowerFcltyStyle = new OpenLayers.Style();
			rules=[];
			$.each(fcltyPowerRuleSet, function() {
				$.each(this.symbolizer, function(key, val) {
					if(val.externalGraphic!=undefined || val.externalGraphic!=null) {
						val.externalGraphic=EMD.context_root+val.externalGraphic;
					}
				});
				rules[rules.length] = new OpenLayers.Rule({
					title: this.title,
					filter: this.filter,
					symbolizer: this.symbolizer
				});
			});
			gisPowerFcltyStyle.addRules( rules );
			var gisElecFcltySaveStrategy = new OpenLayers.Strategy.Save();
			gisElecFcltySaveStrategy.events.register('start', null, function(e) {
				EMD.util.showProgress();
			});
			gisElecFcltySaveStrategy.events.register('success', null, function(e) {
				EMD.util.hideProgress();
				this.layer.refreshStrategy.refresh();
			});
			gisElecFcltySaveStrategy.events.register('fail', null, function(e) {
				EMD.util.hideProgress();
				alert('자산코드 저장에 실패 하였습니다.');
			});
			var gisElecFcltyRefreshStrategy = new OpenLayers.Strategy.Refresh();

			EMD.userLayer.gisElecFclty = new OpenLayers.Layer.Vector("전기 시설 정보", {
		 		minScale: 9000,
				strategies: [new OpenLayers.Strategy.Fixed(), gisElecFcltySaveStrategy, gisElecFcltyRefreshStrategy],
		        projection: new OpenLayers.Projection("EPSG:5186"),
				protocol: EMD.protocols.gisPrtFcltyCd,
				styleMap: new OpenLayers.StyleMap( {
					"default" : gisPowerFcltyStyle,
					'select': {
						strokeOpacity: 1,
						strokeWidth: 3,
						fillColor: "#303294",
						fillOpacity: 1,
						pointRadius: 6,
						pointerEvents: "visiblePainted",
						fontColor: "blue",
						fontSize: "12px",
						fontWeight: "bold",
						labelAlign: "cm",
						labelOutlineColor: "white",
						labelOutlineWidth: 3
					},
					'modify': {
						strokeOpacity: 1,
						strokeWidth: 1,
						fillOpacity: 0.5,
						pointRadius: 6,
						label : "",
						pointerEvents: "visiblePainted",
						fontColor: "black",
						fontSize: "12px",
						fontFamily: "Courier New, monospace",
						fontWeight: "bold",
					    strokeColor: "#ff0000",
					    fillColor: "#ff0000",
					    graphicName: "circle"
					}
					}, {extendDefault: false}),
					filter:new OpenLayers.Filter.Comparison({
					      type: OpenLayers.Filter.Comparison.EQUAL_TO,
					      property: "FCLTY_SE",
					      value: "E"
					}),
					saveStrategy: gisElecFcltySaveStrategy,
					refreshStrategy: gisElecFcltyRefreshStrategy
// renderers: renderer
		 	});

			EMD.userLayer.gisElecFclty.events.register('featuresadded', null, function(e) {
// e.feature.attribute['FCLTY_SE']='E';
			});

			EMD.userLayer.gisElecFclty.events.register('beforefeatureselected', null, function(e) {
    			EMD.selectControl.unselectAll();
                if(EMD.popup.lotAreaInfo!=null) {
// EMD.selectControl.unselectAll();
                    EMD.map.removePopup(EMD.popup.lotAreaInfo);
                    EMD.popup.lotAreaInfo.destroy();
                    EMD.popup.lotAreaInfo=null;
                }
			});

			EMD.userLayer.gisElecFclty.events.register('featureselected', null, function(e) {
				EMD.gis.openPopup(e.feature, "/maps/fclty/gamElecFcltyInfo.do", [
   				       { name: 'gisAssetsPrtAtCode', value:e.feature.attributes.PRT_CD },
				       { name: 'gisAssetsCd', value:e.feature.attributes.ASSETS_CD },
				       { name: 'gisAssetsSubCd', value:e.feature.attributes.ASSETS_SCD },
				       { name: 'gisPrtFcltyCd', value:e.feature.attributes.FCLTY_CD },
				       { name: 'gisPrtFcltySeq', value:e.feature.attributes.FCLTY_SEQ },
				       { name: 'gisPrtFcltySe', value:e.feature.attributes.FCLTY_SE }
				       ]);
// console.log(e);
            });

			EMD.userLayer.gisElecFclty.events.register('featureunselected', null, function(e) {
                var feature = e.feature;
                if(feature.popup!=null) {
                    EMD.map.removePopup(feature.popup);
                    feature.popup.destroy();
                    feature.popup = null;
                }
            });

			var civilFilter = new OpenLayers.Strategy.Filter({
				filter: new OpenLayers.Filter.Comparison({
				      type: OpenLayers.Filter.Comparison.EQUAL_TO,
				      property: "FCLTY_SE",
				      value: "C"
				})
			});
			civilFilter.deactivate();

			var gisCivilFcltyStyle = new OpenLayers.Style();
			var rules=[];
			$.each(fcltyCivilRuleSet, function() {
				$.each(this.symbolizer, function(key, val) {
					if(val.externalGraphic!=undefined || val.externalGraphic!=null) {
						val.externalGraphic=EMD.context_root+val.externalGraphic;
					}
				});
				rules[rules.length] = new OpenLayers.Rule({
					title: this.title,
					filter: this.filter,
					symbolizer: this.symbolizer
				});
			});
			gisCivilFcltyStyle.addRules( rules );
			var gisCivilFcltySaveStrategy = new OpenLayers.Strategy.Save();
			gisCivilFcltySaveStrategy.events.register('start', null, function(e) {
				EMD.util.showProgress();
			});
			gisCivilFcltySaveStrategy.events.register('success', null, function(e) {
				EMD.util.hideProgress();
				this.layer.refreshStrategy.refresh();
			});
			gisCivilFcltySaveStrategy.events.register('fail', null, function(e) {
				EMD.util.hideProgress();
				alert('자산코드 저장에 실패 하였습니다.');
			});
			var gisCivilFcltyRefreshStrategy = new OpenLayers.Strategy.Refresh();

			EMD.userLayer.gisCivilFclty = new OpenLayers.Layer.Vector("토목 시설 정보", {
		 		minScale: 9000,
				strategies: [new OpenLayers.Strategy.Fixed(), gisCivilFcltySaveStrategy, gisCivilFcltyRefreshStrategy],
		        projection: new OpenLayers.Projection("EPSG:5186"),
				protocol: EMD.protocols.gisPrtFcltyCd,
				styleMap: new OpenLayers.StyleMap( {
					"default": gisCivilFcltyStyle,
					'select': {
						strokeOpacity: 1,
						strokeWidth: 7,
						fillColor: "#303294",
						fillOpacity: 1,
						pointRadius: 6,
						pointerEvents: "visiblePainted",
						fontColor: "blue",
						fontSize: "12px",
						fontWeight: "bold",
						labelAlign: "cm",
						labelOutlineColor: "white",
						labelOutlineWidth: 3
					},
					'modify': {
						strokeOpacity: 1,
						strokeWidth: 1,
						fillOpacity: 0.5,
						pointRadius: 6,
						label : "",
						pointerEvents: "visiblePainted",
						fontColor: "black",
						fontSize: "12px",
						fontFamily: "Courier New, monospace",
						fontWeight: "bold",
					    strokeColor: "#ff0000",
					    fillColor: "#ff0000",
					    graphicName: "circle"
					}
					}, {extendDefault: false}
				),
				filter:new OpenLayers.Filter.Comparison({
				      type: OpenLayers.Filter.Comparison.EQUAL_TO,
				      property: "FCLTY_SE",
				      value: "C"
				}),
				saveStrategy: gisCivilFcltySaveStrategy,
				refreshStrategy: gisCivilFcltyRefreshStrategy
// renderers: renderer
		 	});

			EMD.userLayer.gisCivilFclty.events.register('featuresadded', null, function(e) {
// e.feature.attribute['FCLTY_SE']='C';
			});

			EMD.userLayer.gisCivilFclty.events.register('beforefeatureselected', null, function(e) {
    			EMD.selectControl.unselectAll();
                if(EMD.popup.lotAreaInfo!=null) {
// EMD.selectControl.unselectAll();
                    EMD.map.removePopup(EMD.popup.lotAreaInfo);
                    EMD.popup.lotAreaInfo.destroy();
                    EMD.popup.lotAreaInfo=null;
                }
			});

			EMD.userLayer.gisCivilFclty.events.register('featureselected', null, function(e) {
				EMD.gis.openPopup(e.feature, "/maps/fclty/gamCivilFcltyInfo.do", [
   				       { name: 'gisAssetsPrtAtCode', value:e.feature.attributes.PRT_CD },
				       { name: 'gisAssetsCd', value:e.feature.attributes.ASSETS_CD },
				       { name: 'gisAssetsSubCd', value:e.feature.attributes.ASSETS_SCD },
				       { name: 'gisPrtFcltyCd', value:e.feature.attributes.FCLTY_CD },
				       { name: 'gisPrtFcltySeq', value:e.feature.attributes.FCLTY_SEQ },
				       { name: 'gisPrtFcltySe', value:e.feature.attributes.FCLTY_SE }
				       ]);
// console.log(e);
            });

			EMD.userLayer.gisCivilFclty.events.register('featureunselected', null, function(e) {
                var feature = e.feature;
                if(feature.popup!=null) {
                    EMD.map.removePopup(feature.popup);
                    feature.popup.destroy();
                    feature.popup = null;
                }
            });

			// 자산 임대 현황
			var assetsRentStyle = new OpenLayers.Style();

			var rules=[
				new OpenLayers.Rule({
					title: "일반부두 임대",
					filter: new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "QUAY_CD",
			        	  value: 'G'
			          }),
					symbolizer: {
						"Polygon" : {
							fillColor: "#3F48CC",
							fillOpacity: 0.5,
							strokeWidth : 1,
							strokeOpacity : 1,
							strokeColor : "#FFFFFF",
							label : "${ENTRPS_NM}\n${GR_AR}m²",
							fontColor: "black",
							fontSize: "12px",
							fontFamily: "굴림",
							fontWeight: "bold",
							labelAlign: "cm",
							labelXOffset: "2px",
							labelYOffset: "16px",
							labelOutlineColor: "white",
							labelOutlineWidth: 3
						}
					}
				}),
				new OpenLayers.Rule({
					title: "배후단지 임대",
					filter: new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "QUAY_CD",
			        	  value: 'H'
			          }),
					symbolizer: {
						"Polygon" : {
							fillColor: "#00A2E8",
							fillOpacity: 0.5,
							strokeWidth : 1,
							strokeOpacity : 1,
							strokeColor : "#FFFFFF",
							label : "${ENTRPS_NM}\n${GR_AR}m²",
							fontColor: "black",
							fontSize: "12px",
							fontFamily: "굴림",
							fontWeight: "bold",
							labelAlign: "cm",
							labelXOffset: "2px",
							labelYOffset: "16px",
							labelOutlineColor: "white",
							labelOutlineWidth: 3
						}
					}
				}),
				new OpenLayers.Rule({
					title: "항만관련부지 임대",
					filter: new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "QUAY_CD",
			        	  value: 'C'
			          }),
					symbolizer: {
						"Polygon" : {
							fillColor: "#FF7F27",
							fillOpacity: 0.5,
							strokeWidth : 1,
							strokeOpacity : 1,
							strokeColor : "#FFFFFF",
							label : "${ENTRPS_NM}\n${GR_AR}m²",
							fontColor: "black",
							fontSize: "12px",
							fontFamily: "굴림",
							fontWeight: "bold",
							labelAlign: "cm",
							labelXOffset: "2px",
							labelYOffset: "16px",
							labelOutlineColor: "white",
							labelOutlineWidth: 3
						}
					}
				}),
				new OpenLayers.Rule({
					title: "공컨장치장 임대",
					filter: new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "QUAY_CD",
			        	  value: 'E'
			          }),
					symbolizer: {
						"Polygon" : {
							fillColor: "#A349A4",
							fillOpacity: 0.5,
							strokeWidth : 1,
							strokeOpacity : 1,
							strokeColor : "#FFFFFF",
							label : "${ENTRPS_NM}\n${GR_AR}m²",
							fontColor: "black",
							fontSize: "12px",
							fontFamily: "굴림",
							fontWeight: "bold",
							labelAlign: "cm",
							labelXOffset: "2px",
							labelYOffset: "16px",
							labelOutlineColor: "white",
							labelOutlineWidth: 3
						}
					}
				}),
				new OpenLayers.Rule({
					title: "철송장 임대",
					filter: new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "QUAY_CD",
			        	  value: 'T'
			          }),
					symbolizer: {
						"Polygon" : {
							fillColor: "#7F7F7F",
							fillOpacity: 0.5,
							strokeWidth : 1,
							strokeOpacity : 1,
							strokeColor : "#FFFFFF",
							label : "${ENTRPS_NM}\n${GR_AR}m²",
							fontColor: "black",
							fontSize: "12px",
							fontFamily: "굴림",
							fontWeight: "bold",
							labelAlign: "cm",
							labelXOffset: "2px",
							labelYOffset: "16px",
							labelOutlineColor: "white",
							labelOutlineWidth: 3
						}
					}
				})
			];
			assetsRentStyle.addRules( rules );

			var saveAssetsRent = new OpenLayers.Strategy.Save({auto:false});
			var refreshAssetsRent = new OpenLayers.Strategy.Refresh();

			var today = EMD.util.getDate().replace(/-/g, "");
		 	EMD.userLayer.assetsRent = new OpenLayers.Layer.Vector("임대정보", {
		 		minScale: 10000,
				strategies: [new OpenLayers.Strategy.BBOX(), saveAssetsRent, refreshAssetsRent],
		        projection: new OpenLayers.Projection("EPSG:5186"),
				protocol: EMD.protocols.assetsRent,
				styleMap: new OpenLayers.StyleMap({
					"default" : assetsRentStyle,
					'select': {
						strokeOpacity: 1,
						strokeWidth: 3,
						fillColor: "#303294",
						fillOpacity: 1,
						pointRadius: 6,
						pointerEvents: "visiblePainted",
						fontColor: "blue",
						fontSize: "12px",
						fontWeight: "bold",
						labelAlign: "cm",
						labelOutlineColor: "white",
						labelOutlineWidth: 3
					},
					'modify': {
						strokeOpacity: 1,
						strokeWidth: 1,
						fillOpacity: 0.5,
						pointRadius: 6,
						label : "",
						pointerEvents: "visiblePainted",
						fontColor: "black",
						fontSize: "12px",
						fontFamily: "Courier New, monospace",
						fontWeight: "bold",
					    strokeColor: "#ff0000",
					    fillColor: "#ff0000",
					    graphicName: "circle"
					}
					}, {extendDefault: false}),
					filter:new OpenLayers.Filter.Logical({
						type: OpenLayers.Filter.Logical.AND,
						filters: [
						          new OpenLayers.Filter.Comparison({
								      type: OpenLayers.Filter.Comparison.LESS_THAN,
								      property: "DT_FR",
								      value: today
						          }),
						          new OpenLayers.Filter.Comparison({
								      type: OpenLayers.Filter.Comparison.GREATER_THAN ,
								      property: "DT_TO",
								      value: today
						          }),
						          new OpenLayers.Filter.Comparison({
								      type: OpenLayers.Filter.Comparison.EQUAL_TO ,
								      property: "PRMISN_YN",
								      value: "Y"
						          })
				        ]
					}),
					saveStrategy: saveAssetsRent,
					refreshStrategy: refreshAssetsRent
		 	});

		 	EMD.userLayer.assetsRent.events.register('visibilitychanged', null, function(e) {
				var legendTab = EMD.legendWin.tabs("assetsRent");
				if(e.object.visibility) {
					$('#legendPanel').show();
					if(legendTab==undefined || legendTab==null) {
						EMD.legendWin.addTab("assetsRent", "임대현황 범례", null, null, true);
						legendTab = EMD.gis.addLegendLayer(e.object, "assetsRent");
						EMD.legendWin._counts++;
					}
					legendTab.show(true);
				}
				else {
					if(legendTab!=undefined && legendTab!=null) {
						legendTab.close();
						EMD.legendWin._counts--;
						if(EMD.legendWin._counts<=0) {
							EMD.legendWin._counts=0;
							$('#legendPanel').hide();
						}
					}
				}
			});

			EMD.userLayer.assetsRent.events.register('featureselected', null, function(e) {
				var feature = e.feature;

				EMD.gis.openPopup(feature, "/maps/assets/gamAssetRentInfo.do",
					feature.attributes,
			       function(feature) {
				});
			});

			EMD.userLayer.assetsRent.events.register('featureunselected', null, function(e) {
                var feature = e.feature;
                if(feature.popup!=null) {
                    EMD.map.removePopup(feature.popup);
                    feature.popup.destroy();
                    feature.popup = null;
                }
            });

			/*
			 * // 시설 임대 조회 레이어 EMD.userStyle.assetRentStyle['default'] = {
			 * 'default':{ strokeColor: "#000000", strokeOpacity: 1,
			 * strokeWidth: 1, fillColor: "#6EBDA8", fillOpacity: 0.5,
			 * pointRadius: 6, pointerEvents: "visiblePainted", // label with \n
			 * linebreaks label : "관리번호:
			 * ${MNG_YEAR}-${MNG_NO}-${MNG_CNT}\n${USAGE_PD_FROM}~${USAGE_PD_TO}",
			 * fontColor: "black", fontSize: "12px", fontFamily: "Courier New,
			 * monospace", fontWeight: "bold", labelAlign: "cm", labelXOffset:
			 * "2px", labelYOffset: "2px", labelOutlineColor: "white",
			 * labelOutlineWidth: 3 }, 'select': { strokeColor: "#00FF00",
			 * strokeOpacity: 1, strokeWidth: 3, fillColor: "#D5DD03",
			 * fillOpacity: 1, pointRadius: 6, pointerEvents: "visiblePainted", //
			 * label with \n linebreaks label : "관리번호:
			 * ${MNG_YEAR}-${MNG_NO}-${MNG_CNT}\n${USAGE_PD_FROM}~${USAGE_PD_TO}",
			 * fontColor: "white", fontSize: "12px", fontFamily: "Courier New,
			 * monospace", fontWeight: "bold", labelAlign: "cm", labelXOffset:
			 * "2px", labelYOffset: "2px", labelOutlineColor: "black",
			 * labelOutlineWidth: 3 } };
			 *
			 * EMD.userStyle.assetsRentDetailStyle['default'] = { 'default':{
			 * strokeColor: "#000000", strokeOpacity: 1, strokeWidth: 1,
			 * fillColor: "#6EBDA8", fillOpacity: 0.5, pointRadius: 6,
			 * pointerEvents: "visiblePainted", // label with \n linebreaks //
			 * label : "사용면적: ${USAGE_AR}\n${USAGE_PD_FROM}~${USAGE_PD_TO}",
			 * fontColor: "black", fontSize: "12px", fontFamily: "Courier New,
			 * monospace", fontWeight: "bold", labelAlign: "cm", labelXOffset:
			 * "2px", labelYOffset: "2px", labelOutlineColor: "white",
			 * labelOutlineWidth: 3 }, 'select': { strokeColor: "#00FF00",
			 * strokeOpacity: 1, strokeWidth: 3, fillColor: "#D5DD03",
			 * fillOpacity: 1, pointRadius: 6, pointerEvents: "visiblePainted", //
			 * label with \n linebreaks // label : "사용면적:
			 * ${USAGE_AR}\n${USAGE_PD_FROM}~${USAGE_PD_TO}", fontColor:
			 * "white", fontSize: "12px", fontFamily: "Courier New, monospace",
			 * fontWeight: "bold", labelAlign: "cm", labelXOffset: "2px",
			 * labelYOffset: "2px", labelOutlineColor: "black",
			 * labelOutlineWidth: 3 } };
			 *
			 * EMD.strategies.assetsRent = new OpenLayers.Strategy.Refresh();
			 *
			 * EMD.userLayer.assetsRent = new OpenLayers.Layer.Vector("임대정보", {
			 * minScale: 10000, strategies: [EMD.strategies.assetsRent,
			 * EMD.saveStrategy], projection: new
			 * OpenLayers.Projection("EPSG:5186"), protocol:
			 * EMD.protocols.assetsRent, displayInLayerSwitcher: true, styleMap:
			 * new OpenLayers.StyleMap(EMD.userStyle.assetRentStyle['default'])
			 * }); EMD.protocols.assetsRent.layer=EMD.userLayer.assetsRent; //
			 * 레이어 저장용 프로토콜을 지정 한다.
			 *
			 * EMD.userLayer.assetsRent.events.register('featuresadded', null,
			 * function(e) { });
			 *
			 * EMD.userLayer.assetsRent.events.register('featureselected', null,
			 * function(e) { var feature = e.feature;
			 *
			 * $.post( EMD.context_root+"/maps/assets/gamAssetCdInfo.do", [ {
			 * name: 'gisAssetsPrtAtCode', value:e.feature.attributes.PRT_CD }, {
			 * name: 'gisAssetsCd', value:e.feature.attributes.ASSETS_CD }, {
			 * name: 'gisAssetsSubCd', value:e.feature.attributes.ASSETS_SCD } ]
			 * ).done(function(data) { // $.pageslide({ direction: 'left',
			 * href='_secondary.html' }) var popup = new
			 * OpenLayers.Popup.FramedCloud("popup",
			 * feature.geometry.bounds.getCenterLonLat(), new
			 * OpenLayers.Size(250,150), data, null, true ); feature.popup =
			 * popup; EMD.map.addPopup(popup); // 버튼 이벤트
			 * $("button[data-role='assetMngt']",
			 * popup.contentDiv).button({icons: { primary:
			 * 'ui-icon-wrench'}}).click(function(event) {
			 * EMD.util.create_window('', $(this).text(),
			 * "/code/assets/gamAssetCodeMngt.do", null, { action:
			 * "prtFcltyInqire" ,gisPrtAtCode:
			 * $(this).data("assets-prt-at-code") ,gisAssetsCd:
			 * $(this).data("assets-cd") ,gisAssetsSubCd:
			 * $(this).data("assets-sub-cd") }); });
			 * $("button[data-role='assetInqire']",
			 * popup.contentDiv).button({icons: { primary:
			 * 'ui-icon-folder-open'}}).click(function(event) {
			 * EMD.util.create_window('', $(this).text(),
			 * "/code/assets/gamAssetCodeList.do", null, { action:
			 * "prtFcltyInqire" ,gisPrtAtCode:
			 * $(this).data("assets-prt-at-code") ,gisAssetsCd:
			 * $(this).data("assets-cd") ,gisAssetsSubCd:
			 * $(this).data("assets-sub-cd") }); }); // 피처 수정 버튼
			 * $("button[data-role='modifyFeature']",
			 * popup.contentDiv).button({icons: { primary:
			 * 'ui-icon-wrench'}}).click({feature: feature}, function(event) {
			 * EMD.util.create_window('', $(this).text(),
			 * "/code/assets/gamAssetCodeMngt.do", null, { action:
			 * "modifyFeature" ,gisPrtAtCode: $(this).data("assets-prt-at-code")
			 * ,gisAssetsCd: $(this).data("assets-cd") ,gisAssetsSubCd:
			 * $(this).data("assets-sub-cd") ,feature: event.data.feature });
			 * }); $("button[data-role='removeFeature']",
			 * popup.contentDiv).button({icons: { primary:
			 * 'ui-icon-trash'}}).click({feature: feature}, function(event) {
			 * EMD.map.removePopup(event.data.feature.popup);
			 * //EMD.gis.removeFeature('gisAssetsCd', event.data.feature);
			 * event.data.feature.state = OpenLayers.State.DELETE;
			 * EMD.userLayer.gisAssetsCd.removeFeatures([event.data.feature]);
			 * EMD.saveStrategy.save(); }); popup.updateSize(); // } }); });
			 * EMD.userLayer.assetsRent.events.register('featureunselected',
			 * null, function(e) { var feature = e.feature;
			 * if(feature.popup!=null) { EMD.map.removePopup(feature.popup);
			 * feature.popup.destroy(); feature.popup = null; } });
			 *  // EMD.strategies.assetsRentDetail = new
			 * OpenLayers.Strategy.Filter(); //
			 * EMD.strategies.assetsRentDetail.deactivate();
			 *
			 * EMD.userLayer.assetsRentDetail = new
			 * OpenLayers.Layer.Vector("시설임대상세", { minScale: 20000, strategies:
			 * [EMD.strategies.assetsRent, EMD.saveStrategy], projection: new
			 * OpenLayers.Projection("EPSG:5186"), protocol:
			 * EMD.protocols.assetsRentDetail, displayInLayerSwitcher: false, //
			 * if debug styleMap: new
			 * OpenLayers.StyleMap(EMD.userStyle.assetsRentDetailStyle['default']) //
			 * renderers: renderer });
			 * EMD.protocols.assetsRentDetail.layer=EMD.userLayer.assetsRentDetail; //
			 * 레이어 저장용 프로토콜을 지정 한다.
			 *
			 * EMD.userLayer.assetsRentDetail.events.register('featuresadded',
			 * null, function(e) { });
			 *
			 * EMD.userLayer.assetsRentDetail.events.register('featureselected',
			 * null, function(e) { EMD.gis.openPopup(e.feature ,
			 * "/maps/assets/gamMNGInfo.do" , [ { name: 'prtAtCode',
			 * value:e.feature.attributes.PRT_CD }, { name: 'mngYear',
			 * value:e.feature.attributes.MNG_YEAR }, { name: 'mngNo',
			 * value:e.feature.attributes.MNG_NO }, { name: 'mngCnt',
			 * value:e.feature.attributes.MNG_CNT } ] , function(feature) { var
			 * popup=feature.popup; // 버튼 이벤트 $("button[data-role='assetMngt']",
			 * popup.contentDiv).button({icons: { primary:
			 * 'ui-icon-wrench'}}).click(function(event) {
			 * EMD.util.create_window('', $(this).text(),
			 * "/code/assets/gamAssetCodeMngt.do", null, { action:
			 * "prtFcltyInqire" ,gisPrtAtCode:
			 * $(this).data("assets-prt-at-code") ,gisAssetsCd:
			 * $(this).data("assets-cd") ,gisAssetsSubCd:
			 * $(this).data("assets-sub-cd") }); });
			 * $("button[data-role='assetInqire']",
			 * popup.contentDiv).button({icons: { primary:
			 * 'ui-icon-folder-open'}}).click(function(event) {
			 * EMD.util.create_window('', $(this).text(),
			 * "/code/assets/gamAssetCodeList.do", null, { action:
			 * "prtFcltyInqire" ,gisPrtAtCode:
			 * $(this).data("assets-prt-at-code") ,gisAssetsCd:
			 * $(this).data("assets-cd") ,gisAssetsSubCd:
			 * $(this).data("assets-sub-cd") }); }); popup.updateSize(); } );
			 * });
			 * EMD.userLayer.assetsRentDetail.events.register('featureunselected',
			 * null, function(e) { var feature = e.feature;
			 * if(feature.popup!=null) { EMD.map.removePopup(feature.popup);
			 * feature.popup.destroy(); feature.popup = null; } });
			 *
			 */

			EMD.strategies.assetStats = new OpenLayers.Strategy.Refresh();

			var context = {
                    getColor: function(feature) {
                        return EMD.gis.getStatsColor(feature);
                    },
                    getLabel: function(feature) {
                    	return EMD.gis.getStatsLabel(feature);
                    }
                };
            var template = {
                    fillColor: "${getColor}",
                	label: "${getLabel}"
                };
            var style = new OpenLayers.Style(template, {
            	context: context,
            	labelOutlineColor: "white",
                labelOutlineWidth: 3
                });

		 	EMD.userLayer.assetStats = new OpenLayers.Layer.Vector("통계정보", {
		 		minScale: 72000,
				strategies: [EMD.strategies.assetStats],
		        projection: new OpenLayers.Projection("EPSG:5186"),
		 		styleMap: new OpenLayers.StyleMap(style)
		 	});

			EMD.strategies.assetStats.activate();

			EMD.userLayer.assetStats.events.register('featureselected', null, function(e) {
				EMD.gis.openPopup(e.feature, "/maps/assets/gamAssetCdInfo.do"
						,EMD.util.objectToArray(e.feature.attributes)
				, function(feature) {
					feature.popup.updateSize();
				});
	        });

			EMD.userLayer.assetStats.events.register('featureunselected', null, function(e) {
	            var feature = e.feature;
	            if(feature.popup!=null) {
	                EMD.map.removePopup(feature.popup);
	                feature.popup.destroy();
	                feature.popup = null;
	            }
	        });

			var currDate=new Date();

			EMD.strategies.assetRentStats = new OpenLayers.Strategy.Filter();

		 	EMD.userLayer.assetRentStats = new OpenLayers.Layer.Vector("임대현황", {
		 		minScale: 10000,
				strategies: [new OpenLayers.Strategy.BBOX(), EMD.strategies.assetRentStats],
		        projection: new OpenLayers.Projection("EPSG:5186"),
				protocol: EMD.protocols.assetsRentDetail,
				styleMap: new OpenLayers.StyleMap(EMD.userStyle.assetRentStyle['default'])
// renderers: renderer
		 	});

			EMD.strategies.assetRentStats.deactivate();
// EMD.strategies.assetRentStats.activate();

			EMD.userLayer.assetRentStats.events.register('featuresadded', null, function(e) {
			});

			EMD.userLayer.assetRentStats.events.register('featureselected', null, function(e) {
				EMD.gis.openPopup(e.feature
						,"/maps/assets/gamAssetRentInfo.do"
						,[
					       { name: 'prtAtCode', value:e.feature.attributes.PRT_CD },
					       { name: 'mngYear', value:e.feature.attributes.MNG_YEAR },
					       { name: 'mngNo', value:e.feature.attributes.MNG_NO },
					       { name: 'mngCnt', value:e.feature.attributes.MNG_CNT }
					       ]
						, function(feature) {
					var popup=feature.popup;
					// 버튼 이벤트
	                $("button[data-role='assetRentMngt']", popup.contentDiv).button({icons: {
						  	primary: 'ui-icon-wrench'}}).click(function(event) {
		                    	EMD.util.create_window('gamAssetRentMngt', $(this).text(), "/code/assets/gamAssetRentMngt.do", null, {
		                    		action: "assetRentMngt"
	                    			,prtAtCode: $(this).data("prt-at-code")
	                    			,mngYear: $(this).data("mng-year")
	                    			,mngNo: $(this).data("mng-no")
	                    			,mngCnt: $(this).data("mng-cnt")
                			});
						  });
	                $("button[data-role='assetRentInqire']", popup.contentDiv).button({icons: {
					  primary: 'ui-icon-folder-open'}}).click(function(event) {
	                    	EMD.util.create_window('gamAssetRentInqire', $(this).text(), "/code/assets/gamAssetRentInqire.do", null, {
		                    		action: "assetRentInqire"
	                    			,prtAtCode: $(this).data("prt-at-code")
	                    			,mngYear: $(this).data("mng-year")
	                    			,mngNo: $(this).data("mng-no")
	                    			,mngCnt: $(this).data("mng-cnt")
                			});
					  });
	                popup.updateSize();
				});
            });
			EMD.userLayer.assetRentStats.events.register('featureunselected', null, function(e) {
                var feature = e.feature;
                if(feature.popup!=null) {
                    EMD.map.removePopup(feature.popup);
                    feature.popup.destroy();
                    feature.popup = null;
                }
            });

			// 일반부두 임대현황

			EMD.strategies.gnrlRentStats = new OpenLayers.Strategy.Filter();
			EMD.strategies.gnrlRentStats.deactivate();

		 	EMD.userLayer.gnrlRentStats = new OpenLayers.Layer.Vector("일반부두 임대현황", {
		 		minScale: 10000,
				strategies: [EMD.strategies.gnrlRentStats],
		        projection: new OpenLayers.Projection("EPSG:5186"),
				protocol: EMD.protocols.assetsRent,
				styleMap: new OpenLayers.StyleMap(EMD.userStyle.assetRentStyle['default'])
// renderers: renderer
		 	});

			EMD.userLayer.gnrlRentStats.events.register('featuresadded', null, function(e) {
			});

			EMD.userLayer.gnrlRentStats.events.register('featureselected', null, function(e) {
				EMD.gis.openPopup(e.feature, "/oper/gnrl/gamPrtFcltyRentMngt.do"
						,[
					       { name: 'prtAtCode', value:e.feature.attributes.PRT_CD },
					       { name: 'mngYear', value:e.feature.attributes.MNG_YEAR },
					       { name: 'mngNo', value:e.feature.attributes.MNG_NO },
					       { name: 'mngCnt', value:e.feature.attributes.MNG_CNT }
					       ]
				,function(feature) {
					// 버튼 이벤트
	                $("button[data-role='assetRentMngt']", popup.contentDiv).button({icons: {
						  	primary: 'ui-icon-wrench'}}).click(function(event) {
		                    	EMD.util.create_window('gamAssetRentMngt', $(this).text(), "/code/assets/gamAssetRentMngt.do", null, {
		                    		action: "assetRentMngt"
	                    			,prtAtCode: $(this).data("prt-at-code")
	                    			,mngYear: $(this).data("mng-year")
	                    			,mngNo: $(this).data("mng-no")
	                    			,mngCnt: $(this).data("mng-cnt")
                			});
						  });
	                $("button[data-role='assetRentInqire']", popup.contentDiv).button({icons: {
					  primary: 'ui-icon-folder-open'}}).click(function(event) {
	                    	EMD.util.create_window('gamAssetRentInqire', $(this).text(), "/code/assets/gamAssetRentInqire.do", null, {
		                    		action: "assetRentInqire"
	                    			,prtAtCode: $(this).data("prt-at-code")
	                    			,mngYear: $(this).data("mng-year")
	                    			,mngNo: $(this).data("mng-no")
	                    			,mngCnt: $(this).data("mng-cnt")
                			});
					  });
	                popup.updateSize();
				});
            });
			EMD.userLayer.gnrlRentStats.events.register('featureunselected', null, function(e) {
                var feature = e.feature;
                if(feature.popup!=null) {
                    EMD.map.removePopup(feature.popup);
                    feature.popup.destroy();
                    feature.popup = null;
                }
            });

			// 컨테이너부두 임대현황

			EMD.strategies.cntrRentStats = new OpenLayers.Strategy.Filter();
			EMD.strategies.cntrRentStats.deactivate();

		 	EMD.userLayer.cntrRentStats = new OpenLayers.Layer.Vector("컨테이너부두 임대현황", {
		 		minScale: 10000,
				strategies: [EMD.strategies.cntrRentStats],
		        projection: new OpenLayers.Projection("EPSG:5186"),
				protocol: EMD.protocols.assetsRent,
				styleMap: new OpenLayers.StyleMap(EMD.userStyle.assetRentStyle['default'])
// renderers: renderer
		 	});

			EMD.userLayer.cntrRentStats.events.register('featuresadded', null, function(e) {
			});

			EMD.userLayer.cntrRentStats.events.register('featureselected', null, function(e) {
				var feature = e.feature;
				$.post(
					EMD.context_root+"/oper/gnrl/gamPrtFcltyRentMngt.do",
					[
				       { name: 'prtAtCode', value:e.feature.attributes.PRT_AT_CD },
				       { name: 'mngYear', value:e.feature.attributes.MNG_YEAR },
				       { name: 'mngNo', value:e.feature.attributes.MNG_NO },
				       { name: 'mngCnt', value:e.feature.attributes.MNG_CNT }
				       ] ).done(function(data) {
// $.pageslide({ direction: 'left', href='_secondary.html' })
			                var popup = new OpenLayers.Popup.FramedCloud("popup",
			                		feature.geometry.bounds.getCenterLonLat(),
			                		new OpenLayers.Size(250,150),
			                		data,
			                    null,
			                    true
			                );
			                feature.popup = popup;
			                EMD.map.addPopup(popup);
			                // 버튼 이벤트
			                $("button[data-role='assetRentMngt']", popup.contentDiv).button({icons: {
  							  	primary: 'ui-icon-wrench'}}).click(function(event) {
      		                    	EMD.util.create_window('gamAssetRentMngt', $(this).text(), "/code/assets/gamAssetRentMngt.do", null, {
      		                    		action: "assetRentMngt"
  		                    			,prtAtCode: $(this).data("prt-at-code")
  		                    			,mngYear: $(this).data("mng-year")
  		                    			,mngNo: $(this).data("mng-no")
  		                    			,mngCnt: $(this).data("mng-cnt")
	                    			});
  							  });
			                $("button[data-role='assetRentInqire']", popup.contentDiv).button({icons: {
							  primary: 'ui-icon-folder-open'}}).click(function(event) {
    		                    	EMD.util.create_window('gamAssetRentInqire', $(this).text(), "/code/assets/gamAssetRentInqire.do", null, {
      		                    		action: "assetRentInqire"
  		                    			,prtAtCode: $(this).data("prt-at-code")
  		                    			,mngYear: $(this).data("mng-year")
  		                    			,mngNo: $(this).data("mng-no")
  		                    			,mngCnt: $(this).data("mng-cnt")
	                    			});
							  });
			                popup.updateSize();
// }
					});
            });
			EMD.userLayer.cntrRentStats.events.register('featureunselected', null, function(e) {
                var feature = e.feature;
                if(feature.popup!=null) {
                    EMD.map.removePopup(feature.popup);
                    feature.popup.destroy();
                    feature.popup = null;
                }
            });

			// 배후단지 임대현황

			EMD.strategies.htldRentStats = new OpenLayers.Strategy.Filter();
			EMD.strategies.htldRentStats.deactivate();

		 	EMD.userLayer.htldRentStats = new OpenLayers.Layer.Vector("배후단지 임대현황", {
		 		minScale: 10000,
				strategies: [EMD.strategies.htldRentStats],
		        projection: new OpenLayers.Projection("EPSG:5186"),
				protocol: EMD.protocols.assetsRent,
				styleMap: new OpenLayers.StyleMap(EMD.userStyle.assetRentStyle['default'])
// renderers: renderer
		 	});

			EMD.userLayer.htldRentStats.events.register('featuresadded', null, function(e) {
			});

			EMD.userLayer.htldRentStats.events.register('featureselected', null, function(e) {
				var feature = e.feature;
				$.post(
					EMD.context_root+"/oper/gnrl/gamPrtFcltyRentMngt.do",
					[
				       { name: 'prtAtCode', value:e.feature.attributes.PRT_AT_CD },
				       { name: 'mngYear', value:e.feature.attributes.MNG_YEAR },
				       { name: 'mngNo', value:e.feature.attributes.MNG_NO },
				       { name: 'mngCnt', value:e.feature.attributes.MNG_CNT }
				       ] ).done(function(data) {
// $.pageslide({ direction: 'left', href='_secondary.html' })
			                var popup = new OpenLayers.Popup.FramedCloud("popup",
			                		feature.geometry.bounds.getCenterLonLat(),
			                		new OpenLayers.Size(250,150),
			                		data,
			                    null,
			                    true
			                );
			                feature.popup = popup;
			                EMD.map.addPopup(popup);
			                // 버튼 이벤트
			                $("button[data-role='assetRentMngt']", popup.contentDiv).button({icons: {
  							  	primary: 'ui-icon-wrench'}}).click(function(event) {
      		                    	EMD.util.create_window('gamAssetRentMngt', $(this).text(), "/code/assets/gamAssetRentMngt.do", null, {
      		                    		action: "assetRentMngt"
  		                    			,prtAtCode: $(this).data("prt-at-code")
  		                    			,mngYear: $(this).data("mng-year")
  		                    			,mngNo: $(this).data("mng-no")
  		                    			,mngCnt: $(this).data("mng-cnt")
	                    			});
  							  });
			                $("button[data-role='assetRentInqire']", popup.contentDiv).button({icons: {
							  primary: 'ui-icon-folder-open'}}).click(function(event) {
    		                    	EMD.util.create_window('gamAssetRentInqire', $(this).text(), "/code/assets/gamAssetRentInqire.do", null, {
      		                    		action: "assetRentInqire"
  		                    			,prtAtCode: $(this).data("prt-at-code")
  		                    			,mngYear: $(this).data("mng-year")
  		                    			,mngNo: $(this).data("mng-no")
  		                    			,mngCnt: $(this).data("mng-cnt")
	                    			});
							  });
			                popup.updateSize();
// }
					});
            });
			EMD.userLayer.htldRentStats.events.register('featureunselected', null, function(e) {
                var feature = e.feature;
                if(feature.popup!=null) {
                    EMD.map.removePopup(feature.popup);
                    feature.popup.destroy();
                    feature.popup = null;
                }
            });

			// 공컨장치장 임대현황

			EMD.strategies.ccntRentStats = new OpenLayers.Strategy.Filter();
			EMD.strategies.ccntRentStats.deactivate();

		 	EMD.userLayer.ccntRentStats = new OpenLayers.Layer.Vector("공컨장치장 임대현황", {
		 		minScale: 10000,
				strategies: [EMD.strategies.ccntRentStats],
		        projection: new OpenLayers.Projection("EPSG:5186"),
				protocol: EMD.protocols.assetsRent,
				styleMap: new OpenLayers.StyleMap(EMD.userStyle.assetRentStyle['default'])
// renderers: renderer
		 	});

			EMD.userLayer.ccntRentStats.events.register('featuresadded', null, function(e) {
			});

			EMD.userLayer.ccntRentStats.events.register('featureselected', null, function(e) {
				var feature = e.feature;
				$.post(
					EMD.context_root+"/oper/gnrl/gamPrtFcltyRentMngt.do",
					[
				       { name: 'prtAtCode', value:e.feature.attributes.PRT_AT_CD },
				       { name: 'mngYear', value:e.feature.attributes.MNG_YEAR },
				       { name: 'mngNo', value:e.feature.attributes.MNG_NO },
				       { name: 'mngCnt', value:e.feature.attributes.MNG_CNT }
				       ] ).done(function(data) {
// $.pageslide({ direction: 'left', href='_secondary.html' })
			                var popup = new OpenLayers.Popup.FramedCloud("popup",
			                		feature.geometry.bounds.getCenterLonLat(),
			                		new OpenLayers.Size(250,150),
			                		data,
			                    null,
			                    true
			                );
			                feature.popup = popup;
			                EMD.map.addPopup(popup);
			                // 버튼 이벤트
			                $("button[data-role='assetRentMngt']", popup.contentDiv).button({icons: {
  							  	primary: 'ui-icon-wrench'}}).click(function(event) {
      		                    	EMD.util.create_window('gamAssetRentMngt', $(this).text(), "/code/assets/gamAssetRentMngt.do", null, {
      		                    		action: "assetRentMngt"
  		                    			,prtAtCode: $(this).data("prt-at-code")
  		                    			,mngYear: $(this).data("mng-year")
  		                    			,mngNo: $(this).data("mng-no")
  		                    			,mngCnt: $(this).data("mng-cnt")
	                    			});
  							  });
			                $("button[data-role='assetRentInqire']", popup.contentDiv).button({icons: {
							  primary: 'ui-icon-folder-open'}}).click(function(event) {
    		                    	EMD.util.create_window('gamAssetRentInqire', $(this).text(), "/code/assets/gamAssetRentInqire.do", null, {
      		                    		action: "assetRentInqire"
  		                    			,prtAtCode: $(this).data("prt-at-code")
  		                    			,mngYear: $(this).data("mng-year")
  		                    			,mngNo: $(this).data("mng-no")
  		                    			,mngCnt: $(this).data("mng-cnt")
	                    			});
							  });
			                popup.updateSize();
// }
					});
            });
			EMD.userLayer.ccntRentStats.events.register('featureunselected', null, function(e) {
                var feature = e.feature;
                if(feature.popup!=null) {
                    EMD.map.removePopup(feature.popup);
                    feature.popup.destroy();
                    feature.popup = null;
                }
            });

			// 철송장 임대현황

			EMD.strategies.trnpRentStats = new OpenLayers.Strategy.Filter();
			EMD.strategies.trnpRentStats.deactivate();

		 	EMD.userLayer.trnpRentStats = new OpenLayers.Layer.Vector("철송장 임대현황", {
		 		minScale: 10000,
				strategies: [EMD.strategies.trnpRentStats],
		        projection: new OpenLayers.Projection("EPSG:5186"),
				protocol: EMD.protocols.assetsRent,
				styleMap: new OpenLayers.StyleMap(EMD.userStyle.assetRentStyle['default'])
// renderers: renderer
		 	});

			EMD.userLayer.trnpRentStats.events.register('featuresadded', null, function(e) {
			});

			EMD.userLayer.trnpRentStats.events.register('featureselected', null, function(e) {
				var feature = e.feature;
				$.post(
					EMD.context_root+"/oper/gnrl/gamPrtFcltyRentMngt.do",
					[
				       { name: 'prtAtCode', value:e.feature.attributes.PRT_AT_CD },
				       { name: 'mngYear', value:e.feature.attributes.MNG_YEAR },
				       { name: 'mngNo', value:e.feature.attributes.MNG_NO },
				       { name: 'mngCnt', value:e.feature.attributes.MNG_CNT }
				       ] ).done(function(data) {
// $.pageslide({ direction: 'left', href='_secondary.html' })
			                var popup = new OpenLayers.Popup.FramedCloud("popup",
			                		feature.geometry.bounds.getCenterLonLat(),
			                		new OpenLayers.Size(250,150),
			                		data,
			                    null,
			                    true
			                );
			                feature.popup = popup;
			                EMD.map.addPopup(popup);
			                // 버튼 이벤트
			                $("button[data-role='assetRentMngt']", popup.contentDiv).button({icons: {
  							  	primary: 'ui-icon-wrench'}}).click(function(event) {
      		                    	EMD.util.create_window('gamAssetRentMngt', $(this).text(), "/code/assets/gamAssetRentMngt.do", null, {
      		                    		action: "assetRentMngt"
  		                    			,prtAtCode: $(this).data("prt-at-code")
  		                    			,mngYear: $(this).data("mng-year")
  		                    			,mngNo: $(this).data("mng-no")
  		                    			,mngCnt: $(this).data("mng-cnt")
	                    			});
  							  });
			                $("button[data-role='assetRentInqire']", popup.contentDiv).button({icons: {
							  primary: 'ui-icon-folder-open'}}).click(function(event) {
    		                    	EMD.util.create_window('gamAssetRentInqire', $(this).text(), "/code/assets/gamAssetRentInqire.do", null, {
      		                    		action: "assetRentInqire"
  		                    			,prtAtCode: $(this).data("prt-at-code")
  		                    			,mngYear: $(this).data("mng-year")
  		                    			,mngNo: $(this).data("mng-no")
  		                    			,mngCnt: $(this).data("mng-cnt")
	                    			});
							  });
			                popup.updateSize();
// }
					});
            });
			EMD.userLayer.trnpRentStats.events.register('featureunselected', null, function(e) {
                var feature = e.feature;
                if(feature.popup!=null) {
                    EMD.map.removePopup(feature.popup);
                    feature.popup.destroy();
                    feature.popup = null;
                }
            });

			// 시설 코드 레이어
			/*
			 * EMD.userLayer.prtFclty = new OpenLayers.Layer.Vector("시설코드", {
			 * fillColor: "#073EC9", fillOpacity: 0.8, strokeColor: "#414141",
			 * strokeOpacity: 1, strokeWidth: 3, pointRadius: 6, pointerEvents:
			 * "visiblePainted", styleMap: new OpenLayers.StyleMap({ 'default':{
			 * strokeColor: "#000000", strokeOpacity: 1, strokeWidth: 1,
			 * fillColor: "#303294", fillOpacity: 0.5, pointRadius: 6,
			 * pointerEvents: "visiblePainted", // label with \n linebreaks
			 * label : "시설코드: ${FCLTY}\n시설명: ${name}\n\n속성: ${type}",
			 *
			 * fontColor: "${favColor}", fontSize: "12px", fontFamily: "Courier
			 * New, monospace", fontWeight: "bold", labelAlign: "cm",
			 * labelXOffset: "${xOffset}", labelYOffset: "${yOffset}",
			 * labelOutlineColor: "white", labelOutlineWidth: 3 }, 'select': {
			 * strokeColor: "#00FF00", strokeOpacity: 1, strokeWidth: 3,
			 * fillColor: "#303294", fillOpacity: 1, pointRadius: 6,
			 * pointerEvents: "visiblePainted", // label with \n linebreaks
			 * label : "시설코드: ${fcltyCd}\n시설명: ${name}\n\n속성: ${type}",
			 * fontColor: "${favColor}", fontSize: "12px", fontFamily: "Courier
			 * New, monospace", fontWeight: "bold", labelAlign: "cm",
			 * labelXOffset: "${xOffset}", labelYOffset: "${yOffset}",
			 * labelOutlineColor: "white", labelOutlineWidth: 3 } }), renderers:
			 * OpenLayers.Layer.Vector.prototype.renderers
			 *
			 * });
			 *
			 * EMD.userLayer.fcltyMarker = new OpenLayers.Layer.Vector( "시설물", {
			 * minScale: 5000, } );
			 *
			 * EMD.userLayer.fcltyMarker.events.register('featureselected',
			 * null, function(e) { $.ajax({ url:
			 * EMD.context_root+"/maps/fclty/gamFcltyInfo.do", type: 'POST',
			 * dataType: 'json', global: false, contentType:
			 * "application/x-www-form-urlencoded; charset=UTF-8", //
			 * contentType: "text/plain; charset=UTF-8", data: [ { name:
			 * 'gisAssetsPrtAtCode',
			 * value:e.feature.attributes.gisAssetsPrtAtCode}, { name:
			 * 'gisAssetsCd', value:e.feature.attributes.gisAssetsCd}, { name:
			 * 'gisAssetsSubCd', value:e.feature.attributes.gisAssetsSubCd}, {
			 * name: 'gisPrtFcltyCd', value:e.feature.attributes.gisPrtFcltyCd}, {
			 * name: 'gisPrtFcltySeq',
			 * value:e.feature.attributes.gisPrtFcltySeq}, { name:
			 * 'gisPrtFcltySe', value:e.feature.attributes.gisPrtFcltySe} ]
			 * }).done(function(data) { if(data.resultCode==0) { // if no error
			 * var htmlInfo = "<div class='prtFcltyInfo'><h2>시설정보</h2><table
			 * class='prtFcltyInfo'><tbody>"; var fclty = data.result;
			 *
			 * if(fclty==null) alert('선택한 시설의 정보가 없습니다.');
			 *
			 * htmlInfo+="<tr><th>자산코드</th><td>"+fclty.gisAssetsCd+"-"+fclty.gisAssetsSubCd+"</td></tr>";
			 * htmlInfo+="<tr><th>시설코드</th><td>"+fclty.gisPrtFcltyCd+"-"+fclty.gisPrtFcltySeq+"</td></tr>";
			 * if(fclty.prtFcltyNm!=null) htmlInfo+="<tr><th>시설명</th><td>"+fclty.prtFcltyNm+"</td></tr>";
			 * if(fclty.filenmPhysicl!=null) { htmlInfo+="<tr><th>시설사진</th><td><img
			 * src='"+EMD.context_root+"/cmm/getPfImage.do?physicalFileNm="+fclty.filenmPhysicl+"'
			 * style='width:300px;' /></td></tr>"; } // 권한에 따른 기능 추가
			 * switch(fclty.gisPrtFcltySe) { case 'S': // 토목 시설
			 * if(EMD.user.hasAuth('ROLE_ADMIN,ROLE_S_PRTFCLTY_MNGT')) {
			 * htmlInfo+="<tr><td colSpan='2' style='align: right;'>";
			 * htmlInfo += "<button id='btnPrtfcltyMngt'
			 * data-role='frmwrkButton'
			 * data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode +"'
			 * data-assets-cd='"+fclty.gisAssetsCd +"'
			 * data-assets-sub-cd='"+fclty.gisAssetsSubCd +"'
			 * data-prt-fclty-cd='"+fclty.gisPrtFcltyCd +"'
			 * data-prt-fclty-seq='"+fclty.gisPrtFcltySeq +"'
			 * data-prt-fclty-se='"+fclty.gisPrtFcltySe +"' >시설관리</button>";
			 * htmlInfo += "<button id='btnPrtfcltyInqire'
			 * data-role='frmwrkButton'
			 * data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode +"'
			 * data-assets-cd='"+fclty.gisAssetsCd +"'
			 * data-assets-sub-cd='"+fclty.gisAssetsSubCd +"'
			 * data-prt-fclty-cd='"+fclty.gisPrtFcltyCd +"'
			 * data-prt-fclty-seq='"+fclty.gisPrtFcltySeq +"'
			 * data-prt-fclty-se='"+fclty.gisPrtFcltySe +"' >시설조회</button>";
			 * htmlInfo+="</td></tr>"; } else
			 * if(EMD.user.hasAuth('ROLE_USER,ROLE_S_PRTFCLTY_INQIRE')) {
			 * htmlInfo+="<tr><td colSpan='2' style='align: right;'>";
			 * htmlInfo += "<button id='btnPrtfcltyInqire'
			 * data-role='frmwrkButton'
			 * data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode +"'
			 * data-assets-cd='"+fclty.gisAssetsCd +"'
			 * data-assets-sub-cd='"+fclty.gisAssetsSubCd +"'
			 * data-prt-fclty-cd='"+fclty.gisPrtFcltyCd +"'
			 * data-prt-fclty-seq='"+fclty.gisPrtFcltySeq +"'
			 * data-prt-fclty-se='"+fclty.gisPrtFcltySe +"' >시설조회</button>";
			 * htmlInfo+="</td></tr>"; } break; case 'C': // 건축 시설
			 * if(EMD.user.hasAuth('ROLE_ADMIN,ROLE_C_PRTFCLTY_MNGT')) {
			 * htmlInfo+="<tr><td colSpan='2' style='align: right;'>";
			 * htmlInfo += "<button id='btnPrtfcltyMngt'
			 * data-role='frmwrkButton'
			 * data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode +"'
			 * data-assets-cd='"+fclty.gisAssetsCd +"'
			 * data-assets-sub-cd='"+fclty.gisAssetsSubCd +"'
			 * data-prt-fclty-cd='"+fclty.gisPrtFcltyCd +"'
			 * data-prt-fclty-seq='"+fclty.gisPrtFcltySeq +"'
			 * data-prt-fclty-se='"+fclty.gisPrtFcltySe +"' >시설관리</button>";
			 * htmlInfo += "<button id='btnPrtfcltyInqire'
			 * data-role='frmwrkButton'
			 * data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode +"'
			 * data-assets-cd='"+fclty.gisAssetsCd +"'
			 * data-assets-sub-cd='"+fclty.gisAssetsSubCd +"'
			 * data-prt-fclty-cd='"+fclty.gisPrtFcltyCd +"'
			 * data-prt-fclty-seq='"+fclty.gisPrtFcltySeq +"'
			 * data-prt-fclty-se='"+fclty.gisPrtFcltySe +"' >시설조회</button>";
			 * htmlInfo+="</td></tr>"; } else
			 * if(EMD.user.hasAuth('ROLE_USER,ROLE_C_PRTFCLTY_INQIRE')) {
			 * htmlInfo+="<tr><td colSpan='2' style='align: right;'>";
			 * htmlInfo += "<button id='btnPrtfcltyInqire'
			 * data-role='frmwrkButton'
			 * data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode +"'
			 * data-assets-cd='"+fclty.gisAssetsCd +"'
			 * data-assets-sub-cd='"+fclty.gisAssetsSubCd +"'
			 * data-prt-fclty-cd='"+fclty.gisPrtFcltyCd +"'
			 * data-prt-fclty-seq='"+fclty.gisPrtFcltySeq +"'
			 * data-prt-fclty-se='"+fclty.gisPrtFcltySe +"' >시설조회</button>";
			 * htmlInfo+="</td></tr>"; } break; case 'E': // 전기 시설
			 * if(EMD.user.hasAuth('ROLE_ADMIN,ROLE_E_PRTFCLTY_MNGT')) {
			 * htmlInfo+="<tr><td colSpan='2' style='align: right;'>";
			 * htmlInfo += "<button id='btnPrtfcltyMngt'
			 * data-role='frmwrkButton'
			 * data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode +"'
			 * data-assets-cd='"+fclty.gisAssetsCd +"'
			 * data-assets-sub-cd='"+fclty.gisAssetsSubCd +"'
			 * data-prt-fclty-cd='"+fclty.gisPrtFcltyCd +"'
			 * data-prt-fclty-seq='"+fclty.gisPrtFcltySeq +"'
			 * data-prt-fclty-se='"+fclty.gisPrtFcltySe +"' >시설관리</button>";
			 * htmlInfo += "<button id='btnPrtfcltyInqire'
			 * data-role='frmwrkButton'
			 * data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode +"'
			 * data-assets-cd='"+fclty.gisAssetsCd +"'
			 * data-assets-sub-cd='"+fclty.gisAssetsSubCd +"'
			 * data-prt-fclty-cd='"+fclty.gisPrtFcltyCd +"'
			 * data-prt-fclty-seq='"+fclty.gisPrtFcltySeq +"'
			 * data-prt-fclty-se='"+fclty.gisPrtFcltySe +"' >시설조회</button>";
			 * htmlInfo+="</td></tr>"; } else
			 * if(EMD.user.hasAuth('ROLE_USER,ROLE_E_PRTFCLTY_INQIRE')) {
			 * htmlInfo+="<tr><td colSpan='2' style='align: right;'>";
			 * htmlInfo += "<button id='btnPrtfcltyInqire'
			 * data-role='frmwrkButton'
			 * data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode +"'
			 * data-assets-cd='"+fclty.gisAssetsCd +"'
			 * data-assets-sub-cd='"+fclty.gisAssetsSubCd +"'
			 * data-prt-fclty-cd='"+fclty.gisPrtFcltyCd +"'
			 * data-prt-fclty-seq='"+fclty.gisPrtFcltySeq +"'
			 * data-prt-fclty-se='"+fclty.gisPrtFcltySe +"' >시설조회</button>";
			 * htmlInfo+="</td></tr>"; } break; case 'M': // 기계 시설
			 * if(EMD.user.hasAuth('ROLE_ADMIN,ROLE_M_PRTFCLTY_MNGT')) {
			 * htmlInfo+="<tr><td colSpan='2' style='align: right;'>";
			 * htmlInfo += "<button id='btnPrtfcltyMngt'
			 * data-role='frmwrkButton'
			 * data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode +"'
			 * data-assets-cd='"+fclty.gisAssetsCd +"'
			 * data-assets-sub-cd='"+fclty.gisAssetsSubCd +"'
			 * data-prt-fclty-cd='"+fclty.gisPrtFcltyCd +"'
			 * data-prt-fclty-seq='"+fclty.gisPrtFcltySeq +"'
			 * data-prt-fclty-se='"+fclty.gisPrtFcltySe +"' >시설관리</button>";
			 * htmlInfo += "<button id='btnPrtfcltyInqire'
			 * data-role='frmwrkButton'
			 * data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode +"'
			 * data-assets-cd='"+fclty.gisAssetsCd +"'
			 * data-assets-sub-cd='"+fclty.gisAssetsSubCd +"'
			 * data-prt-fclty-cd='"+fclty.gisPrtFcltyCd +"'
			 * data-prt-fclty-seq='"+fclty.gisPrtFcltySeq +"'
			 * data-prt-fclty-se='"+fclty.gisPrtFcltySe +"' >시설조회</button>";
			 * htmlInfo+="</td></tr>"; } else
			 * if(EMD.user.hasAuth('ROLE_USER,ROLE_M_PRTFCLTY_INQIRE')) {
			 * htmlInfo+="<tr><td colSpan='2' style='align: right;'>";
			 * htmlInfo += "<button id='btnPrtfcltyInqire'
			 * data-role='frmwrkButton'
			 * data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode +"'
			 * data-assets-cd='"+fclty.gisAssetsCd +"'
			 * data-assets-sub-cd='"+fclty.gisAssetsSubCd +"'
			 * data-prt-fclty-cd='"+fclty.gisPrtFcltyCd +"'
			 * data-prt-fclty-seq='"+fclty.gisPrtFcltySeq +"'
			 * data-prt-fclty-se='"+fclty.gisPrtFcltySe +"' >시설조회</button>";
			 * htmlInfo+="</td></tr>"; } break; }
			 *
			 * htmlInfo+="</tbody></table>"; if(EMD.popup.prtFcltyInfo!=null) {
			 * EMD.map.removePopup(EMD.popup.prtFcltyInfo);
			 * EMD.popup.prtFcltyInfo=null; } if(fclty.loCrdnt==null ||
			 * fclty.laCrdnt==null) { if(fclty.lat!=null && fclty.lng!=null) {
			 * var latLng = new OpenLayers.LonLat(fclty.lng, fclty.lat); var
			 * proj = new OpenLayers.Projection('EPSG:4326'); var newPos=
			 * latLng.transform(proj, new OpenLayers.Projection('EPSG:900913'));
			 * fclty.loCrdnt=newPos.lon; fclty.laCrdnt=newPos.lat; } }
			 * EMD.popup.prtFcltyInfo = new OpenLayers.Popup.FramedCloud( "항만시설
			 * 속성", new OpenLayers.LonLat(fclty.loCrdnt,fclty.laCrdnt), new
			 * OpenLayers.Size(250,150), htmlInfo, null, true );
			 * EMD.map.addPopup(EMD.popup.prtFcltyInfo);
			 * $("#btnPrtfcltyMngt").button( { icons: { primary:
			 * 'ui-icon-wrench' }, text : true }).click(function(event) { var
			 * title=""; var url=""; switch($(this).data("prt-fclty-se")) { case
			 * 'S': title ='토목 시설 관리'; url="/fclty/gamCivilFcltyMngt.do"; break;
			 * case 'C': title ='건축 시설 관리'; url="/fclty/gamConstFcltyMngt.do";
			 * break; case 'E': title ='정보통신시설관리';
			 * url="/fclty/gamInfoTechFcltyMngt.do"; break; case 'M': title ='기계
			 * 시설 관리'; url="/fclty/gamMechFcltyMngt.do"; break; }
			 * EMD.util.create_window('', title, url, null, { action:
			 * "prtFcltyMngt" ,gisPrtAtCode: $(this).data("assets-prt-at-code")
			 * ,gisAssetsCd: $(this).data("assets-cd") ,gisAssetsSubCd:
			 * $(this).data("assets-sub-cd") ,gisPrtFcltyCd:
			 * $(this).data("prt-fclty-cd") ,gisPrtFcltySeq:
			 * $(this).data("prt-fclty-seq") ,gisPrtFcltySe:
			 * $(this).data("prt-fclty-se") }); });
			 * $("#btnPrtfcltyInqire").button( { icons: { primary:
			 * 'ui-icon-folder-open' }, text : true }).click(function(event) {
			 * var title=""; var url=""; switch($(this).data("prt-fclty-se")) {
			 * case 'S': title ='토목 시설 조회'; url="/fclty/gamCivilFcltyInqire.do";
			 * break; case 'C': title ='건축 시설 조회';
			 * url="/fclty/gamConstFcltyInqire.do"; break; case 'E': title
			 * ='정보통신시설조회'; url="/fclty/gamInfoTechFcltyInqire.do"; break; case
			 * 'M': title ='기계 시설 조회'; url="/fclty/gamMechFcltyInqire.do";
			 * break; } EMD.util.create_window('', title, url, null, { action:
			 * "prtFcltyMngt" ,gisPrtAtCode: $(this).data("assets-prt-at-code")
			 * ,gisAssetsCd: $(this).data("assets-cd") ,gisAssetsSubCd:
			 * $(this).data("assets-sub-cd") ,gisPrtFcltyCd:
			 * $(this).data("prt-fclty-cd") ,gisPrtFcltySeq:
			 * $(this).data("prt-fclty-seq") ,gisPrtFcltySe:
			 * $(this).data("prt-fclty-se") }); }); $('.prtFcltyInfo td >
			 * img').load(function(e){ var title = $("h2",
			 * EMD.popup.prtFcltyInfo.contentDiv); var t =
			 * $("table.prtFcltyInfo", EMD.popup.prtFcltyInfo.contentDiv);
			 * EMD.popup.prtFcltyInfo.setSize(t.width()+16,
			 * title.height()+t.height()+8); }); } }); });
			 *
			 * EMD.userLayer.fcltyMarker.events.register('featureunselected',
			 * null, function(e) { if(EMD.popup.prtFcltyInfo!=null) {
			 * EMD.map.removePopup(EMD.popup.prtFcltyInfo);
			 * EMD.popup.prtFcltyInfo=null; } });
			 */
			// 건축 시설
			EMD.userLayer.fcltyConstMarker = new OpenLayers.Layer.Vector( "건축 시설", {
		 		minScale: 5000
			} );

// EMD.protocols.assetsRentDetail.layer=EMD.userLayer.fcltyConstMarker; // 레이어
// 저장용 프로토콜을 지정 한다.


			EMD.userLayer.fcltyConstMarker.events.register('featureselected', null, function(e) {
					$.ajax({
					url: EMD.context_root+"/maps/fclty/gamFcltyInfo.do",
					type: 'POST',
					dataType: 'json',
					global: false,
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	// contentType: "text/plain; charset=UTF-8",
					data: [
					       { name: 'gisAssetsPrtAtCode', value:e.feature.attributes.gisAssetsPrtAtCode},
					       { name: 'gisAssetsCd', value:e.feature.attributes.gisAssetsCd},
					       { name: 'gisAssetsSubCd', value:e.feature.attributes.gisAssetsSubCd},
					       { name: 'gisPrtFcltyCd', value:e.feature.attributes.gisPrtFcltyCd},
					       { name: 'gisPrtFcltySeq', value:e.feature.attributes.gisPrtFcltySeq},
					       { name: 'gisPrtFcltySe', value:e.feature.attributes.gisPrtFcltySe}
					       ]
					}).done(function(data) {
						if(data.resultCode==0) {	// if no error
							var htmlInfo = "<div class='prtFcltyInfo'><h2>건축시설정보</h2><table class='prtFcltyInfo'><tbody>";
							var fclty = data.result;

							if(fclty==null) alert('선택한 시설의 정보가 없습니다.');

							htmlInfo+="<tr><th>자산코드</th><td>"+fclty.gisAssetsCd+"-"+fclty.gisAssetsSubCd+"</td></tr>";
							htmlInfo+="<tr><th>시설코드</th><td>"+fclty.gisPrtFcltyCd+"-"+fclty.gisPrtFcltySeq+"</td></tr>";
							if(fclty.prtFcltyNm!=null) htmlInfo+="<tr><th>시설명</th><td>"+fclty.prtFcltyNm+"</td></tr>";
							if(fclty.gisAssetsLocplc!=null) {
								var lnm = fclty.gisAssetsLnm+((fclty.gisAssetsLnmSub!=null&&fclty.gisAssetsLnmSub.length>0)?'-'+fclty.gisAssetsLnmSub:'');
								htmlInfo+="<tr><th>소재지</th><td>"+fclty.gisAssetsLocplc+' '+lnm+"</td></tr>";
							}
							if(fclty.filenmPhysicl!=null) {
								htmlInfo+="<tr><th>시설사진</th><td><img src='"+EMD.context_root+"/cmm/getPfImage.do?physicalFileNm="+fclty.filenmPhysicl+"' style='width:300px;' /></td></tr>";
							}
							// 권한에 따른 기능 추가
							htmlInfo+="<tr><td colSpan='2' style='align: right;'>";
		                	if(EMD.popupMenu['fcltyConstPopMngt']!=undefined) {
		                		htmlInfo += "<td><button id='btnPrtfcltyMngt' data-role='frmwrkButton' data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode
		                			+"' data-assets-cd='"+fclty.gisAssetsCd
		                			+"' data-assets-sub-cd='"+fclty.gisAssetsSubCd
		                			+"' data-prt-fclty-cd='"+fclty.gisPrtFcltyCd
		                			+"' data-prt-fclty-seq='"+fclty.gisPrtFcltySeq
		                			+"' data-prt-fclty-se='"+fclty.gisPrtFcltySe
		                			+"' >시설관리</button></td>";
		                	}
		                	if(EMD.popupMenu['fcltyConstPopInqire']!=undefined) {
			                		htmlInfo += "<td><button id='btnPrtfcltyInqire' data-role='frmwrkButton' data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode
	                			+"' data-assets-cd='"+fclty.gisAssetsCd
	                			+"' data-assets-sub-cd='"+fclty.gisAssetsSubCd
	                			+"' data-prt-fclty-cd='"+fclty.gisPrtFcltyCd
	                			+"' data-prt-fclty-seq='"+fclty.gisPrtFcltySeq
	                			+"' data-prt-fclty-se='"+fclty.gisPrtFcltySe
	                			+"' >시설조회</button></td>";
		                	}
							htmlInfo+="</tr>";

							htmlInfo+="</tbody></table>";
							if(EMD.popup.prtFcltyInfo!=null) {
								EMD.map.removePopup(EMD.popup.prtFcltyInfo);
								EMD.popup.prtFcltyInfo=null;
							}
							if(fclty.loCrdnt==null || fclty.laCrdnt==null) {
								if(fclty.lat!=null && fclty.lng!=null) {
									var latLng = new OpenLayers.LonLat(fclty.lng, fclty.lat);
									var proj = new OpenLayers.Projection('EPSG:4326');
									var newPos= latLng.transform(proj, new OpenLayers.Projection('EPSG:900913'));
									fclty.loCrdnt=newPos.lon;
									fclty.laCrdnt=newPos.lat;
								}
							}
							EMD.popup.prtFcltyInfo = new OpenLayers.Popup.FramedCloud(
					                "건축시설정보",
					                new OpenLayers.LonLat(fclty.loCrdnt,fclty.laCrdnt),
					                new OpenLayers.Size(250,150),
					                htmlInfo,
					                null,
					                true
					            );
				            EMD.map.addPopup(EMD.popup.prtFcltyInfo);
				            $("#btnPrtfcltyMngt").button(
	        						  { icons: {
	        							  primary: 'ui-icon-wrench'
	        						  },
	        						  text : true
	        						  }).click(function(event) {
	        							  var title="";
	        							  var url="";
        								  title ='건축 시설 관리';
        								  url=EMD.popupMenu['fcltyConstPopMngt'];
	      		                    	EMD.util.create_window('', title, url, null, {
	      		                    		action: "prtFcltyMngt"
	      		                    			,gisPrtAtCode: $(this).data("assets-prt-at-code")
	      		                    			,gisAssetsCd: $(this).data("assets-cd")
	      		                    			,gisAssetsSubCd: $(this).data("assets-sub-cd")
	      		                    			,gisPrtFcltyCd: $(this).data("prt-fclty-cd")
	      		                    			,gisPrtFcltySeq: $(this).data("prt-fclty-seq")
	      		                    			,gisPrtFcltySe: $(this).data("prt-fclty-se")
	      		                    			});
	        						  });
				            $("#btnPrtfcltyInqire").button(
	        						  { icons: {
	        							  primary: 'ui-icon-folder-open'
	        						  },
	        						  text : true
	        						  }).click(function(event) {
	        							  var title="";
	        							  var url="";
        								  title ='건축 시설 조회';
        								  url=EMD.popupMenu['fcltyConstPopInqire'];
	      		                    	EMD.util.create_window('', title, url, null, {
	      		                    		action: "prtFcltyMngt"
	      		                    			,gisPrtAtCode: $(this).data("assets-prt-at-code")
	      		                    			,gisAssetsCd: $(this).data("assets-cd")
	      		                    			,gisAssetsSubCd: $(this).data("assets-sub-cd")
	      		                    			,gisPrtFcltyCd: $(this).data("prt-fclty-cd")
	      		                    			,gisPrtFcltySeq: $(this).data("prt-fclty-seq")
	      		                    			,gisPrtFcltySe: $(this).data("prt-fclty-se")
	      		                    			});
	        						  });
				            EMD.popup.prtFcltyInfo.updateSize();
							$('.prtFcltyInfo td > img').load(function(e){
								var title = $("h2", EMD.popup.prtFcltyInfo.contentDiv);
								var t = $("table.prtFcltyInfo", EMD.popup.prtFcltyInfo.contentDiv);
					            EMD.popup.prtFcltyInfo.updateSize();
							});
						}
				});
			});

			EMD.userLayer.fcltyConstMarker.events.register('featureunselected', null, function(e) {
				if(EMD.popup.prtFcltyInfo!=null) {
					EMD.map.removePopup(EMD.popup.prtFcltyInfo);
					EMD.popup.prtFcltyInfo=null;
				}
			});

			// 토목 시설
			EMD.userLayer.fcltySocMarker = new OpenLayers.Layer.Vector( "토목 시설", {
		 		minScale: 5000
			} );

			EMD.userLayer.fcltySocMarker.events.register('featureselected', null, function(e) {
					$.ajax({
					url: EMD.context_root+"/maps/fclty/gamFcltyInfo.do",
					type: 'POST',
					dataType: 'json',
					global: false,
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	// contentType: "text/plain; charset=UTF-8",
					data: [
					       { name: 'gisAssetsPrtAtCode', value:e.feature.attributes.gisAssetsPrtAtCode},
					       { name: 'gisAssetsCd', value:e.feature.attributes.gisAssetsCd},
					       { name: 'gisAssetsSubCd', value:e.feature.attributes.gisAssetsSubCd},
					       { name: 'gisPrtFcltyCd', value:e.feature.attributes.gisPrtFcltyCd},
					       { name: 'gisPrtFcltySeq', value:e.feature.attributes.gisPrtFcltySeq},
					       { name: 'gisPrtFcltySe', value:e.feature.attributes.gisPrtFcltySe}
					       ]
					}).done(function(data) {
						if(data.resultCode==0) {	// if no error
							var htmlInfo = "<div class='prtFcltyInfo'><h2>토목시설정보</h2><table class='prtFcltyInfo'><tbody>";
							var fclty = data.result;

							if(fclty==null) alert('선택한 시설의 정보가 없습니다.');

							htmlInfo+="<tr><th>자산코드</th><td>"+fclty.gisAssetsCd+"-"+fclty.gisAssetsSubCd+"</td></tr>";
							htmlInfo+="<tr><th>시설코드</th><td>"+fclty.gisPrtFcltyCd+"-"+fclty.gisPrtFcltySeq+"</td></tr>";
							if(fclty.prtFcltyNm!=null) htmlInfo+="<tr><th>시설명</th><td>"+fclty.prtFcltyNm+"</td></tr>";
							if(fclty.gisAssetsLocplc!=null) {
								var lnm = fclty.gisAssetsLnm+(fclty.gisAssetsLnmSub!=null&&fclty.gisAssetsLnmSub.length>0)?'-'+fclty.gisAssetsLnmSub:'';
								htmlInfo+="<tr><th>소재지</th><td>"+fclty.gisAssetsLocplc+' '+lnm+"</td></tr>";
							}
							if(fclty.filenmPhysicl!=null) {
								htmlInfo+="<tr><th>시설사진</th><td><img src='"+EMD.context_root+"/cmm/getPfImage.do?physicalFileNm="+fclty.filenmPhysicl+"' style='width:300px;' /></td></tr>";
							}
							// 권한에 따른 기능 추가
							htmlInfo+="<tr><td colSpan='2' style='align: right;'>";
		                	if(EMD.popupMenu['fcltySocPopMngt']!=undefined) {
		                		htmlInfo += "<td><button id='btnPrtfcltyMngt' data-role='frmwrkButton' data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode
		                			+"' data-assets-cd='"+fclty.gisAssetsCd
		                			+"' data-assets-sub-cd='"+fclty.gisAssetsSubCd
		                			+"' data-prt-fclty-cd='"+fclty.gisPrtFcltyCd
		                			+"' data-prt-fclty-seq='"+fclty.gisPrtFcltySeq
		                			+"' data-prt-fclty-se='"+fclty.gisPrtFcltySe
		                			+"' >시설관리</button></td>";
		                	}
		                	if(EMD.popupMenu['fcltySocPopInqire']!=undefined) {
			                		htmlInfo += "<td><button id='btnPrtfcltyInqire' data-role='frmwrkButton' data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode
	                			+"' data-assets-cd='"+fclty.gisAssetsCd
	                			+"' data-assets-sub-cd='"+fclty.gisAssetsSubCd
	                			+"' data-prt-fclty-cd='"+fclty.gisPrtFcltyCd
	                			+"' data-prt-fclty-seq='"+fclty.gisPrtFcltySeq
	                			+"' data-prt-fclty-se='"+fclty.gisPrtFcltySe
	                			+"' >시설조회</button></td>";
		                	}
							htmlInfo+="</tr>";

							htmlInfo+="</tbody></table>";
							if(EMD.popup.prtFcltyInfo!=null) {
								EMD.map.removePopup(EMD.popup.prtFcltyInfo);
								EMD.popup.prtFcltyInfo=null;
							}
							if(fclty.loCrdnt==null || fclty.laCrdnt==null) {
								if(fclty.lat!=null && fclty.lng!=null) {
									var latLng = new OpenLayers.LonLat(fclty.lng, fclty.lat);
									var proj = new OpenLayers.Projection('EPSG:4326');
									var newPos= latLng.transform(proj, new OpenLayers.Projection('EPSG:900913'));
									fclty.loCrdnt=newPos.lon;
									fclty.laCrdnt=newPos.lat;
								}
							}
							EMD.popup.prtFcltyInfo = new OpenLayers.Popup.FramedCloud(
					                "토목시설정보",
					                new OpenLayers.LonLat(fclty.loCrdnt,fclty.laCrdnt),
					                new OpenLayers.Size(250,150),
					                htmlInfo,
					                null,
					                true
					            );
				            EMD.map.addPopup(EMD.popup.prtFcltyInfo);
				            $("#btnPrtfcltyMngt").button(
	        						  { icons: {
	        							  primary: 'ui-icon-wrench'
	        						  },
	        						  text : true
	        						  }).click(function(event) {
	        							  var title="";
	        							  var url="";
        								  title ='토목 시설 관리';
        								  url=EMD.popupMenu['fcltyConstPopMngt'];
	      		                    	EMD.util.create_window('', title, url, null, {
	      		                    		action: "prtFcltyMngt"
	      		                    			,gisPrtAtCode: $(this).data("assets-prt-at-code")
	      		                    			,gisAssetsCd: $(this).data("assets-cd")
	      		                    			,gisAssetsSubCd: $(this).data("assets-sub-cd")
	      		                    			,gisPrtFcltyCd: $(this).data("prt-fclty-cd")
	      		                    			,gisPrtFcltySeq: $(this).data("prt-fclty-seq")
	      		                    			,gisPrtFcltySe: $(this).data("prt-fclty-se")
	      		                    			});
	        						  });
				            $("#btnPrtfcltyInqire").button(
	        						  { icons: {
	        							  primary: 'ui-icon-folder-open'
	        						  },
	        						  text : true
	        						  }).click(function(event) {
	        							  var title="";
	        							  var url="";
        								  title ='건축 시설 조회';
        								  url=EMD.popupMenu['fcltyConstPopInqire'];
	      		                    	EMD.util.create_window('', title, url, null, {
	      		                    		action: "prtFcltyMngt"
	      		                    			,gisPrtAtCode: $(this).data("assets-prt-at-code")
	      		                    			,gisAssetsCd: $(this).data("assets-cd")
	      		                    			,gisAssetsSubCd: $(this).data("assets-sub-cd")
	      		                    			,gisPrtFcltyCd: $(this).data("prt-fclty-cd")
	      		                    			,gisPrtFcltySeq: $(this).data("prt-fclty-seq")
	      		                    			,gisPrtFcltySe: $(this).data("prt-fclty-se")
	      		                    			});
	        						  });
				            EMD.popup.prtFcltyInfo.updateSize();
							$('.prtFcltyInfo td > img').load(function(e){
								var title = $("h2", EMD.popup.prtFcltyInfo.contentDiv);
								var t = $("table.prtFcltyInfo", EMD.popup.prtFcltyInfo.contentDiv);
					            EMD.popup.prtFcltyInfo.updateSize();
							});
						}
				});
			});

			EMD.userLayer.fcltySocMarker.events.register('featureunselected', null, function(e) {
				if(EMD.popup.prtFcltyInfo!=null) {
					EMD.map.removePopup(EMD.popup.prtFcltyInfo);
					EMD.popup.prtFcltyInfo=null;
				}
			});

			// 기계 시설
			EMD.userLayer.fcltyMechMarker = new OpenLayers.Layer.Vector( "기계 시설", {
		 		minScale: 5000
			} );

			EMD.userLayer.fcltyMechMarker.events.register('featureselected', null, function(e) {
					$.ajax({
					url: EMD.context_root+"/maps/fclty/gamFcltyInfo.do",
					type: 'POST',
					dataType: 'json',
					global: false,
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	// contentType: "text/plain; charset=UTF-8",
					data: [
					       { name: 'gisAssetsPrtAtCode', value:e.feature.attributes.gisAssetsPrtAtCode},
					       { name: 'gisAssetsCd', value:e.feature.attributes.gisAssetsCd},
					       { name: 'gisAssetsSubCd', value:e.feature.attributes.gisAssetsSubCd},
					       { name: 'gisPrtFcltyCd', value:e.feature.attributes.gisPrtFcltyCd},
					       { name: 'gisPrtFcltySeq', value:e.feature.attributes.gisPrtFcltySeq},
					       { name: 'gisPrtFcltySe', value:e.feature.attributes.gisPrtFcltySe}
					       ]
					}).done(function(data) {
						if(data.resultCode==0) {	// if no error
							var htmlInfo = "<div class='prtFcltyInfo'><h2>기계시설정보</h2><table class='prtFcltyInfo'><tbody>";
							var fclty = data.result;

							if(fclty==null) alert('선택한 시설의 정보가 없습니다.');

							htmlInfo+="<tr><th>자산코드</th><td>"+fclty.gisAssetsCd+"-"+fclty.gisAssetsSubCd+"</td></tr>";
							htmlInfo+="<tr><th>시설코드</th><td>"+fclty.gisPrtFcltyCd+"-"+fclty.gisPrtFcltySeq+"</td></tr>";
							if(fclty.prtFcltyNm!=null) htmlInfo+="<tr><th>시설명</th><td>"+fclty.prtFcltyNm+"</td></tr>";
							if(fclty.gisAssetsLocplc!=null) {
								var lnm = fclty.gisAssetsLnm+(fclty.gisAssetsLnmSub!=null&&fclty.gisAssetsLnmSub.length>0)?'-'+fclty.gisAssetsLnmSub:'';
								htmlInfo+="<tr><th>소재지</th><td>"+fclty.gisAssetsLocplc+' '+lnm+"</td></tr>";
							}
							if(fclty.filenmPhysicl!=null) {
								htmlInfo+="<tr><th>시설사진</th><td><img src='"+EMD.context_root+"/cmm/getPfImage.do?physicalFileNm="+fclty.filenmPhysicl+"' style='width:300px;' /></td></tr>";
							}
							// 권한에 따른 기능 추가
							htmlInfo+="<tr><td colSpan='2' style='align: right;'>";
		                	if(EMD.popupMenu['fcltyMechPopMngt']!=undefined) {
		                		htmlInfo += "<td><button id='btnPrtfcltyMngt' data-role='frmwrkButton' data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode
		                			+"' data-assets-cd='"+fclty.gisAssetsCd
		                			+"' data-assets-sub-cd='"+fclty.gisAssetsSubCd
		                			+"' data-prt-fclty-cd='"+fclty.gisPrtFcltyCd
		                			+"' data-prt-fclty-seq='"+fclty.gisPrtFcltySeq
		                			+"' data-prt-fclty-se='"+fclty.gisPrtFcltySe
		                			+"' >시설관리</button></td>";
		                	}
		                	if(EMD.popupMenu['fcltyMechPopInqire']!=undefined) {
			                		htmlInfo += "<td><button id='btnPrtfcltyInqire' data-role='frmwrkButton' data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode
	                			+"' data-assets-cd='"+fclty.gisAssetsCd
	                			+"' data-assets-sub-cd='"+fclty.gisAssetsSubCd
	                			+"' data-prt-fclty-cd='"+fclty.gisPrtFcltyCd
	                			+"' data-prt-fclty-seq='"+fclty.gisPrtFcltySeq
	                			+"' data-prt-fclty-se='"+fclty.gisPrtFcltySe
	                			+"' >시설조회</button></td>";
		                	}
							htmlInfo+="</tr>";

							htmlInfo+="</tbody></table>";
							if(EMD.popup.prtFcltyInfo!=null) {
								EMD.map.removePopup(EMD.popup.prtFcltyInfo);
								EMD.popup.prtFcltyInfo=null;
							}
							if(fclty.loCrdnt==null || fclty.laCrdnt==null) {
								if(fclty.lat!=null && fclty.lng!=null) {
									var latLng = new OpenLayers.LonLat(fclty.lng, fclty.lat);
									var proj = new OpenLayers.Projection('EPSG:4326');
									var newPos= latLng.transform(proj, new OpenLayers.Projection('EPSG:900913'));
									fclty.loCrdnt=newPos.lon;
									fclty.laCrdnt=newPos.lat;
								}
							}
							EMD.popup.prtFcltyInfo = new OpenLayers.Popup.FramedCloud(
					                "기계시설정보",
					                new OpenLayers.LonLat(fclty.loCrdnt,fclty.laCrdnt),
					                new OpenLayers.Size(250,150),
					                htmlInfo,
					                null,
					                true
					            );
				            EMD.map.addPopup(EMD.popup.prtFcltyInfo);
				            $("#btnPrtfcltyMngt").button(
	        						  { icons: {
	        							  primary: 'ui-icon-wrench'
	        						  },
	        						  text : true
	        						  }).click(function(event) {
	        							  var title="";
	        							  var url="";
        								  title ='기계 시설 관리';
        								  url=EMD.popupMenu['fcltyMechPopMngt'];
	      		                    	EMD.util.create_window('', title, url, null, {
	      		                    		action: "prtFcltyMngt"
	      		                    			,gisPrtAtCode: $(this).data("assets-prt-at-code")
	      		                    			,gisAssetsCd: $(this).data("assets-cd")
	      		                    			,gisAssetsSubCd: $(this).data("assets-sub-cd")
	      		                    			,gisPrtFcltyCd: $(this).data("prt-fclty-cd")
	      		                    			,gisPrtFcltySeq: $(this).data("prt-fclty-seq")
	      		                    			,gisPrtFcltySe: $(this).data("prt-fclty-se")
	      		                    			});
	        						  });
				            $("#btnPrtfcltyInqire").button(
	        						  { icons: {
	        							  primary: 'ui-icon-folder-open'
	        						  },
	        						  text : true
	        						  }).click(function(event) {
	        							  var title="";
	        							  var url="";
        								  title ='기계 시설 조회';
        								  url=EMD.popupMenu['fcltyMechPopInqire'];
	      		                    	EMD.util.create_window('', title, url, null, {
	      		                    		action: "prtFcltyMngt"
	      		                    			,gisPrtAtCode: $(this).data("assets-prt-at-code")
	      		                    			,gisAssetsCd: $(this).data("assets-cd")
	      		                    			,gisAssetsSubCd: $(this).data("assets-sub-cd")
	      		                    			,gisPrtFcltyCd: $(this).data("prt-fclty-cd")
	      		                    			,gisPrtFcltySeq: $(this).data("prt-fclty-seq")
	      		                    			,gisPrtFcltySe: $(this).data("prt-fclty-se")
	      		                    			});
	        						  });
				            EMD.popup.prtFcltyInfo.updateSize();
							$('.prtFcltyInfo td > img').load(function(e){
								var title = $("h2", EMD.popup.prtFcltyInfo.contentDiv);
								var t = $("table.prtFcltyInfo", EMD.popup.prtFcltyInfo.contentDiv);
					            EMD.popup.prtFcltyInfo.updateSize();
							});
						}
				});
			});

			EMD.userLayer.fcltyMechMarker.events.register('featureunselected', null, function(e) {
				if(EMD.popup.prtFcltyInfo!=null) {
					EMD.map.removePopup(EMD.popup.prtFcltyInfo);
					EMD.popup.prtFcltyInfo=null;
				}
			});

			// 정보통신 시설
			EMD.userLayer.fcltyInfoTeleMarker = new OpenLayers.Layer.Vector( "정보통신 시설", {
		 		minScale: 5000
			} );

			EMD.userLayer.fcltyInfoTeleMarker.events.register('featureselected', null, function(e) {
					$.ajax({
					url: EMD.context_root+"/maps/fclty/gamFcltyInfo.do",
					type: 'POST',
					dataType: 'json',
					global: false,
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	// contentType: "text/plain; charset=UTF-8",
					data: [
					       { name: 'gisAssetsPrtAtCode', value:e.feature.attributes.gisAssetsPrtAtCode},
					       { name: 'gisAssetsCd', value:e.feature.attributes.gisAssetsCd},
					       { name: 'gisAssetsSubCd', value:e.feature.attributes.gisAssetsSubCd},
					       { name: 'gisPrtFcltyCd', value:e.feature.attributes.gisPrtFcltyCd},
					       { name: 'gisPrtFcltySeq', value:e.feature.attributes.gisPrtFcltySeq},
					       { name: 'gisPrtFcltySe', value:e.feature.attributes.gisPrtFcltySe}
					       ]
					}).done(function(data) {
						if(data.resultCode==0) {	// if no error
							var htmlInfo = "<div class='prtFcltyInfo'><h2>정보통신시설정보</h2><table class='prtFcltyInfo'><tbody>";
							var fclty = data.result;

							if(fclty==null) alert('선택한 시설의 정보가 없습니다.');

							htmlInfo+="<tr><th>자산코드</th><td>"+fclty.gisAssetsCd+"-"+fclty.gisAssetsSubCd+"</td></tr>";
							htmlInfo+="<tr><th>시설코드</th><td>"+fclty.gisPrtFcltyCd+"-"+fclty.gisPrtFcltySeq+"</td></tr>";
							if(fclty.prtFcltyNm!=null) htmlInfo+="<tr><th>시설명</th><td>"+fclty.prtFcltyNm+"</td></tr>";
							if(fclty.gisAssetsLocplc!=null) {
								var lnm = fclty.gisAssetsLnm+(fclty.gisAssetsLnmSub!=null&&fclty.gisAssetsLnmSub.length>0)?'-'+fclty.gisAssetsLnmSub:'';
								htmlInfo+="<tr><th>소재지</th><td>"+fclty.gisAssetsLocplc+' '+lnm+"</td></tr>";
							}
							if(fclty.filenmPhysicl!=null) {
								htmlInfo+="<tr><th>시설사진</th><td><img src='"+EMD.context_root+"/cmm/getPfImage.do?physicalFileNm="+fclty.filenmPhysicl+"' style='width:300px;' /></td></tr>";
							}
							// 권한에 따른 기능 추가
							htmlInfo+="<tr><td colSpan='2' style='align: right;'>";
		                	if(EMD.popupMenu['fcltyInfoTechPopMngt']!=undefined) {
		                		htmlInfo += "<td><button id='btnPrtfcltyMngt' data-role='frmwrkButton' data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode
		                			+"' data-assets-cd='"+fclty.gisAssetsCd
		                			+"' data-assets-sub-cd='"+fclty.gisAssetsSubCd
		                			+"' data-prt-fclty-cd='"+fclty.gisPrtFcltyCd
		                			+"' data-prt-fclty-seq='"+fclty.gisPrtFcltySeq
		                			+"' data-prt-fclty-se='"+fclty.gisPrtFcltySe
		                			+"' >시설관리</button></td>";
		                	}
		                	if(EMD.popupMenu['fcltyInfoTechPopInqire']!=undefined) {
			                		htmlInfo += "<td><button id='btnPrtfcltyInqire' data-role='frmwrkButton' data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode
	                			+"' data-assets-cd='"+fclty.gisAssetsCd
	                			+"' data-assets-sub-cd='"+fclty.gisAssetsSubCd
	                			+"' data-prt-fclty-cd='"+fclty.gisPrtFcltyCd
	                			+"' data-prt-fclty-seq='"+fclty.gisPrtFcltySeq
	                			+"' data-prt-fclty-se='"+fclty.gisPrtFcltySe
	                			+"' >시설조회</button></td>";
		                	}
							htmlInfo+="</tr>";

							htmlInfo+="</tbody></table>";
							if(EMD.popup.prtFcltyInfo!=null) {
								EMD.map.removePopup(EMD.popup.prtFcltyInfo);
								EMD.popup.prtFcltyInfo=null;
							}
							if(fclty.loCrdnt==null || fclty.laCrdnt==null) {
								if(fclty.lat!=null && fclty.lng!=null) {
									var latLng = new OpenLayers.LonLat(fclty.lng, fclty.lat);
									var proj = new OpenLayers.Projection('EPSG:4326');
									var newPos= latLng.transform(proj, new OpenLayers.Projection('EPSG:900913'));
									fclty.loCrdnt=newPos.lon;
									fclty.laCrdnt=newPos.lat;
								}
							}
							EMD.popup.prtFcltyInfo = new OpenLayers.Popup.FramedCloud(
					                "정보통신시설정보",
					                new OpenLayers.LonLat(fclty.loCrdnt,fclty.laCrdnt),
					                new OpenLayers.Size(250,150),
					                htmlInfo,
					                null,
					                true
					            );
				            EMD.map.addPopup(EMD.popup.prtFcltyInfo);
				            $("#btnPrtfcltyMngt").button(
	        						  { icons: {
	        							  primary: 'ui-icon-wrench'
	        						  },
	        						  text : true
	        						  }).click(function(event) {
	        							  var title="";
	        							  var url="";
        								  title ='정보통신 시설 관리';
        								  url=EMD.popupMenu['fcltyInfoTechPopMngt'];
	      		                    	EMD.util.create_window('', title, url, null, {
	      		                    		action: "prtFcltyMngt"
	      		                    			,gisPrtAtCode: $(this).data("assets-prt-at-code")
	      		                    			,gisAssetsCd: $(this).data("assets-cd")
	      		                    			,gisAssetsSubCd: $(this).data("assets-sub-cd")
	      		                    			,gisPrtFcltyCd: $(this).data("prt-fclty-cd")
	      		                    			,gisPrtFcltySeq: $(this).data("prt-fclty-seq")
	      		                    			,gisPrtFcltySe: $(this).data("prt-fclty-se")
	      		                    			});
	        						  });
				            $("#btnPrtfcltyInqire").button(
	        						  { icons: {
	        							  primary: 'ui-icon-folder-open'
	        						  },
	        						  text : true
	        						  }).click(function(event) {
	        							  var title="";
	        							  var url="";
        								  title ='정보통신 시설 조회';
        								  url=EMD.popupMenu['fcltyInfoTechPopInqire'];
	      		                    	EMD.util.create_window('', title, url, null, {
	      		                    		action: "prtFcltyMngt"
	      		                    			,gisPrtAtCode: $(this).data("assets-prt-at-code")
	      		                    			,gisAssetsCd: $(this).data("assets-cd")
	      		                    			,gisAssetsSubCd: $(this).data("assets-sub-cd")
	      		                    			,gisPrtFcltyCd: $(this).data("prt-fclty-cd")
	      		                    			,gisPrtFcltySeq: $(this).data("prt-fclty-seq")
	      		                    			,gisPrtFcltySe: $(this).data("prt-fclty-se")
	      		                    			});
	        						  });
				            EMD.popup.prtFcltyInfo.updateSize();
							$('.prtFcltyInfo td > img').load(function(e){
								var title = $("h2", EMD.popup.prtFcltyInfo.contentDiv);
								var t = $("table.prtFcltyInfo", EMD.popup.prtFcltyInfo.contentDiv);
					            EMD.popup.prtFcltyInfo.updateSize();
							});
						}
				});
			});

			EMD.userLayer.fcltyInfoTeleMarker.events.register('featureunselected', null, function(e) {
				if(EMD.popup.prtFcltyInfo!=null) {
					EMD.map.removePopup(EMD.popup.prtFcltyInfo);
					EMD.popup.prtFcltyInfo=null;
				}
			});

			// 전기 시설
			EMD.userLayer.fcltyElecPowerMarker = new OpenLayers.Layer.Vector( "전기 시설", {
		 		minScale: 5000
			} );

			EMD.userLayer.fcltyElecPowerMarker.events.register('featureselected', null, function(e) {
					$.ajax({
					url: EMD.context_root+"/maps/fclty/gamFcltyInfo.do",
					type: 'POST',
					dataType: 'json',
					global: false,
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	// contentType: "text/plain; charset=UTF-8",
					data: [
					       { name: 'gisAssetsPrtAtCode', value:e.feature.attributes.gisAssetsPrtAtCode},
					       { name: 'gisAssetsCd', value:e.feature.attributes.gisAssetsCd},
					       { name: 'gisAssetsSubCd', value:e.feature.attributes.gisAssetsSubCd},
					       { name: 'gisPrtFcltyCd', value:e.feature.attributes.gisPrtFcltyCd},
					       { name: 'gisPrtFcltySeq', value:e.feature.attributes.gisPrtFcltySeq},
					       { name: 'gisPrtFcltySe', value:e.feature.attributes.gisPrtFcltySe}
					       ]
					}).done(function(data) {
						if(data.resultCode==0) {	// if no error
							var htmlInfo = "<div class='prtFcltyInfo'><h2>전기시설정보</h2><table class='prtFcltyInfo'><tbody>";
							var fclty = data.result;

							if(fclty==null) alert('선택한 시설의 정보가 없습니다.');

							htmlInfo+="<tr><th>자산코드</th><td>"+fclty.gisAssetsCd+"-"+fclty.gisAssetsSubCd+"</td></tr>";
							htmlInfo+="<tr><th>시설코드</th><td>"+fclty.gisPrtFcltyCd+"-"+fclty.gisPrtFcltySeq+"</td></tr>";
							if(fclty.prtFcltyNm!=null) htmlInfo+="<tr><th>시설명</th><td>"+fclty.prtFcltyNm+"</td></tr>";
							if(fclty.gisAssetsLocplc!=null) {
								var lnm = fclty.gisAssetsLnm+(fclty.gisAssetsLnmSub!=null&&fclty.gisAssetsLnmSub.length>0)?'-'+fclty.gisAssetsLnmSub:'';
								htmlInfo+="<tr><th>소재지</th><td>"+fclty.gisAssetsLocplc+' '+lnm+"</td></tr>";
							}
							if(fclty.filenmPhysicl!=null) {
								htmlInfo+="<tr><th>시설사진</th><td><img src='"+EMD.context_root+"/cmm/getPfImage.do?physicalFileNm="+fclty.filenmPhysicl+"' style='width:300px;' /></td></tr>";
							}
							// 권한에 따른 기능 추가
							htmlInfo+="<tr><td colSpan='2' style='align: right;'>";
		                	if(EMD.popupMenu['fcltyElecPopMngt']!=undefined) {
		                		htmlInfo += "<td><button id='btnPrtfcltyMngt' data-role='frmwrkButton' data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode
		                			+"' data-assets-cd='"+fclty.gisAssetsCd
		                			+"' data-assets-sub-cd='"+fclty.gisAssetsSubCd
		                			+"' data-prt-fclty-cd='"+fclty.gisPrtFcltyCd
		                			+"' data-prt-fclty-seq='"+fclty.gisPrtFcltySeq
		                			+"' data-prt-fclty-se='"+fclty.gisPrtFcltySe
		                			+"' >시설관리</button></td>";
		                	}
		                	if(EMD.popupMenu['fcltyElecPopInqire']!=undefined) {
			                		htmlInfo += "<td><button id='btnPrtfcltyInqire' data-role='frmwrkButton' data-assets-prt-at-code='"+fclty.gisAssetsPrtAtCode
	                			+"' data-assets-cd='"+fclty.gisAssetsCd
	                			+"' data-assets-sub-cd='"+fclty.gisAssetsSubCd
	                			+"' data-prt-fclty-cd='"+fclty.gisPrtFcltyCd
	                			+"' data-prt-fclty-seq='"+fclty.gisPrtFcltySeq
	                			+"' data-prt-fclty-se='"+fclty.gisPrtFcltySe
	                			+"' >시설조회</button></td>";
		                	}
							htmlInfo+="</tr>";

							htmlInfo+="</tbody></table>";
							if(EMD.popup.prtFcltyInfo!=null) {
								EMD.map.removePopup(EMD.popup.prtFcltyInfo);
								EMD.popup.prtFcltyInfo=null;
							}
							if(fclty.loCrdnt==null || fclty.laCrdnt==null) {
								if(fclty.lat!=null && fclty.lng!=null) {
									var latLng = new OpenLayers.LonLat(fclty.lng, fclty.lat);
									var proj = new OpenLayers.Projection('EPSG:4326');
									var newPos= latLng.transform(proj, new OpenLayers.Projection('EPSG:900913'));
									fclty.loCrdnt=newPos.lon;
									fclty.laCrdnt=newPos.lat;
								}
							}
							EMD.popup.prtFcltyInfo = new OpenLayers.Popup.FramedCloud(
					                "정보통신시설정보",
					                new OpenLayers.LonLat(fclty.loCrdnt,fclty.laCrdnt),
					                new OpenLayers.Size(250,150),
					                htmlInfo,
					                null,
					                true
					            );
				            EMD.map.addPopup(EMD.popup.prtFcltyInfo);
				            $("#btnPrtfcltyMngt").button(
	        						  { icons: {
	        							  primary: 'ui-icon-wrench'
	        						  },
	        						  text : true
	        						  }).click(function(event) {
	        							  var title="";
	        							  var url="";
        								  title ='전기 시설 관리';
        								  url=EMD.popupMenu['fcltyElecPopMngt'];
	      		                    	EMD.util.create_window('', title, url, null, {
	      		                    		action: "prtFcltyMngt"
	      		                    			,gisPrtAtCode: $(this).data("assets-prt-at-code")
	      		                    			,gisAssetsCd: $(this).data("assets-cd")
	      		                    			,gisAssetsSubCd: $(this).data("assets-sub-cd")
	      		                    			,gisPrtFcltyCd: $(this).data("prt-fclty-cd")
	      		                    			,gisPrtFcltySeq: $(this).data("prt-fclty-seq")
	      		                    			,gisPrtFcltySe: $(this).data("prt-fclty-se")
	      		                    			});
	        						  });
				            $("#btnPrtfcltyInqire").button(
	        						  { icons: {
	        							  primary: 'ui-icon-folder-open'
	        						  },
	        						  text : true
	        						  }).click(function(event) {
	        							  var title="";
	        							  var url="";
        								  title ='전기 시설 조회';
        								  url=EMD.popupMenu['fcltyElecPopInqire'];
	      		                    	EMD.util.create_window('', title, url, null, {
	      		                    		action: "prtFcltyMngt"
	      		                    			,gisPrtAtCode: $(this).data("assets-prt-at-code")
	      		                    			,gisAssetsCd: $(this).data("assets-cd")
	      		                    			,gisAssetsSubCd: $(this).data("assets-sub-cd")
	      		                    			,gisPrtFcltyCd: $(this).data("prt-fclty-cd")
	      		                    			,gisPrtFcltySeq: $(this).data("prt-fclty-seq")
	      		                    			,gisPrtFcltySe: $(this).data("prt-fclty-se")
	      		                    			});
	        						  });
				            EMD.popup.prtFcltyInfo.updateSize();
							$('.prtFcltyInfo td > img').load(function(e){
								var title = $("h2", EMD.popup.prtFcltyInfo.contentDiv);
								var t = $("table.prtFcltyInfo", EMD.popup.prtFcltyInfo.contentDiv);
					            EMD.popup.prtFcltyInfo.updateSize();
							});
						}
				});
			});

			EMD.userLayer.fcltyElecPowerMarker.events.register('featureunselected', null, function(e) {
				if(EMD.popup.prtFcltyInfo!=null) {
					EMD.map.removePopup(EMD.popup.prtFcltyInfo);
					EMD.popup.prtFcltyInfo=null;
				}
			});

			if($DEBUG) {
			 	EMD.selectControl = new OpenLayers.Control.SelectFeature(
						[EMD.lotarea, EMD.userLayer.gisAssetsCd, EMD.userLayer.locArea, EMD.userLayer.assetStats
// , EMD.userLayer.prtFclty
						 , EMD.userLayer.fcltyConstMarker, EMD.userLayer.fcltySocMarker, EMD.userLayer.fcltyMechMarker, EMD.userLayer.fcltyInfoTeleMarker, EMD.userLayer.fcltyElecPowerMarker],
						{
						    clickout: true, toggle: true,
						    multiple: true, hover: false,
// toggleKey: "ctrlKey", // ctrl key removes from selection
						    multipleKey: "shiftKey" // shift key adds to
													// selection
						}
					);
			}
			else {
				EMD.selectControl = new OpenLayers.Control.SelectFeature(
						[EMD.userLayer.landCode, EMD.userLayer.gisAssetsCd, EMD.userLayer.assetsRent
						 , EMD.userLayer.gisArchFclty, EMD.userLayer.gisCivilFclty, EMD.userLayer.gisMechFclty, EMD.userLayer.gisElecFclty, EMD.userLayer.gisTeleFclty
						 , EMD.userLayer.locArea, EMD.userLayer.assetStats, EMD.userLayer.fcltyConstMarker, EMD.userLayer.fcltySocMarker, EMD.userLayer.fcltyMechMarker, EMD.userLayer.fcltyInfoTeleMarker, EMD.userLayer.fcltyElecPowerMarker],
						{
						    clickout: true, toggle: true,
						    multiple: true, hover: false,
// toggleKey: "ctrlKey", // ctrl key removes from selection
						    multipleKey: "shiftKey" // shift key adds to
													// selection
						}
					);
			}

		 	EMD.selectControl.handlers.feature.stopDown = false;

// EMD.selectInfoControl = new OpenLayers.Control.SelectFeature(
// [EMD.userLayer.fcltyMarker],
// {
// clickout: true, toggle: true,
// multiple: false, hover: false,
// }
// );

// EMD.selectInfoControl.handlers.feature.stopDown = false;


// EMD.dragFeature = new OpenLayers.Control.DragFeature(layer, {
// onStart: function(feature, pixel){
// EMD.selectControl.clickFeature(feature);
// }
// });
// map.addControl(dragFeature);
// EMD.dragFeature.activate();


// EMD.userLayer.prtFclty.events.register('featureselected', null, function(e) {
// var feature = e.feature;
// var popup = new OpenLayers.Popup.FramedCloud("popup",
// OpenLayers.LonLat.fromString(feature.geometry.toShortString()),
// null,
// "<div style='font-size:.8em'>ID: " + feature.id +"<br>시설코드: " +
// feature.attributes.fcltyCd+"<br> 시설명 : "+feature.attributes.name+"</div>",
// null,
// true
// );
// feature.popup = popup;
// EMD.map.addPopup(popup);
// });
// EMD.userLayer.prtFclty.events.register('featureunselected', null, function(e)
// {
// var feature = e.feature;
// map.removePopup(feature.popup);
// feature.popup.destroy();
// feature.popup = null;
// });
			EMD.info = new OpenLayers.Control.WMSGetFeatureInfo({
			    url: 'http://demo.opengeo.org/geoserver/wms',
			    title: '항만시설 속성',
			    queryVisible: true,
			    eventListeners: {
			        getfeatureinfo: function(event) {
			            map.addPopup(new OpenLayers.Popup.FramedCloud(
			                "항만시설 속성",
			                map.getLonLatFromPixel(event.xy),
			                new OpenLayers.Size(250,150),
			                event.text,
			                null,
			                true
			            ));
			        }
			    }
			});

// EMD.wfs = new OpenLayers.Layer.Vector("Vector Layer");


			 	EMD.wfs = new OpenLayers.Layer.Vector("Edit Features", {
			 		projection: new OpenLayers.Projection("EPSG:5186"),
			 		displayInLayerSwitcher: false
			 	});
/*
 * EMD.wfs = new OpenLayers.Layer.Vector("Edit Features",{ strategies: [new
 * OpenLayers.Strategy.BBOX(), EMD.saveStrategy], projection: new
 * OpenLayers.Projection("EPSG:5186"), protocol:new OpenLayers.Protocol.WFS({
 * version: "1.1.0", service: "WFS", url: EMD.gis_engine_url, featureNS:
 * "http://geogate.g-inno.com/dataserver/datahouse", featurePrefix: "datahouse",
 * featureType: "ASSETS_CD_F_A", srsName: "EPSG:5186", geometryName:
 * "G2_SPATIAL" }), styleMap: new OpenLayers.StyleMap({ 'default':{ strokeColor:
 * "#000000", strokeOpacity: 1, strokeWidth: 1, fillColor: "#303294",
 * fillOpacity: 0.5, pointRadius: 6, pointerEvents: "visiblePainted", fontColor:
 * "black", fontSize: "12px", fontFamily: "Courier New, monospace", fontWeight:
 * "bold", labelAlign: "cm", labelXOffset: "2px", labelYOffset: "2px",
 * labelOutlineColor: "white", labelOutlineWidth: 3 }, 'select': { strokeColor:
 * "#00FF00", strokeOpacity: 1, strokeWidth: 3, fillColor: "#303294",
 * fillOpacity: 1, pointRadius: 6, pointerEvents: "visiblePainted", // label
 * with \n linebreaks fontSize: "12px", fontFamily: "Courier New, monospace",
 * fontWeight: "bold", labelAlign: "cm", labelXOffset: "2px", labelYOffset:
 * "2px", labelOutlineColor: "black", labelOutlineWidth: 3 } }) });
 */

/*
 * EMD.draw = new OpenLayers.Control.DrawFeature( EMD.wfs,
 * OpenLayers.Handler.Polygon, { title: "추가", displayClass:
 * "olControlDrawFeaturePolygon", multi: true } );
 *
 * EMD.edit = new OpenLayers.Control.ModifyFeature(EMD.wfs, { title: "수정",
 * displayClass: "olControlModifyFeature" });
 *
 * EMD.del = new DeleteFeature(EMD.wfs, {title: "삭제"});
 *
 * EMD.save = new OpenLayers.Control.Button({ title: "저장", trigger: function() {
 * if(EMD.edit.feature) { EMD.edit.selectControl.unselectAll(); } //
 * EMD.gis.modifyAssetCdFeature(EMD.draw._mode.param, EMD.edit.feature);
 * EMD.saveStrategy.save(); EMD.map.removeControl(EMD.panel); }, displayClass:
 * "olControlSaveFeatures" });
 */
	    		EMD.controls.measureDist = new OpenLayers.Control.Button({
	    			title: "거리측정",
	    			trigger: function() {
	    				var mapControls = EMD.measureControls;
	    				for(var i in mapControls) {
// mapControls[i].deactivate();
	    					if(mapControls[i].id=="measureDist") {
	    						if(!mapControls[i].active) mapControls[i].activate();
	    						else mapControls[i].deactivate();
	    					}
	    					else mapControls[i].deactivate();
	    				}
	    			},
	    			type: OpenLayers.Control.TYPE_TOGGLE,
	    			displayClass: "olControlMeasureDist",
	    			eventListeners: {
		    			activate: function() {
		    				var mapControls = EMD.measureControls;
		    				for(var i in mapControls) {
// mapControls[i].deactivate();
		    					if(mapControls[i].id=="measureDist") {
		    						mapControls[i].activate();
		    					}
// else mapControls[i].deactivate();
		    				}
		    				EMD.controls.measureArea.deactivate();
		    			},
		    			deactivate: function() {
		    				var mapControls = EMD.measureControls;
		    				for(var i in mapControls) {
		    					if(mapControls[i].id=="measureDist") {
		    						mapControls[i].deactivate();
		    					}
		    				}
		    			},
	    			}
	    		});

	    		EMD.controls.measureArea = new OpenLayers.Control.Button({
	    			title: "면적측정",
	    			trigger: function() {
	    				var mapControls = EMD.measureControls;
	    				for(var i in mapControls) {
// mapControls[i].deactivate();
	    					if(mapControls[i].id=="measureArea") {
	    						if(!mapControls[i].active) mapControls[i].activate();
	    						else mapControls[i].deactivate();
	    					}
	    					else mapControls[i].deactivate();
	    				}
	    			},
	    			type: OpenLayers.Control.TYPE_TOGGLE,
	    			displayClass: "olControlMeasureArea",
	    			eventListeners: {
		    			activate: function() {
		    				var mapControls = EMD.measureControls;
		    				for(var i in mapControls) {
// mapControls[i].deactivate();
		    					if(mapControls[i].id=="measureArea") {
		    						mapControls[i].activate();
		    					}
// else mapControls[i].deactivate();
		    				}
		    				EMD.controls.measureDist.deactivate();
		    			},
		    			deactivate: function() {
		    				var mapControls = EMD.measureControls;
		    				for(var i in mapControls) {
		    					if(mapControls[i].id=="measureArea") {
		    						mapControls[i].deactivate();
		    					}
		    				}
		    			},
	    			}
	    		});
	    		EMD.measureControls=[
	    		                     new OpenLayers.Control.DynamicMeasure(
		    				 OpenLayers.Handler.PathMeasureCustom, {
		    					 id : "measureDist",
		    					 persist : true,
		    					 handlerOptions : {
		    						 layerOptions : {
		    							 styleMap : styleMap
		    						 }
		    					 }
		    				 }),
		    				 new OpenLayers.Control.DynamicMeasure(
				    				 OpenLayers.Handler.PolygonMeasureCustom, {
				    					 id : "measureArea",
				    					 persist : true,
				    					 handlerOptions : {
				    						 layerOptions : {
				    							 styleMap : styleMap
				    						 }
				    					 }
				    				 })
	    		                     ];

	    		EMD.measurePanel = new OpenLayers.Control.Panel({
	    			displayClass: 'mapMeasureToolBar'
// allowDepress: true
	    		});

	    		EMD.measurePanel.addControls([EMD.controls.measureArea, EMD.controls.measureDist]);

// renderers: renderer

// EMD.wfs = new OpenLayers.Layer.Vector("User Drawing Layer"
// , {
// strategies: [new OpenLayers.Strategy.BBOX(), saveStrategy],
// projection: new OpenLayers.Projection("EPSG:4326")
// protocol: new OpenLayers.Protocol.WFS({
// version: "1.1.0",
// srsName: "EPSG:4326",
// url: "http://192.168.220.128:8880/geonuris/wfs",
// featureNS : "http://opengeo.org",
// featureType: "restricted",
// geometryName: "the_geom",
// schema:
// "http://demo.opengeo.org/geoserver/wfs/DescribeFeatureType?version=1.1.0&typename=og:restricted"
// })
// });

// geonuris.setVisibility(true);
// EMD.wfs.setVisibility(true);
		    EMD.userLayer.custLand.setVisibility(true);
		    EMD.userLayer.wharfNm.setVisibility(true);
		    EMD.userLayer.fcltyNm.setVisibility(true);
		    EMD.userLayer.landCode.setVisibility(false);
		    EMD.userLayer.gisAssetsCd.setVisibility(true);
		    EMD.userLayer.gisArchFclty.setVisibility(false);
		    EMD.userLayer.gisCivilFclty.setVisibility(false);
		    EMD.userLayer.gisMechFclty.setVisibility(false);
		    EMD.userLayer.gisElecFclty.setVisibility(false);
		    EMD.userLayer.gisTeleFclty.setVisibility(false);
		    EMD.userLayer.locArea.setVisibility(true);

		    EMD.userLayer.assetsRent.setVisibility(true);

		    EMD.userLayer.assetStats.setVisibility(true);
// EMD.userLayer.prtFclty.setVisibility(true);
            EMD.userLayer.fcltyConstMarker.setVisibility(true);
            EMD.userLayer.fcltySocMarker.setVisibility(true);
            EMD.userLayer.fcltyMechMarker.setVisibility(true);
            EMD.userLayer.fcltyInfoTeleMarker.setVisibility(true);
            EMD.userLayer.fcltyElecPowerMarker.setVisibility(true);

// EMD.legendWin = $('#legendPanel');
            EMD.legendWin = new dhtmlXTabBar({
            	parent: "legendPanel",
            	skin: "dhx_skyblue",
            	mode: "top",
            	align: "left",
            	close_button: false,
            	content_zone: true,
            	onload: function() {

            	},
            	arrows_mode: "auto"
            });

            $('#legendPanel').width(200);
            $('#legendPanel').height(300);
            $('#legendPanel').hide();
            EMD.legendWin.setSizes();
            EMD.legendWin._counts=0;
            // 왠일인지 이벤트가 동작 안함.
// EMD.legendWin.attachEvent("onTabClose", function(id){
// if(EMD.legendWin.getNumberOfTabs()==0) {
// $('#legendPanel').hide();
// }
// return true;
// });

			EMD.userLayer.gisArchFclty.events.register('visibilitychanged', null, function(e) {
				var legendTab = EMD.legendWin.tabs("archFclty");
				if(e.object.visibility) {
					$('#legendPanel').show();
					if(legendTab==undefined || legendTab==null) {
						EMD.legendWin.addTab("archFclty", "건축시설 범례", null, null, true);
						legendTab = EMD.gis.addLegendLayer(e.object, "archFclty");
						EMD.legendWin._counts++;
					}
					legendTab.show(true);
				}
				else {
					if(legendTab!=undefined && legendTab!=null) {
						legendTab.close();
						EMD.legendWin._counts--;
						if(EMD.legendWin._counts<=0) {
							EMD.legendWin._counts=0;
							$('#legendPanel').hide();
						}
					}
				}
			});
			EMD.userLayer.gisMechFclty.events.register('visibilitychanged', null, function(e) {
				var legendTab = EMD.legendWin.tabs("mechGroup");
				if(e.object.visibility) {
					$('#legendPanel').show();
					if(legendTab==undefined || legendTab==null) {
						EMD.legendWin.addTab("mechGroup", "기계시설 범례", null, null, true);
						legendTab = EMD.gis.addLegendLayer(e.object, "mechGroup");
						EMD.legendWin._counts++;
					}
					legendTab.show(true);
				}
				else {
					if(legendTab!=undefined && legendTab!=null) {
						legendTab.close();
						EMD.legendWin._counts--;
						if(EMD.legendWin._counts<=0) {
							EMD.legendWin._counts=0;
							$('#legendPanel').hide();
						}
					}
				}
			});
			EMD.userLayer.gisElecFclty.events.register('visibilitychanged', null, function(e) {
				var legendTab = EMD.legendWin.tabs("elecGroup");
				if(e.object.visibility) {
					$('#legendPanel').show();
					if(legendTab==undefined || legendTab==null) {
						EMD.legendWin.addTab("elecGroup", "전기시설 범례", null, null, true);
						legendTab = EMD.gis.addLegendLayer(e.object, "elecGroup");
						EMD.legendWin._counts++;
					}
					legendTab.show(true);
				}
				else {
					if(legendTab!=undefined && legendTab!=null) {
						legendTab.close();
						EMD.legendWin._counts--;
						if(EMD.legendWin._counts<=0) {
							EMD.legendWin._counts=0;
							$('#legendPanel').hide();
						}
					}
				}
			});
			EMD.userLayer.gisTeleFclty.events.register('visibilitychanged', null, function(e) {
				var legendTab = EMD.legendWin.tabs("infoGroup");
				if(e.object.visibility) {
					$('#legendPanel').show();
					if(legendTab==undefined || legendTab==null) {
						EMD.legendWin.addTab("infoGroup", "정보통신시설 범례", null, null, true);
						legendTab = EMD.gis.addLegendLayer(e.object, "infoGroup");
						EMD.legendWin._counts++;
					}
					legendTab.show(true);
				}
				else {
					if(legendTab!=undefined && legendTab!=null) {
						legendTab.close();
						EMD.legendWin._counts--;
						if(EMD.legendWin._counts<=0) {
							EMD.legendWin._counts=0;
							$('#legendPanel').hide();
						}
					}
				}
			});
			EMD.userLayer.gisCivilFclty.events.register('visibilitychanged', null, function(e) {
				var legendTab = EMD.legendWin.tabs("civilGroup");
				if(e.object.visibility) {
					$('#legendPanel').show();
					if(legendTab==undefined || legendTab==null) {
						EMD.legendWin.addTab("civilGroup", "토목시설 범례", null, null, true);
						legendTab = EMD.gis.addLegendLayer(e.object, "civilGroup");
						EMD.legendWin._counts++;
					}
					legendTab.show(true);
				}
				else {
					if(legendTab!=undefined && legendTab!=null) {
						legendTab.close();
						EMD.legendWin._counts--;
						if(EMD.legendWin._counts<=0) {
							EMD.legendWin._counts=0;
							$('#legendPanel').hide();
						}
					}
				}
			});

			if(EMD.userinfo.mngFcltyCd=="A") {
				EMD.userLayer.gisArchFclty.setVisibility(true);
			}
			if(EMD.userinfo.mngFcltyCd=="M") {
    		  EMD.userLayer.gisMechFclty.setVisibility(true);
			}
			if(EMD.userinfo.mngFcltyCd=="E") {
    		  EMD.userLayer.gisElecFclty.setVisibility(true);
			}
			if(EMD.userinfo.mngFcltyCd=="I") {
    		  EMD.userLayer.gisTeleFclty.setVisibility(true);
			}
			if(EMD.userinfo.mngFcltyCd=="C") {
    		  EMD.userLayer.gisCivilFclty.setVisibility(true);
			}
// EMD.gyhmap.setVisibility(true);

	        if($DEBUG) {
		        EMD.lotarea.setVisibility(true);
		        var layers=[basemap
	        	       , EMD.userLayer.custLand
	        	       , EMD.userLayer.wharfNm
	        	       , EMD.userLayer.fcltyNm
	                   , EMD.lotarea
	                   , EMD.userLayer.landCode
	                   , EMD.userLayer.gisAssetsCd
	                   , EMD.userLayer.gisArchFclty
	                   , EMD.userLayer.gisCivilFclty
	                   , EMD.userLayer.gisMechFclty
	                   , EMD.userLayer.gisElecFclty
	                   , EMD.userLayer.gisTeleFclty
	                   , EMD.userLayer.assetsRent
	                   , EMD.userLayer.assetStats
//	                   , EMD.wfs
//	                   , EMD.userLayer.fcltyConstMarker
//	                   , EMD.userLayer.fcltySocMarker
//	                   , EMD.userLayer.fcltyMechMarker
//	                   , EMD.userLayer.fcltyInfoTeleMarker
//	                   , EMD.userLayer.fcltyElecPowerMarker
	                   ];
		        console.log(layers);
				EMD.map.addLayers(layers);
	        }
	        else {
	        	var layers=[basemap
	        	       , EMD.userLayer.custLand
	        	       , EMD.userLayer.wharfNm
	        	       , EMD.userLayer.fcltyNm
	                   , EMD.userLayer.landCode
	                   , EMD.userLayer.gisAssetsCd
	                   , EMD.userLayer.gisArchFclty
	                   , EMD.userLayer.gisCivilFclty
	                   , EMD.userLayer.gisMechFclty
	                   , EMD.userLayer.gisElecFclty
	                   , EMD.userLayer.gisTeleFclty
	                   , EMD.userLayer.assetsRent
	                   , EMD.userLayer.assetStats
// , EMD.userLayer.assetsRentDetail
// , EMD.wfs
	                   ];

// if(EMD.layerDisplay['assetRentStats']!=undefined)
// layers[layers.length]=EMD.userLayer.assetRentStats;
// if(EMD.layerDisplay['gnrlRentStats']!=undefined)
// layers[layers.length]=EMD.userLayer.gnrlRentStats;
// if(EMD.layerDisplay['cntrRentStats']!=undefined)
// layers[layers.length]=EMD.userLayer.cntrRentStats;
// if(EMD.layerDisplay['htldRentStats']!=undefined)
// layers[layers.length]=EMD.userLayer.htldRentStats;
// if(EMD.layerDisplay['ccntRentStats']!=undefined)
// layers[layers.length]=EMD.userLayer.ccntRentStats;
// if(EMD.layerDisplay['trnpRentStats']!=undefined)
// layers[layers.length]=EMD.userLayer.trnpRentStats;

	        	/*
				 * 시설물 잠시 보류 업로드시 해제 할 것
				 * layers=layers.concat([EMD.userLayer.fcltyConstMarker ,
				 * EMD.userLayer.fcltySocMarker , EMD.userLayer.fcltyMechMarker ,
				 * EMD.userLayer.fcltyInfoTeleMarker ,
				 * EMD.userLayer.fcltyElecPowerMarker]);
				 */

	        	EMD.map.addLayers(layers);
// EMD.map.setLayerIndex(basemap, 350);

/*
 * EMD.strategies.assetsRent.setFilter(new OpenLayers.Filter.Logical({ type:
 * OpenLayers.Filter.Logical.AND, filters: [ new OpenLayers.Filter.Comparison({
 * type: OpenLayers.Filter.Comparison.EQUAL_TO, property: "QUAY_GROUP_CD",
 * value: 'P' }), new OpenLayers.Filter.Comparison({ type:
 * OpenLayers.Filter.Comparison.EQUAL_TO, property: "PRT_AT_CD", value: '' }),
 * new OpenLayers.Filter.Comparison({ type:
 * OpenLayers.Filter.Comparison.EQUAL_TO , property: "MNG_YEAR", value: '' }),
 * new OpenLayers.Filter.Comparison({ type:
 * OpenLayers.Filter.Comparison.EQUAL_TO, property: "MNG_NO", value: '' }), new
 * OpenLayers.Filter.Comparison({ type: OpenLayers.Filter.Comparison.EQUAL_TO,
 * property: "MNG_CNT", value: '' }) ] }) );
 */

// EMD.gis.assetRentStatsRedraw();
//
// EMD.gis.gnrlRentStatsRedraw();
//
// EMD.gis.cntrRentStatsRedraw();
//
// EMD.gis.htldRentStatsRedraw();
//
// EMD.gis.ccntRentStatsRedraw();
//
// EMD.gis.trnpRentStatsRedraw();

	        }

// archFilter.activate();
// mechFilter.activate();
// ITFilter.activate();
// elecFilter.activate();
// civilFilter.activate();
//
// archFilter.setFilter(new OpenLayers.Filter.Comparison({
// type: OpenLayers.Filter.Comparison.EQUAL_TO,
// property: "FCLTY_SE",
// value: "A"
// }));
// mechFilter.setFilter(new OpenLayers.Filter.Comparison({
// type: OpenLayers.Filter.Comparison.EQUAL_TO,
// property: "FCLTY_SE",
// value: "M"
// }));
// ITFilter.setFilter(new OpenLayers.Filter.Comparison({
// type: OpenLayers.Filter.Comparison.EQUAL_TO,
// property: "FCLTY_SE",
// value: "I"
// }));
// elecFilter.setFilter(new OpenLayers.Filter.Comparison({
// type: OpenLayers.Filter.Comparison.EQUAL_TO,
// property: "FCLTY_SE",
// value: "E"
// }));
// civilFilter.setFilter(new OpenLayers.Filter.Comparison({
// type: OpenLayers.Filter.Comparison.EQUAL_TO,
// property: "FCLTY_SE",
// value: "C"
// }));


// EMD.map.addControl(EMD.selectInfoControl);

// EMD.selectInfoControl.activate();

// EMD.info.activate();


// EMD.map.addControl(EMD.lotarea);

// EMD.lotarea.activate();

// EMD.map.setCenter(new OpenLayers.LonLat(14211461.37, 4151377.18), 5);
// EMD.map.setCenter(new OpenLayers.LonLat(126.644, 34.397), 5);

		    var layerSwitcer = new OpenLayers.Control.LayerSwitcher();

		    EMD.map.addControls(
		    		[
		    		 EMD.selectControl
		    		 ,layerSwitcer
		    		 ,new OpenLayers.Control.MousePosition({
		    			 prefix: '지도 좌표 : ',
		    			 separator: ' | ',
		    			 numDigits: 6,
		    			 displayProjection: new OpenLayers.Projection("EPSG:5186"),
		    		 })
		    		 ,new OpenLayers.Control.Scale()
		    		 ,EMD.measurePanel
		    		 ]
		    );
		    EMD.map.addControls(
		    		EMD.measureControls
		    		);

		    EMD.selectControl.activate();
		    layerSwitcer.dataLbl.innerText = "레이어 목록";

    		EMD.measurePanel.activate();

// EMD.map.setCenter(new OpenLayers.LonLat(34.397, 126.644), 6);

// EMD.map.setCenter(new OpenLayers.LonLat(14213430, 4147543), 13);

			EMD.map.events.register("featureover", EMD.map, function(e) {
// e.feature.renderIntent="select";
// e.feature.layer.drawFeature(e.feature);
// console.log("Map says: Pointer entered " + e.feature.id + " on " +
// e.feature.layer.name);
			});

			EMD.map.events.register("mousedown", EMD.map, function(e) {
// EMD.util.clear_active();
	            if(EMD.dragging) {
// var nevent = $.clone(e, true);
// $(EMD.drag_handle).trigger(nevent);
// $(EMD.drag_handle).trigger({type: e.type, altKey: e.altKey, clientX:
// e.clientX, clientY:e.clientY, ctrlKey: e.ctrlKey, detail: e.detail, keyCode:
// e.keyCode, layerX: e.layerX, layerY: e.layerY, metaKey:e.metaKey, offsetX:
// e.offsetX, offsetY:e.offsetY, pageX: e.pageX, pageY: e.pageY, screenX:
// e.screenX, screenY: e.screenY, shiftKey: e.shiftKey, webkitMovementX:
// e.webkitMovementX, webkitMovementY: e.webkitMovementY, x: e.x, y: e.y, xy:
// e.xy});
	            }
			});

			EMD.map.events.register("mousemove", EMD.map, function(e) {
	            if(EMD.dragging) {
	            	$(EMD.drag_handle).trigger({type: e.type, altKey: e.altKey, clientX: e.clientX, clientY:e.clientY, ctrlKey: e.ctrlKey, detail: e.detail, keyCode: e.keyCode, layerX: e.layerX, layerY: e.layerY, metaKey:e.metaKey, offsetX: e.offsetX, offsetY:e.offsetY, pageX: e.pageX, pageY: e.pageY, screenX: e.screenX, screenY: e.screenY, shiftKey: e.shiftKey, webkitMovementX: e.webkitMovementX, webkitMovementY: e.webkitMovementY, x: e.x, y: e.y, xy: e.xy});
	            }
			});

			EMD.map.events.register("mouseup", EMD.map, function(e) {
	            EMD.util.clear_active();
	            if(EMD.dragging) {
// var nevent = $.clone(e, true);
// $(EMD.drag_handle).trigger(nevent);
// $(EMD.drag_handle).trigger({type: e.type, altKey: e.altKey, clientX:
// e.clientX, clientY:e.clientY, ctrlKey: e.ctrlKey, detail: e.detail, keyCode:
// e.keyCode, layerX: e.layerX, layerY: e.layerY, metaKey:e.metaKey, offsetX:
// e.offsetX, offsetY:e.offsetY, pageX: e.pageX, pageY: e.pageY, screenX:
// e.screenX, screenY: e.screenY, shiftKey: e.shiftKey, webkitMovementX:
// e.webkitMovementX, webkitMovementY: e.webkitMovementY, x: e.x, y: e.y, xy:
// e.xy});
	            }
			});

			EMD.map.events.register("changelayer", EMD.map, function(e) {
				if(EMD.userLayer.gisElecFclty==e.layer) {
					if(e.layer.visibility) {
						if(e.layer['legendPanel']==null) {
/*
 * e.layer['legendPanel'] = new GeoExt.LegendPanel({ layerStore: e.layer,
 * renderTo: "legendPanel", border: false });
 */

// e.layer['legendPanel']=new OpenLayers.Control.Panel({
// displayClass: 'legendPanel'
// });
						}
// e.layer.legendPanel.activate();
					}
					else {
// if(e.layer.legendPanel!=null) {
// e.layer.legendPanel.deactivate();
// }
					}
				}
			});

// EMD.map.zoomToMaxExtent();

			// var center=new OpenLayers.LonLat(14211246,4145825); /// 14412822
			// 4068519
//			console.log('EMD.userCfg.startLon = '+EMD.userCfg.startLon);
//			console.log('EMD.userCfg.startLat = '+EMD.userCfg.startLat);
			var center=new OpenLayers.LonLat(EMD.userCfg.startLon,EMD.userCfg.startLat);

			// center=center.transform(new OpenLayers.Projection("EPSG:5186"),
			// new OpenLayers.Projection("EPSG:5181"));
			// console.log(center);
			var src_proj = new OpenLayers.Projection("EPSG:5186");
			var dest_proj = new OpenLayers.Projection("EPSG:900913");
			// center = Proj4js.transform("EPSG:5186", "EPSG:900913", center);
			var c = center.transform(src_proj, dest_proj);
			// console.log(c);
			// center=center.transform("EPSG:5186", "EPSG:900913");
			// console.log(center);
			//console.log('EMD.userCfg.startZoom = '+EMD.userCfg.startZoom);

		    EMD.map.setCenter(center, EMD.userCfg.startZoom);
/*
 * if($DEBUG==false) { // 시설물 정보를 읽어온다. $.ajax({ url:
 * EMD.context_root+"/maps/fclty/gamFcltyList.do", type: 'POST', dataType:
 * 'json', global: false, contentType: "application/x-www-form-urlencoded;
 * charset=UTF-8", // contentType: "text/plain; charset=UTF-8", data: []
 * }).done(function(data) { if(data.resultCode==0) { // if no error
 * $.each(data.resultList, function() { EMD.gis.drawPrtFclty(this); });
 * EMD.userLayer.fcltyConstMarker.redraw();
 * EMD.userLayer.fcltySocMarker.redraw();
 * EMD.userLayer.fcltyMechMarker.redraw();
 * EMD.userLayer.fcltyInfoTeleMarker.redraw();
 * EMD.userLayer.fcltyElecPowerMarker.redraw(); } }); }
 */

      },
      ui_init: function() {
// $('#sideMenu').sidr('open', 'sidr-main');
      },
      fileupload_init: function() {
    	    // Initialize the jQuery File Upload widget:
// if (browser.msie && browser.version <= 9.0) {
// // $('#fileupload').prop("action", "/upload/genericMulti.do");
// $('#fileupload').fileupload({
// // Uncomment the following to send cross-domain cookies:
// //xhrFields: {withCredentials: true},
// url: '/upload/genericMultiIE.do',
// autoUpload: false,
// });
//
// }
// else {
// $('#fileupload').prop("action", "/upload/genericMultiIE.do");
	    	    $('#fileupload').fileupload({
	    	        // Uncomment the following to send cross-domain cookies:
	    	        // xhrFields: {withCredentials: true},
	    	        url: EMD.context_root+'/upload/genericMulti.do',
	    	    	autoUpload: true,
	    	    });
// }


    	    // Enable iframe cross-domain access via redirect option:
    	    $('#fileupload').fileupload(
    	        'option',
    	        'redirect',
    	        window.location.href.replace(
    	            /\/[^\/]*$/,
    	            '/cors/result.html?%s'
    	        )
    	    );
            // Load existing files:
// $('#fileupload').addClass('fileupload-processing');
// $.ajax({
// // Uncomment the following to send cross-domain cookies:
// //xhrFields: {withCredentials: true},
// url: $('#fileupload').fileupload('option', 'url'),
// dataType: 'json',
// context: $('#fileupload')[0]
// }).always(function () {
// $(this).removeClass('fileupload-processing');
// }).done(function (result) {
// $(this).fileupload('option', 'done')
// .call(this, $.Event('done'), {result: result});
// });

    	    // 시설 사진
    	    $('#pfPhotoupload').fileupload({
    	        // Uncomment the following to send cross-domain cookies:
    	        // xhrFields: {withCredentials: true},
    	        url: EMD.context_root+'/upload/pfUploadMulti.do',
    	    	autoUpload: true,
    	    });

    	    $('#pfPhotoupload').fileupload(
        	        'option',
        	        'redirect',
        	        window.location.href.replace(
        	            /\/[^\/]*$/,
        	            '/cors/result.html?%s'
        	        )
        	    );

    	    // excel file
    	    $('#xlsfileupload').fileupload({
    	    	autoUpload: true,
    	    });


    	    // Enable iframe cross-domain access via redirect option:
    	    $('#xlsfileupload').fileupload(
    	        'option',
    	        'redirect',
    	        window.location.href.replace(
    	            /\/[^\/]*$/,
    	            '/cors/result.html?%s'
    	        )
    	    );
      },
      loadCompleteWindow: function() {
    	  var i, cv=[];
    	  var name="ygpa_popup_c";
		    var ca = document.cookie.split(';');
		    for(i=0; i<ca.length; i++) {
		        var c = ca[i];
		        while (c.charAt(0)==' ') c = c.substring(1);
		        if (c.indexOf(name) != -1) cv[cv.length]=c.substring(name.length+2,c.length).split(";")[0];
		    }

		 for(var j=0; j<EMD.loadAtStartWindow.length; j++) {
			 if(cv.length!=0) {
				 for(i=0; i<cv.length; i++) {
					 if(cv[i]==EMD.loadAtStartWindow[j].url) break;
				 }
				 if(i!=cv.length) continue;
			 }
	          	EMD.util.create_window(EMD.loadAtStartWindow[j].progrmFileNm, EMD.loadAtStartWindow[j].menuNm, EMD.loadAtStartWindow[j].url, null, {
	          		action: "loadStart"
	  			});
		 }
      }
    },
    closeWindow: function(win) {
        var dock_id = '#'+win.attr('id').replace('window', 'dock');

// $(this).children().remove();
// $('#'+win.attr('id')).hide('scale', { percent: 0 }, 800, function() {
// $(this).remove();
// }
// );
        $('#'+win.attr('id')).hide(function() {
        	$(this).remove();
        });

        $(dock_id).hide('fast');
        $(dock_id).remove();
    },
    /**
	 * gis 관련 함수
	 */
    gis: {
    	/**
		 * 지도에서 팝업을 호출 한다. (피처 선택 시)
		 *
		 * @param feature -
		 *            피처 오브젝트
		 * @param url -
		 *            팝업 내용을 로드할 URL
		 * @param params -
		 *            팝업 내용을 로드할때 전송할 파라미터
		 * @param func -
		 *            팝업이 완료 되고 실행 할 함수 (파라미터로 팝업 객체를 popup 에 담아 feature 가 전송
		 *            된다.)
		 * @returns - 없음
		 */
    	openPopup: function(feature, url, params, func) {
			$.ajax({
				type: "POST",
				url:EMD.context_root+url,
				data:params,
				dataType: "html",
				feature: feature,
				popupFunction: func,
				success: function(data, textStatus, jqXHR) {
						var mapPopupId="__framePopup_"+EMD.mapPopupId++;
		                var popup = new OpenLayers.Popup.FramedCloud("popup",
		                		this.feature.geometry.bounds.getCenterLonLat(),
		                		new OpenLayers.Size(250,150),
		                		"<div id='"+mapPopupId+"'></div>",
		                    null,
		                    true,
		                    function() {
		                		var ret=true;
		                		if(this.module.onClose!=null) {
		                			ret=this.module.onClosePopup(this);
		                		}
		                		if(ret) {
		                			EMD.map.removePopup(this);
		                		}
		                	}
		                );
		                EMD.map.addPopup(popup);
						var html = $.parseHTML( data, null, true );
		                $('#'+mapPopupId).append(html);
		                this.feature.popup = popup;

		                if(popupInfoModule!=null) {
	    					$('#'+mapPopupId)[0].module = popupInfoModule;
	    					popup.module=popupInfoModule;
			                EMD.util.modify_window(popupInfoModule, mapPopupId);
			                popupInfoModule.setModuleId(mapPopupId);
			                popupInfoModule.setPopup(popup);
			                popupInfoModule.setFeature(this.feature);
			                popupInfoModule.loadComplete();
		                }
		                if(this.popupFunction!=null) this.popupFunction(this.feature);
		                popup.updateSize();
				}
			});
    	},
    	closeAllPopup: function(layerName) {
    		var layer=EMD.gis.getUserLayer(layerName);
			for(var i = 0; i < len; i++) {
				feature = layer.features[i];
				if(feature && feature.popup!=null) {
					EMD.map.removePopup(feature.popup);
				}
			}
    	},
    	openGisPage: function(name) {
    		$('#__tempDiv').empty();
    		var form = document.createElement("form");
    		$(form).attr("id", "__InnerGisLayer");
    		for(var pn in params) {
    			var e = document.createElement("input");
    			$(e).attr("name", pn);
    			$(e).val(params[pn]);
    			$(form).append(e);
    		}
    		$(form).attr("action", EMD.context_root+"/openGisPage.do?name="+name);
    		$('#__tempDiv').append(form);

    		var win = window.open("","newPage","width=800, height=600, menubar=no,status=no,scrollbars=yes");

    		$(win).on("");

    		var module=this;

    		form.target = "newPage";
    		form.submit();
    	},
    	/**
		 * 레이어를 저장한다.
		 */
    	storeLayer: function(layer) {
    		layer.saveStrategy.save();
// EMD.saveStrategy.save();
		    EMD.refreshStrategy.refresh();
/*
 * var protocol = EMD.gis.getLayerProtocol(layer); var resp =
 * protocol.commit(layer.features, { callback:function(resp) { if(resp.error) {
 * //console.log('error'); return -1; } EMD.selectControl.unselectAll(); // //
 * 피처의 좌표계를 지도의 좌표계로 변경한다. EMD.refreshStrategy.refresh(); } });
 * resp.layer=layer;
 */
    		/*
			 * var protocol = EMD.gis.getLayerProtocol(layer); var baseProj =
			 * new OpenLayers.Projection("EPSG:5186"); var mapProj =
			 * EMD.map.getProjection(); for(var k in layer.features) { var
			 * f=layer.features[k]; if(f.state==OpenLayers.State.INSERT ||
			 * f.state==OpenLayers.State.UPDATE) { var polygon = f.geometry;
			 * f.geometry = polygon.transform(mapProj, baseProj); } } var resp =
			 * protocol.commit(layer.features, { callback:function(resp) {
			 * if(resp.error) { //console.log('error'); return -1; }
			 * EMD.selectControl.unselectAll(); // // 피처의 좌표계를 지도의 좌표계로 변경한다.
			 * EMD.refreshStrategy.refresh(); } }); resp.layer=layer;
			 */
    	},
    	refreshLayer: function(layer) {
    		EMD.refreshStrategy.refresh();
    		/*
			 * var protocol = EMD.gis.getLayerProtocol(layer);
			 * protocol.read(layer.features, { layer: layer,
			 * callback:function(resp) { if(resp.error) {
			 * //console.log('error'); return -1; } var gisAssetsCdProjection =
			 * new OpenLayers.Projection("EPSG:5186");
			 * EMD.selectControl.unselectAll(); // 피처의 좌표계를 지도의 좌표계로 변경한다.
			 * $.each(resp.reqFeatures, function() { var polygon= this.geometry;
			 * //alert(polygon); // var bounds=polygon.getBounds();
			 * this.geometry = polygon.transform(
			 * gisAssetsCdProjection,EMD.map.getProjection() ); });
			 * this.layer.addFeatures(resp.reqFeatures); this.layer.redraw(); }
			 * });
			 */
    	},
    	addLegendLayer: function(layer, legend) {
    		var l="";
	        var types = ["Point", "Line", "Polygon"];
			$.each(layer.styleMap.styles['default'].rules, function() {
		        var type, haveType;
		        if(this['title']==undefined) return;
				var symbolizers=this.symbolizers;
				if (!symbolizers) {
		            var symbolizer = this.symbolizer;
		            for (var i=0, len=types.length; i<len; ++i) {
		                type = types[i];
		                if (symbolizer[type]) {
		                    symbolizer = symbolizer[type];
		                    haveType = true;
		                    break;
		                }
		            }
		            symbolizers = [symbolizer];
		        } else {
		            var Type;
		            outer: for (var i=0, ii=types.length; i<ii; ++i) {
		                type = types[i];
		                Type = OpenLayers.Symbolizer[type];
		                if (Type) {
		                    for (var j=0, jj=symbolizers.length; j<jj; ++j) {
		                        if (symbolizers[j] instanceof Type) {
		                            haveType = true;
		                            break outer;
		                        }
		                    }
		                }
		            }
		        }
				EMD.symbols[this.id]={
		            symbolType: haveType ? type : this.symbolType,
		            symbolizers: symbolizers
				};
				l+="<li><div class='legendItem'><span class='symbol "+EMD.symbols[this.id].symbolType+"' id='"+this.id+"'></span><span class='title'>"+this.title+"</span></div></li>";
			});
			var tab = EMD.legendWin.tabs(legend);
// tab.attachHTMLString("<div class='legend'>test messages.test messages.test
// messages.test messages.test messages.test messages.test messages.test
// messages.test messages.test messages.test messages.test messages.</div>");
			tab.attachHTMLString("<div class='legend'><ul>"+l+"</ul></div>");
// EMD.legendWin.setSizes();
			$('div.legend').find("span.symbol").each(function() {
				var renderer=null;
				var renderers=["SVG", "VML", "Canvas"];
				var feature=new OpenLayers.Feature.Vector(
		                new OpenLayers.Geometry.Polygon([
		                                                 new OpenLayers.Geometry.LinearRing([
		                                                     new OpenLayers.Geometry.Point(-8, -4),
		                                                     new OpenLayers.Geometry.Point(-6, -6),
		                                                     new OpenLayers.Geometry.Point(6, -6),
		                                                     new OpenLayers.Geometry.Point(8, -4),
		                                                     new OpenLayers.Geometry.Point(8, 4),
		                                                     new OpenLayers.Geometry.Point(6, 6),
		                                                     new OpenLayers.Geometry.Point(-6, 6),
		                                                     new OpenLayers.Geometry.Point(-8, 4)
		                                                 ])
		                                             ])
		                );
		        for(var i=0, len=renderers.length; i<len; ++i) {
		            var Renderer = OpenLayers.Renderer[renderers[i]];
		            if(Renderer && Renderer.prototype.supported()) {
		                renderer = new Renderer(
		                		this, {
		                		map: {
				        		 getResolution: function() {
			        				return 1;
				        		 }
		                		}
		                	}
		                	// this.rendererOptions
		                );
		                var gb = feature.geometry.getBounds();
		                var gw = gb.getWidth();
		                var gh = gb.getHeight();
		                var resolution = null;
		                if(!resolution) {
		                    resolution = Math.max(gw / $(this).width() || 0, gh / $(this).height() || 0) || 1;
		                }
		                // determine height and width of element
		                var width = Math.max($(this).width(), gw / resolution);
		                var height = Math.max($(this).height(), gh / resolution);
		                // determine bounds of renderer
		                var center = gb.getCenterPixel();
		                var bhalfw = width * resolution / 2;
		                var bhalfh = height * resolution / 2;
		                var bounds = new OpenLayers.Bounds(
		                    center.x - bhalfw, center.y - bhalfh,
		                    center.x + bhalfw, center.y + bhalfh
		                );

		                renderer.setSize(new OpenLayers.Size(Math.round(width), Math.round(height)));
		                renderer.setExtent(bounds, true);
		                renderer.resolution = resolution;
		                renderer.clear();
		                break;
		            }
		        }
		        if(renderer==null) return;
		        switch(EMD.symbols[this.id].symbolType) {
		        	case 'Point':
		        		var symbol = new OpenLayers.Symbolizer(EMD.symbols[this.id].symbolizers[0]);
		        		symbol.label=this.title;
						renderer.drawFeature(new OpenLayers.Feature.Vector(
				                new OpenLayers.Geometry.Point(4, -6)
				            )
				            , symbol);
		        		break;
		        	case 'Line':
		        		var symbol = new OpenLayers.Symbolizer(EMD.symbols[this.id].symbolizers[0]);
		        		symbol.label=this.title;
						renderer.drawFeature(new OpenLayers.Feature.Vector(
				                new OpenLayers.Geometry.LineString([
				                                                    new OpenLayers.Geometry.Point(-8, -1),
				                                                    new OpenLayers.Geometry.Point(16, -1)
				                                                ])
							)
						    , symbol);
		        		break;
		        	case 'Polygon':
		        		var symbol = new OpenLayers.Symbolizer(EMD.symbols[this.id].symbolizers[0]);
		        		symbol.label=this.title;
		        		renderer.drawFeature(new OpenLayers.Feature.Vector(
				                new OpenLayers.Geometry.Polygon([
				                                                 new OpenLayers.Geometry.LinearRing([
				                                                     new OpenLayers.Geometry.Point(-6, -4),
				                                                     new OpenLayers.Geometry.Point(-4, -6),
				                                                     new OpenLayers.Geometry.Point(14, -6),
				                                                     new OpenLayers.Geometry.Point(16, -4),
				                                                     new OpenLayers.Geometry.Point(16, 1),
				                                                     new OpenLayers.Geometry.Point(14, 3),
				                                                     new OpenLayers.Geometry.Point(-4, 3),
				                                                     new OpenLayers.Geometry.Point(-6, 1)
				                                                 ])
				                                             ]),
				                {
				                	FCLTY_NM: this.title
				                }
				                )
						, symbol);
		        		break;
		        }
			});

			return tab;
    	},
    	/**
		 * Vworld에 지번 정보를 조회 하여 레이어에 추가한다.
		 *
		 * @param layerName -
		 *            피처를 추가할 레이어 명
		 * @param pnu -
		 *            조회 할 지번 코드
		 * @param retfunc -
		 *            호출을 완료 한 후 호출 할 함수 로딩 된 feature를 변수로 전송한다.
		 * @returns - 없음
		 */
    	getLotcode: function(layerName, pnu, retfunc, opt) {
    		var layer=EMD.gis.getUserLayer(layerName);
    		var filter = new OpenLayers.Filter.Logical({
    			type: OpenLayers.Filter.Logical.AND,
    			filters: [
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.EQUAL_TO,
						property: "pnu",
						value: pnu
					}),
					new OpenLayers.Filter.Spatial({
						type: OpenLayers.Filter.Spatial.BBOX,
						property: "BBOX",
						value: new OpenLayers.Bounds(14132415,4494156,14132417,4494158)
					})
				]
    		});
		 	var wfs_options = {
		 			url: "http://apis.vworld.kr/2ddata/cadastral/data?apiKey="+EMD.vworldKey+"&domain="+EMD.serverUrl,
// url:
// "http://map.vworld.kr/js/wfs.do?APIKEY="+EMD.vworldKey+"&domain="+EMD.serverUrl,
		 			params: {
		 				request: "GetFeature",
		 		        service: "wfs",
		 		        version: "1.1.0",
		 		        typeName: "LP_PA_CBND_BUBUN",
		 		        srsName: "EPSG:5186",
		 		        filter: "pnu:=:"+pnu
		 			},
 		        	format: new OpenLayers.Format.GML({
 		               geometryName: "AG_GEOM"
 		        	})
	 			};

		 	var protocol = new OpenLayers.Protocol.HTTP(wfs_options);
		 	var resp=protocol.read({
// filter: new OpenLayers.Filter.Comparison({
// type: OpenLayers.Filter.Comparison.EQUAL_TO,
// property: "pnu",
// value: pnu
// }),
				callback: function(resp) {
				    if(resp.error) {
				        // console.log('error');
				    	this.done_func(null);
				        return -1;
				    }
// resp.addFeature(resp.features);
			    	resp.doneFunc(resp.features, resp.opt);
				}
			});
		 	resp.layer=layer;
		 	resp.doneFunc=retfunc;
		 	resp.opt=opt;

    	},
    	/**
		 * 통계 색상용 더미 함수
		 *
		 * @param feature
		 * @returns
		 */
    	getStatsColor: function(feature) {
    		return '#ee6666';	// default color
    	},
    	/**
		 * 통계 라벨 더미 함수
		 *
		 * @param feature
		 * @returns
		 */
    	getStatsLabel: function(feature) {
    		return feature['PRT_FCLTY_NM'];
    	},
    	_getIndexBaseURL: function(bounds) {
    		var res = this.map.getResolution();
    		var x = Math.round((bounds.left - this.maxExtent.left) / (res * this.tileSize.w));
    		var y = Math.round((this.maxExtent.top - bounds.top) / (res * this.tileSize.h));
    		var z = this.map.getZoom();
    		var limit = Math.pow(2, z);
    		if (y < 0 || y >= limit)
    		{ return vworldUrlsExt.blankimage; }
    		else { x = ((x % limit) + limit) % limit;
    		return this.url + z + "/" + x + "/" + y + "." + this.type;
    		}
    	},
    	_adjustWindow: null,
    	_initGisAdjust: function() {
    		_adjustWindow = $('.window_stack');
    		EMD.util.window_hide_all();
    		EMD.selectControl.deactivate();
            if(EMD.popup.prtFcltyInfo) EMD.map.removePopup(EMD.popup.prtFcltyInfo);

    	},
    	_closeGisAdjust: function() {
    		EMD.selectControl.activate();

    		EMD.util.window_show(_adjustWindow);
// _adjustWindow.addClass('.window_stack');
    	},
    	modifyFeature: null,
    	saveFeatures: function() {
//    		EMD.saveStrategy.save();
    		EMD.saveLayer.gisAssetsCd.save();
    	},
    	addPrtFcltyMarkerClick: OpenLayers.Class(OpenLayers.Control, {
            defaultHandlerOptions: {
                'single': true,
                'double': false,
                'pixelTolerance': 0,
                'stopSingle': false,
                'stopDouble': false
            },

            initialize: function(options) {
                this.handlerOptions = OpenLayers.Util.extend(
                    {}, this.defaultHandlerOptions
                );
                OpenLayers.Control.prototype.initialize.apply(
                    this, arguments
                );
                this.handler = new OpenLayers.Handler.Click(
                    this, {
                        'click': this.trigger
                    }, this.handlerOptions
                );
            },

            trigger: function(e) {
                var lonlat = EMD.map.getLonLatFromPixel(e.xy);
        		EMD.map_value.fclty.laCrdnt=lonlat.lat;
        		EMD.map_value.fclty.loCrdnt=lonlat.lon;
        		EMD.map_state=null;
        		EMD.gis.removePrtFclty(EMD.map_value.fclty);
        		EMD.gis.drawPrtFclty(EMD.map_value.fclty);
	            EMD.userLayer.fcltyConstMarker.redraw();
	            EMD.userLayer.fcltySocMarker.redraw();
	            EMD.userLayer.fcltyMechMarker.redraw();
	            EMD.userLayer.fcltyInfoTeleMarker.redraw();
	            EMD.userLayer.fcltyElecPowerMarker.redraw();
        		EMD.gis._markerClick.deactivate();
        		EMD.map.removeControl(EMD.gis._markerClick);
        		EMD.gis._closeGisAdjust();
        		if(EMD.map_callback!=null) EMD.map_callback(EMD.map_value.fclty);
            }

        }),
        _markerClick: null,
    	addPrtFcltyMarker: function(prtFclty, callbackfunc) {
    		EMD.map_state="addPrtFcltyMarker";
    		EMD.map_value={fclty: prtFclty};
    		EMD.map_callback=callbackfunc;
    		EMD.gis._initGisAdjust();
    		EMD.gis._markerClick = new EMD.gis.addPrtFcltyMarkerClick();
    		EMD.map.addControl(EMD.gis._markerClick);
    		EMD.gis._markerClick.activate();
    	},
    	removePrtFclty:function(prtFclty) {
    		var features=null;
		    switch(prtFclty.gisPrtFcltySe) {
		    case 'C':
		    	features=EMD.userLayer.fcltyConstMarker.getFeatureBy('_fcltyObjCode', prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
			    EMD.userLayer.fcltyConstMarker.removeFeatures(features);
				break;
		    case 'M':
		    	features=EMD.userLayer.fcltyMechMarker.getFeatureBy('_fcltyObjCode', prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
			    EMD.userLayer.fcltyMechMarker.removeFeatures(features);
				break;
		    case 'E':
		    	features=EMD.userLayer.fcltyInfoTeleMarker.getFeatureBy('_fcltyObjCode', prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
			    EMD.userLayer.fcltyInfoTeleMarker.removeFeatures(features);
				break;
		    case 'S':
		    	features=EMD.userLayer.fcltySocMarker.getFeatureBy('_fcltyObjCode', prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
			    EMD.userLayer.fcltySocMarker.removeFeatures(features);
				break;
		    case 'P':
		    	features=EMD.userLayer.fcltyElecPowerMarker.getFeatureBy('_fcltyObjCode', prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
			    EMD.userLayer.fcltyElecPowerMarker.removeFeatures(features);
				break;

		    }
    		EMD.gis._markerClick.deactivate();
    	},
    	removeFeatures:function(module, layerName, features) {
    		var layer=EMD.gis.getUserLayer(layerName);
		    EMD.selectControl.unselectAll();
    		$.each(features, function() {
    			if(this.popup!=undefined) EMD.map.removePopup(this.popup);
    			this.state=OpenLayers.State.DELETE;
    		});
    		var protocol = EMD.gis.getLayerProtocol(layer);
    		resp=protocol.commit(features, {
		    	callback:function(resp) {
    			    if(resp.error) {
    			        console.log('error');
    			        return -1;
    			    }
    			    resp.layer.removeFeatures(resp.reqFeatures);
    			    alert('영역이 삭제 되었습니다.');
		    	}
		    });
    		resp.module=module;
    		resp.layer=layer;
    	},
    	drawPrtFclty:function(prtFclty) {
			var icon;
// var size = new OpenLayers.Size(32,37);
// var calculateOffset = function(size) {
// return new OpenLayers.Pixel(-(size.w/2), -size.h); };

			switch(prtFclty.gisPrtFcltyCd) {
			case '55':
// icon = new
// OpenLayers.Icon(EMD.context_root+'/images/egovframework/ygpa/gam/maps/map_icon/dam.png',
// size, null, calculateOffset);
				icon = EMD.context_root+'/images/egovframework/ygpa/gam/maps/map_icon/dam.png';
				break;
			default:
// icon = new
// OpenLayers.Icon(EMD.context_root+'/images/egovframework/ygpa/gam/maps/map_icon/photo.png',
// size, null, calculateOffset);
				icon = EMD.context_root+'/images/egovframework/ygpa/gam/maps/map_icon/photo.png';
				break;
			}
// var pos = new OpenLayers.LonLat(prtFclty.loCrdnt,prtFclty.laCrdnt);
// var marker = new OpenLayers.Feature.Marker(pos,{},
// {externalGraphic: icon});
// EMD.userLayer.fcltyMarker.addMarker(marker);
			if(prtFclty.loCrdnt==null || prtFclty.laCrdnt==null) {
				if(prtFclty.lat!=null && prtFclty.lng!=null) {
					var latLng = new OpenLayers.LonLat(prtFclty.lng, prtFclty.lat);
					var proj = new OpenLayers.Projection('EPSG:4326');
					var newPos= latLng.transform(proj, new OpenLayers.Projection('EPSG:900913'));
// //console.log(newPos);
					prtFclty.loCrdnt=newPos.lon;
					prtFclty.laCrdnt=newPos.lat;
				}
			}
			var marker = new OpenLayers.Feature.Vector(
					new OpenLayers.Geometry.Point(prtFclty.loCrdnt,prtFclty.laCrdnt),
					{gisAssetsPrtAtCode:prtFclty.gisAssetsPrtAtCode, gisAssetsCd:prtFclty.gisAssetsCd, gisAssetsSubCd:prtFclty.gisAssetsSubCd, gisPrtFcltyCd:prtFclty.gisPrtFcltyCd, gisPrtFcltySeq:prtFclty.gisPrtFcltySeq, gisPrtFcltySe:prtFclty.gisPrtFcltySe, fcltyNm: prtFclty.prtFcltyNm},
					{externalGraphic: icon, graphicHeight: 37, graphicWidth: 32, graphicXOffset:-18, graphicYOffset:-37,
		                labelOutlineColor: "white",
		                labelOutlineWidth: 3,
						label: prtFclty.prtFcltyNm});
		    marker._fcltyObjCode=prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq;
		    switch(prtFclty.gisPrtFcltySe) {
		    case 'C':
				EMD.userLayer.fcltyConstMarker.addFeatures(marker);
				break;
		    case 'M':
				EMD.userLayer.fcltyMechMarker.addFeatures(marker);
				break;
		    case 'E':
				EMD.userLayer.fcltyInfoTeleMarker.addFeatures(marker);
				break;
		    case 'S':
				EMD.userLayer.fcltySocMarker.addFeatures(marker);
				break;
		    case 'P':
				EMD.userLayer.fcltyElecPowerMarker.addFeatures(marker);
				break;

		    }
    	},
    	_getLayerFilter: function(layer, obj) {
    		var filter=null;
    		if(layer==EMD.userLayer.gisAssetsCd) {
	    		filter = new OpenLayers.Filter.Logical({
					type: OpenLayers.Filter.Logical.AND,
					filters: [
						new OpenLayers.Filter.Comparison({
							type: OpenLayers.Filter.Comparison.EQUAL_TO,
							property: "PRT_CD",
							value: obj.gisAssetsPrtAtCode
						}),
						new OpenLayers.Filter.Comparison({
							type: OpenLayers.Filter.Comparison.EQUAL_TO,
							property: "ASSETS_CD",
							value: obj.gisAssetsCd
						}),
						new OpenLayers.Filter.Comparison({
							type: OpenLayers.Filter.Comparison.EQUAL_TO,
							property: "ASSETS_SCD",
							value: obj.gisAssetsSubCd
						})
					]
				});
	    	}
    		else if(layer==EMD.userLayer.assetsRent) {
        		if(obj['assetsUsageSeq']==undefined) {
		    		filter = new OpenLayers.Filter.Logical({
						type: OpenLayers.Filter.Logical.AND,
						filters: [
							new OpenLayers.Filter.Comparison({
								type: OpenLayers.Filter.Comparison.EQUAL_TO,
								property: "PRT_AT_CD",
								value: obj.prtAtCode
							}),
							new OpenLayers.Filter.Comparison({
								type: OpenLayers.Filter.Comparison.EQUAL_TO,
								property: "MNG_YEAR",
								value: obj.mngYear
							}),
							new OpenLayers.Filter.Comparison({
								type: OpenLayers.Filter.Comparison.EQUAL_TO,
								property: "MNG_NO",
								value: obj.mngNo
							}),
							new OpenLayers.Filter.Comparison({
								type: OpenLayers.Filter.Comparison.EQUAL_TO,
								property: "MNG_CNT",
								value: obj.mngCnt
							})
						]
					});
        		}
        		else {
        			filter = new OpenLayers.Filter.Logical({
						type: OpenLayers.Filter.Logical.AND,
						filters: [
							new OpenLayers.Filter.Comparison({
								type: OpenLayers.Filter.Comparison.EQUAL_TO,
								property: "PRT_AT_CD",
								value: obj.prtAtCode
							}),
							new OpenLayers.Filter.Comparison({
								type: OpenLayers.Filter.Comparison.EQUAL_TO,
								property: "MNG_YEAR",
								value: obj.mngYear
							}),
							new OpenLayers.Filter.Comparison({
								type: OpenLayers.Filter.Comparison.EQUAL_TO,
								property: "MNG_NO",
								value: obj.mngNo
							}),
							new OpenLayers.Filter.Comparison({
								type: OpenLayers.Filter.Comparison.EQUAL_TO,
								property: "MNG_CNT",
								value: obj.mngCnt
							}),
							new OpenLayers.Filter.Comparison({
								type: OpenLayers.Filter.Comparison.EQUAL_TO,
								property: "ASSETS_USAGE_SEQ",
								value: obj.assetsUsageSeq
							})
						]
					});
        		}
    		}
    		return filter;
    	},
    	_getLayerprotocol: function(layer) {
    		var protocol=null;
    		if(layer==EMD.userLayer.gisAssetsCd) {
    			protocol=EMD.protocols.gisAssetsCd;
	    	}
    		else if(layer==EMD.userLayer.assetsRent) {
    			protocol= EMD.protocols.assetsRent;
    		}
    		else if(layer==EMD.userLayer.assetsRentDetail) {
    			protocol= EMD.protocols.assetsRentDetail;
    		}
    		return protocol;
    	},
    	_findFeatures: function(layer, obj) {
    		var foundFeatures=[];
			for(var i = 0; i < layer.features.length; i++) {
				feature = layer.features[i];
				if(feature && feature.attributes) {
					if (EMD.gis.compareLayerObject(layer, feature, obj)) {
						foundFeatures.push(feature);
						break;
					}
				}
			}
			return foundFeatures;
    	},
    	/**
		 * 피처를 찾는 펑션
		 *
		 * @param module
		 * @param layerName
		 * @param obj
		 * @param opt
		 * @returns
		 */
    	findFeatureData:function(module, layerName, obj, opt) {
			var layer=EMD.gis.getUserLayer(layerName);
    		if(layer==null) {
    			return;
    		}
			var filter = EMD.gis._getLayerFilter(obj);
			var protocol = EMD.gis._getLayerprotocol(layer);
			var zoomTo = zoomToExtent==null?false:zoomToExtent;
			var refreshData = refresh==undefined?false:refresh;
			if(opt!=undefined) {
				opt.zoomToExtent = opt.zoomToExtent==null?false:zoomToExtent;
				opt.refresh = opt.refresh==undefined?false:refresh;
			}
			else {
				opt = {
					zoomTo:true,
					refreshData:true
				};
			}

			var foundFeatures = EMD.gis._findFeatures(layer, obj);

			if(foundFeatures.length==0 || refreshData) {
	    		protocol.read({
	    			filter: filter,
	    			_callback: opt.callback,
	    			opt: opt,
	    			callback: function(resp) {
	    			    if(resp.error) {
	    			        console.log('find error');
	    			        return 0;
	    			    }
	    			    if(resp.features.length>0) {
	    					var ext;
	    					var proj = new OpenLayers.Projection("EPSG:5186");
	    					if(resp.features.length!=undefined) {
			    			    $.each(resp.features, function() {
									  var polygon= this.geometry;
								      this.geometry = polygon.transform(
								    		  proj, EMD.map.getProjection()
								          );
				    			    });
	    					}
	    					else {
							  var polygon= resp.features.geometry;
						      this.geometry = polygon.transform(
						    		  proj, EMD.map.getProjection()
						          );
	    					}

	  			          	if(this.opt.zoomTo) {
		    					ext = resp.features[0].geometry.getBounds().clone();
		    					for(var i=1;i<resp.features.length;i++) {
		    						ext.extend(resp.features[i].geometry.getBounds());
		    					}
		    					EMD.map.zoomToExtent(ext);
		    			    }
	    			    }

	    			}
	    		});
			}
			else {
		          	if(zoomTo) {
    					ext = resp.features[0].geometry.getBounds().clone();
    					for(var i=1;i<resp.features.length;i++) {
    						ext.extend(resp.features[i].geometry.getBounds());
    					}
    					EMD.map.zoomToExtent(ext);
    			    }
			}
    	},
    	/**
		 * 레이어에 대해 피처 코드를 로딩 한다.
		 *
		 * @param layerName
		 * @param obj
		 * @returns
		 */
    	selectFeatureData:function(module, layerName, obj, zoomToExtent, refresh) {
			var layer=EMD.gis.getUserLayer(layerName);
    		if(layer==null) {
    			return;
    		}
			var filter = EMD.gis._getLayerFilter(obj);
			var protocol = EMD.gis._getLayerprotocol(layer);
			var zoomTo = zoomToExtent==null?false:zoomToExtent;
			var refreshData = refresh==undefined?false:refresh;

			var foundFeatures = EMD.gis._findFeatures(layer, obj);

			if(foundFeatures.length==0 || refreshData) {
	    		protocol.read({
	    			filter: filter,
	    			callback: function(resp) {
	    			    if(resp.error) {
	    			        // console.log('find error');
	    			        return 0;
	    			    }
	    			    if(resp.features.length>0) {
	    					var ext;
	    					var proj = new OpenLayers.Projection("EPSG:5186");
		    			    $.each(resp.features, function() {
							  var polygon= this.geometry;
						      this.geometry = polygon.transform(
						    		  proj,EMD. map.getProjection()
						          );
		    			    });
		    			    layer.removeAllFeatures();
		    			    layer.addFeatures(resp.features);
		    			    layer.redraw();

	  			          	if(zoomTo) {
		    					ext = resp.features[0].geometry.getBounds().clone();
		    					for(var i=1;i<resp.features.length;i++) {
		    						ext.extend(resp.features[i].geometry.getBounds());
		    					}
		    					EMD.map.zoomToExtent(ext);
		    			    }
	    			    }

	    			}
	    		});
			}
			else {
		          	if(zoomTo) {
    					ext = resp.features[0].geometry.getBounds().clone();
    					for(var i=1;i<resp.features.length;i++) {
    						ext.extend(resp.features[i].geometry.getBounds());
    					}
    					EMD.map.zoomToExtent(ext);
    			    }
			}
    	},
    	selectRentFeature:function(obj) {
    		if(obj==null) {
// EMD.strategies.assetsRentDetail.deactivate();
    			return;
    		}
// EMD.strategies.assetsRentDetail.activate();
    		if(obj['assetsUsageSeq']==undefined) {
    			protocol.read({
        			filter: filter,
        			callback: function(resp) {
        			    if(resp.error) {
        			        // console.log('find error');
        			        return 0;
        			    }
        			    if(resp.features.length>0) {
        					var ext;
        					var proj = new OpenLayers.Projection("EPSG:5186");
		    			    $.each(resp.features, function() {
								  var polygon= this.geometry;
						      this.geometry = polygon.transform(
						    		  proj,EMD.map.getProjection()
						          );
		    			    });
		    			    EMD.userLayer.assetsRentDetail.removeAllFeatures();
        					EMD.userLayer.assetsRentDetail.addFeatures(resp.features);
      			          	EMD.userLayer.assetsRentDetail.redraw();

        					ext = resp.features[0].geometry.getBounds().clone();
        					for(var i=1;i<resp.features.length;i++) {
        						ext.extend(resp.features[i].geometry.getBounds());
        					}
        					EMD.map.zoomToExtent(ext);

        			    	/*
							 * resp.features[0].geometry =
							 * feature.geometry.clone(); resp.features[0].state =
							 * OpenLayers.State.UPDATE; //
							 * EMD.protocols.gisAssetsCd.update(resp.features);
							 * EMD.protocols.gisAssetsCd.commit(resp.features, {
							 * callback:function(resp) { if(resp.error) {
							 * //console.log('error'); return -1; } var
							 * gisAssetsCdProjection = new
							 * OpenLayers.Projection("EPSG:5186");
							 * EMD.selectControl.unselectAll(); // 피처의 좌표계를 지도의
							 * 좌표계로 변경한다. $.each(resp.reqFeatures, function() {
							 * var polygon= this.geometry; //alert(polygon); //
							 * var bounds=polygon.getBounds(); this.geometry =
							 * polygon.transform(
							 * gisAssetsCdProjection,EMD.map.getProjection() );
							 * });
							 * EMD.userLayer.gisAssetsCd.addFeatures(resp.reqFeatures);
							 * EMD.userLayer.gisAssetsCd.redraw(); } }); } else {
							 * var v = new OpenLayers.Feature(); v.state =
							 * OpenLayers.State.INSERT; v.geometry =
							 * feature.geometry.clone(); v.attributes = {
							 * PRT_CD:assetsCd.gisAssetsPrtAtCode,
							 * ASSETS_CD:assetsCd.gisAssetsCd,
							 * ASSETS_SCD:assetsCd.gisAssetsSubCd,
							 * ASSETS_NM:assetsCd.gisAssetsNm,
							 * LOCPLC:assetsCd.gisAssetsLocplc,
							 * GIS_ASSETS_LNM:assetsCd.gisAssetsLnm,
							 * GIS_ASSETS_LNM_SUB:assetsCd.gisAssetsLnmSub };
							 *
							 * var v = new OpenLayers.Feature(); v.geometry =
							 * feature.geometry.clone(); v.attributes =
							 * {PRT_AT_CD:assetsCd.gisAssetsPrtAtCode,
							 * PRT_CD:assetsCd.gisAssetsCd,
							 * PRT_SUB_CD:assetsCd.gisAssetsSubCd,
							 * PRT_NM:assetsCd.gisAssetsNm,
							 * LOCPLC:assetsCd.gisAssetsLocplc,
							 * GIS_ASSETS_LNM:assetsCd.gisAssetsLnm,
							 * GIS_ASSETS_LNM_SUB:assetsCd.gisAssetsLnmSub};
							 * EMD.protocols.gisAssetsCd.create(v); v.state =
							 * OpenLayers.State.INSERT;
							 * EMD.protocols.gisAssetsCd.commit([v],
							 * {callback:function(resp) { if (resp.error) {
							 * //console.log("error"); return-1; commit; }
							 * EMD.selectControl.unselectAll(); var
							 * gisAssetsCdProjection = new
							 * OpenLayers.Projection("EPSG:5186");
							 *
							 * $.each(resp.reqFeatures, function() { var polygon =
							 * this.geometry; this.geometry =
							 * polygon.transform(gisAssetsCdProjection,
							 * EMD.map.getProjection()); });
							 * EMD.userLayer.gisAssetsCd.addFeatures(resp.reqFeatures);
							 * EMD.userLayer.gisAssetsCd.redraw(); }});
							 */
        			    }

        			}
// scope: new OpenLayers.Strategy.BBOX()
        		});
    		}
    		else {
    			EMD.strategies.assetsRentDetail.setFilter(new OpenLayers.Filter.Logical({
    				type: OpenLayers.Filter.Logical.AND,
    				filters: [
    					new OpenLayers.Filter.Comparison({
    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
    						property: "PRT_AT_CD",
    						value: obj.prtAtCode
    					}),
    					new OpenLayers.Filter.Comparison({
    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
    						property: "MNG_YEAR",
    						value: obj.mngYear
    					}),
    					new OpenLayers.Filter.Comparison({
    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
    						property: "MNG_NO",
    						value: obj.mngNo
    					}),
    					new OpenLayers.Filter.Comparison({
    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
    						property: "MNG_CNT",
    						value: obj.mngCnt
    					}),
    					new OpenLayers.Filter.Comparison({
    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
    						property: "ASSETS_USAGE_SEQ",
    						value: obj.assetsUsageSeq
    					})
    				]
    			})
    			);
    		}
    	},
    	assetRentStatsRedraw: function() {
    		var currDate=new Date();
        	if(EMD.layerDisplay['assetRentStats']!=undefined) {
				EMD.strategies.assetRentStats.setFilter(new OpenLayers.Filter.Logical({
					type: OpenLayers.Filter.Logical.AND,
					filters: [
						new OpenLayers.Filter.Comparison({
							type: OpenLayers.Filter.Comparison.EQUAL_TO,
							property: "PRT_AT_CD",
							value: '622'
						}),
						/*
						 * new OpenLayers.Filter.Comparison({ type:
						 * OpenLayers.Filter.Comparison.LESS_THAN, property:
						 * "GR_USAGE_PD_FROM", value: currDate }), new
						 * OpenLayers.Filter.Comparison({ type:
						 * OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO ,
						 * property: "GR_USAGE_PD_TO", value: currDate }),
						 */
						new OpenLayers.Filter.Comparison({
							type: OpenLayers.Filter.Comparison.EQUAL_TO,
							property: "MNG_YEAR",
							value: '2014'
						})
					]
				})
				);
        	}
    	},
    	gnrlRentStatsRedraw: function() {
    		var currDate=new Date();
        	if(EMD.layerDisplay['gnrlRentStats']!=undefined) EMD.strategies.gnrlRentStats.setFilter(new OpenLayers.Filter.Logical({
					type: OpenLayers.Filter.Logical.AND,
					filters: [
						new OpenLayers.Filter.Comparison({
							type: OpenLayers.Filter.Comparison.EQUAL_TO,
							property: "QUAY_GROUP_CD",
							value: 'P'
						}),
						new OpenLayers.Filter.Comparison({
							type: OpenLayers.Filter.Comparison.LESS_THAN,
							property: "GR_USAGE_PD_FROM",
							value: currDate
						}),
						new OpenLayers.Filter.Comparison({
							type: OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO ,
							property: "GR_USAGE_PD_TO",
							value: currDate
						}),
						new OpenLayers.Filter.Comparison({
							type: OpenLayers.Filter.Comparison.EQUAL_TO,
							property: "PRMISN_YN",
							value: 'Y'
						})
					]
				})
			);
    	},
    	cntrRentStatsRedraw: function() {
    		var currDate=new Date();
        	if(EMD.layerDisplay['cntrRentStats']!=undefined) EMD.strategies.cntrRentStats.setFilter(new OpenLayers.Filter.Logical({
				type: OpenLayers.Filter.Logical.AND,
				filters: [
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.EQUAL_TO,
						property: "QUAY_GROUP_CD",
						value: 'P'
					}),
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.LESS_THAN,
						property: "GR_USAGE_PD_FROM",
						value: currDate
					}),
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO ,
						property: "GR_USAGE_PD_TO",
						value: new Date()
					}),
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.EQUAL_TO,
						property: "PRMISN_YN",
						value: 'Y'
					})
				]
			})
		);
    	},
    	htldRentStatsRedraw: function() {
    		var currDate=new Date();
        	if(EMD.layerDisplay['htldRentStats']!=undefined) EMD.strategies.htldRentStats.setFilter(new OpenLayers.Filter.Logical({
				type: OpenLayers.Filter.Logical.AND,
				filters: [
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.EQUAL_TO,
						property: "QUAY_GROUP_CD",
						value: 'H'
					}),
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.LESS_THAN,
						property: "GR_USAGE_PD_FROM",
						value: currDate
					}),
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO ,
						property: "GR_USAGE_PD_TO",
						value: currDate
					}),
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.EQUAL_TO,
						property: "PRMISN_YN",
						value: 'Y'
					})
				]
				})
			);
    	},
    	ccntRentStatsRedraw: function() {
    		var currDate=new Date();
    		if(EMD.layerDisplay['ccntRentStats']!=undefined) EMD.strategies.ccntRentStats.setFilter(new OpenLayers.Filter.Logical({
					type: OpenLayers.Filter.Logical.AND,
					filters: [
						new OpenLayers.Filter.Comparison({
							type: OpenLayers.Filter.Comparison.EQUAL_TO,
							property: "QUAY_GROUP_CD",
							value: 'E'
						}),
						new OpenLayers.Filter.Comparison({
							type: OpenLayers.Filter.Comparison.LESS_THAN,
							property: "GR_USAGE_PD_FROM",
							value: currDate
						}),
						new OpenLayers.Filter.Comparison({
							type: OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO ,
							property: "GR_USAGE_PD_TO",
							value: currDate
						}),
						new OpenLayers.Filter.Comparison({
							type: OpenLayers.Filter.Comparison.EQUAL_TO,
							property: "PRMISN_YN",
							value: 'Y'
						})
					]
				})
			);
    	},
    	trnpRentStatsRedraw: function() {
    		var currDate=new Date();
		        	if(EMD.layerDisplay['trnpRentStats']!=undefined) EMD.strategies.trnpRentStats.setFilter(new OpenLayers.Filter.Logical({
						type: OpenLayers.Filter.Logical.AND,
						filters: [
							new OpenLayers.Filter.Comparison({
								type: OpenLayers.Filter.Comparison.EQUAL_TO,
								property: "QUAY_GROUP_CD",
								value: 'T'
							}),
							new OpenLayers.Filter.Comparison({
								type: OpenLayers.Filter.Comparison.LESS_THAN,
								property: "GR_USAGE_PD_FROM",
								value: currDate
							}),
							new OpenLayers.Filter.Comparison({
								type: OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO ,
								property: "GR_USAGE_PD_TO",
								value: currDate
							}),
							new OpenLayers.Filter.Comparison({
								type: OpenLayers.Filter.Comparison.EQUAL_TO,
								property: "PRMISN_YN",
								value: 'Y'
							})
						]
					})
				);
    	},
    	goLocation: function(lat, lon) {
			var pos = new OpenLayers.LonLat(lon, lat);
			EMD.map.setCenter(pos);
    	},
    	goLocation4326: function(lat, lon) {
			var latLng = new OpenLayers.LonLat(lon, lat);
			var proj = new OpenLayers.Projection('EPSG:4326');
			var newPos= latLng.transform(proj, new OpenLayers.Projection('EPSG:900913'));
			EMD.map.setCenter(newPos, 18);
    	},
    	selectPrtFclty: function(prtFclty) {
    		var features=null;
		    switch(prtFclty.gisPrtFcltySe) {
		    case 'C':
		    	features=EMD.userLayer.fcltyConstMarker.getFeatureBy('_fcltyObjCode', prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
				break;
		    case 'M':
		    	features=EMD.userLayer.fcltyMechMarker.getFeatureBy('_fcltyObjCode', prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
				break;
		    case 'E':
		    	features=EMD.userLayer.fcltyInfoTeleMarker.getFeatureBy('_fcltyObjCode', prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
				break;
		    case 'S':
		    	features=EMD.userLayer.fcltySocMarker.getFeatureBy('_fcltyObjCode', prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
				break;
		    case 'P':
		    	features=EMD.userLayer.fcltyElecPowerMarker.getFeatureBy('_fcltyObjCode', prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
				break;

		    }
		    if(features==null) {
		    	alert('선택된 시설의 정의된 위치가 없습니다.');
		    	return;
		    }
			EMD.selectControl.select(features);
    	},
    	setScale: function(scale) {
    		EMD.map.setScale(scale);
    	},
    	showGisAssetCd:function(prtFclty) {
    		var filter = new OpenLayers.Filter.Logical({
				type: OpenLayers.Filter.Logical.AND,
				filters: [
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.EQUAL_TO,
						property: "PRT_CD",
						value: prtFclty.gisAssetsPrtAtCode
					}),
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.EQUAL_TO,
						property: "ASSETS_CD",
						value: prtFclty.gisAssetsCd
					}),
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.EQUAL_TO,
						property: "ASSETS_SCD",
						value: prtFclty.gisAssetsSubCd
					})
				]
			});
			EMD.protocols.gisAssetsCd.read({
				filter: filter,
				callback: function(resp) {
				    if(resp.error) {
				        // console.log('error');
				        return -1;
				    }
// EMD.userLayer.gisAssetsCd. resp.features
				}
			});
    	},
    	selectAssetCdFeature: function(gisAssetsPrtAtCode, gisAssetsCd, gisAssetsSubCd, option) {
    		var features=null;
		    switch(prtFclty.gisPrtFcltySe) {
		    case 'C':
		    	features=EMD.userLayer.fcltyConstMarker.getFeatureBy('_fcltyObjCode', prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
				break;
		    case 'M':
		    	features=EMD.userLayer.fcltyMechMarker.getFeatureBy('_fcltyObjCode', prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
				break;
		    case 'E':
		    	features=EMD.userLayer.fcltyInfoTeleMarker.getFeatureBy('_fcltyObjCode', prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
				break;
		    case 'S':
		    	features=EMD.userLayer.fcltySocMarker.getFeatureBy('_fcltyObjCode', prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
				break;
		    case 'P':
		    	features=EMD.userLayer.fcltyElecPowerMarker.getFeatureBy('_fcltyObjCode', prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
				break;

		    }
		    if(features==null) {
		    	alert('선택된 시설의 정의된 위치가 없습니다.');
		    	return;
		    }
			EMD.selectControl.select(features);

    		var opts = option;
// var filter = new OpenLayers.Filter.Logical({
// type: OpenLayers.Filter.Logical.AND,
// filters: [
// new OpenLayers.Filter.Comparison({
// type: OpenLayers.Filter.Comparison.EQUAL_TO,
// property: "PRT_AT_CD",
// value: gisAssetsPrtAtCode
// }),
// new OpenLayers.Filter.Comparison({
// type: OpenLayers.Filter.Comparison.EQUAL_TO,
// property: "PRT_CD",
// value: gisAssetsCd
// }),
// new OpenLayers.Filter.Comparison({
// type: OpenLayers.Filter.Comparison.EQUAL_TO,
// property: "PRT_SUB_CD",
// value: gisAssetsSubCd
// })
// ]
// });
// EMD.protocols.gisAssetsCd.read({
// filter: EMD.protocols.gisAssetsCd,
// callback: function(resp) {
// if(resp.error) {
// //console.log('error');
// return -1;
// }
// if(resp.features.length>0) {
// EMD.userLayer.gisAssetsCd.addFeatures(resp.features);
// EMD.userLayer.gisAssetsCd.redraw();
//
// // var bounds = new OpenLayers.Bounds();
// var bounds = resp.features[0].geometry.bounds.clone();
// for(var i=1; i<resp.features.length; i++) {
// bounds.extend(resp.features[i].geometry.bounds);
// }
//
// // EMD.selectControl.select(resp.features);
// EMD.map.zoomToExtent(bounds, true);
// }
// if(opts.callback!=null) {
// opts.callback(resp);
// }
// },
// scope: new OpenLayers.Strategy.BBOX()
// });
    	},
    	removeAssetCdFeature: function(assetsCd) {
    		var filter = new OpenLayers.Filter.Logical({
    			type: OpenLayers.Filter.Logical.AND,
    			filters: [
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.EQUAL_TO,
						property: "PRT_CD",
						value: assetsCd.gisAssetsPrtAtCode
					}),
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.EQUAL_TO,
						property: "ASSETS_CD",
						value: assetsCd.gisAssetsCd
					}),
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.EQUAL_TO,
						property: "ASSETS_SCD",
						value: assetsCd.gisAssetsSubCd
					})
				]
    		});
    		EMD.protocols.gisAssetsCd['delete']({
    			filter: filter
    		});
    		EMD.protocols.gisAssetsCd.commit();
    	},
    	modifyAssetCdFeature: function(assetsCd, feature) {
    		var filter = new OpenLayers.Filter.Logical({
    			type: OpenLayers.Filter.Logical.AND,
    			filters: [
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.EQUAL_TO,
						property: "PRT_CD",
						value: assetsCd.gisAssetsPrtAtCode
					}),
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.EQUAL_TO,
						property: "ASSETS_CD",
						value: assetsCd.gisAssetsCd
					}),
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.EQUAL_TO,
						property: "ASSETS_SCD",
						value: assetsCd.gisAssetsSubCd
					})
				]
    		});
    		EMD.protocols.gisAssetsCd.read({
    			filter: filter,
    			callback: function(resp) {
    			    if(resp.error) {
    			        // console.log('find error');
    			        return 0;
    			    }
    			    if(resp.features.length>0) {
        			    resp.features[0].geometry = feature.geometry.clone();
        			    resp.features[0].state = OpenLayers.State.UPDATE;
// EMD.protocols.gisAssetsCd.update(resp.features);
        			    EMD.protocols.gisAssetsCd.commit(resp.features, {
        			    	callback:function(resp) {
    		    			    if(resp.error) {
    		    			        // console.log('error');
    		    			        return -1;
    		    			    }
    		    			    var gisAssetsCdProjection = new OpenLayers.Projection("EPSG:5186");
    		    			    EMD.selectControl.unselectAll();
    							  // 피처의 좌표계를 지도의 좌표계로 변경한다.
    		    			    $.each(resp.reqFeatures, function() {
    								  var polygon= this.geometry;
    							      // alert(polygon);
// var bounds=polygon.getBounds();
    						      this.geometry = polygon.transform(
    								    	 gisAssetsCdProjection,EMD.map.getProjection()
    						          );
    		    			    });
    					    	EMD.userLayer.gisAssetsCd.addFeatures(resp.reqFeatures);
    					    	EMD.userLayer.gisAssetsCd.redraw();
        			    	}
        			    });
    			    }
    			    else {
    			    	var v = new OpenLayers.Feature();
    			    	v.state = OpenLayers.State.INSERT;
    			    	v.geometry = feature.geometry.clone();
    			    	v.attributes = {
    			    			PRT_CD:assetsCd.gisAssetsPrtAtCode,
			    				ASSETS_CD:assetsCd.gisAssetsCd,
		    					ASSETS_SCD:assetsCd.gisAssetsSubCd,
		    					ASSETS_NM:assetsCd.gisAssetsNm,
		    					ASSETS_SE_CD:assetsCd.gisAssetsSeCd,
		    					BJD_CODE:EMD.gis.makeBJDCode(assetsCd.gisAssetsLocplc, assetsCd.gisAssetsLnm, assetsCd.gisAssetsLnmSub)
    			    	};

    			        var v = new OpenLayers.Feature();
    			        v.geometry = feature.geometry.clone();
    			        v.attributes = {
    			        		PRT_CD:assetsCd.gisAssetsPrtAtCode,
    			        		ASSETS_CD:assetsCd.gisAssetsCd,
    			        		ASSETS_SCD:assetsCd.gisAssetsSubCd,
    			        		ASSETS_NM:assetsCd.gisAssetsNm,
		    					ASSETS_SE_CD:assetsCd.gisAssetsSeCd,
    			        		LOCPLC:assetsCd.gisAssetsLocplc,
    			        		GIS_ASSETS_LNM:assetsCd.gisAssetsLnm,
    			        		GIS_ASSETS_LNM_SUB:assetsCd.gisAssetsLnmSub};
    			        EMD.protocols.gisAssetsCd.create(v);
    			        v.state = OpenLayers.State.INSERT;
    			        EMD.protocols.gisAssetsCd.commit([v], {callback:function(resp) {
    			          if (resp.error) {
    			            // console.log("error");
    			            return-1;
    			            commit;
    			          }
    			          EMD.selectControl.unselectAll();
    			            var gisAssetsCdProjection = new OpenLayers.Projection("EPSG:5186");

    			          $.each(resp.reqFeatures, function() {
    			            var polygon = this.geometry;
    			            this.geometry = polygon.transform(gisAssetsCdProjection, EMD.map.getProjection());
    			          });
    			          EMD.userLayer.gisAssetsCd.addFeatures(resp.reqFeatures);
    			          EMD.userLayer.gisAssetsCd.redraw();
    			        }});
    			    }

    			},
// scope: new OpenLayers.Strategy.BBOX()
    		});
    	},
    	findLotCode: function(bjdCode, lnm, lnmSub, mt, data, getFunc) {
    		var pnu=bjdCode+(mt?'2':'1')+EMD.util.leftPad(lnm*1, '0', 4)+EMD.util.leftPad((lnmSub||0)*1, '0', 4);
    		console.log('pnu:"'+pnu+'"');
    		var filter = new OpenLayers.Filter.Comparison({
				type: OpenLayers.Filter.Comparison.EQUAL_TO,
				property: "PNU",
				value: pnu
			});
    		var resp=EMD.protocols.landCode.read({
    			filter: filter,
    			callback: function(resp, opts) {
    			    if(resp.error) {
    			    	resp.userdata.func(null, "error");
    			        return -1;
    			    }

    			    resp.userdata.func(resp.features, resp.userdata.attr);
    			}
    		});
    		resp.userdata={attr:data, func:getFunc};
    	},
    	modifyAssetCdFeatureByBJDCode: function(gac) {
    		var filter = new OpenLayers.Filter.Logical({
    			type: OpenLayers.Filter.Logical.AND,
    			filters: [
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.EQUAL_TO,
						property: "PRT_CD",
						value: gac.gisAssetsPrtAtCode
					}),
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.EQUAL_TO,
						property: "ASSETS_CD",
						value: gac.gisAssetsCd
					}),
					new OpenLayers.Filter.Comparison({
						type: OpenLayers.Filter.Comparison.EQUAL_TO,
						property: "ASSETS_SCD",
						value: gac.gisAssetsSubCd
					})
				]
    		});
    		EMD.lotareaProtocol.read({
    			filter: filter,
    			callback: function(resp) {
    			    if(resp.error) {
    			        // console.log('error');
    			        return -1;
    			    }

//
// EMD.userLayer.gisAssetsCd.addFeatures(resp.features);
// EMD.userLayer.gisAssetsCd.redraw();
//
// var bounds = resp.features[0].geometry.bounds.clone();
// for(var i=1; i<resp.features.length; i++) {
// bounds.extend(resp.features[i].geometry.bounds);
// }
//
// EMD.map.zoomToExtents(bounds, true);
    			}
    		});
    		EMD.protocols.gisAssetsCd.update(features, {
    			filter: new OpenLayers.Filter.Logical({
        			type: OpenLayers.Filter.Logical.AND,
        			filters: [
    					new OpenLayers.Filter.Comparison({
    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
    						property: "PRT_CD",
    						value: gisAssetsPrtAtCode
    					}),
    					new OpenLayers.Filter.Comparison({
    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
    						property: "ASSETS_CD",
    						value: gisAssetsCd
    					}),
    					new OpenLayers.Filter.Comparison({
    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
    						property: "ASSETS_SCD",
    						value: gisAssetsSubCd
    					})
    				]
        		}),
    			callback: function(resp) {
    			    if(resp.error) {
    			        // console.log('error');
    			        return -1;
    			    }
//
// EMD.userLayer.gisAssetsCd.addFeatures(resp.features);
// EMD.userLayer.gisAssetsCd.redraw();
//
// var bounds = resp.features[0].geometry.bounds.clone();
// for(var i=1; i<resp.features.length; i++) {
// bounds.extend(resp.features[i].geometry.bounds);
// }
//
// EMD.map.zoomToExtents(bounds, true);
    			}
// scope: new OpenLayers.Strategy.BBOX()
    		});
    	},
    	getBupjungDongNm: function(bjdCode, dataObj, callBackFunc) {
    		if(callBackFunc==null) return;
  			$.ajax({
  				url: EMD.context_root+'/cmmn/selectBupJungDongCodeNm.do',
  				type: 'POST',
  				userData: {callBack: callBackFunc, data:dataObj},
// module: input_item,
  				dataType: 'json',
				global: false,
  				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
  				data: [{name: 'cmd', value: bjdCode}],
				success: function (data) {
					if(data.resultCode!=null && data.resultCode!=0) {
						if(data.resultCode==1) {	// 로그인 에러
							alert(data.resultMsg);
							location.reload();
						}
					}
					else {
						data = $.extend(data, this.userData.data);
						if(this.userData.callBack!=null) { this.userData.callBack(data); }
					}
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
					try {
						// console.log(textStatus);
					} catch (e) {}
				}
  				});
  			},
  			removeFeature: function(layerName, obj) {
  				var layer=EMD.gis.getUserLayer(layerName);
				var i, feature;
				len = layer.features.length;
				var foundFeatures = [];
    			EMD.selectControl.unselectAll();
				for(i = 0; i < len; i++) {
					feature = layer.features[i];
					if(feature && feature.attributes) {
						if (EMD.gis.compareLayerObject(layer, feature, obj)) {
							foundFeatures.push(feature);
						}
					}
				}
				for(i=0; i<foundFeatures.length; i++) {
					feature=foundFeatures[i];
		            feature.state = OpenLayers.State.DELETE;
		            layer.removeFeature(feature);
				}
  			},
  			loadMapDataFlexGrid: function(module, layer, grid, code_id, value_id) {
  			},
  			loadMapDataCode: function(module, layer, code, value) {

  			},
  			clearMap:function(module, mapLayer) {
  				var layer=EMD.gis.getUserLayer(mapLayer);
  				EMD.gis.closeAllPopup(mapLayer);
  				layer.removeAllFeatures();
  			},
  			showStatusMapView:function(module, mapLayer, grid, mapStyle, value, labelField, selectFeature, getUserColor, getUserLabel) {
  				var g = module.$('#'+grid);
  				var rows = g.flexGetData();
  				var layer=EMD.gis.getUserLayer(mapLayer);
  				var target = EMD.userLayer.assetStats;
  				var ext=null;
  				var maxValue=0;
  				var foundFeature=[];
				len = layer.features.length;

				target.removeAllFeatures();
// target.addFeatures(layer.features);

				switch(mapStyle) {
				case 'user':
					EMD.gis.getStatsColor = module[getUserColor];
					EMD.gis.getStatsLabel = module[getUserLabel];
					break;
				case 'rate':
					EMD.gis.getStatsColor = function(feature) {
						if(feature['attributes']!=undefined) {
							var b=feature.attributes['value'];
							var hue = Math.floor((b/100) * 120 / 100);
							// var sat = Math.abs(b - 50)/50;
							return EMD.util.hsv2rgb(hue, 1, 1);
						}
						else return "#FFFFFF";
					};
					EMD.gis.getStatsLabel = function(feature) {
						if(feature['attributes']==undefined || feature.attributes['label']==undefined) return "";
					return feature.attributes['label'];
				};
					break;
				case 'value':
				default:
					EMD.gis.getStatsColor = function(feature) {
						if(feature['attributes']!=undefined) {
							var val=feature.attributes['value']||0;
							var maxVal=feature.attributes['max-value']||0;
							var b;
							if(maxVal!=0) {
								b=Math.round(val/maxVal*100);
							}
							else b=0;
							var hue = Math.floor((b) * 120 / 100);  // go from
																	// green to
																	// red
							return EMD.util.hsv2rgb(hue, 1, 1);
						}
						else return "#FFFFFF";
					};
					EMD.gis.getStatsLabel = function(feature) {
						if(feature['attributes']==undefined || feature.attributes['label']==undefined) return "";
						return feature.attributes['label'];
					};
					for(var j=0; j<rows.length; j++) {
						var d=rows[j][value];
						if(typeof d=="string") {
							d=d.replace(/,/g, "")*1;
						}
						if(d>maxValue) {
							maxValue=d;
						}
					}
					break;
				}

				for(var i = 0; i < len; i++) {
					feature = layer.features[i];
					if(feature && feature.attributes) {
						for(var j=0; j<rows.length; j++) {
							var r = rows[j];
							if (EMD.gis.compareLayerObject(layer, feature, r)) {
								var f = feature.clone();
								f.attributes['value']=r[value];
// f.attributes['PRT_CD']=r['gisAssetsPrtAtCodeFclty']||r['gisAssetsPrtAtCode'];
// f.attributes['ASSETS_CD']=r['gisAssetsCdFclty']||r['gisAssetsCd'];
// f.attributes['ASSETS_SCD']=r['gisAssetsSubCdFclty']||r['gisAssetsSubCd'];
								if(mapStyle!="rate") f.attributes['max-value']=maxValue;
								if(labelField!=undefined) {
									f.attributes['label']=r[labelField];
								}
								else {
									f.attributes['label']=r['gisAssetsNm'];
								}
								foundFeature[foundFeature.length] = f;

					          var point = new OpenLayers.Geometry.Point(feature.bounds.left, feature.bounds.bottom);
					          var minGeom = OpenLayers.Projection.transform(point, layer.projection, EMD.map.projection);
					          point = new OpenLayers.Geometry.Point(feature.bounds.right, feature.bounds.top);
					          var maxGeom = OpenLayers.Projection.transform(point, layer.projection, EMD.map.projection);
					          if(ext==null) {
					    			ext = new OpenLayers.Bounds();
					          }
					          ext.extend(minGeom);
					          ext.extend(maxGeom);
							}
						}
					}
				}
				if(foundFeature.length>0) {
					target.addFeatures(foundFeature);
					target.drawFeature(foundFeature);
					EMD.map.zoomToExtent(ext);

					target.events.remove('featureselected');
					target.events.register('featureselected', {module:module, selectFeature:selectFeature}, function(e) {
						if(this.selectFeature!=null && typeof module[selectFeature]=="function") {
							module[selectFeature](e);
						}
						else {
							EMD.gis.openPopup(e.feature, "/maps/assets/gamAssetCdInfo.do", EMD.util.objectToArray(e.feature.attributes));
						}

		            });
					target.events.remove('featureunselected');
					target.events.register('featureunselected', null, function(e) {
		                var feature = e.feature;
		                if(feature.popup!=null) {
		                    EMD.map.removePopup(feature.popup);
		                    feature.popup.destroy();
		                    feature.popup = null;
		                }
		            });
				}
				else {
					alert('조회된 영역이 존재 하지 않습니다.');
				}

  			},
  			/*
			 * 지도 조회
			 */
  			_showFeatureData: function(module, layerName, item) {
  				var layer=layerName;
				var i, feature;
	    		var ext=null;
				var foundFeatures = [];
				if((item.length!=undefined && item.length==0)||typeof item!="object") {
					alert('항목을 선택 하십시요.');
					return;
				};
				if(typeof layerName=="string") layer=EMD.gis.getUserLayer(layerName);
				len = layer.features.length;
// EMD.gis.selectFeatureData(module, layerName, row, true, true);
				var done=1;

				while(done) {
					if(item.length!=undefined) {
						for(i = 0; i < len; i++) {
							feature = layer.features[i];
							if(feature && feature.attributes) {
								for(var j=0; j<item.length; j++) {
									var r = item[j];
									if (EMD.gis.compareLayerObject(layer, feature, r)) {
										foundFeatures.push(feature);
								          EMD.selectControl.select(feature);
								          if(feature.bounds!=null) {
									          var point = new OpenLayers.Geometry.Point(feature.bounds.left, feature.bounds.bottom);
									          var minGeom = OpenLayers.Projection.transform(point, layer.projection, EMD.map.projection);
									          point = new OpenLayers.Geometry.Point(feature.bounds.right, feature.bounds.top);
									          var maxGeom = OpenLayers.Projection.transform(point, layer.projection, EMD.map.projection);
									          if(ext==null) {
									    			ext = new OpenLayers.Bounds(minGeom.x, minGeom.y, maxGeom.x, maxGeom.y);
									          }
									          ext.extend(minGeom);
									          ext.extend(maxGeom);
								          }
									}
								}
							}
						}
					}
					else {
						for(i = 0; i < len; i++) {
							feature = layer.features[i];
							if(feature && feature.attributes) {
								if (EMD.gis.compareLayerObject(layer, feature, item)) {
									foundFeatures.push(feature);
							          EMD.selectControl.select(feature);
							          if(feature.bounds!=null) {
								          var point = new OpenLayers.Geometry.Point(feature.bounds.left, feature.bounds.bottom);
								          var minGeom = OpenLayers.Projection.transform(point, layer.projection, EMD.map.projection);
								          point = new OpenLayers.Geometry.Point(feature.bounds.right, feature.bounds.top);
								          var maxGeom = OpenLayers.Projection.transform(point, layer.projection, EMD.map.projection);
								          if(ext==null) {
								    			ext = new OpenLayers.Bounds(minGeom.x, minGeom.y, maxGeom.x, maxGeom.y);
								          }
								          ext.extend(minGeom);
								          ext.extend(maxGeom);
							          }
								}
							}
						}
					}
					if(foundFeatures.length==0) {
						var pl=EMD.gis.getParentLayer(layer);
						if(pl!=null) {
							EMD.gis._showFeatureData(module, pl, item);
							return;
						}
						else {
							done=0;
						}
					}
					else {
						done=0;
				        EMD.map.zoomToExtent(ext);
// EMD.util.window_hide_all();
					}
				}
				if(foundFeatures.length==0) {
					alert('위치 데이터가 없습니다.');
				}
				else {
					EMD.selectControl.unselectAll();
// EMD.selectControl.select(foundFeatures);
				}
  			},
  			showMapDataFlexGrid: function(module, layerName, grid, code_id, value_id) {
  				var row = module.$('#'+grid).selectedRows();
  				EMD.gis._showFeatureData(module, layerName, row);
  			},
  			showMapDataCode: function(module, layerName, code) {
  	  				var row = module[code];
  	  				EMD.gis._showFeatureData(module, layerName, row);
  			},
  			/**
			 * 레이어를 편집한다.
			 *
			 * @param module
			 * @param layerName
			 * @returns
			 */
  			editLayer: function(module, layerName, retfunc) {
  				var layer=EMD.gis.getUserLayer(layerName);
  				if(layer==null) {
  					alert('편집 할 수 없는 지도 레이어 입니다.');
  					return;
  				}
  				var virtual = {
  					    strokeColor: "#00ff00",
  					    fillColor: "#00ff00",
  					    strokeOpacity: 1,
  					    strokeWidth: 2,
  					    pointRadius: 5,
  					    graphicName: "cross"
  					};
  				EMD._currentEditLayer = layer;
    			EMD.editControls={
	                addPoint: new OpenLayers.Control.DrawFeature(layer,
                        OpenLayers.Handler.Point, {
						featureAdded: function(f) {
// console.log('add point');
							EMD.gis.storeLayerObject(this.layer, f, null);
							if(this.layer!=f.layer)
								this.layer.addFeatures([f]);
// this.layer.redraw();
						}
	                }),
	                addLine: new OpenLayers.Control.DrawFeature(layer,
                        OpenLayers.Handler.Path, {
							featureAdded: function(f) {
// console.log('add line');
								EMD.gis.storeLayerObject(this.layer, f, null);
								if(this.layer!=f.layer)
									this.layer.addFeatures([f]);
								// this.layer.addFeatures([f]);
						}
	                }),
	                addFeature: new OpenLayers.Control.DynamicMeasure(OpenLayers.Handler.Polygon, {
						drawingLayer: layer,
						handlerOptions: {holeModifier: "altKey"},
						featureAdded: function(f) {
// console.log('add feature');
							EMD.gis.storeLayerObject(this.drawingLayer, f, null);
							if(this.drawingLayer!=f.layer)
								this.drawingLayer.addFeatures([f]);
							// this.drawingLayer.addFeatures([f]);
						}
		            }),
	        		editFeature: new OpenLayers.Control.ModifyFeature(layer,{
	            			vertexRenderIntent: 'modify',
	            			virtualStyle: virtual,
	            			deleteCodes: [46, 68],
							mode: OpenLayers.Control.ModifyFeature.RESHAPE|OpenLayers.Control.ModifyFeature.DRAG,
							handlerOptions: {holeModifier: "altKey"}
	          	    }),
// editShapeFeature: new OpenLayers.Control.ModifyFeature(layer,{
// vertexRenderIntent: 'modify',
// virtualStyle: virtual,
// mode: OpenLayers.Control.ModifyFeature.RESHAPE,
// handlerOptions: {holeModifier: "altKey"}
// }),
          	    	delFeature: new DeleteFeature(layer, {title: "삭제"})

	    			};
	                for(var key in EMD.editControls) {
	                	EMD.map.addControl(EMD.editControls[key]);
	                }

		            // show edit panel
		    		var addPointBtn = new OpenLayers.Control.Button({
		    			title: "점 추가",
		    			displayClass: "olControlEditAddPoint",
		    			type: OpenLayers.Control.TYPE_TOGGLE,
		    			eventListeners: {
			    			activate: function() {
			    				for(var key in EMD.editControls) {
			    					if(key!='addPoint') EMD.editControls[key].button.deactivate();
			    				}
			    				EMD.editControls['addPoint'].activate();
			    			},
			    			deactivate: function() {
			    				for(var key in EMD.editControls) {
			    					var control=EMD.editControls[key];
			    					if(key=='addPoint') control.deactivate();
			    				}
			    			}
		    			}
		    		});
		    		EMD.editControls.addPoint.button=addPointBtn;
		    		var addLineBtn = new OpenLayers.Control.Button({
		    			title: "선 추가",
		    			displayClass: "olControlEditAddLine",
		    			type: OpenLayers.Control.TYPE_TOGGLE,
		    			eventListeners: {
			    			activate: function() {
			    				for(var key in EMD.editControls) {
			    					if(key!='addLine') EMD.editControls[key].button.deactivate();
			    				}
			    				EMD.editControls['addLine'].activate();
			    			},
			    			deactivate: function() {
			    				for(var key in EMD.editControls) {
			    					var control=EMD.editControls[key];
			    					if(key=='addLine') control.deactivate();
			    				}
			    			}
		    			}
		    		});
		    		EMD.editControls.addLine.button=addLineBtn;
		    		var addButton = new OpenLayers.Control.Button({
		    			title: "추가",
		    			displayClass: "olControlEditAdd",
		    			type: OpenLayers.Control.TYPE_TOGGLE,
		    			eventListeners: {
			    			activate: function() {
			    				for(var key in EMD.editControls) {
			    					if(key!='addFeature') EMD.editControls[key].button.deactivate();
			    				}
			    				EMD.editControls['addFeature'].activate();
			    			},
			    			deactivate: function() {
			    				for(var key in EMD.editControls) {
			    					var control=EMD.editControls[key];
			    					if(key=='addFeature') control.deactivate();
			    				}
			    			}
		    			}
		    		});
		    		EMD.editControls.addFeature.button=addButton;
		    		var editButton = new OpenLayers.Control.Button({
		    			title: "편집",
		    			displayClass: "olControlEditEdit",
		    			type: OpenLayers.Control.TYPE_TOGGLE,
		    			eventListeners: {
			    			activate: function() {
			    				for(var key in EMD.editControls) {
			    					if(key!='editFeature') EMD.editControls[key].button.deactivate();
			    				}
			    				EMD.editControls['editFeature'].activate();
			    			},
			    			deactivate: function() {
			    				for(var key in EMD.editControls) {
			    					var control=EMD.editControls[key];
			    					if(key=='editFeature') control.deactivate();
			    				}
			    			}
		    			}
		    		});
		    		EMD.editControls.editFeature.button=editButton;
		    		var delButton = new OpenLayers.Control.Button({
		    			title: "삭제",
		    			displayClass: "olControlEditDelete",
		    			type: OpenLayers.Control.TYPE_TOGGLE,
		    			eventListeners: {
			    			activate: function() {
			    				for(var key in EMD.editControls) {
			    					if(key!='delFeature') EMD.editControls[key].button.deactivate();
			    				}
			    				EMD.editControls['delFeature'].activate();
			    			},
			    			deactivate: function() {
			    				for(var key in EMD.editControls) {
			    					var control=EMD.editControls[key];
			    					if(key=='delFeature') control.deactivate();
			    				}
			    			}
		    			}
		    		});
		    		EMD.editControls.delFeature.button=delButton;
		    		EMD.save = new OpenLayers.Control.Button({
		    			title: "편집 완료",
		    			module: module,
		    			retfunc: retfunc,
		    			layer: layer,
		    			trigger: function() {
		    				EMD.panel.deactivate();
		    				EMD.map.removeControl(EMD.panel);
		    				for(var key in EMD.editControls) {
		    					EMD.editControls[key].deactivate();
		    				}

// for(var i=0; i<this.layer.features.length; i++) {
// var feature=this.layer.features[i];
// if(feature.state==OpenLayers.state.INSERT) {
// var polygon= feature.geometry;
// feature.geometry = polygon.transform(
// this.layer.projection,EMD.map.getProjection()
// );
// }
// }

		    				EMD.selectControl.activate();
		    				// 레이어를 저장한다.
		    				this.layer.saveStrategy.save();

		    				// EMD.gis.storeLayer(EMD._currentEditLayer);

		    				// 삽입한 피처의 프로젝션을 지도의 프로젝션으로 변환한다.
// for(var i=0; i<this.layer.features.length; i++) {
// var feature=this.layer.features[i];
// if(feature.state==OpenLayers.State.INSERT) {
// var polygon= feature.geometry;
// feature.geometry = polygon.transform(
// this.layer.projection,EMD.map.getProjection()
// );
// }
// }

		    				module.showWindow();
		    			},
		    			displayClass: "olControlEditDone"
		    		});

		    		EMD.cancel = new OpenLayers.Control.Button({
		    			title: "편집 취소",
		    			layer: layer,
		    			trigger: function() {
		    				EMD.panel.deactivate();
		    				EMD.map.removeControl(EMD.panel);
		    				for(var key in EMD.editControls) {
		    					EMD.editControls[key].deactivate();
		    				}
// EMD.gis.selectFeatureData(module, layerName, module[code], true, true); // 취소
// 되었으므로 다시 로드 한다.
		    				EMD.selectControl.activate();

		    				this.layer.refreshStrategy.refresh();
// EMD.gis.refreshLayer(EMD._currentEditLayer);
			    			module.showWindow();
		    			},
		    			displayClass: "olControlEditCancel"
		    		});

		    		if(EMD.panel!=null) {
		    			EMD.panel.deactivate();
		    		}
		    		EMD.panel = new OpenLayers.Control.Panel({
		    			displayClass: 'mapEditToolBar'
// allowDepress: true
		    		});

		    		EMD.selectControl.deactivate();		// 객체 정보를 표시 하지 않도록
														// 변경한다.

		    		EMD.panel.addControls([addPointBtn, addLineBtn, addButton, editButton, delButton, EMD.save, EMD.cancel]);
		    		EMD.map.addControl(EMD.panel);
		    		EMD.panel.activate();
  			},
  			addFeatureCode: function(module, layerName, code, value) {
  				var layer=EMD.gis.getUserLayer(layerName);
  				var row=module[code];
				var i, feature;
				len = layer.features.length;
				var foundFeatures = [];

				if(row==undefined || !EMD.gis.checkLayerObject(layer, row)) {
					alert('속성이 정의 되지 않아 편집이 가능하지 않습니다.');
					return;
				}

    			EMD.selectControl.unselectAll();

				EMD.util.window_hide_all();

				if(row['_feature']==undefined) {
					for(i = 0; i < len; i++) {
						feature = layer.features[i];
						if(feature && feature.attributes) {
							if (EMD.gis.compareLayerObject(layer, feature, row)) {
								foundFeatures.push(feature);
								break;
							}
						}
					}
					if(foundFeatures.length==0) {// new feature
					}
					else {	// edit feature
			    		var ext;
			    		var modifyFeatures = [];
// var attributes = modifyFeatures[0].attributes.clone();
		    			ext = foundFeatures[0].geometry.getBounds().clone();
		    			modifyFeatures[modifyFeatures.length] = foundFeatures[0].clone();
		    			for(var i=1;i<foundFeatures.length;i++) {
		    				ext.extend(foundFeatures[i].geometry.getBounds());
		    				modifyFeatures[modifyFeatures.length] = foundFeatures[i].clone();
		    			}
		    			EMD.map.zoomToExtent(ext);

// EMD.wfs.removeAllFeatures();
// EMD.wfs.addFeatures(modifyFeatures);
// layer.removeFeatures(foundFeatures);
					}
	    			EMD.editControls={
			                  addFeature: new OpenLayers.Control.DynamicMeasure(OpenLayers.Handler.Polygon, {
									drawingLayer: EMD.wfs,
									handlerOptions: {holeModifier: "altKey"}
			            }),
			            		editFeature: new OpenLayers.Control.ModifyFeature(EMD.wfs,
		            	    {
							mode: OpenLayers.Control.ModifyFeature.RESHAPE,
							handlerOptions: {holeModifier: "altKey"}
	            	    }),
	            	    	delFeature: new DeleteFeature(EMD.wfs, {title: "삭제"})

		    			};
		                for(var key in EMD.editControls) {
		                	EMD.map.addControl(EMD.editControls[key]);
		                }

			            // show edit panel
			    		var addButton = new OpenLayers.Control.Button({
			    			title: "추가",
			    			displayClass: "olControlEditAdd",
			    			type: OpenLayers.Control.TYPE_TOGGLE,
			    			eventListeners: {
				    			activate: function() {
				    				for(var key in EMD.editControls) {
				    					var control=EMD.editControls[key];
				    					if(key=='addFeature') control.activate();
				    					else control.deactivate();
				    				}
				    			},
				    			deactivate: function() {
				    				for(var key in EMD.editControls) {
				    					EMD.editControls[key].deactivate();
				    				}
				    			}
			    			}
			    		});
			    		var editButton = new OpenLayers.Control.Button({
			    			title: "편집",
			    			displayClass: "olControlEditEdit",
			    			type: OpenLayers.Control.TYPE_TOGGLE,
			    			eventListeners: {
				    			activate: function() {
				    				for(var key in EMD.editControls) {
				    					var control=EMD.editControls[key];
				    					if(key=='editFeature') control.activate();
				    					else control.deactivate();
				    				}
				    			},
				    			deactivate: function() {
				    				for(var key in EMD.editControls) {
				    					EMD.editControls[key].deactivate();
				    				}
				    			}
			    			}
			    		});
			    		var delButton = new OpenLayers.Control.Button({
			    			title: "삭제",
			    			displayClass: "olControlEditDelete",
			    			type: OpenLayers.Control.TYPE_TOGGLE,
			    			eventListeners: {
				    			activate: function() {
				    				for(var key in EMD.editControls) {
				    					var control=EMD.editControls[key];
				    					if(key=='delFeature') control.activate();
				    					else control.deactivate();
				    				}
				    			},
				    			deactivate: function() {
				    				for(var key in EMD.editControls) {
				    					EMD.editControls[key].deactivate();
				    				}
				    			}
			    			}
			    		});
			    		EMD.save = new OpenLayers.Control.Button({
			    			title: "편집 완료",
			    			trigger: function() {
			    				EMD.panel.deactivate();
			    				EMD.map.removeControl(EMD.panel);
			    				for(var key in EMD.editControls) {
			    					EMD.editControls[key].deactivate();
			    				}
			    				var polygon;
			    				if(EMD.wfs.features.length>1) {
			    					var g=[];
			    					for(var i=0; i<EMD.wfs.features.length; i++) {
			    						g[i]=EMD.wfs.features[i].geometry.clone();
			    					}
			    					polygon = new OpenLayers.Geometry.MultiPolygon(g);
			    				}
			    				else {
			    					polygon = new OpenLayers.Geometry.Polygon(EMD.wfs.features[0].geometry.clone());
			    				}
			    		        f = new OpenLayers.Feature.Vector(polygon);
			    		        f.attributes = modifyFeatures[0].attributes;
// EMD.gis.storeLayerObject(EMD.userLayer.assetsRentDetail, f,
// assetsRentDetail);
// EMD.protocols.assetsRentDetail.update(f);
			    		        f.state = OpenLayers.State.UPDATE;
			    		        layer.addFeatures([f]);
// EMD.userLayer.assetsRentDetail.drawFeature(f);

// EMD.saveStrategy.save();
			    				EMD.selectControl.activate();

			    				module.showWindow();
			    			},
			    			displayClass: "olControlEditDone"
			    		});

			    		EMD.cancel = new OpenLayers.Control.Button({
			    			title: "편집 취소",
			    			trigger: function() {
			    				EMD.panel.deactivate();
			    				EMD.map.removeControl(EMD.panel);
			    				for(var key in EMD.editControls) {
			    					EMD.editControls[key].deactivate();
			    				}
			    				EMD.gis.selectFeatureData(module, layerName, module[code], true, true);	// 취소
																										// 되었으므로
																										// 다시
																										// 로드
																										// 한다.
			    				EMD.selectControl.activate();
				    			module.showWindow();
			    			},
			    			displayClass: "olControlEditCancel"
			    		});

			    		if(EMD.panel!=null) {
			    			EMD.panel.deactivate();
			    		}
			    		EMD.panel = new OpenLayers.Control.Panel({
			    			displayClass: 'mapEditToolBar'
// allowDepress: true
			    		});

			    		EMD.selectControl.deactivate();

			    		EMD.panel.addControls([addButton, editButton, delButton, EMD.save, EMD.cancel]);
			    		EMD.map.addControl(EMD.panel);
			    		EMD.panel.activate();

// EMD.editPanel = new OpenLayers.Control.Panel({displayClass: 'editPanel'});
// editPanel.addControls([
// new OpenLayers.Control.DrawFeature(vectorLayer, OpenLayers.Handler.Point,
// {displayClass: 'pointButton', title: 'Add point', handlerOptions: {style:
// sty}}),
// new OpenLayers.Control.DrawFeature(vectorLayer, OpenLayers.Handler.Path,
// {displayClass: 'lineButton', title: 'Draw line', handlerOptions: {style:
// sty}}),
// new OpenLayers.Control.ModifyFeature(vectorLayer, {title: 'Edit feature'}),
// new DeleteFeature(vectorLayer, {title: 'Delete Feature'}),
// new OpenLayers.Control.Split({ layer: vectorLayer, deferDelete: true, title:
// 'Split line' }),
// new OpenLayers.Control.Button({displayClass: 'saveButton', trigger:
// function() {saveStrategy.save()}, title: 'Save changes' }),
// navControl
// ]);
				}
				else {
					EMD.modifyFeature=new OpenLayers.Control.ModifyFeature(layer,
		            	    {
								mode: OpenLayers.Control.ModifyFeature.RESHAPE,
								handlerOptions: {holeModifier: "altKey"},
								afterfeaturemodified: function(feature) {
									layer.addFeatures(feature);
									layer.redraw();
									module[code]['_feature']=feature;
									EMD.modifyFeature.deactivate();
									module.showWindow();
	// EMD.saveStrategy.save();
								}
							});
		            EMD.map.addControl(EMD.modifyFeature);
		            EMD.modifyFeature.activate();
		            EMD.modifyFeature.selectFeature(row['_feature']);

		            // show edit panel
		    		var addButton = new OpenLayers.Control.Button({
		    			title: "추가",
		    			trigger: function() {
		    				EMD.panel.deactivate();
			    			EMD.modifyFeature.deactivate();
		    				EMD.map.removeControl(EMD.panel);
			    			layer.addFeatures(EMD.wfs.features);
		    				EMD.saveStrategy.save();
		    				module.showWindow();
		    			},
		    			displayClass: "olControlEditAdd"
		    		});
		    		var editButton = new OpenLayers.Control.Button({
		    			title: "편집",
		    			trigger: function() {
		    				EMD.panel.deactivate();
			    			EMD.modifyFeature.deactivate();
		    				EMD.map.removeControl(EMD.panel);
			    			layer.addFeatures(EMD.wfs.features);
		    				EMD.saveStrategy.save();
		    				module.showWindow();
		    			},
		    			displayClass: "olControlEditEdit"
		    		});
		    		var delButton = new OpenLayers.Control.Button({
		    			title: "삭제",
		    			trigger: function() {
		    				EMD.panel.deactivate();
			    			EMD.modifyFeature.deactivate();
		    				EMD.map.removeControl(EMD.panel);
			    			layer.addFeatures(EMD.wfs.features);
		    				EMD.saveStrategy.save();
		    				module.showWindow();
		    			},
		    			displayClass: "olControlEditDel"
		    		});
		    		EMD.save = new OpenLayers.Control.Button({
		    			title: "편집 완료",
		    			trigger: function() {
		    				EMD.panel.deactivate();
			    			EMD.modifyFeature.deactivate();
		    				EMD.map.removeControl(EMD.panel);
			    			layer.addFeatures(EMD.wfs.features);
		    				EMD.saveStrategy.save();
		    				module.showWindow();
		    			},
		    			displayClass: "olControlEditDone"
		    		});

		    		EMD.cancel = new OpenLayers.Control.Button({
		    			title: "편집 취소",
		    			trigger: function() {
		    				EMD.panel.deactivate();
			    			EMD.modifyFeature.deactivate();
		    				EMD.map.removeControl(EMD.panel);
		    				EMD.gis.selectFeatureData(module, layerName, module[code], true, true);	// 다시
																									// 로드
																									// 한다.
			    			module.showWindow();
		    			},
		    			displayClass: "olControlEditCancel"
		    		});

		    		if(EMD.panel!=null) {
		    			EMD.panel.deactivate();
		    		}
		    		EMD.panel = new OpenLayers.Control.Panel({
		    			displayClass: 'mapEditToolBar'
// allowDepress: true
		    		});

		    		EMD.panel.addControls([addButton, editButton, delButton, EMD.save, EMD.cancel]);
				}
		},
		removeFeatureCode: function(module, layerName, code) {
			var layer=EMD.gis.getUserLayer(layerName);
			var row=module[code];
			var i, feature;
			len = layer.features.length;
			var foundFeatures = [];

			EMD.selectControl.unselectAll();

			for(i = 0; i < len; i++) {
				feature = layer.features[i];
				if(feature && feature.attributes) {
					if (EMD.gis.compareLayerObject(layer, feature, row)) {
						foundFeatures.push(feature);
						break;
					}
				}
			}
			if(foundFeatures.length==0) {
				return false;
			}
			else {
	    		var ext;
	    		if (foundFeatures.length>0) {
	    			for(var i=0; i<foundFeatures.length; i++) {
	    				if(foundFeatures[i].state!=OpenLayers.State.INSERT) {
	    					foundFeatures[i].state=OpenLayers.State.DELETE;
	    				}
	    				layer.removeFeatures(foundFeatures);
    	    			EMD.saveStrategy.save();
	    			}
	    			layer.redraw();
	    		}
			}
			return true;
		},
		/**
		 * 피처의 속성을 수정 한다.
		 *
		 * @param module
		 * @param layerName
		 * @param value
		 * @param newfeature
		 * @returns
		 */
		modifyFeatureAttr: function(module, layerName, value, newfeature) {
		},
		/**
		 * 피처코드의 속성을 저장한다.
		 *
		 * @param module
		 * @param layerName
		 * @param value
		 * @param newfeature
		 * @returns
		 */
		setFeatureCode: function(module, layerName, value, feature) {
			var layer=EMD.gis.getUserLayer(layerName);
			EMD.gis.storeLayerObject(layer, feature, value);
			EMD.map.removePopup(feature.popup);
			layer.saveStrategy.save();
			/*
			 * var protocol = EMD.gis.getLayerProtocol(layerName); //
			 * layer.redraw(); var polygon= feature.geometry.clone();
			 *
			 * feature.geometry = polygon.transform( EMD.map.getProjection(),
			 * new OpenLayers.Projection("EPSG:5186") );
			 * EMD.gis.storeLayerObject(layer, feature, value);
			 * if(feature.state!=OpenLayers.State.INSERT)
			 * feature.state=OpenLayers.State.UPDATE; var
			 * resp=protocol.commit([feature], { layer: layer,
			 * callback:function(resp) { if(resp.error) {
			 * //console.log('error'); return -1; } var baseProj = new
			 * OpenLayers.Projection("EPSG:5186"); var mapProj =
			 * EMD.map.getProjection(); EMD.selectControl.unselectAll(); var
			 * features=[]; // 피처의 좌표계를 지도의 좌표계로 변경한다. $.each(resp.reqFeatures,
			 * function() { if(this.state==OpenLayers.State.INSERT ||
			 * this.state==OpenLayers.State.UPDATE) { var polygon =
			 * this.geometry; this.geometry =
			 * polygon.transform(baseProj,mapProj); this.state="";
			 * features[features.length]=this; } }); // resp.layer.redraw(); //
			 * resp.layer.drawFeature(features);
			 *  } }); resp.layer=layer;
			 */
		},
		/**
		 * 피처코드 변경
		 *
		 * @param module
		 * @param layerName
		 * @param value
		 * @param newfeature
		 * @returns
		 */
		modifyFeatureCode: function(module, layerName, value, newfeature) {
  				var layer=EMD.gis.getUserLayer(layerName);
				var i, feature;
				len = layer.features.length;
				var foundFeatures = [];

				for(i = 0; i < len; i++) {
					feature = layer.features[i];
					if(feature && feature.attributes) {
						if (EMD.gis.compareLayerObject(layer, feature, value)) {
							foundFeatures.push(feature);
							break;
						}
					}
				}
				if(foundFeatures.length==0) {
					if(newfeature!=undefined) {
						var polygon= newfeature.geometry;
						newfeature.geometry = polygon.transform(
								newfeature.layer.projection,EMD.map.getProjection()
						);
						EMD.gis.storeLayerObject(layer, newfeature, value);
						newfeature.state=OpenLayers.State.INSERT;
						layer.addFeatures([newfeature]);
						layer.redraw();

						EMD.protocols.gisAssetsCd.create(newfeature);
						EMD.protocols.gisAssetsCd.commit();
						EMD.saveLayer.gisAssetsCd.save();
					}
					else {
						console.log('[ERROR] Feature not found.')
					}
				}
				else {
		    		if (foundFeatures.length>0) {
		    			if(newfeature!=undefined) {	// if feature modify
							var polygon= newfeature.geometry;
							foundFeatures[0].geometry = polygon.transform(
									newfeature.layer.projection,EMD.map.getProjection()
					          );
							layer.addFeatures(foundFeatures);
		    			}
		    			foundFeatures[0].state=OpenLayers.State.UPDATE;
//		    			console.log(foundFeatures);
		    			// $.extend(foundFeatures[0].attributes, value);
		    			foundFeatures[0].attributes['ASSETS_SE_CD']=value.gisAssetsSeCd;
		    			foundFeatures[0].attributes['ASSETS_NM']=value.gisAssetsNm;
		    			foundFeatures[0].attributes['AR']=value.gisAssetsAr;
		    			layer.drawFeature(foundFeatures[0]);
		    			// layer.redraw();
				        EMD.protocols.gisAssetsCd.update(foundFeatures, {
			    			filter: new OpenLayers.Filter.Logical({
			        			type: OpenLayers.Filter.Logical.AND,
			        			filters: [
			    					new OpenLayers.Filter.Comparison({
			    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
			    						property: "PRT_CD",
			    						value: foundFeatures[0].attributes.PRT_CD
			    					}),
			    					new OpenLayers.Filter.Comparison({
			    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
			    						property: "ASSETS_CD",
			    						value: foundFeatures[0].attributes.PRT_CD
			    					}),
			    					new OpenLayers.Filter.Comparison({
			    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
			    						property: "ASSETS_SCD",
			    						value: foundFeatures[0].attributes.PRT_CD
			    					})
			    				]
			        		}),
			    			callback: function(resp) {
			    			    if(resp.error) {
			    			        // console.log('error');
			    			        return -1;
			    			    }
			    			}
				        });
						EMD.saveLayer.gisAssetsCd.save(foundFeatures);
		    		}
				}
		},
		changeFeatureAttribute: function(module, layerName, oldattr, newattr) {
				var layer=EMD.gis.getUserLayer(layerName);
				var i, feature;
				len = layer.features.length;
				var changed=false;

				for(i = 0; i < len; i++) {
					feature = layer.features[i];
					if(feature && feature.attributes) {
						if (EMD.gis.compareLayerObject(layer, feature, oldattr)) {
							EMD.gis.storeLayerObject(layer, feature, newattr);
							layer.drawFeature(feature);
//							EMD.gis.storeLayerObject(layer, feature, newattr);
							changed=true;
						}
					}
				}
				if(changed) {
//					EMD.saveLayer.gisAssetsCd.save();
					EMD.saveStrategy.save();
				}
		},
		addFeatureFlexGrid: function(module, layerName, grid, code_id, value_id) {
  				var row = module.$('#'+grid).selectedRows();
  				var layer=EMD.gis.getUserLayer(layerName);
				var i, feature;
				len = layer.features.length;
				var foundFeatures = [];
				if(row.length==0) {
					alert('항목을 선택 하십시요.');
					return;
				};
    			EMD.selectControl.unselectAll();
				for(i = 0; i < len; i++) {
					feature = layer.features[i];
					if(feature && feature.attributes) {
						for(var j=0; j<row.length; j++) {
							var r = row[j];
							if (EMD.gis.compareLayerObject(layer, feature, r)) {
								foundFeatures.push(feature);
							}
						}
					}
				}
				EMD.util.window_hide_all();
				if(foundFeatures.length==0) {
		            EMD.map.addControl(new OpenLayers.Control.DrawFeature(EMD.userLayer.gisAssetsCd, OpenLayers.Handler.Polygon,
		            	    {handlerOptions: {holeModifier: "altKey"}, featureAdded: function(feature) {
		            	    	EMD.gis.storeLayerObject(layer, feature, row[0]);
		    	    			EMD.userLayer.gisAssetsCd.addFeatures([feature]);
		    	    			EMD.saveStrategy.save();
		            	    }}));
				}
				else {
// var updatefeature=foundFeatures[0].clone();
// EMD.wfs.addFeatures([updatefeature]);
					var ext;
					ext = foundFeatures[0].geometry.getBounds().clone();
					for(var i=1;i<foundFeatures.length;i++) {
						ext.extend(foundFeatures[i].geometry.getBounds());
					}
					EMD.map.zoomToExtent(ext);
					EMD.modifyFeature=new OpenLayers.Control.ModifyFeature(EMD.userLayer.gisAssetsCd,
							{handlerOptions: {holeModifier: "altKey"}, featureAdded: function(feature) {
								EMD.gis.storeLayerObject(layer, feature, row[0]);
								EMD.userLayer.gisAssetsCd.addFeatures(e.feature);
								EMD.saveStrategy.save();
							}});
					EMD.map.addControl(modifyFeature);
					modifyFeature.selectFeature(foundFeatures[0]);
				}
  			},
  			removeFeatureFlexGrid: function(module, layerName, grid, code_id, value_id) {
  				var row = module.$('#'+grid).selectedRows();
  				var layer=EMD.gis.getUserLayer(layerName);
				var i, feature;
				len = layer.features.length;
				var foundFeatures = [];
				if(row.length==0) {
					alert('항목을 선택 하십시요.');
					return;
				};
				for(i = 0; i < len; i++) {
					feature = layer.features[i];
					if(feature && feature.attributes) {
						for(var j=0; j<row.length; j++) {
							var r = row[j];
							if (EMD.gis.compareLayerObject(layer, feature, r)) {
								foundFeatures.push(feature);
							}
						}
					}
				}
				if(foundFeatures.length==0) {
					return false;
				}
				else {
					layer.removeFeatures(e.feature);
					EMD.saveStrategy.save();
				}
				return true;
  			},
  			readFeature: function(module, layerName, obj, _callback) {
  				var layer=EMD.gis.getUserLayer(layerName);
  				if(layer==EMD.userLayer.assetsRent) {
  					var resp=EMD.protocols.assetsRent.read({
		    			filter: new OpenLayers.Filter.Logical({
		        			type: OpenLayers.Filter.Logical.AND,
		        			filters: [
		    					new OpenLayers.Filter.Comparison({
		    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
		    						property: "PRT_AT_CD",
		    						value: obj['prtAtCode']
		    					}),
		    					new OpenLayers.Filter.Comparison({
		    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
		    						property: "MNG_YEAR",
		    						value: obj['mngYear']
		    					}),
		    					new OpenLayers.Filter.Comparison({
		    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
		    						property: "MNG_NO",
		    						value: obj['mngNo']
		    					}),
		    					new OpenLayers.Filter.Comparison({
		    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
		    						property: "MNG_CNT",
		    						value: obj['mngCnt']
		    					})
		    				]
		        		}),
						callback: _callback(response)
  					});
  				}
	    		resp.data={obj:obj};
	    		return resp;
  			},
  			selectAssetCdToAssetRentDetailFeature: function(module, assetsRentDetail, func) {
  				var f;
				var assetCdLayer=EMD.userLayer.gisAssetsCd;
				var i, feature;
				len = assetCdLayer.features.length;

				for(i = 0; i < len; i++) {
					feature = assetCdLayer.features[i];
					if(feature && feature.attributes) {
						if (EMD.gis.compareLayerObject(assetCdLayer, feature, assetsRentDetail)) {
	    			        f = new OpenLayers.Feature();
	    			        f.geometry = feature.geometry.clone();
	    			        f.attributes = {};
	    			        EMD.gis.storeLayerObject(EMD.userLayer.assetsRentDetail, f, assetsRentDetail);
	    			        EMD.protocols.assetsRentDetail.update(f);
	    			        f.state = OpenLayers.State.UPDATE;
	    			        EMD.userLayer.assetsRentDetail.addFeatures([f]);
	    			        EMD.userLayer.assetsRentDetail.drawFeature(f);
	    			        func(module, f);
	    			        return;
						}
					}
				}
				// console.log("cannot found assetCd.");
		        f = new OpenLayers.Feature();
				var polygon= new OpenLayers.Geometry.Polygon();

		        f.geometry = new OpenLayers.Feature.Vector(polygon);
		        f.attributes = {};
		        EMD.gis.storeLayerObject(EMD.userLayer.assetsRentDetail, f, assetsRentDetail);
		        EMD.protocols.assetsRentDetail.update(f);
		        f.state = OpenLayers.State.UPDATE;
		        EMD.userLayer.assetsRentDetail.addFeatures([f]);
		        EMD.userLayer.assetsRentDetail.drawFeature(f);
		        func(module, f);
  			},
  			selectAssetRentDetail: function(assetsRentDetail, f) {
				var resp=EMD.protocols.assetsRentDetail.read({
	    			filter: new OpenLayers.Filter.Logical({
	        			type: OpenLayers.Filter.Logical.AND,
	        			filters: [
	    					new OpenLayers.Filter.Comparison({
	    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
	    						property: "PRT_AT_CD",
	    						value: assetsRentDetail['prtAtCode']
	    					}),
	    					new OpenLayers.Filter.Comparison({
	    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
	    						property: "MNG_YEAR",
	    						value: assetsRentDetail['mngYear']
	    					}),
	    					new OpenLayers.Filter.Comparison({
	    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
	    						property: "MNG_NO",
	    						value: assetsRentDetail['mngNo']
	    					}),
	    					new OpenLayers.Filter.Comparison({
	    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
	    						property: "MNG_CNT",
	    						value: assetsRentDetail['mngCnt']
	    					}),
	    					new OpenLayers.Filter.Comparison({
	    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
	    						property: "ASSETS_USAGE_SEQ",
	    						value: assetsRentDetail['assetsUsageSeq']
	    					})
	    				]
	        		}),
					callback: function(resp) {
						var ext;
						if(resp.features==undefined || resp.features.length==0) {
							alert('정의된 GIS 영역이 없습니다.');
// resp.data.f = new OpenLayers.Feature();
// resp.data.f.geometry = resp.data.src.geometry.clone();
// EMD.gis.storeLayerObject(EMD.userLayer.assetsRentDetail, resp.data.f,
// resp.data.obj);
// EMD.protocols.assetsRentDetail.update(resp.data.f);
// resp.data.f.state = OpenLayers.State.UPDATE;
						}
						else {
							resp.data.src = resp.features[0].clone();
						}
// ext = resp.features[0].geometry.getBounds().clone();
// for(var i=1;i<resp.features.length;i++) {
// ext.extend(resp.features[i].geometry.getBounds());
// }
// resp.data.obj.geometry = resp.features[0].geometry.clone();
// EMD.map.zoomToExtent(ext);
// EMD.saveStrategy.save();
					}
				});
	    		resp.data={obj:assetsRentDetail, src: f};
			},
			modifyAssetRentDetailFeature: function(module, assetsRentDetail, feature, callback) {
				if(feature.geometry.components==undefined || feature.geometry.components.length==0) {
					alert('위치를 클릭하여 영역을 지정합니다.');
					EMD.draw = new OpenLayers.Control.DrawFeature(EMD.userLayer.assetsRentDetail,
							OpenLayers.Handler.PathMeasureCustom,
		    				{
		    					title: "추가",
		    					displayClass: "olControlDrawFeaturePolygon",
		    					multi: true
		    				});
// EMD.edit = new
// OpenLayers.Control.ModifyFeature(EMD.userLayer.assetsRentDetail, {
// title: "수정",
// displayClass: "olControlModifyFeature"
// });
// EMD.del = new DeleteFeature(EMD.userLayer.assetsRentDetail, {title: "삭제"});
		    		EMD.save = new OpenLayers.Control.Button({
		    			title: "저장",
		    			trigger: function() {
		    				EMD.panel.removeControl(EMD.save);
		    				callback(module, EMD.draw.feature);
			    			EMD.draw.deactivate();
		    			},
		    			displayClass: "olControlSaveFeatures"
		    		});

		    		EMD.cancel = new OpenLayers.Control.Button({
		    			title: "취소",
		    			trigger: function() {
		    				EMD.panel.removeControl(EMD.save);
			    			EMD.map.addControl(EMD.draw);
			    			EMD.draw.deactivate();
		    				callback(module, null);
		    			},
		    			displayClass: "olControlSaveFeatures"
		    		});

	    			EMD.map.addControl(EMD.draw);

	    			EMD.draw.activate();

					EMD.panel.addControls([EMD.save, EMD.cancel]);
				}
				else {
// EMD.draw = new OpenLayers.Control.DrawFeature(EMD.userLayer.assetsRentDetail,
// OpenLayers.Handler.Polygon,
// {
// title: "추가",
// displayClass: "olControlDrawFeaturePolygon",
// multi: true
// });
					EMD.edit = new OpenLayers.Control.ModifyFeature(EMD.userLayer.assetsRentDetail, {
		    			title: "수정",
		    			displayClass: "olControlModifyFeature"
		    		});
					EMD.edit.selectFeature(feature);
					EMD.edit.mode=OpenLayers.Control.ModifyFeature.RESHAPE;
					EMD.edit.createVertices=true;
// EMD.del = new DeleteFeature(EMD.userLayer.assetsRentDetail, {title: "삭제"});
		    		EMD.save = new OpenLayers.Control.Button({
		    			title: "저장",
		    			trigger: function() {
		    				EMD.panel.removeControls(EMD.save);
		    				callback(EMD.edit.feature);
		    			},
		    			displayClass: "olControlSaveFeatures"
		    		});

	    		 	EMD.selectControl.handlers.feature.stopDown = true;
	    			EMD.selectControl.unselectAll();
	    			EMD.selectControl.deactivate();

	    			EMD.map.addControl(EMD.edit);

		    		EMD.edit.activate();

// EMD.panel.addControls([EMD.draw, EMD.edit, EMD.del, EMD.save]);
					EMD.panel.addControls([/* EMD.draw, EMD.edit, EMD.del, */EMD.save]);
				}
			},
  			updateAssetRentDetailFeature: function(assetsRentDetail, feature) {
    			EMD.selectControl.unselectAll();
				var resp=EMD.protocols.gisAssetsCd.read({
	    			filter: new OpenLayers.Filter.Logical({
	        			type: OpenLayers.Filter.Logical.AND,
	        			filters: [
	    					new OpenLayers.Filter.Comparison({
	    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
	    						property: "PRT_AT_CD",
	    						value: assetsRentDetail['prtAtCode']
	    					}),
	    					new OpenLayers.Filter.Comparison({
	    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
	    						property: "MNG_YEAR",
	    						value: assetsRentDetail['mngYear']
	    					}),
	    					new OpenLayers.Filter.Comparison({
	    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
	    						property: "MNG_NO",
	    						value: assetsRentDetail['mngNo']
	    					}),
	    					new OpenLayers.Filter.Comparison({
	    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
	    						property: "MNG_CNT",
	    						value: assetsRentDetail['mngCnt']
	    					}),
	    					new OpenLayers.Filter.Comparison({
	    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
	    						property: "ASSETS_USAGE_SEQ",
	    						value: assetsRentDetail['assetsUsageSeq']
	    					})
	    				]
	        		}),
					callback: function(resp) {
						var ext;
						var f;
						if(resp.features==undefined || resp.features.length==0) {
	    			        alert('GIS 영역이 저장되지 않았습니다.');
						}
						else {
							resp.data.src = resp.features[0].clone();
							resp.data.src.state = OpenLayers.State.UPDATE;
	    			        EMD.protocols.assetsRentDetail.update(resp.data.src);
						}
					}
				});
	    		resp.data={obj:assetsRentDetail, src: feature};
  			},
  			// 자산 코드에서 임대 상세 정보의 피처를 정의 한다.
/*
 * setAssetRentDetailFeature: function(module, layerName, assetsRentDetail) {
 * EMD.selectControl.unselectAll();
 *
 * var assetCdLayer=EMD.userLayer.gisAssetsCd; var i, feature; len =
 * assetCdLayer.features.length;
 *
 * for(i = 0; i < len; i++) { feature = layer.features[i]; if(feature &&
 * feature.attributes) { if (EMD.gis.compareLayerObject(assetCdLayer, feature,
 * assetsRentDetail)) { var resp=EMD.protocols.gisAssetsCd.read({ filter: new
 * OpenLayers.Filter.Logical({ type: OpenLayers.Filter.Logical.AND, filters: [
 * new OpenLayers.Filter.Comparison({ type:
 * OpenLayers.Filter.Comparison.EQUAL_TO, property: "PRT_AT_CD", value:
 * assetsRentDetail['prtAtCode'] }), new OpenLayers.Filter.Comparison({ type:
 * OpenLayers.Filter.Comparison.EQUAL_TO, property: "MNG_YEAR", value:
 * assetsRentDetail['mngYear'] }), new OpenLayers.Filter.Comparison({ type:
 * OpenLayers.Filter.Comparison.EQUAL_TO, property: "MNG_NO", value:
 * assetsRentDetail['mngNo'] }), new OpenLayers.Filter.Comparison({ type:
 * OpenLayers.Filter.Comparison.EQUAL_TO, property: "MNG_CNT", value:
 * assetsRentDetail['mngCnt'] }), new OpenLayers.Filter.Comparison({ type:
 * OpenLayers.Filter.Comparison.EQUAL_TO, property: "ASSETS_USAGE_SEQ", value:
 * assetsRentDetail['assetsUsageSeq'] }) ] }), callback: function(resp) { var
 * ext; ext = resp.features[0].geometry.getBounds().clone(); // for(var i=1;i<resp.features.length;i++) { //
 * ext.extend(resp.features[i].geometry.getBounds()); // }
 * resp.data.obj.geometry = resp.features[0].geometry.clone();
 * EMD.map.zoomToExtent(ext); EMD.saveStrategy.save(); } });
 * resp.data={obj:assetsRentDetail, src: feature}; } } } },
 */
  			// 임대정보를 업데이트 한다.
  			setAssetRentFeature: function(module, layerName, obj, srcFeature) {
  				var layer=EMD.gis.getUserLayer(layerName);
				var i, feature;
    			EMD.selectControl.unselectAll();

				var resp=EMD.protocols.assetsRent.read({
	    			filter: new OpenLayers.Filter.Logical({
	        			type: OpenLayers.Filter.Logical.AND,
	        			filters: [
	    					new OpenLayers.Filter.Comparison({
	    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
	    						property: "PRT_AT_CD",
	    						value: obj['prtAtCode']
	    					}),
	    					new OpenLayers.Filter.Comparison({
	    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
	    						property: "MNG_YEAR",
	    						value: obj['mngYear']
	    					}),
	    					new OpenLayers.Filter.Comparison({
	    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
	    						property: "MNG_NO",
	    						value: obj['mngNo']
	    					}),
	    					new OpenLayers.Filter.Comparison({
	    						type: OpenLayers.Filter.Comparison.EQUAL_TO,
	    						property: "MNG_CNT",
	    						value: obj['mngCnt']
	    					})
	    				]
	        		}),
					callback: function(resp) {
						var ext;
						ext = foundFeatures[0].geometry.getBounds().clone();
						for(var i=1;i<foundFeatures.length;i++) {
							ext.extend(foundFeatures[i].geometry.getBounds());
						}
						EMD.map.zoomToExtent(ext);
						EMD.modifyFeature=new OpenLayers.Control.ModifyFeature(EMD.userLayer.gisAssetsCd,
								{handlerOptions: {holeModifier: "altKey"}, featureAdded: function(feature) {
									EMD.gis.storeLayerObject(layer, feature, row[0]);
									EMD.userLayer.gisAssetsCd.addFeatures(e.feature);
									EMD.saveStrategy.save();
								}});
						EMD.map.addControl(modifyFeature);
						modifyFeature.selectFeature(foundFeatures[0]);
					}
				});
	    		resp.data={obj:obj, src: srcFeature};

				for(i = 0; i < len; i++) {
					feature = layer.features[i];
					if(feature && feature.attributes) {
						if (EMD.gis.compareLayerObject(layer, feature, destFeature)) {
							var polygon= feature.geometry;
							feature.geometry = polygon.transform(
							    	 gisAssetsCdProjection,EMD.map.getProjection()
					          );
	    	    			EMD.userLayer.gisAssetsCd.addFeatures(feature);
	    	    			EMD.saveStrategy.save();

							foundFeatures.push(srcFeature);
						}
					}
				}
				EMD.util.window_hide_all();
				if(foundFeatures.length==0) {
		            EMD.map.addControl(new OpenLayers.Control.DrawFeature(EMD.userLayer.gisAssetsCd, OpenLayers.Handler.Polygon,
		            	    {handlerOptions: {holeModifier: "altKey"}, featureAdded: function(feature) {
		            	    	EMD.gis.storeLayerObject(layer, feature, row[0]);
		    	    			EMD.userLayer.gisAssetsCd.addFeatures([feature]);
		    	    			EMD.saveStrategy.save();
		            	    }}));
				}
				else {
// var updatefeature=foundFeatures[0].clone();
// EMD.wfs.addFeatures([updatefeature]);
					var ext;
					ext = foundFeatures[0].geometry.getBounds().clone();
					for(var i=1;i<foundFeatures.length;i++) {
						ext.extend(foundFeatures[i].geometry.getBounds());
					}
					EMD.map.zoomToExtent(ext);
					EMD.modifyFeature=new OpenLayers.Control.ModifyFeature(EMD.userLayer.gisAssetsCd,
							{handlerOptions: {holeModifier: "altKey"}, featureAdded: function(feature) {
								EMD.gis.storeLayerObject(layer, feature, row[0]);
								EMD.userLayer.gisAssetsCd.addFeatures(e.feature);
								EMD.saveStrategy.save();
							}});
					EMD.map.addControl(modifyFeature);
					modifyFeature.selectFeature(foundFeatures[0]);
				}
  			},
  			// 기존 피처에 새로운 피처 추가
  			appendFeature: function(module, layerName, destFeature, srcFeature) {
  				var row = module.$('#'+grid).selectedRows();
  				var layer=EMD.gis.getUserLayer(layerName);
				var i, feature;
				len = layer.features.length;
				var foundFeatures = [];
				if(row.length==0) {
					alert('항목을 선택 하십시요.');
					return;
				};
    			EMD.selectControl.unselectAll();

				for(i = 0; i < len; i++) {
					feature = layer.features[i];
					if(feature && feature.attributes) {
						if (EMD.gis.compareLayerObject(layer, feature, destFeature)) {
							var polygon= feature.geometry;
							feature.geometry = polygon.transform(
							    	 gisAssetsCdProjection,EMD.map.getProjection()
					          );
	    	    			EMD.userLayer.gisAssetsCd.addFeatures(feature);
	    	    			EMD.saveStrategy.save();

							foundFeatures.push(srcFeature);
						}
					}
				}
				EMD.util.window_hide_all();
				if(foundFeatures.length==0) {
		            EMD.map.addControl(new OpenLayers.Control.DrawFeature(EMD.userLayer.gisAssetsCd, OpenLayers.Handler.Polygon,
		            	    {handlerOptions: {holeModifier: "altKey"}, featureAdded: function(feature) {
		            	    	EMD.gis.storeLayerObject(layer, feature, row[0]);
		    	    			EMD.userLayer.gisAssetsCd.addFeatures([feature]);
		    	    			EMD.saveStrategy.save();
		            	    }}));
				}
				else {
// var updatefeature=foundFeatures[0].clone();
// EMD.wfs.addFeatures([updatefeature]);
					var ext;
					ext = foundFeatures[0].geometry.getBounds().clone();
					for(var i=1;i<foundFeatures.length;i++) {
						ext.extend(foundFeatures[i].geometry.getBounds());
					}
					EMD.map.zoomToExtent(ext);
					EMD.modifyFeature=new OpenLayers.Control.ModifyFeature(EMD.userLayer.gisAssetsCd,
							{handlerOptions: {holeModifier: "altKey"}, featureAdded: function(feature) {
								EMD.gis.storeLayerObject(layer, feature, row[0]);
								EMD.userLayer.gisAssetsCd.addFeatures(e.feature);
								EMD.saveStrategy.save();
							}});
					EMD.map.addControl(modifyFeature);
					modifyFeature.selectFeature(foundFeatures[0]);
				}
  			},
  			showEditPanel: function(module) {
	    		var shape = new OpenLayers.Control.Button({
	    			title: "편집",
	    			trigger: function() {
	  		        	EMD.modifyFeatyure.mode = OpenLayers.Control.ModifyFeature.RESHAPE;
	    			},
	    			displayClass: "olControlEditShpaePointFeatures"
	    		});
	    		var move = new OpenLayers.Control.Button({
	    			title: "이동",
	    			trigger: function() {
	  		        	EMD.modifyFeatyure.mode = OpenLayers.Control.ModifyFeature.DRAG;
	    			},
	    			displayClass: "olControlMoveShpaeFeatures"
	    		});
	    		var save = new OpenLayers.Control.Button({
	    			title: "편집 종료",
	    			trigger: function() {
	    				if(EMD.edit.feature) {
	    					EMD.edit.selectControl.unselectAll();
	    				}
	    				EMD.saveStrategy.save();
	    				EMD.map.removeControl(EMD.panel);
	    			},
	    			displayClass: "olControlFinishEditShapeFeatures"
	    		});

	    		EMD.panel = new OpenLayers.Control.Panel({
	    			displayClass: 'customEditingToolbar',
	    			allowDepress: true
	    		});

	    		EMD.panel.addControls([shape, move, save]);
	    		EMD.map.addControl(EMD.panel);
  			},
  			showDrawPanel: function() {
  				EMD.draw = new OpenLayers.Control.DrawFeature(
	    				EMD.wfs, OpenLayers.Handler.Polygon,
	    				{
	    					title: "추가",
	    					displayClass: "olControlDrawFeaturePolygon",
	    					multi: true
	    				}
	    		);

	    		EMD.edit = new OpenLayers.Control.ModifyFeature(EMD.wfs, {
	    			title: "수정",
	    			displayClass: "olControlModifyFeature"
	    		});

	    		EMD.del = new DeleteFeature(EMD.wfs, {title: "삭제"});

	    		EMD.save = new OpenLayers.Control.Button({
	    			title: "저장",
	    			trigger: function() {
	    				if(EMD.edit.feature) {
	    					EMD.edit.selectControl.unselectAll();
	    				}
// EMD.gis.modifyAssetCdFeature(EMD.draw._mode.param, EMD.edit.feature);
	    				EMD.saveStrategy.save();
	    				EMD.map.removeControl(EMD.panel);
	    			},
	    			displayClass: "olControlSaveFeatures"
	    		});

	    		EMD.panel = new OpenLayers.Control.Panel({
	    			displayClass: 'customEditingToolbar',
	    			allowDepress: true
	    		});

	    		EMD.panel.addControls([EMD.save, EMD.del, EMD.edit, EMD.draw]);
	    		EMD.map.addControl(EMD.panel);
  			},
  			getParentLayer: function(layerName) {
  				var layer=layerName;
  				if(typeof layerName=="string") layer=EMD.gis.getUserLayer(layerName);
  				if(layer==EMD.userLayer.landCode) {
  					return null;
  				}
  				if(layer==EMD.userLayer.gisAssetsCd) {
  					return null;
  				}
  				if(layer==EMD.userLayer.assetsRent) {
  					return EMD.userLayer.assetsRentDetail;
  				}
  				if(layer==EMD.userLayer.assetsRentDetail) {
  					return EMD.userLayer.gisAssetsCd;
  				}
  				if(layer==EMD.userLayer.assetStats
  						||layer==EMD.userLayer.gisArchFclty
  						||layer==EMD.userLayer.gisTeleFclty
  						||layer==EMD.userLayer.gisMechFclty
  						||layer==EMD.userLayer.gisElecFclty
  						||layer==EMD.userLayer.gisCivilFclty
  						||layer==EMD.userLayer.gisArchFclty) {
  					return EMD.userLayer.gisAssetsCd;
  				}
  				return null;
  			},
  			getUserLayer: function(layerName) {
  				switch(layerName) {
  				case 'landCode':
  					return EMD.userLayer.landCode;
  					break;
  				case 'gisAssetsCd':
  					return EMD.userLayer.gisAssetsCd;
  					break;
  				case 'assetsRent':
  					return EMD.userLayer.assetsRent;
  					break;
  				case 'assetsRentDetail':
  					return EMD.userLayer.assetsRentDetail;
  					break;
  				case 'assetStats':
  					return EMD.userLayer.assetStats;
  					break;
  				case 'gisArchFclty':
  					return EMD.userLayer.gisArchFclty;
  					break;
  				case 'gisTeleFclty':
  					return EMD.userLayer.gisTeleFclty;
  					break;
  				case 'gisMechFclty':
  					return EMD.userLayer.gisMechFclty;
  					break;
  				case 'gisElecFclty':
  					return EMD.userLayer.gisElecFclty;
  					break;
  				case 'gisCivilFclty':
  					return EMD.userLayer.gisCivilFclty;
  					break;
  				}
  				return null;
  			},
  			getLayerProtocol: function(layerName) {
  				var layer=null;
  				if(typeof layerName=="string") {
  					layer=EMD.gis.getUserLayer(layerName);
  	  				switch(layerName) {
  	  				case 'landCode':
  	  					return EMD.protocols.landCode;
  	  					break;
  	  				case 'gisAssetsCd':
  	  					return EMD.protocols.gisAssetsCd;
  	  					break;
  	  				case 'assetsRent':
  	  					return EMD.protocols.assetsRent;
  	  					break;
  	  				case 'assetsRentDetail':
  	  					return EMD.protocols.assetsRentDetail;
  	  					break;
  	  				case 'assetStats':
  	  					return EMD.protocols.assetStats;
  	  					break;
  	  				case 'gisCivilFclty':
  	  				case 'gisArchFclty':
  	  				case 'gisTeleFclty':
  	  				case 'gisMechFclty':
  	  				case 'gisElecFclty':
  	  					return EMD.protocols.gisPrtFcltyCd;
  	  				}
  				}
  				else layer=layerName;
  				for(var k in EMD.protocols) {
  					if(EMD.protocols[k]!=null && EMD.protocols[k]['layer']!=undefined && EMD.protocols[k].layer==layer) {
  						return EMD.protocols[k];
  					}
  				}
  				if(layer==EMD.userLayer.gisArchFclty
  						||layer==EMD.userLayer.gisCivilFclty
  						||layer==EMD.userLayer.gisMechFclty
  						||layer==EMD.userLayer.gisElecFclty
  						||layer==EMD.userLayer.gisTeleFclty) return EMD.protocols.gisPrtFcltyCd;

  				return null;
  			},
  			newLayerObject: function(layer, value, newfeature) {
  				if(layer==EMD.userLayer.gisAssetsCd) {
  				}
  			},
  			checkLayerObject: function(layer, obj) {
  				if(layer==EMD.userLayer.gisAssetsCd) {
  					var prtAtCode=obj['gisAssetsPrtAtCode']==undefined?obj['PRT_CD']:obj['gisAssetsPrtAtCode'];
  					var gisAssetsCd=obj['gisAssetsCd']==undefined?obj['ASSETS_CD']:obj['gisAssetsCd'];
  					var gisAssetsSubCd=obj['gisAssetsSubCd']==undefined?obj['ASSETS_SCD']:obj['gisAssetsSubCd'];
  					return prtAtCode!=undefined&&gisAssetsCd!=undefined&&gisAssetsSubCd!=undefined
  						&&prtAtCode.length==3&&gisAssetsCd.length==3&&gisAssetsSubCd.length==2;
  				}
  				else if(layer==EMD.userLayer.assetRentStats) {
  					var prtAtCode=obj['prtAtCode']==undefined?obj['PRT_CD']:obj['prtAtCode'];
  					var mngYear=obj['mngYear']==undefined?obj['MNG_YEAR']:obj['mngYear'];
  					var mngNo=obj['mngNo']==undefined?obj['MNG_NO']:obj['mngNo'];
  					var mngCnt=obj['mngCnt']==undefined?obj['MNG_CNT']:obj['mngCnt'];
  					return prtAtCode!=undefined&&mngYear!=undefined&&mngNo!=undefined&&mngCnt!=undefined
						&&prtAtCode.length==3&&mngYear.length==4&&mngNo.length==3&&mngCnt.length==2;
  				}
  				else if(layer==EMD.userLayer.assetsRentDetail) {
  					var prtAtCode=obj['prtAtCode']==undefined?obj['PRT_CD']:obj['prtAtCode'];
  					var mngYear=obj['mngYear']==undefined?obj['MNG_YEAR']:obj['mngYear'];
  					var mngNo=obj['mngNo']==undefined?obj['MNG_NO']:obj['mngNo'];
  					var mngCnt=obj['mngCnt']==undefined?obj['MNG_CNT']:obj['mngCnt'];
  					var assetsUsageSeq=obj['assetsUsageSeq']==undefined?obj['ASSETS_USAGE_SEQ']:obj['assetsUsageSeq'];
  					return prtAtCode!=undefined&&mngYear!=undefined&&mngNo!=undefined&&mngCnt!=undefined
					&&prtAtCode.length==3&&mngYear.length==4&&mngNo.length==3&&mngCnt.length==2;
  				}
  				else if(layer==EMD.userLayer.gisArchFclty
  						||layer==EMD.userLayer.gisTeleFclty
  						||layer==EMD.userLayer.gisMechFclty
  						||layer==EMD.userLayer.gisElecFclty
  						||layer==EMD.userLayer.gisCivilFclty) {
  					var gisAssetsPrtAtCode=obj['gisAssetsPrtAtCode']==undefined?obj['PRT_CD']:obj['gisAssetsPrtAtCode'];
  					var gisAssetsCd=obj['gisAssetsCd']==undefined?obj['ASSETS_CD']:obj['gisAssetsCd'];
  					var gisAssetsSubCd=obj['gisAssetsSubCd']==undefined?obj['ASSETS_SCD']:obj['gisAssetsSubCd'];
  					var gisPrtFcltyCd=obj['gisPrtFcltyCd']==undefined?obj['FCLTY_CD']:obj['gisPrtFcltyCd'];
  					var gisPrtFcltySeq=obj['gisPrtFcltySeq']==undefined?obj['FCLTY_SEQ']:obj['gisPrtFcltySeq'];
  					var gisPrtFcltySe=obj['gisPrtFcltySe']==undefined?obj['FCLTY_SE']:obj['gisPrtFcltySe'];
  					return gisAssetsPrtAtCode!=undefined&&gisAssetsCd!=undefined&&gisAssetsSubCd!=undefined
  						&&gisPrtFcltyCd!=undefined&&gisPrtFcltySeq!=undefined&&gisPrtFcltySe!=undefined
  						&&gisAssetsPrtAtCode.length==3&&gisAssetsCd.length==3&&gisAssetsSubCd.length==2
  						&&gisPrtFcltyCd.length==2&&gisPrtFcltySeq.length==4&&gisPrtFcltySe.length==1;
  				}
  				return false;
  			},
  			compareLayerObject: function(layer, feature, obj) {
  				if(layer==EMD.userLayer.gisAssetsCd) {
  					var prtAtCode=obj['gisAssetsPrtAtCode']==undefined?obj['PRT_CD']:obj['gisAssetsPrtAtCode'];
  					var gisAssetsCd=obj['gisAssetsCd']==undefined?obj['ASSETS_CD']:obj['gisAssetsCd'];
  					var gisAssetsSubCd=obj['gisAssetsSubCd']==undefined?obj['ASSETS_SCD']:obj['gisAssetsSubCd'];
  					return feature.attributes['PRT_CD'] === prtAtCode
					&& feature.attributes['ASSETS_CD'] === gisAssetsCd
					&& feature.attributes['ASSETS_SCD'] === gisAssetsSubCd;
  				}
  				else if(layer==EMD.userLayer.assetRentStats) {
  					var prtAtCode=obj['prtAtCode']==undefined?obj['PRT_CD']:obj['prtAtCode'];
  					var mngYear=obj['mngYear']==undefined?obj['MNG_YEAR']:obj['mngYear'];
  					var mngNo=obj['mngNo']==undefined?obj['MNG_NO']:obj['mngNo'];
  					var mngCnt=obj['mngCnt']==undefined?obj['MNG_CNT']:obj['mngCnt'];
  					return feature.attributes['PRT_AT_CD'] === prtAtCode
					&& feature.attributes['MNG_NO'] === mngYear
					&& feature.attributes['MNG_NO'] === mngNo
					&& feature.attributes['MNG_CNT'] === mngCnt;
  				}
  				else if(layer==EMD.userLayer.assetsRentDetail) {
  					var prtAtCode=obj['prtAtCode']==undefined?obj['PRT_CD']:obj['prtAtCode'];
  					var mngYear=obj['mngYear']==undefined?obj['MNG_YEAR']:obj['mngYear'];
  					var mngNo=obj['mngNo']==undefined?obj['MNG_NO']:obj['mngNo'];
  					var mngCnt=obj['mngCnt']==undefined?obj['MNG_CNT']:obj['mngCnt'];
  					var assetsUsageSeq=obj['assetsUsageSeq']==undefined?obj['ASSETS_USAGE_SEQ']:obj['assetsUsageSeq'];
  					return feature.attributes['PRT_AT_CD'] === prtAtCode
					&& feature.attributes['MNG_NO'] === mngYear
					&& feature.attributes['MNG_NO'] === mngNo
					&& feature.attributes['MNG_CNT'] === mngCnt
					&& feature.attributes['ASSETS_USAGE_SEQ'] === assetsUsageSeq;
  				}
  				else if(layer==EMD.userLayer.gisArchFclty
  						||layer==EMD.userLayer.gisTeleFclty
  						||layer==EMD.userLayer.gisMechFclty
  						||layer==EMD.userLayer.gisElecFclty
  						||layer==EMD.userLayer.gisCivilFclty) {
  					var gisAssetsPrtAtCode=obj['gisAssetsPrtAtCode']==undefined?obj['PRT_CD']:obj['gisAssetsPrtAtCode'];
  					var gisAssetsCd=obj['gisAssetsCd']==undefined?obj['ASSETS_CD']:obj['gisAssetsCd'];
  					var gisAssetsSubCd=obj['gisAssetsSubCd']==undefined?obj['ASSETS_SCD']:obj['gisAssetsSubCd'];
  					var gisPrtFcltyCd=obj['gisPrtFcltyCd']==undefined?obj['FCLTY_CD']:obj['gisPrtFcltyCd'];
  					var gisPrtFcltySeq=obj['gisPrtFcltySeq']==undefined?obj['FCLTY_SEQ']:obj['gisPrtFcltySeq'];
  					var gisPrtFcltySe=obj['prtFcltySe']==undefined?obj['FCLTY_SE']:obj['prtFcltySe'];
  					return feature.attributes['PRT_CD'] === gisAssetsPrtAtCode
					&& feature.attributes['ASSETS_CD'] === gisAssetsCd
					&& feature.attributes['ASSETS_SCD'] === gisAssetsSubCd
					&& feature.attributes['FCLTY_CD'] === gisPrtFcltyCd
					&& feature.attributes['FCLTY_SEQ'] === gisPrtFcltySeq
					&& feature.attributes['FCLTY_SE'] === gisPrtFcltySe;
  				}
  				return false;
  			},
  			storeLayerObject:function(layer, feature, obj) {
  				if(layer==EMD.userLayer.gisAssetsCd) {
  					if(obj==null) {
						obj = {
								'PRT_CD':'',
								'ASSETS_CD':'',
								'ASSETS_SCD':'',
								'ASSETS_NM':'미정의'
							};
  					}
  					var gisAssetsPrtAtCode=obj['gisAssetsPrtAtCode']==undefined?obj['PRT_CD']:obj['gisAssetsPrtAtCode'];
  					var gisAssetsCd=obj['gisAssetsCd']==undefined?obj['ASSETS_CD']:obj['gisAssetsCd'];
  					var gisAssetsSubCd=obj['gisAssetsSubCd']==undefined?obj['ASSETS_SCD']:obj['gisAssetsSubCd'];
  					var gisAssetsSeCd=obj['gisAssetsSeCd']==undefined?obj['ASSETS_SE_CD']:obj['gisAssetsSeCd'];
  					var gisAssetsNm=obj['gisAssetsNm']==undefined?obj['ASSETS_NM']:obj['gisAssetsNm'];
// gisAssetsNm=obj['gisAssetsNm']==undefined?obj['ASSETS_NM']:obj['gisAssetsNm'];
//  					var gisAssetsNm='';
  					feature.attributes['PRT_CD'] = gisAssetsPrtAtCode;
					feature.attributes['ASSETS_CD'] = gisAssetsCd;
					feature.attributes['ASSETS_SCD'] = gisAssetsSubCd;
					feature.attributes['ASSETS_SE_CD'] = gisAssetsSeCd;
					feature.attributes['ASSETS_NM'] = gisAssetsNm;
					if(feature.state!=OpenLayers.State.INSERT) feature.state=OpenLayers.State.UPDATE;
  				}
  				else if(layer==EMD.userLayer.assetsRent) {
  					if(obj==null) {
						obj = {
								'PRT_CD':'',
								'MNG_YEAR':'',
								'MNG_NO':'',
								'MNG_CNT':'',
								'ENTRPS_NM':'미정의',
								'USAGE_AR':''
							};
  					}
  					feature.attributes['PRT_CD'] = obj['PRT_CD'];
					feature.attributes['MNG_YEAR'] = obj['MNG_YEAR'];
					feature.attributes['MNG_NO'] = obj['MNG_YEAR'];
					feature.attributes['MNG_CNT'] = obj['MNG_CNT'];
// feature.attributes['ENTRPS_NM'] = obj['ENTRPS_NM'];
					feature.attributes['ENTRPS_NM'] = '';	// 한글은 트리거에서 처리
					feature.attributes['USAGE_AR'] = obj['USAGE_AR'];
					if(feature.state!=OpenLayers.State.INSERT) feature.state=OpenLayers.State.UPDATE;
  				}
  				else if(layer==EMD.userLayer.assetsRentDetail) {
  					if(obj==null) {
						obj = {
								'PRT_CD':'',
								'MNG_YEAR':'',
								'MNG_NO':'',
								'MNG_CNT':'',
								'ASSET_SEQ':'',
								'ENTRPS_NM':'미정의',
								'USAGE_AR':'',
								'FEE':''
							};
  					}
  					feature.attributes['PRT_CD'] = obj['PRT_CD'];
					feature.attributes['MNG_YEAR'] = obj['MNG_YEAR'];
					feature.attributes['MNG_NO'] = obj['MNG_YEAR'];
					feature.attributes['MNG_CNT'] = obj['MNG_CNT'];
					feature.attributes['ASSET_SEQ'] = obj['ASSET_SEQ'];
// feature.attributes['ENTRPS_NM'] = obj['ENTRPS_NM'];
					feature.attributes['ENTRPS_NM'] = '';
					feature.attributes['USAGE_AR'] = obj['USAGE_AR'];
					if(feature.state!=OpenLayers.State.INSERT) feature.state=OpenLayers.State.UPDATE;
  				}
  				else if(layer==EMD.userLayer.gisArchFclty
  						||layer==EMD.userLayer.gisTeleFclty
  						||layer==EMD.userLayer.gisMechFclty
  						||layer==EMD.userLayer.gisElecFclty
  						||layer==EMD.userLayer.gisCivilFclty) {
  					if(obj==null) {
						obj = {
								'PRT_CD':'',
								'ASSETS_CD':'',
								'ASSETS_SCD':'',
								'FCLTY_CD':'',
								'FCLTY_SEQ':'',
								'FCLTY_SE':'',
								'FCLTY_NM':'미정의'
							};
						if(layer==EMD.userLayer.gisArchFclty) obj['FCLTY_SE']='A';
						else if(layer==EMD.userLayer.gisTeleFclty) obj['FCLTY_SE']='I';
						else if(layer==EMD.userLayer.gisMechFclty) obj['FCLTY_SE']='M';
						else if(layer==EMD.userLayer.gisElecFclty) obj['FCLTY_SE']='E';
						else if(layer==EMD.userLayer.gisCivilFclty) obj['FCLTY_SE']='C';
  					}
  					var gisAssetsPrtAtCode=obj['gisAssetsPrtAtCode']==undefined?obj['PRT_CD']:obj['gisAssetsPrtAtCode'];
  					var gisAssetsCd=obj['gisAssetsCd']==undefined?obj['ASSETS_CD']:obj['gisAssetsCd'];
  					var gisAssetsSubCd=obj['gisAssetsSubCd']==undefined?obj['ASSETS_SCD']:obj['gisAssetsSubCd'];
  					var gisPrtFcltyCd=obj['gisPrtFcltyCd']==undefined?obj['FCLTY_CD']:obj['gisPrtFcltyCd'];
  					var gisPrtFcltySeq=obj['gisPrtFcltySeq']==undefined?obj['FCLTY_SEQ']:obj['gisPrtFcltySeq'];
  					var gisPrtFcltySe=obj['gisPrtFcltySe']==undefined?obj['FCLTY_SE']:obj['prtFcltySe'];
// var
// prtFcltyNm=obj['prtFcltyNm']==undefined?obj['FCLTY_NM']:obj['prtFcltyNm'];
  					var prtFcltyNm='';
  					feature.attributes['PRT_CD'] = gisAssetsPrtAtCode;
					feature.attributes['ASSETS_CD'] = gisAssetsCd;
					feature.attributes['ASSETS_SCD'] = gisAssetsSubCd;
					feature.attributes['FCLTY_CD'] = gisPrtFcltyCd;
					feature.attributes['FCLTY_SEQ'] = gisPrtFcltySeq;
					feature.attributes['FCLTY_SE'] = gisPrtFcltySe;
					feature.attributes['FCLTY_NM'] = prtFcltyNm;
					if(feature.state!=OpenLayers.State.INSERT) feature.state=OpenLayers.State.UPDATE;
  				}
  				layer.drawFeature(feature);
  			},
    	getWfsData: function(url, option) {
    		var shapeParams = OpenLayers.Util.getParameterString({
    			SERVICE: "WFS",
    			VERSION: "1.1.0",
    			REQUEST: "GetFeature",
    			BBOX: postioncd,
    			// bbox TYPENAME: findLayerName,
    			// wfs 할 레이어명 MAXFEATURES:"40",
    			SRSNAME: "EPSG:900913",
    			OUTPUT: "text/xml;subType=gml/3.1.1/profiles/gmlsf/1.0.0/0",
    			EXCEPTIONS: "text/xml" });
    		OpenLayers.loadURL("/proxy/proxy.jsp",
    				"?url=" + escape("http://map.vworld.kr/js/wfs.do?APIKEY=369C4265-766B-31D6-9469-8FB5ECC1BE17&DOMAIN=localhost:8080" + shapeParams),
    				this,
    				getSearchdFeature);
    		// 받아온 gml을 분석하여 표출
// function getSearchdFeature(response){ var g = new OpenLayers.Format.GML();
// features = g.read(response.responseText); . . . }
    	},
    	OpenLayersVworld: OpenLayers.Class(OpenLayers.Layer.XYZ, {
    	    name: "vworldMap",
// url: [
// "http://xdworld.vworld.kr:8080/2d/Base/201310/${z}/${x}/${y}.png"
// ],
    	    zoomOffset: 6,
    	    serverResolutions: [
    	                        2445.9849047851562
    	                        , 1222.99245239257812992
    	                        , 611.49622619628906496
    	                        , 305.74811309814453248
    	                        , 152.87405654907226624
    	                        , 76.43702827453613312
    	                        , 38.21851413726806656
    	                        , 19.10925706863403328
    	                        , 9.55462853431701664
    	                        , 4.77731426715850832
    	                        , 2.388657133579254
    	                        , 1.194328566789627
    	                        , 0.5971642833948135
    	                        ],
    		sphericalMercator: false,
    		transitionEffect: "resize",
    		buffer: 1,
    		displayOutsideMaxExtent: true,
    	    initialize: function(name, options) {
    	        if(options.baseurl!=null || options.baseurl.length>0) options.url =[
                                                                    		"http://xdworld.vworld.kr:8080/2d/Base/201310/${z}/${x}/${y}.png"
    	    	                                        ];
    	        var newArgs = [name, [
    	                              		options.baseurl+"/${z}/${x}/${y}.png"
                                    ], options];
    	        OpenLayers.Layer.XYZ.prototype.initialize.apply(this, newArgs);
    	    },
    	    clone: function(obj) {
    	        if (obj == null) {
    	            obj = new OpenLayers.Layer.Vworld(
    	                this.name, this.getOptions());
    	        }
    	        obj = OpenLayers.Layer.XYZ.prototype.clone.apply(this, [obj]);
    	        return obj;
    	    },

    		getXYZ: function(bounds) {
        		var res = this.getServerResolution();
        		var z = this.getZoomForResolution(res);
        		var x = Math.round((bounds.left - this.maxExtent.left) / (res * this.tileSize.w));
        		var y = Math.round((this.maxExtent.top - bounds.top) / (res * this.tileSize.h));
    	        return {'x': x, 'y': y, 'z': z};
    	    },

    	    CLASS_NAME: "OpenLayers.Layer.Vworld"
    	}),
    	/**
		 * @requires OpenLayers/Layer/XYZ.js
		 */

    	OpenLayersDaum: OpenLayers.Class(OpenLayers.Layer.XYZ, {

    	    name: "DaumMap",
    	    url: [
    			"http://i0.maps.daum-img.net/map/image/G03/i/1.04/L${z}/${y}/${x}.png",
    			"http://i1.maps.daum-img.net/map/image/G03/i/1.04/L${z}/${y}/${x}.png",
    			"http://i2.maps.daum-img.net/map/image/G03/i/1.04/L${z}/${y}/${x}.png",
    			"http://i3.maps.daum-img.net/map/image/G03/i/1.04/L${z}/${y}/${x}.png"
    	    ],
    		sphericalMercator: false,
    		transitionEffect: "resize",
    		buffer: 1,
    		displayOutsideMaxExtent: true,
    	    initialize: function(name, options) {
    			if (!options) options = {resolutions: [2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1, 0.5, 0.25]};
    			else if (!options.resolutions) options.resolutions = [2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1, 0.5, 0.25];
    	        var newArgs = [name, null, options];
    	        OpenLayers.Layer.XYZ.prototype.initialize.apply(this, newArgs);
    	    },
    	    clone: function(obj) {
    	        if (obj == null) {
    	            obj = new OpenLayers.Layer.Daum(
    	                this.name, this.getOptions());
    	        }
    	        obj = OpenLayers.Layer.XYZ.prototype.clone.apply(this, [obj]);
    	        return obj;
    	    },

    		getXYZ: function(bounds) {
    	        var res = this.getServerResolution();
    	        var x = Math.round((bounds.left - this.maxExtent.left) /
    	            (res * this.tileSize.w));
    	        var y = Math.round((bounds.bottom - this.maxExtent.bottom) /
    	            (res * this.tileSize.h));
    	        var z = 14 - this.getServerZoom();

    	        if (this.wrapDateLine) {
    	            var limit = Math.pow(2, z);
    	            x = ((x % limit) + limit) % limit;
    	        }

    	        return {'x': x, 'y': y, 'z': z};
    	    },

    	    CLASS_NAME: "OpenLayers.Layer.Daum"
    	}),
    	GTransformWMS: OpenLayers.Class(OpenLayers.Layer.WMS, {

    		getURL: function (bounds) {
    	        bounds = this.adjustBounds(bounds);

    	        var imageSize = this.getImageSize();
    	        var newParams = {};
    	        // WMS 1.3 introduced axis order
    	        var reverseAxisOrder = this.reverseAxisOrder();
    	        newParams.BBOX = this.encodeBBOX ?
    	            bounds.toBBOX(null, reverseAxisOrder) :
    	            bounds.toArray(reverseAxisOrder);
    	        newParams.WIDTH = imageSize.w;
    	        newParams.HEIGHT = imageSize.h;
    	        // WMS 요청시 좌표변환
    			if(!(this.params.CRS == this.map.getProjectionObject().projCode)) {
    				var point = new OpenLayers.Geometry.Point(bounds.left, bounds.bottom);
    				var minGeom = OpenLayers.Projection.transform(point, this.map.projection, this.projection);

    				point = new OpenLayers.Geometry.Point(bounds.right, bounds.top);
    				var maxGeom = OpenLayers.Projection.transform(point, this.map.projection, this.projection);
    				newParams.BBOX = [minGeom.x,minGeom.y,maxGeom.x,maxGeom.y];
    			}

    	        var requestString = this.getFullRequestString(newParams);
    	        return requestString;
    	    },

    		getFullRequestString:function(newParams, altUrl) {
    			var mapProjection = this.map.getProjectionObject();
// var projectionCode = this.projection ? this.projection.getCode() :
// mapProjection.getCode();
    	        // var value = (projectionCode == "none") ? null :
				// projectionCode;
    			var value = "EPSG:5186";
    	        if (parseFloat(this.params.VERSION) >= 1.3) {
    	            this.params.CRS = value;
    	        } else {
    	            this.params.SRS = value;
    	        }

    	        if (typeof this.params.TRANSPARENT == "boolean") {
    	            newParams.TRANSPARENT = this.params.TRANSPARENT ? "TRUE" : "FALSE";
    	        }

    	        return OpenLayers.Layer.Grid.prototype.getFullRequestString.apply(
    	                                                    this, arguments);
    	    },

    		CLASS_NAME: "GTransformWMS"
    	}),
    	fnRequestWFS: function (filterText, success, failure){

    		geometry = filterText;

    		try {
    			var successCall = null;
    			var failureCall = null;

    			if (typeof success == 'function'){successCall = success; }
    			if (typeof failure == 'function'){failureCall = failure; }

    			OpenLayers.ProxyHost = "/proxy.do?url=";

    			// 파라미터 설정
    			var buffer	= document.getElementById("buffer").value;

    			// 속성검색
    			var prop_name	= document.getElementById("prop_name").value;
    			var prop_val	= document.getElementById("prop_val").value;
    			var prop_opr	= document.getElementById("prop_opr").value;

    	    	var params = "";
    			params += "apiKey=" + dataApiKey;
    	    	if(geometry != ""){
    	    		params += "&geometry=" + geometry;
    	    	}else if(vCurNaviCode != ""){
    	    		params += "&emdCd=" + vCurNaviCode;
    	    	}
    			if(prop_val != ""){
    				params += "&filter=" + prop_name + ":"+prop_opr+":" + encodeURI(prop_val);
    			}
    			// if(prop_val != ""){
    			// params += "&"+prop_name+"=" + encodeURI(prop_val) +
				// "&operator="+prop_opr;
    			// }
    			params += "&output=" + output;
    			params += "&srsName=EPSG:5186";

    			if(buffer > 0){
    				params += "&buffer="+buffer; // 범위검색
    			}
    			if(pageInfo.pageIndex > 1){
    				params += "&pageIndex="+pageInfo.pageIndex; // 페이지번호
    			}
    			params += "&pageUnit="+pageInfo.pageUnit; // 한페이지당 레코드 수
    			// params +=
				// "&propertyname=ag_geom,uname,sido_name,sigungu_name"; //특정필드
				// 검색

    			serviceId = $("#service").val();

    			var reqConfig = OpenLayers.Util.extend({
    	        		url: reqUrl + serviceId + "/data?" + params,
    	        		headers: {"Content-Type": "text/plain"},
    	        		success: successCall,
    	        		failure: failureCall,
    	        		scope: this
    	    		}, {method: "GET"});
    		    OpenLayers.Request.issue(reqConfig);
    		} catch(e){
    			alert("공간정보조회를 실패하였습니다.\n\n" + e.message);
    		}
    	},
    	/**
		 * 피처 정보 추가 함수명 : EMD.gis.addFeature
		 *
		 * @param mode -
		 *            추가할 모드 (PF: 항만시설, AC: 자산코드, AR: 자산임대...)
		 * @param param -
		 *            추가할 피처에 대한 속성 정보
		 * @returns
		 */
    	addFeature: function(mode, param) {
    		EMD.draw._mode={mode:mode, param:param};

// EMD.draw.events.register('featureadded', EMD.draw, function(e) {
// param = EMD.draw._mode.param;
// switch(EMD.draw._mode.mode) {
// case 'PF':
// e.feature.attributes.PRT_AT_CD=param.gisAssetsPrtAtCode;
// e.feature.attributes.PRT_CD=param.gisAssetsCd;
// e.feature.attributes.PRT_SUB_CD=param.gisAssetsSubCd;
// e.feature.attributes.GIS_PRT_FCLTY_CD=param.gisPrtFcltyCd;
// e.feature.attributes.GIS_PRT_FCLTY_SUB_CD=param.gisPrtFcltySubCd;
// e.feature.attributes.GIS_PRT_FCLTY_NM=param.gisPrtFcltyNm;
// EMD.userLayer.prtFclty.addFeatures(e.feature);
// EMD.wfs.removeAllFeatures();
// break;
// case 'AC':
// e.feature.attributes.PRT_CD=param.gisAssetsPrtAtCode;
// e.feature.attributes.ASSETS_CD=param.gisAssetsCd;
// e.feature.attributes.ASSETS_SCD=param.gisAssetsSubCd;
// e.feature.attributes.ASSETS_NM=param.gisAssetsNm;
// e.feature.attributes.LOCPLC=param.gisAssetsLocplc;
// e.feature.attributes.GIS_ASSETS_LNM=param.gisAssetsLnm;
// e.feature.attributes.GIS_ASSETS_LNM_SUB=param.gisAssetsLnmSub;
// EMD.userLayer.gisAssetsCd.addFeatures(e.feature);
// EMD.wfs.removeAllFeatures();
// break;
// }
// });

    		EMD.map.addControl(EMD.panel);
    	},
    	cancelFeature: function() {
    		EMD.map.removeControl(EMD.panel);
    	},
    	/**
		 * 피처 정보 추가 함수명 : EMD.gis.addAssetCdFeature
		 *
		 * @param gisAssetsCd -
		 *            (추가할 자산 코드)
		 * @param retfunc(retStat,
		 *            feature) - 추가 후 실행할 펑션 retStat: 0 추가, retStat=-1 취소
		 * @returns
		 */
    	addAssetCdFeature: function(gisAssetsCd, retfunc) {

    	},
    	showBupJungDongCode: function (code) {
    		/*
			 * var shapeParams = OpenLayers.Util.getParameterString({ SERVICE:
			 * "WFS", VERSION: "1.1.0", REQUEST: "GetFeature", // BBOX:
			 * postioncd, //bbox TYPENAME: 'LP_PA_CBND_BUBUN', //wfs 할 레이어명
			 * PROPERTYNAME:'PNU,BCHK,STD_SGGCD,JIBUN,BONBUN,BUBUN', PNU: code,
			 * filter: "<ogc:Filter xmlns:ogc='http://www.opengis.net/ogc'><ogc:And><ogc:PropertyLike
			 * escape='\' singleChar='_' wildCard='%'><ogc:PropertyName>PNU</ogc:PropertyName><gml:Literal>"+code+"%</gml:Literal></ogc:PropertyLike></ogc:And></ogc:Filter>", //
			 * filter: 'jibun:like:'+jibun+jibunsub, // MAXFEATURES:"40",
			 * SRSNAME: "EPSG:900913", OUTPUT:
			 * "text/xml;subType=gml/3.1.1/profiles/gmlsf/1.0.0/0", EXCEPTIONS:
			 * "text/xml" });
			 */

    		OpenLayers.Request.GET({
    			url: escape("http://map.vworld.kr/js/wfs.do"),
    			params : {
    				SERVICE: "WFS",
        			VERSION: "1.1.0",
        			REQUEST: "GetFeature",
        			APIKEY: '5E7D605D-BD5A-3BEE-8C20-62172A86B4AA',
        			DOMAIN: 'http://localhost',
// BBOX: postioncd, //bbox
					BBOX: "14179985.99,4077303.96,14266206.96,4183704.31",
        			TYPENAME: 'LP_PA_CBND_BUBUN',	// wfs 할 레이어명
// PROPERTYNAME:'PNU,BCHK,STD_SGGCD,JIBUN,BONBUN,BUBUN',
        			emdCd: code,
// filter: escape("<ogc:Filter
// xmlns:ogc='http://www.opengis.net/ogc'><ogc:And><ogc:PropertyLike escape='\'
// singleChar='_'
// wildCard='%'><ogc:PropertyName>PNU</ogc:PropertyName><gml:Literal>"+code+"%</gml:Literal></ogc:PropertyLike><ogc:BBOX><ogc:PropertyName>ows:BoundingBox</ogc:PropertyName><gml:Envelope><gml:lowerCorner>14179985.99
// 4077303.96</gml:lowerCorner><gml:upperCorner>14266206.96
// 4183704.31</gml:upperCorner></gml:Envelope></ogc:BBOX></ogc:And><ogc:BBOX</ogc:Filter>"),
// filter: "PNU:like:"+code+"",
        			filter: "STD_SGGCD:like:"+code.substring(0, 5),
// filter:
// "<PropertyIsEqualTo><PropertyName>PNU</PropertyName><Literal>"+code+"%</Literal></PropertyIsEqualTo>",
// filter: escape("<ogc:Filter
// xmlns:ogc='http://www.opengis.net/ogc'><ogc:And><ogc:BBOX><ogc:PropertyName>ows:BoundingBox</ogc:PropertyName><gml:Envelope><gml:lowerCorner>14179985.99
// 4077303.96</gml:lowerCorner><gml:upperCorner>14266206.96
// 4183704.31</gml:upperCorner></gml:Envelope></ogc:BBOX></ogc:And><ogc:BBOX</ogc:Filter>"),
// MAXFEATURES:"40",
        			SRSNAME: "EPSG:900913",
        			OUTPUT: "text/xml;subType=gml/3.1.1/profiles/gmlsf/1.0.0/0",
        			EXCEPTIONS: "text/xml"
    			},
				callback: function(response){
        			var g = new OpenLayers.Format.GML();
// var features = g.read(response.responseText);
        		}
    		});
    	},
    	showFeature: function(features) {
    	},
    	getLotAreaFeature: function (response){
    		var g = new OpenLayers.Format.GML();
    		features = g.read(response.responseText);

// EMD.userLayer.landCode = new OpenLayers.Layer.Vector("lotcode", {
// strategies: [new OpenLayers.Strategy.Fixed()],
// protocol: new OpenLayers.Protocol.HTTP({
// url: "gml/lotcode3.xml",
// format: new OpenLayers.Format.GML()
// }),
// styleMap: new OpenLayers.StyleMap({
// 'default':{
// strokeColor: "#000000",
// strokeOpacity: 1,
// strokeWidth: 1,
// fillColor: "#303294",
// fillOpacity: 0.5,
// pointRadius: 6,
// pointerEvents: "visiblePainted",
// // label with \n linebreaks
// label : "${JIBUN}",
//
// fontColor: "white",
// fontSize: "12px",
// fontFamily: "Courier New, monospace",
// fontWeight: "bold",
// labelAlign: "cm",
// labelXOffset: "${xOffset}",
// labelYOffset: "${yOffset}",
// labelOutlineColor: "black",
// labelOutlineWidth: 1
// },
// 'select': {
// strokeColor: "#00FF00",
// strokeOpacity: 1,
// strokeWidth: 1,
// fillColor: "#303294",
// fillOpacity: 1,
// pointRadius: 6,
// pointerEvents: "visiblePainted",
// // label with \n linebreaks
// label : "${JIBUN}",
// fontColor: "black",
// fontSize: "12px",
// fontFamily: "Courier New, monospace",
// fontWeight: "bold",
// labelAlign: "cm",
// labelXOffset: "${xOffset}",
// labelYOffset: "${yOffset}",
// labelOutlineColor: "white",
// labelOutlineWidth: 3
// }
// }),
// renderers: OpenLayers.Layer.Vector.prototype.renderers
// });
		},
		/**
		 * 팝업 메뉴의 요소를 정의 한다.
		 *
		 * @param popup
		 * @returns
		 */
		makePopup:function(popup, feature) {
			$("button[data-role='assetInqire']", popup.contentDiv).button({icons: {
				  primary: 'ui-icon-folder-open'}}).click(function(event) {
                  	EMD.util.create_window('gamAssetCodeList', $(this).text(), "/code/assets/gamAssetCodeList.do", null, {
                    		action: "prtFcltyInqire"
                			,gisPrtAtCode: $(this).data("assets-prt-at-code")
                			,gisAssetsCd: $(this).data("assets-cd")
                			,gisAssetsSubCd: $(this).data("assets-sub-cd")
          			});
				  });
              $("button[data-role='removeFeature']", popup.contentDiv).button({icons: {
				  	primary: 'ui-icon-trash'}}).click({feature: feature}, function(event) {
	                    EMD.map.removePopup(event.data.feature.popup);
	                    // EMD.gis.removeFeature('gisAssetsCd',
						// event.data.feature);
	                    event.data.feature.state = OpenLayers.State.DELETE;
	                    EMD.userLayer.gisAssetsCd.removeFeatures([event.data.feature]);
						EMD.saveStrategy.save();
				  });
              popup.updateSize();
		}
    },
    /**
	 * 사용자 권한 관련 함수
	 */
    user: {
    	/*
		 * 사용자 권한이 있는지 확인 하는 펑션 함수명 : EMD.user.hasAuth(authorCode) 파라메터 :
		 * authorCode - 서버의 권한 코드 (여러 개일 경우 , (컴마) 로 구분함 띄어쓰기 없음) 리턴값 : bool -
		 * 해당 권한이 있으면 true 아니면 false
		 */
    	hasAuth: function(authorCode) {
    		var roles = authorCode.split(",");
    		if(EMD.userinfo!=null && EMD.userinfo.authorities!=null && EMD.userinfo.authorities.length>0) {
    			for(var i=0; i<EMD.userinfo.authorities.length; i++) {
    				for(var j=0; j<roles.length; j++) {
    					if(EMD.userinfo.authorities[i]==roles[j]) return true;
    				}
    			}
    		}
    		return true;	// 감리 대비용 권한 모두 해제
    	},
    	userId: function() {
    		return EMD.userinfo['userId'];
    	}
    },
    /**
	 * EMD 유틸리티 함수
	 */
    util: {
    	/**
		 * 그리드를 프린트 한다. (현재는 그리드가 아니고 프린트 페이지를 출력 한다.)
		 *
		 * @param module -
		 *            실행 모듈 명
		 * @param url -
		 *            실행 주소
		 * @param gridNm -
		 *            그리드 명
		 * @param searchOptNm -
		 *            조회 조건
		 * @returns
		 */
    	printPageGrid: function(module, url, gridNm, searchOptNm) {
    		var params = {};
    		if(searchOptNm!=null) {
    			params = module.getFormValues('#'+searchOptNm);
    		}
    		module.printPage(url, params);
		},
		/**
		 * 페이지를 프린트 한다.
		 *
		 * @param module -
		 *            실행 모듈 명
		 * @param url -
		 *            실행 주소
		 * @param searchOptNm -
		 *            조회조건 폼 이름
		 * @returns
		 */
    	printPageValue: function(module, url, searchOptNm) {
    		var params = {};
    		if(searchOptNm!=null) {
    			params = module.getFormValues('#'+searchOptNm);
    		}
    		module.printPage(url, params);
		},
    	/**
		 * 오브젝트를 JSON 형태로 변환 하여 리턴 한다. 함수명 : EMD.util.printObj(obj, replacer)
		 * 파라메터 : obj - 오브젝트나 리스트 변수 리턴값 : string - JSON 문자열
		 */
    	printObj: function( obj ) {
    		if(typeof JSON !== "undefined") return JSON.stringify(obj, function(k,v){if (typeof v==='number' && !isFinite(v)) {return String(v);}return v;});
    		  var arr = [];
    		  $.each( obj, function( key, val ) {
    		    var next = key + ": ";
    		    next += $.isPlainObject( val ) ? printObj( val ) : val;
    		    arr.push( next );
    		  });
    		  return "{ " +  arr.join( ", " ) + " }";
		},
		showHelp: function( url ) {
			var win = window.open(url,"helpPage","width=940, height=800,toolbar=yes, resizable=yes, menubar=no,status=no,scrollbars=yes");
		},
		/**
		 * 날짜 함수 (인자가 없으면 오늘 날짜를 리턴한다.)
		 */
    	getDate: function (d) {
    		if(d==null) d=new Date();
    		  var s =
    		    EMD.util.leftPad(d.getFullYear(), '0', 4) + '-' +
    		    EMD.util.leftPad(d.getMonth() + 1, '0', 2) + '-' +
    		    EMD.util.leftPad(d.getDate(), '0', 2);

    		  return s;
    	},
    	/**
		 * 날짜를 비교해서 a가 작으면 -1 같으면 0 크면 1을 리턴한다.
		 *
		 * @param a
		 * @param b
		 * @returns
		 */
    	compareDate: function(a, b) {
    		if(typeof a=="string") a=EMD.strToDate(a);
    		if(typeof b=="string") b=EMD.strToDate(b);
            return (
                    isFinite(a=this.convert(a).valueOf()) &&
                    isFinite(b=this.convert(b).valueOf()) ?
                    (a>b)-(a<b) :
                    NaN
                );
    	},
    	/**
		 * 날짜를 비교하여 해당 날짜가 기간 안에 속해 있는지 검사하는 함수.
		 *
		 * @param d -
		 *            검사할 날짜
		 * @param s -
		 *            기간 시작 날짜
		 * @param e -
		 *            기간 종료 날짜
		 * @returns
		 */
    	inRangeDate: function(d, s, e) {
    	       return (
    	               isFinite(d=this.convert(d).valueOf()) &&
    	               isFinite(start=this.convert(start).valueOf()) &&
    	               isFinite(end=this.convert(end).valueOf()) ?
    	               start <= d && d <= end :
    	               NaN
    	           );
    	},
    	/**
		 * 시간 도장 함수 (인자가 없으면 현재 시간을 리턴한다.)
		 *
		 * @param d -
		 *            입력 날짜, 지정 안하면 현재 날짜에 대한 시간 도장을 리턴한다.
		 * @returns
		 */
    	getTimeStamp: function (d) {
    		if(d==null) d=new Date();
    		  var s =
    			  EMD.util.leftPad(d.getFullYear(), '0', 4) + '-' +
    			  EMD.util.leftPad(d.getMonth() + 1, '0', 2) + '-' +
    			  EMD.util.leftPad(d.getDate(), '0', 2) + ' ' +

    			  EMD.util.leftPad(d.getHours(), '0', 2) + ':' +
    			  EMD.util.leftPad(d.getMinutes(), '0', 2) + ':' +
    			  EMD.util.leftPad(d.getSeconds(), '0', 2);

    		  return s;
    	},
    	/**
		 * 현재 날짜나 지정한 날짜에 달을 더하거나 빼서 리턴 한다.
		 *
		 * @param md
		 * @param m
		 * @returns
		 */
    	addMonths: function(md, m) {
    		var date=md;
    		if(m==null) {
    			date = new Date;
    		}
    		else md=m;
    		return new Date(date.setMonth(date.getMonth()+md));
    	},
    	/**
		 * 날짜에 일수를 더한 날짜를 리턴한다.
		 *
		 * @param dd
		 * @param d
		 * @returns
		 */
    	addDates: function(dd, d) {
    	    var date = dd;
    	    if (d == undefined) {
    	      date = new Date();
    	     }
    	    else {
    	    	dd=d;
    	    }
    	    return new Date(date.setDate(date.getDate() + dd));
    	},
    	/**
		 * 왼쪽에 문자를 체워 넣는다
		 *
		 * @param n
		 * @param c
		 * @param digits
		 * @returns
		 */
    	leftPad: function(n, c, digits) {
    		  var zero = '';
    		  n = n.toString();

    		  if (n.length < digits) {
    		    for (var i = 0; i < digits - n.length; i++)
    		      zero += c;
    		  }
    		  return zero + n;
		},
		/**
		 * 메세지 팝업을 출력한다.
		 *
		 * @param obj -
		 *            출력할 대상 객체 (INPUT, BUTTON 타입 속성)
		 * @param msg -
		 *            출력할 메세지
		 * @returns
		 */
		showMessage: function(obj, msg) {
       		var popup = new dhtmlXPopup();
       		popup.attachHTML(msg);
       		if(obj['length']!=undefined) obj=obj[0];
       		if($(obj).is(":input")) {
       			if($(obj).hasClass("ygpaCmmnCd")
       					||$(obj).hasClass("ygpaFilterCode")
       					||$(obj).hasClass("ygpaDeptSelect")
       					||$(obj).hasClass("ygpaCmmnCl")
       					||$(obj).hasClass("ygpaCmmnCode")
       					||$(obj).hasClass("ygpaYnSelect")
       					) {
       				obj=$('#'+$(obj).attr('id')+'_select')[0];
       				if(obj==null) {
       					alert(mag);
       				}
       			}
       			$(obj).focus();
           		var x = window.dhx4.absLeft(obj);
    	        var y = window.dhx4.absTop(obj);
    	        var w = obj.offsetWidth;
    	        var h = obj.offsetHeight;
    			popup.show(x,y,w,h);
           		$(obj).blur({popup:popup}, function(e) {
           			if(e.data.popup) {
           				e.data.popup.unload();
           				e.data.popup=null;
           			}
           		});
           		return popup;
       		}
       		return null;
		},
		/**
		 * 에러 메세지 팝업을 출력한다.
		 *
		 * @param obj -
		 *            출력할 대상 객체 (INPUT, BUTTON 타입 속성)
		 * @param msg -
		 *            출력할 메세지
		 * @returns
		 */
		showErrorMessage: function(obj, msg) {
       		var popup = new dhtmlXPopup();
       		popup.attachHTML(msg);
       		if($(obj).is(":input")) {
           		var x = window.dhx4.absLeft(obj);
    	        var y = window.dhx4.absTop(obj);
    	        var w = obj.offsetWidth;
    	        var h = obj.offsetHeight;
    			popup.show(x,y,w,h);
           		$(obj).blur({popup:popup}, function(e) {
           			if(e.data.popup) {
           				e.data.popup.unload();
           				e.data.popup=null;
           			}
           		});
           		return popup;
       		}
       		return null;
		},
		/**
		 * number format
		 *
		 * @param nStr
		 * @returns
		 */
    	addCommas: function (nStr)
    	{
    		nStr += '';
    		x = nStr.split('.');
    		x1 = x[0];
    		x2 = x.length > 1 ? '.' + x[1] : '';
    		var rgx = /(\d+)(\d{3})/;
    		while (rgx.test(x1)) {
    			x1 = x1.replace(rgx, '$1' + ',' + '$2');
    		}
    		return x1 + x2;
    	},
    	/**
		 * 날짜와 시간 문자열을 Date 포맷으로 변환
		 *
		 * @param dStr
		 * @returns
		 */
    	strToDate: function(dStr) {
      	  var regiDate = dStr.replace(/-/g,'/');;
    	  return new Date(Date.parse(regiDate));
    	},
    	/**
		 * 날짜 인지 검사
		 *
		 * @param txtDate
		 * @returns
		 */
    	isDate: function (txtDate)
    	{
    	  var currVal = txtDate;
    	  if(currVal == '')
    	    return false;

    	  var rxDatePattern = /^(\d{1,4})-(\d{1,2})-(\d{2})$/;
    	  var dtArray = currVal.match(rxDatePattern);

    	  if (dtArray == null)
    	     return false;

    	  dtYear = dtArray[1];
    	  dtMonth = dtArray[2];
    	  dtDay= dtArray[3];

    	  if (dtMonth < 1 || dtMonth > 12)
    	      return false;
    	  else if (dtDay < 1 || dtDay> 31)
    	      return false;
    	  else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31)
    	      return false;
    	  else if (dtMonth == 2)
    	  {
    	     var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
    	     if (dtDay> 29 || (dtDay ==29 && !isleap))
    	          return false;
    	  }
    	  return true;
    	},
    	/**
		 * 사용자 정보 가져오기
		 *
		 * @returns
		 */
    	getLoginUserVO: function() {
    		return EMD.userinfo;
    	},
    	/**
		 * 진행중 대화 상자 표시 EMD.util.showProgress() 리턴 값 없음
		 */
    	showProgress: function() {
    		if(!$('#progress_dialog').dialog("isOpen"))
			  $('#progress_dialog').dialog("open");
    	},
    	/**
		 * 진행중 대화 상자 숨기기 EMD.util.hideProgress() 리턴 값 없음
		 */
    	hideProgress: function() {
    		if($('#progress_dialog').dialog("isOpen"))
			  $('#progress_dialog').dialog("close");
    	},
      /**
		 * 메뉴창을 닫는다.
		 *
		 * @returns
		 */
      clear_active: function() {
        $('a.active, tr.active').removeClass('active');
        $('ul.menu').hide();
      },
      /**
		 * 오브젝트 값을 name 과 value 형태의 어레이로 변환 한다.
		 *
		 * @param obj
		 * @returns
		 */
      objectToArray: function(obj) {
    	  var ret=[];
    	  for(var k in obj) {
    		  ret[ret.length]={
    				  name: k,
    				  value: obj[k]
    		  };
    	  }
    	  return ret;
      },
      /**
		 * 윈도우 창을 생성한다.
		 *
		 * @param win_title -
		 *            창 제목 (String)
		 * @param win_url -
		 *            호출 창 컨텐츠 주소 (String)
		 * @param argv -
		 *            호출할때 서버에 전달 할 파라메터 (Object)
		 * @param init_params -
		 *            창이 열리고 loadComplete 할때 전달할 변수 (Object)
		 * @param parent -
		 *            부모창 모듈
		 * @returns - 윈도우 id
		 */
      create_window: function(progrmFileNm, win_title, win_url, argv, init_params, parent) {
    	  var x = '#window_'+ EMD.window_seq;
    	  var dock_id='#dock_'+ EMD.window_seq;

		  EMD.window_seq++;

		  var li=document.createElement('li');
		  li.id=dock_id.substring(1);
		  li.innerHTML='<a href="'+x+'">'+win_title+'</a>';
		  $('#dock')[0].appendChild(li);

		  var win = document.createElement('div');
		  win.id=x.substring(1);
// win.style.visibility='hidden';
		  win.style.display='none';
		  if(progrmFileNm!=null && progrmFileNm!="") {
			  win.innerHTML='<div class="abs window_inner">'+
			  '<div class="window_top ui-widget-header">'+
				'<span class="float_left">'+
				win_title +
				'</span> <span class="float_right"><a href="#'+progrmFileNm+'" class="window_help"><span class="ui-icon ui-icon-help"></span></a> <a href="#'+win.id+'" class="window_min"></a><a href="#'+win.id+'" class="window_resize"></a> <a href="#'+win.id+'" class="window_close"></a></span>'+
			'</div><div class="abs window_content"></div></div><div class="abs window_bottom ">여수광양항만공사 지도프레임워크 ver : 2015.04.14.001</div><span class="abs ui-resizable-handle ui-resizable-se"></span>';
		  }
		  else {
			  win.innerHTML='<div class="abs window_inner">'+
			  '<div class="window_top ui-widget-header">'+
				'<span class="float_left">'+
				win_title +
				'</span> <span class="float_right"><a href="#'+win.id+'" class="window_min"></a><a href="#'+win.id+'" class="window_resize"></a> <a href="#'+win.id+'" class="window_close"></a></span>'+
			'</div><div class="abs window_content"></div></div><div class="abs window_bottom ">여수광양항만공사 지도프레임워크 ver : 2015.04.14.001</div><span class="abs ui-resizable-handle ui-resizable-se"></span>';
		  }
		  $('#desktop')[0].appendChild(win);
		  $(x).addClass('abs');
		  $(x).addClass('window');
		  $(x)[0]._initParam = init_params;
		  if(win_url!=undefined) {
			  EMD.util.showProgress();
			  $(x+ ' .window_content' ).load(EMD.context_root+win_url, {"window_id": x.substring(1), "args": argv},
    			  function( response, status, xhr ) {
				  		EMD.util.hideProgress();
    				  if ( status == "error" ) {
    				    var msg = "에러가 발생했습니다. 에러번호: ";
    				    alert( msg + xhr.status + " " + xhr.statusText );
    				  }
    				  else {
    					  if(module_instance.getModuleType()=="_EMD_ERROR_MODULE") {
    						  module_instance.loadComplete();
    						  alert(module_instance.getMessge());
    						  EMD.util.window_close($(this).closest('.window'));
    						  return;
    					  }
    					  var win_id=$("#window_id").val();
    					  if(win_id==undefined) {
	        				    alert( '서버 오류로 인하여 페이지를 로드할 수 없습니다.');
	        				    EMD.util.window_close($(this).closest('.window'));
    						  return;
    					  }
    					  var win_id_num=win_id.substring(win_id.lastIndexOf('_')+1)*1;
    					  $("#window_id").remove();
    					  $('#'+win_id)[0].module = module_instance;
    					  module_instance.setModuleId(win_id_num);
    					  module_instance.setParent(parent);

    					  EMD.module_instance[win_id_num]=module_instance;
    					  module_instance.setModuleId(win_id);
    					  EMD.util.modify_window(module_instance, win_id);
    					  var x, y, w, h;
    					  x=EMD.win_x;
    					  y=EMD.win_y;
    					  w=EMD.module_instance[win_id_num].getWidth();
    					  h=EMD.module_instance[win_id_num].getHeight();
    					  if($(document).height()-48<y+h) {
    						  y=$(document).height()-h-28;
    						  if(y<20) y=20;
    					  }
    					  if($(document).width()<x+w) {
    						  x=0;
    					  }
    					  EMD.util.window_move(win_id, x, y);
    					  EMD.util.window_size(win_id, w, h);
        			  		$('#'+win_id).resizable({
        						create: function(event, ui) {

        						},
        						resize: function(event, ui) {
        							event.stopPropagation();
        							var win=$(this);
        				    		  $(this).find('.window_main').each(function() {
        				    			  $(this).css('height', win.height()-54);
        				    		  });
        				    		  EMD.util.window_resized(ui.element);
        						}
        					});

        			  		$('#'+win_id).find(':input[data-required="true"]').each(function() {
        			  			var th =$(this).parent("td").prev();
        			  			if(th.data('required')==null) {	// check insert
																// before
	        			  			th.append("<span style='color:red; font-weight:bold; float:right;'>*</span>");
	        			  			th.data('required', 'true');
        			  			}
        			  		});

    					  EMD.module_instance[win_id_num].loadComplete($(this).closest(".window")[0]._initParam);

    		                EMD.util.window_flat();
        					$('#'+win_id).addClass('window_stack').show('drop', {}, 600, function(){
        						EMD.util.window_resized($(this));
        					});
			    		  $($('#'+win_id)).find('.window_main').each(function() {
			    			  $(this).css('height', $('#'+win_id).height()-54);
			    		  });
			    		  EMD.util.window_resized($('#'+win_id));

    		                EMD.util.clear_active();

    				  }
    				});
		  }
		  return win.id;
      },
      window_resized: function(win) {
			var resizeFunc = function(func, resizeDiv) {
  				var resizeFill = null;
  	  			var items = $(resizeDiv).children();
  	  			var h = $(resizeDiv).height();
  	  			for(var i=0; i<items.length; i++) {
  	  				var item = items[i];
  	  				if($(item).hasClass('fillHeight')) {
  	  					resizeFill=item;
  	  				}
  	  				else {
  	  					if(!$(item).is("script") || (($(item).is(".emdTabPage") || $(item).is(".viewStack")) && $(item).attr("display")!="none")) {
	  	  					h-=$(item).outerHeight(true);
  	  					}
  	  				}
  	  			}
  	  			if(resizeFill!=null) {
  	  				h-=$(resizeFill).outerHeight(true)-Math.ceil($(resizeFill).height());

  	  				if(h>0) {
	  	  				if($(resizeFill).is(".emdTabPanel")) {
	  	  					$(resizeFill).height(h);
	  				    	$(resizeFill).tabs("option", "heightStyle", "fill");
	  				    	$(resizeFill).children(".emdTabPage").each(function() {
	  	  				    	func(func, this);	// 탭페이지에 리사이즈 적용
	  				    	});
						}
	  	  				else {
	  	  					var overflow = $(resizeFill).parent().css("overflow");
	  	  					$(resizeFill).parent().css("overflow", "hidden");
	  	  					$(resizeFill).height(h);
	  	  					if($(resizeFill).hasClass("gridbox")) {
	  	  						$(resizeFill).width($(resizeFill).parent().width()-4);
	  	  						$(resizeFill).resizeGrid();
	  	  					}
	  	  					else
	  	  						func(func, resizeFill);
		  					$(resizeFill).parent().css("overflow", overflow);
	  	  				}
  	  				}
  	  			}
			};
			if($(win).is('#__dialog-modal')) {
				resizeFunc(resizeFunc, $(win).find(".dialog")[0]);
			}
			else {
				resizeFunc(resizeFunc, $(win).find(".window_main")[0]);
			}
      },
      /**
		 * 모든창의 숨김 상태를 해제한다.
		 *
		 * @returns
		 */
      window_flat: function() {
        $('div.window_stack').removeClass('window_stack');
      },
      window_show: function(win) {
          $('div.window').removeClass('window_stack');
          win.addClass('window_stack').css({
              'top': win.data('t'),
              'left': win.data('l'),
              'right': win.data('r'),
              'bottom': win.data('b'),
              'width': win.data('w'),
              'height': win.data('h')
            }).show();
// win.addClass('window_stack').show('drop', {}, 300, function(){
// EMD.util.window_resized($(this));
// });
// win.addClass('window_stack').effect('transfer', {to: win, className:
// '.ui-effects-transfer'}, 500
// , function(){ EMD.util.window_resized($(this)); });
      },
      window_move: function(win_id, x, y) {
    	  $('#'+win_id).css({'left':x, 'top':y });
      },
      window_size:function(win_id, w, h) {
    	  $('#'+win_id).css({'width':w, 'height':h});
      },
      //
      // Resize modal window.
      //
      window_resize: function(win) {
        // Is it maximized already?
        if (win.hasClass('window_full')) {
          // Restore window position.
          win.removeClass('window_full').css({
            'top': win.data('t'),
            'left': win.data('l'),
            'right': win.data('r'),
            'bottom': win.data('b'),
            'width': win.data('w'),
            'height': win.data('h')
          });
        }
        else {
        	win.data('t', win.css('top'));
        	win.data('l', win.css('left'));
        	win.data('r', win.css('right'));
        	win.data('b', win.css('bottom'));
        	win.data('w', win.css('width'));
        	win.data('h', win.css('height'));
          win.addClass('window_full').css({
            // Maximize dimensions.
            'top': '0',
            'left': '0',
            'right': '0',
            'bottom': '0',
            'width': '100%',
            'height': '100%'
          });
        }

        // Bring window to front.
        EMD.util.window_flat();
        win.addClass('window_stack');

        EMD.util.window_resized(win);

      },
      /**
		 * 모든 창을 최소화 한다.
		 *
		 * @returns
		 */
      window_hide_all: function() {
          $('div.window').each(function() {
        	  EMD.util.window_hide($(this));
    	  });
	  },
	  /**
		 * 창을 최소화 한다.
		 *
		 * @param win
		 * @returns
		 */
      window_hide: function(win) {;
          var dock_id = win.attr('id').replace('window', 'dock');
      	win.data('t', win.css('top'));
    	win.data('l', win.css('left'));
    	win.data('r', win.css('right'));
    	win.data('b', win.css('bottom'));
    	win.data('w', win.css('width'));
    	win.data('h', win.css('height'));
    	win.effect('transfer', {to: '#'+dock_id, className: '.ui-effects-transfer'}, 300
    			  , function(){ win.removeAttr('style').hide(); });
      },
      /**
		 * 창을 닫는다.
		 *
		 * @param win
		 * @returns
		 */
      window_close: function(win) {
          var dock_id = win.attr('id').replace('window', 'dock');

          win.hide(function() {
        	  $(this).remove();
          }
          );

          $('#'+dock_id).hide('fast');
          $('#'+dock_id).remove();
      },
      /**
		 * 최종 생성된 창을 하나 닫는다.
		 *
		 * @returns
		 */
      last_window_close: function() {
    	  win = $('#window_'+(EMD.window_seq-1));
          var dock_id = win.attr('href').replace('window', 'dock');

          $(win.attr('href')).hide('scale', { percent: 0 }, 1000, function() {
        	  $(this).remove();
          }
          );

          $(dock_id).hide('fast');
          $(dock_id).remove();
      },
      save_feature: function() {
    	  },
      edit_feature: function(win, feature) {
			if(feature!=undefined || feature!=null) EMD.wfs.addFeatures(feature);
			EMD.edit_win = win;
	  },
      select: function(selector) {
      },
      modify_id: function(win_id,modify_item) {
    	  if(!modify_item.children().length) return;
    	  modify_item.children().each(function() {
    		  if($(this).children().length>0) EMD.util.modify_id(win_id, $(this));
    		  if($(this).attr("id")!=undefined) $(this).attr("id",win_id+"_"+$(this).attr("id"));
    		  if($(this).attr("data-grid")!=undefined) $(this).attr("data-grid",win_id+"_"+$(this).attr("id"));
    	  });
      },
      modify_window: function(module_instance,win_id) {
    	  var win = $('#'+win_id);
    	  EMD.util.modify_id(win_id, $('#'+win_id));
    	  win.data('_win_id', win_id);
    	  win.find(".emdTabPanel").data('_win_id', win_id);
    	  win.find(".emdTabPanel").each(function() {
  			$(this).find(".emdTab").data('_win_id', $(this).data('_win_id'));
  			$(this).find(".emdTab").each(function() {
  				$(this).attr('href', $(this).attr('href').replace('#', '#'+$(this).data('_win_id')+'_'));
  			});
  			$(this).tabs({
  				beforeActivate: function(event, ui) {
  					var onchangeId=$(this).data("onchange-before");

  					var oldId=ui.oldPanel.attr("id").replace($(this).closest('.window').data('_win_id')+'_', '');
  					var newId=ui.newPanel.attr("id").replace($(this).closest('.window').data('_win_id')+'_', '');

  					if(onchangeId!=null)
  						return $(this).closest('.window')[0].module[onchangeId](newId, oldId);
  				},
  				activate: function(event, ui) {
  					var onchangeId=$(this).data("onchange");
  					var oldId=ui.oldPanel.attr("id").replace($(this).closest('.window').data('_win_id')+'_', '');
  					var newId=ui.newPanel.attr("id").replace($(this).closest('.window').data('_win_id')+'_', '');

  					if(onchangeId!=null)
  						$(this).closest('.window')[0].module[onchangeId](newId, oldId);
  					// EMD.util.window_resized($(this).closest('.window'));
  				},
  				select: function(e, i) {
  					currentTab = i.index;
  				},
  				nextTab: function(e) {
  					currentTab =  currentTab < $(this).tabs("length")-1 ? currentTab+1:0;
  				},
  			});
  		});
    	  win.find(".viewStack").each(function() {
    		  $(this).changeViewId = function(index) {
    			  $(this)._viewStackId = index;
    			  $(this).find(".viewPanel").each(function(id) {
    				  if(id!=$(this).parent(".viewStack")._viewStackId) $(this).css("display", "none");
    				  else $(this).css("display", "block");
    			  });
    			  $(this).closest(".window_main").trigger("resizeWindow");
    		  };
    		  $(this).find(".viewPanel").each(function(index) {
    			  if(index!=0) $(this).css("display", "none");
    		  });
			  $(this)[0].changePanelId=function(num) {
    				  $(this).find(".viewPanel").each(function(index) {
        				  if(index==num) $(this).css("display", "block");
        				  else $(this).css("display", "none");
        			  });
        			  EMD.util.window_resized($(this).closest('.window'));
    			  };
    	  });
    	  // button mapping
    	  win.find("button").each(function() {
    		  var id=$(this).data("cmd");
    		  var but = $(this);
    		  var isbutton=false;
    		  var icon=null;
    		  if(id==null) id=$(this).attr('id')==null||$(this).attr('id').length<win_id.length+1?$(this).attr('class'):$(this).attr('id').substring(win_id.length+1);
    		  if(but.attr('class')!=null) {
        		  var classes = $(this).attr('class').split(/\s+/);
        		  $.each(classes, function(index, item) {
        			  switch(item) {
        			  case 'buttonRegist':
        				  icon='ui-icon-check';
        				  break;
        			  case 'buttonAdd':
        				  icon='ui-icon-plusthick';
        				  break;
        			  case 'buttonDelete':
        				  icon='ui-icon-minusthick';
        				  break;
        			  case 'buttonTrash':
	    				  icon='ui-icon-trash';
	    				  break;
        			  case 'buttonSave':
        				  icon='ui-icon-disk';
        				  break;
        			  case 'buttonSearch':
        				  icon='ui-icon-search';
        				  break;
        			  case 'popupButton':
        				  icon='ui-icon-newwin';
        				  break;
        			  case 'buttonExcel':
        				  icon='ui-icon-document';
        				  break;
        			  case 'popupDialog':
        				  icon='ui-icon-newwin';
        				  break;
    				  default:
    					  icon=$(this).data('icon');
        					  break;
        			  }
        			  if (icon!=null && icon.indexOf('ui-icon-') === 0) {
        				  $(but).button(
        						  { icons: {
        							  primary: icon
        						  },
        						  text : true
        						  }).click({module:win[0].module, button_id: id}, function(event) {
        							  event.preventDefault();
        							  if(id=="buttonSearch") {
        								  event.data.module.onSubmit();
        							  }
        							  else {
            							  EMD.util._clickButtonExec(event.data.module, $(this), event.data.button_id);
        							  }
        						  });
        				  isbutton=true;
        				  return false;
        			  }
        		  });
        		  if(isbutton==false) {
    				  $(but).button().click({module:win[0].module, button_id: id}, function(event) {
    							  event.preventDefault();
    							  EMD.util._clickButtonExec(event.data.module, $(this), event.data.button_id);
    						  });
        		  }
    		  }
    		  else {
    			  if(id==null) id=$(but).data('id');
    			  if(id==null) id=$(but).data('role');
    			  var label = $(but).text();
    			  icon=null;
    			  for(var i=0; i<buttonIcons.length; i++) {
    				  if(buttonIcons[i].label==label) {
    					  icon=buttonIcons[i].icon;
    					  break;
    				  }
    			  };

				  if(icon!=null) {
					  $(but).button({ icons: {
						  primary: icon
					  },
					  text : true}).click({module:win[0].module, button_id: id}, function(event) {
						  event.preventDefault();
						  EMD.util._clickButtonExec(event.data.module, $(this), event.data.button_id);
					  });
				  }
				  else {
					// console.log('unkown button label : '+$(but).text());
					  $(but).button().click({module:win[0].module, button_id: id}, function(event) {
						  event.preventDefault();
						  EMD.util._clickButtonExec(event.data.module, $(this), event.data.button_id);
					  });
				  }
    		  }

    	  });

    	  // 입력 컴포넌트 처리
    		$('#'+win_id).find("input.mngYear").each(function() {
    			$(this).attr('maxlength', 4);
    			$(this).on("keyup", function(event) {
    				if($(this).val().length==4) {
    					$(this).next(':input.mngNo').focus();
    				}
    			});
    		});

    		$('#'+win_id).find("input.mngNo").each(function() {
    			$(this).attr('maxlength', 3);
    			$(this).on("keyup", function(event) {
    				if($(this).val().length==3) {
    					$(this).next(':input.mngCnt').focus();
    				}
    			});
    		});

    		$('#'+win_id).find("input.mngCnt").each(function() {
    			$(this).attr('maxlength', 2);
    		});

    		$('#'+win_id).find("input.gisAssetsCd").each(function() {
    			$(this).attr('maxlength', 3);
    			$(this).on("keyup", function(event) {
    				var v=$(this).val();
    				if(v.length==3) {
    					$(this).val(v.toUpperCase());
    					$(this).next(':input.gisAssetsSubCd').focus();
    				}
    			});
    		});

    		$('#'+win_id).find("input.gisAssetsSubCd").each(function() {
    			$(this).attr('maxlength', 2);
    		});

  		// 부서코드
  		$('#'+win_id).find("input.ygpaDeptSelect").each(function() {
  			var default_prompt='';
  			var default_value='';
  			var input_item=this;
  			var input_id=$(this).attr('id');
  			if($(this).data('default-prompt')!=null) {
  				if($(this).data('default-prompt')!=null) default_prompt=$(this).data('default-prompt');
  				else default_prompt="선택";
  				if($(this).data('default-value')!=null) default_value=$(this).data('default-value');
  				else default_value="";
  			}
  			if($(this).data('display-value')==null) {
  				try {
  	  	  			$(this).attr("type", "hidden");
  				}
  				catch(err) {
  	  	  			$(this).attr("id", input_id+"_backup");
  	  	  			$(this).after('<input type="hidden" id="'+input_id+'" />');
  	  	  			input_item = $('#'+win_id+' #'+input_id)[0];
  	  	  			$(this).remove();
  				}
  	  			$(input_item).after('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
  			else if($(this).data('display-value')=='N') {
  	  			$(this).attr("readonly", "readonly");
  	  			$(this).before('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
  			else {
  	  			$(this).attr("readonly", "readonly");
  	  			$(this).after('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
			if(default_prompt!='') {
					$('#'+win_id).find('#'+input_id+'_select').append('<option value="'+default_value+'" selected>'+default_prompt+'</option>');
			}
			$(this).focus(function() {
				$('#'+$(this).attr("id")+"_select")[0].focus();
			});

  			$.ajax({
  				url: EMD.context_root+'/cmmn/selectDeptCodeOptionsList.do',
  				type: 'POST',
  				module: input_item,
  				dataType: 'json',
  				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
  				data: [{name: 'cmd', value: ''}],
				success: function (data) {
					if(data.resultCode!=null && data.resultCode!=0) {
						if(data.resultCode==1) {	// 로그인 에러
							alert(data.resultMsg);
							location.reload();
						}
					}
					else {
						var selected_code='';
						var comp_id='#'+$(this.module).attr('id')+'_select';
						if($(this.module).val()!=null) {
							selected_code=$(this.module).val();
		  					for(var i=0; i<data.resultList.length; i++) {
		  						if(selected_code==data.resultList[i].value) {
			  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'" selected>'+data.resultList[i].name+'</option>');
		  						}
		  						else {
			  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'">'+data.resultList[i].name+'</option>');
		  						}
		  					}
						}
						else {
		  					for(var i=0; i<data.resultList.length; i++) {
		  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'">'+data.resultList[i].name+'</option>');
		  					}
						}
						$('#'+win_id).find(comp_id).change({module: this.module}, function(event){
							$(event.data.module).val( $(this).find("option:selected").val() );
							$(event.data.module).trigger('change');
						});
						if($(this.module).attr("readonly")=="readonly") $(comp_id).attr("readonly", "readonly");
						if($(this.module).attr("disabled")=="disabled") $(comp_id).attr("disabled", "disabled");
					}
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
					try {
						// console.log(textStatus);
// if (p.onError) p.onError(XMLHttpRequest, textStatus, errorThrown);
					} catch (e) {}
				}
  				});
  		});

  		// 공통코드 분류 처리
  		$('#'+win_id).find("input.ygpaCmmnCl").each(function() {
  			var default_prompt='';
  			var default_value='';
  			var input_item=this;
  			var input_id=$(this).attr('id');
  			if($(this).data('default-prompt')!=null) {
  				if($(this).data('default-prompt')!=null) default_prompt=$(this).data('default-prompt');
  				else default_prompt="선택";
  				if($(this).data('default-value')!=null) default_value=$(this).data('default-value');
  				else default_value="";
  			}
  			if($(this).data('display-value')==null) {
  				try {
  	  	  			$(this).attr("type", "hidden");
  				}
  				catch(err) {
  	  	  			$(this).attr("id", input_id+"_backup");
  	  	  			$(this).after('<input type="hidden" id="'+input_id+'" />');
  	  	  			input_item = $('#'+win_id+' #'+input_id)[0];
  	  	  			$(this).remove();
				}
  				$(input_item).after('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
  			else if($(this).data('display-value')=='N') {
  	  			$(this).attr("readonly", "readonly");
  	  			$(this).before('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
  			else {
  	  			$(this).attr("readonly", "readonly");
  	  			$(this).after('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
			if(default_prompt!='') {
					if($(input_item).data('value')==null) $('#'+win_id).find('#'+input_id+'_select').append('<option value="'+default_value+'" selected>'+default_prompt+'</option>');
					else $('#'+win_id).find('#'+input_id+'_select').append('<option value="'+default_value+'">'+default_prompt+'</option>');
			}
			$(this).focus(function() {
				$('#'+$(this).attr("id")+"_select")[0].focus();
			});
  			$.ajax({
  				url: EMD.context_root+'/cmmn/selectCmmnClOptionsList.do',
  				type: 'POST',
  				module: input_item,
  				dataType: 'json',
  				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
  				data: [],
				success: function (data) {
					if(data.resultCode!=null && data.resultCode!=0) {
						if(data.resultCode==1) {	// 로그인 에러
							alert(data.resultMsg);
							location.reload();
						}
					}
					else {
						var selected_code='';
						var comp_id='#'+$(this.module).attr('id')+'_select';
						$('#'+win_id).find(comp_id).empty();
						if($(this.module).val()!=null) {
							selected_code=$(this.module).val();
		  					for(var i=0; i<data.resultList.length; i++) {
		  						if(selected_code==data.resultList[i].value) {
			  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'" selected>'+data.resultList[i].name+'</option>');
		  						}
		  						else {
			  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'">'+data.resultList[i].name+'</option>');
		  						}
		  					}
						}
						else {
		  					for(var i=0; i<data.resultList.length; i++) {
		  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'">'+data.resultList[i].name+'</option>');
		  					}
						}
						$('#'+win_id).find(comp_id).change({module: this.module}, function(event){
							$(event.data.module).val( $(this).find("option:selected").val() );
							$(event.data.module).trigger('change');
						});
						$('#'+win_id).find(comp_id).trigger('change');
						if($(this.module).attr("readonly")=="readonly") $(comp_id).attr("readonly", "readonly");
						if($(this.module).attr("disabled")=="disabled") $(comp_id).attr("disabled", "disabled");
					}
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
					// console.log(textStatus);
// try {
// if (p.onError) p.onError(XMLHttpRequest, textStatus, errorThrown);
// } catch (e) {

// }
				}
  				});
  		});

  		// 공통코드 목록 처리
  		$('#'+win_id).find("input.ygpaCmmnCode").each(function() {
  			var default_prompt='';
  			var default_value='';
  			var input_item=this;
  			var input_id=$(this).attr('id');
  			if($(this).data('cl-code')==undefined && $(this).data('cl-selector')==undefined) return;
  			if($(this).data('default-prompt')!=null) {
  				if($(this).data('default-prompt')!=null) default_prompt=$(this).data('default-prompt');
  				else default_prompt="선택";
  				if($(this).data('default-value')!=null) default_value=$(this).data('default-value');
  				else default_value="";
  			}
  			if($(this).data('display-value')==null) {
  				try {
  	  	  			$(this).attr("type", "hidden");
  				}
  				catch(err) {
  	  	  			$(this).attr("id", input_id+"_backup");
  	  	  			$(this).after('<input type="hidden" id="'+input_id+'" />');
  	  	  			input_item = $('#'+win_id+' #'+input_id)[0];
  	  	  			$(this).remove();
				}
  				$(input_item).after('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
  			else if($(this).data('display-value')=='N') {
  	  			$(this).attr("readonly", "readonly");
  	  			$(this).before('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
  			else {
  	  			$(this).attr("readonly", "readonly");
  	  			$(this).after('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
			if(default_prompt!='') {
					if($(input_item).data('value')==null) $('#'+win_id).find('#'+input_id+'_select').append('<option value="'+default_value+'" selected>'+default_prompt+'</option>');
					else $('#'+win_id).find('#'+input_id+'_select').append('<option value="'+default_value+'">'+default_prompt+'</option>');
			}
			$(this).focus(function() {
				$('#'+$(this).attr("id")+"_select")[0].focus();
			});
			var clcode = $(this).data('cl-code');
			if(clcode==undefined) {
				var clSelector=$("#" + win_id + "_" + $(this).data("cl-selector"));
				clSelector.change({module: input_item}, function(event){
					var input_item=event.data.module;
					clcode=$(this).val();
		  			$.ajax({
		  				url: EMD.context_root+'/cmmn/selectCmmnCodeOptionsList.do',
		  				type: 'POST',
		  				module: input_item,
		  				dataType: 'json',
		  				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		  				data: [{name: 'clCode', value: $(this).val()}],
						success: function (data) {
							if(data.resultCode!=null && data.resultCode!=0) {
								if(data.resultCode==1) {	// 로그인 에러
									alert(data.resultMsg);
									location.reload();
								}
							}
							else {
								var selected_code='';
								var comp_id='#'+$(this.module).attr('id')+'_select';
								$('#'+win_id).find(comp_id).empty();
								if($(this.module).val()!=null) {
									selected_code=$(this.module).val();
				  					for(var i=0; i<data.resultList.length; i++) {
				  						if(selected_code==data.resultList[i].value) {
					  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'" selected>'+data.resultList[i].name+'</option>');
				  						}
				  						else {
					  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'">'+data.resultList[i].name+'</option>');
				  						}
				  					}
								}
								else {
				  					for(var i=0; i<data.resultList.length; i++) {
				  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'">'+data.resultList[i].name+'</option>');
				  					}
								}
								$('#'+win_id).find(comp_id).change({module: this.module}, function(event){
									$(event.data.module).val( $(this).find("option:selected").val() );
									$(event.data.module).trigger('change');
								});
								if($(this.module).attr("readonly")=="readonly") $(comp_id).attr("readonly", "readonly");
								if($(this.module).attr("disabled")=="disabled") $(comp_id).attr("disabled", "disabled");
							}
						},
						error: function (XMLHttpRequest, textStatus, errorThrown) {
							// console.log(textStatus);
// try {
// if (p.onError) p.onError(XMLHttpRequest, textStatus, errorThrown);
// } catch (e) {

// }
						}
		  				});
				});
			}
			else {
	  			$.ajax({
	  				url: EMD.context_root+'/cmmn/selectCmmnCodeOptionsList.do',
	  				type: 'POST',
	  				module: input_item,
	  				dataType: 'json',
	  				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	  				data: [{name: 'cl_code', value: clcode}],
					success: function (data) {
						if(data.resultCode!=null && data.resultCode!=0) {
							if(data.resultCode==1) {	// 로그인 에러
								alert(data.resultMsg);
								location.reload();
							}
						}
						else {
							var selected_code='';
							var comp_id='#'+$(this.module).attr('id')+'_select';
							if($(this.module).val()!=null) {
								selected_code=$(this.module).val();
			  					for(var i=0; i<data.resultList.length; i++) {
			  						if(selected_code==data.resultList[i].value) {
				  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'" selected>'+data.resultList[i].name+'</option>');
			  						}
			  						else {
				  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'">'+data.resultList[i].name+'</option>');
			  						}
			  					}
							}
							else {
			  					for(var i=0; i<data.resultList.length; i++) {
			  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'">'+data.resultList[i].name+'</option>');
			  					}
							}
							$('#'+win_id).find(comp_id).change({module: this.module}, function(event){
								$(event.data.module).val( $(this).find("option:selected").val() );
								$(event.data.module).trigger('change');
							});
							if($(this.module).attr("readonly")=="readonly") $(comp_id).attr("readonly", "readonly");
							if($(this.module).attr("disabled")=="disabled") $(comp_id).attr("disabled", "disabled");
						}
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
						// console.log(textStatus);
// try {
// if (p.onError) p.onError(XMLHttpRequest, textStatus, errorThrown);
// } catch (e) {

// }
					}
	  				});
			}
  		});

  		// 공통코드 처리
  		$('#'+win_id).find("input.ygpaCmmnCd").each(function() {
  			var default_prompt='';
  			var default_value='';
  			var input_item=this;
  			var input_id=$(this).attr('id');
  			if($(this).data('code-id')==undefined) return;
  			if($(this).data('default-prompt')!=null) {
  				if($(this).data('default-prompt')!=null) default_prompt=$(this).data('default-prompt');
  				else default_prompt="선택";
  				if($(this).data('default-value')!=null) default_value=$(this).data('default-value');
  				else default_value="";
  			}
  			if($(this).data('display-value')==null) {
  				try {
  	  	  			$(this).attr("type", "hidden");
  				}
  				catch(err) {
  	  	  			$(this).attr("id", input_id+"_backup");
  	  	  			$(this).after('<input type="hidden" id="'+input_id+'" />');
  	  	  			input_item = $('#'+win_id+' #'+input_id)[0];
  	  	  			$(this).remove();
				}
  				$(input_item).after('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
  			else if($(this).data('display-value')=='N') {
  	  			$(this).attr("readonly", "readonly");
  	  			$(this).before('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
  			else {
  	  			$(this).attr("readonly", "readonly");
  	  			$(this).after('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
			if(default_prompt!='') {
					if($(input_item).data('value')==null) $('#'+win_id).find('#'+input_id+'_select').append('<option value="'+default_value+'" selected>'+default_prompt+'</option>');
					else $('#'+win_id).find('#'+input_id+'_select').append('<option value="'+default_value+'">'+default_prompt+'</option>');
			}
			$(this).focus(function() {
				$('#'+$(this).attr("id")+"_select")[0].focus();
			});
  			$.ajax({
  				url: EMD.context_root+'/cmmn/selectCmmnCdOptionsList.do',
  				type: 'POST',
  				module: input_item,
  				dataType: 'json',
  				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
  				data: [{name: 'code_id', value: $(this).data('code-id')}],
				success: function (data) {
					if(data.resultCode!=null && data.resultCode!=0) {
						if(data.resultCode==1) {	// 로그인 에러
							alert(data.resultMsg);
							location.reload();
						}
					}
					else {
						var selected_code='';
						var comp_id='#'+$(this.module).attr('id')+'_select';
						if($(this.module).val()!=null) {
							selected_code=$(this.module).val();
		  					for(var i=0; i<data.resultList.length; i++) {
		  						if(selected_code==data.resultList[i].value) {
			  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'" selected>'+data.resultList[i].name+'</option>');
		  						}
		  						else {
			  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'">'+data.resultList[i].name+'</option>');
		  						}
		  					}
						}
						else {
		  					for(var i=0; i<data.resultList.length; i++) {
		  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'">'+data.resultList[i].name+'</option>');
		  					}
						}
						$('#'+win_id).find(comp_id).change({module: this.module}, function(event){
							$(event.data.module).val( $(this).find("option:selected").val() );
							$(event.data.module).trigger('change');
						});
						if($(this.module).attr("readonly")=="readonly") $(comp_id).attr("readonly", "readonly");
						if($(this.module).attr("disabled")=="disabled") $(comp_id).attr("disabled", "disabled");
					}
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
					// console.log(textStatus);
// try {
// if (p.onError) p.onError(XMLHttpRequest, textStatus, errorThrown);
// } catch (e) {

// }
				}
  				});
  		});

  		$('#'+win_id).find("input.ygpaFilterCode").each(function() {
  			var default_prompt=$(this).data('default-prompt')||'';
  			var default_value=$(this).data('default-value')||'';
  			var input_item=this;
  			var input_id=$(this).attr('id');
  			var filter=$(this).data('filter');

  			if($(this).data('display-value')==null) {
  				try {
  	  	  			$(this).attr("type", "hidden");
  				}
  				catch(err) {
  	  	  			$(this).attr("id", input_id+"_backup");
  	  	  			$(this).after('<input type="hidden" id="'+input_id+'" />');
  	  	  			input_item = $('#'+win_id+' #'+input_id)[0];
  	  	  			$(this).remove();
				}
  				$(input_item).after('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
  			else if($(this).data('display-value')=='N') {
  	  			$(this).attr("readonly", "readonly");
  	  			$(this).before('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
  			else {
  	  			$(this).attr("readonly", "readonly");
  	  			$(this).after('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
  			var comp_item=$('#'+win_id).find('#'+input_id+'_select');
  			comp_item.data('input-id', input_id);
			if(default_prompt!='') {
					if($(input_item).data('value')==null) comp_item.append('<option value="'+default_value+'" selected>'+default_prompt+'</option>');
					else comp_item.append('<option value="'+default_value+'">'+default_prompt+'</option>');
			}
			$(this).focus(function() {
				$('#'+$(this).attr("id")+"_select")[0].focus();
			});
			var f=$('#'+win_id+'_'+filter+'_select');
			if(f.length==0) f=$('#'+win_id+'_'+filter+".frmwrkAuto");
			if(f.length==0) {
				alert('필터로 선택된 객체가 없습니다. 개발 업체에게 문의 하시기 바랍니다.');
				return;
			}

			comp_item.change(function(event){
				var comp=$(event.target);
				val = comp.val();
				$('#'+comp.data('input-id')).val(val);
			});
			comp_item[0].onFilterChange=function(val) {
				val=val||"";
				$(this).empty();
				var nemp=$(this).data("filter-empty")||"N";
				var input_item=$('#'+$(this).data('input-id'));
				$(this).val("");
// input_item.val("");
				if(nemp=="N" && val=="") {
					var default_prompt=$(this).data('default-prompt')||"";
					var default_value=$(this).data('default-value')||"";
					if(default_prompt!="") {
						var comp_id='#'+$(this).attr('id')+'_select';

						$(comp_id).append('<option value="'+default_value+'" selected="selected">'+default_prompt+'</option>');
						$(input_item).val(default_value);
					}
					return;
				}
	  			$.ajax({
	  				url: EMD.context_root+$(input_item).data('url'),
	  				type: 'POST',
	  				module: this,
	  				dataType: 'json',
	  				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	  				data: [{name: 'filter', value: val}],
					success: function (data) {

						if(data.resultCode!=null && data.resultCode!=0) {
							if(data.resultCode==1) {	// 로그인 에러
								alert(data.resultMsg);
								location.reload();
							}
						}
						else {
							var input_item=$('#'+$(this.module).data('input-id'));
							var default_prompt=input_item.data('default-prompt')||"";
							var default_value=input_item.data('default-value')||"";
							var val=input_item.val()||default_value;
							if(default_prompt!="") {
								if(val==default_value) $(this.module).append('<option value="'+default_value+'" selected="selected">'+default_prompt+'</option>');
								else $(this.module).append('<option value="'+default_value+'">'+default_prompt+'</option>');
							}
		  					for(var i=0; i<data.resultList.length; i++) {
		  						if(val==data.resultList[i].value) {
		  							$(this.module).append('<option value="'+data.resultList[i].value+'" selected="selected">'+data.resultList[i].name+'</option>');
		  						}
		  						else {
		  							$(this.module).append('<option value="'+data.resultList[i].value+'">'+data.resultList[i].name+'</option>');
		  						}
		  					}
							if(input_item.attr("readonly")=="readonly") $(this.module).attr("readonly", "readonly");
							if(input_item.attr("disabled")=="disabled") $(this.module).attr("disabled", "disabled");
							if(this.module.childComboItem!=undefined) {
								for(var i=0; i<this.module.childComboItem.length; i++) {
									var childComboItem = this.module.childComboItem[i];
									childComboItem.onFilterChange(val);
								}
							}
							input_item.change({module: this.module}, function(event){
								$(event.data.module).val( $(this).find("option:selected").val() );
								$(event.data.module).trigger('change');
							});
						}
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
						console.log(textStatus);
					}
	  				});
			};
			if(f[0].childComboItem==undefined) {
				f[0].childComboItem=[comp_item[0]];
			}
			else {
				f[0].childComboItem[f[0].childComboItem.length]=comp_item[0];
			}

			f.change(function(event){
				var comp=$(event.target);
				val = comp.val();
				if(comp[0].childComboItem!=undefined) {
					for(var i=0; i<comp[0].childComboItem.length; i++) {
						var childComboItem = comp[0].childComboItem[i];
						childComboItem.onFilterChange(val);
					}
				}

				// $(event.data.module).trigger('change');
			});

			comp_item[0].onFilterChange(f.val());

  		});

  		$('#'+win_id).find("input.selectBox").each(function() {
  			var default_prompt='';
  			var default_value='';
  			var input_item=this;
  			var input_id=$(this).attr('id');
  			var url=$(this).data('url');
  			var params=$(this).data('params');
  			var parentSB=$(this).data('parent-select');
  			if($(this).data('code-id')==undefined) return;
  			if($(this).data('default-prompt')!=null) {
  				if($(this).data('default-prompt')!=null) default_prompt=$(this).data('default-prompt');
  				else default_prompt="선택";
  				if($(this).data('default-value')!=null) default_value=$(this).data('default-value');
  				else default_value="";
  			}
  			if($(this).data('display-value')==null) {
  				try {
  	  	  			$(this).attr("type", "hidden");
  				}
  				catch(err) {
  	  	  			$(this).attr("id", input_id+"_backup");
  	  	  			$(this).after('<input type="hidden" id="'+input_id+'" />');
  	  	  			input_item = $('#'+win_id+' #'+input_id)[0];
  	  	  			$(this).remove();
				}
  				$(input_item).after('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
  			else if($(this).data('display-value')=='N') {
  	  			$(this).attr("readonly", "readonly");
  	  			$(this).before('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
  			else {
  	  			$(this).attr("readonly", "readonly");
  	  			$(this).after('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
			if(default_prompt!='') {
					if($(input_item).data('value')==null) $('#'+win_id).find('#'+input_id+'_select').append('<option value="'+default_value+'" selected>'+default_prompt+'</option>');
					else $('#'+win_id).find('#'+input_id+'_select').append('<option value="'+default_value+'">'+default_prompt+'</option>');
			}
			$(this).focus(function() {
				$('#'+$(this).attr("id")+"_select")[0].focus();
			});
			var p=[];
			if(params!=null) {
				$.each(params.split(','), function() {
					var d=this.split('=');
					p[p.length]={name:parentSB, value:d[1]};
				});
			}

			input_item._setValue = function(data) {
				if(data.resultCode!=null && data.resultCode!=0) {
					if(data.resultCode==1) {	// 로그인 에러
						alert(data.resultMsg);
						location.reload();
					}
				}
				else {
					var selected_code='';
					var comp_id='#'+$(this).attr('id')+'_select';
					if($(this).val()!=null) {
						selected_code=$(this).val();
	  					for(var i=0; i<data.resultList.length; i++) {
	  						if(selected_code==data.resultList[i].value) {
		  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'" selected>'+data.resultList[i].name+'</option>');
	  						}
	  						else {
		  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'">'+data.resultList[i].name+'</option>');
	  						}
	  					}
					}
					else {
	  					for(var i=0; i<data.resultList.length; i++) {
	  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'">'+data.resultList[i].name+'</option>');
	  					}
					}
					$('#'+win_id).find(comp_id).change({module: this}, function(event){
						$(event.data.module).val( $(this).find("option:selected").val() );
						$(event.data.module).trigger('change');
					});
					if($(this).attr("readonly")=="readonly") $(comp_id).attr("readonly", "readonly");
					if($(this).attr("disabled")=="disabled") $(comp_id).attr("disabled", "disabled");
				}
			};

			this._params=p.slice();
			if(parentSB!=null) {
				var val = module_instance.$('#'+parentSB).val();
				module_instance.$('#'+parentSB).data("id", parentSB);
				p[p.length]={name:parentSB, value:val};
				module_instance.$('#'+parentSB).on("change", {module:this}, function(e) {
					var module=e.data.module;
					var p=module._params.slice();
					p[p.length]={name:$(this).data("id"), value:$(this).val()};
					$.ajax({
		  				url: EMD.context_root+$(module).data('url'),
		  				type: 'POST',
		  				module: module,
		  				dataType: 'json',
		  				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		  				data: p,
						success: function (data) {
							this.module._setValue(data);
						},
						error: function (XMLHttpRequest, textStatus, errorThrown) {
						}
		  				});
				});
			}
			else {
	  			$.ajax({
	  				url: EMD.context_root+url,
	  				type: 'POST',
	  				module: input_item,
	  				dataType: 'json',
	  				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	  				data: p,
					success: function (data) {
						this.module._setValue(data);
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
					}
	  				});
			}
  		});

  	// Y/N 선택 처리
  		$('#'+win_id).find("input.ygpaYnSelect").each(function() {
  			var default_prompt='';
  			var default_value='';
  			var y_value='Y';
  			var n_value='N';
  			var y_prompt='예';
  			var n_prompt='아니오';
  			var selector;
  			var input_item=this;
  			var input_id=$(this).attr('id');
  			if($(this).data('default-prompt')!=null) {
  				if($(this).data('default-prompt')!=null) default_prompt=$(this).data('default-prompt');
  				else default_prompt="선택";
  				if($(this).data('default-value')!=null) default_value=$(this).data('default-value');
  				else default_value="";
  			}
  			if($(this).data('display-value')==null) {
  				try {
  	  	  			$(this).attr("type", "hidden");
  				}
  				catch(err) {
  	  	  			$(this).attr("id", input_id+"_backup");
  	  	  			$(this).after('<input type="hidden" id="'+input_id+'" />');
  	  	  			input_item = $('#'+win_id+' #'+input_id)[0];
  	  	  			$(this).remove();
				}
  				$(input_item).after('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
  			else if($(this).data('display-value')=='N') {
  	  			$(this).attr("readonly", "readonly");
  	  			$(this).before('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
  			else {
  	  			$(this).attr("readonly", "readonly");
  	  			$(this).after('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
  			selector=$('#'+win_id).find('#'+input_id+'_select');
			selected_code=$(this).data('value');
			if(default_prompt!='') {
					if(selected_code==null) selector.append('<option value="'+default_value+'" selected>'+default_prompt+'</option>');
					else selector.append('<option value="'+default_value+'">'+default_prompt+'</option>');
			}
			if($(this).data('y-value')!=null) y_value=$(this).data('y-value');
			if($(this).data('n-value')!=null) y_value=$(this).data('n-value');
			if($(this).data('y-prompt')!=null) y_prompt=$(this).data('y-prompt');
			if($(this).data('n-prompt')!=null) n_prompt=$(this).data('n-prompt');
			if(default_prompt=='' || selected_code!=null) {
				if(selected_code==y_value) selector.append('<option value="'+y_value+'" selected>'+y_prompt+'</option>');
				else selector.append('<option value="'+y_value+'">'+y_prompt+'</option>');
				if(selected_code!=y_value) selector.append('<option value="'+n_value+'" selected>'+n_prompt+'</option>');
				else selector.append('<option value="'+n_value+'">'+n_prompt+'</option>');
				$(this).val(selected_code);
			}
			else {
				selector.append('<option value="'+y_value+'">'+y_prompt+'</option>');
				selector.append('<option value="'+n_value+'">'+n_prompt+'</option>');
			}
			$(this).focus(function() {
				$('#'+$(this).attr("id")+"_select")[0].focus();
			});
			selector.change({module: input_item}, function(event){
				$(event.data.module).val( $(this).find("option:selected").val() );
				$(event.data.module).trigger('change');
			});
  		});


  		// 숫자 처리
		$('#'+win_id).find("input.ygpaNumber, input.ygpaCurrency").each(function() {
			$(this).number(true, $(this).data('decimal-point')||0);
		});

		$('#'+win_id).find(".emdTime").each(function() {
			$(this).keyup( function(){
				var value=$(this).val();
				if(value.indexOf(":")>=0) {
					if(value.length!=5) {
						$(this).val($(this).val().replace(/-/g, ''));
					}
				}
				else {
					if(value.length==4) {
						value=value.substr(0, 2)+':'+value.substr(2, 2);
					}
				}
			});
		  });

		$('#'+win_id).find(".emdcal").each(function() {
			var dateFormat=$(this).data('date-format')||'yy-mm-dd';
			var disabled = $(this).attr('disabled')!=undefined;
			$(this).datepicker({
			    dateFormat: dateFormat,
		    prevText: '이전 달',
		    nextText: '다음 달',
		    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		    dayNames: ['일','월','화','수','목','금','토'],
		    dayNamesShort: ['일','월','화','수','목','금','토'],
		    dayNamesMin: ['일','월','화','수','목','금','토'],
		    changeMonth: true,
			    changeYear: true,
			    disabled: disabled
		  });
			if(!disabled) {
				$(this).datepicker("option", {
					showOn: "button",
				    buttonImage: EMD.context_root+"/images/egovframework/rte/icon_date_picker.jpg",
		 		    buttonImageOnly: true,
				});
			}
			if($(this).data("role")=="dtFrom") {
				$('#'+win_id+'_'+$(this).data("dt-to")).on('change', {dtFrom: this}, function(event){
					$(event.data.dtFrom).datepicker("option", "maxDate", $(this).datepicker("getDate"));
				});
				$(this).datepicker("option", "maxDate", $('#'+win_id+'_'+$(this).data("dt-to")).datepicker("getDate"));
			}
			if($(this).data("role")=="dtTo") {
				$('#'+win_id+'_'+$(this).data("dt-from")).on('change', {dtTo: this}, function(event){
					$(event.data.dtTo).datepicker("option", "minDate", $(this).datepicker("getDate"));
				});
				$(this).datepicker("option", "minDate", $('#'+win_id+'_'+$(this).data("dt-from")).datepicker("getDate"));
			}
			$(this).keyup( function(){
				var value=$(this).val();
				if(value.indexOf("-")>=0) {
					if(value.length!=10) {
						$(this).val($(this).val().replace(/-/g, ''));
// alert("날짜 필드에는 숫자만 입력 가능합니다.");
					}
					else {
						if(!EMD.util.isDate(value)) {
							alert("날짜 형식이 부적합 합니다.");
// $(this).val('');
						}
					}
				}
				else {
					if(value.length==8) {
						value=value.substr(0, 4)+'-'+value.substr(4, 2)+'-'+value.substr(6, 2);
						if(!EMD.util.isDate(value)) {
							alert("날짜 형식이 부적합 합니다.");
// $(this).val('');
						}
						else {
							$(this).val(value);
						}

					}
				}
			});
		  });
		// show window

// $(win_id+" .emdTab").click(win_id, function(win_id){
// var display = ($(this).attr('id'))+"bottom";
// $(win_id+" .emd").attr("style","display:none");
// $("#"+display).attr("style","display:inline");
// };);
      },
      _clickButtonExec: function(module, button, button_id) {
		  var role=button.data('role');
		  switch(role) {
			  case 'gridXlsDown':
				  var grid=button.data("flexi-grid");
				  var filename=button.data("xls-name")||"gis.xls";
				  var title=button.data("xls-title")||"GIS기반 자산관리 시스템";
				  if(grid!=undefined) {
					  module.$('#'+grid).flexiXlsDown(filename, title);
				  }
				  else module.onButtonClick(button_id);
				  break;
			  case 'hwpPage':
				  var url=button.data('url');
				  EMD.util.printPageValue(module, url, button.data('search-option'));
				  break;
			case 'printPage':
			  var src=button.data('flexi-grid');
			  var url=button.data('url');
			  if(src==null) {
				  EMD.util.printPageValue(module, url, button.data('search-option'));
				}
			  else {	// load list
				  EMD.util.printPageGrid(module, url, button.data('flexi-grid'), button.data('search-option'));
			  }
			  break;
			case 'printDown':
				// var src=button.data('flexi-grid');
				  var url=button.data('url');
				  var param;
				  // if(src==null) {
					  param = module.makeFormArgs('#'+button.data('search-option'), 'object');
					  param['filename'] = button.data('filename');

					  $.fileDownload(EMD.context_root+url, {data:param, httpMethod:"POST"});
// }
// else { // load list
// param = module.makeFormArgs('#'+button.data('search-option'), 'object');
// param[param.length] = {
// name: 'filename',
// value: button.data('filename')
// };
//
// $.fileDownload(EMD.context_root+url, {data:param, httpMethod:"POST"});
// EMD.util.printPageGrid(module, url, button.data('flexi-grid'),
// button.data('search-option'));
// }
				break;
			case 'openWindow':
				var argument=button.data('argument');
				var params=button.data('params');
				var p=[];
				if(argument!=null) {
					if(typeof module[argument]=="object") {
						p=EMD.util.objectToArray(module[argument]);
					}
				}
				if(params!=null) params=module[params];
				EMD.util.create_window(button.data('progrm-file-nm'), button.data('title'), button.data('url'), p, params, module);
				break;
			case 'showHelp':
				EMD.util.showHelp(wikiUrl+'page='+button.data('page'));
				break;
		  case 'popupDialog':
			  var p={};
			  var dp={};
			  var params=button.data('params');
			  var params_func=button.data('params-func');
			  var direct_params=button.data('direct-params');
			  var direct_params_func=button.data('direct-params-func');
			  if(params!=undefined) {
				  if(params.indexOf(",")>=0) {
					  $.each(params.split(","), function() {
						  var d=this.split("=");
						  p[d[0]]=d[1];
					  });
				  }
				  else {
					  p=module[params];
				  }
			  }
			  else if(params_func!=undefined) {
				  p=module[params]();
			  }
			  if(direct_params!=undefined) {
				  if(direct_params.indexOf(",")>=0) {
					  $.each(direct_params.split(","), function() {
						  var d=this.split("=");
						  dp[d[0]]=d[1];
					  });
				  }
				  else {
					  dp=module[direct_params];
				  }
			  }
			  else if(direct_params_func!=undefined) {
				  dp=module[direct_params]();
			  }
			  module.doExecuteDialog(button_id, button.data('title'), EMD.context_root+button.data('url'), p,dp);
			  break;
		  case 'loadMap':
			  var src=button.data('flexi-grid');
			  EMD.util.window_hide_all();
			  if(src==null) {
				  EMD.gis.loadMapDataCode(module, button.data('gis-layer'), button.data('gis-style'), button.data('value-name'), button.data('value'));
			  }
			  else {	// load list
				  EMD.gis.loadMapDataFlexGrid(module, button.data('gis-layer'), button.data('gis-style'), button.data('flexi-grid'), button.data('code-id'), button.data('value'));
			  }
			  break;
		  case 'showMap':
			  var src=button.data('flexi-grid');
			  EMD.util.window_hide_all();
			  if(src==null) {
				  EMD.gis.showMapDataCode(module, button.data('gis-layer'), button.data('value-name'));
			  }
			  else {	// load list
				  EMD.gis.showMapDataFlexGrid(module, button.data('gis-layer'), button.data('flexi-grid'), button.data('code-id'), button.data('value'));
			  }
			  break;
		  case 'clearMap':
			  EMD.gis.clearMap(module, button.data('gis-layer'));
			  break;
		  case 'loadStatsMap':	// 통계 맵 조회
			  var src=button.data('flexi-grid');
			  if(src!=null) {
				  EMD.util.window_hide_all();
				  EMD.gis.showStatusMapView(module, button.data('gis-layer'), src, button.data('map-style'), button.data('value'), button.data('label-field'), button.data('select-feature'));
			  }
			  break;
		  case 'editMap': // 지도 레이어 편집
			  var layer=button.data('gis-layer');
			  EMD.gis.editLayer(module, layer);
			  EMD.util.window_hide_all();
			  break;
		  case 'removeFeature':
			  var src=button.data('flexi-grid');
			  if(src==null) {
				  EMD.gis.removeMapDataCode(module, button.data('gis-layer'), button.data('value-name'), button.data('value'));
			  }
			  else {	// load list
				  EMD.gis.removeMapDataFlexGrid(module, button.data('gis-layer'), button.data('flexi-grid'), button.data('code-id'), button.data('value'));
			  }
		  	break;
		  case 'addFeature':
			  var src=button.data('flexi-grid');
			  if(src==null) {
				  EMD.gis.addFeatureCode(module, button.data('gis-layer'), button.data('value-name'), button.data('value'));
			  }
			  else {	// load list
				  EMD.gis.addFeatureFlexGrid(module, button.data('gis-layer'), button.data('flexi-grid'), button.data('code-id'), button.data('value'));
			  }
              if(EMD.popup.lotAreaInfo!=null) {
                  EMD.map.removePopup(EMD.popup.lotAreaInfo);
                  EMD.popup.lotAreaInfo.destroy();
              }
			  break;
		  default:
			  if(button.hasClass('buttonExcel')) {
				  var grid=button.data("flexi-grid");
				  var url=button.data("url");
				  if(grid!=undefined && url!=undefined) {
					  module.$('#'+grid).flexExcelDown(url);
				  }
				  else module.onButtonClick(button_id);
			  }
			  else
				  module.onButtonClick(button_id);
		  break;
		  }
      },
      hsv2rgb: function(h, s, v) {
    	  var rgb, i, data = [];
    	  if (s === 0) {
    	    rgb = [v,v,v];
    	  } else {
    	    h = h / 60;
    	    i = Math.floor(h);
    	    data = [v*(1-s), v*(1-s*(h-i)), v*(1-s*(1-(h-i)))];
    	    switch(i) {
    	      case 0:
    	        rgb = [v, data[2], data[0]];
    	        break;
    	      case 1:
    	        rgb = [data[1], v, data[0]];
    	        break;
    	      case 2:
    	        rgb = [data[0], v, data[2]];
    	        break;
    	      case 3:
    	        rgb = [data[0], data[1], v];
    	        break;
    	      case 4:
    	        rgb = [data[2], data[0], v];
    	        break;
    	      default:
    	        rgb = [v, data[0], data[1]];
    	        break;
    	    }
    	  }
    	  return '#' + rgb.map(function(x){
    	    return ("0" + Math.round(x*255).toString(16)).slice(-2);
    	  }).join('');
      },
      storeMapState: function(lon, lat, zoom) {
    	  $.ajax({
  			url: EMD.context_root+"/main/storeUserPos.do",
  			type: 'POST',
  			async: true,
  			dataType: 'json',
  			global: false,
  			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
  			// contentType: "text/plain; charset=UTF-8",
  			data: {
  				lat:lat,
  				lon:lon,
  				zoom:zoom
  			}
  		}).done(function(data) {
  			if(data.resultCode=="0") {
//  				alert(data.message);
  				alert('저장되었습니다.');
  				return;
  			}
  		});
      }
    }
  };
// Pass in jQuery.
})(jQuery, this, this.document);

// module define

function EmdModuleBase() {
	this._moduleType="_EMD_MODULE_BASE";
};

EmdModuleBase.prototype.getModuleId = function() {
	return this._module_id;
};

EmdModuleBase.prototype.setModuleId = function(id) {
	this._module_id = id;
};

/**
 *
 * @param selector
 * @returns {$}
 */
EmdModuleBase.prototype.$ = function(selector) {
	return $('#'+this._module_id).find(selector.replace(/#/g, '#'+this._module_id+'_'));
};

/**
 * 폼 데이터 파라미터 변환 함수
 *
 * @param selector
 * @param type -
 *            파라미터 타입 (지정 안하면 array로 지정하면 아니면 오브젝트로 변환 함)
 * @returns 파라미터 오브젝트나 배열
 */
EmdModuleBase.prototype.makeFormArgs = function(selector, type) {
	var obj_type=type||'array';
	var args = null;
	var module_id=this._module_id;
	if(obj_type=='array') {
		args=[];
	}
	else {
		args={};
	}
	this.$(selector).find(':input').not('.frmwrkAuto').not(".skipValue").each( function() {
		var value='';
		if($(this).hasClass('ygpaNumber') || $(this).hasClass('ygpaCurrency')) {
			value=$(this).val();
		}
		else value=$(this).val();
		if($(this).data('column-id')!=null) {
			if(obj_type=='array') {
				args[args.length]={
						name: $(this).data('column-id'),
						value: value
				};
			}
			else {
				args[$(this).data('column-id')]=value;
			}
		}
		else {
			var input_id=$(this).attr('id');
			if(typeof input_id == 'string') {
				input_id=input_id.replace(module_id+'_', '');

				if(obj_type=='array') {
					args[args.length]={
							name: input_id,
							value: value
					};
				}
				else {
					args[input_id]=value;
				}
			}
		}
	});
	return args;
};

/**
 * 폼 변수 입력 함수 - 폼의 아이디와 data-column-id 를 참조하여 obj 에 해당 변수가 있으면 해당 변수의 값으로 채운다.
 * 모듈명 : EmdModuleBase 함수명 : makeFormValues
 *
 * @param selector -
 *            선택자 (jquery selector)
 * @param obj -
 *            입력 변수 JSON
 * @returns {Array} - 선택된 폼에 대한 폼 아이디와 변수 목록을 리턴 한다.
 */
EmdModuleBase.prototype.makeFormValues = function(selector, obj) {
	var module_id=this._module_id;
	if(obj instanceof Array) {
		var newobj = {};
		for(var i=0; i<obj.length; i++) {
			newobj[obj[i].name] = obj[i].value;
		}
		obj=newobj;
	}
	if(typeof obj != 'object') {
		return;
	}
	this.$(selector).find(':input').not('.frmwrkAuto').not('.skipValue').each( function() {
		var column_id='';
		if($(this).data('column-id')!=null) {
			column_id = $(this).data('column-id');
		}
		else {
			column_id=$(this).attr('id');
			if(typeof column_id == 'string') {
				column_id=column_id.replace(module_id+'_', '');
			}
			else return;
		}
		if(obj==null) {
			console.log('error object is null in makeformvalues');
			return false;
		}
		if(obj[column_id]!=null) {
			if($(this).hasClass('ygpaNumber') || $(this).hasClass('ygpaCurrency')) {
				$(this).val(obj[column_id]);
			}
			else {
				if($(this).hasClass('ygpaDeptSelect') || $(this).hasClass('ygpaCmmnCl') || $(this).hasClass('ygpaCmmnCode') || $(this).hasClass('ygpaCmmnCd') || $(this).hasClass('ygpaFilterCode') || $(this).hasClass('ygpaYnSelect')){
					$(this).val(obj[column_id]);
					var sel=$('#'+$(this).attr('id')+"_select");
					if(sel.length!=0) {
						if(sel[0].childComboItem!=undefined) {
							for(var i=0; i<sel[0].childComboItem.length; i++) {
								var childComboItem = sel[0].childComboItem[i];
								childComboItem.onFilterChange(obj[column_id]);
							}
						}
// $('#'+$(this).attr('id')+'_select').val(obj[column_id]);
					}
				}
				else $(this).val(obj[column_id]);
			}
		}
		else {
			$(this).val('');
		}
	});
};

/**
 * 선택한 디비전에 해당 오브젝트의 값을 적용한다.
 */
EmdModuleBase.prototype.makeDivValues = function(selector, obj) {
	var selobj=null;
	var win_id=this._module_id;
	if(obj instanceof Array) {
		var newobj = {};
		for(var i=0; i<obj.length; i++) {
			newobj[obj[i].name] = obj[i].value;
		}
		obj=newobj;
	}
	if(typeof obj != 'object') {
		return;
	}
	if(typeof selector == 'string') selobj=this.$(selector);
	else if(typeof selector == 'object') selobj=selector;
	else selobj=$(selector);
	selobj.find('span').each( function() {
		var column_id='';
		if($(this).data('column-id')!=null) {
			column_id = $(this).data('column-id');
		}
		else {
			column_id=$(this).attr('id');
			if(typeof column_id == 'string') {
				column_id=column_id.replace(win_id+'_', '');
			}
			else return;
		}
		if(obj[column_id]!=null) {
			if($(this).hasClass('ygpaNumber') || $(this).hasClass('ygpaCurrency')) {
				$(this).text($.number(obj[column_id], $(this).data("decimal-point")||0));
			}
			else {
				if($(this).hasClass('ygpaDeptSelect') || $(this).hasClass('ygpaCmmnCl') || $(this).hasClass('ygpaCmmnCode') || $(this).hasClass('ygpaCmmnCd') || $(this).hasClass('ygpaFilterCode')  || $(this).hasClass('ygpaYnSelect') || $(this).hasClass('ygpaDecode')){
					var input_item=$(this);
					if($(this).hasClass('ygpaDeptSelect')) {
			  			$.ajax({
			  				url: EMD.context_root+'/cmmn/selectDeptCodeName.do',
			  				type: 'POST',
			  				module: input_item,
			  				dataType: 'json',
			  				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			  				data: [{name: 'value', value: obj[column_id]}],
							success: function (data) {
			                    this.module.text(data.name);
							},
							error: function (XMLHttpRequest, textStatus, errorThrown) {
								try {
									// console.log(textStatus);
									this.module.text("서버오류");
// if (p.onError) p.onError(XMLHttpRequest, textStatus, errorThrown);
								} catch (e) {}
							}
		  				});
					}
					else if($(this).hasClass('ygpaCmmnCl')) {
			  			$.ajax({
			  				url: EMD.context_root+'/cmmn/selectCmmnClName.do',
			  				type: 'POST',
			  				module: input_item,
			  				dataType: 'json',
			  				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			  				data: [{value: code_id},{name: 'value', value: obj[column_id]}],
							success: function (data) {
								this.module.text(data.value);
							},
							error: function (XMLHttpRequest, textStatus, errorThrown) {
								try {
									// console.log(textStatus);
									this.module.text("서버오류");
// if (p.onError) p.onError(XMLHttpRequest, textStatus, errorThrown);
								} catch (e) {}
							}
		  				});
					}
					else if($(this).hasClass('ygpaCmmnCode')) {
						var cl_code=input_item.data('cl-code');
			  			$.ajax({
			  				url: EMD.context_root+'/cmmn/selectCmmnCodeName.do',
			  				type: 'POST',
			  				module: input_item,
			  				dataType: 'json',
			  				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			  				data: [{name: 'clCode', value: cl_code},{name: 'codeId', value: obj[column_id]}],
							success: function (data) {
								this.module.text(data.value);
							},
							error: function (XMLHttpRequest, textStatus, errorThrown) {
								try {
									// console.log(textStatus);
									this.module.text("서버오류");
// if (p.onError) p.onError(XMLHttpRequest, textStatus, errorThrown);
								} catch (e) {}
							}
		  				});
					}
					else if($(this).hasClass('ygpaCmmnCd')) {
						var code_id=input_item.data('code-id');
			  			$.ajax({
			  				url: EMD.context_root+'/cmmn/selectCmmnDetailCodeName.do',
			  				type: 'POST',
			  				module: input_item,
			  				dataType: 'json',
			  				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			  				data: [{name: 'codeId', value: code_id},{name: 'code', value: obj[column_id]}],
							success: function (data) {
								this.module.text(data.value);
							},
							error: function (XMLHttpRequest, textStatus, errorThrown) {
								try {
									// console.log(textStatus);
									this.module.text("서버오류");
// if (p.onError) p.onError(XMLHttpRequest, textStatus, errorThrown);
								} catch (e) {}
							}
		  				});
					}
					else if($(this).hasClass('ygpaFilterCode')) {
						var code_id=input_item.data('filter');
						var comp_id=$(this).attr('id');
						var filter=$('#'+comp_id.substring(comp_id.indexOf('_')+1)+code_id).val();
			  			$.ajax({
			  				url: EMD.context_root+input_item.data("url"),
			  				type: 'POST',
			  				module: input_item,
			  				dataType: 'json',
			  				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			  				data: [{name: 'filter', value: filter}, {name: 'value', value:input_item.val()}],
							success: function (data) {
								this.module.text(data.resultList[0].name);
							},
							error: function (XMLHttpRequest, textStatus, errorThrown) {
								try {
									// console.log(textStatus);
									this.module.text("서버오류");
// if (p.onError) p.onError(XMLHttpRequest, textStatus, errorThrown);
								} catch (e) {}
							}
		  				});
					}
					else if($(this).hasClass('ygpaYnSelect')) {
						var y_value='Y';
						var n_value='N';
						var y_prompt="예";
						var n_prompt="아니오";
						if($(this).data('y-value')!=null) y_value=$(this).data('y-value');
						if($(this).data('n-value')!=null) y_value=$(this).data('n-value');
						if(obj[column_id]==y_value) {
							if($(this).data('y-prompt')!=null) y_prompt=$(this).data('y-prompt');
							$(this).text(y_prompt);
						}
						else if(obj[column_id]==n_value) {
							if($(this).data('n-prompt')!=null) n_prompt=$(this).data('n-prompt');
							$(this).text(n_prompt);
						}
						else $(this).text(obj[column_id]);
					}
					else if($(this).hasClass('ygpaDecode')) {	// 값이
																// data-decode-string
																// 값을 컴마로 구분하여
																// 디코드 값
																// data-decode-(idx)
																// 의 값을 표시 한다.
																// 해당 값이 없으면
																// data-decode-default
																// 의 값을 표시한다.
						if(typeof obj[column_id]=='string' && typeof $(this).data('decode-string')=='string') {
							var idx=$(this).data('decode-string').match(',');
							var input_item=$(this);
							for(var i=0; i<idx.length; i++) {
								if(idx[i]==obj[column_id]) {
									$(this).text($(this).data('decode-'+i));
									return;
								}
							}
							$(this).text($(this).data('decode-default'));
						}
					}
				}
				else {
					// normal text
					$(this).text(obj[column_id]);
				}
			}
		}
		else {
			// clear text
			$(this).text('');
		}
	});
};

/**
 * * 선택한 디비전에 차일드에 리스트의 갯수 만큼 복제하여 값을 적용한다.
 */
EmdModuleBase.prototype.makeMultiDivValues = function(selector, list, func) {
	var selobj = this.$(selector);
	if(selobj.children().length<list.length) {	// 목록 이 갯수가 작으면 늘린다.
		for(var i=selobj.children().length; i<list.length; i++) {
			var tab=selobj.children(":first-child").clone();
			selobj.append(tab);
		}
	}
	else if(selobj.children().length>list.length) {	// 목록 이 크면 줄인다.
		while(selobj.children().length>1 && selobj.children().length>list.length) {
	        selobj.children(":last-child").remove();
		}
	}
	for(var i=0; i<list.length; i++) {
		var div=selobj.children(':eq('+i+')');
		this.makeDivValues(div, list[i]); // 결과값을 채운다.
		div[0].func=func;
		div[0].func(list[i]);
		delete div[0]['func'];
	}
	if(list.length==0) {
		this.makeDivValues(selobj.children(), {}); // 클리어
	}
};

/**
 * 폼 변수 출력 함수 - 폼의 아이디와 data-column-id 를 참조하여 obj 에 해당 변수명에 입력 폼 값을 가져온다. 모듈명 :
 * EmdModuleBase 함수명 : getFormValues
 *
 * @param selector -
 *            선택자 (jquery selector)
 * @param obj -
 *            입력 변수 JSON (입력 폼이 없을때 디폴트 값 변수)
 * @returns {Object} - 선택된 폼의 변수 값을 리턴 한다.
 */
EmdModuleBase.prototype.getFormValues = function(selector, obj) {
	var win_id=this.getModuleId();
	var row = $.extend({}, obj);
	this.$(selector).find(':input').not('.frmwrkAuto').each( function() {
		var value='';
		if($(this).hasClass('ygpaNumber') || $(this).hasClass('ygpaCurrency')) {
			value=$(this).val();
		}
		else value=$(this).val();
		if($(this).data('column-id')!=null) {
			row[$(this).data('column-id')]=value;
		}
		else {
			var input_id=$(this).attr('id');
			if(typeof input_id == 'string') {
				input_id=input_id.replace(win_id+'_', '');
				row[input_id]=value;
			}
		}
		if($(this).data('column-label-id')!=null) {
			row[$(this).data('column-label-id')]=$('#'+$(this).attr('id')+'_select').find(':selected').text();
		}
	});
	return row;
};

/**
 * 서버 액션을 실행한다. 모듈명 : EmdModule 함수명 :
 *
 * @param addr -
 *            실행 할 서버 주소
 * @param args -
 *            서버에 넘길 변수 (Object)
 * @param retfunc -
 *            서버 실행 후 실행 할 콜백 함수 (module과, data 를 파라메터로 실행 한다.)
 */
EmdModuleBase.prototype.doAction = function(addr, args, retfunc) {
	$.ajax({
		url: EMD.context_root+addr,
		type: 'POST',
		module: this,
		dataType: 'json',
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
// contentType: "text/plain; charset=UTF-8",
		data: args
		}).done(function(data) {
		if(retfunc!=null) retfunc(this.module, data);
	});
};

/**
 * 한개의 파일을 업로드 한다.
 *
 * @param dir -
 *            업로드 서브 디렉토리
 * @param func -
 *            업로드 후 리턴 값을 받을 함수
 */
EmdModuleBase.prototype.uploadSingleFile = function(url, func) {
	this.uploadIEMultiFile(url, func);
};

/**
 * 여러개의 파일을 업로드 한다.
 */
EmdModuleBase.prototype.uploadMultiFile = function(url, func) {
	this.uploadIEMultiFile(url, func);
};

EmdModuleBase.prototype.uploadIEMultiFile = function(url, func) {
	$('#__tempDiv').empty();
	$('#__uploadFrame').remove();
	var $iFrame = $('<iFrame id="__uploadFrame" name="__uploadFrame"></iFrame>');
    $iFrame.appendTo('body');
    $('#__tempDiv').append('<form id="__uploadForm" name="__uploadForm" method="post" enctype="multipart/form-data">'
    		+'<input name="type" type="hidden" value="genericFileMulti" /><input id="__uploadFile" name="__uploadFile" type="file" multiple />'
    		+'</form>');
    $('#__uploadForm').data('url', url);
    $('#__uploadForm').submit(function() {
		document.getElementById("__uploadForm").target = "__uploadFrame";
    });
    $('#__uploadFile').on('change', {module:this, func: func, url: url}, function(e) {
        $('#__uploadFrame').load(function(){
        	if ( status == "error" ) {
        	    var msg = "파일을 업로드 하는데 오류가 발생 했습니다. : ";
        	    alert( msg + xhr.status + " " + xhr.statusText );
        	  }
        	$('#__tempDiv').empty();
            var data = $('#__uploadFrame').contents().text();
            if(e.data.func!=undefined) e.data.func(e.data.module, jQuery.parseJSON(data));

            $('#__uploadFrame').remove();
        });
        var fileInput = document.getElementById("__uploadFile");
    	if(fileInput.files !== undefined) {
    		var formData = new FormData();
    		for(var i=0; i<fileInput.files.length; i++) {
        		var file=fileInput.files[0];
        		formData.append("__uploadFile", file);
    		}

    		var xhr = new XMLHttpRequest();
    		xhr.open("POST", EMD.context_root+e.data.url, true);
    		xhr.onreadystatechange = function() {
    			if(this.readyState==4 && this.status == 200) {
// var iframe = document.getElementById("__uploadFrame");
// iframe.contentDocument.write(this.responseText);
    	            if(e.data.func!=undefined) e.data.func(e.data.module, jQuery.parseJSON(this.responseText));
    	        	$('#__tempDiv').empty();
    	            $('#__uploadFrame').remove();
    			}
    		};
    		xhr.onprogress = function(e) {
// console.log('upload '+ (e.loaded/e.total)*100+'%');
    		};

    		xhr.send(formData);
    	}
    	else {
    		$('#__uploadForm').attr('action', EMD.context_root+e.data.url).submit();
    	}
    });

    $('#__uploadFile').click();
};

EmdModuleBase.prototype.downloadSingleFile = function(url, filePhysiclNm, fileLogicalNm) {
	var param = {"physicalFileNm": filePhysiclNm, "logicalFileNm": fileLogicalNm};
	$.fileDownload(EMD.context_root+url, {data:param, httpMethod:"POST"});	// 헤더를
																			// 전송
																			// 해야
																			// 해서
																			// 컨트롤러를
																			// 따로
																			// 생성
																			// 해야 함
};

/**
 * url 을 리턴한다.
 */
EmdModuleBase.prototype.getUrl = function(url) {
	return EMD.context_root+url;
};


/**
 * 피처를 삭제한다.
 *
 * @param layerName -
 * @param f -
 *            피처 배열
 */
EmdModuleBase.prototype.removeFeatures = function(layerName, f) {
	EMD.gis.removeFeatures(this, layerName, f);
};

/**
 * 실내공간관리 페이지를 호출 한다.
 */
EmdModuleBase.prototype.openPopupWindow = function(url, params) {
	$('#__tempDiv').empty();
	var form = document.createElement("form");
	$(form).attr("id", "__temp");
	for(var pn in params) {
		var e = document.createElement("input");
		$(e).attr("name", pn);
		$(e).val(params[pn]);
		$(form).append(e);
	}
	$(form).attr("action", EMD.context_root+url);
	$('#__tempDiv').append(form);
	if(EmdModuleBase._lastPopupId==undefined) {
		EmdModuleBase['_lastPopupId']=1;
	}

	var win = window.open("","__tempPopup"+EmdModuleBase._lastPopupId,"left=0, top=0, width="+screen.width+", height="+screen.height+", menubar=no,status=no,scrollbars=yes");

	$(win).on("");

	var module=this;

	form.target = "__tempPopup"+EmdModuleBase._lastPopupId;
	EmdModuleBase._lastPopupId++;

	form.submit();
};

/**
 * EmdModule을 생성한다. EmdModule 생성자
 *
 * @param width -
 *            생성 화면 폭
 * @param height -
 *            생성 화면 높이
 */
function EmdModule(width, height) {
	this._parent=null;
	this._selectedRow=null;
	if(width==undefined || width==null || height==undefined || height==null) {
		this._width=720;
		this._height=480;
	}
	else {
		this._width=width;
		this._height=height;
	}
	this._moduleType="_EMD_MODULE";
};

/**
 * EmdModuleBase 를 상속 받는다.
 */
EmdModule.prototype = new EmdModuleBase();

/**
 * 모듈 타입을 리턴한다. 모듈명 : EmdModule 함수명 : getModuleType
 *
 * @returns {String}
 */
EmdModule.prototype.getModuleType = function() {
	return this._moduleType;
};


/**
 * 모듈의 parent 를 지정한다. 모듈명 : EmdModule 함수명 : setParent
 *
 * @param parent -
 *            모듈의 parent
 */
EmdModule.prototype.setParent = function(parent) {
	this._parent=parent;
};

/**
 * 지정한 그리드의 목록에 대한 지도를 표시한다. 모듈명 : EmdModule 함수명 : showMap
 *
 * @param gridId -
 *            지도로 표시할 그리드 아이디 (그리드에 목록이 로드되 있어야 한다.
 */
EmdModule.prototype.showMap = function(gridId, layerName) {
	if(gridId != undefined) {
		this._selectedRow=this.$('#'+gridId).selectedRows();
		$(this._selectedRow).each(function() {
			EMD.gis.selectAssetCdFeature(this.gisAssetsPrtAtCode, this.gisAssetsCd, this.gisAssetsSubCd);
		});

	}
	EMD.transactionWindow = this.getModuleId();
	EMD.util.window_hide_all();
};

/**
 * 피처 코드를 편집한다. 모듈명 : EmdModule 함수명 : showMap
 *
 * @param layerName -
 *            레이어명
 * @param value -
 *            코드 값
 */
EmdModule.prototype.modifyFeatureCode = function(layerName, value, newfeature) {
	EMD.gis.modifyFeatureCode(this, layerName, value, newfeature);
};

/**
 * 피처의 속성 코드를 지정한다.
 *
 * @param layerName -
 *            레이어 명
 * @param value -
 *            속성
 * @param feature
 *            피처
 */
EmdModule.prototype.setFeatureCode = function(layerName, value, feature) {
	EMD.gis.setFeatureCode(this, layerName, value, feature);
};

/**
 * 피처코드를 삭제 한다.
 *
 * @param layerName
 * @param value
 */
EmdModule.prototype.removeFeatureCode = function(layerName, value) {
	EMD.gis.removeFeatureCode(this, layerName, value);
};

/**
 * 피처 속성을 변경한다.
 *
 * @param layerName
 * @param oldAttr
 * @param newAttr
 */
EmdModule.prototype.changeFeatureAttrib = function(layerName, oldAttr, newAttr) {
	EMD.gis.changeFeatureAttribute(this, layerName, oldAttr, newAttr);
};

/**
 * 피처를 선택한다.
 *
 * @param layerName
 * @param value
 * @param zoomToExtent
 */
EmdModule.prototype.selectFeatureData = function(layerName, value, zoomToExtent) {
	EMD.gis.selectFeatureData(this, layerName, value, zoomToExtent);
};

/**
 * 테스트 함수 선택자의 전체 문자열을 표시한다.
 *
 * @param selector
 */
EmdModule.prototype.getSelect = function(selector) {
	alert('get select = '+this.getModuleId()+' '+selector);
};

/**
 * 테스트 함수 alert 표시
 *
 * @param msg
 */
EmdModule.prototype.showAlert = function(msg) {
	alert('alert id = '+this.getModuleId()+' '+msg);
};

/**
 * 윈도우 화면 폭을 리턴한다.
 *
 * @returns {Number}
 */
EmdModule.prototype.getWidth = function() {
	return this._width;
};

/**
 * 윈도우의 화면 폭을 설정한다. (내부적으로만 사용)
 *
 * @param value
 */
EmdModule.prototype.setWidth = function(value) {
	this._width=value;
};

/**
 * 윈도우의 화면 높이를 리턴한다.
 *
 * @returns {Number}
 */
EmdModule.prototype.getHeight = function() {
	return this._height;
};

/**
 * 윈도우의 화면 높이를 설정한다. (내부적으로만 사용)
 *
 * @param value
 */
EmdModule.prototype.setHeight = function(value) {
	this._height=value;
};

/**
 * 선택 된 행울 설정한다. (내부적으로만 사용)
 *
 * @param row
 */
EmdModule.prototype.setSelectedRow = function(row) {
	this._selectedRow = row;
};

/**
 * 선택된 행을 리턴한다.
 *
 * @returns
 */
EmdModule.prototype.getSelectedRow = function() {
	return this._selectedRow;
};

/**
 * 모듈에 아이디를 생성한다.
 */
EmdModule.prototype.getId = function(id) {
	return this.getModuleId()+'_'+id;
};

/**
 * 대화상자를 실행 한다. 모듈명 : EmdModule 함수명 : doExecuteDialog
 *
 * @param dlgId -
 *            실행 할 대화상자 아이디 실행 후 처리 할때 사용 된다
 * @param dlgTitle -
 *            대화 상자의 타이틀 명
 * @param dlgAddr -
 *            대화상자 서버 url
 * @param dlgOpts -
 *            대화상자 옵션
 * @param dlgParams -
 *            대화상자 초기화시 넘겨 줄 변수 (loadComplete
 */
EmdModule.prototype.doExecuteDialog = function(dlgId, dlgTitle, dlgAddr, dlgOpts, dlgParams) {
	if($('#__dialog-modal' ).length==0) {
		var win = document.createElement('div');
		win.style.display='none';
		win.innerHTML='<div id="__dialog-modal"></div>';
		$('#desktop')[0].appendChild(win);
	}
	  EMD.popupcaller=this;
	  EMD.popupId=dlgId;
	  EMD.popupParams = dlgParams;
	  if(dlgAddr!=undefined) {
		  $('#__dialog-modal' ).load(EMD.context_root+dlgAddr, dlgOpts,
			  function( response, status, xhr ) {
				  if ( status == "error" ) {
				    var msg = "에러가 발생했습니다. 에러번호: ";
				    alert( msg + xhr.status + " " + xhr.statusText );
				  }
				  else {
					  var w, h;
					  w=popup_instance.getWidth();
					  h=popup_instance.getHeight();

					  $('#__dialog-modal').dialog({width: w, height: h, modal: true, closeOnEscape: true, title:dlgTitle });
					  $('#__dialog-modal')[0].module = popup_instance;

					  popup_instance.setModuleId('__dialog-modal');
					  popup_instance.setDialog($('#__dialog-modal'));
					  popup_instance.setPopupCaller(EMD.popupcaller);
					  popup_instance.setPopupId(EMD.popupId);

					  // 윈도우 속성 적용
					  EMD.util.modify_window(popup_instance, '__dialog-modal');

					  var win_id='__dialog-modal';

					  $('#'+win_id).on('resizeWindow', function(event){
						  event.stopPropagation();
						  EMD.util.window_resized($(this));
					  });
    			  		$('#'+win_id).trigger('resizeWindow');
    			  		$('#'+win_id).resizable({
    						create: function(event, ui) {

    						},
    						resize: function(event, ui) {
    							event.stopPropagation();
// $('#'+win_id).trigger('resizeWindow');
// EMD.util.window_resized(ui.element);
    						},
    						stop: function(event, ui) {
    							EMD.util.window_resized(ui.element);
    						}
    					});

				  		popup_instance.loadComplete(EMD.popupParams);
				  }
				});
	  }
};

/**
 * 파일 업로드 함수 모듈명 : EmdModule 함수명 : uploadFile
 *
 * @param upload_id -
 *            업로드가 끝난 후 처리 할 업로드 창 아이디
 * @param uploadcomplete -
 *            업로드가 끝난 후 호출 할 함수
 * @param dlgTitle -
 *            업로드 대화상자 타이틀 명
 */
EmdModule.prototype.uploadFile = function(upload_id, uploadcomplete, dlgTitle) {
	if(dlgTitle==null) dlgTitle="업로드 파일";

	$('tbody.files').empty();

// $('#fileupload').fileupload({
// // Uncomment the following to send cross-domain cookies:
// //xhrFields: {withCredentials: true},
// url: '/upload/genericMulti.do',
// autoUpload: true,
// });

	  $('#file_upload_dialog').dialog("option", {title: dlgTitle, closeOnEscape: true});
// $('#file_upload_dialog').dialog({close: function(event, ui) {
// }});
	  $('#file_upload_dialog').dialog({buttons: [ {text: '확인', click: function() {
		  var rows=[];
		  $('#fileupload tbody.files tr').each(function() {
			  rows[rows.length] = { physcalFileNm: $(this).data('physcal-file-nm'), logicalFileNm: $(this).data('logical-file-nm') };
		  });
		  $(this).trigger('fileUploaded',{ rows: rows });
		  $(this).dialog("close"); }}, {text: '취소', click: function() {
			  $(this).dialog("close");
		  }} ]});
	  $('#file_upload_dialog').dialog("open");

      // Load existing files:
      $('#fileupload').addClass('fileupload-processing');
// $.ajax({
// // Uncomment the following to send cross-domain cookies:
// //xhrFields: {withCredentials: true},
// url: $('#fileupload').fileupload('option', 'url'),
// dataType: 'json',
// context: $('#fileupload')[0]
// }).always(function () {
// $(this).removeClass('fileupload-processing');
// }).done(function (result) {
// $(this).fileupload('option', 'done')
// .call(this, $.Event('done'), {result: result});
// });

      $('#fileupload').data('module', this);
// $('#fileupload').data('module_id', upload_id);
// $('#fileupload').on('done', {dialog: $('#file_upload_dialog')},
// function(event, result) {
// $('#file_upload_dialog').uploadFileResult = result;
// $('#file_upload_dialog').trigger('fileUploaded', result);
// uploadcomplete($(this).data('module'), result);
// $(this).data('module').onUploadFileDone($(this).data('module'), result);
// });

      $('#file_upload_dialog').off('fileUploaded');
	  $('#file_upload_dialog').on('fileUploaded', {done: uploadcomplete, module: this}, function(event, data) {
		  event.data.done(event.data.module, data.rows);
	  });
};

/**
 * 시설 사진 파일 업로드 함수 모듈명 : EmdModule 함수명 : uploadPfPhoto
 *
 * @param upload_id -
 *            업로드가 끝난 후 처리 할 업로드 창 아이디
 * @param uploadcomplete -
 *            업로드가 끝난 후 호출 할 함수
 * @param dlgTitle -
 *            업로드 대화상자 타이틀 명
 */
EmdModule.prototype.uploadPfPhoto = function(upload_id, uploadcomplete, dlgTitle) {
	if(dlgTitle==null) dlgTitle="시설 사진 업로드";

	$('tbody.files').empty();

	  $('#pfPhoto_upload_dialog').dialog("option", {title: dlgTitle, closeOnEscape: true});
	  $('#pfPhoto_upload_dialog').dialog({buttons: [ {text: '확인', click: function() {
		  var rows=[];
		  $('#pfPhotoupload tbody.files tr').each(function() {
			  rows[rows.length] = { physcalFileNm: $(this).data('physcal-file-nm'), logicalFileNm: $(this).data('logical-file-nm') };
		  });
		  $(this).trigger('fileUploaded',{ rows: rows });
		  $(this).dialog("close"); }}, {text: '취소', click: function() {
			  $(this).dialog("close");
		  }} ]});
	  $('#pfPhoto_upload_dialog').dialog("open");

      $('#pfPhotoupload').addClass('fileupload-processing');

      $('#pfPhotoupload').data('module', this);

      $('#pfPhoto_upload_dialog').off('fileUploaded');
	  $('#pfPhoto_upload_dialog').on('fileUploaded', {done: uploadcomplete, module: this}, function(event, data) {
		  event.data.done(event.data.module, data.rows);
	  });
};

/**
 * 시설 사진을 다운로드 한다.
 */
EmdModule.prototype.downPfPhoto = function(filePhysiclNm, logicalName) {
	var url="/download/pfDownloadFile.do";
	var param = {"requestedFile": filePhysiclNm, "downloadFileName": logicalName};
	$.fileDownload(EMD.context_root+url, {data:param, httpMethod:"POST"});	// 헤더를
																			// 전송
																			// 해야
																			// 해서
																			// 컨트롤러를
																			// 따로
																			// 생성
																			// 해야 함
};


/**
 * 엑셀 파일 업로드 함수 모듈명 : EmdModule 함수명 : uploadXlsFile
 *
 * @param upload_id -
 *            업로드가 끝난 후 처리 할 업로드 창 아이디
 * @param uploadcomplete -
 *            업로드가 끝난 후 호출 할 함수
 * @param dlgTitle -
 *            업로드 대화상자 타이틀 명
 */
EmdModule.prototype.uploadXlsFile = function(upload_id, uploadcomplete, dlgTitle, url) {
	if(dlgTitle==null) dlgTitle="업로드 파일";

	  $('#xlsfile_upload_dialog').dialog("option", {title: dlgTitle, closeOnEscape: true});

	  $('#xlsfile_upload_dialog').dialog({buttons: [ {text: '확인', click: function() {
				  $(this).trigger('fileUploaded');
				  $(this).dialog("close");
		  }},
		  {text: '취소', click: function() {
			  $(this).dialog("close");
		  }}
	  ]});
	  $('#xlsfile_upload_dialog').dialog("open");

      $('#xlsfileupload').attr('action', EMD.context_root+url);
      $('#xlsfileupload').data('module', this);
      $('#xlsfile_upload_dialog').off('fileUploaded');
	  $('#xlsfile_upload_dialog').on('fileUploaded', {done: uploadcomplete, module: this}, function(event) {
		  event.data.done(event.data.module, null);
	  });
};

/**
 * 다운로드 파일 함수 - 파일을 다운로드 한다.
 */
EmdModule.prototype.downloadFile = function(filePhysiclNm, logicalName) {
	var url="/download/downloadFile.do";
	var param = {"requestedFile": filePhysiclNm, "downloadFileName": logicalName};
	$.fileDownload(EMD.context_root+url, {data:param, httpMethod:"POST"});	// 헤더를
																			// 전송
																			// 해야
																			// 해서
																			// 컨트롤러를
																			// 따로
																			// 생성
																			// 해야 함
};

/**
 * 자산 코드 추가 함수 - 자산코드에 대해 피처를 추가하고 등록 한다. 모듈명 : EmdModule 함수명 :
 * addGisAssetsCdMap
 *
 * @param mode -
 *            추가할 모드
 * @param params -
 *            피처에 추가할 속성 정보
 */
EmdModule.prototype.addGisAssetsCdMap = function(mode, params) {
	// if(EMD.gis.getFeature(mode, params).isExists()) EMD.gis.editFeature(mode,
	// params);
	EMD.util.window_hide_all();

	EMD.gis.addFeature(mode, params);
};

/**
 * 자산코드를 수정 한다.
 *
 * @param featureid -
 *            피처아이디
 * @param assetCode -
 *            자산코드 속성
 */
EmdModule.prototype.modifyGisAssetsCdMap = function(featureid, assetCode) {
	EMD.gis.modifyFeature(featureid, assetCode);
};

/**
 * GIS: 지번 코드로 자산코드를 추가 하여 저장
 *
 * @param featureid
 * @param assetCode
 */
EmdModule.prototype.addAssetsCodeByLotcode = function(featureid, assetCode) {
/*
 * var feature = EMD.lotarea.getFeatureById(featureid); feature.attributes = {
 * PRT_AT_CD: assetCode.gisAssetsPrtAtCode, PRT_CD: assetCode.gisAssetsCd,
 * PRT_SUB_CD: assetCode.gisAssetsSubCd, PRT_NM: assetCode.gisAssetsNm, LOCPLC:
 * assetCode.gisAssetsLocplc, };
 * EMD.userLayer.gisAssetsCd.addFeatures([feature]);
 * EMD.userLayer.gisAssetsCd.redraw();
 */
};

/**
 * 법정동 코드의 위치를 표시한다.
 *
 * @param code
 */
EmdModule.prototype.showBupJungDongCodeLocation = function(code) {
	EMD.gis.showBupJungDongCode(code);
};

/**
 * 이미지 URL을 표시한다.
 *
 * @param filenm -
 *            물리 파일 명
 * @returns {String} - 이미지 URL
 */
EmdModule.prototype.getImageUrl = function(filenm) {
	return EMD.context_root+'/cmm/getImage.do?physicalFileNm='+filenm;
};

/**
 * 시설 이미지 URL을 표시한다.
 *
 * @param filenm -
 *            물리 파일 명
 * @returns {String} - 이미지 URL
 */
EmdModule.prototype.getPfPhotoUrl = function(filenm) {
	return EMD.context_root+'/cmm/getPfImage.do?physicalFileNm='+filenm;
};

/**
 * 현재 창을 표시 한다.
 *
 * @param retval -
 *            리턴 값이 있을때 값을 넣는다.
 */
EmdModule.prototype.showWindow = function() {
	var window_id='#'+this.getModuleId();
	EMD.util.window_show($(window_id));
};

/**
 * 현재 창을 최소화 한다.
 *
 * @param retval -
 *            리턴 값이 있을때 값을 넣는다.
 */
EmdModule.prototype.hideWindow = function() {
	var window_id='#'+this.getModuleId();
	EMD.util.window_hide($(window_id));
};

/**
 * 현재 창을 닫는다.
 *
 * @param retval -
 *            리턴 값이 있을때 값을 넣는다.
 */
EmdModule.prototype.closeWindow = function(retval) {
	if(this._parent!=null) {
		var closeqry = this._parent.closeChildWindow(this.getModuleId(), retval);
		if(closeqry!=null && closeqry==false) return false;	// can't close
	}
	var window_id='#'+this.getModuleId();
	setTimeout(function() {
		EMD.util.window_close($(window_id));
		}, 0);
	return true;
};

/**
 * 창 닫을때 호출 하는 함수 *override 해서 사용
 *
 * @param window_id -
 *            닫히는 창 아이디
 * @param retval -
 *            닫히는 창에서 리턴하는 값
 * @returns {Boolean} - true이면 창 닫힘, false 이면 창 유지
 */
EmdModule.prototype.closeChildWindow = function(window_id, retval) {
	// overrid
	return true;
};
/**
 * 전자 결재 처리 모듈 - 전자결재를 처리한다.
 *
 */
EmdModule.prototype.requestEApproval = function(params, retfunc) {
	$('#__tempDiv').empty();
	var form = document.createElement("form");
	$(form).attr("id", "__requestEApprovalForm");
	var prtAtCode = document.createElement("input");
	var mngYear = document.createElement("input");
	var mngNo = document.createElement("input");
	var mngCnt = document.createElement("input");
	var type = document.createElement("input");
	$(prtAtCode).attr("name", "prtAtCode");
	$(prtAtCode).val(params.prtAtCode);
	$(mngYear).attr("name", "mngYear");
	$(mngYear).val(params.mngYear);
	$(mngNo).attr("name", "mngNo");
	$(mngNo).val(params.mngNo);
	$(mngCnt).attr("name", "mngCnt");
	$(mngCnt).val(params.mngCnt);
	$(type).attr("name", "type");
	$(type).val(params.type);
	if(params.type.substring(2, 3)=="N") {
		var nticCnt = document.createElement("input");
		$(nticCnt).attr("name", "nticCnt");
		$(nticCnt).val(params.nticCnt);
		$(form).append(nticCnt);
	}
	$(form).append(prtAtCode);
	$(form).append(mngYear);
	$(form).append(mngNo);
	$(form).append(mngCnt);
	$(form).append(type);
	$(form).attr("action", EMD.context_root+'/cmmn/fclty/openApprovalRequest.do');
	$('#__tempDiv').append(form);

	var win = window.open("","Window","width=1024, height=768, menubar=no,status=yes,scrollbars=no");

	var module=this;

/*
 * win[win.addEventListener ? 'addEventListener' : 'attachEvent'](
 * (win.attachEvent ? 'on' : '') + 'load', function(e){ retfunc(module, '결재 요청
 * 되었습니다.'); }, false );
 */
	form.target = "Window";
	form.submit();
};

/**
 * 고지서를 출력한다.
 */
EmdModule.prototype.printPayNotice = function(url, params, retfunc) {
	$('#__tempDiv').empty();
	var form = document.createElement("form");
	$(form).attr("id", "__printPayNoticeForm");
	var prtAtCode = document.createElement("input");
	var mngYear = document.createElement("input");
	var mngNo = document.createElement("input");
	var mngCnt = document.createElement("input");
	var nticCnt = document.createElement("input");
	var chrgeKnd = document.createElement("input");
	var workCode = document.createElement("input");
	var fiscalYr = document.createElement("input");
	var billNo = document.createElement("input");
	var dlySerNo = document.createElement("input");
	$(prtAtCode).attr("name", "prtAtCode");
	$(prtAtCode).val(params.prtAtCode);
	$(mngYear).attr("name", "mngYear");
	$(mngYear).val(params.mngYear);
	$(mngNo).attr("name", "mngNo");
	$(mngNo).val(params.mngNo);
	$(mngCnt).attr("name", "mngCnt");
	$(mngCnt).val(params.mngCnt);
	$(nticCnt).attr("name", "nticCnt");
	$(nticCnt).val(params.nticCnt);
	$(chrgeKnd).attr("name", "chrgeKnd");
	$(chrgeKnd).val(params.chrgeKnd);
	$(workCode).attr("name", "workCode");
	$(workCode).val(params.workCode);
	$(fiscalYr).attr("name", "fiscalYr");
	$(fiscalYr).val(params.fiscalYr);
	$(billNo).attr("name", "billNo");
	$(billNo).val(params.billNo);
	$(dlySerNo).attr("name", "dlySerNo");
	$(dlySerNo).val(params.dlySerNo);
	$(form).append(prtAtCode);
	$(form).append(mngYear);
	$(form).append(mngNo);
	$(form).append(mngCnt);
	$(form).append(nticCnt);
	$(form).append(chrgeKnd);
	$(form).append(workCode);
	$(form).append(fiscalYr);
	$(form).append(billNo);
	$(form).append(dlySerNo);
	$(form).attr("action", EMD.context_root+url);
	$('#__tempDiv').append(form);

	var win = window.open("","payNotice","width=800, height=600, menubar=no,status=no,scrollbars=yes");

	var module=this;

	win[win.addEventListener ? 'addEventListener' : 'attachEvent'](
			  (win.attachEvent ? 'on' : '') + 'load', function(e){
				  console.log('debug listener');
					retfunc(module, 'done');
				}, false
			);
	form.target = "payNotice";
	form.submit();
};

/**
 * 세금계산서를 출력한다.
 */
EmdModule.prototype.printTaxNotice = function(url, params, retfunc) {
	$('#__tempDiv').empty();
	var form = document.createElement("form");
	$(form).attr("id", "__printTaxNoticeForm");
	var prtAtCode = document.createElement("input");
	var mngYear = document.createElement("input");
	var mngNo = document.createElement("input");
	var mngCnt = document.createElement("input");
	var nticCnt = document.createElement("input");
	$(prtAtCode).attr("name", "prtAtCode");
	$(prtAtCode).val(params.prtAtCode);
	$(mngYear).attr("name", "mngYear");
	$(mngYear).val(params.mngYear);
	$(mngNo).attr("name", "mngNo");
	$(mngNo).val(params.mngNo);
	$(mngCnt).attr("name", "mngCnt");
	$(mngCnt).val(params.mngCnt);
	$(nticCnt).attr("name", "nticCnt");
	$(nticCnt).val(params.nticCnt);
	$(form).append(prtAtCode);
	$(form).append(mngYear);
	$(form).append(mngNo);
	$(form).append(mngCnt);
	$(form).append(nticCnt);
	$(form).attr("action", EMD.context_root+url);
	$('#__tempDiv').append(form);

	var win = window.open("","taxNotice","width=800, height=600, menubar=no,status=no,scrollbars=yes");

	var module=this;

/*
 * win[win.addEventListener ? 'addEventListener' : 'attachEvent'](
 * (win.attachEvent ? 'on' : '') + 'load', function(e){ retfunc(module, 'done'); },
 * false );
 */
	form.target = "taxNotice";
	form.submit();
};

/**
 * 인쇄 페이지를 호출 한다.
 */
EmdModule.prototype.printPage = function(url, params) {
	$('#__tempDiv').empty();
	var form = document.createElement("form");
	$(form).attr("accept-charset", "UTF-8");
	$(form).attr("id", "__printPayNoticeForm");
	$(form).attr("method", "post");
	for(var pn in params) {
		var e = document.createElement("input");
		$(e).attr("name", pn);
		$(form).attr("type", "hidden");
		$(e).val(params[pn]);
		$(form).append(e);
	}
	$(form).attr("action", EMD.context_root+url);
	$('#__tempDiv').append(form);

	var win = window.open("","printPage","width=800, height=600, menubar=no,status=no,scrollbars=yes");

	$(win).on("");

	var module=this;

	form.target = "printPage";
	form.submit();
};

/**
 * 피처코드를 수정한다.
 */
EmdModule.prototype.modifyAssetsCodeFeature = function(assetCode, feature) {
	EMD.gis.modifyAssetCdFeature(assetCode, feature);
};

/**
 * EmdPopupModule 을 생성한다. - 팝업 모듈 생성
 *
 * @param width -
 *            생성할 대화상자의 창 폭
 * @param height -
 *            생성할 대화상자의 창 높이
 */
function EmdPopupModule(width, height) {
	this._popupCaller=null;
	this._popupId =null;
	this._returnValue=null;
	if(width==undefined || width==null || height==undefined || height==null) {
		this._width=720;
		this._height=480;
	}
	else {
		this._width=width;
		this._height=height;
	}
	this.module=null;
	this.module_id="__dialog-modal";
	this._moduleType="_EMD_POPUP_MODULE";
};

EmdPopupModule.prototype = new EmdModuleBase();
/**
 * 대화상자 모듈 타입
 *
 * @returns {String}
 */
EmdPopupModule.prototype.getModuleType = function() {
	return this._moduleType;
};

/**
 * 대화상자 객체 저장
 *
 * @param dlg
 */
EmdPopupModule.prototype.setDialog = function(dlg) {
	this._dialog = dlg;
};

/**
 * 대화상자 객체 리턴
 *
 * @param dlg
 * @returns
 */
EmdPopupModule.prototype.getDialog = function(dlg) {
	return this._dialog;
};

/**
 * 팝업을 호출 한 객체를 저장한다.
 *
 * @param parent
 */
EmdPopupModule.prototype.setPopupCaller = function(parent) {
	this._popupCaller=parent;
};

/**
 * 팝업을 호출한 객체를 리턴한다.
 *
 * @param popupId
 * @param msg
 * @param retvalue
 */
EmdPopupModule.prototype.onClosePopup = function(popupId, msg, retvalue) {
	alert('close dialog '+popupId);
};

EmdPopupModule.prototype.setPopupId = function(id) {
	this._popupId=id;
};

EmdPopupModule.prototype.getWidth = function() {
	return this._width;
};

EmdPopupModule.prototype.setWidth = function(value) {
	this._width=value;
};

EmdPopupModule.prototype.getHeight = function() {
	return this._height;
};

EmdPopupModule.prototype.setHeight = function(value) {
	this._height=value;
};

EmdPopupModule.prototype.onButtonClick = function(button_id) {
	alert('button '+button_id+' is clicked');
};

EmdPopupModule.prototype.resizable = function(value) {
	var resizable = this._dialog.dialog( "option", "resizable" );
	if(resizable!=value) {
		 this._dialog.dialog( "option", "resizable", value );
	}
};

EmdPopupModule.prototype.closeDialog = function(msg, value) {
	this._dialog.dialog("close");
	this._popupCaller.onClosePopup(this._popupId, msg, value);
};

EmdPopupModule.prototype.cancelDialog = function() {
	this._dialog.dialog("close");
	this._popupCaller.onClosePopup(this._popupId, 'cancel', null);
};

/**
 * EmdPopupInfoModule 을 생성한다. - 지도 팝업 모듈 생성
 *
 * @param width -
 *            생성할 대화상자의 창 폭
 * @param height -
 *            생성할 대화상자의 창 높이
 */
function EmdPopupInfoModule(width, height) {
	this._popupCaller=null;
	this._popupId =null;
	this._popup =null;
	this._feature=null;
	this._returnValue=null;
	if(width==undefined || width==null || height==undefined || height==null) {
		this._width=720;
		this._height=480;
	}
	else {
		this._width=width;
		this._height=height;
	}
	this.module=null;
	this._moduleType="_EMD_POPUP_INFO_MODULE";
};

EmdPopupInfoModule.prototype = new EmdModuleBase();

/**
 * 대화상자 모듈 타입
 *
 * @returns {String}
 */
EmdPopupInfoModule.prototype.getModuleType = function() {
	return this._moduleType;
};

/**
 * 대화상자 객체 저장
 *
 * @param dlg
 */
EmdPopupInfoModule.prototype.setPopupId = function(dlg) {
	this._popupId = dlg;
};

/**
 * 대화상자 객체 리턴
 *
 * @param dlg
 * @returns
 */
EmdPopupInfoModule.prototype.getPopupId = function() {
	return this._popupId;
};

/**
 * 팝업 객체 저장
 *
 * @param dlg
 */
EmdPopupInfoModule.prototype.setPopup = function(pop) {
	this._popup = pop;
};

/**
 * 팝업 객체 리턴
 *
 * @param dlg
 * @returns
 */
EmdPopupInfoModule.prototype.getPopup= function() {
	return this._popup;
};

/**
 * 피처 객체 저장
 *
 * @param dlg
 */
EmdPopupInfoModule.prototype.setFeature = function(feature) {
	this._feature = feature;
};

/**
 * 피처 객체 리턴
 *
 * @param dlg
 * @returns
 */
EmdPopupInfoModule.prototype.getFeature= function() {
	return this._feature;
};

EmdPopupInfoModule.prototype.getWidth = function() {
	return this._width;
};

EmdPopupInfoModule.prototype.setWidth = function(value) {
	this._width=value;
};

EmdPopupInfoModule.prototype.getHeight = function() {
	return this._height;
};

EmdPopupInfoModule.prototype.setHeight = function(value) {
	this._height=value;
};

EmdPopupInfoModule.prototype.onButtonClick = function(button_id) {
	alert('button '+button_id+' is clicked');
};

EmdPopupInfoModule.prototype.closeDialog = function(msg, value) {
	this._dialog.dialog("close");
	this._popupCaller.onClosePopup(this._popupId, msg, value);
};

EmdPopupInfoModule.prototype.cancelDialog = function() {
	this._dialog.dialog("close");
	this._popupCaller.onClosePopup(this._popupId, 'cancel', null);
};

/**
 * 에러 모듈
 */
function EmdErrorModule() {
	this._msg="오류가 발생하였습니다.";
	this._moduleType="_EMD_ERROR_MODULE";
};

EmdErrorModule.prototype.getModuleType = function() {
	return this._moduleType;
};

EmdErrorModule.prototype.loadComplete = function() {
};

EmdErrorModule.prototype.setMessage = function(msg) {
	this._msg=msg;
};

EmdErrorModule.prototype.getMessage = function() {
	return this._msg;
};

/*
 * jquery get ygpa code label plugin
 */
(function($){
	"use strict";

	/**
	 * @export &
	 * @api
	 */
	$.fn.getSelectedCodeLabel = function() {
		var label='';
		if($(this).hasClass("ygpaCmmnCode") || $(this).hasClass("ygpaCmmnCl") || $(this).hasClass("ygpaCmmnCd") || $(this).hasClass("ygpaFilterCode") || $(this).hasClass("ygpaDeptSelect") || $(this).hasClass("ygpaYnSelect")) {
			label=$('#'+$(this).attr('id')+'_select.frmwrkAuto').find(":selected").text();
		}
		return label;
	};
	/**
	 * @export &
	 * @api
	 */
	$.fn.getSelectedCodeLabel = function() {
		var label='';
		if($(this).hasClass("ygpaCmmnCode") || $(this).hasClass("ygpaCmmnCl") || $(this).hasClass("ygpaCmmnCd") || $(this).hasClass("ygpaFilterCode") || $(this).hasClass("ygpaDeptSelect") || $(this).hasClass("ygpaYnSelect")) {
			label=$('#'+$(this).attr('id')+'_select.frmwrkAuto').find(":selected").text();
		}
		return label;
	};

})(jQuery);


/**
 * jQuery number plug-in 2.1.3 Copyright 2012, Digital Fusion Licensed under the
 * MIT license. http://opensource.teamdf.com/license/
 *
 * A jQuery plugin which implements a permutation of phpjs.org's number_format
 * to provide simple number formatting, insertion, and as-you-type masking of a
 * number.
 *
 * @author Sam Sehnert
 * @docs http://www.teamdf.com/web/jquery-number-format-redux/196/
 */
(function($){

	"use strict";

	function setSelectionRange( rangeStart, rangeEnd )
	{
		// Check which way we need to define the text range.
		if( this.createTextRange )
		{
			var range = this.createTextRange();
				range.collapse( true );
				range.moveStart( 'character',	rangeStart );
				range.moveEnd( 'character',		rangeEnd-rangeStart );
				range.select();
		}

		// Alternate setSelectionRange method for supporting browsers.
		else if( this.setSelectionRange )
		{
			this.focus();
			this.setSelectionRange( rangeStart, rangeEnd );
		}
	}

	function getSelection( part )
	{
		var pos	= this.value.length;

		// Work out the selection part.
		part = ( part.toLowerCase() == 'start' ? 'Start' : 'End' );

		if( document.selection ){
			// The current selection
			var range = document.selection.createRange(), stored_range, selectionStart, selectionEnd;
			// We'll use this as a 'dummy'
			stored_range = range.duplicate();
			// Select all text
			// stored_range.moveToElementText( this );
			stored_range.expand('textedit');
			// Now move 'dummy' end point to end point of original range
			stored_range.setEndPoint( 'EndToEnd', range );
			// Now we can calculate start and end points
			selectionStart = stored_range.text.length - range.text.length;
			selectionEnd = selectionStart + range.text.length;
			return part == 'Start' ? selectionStart : selectionEnd;
		}

		else if(typeof(this['selection'+part])!="undefined")
		{
		 	pos = this['selection'+part];
		}
		return pos;
	}

	/**
	 * Substitutions for keydown keycodes. Allows conversion from e.which to
	 * ascii characters.
	 */
	var _keydown = {
		codes : {
			188 : 44,
			109 : 45,
			190 : 46,
			191 : 47,
			192 : 96,
			220 : 92,
			222 : 39,
			221 : 93,
			219 : 91,
			173 : 45,
			187 : 61, // IE Key codes
			186 : 59, // IE Key codes
			189 : 45, // IE Key codes
			110 : 46  // IE Key codes
        },
        shifts : {
			96 : "~",
			49 : "!",
			50 : "@",
			51 : "#",
			52 : "$",
			53 : "%",
			54 : "^",
			55 : "&",
			56 : "*",
			57 : "(",
			48 : ")",
			45 : "_",
			61 : "+",
			91 : "{",
			93 : "}",
			92 : "|",
			59 : ":",
			39 : "\"",
			44 : "<",
			46 : ">",
			47 : "?"
        }
    };

	$.fn.number = function( number, decimals, dec_point, thousands_sep ){

	    // Enter the default thousands separator, and the decimal placeholder.
	    thousands_sep	= (typeof thousands_sep === 'undefined') ? ',' : thousands_sep;
	    dec_point		= (typeof dec_point === 'undefined') ? '.' : dec_point;
	    decimals		= (typeof decimals === 'undefined' ) ? 0 : decimals;

	    // Work out the unicode character for the decimal placeholder.
	    var u_dec			= ('\\u'+('0000'+(dec_point.charCodeAt(0).toString(16))).slice(-4)),
	    	regex_dec_num	= new RegExp('[^'+u_dec+'0-9]','g'),
	    	regex_dec		= new RegExp(u_dec,'g');

	    if( number === true )
	    {
	    	if( this.is('input:text') )
	    	{
	    		return this.on({
	    			'keydown.format' : function(e){
	    				var $this	= $(this),
	    					data	= $this.data('numFormat'),
	    					code	= (e.keyCode ? e.keyCode : e.which),
							chara	= '', // unescape(e.originalEvent.keyIdentifier.replace('U+','%u')),
	    					start	= getSelection.apply(this,['start']),
	    					end		= getSelection.apply(this,['end']),
	    					val		= '',
	    					setPos	= false;

    					if (_keydown.codes.hasOwnProperty(code)) {
				            code = _keydown.codes[code];
				        }
				        if (!e.shiftKey && (code >= 65 && code <= 90)){
				        	code += 32;
				        } else if (!e.shiftKey && (code >= 69 && code <= 105)){
				        	code -= 48;
				        } else if (e.shiftKey && _keydown.shifts.hasOwnProperty(code)){
				            // get shifted keyCode value
				            chara = _keydown.shifts[code];
				        }

				        if( chara == '' ) chara = String.fromCharCode(code);

	    				if( code !== 8 && chara != dec_point && !chara.match(/[0-9]/) )
	    				{
	    					var ct = true;
							if (chara.match(/[-]/)) {
								if (!data.isNegative) {
									// User is trying to make this field a
									// negative number
									data.isNegative = true;
									ct = false;
								}
							}
  							if (ct) {
		    					// We need the original keycode now...
		    					var key = (e.keyCode ? e.keyCode : e.which);
		    					if( // Allow control keys to go through...
									// (delete, etc)
		    						key == 46 || key == 8 || key == 9 || key == 27 || key == 13 ||
		    						// Allow: Ctrl+A, Ctrl+R
		    						( (key == 65 || key == 82 ) && ( e.ctrlKey || e.metaKey ) === true ) ||
		    						// Allow: Ctrl+V, Ctrl+C
		    						( (key == 86 || key == 67 ) && ( e.ctrlKey || e.metaKey ) === true ) ||
		    						// Allow: home, end, left, right
		    						( (key >= 35 && key <= 39) )
								){
									return;
								}
								// But prevent all other keys.
								e.preventDefault();
								return false;
							}
	    				}

	    				// The whole lot has been selected, or if the field is
						// empty...
	    				if( start == 0 && end == this.value.length || $this.val() == 0 )
	    				{
	    					if( code === 8 )
	    					{
		    					// Blank out the field, but only if the data
								// object has already been instanciated.
	    						start = end = 1;
	    						this.value = '';

	    						// Reset the cursor position.
		    					data.init = (decimals>0?-1:0);
		    					data.c = (decimals>0?-(decimals+1):0);
		    					setSelectionRange.apply(this, [0,0]);
		    				}
		    				else if( chara === dec_point )
		    				{
		    					start = end = 1;
		    					this.value = '0'+ dec_point + (new Array(decimals+1).join('0'));

		    					// Reset the cursor position.
		    					data.init = (decimals>0?1:0);
		    					data.c = (decimals>0?-(decimals+1):0);
		    				}
		    				else
		    				{
		    					// Reset the cursor position.
		    					data.init = (decimals>0?-1:0);
		    					data.c = (decimals>0?-(decimals+1):0);
		    				}
	    				}
	    				else
	    				{
	    					data.c = end-this.value.length;
	    				}

						if (code == 8 && start <= 1 && data.isNegative)
						{
							e.preventDefault();
							data.isNegative = false;
							data.c--;
							setPos = this.value.length+data.c;
						}
	    				else if( decimals > 0 && chara == dec_point && start == this.value.length-decimals-1 )
	    				{
	    					data.c++;
	    					data.init = Math.max(0,data.init);
	    					e.preventDefault();

	    					// Set the selection position.
	    					setPos = this.value.length+data.c;
	    				}
	    				else if( chara == dec_point )
	    				{
	    					data.init = Math.max(0,data.init);
	    					e.preventDefault();
	    				}

	    				else if( decimals > 0 && code == 8 && start == this.value.length-decimals )
	    				{
	    					e.preventDefault();
	    					data.c--;

	    					// Set the selection position.
	    					setPos = this.value.length+data.c;
	    				}

	    				else if( decimals > 0 && code == 8 && start > this.value.length-decimals )
	    				{
	    					if( this.value === '' ) return;

	    					// If the character preceeding is not already a 0,
	    					// replace it with one.
	    					if( this.value.slice(start-1, start) != '0' )
	    					{
	    						val = this.value.slice(0, start-1) + '0' + this.value.slice(start);
	    						$this.val(val.replace(regex_dec_num,'').replace(regex_dec,dec_point));
	    					}

	    					e.preventDefault();
	    					data.c--;

	    					// Set the selection position.
	    					setPos = this.value.length+data.c;
	    				}

	    				else if( code == 8 && this.value.slice(start-1, start) == thousands_sep )
	    				{
	    					e.preventDefault();
	    					data.c--;

	    					// Set the selection position.
	    					setPos = this.value.length+data.c;
	    				}
	    				else if(
	    					decimals > 0 &&
	    					start == end &&
	    					this.value.length > decimals+1 &&
	    					start > this.value.length-decimals-1 && isFinite(+chara) &&
		    				!e.metaKey && !e.ctrlKey && !e.altKey && chara.length === 1
	    				) {
	    					// If the character preceeding is not already a 0,
	    					// replace it with one.
	    					if( end === this.value.length )
	    					{
	    						val = this.value.slice(0, start-1);
	    					}
	    					else
	    					{
	    						val = this.value.slice(0, start)+this.value.slice(start+1);
	    					}

	    					// Reset the position.
	    					this.value = val;
	    					setPos = start;
	    				}

	    				// If we need to re-position the characters.
	    				if( setPos !== false )
	    				{
	    					// //console.log('Setpos keydown: ', setPos );
	    					setSelectionRange.apply(this, [setPos, setPos]);
	    				}

	    				// Store the data on the element.
	    				$this.data('numFormat', data);

	    			},
	    			'keyup.format' : function(e){

	    				// Store these variables for use below.
	    				var $this	= $(this),
	    					data	= $this.data('numFormat'),
	    					code	= (e.keyCode ? e.keyCode : e.which),
	    					start	= getSelection.apply(this,['start']),
	    					setPos;

	    				// Stop executing if the user didn't type a number key,
						// a decimal, or a comma.
	    				if( this.value === '' || (code < 48 || code > 57) && (code < 96 || code > 105 ) && code !== 8 ) return;

	    				// Re-format the textarea.
	    				$this.val($this.val());

	    				if( decimals > 0 )
	    				{
	    					if( start > this.value.length-decimals && code != 8 ) {
		    					data.c++;

		    					// Store the data, now that it's changed.
		    					$this.data('numFormat', data);
		    				}
	    				}

	    				// //console.log( 'Setting pos: ', start, decimals,
						// this.value.length + data.c, this.value.length, data.c
						// );

	    				if (!$this.get(0).value.length) {
	    					// If they delete the entire contents of the text
							// field, remove the 'negative' variable
							data.isNegative = false;
						} else if (data.isNegative) {
							// Otherwise, we add the - sign to the beginning of
							// the field if it's negative
							$this.get(0).value = '-' + this.value;
						}

	    				// Set the selection position.
	    				setPos = this.value.length+data.c;
	    				setSelectionRange.apply(this, [setPos, setPos]);
	    			},
	    			'paste.format' : function(e){
	    				var $this		= $(this),
	    					original	= e.originalEvent,
	    					val		= null;

						if (window.clipboardData && window.clipboardData.getData) { // IE
							val = window.clipboardData.getData('Text');
						} else if (original.clipboardData && original.clipboardData.getData) {
							val = original.clipboardData.getData('text/plain');
						}

	    				$this.val(val);

	    				e.preventDefault();
	    				return false;
	    			}

	    		}).each(function(){

    				var $this = $(this).data('numFormat',{
    					c				: -(decimals+1),
    					decimals		: decimals,
    					thousands_sep	: thousands_sep,
    					dec_point		: dec_point,
    					regex_dec_num	: regex_dec_num,
    					regex_dec		: regex_dec,
    					init			: -1
    				});

    				if( this.value === '' ) return;

    				$this.val($this.val());
    			});
	    	}
	    	else
	    	{
				return this.each(function(){
					var $this = $(this),
						isNegative = $this.text().match(/^-/) ? -1 : 1,
						num = +$this.text().replace(regex_dec_num,'').replace(regex_dec,'.') * isNegative;
					$this.number( !isFinite(num) ? 0 : +num, decimals, dec_point, thousands_sep );
				});
	    	}
	    }

	    return this.text( $.number.apply(window,arguments) );
	};

	var origHookGet = null, origHookSet = null;

	if( $.isPlainObject( $.valHooks.text ) )
	{
	    if( $.isFunction( $.valHooks.text.get ) ) origHookGet = $.valHooks.text.get;
	    if( $.isFunction( $.valHooks.text.set ) ) origHookSet = $.valHooks.text.set;
	}
	else
	{
	    $.valHooks.text = {};
	}

	/**
	 * Define the valHook to return normalised field data against an input which
	 * has been tagged by the number formatter.
	 *
	 * @param object
	 *            el : The raw DOM element that we're getting the value from.
	 *
	 * @return mixed : Returns the value that was written to the element as a
	 *         javascript number, or undefined to let jQuery handle it normally.
	 */
	$.valHooks.text.get = function( el ){

		// Get the element, and its data.
		var $this	= $(el), num,
			data	= $this.data('numFormat');

        // Does this element have our data field?
        if( !data )
        {
            // Check if the valhook function already existed
            if( $.isFunction( origHookGet ) )
            {
                // There was, so go ahead and call it
                return origHookGet(el);
            }
            else
            {
                // No previous function, return undefined to have jQuery
                // take care of retrieving the value
                return undefined;
			}
		}
		else
		{
			// Remove formatting, and return as number.
			if( el.value === '' ) return '';

			// If the first character is a minus sign,
			// we assume the number is negative.
			if (el.value.match(/^-/)) {
				data.isNegative = true;
			}

			// Convert to a number.
			num = +(el.value
				.replace( data.regex_dec_num, '' )
				.replace( data.regex_dec, '.' ));

			// If we've got a finite number, return it.
			// Otherwise, simply return 0.
			num = ( isFinite( num ) ? num : 0 );

			// If it's a negative number, times by -1.
			if (num != 0 && data.isNegative) {
			  num *= -1;
			}

			// Return as a string... thats what we're
			// used to with .val()
			return ''+num;
		}
	};

	/**
	 * A valhook which formats a number when run against an input which has been
	 * tagged by the number formatter.
	 *
	 * @param object
	 *            el : The raw DOM element (input element).
	 * @param float :
	 *            The number to set into the value field.
	 *
	 * @return mixed : Returns the value that was written to the element, or
	 *         undefined to let jQuery handle it normally.
	 */
	$.valHooks.text.set = function( el, val )
	{
		// Get the element, and its data.
		var $this	= $(el),
			data	= $this.data('numFormat');

		// Does this element have our data field?
		if( !data )
		{

		    // Check if the valhook function already exists
		    if( $.isFunction( origHookSet ) )
		    {
		        // There was, so go ahead and call it
		        return origHookSet(el,val);
		    }
		    else
		    {
		        // No previous function, return undefined to have jQuery
		        // take care of retrieving the value
		        return undefined;
			}
		}
		else
		{
			if(val === '')
			{
				return el.value = '';
			}
			// Otherwise, don't worry about other valhooks, just run ours.
			return el.value = $.number( val, data.decimals, data.dec_point, data.thousands_sep );
		}
	};

	/**
	 * The (modified) excellent number formatting method from PHPJS.org.
	 * http://phpjs.org/functions/number_format/
	 *
	 * @modified by Sam Sehnert (teamdf.com) - don't redefine dec_point,
	 *           thousands_sep... just overwrite with defaults. - don't redefine
	 *           decimals, just overwrite as numeric. - Generate regex for
	 *           normalizing pre-formatted numbers.
	 *
	 * @param float
	 *            number : The number you wish to format, or TRUE to use the
	 *            text contents of the element as the number. Please note that
	 *            this won't work for elements which have child nodes with text
	 *            content.
	 * @param int
	 *            decimals : The number of decimal places that should be
	 *            displayed. Defaults to 0.
	 * @param string
	 *            dec_point : The character to use as a decimal point. Defaults
	 *            to '.'.
	 * @param string
	 *            thousands_sep : The character to use as a thousands separator.
	 *            Defaults to ','.
	 *
	 * @return string : The formatted number as a string.
	 */
	$.number = function( number, decimals, dec_point, thousands_sep ){
		// Set the default values here, instead so we can use them in the
		// replace below.
		thousands_sep	= (typeof thousands_sep === 'undefined') ? ',' : thousands_sep;
		dec_point		= (typeof dec_point === 'undefined') ? '.' : dec_point;
		decimals		= !isFinite(+decimals) ? 0 : Math.abs(decimals);

		// Work out the unicode representation for the decimal place and
		// thousand sep.
		var u_dec = ('\\u'+('0000'+(dec_point.charCodeAt(0).toString(16))).slice(-4));
		var u_sep = ('\\u'+('0000'+(thousands_sep.charCodeAt(0).toString(16))).slice(-4));

		// Fix the number, so that it's an actual number.
		number = (number + '')
			.replace('\.', dec_point) // because the number if passed in as a
										// float (having . as decimal point per
										// definition) we need to replace this
										// with the passed in decimal point
										// character
			.replace(new RegExp(u_sep,'g'),'')
			.replace(new RegExp(u_dec,'g'),'.')
			.replace(new RegExp('[^0-9+\-Ee.]','g'),'');

		var n = !isFinite(+number) ? 0 : +number,
		    s = '',
		    toFixedFix = function (n, decimals) {
		        var k = Math.pow(10, decimals);
		        return '' + Math.round(n * k) / k;
		    };

		// Fix for IE parseFloat(0.55).toFixed(0) = 0;
		s = (decimals ? toFixedFix(n, decimals) : '' + Math.round(n)).split('.');
		if (s[0].length > 3) {
		    s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, thousands_sep);
		}
		if ((s[1] || '').length < decimals) {
		    s[1] = s[1] || '';
		    s[1] += new Array(decimals - s[1].length + 1).join('0');
		}
		return s.join(dec_point);
	};

})(jQuery);

// for ie9 add indexOf in Array
if (!Array.prototype.indexOf) {
    Array.prototype.indexOf = function (searchElement /* , fromIndex */ ) {
        "use strict";
        if (this == null) {
            throw new TypeError();
        }
        var t = Object(this);
        var len = t.length >>> 0;
        if (len === 0) {
            return -1;
        }
        var n = 0;
        if (arguments.length > 1) {
            n = Number(arguments[1]);
            if (n != n) { // shortcut for verifying if it's NaN
                n = 0;
            } else if (n != 0 && n != Infinity && n != -Infinity) {
                n = (n > 0 || -1) * Math.floor(Math.abs(n));
            }
        }
        if (n >= len) {
            return -1;
        }
        var k = n >= 0 ? n : Math.max(len - Math.abs(n), 0);
        for (; k < len; k++) {
            if (k in t && t[k] === searchElement) {
                return k;
            }
        }
        return -1;
    };
}

/** 실시간 선택 상자를 만드는 함수 */
(function($) {
	$.fn.makeSelectDeptCd = function(){
		return this.each(function(){
			var default_prompt='';
			var default_value='';
			var input_item=this;
			var input_id=$(this).attr('id');
			var win_id="window_"+$(this).attr('id').split("_")[1];
			if($(this).data('default-prompt')!=null) {
				if($(this).data('default-prompt')!=null) default_prompt=$(this).data('default-prompt');
				else default_prompt="선택";
				if($(this).data('default-value')!=null) default_value=$(this).data('default-value');
				else default_value="";
			}
			if($(this).data('display-value')==null) {
				try {
	  	  			$(this).attr("type", "hidden");
				}
				catch(err) {
	  	  			$(this).attr("id", input_id+"_backup");
	  	  			$(this).after('<input type="hidden" id="'+input_id+'" />');
	  	  			input_item = $('#'+win_id+' #'+input_id)[0];
	  	  			$(this).remove();
				}
	  			$(input_item).after('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
			}
			else if($(this).data('display-value')=='N') {
	  			$(this).attr("readonly", "readonly");
	  			$(this).before('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
			}
			else {
	  			$(this).attr("readonly", "readonly");
	  			$(this).after('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
			}
		if(default_prompt!='') {
				$(this).append('<option value="'+default_value+'" selected>'+default_prompt+'</option>');
		}
		$(this).focus(function() {
			$('#'+$(this).attr("id")+"_select")[0].focus();
		});

			$.ajax({
				url: EMD.context_root+'/cmmn/selectDeptCodeOptionsList.do',
				type: 'POST',
				module: input_item,
				dataType: 'json',
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				data: [{name: 'cmd', value: ''}],
			success: function (data) {
				if(data.resultCode!=null && data.resultCode!=0) {
					if(data.resultCode==1) {	// 로그인 에러
						alert(data.resultMsg);
						location.reload();
					}
				}
				else {
					var selected_code='';
					var comp_id='#'+$(this.module).attr('id')+'_select';
					if($(this.module).data('value')!=undefined) {
						selected_code=$(this.module).data('value');
	  					for(var i=0; i<data.resultList.length; i++) {
	  						if(selected_code==data.resultList[i].value) {
		  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'" selected>'+data.resultList[i].name+'</option>');
	  						}
	  						else {
		  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'">'+data.resultList[i].name+'</option>');
	  						}
	  					}
					}
					else {
	  					for(var i=0; i<data.resultList.length; i++) {
	  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'">'+data.resultList[i].name+'</option>');
	  					}
					}
					$('#'+win_id).find(comp_id).change({module: this.module}, function(event){
						$(event.data.module).val( $(this).find("option:selected").val() );
						$(event.data.module).trigger('change');
					});
					if($(this.module).attr("readonly")=="readonly") $(comp_id).attr("readonly", "readonly");
					if($(this.module).attr("disabled")=="disabled") $(comp_id).attr("disabled", "disabled");
				}
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				try {
					// console.log(textStatus);
// if (p.onError) p.onError(XMLHttpRequest, textStatus, errorThrown);
				} catch (e) {}
			}
			});
		});
	};

	$.fn.makeSelectCmmnCd = function(){
		return this.each(function() {
  			var default_prompt='';
  			var default_value='';
  			var input_item=this;
  			var win_id = "window_"+$(this).attr('id').split("_")[1];
  			var input_id=$(this).attr('id');
  			if($(this).data('code-id')==undefined) return;
  			if($(this).data('default-prompt')!=null) {
  				if($(this).data('default-prompt')!=null) default_prompt=$(this).data('default-prompt');
  				else default_prompt="선택";
  				if($(this).data('default-value')!=null) default_value=$(this).data('default-value');
  				else default_value="";
  			}
  			if($(this).data('display-value')==null) {
  				try {
  	  	  			$(this).attr("type", "hidden");
  				}
  				catch(err) {
  	  	  			$(this).attr("id", input_id+"_backup");
  	  	  			$(this).after('<input type="hidden" id="'+input_id+'" />');
  	  	  			input_item = $('#'+win_id+' #'+input_id)[0];
  	  	  			$(this).remove();
				}
  				$(input_item).after('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
  			else if($(this).data('display-value')=='N') {
  	  			$(this).attr("readonly", "readonly");
  	  			$(this).before('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
  			else {
  	  			$(this).attr("readonly", "readonly");
  	  			$(this).after('<select id="'+input_id+'_select" class="frmwrkAuto"></select>');
  			}
			if(default_prompt!='') {
					if($(input_item).data('value')==null) $('#'+win_id).find('#'+input_id+'_select').append('<option value="'+default_value+'" selected>'+default_prompt+'</option>');
					else $('#'+win_id).find('#'+input_id+'_select').append('<option value="'+default_value+'">'+default_prompt+'</option>');
			}
			$(this).focus(function() {
				$('#'+$(this).attr("id")+"_select")[0].focus();
			});
  			$.ajax({
  				url: EMD.context_root+'/cmmn/selectCmmnCdOptionsList.do',
  				type: 'POST',
  				module: input_item,
  				dataType: 'json',
  				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
  				data: [{name: 'code_id', value: $(this).data('code-id')}],
				success: function (data) {
					if(data.resultCode!=null && data.resultCode!=0) {
						if(data.resultCode==1) {	// 로그인 에러
							alert(data.resultMsg);
							location.reload();
						}
					}
					else {
						var selected_code='';
						var comp_id='#'+$(this.module).attr('id')+'_select';
						if($(this.module).data('value')!=null) {
							selected_code=$(this.module).data('value');
		  					for(var i=0; i<data.resultList.length; i++) {
		  						if(selected_code==data.resultList[i].value) {
			  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'" selected>'+data.resultList[i].name+'</option>');
		  						}
		  						else {
			  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'">'+data.resultList[i].name+'</option>');
		  						}
		  					}
						}
						else {
		  					for(var i=0; i<data.resultList.length; i++) {
		  	  					$('#'+win_id).find(comp_id).append('<option value="'+data.resultList[i].value+'">'+data.resultList[i].name+'</option>');
		  					}
						}
						$('#'+win_id).find(comp_id).change({module: this.module}, function(event){
							$(event.data.module).val( $(this).find("option:selected").val() );
							$(event.data.module).trigger('change');
						});
						if($(this.module).attr("readonly")=="readonly") $(comp_id).attr("readonly", "readonly");
						if($(this.module).attr("disabled")=="disabled") $(comp_id).attr("disabled", "disabled");
					}
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
					// console.log(textStatus);
// try {
// if (p.onError) p.onError(XMLHttpRequest, textStatus, errorThrown);
// } catch (e) {

// }
				}
  				});
  		});
	};
})(jQuery);

(function($){
	$.fn.popupWindow = function(instanceSettings){

		return this.each(function(){

		$(this).click(function(){

		$.fn.popupWindow.defaultSettings = {
			centerBrowser:0, // center window over browser window? {1 (YES)
								// or 0 (NO)}. overrides top and left
			centerScreen:0, // center window over entire screen? {1 (YES) or 0
							// (NO)}. overrides top and left
			height:500, // sets the height in pixels of the window.
			left:0, // left position when the window appears.
			location:0, // determines whether the address bar is displayed {1
						// (YES) or 0 (NO)}.
			menubar:0, // determines whether the menu bar is displayed {1 (YES)
						// or 0 (NO)}.
			resizable:0, // whether the window can be resized {1 (YES) or 0
							// (NO)}. Can also be overloaded using resizable.
			scrollbars:0, // determines whether scrollbars appear on the
							// window {1 (YES) or 0 (NO)}.
			status:0, // whether a status line appears at the bottom of the
						// window {1 (YES) or 0 (NO)}.
			width:500, // sets the width in pixels of the window.
			windowName:null, // name of window set from the name attribute of
								// the element that invokes the click
			windowURL:null, // url used for the popup
			top:0, // top position when the window appears.
			toolbar:0 // determines whether a toolbar (includes the forward
						// and back buttons) is displayed {1 (YES) or 0 (NO)}.
		};

		settings = $.extend({}, $.fn.popupWindow.defaultSettings, instanceSettings || {});

		var windowFeatures =    'height=' + settings.height +
								',width=' + settings.width +
								',toolbar=' + settings.toolbar +
								',scrollbars=' + settings.scrollbars +
								',status=' + settings.status +
								',resizable=' + settings.resizable +
								',location=' + settings.location +
								',menuBar=' + settings.menubar;

				settings.windowName = this.name || settings.windowName;
				settings.windowURL = this.href || settings.windowURL;
				var centeredY,centeredX;

				if(settings.centerBrowser){

					if ($.browser.msie) {// hacked together for IE browsers
						centeredY = (window.screenTop - 120) + ((((document.documentElement.clientHeight + 120)/2) - (settings.height/2)));
						centeredX = window.screenLeft + ((((document.body.offsetWidth + 20)/2) - (settings.width/2)));
					}else{
						centeredY = window.screenY + (((window.outerHeight/2) - (settings.height/2)));
						centeredX = window.screenX + (((window.outerWidth/2) - (settings.width/2)));
					}
					window.open(settings.windowURL, settings.windowName, windowFeatures+',left=' + centeredX +',top=' + centeredY).focus();
				}else if(settings.centerScreen){
					centeredY = (screen.height - settings.height)/2;
					centeredX = (screen.width - settings.width)/2;
					window.open(settings.windowURL, settings.windowName, windowFeatures+',left=' + centeredX +',top=' + centeredY).focus();
				}else{
					window.open(settings.windowURL, settings.windowName, windowFeatures+',left=' + settings.left +',top=' + settings.top).focus();
				}
				return false;
			});

		});
	};
})(jQuery);

/**
 * readonly, disable plug in 객체를 disable 처리 한다.
 */
(function($) {

    $.fn.disable = function(options) {
        var opts = $.extend({}, $.fn.disable.defaults, options);

        function disable($this) {
            $this.attr('disabled', 'disabled');
            $('#'+$this.attr('id')+'_select').attr('disabled', 'disabled');

            if(opts.disableClass !== '') {
                 $this.addClass(opts.disableClass);
                 $('#'+$this.attr('id')+'_select').addClass(opts.disableClass);
            }
			if(opts.enableClass !== '') {
				$this.removeClass(opts.enableClass);
	            $('#'+$this.attr('id')+'_select').removeClass(opts.enableClass);
			}
        }

        function enable($this) {
            $this.removeAttr('disabled');
            $('#'+$this.attr('id')+'_select').removeAttr('disabled');
			if(opts.enableClass !== '') {
                 $this.addClass(opts.enableClass);
                 $('#'+$this.attr('id')+'_select').addClass(opts.enableClass);
            }
			if(opts.disableClass !== '') {
				$this.removeClass(opts.disableClass);
	            $('#'+$this.attr('id')+'_select').removeClass(opts.disableClass);
			}
        }

        function setEnableTimeout($this) {
            setTimeout(function() {
                enable($this);
            }, opts.enableAfterSeconds * 1000);
        }

        function setAjaxEnable($this) {
            $this.ajaxComplete(function(event, request, settings){
                var statusOk = !opts.enableOnAjaxSuccess || (opts.enableOnAjaxSuccess && request.status === 200),
                    urlOk = opts.ajaxUrl === null || settings.url === opts.ajaxUrl,
                    responseTextOk = opts.ajaxResponseText === null || request.responseText === opts.ajaxResponseText;
                if(statusOk && urlOk && responseTextOk) {
                    enable($this);
                }
            });
        }

        return this.each(function() {
            var $this = $(this);

            disable($this);

            if(opts.enableAfterSeconds > 0) {
                setEnableTimeout($this);
            }

            if(opts.enableOnAjaxComplete || opts.enableOnAjaxSuccess) {
                setAjaxEnable($this);
            }
         });
     };

    $.fn.disable.defaults = {
        enableClass: '',
		disableClass: '',
        enableAfterSeconds: 0,
        enableOnAjaxComplete: false,
        enableOnAjaxSuccess: false,
        ajaxUrl: null,
        ajaxResponseText: null
    };

	$.fn.enable = function(options) {
        var opts = $.extend({}, $.fn.enable.defaults, options);

        function enable($this) {
            $this.removeAttr('disabled');
            $('#'+$this.attr('id')+'_select').removeAttr('disabled');
            if(opts.enableClass !== '') {
                 $this.addClass(opts.enableClass);
                 $('#'+$this.attr('id')+'_select').addClass(opts.enableClass);
            }
			if(opts.disableClass !== '') {
				$this.removeClass(opts.disableClass);
	            $('#'+$this.attr('id')+'_select').removeClass(opts.disableClass);
			}
        }

        return this.each(function() {
            var $this = $(this);

            enable($this);
         });
     };

    $.fn.enable.defaults = {
        enableClass: '',
        disableClass: ''
    };

    // readonly On/Off

    /**
	 * readonly 를 설정하거나 해제한다. status : true, false (boolean) options : 설정 해제 옵션
	 */
    $.fn.readonly = function(status, options) {
        var opts = $.extend({}, $.fn.readonly.defaults, options);

        function readonly($this, status) {
            $this.attr('readonly', status);
            $('#'+$this.attr('id')+'_select').attr('readonly', status);

            if(opts.readonlyClass !== '') {
            	if(status) {
                    $this.addClass(opts.readonlyClass);
                    $('#'+$this.attr('id')+'_select').addClass(opts.readonlyClass);
            	}
            	else {
                    $this.removeClass(opts.readonlyClass);
                    $('#'+$this.attr('id')+'_select').removeClass(opts.readonlyClass);
            	}
            }
        }

        function setReadonlyTimeout($this, status) {
            setTimeout(function() {
            	readonly($this, status);
            }, opts.readonltAfterSeconds * 1000);
        }

        function setAjaxReadonly($this, status) {
            $this.ajaxComplete(function(event, request, settings){
                var statusOk = !opts.enableOnAjaxSuccess || (opts.enableOnAjaxSuccess && request.status === 200),
                    urlOk = opts.ajaxUrl === null || settings.url === opts.ajaxUrl,
                    responseTextOk = opts.ajaxResponseText === null || request.responseText === opts.ajaxResponseText;
                if(statusOk && urlOk && responseTextOk) {
                	readonly($this, status);
                }
            });
        }

        return this.each(function() {
            var $this = $(this);

            readonly($this, status);

            if(opts.readonlyAfterSeconds > 0) {
                setEnableTimeout($this, status);
            }

            if(opts.readonlyAjaxComplete || opts.readonlyAjaxSuccess) {
                setAjaxEnable($this, status);
            }
         });
     };

    $.fn.readonly.defaults = {
        readonlyClass: '',
        readonlyAfterSeconds: 0,
        readonlyAjaxComplete: false,
        readonlyAjaxSuccess: false,
        ajaxUrl: null,
        ajaxResponseText: null
    };

})(jQuery);

// set value overriding
(function($) {
	  var originalVal = $.fn.val;
	  $.fn.val = function(value) {
		    if (typeof value == 'undefined') {
		        return originalVal.call(this);
		    }
		    else {
			    if (typeof $(this).attr('id') != 'undefined') {
			    	var s = $('#'+$(this).attr('id')+'_select.frmwrkAuto');
			    	if(s.length==1) {
			    		originalVal.call(s, value); 	// select 가 있는 경우
			    	}
			    }

		        return originalVal.call(this, value);
		    }
	  };

/*
 * var originalShow = $.fn.show;
 *
 * $.fn.show = function(a, b, c) { if($(this)['attr']!==undefined && typeof
 * $(this).attr('id') != 'undefined') { var s =
 * $('#'+$(this).attr('id')+'_select.frmwrkAuto'); if(s.length==1) {
 * originalShow.call(s, a, b, c); // select 가 있는 경우 } }
 *
 * originalShow.call(this, a, b, c); };
 *
 * var originalHide = $.fn.hide;
 *
 * $.fn.hide = function(a, b, c) { if($(this)['attr']!==undefined && typeof
 * $(this).attr('id') != 'undefined') { var s =
 * $('#'+$(this).attr('id')+'_select.frmwrkAuto'); if(s.length==1) {
 * originalHide.call(s, a, b, c); // select 가 있는 경우 } }
 *
 * originalHide.call(this, a, b, c); };
 */

})(jQuery);

/*
 * ! jQuery throttle / debounce - v1.1 - 3/7/2010
 * http://benalman.com/projects/jquery-throttle-debounce-plugin/
 *
 * Copyright (c) 2010 "Cowboy" Ben Alman Dual licensed under the MIT and GPL
 * licenses. http://benalman.com/about/license/
 */

// Script: jQuery throttle / debounce: Sometimes, less is more!
//
// *Version: 1.1, Last updated: 3/7/2010*
//
// Project Home - http://benalman.com/projects/jquery-throttle-debounce-plugin/
// GitHub - http://github.com/cowboy/jquery-throttle-debounce/
// Source -
// http://github.com/cowboy/jquery-throttle-debounce/raw/master/jquery.ba-throttle-debounce.js
// (Minified) -
// http://github.com/cowboy/jquery-throttle-debounce/raw/master/jquery.ba-throttle-debounce.min.js
// (0.7kb)
//
// About: License
//
// Copyright (c) 2010 "Cowboy" Ben Alman,
// Dual licensed under the MIT and GPL licenses.
// http://benalman.com/about/license/
//
// About: Examples
//
// These working examples, complete with fully commented code, illustrate a few
// ways in which this plugin can be used.
//
// Throttle -
// http://benalman.com/code/projects/jquery-throttle-debounce/examples/throttle/
// Debounce -
// http://benalman.com/code/projects/jquery-throttle-debounce/examples/debounce/
//
// About: Support and Testing
//
// Information about what version or versions of jQuery this plugin has been
// tested with, what browsers it has been tested in, and where the unit tests
// reside (so you can test it yourself).
//
// jQuery Versions - none, 1.3.2, 1.4.2
// Browsers Tested - Internet Explorer 6-8, Firefox 2-3.6, Safari 3-4, Chrome
// 4-5, Opera 9.6-10.1.
// Unit Tests - http://benalman.com/code/projects/jquery-throttle-debounce/unit/
//
// About: Release History
//
// 1.1 - (3/7/2010) Fixed a bug in <jQuery.throttle> where trailing callbacks
// executed later than they should. Reworked a fair amount of internal
// logic as well.
// 1.0 - (3/6/2010) Initial release as a stand-alone project. Migrated over
// from jquery-misc repo v0.4 to jquery-throttle repo v1.0, added the
// no_trailing throttle parameter and debounce functionality.
//
// Topic: Note for non-jQuery users
//
// jQuery isn't actually required for this plugin, because nothing internal
// uses any jQuery methods or properties. jQuery is just used as a namespace
// under which these methods can exist.
//
// Since jQuery isn't actually required for this plugin, if jQuery doesn't exist
// when this plugin is loaded, the method described below will be created in
// the `Cowboy` namespace. Usage will be exactly the same, but instead of
// $.method() or jQuery.method(), you'll need to use Cowboy.method().

(function(window,undefined){
  '$:nomunge'; // Used by YUI compressor.

  // Since jQuery really isn't required for this plugin, use `jQuery` as the
  // namespace only if it already exists, otherwise use the `Cowboy`
	// namespace,
  // creating it if necessary.
  var $ = window.jQuery || window.Cowboy || ( window.Cowboy = {} ),

    // Internal method reference.
    jq_throttle;

  // Method: jQuery.throttle
  //
  // Throttle execution of a function. Especially useful for rate limiting
  // execution of handlers on events like resize and scroll. If you want to
  // rate-limit execution of a function to a single time, see the
  // <jQuery.debounce> method.
  //
  // In this visualization, | is a throttled-function call and X is the actual
  // callback execution:
  //
  // > Throttled with `no_trailing` specified as false or unspecified:
  // > ||||||||||||||||||||||||| (pause) |||||||||||||||||||||||||
  // > X X X X X X X X X X X X
  // >
  // > Throttled with `no_trailing` specified as true:
  // > ||||||||||||||||||||||||| (pause) |||||||||||||||||||||||||
  // > X X X X X X X X X X
  //
  // Usage:
  //
  // > var throttled = jQuery.throttle( delay, [ no_trailing, ] callback );
  // >
  // > jQuery('selector').bind( 'someevent', throttled );
  // > jQuery('selector').unbind( 'someevent', throttled );
  //
  // This also works in jQuery 1.4+:
  //
  // > jQuery('selector').bind( 'someevent', jQuery.throttle( delay, [
	// no_trailing, ] callback ) );
  // > jQuery('selector').unbind( 'someevent', callback );
  //
  // Arguments:
  //
  // delay - (Number) A zero-or-greater delay in milliseconds. For event
  // callbacks, values around 100 or 250 (or even higher) are most useful.
  // no_trailing - (Boolean) Optional, defaults to false. If no_trailing is
  // true, callback will only execute every `delay` milliseconds while the
  // throttled-function is being called. If no_trailing is false or
  // unspecified, callback will be executed one final time after the last
  // throttled-function call. (After the throttled-function has not been
  // called for `delay` milliseconds, the internal counter is reset)
  // callback - (Function) A function to be executed after delay milliseconds.
  // The `this` context and all arguments are passed through, as-is, to
  // `callback` when the throttled-function is executed.
  //
  // Returns:
  //
  // (Function) A new, throttled, function.

  $.throttle = jq_throttle = function( delay, no_trailing, callback, debounce_mode ) {
    // After wrapper has stopped being called, this timeout ensures that
    // `callback` is executed at the proper times in `throttle` and `end`
    // debounce modes.
    var timeout_id,

      // Keep track of the last time `callback` was executed.
      last_exec = 0;

    // `no_trailing` defaults to falsy.
    if ( typeof no_trailing !== 'boolean' ) {
      debounce_mode = callback;
      callback = no_trailing;
      no_trailing = undefined;
    }

    // The `wrapper` function encapsulates all of the throttling / debouncing
    // functionality and when executed will limit the rate at which `callback`
    // is executed.
    function wrapper() {
      var that = this,
        elapsed = +new Date() - last_exec,
        args = arguments;

      // Execute `callback` and update the `last_exec` timestamp.
      function exec() {
        last_exec = +new Date();
        callback.apply( that, args );
      };

      // If `debounce_mode` is true (at_begin) this is used to clear the flag
      // to allow future `callback` executions.
      function clear() {
        timeout_id = undefined;
      };

      if ( debounce_mode && !timeout_id ) {
        // Since `wrapper` is being called for the first time and
        // `debounce_mode` is true (at_begin), execute `callback`.
        exec();
      }

      // Clear any existing timeout.
      timeout_id && clearTimeout( timeout_id );

      if ( debounce_mode === undefined && elapsed > delay ) {
        // In throttle mode, if `delay` time has been exceeded, execute
        // `callback`.
        exec();

      } else if ( no_trailing !== true ) {
        // In trailing throttle mode, since `delay` time has not been
        // exceeded, schedule `callback` to execute `delay` ms after most
        // recent execution.
        //
        // If `debounce_mode` is true (at_begin), schedule `clear` to execute
        // after `delay` ms.
        //
        // If `debounce_mode` is false (at end), schedule `callback` to
        // execute after `delay` ms.
        timeout_id = setTimeout( debounce_mode ? clear : exec, debounce_mode === undefined ? delay - elapsed : delay );
      }
    };

    // Set the guid of `wrapper` function to the same of original callback, so
    // it can be removed in jQuery 1.4+ .unbind or .die by using the original
    // callback as a reference.
    if ( $.guid ) {
      wrapper.guid = callback.guid = callback.guid || $.guid++;
    }

    // Return the wrapper function.
    return wrapper;
  };

  // Method: jQuery.debounce
  //
  // Debounce execution of a function. Debouncing, unlike throttling,
  // guarantees that a function is only executed a single time, either at the
  // very beginning of a series of calls, or at the very end. If you want to
  // simply rate-limit execution of a function, see the <jQuery.throttle>
  // method.
  //
  // In this visualization, | is a debounced-function call and X is the actual
  // callback execution:
  //
  // > Debounced with `at_begin` specified as false or unspecified:
  // > ||||||||||||||||||||||||| (pause) |||||||||||||||||||||||||
  // > X X
  // >
  // > Debounced with `at_begin` specified as true:
  // > ||||||||||||||||||||||||| (pause) |||||||||||||||||||||||||
  // > X X
  //
  // Usage:
  //
  // > var debounced = jQuery.debounce( delay, [ at_begin, ] callback );
  // >
  // > jQuery('selector').bind( 'someevent', debounced );
  // > jQuery('selector').unbind( 'someevent', debounced );
  //
  // This also works in jQuery 1.4+:
  //
  // > jQuery('selector').bind( 'someevent', jQuery.debounce( delay, [
	// at_begin, ] callback ) );
  // > jQuery('selector').unbind( 'someevent', callback );
  //
  // Arguments:
  //
  // delay - (Number) A zero-or-greater delay in milliseconds. For event
  // callbacks, values around 100 or 250 (or even higher) are most useful.
  // at_begin - (Boolean) Optional, defaults to false. If at_begin is false or
  // unspecified, callback will only be executed `delay` milliseconds after
  // the last debounced-function call. If at_begin is true, callback will be
  // executed only at the first debounced-function call. (After the
  // throttled-function has not been called for `delay` milliseconds, the
  // internal counter is reset)
  // callback - (Function) A function to be executed after delay milliseconds.
  // The `this` context and all arguments are passed through, as-is, to
  // `callback` when the debounced-function is executed.
  //
  // Returns:
  //
  // (Function) A new, debounced, function.

  $.debounce = function( delay, at_begin, callback ) {
    return callback === undefined
      ? jq_throttle( delay, at_begin, false )
      : jq_throttle( delay, callback, at_begin !== false );
  };

})(this);


(function($, window){
	// i'll just put them here to get evaluated on script load
	var htmlSpecialCharsRegEx = /[<>&\r\n"']/gm;
	var htmlSpecialCharsPlaceHolders = {
				'<': 'lt;',
				'>': 'gt;',
				'&': 'amp;',
				'\r': "#13;",
				'\n': "#10;",
				'"': 'quot;',
				"'": 'apos;' /* single quotes just to be safe */
	};

$.extend({
    //
    // $.fileDownload('/path/to/url/', options)
    // see directly below for possible 'options'
    fileDownload: function (fileUrl, options) {

        // provide some reasonable defaults to any unspecified options below
        var settings = $.extend({

            //
            // Requires jQuery UI: provide a message to display to the user when
			// the file download is being prepared before the browser's dialog
			// appears
            //
            preparingMessageHtml: null,

            //
            // Requires jQuery UI: provide a message to display to the user when
			// a file download fails
            //
            failMessageHtml: null,

            //
            // the stock android browser straight up doesn't support file
			// downloads initiated by a non GET:
			// http://code.google.com/p/android/issues/detail?id=1780
            // specify a message here to display if a user tries with an android
			// browser
            // if jQuery UI is installed this will be a dialog, otherwise it
			// will be an alert
            //
            androidPostUnsupportedMessageHtml: "Unfortunately your Android browser doesn't support this type of file download. Please try again with a different browser.",

            //
            // Requires jQuery UI: options to pass into jQuery UI Dialog
            //
            dialogOptions: { modal: true },

            //
            // a function to call while the dowload is being prepared before the
			// browser's dialog appears
            // Args:
            // url - the original url attempted
            //
            prepareCallback: function (url) { },

            //
            // a function to call after a file download dialog/ribbon has
			// appeared
            // Args:
            // url - the original url attempted
            //
            successCallback: function (url) { },

            //
            // a function to call after a file download dialog/ribbon has
			// appeared
            // Args:
            // responseHtml - the html that came back in response to the file
			// download. this won't necessarily come back depending on the
			// browser.
            // in less than IE9 a cross domain error occurs because 500+ errors
			// cause a cross domain issue due to IE subbing out the
            // server's error message with a "helpful" IE built in message
            // url - the original url attempted
            //
            failCallback: function (responseHtml, url) { },

            //
            // the HTTP method to use. Defaults to "GET".
            //
            httpMethod: "GET",

            //
            // if specified will perform a "httpMethod" request to the specified
			// 'fileUrl' using the specified data.
            // data must be an object (which will be $.param serialized) or
			// already a key=value param string
            //
            data: null,

            //
            // a period in milliseconds to poll to determine if a successful
			// file download has occured or not
            //
            checkInterval: 100,

            //
            // the cookie name to indicate if a file download has occured
            //
            cookieName: "fileDownload",

            //
            // the cookie value for the above name to indicate that a file
			// download has occured
            //
            cookieValue: "true",

            //
            // the cookie path for above name value pair
            //
            cookiePath: "/",

            //
            // the title for the popup second window as a download is processing
			// in the case of a mobile browser
            //
            popupWindowTitle: "Initiating file download...",

            //
            // Functionality to encode HTML entities for a POST, need this if
			// data is an object with properties whose values contains strings
			// with quotation marks.
            // HTML entity encoding is done by replacing all &,<,>,',",\r,\n
			// characters.
            // Note that some browsers will POST the string htmlentity-encoded
			// whilst others will decode it before POSTing.
            // It is recommended that on the server, htmlentity decoding is done
			// irrespective.
            //
            encodeHTMLEntities: true

        }, options);

        var deferred = new $.Deferred();

        // Setup mobile browser detection: Partial credit:
		// http://detectmobilebrowser.com/
        var userAgent = (navigator.userAgent || navigator.vendor || window.opera).toLowerCase();

        var isIos;                  // has full support of features in iOS 4.0+,
									// uses a new window to accomplish this.
        var isAndroid;              // has full support of GET features in 4.0+
									// by using a new window. Non-GET is
									// completely unsupported by the browser.
									// See above for specifying a message.
        var isOtherMobileBrowser;   // there is no way to reliably guess here so
									// all other mobile devices will GET and
									// POST to the current window.

        if (/ip(ad|hone|od)/.test(userAgent)) {

            isIos = true;

        } else if (userAgent.indexOf('android') !== -1) {

            isAndroid = true;

        } else {

            isOtherMobileBrowser = /avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|playbook|silk|iemobile|iris|kindle|lge |maemo|midp|mmp|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino/i.test(userAgent) || /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|e\-|e\/|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\-|2|g)|yas\-|your|zeto|zte\-/i.test(userAgent.substr(0, 4));

        }

        var httpMethodUpper = settings.httpMethod.toUpperCase();

        if (isAndroid && httpMethodUpper !== "GET") {
            // the stock android browser straight up doesn't support file
			// downloads initiated by non GET requests:
			// http://code.google.com/p/android/issues/detail?id=1780

            if ($().dialog) {
                $("<div>").html(settings.androidPostUnsupportedMessageHtml).dialog(settings.dialogOptions);
            } else {
                alert(settings.androidPostUnsupportedMessageHtml);
            }

            return deferred.reject();
        }

        var $preparingDialog = null;

        var internalCallbacks = {

            onPrepare: function (url) {

                // wire up a jquery dialog to display the preparing message if
				// specified
                if (settings.preparingMessageHtml) {

                    $preparingDialog = $("<div>").html(settings.preparingMessageHtml).dialog(settings.dialogOptions);

                } else if (settings.prepareCallback) {

                    settings.prepareCallback(url);

                }

            },

            onSuccess: function (url) {

                // remove the perparing message if it was specified
                if ($preparingDialog) {
                    $preparingDialog.dialog('close');
                };

                settings.successCallback(url);

                deferred.resolve(url);
            },

            onFail: function (responseHtml, url) {

                // remove the perparing message if it was specified
                if ($preparingDialog) {
                    $preparingDialog.dialog('close');
                };

                // wire up a jquery dialog to display the fail message if
				// specified
                if (settings.failMessageHtml) {
                    $("<div>").html(settings.failMessageHtml).dialog(settings.dialogOptions);
                }

                settings.failCallback(responseHtml, url);

                deferred.reject(responseHtml, url);
            }
        };

        internalCallbacks.onPrepare(fileUrl);

        // make settings.data a param string if it exists and isn't already
        if (settings.data !== null && typeof settings.data !== "string") {
            settings.data = $.param(settings.data);
        }


        var $iframe,
            downloadWindow,
            formDoc,
            $form;

        if (httpMethodUpper === "GET") {

            if (settings.data !== null) {
                // need to merge any fileUrl params with the data object

                var qsStart = fileUrl.indexOf('?');

                if (qsStart !== -1) {
                    // we have a querystring in the url

                    if (fileUrl.substring(fileUrl.length - 1) !== "&") {
                        fileUrl = fileUrl + "&";
                    }
                } else {

                    fileUrl = fileUrl + "?";
                }

                fileUrl = fileUrl + settings.data;
            }

            if (isIos || isAndroid) {

                downloadWindow = window.open(fileUrl);
                downloadWindow.document.title = settings.popupWindowTitle;
                window.focus();

            } else if (isOtherMobileBrowser) {

                window.location(fileUrl);

            } else {

                // create a temporary iframe that is used to request the fileUrl
				// as a GET request
                $iframe = $("<iframe>")
                    .hide()
                    .prop("src", fileUrl)
                    .appendTo("body");
            }

        } else {

            var formInnerHtml = "";

            if (settings.data !== null) {

                $.each(settings.data.replace(/\+/g, ' ').split("&"), function () {

                    var kvp = this.split("=");

                    var key = settings.encodeHTMLEntities ? htmlSpecialCharsEntityEncode(decodeURIComponent(kvp[0])) : decodeURIComponent(kvp[0]);
                    if (key) {
                        var value = settings.encodeHTMLEntities ? htmlSpecialCharsEntityEncode(decodeURIComponent(kvp[1])) : decodeURIComponent(kvp[1]);
                    formInnerHtml += '<input type="hidden" name="' + key + '" value="' + value + '" />';
                    }
                });
            }

            if (isOtherMobileBrowser) {

                $form = $("<form>").appendTo("body");
                $form.hide()
                    .prop('method', settings.httpMethod)
                    .prop('action', fileUrl)
                    .html(formInnerHtml);

            } else {

                if (isIos) {

                    downloadWindow = window.open("about:blank");
                    downloadWindow.document.title = settings.popupWindowTitle;
                    formDoc = downloadWindow.document;
                    window.focus();

                } else {

                    $iframe = $("<iframe style='display: none' src='about:blank'></iframe>").appendTo("body");
                    formDoc = getiframeDocument($iframe);
                }

                formDoc.write("<html><head></head><body><form method='" + settings.httpMethod + "' action='" + fileUrl + "'>" + formInnerHtml + "</form>" + settings.popupWindowTitle + "</body></html>");
                $form = $(formDoc).find('form');
            }

            $form.submit();
        }


        // check if the file download has completed every checkInterval ms
        setTimeout(checkFileDownloadComplete, settings.checkInterval);


        function checkFileDownloadComplete() {

            // has the cookie been written due to a file download occuring?
            if (document.cookie.indexOf(settings.cookieName + "=" + settings.cookieValue) != -1) {

                // execute specified callback
                internalCallbacks.onSuccess(fileUrl);

                // remove the cookie and iframe
                document.cookie = settings.cookieName + "=; expires=" + new Date(1000).toUTCString() + "; path=" + settings.cookiePath;

                cleanUp(false);

                return;
            }

            // has an error occured?
            // if neither containers exist below then the file download is
			// occuring on the current window
            if (downloadWindow || $iframe) {

                // has an error occured?
                try {

                    var formDoc = downloadWindow ? downloadWindow.document : getiframeDocument($iframe);

                    if (formDoc && formDoc.body != null && formDoc.body.innerHTML.length) {

                        var isFailure = true;

                        if ($form && $form.length) {
                            var $contents = $(formDoc.body).contents().first();

                            try {
                                if ($contents.length && $contents[0] === $form[0]) {
                                    isFailure = false;
                                }
                            } catch (e) {
                                if (e && e.number == -2146828218) {
                                    // IE 8-10 throw a permission denied after
									// the form reloads on the "$contents[0] ===
									// $form[0]" comparison
                                    isFailure = true;
                                } else {
                                    throw e;
                                }
                            }
                        }

                        if (isFailure) {
                            // IE 8-10 don't always have the full content
							// available right away, they need a litle bit to
							// finish
                            setTimeout(function () {
                                internalCallbacks.onFail(formDoc.body.innerHTML, fileUrl);
                                cleanUp(true);
                            }, 100);

                            return;
                        }
                    }
                }
                catch (err) {

                    // 500 error less than IE9
                    internalCallbacks.onFail('', fileUrl);

                    cleanUp(true);

                    return;
                }
            }


            // keep checking...
            setTimeout(checkFileDownloadComplete, settings.checkInterval);
        }

        // gets an iframes document in a cross browser compatible manner
        function getiframeDocument($iframe) {
            var iframeDoc = $iframe[0].contentWindow || $iframe[0].contentDocument;
            if (iframeDoc.document) {
                iframeDoc = iframeDoc.document;
            }
            return iframeDoc;
        }

        function cleanUp(isFailure) {

            setTimeout(function() {

                if (downloadWindow) {

                    if (isAndroid) {
                        downloadWindow.close();
                    }

                    if (isIos) {
                        if (downloadWindow.focus) {
                            downloadWindow.focus(); // ios safari bug doesn't
													// allow a window to be
													// closed unless it is
													// focused
                            if (isFailure) {
                                downloadWindow.close();
                            }
                        }
                    }
                }

                // iframe cleanup appears to randomly cause the download to fail
                // not doing it seems better than failure...
                // if ($iframe) {
                // $iframe.remove();
                // }

            }, 0);
        }


        function htmlSpecialCharsEntityEncode(str) {
            return str.replace(htmlSpecialCharsRegEx, function(match) {
                return '&' + htmlSpecialCharsPlaceHolders[match];
        	});
        }

        return deferred.promise();
    }
});

})(jQuery, this);
