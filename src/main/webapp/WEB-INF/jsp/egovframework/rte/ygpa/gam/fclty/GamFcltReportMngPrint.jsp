<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamFcltReportMngPrint.jsp
  * @Description : 시설물관리대장 인쇄 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2015.2.6    HNJ          최초 생성
  *
  * author HNJ
  * since 2015.2.6
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<html lang="ko" xml:lang="ko">
  <head>
    <title>여수광양항만공사 - GIS기반 자산관리 시스템</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
	<link rel="stylesheet" href="/css/ygpa/gam/reset.css" />
	<link rel="stylesheet" href="/css/demo/jquery-ui-1.10.4.custom.css" />
	<link rel="stylesheet" href="/css/ygpa/gam/ygpa_report_l.css" />

	<script src="/js/jquery-1.10.2.min.js"></script>
	<script src="/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="/js/jquery-ui.min.js"></script>
	<script>
	
	$( window ).load(function() {
		$('#printButton').button().click(function(){
	        			window.print();
		});
	});
	</script>
  </head>
  <body>
  <c:if test="${resultCode==0 }">
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
        	<div style="height:40px;text-align:left;vertical-align:middle;border-top:1px red;font-size:25px;font-weight:bold;">5. 시설물관리대장</div>
        	<div style="height:30px;text-align:left;vertical-align:middle;border-top:1px red;font-size:20px;"> 1. 기본현황</div>
    		<table style="width:100%;" class="rpr_form_table">
        		<tbody>
        			<tr height="40px">
        				<td colspan="3" style="width:90px;text-align:center;font-size:13px;">시설물 번호</td>
        				<td style="width:210px;text-align:center;font-size:13px;">관리번호</td>
        				<td colspan="3" style="width:100px;text-align:center;font-size:13px;">시설물명</td>
        				<td style="text-align:center;font-size:13px;">노선</td>
        				<td style="text-align:center;font-size:13px;">시설물종류</td>
        				<td style="text-align:center;font-size:13px;">시설물종별</td>
        				<td style="text-align:center;font-size:13px;">시설물구분</td>
        			</tr>
        			<tr height="40px">
        				<td colspan="3" style="width:90px;text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.fcltsNo }" /></td>
        				<td style="width:210px;text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.fcltsMngGroupNo }" /></td>
        				<td colspan="3" style="width:100px;text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.fcltsNm }" /></td>
        				<td style="text-align:center;font-size:13px;width:80px;word-break:break-all;"><c:out value="${result.route }" /></td>
        				<td style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.fcltsKnd }" /></td>
        				<td style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.fcltsGbn }" /></td>
        				<td style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.fcltsSe }" /></td>
        			</tr>
        			<tr height="40px">
        				<td colspan="6" style="text-align:center;font-size:13px;">
        					위치&nbsp;&nbsp; (시,도) &nbsp;(시,군,구) &nbsp;&nbsp;&nbsp;(읍,면,동) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(리,번지 등 주소)
        				</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">관리주체</td>
        				<td style="text-align:center;font-size:13px;">관리주체구분</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">소유자</td>
        			</tr>
        			<tr height="40px">
        				<td style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.locDo }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;width:80px;word-break:break-all;"><c:out value="${result.locSi }" /></td>
        				<td style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.locDong }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.locJibun }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.mngMainbd }" /></td>
        				<td style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.mngMainbdSe }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.owner }" /></td>
        			</tr>
        			<tr height="40px">
        				<td colspan="2" style="text-align:center;font-size:13px;">준공일</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">하자담보책임만료일</td>
        				<td style="text-align:center;font-size:13px;">상세제원</td>
        				<td colspan="2" style="width:150px;text-align:center;font-size:13px;">안전점검 및<br>정밀안전진단이력</td>
        				<td colspan="4" style="text-align:center;font-size:13px;">보수·보강<br>이력</td>
        			</tr>
        			<tr height="40px">
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.bldDt }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.flawEndDt }" /></td>
        				<td style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.dtlsSpecYn }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.qcHistYn }" /></td>
        				<td colspan="4" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.mntnHistYn }" /></td>
        			</tr>
        			<tr height="40px">
        				<td colspan="3" style="text-align:center;font-size:13px;">설계기간</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">설계자</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">공사기간</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">시공자</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">총공사비(백만원)</td>
        			</tr>
        			<tr height="40px">
        				<td colspan="3" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.planBeginDt }" /> ~ <br><c:out value="${result.planEndDt }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.planner }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.cnstBeginDt }" /> ~ <br><c:out value="${result.cnstEndDt }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.cnstrtr }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.cnstrctAmt }" /></td>
        			</tr>
        			<tr height="40px">
        				<td style="text-align:center;font-size:13px;">영10조대상</td>
        				<td colspan="3" style="text-align:center;font-size:13px;">감리기간</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">감리자<br>(책임감리원)</td>
        				<td style="text-align:center;font-size:13px;">공사발주자</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">공사명</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">공사감독·<br>공사관리관</td>
        			</tr>
        			<tr height="40px">
        				<td style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.erqProofPlanApplcEnnc }" /></td>
        				<td colspan="3" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.inspectBeginDt }" /> ~ <br><c:out value="${result.inspectEndDt }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.inspector }" /></td>
        				<td style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.cnstOrderBody }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.cnstNm }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.cnstSupervisor }" /></td>
        			</tr>
        			<tr height="40px">
        				<td colspan="11" style="text-align:left;font-size:13px;"> ▷ 기타 기본현황</td>
        			</tr>
        			<tr height="40px">
        				<td colspan="3" style="text-align:center;font-size:13px;">작성일</td>
        				<td colspan="3" style="text-align:center;font-size:13px;">작성자</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">최종 수정일</td>
        				<td colspan="3" style="text-align:center;font-size:13px;">최종 수정자</td>
        			</tr>
        			<tr height="40px">
        				<td colspan="3" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.wrtDt }" /></td>
        				<td colspan="3" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.wrtUsr }" />&nbsp;&nbsp;&nbsp;인</td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.lastUpdtDt }" /></td>
        				<td colspan="3" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.lastUpdtUsr }" /></td>
        			</tr>
        			<tr height="40px">
        				<td colspan="3" style="text-align:left;font-size:13px;border-right:0;"> ▷ 비고</td>
        				<td colspan="8" style="text-align:left;font-size:13px;border-left:0;word-break:break-all;"> <c:out value="${result.basicRm }" /></td>
        			</tr>
        		</tbody>  
        	</table>
    		
        </div>
    </div>
    <div class="page">
        <div class="subpage ygpa_report" >
        	<div style="height:40px;text-align:left;vertical-align:middle;border-top:1px red;font-size:20px;"> 2. 상세제원</div>
