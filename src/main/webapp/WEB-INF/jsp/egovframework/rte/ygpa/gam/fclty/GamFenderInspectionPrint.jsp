<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
/**
 * @Class Name : GamFenderInspectionPrint.jsp
 * @Description : 방충재 정기점검 출력
 * @Modification Information
 *
 *   수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2019.06.19  	 김재철          화면단 최초 생성
 *
 * author 김재철
 * since 2016.06.19
 *
**/
%>
<html lang="ko" xml:lang="ko">
  <head>
    <title>여수광양항만공사 - GIS기반 자산관리 시스템</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/reset.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/demo/jquery-ui-1.10.4.custom.css' />" />
<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/ygpa_report_l.css' />" />	<% /* /css/ygpa/gam/ygpa_report_l.css 은 가로모드 /css/ygpa/gam/ygpa_report.css 는 세로 모드 (인쇄 설정 시 변경 하여야 함) */ %>

	<script src="<c:url value='/js/jquery-1.10.2.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-migrate-1.2.1.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-ui.min.js'/>"></script>

	<style type="text/css">
	
		 table.fenderInspection {
		    border-collapse: separate;
		    border-spacing: 0;
		    text-align: left;
		    line-height: 1.5;
		    border-top: 1px solid #ccc;
		    border-left: 1px solid #ccc;
		  	margin : 20px 10px;
		}
		table.fenderInspection th {
		    width: 170px;
		    padding: 5px;
		    font-weight: bold;
		    vertical-align: top;
		    border-right: 1px solid #ccc;
		    border-bottom: 1px solid #ccc;
		    border-top: 1px solid #fff;
		    border-left: 1px solid #fff;
		    background: #eee;
		}
		table.fenderInspection td {
		    width: 350px;
		    padding: 5px;
		    vertical-align: top;
		    border-right: 1px solid #ccc;
		    border-bottom: 1px solid #ccc;
		}
	</style>


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
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
	    	<h1 class="ygpa_report_h1">방충재 정기점검</h1>

			<p>
			<table class="summaryPanel" style="width:100%;">
				<tr>
					<th style="font-weight:bold; height:20px;">시설물 현황</th>
					<td style="text-align:right;">
						&nbsp;　　　&nbsp;
						&nbsp;　　　&nbsp;
						&nbsp;　　　&nbsp;
						&nbsp;　　　&nbsp;
						&nbsp;　　　&nbsp;
						&nbsp;　　　&nbsp;
					</td>
				</tr>
			</table>
	
			<table class="fenderInspection" style="width:100%;">
				<tr>
					<th style="width:15%; height:18px;" >연도</th>
					<td  style="width:20%; height:18px;">
						<c:out value="${printVo.year }" />
						</select>
					</td>
					<th style="width:15%; height:18px;">시설물관리그룹</th>
					<td >
						<c:out value="${printVo.fcltsMngGroupNm }" />
					</td>
				</tr>
				<tr>
					<th style="width:15%; height:18px;">위　　　　　　　　치</th>
					<td  style="width:20%; height:18px;">
						<c:out value="${printVo.loc }" />
					</td>
					<th style="width:15%; height:18px;">시　설　물　　종　별</th>
					<td >
						<c:out value="${printVo.fcltsGbnNm }" />
					</td>
				</tr>
				<tr>
					<th style="width:15%; height:18px;" >공　　사　　기　　간</th>
					<td style="width:20%; height:18px;">
						<c:out value="${printVo.cnstDt }" />
					</td>
					<th style="width:15%; height:18px;">준　　공　　일　　자</th>
					<td>
						<c:out value="${printVo.bldDt }" />
					</td>
				</tr>
				<tr>
					<th style="width:15%; height:18px;">운　　　　영　　　사</th>
					<td style="width:20%; height:18px;" >
						<c:out value="${printVo.owner }" />
					</td>
					<th style="width:15%; height:18px;">시　　　　공　　　자</th>
					<td >
						<c:out value="${printVo.cnstrtr }" />
					</td>
				</tr>
				<tr>
					<th style="width:15%; height:18px;">규　　　　  　　　 격</th>
					<td style="width:20%; height:18px;">
						<c:out value="${printVo.prtFcltyStndrd }" />
					</td>
					<th style="width:15%; height:18px;">수　　　　  　　　 량</th>
					<td >
						<c:out value="${printVo.prtPrtFcltyCnt }" />
					</td>
				</tr>
			</table>
			<table class="summaryPanel" style="width:100%;">
				<tr>
					<th style="font-weight:bold; height:20px;">점검 현황</th>
					<td style="text-align:right;">
						&nbsp;　　　&nbsp;
						&nbsp;　　　&nbsp;
						&nbsp;　　　&nbsp;
						&nbsp;　　　&nbsp;
						&nbsp;　　　&nbsp;
						&nbsp;　　　&nbsp;
					</td>
				</tr>
			</table>
			<table class="fenderInspection" style="width:100%;">
				<tr>
					<th style="width:20%; height:18px;">점검일자</th>
					<td  >
						<c:out value="${printVo.chckDeOne }" />
					</td>
					<td  >
						<c:out value="${printVo.chckDeTwo }" />
					</td>
					<td  >
						<c:out value="${printVo.chckDeThree }" />
					</td>
				</tr>
				<tr>
					<th style="width:11%; height:18px;">점검자</th>
					<td >
						<c:out value="${printVo.insctrOne }" />
					<td >
						<c:out value="${printVo.insctrTwo }" />
					</td>
					<td >
						<c:out value="${printVo.insctrThree }" />
					</td>
				</tr>
				<tr>
					<th style="width:11%; height:18px;">점검내용</th>
					<td >
						<c:out value="${printVo.chckCnOne }" />
					</td>
					<td >
						<c:out value="${printVo.chckCnTwo }" />
					</td>
					<td >
						<c:out value="${printVo.chckCnThree }" />
					</td>
				</tr>
				<tr>
					<th style="width:11%; height:18px;">상태평가</th>
					<td >
						<c:out value="${printVo.sttusEvlOne }" />
					</td>
					<td >
						<c:out value="${printVo.sttusEvlTwo }" />
					</td>
					<td >
						<c:out value="${printVo.sttusEvlThree }" />
					</td>
				</tr>
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