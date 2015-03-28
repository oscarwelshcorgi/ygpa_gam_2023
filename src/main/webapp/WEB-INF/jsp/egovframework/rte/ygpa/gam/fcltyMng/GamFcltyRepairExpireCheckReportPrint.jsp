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
  * @Class Name : GamFcltyRepairExpireCheckReportPrint.jsp
  * @Description : 인쇄 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2015.1.7    HNJ          최초 생성
  *
  * author HNJ
  * since 2015.1.7
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
		
		var now = new Date();
		var year= now.getFullYear();
		var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
		var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
		        
		var currDate = year + '년 ' + mon + '월 ' + day + '일';
		
		$('#today').text(currDate);
	});
	</script>
	</c:if>
  </head>
  <body>
  <c:set var="pagePerCount" value="9"/>
  
  <c:if test="${resultCode==0 }">
  <c:if test="${isHwp eq null }">
  <a id="printButton" href="#">인쇄</a>
  </c:if>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
    		<table style="width:100%;border:1px gray solid;"  width="530">
        		<thead>
        			<tr height="60px">
        				<td></td>
        			</tr>
        			<tr height="100px">
		  				<td style="text-align:center;vertical-align:bottom;font-size:30px;font-weight:bold;text-decoration:underline;">하 자 만 료 검 사 조 서</td>
		 			</tr>
        		</thead>
        		<tbody>
        			<tr height="80px">
        				<td style="vertical-align:middle;font-size:15px;">&nbsp;공 사 명 : <c:out value="${result.flawRprNm }" /></td>
        			</tr>
        			<tr height="20px">
        				<td></td>
        			</tr>
        			<tr height="30px">
        			<!-- TODO: 현재 DB의 값이 없어 미적용 향후 처리 예정 -->
        				<td style="text-align:right;vertical-align:middle;font-size:15px;">2012년 11월 26일 준공(향후적용)</td>
        			</tr>
        			<tr height="30px">
        				<td style="text-align:right;vertical-align:middle;font-size:15px;"><c:out value="${result.ctrtDt }" /> <c:out value="${result.flawRprEntrpsNm }" /> 대표이사 <c:out value="${result.rprsntv }" />과 계약분</td>
        			</tr>
        			<tr height="30px">
        				<td style="text-align:right;vertical-align:middle;font-size:15px;">도급액 : 일금 <c:out value="${result.ctrtAmtKo }" />원정 (₩<fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.ctrtAmt }" />원)</td>
        			</tr>
        			<tr height="30px">
        				<td></td>
        			</tr>
        			<tr height="130px">
        				<td style="vertical-align:top;font-size:15px;">
        					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        					위 공사 하자검사의 명을 받아 <c:out value="${result.flawExamDt }" /> 검사한 결과 <span style="text-decoration:underline;"><c:out value="${result.flawExamResult }" /></span>을 확인함
        				</td>
        			</tr>
        			<tr height="140px">
        				<td style="text-align:center;vertical-align:middle;font-size:15px;"><span id="today"></span></td>
        			</tr>
        			<tr height="60px">
        				<td style="text-align:center;vertical-align:middle;font-size:15px;">
			        		하자검사자 : <span>여수광양항만공사</span>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${result.flawExamUsr }" />&nbsp;&nbsp;<span>도장</span>
        				</td>
        			</tr>
        			<tr height="100px">
        				<td></td>
        			</tr>
        			<tr>
        				<td style="text-align:center;vertical-align:middle;font-size:30px;"><h1>여수광양항만공사 사장 귀하</h1></td>
        			</tr>
        			<tr height="80px">
        				<td></td>
        			</tr>
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