<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
  /**
  * @Class Name : gisInfoPage.jsp
  * @Description : 실내공간 조회 화면 (RELEASE)
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2013.10.24            최초 생성
  *
  * author 장은성
  * since 2013.10.24
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<!DOCTYPE html>
<html lang="ko" xml:lang="ko">
  <head>
    <title>여수광양항만공사 - GIS기반 자산관리 시스템 (LOCAL)</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">

<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/reset.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/emd_desktop.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/ygpa_desktop.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/theme/jquery-ui-1.10.4.custom.css' />" />
<link rel="stylesheet" href="<c:url value='/css/jquery.sidr.light.css'/>">

<link rel="stylesheet" href="<c:url value='/js/codebase/dhtmlx.css'/>">

<style>
html, body {
        width: 100%;
        height: 100%;
        margin: 0px;
        overflow: hidden;
   }
</style>

<script src="<c:url value='/js/OpenLayers.js'/>"></script>
<script src="<c:url value='/js/jquery-1.10.2.min.js'/>"></script>
<script src="<c:url value='/js/jquery-migrate-1.2.1.min.js'/>"></script>
<script src="<c:url value='/js/jquery-ui.min.js'/>"></script>

<script src="<c:url value='/js/Proj4js/proj4js.js'/>"></script>
<script src="<c:url value='/js/Proj4js/defs/EPSG5181.js'/>"></script>
<script src="<c:url value='/js/Proj4js/defs/EPSG5186.js'/>"></script>
<script src="<c:url value='/js/Proj4js/defs/EPSG4326.js'/>"></script>

<script src="<c:url value='/js/codebase/dhtmlx.js'/>"></script>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<script src="<c:url value='/js/DynamicMeasure.js'/>"></script>
<script src="<c:url value='/js/indoor_map.js'/>"></script>
<script>

var context_root = "<c:url value='/' />";
OpenLayers.ProxyHost = context_root+"proxy.jsp?url=";

jQuery(document).ready(function() {
	var floor=[
	            {
					name: '1 층',
					layer: '1'
				},
				{
					name: '4 층',
					layer: '4'
				},
				{
					name: '5 층',
					layer: '5'
				},
				{
					name: '지붕층',
					layer: 'ROOF'
				}
		];

		initLayout();

		var map=initMap('GL_PL', 'GL_ROOM', floor, [0, 0, 380, 130]);

		var formData = [
		                 {type:"settings",position:"label-left"},
		                 {type: "label", width:200, label: "황금물류센터 실내공간정보"}
			/* ,
		                 {type:"newcolumn"},
		                 {type: "input", width: 300, name: 'searchKeyword', label: '시설 검색:'},
		                 {type:"newcolumn"},
	                     {type: "button", name:"seach", value:"검색"} */
		             ];

		var myform = headerCell.attachForm(formData);

		var layerForm = [
		                 {type:"settings",position:"label-right"}
		             ];
		$.each(floor, function(key, value) {
			layerForm[layerForm.length] = {
					type:"radio", name:"layer", value:this.layer, label:this.name,checked:key==0
			};
		});

		layerForm[layerForm.length] = {
				type:"button", name:"editLayer", value:"레이어 편집"
		};

		var catForm=categoryCell.attachForm(layerForm);

		catForm.attachEvent("onButtonClick", function(name, value) {
			if(name=="editLayer") {
				editLayer();
			}
		});

		catForm.attachEvent("onChange", function(name, value) {
			if(name=="layer") {
				switchFloor(value);
			}
		});

		var propertyFormData = [
		                 {type:"settings",position:"label-top"},
		                 {type: "hidden", name:"ROOM_ID"},
		                 {type: "input", name:"ROOM_NM", label: "공간 정보 명"},
		                 {type: "input", name:"STD", label: "규격", rows:2},
		                 {type: "input", name:"INFO", label: "정보", rows:2},
		                 {type: "input", name:"RMK", label: "설명", rows:3},
		                 {type: "input", name:"REG_USER", label: "등록자", readonly:true},
		                 {type: "input", name:"REG_DT", label: "등록일", readonly:true},
		                 {type: "input", name:"UPD_USER", label: "수정인", readonly:true},
		                 {type: "input", name:"UPD_DT", label: "수정일자", readonly:true},
	                     {type: "button", name:"btnSave", value:"저장"}
		             ];

		var propertyForm=propertyCell.attachForm(propertyFormData);
		propertyForm.attachEvent("onButtonClick", function(name, value) {
			if(name=="btnSave") {
				propertyForm._feature.attributes=propertyForm.getFormData();
				if(propertyForm._feature.state==OpenLayers.State.INSERT) {
					propertyForm._feature.attributes.REG_USER=$('#userId').val();
				}
				else {
					propertyForm._feature.attributes.UPD_USER=$('#userId').val();
				}
				saveFeature(propertyForm._feature);
			}
		});

		map.events.register('featureselected', propertyForm, function(e) {
			if(propertyCell.isCollapsed()) {
				propertyCell.expand();
				propertyForm.adjustParentSize();
			}
			propertyForm.clear();
			propertyForm.setFormData(e.attributes);
			propertyForm._feature=e;
		});
		map.events.register('featureunselected', propertyForm, function(e) {
			if(!propertyCell.isCollapsed()) {
				propertyCell.collapse();
			}
			propertyForm.clear();
		});

	});
</script>
</head>
<body>
<input id="userId" type="hidden" value="<c:out value='${updUsr }'/>" />
	<div id="div_map" style="display:none;"></div>
</body>
</html>

