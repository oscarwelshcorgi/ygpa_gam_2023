<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
/**
 * @Class Name : GamFcltsMngFeeMngPrintReport.jsp
 * @Description : 인쇄 화면
 * @Modification Information
 *
 *   수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.10.22  ACEWOLF          최초 생성
 *
 * author ACEWOLF
 * since 2014.12.12
 *
 * Copyright (C) 2014 by LFIT  All right reserved.
**/
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
			body {
				margin : 0;
				padding : 0;
				font-family : 굴림체;
				font-size : 12px;
			};
			div.d1title {
				width:20%;
				height:25;
				border-right-style : solid;
				text-align : center;
				font-family : 굴림체;
				font-size : 12px;
			};
			div.d2title {
				width:20%;
				height:25;
				border-right-style : solid;
				border-bottom-style : solid;
				text-align : center;
				font-family : 굴림체;
				font-size : 12px;
			};
			div.d3title {
				width:20%;
				height:25;
				border-right-style : solid;
				border-bottom-style : solid;
				text-align : center;
				font-family : 굴림체;
				font-size : 12px;
			};
		</script>
	</head>
	<body>
		<c:if test="${resultCode==0 }">
			<a id="printButton" href="#">인쇄</a>
			<div class="book">
				<div class="page">
					<div class="subpage ygpa_report" >
						<h1 class="ygpa_report_h1"><c:out value="${mngYrMt }" /> 임대료/관리비 내역서</h1>
						<br />
						<br />
						<table class="rpr_form_table" style="border:3px double;">
							<tbody>
								<tr>
									<th style="width:20%; height:25; text-align:center; border-right-style: solid;">상    호</th>
									<td colspan="2" style="width:40%; height:25; text-align:center; border-right-style: solid;"><c:out value="${entrpsNm }" /></td>
									<th style="width:20%; height:25; text-align:center; border-right-style: solid;">임대면적</th>
									<td style="width:40%; height:25; text-align:right;"><c:out value="${usageAr }" /></td>
								</tr>
							</tbody>
						</table>
						<br />
						<br />
						<br />
						<table class="rpr_form_table" style="border:3px double;">
							<tbody>
								<tr style="border-bottom-style:3px double;">
									<th style="width:20%; height:25; text-align:center; border-right-style: solid;">구    분</th>
									<th style="width:20%; height:25; text-align:center; border-right-style: solid;">공급가액</th>
									<th style="width:20%; height:25; text-align:center; border-right-style: solid;">부 가 세</th>
									<th style="width:20%; height:25; text-align:center; border-right-style: solid;">합    계</th>
									<th style="width:20%; height:25; text-align:center;">비    고</th>
								</tr>
								<tr style="border-bottom-style: solid;">
									<th style="width:20%; height:25; text-align:center; border-right-style: solid;">임 대 료</th>
									<td style="width:20%; height:25; text-align:right; border-right-style: solid;"><c:out value="${rentFee }" /></td>
									<td style="width:20%; height:25; text-align:right; border-right-style: solid;"><c:out value="${rentVat }" /></td>
									<td style="width:20%; height:25; text-align:right; border-right-style: solid;"><c:out value="${rentAmt }" /></td>
									<td style="width:20%; height:25; text-align:center;"><c:out value="${rentRm }" /></td>
								</tr>
								<tr style="border-bottom-style: solid;">
									<th style="width:20%; height:25; text-align:center; border-right-style: solid;">관 리 비</th>
									<td style="width:20%; height:25; text-align:right; border-right-style: solid;"><c:out value="${mngFee }" /></td>
									<td style="width:20%; height:25; text-align:right; border-right-style: solid;"><c:out value="${mngVat }" /></td>
									<td style="width:20%; height:25; text-align:right; border-right-style: solid;"><c:out value="${mngAmt }" /></td>
									<td style="width:20%; height:25; text-align:center;"><c:out value="${mngRm }" /></td>
								</tr>
								<tr style="border-bottom-style: solid;">
									<th style="width:20%; height:25; text-align:center; border-right-style: solid;">연 체 료</th>
									<td style="width:20%; height:25; text-align:right; border-right-style: solid;"><c:out value="${arrrgFee }" /></td>
									<td style="width:20%; height:25; text-align:right; border-right-style: solid;"><c:out value="${arrrgVat }" /></td>
									<td style="width:20%; height:25; text-align:right; border-right-style: solid;"><c:out value="${arrrgAmt }" /></td>
									<td style="width:20%; height:25; text-align:center;"><c:out value="${arrrgRm }" /></td>
								</tr>
								<tr>
									<th style="width:20%; height:25; text-align:center; border-right-style: solid;">납부금액</th>
									<td style="width:20%; height:25; text-align:right; border-right-style: solid;"><c:out value="${nticFee }" /></td>
									<td style="width:20%; height:25; text-align:right; border-right-style: solid;"><c:out value="${nticVat }" /></td>
									<td style="width:20%; height:25; text-align:right; border-right-style: solid;"><c:out value="${nticAmt }" /></td>
									<td style="width:20%; height:25; text-align:center;"><c:out value="${nticRm }" /></td>
								</tr>
							</tbody>
						</table>
						<br />
						<br />
						<table class="rpr_form_table" style="border:3px double;">
							<tbody>
								<tr style="border-bottom-style:3px double;">
									<th style="width:20%; height:25; text-align:center; border-right-style: solid;">항    목</th>
									<th style="width:20%; height:25; text-align:center; border-right-style: solid;">공급가액</th>
									<th style="width:20%; height:25; text-align:center; border-right-style: solid;">부 가 세</th>
									<th style="width:20%; height:25; text-align:center; border-right-style: solid;">합    계</th>
									<th style="width:20%; height:25; text-align:center;">비    고</th>
								</tr>
								<tr style="border-bottom-style: solid;">
									<th style="width:20%; height:25; text-align:center; border-right-style: solid;">시설관리비</th>
									<td style="width:20%; height:25; text-align:right; border-right-style: solid;"><c:out value="${fcltyMngFee }" /></td>
									<td rowspan="4" style="width:20%; text-align:right; border-right-style: solid;"><c:out value="${nticVat }" /></td>
									<td rowspan="4" style="width:20%; text-align:right; border-right-style: solid;"><c:out value="${nticAmt }" /></td>
									<td rowspan="4" style="width:20%; text-align:center;"><c:out value="${mngFeeRm }" /></td>
								</tr>
								<tr style="border-bottom-style: solid;">
									<th style="width:20%; height:25; text-align:center; border-right-style: solid;">전기사용료</th>
									<td style="width:20%; height:25; text-align:right; border-right-style: solid;"><c:out value="${elctyFee }" /></td>
								</tr>
								<tr style="border-bottom-style: solid;">
									<th style="width:20%; height:25; text-align:center; border-right-style: solid;">상하수도비</th>
									<td style="width:20%; height:25; text-align:right; border-right-style: solid;"><c:out value="${waterFee }" /></td>
								</tr>
								<tr>
									<th style="width:20%; height:25; text-align:center; border-right-style: solid;">냉난방비</th>
									<td style="width:20%; height:25; text-align:right; border-right-style: solid;"><c:out value="${gasFee }" /></td>
								</tr>
								<tr>
									<th style="width:20%; height:25; text-align:center; border-right-style: solid;"> </th>
									<td style="width:20%; height:25; text-align:right; border-right-style: solid;"> </td>
									<td style="width:20%; height:25; text-align:right; border-right-style: solid;"> </td>
									<td style="width:20%; height:25; text-align:right; border-right-style: solid;"> </td>
									<td style="width:20%; height:25; text-align:center;"> </td>
								</tr>
							</tbody>
						</table>
						<br />
						<br />
						<p style="text-align:center;"><c:out value="${mngYrMt }" />분 임대료 및 관리비를 <c:out value="${payTmlmt }" />까지</p>
						<br />
						<p style="text-align:center;">아래의 계좌로 입금하여 주시기 바랍니다.</p>
						<br />
						<br />
						<p style="text-align:center;">은 행 명 : <c:out value="${bankNm }" /></p>
						<br />
						<p style="text-align:center;">계좌번호 : <c:out value="${bankAccountNo }" /></p>
						<br />
						<p style="text-align:center;">예 금 주 : <c:out value="${bankOwnerNm }" /></p>
						<br />
						<br />
						<p style="text-align:center;"><c:out value="${nticDt }" /></p>
						<br />
						<br />
						<br />
						<h1 class="ygpa_report_h1" align="center">여수광양항만공사               (직인생략)</h1>
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
