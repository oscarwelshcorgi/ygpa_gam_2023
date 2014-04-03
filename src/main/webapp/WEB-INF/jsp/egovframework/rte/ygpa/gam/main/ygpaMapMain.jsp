<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : ygpaMapMain.jsp
  * @Description : 맵 조회 화면 (DEBUG)
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
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
  <head>
    <title>여수광양항만공사 - GIS기반 자산관리 시스템 (DEBUG)</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">

<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/reset.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/emd_desktop.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/ygpa_desktop.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/demo/jquery-ui-1.10.4.custom.css' />" />
<link rel="stylesheet" href="<c:url value='/css/jquery.fileupload.css' />">
<link rel="stylesheet" href="<c:url value='/css/jquery.fileupload-ui.css' />">
<link rel="stylesheet" href="<c:url value='/css/flexigrid.ygpa.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/jquery.sidr.light.css'/>">
<link rel="stylesheet" href="<c:url value='/css/jtree/themes/default/style.min.css'/>">
<!--[if lt IE 9]>
<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/ie.css'/>" />
<![endif]-->

    <style>
/*       html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
      }
 */    </style>
    <script src="http://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&language=ko"></script>

    <script src="<c:url value='/js/OpenLayers.js'/>"></script>
    <script src="<c:url value='/js/jquery-1.10.2.min.js'/>"></script>
    <script src="<c:url value='/js/jquery-migrate-1.2.1.min.js'/>"></script>
    <script src="<c:url value='/js/jquery-ui.min.js'/>"></script>
    <script src="<c:url value='/js/jquery.sidr.min.js'/>"></script>

	<script src="<c:url value='/js/Proj4js/proj4js.js'/>"></script>
	<script src="<c:url value='/js/Proj4js/defs/EPSG5181.js'/>"></script>
	<script src="<c:url value='/js/Proj4js/defs/EPSG5186.js'/>"></script>

    <script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

    <script src="<c:url value='/js/emf_map.ygpa_gam.js'/>"></script>

    <script type="text/javascript">
       OpenLayers.ImgPath = "<c:url value='/images/egovframework/ygpa/gam/maps/'/>";

       jQuery(document).ready(function() {
    	   EMD.go("<c:url value='/'/>", "${pageContext.request.scheme}://${pageContext.request.serverName}");
    	 });

    </script>
  </head>
  <body>
    <div class="abs" id="wrapper">
        <div id="desktop" class="abs mapdesk">
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
                <li><a class="menu_trigger" href="#">자산 정보</a>
                    <ul class="menu">
                        <li><a href="#"><b>자산정보 관리</b></a>
                            <ul class="submenu">
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0001"
                                    data-url="<c:url value='/asset/gamErpGisAssetCodeMngt.do'/>">자산취득관리</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0002"
                                    data-url="<c:url value='/asset/gamAssetEvlDtlsInqire.do'/>">자산가치평가내역조회</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0003"
                                    data-url="<c:url value='/asset/gamAssetDisUseMngt.do'/>">자산폐기등록</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0005"
                                    data-url="<c:url value='/asset/gamAssetSttusInqire.do'/>">자산정보현황조회</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0005"
                                    data-url="<c:url value='/asset/gamAssetLndValInqire.do'/>">자산부지공시지가조회</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0005"
                                    data-url="<c:url value='/asset/gamAssetTypeValueSts.do'/>">자산종류별자산가치통계조회</a>
                                </li>
                            </ul></li>
                        <li><a href="#"><b>자산운영</b></a>
                            <ul class="submenu">
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0007"
                                    data-url="<c:url value='/asset/rent/gamAssetRentSttusInqire.do'/>">자산임대현황조회</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0008"
                                    data-url="<c:url value='/asset/rent/gamAssetRentMngt.do'/>">자산임대관리</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0009"
                                    data-url="<c:url value='/asset/rent/gamAssetRentFeeMngt.do'/>">자산임대료고지관리</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0010"
                                    data-url="<c:url value='/asset/rent/gamAssetRentFeePayDtlsMngt.do'/>">자산임대료납부관리</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0011"
                                    data-url="<c:url value='/asset/rent/gamAssetUseExprInqire.do'/>">자산임대만기도래자료조회</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0011"
                                    data-url="<c:url value='/asset/rent/gamAssetFeeExprInqire.do'/>">자산임대고지도래자료조회</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0011"
                                    data-url="<c:url value='/asset/rent/gamAssetTotalRentfeeInqire.do'/>">자산별사용료현황조회</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0011"
                                    data-url="<c:url value='/asset/rent/gamAssetRentfeePayDtlsInqire.do'/>">사용료납부내역조회</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0011"
                                    data-url="<c:url value='/asset/rent/gamAssetPopupInqire.do'/>">자산정보현황알림</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0012"
                                    data-url="<c:url value='/asset/rent/gamCmpyRecvStsInqire.do'/>">업체별수입현황조회</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0012"
                                    data-url="<c:url value='/asset/rent/gamRecvTpRecvStsInqire.do'/>">수입종류별수입현황조회</a>
                                </li>
                            </ul></li>
                    </ul></li>
                <li><a class="menu_trigger" href="#">항만시설 운영</a>
                    <ul class="menu">
                        <li><a href="#"><b>일반부두</b></a>
                            <ul class="submenu">
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0003"
                                    data-url="<c:url value='/oper/gnrl/gamPrtFcltyUseSttusInqire.do'/>">항만시설사용현황조회</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0003"
                                    data-url="<c:url value='/oper/gnrl/gamPrtFcltyRentMngt.do'/>">항만시설사용목록관리</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0003"
                                    data-url="<c:url value='/oper/gnrl/gamPrtFcltyRentFeeMngt.do'/>">항만시설사용료관리</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0003"
                                    data-url="<c:url value="/oper/gnrl/gamPrtFcltyRentFeePaySttusMngt.do"/>">항만시설납부현황관리</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0003"
                                    data-url="<c:url value='/oper/gnrl/gamPrtFcltyUseExprInqire.do'/>">항만시설사용만기도래자료조회</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0003"
                                    data-url="<c:url value='/oper/gnrl/gamPrtFcltyNticArrvlDtaInqire.do'/>">항만시설고지도래현황조회</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0003"
                                    data-url="<c:url value='/oper/gnrl/gamPrtFcltyMtRentFeeSttusInqire.do'/>">항만시설월별사용료현황조회</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0003"
                                    data-url="<c:url value='/oper/gnrl/gamPrtFcltyEntrpsRentFeeSttusInqire.do'/>">항만시설업체별사용료현황조회</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0003"
                                    data-url="<c:url value='/oper/gnrl/gamPrtFcltyPdRentFeeSttusInqire.do'/>">항만시설기간별사용료현황조회</a>
                                </li>
                                <!--
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0003"
                                    data-url="<c:url value=''/>">항만시설월별사용료현황조회</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0003"
                                    data-url="<c:url value=''/>">항만시설업체별사용료현황조회</a>
                                </li>
                                <li><a href="#" data-role="LoadModule"
                                    data-prgid="PRG_0003"
                                    data-url="<c:url value=''/>">항만시설기간별사용료현황조회</a>
                                </li>
                                 -->
                            </ul></li>
                        <li><a href="#"><b>배후단지</b></a>
                            <ul class="submenu">
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/htld/gamHtldRentSttusInqire.do'/>">배후단지임대현황조회</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/htld/gamHtldRentMngt.do'/>">배후단지임대목록관리</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/htld/gamHtldRentFeeMngt.do'/>">배후단지임대료관리</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value="/oper/htld/gamHtldRentFeePaySttusMngt.do"/>">배후단지납부현황관리</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/htld/gamHtldUseExprInqire.do'/>">배후단지임대만기도래자료조회</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/htld/gamHtldNticArrvlDtaInqire.do'/>">배후단지임대고지도래관리</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/htld/gamHtldMtRentFeeSttusInqire.do'/>">배후단지월별임대료현황조회</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/htld/gamHtldEntrpsRentFeeSttusInqire.do'/>">배후단지업체별임대료현황조회</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/htld/gamHtldPdRentFeeSttusInqire.do'/>">배후단지기간별임대료현황조회</a>
							      </li>
                            </ul>
                        </li>
                        <li><a href="#"><b>컨테이너부두</b></a>
                            <ul class="submenu">
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/cntnr/gamCntnrQuayRentSttusInqire.do'/>">컨테이너부두임대현황조회</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/cntnr/gamCntnrQuayRentMngt.do'/>">컨테이너부두임대목록관리</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/cntnr/gamCntnrQuayRentFeeMngt.do'/>">컨테이너부두임대료관리</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value="/oper/cntnr/gamCntnrQuayRentFeePaySttusMngt.do"/>">컨테이너부두납부현황관리</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/cntnr/gamCntnrQuayUseExprInqire.do'/>">컨테이너부두임대만기도래자료조회</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/cntnr/gamCntnrQuayNticArrvlDtaInqire.do'/>">컨테이너부두임대고지도래관리</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/cntnr/gamCntnrQuayMtRentFeeSttusInqire.do'/>">컨테이너부두월별임대료현황조회</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/cntnr/gamCntnrQuayEntrpsRentFeeSttusInqire.do'/>">컨테이너부두업체별임대료현황조회</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/cntnr/gamCntnrQuayPdRentFeeSttusInqire.do'/>">컨테이너부두기간별임대료현황조회</a>
							      </li>
                            </ul>
                        </li>
                        <li><a href="#"><b>철송장</b></a>
                            <ul class="submenu">
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/train/gamTrainPortRentSttusInqire.do'/>">철송장임대현황조회</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/train/gamTrainPortRentMngt.do'/>">철송장임대목록관리</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/train/gamTrainPortRentFeeMngt.do'/>">철송장임대료관리</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value="/oper/train/gamTrainPortRentFeePaySttusMngt.do"/>">철송장납부현황관리</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/train/gamTrainPortUseExprInqire.do'/>">철송장임대만기도래자료조회</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/train/gamTrainPortNticArrvlDtaInqire.do'/>">철송장임대고지도래관리</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/train/gamTrainPortMtRentFeeSttusInqire.do'/>">철송장월별임대료현황조회</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/train/gamTrainPortEntrpsRentFeeSttusInqire.do'/>">철송장업체별임대료현황조회</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/train/gamTrainPortPdRentFeeSttusInqire.do'/>">철송장기간별임대료현황조회</a>
							      </li>
                            </ul>
                        </li>
                        <li><a href="#"><b>공컨장치장</b></a>
                            <ul class="submenu">
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/shed/gamCmmnCntrRentSttusInqire.do'/>">공컨장치장임대현황조회</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/shed/gamCmmnCntrRentMngt.do'/>">공컨장치장임대목록관리</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/shed/gamCmmnCntrRentFeeMngt.do'/>">공컨장치장임대료관리</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value="/oper/shed/gamCmmnCntrRentFeePaySttusMngt.do"/>">공컨장치장납부현황관리</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/shed/gamCmmnCntrUseExprInqire.do'/>">공컨장치장임대만기도래자료조회</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/shed/gamCmmnCntrNticArrvlDtaInqire.do'/>">공컨장치장임대고지도래관리</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/shed/gamCmmnCntrMtRentFeeSttusInqire.do'/>">공컨장치장월별임대료현황조회</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/shed/gamCmmnCntrEntrpsRentFeeSttusInqire.do'/>">공컨장치장업체별임대료현황조회</a>
							      </li>
							      <li><a href="#" data-role="LoadModule"
							          data-prgid="PRG_0003"
							          data-url="<c:url value='/oper/shed/gamCmmnCntrPdRentFeeSttusInqire.do'/>">공컨장치장기간별임대료현황조회</a>
							      </li>
                            </ul>
                        </li>
                        <li><a href="#"><b>사옥관리</b></a>
                            <ul class="submenu">
                            <li>
                            <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                            data-url="<c:url value='/oper/center/gamMarineCenterRentSttusInqire.do'/>">마린센터임대현황조회</a>
                            </li>
                            <li>
                            <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                            data-url="<c:url value='/oper/center/gamMarineCenterRentMngt.do'/>">마린센터임대목록관리</a>
                            </li>
                            <li>
                            <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                            data-url="<c:url value='/oper/center/gamMarineCenterRentFeeMngt.do'/>">마린센터임대료관리</a>
                            </li>
                            <li>
                            <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                            data-url="<c:url value='/oper/center/gamMarineCenterRentNticMngt.do'/>">마린센터납부현황관리</a>
                            </li>
                            <li>
                            <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                            data-url="<c:url value='/oper/center/gamMarineCenterFeeExprInqire.do'/>">마린센터임대고지도래관리</a>
                            </li>
                            <li>
                            <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                            data-url="<c:url value='/oper/center/gamMarineCenterUseExprInqire.do'/>">마린센터임대만기도래자료조회</a>
                            </li>
                            <li>
                            <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                            data-url="<c:url value='/oper/center/gamMarineCenterMonthStsReport.do'/>">마린센터월별사용료현황조회</a>
                            </li>
                            <li>
                            <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                            data-url="<c:url value='/oper/center/gamMarineCenterRentCmpyStsReport.do'/>">마린센터업체별사용료현황조회</a>
                            </li>
                            <li>
                            <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                            data-url="<c:url value='/oper/center/gamMarineCenterPdRentfeeSttusInqire.do'/>">마린센터기간별사용료현황조회</a>
                            </li>
                            <li>
                            <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                            data-url="<c:url value='/oper/center/gamMarineCenterRentStairStsReport.do'/>">마린센터층별사용료현황조회</a>
                            </li>
                            <li>
                            <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                            data-url="<c:url value='/oper/center/gamMarineCenterRentArStsReport.do'/>">마린센터면적별사용료현황조회</a>
                            </li>

                            </ul>
                        </li>
                    </ul>
                    </li>
                <li>
        <a class="menu_trigger" href="#">포트미스 연계</a>
        <ul class="menu">
          <li>
            <a href="#"><b>포트미스 정보조회</b></a>
                <ul class="submenu">
                            <li>
                                <a href="#" data-role="LoadModule" data-url="<c:url value='/portmis/gamFcltyUseRealloadInqire.do'/>">항만시설사용실적조회</a>
                            </li>
                            <!--
                            <li>
                                <a href="#" data-role="LoadModule" data-url="<c:url value='/portmis/gamFcltyUseSttusInqire.do'/>">항만시설사용현황조회</a>
                            </li>
                             -->
                            <li>
                                <a href="#" data-role="LoadModule" data-url="<c:url value='/port_mis/gamFcltyUseSttusInqire.do'/>">항만시설사용현황조회</a>
                            </li>
                            <li>
                                <a href="#" data-role="LoadModule" data-url="<c:url value='/portmis/gamFcltyUseFeePayDtlsInqire.do'/>">항만시설사용료납부현황조회</a>
                            </li>
                            <li>
                                <a href="#" data-role="LoadModule" data-url="<c:url value='/portmis/gamCustTpSalesSttutsCreat.do'/>">고객군별매출액통계생성</a>
                            </li>
                            <li>
                                <a href="#" data-role="LoadModule" data-url="<c:url value='/portmis/gamCustTpShpCmpySttutsInqire.do'/>">고객군별선사별매출액통계</a>
                            </li>
                            <li>
                                <a href="#" data-role="LoadModule" data-url="<c:url value='/portmis/gamCustTpCmpySttutsInqire.do'/>">고객군별업체별매출액통계</a>
                            </li>
                </ul>
          </li>
        </ul>
      </li>


        <!-- 시설관리 -->
        <li>
            <a class="menu_trigger" href="#">시설관리</a>
            <ul class="menu">
                <li>
                    <a href="#"><b>건축</b></a>
                    <ul class="submenu">
                        <li><a href="#" data-role="LoadModule" data-url="<c:url value='/fclty/gamConstFcltyMngt.do'/>">건축시설관리</a></li>
                        <li><a href="#" data-role="LoadModule" data-url="<c:url value='/fclty/gamConstFcltyInqire.do'/>">건축시설조회</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><b>기계</b></a>
                    <ul class="submenu">
                        <li><a href="#" data-role="LoadModule" data-url="<c:url value='/fclty/gamMechFcltyMngt.do'/>">기계시설관리</a></li>
                        <li><a href="#" data-role="LoadModule" data-url="<c:url value='/fclty/gamMechFcltyInqire.do'/>">기계시설조회</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><b>정보통신</b></a>
                    <ul class="submenu">
                        <li><a href="#" data-role="LoadModule" data-url="<c:url value='/fclty/gamInfoTechFcltyMngt.do'/>">정보통신시설관리</a></li>
                        <li><a href="#" data-role="LoadModule" data-url="<c:url value='/fclty/gamInfoTechFcltyInqire.do'/>">정보통신시설조회</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><b>토목</b></a>
                    <ul class="submenu">
                        <li><a href="#" data-role="LoadModule" data-url="<c:url value='/fclty/gamCivilFcltyMngt.do'/>">토목시설관리</a></li>
                        <li><a href="#" data-role="LoadModule" data-url="<c:url value='/fclty/gamCivilFcltyInqire.do'/>">토목시설조회</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><b>도면</b></a>
                    <ul class="submenu">
                        <li><a href="#" data-role="LoadModule" data-url="<c:url value='/fclty/gamDrwListMngt.do'/>">도면목록관리</a></li>
                    </ul>
                </li>
            </ul>
        </li>



        <!-- 공통코드 기능 관리 -->
        <li>
            <a class="menu_trigger" href="#">코드관리</a>
            <ul class="menu">
                <li>
                    <a href="#"><b>공통코드 관리</b></a>
                        <ul class="submenu">
                            <li><a href="#" data-role="LoadModule" data-url="<c:url value='/code/gamCmmnCodeClMngt.do'/>">공통코드분류</a></li>
                            <li><a href="#" data-role="LoadModule" data-url="<c:url value='/code/gamCmmnCodeMngt.do'/>">공통코드관리</a></li>
                            <li><a href="#" data-role="LoadModule" data-url="<c:url value='/code/gamCmmnCodeDetailMngt.do'/>">공통코드상세 관리</a></li>
                        </ul>
                </li>
                  <li>
                    <a href="#"><b>기본코드 관리</b></a>
                        <ul class="submenu">
                            <li><a href="#" data-role="LoadModule" data-url="<c:url value='/code/assets/gamAssetCodeMngt.do'/>">자산코드관리</a></li>
                            <li><a href="#" data-role="LoadModule" data-url="<c:url value='/code/gamOlnlpMngt.do'/>">공시지가 관리</a></li>
                            <li><a href="#" data-role="LoadModule" data-url="<c:url value='/code/gamCmpyInfoMngt.do'/>">업체정보 관리</a></li>
                        </ul>
                </li>
                  <li>
                    <a href="#"><b>기본코드 조회</b></a>
                        <ul class="submenu">
                            <li><a href="#" data-role="LoadModule" data-url="<c:url value='/code/assets/gamAssetCodeList.do'/>">자산코드조회</a></li>
                            <li><a href="#" data-role="LoadModule" data-url="<c:url value='/code/gamOlnlpList.do'/>">공시지가 조회</a></li>
                            <li><a href="#" data-role="LoadModule" data-url="<c:url value='/code/gamCmpyInfoList.do'/>">업체정보 조회</a></li>
                            <li><a href="#" data-role="LoadModule" data-url="<c:url value='/code/gamBupJungDongCodeMngt.do'/>">법정동 조회</a></li>
                        </ul>
                </li>
            </ul>
        </li>


        <!-- 공통기능 메뉴 -->
        <li>
            <a class="menu_trigger" href="#">공통기능</a>
            <ul class="menu">
                <li>
                    <a href="#"><b>자료 연계</b></a>
                    <ul class="submenu">
                        <li><a href="#" data-role="LoadModule" data-prgid="PRG_0019" data-url="<c:url value='/cmmn/itgrn/gamNticPayList.do'/>">납부현황목록조회</a></li>
                    </ul>
                </li>
                <li><a href="#"><b>권한 관리</b></a>
                    <ul class="submenu">
                        <li><a href="#" data-role="LoadModule" data-url="<c:url value='/cmmn/gamUserMng.do'/>">사용자관리</a></li>
                        <li><a href="#" data-role="LoadModule" data-url="<c:url value='/cmmn/gamProgListMng.do'/>">프로그램 목록 관리</a></li>
                        <li><a href="#" data-role="LoadModule" data-url="<c:url value='/cmmn/gamMenuMng.do'/>">메뉴 관리</a></li>
                        <li><a href="#" data-role="LoadModule" data-url="<c:url value='/cmmn/gamMenuMngCreat.do'/>">메뉴 생성 관리</a></li>
                        <li><a href="#" data-role="LoadModule" data-url="<c:url value='/cmmn/gamAuthorMng.do'/>">권한 관리</a></li>
                        <li><a href="#" data-role="LoadModule" data-url="<c:url value='/cmmn/gamAuthorRoleMng.do'/>">권한 롤 관리</a></li>
                        <li><a href="#" data-role="LoadModule" data-url="<c:url value='/cmmn/gamAuthorGrpMng.do'/>">권한 그룹 관리</a></li>
                    </ul>
                </li>
   				<li><a href="#"><b>테스트</b></a>
					<ul class="submenu">
						<li><a href="#" data-role="LoadModule" data-url="<c:url value='/eap/showEapGwCallInterfaceView.do'/>">전자결재 테스트</a></li>
					</ul>
				</li>
            </ul>
        </li>

        <li>
            <a class="menu_trigger" href="#">창관리</a>
            <ul class="menu">
                <li>
                    <a href="#" data-role="CloseAllWindow">모든창 닫기</a>
                </li>
                <li>
                    <a href="#" data-role="MinimizeAllWindow">모든창 최소화</a>
                </li>
            </ul>
        </li>

                <li><a class="menu_trigger" href="#">샘플</a>
                    <ul class="menu">
                        <li><a href="#"><b>자산정보 관리</b></a>
                            <ul class="submenu">
                                <li><a href="#" data-role="LoadModule" data-url="<c:url value='/sample/gamAssetMngt.do'/>">자산정보관리(샘플)</a></li>
                                <li><a href="#" data-role="LoadModule" data-url="<c:url value='/sample/gamAssetRentMngt.do'/>">자산임대관리(샘플)</a></li>
                                <li><a href="#" data-role="LoadModule" data-url="<c:url value='/sample/gamAuthorRoleMng.do'/>">권한 롤 관리(샘플)</a></li>
                                <li><a href="#" data-role="LoadModule" data-url="<c:url value='/sample/gamAuthorGrpMng.do'/>">권한 그룹 관리(샘플)</a></li>
                                <li><a href="#" data-role="LoadModule" data-url="<c:url value='/sample/mnu/gamMenuList.do'/>">메뉴리스트(샘플)</a></li>
                            </ul>
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
<div id="file_upload_dialog" title="업로드 파일">
<form id="fileupload" action="<c:url value='/upload/genericMulti.do'/>" method="POST" enctype="multipart/form-data">
	<input name="type" type="hidden" value="genericFileMulti"/>
    <div class="fileupload-buttonbar">
        <div class="fileupload-buttons">
            <!-- The fileinput-button span is used to style the file input field as button -->
            <span class="fileinput-button">
                <span>파일 추가...</span>
                <input type="file" name="files[]" multiple>
            </span>
            <button type="submit" class="start">업로드 시작</button>
            <button type="reset" class="cancel">업로드 취소</button>
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
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="close">×</a>
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
            <p class="size">Processing...</p>
            <div class="progress"></div>
        </td>
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="start" disabled>Start</button>
            {% } %}
            {% if (!i) { %}
                <button class="cancel">Cancel</button>
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
                <div><span class="error">Error</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=o.formatFileSize(file.fileSize)%}</span>
        </td>
        <td>
            <button class="delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>Delete</button>
            <input type="checkbox" name="delete" value="1" class="toggle">
        </td>
    </tr>
{% } %}
</script>

</form>

</div>
  </body>
</html>

