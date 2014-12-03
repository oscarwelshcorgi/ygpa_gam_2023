<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamSocApplyLgerPrint.jsp
  * @Description : 투자비 보전 신청대장 인쇄 화면
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
  <c:set var="appPrtAtCode" value=""/>
  <c:set var="appPrtAtNm" value=""/>
  <c:set var="prtAtCode" value="" />
  <c:set var="prtAtNm" value="" />
  <c:set var="appAgentCode" value="" />
  <c:set var="appAgentName" value="" />
  <c:set var="constNo" value="" />
  <c:set var="pageSkip" value="true" />
  <c:set var="printRecordCount" value="0" />
  <c:if test="${resultCode==0 }">
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
	    	<h1 class="ygpa_report_h1">투자비보전신청대장</h1>

	<c:if test="${fn:length(resultList) == 0}">
		<table class="rpr_main_table">
	  		<thead>
       			<tr>
	  				<th>요금종류</th>
	  				<th>신청횟수</th>
	  				<th>신청보장액</th>
	  				<th>보전누계액</th>
	  				<th>신청기간(From)</th>
	  				<th>신청기간(To)</th>
	  				<th>적용요율</th>
	  				<th>신고일자</th>
	  				<th>허가일자</th>
	  				<th>적용여부</th>
	  			</tr>
	  			<tr>
	  				<th>처리방법</th>
	  				<th>작업자</th>
	  				<th>작업일자</th>
	  				<th colspan="3">특이사항</th>
	  				<th colspan="4">공사명(다른항만공사일경우)</th>
      			</tr>
	  		</thead>
	  		<tbody>
			<tr>
				<td colspan="10">자료가 존재 하지 않습니다.</td>
			</tr>
	</c:if>
    <c:forEach var="result" items="${resultList }" varStatus="resultStatus">
    				<c:if test="${pageSkip == false}">
    					<c:if test="${(result.appPrtAtCode != appPrtAtCode) or (result.prtAtCode != prtAtCode) or (result.constNo != constNo) or (result.appAgentCode != appAgentCode) }">
    						<c:set var="pageSkip" value="true"/>
    					</c:if>
    					<c:if test="${printRecordCount >= pagePerCount }">
    						<c:set var="pageSkip" value="true"/>
							<c:set var="printRecordCount" value="0" />
    					</c:if>
    				</c:if>
           			<c:if test="${pageSkip == true}"> <% /*  페이지 당 출력 갯수 */ %>
           				<c:if test="${resultStatus.index!=0}">	<% /*  페이지 구분*/ %>
           					
			        		</tbody>
			        		</table>
	        		        </div>
    						</div>
						    <div class="page">
						        <div class="subpage ygpa_report" >
           				</c:if>
        				<!--  헤더 반복  -->
           				<c:set var="pageSkip" value="false" />
           				<c:set var="appPrtAtCode" value="${result.appPrtAtCode}"/>
						<c:set var="appPrtAtNm" value="${result.appPrtAtKorNm}"/>
						<c:set var="prtAtCode" value="${result.prtAtCode}" />
						<c:set var="prtAtNm" value="${result.prtAtKorNm}" />
						<c:set var="appAgentCode" value="${result.appAgentCode}" />
						<c:set var="appAgentName" value="${result.appAgentName}" />
						<c:set var="constNo" value="${result.constNo}" />
						<c:set var="printRecordCount" value="0" />
       		        	<table class="rpr_main_table">
			        		<thead>
			        			<tr>
			        				<td colspan="10">
			        					항구 : <c:out value="${appPrtAtNm}" /> &nbsp;&nbsp;&nbsp;
			        					공사항구 : <c:out value="${prtAtNm}" /> &nbsp;&nbsp;&nbsp;
			        					문서번호 : <c:out value="${constNo}" /> &nbsp;&nbsp; 
			        					시행업체 : <c:out value="${appAgentName}" /> 
			        				</td>
			        			</tr>
				       			<tr>
					  				<th>요금종류</th>
					  				<th>신청횟수</th>
					  				<th>신청보장액</th>
					  				<th>보전누계액</th>
					  				<th>신청기간(From)</th>
					  				<th>신청기간(To)</th>
					  				<th>적용요율</th>
					  				<th>신고일자</th>
					  				<th>허가일자</th>
					  				<th>적용여부</th>
					  			</tr>
					  			<tr>
					  				<th>처리방법</th>
					  				<th>작업자</th>
					  				<th>작업일자</th>
					  				<th colspan="3">특이사항</th>
					  				<th colspan="4">공사명(다른항만공사일경우)</th>
				      			</tr>
			        		</thead>
			        		<tbody>
        			</c:if>        			
        			<tr>
        				<td><c:out value="${result.feeTpNm }" /></td>
        				<td><c:out value="${result.useNo }" /></td>
        				<td><fmt:formatNumber value="${result.exmpAmnt}" type="currency" currencySymbol=""/></td>
        				<td><fmt:formatNumber value="${result.exmpAcc}" type="currency" currencySymbol=""/></td>
        				<td><c:out value="${result.periodFr }" /></td>
        				<td><c:out value="${result.periodTo }" /></td>
        				<td><c:out value="${result.rateGubun }" /></td>
        				<td><c:out value="${result.applDate }" /></td>
        				<td><c:out value="${result.perfDt }" /></td>
        				<td><c:out value="${result.useYn }" /></td>
					</tr>
					<tr>        				
        				<td></td>
        				<td><c:out value="${result.updtUid }" /></td>
        				<td><c:out value="${result.updtDate }" /></td>
        				<td colspan="3"><c:out value="${result.remark }" /></td>
        				<td colspan="4"><c:out value="${result.item }" /></td>
        			</tr>
        			<c:set var="printRecordCount" value="${printRecordCount+1}" />
    </c:forEach>
        		</tbody>
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