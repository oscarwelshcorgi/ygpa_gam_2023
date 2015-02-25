<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<html lang="ko" xml:lang="ko">
  <head>
    <title>여수광양항만공사 - GIS기반 자산관리 시스템 (RELEASE)</title>
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

<!--[if lt IE 9]>
<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/ie.css'/>" />
<![endif]-->

    <script src="<c:url value='/js/OpenLayers.js'/>"></script>
    <script src="<c:url value='/js/jquery-1.10.2.min.js'/>"></script>
    <script src="<c:url value='/js/jquery-migrate-1.2.1.min.js'/>"></script>
    <script src="<c:url value='/js/jquery-ui.min.js'/>"></script>
    <script src="<c:url value='/js/jquery.sidr.min.js'/>"></script>

	<script src="<c:url value='/js/Proj4js/proj4js.js'/>"></script>
	<script src="<c:url value='/js/Proj4js/defs/EPSG5181.js'/>"></script>
	<script src="<c:url value='/js/Proj4js/defs/EPSG5186.js'/>"></script>

    <script src="<c:url value='/js/codebase/dhtmlx.js'/>"></script>

    <script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
    <script src="<c:url value='/js/emf_map.ygpa_gam.js'/>"></script>

    <script type="text/javascript">
	var $DEBUG=false;

       jQuery(document).ready(function() {
    	   var frmwrkMenu=null;
    	   Proj4js.libPath = '${pageContext.request.contextPath}/js/Proj4js/';
	    	<c:if test="${frmwrkMenu!=null}">
	   	   	frmwrkMenu = [
					<c:forEach items="${frmwrkMenu }" var="menuItem" varStatus="menuStatus">
						{
							menuNo: '<c:out value="${menuItem.menuNo }"/>',
							menuNm: '<c:out value="${menuItem.menuNm }"/>',
							url: '<c:out value="${menuItem.url }"/>',
							<c:if test="${fn:contains(menuItem, 'submenu')}">
							submenu: [
										<c:forEach items="${menuItem.submenu }" var="subMenu" varStatus="status">
										{
											menuNo: '<c:out value="${subMenu.menuNo }"/>',
											menuNm: '<c:out value="${subMenu.menuNm }"/>',
											url: '<c:out value="${subMenu.url }"/>',
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
	    	EMD.go("${pageContext.request.contextPath}", "http://192.168.0.71:8092/G2DataService/2d/Base/201310", "http://192.168.0.71:8092/G2DataService/GService?", "${pageContext.request.scheme}://${pageContext.request.serverName}", frmwrkMenu);
    	 });

    </script>
  </head>
  <body>
    <div class="abs" id="wrapper">
        <div id="desktop" class="abs mapdesk">
        </div>
        <div id="legendPanel" class="legendPanel">
        	<div id="archGroup"></div>
        	<div id="civilGroup"></div>
        	<div id="mechGroup"></div>
        	<div id="elecGroup"></div>
        	<div id="infoGroup"></div>
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
          		<a href="#" data-role="LoadModule" data-url="<c:url value='${menuItem2.url }'/>"><c:out value="${menuItem2.menuNm }"/></a>
    			<c:if test="${menuItem2.submenu!=null }">
    				<ul class="submenu">
			    		<c:forEach items="${menuItem2.submenu }" var="menuItem3">
			                <li>
			                	<a href="#" data-role="LoadModule" data-url="<c:url value='${menuItem3.url }'/>"><c:out value="${menuItem3.menuNm }"/></a>
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
            </ul>
        </li>
        </ul>
  </div>
    <div class="abs" id="bar_bottom">
        <a class="float_left" href="#" id="show_desktop" title="Show Desktop">
            지도
          <!-- img src="<c:url value='/assets/images/icons/icon_22_desktop.png'/>"/-->
        </a>

        <ul id="dock">
        </ul>
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
    <a class="prev"><span class="ui-icon ui-icon-carat-1-w" /></a>
    <a class="next"><span class="ui-icon ui-icon-carat-1-e" /></a>
    <a class="close"><span class="ui-icon ui-icon-close" /></a>
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
    <a class="prev"><span class="ui-icon ui-icon-carat-1-w" /></a>
    <a class="next"><span class="ui-icon ui-icon-carat-1-e" /></a>
    <a class="close"><span class="ui-icon ui-icon-close" /></a>
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

