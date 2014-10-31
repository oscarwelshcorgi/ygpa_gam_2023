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
<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/reset.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/demo/jquery-ui-1.10.4.custom.css' />" />
<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/ygpa_report.css' />" />

	<script src="<c:url value='/js/jquery-1.10.2.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-migrate-1.2.1.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-ui.min.js'/>"></script>
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
				<tr>
					<th>구 분</th>
					<th>공고번호</th>
					<th>입찰공고</th>
					<th>입찰일</th>
					<th>등록업체</th>
					<th>현장설명</th>
				</tr>
				<tr>
					<td style="text-align: center"><c:out value="${fcltyCtrtLgerHistDetail.ctrtSe }" /></td>
					<td><c:out value="${fcltyCtrtLgerHistDetail.bidPblancNo }" /></td>
					<td><c:out value="${fcltyCtrtLgerHistDetail.bidPblancDt }" /></td>
					<td><c:out value="${fcltyCtrtLgerHistDetail.bidDt }" /></td>
					<td><c:out value="${fcltyCtrtLgerHistDetail.registEntrpsCd }" /></td>
					<td><c:out value="${fcltyCtrtLgerHistDetail.siteDesc }" /></td>
				</tr>
				<tr>
					<th>계약명</th>
					<td colSpan="5"><c:out value="${fcltyCtrtLgerHistDetail.ctrtNm }" /></td>
				</tr>
				<tr>
					<th>예 산</th>
					<th>설계금액</th>
					<th>예정금액</th>
					<th>낙찰액</th>
					<th>낙찰율</th>
					<th>기초금액</th>
				</tr>
				<tr>
					<td> </td>
					<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtLgerHistDetail.planAmt }" /></td>
					<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtLgerHistDetail.prmtAmt }" /></td>
					<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtLgerHistDetail.scsbidAmt }" /></td>
					<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtLgerHistDetail.scsbidRate }" /></td>
					<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtLgerHistDetail.baseAmt }" /></td>
				</tr>
				<tr>
					<th>입찰방법</th>
					<td colSpan="5"><c:out value="${fcltyCtrtLgerHistDetail.bidMth }" /></td>
				</tr>
				<tr>
					<th>계약방법</th>
					<td colSpan="5"><c:out value="${fcltyCtrtLgerHistDetail.ctrtMth }" /></td>
				</tr>
				<tr>
					<th>계약일</th>
					<th>계약기간</th>
					<th>1회변경</th>
					<th>2회변경</th>
					<th>3회변경</th>
					<th>4회변경</th>
				</tr>
				<tr>
					<td><c:out value="${fcltyCtrtLgerHistDetail.ctrtDt }" /></td>
					<td><c:out value="${fcltyCtrtLgerHistDetail.ctrtPdFrom }" /> ~ <c:out value="${fcltyCtrtLgerHistDetail.ctrtPdTo }" /></td>
					<td><c:out value="${fcltyCtrtChangeFList[0].changeCtrtPdFrom }" /> ~ <c:out value="${fcltyCtrtChangeFList[0].changeCtrtPdTo }" /></td>
					<td><c:out value="${fcltyCtrtChangeFList[1].changeCtrtPdFrom }" /> ~ <c:out value="${fcltyCtrtChangeFList[1].changeCtrtPdTo }" /></td>
					<td><c:out value="${fcltyCtrtChangeFList[2].changeCtrtPdFrom }" /> ~ <c:out value="${fcltyCtrtChangeFList[2].changeCtrtPdTo }" /></td>
					<td><c:out value="${fcltyCtrtChangeFList[3].changeCtrtPdFrom }" /> ~ <c:out value="${fcltyCtrtChangeFList[3].changeCtrtPdTo }" /></td>
				</tr>
				<tr>
					<th>계약금액</th>
					<th>최초</th>
					<th>1회변경</th>
					<th>2회변경</th>
					<th>3회변경</th>
					<th>4회변경</th>
				</tr>
				<tr>
					<td> </td>
					<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtLgerHistDetail.ctrtAmt }" /></td>
					<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtChangeFList[0].lastCtrtAmt }" /></td>
					<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtChangeFList[1].lastCtrtAmt }" /></td>
					<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtChangeFList[2].lastCtrtAmt }" /></td>
					<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${fcltyCtrtChangeFList[3].lastCtrtAmt }" /></td>
				</tr>
				<tr>
					<th>계약자</th>
					<th>지분율</th>
					<th>회사명</th>
					<th>대표자</th>
					<th>전화번호</th>
					<th>FAX</th>
				</tr>
				<tr>
					<th>공동도급자1</th>
					<td style="text-align: right"><c:out value="${fcltyCtrtJoinContrFList[0].qotaRate }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[0].entrpsNm }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[0].rprsntv }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[0].tlphonNo }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[0].faxNo }" /></td>
				</tr>
				<tr>
					<th>공동도급자2</th>
					<td style="text-align: right"><c:out value="${fcltyCtrtJoinContrFList[1].qotaRate }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[1].entrpsNm }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[1].rprsntv }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[1].tlphonNo }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[1].faxNo }" /></td>
				</tr>
				<tr>
					<th>공동도급자3</th>
					<td style="text-align: right"><c:out value="${fcltyCtrtJoinContrFList[2].qotaRate }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[2].entrpsNm }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[2].rprsntv }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[2].tlphonNo }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[2].faxNo }" /></td>
				</tr>
				<tr>
					<th>공동도급자4</th>
					<td><c:out value="${fcltyCtrtJoinContrFList[3].qotaRate }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[3].entrpsNm }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[3].rprsntv }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[3].tlphonNo }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[3].faxNo }" /></td>
				</tr>
				<tr>
					<th>공동도급자5</th>
					<td style="text-align: right"><c:out value="${fcltyCtrtJoinContrFList[4].qotaRate }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[4].entrpsNm }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[4].rprsntv }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[4].tlphonNo }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[4].faxNo }" /></td>
				</tr>
				<tr>
					<th>공동도급자6</th>
					<td style="text-align: right"><c:out value="${fcltyCtrtJoinContrFList[5].qotaRate }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[5].entrpsNm }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[5].rprsntv }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[5].tlphonNo }" /></td>
					<td><c:out value="${fcltyCtrtJoinContrFList[5].faxNo }" /></td>
				</tr>
				<tr>
					<th>연대보증인</th>
					<td colSpan="5"><c:out value="${fcltyCtrtLgerHistDetail.sldrtGrnty }" /></td>
				</tr>
				<tr>
					<th>감독원</th>
					<td><c:out value="${fcltyCtrtLgerHistDetail.intendant1 }" /></td>
					<td><c:out value="${fcltyCtrtLgerHistDetail.intendant2 }" /></td>
					<td><c:out value="${fcltyCtrtLgerHistDetail.intendant3 }" /></td>
					<td> </td>
					<td> </td>
				</tr>
				<tr>
					<th>업무담당</th>
					<td><c:out value="${fcltyCtrtLgerHistDetail.jobChrgDeptCd }" /></td>
					<td> </td>
					<td> </td>
					<td> </td>
					<td> </td>
				</tr>
				<tr>
					<th>이행금액</th>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[0].fulfillCaryFwdYear }" /></td>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[1].fulfillCaryFwdYear }" /></td>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[2].fulfillCaryFwdYear }" /></td>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[3].fulfillCaryFwdYear }" /></td>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[4].fulfillCaryFwdYear }" /></td>
				</tr>
				<tr>
					<th> </th>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[0].fulfillAmt }" /></td>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[1].fulfillAmt }" /></td>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[2].fulfillAmt }" /></td>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[3].fulfillAmt }" /></td>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[4].fulfillAmt }" /></td>
				</tr>
				<tr>
					<th>이월금액</th>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[0].fulfillCaryFwdYear }" /></td>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[1].fulfillCaryFwdYear }" /></td>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[2].fulfillCaryFwdYear }" /></td>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[3].fulfillCaryFwdYear }" /></td>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[4].fulfillCaryFwdYear }" /></td>
				</tr>
				<tr>
					<th> </th>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[0].caryFwdAmt }" /></td>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[1].caryFwdAmt }" /></td>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[2].caryFwdAmt }" /></td>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[3].caryFwdAmt }" /></td>
					<td><c:out value="${fcltyCtrtFulfillCaryFwdFList[4].caryFwdAmt }" /></td>
				</tr>
				<tr>
					<th>원인행위</th>
					<td colSpan="5"><c:out value="${fcltyCtrtLgerHistDetail.causeAct }" /></td>
				</tr>
				<tr>
					<th>계약변경</th>
					<th>변경일</th>
					<th>변경사유</th>
					<th>금액</th>
					<th>기간</th>
					<th>기타</th>
				</tr>
				<tr>
					<th>1회</th>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<th>하자기간</th>
					<td colSpan="5"></td>
				</tr>
			</tbody>
		</table>

		

    <c:if test="${resultCode!=0 }">
    	<h2>서버 오류</h2>
    	<p>에러 메시지 : <c:out value="${ resultMessage}"/></p>
    </c:if>
  </body>
</html>