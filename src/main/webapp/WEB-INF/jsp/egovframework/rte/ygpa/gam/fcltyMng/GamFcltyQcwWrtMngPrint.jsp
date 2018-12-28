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
						<th>연도</th>
						<th>관리그룹</th>
						<th>점검일자</th>
						<th>점검관리명 </th>
						<th>점검구분 </th>
						<th>작성자 </th>
						<th>점검내역 </th>
						<th>상태평가 등급 </th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${printList }" varStatus="status" >
						<tr>
							<td >${item.enforceYear} </td>
							<td >${item.fcltsMngGroupNm} </td>
							<td >${item.qcInspDt} </td>
							<td >${item.qcMngNm} </td>
							<td >${item.qcSeNm} </td>
							<td >${item.wrtUsr} </td>
							<td >${item.actionCn} </td>

							<c:choose>
								<c:when test = "${item.sttusEvlLvl eq '0'}">
									<td >양호</td>
								</c:when>
								<c:when test = "${item.sttusEvlLvl eq '1'}">
									<td >보통</td>
								</c:when>
								<c:when test = "${item.sttusEvlLvl eq '2'}">
									<td >불량</td>
								</c:when>
								<c:when test = "${item.sttusEvlLvl eq 'Z'}">
									<td >불명</td>
								</c:when>
								<c:otherwise>
									<td >${item.sttusEvlLvl} </td>
								</c:otherwise>
							</c:choose>
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