<!-- A이면 건축, B이면 토목->교량 아니면 토목(H)으로 인쇄화면 디스플레이 처리 -->
<c:choose>
	<c:when test="${fcltsSe == 'A' }">
    		<table style="width:100%;" class="rpr_form_table">
        		<tbody>
        			<tr height="30px">
        				<td colspan="2" rowspan="2" style="width:120px;text-align:center;font-size:13px;">시설물명</td>
        				<td colspan="2" rowspan="2" style="width:140px;text-align:center;font-size:13px;">주용도</td>
        				<td colspan="4" style="text-align:center;font-size:13px;">층수</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">최고층고</td>
        			</tr>
        			<tr height="30px">
        				<td style="width:110px;text-align:center;font-size:13px;">지상(옥탑제외)</td>
        				<td style="width:70px;text-align:center;font-size:13px;">옥탑</td>
        				<td style="width:70px;text-align:center;font-size:13px;">지하</td>
        				<td style="width:70px;text-align:center;font-size:13px;">최고높이</td>
        				<td style="width:70px;text-align:center;font-size:13px;">높이</td>
        				<td style="width:70px;text-align:center;font-size:13px;">해당층</td>
        			</tr>
        			<tr height="40px">
        				<td colspan="2" style="text-align:center;font-size:13px;"><c:out value="${result.fcltsNm }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        			</tr>
        			<tr height="30px">
        				<td colspan="2" style="text-align:center;font-size:13px;">구조형식</td>
        				<td style="width:70px;text-align:center;font-size:13px;">지정형태</td>
        				<td style="width:70px;text-align:center;font-size:13px;">기초형식</td>
        				<td style="text-align:center;font-size:13px;">기초밑면깊이</td>
        				<td style="text-align:center;font-size:13px;">대지면적</td>
        				<td style="text-align:center;font-size:13px;">건축면적</td>
        				<td style="text-align:center;font-size:13px;">건축연면적</td>
        				<td style="text-align:center;font-size:13px;">건폐율</td>
        				<td style="text-align:center;font-size:13px;">용적율</td>
        			</tr>
        			<tr height="40px">
        				<td colspan="2" style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        			</tr>
        			<tr height="30px">
        				<td rowspan="2" style="text-align:center;font-size:13px;">주차시설</td>
        				<td style="text-align:center;font-size:13px;">주차면젹</td>
        				<td style="text-align:center;font-size:13px;">옥내주차면적</td>
        				<td style="text-align:center;font-size:13px;">옥외주차면적</td>
        				<td style="text-align:center;font-size:13px;">주차대수</td>
        				<td style="text-align:center;font-size:13px;">옥내주차대수</td>
        				<td style="text-align:center;font-size:13px;">옥외주차대수</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">일시최대 사용인원</td>
        				<td style="text-align:center;font-size:13px;">1일 사용인원</td>
        			</tr>
        			<tr height="40px">
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td colspan="2" style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        			</tr>
        			<tr height="30px">
        				<td rowspan="6" style="text-align:center;font-size:13px;">설 비</td>
        				<td rowspan="2" style="text-align:center;font-size:13px;">환기(공조)<br>방식</td>
        				<td rowspan="2" style="text-align:center;font-size:13px;">기계시설일경<br>배기닥트유무</td>
        				<td rowspan="2" style="text-align:center;font-size:13px;">물탱크위치</td>
        				<td rowspan="2" style="text-align:center;font-size:13px;">변전실위치</td>
        				<td rowspan="2" style="text-align:center;font-size:13px;">유류저장<br>시설위치</td>
        				<td rowspan="2" style="text-align:center;font-size:13px;">오수정화<br>시설위치</td>
        				<td colspan="3" style="text-align:center;font-size:13px;">승 강 기 대 수</td>
        			</tr>
        			<tr height="30px">
        				<td style="text-align:center;font-size:13px;">승객용</td>
        				<td style="text-align:center;font-size:13px;">화물용</td>
        				<td style="text-align:center;font-size:13px;">비상용</td>
        			</tr>
        			<tr height="40px">
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        			</tr>
        			<tr height="30px">
        				<td colspan="2" style="text-align:center;font-size:13px;">중 앙 냉 방</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">중 앙 난 방</td>
        				<td rowspan="2" style="text-align:center;font-size:13px;">전기인입용량</td>
        				<td rowspan="2" style="text-align:center;font-size:13px;">정화조형식</td>
        				<td rowspan="2" style="text-align:center;font-size:13px;">건물유지관리<br>시스템</td>
        				<td rowspan="2" style="text-align:center;font-size:13px;">유지관리<br>부대시설</td>
        				<td rowspan="2" style="text-align:center;font-size:13px;">승강기<br>운영방식</td>
        			</tr>
        			<tr height="30px">
        				<td style="text-align:center;font-size:13px;">냉방유무</td>
        				<td style="text-align:center;font-size:13px;">냉방열원</td>
        				<td style="text-align:center;font-size:13px;">난방유무</td>
        				<td style="text-align:center;font-size:13px;">난방열원</td>
        			</tr>
        			<tr height="40px">
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        			</tr>
        			<tr height="100px">
        				<td colspan="2" style="text-align:left;font-size:13px;border-right:0;"> ▷ 기타 상세제원</td>
        				<td colspan="8" style="text-align:left;font-size:13px;border-left:0;"></td>
        			</tr>
        		</tbody>  
        	</table>
	</c:when>
	<c:when test="${fcltsSe == 'B' }">
    		<table style="width:100%;" class="rpr_form_table">
        		<tbody>
        			<tr height="40px">
        				<td colspan="3" style="text-align:center;font-size:13px;">시설물명</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">교량시점위치<br>(읍면동리까지 기입)</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">교량종점위치<br>(읍면동리까지 기입)</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">설계활하중</td>
        				<td style="width:200px;text-align:center;font-size:13px;">허용통행하중</td>
        			</tr>
        			<tr height="40px">
        				<td colspan="3" style="text-align:center;font-size:13px;"></td>
        				<td colspan="2" style="text-align:left;font-size:13px;"></td>
        				<td colspan="2" style="text-align:left;font-size:13px;"></td>
        				<td colspan="2" style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        			</tr>
        			<tr height="40px">
        				<td colspan="3" style="text-align:center;font-size:13px;">연&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;장</td>
        				<td colspan="3" style="text-align:center;font-size:13px;">폭</td>
        				<td colspan="3" style="text-align:center;font-size:13px;">차로수</td>
        				<td rowspan="2" style="text-align:center;font-size:13px;">내진설계 적용여부</td>
        			</tr>
        			<tr height="40px">
        				<td style="width:80px;text-align:center;font-size:13px;">길이(m)</td>
        				<td style="width:80px;text-align:center;font-size:13px;">경간수</td>
        				<td style="width:120px;text-align:center;font-size:13px;">최대경간장(m)</td>
        				<td style="width:80px;text-align:center;font-size:13px;">보도(m)</td>
        				<td style="width:80px;text-align:center;font-size:13px;">차도(m)</td>
        				<td style="width:80px;text-align:center;font-size:13px;">계(m)</td>
        				<td style="width:80px;text-align:center;font-size:13px;">상행</td>
        				<td style="width:80px;text-align:center;font-size:13px;">하행</td>
        				<td style="text-align:center;font-size:13px;">계</td>
        			</tr>
        			<tr height="40px">
        				<td style="width:80px;text-align:center;font-size:13px;"></td>
        				<td style="width:80px;text-align:center;font-size:13px;"></td>
        				<td style="width:120px;text-align:center;font-size:13px;"></td>
        				<td style="width:80px;text-align:center;font-size:13px;"></td>
        				<td style="width:80px;text-align:center;font-size:13px;"></td>
        				<td style="width:80px;text-align:center;font-size:13px;"></td>
        				<td style="width:80px;text-align:center;font-size:13px;"></td>
        				<td style="width:80px;text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        			</tr>
        			<tr height="40px">
        				<td rowspan="3" style="text-align:center;font-size:13px;">상부구조</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">경간구성</td>
        				<td colspan="7" style="text-align:center;font-size:13px;"></td>
        			</tr>
        			<tr height="40px">
        				<td colspan="2" style="text-align:center;font-size:13px;">주경간 형식</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">부경간 형식</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">받침 종류</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">신축이음 종류</td>
        				<td style="text-align:center;font-size:13px;">하부 통과제한높이(m)</td>
        			</tr>
        			<tr height="40px">
        				<td colspan="2" style="text-align:center;font-size:13px;"></td>
        				<td colspan="2" style="text-align:center;font-size:13px;"></td>
        				<td colspan="2" style="text-align:center;font-size:13px;"></td>
        				<td colspan="2" style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        			</tr>
        			<tr height="40px">
        				<td rowspan="4" style="text-align:center;font-size:13px;">하부구조</td>
        				<td colspan="4" style="text-align:center;font-size:13px;">교&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;각</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">교&nbsp;&nbsp;&nbsp;대</td>
        				<td colspan="2" rowspan="2" style="text-align:center;font-size:13px;">교차노선<br>(또는 교차하천)</td>
        				<td rowspan="2" style="text-align:center;font-size:13px;">교차하천 최고수심(m)</td>
        			</tr>
        			<tr height="40px">
        				<td colspan="2" style="text-align:center;font-size:13px;">형&nbsp;&nbsp;&nbsp;식</td>
        				<td style="text-align:center;font-size:13px;">갯&nbsp;&nbsp;수</td>
        				<td style="text-align:center;font-size:13px;">기초형식</td>
        				<td style="text-align:center;font-size:13px;">형&nbsp;&nbsp;식</td>
        				<td style="text-align:center;font-size:13px;">기초형식</td>
        			</tr>
        			<tr height="40px">
        				<td colspan="2" style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td colspan="2" rowspan="2" style="text-align:center;font-size:13px;"></td>
        				<td rowspan="2" style="text-align:center;font-size:13px;"></td>
        			</tr>
        			<tr height="40px">
        				<td colspan="2" style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        				<td style="text-align:center;font-size:13px;"></td>
        			</tr>
        			<tr height="100px">
        				<td colspan="2" style="text-align:left;font-size:13px;border-right:0;"> ▷ 기타 상세제원</td>
        				<td colspan="8" style="text-align:left;font-size:13px;border-left:0;"></td>
        			</tr>
        		</tbody>  
        	</table>
	</c:when>
	<c:otherwise>
			<table style="width:100%;" class="rpr_form_table">
        		<tbody>
        			<tr height="60px">
        				<td colspan="2" style="width:80px;text-align:center;font-size:13px;">시설물명</td>
        				<td style="width:210px;text-align:center;font-size:13px;">최대계류선박규모</td>
        				<td colspan="2" style="width:80px;text-align:center;font-size:13px;">연장</td>
        				<td style="text-align:center;font-size:13px;">천단고</td>
        				<td style="text-align:center;font-size:13px;">수심</td>
        			</tr>
        			<tr height="60px">
        				<td colspan="2" style="width:80px;text-align:center;font-size:13px;"><c:out value="${result.fcltsNm }" /></td>
        				<td style="width:210px;text-align:center;font-size:13px;"><c:out value="${result.maxShipScl }" /></td>
        				<td colspan="2" style="width:80px;text-align:center;font-size:13px;"><c:out value="${result.extd }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.upsideAlt }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.dpwt }" /></td>
        			</tr>
        			<tr height="60px">
        				<td rowspan="7" style="width:80px;text-align:center;font-size:13px;">구조형식</td>
        				<td rowspan="2" style="width:100px;text-align:center;font-size:13px;">중력식</td>
        				<td style="width:210px;text-align:center;font-size:13px;">케이슨식</td>
        				<td colspan="2" style="width:80px;text-align:center;font-size:13px;">L형블럭</td>
        				<td style="text-align:center;font-size:13px;">셀룰러블럭식</td>
        				<td style="text-align:center;font-size:13px;">현장타설식</td>
        			</tr>
        			<tr height="60px">
        				<td style="width:210px;text-align:center;font-size:13px;"><c:out value="${result.fmt1Desc1 }" /></td>
        				<td colspan="2" style="width:80px;text-align:center;font-size:13px;"><c:out value="${result.fmt1Desc2 }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.fmt1Desc3 }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.fmt1Desc4 }" /></td>
        			</tr>
        			<tr height="60px">
        				<td rowspan="3" style="width:100px;text-align:center;font-size:13px;">잔교식</td>
        				<td colspan="3" style="width:210px;text-align:center;font-size:13px;">말&nbsp;&nbsp;&nbsp;&nbsp;뚝&nbsp;&nbsp;&nbsp;&nbsp;식</td>
        				<td rowspan="2" style="text-align:center;font-size:13px;">원&nbsp;&nbsp;&nbsp;통&nbsp;&nbsp;&nbsp;식</td>
        				<td rowspan="2" style="text-align:center;font-size:13px;">교&nbsp;&nbsp;&nbsp;각&nbsp;&nbsp;&nbsp;식</td>
        			</tr>
        			<tr height="60px">
        				<td style="width:120px;text-align:center;font-size:13px;">구&nbsp;&nbsp;&nbsp;&nbsp;경</td>
        				<td style="text-align:center;font-size:13px;">연&nbsp;&nbsp;장</td>
        				<td style="text-align:center;font-size:13px;">본&nbsp;&nbsp;수</td>
        			</tr>
        			<tr height="60px">
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.fmt2Desc11 }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.fmt2Desc12 }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.fmt2Desc13 }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.fmt2Desc2 }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.fmt2Desc3 }" /></td>
        			</tr>
        			<tr height="60px">
        				<td rowspan="2" style="width:100px;text-align:center;font-size:13px;">널말뚝식</td>
        				<td style="width:210px;text-align:center;font-size:13px;">규&nbsp;&nbsp;&nbsp;&nbsp;격</td>
        				<td colspan="2" style="width:80px;text-align:center;font-size:13px;"> </td>
        				<td colspan="2" style="text-align:center;font-size:13px;">기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;타</td>
        			</tr>
        			<tr height="60px">
        				<td style="width:210px;text-align:center;font-size:13px;"><c:out value="${result.fmt3Desc1 }" /></td>
        				<td colspan="2" style="width:80px;text-align:center;font-size:13px;"> </td>
        				<td colspan="2" style="text-align:center;font-size:13px;"><c:out value="${result.fmt3Desc2 }" /></td>
        			</tr>
        			<tr height="60px">
        				<td colspan="2" style="text-align:left;font-size:13px;border-right:0;"> ▷ 기타 상세제원</td>
        				<td colspan="5" style="text-align:left;font-size:13px;border-left:0;word-break:break-all;"> <c:out value="${result.etcDtlsSpec }" /></td>
        			</tr>
        		</tbody>  
        	</table>
	</c:otherwise>
