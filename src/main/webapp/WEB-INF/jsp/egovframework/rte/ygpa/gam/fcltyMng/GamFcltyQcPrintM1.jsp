<%@ page import="java.net.URLDecoder"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamFcltyQcPrintM1.jsp
  * @Description : 항만하역장비 안전 점검표 인쇄
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2015.02.12  김종민          최초 생성
  *
  * author 김종민
  * since 2015.02.12
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>

<%
// 한글 파일 출력일 경우 컨트롤러에 isHwp 에 값을 담아서 리턴한다. fileName 에는 파일명을 넣는다.
if(request.getAttribute("isHwp")!=null){
	String fileName = request.getParameter("filename");
	fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
	response.reset();
	response.setHeader("Content-Disposition", "attachment;filename=\""+fileName + "\"");
	response.setHeader("Content-Description", "JSP Generated Data");
	response.setContentType("application/hwp; charset=UTF-8");
}
// 한글파일에는 css가 먹지 않음.... 안타깝게도... 테이블에 속성정의를 해주어야 함... 귀찮더라도 작업 바람
// table에 border="1" width="530" 을 추가하면 됨
%>
<html lang="ko" xml:lang="ko">
<head>
	<title>여수광양항만공사 - GIS기반 자산관리 시스템</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
	<c:if test="${isHwp eq null }">
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
	</c:if>


</head>
<body>
  <c:set var="pagePerCount" value="24"/>
  <c:set var="qcItemUpperCd" value=""/>
  <c:set var="qcItemUpperUpperCd" value=""/>
  <c:set var="rowspanCount" value="0"/>
  <c:set var="upperItemRowspanCount" value="0" />
  <c:set var="pageSkip" value="true" />
  <c:set var="printRecordCount" value="0" />
  <c:set var="startChar" value="○ "/>

  <c:if test="${resultCode==0 }">
  	<c:if test="${isHwp eq null }">
  <a id="printButton" href="#">인쇄</a>
  </c:if>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
	    	<div style="width:100%;height:50px;text-align:center;vertical-align:middle;border-top:1px red;font-size:25px;font-weight:bold;">항만 하역장비 안전 점검표</div>
		<span>○ 시설명 : <c:out value="${detailData['fcltsMngGroupNm']}"></c:out></span>
	<c:if test="${fn:length(resultList) == 0}">
		<div style="text-align:right">※　정상 : ○　　요주의 : △　　불량 : ×</div>
		<table class="rpr_main_table" border="1" width="530">
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
    				<c:if test="${pageSkip eq false}">
    					<c:if test="${(printRecordCount ge pagePerCount) and (qcItemUpperCd ne result.qcItemUpperCd) }">
    						<c:set var="pageSkip" value="true"/>
							<c:set var="printRecordCount" value="0" />
    					</c:if>
    				</c:if>
           			<c:if test="${pageSkip eq true }"> <% /*  페이지 당 출력 갯수 */ %>
           				<c:set var="pageSkip" value="false"/>
           				<c:if test="${resultStatus.index!=0 }">	<% /*  페이지 구분*/ %>
			        		</tbody>
			        		</table>
	        		        </div>
    						</div>
						    <div class="page">
						        <div class="subpage ygpa_report" >
           				</c:if>
        				<!--  헤더 반복  -->
						<div style="text-align:right">※　정상 : ○　　요주의 : △　　불량 : ×</div>
       		        	<table class="rpr_main_table" border="1" width="530">
			        		<thead>
			        			<tr>
			        				<th width="20%" style="background:#ffffff">장비명</th>
			        				<th colspan="3" style="background:#ffffff">컨 테 이 너 크 레 인</th>
			        			</tr>
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
	        					<c:set var="qcItemUpperCd" value="${result.qcItemUpperCd}" />
	        					<c:set var="upperItemRowspanCount" value="${result.qcItemUpperCdCount }" />
	        					<c:set var="rowspanCount" value="1" />
	        					<c:if test="${qcItemUpperUpperCd ne result.qcItemUpperUpperCd}">
	        						<c:set var="qcItemUpperUpperCd" value="${result.qcItemUpperUpperCd }"></c:set>
	        							<tr>
		        						<td style="border-bottom:0; background:#ffffff; text-align:center;"><c:out value="${result.qcItemUpperUpperNm }" /></td>
		        						<td style="border-bottom:0; background:#ffffff;"></td>
		        						<td style="border-bottom:0; background:#ffffff;"></td>
		        						<td style="border-bottom:0; background:#ffffff;"></td>
		        						</tr>
	        						<c:set var="printRecordCount" value="${printRecordCount+1}" />
	        					</c:if>
	        					<c:choose>
	        						<c:when test="${upperItemRowspanCount gt 1}">
	        							<tr>
		        						<td rowspan="${upperItemRowspanCount}" style="background:#ffffff; text-align:center;"><c:out value="${result.qcItemUpperNm }" /></td>
		        						<td style="border-bottom:0; background:#ffffff;"><c:out value="${startChar}" /><c:out value="${result.qcItemNm }" /></td>
		        						<td style="border-bottom:0; background:#ffffff; text-align:center"><c:out value="${inspResultChk }" /></td>
		        						<td style="border-bottom:0; background:#ffffff;"></td>
		        						</tr>
	        						</c:when>
	        						<c:otherwise>
	        							<tr>
		        						<td style="background:#ffffff; text-align:center;"><c:out value="${result.qcItemUpperNm }" /></td>
		        						<td style="background:#ffffff;"><c:out value="${startChar}" /><c:out value="${result.qcItemNm }" /></td>
		        						<td style="background:#ffffff; text-align:center"><c:out value="${inspResultChk }" /></td>
		        						<td style="background:#ffffff;"></td>
		        						</tr>
	        						</c:otherwise>
	        					</c:choose>
	        				</c:when>
	        				<c:otherwise>
	        					<c:set var="rowspanCount" value="${rowspanCount+1 }" />
	        					<c:choose>
	        						<c:when test="${rowspanCount ge upperItemRowspanCount}">
	        							<tr>
		        						<td style="background:#ffffff;"><c:out value="${startChar}" /><c:out value="${result.qcItemNm }" /></td>
		        						<td style="background:#ffffff; text-align:center"><c:out value="${inspResultChk }" /></td>
		        						<td style="background:#ffffff;"></td>
		        						</tr>
	        						</c:when>
	        						<c:otherwise>
	        							<tr>
		        						<td style="border-bottom:0; background:#ffffff"><c:out value="${startChar}" /><c:out value="${result.qcItemNm }" /></td>
		        						<td style="border-bottom:0; background:#ffffff; text-align:center"><c:out value="${inspResultChk }" /></td>
		        						<td style="border-bottom:0; background:#ffffff"></td>
		        						</tr>
	        						</c:otherwise>
	        					</c:choose>
	        				</c:otherwise>
	        			</c:choose>
	        			<c:set var="printRecordCount" value="${printRecordCount+1}" />
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