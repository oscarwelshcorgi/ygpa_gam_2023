function removeWindow(rem_win) {
  rem_win.remove();
}
window.removeWindow = removeWindow;
var buttonIcons = [{label:"\ucd5c\ucd08\uc2e0\uccad", icon:"ui-icon-check"}, {label:"\uc5f0\uc7a5\uc2e0\uccad", icon:"ui-icon-clock"}, {label:"\uc2e0\uccad\uc0ad\uc81c", icon:"ui-icon-minusthick"}, {label:"\uacb0\uc7ac\uc694\uccad", icon:"ui-icon-document"}, {label:"\uc790\uc0b0\ub4f1\ub85d", icon:"ui-icon-document"}, {label:"\uc9c0\ub3c4\ubcf4\uae30", icon:"ui-icon-flag"}, {label:"\uc704\uce58\ub4f1\ub85d", icon:"ui-icon-pin-s"}, {label:"\uc790\uc0b0\ucd94\uac00", icon:"ui-icon-plusthick"}, {label:"\ucd94\uac00", 
icon:"ui-icon-plusthick"}, {label:"\uc0ad\uc81c", icon:"ui-icon-trash"}, {label:"\uc800\uc7a5", icon:"ui-icon-disk"}, {label:"\ucde8\uc18c", icon:"ui-icon-arrowreturnthick-1-e"}, {label:"\uc801\uc6a9", icon:"ui-icon-check"}, {label:"\uc5c5\ub85c\ub4dc", icon:"ui-icon-arrowthickstop-1-n"}, {label:"\ub2e4\uc6b4\ub85c\ub4dc", icon:"ui-icon-arrowthickstop-1-s"}, {label:"\uc0ac\uc6a9\uc2b9\ub099", icon:"ui-icon-clipboard"}, {label:"\uc2b9\ub099\ucde8\uc18c", icon:"ui-icon-cancel"}, {label:"\ub9f5\uc870\ud68c", 
icon:"ui-icon-extlink"}, {label:"\uc5d1\uc140", icon:"ui-icon-excel-down"}, {label:"\uc778\uc1c4", icon:"ui-icon-print"}, {label:"\uacfc\ud0dc\ub8cc", icon:"ui-icon-alert"}];
var DeleteFeature = new OpenLayers.Class(OpenLayers.Control, {initialize:function(layer, options) {
  OpenLayers.Control.prototype.initialize.apply(this, [options]);
  this.layer = layer;
  this.handler = new OpenLayers.Handler.Feature(this, layer, {click:this.clickFeature});
}, clickFeature:function(feature) {
  if (feature.fid == undefined) {
    this.layer.destroyFeatures([feature]);
  } else {
    feature.state = OpenLayers.State.DELETE;
    this.layer.events.triggerEvent("afterfeaturemodified", {feature:feature});
    feature.renderIntent = "select";
    this.layer.drawFeature(feature);
  }
}, setMap:function(map) {
  this.handler.setMap(map);
  OpenLayers.Control.prototype.setMap.apply(this, arguments);
}, CLASS_NAME:"OpenLayers.Control.DeleteFeature"});
OpenLayers.Projection.defaults = {"EPSG:5181":{units:"m"}, "EPSG:5186":{units:"m"}};
OpenLayers.DOTS_PER_INCH = 96;
OpenLayers.Layer.TileCacheCustom = OpenLayers.Class(OpenLayers.Layer.TileCache, {initialize:function(name, url, layername, options) {
  this.layername = layername;
  OpenLayers.Layer.Grid.prototype.initialize.apply(this, [name, url, {}, options]);
  this.extension = this.format.split("/")[1].toLowerCase();
}, getURL:function(bounds) {
  var res = this.map.getResolution();
  var bbox = this.maxExtent;
  var size = this.tileSize;
  var tileX = bounds.bottom;
  var tileY = bounds.left;
  var tileZ = this.serverResolutions != null ? OpenLayers.Util.indexOf(this.serverResolutions, res) : this.map.getZoom();
  function zeroPad(number, length) {
    number = String(number);
    var zeros = [];
    for (var i = 0;i < length;++i) {
      zeros.push("0");
    }
    return zeros.join("").substring(0, length - number.length) + number;
  }
  var components = [this.layername, zeroPad(tileZ, 2), zeroPad(parseInt(tileX / 1E6), 3), zeroPad(parseInt(tileX / 1E3) % 1E3, 3), zeroPad(parseInt(tileX) % 1E3, 3), zeroPad(parseInt(tileY / 1E6), 3), zeroPad(parseInt(tileY / 1E3) % 1E3, 3), zeroPad(parseInt(tileY) % 1E3, 3) + "." + this.extension];
  var path = components.join("/");
  if (this.version) {
    path += "?v=" + this.version;
  }
  var url = this.url;
  if (url instanceof Array) {
    url = this.selectUrl(path, url);
  }
  url = url.charAt(url.length - 1) == "/" ? url : url + "/";
  return url + path;
}, CLASS_NAME:"OpenLayers.Layer.TileCacheCustom"});
OpenLayers.Control.indexMapCustom = OpenLayers.Class(OpenLayers.Control.ZoomBox, {baseMap:null, draw:function() {
  this.handler = new OpenLayers.Handler.BoxCustom(this, {done:this.zoomBox}, {indexMap:true});
}, initialize:function(baseMap, options) {
  this.baseMap = baseMap;
  OpenLayers.Control.prototype.initialize.apply(this, [options]);
}, zoomBox:function(position) {
  if (position instanceof OpenLayers.Bounds) {
    if (!this.out) {
      var minXY = this.map.getLonLatFromPixel(new OpenLayers.Pixel(position.left, position.bottom));
      var maxXY = this.map.getLonLatFromPixel(new OpenLayers.Pixel(position.right, position.top));
      var bounds = new OpenLayers.Bounds(minXY.lon, minXY.lat, maxXY.lon, maxXY.lat);
    } else {
      var pixWidth = Math.abs(position.right - position.left);
      var pixHeight = Math.abs(position.top - position.bottom);
      var zoomFactor = Math.min(this.map.size.h / pixHeight, this.map.size.w / pixWidth);
      var extent = this.map.getExtent();
      var center = this.map.getLonLatFromPixel(position.getCenterPixel());
      var xmin = center.lon - extent.getWidth() / 2 * zoomFactor;
      var xmax = center.lon + extent.getWidth() / 2 * zoomFactor;
      var ymin = center.lat - extent.getHeight() / 2 * zoomFactor;
      var ymax = center.lat + extent.getHeight() / 2 * zoomFactor;
      var bounds = new OpenLayers.Bounds(xmin, ymin, xmax, ymax);
    }
    this.baseMap.zoomToExtent(bounds, true);
  } else {
    this.baseMap.setCenter(this.map.getLonLatFromPixel(position), this.baseMap.numZoomLevels - 1);
  }
}, CLASS_NAME:"OpenLayers.Control.indexMapCustom"});
OpenLayers.Handler.BoxCustom = OpenLayers.Class(OpenLayers.Handler.Box, {indexMap:false, startBox:function(xy) {
  if (this.indexMap && this.zoomBox) {
    this.removeBox();
  }
  this.zoomBox = OpenLayers.Util.createDiv("zoomBox", this.dragHandler.start);
  this.zoomBox.className = this.boxDivClassName;
  this.zoomBox.style.zIndex = this.map.Z_INDEX_BASE["Popup"] - 1;
  this.map.viewPortDiv.appendChild(this.zoomBox);
  OpenLayers.Element.addClass(this.map.viewPortDiv, "olDrawBox");
}, applyBox:function(bounds) {
  if (this.indexMap && this.zoomBox) {
    this.removeBox();
  }
  this.dragHandler.start = this.map.getPixelFromLonLat(new OpenLayers.LonLat(bounds.left, bounds.top));
  var endPixel = this.map.getPixelFromLonLat(new OpenLayers.LonLat(bounds.right, bounds.bottom));
  var width = endPixel.x - this.dragHandler.start.x;
  var height = endPixel.y - this.dragHandler.start.y;
  this.zoomBox = OpenLayers.Util.createDiv("zoomBox", this.dragHandler.start);
  this.zoomBox.className = this.boxDivClassName;
  this.zoomBox.style.zIndex = this.map.Z_INDEX_BASE["Popup"] - 1;
  this.map.viewPortDiv.appendChild(this.zoomBox);
  this.zoomBox.style.width = width + "px";
  this.zoomBox.style.height = height + "px";
}, endBox:function(end) {
  var result;
  if (Math.abs(this.dragHandler.start.x - end.x) > 5 || Math.abs(this.dragHandler.start.y - end.y) > 5) {
    var start = this.dragHandler.start;
    var top = Math.min(start.y, end.y);
    var bottom = Math.max(start.y, end.y);
    var left = Math.min(start.x, end.x);
    var right = Math.max(start.x, end.x);
    result = new OpenLayers.Bounds(left, bottom, right, top);
  } else {
    result = this.dragHandler.start.clone();
  }
  if (!this.indexMap) {
    this.removeBox();
  }
  this.callback("done", [result]);
}, CLASS_NAME:"OpenLayers.Handler.BoxCustom"});
OpenLayers.Handler.PolygonMeasureCustom = OpenLayers.Class(OpenLayers.Handler.Polygon, {popup:null, measureArea:function() {
  var geometry = this.geometryClone();
  var subLength = geometry.getGeodesicArea(EMD.map.projection);
  var tempLength = subLength;
  tempLength *= Math.pow(OpenLayers.INCHES_PER_UNIT["m"] / OpenLayers.INCHES_PER_UNIT["km"], 2);
  if (tempLength > 1) {
    subLength = tempLength.toFixed(2) + "km" + "<sup>2</" + "sup>";
  } else {
    subLength = subLength.toFixed(2) + "m" + "<sup>2</" + "sup>";
  }
  return subLength;
}, mousedown:function(evt) {
  if (this.lastDown && this.lastDown.equals(evt.xy)) {
    return false;
  }
  if (this.lastDown == null) {
    if (!this.multiLine) {
      if (this.persist) {
        this.destroyFeature();
      }
      this.removePopup();
    }
    this.createFeature(evt.xy);
  } else {
    if (this.lastUp == null || !this.lastUp.equals(evt.xy)) {
      this.addPoint(evt.xy);
    }
  }
  this.lastDown = evt.xy;
  this.drawing = true;
  if (this.freehandMode(evt)) {
    this.removePoint();
    this.finalize();
  } else {
    if (this.lastUp == null) {
      this.addPoint(evt.xy);
    }
    this.lastUp = evt.xy;
  }
  var lonlat = this.map.getLonLatFromPixel(evt.xy);
  var pointFeature = new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Point(lonlat.lon, lonlat.lat));
  this.layer.addFeatures(pointFeature);
  var popup;
  if (!this.count) {
    contentHtml = "<div class='olControlMeasurePopup'>\uc2dc\uc791</div>";
    popup = new PopupCustom("measurePopup", lonlat, null, contentHtml, this.layer.map, new OpenLayers.Pixel(5, 5));
    contentHtml = "<div class='olControlMeasurePopup'>" + this.measureArea() + "</div>";
    this.popup = new PopupCustom("measurePopup", lonlat, null, contentHtml, this.layer.map, new OpenLayers.Pixel(5, 5));
    this.map.addPopup(this.popup);
    this.popup.updateSize();
    this.popup.type = "measure";
    this.count = 1;
  } else {
    if (this.count > 1) {
      contentHtml = "<div class='olControlMeasurePopup'>" + this.measureArea() + "</div>";
      popup = new PopupCustom("measurePopup", lonlat, null, contentHtml, this.layer.map, new OpenLayers.Pixel(5, 5));
    }
    this.count += 1;
  }
  if (popup) {
    this.map.addPopup(popup);
    popup.type = "measure";
    popup.updateSize();
  }
  if (evt.button == "2") {
    this.rightclick(evt);
    return true;
  }
  return false;
}, mousemove:function(evt) {
  if (this.drawing) {
    if (this.mouseDown && this.freehandMode(evt)) {
      this.addPoint(evt.xy);
    } else {
      this.modifyFeature(evt.xy);
      if (this.popup) {
        contentHtml = "<div class='olControlMeasurePopup'>" + this.measureArea() + "</div>";
        this.popup.setContentHTML(contentHtml);
        this.popup.updateSize();
        this.popup.moveTo(evt.xy);
      }
    }
  }
  return true;
}, rightclick:function(evt) {
  this.dblclick(evt);
  return false;
}, dblclick:function(evt) {
  if (this.count < 3) {
    alert("\uba74\uc801\uc740 3\uac1c \uc774\uc0c1\uc758 \uc9c0\uc810\uc744 \uc120\ud0dd\ud574\uc57c \ud569\ub2c8\ub2e4.");
    return false;
  }
  this.count = 0;
  if (!this.freehandMode(evt)) {
    var index = this.line.geometry.components.length - 1;
    this.line.geometry.removeComponent(this.line.geometry.components[index]);
    this.removePoint();
    this.finalize();
  }
  if (this.popup) {
    this.map.removePopup(this.popup);
    this.popup = null;
  }
  return false;
}, deactivate:function() {
  if (!OpenLayers.Handler.prototype.deactivate.apply(this, arguments)) {
    return false;
  }
  if (this.drawing) {
    this.cancel();
  }
  if (!this.persistControl) {
    this.layer.destroy(false);
    this.removePopup();
  }
  this.layer.prevLayer = true;
  this.layer = null;
  return true;
}, removePopup:function() {
  var len = this.map.popups.length;
  for (var i = len - 1;i >= 0;i--) {
    if (this.map.popups[i].type == "measure") {
      this.map.removePopup(this.map.popups[i]);
    }
  }
}, CLASS_NAME:"OpenLayers.Handler.PolygonMeasureCustom"});
OpenLayers.Handler.PathMeasureCustom = OpenLayers.Class(OpenLayers.Handler.Path, {popup:null, measureDistance:function() {
  var geometry = this.geometryClone();
  var subLength = geometry.getGeodesicLength(EMD.map.projection);
  var tempLength = subLength;
  tempLength *= OpenLayers.INCHES_PER_UNIT["m"] / OpenLayers.INCHES_PER_UNIT["km"];
  if (tempLength > 1) {
    subLength = tempLength.toFixed(2) + "km";
  } else {
    subLength = subLength.toFixed(2) + "m";
  }
  return subLength;
}, mousedown:function(evt) {
  if (this.lastDown && this.lastDown.equals(evt.xy)) {
    return false;
  }
  if (this.lastDown == null) {
    if (!this.multiLine) {
      if (this.persist) {
        this.destroyFeature();
      }
      this.removePopup();
    }
    this.createFeature(evt.xy);
  } else {
    if (this.lastUp == null || !this.lastUp.equals(evt.xy)) {
      this.addPoint(evt.xy);
    }
  }
  this.lastDown = evt.xy;
  this.drawing = true;
  if (this.freehandMode(evt)) {
    this.removePoint();
    this.finalize();
  } else {
    if (this.lastUp == null) {
      this.addPoint(evt.xy);
    }
    this.lastUp = evt.xy;
  }
  var lonlat = this.map.getLonLatFromPixel(evt.xy);
  var pointFeature = new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Point(lonlat.lon, lonlat.lat));
  this.layer.addFeatures(pointFeature);
  var popup;
  if (!this.count) {
    var contentHtml = "<div class='olControlMeasurePopup'>\uc2dc\uc791</div>";
    popup = new PopupCustom("measurePopup", lonlat, null, contentHtml, this.layer.map, new OpenLayers.Pixel(5, 5));
    contentHtml = "<div class='olControlMeasurePopup'>" + this.measureDistance() + "</div>";
    this.popup = new PopupCustom("measurePopup", lonlat, null, contentHtml, this.layer.map, new OpenLayers.Pixel(5, 5));
    this.map.addPopup(this.popup);
    this.popup.updateSize();
    this.popup.type = "measure";
    this.count = 1;
  } else {
    contentHtml = "<div class='olControlMeasurePopup'>" + this.measureDistance() + "</div>";
    popup = new PopupCustom("measurePopup", lonlat, null, contentHtml, this.layer.map, new OpenLayers.Pixel(5, 5));
    this.count += 1;
  }
  if (popup) {
    this.map.addPopup(popup);
    popup.type = "measure";
    popup.updateSize();
  }
  if (evt.button == "2") {
    this.rightclick(evt);
    return true;
  }
  return false;
}, mousemove:function(evt) {
  if (this.drawing) {
    if (this.mouseDown && this.freehandMode(evt)) {
      this.addPoint(evt.xy);
    } else {
      this.modifyFeature(evt.xy);
      if (this.popup) {
        contentHtml = "<div class='olControlMeasurePopup'>" + this.measureDistance() + "</div>";
        this.popup.setContentHTML(contentHtml);
        this.popup.updateSize();
        this.popup.moveTo(evt.xy);
      }
    }
  }
  return true;
}, rightclick:function(evt) {
  this.dblclick(evt);
  return false;
}, dblclick:function(evt) {
  this.count = 0;
  if (!this.freehandMode(evt)) {
    var index = this.line.geometry.components.length - 1;
    this.line.geometry.removeComponent(this.line.geometry.components[index]);
    this.removePoint();
    this.finalize();
  }
  if (this.popup) {
    this.map.removePopup(this.popup);
    this.popup = null;
  }
  return false;
}, deactivate:function() {
  if (!OpenLayers.Handler.prototype.deactivate.apply(this, arguments)) {
    return false;
  }
  if (this.drawing) {
    this.cancel();
  }
  if (!this.persistControl) {
    this.layer.destroy(false);
    this.removePopup();
  }
  this.layer.prevLayer = true;
  this.layer = null;
  return true;
}, removePopup:function() {
  var len = this.map.popups.length;
  for (var i = len - 1;i >= 0;i--) {
    if (this.map.popups[i].type == "measure") {
      this.map.removePopup(this.map.popups[i]);
    }
  }
}, CLASS_NAME:"OpenLayers.Handler.PathMeasureCustom"});
PopupCustom = OpenLayers.Class(OpenLayers.Popup, {offsetPixel:null, initialize:function(id, lonlat, contentSize, contentHTML, map, offsetPixel) {
  if (offsetPixel) {
    this.offsetPixel = offsetPixel;
  }
  if (id == null) {
    id = OpenLayers.Util.createUniqueID(this.CLASS_NAME + "_");
  }
  this.id = id;
  this.lonlat = lonlat;
  this.contentSize = contentSize != null ? contentSize : new OpenLayers.Size(OpenLayers.Popup.WIDTH, OpenLayers.Popup.HEIGHT);
  if (contentHTML != null) {
    this.contentHTML = contentHTML;
  }
  this.backgroundColor = OpenLayers.Popup.COLOR;
  this.opacity = OpenLayers.Popup.OPACITY;
  this.border = OpenLayers.Popup.BORDER;
  this.div = OpenLayers.Util.createDiv(this.id, null, null, null, null, null, "hidden");
  this.div.className = this.displayClass;
  var groupDivId = this.id + "_GroupDiv";
  this.groupDiv = OpenLayers.Util.createDiv(groupDivId, null, null, null, "relative", null, "hidden");
  var id = this.div.id + "_contentDiv";
  this.contentDiv = OpenLayers.Util.createDiv(id, null, this.contentSize.clone(), null, "relative");
  this.contentDiv.className = this.contentDisplayClass;
  this.groupDiv.appendChild(this.contentDiv);
  this.div.appendChild(this.groupDiv);
  this.registerEvents();
}, getLonLat:function() {
  return this.lonlat;
}, moveTo:function(px) {
  if (px != null && this.div != null) {
    if (this.offsetPixel) {
      px = px.add(this.offsetPixel.x, this.offsetPixel.y);
    }
    this.div.style.left = px.x + "px";
    this.div.style.top = px.y + "px";
  }
}, CLASS_NAME:"PopupCustom"});
OpenLayers.Control.ClickFeature = OpenLayers.Class(OpenLayers.Control, {initialize:function(options) {
  OpenLayers.Control.prototype.initialize.apply(this, arguments);
}, activate:function() {
  if (OpenLayers.Control.prototype.activate.apply(this, arguments)) {
    this.map.events.register("mousemove", this, this.redraw);
    this.redraw();
    return true;
  } else {
    return false;
  }
}, deactivate:function() {
  if (OpenLayers.Control.prototype.deactivate.apply(this, arguments)) {
    this.map.events.unregister("mousemove", this, this.redraw);
    this.element.innerHTML = "";
    return true;
  } else {
    return false;
  }
}, CLASS_NAME:"OpenLayers.Control.ClickFeature"});
OpenLayers.Control.Click = OpenLayers.Class(OpenLayers.Control, {defaultHandlerOptions:{"single":true, "double":false, "pixelTolerance":0, "stopSingle":false, "stopDouble":false}, initialize:function(options) {
  this.handlerOptions = OpenLayers.Util.extend({}, this.defaultHandlerOptions);
  OpenLayers.Control.prototype.initialize.apply(this, arguments);
  this.handler = new OpenLayers.Handler.Click(this, {"click":this.onClick, "dblclick":this.onDblclick}, this.handlerOptions);
}, onClick:function(evt) {
  var output = document.getElementById(this.key + "Output");
  var msg = "click " + evt.xy;
  output.value = output.value + msg + "\r\n";
}, onDblclick:function(evt) {
  var output = document.getElementById(this.key + "Output");
  var msg = "dblclick " + evt.xy;
  output.value = output.value + msg + "\n";
}});
OpenLayers.Control.CustomGetFeature = OpenLayers.Class(OpenLayers.Control.GetFeature, {polygon:false, regularPolygon:false, all:false, map:null, initialize:function(options) {
  OpenLayers.Control.GetFeature.prototype.initialize.apply(this, [options]);
  if (this.polygon) {
    this.handlers.polygon = new OpenLayers.Handler.Polygon(this, {done:this.selectPolygon});
  }
  if (this.regularPolygon) {
    this.handlers.regularPolygon = new OpenLayers.Handler.RegularPolygon(this, {done:this.selectRegularPolygon}, {sides:50, persist:false});
  }
  if (this.all) {
    var bounds = this.map.getExtent();
    this.setModifiers(this.map.events);
    this.request(bounds);
  }
}, selectRegularPolygon:function(evt) {
  var bounds = evt.getBounds();
  this.setModifiers(this.handlers.regularPolygon.evt);
  this.request(bounds);
  OpenLayers.Util.deletePopup();
}, selectPolygon:function(evt) {
  var bounds = evt.getBounds();
  this.setModifiers(this.handlers.polygon.evt);
  this.request(bounds);
}, CLASS_NAME:"OpenLayers.Control.CustomGetFeature"});
var EMD = function($, window, document, undefined) {
  var browser = $.browser;
  if (!browser) {
    function uaMatch(ua) {
      ua = ua.toLowerCase();
      var match = /(chrome)[ \/]([\w.]+)/.exec(ua) || (/(webkit)[ \/]([\w.]+)/.exec(ua) || (/(opera)(?:.*version|)[ \/]([\w.]+)/.exec(ua) || (/(msie) ([\w.]+)/.exec(ua) || (ua.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec(ua) || []))));
      return{browser:match[1] || "", version:match[2] || "0"};
    }
    var matched = uaMatch(navigator.userAgent);
    browser = {};
    if (matched.browser) {
      browser[matched.browser] = true;
      browser.version = matched.version;
    }
    if (browser.chrome) {
      browser.webkit = true;
    } else {
      if (browser.webkit) {
        browser.safari = true;
      }
    }
  }
  return{go:function(ctr, mapBaseUrl, gisEngineUrl, serverUrl, frmwrkMenu) {
    if (ctr.length == 0 || ctr.substring(ctr.length - 1) == "/") {
      ctr = ctr.substring(0, ctr.length - 1);
    }
    EMD.context_root = ctr;
    EMD.gis_engine_url = gisEngineUrl;
    EMD.serverUrl = serverUrl;
    OpenLayers.ImgPath = EMD.context_root + "/images/egovframework/ygpa/gam/maps/";
    OpenLayers.theme = EMD.context_root + "/js/theme/ygpa/";
    Proj4js.libPath = EMD.context_root + "/js/Proj4js/";
    EMD.map_base_url = mapBaseUrl;
    switch(serverUrl) {
      case "http://lfitsvr.iptime.org":
        EMD.vworldKey = "B60CBFE2-E105-39B4-B7E7-C737F1512A16";
        break;
      case "http://192.168.0.40":
        EMD.vworldKey = "474AD190-F9E9-31C6-BE7C-15EFD66E5DAD";
        break;
      case "http://localhost":
        EMD.vworldKey = "5E7D605D-BD5A-3BEE-8C20-62172A86B4AA";
        break;
      default:
        EMD.serverUrl = "http://localhost";
        EMD.vworldKey = "5E7D605D-BD5A-3BEE-8C20-62172A86B4AA";
        break;
    }
    EMD._frmwrkMenu = frmwrkMenu;
    OpenLayers.ProxyHost = EMD.context_root + "/proxy.jsp?url=";
    for (var i in EMD.init) {
      EMD.init[i]();
    }
  }, _frmwrkMenu:null, context_root:"", wikiUrl:"", map_base_url:"", gis_engine_url:"", serverUrl:"", vworldKey:"", window_seq:0, legendWin:null, loadAtStartWindow:[], win_x:80, win_y:40, drag_handle:null, dragging:false, drag_ui:null, userinfo:null, popupCaller:null, popupId:null, mapPopupId:0, popupParams:null, chart_id:0, map:null, map_state:null, map_value:null, map_callback:null, gyhmap:null, wfs:null, lotarea:null, lotareaProtocol:null, panel:null, measurePanel:null, controls:{measureDist:null, 
  measureArea:null}, measureControls:null, editControls:[], _currentEditLayer:null, draw:null, edit:null, edit_win:null, del:null, info:null, save:null, cancel:null, saveStrategy:null, refreshStrategy:null, transaction:{type:null, callbackWindowId:null}, map_panel:null, userLayer:{custLand:null, landCode:null, prtFclty:null, locArea:null, gisAssetsCd:null, gisPrtFcltyCd:null, gisArchFclty:null, gisTeleFclty:null, gisMechFclty:null, gisElecFclty:null, gisCivilFclty:null, assetsRent:null, assetsRentDetail:null, 
  fcltyMech:null, fcltyIT:null, fcltyCivil:null, fcltyConst:null, assetEPower:null, assetRentStats:null, gnrlRentStats:null, cntrRentStats:null, htldRentStats:null, ccntRentStats:null, trnpRentStats:null, portArea:null, quayGroup:null, quayCd:null, berthCd:null, constructCd:null, fcltyConstMarker:null, fcltySocMarker:null, fcltyMechMarker:null, fcltyInfoTeleMarker:null, fcltyElecPowerMarker:null}, userStyle:{assetCdStyle:{"default":null, "useSttus":null, "notUsgArea":null, "feeSttus":null, "reqSttus":null, 
  "evlDtls":null}, assetRentStyle:{"default":null, "useSttus":null}, assetsRentDetailStyle:{"default":null, "useSttus":null}, quayStyle:{"default":null, "useSttus":null, "notUsgArea":null, "feeSttus":null, "reqSttus":null}}, popup:{prtFcltyInfo:null, lotAreaInfo:null}, popupMenu:{}, layerDisplay:{}, userFunc:{}, startLogLotarea:false, lotareaStackCnt:0, lotareaFeatures:[], symbols:[], protocols:{prtFclty:null, landCode:null, gisAssetsCd:null, locArea:null, assetsRent:null, assetsRentDetail:null, 
  gisPrtFcltyCd:null}, filters:{assetsRent:null}, strategies:{assetsRent:null, assetsRentDetail:null, assetStats:null, assetRentStats:null, gnrlRentStats:null, htldRentStats:null, cntrRentStats:null, ccntRentStats:null, trnpRentStats:null}, selectControl:null, selectInfoControl:null, dragFeature:null, maxBounds:new OpenLayers.Bounds(-3E4, -6E4, 494288, 988576), maxRes:0.0439453125, maxZoom:14, module_instance:[], init:{frame_breaker:function() {
    if (window.location !== window.top.location) {
      window.top.location = window.location;
    }
  }, user_init:function() {
    if ($DEBUG) {
      EMD.userinfo = {name:"\ub514\ubc84\uadf8", authorities:["ROLE_ADMIN"], userId:"TEST1", quayGroupCd:"*", mngFcltyCd:"*", deptCd:"99999"};
      EMD.userLayer.gisArchFclty.setVisibility(true);
      EMD.userLayer.gisCivilFclty.setVisibility(true);
      EMD.userLayer.gisMechFclty.setVisibility(true);
      EMD.userLayer.gisElecFclty.setVisibility(true);
      EMD.userLayer.gisTeleFclty.setVisibility(true);
    } else {
      $.ajax({url:EMD.context_root + "/main/getUserInfo.do", type:"POST", async:false, dataType:"json", global:false, contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:""}).done(function(data) {
        if (data.resultCode == "1") {
          alert(data.message);
          $.post(EMD.context_root + "/uat/uia/YGActionLogout.do").done(function() {
            window.location.reload();
          });
          return;
        }
        EMD.userinfo = data.userInfo;
        if (EMD.userLayer.gisArchFclty != null && EMD.userinfo.mngFcltyCd == "A") {
          EMD.userLayer.gisArchFclty.setVisibility(true);
        }
        if (EMD.userLayer.gisMechFclty != null && EMD.userinfo.mngFcltyCd == "M") {
          EMD.userLayer.gisMechFclty.setVisibility(true);
        }
        if (EMD.userLayer.gisElecFclty != null && EMD.userinfo.mngFcltyCd == "E") {
          EMD.userLayer.gisElecFclty.setVisibility(true);
        }
        if (EMD.userLayer.gisTeleFclty != null && EMD.userinfo.mngFcltyCd == "I") {
          EMD.userLayer.gisTeleFclty.setVisibility(true);
        }
        if (EMD.userLayer.gisCivilFclty != null && EMD.userinfo.mngFcltyCd == "C") {
          EMD.userLayer.gisCivilFclty.setVisibility(true);
        }
        $("#logUserName").text(EMD.userinfo.name);
      });
    }
    if (EMD._frmwrkMenu != null) {
      var mnu = EMD._frmwrkMenu;
      for (var i = 0;i < mnu.length;i++) {
        switch(mnu[i].menuNo) {
          case "2100000":
            var sub = mnu[i].submenu;
            if (sub instanceof Array) {
              EMD.loadAtStartWindow = sub;
            }
            break;
          case "2200000":
            var sub = mnu[i].submenu;
            if (sub instanceof Array) {
              for (var j = 0;j < sub.length;j++) {
                EMD.userFunc[sub[j].url] = sub[j].menuNm;
              }
            }
            break;
          case "2300000":
            var sub = mnu[i].submenu;
            if (sub instanceof Array) {
              for (var j = 0;j < sub.length;j++) {
                EMD.popupMenu[sub[j].progrmStrePath] = sub[j].url;
              }
            }
            break;
          case "2400000":
            var sub = mnu[i].submenu;
            if (sub instanceof Array) {
              for (var j = 0;j < sub.length;j++) {
                EMD.layerDisplay[sub[j].progrmStrePath] = sub[j].url;
              }
            }
            break;
        }
      }
    }
  }, clock:function() {
    var clock = $("#clock");
    if (!clock.length) {
      return;
    }
    var date_obj = new Date;
    var hour = date_obj.getHours();
    var minute = date_obj.getMinutes();
    var day = date_obj.getDate();
    var year = date_obj.getFullYear();
    var suffix = "AM";
    var weekday;
    var month;
    weekday = ["\uc77c", "\uc6d4", "\ud654", "\uc218", "\ubaa9", "\uae08", "\ud1a0"];
    month = ["1\uc6d4", "2\uc6d4", "3\uc6d4", "4\uc6d4", "5\uc6d4", "6\uc6d4", "7\uc6d4", "8\uc6d4", "9\uc6d4", "10\uc6d4", "11\uc6d4", "12\uc6d4"];
    weekday = weekday[date_obj.getDay()];
    month = month[date_obj.getMonth()];
    if (hour >= 12) {
      suffix = "PM";
    }
    if (hour > 12) {
      hour = hour - 12;
    } else {
      if (hour === 0) {
        hour = 12;
      }
    }
    if (minute < 10) {
      minute = "0" + minute;
    }
    var clock_time = weekday + " " + hour + ":" + minute + " " + suffix;
    var clock_date = month + " " + day + ", " + year;
    clock.html(clock_time).attr("title", clock_date);
    setTimeout(EMD.init.clock, 6E4);
  }, desktop:function() {
    var d = $(document);
    $("#progress_dialog").dialog({autoOpen:false, width:180, height:66, modal:true, dialogClass:"progress"});
    $("#file_upload_dialog").dialog({autoOpen:false, width:640, height:480, modal:true, resizable:true, dialogClass:"fileupload"});
    $("#pfPhoto_upload_dialog").dialog({autoOpen:false, width:640, height:480, modal:true, resizable:true, dialogClass:"fileupload"});
    $("#xlsfile_upload_dialog").dialog({autoOpen:false, width:400, height:300, modal:true, resizable:false, dialogClass:"fileupload"});
    d.mousedown(function(ev) {
      if (!$(ev.target).is("a.menu_trigger") && (!$(ev.target).is("a") && (!$(ev.target).is("button") && (!$(ev.target).is("input") && (!$(ev.target).is("select") && (!$(ev.target).is("textarea") && !$(ev.target).is("tr"))))))) {
        EMD.util.clear_active();
        ev.preventDefault();
        ev.stopPropagation();
      }
    });
    d.on("contextmenu", function() {
      return false;
    });
    d.on("click", "a", function(ev) {
      var url = $(this).attr("href");
      this.blur();
      switch($(this).data("role")) {
        case "LoadModule":
          if ($(this).data("url") == "/") {
            return;
          }
          if ($(this).data("url") == "help") {
            EMD.util.showHelp(wikiUrl + "page=Main");
            return;
          }
          EMD.util.create_window($(this).data("progrm-file-nm"), $(this).text(), $(this).data("url"));
          break;
        case "CloseAllWindow":
          EMD.util.clear_active();
          $("#desktop").find(".window").each(function() {
            EMD.closeWindow($(this));
          });
          break;
        case "MinimizeAllWindow":
          EMD.util.clear_active();
          if ($("div.window:visible").length) {
            $("div.window").hide();
          }
          break;
        case "logout":
          EMD.util.clear_active();
          $.post(EMD.context_root + "/uat/uia/YGActionLogout.do").done(function() {
            window.location.reload();
          });
          break;
        case "getUserInfo":
          EMD.util.clear_active();
          EMD.init.user_init();
          break;
        case "startLogLandCode":
          EMD.util.clear_active();
          EMD.lotareaFeatures = [];
          EMD.lotareaStackCnt = 0;
          EMD.startLogLotarea = true;
          break;
        case "saveLandCode":
          EMD.util.clear_active();
          EMD.startLogLotarea = false;
          EMD.protocols.landCode.commit(EMD.lotareaFeatures, {callback:function(resp) {
            if (resp.error) {
              alert("error save lotarea");
              return-1;
            }
            alert("lotarea saved.");
          }});
          EMD.lotareaFeatures = [];
          break;
        case "ShowAllWindow":
          EMD.util.clear_active();
          $("#dock li:visible a").each(function() {
            $($(this).attr("href")).show();
          });
          break;
        case "SortingWindow":
          EMD.util.window_flat();
          EMD.win_x = 80;
          EMD.win_y = 40;
          $("#desktop").find(".window").each(function() {
            EMD.util.window_move($(this).attr("id"), EMD.win_x, EMD.win_y);
            EMD.win_x += 30;
            EMD.win_y += 20;
          });
      }
      if (url == null) {
        return;
      }
      if (url.match(/^#/)) {
        ev.preventDefault();
        ev.stopPropagation();
      } else {
        $(this).attr("target", "_blank");
      }
    });
    d.on("mousedown", "a.menu_trigger", function() {
      if ($(this).next("ul.menu").is(":hidden")) {
        EMD.util.clear_active();
        $(this).addClass("active").next("ul.menu").show();
      } else {
        EMD.util.clear_active();
      }
    });
    d.on("mouseenter", "a.menu_trigger", function() {
      if ($("ul.menu").is(":visible")) {
        EMD.util.clear_active();
        $(this).addClass("active").next("ul.menu").show();
      }
    });
    d.on("mousedown", "a.icon", function() {
      EMD.util.clear_active();
      $(this).addClass("active");
    });
    d.on("dblclick", "a.icon", function() {
      var x = $(this).attr("href");
      var y = $(x).find("a").attr("href");
      if ($(x).is(":hidden")) {
        $(x).remove().appendTo("#dock");
        $(x).show("fast");
      }
      EMD.util.window_flat();
      $(y).addClass("window_stack").show();
    });
    d.on("mouseenter", "a.icon", function() {
      $(this).off("mouseenter").draggable({revert:true, containment:"parent"});
    });
    d.on("click", "#dock a", function() {
      var x = $($(this).attr("href"));
      if (x.is(":visible")) {
        EMD.util.window_hide(x);
      } else {
        EMD.util.window_show(x);
      }
    });
    d.on("mousedown", "div.window", function() {
      EMD.util.window_flat();
      $(this).addClass("window_stack");
    });
    d.on("mouseenter", "div.window", function() {
      $(this).off("mouseenter").draggable({cancel:"a", containment:"#desktop", handle:"div.window_top", start:function(event, ui) {
        EMD.dragging = true;
        EMD.drag_handle = this;
      }, drag:function(event, ui) {
        EMD.drag_ui = ui;
      }, stop:function(event, ui) {
        EMD.dragging = false;
        EMD.drag_ui = ui;
        EMD.drag_handle = null;
      }}).resizable({containment:"parent", minWidth:400, minHeight:200, start:function(event, ui) {
        EMD.dragging = true;
        EMD.drag_handle = this;
      }, stop:function(event, ui) {
        EMD.dragging = false;
        EMD.drag_ui = ui;
        EMD.drag_handle = null;
        EMD.util.window_resized(this);
      }});
    });
    d.on("dblclick", "div.window_top", function() {
      EMD.util.window_resize($(this).closest("div.window"));
    });
    d.on("dblclick", "div.window_top img", function() {
      $($(this).closest("div.window_top").find("a.window_close").attr("href")).hide("fast");
      $(this).closest("div.window").hide();
      return false;
    });
    d.on("click", "a.window_min", function() {
      EMD.util.window_hide($($(this).attr("href")));
    });
    d.on("click", "a.window_help", function() {
      var href = $(this).attr("href");
      if (href != null && href.length > 1) {
        EMD.util.showHelp(wikiUrl + "page=" + href.substring(1));
      } else {
        alert("\uc874\uc7ac\ud558\uc9c0 \uc54a\ub294 \ub9e4\ub274\uc5bc \uc785\ub2c8\ub2e4.");
      }
    });
    d.on("click", "a.window_resize", function() {
      EMD.util.window_resize($($(this).attr("href")));
    });
    d.on("click", "a.window_close", function() {
      EMD.util.window_close($($(this).attr("href")));
    });
    d.on("mousedown", "#show_desktop", function() {
      if ($("div.window:visible").length) {
        $("div.window").hide();
      } else {
        $("#dock li:visible a").each(function() {
          $($(this).attr("href")).show();
        });
      }
    });
    $("table.data").each(function() {
      $(this).find("tbody tr:odd").addClass("zebra");
    });
    d.on("mousedown", "table.data tr", function() {
      EMD.util.clear_active();
      $(this).closest("tr").addClass("active");
    });
    d.on("mouseenter", "#cat_menu", function() {
      $(this).addClass("showCategoryMenu", 1E3);
    });
    d.on("mouseout", "#cat_menu", function() {
      setTimeout(function() {
        $(this).removeClass("showCategoryMenu", 1E3);
      });
    });
  }, wallpaper:function() {
    if ($("#desktop").length) {
    }
  }, map_init:function() {
    var initleftbottomX = "-20037508.34";
    var initleftbottomY = "-20037508.34";
    var initrighttopX = "20037508.34";
    var initrighttopY = "20037508.34";
    initExtent = new OpenLayers.Bounds(initleftbottomX, initleftbottomY, initrighttopX, initrighttopY);
    var sketchSymbolizers = {"Point":{pointRadius:4, graphicName:"square", fillColor:"white", fillOpacity:1, strokeWidth:1, strokeOpacity:1, strokeColor:"#333333"}, "Line":{strokeWidth:3, strokeOpacity:1, strokeColor:"#800080"}, "Polygon":{fill:null, strokeWidth:3, strokeOpacity:1, strokeColor:"#800080"}};
    var style = new OpenLayers.Style;
    style.addRules([new OpenLayers.Rule({symbolizer:sketchSymbolizers})]);
    var styleMap = new OpenLayers.StyleMap({"default":style});
    EMD.map = new OpenLayers.Map("desktop", {div:"desktop", projection:new OpenLayers.Projection("EPSG:900913"), displayProjection:new OpenLayers.Projection("EPSG:5186"), units:"m", numZoomLevels:25, maxResolution:2445.9849047851562, allOverlays:true, maxExtent:initExtent, theme:EMD.context_root + "/css/theme/ygpa/style.css"});
    var basemap = new EMD.gis.OpenLayersVworld("\ubc30\uacbd\uc9c0\ub3c4", {isBaseLayer:true, displayInLayerSwitcher:false, maxExtent:initExtent, type:"png", numZoomLevels:21, baseurl:EMD.map_base_url, projection:new OpenLayers.Projection("EPSG:900913")});
    var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
    renderer = renderer ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;
    if ($DEBUG) {
      var wfs_options = {url:"http://map.vworld.kr/js/wfs.do?APIKEY=" + EMD.vworldKey + "&domain=" + EMD.serverUrl, params:{request:"GetFeature", service:"wfs", version:"1.1.0", typeName:"LP_PA_CBND_BUBUN", srsName:"EPSG:900913"}, format:new OpenLayers.Format.GML({geometryName:"AG_GEOM"})};
      EMD.lotareaProtocol = new OpenLayers.Protocol.HTTP(wfs_options);
      EMD.lotarea = new OpenLayers.Layer.Vector("\uc9c0\ubc88", {minScale:5E3, strategies:[new OpenLayers.Strategy.BBOX({ratio:1})], projection:new OpenLayers.Projection("EPSG:900913"), protocol:EMD.lotareaProtocol, styleMap:new OpenLayers.StyleMap({"default":{strokeColor:"#000000", strokeOpacity:1, strokeWidth:1, fillColor:"#FBFF9A", fillOpacity:0.5, pointRadius:6, pointerEvents:"visiblePainted", label:"${JIBUN}", fontColor:"black", fontSize:"12px", fontFamily:"Courier New, monospace", fontWeight:"bold", 
      labelAlign:"cm", labelXOffset:"2px", labelYOffset:"2px", labelOutlineColor:"white", labelOutlineWidth:3}, "select":{strokeColor:"#00FF00", strokeOpacity:1, strokeWidth:3, fillColor:"#E92FFF", fillOpacity:1, pointRadius:6, pointerEvents:"visiblePainted", label:"${JIBUN}", fontColor:"white", fontSize:"12px", fontFamily:"Courier New, monospace", fontWeight:"bold", labelAlign:"cm", labelXOffset:"2px", labelYOffset:"2px", labelOutlineColor:"black", labelOutlineWidth:3}})});
      EMD.lotarea.events.register("featuresadded", null, function(e) {
        if (!EMD.startLogLotarea) {
          return;
        }
        var lotareaProj = new OpenLayers.Projection("EPSG:900913");
        var features = [];
        for (var i = 0;i < e.features.length;i++) {
          var polygon = e.features[i].geometry.clone();
          var poly = polygon.transform(lotareaProj, EMD.map.getProjection());
          var f = new OpenLayers.Feature.Vector(poly, e.features[i].attributes);
          f.state = OpenLayers.State.INSERT;
          features[features.length] = f;
          EMD.lotareaStackCnt++;
          var resp = EMD.protocols.landCode.read({filter:new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PNU", value:f.attributes.PNU}), data:{feature:f}, callback:function(resp) {
            EMD.lotareaStackCnt--;
            if (resp.error) {
              return 0;
            }
            if (resp.features.length == undefined || resp.features.length === 0) {
              var f = new OpenLayers.Feature;
              f.geometry = resp.data.feature.geometry.clone();
              f.attributes = resp.data.feature.attributes;
              EMD.protocols.landCode.create(f);
              f.state = OpenLayers.State.INSERT;
              var polygon = resp.data.feature.geometry;
              f.geometry = polygon.transform(EMD.lotarea.projection, EMD.userLayer.landCode.projection);
              EMD.lotareaFeatures[EMD.lotareaFeatures.length] = f;
              var v = new OpenLayers.Feature;
              v.geometry = polygon.transform(EMD.lotarea.projection, EMD.map.getProjection());
              v.attributes = resp.data.feature.attributes;
              EMD.userLayer.landCode.addFeatures([v]);
              EMD.userLayer.landCode.redraw();
            } else {
            }
            if (EMD.lotareaStackCnt == 0) {
              alert("stack cleared. " + EMD.lotareaFeatures.length + " count lotarea saved.");
            }
          }});
          resp.data = {feature:f};
        }
      });
      EMD.lotarea.events.register("beforefeatureselected", null, function(e) {
        EMD.selectControl.unselectAll();
        if (EMD.popup.lotAreaInfo != null) {
          EMD.map.removePopup(EMD.popup.lotAreaInfo);
          EMD.popup.lotAreaInfo.destroy();
          EMD.popup.lotAreaInfo = null;
        }
      });
      EMD.lotarea.events.register("featureselected", null, function(e) {
        var feature = e.feature;
        EMD.gis.getBupjungDongNm(e.feature.attributes.PNU.substring(0, 10), {feature:feature}, function(data) {
          if (data.feature == null || data.feature.geometry == null) {
            alert("\uc120\ud0dd\ub41c \uc9c0\ubc88\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.");
          }
          var locplc = data.resultList[0].name;
          var info = "<h2 class='infoClass'>\uc9c0\ubc88 \uc815\ubcf4</h2><div style='font-size:.9em'><br>\uc8fc\uc18c : " + locplc + " " + data.feature.attributes.JIBUN;
          if (EMD.user.hasAuth("ROLE_ADMIN,ROLE_ASSET_MNGT")) {
            info += "<br /><br /><button id='addLotcodeToAsset' data-role='frmwrkButton' data-id='" + data.feature.id + "' >\uc790\uc0b0\uc73c\ub85c \ub4f1\ub85d</button>";
          }
          info += "</div>";
          var popup = new OpenLayers.Popup.FramedCloud("popup", data.feature.geometry.bounds.getCenterLonLat(), new OpenLayers.Size(250, 150), info, null, true);
          if (EMD.popup.lotAreaInfo != null) {
            EMD.map.removePopup(EMD.popup.lotAreaInfo);
            EMD.popup.lotAreaInfo.destroy();
          }
          EMD.popup.lotAreaInfo = popup;
          EMD.map.addPopup(popup);
          $("#addLotcodeToAsset").button({icons:{primary:"ui-icon-plus"}, text:true}).click({feature:feature}, function(event) {
            var gisAssetsProj = new OpenLayers.Projection("EPSG:5186");
            var polygon = event.data.feature.geometry.clone();
            var poly = polygon.transform(event.data.feature.layer.projection, gisAssetsProj);
            var attr = {PRT_CD:"", ASSETS_CD:"", ASSETS_SCD:"", ASSETS_NM:locplc, AR:"", BJD_CODE:""};
            attr = event.data.feature.attributes;
            var vec = new OpenLayers.Feature.Vector(poly, attr);
            EMD.layers.gisAssetsCd.addFeatures(vec);
            EMD.layers.gisAssetsCd.redraw();
            EMD.protocols.gisAssetsCd.create(vec);
            EMD.protocols.gisAssetsCd.commit([vec], {callback:function(resp) {
              if (resp.error) {
                return-1;
              }
            }});
          });
        });
      });
      EMD.lotarea.events.register("featureunselected", null, function(e) {
        if (EMD.popup.lotAreaInfo == null) {
          return;
        }
        EMD.map.removePopup(EMD.popup.lotAreaInfo);
        EMD.popup.lotAreaInfo.destroy();
        EMD.popup.lotAreaInfo = null;
      });
    } else {
    }
    EMD.userLayer.custLand = new OpenLayers.Layer.Vector("\uc9c0\ub3c4", {minScale:9E4, displayInLayerSwitcher:false, strategies:[new OpenLayers.Strategy.BBOX({ratio:1})], projection:new OpenLayers.Projection("EPSG:5186"), protocol:new OpenLayers.Protocol.WFS({version:"1.1.0", service:"WFS", url:EMD.gis_engine_url, featureNS:"http://geogate.g-inno.com/dataserver/datahouse", featurePrefix:"datahouse", featureType:"YGPA_LAND", srsName:"EPSG:5186", geometryName:"G2_SPATIAL"}), styleMap:new OpenLayers.StyleMap({"default":{strokeWidth:0, 
    fillColor:"#F5F1EF", fillOpacity:1}})});
    EMD.userLayer["wharfNm"] = new OpenLayers.Layer.Vector("\ubd80\ub450\uba85", {minScale:9E4, displayInLayerSwitcher:false, strategies:[new OpenLayers.Strategy.BBOX({ratio:1})], projection:new OpenLayers.Projection("EPSG:5186"), protocol:new OpenLayers.Protocol.WFS({version:"1.1.0", service:"WFS", url:EMD.gis_engine_url, featureNS:"http://geogate.g-inno.com/dataserver/datahouse", featurePrefix:"datahouse", featureType:"WHARF_NM", srsName:"EPSG:5186", geometryName:"G2_SPATIAL"}), styleMap:new OpenLayers.StyleMap({"default":{strokeColor:"#00FF00", 
    strokeOpacity:1, strokeWidth:3, fillColor:"#FF5500", fillOpacity:0.5, label:"${TEXT}", fontColor:"blue", fontSize:"12px", fontFamily:"Nanum Gothic", fontWeight:"bold", labelAlign:"cm", labelOutlineColor:"white", labelOutlineWidth:3}})});
    EMD.userLayer["fcltyNm"] = new OpenLayers.Layer.Vector("\uc2dc\uc124\uba85", {minScale:1E4, displayInLayerSwitcher:false, strategies:[new OpenLayers.Strategy.BBOX({ratio:1})], projection:new OpenLayers.Projection("EPSG:5186"), protocol:new OpenLayers.Protocol.WFS({version:"1.1.0", service:"WFS", url:EMD.gis_engine_url, featureNS:"http://geogate.g-inno.com/dataserver/datahouse", featurePrefix:"datahouse", featureType:"FCLTY_NM", srsName:"EPSG:5186", geometryName:"G2_SPATIAL"}), styleMap:new OpenLayers.StyleMap({"default":{strokeColor:"#00FF00", 
    strokeOpacity:1, strokeWidth:3, fillColor:"#FF5500", fillOpacity:0.5, label:"${Text}", fontColor:"white", fontSize:"12px", fontFamily:"Nanum Gothic", fontWeight:"bold", labelAlign:"cm", labelOutlineColor:"black", labelOutlineWidth:3}})});
    EMD.protocols.landCode = new OpenLayers.Protocol.WFS({version:"1.1.0", service:"WFS", url:EMD.gis_engine_url, featureNS:"http://geogate.g-inno.com/dataserver/datahouse", featurePrefix:"datahouse", featureType:"LP_PA_CBND_BUBUN_A", srsName:"EPSG:5186", geometryName:"G2_SPATIAL"});
    EMD.protocols.gisAssetsCd = new OpenLayers.Protocol.WFS({version:"1.1.0", service:"WFS", url:EMD.gis_engine_url, featureNS:"http://geogate.g-inno.com/dataserver/datahouse", featurePrefix:"datahouse", featureType:"GIS_ASSETS_CD_E", srsName:"EPSG:5186", geometryName:"G2_SPATIAL"});
    EMD.protocols.locArea = new OpenLayers.Protocol.WFS({version:"1.1.0", service:"WFS", url:EMD.gis_engine_url, featureNS:"http://geogate.g-inno.com/dataserver/datahouse", featurePrefix:"datahouse", featureType:"LOC_AREA_E", srsName:"EPSG:5186", geometryName:"G2_SPATIAL"});
    EMD.protocols.assetsRent = new OpenLayers.Protocol.WFS({version:"1.1.0", service:"WFS", url:EMD.gis_engine_url, featureNS:"http://geogate.g-inno.com/dataserver/datahouse", featurePrefix:"datahouse", featureType:"ASSETS_RENT_E", srsName:"EPSG:5186", geometryName:"G2_SPATIAL"});
    EMD.protocols.assetsRentDetail = new OpenLayers.Protocol.WFS({version:"1.1.0", service:"WFS", url:EMD.gis_engine_url, featureNS:"http://geogate.g-inno.com/dataserver/datahouse", featurePrefix:"datahouse", featureType:"ASSETS_RENT_DETAIL_F", srsName:"EPSG:5186", geometryName:"G2_SPATIAL"});
    EMD.protocols.gisPrtFcltyCd = new OpenLayers.Protocol.WFS.v1_1_0({version:"1.1.0", service:"WFS", url:EMD.gis_engine_url, featureNS:"http://geogate.g-inno.com/dataserver/datahouse", featurePrefix:"datahouse", featureType:"FCLTY_E", srsName:"EPSG:5186", geometryName:"G2_SPATIAL"});
    EMD.saveStrategy = new OpenLayers.Strategy.Save({auto:true});
    EMD.refreshStrategy = new OpenLayers.Strategy.Refresh;
    EMD.userStyle.assetCdStyle["default"] = {"default":{strokeColor:"#000000", strokeOpacity:1, strokeWidth:1, fillColor:"#303294", fillOpacity:0.5, pointRadius:6, pointerEvents:"visiblePainted", label:"${ASSETS_NM}", fontColor:"black", fontSize:"12px", fontFamily:"Courier New, monospace", fontWeight:"bold", labelAlign:"cm", labelXOffset:"2px", labelYOffset:"2px", labelOutlineColor:"white", labelOutlineWidth:3}, "select":{strokeColor:"#00FF00", strokeOpacity:1, strokeWidth:3, fillColor:"#303294", 
    fillOpacity:1, pointRadius:6, pointerEvents:"visiblePainted", label:"${ASSETS_NM}", fontColor:"white", fontSize:"12px", fontFamily:"Courier New, monospace", fontWeight:"bold", labelAlign:"cm", labelXOffset:"2px", labelYOffset:"2px", labelOutlineColor:"black", labelOutlineWidth:3}, "modify":{strokeOpacity:1, strokeWidth:1, fillOpacity:0.5, pointRadius:6, label:"", pointerEvents:"visiblePainted", fontColor:"black", fontSize:"12px", fontFamily:"Courier New, monospace", fontWeight:"bold", strokeColor:"#ff0000", 
    fillColor:"#ff0000", graphicName:"circle"}};
    EMD.userStyle.assetCdStyle["evlDtls"] = {"default":{strokeColor:"#000000", strokeOpacity:1, strokeWidth:1, fillColor:"#73D7CF", fillOpacity:0.8, pointRadius:6, pointerEvents:"visiblePainted", label:"\uc790\uc0b0\uba85:${ASSETS_NM}\n\uc790\ubcf8\uc801\uc9c0\ucd9c\uae08\uc561:${assetBuyAmt}\n\uc794\uc874\uae08\uc561:${curAmt}", fontColor:"black", fontSize:"12px", fontFamily:"Courier New, monospace", fontWeight:"bold", labelAlign:"cm", labelXOffset:"2px", labelYOffset:"2px", labelOutlineColor:"white", 
    labelOutlineWidth:3}, "select":{strokeColor:"#76750A", strokeOpacity:1, strokeWidth:3, fillColor:"#8D1594", fillOpacity:1, pointRadius:6, pointerEvents:"visiblePainted", label:"\uc790\uc0b0\uba85:${ASSETS_NM}\n\uc790\ubcf8\uc801\uc9c0\ucd9c\uae08\uc561:${assetBuyAmt}\n\uc794\uc874\uae08\uc561:${curAmt}", fontColor:"white", fontSize:"12px", fontFamily:"Courier New, monospace", fontWeight:"bold", labelAlign:"cm", labelXOffset:"2px", labelYOffset:"2px", labelOutlineColor:"black", labelOutlineWidth:3}};
    EMD.userLayer.landCode = new OpenLayers.Layer.Vector("\uc9c0\ubc88", {minScale:9E3, strategies:[new OpenLayers.Strategy.BBOX({ratio:1})], projection:new OpenLayers.Projection("EPSG:5186"), protocol:EMD.protocols.landCode, styleMap:new OpenLayers.StyleMap({"default":{strokeColor:"#000000", strokeOpacity:1, strokeWidth:1, fillColor:"#FBFF9A", fillOpacity:0.5, pointRadius:6, pointerEvents:"visiblePainted", label:"[${JIBUN}]", fontColor:"black", fontSize:"12px", fontFamily:"Courier New, monospace", 
    fontWeight:"bold", labelAlign:"cm", labelXOffset:"2px", labelYOffset:"2px", labelOutlineColor:"white", labelOutlineWidth:3}, "select":{strokeColor:"#00FF00", strokeOpacity:1, strokeWidth:3, fillColor:"#E92FFF", fillOpacity:1, pointRadius:6, pointerEvents:"visiblePainted", label:"!${JIBUN}", fontColor:"white", fontSize:"12px", fontFamily:"Courier New, monospace", fontWeight:"bold", labelAlign:"cm", labelXOffset:"2px", labelYOffset:"2px", labelOutlineColor:"black", labelOutlineWidth:3}})});
    EMD.protocols.landCode.layer = EMD.userLayer.landCode;
    EMD.userLayer.landCode.events.register("beforefeatureselected", null, function(e) {
      EMD.selectControl.unselectAll();
      if (EMD.popup.lotAreaInfo != null) {
        EMD.map.removePopup(EMD.popup.lotAreaInfo);
        EMD.popup.lotAreaInfo.destroy();
        EMD.popup.lotAreaInfo = null;
      }
    });
    EMD.userLayer.landCode.events.register("featureselected", null, function(e) {
      var feature = e.feature;
      EMD.gis.getBupjungDongNm(e.feature.attributes.PNU.substring(0, 10), {feature:feature}, function(data) {
        if (data.feature == null || data.feature.geometry == null) {
          alert("\uc120\ud0dd\ub41c \uc9c0\ubc88\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.");
        }
        var locplc = data.resultList[0].name;
        var info = "<h2 class='infoClass'>\uc9c0\ubc88 \uc815\ubcf4</h2><div style='font-size:.9em'><br>\uc8fc\uc18c : " + locplc + " " + data.feature.attributes.JIBUN;
        if (EMD.user.hasAuth("ROLE_ADMIN,ROLE_ASSET_MNGT")) {
          info += "<br /><br /><button id='addLotcodeToAsset' data-role='frmwrkButton' data-id='" + data.feature.id + "' >\uc790\uc0b0\uc73c\ub85c \ub4f1\ub85d</button>";
        }
        info += "</div>";
        var popup = new OpenLayers.Popup.FramedCloud("popup", data.feature.geometry.bounds.getCenterLonLat(), new OpenLayers.Size(250, 150), info, null, true);
        if (EMD.popup.lotAreaInfo != null) {
          EMD.map.removePopup(EMD.popup.lotAreaInfo);
          EMD.popup.lotAreaInfo.destroy();
        }
        EMD.popup.lotAreaInfo = popup;
        EMD.map.addPopup(popup);
        $("#addLotcodeToAsset").button({icons:{primary:"ui-icon-plus"}, text:true}).click({feature:feature}, function(event) {
          var lotareaProj = new OpenLayers.Projection("EPSG:900913");
          var gisAssetsProj = new OpenLayers.Projection("EPSG:5186");
          var polygon = event.data.feature.geometry.clone();
          var poly = polygon.transform(lotareaProj, gisAssetsProj);
          var attr = {PRT_CD:"", ASSETS_CD:"", ASSETS_SCD:"", ASSETS_NM:"\ubbf8\uc815\uc758", AR:"", BJD_CODE:event.data.feature.attributes.PNU};
          var vec = new OpenLayers.Feature.Vector(poly, attr);
          EMD.protocols.gisAssetsCd.create(vec);
          vec.state = OpenLayers.State.INSERT;
          EMD.protocols.gisAssetsCd.commit([vec], {callback:function(resp) {
            if (resp.error) {
              return-1;
            }
            var gisAssetsCdProjection = new OpenLayers.Projection("EPSG:5186");
            EMD.selectControl.unselectAll();
            if (resp.reqFeatures.length != undefined) {
              $.each(resp.reqFeatures, function() {
                var polygon = this.geometry;
                this.state = "";
                this.geometry = polygon.transform(gisAssetsCdProjection, EMD.map.getProjection());
              });
            } else {
              resp.reqFeatures.geometry = polygon.transform(gisAssetsCdProjection, EMD.map.getProjection());
              resp.reqFeatures.state = "";
            }
            EMD.userLayer.gisAssetsCd.addFeatures(resp.reqFeatures);
            EMD.userLayer.gisAssetsCd.redraw();
          }});
        });
      });
    });
    EMD.userLayer.landCode.events.register("featureunselected", null, function(e) {
      if (EMD.popup.lotAreaInfo == null) {
        return;
      }
      EMD.map.removePopup(EMD.popup.lotAreaInfo);
      EMD.popup.lotAreaInfo.destroy();
      EMD.popup.lotAreaInfo = null;
    });
    EMD.userLayer.locArea = new OpenLayers.Layer.Vector("\uc704\uce58", {minScale:81E3, minScale:3E3, strategies:[new OpenLayers.Strategy.Fixed], projection:new OpenLayers.Projection("EPSG:5186"), protocol:EMD.protocols.locArea, displayInLayerSwitcher:false, styleMap:new OpenLayers.StyleMap({"default":{strokeColor:"#FFFFFF", strokeOpacity:1, strokeWidth:1, fillColor:"#45CEC9", fillOpacity:0.5, pointRadius:6, pointerEvents:"visiblePainted", label:"[${LOC_NM}]", fontColor:"black", fontSize:"12px", 
    fontFamily:"Courier New, monospace", fontWeight:"bold", labelAlign:"cm", labelXOffset:"2px", labelYOffset:"2px", labelOutlineColor:"white", labelOutlineWidth:3}, "select":{strokeColor:"#000000", strokeOpacity:1, strokeWidth:3, fillColor:"#E92FFF", fillOpacity:1, pointRadius:6, pointerEvents:"visiblePainted", label:"!${LOC_NM}", fontColor:"white", fontSize:"12px", fontFamily:"Courier New, monospace", fontWeight:"bold", labelAlign:"cm", labelXOffset:"2px", labelYOffset:"2px", labelOutlineColor:"black", 
    labelOutlineWidth:3}}, {extendDefault:false})});
    EMD.protocols.locArea.layer = EMD.userLayer.locArea;
    EMD.userLayer.locArea.events.register("featureselected", null, function(e) {
      var feature = e.feature;
    });
    EMD.userLayer.locArea.events.register("featureunselected", null, function(e) {
    });
    EMD.userLayer.gisAssetsCd = new OpenLayers.Layer.Vector("\uc790\uc0b0\uc815\ubcf4", {minScale:1E4, strategies:[new OpenLayers.Strategy.Fixed, EMD.saveStrategy, EMD.refreshStrategy], projection:new OpenLayers.Projection("EPSG:5186"), protocol:EMD.protocols.gisAssetsCd, styleMap:new OpenLayers.StyleMap(EMD.userStyle.assetCdStyle["default"], {extendDefault:true})});
    EMD.protocols.gisAssetsCd.layer = EMD.userLayer.gisAssetsCd;
    EMD.userLayer.gisAssetsCd.events.register("featuresadded", null, function(e) {
    });
    EMD.userLayer.gisAssetsCd.events.register("featureselected", null, function(e) {
      EMD.gis.openPopup(e.feature, "/maps/assets/gamAssetCdInfo.do", [{name:"gisAssetsPrtAtCode", value:e.feature.attributes.PRT_CD}, {name:"gisAssetsCd", value:e.feature.attributes.ASSETS_CD}, {name:"gisAssetsSubCd", value:e.feature.attributes.ASSETS_SCD}, {name:"bjdCode", value:e.feature.attributes.BJD_CODE}, {name:"ar", value:e.feature.attributes.AR}]);
    });
    EMD.userLayer.gisAssetsCd.events.register("featureunselected", null, function(e) {
      var feature = e.feature;
      if (feature.popup != null) {
        EMD.map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
      }
    });
    var prtFcltyCdSymbol = {"Point":{pointRadius:16, fillColor:"white", fillOpacity:1, strokeWidth:1, strokeOpacity:1, strokeColor:"#333333"}, "Line":{strokeWidth:3, strokeOpacity:1, strokeColor:"#800080"}, "Polygon":{fillColor:"#45CEC9", fillOpacity:0.5, strokeWidth:3, strokeOpacity:1, strokeColor:"#800080"}};
    var fcltyCdRule = new OpenLayers.Rule({filter:new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"FCLTY_SE", value:"E"}), symbolizer:{"Point":{pointRadius:10, externalGraphic:EMD.context_root + "/images/egovframework/ygpa/gam/maps/map_icon/icon_${FCLTY_CD}.png", graphicWidth:32, graphicHeight:37, fillColor:"red", fillOpacity:1, strokeWidth:1, strokeOpacity:1, strokeColor:"#333333"}, "Line":{strokeWidth:3, strokeOpacity:1, strokeColor:"#800080"}, "Polygon":{fillColor:"#45CEC9", 
    fillOpacity:0.5, strokeWidth:1, strokeOpacity:1, strokeColor:"#800080"}}});
    var gisPrtFcltyCdStyle = new OpenLayers.Style;
    gisPrtFcltyCdStyle.addRules([fcltyCdRule]);
    var archFilter = new OpenLayers.Strategy.Filter({filter:new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"FCLTY_SE", value:"A"})});
    archFilter.deactivate();
    var gisArchFcltyStyle = new OpenLayers.Style;
    var rules = [];
    $.each(fcltyConstRuleSet, function() {
      $.each(this.symbolizer, function(key, val) {
        if (val.externalGraphic != undefined || val.externalGraphic != null) {
          val.externalGraphic = EMD.context_root + val.externalGraphic;
        }
      });
      rules[rules.length] = new OpenLayers.Rule({title:this.title, filter:this.filter, symbolizer:this.symbolizer});
    });
    gisArchFcltyStyle.addRules(rules);
    EMD.userLayer.gisArchFclty = new OpenLayers.Layer.Vector("\uac74\ucd95 \uc2dc\uc124 \uc815\ubcf4", {minScale:9E3, strategies:[new OpenLayers.Strategy.Fixed, EMD.saveStrategy], projection:new OpenLayers.Projection("EPSG:5186"), protocol:EMD.protocols.gisPrtFcltyCd, styleMap:new OpenLayers.StyleMap({"default":gisArchFcltyStyle, "select":{strokeOpacity:1, strokeWidth:3, fillColor:"#303294", fillOpacity:1, pointRadius:6, pointerEvents:"visiblePainted", fontColor:"blue", fontSize:"12px", fontWeight:"bold", 
    labelAlign:"cm", labelOutlineColor:"white", labelOutlineWidth:3}, "modify":{strokeOpacity:1, strokeWidth:1, fillOpacity:0.5, pointRadius:6, label:"", pointerEvents:"visiblePainted", fontColor:"black", fontSize:"12px", fontFamily:"Courier New, monospace", fontWeight:"bold", strokeColor:"#ff0000", fillColor:"#ff0000", graphicName:"circle"}}, {extendDefault:false}), filter:new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"FCLTY_SE", value:"A"})});
    EMD.userLayer.gisArchFclty.events.register("featuresadded", null, function(e) {
    });
    EMD.userLayer.gisArchFclty.events.register("beforefeatureselected", null, function(e) {
      EMD.selectControl.unselectAll();
      if (EMD.popup.lotAreaInfo != null) {
        EMD.map.removePopup(EMD.popup.lotAreaInfo);
        EMD.popup.lotAreaInfo.destroy();
        EMD.popup.lotAreaInfo = null;
      }
    });
    EMD.userLayer.gisArchFclty.events.register("featureselected", null, function(e) {
      EMD.gis.openPopup(e.feature, "/maps/fclty/gamArchFcltyInfo.do", [{name:"gisAssetsPrtAtCode", value:e.feature.attributes.PRT_CD}, {name:"gisAssetsCd", value:e.feature.attributes.ASSETS_CD}, {name:"gisAssetsSubCd", value:e.feature.attributes.ASSETS_SCD}, {name:"gisPrtFcltyCd", value:e.feature.attributes.FCLTY_CD}, {name:"gisPrtFcltySeq", value:e.feature.attributes.FCLTY_SEQ}, {name:"gisPrtFcltySe", value:e.feature.attributes.FCLTY_SE}]);
    });
    EMD.userLayer.gisArchFclty.events.register("featureunselected", null, function(e) {
      var feature = e.feature;
      if (feature.popup != null) {
        EMD.map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
      }
    });
    var mechFilter = new OpenLayers.Strategy.Filter({filter:new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"FCLTY_SE", value:"M"})});
    mechFilter.deactivate();
    gisMechFcltyStyle = new OpenLayers.Style;
    rules = [];
    $.each(fcltyMechRuleSet, function() {
      $.each(this.symbolizer, function(key, val) {
        if (val.externalGraphic != undefined || val.externalGraphic != null) {
          val.externalGraphic = EMD.context_root + val.externalGraphic;
        }
      });
      rules[rules.length] = new OpenLayers.Rule({title:this.title, filter:this.filter, symbolizer:this.symbolizer});
    });
    gisMechFcltyStyle.addRules(rules);
    EMD.userLayer.gisMechFclty = new OpenLayers.Layer.Vector("\uae30\uacc4 \uc2dc\uc124 \uc815\ubcf4", {minScale:9E3, strategies:[new OpenLayers.Strategy.Fixed, EMD.saveStrategy, EMD.refreshStrategy], projection:new OpenLayers.Projection("EPSG:5186"), protocol:EMD.protocols.gisPrtFcltyCd, styleMap:new OpenLayers.StyleMap({"default":gisMechFcltyStyle, "select":{strokeOpacity:1, strokeWidth:3, fillColor:"#303294", fillOpacity:1, pointRadius:6, pointerEvents:"visiblePainted", fontColor:"blue", fontSize:"12px", 
    fontWeight:"bold", labelAlign:"cm", labelOutlineColor:"white", labelOutlineWidth:3}, "modify":{strokeOpacity:1, strokeWidth:1, fillOpacity:0.5, pointRadius:6, label:"", pointerEvents:"visiblePainted", fontColor:"black", fontSize:"12px", fontFamily:"Courier New, monospace", fontWeight:"bold", strokeColor:"#ff0000", fillColor:"#ff0000", graphicName:"circle"}}, {extendDefault:false}), filter:new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"FCLTY_SE", value:"M"})});
    EMD.userLayer.gisMechFclty.events.register("featuresadded", null, function(e) {
    });
    EMD.userLayer.gisMechFclty.events.register("beforefeatureselected", null, function(e) {
      EMD.selectControl.unselectAll();
      if (EMD.popup.lotAreaInfo != null) {
        EMD.map.removePopup(EMD.popup.lotAreaInfo);
        EMD.popup.lotAreaInfo.destroy();
        EMD.popup.lotAreaInfo = null;
      }
    });
    EMD.userLayer.gisMechFclty.events.register("featureselected", null, function(e) {
      EMD.gis.openPopup(e.feature, "/maps/fclty/gamMechFcltyInfo.do", [{name:"gisAssetsPrtAtCode", value:e.feature.attributes.PRT_CD}, {name:"gisAssetsCd", value:e.feature.attributes.ASSETS_CD}, {name:"gisAssetsSubCd", value:e.feature.attributes.ASSETS_SCD}, {name:"gisPrtFcltyCd", value:e.feature.attributes.FCLTY_CD}, {name:"gisPrtFcltySeq", value:e.feature.attributes.FCLTY_SEQ}, {name:"gisPrtFcltySe", value:e.feature.attributes.FCLTY_SE}]);
    });
    EMD.userLayer.gisMechFclty.events.register("featureunselected", null, function(e) {
      var feature = e.feature;
      if (feature.popup != null) {
        EMD.map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
      }
    });
    var ITFilter = new OpenLayers.Strategy.Filter({filter:new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"FCLTY_SE", value:"I"})});
    ITFilter.deactivate();
    var gisTeleFcltyStyle = new OpenLayers.Style;
    rules = [];
    $.each(fcltyITRuleSet, function() {
      $.each(this.symbolizer, function(key, val) {
        if (val.externalGraphic != undefined || val.externalGraphic != null) {
          val.externalGraphic = EMD.context_root + val.externalGraphic;
        }
      });
      rules[rules.length] = new OpenLayers.Rule({title:this.title, filter:this.filter, symbolizer:this.symbolizer});
    });
    gisTeleFcltyStyle.addRules(rules);
    EMD.userLayer.gisTeleFclty = new OpenLayers.Layer.Vector("\uc815\ubcf4\ud1b5\uc2e0 \uc2dc\uc124 \uc815\ubcf4", {minScale:9E3, strategies:[new OpenLayers.Strategy.Fixed, EMD.saveStrategy, EMD.refreshStrategy], projection:new OpenLayers.Projection("EPSG:5186"), protocol:EMD.protocols.gisPrtFcltyCd, styleMap:new OpenLayers.StyleMap({"default":gisTeleFcltyStyle, "select":{strokeOpacity:1, strokeWidth:3, fillColor:"#303294", fillOpacity:1, pointRadius:6, pointerEvents:"visiblePainted", fontColor:"blue", 
    fontSize:"12px", fontWeight:"bold", labelAlign:"cm", labelOutlineColor:"white", labelOutlineWidth:3}, "modify":{strokeOpacity:1, strokeWidth:1, fillOpacity:0.5, pointRadius:6, label:"", pointerEvents:"visiblePainted", fontColor:"black", fontSize:"12px", fontFamily:"Courier New, monospace", fontWeight:"bold", strokeColor:"#ff0000", fillColor:"#ff0000", graphicName:"circle"}}, {extendDefault:false}), filter:new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"FCLTY_SE", 
    value:"I"})});
    EMD.userLayer.gisTeleFclty.events.register("featuresadded", null, function(e) {
    });
    EMD.userLayer.gisTeleFclty.events.register("beforefeatureselected", null, function(e) {
      EMD.selectControl.unselectAll();
      if (EMD.popup.lotAreaInfo != null) {
        EMD.map.removePopup(EMD.popup.lotAreaInfo);
        EMD.popup.lotAreaInfo.destroy();
        EMD.popup.lotAreaInfo = null;
      }
    });
    EMD.userLayer.gisTeleFclty.events.register("featureselected", null, function(e) {
      EMD.gis.openPopup(e.feature, "/maps/fclty/gamTeleFcltyInfo.do", [{name:"gisAssetsPrtAtCode", value:e.feature.attributes.PRT_CD}, {name:"gisAssetsCd", value:e.feature.attributes.ASSETS_CD}, {name:"gisAssetsSubCd", value:e.feature.attributes.ASSETS_SCD}, {name:"gisPrtFcltyCd", value:e.feature.attributes.FCLTY_CD}, {name:"gisPrtFcltySeq", value:e.feature.attributes.FCLTY_SEQ}, {name:"gisPrtFcltySe", value:e.feature.attributes.FCLTY_SE}]);
    });
    EMD.userLayer.gisTeleFclty.events.register("featureunselected", null, function(e) {
      var feature = e.feature;
      if (feature.popup != null) {
        EMD.map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
      }
    });
    var elecFilter = new OpenLayers.Strategy.Filter({filter:new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"FCLTY_SE", value:"E"})});
    elecFilter.deactivate();
    var gisPowerFcltyStyle = new OpenLayers.Style;
    rules = [];
    $.each(fcltyPowerRuleSet, function() {
      $.each(this.symbolizer, function(key, val) {
        if (val.externalGraphic != undefined || val.externalGraphic != null) {
          val.externalGraphic = EMD.context_root + val.externalGraphic;
        }
      });
      rules[rules.length] = new OpenLayers.Rule({title:this.title, filter:this.filter, symbolizer:this.symbolizer});
    });
    gisPowerFcltyStyle.addRules(rules);
    EMD.userLayer.gisElecFclty = new OpenLayers.Layer.Vector("\uc804\uae30 \uc2dc\uc124 \uc815\ubcf4", {minScale:9E3, strategies:[new OpenLayers.Strategy.Fixed, EMD.saveStrategy, EMD.refreshStrategy], projection:new OpenLayers.Projection("EPSG:5186"), protocol:EMD.protocols.gisPrtFcltyCd, styleMap:new OpenLayers.StyleMap({"default":gisPowerFcltyStyle, "select":{strokeOpacity:1, strokeWidth:3, fillColor:"#303294", fillOpacity:1, pointRadius:6, pointerEvents:"visiblePainted", fontColor:"blue", fontSize:"12px", 
    fontWeight:"bold", labelAlign:"cm", labelOutlineColor:"white", labelOutlineWidth:3}, "modify":{strokeOpacity:1, strokeWidth:1, fillOpacity:0.5, pointRadius:6, label:"", pointerEvents:"visiblePainted", fontColor:"black", fontSize:"12px", fontFamily:"Courier New, monospace", fontWeight:"bold", strokeColor:"#ff0000", fillColor:"#ff0000", graphicName:"circle"}}, {extendDefault:false}), filter:new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"FCLTY_SE", value:"E"})});
    EMD.userLayer.gisElecFclty.events.register("featuresadded", null, function(e) {
    });
    EMD.userLayer.gisElecFclty.events.register("beforefeatureselected", null, function(e) {
      EMD.selectControl.unselectAll();
      if (EMD.popup.lotAreaInfo != null) {
        EMD.map.removePopup(EMD.popup.lotAreaInfo);
        EMD.popup.lotAreaInfo.destroy();
        EMD.popup.lotAreaInfo = null;
      }
    });
    EMD.userLayer.gisElecFclty.events.register("featureselected", null, function(e) {
      EMD.gis.openPopup(e.feature, "/maps/fclty/gamElecFcltyInfo.do", [{name:"gisAssetsPrtAtCode", value:e.feature.attributes.PRT_CD}, {name:"gisAssetsCd", value:e.feature.attributes.ASSETS_CD}, {name:"gisAssetsSubCd", value:e.feature.attributes.ASSETS_SCD}, {name:"gisPrtFcltyCd", value:e.feature.attributes.FCLTY_CD}, {name:"gisPrtFcltySeq", value:e.feature.attributes.FCLTY_SEQ}, {name:"gisPrtFcltySe", value:e.feature.attributes.FCLTY_SE}]);
    });
    EMD.userLayer.gisElecFclty.events.register("featureunselected", null, function(e) {
      var feature = e.feature;
      if (feature.popup != null) {
        EMD.map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
      }
    });
    var civilFilter = new OpenLayers.Strategy.Filter({filter:new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"FCLTY_SE", value:"C"})});
    civilFilter.deactivate();
    var gisCivilFcltyStyle = new OpenLayers.Style;
    var rules = [];
    $.each(fcltyCivilRuleSet, function() {
      $.each(this.symbolizer, function(key, val) {
        if (val.externalGraphic != undefined || val.externalGraphic != null) {
          val.externalGraphic = EMD.context_root + val.externalGraphic;
        }
      });
      rules[rules.length] = new OpenLayers.Rule({title:this.title, filter:this.filter, symbolizer:this.symbolizer});
    });
    gisCivilFcltyStyle.addRules(rules);
    EMD.userLayer.gisCivilFclty = new OpenLayers.Layer.Vector("\ud1a0\ubaa9 \uc2dc\uc124 \uc815\ubcf4", {minScale:9E3, strategies:[new OpenLayers.Strategy.Fixed, EMD.saveStrategy, EMD.refreshStrategy], projection:new OpenLayers.Projection("EPSG:5186"), protocol:EMD.protocols.gisPrtFcltyCd, styleMap:new OpenLayers.StyleMap({"default":gisCivilFcltyStyle, "select":{strokeOpacity:1, strokeWidth:7, fillColor:"#303294", fillOpacity:1, pointRadius:6, pointerEvents:"visiblePainted", fontColor:"blue", fontSize:"12px", 
    fontWeight:"bold", labelAlign:"cm", labelOutlineColor:"white", labelOutlineWidth:3}, "modify":{strokeOpacity:1, strokeWidth:1, fillOpacity:0.5, pointRadius:6, label:"", pointerEvents:"visiblePainted", fontColor:"black", fontSize:"12px", fontFamily:"Courier New, monospace", fontWeight:"bold", strokeColor:"#ff0000", fillColor:"#ff0000", graphicName:"circle"}}, {extendDefault:false}), filter:new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"FCLTY_SE", value:"C"})});
    EMD.userLayer.gisCivilFclty.events.register("featuresadded", null, function(e) {
    });
    EMD.userLayer.gisCivilFclty.events.register("beforefeatureselected", null, function(e) {
      EMD.selectControl.unselectAll();
      if (EMD.popup.lotAreaInfo != null) {
        EMD.map.removePopup(EMD.popup.lotAreaInfo);
        EMD.popup.lotAreaInfo.destroy();
        EMD.popup.lotAreaInfo = null;
      }
    });
    EMD.userLayer.gisCivilFclty.events.register("featureselected", null, function(e) {
      EMD.gis.openPopup(e.feature, "/maps/fclty/gamCivilFcltyInfo.do", [{name:"gisAssetsPrtAtCode", value:e.feature.attributes.PRT_CD}, {name:"gisAssetsCd", value:e.feature.attributes.ASSETS_CD}, {name:"gisAssetsSubCd", value:e.feature.attributes.ASSETS_SCD}, {name:"gisPrtFcltyCd", value:e.feature.attributes.FCLTY_CD}, {name:"gisPrtFcltySeq", value:e.feature.attributes.FCLTY_SEQ}, {name:"gisPrtFcltySe", value:e.feature.attributes.FCLTY_SE}]);
    });
    EMD.userLayer.gisCivilFclty.events.register("featureunselected", null, function(e) {
      var feature = e.feature;
      if (feature.popup != null) {
        EMD.map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
      }
    });
    var assetsRentStyle = new OpenLayers.Style;
    var rules = [new OpenLayers.Rule({title:"\uc77c\ubc18\ubd80\ub450 \uc784\ub300", filter:new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"QUAY_CD", value:"G"}), symbolizer:{"Polygon":{fillColor:"#3F48CC", fillOpacity:0.5, strokeWidth:1, strokeOpacity:1, strokeColor:"#FFFFFF", label:"${ENTRPS_NM}\n${GR_AR}m\u00b2", fontColor:"black", fontSize:"12px", fontFamily:"\uad74\ub9bc", fontWeight:"bold", labelAlign:"cm", labelXOffset:"2px", labelYOffset:"16px", labelOutlineColor:"white", 
    labelOutlineWidth:3}}}), new OpenLayers.Rule({title:"\ubc30\ud6c4\ub2e8\uc9c0 \uc784\ub300", filter:new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"QUAY_CD", value:"H"}), symbolizer:{"Polygon":{fillColor:"#00A2E8", fillOpacity:0.5, strokeWidth:1, strokeOpacity:1, strokeColor:"#FFFFFF", label:"${ENTRPS_NM}\n${GR_AR}m\u00b2", fontColor:"black", fontSize:"12px", fontFamily:"\uad74\ub9bc", fontWeight:"bold", labelAlign:"cm", labelXOffset:"2px", labelYOffset:"16px", 
    labelOutlineColor:"white", labelOutlineWidth:3}}}), new OpenLayers.Rule({title:"\ud56d\ub9cc\uad00\ub828\ubd80\uc9c0 \uc784\ub300", filter:new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"QUAY_CD", value:"C"}), symbolizer:{"Polygon":{fillColor:"#FF7F27", fillOpacity:0.5, strokeWidth:1, strokeOpacity:1, strokeColor:"#FFFFFF", label:"${ENTRPS_NM}\n${GR_AR}m\u00b2", fontColor:"black", fontSize:"12px", fontFamily:"\uad74\ub9bc", fontWeight:"bold", labelAlign:"cm", 
    labelXOffset:"2px", labelYOffset:"16px", labelOutlineColor:"white", labelOutlineWidth:3}}}), new OpenLayers.Rule({title:"\uacf5\ucee8\uc7a5\uce58\uc7a5 \uc784\ub300", filter:new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"QUAY_CD", value:"E"}), symbolizer:{"Polygon":{fillColor:"#A349A4", fillOpacity:0.5, strokeWidth:1, strokeOpacity:1, strokeColor:"#FFFFFF", label:"${ENTRPS_NM}\n${GR_AR}m\u00b2", fontColor:"black", fontSize:"12px", fontFamily:"\uad74\ub9bc", 
    fontWeight:"bold", labelAlign:"cm", labelXOffset:"2px", labelYOffset:"16px", labelOutlineColor:"white", labelOutlineWidth:3}}}), new OpenLayers.Rule({title:"\ucca0\uc1a1\uc7a5 \uc784\ub300", filter:new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"QUAY_CD", value:"T"}), symbolizer:{"Polygon":{fillColor:"#7F7F7F", fillOpacity:0.5, strokeWidth:1, strokeOpacity:1, strokeColor:"#FFFFFF", label:"${ENTRPS_NM}\n${GR_AR}m\u00b2", fontColor:"black", fontSize:"12px", 
    fontFamily:"\uad74\ub9bc", fontWeight:"bold", labelAlign:"cm", labelXOffset:"2px", labelYOffset:"16px", labelOutlineColor:"white", labelOutlineWidth:3}}})];
    assetsRentStyle.addRules(rules);
    var saveAssetsRent = new OpenLayers.Strategy.Save({auto:false});
    var refreshAssetsRent = new OpenLayers.Strategy.Refresh;
    var today = EMD.util.getDate().replace(/-/g, "");
    EMD.userLayer.assetsRent = new OpenLayers.Layer.Vector("\uc784\ub300\uc815\ubcf4", {minScale:1E4, strategies:[new OpenLayers.Strategy.BBOX, saveAssetsRent, refreshAssetsRent], projection:new OpenLayers.Projection("EPSG:5186"), protocol:EMD.protocols.assetsRent, styleMap:new OpenLayers.StyleMap({"default":assetsRentStyle, "select":{strokeOpacity:1, strokeWidth:3, fillColor:"#303294", fillOpacity:1, pointRadius:6, pointerEvents:"visiblePainted", fontColor:"blue", fontSize:"12px", fontWeight:"bold", 
    labelAlign:"cm", labelOutlineColor:"white", labelOutlineWidth:3}, "modify":{strokeOpacity:1, strokeWidth:1, fillOpacity:0.5, pointRadius:6, label:"", pointerEvents:"visiblePainted", fontColor:"black", fontSize:"12px", fontFamily:"Courier New, monospace", fontWeight:"bold", strokeColor:"#ff0000", fillColor:"#ff0000", graphicName:"circle"}}, {extendDefault:false}), filter:new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.LESS_THAN, 
    property:"DT_FR", value:today}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.GREATER_THAN, property:"DT_TO", value:today}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRMISN_YN", value:"Y"})]}), saveStrategy:saveAssetsRent, refreshStrategy:refreshAssetsRent});
    EMD.userLayer.assetsRent.events.register("visibilitychanged", null, function(e) {
      var legendTab = EMD.legendWin.tabs("assetsRent");
      if (e.object.visibility) {
        $("#legendPanel").show();
        if (legendTab == undefined || legendTab == null) {
          EMD.legendWin.addTab("assetsRent", "\uc784\ub300\ud604\ud669 \ubc94\ub840", null, null, true);
          legendTab = EMD.gis.addLegendLayer(e.object, "assetsRent");
          EMD.legendWin._counts++;
        }
        legendTab.show(true);
      } else {
        if (legendTab != undefined && legendTab != null) {
          legendTab.close();
          EMD.legendWin._counts--;
          if (EMD.legendWin._counts <= 0) {
            EMD.legendWin._counts = 0;
            $("#legendPanel").hide();
          }
        }
      }
    });
    EMD.userLayer.assetsRent.events.register("featureselected", null, function(e) {
      var feature = e.feature;
      EMD.gis.openPopup(feature, "/maps/assets/gamAssetRentInfo.do", feature.attributes, function(feature) {
      });
    });
    EMD.userLayer.assetsRent.events.register("featureunselected", null, function(e) {
      var feature = e.feature;
      if (feature.popup != null) {
        EMD.map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
      }
    });
    EMD.strategies.assetStats = new OpenLayers.Strategy.Refresh;
    var context = {getColor:function(feature) {
      return EMD.gis.getStatsColor(feature);
    }, getLabel:function(feature) {
      return EMD.gis.getStatsLabel(feature);
    }};
    var template = {fillColor:"${getColor}", label:"${getLabel}"};
    var style = new OpenLayers.Style(template, {context:context, labelOutlineColor:"white", labelOutlineWidth:3});
    EMD.userLayer.assetStats = new OpenLayers.Layer.Vector("\ud1b5\uacc4\uc815\ubcf4", {minScale:72E3, strategies:[EMD.strategies.assetStats], projection:new OpenLayers.Projection("EPSG:5186"), styleMap:new OpenLayers.StyleMap(style)});
    EMD.strategies.assetStats.activate();
    EMD.userLayer.assetStats.events.register("featureselected", null, function(e) {
      EMD.gis.openPopup(e.feature, "/maps/assets/gamAssetCdInfo.do", EMD.util.objectToArray(e.feature.attributes), function(feature) {
        feature.popup.updateSize();
      });
    });
    EMD.userLayer.assetStats.events.register("featureunselected", null, function(e) {
      var feature = e.feature;
      if (feature.popup != null) {
        EMD.map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
      }
    });
    var currDate = new Date;
    EMD.strategies.assetRentStats = new OpenLayers.Strategy.Filter;
    EMD.userLayer.assetRentStats = new OpenLayers.Layer.Vector("\uc784\ub300\ud604\ud669", {minScale:1E4, strategies:[new OpenLayers.Strategy.BBOX, EMD.strategies.assetRentStats], projection:new OpenLayers.Projection("EPSG:5186"), protocol:EMD.protocols.assetsRentDetail, styleMap:new OpenLayers.StyleMap(EMD.userStyle.assetRentStyle["default"])});
    EMD.strategies.assetRentStats.deactivate();
    EMD.userLayer.assetRentStats.events.register("featuresadded", null, function(e) {
    });
    EMD.userLayer.assetRentStats.events.register("featureselected", null, function(e) {
      EMD.gis.openPopup(e.feature, "/maps/assets/gamAssetRentInfo.do", [{name:"prtAtCode", value:e.feature.attributes.PRT_CD}, {name:"mngYear", value:e.feature.attributes.MNG_YEAR}, {name:"mngNo", value:e.feature.attributes.MNG_NO}, {name:"mngCnt", value:e.feature.attributes.MNG_CNT}], function(feature) {
        var popup = feature.popup;
        $("button[data-role='assetRentMngt']", popup.contentDiv).button({icons:{primary:"ui-icon-wrench"}}).click(function(event) {
          EMD.util.create_window("gamAssetRentMngt", $(this).text(), "/code/assets/gamAssetRentMngt.do", null, {action:"assetRentMngt", prtAtCode:$(this).data("prt-at-code"), mngYear:$(this).data("mng-year"), mngNo:$(this).data("mng-no"), mngCnt:$(this).data("mng-cnt")});
        });
        $("button[data-role='assetRentInqire']", popup.contentDiv).button({icons:{primary:"ui-icon-folder-open"}}).click(function(event) {
          EMD.util.create_window("gamAssetRentInqire", $(this).text(), "/code/assets/gamAssetRentInqire.do", null, {action:"assetRentInqire", prtAtCode:$(this).data("prt-at-code"), mngYear:$(this).data("mng-year"), mngNo:$(this).data("mng-no"), mngCnt:$(this).data("mng-cnt")});
        });
        popup.updateSize();
      });
    });
    EMD.userLayer.assetRentStats.events.register("featureunselected", null, function(e) {
      var feature = e.feature;
      if (feature.popup != null) {
        EMD.map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
      }
    });
    EMD.strategies.gnrlRentStats = new OpenLayers.Strategy.Filter;
    EMD.strategies.gnrlRentStats.deactivate();
    EMD.userLayer.gnrlRentStats = new OpenLayers.Layer.Vector("\uc77c\ubc18\ubd80\ub450 \uc784\ub300\ud604\ud669", {minScale:1E4, strategies:[EMD.strategies.gnrlRentStats], projection:new OpenLayers.Projection("EPSG:5186"), protocol:EMD.protocols.assetsRent, styleMap:new OpenLayers.StyleMap(EMD.userStyle.assetRentStyle["default"])});
    EMD.userLayer.gnrlRentStats.events.register("featuresadded", null, function(e) {
    });
    EMD.userLayer.gnrlRentStats.events.register("featureselected", null, function(e) {
      EMD.gis.openPopup(e.feature, "/oper/gnrl/gamPrtFcltyRentMngt.do", [{name:"prtAtCode", value:e.feature.attributes.PRT_CD}, {name:"mngYear", value:e.feature.attributes.MNG_YEAR}, {name:"mngNo", value:e.feature.attributes.MNG_NO}, {name:"mngCnt", value:e.feature.attributes.MNG_CNT}], function(feature) {
        $("button[data-role='assetRentMngt']", popup.contentDiv).button({icons:{primary:"ui-icon-wrench"}}).click(function(event) {
          EMD.util.create_window("gamAssetRentMngt", $(this).text(), "/code/assets/gamAssetRentMngt.do", null, {action:"assetRentMngt", prtAtCode:$(this).data("prt-at-code"), mngYear:$(this).data("mng-year"), mngNo:$(this).data("mng-no"), mngCnt:$(this).data("mng-cnt")});
        });
        $("button[data-role='assetRentInqire']", popup.contentDiv).button({icons:{primary:"ui-icon-folder-open"}}).click(function(event) {
          EMD.util.create_window("gamAssetRentInqire", $(this).text(), "/code/assets/gamAssetRentInqire.do", null, {action:"assetRentInqire", prtAtCode:$(this).data("prt-at-code"), mngYear:$(this).data("mng-year"), mngNo:$(this).data("mng-no"), mngCnt:$(this).data("mng-cnt")});
        });
        popup.updateSize();
      });
    });
    EMD.userLayer.gnrlRentStats.events.register("featureunselected", null, function(e) {
      var feature = e.feature;
      if (feature.popup != null) {
        EMD.map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
      }
    });
    EMD.strategies.cntrRentStats = new OpenLayers.Strategy.Filter;
    EMD.strategies.cntrRentStats.deactivate();
    EMD.userLayer.cntrRentStats = new OpenLayers.Layer.Vector("\ucee8\ud14c\uc774\ub108\ubd80\ub450 \uc784\ub300\ud604\ud669", {minScale:1E4, strategies:[EMD.strategies.cntrRentStats], projection:new OpenLayers.Projection("EPSG:5186"), protocol:EMD.protocols.assetsRent, styleMap:new OpenLayers.StyleMap(EMD.userStyle.assetRentStyle["default"])});
    EMD.userLayer.cntrRentStats.events.register("featuresadded", null, function(e) {
    });
    EMD.userLayer.cntrRentStats.events.register("featureselected", null, function(e) {
      var feature = e.feature;
      $.post(EMD.context_root + "/oper/gnrl/gamPrtFcltyRentMngt.do", [{name:"prtAtCode", value:e.feature.attributes.PRT_AT_CD}, {name:"mngYear", value:e.feature.attributes.MNG_YEAR}, {name:"mngNo", value:e.feature.attributes.MNG_NO}, {name:"mngCnt", value:e.feature.attributes.MNG_CNT}]).done(function(data) {
        var popup = new OpenLayers.Popup.FramedCloud("popup", feature.geometry.bounds.getCenterLonLat(), new OpenLayers.Size(250, 150), data, null, true);
        feature.popup = popup;
        EMD.map.addPopup(popup);
        $("button[data-role='assetRentMngt']", popup.contentDiv).button({icons:{primary:"ui-icon-wrench"}}).click(function(event) {
          EMD.util.create_window("gamAssetRentMngt", $(this).text(), "/code/assets/gamAssetRentMngt.do", null, {action:"assetRentMngt", prtAtCode:$(this).data("prt-at-code"), mngYear:$(this).data("mng-year"), mngNo:$(this).data("mng-no"), mngCnt:$(this).data("mng-cnt")});
        });
        $("button[data-role='assetRentInqire']", popup.contentDiv).button({icons:{primary:"ui-icon-folder-open"}}).click(function(event) {
          EMD.util.create_window("gamAssetRentInqire", $(this).text(), "/code/assets/gamAssetRentInqire.do", null, {action:"assetRentInqire", prtAtCode:$(this).data("prt-at-code"), mngYear:$(this).data("mng-year"), mngNo:$(this).data("mng-no"), mngCnt:$(this).data("mng-cnt")});
        });
        popup.updateSize();
      });
    });
    EMD.userLayer.cntrRentStats.events.register("featureunselected", null, function(e) {
      var feature = e.feature;
      if (feature.popup != null) {
        EMD.map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
      }
    });
    EMD.strategies.htldRentStats = new OpenLayers.Strategy.Filter;
    EMD.strategies.htldRentStats.deactivate();
    EMD.userLayer.htldRentStats = new OpenLayers.Layer.Vector("\ubc30\ud6c4\ub2e8\uc9c0 \uc784\ub300\ud604\ud669", {minScale:1E4, strategies:[EMD.strategies.htldRentStats], projection:new OpenLayers.Projection("EPSG:5186"), protocol:EMD.protocols.assetsRent, styleMap:new OpenLayers.StyleMap(EMD.userStyle.assetRentStyle["default"])});
    EMD.userLayer.htldRentStats.events.register("featuresadded", null, function(e) {
    });
    EMD.userLayer.htldRentStats.events.register("featureselected", null, function(e) {
      var feature = e.feature;
      $.post(EMD.context_root + "/oper/gnrl/gamPrtFcltyRentMngt.do", [{name:"prtAtCode", value:e.feature.attributes.PRT_AT_CD}, {name:"mngYear", value:e.feature.attributes.MNG_YEAR}, {name:"mngNo", value:e.feature.attributes.MNG_NO}, {name:"mngCnt", value:e.feature.attributes.MNG_CNT}]).done(function(data) {
        var popup = new OpenLayers.Popup.FramedCloud("popup", feature.geometry.bounds.getCenterLonLat(), new OpenLayers.Size(250, 150), data, null, true);
        feature.popup = popup;
        EMD.map.addPopup(popup);
        $("button[data-role='assetRentMngt']", popup.contentDiv).button({icons:{primary:"ui-icon-wrench"}}).click(function(event) {
          EMD.util.create_window("gamAssetRentMngt", $(this).text(), "/code/assets/gamAssetRentMngt.do", null, {action:"assetRentMngt", prtAtCode:$(this).data("prt-at-code"), mngYear:$(this).data("mng-year"), mngNo:$(this).data("mng-no"), mngCnt:$(this).data("mng-cnt")});
        });
        $("button[data-role='assetRentInqire']", popup.contentDiv).button({icons:{primary:"ui-icon-folder-open"}}).click(function(event) {
          EMD.util.create_window("gamAssetRentInqire", $(this).text(), "/code/assets/gamAssetRentInqire.do", null, {action:"assetRentInqire", prtAtCode:$(this).data("prt-at-code"), mngYear:$(this).data("mng-year"), mngNo:$(this).data("mng-no"), mngCnt:$(this).data("mng-cnt")});
        });
        popup.updateSize();
      });
    });
    EMD.userLayer.htldRentStats.events.register("featureunselected", null, function(e) {
      var feature = e.feature;
      if (feature.popup != null) {
        EMD.map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
      }
    });
    EMD.strategies.ccntRentStats = new OpenLayers.Strategy.Filter;
    EMD.strategies.ccntRentStats.deactivate();
    EMD.userLayer.ccntRentStats = new OpenLayers.Layer.Vector("\uacf5\ucee8\uc7a5\uce58\uc7a5 \uc784\ub300\ud604\ud669", {minScale:1E4, strategies:[EMD.strategies.ccntRentStats], projection:new OpenLayers.Projection("EPSG:5186"), protocol:EMD.protocols.assetsRent, styleMap:new OpenLayers.StyleMap(EMD.userStyle.assetRentStyle["default"])});
    EMD.userLayer.ccntRentStats.events.register("featuresadded", null, function(e) {
    });
    EMD.userLayer.ccntRentStats.events.register("featureselected", null, function(e) {
      var feature = e.feature;
      $.post(EMD.context_root + "/oper/gnrl/gamPrtFcltyRentMngt.do", [{name:"prtAtCode", value:e.feature.attributes.PRT_AT_CD}, {name:"mngYear", value:e.feature.attributes.MNG_YEAR}, {name:"mngNo", value:e.feature.attributes.MNG_NO}, {name:"mngCnt", value:e.feature.attributes.MNG_CNT}]).done(function(data) {
        var popup = new OpenLayers.Popup.FramedCloud("popup", feature.geometry.bounds.getCenterLonLat(), new OpenLayers.Size(250, 150), data, null, true);
        feature.popup = popup;
        EMD.map.addPopup(popup);
        $("button[data-role='assetRentMngt']", popup.contentDiv).button({icons:{primary:"ui-icon-wrench"}}).click(function(event) {
          EMD.util.create_window("gamAssetRentMngt", $(this).text(), "/code/assets/gamAssetRentMngt.do", null, {action:"assetRentMngt", prtAtCode:$(this).data("prt-at-code"), mngYear:$(this).data("mng-year"), mngNo:$(this).data("mng-no"), mngCnt:$(this).data("mng-cnt")});
        });
        $("button[data-role='assetRentInqire']", popup.contentDiv).button({icons:{primary:"ui-icon-folder-open"}}).click(function(event) {
          EMD.util.create_window("gamAssetRentInqire", $(this).text(), "/code/assets/gamAssetRentInqire.do", null, {action:"assetRentInqire", prtAtCode:$(this).data("prt-at-code"), mngYear:$(this).data("mng-year"), mngNo:$(this).data("mng-no"), mngCnt:$(this).data("mng-cnt")});
        });
        popup.updateSize();
      });
    });
    EMD.userLayer.ccntRentStats.events.register("featureunselected", null, function(e) {
      var feature = e.feature;
      if (feature.popup != null) {
        EMD.map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
      }
    });
    EMD.strategies.trnpRentStats = new OpenLayers.Strategy.Filter;
    EMD.strategies.trnpRentStats.deactivate();
    EMD.userLayer.trnpRentStats = new OpenLayers.Layer.Vector("\ucca0\uc1a1\uc7a5 \uc784\ub300\ud604\ud669", {minScale:1E4, strategies:[EMD.strategies.trnpRentStats], projection:new OpenLayers.Projection("EPSG:5186"), protocol:EMD.protocols.assetsRent, styleMap:new OpenLayers.StyleMap(EMD.userStyle.assetRentStyle["default"])});
    EMD.userLayer.trnpRentStats.events.register("featuresadded", null, function(e) {
    });
    EMD.userLayer.trnpRentStats.events.register("featureselected", null, function(e) {
      var feature = e.feature;
      $.post(EMD.context_root + "/oper/gnrl/gamPrtFcltyRentMngt.do", [{name:"prtAtCode", value:e.feature.attributes.PRT_AT_CD}, {name:"mngYear", value:e.feature.attributes.MNG_YEAR}, {name:"mngNo", value:e.feature.attributes.MNG_NO}, {name:"mngCnt", value:e.feature.attributes.MNG_CNT}]).done(function(data) {
        var popup = new OpenLayers.Popup.FramedCloud("popup", feature.geometry.bounds.getCenterLonLat(), new OpenLayers.Size(250, 150), data, null, true);
        feature.popup = popup;
        EMD.map.addPopup(popup);
        $("button[data-role='assetRentMngt']", popup.contentDiv).button({icons:{primary:"ui-icon-wrench"}}).click(function(event) {
          EMD.util.create_window("gamAssetRentMngt", $(this).text(), "/code/assets/gamAssetRentMngt.do", null, {action:"assetRentMngt", prtAtCode:$(this).data("prt-at-code"), mngYear:$(this).data("mng-year"), mngNo:$(this).data("mng-no"), mngCnt:$(this).data("mng-cnt")});
        });
        $("button[data-role='assetRentInqire']", popup.contentDiv).button({icons:{primary:"ui-icon-folder-open"}}).click(function(event) {
          EMD.util.create_window("gamAssetRentInqire", $(this).text(), "/code/assets/gamAssetRentInqire.do", null, {action:"assetRentInqire", prtAtCode:$(this).data("prt-at-code"), mngYear:$(this).data("mng-year"), mngNo:$(this).data("mng-no"), mngCnt:$(this).data("mng-cnt")});
        });
        popup.updateSize();
      });
    });
    EMD.userLayer.trnpRentStats.events.register("featureunselected", null, function(e) {
      var feature = e.feature;
      if (feature.popup != null) {
        EMD.map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
      }
    });
    EMD.userLayer.fcltyConstMarker = new OpenLayers.Layer.Vector("\uac74\ucd95 \uc2dc\uc124", {minScale:5E3});
    EMD.userLayer.fcltyConstMarker.events.register("featureselected", null, function(e) {
      $.ajax({url:EMD.context_root + "/maps/fclty/gamFcltyInfo.do", type:"POST", dataType:"json", global:false, contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"gisAssetsPrtAtCode", value:e.feature.attributes.gisAssetsPrtAtCode}, {name:"gisAssetsCd", value:e.feature.attributes.gisAssetsCd}, {name:"gisAssetsSubCd", value:e.feature.attributes.gisAssetsSubCd}, {name:"gisPrtFcltyCd", value:e.feature.attributes.gisPrtFcltyCd}, {name:"gisPrtFcltySeq", value:e.feature.attributes.gisPrtFcltySeq}, 
      {name:"gisPrtFcltySe", value:e.feature.attributes.gisPrtFcltySe}]}).done(function(data) {
        if (data.resultCode == 0) {
          var htmlInfo = "<div class='prtFcltyInfo'><h2>\uac74\ucd95\uc2dc\uc124\uc815\ubcf4</h2><table class='prtFcltyInfo'><tbody>";
          var fclty = data.result;
          if (fclty == null) {
            alert("\uc120\ud0dd\ud55c \uc2dc\uc124\uc758 \uc815\ubcf4\uac00 \uc5c6\uc2b5\ub2c8\ub2e4.");
          }
          htmlInfo += "<tr><th>\uc790\uc0b0\ucf54\ub4dc</th><td>" + fclty.gisAssetsCd + "-" + fclty.gisAssetsSubCd + "</td></tr>";
          htmlInfo += "<tr><th>\uc2dc\uc124\ucf54\ub4dc</th><td>" + fclty.gisPrtFcltyCd + "-" + fclty.gisPrtFcltySeq + "</td></tr>";
          if (fclty.prtFcltyNm != null) {
            htmlInfo += "<tr><th>\uc2dc\uc124\uba85</th><td>" + fclty.prtFcltyNm + "</td></tr>";
          }
          if (fclty.gisAssetsLocplc != null) {
            var lnm = fclty.gisAssetsLnm + (fclty.gisAssetsLnmSub != null && fclty.gisAssetsLnmSub.length > 0 ? "-" + fclty.gisAssetsLnmSub : "");
            htmlInfo += "<tr><th>\uc18c\uc7ac\uc9c0</th><td>" + fclty.gisAssetsLocplc + " " + lnm + "</td></tr>";
          }
          if (fclty.filenmPhysicl != null) {
            htmlInfo += "<tr><th>\uc2dc\uc124\uc0ac\uc9c4</th><td><img src='" + EMD.context_root + "/cmm/getPfImage.do?physicalFileNm=" + fclty.filenmPhysicl + "' style='width:300px;' /></td></tr>";
          }
          htmlInfo += "<tr><td colSpan='2' style='align: right;'>";
          if (EMD.popupMenu["fcltyConstPopMngt"] != undefined) {
            htmlInfo += "<td><button id='btnPrtfcltyMngt' data-role='frmwrkButton' data-assets-prt-at-code='" + fclty.gisAssetsPrtAtCode + "' data-assets-cd='" + fclty.gisAssetsCd + "' data-assets-sub-cd='" + fclty.gisAssetsSubCd + "' data-prt-fclty-cd='" + fclty.gisPrtFcltyCd + "' data-prt-fclty-seq='" + fclty.gisPrtFcltySeq + "' data-prt-fclty-se='" + fclty.gisPrtFcltySe + "' >\uc2dc\uc124\uad00\ub9ac</button></td>";
          }
          if (EMD.popupMenu["fcltyConstPopInqire"] != undefined) {
            htmlInfo += "<td><button id='btnPrtfcltyInqire' data-role='frmwrkButton' data-assets-prt-at-code='" + fclty.gisAssetsPrtAtCode + "' data-assets-cd='" + fclty.gisAssetsCd + "' data-assets-sub-cd='" + fclty.gisAssetsSubCd + "' data-prt-fclty-cd='" + fclty.gisPrtFcltyCd + "' data-prt-fclty-seq='" + fclty.gisPrtFcltySeq + "' data-prt-fclty-se='" + fclty.gisPrtFcltySe + "' >\uc2dc\uc124\uc870\ud68c</button></td>";
          }
          htmlInfo += "</tr>";
          htmlInfo += "</tbody></table>";
          if (EMD.popup.prtFcltyInfo != null) {
            EMD.map.removePopup(EMD.popup.prtFcltyInfo);
            EMD.popup.prtFcltyInfo = null;
          }
          if (fclty.loCrdnt == null || fclty.laCrdnt == null) {
            if (fclty.lat != null && fclty.lng != null) {
              var latLng = new OpenLayers.LonLat(fclty.lng, fclty.lat);
              var proj = new OpenLayers.Projection("EPSG:4326");
              var newPos = latLng.transform(proj, new OpenLayers.Projection("EPSG:900913"));
              fclty.loCrdnt = newPos.lon;
              fclty.laCrdnt = newPos.lat;
            }
          }
          EMD.popup.prtFcltyInfo = new OpenLayers.Popup.FramedCloud("\uac74\ucd95\uc2dc\uc124\uc815\ubcf4", new OpenLayers.LonLat(fclty.loCrdnt, fclty.laCrdnt), new OpenLayers.Size(250, 150), htmlInfo, null, true);
          EMD.map.addPopup(EMD.popup.prtFcltyInfo);
          $("#btnPrtfcltyMngt").button({icons:{primary:"ui-icon-wrench"}, text:true}).click(function(event) {
            var title = "";
            var url = "";
            title = "\uac74\ucd95 \uc2dc\uc124 \uad00\ub9ac";
            url = EMD.popupMenu["fcltyConstPopMngt"];
            EMD.util.create_window("", title, url, null, {action:"prtFcltyMngt", gisPrtAtCode:$(this).data("assets-prt-at-code"), gisAssetsCd:$(this).data("assets-cd"), gisAssetsSubCd:$(this).data("assets-sub-cd"), gisPrtFcltyCd:$(this).data("prt-fclty-cd"), gisPrtFcltySeq:$(this).data("prt-fclty-seq"), gisPrtFcltySe:$(this).data("prt-fclty-se")});
          });
          $("#btnPrtfcltyInqire").button({icons:{primary:"ui-icon-folder-open"}, text:true}).click(function(event) {
            var title = "";
            var url = "";
            title = "\uac74\ucd95 \uc2dc\uc124 \uc870\ud68c";
            url = EMD.popupMenu["fcltyConstPopInqire"];
            EMD.util.create_window("", title, url, null, {action:"prtFcltyMngt", gisPrtAtCode:$(this).data("assets-prt-at-code"), gisAssetsCd:$(this).data("assets-cd"), gisAssetsSubCd:$(this).data("assets-sub-cd"), gisPrtFcltyCd:$(this).data("prt-fclty-cd"), gisPrtFcltySeq:$(this).data("prt-fclty-seq"), gisPrtFcltySe:$(this).data("prt-fclty-se")});
          });
          EMD.popup.prtFcltyInfo.updateSize();
          $(".prtFcltyInfo td > img").load(function(e) {
            var title = $("h2", EMD.popup.prtFcltyInfo.contentDiv);
            var t = $("table.prtFcltyInfo", EMD.popup.prtFcltyInfo.contentDiv);
            EMD.popup.prtFcltyInfo.updateSize();
          });
        }
      });
    });
    EMD.userLayer.fcltyConstMarker.events.register("featureunselected", null, function(e) {
      if (EMD.popup.prtFcltyInfo != null) {
        EMD.map.removePopup(EMD.popup.prtFcltyInfo);
        EMD.popup.prtFcltyInfo = null;
      }
    });
    EMD.userLayer.fcltySocMarker = new OpenLayers.Layer.Vector("\ud1a0\ubaa9 \uc2dc\uc124", {minScale:5E3});
    EMD.userLayer.fcltySocMarker.events.register("featureselected", null, function(e) {
      $.ajax({url:EMD.context_root + "/maps/fclty/gamFcltyInfo.do", type:"POST", dataType:"json", global:false, contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"gisAssetsPrtAtCode", value:e.feature.attributes.gisAssetsPrtAtCode}, {name:"gisAssetsCd", value:e.feature.attributes.gisAssetsCd}, {name:"gisAssetsSubCd", value:e.feature.attributes.gisAssetsSubCd}, {name:"gisPrtFcltyCd", value:e.feature.attributes.gisPrtFcltyCd}, {name:"gisPrtFcltySeq", value:e.feature.attributes.gisPrtFcltySeq}, 
      {name:"gisPrtFcltySe", value:e.feature.attributes.gisPrtFcltySe}]}).done(function(data) {
        if (data.resultCode == 0) {
          var htmlInfo = "<div class='prtFcltyInfo'><h2>\ud1a0\ubaa9\uc2dc\uc124\uc815\ubcf4</h2><table class='prtFcltyInfo'><tbody>";
          var fclty = data.result;
          if (fclty == null) {
            alert("\uc120\ud0dd\ud55c \uc2dc\uc124\uc758 \uc815\ubcf4\uac00 \uc5c6\uc2b5\ub2c8\ub2e4.");
          }
          htmlInfo += "<tr><th>\uc790\uc0b0\ucf54\ub4dc</th><td>" + fclty.gisAssetsCd + "-" + fclty.gisAssetsSubCd + "</td></tr>";
          htmlInfo += "<tr><th>\uc2dc\uc124\ucf54\ub4dc</th><td>" + fclty.gisPrtFcltyCd + "-" + fclty.gisPrtFcltySeq + "</td></tr>";
          if (fclty.prtFcltyNm != null) {
            htmlInfo += "<tr><th>\uc2dc\uc124\uba85</th><td>" + fclty.prtFcltyNm + "</td></tr>";
          }
          if (fclty.gisAssetsLocplc != null) {
            var lnm = fclty.gisAssetsLnm + (fclty.gisAssetsLnmSub != null && fclty.gisAssetsLnmSub.length > 0) ? "-" + fclty.gisAssetsLnmSub : "";
            htmlInfo += "<tr><th>\uc18c\uc7ac\uc9c0</th><td>" + fclty.gisAssetsLocplc + " " + lnm + "</td></tr>";
          }
          if (fclty.filenmPhysicl != null) {
            htmlInfo += "<tr><th>\uc2dc\uc124\uc0ac\uc9c4</th><td><img src='" + EMD.context_root + "/cmm/getPfImage.do?physicalFileNm=" + fclty.filenmPhysicl + "' style='width:300px;' /></td></tr>";
          }
          htmlInfo += "<tr><td colSpan='2' style='align: right;'>";
          if (EMD.popupMenu["fcltySocPopMngt"] != undefined) {
            htmlInfo += "<td><button id='btnPrtfcltyMngt' data-role='frmwrkButton' data-assets-prt-at-code='" + fclty.gisAssetsPrtAtCode + "' data-assets-cd='" + fclty.gisAssetsCd + "' data-assets-sub-cd='" + fclty.gisAssetsSubCd + "' data-prt-fclty-cd='" + fclty.gisPrtFcltyCd + "' data-prt-fclty-seq='" + fclty.gisPrtFcltySeq + "' data-prt-fclty-se='" + fclty.gisPrtFcltySe + "' >\uc2dc\uc124\uad00\ub9ac</button></td>";
          }
          if (EMD.popupMenu["fcltySocPopInqire"] != undefined) {
            htmlInfo += "<td><button id='btnPrtfcltyInqire' data-role='frmwrkButton' data-assets-prt-at-code='" + fclty.gisAssetsPrtAtCode + "' data-assets-cd='" + fclty.gisAssetsCd + "' data-assets-sub-cd='" + fclty.gisAssetsSubCd + "' data-prt-fclty-cd='" + fclty.gisPrtFcltyCd + "' data-prt-fclty-seq='" + fclty.gisPrtFcltySeq + "' data-prt-fclty-se='" + fclty.gisPrtFcltySe + "' >\uc2dc\uc124\uc870\ud68c</button></td>";
          }
          htmlInfo += "</tr>";
          htmlInfo += "</tbody></table>";
          if (EMD.popup.prtFcltyInfo != null) {
            EMD.map.removePopup(EMD.popup.prtFcltyInfo);
            EMD.popup.prtFcltyInfo = null;
          }
          if (fclty.loCrdnt == null || fclty.laCrdnt == null) {
            if (fclty.lat != null && fclty.lng != null) {
              var latLng = new OpenLayers.LonLat(fclty.lng, fclty.lat);
              var proj = new OpenLayers.Projection("EPSG:4326");
              var newPos = latLng.transform(proj, new OpenLayers.Projection("EPSG:900913"));
              fclty.loCrdnt = newPos.lon;
              fclty.laCrdnt = newPos.lat;
            }
          }
          EMD.popup.prtFcltyInfo = new OpenLayers.Popup.FramedCloud("\ud1a0\ubaa9\uc2dc\uc124\uc815\ubcf4", new OpenLayers.LonLat(fclty.loCrdnt, fclty.laCrdnt), new OpenLayers.Size(250, 150), htmlInfo, null, true);
          EMD.map.addPopup(EMD.popup.prtFcltyInfo);
          $("#btnPrtfcltyMngt").button({icons:{primary:"ui-icon-wrench"}, text:true}).click(function(event) {
            var title = "";
            var url = "";
            title = "\ud1a0\ubaa9 \uc2dc\uc124 \uad00\ub9ac";
            url = EMD.popupMenu["fcltyConstPopMngt"];
            EMD.util.create_window("", title, url, null, {action:"prtFcltyMngt", gisPrtAtCode:$(this).data("assets-prt-at-code"), gisAssetsCd:$(this).data("assets-cd"), gisAssetsSubCd:$(this).data("assets-sub-cd"), gisPrtFcltyCd:$(this).data("prt-fclty-cd"), gisPrtFcltySeq:$(this).data("prt-fclty-seq"), gisPrtFcltySe:$(this).data("prt-fclty-se")});
          });
          $("#btnPrtfcltyInqire").button({icons:{primary:"ui-icon-folder-open"}, text:true}).click(function(event) {
            var title = "";
            var url = "";
            title = "\uac74\ucd95 \uc2dc\uc124 \uc870\ud68c";
            url = EMD.popupMenu["fcltyConstPopInqire"];
            EMD.util.create_window("", title, url, null, {action:"prtFcltyMngt", gisPrtAtCode:$(this).data("assets-prt-at-code"), gisAssetsCd:$(this).data("assets-cd"), gisAssetsSubCd:$(this).data("assets-sub-cd"), gisPrtFcltyCd:$(this).data("prt-fclty-cd"), gisPrtFcltySeq:$(this).data("prt-fclty-seq"), gisPrtFcltySe:$(this).data("prt-fclty-se")});
          });
          EMD.popup.prtFcltyInfo.updateSize();
          $(".prtFcltyInfo td > img").load(function(e) {
            var title = $("h2", EMD.popup.prtFcltyInfo.contentDiv);
            var t = $("table.prtFcltyInfo", EMD.popup.prtFcltyInfo.contentDiv);
            EMD.popup.prtFcltyInfo.updateSize();
          });
        }
      });
    });
    EMD.userLayer.fcltySocMarker.events.register("featureunselected", null, function(e) {
      if (EMD.popup.prtFcltyInfo != null) {
        EMD.map.removePopup(EMD.popup.prtFcltyInfo);
        EMD.popup.prtFcltyInfo = null;
      }
    });
    EMD.userLayer.fcltyMechMarker = new OpenLayers.Layer.Vector("\uae30\uacc4 \uc2dc\uc124", {minScale:5E3});
    EMD.userLayer.fcltyMechMarker.events.register("featureselected", null, function(e) {
      $.ajax({url:EMD.context_root + "/maps/fclty/gamFcltyInfo.do", type:"POST", dataType:"json", global:false, contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"gisAssetsPrtAtCode", value:e.feature.attributes.gisAssetsPrtAtCode}, {name:"gisAssetsCd", value:e.feature.attributes.gisAssetsCd}, {name:"gisAssetsSubCd", value:e.feature.attributes.gisAssetsSubCd}, {name:"gisPrtFcltyCd", value:e.feature.attributes.gisPrtFcltyCd}, {name:"gisPrtFcltySeq", value:e.feature.attributes.gisPrtFcltySeq}, 
      {name:"gisPrtFcltySe", value:e.feature.attributes.gisPrtFcltySe}]}).done(function(data) {
        if (data.resultCode == 0) {
          var htmlInfo = "<div class='prtFcltyInfo'><h2>\uae30\uacc4\uc2dc\uc124\uc815\ubcf4</h2><table class='prtFcltyInfo'><tbody>";
          var fclty = data.result;
          if (fclty == null) {
            alert("\uc120\ud0dd\ud55c \uc2dc\uc124\uc758 \uc815\ubcf4\uac00 \uc5c6\uc2b5\ub2c8\ub2e4.");
          }
          htmlInfo += "<tr><th>\uc790\uc0b0\ucf54\ub4dc</th><td>" + fclty.gisAssetsCd + "-" + fclty.gisAssetsSubCd + "</td></tr>";
          htmlInfo += "<tr><th>\uc2dc\uc124\ucf54\ub4dc</th><td>" + fclty.gisPrtFcltyCd + "-" + fclty.gisPrtFcltySeq + "</td></tr>";
          if (fclty.prtFcltyNm != null) {
            htmlInfo += "<tr><th>\uc2dc\uc124\uba85</th><td>" + fclty.prtFcltyNm + "</td></tr>";
          }
          if (fclty.gisAssetsLocplc != null) {
            var lnm = fclty.gisAssetsLnm + (fclty.gisAssetsLnmSub != null && fclty.gisAssetsLnmSub.length > 0) ? "-" + fclty.gisAssetsLnmSub : "";
            htmlInfo += "<tr><th>\uc18c\uc7ac\uc9c0</th><td>" + fclty.gisAssetsLocplc + " " + lnm + "</td></tr>";
          }
          if (fclty.filenmPhysicl != null) {
            htmlInfo += "<tr><th>\uc2dc\uc124\uc0ac\uc9c4</th><td><img src='" + EMD.context_root + "/cmm/getPfImage.do?physicalFileNm=" + fclty.filenmPhysicl + "' style='width:300px;' /></td></tr>";
          }
          htmlInfo += "<tr><td colSpan='2' style='align: right;'>";
          if (EMD.popupMenu["fcltyMechPopMngt"] != undefined) {
            htmlInfo += "<td><button id='btnPrtfcltyMngt' data-role='frmwrkButton' data-assets-prt-at-code='" + fclty.gisAssetsPrtAtCode + "' data-assets-cd='" + fclty.gisAssetsCd + "' data-assets-sub-cd='" + fclty.gisAssetsSubCd + "' data-prt-fclty-cd='" + fclty.gisPrtFcltyCd + "' data-prt-fclty-seq='" + fclty.gisPrtFcltySeq + "' data-prt-fclty-se='" + fclty.gisPrtFcltySe + "' >\uc2dc\uc124\uad00\ub9ac</button></td>";
          }
          if (EMD.popupMenu["fcltyMechPopInqire"] != undefined) {
            htmlInfo += "<td><button id='btnPrtfcltyInqire' data-role='frmwrkButton' data-assets-prt-at-code='" + fclty.gisAssetsPrtAtCode + "' data-assets-cd='" + fclty.gisAssetsCd + "' data-assets-sub-cd='" + fclty.gisAssetsSubCd + "' data-prt-fclty-cd='" + fclty.gisPrtFcltyCd + "' data-prt-fclty-seq='" + fclty.gisPrtFcltySeq + "' data-prt-fclty-se='" + fclty.gisPrtFcltySe + "' >\uc2dc\uc124\uc870\ud68c</button></td>";
          }
          htmlInfo += "</tr>";
          htmlInfo += "</tbody></table>";
          if (EMD.popup.prtFcltyInfo != null) {
            EMD.map.removePopup(EMD.popup.prtFcltyInfo);
            EMD.popup.prtFcltyInfo = null;
          }
          if (fclty.loCrdnt == null || fclty.laCrdnt == null) {
            if (fclty.lat != null && fclty.lng != null) {
              var latLng = new OpenLayers.LonLat(fclty.lng, fclty.lat);
              var proj = new OpenLayers.Projection("EPSG:4326");
              var newPos = latLng.transform(proj, new OpenLayers.Projection("EPSG:900913"));
              fclty.loCrdnt = newPos.lon;
              fclty.laCrdnt = newPos.lat;
            }
          }
          EMD.popup.prtFcltyInfo = new OpenLayers.Popup.FramedCloud("\uae30\uacc4\uc2dc\uc124\uc815\ubcf4", new OpenLayers.LonLat(fclty.loCrdnt, fclty.laCrdnt), new OpenLayers.Size(250, 150), htmlInfo, null, true);
          EMD.map.addPopup(EMD.popup.prtFcltyInfo);
          $("#btnPrtfcltyMngt").button({icons:{primary:"ui-icon-wrench"}, text:true}).click(function(event) {
            var title = "";
            var url = "";
            title = "\uae30\uacc4 \uc2dc\uc124 \uad00\ub9ac";
            url = EMD.popupMenu["fcltyMechPopMngt"];
            EMD.util.create_window("", title, url, null, {action:"prtFcltyMngt", gisPrtAtCode:$(this).data("assets-prt-at-code"), gisAssetsCd:$(this).data("assets-cd"), gisAssetsSubCd:$(this).data("assets-sub-cd"), gisPrtFcltyCd:$(this).data("prt-fclty-cd"), gisPrtFcltySeq:$(this).data("prt-fclty-seq"), gisPrtFcltySe:$(this).data("prt-fclty-se")});
          });
          $("#btnPrtfcltyInqire").button({icons:{primary:"ui-icon-folder-open"}, text:true}).click(function(event) {
            var title = "";
            var url = "";
            title = "\uae30\uacc4 \uc2dc\uc124 \uc870\ud68c";
            url = EMD.popupMenu["fcltyMechPopInqire"];
            EMD.util.create_window("", title, url, null, {action:"prtFcltyMngt", gisPrtAtCode:$(this).data("assets-prt-at-code"), gisAssetsCd:$(this).data("assets-cd"), gisAssetsSubCd:$(this).data("assets-sub-cd"), gisPrtFcltyCd:$(this).data("prt-fclty-cd"), gisPrtFcltySeq:$(this).data("prt-fclty-seq"), gisPrtFcltySe:$(this).data("prt-fclty-se")});
          });
          EMD.popup.prtFcltyInfo.updateSize();
          $(".prtFcltyInfo td > img").load(function(e) {
            var title = $("h2", EMD.popup.prtFcltyInfo.contentDiv);
            var t = $("table.prtFcltyInfo", EMD.popup.prtFcltyInfo.contentDiv);
            EMD.popup.prtFcltyInfo.updateSize();
          });
        }
      });
    });
    EMD.userLayer.fcltyMechMarker.events.register("featureunselected", null, function(e) {
      if (EMD.popup.prtFcltyInfo != null) {
        EMD.map.removePopup(EMD.popup.prtFcltyInfo);
        EMD.popup.prtFcltyInfo = null;
      }
    });
    EMD.userLayer.fcltyInfoTeleMarker = new OpenLayers.Layer.Vector("\uc815\ubcf4\ud1b5\uc2e0 \uc2dc\uc124", {minScale:5E3});
    EMD.userLayer.fcltyInfoTeleMarker.events.register("featureselected", null, function(e) {
      $.ajax({url:EMD.context_root + "/maps/fclty/gamFcltyInfo.do", type:"POST", dataType:"json", global:false, contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"gisAssetsPrtAtCode", value:e.feature.attributes.gisAssetsPrtAtCode}, {name:"gisAssetsCd", value:e.feature.attributes.gisAssetsCd}, {name:"gisAssetsSubCd", value:e.feature.attributes.gisAssetsSubCd}, {name:"gisPrtFcltyCd", value:e.feature.attributes.gisPrtFcltyCd}, {name:"gisPrtFcltySeq", value:e.feature.attributes.gisPrtFcltySeq}, 
      {name:"gisPrtFcltySe", value:e.feature.attributes.gisPrtFcltySe}]}).done(function(data) {
        if (data.resultCode == 0) {
          var htmlInfo = "<div class='prtFcltyInfo'><h2>\uc815\ubcf4\ud1b5\uc2e0\uc2dc\uc124\uc815\ubcf4</h2><table class='prtFcltyInfo'><tbody>";
          var fclty = data.result;
          if (fclty == null) {
            alert("\uc120\ud0dd\ud55c \uc2dc\uc124\uc758 \uc815\ubcf4\uac00 \uc5c6\uc2b5\ub2c8\ub2e4.");
          }
          htmlInfo += "<tr><th>\uc790\uc0b0\ucf54\ub4dc</th><td>" + fclty.gisAssetsCd + "-" + fclty.gisAssetsSubCd + "</td></tr>";
          htmlInfo += "<tr><th>\uc2dc\uc124\ucf54\ub4dc</th><td>" + fclty.gisPrtFcltyCd + "-" + fclty.gisPrtFcltySeq + "</td></tr>";
          if (fclty.prtFcltyNm != null) {
            htmlInfo += "<tr><th>\uc2dc\uc124\uba85</th><td>" + fclty.prtFcltyNm + "</td></tr>";
          }
          if (fclty.gisAssetsLocplc != null) {
            var lnm = fclty.gisAssetsLnm + (fclty.gisAssetsLnmSub != null && fclty.gisAssetsLnmSub.length > 0) ? "-" + fclty.gisAssetsLnmSub : "";
            htmlInfo += "<tr><th>\uc18c\uc7ac\uc9c0</th><td>" + fclty.gisAssetsLocplc + " " + lnm + "</td></tr>";
          }
          if (fclty.filenmPhysicl != null) {
            htmlInfo += "<tr><th>\uc2dc\uc124\uc0ac\uc9c4</th><td><img src='" + EMD.context_root + "/cmm/getPfImage.do?physicalFileNm=" + fclty.filenmPhysicl + "' style='width:300px;' /></td></tr>";
          }
          htmlInfo += "<tr><td colSpan='2' style='align: right;'>";
          if (EMD.popupMenu["fcltyInfoTechPopMngt"] != undefined) {
            htmlInfo += "<td><button id='btnPrtfcltyMngt' data-role='frmwrkButton' data-assets-prt-at-code='" + fclty.gisAssetsPrtAtCode + "' data-assets-cd='" + fclty.gisAssetsCd + "' data-assets-sub-cd='" + fclty.gisAssetsSubCd + "' data-prt-fclty-cd='" + fclty.gisPrtFcltyCd + "' data-prt-fclty-seq='" + fclty.gisPrtFcltySeq + "' data-prt-fclty-se='" + fclty.gisPrtFcltySe + "' >\uc2dc\uc124\uad00\ub9ac</button></td>";
          }
          if (EMD.popupMenu["fcltyInfoTechPopInqire"] != undefined) {
            htmlInfo += "<td><button id='btnPrtfcltyInqire' data-role='frmwrkButton' data-assets-prt-at-code='" + fclty.gisAssetsPrtAtCode + "' data-assets-cd='" + fclty.gisAssetsCd + "' data-assets-sub-cd='" + fclty.gisAssetsSubCd + "' data-prt-fclty-cd='" + fclty.gisPrtFcltyCd + "' data-prt-fclty-seq='" + fclty.gisPrtFcltySeq + "' data-prt-fclty-se='" + fclty.gisPrtFcltySe + "' >\uc2dc\uc124\uc870\ud68c</button></td>";
          }
          htmlInfo += "</tr>";
          htmlInfo += "</tbody></table>";
          if (EMD.popup.prtFcltyInfo != null) {
            EMD.map.removePopup(EMD.popup.prtFcltyInfo);
            EMD.popup.prtFcltyInfo = null;
          }
          if (fclty.loCrdnt == null || fclty.laCrdnt == null) {
            if (fclty.lat != null && fclty.lng != null) {
              var latLng = new OpenLayers.LonLat(fclty.lng, fclty.lat);
              var proj = new OpenLayers.Projection("EPSG:4326");
              var newPos = latLng.transform(proj, new OpenLayers.Projection("EPSG:900913"));
              fclty.loCrdnt = newPos.lon;
              fclty.laCrdnt = newPos.lat;
            }
          }
          EMD.popup.prtFcltyInfo = new OpenLayers.Popup.FramedCloud("\uc815\ubcf4\ud1b5\uc2e0\uc2dc\uc124\uc815\ubcf4", new OpenLayers.LonLat(fclty.loCrdnt, fclty.laCrdnt), new OpenLayers.Size(250, 150), htmlInfo, null, true);
          EMD.map.addPopup(EMD.popup.prtFcltyInfo);
          $("#btnPrtfcltyMngt").button({icons:{primary:"ui-icon-wrench"}, text:true}).click(function(event) {
            var title = "";
            var url = "";
            title = "\uc815\ubcf4\ud1b5\uc2e0 \uc2dc\uc124 \uad00\ub9ac";
            url = EMD.popupMenu["fcltyInfoTechPopMngt"];
            EMD.util.create_window("", title, url, null, {action:"prtFcltyMngt", gisPrtAtCode:$(this).data("assets-prt-at-code"), gisAssetsCd:$(this).data("assets-cd"), gisAssetsSubCd:$(this).data("assets-sub-cd"), gisPrtFcltyCd:$(this).data("prt-fclty-cd"), gisPrtFcltySeq:$(this).data("prt-fclty-seq"), gisPrtFcltySe:$(this).data("prt-fclty-se")});
          });
          $("#btnPrtfcltyInqire").button({icons:{primary:"ui-icon-folder-open"}, text:true}).click(function(event) {
            var title = "";
            var url = "";
            title = "\uc815\ubcf4\ud1b5\uc2e0 \uc2dc\uc124 \uc870\ud68c";
            url = EMD.popupMenu["fcltyInfoTechPopInqire"];
            EMD.util.create_window("", title, url, null, {action:"prtFcltyMngt", gisPrtAtCode:$(this).data("assets-prt-at-code"), gisAssetsCd:$(this).data("assets-cd"), gisAssetsSubCd:$(this).data("assets-sub-cd"), gisPrtFcltyCd:$(this).data("prt-fclty-cd"), gisPrtFcltySeq:$(this).data("prt-fclty-seq"), gisPrtFcltySe:$(this).data("prt-fclty-se")});
          });
          EMD.popup.prtFcltyInfo.updateSize();
          $(".prtFcltyInfo td > img").load(function(e) {
            var title = $("h2", EMD.popup.prtFcltyInfo.contentDiv);
            var t = $("table.prtFcltyInfo", EMD.popup.prtFcltyInfo.contentDiv);
            EMD.popup.prtFcltyInfo.updateSize();
          });
        }
      });
    });
    EMD.userLayer.fcltyInfoTeleMarker.events.register("featureunselected", null, function(e) {
      if (EMD.popup.prtFcltyInfo != null) {
        EMD.map.removePopup(EMD.popup.prtFcltyInfo);
        EMD.popup.prtFcltyInfo = null;
      }
    });
    EMD.userLayer.fcltyElecPowerMarker = new OpenLayers.Layer.Vector("\uc804\uae30 \uc2dc\uc124", {minScale:5E3});
    EMD.userLayer.fcltyElecPowerMarker.events.register("featureselected", null, function(e) {
      $.ajax({url:EMD.context_root + "/maps/fclty/gamFcltyInfo.do", type:"POST", dataType:"json", global:false, contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"gisAssetsPrtAtCode", value:e.feature.attributes.gisAssetsPrtAtCode}, {name:"gisAssetsCd", value:e.feature.attributes.gisAssetsCd}, {name:"gisAssetsSubCd", value:e.feature.attributes.gisAssetsSubCd}, {name:"gisPrtFcltyCd", value:e.feature.attributes.gisPrtFcltyCd}, {name:"gisPrtFcltySeq", value:e.feature.attributes.gisPrtFcltySeq}, 
      {name:"gisPrtFcltySe", value:e.feature.attributes.gisPrtFcltySe}]}).done(function(data) {
        if (data.resultCode == 0) {
          var htmlInfo = "<div class='prtFcltyInfo'><h2>\uc804\uae30\uc2dc\uc124\uc815\ubcf4</h2><table class='prtFcltyInfo'><tbody>";
          var fclty = data.result;
          if (fclty == null) {
            alert("\uc120\ud0dd\ud55c \uc2dc\uc124\uc758 \uc815\ubcf4\uac00 \uc5c6\uc2b5\ub2c8\ub2e4.");
          }
          htmlInfo += "<tr><th>\uc790\uc0b0\ucf54\ub4dc</th><td>" + fclty.gisAssetsCd + "-" + fclty.gisAssetsSubCd + "</td></tr>";
          htmlInfo += "<tr><th>\uc2dc\uc124\ucf54\ub4dc</th><td>" + fclty.gisPrtFcltyCd + "-" + fclty.gisPrtFcltySeq + "</td></tr>";
          if (fclty.prtFcltyNm != null) {
            htmlInfo += "<tr><th>\uc2dc\uc124\uba85</th><td>" + fclty.prtFcltyNm + "</td></tr>";
          }
          if (fclty.gisAssetsLocplc != null) {
            var lnm = fclty.gisAssetsLnm + (fclty.gisAssetsLnmSub != null && fclty.gisAssetsLnmSub.length > 0) ? "-" + fclty.gisAssetsLnmSub : "";
            htmlInfo += "<tr><th>\uc18c\uc7ac\uc9c0</th><td>" + fclty.gisAssetsLocplc + " " + lnm + "</td></tr>";
          }
          if (fclty.filenmPhysicl != null) {
            htmlInfo += "<tr><th>\uc2dc\uc124\uc0ac\uc9c4</th><td><img src='" + EMD.context_root + "/cmm/getPfImage.do?physicalFileNm=" + fclty.filenmPhysicl + "' style='width:300px;' /></td></tr>";
          }
          htmlInfo += "<tr><td colSpan='2' style='align: right;'>";
          if (EMD.popupMenu["fcltyElecPopMngt"] != undefined) {
            htmlInfo += "<td><button id='btnPrtfcltyMngt' data-role='frmwrkButton' data-assets-prt-at-code='" + fclty.gisAssetsPrtAtCode + "' data-assets-cd='" + fclty.gisAssetsCd + "' data-assets-sub-cd='" + fclty.gisAssetsSubCd + "' data-prt-fclty-cd='" + fclty.gisPrtFcltyCd + "' data-prt-fclty-seq='" + fclty.gisPrtFcltySeq + "' data-prt-fclty-se='" + fclty.gisPrtFcltySe + "' >\uc2dc\uc124\uad00\ub9ac</button></td>";
          }
          if (EMD.popupMenu["fcltyElecPopInqire"] != undefined) {
            htmlInfo += "<td><button id='btnPrtfcltyInqire' data-role='frmwrkButton' data-assets-prt-at-code='" + fclty.gisAssetsPrtAtCode + "' data-assets-cd='" + fclty.gisAssetsCd + "' data-assets-sub-cd='" + fclty.gisAssetsSubCd + "' data-prt-fclty-cd='" + fclty.gisPrtFcltyCd + "' data-prt-fclty-seq='" + fclty.gisPrtFcltySeq + "' data-prt-fclty-se='" + fclty.gisPrtFcltySe + "' >\uc2dc\uc124\uc870\ud68c</button></td>";
          }
          htmlInfo += "</tr>";
          htmlInfo += "</tbody></table>";
          if (EMD.popup.prtFcltyInfo != null) {
            EMD.map.removePopup(EMD.popup.prtFcltyInfo);
            EMD.popup.prtFcltyInfo = null;
          }
          if (fclty.loCrdnt == null || fclty.laCrdnt == null) {
            if (fclty.lat != null && fclty.lng != null) {
              var latLng = new OpenLayers.LonLat(fclty.lng, fclty.lat);
              var proj = new OpenLayers.Projection("EPSG:4326");
              var newPos = latLng.transform(proj, new OpenLayers.Projection("EPSG:900913"));
              fclty.loCrdnt = newPos.lon;
              fclty.laCrdnt = newPos.lat;
            }
          }
          EMD.popup.prtFcltyInfo = new OpenLayers.Popup.FramedCloud("\uc815\ubcf4\ud1b5\uc2e0\uc2dc\uc124\uc815\ubcf4", new OpenLayers.LonLat(fclty.loCrdnt, fclty.laCrdnt), new OpenLayers.Size(250, 150), htmlInfo, null, true);
          EMD.map.addPopup(EMD.popup.prtFcltyInfo);
          $("#btnPrtfcltyMngt").button({icons:{primary:"ui-icon-wrench"}, text:true}).click(function(event) {
            var title = "";
            var url = "";
            title = "\uc804\uae30 \uc2dc\uc124 \uad00\ub9ac";
            url = EMD.popupMenu["fcltyElecPopMngt"];
            EMD.util.create_window("", title, url, null, {action:"prtFcltyMngt", gisPrtAtCode:$(this).data("assets-prt-at-code"), gisAssetsCd:$(this).data("assets-cd"), gisAssetsSubCd:$(this).data("assets-sub-cd"), gisPrtFcltyCd:$(this).data("prt-fclty-cd"), gisPrtFcltySeq:$(this).data("prt-fclty-seq"), gisPrtFcltySe:$(this).data("prt-fclty-se")});
          });
          $("#btnPrtfcltyInqire").button({icons:{primary:"ui-icon-folder-open"}, text:true}).click(function(event) {
            var title = "";
            var url = "";
            title = "\uc804\uae30 \uc2dc\uc124 \uc870\ud68c";
            url = EMD.popupMenu["fcltyElecPopInqire"];
            EMD.util.create_window("", title, url, null, {action:"prtFcltyMngt", gisPrtAtCode:$(this).data("assets-prt-at-code"), gisAssetsCd:$(this).data("assets-cd"), gisAssetsSubCd:$(this).data("assets-sub-cd"), gisPrtFcltyCd:$(this).data("prt-fclty-cd"), gisPrtFcltySeq:$(this).data("prt-fclty-seq"), gisPrtFcltySe:$(this).data("prt-fclty-se")});
          });
          EMD.popup.prtFcltyInfo.updateSize();
          $(".prtFcltyInfo td > img").load(function(e) {
            var title = $("h2", EMD.popup.prtFcltyInfo.contentDiv);
            var t = $("table.prtFcltyInfo", EMD.popup.prtFcltyInfo.contentDiv);
            EMD.popup.prtFcltyInfo.updateSize();
          });
        }
      });
    });
    EMD.userLayer.fcltyElecPowerMarker.events.register("featureunselected", null, function(e) {
      if (EMD.popup.prtFcltyInfo != null) {
        EMD.map.removePopup(EMD.popup.prtFcltyInfo);
        EMD.popup.prtFcltyInfo = null;
      }
    });
    if ($DEBUG) {
      EMD.selectControl = new OpenLayers.Control.SelectFeature([EMD.lotarea, EMD.userLayer.gisAssetsCd, EMD.userLayer.gisPrtFcltyCd, EMD.userLayer.locArea, EMD.userLayer.assetStats, EMD.userLayer.fcltyConstMarker, EMD.userLayer.fcltySocMarker, EMD.userLayer.fcltyMechMarker, EMD.userLayer.fcltyInfoTeleMarker, EMD.userLayer.fcltyElecPowerMarker], {clickout:true, toggle:true, multiple:true, hover:false, multipleKey:"shiftKey"});
    } else {
      EMD.selectControl = new OpenLayers.Control.SelectFeature([EMD.userLayer.landCode, EMD.userLayer.gisAssetsCd, EMD.userLayer.assetsRent, EMD.userLayer.gisArchFclty, EMD.userLayer.gisCivilFclty, EMD.userLayer.gisMechFclty, EMD.userLayer.gisElecFclty, EMD.userLayer.gisTeleFclty, EMD.userLayer.locArea, EMD.userLayer.assetStats, EMD.userLayer.fcltyConstMarker, EMD.userLayer.fcltySocMarker, EMD.userLayer.fcltyMechMarker, EMD.userLayer.fcltyInfoTeleMarker, EMD.userLayer.fcltyElecPowerMarker], {clickout:true, 
      toggle:true, multiple:true, hover:false, multipleKey:"shiftKey"});
    }
    EMD.selectControl.handlers.feature.stopDown = false;
    EMD.info = new OpenLayers.Control.WMSGetFeatureInfo({url:"http://demo.opengeo.org/geoserver/wms", title:"\ud56d\ub9cc\uc2dc\uc124 \uc18d\uc131", queryVisible:true, eventListeners:{getfeatureinfo:function(event) {
      map.addPopup(new OpenLayers.Popup.FramedCloud("\ud56d\ub9cc\uc2dc\uc124 \uc18d\uc131", map.getLonLatFromPixel(event.xy), new OpenLayers.Size(250, 150), event.text, null, true));
    }}});
    EMD.wfs = new OpenLayers.Layer.Vector("Edit Features", {projection:new OpenLayers.Projection("EPSG:5186"), displayInLayerSwitcher:false});
    EMD.controls.measureDist = new OpenLayers.Control.Button({title:"\uac70\ub9ac\uce21\uc815", trigger:function() {
      var mapControls = EMD.measureControls;
      for (var i in mapControls) {
        if (mapControls[i].id == "measureDist") {
          if (!mapControls[i].active) {
            mapControls[i].activate();
          } else {
            mapControls[i].deactivate();
          }
        } else {
          mapControls[i].deactivate();
        }
      }
    }, type:OpenLayers.Control.TYPE_TOGGLE, displayClass:"olControlMeasureDist", eventListeners:{activate:function() {
      var mapControls = EMD.measureControls;
      for (var i in mapControls) {
        if (mapControls[i].id == "measureDist") {
          mapControls[i].activate();
        }
      }
      EMD.controls.measureArea.deactivate();
    }, deactivate:function() {
      var mapControls = EMD.measureControls;
      for (var i in mapControls) {
        if (mapControls[i].id == "measureDist") {
          mapControls[i].deactivate();
        }
      }
    }}});
    EMD.controls.measureArea = new OpenLayers.Control.Button({title:"\uba74\uc801\uce21\uc815", trigger:function() {
      var mapControls = EMD.measureControls;
      for (var i in mapControls) {
        if (mapControls[i].id == "measureArea") {
          if (!mapControls[i].active) {
            mapControls[i].activate();
          } else {
            mapControls[i].deactivate();
          }
        } else {
          mapControls[i].deactivate();
        }
      }
    }, type:OpenLayers.Control.TYPE_TOGGLE, displayClass:"olControlMeasureArea", eventListeners:{activate:function() {
      var mapControls = EMD.measureControls;
      for (var i in mapControls) {
        if (mapControls[i].id == "measureArea") {
          mapControls[i].activate();
        }
      }
      EMD.controls.measureDist.deactivate();
    }, deactivate:function() {
      var mapControls = EMD.measureControls;
      for (var i in mapControls) {
        if (mapControls[i].id == "measureArea") {
          mapControls[i].deactivate();
        }
      }
    }}});
    EMD.measureControls = [new OpenLayers.Control.DynamicMeasure(OpenLayers.Handler.PathMeasureCustom, {id:"measureDist", persist:true, handlerOptions:{layerOptions:{styleMap:styleMap}}}), new OpenLayers.Control.DynamicMeasure(OpenLayers.Handler.PolygonMeasureCustom, {id:"measureArea", persist:true, handlerOptions:{layerOptions:{styleMap:styleMap}}})];
    EMD.measurePanel = new OpenLayers.Control.Panel({displayClass:"mapMeasureToolBar"});
    EMD.measurePanel.addControls([EMD.controls.measureArea, EMD.controls.measureDist]);
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
    EMD.userLayer.fcltyConstMarker.setVisibility(true);
    EMD.userLayer.fcltySocMarker.setVisibility(true);
    EMD.userLayer.fcltyMechMarker.setVisibility(true);
    EMD.userLayer.fcltyInfoTeleMarker.setVisibility(true);
    EMD.userLayer.fcltyElecPowerMarker.setVisibility(true);
    EMD.legendWin = new dhtmlXTabBar({parent:"legendPanel", skin:"dhx_skyblue", mode:"top", align:"left", close_button:false, content_zone:true, onload:function() {
    }, arrows_mode:"auto"});
    $("#legendPanel").width(200);
    $("#legendPanel").height(300);
    $("#legendPanel").hide();
    EMD.legendWin.setSizes();
    EMD.legendWin._counts = 0;
    EMD.userLayer.gisArchFclty.events.register("visibilitychanged", null, function(e) {
      var legendTab = EMD.legendWin.tabs("archFclty");
      if (e.object.visibility) {
        $("#legendPanel").show();
        if (legendTab == undefined || legendTab == null) {
          EMD.legendWin.addTab("archFclty", "\uac74\ucd95\uc2dc\uc124 \ubc94\ub840", null, null, true);
          legendTab = EMD.gis.addLegendLayer(e.object, "archFclty");
          EMD.legendWin._counts++;
        }
        legendTab.show(true);
      } else {
        if (legendTab != undefined && legendTab != null) {
          legendTab.close();
          EMD.legendWin._counts--;
          if (EMD.legendWin._counts <= 0) {
            EMD.legendWin._counts = 0;
            $("#legendPanel").hide();
          }
        }
      }
    });
    EMD.userLayer.gisMechFclty.events.register("visibilitychanged", null, function(e) {
      var legendTab = EMD.legendWin.tabs("mechGroup");
      if (e.object.visibility) {
        $("#legendPanel").show();
        if (legendTab == undefined || legendTab == null) {
          EMD.legendWin.addTab("mechGroup", "\uae30\uacc4\uc2dc\uc124 \ubc94\ub840", null, null, true);
          legendTab = EMD.gis.addLegendLayer(e.object, "mechGroup");
          EMD.legendWin._counts++;
        }
        legendTab.show(true);
      } else {
        if (legendTab != undefined && legendTab != null) {
          legendTab.close();
          EMD.legendWin._counts--;
          if (EMD.legendWin._counts <= 0) {
            EMD.legendWin._counts = 0;
            $("#legendPanel").hide();
          }
        }
      }
    });
    EMD.userLayer.gisElecFclty.events.register("visibilitychanged", null, function(e) {
      var legendTab = EMD.legendWin.tabs("elecGroup");
      if (e.object.visibility) {
        $("#legendPanel").show();
        if (legendTab == undefined || legendTab == null) {
          EMD.legendWin.addTab("elecGroup", "\uc804\uae30\uc2dc\uc124 \ubc94\ub840", null, null, true);
          legendTab = EMD.gis.addLegendLayer(e.object, "elecGroup");
          EMD.legendWin._counts++;
        }
        legendTab.show(true);
      } else {
        if (legendTab != undefined && legendTab != null) {
          legendTab.close();
          EMD.legendWin._counts--;
          if (EMD.legendWin._counts <= 0) {
            EMD.legendWin._counts = 0;
            $("#legendPanel").hide();
          }
        }
      }
    });
    EMD.userLayer.gisTeleFclty.events.register("visibilitychanged", null, function(e) {
      var legendTab = EMD.legendWin.tabs("infoGroup");
      if (e.object.visibility) {
        $("#legendPanel").show();
        if (legendTab == undefined || legendTab == null) {
          EMD.legendWin.addTab("infoGroup", "\uc815\ubcf4\ud1b5\uc2e0\uc2dc\uc124 \ubc94\ub840", null, null, true);
          legendTab = EMD.gis.addLegendLayer(e.object, "infoGroup");
          EMD.legendWin._counts++;
        }
        legendTab.show(true);
      } else {
        if (legendTab != undefined && legendTab != null) {
          legendTab.close();
          EMD.legendWin._counts--;
          if (EMD.legendWin._counts <= 0) {
            EMD.legendWin._counts = 0;
            $("#legendPanel").hide();
          }
        }
      }
    });
    EMD.userLayer.gisCivilFclty.events.register("visibilitychanged", null, function(e) {
      var legendTab = EMD.legendWin.tabs("civilGroup");
      if (e.object.visibility) {
        $("#legendPanel").show();
        if (legendTab == undefined || legendTab == null) {
          EMD.legendWin.addTab("civilGroup", "\ud1a0\ubaa9\uc2dc\uc124 \ubc94\ub840", null, null, true);
          legendTab = EMD.gis.addLegendLayer(e.object, "civilGroup");
          EMD.legendWin._counts++;
        }
        legendTab.show(true);
      } else {
        if (legendTab != undefined && legendTab != null) {
          legendTab.close();
          EMD.legendWin._counts--;
          if (EMD.legendWin._counts <= 0) {
            EMD.legendWin._counts = 0;
            $("#legendPanel").hide();
          }
        }
      }
    });
    if (EMD.userinfo.mngFcltyCd == "A") {
      EMD.userLayer.gisArchFclty.setVisibility(true);
    }
    if (EMD.userinfo.mngFcltyCd == "M") {
      EMD.userLayer.gisMechFclty.setVisibility(true);
    }
    if (EMD.userinfo.mngFcltyCd == "E") {
      EMD.userLayer.gisElecFclty.setVisibility(true);
    }
    if (EMD.userinfo.mngFcltyCd == "I") {
      EMD.userLayer.gisTeleFclty.setVisibility(true);
    }
    if (EMD.userinfo.mngFcltyCd == "C") {
      EMD.userLayer.gisCivilFclty.setVisibility(true);
    }
    if ($DEBUG) {
      EMD.lotarea.setVisibility(true);
      EMD.map.addLayers([basemap, EMD.userLayer.custLand, EMD.userLayer.wharfNm, EMD.userLayer.fcltyNm, EMD.lotarea, EMD.userLayer.landCode, EMD.userLayer.gisAssetsCd, EMD.userLayer.gisArchFclty, EMD.userLayer.gisCivilFclty, EMD.userLayer.gisMechFclty, EMD.userLayer.gisElecFclty, EMD.userLayer.gisTeleFclty, EMD.userLayer.locArea, EMD.userLayer.assetStats, EMD.userLayer.assetsRentDetail, EMD.wfs, EMD.userLayer.fcltyConstMarker, EMD.userLayer.fcltySocMarker, EMD.userLayer.fcltyMechMarker, EMD.userLayer.fcltyInfoTeleMarker, 
      EMD.userLayer.fcltyElecPowerMarker]);
    } else {
      var layers = [basemap, EMD.userLayer.custLand, EMD.userLayer.wharfNm, EMD.userLayer.fcltyNm, EMD.userLayer.landCode, EMD.userLayer.gisAssetsCd, EMD.userLayer.gisArchFclty, EMD.userLayer.gisCivilFclty, EMD.userLayer.gisMechFclty, EMD.userLayer.gisElecFclty, EMD.userLayer.gisTeleFclty, EMD.userLayer.assetsRent, EMD.userLayer.assetStats];
      EMD.map.addLayers(layers);
    }
    var layerSwitcer = new OpenLayers.Control.LayerSwitcher;
    EMD.map.addControls([EMD.selectControl, layerSwitcer, new OpenLayers.Control.MousePosition({prefix:"\uc9c0\ub3c4 \uc88c\ud45c : ", separator:" | ", numDigits:6, displayProjection:new OpenLayers.Projection("EPSG:5186")}), new OpenLayers.Control.Scale, EMD.measurePanel]);
    EMD.map.addControls(EMD.measureControls);
    EMD.selectControl.activate();
    layerSwitcer.dataLbl.innerText = "\ub808\uc774\uc5b4 \ubaa9\ub85d";
    EMD.measurePanel.activate();
    EMD.map.events.register("featureover", EMD.map, function(e) {
    });
    EMD.map.events.register("mousedown", EMD.map, function(e) {
      if (EMD.dragging) {
      }
    });
    EMD.map.events.register("mousemove", EMD.map, function(e) {
      if (EMD.dragging) {
        $(EMD.drag_handle).trigger({type:e.type, altKey:e.altKey, clientX:e.clientX, clientY:e.clientY, ctrlKey:e.ctrlKey, detail:e.detail, keyCode:e.keyCode, layerX:e.layerX, layerY:e.layerY, metaKey:e.metaKey, offsetX:e.offsetX, offsetY:e.offsetY, pageX:e.pageX, pageY:e.pageY, screenX:e.screenX, screenY:e.screenY, shiftKey:e.shiftKey, webkitMovementX:e.webkitMovementX, webkitMovementY:e.webkitMovementY, x:e.x, y:e.y, xy:e.xy});
      }
    });
    EMD.map.events.register("mouseup", EMD.map, function(e) {
      EMD.util.clear_active();
      if (EMD.dragging) {
      }
    });
    EMD.map.events.register("changelayer", EMD.map, function(e) {
      if (EMD.userLayer.gisElecFclty == e.layer) {
        if (e.layer.visibility) {
          if (e.layer["legendPanel"] == null) {
          }
        } else {
        }
      }
    });
    var center = new OpenLayers.LonLat(14211246, 4145825);
    EMD.map.setCenter(center, 13);
  }, ui_init:function() {
  }, fileupload_init:function() {
    $("#fileupload").fileupload({url:EMD.context_root + "/upload/genericMulti.do", autoUpload:true});
    $("#fileupload").fileupload("option", "redirect", window.location.href.replace(/\/[^\/]*$/, "/cors/result.html?%s"));
    $("#pfPhotoupload").fileupload({url:EMD.context_root + "/upload/pfUploadMulti.do", autoUpload:true});
    $("#pfPhotoupload").fileupload("option", "redirect", window.location.href.replace(/\/[^\/]*$/, "/cors/result.html?%s"));
    $("#xlsfileupload").fileupload({autoUpload:true});
    $("#xlsfileupload").fileupload("option", "redirect", window.location.href.replace(/\/[^\/]*$/, "/cors/result.html?%s"));
  }, loadCompleteWindow:function() {
    var i, cv = [];
    var name = "ygpa_popup_c";
    var ca = document.cookie.split(";");
    for (i = 0;i < ca.length;i++) {
      var c = ca[i];
      while (c.charAt(0) == " ") {
        c = c.substring(1);
      }
      if (c.indexOf(name) != -1) {
        cv[cv.length] = c.substring(name.length + 2, c.length).split(";")[0];
      }
    }
    for (var j = 0;j < EMD.loadAtStartWindow.length;j++) {
      if (cv.length != 0) {
        for (i = 0;i < cv.length;i++) {
          if (cv[i] == EMD.loadAtStartWindow[j].url) {
            break;
          }
        }
        if (i != cv.length) {
          continue;
        }
      }
      EMD.util.create_window(EMD.loadAtStartWindow[j].progrmFileNm, EMD.loadAtStartWindow[j].menuNm, EMD.loadAtStartWindow[j].url, null, {action:"loadStart"});
    }
  }}, closeWindow:function(win) {
    var dock_id = "#" + win.attr("id").replace("window", "dock");
    $("#" + win.attr("id")).hide(function() {
      $(this).remove();
    });
    $(dock_id).hide("fast");
    $(dock_id).remove();
  }, gis:{openPopup:function(feature, url, params, func) {
    $.ajax({type:"POST", url:EMD.context_root + url, data:params, dataType:"html", feature:feature, popupFunction:func, success:function(data, textStatus, jqXHR) {
      var mapPopupId = "__framePopup_" + EMD.mapPopupId++;
      var popup = new OpenLayers.Popup.FramedCloud("popup", this.feature.geometry.bounds.getCenterLonLat(), new OpenLayers.Size(250, 150), "<div id='" + mapPopupId + "'></div>", null, true, function() {
        var ret = true;
        if (this.module.onClose != null) {
          ret = this.module.onClosePopup(this);
        }
        if (ret) {
          EMD.map.removePopup(this);
        }
      });
      EMD.map.addPopup(popup);
      var html = $.parseHTML(data, null, true);
      $("#" + mapPopupId).append(html);
      this.feature.popup = popup;
      if (popupInfoModule != null) {
        $("#" + mapPopupId)[0].module = popupInfoModule;
        popup.module = popupInfoModule;
        EMD.util.modify_window(popupInfoModule, mapPopupId);
        popupInfoModule.setModuleId(mapPopupId);
        popupInfoModule.setPopup(popup);
        popupInfoModule.setFeature(this.feature);
        popupInfoModule.loadComplete();
      }
      if (this.popupFunction != null) {
        this.popupFunction(this.feature);
      }
      popup.updateSize();
    }});
  }, closeAllPopup:function(layerName) {
    var layer = EMD.gis.getUserLayer(layerName);
    for (var i = 0;i < len;i++) {
      feature = layer.features[i];
      if (feature && feature.popup != null) {
        EMD.map.removePopup(feature.popup);
      }
    }
  }, openGisPage:function(name) {
    $("#__tempDiv").empty();
    var form = document.createElement("form");
    $(form).attr("id", "__InnerGisLayer");
    for (var pn in params) {
      var e = document.createElement("input");
      $(e).attr("name", pn);
      $(e).val(params[pn]);
      $(form).append(e);
    }
    $(form).attr("action", EMD.context_root + "/openGisPage.do?name=" + name);
    $("#__tempDiv").append(form);
    var win = window.open("", "newPage", "width=800, height=600, menubar=no,status=no,scrollbars=yes");
    $(win).on("");
    var module = this;
    form.target = "newPage";
    form.submit();
  }, storeLayer:function(layer) {
    EMD.saveStrategy.save();
    EMD.refreshStrategy.refresh();
  }, refreshLayer:function(layer) {
    EMD.refreshStrategy.refresh();
  }, addLegendLayer:function(layer, legend) {
    var l = "";
    var types = ["Point", "Line", "Polygon"];
    $.each(layer.styleMap.styles["default"].rules, function() {
      var type, haveType;
      if (this["title"] == undefined) {
        return;
      }
      var symbolizers = this.symbolizers;
      if (!symbolizers) {
        var symbolizer = this.symbolizer;
        for (var i = 0, len = types.length;i < len;++i) {
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
        outer: for (var i = 0, ii = types.length;i < ii;++i) {
          type = types[i];
          Type = OpenLayers.Symbolizer[type];
          if (Type) {
            for (var j = 0, jj = symbolizers.length;j < jj;++j) {
              if (symbolizers[j] instanceof Type) {
                haveType = true;
                break outer;
              }
            }
          }
        }
      }
      EMD.symbols[this.id] = {symbolType:haveType ? type : this.symbolType, symbolizers:symbolizers};
      l += "<li><div class='legendItem'><span class='symbol " + EMD.symbols[this.id].symbolType + "' id='" + this.id + "'></span><span class='title'>" + this.title + "</span></div></li>";
    });
    var tab = EMD.legendWin.tabs(legend);
    tab.attachHTMLString("<div class='legend'><ul>" + l + "</ul></div>");
    $("div.legend").find("span.symbol").each(function() {
      var renderer = null;
      var renderers = ["SVG", "VML", "Canvas"];
      var feature = new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Polygon([new OpenLayers.Geometry.LinearRing([new OpenLayers.Geometry.Point(-8, -4), new OpenLayers.Geometry.Point(-6, -6), new OpenLayers.Geometry.Point(6, -6), new OpenLayers.Geometry.Point(8, -4), new OpenLayers.Geometry.Point(8, 4), new OpenLayers.Geometry.Point(6, 6), new OpenLayers.Geometry.Point(-6, 6), new OpenLayers.Geometry.Point(-8, 4)])]));
      for (var i = 0, len = renderers.length;i < len;++i) {
        var Renderer = OpenLayers.Renderer[renderers[i]];
        if (Renderer && Renderer.prototype.supported()) {
          renderer = new Renderer(this, {map:{getResolution:function() {
            return 1;
          }}});
          var gb = feature.geometry.getBounds();
          var gw = gb.getWidth();
          var gh = gb.getHeight();
          var resolution = null;
          if (!resolution) {
            resolution = Math.max(gw / $(this).width() || 0, gh / $(this).height() || 0) || 1;
          }
          var width = Math.max($(this).width(), gw / resolution);
          var height = Math.max($(this).height(), gh / resolution);
          var center = gb.getCenterPixel();
          var bhalfw = width * resolution / 2;
          var bhalfh = height * resolution / 2;
          var bounds = new OpenLayers.Bounds(center.x - bhalfw, center.y - bhalfh, center.x + bhalfw, center.y + bhalfh);
          renderer.setSize(new OpenLayers.Size(Math.round(width), Math.round(height)));
          renderer.setExtent(bounds, true);
          renderer.resolution = resolution;
          renderer.clear();
          break;
        }
      }
      if (renderer == null) {
        return;
      }
      switch(EMD.symbols[this.id].symbolType) {
        case "Point":
          var symbol = new OpenLayers.Symbolizer(EMD.symbols[this.id].symbolizers[0]);
          symbol.label = this.title;
          renderer.drawFeature(new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Point(4, -6)), symbol);
          break;
        case "Line":
          var symbol = new OpenLayers.Symbolizer(EMD.symbols[this.id].symbolizers[0]);
          symbol.label = this.title;
          renderer.drawFeature(new OpenLayers.Feature.Vector(new OpenLayers.Geometry.LineString([new OpenLayers.Geometry.Point(-8, -1), new OpenLayers.Geometry.Point(16, -1)])), symbol);
          break;
        case "Polygon":
          var symbol = new OpenLayers.Symbolizer(EMD.symbols[this.id].symbolizers[0]);
          symbol.label = this.title;
          renderer.drawFeature(new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Polygon([new OpenLayers.Geometry.LinearRing([new OpenLayers.Geometry.Point(-6, -4), new OpenLayers.Geometry.Point(-4, -6), new OpenLayers.Geometry.Point(14, -6), new OpenLayers.Geometry.Point(16, -4), new OpenLayers.Geometry.Point(16, 1), new OpenLayers.Geometry.Point(14, 3), new OpenLayers.Geometry.Point(-4, 3), new OpenLayers.Geometry.Point(-6, 1)])]), {FCLTY_NM:this.title}), symbol);
          break;
      }
    });
    return tab;
  }, getLotcode:function(layerName, pnu, retfunc, opt) {
    var layer = EMD.gis.getUserLayer(layerName);
    var filter = new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"pnu", value:pnu}), new OpenLayers.Filter.Spatial({type:OpenLayers.Filter.Spatial.BBOX, property:"BBOX", value:new OpenLayers.Bounds(14132415, 4494156, 14132417, 4494158)})]});
    var wfs_options = {url:"http://apis.vworld.kr/2ddata/cadastral/data?apiKey=" + EMD.vworldKey + "&domain=" + EMD.serverUrl, params:{request:"GetFeature", service:"wfs", version:"1.1.0", typeName:"LP_PA_CBND_BUBUN", srsName:"EPSG:5186", filter:"pnu:=:" + pnu}, format:new OpenLayers.Format.GML({geometryName:"AG_GEOM"})};
    var protocol = new OpenLayers.Protocol.HTTP(wfs_options);
    var resp = protocol.read({callback:function(resp) {
      if (resp.error) {
        this.done_func(null);
        return-1;
      }
      resp.doneFunc(resp.features, resp.opt);
    }});
    resp.layer = layer;
    resp.doneFunc = retfunc;
    resp.opt = opt;
  }, getStatsColor:function(feature) {
    return "#ee6666";
  }, getStatsLabel:function(feature) {
    return feature["PRT_FCLTY_NM"];
  }, _getIndexBaseURL:function(bounds) {
    var res = this.map.getResolution();
    var x = Math.round((bounds.left - this.maxExtent.left) / (res * this.tileSize.w));
    var y = Math.round((this.maxExtent.top - bounds.top) / (res * this.tileSize.h));
    var z = this.map.getZoom();
    var limit = Math.pow(2, z);
    if (y < 0 || y >= limit) {
      return vworldUrlsExt.blankimage;
    } else {
      x = (x % limit + limit) % limit;
      return this.url + z + "/" + x + "/" + y + "." + this.type;
    }
  }, _adjustWindow:null, _initGisAdjust:function() {
    _adjustWindow = $(".window_stack");
    EMD.util.window_hide_all();
    EMD.selectControl.deactivate();
    if (EMD.popup.prtFcltyInfo) {
      EMD.map.removePopup(EMD.popup.prtFcltyInfo);
    }
  }, _closeGisAdjust:function() {
    EMD.selectControl.activate();
    EMD.util.window_show(_adjustWindow);
  }, modifyFeature:null, saveFeatures:function() {
    EMD.saveStrategy.save();
  }, addPrtFcltyMarkerClick:OpenLayers.Class(OpenLayers.Control, {defaultHandlerOptions:{"single":true, "double":false, "pixelTolerance":0, "stopSingle":false, "stopDouble":false}, initialize:function(options) {
    this.handlerOptions = OpenLayers.Util.extend({}, this.defaultHandlerOptions);
    OpenLayers.Control.prototype.initialize.apply(this, arguments);
    this.handler = new OpenLayers.Handler.Click(this, {"click":this.trigger}, this.handlerOptions);
  }, trigger:function(e) {
    var lonlat = EMD.map.getLonLatFromPixel(e.xy);
    EMD.map_value.fclty.laCrdnt = lonlat.lat;
    EMD.map_value.fclty.loCrdnt = lonlat.lon;
    EMD.map_state = null;
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
    if (EMD.map_callback != null) {
      EMD.map_callback(EMD.map_value.fclty);
    }
  }}), _markerClick:null, addPrtFcltyMarker:function(prtFclty, callbackfunc) {
    EMD.map_state = "addPrtFcltyMarker";
    EMD.map_value = {fclty:prtFclty};
    EMD.map_callback = callbackfunc;
    EMD.gis._initGisAdjust();
    EMD.gis._markerClick = new EMD.gis.addPrtFcltyMarkerClick;
    EMD.map.addControl(EMD.gis._markerClick);
    EMD.gis._markerClick.activate();
  }, removePrtFclty:function(prtFclty) {
    var features = null;
    switch(prtFclty.gisPrtFcltySe) {
      case "C":
        features = EMD.userLayer.fcltyConstMarker.getFeatureBy("_fcltyObjCode", prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
        EMD.userLayer.fcltyConstMarker.removeFeatures(features);
        break;
      case "M":
        features = EMD.userLayer.fcltyMechMarker.getFeatureBy("_fcltyObjCode", prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
        EMD.userLayer.fcltyMechMarker.removeFeatures(features);
        break;
      case "E":
        features = EMD.userLayer.fcltyInfoTeleMarker.getFeatureBy("_fcltyObjCode", prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
        EMD.userLayer.fcltyInfoTeleMarker.removeFeatures(features);
        break;
      case "S":
        features = EMD.userLayer.fcltySocMarker.getFeatureBy("_fcltyObjCode", prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
        EMD.userLayer.fcltySocMarker.removeFeatures(features);
        break;
      case "P":
        features = EMD.userLayer.fcltyElecPowerMarker.getFeatureBy("_fcltyObjCode", prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
        EMD.userLayer.fcltyElecPowerMarker.removeFeatures(features);
        break;
    }
    EMD.gis._markerClick.deactivate();
  }, removeFeatures:function(module, layerName, features) {
    var layer = EMD.gis.getUserLayer(layerName);
    EMD.selectControl.unselectAll();
    $.each(features, function() {
      if (this.popup != undefined) {
        EMD.map.removePopup(this.popup);
      }
      this.state = OpenLayers.State.DELETE;
    });
    var protocol = EMD.gis.getLayerProtocol(layer);
    resp = protocol.commit(features, {callback:function(resp) {
      if (resp.error) {
        console.log("error");
        return-1;
      }
      resp.layer.removeFeatures(resp.reqFeatures);
      alert("\uc601\uc5ed\uc774 \uc0ad\uc81c \ub418\uc5c8\uc2b5\ub2c8\ub2e4.");
    }});
    resp.module = module;
    resp.layer = layer;
  }, drawPrtFclty:function(prtFclty) {
    var icon;
    switch(prtFclty.gisPrtFcltyCd) {
      case "55":
        icon = EMD.context_root + "/images/egovframework/ygpa/gam/maps/map_icon/dam.png";
        break;
      default:
        icon = EMD.context_root + "/images/egovframework/ygpa/gam/maps/map_icon/photo.png";
        break;
    }
    if (prtFclty.loCrdnt == null || prtFclty.laCrdnt == null) {
      if (prtFclty.lat != null && prtFclty.lng != null) {
        var latLng = new OpenLayers.LonLat(prtFclty.lng, prtFclty.lat);
        var proj = new OpenLayers.Projection("EPSG:4326");
        var newPos = latLng.transform(proj, new OpenLayers.Projection("EPSG:900913"));
        prtFclty.loCrdnt = newPos.lon;
        prtFclty.laCrdnt = newPos.lat;
      }
    }
    var marker = new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Point(prtFclty.loCrdnt, prtFclty.laCrdnt), {gisAssetsPrtAtCode:prtFclty.gisAssetsPrtAtCode, gisAssetsCd:prtFclty.gisAssetsCd, gisAssetsSubCd:prtFclty.gisAssetsSubCd, gisPrtFcltyCd:prtFclty.gisPrtFcltyCd, gisPrtFcltySeq:prtFclty.gisPrtFcltySeq, gisPrtFcltySe:prtFclty.gisPrtFcltySe, fcltyNm:prtFclty.prtFcltyNm}, {externalGraphic:icon, graphicHeight:37, graphicWidth:32, graphicXOffset:-18, graphicYOffset:-37, labelOutlineColor:"white", 
    labelOutlineWidth:3, label:prtFclty.prtFcltyNm});
    marker._fcltyObjCode = prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq;
    switch(prtFclty.gisPrtFcltySe) {
      case "C":
        EMD.userLayer.fcltyConstMarker.addFeatures(marker);
        break;
      case "M":
        EMD.userLayer.fcltyMechMarker.addFeatures(marker);
        break;
      case "E":
        EMD.userLayer.fcltyInfoTeleMarker.addFeatures(marker);
        break;
      case "S":
        EMD.userLayer.fcltySocMarker.addFeatures(marker);
        break;
      case "P":
        EMD.userLayer.fcltyElecPowerMarker.addFeatures(marker);
        break;
    }
  }, _getLayerFilter:function(layer, obj) {
    var filter = null;
    if (layer == EMD.userLayer.gisAssetsCd) {
      filter = new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRT_CD", value:obj.gisAssetsPrtAtCode}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"ASSETS_CD", value:obj.gisAssetsCd}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"ASSETS_SCD", value:obj.gisAssetsSubCd})]});
    } else {
      if (layer == EMD.userLayer.assetsRent) {
        if (obj["assetsUsageSeq"] == undefined) {
          filter = new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRT_AT_CD", value:obj.prtAtCode}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"MNG_YEAR", value:obj.mngYear}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"MNG_NO", value:obj.mngNo}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, 
          property:"MNG_CNT", value:obj.mngCnt})]});
        } else {
          filter = new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRT_AT_CD", value:obj.prtAtCode}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"MNG_YEAR", value:obj.mngYear}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"MNG_NO", value:obj.mngNo}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, 
          property:"MNG_CNT", value:obj.mngCnt}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"ASSETS_USAGE_SEQ", value:obj.assetsUsageSeq})]});
        }
      }
    }
    return filter;
  }, _getLayerprotocol:function(layer) {
    var protocol = null;
    if (layer == EMD.userLayer.gisAssetsCd) {
      protocol = EMD.protocols.gisAssetsCd;
    } else {
      if (layer == EMD.userLayer.assetsRent) {
        protocol = EMD.protocols.assetsRent;
      } else {
        if (layer == EMD.userLayer.assetsRentDetail) {
          protocol = EMD.protocols.assetsRentDetail;
        }
      }
    }
    return protocol;
  }, _findFeatures:function(layer, obj) {
    var foundFeatures = [];
    for (var i = 0;i < layer.features.length;i++) {
      feature = layer.features[i];
      if (feature && feature.attributes) {
        if (EMD.gis.compareLayerObject(layer, feature, obj)) {
          foundFeatures.push(feature);
          break;
        }
      }
    }
    return foundFeatures;
  }, findFeatureData:function(module, layerName, obj, opt) {
    var layer = EMD.gis.getUserLayer(layerName);
    if (layer == null) {
      return;
    }
    var filter = EMD.gis._getLayerFilter(obj);
    var protocol = EMD.gis._getLayerprotocol(layer);
    var zoomTo = zoomToExtent == null ? false : zoomToExtent;
    var refreshData = refresh == undefined ? false : refresh;
    if (opt != undefined) {
      opt.zoomToExtent = opt.zoomToExtent == null ? false : zoomToExtent;
      opt.refresh = opt.refresh == undefined ? false : refresh;
    } else {
      opt = {zoomTo:true, refreshData:true};
    }
    var foundFeatures = EMD.gis._findFeatures(layer, obj);
    if (foundFeatures.length == 0 || refreshData) {
      protocol.read({filter:filter, _callback:opt.callback, opt:opt, callback:function(resp) {
        if (resp.error) {
          console.log("find error");
          return 0;
        }
        if (resp.features.length > 0) {
          var ext;
          var proj = new OpenLayers.Projection("EPSG:5186");
          if (resp.features.length != undefined) {
            $.each(resp.features, function() {
              var polygon = this.geometry;
              this.geometry = polygon.transform(proj, EMD.map.getProjection());
            });
          } else {
            var polygon = resp.features.geometry;
            this.geometry = polygon.transform(proj, EMD.map.getProjection());
          }
          if (this.opt.zoomTo) {
            ext = resp.features[0].geometry.getBounds().clone();
            for (var i = 1;i < resp.features.length;i++) {
              ext.extend(resp.features[i].geometry.getBounds());
            }
            EMD.map.zoomToExtent(ext);
          }
        }
      }});
    } else {
      if (zoomTo) {
        ext = resp.features[0].geometry.getBounds().clone();
        for (var i = 1;i < resp.features.length;i++) {
          ext.extend(resp.features[i].geometry.getBounds());
        }
        EMD.map.zoomToExtent(ext);
      }
    }
  }, selectFeatureData:function(module, layerName, obj, zoomToExtent, refresh) {
    var layer = EMD.gis.getUserLayer(layerName);
    if (layer == null) {
      return;
    }
    var filter = EMD.gis._getLayerFilter(obj);
    var protocol = EMD.gis._getLayerprotocol(layer);
    var zoomTo = zoomToExtent == null ? false : zoomToExtent;
    var refreshData = refresh == undefined ? false : refresh;
    var foundFeatures = EMD.gis._findFeatures(layer, obj);
    if (foundFeatures.length == 0 || refreshData) {
      protocol.read({filter:filter, callback:function(resp) {
        if (resp.error) {
          return 0;
        }
        if (resp.features.length > 0) {
          var ext;
          var proj = new OpenLayers.Projection("EPSG:5186");
          $.each(resp.features, function() {
            var polygon = this.geometry;
            this.geometry = polygon.transform(proj, EMD.map.getProjection());
          });
          layer.removeAllFeatures();
          layer.addFeatures(resp.features);
          layer.redraw();
          if (zoomTo) {
            ext = resp.features[0].geometry.getBounds().clone();
            for (var i = 1;i < resp.features.length;i++) {
              ext.extend(resp.features[i].geometry.getBounds());
            }
            EMD.map.zoomToExtent(ext);
          }
        }
      }});
    } else {
      if (zoomTo) {
        ext = resp.features[0].geometry.getBounds().clone();
        for (var i = 1;i < resp.features.length;i++) {
          ext.extend(resp.features[i].geometry.getBounds());
        }
        EMD.map.zoomToExtent(ext);
      }
    }
  }, selectRentFeature:function(obj) {
    if (obj == null) {
      return;
    }
    if (obj["assetsUsageSeq"] == undefined) {
      protocol.read({filter:filter, callback:function(resp) {
        if (resp.error) {
          return 0;
        }
        if (resp.features.length > 0) {
          var ext;
          var proj = new OpenLayers.Projection("EPSG:5186");
          $.each(resp.features, function() {
            var polygon = this.geometry;
            this.geometry = polygon.transform(proj, EMD.map.getProjection());
          });
          EMD.userLayer.assetsRentDetail.removeAllFeatures();
          EMD.userLayer.assetsRentDetail.addFeatures(resp.features);
          EMD.userLayer.assetsRentDetail.redraw();
          ext = resp.features[0].geometry.getBounds().clone();
          for (var i = 1;i < resp.features.length;i++) {
            ext.extend(resp.features[i].geometry.getBounds());
          }
          EMD.map.zoomToExtent(ext);
        }
      }});
    } else {
      EMD.strategies.assetsRentDetail.setFilter(new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRT_AT_CD", value:obj.prtAtCode}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"MNG_YEAR", value:obj.mngYear}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"MNG_NO", value:obj.mngNo}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, 
      property:"MNG_CNT", value:obj.mngCnt}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"ASSETS_USAGE_SEQ", value:obj.assetsUsageSeq})]}));
    }
  }, assetRentStatsRedraw:function() {
    var currDate = new Date;
    if (EMD.layerDisplay["assetRentStats"] != undefined) {
      EMD.strategies.assetRentStats.setFilter(new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRT_AT_CD", value:"622"}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"MNG_YEAR", value:"2014"})]}));
    }
  }, gnrlRentStatsRedraw:function() {
    var currDate = new Date;
    if (EMD.layerDisplay["gnrlRentStats"] != undefined) {
      EMD.strategies.gnrlRentStats.setFilter(new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"QUAY_GROUP_CD", value:"P"}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.LESS_THAN, property:"GR_USAGE_PD_FROM", value:currDate}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO, property:"GR_USAGE_PD_TO", value:currDate}), 
      new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRMISN_YN", value:"Y"})]}));
    }
  }, cntrRentStatsRedraw:function() {
    var currDate = new Date;
    if (EMD.layerDisplay["cntrRentStats"] != undefined) {
      EMD.strategies.cntrRentStats.setFilter(new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"QUAY_GROUP_CD", value:"P"}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.LESS_THAN, property:"GR_USAGE_PD_FROM", value:currDate}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO, property:"GR_USAGE_PD_TO", value:new Date}), 
      new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRMISN_YN", value:"Y"})]}));
    }
  }, htldRentStatsRedraw:function() {
    var currDate = new Date;
    if (EMD.layerDisplay["htldRentStats"] != undefined) {
      EMD.strategies.htldRentStats.setFilter(new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"QUAY_GROUP_CD", value:"H"}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.LESS_THAN, property:"GR_USAGE_PD_FROM", value:currDate}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO, property:"GR_USAGE_PD_TO", value:currDate}), 
      new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRMISN_YN", value:"Y"})]}));
    }
  }, ccntRentStatsRedraw:function() {
    var currDate = new Date;
    if (EMD.layerDisplay["ccntRentStats"] != undefined) {
      EMD.strategies.ccntRentStats.setFilter(new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"QUAY_GROUP_CD", value:"E"}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.LESS_THAN, property:"GR_USAGE_PD_FROM", value:currDate}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO, property:"GR_USAGE_PD_TO", value:currDate}), 
      new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRMISN_YN", value:"Y"})]}));
    }
  }, trnpRentStatsRedraw:function() {
    var currDate = new Date;
    if (EMD.layerDisplay["trnpRentStats"] != undefined) {
      EMD.strategies.trnpRentStats.setFilter(new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"QUAY_GROUP_CD", value:"T"}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.LESS_THAN, property:"GR_USAGE_PD_FROM", value:currDate}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO, property:"GR_USAGE_PD_TO", value:currDate}), 
      new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRMISN_YN", value:"Y"})]}));
    }
  }, goLocation:function(lat, lon) {
    var pos = new OpenLayers.LonLat(lon, lat);
    EMD.map.setCenter(pos);
  }, goLocation4326:function(lat, lon) {
    var latLng = new OpenLayers.LonLat(lon, lat);
    var proj = new OpenLayers.Projection("EPSG:4326");
    var newPos = latLng.transform(proj, new OpenLayers.Projection("EPSG:900913"));
    EMD.map.setCenter(newPos, 18);
  }, selectPrtFclty:function(prtFclty) {
    var features = null;
    switch(prtFclty.gisPrtFcltySe) {
      case "C":
        features = EMD.userLayer.fcltyConstMarker.getFeatureBy("_fcltyObjCode", prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
        break;
      case "M":
        features = EMD.userLayer.fcltyMechMarker.getFeatureBy("_fcltyObjCode", prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
        break;
      case "E":
        features = EMD.userLayer.fcltyInfoTeleMarker.getFeatureBy("_fcltyObjCode", prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
        break;
      case "S":
        features = EMD.userLayer.fcltySocMarker.getFeatureBy("_fcltyObjCode", prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
        break;
      case "P":
        features = EMD.userLayer.fcltyElecPowerMarker.getFeatureBy("_fcltyObjCode", prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
        break;
    }
    if (features == null) {
      alert("\uc120\ud0dd\ub41c \uc2dc\uc124\uc758 \uc815\uc758\ub41c \uc704\uce58\uac00 \uc5c6\uc2b5\ub2c8\ub2e4.");
      return;
    }
    EMD.selectControl.select(features);
  }, setScale:function(scale) {
    EMD.map.setScale(scale);
  }, showGisAssetCd:function(prtFclty) {
    var filter = new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRT_CD", value:prtFclty.gisAssetsPrtAtCode}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"ASSETS_CD", value:prtFclty.gisAssetsCd}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"ASSETS_SCD", value:prtFclty.gisAssetsSubCd})]});
    EMD.protocols.gisAssetsCd.read({filter:filter, callback:function(resp) {
      if (resp.error) {
        return-1;
      }
    }});
  }, selectAssetCdFeature:function(gisAssetsPrtAtCode, gisAssetsCd, gisAssetsSubCd, option) {
    var features = null;
    switch(prtFclty.gisPrtFcltySe) {
      case "C":
        features = EMD.userLayer.fcltyConstMarker.getFeatureBy("_fcltyObjCode", prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
        break;
      case "M":
        features = EMD.userLayer.fcltyMechMarker.getFeatureBy("_fcltyObjCode", prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
        break;
      case "E":
        features = EMD.userLayer.fcltyInfoTeleMarker.getFeatureBy("_fcltyObjCode", prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
        break;
      case "S":
        features = EMD.userLayer.fcltySocMarker.getFeatureBy("_fcltyObjCode", prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
        break;
      case "P":
        features = EMD.userLayer.fcltyElecPowerMarker.getFeatureBy("_fcltyObjCode", prtFclty.gisAssetsPrtAtCode + ":" + prtFclty.gisAssetsCd + ":" + prtFclty.gisAssetsSubCd + ":" + prtFclty.gisPrtFcltySe + ":" + prtFclty.gisPrtFcltyCd + ":" + prtFclty.gisPrtFcltySeq);
        break;
    }
    if (features == null) {
      alert("\uc120\ud0dd\ub41c \uc2dc\uc124\uc758 \uc815\uc758\ub41c \uc704\uce58\uac00 \uc5c6\uc2b5\ub2c8\ub2e4.");
      return;
    }
    EMD.selectControl.select(features);
    var opts = option;
  }, removeAssetCdFeature:function(assetsCd) {
    var filter = new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRT_CD", value:assetsCd.gisAssetsPrtAtCode}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"ASSETS_CD", value:assetsCd.gisAssetsCd}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"ASSETS_SCD", value:assetsCd.gisAssetsSubCd})]});
    EMD.protocols.gisAssetsCd["delete"]({filter:filter});
    EMD.protocols.gisAssetsCd.commit();
  }, modifyAssetCdFeature:function(assetsCd, feature) {
    var filter = new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRT_CD", value:assetsCd.gisAssetsPrtAtCode}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"ASSETS_CD", value:assetsCd.gisAssetsCd}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"ASSETS_SCD", value:assetsCd.gisAssetsSubCd})]});
    EMD.protocols.gisAssetsCd.read({filter:filter, callback:function(resp) {
      if (resp.error) {
        return 0;
      }
      if (resp.features.length > 0) {
        resp.features[0].geometry = feature.geometry.clone();
        resp.features[0].state = OpenLayers.State.UPDATE;
        EMD.protocols.gisAssetsCd.commit(resp.features, {callback:function(resp) {
          if (resp.error) {
            return-1;
          }
          var gisAssetsCdProjection = new OpenLayers.Projection("EPSG:5186");
          EMD.selectControl.unselectAll();
          $.each(resp.reqFeatures, function() {
            var polygon = this.geometry;
            this.geometry = polygon.transform(gisAssetsCdProjection, EMD.map.getProjection());
          });
          EMD.userLayer.gisAssetsCd.addFeatures(resp.reqFeatures);
          EMD.userLayer.gisAssetsCd.redraw();
        }});
      } else {
        var v = new OpenLayers.Feature;
        v.state = OpenLayers.State.INSERT;
        v.geometry = feature.geometry.clone();
        v.attributes = {PRT_CD:assetsCd.gisAssetsPrtAtCode, ASSETS_CD:assetsCd.gisAssetsCd, ASSETS_SCD:assetsCd.gisAssetsSubCd, ASSETS_NM:assetsCd.gisAssetsNm, BJD_CODE:EMD.gis.makeBJDCode(assetsCd.gisAssetsLocplc, assetsCd.gisAssetsLnm, assetsCd.gisAssetsLnmSub)};
        var v = new OpenLayers.Feature;
        v.geometry = feature.geometry.clone();
        v.attributes = {PRT_CD:assetsCd.gisAssetsPrtAtCode, ASSETS_CD:assetsCd.gisAssetsCd, ASSETS_SCD:assetsCd.gisAssetsSubCd, ASSETS_NM:assetsCd.gisAssetsNm, LOCPLC:assetsCd.gisAssetsLocplc, GIS_ASSETS_LNM:assetsCd.gisAssetsLnm, GIS_ASSETS_LNM_SUB:assetsCd.gisAssetsLnmSub};
        EMD.protocols.gisAssetsCd.create(v);
        v.state = OpenLayers.State.INSERT;
        EMD.protocols.gisAssetsCd.commit([v], {callback:function(resp) {
          if (resp.error) {
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
    }});
  }, findLotCode:function(bjdCode, lnm, lnmSub, mt, data, getFunc) {
    var pnu = bjdCode + (mt ? "2" : "1") + EMD.util.leftPad(lnm * 1, "0", 4) + EMD.util.leftPad((lnmSub || 0) * 1, "0", 4);
    console.log('pnu:"' + pnu + '"');
    var filter = new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PNU", value:pnu});
    var resp = EMD.protocols.landCode.read({filter:filter, callback:function(resp, opts) {
      if (resp.error) {
        resp.userdata.func(null, "error");
        return-1;
      }
      resp.userdata.func(resp.features, resp.userdata.attr);
    }});
    resp.userdata = {attr:data, func:getFunc};
  }, modifyAssetCdFeatureByBJDCode:function(gac) {
    var filter = new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRT_CD", value:gac.gisAssetsPrtAtCode}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"ASSETS_CD", value:gac.gisAssetsCd}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"ASSETS_SCD", value:gac.gisAssetsSubCd})]});
    EMD.lotareaProtocol.read({filter:filter, callback:function(resp) {
      if (resp.error) {
        return-1;
      }
    }});
    EMD.protocols.gisAssetsCd.update(features, {filter:new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRT_CD", value:gisAssetsPrtAtCode}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"ASSETS_CD", value:gisAssetsCd}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"ASSETS_SCD", value:gisAssetsSubCd})]}), 
    callback:function(resp) {
      if (resp.error) {
        return-1;
      }
    }});
  }, getBupjungDongNm:function(bjdCode, dataObj, callBackFunc) {
    if (callBackFunc == null) {
      return;
    }
    $.ajax({url:EMD.context_root + "/cmmn/selectBupJungDongCodeNm.do", type:"POST", userData:{callBack:callBackFunc, data:dataObj}, dataType:"json", global:false, contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"cmd", value:bjdCode}], success:function(data) {
      if (data.resultCode != null && data.resultCode != 0) {
        if (data.resultCode == 1) {
          alert(data.resultMsg);
          location.reload();
        }
      } else {
        data = $.extend(data, this.userData.data);
        if (this.userData.callBack != null) {
          this.userData.callBack(data);
        }
      }
    }, error:function(XMLHttpRequest, textStatus, errorThrown) {
      try {
      } catch (e) {
      }
    }});
  }, removeFeature:function(layerName, obj) {
    var layer = EMD.gis.getUserLayer(layerName);
    var i, feature;
    len = layer.features.length;
    var foundFeatures = [];
    EMD.selectControl.unselectAll();
    for (i = 0;i < len;i++) {
      feature = layer.features[i];
      if (feature && feature.attributes) {
        if (EMD.gis.compareLayerObject(layer, feature, obj)) {
          foundFeatures.push(feature);
        }
      }
    }
    for (i = 0;i < foundFeatures.length;i++) {
      feature = foundFeatures[i];
      feature.state = OpenLayers.State.DELETE;
      layer.removeFeature(feature);
    }
  }, loadMapDataFlexGrid:function(module, layer, grid, code_id, value_id) {
  }, loadMapDataCode:function(module, layer, code, value) {
  }, clearMap:function(module, mapLayer) {
    var layer = EMD.gis.getUserLayer(mapLayer);
    EMD.gis.closeAllPopup(mapLayer);
    layer.removeAllFeatures();
  }, showStatusMapView:function(module, mapLayer, grid, mapStyle, value, labelField, selectFeature, getUserColor, getUserLabel) {
    var g = module.$("#" + grid);
    var rows = g.flexGetData();
    var layer = EMD.gis.getUserLayer(mapLayer);
    var target = EMD.userLayer.assetStats;
    var ext = null;
    var maxValue = 0;
    var foundFeature = [];
    len = layer.features.length;
    target.removeAllFeatures();
    switch(mapStyle) {
      case "user":
        EMD.gis.getStatsColor = module[getUserColor];
        EMD.gis.getStatsLabel = module[getUserLabel];
        break;
      case "rate":
        EMD.gis.getStatsColor = function(feature) {
          if (feature["attributes"] != undefined) {
            var b = feature.attributes["value"];
            var hue = Math.floor(b / 100 * 120 / 100);
            return EMD.util.hsv2rgb(hue, 1, 1);
          } else {
            return "#FFFFFF";
          }
        };
        EMD.gis.getStatsLabel = function(feature) {
          if (feature["attributes"] == undefined || feature.attributes["label"] == undefined) {
            return "";
          }
          return feature.attributes["label"];
        };
        break;
      case "value":
      ;
      default:
        EMD.gis.getStatsColor = function(feature) {
          if (feature["attributes"] != undefined) {
            var val = feature.attributes["value"] || 0;
            var maxVal = feature.attributes["max-value"] || 0;
            var b;
            if (maxVal != 0) {
              b = Math.round(val / maxVal * 100);
            } else {
              b = 0;
            }
            var hue = Math.floor(b * 120 / 100);
            return EMD.util.hsv2rgb(hue, 1, 1);
          } else {
            return "#FFFFFF";
          }
        };
        EMD.gis.getStatsLabel = function(feature) {
          if (feature["attributes"] == undefined || feature.attributes["label"] == undefined) {
            return "";
          }
          return feature.attributes["label"];
        };
        for (var j = 0;j < rows.length;j++) {
          var d = rows[j][value];
          if (typeof d == "string") {
            d = d.replace(/,/g, "") * 1;
          }
          if (d > maxValue) {
            maxValue = d;
          }
        }
        break;
    }
    for (var i = 0;i < len;i++) {
      feature = layer.features[i];
      if (feature && feature.attributes) {
        for (var j = 0;j < rows.length;j++) {
          var r = rows[j];
          if (EMD.gis.compareLayerObject(layer, feature, r)) {
            var f = feature.clone();
            f.attributes["value"] = r[value];
            if (mapStyle != "rate") {
              f.attributes["max-value"] = maxValue;
            }
            if (labelField != undefined) {
              f.attributes["label"] = r[labelField];
            } else {
              f.attributes["label"] = r["gisAssetsNm"];
            }
            foundFeature[foundFeature.length] = f;
            var point = new OpenLayers.Geometry.Point(feature.bounds.left, feature.bounds.bottom);
            var minGeom = OpenLayers.Projection.transform(point, layer.projection, EMD.map.projection);
            point = new OpenLayers.Geometry.Point(feature.bounds.right, feature.bounds.top);
            var maxGeom = OpenLayers.Projection.transform(point, layer.projection, EMD.map.projection);
            if (ext == null) {
              ext = new OpenLayers.Bounds;
            }
            ext.extend(minGeom);
            ext.extend(maxGeom);
          }
        }
      }
    }
    if (foundFeature.length > 0) {
      target.addFeatures(foundFeature);
      target.drawFeature(foundFeature);
      EMD.map.zoomToExtent(ext);
      target.events.remove("featureselected");
      target.events.register("featureselected", {module:module, selectFeature:selectFeature}, function(e) {
        if (this.selectFeature != null && typeof module[selectFeature] == "function") {
          module[selectFeature](e);
        } else {
          EMD.gis.openPopup(e.feature, "/maps/assets/gamAssetCdInfo.do", EMD.util.objectToArray(e.feature.attributes));
        }
      });
      target.events.remove("featureunselected");
      target.events.register("featureunselected", null, function(e) {
        var feature = e.feature;
        if (feature.popup != null) {
          EMD.map.removePopup(feature.popup);
          feature.popup.destroy();
          feature.popup = null;
        }
      });
    } else {
      alert("\uc870\ud68c\ub41c \uc601\uc5ed\uc774 \uc874\uc7ac \ud558\uc9c0 \uc54a\uc2b5\ub2c8\ub2e4.");
    }
  }, _showFeatureData:function(module, layerName, item) {
    var layer = layerName;
    var i, feature;
    var ext = null;
    var foundFeatures = [];
    if (item.length != undefined && item.length == 0 || typeof item != "object") {
      alert("\ud56d\ubaa9\uc744 \uc120\ud0dd \ud558\uc2ed\uc2dc\uc694.");
      return;
    }
    if (typeof layerName == "string") {
      layer = EMD.gis.getUserLayer(layerName);
    }
    len = layer.features.length;
    var done = 1;
    while (done) {
      if (item.length != undefined) {
        for (i = 0;i < len;i++) {
          feature = layer.features[i];
          if (feature && feature.attributes) {
            for (var j = 0;j < item.length;j++) {
              var r = item[j];
              if (EMD.gis.compareLayerObject(layer, feature, r)) {
                foundFeatures.push(feature);
                EMD.selectControl.select(feature);
                if (feature.bounds != null) {
                  var point = new OpenLayers.Geometry.Point(feature.bounds.left, feature.bounds.bottom);
                  var minGeom = OpenLayers.Projection.transform(point, layer.projection, EMD.map.projection);
                  point = new OpenLayers.Geometry.Point(feature.bounds.right, feature.bounds.top);
                  var maxGeom = OpenLayers.Projection.transform(point, layer.projection, EMD.map.projection);
                  if (ext == null) {
                    ext = new OpenLayers.Bounds(minGeom.x, minGeom.y, maxGeom.x, maxGeom.y);
                  }
                  ext.extend(minGeom);
                  ext.extend(maxGeom);
                }
              }
            }
          }
        }
      } else {
        for (i = 0;i < len;i++) {
          feature = layer.features[i];
          if (feature && feature.attributes) {
            if (EMD.gis.compareLayerObject(layer, feature, item)) {
              foundFeatures.push(feature);
              EMD.selectControl.select(feature);
              if (feature.bounds != null) {
                var point = new OpenLayers.Geometry.Point(feature.bounds.left, feature.bounds.bottom);
                var minGeom = OpenLayers.Projection.transform(point, layer.projection, EMD.map.projection);
                point = new OpenLayers.Geometry.Point(feature.bounds.right, feature.bounds.top);
                var maxGeom = OpenLayers.Projection.transform(point, layer.projection, EMD.map.projection);
                if (ext == null) {
                  ext = new OpenLayers.Bounds(minGeom.x, minGeom.y, maxGeom.x, maxGeom.y);
                }
                ext.extend(minGeom);
                ext.extend(maxGeom);
              }
            }
          }
        }
      }
      if (foundFeatures.length == 0) {
        var pl = EMD.gis.getParentLayer(layer);
        if (pl != null) {
          EMD.gis._showFeatureData(module, pl, item);
          return;
        } else {
          done = 0;
        }
      } else {
        done = 0;
        EMD.map.zoomToExtent(ext);
      }
    }
    if (foundFeatures.length == 0) {
      alert("\uc704\uce58 \ub370\uc774\ud130\uac00 \uc5c6\uc2b5\ub2c8\ub2e4.");
    } else {
      EMD.selectControl.unselectAll();
    }
  }, showMapDataFlexGrid:function(module, layerName, grid, code_id, value_id) {
    var row = module.$("#" + grid).selectedRows();
    EMD.gis._showFeatureData(module, layerName, row);
  }, showMapDataCode:function(module, layerName, code) {
    var row = module[code];
    EMD.gis._showFeatureData(module, layerName, row);
  }, editLayer:function(module, layerName, retfunc) {
    var layer = EMD.gis.getUserLayer(layerName);
    if (layer == null) {
      alert("\ud3b8\uc9d1 \ud560 \uc218 \uc5c6\ub294 \uc9c0\ub3c4 \ub808\uc774\uc5b4 \uc785\ub2c8\ub2e4.");
      return;
    }
    var virtual = {strokeColor:"#00ff00", fillColor:"#00ff00", strokeOpacity:1, strokeWidth:2, pointRadius:5, graphicName:"cross"};
    EMD._currentEditLayer = layer;
    EMD.editControls = {addPoint:new OpenLayers.Control.DrawFeature(layer, OpenLayers.Handler.Point, {featureAdded:function(f) {
      console.log("add point");
      EMD.gis.storeLayerObject(this.layer, f, null);
      if (this.layer != f.layer) {
        this.layer.addFeatures([f]);
      }
    }}), addLine:new OpenLayers.Control.DrawFeature(layer, OpenLayers.Handler.Path, {featureAdded:function(f) {
      console.log("add line");
      EMD.gis.storeLayerObject(this.layer, f, null);
      if (this.layer != f.layer) {
        this.layer.addFeatures([f]);
      }
    }}), addFeature:new OpenLayers.Control.DynamicMeasure(OpenLayers.Handler.Polygon, {drawingLayer:layer, handlerOptions:{holeModifier:"altKey"}, featureAdded:function(f) {
      console.log("add feature");
      EMD.gis.storeLayerObject(this.drawingLayer, f, null);
      if (this.drawingLayer != f.layer) {
        this.drawingLayer.addFeatures([f]);
      }
    }}), editFeature:new OpenLayers.Control.ModifyFeature(layer, {vertexRenderIntent:"modify", virtualStyle:virtual, deleteCodes:[46, 68], mode:OpenLayers.Control.ModifyFeature.RESHAPE | OpenLayers.Control.ModifyFeature.DRAG, handlerOptions:{holeModifier:"altKey"}}), delFeature:new DeleteFeature(layer, {title:"\uc0ad\uc81c"})};
    for (var key in EMD.editControls) {
      EMD.map.addControl(EMD.editControls[key]);
    }
    var addPointBtn = new OpenLayers.Control.Button({title:"\uc810 \ucd94\uac00", displayClass:"olControlEditAddPoint", type:OpenLayers.Control.TYPE_TOGGLE, eventListeners:{activate:function() {
      for (var key in EMD.editControls) {
        if (key != "addPoint") {
          EMD.editControls[key].button.deactivate();
        }
      }
      EMD.editControls["addPoint"].activate();
    }, deactivate:function() {
      for (var key in EMD.editControls) {
        var control = EMD.editControls[key];
        if (key == "addPoint") {
          control.deactivate();
        }
      }
    }}});
    EMD.editControls.addPoint.button = addPointBtn;
    var addLineBtn = new OpenLayers.Control.Button({title:"\uc120 \ucd94\uac00", displayClass:"olControlEditAddLine", type:OpenLayers.Control.TYPE_TOGGLE, eventListeners:{activate:function() {
      for (var key in EMD.editControls) {
        if (key != "addLine") {
          EMD.editControls[key].button.deactivate();
        }
      }
      EMD.editControls["addLine"].activate();
    }, deactivate:function() {
      for (var key in EMD.editControls) {
        var control = EMD.editControls[key];
        if (key == "addLine") {
          control.deactivate();
        }
      }
    }}});
    EMD.editControls.addLine.button = addLineBtn;
    var addButton = new OpenLayers.Control.Button({title:"\ucd94\uac00", displayClass:"olControlEditAdd", type:OpenLayers.Control.TYPE_TOGGLE, eventListeners:{activate:function() {
      for (var key in EMD.editControls) {
        if (key != "addFeature") {
          EMD.editControls[key].button.deactivate();
        }
      }
      EMD.editControls["addFeature"].activate();
    }, deactivate:function() {
      for (var key in EMD.editControls) {
        var control = EMD.editControls[key];
        if (key == "addFeature") {
          control.deactivate();
        }
      }
    }}});
    EMD.editControls.addFeature.button = addButton;
    var editButton = new OpenLayers.Control.Button({title:"\ud3b8\uc9d1", displayClass:"olControlEditEdit", type:OpenLayers.Control.TYPE_TOGGLE, eventListeners:{activate:function() {
      for (var key in EMD.editControls) {
        if (key != "editFeature") {
          EMD.editControls[key].button.deactivate();
        }
      }
      EMD.editControls["editFeature"].activate();
    }, deactivate:function() {
      for (var key in EMD.editControls) {
        var control = EMD.editControls[key];
        if (key == "editFeature") {
          control.deactivate();
        }
      }
    }}});
    EMD.editControls.editFeature.button = editButton;
    var delButton = new OpenLayers.Control.Button({title:"\uc0ad\uc81c", displayClass:"olControlEditDelete", type:OpenLayers.Control.TYPE_TOGGLE, eventListeners:{activate:function() {
      for (var key in EMD.editControls) {
        if (key != "delFeature") {
          EMD.editControls[key].button.deactivate();
        }
      }
      EMD.editControls["delFeature"].activate();
    }, deactivate:function() {
      for (var key in EMD.editControls) {
        var control = EMD.editControls[key];
        if (key == "delFeature") {
          control.deactivate();
        }
      }
    }}});
    EMD.editControls.delFeature.button = delButton;
    EMD.save = new OpenLayers.Control.Button({title:"\ud3b8\uc9d1 \uc644\ub8cc", module:module, retfunc:retfunc, layer:layer, trigger:function() {
      EMD.panel.deactivate();
      EMD.map.removeControl(EMD.panel);
      for (var key in EMD.editControls) {
        EMD.editControls[key].deactivate();
      }
      EMD.selectControl.activate();
      EMD.gis.storeLayer(EMD._currentEditLayer);
      module.showWindow();
    }, displayClass:"olControlEditDone"});
    EMD.cancel = new OpenLayers.Control.Button({title:"\ud3b8\uc9d1 \ucde8\uc18c", trigger:function() {
      EMD.panel.deactivate();
      EMD.map.removeControl(EMD.panel);
      for (var key in EMD.editControls) {
        EMD.editControls[key].deactivate();
      }
      EMD.selectControl.activate();
      module.showWindow();
    }, displayClass:"olControlEditCancel"});
    if (EMD.panel != null) {
      EMD.panel.deactivate();
    }
    EMD.panel = new OpenLayers.Control.Panel({displayClass:"mapEditToolBar"});
    EMD.selectControl.deactivate();
    EMD.panel.addControls([addPointBtn, addLineBtn, addButton, editButton, delButton, EMD.save, EMD.cancel]);
    EMD.map.addControl(EMD.panel);
    EMD.panel.activate();
  }, addFeatureCode:function(module, layerName, code, value) {
    var layer = EMD.gis.getUserLayer(layerName);
    var row = module[code];
    var i, feature;
    len = layer.features.length;
    var foundFeatures = [];
    if (row == undefined || !EMD.gis.checkLayerObject(layer, row)) {
      alert("\uc18d\uc131\uc774 \uc815\uc758 \ub418\uc9c0 \uc54a\uc544 \ud3b8\uc9d1\uc774 \uac00\ub2a5\ud558\uc9c0 \uc54a\uc2b5\ub2c8\ub2e4.");
      return;
    }
    EMD.selectControl.unselectAll();
    EMD.util.window_hide_all();
    if (row["_feature"] == undefined) {
      for (i = 0;i < len;i++) {
        feature = layer.features[i];
        if (feature && feature.attributes) {
          if (EMD.gis.compareLayerObject(layer, feature, row)) {
            foundFeatures.push(feature);
            break;
          }
        }
      }
      if (foundFeatures.length == 0) {
      } else {
        var ext;
        var modifyFeatures = [];
        ext = foundFeatures[0].geometry.getBounds().clone();
        modifyFeatures[modifyFeatures.length] = foundFeatures[0].clone();
        for (var i = 1;i < foundFeatures.length;i++) {
          ext.extend(foundFeatures[i].geometry.getBounds());
          modifyFeatures[modifyFeatures.length] = foundFeatures[i].clone();
        }
        EMD.map.zoomToExtent(ext);
      }
      EMD.editControls = {addFeature:new OpenLayers.Control.DynamicMeasure(OpenLayers.Handler.Polygon, {drawingLayer:EMD.wfs, handlerOptions:{holeModifier:"altKey"}}), editFeature:new OpenLayers.Control.ModifyFeature(EMD.wfs, {mode:OpenLayers.Control.ModifyFeature.RESHAPE, handlerOptions:{holeModifier:"altKey"}}), delFeature:new DeleteFeature(EMD.wfs, {title:"\uc0ad\uc81c"})};
      for (var key in EMD.editControls) {
        EMD.map.addControl(EMD.editControls[key]);
      }
      var addButton = new OpenLayers.Control.Button({title:"\ucd94\uac00", displayClass:"olControlEditAdd", type:OpenLayers.Control.TYPE_TOGGLE, eventListeners:{activate:function() {
        for (var key in EMD.editControls) {
          var control = EMD.editControls[key];
          if (key == "addFeature") {
            control.activate();
          } else {
            control.deactivate();
          }
        }
      }, deactivate:function() {
        for (var key in EMD.editControls) {
          EMD.editControls[key].deactivate();
        }
      }}});
      var editButton = new OpenLayers.Control.Button({title:"\ud3b8\uc9d1", displayClass:"olControlEditEdit", type:OpenLayers.Control.TYPE_TOGGLE, eventListeners:{activate:function() {
        for (var key in EMD.editControls) {
          var control = EMD.editControls[key];
          if (key == "editFeature") {
            control.activate();
          } else {
            control.deactivate();
          }
        }
      }, deactivate:function() {
        for (var key in EMD.editControls) {
          EMD.editControls[key].deactivate();
        }
      }}});
      var delButton = new OpenLayers.Control.Button({title:"\uc0ad\uc81c", displayClass:"olControlEditDelete", type:OpenLayers.Control.TYPE_TOGGLE, eventListeners:{activate:function() {
        for (var key in EMD.editControls) {
          var control = EMD.editControls[key];
          if (key == "delFeature") {
            control.activate();
          } else {
            control.deactivate();
          }
        }
      }, deactivate:function() {
        for (var key in EMD.editControls) {
          EMD.editControls[key].deactivate();
        }
      }}});
      EMD.save = new OpenLayers.Control.Button({title:"\ud3b8\uc9d1 \uc644\ub8cc", trigger:function() {
        EMD.panel.deactivate();
        EMD.map.removeControl(EMD.panel);
        for (var key in EMD.editControls) {
          EMD.editControls[key].deactivate();
        }
        var polygon;
        if (EMD.wfs.features.length > 1) {
          var g = [];
          for (var i = 0;i < EMD.wfs.features.length;i++) {
            g[i] = EMD.wfs.features[i].geometry.clone();
          }
          polygon = new OpenLayers.Geometry.MultiPolygon(g);
        } else {
          polygon = new OpenLayers.Geometry.Polygon(EMD.wfs.features[0].geometry.clone());
        }
        f = new OpenLayers.Feature.Vector(polygon);
        f.attributes = modifyFeatures[0].attributes;
        f.state = OpenLayers.State.UPDATE;
        layer.addFeatures([f]);
        EMD.selectControl.activate();
        module.showWindow();
      }, displayClass:"olControlEditDone"});
      EMD.cancel = new OpenLayers.Control.Button({title:"\ud3b8\uc9d1 \ucde8\uc18c", trigger:function() {
        EMD.panel.deactivate();
        EMD.map.removeControl(EMD.panel);
        for (var key in EMD.editControls) {
          EMD.editControls[key].deactivate();
        }
        EMD.gis.selectFeatureData(module, layerName, module[code], true, true);
        EMD.selectControl.activate();
        module.showWindow();
      }, displayClass:"olControlEditCancel"});
      if (EMD.panel != null) {
        EMD.panel.deactivate();
      }
      EMD.panel = new OpenLayers.Control.Panel({displayClass:"mapEditToolBar"});
      EMD.selectControl.deactivate();
      EMD.panel.addControls([addButton, editButton, delButton, EMD.save, EMD.cancel]);
      EMD.map.addControl(EMD.panel);
      EMD.panel.activate();
    } else {
      EMD.modifyFeature = new OpenLayers.Control.ModifyFeature(layer, {mode:OpenLayers.Control.ModifyFeature.RESHAPE, handlerOptions:{holeModifier:"altKey"}, afterfeaturemodified:function(feature) {
        layer.addFeatures(feature);
        layer.redraw();
        module[code]["_feature"] = feature;
        EMD.modifyFeature.deactivate();
        module.showWindow();
      }});
      EMD.map.addControl(EMD.modifyFeature);
      EMD.modifyFeature.activate();
      EMD.modifyFeature.selectFeature(row["_feature"]);
      var addButton = new OpenLayers.Control.Button({title:"\ucd94\uac00", trigger:function() {
        EMD.panel.deactivate();
        EMD.modifyFeature.deactivate();
        EMD.map.removeControl(EMD.panel);
        layer.addFeatures(EMD.wfs.features);
        EMD.saveStrategy.save();
        module.showWindow();
      }, displayClass:"olControlEditAdd"});
      var editButton = new OpenLayers.Control.Button({title:"\ud3b8\uc9d1", trigger:function() {
        EMD.panel.deactivate();
        EMD.modifyFeature.deactivate();
        EMD.map.removeControl(EMD.panel);
        layer.addFeatures(EMD.wfs.features);
        EMD.saveStrategy.save();
        module.showWindow();
      }, displayClass:"olControlEditEdit"});
      var delButton = new OpenLayers.Control.Button({title:"\uc0ad\uc81c", trigger:function() {
        EMD.panel.deactivate();
        EMD.modifyFeature.deactivate();
        EMD.map.removeControl(EMD.panel);
        layer.addFeatures(EMD.wfs.features);
        EMD.saveStrategy.save();
        module.showWindow();
      }, displayClass:"olControlEditDel"});
      EMD.save = new OpenLayers.Control.Button({title:"\ud3b8\uc9d1 \uc644\ub8cc", trigger:function() {
        EMD.panel.deactivate();
        EMD.modifyFeature.deactivate();
        EMD.map.removeControl(EMD.panel);
        layer.addFeatures(EMD.wfs.features);
        EMD.saveStrategy.save();
        module.showWindow();
      }, displayClass:"olControlEditDone"});
      EMD.cancel = new OpenLayers.Control.Button({title:"\ud3b8\uc9d1 \ucde8\uc18c", trigger:function() {
        EMD.panel.deactivate();
        EMD.modifyFeature.deactivate();
        EMD.map.removeControl(EMD.panel);
        EMD.gis.selectFeatureData(module, layerName, module[code], true, true);
        module.showWindow();
      }, displayClass:"olControlEditCancel"});
      if (EMD.panel != null) {
        EMD.panel.deactivate();
      }
      EMD.panel = new OpenLayers.Control.Panel({displayClass:"mapEditToolBar"});
      EMD.panel.addControls([addButton, editButton, delButton, EMD.save, EMD.cancel]);
    }
  }, removeFeatureCode:function(module, layerName, code) {
    var layer = EMD.gis.getUserLayer(layerName);
    var row = module[code];
    var i, feature;
    len = layer.features.length;
    var foundFeatures = [];
    EMD.selectControl.unselectAll();
    for (i = 0;i < len;i++) {
      feature = layer.features[i];
      if (feature && feature.attributes) {
        if (EMD.gis.compareLayerObject(layer, feature, row)) {
          foundFeatures.push(feature);
          break;
        }
      }
    }
    if (foundFeatures.length == 0) {
      return false;
    } else {
      var ext;
      if (foundFeatures.length > 0) {
        for (var i = 0;i < foundFeatures.length;i++) {
          if (foundFeatures[i].state != OpenLayers.State.INSERT) {
            foundFeatures[i].state = OpenLayers.State.DELETE;
          }
          layer.removeFeatures(foundFeatures);
          EMD.saveStrategy.save();
        }
        layer.redraw();
      }
    }
    return true;
  }, modifyFeatureAttr:function(module, layerName, value, newfeature) {
  }, setFeatureCode:function(module, layerName, value, feature) {
    var layer = EMD.gis.getUserLayer(layerName);
    EMD.gis.storeLayerObject(layer, feature, value);
  }, modifyFeatureCode:function(module, layerName, value, newfeature) {
    var layer = EMD.gis.getUserLayer(layerName);
    var i, feature;
    len = layer.features.length;
    var foundFeatures = [];
    for (i = 0;i < len;i++) {
      feature = layer.features[i];
      if (feature && feature.attributes) {
        if (EMD.gis.compareLayerObject(layer, feature, value)) {
          foundFeatures.push(feature);
          break;
        }
      }
    }
    if (foundFeatures.length == 0) {
      var polygon = newfeature.geometry;
      newfeature.geometry = polygon.transform(newfeature.layer.projection, EMD.map.getProjection());
      EMD.gis.storeLayerObject(layer, newfeature, value);
      newfeature.state = OpenLayers.State.INSERT;
      layer.addFeatures([newfeature]);
      layer.redraw();
      EMD.protocols.gisAssetsCd.create(newfeature);
      EMD.protocols.gisAssetsCd.commit();
      EMD.saveStrategy.save();
    } else {
      if (foundFeatures.length > 0) {
        var polygon = newfeature.geometry;
        foundFeatures[0].geometry = polygon.transform(newfeature.layer.projection, EMD.map.getProjection());
        foundFeatures[0].state = OpenLayers.State.UPDATE;
        layer.addFeatures(foundFeatures);
        layer.drawFeature(foundFeatures);
        layer.redraw();
        EMD.protocols.gisAssetsCd.update(foundFeatures, {filter:new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRT_CD", value:foundFeatures[0].gisAssetsPrtAtCode}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"ASSETS_CD", value:foundFeatures[0].gisAssetsCd}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"ASSETS_SCD", 
        value:foundFeatures[0].gisAssetsSubCd})]}), callback:function(resp) {
          if (resp.error) {
            return-1;
          }
        }});
        EMD.saveStrategy.save();
      }
    }
  }, changeFeatureAttribute:function(module, layerName, oldattr, newattr) {
    var layer = EMD.gis.getUserLayer(layerName);
    var i, feature;
    len = layer.features.length;
    var changed = false;
    for (i = 0;i < len;i++) {
      feature = layer.features[i];
      if (feature && feature.attributes) {
        if (EMD.gis.compareLayerObject(layer, feature, oldattr)) {
          EMD.gis.storeLayerObject(layer, feature, newattr);
          layer.drawFeature(feature);
          EMD.gis.storeLayerObject(layer, feature, newattr);
          changed = true;
        }
      }
    }
    if (changed) {
      EMD.saveStrategy.save();
    }
  }, addFeatureFlexGrid:function(module, layerName, grid, code_id, value_id) {
    var row = module.$("#" + grid).selectedRows();
    var layer = EMD.gis.getUserLayer(layerName);
    var i, feature;
    len = layer.features.length;
    var foundFeatures = [];
    if (row.length == 0) {
      alert("\ud56d\ubaa9\uc744 \uc120\ud0dd \ud558\uc2ed\uc2dc\uc694.");
      return;
    }
    EMD.selectControl.unselectAll();
    for (i = 0;i < len;i++) {
      feature = layer.features[i];
      if (feature && feature.attributes) {
        for (var j = 0;j < row.length;j++) {
          var r = row[j];
          if (EMD.gis.compareLayerObject(layer, feature, r)) {
            foundFeatures.push(feature);
          }
        }
      }
    }
    EMD.util.window_hide_all();
    if (foundFeatures.length == 0) {
      EMD.map.addControl(new OpenLayers.Control.DrawFeature(EMD.userLayer.gisAssetsCd, OpenLayers.Handler.Polygon, {handlerOptions:{holeModifier:"altKey"}, featureAdded:function(feature) {
        EMD.gis.storeLayerObject(layer, feature, row[0]);
        EMD.userLayer.gisAssetsCd.addFeatures([feature]);
        EMD.saveStrategy.save();
      }}));
    } else {
      var ext;
      ext = foundFeatures[0].geometry.getBounds().clone();
      for (var i = 1;i < foundFeatures.length;i++) {
        ext.extend(foundFeatures[i].geometry.getBounds());
      }
      EMD.map.zoomToExtent(ext);
      EMD.modifyFeature = new OpenLayers.Control.ModifyFeature(EMD.userLayer.gisAssetsCd, {handlerOptions:{holeModifier:"altKey"}, featureAdded:function(feature) {
        EMD.gis.storeLayerObject(layer, feature, row[0]);
        EMD.userLayer.gisAssetsCd.addFeatures(e.feature);
        EMD.saveStrategy.save();
      }});
      EMD.map.addControl(modifyFeature);
      modifyFeature.selectFeature(foundFeatures[0]);
    }
  }, removeFeatureFlexGrid:function(module, layerName, grid, code_id, value_id) {
    var row = module.$("#" + grid).selectedRows();
    var layer = EMD.gis.getUserLayer(layerName);
    var i, feature;
    len = layer.features.length;
    var foundFeatures = [];
    if (row.length == 0) {
      alert("\ud56d\ubaa9\uc744 \uc120\ud0dd \ud558\uc2ed\uc2dc\uc694.");
      return;
    }
    for (i = 0;i < len;i++) {
      feature = layer.features[i];
      if (feature && feature.attributes) {
        for (var j = 0;j < row.length;j++) {
          var r = row[j];
          if (EMD.gis.compareLayerObject(layer, feature, r)) {
            foundFeatures.push(feature);
          }
        }
      }
    }
    if (foundFeatures.length == 0) {
      return false;
    } else {
      layer.removeFeatures(e.feature);
      EMD.saveStrategy.save();
    }
    return true;
  }, readFeature:function(module, layerName, obj, _callback) {
    var layer = EMD.gis.getUserLayer(layerName);
    if (layer == EMD.userLayer.assetsRent) {
      var resp = EMD.protocols.assetsRent.read({filter:new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRT_AT_CD", value:obj["prtAtCode"]}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"MNG_YEAR", value:obj["mngYear"]}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"MNG_NO", value:obj["mngNo"]}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, 
      property:"MNG_CNT", value:obj["mngCnt"]})]}), callback:_callback(response)})
    }
    resp.data = {obj:obj};
    return resp;
  }, selectAssetCdToAssetRentDetailFeature:function(module, assetsRentDetail, func) {
    var f;
    var assetCdLayer = EMD.userLayer.gisAssetsCd;
    var i, feature;
    len = assetCdLayer.features.length;
    for (i = 0;i < len;i++) {
      feature = assetCdLayer.features[i];
      if (feature && feature.attributes) {
        if (EMD.gis.compareLayerObject(assetCdLayer, feature, assetsRentDetail)) {
          f = new OpenLayers.Feature;
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
    f = new OpenLayers.Feature;
    var polygon = new OpenLayers.Geometry.Polygon;
    f.geometry = new OpenLayers.Feature.Vector(polygon);
    f.attributes = {};
    EMD.gis.storeLayerObject(EMD.userLayer.assetsRentDetail, f, assetsRentDetail);
    EMD.protocols.assetsRentDetail.update(f);
    f.state = OpenLayers.State.UPDATE;
    EMD.userLayer.assetsRentDetail.addFeatures([f]);
    EMD.userLayer.assetsRentDetail.drawFeature(f);
    func(module, f);
  }, selectAssetRentDetail:function(assetsRentDetail, f) {
    var resp = EMD.protocols.assetsRentDetail.read({filter:new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRT_AT_CD", value:assetsRentDetail["prtAtCode"]}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"MNG_YEAR", value:assetsRentDetail["mngYear"]}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"MNG_NO", 
    value:assetsRentDetail["mngNo"]}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"MNG_CNT", value:assetsRentDetail["mngCnt"]}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"ASSETS_USAGE_SEQ", value:assetsRentDetail["assetsUsageSeq"]})]}), callback:function(resp) {
      var ext;
      if (resp.features == undefined || resp.features.length == 0) {
        alert("\uc815\uc758\ub41c GIS \uc601\uc5ed\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.");
      } else {
        resp.data.src = resp.features[0].clone();
      }
    }});
    resp.data = {obj:assetsRentDetail, src:f};
  }, modifyAssetRentDetailFeature:function(module, assetsRentDetail, feature, callback) {
    if (feature.geometry.components == undefined || feature.geometry.components.length == 0) {
      alert("\uc704\uce58\ub97c \ud074\ub9ad\ud558\uc5ec \uc601\uc5ed\uc744 \uc9c0\uc815\ud569\ub2c8\ub2e4.");
      EMD.draw = new OpenLayers.Control.DrawFeature(EMD.userLayer.assetsRentDetail, OpenLayers.Handler.PathMeasureCustom, {title:"\ucd94\uac00", displayClass:"olControlDrawFeaturePolygon", multi:true});
      EMD.save = new OpenLayers.Control.Button({title:"\uc800\uc7a5", trigger:function() {
        EMD.panel.removeControl(EMD.save);
        callback(module, EMD.draw.feature);
        EMD.draw.deactivate();
      }, displayClass:"olControlSaveFeatures"});
      EMD.cancel = new OpenLayers.Control.Button({title:"\ucde8\uc18c", trigger:function() {
        EMD.panel.removeControl(EMD.save);
        EMD.map.addControl(EMD.draw);
        EMD.draw.deactivate();
        callback(module, null);
      }, displayClass:"olControlSaveFeatures"});
      EMD.map.addControl(EMD.draw);
      EMD.draw.activate();
      EMD.panel.addControls([EMD.save, EMD.cancel]);
    } else {
      EMD.edit = new OpenLayers.Control.ModifyFeature(EMD.userLayer.assetsRentDetail, {title:"\uc218\uc815", displayClass:"olControlModifyFeature"});
      EMD.edit.selectFeature(feature);
      EMD.edit.mode = OpenLayers.Control.ModifyFeature.RESHAPE;
      EMD.edit.createVertices = true;
      EMD.save = new OpenLayers.Control.Button({title:"\uc800\uc7a5", trigger:function() {
        EMD.panel.removeControls(EMD.save);
        callback(EMD.edit.feature);
      }, displayClass:"olControlSaveFeatures"});
      EMD.selectControl.handlers.feature.stopDown = true;
      EMD.selectControl.unselectAll();
      EMD.selectControl.deactivate();
      EMD.map.addControl(EMD.edit);
      EMD.edit.activate();
      EMD.panel.addControls([EMD.save]);
    }
  }, updateAssetRentDetailFeature:function(assetsRentDetail, feature) {
    EMD.selectControl.unselectAll();
    var resp = EMD.protocols.gisAssetsCd.read({filter:new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRT_AT_CD", value:assetsRentDetail["prtAtCode"]}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"MNG_YEAR", value:assetsRentDetail["mngYear"]}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"MNG_NO", 
    value:assetsRentDetail["mngNo"]}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"MNG_CNT", value:assetsRentDetail["mngCnt"]}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"ASSETS_USAGE_SEQ", value:assetsRentDetail["assetsUsageSeq"]})]}), callback:function(resp) {
      var ext;
      var f;
      if (resp.features == undefined || resp.features.length == 0) {
        alert("GIS \uc601\uc5ed\uc774 \uc800\uc7a5\ub418\uc9c0 \uc54a\uc558\uc2b5\ub2c8\ub2e4.");
      } else {
        resp.data.src = resp.features[0].clone();
        resp.data.src.state = OpenLayers.State.UPDATE;
        EMD.protocols.assetsRentDetail.update(resp.data.src);
      }
    }});
    resp.data = {obj:assetsRentDetail, src:feature};
  }, setAssetRentFeature:function(module, layerName, obj, srcFeature) {
    var layer = EMD.gis.getUserLayer(layerName);
    var i, feature;
    EMD.selectControl.unselectAll();
    var resp = EMD.protocols.assetsRent.read({filter:new OpenLayers.Filter.Logical({type:OpenLayers.Filter.Logical.AND, filters:[new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"PRT_AT_CD", value:obj["prtAtCode"]}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"MNG_YEAR", value:obj["mngYear"]}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, property:"MNG_NO", value:obj["mngNo"]}), new OpenLayers.Filter.Comparison({type:OpenLayers.Filter.Comparison.EQUAL_TO, 
    property:"MNG_CNT", value:obj["mngCnt"]})]}), callback:function(resp) {
      var ext;
      ext = foundFeatures[0].geometry.getBounds().clone();
      for (var i = 1;i < foundFeatures.length;i++) {
        ext.extend(foundFeatures[i].geometry.getBounds());
      }
      EMD.map.zoomToExtent(ext);
      EMD.modifyFeature = new OpenLayers.Control.ModifyFeature(EMD.userLayer.gisAssetsCd, {handlerOptions:{holeModifier:"altKey"}, featureAdded:function(feature) {
        EMD.gis.storeLayerObject(layer, feature, row[0]);
        EMD.userLayer.gisAssetsCd.addFeatures(e.feature);
        EMD.saveStrategy.save();
      }});
      EMD.map.addControl(modifyFeature);
      modifyFeature.selectFeature(foundFeatures[0]);
    }});
    resp.data = {obj:obj, src:srcFeature};
    for (i = 0;i < len;i++) {
      feature = layer.features[i];
      if (feature && feature.attributes) {
        if (EMD.gis.compareLayerObject(layer, feature, destFeature)) {
          var polygon = feature.geometry;
          feature.geometry = polygon.transform(gisAssetsCdProjection, EMD.map.getProjection());
          EMD.userLayer.gisAssetsCd.addFeatures(feature);
          EMD.saveStrategy.save();
          foundFeatures.push(srcFeature);
        }
      }
    }
    EMD.util.window_hide_all();
    if (foundFeatures.length == 0) {
      EMD.map.addControl(new OpenLayers.Control.DrawFeature(EMD.userLayer.gisAssetsCd, OpenLayers.Handler.Polygon, {handlerOptions:{holeModifier:"altKey"}, featureAdded:function(feature) {
        EMD.gis.storeLayerObject(layer, feature, row[0]);
        EMD.userLayer.gisAssetsCd.addFeatures([feature]);
        EMD.saveStrategy.save();
      }}));
    } else {
      var ext;
      ext = foundFeatures[0].geometry.getBounds().clone();
      for (var i = 1;i < foundFeatures.length;i++) {
        ext.extend(foundFeatures[i].geometry.getBounds());
      }
      EMD.map.zoomToExtent(ext);
      EMD.modifyFeature = new OpenLayers.Control.ModifyFeature(EMD.userLayer.gisAssetsCd, {handlerOptions:{holeModifier:"altKey"}, featureAdded:function(feature) {
        EMD.gis.storeLayerObject(layer, feature, row[0]);
        EMD.userLayer.gisAssetsCd.addFeatures(e.feature);
        EMD.saveStrategy.save();
      }});
      EMD.map.addControl(modifyFeature);
      modifyFeature.selectFeature(foundFeatures[0]);
    }
  }, appendFeature:function(module, layerName, destFeature, srcFeature) {
    var row = module.$("#" + grid).selectedRows();
    var layer = EMD.gis.getUserLayer(layerName);
    var i, feature;
    len = layer.features.length;
    var foundFeatures = [];
    if (row.length == 0) {
      alert("\ud56d\ubaa9\uc744 \uc120\ud0dd \ud558\uc2ed\uc2dc\uc694.");
      return;
    }
    EMD.selectControl.unselectAll();
    for (i = 0;i < len;i++) {
      feature = layer.features[i];
      if (feature && feature.attributes) {
        if (EMD.gis.compareLayerObject(layer, feature, destFeature)) {
          var polygon = feature.geometry;
          feature.geometry = polygon.transform(gisAssetsCdProjection, EMD.map.getProjection());
          EMD.userLayer.gisAssetsCd.addFeatures(feature);
          EMD.saveStrategy.save();
          foundFeatures.push(srcFeature);
        }
      }
    }
    EMD.util.window_hide_all();
    if (foundFeatures.length == 0) {
      EMD.map.addControl(new OpenLayers.Control.DrawFeature(EMD.userLayer.gisAssetsCd, OpenLayers.Handler.Polygon, {handlerOptions:{holeModifier:"altKey"}, featureAdded:function(feature) {
        EMD.gis.storeLayerObject(layer, feature, row[0]);
        EMD.userLayer.gisAssetsCd.addFeatures([feature]);
        EMD.saveStrategy.save();
      }}));
    } else {
      var ext;
      ext = foundFeatures[0].geometry.getBounds().clone();
      for (var i = 1;i < foundFeatures.length;i++) {
        ext.extend(foundFeatures[i].geometry.getBounds());
      }
      EMD.map.zoomToExtent(ext);
      EMD.modifyFeature = new OpenLayers.Control.ModifyFeature(EMD.userLayer.gisAssetsCd, {handlerOptions:{holeModifier:"altKey"}, featureAdded:function(feature) {
        EMD.gis.storeLayerObject(layer, feature, row[0]);
        EMD.userLayer.gisAssetsCd.addFeatures(e.feature);
        EMD.saveStrategy.save();
      }});
      EMD.map.addControl(modifyFeature);
      modifyFeature.selectFeature(foundFeatures[0]);
    }
  }, showEditPanel:function(module) {
    var shape = new OpenLayers.Control.Button({title:"\ud3b8\uc9d1", trigger:function() {
      EMD.modifyFeatyure.mode = OpenLayers.Control.ModifyFeature.RESHAPE;
    }, displayClass:"olControlEditShpaePointFeatures"});
    var move = new OpenLayers.Control.Button({title:"\uc774\ub3d9", trigger:function() {
      EMD.modifyFeatyure.mode = OpenLayers.Control.ModifyFeature.DRAG;
    }, displayClass:"olControlMoveShpaeFeatures"});
    var save = new OpenLayers.Control.Button({title:"\ud3b8\uc9d1 \uc885\ub8cc", trigger:function() {
      if (EMD.edit.feature) {
        EMD.edit.selectControl.unselectAll();
      }
      EMD.saveStrategy.save();
      EMD.map.removeControl(EMD.panel);
    }, displayClass:"olControlFinishEditShapeFeatures"});
    EMD.panel = new OpenLayers.Control.Panel({displayClass:"customEditingToolbar", allowDepress:true});
    EMD.panel.addControls([shape, move, save]);
    EMD.map.addControl(EMD.panel);
  }, showDrawPanel:function() {
    EMD.draw = new OpenLayers.Control.DrawFeature(EMD.wfs, OpenLayers.Handler.Polygon, {title:"\ucd94\uac00", displayClass:"olControlDrawFeaturePolygon", multi:true});
    EMD.edit = new OpenLayers.Control.ModifyFeature(EMD.wfs, {title:"\uc218\uc815", displayClass:"olControlModifyFeature"});
    EMD.del = new DeleteFeature(EMD.wfs, {title:"\uc0ad\uc81c"});
    EMD.save = new OpenLayers.Control.Button({title:"\uc800\uc7a5", trigger:function() {
      if (EMD.edit.feature) {
        EMD.edit.selectControl.unselectAll();
      }
      EMD.saveStrategy.save();
      EMD.map.removeControl(EMD.panel);
    }, displayClass:"olControlSaveFeatures"});
    EMD.panel = new OpenLayers.Control.Panel({displayClass:"customEditingToolbar", allowDepress:true});
    EMD.panel.addControls([EMD.save, EMD.del, EMD.edit, EMD.draw]);
    EMD.map.addControl(EMD.panel);
  }, getParentLayer:function(layerName) {
    var layer = layerName;
    if (typeof layerName == "string") {
      layer = EMD.gis.getUserLayer(layerName);
    }
    if (layer == EMD.userLayer.landCode) {
      return null;
    }
    if (layer == EMD.userLayer.gisAssetsCd) {
      return null;
    }
    if (layer == EMD.userLayer.assetsRent) {
      return EMD.userLayer.assetsRentDetail;
    }
    if (layer == EMD.userLayer.assetsRentDetail) {
      return EMD.userLayer.gisAssetsCd;
    }
    if (layer == EMD.userLayer.assetStats || (layer == EMD.userLayer.gisArchFclty || (layer == EMD.userLayer.gisTeleFclty || (layer == EMD.userLayer.gisMechFclty || (layer == EMD.userLayer.gisElecFclty || (layer == EMD.userLayer.gisCivilFclty || layer == EMD.userLayer.gisArchFclty)))))) {
      return EMD.userLayer.gisAssetsCd;
    }
    return null;
  }, getUserLayer:function(layerName) {
    switch(layerName) {
      case "landCode":
        return EMD.userLayer.landCode;
        break;
      case "gisAssetsCd":
        return EMD.userLayer.gisAssetsCd;
        break;
      case "assetsRent":
        return EMD.userLayer.assetsRent;
        break;
      case "assetsRentDetail":
        return EMD.userLayer.assetsRentDetail;
        break;
      case "assetStats":
        return EMD.userLayer.assetStats;
        break;
      case "gisArchFclty":
        return EMD.userLayer.gisArchFclty;
        break;
      case "gisTeleFclty":
        return EMD.userLayer.gisTeleFclty;
        break;
      case "gisMechFclty":
        return EMD.userLayer.gisMechFclty;
        break;
      case "gisElecFclty":
        return EMD.userLayer.gisElecFclty;
        break;
      case "gisCivilFclty":
        return EMD.userLayer.gisCivilFclty;
        break;
    }
    return null;
  }, getLayerProtocol:function(layerName) {
    var layer = null;
    if (typeof layerName == "string") {
      layer = EMD.gis.getUserLayer(layerName);
      switch(layerName) {
        case "landCode":
          return EMD.protocols.landCode;
          break;
        case "gisAssetsCd":
          return EMD.protocols.gisAssetsCd;
          break;
        case "assetsRent":
          return EMD.protocols.assetsRent;
          break;
        case "assetsRentDetail":
          return EMD.protocols.assetsRentDetail;
          break;
        case "assetStats":
          return EMD.protocols.assetStats;
          break;
        case "gisCivilFclty":
        ;
        case "gisArchFclty":
        ;
        case "gisTeleFclty":
        ;
        case "gisMechFclty":
        ;
        case "gisElecFclty":
          return EMD.protocols.gisPrtFcltyCd;
      }
    } else {
      layer = layerName;
    }
    for (var k in EMD.protocols) {
      if (EMD.protocols[k] != null && (EMD.protocols[k]["layer"] != undefined && EMD.protocols[k].layer == layer)) {
        return EMD.protocols[k];
      }
    }
    if (layer == EMD.userLayer.gisArchFclty || (layer == EMD.userLayer.gisCivilFclty || (layer == EMD.userLayer.gisMechFclty || (layer == EMD.userLayer.gisElecFclty || layer == EMD.userLayer.gisTeleFclty)))) {
      return EMD.protocols.gisPrtFcltyCd;
    }
    return null;
  }, newLayerObject:function(layer, value, newfeature) {
    if (layer == EMD.userLayer.gisAssetsCd) {
    }
  }, checkLayerObject:function(layer, obj) {
    if (layer == EMD.userLayer.gisAssetsCd) {
      var prtAtCode = obj["gisAssetsPrtAtCode"] == undefined ? obj["PRT_CD"] : obj["gisAssetsPrtAtCode"];
      var gisAssetsCd = obj["gisAssetsCd"] == undefined ? obj["ASSETS_CD"] : obj["gisAssetsCd"];
      var gisAssetsSubCd = obj["gisAssetsSubCd"] == undefined ? obj["ASSETS_SCD"] : obj["gisAssetsSubCd"];
      return prtAtCode != undefined && (gisAssetsCd != undefined && (gisAssetsSubCd != undefined && (prtAtCode.length == 3 && (gisAssetsCd.length == 3 && gisAssetsSubCd.length == 2))));
    } else {
      if (layer == EMD.userLayer.assetRentStats) {
        var prtAtCode = obj["prtAtCode"] == undefined ? obj["PRT_CD"] : obj["prtAtCode"];
        var mngYear = obj["mngYear"] == undefined ? obj["MNG_YEAR"] : obj["mngYear"];
        var mngNo = obj["mngNo"] == undefined ? obj["MNG_NO"] : obj["mngNo"];
        var mngCnt = obj["mngCnt"] == undefined ? obj["MNG_CNT"] : obj["mngCnt"];
        return prtAtCode != undefined && (mngYear != undefined && (mngNo != undefined && (mngCnt != undefined && (prtAtCode.length == 3 && (mngYear.length == 4 && (mngNo.length == 3 && mngCnt.length == 2))))));
      } else {
        if (layer == EMD.userLayer.assetsRentDetail) {
          var prtAtCode = obj["prtAtCode"] == undefined ? obj["PRT_CD"] : obj["prtAtCode"];
          var mngYear = obj["mngYear"] == undefined ? obj["MNG_YEAR"] : obj["mngYear"];
          var mngNo = obj["mngNo"] == undefined ? obj["MNG_NO"] : obj["mngNo"];
          var mngCnt = obj["mngCnt"] == undefined ? obj["MNG_CNT"] : obj["mngCnt"];
          var assetsUsageSeq = obj["assetsUsageSeq"] == undefined ? obj["ASSETS_USAGE_SEQ"] : obj["assetsUsageSeq"];
          return prtAtCode != undefined && (mngYear != undefined && (mngNo != undefined && (mngCnt != undefined && (prtAtCode.length == 3 && (mngYear.length == 4 && (mngNo.length == 3 && mngCnt.length == 2))))));
        } else {
          if (layer == EMD.userLayer.gisArchFclty || (layer == EMD.userLayer.gisTeleFclty || (layer == EMD.userLayer.gisMechFclty || (layer == EMD.userLayer.gisElecFclty || layer == EMD.userLayer.gisCivilFclty)))) {
            var gisAssetsPrtAtCode = obj["gisAssetsPrtAtCode"] == undefined ? obj["PRT_CD"] : obj["gisAssetsPrtAtCode"];
            var gisAssetsCd = obj["gisAssetsCd"] == undefined ? obj["ASSETS_CD"] : obj["gisAssetsCd"];
            var gisAssetsSubCd = obj["gisAssetsSubCd"] == undefined ? obj["ASSETS_SCD"] : obj["gisAssetsSubCd"];
            var gisPrtFcltyCd = obj["gisPrtFcltyCd"] == undefined ? obj["FCLTY_CD"] : obj["gisPrtFcltyCd"];
            var gisPrtFcltySeq = obj["gisPrtFcltySeq"] == undefined ? obj["FCLTY_SEQ"] : obj["gisPrtFcltySeq"];
            var gisPrtFcltySe = obj["gisPrtFcltySe"] == undefined ? obj["FCLTY_SE"] : obj["gisPrtFcltySe"];
            return gisAssetsPrtAtCode != undefined && (gisAssetsCd != undefined && (gisAssetsSubCd != undefined && (gisPrtFcltyCd != undefined && (gisPrtFcltySeq != undefined && (gisPrtFcltySe != undefined && (gisAssetsPrtAtCode.length == 3 && (gisAssetsCd.length == 3 && (gisAssetsSubCd.length == 2 && (gisPrtFcltyCd.length == 2 && (gisPrtFcltySeq.length == 4 && gisPrtFcltySe.length == 1))))))))));
          }
        }
      }
    }
    return false;
  }, compareLayerObject:function(layer, feature, obj) {
    if (layer == EMD.userLayer.gisAssetsCd) {
      var prtAtCode = obj["gisAssetsPrtAtCode"] == undefined ? obj["PRT_CD"] : obj["gisAssetsPrtAtCode"];
      var gisAssetsCd = obj["gisAssetsCd"] == undefined ? obj["ASSETS_CD"] : obj["gisAssetsCd"];
      var gisAssetsSubCd = obj["gisAssetsSubCd"] == undefined ? obj["ASSETS_SCD"] : obj["gisAssetsSubCd"];
      return feature.attributes["PRT_CD"] === prtAtCode && (feature.attributes["ASSETS_CD"] === gisAssetsCd && feature.attributes["ASSETS_SCD"] === gisAssetsSubCd);
    } else {
      if (layer == EMD.userLayer.assetRentStats) {
        var prtAtCode = obj["prtAtCode"] == undefined ? obj["PRT_CD"] : obj["prtAtCode"];
        var mngYear = obj["mngYear"] == undefined ? obj["MNG_YEAR"] : obj["mngYear"];
        var mngNo = obj["mngNo"] == undefined ? obj["MNG_NO"] : obj["mngNo"];
        var mngCnt = obj["mngCnt"] == undefined ? obj["MNG_CNT"] : obj["mngCnt"];
        return feature.attributes["PRT_AT_CD"] === prtAtCode && (feature.attributes["MNG_NO"] === mngYear && (feature.attributes["MNG_NO"] === mngNo && feature.attributes["MNG_CNT"] === mngCnt));
      } else {
        if (layer == EMD.userLayer.assetsRentDetail) {
          var prtAtCode = obj["prtAtCode"] == undefined ? obj["PRT_CD"] : obj["prtAtCode"];
          var mngYear = obj["mngYear"] == undefined ? obj["MNG_YEAR"] : obj["mngYear"];
          var mngNo = obj["mngNo"] == undefined ? obj["MNG_NO"] : obj["mngNo"];
          var mngCnt = obj["mngCnt"] == undefined ? obj["MNG_CNT"] : obj["mngCnt"];
          var assetsUsageSeq = obj["assetsUsageSeq"] == undefined ? obj["ASSETS_USAGE_SEQ"] : obj["assetsUsageSeq"];
          return feature.attributes["PRT_AT_CD"] === prtAtCode && (feature.attributes["MNG_NO"] === mngYear && (feature.attributes["MNG_NO"] === mngNo && (feature.attributes["MNG_CNT"] === mngCnt && feature.attributes["ASSETS_USAGE_SEQ"] === assetsUsageSeq)));
        } else {
          if (layer == EMD.userLayer.gisArchFclty || (layer == EMD.userLayer.gisTeleFclty || (layer == EMD.userLayer.gisMechFclty || (layer == EMD.userLayer.gisElecFclty || layer == EMD.userLayer.gisCivilFclty)))) {
            var gisAssetsPrtAtCode = obj["gisAssetsPrtAtCode"] == undefined ? obj["PRT_CD"] : obj["gisAssetsPrtAtCode"];
            var gisAssetsCd = obj["gisAssetsCd"] == undefined ? obj["ASSETS_CD"] : obj["gisAssetsCd"];
            var gisAssetsSubCd = obj["gisAssetsSubCd"] == undefined ? obj["ASSETS_SCD"] : obj["gisAssetsSubCd"];
            var gisPrtFcltyCd = obj["gisPrtFcltyCd"] == undefined ? obj["FCLTY_CD"] : obj["gisPrtFcltyCd"];
            var gisPrtFcltySeq = obj["gisPrtFcltySeq"] == undefined ? obj["FCLTY_SEQ"] : obj["gisPrtFcltySeq"];
            var gisPrtFcltySe = obj["prtFcltySe"] == undefined ? obj["FCLTY_SE"] : obj["prtFcltySe"];
            return feature.attributes["PRT_CD"] === gisAssetsPrtAtCode && (feature.attributes["ASSETS_CD"] === gisAssetsCd && (feature.attributes["ASSETS_SCD"] === gisAssetsSubCd && (feature.attributes["FCLTY_CD"] === gisPrtFcltyCd && (feature.attributes["FCLTY_SEQ"] === gisPrtFcltySeq && feature.attributes["FCLTY_SE"] === gisPrtFcltySe))));
          }
        }
      }
    }
    return false;
  }, storeLayerObject:function(layer, feature, obj) {
    if (layer == EMD.userLayer.gisAssetsCd) {
      if (obj == null) {
        obj = {"PRT_CD":"", "ASSETS_CD":"", "ASSETS_SCD":"", "ASSETS_NM":"\ubbf8\uc815\uc758"};
      }
      var gisAssetsPrtAtCode = obj["gisAssetsPrtAtCode"] == undefined ? obj["PRT_CD"] : obj["gisAssetsPrtAtCode"];
      var gisAssetsCd = obj["gisAssetsCd"] == undefined ? obj["ASSETS_CD"] : obj["gisAssetsCd"];
      var gisAssetsSubCd = obj["gisAssetsSubCd"] == undefined ? obj["ASSETS_SCD"] : obj["gisAssetsSubCd"];
      var gisAssetsNm = obj["gisAssetsNm"] == undefined ? obj["ASSETS_NM"] : obj["gisAssetsNm"];
      feature.attributes["PRT_CD"] = gisAssetsPrtAtCode;
      feature.attributes["ASSETS_CD"] = gisAssetsCd;
      feature.attributes["ASSETS_SCD"] = gisAssetsSubCd;
      feature.attributes["ASSETS_NM"] = gisAssetsNm;
      if (feature.state != OpenLayers.State.INSERT) {
        feature.state = OpenLayers.State.UPDATE;
      }
    } else {
      if (layer == EMD.userLayer.assetsRent) {
        if (obj == null) {
          obj = {"PRT_CD":"", "MNG_YEAR":"", "MNG_NO":"", "MNG_CNT":"", "ENTRPS_NM":"\ubbf8\uc815\uc758", "USAGE_AR":""};
        }
        feature.attributes["PRT_CD"] = obj["PRT_CD"];
        feature.attributes["MNG_YEAR"] = obj["MNG_YEAR"];
        feature.attributes["MNG_NO"] = obj["MNG_YEAR"];
        feature.attributes["MNG_CNT"] = obj["MNG_CNT"];
        feature.attributes["ENTRPS_NM"] = obj["ENTRPS_NM"];
        feature.attributes["USAGE_AR"] = obj["USAGE_AR"];
        if (feature.state != OpenLayers.State.INSERT) {
          feature.state = OpenLayers.State.UPDATE;
        }
      } else {
        if (layer == EMD.userLayer.assetsRentDetail) {
          if (obj == null) {
            obj = {"PRT_CD":"", "MNG_YEAR":"", "MNG_NO":"", "MNG_CNT":"", "ASSET_SEQ":"", "ENTRPS_NM":"\ubbf8\uc815\uc758", "USAGE_AR":"", "FEE":""};
          }
          feature.attributes["PRT_CD"] = obj["PRT_CD"];
          feature.attributes["MNG_YEAR"] = obj["MNG_YEAR"];
          feature.attributes["MNG_NO"] = obj["MNG_YEAR"];
          feature.attributes["MNG_CNT"] = obj["MNG_CNT"];
          feature.attributes["ASSET_SEQ"] = obj["ASSET_SEQ"];
          feature.attributes["ENTRPS_NM"] = obj["ENTRPS_NM"];
          feature.attributes["USAGE_AR"] = obj["USAGE_AR"];
          if (feature.state != OpenLayers.State.INSERT) {
            feature.state = OpenLayers.State.UPDATE;
          }
        } else {
          if (layer == EMD.userLayer.gisArchFclty || (layer == EMD.userLayer.gisTeleFclty || (layer == EMD.userLayer.gisMechFclty || (layer == EMD.userLayer.gisElecFclty || layer == EMD.userLayer.gisCivilFclty)))) {
            if (obj == null) {
              obj = {"PRT_CD":"", "ASSETS_CD":"", "ASSETS_SCD":"", "FCLTY_CD":"", "FCLTY_SEQ":"", "FCLTY_SE":"", "FCLTY_NM":"\ubbf8\uc815\uc758"};
              if (layer == EMD.userLayer.gisArchFclty) {
                obj["FCLTY_SE"] = "A";
              } else {
                if (layer == EMD.userLayer.gisTeleFclty) {
                  obj["FCLTY_SE"] = "I";
                } else {
                  if (layer == EMD.userLayer.gisMechFclty) {
                    obj["FCLTY_SE"] = "M";
                  } else {
                    if (layer == EMD.userLayer.gisElecFclty) {
                      obj["FCLTY_SE"] = "E";
                    } else {
                      if (layer == EMD.userLayer.gisCivilFclty) {
                        obj["FCLTY_SE"] = "C";
                      }
                    }
                  }
                }
              }
            }
            var gisAssetsPrtAtCode = obj["gisAssetsPrtAtCode"] == undefined ? obj["PRT_CD"] : obj["gisAssetsPrtAtCode"];
            var gisAssetsCd = obj["gisAssetsCd"] == undefined ? obj["ASSETS_CD"] : obj["gisAssetsCd"];
            var gisAssetsSubCd = obj["gisAssetsSubCd"] == undefined ? obj["ASSETS_SCD"] : obj["gisAssetsSubCd"];
            var gisPrtFcltyCd = obj["gisPrtFcltyCd"] == undefined ? obj["FCLTY_CD"] : obj["gisPrtFcltyCd"];
            var gisPrtFcltySeq = obj["gisPrtFcltySeq"] == undefined ? obj["FCLTY_SEQ"] : obj["gisPrtFcltySeq"];
            var gisPrtFcltySe = obj["gisPrtFcltySe"] == undefined ? obj["FCLTY_SE"] : obj["prtFcltySe"];
            var prtFcltyNm = obj["prtFcltyNm"] == undefined ? obj["FCLTY_NM"] : obj["prtFcltyNm"];
            feature.attributes["PRT_CD"] = gisAssetsPrtAtCode;
            feature.attributes["ASSETS_CD"] = gisAssetsCd;
            feature.attributes["ASSETS_SCD"] = gisAssetsSubCd;
            feature.attributes["FCLTY_CD"] = gisPrtFcltyCd;
            feature.attributes["FCLTY_SEQ"] = gisPrtFcltySeq;
            feature.attributes["FCLTY_SE"] = gisPrtFcltySe;
            feature.attributes["FCLTY_NM"] = prtFcltyNm;
            if (feature.state != OpenLayers.State.INSERT) {
              feature.state = OpenLayers.State.UPDATE;
            }
          }
        }
      }
    }
    layer.drawFeature(feature);
  }, getWfsData:function(url, option) {
    var shapeParams = OpenLayers.Util.getParameterString({SERVICE:"WFS", VERSION:"1.1.0", REQUEST:"GetFeature", BBOX:postioncd, SRSNAME:"EPSG:900913", OUTPUT:"text/xml;subType=gml/3.1.1/profiles/gmlsf/1.0.0/0", EXCEPTIONS:"text/xml"});
    OpenLayers.loadURL("/proxy/proxy.jsp", "?url=" + escape("http://map.vworld.kr/js/wfs.do?APIKEY=369C4265-766B-31D6-9469-8FB5ECC1BE17&DOMAIN=localhost:8080" + shapeParams), this, getSearchdFeature);
  }, OpenLayersVworld:OpenLayers.Class(OpenLayers.Layer.XYZ, {name:"vworldMap", zoomOffset:6, serverResolutions:[2445.9849047851562, 1222.9924523925781, 611.4962261962891, 305.74811309814453, 152.87405654907226, 76.43702827453613, 38.218514137268066, 19.109257068634033, 9.554628534317017, 4.777314267158508, 2.388657133579254, 1.194328566789627, 0.5971642833948135], sphericalMercator:false, transitionEffect:"resize", buffer:1, displayOutsideMaxExtent:true, initialize:function(name, options) {
    if (options.baseurl != null || options.baseurl.length > 0) {
      options.url = ["http://xdworld.vworld.kr:8080/2d/Base/201310/${z}/${x}/${y}.png"];
    }
    var newArgs = [name, [options.baseurl + "/${z}/${x}/${y}.png"], options];
    OpenLayers.Layer.XYZ.prototype.initialize.apply(this, newArgs);
  }, clone:function(obj) {
    if (obj == null) {
      obj = new OpenLayers.Layer.Vworld(this.name, this.getOptions());
    }
    obj = OpenLayers.Layer.XYZ.prototype.clone.apply(this, [obj]);
    return obj;
  }, getXYZ:function(bounds) {
    var res = this.getServerResolution();
    var z = this.getZoomForResolution(res);
    var x = Math.round((bounds.left - this.maxExtent.left) / (res * this.tileSize.w));
    var y = Math.round((this.maxExtent.top - bounds.top) / (res * this.tileSize.h));
    return{"x":x, "y":y, "z":z};
  }, CLASS_NAME:"OpenLayers.Layer.Vworld"}), OpenLayersDaum:OpenLayers.Class(OpenLayers.Layer.XYZ, {name:"DaumMap", url:["http://i0.maps.daum-img.net/map/image/G03/i/1.04/L${z}/${y}/${x}.png", "http://i1.maps.daum-img.net/map/image/G03/i/1.04/L${z}/${y}/${x}.png", "http://i2.maps.daum-img.net/map/image/G03/i/1.04/L${z}/${y}/${x}.png", "http://i3.maps.daum-img.net/map/image/G03/i/1.04/L${z}/${y}/${x}.png"], sphericalMercator:false, transitionEffect:"resize", buffer:1, displayOutsideMaxExtent:true, 
  initialize:function(name, options) {
    if (!options) {
      options = {resolutions:[2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1, 0.5, 0.25]};
    } else {
      if (!options.resolutions) {
        options.resolutions = [2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1, 0.5, 0.25];
      }
    }
    var newArgs = [name, null, options];
    OpenLayers.Layer.XYZ.prototype.initialize.apply(this, newArgs);
  }, clone:function(obj) {
    if (obj == null) {
      obj = new OpenLayers.Layer.Daum(this.name, this.getOptions());
    }
    obj = OpenLayers.Layer.XYZ.prototype.clone.apply(this, [obj]);
    return obj;
  }, getXYZ:function(bounds) {
    var res = this.getServerResolution();
    var x = Math.round((bounds.left - this.maxExtent.left) / (res * this.tileSize.w));
    var y = Math.round((bounds.bottom - this.maxExtent.bottom) / (res * this.tileSize.h));
    var z = 14 - this.getServerZoom();
    if (this.wrapDateLine) {
      var limit = Math.pow(2, z);
      x = (x % limit + limit) % limit;
    }
    return{"x":x, "y":y, "z":z};
  }, CLASS_NAME:"OpenLayers.Layer.Daum"}), GTransformWMS:OpenLayers.Class(OpenLayers.Layer.WMS, {getURL:function(bounds) {
    bounds = this.adjustBounds(bounds);
    var imageSize = this.getImageSize();
    var newParams = {};
    var reverseAxisOrder = this.reverseAxisOrder();
    newParams.BBOX = this.encodeBBOX ? bounds.toBBOX(null, reverseAxisOrder) : bounds.toArray(reverseAxisOrder);
    newParams.WIDTH = imageSize.w;
    newParams.HEIGHT = imageSize.h;
    if (!(this.params.CRS == this.map.getProjectionObject().projCode)) {
      var point = new OpenLayers.Geometry.Point(bounds.left, bounds.bottom);
      var minGeom = OpenLayers.Projection.transform(point, this.map.projection, this.projection);
      point = new OpenLayers.Geometry.Point(bounds.right, bounds.top);
      var maxGeom = OpenLayers.Projection.transform(point, this.map.projection, this.projection);
      newParams.BBOX = [minGeom.x, minGeom.y, maxGeom.x, maxGeom.y];
    }
    var requestString = this.getFullRequestString(newParams);
    return requestString;
  }, getFullRequestString:function(newParams, altUrl) {
    var mapProjection = this.map.getProjectionObject();
    var value = "EPSG:5186";
    if (parseFloat(this.params.VERSION) >= 1.3) {
      this.params.CRS = value;
    } else {
      this.params.SRS = value;
    }
    if (typeof this.params.TRANSPARENT == "boolean") {
      newParams.TRANSPARENT = this.params.TRANSPARENT ? "TRUE" : "FALSE";
    }
    return OpenLayers.Layer.Grid.prototype.getFullRequestString.apply(this, arguments);
  }, CLASS_NAME:"GTransformWMS"}), fnRequestWFS:function(filterText, success, failure) {
    geometry = filterText;
    try {
      var successCall = null;
      var failureCall = null;
      if (typeof success == "function") {
        successCall = success;
      }
      if (typeof failure == "function") {
        failureCall = failure;
      }
      OpenLayers.ProxyHost = "/proxy.do?url=";
      var buffer = document.getElementById("buffer").value;
      var prop_name = document.getElementById("prop_name").value;
      var prop_val = document.getElementById("prop_val").value;
      var prop_opr = document.getElementById("prop_opr").value;
      var params = "";
      params += "apiKey=" + dataApiKey;
      if (geometry != "") {
        params += "&geometry=" + geometry;
      } else {
        if (vCurNaviCode != "") {
          params += "&emdCd=" + vCurNaviCode;
        }
      }
      if (prop_val != "") {
        params += "&filter=" + prop_name + ":" + prop_opr + ":" + encodeURI(prop_val);
      }
      params += "&output=" + output;
      params += "&srsName=EPSG:5186";
      if (buffer > 0) {
        params += "&buffer=" + buffer;
      }
      if (pageInfo.pageIndex > 1) {
        params += "&pageIndex=" + pageInfo.pageIndex;
      }
      params += "&pageUnit=" + pageInfo.pageUnit;
      serviceId = $("#service").val();
      var reqConfig = OpenLayers.Util.extend({url:reqUrl + serviceId + "/data?" + params, headers:{"Content-Type":"text/plain"}, success:successCall, failure:failureCall, scope:this}, {method:"GET"});
      OpenLayers.Request.issue(reqConfig);
    } catch (e) {
      alert("\uacf5\uac04\uc815\ubcf4\uc870\ud68c\ub97c \uc2e4\ud328\ud558\uc600\uc2b5\ub2c8\ub2e4.\n\n" + e.message);
    }
  }, addFeature:function(mode, param) {
    EMD.draw._mode = {mode:mode, param:param};
    EMD.map.addControl(EMD.panel);
  }, cancelFeature:function() {
    EMD.map.removeControl(EMD.panel);
  }, addAssetCdFeature:function(gisAssetsCd, retfunc) {
  }, showBupJungDongCode:function(code) {
    OpenLayers.Request.GET({url:escape("http://map.vworld.kr/js/wfs.do"), params:{SERVICE:"WFS", VERSION:"1.1.0", REQUEST:"GetFeature", APIKEY:"5E7D605D-BD5A-3BEE-8C20-62172A86B4AA", DOMAIN:"http://localhost", BBOX:"14179985.99,4077303.96,14266206.96,4183704.31", TYPENAME:"LP_PA_CBND_BUBUN", emdCd:code, filter:"STD_SGGCD:like:" + code.substring(0, 5), SRSNAME:"EPSG:900913", OUTPUT:"text/xml;subType=gml/3.1.1/profiles/gmlsf/1.0.0/0", EXCEPTIONS:"text/xml"}, callback:function(response) {
      var g = new OpenLayers.Format.GML;
    }});
  }, showFeature:function(features) {
  }, getLotAreaFeature:function(response) {
    var g = new OpenLayers.Format.GML;
    features = g.read(response.responseText);
  }, makePopup:function(popup, feature) {
    $("button[data-role='assetInqire']", popup.contentDiv).button({icons:{primary:"ui-icon-folder-open"}}).click(function(event) {
      EMD.util.create_window("gamAssetCodeList", $(this).text(), "/code/assets/gamAssetCodeList.do", null, {action:"prtFcltyInqire", gisPrtAtCode:$(this).data("assets-prt-at-code"), gisAssetsCd:$(this).data("assets-cd"), gisAssetsSubCd:$(this).data("assets-sub-cd")});
    });
    $("button[data-role='removeFeature']", popup.contentDiv).button({icons:{primary:"ui-icon-trash"}}).click({feature:feature}, function(event) {
      EMD.map.removePopup(event.data.feature.popup);
      event.data.feature.state = OpenLayers.State.DELETE;
      EMD.userLayer.gisAssetsCd.removeFeatures([event.data.feature]);
      EMD.saveStrategy.save();
    });
    popup.updateSize();
  }}, user:{hasAuth:function(authorCode) {
    var roles = authorCode.split(",");
    if (EMD.userinfo != null && (EMD.userinfo.authorities != null && EMD.userinfo.authorities.length > 0)) {
      for (var i = 0;i < EMD.userinfo.authorities.length;i++) {
        for (var j = 0;j < roles.length;j++) {
          if (EMD.userinfo.authorities[i] == roles[j]) {
            return true;
          }
        }
      }
    }
    return true;
  }, userId:function() {
    return EMD.userinfo["userId"];
  }}, util:{printPageGrid:function(module, url, gridNm, searchOptNm) {
    var params = {};
    if (searchOptNm != null) {
      params = module.getFormValues("#" + searchOptNm);
    }
    module.printPage(url, params);
  }, printPageValue:function(module, url, searchOptNm) {
    var params = {};
    if (searchOptNm != null) {
      params = module.getFormValues("#" + searchOptNm);
    }
    module.printPage(url, params);
  }, printObj:function(obj) {
    if (typeof JSON !== "undefined") {
      return JSON.stringify(obj, function(k, v) {
        if (typeof v === "number" && !isFinite(v)) {
          return String(v);
        }
        return v;
      });
    }
    var arr = [];
    $.each(obj, function(key, val) {
      var next = key + ": ";
      next += $.isPlainObject(val) ? printObj(val) : val;
      arr.push(next);
    });
    return "{ " + arr.join(", ") + " }";
  }, showHelp:function(url) {
    var win = window.open(url, "helpPage", "width=940, height=800,toolbar=yes, resizable=yes, menubar=no,status=no,scrollbars=yes");
  }, getDate:function(d) {
    if (d == null) {
      d = new Date;
    }
    var s = EMD.util.leftPad(d.getFullYear(), "0", 4) + "-" + EMD.util.leftPad(d.getMonth() + 1, "0", 2) + "-" + EMD.util.leftPad(d.getDate(), "0", 2);
    return s;
  }, compareDate:function(a, b) {
    if (typeof a == "string") {
      a = EMD.strToDate(a);
    }
    if (typeof b == "string") {
      b = EMD.strToDate(b);
    }
    return isFinite(a = this.convert(a).valueOf()) && isFinite(b = this.convert(b).valueOf()) ? (a > b) - (a < b) : NaN;
  }, inRangeDate:function(d, s, e) {
    return isFinite(d = this.convert(d).valueOf()) && (isFinite(start = this.convert(start).valueOf()) && isFinite(end = this.convert(end).valueOf())) ? start <= d && d <= end : NaN;
  }, getTimeStamp:function(d) {
    if (d == null) {
      d = new Date;
    }
    var s = EMD.util.leftPad(d.getFullYear(), "0", 4) + "-" + EMD.util.leftPad(d.getMonth() + 1, "0", 2) + "-" + EMD.util.leftPad(d.getDate(), "0", 2) + " " + EMD.util.leftPad(d.getHours(), "0", 2) + ":" + EMD.util.leftPad(d.getMinutes(), "0", 2) + ":" + EMD.util.leftPad(d.getSeconds(), "0", 2);
    return s;
  }, addMonths:function(md, m) {
    var date = md;
    if (m == null) {
      date = new Date;
    } else {
      md = m;
    }
    return new Date(date.setMonth(date.getMonth() + md));
  }, addDates:function(dd, d) {
    var date = dd;
    if (d == undefined) {
      date = new Date;
    } else {
      dd = d;
    }
    return new Date(date.setDate(date.getDate() + dd));
  }, leftPad:function(n, c, digits) {
    var zero = "";
    n = n.toString();
    if (n.length < digits) {
      for (var i = 0;i < digits - n.length;i++) {
        zero += c;
      }
    }
    return zero + n;
  }, showMessage:function(obj, msg) {
    var popup = new dhtmlXPopup;
    popup.attachHTML(msg);
    if (obj["length"] != undefined) {
      obj = obj[0];
    }
    if ($(obj).is(":input")) {
      if ($(obj).hasClass("ygpaCmmnCd") || ($(obj).hasClass("ygpaFilterCode") || ($(obj).hasClass("ygpaDeptSelect") || ($(obj).hasClass("ygpaCmmnCl") || ($(obj).hasClass("ygpaCmmnCode") || $(obj).hasClass("ygpaYnSelect")))))) {
        obj = $("#" + $(obj).attr("id") + "_select")[0];
        if (obj == null) {
          alert(mag);
        }
      }
      $(obj).focus();
      var x = window.dhx4.absLeft(obj);
      var y = window.dhx4.absTop(obj);
      var w = obj.offsetWidth;
      var h = obj.offsetHeight;
      popup.show(x, y, w, h);
      $(obj).blur({popup:popup}, function(e) {
        if (e.data.popup) {
          e.data.popup.unload();
          e.data.popup = null;
        }
      });
      return popup;
    }
    return null;
  }, showErrorMessage:function(obj, msg) {
    var popup = new dhtmlXPopup;
    popup.attachHTML(msg);
    if ($(obj).is(":input")) {
      var x = window.dhx4.absLeft(obj);
      var y = window.dhx4.absTop(obj);
      var w = obj.offsetWidth;
      var h = obj.offsetHeight;
      popup.show(x, y, w, h);
      $(obj).blur({popup:popup}, function(e) {
        if (e.data.popup) {
          e.data.popup.unload();
          e.data.popup = null;
        }
      });
      return popup;
    }
    return null;
  }, addCommas:function(nStr) {
    nStr += "";
    x = nStr.split(".");
    x1 = x[0];
    x2 = x.length > 1 ? "." + x[1] : "";
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
      x1 = x1.replace(rgx, "$1" + "," + "$2");
    }
    return x1 + x2;
  }, strToDate:function(dStr) {
    var regiDate = dStr.replace(/-/g, "/");
    return new Date(Date.parse(regiDate));
  }, isDate:function(txtDate) {
    var currVal = txtDate;
    if (currVal == "") {
      return false;
    }
    var rxDatePattern = /^(\d{1,4})-(\d{1,2})-(\d{2})$/;
    var dtArray = currVal.match(rxDatePattern);
    if (dtArray == null) {
      return false;
    }
    dtYear = dtArray[1];
    dtMonth = dtArray[2];
    dtDay = dtArray[3];
    if (dtMonth < 1 || dtMonth > 12) {
      return false;
    } else {
      if (dtDay < 1 || dtDay > 31) {
        return false;
      } else {
        if ((dtMonth == 4 || (dtMonth == 6 || (dtMonth == 9 || dtMonth == 11))) && dtDay == 31) {
          return false;
        } else {
          if (dtMonth == 2) {
            var isleap = dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0);
            if (dtDay > 29 || dtDay == 29 && !isleap) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }, getLoginUserVO:function() {
    return EMD.userinfo;
  }, showProgress:function() {
    if (!$("#progress_dialog").dialog("isOpen")) {
      $("#progress_dialog").dialog("open");
    }
  }, hideProgress:function() {
    if ($("#progress_dialog").dialog("isOpen")) {
      $("#progress_dialog").dialog("close");
    }
  }, clear_active:function() {
    $("a.active, tr.active").removeClass("active");
    $("ul.menu").hide();
  }, objectToArray:function(obj) {
    var ret = [];
    for (var k in obj) {
      ret[ret.length] = {name:k, value:obj[k]};
    }
    return ret;
  }, create_window:function(progrmFileNm, win_title, win_url, argv, init_params, parent) {
    var x = "#window_" + EMD.window_seq;
    var dock_id = "#dock_" + EMD.window_seq;
    EMD.window_seq++;
    var li = document.createElement("li");
    li.id = dock_id.substring(1);
    li.innerHTML = '<a href="' + x + '">' + win_title + "</a>";
    $("#dock")[0].appendChild(li);
    var win = document.createElement("div");
    win.id = x.substring(1);
    win.style.display = "none";
    if (progrmFileNm != null && progrmFileNm != "") {
      win.innerHTML = '<div class="abs window_inner">' + '<div class="window_top ui-widget-header">' + '<span class="float_left">' + win_title + '</span> <span class="float_right"><a href="#' + progrmFileNm + '" class="window_help"><span class="ui-icon ui-icon-help"></span></a> <a href="#' + win.id + '" class="window_min"></a><a href="#' + win.id + '" class="window_resize"></a> <a href="#' + win.id + '" class="window_close"></a></span>' + '</div><div class="abs window_content"></div></div><div class="abs window_bottom ">\uc5ec\uc218\uad11\uc591\ud56d\ub9cc\uacf5\uc0ac \uc9c0\ub3c4\ud504\ub808\uc784\uc6cc\ud06c ver : 2015.04.14.001</div><span class="abs ui-resizable-handle ui-resizable-se"></span>'
      ;
    } else {
      win.innerHTML = '<div class="abs window_inner">' + '<div class="window_top ui-widget-header">' + '<span class="float_left">' + win_title + '</span> <span class="float_right"><a href="#' + win.id + '" class="window_min"></a><a href="#' + win.id + '" class="window_resize"></a> <a href="#' + win.id + '" class="window_close"></a></span>' + '</div><div class="abs window_content"></div></div><div class="abs window_bottom ">\uc5ec\uc218\uad11\uc591\ud56d\ub9cc\uacf5\uc0ac \uc9c0\ub3c4\ud504\ub808\uc784\uc6cc\ud06c ver : 2015.04.14.001</div><span class="abs ui-resizable-handle ui-resizable-se"></span>'
      ;
    }
    $("#desktop")[0].appendChild(win);
    $(x).addClass("abs");
    $(x).addClass("window");
    $(x)[0]._initParam = init_params;
    if (win_url != undefined) {
      EMD.util.showProgress();
      $(x + " .window_content").load(EMD.context_root + win_url, {"window_id":x.substring(1), "args":argv}, function(response, status, xhr) {
        EMD.util.hideProgress();
        if (status == "error") {
          var msg = "\uc5d0\ub7ec\uac00 \ubc1c\uc0dd\ud588\uc2b5\ub2c8\ub2e4. \uc5d0\ub7ec\ubc88\ud638: ";
          alert(msg + xhr.status + " " + xhr.statusText);
        } else {
          if (module_instance.getModuleType() == "_EMD_ERROR_MODULE") {
            module_instance.loadComplete();
            alert(module_instance.getMessge());
            EMD.util.window_close($(this).closest(".window"));
            return;
          }
          var win_id = $("#window_id").val();
          if (win_id == undefined) {
            alert("\uc11c\ubc84 \uc624\ub958\ub85c \uc778\ud558\uc5ec \ud398\uc774\uc9c0\ub97c \ub85c\ub4dc\ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.");
            EMD.util.window_close($(this).closest(".window"));
            return;
          }
          var win_id_num = win_id.substring(win_id.lastIndexOf("_") + 1) * 1;
          $("#window_id").remove();
          $("#" + win_id)[0].module = module_instance;
          module_instance.setModuleId(win_id_num);
          module_instance.setParent(parent);
          EMD.module_instance[win_id_num] = module_instance;
          module_instance.setModuleId(win_id);
          EMD.util.modify_window(module_instance, win_id);
          var x, y, w, h;
          x = EMD.win_x;
          y = EMD.win_y;
          w = EMD.module_instance[win_id_num].getWidth();
          h = EMD.module_instance[win_id_num].getHeight();
          if ($(document).height() - 48 < y + h) {
            y = $(document).height() - h - 28;
            if (y < 20) {
              y = 20;
            }
          }
          if ($(document).width() < x + w) {
            x = 0;
          }
          EMD.util.window_move(win_id, x, y);
          EMD.util.window_size(win_id, w, h);
          $("#" + win_id).resizable({create:function(event, ui) {
          }, resize:function(event, ui) {
            event.stopPropagation();
            var win = $(this);
            $(this).find(".window_main").each(function() {
              $(this).css("height", win.height() - 54);
            });
            EMD.util.window_resized(ui.element);
          }});
          $("#" + win_id).find(':input[data-required="true"]').each(function() {
            var th = $(this).parent("td").prev();
            if (th.data("required") == null) {
              th.append("<span style='color:red; font-weight:bold; float:right;'>*</span>");
              th.data("required", "true");
            }
          });
          EMD.module_instance[win_id_num].loadComplete($(this).closest(".window")[0]._initParam);
          EMD.util.window_flat();
          $("#" + win_id).addClass("window_stack").show("drop", {}, 600, function() {
            EMD.util.window_resized($(this));
          });
          $($("#" + win_id)).find(".window_main").each(function() {
            $(this).css("height", $("#" + win_id).height() - 54);
          });
          EMD.util.window_resized($("#" + win_id));
          EMD.util.clear_active();
        }
      });
    }
    return win.id;
  }, window_resized:function(win) {
    var resizeFunc = function(func, resizeDiv) {
      var resizeFill = null;
      var items = $(resizeDiv).children();
      var h = $(resizeDiv).height();
      for (var i = 0;i < items.length;i++) {
        var item = items[i];
        if ($(item).hasClass("fillHeight")) {
          resizeFill = item;
        } else {
          if (!$(item).is("script") || ($(item).is(".emdTabPage") || $(item).is(".viewStack")) && $(item).attr("display") != "none") {
            h -= $(item).outerHeight(true);
          }
        }
      }
      if (resizeFill != null) {
        h -= $(resizeFill).outerHeight(true) - Math.ceil($(resizeFill).height());
        if (h > 0) {
          if ($(resizeFill).is(".emdTabPanel")) {
            $(resizeFill).height(h);
            $(resizeFill).tabs("option", "heightStyle", "fill");
            $(resizeFill).children(".emdTabPage").each(function() {
              func(func, this);
            });
          } else {
            var overflow = $(resizeFill).parent().css("overflow");
            $(resizeFill).parent().css("overflow", "hidden");
            $(resizeFill).height(h);
            if ($(resizeFill).hasClass("gridbox")) {
              $(resizeFill).width($(resizeFill).parent().width() - 4);
              $(resizeFill).resizeGrid();
            } else {
              func(func, resizeFill);
            }
            $(resizeFill).parent().css("overflow", overflow);
          }
        }
      }
    };
    if ($(win).is("#__dialog-modal")) {
      resizeFunc(resizeFunc, $(win).find(".dialog")[0]);
    } else {
      resizeFunc(resizeFunc, $(win).find(".window_main")[0]);
    }
  }, window_flat:function() {
    $("div.window_stack").removeClass("window_stack");
  }, window_show:function(win) {
    $("div.window").removeClass("window_stack");
    win.addClass("window_stack").css({"top":win.data("t"), "left":win.data("l"), "right":win.data("r"), "bottom":win.data("b"), "width":win.data("w"), "height":win.data("h")}).show();
  }, window_move:function(win_id, x, y) {
    $("#" + win_id).css({"left":x, "top":y});
  }, window_size:function(win_id, w, h) {
    $("#" + win_id).css({"width":w, "height":h});
  }, window_resize:function(win) {
    if (win.hasClass("window_full")) {
      win.removeClass("window_full").css({"top":win.data("t"), "left":win.data("l"), "right":win.data("r"), "bottom":win.data("b"), "width":win.data("w"), "height":win.data("h")});
    } else {
      win.data("t", win.css("top"));
      win.data("l", win.css("left"));
      win.data("r", win.css("right"));
      win.data("b", win.css("bottom"));
      win.data("w", win.css("width"));
      win.data("h", win.css("height"));
      win.addClass("window_full").css({"top":"0", "left":"0", "right":"0", "bottom":"0", "width":"100%", "height":"100%"});
    }
    EMD.util.window_flat();
    win.addClass("window_stack");
    EMD.util.window_resized(win);
  }, window_hide_all:function() {
    $("div.window").each(function() {
      EMD.util.window_hide($(this));
    });
  }, window_hide:function(win) {
    var dock_id = win.attr("id").replace("window", "dock");
    win.data("t", win.css("top"));
    win.data("l", win.css("left"));
    win.data("r", win.css("right"));
    win.data("b", win.css("bottom"));
    win.data("w", win.css("width"));
    win.data("h", win.css("height"));
    win.effect("transfer", {to:"#" + dock_id, className:".ui-effects-transfer"}, 300, function() {
      win.removeAttr("style").hide();
    });
  }, window_close:function(win) {
    var dock_id = win.attr("id").replace("window", "dock");
    win.hide(function() {
      $(this).remove();
    });
    $("#" + dock_id).hide("fast");
    $("#" + dock_id).remove();
  }, last_window_close:function() {
    win = $("#window_" + (EMD.window_seq - 1));
    var dock_id = win.attr("href").replace("window", "dock");
    $(win.attr("href")).hide("scale", {percent:0}, 1E3, function() {
      $(this).remove();
    });
    $(dock_id).hide("fast");
    $(dock_id).remove();
  }, save_feature:function() {
  }, edit_feature:function(win, feature) {
    if (feature != undefined || feature != null) {
      EMD.wfs.addFeatures(feature);
    }
    EMD.edit_win = win;
  }, select:function(selector) {
  }, modify_id:function(win_id, modify_item) {
    if (!modify_item.children().length) {
      return;
    }
    modify_item.children().each(function() {
      if ($(this).children().length > 0) {
        EMD.util.modify_id(win_id, $(this));
      }
      if ($(this).attr("id") != undefined) {
        $(this).attr("id", win_id + "_" + $(this).attr("id"));
      }
      if ($(this).attr("data-grid") != undefined) {
        $(this).attr("data-grid", win_id + "_" + $(this).attr("id"));
      }
    });
  }, modify_window:function(module_instance, win_id) {
    var win = $("#" + win_id);
    EMD.util.modify_id(win_id, $("#" + win_id));
    win.data("_win_id", win_id);
    win.find(".emdTabPanel").data("_win_id", win_id);
    win.find(".emdTabPanel").each(function() {
      $(this).find(".emdTab").data("_win_id", $(this).data("_win_id"));
      $(this).find(".emdTab").each(function() {
        $(this).attr("href", $(this).attr("href").replace("#", "#" + $(this).data("_win_id") + "_"));
      });
      $(this).tabs({beforeActivate:function(event, ui) {
        var onchangeId = $(this).data("onchange-before");
        var oldId = ui.oldPanel.attr("id").replace($(this).closest(".window").data("_win_id") + "_", "");
        var newId = ui.newPanel.attr("id").replace($(this).closest(".window").data("_win_id") + "_", "");
        if (onchangeId != null) {
          return $(this).closest(".window")[0].module[onchangeId](newId, oldId);
        }
      }, activate:function(event, ui) {
        var onchangeId = $(this).data("onchange");
        var oldId = ui.oldPanel.attr("id").replace($(this).closest(".window").data("_win_id") + "_", "");
        var newId = ui.newPanel.attr("id").replace($(this).closest(".window").data("_win_id") + "_", "");
        if (onchangeId != null) {
          $(this).closest(".window")[0].module[onchangeId](newId, oldId);
        }
      }, select:function(e, i) {
        currentTab = i.index;
      }, nextTab:function(e) {
        currentTab = currentTab < $(this).tabs("length") - 1 ? currentTab + 1 : 0;
      }});
    });
    win.find(".viewStack").each(function() {
      $(this).changeViewId = function(index) {
        $(this)._viewStackId = index;
        $(this).find(".viewPanel").each(function(id) {
          if (id != $(this).parent(".viewStack")._viewStackId) {
            $(this).css("display", "none");
          } else {
            $(this).css("display", "block");
          }
        });
        $(this).closest(".window_main").trigger("resizeWindow");
      };
      $(this).find(".viewPanel").each(function(index) {
        if (index != 0) {
          $(this).css("display", "none");
        }
      });
      $(this)[0].changePanelId = function(num) {
        $(this).find(".viewPanel").each(function(index) {
          if (index == num) {
            $(this).css("display", "block");
          } else {
            $(this).css("display", "none");
          }
        });
        EMD.util.window_resized($(this).closest(".window"));
      };
    });
    win.find("button").each(function() {
      var id = $(this).data("cmd");
      var but = $(this);
      var isbutton = false;
      var icon = null;
      if (id == null) {
        id = $(this).attr("id") == null || $(this).attr("id").length < win_id.length + 1 ? $(this).attr("class") : $(this).attr("id").substring(win_id.length + 1);
      }
      if (but.attr("class") != null) {
        var classes = $(this).attr("class").split(/\s+/);
        $.each(classes, function(index, item) {
          switch(item) {
            case "buttonRegist":
              icon = "ui-icon-check";
              break;
            case "buttonAdd":
              icon = "ui-icon-plusthick";
              break;
            case "buttonDelete":
              icon = "ui-icon-minusthick";
              break;
            case "buttonTrash":
              icon = "ui-icon-trash";
              break;
            case "buttonSave":
              icon = "ui-icon-disk";
              break;
            case "buttonSearch":
              icon = "ui-icon-search";
              break;
            case "popupButton":
              icon = "ui-icon-newwin";
              break;
            case "buttonExcel":
              icon = "ui-icon-document";
              break;
            case "popupDialog":
              icon = "ui-icon-newwin";
              break;
            default:
              icon = $(this).data("icon");
              break;
          }
          if (icon != null && icon.indexOf("ui-icon-") === 0) {
            $(but).button({icons:{primary:icon}, text:true}).click({module:win[0].module, button_id:id}, function(event) {
              event.preventDefault();
              if (id == "buttonSearch") {
                event.data.module.onSubmit();
              } else {
                EMD.util._clickButtonExec(event.data.module, $(this), event.data.button_id);
              }
            });
            isbutton = true;
            return false;
          }
        });
        if (isbutton == false) {
          $(but).button().click({module:win[0].module, button_id:id}, function(event) {
            event.preventDefault();
            EMD.util._clickButtonExec(event.data.module, $(this), event.data.button_id);
          });
        }
      } else {
        if (id == null) {
          id = $(but).data("id");
        }
        if (id == null) {
          id = $(but).data("role");
        }
        var label = $(but).text();
        icon = null;
        for (var i = 0;i < buttonIcons.length;i++) {
          if (buttonIcons[i].label == label) {
            icon = buttonIcons[i].icon;
            break;
          }
        }
        if (icon != null) {
          $(but).button({icons:{primary:icon}, text:true}).click({module:win[0].module, button_id:id}, function(event) {
            event.preventDefault();
            EMD.util._clickButtonExec(event.data.module, $(this), event.data.button_id);
          });
        } else {
          $(but).button().click({module:win[0].module, button_id:id}, function(event) {
            event.preventDefault();
            EMD.util._clickButtonExec(event.data.module, $(this), event.data.button_id);
          });
        }
      }
    });
    $("#" + win_id).find("input.mngYear").each(function() {
      $(this).attr("maxlength", 4);
      $(this).on("keyup", function(event) {
        if ($(this).val().length == 4) {
          $(this).next(":input.mngNo").focus();
        }
      });
    });
    $("#" + win_id).find("input.mngNo").each(function() {
      $(this).attr("maxlength", 3);
      $(this).on("keyup", function(event) {
        if ($(this).val().length == 3) {
          $(this).next(":input.mngCnt").focus();
        }
      });
    });
    $("#" + win_id).find("input.mngCnt").each(function() {
      $(this).attr("maxlength", 2);
    });
    $("#" + win_id).find("input.gisAssetsCd").each(function() {
      $(this).attr("maxlength", 3);
      $(this).on("keyup", function(event) {
        var v = $(this).val();
        if (v.length == 3) {
          $(this).val(v.toUpperCase());
          $(this).next(":input.gisAssetsSubCd").focus();
        }
      });
    });
    $("#" + win_id).find("input.gisAssetsSubCd").each(function() {
      $(this).attr("maxlength", 2);
    });
    $("#" + win_id).find("input.ygpaDeptSelect").each(function() {
      var default_prompt = "";
      var default_value = "";
      var input_item = this;
      var input_id = $(this).attr("id");
      if ($(this).data("default-prompt") != null) {
        if ($(this).data("default-prompt") != null) {
          default_prompt = $(this).data("default-prompt");
        } else {
          default_prompt = "\uc120\ud0dd";
        }
        if ($(this).data("default-value") != null) {
          default_value = $(this).data("default-value");
        } else {
          default_value = "";
        }
      }
      if ($(this).data("display-value") == null) {
        try {
          $(this).attr("type", "hidden");
        } catch (err) {
          $(this).attr("id", input_id + "_backup");
          $(this).after('<input type="hidden" id="' + input_id + '" />');
          input_item = $("#" + win_id + " #" + input_id)[0];
          $(this).remove();
        }
        $(input_item).after('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
      } else {
        if ($(this).data("display-value") == "N") {
          $(this).attr("readonly", "readonly");
          $(this).before('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
        } else {
          $(this).attr("readonly", "readonly");
          $(this).after('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
        }
      }
      if (default_prompt != "") {
        $("#" + win_id).find("#" + input_id + "_select").append('<option value="' + default_value + '" selected>' + default_prompt + "</option>");
      }
      $(this).focus(function() {
        $("#" + $(this).attr("id") + "_select")[0].focus();
      });
      $.ajax({url:EMD.context_root + "/cmmn/selectDeptCodeOptionsList.do", type:"POST", module:input_item, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"cmd", value:""}], success:function(data) {
        if (data.resultCode != null && data.resultCode != 0) {
          if (data.resultCode == 1) {
            alert(data.resultMsg);
            location.reload();
          }
        } else {
          var selected_code = "";
          var comp_id = "#" + $(this.module).attr("id") + "_select";
          if ($(this.module).val() != null) {
            selected_code = $(this.module).val();
            for (var i = 0;i < data.resultList.length;i++) {
              if (selected_code == data.resultList[i].value) {
                $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '" selected>' + data.resultList[i].name + "</option>");
              } else {
                $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '">' + data.resultList[i].name + "</option>");
              }
            }
          } else {
            for (var i = 0;i < data.resultList.length;i++) {
              $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '">' + data.resultList[i].name + "</option>");
            }
          }
          $("#" + win_id).find(comp_id).change({module:this.module}, function(event) {
            $(event.data.module).val($(this).find("option:selected").val());
            $(event.data.module).trigger("change");
          });
          if ($(this.module).attr("readonly") == "readonly") {
            $(comp_id).attr("readonly", "readonly");
          }
          if ($(this.module).attr("disabled") == "disabled") {
            $(comp_id).attr("disabled", "disabled");
          }
        }
      }, error:function(XMLHttpRequest, textStatus, errorThrown) {
        try {
        } catch (e) {
        }
      }});
    });
    $("#" + win_id).find("input.ygpaCmmnCl").each(function() {
      var default_prompt = "";
      var default_value = "";
      var input_item = this;
      var input_id = $(this).attr("id");
      if ($(this).data("default-prompt") != null) {
        if ($(this).data("default-prompt") != null) {
          default_prompt = $(this).data("default-prompt");
        } else {
          default_prompt = "\uc120\ud0dd";
        }
        if ($(this).data("default-value") != null) {
          default_value = $(this).data("default-value");
        } else {
          default_value = "";
        }
      }
      if ($(this).data("display-value") == null) {
        try {
          $(this).attr("type", "hidden");
        } catch (err) {
          $(this).attr("id", input_id + "_backup");
          $(this).after('<input type="hidden" id="' + input_id + '" />');
          input_item = $("#" + win_id + " #" + input_id)[0];
          $(this).remove();
        }
        $(input_item).after('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
      } else {
        if ($(this).data("display-value") == "N") {
          $(this).attr("readonly", "readonly");
          $(this).before('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
        } else {
          $(this).attr("readonly", "readonly");
          $(this).after('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
        }
      }
      if (default_prompt != "") {
        if ($(input_item).data("value") == null) {
          $("#" + win_id).find("#" + input_id + "_select").append('<option value="' + default_value + '" selected>' + default_prompt + "</option>");
        } else {
          $("#" + win_id).find("#" + input_id + "_select").append('<option value="' + default_value + '">' + default_prompt + "</option>");
        }
      }
      $(this).focus(function() {
        $("#" + $(this).attr("id") + "_select")[0].focus();
      });
      $.ajax({url:EMD.context_root + "/cmmn/selectCmmnClOptionsList.do", type:"POST", module:input_item, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[], success:function(data) {
        if (data.resultCode != null && data.resultCode != 0) {
          if (data.resultCode == 1) {
            alert(data.resultMsg);
            location.reload();
          }
        } else {
          var selected_code = "";
          var comp_id = "#" + $(this.module).attr("id") + "_select";
          $("#" + win_id).find(comp_id).empty();
          if ($(this.module).val() != null) {
            selected_code = $(this.module).val();
            for (var i = 0;i < data.resultList.length;i++) {
              if (selected_code == data.resultList[i].value) {
                $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '" selected>' + data.resultList[i].name + "</option>");
              } else {
                $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '">' + data.resultList[i].name + "</option>");
              }
            }
          } else {
            for (var i = 0;i < data.resultList.length;i++) {
              $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '">' + data.resultList[i].name + "</option>");
            }
          }
          $("#" + win_id).find(comp_id).change({module:this.module}, function(event) {
            $(event.data.module).val($(this).find("option:selected").val());
            $(event.data.module).trigger("change");
          });
          $("#" + win_id).find(comp_id).trigger("change");
          if ($(this.module).attr("readonly") == "readonly") {
            $(comp_id).attr("readonly", "readonly");
          }
          if ($(this.module).attr("disabled") == "disabled") {
            $(comp_id).attr("disabled", "disabled");
          }
        }
      }, error:function(XMLHttpRequest, textStatus, errorThrown) {
      }});
    });
    $("#" + win_id).find("input.ygpaCmmnCode").each(function() {
      var default_prompt = "";
      var default_value = "";
      var input_item = this;
      var input_id = $(this).attr("id");
      if ($(this).data("cl-code") == undefined && $(this).data("cl-selector") == undefined) {
        return;
      }
      if ($(this).data("default-prompt") != null) {
        if ($(this).data("default-prompt") != null) {
          default_prompt = $(this).data("default-prompt");
        } else {
          default_prompt = "\uc120\ud0dd";
        }
        if ($(this).data("default-value") != null) {
          default_value = $(this).data("default-value");
        } else {
          default_value = "";
        }
      }
      if ($(this).data("display-value") == null) {
        try {
          $(this).attr("type", "hidden");
        } catch (err) {
          $(this).attr("id", input_id + "_backup");
          $(this).after('<input type="hidden" id="' + input_id + '" />');
          input_item = $("#" + win_id + " #" + input_id)[0];
          $(this).remove();
        }
        $(input_item).after('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
      } else {
        if ($(this).data("display-value") == "N") {
          $(this).attr("readonly", "readonly");
          $(this).before('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
        } else {
          $(this).attr("readonly", "readonly");
          $(this).after('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
        }
      }
      if (default_prompt != "") {
        if ($(input_item).data("value") == null) {
          $("#" + win_id).find("#" + input_id + "_select").append('<option value="' + default_value + '" selected>' + default_prompt + "</option>");
        } else {
          $("#" + win_id).find("#" + input_id + "_select").append('<option value="' + default_value + '">' + default_prompt + "</option>");
        }
      }
      $(this).focus(function() {
        $("#" + $(this).attr("id") + "_select")[0].focus();
      });
      var clcode = $(this).data("cl-code");
      if (clcode == undefined) {
        var clSelector = $("#" + win_id + "_" + $(this).data("cl-selector"));
        clSelector.change({module:input_item}, function(event) {
          var input_item = event.data.module;
          clcode = $(this).val();
          $.ajax({url:EMD.context_root + "/cmmn/selectCmmnCodeOptionsList.do", type:"POST", module:input_item, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"clCode", value:$(this).val()}], success:function(data) {
            if (data.resultCode != null && data.resultCode != 0) {
              if (data.resultCode == 1) {
                alert(data.resultMsg);
                location.reload();
              }
            } else {
              var selected_code = "";
              var comp_id = "#" + $(this.module).attr("id") + "_select";
              $("#" + win_id).find(comp_id).empty();
              if ($(this.module).val() != null) {
                selected_code = $(this.module).val();
                for (var i = 0;i < data.resultList.length;i++) {
                  if (selected_code == data.resultList[i].value) {
                    $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '" selected>' + data.resultList[i].name + "</option>");
                  } else {
                    $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '">' + data.resultList[i].name + "</option>");
                  }
                }
              } else {
                for (var i = 0;i < data.resultList.length;i++) {
                  $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '">' + data.resultList[i].name + "</option>");
                }
              }
              $("#" + win_id).find(comp_id).change({module:this.module}, function(event) {
                $(event.data.module).val($(this).find("option:selected").val());
                $(event.data.module).trigger("change");
              });
              if ($(this.module).attr("readonly") == "readonly") {
                $(comp_id).attr("readonly", "readonly");
              }
              if ($(this.module).attr("disabled") == "disabled") {
                $(comp_id).attr("disabled", "disabled");
              }
            }
          }, error:function(XMLHttpRequest, textStatus, errorThrown) {
          }});
        });
      } else {
        $.ajax({url:EMD.context_root + "/cmmn/selectCmmnCodeOptionsList.do", type:"POST", module:input_item, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"cl_code", value:clcode}], success:function(data) {
          if (data.resultCode != null && data.resultCode != 0) {
            if (data.resultCode == 1) {
              alert(data.resultMsg);
              location.reload();
            }
          } else {
            var selected_code = "";
            var comp_id = "#" + $(this.module).attr("id") + "_select";
            if ($(this.module).val() != null) {
              selected_code = $(this.module).val();
              for (var i = 0;i < data.resultList.length;i++) {
                if (selected_code == data.resultList[i].value) {
                  $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '" selected>' + data.resultList[i].name + "</option>");
                } else {
                  $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '">' + data.resultList[i].name + "</option>");
                }
              }
            } else {
              for (var i = 0;i < data.resultList.length;i++) {
                $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '">' + data.resultList[i].name + "</option>");
              }
            }
            $("#" + win_id).find(comp_id).change({module:this.module}, function(event) {
              $(event.data.module).val($(this).find("option:selected").val());
              $(event.data.module).trigger("change");
            });
            if ($(this.module).attr("readonly") == "readonly") {
              $(comp_id).attr("readonly", "readonly");
            }
            if ($(this.module).attr("disabled") == "disabled") {
              $(comp_id).attr("disabled", "disabled");
            }
          }
        }, error:function(XMLHttpRequest, textStatus, errorThrown) {
        }});
      }
    });
    $("#" + win_id).find("input.ygpaCmmnCd").each(function() {
      var default_prompt = "";
      var default_value = "";
      var input_item = this;
      var input_id = $(this).attr("id");
      if ($(this).data("code-id") == undefined) {
        return;
      }
      if ($(this).data("default-prompt") != null) {
        if ($(this).data("default-prompt") != null) {
          default_prompt = $(this).data("default-prompt");
        } else {
          default_prompt = "\uc120\ud0dd";
        }
        if ($(this).data("default-value") != null) {
          default_value = $(this).data("default-value");
        } else {
          default_value = "";
        }
      }
      if ($(this).data("display-value") == null) {
        try {
          $(this).attr("type", "hidden");
        } catch (err) {
          $(this).attr("id", input_id + "_backup");
          $(this).after('<input type="hidden" id="' + input_id + '" />');
          input_item = $("#" + win_id + " #" + input_id)[0];
          $(this).remove();
        }
        $(input_item).after('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
      } else {
        if ($(this).data("display-value") == "N") {
          $(this).attr("readonly", "readonly");
          $(this).before('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
        } else {
          $(this).attr("readonly", "readonly");
          $(this).after('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
        }
      }
      if (default_prompt != "") {
        if ($(input_item).data("value") == null) {
          $("#" + win_id).find("#" + input_id + "_select").append('<option value="' + default_value + '" selected>' + default_prompt + "</option>");
        } else {
          $("#" + win_id).find("#" + input_id + "_select").append('<option value="' + default_value + '">' + default_prompt + "</option>");
        }
      }
      $(this).focus(function() {
        $("#" + $(this).attr("id") + "_select")[0].focus();
      });
      $.ajax({url:EMD.context_root + "/cmmn/selectCmmnCdOptionsList.do", type:"POST", module:input_item, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"code_id", value:$(this).data("code-id")}], success:function(data) {
        if (data.resultCode != null && data.resultCode != 0) {
          if (data.resultCode == 1) {
            alert(data.resultMsg);
            location.reload();
          }
        } else {
          var selected_code = "";
          var comp_id = "#" + $(this.module).attr("id") + "_select";
          if ($(this.module).val() != null) {
            selected_code = $(this.module).val();
            for (var i = 0;i < data.resultList.length;i++) {
              if (selected_code == data.resultList[i].value) {
                $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '" selected>' + data.resultList[i].name + "</option>");
              } else {
                $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '">' + data.resultList[i].name + "</option>");
              }
            }
          } else {
            for (var i = 0;i < data.resultList.length;i++) {
              $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '">' + data.resultList[i].name + "</option>");
            }
          }
          $("#" + win_id).find(comp_id).change({module:this.module}, function(event) {
            $(event.data.module).val($(this).find("option:selected").val());
            $(event.data.module).trigger("change");
          });
          if ($(this.module).attr("readonly") == "readonly") {
            $(comp_id).attr("readonly", "readonly");
          }
          if ($(this.module).attr("disabled") == "disabled") {
            $(comp_id).attr("disabled", "disabled");
          }
        }
      }, error:function(XMLHttpRequest, textStatus, errorThrown) {
      }});
    });
    $("#" + win_id).find("input.ygpaFilterCode").each(function() {
      var default_prompt = $(this).data("default-prompt") || "";
      var default_value = $(this).data("default-value") || "";
      var input_item = this;
      var input_id = $(this).attr("id");
      var url = $(this).data("url");
      var filter = $(this).data("filter");
      if ($(this).data("display-value") == null) {
        try {
          $(this).attr("type", "hidden");
        } catch (err) {
          $(this).attr("id", input_id + "_backup");
          $(this).after('<input type="hidden" id="' + input_id + '" />');
          input_item = $("#" + win_id + " #" + input_id)[0];
          $(this).remove();
        }
        $(input_item).after('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
      } else {
        if ($(this).data("display-value") == "N") {
          $(this).attr("readonly", "readonly");
          $(this).before('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
        } else {
          $(this).attr("readonly", "readonly");
          $(this).after('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
        }
      }
      var comp_item = $("#" + win_id).find("#" + input_id + "_select");
      if (default_prompt != "") {
        if ($(input_item).data("value") == null) {
          comp_item.append('<option value="' + default_value + '" selected>' + default_prompt + "</option>");
        } else {
          comp_item.append('<option value="' + default_value + '">' + default_prompt + "</option>");
        }
      }
      $(this).focus(function() {
        $("#" + $(this).attr("id") + "_select")[0].focus();
      });
      var f = $("#" + win_id + "_" + filter + "_select");
      if (f.length == 0) {
        f = $("#" + win_id + "_" + filter + ".frmwrkAuto");
      }
      if (f.length == 0) {
        alert("\ud544\ud130\ub85c \uc120\ud0dd\ub41c \uac1d\uccb4\uac00 \uc5c6\uc2b5\ub2c8\ub2e4. \uac1c\ubc1c\uc790\uc5d0\uac8c \ubb38\uc758 \ud558\uc2dc\uae30 \ubc14\ub78d\ub2c8\ub2e4.");
        return;
      }
      comp_item.change({module:input_item}, function(event) {
        $(event.data.module).val($(this).find("option:selected").val());
        $(event.data.module).trigger("change");
      });
      comp_item.onFilterChange = function(e) {
        $("#" + $(e.data.module).attr("id") + "_select").empty();
        var nemp = $(e.data.module).data("filter-empty") || "N";
        if (nemp == "N" && $(e.target).val() === "") {
          var default_prompt = $(e.data.module).data("default-prompt") || "";
          var default_value = $(e.data.module).data("default-value") || "";
          if (default_prompt != "") {
            var comp_id = "#" + $(e.data.module).attr("id") + "_select";
            if ($(e.data.module).val() == default_value) {
              $(comp_id).append('<option value="' + default_value + '" selected="selected">' + default_prompt + "</option>");
            } else {
              $(comp_id).append('<option value="' + default_value + '">' + default_prompt + "</option>");
              $(e.data.module).val(default_value);
            }
          }
          return;
        }
        $.ajax({url:EMD.context_root + e.data.url, type:"POST", module:e.data.module, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"filter", value:$(e.target).val()}], success:function(data) {
          if (data.resultCode != null && data.resultCode != 0) {
            if (data.resultCode == 1) {
              alert(data.resultMsg);
              location.reload();
            }
          } else {
            var comp_id = "#" + $(this.module).attr("id") + "_select";
            var default_prompt = $(this.module).data("default-prompt") || "";
            var default_value = $(this.module).data("default-value") || "";
            var val = $(this.module).val();
            if (default_prompt != "") {
              if (val == default_value) {
                $(comp_id).append('<option value="' + default_value + '" selected="selected">' + default_prompt + "</option>");
              } else {
                $(comp_id).append('<option value="' + default_value + '">' + default_prompt + "</option>");
              }
            }
            for (var i = 0;i < data.resultList.length;i++) {
              if (val == data.resultList[i].value) {
                $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '" selected="selected">' + data.resultList[i].name + "</option>");
              } else {
                $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '">' + data.resultList[i].name + "</option>");
              }
            }
            if ($(this.module).attr("readonly") == "readonly") {
              $(comp_id).attr("readonly", "readonly");
            }
            if ($(this.module).attr("disabled") == "disabled") {
              $(comp_id).attr("disabled", "disabled");
            }
            $(this.module).trigger("change");
          }
        }, error:function(XMLHttpRequest, textStatus, errorThrown) {
          console.log(textStatus);
        }});
      };
      f.on("change", {module:input_item, url:url}, comp_item.onFilterChange);
      comp_item.onFilterChange({data:{module:input_item, url:url}, target:f[0]});
    });
    $("#" + win_id).find("input.selectBox").each(function() {
      var default_prompt = "";
      var default_value = "";
      var input_item = this;
      var input_id = $(this).attr("id");
      var url = $(this).data("url");
      var params = $(this).data("params");
      var parentSB = $(this).data("parent-select");
      if ($(this).data("code-id") == undefined) {
        return;
      }
      if ($(this).data("default-prompt") != null) {
        if ($(this).data("default-prompt") != null) {
          default_prompt = $(this).data("default-prompt");
        } else {
          default_prompt = "\uc120\ud0dd";
        }
        if ($(this).data("default-value") != null) {
          default_value = $(this).data("default-value");
        } else {
          default_value = "";
        }
      }
      if ($(this).data("display-value") == null) {
        try {
          $(this).attr("type", "hidden");
        } catch (err) {
          $(this).attr("id", input_id + "_backup");
          $(this).after('<input type="hidden" id="' + input_id + '" />');
          input_item = $("#" + win_id + " #" + input_id)[0];
          $(this).remove();
        }
        $(input_item).after('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
      } else {
        if ($(this).data("display-value") == "N") {
          $(this).attr("readonly", "readonly");
          $(this).before('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
        } else {
          $(this).attr("readonly", "readonly");
          $(this).after('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
        }
      }
      if (default_prompt != "") {
        if ($(input_item).data("value") == null) {
          $("#" + win_id).find("#" + input_id + "_select").append('<option value="' + default_value + '" selected>' + default_prompt + "</option>");
        } else {
          $("#" + win_id).find("#" + input_id + "_select").append('<option value="' + default_value + '">' + default_prompt + "</option>");
        }
      }
      $(this).focus(function() {
        $("#" + $(this).attr("id") + "_select")[0].focus();
      });
      var p = [];
      if (params != null) {
        $.each(params.split(","), function() {
          var d = this.split("=");
          p[p.length] = {name:parentSB, value:d[1]};
        });
      }
      input_item._setValue = function(data) {
        if (data.resultCode != null && data.resultCode != 0) {
          if (data.resultCode == 1) {
            alert(data.resultMsg);
            location.reload();
          }
        } else {
          var selected_code = "";
          var comp_id = "#" + $(this).attr("id") + "_select";
          if ($(this).val() != null) {
            selected_code = $(this).val();
            for (var i = 0;i < data.resultList.length;i++) {
              if (selected_code == data.resultList[i].value) {
                $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '" selected>' + data.resultList[i].name + "</option>");
              } else {
                $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '">' + data.resultList[i].name + "</option>");
              }
            }
          } else {
            for (var i = 0;i < data.resultList.length;i++) {
              $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '">' + data.resultList[i].name + "</option>");
            }
          }
          $("#" + win_id).find(comp_id).change({module:this}, function(event) {
            $(event.data.module).val($(this).find("option:selected").val());
            $(event.data.module).trigger("change");
          });
          if ($(this).attr("readonly") == "readonly") {
            $(comp_id).attr("readonly", "readonly");
          }
          if ($(this).attr("disabled") == "disabled") {
            $(comp_id).attr("disabled", "disabled");
          }
        }
      };
      this._params = p.slice();
      if (parentSB != null) {
        var val = module_instance.$("#" + parentSB).val();
        module_instance.$("#" + parentSB).data("id", parentSB);
        p[p.length] = {name:parentSB, value:val};
        module_instance.$("#" + parentSB).on("change", {module:this}, function(e) {
          var module = e.data.module;
          var p = module._params.slice();
          p[p.length] = {name:$(this).data("id"), value:$(this).val()};
          $.ajax({url:EMD.context_root + $(module).data("url"), type:"POST", module:module, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:p, success:function(data) {
            this.module._setValue(data);
          }, error:function(XMLHttpRequest, textStatus, errorThrown) {
          }});
        });
      } else {
        $.ajax({url:EMD.context_root + url, type:"POST", module:input_item, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:p, success:function(data) {
          this.module._setValue(data);
        }, error:function(XMLHttpRequest, textStatus, errorThrown) {
        }});
      }
    });
    $("#" + win_id).find("input.ygpaYnSelect").each(function() {
      var default_prompt = "";
      var default_value = "";
      var y_value = "Y";
      var n_value = "N";
      var y_prompt = "\uc608";
      var n_prompt = "\uc544\ub2c8\uc624";
      var selector;
      var input_item = this;
      var input_id = $(this).attr("id");
      if ($(this).data("default-prompt") != null) {
        if ($(this).data("default-prompt") != null) {
          default_prompt = $(this).data("default-prompt");
        } else {
          default_prompt = "\uc120\ud0dd";
        }
        if ($(this).data("default-value") != null) {
          default_value = $(this).data("default-value");
        } else {
          default_value = "";
        }
      }
      if ($(this).data("display-value") == null) {
        try {
          $(this).attr("type", "hidden");
        } catch (err) {
          $(this).attr("id", input_id + "_backup");
          $(this).after('<input type="hidden" id="' + input_id + '" />');
          input_item = $("#" + win_id + " #" + input_id)[0];
          $(this).remove();
        }
        $(input_item).after('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
      } else {
        if ($(this).data("display-value") == "N") {
          $(this).attr("readonly", "readonly");
          $(this).before('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
        } else {
          $(this).attr("readonly", "readonly");
          $(this).after('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
        }
      }
      selector = $("#" + win_id).find("#" + input_id + "_select");
      selected_code = $(this).data("value");
      if (default_prompt != "") {
        if (selected_code == null) {
          selector.append('<option value="' + default_value + '" selected>' + default_prompt + "</option>");
        } else {
          selector.append('<option value="' + default_value + '">' + default_prompt + "</option>");
        }
      }
      if ($(this).data("y-value") != null) {
        y_value = $(this).data("y-value");
      }
      if ($(this).data("n-value") != null) {
        y_value = $(this).data("n-value");
      }
      if ($(this).data("y-prompt") != null) {
        y_prompt = $(this).data("y-prompt");
      }
      if ($(this).data("n-prompt") != null) {
        n_prompt = $(this).data("n-prompt");
      }
      if (default_prompt == "" || selected_code != null) {
        if (selected_code == y_value) {
          selector.append('<option value="' + y_value + '" selected>' + y_prompt + "</option>");
        } else {
          selector.append('<option value="' + y_value + '">' + y_prompt + "</option>");
        }
        if (selected_code != y_value) {
          selector.append('<option value="' + n_value + '" selected>' + n_prompt + "</option>");
        } else {
          selector.append('<option value="' + n_value + '">' + n_prompt + "</option>");
        }
        $(this).val(selected_code);
      } else {
        selector.append('<option value="' + y_value + '">' + y_prompt + "</option>");
        selector.append('<option value="' + n_value + '">' + n_prompt + "</option>");
      }
      $(this).focus(function() {
        $("#" + $(this).attr("id") + "_select")[0].focus();
      });
      selector.change({module:input_item}, function(event) {
        $(event.data.module).val($(this).find("option:selected").val());
        $(event.data.module).trigger("change");
      });
    });
    $("#" + win_id).find("input.ygpaNumber, input.ygpaCurrency").each(function() {
      $(this).number(true, $(this).data("decimal-point") || 0);
    });
    $("#" + win_id).find(".emdTime").each(function() {
      $(this).keyup(function() {
        var value = $(this).val();
        if (value.indexOf(":") >= 0) {
          if (value.length != 5) {
            $(this).val($(this).val().replace(/-/g, ""));
          }
        } else {
          if (value.length == 4) {
            value = value.substr(0, 2) + ":" + value.substr(2, 2);
          }
        }
      });
    });
    $("#" + win_id).find(".emdcal").each(function() {
      var dateFormat = $(this).data("date-format") || "yy-mm-dd";
      var disabled = $(this).attr("disabled") != undefined;
      $(this).datepicker({dateFormat:dateFormat, prevText:"\uc774\uc804 \ub2ec", nextText:"\ub2e4\uc74c \ub2ec", monthNames:["1\uc6d4", "2\uc6d4", "3\uc6d4", "4\uc6d4", "5\uc6d4", "6\uc6d4", "7\uc6d4", "8\uc6d4", "9\uc6d4", "10\uc6d4", "11\uc6d4", "12\uc6d4"], monthNamesShort:["1\uc6d4", "2\uc6d4", "3\uc6d4", "4\uc6d4", "5\uc6d4", "6\uc6d4", "7\uc6d4", "8\uc6d4", "9\uc6d4", "10\uc6d4", "11\uc6d4", "12\uc6d4"], dayNames:["\uc77c", "\uc6d4", "\ud654", "\uc218", "\ubaa9", "\uae08", "\ud1a0"], dayNamesShort:["\uc77c", 
      "\uc6d4", "\ud654", "\uc218", "\ubaa9", "\uae08", "\ud1a0"], dayNamesMin:["\uc77c", "\uc6d4", "\ud654", "\uc218", "\ubaa9", "\uae08", "\ud1a0"], changeMonth:true, changeYear:true, disabled:disabled});
      if (!disabled) {
        $(this).datepicker("option", {showOn:"button", buttonImage:EMD.context_root + "/images/egovframework/rte/icon_date_picker.jpg", buttonImageOnly:true});
      }
      if ($(this).data("role") == "dtFrom") {
        $("#" + win_id + "_" + $(this).data("dt-to")).on("change", {dtFrom:this}, function(event) {
          $(event.data.dtFrom).datepicker("option", "maxDate", $(this).datepicker("getDate"));
        });
        $(this).datepicker("option", "maxDate", $("#" + win_id + "_" + $(this).data("dt-to")).datepicker("getDate"));
      }
      if ($(this).data("role") == "dtTo") {
        $("#" + win_id + "_" + $(this).data("dt-from")).on("change", {dtTo:this}, function(event) {
          $(event.data.dtTo).datepicker("option", "minDate", $(this).datepicker("getDate"));
        });
        $(this).datepicker("option", "minDate", $("#" + win_id + "_" + $(this).data("dt-from")).datepicker("getDate"));
      }
      $(this).keyup(function() {
        var value = $(this).val();
        if (value.indexOf("-") >= 0) {
          if (value.length != 10) {
            $(this).val($(this).val().replace(/-/g, ""));
          } else {
            if (!EMD.util.isDate(value)) {
              alert("\ub0a0\uc9dc \ud615\uc2dd\uc774 \ubd80\uc801\ud569 \ud569\ub2c8\ub2e4.");
            }
          }
        } else {
          if (value.length == 8) {
            value = value.substr(0, 4) + "-" + value.substr(4, 2) + "-" + value.substr(6, 2);
            if (!EMD.util.isDate(value)) {
              alert("\ub0a0\uc9dc \ud615\uc2dd\uc774 \ubd80\uc801\ud569 \ud569\ub2c8\ub2e4.");
            } else {
              $(this).val(value);
            }
          }
        }
      });
    });
  }, _clickButtonExec:function(module, button, button_id) {
    var role = button.data("role");
    switch(role) {
      case "gridXlsDown":
        var grid = button.data("flexi-grid");
        var filename = button.data("xls-name") || "gis.xls";
        var title = button.data("xls-title") || "GIS\uae30\ubc18 \uc790\uc0b0\uad00\ub9ac \uc2dc\uc2a4\ud15c";
        if (grid != undefined) {
          module.$("#" + grid).flexiXlsDown(filename, title);
        } else {
          module.onButtonClick(button_id);
        }
        break;
      case "printPage":
        var src = button.data("flexi-grid");
        var url = button.data("url");
        if (src == null) {
          EMD.util.printPageValue(module, url, button.data("search-option"));
        } else {
          EMD.util.printPageGrid(module, url, button.data("flexi-grid"), button.data("search-option"));
        }
        break;
      case "printDown":
        var url = button.data("url");
        var param;
        param = module.makeFormArgs("#" + button.data("search-option"), "object");
        param["filename"] = button.data("filename");
        $.fileDownload(EMD.context_root + url, {data:param, httpMethod:"POST"});
        break;
      case "openWindow":
        var argument = button.data("argument");
        var params = button.data("params");
        var p = [];
        if (argument != null) {
          if (typeof module[argument] == "object") {
            p = EMD.util.objectToArray(module[argument]);
          }
        }
        if (params != null) {
          params = module[params];
        }
        EMD.util.create_window(button.data("progrm-file-nm"), button.data("title"), button.data("url"), p, params, module);
        break;
      case "showHelp":
        EMD.util.showHelp(wikiUrl + "page=" + button.data("page"));
        break;
      case "popupDialog":
        var p = {};
        var dp = {};
        var params = button.data("params");
        var params_func = button.data("params-func");
        var direct_params = button.data("direct-params");
        var direct_params_func = button.data("direct-params-func");
        if (params != undefined) {
          if (params.indexOf(",") >= 0) {
            $.each(params.split(","), function() {
              var d = this.split("=");
              p[d[0]] = d[1];
            });
          } else {
            p = module[params];
          }
        } else {
          if (params_func != undefined) {
            p = module[params]();
          }
        }
        if (direct_params != undefined) {
          if (direct_params.indexOf(",") >= 0) {
            $.each(direct_params.split(","), function() {
              var d = this.split("=");
              dp[d[0]] = d[1];
            });
          } else {
            dp = module[direct_params];
          }
        } else {
          if (direct_params_func != undefined) {
            dp = module[direct_params]();
          }
        }
        module.doExecuteDialog(button_id, button.data("title"), EMD.context_root + button.data("url"), p, dp);
        break;
      case "loadMap":
        var src = button.data("flexi-grid");
        EMD.util.window_hide_all();
        if (src == null) {
          EMD.gis.loadMapDataCode(module, button.data("gis-layer"), button.data("gis-style"), button.data("value-name"), button.data("value"));
        } else {
          EMD.gis.loadMapDataFlexGrid(module, button.data("gis-layer"), button.data("gis-style"), button.data("flexi-grid"), button.data("code-id"), button.data("value"));
        }
        break;
      case "showMap":
        var src = button.data("flexi-grid");
        EMD.util.window_hide_all();
        if (src == null) {
          EMD.gis.showMapDataCode(module, button.data("gis-layer"), button.data("value-name"));
        } else {
          EMD.gis.showMapDataFlexGrid(module, button.data("gis-layer"), button.data("flexi-grid"), button.data("code-id"), button.data("value"));
        }
        break;
      case "clearMap":
        EMD.gis.clearMap(module, button.data("gis-layer"));
        break;
      case "loadStatsMap":
        var src = button.data("flexi-grid");
        if (src != null) {
          EMD.util.window_hide_all();
          EMD.gis.showStatusMapView(module, button.data("gis-layer"), src, button.data("map-style"), button.data("value"), button.data("label-field"), button.data("select-feature"));
        }
        break;
      case "editMap":
        var layer = button.data("gis-layer");
        EMD.gis.editLayer(module, layer);
        EMD.util.window_hide_all();
        break;
      case "removeFeature":
        var src = button.data("flexi-grid");
        if (src == null) {
          EMD.gis.removeMapDataCode(module, button.data("gis-layer"), button.data("value-name"), button.data("value"));
        } else {
          EMD.gis.removeMapDataFlexGrid(module, button.data("gis-layer"), button.data("flexi-grid"), button.data("code-id"), button.data("value"));
        }
        break;
      case "addFeature":
        var src = button.data("flexi-grid");
        if (src == null) {
          EMD.gis.addFeatureCode(module, button.data("gis-layer"), button.data("value-name"), button.data("value"));
        } else {
          EMD.gis.addFeatureFlexGrid(module, button.data("gis-layer"), button.data("flexi-grid"), button.data("code-id"), button.data("value"));
        }
        if (EMD.popup.lotAreaInfo != null) {
          EMD.map.removePopup(EMD.popup.lotAreaInfo);
          EMD.popup.lotAreaInfo.destroy();
        }
        break;
      default:
        if (button.hasClass("buttonExcel")) {
          var grid = button.data("flexi-grid");
          var url = button.data("url");
          if (grid != undefined && url != undefined) {
            module.$("#" + grid).flexExcelDown(url);
          } else {
            module.onButtonClick(button_id);
          }
        } else {
          module.onButtonClick(button_id);
        }
        break;
    }
  }, hsv2rgb:function(h, s, v) {
    var rgb, i, data = [];
    if (s === 0) {
      rgb = [v, v, v];
    } else {
      h = h / 60;
      i = Math.floor(h);
      data = [v * (1 - s), v * (1 - s * (h - i)), v * (1 - s * (1 - (h - i)))];
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
    return "#" + rgb.map(function(x) {
      return("0" + Math.round(x * 255).toString(16)).slice(-2);
    }).join("");
  }}};
}(jQuery, this, this.document);
function EmdModuleBase() {
  this._moduleType = "_EMD_MODULE_BASE";
}
EmdModuleBase.prototype.getModuleId = function() {
  return this._module_id;
};
EmdModuleBase.prototype.setModuleId = function(id) {
  this._module_id = id;
};
EmdModuleBase.prototype.$ = function(selector) {
  return $("#" + this._module_id).find(selector.replace(/#/g, "#" + this._module_id + "_"));
};
EmdModuleBase.prototype.makeFormArgs = function(selector, type) {
  var obj_type = type || "array";
  var args = null;
  var module_id = this._module_id;
  if (obj_type == "array") {
    args = [];
  } else {
    args = {};
  }
  this.$(selector).find(":input").not(".frmwrkAuto").not(".skipValue").each(function() {
    var value = "";
    if ($(this).hasClass("ygpaNumber") || $(this).hasClass("ygpaCurrency")) {
      value = $(this).val();
    } else {
      value = $(this).val();
    }
    if ($(this).data("column-id") != null) {
      if (obj_type == "array") {
        args[args.length] = {name:$(this).data("column-id"), value:value};
      } else {
        args[$(this).data("column-id")] = value;
      }
    } else {
      var input_id = $(this).attr("id");
      if (typeof input_id == "string") {
        input_id = input_id.replace(module_id + "_", "");
        if (obj_type == "array") {
          args[args.length] = {name:input_id, value:value};
        } else {
          args[input_id] = value;
        }
      }
    }
  });
  return args;
};
EmdModuleBase.prototype.makeFormValues = function(selector, obj) {
  var module_id = this._module_id;
  if (obj instanceof Array) {
    var newobj = {};
    for (var i = 0;i < obj.length;i++) {
      newobj[obj[i].name] = obj[i].value;
    }
    obj = newobj;
  }
  if (typeof obj != "object") {
    return;
  }
  this.$(selector).find(":input").not(".frmwrkAuto").not(".skipValue").each(function() {
    var column_id = "";
    if ($(this).data("column-id") != null) {
      column_id = $(this).data("column-id");
    } else {
      column_id = $(this).attr("id");
      if (typeof column_id == "string") {
        column_id = column_id.replace(module_id + "_", "");
      } else {
        return;
      }
    }
    if (obj == null) {
      console.log("error object is null in makeformvalues");
      return false;
    }
    if (obj[column_id] != null) {
      if ($(this).hasClass("ygpaNumber") || $(this).hasClass("ygpaCurrency")) {
        $(this).val(obj[column_id]);
      } else {
        if ($(this).hasClass("ygpaDeptSelect") || ($(this).hasClass("ygpaCmmnCl") || ($(this).hasClass("ygpaCmmnCode") || ($(this).hasClass("ygpaCmmnCd") || ($(this).hasClass("ygpaFilterCode") || $(this).hasClass("ygpaYnSelect")))))) {
          $(this).val(obj[column_id]);
          $("#" + $(this).attr("id") + "_select").val(obj[column_id]);
        } else {
          $(this).val(obj[column_id]);
        }
      }
    } else {
      $(this).val("");
    }
  });
};
EmdModuleBase.prototype.makeDivValues = function(selector, obj) {
  var selobj = null;
  var win_id = this._module_id;
  if (obj instanceof Array) {
    var newobj = {};
    for (var i = 0;i < obj.length;i++) {
      newobj[obj[i].name] = obj[i].value;
    }
    obj = newobj;
  }
  if (typeof obj != "object") {
    return;
  }
  if (typeof selector == "string") {
    selobj = this.$(selector);
  } else {
    if (typeof selector == "object") {
      selobj = selector;
    } else {
      selobj = $(selector);
    }
  }
  selobj.find("span").each(function() {
    var column_id = "";
    if ($(this).data("column-id") != null) {
      column_id = $(this).data("column-id");
    } else {
      column_id = $(this).attr("id");
      if (typeof column_id == "string") {
        column_id = column_id.replace(win_id + "_", "");
      } else {
        return;
      }
    }
    if (obj[column_id] != null) {
      if ($(this).hasClass("ygpaNumber") || $(this).hasClass("ygpaCurrency")) {
        $(this).text($.number(obj[column_id], $(this).data("decimal-point") || 0));
      } else {
        if ($(this).hasClass("ygpaDeptSelect") || ($(this).hasClass("ygpaCmmnCl") || ($(this).hasClass("ygpaCmmnCode") || ($(this).hasClass("ygpaCmmnCd") || ($(this).hasClass("ygpaFilterCode") || ($(this).hasClass("ygpaYnSelect") || $(this).hasClass("ygpaDecode"))))))) {
          var input_item = $(this);
          if ($(this).hasClass("ygpaDeptSelect")) {
            $.ajax({url:EMD.context_root + "/cmmn/selectDeptCodeName.do", type:"POST", module:input_item, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"value", value:obj[column_id]}], success:function(data) {
              this.module.text(data.name);
            }, error:function(XMLHttpRequest, textStatus, errorThrown) {
              try {
                this.module.text("\uc11c\ubc84\uc624\ub958");
              } catch (e) {
              }
            }});
          } else {
            if ($(this).hasClass("ygpaCmmnCl")) {
              $.ajax({url:EMD.context_root + "/cmmn/selectCmmnClName.do", type:"POST", module:input_item, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{value:code_id}, {name:"value", value:obj[column_id]}], success:function(data) {
                this.module.text(data.value);
              }, error:function(XMLHttpRequest, textStatus, errorThrown) {
                try {
                  this.module.text("\uc11c\ubc84\uc624\ub958");
                } catch (e) {
                }
              }});
            } else {
              if ($(this).hasClass("ygpaCmmnCode")) {
                var cl_code = input_item.data("cl-code");
                $.ajax({url:EMD.context_root + "/cmmn/selectCmmnCodeName.do", type:"POST", module:input_item, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"clCode", value:cl_code}, {name:"codeId", value:obj[column_id]}], success:function(data) {
                  this.module.text(data.value);
                }, error:function(XMLHttpRequest, textStatus, errorThrown) {
                  try {
                    this.module.text("\uc11c\ubc84\uc624\ub958");
                  } catch (e) {
                  }
                }});
              } else {
                if ($(this).hasClass("ygpaCmmnCd")) {
                  var code_id = input_item.data("code-id");
                  $.ajax({url:EMD.context_root + "/cmmn/selectCmmnDetailCodeName.do", type:"POST", module:input_item, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"codeId", value:code_id}, {name:"code", value:obj[column_id]}], success:function(data) {
                    this.module.text(data.value);
                  }, error:function(XMLHttpRequest, textStatus, errorThrown) {
                    try {
                      this.module.text("\uc11c\ubc84\uc624\ub958");
                    } catch (e) {
                    }
                  }});
                } else {
                  if ($(this).hasClass("ygpaFilterCode")) {
                    var code_id = input_item.data("filter");
                    var comp_id = $(this).attr("id");
                    var filter = $("#" + comp_id.substring(comp_id.indexOf("_") + 1) + code_id).val();
                    $.ajax({url:EMD.context_root + input_item.data("url"), type:"POST", module:input_item, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"filter", value:filter}, {name:"value", value:input_item.val()}], success:function(data) {
                      this.module.text(data.resultList[0].name);
                    }, error:function(XMLHttpRequest, textStatus, errorThrown) {
                      try {
                        this.module.text("\uc11c\ubc84\uc624\ub958");
                      } catch (e) {
                      }
                    }});
                  } else {
                    if ($(this).hasClass("ygpaYnSelect")) {
                      var y_value = "Y";
                      var n_value = "N";
                      var y_prompt = "\uc608";
                      var n_prompt = "\uc544\ub2c8\uc624";
                      if ($(this).data("y-value") != null) {
                        y_value = $(this).data("y-value");
                      }
                      if ($(this).data("n-value") != null) {
                        y_value = $(this).data("n-value");
                      }
                      if (obj[column_id] == y_value) {
                        if ($(this).data("y-prompt") != null) {
                          y_prompt = $(this).data("y-prompt");
                        }
                        $(this).text(y_prompt);
                      } else {
                        if (obj[column_id] == n_value) {
                          if ($(this).data("n-prompt") != null) {
                            n_prompt = $(this).data("n-prompt");
                          }
                          $(this).text(n_prompt);
                        } else {
                          $(this).text(obj[column_id]);
                        }
                      }
                    } else {
                      if ($(this).hasClass("ygpaDecode")) {
                        if (typeof obj[column_id] == "string" && typeof $(this).data("decode-string") == "string") {
                          var idx = $(this).data("decode-string").match(",");
                          var input_item = $(this);
                          for (var i = 0;i < idx.length;i++) {
                            if (idx[i] == obj[column_id]) {
                              $(this).text($(this).data("decode-" + i));
                              return;
                            }
                          }
                          $(this).text($(this).data("decode-default"));
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        } else {
          $(this).text(obj[column_id]);
        }
      }
    } else {
      $(this).text("");
    }
  });
};
EmdModuleBase.prototype.makeMultiDivValues = function(selector, list, func) {
  var selobj = this.$(selector);
  if (selobj.children().length < list.length) {
    for (var i = selobj.children().length;i < list.length;i++) {
      var tab = selobj.children(":first-child").clone();
      selobj.append(tab);
    }
  } else {
    if (selobj.children().length > list.length) {
      while (selobj.children().length > 1 && selobj.children().length > list.length) {
        selobj.children(":last-child").remove();
      }
    }
  }
  for (var i = 0;i < list.length;i++) {
    var div = selobj.children(":eq(" + i + ")");
    this.makeDivValues(div, list[i]);
    div[0].func = func;
    div[0].func(list[i]);
    delete div[0]["func"];
  }
  if (list.length == 0) {
    this.makeDivValues(selobj.children(), {});
  }
};
EmdModuleBase.prototype.getFormValues = function(selector, obj) {
  var win_id = this.getModuleId();
  var row = $.extend({}, obj);
  this.$(selector).find(":input").not(".frmwrkAuto").each(function() {
    var value = "";
    if ($(this).hasClass("ygpaNumber") || $(this).hasClass("ygpaCurrency")) {
      value = $(this).val();
    } else {
      value = $(this).val();
    }
    if ($(this).data("column-id") != null) {
      row[$(this).data("column-id")] = value;
    } else {
      var input_id = $(this).attr("id");
      if (typeof input_id == "string") {
        input_id = input_id.replace(win_id + "_", "");
        row[input_id] = value;
      }
    }
    if ($(this).data("column-label-id") != null) {
      row[$(this).data("column-label-id")] = $("#" + $(this).attr("id") + "_select").find(":selected").text();
    }
  });
  return row;
};
EmdModuleBase.prototype.doAction = function(addr, args, retfunc) {
  $.ajax({url:EMD.context_root + addr, type:"POST", module:this, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:args}).done(function(data) {
    if (retfunc != null) {
      retfunc(this.module, data);
    }
  });
};
EmdModuleBase.prototype.uploadSingleFile = function(url, func) {
  $("#__tempDiv").empty();
  $("#__uploadFrame").remove();
  var $iFrame = $('<iFrame id="__uploadFrame" name="__uploadFrame"></iFrame>');
  $iFrame.appendTo("body");
  $("#__tempDiv").append('<form id="__uploadForm" name="__uploadForm" method="post" enctype="multipart/form-data">' + '<input name="type" type="hidden" value="genericFileMulti" /><input id="__uploadFile" name="__uploadFile" type="file" />' + "</form>");
  $("#__uploadForm").submit(function() {
    $("#__uploadForm").attr("target", "__uploadFrame");
  });
  $("#__uploadFile").on("change", {module:this, func:func, url:url}, function(e) {
    $("#__uploadForm").attr("action", EMD.context_root + e.data.url).submit();
    $("#__uploadFrame").load(function() {
      if (status == "error") {
        var msg = "\ud30c\uc77c\uc744 \uc5c5\ub85c\ub4dc \ud558\ub294\ub370 \uc624\ub958\uac00 \ubc1c\uc0dd \ud588\uc2b5\ub2c8\ub2e4. : ";
        alert(msg + xhr.status + " " + xhr.statusText);
      }
      $("#__tempDiv").empty();
      var data = $("#__uploadFrame").contents().text();
      if (e.data.func != undefined) {
        e.data.func(e.data.module, jQuery.parseJSON(data));
      }
      $("#__uploadFrame").remove();
    });
  });
  $("#__uploadFile").click();
};
EmdModuleBase.prototype.uploadMultiFile = function(url, func) {
  $("#__tempDiv").empty();
  $("#__uploadFrame").remove();
  var $iFrame = $('<iFrame id="__uploadFrame" name="__uploadFrame"></iFrame>');
  $iFrame.appendTo("body");
  $("#__tempDiv").append('<form id="__uploadForm" name="__uploadForm" method="post" enctype="multipart/form-data">' + '<input name="type" type="hidden" value="genericFileMulti" /><input id="__uploadFile" name="__uploadFile" type="file" multiple />' + "</form>");
  $("#__uploadForm").submit(function() {
    $("#__uploadForm").attr("target", "__uploadFrame");
  });
  $("#__uploadFile").on("change", {module:this, func:func, url:url}, function(e) {
    $("#__uploadForm").attr("action", EMD.context_root + e.data.url).submit();
    $("#__uploadFrame").load(function() {
      if (status == "error") {
        var msg = "\ud30c\uc77c\uc744 \uc5c5\ub85c\ub4dc \ud558\ub294\ub370 \uc624\ub958\uac00 \ubc1c\uc0dd \ud588\uc2b5\ub2c8\ub2e4. : ";
        alert(msg + xhr.status + " " + xhr.statusText);
      }
      $("#__tempDiv").empty();
      var data = $("#__uploadFrame").contents().text();
      if (e.data.func != undefined) {
        e.data.func(e.data.module, jQuery.parseJSON(data));
      }
      $("#__uploadFrame").remove();
    });
  });
  $("#__uploadFile").click();
};
EmdModuleBase.prototype.downloadSingleFile = function(url, filePhysiclNm, fileLogicalNm) {
  var param = {"physicalFileNm":filePhysiclNm, "logicalFileNm":fileLogicalNm};
  $.fileDownload(EMD.context_root + url, {data:param, httpMethod:"POST"});
};
EmdModuleBase.prototype.getUrl = function(url) {
  return EMD.context_root + url;
};
EmdModuleBase.prototype.removeFeatures = function(layerName, f) {
  EMD.gis.removeFeatures(this, layerName, f);
};
EmdModuleBase.prototype.openPopupWindow = function(url, params) {
  $("#__tempDiv").empty();
  var form = document.createElement("form");
  $(form).attr("id", "__temp");
  for (var pn in params) {
    var e = document.createElement("input");
    $(e).attr("name", pn);
    $(e).val(params[pn]);
    $(form).append(e);
  }
  $(form).attr("action", EMD.context_root + url);
  $("#__tempDiv").append(form);
  if (EmdModuleBase._lastPopupId == undefined) {
    EmdModuleBase["_lastPopupId"] = 1;
  }
  var win = window.open("", "__tempPopup" + EmdModuleBase._lastPopupId, "left=0, top=0, width=" + screen.width + ", height=" + screen.height + ", menubar=no,status=no,scrollbars=yes");
  $(win).on("");
  var module = this;
  form.target = "__tempPopup" + EmdModuleBase._lastPopupId;
  EmdModuleBase._lastPopupId++;
  form.submit();
};
function EmdModule(width, height) {
  this._parent = null;
  this._selectedRow = null;
  if (width == undefined || (width == null || (height == undefined || height == null))) {
    this._width = 720;
    this._height = 480;
  } else {
    this._width = width;
    this._height = height;
  }
  this._moduleType = "_EMD_MODULE";
}
EmdModule.prototype = new EmdModuleBase;
EmdModule.prototype.getModuleType = function() {
  return this._moduleType;
};
EmdModule.prototype.setParent = function(parent) {
  this._parent = parent;
};
EmdModule.prototype.showMap = function(gridId, layerName) {
  if (gridId != undefined) {
    this._selectedRow = this.$("#" + gridId).selectedRows();
    $(this._selectedRow).each(function() {
      EMD.gis.selectAssetCdFeature(this.gisAssetsPrtAtCode, this.gisAssetsCd, this.gisAssetsSubCd);
    });
  }
  EMD.transactionWindow = this.getModuleId();
  EMD.util.window_hide_all();
};
EmdModule.prototype.modifyFeatureCode = function(layerName, value, newfeature) {
  EMD.gis.modifyFeatureCode(this, layerName, value, newfeature);
};
EmdModule.prototype.setFeatureCode = function(layerName, value, feature) {
  EMD.gis.setFeatureCode(this, layerName, value, feature);
};
EmdModule.prototype.removeFeatureCode = function(layerName, value) {
  EMD.gis.removeFeatureCode(this, layerName, value);
};
EmdModule.prototype.changeFeatureAttrib = function(layerName, oldAttr, newAttr) {
  EMD.gis.changeFeatureAttribute(this, layerName, oldAttr, newAttr);
};
EmdModule.prototype.selectFeatureData = function(layerName, value, zoomToExtent) {
  EMD.gis.selectFeatureData(this, layerName, value, zoomToExtent);
};
EmdModule.prototype.getSelect = function(selector) {
  alert("get select = " + this.getModuleId() + " " + selector);
};
EmdModule.prototype.showAlert = function(msg) {
  alert("alert id = " + this.getModuleId() + " " + msg);
};
EmdModule.prototype.getWidth = function() {
  return this._width;
};
EmdModule.prototype.setWidth = function(value) {
  this._width = value;
};
EmdModule.prototype.getHeight = function() {
  return this._height;
};
EmdModule.prototype.setHeight = function(value) {
  this._height = value;
};
EmdModule.prototype.setSelectedRow = function(row) {
  this._selectedRow = row;
};
EmdModule.prototype.getSelectedRow = function() {
  return this._selectedRow;
};
EmdModule.prototype.getId = function(id) {
  return this.getModuleId() + "_" + id;
};
EmdModule.prototype.doExecuteDialog = function(dlgId, dlgTitle, dlgAddr, dlgOpts, dlgParams) {
  if ($("#__dialog-modal").length == 0) {
    var win = document.createElement("div");
    win.style.display = "none";
    win.innerHTML = '<div id="__dialog-modal"></div>';
    $("#desktop")[0].appendChild(win);
  }
  EMD.popupcaller = this;
  EMD.popupId = dlgId;
  EMD.popupParams = dlgParams;
  if (dlgAddr != undefined) {
    $("#__dialog-modal").load(EMD.context_root + dlgAddr, dlgOpts, function(response, status, xhr) {
      if (status == "error") {
        var msg = "\uc5d0\ub7ec\uac00 \ubc1c\uc0dd\ud588\uc2b5\ub2c8\ub2e4. \uc5d0\ub7ec\ubc88\ud638: ";
        alert(msg + xhr.status + " " + xhr.statusText);
      } else {
        var w, h;
        w = popup_instance.getWidth();
        h = popup_instance.getHeight();
        $("#__dialog-modal").dialog({width:w, height:h, modal:true, closeOnEscape:true, title:dlgTitle});
        $("#__dialog-modal")[0].module = popup_instance;
        popup_instance.setModuleId("__dialog-modal");
        popup_instance.setDialog($("#__dialog-modal"));
        popup_instance.setPopupCaller(EMD.popupcaller);
        popup_instance.setPopupId(EMD.popupId);
        EMD.util.modify_window(popup_instance, "__dialog-modal");
        var win_id = "__dialog-modal";
        $("#" + win_id).on("resizeWindow", function(event) {
          event.stopPropagation();
          EMD.util.window_resized($(this));
        });
        $("#" + win_id).trigger("resizeWindow");
        $("#" + win_id).resizable({create:function(event, ui) {
        }, resize:function(event, ui) {
          event.stopPropagation();
        }, stop:function(event, ui) {
          EMD.util.window_resized(ui.element);
        }});
        popup_instance.loadComplete(EMD.popupParams);
      }
    });
  }
};
EmdModule.prototype.uploadFile = function(upload_id, uploadcomplete, dlgTitle) {
  if (dlgTitle == null) {
    dlgTitle = "\uc5c5\ub85c\ub4dc \ud30c\uc77c";
  }
  $("tbody.files").empty();
  $("#file_upload_dialog").dialog("option", {title:dlgTitle, closeOnEscape:true});
  $("#file_upload_dialog").dialog({buttons:[{text:"\ud655\uc778", click:function() {
    var rows = [];
    $("#fileupload tbody.files tr").each(function() {
      rows[rows.length] = {physcalFileNm:$(this).data("physcal-file-nm"), logicalFileNm:$(this).data("logical-file-nm")};
    });
    $(this).trigger("fileUploaded", {rows:rows});
    $(this).dialog("close");
  }}, {text:"\ucde8\uc18c", click:function() {
    $(this).dialog("close");
  }}]});
  $("#file_upload_dialog").dialog("open");
  $("#fileupload").addClass("fileupload-processing");
  $("#fileupload").data("module", this);
  $("#file_upload_dialog").off("fileUploaded");
  $("#file_upload_dialog").on("fileUploaded", {done:uploadcomplete, module:this}, function(event, data) {
    event.data.done(event.data.module, data.rows);
  });
};
EmdModule.prototype.uploadPfPhoto = function(upload_id, uploadcomplete, dlgTitle) {
  if (dlgTitle == null) {
    dlgTitle = "\uc2dc\uc124 \uc0ac\uc9c4 \uc5c5\ub85c\ub4dc";
  }
  $("tbody.files").empty();
  $("#pfPhoto_upload_dialog").dialog("option", {title:dlgTitle, closeOnEscape:true});
  $("#pfPhoto_upload_dialog").dialog({buttons:[{text:"\ud655\uc778", click:function() {
    var rows = [];
    $("#pfPhotoupload tbody.files tr").each(function() {
      rows[rows.length] = {physcalFileNm:$(this).data("physcal-file-nm"), logicalFileNm:$(this).data("logical-file-nm")};
    });
    $(this).trigger("fileUploaded", {rows:rows});
    $(this).dialog("close");
  }}, {text:"\ucde8\uc18c", click:function() {
    $(this).dialog("close");
  }}]});
  $("#pfPhoto_upload_dialog").dialog("open");
  $("#pfPhotoupload").addClass("fileupload-processing");
  $("#pfPhotoupload").data("module", this);
  $("#pfPhoto_upload_dialog").off("fileUploaded");
  $("#pfPhoto_upload_dialog").on("fileUploaded", {done:uploadcomplete, module:this}, function(event, data) {
    event.data.done(event.data.module, data.rows);
  });
};
EmdModule.prototype.downPfPhoto = function(filePhysiclNm, logicalName) {
  var url = "/download/pfDownloadFile.do";
  var param = {"requestedFile":filePhysiclNm, "downloadFileName":logicalName};
  $.fileDownload(EMD.context_root + url, {data:param, httpMethod:"POST"});
};
EmdModule.prototype.uploadXlsFile = function(upload_id, uploadcomplete, dlgTitle, url) {
  if (dlgTitle == null) {
    dlgTitle = "\uc5c5\ub85c\ub4dc \ud30c\uc77c";
  }
  $("#xlsfile_upload_dialog").dialog("option", {title:dlgTitle, closeOnEscape:true});
  $("#xlsfile_upload_dialog").dialog({buttons:[{text:"\ud655\uc778", click:function() {
    $(this).trigger("fileUploaded");
    $(this).dialog("close");
  }}, {text:"\ucde8\uc18c", click:function() {
    $(this).dialog("close");
  }}]});
  $("#xlsfile_upload_dialog").dialog("open");
  $("#xlsfileupload").attr("action", EMD.context_root + url);
  $("#xlsfileupload").data("module", this);
  $("#xlsfile_upload_dialog").off("fileUploaded");
  $("#xlsfile_upload_dialog").on("fileUploaded", {done:uploadcomplete, module:this}, function(event) {
    event.data.done(event.data.module, null);
  });
};
EmdModule.prototype.downloadFile = function(filePhysiclNm, logicalName) {
  var url = "/download/downloadFile.do";
  var param = {"requestedFile":filePhysiclNm, "downloadFileName":logicalName};
  $.fileDownload(EMD.context_root + url, {data:param, httpMethod:"POST"});
};
EmdModule.prototype.addGisAssetsCdMap = function(mode, params) {
  EMD.util.window_hide_all();
  EMD.gis.addFeature(mode, params);
};
EmdModule.prototype.modifyGisAssetsCdMap = function(featureid, assetCode) {
  EMD.gis.modifyFeature(featureid, assetCode);
};
EmdModule.prototype.addAssetsCodeByLotcode = function(featureid, assetCode) {
};
EmdModule.prototype.showBupJungDongCodeLocation = function(code) {
  EMD.gis.showBupJungDongCode(code);
};
EmdModule.prototype.getImageUrl = function(filenm) {
  return EMD.context_root + "/cmm/getImage.do?physicalFileNm=" + filenm;
};
EmdModule.prototype.getPfPhotoUrl = function(filenm) {
  return EMD.context_root + "/cmm/getPfImage.do?physicalFileNm=" + filenm;
};
EmdModule.prototype.showWindow = function() {
  var window_id = "#" + this.getModuleId();
  EMD.util.window_show($(window_id));
};
EmdModule.prototype.hideWindow = function() {
  var window_id = "#" + this.getModuleId();
  EMD.util.window_hide($(window_id));
};
EmdModule.prototype.closeWindow = function(retval) {
  if (this._parent != null) {
    var closeqry = this._parent.closeChildWindow(this.getModuleId(), retval);
    if (closeqry != null && closeqry == false) {
      return false;
    }
  }
  var window_id = "#" + this.getModuleId();
  setTimeout(function() {
    EMD.util.window_close($(window_id));
  }, 0);
  return true;
};
EmdModule.prototype.closeChildWindow = function(window_id, retval) {
  return true;
};
EmdModule.prototype.requestEApproval = function(params, retfunc) {
  $("#__tempDiv").empty();
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
  if (params.type.substring(2, 3) == "N") {
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
  $(form).attr("action", EMD.context_root + "/cmmn/fclty/openApprovalRequest.do");
  $("#__tempDiv").append(form);
  var win = window.open("", "Window", "width=1024, height=768, menubar=no,status=yes,scrollbars=no");
  var module = this;
  form.target = "Window";
  form.submit();
};
EmdModule.prototype.printPayNotice = function(url, params, retfunc) {
  $("#__tempDiv").empty();
  var form = document.createElement("form");
  $(form).attr("id", "__printPayNoticeForm");
  var prtAtCode = document.createElement("input");
  var mngYear = document.createElement("input");
  var mngNo = document.createElement("input");
  var mngCnt = document.createElement("input");
  var nticCnt = document.createElement("input");
  var chrgeKnd = document.createElement("input");
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
  $(form).append(prtAtCode);
  $(form).append(mngYear);
  $(form).append(mngNo);
  $(form).append(mngCnt);
  $(form).append(nticCnt);
  $(form).append(chrgeKnd);
  $(form).attr("action", EMD.context_root + url);
  $("#__tempDiv").append(form);
  var win = window.open("", "payNotice", "width=800, height=600, menubar=no,status=no,scrollbars=yes");
  var module = this;
  win[win.addEventListener ? "addEventListener" : "attachEvent"]((win.attachEvent ? "on" : "") + "load", function(e) {
    console.log("debug listener");
    retfunc(module, "done");
  }, false);
  form.target = "payNotice";
  form.submit();
};
EmdModule.prototype.printTaxNotice = function(url, params, retfunc) {
  $("#__tempDiv").empty();
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
  $(form).attr("action", EMD.context_root + url);
  $("#__tempDiv").append(form);
  var win = window.open("", "taxNotice", "width=800, height=600, menubar=no,status=no,scrollbars=yes");
  var module = this;
  form.target = "taxNotice";
  form.submit();
};
EmdModule.prototype.printPage = function(url, params) {
  $("#__tempDiv").empty();
  var form = document.createElement("form");
  $(form).attr("accept-charset", "UTF-8");
  $(form).attr("id", "__printPayNoticeForm");
  $(form).attr("method", "post");
  for (var pn in params) {
    var e = document.createElement("input");
    $(e).attr("name", pn);
    $(form).attr("type", "hidden");
    $(e).val(params[pn]);
    $(form).append(e);
  }
  $(form).attr("action", EMD.context_root + url);
  $("#__tempDiv").append(form);
  var win = window.open("", "printPage", "width=800, height=600, menubar=no,status=no,scrollbars=yes");
  $(win).on("");
  var module = this;
  form.target = "printPage";
  form.submit();
};
EmdModule.prototype.modifyAssetsCodeFeature = function(assetCode, feature) {
  EMD.gis.modifyAssetCdFeature(assetCode, feature);
};
function EmdPopupModule(width, height) {
  this._popupCaller = null;
  this._popupId = null;
  this._returnValue = null;
  if (width == undefined || (width == null || (height == undefined || height == null))) {
    this._width = 720;
    this._height = 480;
  } else {
    this._width = width;
    this._height = height;
  }
  this.module = null;
  this.module_id = "__dialog-modal";
  this._moduleType = "_EMD_POPUP_MODULE";
}
EmdPopupModule.prototype = new EmdModuleBase;
EmdPopupModule.prototype.getModuleType = function() {
  return this._moduleType;
};
EmdPopupModule.prototype.setDialog = function(dlg) {
  this._dialog = dlg;
};
EmdPopupModule.prototype.getDialog = function(dlg) {
  return this._dialog;
};
EmdPopupModule.prototype.setPopupCaller = function(parent) {
  this._popupCaller = parent;
};
EmdPopupModule.prototype.onClosePopup = function(popupId, msg, retvalue) {
  alert("close dialog " + popupId);
};
EmdPopupModule.prototype.setPopupId = function(id) {
  this._popupId = id;
};
EmdPopupModule.prototype.getWidth = function() {
  return this._width;
};
EmdPopupModule.prototype.setWidth = function(value) {
  this._width = value;
};
EmdPopupModule.prototype.getHeight = function() {
  return this._height;
};
EmdPopupModule.prototype.setHeight = function(value) {
  this._height = value;
};
EmdPopupModule.prototype.onButtonClick = function(button_id) {
  alert("button " + button_id + " is clicked");
};
EmdPopupModule.prototype.resizable = function(value) {
  var resizable = this._dialog.dialog("option", "resizable");
  if (resizable != value) {
    this._dialog.dialog("option", "resizable", value);
  }
};
EmdPopupModule.prototype.closeDialog = function(msg, value) {
  this._dialog.dialog("close");
  this._popupCaller.onClosePopup(this._popupId, msg, value);
};
EmdPopupModule.prototype.cancelDialog = function() {
  this._dialog.dialog("close");
  this._popupCaller.onClosePopup(this._popupId, "cancel", null);
};
function EmdPopupInfoModule(width, height) {
  this._popupCaller = null;
  this._popupId = null;
  this._popup = null;
  this._feature = null;
  this._returnValue = null;
  if (width == undefined || (width == null || (height == undefined || height == null))) {
    this._width = 720;
    this._height = 480;
  } else {
    this._width = width;
    this._height = height;
  }
  this.module = null;
  this._moduleType = "_EMD_POPUP_INFO_MODULE";
}
EmdPopupInfoModule.prototype = new EmdModuleBase;
EmdPopupInfoModule.prototype.getModuleType = function() {
  return this._moduleType;
};
EmdPopupInfoModule.prototype.setPopupId = function(dlg) {
  this._popupId = dlg;
};
EmdPopupInfoModule.prototype.getPopupId = function() {
  return this._popupId;
};
EmdPopupInfoModule.prototype.setPopup = function(pop) {
  this._popup = pop;
};
EmdPopupInfoModule.prototype.getPopup = function() {
  return this._popup;
};
EmdPopupInfoModule.prototype.setFeature = function(feature) {
  this._feature = feature;
};
EmdPopupInfoModule.prototype.getFeature = function() {
  return this._feature;
};
EmdPopupInfoModule.prototype.getWidth = function() {
  return this._width;
};
EmdPopupInfoModule.prototype.setWidth = function(value) {
  this._width = value;
};
EmdPopupInfoModule.prototype.getHeight = function() {
  return this._height;
};
EmdPopupInfoModule.prototype.setHeight = function(value) {
  this._height = value;
};
EmdPopupInfoModule.prototype.onButtonClick = function(button_id) {
  alert("button " + button_id + " is clicked");
};
EmdPopupInfoModule.prototype.closeDialog = function(msg, value) {
  this._dialog.dialog("close");
  this._popupCaller.onClosePopup(this._popupId, msg, value);
};
EmdPopupInfoModule.prototype.cancelDialog = function() {
  this._dialog.dialog("close");
  this._popupCaller.onClosePopup(this._popupId, "cancel", null);
};
function EmdErrorModule() {
  this._msg = "\uc624\ub958\uac00 \ubc1c\uc0dd\ud558\uc600\uc2b5\ub2c8\ub2e4.";
  this._moduleType = "_EMD_ERROR_MODULE";
}
EmdErrorModule.prototype.getModuleType = function() {
  return this._moduleType;
};
EmdErrorModule.prototype.loadComplete = function() {
};
EmdErrorModule.prototype.setMessage = function(msg) {
  this._msg = msg;
};
EmdErrorModule.prototype.getMessage = function() {
  return this._msg;
};
(function($) {
  $.fn.getSelectedCodeLabel = function() {
    var label = "";
    if ($(this).hasClass("ygpaCmmnCode") || ($(this).hasClass("ygpaCmmnCl") || ($(this).hasClass("ygpaCmmnCd") || ($(this).hasClass("ygpaFilterCode") || ($(this).hasClass("ygpaDeptSelect") || $(this).hasClass("ygpaYnSelect")))))) {
      label = $("#" + $(this).attr("id") + "_select.frmwrkAuto").find(":selected").text();
    }
    return label;
  };
  $.getSelectedCodeLabel = function() {
    var label = "";
    if ($(this).hasClass("ygpaCmmnCode") || ($(this).hasClass("ygpaCmmnCl") || ($(this).hasClass("ygpaCmmnCd") || ($(this).hasClass("ygpaFilterCode") || ($(this).hasClass("ygpaDeptSelect") || $(this).hasClass("ygpaYnSelect")))))) {
      label = $("#" + $(this).attr("id") + "_select.frmwrkAuto").find(":selected").text();
    }
    return label;
  };
})(jQuery);
(function($) {
  function setSelectionRange(rangeStart, rangeEnd) {
    if (this.createTextRange) {
      var range = this.createTextRange();
      range.collapse(true);
      range.moveStart("character", rangeStart);
      range.moveEnd("character", rangeEnd - rangeStart);
      range.select();
    } else {
      if (this.setSelectionRange) {
        this.focus();
        this.setSelectionRange(rangeStart, rangeEnd);
      }
    }
  }
  function getSelection(part) {
    var pos = this.value.length;
    part = part.toLowerCase() == "start" ? "Start" : "End";
    if (document.selection) {
      var range = document.selection.createRange(), stored_range, selectionStart, selectionEnd;
      stored_range = range.duplicate();
      stored_range.expand("textedit");
      stored_range.setEndPoint("EndToEnd", range);
      selectionStart = stored_range.text.length - range.text.length;
      selectionEnd = selectionStart + range.text.length;
      return part == "Start" ? selectionStart : selectionEnd;
    } else {
      if (typeof this["selection" + part] != "undefined") {
        pos = this["selection" + part];
      }
    }
    return pos;
  }
  var _keydown = {codes:{188:44, 109:45, 190:46, 191:47, 192:96, 220:92, 222:39, 221:93, 219:91, 173:45, 187:61, 186:59, 189:45, 110:46}, shifts:{96:"~", 49:"!", 50:"@", 51:"#", 52:"$", 53:"%", 54:"^", 55:"&", 56:"*", 57:"(", 48:")", 45:"_", 61:"+", 91:"{", 93:"}", 92:"|", 59:":", 39:'"', 44:"<", 46:">", 47:"?"}};
  $.fn.number = function(number, decimals, dec_point, thousands_sep) {
    thousands_sep = typeof thousands_sep === "undefined" ? "," : thousands_sep;
    dec_point = typeof dec_point === "undefined" ? "." : dec_point;
    decimals = typeof decimals === "undefined" ? 0 : decimals;
    var u_dec = "\\u" + ("0000" + dec_point.charCodeAt(0).toString(16)).slice(-4), regex_dec_num = new RegExp("[^" + u_dec + "0-9]", "g"), regex_dec = new RegExp(u_dec, "g");
    if (number === true) {
      if (this.is("input:text")) {
        return this.on({"keydown.format":function(e) {
          var $this = $(this), data = $this.data("numFormat"), code = e.keyCode ? e.keyCode : e.which, chara = "", start = getSelection.apply(this, ["start"]), end = getSelection.apply(this, ["end"]), val = "", setPos = false;
          if (_keydown.codes.hasOwnProperty(code)) {
            code = _keydown.codes[code];
          }
          if (!e.shiftKey && (code >= 65 && code <= 90)) {
            code += 32;
          } else {
            if (!e.shiftKey && (code >= 69 && code <= 105)) {
              code -= 48;
            } else {
              if (e.shiftKey && _keydown.shifts.hasOwnProperty(code)) {
                chara = _keydown.shifts[code];
              }
            }
          }
          if (chara == "") {
            chara = String.fromCharCode(code);
          }
          if (code !== 8 && (chara != dec_point && !chara.match(/[0-9]/))) {
            var ct = true;
            if (chara.match(/[-]/)) {
              if (!data.isNegative) {
                data.isNegative = true;
                ct = false;
              }
            }
            if (ct) {
              var key = e.keyCode ? e.keyCode : e.which;
              if (key == 46 || (key == 8 || (key == 9 || (key == 27 || (key == 13 || ((key == 65 || key == 82) && (e.ctrlKey || e.metaKey) === true || ((key == 86 || key == 67) && (e.ctrlKey || e.metaKey) === true || key >= 35 && key <= 39))))))) {
                return;
              }
              e.preventDefault();
              return false;
            }
          }
          if (start == 0 && end == this.value.length || $this.val() == 0) {
            if (code === 8) {
              start = end = 1;
              this.value = "";
              data.init = decimals > 0 ? -1 : 0;
              data.c = decimals > 0 ? -(decimals + 1) : 0;
              setSelectionRange.apply(this, [0, 0]);
            } else {
              if (chara === dec_point) {
                start = end = 1;
                this.value = "0" + dec_point + (new Array(decimals + 1)).join("0");
                data.init = decimals > 0 ? 1 : 0;
                data.c = decimals > 0 ? -(decimals + 1) : 0;
              } else {
                data.init = decimals > 0 ? -1 : 0;
                data.c = decimals > 0 ? -(decimals + 1) : 0;
              }
            }
          } else {
            data.c = end - this.value.length;
          }
          if (code == 8 && (start <= 1 && data.isNegative)) {
            e.preventDefault();
            data.isNegative = false;
            data.c--;
            setPos = this.value.length + data.c;
          } else {
            if (decimals > 0 && (chara == dec_point && start == this.value.length - decimals - 1)) {
              data.c++;
              data.init = Math.max(0, data.init);
              e.preventDefault();
              setPos = this.value.length + data.c;
            } else {
              if (chara == dec_point) {
                data.init = Math.max(0, data.init);
                e.preventDefault();
              } else {
                if (decimals > 0 && (code == 8 && start == this.value.length - decimals)) {
                  e.preventDefault();
                  data.c--;
                  setPos = this.value.length + data.c;
                } else {
                  if (decimals > 0 && (code == 8 && start > this.value.length - decimals)) {
                    if (this.value === "") {
                      return;
                    }
                    if (this.value.slice(start - 1, start) != "0") {
                      val = this.value.slice(0, start - 1) + "0" + this.value.slice(start);
                      $this.val(val.replace(regex_dec_num, "").replace(regex_dec, dec_point));
                    }
                    e.preventDefault();
                    data.c--;
                    setPos = this.value.length + data.c;
                  } else {
                    if (code == 8 && this.value.slice(start - 1, start) == thousands_sep) {
                      e.preventDefault();
                      data.c--;
                      setPos = this.value.length + data.c;
                    } else {
                      if (decimals > 0 && (start == end && (this.value.length > decimals + 1 && (start > this.value.length - decimals - 1 && (isFinite(+chara) && (!e.metaKey && (!e.ctrlKey && (!e.altKey && chara.length === 1)))))))) {
                        if (end === this.value.length) {
                          val = this.value.slice(0, start - 1);
                        } else {
                          val = this.value.slice(0, start) + this.value.slice(start + 1);
                        }
                        this.value = val;
                        setPos = start;
                      }
                    }
                  }
                }
              }
            }
          }
          if (setPos !== false) {
            setSelectionRange.apply(this, [setPos, setPos]);
          }
          $this.data("numFormat", data);
        }, "keyup.format":function(e) {
          var $this = $(this), data = $this.data("numFormat"), code = e.keyCode ? e.keyCode : e.which, start = getSelection.apply(this, ["start"]), setPos;
          if (this.value === "" || (code < 48 || code > 57) && ((code < 96 || code > 105) && code !== 8)) {
            return;
          }
          $this.val($this.val());
          if (decimals > 0) {
            if (start > this.value.length - decimals && code != 8) {
              data.c++;
              $this.data("numFormat", data);
            }
          }
          if (!$this.get(0).value.length) {
            data.isNegative = false;
          } else {
            if (data.isNegative) {
              $this.get(0).value = "-" + this.value;
            }
          }
          setPos = this.value.length + data.c;
          setSelectionRange.apply(this, [setPos, setPos]);
        }, "paste.format":function(e) {
          var $this = $(this), original = e.originalEvent, val = null;
          if (window.clipboardData && window.clipboardData.getData) {
            val = window.clipboardData.getData("Text");
          } else {
            if (original.clipboardData && original.clipboardData.getData) {
              val = original.clipboardData.getData("text/plain");
            }
          }
          $this.val(val);
          e.preventDefault();
          return false;
        }}).each(function() {
          var $this = $(this).data("numFormat", {c:-(decimals + 1), decimals:decimals, thousands_sep:thousands_sep, dec_point:dec_point, regex_dec_num:regex_dec_num, regex_dec:regex_dec, init:-1});
          if (this.value === "") {
            return;
          }
          $this.val($this.val());
        });
      } else {
        return this.each(function() {
          var $this = $(this), isNegative = $this.text().match(/^-/) ? -1 : 1, num = +$this.text().replace(regex_dec_num, "").replace(regex_dec, ".") * isNegative;
          $this.number(!isFinite(num) ? 0 : +num, decimals, dec_point, thousands_sep);
        });
      }
    }
    return this.text($.number.apply(window, arguments));
  };
  var origHookGet = null, origHookSet = null;
  if ($.isPlainObject($.valHooks.text)) {
    if ($.isFunction($.valHooks.text.get)) {
      origHookGet = $.valHooks.text.get;
    }
    if ($.isFunction($.valHooks.text.set)) {
      origHookSet = $.valHooks.text.set;
    }
  } else {
    $.valHooks.text = {};
  }
  $.valHooks.text.get = function(el) {
    var $this = $(el), num, data = $this.data("numFormat");
    if (!data) {
      if ($.isFunction(origHookGet)) {
        return origHookGet(el);
      } else {
        return undefined;
      }
    } else {
      if (el.value === "") {
        return "";
      }
      if (el.value.match(/^-/)) {
        data.isNegative = true;
      }
      num = +el.value.replace(data.regex_dec_num, "").replace(data.regex_dec, ".");
      num = isFinite(num) ? num : 0;
      if (num != 0 && data.isNegative) {
        num *= -1;
      }
      return "" + num;
    }
  };
  $.valHooks.text.set = function(el, val) {
    var $this = $(el), data = $this.data("numFormat");
    if (!data) {
      if ($.isFunction(origHookSet)) {
        return origHookSet(el, val);
      } else {
        return undefined;
      }
    } else {
      if (val === "") {
        return el.value = "";
      }
      return el.value = $.number(val, data.decimals, data.dec_point, data.thousands_sep);
    }
  };
  $.number = function(number, decimals, dec_point, thousands_sep) {
    thousands_sep = typeof thousands_sep === "undefined" ? "," : thousands_sep;
    dec_point = typeof dec_point === "undefined" ? "." : dec_point;
    decimals = !isFinite(+decimals) ? 0 : Math.abs(decimals);
    var u_dec = "\\u" + ("0000" + dec_point.charCodeAt(0).toString(16)).slice(-4);
    var u_sep = "\\u" + ("0000" + thousands_sep.charCodeAt(0).toString(16)).slice(-4);
    number = (number + "").replace(".", dec_point).replace(new RegExp(u_sep, "g"), "").replace(new RegExp(u_dec, "g"), ".").replace(new RegExp("[^0-9+-Ee.]", "g"), "");
    var n = !isFinite(+number) ? 0 : +number, s = "", toFixedFix = function(n, decimals) {
      var k = Math.pow(10, decimals);
      return "" + Math.round(n * k) / k;
    };
    s = (decimals ? toFixedFix(n, decimals) : "" + Math.round(n)).split(".");
    if (s[0].length > 3) {
      s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, thousands_sep);
    }
    if ((s[1] || "").length < decimals) {
      s[1] = s[1] || "";
      s[1] += (new Array(decimals - s[1].length + 1)).join("0");
    }
    return s.join(dec_point);
  };
})(jQuery);
if (!Array.prototype.indexOf) {
  Array.prototype.indexOf = function(searchElement) {
    if (this == null) {
      throw new TypeError;
    }
    var t = Object(this);
    var len = t.length >>> 0;
    if (len === 0) {
      return-1;
    }
    var n = 0;
    if (arguments.length > 1) {
      n = Number(arguments[1]);
      if (n != n) {
        n = 0;
      } else {
        if (n != 0 && (n != Infinity && n != -Infinity)) {
          n = (n > 0 || -1) * Math.floor(Math.abs(n));
        }
      }
    }
    if (n >= len) {
      return-1;
    }
    var k = n >= 0 ? n : Math.max(len - Math.abs(n), 0);
    for (;k < len;k++) {
      if (k in t && t[k] === searchElement) {
        return k;
      }
    }
    return-1;
  };
}
(function($) {
  $.fn.makeSelectDeptCd = function() {
    return this.each(function() {
      var default_prompt = "";
      var default_value = "";
      var input_item = this;
      var input_id = $(this).attr("id");
      var win_id = "window_" + $(this).attr("id").split("_")[1];
      if ($(this).data("default-prompt") != null) {
        if ($(this).data("default-prompt") != null) {
          default_prompt = $(this).data("default-prompt");
        } else {
          default_prompt = "\uc120\ud0dd";
        }
        if ($(this).data("default-value") != null) {
          default_value = $(this).data("default-value");
        } else {
          default_value = "";
        }
      }
      if ($(this).data("display-value") == null) {
        try {
          $(this).attr("type", "hidden");
        } catch (err) {
          $(this).attr("id", input_id + "_backup");
          $(this).after('<input type="hidden" id="' + input_id + '" />');
          input_item = $("#" + win_id + " #" + input_id)[0];
          $(this).remove();
        }
        $(input_item).after('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
      } else {
        if ($(this).data("display-value") == "N") {
          $(this).attr("readonly", "readonly");
          $(this).before('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
        } else {
          $(this).attr("readonly", "readonly");
          $(this).after('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
        }
      }
      if (default_prompt != "") {
        $(this).append('<option value="' + default_value + '" selected>' + default_prompt + "</option>");
      }
      $(this).focus(function() {
        $("#" + $(this).attr("id") + "_select")[0].focus();
      });
      $.ajax({url:EMD.context_root + "/cmmn/selectDeptCodeOptionsList.do", type:"POST", module:input_item, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"cmd", value:""}], success:function(data) {
        if (data.resultCode != null && data.resultCode != 0) {
          if (data.resultCode == 1) {
            alert(data.resultMsg);
            location.reload();
          }
        } else {
          var selected_code = "";
          var comp_id = "#" + $(this.module).attr("id") + "_select";
          if ($(this.module).data("value") != undefined) {
            selected_code = $(this.module).data("value");
            for (var i = 0;i < data.resultList.length;i++) {
              if (selected_code == data.resultList[i].value) {
                $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '" selected>' + data.resultList[i].name + "</option>");
              } else {
                $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '">' + data.resultList[i].name + "</option>");
              }
            }
          } else {
            for (var i = 0;i < data.resultList.length;i++) {
              $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '">' + data.resultList[i].name + "</option>");
            }
          }
          $("#" + win_id).find(comp_id).change({module:this.module}, function(event) {
            $(event.data.module).val($(this).find("option:selected").val());
            $(event.data.module).trigger("change");
          });
          if ($(this.module).attr("readonly") == "readonly") {
            $(comp_id).attr("readonly", "readonly");
          }
          if ($(this.module).attr("disabled") == "disabled") {
            $(comp_id).attr("disabled", "disabled");
          }
        }
      }, error:function(XMLHttpRequest, textStatus, errorThrown) {
        try {
        } catch (e) {
        }
      }});
    });
  };
  $.fn.makeSelectCmmnCd = function() {
    return this.each(function() {
      var default_prompt = "";
      var default_value = "";
      var input_item = this;
      var win_id = "window_" + $(this).attr("id").split("_")[1];
      var input_id = $(this).attr("id");
      if ($(this).data("code-id") == undefined) {
        return;
      }
      if ($(this).data("default-prompt") != null) {
        if ($(this).data("default-prompt") != null) {
          default_prompt = $(this).data("default-prompt");
        } else {
          default_prompt = "\uc120\ud0dd";
        }
        if ($(this).data("default-value") != null) {
          default_value = $(this).data("default-value");
        } else {
          default_value = "";
        }
      }
      if ($(this).data("display-value") == null) {
        try {
          $(this).attr("type", "hidden");
        } catch (err) {
          $(this).attr("id", input_id + "_backup");
          $(this).after('<input type="hidden" id="' + input_id + '" />');
          input_item = $("#" + win_id + " #" + input_id)[0];
          $(this).remove();
        }
        $(input_item).after('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
      } else {
        if ($(this).data("display-value") == "N") {
          $(this).attr("readonly", "readonly");
          $(this).before('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
        } else {
          $(this).attr("readonly", "readonly");
          $(this).after('<select id="' + input_id + '_select" class="frmwrkAuto"></select>');
        }
      }
      if (default_prompt != "") {
        if ($(input_item).data("value") == null) {
          $("#" + win_id).find("#" + input_id + "_select").append('<option value="' + default_value + '" selected>' + default_prompt + "</option>");
        } else {
          $("#" + win_id).find("#" + input_id + "_select").append('<option value="' + default_value + '">' + default_prompt + "</option>");
        }
      }
      $(this).focus(function() {
        $("#" + $(this).attr("id") + "_select")[0].focus();
      });
      $.ajax({url:EMD.context_root + "/cmmn/selectCmmnCdOptionsList.do", type:"POST", module:input_item, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"code_id", value:$(this).data("code-id")}], success:function(data) {
        if (data.resultCode != null && data.resultCode != 0) {
          if (data.resultCode == 1) {
            alert(data.resultMsg);
            location.reload();
          }
        } else {
          var selected_code = "";
          var comp_id = "#" + $(this.module).attr("id") + "_select";
          if ($(this.module).data("value") != null) {
            selected_code = $(this.module).data("value");
            for (var i = 0;i < data.resultList.length;i++) {
              if (selected_code == data.resultList[i].value) {
                $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '" selected>' + data.resultList[i].name + "</option>");
              } else {
                $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '">' + data.resultList[i].name + "</option>");
              }
            }
          } else {
            for (var i = 0;i < data.resultList.length;i++) {
              $("#" + win_id).find(comp_id).append('<option value="' + data.resultList[i].value + '">' + data.resultList[i].name + "</option>");
            }
          }
          $("#" + win_id).find(comp_id).change({module:this.module}, function(event) {
            $(event.data.module).val($(this).find("option:selected").val());
            $(event.data.module).trigger("change");
          });
          if ($(this.module).attr("readonly") == "readonly") {
            $(comp_id).attr("readonly", "readonly");
          }
          if ($(this.module).attr("disabled") == "disabled") {
            $(comp_id).attr("disabled", "disabled");
          }
        }
      }, error:function(XMLHttpRequest, textStatus, errorThrown) {
      }});
    });
  };
})(jQuery);
(function($) {
  $.fn.popupWindow = function(instanceSettings) {
    return this.each(function() {
      $(this).click(function() {
        $.fn.popupWindow.defaultSettings = {centerBrowser:0, centerScreen:0, height:500, left:0, location:0, menubar:0, resizable:0, scrollbars:0, status:0, width:500, windowName:null, windowURL:null, top:0, toolbar:0};
        settings = $.extend({}, $.fn.popupWindow.defaultSettings, instanceSettings || {});
        var windowFeatures = "height=" + settings.height + ",width=" + settings.width + ",toolbar=" + settings.toolbar + ",scrollbars=" + settings.scrollbars + ",status=" + settings.status + ",resizable=" + settings.resizable + ",location=" + settings.location + ",menuBar=" + settings.menubar;
        settings.windowName = this.name || settings.windowName;
        settings.windowURL = this.href || settings.windowURL;
        var centeredY, centeredX;
        if (settings.centerBrowser) {
          if ($.browser.msie) {
            centeredY = window.screenTop - 120 + ((document.documentElement.clientHeight + 120) / 2 - settings.height / 2);
            centeredX = window.screenLeft + ((document.body.offsetWidth + 20) / 2 - settings.width / 2);
          } else {
            centeredY = window.screenY + (window.outerHeight / 2 - settings.height / 2);
            centeredX = window.screenX + (window.outerWidth / 2 - settings.width / 2);
          }
          window.open(settings.windowURL, settings.windowName, windowFeatures + ",left=" + centeredX + ",top=" + centeredY).focus();
        } else {
          if (settings.centerScreen) {
            centeredY = (screen.height - settings.height) / 2;
            centeredX = (screen.width - settings.width) / 2;
            window.open(settings.windowURL, settings.windowName, windowFeatures + ",left=" + centeredX + ",top=" + centeredY).focus();
          } else {
            window.open(settings.windowURL, settings.windowName, windowFeatures + ",left=" + settings.left + ",top=" + settings.top).focus();
          }
        }
        return false;
      });
    });
  };
})(jQuery);
(function($) {
  $.fn.disable = function(options) {
    var opts = $.extend({}, $.fn.disable.defaults, options);
    function disable($this) {
      $this.attr("disabled", "disabled");
      $("#" + $this.attr("id") + "_select").attr("disabled", "disabled");
      if (opts.disableClass !== "") {
        $this.addClass(opts.disableClass);
        $("#" + $this.attr("id") + "_select").addClass(opts.disableClass);
      }
      if (opts.enableClass !== "") {
        $this.removeClass(opts.enableClass);
        $("#" + $this.attr("id") + "_select").removeClass(opts.enableClass);
      }
    }
    function enable($this) {
      $this.removeAttr("disabled");
      $("#" + $this.attr("id") + "_select").removeAttr("disabled");
      if (opts.enableClass !== "") {
        $this.addClass(opts.enableClass);
        $("#" + $this.attr("id") + "_select").addClass(opts.enableClass);
      }
      if (opts.disableClass !== "") {
        $this.removeClass(opts.disableClass);
        $("#" + $this.attr("id") + "_select").removeClass(opts.disableClass);
      }
    }
    function setEnableTimeout($this) {
      setTimeout(function() {
        enable($this);
      }, opts.enableAfterSeconds * 1E3);
    }
    function setAjaxEnable($this) {
      $this.ajaxComplete(function(event, request, settings) {
        var statusOk = !opts.enableOnAjaxSuccess || opts.enableOnAjaxSuccess && request.status === 200, urlOk = opts.ajaxUrl === null || settings.url === opts.ajaxUrl, responseTextOk = opts.ajaxResponseText === null || request.responseText === opts.ajaxResponseText;
        if (statusOk && (urlOk && responseTextOk)) {
          enable($this);
        }
      });
    }
    return this.each(function() {
      var $this = $(this);
      disable($this);
      if (opts.enableAfterSeconds > 0) {
        setEnableTimeout($this);
      }
      if (opts.enableOnAjaxComplete || opts.enableOnAjaxSuccess) {
        setAjaxEnable($this);
      }
    });
  };
  $.fn.disable.defaults = {enableClass:"", disableClass:"", enableAfterSeconds:0, enableOnAjaxComplete:false, enableOnAjaxSuccess:false, ajaxUrl:null, ajaxResponseText:null};
  $.fn.enable = function(options) {
    var opts = $.extend({}, $.fn.enable.defaults, options);
    function enable($this) {
      $this.removeAttr("disabled");
      $("#" + $this.attr("id") + "_select").removeAttr("disabled");
      if (opts.enableClass !== "") {
        $this.addClass(opts.enableClass);
        $("#" + $this.attr("id") + "_select").addClass(opts.enableClass);
      }
      if (opts.disableClass !== "") {
        $this.removeClass(opts.disableClass);
        $("#" + $this.attr("id") + "_select").removeClass(opts.disableClass);
      }
    }
    return this.each(function() {
      var $this = $(this);
      enable($this);
    });
  };
  $.fn.enable.defaults = {enableClass:"", disableClass:""};
  $.fn.readonly = function(status, options) {
    var opts = $.extend({}, $.fn.readonly.defaults, options);
    function readonly($this, status) {
      $this.attr("readonly", status);
      $("#" + $this.attr("id") + "_select").attr("readonly", status);
      if (opts.readonlyClass !== "") {
        if (status) {
          $this.addClass(opts.readonlyClass);
          $("#" + $this.attr("id") + "_select").addClass(opts.readonlyClass);
        } else {
          $this.removeClass(opts.readonlyClass);
          $("#" + $this.attr("id") + "_select").removeClass(opts.readonlyClass);
        }
      }
    }
    function setReadonlyTimeout($this, status) {
      setTimeout(function() {
        readonly($this, status);
      }, opts.readonltAfterSeconds * 1E3);
    }
    function setAjaxReadonly($this, status) {
      $this.ajaxComplete(function(event, request, settings) {
        var statusOk = !opts.enableOnAjaxSuccess || opts.enableOnAjaxSuccess && request.status === 200, urlOk = opts.ajaxUrl === null || settings.url === opts.ajaxUrl, responseTextOk = opts.ajaxResponseText === null || request.responseText === opts.ajaxResponseText;
        if (statusOk && (urlOk && responseTextOk)) {
          readonly($this, status);
        }
      });
    }
    return this.each(function() {
      var $this = $(this);
      readonly($this, status);
      if (opts.readonlyAfterSeconds > 0) {
        setEnableTimeout($this, status);
      }
      if (opts.readonlyAjaxComplete || opts.readonlyAjaxSuccess) {
        setAjaxEnable($this, status);
      }
    });
  };
  $.fn.readonly.defaults = {readonlyClass:"", readonlyAfterSeconds:0, readonlyAjaxComplete:false, readonlyAjaxSuccess:false, ajaxUrl:null, ajaxResponseText:null};
})(jQuery);
(function($) {
  var originalVal = $.fn.val;
  $.fn.val = function(value) {
    if (typeof value == "undefined") {
      return originalVal.call(this);
    } else {
      if (typeof $(this).attr("id") != "undefined") {
        var s = $("#" + $(this).attr("id") + "_select.frmwrkAuto");
        if (s.length == 1) {
          originalVal.call(s, value);
        }
      }
      return originalVal.call(this, value);
    }
  };
})(jQuery);
(function(window, undefined) {
  "$:nomunge";
  var $ = window.jQuery || (window.Cowboy || (window.Cowboy = {})), jq_throttle;
  $.throttle = jq_throttle = function(delay, no_trailing, callback, debounce_mode) {
    var timeout_id, last_exec = 0;
    if (typeof no_trailing !== "boolean") {
      debounce_mode = callback;
      callback = no_trailing;
      no_trailing = undefined;
    }
    function wrapper() {
      var that = this, elapsed = +new Date - last_exec, args = arguments;
      function exec() {
        last_exec = +new Date;
        callback.apply(that, args);
      }
      function clear() {
        timeout_id = undefined;
      }
      if (debounce_mode && !timeout_id) {
        exec();
      }
      timeout_id && clearTimeout(timeout_id);
      if (debounce_mode === undefined && elapsed > delay) {
        exec();
      } else {
        if (no_trailing !== true) {
          timeout_id = setTimeout(debounce_mode ? clear : exec, debounce_mode === undefined ? delay - elapsed : delay);
        }
      }
    }
    if ($.guid) {
      wrapper.guid = callback.guid = callback.guid || $.guid++;
    }
    return wrapper;
  };
  $.debounce = function(delay, at_begin, callback) {
    return callback === undefined ? jq_throttle(delay, at_begin, false) : jq_throttle(delay, callback, at_begin !== false);
  };
})(this);
(function($, window) {
  var htmlSpecialCharsRegEx = /[<>&\r\n"']/gm;
  var htmlSpecialCharsPlaceHolders = {"<":"lt;", ">":"gt;", "&":"amp;", "\r":"#13;", "\n":"#10;", '"':"quot;", "'":"apos;"};
  $.extend({fileDownload:function(fileUrl, options) {
    var settings = $.extend({preparingMessageHtml:null, failMessageHtml:null, androidPostUnsupportedMessageHtml:"Unfortunately your Android browser doesn't support this type of file download. Please try again with a different browser.", dialogOptions:{modal:true}, prepareCallback:function(url) {
    }, successCallback:function(url) {
    }, failCallback:function(responseHtml, url) {
    }, httpMethod:"GET", data:null, checkInterval:100, cookieName:"fileDownload", cookieValue:"true", cookiePath:"/", popupWindowTitle:"Initiating file download...", encodeHTMLEntities:true}, options);
    var deferred = new $.Deferred;
    var userAgent = (navigator.userAgent || (navigator.vendor || window.opera)).toLowerCase();
    var isIos;
    var isAndroid;
    var isOtherMobileBrowser;
    if (/ip(ad|hone|od)/.test(userAgent)) {
      isIos = true;
    } else {
      if (userAgent.indexOf("android") !== -1) {
        isAndroid = true;
      } else {
        isOtherMobileBrowser = /avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|playbook|silk|iemobile|iris|kindle|lge |maemo|midp|mmp|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino/i.test(userAgent) || /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|e\-|e\/|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\-|2|g)|yas\-|your|zeto|zte\-/i.test(userAgent.substr(0, 
        4));
      }
    }
    var httpMethodUpper = settings.httpMethod.toUpperCase();
    if (isAndroid && httpMethodUpper !== "GET") {
      if ($().dialog) {
        $("<div>").html(settings.androidPostUnsupportedMessageHtml).dialog(settings.dialogOptions);
      } else {
        alert(settings.androidPostUnsupportedMessageHtml);
      }
      return deferred.reject();
    }
    var $preparingDialog = null;
    var internalCallbacks = {onPrepare:function(url) {
      if (settings.preparingMessageHtml) {
        $preparingDialog = $("<div>").html(settings.preparingMessageHtml).dialog(settings.dialogOptions);
      } else {
        if (settings.prepareCallback) {
          settings.prepareCallback(url);
        }
      }
    }, onSuccess:function(url) {
      if ($preparingDialog) {
        $preparingDialog.dialog("close");
      }
      settings.successCallback(url);
      deferred.resolve(url);
    }, onFail:function(responseHtml, url) {
      if ($preparingDialog) {
        $preparingDialog.dialog("close");
      }
      if (settings.failMessageHtml) {
        $("<div>").html(settings.failMessageHtml).dialog(settings.dialogOptions);
      }
      settings.failCallback(responseHtml, url);
      deferred.reject(responseHtml, url);
    }};
    internalCallbacks.onPrepare(fileUrl);
    if (settings.data !== null && typeof settings.data !== "string") {
      settings.data = $.param(settings.data);
    }
    var $iframe, downloadWindow, formDoc, $form;
    if (httpMethodUpper === "GET") {
      if (settings.data !== null) {
        var qsStart = fileUrl.indexOf("?");
        if (qsStart !== -1) {
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
      } else {
        if (isOtherMobileBrowser) {
          window.location(fileUrl);
        } else {
          $iframe = $("<iframe>").hide().prop("src", fileUrl).appendTo("body");
        }
      }
    } else {
      var formInnerHtml = "";
      if (settings.data !== null) {
        $.each(settings.data.replace(/\+/g, " ").split("&"), function() {
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
        $form.hide().prop("method", settings.httpMethod).prop("action", fileUrl).html(formInnerHtml);
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
        $form = $(formDoc).find("form");
      }
      $form.submit();
    }
    setTimeout(checkFileDownloadComplete, settings.checkInterval);
    function checkFileDownloadComplete() {
      if (document.cookie.indexOf(settings.cookieName + "=" + settings.cookieValue) != -1) {
        internalCallbacks.onSuccess(fileUrl);
        document.cookie = settings.cookieName + "=; expires=" + (new Date(1E3)).toUTCString() + "; path=" + settings.cookiePath;
        cleanUp(false);
        return;
      }
      if (downloadWindow || $iframe) {
        try {
          var formDoc = downloadWindow ? downloadWindow.document : getiframeDocument($iframe);
          if (formDoc && (formDoc.body != null && formDoc.body.innerHTML.length)) {
            var isFailure = true;
            if ($form && $form.length) {
              var $contents = $(formDoc.body).contents().first();
              try {
                if ($contents.length && $contents[0] === $form[0]) {
                  isFailure = false;
                }
              } catch (e) {
                if (e && e.number == -2146828218) {
                  isFailure = true;
                } else {
                  throw e;
                }
              }
            }
            if (isFailure) {
              setTimeout(function() {
                internalCallbacks.onFail(formDoc.body.innerHTML, fileUrl);
                cleanUp(true);
              }, 100);
              return;
            }
          }
        } catch (err) {
          internalCallbacks.onFail("", fileUrl);
          cleanUp(true);
          return;
        }
      }
      setTimeout(checkFileDownloadComplete, settings.checkInterval);
    }
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
              downloadWindow.focus();
              if (isFailure) {
                downloadWindow.close();
              }
            }
          }
        }
      }, 0);
    }
    function htmlSpecialCharsEntityEncode(str) {
      return str.replace(htmlSpecialCharsRegEx, function(match) {
        return "&" + htmlSpecialCharsPlaceHolders[match];
      });
    }
    return deferred.promise();
  }});
})(jQuery, this);
function eXcell_button(cell) {
  if (cell) {
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
  }
  this.disabledF = function(fl) {
    if (fl == true || fl == 1) {
      this.cell.innerHTML = this.cell.innerHTML.replace("item_chk0.", "item_chk0_dis.").replace("item_chk1.", "item_chk1_dis.");
    } else {
      this.cell.innerHTML = this.cell.innerHTML.replace("item_chk0_dis.", "item_chk0.").replace("item_chk1_dis.", "item_chk1.");
    }
  };
  this.buttonClick = function(fromClick) {
    if (fromClick === true && !this.grid.isActive) {
      if (window.globalActiveDHTMLGridObject != null && (window.globalActiveDHTMLGridObject != this.grid && window.globalActiveDHTMLGridObject.isActive)) {
        window.globalActiveDHTMLGridObject.setActive(false);
      }
      this.grid.setActive(true);
    }
    if (!this.grid.isEditable || (this.cell.parentNode._locked || this.isDisabled())) {
      return;
    }
    this.val = this.getValue();
    this.cell.wasChanged = true;
    this.grid.callEvent("onButtonClick", [this.cell.parentNode.idd, this.cell._cellIndex, this.val]);
  };
  this.getValue = function() {
    return this.cell.chstate ? this.cell.chstate.toString() : "0";
  };
  this.isButton = function() {
    return true;
  };
  this.detach = function() {
    return this.val != this.getValue();
  };
  this.edit = null;
}
eXcell_button.prototype = new eXcell;
eXcell_button.prototype.setValue = function(val) {
  this.cell.style.verticalAlign = "middle";
  this.setCValue("<input type='button' value='" + val + "' onclick='new eXcell_button(this.parentNode).buttonClick(true); (arguments[0]||event).cancelBubble=true; '>", val);
};
function eXcell_jqimg(cell) {
  if (cell) {
    this.cell = cell;
    this.grid = this.cell.parentNode.grid;
  }
  this.disabledF = function(fl) {
    if (fl == true || fl == 1) {
      $(this.cell).enable();
    } else {
      $(this.cell).disable();
    }
  };
  this.getValue = function() {
    return this.cell.chstate ? this.cell.chstate.toString() : "0";
  };
  this.detach = function() {
    return this.val != this.getValue();
  };
  this.edit = null;
}
eXcell_jqimg.prototype = new eXcell;
eXcell_jqimg.prototype.setValue = function(val) {
  this.cell.style.verticalAlign = "middle";
  if (val == null) {
    this.setCValue("<span></span>", val);
  } else {
    this.setCValue('<span class="ui-icon ui-icon-' + val + '"></span>', val);
  }
};
dhtmlXCalendarObject.prototype.langData["ko"] = {dateformat:"%Y-%m-%d", monthesFNames:["1\uc6d4", "2\uc6d4", "3\uc6d4", "4\uc6d4", "5\uc6d4", "6\uc6d4", "7\uc6d4", "8\uc6d4", "9\uc6d4", "10\uc6d4", "11\uc6d4", "12\uc6d4"], monthesSNames:["1\uc6d4", "2\uc6d4", "3\uc6d4", "4\uc6d4", "5\uc6d4", "6\uc6d4", "7\uc6d4", "8\uc6d4", "9\uc6d4", "10\uc6d4", "11\uc6d4", "12\uc6d4"], daysFNames:["\uc77c\uc694\uc77c", "\uc6d4\uc694\uc77c", "\ud654\uc694\uc77c", "\uc218\uc694\uc77c", "\ubaa9\uc694\uc77c", "\uae08\uc694\uc77c", 
"\ud1a0\uc694\uc77c"], daysSNames:["\uc77c", "\uc6d4", "\ud654", "\uc218", "\ubaa9", "\uae08", "\ud1a0"], weekstart:0, weekname:"\uc8fc"};
dhtmlXCalendarObject.prototype.lang = "ko";
(function($) {
  if (typeof $.support.selectstart != "function") {
    $.support.selectstart = "onselectstart" in document.createElement("div");
  }
  if (typeof $.fn.disableSelection != "function") {
    $.fn.disableSelection = function() {
      return this.bind(($.support.selectstart ? "selectstart" : "mousedown") + ".ui-disableSelection", function(event) {
        event.preventDefault();
      });
    };
  }
  $.fn.flexigrid = function(p) {
    return this.each(function() {
      var grid_id = $(this).attr("id");
      var w, h;
      $(this).attr("id", "___temp");
      w = $(this).css("width");
      h = $(this).css("height");
      if (p.width != undefined) {
        w = p.width;
      }
      if (p.height != undefined) {
        if (p.height != "auto") {
          h = p.height;
        } else {
          $(this).addClass("fillHeight");
        }
      }
      if ($(this).hasClass("fillHeight")) {
        $(this).removeClass("fillHeight");
        $(this).after("<div id='" + grid_id + "' style='width:" + w + "px; height:" + h + "px;' class='fillHeight'></div>");
      } else {
        $(this).after("<div id='" + grid_id + "' style='width:" + w + "px; height:" + h + "px;'></div>");
      }
      var dgrid = new dhtmlXGridObject(grid_id);
      var col_headers = null;
      var col_ids = null;
      var col_widths = null;
      var col_aligns = null;
      var col_valigns = null;
      var col_types = null;
      var col_sorting = null;
      var col_footers = null;
      var header_style = [];
      var footer_style = [];
      var nformat = [];
      var combo_list = [];
      $.each(p.colModel, function(index, value) {
        var str;
        var col_sort = "str";
        col_headers = col_headers == null ? this.display : col_headers + "," + this.display;
        col_ids = col_ids == null ? this.name : col_ids + "," + this.name;
        col_widths = col_widths == null ? this.width : col_widths + "," + this.width;
        switch(this.halign) {
          case "right":
            header_style[header_style.length] = "text-align:right;";
            break;
          case "left":
            header_style[header_style.length] = "text-align:left;";
            break;
          default:
            header_style[header_style.length] = "text-align:center;";
            break;
        }
        switch(this.align) {
          case "right":
            str = "right";
            break;
          case "left":
            str = "left";
            break;
          default:
            str = "center";
            break;
        }
        col_aligns = col_aligns == null ? str : col_aligns + "," + str;
        switch(this.valign) {
          case "top":
            str = "top";
            break;
          case "bottom":
            str = "bottom";
            break;
          case "baseline":
            str = "baseline";
            break;
          default:
            str = "middle";
            break;
        }
        col_valigns = col_valigns == null ? str : col_valigns + "," + str;
        switch(this.displayFormat) {
          case "checkbox":
            str = "ch";
            col_sort = "na";
            break;
          case "number":
            str = "ron";
            col_sort = "int";
            if (this.displayOption != undefined) {
              if (typeof this.displayOption == "string") {
                nformat[nformat.length] = {mask:this.displayOption, cInd:index};
              } else {
                if (typeof this.displayOption == "object") {
                  nformat[nformat.length] = {mask:this.displayOption.format, cInd:index};
                }
              }
            } else {
              nformat[nformat.length] = {mask:"0,000", cInd:index};
            }
            break;
          case "button":
            str = "button";
            col_sort = "na";
            if (this.displayOption != undefined) {
            } else {
            }
            break;
          case "jqimg":
            str = "jqimg";
            break;
          case "select":
            str = "coro";
            col_sort = "na";
            if (this.displayOption != undefined) {
              if (typeof this.displayOption == "string") {
                combo_list[combo_list.length] = {items:this.displayOption, cInd:index};
              } else {
                if (typeof this.displayOption == "object") {
                  var ol = [];
                  $.each(this.displayOption, function() {
                    ol[ol.length] = [this.value, this.name];
                  });
                  combo_list[combo_list.length] = {items:ol, cInd:index};
                }
              }
            } else {
            }
            break;
          case "input":
            str = "ed";
            break;
          case "input-number":
            str = "edn";
            if (this.displayOption != undefined) {
              if (typeof this.displayOption == "string") {
                nformat[nformat.length] = {mask:this.displayOption, cInd:index};
              } else {
                if (typeof this.displayOption == "object") {
                  nformat[nformat.length] = {mask:this.displayOption.format, cInd:index};
                }
              }
            } else {
              nformat[nformat.length] = {mask:"0,000", cInd:index};
            }
            break;
          case "dyn":
            str = "dyn";
            break;
          case "image":
            str = "image";
            break;
          case "cal":
            str = "dhxCalendar";
            break;
          default:
            str = "ro";
            break;
        }
        col_types = col_types == null ? str : col_types + "," + str;
        if (this.sortType != undefined) {
          switch(this.sortType) {
            case "number":
              col_sort = "int";
              break;
            case "text":
              col_sort = "str";
              break;
            default:
              col_sort = this.sortType;
          }
        }
        col_sorting = col_sorting == null ? col_sort : col_sorting + "," + col_sort;
      });
      dgrid.setImagePath(EMD.context_root + "/js/codebase/imgs/");
      dgrid.setHeader(col_headers, null, header_style);
      dgrid.setColumnIds(col_ids);
      dgrid.setInitWidths(col_widths);
      dgrid.setColTypes(col_types);
      dgrid.setColAlign(col_aligns);
      dgrid.setColVAlign(col_valigns);
      dgrid.enableEditEvents(true, false, true);
      dgrid.setDateFormat("%Y-%m-%d");
      dgrid._mergeRows = null;
      if (p.mergeRows != undefined) {
        if (typeof p.mergeRows == "string") {
          dgrid._mergeRows = p.mergeRows.split(",");
        } else {
          dgrid._mergeRows = p.mergeRows;
        }
        dgrid.enableRowspan();
      }
      dgrid._groupBy = null;
      if (p.groupBy != undefined) {
        dgrid._groupBy = dgrid.getColIndexById(p.groupBy);
      }
      $.each(nformat, function() {
        dgrid.setNumberFormat(this.mask, this.cInd);
      });
      $.each(combo_list, function() {
        var combo = dgrid.getCombo(this.cInd);
        $.each(this.items, function() {
          combo.put(this[0], this[1]);
        });
      });
      if (p.multiline != undefined && p.multiline == true) {
        dgrid.enableMultiline(true);
      }
      if (p.multiSelect != undefined && p.multiSelect == true) {
        dgrid.enableMultiselect(true);
      } else {
        dgrid.enableMultiselect(false);
      }
      dgrid.setColSorting(col_sorting);
      dgrid._defaultPageUnit = 3E3;
      dgrid._totalCount = 0;
      dgrid._loadCount = 0;
      dgrid._doMergeRow = function() {
        var beforeVal = [];
        var beforeId = [];
        var countRow = [];
        var proc_id = null;
        var s = false;
        this.forEachRow(function(id) {
          var br = false;
          proc_id = id;
          var inx = this.getRowIndex(id);
          var row = this.rowsBuffer[inx]._attrs;
          for (var a in this._mergeRows) {
            var cid = this._mergeRows[a];
            if (!s) {
              beforeVal[cid] = row[cid];
              beforeId[cid] = id;
              countRow[cid] = 1;
              s = true;
            } else {
              if (!br && beforeVal[cid] != row[cid]) {
                if (countRow[cid] > 1) {
                  this.setRowspan(beforeId[cid], this.getColIndexById(cid), countRow[cid]);
                  countRow[cid] = 1;
                }
                br = true;
                beforeId[cid] = id;
                beforeVal[cid] = row[cid];
              } else {
                if (br) {
                  if (countRow[cid] > 1) {
                    this.setRowspan(beforeId[cid], this.getColIndexById(cid), countRow[cid]);
                    countRow[cid] = 1;
                  }
                  beforeId[cid] = id;
                  beforeVal[cid] = row[cid];
                } else {
                  countRow[cid]++;
                }
              }
            }
          }
        });
        if (proc_id != null) {
          var id = proc_id;
          var br = false;
          var inx = this.getRowIndex(id);
          var row = this.rowsBuffer[inx]._attrs;
          for (var a in this._mergeRows) {
            var cid = this._mergeRows[a];
            if (!br) {
              if (countRow[cid] > 1 && (beforeVal[cid] == row[cid] && id != beforeId[cid])) {
                this.setRowspan(beforeId[cid], this.getColIndexById(cid), countRow[cid] + 1);
              } else {
                this.setRowspan(beforeId[cid], this.getColIndexById(cid), countRow[cid]);
                br = true;
              }
            } else {
              if (id - beforeId[cid] > 1) {
                this.setRowspan(beforeId[cid], this.getColIndexById(cid), countRow[cid]);
              }
            }
          }
        }
      };
      dgrid._loadData = function(data) {
        if (data.resultCode != null && data.resultCode != 0) {
          if (data.resultCode == 1) {
            alert(data.resultMsg);
            location.reload();
          }
        } else {
          if (this.p.preProcess != undefined) {
            data = this.p.preProcess(this.p.module, data);
          }
          var d;
          if (this.rowsBuffer != undefined && this.rowsBuffer.length !== 0) {
            d = this.rowsAr.concat(data.resultList);
            this._loadCount = this._loadCount + d.length;
          } else {
            d = data.resultList;
            this._loadCount = d.length;
          }
          this.parse(d, "js");
          if (this._mergeRows != null) {
            this._doMergeRow();
          }
          if (this._groupBy != null) {
            this.groupBy(this._groupBy);
          }
          this._totalCount = data.totalCount;
          $("#" + this._grid_id).trigger("onLoadDataComplete", [this.p.module, d]);
        }
      };
      dgrid._clearData = function() {
        this._loadCount = 0;
        this.clearAll();
      };
      dgrid._appendRow = function(obj) {
        var rowId = null;
        if (this.rowsBuffer != undefined) {
          rowId = this.rowsBuffer.length + 1;
        } else {
          rowId = 0;
        }
        this.addRow(rowId, "");
        var rid = this.getRowIndex(rowId);
        var r = this.render_row(rid);
        this._process_js_row(r, obj);
        return rowId;
      };
      dgrid._updateRow = function(rowId, obj) {
        if (obj == null) {
          return;
        }
        this.forEachCell(rowId, function(cellObj, ind) {
          var id = cellObj.grid.getColumnId(ind);
          if (obj[id] != undefined) {
            cellObj.setValue(obj[id]);
          }
        });
        this.rowsBuffer[this.getRowIndex(rowId)]._attrs = obj;
      };
      dgrid.attachEvent("onRowSelect", function(id, ind) {
        var row = this.rowsBuffer[this.getRowIndex(id)]._attrs;
        $("#" + this._grid_id).trigger("onItemSelected", [this.p.module, row]);
        $("#" + this._grid_id).trigger("onSelectChanged", [this.p.module, row]);
      });
      dgrid.attachEvent("onRowDblClicked", function(id, ind) {
        var row = this.rowsBuffer[this.getRowIndex(id)]._attrs;
        $("#" + this._grid_id).trigger("onItemDoubleClick", [this.p.module, row]);
      });
      dgrid.attachEvent("onCheckbox", function(rid, cInd, state) {
        var rid = this.getRowIndex(rid);
        var row = this.rowsBuffer[rid]._attrs;
        row[this.getColumnId(cInd)] = state;
        $("#" + this._grid_id).trigger("onItemCheckboxClick", [this.p.module, row, rid, cInd]);
      });
      dgrid.attachEvent("onButtonClick", function(rid, cInd, val) {
        var rid = this.getRowIndex(rid);
        var row = this.rowsBuffer[rid]._attrs;
        $("#" + this._grid_id).trigger("onButtonClicked", [this.p.module, this.getColumnId(cInd), row, val]);
      });
      dgrid.attachEvent("onEditCell", function(stage, rId, cInd, nValue, oValue) {
        if (stage == "2") {
          var val = this.cells(rId, cInd).getValue();
          var rid = this.getRowIndex(rId);
          var cId = this.getColumnId(cInd);
          this.rowsBuffer[rid]._attrs[cId] = val;
          var row = this.rowsBuffer[rid]._attrs;
          if (nValue != oValue) {
            $("#" + this._grid_id).trigger("onCellEdited", [this.p.module, row, rId, cId, oValue]);
          }
          return true;
        } else {
          return true;
        }
      });
      dgrid.attachEvent("onScroll", function(sLeft, sTop) {
        return;
        if (this._pageLoading != undefined && this._pageLoading == true) {
          return;
        }
        if (this._totalCount != this._loadCount) {
          var state = this.getStateOfView();
          if (state[0] + state[1] >= this._loadCount * 0.9) {
            this._pageLoading = true;
            this._pageIndex++;
            var params = this.p.params == null ? [] : this.p.params.slice();
            params[params.length] = {name:"pageIndex", value:this._pageIndex};
            params[params.length] = {name:"pageUnit", value:this._defaultPageUnit};
            $.ajax({url:EMD.context_root + this.p.url, type:"POST", grid_id:this._grid_id, grid:this, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:params, success:function(data) {
              this.grid._loadData(data);
              this.grid._pageLoading = false;
            }, error:function(XMLHttpRequest, textStatus, errorThrown) {
              try {
                this.grid._pageLoading = false;
              } catch (e) {
              }
            }});
          }
        }
      });
      dgrid.init();
      dgrid._url = p.url;
      dgrid.p = p;
      dgrid._grid_id = grid_id;
      $("#" + grid_id)[0]["dgrid"] = dgrid;
      $("#" + grid_id).resize(function() {
        this.dgrid.setSizes();
      });
      $(this).remove();
    });
  };
  $.fn.flexReload = function(p) {
    return this.each(function() {
      if (this.dgrid == undefined) {
        console.log("grid not init!");
        return;
      }
      this.dgrid._pageIndex = 1;
      var params = [];
      if (this.dgrid.p.params != null) {
        if (this.dgrid.p.params.slice == undefined) {
          params = EMD.util.objectToArray(this.dgrid.p.params);
        } else {
          params = this.dgrid.p.params.slice();
        }
      }
      params[params.length] = {name:"pageIndex", value:this.dgrid._pageIndex};
      params[params.length] = {name:"pageUnit", value:this.dgrid._defaultPageUnit};
      $.ajax({url:EMD.context_root + this.dgrid.p.url, type:"POST", grid_id:$(this).attr("id"), grid:this.dgrid, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:params, success:function(data) {
        this.grid._clearData();
        this.grid._loadData(data);
      }, error:function(XMLHttpRequest, textStatus, errorThrown) {
        try {
          console.log(textStatus);
        } catch (e) {
        }
      }});
    });
  };
  $.fn.flexEmptyData = function(p) {
    return this.each(function() {
      if (this.dgrid == undefined) {
        console.log("grid not init!");
        return;
      }
      this.dgrid._clearData();
    });
  };
  $.fn.flexOptions = function(p) {
    return this.each(function() {
      if (this.dgrid) {
        $.extend(this.dgrid.p, p);
      }
    });
  };
  $.fn.flexToggleCol = function(cid, visible) {
    return this.each(function() {
      if (this.dgrid == undefined) {
        console.log("grid not init!");
        return;
      }
      var col = this.dgrid.getColIndexById(cid);
      this.dgrid.setColumnHidden(col, visible);
    });
  };
  $.fn.flexAddData = function(data) {
    return this.each(function() {
      if (this.dgrid == undefined) {
        console.log("grid not init!");
        return;
      }
      this.dgrid._loadData(data);
    });
  };
  $.fn.flexAddRow = function(row) {
    if ($(this).length == 0) {
      console.log("error flexAddRow selection");
      return-1;
    }
    var t = $(this)[0];
    if (t.dgrid == undefined) {
      console.log("grid not init!");
      return-2;
    }
    return t.dgrid._appendRow(row);
  };
  $.fn.flexUpdateRow = function(i, row) {
    if ($(this).length == 0) {
      console.log("error flexUpdateRow selection");
      return-1;
    }
    var t = $(this)[0];
    if (t.dgrid == undefined) {
      console.log("grid not init!");
      return-2;
    }
    t.dgrid._updateRow(i, row);
  };
  $.fn.flexRemoveRow = function(rowIndex) {
    if ($(this).length == 0) {
      console.log("error flexAddRow selection");
      return-1;
    }
    var t = $(this)[0];
    if (t.dgrid == undefined) {
      console.log("grid not init!");
      return-2;
    }
    t.dgrid.deleteRow(rowIndex);
  };
  $.fn.noSelect = function(p) {
    if ($(this).length == 0) {
      console.log("error flexAddRow selection");
      return-1;
    }
    var t = $(this)[0];
    if (t.dgrid == undefined) {
      console.log("grid not init!");
      return-2;
    }
    t.dgrid.clearSelection();
  };
  $.fn.selectedRows = function(p) {
    if ($(this).length == 0) {
      console.log("error flexAddRow selection");
      return-1;
    }
    var t = $(this)[0];
    if (t.dgrid == undefined) {
      console.log("grid not init!");
      return-2;
    }
    var selectedRowIds = t.dgrid.getSelectedRowId();
    var arReturn = [];
    if (selectedRowIds == null) {
      return[];
    }
    $.each(selectedRowIds.split(","), function() {
      var id = t.dgrid.getRowIndex(this);
      if (id >= 0 && id < t.dgrid.rowsBuffer.length) {
        arReturn[arReturn.length] = t.dgrid.rowsBuffer[id]._attrs;
      } else {
        console.log("[DGRID:Error] grid out of bounds");
      }
    });
    return arReturn;
  };
  $.fn.selectedRowIds = function(p) {
    if ($(this).length == 0) {
      console.log("error selectedRowIds selection");
      return-1;
    }
    var t = $(this)[0];
    if (t.dgrid == undefined) {
      console.log("grid not init!");
      return-2;
    }
    var selectedRowIds = t.dgrid.getSelectedRowId();
    if (selectedRowIds != null) {
      return selectedRowIds.split(",");
    }
    return[];
  };
  $.fn.selectRowId = function(rownum) {
    if ($(this).length == 0) {
      console.log("error selectRowId selection");
      return-1;
    }
    var t = $(this)[0];
    if (t.dgrid == undefined) {
      console.log("grid not init!");
      return-2;
    }
    if (rownum == undefined) {
      t.dgrid.clearSelection();
      return;
    }
    t.dgrid.selectRow(t.dgrid.getRowIndex(rownum));
  };
  $.fn.selectPrevRow = function() {
    if ($(this).length == 0) {
      console.log("error selectRowId selection");
      return-1;
    }
    var t = $(this)[0];
    if (t.dgrid == undefined) {
      console.log("grid not init!");
      return-2;
    }
    var selectedRowIds = t.dgrid.getSelectedRowId();
    if (selectedRowIds == null) {
      return false;
    }
    var rownum = selectedRowIds.split(",")[0];
    if (rownum == undefined || rownum == "") {
      return false;
    }
    var rowid = t.dgrid.getRowIndex(rownum);
    if (rowid == 0) {
      return false;
    }
    rowid--;
    t.dgrid.selectRow(rowid);
    return true;
  };
  $.fn.selectNextRow = function() {
    if ($(this).length == 0) {
      console.log("error selectRowId selection");
      return-1;
    }
    var t = $(this)[0];
    if (t.dgrid == undefined) {
      console.log("grid not init!");
      return-2;
    }
    var selectedRowIds = t.dgrid.getSelectedRowId();
    if (selectedRowIds == null) {
      return false;
    }
    var rownum = selectedRowIds.split(",")[0];
    if (rownum == undefined || rownum == "") {
      return false;
    }
    var rowid = t.dgrid.getRowIndex(rownum);
    if (rowid >= t.dgrid.getRowsNum() - 1) {
      return false;
    }
    rowid++;
    t.dgrid.selectRow(rowid);
    return true;
  };
  $.fn.selectFilterRow = function(filter, ncount) {
    if ($(this).length == 0) {
      console.log("error selectFilterData selection");
      return-1;
    }
    var t = $(this)[0];
    if (t.dgrid == undefined) {
      console.log("grid not init!");
      return-2;
    }
    if (ncount == undefined) {
      ncount = 1;
    }
    var arReturn = [];
    $.each(t.dgrid.rowsBuffer, function(i, row) {
      var filtered = false;
      arRow = {};
      $.each(filter, function() {
        if (typeof row._attrs[this.col] != "undefined" && row._attrs[this.col] == this.filter) {
          filtered = true;
        } else {
          filtered = false;
        }
        return filtered != false;
      });
      if (filtered) {
        arReturn[arReturn.length] = row;
      }
    });
    for (var i = 0;i < ncount;i++) {
      t.dgrid.selectRow(arReturn[i]);
    }
  };
  $.fn.selectedRowCount = function() {
    if ($(this).length == 0) {
      console.log("error selectedRowCount selection");
      return-1;
    }
    var t = $(this)[0];
    if (t.dgrid == undefined) {
      console.log("grid not init!");
      return-2;
    }
    var selectedRowIds = t.dgrid.getSelectedRowId();
    if (selectedRowIds != null) {
      return selectedRowIds.split(",").length;
    }
    return 0;
  };
  $.fn.flexGetData = function(p) {
    if ($(this).length == 0) {
      console.log("error flexGetData selection");
      return[];
    }
    var t = $(this)[0];
    if (t.dgrid == undefined) {
      console.log("grid not init!");
      return[];
    }
    var rows = [];
    for (var i = 0;i < t.dgrid.rowsBuffer.length;i++) {
      rows[rows.length] = t.dgrid.rowsBuffer[i]._attrs;
    }
    return rows;
  };
  $.fn.flexRowCount = function(p) {
    if ($(this).length == 0) {
      console.log("error flexRowCount selection");
      return 0;
    }
    var t = $(this)[0];
    if (t.dgrid == undefined) {
      console.log("grid not init!");
      return 0;
    }
    return t.dgrid.getRowsNum();
  };
  $.fn.flexGetRow = function(p) {
    if ($(this).length == 0) {
      console.log("error flexGetRow selection");
      return null;
    }
    var t = $(this)[0];
    if (t.dgrid == undefined) {
      console.log("grid not init!");
      return null;
    }
    return t.dgrid.rowsBuffer[t.dgrid.getRowIndex(p)]._attrs;
  };
  $.fn.selectFilterData = function(filter) {
    if ($(this).length == 0) {
      console.log("error selectFilterData selection");
      return-1;
    }
    var t = $(this)[0];
    if (t.dgrid == undefined) {
      console.log("grid not init!");
      return-2;
    }
    var arReturn = [];
    $.each(t.dgrid.rowsBuffer, function(i, row) {
      var filtered = false;
      arRow = {};
      $.each(filter, function() {
        if (typeof row._attrs[this.col] != "undefined" && row._attrs[this.col] == this.filter) {
          filtered = true;
        } else {
          filtered = false;
        }
        return filtered != false;
      });
      if (filtered) {
        arReturn.push(row._attrs);
      }
    });
    return arReturn;
  };
  $.fn.resizeGrid = function() {
    if ($(this).length == 0) {
      console.log("error flexAddRow selection");
      return-1;
    }
    var t = $(this)[0];
    if (t.dgrid == undefined) {
      console.log("grid not init!");
      return-2;
    }
    t.dgrid.setSizes();
  };
  $.fn.flexExcelDown = function(url, fileName) {
    return this.each(function() {
      if (this.dgrid == undefined) {
        console.log("grid not init!");
        return;
      }
      var p = this.dgrid.p;
      var param = [{name:"pageIndex", value:p.pageIndex}, {name:"pageUnit", value:9999}, {name:"recordCountPerPage", value:9999}];
      if (p.params.length) {
        for (var pi = 0;pi < p.params.length;pi++) {
          param[param.length] = p.params[pi];
        }
      }
      var header = [];
      $.each(this.dgrid.p.colModel, function() {
        header[header.length] = {title:this.display, abbr:this.name};
      });
      if (fileName == null) {
        fileName = "\uc870\ud68c\uacb0\uacfc.xls";
      }
      param[param.length] = {name:"header", value:JSON.stringify(header)};
      param[param.length] = {name:"fileName", value:fileName};
      $.fileDownload(EMD.context_root + url, {data:param, httpMethod:"POST"});
    });
  };
  $.fn.flexiXlsDown = function(fileName, title) {
    return this.each(function() {
      if (this.dgrid == undefined) {
        console.log("grid not init!");
        return;
      }
      var p = this.dgrid.p;
      var param = [];
      var header = [];
      $.each(this.dgrid.p.colModel, function() {
        if (this.xlsOption != undefined) {
          if (this.skipxls == undefined || !this.skipxls) {
            header[header.length] = {title:this.display, align:this.align, format:this.displayFormat, width:this.width, abbr:this.name, option:this.xlsOption};
          }
        }
        if (this.skipxls == undefined || !this.skipxls) {
          header[header.length] = {title:this.display, align:this.align, format:this.displayFormat, width:this.width, abbr:this.name};
        }
      });
      var rows = [];
      for (var i = 0;i < this.dgrid.rowsBuffer.length;i++) {
        rows[rows.length] = this.dgrid.rowsBuffer[i]._attrs;
      }
      if (fileName == null) {
        fileName = "gis.xls";
      }
      if (title == null) {
        title = "GIS\uae30\ubc18 \uc790\uc0b0\uad00\ub9ac \uc2dc\uc2a4\ud15c";
      }
      param[param.length] = {name:"header", value:JSON.stringify(header)};
      param[param.length] = {name:"data", value:JSON.stringify(rows)};
      param[param.length] = {name:"fileName", value:fileName};
      param[param.length] = {name:"title", value:title};
      $.fileDownload(EMD.context_root + "/cmmn/excel/downloadGridToExcel.do", {data:param, httpMethod:"POST"});
    });
  };
})(jQuery);
!function(a) {
  var b = function(a, c) {
    var d = /[^\w\-\.:]/.test(a) ? new Function(b.arg + ",tmpl", "var _e=tmpl.encode" + b.helper + ",_s='" + a.replace(b.regexp, b.func) + "';return _s;") : b.cache[a] = b.cache[a] || b(b.load(a));
    return c ? d(c, b) : function(a) {
      return d(a, b);
    };
  };
  b.cache = {}, b.load = function(a) {
    return document.getElementById(a).innerHTML;
  }, b.regexp = /([\s'\\])(?!(?:[^{]|\{(?!%))*%\})|(?:\{%(=|#)([\s\S]+?)%\})|(\{%)|(%\})/g, b.func = function(a, b, c, d, e, f) {
    return b ? {"\n":"\\n", "\r":"\\r", "\t":"\\t", " ":" "}[b] || "\\" + b : c ? "=" === c ? "'+_e(" + d + ")+'" : "'+(" + d + "==null?'':" + d + ")+'" : e ? "';" : f ? "_s+='" : void 0;
  }, b.encReg = /[<>&"'\x00]/g, b.encMap = {"<":"&lt;", ">":"&gt;", "&":"&amp;", '"':"&quot;", "'":"&#39;"}, b.encode = function(a) {
    return(null == a ? "" : "" + a).replace(b.encReg, function(a) {
      return b.encMap[a] || "";
    });
  }, b.arg = "o", b.helper = ",print=function(s,e){_s+=e?(s==null?'':s):_e(s);},include=function(s,d){_s+=tmpl(s,d);}", "function" == typeof define && define.amd ? define(function() {
    return b;
  }) : a.tmpl = b;
}(this);
!function(a) {
  var b = function(a, c, d) {
    var e, f, g = document.createElement("img");
    if (g.onerror = c, g.onload = function() {
      !f || (d && d.noRevoke || b.revokeObjectURL(f)), c && c(b.scale(g, d));
    }, b.isInstanceOf("Blob", a) || b.isInstanceOf("File", a)) {
      e = f = b.createObjectURL(a), g._type = a.type;
    } else {
      if ("string" != typeof a) {
        return!1;
      }
      e = a, d && (d.crossOrigin && (g.crossOrigin = d.crossOrigin));
    }
    return e ? (g.src = e, g) : b.readFile(a, function(a) {
      var b = a.target;
      b && b.result ? g.src = b.result : c && c(a);
    });
  }, c = window.createObjectURL && window || (window.URL && (URL.revokeObjectURL && URL) || window.webkitURL && webkitURL);
  b.isInstanceOf = function(a, b) {
    return Object.prototype.toString.call(b) === "[object " + a + "]";
  }, b.transformCoordinates = function() {
  }, b.getTransformedOptions = function(a) {
    return a;
  }, b.renderImageToCanvas = function(a, b, c, d, e, f, g, h, i, j) {
    return a.getContext("2d").drawImage(b, c, d, e, f, g, h, i, j), a;
  }, b.hasCanvasOption = function(a) {
    return a.canvas || a.crop;
  }, b.scale = function(a, c) {
    c = c || {};
    var d, e, f, g, h, i, j, k, l, m = document.createElement("canvas"), n = a.getContext || b.hasCanvasOption(c) && m.getContext, o = a.naturalWidth || a.width, p = a.naturalHeight || a.height, q = o, r = p, s = function() {
      var a = Math.max((f || q) / q, (g || r) / r);
      a > 1 && (q = Math.ceil(q * a), r = Math.ceil(r * a));
    }, t = function() {
      var a = Math.min((d || q) / q, (e || r) / r);
      1 > a && (q = Math.ceil(q * a), r = Math.ceil(r * a));
    };
    return n && (c = b.getTransformedOptions(c), j = c.left || 0, k = c.top || 0, c.sourceWidth ? (h = c.sourceWidth, void 0 !== c.right && (void 0 === c.left && (j = o - h - c.right))) : h = o - j - (c.right || 0), c.sourceHeight ? (i = c.sourceHeight, void 0 !== c.bottom && (void 0 === c.top && (k = p - i - c.bottom))) : i = p - k - (c.bottom || 0), q = h, r = i), d = c.maxWidth, e = c.maxHeight, f = c.minWidth, g = c.minHeight, n && (d && (e && c.crop)) ? (q = d, r = e, l = h / i - d / e, 0 > 
    l ? (i = e * h / d, void 0 === c.top && (void 0 === c.bottom && (k = (p - i) / 2))) : l > 0 && (h = d * i / e, void 0 === c.left && (void 0 === c.right && (j = (o - h) / 2)))) : ((c.contain || c.cover) && (f = d = d || f, g = e = e || g), c.cover ? (t(), s()) : (s(), t())), n ? (m.width = q, m.height = r, b.transformCoordinates(m, c), b.renderImageToCanvas(m, a, j, k, h, i, 0, 0, q, r)) : (a.width = q, a.height = r, a);
  }, b.createObjectURL = function(a) {
    return c ? c.createObjectURL(a) : !1;
  }, b.revokeObjectURL = function(a) {
    return c ? c.revokeObjectURL(a) : !1;
  }, b.readFile = function(a, b, c) {
    if (window.FileReader) {
      var d = new FileReader;
      if (d.onload = d.onerror = b, c = c || "readAsDataURL", d[c]) {
        return d[c](a), d;
      }
    }
    return!1;
  }, "function" == typeof define && define.amd ? define(function() {
    return b;
  }) : a.loadImage = b;
}(this), function(a) {
  "function" == typeof define && define.amd ? define(["load-image"], a) : a(window.loadImage);
}(function(a) {
  if (window.navigator && (window.navigator.platform && /iP(hone|od|ad)/.test(window.navigator.platform))) {
    var b = a.renderImageToCanvas;
    a.detectSubsampling = function(a) {
      var b, c;
      return a.width * a.height > 1048576 ? (b = document.createElement("canvas"), b.width = b.height = 1, c = b.getContext("2d"), c.drawImage(a, -a.width + 1, 0), 0 === c.getImageData(0, 0, 1, 1).data[3]) : !1;
    }, a.detectVerticalSquash = function(a, b) {
      var c, d, e, f, g, h = a.naturalHeight || a.height, i = document.createElement("canvas"), j = i.getContext("2d");
      for (b && (h /= 2), i.width = 1, i.height = h, j.drawImage(a, 0, 0), c = j.getImageData(0, 0, 1, h).data, d = 0, e = h, f = h;f > d;) {
        g = c[4 * (f - 1) + 3], 0 === g ? e = f : d = f, f = e + d >> 1;
      }
      return f / h || 1;
    }, a.renderImageToCanvas = function(c, d, e, f, g, h, i, j, k, l) {
      if ("image/jpeg" === d._type) {
        var m, n, o, p, q = c.getContext("2d"), r = document.createElement("canvas"), s = 1024, t = r.getContext("2d");
        if (r.width = s, r.height = s, q.save(), m = a.detectSubsampling(d), m && (e /= 2, f /= 2, g /= 2, h /= 2), n = a.detectVerticalSquash(d, m), m || 1 !== n) {
          for (f *= n, k = Math.ceil(s * k / g), l = Math.ceil(s * l / h / n), j = 0, p = 0;h > p;) {
            for (i = 0, o = 0;g > o;) {
              t.clearRect(0, 0, s, s), t.drawImage(d, e, f, g, h, -o, -p, g, h), q.drawImage(r, 0, 0, s, s, i, j, k, l), o += s, i += k;
            }
            p += s, j += l;
          }
          return q.restore(), c;
        }
      }
      return b(c, d, e, f, g, h, i, j, k, l);
    };
  }
}), function(a) {
  "function" == typeof define && define.amd ? define(["load-image"], a) : a(window.loadImage);
}(function(a) {
  var b = a.hasCanvasOption;
  a.hasCanvasOption = function(a) {
    return b(a) || a.orientation;
  }, a.transformCoordinates = function(a, b) {
    var c = a.getContext("2d"), d = a.width, e = a.height, f = b.orientation;
    if (f) {
      switch(f > 4 && (a.width = e, a.height = d), f) {
        case 2:
          c.translate(d, 0), c.scale(-1, 1);
          break;
        case 3:
          c.translate(d, e), c.rotate(Math.PI);
          break;
        case 4:
          c.translate(0, e), c.scale(1, -1);
          break;
        case 5:
          c.rotate(0.5 * Math.PI), c.scale(1, -1);
          break;
        case 6:
          c.rotate(0.5 * Math.PI), c.translate(0, -e);
          break;
        case 7:
          c.rotate(0.5 * Math.PI), c.translate(d, -e), c.scale(-1, 1);
          break;
        case 8:
          c.rotate(-0.5 * Math.PI), c.translate(-d, 0);
      }
    }
  }, a.getTransformedOptions = function(a) {
    if (!a.orientation || 1 === a.orientation) {
      return a;
    }
    var b, c = {};
    for (b in a) {
      a.hasOwnProperty(b) && (c[b] = a[b]);
    }
    switch(a.orientation) {
      case 2:
        c.left = a.right, c.right = a.left;
        break;
      case 3:
        c.left = a.right, c.top = a.bottom, c.right = a.left, c.bottom = a.top;
        break;
      case 4:
        c.top = a.bottom, c.bottom = a.top;
        break;
      case 5:
        c.left = a.top, c.top = a.left, c.right = a.bottom, c.bottom = a.right;
        break;
      case 6:
        c.left = a.top, c.top = a.right, c.right = a.bottom, c.bottom = a.left;
        break;
      case 7:
        c.left = a.bottom, c.top = a.right, c.right = a.top, c.bottom = a.left;
        break;
      case 8:
        c.left = a.bottom, c.top = a.left, c.right = a.top, c.bottom = a.right;
    }
    return a.orientation > 4 && (c.maxWidth = a.maxHeight, c.maxHeight = a.maxWidth, c.minWidth = a.minHeight, c.minHeight = a.minWidth, c.sourceWidth = a.sourceHeight, c.sourceHeight = a.sourceWidth), c;
  };
}), function(a) {
  "function" == typeof define && define.amd ? define(["load-image"], a) : a(window.loadImage);
}(function(a) {
  var b = window.Blob && (Blob.prototype.slice || (Blob.prototype.webkitSlice || Blob.prototype.mozSlice));
  a.blobSlice = b && function() {
    var a = this.slice || (this.webkitSlice || this.mozSlice);
    return a.apply(this, arguments);
  }, a.metaDataParsers = {jpeg:{65505:[]}}, a.parseMetaData = function(b, c, d) {
    d = d || {};
    var e = this, f = d.maxMetaDataSize || 262144, g = {}, h = !(window.DataView && (b && (b.size >= 12 && ("image/jpeg" === b.type && a.blobSlice))));
    (h || !a.readFile(a.blobSlice.call(b, 0, f), function(b) {
      if (b.target.error) {
        return console.log(b.target.error), c(g), void 0;
      }
      var f, h, i, j, k = b.target.result, l = new DataView(k), m = 2, n = l.byteLength - 4, o = m;
      if (65496 === l.getUint16(0)) {
        for (;n > m && (f = l.getUint16(m), f >= 65504 && 65519 >= f || 65534 === f);) {
          if (h = l.getUint16(m + 2) + 2, m + h > l.byteLength) {
            console.log("Invalid meta data: Invalid segment size.");
            break;
          }
          if (i = a.metaDataParsers.jpeg[f]) {
            for (j = 0;j < i.length;j += 1) {
              i[j].call(e, l, m, h, g, d);
            }
          }
          m += h, o = m;
        }
        !d.disableImageHead && (o > 6 && (g.imageHead = k.slice ? k.slice(0, o) : (new Uint8Array(k)).subarray(0, o)));
      } else {
        console.log("Invalid JPEG file: Missing JPEG marker.");
      }
      c(g);
    }, "readAsArrayBuffer")) && c(g);
  };
}), function(a) {
  "function" == typeof define && define.amd ? define(["load-image", "load-image-meta"], a) : a(window.loadImage);
}(function(a) {
  a.ExifMap = function() {
    return this;
  }, a.ExifMap.prototype.map = {Orientation:274}, a.ExifMap.prototype.get = function(a) {
    return this[a] || this[this.map[a]];
  }, a.getExifThumbnail = function(a, b, c) {
    var d, e, f;
    if (!c || b + c > a.byteLength) {
      return console.log("Invalid Exif data: Invalid thumbnail data."), void 0;
    }
    for (d = [], e = 0;c > e;e += 1) {
      f = a.getUint8(b + e), d.push((16 > f ? "0" : "") + f.toString(16));
    }
    return "data:image/jpeg,%" + d.join("%");
  }, a.exifTagTypes = {1:{getValue:function(a, b) {
    return a.getUint8(b);
  }, size:1}, 2:{getValue:function(a, b) {
    return String.fromCharCode(a.getUint8(b));
  }, size:1, ascii:!0}, 3:{getValue:function(a, b, c) {
    return a.getUint16(b, c);
  }, size:2}, 4:{getValue:function(a, b, c) {
    return a.getUint32(b, c);
  }, size:4}, 5:{getValue:function(a, b, c) {
    return a.getUint32(b, c) / a.getUint32(b + 4, c);
  }, size:8}, 9:{getValue:function(a, b, c) {
    return a.getInt32(b, c);
  }, size:4}, 10:{getValue:function(a, b, c) {
    return a.getInt32(b, c) / a.getInt32(b + 4, c);
  }, size:8}}, a.exifTagTypes[7] = a.exifTagTypes[1], a.getExifValue = function(b, c, d, e, f, g) {
    var h, i, j, k, l, m, n = a.exifTagTypes[e];
    if (!n) {
      return console.log("Invalid Exif data: Invalid tag type."), void 0;
    }
    if (h = n.size * f, i = h > 4 ? c + b.getUint32(d + 8, g) : d + 8, i + h > b.byteLength) {
      return console.log("Invalid Exif data: Invalid data offset."), void 0;
    }
    if (1 === f) {
      return n.getValue(b, i, g);
    }
    for (j = [], k = 0;f > k;k += 1) {
      j[k] = n.getValue(b, i + k * n.size, g);
    }
    if (n.ascii) {
      for (l = "", k = 0;k < j.length && (m = j[k], "\x00" !== m);k += 1) {
        l += m;
      }
      return l;
    }
    return j;
  }, a.parseExifTag = function(b, c, d, e, f) {
    var g = b.getUint16(d, e);
    f.exif[g] = a.getExifValue(b, c, d, b.getUint16(d + 2, e), b.getUint32(d + 4, e), e);
  }, a.parseExifTags = function(a, b, c, d, e) {
    var f, g, h;
    if (c + 6 > a.byteLength) {
      return console.log("Invalid Exif data: Invalid directory offset."), void 0;
    }
    if (f = a.getUint16(c, d), g = c + 2 + 12 * f, g + 4 > a.byteLength) {
      return console.log("Invalid Exif data: Invalid directory size."), void 0;
    }
    for (h = 0;f > h;h += 1) {
      this.parseExifTag(a, b, c + 2 + 12 * h, d, e);
    }
    return a.getUint32(g, d);
  }, a.parseExifData = function(b, c, d, e, f) {
    if (!f.disableExif) {
      var g, h, i, j = c + 10;
      if (1165519206 === b.getUint32(c + 4)) {
        if (j + 8 > b.byteLength) {
          return console.log("Invalid Exif data: Invalid segment size."), void 0;
        }
        if (0 !== b.getUint16(c + 8)) {
          return console.log("Invalid Exif data: Missing byte alignment offset."), void 0;
        }
        switch(b.getUint16(j)) {
          case 18761:
            g = !0;
            break;
          case 19789:
            g = !1;
            break;
          default:
            return console.log("Invalid Exif data: Invalid byte alignment marker."), void 0;
        }
        if (42 !== b.getUint16(j + 2, g)) {
          return console.log("Invalid Exif data: Missing TIFF marker."), void 0;
        }
        h = b.getUint32(j + 4, g), e.exif = new a.ExifMap, h = a.parseExifTags(b, j, j + h, g, e), h && (!f.disableExifThumbnail && (i = {exif:{}}, h = a.parseExifTags(b, j, j + h, g, i), i.exif[513] && (e.exif.Thumbnail = a.getExifThumbnail(b, j + i.exif[513], i.exif[514])))), e.exif[34665] && (!f.disableExifSub && a.parseExifTags(b, j, j + e.exif[34665], g, e)), e.exif[34853] && (!f.disableExifGps && a.parseExifTags(b, j, j + e.exif[34853], g, e));
      }
    }
  }, a.metaDataParsers.jpeg[65505].push(a.parseExifData);
}), function(a) {
  "function" == typeof define && define.amd ? define(["load-image", "load-image-exif"], a) : a(window.loadImage);
}(function(a) {
  a.ExifMap.prototype.tags = {256:"ImageWidth", 257:"ImageHeight", 34665:"ExifIFDPointer", 34853:"GPSInfoIFDPointer", 40965:"InteroperabilityIFDPointer", 258:"BitsPerSample", 259:"Compression", 262:"PhotometricInterpretation", 274:"Orientation", 277:"SamplesPerPixel", 284:"PlanarConfiguration", 530:"YCbCrSubSampling", 531:"YCbCrPositioning", 282:"XResolution", 283:"YResolution", 296:"ResolutionUnit", 273:"StripOffsets", 278:"RowsPerStrip", 279:"StripByteCounts", 513:"JPEGInterchangeFormat", 514:"JPEGInterchangeFormatLength", 
  301:"TransferFunction", 318:"WhitePoint", 319:"PrimaryChromaticities", 529:"YCbCrCoefficients", 532:"ReferenceBlackWhite", 306:"DateTime", 270:"ImageDescription", 271:"Make", 272:"Model", 305:"Software", 315:"Artist", 33432:"Copyright", 36864:"ExifVersion", 40960:"FlashpixVersion", 40961:"ColorSpace", 40962:"PixelXDimension", 40963:"PixelYDimension", 42240:"Gamma", 37121:"ComponentsConfiguration", 37122:"CompressedBitsPerPixel", 37500:"MakerNote", 37510:"UserComment", 40964:"RelatedSoundFile", 
  36867:"DateTimeOriginal", 36868:"DateTimeDigitized", 37520:"SubSecTime", 37521:"SubSecTimeOriginal", 37522:"SubSecTimeDigitized", 33434:"ExposureTime", 33437:"FNumber", 34850:"ExposureProgram", 34852:"SpectralSensitivity", 34855:"PhotographicSensitivity", 34856:"OECF", 34864:"SensitivityType", 34865:"StandardOutputSensitivity", 34866:"RecommendedExposureIndex", 34867:"ISOSpeed", 34868:"ISOSpeedLatitudeyyy", 34869:"ISOSpeedLatitudezzz", 37377:"ShutterSpeedValue", 37378:"ApertureValue", 37379:"BrightnessValue", 
  37380:"ExposureBias", 37381:"MaxApertureValue", 37382:"SubjectDistance", 37383:"MeteringMode", 37384:"LightSource", 37385:"Flash", 37396:"SubjectArea", 37386:"FocalLength", 41483:"FlashEnergy", 41484:"SpatialFrequencyResponse", 41486:"FocalPlaneXResolution", 41487:"FocalPlaneYResolution", 41488:"FocalPlaneResolutionUnit", 41492:"SubjectLocation", 41493:"ExposureIndex", 41495:"SensingMethod", 41728:"FileSource", 41729:"SceneType", 41730:"CFAPattern", 41985:"CustomRendered", 41986:"ExposureMode", 
  41987:"WhiteBalance", 41988:"DigitalZoomRatio", 41989:"FocalLengthIn35mmFilm", 41990:"SceneCaptureType", 41991:"GainControl", 41992:"Contrast", 41993:"Saturation", 41994:"Sharpness", 41995:"DeviceSettingDescription", 41996:"SubjectDistanceRange", 42016:"ImageUniqueID", 42032:"CameraOwnerName", 42033:"BodySerialNumber", 42034:"LensSpecification", 42035:"LensMake", 42036:"LensModel", 42037:"LensSerialNumber", 0:"GPSVersionID", 1:"GPSLatitudeRef", 2:"GPSLatitude", 3:"GPSLongitudeRef", 4:"GPSLongitude", 
  5:"GPSAltitudeRef", 6:"GPSAltitude", 7:"GPSTimeStamp", 8:"GPSSatellites", 9:"GPSStatus", 10:"GPSMeasureMode", 11:"GPSDOP", 12:"GPSSpeedRef", 13:"GPSSpeed", 14:"GPSTrackRef", 15:"GPSTrack", 16:"GPSImgDirectionRef", 17:"GPSImgDirection", 18:"GPSMapDatum", 19:"GPSDestLatitudeRef", 20:"GPSDestLatitude", 21:"GPSDestLongitudeRef", 22:"GPSDestLongitude", 23:"GPSDestBearingRef", 24:"GPSDestBearing", 25:"GPSDestDistanceRef", 26:"GPSDestDistance", 27:"GPSProcessingMethod", 28:"GPSAreaInformation", 29:"GPSDateStamp", 
  30:"GPSDifferential", 31:"GPSHPositioningError"}, a.ExifMap.prototype.stringValues = {ExposureProgram:{0:"Undefined", 1:"Manual", 2:"Normal program", 3:"Aperture priority", 4:"Shutter priority", 5:"Creative program", 6:"Action program", 7:"Portrait mode", 8:"Landscape mode"}, MeteringMode:{0:"Unknown", 1:"Average", 2:"CenterWeightedAverage", 3:"Spot", 4:"MultiSpot", 5:"Pattern", 6:"Partial", 255:"Other"}, LightSource:{0:"Unknown", 1:"Daylight", 2:"Fluorescent", 3:"Tungsten (incandescent light)", 
  4:"Flash", 9:"Fine weather", 10:"Cloudy weather", 11:"Shade", 12:"Daylight fluorescent (D 5700 - 7100K)", 13:"Day white fluorescent (N 4600 - 5400K)", 14:"Cool white fluorescent (W 3900 - 4500K)", 15:"White fluorescent (WW 3200 - 3700K)", 17:"Standard light A", 18:"Standard light B", 19:"Standard light C", 20:"D55", 21:"D65", 22:"D75", 23:"D50", 24:"ISO studio tungsten", 255:"Other"}, Flash:{0:"Flash did not fire", 1:"Flash fired", 5:"Strobe return light not detected", 7:"Strobe return light detected", 
  9:"Flash fired, compulsory flash mode", 13:"Flash fired, compulsory flash mode, return light not detected", 15:"Flash fired, compulsory flash mode, return light detected", 16:"Flash did not fire, compulsory flash mode", 24:"Flash did not fire, auto mode", 25:"Flash fired, auto mode", 29:"Flash fired, auto mode, return light not detected", 31:"Flash fired, auto mode, return light detected", 32:"No flash function", 65:"Flash fired, red-eye reduction mode", 69:"Flash fired, red-eye reduction mode, return light not detected", 
  71:"Flash fired, red-eye reduction mode, return light detected", 73:"Flash fired, compulsory flash mode, red-eye reduction mode", 77:"Flash fired, compulsory flash mode, red-eye reduction mode, return light not detected", 79:"Flash fired, compulsory flash mode, red-eye reduction mode, return light detected", 89:"Flash fired, auto mode, red-eye reduction mode", 93:"Flash fired, auto mode, return light not detected, red-eye reduction mode", 95:"Flash fired, auto mode, return light detected, red-eye reduction mode"}, 
  SensingMethod:{1:"Undefined", 2:"One-chip color area sensor", 3:"Two-chip color area sensor", 4:"Three-chip color area sensor", 5:"Color sequential area sensor", 7:"Trilinear sensor", 8:"Color sequential linear sensor"}, SceneCaptureType:{0:"Standard", 1:"Landscape", 2:"Portrait", 3:"Night scene"}, SceneType:{1:"Directly photographed"}, CustomRendered:{0:"Normal process", 1:"Custom process"}, WhiteBalance:{0:"Auto white balance", 1:"Manual white balance"}, GainControl:{0:"None", 1:"Low gain up", 
  2:"High gain up", 3:"Low gain down", 4:"High gain down"}, Contrast:{0:"Normal", 1:"Soft", 2:"Hard"}, Saturation:{0:"Normal", 1:"Low saturation", 2:"High saturation"}, Sharpness:{0:"Normal", 1:"Soft", 2:"Hard"}, SubjectDistanceRange:{0:"Unknown", 1:"Macro", 2:"Close view", 3:"Distant view"}, FileSource:{3:"DSC"}, ComponentsConfiguration:{0:"", 1:"Y", 2:"Cb", 3:"Cr", 4:"R", 5:"G", 6:"B"}, Orientation:{1:"top-left", 2:"top-right", 3:"bottom-right", 4:"bottom-left", 5:"left-top", 6:"right-top", 7:"right-bottom", 
  8:"left-bottom"}}, a.ExifMap.prototype.getText = function(a) {
    var b = this.get(a);
    switch(a) {
      case "LightSource":
      ;
      case "Flash":
      ;
      case "MeteringMode":
      ;
      case "ExposureProgram":
      ;
      case "SensingMethod":
      ;
      case "SceneCaptureType":
      ;
      case "SceneType":
      ;
      case "CustomRendered":
      ;
      case "WhiteBalance":
      ;
      case "GainControl":
      ;
      case "Contrast":
      ;
      case "Saturation":
      ;
      case "Sharpness":
      ;
      case "SubjectDistanceRange":
      ;
      case "FileSource":
      ;
      case "Orientation":
        return this.stringValues[a][b];
      case "ExifVersion":
      ;
      case "FlashpixVersion":
        return String.fromCharCode(b[0], b[1], b[2], b[3]);
      case "ComponentsConfiguration":
        return this.stringValues[a][b[0]] + this.stringValues[a][b[1]] + this.stringValues[a][b[2]] + this.stringValues[a][b[3]];
      case "GPSVersionID":
        return b[0] + "." + b[1] + "." + b[2] + "." + b[3];
    }
    return String(b);
  }, function(a) {
    var b, c = a.tags, d = a.map;
    for (b in c) {
      c.hasOwnProperty(b) && (d[c[b]] = b);
    }
  }(a.ExifMap.prototype), a.ExifMap.prototype.getAll = function() {
    var a, b, c = {};
    for (a in this) {
      this.hasOwnProperty(a) && (b = this.tags[a], b && (c[b] = this.getText(b)));
    }
    return c;
  };
});
!function(a) {
  var b = a.HTMLCanvasElement && a.HTMLCanvasElement.prototype, c = a.Blob && function() {
    try {
      return Boolean(new Blob);
    } catch (a) {
      return!1;
    }
  }(), d = c && (a.Uint8Array && function() {
    try {
      return 100 === (new Blob([new Uint8Array(100)])).size;
    } catch (a) {
      return!1;
    }
  }()), e = a.BlobBuilder || (a.WebKitBlobBuilder || (a.MozBlobBuilder || a.MSBlobBuilder)), f = (c || e) && (a.atob && (a.ArrayBuffer && (a.Uint8Array && function(a) {
    var b, f, g, h, i, j;
    for (b = a.split(",")[0].indexOf("base64") >= 0 ? atob(a.split(",")[1]) : decodeURIComponent(a.split(",")[1]), f = new ArrayBuffer(b.length), g = new Uint8Array(f), h = 0;h < b.length;h += 1) {
      g[h] = b.charCodeAt(h);
    }
    return i = a.split(",")[0].split(":")[1].split(";")[0], c ? new Blob([d ? g : f], {type:i}) : (j = new e, j.append(f), j.getBlob(i));
  })));
  a.HTMLCanvasElement && (!b.toBlob && (b.mozGetAsFile ? b.toBlob = function(a, c, d) {
    d && (b.toDataURL && f) ? a(f(this.toDataURL(c, d))) : a(this.mozGetAsFile("blob", c));
  } : b.toDataURL && (f && (b.toBlob = function(a, b, c) {
    a(f(this.toDataURL(b, c)));
  })))), "function" == typeof define && define.amd ? define(function() {
    return f;
  }) : a.dataURLtoBlob = f;
}(this);
!function(a) {
  "function" == typeof define && define.amd ? define(["./blueimp-helper"], a) : (window.blueimp = window.blueimp || {}, window.blueimp.Gallery = a(window.blueimp.helper || window.jQuery));
}(function(a) {
  function b(a, c) {
    return void 0 === document.body.style.maxHeight ? null : this && this.options === b.prototype.options ? a && a.length ? (this.list = a, this.num = a.length, this.initOptions(c), void this.initialize()) : void this.console.log("blueimp Gallery: No or empty list provided as first argument.", a) : new b(a, c);
  }
  return a.extend(b.prototype, {options:{container:"#blueimp-gallery", slidesContainer:"div", titleElement:"h3", displayClass:"blueimp-gallery-display", controlsClass:"blueimp-gallery-controls", singleClass:"blueimp-gallery-single", leftEdgeClass:"blueimp-gallery-left", rightEdgeClass:"blueimp-gallery-right", playingClass:"blueimp-gallery-playing", slideClass:"slide", slideLoadingClass:"slide-loading", slideErrorClass:"slide-error", slideContentClass:"slide-content", toggleClass:"toggle", prevClass:"prev", 
  nextClass:"next", closeClass:"close", playPauseClass:"play-pause", typeProperty:"type", titleProperty:"title", urlProperty:"href", displayTransition:!0, clearSlides:!0, stretchImages:!1, toggleControlsOnReturn:!0, toggleSlideshowOnSpace:!0, enableKeyboardNavigation:!0, closeOnEscape:!0, closeOnSlideClick:!0, closeOnSwipeUpOrDown:!0, emulateTouchEvents:!0, stopTouchEventsPropagation:!1, hidePageScrollbars:!0, disableScroll:!0, carousel:!1, continuous:!0, unloadElements:!0, startSlideshow:!1, slideshowInterval:5E3, 
  index:0, preloadRange:2, transitionSpeed:400, slideshowTransitionSpeed:void 0, event:void 0, onopen:void 0, onopened:void 0, onslide:void 0, onslideend:void 0, onslidecomplete:void 0, onclose:void 0, onclosed:void 0}, carouselOptions:{hidePageScrollbars:!1, toggleControlsOnReturn:!1, toggleSlideshowOnSpace:!1, enableKeyboardNavigation:!1, closeOnEscape:!1, closeOnSlideClick:!1, closeOnSwipeUpOrDown:!1, disableScroll:!1, startSlideshow:!0}, console:window.console && "function" == typeof window.console.log ? 
  window.console : {log:function() {
  }}, support:function(b) {
    var c = {touch:void 0 !== window.ontouchstart || window.DocumentTouch && document instanceof DocumentTouch}, d = {webkitTransition:{end:"webkitTransitionEnd", prefix:"-webkit-"}, MozTransition:{end:"transitionend", prefix:"-moz-"}, OTransition:{end:"otransitionend", prefix:"-o-"}, transition:{end:"transitionend", prefix:""}}, e = function() {
      var a, d, e = c.transition;
      document.body.appendChild(b), e && (a = e.name.slice(0, -9) + "ransform", void 0 !== b.style[a] && (b.style[a] = "translateZ(0)", d = window.getComputedStyle(b).getPropertyValue(e.prefix + "transform"), c.transform = {prefix:e.prefix, name:a, translate:!0, translateZ:!!d && "none" !== d})), void 0 !== b.style.backgroundSize && (c.backgroundSize = {}, b.style.backgroundSize = "contain", c.backgroundSize.contain = "contain" === window.getComputedStyle(b).getPropertyValue("background-size"), b.style.backgroundSize = 
      "cover", c.backgroundSize.cover = "cover" === window.getComputedStyle(b).getPropertyValue("background-size")), document.body.removeChild(b);
    };
    return function(a, c) {
      var d;
      for (d in c) {
        if (c.hasOwnProperty(d) && void 0 !== b.style[d]) {
          a.transition = c[d], a.transition.name = d;
          break;
        }
      }
    }(c, d), document.body ? e() : a(document).on("DOMContentLoaded", e), c;
  }(document.createElement("div")), requestAnimationFrame:window.requestAnimationFrame || (window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame), initialize:function() {
    return this.initStartIndex(), this.initWidget() === !1 ? !1 : (this.initEventListeners(), this.onslide(this.index), this.ontransitionend(), void(this.options.startSlideshow && this.play()));
  }, slide:function(a, b) {
    window.clearTimeout(this.timeout);
    var c, d, e, f = this.index;
    if (f !== a && 1 !== this.num) {
      if (b || (b = this.options.transitionSpeed), this.support.transition) {
        for (this.options.continuous || (a = this.circle(a)), c = Math.abs(f - a) / (f - a), this.options.continuous && (d = c, c = -this.positions[this.circle(a)] / this.slideWidth, c !== d && (a = -c * this.num + a)), e = Math.abs(f - a) - 1;e;) {
          e -= 1, this.move(this.circle((a > f ? a : f) - e - 1), this.slideWidth * c, 0);
        }
        a = this.circle(a), this.move(f, this.slideWidth * c, b), this.move(a, 0, b), this.options.continuous && this.move(this.circle(a - c), -(this.slideWidth * c), 0);
      } else {
        a = this.circle(a), this.animate(f * -this.slideWidth, a * -this.slideWidth, b);
      }
      this.onslide(a);
    }
  }, getIndex:function() {
    return this.index;
  }, getNumber:function() {
    return this.num;
  }, prev:function() {
    (this.options.continuous || this.index) && this.slide(this.index - 1);
  }, next:function() {
    (this.options.continuous || this.index < this.num - 1) && this.slide(this.index + 1);
  }, play:function(a) {
    var b = this;
    window.clearTimeout(this.timeout), this.interval = a || this.options.slideshowInterval, this.elements[this.index] > 1 && (this.timeout = this.setTimeout(!this.requestAnimationFrame && this.slide || function(a, c) {
      b.animationFrameId = b.requestAnimationFrame.call(window, function() {
        b.slide(a, c);
      });
    }, [this.index + 1, this.options.slideshowTransitionSpeed], this.interval)), this.container.addClass(this.options.playingClass);
  }, pause:function() {
    window.clearTimeout(this.timeout), this.interval = null, this.container.removeClass(this.options.playingClass);
  }, add:function(a) {
    var b;
    for (a.concat || (a = Array.prototype.slice.call(a)), this.list.concat || (this.list = Array.prototype.slice.call(this.list)), this.list = this.list.concat(a), this.num = this.list.length, this.num > 2 && (null === this.options.continuous && (this.options.continuous = !0, this.container.removeClass(this.options.leftEdgeClass))), this.container.removeClass(this.options.rightEdgeClass).removeClass(this.options.singleClass), b = this.num - a.length;b < this.num;b += 1) {
      this.addSlide(b), this.positionSlide(b);
    }
    this.positions.length = this.num, this.initSlides(!0);
  }, resetSlides:function() {
    this.slidesContainer.empty(), this.slides = [];
  }, handleClose:function() {
    var a = this.options;
    this.destroyEventListeners(), this.pause(), this.container[0].style.display = "none", this.container.removeClass(a.displayClass).removeClass(a.singleClass).removeClass(a.leftEdgeClass).removeClass(a.rightEdgeClass), a.hidePageScrollbars && (document.body.style.overflow = this.bodyOverflowStyle), this.options.clearSlides && this.resetSlides(), this.options.onclosed && this.options.onclosed.call(this);
  }, close:function() {
    var a = this, b = function(c) {
      c.target === a.container[0] && (a.container.off(a.support.transition.end, b), a.handleClose());
    };
    this.options.onclose && this.options.onclose.call(this), this.support.transition && this.options.displayTransition ? (this.container.on(this.support.transition.end, b), this.container.removeClass(this.options.displayClass)) : this.handleClose();
  }, circle:function(a) {
    return(this.num + a % this.num) % this.num;
  }, move:function(a, b, c) {
    this.translateX(a, b, c), this.positions[a] = b;
  }, translate:function(a, b, c, d) {
    var e = this.slides[a].style, f = this.support.transition, g = this.support.transform;
    e[f.name + "Duration"] = d + "ms", e[g.name] = "translate(" + b + "px, " + c + "px)" + (g.translateZ ? " translateZ(0)" : "");
  }, translateX:function(a, b, c) {
    this.translate(a, b, 0, c);
  }, translateY:function(a, b, c) {
    this.translate(a, 0, b, c);
  }, animate:function(a, b, c) {
    if (!c) {
      return void(this.slidesContainer[0].style.left = b + "px");
    }
    var d = this, e = (new Date).getTime(), f = window.setInterval(function() {
      var g = (new Date).getTime() - e;
      return g > c ? (d.slidesContainer[0].style.left = b + "px", d.ontransitionend(), void window.clearInterval(f)) : void(d.slidesContainer[0].style.left = (b - a) * (Math.floor(g / c * 100) / 100) + a + "px");
    }, 4);
  }, preventDefault:function(a) {
    a.preventDefault ? a.preventDefault() : a.returnValue = !1;
  }, stopPropagation:function(a) {
    a.stopPropagation ? a.stopPropagation() : a.cancelBubble = !0;
  }, onresize:function() {
    this.initSlides(!0);
  }, onmousedown:function(a) {
    a.which && (1 === a.which && ("VIDEO" !== a.target.nodeName && (a.preventDefault(), (a.originalEvent || a).touches = [{pageX:a.pageX, pageY:a.pageY}], this.ontouchstart(a))));
  }, onmousemove:function(a) {
    this.touchStart && ((a.originalEvent || a).touches = [{pageX:a.pageX, pageY:a.pageY}], this.ontouchmove(a));
  }, onmouseup:function(a) {
    this.touchStart && (this.ontouchend(a), delete this.touchStart);
  }, onmouseout:function(b) {
    if (this.touchStart) {
      var c = b.target, d = b.relatedTarget;
      (!d || d !== c && !a.contains(c, d)) && this.onmouseup(b);
    }
  }, ontouchstart:function(a) {
    this.options.stopTouchEventsPropagation && this.stopPropagation(a);
    var b = (a.originalEvent || a).touches[0];
    this.touchStart = {x:b.pageX, y:b.pageY, time:Date.now()}, this.isScrolling = void 0, this.touchDelta = {};
  }, ontouchmove:function(a) {
    this.options.stopTouchEventsPropagation && this.stopPropagation(a);
    var b, c, d = (a.originalEvent || a).touches[0], e = (a.originalEvent || a).scale, f = this.index;
    if (!(d.length > 1 || e && 1 !== e)) {
      if (this.options.disableScroll && a.preventDefault(), this.touchDelta = {x:d.pageX - this.touchStart.x, y:d.pageY - this.touchStart.y}, b = this.touchDelta.x, void 0 === this.isScrolling && (this.isScrolling = this.isScrolling || Math.abs(b) < Math.abs(this.touchDelta.y)), this.isScrolling) {
        this.options.closeOnSwipeUpOrDown && this.translateY(f, this.touchDelta.y + this.positions[f], 0);
      } else {
        for (a.preventDefault(), window.clearTimeout(this.timeout), this.options.continuous ? c = [this.circle(f + 1), f, this.circle(f - 1)] : (this.touchDelta.x = b /= !f && b > 0 || f === this.num - 1 && 0 > b ? Math.abs(b) / this.slideWidth + 1 : 1, c = [f], f && c.push(f - 1), f < this.num - 1 && c.unshift(f + 1));c.length;) {
          f = c.pop(), this.translateX(f, b + this.positions[f], 0);
        }
      }
    }
  }, ontouchend:function(a) {
    this.options.stopTouchEventsPropagation && this.stopPropagation(a);
    var b, c, d, e, f, g = this.index, h = this.options.transitionSpeed, i = this.slideWidth, j = Number(Date.now() - this.touchStart.time) < 250, k = j && Math.abs(this.touchDelta.x) > 20 || Math.abs(this.touchDelta.x) > i / 2, l = !g && this.touchDelta.x > 0 || g === this.num - 1 && this.touchDelta.x < 0, m = !k && (this.options.closeOnSwipeUpOrDown && (j && Math.abs(this.touchDelta.y) > 20 || Math.abs(this.touchDelta.y) > this.slideHeight / 2));
    this.options.continuous && (l = !1), b = this.touchDelta.x < 0 ? -1 : 1, this.isScrolling ? m ? this.close() : this.translateY(g, 0, h) : k && !l ? (c = g + b, d = g - b, e = i * b, f = -i * b, this.options.continuous ? (this.move(this.circle(c), e, 0), this.move(this.circle(g - 2 * b), f, 0)) : c >= 0 && (c < this.num && this.move(c, e, 0)), this.move(g, this.positions[g] + e, h), this.move(this.circle(d), this.positions[this.circle(d)] + e, h), g = this.circle(d), this.onslide(g)) : this.options.continuous ? 
    (this.move(this.circle(g - 1), -i, h), this.move(g, 0, h), this.move(this.circle(g + 1), i, h)) : (g && this.move(g - 1, -i, h), this.move(g, 0, h), g < this.num - 1 && this.move(g + 1, i, h));
  }, ontouchcancel:function(a) {
    this.touchStart && (this.ontouchend(a), delete this.touchStart);
  }, ontransitionend:function(a) {
    var b = this.slides[this.index];
    a && b !== a.target || (this.interval && this.play(), this.setTimeout(this.options.onslideend, [this.index, b]));
  }, oncomplete:function(b) {
    var c, d = b.target || b.srcElement, e = d && d.parentNode;
    d && (e && (c = this.getNodeIndex(e), a(e).removeClass(this.options.slideLoadingClass), "error" === b.type ? (a(e).addClass(this.options.slideErrorClass), this.elements[c] = 3) : this.elements[c] = 2, d.clientHeight > this.container[0].clientHeight && (d.style.maxHeight = this.container[0].clientHeight), this.interval && (this.slides[this.index] === e && this.play()), this.setTimeout(this.options.onslidecomplete, [c, e])));
  }, onload:function(a) {
    this.oncomplete(a);
  }, onerror:function(a) {
    this.oncomplete(a);
  }, onkeydown:function(a) {
    switch(a.which || a.keyCode) {
      case 13:
        this.options.toggleControlsOnReturn && (this.preventDefault(a), this.toggleControls());
        break;
      case 27:
        this.options.closeOnEscape && this.close();
        break;
      case 32:
        this.options.toggleSlideshowOnSpace && (this.preventDefault(a), this.toggleSlideshow());
        break;
      case 37:
        this.options.enableKeyboardNavigation && (this.preventDefault(a), this.prev());
        break;
      case 39:
        this.options.enableKeyboardNavigation && (this.preventDefault(a), this.next());
    }
  }, handleClick:function(b) {
    var c = this.options, d = b.target || b.srcElement, e = d.parentNode, f = function(b) {
      return a(d).hasClass(b) || a(e).hasClass(b);
    };
    f(c.toggleClass) ? (this.preventDefault(b), this.toggleControls()) : f(c.prevClass) ? (this.preventDefault(b), this.prev()) : f(c.nextClass) ? (this.preventDefault(b), this.next()) : f(c.closeClass) ? (this.preventDefault(b), this.close()) : f(c.playPauseClass) ? (this.preventDefault(b), this.toggleSlideshow()) : e === this.slidesContainer[0] ? (this.preventDefault(b), c.closeOnSlideClick ? this.close() : this.toggleControls()) : e.parentNode && (e.parentNode === this.slidesContainer[0] && (this.preventDefault(b), 
    this.toggleControls()));
  }, onclick:function(a) {
    return this.options.emulateTouchEvents && (this.touchDelta && (Math.abs(this.touchDelta.x) > 20 || Math.abs(this.touchDelta.y) > 20)) ? void delete this.touchDelta : this.handleClick(a);
  }, updateEdgeClasses:function(a) {
    a ? this.container.removeClass(this.options.leftEdgeClass) : this.container.addClass(this.options.leftEdgeClass), a === this.num - 1 ? this.container.addClass(this.options.rightEdgeClass) : this.container.removeClass(this.options.rightEdgeClass);
  }, handleSlide:function(a) {
    this.options.continuous || this.updateEdgeClasses(a), this.loadElements(a), this.options.unloadElements && this.unloadElements(a), this.setTitle(a);
  }, onslide:function(a) {
    this.index = a, this.handleSlide(a), this.setTimeout(this.options.onslide, [a, this.slides[a]]);
  }, setTitle:function(a) {
    var b = this.slides[a].firstChild.title, c = this.titleElement;
    c.length && (this.titleElement.empty(), b && c[0].appendChild(document.createTextNode(b)));
  }, setTimeout:function(a, b, c) {
    var d = this;
    return a && window.setTimeout(function() {
      a.apply(d, b || []);
    }, c || 0);
  }, imageFactory:function(b, c) {
    var d, e, f, g = this, h = this.imagePrototype.cloneNode(!1), i = b, j = this.options.stretchImages, k = function(b) {
      if (!d) {
        if (b = {type:b.type, target:e}, !e.parentNode) {
          return g.setTimeout(k, [b]);
        }
        d = !0, a(h).off("load error", k), j && ("load" === b.type && (e.style.background = 'url("' + i + '") center no-repeat', e.style.backgroundSize = j)), c(b);
      }
    };
    return "string" != typeof i && (i = this.getItemProperty(b, this.options.urlProperty), f = this.getItemProperty(b, this.options.titleProperty)), j === !0 && (j = "contain"), j = this.support.backgroundSize && (this.support.backgroundSize[j] && j), j ? e = this.elementPrototype.cloneNode(!1) : (e = h, h.draggable = !1), f && (e.title = f), a(h).on("load error", k), h.src = i, e;
  }, createElement:function(b, c) {
    var d = b && this.getItemProperty(b, this.options.typeProperty), e = d && this[d.split("/")[0] + "Factory"] || this.imageFactory, f = b && e.call(this, b, c);
    return f || (f = this.elementPrototype.cloneNode(!1), this.setTimeout(c, [{type:"error", target:f}])), a(f).addClass(this.options.slideContentClass), f;
  }, loadElement:function(b) {
    this.elements[b] || (this.slides[b].firstChild ? this.elements[b] = a(this.slides[b]).hasClass(this.options.slideErrorClass) ? 3 : 2 : (this.elements[b] = 1, a(this.slides[b]).addClass(this.options.slideLoadingClass), this.slides[b].appendChild(this.createElement(this.list[b], this.proxyListener))));
  }, loadElements:function(a) {
    var b, c = Math.min(this.num, 2 * this.options.preloadRange + 1), d = a;
    for (b = 0;c > b;b += 1) {
      d += b * (b % 2 === 0 ? -1 : 1), d = this.circle(d), this.loadElement(d);
    }
  }, unloadElements:function(a) {
    var b, c, d;
    for (b in this.elements) {
      this.elements.hasOwnProperty(b) && (d = Math.abs(a - b), d > this.options.preloadRange && (d + this.options.preloadRange < this.num && (c = this.slides[b], c.removeChild(c.firstChild), delete this.elements[b])));
    }
  }, addSlide:function(a) {
    var b = this.slidePrototype.cloneNode(!1);
    b.setAttribute("data-index", a), this.slidesContainer[0].appendChild(b), this.slides.push(b);
  }, positionSlide:function(a) {
    var b = this.slides[a];
    b.style.width = this.slideWidth + "px", this.support.transition && (b.style.left = a * -this.slideWidth + "px", this.move(a, this.index > a ? -this.slideWidth : this.index < a ? this.slideWidth : 0, 0));
  }, initSlides:function(b) {
    var c, d;
    for (b || (this.positions = [], this.positions.length = this.num, this.elements = {}, this.imagePrototype = document.createElement("img"), this.elementPrototype = document.createElement("div"), this.slidePrototype = document.createElement("div"), a(this.slidePrototype).addClass(this.options.slideClass), this.slides = this.slidesContainer[0].children, c = this.options.clearSlides || this.slides.length !== this.num), this.slideWidth = this.container[0].offsetWidth, this.slideHeight = this.container[0].offsetHeight, 
    this.slidesContainer[0].style.width = this.num * this.slideWidth + "px", c && this.resetSlides(), d = 0;d < this.num;d += 1) {
      c && this.addSlide(d), this.positionSlide(d);
    }
    this.options.continuous && (this.support.transition && (this.move(this.circle(this.index - 1), -this.slideWidth, 0), this.move(this.circle(this.index + 1), this.slideWidth, 0))), this.support.transition || (this.slidesContainer[0].style.left = this.index * -this.slideWidth + "px");
  }, toggleControls:function() {
    var a = this.options.controlsClass;
    this.container.hasClass(a) ? this.container.removeClass(a) : this.container.addClass(a);
  }, toggleSlideshow:function() {
    this.interval ? this.pause() : this.play();
  }, getNodeIndex:function(a) {
    return parseInt(a.getAttribute("data-index"), 10);
  }, getNestedProperty:function(a, b) {
    return b.replace(/\[(?:'([^']+)'|"([^"]+)"|(\d+))\]|(?:(?:^|\.)([^\.\[]+))/g, function(b, c, d, e, f) {
      var g = f || (c || (d || e && parseInt(e, 10)));
      b && (a && (a = a[g]));
    }), a;
  }, getDataProperty:function(b, c) {
    if (b.getAttribute) {
      var d = b.getAttribute("data-" + c.replace(/([A-Z])/g, "-$1").toLowerCase());
      if ("string" == typeof d) {
        if (/^(true|false|null|-?\d+(\.\d+)?|\{[\s\S]*\}|\[[\s\S]*\])$/.test(d)) {
          try {
            return a.parseJSON(d);
          } catch (e) {
          }
        }
        return d;
      }
    }
  }, getItemProperty:function(a, b) {
    var c = a[b];
    return void 0 === c && (c = this.getDataProperty(a, b), void 0 === c && (c = this.getNestedProperty(a, b))), c;
  }, initStartIndex:function() {
    var a, b = this.options.index, c = this.options.urlProperty;
    if (b && "number" != typeof b) {
      for (a = 0;a < this.num;a += 1) {
        if (this.list[a] === b || this.getItemProperty(this.list[a], c) === this.getItemProperty(b, c)) {
          b = a;
          break;
        }
      }
    }
    this.index = this.circle(parseInt(b, 10) || 0);
  }, initEventListeners:function() {
    var b = this, c = this.slidesContainer, d = function(a) {
      var c = b.support.transition && b.support.transition.end === a.type ? "transitionend" : a.type;
      b["on" + c](a);
    };
    a(window).on("resize", d), a(document.body).on("keydown", d), this.container.on("click", d), this.support.touch ? c.on("touchstart touchmove touchend touchcancel", d) : this.options.emulateTouchEvents && (this.support.transition && c.on("mousedown mousemove mouseup mouseout", d)), this.support.transition && c.on(this.support.transition.end, d), this.proxyListener = d;
  }, destroyEventListeners:function() {
    var b = this.slidesContainer, c = this.proxyListener;
    a(window).off("resize", c), a(document.body).off("keydown", c), this.container.off("click", c), this.support.touch ? b.off("touchstart touchmove touchend touchcancel", c) : this.options.emulateTouchEvents && (this.support.transition && b.off("mousedown mousemove mouseup mouseout", c)), this.support.transition && b.off(this.support.transition.end, c);
  }, handleOpen:function() {
    this.options.onopened && this.options.onopened.call(this);
  }, initWidget:function() {
    var b = this, c = function(a) {
      a.target === b.container[0] && (b.container.off(b.support.transition.end, c), b.handleOpen());
    };
    return this.container = a(this.options.container), this.container.length ? (this.slidesContainer = this.container.find(this.options.slidesContainer).first(), this.slidesContainer.length ? (this.titleElement = this.container.find(this.options.titleElement).first(), 1 === this.num && this.container.addClass(this.options.singleClass), this.options.onopen && this.options.onopen.call(this), this.support.transition && this.options.displayTransition ? this.container.on(this.support.transition.end, c) : 
    this.handleOpen(), this.options.hidePageScrollbars && (this.bodyOverflowStyle = document.body.style.overflow, document.body.style.overflow = "hidden"), this.container[0].style.display = "block", this.initSlides(), void this.container.addClass(this.options.displayClass)) : (this.console.log("blueimp Gallery: Slides container not found.", this.options.slidesContainer), !1)) : (this.console.log("blueimp Gallery: Widget container not found.", this.options.container), !1);
  }, initOptions:function(b) {
    this.options = a.extend({}, this.options), (b && b.carousel || this.options.carousel && (!b || b.carousel !== !1)) && a.extend(this.options, this.carouselOptions), a.extend(this.options, b), this.num < 3 && (this.options.continuous = this.options.continuous ? null : !1), this.support.transition || (this.options.emulateTouchEvents = !1), this.options.event && this.preventDefault(this.options.event);
  }}), b;
}), function(a) {
  "function" == typeof define && define.amd ? define(["./blueimp-helper", "./blueimp-gallery"], a) : a(window.blueimp.helper || window.jQuery, window.blueimp.Gallery);
}(function(a, b) {
  a.extend(b.prototype.options, {fullScreen:!1});
  var c = b.prototype.initialize, d = b.prototype.close;
  return a.extend(b.prototype, {getFullScreenElement:function() {
    return document.fullscreenElement || (document.webkitFullscreenElement || (document.mozFullScreenElement || document.msFullscreenElement));
  }, requestFullScreen:function(a) {
    a.requestFullscreen ? a.requestFullscreen() : a.webkitRequestFullscreen ? a.webkitRequestFullscreen() : a.mozRequestFullScreen ? a.mozRequestFullScreen() : a.msRequestFullscreen && a.msRequestFullscreen();
  }, exitFullScreen:function() {
    document.exitFullscreen ? document.exitFullscreen() : document.webkitCancelFullScreen ? document.webkitCancelFullScreen() : document.mozCancelFullScreen ? document.mozCancelFullScreen() : document.msExitFullscreen && document.msExitFullscreen();
  }, initialize:function() {
    c.call(this), this.options.fullScreen && (!this.getFullScreenElement() && this.requestFullScreen(this.container[0]));
  }, close:function() {
    this.getFullScreenElement() === this.container[0] && this.exitFullScreen(), d.call(this);
  }}), b;
}), function(a) {
  "function" == typeof define && define.amd ? define(["./blueimp-helper", "./blueimp-gallery"], a) : a(window.blueimp.helper || window.jQuery, window.blueimp.Gallery);
}(function(a, b) {
  a.extend(b.prototype.options, {indicatorContainer:"ol", activeIndicatorClass:"active", thumbnailProperty:"thumbnail", thumbnailIndicators:!0});
  var c = b.prototype.initSlides, d = b.prototype.addSlide, e = b.prototype.resetSlides, f = b.prototype.handleClick, g = b.prototype.handleSlide, h = b.prototype.handleClose;
  return a.extend(b.prototype, {createIndicator:function(b) {
    var c, d, e = this.indicatorPrototype.cloneNode(!1), f = this.getItemProperty(b, this.options.titleProperty), g = this.options.thumbnailProperty;
    return this.options.thumbnailIndicators && (d = b.getElementsByTagName && a(b).find("img")[0], d ? c = d.src : g && (c = this.getItemProperty(b, g)), c && (e.style.backgroundImage = 'url("' + c + '")')), f && (e.title = f), e;
  }, addIndicator:function(a) {
    if (this.indicatorContainer.length) {
      var b = this.createIndicator(this.list[a]);
      b.setAttribute("data-index", a), this.indicatorContainer[0].appendChild(b), this.indicators.push(b);
    }
  }, setActiveIndicator:function(b) {
    this.indicators && (this.activeIndicator && this.activeIndicator.removeClass(this.options.activeIndicatorClass), this.activeIndicator = a(this.indicators[b]), this.activeIndicator.addClass(this.options.activeIndicatorClass));
  }, initSlides:function(a) {
    a || (this.indicatorContainer = this.container.find(this.options.indicatorContainer), this.indicatorContainer.length && (this.indicatorPrototype = document.createElement("li"), this.indicators = this.indicatorContainer[0].children)), c.call(this, a);
  }, addSlide:function(a) {
    d.call(this, a), this.addIndicator(a);
  }, resetSlides:function() {
    e.call(this), this.indicatorContainer.empty(), this.indicators = [];
  }, handleClick:function(a) {
    var b = a.target || a.srcElement, c = b.parentNode;
    if (c === this.indicatorContainer[0]) {
      this.preventDefault(a), this.slide(this.getNodeIndex(b));
    } else {
      if (c.parentNode !== this.indicatorContainer[0]) {
        return f.call(this, a);
      }
      this.preventDefault(a), this.slide(this.getNodeIndex(c));
    }
  }, handleSlide:function(a) {
    g.call(this, a), this.setActiveIndicator(a);
  }, handleClose:function() {
    this.activeIndicator && this.activeIndicator.removeClass(this.options.activeIndicatorClass), h.call(this);
  }}), b;
}), function(a) {
  "function" == typeof define && define.amd ? define(["./blueimp-helper", "./blueimp-gallery"], a) : a(window.blueimp.helper || window.jQuery, window.blueimp.Gallery);
}(function(a, b) {
  a.extend(b.prototype.options, {videoContentClass:"video-content", videoLoadingClass:"video-loading", videoPlayingClass:"video-playing", videoPosterProperty:"poster", videoSourcesProperty:"sources"});
  var c = b.prototype.handleSlide;
  return a.extend(b.prototype, {handleSlide:function(a) {
    c.call(this, a), this.playingVideo && this.playingVideo.pause();
  }, videoFactory:function(b, c, d) {
    var e, f, g, h, i, j = this, k = this.options, l = this.elementPrototype.cloneNode(!1), m = a(l), n = [{type:"error", target:l}], o = d || document.createElement("video"), p = this.getItemProperty(b, k.urlProperty), q = this.getItemProperty(b, k.typeProperty), r = this.getItemProperty(b, k.titleProperty), s = this.getItemProperty(b, k.videoPosterProperty), t = this.getItemProperty(b, k.videoSourcesProperty);
    if (m.addClass(k.videoContentClass), r && (l.title = r), o.canPlayType) {
      if (p && (q && o.canPlayType(q))) {
        o.src = p;
      } else {
        for (;t && t.length;) {
          if (f = t.shift(), p = this.getItemProperty(f, k.urlProperty), q = this.getItemProperty(f, k.typeProperty), p && (q && o.canPlayType(q))) {
            o.src = p;
            break;
          }
        }
      }
    }
    return s && (o.poster = s, e = this.imagePrototype.cloneNode(!1), a(e).addClass(k.toggleClass), e.src = s, e.draggable = !1, l.appendChild(e)), g = document.createElement("a"), g.setAttribute("target", "_blank"), d || g.setAttribute("download", r), g.href = p, o.src && (o.controls = !0, (d || a(o)).on("error", function() {
      j.setTimeout(c, n);
    }).on("pause", function() {
      h = !1, m.removeClass(j.options.videoLoadingClass).removeClass(j.options.videoPlayingClass), i && j.container.addClass(j.options.controlsClass), delete j.playingVideo, j.interval && j.play();
    }).on("playing", function() {
      h = !1, m.removeClass(j.options.videoLoadingClass).addClass(j.options.videoPlayingClass), j.container.hasClass(j.options.controlsClass) ? (i = !0, j.container.removeClass(j.options.controlsClass)) : i = !1;
    }).on("play", function() {
      window.clearTimeout(j.timeout), h = !0, m.addClass(j.options.videoLoadingClass), j.playingVideo = o;
    }), a(g).on("click", function(a) {
      j.preventDefault(a), h ? o.pause() : o.play();
    }), l.appendChild(d && d.element || o)), l.appendChild(g), this.setTimeout(c, [{type:"load", target:l}]), l;
  }}), b;
}), function(a) {
  "function" == typeof define && define.amd ? define(["./blueimp-helper", "./blueimp-gallery-video"], a) : a(window.blueimp.helper || window.jQuery, window.blueimp.Gallery);
}(function(a, b) {
  if (!window.postMessage) {
    return b;
  }
  a.extend(b.prototype.options, {vimeoVideoIdProperty:"vimeo", vimeoPlayerUrl:"//player.vimeo.com/video/VIDEO_ID?api=1&player_id=PLAYER_ID", vimeoPlayerIdPrefix:"vimeo-player-", vimeoClickToPlay:!0});
  var c = b.prototype.textFactory || b.prototype.imageFactory, d = function(a, b, c, d) {
    this.url = a, this.videoId = b, this.playerId = c, this.clickToPlay = d, this.element = document.createElement("div"), this.listeners = {};
  }, e = 0;
  return a.extend(d.prototype, {canPlayType:function() {
    return!0;
  }, on:function(a, b) {
    return this.listeners[a] = b, this;
  }, loadAPI:function() {
    for (var b, c, d = this, e = "//" + ("https" === location.protocol ? "secure-" : "") + "a.vimeocdn.com/js/froogaloop2.min.js", f = document.getElementsByTagName("script"), g = f.length, h = function() {
      !c && (d.playOnReady && d.play()), c = !0;
    };g;) {
      if (g -= 1, f[g].src === e) {
        b = f[g];
        break;
      }
    }
    b || (b = document.createElement("script"), b.src = e), a(b).on("load", h), f[0].parentNode.insertBefore(b, f[0]), /loaded|complete/.test(b.readyState) && h();
  }, onReady:function() {
    var a = this;
    this.ready = !0, this.player.addEvent("play", function() {
      a.hasPlayed = !0, a.onPlaying();
    }), this.player.addEvent("pause", function() {
      a.onPause();
    }), this.player.addEvent("finish", function() {
      a.onPause();
    }), this.playOnReady && this.play();
  }, onPlaying:function() {
    this.playStatus < 2 && (this.listeners.playing(), this.playStatus = 2);
  }, onPause:function() {
    this.listeners.pause(), delete this.playStatus;
  }, insertIframe:function() {
    var a = document.createElement("iframe");
    a.src = this.url.replace("VIDEO_ID", this.videoId).replace("PLAYER_ID", this.playerId), a.id = this.playerId, this.element.parentNode.replaceChild(a, this.element), this.element = a;
  }, play:function() {
    var a = this;
    this.playStatus || (this.listeners.play(), this.playStatus = 1), this.ready ? !this.hasPlayed && (this.clickToPlay || window.navigator && /iP(hone|od|ad)/.test(window.navigator.platform)) ? this.onPlaying() : this.player.api("play") : (this.playOnReady = !0, window.$f ? this.player || (this.insertIframe(), this.player = $f(this.element), this.player.addEvent("ready", function() {
      a.onReady();
    })) : this.loadAPI());
  }, pause:function() {
    this.ready ? this.player.api("pause") : this.playStatus && (delete this.playOnReady, this.listeners.pause(), delete this.playStatus);
  }}), a.extend(b.prototype, {VimeoPlayer:d, textFactory:function(a, b) {
    var f = this.getItemProperty(a, this.options.vimeoVideoIdProperty);
    return f ? (e += 1, this.videoFactory(a, b, new d(this.options.vimeoPlayerUrl, f, this.options.vimeoPlayerIdPrefix + e, this.options.vimeoClickToPlay))) : c.call(this, a, b);
  }}), b;
}), function(a) {
  "function" == typeof define && define.amd ? define(["./blueimp-helper", "./blueimp-gallery-video"], a) : a(window.blueimp.helper || window.jQuery, window.blueimp.Gallery);
}(function(a, b) {
  if (!window.postMessage) {
    return b;
  }
  a.extend(b.prototype.options, {youTubeVideoIdProperty:"youtube", youTubePlayerVars:{wmode:"transparent"}, youTubeClickToPlay:!0});
  var c = b.prototype.textFactory || b.prototype.imageFactory, d = function(a, b, c) {
    this.videoId = a, this.playerVars = b, this.clickToPlay = c, this.element = document.createElement("div"), this.listeners = {};
  };
  return a.extend(d.prototype, {canPlayType:function() {
    return!0;
  }, on:function(a, b) {
    return this.listeners[a] = b, this;
  }, loadAPI:function() {
    var a, b = this, c = window.onYouTubeIframeAPIReady, d = "//www.youtube.com/iframe_api", e = document.getElementsByTagName("script"), f = e.length;
    for (window.onYouTubeIframeAPIReady = function() {
      c && c.apply(this), b.playOnReady && b.play();
    };f;) {
      if (f -= 1, e[f].src === d) {
        return;
      }
    }
    a = document.createElement("script"), a.src = d, e[0].parentNode.insertBefore(a, e[0]);
  }, onReady:function() {
    this.ready = !0, this.playOnReady && this.play();
  }, onPlaying:function() {
    this.playStatus < 2 && (this.listeners.playing(), this.playStatus = 2);
  }, onPause:function() {
    b.prototype.setTimeout.call(this, this.checkSeek, null, 2E3);
  }, checkSeek:function() {
    (this.stateChange === YT.PlayerState.PAUSED || this.stateChange === YT.PlayerState.ENDED) && (this.listeners.pause(), delete this.playStatus);
  }, onStateChange:function(a) {
    switch(a.data) {
      case YT.PlayerState.PLAYING:
        this.hasPlayed = !0, this.onPlaying();
        break;
      case YT.PlayerState.PAUSED:
      ;
      case YT.PlayerState.ENDED:
        this.onPause();
    }
    this.stateChange = a.data;
  }, onError:function(a) {
    this.listeners.error(a);
  }, play:function() {
    var a = this;
    this.playStatus || (this.listeners.play(), this.playStatus = 1), this.ready ? !this.hasPlayed && (this.clickToPlay || window.navigator && /iP(hone|od|ad)/.test(window.navigator.platform)) ? this.onPlaying() : this.player.playVideo() : (this.playOnReady = !0, window.YT && YT.Player ? this.player || (this.player = new YT.Player(this.element, {videoId:this.videoId, playerVars:this.playerVars, events:{onReady:function() {
      a.onReady();
    }, onStateChange:function(b) {
      a.onStateChange(b);
    }, onError:function(b) {
      a.onError(b);
    }}})) : this.loadAPI());
  }, pause:function() {
    this.ready ? this.player.pauseVideo() : this.playStatus && (delete this.playOnReady, this.listeners.pause(), delete this.playStatus);
  }}), a.extend(b.prototype, {YouTubePlayer:d, textFactory:function(a, b) {
    var e = this.getItemProperty(a, this.options.youTubeVideoIdProperty);
    return e ? this.videoFactory(a, b, new d(e, this.options.youTubePlayerVars, this.options.youTubeClickToPlay)) : c.call(this, a, b);
  }}), b;
}), function(a) {
  "function" == typeof define && define.amd ? define(["jquery", "./blueimp-gallery"], a) : a(window.jQuery, window.blueimp.Gallery);
}(function(a, b) {
  a(document).on("click", "[data-gallery]", function(c) {
    var d = a(this).data("gallery"), e = a(d), f = e.length && e || a(b.prototype.options.container), g = {onopen:function() {
      f.data("gallery", this).trigger("open");
    }, onopened:function() {
      f.trigger("opened");
    }, onslide:function() {
      f.trigger("slide", arguments);
    }, onslideend:function() {
      f.trigger("slideend", arguments);
    }, onslidecomplete:function() {
      f.trigger("slidecomplete", arguments);
    }, onclose:function() {
      f.trigger("close");
    }, onclosed:function() {
      f.trigger("closed").removeData("gallery");
    }}, h = a.extend(f.data(), {container:f[0], index:this, event:c}, g), i = a('[data-gallery="' + d + '"]');
    return h.filter && (i = i.filter(h.filter)), new b(i, h);
  });
});
(function(factory) {
  if (typeof define === "function" && define.amd) {
    define(["jquery"], factory);
  } else {
    factory(window.jQuery);
  }
})(function($) {
  var counter = 0;
  $.ajaxTransport("iframe", function(options) {
    if (options.async) {
      var initialIframeSrc = options.initialIframeSrc || "javascript:false;", form, iframe, addParamChar;
      return{send:function(_, completeCallback) {
        form = $('<form style="display:none;"></form>');
        form.attr("accept-charset", options.formAcceptCharset);
        addParamChar = /\?/.test(options.url) ? "&" : "?";
        if (options.type === "DELETE") {
          options.url = options.url + addParamChar + "_method=DELETE";
          options.type = "POST";
        } else {
          if (options.type === "PUT") {
            options.url = options.url + addParamChar + "_method=PUT";
            options.type = "POST";
          } else {
            if (options.type === "PATCH") {
              options.url = options.url + addParamChar + "_method=PATCH";
              options.type = "POST";
            }
          }
        }
        counter += 1;
        iframe = $('<iframe src="' + initialIframeSrc + '" name="iframe-transport-' + counter + '"></iframe>').bind("load", function() {
          var fileInputClones, paramNames = $.isArray(options.paramName) ? options.paramName : [options.paramName];
          iframe.unbind("load").bind("load", function() {
            var response;
            try {
              response = iframe.contents();
              if (!response.length || !response[0].firstChild) {
                throw new Error;
              }
            } catch (e) {
              response = undefined;
            }
            completeCallback(200, "success", {"iframe":response});
            $('<iframe src="' + initialIframeSrc + '"></iframe>').appendTo(form);
            window.setTimeout(function() {
              form.remove();
            }, 0);
          });
          form.prop("target", iframe.prop("name")).prop("action", options.url).prop("method", options.type);
          if (options.formData) {
            $.each(options.formData, function(index, field) {
              $('<input type="hidden"/>').prop("name", field.name).val(field.value).appendTo(form);
            });
          }
          if (options.fileInput && (options.fileInput.length && options.type === "POST")) {
            fileInputClones = options.fileInput.clone();
            options.fileInput.after(function(index) {
              return fileInputClones[index];
            });
            if (options.paramName) {
              options.fileInput.each(function(index) {
                $(this).prop("name", paramNames[index] || options.paramName);
              });
            }
            form.append(options.fileInput).prop("enctype", "multipart/form-data").prop("encoding", "multipart/form-data");
            options.fileInput.removeAttr("form");
          }
          form.submit();
          if (fileInputClones && fileInputClones.length) {
            options.fileInput.each(function(index, input) {
              var clone = $(fileInputClones[index]);
              $(input).prop("name", clone.prop("name")).attr("form", clone.attr("form"));
              clone.replaceWith(input);
            });
          }
        });
        form.append(iframe).appendTo(document.body);
      }, abort:function() {
        if (iframe) {
          iframe.unbind("load").prop("src", initialIframeSrc);
        }
        if (form) {
          form.remove();
        }
      }};
    }
  });
  $.ajaxSetup({converters:{"iframe text":function(iframe) {
    return iframe && $(iframe[0].body).text();
  }, "iframe json":function(iframe) {
    return iframe && $.parseJSON($(iframe[0].body).text());
  }, "iframe html":function(iframe) {
    return iframe && $(iframe[0].body).html();
  }, "iframe xml":function(iframe) {
    var xmlDoc = iframe && iframe[0];
    return xmlDoc && $.isXMLDoc(xmlDoc) ? xmlDoc : $.parseXML(xmlDoc.XMLDocument && xmlDoc.XMLDocument.xml || $(xmlDoc.body).html());
  }, "iframe script":function(iframe) {
    return iframe && $.globalEval($(iframe[0].body).text());
  }}});
});
(function(factory) {
  if (typeof define === "function" && define.amd) {
    define(["jquery", "jquery.ui.widget"], factory);
  } else {
    factory(window.jQuery);
  }
})(function($) {
  $.support.fileInput = !((new RegExp("(Android (1\\.[0156]|2\\.[01]))" + "|(Windows Phone (OS 7|8\\.0))|(XBLWP)|(ZuneWP)|(WPDesktop)" + "|(w(eb)?OSBrowser)|(webOS)" + "|(Kindle/(1\\.0|2\\.[05]|3\\.0))")).test(window.navigator.userAgent) || $('<input type="file">').prop("disabled"));
  $.support.xhrFileUpload = !!(window.ProgressEvent && window.FileReader);
  $.support.xhrFormDataFileUpload = !!window.FormData;
  $.support.blobSlice = window.Blob && (Blob.prototype.slice || (Blob.prototype.webkitSlice || Blob.prototype.mozSlice));
  $.widget("blueimp.fileupload", {options:{dropZone:$(document), pasteZone:$(document), fileInput:undefined, replaceFileInput:true, paramName:undefined, singleFileUploads:true, limitMultiFileUploads:undefined, limitMultiFileUploadSize:undefined, limitMultiFileUploadSizeOverhead:512, sequentialUploads:false, limitConcurrentUploads:undefined, forceIframeTransport:false, redirect:undefined, redirectParamName:undefined, postMessage:undefined, multipart:true, maxChunkSize:undefined, uploadedBytes:undefined, 
  recalculateProgress:true, progressInterval:100, bitrateInterval:500, autoUpload:true, messages:{uploadedBytes:"\uc5c5\ub85c\ub4dc\ub41c \ud30c\uc77c\uc774 \uc81c\ud55c\ub41c \uc6a9\ub7c9\uc744 \ucd08\uacfc \ud569\ub2c8\ub2e4."}, i18n:function(message, context) {
    message = this.messages[message] || message.toString();
    if (context) {
      $.each(context, function(key, value) {
        message = message.replace("{" + key + "}", value);
      });
    }
    return message;
  }, formData:function(form) {
    return form.serializeArray();
  }, add:function(e, data) {
    if (e.isDefaultPrevented()) {
      return false;
    }
    if (data.autoUpload || data.autoUpload !== false && $(this).fileupload("option", "autoUpload")) {
      data.process().done(function() {
        data.submit();
      });
    }
  }, processData:false, contentType:false, cache:false}, _specialOptions:["fileInput", "dropZone", "pasteZone", "multipart", "forceIframeTransport"], _blobSlice:$.support.blobSlice && function() {
    var slice = this.slice || (this.webkitSlice || this.mozSlice);
    return slice.apply(this, arguments);
  }, _BitrateTimer:function() {
    this.timestamp = Date.now ? Date.now() : (new Date).getTime();
    this.loaded = 0;
    this.bitrate = 0;
    this.getBitrate = function(now, loaded, interval) {
      var timeDiff = now - this.timestamp;
      if (!this.bitrate || (!interval || timeDiff > interval)) {
        this.bitrate = (loaded - this.loaded) * (1E3 / timeDiff) * 8;
        this.loaded = loaded;
        this.timestamp = now;
      }
      return this.bitrate;
    };
  }, _isXHRUpload:function(options) {
    return!options.forceIframeTransport && (!options.multipart && $.support.xhrFileUpload || $.support.xhrFormDataFileUpload);
  }, _getFormData:function(options) {
    var formData;
    if ($.type(options.formData) === "function") {
      return options.formData(options.form);
    }
    if ($.isArray(options.formData)) {
      return options.formData;
    }
    if ($.type(options.formData) === "object") {
      formData = [];
      $.each(options.formData, function(name, value) {
        formData.push({name:name, value:value});
      });
      return formData;
    }
    return[];
  }, _getTotal:function(files) {
    var total = 0;
    $.each(files, function(index, file) {
      total += file.size || 1;
    });
    return total;
  }, _initProgressObject:function(obj) {
    var progress = {loaded:0, total:0, bitrate:0};
    if (obj._progress) {
      $.extend(obj._progress, progress);
    } else {
      obj._progress = progress;
    }
  }, _initResponseObject:function(obj) {
    var prop;
    if (obj._response) {
      for (prop in obj._response) {
        if (obj._response.hasOwnProperty(prop)) {
          delete obj._response[prop];
        }
      }
    } else {
      obj._response = {};
    }
  }, _onProgress:function(e, data) {
    if (e.lengthComputable) {
      var now = Date.now ? Date.now() : (new Date).getTime(), loaded;
      if (data._time && (data.progressInterval && (now - data._time < data.progressInterval && e.loaded !== e.total))) {
        return;
      }
      data._time = now;
      loaded = Math.floor(e.loaded / e.total * (data.chunkSize || data._progress.total)) + (data.uploadedBytes || 0);
      this._progress.loaded += loaded - data._progress.loaded;
      this._progress.bitrate = this._bitrateTimer.getBitrate(now, this._progress.loaded, data.bitrateInterval);
      data._progress.loaded = data.loaded = loaded;
      data._progress.bitrate = data.bitrate = data._bitrateTimer.getBitrate(now, loaded, data.bitrateInterval);
      this._trigger("progress", $.Event("progress", {delegatedEvent:e}), data);
      this._trigger("progressall", $.Event("progressall", {delegatedEvent:e}), this._progress);
    }
  }, _initProgressListener:function(options) {
    var that = this, xhr = options.xhr ? options.xhr() : $.ajaxSettings.xhr();
    if (xhr.upload) {
      $(xhr.upload).bind("progress", function(e) {
        var oe = e.originalEvent;
        e.lengthComputable = oe.lengthComputable;
        e.loaded = oe.loaded;
        e.total = oe.total;
        that._onProgress(e, options);
      });
      options.xhr = function() {
        return xhr;
      };
    }
  }, _isInstanceOf:function(type, obj) {
    return Object.prototype.toString.call(obj) === "[object " + type + "]";
  }, _initXHRData:function(options) {
    var that = this, formData, file = options.files[0], multipart = options.multipart || !$.support.xhrFileUpload, paramName = $.type(options.paramName) === "array" ? options.paramName[0] : options.paramName;
    options.headers = $.extend({}, options.headers);
    if (options.contentRange) {
      options.headers["Content-Range"] = options.contentRange;
    }
    if (!multipart || (options.blob || !this._isInstanceOf("File", file))) {
      options.headers["Content-Disposition"] = 'attachment; filename="' + encodeURI(file.name) + '"';
    }
    if (!multipart) {
      options.contentType = file.type || "application/octet-stream";
      options.data = options.blob || file;
    } else {
      if ($.support.xhrFormDataFileUpload) {
        if (options.postMessage) {
          formData = this._getFormData(options);
          if (options.blob) {
            formData.push({name:paramName, value:options.blob});
          } else {
            $.each(options.files, function(index, file) {
              formData.push({name:$.type(options.paramName) === "array" && options.paramName[index] || paramName, value:file});
            });
          }
        } else {
          if (that._isInstanceOf("FormData", options.formData)) {
            formData = options.formData;
          } else {
            formData = new FormData;
            $.each(this._getFormData(options), function(index, field) {
              formData.append(field.name, field.value);
            });
          }
          if (options.blob) {
            formData.append(paramName, options.blob, file.name);
          } else {
            $.each(options.files, function(index, file) {
              if (that._isInstanceOf("File", file) || that._isInstanceOf("Blob", file)) {
                formData.append($.type(options.paramName) === "array" && options.paramName[index] || paramName, file, file.uploadName || file.name);
              }
            });
          }
        }
        options.data = formData;
      }
    }
    options.blob = null;
  }, _initIframeSettings:function(options) {
    var targetHost = $("<a></a>").prop("href", options.url).prop("host");
    options.dataType = "iframe " + (options.dataType || "");
    options.formData = this._getFormData(options);
    if (options.redirect && (targetHost && targetHost !== location.host)) {
      options.formData.push({name:options.redirectParamName || "redirect", value:options.redirect});
    }
  }, _initDataSettings:function(options) {
    if (this._isXHRUpload(options)) {
      if (!this._chunkedUpload(options, true)) {
        if (!options.data) {
          this._initXHRData(options);
        }
        this._initProgressListener(options);
      }
      if (options.postMessage) {
        options.dataType = "postmessage " + (options.dataType || "");
      }
    } else {
      this._initIframeSettings(options);
    }
  }, _getParamName:function(options) {
    var fileInput = $(options.fileInput), paramName = options.paramName;
    if (!paramName) {
      paramName = [];
      fileInput.each(function() {
        var input = $(this), name = input.prop("name") || "files[]", i = (input.prop("files") || [1]).length;
        while (i) {
          paramName.push(name);
          i -= 1;
        }
      });
      if (!paramName.length) {
        paramName = [fileInput.prop("name") || "files[]"];
      }
    } else {
      if (!$.isArray(paramName)) {
        paramName = [paramName];
      }
    }
    return paramName;
  }, _initFormSettings:function(options) {
    if (!options.form || !options.form.length) {
      options.form = $(options.fileInput.prop("form"));
      if (!options.form.length) {
        options.form = $(this.options.fileInput.prop("form"));
      }
    }
    options.paramName = this._getParamName(options);
    if (!options.url) {
      options.url = options.form.prop("action") || location.href;
    }
    options.type = (options.type || ($.type(options.form.prop("method")) === "string" && options.form.prop("method") || "")).toUpperCase();
    if (options.type !== "POST" && (options.type !== "PUT" && options.type !== "PATCH")) {
      options.type = "POST";
    }
    if (!options.formAcceptCharset) {
      options.formAcceptCharset = options.form.attr("accept-charset");
    }
  }, _getAJAXSettings:function(data) {
    var options = $.extend({}, this.options, data);
    this._initFormSettings(options);
    this._initDataSettings(options);
    return options;
  }, _getDeferredState:function(deferred) {
    if (deferred.state) {
      return deferred.state();
    }
    if (deferred.isResolved()) {
      return "resolved";
    }
    if (deferred.isRejected()) {
      return "rejected";
    }
    return "pending";
  }, _enhancePromise:function(promise) {
    promise.success = promise.done;
    promise.error = promise.fail;
    promise.complete = promise.always;
    return promise;
  }, _getXHRPromise:function(resolveOrReject, context, args) {
    var dfd = $.Deferred(), promise = dfd.promise();
    context = context || (this.options.context || promise);
    if (resolveOrReject === true) {
      dfd.resolveWith(context, args);
    } else {
      if (resolveOrReject === false) {
        dfd.rejectWith(context, args);
      }
    }
    promise.abort = dfd.promise;
    return this._enhancePromise(promise);
  }, _addConvenienceMethods:function(e, data) {
    var that = this, getPromise = function(args) {
      return $.Deferred().resolveWith(that, args).promise();
    };
    data.process = function(resolveFunc, rejectFunc) {
      if (resolveFunc || rejectFunc) {
        data._processQueue = this._processQueue = (this._processQueue || getPromise([this])).pipe(function() {
          if (data.errorThrown) {
            return $.Deferred().rejectWith(that, [data]).promise();
          }
          return getPromise(arguments);
        }).pipe(resolveFunc, rejectFunc);
      }
      return this._processQueue || getPromise([this]);
    };
    data.submit = function() {
      if (this.state() !== "pending") {
        data.jqXHR = this.jqXHR = that._trigger("submit", $.Event("submit", {delegatedEvent:e}), this) !== false && that._onSend(e, this);
      }
      return this.jqXHR || that._getXHRPromise();
    };
    data.abort = function() {
      if (this.jqXHR) {
        return this.jqXHR.abort();
      }
      this.errorThrown = "abort";
      that._trigger("fail", null, this);
      return that._getXHRPromise(false);
    };
    data.state = function() {
      if (this.jqXHR) {
        return that._getDeferredState(this.jqXHR);
      }
      if (this._processQueue) {
        return that._getDeferredState(this._processQueue);
      }
    };
    data.processing = function() {
      return!this.jqXHR && (this._processQueue && that._getDeferredState(this._processQueue) === "pending");
    };
    data.progress = function() {
      return this._progress;
    };
    data.response = function() {
      return this._response;
    };
  }, _getUploadedBytes:function(jqXHR) {
    var range = jqXHR.getResponseHeader("Range"), parts = range && range.split("-"), upperBytesPos = parts && (parts.length > 1 && parseInt(parts[1], 10));
    return upperBytesPos && upperBytesPos + 1;
  }, _chunkedUpload:function(options, testOnly) {
    options.uploadedBytes = options.uploadedBytes || 0;
    var that = this, file = options.files[0], fs = file.size, ub = options.uploadedBytes, mcs = options.maxChunkSize || fs, slice = this._blobSlice, dfd = $.Deferred(), promise = dfd.promise(), jqXHR, upload;
    if (!(this._isXHRUpload(options) && (slice && (ub || mcs < fs))) || options.data) {
      return false;
    }
    if (testOnly) {
      return true;
    }
    if (ub >= fs) {
      file.error = options.i18n("uploadedBytes");
      return this._getXHRPromise(false, options.context, [null, "error", file.error]);
    }
    upload = function() {
      var o = $.extend({}, options), currentLoaded = o._progress.loaded;
      o.blob = slice.call(file, ub, ub + mcs, file.type);
      o.chunkSize = o.blob.size;
      o.contentRange = "bytes " + ub + "-" + (ub + o.chunkSize - 1) + "/" + fs;
      that._initXHRData(o);
      that._initProgressListener(o);
      jqXHR = (that._trigger("chunksend", null, o) !== false && $.ajax(o) || that._getXHRPromise(false, o.context)).done(function(result, textStatus, jqXHR) {
        ub = that._getUploadedBytes(jqXHR) || ub + o.chunkSize;
        if (currentLoaded + o.chunkSize - o._progress.loaded) {
          that._onProgress($.Event("progress", {lengthComputable:true, loaded:ub - o.uploadedBytes, total:ub - o.uploadedBytes}), o);
        }
        options.uploadedBytes = o.uploadedBytes = ub;
        o.result = result;
        o.textStatus = textStatus;
        o.jqXHR = jqXHR;
        that._trigger("chunkdone", null, o);
        that._trigger("chunkalways", null, o);
        if (ub < fs) {
          upload();
        } else {
          dfd.resolveWith(o.context, [result, textStatus, jqXHR]);
        }
      }).fail(function(jqXHR, textStatus, errorThrown) {
        o.jqXHR = jqXHR;
        o.textStatus = textStatus;
        o.errorThrown = errorThrown;
        that._trigger("chunkfail", null, o);
        that._trigger("chunkalways", null, o);
        dfd.rejectWith(o.context, [jqXHR, textStatus, errorThrown]);
      });
    };
    this._enhancePromise(promise);
    promise.abort = function() {
      return jqXHR.abort();
    };
    upload();
    return promise;
  }, _beforeSend:function(e, data) {
    if (this._active === 0) {
      this._trigger("start");
      this._bitrateTimer = new this._BitrateTimer;
      this._progress.loaded = this._progress.total = 0;
      this._progress.bitrate = 0;
    }
    this._initResponseObject(data);
    this._initProgressObject(data);
    data._progress.loaded = data.loaded = data.uploadedBytes || 0;
    data._progress.total = data.total = this._getTotal(data.files) || 1;
    data._progress.bitrate = data.bitrate = 0;
    this._active += 1;
    this._progress.loaded += data.loaded;
    this._progress.total += data.total;
  }, _onDone:function(result, textStatus, jqXHR, options) {
    var total = options._progress.total, response = options._response;
    if (options._progress.loaded < total) {
      this._onProgress($.Event("progress", {lengthComputable:true, loaded:total, total:total}), options);
    }
    response.result = options.result = result;
    response.textStatus = options.textStatus = textStatus;
    response.jqXHR = options.jqXHR = jqXHR;
    this._trigger("done", null, options);
  }, _onFail:function(jqXHR, textStatus, errorThrown, options) {
    var response = options._response;
    if (options.recalculateProgress) {
      this._progress.loaded -= options._progress.loaded;
      this._progress.total -= options._progress.total;
    }
    response.jqXHR = options.jqXHR = jqXHR;
    response.textStatus = options.textStatus = textStatus;
    response.errorThrown = options.errorThrown = errorThrown;
    this._trigger("fail", null, options);
  }, _onAlways:function(jqXHRorResult, textStatus, jqXHRorError, options) {
    this._trigger("always", null, options);
  }, _onSend:function(e, data) {
    if (!data.submit) {
      this._addConvenienceMethods(e, data);
    }
    var that = this, jqXHR, aborted, slot, pipe, options = that._getAJAXSettings(data), send = function() {
      that._sending += 1;
      options._bitrateTimer = new that._BitrateTimer;
      jqXHR = jqXHR || ((aborted || that._trigger("send", $.Event("send", {delegatedEvent:e}), options) === false) && that._getXHRPromise(false, options.context, aborted) || (that._chunkedUpload(options) || $.ajax(options))).done(function(result, textStatus, jqXHR) {
        that._onDone(result, textStatus, jqXHR, options);
      }).fail(function(jqXHR, textStatus, errorThrown) {
        that._onFail(jqXHR, textStatus, errorThrown, options);
      }).always(function(jqXHRorResult, textStatus, jqXHRorError) {
        that._onAlways(jqXHRorResult, textStatus, jqXHRorError, options);
        that._sending -= 1;
        that._active -= 1;
        if (options.limitConcurrentUploads && options.limitConcurrentUploads > that._sending) {
          var nextSlot = that._slots.shift();
          while (nextSlot) {
            if (that._getDeferredState(nextSlot) === "pending") {
              nextSlot.resolve();
              break;
            }
            nextSlot = that._slots.shift();
          }
        }
        if (that._active === 0) {
          that._trigger("stop");
        }
      });
      return jqXHR;
    };
    this._beforeSend(e, options);
    if (this.options.sequentialUploads || this.options.limitConcurrentUploads && this.options.limitConcurrentUploads <= this._sending) {
      if (this.options.limitConcurrentUploads > 1) {
        slot = $.Deferred();
        this._slots.push(slot);
        pipe = slot.pipe(send);
      } else {
        this._sequence = this._sequence.pipe(send, send);
        pipe = this._sequence;
      }
      pipe.abort = function() {
        aborted = [undefined, "abort", "abort"];
        if (!jqXHR) {
          if (slot) {
            slot.rejectWith(options.context, aborted);
          }
          return send();
        }
        return jqXHR.abort();
      };
      return this._enhancePromise(pipe);
    }
    return send();
  }, _onAdd:function(e, data) {
    var that = this, result = true, options = $.extend({}, this.options, data), files = data.files, filesLength = files.length, limit = options.limitMultiFileUploads, limitSize = options.limitMultiFileUploadSize, overhead = options.limitMultiFileUploadSizeOverhead, batchSize = 0, paramName = this._getParamName(options), paramNameSet, paramNameSlice, fileSet, i, j = 0;
    if (limitSize && (!filesLength || files[0].size === undefined)) {
      limitSize = undefined;
    }
    if (!(options.singleFileUploads || (limit || limitSize)) || !this._isXHRUpload(options)) {
      fileSet = [files];
      paramNameSet = [paramName];
    } else {
      if (!(options.singleFileUploads || limitSize) && limit) {
        fileSet = [];
        paramNameSet = [];
        for (i = 0;i < filesLength;i += limit) {
          fileSet.push(files.slice(i, i + limit));
          paramNameSlice = paramName.slice(i, i + limit);
          if (!paramNameSlice.length) {
            paramNameSlice = paramName;
          }
          paramNameSet.push(paramNameSlice);
        }
      } else {
        if (!options.singleFileUploads && limitSize) {
          fileSet = [];
          paramNameSet = [];
          for (i = 0;i < filesLength;i = i + 1) {
            batchSize += files[i].size + overhead;
            if (i + 1 === filesLength || (batchSize + files[i + 1].size + overhead > limitSize || limit && i + 1 - j >= limit)) {
              fileSet.push(files.slice(j, i + 1));
              paramNameSlice = paramName.slice(j, i + 1);
              if (!paramNameSlice.length) {
                paramNameSlice = paramName;
              }
              paramNameSet.push(paramNameSlice);
              j = i + 1;
              batchSize = 0;
            }
          }
        } else {
          paramNameSet = paramName;
        }
      }
    }
    data.originalFiles = files;
    $.each(fileSet || files, function(index, element) {
      var newData = $.extend({}, data);
      newData.files = fileSet ? element : [element];
      newData.paramName = paramNameSet[index];
      that._initResponseObject(newData);
      that._initProgressObject(newData);
      that._addConvenienceMethods(e, newData);
      result = that._trigger("add", $.Event("add", {delegatedEvent:e}), newData);
      return result;
    });
    return result;
  }, _replaceFileInput:function(input) {
    var inputClone = input.clone(true);
    $("<form></form>").append(inputClone)[0].reset();
    input.after(inputClone).detach();
    $.cleanData(input.unbind("remove"));
    this.options.fileInput = this.options.fileInput.map(function(i, el) {
      if (el === input[0]) {
        return inputClone[0];
      }
      return el;
    });
    if (input[0] === this.element[0]) {
      this.element = inputClone;
    }
  }, _handleFileTreeEntry:function(entry, path) {
    var that = this, dfd = $.Deferred(), errorHandler = function(e) {
      if (e && !e.entry) {
        e.entry = entry;
      }
      dfd.resolve([e]);
    }, dirReader;
    path = path || "";
    if (entry.isFile) {
      if (entry._file) {
        entry._file.relativePath = path;
        dfd.resolve(entry._file);
      } else {
        entry.file(function(file) {
          file.relativePath = path;
          dfd.resolve(file);
        }, errorHandler);
      }
    } else {
      if (entry.isDirectory) {
        dirReader = entry.createReader();
        dirReader.readEntries(function(entries) {
          that._handleFileTreeEntries(entries, path + entry.name + "/").done(function(files) {
            dfd.resolve(files);
          }).fail(errorHandler);
        }, errorHandler);
      } else {
        dfd.resolve([]);
      }
    }
    return dfd.promise();
  }, _handleFileTreeEntries:function(entries, path) {
    var that = this;
    return $.when.apply($, $.map(entries, function(entry) {
      return that._handleFileTreeEntry(entry, path);
    })).pipe(function() {
      return Array.prototype.concat.apply([], arguments);
    });
  }, _getDroppedFiles:function(dataTransfer) {
    dataTransfer = dataTransfer || {};
    var items = dataTransfer.items;
    if (items && (items.length && (items[0].webkitGetAsEntry || items[0].getAsEntry))) {
      return this._handleFileTreeEntries($.map(items, function(item) {
        var entry;
        if (item.webkitGetAsEntry) {
          entry = item.webkitGetAsEntry();
          if (entry) {
            entry._file = item.getAsFile();
          }
          return entry;
        }
        return item.getAsEntry();
      }));
    }
    return $.Deferred().resolve($.makeArray(dataTransfer.files)).promise();
  }, _getSingleFileInputFiles:function(fileInput) {
    fileInput = $(fileInput);
    var entries = fileInput.prop("webkitEntries") || fileInput.prop("entries"), files, value;
    if (entries && entries.length) {
      return this._handleFileTreeEntries(entries);
    }
    files = $.makeArray(fileInput.prop("files"));
    if (!files.length) {
      value = fileInput.prop("value");
      if (!value) {
        return $.Deferred().resolve([]).promise();
      }
      files = [{name:value.replace(/^.*\\/, "")}];
    } else {
      if (files[0].name === undefined && files[0].fileName) {
        $.each(files, function(index, file) {
          file.name = file.fileName;
          file.size = file.fileSize;
        });
      }
    }
    return $.Deferred().resolve(files).promise();
  }, _getFileInputFiles:function(fileInput) {
    if (!(fileInput instanceof $) || fileInput.length === 1) {
      return this._getSingleFileInputFiles(fileInput);
    }
    return $.when.apply($, $.map(fileInput, this._getSingleFileInputFiles)).pipe(function() {
      return Array.prototype.concat.apply([], arguments);
    });
  }, _onChange:function(e) {
    var that = this, data = {fileInput:$(e.target), form:$(e.target.form)};
    this._getFileInputFiles(data.fileInput).always(function(files) {
      data.files = files;
      if (that.options.replaceFileInput) {
        that._replaceFileInput(data.fileInput);
      }
      if (that._trigger("change", $.Event("change", {delegatedEvent:e}), data) !== false) {
        that._onAdd(e, data);
      }
    });
  }, _onPaste:function(e) {
    var items = e.originalEvent && (e.originalEvent.clipboardData && e.originalEvent.clipboardData.items), data = {files:[]};
    if (items && items.length) {
      $.each(items, function(index, item) {
        var file = item.getAsFile && item.getAsFile();
        if (file) {
          data.files.push(file);
        }
      });
      if (this._trigger("paste", $.Event("paste", {delegatedEvent:e}), data) !== false) {
        this._onAdd(e, data);
      }
    }
  }, _onDrop:function(e) {
    e.dataTransfer = e.originalEvent && e.originalEvent.dataTransfer;
    var that = this, dataTransfer = e.dataTransfer, data = {};
    if (dataTransfer && (dataTransfer.files && dataTransfer.files.length)) {
      e.preventDefault();
      this._getDroppedFiles(dataTransfer).always(function(files) {
        data.files = files;
        if (that._trigger("drop", $.Event("drop", {delegatedEvent:e}), data) !== false) {
          that._onAdd(e, data);
        }
      });
    }
  }, _onDragOver:function(e) {
    e.dataTransfer = e.originalEvent && e.originalEvent.dataTransfer;
    var dataTransfer = e.dataTransfer;
    if (dataTransfer && ($.inArray("Files", dataTransfer.types) !== -1 && this._trigger("dragover", $.Event("dragover", {delegatedEvent:e})) !== false)) {
      e.preventDefault();
      dataTransfer.dropEffect = "copy";
    }
  }, _initEventHandlers:function() {
    if (this._isXHRUpload(this.options)) {
      this._on(this.options.dropZone, {dragover:this._onDragOver, drop:this._onDrop});
      this._on(this.options.pasteZone, {paste:this._onPaste});
    }
    if ($.support.fileInput) {
      this._on(this.options.fileInput, {change:this._onChange});
    }
  }, _destroyEventHandlers:function() {
    this._off(this.options.dropZone, "dragover drop");
    this._off(this.options.pasteZone, "paste");
    this._off(this.options.fileInput, "change");
  }, _setOption:function(key, value) {
    var reinit = $.inArray(key, this._specialOptions) !== -1;
    if (reinit) {
      this._destroyEventHandlers();
    }
    this._super(key, value);
    if (reinit) {
      this._initSpecialOptions();
      this._initEventHandlers();
    }
  }, _initSpecialOptions:function() {
    var options = this.options;
    if (options.fileInput === undefined) {
      options.fileInput = this.element.is('input[type="file"]') ? this.element : this.element.find('input[type="file"]');
    } else {
      if (!(options.fileInput instanceof $)) {
        options.fileInput = $(options.fileInput);
      }
    }
    if (!(options.dropZone instanceof $)) {
      options.dropZone = $(options.dropZone);
    }
    if (!(options.pasteZone instanceof $)) {
      options.pasteZone = $(options.pasteZone);
    }
  }, _getRegExp:function(str) {
    var parts = str.split("/"), modifiers = parts.pop();
    parts.shift();
    return new RegExp(parts.join("/"), modifiers);
  }, _isRegExpOption:function(key, value) {
    return key !== "url" && ($.type(value) === "string" && /^\/.*\/[igm]{0,3}$/.test(value));
  }, _initDataAttributes:function() {
    var that = this, options = this.options, clone = $(this.element[0].cloneNode(false));
    $.each(clone.data(), function(key, value) {
      var dataAttributeName = "data-" + key.replace(/([a-z])([A-Z])/g, "$1-$2").toLowerCase();
      if (clone.attr(dataAttributeName)) {
        if (that._isRegExpOption(key, value)) {
          value = that._getRegExp(value);
        }
        options[key] = value;
      }
    });
  }, _create:function() {
    this._initDataAttributes();
    this._initSpecialOptions();
    this._slots = [];
    this._sequence = this._getXHRPromise(true);
    this._sending = this._active = 0;
    this._initProgressObject(this);
    this._initEventHandlers();
  }, active:function() {
    return this._active;
  }, progress:function() {
    return this._progress;
  }, add:function(data) {
    var that = this;
    if (!data || this.options.disabled) {
      return;
    }
    if (data.fileInput && !data.files) {
      this._getFileInputFiles(data.fileInput).always(function(files) {
        data.files = files;
        that._onAdd(null, data);
      });
    } else {
      data.files = $.makeArray(data.files);
      this._onAdd(null, data);
    }
  }, send:function(data) {
    if (data && !this.options.disabled) {
      if (data.fileInput && !data.files) {
        var that = this, dfd = $.Deferred(), promise = dfd.promise(), jqXHR, aborted;
        promise.abort = function() {
          aborted = true;
          if (jqXHR) {
            return jqXHR.abort();
          }
          dfd.reject(null, "abort", "abort");
          return promise;
        };
        this._getFileInputFiles(data.fileInput).always(function(files) {
          if (aborted) {
            return;
          }
          if (!files.length) {
            dfd.reject();
            return;
          }
          data.files = files;
          jqXHR = that._onSend(null, data).then(function(result, textStatus, jqXHR) {
            dfd.resolve(result, textStatus, jqXHR);
          }, function(jqXHR, textStatus, errorThrown) {
            dfd.reject(jqXHR, textStatus, errorThrown);
          });
        });
        return this._enhancePromise(promise);
      }
      data.files = $.makeArray(data.files);
      if (data.files.length) {
        return this._onSend(null, data);
      }
    }
    return this._getXHRPromise(false, data && data.context);
  }});
});
(function(factory) {
  if (typeof define === "function" && define.amd) {
    define(["jquery", "./jquery.fileupload"], factory);
  } else {
    factory(window.jQuery);
  }
})(function($) {
  var originalAdd = $.blueimp.fileupload.prototype.options.add;
  $.widget("blueimp.fileupload", $.blueimp.fileupload, {options:{processQueue:[], add:function(e, data) {
    var $this = $(this);
    data.process(function() {
      return $this.fileupload("process", data);
    });
    originalAdd.call(this, e, data);
  }}, processActions:{}, _processFile:function(data, originalData) {
    var that = this, dfd = $.Deferred().resolveWith(that, [data]), chain = dfd.promise();
    this._trigger("process", null, data);
    $.each(data.processQueue, function(i, settings) {
      var func = function(data) {
        if (originalData.errorThrown) {
          return $.Deferred().rejectWith(that, [originalData]).promise();
        }
        return that.processActions[settings.action].call(that, data, settings);
      };
      chain = chain.pipe(func, settings.always && func);
    });
    chain.done(function() {
      that._trigger("processdone", null, data);
      that._trigger("processalways", null, data);
    }).fail(function() {
      that._trigger("processfail", null, data);
      that._trigger("processalways", null, data);
    });
    return chain;
  }, _transformProcessQueue:function(options) {
    var processQueue = [];
    $.each(options.processQueue, function() {
      var settings = {}, action = this.action, prefix = this.prefix === true ? action : this.prefix;
      $.each(this, function(key, value) {
        if ($.type(value) === "string" && value.charAt(0) === "@") {
          settings[key] = options[value.slice(1) || (prefix ? prefix + key.charAt(0).toUpperCase() + key.slice(1) : key)];
        } else {
          settings[key] = value;
        }
      });
      processQueue.push(settings);
    });
    options.processQueue = processQueue;
  }, processing:function() {
    return this._processing;
  }, process:function(data) {
    var that = this, options = $.extend({}, this.options, data);
    if (options.processQueue && options.processQueue.length) {
      this._transformProcessQueue(options);
      if (this._processing === 0) {
        this._trigger("processstart");
      }
      $.each(data.files, function(index) {
        var opts = index ? $.extend({}, options) : options, func = function() {
          if (data.errorThrown) {
            return $.Deferred().rejectWith(that, [data]).promise();
          }
          return that._processFile(opts, data);
        };
        opts.index = index;
        that._processing += 1;
        that._processingQueue = that._processingQueue.pipe(func, func).always(function() {
          that._processing -= 1;
          if (that._processing === 0) {
            that._trigger("processstop");
          }
        });
      });
    }
    return this._processingQueue;
  }, _create:function() {
    this._super();
    this._processing = 0;
    this._processingQueue = $.Deferred().resolveWith(this).promise();
  }});
});
(function(factory) {
  if (typeof define === "function" && define.amd) {
    define(["jquery", "load-image", "load-image-meta", "load-image-exif", "load-image-ios", "canvas-to-blob", "./jquery.fileupload-process"], factory);
  } else {
    factory(window.jQuery, window.loadImage);
  }
})(function($, loadImage) {
  $.blueimp.fileupload.prototype.options.processQueue.unshift({action:"loadImageMetaData", disableImageHead:"@", disableExif:"@", disableExifThumbnail:"@", disableExifSub:"@", disableExifGps:"@", disabled:"@disableImageMetaDataLoad"}, {action:"loadImage", prefix:true, fileTypes:"@", maxFileSize:"@", noRevoke:"@", disabled:"@disableImageLoad"}, {action:"resizeImage", prefix:"image", maxWidth:"@", maxHeight:"@", minWidth:"@", minHeight:"@", crop:"@", orientation:"@", forceResize:"@", disabled:"@disableImageResize"}, 
  {action:"saveImage", quality:"@imageQuality", type:"@imageType", disabled:"@disableImageResize"}, {action:"saveImageMetaData", disabled:"@disableImageMetaDataSave"}, {action:"resizeImage", prefix:"preview", maxWidth:"@", maxHeight:"@", minWidth:"@", minHeight:"@", crop:"@", orientation:"@", thumbnail:"@", canvas:"@", disabled:"@disableImagePreview"}, {action:"setImage", name:"@imagePreviewName", disabled:"@disableImagePreview"}, {action:"deleteImageReferences", disabled:"@disableImageReferencesDeletion"});
  $.widget("blueimp.fileupload", $.blueimp.fileupload, {options:{loadImageFileTypes:/^image\/(gif|jpeg|png|svg\+xml)$/, loadImageMaxFileSize:1E7, imageMaxWidth:1920, imageMaxHeight:1080, imageOrientation:false, imageCrop:false, disableImageResize:true, previewMaxWidth:80, previewMaxHeight:80, previewOrientation:true, previewThumbnail:true, previewCrop:false, previewCanvas:true}, processActions:{loadImage:function(data, options) {
    if (options.disabled) {
      return data;
    }
    var that = this, file = data.files[data.index], dfd = $.Deferred();
    if ($.type(options.maxFileSize) === "number" && file.size > options.maxFileSize || (options.fileTypes && !options.fileTypes.test(file.type) || !loadImage(file, function(img) {
      if (img.src) {
        data.img = img;
      }
      dfd.resolveWith(that, [data]);
    }, options))) {
      return data;
    }
    return dfd.promise();
  }, resizeImage:function(data, options) {
    if (options.disabled || !(data.canvas || data.img)) {
      return data;
    }
    options = $.extend({canvas:true}, options);
    var that = this, dfd = $.Deferred(), img = options.canvas && data.canvas || data.img, resolve = function(newImg) {
      if (newImg && (newImg.width !== img.width || (newImg.height !== img.height || options.forceResize))) {
        data[newImg.getContext ? "canvas" : "img"] = newImg;
      }
      data.preview = newImg;
      dfd.resolveWith(that, [data]);
    }, thumbnail;
    if (data.exif) {
      if (options.orientation === true) {
        options.orientation = data.exif.get("Orientation");
      }
      if (options.thumbnail) {
        thumbnail = data.exif.get("Thumbnail");
        if (thumbnail) {
          loadImage(thumbnail, resolve, options);
          return dfd.promise();
        }
      }
      if (data.orientation) {
        delete options.orientation;
      } else {
        data.orientation = options.orientation;
      }
    }
    if (img) {
      resolve(loadImage.scale(img, options));
      return dfd.promise();
    }
    return data;
  }, saveImage:function(data, options) {
    if (!data.canvas || options.disabled) {
      return data;
    }
    var that = this, file = data.files[data.index], dfd = $.Deferred();
    if (data.canvas.toBlob) {
      data.canvas.toBlob(function(blob) {
        if (!blob.name) {
          if (file.type === blob.type) {
            blob.name = file.name;
          } else {
            if (file.name) {
              blob.name = file.name.replace(/\..+$/, "." + blob.type.substr(6));
            }
          }
        }
        if (file.type !== blob.type) {
          delete data.imageHead;
        }
        data.files[data.index] = blob;
        dfd.resolveWith(that, [data]);
      }, options.type || file.type, options.quality);
    } else {
      return data;
    }
    return dfd.promise();
  }, loadImageMetaData:function(data, options) {
    if (options.disabled) {
      return data;
    }
    var that = this, dfd = $.Deferred();
    loadImage.parseMetaData(data.files[data.index], function(result) {
      $.extend(data, result);
      dfd.resolveWith(that, [data]);
    }, options);
    return dfd.promise();
  }, saveImageMetaData:function(data, options) {
    if (!(data.imageHead && (data.canvas && (data.canvas.toBlob && !options.disabled)))) {
      return data;
    }
    var file = data.files[data.index], blob = new Blob([data.imageHead, this._blobSlice.call(file, 20)], {type:file.type});
    blob.name = file.name;
    data.files[data.index] = blob;
    return data;
  }, setImage:function(data, options) {
    if (data.preview && !options.disabled) {
      data.files[data.index][options.name || "preview"] = data.preview;
    }
    return data;
  }, deleteImageReferences:function(data, options) {
    if (!options.disabled) {
      delete data.img;
      delete data.canvas;
      delete data.preview;
      delete data.imageHead;
    }
    return data;
  }}});
});
(function(factory) {
  if (typeof define === "function" && define.amd) {
    define(["jquery", "load-image", "./jquery.fileupload-process"], factory);
  } else {
    factory(window.jQuery, window.loadImage);
  }
})(function($, loadImage) {
  $.blueimp.fileupload.prototype.options.processQueue.unshift({action:"loadAudio", prefix:true, fileTypes:"@", maxFileSize:"@", disabled:"@disableAudioPreview"}, {action:"setAudio", name:"@audioPreviewName", disabled:"@disableAudioPreview"});
  $.widget("blueimp.fileupload", $.blueimp.fileupload, {options:{loadAudioFileTypes:/^audio\/.*$/}, _audioElement:document.createElement("audio"), processActions:{loadAudio:function(data, options) {
    if (options.disabled) {
      return data;
    }
    var file = data.files[data.index], url, audio;
    if (this._audioElement.canPlayType && (this._audioElement.canPlayType(file.type) && (($.type(options.maxFileSize) !== "number" || file.size <= options.maxFileSize) && (!options.fileTypes || options.fileTypes.test(file.type))))) {
      url = loadImage.createObjectURL(file);
      if (url) {
        audio = this._audioElement.cloneNode(false);
        audio.src = url;
        audio.controls = true;
        data.audio = audio;
        return data;
      }
    }
    return data;
  }, setAudio:function(data, options) {
    if (data.audio && !options.disabled) {
      data.files[data.index][options.name || "preview"] = data.audio;
    }
    return data;
  }}});
});
(function(factory) {
  if (typeof define === "function" && define.amd) {
    define(["jquery", "load-image", "./jquery.fileupload-process"], factory);
  } else {
    factory(window.jQuery, window.loadImage);
  }
})(function($, loadImage) {
  $.blueimp.fileupload.prototype.options.processQueue.unshift({action:"loadVideo", prefix:true, fileTypes:"@", maxFileSize:"@", disabled:"@disableVideoPreview"}, {action:"setVideo", name:"@videoPreviewName", disabled:"@disableVideoPreview"});
  $.widget("blueimp.fileupload", $.blueimp.fileupload, {options:{loadVideoFileTypes:/^video\/.*$/}, _videoElement:document.createElement("video"), processActions:{loadVideo:function(data, options) {
    if (options.disabled) {
      return data;
    }
    var file = data.files[data.index], url, video;
    if (this._videoElement.canPlayType && (this._videoElement.canPlayType(file.type) && (($.type(options.maxFileSize) !== "number" || file.size <= options.maxFileSize) && (!options.fileTypes || options.fileTypes.test(file.type))))) {
      url = loadImage.createObjectURL(file);
      if (url) {
        video = this._videoElement.cloneNode(false);
        video.src = url;
        video.controls = true;
        data.video = video;
        return data;
      }
    }
    return data;
  }, setVideo:function(data, options) {
    if (data.video && !options.disabled) {
      data.files[data.index][options.name || "preview"] = data.video;
    }
    return data;
  }}});
});
(function(factory) {
  if (typeof define === "function" && define.amd) {
    define(["jquery", "./jquery.fileupload-process"], factory);
  } else {
    factory(window.jQuery);
  }
})(function($) {
  $.blueimp.fileupload.prototype.options.processQueue.push({action:"validate", always:true, acceptFileTypes:"@", maxFileSize:"@", minFileSize:"@", maxNumberOfFiles:"@", disabled:"@disableValidation"});
  $.widget("blueimp.fileupload", $.blueimp.fileupload, {options:{getNumberOfFiles:$.noop, messages:{maxNumberOfFiles:"Maximum number of files exceeded", acceptFileTypes:"File type not allowed", maxFileSize:"File is too large", minFileSize:"File is too small"}}, processActions:{validate:function(data, options) {
    if (options.disabled) {
      return data;
    }
    var dfd = $.Deferred(), settings = this.options, file = data.files[data.index], fileSize;
    if (options.minFileSize || options.maxFileSize) {
      fileSize = file.size;
    }
    if ($.type(options.maxNumberOfFiles) === "number" && (settings.getNumberOfFiles() || 0) + data.files.length > options.maxNumberOfFiles) {
      file.error = settings.i18n("maxNumberOfFiles");
    } else {
      if (options.acceptFileTypes && !(options.acceptFileTypes.test(file.type) || options.acceptFileTypes.test(file.name))) {
        file.error = settings.i18n("acceptFileTypes");
      } else {
        if (fileSize > options.maxFileSize) {
          file.error = settings.i18n("maxFileSize");
        } else {
          if ($.type(fileSize) === "number" && fileSize < options.minFileSize) {
            file.error = settings.i18n("minFileSize");
          } else {
            delete file.error;
          }
        }
      }
    }
    if (file.error || data.files.error) {
      data.files.error = true;
      dfd.rejectWith(this, [data]);
    } else {
      dfd.resolveWith(this, [data]);
    }
    return dfd.promise();
  }}});
});
(function(factory) {
  if (typeof define === "function" && define.amd) {
    define(["jquery", "tmpl", "./jquery.fileupload-image", "./jquery.fileupload-audio", "./jquery.fileupload-video", "./jquery.fileupload-validate"], factory);
  } else {
    factory(window.jQuery, window.tmpl);
  }
})(function($, tmpl) {
  $.blueimp.fileupload.prototype._specialOptions.push("filesContainer", "uploadTemplateId", "downloadTemplateId");
  $.widget("blueimp.fileupload", $.blueimp.fileupload, {options:{autoUpload:false, uploadTemplateId:"template-upload", downloadTemplateId:"template-download", filesContainer:undefined, prependFiles:false, dataType:"json", getNumberOfFiles:function() {
    return this.filesContainer.children().not(".processing").length;
  }, getFilesFromResponse:function(data) {
    if (data.result && $.isArray(data.result.result)) {
      return data.result.result;
    }
    return[];
  }, add:function(e, data) {
    if (e.isDefaultPrevented()) {
      return false;
    }
    var $this = $(this), that = $this.data("blueimp-fileupload") || $this.data("fileupload"), options = that.options;
    data.context = that._renderUpload(data.files).data("data", data).addClass("processing");
    options.filesContainer[options.prependFiles ? "prepend" : "append"](data.context);
    that._forceReflow(data.context);
    that._transition(data.context);
    data.process(function() {
      return $this.fileupload("process", data);
    }).always(function() {
      data.context.each(function(index) {
        $(this).find(".size").text(that._formatFileSize(data.files[index].size));
      }).removeClass("processing");
      that._renderPreviews(data);
    }).done(function() {
      data.context.find(".start").prop("disabled", false);
      if (that._trigger("added", e, data) !== false && ((options.autoUpload || data.autoUpload) && data.autoUpload !== false)) {
        data.submit();
      }
    }).fail(function() {
      if (data.files.error) {
        data.context.each(function(index) {
          var error = data.files[index].error;
          if (error) {
            $(this).find(".error").text(error);
          }
        });
      }
    });
  }, send:function(e, data) {
    if (e.isDefaultPrevented()) {
      return false;
    }
    var that = $(this).data("blueimp-fileupload") || $(this).data("fileupload");
    if (data.context && (data.dataType && data.dataType.substr(0, 6) === "iframe")) {
      data.context.find(".progress").addClass(!$.support.transition && "progress-animated").attr("aria-valuenow", 100).children().first().css("width", "100%");
    }
    return that._trigger("sent", e, data);
  }, done:function(e, data) {
    if (e.isDefaultPrevented()) {
      return false;
    }
    var that = $(this).data("blueimp-fileupload") || $(this).data("fileupload"), getFilesFromResponse = data.getFilesFromResponse || that.options.getFilesFromResponse, files = getFilesFromResponse(data), template, deferred;
    if (data.context) {
      data.context.each(function(index) {
        var file = files[index] || {error:"Empty file upload result"};
        deferred = that._addFinishedDeferreds();
        that._transition($(this)).done(function() {
          var node = $(this);
          template = that._renderDownload([file]).replaceAll(node);
          that._forceReflow(template);
          that._transition(template).done(function() {
            data.context = $(this);
            that._trigger("completed", e, data);
            that._trigger("finished", e, data);
            deferred.resolve();
          });
        });
      });
    } else {
      template = that._renderDownload(files)[that.options.prependFiles ? "prependTo" : "appendTo"](that.options.filesContainer);
      that._forceReflow(template);
      deferred = that._addFinishedDeferreds();
      that._transition(template).done(function() {
        data.context = $(this);
        that._trigger("completed", e, data);
        that._trigger("finished", e, data);
        deferred.resolve();
      });
    }
  }, fail:function(e, data) {
    if (e.isDefaultPrevented()) {
      return false;
    }
    var that = $(this).data("blueimp-fileupload") || $(this).data("fileupload"), template, deferred;
    if (data.context) {
      data.context.each(function(index) {
        if (data.errorThrown !== "abort") {
          var file = data.files[index];
          file.error = file.error || (data.errorThrown || true);
          deferred = that._addFinishedDeferreds();
          that._transition($(this)).done(function() {
            var node = $(this);
            template = that._renderDownload([file]).replaceAll(node);
            that._forceReflow(template);
            that._transition(template).done(function() {
              data.context = $(this);
              that._trigger("failed", e, data);
              that._trigger("finished", e, data);
              deferred.resolve();
            });
          });
        } else {
          deferred = that._addFinishedDeferreds();
          that._transition($(this)).done(function() {
            $(this).remove();
            that._trigger("failed", e, data);
            that._trigger("finished", e, data);
            deferred.resolve();
          });
        }
      });
    } else {
      if (data.errorThrown !== "abort") {
        data.context = that._renderUpload(data.files)[that.options.prependFiles ? "prependTo" : "appendTo"](that.options.filesContainer).data("data", data);
        that._forceReflow(data.context);
        deferred = that._addFinishedDeferreds();
        that._transition(data.context).done(function() {
          data.context = $(this);
          that._trigger("failed", e, data);
          that._trigger("finished", e, data);
          deferred.resolve();
        });
      } else {
        that._trigger("failed", e, data);
        that._trigger("finished", e, data);
        that._addFinishedDeferreds().resolve();
      }
    }
  }, progress:function(e, data) {
    if (e.isDefaultPrevented()) {
      return false;
    }
    var progress = Math.floor(data.loaded / data.total * 100);
    if (data.context) {
      data.context.each(function() {
        $(this).find(".progress").attr("aria-valuenow", progress).children().first().css("width", progress + "%");
      });
    }
  }, progressall:function(e, data) {
    if (e.isDefaultPrevented()) {
      return false;
    }
    var $this = $(this), progress = Math.floor(data.loaded / data.total * 100), globalProgressNode = $this.find(".fileupload-progress"), extendedProgressNode = globalProgressNode.find(".progress-extended");
    if (extendedProgressNode.length) {
      extendedProgressNode.html(($this.data("blueimp-fileupload") || $this.data("fileupload"))._renderExtendedProgress(data));
    }
    globalProgressNode.find(".progress").attr("aria-valuenow", progress).children().first().css("width", progress + "%");
  }, start:function(e) {
    if (e.isDefaultPrevented()) {
      return false;
    }
    var that = $(this).data("blueimp-fileupload") || $(this).data("fileupload");
    that._resetFinishedDeferreds();
    that._transition($(this).find(".fileupload-progress")).done(function() {
      that._trigger("started", e);
    });
  }, stop:function(e) {
    if (e.isDefaultPrevented()) {
      return false;
    }
    var that = $(this).data("blueimp-fileupload") || $(this).data("fileupload"), deferred = that._addFinishedDeferreds();
    $.when.apply($, that._getFinishedDeferreds()).done(function() {
      that._trigger("stopped", e);
    });
    that._transition($(this).find(".fileupload-progress")).done(function() {
      $(this).find(".progress").attr("aria-valuenow", "0").children().first().css("width", "0%");
      $(this).find(".progress-extended").html("&nbsp;");
      deferred.resolve();
    });
  }, processstart:function(e) {
    if (e.isDefaultPrevented()) {
      return false;
    }
    $(this).addClass("fileupload-processing");
  }, processstop:function(e) {
    if (e.isDefaultPrevented()) {
      return false;
    }
    $(this).removeClass("fileupload-processing");
  }, destroy:function(e, data) {
    if (e.isDefaultPrevented()) {
      return false;
    }
    var that = $(this).data("blueimp-fileupload") || $(this).data("fileupload"), removeNode = function() {
      that._transition(data.context).done(function() {
        $(this).remove();
        that._trigger("destroyed", e, data);
      });
    };
    if (data.url) {
      data.dataType = data.dataType || that.options.dataType;
      $.ajax(data).done(removeNode).fail(function() {
        that._trigger("destroyfailed", e, data);
      });
    } else {
      removeNode();
    }
  }}, _resetFinishedDeferreds:function() {
    this._finishedUploads = [];
  }, _addFinishedDeferreds:function(deferred) {
    if (!deferred) {
      deferred = $.Deferred();
    }
    this._finishedUploads.push(deferred);
    return deferred;
  }, _getFinishedDeferreds:function() {
    return this._finishedUploads;
  }, _enableDragToDesktop:function() {
    var link = $(this), url = link.prop("href"), name = link.prop("download"), type = "application/octet-stream";
    link.bind("dragstart", function(e) {
      try {
        e.originalEvent.dataTransfer.setData("DownloadURL", [type, name, url].join(":"));
      } catch (ignore) {
      }
    });
  }, _formatFileSize:function(bytes) {
    if (typeof bytes !== "number") {
      return "";
    }
    if (bytes >= 1E9) {
      return(bytes / 1E9).toFixed(2) + " GB";
    }
    if (bytes >= 1E6) {
      return(bytes / 1E6).toFixed(2) + " MB";
    }
    return(bytes / 1E3).toFixed(2) + " KB";
  }, _formatBitrate:function(bits) {
    if (typeof bits !== "number") {
      return "";
    }
    if (bits >= 1E9) {
      return(bits / 1E9).toFixed(2) + " Gbit/s";
    }
    if (bits >= 1E6) {
      return(bits / 1E6).toFixed(2) + " Mbit/s";
    }
    if (bits >= 1E3) {
      return(bits / 1E3).toFixed(2) + " kbit/s";
    }
    return bits.toFixed(2) + " bit/s";
  }, _formatTime:function(seconds) {
    var date = new Date(seconds * 1E3), days = Math.floor(seconds / 86400);
    days = days ? days + "d " : "";
    return days + ("0" + date.getUTCHours()).slice(-2) + ":" + ("0" + date.getUTCMinutes()).slice(-2) + ":" + ("0" + date.getUTCSeconds()).slice(-2);
  }, _formatPercentage:function(floatValue) {
    return(floatValue * 100).toFixed(2) + " %";
  }, _renderExtendedProgress:function(data) {
    return this._formatBitrate(data.bitrate) + " | " + this._formatTime((data.total - data.loaded) * 8 / data.bitrate) + " | " + this._formatPercentage(data.loaded / data.total) + " | " + this._formatFileSize(data.loaded) + " / " + this._formatFileSize(data.total);
  }, _renderTemplate:function(func, files) {
    if (!func) {
      return $();
    }
    var result = func({files:files, formatFileSize:this._formatFileSize, options:this.options});
    if (result instanceof $) {
      return result;
    }
    return $(this.options.templatesContainer).html(result).children();
  }, _renderPreviews:function(data) {
    data.context.find(".preview").each(function(index, elm) {
      $(elm).append(data.files[index].preview);
    });
  }, _renderUpload:function(files) {
    return this._renderTemplate(this.options.uploadTemplate, files);
  }, _renderDownload:function(files) {
    return this._renderTemplate(this.options.downloadTemplate, files).find("a[download]").each(this._enableDragToDesktop).end();
  }, _startHandler:function(e) {
    e.preventDefault();
    var button = $(e.currentTarget), template = button.closest(".template-upload"), data = template.data("data");
    button.prop("disabled", true);
    if (data && data.submit) {
      data.submit();
    }
  }, _cancelHandler:function(e) {
    e.preventDefault();
    var template = $(e.currentTarget).closest(".template-upload,.template-download"), data = template.data("data") || {};
    data.context = data.context || template;
    if (data.abort) {
      data.abort();
    } else {
      data.errorThrown = "abort";
      this._trigger("fail", e, data);
    }
  }, _deleteHandler:function(e) {
    e.preventDefault();
    var button = $(e.currentTarget);
    this._trigger("destroy", e, $.extend({context:button.closest(".template-download"), type:"DELETE"}, button.data()));
  }, _forceReflow:function(node) {
    return $.support.transition && (node.length && node[0].offsetWidth);
  }, _transition:function(node) {
    var dfd = $.Deferred();
    if ($.support.transition && (node.hasClass("fade") && node.is(":visible"))) {
      node.bind($.support.transition.end, function(e) {
        if (e.target === node[0]) {
          node.unbind($.support.transition.end);
          dfd.resolveWith(node);
        }
      }).toggleClass("in");
    } else {
      node.toggleClass("in");
      dfd.resolveWith(node);
    }
    return dfd;
  }, _initButtonBarEventHandlers:function() {
    var fileUploadButtonBar = this.element.find(".fileupload-buttonbar"), filesList = this.options.filesContainer;
    this._on(fileUploadButtonBar.find(".start"), {click:function(e) {
      e.preventDefault();
      filesList.find(".start").click();
    }});
    this._on(fileUploadButtonBar.find(".cancel"), {click:function(e) {
      e.preventDefault();
      filesList.find(".cancel").click();
    }});
    this._on(fileUploadButtonBar.find(".delete"), {click:function(e) {
      e.preventDefault();
      filesList.find(".toggle:checked").closest(".template-download").find(".delete").click();
      fileUploadButtonBar.find(".toggle").prop("checked", false);
    }});
    this._on(fileUploadButtonBar.find(".toggle"), {change:function(e) {
      filesList.find(".toggle").prop("checked", $(e.currentTarget).is(":checked"));
    }});
  }, _destroyButtonBarEventHandlers:function() {
    this._off(this.element.find(".fileupload-buttonbar").find(".start, .cancel, .delete"), "click");
    this._off(this.element.find(".fileupload-buttonbar .toggle"), "change.");
  }, _initEventHandlers:function() {
    this._super();
    this._on(this.options.filesContainer, {"click .start":this._startHandler, "click .cancel":this._cancelHandler, "click .delete":this._deleteHandler});
    this._initButtonBarEventHandlers();
  }, _destroyEventHandlers:function() {
    this._destroyButtonBarEventHandlers();
    this._off(this.options.filesContainer, "click");
    this._super();
  }, _enableFileInputButton:function() {
    this.element.find(".fileinput-button input").prop("disabled", false).parent().removeClass("disabled");
  }, _disableFileInputButton:function() {
    this.element.find(".fileinput-button input").prop("disabled", true).parent().addClass("disabled");
  }, _initTemplates:function() {
    var options = this.options;
    options.templatesContainer = this.document[0].createElement(options.filesContainer.prop("nodeName"));
    if (tmpl) {
      if (options.uploadTemplateId) {
        options.uploadTemplate = tmpl(options.uploadTemplateId);
      }
      if (options.downloadTemplateId) {
        options.downloadTemplate = tmpl(options.downloadTemplateId);
      }
    }
  }, _initFilesContainer:function() {
    var options = this.options;
    if (options.filesContainer === undefined) {
      options.filesContainer = this.element.find(".files");
    } else {
      if (!(options.filesContainer instanceof $)) {
        options.filesContainer = $(options.filesContainer);
      }
    }
  }, _initSpecialOptions:function() {
    this._super();
    this._initFilesContainer();
    this._initTemplates();
  }, _create:function() {
    this._super();
    this._resetFinishedDeferreds();
    if (!$.support.fileInput) {
      this._disableFileInputButton();
    }
  }, enable:function() {
    var wasDisabled = false;
    if (this.options.disabled) {
      wasDisabled = true;
    }
    this._super();
    if (wasDisabled) {
      this.element.find("input, button").prop("disabled", false);
      this._enableFileInputButton();
    }
  }, disable:function() {
    if (!this.options.disabled) {
      this.element.find("input, button").prop("disabled", true);
      this._disableFileInputButton();
    }
    this._super();
  }});
});
(function(factory) {
  if (typeof define === "function" && define.amd) {
    define(["jquery", "./jquery.fileupload-ui"], factory);
  } else {
    factory(window.jQuery);
  }
})(function($) {
  $.widget("blueimp.fileupload", $.blueimp.fileupload, {options:{processdone:function(e, data) {
    data.context.find(".start").button("enable");
  }, progress:function(e, data) {
    if (data.context) {
      data.context.find(".progress").progressbar("option", "value", parseInt(data.loaded / data.total * 100, 10));
    }
  }, progressall:function(e, data) {
    var $this = $(this);
    $this.find(".fileupload-progress").find(".progress").progressbar("option", "value", parseInt(data.loaded / data.total * 100, 10)).end().find(".progress-extended").each(function() {
    });
  }}, _renderUpload:function(func, files) {
    var node = this._super(func, files), showIconText = $(window).width() > 480;
    node.find(".progress").empty().progressbar();
    node.find(".start").button({icons:{primary:"ui-icon-circle-arrow-e"}, text:showIconText});
    node.find(".cancel").button({icons:{primary:"ui-icon-cancel"}, text:showIconText});
    if (node.hasClass("fade")) {
      node.hide();
    }
    return node;
  }, _renderDownload:function(func, files) {
    var node = this._super(func, files), showIconText = $(window).width() > 480;
    node.find(".delete").button({icons:{primary:"ui-icon-trash"}, text:showIconText});
    if (node.hasClass("fade")) {
      node.hide();
    }
    return node;
  }, _startHandler:function(e) {
    $(e.currentTarget).button("disable");
    this._super(e);
  }, _transition:function(node) {
    var deferred = $.Deferred();
    if (node.hasClass("fade")) {
      node.fadeToggle(this.options.transitionDuration, this.options.transitionEasing, function() {
        deferred.resolveWith(node);
      });
    } else {
      deferred.resolveWith(node);
    }
    return deferred;
  }, _create:function() {
    this._super();
    this.element.find(".fileupload-buttonbar").find(".fileinput-button").each(function() {
      var input = $(this).find("input:file").detach();
      $(this).button({icons:{primary:"ui-icon-plusthick"}}).append(input);
    }).end().find(".start").button({icons:{primary:"ui-icon-circle-arrow-e"}}).end().find(".cancel").button({icons:{primary:"ui-icon-cancel"}}).end().find(".delete").button({icons:{primary:"ui-icon-trash"}}).end().find(".progress").progressbar();
  }, _destroy:function() {
    this.element.find(".fileupload-buttonbar").find(".fileinput-button").each(function() {
      var input = $(this).find("input:file").detach();
      $(this).button("destroy").append(input);
    }).end().find(".start").button("destroy").end().find(".cancel").button("destroy").end().find(".delete").button("destroy").end().find(".progress").progressbar("destroy");
    this._super();
  }});
});
OpenLayers.Control.DynamicMeasure = OpenLayers.Class(OpenLayers.Control.Measure, {accuracy:5, persist:true, styles:null, positions:null, maxSegments:1, maxHeadings:1, layerSegmentsOptions:undefined, layerHeadingOptions:null, layerLengthOptions:undefined, layerAreaOptions:undefined, drawingLayer:null, multi:false, layerSegments:null, layerLength:null, layerArea:null, dynamicObj:null, isArea:null, initialize:function(handler, options) {
  options = options || {};
  options.handlerOptions = OpenLayers.Util.extend({persist:!options.drawingLayer}, options.handlerOptions);
  if (options.drawingLayer && !("multi" in options.handlerOptions)) {
    options.handlerOptions.multi = options.multi;
  }
  if (options.drawingLayer) {
    var sketchStyle = options.drawingLayer.styleMap && options.drawingLayer.styleMap.styles.temporary;
    if (sketchStyle) {
      options.handlerOptions.layerOptions = OpenLayers.Util.applyDefaults(options.handlerOptions.layerOptions, {styleMap:new OpenLayers.StyleMap({"default":sketchStyle})});
    }
  }
  var optionsStyles = options.styles || {};
  options.styles = optionsStyles;
  var defaultStyles = OpenLayers.Control.DynamicMeasure.styles;
  if (!options.handlerOptions.layerOptions || !options.handlerOptions.layerOptions.styleMap) {
    var style = new OpenLayers.Style(null, {rules:[new OpenLayers.Rule({symbolizer:{"Point":OpenLayers.Util.applyDefaults(optionsStyles.Point, defaultStyles.Point), "Line":OpenLayers.Util.applyDefaults(optionsStyles.Line, defaultStyles.Line), "Polygon":OpenLayers.Util.applyDefaults(optionsStyles.Polygon, defaultStyles.Polygon)}})]});
    options.handlerOptions = options.handlerOptions || {};
    options.handlerOptions.layerOptions = options.handlerOptions.layerOptions || {};
    options.handlerOptions.layerOptions.styleMap = new OpenLayers.StyleMap({"default":style});
  }
  options.positions = OpenLayers.Util.applyDefaults(options.positions, OpenLayers.Control.DynamicMeasure.positions);
  options.callbacks = options.callbacks || {};
  if (options.drawingLayer) {
    OpenLayers.Util.applyDefaults(options.callbacks, {create:function(vertex, feature) {
      this.callbackCreate(vertex, feature);
      this.drawingLayer.events.triggerEvent("sketchstarted", {vertex:vertex, feature:feature});
    }, modify:function(vertex, feature) {
      this.callbackModify(vertex, feature);
      this.drawingLayer.events.triggerEvent("sketchmodified", {vertex:vertex, feature:feature});
    }, done:function(geometry) {
      this.callbackDone(geometry);
      this.drawFeature(geometry);
    }});
  }
  OpenLayers.Util.applyDefaults(options.callbacks, {create:this.callbackCreate, point:this.callbackPoint, cancel:this.callbackCancel, done:this.callbackDone, modify:this.callbackModify, redo:this.callbackRedo, undo:this.callbackUndo});
  var _self = this;
  var oldOnselectstart = document.onselectstart ? document.onselectstart : OpenLayers.Function.True;
  var handlerTuned = OpenLayers.Class(handler, {down:function(evt) {
    document.onselectstart = OpenLayers.Function.False;
    return handler.prototype.down.apply(this, arguments);
  }, up:function(evt) {
    document.onselectstart = oldOnselectstart;
    return handler.prototype.up.apply(this, arguments);
  }, move:function(evt) {
    if (!this.mouseDown) {
      document.onselectstart = oldOnselectstart;
    }
    return handler.prototype.move.apply(this, arguments);
  }, mouseout:function(evt) {
    if (OpenLayers.Util.mouseLeft(evt, this.map.viewPortDiv)) {
      if (this.mouseDown) {
        document.onselectstart = oldOnselectstart;
      }
    }
    return handler.prototype.mouseout.apply(this, arguments);
  }, finalize:function() {
    document.onselectstart = oldOnselectstart;
    handler.prototype.finalize.apply(this, arguments);
  }}, {undo:function() {
    var undone = handler.prototype.undo.call(this);
    if (undone) {
      this.callback("undo", [this.point.geometry, this.getSketch(), true]);
    }
    return undone;
  }, redo:function() {
    var redone = handler.prototype.redo.call(this);
    if (redone) {
      this.callback("redo", [this.point.geometry, this.getSketch(), true]);
    }
    return redone;
  }});
  OpenLayers.Control.Measure.prototype.initialize.call(this, handlerTuned, options);
  this.isArea = handler.prototype.polygon !== undefined;
}, destroy:function() {
  this.deactivate();
  OpenLayers.Control.Measure.prototype.destroy.apply(this, arguments);
}, draw:function() {
}, activate:function() {
  var response = OpenLayers.Control.Measure.prototype.activate.apply(this, arguments);
  if (response) {
    this.dynamicObj = {};
    var _optionsStyles = this.styles || {}, _defaultStyles = OpenLayers.Control.DynamicMeasure.styles, _self = this;
    var _create = function(styleName, initialOptions) {
      if (initialOptions === null) {
        return null;
      }
      var options = OpenLayers.Util.extend({displayInLayerSwitcher:false, calculateInRange:OpenLayers.Function.True}, initialOptions);
      if (!options.styleMap) {
        var style = _optionsStyles[styleName];
        options.styleMap = new OpenLayers.StyleMap({"default":OpenLayers.Util.applyDefaults(style, _defaultStyles[styleName])});
      }
      var layer = new OpenLayers.Layer.Vector(_self.CLASS_NAME + " " + styleName, options);
      _self.map.addLayer(layer);
      return layer;
    };
    this.layerSegments = _create("labelSegments", this.layerSegmentsOptions);
    this.layerHeading = _create("labelHeading", this.layerHeadingOptions);
    this.layerLength = _create("labelLength", this.layerLengthOptions);
    if (this.isArea) {
      this.layerArea = _create("labelArea", this.layerAreaOptions);
    }
  }
  return response;
}, deactivate:function() {
  var response = OpenLayers.Control.Measure.prototype.deactivate.apply(this, arguments);
  if (response) {
    this.layerSegments && this.layerSegments.destroy();
    this.layerLength && this.layerLength.destroy();
    this.layerHeading && this.layerHeading.destroy();
    this.layerArea && this.layerArea.destroy();
    this.dynamicObj = null;
    this.layerSegments = null;
    this.layerLength = null;
    this.layerHeading = null;
    this.layerArea = null;
  }
  return response;
}, setImmediate:function(immediate) {
  this.immediate = immediate;
}, callbackCreate:function() {
  var dynamicObj = this.dynamicObj;
  dynamicObj.drawing = false;
  dynamicObj.freehand = false;
  dynamicObj.fromIndex = 0;
  dynamicObj.countSegments = 0;
}, callbackCancel:function() {
  this.destroyLabels();
}, callbackDone:function(geometry) {
  this.measureComplete(geometry);
  if (!this.persist) {
    this.destroyLabels();
  }
}, drawFeature:function(geometry) {
  var feature = new OpenLayers.Feature.Vector(geometry);
  var proceed = this.drawingLayer.events.triggerEvent("sketchcomplete", {feature:feature});
  if (proceed !== false) {
    feature.state = OpenLayers.State.INSERT;
    this.drawingLayer.addFeatures([feature]);
    this.featureAdded && this.featureAdded(feature);
    this.events.triggerEvent("featureadded", {feature:feature});
  }
}, destroyLabels:function() {
  this.layerSegments && this.layerSegments.destroyFeatures(null, {silent:true});
  this.layerLength && this.layerLength.destroyFeatures(null, {silent:true});
  this.layerHeading && this.layerHeading.destroyFeatures(null, {silent:true});
  this.layerArea && this.layerArea.destroyFeatures(null, {silent:true});
}, callbackPoint:function(point, geometry) {
  var dynamicObj = this.dynamicObj;
  if (!dynamicObj.drawing) {
    this.destroyLabels();
  }
  if (!this.handler.freehandMode(this.handler.evt)) {
    dynamicObj.fromIndex = this.handler.getCurrentPointIndex() - 1;
    dynamicObj.freehand = false;
    dynamicObj.countSegments++;
  } else {
    if (!dynamicObj.freehand) {
      dynamicObj.fromIndex = this.handler.getCurrentPointIndex() - 1;
      dynamicObj.freehand = true;
      dynamicObj.countSegments++;
    }
  }
  this.measurePartial(point, geometry);
  dynamicObj.drawing = true;
}, callbackUndo:function(point, feature) {
  var _self = this, undoLabel = function(layer) {
    if (layer) {
      var features = layer.features, lastSegmentIndex = features.length - 1, lastSegment = features[lastSegmentIndex], lastSegmentFromIndex = lastSegment.attributes.from, lastPointIndex = _self.handler.getCurrentPointIndex();
      if (lastSegmentFromIndex >= lastPointIndex) {
        var dynamicObj = _self.dynamicObj;
        layer.destroyFeatures(lastSegment);
        lastSegment = features[lastSegmentIndex - 1];
        dynamicObj.fromIndex = lastSegment.attributes.from;
        dynamicObj.countSegments = features.length;
      }
    }
  };
  undoLabel(this.layerSegments);
  undoLabel(this.layerHeading);
  this.callbackModify(point, feature, true);
}, callbackRedo:function(point, feature) {
  var line = this.handler.line.geometry, currIndex = this.handler.getCurrentPointIndex();
  var dynamicObj = this.dynamicObj;
  this.showLabelSegment(dynamicObj.countSegments, dynamicObj.fromIndex, line.components.slice(dynamicObj.fromIndex, currIndex));
  dynamicObj.fromIndex = this.handler.getCurrentPointIndex() - 1;
  dynamicObj.countSegments++;
  this.callbackModify(point, feature, true);
}, callbackModify:function(point, feature, drawing) {
  if (this.immediate) {
    this.measureImmediate(point, feature, drawing);
  }
  var dynamicObj = this.dynamicObj;
  if (dynamicObj.drawing === false) {
    return;
  }
  var line = this.handler.line.geometry, currIndex = this.handler.getCurrentPointIndex();
  if (!this.handler.freehandMode(this.handler.evt) && dynamicObj.freehand) {
    dynamicObj.fromIndex = currIndex - 1;
    dynamicObj.freehand = false;
    dynamicObj.countSegments++;
  }
  var totalLength = [line.getGeodesicLength(this.map.projection), "m"];
  if (totalLength[0] > 1E3) {
    totalLength[0] = totalLength[0] / 1E3;
    totalLength[1] = "km";
  }
  if (!totalLength[0]) {
    return;
  }
  var positions = this.positions, positionGet = {center:function() {
    var center = feature.geometry.getBounds().clone();
    center.extend(point);
    center = center.getCenterLonLat();
    return[center.lon, center.lat];
  }, initial:function() {
    var initial = line.components[0];
    return[initial.x, initial.y];
  }, start:function() {
    var start = line.components[dynamicObj.fromIndex];
    return[start.x, start.y];
  }, middle:function() {
    var start = line.components[dynamicObj.fromIndex];
    return[(start.x + point.x) / 2, (start.y + point.y) / 2];
  }, end:function() {
    return[point.x, point.y];
  }};
  if (this.layerLength) {
    this.showLabel(this.layerLength, 1, 0, totalLength, positionGet[positions["labelLength"]](), 1);
  }
  if (this.isArea) {
    if (this.layerArea) {
      var area = [feature.geometry.getGeodesicArea(this.map.projection), "m"];
      if (area[0] > 1E3) {
        area[0] = area[0] / 1E3;
        area[1] = "km";
      }
      this.showLabel(this.layerArea, 1, 0, area, positionGet[positions["labelArea"]](), 1);
    }
    if (this.showLabelSegment(1, 0, [line.components[0], line.components[currIndex]])) {
      dynamicObj.countSegments++;
    }
  }
  this.showLabelSegment(dynamicObj.countSegments, dynamicObj.fromIndex, line.components.slice(dynamicObj.fromIndex, currIndex + 1));
}, showLabelSegment:function(labelsNumber, fromIndex, _points) {
  var layerSegments = this.layerSegments, layerHeading = this.layerHeading;
  if (!layerSegments && !layerHeading) {
    return false;
  }
  var points = [], pointsLen = _points.length;
  for (var i = 0;i < pointsLen;i++) {
    points.push(_points[i].clone());
  }
  var totalLength = [(new OpenLayers.Geometry.LineString(points)).getGeodesicLength(this.map.projection), "m"];
  if (totalLength[0] > 1E3) {
    totalLength[0] = totalLength[0] / 1E3;
    totalLength[1] = "km";
  }
  var from = points[0], to = points[pointsLen - 1], segmentLength = totalLength, positions = this.positions, positionGet = {start:function() {
    return[from.x, from.y];
  }, middle:function() {
    return[(from.x + to.x) / 2, (from.y + to.y) / 2];
  }, end:function() {
    return[to.x, to.y];
  }}, created = false;
  if (layerSegments) {
    created = this.showLabel(layerSegments, labelsNumber, fromIndex, segmentLength, positionGet[positions["labelSegments"]](), this.maxSegments);
  }
  if (layerHeading && segmentLength[0] > 0) {
    var heading = Math.atan2(to.y - from.y, to.x - from.x), bearing = 90 - heading * 180 / Math.PI;
    if (bearing < 0) {
      bearing += 360;
    }
    created = created || this.showLabel(layerHeading, labelsNumber, fromIndex, [bearing, "\u00b0"], positionGet[positions["labelHeading"]](), this.maxHeadings);
  }
  return created;
}, showLabel:function(layer, labelsNumber, fromIndex, measure, xy, maxSegments) {
  var featureLabel, featureAux, features = layer.features;
  if (features.length < labelsNumber) {
    if (measure[0] === 0) {
      return false;
    }
    featureLabel = new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Point(xy[0], xy[1]), {from:fromIndex});
    this.setMesureAttributes(featureLabel.attributes, measure);
    layer.addFeatures([featureLabel]);
    if (maxSegments !== null) {
      var hide = features.length - maxSegments - 1;
      if (hide >= 0) {
        featureAux = features[hide];
        featureAux.style = {display:"none"};
        layer.drawFeature(featureAux);
      }
    }
    return true;
  } else {
    featureLabel = features[labelsNumber - 1];
    var geometry = featureLabel.geometry;
    geometry.x = xy[0];
    geometry.y = xy[1];
    geometry.clearBounds();
    this.setMesureAttributes(featureLabel.attributes, measure);
    layer.drawFeature(featureLabel);
    if (maxSegments !== null) {
      var show = features.length - maxSegments;
      if (show >= 0) {
        featureAux = features[show];
        if (featureAux.style) {
          delete featureAux.style;
          layer.drawFeature(featureAux);
        }
      }
    }
    return false;
  }
}, setMesureAttributes:function(attributes, measure) {
  attributes.measure = OpenLayers.Number.format(Number(measure[0].toPrecision(this.accuracy)), null);
  attributes.units = measure[1];
}, CLASS_NAME:"OpenLayers.Control.DynamicMeasure"});
OpenLayers.Control.DynamicMeasure.styles = {"Point":{pointRadius:4, graphicName:"square", fillColor:"white", fillOpacity:1, strokeWidth:1, strokeOpacity:1, strokeColor:"#333333"}, "Line":{strokeWidth:2, strokeOpacity:1, strokeColor:"#666666", strokeDashstyle:"dash"}, "Polygon":{strokeWidth:2, strokeOpacity:1, strokeColor:"#666666", strokeDashstyle:"solid", fillColor:"white", fillOpacity:0.3}, labelSegments:{label:"${measure} ${units}", fontSize:"11px", fontColor:"#800517", fontFamily:"Verdana", labelOutlineColor:"#dddddd", 
labelAlign:"cm", labelOutlineWidth:2}, labelLength:{label:"${measure} ${units}\n", fontSize:"11px", fontWeight:"bold", fontColor:"#800517", fontFamily:"Verdana", labelOutlineColor:"#dddddd", labelAlign:"lb", labelOutlineWidth:3}, labelArea:{label:"${measure}\n${units}\u00b2\n", fontSize:"11px", fontWeight:"bold", fontColor:"#800517", fontFamily:"Verdana", labelOutlineColor:"#dddddd", labelAlign:"cm", labelOutlineWidth:3}, labelHeading:{label:"${measure} ${units}", fontSize:"11px", fontColor:"#800517", 
fontFamily:"Verdana", labelOutlineColor:"#dddddd", labelAlign:"cm", labelOutlineWidth:3}};
OpenLayers.Control.DynamicMeasure.positions = {labelSegments:"middle", labelLength:"end", labelArea:"center", labelHeading:"start"};