</c:choose>
        </div>
    </div>
    <div class="page">
<c:set var="pagePerCount" value="14"/>
<c:if test="${fn:length(resultList) == 0}">
		<div class="subpage ygpa_report" >
        	<div style="height:40px;text-align:left;vertical-align:middle;border-top:1px red;font-size:20px;"> 3. 안전점검 및 정밀안전진단 계획</div>
    		<table style="width:100%;" class="rpr_form_table">
        		<tbody>
        			<tr height="40px">
        				<th style="width:60px;vertical-align:middle;text-align:center;font-size:13px;">번호</th>
        				<th style="width:180px;vertical-align:middle;text-align:center;font-size:13px;">구분</th>
        				<th style="width:150px;vertical-align:middle;text-align:center;font-size:13px;">시행일</th>
        				<th style="width:150px;vertical-align:middle;text-align:center;font-size:13px;">예산(천원)</th>
        				<th style="width:180px;vertical-align:middle;text-align:center;font-size:13px;">점검진단자</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">비고</th>
        			</tr>
        			<tr>
        				<td colspan="6" style="text-align:center;font-size:13px;">자료가 존재 하지 않습니다.</td>
        			</tr>
</c:if>
<c:forEach var="result" items="${resultList }" varStatus="resultStatus">
	<c:if test="${resultStatus.index%pagePerCount==0 }"> <% /*  페이지 당 출력 갯수 */ %>
	
		<c:if test="${resultStatus.index!=0 }">	<% /*  페이지 구분*/ %>
				</tbody>
			</table>
		</div>
	</div>
	<div class="page">
		</c:if>
		<div class="subpage ygpa_report" >
			<!--  헤더 반복  -->
		<c:choose>
			<c:when test="${(resultListTotalCount - resultStatus.index) > pagePerCount }"><c:set var="continueChk" value="(계속)"/></c:when>
			<c:otherwise><c:set var="continueChk" value=""/></c:otherwise>
		</c:choose>
        	<div style="height:40px;text-align:left;vertical-align:middle;border-top:1px red;font-size:20px;"> 3. 안전점검 및 정밀안전진단 계획 <c:out value="${continueChk }" /></div>
       		<table style="width:100%;" class="rpr_form_table">
			   <tbody>
        			<tr height="40px">
        				<th style="width:60px;vertical-align:middle;text-align:center;font-size:13px;">번호</th>
        				<th style="width:180px;vertical-align:middle;text-align:center;font-size:13px;">구분</th>
        				<th style="width:150px;vertical-align:middle;text-align:center;font-size:13px;">시행일</th>
        				<th style="width:150px;vertical-align:middle;text-align:center;font-size:13px;">예산(천원)</th>
        				<th style="width:180px;vertical-align:middle;text-align:center;font-size:13px;">점검진단자</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">비고</th>
        			</tr>
	</c:if>
        			<tr height="40px">
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.rnum }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.qcMngNm }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.qcInspDt }" /></td>
        				<td style="text-align:center;font-size:13px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.qcInspBdgt }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.qcInspTp }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.rm }" /></td>
        			</tr>
