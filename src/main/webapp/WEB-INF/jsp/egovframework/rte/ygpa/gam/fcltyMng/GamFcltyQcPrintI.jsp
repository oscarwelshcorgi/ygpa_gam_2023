<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamFcltyQcPrintI.jsp
  * @Description : 정보통신 시설물 점검표 인쇄
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2015.02.09  김종민          최초 생성
  *
  * author 김종민
  * since 2015.02.09
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
  <c:set var="pagePerCount" value="35"/>
  <c:set var="qcItemUpperCd" value=""/>
  <c:set var="upperCount" value="0"/>
  <c:set var="itemUpperCdCount" value="0" />
  
  <c:if test="${resultCode==0 }">
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
	    	<h1 class="ygpa_report_h1">정보통신시설점검표</h1>
		<span>○ 시설명 : <c:out value="${detailData['fcltsMngGroupNm']}"></c:out></span>
		<div style="text-align:right">※　정상 : ○　　요주의 : △　　불량 : ×</div>
	<c:if test="${fn:length(resultList) == 0}">
		<table class="rpr_main_table">
	  		<thead>
	  			<tr>
	  				<th width="20%" style="background:#ffffff">시실명</th>
	  				<th width="50%" style="background:#ffffff">점검사항</th>
	  				<th width="20%" style="background:#ffffff">결과</th>
	  				<th width="10%" style="background:#ffffff">비고</th>
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
					  				<th width="20%" style="background:#ffffff">시실명</th>
					  				<th width="50%" style="background:#ffffff">점검사항</th>
					  				<th width="20%" style="background:#ffffff">결과</th>
					  				<th width="10%" style="background:#ffffff">비고</th>
					  			</tr>
			        		</thead>
			        		<tbody>
        			</c:if>
        				<c:choose>
        					<c:when test="${result.inspResultChk eq 'N'}">
        						<c:set var="inspResultChk" value="○" />
        					</c:when>
        					<c:when test="${result.inspResultChk eq 'W'}">
        						<c:set var="inspResultChk" value="△" />
        					</c:when>
        					<c:when test="${result.inspResultChk eq 'X'}">
        						<c:set var="inspResultChk" value="×" />
        					</c:when>
        					<c:otherwise>
        						<c:set var="inspResultChk" value="" />
        					</c:otherwise>
        				</c:choose>
	        			<c:choose>
	        				<c:when test="${qcItemUpperCd ne result.qcItemUpperCd }">
	        					<c:choose>
	        						<c:when test="${upperCount ge itemUpperCdCount}">
			        					<c:set var="qcItemUpperCd" value="${result.qcItemUpperCd}" />
			        					<c:set var="itemUpperCdCount" value="${result.qcItemUpperCdCount }" />
			        					<c:set var="upperCount" value="1" />
			        					<c:choose>
			        						<c:when test="${itemUpperCdCount gt 1}">
			        							<tr>
				        						<td rowspan="${itemUpperCdCount}" style="background:#ffffff; text-align:center;"><c:out value="${result.qcItemUpperNm }" /></td>
				        						<td style="border-bottom:0; background:#ffffff"><c:out value="${result.qcItemNm }" /></td>
				        						<td style="border-bottom:0; background:#ffffff; text-align:center"><c:out value="${inspResultChk }" /></td>
				        						<td style="border-bottom:0; background:#ffffff"></td>
				        						</tr>
			        						</c:when>
			        						<c:otherwise>
			        							<tr>
				        						<td style="background:#ffffff; text-align:center;"><c:out value="${result.qcItemUpperNm }" /></td>
				        						<td style="background:#ffffff"><c:out value="${result.qcItemNm }" /></td>
				        						<td style="background:#ffffff; text-align:center"><c:out value="${inspResultChk }" /></td>
				        						<td style="background:#ffffff"></td>
				        						</tr>
			        						</c:otherwise>
			        					</c:choose>
	        						</c:when>
	        						<c:otherwise>
	        							<c:forEach begin="${upperCount}" end="${itemUpperCdCount - 1}" step="1" var="num">
		        							<c:set var="upperCount" value="${upperCount+1 }" />
		        							<c:choose>
		        								<c:when test="${num ge itemUpperCdCount - 1}">
		        									<tr>
					        						<td style="background:#ffffff"></td>
					        						<td style="background:#ffffff; text-align:center"></td>
					        						<td style="background:#ffffff"></td>
					        						</tr>
		        								</c:when>
		        								<c:otherwise>
		        									<tr>
					        						<td style="border-bottom:0; background:#ffffff"></td>
					        						<td style="border-bottom:0; background:#ffffff; text-align:center"></td>
					        						<td style="border-bottom:0; background:#ffffff"></td>
					        						</tr>
		        								</c:otherwise>
		        							</c:choose>
	        							</c:forEach>
			        					<c:set var="qcItemUpperCd" value="${result.qcItemUpperCd}" />
			        					<c:set var="itemUpperCdCount" value="${result.qcItemUpperCdCount }" />
			        					<c:set var="upperCount" value="1" />
			        					<c:choose>
			        						<c:when test="${itemUpperCdCount gt 1}">
			        							<tr>
				        						<td rowspan="${itemUpperCdCount}" style="background:#ffffff; text-align:center;"><c:out value="${result.qcItemUpperNm }" /></td>
				        						<td style="border-bottom:0; background:#ffffff"><c:out value="${result.qcItemNm }" /></td>
				        						<td style="border-bottom:0; background:#ffffff; text-align:center"><c:out value="${inspResultChk }" /></td>
				        						<td style="border-bottom:0; background:#ffffff"></td>
				        						</tr>
			        						</c:when>
			        						<c:otherwise>
			        							<tr>
				        						<td style="background:#ffffff; text-align:center;"><c:out value="${result.qcItemUpperNm }" /></td>
				        						<td style="background:#ffffff"><c:out value="${result.qcItemNm }" /></td>
				        						<td style="background:#ffffff; text-align:center"><c:out value="${inspResultChk }" /></td>
				        						<td style="background:#ffffff"></td>
				        						</tr>
			        						</c:otherwise>
			        					</c:choose>
	        						</c:otherwise>
	        					</c:choose>
	        				</c:when>
	        				<c:otherwise>
	        					<c:set var="upperCount" value="${upperCount+1 }" />
	        					<c:choose>
	        						<c:when test="${upperCount ge result.qcItemUpperCdCount}">
	        							<tr>
		        						<td style="background:#ffffff;"><c:out value="${result.qcItemNm }" /></td>
		        						<td style="background:#ffffff; text-align:center"><c:out value="${inspResultChk }" /></td>
		        						<td style="background:#ffffff;"></td>
		        						</tr>
	        						</c:when>
	        						<c:otherwise>
	        							<tr>
		        						<td style="border-bottom:0; background:#ffffff"><c:out value="${result.qcItemNm }" /></td>
		        						<td style="border-bottom:0; background:#ffffff; text-align:center"><c:out value="${inspResultChk }" /></td>
		        						<td style="border-bottom:0; background:#ffffff"></td>
		        						</tr>
	        						</c:otherwise>
	        					</c:choose>
	        				</c:otherwise>
	        			</c:choose>
    </c:forEach>
        		</tbody>
        		<tfoot>
        		</tfoot>
        	</table>
        	<div style="height:50px;"></div>
	        <c:choose>
	        	<c:when test="${empty detailData['wrtDt'] }">
					<div style="text-align:right">점검일자 : <c:out value="　　. 　. 　." /></div>	        		
	        	</c:when>
	        	<c:otherwise>
	        		<div style="text-align:right">점검일자 : <c:out value="${fn:replace(detailData['wrtDt'], '-', '. ')}" /></div>
	        	</c:otherwise>
	        </c:choose>
	        <c:choose>
	        	<c:when test="${empty detailData['wrtUsr'] }">
					<div style="text-align:right">점검자 : <c:out value="　　　　" /> (인)</div>	        		
	        	</c:when>
	        	<c:otherwise>
	        		<div style="text-align:right">점검자 : <c:out value="${detailData['wrtUsr']}" /> (인)</div>
	        	</c:otherwise>
	        </c:choose>
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