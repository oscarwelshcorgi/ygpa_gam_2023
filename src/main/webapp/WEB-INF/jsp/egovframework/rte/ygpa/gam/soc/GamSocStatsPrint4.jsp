<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamSocStatsPrint4.jsp
  * @Description : 업체기준 월별 투자비보전 집계 인쇄 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.10.21  김종민          최초 생성
  *
  * author 김종민
  * since 2014.10.21
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
  <c:set var="exmpAgentCode" value="" />
  <c:set var="exmpAgentName" value="" />
  <c:set var="exmpMonth" value="" />
  <c:set var="exmpCntSum" value="0" />
  <c:set var="exmpAmntSum" value="0" />
  <c:set var="exmpAmntPaSum" value="0" />
  <c:set var="pagePerCount" value="28"/>
  <c:set var="printRecordCount" value="0"/>
  <c:set var="pageSkip" value="true" />
  <c:if test="${resultCode==0 }">
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
	    	<h1 class="ygpa_report_h1"><c:out value="업체기준 월별 투자비보전 집계" /></h1>
			<span>업체코드 : ${searchAgentCode}</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span>조회월 : ${searchFr} ~ ${searchTo}</span>
	<c:if test="${fn:length(resultList) == 0}">
		<table class="rpr_main_table">
	  		<thead>
	  			<tr>
	  				<th colspan="8">월</th>	  				
	  			</tr>
	  			<tr>
	  				<th colspan="8">업체</th>
	  			</tr>
	  			<tr>
	  				<th colspan="2">요금종류</th>
	  				<th colspan="2">상계금액(청)</th>
	  				<th colspan="2">상계금액(공사)</th>
	  				<th colspan="2">합계</th>
	 			</tr>
	  		</thead>
	  		<tbody>
			<tr>
				<td colspan="10">자료가 존재 하지 않습니다.</td>
			</tr>
	</c:if>
    <c:forEach var="result" items="${resultList }" varStatus="resultStatus">
    				<c:if test="${pageSkip == false}">
    					<c:if test="${printRecordCount >= pagePerCount }">
    						<c:set var="pageSkip" value="true"/>
							<c:set var="printRecordCount" value="0" />
    					</c:if>
    				</c:if>
           			<c:if test="${pageSkip == true}"> <% /*  페이지 당 출력 갯수 */ %>
           				<c:if test="${resultStatus.index!=0 }">	<% /*  페이지 구분*/ %>
			        		</tbody>
			        		</table>
	        		        </div>
    						</div>
						    <div class="page">
						        <div class="subpage ygpa_report" >
           				</c:if>
        				<c:set var="pageSkip" value="false" />
        				<!--  헤더 반복  -->
       		        	<table class="rpr_main_table">
			        		<thead>
					  			<tr>
					  				<th colspan="8">월</th>
					  			</tr>
					  			<tr>
					  				<th colspan="8">업체</th>
					  			</tr>
					  			<tr>
					  				<th colspan="2">요금종류</th>
					  				<th colspan="2">상계금액(청)</th>
					  				<th colspan="2">상계금액(공사)</th>
					  				<th colspan="2">합계</th>
					 			</tr>
			        		</thead>
			        		<tbody>
        			</c:if>
        			<c:if test="${result.exmpMonth != exmpMonth}">
        				<c:set var="exmpMonth" value="${result.exmpMonth}" />
        				<c:set var="exmpAgentCode" value=""/>
        				<c:set var="exmpAgentName" value=""/>
        				<c:if test="${exmpCntSum != 0}">
        					<tr>
        						<td colspan="2"> 합계 </td>
        						<td style="text-align: right" colspan="2"><fmt:formatNumber value="${exmpAmntSum }" type="currency" currencySymbol=""/></td>
        						<td style="text-align: right" colspan="2"><fmt:formatNumber value="${exmpAmntPaSum }" type="currency" currencySymbol=""/></td>
        						<td style="text-align: right" colspan="2"><fmt:formatNumber value="${ exmpAmntSum + exmpAmntPaSum }" type="currency" currencySymbol=""/></td>
        					</tr>	
							<c:set var="printRecordCount" value="${printRecordCount+1}"/>
							<c:set var="exmpCntSum" value="0" />
							<c:set var="exmpAmntSum" value="0" />
							<c:set var="exmpAmntPaSum" value="0" />
        				</c:if>
        				<c:set var="exmpCntSum" value="0" />
        				<tr>
        					<td style="text-align: center" colspan="8"><c:out value="${exmpMonth}" /></td>
        				</tr>
						<c:set var="printRecordCount" value="${printRecordCount+1}"/>
        			</c:if>
        			<c:if test="${result.exmpAgentCode != exmpAgentCode}">
        				<c:if test="${exmpCntSum != 0}">
        					<tr>
        						<td colspan="2"> 합계 </td>
        						<td style="text-align: right" colspan="2"><fmt:formatNumber value="${exmpAmntSum }" type="currency" currencySymbol=""/></td>
        						<td style="text-align: right" colspan="2"><fmt:formatNumber value="${exmpAmntPaSum }" type="currency" currencySymbol=""/></td>
        						<td style="text-align: right" colspan="2"><fmt:formatNumber value="${ exmpAmntSum + exmpAmntPaSum }" type="currency" currencySymbol=""/></td>
        					</tr>	
							<c:set var="printRecordCount" value="${printRecordCount+1}"/>
							<c:set var="exmpCntSum" value="0" />
							<c:set var="exmpAmntSum" value="0" />
							<c:set var="exmpAmntPaSum" value="0" />
        				</c:if>
        				<c:set var="exmpAgentCode" value="${result.exmpAgentCode}"/>
        				<c:set var="exmpAgentName" value="${result.exmpAgentNm}"/>
        				<tr>
        					<td colspan="2"><c:out value="${exmpAgentCode}" /></td>
        					<td colspan="6"><c:out value="${exmpAgentName}" /></td>
        				</tr>
						<c:set var="printRecordCount" value="${printRecordCount+1}"/>
        			</c:if>
        			<tr>
        				<td><c:out value="${result.feeTp }" /></td>
        				<td><c:out value="${result.feeNm }" /></td>
        				<td style="text-align: right" colspan="2"><fmt:formatNumber value="${result.exmpAmntSum }" type="currency" currencySymbol=""/></td>
        				<td style="text-align: right" colspan="2"><fmt:formatNumber value="${result.exmpAmntPaSum }" type="currency" currencySymbol=""/></td>
        				<td style="text-align: right" colspan="2"><fmt:formatNumber value="${result.exmpAmntTotSum }" type="currency" currencySymbol=""/></td>
        			</tr>
					<c:set var="printRecordCount" value="${printRecordCount+1}"/>
        			<c:set var="exmpCntSum" value="${ exmpCntSum + 1 }" />
					<c:set var="exmpAmntSum" value="${ exmpAmntSum + result.exmpAmntSum }" />
					<c:set var="exmpAmntPaSum" value="${ exmpAmntPaSum + result.exmpAmntPaSum }" />
    </c:forEach>
        					<tr>
        						<td colspan="2"> 합계 </td>
        						<td style="text-align: right" colspan="2"><fmt:formatNumber value="${exmpAmntSum }" type="currency" currencySymbol=""/></td>
        						<td style="text-align: right" colspan="2"><fmt:formatNumber value="${exmpAmntPaSum }" type="currency" currencySymbol=""/></td>
        						<td style="text-align: right" colspan="2"><fmt:formatNumber value="${ exmpAmntSum + exmpAmntPaSum }" type="currency" currencySymbol=""/></td>
        					</tr>	
        		</tbody>
        		<tfoot>
        			<tr>
        				<td>건수 : </td>
        				<td style="text-align: right"><c:out value="${totalCount }" />건 합계<!-- fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumCnt}" /--></td>
        				<td style="text-align: right" colspan="2"><fmt:formatNumber value="${totExmpAmntSum }" type="currency" currencySymbol=""/><!-- fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumAr}" /--></td>
        				<td style="text-align: right" colspan="2"><fmt:formatNumber value="${totExmpAmntPaSum }" type="currency" currencySymbol=""/><!-- fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumFee}" /--></td>
        				<td style="text-align: right" colspan="2"><fmt:formatNumber value="${totExmpAmntTotSum }" type="currency" currencySymbol=""/><!-- fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumRdcxptFee}" /--></td>
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