</c:forEach>
        		</tbody>  
        	</table>
        </div>
    </div>
    
    <div class="page">
<c:set var="pagePerCount1" value="4"/>
<c:if test="${fn:length(resultHistList) == 0}">
		<div class="subpage ygpa_report" >
        	<div style="height:40px;text-align:left;vertical-align:middle;border-top:1px red;font-size:20px;"> 4. 안전점검 및 정밀안전진단 이력</div>
    		<table style="width:100%;" class="rpr_form_table">
        		<tbody>
        			<tr height="40px">
        				<th rowspan="2" style="width:60px;vertical-align:middle;text-align:center;font-size:13px;">번호</th>
        				<th style="width:120px;vertical-align:middle;text-align:center;font-size:13px;">점검·진단기간</th>
        				<th style="width:120px;vertical-align:middle;text-align:center;font-size:13px;">점검·진단기관명</th>
        				<th style="width:120px;vertical-align:middle;text-align:center;font-size:13px;">비용(천원)</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">주요 점검·진단결과</th>
        				<th style="width:120px;vertical-align:middle;text-align:center;font-size:13px;">작성일</th>
        			</tr>
        			<tr height="40px">
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">점검·진단구분</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">점검·진단<br>책임기술자</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">상태등급</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">주요 보수보강(안)</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">작성자(인)</th>
        			</tr>
        			<tr>
        				<td colspan="6" style="text-align:center;font-size:13px;">자료가 존재 하지 않습니다.</td>
        			</tr>
