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
		<style type="text/css">
			h1.title {
				width:100%;
				text-align: center;
				border: none;
				font-family: 굴림체;
				font-size: 30;
				font-weight: bold;
			}

			table.userGrid {
				width: 100%;
				border:3px double;
			}

			table.userGrid tbody tr td {
				height: 30;
				text-align: center;
				border: black 1px solid;
				vertical-align: middle;
				padding: 3;
				font-size: 16;
			}

			table.userGrid tbody tr td.number {
				height: 30;
				text-align: right;
				border: black 1px solid;
				vertical-align: middle;
				padding: 3;
				font-size: 16;
			}

			table.userGrid tbody tr th {
				height: 30;
				text-align: center;
				border: black 1px solid;
				border-bottom: 3px double;
				vertical-align: middle;
				padding: 3;
				font-size: 16;
			}

			p.notice {
				width: 500;
				height: 30;
				margin: 30 30 30 0;
				font-size: 16;
			}

			p.bankinfo {
				margin-left: 180;
				margin-bottom: 10;
				font-size: 16;
			}

			p.notice_date {
				width: 100%;
				text-align: center;
				margin-top: 30;
				font-size: 16;
			}

			p.bottom_title {
				width: 100%;
				text-align: right;
				font-weight: bolder;
				font-size: large;
				margin-top: 60;
			}

		</style>
		<script src="<c:url value='/js/jquery-1.10.2.min.js'/>"></script>
		<script src="<c:url value='/js/jquery-migrate-1.2.1.min.js'/>"></script>
		<script src="<c:url value='/js/jquery-ui.min.js'/>"></script>
		<script>
			$(window).load(function() {
				$('#printButton').button().click(function(){
					window.print();
				});
			});
		</script>
	</head>
	<body>
		<c:if test="${resultCode==0 }">
			<a id="printButton" href="#">인쇄</a>
			<div class="book">
				<div class="page">
					<div class="subpage ygpa_report" >
						<h1 class="title"><c:out value="${result.mngMt }" /> 임대료/관리비 내역서</h1>
						<br />
						<br />
						<table class="userGrid">
							<tbody>
								<tr>
									<td>상    호</td>
									<td style="font-weight: bolder;"><c:out value="${result.entrpsNm }" /></td>
									<td>임대면적</td>
									<td class="number"><c:out value="${result.usageAr }" /></td>
								</tr>
							</tbody>
						</table>
						<br />
						<br />
						<br />
						<table class="userGrid">
							<tbody>
								<tr>
									<th>구    분</th>
									<th>공급가액</th>
									<th>부 가 세</th>
									<th>합    계</th>
									<th>비    고</th>
								</tr>
								<tr>
									<td>임 대 료</td>
									<td class="number"><c:out value="${result.rentFee }" /></td>
									<td class="number"><c:out value="${result.rentVat }" /></td>
									<td class="number"><c:out value="${result.rentAmt }" /></td>
									<td><c:out value="${result.rentRm }" /></td>
								</tr>
								<tr>
									<td>관 리 비</td>
									<td class="number"><c:out value="${result.mngFee }" /></td>
									<td class="number"><c:out value="${result.mngVat }" /></td>
									<td class="number"><c:out value="${result.mngAmt }" /></td>
									<td><c:out value="${result.mngRm }" /></td>
								</tr>
								<tr>
									<td>연 체 료</td>
									<td class="number"><c:out value="${result.arrrgFee }" /></td>
									<td class="number"><c:out value="${result.arrrgVat }" /></td>
									<td class="number"><c:out value="${result.arrrgAmt }" /></td>
									<td><c:out value="${result.arrrgRm }" /></td>
								</tr>
								<tr>
									<td>납부금액</td>
									<td class="number"><c:out value="${result.nticFee }" /></td>
									<td class="number"><c:out value="${result.nticVat }" /></td>
									<td class="number"><c:out value="${result.nticAmt }" /></td>
									<td><c:out value="${result.nticRm }" /></td>
								</tr>
							</tbody>
						</table>
						<br />
						<br />
						<table class="userGrid">
							<tbody>
								<tr>
									<th>항    목</th>
									<th>공급가액</th>
									<th>부 가 세</th>
									<th>합    계</th>
									<th>비    고</th>
								</tr>
								<tr>
									<td>시설관리비</td>
									<td class="number"><c:out value="${result.fcltyMngFee }" /></td>
									<td rowspan="4" class="number"><c:out value="${result.nticVat }" /></td>
									<td rowspan="4" class="number"><c:out value="${result.nticAmt }" /></td>
									<td rowspan="4"><c:out value="${result.mngFeeRm }" /></td>
								</tr>
								<tr>
									<td>전기사용료</td>
									<td class="number"><c:out value="${result.elctyFee }" /></td>
								</tr>
								<tr>
									<td>상하수도비</td>
									<td class="number"><c:out value="${result.waterFee }" /></td>
								</tr>
								<tr>
									<td>냉난방비</td>
									<td class="number"><c:out value="${result.gasFee }" /></td>
								</tr>
								<tr>
									<th> </th>
									<td> </td>
									<td> </td>
									<td> </td>
									<td> </td>
								</tr>
							</tbody>
						</table>
						<p class="notice"><c:out value="${result.mngMt }" />분 임대료 및 관리비를 <c:out value="${result.payTmlmt }" />까지 <br/>아래의 계좌로 입금하여 주시기 바랍니다.</p>
						<p class="bankinfo">은 행 명 : <c:out value="${result.bankNm }" /></p>
						<p class="bankinfo">계좌번호 : <c:out value="${result.bankAccountNo }" /></p>
						<p class="bankinfo">예 금 주 : <c:out value="${result.bankOwnerNm }" /></p>
						<p class="notice_date"><c:out value="${result.nticDt }" /></p>
						<p class="bottom_title">여수광양항만공사               (직인생략)</p>
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
