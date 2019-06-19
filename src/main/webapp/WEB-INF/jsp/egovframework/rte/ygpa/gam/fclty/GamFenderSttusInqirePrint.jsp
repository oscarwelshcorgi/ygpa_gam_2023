<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamAssetSttusInqirePrint.jsp
  * @Description : 인쇄 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.10.10  eunsungj          최초 생성
  *
  * author eunsungj
  * since 2014.10.10
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
<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/ygpa_report_l.css' />" />	<% /* /css/ygpa/gam/ygpa_report_l.css 은 가로모드 /css/ygpa/gam/ygpa_report.css 는 세로 모드 (인쇄 설정 시 변경 하여야 함) */ %>

	<script src="<c:url value='/js/jquery-1.10.2.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-migrate-1.2.1.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-ui.min.js'/>"></script>

	<style type="text/css">
		 table.fcltsMng {
		    border-collapse: separate;
		    border-spacing: 0;
		    text-align: left;
		    line-height: 1.5;
		    border-top: 1px solid #ccc;
		    border-left: 1px solid #ccc;
		  	margin : 20px 10px;
		}
		table.fcltsMng th {
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
		table.fcltsMng td {
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
	    	<h1 class="ygpa_report_h1">방충재 현황</h1>

			<p>
			<table class="fcltsMng">
				<tr>
					<th >시설물　관리 그룹　명</th>
					<td  >
						<c:out value="${fenderMng.fcltsMngGroupNm }" />
					</td>
				</tr>
				<tr>
					<th >위　　　　　　　　치</th>
					<td  >
						<c:out value="${fenderMng.loc }" />
					</td>
				</tr>
				<tr>
					<th >시　설　물　　종　별</th>
					<td  >
						<c:if test="${fenderMng.fcltsGbn == 0}">
							1종
						</c:if>
						<c:if test="${fenderMng.fcltsGbn == 0}">
							2종
						</c:if>
						<c:if test="${fenderMng.fcltsGbn == 0}">
							1종/2종
						</c:if>
						<c:if test="${fenderMng.fcltsGbn == 0}">
							기타
						</c:if>
					</td>
				</tr>
				<tr>
					<th >공　　사　　기　　간</th>
					<td>
						<c:out value="${fenderMng.cnstDt }" />
					</td>
				</tr>
				<tr>
					<th >준　　공　　일　　자</th>
					<td>
						<c:out value="${fenderMng.bldDt }" />
					</td>
				</tr>
				<tr>
					<th >운　　　　영　　　사</th>
					<td  >
						<c:out value="${fenderMng.owner }" />
					</td>
				</tr>
				<tr>
					<th >시　　　　공　　　자</th>
					<td >
						<c:out value="${fenderMng.cnstrtr }" />
					</td>
				</tr>
				<tr>
					<th >규　　　　  　　　 격</th>
					<td >
						<c:out value="${fenderMng.stndrd }" />
					</td>
				</tr>
				<tr>
					<th >수　　　　  　　　 량</th>
					<td >
						<c:out value="${fenderMng.qy }" />
					</td>
				</tr>
				<tr>
					<th >기　　타　　규　　격</th>
					<td >
						<c:out value="${fenderMng.etcStndrd }" />
					</td>
				</tr>
				<tr>
					<th >기　　타　　수　　량</th>
					<td >
						<c:out value="${fenderMng.etcQy }" />
					</td>
				</tr>
		</table>


	<c:if test="${fn:length(gamFenderMngGroupList) == 0}">
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
			<tr>
				<td colspan="10">자료가 존재 하지 않습니다.</td>
			</tr>
	</c:if>
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
    <c:forEach var="result" items="${gamFenderMngGroupList }" varStatus="resultStatus">
<%--            			<c:if test="${resultStatus.index%18==0 }"> <% /*  페이지 당 출력 갯수 */ %>
           				<c:if test="${resultStatus.index!=0 }">	<% /*  페이지 구분*/ %>
			        		</tbody>
			        		</table>
	        		        </div>
    						</div>
						    <div class="page">
						        <div class="subpage ygpa_report" >
           				</c:if>

        				<!--  헤더 반복  -->
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
        			</c:if>
 --%>
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

<%--
        				<td style="text-align: right"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.gisAssetsAr}" /></td>
 --%>

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