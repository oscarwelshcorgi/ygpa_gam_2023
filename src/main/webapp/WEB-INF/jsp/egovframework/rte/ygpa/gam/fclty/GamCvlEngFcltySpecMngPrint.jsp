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
		}

		th{
			text-align: center;
		    background: #efefef;
		}


		@page {
		    size: A4;
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
			    width: 21cm;
			    height: 28.7cm;
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
<%-- 			<c:forEach var="item" items="${printList }" begin="1" end="100" step="1" varStatus="status" >
 --%>
			 <table>
				<thead>
					<tr>
						<th>시설물 종류	</th>
						<th>준공일</th>
						<th>시공사</th>
						<th>담당자</th>
						<th>재원</th>
						<th>규격</th>
						<th>수량</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${printList }" varStatus="status" >
						<tr>
							<td >${item.gisPrtFcltyCdNm} </td>
							<td >${item.prtFcltyInstlDt} </td>
							<td >${item.prtFcltyMngEntrpsNm} </td>
							<td >${item.prtPrtFcltyMnger} </td>
							<td >${item.fenderFmt} </td>
							<td >${item.prtFcltyStndrd} </td>
							<td >${item.prtPrtFcltyCnt} </td>
						</tr>
					</c:forEach>

				    <c:if test="${fn:length(printList) eq 0}">
				    	<tr>
				    		<td colspan="7" style="text-align: center;">
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