</c:if>
<c:forEach var="result" items="${resultHistList }" varStatus="resultStatus">
	<c:if test="${resultStatus.index%pagePerCount1==0 }"> <% /*  페이지 당 출력 갯수 */ %>
	
		<c:if test="${resultStatus.index!=0 }">	<% /*  페이지 구분*/ %>
				</tbody>
			</table>
		</div>
	</div>
	<div class="page">
		</c:if>
		<div class="subpage ygpa_report" >
			<!--  헤더 반복  -->
		<c:choose>
			<c:when test="${(resultHistListTotalCount - resultStatus.index) > pagePerCount1 }"><c:set var="continueChk1" value="(계속)"/></c:when>
			<c:otherwise><c:set var="continueChk1" value=""/></c:otherwise>
		</c:choose>
        	<div style="height:40px;text-align:left;vertical-align:middle;border-top:1px red;font-size:20px;"> 4. 안전점검 및 정밀안전진단 이력 <c:out value="${continueChk1 }" /></div>
       		<table style="width:100%;" class="rpr_form_table">
			   <tbody>
        			<tr height="40px">
        				<th rowspan="2" style="width:60px;vertical-align:middle;text-align:center;font-size:13px;">번호</th>
        				<th style="width:120px;vertical-align:middle;text-align:center;font-size:13px;">점검·진단기간</th>
        				<th style="width:120px;vertical-align:middle;text-align:center;font-size:13px;">점검·진단기관명</th>
        				<th style="width:120px;vertical-align:middle;text-align:center;font-size:13px;">비용(천원)</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">주요 점검·진단결과</th>
        				<th style="width:120px;vertical-align:middle;text-align:center;font-size:13px;">작성일</th>
        			</tr>
        			<tr height="40px">
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">점검·진단구분</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">점검·진단<br>책임기술자</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">상태등급</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">주요 보수보강(안)</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">작성자(인)</th>
        			</tr>
	</c:if>
        			<tr height="40px">
        				<td rowspan="2" style="width:60px;text-align:center;font-size:13px;"><c:out value="${result.rnum }" /></td>
        				<td style="width:120px;text-align:center;font-size:13px;"><c:out value="${result.qcBeginDt }" /> ~ <br><c:out value="${result.qcEndDt }" /></td>
        				<td style="width:120px;text-align:center;font-size:13px;"><c:out value="${result.qcInspInsttNm }" /></td>
        				<td style="width:120px;text-align:center;font-size:13px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.qcInspAmt }" /></td>
        				<td style="text-align:left;font-size:13px;word-break:break-all;"><c:out value="${result.qcInspResult }" /></td>
        				<td style="width:120px;text-align:center;font-size:13px;"><c:out value="${result.wrtDt }" /></td>
        			</tr>
        			<tr height="40px">
        				<td style="width:120px;text-align:center;font-size:13px;"><c:out value="${result.qcMngNm }" /></td>
        				<td style="width:120px;text-align:center;font-size:13px;"><c:out value="${result.responEngineerNm }" /></td>
        				<td style="width:120px;text-align:center;font-size:13px;"><c:out value="${result.sttusEvlLvl }" /></td>
        				<td style="text-align:left;font-size:13px;word-break:break-all;"><c:out value="${result.actionCn }" /></td>
        				<td style="width:120px;text-align:center;font-size:13px;"><c:out value="${result.wrtUsr }" /></td>
        			</tr>
