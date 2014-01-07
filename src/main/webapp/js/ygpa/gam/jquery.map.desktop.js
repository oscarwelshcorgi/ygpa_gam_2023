//
// Namespace - Module Pattern.
//
OpenLayers.ProxyHost = "proxy.cgi?url=";

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

var EMD = (function($, window, document, undefined) {
  // Expose innards of EMD.
  return {
    go: function() {
      for (var i in EMD.init) {
        EMD.init[i]();
      }
    },
    window_seq: 0,
    win_x: 80,
    win_y: 40,
    map: null,
    wfs: null,
    panel: null,
    draw: null,
    edit: null,
    del: null,
    save: null,
    saveStrategy:null,
    map_panel: null,
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
        var weekday = [
          'Sunday',
          'Monday',
          'Tuesday',
          'Wednesday',
          'Thursday',
          'Friday',
          'Saturday'
        ];

        // Array for month.
        var month = [
          'January',
          'February',
          'March',
          'April',
          'May',
          'June',
          'July',
          'August',
          'September',
          'October',
          'November',
          'December'
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

        // Cancel mousedown.
        d.mousedown(function(ev) {
//          if (!$(ev.target).closest(tags).length || $(ev.target).is('#desktop')) {
          if(!$(ev.target).is("a.menu_trigger")
        		  && !$(ev.target).is("a")
        		  && !$(ev.target).is("button")
        		  && !$(ev.target).is("input")
        		  && !$(ev.target).is("select")
        		  && !$(ev.target).is("textarea")
        		  && !$(ev.target).is('tr') ) {
        		  //&& ($(ev.target).is('#desktop') || $(ev.target).is('.window'))) {
            EMD.util.clear_active();
            ev.preventDefault();
            ev.stopPropagation();;
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

          if($(this).data('role')=='LoadModule') {
//        	  alert('프로그램아이디 : '+ $(this).attr('href') + ' : '+$(this).context.text + ' 창이 열립니다.');
        	  var x = '#window_'+ EMD.window_seq;
        	  var dock_id='#dock_'+ EMD.window_seq;
        	  
    		  EMD.window_seq++;
        	  
        	  // dock add
//        	  if(!$('#dock').find(x).length) {
        		  var li=document.createElement('li');
//        		  li.id=$(this).data('prgid');
        		  li.id=dock_id.substring(1);
        		  li.innerHTML='<a href="'+x+'">'+$(this).text()+'</a>';
        		  $('#dock')[0].appendChild(li);
//        	  }
        	  
//        	  var y = $(x).find('a').attr('href');
        	  
//              if ($(x).is(':hidden')) {
//                  $(x).remove().appendTo('#dock');
//                  $(x).show('fast');
//                }

        	  // window add
//        	  if(!$('#desktop').find(y).length) {
        		  var win = document.createElement('div');
        		  win.id=x.substring(1);
        		  win.innerHTML='<div class="abs window_inner">'+
        			  '<div class="window_top">'+
        				'<span class="float_left">'+
        				$(this).text() +
        				'</span> <span class="float_right"> <a href="#" class="window_min"></a><a href="#" class="window_resize"></a> <a href="#'+win.id+'" class="window_close"></a></span>'+
        			'</div><div class="abs window_content"></div></div><span class="abs ui-resizable-handle ui-resizable-se"></span><div class="abs window_bottom ">여수광양항만공사 ver : 20131024</div>';
        		  $('#desktop')[0].appendChild(win);
        		  $(x).addClass('abs');
        		  $(x).addClass('window');
        		  if($(this).data('url')!=undefined) {
        			  $(x+ ' .window_content' ).load($(this).data('url'), {"window_id": x.substring(1), "choices[]": ["prtCd", "data"]},
	        			  function( response, status, xhr ) {
	        				  if ( status == "error" ) {
	        				    var msg = "Sorry but there was an error: ";
	        				    alert( msg + xhr.status + " " + xhr.statusText );
	        				  }
	        				  else {
	        					  var win_id=$("#window_id").val();
	        					  var win_id_num=win_id.substring(win_id.lastIndexOf('_')+1)*1;
	        					  $("#window_id").remove();
//	        					  EMD.module_instance[win_id_num].push(module_instance);
	        					  EMD.module_instance[win_id_num]=module_instance;
//	        					  EMD.module_instance[win_id.substring(win_id.lastIndexOf('_')+1)]=module_instance;
//	        					  module_script.prototype=new EmdModule();
	        					  module_instance.setWindowId(win_id);
	        					  EMD.util.modify_window(win_id);
	        					  EMD.module_instance[win_id_num].loadComplete();
	        					  EMD.util.window_move(win_id, EMD.win_x, EMD.win_y);
	        					  EMD.win_x+=30;
	        					  EMD.win_y+=30;
	        					  if(EMD.win_x>380) {
	        						  EMD.win_x=80;
	        						  EMD.win_y=40;
	        					  }
	        				  }
	        				});
        		  }
//        	  }
                // Bring window to front.
                EMD.util.window_flat();
                $(x).addClass('window_stack').show();
                EMD.util.clear_active();
           }
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
          if (x.is(':visible')) {
            x.hide();
          }
          else {
            // Bring window to front.
            EMD.util.window_flat();
            x.show().addClass('window_stack');
          }
        });

        // Focus active window.
        d.on('mousedown', 'div.window', function() {
          // Bring window to front.
          EMD.util.window_flat();
          $(this).addClass('window_stack');
        });

        // Make windows draggable.
        d.on('mouseenter', 'div.window', function() {
          $(this).off('mouseenter').draggable({
            // Confine to desktop.
            // Movable via top bar only.
            cancel: 'a',
            containment: 'parent',
            handle: 'div.window_top'
          }).resizable({
            containment: 'parent',
            minWidth: 400,
            minHeight: 200
          });
        });

        // Double-click top bar to resize, ala Windows OS.
        d.on('dblclick', 'div.window_top', function() {
          EMD.util.window_resize(this);
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
          $(this).closest('div.window').hide();
        });

        // Maximize or restore the window.
        d.on('click', 'a.window_resize', function() {
          EMD.util.window_resize(this);
        });

        // Close the window.
        d.on('click', 'a.window_close', function() {
        	EMD.util.window_close($(this));
        });

        // Show desktop button, ala Windows OS.
        d.on('mousedown', '#show_desktop', function() {
          // If any windows are visible, hide all.
          if ($('div.window:visible').length) {
            $('div.window').hide();
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
      },
      wallpaper: function() {
        // Add wallpaper last, to prevent blocking.
        if ($('#desktop').length) {
//          $('body').prepend('<img id="wallpaper" class="abs" src="assets/images/misc/wallpaper.jpg" />');
        }
      },
      map_init: function() {
//    	  var mapOptions = {
//    			    zoom: 8,
//    			    center: new google.maps.LatLng(34.397, 126.644),
//    			    mapTypeId: google.maps.MapTypeId.ROADMAP
//    			  };
//		  var map = new google.maps.Map(document.getElementById('desktop'),
//		      mapOptions);

		  
		    EMD.map = new OpenLayers.Map({
		        div: "desktop",
		    });

		    var gphy = new OpenLayers.Layer.Google(
			    "Google Physical",
			    {type: google.maps.MapTypeId.TERRAIN}
			);
			var gmap = new OpenLayers.Layer.Google(
			    "Google Streets", // the default
			    {numZoomLevels: 20}
			);
			var ghyb = new OpenLayers.Layer.Google(
			    "Google Hybrid",
			    {type: google.maps.MapTypeId.HYBRID, numZoomLevels: 20}
			);
			var gsat = new OpenLayers.Layer.Google(
			    "Google Satellite",
			    {type: google.maps.MapTypeId.SATELLITE, numZoomLevels: 22}
			);
			
			var vworld = new OpenLayers.Layer.WMS("Vworld WMS Service", "http://map.vworld.kr/js/wms.do"
						, {
					SERVICE:"WMS",
					REQUEST:"GetMap",
					LAYERS: "LP_PA_CBND_BONBUN,LP_PA_CBND_BUBUN",
					STYLES:"GetMap",
					FORMAT:"image/png",
					VERSION:"1.3.0",
					CRS:"EPSG:4326",
					EXCEPTIONS:"text/xml",
					TRANSPARENT:false,
					APIKEY:"5E7D605D-BD5A-3BEE-8C20-62172A86B4AA",
					DOMAIN:"localhost"
				});
			
			saveStrategy = new OpenLayers.Strategy.Save();
//			SERVICE=WFS&
//			REQUEST=GetFeature&
//			TYPENAME=LT_C_UQ111&
//			BBOX=13987670,3912271,14359383,4642932&
//			VERSION=1.1.0&
//			MAXFEATURES=40&
//			SRSNAME=EPSG:900913&
//			OUTPUT=text/xml;subType=gml/3.1.1/profiles/gmlsf/1.0.0/0&
//			EXCEPTIONS=text/xml&
//			APIKEY=[APIKEY]&
//			DOMAIN=[DOMAIN]
		    EMD.wfs = new OpenLayers.Layer.Vector("Editable Features", {
		        strategies: [new OpenLayers.Strategy.BBOX(), saveStrategy],
		        projection: new OpenLayers.Projection("EPSG:4326"),
		        protocol: new OpenLayers.Protocol.WFS({
		            version: "1.1.0",
		            srsName: "EPSG:4326",
		            url: "http://map.vworld.kr/js/wfs.do?apiKey=5E7D605D-BD5A-3BEE-8C20-62172A86B4AA&DOMAIN=localhost",
//		            featureNS :  "http://opengeo.org",
//		            featureType: "restricted",
//		            geometryName: "the_geom",
//		            schema: "http://demo.opengeo.org/geoserver/wfs/DescribeFeatureType?version=1.1.0&typename=og:restricted"
		        })
		    }); 

	        EMD.panel = new OpenLayers.Control.Panel({
		        displayClass: 'customEditingToolbar',
		        allowDepress: true
		    });

			EMD.draw = new OpenLayers.Control.DrawFeature(
			        EMD.wfs, OpenLayers.Handler.Polygon,
			        {
			            title: "Draw Feature",
			            displayClass: "olControlDrawFeaturePolygon",
			            multi: true
			        }
			    );
    
			EMD.edit = new OpenLayers.Control.ModifyFeature(EMD.wfs, {
			        title: "Modify Feature",
			        displayClass: "olControlModifyFeature"
			    });
		
		    EMD.del = new DeleteFeature(EMD.wfs, {title: "Delete Feature"});
		   
			EMD.save = new OpenLayers.Control.Button({
			        title: "Save Changes",
			        trigger: function() {
			            if(edit.feature) {
			                edit.selectControl.unselectAll();
			            }
			            EMD.saveStrategy.save();
			        },
			        displayClass: "olControlSaveFeatures"
			    });

		    EMD.panel.addControls([EMD.save, EMD.del, EMD.edit, EMD.draw]);
		    EMD.map.addControl(EMD.panel);

			EMD.map.addLayers([vworld,gphy, gmap, ghyb, gsat]);

//			EMD.map.setCenter(new OpenLayers.LonLat(14211461.37, 4151377.18), 5);
			EMD.map.setCenter(new OpenLayers.LonLat(126.644, 34.397), 5);

		    EMD.map.addControl(new OpenLayers.Control.LayerSwitcher());
		    
		    EMD.map.addControl(
				new OpenLayers.Control.MousePosition({
				    prefix: '좌표 : ',
				    separator: ' | ',
				    numDigits: 6,
			        displayProjection: new OpenLayers.Projection("EPSG:4326"),
				})
			);
		    
		    EMD.map.addControl(
		    		new OpenLayers.Control.Scale()
		    );
		    
		    EMD.map.setCenter(new OpenLayers.Bounds(-140.444336,25.115234,-44.438477,50.580078).getCenterLonLat(), 3);

//			EMD.map.events.register("mousemove", EMD.map, function(e) {
//			    var position = this.events.getMousePosition(e);
//			    OpenLayers.Util.getElement("coords").innerHTML = position;
//			});

//		    EMD.map.zoomToMaxExtent();
//		    EMD.map.setCenter(new OpenLayers.LonLat(126.644, 34.397), 10);

      }
    },
    util: {
      //
      // Clear active states, hide menus.
      //
      clear_active: function() {
        $('a.active, tr.active').removeClass('active');
        $('ul.menu').hide();
      },
      //
      // Zero out window z-index.
      //
      window_flat: function() {
        $('div.window').removeClass('window_stack');
      },
      window_move: function(win_id, x, y) {
    	  $('#'+win_id).css({'left':x, 'top':y });
      },
      //
      // Resize modal window.
      //
      window_resize: function(el) {
        // Nearest parent window.
        var win = $(el).closest('div.window');

        // Is it maximized already?
        if (win.hasClass('window_full')) {
          // Restore window position.
          win.removeClass('window_full').css({
            'top': win.attr('data-t'),
            'left': win.attr('data-l'),
            'right': win.attr('data-r'),
            'bottom': win.attr('data-b'),
            'width': win.attr('data-w'),
            'height': win.attr('data-h')
          });
        }
        else {
          win.attr({
            // Save window position.
            'data-t': win.css('top'),
            'data-l': win.css('left'),
            'data-r': win.css('right'),
            'data-b': win.css('bottom'),
            'data-w': win.css('width'),
            'data-h': win.css('height')
          }).addClass('window_full').css({
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
      },
      window_close: function(win) {
          $(win.attr('href')).hide('fast');
          $(win.attr('href')).remove();
          var dock_id = win.attr('href').replace('window', 'dock');
          $(dock_id).hide('fast');
          $(dock_id).remove();
  
      },
      select: function(selector) {
      },
      modify_id: function(win_id,modify_item) {
    	  if(!modify_item.children().length) return;
    	  modify_item.children().each(function() {
    		  if($(this).children().length>0) EMD.util.modify_id(win_id, $(this));
    		  if($(this).attr("id")!=undefined) $(this).attr("id",win_id+"_"+$(this).attr("id"));
    	  });
      },
      modify_window: function(win_id) {
    	  EMD.util.modify_id(win_id, $('#'+win_id));
//    	  $('#'+win_id).children().each(function() {
//    		if($(this).id!="") $(this).id=win_id+"_"+$(this).id;
//    	  });
    	  $('#'+win_id).data('_win_id', win_id);
    	  $('#'+win_id).find(".emdTabPanel").data('_win_id', win_id);
  		$('#'+win_id).find(".emdTabPanel").each(function() {
  			$(this).find(".emdTab").data('_win_id', $(this).data('_win_id'));
  			$(this).find(".emdTab").each(function() {
  				$(this).attr('href', $(this).attr('href').replace('#', '#'+$(this).data('_win_id')+'_'));
  			});
  			$(this).tabs({heightStyle: "fill"});
  		});
  		
  		/*
  		 * $('#'+win_id).find("button").each({},function() {
  			if($(this.))
  			$(this).click({},function(e) {
  				
  			});
  		}*/
  		
//  		$('#'+win_id).find(".emdTab").each(function() {
//  			$(this).click(function(){
//  				$(this).attr('href')
//  			});
//  		});
//  		
		$('#'+win_id).find(".emdcal").each(function() {
			$(this).datepicker({
			showOn: "button",
//		    buttonImage: "images/calendar.gif",
// 		    buttonImageOnly: true,
		    dateFormat: 'yymmdd',
		    prevText: '이전 달',
		    nextText: '다음 달',
		    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		    dayNames: ['일','월','화','수','목','금','토'],
		    dayNamesShort: ['일','월','화','수','목','금','토'],
		    dayNamesMin: ['일','월','화','수','목','금','토'],
		    changeMonth: true,
		    changeYear: true
		  });
		  });
//		$(win_id+" .emdTab").click(win_id, function(win_id){
//			var display = ($(this).attr('id'))+"bottom";
//			$(win_id+" .emd").attr("style","display:none");
//			$("#"+display).attr("style","display:inline");
//		};);
      }
    }
  };
// Pass in jQuery.
})(jQuery, this, this.document);

// module define

function EmdModule() {
};

EmdModule.prototype.getSelect = function(selector) {
	alert('get select = '+this._window_id+' '+selector);
};

EmdModule.prototype.showAlert = function(msg) {
	alert('alert id = '+this._window_id+' '+msg);
};

EmdModule.prototype.setWindowId = function(value) {
	this._window_id = value;
	this._window = $(value);
};

EmdModule.prototype.getWindowId = function() {
	return this._window_id;
};

EmdModule.prototype.$ = function(selector) {
	return $('#'+this._window_id).find(selector.replace('#', '#'+this._window_id+'_'));
};

EmdModule.prototype.makeFormArgs = function(selector) {
	var args = [];
	var win_id=this._window_id;
	this.$(selector).find(':input').each( function() {
		var input_id=$(this).attr('id');
		if(typeof input_id == 'string') {
			input_id=input_id.replace(win_id+'_', '');
			args[args.length]={
					name: input_id,
					value: $(this).val()
			};
		}
	});
	return args;
};
//
// Kick things off.
//
jQuery(document).ready(function() {
  EMD.go();
});
