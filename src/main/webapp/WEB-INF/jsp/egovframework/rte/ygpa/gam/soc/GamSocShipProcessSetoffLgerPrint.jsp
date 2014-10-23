<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamSocShipProcessSetoffLgerPrint.jsp
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
  <c:if test="${resultCode==0 }">
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
	    	<h1 class="ygpa_report_h1">총사업비보전 상계처리 내역(선박입항료)</h1>

	<c:if test="${fn:length(resultList) == 0}">
		<table style="width:100%;">
			<tr>
				<td style="width:80px;">상계기간 : </td>
				<td colSpan="2"><c:out value="${dtFr }" /> ~ <c:out value="${dtTo }" /></td>
			</tr>
			<tr>
				<td style="width:80px;">입출항일자 : </td>
				<td><c:out value="${ioDt }" /> ~ <c:out value="${ioDt2 }" /></td>
				<td style="text-align: right">보전누계액 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${exmpAcc }" /></td>
			</tr>
		</table>
		<table class="rpr_form_table">
			<tbody>
				<tr>
					<th>공사항구</th>
					<td></td>
					<th>준공년도</th>
					<td></td>
					<th>공사번호</th>
					<td></td>
					<th>공사명</th>
					<td colspan="3"></td>
				</tr>
				<tr>
					<th>요청항구</th>
					<td></td>
					<th>요청업체</th>
					<td></td>
					<th>신청횟수</th>
					<td></td>
					<th>총공사비보전금액(원부)</th>
					<td></td>
					<th>보전처리대상금액</th>
					<td></td>
				</tr>
			</tbody>
		</table>
		<table class="rpr_main_table">
	  		<thead>
	  			<tr>
	  				<th>선명</th>
	  				<th>호출부호</th>
	  				<th>입항횟수</th>
	  				<th>관리번호</th>
	  				<th colspan="2">상계일자</th>
	  				<th>적용요율</th>
	  				<th>사용시설</th>
	  				<th>내외항</th>
	  				<th>신고업체</th>
	  				<th>신고업체명</th>
	 			</tr>
	 			<tr>
	  				<th></th>
	  				<th>처리항구</th>
	  				<th>요금종류</th>
	  				<th>할인율</th>
	  				<th>상계금액(청)</th>
	  				<th>상계금액(공사)</th>
	  				<th>사용기간(From~To)</th>
	  				<th colspan="4">특이사항</th>
	 			</tr>
	  		</thead>
	  		<tbody>
			<tr>
				<td colspan="11">자료가 존재 하지 않습니다.</td>
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
							<tr>
								<td style="width:80px;">상계기간 : </td>
								<td colSpan="2"><c:out value="${dtFr }" /> ~ <c:out value="${dtTo }" /></td>
							</tr>
							<tr>
								<td style="width:80px;">입출항일자 : </td>
								<td><c:out value="${ioDt }" /> ~ <c:out value="${ioDt2 }" /></td>
								<td style="text-align: right">보전누계액 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${exmpAcc }" /></td>
							</tr>
						</table>
						<table class="rpr_form_table">
							<tbody>
								<tr>
									<th>공사항구</th>
									<td><c:out value="${result.prtAtCode } ${result.prtAtNm }" /></td>
									<th>준공년도</th>
									<td><c:out value="${result.cmplYr }" /></td>
									<th>공사번호</th>
									<td><c:out value="${result.constNo }" /></td>
									<th>공사명</th>
									<td colspan="3"><c:out value="${result.socCnstNm }" /></td>
								</tr>
								<tr>
									<th>요청항구</th>
									<td><c:out value="${result.appPrtAtCode } ${result.appPrtAtNm }" /></td>
									<th>요청업체</th>
									<td><c:out value="${result.sctmc }  ${result.agentNm2 }" /></td>
									<th>신청횟수</th>
									<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.useNo }" /></td>
									<th>총공사비보전금액(원부)</th>
									<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.totalAmnt }" /></td>
									<th>보전처리대상금액</th>
									<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.exmpAmnt }" /></td>
								</tr>
							</tbody>
						</table>
       		        	<table class="rpr_main_table">
			        		<thead>
			        			<tr>
					  				<th>선명</th>
					  				<th>호출부호</th>
					  				<th>입항횟수</th>
					  				<th>관리번호</th>
					  				<th colspan="2">상계일자</th>
					  				<th>적용요율</th>
					  				<th>사용시설</th>
					  				<th>내외항</th>
					  				<th>신고업체</th>
					  				<th>신고업체명</th>
					 			</tr>
					 			<tr>
					  				<th></th>
					  				<th>처리항구</th>
					  				<th>요금종류</th>
					  				<th>할인율</th>
					  				<th>상계금액(청)</th>
					  				<th>상계금액(공사)</th>
					  				<th>사용기간(From~To)</th>
					  				<th colspan="4">특이사항</th>
					 			</tr>
			        		</thead>
			        		<tbody>
        			</c:if>
        			<tr>
        				<td><c:out value="${result.dVsslKorNm }" /></td>
        				<td><c:out value="${result.callLetter }" /></td>
        				<td><c:out value="${result.yicn }" /></td>
        				<td><c:out value="${result.fiscalSocNo }" /></td>
        				<td colspan="2"><c:out value="${result.billDt }" /></td>
        				<td><c:out value="${result.standardFee }" /></td>
        				<td><c:out value="${result.facCode } ${result.facKorNm }" /></td>
        				<td><c:out value="${result.ixtn }" /></td>
        				<td><c:out value="${result.mctmc }" /></td>
        				<td><c:out value="${result.agentNm3 }" /></td>
        			</tr>
        			<tr>
        				
        				<td></td>
        				<td><c:out value="${result.prtKorNm }" /></td>
        				<td><c:out value="${result.feeTp } ${result.feeTpKorNm }" /></td>
        				<td><c:out value="${result.dcCode }" /></td>
        				<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.exmpAmnt }" /></td>
        				<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.exmpAmntPa }" /></td>
        				<td><c:out value="${result.usdt }" /></td>
        				<td colspan="4"><c:out value="${result.remark }" /></td>
        			</tr>
    </c:forEach>
        		</tbody>
        		<tfoot>
        			<tr>
        				<td colspan="11">
        					<table style="width:100%;" class="rpr_main_table">
	        	               	<tr>
									<th width="10%" height="22">접안료(청)</th>
									<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumR1Fare }" /></td>
									<th width="10%" height="22">정박료(청)</th>
									<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumR2Fare }" /></td>
									<th width="10%" height="22">입항료(청)</th>
									<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumR3Fare }" /></td>
									<th width="10%" height="22">기타(청)</th>
									<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumR6Fare }" /></td>
									<th width="10%" height="22">총계(청)</th>
									<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumRFare }" /></td>
								</tr>
								<tr>
									<th width="10%" height="22">접안료(공사)</th>
									<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumR1FarePa }" /></td>
									<th width="10%" height="22">정박료(공사)</th>
									<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumR2FarePa }" /></td>
									<th width="10%" height="22">입항료(공사)</th>
									<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumR3FarePa }" /></td>
									<th width="10%" height="22">기타(공사)</th>
									<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumR6FarePa }" /></td>
									<th width="10%" height="22">총계(공사)</th>
									<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumRFarePa }" /></td>
								</tr>
								<tr>
									<th width="10%" height="22">접안료(전체)</th>
									<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumR1All }" /></td>
									<th width="10%" height="22">정박료(전체)</th>
									<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumR2All }" /></td>
									<th width="10%" height="22">입항료(전체)</th>
									<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumR3All }" /></td>
									<th width="10%" height="22">기타(전체)</th>
									<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumR6All }" /></td>
									<th width="10%" height="22">총계(전체)</th>
									<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumRAll }" /></td>
								</tr>
							</table>
        				</td>
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