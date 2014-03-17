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
<html lang="ko" xml:lang="ko">
  <head>
    <title>여수광양항만공사 - GIS기반 자산관리 시스템 (DEBUG)</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">

<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/reset.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/emd_desktop.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/ygpa_desktop.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/demo/jquery-ui-1.10.4.custom.css' />" />
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

    <script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

    <script src="<c:url value='/js/emf_map.ygpa_gam.js'/>"></script>

    <script type="text/javascript">
       OpenLayers.ImgPath = "<c:url value='/images/egovframework/ygpa/gam/maps/'/>";

       jQuery(document).ready(function() {
    	   EMD.go("${pageContext.request.contextPath}");
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
                                    data-url="<c:url value='/asset/gamGisAssetCodeMngt.do'/>">자산코드정보관리</a>
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
                                    data-url="<c:url value="/oper/gnrl/gamPrtFcltyRentFeePaySttusMngt.do"/>">항만시설납부현황관리</a>
                                </li>
                                 -->
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
                        <li><a href="#"><b>컨테이너 부두</b></a>
                            <ul class="submenu">
                                <li>
                                    <a href="#" data-role="LoadModule"data-prgid="PRG_0005"
                                    data-url="<c:url value='/oper/cntnr/gamCntnrQuayRentSttusInqire.do'/>">컨테이너부두임대현황조회</a>
                                </li>
                                <li>
                                    <a href="#" data-role="LoadModule" data-prgid="PRG_0005"
                                    data-url="<c:url value='/oper/cntnr/gamCntnrRentMngt.do'/>">컨테이너부두임대목록관리</a>
                                </li>
                                <li>
                                    <a href="#" data-role="LoadModule" data-prgid="PRG_0005"
                                    data-url="<c:url value='/oper/cntnr/gamCntnrRentFeeMngt.do'/>">컨테이너부두임대료관리</a>
                                </li>
                                <li>
                                    <a href="#" data-role="LoadModule" data-prgid="PRG_0005"
                                    data-url="<c:url value='/oper/cntnr/gamCntnrRentFeePaySttusMngt.do'/>">컨테이너부두납부현황관리</a>
                                </li>
                            </ul>
                            </li>
                        <li><a href="#"><b>배후단지</b></a>
                            <ul class="submenu">
                            <li>
                                <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                                data-url="<c:url value='/oper/htld/gamHtldRentSttusInqire.do'/>">배후단지임대현황조회</a>
                            </li>
                            <li>
                                <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                                data-url="<c:url value='/oper/htld/gamHtldRentMngt.do'/>">배후단지임대목록관리</a>
                            </li>
                            <li>
                                <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                                data-url="<c:url value='/oper/htld/gamHtldRentFeeMngt.do'/>">배후단지임대료관리</a>
                            </li>
                            <li>
                                <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                                data-url="<c:url value='/oper/htld/gamHtldRentFeePaySttusMngt.do'/>">배후단지납부현황관리</a>
                            </li>
                            </ul>
                        </li>
                        <li><a href="#"><b>철송장</b></a>
                            <ul class="submenu">
                                <li>
                                <a href="#"
                                data-role="LoadModule" data-prgid="PRG_0004"
                                data-url="<c:url value='/oper/train/gamTrainPortRentSttusInqire.do'/>">철송장임대현황조회</a>
                            </li>
                            <li>
                                <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                                data-url="<c:url value='/oper/train/gamTrainPortRentLstMngt.do'/>">철송장임대목록관리</a>
                            </li>
                            <li>
                                <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                                data-url="<c:url value='/oper/train/gamTrainPortRentFeeMngt.do'/>">철송장임대료관리</a>
                            </li>
                            <li>
                                <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                                data-url="<c:url value='/oper/train/gamTrainPortRentFeePaySttusMngt.do'/>">철송장납부현황관리</a>
                            </li>
                            </ul>
                        </li>
                        <li><a href="#"><b>공컨장치장</b></a>
                            <ul class="submenu">
                                <li>
                                <a href="#"
                            data-role="LoadModule" data-prgid="PRG_0004"
                            data-url="<c:url value='/oper/shed/gamCmmnCntrShedRentSttusInqire.do'/>">공컨장치장임대현황조회</a>
                            </li>
                            <li>
                            <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                            data-url="<c:url value='/oper/shed/gamCmmnCntrRentMngt.do'/>">공컨장치장임대목록관리</a>
                            </li>
                            <li>
                            <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                            data-url="<c:url value='/oper/shed/gamCmmnCntrShedRentFeeMngt.do'/>">공컨장치장임대료관리</a>
                            </li>
                            <li>
                            <a href="#" data-role="LoadModule" data-prgid="PRG_0004"
                            data-url="<c:url value='/oper/shed/gamCmmnCntrRentFeePaySttusMngt.do'/>">공컨장치장납부현황관리</a>
                            </li>
                            </ul>
                        </li>
                        <li><a href="#"><b>사옥관리</b></a>
                            <ul class="submenu">
                                <li>
                                <a href="#"
                            data-role="LoadModule" data-prgid="PRG_0004"
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
                            data-url="<c:url value='/prt_fclty/gamMarineCenterMonthStsReport.do'/>">마린센터월별사용료현황조회</a>
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
                            <li>
                                <a href="#" data-role="LoadModule" data-url="<c:url value='/portmis/gamFcltyUseSttusInqire.do'/>">항만시설사용현황조회</a>
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
                        <li><a href="#" data-role="LoadModule" data-url="<c:url value='/fclty/gamInfoTechInqire.do'/>">정보통신시설조회</a></li>
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
                            <li><a href="#" data-role="LoadModule" data-url="<c:url value='/code/gamAssetCodeMngt.do'/>">자산코드관리</a></li>
                            <li><a href="#" data-role="LoadModule" data-url="<c:url value='/code/gamCmmnCodeClMngt.do'/>">공통코드분류</a></li>
                            <li><a href="#" data-role="LoadModule" data-url="<c:url value='/code/gamCmmnCodeMngt.do'/>">공통코드관리</a></li>
                            <li><a href="#" data-role="LoadModule" data-url="<c:url value='/code/gamCmmnCodeDetailMngt.do'/>">공통코드상세 관리</a></li>
                            <li><a href="#" data-role="LoadModule" data-url="<c:url value='/code/gamOlnlpMngt.do'/>">공시지가 관리</a></li>
                            <li><a href="#" data-role="LoadModule" data-url="<c:url value='/code/gamOlnlpList.do'/>">공시지가 조회</a></li>
                            <li><a href="#" data-role="LoadModule" data-url="<c:url value='/code/gamCmpyInfoMngt.do'/>">업체정보 관리</a></li>
                            <li><a href="#" data-role="LoadModule" data-url="<c:url value='/code/gamCmpyInfoList.do'/>">업체정보 조회</a></li>
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

  </body>
</html>

