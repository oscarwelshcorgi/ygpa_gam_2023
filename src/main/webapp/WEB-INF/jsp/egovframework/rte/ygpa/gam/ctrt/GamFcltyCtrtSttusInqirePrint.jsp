<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamFcltyCtrtSttusInqirePrint.jsp
  * @Description : 인쇄 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.11.04  HNJ          최초 생성
  *
  * author HNJ
  * since 2014.11.04
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>

<html lang="ko" xml:lang="ko">
	<head>
		<title>여수광양항만공사 - GIS기반 자산관리 시스템</title>
		<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
		<meta charset="utf-8">
		<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/reset.css' />"/> 
		<link rel="stylesheet" href="<c:url value='/css/demo/jquery-ui-1.10.4.custom.css' />" />
		<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/ygpa_report_l.css' />" />
		<script src="<c:url value='/js/jquery-1.10.2.min.js' />"></script>
		<script src="<c:url value='/js/jquery-migrate-1.2.1.min.js' />"></script>
		<script src="<c:url value='/js/jquery-ui.min.js' />"></script>
		
		<script>
			$( window ).load(function() {
				$('#printButton').button().click(function(){  
					window.print();
				});
			});
		</script>
 	</head>
 	<body>
		<c:set var="pagePerCount" value="7"/>
		<c:if test="${resultCode==0 }">
			<a id="printButton" href="#">인쇄</a>
			<div class="book">
				<c:if test="${fn:length(resultList) == 0}">
					<div class="page">
						<div class="subpage ygpa_report" >
							<h1 class="ygpa_report_h1">시설물 계약이력 조회</h1>
							<table class="rpr_main_table">
								<thead>
									<tr height="25">
										<th style="width:30px;text-align:center;vertical-align:middle;font-size:12px;" rowSpan="2">NO</th>
										<th style="width:50px;text-align:center;vertical-align:middle;font-size:12px;" rowSpan="2">거래관계</th>
										<th style="width:110px;text-align:center;vertical-align:middle;font-size:12px;">기업명</th>
										<th style="width:95px;text-align:center;vertical-align:middle;font-size:12px;">사업자번호</th>
										<th style="text-align:center;vertical-align:middle;font-size:12px;" colSpan="2">거래금액(원)</th>
										<th style="width:95px;text-align:center;vertical-align:middle;font-size:12px;">대표자</th>
										<th style="width:95px;text-align:center;vertical-align:middle;font-size:12px;">직위</th>
										<th style="width:95px;text-align:center;vertical-align:middle;font-size:12px;">휴대폰</th>
										<th style="width:150px;text-align:center;vertical-align:middle;font-size:12px;">이메일</th>
									</tr>
									<tr height="25">
										<th style="width:110px;text-align:center;vertical-align:middle;font-size:12px;">업종</th>
										<th style="width:95px;text-align:center;vertical-align:middle;font-size:12px;">주요품목</th>
										<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;border:1px #000000 solid;">${sPrevCtrtYr }년</th>
										<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;">${sCtrtYr }년</th>
										<th style="width:95px;text-align:center;vertical-align:middle;font-size:12px;">담당자</th>
										<th style="width:95px;text-align:center;vertical-align:middle;font-size:12px;">전화번호</th>
										<th style="width:95px;text-align:center;vertical-align:middle;font-size:12px;">팩스</th>
										<th style="width:150px;text-align:center;vertical-align:middle;font-size:12px;">사업장주소</th>
									</tr>
								</thead>
								<tbody>
									<tr height="25">
										<td style="text-align:center;font-size:12px;" colspan="17">자료가 존재 하지 않습니다.</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</c:if>
				<c:if test="${fn:length(resultList) != 0}">
					<c:forEach var="result" items="${resultList }" varStatus="resultStatus">
						<c:if test="${resultStatus.index%pagePerCount==0 }">
							<div class="page">
								<div class="subpage ygpa_report" >
									<h1 class="ygpa_report_h1">시설물 계약이력 조회</h1>
									<table class="rpr_main_table">
										<thead>
											<tr style="height:25px;">
												<th style="width:30px;text-align:center;vertical-align:middle;font-size:12px;" rowSpan="2">NO</th>
												<th style="width:50px;text-align:center;vertical-align:middle;font-size:12px;" rowSpan="2">거래관계</th>
												<th style="width:110px;text-align:center;vertical-align:middle;font-size:12px;">기업명</th>
												<th style="width:95px;text-align:center;vertical-align:middle;font-size:12px;">사업자번호</th>
												<th style="text-align:center;vertical-align:middle;font-size:12px;" colSpan="2">거래금액(원)</th>
												<th style="width:95px;text-align:center;vertical-align:middle;font-size:12px;">대표자</th>
												<th style="width:95px;text-align:center;vertical-align:middle;font-size:12px;">직위</th>
												<th style="width:95px;text-align:center;vertical-align:middle;font-size:12px;">휴대폰</th>
												<th style="width:150px;text-align:center;vertical-align:middle;font-size:12px;">이메일</th>
											</tr>
											<tr style="height:25px;">
												<th style="width:110px;text-align:center;vertical-align:middle;font-size:12px;border:1px #000000 solid;">업종</th>
												<th style="width:95px;text-align:center;vertical-align:middle;font-size:12px;">주요품목</th>
												<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;border:1px #000000 solid;">${sPrevCtrtYr }년</th>
												<th style="width:100px;text-align:center;vertical-align:middle;font-size:12px;">${sCtrtYr }년</th>
												<th style="width:95px;text-align:center;vertical-align:middle;font-size:12px;">담당자</th>
												<th style="width:95px;text-align:center;vertical-align:middle;font-size:12px;">전화번호</th>
												<th style="width:95px;text-align:center;vertical-align:middle;font-size:12px;">팩스</th>
												<th style="width:150px;text-align:center;vertical-align:middle;font-size:12px;">사업장주소</th>
											</tr>
										</thead>
										<tbody>
						</c:if>
						<tr style="height:25px;">
							<td style="text-align:center;font-size:12px;" rowSpan="2"><c:out value="${result.rnum }" /></td>
							<td style="text-align:center;font-size:12px;" rowSpan="2"><c:out value="${result.dealRelate }" /></td>
							<td style="text-align:left;font-size:12px;"><c:out value="${result.entrpsNm }" /></td>
							<td style="text-align:center;font-size:12px;"><c:out value="${result.bsnmNo }" /></td>
							<td style="text-align: right;font-size:12px;" rowSpan="2"><c:out value="${result.prevCtrtAmt }" /></td>
							<td style="text-align: right;font-size:12px;" rowSpan="2"><c:out value="${result.currCtrtAmt }" /></td>
							<td style="text-align:center;font-size:12px;"><c:out value="${result.rprsntv }" /></td>
							<td style="text-align:center;font-size:12px;"><c:out value="${result.chargerOfcPos }" /></td>
							<td style="text-align:center;font-size:12px;"><c:out value="${result.chargerMoblphonNo }" /></td>
							<td style="text-align:center;font-size:12px;"><c:out value="${result.chargerEmail }" /></td>
						</tr>
						<tr style="height:25px;">
							<td style="text-align:center;font-size:12px;"><c:out value="${result.induty }" /></td>
							<td style="text-align:center;font-size:12px;"><c:out value="${result.stplPrdlst }" /></td>
							<td style="text-align:center;font-size:12px;"><c:out value="${result.charger }" /></td>
							<td style="text-align:center;font-size:12px;"><c:out value="${result.tlphonNo }" /></td>
							<td style="text-align:center;font-size:12px;"><c:out value="${result.faxNo }" /></td>
							<td style="text-align:center;font-size:12px;"><c:out value="${result.roadnmAdres }" /></td>
						</tr>
						<c:if test="${resultStatus.index%pagePerCount==(pagePerCount - 1) }">
										</tbody>
									</table>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</c:if>
			</div>
		</c:if>
		<c:if test="${resultCode!=0 }">
			<h2>서버 오류</h2>
			<p>에러 메시지 : <c:out value="${ resultMessage}"/></p>
		</c:if>
	</body>
</html>