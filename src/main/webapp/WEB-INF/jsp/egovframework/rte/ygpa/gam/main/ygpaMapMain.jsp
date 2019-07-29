<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- 맵 디버깅 용 변수 값을 true로 하면 지적도 저장 기능이 나온다. --%>
<c:set var="_mapDebug" value="false"/>
<%
  /**
  * @Class Name : ygpaMapMain.jsp
  * @Description : 맵 조회 화면 (RELEASE)
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
<html>
  <head>
    <title>여수광양항만공사 - GIS기반 자산관리 시스템 (LOCAL)</title>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
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

<link rel="stylesheet" type="text/css" href="<c:url value="/css/fa-all.min.css" />">

<link rel="stylesheet" href="<c:url value='/js/codebase/dhtmlx.css'/>">

<%-- <link rel="stylesheet" href="<c:url value='/css/jtree/themes/default/style.min.css'/>">
 --%><!--[if lt IE 9]>
<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/ie.css'/>" />
<![endif]-->

    <style>
/*       html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
      }
 */    </style>

    <script src="<c:url value='/js/jquery-1.10.2.min.js'/>"></script>
    <script src="<c:url value='/js/jquery-migrate-1.2.1.min.js'/>"></script>
    <script src="<c:url value='/js/jquery-ui.min.js'/>"></script>
    <script src="<c:url value='/js/jquery.sidr.min.js'/>"></script>
    <script src="<c:url value='/js/jquery.table2excel.js'/>"></script>

    <script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

    <script src="<c:url value='/js/codebase/dhtmlx.js'/>"></script>
    <script src="<c:url value='/js/emf_map.ygpa_gam_2019.js'/>"></script>
    <script src="<c:url value='/js/emf.map.desktop.js'/>"></script>

    <script type="text/javascript">
	var $DEBUG=${_mapDebug};
	var wikiUrl="http://192.168.200.61:8100/wiki/Wiki.jsp?";

    jQuery(document).ready(function() {
 	   var frmwrkMenu=null;
	    	<c:if test="${frmwrkMenu!=null}">
	   	   	frmwrkMenu = [
					<c:forEach items="${frmwrkMenu }" var="menuItem" varStatus="menuStatus">
						{
							menuNo: '<c:out value="${menuItem.menuNo }"/>',
							menuNm: '<c:out value="${menuItem.menuNm }"/>',
							url: '<c:out value="${menuItem.url }"/>',
							progrmFileNm: '<c:out value="${menuItem.progrmFileNm }"/>',
							<c:if test="${fn:contains(menuItem, 'submenu')}">
							submenu: [
										<c:forEach items="${menuItem.submenu }" var="subMenu" varStatus="status">
										{
											menuNo: '<c:out value="${subMenu.menuNo }"/>',
											menuNm: '<c:out value="${subMenu.menuNm }"/>',
											url: '<c:out value="${subMenu.url }"/>',
											progrmFileNm: '<c:out value="${menuItem.progrmFileNm }"/>',
											progrmStrePath: '<c:out value="${subMenu.progrmStrePath }"/>'
										}
										<c:if test="${!status.last}">,</c:if>
										</c:forEach>
							          ]
							</c:if>
						}
						<c:if test="${!menuStatus.last}">,</c:if>
					</c:forEach>
				];
	   	   </c:if>

//	    	EMD.go("${pageContext.request.contextPath}", "http://192.168.0.71:8092/G2DataService/2d/Base/201310", "http://192.168.0.71:8092/G2DataService/GService?", "${pageContext.request.scheme}://${pageContext.request.serverName}", frmwrkMenu);
	    	EMD.go("${pageContext.request.contextPath}", "http://xdworld.vworld.kr:8080/2d/Base/201310", "http://192.168.100.70:8080/G2DataService/GService?", "${pageContext.request.scheme}://${pageContext.request.serverName}", frmwrkMenu);
//	    	EMD.go("${pageContext.request.contextPath}", "http://192.168.0.71:8092/G2DataService/2d/Base/201310", "http://192.168.0.71:8092/G2DataService/GService?", "${pageContext.request.scheme}://${pageContext.request.serverName}", frmwrkMenu);
 	 });

    function getAssetsCdStyleMap() {
    	var assetCdStyle = new OpenLayers.Style({
				strokeColor: "#000000",
				strokeOpacity: 1,
				strokeWidth: 1,
				fillColor: "#303294",
				fillOpacity: 0.5,
				pointRadius: 6,
				pointerEvents: "visiblePainted",
				label : "\${ASSETS_NM}",
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
    	{
    		rules: [
    			new OpenLayers.Rule({
    				filter: new OpenLayers.Filter.Comparison({
    					type: OpenLayers.Filter.Comparison.EQUAL_TO,
    					property: "AS_TP",
    					value: 'W',
    				}),
    				symbolizer: {
   						strokeColor: "#000000",
   						fillColor: "#303294"
    				}
    			}),
    			new OpenLayers.Rule({
    				filter: new OpenLayers.Filter.Comparison({
    					type: OpenLayers.Filter.Comparison.EQUAL_TO,
    					property: "AS_TP",
    					value: 'A',
    				}),
    				symbolizer: {
    					strokeColor: "#000000",
    					fillColor: "#803294"
    				}
    			}),
    			new OpenLayers.Rule({
    				filter: new OpenLayers.Filter.Comparison({
    					type: OpenLayers.Filter.Comparison.EQUAL_TO,
    					property: "AS_TP",
    					value: 'L',
    				}),
    				symbolizer: {
    					strokeColor: "#000000",
    					fillColor: "#80ced6"
    				}
    			}),
    			new OpenLayers.Rule({
    				filter: new OpenLayers.Filter.Comparison({
    					type: OpenLayers.Filter.Comparison.EQUAL_TO,
    					property: "AS_TP",
    					value: 'U',
    				}),
    				symbolizer: {
    					strokeColor: "#000000",
    					fillColor: "#f7786b"
    				}
    			}),
    			new OpenLayers.Rule({
    				filter: new OpenLayers.Filter.Comparison({
    					type: OpenLayers.Filter.Comparison.EQUAL_TO,
    					property: "AS_TP",
    					value: 'S',
    				}),
    				symbolizer: {
    					strokeColor: "#000000",
    					fillColor: "#ffef96"
    				}
    			}),
    			new OpenLayers.Rule({
    				filter: new OpenLayers.Filter.Comparison({
    					type: OpenLayers.Filter.Comparison.EQUAL_TO,
    					property: "AS_TP",
    					value: 'w',
    				}),
    				symbolizer: {
    					strokeColor: "#000000",
    					fillColor: "#618685"
    				}
    			}),
    			new OpenLayers.Rule({
    				filter: new OpenLayers.Filter.Comparison({
    					type: OpenLayers.Filter.Comparison.EQUAL_TO,
    					property: "ASSETS_SE_CD",
    					value: '1',
    				}),
    				symbolizer: {
   						strokeColor: "#000000",
   						fillColor: "#FF0000",
   						fillOpacity: 0.75
    				}
    			}),
    			new OpenLayers.Rule({
    				filter: new OpenLayers.Filter.Comparison({
    					type: OpenLayers.Filter.Comparison.EQUAL_TO,
    					property: "ASSETS_SE_CD",
    					value: '2',
    				}),
    				symbolizer: {
    					strokeColor: "#000000",
    					fillColor: "#00FF00",
   						fillOpacity: 0.75
    				}
    			}),
    			new OpenLayers.Rule({
    				filter: new OpenLayers.Filter.Comparison({
    					type: OpenLayers.Filter.Comparison.EQUAL_TO,
    					property: "ASSETS_SE_CD",
    					value: '4',
    				}),
    				symbolizer: {
    					strokeColor: "#000000",
    					fillColor: "#0000FF",
   						fillOpacity: 0.75
    				}
    			}),
    			new OpenLayers.Rule({
    				elseFilter: true,
    				symbolizer: {
    					strokeColor: "#000000",
    					fillColor: "#A0A0A0",
   						fillOpacity: 0.75
    				}
    			})
    		]
    	});

		return new OpenLayers.StyleMap(assetCdStyle, {extendDefault: true});

    }
    </script>
  </head>
  <body>
    <div class="abs" id="wrapper">
        <div id="desktop" class="abs mapdesk">
        </div>
        <div id="legendPanel" class="legendPanel">
        </div>
        <div id="sideMenu">
          <ul>
            <li><a href="#">주소 찾기</a></li>
            <li class="active"><a href="#">메뉴2</a></li>
            <li><a href="#">메뉴3</a></li>
          </ul>
        </div>
      <div class="abs" id="bar_top">
    <span class="float_right" id="clock"></span>
    <ul>    <!-- 메뉴 -->
    <c:forEach items="${mainMenu }" var="menuItem">
		<li><a class="menu_trigger" href="#"><c:out value="${menuItem.menuNm }"/></a>
          <ul class="menu">
    	<c:if test="${menuItem.submenu!=null }">
	    	<c:forEach items="${menuItem.submenu }" var="menuItem2">
          	<li>
          		<a href="#" data-role="LoadModule" data-progrm-file-nm="${menuItem2.progrmFileNm }" data-url="${menuItem2.url }" data-nav="${menuItem.menuNm } > ${menuItem2.menuNm }"><c:out value="${menuItem2.menuNm }"/></a>
    			<c:if test="${menuItem2.submenu!=null }">
    				<ul class="submenu">
			    		<c:forEach items="${menuItem2.submenu }" var="menuItem3">
			                <li>
			                	<a href="#" data-role="LoadModule" data-progrm-file-nm="${menuItem3.progrmFileNm }" data-url="<c:out value='${menuItem3.url }'/>" data-nav="${menuItem.menuNm } > ${menuItem2.menuNm } > ${menuItem3.menuNm }" ><c:out value="${menuItem3.menuNm }"/></a>
		                	</li>
	                    </c:forEach>
                    </ul>
                </c:if>
            </li>
            </c:forEach>
        </c:if>
        </ul>
        </li>
    </c:forEach>

        <li>
            <a class="menu_trigger" href="#">창관리</a>
            <ul class="menu">
                <li>
                    <a href="#" data-role="CloseAllWindow">모든창 닫기</a>
                </li>
                <li>
                    <a href="#" data-role="MinimizeAllWindow">모든창 최소화</a>
                </li>
                <li>
                    <a href="#" data-role="logout">로그아웃</a>
                </li>
                <li>
                    <a href="#" data-role="getUserInfo">사용자정보 갱신</a>
                </li>
            </ul>
        </li>
        <li>
            <a class="menu_trigger" href="#">매뉴얼</a>
            <ul class="menu">
                <li>
                    <a href="<c:url value='/manual/htld_manual_v2.0.pdf' />">배후단지 매뉴얼</a>
                </li>
                <li>
                    <a href="<c:url value='/manual/oper_gen_v1.5.pdf' />">항만시설운영(일반부두) 매뉴얼</a>
                </li>
                <li>
                    <a href="<c:url value='/manual/fclty_manual_v1.1.pdf' />">시설관리 매뉴얼</a>
                </li>
                <li>
                    <a href="<c:url value='/manual/asset_code_manual.pdf' />">자산코드관리 매뉴얼</a>
                </li>
            </ul>
        </li>
        <c:if test="${_mapDebug }">
        <li><a class="menu_trigger" href="#">샘플</a>
					<ul class="menu">
						<li><a href="#"><b>자산정보 관리</b></a>
							<ul class="submenu">
								<li><a href="#" data-role="LoadModule" data-url="<c:url value='/sample/gamAssetMngt.do'/>">자산정보관리</a></li>
								<li><a href="#" data-role="LoadModule" data-url="<c:url value='/sample/gamAssetRentMngt.do'/>">자산임대관리</a></li>
								<li><a href="#" data-role="LoadModule" data-url="<c:url value='/sample/gamAuthorRoleMng.do'/>">권한 롤 관리</a></li>
								<li><a href="#" data-role="LoadModule" data-url="<c:url value='/sample/gamAuthorGrpMng.do'/>">권한 그룹 관리</a></li>
								<li><a href="#" data-role="LoadModule" data-url="<c:url value='/sample/gamAuthorGrpMng.do'/>">권한 그룹 관리</a></li>
							</ul>
							</li>
						<li><a href="#"><b>테스트</b></a>
							<ul class="submenu">
								<li><a href="#" data-role="LoadModule" data-url="<c:url value='/eap/showEapGwCallInterfaceView.do'/>">전자결재 테스트</a></li>
								<li><a href="#" data-role="startLogLandCode">지적도 기록</a></li>
								<li><a href="#" data-role="saveLandCode">지적도 저장</a></li>
							</ul>
							</li>
					</ul>
				</li>
				</c:if>
        </ul>
  </div>
    <div class="abs" id="bar_bottom">
        <a class="float_left" href="#" id="show_desktop" title="Show Desktop">
            지도
          <!-- img src="<c:url value='/assets/images/icons/icon_22_desktop.png'/>"/-->
        </a>

        <ul id="dock">
        </ul>
        <a class="float_right" href="#" id="store_map" title="지도저장" data-role="StoreMap">
        지도저장
        </a>
    </div>
    <div class="abs" id="cat_menu">
        <a class="button" href="#" data-role="popup-searchAddr">주소 검색</a>
    </div>
    </div>  <!-- div wrapper -->
    <div id="progress_dialog" title="로딩중...">
	<p><img alt="로딩중 입니다." style="width:100%; height:10px;" src="<c:url value='/images/egovframework/rte/progress.gif' />" /></p>
</div>
<div id="__templateXls" style="display:none;"></div>
<div id="__tempDiv" style="display:none;"></div>
<div id="file_upload_dialog" title="업로드 파일">
<form id="fileupload" method="POST" enctype="multipart/form-data">
	<input name="type" type="hidden" value="genericFileMulti"/>
    <div class="fileupload-buttonbar">
        <div class="fileupload-buttons">
            <!-- The fileinput-button span is used to style the file input field as button -->
            <span class="fileinput-button">
                <span>파일 추가...</span>
                <input type="file" name="files[]" multiple>
            </span>
<!--             <button type="submit" class="start">업로드 시작</button>
            <button type="reset" class="cancel">업로드 취소</button> -->
            <button type="button" class="delete">삭제</button>
            <input type="checkbox" class="toggle">
            <!-- The global file processing state -->
            <span class="fileupload-process"></span>
        </div>
        <!-- The global progress state -->
        <div class="fileupload-progress fade" style="display:none">
            <!-- The global progress bar -->
            <div class="progress" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
            <!-- The extended global progress state -->
            <div class="progress-extended">&nbsp;</div>
        </div>
    </div>
    <!-- The table listing the files available for upload/download -->
    <table role="presentation"><tbody class="files"></tbody></table>
    <!-- The blueimp Gallery widget -->
<div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls" data-filter=":even">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev"><span class="ui-icon ui-icon-carat-1-w"></span></a>
    <a class="next"><span class="ui-icon ui-icon-carat-1-e"></span></a>
    <a class="close"><span class="ui-icon ui-icon-close"></span></a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
</div>
<!-- The template to display files available for upload -->
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td>
            <span class="preview"></span>
        </td>
        <td>
            <p class="name">{%=file.name%}</p>
            <strong class="error"></strong>
        </td>
        <td>
            <p class="size">전송중...</p>
            <div class="progress"></div>
        </td>
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="start" disabled>전송시작</button>
            {% } %}
            {% if (!i) { %}
                <button class="cancel">취소</button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade" data-physcal-file-nm="{%=file.fileName%}" data-logical-file-nm="{%=file.orizinalFileName%}">
        <td>
            <span class="preview">
                    <a href="{%=EMD.context_root%}/cmm/getImage.do?physicalFileNm={%=file.fileName%}" target="_blank" title="{%=file.orizinalFileName%}" download="{%=EMD.context_root%}/cmm/getImage.do?physicalFileNm={%=file.fileName%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
            </span>
        </td>
        <td>
            <p class="name">
                <a href="{%=EMD.context_root%}/cmm/getImage.do?physicalFileNm={%=file.fileName%}" title="{%=file.orizinalFileName%}" download="{%=EMD.context_root%}/cmm/getImage.do?physicalFileNm={%=file.fileName%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.orizinalFileName%}</a>
            </p>
            {% if (file.error) { %}
                <div><span class="error">에러</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=o.formatFileSize(file.fileSize)%}</span>
        </td>
        <td>
            <button class="delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>삭제</button>
            <input type="checkbox" name="delete" value="1" class="toggle">
        </td>
    </tr>
{% } %}
</script>