</c:forEach>
        		</tbody>  
        	</table>
        </div>
    </div>
    
    <div class="page">
<c:set var="pagePerCount2" value="10"/>
<c:if test="${fn:length(mntnResultList) == 0}">
		<div class="subpage ygpa_report" >
        	<div style="height:40px;text-align:left;vertical-align:middle;border-top:1px red;font-size:20px;"> 5. 보수·보강계획</div>
    		<table style="width:100%;" class="rpr_form_table">
        		<tbody>
        			<tr height="40px">
        				<th style="width:60px;vertical-align:middle;text-align:center;font-size:13px;">번호</th>
        				<th style="width:150px;vertical-align:middle;text-align:center;font-size:13px;">공사기간</th>
        				<th style="width:120px;vertical-align:middle;text-align:center;font-size:13px;">공사구분</th>
        				<th style="width:120px;vertical-align:middle;text-align:center;font-size:13px;">부위</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">공사내역</th>
        				<th style="width:120px;vertical-align:middle;text-align:center;font-size:13px;">예산(천원)</th>
        			</tr>
        			<tr>
        				<td colspan="6" style="text-align:center;font-size:13px;">자료가 존재 하지 않습니다.</td>
        			</tr>
</c:if>
<c:forEach var="result" items="${mntnResultList }" varStatus="resultStatus">
	<c:if test="${resultStatus.index%pagePerCount2==0 }"> <% /*  페이지 당 출력 갯수 */ %>
	
		<c:if test="${resultStatus.index!=0 }">	<% /*  페이지 구분*/ %>
				</tbody>
			</table>
		</div>
	</div>
	<div class="page">
		</c:if>
		<div class="subpage ygpa_report" >
			<!--  헤더 반복  -->
		<c:choose>
			<c:when test="${(mntnResultListTotalCount - resultStatus.index) > pagePerCount2 }"><c:set var="continueChk" value="(계속)"/></c:when>
			<c:otherwise><c:set var="continueChk" value=""/></c:otherwise>
		</c:choose>
        	<div style="height:40px;text-align:left;vertical-align:middle;border-top:1px red;font-size:20px;"> 5. 보수·보강계획 <c:out value="${continueChk }" /></div>
       		<table style="width:100%;" class="rpr_form_table">
			   <tbody>
        			<tr height="40px">
        				<th style="width:60px;vertical-align:middle;text-align:center;font-size:13px;">번호</th>
        				<th style="width:150px;vertical-align:middle;text-align:center;font-size:13px;">공사기간</th>
        				<th style="width:120px;vertical-align:middle;text-align:center;font-size:13px;">공사구분</th>
        				<th style="width:120px;vertical-align:middle;text-align:center;font-size:13px;">부위</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">공사내역</th>
        				<th style="width:120px;vertical-align:middle;text-align:center;font-size:13px;">예산(천원)</th>
        			</tr>
	</c:if>
        			<tr height="40px">
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.rnum }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.mntnRprCnstStartDt }" /> ~ <br><c:out value="${result.mntnRprCnstEndDt }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.mntnRprSeNm }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.mntnRprPart }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.mntnRprCn }" /></td>
        				<td style="text-align:center;font-size:13px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.mntnRprBdgt }" /></td>
        			</tr>
