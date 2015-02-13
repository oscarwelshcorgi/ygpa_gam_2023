<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamFcltyQcPrintC.jsp
  * @Description : 토목시설물점검표 인쇄 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2015.2.12    HNJ          최초 생성
  *
  * author HNJ
  * since 2015.2.12
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
  <c:set var="pagePerCount" value="20"/>
  
  <c:if test="${resultCode==0 }">
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
        	<div style="width:100%;height:50px;text-align:center;vertical-align:middle;border-top:1px red;font-size:25px;font-weight:bold;text-decoration:underline;">토목 시설물 점검표</div>
        	<table style="width:100%;">
        		<tr height="40px">
        			<td style="text-align:left;vertical-align:bottom;font-size:15px;">항&nbsp;&nbsp;&nbsp;명 : <c:out value="${detailMngGroup.prtAtCodeNm }" ></c:out></td>
        			<td style="width:150px;text-align:right;vertical-align:bottom;font-size:15px;">점검일 : </td>
        			<c:choose>
        				<c:when test="${empty detailData['wrtDt'] }">
        					<td style="width:150px;text-align:center;vertical-align:bottom;font-size:15px;"><c:out value="　　. 　. 　." /></td>
        				</c:when>
        				<c:otherwise>
        					<td style="width:150px;text-align:center;vertical-align:bottom;font-size:15px;"><c:out value="${fn:replace(detailData['wrtDt'], '-', '. ')}" /></td>
        				</c:otherwise>
        			</c:choose>
        		</tr>
        		<tr height="20px">
        			<td style="text-align:left;vertical-align:bottom;font-size:15px;"> </td>
        			<td style="width:150px;text-align:right;vertical-align:bottom;font-size:15px;">점검자 : </td>
        			<c:choose>
	        			<c:when test="${empty detailData['wrtUsr'] }">
        					<td style="width:150px;text-align:center;vertical-align:bottom;font-size:15px;"><c:out value="　　　　" /> (인)</td>
        				</c:when>
        				<c:otherwise>
        					<td style="width:150px;text-align:center;vertical-align:bottom;font-size:15px;"><c:out value="${detailData['wrtUsr']}" /> (인)</td>
        				</c:otherwise>
        			</c:choose>
        		</tr>
        	</table>
<c:if test="${fn:length(resultList) == 0}">
        	<table style="width:100%;" class="rpr_form_table">
        		<tbody>
        			<tr height="40px">
        				<th style="width:40px;text-align:center;vertical-align:middle;font-size:13px;">시 설 명</th>
        				<td style="width:100px;text-align:center;font-size:13px;"><c:out value="${detailMngGroup.fcltsMngGroupNm }" /></td>
        				<th colspan="2" style="text-align:center;vertical-align:middle;font-size:13px;">시설물소재지</th>
        				<td colspan="2" style="text-align:center;font-size:13px;"><c:out value="${detailMngGroup.loc }" /></td>
        			</tr>
        			<tr height="40px">
        				<th style="text-align:center;vertical-align:middle;font-size:13px;">시설개요</th>
        				<td style="text-align:center;font-size:13px;"></td>
        				<th colspan="3" style="text-align:center;vertical-align:middle;font-size:13px;">종별/상태등급</th>
        				<td style="text-align:center;font-size:13px;"> <c:out value="${detailMngGroup.fcltsGbnNm }" /> / <c:out value="${detailData.sttusEvlLvlNm }" /> </td>
        			</tr>
        			<tr height="40px">
        				<th style="text-align:center;vertical-align:middle;font-size:13px;">준공년도</th>
        				<td style="text-align:center;font-size:13px;"><c:out value="${detailMngGroup.bldYear }"/></td>
        				<th colspan="3" style="text-align:center;vertical-align:middle;font-size:13px;">하 자 만 료 일</th>
        				<c:choose>
        					<c:when test="${empty detailMngGroup.flawEndDt}">
        						<td style="text-align:center;font-size:13px;">　　　　년 　　월 　　일 </td>
        					</c:when>
        					<c:otherwise>
        						<td style="text-align:center;font-size:13px;"><c:out value="${detailMngGroup.flawEndDtYear }"/>년 <c:out value="${detailMngGroup.flawEndDtMonth }"/>월 <c:out value="${detailMngGroup.flawEndDtDay }"/>일 </td>
        					</c:otherwise>
        				</c:choose>
        			</tr>
        			<tr height="40px">
        				<th style="text-align:center;vertical-align:middle;font-size:13px;">시설규모</th>
        				<td style="text-align:center;font-size:13px;"></td>
        				<th colspan="3" style="text-align:center;vertical-align:middle;font-size:13px;">시&nbsp;&nbsp;공&nbsp;&nbsp;회&nbsp;&nbsp;사</th>
        				<td style="text-align:center;font-size:13px;"></td>
        			</tr>
        			<tr height="40px">
        				<th style="text-align:center;vertical-align:middle;font-size:13px;">점검항목</th>
        				<th colspan="2" style="text-align:center;vertical-align:middle;font-size:13px;">점검세부항목</th>
        				<th colspan="3" style="text-align:center;vertical-align:middle;font-size:13px;">점 검 내 용</th>
        			</tr>
        			<tr height="40px">
        				<td colspan="6" style="text-align:center;font-size:13px;">자료가 존재 하지 않습니다.</td>
        			</tr>
