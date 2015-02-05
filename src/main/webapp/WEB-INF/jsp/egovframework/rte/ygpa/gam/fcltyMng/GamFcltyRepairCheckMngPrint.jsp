<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamFcltyRepairExpireCheckReportPrint.jsp
  * @Description : 하자검사관리대장 인쇄 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2015.2.5    HNJ          최초 생성
  *
  * author HNJ
  * since 2015.2.5
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
  <c:set var="pagePerCount" value="9"/>
  
  <c:if test="${resultCode==0 }">
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
        	<div style="height:50px;text-align:center;vertical-align:middle;border-top:1px red;font-size:25px;font-weight:bold;text-decoration:underline;">하자검사관리대장</div>
    		<table style="width:100%;" class="rpr_form_table">
        		<tbody>
        			<tr height="40px">
        				<td style="width:80px;text-align:center;vertical-align:middle;font-size:13px;">공 사 명</td>
        				<td colspan="2" style="width:210px;vertical-align:middle;font-size:13px;word-break:break-all;">&nbsp;<c:out value="${result.flawRprNm }" /></td>
        				<td style="width:80px;text-align:center;vertical-align:middle;font-size:13px;">공사위치</td>
        				<td colspan="3" style="vertical-align:middle;font-size:13px;word-break:break-all;">&nbsp;</td>
        			</tr>
        			<tr height="40px">
        				<td style="text-align:center;vertical-align:middle;font-size:13px;">도급회사명</td>
        				<td style="width:150px;vertical-align:middle;font-size:13px;word-break:break-all;">&nbsp;<c:out value="${result.flawRprEntrpsNm }" /></td>
        				<td style="width:60px;text-align:center;vertical-align:middle;font-size:13px;">주소</td>
        				<td colspan="2" style="width:120px;vertical-align:middle;font-size:13px;word-break:break-all;">&nbsp;<c:out value="${result.flawRprEntrpsAdres }" /></td>
        				<td style="width:80px;text-align:center;vertical-align:middle;font-size:13px;">대표자성명</td>
        				<td style="width:100px;vertical-align:middle;font-size:13px;">&nbsp;<c:out value="${result.rprsntvNm }" /></td>
        			</tr>
        			<tr height="40px">
        				<td style="text-align:center;vertical-align:middle;font-size:13px;">보증회사명</td>
        				<td style="vertical-align:middle;font-size:13px;word-break:break-all;">&nbsp;<c:out value="${result.flawExamEntrpsNm1 }" /></td>
        				<td style="text-align:center;vertical-align:middle;font-size:13px;">주소</td>
        				<td colspan="2" style="vertical-align:middle;font-size:13px;word-break:break-all;">&nbsp;<c:out value="${result.flawExamEntrpsAdres1 }" /></td>
        				<td style="text-align:center;vertical-align:middle;font-size:13px;">대표자성명</td>
        				<td style="vertical-align:middle;font-size:13px;">&nbsp;<c:out value="${result.examsntvNm1 }" /></td>
        			</tr>
        			<tr height="40px">
        				<td style="text-align:center;vertical-align:middle;font-size:13px;">보증회사명</td>
        				<td style="vertical-align:middle;font-size:13px;word-break:break-all;">&nbsp;<c:out value="${result.flawExamEntrpsNm2 }" /></td>
        				<td style="text-align:center;vertical-align:middle;font-size:13px;">주소</td>
        				<td colspan="2" style="vertical-align:middle;font-size:13px;word-break:break-all;">&nbsp;<c:out value="${result.flawExamEntrpsAdres2 }" /></td>
        				<td style="text-align:center;vertical-align:middle;font-size:13px;">대표자성명</td>
        				<td style="vertical-align:middle;font-size:13px;">&nbsp;<c:out value="${result.examsntvNm2 }" /></td>
        			</tr>
        			<tr height="40px">
        				<td style="text-align:center;vertical-align:middle;font-size:13px;">계약&nbsp;&nbsp;금액</td>
        				<td colspan="2" style="text-align:right;vertical-align:middle;font-size:13px;">&nbsp;<fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.ctrtAmt }" /></td>
        				<td style="text-align:center;vertical-align:middle;font-size:13px;">정산액</td>
        				<td colspan="3" style="text-align:right;vertical-align:middle;font-size:15px;">&nbsp;</td>
        			</tr>
        			<tr height="40px">
        				<td style="text-align:center;vertical-align:middle;font-size:13px;">계 약 일</td>
        				<td style="vertical-align:middle;font-size:13px;">&nbsp;<c:out value="${result.ctrtDt }" /></td>
        				<td style="text-align:center;vertical-align:middle;font-size:13px;">준공일</td>
        				<td colspan="2" style="vertical-align:middle;font-size:13px;">&nbsp;</td>
        				<td style="text-align:center;vertical-align:middle;font-size:13px;">하자만료일</td>
        				<td style="vertical-align:middle;font-size:13px;">&nbsp;<c:out value="${result.flawDtTo }" /></td>
        			</tr>
        		</tbody>
        	</table>
        	<div style="height:50px;"> </div>
        	<div style="height:50px;text-align:center;vertical-align:bottom;border-top:1px red;font-size:25px;font-weight:bold;text-decoration:underline;">하 자 보 증 내 용</div>
    		<table style="width:100%;" class="rpr_form_table">
        		<tbody>
        			<tr height="30px">
        				<td style="width:60px;text-align:center;vertical-align:middle;font-size:13px;">구분</td>
        				<td style="width:70px;text-align:center;vertical-align:middle;font-size:13px;">검사일</td>
        				<td style="width:70px;text-align:center;vertical-align:middle;font-size:13px;">검사자</td>
        				<td style="width:70px;text-align:center;vertical-align:middle;font-size:13px;">소 속</td>
        				<td style="width:70px;text-align:center;vertical-align:middle;font-size:13px;">직급 및<br>성명</td>
        				<td style="width:180px;text-align:center;vertical-align:middle;font-size:13px;">하자유무 및 조치사항</td>
        				<td style="text-align:center;vertical-align:middle;font-size:13px;">비고</td>
        			</tr>
        			<tr height="200px">
        				<td style="text-align:center;vertical-align:middle;font-size:13px;">준공<br>검사</td>
        				<td style="text-align:center;vertical-align:middle;font-size:13px;"><c:out value="${result.flawExamEntrpsNm2 }" /></td>
        				<td style="text-align:center;vertical-align:middle;font-size:13px;"><c:out value="${result.flawExamEntrpsNm2 }" /></td>
        				<td style="text-align:left;vertical-align:middle;font-size:13px;">&nbsp;<c:out value="${result.flawExamEntrpsNm2 }" /></td>
        				<td style="text-align:center;vertical-align:middle;font-size:13px;"><c:out value="${result.flawExamEntrpsNm2 }" /><br><c:out value="${result.flawExamEntrpsNm2 }" /></td>
        				<td style="text-align:left;vertical-align:middle;font-size:13px;word-break:break-all;">&nbsp;<c:out value="${result.flawExamEntrpsNm2 }" /></td>
        				<td style="text-align:left;vertical-align:middle;font-size:13px;word-break:break-all;">&nbsp;<c:out value="${result.flawExamEntrpsNm2 }" /></td>
        			</tr>
        		<c:forEach var="result" items="${resultList }" varStatus="resultStatus">
        			<c:if test="${resultStatus.index%pagePerCount==0 && resultStatus.index > 6}"> <% /*  페이지 당 출력 갯수 */ %>
           				<c:if test="${resultStatus.index!=0 }">	<% /*  페이지 구분*/ %>
			        		</tbody>
			        		</table>
	        		        </div>
    						</div>
						    <div class="page">
						        <div class="subpage ygpa_report" >
           				</c:if>
        				<!--  헤더 반복  -->
			        			<tr height="30">
					  				<td style="width:60px;text-align:center;vertical-align:middle;font-size:13px;">구분</td>
			        				<td style="width:70px;text-align:center;vertical-align:middle;font-size:13px;">검사일</td>
			        				<td style="width:70px;text-align:center;vertical-align:middle;font-size:13px;">검사자</td>
			        				<td style="width:70px;text-align:center;vertical-align:middle;font-size:13px;">소 속</td>
			        				<td style="width:70px;text-align:center;vertical-align:middle;font-size:13px;">직급 및<br>성명</td>
			        				<td style="width:180px;text-align:center;vertical-align:middle;font-size:13px;">하자유무 및 조치사항</td>
			        				<td style="text-align:center;vertical-align:middle;font-size:13px;">비고</td>
					 			</tr>

        			</c:if>
        			<tr height="40px">
        				<td style="text-align:center;vertical-align:middle;font-size:13px;">하자<br>검사</td>
        				<td style="text-align:center;vertical-align:middle;font-size:13px;"><c:out value="${result.flawExamDt }" /></td>
        				<td style="text-align:center;vertical-align:middle;font-size:13px;"><c:out value="${result.flawExamUsr }" /></td>
        				<td style="text-align:center;vertical-align:middle;font-size:13px;"><c:out value="${result.flawExamUsrDept }" /></td>
        				<td style="text-align:center;vertical-align:middle;font-size:13px;"><c:out value="${result.flawExamUsrNm }" /></td>
        				<td style="text-align:left;vertical-align:middle;font-size:13px;word-break:break-all;">&nbsp;<c:out value="${result.flawEnnc }" /></td>
        				<td style="text-align:left;vertical-align:middle;font-size:13px;word-break:break-all;">&nbsp;<c:out value="${result.flawRprContents }" /></td>
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