<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamAssetSttusInqirePrint.jsp
  * @Description : 인쇄 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.10.10  eunsungj          최초 생성
  *
  * author eunsungj
  * since 2014.10.10
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
	    	<h1 class="ygpa_report_h1">항만시설 사용 현황 목록</h1>

	<c:if test="${fn:length(resultList) == 0}">
		<table class="rpr_main_table">
	  		<thead>
	  			<tr>
	  				<th>항구분</th>
	  				<th>관리번호</th>
	  				<th>신청업체</th>
	  				<th>신청업체명</th>
	  				<th>자산코드</th>
	  				<th>자산명</th>
	  				<th>사용시작일</th>
	  				<th>사용종료일</th>
	  				<th>사용료</th>
	  				<th>사용면적</th>
	 				</tr>
	  		</thead>
	  		<tbody>
			<tr>
				<td colspan="10">자료가 존재 하지 않습니다.</td>
			</tr>
	</c:if>
    <c:forEach var="result" items="${resultList }" varStatus="resultStatus">
           			<c:if test="${resultStatus.index%80==0 }"> <% /*  페이지 당 출력 갯수 */ %>
           				<c:if test="${resultStatus.index!=0 }">	<% /*  페이지 구분*/ %>
			        		</tbody>
			        		</table>
	        		        </div>
    						</div>
						    <div class="page">
						        <div class="subpage ygpa_report" >
           				</c:if>
        				<!--  헤더 반복  -->
       		        	<table class="rpr_main_table">
			        		<thead>
			        			<tr>
			        				<th>항구분</th>
			        				<th>관리번호</th>
			        				<th>신청업체</th>
			        				<th>신청업체명</th>
			        				<th>자산코드</th>
			        				<th>자산명</th>
			        				<th>사용시작일</th>
			        				<th>사용종료일</th>
			        				<th>사용료</th>
			        				<th>사용면적</th>
			       				</tr>
			        		</thead>
			        		<tbody>
        			</c:if>
        			<tr>
        				<td><c:out value="${result.prtAtCodeNm }" /></td>
        				<td><c:out value="${result.rentMngNo }" /></td>
        				<td><c:out value="${result.entrpscd }" /></td>
        				<td><c:out value="${result.entrpsNm }" /></td>
        				<td><c:out value="${result.assetsCdStr }" /></td>
        				<td><c:out value="${result.gisAssetsNm }" /></td>
        				<td><c:out value="${result.usagePdFrom }" /></td>
        				<td><c:out value="${result.usagePdTo }" /></td>
        				<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.fee}" /></td>
        				<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.usageAr}" /></td>
        			</tr>
    </c:forEach>
        		</tbody>
        		<tfoot>
        			<tr>
        				<th>총계</th>
        				<td style="text-align: right"><c:out value="${sumCnt }" /><!-- fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumCnt}" /--></td>
        				<th>총 면적</th>
        				<td style="text-align: right"><c:out value="${sumAr }" /><!-- fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumAr}" /--> m<sup>2</sup></td>
        				<th>총 사용료</th>
        				<td colspan="2" style="text-align: right"><c:out value="${sumFee }" /><!-- fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumFee}" /--> 원</td>
        				<th>총 면제 금액</th>
        				<td colspan="2" style="text-align: right"><c:out value="${sumRdcxptFee }" /><!-- fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumRdcxptFee}" /--> 원</td>
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