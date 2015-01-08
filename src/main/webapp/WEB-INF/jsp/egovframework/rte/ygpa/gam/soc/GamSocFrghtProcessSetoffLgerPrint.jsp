<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamSocFrghtProcessSetoffLgerPrint.jsp
  * @Description : 인쇄 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.10.22  HNJ          최초 생성
  *
  * author HNJ
  * since 2014.10.22
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
	<link rel="stylesheet" href="/css/ygpa/gam/ygpa_report.css" />

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
	    	<h1 class="ygpa_report_h1">총사업비보전 상계처리 내역(화물료)</h1>

	<c:if test="${fn:length(resultList) == 0}">
		<table style="width:100%;">
			<tr height="15">
				<td style="width:80px;font-size:12px;">상계기간 : </td>
				<td style="font-size:12px;" colSpan="2"><c:out value="${dtFr }" /> ~ <c:out value="${dtTo }" /></td>
			</tr>
			<tr height="15">
				<td style="width:80px;font-size:12px;">입출항일자 : </td>
				<td style="font-size:12px;"><c:out value="${ioDt }" /> ~ <c:out value="${ioDt2 }" /></td>
				<td style="text-align: right;font-size:12px;">보전누계액 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${exmpAcc }" /></td>
			</tr>
		</table>
		<table class="rpr_form_table">
			<tbody>
				<tr height="25">
					<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">공사항구</th>
					<td style="width:30px;text-align:center;font-size:12px;"></td>
					<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">준공년도</th>
					<td style="width:60px;text-align:center;font-size:12px;"></td>
					<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">공사번호</th>
					<td style="width:40px;text-align:center;font-size:12px;"></td>
					<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">공사명</th>
					<td style="width:60px;text-align:center;font-size:12px;" colspan="3"></td>
				</tr>
				<tr height="25">
					<th style="text-align:center;vertical-align:middle;font-size:12px;">요청항구</th>
					<td style="text-align:center;font-size:12px;"></td>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">요청업체</th>
					<td style="text-align:center;font-size:12px;"></td>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">신청횟수</th>
					<td style="text-align:center;font-size:12px;"></td>
					<th style="text-align:center;vertical-align:middle;font-size:12px;">총공사금액</th>
					<td style="text-align:center;font-size:12px;"></td>
					<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;">보전처리대상금액</th>
					<td style="text-align:center;font-size:12px;"></td>
				</tr>
			</tbody>
		</table>
		<table class="rpr_main_table">
	  		<thead>
	  			<tr height="25">
	  				<th style="width:30px;text-align:center;vertical-align:middle;font-size:12px;">선명</th>
	  				<th style="width:50px;text-align:center;vertical-align:middle;font-size:12px;">호출부호</th>
	  				<th style="width:50px;text-align:center;vertical-align:middle;font-size:12px;">입항횟수</th>
	  				<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">관리번호</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;" colspan="2">상계일자</th>
	  				<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">적용요율</th>
	  				<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">입출항일자</th>
	  				<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">내외항</th>
	  				<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">신고업체</th>
	  				<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">신고업체명</th>
	 			</tr>
	 			<tr height="25">
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;"></th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">처리항구</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">요금종류</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;"></th>
	  				<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">할인율</th>
	  				<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">운임톤</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">상계금액(청)</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">상계금액(공사)</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;" colspan="3">특이사항</th>
	 			</tr>
	  		</thead>
	  		<tbody>
			<tr>
				<td style="text-align:center;font-size:12px;border:1px #000000 solid;" colspan="11">자료가 존재 하지 않습니다.</td>
			</tr>
	</c:if>
    <c:forEach var="result" items="${resultList }" varStatus="resultStatus">
           			<c:if test="${resultStatus.index%14==0}"> <% /*  페이지 당 출력 갯수 */ %>
           				<c:if test="${resultStatus.index!=0}">	<% /*  페이지 구분*/ %>
			        		</tbody>
			        		</table>
	        		        </div>
    						</div>
						    <div class="page">
						        <div class="subpage ygpa_report" >
           				</c:if>
        				<!--  헤더 반복  -->
        				<table style="width:100%;">
							<tr height="25">
								<td style="width:80px;font-size:12px;">상계기간 : </td>
								<td style="text-align:center;font-size:12px;" colSpan="2"><c:out value="${dtFr }" /> ~ <c:out value="${dtTo }" /></td>
							</tr>
							<tr height="25">
								<td style="width:80px;font-size:12px;">입출항일자 : </td>
								<td style="text-align:center;font-size:12px;"><c:out value="${ioDt }" /> ~ <c:out value="${ioDt2 }" /></td>
								<td style="text-align: right;font-size:12px;">보전누계액 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${exmpAcc }" /></td>
							</tr>
						</table>
						<table class="rpr_form_table">
							<tbody>
								<tr height="25">
									<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">공사항구</th>
									<td style="width:30px;text-align:center;font-size:12px;"><c:out value="${result.prtAtCode } ${result.prtAtNm }" /></td>
									<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">준공년도</th>
									<td style="width:60px;text-align:center;font-size:12px;"><c:out value="${result.cmplYr }" /></td>
									<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">공사번호</th>
									<td style="width:40px;text-align:center;font-size:12px;"><c:out value="${result.constNo }" /></td>
									<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">공사명</th>
									<td style="width:60px;text-align:center;font-size:12px;" colspan="3"><c:out value="${result.socCnstNm }" /></td>
								</tr>
								<tr height="25">
									<th style="text-align:center;vertical-align:middle;font-size:12px;">요청항구</th>
									<td style="text-align:center;font-size:12px;"><c:out value="${result.appPrtAtCode } ${result.appPrtAtNm }" /></td>
									<th style="text-align:center;vertical-align:middle;font-size:12px;">요청업체</th>
									<td style="text-align:center;font-size:12px;"><c:out value="${result.sctmc }  ${result.agentNm2 }" /></td>
									<th style="text-align:center;vertical-align:middle;font-size:12px;">신청횟수</th>
									<td style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.useNo }" /></td>
									<th style="text-align:center;vertical-align:middle;font-size:12px;">총공사금액</th>
									<td style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.totalAmnt }" /></td>
									<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;">보전처리대상금액</th>
									<td style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.exmpAmnt }" /></td>
								</tr>
							</tbody>
						</table>
       		        	<table class="rpr_main_table">
			        		<thead>
			        			<tr height="25">
					  				<th style="width:30px;text-align:center;vertical-align:middle;font-size:12px;">선명</th>
					  				<th style="width:50px;text-align:center;vertical-align:middle;font-size:12px;">호출부호</th>
					  				<th style="width:50px;text-align:center;vertical-align:middle;font-size:12px;">입항횟수</th>
					  				<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">관리번호</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;" colspan="2">상계일자</th>
					  				<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">적용요율</th>
					  				<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">입출항일자</th>
					  				<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">내외항</th>
					  				<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">신고업체</th>
					  				<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">신고업체명</th>
					 			</tr>
					 			<tr height="25">
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;"></th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">처리항구</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">요금종류</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;"></th>
					  				<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">할인율</th>
					  				<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;">운임톤</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">상계금액(청)</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">상계금액(공사)</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;" colspan="3">특이사항</th>
					 			</tr>
			        		</thead>
			        		<tbody>
        			</c:if>
        			<tr height="25">
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.dVsslKorNm }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.callLetter }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.yicn }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.fiscalSocNo }" /></td>
        				<td style="text-align:center;font-size:12px;" colspan="2"><c:out value="${result.billDt }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.standardFee }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.ioDt }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.ixtn }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.mctmc }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.agentNm3 }" /></td>
        			</tr>
        			<tr height="25">
        				
        				<td style="text-align:center;font-size:12px;"></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.prtKorNm }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.feeTp } ${result.feeTpKorNm }" /></td>
        				<td style="text-align:center;font-size:12px;"></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.dcCode } ${result.rate }" /></td>
        				<td style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.realTn }" /></td>
        				<td style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.exmpAmnt }" /></td>
        				<td style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.exmpAmntPa }" /></td>
        				<td style="text-align:center;font-size:12px;" colspan="3"><c:out value="${result.remark }" /></td>
        			</tr>
    </c:forEach>
        		</tbody>
        		<tfoot>
        			<tr height="25">
        				<td style="text-align:center;font-size:12px;" colspan="2"></td>
        				<th style="width:50px;text-align:center;vertical-align:middle;font-size:12px;border:1px #000000 solid;">총계(청)</th>
        				<td colspan="2" style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumExmpAmnt }" /></td>
        				<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;border:1px #000000 solid;">총계(공사)</th>
        				<td colspan="2" style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumExmpAmntPa }" /></td>
        				<th style="width:60px;text-align:center;vertical-align:middle;font-size:12px;border:1px #000000 solid;">총계(전체)</th>
        				<td colspan="2" style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumAmnt }" /></td>
        			</tr>
        		</tfoot>
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