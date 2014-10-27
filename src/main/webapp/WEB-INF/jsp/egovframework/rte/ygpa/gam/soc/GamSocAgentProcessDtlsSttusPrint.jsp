<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamSocAgentProcessDtlsSttusPrint.jsp
  * @Description : 투자비보전처리내역현황 인쇄화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.10.23  김종민          최초 생성
  *
  * author 김종민
  * since 2014.10.23
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
  <c:set var="pagePerCount" value="15"/>
  <c:if test="${resultCode==0 }">
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
	    	<h1 class="ygpa_report_h1">투자비보전처리내역현황</h1>

	<c:if test="${fn:length(resultList) == 0}">
		<table class="rpr_main_table">
	  		<thead>
       			<tr>
	  				<th rowspan="2">등록항구</th>
	  				<th>등록항구명</th>
	  				<th>요금종류</th>
	  				<th>횟수</th>
	  				<th>관리번호</th>
	  				<th colspan="2">신고업체</th>
	  				<th>요율금액</th>
	  				<th>상계금액</th>
	  			</tr>
	  			<tr>
	  				<th>호출부호</th>
	  				<th colspan="2">보전누계액</th>
	  				<th>입항횟수</th>
	  				<th>외내항</th>
	  				<th>선석</th>
	  				<th>운임톤</th>
	  				<th>고지일자</th>
      			</tr>
	  		</thead>
	  		<tbody>
			<tr>
				<td colspan="10">자료가 존재 하지 않습니다.</td>
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
						        <div class="subpage ygpa_report" >
           				</c:if>
        				<!--  헤더 반복  -->
       		        	<table class="rpr_main_table">
					  		<thead>
				       			<tr>
					  				<th rowspan="2">등록항구</th>
					  				<th>등록항구명</th>
					  				<th>요금종류</th>
					  				<th>횟수</th>
					  				<th>관리번호</th>
					  				<th colspan="2">신고업체</th>
					  				<th>요율금액</th>
					  				<th>상계금액</th>
					  			</tr>
					  			<tr>
					  				<th>호출부호</th>
					  				<th colspan="2">보전누계액</th>
					  				<th>입항횟수</th>
					  				<th>외내항</th>
					  				<th>선석</th>
					  				<th>운임톤</th>
					  				<th>고지일자</th>
				      			</tr>
					  		</thead>
			        		<tbody>
        			</c:if>        			
        			<tr>
        				<td rowspan="2"><c:out value="${result.appPrtAtCode }" /></td>
        				<td><c:out value="${result.appPrtAtKorNm }" /></td>
        				<td><c:out value="${result.feeTp }" /> &nbsp; <c:out value="${result.feeTpNm }" /></td>
        				<td><c:out value="${result.useNo }" /></td>
        				<td><c:out value="${result.constNo }" /></td>
        				<td colspan="2"><c:out value="${result.exmpAgentNm }" /></td>
        				<td style="text-align: right"><fmt:formatNumber value="${result.standardFee}" type="currency" currencySymbol=""/></td>
        				<td style="text-align: right"><fmt:formatNumber value="${result.exmpAmnt}" type="currency" currencySymbol=""/></td>
					</tr>
					<tr>        				
        				<td><c:out value="${result.callLetter }" /></td>
        				<td style="text-align: right" colspan="2"><fmt:formatNumber value="${result.exmpAcc}" type="currency" currencySymbol=""/></td>
        				<td><c:out value="${result.serNo }" /></td>
        				<td><c:out value="${result.inOut }" /></td>
        				<td><c:out value="${result.facCode }" />-<c:out value="${result.facSubCode }" />&nbsp;<c:out value="${result.facilNm }" /></td>
        				<td><c:out value="${result.realTn }" /></td>
        				<td><c:out value="${result.billDt }" /></td>
        			</tr>
    </c:forEach>
        		</tbody>
        		<tfoot>
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