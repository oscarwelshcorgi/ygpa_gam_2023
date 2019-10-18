<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamFcltyCtrtLgerHistPrint.jsp
  * @Description : 인쇄 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.10.30  HNJ          최초 생성
  *
  * author HNJ
  * since 2014.10.30
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<html lang="ko" xml:lang="ko">
  <head>
    <title>여수광양항만공사 - GIS기반 자산관리 시스템</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8"> 
	<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/reset.css' />"/> 
	<link rel="stylesheet" href="<c:url value='/css/demo/jquery-ui-1.10.4.custom.css' />" />
	<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/ygpa_report_l.css' />" />
	<script src="<c:url value='/js/jquery-1.10.2.min.js' />"></script>
	<script src="<c:url value='/js/jquery-migrate-1.2.1.min.js' />"></script>
	<script src="<c:url value='/js/jquery-ui.min.js' />"></script>
		
		
		
	<script>
	$( window ).load(function() {
		$('#printButton').button().click(function(){
	        			window.print();
		});
	});
	</script>
  </head>
  <body>
  <c:set var="pagePerCount" value="13"/>
  <c:set var="appPrtAtCode" value=""/>
  <c:set var="appPrtAtNm" value=""/>
  <c:set var="prtAtCode" value="" />
  <c:set var="prtAtNm" value="" />
  <c:set var="agentCode" value="" />
  <c:set var="agentName" value="" />
  <c:set var="constNo" value="" />
  <c:set var="pageSkip" value="true" />
  <c:set var="checkIndex" value="0" />
  
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
	    	<h1 class="ygpa_report_h1">계 약 대 장</h1>
	    	
	    <table class="rpr_form_table">
			<tbody>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">구 분</th>
					<th style="width:120px;text-align:center;vertical-align:middle;font-size:12px;">공고번호</th>
					<th style="width:120px;text-align:center;vertical-align:middle;font-size:12px;">입찰공고</th>
					<th style="width:120px;text-align:center;vertical-align:middle;font-size:12px;">입찰일</th>
					<th style="width:120px;text-align:center;vertical-align:middle;font-size:12px;">등록업체</th>
					<th style="width:120px;text-align:center;vertical-align:middle;font-size:12px;">현장설명</th>
				</tr>
				<tr height="25">
					<td style="width:80px;text-align:center;font-size:12px;"><c:out value="${fcltyCtrtLgerHistDetail.ctrtSe }" /></td>
					<td style="width:120px;font-size:12px;"><c:out value="${fcltyCtrtLgerHistDetail.bidPblancNo }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtLgerHistDetail.bidPblancDt }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtLgerHistDetail.bidDt }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtLgerHistDetail.registEntrpsCd }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtLgerHistDetail.siteDesc }" /></td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">계약명</th>
					<td style="font-size:12px;" colSpan="5"><c:out value="${fcltyCtrtLgerHistDetail.ctrtNm }" /></td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">예 산</th>
					<th style="width:120px;text-align:center;vertical-align:middle;font-size:12px;">설계금액</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">예정금액</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">낙찰액</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">낙찰율</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">기초금액</th>
				</tr>
				<tr height="25">
					<td style="width:80px;"> </td>
					<td style="width:120px;text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtLgerHistDetail.planAmt }" /></td>
					<td style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtLgerHistDetail.prmtAmt }" /></td>
					<td style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtLgerHistDetail.scsbidAmt }" /></td>
					<td style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtLgerHistDetail.scsbidRate }" /></td>
					<td style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtLgerHistDetail.baseAmt }" /></td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">입찰방법</th>
					<td style="font-size:12px;" colSpan="5"><c:out value="${fcltyCtrtLgerHistDetail.bidMth }" /></td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">계약방법</th>
					<td style="font-size:12px;" colSpan="5"><c:out value="${fcltyCtrtLgerHistDetail.ctrtMth }" /></td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">계약일</th>
					<th style="width:120px;text-align:center;vertical-align:middle;font-size:12px;">계약기간</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">1회변경</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">2회변경</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">3회변경</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">4회변경</th>
				</tr>
				<tr height="25">
					<td style="width:80px;font-size:12px;"><c:out value="${fcltyCtrtLgerHistDetail.ctrtDt }" /></td>
					<td style="width:120px;font-size:12px;"><c:out value="${fcltyCtrtLgerHistDetail.ctrtDtFrom }" /> ~ <c:out value="${fcltyCtrtLgerHistDetail.ctrtDtTo }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtChangeFList[0].changeCtrtDtFrom }" /> ~ <c:out value="${fcltyCtrtChangeFList[0].changeCtrtDtTo }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtChangeFList[1].changeCtrtDtFrom }" /> ~ <c:out value="${fcltyCtrtChangeFList[1].changeCtrtDtTo }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtChangeFList[2].changeCtrtDtFrom }" /> ~ <c:out value="${fcltyCtrtChangeFList[2].changeCtrtDtTo }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtChangeFList[3].changeCtrtDtFrom }" /> ~ <c:out value="${fcltyCtrtChangeFList[3].changeCtrtDtTo }" /></td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">계약금액</th>
					<th style="width:120px;text-align:center;vertical-align:middle;font-size:12px;">최초</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">1회변경</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">2회변경</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">3회변경</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">4회변경</th>
				</tr>
				<tr height="25">
					<td> </td>
					<td style="width:80px;text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtLgerHistDetail.ctrtAmt }" /></td>
					<td style="width:120px;text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtChangeFList[0].lastCtrtAmt }" /></td>
					<td style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtChangeFList[1].lastCtrtAmt }" /></td>
					<td style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtChangeFList[2].lastCtrtAmt }" /></td>
					<td style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtChangeFList[3].lastCtrtAmt }" /></td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">계약자</th>
					<th style="width:120px;text-align:center;vertical-align:middle;font-size:12px;">지분율</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">회사명</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">대표자</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">전화번호</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">FAX</th>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">공동도급자1</th>
					<td style="width:120px;text-align: right;font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[0].qotaRate }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[0].entrpsNm }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[0].rprsntv }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[0].tlphonNo }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[0].faxNo }" /></td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">공동도급자2</th>
					<td style="width:120px;text-align: right;font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[1].qotaRate }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[1].entrpsNm }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[1].rprsntv }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[1].tlphonNo }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[1].faxNo }" /></td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">공동도급자3</th>
					<td style="width:120px;text-align: right;font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[2].qotaRate }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[2].entrpsNm }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[2].rprsntv }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[2].tlphonNo }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[2].faxNo }" /></td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">공동도급자4</th>
					<td style="width:120px;font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[3].qotaRate }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[3].entrpsNm }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[3].rprsntv }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[3].tlphonNo }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[3].faxNo }" /></td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">공동도급자5</th>
					<td style="width:120px;text-align: right;font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[4].qotaRate }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[4].entrpsNm }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[4].rprsntv }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[4].tlphonNo }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[4].faxNo }" /></td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">공동도급자6</th>
					<td style="width:120px;text-align: right;font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[5].qotaRate }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[5].entrpsNm }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[5].rprsntv }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[5].tlphonNo }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtJoinContrFList[5].faxNo }" /></td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">연대보증인</th>
					<td style="font-size:12px;" colSpan="5"><c:out value="${fcltyCtrtLgerHistDetail.sldrtGrnty }" /></td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">감독원</th>
					<td style="width:120px;font-size:12px;"><c:out value="${fcltyCtrtLgerHistDetail.intendant1 }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtLgerHistDetail.intendant2 }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtLgerHistDetail.intendant3 }" /></td>
					<td> </td>
					<td> </td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">업무담당</th>
					<td style="width:120px;font-size:12px;"><c:out value="${fcltyCtrtLgerHistDetail.jobChrgDeptCd }" /></td>
					<td> </td>
					<td> </td>
					<td> </td>
					<td> </td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">이행금액</th>
					<td style="width:120px;font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[0].fulfillCaryFwdYear }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[1].fulfillCaryFwdYear }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[2].fulfillCaryFwdYear }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[3].fulfillCaryFwdYear }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[4].fulfillCaryFwdYear }" /></td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;"> </th>
					<td style="width:120px;font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[0].fulfillAmt }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[1].fulfillAmt }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[2].fulfillAmt }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[3].fulfillAmt }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[4].fulfillAmt }" /></td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">이월금액</th>
					<td style="width:120px;font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[0].fulfillCaryFwdYear }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[1].fulfillCaryFwdYear }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[2].fulfillCaryFwdYear }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[3].fulfillCaryFwdYear }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[4].fulfillCaryFwdYear }" /></td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;"> </th>
					<td style="width:120px;font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[0].caryFwdAmt }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[1].caryFwdAmt }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[2].caryFwdAmt }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[3].caryFwdAmt }" /></td>
					<td style="font-size:12px;"><c:out value="${fcltyCtrtFulfillCaryFwdFList[4].caryFwdAmt }" /></td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">원인행위</th>
					<td style="font-size:12px;" colSpan="5"><c:out value="${fcltyCtrtLgerHistDetail.causeAct }" /></td>
				</tr>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">계약변경</th>
					<th style="width:120px;text-align:center;vertical-align:middle;font-size:12px;">변경일</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">변경사유</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">금액</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">기간</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">기타</th>
				</tr>
		<c:set var="rowNum" value="1" />
		<c:set var="resultCount" value="${fn:length(fcltyCtrtChangeFList)}" />
		<c:set var="lastRotation" value="${5-resultCount }" />
		<c:forEach var="result" items="${fcltyCtrtChangeFList }" varStatus="resultStatus" begin="0" end="4">
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;"><c:out value="${rowNum }" />회</th>
					<td style="width:120px;font-size:12px;"><c:out value="${result.changeDt }" /></td>
					<td style="font-size:12px;"><c:out value="${result.changeRsn }" /></td>
					<td style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.lastCtrtAmt }" /></td>
					<td style="font-size:12px;"><c:out value="${result.changeCtrtDtFrom }" /> ~ <c:out value="${result.changeCtrtDtTo }" /></td>
					<td style="font-size:12px;"><c:out value="${result.rm }" /></td>
				</tr>
			<c:set var="rowNum" value="${rowNum+1}" />
		</c:forEach>
		<c:forEach begin="1" end="${lastRotation }" step="1">
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;"><c:out value="${rowNum }" />회</th>
					<td style="width:120px;"> </td>
					<td> </td>
					<td> </td>
					<td> </td>
					<td> </td>
				</tr>
			<c:set var="rowNum" value="${rowNum+1}" />
		</c:forEach>
				<tr height="25">
					<th style="width:80px;text-align:center;vertical-align:middle;font-size:12px;">하자기간</th>
					<td style="font-size:12px;" colSpan="5"><c:out value="${fcltyCtrtLgerHistDetail.flawDtFrom }" /> ~ <c:out value="${fcltyCtrtLgerHistDetail.flawDtTo }" /></td>
				</tr>
			</tbody>
		</table>
		</div>
	</div>
	<div class="page">
		<div class="subpage ygpa_report" >
		<table class="rpr_form_table">
			<tbody>
				<tr height="37">
					<th style="text-align:center;vertical-align:middle;font-size:12px;">대금지급</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">지급분류</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">지급일</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">금회기성액</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">선금정산액</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">지급액</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">누계액</th>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">비고</th>
				</tr>
		<c:set var="rowNum" value="1" />
		<c:set var="resultCount" value="${fn:length(fcltyCtrtMoneyPymntFList)}" />
		<c:set var="lastRotation" value="${25-resultCount }" />
		<c:forEach var="result" items="${fcltyCtrtMoneyPymntFList }" varStatus="resultStatus" begin="0" end="24" step="1">
				<tr height="37">
					<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;"><c:out value="${rowNum }" />회</th>
					<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;"><c:out value="${result.pymntCl }" /></th>
					<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;"><c:out value="${result.pymntDt }" /></th>
					<td style="width:100px;text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.thisTimeEstbAmt }" /></td>
					<td style="width:100px;text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.depositExcclcAmt }" /></td>
					<td style="width:100px;text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.pymntAmt }" /></td>
					<td style="width:100px;text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.pymntAggrAmt }" /></td>
					<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;"><c:out value="${result.rm }" /></th>
				</tr>
			<c:set var="rowNum" value="${rowNum+1}" />
		</c:forEach>
		<c:forEach begin="1" end="${lastRotation }" step="1">
				<tr height="37">
					<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;"><c:out value="${rowNum }" />회</th>
					<td style="width:100px;"> </td>
					<td style="width:100px;"> </td>
					<td style="width:100px;"> </td>
					<td style="width:100px;"> </td>
					<td style="width:100px;"> </td>
					<td style="width:100px;"> </td>
					<td style="width:100px;"> </td>
				</tr>
			<c:set var="rowNum" value="${rowNum+1}" />
		</c:forEach>
			</tbody>
		</table>
		</div>
	</div>
</div>

    <c:if test="${resultCode!=0 }">
    	<h2>서버 오류</h2>
    	<p>에러 메시지 : <c:out value="${ resultMessage}"/></p>
    </c:if>
  </body>
</html>