</c:forEach>
        		</tbody>  
        	</table>
        </div>
    </div>
    
    
    <div class="page">
<c:set var="pagePerCount3" value="5"/>
<c:if test="${fn:length(mntnResultHistList) == 0}">
		<div class="subpage ygpa_report" >
        	<div style="height:40px;text-align:left;vertical-align:middle;border-top:1px red;font-size:20px;"> 6. 보수·보강이력</div>
    		<table style="width:100%;" class="rpr_form_table">
        		<tbody>
        			<tr height="40px">
        				<th rowspan="2" style="width:60px;vertical-align:middle;text-align:center;font-size:13px;">번호</th>
        				<th style="width:120px;vertical-align:middle;text-align:center;font-size:13px;">공사기간</th>
        				<th rowspan="2" style="width:80px;vertical-align:middle;text-align:center;font-size:13px;">부위</th>
        				<th rowspan="2" style="vertical-align:middle;text-align:center;font-size:13px;">공사내역</th>
        				<th style="width:100px;vertical-align:middle;text-align:center;font-size:13px;">설계자</th>
        				<th style="width:100px;vertical-align:middle;text-align:center;font-size:13px;">시공자</th>
        				<th rowspan="2" style="width:100px;vertical-align:middle;text-align:center;font-size:13px;">공사감독</th>
        				<th style="width:80px;vertical-align:middle;text-align:center;font-size:13px;">작성일</th>
        			</tr>
        			<tr height="40px">
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">공사구분</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">공사비(천원)</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">책임기술자</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">작성자(인)</th>
        			</tr>
        			<tr>
        				<td colspan="8" style="text-align:center;font-size:13px;">자료가 존재 하지 않습니다.</td>
        			</tr>