</form>

</div>
<div id="pfPhoto_upload_dialog" title="시설 사진 업로드">
<form id="pfPhotoupload" method="POST" enctype="multipart/form-data">
	<input name="type" type="hidden" value="genericFileMulti"/>
    <div class="fileupload-buttonbar">
        <div class="fileupload-buttons">
            <!-- The fileinput-button span is used to style the file input field as button -->
            <span class="fileinput-button">
                <span>파일 추가...</span>
                <input type="file" name="files[]" multiple>
            </span>
<!--             <button type="submit" class="start">업로드 시작</button>
            <button type="reset" class="cancel">업로드 취소</button> -->
            <button type="button" class="delete">삭제</button>
            <input type="checkbox" class="toggle">
            <!-- The global file processing state -->
            <span class="fileupload-process"></span>
        </div>
        <!-- The global progress state -->
        <div class="fileupload-progress fade" style="display:none">
            <!-- The global progress bar -->
            <div class="progress" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
            <!-- The extended global progress state -->
            <div class="progress-extended">&nbsp;</div>
        </div>
    </div>
    <!-- The table listing the files available for upload/download -->
    <table role="presentation"><tbody class="files"></tbody></table>
    <!-- The blueimp Gallery widget -->
<div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls" data-filter=":even">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev"><span class="ui-icon ui-icon-carat-1-w"></span></a>
    <a class="next"><span class="ui-icon ui-icon-carat-1-e"></span></a>
    <a class="close"><span class="ui-icon ui-icon-close"></span></a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
