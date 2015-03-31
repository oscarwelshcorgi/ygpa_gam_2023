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

	String fileName = request.getParameter("filename");
	fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
	response.reset();
	response.setHeader("Content-Disposition", "attachment;filename=\""+fileName + "\"");
	response.setHeader("Content-Description", "JSP Generated Data");
	response.setHeader("Cache-control","private");
	response.setContentType("application/hwp; charset=UTF-8");

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
	table.pictureGridTbl {
		width: 100%;
		/* 	page-break-after: auto; */
		border: solid black 0.05mm;
		border-spacing: 0;
	}

	table.pictureGridTbl>tbody>tr {
		/* page-break-inside: avoid;
			page-break-after: auto;
			page-break-before: auto; */
		height: 6cm;
	}

	table.pictureGridTbl>tbody>tr.caption {
		/* page-break-inside: avoid;
			page-break-after: auto;
			page-break-before: auto; */
		height: 0.8cm;
	}

	table.pictureGridTbl>tbody>tr>td {
		/* page-break-inside: avoid;
			page-break-after: avoid;
			page-break-before: auto; */
		border: 0.3mm gray solid;
		width: 50%;
		padding: 0.2cm;
		vertical-align: middle;
		border:1px gray solid;
		text-align: center;
	}

	table.pictureGridTbl>thead {
		display: table-header-group;
	}

	table.pictureGridTbl>tfoot {
		display: table-footer-group;
	}

	h1 {
		text-align:center;
		vertical-align:top;
		font-size:30px;
		font-weight:bold;
		text-decoration:underline;
		margin-bottom: 1cm;
	}

	div.title {
		font-family:한컴바탕;
		font-size:24px;
		font-weight: 2px;
	}

	h2 {
		text-align:left;
		vertical-align:middle;
		font-weight:bold;
		font-size:15px;
		padding-left:0.8cm;
	}

	p.dateStr {
		text-align:right;
		vertical-align:middle;
		font-size:15px;
		padding-right: 25px;
	}

	p.amountStr {
		text-align:right;
		vertical-align:middle;
		font-size:15px;
		padding-right: 15px;
	}

	p.contextStr {
		vertical-align:top;
		font-size:15px;
		padding-top: 0.2cm;
		padding-left: 1cm;
	}

	p.inspectDate {
		text-align:right;
		vertical-align:middle;
		font-size:15px;
		padding-right:100px;
	}

	h3 {
		text-align:center;
		vertical-align:middle;
		font-weight:bold;
		font-size:30px;
	}

	img.tdFull {
		display: block;
		width: 6cm;
		max-height: 100%;
		margin: auto;
	}

	.stamp {
		position: absolute;
		  left: 16.5cm;
		  top: 18.2cm;
	}

	img.stamp {
		position: absolute;
		  left: 16.5cm;
		  top: 18.0cm;
		  filter:Alpha(Opacity=50);Opacity:0.5;
	}

	table.reportPage {
		width: 100%;
	}

	table.pageFont{
		font-family:한컴바탕;
		width:100%;
		height:100%;
		border:1px gray solid;
	}

	table.page2Title {
		font-family:한컴바탕;
		width:100%;
		height:20%;
		border:none;
	}

	table.rprReport {
		height:25%; width:100%; border:1px gray solid;
	}

	p.subtitle {
		padding-top: 1cm;
		padding-bottom: 0.2cm;
		font-family:한컴바탕;
		font-size:0.4cm;
	}

	table.rprReport th {
		border:1px gray solid;
		text-align:center;
		vertical-align:middle;
		background:linear-gradient(gray,white, gray);
		height: 1.5cm;
	}

	table.rprReport td {
		border:1px gray solid;
		width:60%;
		padding:5px;
		height: 4cm;
	}


	@media print {
		.stamp {
			top: 17.2cm;

		}

		img.stamp {
			top: 17.5cm;
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
 <%-- <c:forEach var="resultItem" items="${resultList}">
<c:out value="${resultList.filenmPhysicl}"/>

</c:forEach>  --%>

<a id="printButton" href="#">인쇄</a>
</c:if>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
        	<table border="1" class="reportPage" width="530" height="800">
        		<tr>
	        		<td>
       		    		<table class="pageFont">
			        		<thead>
			        			<tr height="60">
			        				<td></td>
			        			</tr>
			        			<tr height="100">
					  				<td><h1><div class="title">하 자 검 사 조 서</div></h1></td>
					 			</tr>
			        		</thead>
			        		<tbody>
			        			<tr height="80">
			       				<td width="530"><div style="font-family:바탕;font-height:26px;font-weight:2px;">공 사 명 : <c:out value="${result.flawRprNm }" /></div></td>

			        			</tr>
			        			<tr height="20">
			        				<td></td>
			        			</tr>
			        			<tr height="30">
			        			<!-- TODO: 현재 DB의 값이 없어 미적용 향후 처리 예정 -->
			        				<td><p class="dateStr">2012년 11월 26일 준공(향후적용)</p></td>
			        			</tr>
			        			<tr height="30">
			        				<td><p class="dateStr"><c:out value="${result.ctrtDt }" /> <c:out value="${result.flawRprEntrpsNm }" /> 대표이사 <c:out value="${result.rprsntv }" />과 계약분</p></td>
			        			</tr>
			        			<tr height="30">
			        				<td><p class="amountStr">도급액 : 일금 <c:out value="${result.ctrtAmtKo }" />원정 (₩<fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.ctrtAmt }" />원)</p></td>
			        			</tr>
			        			<tr height="30">
			        				<td></td>
			        			</tr>
        			<tr height="130px">
        				<td style="vertical-align:top;font-size:15px;">
        					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        					위 공사에 대하여 <c:out value="${result.flawExamDt }" />&nbsp;&nbsp;하자검사한 결과 <span style="text-decoration:underline;">
        			<c:choose>	
        				<c:when test="${result.flawEnnc == 'Y'}">하자있음</c:when>
        				<c:otherwise>하자없음</c:otherwise>
        			</c:choose>
        					</span>을 확인함
        				</td>
        			</tr>
			        			<tr height="60">
			        				<td><span id="today" class="dateStr"></span></td>
			        			</tr>
			        			<tr height="60">
			        				<td>
			        					<p class="inspectDate">
						        		하자검사자 : <c:out value="${result.flawExamUsrNm }" />
						        		<div  class="stamp">(인)</div>
						        		<img class="stamp" src="<c:url value='/cmm/getPfImage.do?physicalFileNm=${charger.signFileNmPhysicl}' />"/>
						        		</p>
						       		</td>
			        			</tr>
			        			<tr height="100">
			        				<td></td>
			        			</tr>
			        			<tr>
			        				<td><h3>여수광양항만공사 사장 귀하</h3></td>
			        			</tr>
			        			<tr height="80">
			        				<td></td>
			        			</tr>
			        		</tbody>
			        	</table>
	        		</td>
        		</tr>
        	</table>
        </div>
        </div>
        <c:if test="${result.flawEnnc == 'Y'}">
	        <div class="page">
	                <div class="subpage ygpa_report" >
	           <table class="page2Title" width="530">
	        			<tr height="15">
	        				<td></td>
	        			</tr>
	        			<tr height="15">
			  				<td><h1>하　자　내　용</h1></td>
			 			</tr>
	        			<tr height="80">
	        				<td><h2><c:out value="${result.flawRprNm }" />)</h2></td>
	        			</tr>
        			<tr height="20">
        				<td></td>
       				</tr>
	        	 </table>
	           <table border="1" class="rprReport" width="530">
	           		<tr>
		           		<th>하자내용</th>
		           		<th>비 고</th>
	           		</tr>
	           		<tr>
		           		<td><c:out value="${result.flawRprContents}"/></td>
		           		<td><c:out value="${result.rm}"/></td>
	           		</tr>
	           	</table>

	           	<p class="subtitle">○ 사진대지</p>
	    		<table class="pictureGridTbl" border="1" width="530">
					<c:forEach var="resultItem" items="${resultList}" varStatus="status" end="3" step="2">
	    			<tr>
	    				<td>
		    				<img class="tdFull" src="<c:url value='/fcltyMng/getRepairAttachFile.do?physicalFileNm=${resultItem.atchFileNmPhysicl }' />"/>
						</td>
						<td>
	    				<c:if test="${fn:length(resultList) gt status.index+1 }">
		    				<img class="tdFull" src="<c:url value='/fcltyMng/getRepairAttachFile.do?physicalFileNm=${resultList[status.index+1].atchFileNmPhysicl }' />"/>
						</c:if>
						</td>
	    			</tr>
	    		 <tr class="caption">
    			<td><c:out value="${resultItem.atchFileSj }"/></td>
    			<td>
   					<c:if test="${fn:length(resultList) gt status.index+1 }">
						<c:out value="${resultList[status.index+1].atchFileSj }"/>
					</c:if>
    			</td>
    			</tr>
	    			</c:forEach>

	    	<c:if test="${fn:length(resultList) gt 4 }">
	    			<!-- 첫페이지는 두줄 만 출력하고 이상인 경우 다음 페이지 출력한다. -->
		    		</table>
		        </div>
	    	</div>
        <div id="ttt" class="page">
            <div class="subpage ygpa_report" >
	    		<table class="pictureGridTbl"  width="530">
	    		<%-- 첫페이지는 두줄 만 출력하고 이상인 경우 다음 페이지 출력한다. 출력한 갯수가 페이지를 벗어나면 위에 페이지 끊기를 추가 한다. --%>
				<c:forEach var="resultItem" items="${resultList}" varStatus="status" begin="4" step="2">
    			<tr>
    				<td>
    				<img class="tdFull" src="<c:url value='/fcltyMng/getRepairAttachFile.do?physicalFileNm=${resultItem.atchFileNmPhysicl}' />"/>
					</td>
					<td>
					<c:if test="${fn:length(resultList) gt status.index+1 }">
	    				<img class="tdFull" src="<c:url value='/fcltyMng/getRepairAttachFile.do?physicalFileNm=${resultItem[status.index+1].atchFileNmPhysicl }' />"/>
					</c:if>
					</td>
    			</tr>

    			<c:if test="${(status.index-status.begin) % 6 == 0 && !status.first && !status.last }">
	    		</table>
	        </div>
    	</div>
        <div id="tet" class="page">	<!--  class="page"> 페이지 누락 됨 -->
            <div class="subpage ygpa_report" >
	    		<table class="pictureGridTbl"  width="530">
	    		<%-- 첫페이지는 두줄 만 출력하고 이상인 경우 다음 페이지 출력한다. 출력한 갯수가 페이지를 벗어나면 위에 페이지 끊기를 추가 한다. --%>
    			</c:if>
    			</c:forEach>
   			</c:if>
        	   </table>

	        </div>

    	</div>
        </c:if>
</div>
  </c:if>
    <c:if test="${resultCode!=0 }">
    	<h2>서버 오류</h2>
    	<p>에러 메시지 : <c:out value="${ resultMessage}"/></p>
    </c:if>
  </body>
