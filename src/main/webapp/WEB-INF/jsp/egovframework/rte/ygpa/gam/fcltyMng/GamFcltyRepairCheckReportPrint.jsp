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
  * @Class Name : GamFcltyRepairCheckResultPrint.jsp
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
	<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/reset.css' />" />
	<link rel="stylesheet" href="<c:url value='/css/demo/jquery-ui-1.10.4.custom.css' />" />
	<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/ygpa_report.css' />" />

    <style>
	table.pageBrTbl {
		width: 100%;
		/* 	page-break-after: auto; */
		border: solid black 0.05mm;
		border-spacing: 0;
	}
	
	table.pageBrTbl>tbody>tr {
		/* page-break-inside: avoid;
			page-break-after: auto;
			page-break-before: auto; */
		height: 6cm;
	}
	
	table.pageBrTbl>tbody>tr>td {
		/* page-break-inside: avoid;
			page-break-after: avoid;
			page-break-before: auto; */
		border: 0.3mm gray solid;
		width: 50%;
		padding: 0.5cm;
		vertical-align: middle;
	}
	
	table.pageBrTbl>thead {
		display: table-header-group;
	}
	
	table.pageBrTbl>tfoot {
		display: table-footer-group;
	}
	
	img.tdFull {
		display: block;
		width: 7cm;
		max-height: 100%;
		margin: auto;
	}
	
	.stamp {
		position: absolute;
		left: 16.5cm;
		top: 17.7cm;
		
	}
	
	img.stamp {
		top: 17.5cm;
	}
	table.pageFont{
	font-family:한컴바탕;
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
 <%-- <c:set var="imgFiles" value="${fn:split('FILE_000000000006055.jpg,FILE_000000000006063.png,FILE_000000000006064.jpg,FILE_000000000006088.jpg,FILE_000000000006063.png,FILE_000000000006055.jpg,FILE_000000000006055.jpg,FILE_000000000006055.jpg,FILE_000000000006055.jpg,FILE_000000000006055.jpg,FILE_000000000006055.jpg,FILE_000000000006055.jpg,FILE_000000000006063.png,FILE_000000000006055.jpg,FILE_000000000006055.jpg,FILE_000000000006055.jpg,FILE_000000000006055.jpg,FILE_000000000006055.jpg', ',')}" scope="page" /> --%>
<%--  <c:forEach var="imgList" items="${imgList}">
<c:out value="${imgList.filenmPhysicl}"/> 

</c:forEach> --%>
<a id="printButton" href="#">인쇄</a>
</c:if>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
    		<table class="pageFont" style="width:100%;border:1px gray solid;" border="1" width="530">
        		<thead>
        			<tr height="60px">
        				<td></td>
        			</tr>
        			<tr height="100px">
		  				<td style="text-align:center;vertical-align:top;font-size:30px;font-weight:bold;text-decoration:underline;">하 자 검 사 조 서</td>
		 			</tr>
        		</thead>
        		<tbody>
        			<tr height="80px">
       				<td style="text-align:left;vertical-align:middle;font-weight:bold;font-size:15px;padding-left:15px;">&nbsp;공 사 명 : <c:out value="${result.flawRprNm }" /></td>
        				
        			</tr>
        			<tr height="20px">
        				<td></td>
        			</tr>
        			<tr height="30px">
        			<!-- TODO: 현재 DB의 값이 없어 미적용 향후 처리 예정 -->
        				<td style="text-align:right;vertical-align:middle;font-size:15px;padding-right: 25px;">2012년 11월 26일 준공(향후적용)</td>
        			</tr>
        			<tr height="30px">
        				<td style="text-align:right;vertical-align:middle;font-size:15px;padding-right: 25px;"><c:out value="${result.ctrtDt }" /> <c:out value="${result.flawRprEntrpsNm }" /> 대표이사 <c:out value="${result.rprsntv }" />과 계약분</td>
        			</tr>
        			<tr height="30px">
        				<td style="text-align:right;vertical-align:middle;font-size:15px;padding-right: 15px;">도급액 : 일금 <c:out value="${result.ctrtAmtKo }" />원정 (₩<fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.ctrtAmt }" />원)</td>
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
        			<tr height="60px">
        				<td style="text-align:right;vertical-align:middle;font-size:15px;padding-right:100px;"><span id="today"></span></td>
        			</tr>
        			<tr height="60px">
        				<td style="text-align:right;vertical-align:middle;font-size:15px;padding-right:100px;">
			        		하자검사자 : <c:out value="${result.flawExamUsrNm }" />
			        		<div  class="stamp">(인)</div>
			        		<img class="stamp" style="filter:Alpha(Opacity=50);Opacity:0.5;" src="<c:url value='/cmm/getPfImage.do?physicalFileNm=${charger.signFileNmPhysicl}' />"/>
			       			
			       		</td>
        			</tr>
        			<tr height="100px">
        				<td></td>
        			</tr>
        			<tr>
        				<td style="text-align:center;vertical-align:middle;font-weight:bold;font-size:30px;"><h1>여수광양항만공사 사장 귀하</h1></td>
        			</tr>
        			<tr height="80px">
        				<td></td>
        			</tr>
        		</tbody>
        	</table>
        </div>
        </div>
        <div class="page">	<!--  class="page"> 페이지 누락 됨 -->
                <div class="subpage ygpa_report" >
           <table style="height:20%; width:100%;" border="1" width="530">
           <thead>
        			<tr height="15px">
        				<td></td>
        			</tr>
        			<tr height="15px">
		  				<td style="text-align:center;vertical-align:top;font-size:30px;font-weight:bold;text-decoration:underline;">하　자　내　용</td>
		 			</tr>
        		</thead>
        		<tbody>
        			<tr height="80px">
        				<td style="text-align:center;vertical-align:middle;font-size:15px;">&nbsp;(example: 2013년 광양항 항만시설물 보수보강공사<c:out value="${result.flawRprNm }" />)</td>
        			</tr>
        			<tr height="20px">
        				<td></td>
        	 </table>
           <table style="height:25%; width:100%; border:1px gray solid;" border="1" width="530">
         	 <tr height="10px">
        				<td style="border:1px gray solid;text-align:center;vertical-align:middle;background:linear-gradient(gray,white, gray);">하자내용</td><td style="border:1px gray solid;text-align:center;vertical-align:middle;background:linear-gradient(gray,white, gray);">비 고</td>
		</tr>
			<tr height="30px">
        				<td style="border:1px gray solid;"></td><td style="border:1px gray solid;"></td>
		</tr>
           </table>
           <table style="height:5%; width:100%;" border="1" width="530"><tr height="10px"><td style="font-size:15px;text-align:left;vertical-align:bottom;">○ 사진대지</td></tr></table>
    		<table class="pageBrTbl" border="1" width="530">
				<c:forEach var="imgFile" items="${imgFiles }" varStatus="status" end="3" step="2">
    			<tr>
    				<td>
    				<img class="tdFull" src="<c:url value='/cmm/getPfImage.do?physicalFileNm=${charger.signFileNmPhysicl}' />"/>
					</td>
					<td>
    				<c:if test="${fn:length(imgFiles) gt status.index+1 }">
	    				<img class="tdFull" src="<c:url value='/cmm/getPfImage.do?physicalFileNm=${imgFiles[status.index+1] }' />"/>
					</c:if>
					</td>
    			</tr>
    			</c:forEach>
    	<c:if test="${fn:length(imgFiles) gt 3 }">
    			<!-- 첫페이지는 두줄 만 출력하고 이상인 경우 다음 페이지 출력한다. -->
	    		</table>
	        </div>
    	</div>
        <div class="page">	<!--  class="page"> 페이지 누락 됨 -->
            <div class="subpage ygpa_report" >
	    		<table class="pageBrTbl" border="1" width="530">
	    		<!-- 첫페이지는 두줄 만 출력하고 이상인 경우 다음 페이지 출력한다. 출력한 갯수가 페이지를 벗어나면 위에 페이지 끊기를 추가 한다. -->
				<c:forEach var="imgFile" items="${imgFiles }" varStatus="status" begin="4" step="2">
    			<tr>
    				<td>
    				<img class="tdFull" src="<c:url value='/cmm/getPfImage.do?physicalFileNm=${imgFile }' />"/>
					</td>
					<td>
    				<c:if test="${fn:length(imgFiles) gt status.index+1 }">
	    				<img class="tdFull" src="<c:url value='/cmm/getPfImage.do?physicalFileNm=${imgFiles[status.index+1] }' />"/>
					</c:if>
					</td>
    			</tr>
    			<c:if test="${(status.index-status.begin) % 6 == 0 && !status.first && !status.last }">
    			    			<!-- 첫페이지는 두줄 만 출력하고 이상인 경우 다음 페이지 출력한다. -->
	    		</table>
	        </div>
    	</div>
        <div class="page">	<!--  class="page"> 페이지 누락 됨 -->
            <div class="subpage ygpa_report" >
	    		<table class="pageBrTbl" border="1" width="530">
	    		<!-- 첫페이지는 두줄 만 출력하고 이상인 경우 다음 페이지 출력한다. 출력한 갯수가 페이지를 벗어나면 위에 페이지 끊기를 추가 한다. -->
    			</c:if>
    			</c:forEach>
        </c:if>
        	   </table>
	        </div>
    	</div>
    </div>
</div>
  </c:if>
    <c:if test="${resultCode!=0 }">
    	<h2>서버 오류</h2>
    	<p>에러 메시지 : <c:out value="${ resultMessage}"/></p>
    </c:if>
  </body>
