<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamSocPrtFcltyFeeExmpRqestSttusPrint.jsp
  * @Description : 업체별투자비보전상계내역 인쇄 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.10.23  김종민          최초 생성
  *
  * author 김종민
  * since 2014.10.23
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
	<link rel="stylesheet" href="<c:url value='/css/ygpa/gam/ygpa_report.css' />" />

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
  <c:set var="pagePerCount" value="12"/>
  <c:set var="agentName" value="" />
  <c:set var="exmpCntSum" value="0" />
  <c:set var="exmpAmntSum" value="0" />
  <c:if test="${resultCode==0 }">
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
	    	<h1 class="ygpa_report_h1">투자비보전 처리실적</h1>

	<c:if test="${fn:length(resultList) == 0}">
		<table class="rpr_main_table">
	  		<thead>
       			<tr>
	  				<th>호출부호</th>
	  				<th>선명</th>
	  				<th>요금종류</th>
	  				<th>요금종류명</th>
	  				<th>입항년도</th>
	  				<th>입출항일자</th>
	  				<th>외내항구분</th>
	  				<th>시설코드</th>
	  			</tr>
	  			<tr>
	  				<th>회계연도</th>
	  				<th>고지번호</th>
	  				<th>적용요율</th>
	  				<th>징수톤</th>
	  				<th>고지일자</th>
	  				<th>할인율(%)</th>
	  				<th colspan="2">면제금액</th>
				</tr>
	  		</thead>
	  		<tbody>
			<tr>
				<td colspan="10">자료가 존재 하지 않습니다.</td>
			</tr>
	</c:if>
    <c:forEach var="result" items="${resultList }" varStatus="resultStatus">
           			<c:if test="${resultStatus.index%pagePerCount==0 }"> <% /*  페이지 당 출력 갯수 */ %>
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
			        				<td colspan="8">
			        					관할청 : <c:out value="${result.appPrtAtCode}" /> [ <c:out value="${result.appPrtAtKorNm}" />] &nbsp;&nbsp;&nbsp;&nbsp; 
			        					면제기간 : <c:out value="${searchDtFr}" /> ~ <c:out value="${searchDtTo}" />
			        				</td>
			        			</tr>
				       			<tr>
					  				<th>호출부호</th>
					  				<th>선명</th>
					  				<th>요금종류</th>
					  				<th>요금종류명</th>
					  				<th>입항년도</th>
					  				<th>입출항일자</th>
					  				<th>외내항구분</th>
					  				<th>시설코드</th>
					  			</tr>
					  			<tr>
					  				<th>회계연도</th>
					  				<th>고지번호</th>
					  				<th>적용요율</th>
					  				<th>징수톤</th>
					  				<th>고지일자</th>
					  				<th>할인율(%)</th>
					  				<th colspan="2">면제금액</th>
								</tr>
			        		</thead>
			        		<tbody>
        			</c:if>
        			<c:if test="${(result.agentNm != agentName)}">
        				<c:if test="${exmpCntSum != 0}">
        					<tr>
        						<td colspan="4"></td>
        						<td> 업체별 면제금액 </td>
        						<td style="text-align: right" colspan="3"><fmt:formatNumber value="${exmpAmntSum }" type="currency" currencySymbol=""/></td>
        					</tr>	
							<c:set var="exmpCntSum" value="0" />
							<c:set var="exmpAmntSum" value="0" />
        				</c:if>
        				<c:set var="agentName" value="${result.agentNm}"/>
        				<tr>
        					<td style="text-align: center" colspan="8"><c:out value="${agentName}" /></td>
        				</tr>
        			</c:if>        			        			
        			<tr>
        				<td><c:out value="${result.callLetter }" /></td>
        				<td><c:out value="${result.vsslNm }" /></td>
        				<td><c:out value="${result.feeTp }" /></td>
        				<td><c:out value="${result.feeTpNm }" /></td>
        				<td><c:out value="${result.yr }" /></td>
        				<td><c:out value="${result.ioDt }" /></td>
        				<td><c:out value="${result.inOut }" /></td>
        				<td><c:out value="${result.facilNm }" /></td>
					</tr>
					<tr>        				
        				<td><c:out value="${result.fiscalYr }" /></td>
        				<td><c:out value="${result.billNo }" /></td>
        				<td><c:out value="${result.standardFee }" /></td>
        				<td style="text-align: right"><c:out value="${result.realTn }" /></td>
        				<td><c:out value="${result.billDt }" /></td>
        				<td><c:out value="${result.dcRateNm }" /></td>
        				<td colspan="2" style="text-align: right"><fmt:formatNumber value="${result.exmpAmnt}" type="currency" currencySymbol=""/></td>
        			</tr>
        			<c:set var="exmpCntSum" value="${ exmpCntSum + 1 }" />
					<c:set var="exmpAmntSum" value="${ exmpAmntSum + result.exmpAmnt }" />        			
    </c:forEach>
        					<tr>
        						<td colspan="4"></td>
        						<td> 업체별 면제금액 </td>
        						<td style="text-align: right" colspan="3"><fmt:formatNumber value="${exmpAmntSum }" type="currency" currencySymbol=""/></td>
        					</tr>	
        		</tbody>
        		<tfoot>
        			<tr>
        				<td style="border:1px #000000 solid;">자료수 </td>
        				<td style="text-align: right; border:1px #000000 solid;"><c:out value="${totalCount }" />건<!-- fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumCnt}" /--></td>
        				<td style="border:1px #000000 solid;"></td>
        				<td style="border:1px #000000 solid;"></td>
        				<td style="border:1px #000000 solid;">총 면제금액 </td>
        				<td style="text-align: right; border:1px #000000 solid;" colspan="3"><fmt:formatNumber value="${sumExmpAmnt}" type="currency" currencySymbol=""/><!-- fmt:formatNumber type="number" maxIntegerDigits="15" value="${sumAr}" /--></td>
        			</tr>
        		</tfoot>
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