</div>
<!-- The template to display files available for upload -->
<script id="template-pfupload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td>
            <span class="preview"></span>
        </td>
        <td>
            <p class="name">{%=file.name%}</p>
            <strong class="error"></strong>
        </td>
        <td>
            <p class="size">전송중...</p>
            <div class="progress"></div>
        </td>
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="start" disabled>전송시작</button>
            {% } %}
            {% if (!i) { %}
                <button class="cancel">취소</button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
<!-- The template to display files available for download -->
<script id="template-pfdownload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade" data-physcal-file-nm="{%=file.fileName%}" data-logical-file-nm="{%=file.orizinalFileName%}">
        <td>
            <span class="preview">
                    <a href="{%=EMD.context_root%}/cmm/getPfImage.do?physicalFileNm={%=file.fileName%}" target="_blank" title="{%=file.orizinalFileName%}" download="{%=EMD.context_root%}/cmm/getPfImage.do?physicalFileNm={%=file.fileName%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
            </span>
        </td>
        <td>
            <p class="name">
                <a href="{%=EMD.context_root%}/cmm/getPfImage.do?physicalFileNm={%=file.fileName%}" title="{%=file.orizinalFileName%}" download="{%=EMD.context_root%}/cmm/getPfImage.do?physicalFileNm={%=file.fileName%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.orizinalFileName%}</a>
            </p>
            {% if (file.error) { %}
                <div><span class="error">에러</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=o.formatFileSize(file.fileSize)%}</span>
        </td>
        <td>
            <button class="delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>삭제</button>
            <input type="checkbox" name="delete" value="1" class="toggle">
        </td>
    </tr>
{% } %}
</script>

</form>

</div>	<!-- 시설 사진 업로드 -->

<div id="xlsfile_upload_dialog" title="엑셀 업로드 파일">
<form id="xlsfileupload" action="<c:url value='/code/GamExcelOlnlpRegist.do'/>" method="POST" enctype="multipart/form-data">
	<input name="type" type="hidden" value="genericFileMulti"/>
    <div class="fileupload-buttonbar">
        <div class="fileupload-buttons">
            <!-- The fileinput-button span is used to style the file input field as button -->
            <span class="fileinput-button">
                <span>파일 업로드...</span>
                <input type="file" name="files[]" multiple>
            </span>
<!--             <button type="submit" class="start">업로드 시작</button>
            <button type="reset" class="cancel">업로드 취소</button> -->
            <button type="button" class="delete">삭제</button>
            <input type="checkbox" class="toggle">
            <!-- The global file processing state -->
            <span class="fileupload-process"></span>
        </div>
        <!-- The global progress state -->
        <div class="fileupload-progress fade" style="display:none">
            <!-- The global progress bar -->
            <div class="progress" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
            <!-- The extended global progress state -->
            <div class="progress-extended">&nbsp;</div>
        </div>
    </div>
</form>

</div>
  </body>
</html>