</c:if>
<c:forEach var="result" items="${mntnResultHistList }" varStatus="resultStatus">
	<c:if test="${resultStatus.index%pagePerCount3==0 }"> <% /*  페이지 당 출력 갯수 */ %>
	
		<c:if test="${resultStatus.index!=0 }">	<% /*  페이지 구분*/ %>
				</tbody>
			</table>
		</div>
	</div>
	<div class="page">
		</c:if>
		<div class="subpage ygpa_report" >
			<!--  헤더 반복  -->
		<c:choose>
			<c:when test="${(mntnResultHistListTotalCount - resultStatus.index) > pagePerCount3 }"><c:set var="continueChk1" value="(계속)"/></c:when>
			<c:otherwise><c:set var="continueChk1" value=""/></c:otherwise>
		</c:choose>
        	<div style="height:40px;text-align:left;vertical-align:middle;border-top:1px red;font-size:20px;"> 6. 보수·보강이력 <c:out value="${continueChk1 }" /></div>
       		<table style="width:100%;" class="rpr_form_table">
			   <tbody>
        			<tr height="40px">
        				<th rowspan="2" style="width:60px;vertical-align:middle;text-align:center;font-size:13px;">번호</th>
        				<th style="width:120px;vertical-align:middle;text-align:center;font-size:13px;">공사기간</th>
        				<th rowspan="2" style="width:80px;vertical-align:middle;text-align:center;font-size:13px;">부위</th>
        				<th rowspan="2" style="vertical-align:middle;text-align:center;font-size:13px;">공사내역</th>
        				<th style="width:100px;vertical-align:middle;text-align:center;font-size:13px;">설계자</th>
        				<th style="width:100px;vertical-align:middle;text-align:center;font-size:13px;">시공자</th>
        				<th rowspan="2" style="width:100px;vertical-align:middle;text-align:center;font-size:13px;">공사감독</th>
        				<th style="width:80px;vertical-align:middle;text-align:center;font-size:13px;">작성일</th>
        			</tr>
        			<tr height="40px">
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">공사구분</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">공사비(천원)</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">책임기술자</th>
        				<th style="vertical-align:middle;text-align:center;font-size:13px;">작성자(인)</th>
        			</tr>
	</c:if>
        			<tr height="40px">
        				<td rowspan="2" style="text-align:center;font-size:13px;"><c:out value="${result.rnum }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.mntnRprCnstStartDt }" /> ~ <br><c:out value="${result.mntnRprCnstEndDt }" /></td>
        				<td rowspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.mntnRprPart }" /></td>
        				<td rowspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.mntnRprCn }" /></td>
        				<td style="text-align:left;font-size:13px;word-break:break-all;"><c:out value="${result.plannerNm }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.cnstrtr }" /></td>
        				<td rowspan="2" style="text-align:center;font-size:13px;"><c:out value="${result.cnstChargNm }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.wrtDt }" /></td>
        			</tr>
        			<tr height="40px">
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.mntnRprSeNm }" /></td>
        				<td style="text-align:center;font-size:13px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.mntnRprCnstAmt }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.responEngineer }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.wrtUsr }" /></td>
        			</tr>
</c:forEach>
        		</tbody>  
        	</table>
        </div>
    </div>
    
</div>
  </c:if>
    <c:if test="${resultCode!=0 }">
    	<h2>서버 오류</h2>
    	<p>에러 메시지 : <c:out value="${ resultMessage}"/></p>
    </c:if>
  </body>
</html>