</c:if>

<c:forEach var="result" items="${resultList }"  varStatus="resultStatus" >	
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
        	<table style="width:100%;" class="rpr_form_table">
        		<tbody>
        			<tr height="40px">
        				<th style="width:40px;text-align:center;vertical-align:middle;font-size:13px;">시 설 명</th>
        				<td style="width:100px;text-align:center;font-size:13px;"><c:out value="${detailMngGroup.fcltsMngGroupNm }" /></td>
        				<th colspan="2" style="text-align:center;vertical-align:middle;font-size:13px;">시설물소재지</th>
        				<td colspan="2" style="text-align:center;font-size:13px;"><c:out value="${detailMngGroup.loc }" /></td>
        			</tr>
        			<tr height="40px">
        				<th style="text-align:center;vertical-align:middle;font-size:13px;">시설개요</th>
        				<td style="text-align:center;font-size:13px;"></td>
        				<th colspan="3" style="text-align:center;vertical-align:middle;font-size:13px;">종별/상태등급</th>
        				<td style="text-align:center;font-size:13px;"> <c:out value="${detailMngGroup.fcltsGbnNm }" /> / <c:out value="${detailData.sttusEvlLvlNm }" /> </td>
        			</tr>
        			<tr height="40px">
        				<th style="text-align:center;vertical-align:middle;font-size:13px;">준공년도</th>
        				<td style="text-align:center;font-size:13px;"><c:out value="${detailMngGroup.bldYear }" ></c:out></td>
        				<th colspan="3" style="text-align:center;vertical-align:middle;font-size:13px;">하 자 만 료 일</th>
        				<c:choose>
        					<c:when test="${empty detailMngGroup.flawEndDt}">
        						<td style="text-align:center;font-size:13px;">　　　　년 　　월 　　일 </td>
        					</c:when>
        					<c:otherwise>
        						<td style="text-align:center;font-size:13px;"><c:out value="${detailMngGroup.flawEndDtYear }"/>년 <c:out value="${detailMngGroup.flawEndDtMonth }"/>월 <c:out value="${detailMngGroup.flawEndDtDay }"/>일 </td>
        					</c:otherwise>
        				</c:choose>
        			</tr>
        			<tr height="40px">
        				<th style="text-align:center;vertical-align:middle;font-size:13px;">시설규모</th>
        				<td style="text-align:center;font-size:13px;"></td>
        				<th colspan="3" style="text-align:center;vertical-align:middle;font-size:13px;">시&nbsp;&nbsp;공&nbsp;&nbsp;회&nbsp;&nbsp;사</th>
        				<td style="text-align:center;font-size:13px;"></td>
        			</tr>
        			<tr height="40px">
        				<th style="text-align:center;vertical-align:middle;font-size:13px;">점검항목</th>
        				<th colspan="2" style="text-align:center;vertical-align:middle;font-size:13px;">점검세부항목</th>
        				<th colspan="3" style="text-align:center;vertical-align:middle;font-size:13px;">점 검 내 용</th>
        			</tr>
        </c:if>
        			<tr height="25px">
        <c:choose>
			<c:when test="${result.qcItemUpperNm != qcItemUpperNm or resultStatus.index==0 }">
        				<td rowspan="<c:out value="${result.qcItemUpperCdCount }" />" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.qcItemUpperNm }" ></c:out></td>
        				<td colspan="2" style="text-align:left;font-size:13px;border-bottom:0;">O <c:out value="${result.qcItemNm }" ></c:out></td>
        				<td rowspan="<c:out value="${result.qcItemUpperCdCount }" />" colspan="3"  style="text-align:center;font-size:13px;border-bottom:0;"><c:out value="${result.qcGroupInspResult }" ></c:out></td>
        	</c:when>
			<c:otherwise>
        				<td colspan="2" style="text-align:left;font-size:13px;border-top:0;border-bottom:0;">O <c:out value="${result.qcItemNm }" ></c:out></td>
			</c:otherwise>
		</c:choose>
        			</tr>
        <c:set var="qcItemUpperNm" value="${result.qcItemUpperNm }" />
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