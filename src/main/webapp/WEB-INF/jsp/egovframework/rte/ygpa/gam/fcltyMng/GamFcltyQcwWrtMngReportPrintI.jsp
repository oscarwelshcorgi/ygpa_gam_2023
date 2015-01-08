<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamFcltyQcwWrtMngReportPrintI.jsp
  * @Description : 점검관리목록(정보통신) 인쇄 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2015.1.08  김종민          최초 생성
  *
  * author 김종민
  * since 2014.1.08
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
	<style type="text/css">
		th {text-align:center;vertical-align:middle;font-size:12px;}
		td {text-align:center;vertical-align:middle;font-size:12px;}
	</style>
</head>
<body>
  <c:set var="pagePerCount" value="10"/>
  <c:if test="${resultCode==0 }">
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
	    	<h1 class="ygpa_report_h1">
	    	<c:out value="${enforceYear}" />&nbsp;<c:out value="${qcSeNm}" />&nbsp; 항만시설물 안전점검</h1>

	<c:if test="${fn:length(resultList) == 0}">
		<table class="rpr_main_table">
	  		<thead>
       			<tr>
	  				<th>구분</th>
	  				<th>시설명</th>
	  				<th>운영사</th>
	  				<th>상태(지적사항)</th>
	  				<th>조치계획</th>
	  				<th>비고</th>
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
					  				<th>구분</th>
					  				<th>시설명</th>
					  				<th>운영사</th>
					  				<th>상태(지적사항)</th>
					  				<th>조치계획</th>
					  				<th>비고</th>
					  			</tr>
			        		</thead>
			        		<tbody>
        			</c:if>        			
        			<tr>
        				<td><c:out value="${result.prtAtCodeNm }" /></td>
        				<td><c:out value="${result.fcltsMngGroupNm }" /></td>
        				<td><c:out value="${result.owner }" /></td>
        				<td><c:out value="${result.sttusEvlLvlNm }" /></td>
        				<td><c:out value="${result.actionCn }" /></td>
        				<td><c:out value="${result.rm }" /></td>
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