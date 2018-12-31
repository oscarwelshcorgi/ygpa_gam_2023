<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamAssetRentPrintNoticeIssue.jsp
  * @Description : 고지서 출력 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.03.19  eunsungj          최초 생성
  *
  * author eunsungj
  * since 2014.03.14
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
    <style>
		#printButton {
			position:fixed;
			left: 20px;
			top: 10px;
			display:block;
			z-index: 999;
		}

		table {
			width: 100%;
			border: 1px solid #444444;
			border-collapse: collapse;
		}
		th, td {
			border: 1px solid #444444;
			padding: 10px;
			font-size: 11px;
		}

		th{
			text-align: center;
		    background: #efefef;
		    font-size: 12px;
		}
		.print-name{
			font-size: 20px;
		    text-align: center;
		    margin-bottom: 20px;
	    }

		@page {
		    size: landscape;
		/*     margin: 0; */
		}
		@media print {
			#printButton {
				display:none;
			}

			.notprint {
				display:none;
			}

			.page {
			    padding: 0cm;
			    margin: 0cm auto;
			    background: none;
			    page-break-after: always;
			}

		}

     </style>
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
	<c:if test="${resultCode==0 }">
  	<a id="printButton" href="#">인쇄</a>
	    <div class="page">
 		    <div class="print-name">
	    		<B>방충재 유지보수<B>
	    	</div>
<%-- 			<c:forEach var="item" items="${printList }" begin="1" end="100" step="1" varStatus="status" >
 --%>
			 <table>
				<thead>
					<tr>
						<th style="width: 5%;">연도	</th>
						<th style="width: 12%;">시설명</th>
						<th style="width: 8%;">준공일</th>
						<th style="width: 6%;">시공사</th>
						<th style="width: 11%;">재원</th>
						<th style="width: 9%;">규격</th>
						<th style="width: 3%;">수량</th>

						<th style="width: 7%;">공사명 </th>
						<th style="width: 10%;">공사기간 </th>
						<th style="width: 9%;">시공자 </th>
						<th style="width: 7%;">사업<br>책임자 </th>
						<th style="width: 8%;">공사비 </th>
						<th style="width: 8%;">내구연한  </th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${printList }" varStatus="status" >
						<tr>
							<td >${item.enforceYear} </td>
							<td >${item.fcltsMngGroupNm} </td>
							<td >${item.bldDt} </td>
							<td >${item.cnstrtr} </td>
							<td >${item.prtFcltyNm} </td>
							<td >${item.prtFcltyStndrd} </td>
							<td >${item.prtPrtFcltyCnt} </td>

							<td >${item.mntnRprCnstNm} </td>
							<td >${item.mntnRprCnstStartDt}<br>~${item.mntnRprCnstEndDt} </td>
							<td >${item.cnstrtr} </td>
							<td >${item.cnstChargNm} </td>
							<td >${item.mntnRprCnstAmt} </td>
							<td >${item.maturityDt} </td>

						</tr>
					</c:forEach>

				    <c:if test="${fn:length(printList) eq 0}">
				    	<tr>
				    		<td colspan="8" style="text-align: center;">
				    			<h2>인쇄 할 대상이 존재하지 않습니다.</h2>
				    		</td>
				    	</tr>
				    </c:if>

				</tbody>
			 </table>

	    </div>
  </c:if>
  </body>
</html>