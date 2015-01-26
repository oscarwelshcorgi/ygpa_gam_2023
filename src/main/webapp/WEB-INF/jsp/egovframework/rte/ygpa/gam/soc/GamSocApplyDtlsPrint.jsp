<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamSocApplyDtlsPrint.jsp
  * @Description : 투자비보전신청내역 인쇄 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.10.20  김종민          최초 생성
  *
  * author 김종민
  * since 2014.10.20
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
  <c:set var="pagePerCount" value="10"/>
  <c:if test="${resultCode==0 }">
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
	    	<h1 class="ygpa_report_h1">투자비보전신청내역</h1>

	<c:if test="${fn:length(resultList) == 0}">
		<table class="rpr_main_table">
	  		<thead>
       			<tr>
	  				<th rowspan="2">공사항구</th>
	  				<th>공사항구명</th>
	  				<th>시행업체</th>
	  				<th>시행업체명</th>
	  				<th>준공년도</th>
	  				<th>공사번호</th>
	  				<th>요금종류</th>
	  				<th>횟수</th>
	  				<th>요율</th>
	  			</tr>
	  			<tr>
	  				<th>적용</th>
	  				<th>보전기간시작</th>
	  				<th>보전기간종료</th>
	  				<th>신청일자</th>
	  				<th>허가일자</th>
	  				<th>보전신청액</th>
	  				<th>보전누계액</th>
	  				<th>보전 잔액</th>
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
					  				<th rowspan="2">공사항구</th>
					  				<th>공사항구명</th>
					  				<th>시행업체</th>
					  				<th>시행업체명</th>
					  				<th>준공년도</th>
					  				<th>공사번호</th>
					  				<th>요금종류</th>
					  				<th>횟수</th>
					  				<th>요율</th>
					  			</tr>
					  			<tr>
					  				<th>적용</th>
					  				<th>보전기간시작</th>
					  				<th>보전기간종료</th>
					  				<th>신청일자</th>
					  				<th>허가일자</th>
					  				<th>보전신청액</th>
					  				<th>보전누계액</th>
					  				<th>보전 잔액</th>
			       				</tr>
			        		</thead>
			        		<tbody>
        			</c:if>        			
        			<tr>
        				<td rowspan="2"><c:out value="${result.appPrtAtCode }" /></td>
        				<td><c:out value="${result.appPrtAtKorNm }" /></td>
        				<td><c:out value="${result.agentCode }" /></td>
        				<td><c:out value="${result.agentName }" /></td>
        				<td><c:out value="${result.cmplYr }" /></td>
        				<td><c:out value="${result.constNo }" /></td>
        				<td><c:out value="${result.feeTp }" /></td>
        				<td><c:out value="${result.useNo }" /></td>
        				<td><c:out value="${result.rateGubun }" /></td>
					</tr>
					<tr>        				
        				<td><c:out value="${result.useYn }" /></td>
        				<td><c:out value="${result.periodFr }" /></td>
        				<td><c:out value="${result.periodTo }" /></td>
        				<td><c:out value="${result.applyDate }" /></td>
        				<td><c:out value="${result.perfDt }" /></td>
        				<td style="text-align: right"><fmt:formatNumber value="${result.exmpAmnt}" type="currency" currencySymbol=""/></td>
        				<td style="text-align: right"><fmt:formatNumber value="${result.exmpAcc}" type="currency" currencySymbol=""/></td>
        				<td style="text-align: right"><fmt:formatNumber value="${result.exmpRemain}" type="currency" currencySymbol=""/></td>
        			</tr>
    </c:forEach>
        		</tbody>
        		<tfoot>
        			<tr>
        				<td style="border:1px #000000 solid;">자료수 </td>
        				<td style="text-align: right; border:1px #000000 solid;"><c:out value="${totalCount }" />건<!-- fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumCnt}" /--></td>
        				<td style="border:1px #000000 solid;">총신청액 </td>
        				<td style="text-align: right; border:1px #000000 solid;" colspan="2"><fmt:formatNumber value="${sumExmpAmnt}" type="currency" currencySymbol=""/><!-- fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumAr}" /--></td>
        				<td style="border:1px #000000 solid;">총보전액 </td>
        				<td style="text-align: right; border:1px #000000 solid;"><fmt:formatNumber value="${sumExmpAcc}" type="currency" currencySymbol=""/><!-- fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumFee}" /--></td>
        				<td style="border:1px #000000 solid;">총잔액 </td>
        				<td style="text-align: right; border:1px #000000 solid;"><fmt:formatNumber value="${sumExmpRemain }" type="currency" currencySymbol=""/><!-- fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumRdcxptFee}" /--></td>
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