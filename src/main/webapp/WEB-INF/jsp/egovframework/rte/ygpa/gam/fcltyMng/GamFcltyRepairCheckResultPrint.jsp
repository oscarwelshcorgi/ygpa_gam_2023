<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamFcltyRepairCheckResultPrint.jsp
  * @Description : 인쇄 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2015.1.7    HNJ          최초 생성
  *
  * author HNJ
  * since 2015.1.7
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
  <c:set var="pagePerCount" value="9"/>
  
  <c:if test="${resultCode==0 }">
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
	    	<h1 class="ygpa_report_h1">하자검사 결과(장비, 설비)</h1>

	<c:if test="${fn:length(resultList) == 0}">
		<table class="rpr_main_table">
	  		<thead>
	  			<tr height="60">
	  				<th style="text-align:center;vertical-align:middle;font-size:15px;">공사명.</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:15px;">시공사</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:15px;">하자검사결과</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:15px;">비고</th>
	 			</tr>
	  		</thead>
	  		<tbody>
			<tr>
				<td colspan="4">자료가 존재 하지 않습니다.</td>
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
			        			<tr height="60">
					  				<th style="text-align:center;vertical-align:middle;font-size:15px;">공사명</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:15px;">시공사</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:15px;">하자검사결과</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:15px;">비고</th>
					 			</tr>
			        		</thead>
			        		<tbody>
        			</c:if>
        			<tr height="60">
        				<td style="text-align:center;vertical-align:middle;"><c:out value="${result.flawRprNm }" /></td>
        				<td style="text-align:center;vertical-align:middle;"><c:out value="${result.flawRprEntrpsNm }" /></td>
        				<td style="text-align:center;vertical-align:middle;"><c:out value="${result.flawExamResult }" /></td>
        				<td style="text-align:center;vertical-align:middle;"><c:out value="${result.rm }" /></td>
        			</tr>
        		
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