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
<link rel="stylesheet" href="<c:url value='/css/jquery.fileupload.css' />">
<link rel="stylesheet" href="<c:url value='/css/jquery.fileupload-ui.css' />">
<link rel="stylesheet" href="<c:url value='/css/flexigrid.ygpa.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/jquery.sidr.light.css'/>">

<link rel="stylesheet" href="<c:url value='/js/codebase/dhtmlx.css'/>">

<%-- <link rel="stylesheet" href="<c:url value='/css/jtree/themes/default/style.min.css'/>">
 --%><!--[if lt IE 9]>
<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/ie.css'/>" />
<![endif]-->

<style>

html, body {
        width: 100%;
        height: 100%;
        margin: 0px;
        overflow: hidden;
   }
</style>

<script src="<c:url value='/js/OpenLayers.debug.js'/>"></script>
<script src="<c:url value='/js/jquery-1.10.2.min.js'/>"></script>
<script src="<c:url value='/js/jquery-migrate-1.2.1.min.js'/>"></script>
<script src="<c:url value='/js/jquery-ui.min.js'/>"></script>
<script src="<c:url value='/js/jquery.sidr.min.js'/>"></script>

<script src="<c:url value='/js/Proj4js/proj4js.js'/>"></script>
<script src="<c:url value='/js/Proj4js/defs/EPSG5181.js'/>"></script>
<script src="<c:url value='/js/Proj4js/defs/EPSG5186.js'/>"></script>
<script src="<c:url value='/js/Proj4js/defs/EPSG4326.js'/>"></script>

<script src="<c:url value='/js/codebase/dhtmlx.js'/>"></script>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<script src="<c:url value='/js/DynamicMeasure.js'/>"></script>
<script src="<c:url value='/js/nl_map.js'/>"></script>
<script>

var context_root = "<c:url value='/' />";
var gis_engine_url = "http://192.168.200.61:8080/G2DataService/GService?";
OpenLayers.ProxyHost = context_root+"/proxy.jsp?url=";

</script>
</head>
<body>
	<div id="div_map" style="display:none;"></div>
</body>
</html>

