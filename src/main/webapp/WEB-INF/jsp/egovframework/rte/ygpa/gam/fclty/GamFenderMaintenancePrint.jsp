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
	    	<h1 class="ygpa_report_h1">방충재 유지보수</h1>

			<table class="fenderInspection" style="width:100%;">
				<tr>
					<th style="width:15%; height:18px;" >연도</th>
					<td  >
						<c:out value="${printVo.year }" />
						</select>
					</td>
					<th style="width:15%; height:18px;">시설물관리그룹</th>
					<td >
						<c:out value="${printVo.fcltsMngGroupNm }" />
					</td>
				</tr>
				<tr>
					<th style="width:15%; height:18px;">위치</th>
					<td  >
						<c:out value="${printVo.loc }" />
					</td>
					<th style="width:15%; height:18px;">시설물종별</th>
					<td >
						<c:out value="${printVo.fcltsGbnNm }" />
					</td>
				</tr>
				
				<tr>
					<th style="width:15%; height:18px;">연도</th>
					<td  >
						<c:out value="${printVo.year }" />
					</td>
					<th style="width:15%; height:18px;" >시행주제</th>
					<td>
						<c:if test="${printVo.opertnMby == '1'}">
							항만공사
						</c:if>
						<c:if test="${printVo.opertnMby == '2'}">
							운영사
						</c:if>
						<c:if test="${printVo.opertnMby == '3'}">
							시공사
						</c:if>
					</td>
				</tr>
				
				<tr>
					<th  style="width:15%; height:18px;">공사명칭</th>
					<td  >
						<c:out value="${printVo.cntrwkNm }" />
					</td>
					<th  >공사기간</th>
					<td>
						<c:out value="${printVo.cntrwkBegin }" />~<c:out value="${printVo.cntrwkEnd }" />
					</td>
				</tr>
				
				
				<tr>
					<th style="width:15%; height:18px;" >시공자</th>
					<td >
						<c:out value="${printVo.cnstrtr }" />
					</td>
					<th style="width:15%; height:18px;" >사업책임자</th>
					<td>
						<c:out value="${printVo.bsnsRspnber }" />
					</td>
				</tr>
				<tr>
					<th style="width:15%; height:18px;" >공사비</th>
					<td colspan="3">
						<c:out value="${printVo.cntrwkCt }" />
					</td>
				</tr>
				
			</table>

			<table class="rpr_main_table">
		  		<thead>
		  			<tr>
		  				<th>항만시설 명</th>
		  				<th>규격</th>
		  				<th>단위</th>
		  				<th>수량</th>
		  				<th>구조 형식</th>
		  				<th>선석</th>
		  				<th>방충재 종류</th>
		  				<th>방충재 배치 간격</th>
		  				<th>방충재 형식</th>
	 				</tr>
		  		</thead>
		  		<tbody>
					<c:if test="${fn:length(fenderMaintenanceDetailList) == 0}">
						<tr>
							<td colspan="10">자료가 존재 하지 않습니다.</td>
						</tr>
					</c:if>
		
		
 					<c:forEach var="result" items="${fenderMaintenanceDetailList }" varStatus="resultStatus">
	        			<tr>
	        				<td><c:out value="${result.prtFcltyNm }" /></td>
	        				<td><c:out value="${result.prtFcltyStndrd }" /></td>
	        				<td><c:out value="${result.prtFcltyUnit }" /></td>
	        				<td><c:out value="${result.prtPrtFcltyCnt }" /></td>
	        				<td><c:out value="${result.strctFmt }" /></td>
	        				<td><c:out value="${result.berth }" /></td>
	        				<td><c:out value="${result.fenderKndCd }" /></td>
	        				<td><c:out value="${result.fenderPmntItv }" /></td>
	        				<td><c:out value="${result.fenderFmt }" /></td>
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