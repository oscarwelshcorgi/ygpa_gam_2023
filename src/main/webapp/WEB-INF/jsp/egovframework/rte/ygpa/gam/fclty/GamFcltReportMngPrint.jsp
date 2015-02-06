<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamFcltReportMngPrint.jsp
  * @Description : 시설물관리대장 인쇄 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2015.2.6    HNJ          최초 생성
  *
  * author HNJ
  * since 2015.2.6
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
	<link rel="stylesheet" href="/css/ygpa/gam/ygpa_report_l.css" />

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
        	<div style="height:50px;text-align:left;vertical-align:middle;border-top:1px red;font-size:25px;font-weight:bold;">5. 시설물관리대장</div>
        	<div style="height:40px;text-align:left;vertical-align:middle;border-top:1px red;font-size:20px;"> 1. 기본현황</div>
    		<table style="width:100%;" class="rpr_form_table">
        		<tbody>
        			<tr height="40px">
        				<td colspan="3" style="width:80px;text-align:center;font-size:13px;">시설물 번호</td>
        				<td style="width:210px;text-align:center;font-size:13px;">관리번호</td>
        				<td colspan="3" style="width:80px;text-align:center;font-size:13px;">시설물명</td>
        				<td style="text-align:center;font-size:13px;">노선</td>
        				<td style="text-align:center;font-size:13px;">시설물종류</td>
        				<td style="text-align:center;font-size:13px;">시설물종별</td>
        				<td style="text-align:center;font-size:13px;">시설물구분</td>
        			</tr>
        			<tr height="40px">
        				<td colspan="3" style="width:80px;text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.fcltsNo }" /></td>
        				<td style="width:210px;text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.fcltsMngGroupNo }" /></td>
        				<td colspan="3" style="width:80px;text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.fcltsNm }" /></td>
        				<td style="text-align:center;font-size:13px;width:80px;word-break:break-all;"><c:out value="${result.route }" /></td>
        				<td style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.fcltsKnd }" /></td>
        				<td style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.fcltsGbn }" /></td>
        				<td style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.fcltsSe }" /></td>
        			</tr>
        			<tr height="40px">
        				<td colspan="6" style="text-align:center;font-size:13px;">
        					위치&nbsp;&nbsp; (시,도) &nbsp;(시,군,구) &nbsp;&nbsp;&nbsp;(읍,면,동) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(리,번지 등 주소)
        				</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">관리주체</td>
        				<td style="text-align:center;font-size:13px;">관리주체구분</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">소유자</td>
        			</tr>
        			<tr height="40px">
        				<td style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.locDo }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;width:80px;word-break:break-all;"><c:out value="${result.locSi }" /></td>
        				<td style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.locDong }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.locJibun }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.mngMainbd }" /></td>
        				<td style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.mngMainbdSe }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.owner }" /></td>
        			</tr>
        			<tr height="40px">
        				<td colspan="2" style="text-align:center;font-size:13px;">준공일</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">하자담보책임만료일</td>
        				<td style="text-align:center;font-size:13px;">상세제원</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">안전점검 및<br>정밀안전진단이력</td>
        				<td colspan="4" style="text-align:center;font-size:13px;">보수·보강<br>이력</td>
        			</tr>
        			<tr height="40px">
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.bldDt }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.flawEndDt }" /></td>
        				<td style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.dtlsSpecYn }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.qcHistYn }" /></td>
        				<td colspan="4" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.mntnHistYn }" /></td>
        			</tr>
        			<tr height="40px">
        				<td colspan="3" style="text-align:center;font-size:13px;">설계기간</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">설계자</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">공사기간</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">시공자</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">총공사비(백만원)</td>
        			</tr>
        			<tr height="40px">
        				<td colspan="3" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.planBeginDt }" /> ~ <br><c:out value="${result.planEndDt }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.planner }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.cnstBeginDt }" /> ~ <c:out value="${result.cnstEndDt }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.cnstrtr }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.cnstrctAmt }" /></td>
        			</tr>
        			<tr height="40px">
        				<td style="text-align:center;font-size:13px;">영10조대상</td>
        				<td colspan="3" style="text-align:center;font-size:13px;">감리기간</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">감리자<br>(책임감리원)</td>
        				<td style="text-align:center;font-size:13px;">공사발주자</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">공사명</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">공사감독·<br>공사관리관</td>
        			</tr>
        			<tr height="40px">
        				<td style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.erqProofPlanApplcEnnc }" /></td>
        				<td colspan="3" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.inspectBeginDt }" /> ~ <br><c:out value="${result.inspectEndDt }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.inspector }" /></td>
        				<td style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.cnstOrderBody }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.cnstNm }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.cnstSupervisor }" /></td>
        			</tr>
        			<tr height="40px">
        				<td colspan="11" style="text-align:left;font-size:13px;"> ▷ 기타 기본현황</td>
        			</tr>
        			<tr height="40px">
        				<td colspan="3" style="text-align:center;font-size:13px;">작성일</td>
        				<td colspan="3" style="text-align:center;font-size:13px;">작성자</td>
        				<td colspan="2" style="text-align:center;font-size:13px;">최종 수정일</td>
        				<td colspan="3" style="text-align:center;font-size:13px;">최종 수정자</td>
        			</tr>
        			<tr height="40px">
        				<td colspan="3" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.wrtDt }" /></td>
        				<td colspan="3" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.wrtUsr }" /></td>
        				<td colspan="2" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.lastUpdtDt }" /></td>
        				<td colspan="3" style="text-align:center;font-size:13px;word-break:break-all;"><c:out value="${result.lastUpdtUsr }" /></td>
        			</tr>
        			<tr height="40px">
        				<td colspan="3" style="text-align:left;font-size:13px;border-right:0;"> ▷ 비고</td>
        				<td colspan="8" style="text-align:center;font-size:13px;border-left:0;word-break:break-all;"> <c:out value="${result.basicRm }" /></td>
        			</tr>
        		</tbody>  
        	</table>
    		
        </div>
    </div>
    <div class="page">
        <div class="subpage ygpa_report" >
        	<div style="height:40px;text-align:left;vertical-align:middle;border-top:1px red;font-size:20px;"> 2. 상세제원</div>
    		<table style="width:100%;" class="rpr_form_table">
        		<tbody>
        			<tr height="60px">
        				<td colspan="2" style="width:80px;text-align:center;font-size:13px;">시설물명</td>
        				<td style="width:210px;text-align:center;font-size:13px;">최대계류선박규모</td>
        				<td colspan="2" style="width:80px;text-align:center;font-size:13px;">연장</td>
        				<td style="text-align:center;font-size:13px;">천단고</td>
        				<td style="text-align:center;font-size:13px;">수심</td>
        			</tr>
        			<tr height="60px">
        				<td colspan="2" style="width:80px;text-align:center;font-size:13px;"><c:out value="${result.fcltsNm }" /></td>
        				<td style="width:210px;text-align:center;font-size:13px;"><c:out value="${result.maxShipScl }" /></td>
        				<td colspan="2" style="width:80px;text-align:center;font-size:13px;"><c:out value="${result.extd }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.upsideAlt }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.dpwt }" /></td>
        			</tr>
        			<tr height="60px">
        				<td rowspan="7" style="width:80px;text-align:center;font-size:13px;">구조형식</td>
        				<td rowspan="2" style="width:100px;text-align:center;font-size:13px;">중력식</td>
        				<td style="width:210px;text-align:center;font-size:13px;">케이슨식</td>
        				<td colspan="2" style="width:80px;text-align:center;font-size:13px;">L형블럭</td>
        				<td style="text-align:center;font-size:13px;">셀룰러블럭식</td>
        				<td style="text-align:center;font-size:13px;">현장타설식</td>
        			</tr>
        			<tr height="60px">
        				<td style="width:210px;text-align:center;font-size:13px;"><c:out value="${result.fmt1Desc1 }" /></td>
        				<td colspan="2" style="width:80px;text-align:center;font-size:13px;"><c:out value="${result.fmt1Desc2 }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.fmt1Desc3 }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.fmt1Desc4 }" /></td>
        			</tr>
        			<tr height="60px">
        				<td rowspan="3" style="width:100px;text-align:center;font-size:13px;">잔교식</td>
        				<td colspan="3" style="width:210px;text-align:center;font-size:13px;">말&nbsp;&nbsp;&nbsp;&nbsp;뚝&nbsp;&nbsp;&nbsp;&nbsp;식</td>
        				<td rowspan="2" style="text-align:center;font-size:13px;">원&nbsp;&nbsp;&nbsp;통&nbsp;&nbsp;&nbsp;식</td>
        				<td rowspan="2" style="text-align:center;font-size:13px;">교&nbsp;&nbsp;&nbsp;각&nbsp;&nbsp;&nbsp;식</td>
        			</tr>
        			<tr height="60px">
        				<td style="width:120px;text-align:center;font-size:13px;">구&nbsp;&nbsp;&nbsp;&nbsp;경</td>
        				<td style="text-align:center;font-size:13px;">연&nbsp;&nbsp;장</td>
        				<td style="text-align:center;font-size:13px;">본&nbsp;&nbsp;수</td>
        			</tr>
        			<tr height="60px">
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.fmt2Desc11 }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.fmt2Desc12 }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.fmt2Desc13 }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.fmt2Desc2 }" /></td>
        				<td style="text-align:center;font-size:13px;"><c:out value="${result.fmt2Desc3 }" /></td>
        			</tr>
        			<tr height="60px">
        				<td rowspan="2" style="width:100px;text-align:center;font-size:13px;">널말뚝식</td>
        				<td style="width:210px;text-align:center;font-size:13px;">규&nbsp;&nbsp;&nbsp;&nbsp;격</td>
        				<td colspan="2" style="width:80px;text-align:center;font-size:13px;"> </td>
        				<td colspan="2" style="text-align:center;font-size:13px;">기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;타</td>
        			</tr>
        			<tr height="60px">
        				<td style="width:210px;text-align:center;font-size:13px;"><c:out value="${result.fmt3Desc1 }" /></td>
        				<td colspan="2" style="width:80px;text-align:center;font-size:13px;"> </td>
        				<td colspan="2" style="text-align:center;font-size:13px;"><c:out value="${result.fmt3Desc2 }" /></td>
        			</tr>
        			<tr height="60px">
        				<td colspan="2" style="text-align:left;font-size:13px;border-right:0;"> ▷ 기타 상세제원</td>
        				<td colspan="5" style="text-align:center;font-size:13px;border-left:0;word-break:break-all;"> <c:out value="${result.etcDtlsSpec }" /></td>
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