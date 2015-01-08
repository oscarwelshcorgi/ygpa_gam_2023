<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamSocPrtFcltyFeeExmpRqestSttusPrint.jsp
  * @Description : 항만시설사용료 면제요청 현황 인쇄 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.10.22  김종민          최초 생성
  *
  * author 김종민
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
  <c:set var="pagePerCount" value="8"/>
  <c:if test="${resultCode==0 }">
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
	    	<h1 class="ygpa_report_h1">항만시설사용료 면제요청 현황</h1>

	<c:if test="${fn:length(resultList) == 0}">
		<table class="rpr_main_table">
	  		<thead>
       			<tr>
	  				<th rowspan="3">관리청</th>
	  				<th>준공년도</th>
	  				<th>공사번호</th>
	  				<th>면제요청청</th>
	  				<th>요청업체</th>
	  				<th>신청횟수</th>
	  				<th>적용요율</th>
	  			</tr>
	  			<tr>
	  				<th>면제요청액</th>
	  				<th>누계액</th>
	  				<th>신청일자</th>
	  				<th>면제기간</th>
	  				<th>사용여부</th>
	  				<th>처리조건</th>
				</tr>
				<tr>	  				
	  				<th colspan="6">비고</th>
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
			        				<td colspan="7">
			        					관할청 : <c:out value="${result.prtAtCode}" /> [ <c:out value="${result.prtAtKorNm}" />] &nbsp;&nbsp;&nbsp;&nbsp; 단위업무 : 비관리청
			        				</td>
			        			</tr>
				       			<tr>
					  				<th rowspan="3">관리청</th>
					  				<th>준공년도</th>
					  				<th>공사번호</th>
					  				<th>면제요청청</th>
					  				<th>요청업체</th>
					  				<th>신청횟수</th>
					  				<th>적용요율</th>
					  			</tr>
					  			<tr>
					  				<th>면제요청액</th>
					  				<th>누계액</th>
					  				<th>신청일자</th>
					  				<th>면제기간</th>
					  				<th>사용여부</th>
					  				<th>처리조건</th>
								</tr>
								<tr>	  				
					  				<th colspan="6">비고</th>
				      			</tr>
			        		</thead>
			        		<tbody>
        			</c:if>        			
        			<tr>
        				<td rowspan="3"><c:out value="${result.prtAtCode }" /></td>
        				<td><c:out value="${result.cmplYr }" /></td>
        				<td><c:out value="${result.constNo }" /></td>
        				<td><c:out value="${result.appPrtAtCode }" /></td>
        				<td><c:out value="${result.appAgentCode }" /> &nbsp; <c:out value="${result.appAgentName}" /></td>
        				<td><c:out value="${result.useNo }" /></td>
        				<td><c:out value="${result.rateGubun }" /></td>
					</tr>
					<tr>        				
        				<td><fmt:formatNumber value="${result.exmpAmnt}" type="currency" currencySymbol=""/></td>
        				<td><fmt:formatNumber value="${result.exmpAcc}" type="currency" currencySymbol=""/></td>
        				<td><c:out value="${result.applDate }" /></td>
        				<td><c:out value="${result.periodFr }" /> ~ <c:out value="${result.periodTo }" /></td>
        				<td><c:out value="${result.useYn }" /></td>
        				<td><c:out value="${result.exmpCond }" /></td>
        			</tr>
        			<tr>
        				<td colspan="6"><c:out value="${result.remark}"/></td>
        			</tr>
    </c:forEach>
        		</tbody>
        		<tfoot>
        			<tr>
        				<td>자료수 </td>
        				<td style="text-align: right"><c:out value="${totalCount }" />건<!-- fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumCnt}" /--></td>
        				<td>총신청액 </td>
        				<td style="text-align: right" colspan="2"><fmt:formatNumber value="${sumExmpAmnt}" type="currency" currencySymbol=""/><!-- fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumAr}" /--></td>
        				<td>총누계액 </td>
        				<td style="text-align: right"><fmt:formatNumber value="${sumExmpAcc}" type="currency" currencySymbol=""/><!-- fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumFee}" /--></td>
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