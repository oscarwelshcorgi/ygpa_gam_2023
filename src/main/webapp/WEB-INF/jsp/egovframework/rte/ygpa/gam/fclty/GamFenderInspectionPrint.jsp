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
    <div class="">
        <div class="subpage ygpa_report" >
	    	<h1 class="ygpa_report_h1">방충재 정기점검</h1>

			<p>
			<h4>시설물 현황</h4>

			<table class="fenderInspection" style="width:100%;">
				<tr>
					<th style="width:20%; height:18px;" >연　　　　　　도</th>
					<td  style="width:40%; height:18px;">
						<c:out value="${printVo.year }" />
						</select>
					</td>
					<th style="width:20%; height:18px;">시설물　관리그룹</th>
					<td style="width:20%; height:18px;">
						<c:out value="${printVo.fcltsMngGroupNm }" />
					</td>
				</tr>
				<tr>
					<th >위　　　　　　치</th>
					<td >
						<c:out value="${printVo.loc }" />
					</td>
					<th >시설물　　　종별</th>
					<td >
						<c:out value="${printVo.fcltsGbnNm }" />
					</td>
				</tr>
				<tr>
					<th  >공　사　　기　간</th>
					<td>
						<c:out value="${printVo.cnstDt }" />
					</td>
					<th >준　공　　일　자</th>
					<td>
						<c:out value="${printVo.bldDt }" />
					</td>
				</tr>
				<tr>
					<th >운　　　영　　사</th>
					<td >
						<c:out value="${printVo.owner }" />
					</td>
					<th >시　　　공　　자</th>
					<td >
						<c:out value="${printVo.cnstrtr }" />
					</td>
				</tr>
				<tr>
					<th >규　　　　　　격</th>
					<td>
						<c:out value="${printVo.prtFcltyStndrd }" />
					</td>
					<th >수　　　　　　 량</th>
					<td >
						<c:out value="${printVo.prtPrtFcltyCnt }" />
					</td>
				</tr>
			</table>

			<h4>정기점검(해빙기)</h4>
			점검 일자 : <c:out value="${printVo.chckDeOne }" />
			<br>점  검  자 : <c:out value="${printVo.insctrOne }" />

			<table class="rpr_main_table">
		  		<thead>
		  			<tr>
		  				<th>항만시설 명</th>
		  				<th>상태/평가</th>
		  				<th>규격</th>
		  				<th>구조 형식</th>
	 				</tr>
		  		</thead>
		  		<tbody>
					<c:if test="${fn:length(fenderInspectionList1) == 0}">
						<tr>
							<td colspan="10">자료가 존재 하지 않습니다.</td>
						</tr>
					</c:if>


 					<c:forEach var="result" items="${fenderInspectionList1 }" varStatus="resultStatus">
	        			<tr>
	        				<td><c:out value="${result.prtFcltyNm }" /></td>
	        				<td><c:out value="${result.remark }" /></td>
	        				<td><c:out value="${result.prtFcltyStndrd }" /></td>
	        				<td><c:out value="${result.strctFmt }" /></td>
	        			</tr>
    				</c:forEach>
        		</tbody>
        	</table>
        	<BR>

			<h4>정기점검(우기)</h4>
			점검 일자 : <c:out value="${printVo.chckDeTwo }" />
			<br>점  검  자 : <c:out value="${printVo.insctrTwo }" />
			<table class="rpr_main_table">
		  		<thead>
		  			<tr>
		  				<th>항만시설 명</th>
		  				<th>상태/평가</th>
		  				<th>규격</th>
		  				<th>구조 형식</th>
	 				</tr>
		  		</thead>
		  		<tbody>
					<c:if test="${fn:length(fenderInspectionList2) == 0}">
						<tr>
							<td colspan="10">자료가 존재 하지 않습니다.</td>
						</tr>
					</c:if>


 					<c:forEach var="result" items="${fenderInspectionList2 }" varStatus="resultStatus">
	        			<tr>
	        				<td><c:out value="${result.prtFcltyNm }" /></td>
	        				<td><c:out value="${result.remark }" /></td>
	        				<td><c:out value="${result.prtFcltyStndrd }" /></td>
	        				<td><c:out value="${result.strctFmt }" /></td>
	        			</tr>
    				</c:forEach>
        		</tbody>
        	</table>
        	<BR>

			<h4>정기점검(동절기)</h4>
			점검 일자 : <c:out value="${printVo.chckDeThree }" />
			<br>점  검  자 : <c:out value="${printVo.insctrThree }" />
			<table class="rpr_main_table">
		  		<thead>
		  			<tr>
		  				<th>항만시설 명</th>
		  				<th>상태/평가</th>
		  				<th>규격</th>
		  				<th>구조 형식</th>
	 				</tr>
		  		</thead>
		  		<tbody>
					<c:if test="${fn:length(fenderInspectionList3) == 0}">
						<tr>
							<td colspan="10">자료가 존재 하지 않습니다.</td>
						</tr>
					</c:if>


 					<c:forEach var="result" items="${fenderInspectionList3 }" varStatus="resultStatus">
	        			<tr>
	        				<td><c:out value="${result.prtFcltyNm }" /></td>
	        				<td><c:out value="${result.remark }" /></td>
	        				<td><c:out value="${result.prtFcltyStndrd }" /></td>
	        				<td><c:out value="${result.strctFmt }" /></td>
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