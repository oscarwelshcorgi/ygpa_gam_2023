OpenLayers.ProxyHost = "/gam/proxy.do?url=";
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
    for (var i in EMD.init) {
      EMD.init[i]();
    }
    EMD.context_root = ctr;
    OpenLayers.ProxyHost = EMD.context_root + "/proxy.do?url=";
  }, context_root:"/", window_seq:0, win_x:80, win_y:40, userinfo:null, popupCaller:null, popupId:null, map:null, gyhmap:null, wfs:null, lotarea:null, panel:null, draw:null, edit:null, edit_win:null, del:null, info:null, save:null, saveStrategy:null, transaction:{type:null, callbackWindowId:null}, map_panel:null, userLayer:{landCode:null, prtFclty:null}, selectControl:null, maxBounds:new OpenLayers.Bounds(-180, -90, 180, 90), maxRes:0.0439453125, maxZoom:19, module_instance:[], init:{frame_breaker:function() {
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
      $(this).off("mouseenter").draggable({cancel:"a", containment:"div.olForeignContainer", handle:"div.window_top"}).resizable({containment:"div.olForeignContainer", minWidth:400, minHeight:200});
    });
    d.on("dblclick", "div.window_top", function() {
      EMD.util.window_resize(this);
    });
    d.on("dblclick", "div.window_top img", function() {
      $($(this).closest("div.window_top").find("a.window_close").attr("href")).hide("fast");
      $(this).closest("div.window").hide();
      return false;
    });
    d.on("click", "a.window_min", function() {
      var win_id = $(this).closest("div.window").attr("href");
      var dock_id = win_id.replace("window", "dock");
      $("#" + win_id).effect("transfer", {to:"#" + dock_id, className:".ui-effects-transfer"}, 500, function() {
        $("#" + win).hide();
      });
    });
    d.on("click", "a.window_resize", function() {
      EMD.util.window_resize(this);
    });
    d.on("click", "a.window_close", function() {
      EMD.util.window_close($(this));
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
      setTimeOut(function() {
        $(this).removeClass("showCategoryMenu", 1E3);
      });
    });
  }, wallpaper:function() {
    if ($("#desktop").length) {
    }
  }, map_init:function() {
    EMD.map = new OpenLayers.Map({div:"desktop", projection:"ESPG:900913", maxExtents:EMD.maxBounds, numZoomLevels:EMD.maxZoom, maxResolution:EMD.maxRes});
    var gmap = new OpenLayers.Layer.Google("Google Streets", {numZoomLevels:20});
    var ghyb = new OpenLayers.Layer.Google("Google Hybrid", {type:google.maps.MapTypeId.HYBRID, numZoomLevels:20});
    var gsat = new OpenLayers.Layer.Google("Google Satellite", {type:google.maps.MapTypeId.SATELLITE, numZoomLevels:22});
    var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
    renderer = renderer ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;
    EMD.gyhmap = new OpenLayers.Layer.Vector("\uad11\uc591\ud56d", {minScale:3E4, strategies:[new OpenLayers.Strategy.BBOX], protocol:new OpenLayers.Protocol.WFS({version:"1.1.0", url:"http://192.168.0.40:8080/G2DataService/GService?", fearureType:"GYH88", Typename:"GYH88", SRSNAME:"SR-ORG:6640"}), renderers:renderer});
    EMD.lotarea = new OpenLayers.Layer.Vector("\uc9c0\ubc88", {minScale:3E4, strategies:[new OpenLayers.Strategy.BBOX], protocol:new OpenLayers.Protocol.WFS({version:"1.1.0", apikey:"474AD190-F9E9-31C6-BE7C-15EFD66E5DAD", domain:"192.168.0.40", service:"WFS", url:"http://map.vworld.kr/js/wfs.do?APIKEY=474AD190-F9E9-31C6-BE7C-15EFD66E5DAD&DOMAIN=192.168.0.40&SERVICE=WFS&TYPENAME=LP_PA_CBND_BUBUN&SRSNAME=EPSG:900913", fearureType:"LP_PA_CBND_BUBUN", Typename:"LP_PA_CBND_BUBUN", SRSNAME:"ESPG:900913"}), 
    renderers:renderer});
    var saveStrategy = new OpenLayers.Strategy.Save;
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
    EMD.panel = new OpenLayers.Control.Panel({displayClass:"customEditingToolbar", allowDepress:true});
    EMD.lotarea.setVisibility(true);
    EMD.gyhmap.setVisibility(true);
    EMD.map.addLayers([gsat, gmap, EMD.lotarea, EMD.userLayer.prtFclty, EMD.wfs, EMD.gyhmap, new OpenLayers.Layer.Vector("States", {minScale:15E6, strategies:[new OpenLayers.Strategy.BBOX], protocol:new OpenLayers.Protocol.WFS({url:"http://demo.opengeo.org/geoserver/wfs", featureType:"states", featureNS:"http://www.openplans.org/topp"}), renderers:renderer})]);
    EMD.draw = new OpenLayers.Control.DrawFeature(EMD.wfs, OpenLayers.Handler.Polygon, {title:"\ucd94\uac00", displayClass:"olControlDrawFeaturePolygon", multi:true});
    EMD.edit = new OpenLayers.Control.ModifyFeature(EMD.wfs, {title:"\uc218\uc815", displayClass:"olControlModifyFeature"});
    EMD.del = new DeleteFeature(EMD.wfs, {title:"\uc0ad\uc81c"});
    EMD.save = new OpenLayers.Control.Button({title:"\uc800\uc7a5", trigger:function() {
      if (EMD.edit.feature) {
        EMD.edit.selectControl.unselectAll();
      }
      EMD.saveStrategy.save();
    }, displayClass:"olControlSaveFeatures"});
    EMD.panel.addControls([EMD.save, EMD.del, EMD.edit, EMD.draw]);
    EMD.map.addControl(EMD.panel);
    EMD.map.addControl(EMD.selectControl);
    EMD.selectControl.activate();
    EMD.map.addControl(new OpenLayers.Control.LayerSwitcher);
    EMD.map.addControl(new OpenLayers.Control.MousePosition({prefix:"\uc88c\ud45c : ", separator:" | ", numDigits:6, displayProjection:new OpenLayers.Projection("EPSG:900913")}));
    EMD.map.addControl(new OpenLayers.Control.Scale);
    EMD.map.setCenter(new OpenLayers.LonLat(14213430, 4147543), 13);
  }, ui_init:function() {
    $("#sideMenu").sidr("open", "sidr-main");
  }}, closeWindow:function(win) {
    var dock_id = "#" + win.attr("id").replace("window", "dock");
    $("#" + win.attr("id")).hide("explode", {}, 800, function() {
      $(this).remove();
    });
    $(dock_id).hide("fast");
    $(dock_id).remove();
  }, util:{addCommas:function(nStr) {
    nStr += "";
    x = nStr.split(".");
    x1 = x[0];
    x2 = x.length > 1 ? "." + x[1] : "";
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
      x1 = x1.replace(rgx, "$1" + "," + "$2");
    }
    return x1 + x2;
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
    win.innerHTML = '<div class="abs window_inner">' + '<div class="window_top ui-widget-header">' + '<span class="float_left">' + win_title + '</span> <span class="float_right"> <a href="#" class="window_min"></a><a href="#" class="window_resize"></a> <a href="#' + win.id + '" class="window_close"></a></span>' + '</div><div class="abs window_content"></div></div><div class="abs window_bottom ">\uc5ec\uc218\uad11\uc591\ud56d\ub9cc\uacf5\uc0ac \uc9c0\ub3c4\ud504\ub808\uc784\uc6cc\ud06c ver : 2014.03.07.001</div><span class="abs ui-resizable-handle ui-resizable-se"></span>';
    $("#desktop")[0].appendChild(win);
    $(x).addClass("abs");
    $(x).addClass("window");
    if (win_url != undefined) {
      $("#progress_dialog").dialog("open");
      $(x + " .window_content").load(win_url, {"window_id":x.substring(1), "args":argv}, function(response, status, xhr) {
        $("#progress_dialog").dialog("close");
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
  }, window_resize:function(el) {
    var win = $(el).closest("div.window");
    if (win.hasClass("window_full")) {
      win.removeClass("window_full").css({"top":win.attr("data-t"), "left":win.attr("data-l"), "right":win.attr("data-r"), "bottom":win.attr("data-b"), "width":win.attr("data-w"), "height":win.attr("data-h")});
    } else {
      win.attr({"data-t":win.css("top"), "data-l":win.css("left"), "data-r":win.css("right"), "data-b":win.css("bottom"), "data-w":win.css("width"), "data-h":win.css("height")}).addClass("window_full").css({"top":"0", "left":"0", "right":"0", "bottom":"0", "width":"100%", "height":"100%"});
    }
    EMD.util.window_flat();
    win.addClass("window_stack");
    EMD.util.window_resized(win);
  }, window_hide_all:function() {
    $("div.window").each(function() {
      $(this).effect("transfer", {to:"#dock_" + $(this).attr(id), className:".ui-effects-transfer"}, 500, function() {
        $(this).removeAttr("style").hide();
      });
    });
  }, window_hide:function(win) {
    var dock_id = win.attr("href").replace("window", "dock");
    $(this).effect("transfer", {to:"#" + dock_id, className:".ui-effects-transfer"}, 500, function() {
      $("#" + win).removeAttr("style").hide();
    });
  }, window_close:function(win) {
    var dock_id = win.attr("href").replace("window", "dock");
    $(win.attr("href")).hide("explode", {}, 800, function() {
      $(this).remove();
    });
    $(dock_id).hide("fast");
    $(dock_id).remove();
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
        $(this).closest(".window")[0].module[onchangeId](newId, oldId);
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
      $(this).button().click({module:win[0].module, button_id:id}, function(event) {
        event.preventDefault();
        if (event.data.button_id == "loadMap") {
          event.data.module.showMap($(this).attr("data-grid"));
        } else {
          event.data.module.onButtonClick(event.data.button_id);
        }
      });
    });
    $("#" + win_id).find("input.cmmnCd").each(function() {
      $(this).attr("type", "hidden");
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
          });
        }
      }, error:function(XMLHttpRequest, textStatus, errorThrown) {
        console.log(textStatus);
      }});
    });
    $("#" + win_id).find(".emdcal").each(function() {
      $(this).datepicker({showOn:"button", buttonImage:EMD.context_root + "/images/egovframework/rte/icon_date_picker.jpg", buttonImageOnly:true, dateFormat:"yy-mm-dd", prevText:"\uc774\uc804 \ub2ec", nextText:"\ub2e4\uc74c \ub2ec", monthNames:["1\uc6d4", "2\uc6d4", "3\uc6d4", "4\uc6d4", "5\uc6d4", "6\uc6d4", "7\uc6d4", "8\uc6d4", "9\uc6d4", "10\uc6d4", "11\uc6d4", "12\uc6d4"], monthNamesShort:["1\uc6d4", "2\uc6d4", "3\uc6d4", "4\uc6d4", "5\uc6d4", "6\uc6d4", "7\uc6d4", "8\uc6d4", "9\uc6d4", "10\uc6d4", 
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
    var input_id = $(this).attr("id");
    if (typeof input_id == "string") {
      input_id = input_id.replace(win_id + "_", "");
      args[args.length] = {name:input_id, value:$(this).val()};
    }
  });
  return args;
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
          $(this).click({module:popup_instance, button_id:$(this).attr("id")}, function(event) {
            event.preventDefault();
            event.data.module.onButtonClick(event.data.button_id);
          });
        });
        popup_instance.loadComplete();
      }
    });
  }
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
    var input_id = $(this).attr("id");
    if (typeof input_id == "string") {
      args[args.length] = {name:input_id, value:$(this).val()};
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
    p = $.extend({module:null, height:"auto", width:"auto", striped:true, novstripe:false, minwidth:30, minheight:80, resizable:true, nohresize:false, url:false, method:"POST", dataType:"json", errormsg:"\uc11c\ubc84\uc5d0 \uc5f0\uacb0 \ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.", usepager:true, nowrap:true, pageIndex:1, totalCount:1, useRp:false, rp:15, rpOptions:[10, 15, 20, 30, 50], title:false, idProperty:"id", pagestat:"{totalCount} \uc911 {from} \ubd80\ud130 {to} \uae4c\uc9c0 \ud45c\uc2dc \ub428", 
    pagetext:"\ud398\uc774\uc9c0", outof:"of", findtext:"\ucc3e\uae30", params:[], procmsg:"\uc11c\ubc84\uc758 \uc751\ub2f5\uc744 \uae30\ub2e4\ub9ac\ub294 \uc911\uc785\ub2c8\ub2e4...", query:"", qtype:"", nomsg:"\uc870\ud68c\ub41c \ud56d\ubaa9\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.", minColToggle:1, showToggleBtn:true, singleSelect:true, hideOnSubmit:true, autoload:false, blockOpacity:0.5, preProcess:false, addTitleToCell:false, dblClickResize:false, onDragCol:false, onToggleCol:false, onChangeSort:false, 
    onDoubleClick:false, onSuccess:false, onError:false, onSubmit:false, __mw:{datacol:function(p, col, val) {
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
    var g = {hset:{}, rePosDrag:function() {
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
      if (p.dataType == "json") {
      }
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
      if (p.dataType == "xml") {
        p.totalCount = +$("rows total", data).text();
      } else {
        p.totalCount = data.totalCount;
      }
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
      if (p.dataType == "xml") {
        p.pageIndex = +$("rows page", data).text();
      } else {
        p.pageIndex = data.searchOption.pageIndex;
      }
      this.buildpager();
      var tbody = document.createElement("tbody");
      if (p.dataType == "json") {
        $.each(data.resultList, function(i, row) {
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
              }
              if (typeof iHTML != undefined) {
                iHTML = iHTML !== null ? iHTML : "";
              } else {
                iHTML = "";
              }
              td.innerHTML = iHTML;
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
      } else {
        if (p.dataType == "xml") {
          var i = 1;
          $("rows row", data).each(function() {
            i++;
            var tr = document.createElement("tr");
            if ($(this).attr("name")) {
              tr.name = $(this).attr("name");
            }
            if ($(this).attr("color")) {
              $(tr).css("background", $(this).attr("id"));
            } else {
              if (i % 2 && p.striped) {
                tr.className = "erow";
              }
            }
            var nid = $(this).attr("id");
            if (nid) {
              tr.id = "row" + nid;
            }
            nid = null;
            var robj = this;
            $("thead tr:first th", g.hDiv).each(function() {
              var td = document.createElement("td");
              var idx = $(this).attr("axis").substr(3);
              td.align = this.align;
              var text = $("cell:eq(" + idx + ")", robj).text();
              var offs = text.indexOf("<BGCOLOR=");
              if (offs > 0) {
                $(td).css("background", text.substr(offs + 7, 7));
              }
              td.innerHTML = p.__mw.datacol(p, $(this).attr("abbr"), text);
              $(td).attr("abbr", $(this).attr("abbr"));
              $(tr).append(td);
              td = null;
            });
            if ($("thead", this.gDiv).length < 1) {
              $("cell", this).each(function() {
                var td = document.createElement("td");
                td.innerHTML = $(this).text();
                $(tr).append(td);
                td = null;
              });
            }
            $(tbody).append(tr);
            tr = null;
            robj = null;
          });
        }
      }
      $("tr", t).unbind();
      $(t).empty();
      $(t).append(tbody);
      this.addCellProp();
      this.addRowProp();
      this.rePosDrag();
      $(tbody).find("select").change({"result":data.resultList, "p":p, "g":g}, function(event) {
        event.preventDefault();
        var spl = $(this).attr("name").lastIndexOf("_");
        var idx = $(this).attr("name").substring(spl + 1) * 1;
        event.data.result[idx][$(this).attr("name").substring(0, spl)] = $(this).val();
        $(this).closest("table").trigger("onSelectChanged", [event.data.p.module, event.data.result[idx], event.data.g, event.data.p]);
      });
      $(tbody).find(":button").on("click", {"result":data.resultList, "p":p, "g":g}, function(event) {
        event.preventDefault();
        var spl = $(this).attr("name").lastIndexOf("_");
        var idx = $(this).attr("name").substring(spl + 1) * 1;
        $(this).closest("table").trigger("onButtonClicked", [event.data.p.module, $(this).attr("name").substring(0, spl), event.data.result[idx], event.data.g, event.data.p]);
      });
      $(tbody).find("input:checkbox").on("change", {"result":data.resultList, "p":p, "g":g}, function(event) {
        event.preventDefault();
        var spl = $(this).attr("name").lastIndexOf("_");
        var idx = $(this).attr("name").substring(spl + 1) * 1;
        var selectedValue = $(this).is(":checked");
        event.data.result[idx][$(this).attr("name").substring(0, spl)] = selectedValue;
        $(this).closest("table").trigger("onCheckBoxChanged", [event.data.p.module, event.data.result[idx], event.data.g, event.data.p]);
      });
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
      }
      if (p.pageIndex > p.pages) {
        p.pageIndex = p.pages;
      }
      var param = [{name:"pageIndex", value:p.newp}, {name:"recordCountPerPage", value:p.rp}, {name:"sortname", value:p.sortname}, {name:"sortorder", value:p.sortorder}, {name:"query", value:p.query}, {name:"qtype", value:p.qtype}];
      if (p.params.length) {
        for (var pi = 0;pi < p.params.length;pi++) {
          param[param.length] = p.params[pi];
        }
      }
      $.ajax({type:p.method, url:p.url, data:param, dataType:p.dataType, success:function(data) {
        if (data.resultCode != null && data.resultCode != 0) {
          if (data.resultCode == 1) {
            alert(data.resultMsg);
            location.reload();
          }
        } else {
          g.addData(data);
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
        $(this).toggleClass("trSelected");
        if ($(this).hasClass("trSelected")) {
          $(this).closest("table").trigger("onItemSelected", [p.module, p.data[this.sectionRowIndex], g, p]);
        } else {
          $(this).closest("table").trigger("onItemUnSelected", [p.module, p.data[this.sectionRowIndex], g, p]);
        }
        if (p.singleSelect && !g.multisel) {
          $(this).siblings().removeClass("trSelected");
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
    }, pager:0};
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
    g.gDiv.className = "flexigrid";
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
    $(t).before(g.hDiv);
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
    $(t).before(g.bDiv);
    $(g.bDiv).css({height:p.height == "auto" ? "auto" : p.height + "px"}).scroll(function(e) {
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
    if (p.usepager) {
      g.pDiv.className = "pDiv";
      g.pDiv.innerHTML = '<div class="pDiv2"></div>';
      $(g.bDiv).after(g.pDiv);
      var html = ' <div class="pGroup"> <div class="pFirst pButton"><span></span></div><div class="pPrev pButton"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"><span class="pcontrol">' + p.pagetext + ' <input type="text" size="4" value="1" /> ' + p.outof + ' <span> 1 </span></span></div> <div class="btnseparator"></div> <div class="pGroup"> <div class="pNext pButton"><span></span></div><div class="pLast pButton"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"> <div class="pReload pButton"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"><span class="pPageStat"></span></div>';
      $("div", g.pDiv).html(html);
      $(".pReload", g.pDiv).click(function() {
        g.populate();
      });
      $(".pFirst", g.pDiv).click(function() {
        g.changePage("first");
      });
      $(".pPrev", g.pDiv).click(function() {
        g.changePage("prev");
      });
      $(".pNext", g.pDiv).click(function() {
        g.changePage("next");
      });
      $(".pLast", g.pDiv).click(function() {
        g.changePage("last");
      });
      $(".pcontrol input", g.pDiv).keydown(function(e) {
        if (e.keyCode == 13) {
          g.changePage("input");
        }
      });
      if (browser.msie && browser.version < 7) {
        $(".pButton", g.pDiv).hover(function() {
          $(this).addClass("pBtnOver");
        }, function() {
          $(this).removeClass("pBtnOver");
        });
      }
      if (p.useRp) {
        var opt = "", sel = "";
        for (var nx = 0;nx < p.rpOptions.length;nx++) {
          if (p.rp == p.rpOptions[nx]) {
            sel = 'selected="selected"';
          } else {
            sel = "";
          }
          opt += "<option value='" + p.rpOptions[nx] + "' " + sel + " >" + p.rpOptions[nx] + "&nbsp;&nbsp;</option>";
        }
        $(".pDiv2", g.pDiv).prepend("<div class='pGroup'><select name='rp'>" + opt + "</select></div> <div class='btnseparator'></div>");
        $("select", g.pDiv).change(function() {
          if (p.onRpChange) {
            p.onRpChange(+this.value);
          } else {
            p.newp = 1;
            p.rp = +this.value;
            g.populate();
          }
        });
      }
      if (p.searchitems) {
        $(".pDiv2", g.pDiv).prepend("<div class='pGroup'> <div class='pSearch pButton'><span></span></div> </div>  <div class='btnseparator'></div>");
        $(".pSearch", g.pDiv).click(function() {
          $(g.sDiv).slideToggle("fast", function() {
            $(".sDiv:visible input:first", g.gDiv).trigger("focus");
          });
        });
        g.sDiv.className = "sDiv";
        var sitems = p.searchitems;
        var sopt = "", sel = "";
        for (var s = 0;s < sitems.length;s++) {
          if (p.qtype === "" && sitems[s].isdefault === true) {
            p.qtype = sitems[s].name;
            sel = 'selected="selected"';
          } else {
            sel = "";
          }
          sopt += "<option value='" + sitems[s].name + "' " + sel + " >" + sitems[s].display + "&nbsp;&nbsp;</option>";
        }
        if (p.qtype === "") {
          p.qtype = sitems[0].name;
        }
        $(g.sDiv).append("<div class='sDiv2'>" + p.findtext + " <input type='text' value='" + p.query + "' size='30' name='q' class='qsbox' /> " + " <select name='qtype'>" + sopt + "</select></div>");
        $("input[name=q]", g.sDiv).keydown(function(e) {
          if (e.keyCode == 13) {
            g.doSearch();
          }
        });
        $("select[name=qtype]", g.sDiv).keydown(function(e) {
          if (e.keyCode == 13) {
            g.doSearch();
          }
        });
        $("input[value=Clear]", g.sDiv).click(function() {
          $("input[name=q]", g.sDiv).val("");
          p.query = "";
          g.doSearch();
        });
        $(g.bDiv).after(g.sDiv);
      }
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
    return this.each(function() {
      data = this.p.data;
      data.totalCount++;
      data.resultList[data.resultList.length] = row;
      if (this.grid) {
        this.grid.addData(data);
      }
    });
  };
  $.fn.flexRemoveRow = function(rowIndex) {
    return this.each(function() {
      data = this.p.data;
      data.totalCount--;
      data.resultList.splice(rowIndex, 1);
      if (this.grid) {
        this.grid.addData(data);
      }
    });
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
  $.fn.flexGetData = function(p) {
    return p.data;
  };
  $.fn.selectFilterData = function(filter) {
    var arReturn = [];
    var arRow = [];
    var selector = $(this.selector)[0].p;
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

