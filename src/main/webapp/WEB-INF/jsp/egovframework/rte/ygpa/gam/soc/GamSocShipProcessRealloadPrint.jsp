<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamSocShipProcessRealloadPrint.jsp
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
  
  <c:if test="${resultCode==0 }">
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
	    	<h1 class="ygpa_report_h1">투자비보전 처리실적</h1>

	<c:if test="${fn:length(resultList) == 0}">
		<table style="width:100%;">
			<tr height="15">
				<td style="width:40px;font-size:12px;">관할청 : </td>
				<td style="width:380px;text-align:left;font-size:12px;"><c:out value="${prtAtCode }" /> [ <c:out value="${prtKorNm }" /> ]</td>
				<td style="text-align:right;font-size:12px;">고지기간 : <c:out value="${frDt }" /> ~ <c:out value="${toDt }" /></td>
			</tr>
		</table>
		<table class="rpr_main_table">
	  		<thead>
	  			<tr height="25">
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">업체코드</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">호출부호</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">선박명</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">입항연도</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">입출항일자</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;" colspan="2">시설코드</th>
	 			</tr>
	 			<tr height="25">
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">회계연도</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">고지번호</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">적용요율</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">징수톤</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">고지일자</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">할인율(%)</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">면제금액</th>
	 			</tr>
	 			<tr height="25">
	  				<td style="font-size:12px;" colspan="2">요금종류 : </td>
	  				<td style="font-size:12px;" colspan="3">요금종류명 : </td>
	  				<td style="text-align:right;font-size:12px;" colspan="2">총면제금액 : </td>
	 			</tr>
	  		</thead>
	  		<tbody>
			<tr height="25">
				<td style="text-align:center;font-size:12px;border:1px #000000 solid;" colspan="7">자료가 존재 하지 않습니다.</td>
			</tr>
	</c:if>
    <c:forEach var="result" items="${resultList }" varStatus="resultStatus">
           			
           			<c:if test="${pageSkip == false}">
    					<c:if test="${(result.feeTp != preFeeTp) }">
    						<c:set var="pageSkip" value="true"/>
    					</c:if>
    				</c:if>
           			<c:if test="${checkIndex%pagePerCount==0 or (pageSkip == true) }"> <% /*  페이지 당 출력 갯수 */ %>
           				<c:if test="${resultStatus.index!=0}">	<% /*  페이지 구분*/ %>
			        		</tbody>
			        		</table>
	        		        </div>
    						</div>
						    <div class="page">
						        <div class="subpage ygpa_report" >
           				</c:if>
        				<!--  헤더 반복  -->
        				<c:set var="checkIndex" value="0" />
        				<c:set var="pageSkip" value="false" />
        				<c:set var="preFeeTp" value='${result.feeTp}'/>
        				<table style="width:100%;">
							<tr height="15">
								<td style="width:40px;font-size:12px;">관할청 : </td>
								<td style="width:380px;text-align:left;font-size:12px;"><c:out value="${prtAtCode }" /> [ <c:out value="${prtKorNm }" /> ]</td>
								<td style="text-align:right;font-size:12px;">고지기간 : <c:out value="${frDt }" /> ~ <c:out value="${toDt }" /></td>
							</tr>
						</table>
       		        	<table class="rpr_main_table">
			        		<thead>
			        			<tr height="25">
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">업체코드</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">호출부호</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">선박명</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">입항연도</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">입출항일자</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;" colspan="2">시설코드</th>
					 			</tr>
					 			<tr height="25">
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">회계연도</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">고지번호</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">적용요율</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">징수톤</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">고지일자</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">할인율(%)</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">면제금액</th>
					 			</tr>
					 			<tr height="25">
					  				<td colspan="2" style="border-right: hidden;font-size:12px;">요금종류 : <c:out value="${result.feeTp }" /></td>
					  				<td colspan="3" style="border-right: hidden;font-size:12px;">요금종류명 : <c:out value="${result.feeTpNm }" /></td>
					  				<td style="text-align:right;font-size:12px;border-bottom:1px #000000 solid;" colspan="2">총면제금액 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumExmpAmnt }" /></td>
					 			</tr>
			        		</thead>
			        		<tbody>
        			</c:if>
        			<c:set var="checkIndex" value="${checkIndex+1}" />
        			<tr height="25">
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.exmpAgentCode }${preFeeTp }${result.feeTp }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.callLetter }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.vsslNm }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.yr }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.ioDt }" /></td>
        				<td colspan="2"><c:out value="${result.facilNm }" /></td>
        			</tr>
        			<tr height="25">
        				
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.fiscalYr }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.billNo }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.standardFee }" /></td>
        				<td style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.realTn }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.billDt }" /></td>
        				<td style="text-align: right;font-size:12px;"><c:out value="${result.rateNm }" /></td>
        				<td style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.exmpAmnt }" /></td>
        			</tr>
        		
    </c:forEach>
        		</tbody>
        		<%-- <tfoot>
        			<tr height="25">
        				<th style="text-align:center;vertical-align:middle;font-size:12px;">총계</th>
        				<td style="text-align: right"><c:out value="${sumCnt }" /></td>
        				<th style="text-align:center;vertical-align:middle;font-size:12px;">총 면적</th>
        				<td style="text-align: right"><c:out value="${sumAr }" /> m<sup>2</sup></td>
        				<th style="text-align:center;vertical-align:middle;font-size:12px;">총 사용료</th>
        				<td colspan="2" style="text-align: right"><c:out value="${sumFee }" /> 원</td>
        				<th style="text-align:center;vertical-align:middle;font-size:12px;">총 면제 금액</th>
        				<td colspan="2" style="text-align: right"><c:out value="${sumRdcxptFee }" /> 원</td>
        			</tr>
        		</tfoot> --%>
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