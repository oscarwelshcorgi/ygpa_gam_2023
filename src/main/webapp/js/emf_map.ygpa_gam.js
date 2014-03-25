function removeWindow(rem_win) {
  rem_win.remove();
}
window.removeWindow = removeWindow;
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
var EMD = function($, window, document, undefined) {
  return{go:function(ctr) {
    if (ctr.length == 0 || ctr.substring(ctr.length - 1) != "/") {
      ctr = ctr + "/";
    }
    EMD.context_root = ctr;
    OpenLayers.ProxyHost = EMD.context_root + "proxy.jsp?url=";
    for (var i in EMD.init) {
      EMD.init[i]();
    }
  }, context_root:"/", gis_engine_url:"http://192.168.0.40:8080/G2DataService/GService?", window_seq:0, win_x:80, win_y:40, drag_handle:null, dragging:false, drag_ui:null, userinfo:null, popupCaller:null, popupId:null, map:null, gyhmap:null, wfs:null, lotarea:null, panel:null, draw:null, edit:null, edit_win:null, del:null, info:null, save:null, saveStrategy:null, transaction:{type:null, callbackWindowId:null}, map_panel:null, userLayer:{landCode:null, prtFclty:null}, selectControl:null, maxBounds:new OpenLayers.Bounds(-180, 
  -90, 180, 90), maxRes:0.0439453125, maxZoom:19, module_instance:[], init:{frame_breaker:function() {
    if (window.location !== window.top.location) {
      window.top.location = window.location;
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
          EMD.util.create_window($(this).text(), $(this).data("url"));
          break;
        case "CloseAllWindow":
          $("#desktop").find(".window").each(function() {
            EMD.closeWindow($(this));
          });
          break;
        case "MinimizeAllWindow":
          if ($("div.window:visible").length) {
            $("div.window").hide();
          }
          break;
        case "ShowAllWindow":
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
        $("#desktop").show();
      } else {
        EMD.util.window_flat();
        x.show().addClass("window_stack");
      }
    });
    d.on("mousedown", "div.window", function() {
      EMD.util.window_flat();
      $(this).addClass("window_stack");
    });
    d.on("mouseenter", "div.window", function() {
      $(this).off("mouseenter").draggable({cancel:"a", containment:"parent", handle:"div.window_top", start:function(event, ui) {
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
      }});
    });
    d.on("dblclick", "div.window_top", function() {
      EMD.util.window_resize($(this));
    });
    d.on("dblclick", "div.window_top img", function() {
      $($(this).closest("div.window_top").find("a.window_close").attr("href")).hide("fast");
      $(this).closest("div.window").hide();
      return false;
    });
    d.on("click", "a.window_min", function() {
      EMD.util.window_hide($($(this).attr("href")));
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
    EMD.map = new OpenLayers.Map({div:"desktop", projection:new OpenLayers.Projection("EPSG:5186"), displayProjection:new OpenLayers.Projection("EPSG:5186"), maxExtents:EMD.maxBounds, numZoomLevels:EMD.maxZoom, maxResolution:EMD.maxRes});
    var gmap = new OpenLayers.Layer.Google("Google Streets", {numZoomLevels:20});
    var gsat = new OpenLayers.Layer.Google("Google Satellite", {type:google.maps.MapTypeId.SATELLITE, numZoomLevels:22});
    var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
    renderer = renderer ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;
    var saveStrategy = new OpenLayers.Strategy.Save;
    EMD.lotarea = new OpenLayers.Layer.Vector("\uc9c0\ubc88", {strategies:[new OpenLayers.Strategy.BBOX, saveStrategy], projection:new OpenLayers.Projection("EPSG:5186"), minScale:5E3, maxScale:250, protocol:new OpenLayers.Protocol.WFS({version:"1.1.0", srsName:"EPSG:5186", typeName:"LP_PA_CBND_BUBUN", url:EMD.gis_engine_url, minScale:5E5, maxScale:250}), renderers:renderer});
    EMD.userLayer.prtFclty = new OpenLayers.Layer.Vector("Port Facility Layer", {fillColor:"#073EC9", fillOpacity:0.8, strokeColor:"#414141", strokeOpacity:1, strokeWidth:3, pointRadius:6, pointerEvents:"visiblePainted", styleMap:new OpenLayers.StyleMap({"default":{strokeColor:"#000000", strokeOpacity:1, strokeWidth:1, fillColor:"#303294", fillOpacity:0.5, pointRadius:6, pointerEvents:"visiblePainted", label:"\uc2dc\uc124\ucf54\ub4dc: ${fcltyCd}\n\uc2dc\uc124\uba85: ${name}\n\n\uc18d\uc131: ${type}", 
    fontColor:"${favColor}", fontSize:"12px", fontFamily:"Courier New, monospace", fontWeight:"bold", labelAlign:"cm", labelXOffset:"${xOffset}", labelYOffset:"${yOffset}", labelOutlineColor:"white", labelOutlineWidth:3}, "select":{strokeColor:"#00FF00", strokeOpacity:1, strokeWidth:3, fillColor:"#303294", fillOpacity:1, pointRadius:6, pointerEvents:"visiblePainted", label:"\uc2dc\uc124\ucf54\ub4dc: ${fcltyCd}\n\uc2dc\uc124\uba85: ${name}\n\n\uc18d\uc131: ${type}", fontColor:"${favColor}", fontSize:"12px", 
    fontFamily:"Courier New, monospace", fontWeight:"bold", labelAlign:"cm", labelXOffset:"${xOffset}", labelYOffset:"${yOffset}", labelOutlineColor:"white", labelOutlineWidth:3}}), renderers:OpenLayers.Layer.Vector.prototype.renderers});
    EMD.selectControl = new OpenLayers.Control.SelectFeature(EMD.userLayer.prtFclty, {clickout:true, toggle:false, multiple:true, hover:false, toggleKey:"ctrlKey", multipleKey:"shiftKey"});
    EMD.userLayer.prtFclty.events.register("featureselected", null, function(e) {
      var feature = e.feature;
      var popup = new OpenLayers.Popup.FramedCloud("popup", OpenLayers.LonLat.fromString(feature.geometry.toShortString()), null, "<div style='font-size:.8em'>ID: " + feature.id + "<br>\uc2dc\uc124\ucf54\ub4dc: " + feature.attributes.fcltyCd + "<br> \uc2dc\uc124\uba85 : " + feature.attributes.name + "</div>", null, true);
      feature.popup = popup;
      EMD.map.addPopup(popup);
    });
    EMD.userLayer.prtFclty.events.register("featureunselected", null, function(e) {
      var feature = e.feature;
      map.removePopup(feature.popup);
      feature.popup.destroy();
      feature.popup = null;
    });
    EMD.wfs = new OpenLayers.Layer.Vector("Vector Layer");
    EMD.wfs.setVisibility(true);
    EMD.userLayer.prtFclty.setVisibility(true);
    EMD.lotarea.setVisibility(true);
    EMD.map.addLayers([gsat, gmap, EMD.lotarea, EMD.userLayer.prtFclty, EMD.wfs]);
    EMD.map.addControl(EMD.selectControl);
    EMD.selectControl.activate();
    EMD.map.addControl(new OpenLayers.Control.LayerSwitcher);
    EMD.map.addControl(new OpenLayers.Control.MousePosition({prefix:"\uc88c\ud45c : ", separator:" | ", numDigits:6, displayProjection:new OpenLayers.Projection("EPSG:4326")}));
    EMD.map.addControl(new OpenLayers.Control.Scale);
    EMD.map.setCenter(new OpenLayers.LonLat(14213430, 4147543), 13);
    EMD.map.events.register("mousedown", EMD.map, function(e) {
      EMD.util.clear_active();
      if (EMD.dragging) {
        $(EMD.drag_handle).trigger({type:e.type, altKey:e.altKey, clientX:e.clientX, clientY:e.clientY, ctrlKey:e.ctrlKey, detail:e.detail, keyCode:e.keyCode, layerX:e.layerX, layerY:e.layerY, metaKey:e.metaKey, offsetX:e.offsetX, offsetY:e.offsetY, pageX:e.pageX, pageY:e.pageY, screenX:e.screenX, screenY:e.screenY, shiftKey:e.shiftKey, webkitMovementX:e.webkitMovementX, webkitMovementY:e.webkitMovementY, x:e.x, y:e.y, xy:e.xy});
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
        $(EMD.drag_handle).trigger({type:e.type, altKey:e.altKey, clientX:e.clientX, clientY:e.clientY, ctrlKey:e.ctrlKey, detail:e.detail, keyCode:e.keyCode, layerX:e.layerX, layerY:e.layerY, metaKey:e.metaKey, offsetX:e.offsetX, offsetY:e.offsetY, pageX:e.pageX, pageY:e.pageY, screenX:e.screenX, screenY:e.screenY, shiftKey:e.shiftKey, webkitMovementX:e.webkitMovementX, webkitMovementY:e.webkitMovementY, x:e.x, y:e.y, xy:e.xy});
      }
    });
  }, ui_init:function() {
    $("#sideMenu").sidr("open", "sidr-main");
  }, user_init:function() {
    $.ajax({url:EMD.context_root + "uat/uia/getUserInfo.do", type:"POST", dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:""}).done(function(data) {
      EMD.userinfo = data;
    });
  }, fileupload_init:function() {
    $("#fileupload").fileupload({autoUpload:false});
    $("#fileupload").fileupload("option", "redirect", window.location.href.replace(/\/[^\/]*$/, "/cors/result.html?%s"));
  }}, closeWindow:function(win) {
    var dock_id = "#" + win.attr("id").replace("window", "dock");
    $("#" + win.attr("id")).hide("explode", {}, 800, function() {
      $(this).remove();
    });
    $(dock_id).hide("fast");
    $(dock_id).remove();
  }, gis:{fnRequestWFS:function(filterText, success, failure) {
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
    EMD.draw = new OpenLayers.Control.DrawFeature(EMD.wfs, OpenLayers.Handler.Polygon, {title:"\ucd94\uac00", displayClass:"olControlDrawFeaturePolygon", multi:true});
    EMD.draw._mode = {mode:mode, param:param};
    EMD.draw.events.register("featureadded", EMD.draw, function(e) {
      switch(EMD.draw._mode.mode) {
        case "PF":
          EMD.userLayer.prtFclty.addFeatures(e.feature);
          EMD.wfs.removeAllFeatures();
          break;
        case "AC":
          EMD.userLayer.prtFclty.addFeatures(e.feature);
          EMD.wfs.removeAllFeatures();
          break;
      }
    });
    EMD.edit = new OpenLayers.Control.ModifyFeature(EMD.wfs, {title:"\uc218\uc815", displayClass:"olControlModifyFeature"});
    EMD.del = new DeleteFeature(EMD.wfs, {title:"\uc0ad\uc81c"});
    EMD.save = new OpenLayers.Control.Button({title:"\uc800\uc7a5", trigger:function() {
      if (EMD.edit.feature) {
        EMD.edit.selectControl.unselectAll();
      }
      EMD.saveStrategy.save();
    }, displayClass:"olControlSaveFeatures"});
    EMD.panel = new OpenLayers.Control.Panel({displayClass:"customEditingToolbar", allowDepress:true});
    EMD.panel.addControls([EMD.save, EMD.del, EMD.edit, EMD.draw]);
    EMD.map.addControl(EMD.panel);
  }, cancelFeature:function() {
    EMD.map.removeControl(EMD.panel);
  }, showBupJungDongCode:function(code) {
    var shapeParams = OpenLayers.Util.getParameterString({SERVICE:"WFS", VERSION:"1.1.0", REQUEST:"GetFeature", TYPENAME:"LP_PA_CBND_BUBUN", PROPERTYNAME:"PNU,BCHK,STD_SGGCD,JIBUN,BONBUN,BUBUN", PNU:code, SRSNAME:"EPSG:900913", OUTPUT:"text/xml;subType=gml/3.1.1/profiles/gmlsf/1.0.0/0", EXCEPTIONS:"text/xml"});
    OpenLayers.Request.GET({url:escape("http://map.vworld.kr/js/wfs.do"), params:{SERVICE:"WFS", VERSION:"1.1.0", REQUEST:"GetFeature", APIKEY:"5E7D605D-BD5A-3BEE-8C20-62172A86B4AA", DOMAIN:"http://localhost", TYPENAME:"LP_PA_CBND_BUBUN", emdCd:code, SRSNAME:"EPSG:900913", OUTPUT:"text/xml;subType=gml/3.1.1/profiles/gmlsf/1.0.0/0", EXCEPTIONS:"text/xml"}, callback:function(response) {
      var g = new OpenLayers.Format.GML;
      var features = g.read(response.responseText);
    }});
  }, getLotAreaFeature:function(response) {
    var g = new OpenLayers.Format.GML;
    features = g.read(response.responseText);
  }}, util:{getDate:function(d) {
    if (d == null) {
      d = new Date;
    }
    var s = EMD.util.leftPad(d.getFullYear(), "0", 4) + "-" + EMD.util.leftPad(d.getMonth() + 1, "0", 2) + "-" + EMD.util.leftPad(d.getDate(), "0", 2);
    return s;
  }, getTimeStamp:function(d) {
    if (d == null) {
      d = new Date;
    }
    var s = EMD.util.leftPad(d.getFullYear(), "0", 4) + "-" + EMD.util.leftPad(d.getMonth() + 1, "0", 2) + "-" + EMD.util.leftPad(d.getDate(), "0", 2) + " " + EMD.util.leftPad(d.getHours(), "0", 2) + ":" + EMD.util.leftPad(d.getMinutes(), "0", 2) + ":" + EMD.util.leftPad(d.getSeconds(), "0", 2);
    return s;
  }, leftPad:function(n, c, digits) {
    var zero = "";
    n = n.toString();
    if (n.length < digits) {
      for (var i = 0;i < digits - n.length;i++) {
        zero += c;
      }
    }
    return zero + n;
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
  }, StrToDate:function(dStr) {
    var regiDate = dStr.replace(/-/g, "/");
    return Date.parse(regiDate);
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
    $("#progress_dialog").dialog("open");
  }, hideProgress:function() {
    $("#progress_dialog").dialog("close");
  }, clear_active:function() {
    $("a.active, tr.active").removeClass("active");
    $("ul.menu").hide();
  }, create_window:function(win_title, win_url, argv) {
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
    win.innerHTML = '<div class="abs window_inner">' + '<div class="window_top ui-widget-header">' + '<span class="float_left">' + win_title + '</span> <span class="float_right"> <a href="#' + win.id + '" class="window_min"></a><a href="#' + win.id + '" class="window_resize"></a> <a href="#' + win.id + '" class="window_close"></a></span>' + '</div><div class="abs window_content"></div></div><div class="abs window_bottom ">\uc5ec\uc218\uad11\uc591\ud56d\ub9cc\uacf5\uc0ac \uc9c0\ub3c4\ud504\ub808\uc784\uc6cc\ud06c ver : 2014.03.18..003</div><span class="abs ui-resizable-handle ui-resizable-se"></span>';
    $("#desktop")[0].appendChild(win);
    $(x).addClass("abs");
    $(x).addClass("window");
    if (win_url != undefined) {
      EMD.util.showProgress();
      $(x + " .window_content").load(win_url, {"window_id":x.substring(1), "args":argv}, function(response, status, xhr) {
        EMD.util.hideProgress();
        if (status == "error") {
          var msg = "\uc5d0\ub7ec\uac00 \ubc1c\uc0dd\ud588\uc2b5\ub2c8\ub2e4. \uc5d0\ub7ec\ubc88\ud638: ";
          alert(msg + xhr.status + " " + xhr.statusText);
        } else {
          if (module_instance.getModuleType() == "_EMD_ERROR_MODULE") {
            module_instance.loadComplete();
            alert(module_instance.getMessge());
            EMD.util.last_window_close();
            return;
          }
          var win_id = $("#window_id").val();
          if (win_id == undefined) {
            alert("\uc11c\ubc84 \uc624\ub958\ub85c \uc778\ud558\uc5ec \ud398\uc774\uc9c0\ub97c \ub85c\ub4dc\ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.");
            return;
          }
          var win_id_num = win_id.substring(win_id.lastIndexOf("_") + 1) * 1;
          $("#window_id").remove();
          $("#" + win_id)[0].module = module_instance;
          module_instance.setWindowId(win_id_num);
          EMD.module_instance[win_id_num] = module_instance;
          module_instance.setWindowId(win_id);
          EMD.util.modify_window(win_id);
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
          EMD.win_x = x + 30;
          EMD.win_y = y + 30;
          if (EMD.win_x > 380) {
            EMD.win_x = 80;
            EMD.win_y = 40;
          }
          EMD.util.window_size(win_id, w, h);
          $("#" + win_id).find(".fillHeight").each(function() {
            $(this).parent("div").on("resizeWindow", function(event) {
              var resizeFill = null;
              var items = $(this).children();
              var h = $(this).height();
              for (var i = 0;i < items.length;i++) {
                var item = items[i];
                if ($(item).hasClass("fillHeight")) {
                  resizeFill = item;
                } else {
                  h -= $(item).outerHeight(true);
                }
              }
              if (resizeFill != null) {
                h -= $(resizeFill).outerHeight(true) - $(resizeFill).height();
                if (h > 10) {
                  $(resizeFill).height(h);
                  $(resizeFill).find(".emdTabPanel").each(function() {
                    $(this).tabs("option", "heightStyle", "fill");
                  });
                }
              }
            });
          });
          $("#" + win_id).resizable({create:function(event, ui) {
          }, resize:function(event, ui) {
            EMD.util.window_resized(ui.element);
          }});
          EMD.module_instance[win_id_num].loadComplete();
          EMD.util.window_flat();
          $("#" + win_id).addClass("window_stack").show("drop", {}, 600, function() {
            EMD.util.window_resized($(this));
          });
          EMD.util.clear_active();
        }
      });
    }
  }, window_resized:function(win) {
    win.find("div.window, div.emdPanel, div.emdTabPanel, div.emdTabPage").trigger("resizeWindow");
  }, window_flat:function() {
    $("div.window").removeClass("window_stack");
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
      var dock_id = $(this).attr("id").replace("window", "dock");
      $(this).effect("transfer", {to:"#" + dock_id, className:".ui-effects-transfer"}, 500, function() {
        $(this).removeAttr("style").hide();
      });
    });
  }, window_hide:function(win) {
    var dock_id = win.attr("id").replace("window", "dock");
    win.effect("transfer", {to:"#" + dock_id, className:".ui-effects-transfer"}, 500, function() {
      win.removeAttr("style").hide();
    });
  }, window_close:function(win) {
    var dock_id = win.attr("id").replace("window", "dock");
    win.hide("explode", {}, 800, function() {
      $(this).remove();
    });
    $("#" + dock_id).hide("fast");
    $("#" + dock_id).remove();
  }, last_window_close:function() {
    win = $("#window_" + (EMD.window_seq - 1));
    var dock_id = win.attr("href").replace("window", "dock");
    $(win.attr("href")).hide("explode", {}, 1E3, function() {
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
  }, modify_window:function(win_id) {
    var win = $("#" + win_id);
    EMD.util.modify_id(win_id, $("#" + win_id));
    win.data("_win_id", win_id);
    win.resize(function() {
      win.find(".window_main").each(function() {
        $(this).css("height", win.height() - 54);
      });
    });
    win.find(".emdTabPanel").data("_win_id", win_id);
    win.find(".emdTabPanel").each(function() {
      $(this).find(".emdTab").data("_win_id", $(this).data("_win_id"));
      $(this).find(".emdTab").each(function() {
        $(this).attr("href", $(this).attr("href").replace("#", "#" + $(this).data("_win_id") + "_"));
      });
      $(this).tabs({activate:function(event, ui) {
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
      };
    });
    win.find("button").each(function() {
      var id = $(this).attr("id") == null || $(this).attr("id").length < win_id.length + 1 ? $(this).attr("class") : $(this).attr("id").substring(win_id.length + 1);
      var but = $(this);
      var isbutton = false;
      var icon = null;
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
            case "buttonSave":
              icon = "ui-icon-disk";
              break;
            case "buttonSearch":
              icon = "ui-icon-search";
              break;
            case "popupButton":
              icon = "ui-icon-newwin";
              break;
          }
          if (icon != null && icon.indexOf("ui-icon-") === 0) {
            $(but).button({icons:{primary:icon}, text:true}).click({module:win[0].module, button_id:id}, function(event) {
              event.preventDefault();
              if (event.data.button_id == "loadMap") {
                event.data.module.showMap($(this).attr("data-grid"));
              } else {
                event.data.module.onButtonClick(event.data.button_id);
              }
            });
            isbutton = true;
            return false;
          }
        });
        if (isbutton == false) {
          $(but).button().click({module:win[0].module, button_id:id}, function(event) {
            event.preventDefault();
            if (event.data.button_id == "loadMap") {
              event.data.module.showMap($(this).attr("data-grid"));
            } else {
              event.data.module.onButtonClick(event.data.button_id);
            }
          });
        }
      } else {
        switch($(but).text()) {
          case "\ucd5c\ucd08\uc2e0\uccad":
            icon = "ui-icon-check";
            break;
          case "\uc5f0\uc7a5\uc2e0\uccad":
            icon = "ui-icon-clock";
            break;
          case "\uc2e0\uccad\uc0ad\uc81c":
            icon = "ui-icon-minusthick";
            break;
          case "\uacb0\uc7ac\uc694\uccad":
            icon = "ui-icon-document";
            break;
          case "\uc790\uc0b0\ub4f1\ub85d":
            icon = "ui-icon-document";
            break;
          case "\uc9c0\ub3c4\ubcf4\uae30":
            icon = "ui-icon-flag";
            break;
          case "\uc704\uce58\ub4f1\ub85d":
            icon = "ui-icon-pin-s";
            break;
          case "\uc790\uc0b0\ucd94\uac00":
            icon = "ui-icon-plusthick";
            break;
          case "\uc0ad\uc81c":
            icon = "ui-icon-trash";
            break;
          case "\uc800\uc7a5":
            icon = "ui-icon-disk";
            break;
          case "\ucde8\uc18c":
            icon = "ui-icon-arrowreturnthick-1-e";
            break;
          case "\uc801\uc6a9":
            icon = "ui-icon-check";
            break;
          case "\uc5c5\ub85c\ub4dc":
            icon = "ui-icon-arrowthickstop-1-n";
            break;
          case "\ub2e4\uc6b4\ub85c\ub4dc":
            icon = "ui-icon-arrowthickstop-1-s";
            break;
          default:
            icon = null;
            console.log("unkown button label : " + $(but).text());
            break;
        }
        if (icon != null) {
          $(but).button({icons:{primary:icon}, text:true}).click({module:win[0].module, button_id:id}, function(event) {
            event.preventDefault();
            if (event.data.button_id == "loadMap") {
              event.data.module.showMap($(this).attr("data-grid"));
            } else {
              event.data.module.onButtonClick(event.data.button_id);
            }
          });
        } else {
          $(but).button().click({module:win[0].module, button_id:id}, function(event) {
            event.preventDefault();
            if (event.data.button_id == "loadMap") {
              event.data.module.showMap($(this).attr("data-grid"));
            } else {
              event.data.module.onButtonClick(event.data.button_id);
            }
          });
        }
      }
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
        $(this.attr("id") + "_select")[0].focus();
      });
      $.ajax({url:EMD.context_root + "cmmn/selectDeptCodeOptionsList.do", type:"POST", module:input_item, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"cmd", value:""}], success:function(data) {
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
        }
      }, error:function(XMLHttpRequest, textStatus, errorThrown) {
        try {
          console.log(textStatus);
        } catch (e) {
        }
      }});
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
        $(this.attr("id") + "_select")[0].focus();
      });
      $.ajax({url:EMD.context_root + "cmmn/selectCmmnCdOptionsList.do", type:"POST", module:input_item, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"code_id", value:$(this).data("code-id")}], success:function(data) {
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
        }
      }, error:function(XMLHttpRequest, textStatus, errorThrown) {
        console.log(textStatus);
      }});
    });
    $("#" + win_id).find("input.ygpaNumber, input.ygpaCurrency").each(function() {
      $(this).change(function() {
        str = $(this).number(true, $(this).data("decimal-point") * 1).val();
        $(this).val(str);
      });
      $(this).keypress(function(event) {
        if (event.which != 8 && isNaN(String.fromCharCode(event.which))) {
          event.preventDefault();
        }
      });
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
      $(this).datepicker({showOn:"button", buttonImage:EMD.context_root + "images/egovframework/rte/icon_date_picker.jpg", buttonImageOnly:true, dateFormat:"yy-mm-dd", prevText:"\uc774\uc804 \ub2ec", nextText:"\ub2e4\uc74c \ub2ec", monthNames:["1\uc6d4", "2\uc6d4", "3\uc6d4", "4\uc6d4", "5\uc6d4", "6\uc6d4", "7\uc6d4", "8\uc6d4", "9\uc6d4", "10\uc6d4", "11\uc6d4", "12\uc6d4"], monthNamesShort:["1\uc6d4", "2\uc6d4", "3\uc6d4", "4\uc6d4", "5\uc6d4", "6\uc6d4", "7\uc6d4", "8\uc6d4", "9\uc6d4", "10\uc6d4", 
      "11\uc6d4", "12\uc6d4"], dayNames:["\uc77c", "\uc6d4", "\ud654", "\uc218", "\ubaa9", "\uae08", "\ud1a0"], dayNamesShort:["\uc77c", "\uc6d4", "\ud654", "\uc218", "\ubaa9", "\uae08", "\ud1a0"], dayNamesMin:["\uc77c", "\uc6d4", "\ud654", "\uc218", "\ubaa9", "\uae08", "\ud1a0"], changeMonth:true, changeYear:true});
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
  }}};
}(jQuery, this, this.document);
function EmdModule(width, height) {
  this._parent = null;
  this._window_id = null;
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
EmdModule.prototype.getModuleType = function() {
  return this._moduleType;
};
EmdModule.prototype.setParent = function(parent) {
  this._parent = parent;
};
EmdModule.prototype.showMap = function(gridId) {
  if (gridId != undefined) {
    this._selectedRow = $("#" + gridId).selectedRows();
  }
  EMD.transactionWindow = this._window_id;
  EMD.util.window_hide_all();
};
EmdModule.prototype.getSelect = function(selector) {
  alert("get select = " + this._window_id + " " + selector);
};
EmdModule.prototype.showAlert = function(msg) {
  alert("alert id = " + this._window_id + " " + msg);
};
EmdModule.prototype.setWindowId = function(value) {
  this._window_id = value;
  this._window = $(value);
};
EmdModule.prototype.getWindowId = function() {
  return this._window_id;
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
EmdModule.prototype.buttonClick = function(button_id) {
  alert("button " + button_id + " is clicked");
};
EmdModule.prototype.setSelectedRow = function(row) {
  this._selectedRow = row;
};
EmdModule.prototype.getSelectedRow = function() {
  return this._selectedRow;
};
EmdModule.prototype.$ = function(selector) {
  return $("#" + this._window_id).find(selector.replace("#", "#" + this._window_id + "_"));
};
EmdModule.prototype.makeFormArgs = function(selector) {
  var args = [];
  var win_id = this._window_id;
  this.$(selector).find(":input").not(".frmwrkAuto").each(function() {
    var value = "";
    if ($(this).hasClass("ygpaNumber") || $(this).hasClass("ygpaCurrency")) {
      value = $(this).number(false);
    } else {
      value = $(this).val();
    }
    if ($(this).data("column-id") != null) {
      args[args.length] = {name:$(this).data("column-id"), value:value};
    } else {
      var input_id = $(this).attr("id");
      if (typeof input_id == "string") {
        input_id = input_id.replace(win_id + "_", "");
        args[args.length] = {name:input_id, value:value};
      }
    }
  });
  return args;
};
EmdModule.prototype.makeFormValues = function(selector, obj) {
  var win_id = this._window_id;
  if (typeof obj != "object") {
    return;
  }
  this.$(selector).find(":input").not(".frmwrkAuto").each(function() {
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
        $(this).val($.number(obj[column_id]));
      } else {
        if ($(this).hasClass("ygpaDeptSelect") || $(this).hasClass("ygpaCmmnCd")) {
          $(this).val(obj[column_id]);
          $("#" + $(this).attr("id") + "_select").val(obj[column_id]);
        } else {
          $(this).val(obj[column_id]);
        }
      }
    }
  });
};
EmdModule.prototype.getFormValues = function(selector, obj) {
  var win_id = this._window_id;
  var row = $.extend({}, obj);
  this.$(selector).find(":input").not(".frmwrkAuto").each(function() {
    var value = "";
    if ($(this).hasClass("ygpaNumber") || $(this).hasClass("ygpaCurrency")) {
      value = $(this).number(false).val();
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
EmdModule.prototype.doAction = function(addr, args, retfunc) {
  $.ajax({url:addr, type:"POST", module:this, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:args}).done(function(data) {
    if (retfunc != null) {
      retfunc(this.module, data);
    }
  });
};
EmdModule.prototype.doExecuteDialog = function(dlgId, dlgTitle, dlgAddr, dlgOpts) {
  if ($("#__dialog-modal").length == 0) {
    var win = document.createElement("div");
    win.style.display = "none";
    win.innerHTML = '<div id="__dialog-modal"></div>';
    $("#desktop")[0].appendChild(win);
  }
  EMD.popupcaller = this;
  EMD.popupId = dlgId;
  if (dlgAddr != undefined) {
    $("#__dialog-modal").load(dlgAddr, dlgOpts, function(response, status, xhr) {
      if (status == "error") {
        var msg = "\uc5d0\ub7ec\uac00 \ubc1c\uc0dd\ud588\uc2b5\ub2c8\ub2e4. \uc5d0\ub7ec\ubc88\ud638: ";
        alert(msg + xhr.status + " " + xhr.statusText);
      } else {
        var w, h;
        w = popup_instance.getWidth();
        h = popup_instance.getHeight();
        $("#__dialog-modal").dialog({width:w, height:h, modal:true, closeOnEscape:true, title:dlgTitle});
        popup_instance.setDialog($("#__dialog-modal"));
        popup_instance.setPopupCaller(EMD.popupcaller);
        popup_instance.setPopupId(EMD.popupId);
        $("#__dialog-modal").find("button").each(function() {
          var id = $(this).attr("id");
          var but = $(this);
          var isbutton = false;
          if (but.attr("class") != null) {
            var classes = $(this).attr("class").split(/\s+/);
            $.each(classes, function(index, item) {
              var icon = null;
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
                case "buttonSave":
                  icon = "ui-icon-disk";
                  break;
                case "submit":
                  icon = "ui-icon-check";
                  break;
                case "popupButton":
                  icon = "ui-icon-newwin";
                  break;
              }
              if (icon != null && icon.indexOf("ui-icon-") === 0) {
                $(but).button({icons:{primary:icon}, text:true}).click({module:popup_instance, button_id:id}, function(event) {
                  event.preventDefault();
                  if (event.data.button_id == "loadMap") {
                    event.data.module.showMap($(this).attr("data-grid"));
                  } else {
                    event.data.module.onButtonClick(event.data.button_id);
                  }
                });
                isbutton = true;
                return false;
              }
            });
            if (isbutton == false) {
              $(but).button().click({module:popup_instance, button_id:id}, function(event) {
                event.preventDefault();
                if (event.data.button_id == "loadMap") {
                  event.data.module.showMap($(this).attr("data-grid"));
                } else {
                  event.data.module.onButtonClick(event.data.button_id);
                }
              });
            }
          } else {
            $(but).button().click({module:popup_instance, button_id:id}, function(event) {
              event.preventDefault();
              if (event.data.button_id == "loadMap") {
                event.data.module.showMap($(this).attr("data-grid"));
              } else {
                event.data.module.onButtonClick(event.data.button_id);
              }
            });
          }
        });
        var win_id = "__dialog-modal";
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
          $.ajax({url:EMD.context_root + "cmmn/selectDeptCodeOptionsList.do", type:"POST", module:input_item, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"cmd", value:""}], success:function(data) {
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
            }
          }, error:function(XMLHttpRequest, textStatus, errorThrown) {
            try {
              console.log(textStatus);
            } catch (e) {
            }
          }});
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
          $.ajax({url:EMD.context_root + "cmmn/selectCmmnCdOptionsList.do", type:"POST", module:input_item, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:[{name:"code_id", value:$(this).data("code-id")}], success:function(data) {
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
            }
          }, error:function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(textStatus);
          }});
        });
        $("#" + win_id).find("input.ygpaNumber, input.ygpaCurrency").each(function() {
          $(this).change(function() {
            str = $(this).number(true, $(this).data("decimal-point") * 1).val();
            $(this).val(str);
          });
        });
        $("#" + win_id).find(".emdcal").each(function() {
          $(this).datepicker({showOn:"button", buttonImage:EMD.context_root + "images/egovframework/rte/icon_date_picker.jpg", buttonImageOnly:true, dateFormat:"yy-mm-dd", prevText:"\uc774\uc804 \ub2ec", nextText:"\ub2e4\uc74c \ub2ec", monthNames:["1\uc6d4", "2\uc6d4", "3\uc6d4", "4\uc6d4", "5\uc6d4", "6\uc6d4", "7\uc6d4", "8\uc6d4", "9\uc6d4", "10\uc6d4", "11\uc6d4", "12\uc6d4"], monthNamesShort:["1\uc6d4", "2\uc6d4", "3\uc6d4", "4\uc6d4", "5\uc6d4", "6\uc6d4", "7\uc6d4", "8\uc6d4", "9\uc6d4", "10\uc6d4", 
          "11\uc6d4", "12\uc6d4"], dayNames:["\uc77c", "\uc6d4", "\ud654", "\uc218", "\ubaa9", "\uae08", "\ud1a0"], dayNamesShort:["\uc77c", "\uc6d4", "\ud654", "\uc218", "\ubaa9", "\uae08", "\ud1a0"], dayNamesMin:["\uc77c", "\uc6d4", "\ud654", "\uc218", "\ubaa9", "\uae08", "\ud1a0"], changeMonth:true, changeYear:true});
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
        popup_instance.loadComplete();
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
EmdModule.prototype.addGisAssetsCdMap = function(mode, params) {
  EMD.util.window_hide_all();
  EMD.gis.addFeature(mode, params);
};
EmdModule.prototype.showBupJungDongCodeLocation = function(code) {
  EMD.gis.showBupJungDongCode(code);
};
EmdModule.prototype.getImageUrl = function(filenm) {
  return EMD.context_root + "cmm/getImage.do?physicalFileNm=" + filenm;
};
EmdModule.prototype.requestEApproval = function(params) {
  $.post(EMD.context_root + "cmmn/fclty/openApprovalRequest.do", params, function(data) {
    var WinId = window.open("", "newwin", "width=400,height=300");
    WinId.document.open();
    WinId.document.write(data);
    WinId.document.close();
  }, "json");
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
  this._moduleType = "_EMD_POPUP_MODULE";
}
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
EmdPopupModule.prototype.makeFormArgs = function(selector) {
  var args = [];
  this.$(selector).find(":input").each(function() {
    var value = "";
    if ($(this).hasClass("ygpaNumber") || $(this).hasClass("ygpaCurrency")) {
      value = $(this).number(false);
    } else {
      value = $(this).val();
    }
    var input_id = $(this).attr("id");
    if (typeof input_id == "string") {
      args[args.length] = {name:input_id, value:value};
    }
  });
  return args;
};
EmdPopupModule.prototype.closeDialog = function(msg, value) {
  this._dialog.dialog("destroy");
  this._popupCaller.onClosePopup(this._popupId, msg, value);
};
EmdPopupModule.prototype.cancelDialog = function() {
  this._dialog.dialog("destroy");
  this._popupCaller.onClosePopup(this._popupId, "cancel", null);
};
EmdPopupModule.prototype.$ = function(selector) {
  return $("#__dialog-modal").find(selector);
};
EmdPopupModule.prototype.doAction = function(addr, args, retfunc) {
  $.ajax({url:addr, type:"POST", module:this, dataType:"json", contentType:"application/x-www-form-urlencoded; charset=UTF-8", data:args}).done(function(data) {
    if (retfunc != null) {
      retfunc(this.module, data);
    }
  });
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
    if ($(this).hasClass("ygpaCmmnCd") || $(this).hasClass("ygpaDeptSelect")) {
      label = $("#" + $(this).attr("id") + "_select.frmwrkAuto").find(":selected").text();
    }
    return label;
  };
  $.getSelectedCodeLabel = function() {
    var label = "";
    if ($(this).hasClass("ygpaCmmnCd") || $(this).hasClass("ygpaDeptSelect")) {
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
                if (this.value.length === 0) {
                  data.init = decimals > 0 ? -1 : 0;
                  data.c = decimals > 0 ? -decimals : 0;
                }
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
            if (data.init < 1) {
              start = this.value.length - decimals - (data.init < 0 ? 1 : 0);
              data.c = start - this.value.length;
              data.init = 1;
              $this.data("numFormat", data);
            } else {
              if (start > this.value.length - decimals && code != 8) {
                data.c++;
                $this.data("numFormat", data);
              }
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
          var $this = $(this).data("numFormat", {c:-(decimals + 1), decimals:decimals, thousands_sep:thousands_sep, dec_point:dec_point, regex_dec_num:regex_dec_num, regex_dec:regex_dec, init:false});
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
  $.addFlex = function(t, p) {
    if (t.grid) {
      return false;
    }
    p.newp = false;
    p.rp = 30;
    p.useRp = false;
    p.usepager = false;
    p = $.extend({module:null, height:"auto", width:"auto", striped:true, novstripe:false, minwidth:30, minheight:80, resizable:true, nohresize:false, url:false, method:"POST", errormsg:"\uc11c\ubc84\uc5d0 \uc5f0\uacb0 \ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.", usepager:false, nowrap:true, pageIndex:1, totalCount:1, useRp:false, rp:30, rpOptions:[10, 15, 20, 30, 50], title:false, idProperty:"id", pagestat:"{totalCount} \uc911 {from} \ubd80\ud130 {to} \uae4c\uc9c0 \ud45c\uc2dc \ub428", pagetext:"\ud398\uc774\uc9c0", 
    outof:"of", findtext:"\ucc3e\uae30", params:[], procmsg:"\uc11c\ubc84\uc758 \uc751\ub2f5\uc744 \uae30\ub2e4\ub9ac\ub294 \uc911\uc785\ub2c8\ub2e4...", query:"", qtype:"", nomsg:"\uc870\ud68c\ub41c \ud56d\ubaa9\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.", minColToggle:1, showToggleBtn:false, singleSelect:true, hideOnSubmit:true, autoload:false, blockOpacity:0.5, preProcess:false, addTitleToCell:false, dblClickResize:false, onDragCol:false, onToggleCol:false, onChangeSort:false, onDoubleClick:false, onSuccess:false, 
    onError:false, onSubmit:false, __mw:{datacol:function(p, col, val) {
      var _col = typeof p.datacol[col] == "function" ? p.datacol[col](val) : val;
      if (typeof p.datacol["*"] == "function") {
        return p.datacol["*"](_col);
      } else {
        return _col;
      }
    }}, getGridClass:function(g) {
      return g;
    }, datacol:{}, colResize:true, colMove:true}, p);
    $(t).show().attr({cellPadding:0, cellSpacing:0, border:0}).removeAttr("width");
    var g = {scrollbar:null, handleHelper:null, hset:{}, rePosDrag:function() {
      var cdleft = 0 - this.hDiv.scrollLeft;
      if (this.hDiv.scrollLeft > 0) {
        cdleft -= Math.floor(p.cgwidth / 2);
      }
      $(g.cDrag).css({top:g.hDiv.offsetTop + 1});
      var cdpad = this.cdpad;
      var cdcounter = 0;
      $("div", g.cDrag).hide();
      $("thead tr:first th:visible", this.hDiv).each(function() {
        var n = $("thead tr:first th:visible", g.hDiv).index(this);
        var cdpos = parseInt($("div", this).width());
        if (cdleft == 0) {
          cdleft -= Math.floor(p.cgwidth / 2);
        }
        cdpos = cdpos + cdleft + cdpad;
        if (isNaN(cdpos)) {
          cdpos = 0;
        }
        $("div:eq(" + n + ")", g.cDrag).css({"left":(!browser.mozilla ? cdpos - cdcounter : cdpos) + "px"}).show();
        cdleft = cdpos;
        cdcounter++;
      });
    }, fixHeight:function(newH) {
      newH = false;
      if (!newH) {
        newH = $(g.bDiv).height();
      }
      var hdHeight = $(this.hDiv).height();
      $("div", this.cDrag).each(function() {
        $(this).height(newH + hdHeight);
      });
      var nd = parseInt($(g.nDiv).height(), 10);
      if (nd > newH) {
        $(g.nDiv).height(newH).width(200);
      } else {
        $(g.nDiv).height("auto").width("auto");
      }
      $(g.block).css({height:newH, marginBottom:newH * -1});
      var hrH = g.bDiv.offsetTop + newH;
      if (p.height != "auto" && p.resizable) {
        hrH = g.vDiv.offsetTop;
      }
      $(g.rDiv).css({height:hrH});
    }, dragStart:function(dragtype, e, obj) {
      if (dragtype == "colresize" && p.colResize === true) {
        $(g.nDiv).hide();
        $(g.nBtn).hide();
        var n = $("div", this.cDrag).index(obj);
        var ow = $("th:visible div:eq(" + n + ")", this.hDiv).width();
        $(obj).addClass("dragging").siblings().hide();
        $(obj).prev().addClass("dragging").show();
        this.colresize = {startX:e.pageX, ol:parseInt(obj.style.left, 10), ow:ow, n:n};
        $("body").css("cursor", "col-resize");
      } else {
        if (dragtype == "vresize") {
          var hgo = false;
          $("body").css("cursor", "row-resize");
          if (obj) {
            hgo = true;
            $("body").css("cursor", "col-resize");
          }
          this.vresize = {h:p.height, sy:e.pageY, w:p.width, sx:e.pageX, hgo:hgo};
        } else {
          if (dragtype == "colMove") {
            $(e.target).disableSelection();
            if (p.colMove === true) {
              $(g.nDiv).hide();
              $(g.nBtn).hide();
              this.hset = $(this.hDiv).offset();
              this.hset.right = this.hset.left + $("table", this.hDiv).width();
              this.hset.bottom = this.hset.top + $("table", this.hDiv).height();
              this.dcol = obj;
              this.dcoln = $("th", this.hDiv).index(obj);
              this.colCopy = document.createElement("div");
              this.colCopy.className = "colCopy";
              this.colCopy.innerHTML = obj.innerHTML;
              if (browser.msie) {
                this.colCopy.className = "colCopy ie";
              }
              $(this.colCopy).css({position:"absolute", "float":"left", display:"none", textAlign:obj.align});
              $("body").append(this.colCopy);
              $(this.cDrag).hide();
            }
          }
        }
      }
      $("body").noSelect();
    }, dragMove:function(e) {
      if (this.colresize) {
        var n = this.colresize.n;
        var diff = e.pageX - this.colresize.startX;
        var nleft = this.colresize.ol + diff;
        var nw = this.colresize.ow + diff;
        if (nw > p.minwidth) {
          $("div:eq(" + n + ")", this.cDrag).css("left", nleft);
          this.colresize.nw = nw;
        }
      } else {
        if (this.vresize) {
          var v = this.vresize;
          var y = e.pageY;
          var diff = y - v.sy;
          if (!p.defwidth) {
            p.defwidth = p.width;
          }
          if (p.width != "auto" && (!p.nohresize && v.hgo)) {
            var x = e.pageX;
            var xdiff = x - v.sx;
            var newW = v.w + xdiff;
            if (newW > p.defwidth) {
              this.gDiv.style.width = newW + "px";
              p.width = newW;
            }
          }
          var newH = v.h + diff;
          if ((newH > p.minheight || p.height < p.minheight) && !v.hgo) {
            this.bDiv.style.height = newH + "px";
            p.height = newH;
            this.fixHeight(newH);
          }
          v = null;
        } else {
          if (this.colCopy) {
            $(this.dcol).addClass("thMove").removeClass("thOver");
            if (e.pageX > this.hset.right || (e.pageX < this.hset.left || (e.pageY > this.hset.bottom || e.pageY < this.hset.top))) {
              $("body").css("cursor", "move");
            } else {
              $("body").css("cursor", "pointer");
            }
            $(this.colCopy).css({top:e.pageY + 10, left:e.pageX + 20, display:"block"});
          }
        }
      }
    }, dragEnd:function() {
      if (this.colresize) {
        var n = this.colresize.n;
        var nw = this.colresize.nw;
        $("th:visible div:eq(" + n + ")", this.hDiv).css("width", nw);
        $("tr", this.bDiv).each(function() {
          var $tdDiv = $("td:visible div:eq(" + n + ")", this);
          $tdDiv.css("width", nw);
          g.addTitleToCell($tdDiv);
        });
        this.hDiv.scrollLeft = this.bDiv.scrollLeft;
        $("div:eq(" + n + ")", this.cDrag).siblings().show();
        $(".dragging", this.cDrag).removeClass("dragging");
        this.rePosDrag();
        this.fixHeight();
        this.colresize = false;
        if ($.cookies) {
          var name = p.colModel[n].name;
          $.cookie("flexiwidths/" + name, nw);
        }
      } else {
        if (this.vresize) {
          this.vresize = false;
        } else {
          if (this.colCopy) {
            $(this.colCopy).remove();
            if (this.dcolt !== null) {
              if (this.dcoln > this.dcolt) {
                $("th:eq(" + this.dcolt + ")", this.hDiv).before(this.dcol);
              } else {
                $("th:eq(" + this.dcolt + ")", this.hDiv).after(this.dcol);
              }
              this.switchCol(this.dcoln, this.dcolt);
              $(this.cdropleft).remove();
              $(this.cdropright).remove();
              this.rePosDrag();
              if (p.onDragCol) {
                p.onDragCol(this.dcoln, this.dcolt);
              }
            }
            this.dcol = null;
            this.hset = null;
            this.dcoln = null;
            this.dcolt = null;
            this.colCopy = null;
            $(".thMove", this.hDiv).removeClass("thMove");
            $(this.cDrag).show();
          }
        }
      }
      $("body").css("cursor", "default");
      $("body").noSelect(false);
    }, toggleCol:function(cid, visible) {
      var ncol = $("th[axis='col" + cid + "']", this.hDiv)[0];
      var n = $("thead th", g.hDiv).index(ncol);
      var cb = $("input[value=" + cid + "]", g.nDiv)[0];
      if (visible == null) {
        visible = ncol.hidden;
      }
      if ($("input:checked", g.nDiv).length < p.minColToggle && !visible) {
        return false;
      }
      if (visible) {
        ncol.hidden = false;
        $(ncol).show();
        cb.checked = true;
      } else {
        ncol.hidden = true;
        $(ncol).hide();
        cb.checked = false;
      }
      $("tbody tr", t).each(function() {
        if (visible) {
          $("td:eq(" + n + ")", this).show();
        } else {
          $("td:eq(" + n + ")", this).hide();
        }
      });
      this.rePosDrag();
      if (p.onToggleCol) {
        p.onToggleCol(cid, visible);
      }
      return visible;
    }, switchCol:function(cdrag, cdrop) {
      $("tbody tr", t).each(function() {
        if (cdrag > cdrop) {
          $("td:eq(" + cdrop + ")", this).before($("td:eq(" + cdrag + ")", this));
        } else {
          $("td:eq(" + cdrop + ")", this).after($("td:eq(" + cdrag + ")", this));
        }
      });
      if (cdrag > cdrop) {
        $("tr:eq(" + cdrop + ")", this.nDiv).before($("tr:eq(" + cdrag + ")", this.nDiv));
      } else {
        $("tr:eq(" + cdrop + ")", this.nDiv).after($("tr:eq(" + cdrag + ")", this.nDiv));
      }
      if (browser.msie && browser.version < 7) {
        $("tr:eq(" + cdrop + ") input", this.nDiv)[0].checked = true;
      }
      this.hDiv.scrollLeft = this.bDiv.scrollLeft;
    }, scroll:function() {
      this.hDiv.scrollLeft = this.bDiv.scrollLeft;
      this.rePosDrag();
    }, addData:function(data) {
      if (p.preProcess) {
        data = p.preProcess(p.module, data);
      }
      $(".pReload", this.pDiv).removeClass("loading");
      this.loading = false;
      if (!data) {
        $(".pPageStat", this.pDiv).html(p.errormsg);
        if (p.onSuccess) {
          p.onSuccess(this);
        }
        return false;
      }
      p.totalCount = data.totalCount;
      if (p.totalCount === 0) {
        $("tr, a, td, div", t).unbind();
        $(t).empty();
        p.pages = 1;
        p.pageIndex = 1;
        this.buildpager();
        $(".pPageStat", this.pDiv).html(p.nomsg);
        if (p.onSuccess) {
          p.onSuccess(this);
        }
        return false;
      }
      p.pages = Math.ceil(p.totalCount / p.rp);
      this.buildpager();
      var tbody = document.createElement("tbody");
      this.addrows(data.resultList, tbody);
      $("tr", t).unbind();
      $(t).empty();
      $(t).append(tbody);
      this.addCellProp();
      this.addRowProp();
      this.rePosDrag();
      p.data = data.resultList;
      tbody = null;
      data = null;
      i = null;
      if (p.onSuccess) {
        p.onSuccess(this);
      }
      if (p.hideOnSubmit) {
        $(g.block).remove();
      }
      this.hDiv.scrollLeft = this.bDiv.scrollLeft;
      if (browser.opera) {
        $(t).css("visibility", "visible");
      }
      g.resetScrollBarValue(g, t);
      g.sizeScrollbar(g, t);
      g.reflowScrollBarContent(g, t);
    }, appendData:function(data) {
      if (p.preProcess) {
        data = p.preProcess(p.module, data);
      }
      $(".pReload", this.pDiv).removeClass("loading");
      this.loading = false;
      if (!data) {
        $(".pPageStat", this.pDiv).html(p.errormsg);
        if (p.onSuccess) {
          p.onSuccess(this);
        }
        return false;
      }
      var tbody = t.tBodies[0];
      this.addrows(data.resultList, tbody);
      this.addCellProp();
      this.addRowProp();
      this.rePosDrag();
      p.data = data.resultList;
      tbody = null;
      data = null;
      i = null;
      if (p.onSuccess) {
        p.onSuccess(this);
      }
      if (p.hideOnSubmit) {
        $(g.block).remove();
      }
      this.hDiv.scrollLeft = this.bDiv.scrollLeft;
      if (browser.opera) {
        $(t).css("visibility", "visible");
      }
      g.sizeScrollbar(g, t);
      g.reflowScrollBarContent(g, t);
    }, changeSort:function(th) {
      if (this.loading) {
        return true;
      }
      $(g.nDiv).hide();
      $(g.nBtn).hide();
      if (p.sortname == $(th).attr("abbr")) {
        if (p.sortorder == "asc") {
          p.sortorder = "desc";
        } else {
          p.sortorder = "asc";
        }
      }
      $(th).addClass("sorted").siblings().removeClass("sorted");
      $(".sdesc", this.hDiv).removeClass("sdesc");
      $(".sasc", this.hDiv).removeClass("sasc");
      $("div", th).addClass("s" + p.sortorder);
      p.sortname = $(th).attr("abbr");
      if (p.onChangeSort) {
        p.onChangeSort(p.sortname, p.sortorder);
      } else {
        this.populate();
      }
    }, buildpager:function() {
      $(".pcontrol input", this.pDiv).val(p.pageIndex);
      $(".pcontrol span", this.pDiv).html(p.pages);
      var r1 = (p.pageIndex - 1) * p.rp + 1;
      var r2 = r1 + p.rp - 1;
      if (p.totalCount < r2) {
        r2 = p.totalCount;
      }
      var stat = p.pagestat;
      stat = stat.replace(/{from}/, r1);
      stat = stat.replace(/{to}/, r2);
      stat = stat.replace(/{totalCount}/, p.totalCount);
      $(".pPageStat", this.pDiv).html(stat);
    }, addrows:function(rows, tbody) {
      $.each(rows, function(i, row) {
        var tr = document.createElement("tr");
        var jtr = $(tr);
        if (row.name) {
          tr.name = row.name;
        }
        if (row.color) {
          jtr.css("background", row.color);
        } else {
          if (i % 2 && p.striped) {
            tr.className = "erow";
          }
        }
        if (row[p.idProperty]) {
          tr.id = "row" + row[p.idProperty];
          jtr.attr("data-id", row[p.idProperty]);
        }
        $("thead tr:first th", g.hDiv).each(function() {
          var td = document.createElement("td");
          var idx = $(this).attr("axis").substr(3);
          td.align = this.align;
          if (typeof row.cell == "undefined") {
            var iHTML = row[p.colModel[idx].name];
            if (p.colModel[idx].displayFormat != undefined) {
              switch(p.colModel[idx].displayFormat) {
                case "number":
                  iHTML = EMD.util.addCommas(iHTML);
                  break;
                case "select":
                  iHTML = '<SELECT name="' + p.colModel[idx].name + "_" + i + '">';
                  for (var j = 0;j < p.colModel[idx].displayOption.length;j++) {
                    var selected = p.colModel[idx].displayOption[j].value == row[p.colModel[idx].name] ? "selected" : "";
                    iHTML = iHTML + '<OPTION value="' + p.colModel[idx].displayOption[j].value + '"' + selected + ">" + p.colModel[idx].displayOption[j].name + "</OPTION>";
                  }
                  iHTML = iHTML + "</SELECT>";
                  break;
                case "button":
                  if (p.colModel[idx].displayOption == null) {
                    iHTML = '<BUTTON name="' + p.colModel[idx].name + "_" + i + '"></BUTTON>';
                  } else {
                    iHTML = '<BUTTON name="' + p.colModel[idx].name + "_" + i + '"';
                    if (p.colModel[idx].displayOption.className != undefined) {
                      iHTML = iHTML + ' class="' + p.colModel[idx].displayOption.className + '"';
                    }
                    iHTML = iHTML + ">";
                    if (p.colModel[idx].displayOption.label != undefined) {
                      iHTML = iHTML + p.colModel[idx].displayOption.label;
                    }
                    iHTML = iHTML + "</BUTTON>";
                  }
                  break;
                case "checkbox":
                  if (p.colModel[idx].displayOption == null) {
                    iHTML = '<INPUT type="checkbox" name="' + p.colModel[idx].name + "_" + i + '" />';
                  } else {
                    iHTML = '<INPUT type="checkbox" name="' + p.colModel[idx].name + "_" + i + '"';
                    if (p.colModel[idx].displayOption.className != undefined) {
                      iHTML = iHTML + ' class="' + p.colModel[idx].displayOption.className + '"';
                    }
                    iHTML = iHTML + " />";
                    if (p.colModel[idx].displayOption.label != undefined) {
                      iHTML = iHTML + p.colModel[idx].displayOption.label;
                    }
                  }
                  break;
                case "date":
                  break;
              }
              if (typeof iHTML != undefined) {
                iHTML = iHTML !== null ? iHTML : "";
              } else {
                iHTML = "";
              }
              td.innerHTML = iHTML;
              $(td).find("select").change({"result":row[p.colModel[idx].name], "p":p, "g":g}, function(event) {
                event.preventDefault();
                var spl = $(this).attr("name").lastIndexOf("_");
                event.data.result[$(this).attr("name").substring(0, spl)] = $(this).val();
                $(this).closest("table").trigger("onSelectChanged", [event.data.p.module, event.data.result, event.data.g, event.data.p]);
              });
              $(td).find(":button").on("click", {"result":row[p.colModel[idx].name], "p":p, "g":g}, function(event) {
                event.preventDefault();
                var spl = $(this).attr("name").lastIndexOf("_");
                $(this).closest("table").trigger("onButtonClicked", [event.data.p.module, $(this).attr("name").substring(0, spl), event.data.result, event.data.g, event.data.p]);
              });
              $(td).find("input:checkbox").on("change", {"result":row[p.colModel[idx].name], "p":p, "g":g}, function(event) {
                event.preventDefault();
                var spl = $(this).attr("name").lastIndexOf("_");
                var selectedValue = $(this).is(":checked");
                event.data.result[$(this).attr("name").substring(0, spl)] = selectedValue;
                $(this).closest("table").trigger("onCheckBoxChanged", [event.data.p.module, event.data.result, event.data.g, event.data.p]);
              });
            } else {
              if (typeof iHTML != undefined) {
                iHTML = iHTML !== null ? iHTML : "";
              } else {
                iHTML = "";
              }
              td.innerHTML = iHTML;
            }
          } else {
            var iHTML = "";
            if (typeof row.cell[idx] != undefined) {
              iHTML = row.cell[idx] !== null ? row.cell[idx] : "";
            } else {
              iHTML = row.cell[p.colModel[idx].name];
            }
            if (p.colModel[idx].displayFormat != undefined) {
              switch(p.colModel[idx].displayFormat) {
                case "number":
                  iHTML = EMD.util.addCommas(iHTML);
                  break;
                case "date":
                  break;
              }
            }
            td.innerHTML = p.__mw.datacol(p, $(this).attr("abbr"), iHTML);
          }
          var offs = td.innerHTML.indexOf("<BGCOLOR=");
          if (offs > 0) {
            $(td).css("background", text.substr(offs + 7, 7));
          }
          $(td).attr("abbr", $(this).attr("abbr"));
          $(tr).append(td);
          td = null;
        });
        if ($("thead", this.gDiv).length < 1) {
          for (idx = 0;idx < row.cell.length;idx++) {
            var td = document.createElement("td");
            if (typeof row.cell[idx] != "undefined") {
              td.innerHTML = row.cell[idx] != null ? row.cell[idx] : "";
            } else {
              td.innerHTML = row.cell[p.colModel[idx].name];
            }
            $(tr).append(td);
            td = null;
          }
        }
        $(tbody).append(tr);
        tr = null;
      });
    }, populate:function() {
      if (this.loading) {
        alert("\ub370\uc774\ud130\ub97c \ub85c\ub529\uc911\uc785\ub2c8\ub2e4. \uc7a5\uc2dc\uac04 \uc5f0\uacb0 \ub418\uc9c0 \uc54a\uc744 \uacbd\uc6b0 \ucc3d\uc744 \ub2eb\uace0 \ub2e4\uc2dc \uc2e4\ud589 \ud558\uc2dc\uae30 \ubc14\ub78d\ub2c8\ub2e4.");
        return true;
      }
      if (p.onSubmit) {
        var gh = p.onSubmit();
        if (!gh) {
          return false;
        }
      }
      this.loading = true;
      if (!p.url) {
        return false;
      }
      $(".pPageStat", this.pDiv).html(p.procmsg);
      $(".pReload", this.pDiv).addClass("loading");
      $(g.block).css({top:g.bDiv.offsetTop});
      if (p.hideOnSubmit) {
        $(this.gDiv).prepend(g.block);
      }
      if (browser.opera) {
        $(t).css("visibility", "hidden");
      }
      if (!p.newp) {
        p.newp = 1;
      } else {
        p.newp++;
      }
      if (p.pageIndex > p.pages) {
        p.pageIndex = p.pages;
      }
      var param = [{name:"pageIndex", value:p.newp}, {name:"pageUnit", value:p.rp}, {name:"recordCountPerPage", value:p.rp}];
      if (p.params.length) {
        for (var pi = 0;pi < p.params.length;pi++) {
          param[param.length] = p.params[pi];
        }
      }
      $.ajax({type:p.method, url:p.url, data:param, dataType:"json", success:function(data) {
        if (data.resultCode != null && data.resultCode != 0) {
          if (data.resultCode == 1) {
            alert(data.resultMsg);
            location.reload();
          }
        } else {
          if (p.newp == 1) {
            g.addData(data);
          } else {
            g.appendData(data);
          }
        }
      }, error:function(XMLHttpRequest, textStatus, errorThrown) {
        try {
          if (p.onError) {
            p.onError(XMLHttpRequest, textStatus, errorThrown);
          }
        } catch (e) {
        }
      }});
    }, doSearch:function() {
      p.query = $("input[name=q]", g.sDiv).val();
      p.qtype = $("select[name=qtype]", g.sDiv).val();
      p.newp = 1;
      this.populate();
    }, changePage:function(ctype) {
      if (this.loading) {
        return true;
      }
      switch(ctype) {
        case "first":
          p.newp = 1;
          break;
        case "prev":
          if (p.pageIndex > 1) {
            p.newp = parseInt(p.pageIndex, 10) - 1;
          }
          break;
        case "next":
          if (p.pageIndex < p.pages) {
            p.newp = parseInt(p.pageIndex, 10) + 1;
          }
          break;
        case "last":
          p.newp = p.pages;
          break;
        case "input":
          var nv = parseInt($(".pcontrol input", this.pDiv).val(), 10);
          if (isNaN(nv)) {
            nv = 1;
          }
          if (nv < 1) {
            nv = 1;
          } else {
            if (nv > p.pages) {
              nv = p.pages;
            }
          }
          $(".pcontrol input", this.pDiv).val(nv);
          p.newp = nv;
          break;
      }
      if (p.newp == p.pageIndex) {
        return false;
      }
      if (p.onChangePage) {
        p.onChangePage(p.newp);
      } else {
        this.populate();
      }
    }, addCellProp:function() {
      $("tbody tr td", g.bDiv).each(function() {
        if ($(this).hasClass("_D_")) {
          return true;
        }
        var tdDiv = document.createElement("div");
        var n = $("td", $(this).parent()).index(this);
        var pth = $("th:eq(" + n + ")", g.hDiv).get(0);
        if (pth != null) {
          if (p.sortname == $(pth).attr("abbr") && p.sortname) {
            this.className = "sorted";
          }
          $(tdDiv).css({textAlign:pth.align, width:$("div:first", pth)[0].style.width});
          if (pth.hidden) {
            $(this).css("display", "none");
          }
        }
        if (p.nowrap == false) {
          $(tdDiv).css("white-space", "normal");
        }
        if (this.innerHTML == "") {
          this.innerHTML = "&nbsp;";
        }
        tdDiv.innerHTML = this.innerHTML;
        var prnt = $(this).parent()[0];
        var pid = false;
        if (prnt.id) {
          pid = prnt.id.substr(3);
        }
        if (pth != null) {
          if (pth.process) {
            pth.process(tdDiv, pid);
          }
        }
        $(this).empty().append(tdDiv).removeAttr("width");
        g.addTitleToCell(tdDiv);
        $(this).addClass("_D_");
      });
    }, getCellDim:function(obj) {
      var ht = parseInt($(obj).height(), 10);
      var pht = parseInt($(obj).parent().height(), 10);
      var wt = parseInt(obj.style.width, 10);
      var pwt = parseInt($(obj).parent().width(), 10);
      var top = obj.offsetParent.offsetTop;
      var left = obj.offsetParent.offsetLeft;
      var pdl = parseInt($(obj).css("paddingLeft"), 10);
      var pdt = parseInt($(obj).css("paddingTop"), 10);
      return{ht:ht, wt:wt, top:top, left:left, pdl:pdl, pdt:pdt, pht:pht, pwt:pwt};
    }, addRowProp:function() {
      $("button", g.bDiv).on("click", function(e) {
        e.stopPropagation();
        $(this).closest("table").trigger("onButtonClick", [p.module, row, g, p]);
      });
      $("tbody tr", g.bDiv).on("click", function(e) {
        var obj = e.target || e.srcElement;
        if (obj.href || obj.type) {
          return true;
        }
        if (e.ctrlKey || e.metaKey) {
          return;
        }
        if (p.singleSelect && !g.multisel) {
          $(this).siblings().removeClass("trSelected");
        }
        if ($(this).hasClass("trSelected")) {
        } else {
          $(this).addClass("trSelected");
          $(this).closest("table").trigger("onItemSelected", [p.module, p.data[this.sectionRowIndex], g, p]);
        }
      }).on("mousedown", function(e) {
        if (e.shiftKey) {
          if (!p.singleSelect) {
            return;
          }
          $(this).toggleClass("trSelected");
          g.multisel = true;
          this.focus();
          $(g.gDiv).noSelect();
        }
        if (e.ctrlKey || e.metaKey) {
          if (!p.singleSelect) {
            return;
          }
          $(this).toggleClass("trSelected");
          g.multisel = true;
          this.focus();
        }
      }).on("mouseup", function(e) {
        if (g.multisel && !(e.ctrlKey || e.metaKey)) {
          g.multisel = false;
          $(g.gDiv).noSelect(false);
        }
      }).on("dblclick", function(event) {
        $(this).addClass("trSelected");
        $(this).closest("table").trigger("onItemDoubleClick", [p.module, p.data[this.sectionRowIndex], g, p]);
      }).hover(function(e) {
        if (g.multisel && e.shiftKey) {
          $(this).toggleClass("trSelected");
        }
      }, function() {
      });
      if (browser.msie && browser.version < 7) {
        $(this).hover(function() {
          $(this).addClass("trOver");
        }, function() {
          $(this).removeClass("trOver");
        });
      }
    }, combo_flag:true, combo_resetIndex:function(selObj) {
      if (this.combo_flag) {
        selObj.selectedIndex = 0;
      }
      this.combo_flag = true;
    }, combo_doSelectAction:function(selObj) {
      eval(selObj.options[selObj.selectedIndex].value);
      selObj.selectedIndex = 0;
      this.combo_flag = false;
    }, addTitleToCell:function(tdDiv) {
      if (p.addTitleToCell) {
        var $span = $("<span />").css("display", "none"), $div = tdDiv instanceof jQuery ? tdDiv : $(tdDiv), div_w = $div.outerWidth(), span_w = 0;
        $("body").children(":first").before($span);
        $span.html($div.html());
        $span.css("font-size", "" + $div.css("font-size"));
        $span.css("padding-left", "" + $div.css("padding-left"));
        span_w = $span.innerWidth();
        $span.remove();
        if (span_w > div_w) {
          $div.attr("title", $div.text());
        } else {
          $div.removeAttr("title");
        }
      }
    }, autoResizeColumn:function(obj) {
      if (!p.dblClickResize) {
        return;
      }
      var n = $("div", this.cDrag).index(obj), $th = $("th:visible div:eq(" + n + ")", this.hDiv), ol = parseInt(obj.style.left, 10), ow = $th.width(), nw = 0, nl = 0, $span = $("<span />");
      $("body").children(":first").before($span);
      $span.html($th.html());
      $span.css("font-size", "" + $th.css("font-size"));
      $span.css("padding-left", "" + $th.css("padding-left"));
      $span.css("padding-right", "" + $th.css("padding-right"));
      nw = $span.width();
      $("tr", this.bDiv).each(function() {
        var $tdDiv = $("td:visible div:eq(" + n + ")", this), spanW = 0;
        $span.html($tdDiv.html());
        $span.css("font-size", "" + $tdDiv.css("font-size"));
        $span.css("padding-left", "" + $tdDiv.css("padding-left"));
        $span.css("padding-right", "" + $tdDiv.css("padding-right"));
        spanW = $span.width();
        nw = spanW > nw ? spanW : nw;
      });
      $span.remove();
      nw = p.minWidth > nw ? p.minWidth : nw;
      nl = ol + (nw - ow);
      $("div:eq(" + n + ")", this.cDrag).css("left", nl);
      this.colresize = {nw:nw, n:n};
      g.dragEnd();
    }, pager:0, contentSizeReset:function(g, t) {
      g.resetScrollBarValue(g, t);
      g.sizeScrollbar(g, t);
      g.reflowScrollBarContent(g, t);
    }, sizeScrollbar:function(g, t) {
      if (g == null || g.scrollbar == null) {
        return;
      }
      var ch = $(g.cDiv).height() - 46;
      var th = $(t).height();
      var remainder = th - ch;
      var proportion = ch / th;
      var handleSize = proportion * g.scrollbar.height();
      g.scrollbar.find(".ui-slider-handle").css({height:handleSize, "margin-top":0});
      g.scrollbar.slider("value", 100);
      g.handleHelper.css("top", handleSize + "px");
      g.handleHelper.height(g.scrollbar.height() - handleSize);
    }, resetScrollBarValue:function(g, t) {
      if (g == null || g.scrollbar == null) {
        return;
      }
      var ch = $(g.cDiv).height() - 46;
      var th = $(t).height();
      var remainder = th - ch;
      if (th < ch) {
        $(g.cDiv).find(".scroll-bar-wrap").hide();
        return;
      } else {
        $(g.cDiv).find(".scroll-bar-wrap").show();
      }
      var leftVal = $(t).css("margin-top") === "auto" ? 0 : parseInt($(t).css("margin-top"));
      var percentage = Math.round(leftVal / remainder * 100);
      g.scrollbar.slider("value", 100 * percentage);
    }, reflowScrollBarContent:function(g, t) {
      if (g == null || g.scrollbar == null) {
        return;
      }
      var ch = $(g.cDiv).height() - 46;
      var th = $(t).height();
      if (th < ch) {
        return;
      }
      var showing = th + parseInt($(t).css("margin-top"), 10);
      var gap = ch - showing;
      if (gap > 0) {
        $(t).css("margin-top", parseInt($(t).css("margin-top"), 10) + gap);
      }
    }, addScrollBar:function(g, t) {
      $(g.cDiv).append('<div class="scroll-bar-wrap ui-widget-content ui-corner-all">\t\t\t\t\t    <div class="scroll-bar"></div>\t\t\t\t\t    </div>');
      g.scrollbar = $(g.cDiv).find(".scroll-bar").slider({min:0, max:100, slide:function(event, ui) {
        var ch = $(g.cDiv).height() - 46;
        var th = $(t).height();
        if (th > ch) {
          var mt = Math.round((100 - ui.value) / 100 * (ch - th));
          $(t).css("margin-top", mt + "px");
          g.reflowScrollBarContent(g, t);
          if (ui.value == 0) {
            g.checkEndOfList(g, t);
          }
        } else {
          $(t).css("margin-top", 0);
        }
      }, change:function(event, ui) {
        var ch = $(g.cDiv).height() - 46;
        var th = $(t).height();
        var mt = 0;
        if (th > ch) {
          mt = Math.round((100 - ui.value) / 100 * (ch - th));
          $(t).css("margin-top", mt + "px");
          g.reflowScrollBarContent(g, t);
        } else {
          $(t).css("margin-top", 0);
        }
      }, orientation:"vertical"});
      var handle = g.scrollbar.find(".ui-slider-handle");
      g.handleHelper = handle.mousedown(function(e) {
      }).mouseup(function() {
      }).append("<span class='ui-icon ui-icon-grip-dotted-horizontal'></span>").wrap("<div class='ui-handle-helper-parent'></div>").parent();
      $(g.bDiv).css("overflow-y", "hidden");
      $(g.gDiv).resize({g:g, t:t}, function(e) {
        g.resetScrollBarValue(e.data.g, e.data.t);
        g.sizeScrollbar(e.data.g, e.data.t);
        g.reflowScrollBarContent(e.data.g, e.data.t);
      });
      g.resetScrollBarValue(g, t);
      g.sizeScrollbar(g, t);
      setTimeout(g.sizeScrollbar, 10, {g:g, t:t});
      g.reflowScrollBarContent(g, t);
    }, checkEndOfList:function(g, t) {
      if (t.p.pageIndex < t.p.pages) {
        t.p.pageIndex++;
        g.populate();
      }
    }};
    g = p.getGridClass(g);
    if (p.colModel) {
      thead = document.createElement("thead");
      var tr = document.createElement("tr");
      for (var i = 0;i < p.colModel.length;i++) {
        var cm = p.colModel[i];
        var th = document.createElement("th");
        $(th).attr("axis", "col" + i);
        if (cm) {
          if ($.cookies) {
            var cookie_width = "flexiwidths/" + cm.name;
            if ($.cookie(cookie_width) != undefined) {
              cm.width = $.cookie(cookie_width);
            }
          }
          if (cm.display != undefined) {
            th.innerHTML = cm.display;
          }
          if (cm.name) {
            $(th).attr("abbr", cm.name);
          }
          if (cm.align) {
            th.align = cm.align;
          }
          if (cm.width) {
            $(th).attr("width", cm.width);
          }
          if ($(cm).attr("hide")) {
            th.hidden = true;
          }
          if (cm.process) {
            th.process = cm.process;
          }
        } else {
          th.innerHTML = "";
          $(th).attr("width", 30);
        }
        $(tr).append(th);
      }
      $(thead).append(tr);
      $(t).prepend(thead);
    }
    g.gDiv = document.createElement("div");
    g.mDiv = document.createElement("div");
    g.cDiv = document.createElement("div");
    g.hDiv = document.createElement("div");
    g.bDiv = document.createElement("div");
    g.vDiv = document.createElement("div");
    g.rDiv = document.createElement("div");
    g.cDrag = document.createElement("div");
    g.block = document.createElement("div");
    g.nDiv = document.createElement("div");
    g.nBtn = document.createElement("div");
    g.iDiv = document.createElement("div");
    g.tDiv = document.createElement("div");
    g.sDiv = document.createElement("div");
    g.pDiv = document.createElement("div");
    if (p.colResize === false) {
      $(g.cDrag).css("display", "none");
    }
    if (!p.usepager) {
      g.pDiv.style.display = "none";
    }
    g.hTable = document.createElement("table");
    g.gDiv.className = t.className;
    t.className = "";
    $(t).css("display", "inline-block");
    $(g.gDiv).addClass("flexigrid");
    if (p.width != "auto") {
      g.gDiv.style.width = p.width + (isNaN(p.width) ? "" : "px");
    }
    if (browser.msie) {
      $(g.gDiv).addClass("ie");
    }
    if (p.novstripe) {
      $(g.gDiv).addClass("novstripe");
    }
    $(t).before(g.gDiv);
    $(g.gDiv).append(t);
    if (p.buttons) {
      g.tDiv.className = "tDiv";
      var tDiv2 = document.createElement("div");
      tDiv2.className = "tDiv2";
      for (var i = 0;i < p.buttons.length;i++) {
        var btn = p.buttons[i];
        if (!btn.separator) {
          var btnDiv = document.createElement("div");
          btnDiv.className = "fbutton";
          btnDiv.innerHTML = "<div><span>" + (btn.hidename ? "&nbsp;" : btn.name) + "</span></div>";
          if (btn.bclass) {
            $("span", btnDiv).addClass(btn.bclass).css({paddingLeft:20});
          }
          if (btn.bimage) {
            $("span", btnDiv).css("background", "url(" + btn.bimage + ") no-repeat center left");
          }
          $("span", btnDiv).css("paddingLeft", 20);
          if (btn.tooltip) {
            $("span", btnDiv)[0].title = btn.tooltip;
          }
          btnDiv.onpress = btn.onpress;
          btnDiv.name = btn.name;
          if (btn.id) {
            btnDiv.id = btn.id;
          }
          if (btn.onpress) {
            $(btnDiv).click(function() {
              this.onpress(this.id || this.name, g.gDiv);
            });
          }
          $(tDiv2).append(btnDiv);
          if (browser.msie && browser.version < 7) {
            $(btnDiv).hover(function() {
              $(this).addClass("fbOver");
            }, function() {
              $(this).removeClass("fbOver");
            });
          }
        } else {
          $(tDiv2).append("<div class='btnseparator'></div>");
        }
      }
      $(g.tDiv).append(tDiv2);
      $(g.tDiv).append("<div style='clear:both'></div>");
      $(g.gDiv).prepend(g.tDiv);
    }
    g.hDiv.className = "hDiv";
    if (p.combobuttons && $(g.tDiv2)) {
      var btnDiv = document.createElement("div");
      btnDiv.className = "fbutton";
      var tSelect = document.createElement("select");
      $(tSelect).change(function() {
        g.combo_doSelectAction(tSelect);
      });
      $(tSelect).click(function() {
        g.combo_resetIndex(tSelect);
      });
      tSelect.className = "cselect";
      $(btnDiv).append(tSelect);
      for (i = 0;i < p.combobuttons.length;i++) {
        var btn = p.combobuttons[i];
        if (!btn.separator) {
          var btnOpt = document.createElement("option");
          btnOpt.innerHTML = btn.name;
          if (btn.bclass) {
            $(btnOpt).addClass(btn.bclass).css({paddingLeft:20});
          }
          if (btn.bimage) {
            $(btnOpt).css("background", "url(" + btn.bimage + ") no-repeat center left");
          }
          $(btnOpt).css("paddingLeft", 20);
          if (btn.tooltip) {
            $(btnOpt)[0].title = btn.tooltip;
          }
          if (btn.onpress) {
            btnOpt.value = btn.onpress;
          }
          $(tSelect).append(btnOpt);
        }
      }
      $(".tDiv2").append(btnDiv);
    }
    g.cDiv.className = "cDiv";
    $(t).before(g.cDiv);
    $(g.cDiv).append(g.hDiv);
    g.hTable.cellPadding = 0;
    g.hTable.cellSpacing = 0;
    $(g.hDiv).append('<div class="hDivBox"></div>');
    $("div", g.hDiv).append(g.hTable);
    var thead = $("thead:first", t).get(0);
    if (thead) {
      $(g.hTable).append(thead);
    }
    thead = null;
    if (!p.colmodel) {
      var ci = 0
    }
    $("thead tr:first th", g.hDiv).each(function() {
      var thdiv = document.createElement("div");
      if ($(this).attr("abbr")) {
        $(this).click(function(e) {
          if (!$(this).hasClass("thOver")) {
            return false;
          }
          var obj = e.target || e.srcElement;
          if (obj.href || obj.type) {
            return true;
          }
          g.changeSort(this);
        });
        if ($(this).attr("abbr") == p.sortname) {
          this.className = "sorted";
          thdiv.className = "s" + p.sortorder;
        }
      }
      if (this.hidden) {
        $(this).hide();
      }
      if (!p.colmodel) {
        $(this).attr("axis", "col" + ci++);
      }
      if (this.width == "") {
        this.width = 100;
      }
      $(thdiv).css({textAlign:this.align, width:this.width + "px"});
      thdiv.innerHTML = this.innerHTML;
      $(this).empty().append(thdiv).removeAttr("width").mousedown(function(e) {
        g.dragStart("colMove", e, this);
      }).hover(function() {
        if (!g.colresize && (!$(this).hasClass("thMove") && !g.colCopy)) {
          $(this).addClass("thOver");
        }
        if ($(this).attr("abbr") != p.sortname && (!g.colCopy && (!g.colresize && $(this).attr("abbr")))) {
          $("div", this).addClass("s" + p.sortorder);
        } else {
          if ($(this).attr("abbr") == p.sortname && (!g.colCopy && (!g.colresize && $(this).attr("abbr")))) {
            var no = p.sortorder == "asc" ? "desc" : "asc";
            $("div", this).removeClass("s" + p.sortorder).addClass("s" + no);
          }
        }
        if (g.colCopy) {
          var n = $("th", g.hDiv).index(this);
          if (n == g.dcoln) {
            return false;
          }
          if (n < g.dcoln) {
            $(this).append(g.cdropleft);
          } else {
            $(this).append(g.cdropright);
          }
          g.dcolt = n;
        } else {
          if (!g.colresize) {
            var nv = $("th:visible", g.hDiv).index(this);
            var onl = parseInt($("div:eq(" + nv + ")", g.cDrag).css("left"), 10);
            var nw = jQuery(g.nBtn).outerWidth();
            var nl = onl - nw + Math.floor(p.cgwidth / 2);
            $(g.nDiv).hide();
            $(g.nBtn).hide();
            $(g.nBtn).css({"left":nl, top:g.hDiv.offsetTop}).show();
            var ndw = parseInt($(g.nDiv).width(), 10);
            $(g.nDiv).css({top:g.bDiv.offsetTop});
            if (nl + ndw > $(g.gDiv).width()) {
              $(g.nDiv).css("left", onl - ndw + 1);
            } else {
              $(g.nDiv).css("left", nl);
            }
            if ($(this).hasClass("sorted")) {
              $(g.nBtn).addClass("srtd");
            } else {
              $(g.nBtn).removeClass("srtd");
            }
          }
        }
      }, function() {
        $(this).removeClass("thOver");
        if ($(this).attr("abbr") != p.sortname) {
          $("div", this).removeClass("s" + p.sortorder);
        } else {
          if ($(this).attr("abbr") == p.sortname) {
            var no = p.sortorder == "asc" ? "desc" : "asc";
            $("div", this).addClass("s" + p.sortorder).removeClass("s" + no);
          }
        }
        if (g.colCopy) {
          $(g.cdropleft).remove();
          $(g.cdropright).remove();
          g.dcolt = null;
        }
      });
    });
    g.bDiv.className = "bDiv";
    $(g.cDiv).append(g.bDiv);
    $(g.cDiv).resize(function() {
      if ($(g.cDiv).width() > $(t).width()) {
        $(g.cDiv).width($(t).width());
      }
    });
    $(g.gDiv).css({height:p.height == "auto" ? "auto" : p.height + "px"});
    $(g.bDiv).scroll(function(e) {
      g.scroll();
    }).append(t);
    if (p.height == "auto") {
      $("table", g.bDiv).addClass("autoht");
    }
    g.addCellProp();
    g.addRowProp();
    if (p.colResize === true) {
      var cdcol = $("thead tr:first th:first", g.hDiv).get(0);
      if (cdcol !== null) {
        g.cDrag.className = "cDrag";
        g.cdpad = 0;
        g.cdpad += isNaN(parseInt($("div", cdcol).css("borderLeftWidth"), 10)) ? 0 : parseInt($("div", cdcol).css("borderLeftWidth"), 10);
        g.cdpad += isNaN(parseInt($("div", cdcol).css("borderRightWidth"), 10)) ? 0 : parseInt($("div", cdcol).css("borderRightWidth"), 10);
        g.cdpad += isNaN(parseInt($("div", cdcol).css("paddingLeft"), 10)) ? 0 : parseInt($("div", cdcol).css("paddingLeft"), 10);
        g.cdpad += isNaN(parseInt($("div", cdcol).css("paddingRight"), 10)) ? 0 : parseInt($("div", cdcol).css("paddingRight"), 10);
        g.cdpad += isNaN(parseInt($(cdcol).css("borderLeftWidth"), 10)) ? 0 : parseInt($(cdcol).css("borderLeftWidth"), 10);
        g.cdpad += isNaN(parseInt($(cdcol).css("borderRightWidth"), 10)) ? 0 : parseInt($(cdcol).css("borderRightWidth"), 10);
        g.cdpad += isNaN(parseInt($(cdcol).css("paddingLeft"), 10)) ? 0 : parseInt($(cdcol).css("paddingLeft"), 10);
        g.cdpad += isNaN(parseInt($(cdcol).css("paddingRight"), 10)) ? 0 : parseInt($(cdcol).css("paddingRight"), 10);
        $(g.bDiv).before(g.cDrag);
        var cdheight = $(g.bDiv).height();
        var hdheight = $(g.hDiv).height();
        $(g.cDrag).css({top:-hdheight + "px"});
        $("thead tr:first th", g.hDiv).each(function() {
          var cgDiv = document.createElement("div");
          $(g.cDrag).append(cgDiv);
          if (!p.cgwidth) {
            p.cgwidth = $(cgDiv).width();
          }
          $(cgDiv).css({height:cdheight + hdheight}).mousedown(function(e) {
            g.dragStart("colresize", e, this);
          }).dblclick(function(e) {
            g.autoResizeColumn(this);
          });
          if (browser.msie && browser.version < 7) {
            g.fixHeight($(g.gDiv).height());
            $(cgDiv).hover(function() {
              g.fixHeight();
              $(this).addClass("dragging");
            }, function() {
              if (!g.colresize) {
                $(this).removeClass("dragging");
              }
            });
          }
        });
      }
    }
    if (p.striped) {
      $("tbody tr:odd", g.bDiv).addClass("erow");
    }
    if (p.resizable && p.height != "auto") {
      g.vDiv.className = "vGrip";
      $(g.vDiv).mousedown(function(e) {
        g.dragStart("vresize", e);
      }).html("<span></span>");
      $(g.bDiv).after(g.vDiv);
    }
    if (p.resizable && (p.width != "auto" && !p.nohresize)) {
      g.rDiv.className = "hGrip";
      $(g.rDiv).mousedown(function(e) {
        g.dragStart("vresize", e, true);
      }).html("<span></span>").css("height", $(g.gDiv).height());
      if (browser.msie && browser.version < 7) {
        $(g.rDiv).hover(function() {
          $(this).addClass("hgOver");
        }, function() {
          $(this).removeClass("hgOver");
        });
      }
      $(g.gDiv).append(g.rDiv);
    }
    $(g.pDiv, g.sDiv).append("<div style='clear:both'></div>");
    if (p.title) {
      g.mDiv.className = "mDiv";
      g.mDiv.innerHTML = '<div class="ftitle">' + p.title + "</div>";
      $(g.gDiv).prepend(g.mDiv);
      if (p.showTableToggleBtn) {
        $(g.mDiv).append('<div class="ptogtitle" title="Minimize/Maximize Table"><span></span></div>');
        $("div.ptogtitle", g.mDiv).click(function() {
          $(g.gDiv).toggleClass("hideBody");
          $(this).toggleClass("vsble");
        });
      }
    }
    g.cdropleft = document.createElement("span");
    g.cdropleft.className = "cdropleft";
    g.cdropright = document.createElement("span");
    g.cdropright.className = "cdropright";
    g.block.className = "gBlock";
    var gh = $(g.bDiv).height();
    var gtop = g.bDiv.offsetTop;
    $(g.block).css({width:g.bDiv.style.width, height:gh, background:"white", position:"relative", marginBottom:gh * -1, zIndex:1, top:gtop, left:"0px"});
    $(g.block).fadeTo(0, p.blockOpacity);
    if ($("th", g.hDiv).length) {
      g.nDiv.className = "nDiv";
      g.nDiv.innerHTML = "<table cellpadding='0' cellspacing='0'><tbody></tbody></table>";
      $(g.nDiv).css({marginBottom:gh * -1, display:"none", top:gtop}).noSelect();
      var cn = 0;
      $("th div", g.hDiv).each(function() {
        var kcol = $("th[axis='col" + cn + "']", g.hDiv)[0];
        var chk = 'checked="checked"';
        if (kcol.style.display == "none") {
          chk = "";
        }
        $("tbody", g.nDiv).append('<tr><td class="ndcol1"><input type="checkbox" ' + chk + ' class="togCol" value="' + cn + '" /></td><td class="ndcol2">' + this.innerHTML + "</td></tr>");
        cn++;
      });
      if (browser.msie && browser.version < 7) {
        $("tr", g.nDiv).hover(function() {
          $(this).addClass("ndcolover");
        }, function() {
          $(this).removeClass("ndcolover");
        });
      }
      $("td.ndcol2", g.nDiv).click(function() {
        if ($("input:checked", g.nDiv).length <= p.minColToggle && $(this).prev().find("input")[0].checked) {
          return false;
        }
        return g.toggleCol($(this).prev().find("input").val());
      });
      $("input.togCol", g.nDiv).click(function() {
        if ($("input:checked", g.nDiv).length < p.minColToggle && this.checked === false) {
          return false;
        }
        $(this).parent().next().trigger("click");
      });
      $(g.gDiv).prepend(g.nDiv);
      $(g.nBtn).addClass("nBtn").html("<div></div>").attr("title", "Hide/Show Columns").click(function() {
        $(g.nDiv).toggle();
        return true;
      });
      if (p.showToggleBtn) {
        $(g.gDiv).prepend(g.nBtn);
      }
    }
    $(g.iDiv).addClass("iDiv").css({display:"none"});
    $(g.bDiv).append(g.iDiv);
    g.addScrollBar(g, t);
    $(g.bDiv).hover(function() {
      $(g.nDiv).hide();
      $(g.nBtn).hide();
    }, function() {
      if (g.multisel) {
        g.multisel = false;
      }
    });
    $(g.gDiv).hover(function() {
    }, function() {
      $(g.nDiv).hide();
      $(g.nBtn).hide();
    });
    $(document).mousemove(function(e) {
      g.dragMove(e);
    }).mouseup(function(e) {
      g.dragEnd();
    }).hover(function() {
    }, function() {
      g.dragEnd();
    });
    if (browser.msie && browser.version < 7) {
      $(".hDiv,.bDiv,.mDiv,.pDiv,.vGrip,.tDiv, .sDiv", g.gDiv).css({width:"100%"});
      $(g.gDiv).addClass("ie6");
      if (p.width != "auto") {
        $(g.gDiv).addClass("ie6fullwidthbug");
      }
    }
    g.rePosDrag();
    g.fixHeight();
    t.p = p;
    t.grid = g;
    if (p.url && p.autoload) {
      p.newp = false;
      g.populate();
    }
    return t;
  };
  var docloaded = false;
  $(document).ready(function() {
    docloaded = true;
  });
  $.fn.flexigrid = function(p) {
    return this.each(function() {
      if (!docloaded) {
        $(this).hide();
        var t = this;
        $(document).ready(function() {
          $.addFlex(t, p);
        });
      } else {
        $.addFlex(this, p);
      }
    });
  };
  $.fn.flexReload = function(p) {
    return this.each(function() {
      this.p.newp = false;
      if (this.grid && this.p.url) {
        this.grid.populate();
      }
    });
  };
  $.fn.flexOptions = function(p) {
    return this.each(function() {
      if (this.grid) {
        $.extend(this.p, p);
      }
    });
  };
  $.fn.flexToggleCol = function(cid, visible) {
    return this.each(function() {
      if (this.grid) {
        this.grid.toggleCol(cid, visible);
      }
    });
  };
  $.fn.flexAddData = function(data) {
    return this.each(function() {
      if (this.grid) {
        this.grid.addData(data);
      }
    });
  };
  $.fn.flexAddRow = function(row) {
    var dl = {};
    var selector = $(this.selector)[0].p;
    var grid = $(this.selector)[0].grid;
    if (selector.data == null) {
      selector.data = [];
    }
    selector.data[selector.data.length] = row;
    dl.resultList = selector.data;
    if (grid) {
      grid.addData(dl);
    }
  };
  $.fn.flexRemoveRow = function(rowIndex) {
    var dl = {};
    var p = $(this.selector)[0].p;
    var grid = $(this.selector)[0].grid;
    p.data.splice(rowIndex, 1);
    dl.resultList = p.data;
    if (grid) {
      grid.addData(dl);
    }
  };
  $.fn.noSelect = function(p) {
    var prevent = p === null ? true : p;
    if (prevent) {
      return this.each(function() {
        if (browser.msie || browser.safari) {
          $(this).bind("selectstart", function() {
            return false;
          });
        } else {
          if (browser.mozilla) {
            $(this).css("MozUserSelect", "none");
            $("body").trigger("focus");
          } else {
            if (browser.opera) {
              $(this).bind("mousedown", function() {
                return false;
              });
            } else {
              $(this).attr("unselectable", "on");
            }
          }
        }
      });
    } else {
      return this.each(function() {
        if (browser.msie || browser.safari) {
          $(this).unbind("selectstart");
        } else {
          if (browser.mozilla) {
            $(this).css("MozUserSelect", "inherit");
          } else {
            if (browser.opera) {
              $(this).unbind("mousedown");
            } else {
              $(this).removeAttr("unselectable", "on");
            }
          }
        }
      });
    }
  };
  $.fn.flexSearch = function(p) {
    return this.each(function() {
      if (this.grid && this.p.searchitems) {
        this.grid.doSearch();
      }
    });
  };
  $.fn.selectedRows = function(p) {
    var arReturn = [];
    var arRow = [];
    var selector = $(this.selector + " .trSelected");
    $(selector).each(function(i, row) {
      arReturn.push($(this).closest("table")[0].p.data[$(this).closest("table").find("tr").index(this)]);
    });
    return arReturn;
  };
  $.fn.unSelectedRows = function(p) {
    var arReturn = [];
    var arRow = [];
    var selector = $(this.selector).not(".trSelected");
    $(selector).each(function(i, row) {
      arReturn.push($(this).closest("table")[0].p.data[$(this).closest("table").find("tr").index(this)]);
    });
    return arReturn;
  };
  $.fn.selectedRowIds = function(p) {
    var arRow = [];
    var selector = $(this.selector + " .trSelected");
    $(selector).each(function(i, row) {
      arRow[arRow.length] = $(this).closest("table").find("tr").index(this);
    });
    return arRow;
  };
  $.fn.selectedRowCount = function() {
    return $(this.selector + " .trSelected").length;
  };
  $.fn.flexGetData = function(p) {
    var selector = $(this.selector)[0].p;
    if (selector.data == null) {
      selector.data = [];
    }
    return selector.data;
  };
  $.fn.flexUpdateRow = function(i, row) {
    var dl = {};
    var selector = $(this.selector)[0].p;
    selector.data[i] = row;
    dl.resultList = selector.data;
    if ($(this.selector)[0].grid) {
      $(this.selector)[0].grid.addData(dl);
    }
  };
  $.fn.flexGetMergeData = function(p) {
    var arReturn = [];
    var selector = $(this.selector)[0].p;
    if (selector.data != null) {
      $.each(selector.data, function() {
        if ($(this)._updt == null || $(this)._updt == "") {
          return;
        }
        arReturn[arReturn.length] = this;
      });
    }
    return arReturn;
  };
  $.fn.flexGetRow = function(p) {
    var arReturn = [];
    var selector = $(this.selector)[0].p;
    if (selector.data != null) {
      $.each(selector.data, function() {
        if ($(this)._updt == null || $(this)._updt == "") {
          return;
        }
        arReturn[arReturn.length] = this;
      });
    }
    return selector.data[p];
  };
  $.fn.selectFilterData = function(filter) {
    var arReturn = [];
    var arRow = [];
    var selector = $(this.selector)[0].p;
    if (selector.data != null) {
      $.each(selector.data, function(i, row) {
        var filtered = false;
        arRow = {};
        $.each(filter, function() {
          if (row[this.col] != undefined && row[this.col] == this.filter) {
            filtered = true;
          }
          return filtered == false;
        });
        if (filtered) {
          arReturn.push(row);
        }
      });
    }
    return arReturn;
  };
})(jQuery);
(function($) {
  $treedatatype = {Json:1, Xml:2};
  $treedataformat = {Linear:1, Hierarchy:2};
  var $defaults = {containerid:null, module:null, url:null, async:false, dataset:null, datatype:$treedatatype.Json, dataformat:$treedataformat.Hierarchy, class_node_collapse:"ui-icon-folder-collapsed", class_node_expand:"ui-icon-folder-open", class_node_item:"ui-icon-document", collapse_tree:false, class_node_highlight:"ui-state-highlight", class_node_add:"ui-icon-plusthick", class_node_remove:"ui-icon-minusthick", show_button_check:false, show_button_add:false, show_button_remove:false, node_remove_message:"Are you sure you want to remove this node?"};
  var $settings = $defaults;
  $.fn.btechcotree = function(options) {
    $settings = $.extend({}, $defaults, options);
    var tree_datastructure = [];
    var displaytree = "";
    Initialize();
    function Initialize() {
      $(this).closest("div.tree").trigger("onStart", [$settings.module]);
      $(this).closest("div.tree").trigger("onProcessingStart", [$settings.module]);
      BuildTreeDataStructure();
      $(this).closest("div.tree").trigger("onProcessingComplete", [$settings.module]);
      $(this).closest("div.tree").trigger("onRenderStart", [$settings.module]);
      BuildTree();
      $(this).closest("div.tree").trigger("onRenderComplete", [$settings.module]);
      $(this).closest("div.tree").trigger("onEnd", [$settings.module]);
    }
    function BuildTreeDataStructure() {
      if ($settings.url != null && $settings.url.length > 0) {
        var datatype = "";
        switch($settings.datatype) {
          case 1:
            datatype = "json";
            break;
          case 2:
            datatype = "xml";
            break;
        }
        $.ajax({type:"GET", async:$settings.async, url:$settings.url, dataType:datatype, success:function(data) {
          $settings.dataset = data;
        }, error:function(xhr, ajaxOptions, thrownError) {
          $(this).closest("div.tree").trigger("onProcessingError", [$settings.module, xhr, ajaxOptions, thrownError]);
        }});
      }
      $(this).closest("div.tree").trigger("onBeforeDataConversion", [$settings.module]);
      switch($settings.datatype) {
        case 1:
          switch($settings.dataformat) {
            case 1:
              ConvertJsonLinearToTreeObject($settings.dataset.root);
              break;
            case 2:
              tree_datastructure = $settings.dataset.root;
              break;
          }
          break;
        case 2:
          switch($settings.dataformat) {
            case 1:
              ConvertLinearXmlToTreeObject($settings.dataset);
              break;
            case 2:
              ConvertHierarchyXmlToTreeObject($settings.dataset);
              break;
          }
          break;
      }
      $(this).closest("div.tree").trigger("onAfterDataConversion", [$settings.module]);
    }
    function GetNodeObjectFromJson(jsonnode) {
      return{id:jsonnode.id, name:jsonnode.name, parentid:jsonnode.parentid, href:jsonnode.href, target:jsonnode.target, tooltip:jsonnode.tooltip, buttoncheck:jsonnode.buttoncheck, buttonadd:jsonnode.buttonadd, buttonremove:jsonnode.buttonremove, isdisabled:jsonnode.isdisabled, ischecked:jsonnode.ischecked, classnodeicon:jsonnode.classnodeicon, childnodes:[]};
    }
    function GetNodeObjectFromXml(xmlnode) {
      var parentId = 0;
      if ($(xmlnode).parent().attr("id") != null) {
        parentId = $(xmlnode).parent().attr("id");
      } else {
        if ($(xmlnode).attr("parentid") != null) {
          parentId = $(xmlnode).attr("parentid");
        }
      }
      return{id:$(xmlnode).attr("id"), name:$(xmlnode).attr("name"), parentid:parentId, href:$(xmlnode).attr("href"), target:$(xmlnode).attr("target"), tooltip:$(xmlnode).attr("tooltip"), buttoncheck:$(xmlnode).attr("buttoncheck") == null ? false : $(xmlnode).attr("buttoncheck"), buttonadd:$(xmlnode).attr("buttonadd") == null ? false : $(xmlnode).attr("buttonadd"), buttonremove:$(xmlnode).attr("buttonremove") == null ? false : $(xmlnode).attr("buttonremove"), isdisabled:$(xmlnode).attr("isdisabled") == 
      null ? false : $(xmlnode).attr("isdisabled"), ischecked:$(xmlnode).attr("ischecked") == null ? false : $(xmlnode).attr("ischecked"), classnodeicon:$(xmlnode).attr("classnodeicon"), childnodes:[]};
    }
    function ConvertJsonLinearToTreeObject(dataset) {
      $(dataset).each(function(key, value) {
        var node = GetNodeObjectFromJson(this);
        if (tree_datastructure.length == 0 || node.parentid == 0) {
          tree_datastructure.push(node);
        } else {
          AddNodeToTreeDataStructure(tree_datastructure, node);
        }
      });
    }
    function ConvertLinearXmlToTreeObject(dataset) {
      $(dataset).find("node").each(function(key, value) {
        var node = GetNodeObjectFromXml(this);
        if (tree_datastructure.length == 0 || node.parentid == 0) {
          tree_datastructure.push(node);
        } else {
          AddNodeToTreeDataStructure(tree_datastructure, node);
        }
      });
    }
    function ConvertHierarchyXmlToTreeObject(dataset) {
      $(dataset).find("node").each(function(key, value) {
        var node = GetNodeObjectFromXml(this);
        if (tree_datastructure.length == 0 || node.parentid == 0) {
          tree_datastructure.push(node);
        } else {
          AddNodeToTreeDataStructure(tree_datastructure, node);
        }
      });
    }
    function AddNodeToTreeDataStructure(nodes, childnode) {
      for (var i = 0;i < nodes.length;i++) {
        if (nodes[i].id == childnode.parentid) {
          if (nodes[i].childnodes.length == 0) {
            nodes[i].childnodes = [];
          }
          nodes[i].childnodes.push(childnode);
          break;
        } else {
          if (nodes[i].childnodes instanceof Object) {
            AddNodeToTreeDataStructure(nodes[i].childnodes, childnode);
          }
        }
      }
    }
    function BuildTree() {
      AddNodeToDisplayTree(tree_datastructure);
      $("#" + $settings.containerid).append(displaytree);
      EnableRootCarats();
      StandardizeCheckSelection();
    }
    function EnableRootCarats() {
      var root_nodes = $.fn.btechcotree.GetRootNodes();
      $.fn.btechcotree.ToggleCaratIcon(root_nodes);
      $.fn.btechcotree.ToggleTree(root_nodes);
      if ($settings.collapse_tree) {
        $.fn.btechcotree.ToggleCaratIcon(root_nodes);
        $.fn.btechcotree.ToggleTree(root_nodes);
      }
    }
    function AddNodeToDisplayTree(nodes) {
      if (displaytree.length == 0) {
        displaytree += "<ul style='list-style-type:none;'>";
      } else {
        displaytree += "<ul style='list-style-type:none; display:none;'>";
      }
      $(nodes).each(function(key, value) {
        var carat_css = $settings.class_node_collapse;
        $(this).closest("div.tree").trigger("onBeforeNodeInsert", [$settings.module, value]);
        displaytree += CreateNode(this);
        if (this.childnodes != null && this.childnodes.length > 0) {
          AddNodeToDisplayTree(this.childnodes);
        }
        displaytree += "</li>";
        $(this).closest("div.tree").trigger("onAfterNodeInsert", [$settings.module, value]);
      });
      displaytree += "</ul>";
    }
    function CreateNode(node) {
      var displaytree = "";
      var item_margin = 20;
      displaytree += "<li nodeid='" + node.id + "'>";
      displaytree += "<div>";
      displaytree += "<span class='ui-icon ";
      if (node.classnodeicon != null && node.classnodeicon.length > 0) {
        displaytree += node.classnodeicon;
      } else {
        if (node.childnodes != null && node.childnodes.length > 0) {
          if ($settings.collapse_tree) {
            displaytree += $settings.class_node_expand;
          } else {
            displaytree += $settings.class_node_collapse;
          }
        } else {
          displaytree += $settings.class_node_item;
        }
      }
      displaytree += "' data-action='nav_items' style='position:absolute; margin-top:1px;'></span>";
      if (node.buttoncheck || node.buttoncheck == null && $settings.show_button_check) {
        displaytree += "<input type='checkbox' style='position:absolute; margin-top:1px;margin-left:" + item_margin + "px;'";
        if (node.isdisabled) {
          displaytree += " disabled='true'";
        }
        if (node.ischecked == "true") {
          displaytree += " checked='true' data-ischecked=" + node.ischecked;
        } else {
          displaytree += " data-ischecked='false'";
        }
        displaytree += "></input>";
        item_margin += 20;
      }
      if (node.buttonadd || node.buttonadd == null && $settings.show_button_add) {
        displaytree += "<span class='ui-icon " + $settings.class_node_add + "' data-action='add' style='position:absolute; margin-top:1px;margin-left:" + item_margin + "px;'></span>";
        item_margin += 20;
      }
      if (node.buttonremove || node.buttonremove == null && $settings.show_button_remove) {
        displaytree += "<span class='ui-icon " + $settings.class_node_remove + "' data-action='remove' style='position:absolute; margin-top:1px;margin-left:" + item_margin + "px;'></span>";
        item_margin += 20;
      }
      if (node.href) {
        displaytree += "<a href='" + node.href + "' target='" + node.target + "'>";
      }
      displaytree += "<span style='margin-left:" + item_margin + "px;' data-action='text'";
      if (node.tooltip != null) {
        displaytree += " title='" + node.tooltip + "'";
      }
      displaytree += ">" + node.name + "</span>";
      if (node.href) {
        displaytree += "</a>";
      }
      displaytree += "</div>";
      return displaytree;
    }
    $("li div span[data-action='nav_items']").bind("click", function() {
      $.fn.btechcotree.ToggleCaratIcon(this);
      $.fn.btechcotree.ToggleTree(this);
    });
    $("li div span[data-action='text']").bind("click", function(e) {
      $(this).closest("div.tree").trigger("onSelectedNode", [$settings.module, $(this).parent("div").parent("li").attr("nodeid"), $(this).parent("div").parent("li"), e]);
      HighlightNode(this, true);
    });
    $("li div input[type='checkbox']").bind("click", function(e) {
      $(this).closest("div.tree").trigger("onNodeCheckSelected", [$settings.module, $(this).parent("div").parent("li").attr("nodeid"), $(this).parent("div").parent("li"), e]);
      SelectParentChildCheckBox(this, $(this).is(":checked"));
    });
    $("li div span[data-action='add']").bind("click", function(e) {
      $(this).closest("div.tree").trigger("onBeforeAddNode", [$settings.module, $(this).parent("div").parent("li").attr("nodeid"), $(this).parent("div").parent("li"), e]);
      HighlightNode(this, true);
      $(this).closest("div.tree").trigger("onAddNode", [$settings.module, $(this).parent("div").parent("li").attr("nodeid"), $(this).parent("div").parent("li"), e]);
      $(this).closest("div.tree").trigger("onAfterAddNode", [$settings.module, $(this).parent("div").parent("li").attr("nodeid"), $(this).parent("div").parent("li"), e]);
    });
    $("li div span[data-action='remove']").bind("click", function(e) {
      $(this).closest("div.tree").trigger("onBeforereMoveNode", [$settings.module, $(this).parent("div").parent("li").attr("nodeid"), $(this).parent("div").parent("li"), e]);
      HighlightNode(this, true);
      var confirm_remove = confirm($settings.node_remove_message);
      if (confirm_remove) {
        $(this).closest("div.tree").trigger("onRemoveNode", [$settings.module, $(this).parent("div").parent("li").attr("nodeid"), $(this).parent("div").parent("li"), e]);
      } else {
        HighlightNode(this, false);
      }
      $(this).closest("div.tree").trigger("onAfterRemoveNode", [$settings.module, $(this).parent("div").parent("li").attr("nodeid"), $(this).parent("div").parent("li"), e]);
    });
    function HighlightNode(selectednode, flag) {
      $("#" + $settings.containerid + " ul li div span[data-action='text']").removeClass($settings.class_node_highlight);
      if (flag) {
        $(selectednode).parent("div").parent("li").find("span[data-action='text']:first").addClass($settings.class_node_highlight);
      }
    }
    function SelectParentChildCheckBox(selectednode, flag) {
      SelectCheckbox(selectednode, flag);
      $(selectednode).parent("div").parent("li").children("ul").find("input[type='checkbox']").each(function() {
        SelectCheckbox(this, flag);
      });
      $(selectednode).parents("ul li").each(function() {
        SelectCheckbox($(this).find("input[type='checkbox']").first(), flag);
      });
    }
    function SelectCheckbox(selectednode, flag) {
      if (flag) {
        $(selectednode).prop("checked", "true");
        $(selectednode).attr("data-ischecked", "true");
      } else {
        $(selectednode).removeAttr("checked");
        $(selectednode).attr("data-ischecked", "false");
      }
      selectid = $(selectednode).closest("li").attr("nodeid");
      $.each($settings.dataset.root, function() {
        if ($(this).attr("id") == selectid) {
          $(this).attr("ischecked", flag);
          return false;
        }
      });
    }
    function StandardizeCheckSelection() {
      $("#" + $settings.containerid + " ul li div").find("input[type='checkbox']").each(function() {
        if ($(this).is(":checked") && ($(this).attr("data-ischecked") && $(this).parent("div").parent("li").next("ul").children().find("input[type='checkbox']").length > 0)) {
          var isanychildselected = false;
          $(this).parent("div").parent("li").next("ul").children().each(function() {
            if ($(this).find("input[type='checkbox']").is(":checked") && $(this).find("input[type='checkbox']").attr("data-ischecked")) {
              isanychildselected = true;
            }
          });
          if (!isanychildselected) {
            $(this).removeAttr("checked");
            $(this).attr("data-ischecked", "false");
          }
        }
      });
      $("#" + $settings.containerid + " ul li div").find("input[type='checkbox'][checked='true']").each(function() {
        $(this).parents("ul").prev("li").each(function() {
          $(this).find("input[type='checkbox']").attr("checked", "true");
          $(this).find("input[type='checkbox']").attr("data-ischecked", "true");
        });
      });
    }
  };
  $.fn.btechcotree.GetRootNodes = function() {
    return $("#" + $settings.containerid + "> ul > li > div > span:nth-child(1)");
  };
  $.fn.btechcotree.ToggleTree = function(selectednode) {
    $(selectednode).parent("div").next("ul").toggle();
  };
  $.fn.btechcotree.ToggleCaratIcon = function(selectednode) {
    $(selectednode).each(function() {
      if (!$(this).hasClass($settings.class_node_item)) {
        if ($(this).hasClass($settings.class_node_expand)) {
          $(this).removeClass($settings.class_node_expand);
          $(this).addClass($settings.class_node_collapse);
        } else {
          if ($(this).hasClass($settings.class_node_collapse)) {
            $(this).removeClass($settings.class_node_collapse);
            $(this).addClass($settings.class_node_expand);
          } else {
            $(this).addClass($settings.class_node_expand);
          }
        }
      }
    });
  };
  $.fn.btechcotree.ExpandCollapseTree = function(selectednode, flag) {
    if (flag) {
      if (!$(selectednode).parent("div").parent("li").next("ul").is(":visible")) {
        $(selectednode).parent("div").parent("li").next("ul").show();
        $.fn.btechcotree.ToggleCaratIcon(selectednode);
      }
    } else {
      if ($(selectednode).parent("div").parent("li").next("ul").is(":visible")) {
        $(selectednode).parent("div").parent("li").next("ul").hide();
        $.fn.btechcotree.ToggleCaratIcon(selectednode);
      }
    }
  };
  $.fn.btechcotree.Expand = function() {
    var root_nodes = $.fn.btechcotree.GetRootNodes();
    $.fn.btechcotree.ExpandCollapseTree(root_nodes, true);
  };
  $.fn.btechcotree.Collapse = function() {
    var root_nodes = $.fn.btechcotree.GetRootNodes();
    $.fn.btechcotree.ExpandCollapseTree(root_nodes, false);
  };
  $.fn.btechcotree.Toggle = function() {
    var root_nodes = $.fn.btechcotree.GetRootNodes();
    $.fn.btechcotree.ToggleCaratIcon(root_nodes);
    $.fn.btechcotree.ToggleTree(root_nodes);
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

