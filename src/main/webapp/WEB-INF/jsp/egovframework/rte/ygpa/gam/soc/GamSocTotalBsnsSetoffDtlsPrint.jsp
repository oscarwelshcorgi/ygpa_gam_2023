<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamSocTotalBsnsSetoffDtlsPrint.jsp
  * @Description : 인쇄 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.10.22  HNJ          최초 생성
  *
  * author HNJ
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
	<link rel="stylesheet" href="/css/ygpa/gam/reset.css">
	<link rel="stylesheet" href="/css/demo/jquery-ui-1.10.4.custom.css">
	<link rel="stylesheet" href="/css/ygpa/gam/ygpa_report_l.css" >

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
  <c:set var="pagePerCount" value="9"/>
  <c:set var="appPrtAtCode" value=""/>
  <c:set var="appPrtAtNm" value=""/>
  <c:set var="prtAtCode" value="" />
  <c:set var="prtAtNm" value="" />
  <c:set var="agentCode" value="" />
  <c:set var="agentName" value="" />
  <c:set var="constNo" value="" />
  <c:set var="pageSkip" value="true" />
  <c:set var="checkIndex" value="0" />
  
  <c:if test="${resultCode==0 }">
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
	    	<h1 class="ygpa_report_h1">총사업비 상계처리내역</h1>

	<c:if test="${fn:length(resultList) == 0}">
		<table class="rpr_form_table">
			<tbody>
				<tr height="25">
					<td colspan="9" style="border-right: hidden;border-left: hidden;border-top: hidden;font-size:12px;">
						처리기간 : <c:out value="${frDt }" /> ~ <c:out value="${toDt }" />
					</td>
				</tr>
				<tr height="25">
					<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;">공사항구</th>
					<td style="width:70px;border-right: hidden;font-size:12px;"></td>
					<td style="width:70px;text-align:center;font-size:12px;"></td>
					<td colSpan="4"></td>
					<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;">공사명</th>
					<td style="text-align:center;font-size:12px;"></td>
				</tr>
				<tr height="25">
					<th style="text-align:center;vertical-align:middle;font-size:12px;">등록항구</th>
					<td style="border-right: hidden;font-size:12px;"></td>
					<td style="text-align:center;font-size:12px;"></td>
					<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;">시공업체</th>
					<td style="width:200px;border-right: hidden;font-size:12px;"></td>
					<td style="text-align:center;font-size:12px;"></td>
					<td colSpan="3"></td>
				</tr>
			</tbody>
		</table>
		<table class="rpr_main_table">
	  		<thead>
	  			<tr height="25">
	  				<th rowSpan="2">선명</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">호출부호</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">입항횟수</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">관리번호</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">상계일자</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">적용요율</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;"> </th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;"rowSpan="2">내외항</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;"rowSpan="2">신고업체</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">신고업체명</th>
	 			</tr>
	 			<tr height="25">
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">처리항구</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">요금종류</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">할인율</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">상계금액(청)</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">상계금액(공사)</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">운임톤</th>
	  				<th style="text-align:center;vertical-align:middle;font-size:12px;">특이사항</th>
	 			</tr>
	  		</thead>
	  		<tbody>
			<tr height="25">
				<td colspan="13">자료가 존재 하지 않습니다.</td>
			</tr>
	</c:if>
    <c:forEach var="result" items="${resultList }" varStatus="resultStatus">
    				<c:if test="${pageSkip == false}">
    					<c:if test="${(result.prtAtCode != prePrtAtCode) or (result.cmplYr != preCmplYr) or (result.constNo != preConstNo) }">
    						<c:set var="pageSkip" value="true"/>
    					</c:if>
    				</c:if>
           			<c:if test="${checkIndex%pagePerCount==0 or pageSkip == true }"> <% /*  페이지 당 출력 갯수 */ %>
           				<c:if test="${resultStatus.index!=0 }">	<% /*  페이지 구분*/ %>
			        		</tbody>
			        		</table>
	        		        </div>
    						</div>
						    <div class="page">
						        <div class="subpage ygpa_report" >
           				</c:if>
        				<!--  헤더 반복  -->
        				<c:set var="checkIndex" value="0" />
        				<c:set var="pageSkip" value="false" />
        				<c:set var="prePrtAtCode" value='${result.prtAtCode}'/>
		        		<c:set var="preCmplYr" value='${result.cmplYr}'/>
		        		<c:set var="preConstNo" value='${result.constNo}'/>
        				<table class="rpr_form_table">
							<tbody>
								<tr height="15">
									<td colspan="9" style="border-right: hidden;border-left: hidden;border-top: hidden;font-size:12px;">
										처리기간 : <c:out value="${frDt }" /> ~ <c:out value="${toDt }" />
									</td>
								</tr>
								<tr height="25">
									<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;">공사항구</th>
									<td style="width:70px;border-right: hidden;font-size:12px;">${result.socPrtAtCode }</td>
									<td style="width:70px;text-align:center;font-size:12px;">${result.socPrtKorNm }</td>
									<td colSpan="4"></td>
									<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;">공사명</th>
									<td style="text-align:center;font-size:12px;">${result.socCnstNm }</td>
								</tr>
								<tr height="25">
									<th style="text-align:center;vertical-align:middle;font-size:12px;">등록항구</th>
									<td style="border-right: hidden;font-size:12px;">${result.prtAtCode }</td>
									<td style="text-align:center;font-size:12px;">${result.prtKorNm }</td>
									<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;">시공업체</th>
									<td style="width:200px;border-right: hidden;font-size:12px;">${result.agentCode }</td>
									<td style="text-align:center;font-size:12px;">${result.agentName }</td>
									<td colSpan="3"></td>
								</tr>
							</tbody>
						</table>
       		        	<table class="rpr_main_table">
			        		<thead>
			        			<tr height="25">
					  				<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;">선명</th>
					  				<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;">호출부호</th>
					  				<th style="width:70px;text-align:center;vertical-align:middle;font-size:12px;">입항횟수</th>
					  				<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;">관리번호</th>
					  				<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;">상계일자</th>
					  				<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;">적용요율</th>
					  				<th style="width:70px;text-align:center;vertical-align:middle;font-size:12px;"> </th>
					  				<th style="width:70px;text-align:center;vertical-align:middle;font-size:12px;">내외항</th>
					  				<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;">신고업체</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">신고업체명</th>
					 			</tr>
					 			<tr height="25">
					 				<th style="text-align:center;vertical-align:middle;font-size:12px;"></th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">처리항구</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">요금종류</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">할인율</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">상계금액(청)</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">상계금액(공사)</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">운임톤</th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;"></th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;"></th>
					  				<th style="text-align:center;vertical-align:middle;font-size:12px;">특이사항</th>
					 			</tr>
			        		</thead>
			        		<tbody>
        			</c:if>
        			<c:set var="checkIndex" value="${checkIndex+1}" />
        			<tr height="25">
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.vsslKey }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.callLetter }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.serNo }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.socNo }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.billDt }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.dcRate }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.inOutNm }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.exmpAgentCode }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.exmpAgentNm }" /></td>
        			</tr>
        			<tr height="25">
        				<td style="text-align:center;font-size:12px;"></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.prtAtCode }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.feeTp }" /></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.dcRate }" /></td>
        				<td style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.mapExmpAmnt }" /></td>
        				<td style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.patExmpAmnt }" /></td>
        				<td style="text-align: right;font-size:12px;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.realTn }" /></td>
        				<td style="text-align:center;font-size:12px;"></td>
        				<td style="text-align:center;font-size:12px;"></td>
        				<td style="text-align:center;font-size:12px;"><c:out value="${result.remark }" /></td>
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