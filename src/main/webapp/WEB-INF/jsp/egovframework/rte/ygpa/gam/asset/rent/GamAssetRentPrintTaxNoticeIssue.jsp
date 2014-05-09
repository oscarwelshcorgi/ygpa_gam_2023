<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamAssetRentPrintNoticeIssue.jsp
  * @Description : 고지서 출력 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2014.03.19  eunsungj          최초 생성
  *
  * author eunsungj
  * since 2014.03.14
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
    <style>
/*       html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
      }
 */


.bout {
  font-family:굴림,바탕;
  border: 3px double blue;
}

body, table, tr, td, select{font-family:굴림, verdana, arial; font-size: 12px;color: #000000; border:1px; vertical-align: middle; text-align:center;}
.ttxt {font-family:굴림, verdana, arial; font-size: 12px;color: #0000FF;}
.cborder {border-top-width:1; border-right-width:1; border-bottom-width:1; border-left-width:1; border-color:BLUE; border-top-style:solid; border-right-style:none; border-bottom-style:solid; border-left-style:solid;}
.ctit {font-size: 22px;color: #0000FF; font-weight:bold;}
.ccmt {font-size: 12px;color: #0000FF;}
.taxidno {font-size: 16px;color: black; font-weight:bold;}

.bout2 {font-family:굴림,바탕;border: 3px double red;}
.ttxt2 {font-family:굴림, verdana, arial; font-size: 12px;color: red;}
.cborder2 {border-top-width:1; border-right-width:1; border-bottom-width:1; border-left-width:1; border-color:red; border-top-style:solid; border-right-style:none; border-bottom-style:solid; border-left-style:solid;}
.ctit2 {font-size: 22px;color:red; font-weight:bold;}
.ccmt2 {font-size: 12px;color:red;}

.sign_area {
  position: relative;
}
.sign_img {
  position: absolute;
  top: 15px;
  left: 230px;
}

 body {
    margin: 0;
    padding: 0;
    background-color: #FAFAFA;
    font: 12pt "Tahoma";
}

* {
    box-sizing: border-box;
    -moz-box-sizing: border-box;
}

.page {
    width: 21cm;
    min-height: 29.7cm;
    padding: 1cm;
    margin: 0cm auto;
    border: 1px #D3D3D3 solid;
    border-radius: 5px;
}

.subpage {
    padding: 1cm;
/*     border: 5px red solid;
 */    height: 256mm;
}

table {
	border: none;
	padding: 0px;
	border-spacing: 0px;
}

.taxPrint {
	width: 586px;
}

.w100 {
	width: 100%;
}

.w50 {
	width: 50%;
}

.h100 {
	height: 100%;
}

#printButton {
	position:fixed;
	left: 20px;
	top: 10px;
	display:block;
}

@page {
    size: A4;
    margin: 0;
}
@media print {

	body, table, tr, td, select{font-family:굴림, verdana, arial; font-size: 12px;color: #000000; border:1px; vertical-align: middle; text-align:center;}
	.ttxt {font-family:굴림, verdana, arial; font-size: 12px;color: #0000FF;}
	.cborder {border-top-width:1; border-right-width:1; border-bottom-width:1; border-left-width:1; border-color:BLUE; border-top-style:solid; border-right-style:none; border-bottom-style:solid; border-left-style:solid;}
	.ctit {font-size: 22px;color: #0000FF; font-weight:bold;}
	.ccmt {font-size: 12px;color: #0000FF;}
	.taxidno {font-size: 16px;color: black; font-weight:bold;}

	.bout2 {font-family:굴림,바탕;border: 3px double red;}
	.ttxt2 {font-family:굴림, verdana, arial; font-size: 12px;color: red;}
	.cborder2 {border-top-width:1; border-right-width:1; border-bottom-width:1; border-left-width:1; border-color:red; border-top-style:solid; border-right-style:none; border-bottom-style:solid; border-left-style:solid;}
	.ctit2 {font-size: 22px;color:red; font-weight:bold;}
	.ccmt2 {font-size: 12px;color:red;}

	.sign_area {
	  position: relative;
	}
	.sign_img {
	  position: absolute;
	  top: 15px;
	  left: 230px;
	}

	 body {
	    margin: 0;
	    padding: 0;
	    font: 12pt "Tahoma";
	}

	* {
	    box-sizing: border-box;
	    -moz-box-sizing: border-box;
	}

	.page {
	    width: 21cm;
	    min-height: 29.7cm;
	    padding: 1cm;
	    margin: 0cm auto;
	    border: 1px #D3D3D3 solid;
	    border-radius: 5px;
	}

	.subpage {
	    padding: 1cm;
	/*     border: 5px red solid;
	 */    height: 256mm;
	}

	table {
		border: none;
		padding: 0px;
		border-spacing: 0px;
	}

	.taxPrint {
		width: 586px;
	}

	.w100 {
		width: 100%;
	}

	.w50 {
		width: 50%;
	}

	.h100 {
		height: 100%;
	}


	#printButton {
		position:fixed;
		left: 20px;
		top: 10px;
		display:none;
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
	});
	</script>
  </head>
  <body>
<fmt:parseDate value='${resultList[0].nticDt}' var='nticDate' pattern="yyyy-MM-dd" scope="page"/>
<c:set var="feeAmountVal" value="${resultList[0].feeAmount }" scope="page"/>
<c:set var="vatAmountVal" value="${resultList[0].vatAmount }" scope="page"/>
<%
BigDecimal feeAmount=(BigDecimal)pageContext.getAttribute("feeAmountVal");
BigDecimal vatAmount=(BigDecimal)pageContext.getAttribute("vatAmountVal");
String feeAmtStr="";
String vatAmtStr="";
List resultList = (List)request.getAttribute("resultList");
int i=0;

if(resultList!=null) {
	for(i=resultList.size(); i<4; i++) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("chrgeKndNm", "");	// insert empty data
		map.put("gisAssetsLocplc", "");	// insert empty data
		map.put("gisAssetsLnm", "");	// insert empty data
		map.put("grAr", 0);	// insert empty data
		map.put("fee", 0);	// insert empty data
		map.put("vat", 0);	// insert empty data
		resultList.add(map);
	}
	request.setAttribute("resultList", resultList);
}
else System.out.println( "=============resultList is null : "+pageContext.getAttribute("resultList") );

if(feeAmount!=null) {
	feeAmtStr = feeAmount.toString();
	pageContext.setAttribute("emptyCnt", 11-feeAmtStr.length());
	for(i=feeAmtStr.length(); i<11; i++) {
		feeAmtStr=" "+feeAmtStr;
	}
	pageContext.setAttribute("feeAmount", feeAmtStr);
}
else System.out.println( "=============feeAmount is null : "+pageContext.getAttribute("feeAmountVal") );

if(vatAmount!=null) {
	vatAmtStr = vatAmount.toString();
	for(i=vatAmtStr.length(); i<10; i++) {
		vatAmtStr=" "+vatAmtStr;
	}
	pageContext.setAttribute("vatAmount", vatAmtStr);
}
else System.out.println( "=============vatAmount is null : "+pageContext.getAttribute("vatAmountVal") );

%>
<c:if test="${resultCode==0 }">
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <div class="page">
        <div class="subpage">
	       <table class="taxPrint">
			  <tr>
			    <td height="10">
			      <table class="w100">
			        <tr>
			          <td height="10" class="ttxt" style="text-align: left;">[별지 제11호 서식]</td>
			          <td align="right" class="ttxt" style="text-align: right;">[ ]</td>
			        </tr>
			      </table></td>
			  </tr>
			  <tr>
			    <td width="586">
			      <table class="bout">
			        <tr>
			          <td><table class="w100">
			              <tr>
						  	<td width="20"></td>
			                <td width="359"><span class="ctit">세 금 계 산 서</span> <span class="ccmt">(공급받는자용)</span></td>
			                <td width="201">
			                	<table class="w100">
				                    <tr>
				                      <td width="25%" class="cborder" style="border-top-style:none"><span class="ttxt">책 번 호</span></td>
				                      <td width="33%" class="cborder" style="border-top-style:none; text-align: right;"> <!-- serial --><span class="ttxt">권</span> &nbsp;</td>
				                      <td width="42%" class="cborder" style="border-top-style:none; text-align: right;"> <!-- no --><span class="ttxt">호</span> &nbsp;</td>
				                    </tr>
				                    <tr>
				                      <td class="cborder" style="border-top-style:none;border-bottom-style:none"><span class="ttxt">일련번호</span></td>
				                      <td colspan="2" class="cborder" style="border-top-style:none;border-bottom-style:none"><!-- 일련번호 --></td>
				                    </tr>
				                  </table>
	                  		</td>
	              			</tr>
	            		</table></td>
			        </tr>
			        <tr>
			          <td>
			          <table class="w100 cborder" style="border-left-style:none">
			              <tr>
			                <td width="50%">
			                	<!-- 공급자 시작 -->
			                <table class="w100">
					          	<colgroup>
					          	<col width="18"/>
					          	<col width="55"/>
					          	<col width="100"/>
					          	<col width="15"/>
					          	<col width="100"/>
					          	</colgroup>
			                    <tr height="35">
			                      <td class="cborder" style="border-left-style:none" rowspan="4"><span class="ttxt" style="line-height:35px">공<br>
			                        급<br>
			                        자</span>
			                       </td>
			                       <td class="cborder"><span class="ttxt">등록번호</span></td>
		                            <td class="cborder" colspan="3"><span class="taxidno">100-20-34567</span></td>
	                            </tr>
	                            <tr height="35">
		                            <td class="cborder" style="border-top-style:none"><span class="ttxt">상 호<br>
			                            (법인명)</span> </td>
	                                  <td class="cborder" style="border-top-style:none">여수광양항만공사</td>
	                                  <td class="cborder" style="border-top-style:none"><span class="ttxt">성<br>
	                                    명</span></td>
	                                  <td class="cborder" style="border-top-style:none; text-align:right; padding-right: 2px">사장&nbsp;<span class="ttxt">(인)</span></td>
	                            </tr>
	                            <tr height="35">
			                            <td class="cborder" style="border-top-style:none"><span class="ttxt">사업장<br>
			                              주 소</span></td>
			                            <td class="cborder" style="border-top-style:none" colspan="3">광양시 항만대로 465</td>
	                            </tr>
	                            <tr height="35">
			                            <td class="cborder" style="border-top-style:none"><span class="ttxt">업 태</span></td>
		                                  <td class="cborder" style="border-top-style:none"><!-- 업태 --></td>
		                                  <td class="cborder" style="border-top-style:none"><span class="ttxt">종<br>목</span></td>
		                                  <td class="cborder" style="border-top-style:none"><!-- 종목 --></td>
	                            </tr>
			                  </table>
			                  <!-- 공급자 끝 -->
			                  </td>
			                <td  class="w50">
			                			                <table class="w100">
					          	<colgroup>
					          	<col width="18"/>
					          	<col width="55"/>
					          	<col width="100"/>
					          	<col width="15"/>
					          	<col width="100"/>
					          	</colgroup>
			                    <tr height="35">
			                      <td class="cborder" rowspan="4"><span class="ttxt" style="line-height:20px">공<br>
			                        급<br>
									            받<br>
									            는<br>
			                        자</span>
			                       </td>
			                       <td class="cborder"><span class="ttxt">등록번호</span></td>
		                            <td class="cborder" colspan="3"><span class="taxidno"><c:out value="${fn:substring(resultList[0].bizrno, 0,3)}"/>-<c:out value="${fn:substring(resultList[0].bizrno, 3,5)}"/>-<c:out value="${fn:substring(resultList[0].bizrno, 5,10)}"/></span></td>
	                            </tr>
	                            <tr height="35">
		                            <td class="cborder" style="border-top-style:none"><span class="ttxt">상 호<br>
			                            (법인명)</span> </td>
	                                  <td class="cborder" style="border-top-style:none"><c:out value="${resultList[0].entrpsNm}"/></td>
	                                  <td class="cborder" style="border-top-style:none"><span class="ttxt">성<br>
	                                    명</span></td>
	                                  <td class="cborder" style="border-top-style:none; text-align:right; padding-right: 2px"><c:out value="${resultList[0].rprsntvNm}"/>&nbsp;<span class="ttxt">(인)</span></td>
	                            </tr>
	                            <tr height="35">
			                            <td class="cborder" style="border-top-style:none"><span class="ttxt">사업장<br>
			                              주 소</span></td>
			                            <td class="cborder" style="border-top-style:none" colspan="3"><c:out value="${resultList[0].adres}"/></td>
	                            </tr>
	                            <tr height="35">
			                            <td class="cborder" style="border-top-style:none"><span class="ttxt">업 태</span></td>
		                                  <td class="cborder" style="border-top-style:none"><!-- 업태 --></td>
		                                  <td class="cborder" style="border-top-style:none"><span class="ttxt">종<br>목</span></td>
		                                  <td class="cborder" style="border-top-style:none"><!-- 종목 --></td>
	                            </tr>
			                  </table>

							</td>
			              </tr>
			            </table>
			            </td>
			        </tr>
			        <tr>
			          <td><table class="cborder w100" style="border-top-style:none;border-left-style:none">
			              <tr height="20">
			                <td class="cborder" style="border-top-style:none;border-left-style:none"><span class="ttxt">작 성</span></td>
			                <td class="cborder" style="border-top-style:none"><span class="ttxt">공 &nbsp; &nbsp; 급
			                   &nbsp; &nbsp; 가 &nbsp; &nbsp; 액</span></td>
			                <td class="cborder" style="border-top-style:none"><span class="ttxt">세 &nbsp; &nbsp; 액</span></td>
			                <td class="cborder" style="border-top-style:none"><span class="ttxt">비 고</span></td>
			              </tr>
			              <tr>
			                <td><table class="w100">
			                    <tr height="20">
			                      <td class="cborder" style="border-top-style:none;border-left-style:none"><span class="ttxt">년</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">월</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">일</span></td>
			                    </tr>
			                    <tr height="24">
			                      <td class="cborder" style="border-top-style:none;border-left-style:none"><fmt:formatDate value="${nticDate}" pattern="yyyy"/></td>
			                      <td class="cborder" style="border-top-style:none"><fmt:formatDate value="${nticDate}" pattern="MM"/></td>
			                      <td class="cborder" style="border-top-style:none"><fmt:formatDate value="${nticDate}" pattern="dd"/></td>
			                    </tr>
			                  </table></td>
			                <td><table class="w100">
			                    <tr height="20">
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">공란수</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">백</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">십</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">억</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">천</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">백</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">십</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">만</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">천</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">백</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">십</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">일</span></td>
			                    </tr>
			                    <tr height="24">
			                      <td class="cborder" style="border-top-style:none"><c:out value="${emptyCnt}" /></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(feeAmount, 0, 1) }"/></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(feeAmount, 1, 2) }"/></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(feeAmount, 2, 3) }"/></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(feeAmount, 3, 4) }"/></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(feeAmount, 4, 5) }"/></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(feeAmount, 5, 6) }"/></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(feeAmount, 6, 7) }"/></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(feeAmount, 7, 8) }"/></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(feeAmount, 8, 9) }"/></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(feeAmount, 9, 10) }"/></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(feeAmount, 10, 11) }"/></td>
			                    </tr>
			                  </table></td>
			                <td><table class="w100">
			                    <tr height="20">
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">십</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">억</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">천</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">백</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">십</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">만</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">천</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">백</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">십</span></td>
			                      <td class="cborder" style="border-top-style:none"><span class="ttxt">일</span></td>
			                    </tr>
			                    <tr height="24">
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(vatAmount, 0, 1) }"/></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(vatAmount, 1, 2) }"/></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(vatAmount, 2, 3) }"/></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(vatAmount, 3, 4) }"/></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(vatAmount, 4, 5) }"/></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(vatAmount, 5, 6) }"/></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(vatAmount, 6, 7) }"/></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(vatAmount, 7, 8) }"/></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(vatAmount, 8, 9) }"/></td>
			                      <td class="cborder" style="border-top-style:none"><c:out value="${fn:substring(vatAmount, 9, 10) }"/></td>
			                    </tr>
			                  </table></td>
			                <td class="cborder" style="border-top-style:none"><?=$prtInfo['tax_note']?></td>
			              </tr>
			            </table></td>
			        </tr>
			        <tr>
			          <td><table class="w100 cborder" style="border-top-style:none;border-left-style:none">
			              <tr height="20">
			                <td class="cborder" style="border-top-style:none;border-left-style:none"><span class="ttxt">월</span></td>
			                <td class="cborder" style="border-top-style:none;border-left-style:none"><span class="ttxt">일</span></td>
			                <td class="cborder" style="border-top-style:none"><span class="ttxt">품 &nbsp; &nbsp; 목</span></td>
			                <td class="cborder" style="border-top-style:none"><span class="ttxt">규 격</span></td>
			                <td class="cborder" style="border-top-style:none"><span class="ttxt">수 량</span></td>
			                <td class="cborder" style="border-top-style:none"><span class="ttxt">단 가</span></td>
			                <td class="cborder" style="border-top-style:none"><span class="ttxt">공 급 가 액</span></td>
			                <td class="cborder" style="border-top-style:none"><span class="ttxt">세 액</span></td>
			                <td class="cborder" style="border-top-style:none"><span class="ttxt">비 고</span></td>
			              </tr>
			              <c:forEach items="${resultList }" var="result">
			              <tr height="24">
			                <td class="cborder" style="border-top-style:none;border-left-style:none"><c:if test="${result.fee!=0}"><fmt:formatDate value="${nticDate}" pattern="MM"/></c:if></td>
			                <td class="cborder" style="border-top-style:none"><c:if test="${result.fee!=0}"><fmt:formatDate value="${nticDate}" pattern="dd"/></c:if></td>
			                <td class="cborder" style="border-top-style:none"><c:out value="${result.chrgeKndNm}"/></td>
			                <td class="cborder" style="border-top-style:none"><c:out value="${result.gisAssetsLocplc}"/>&nbsp;<c:out value="${result.gisAssetsLnm}"/><c:if test="${result.gisAssetsLnmSub!=null}">-<c:out value="${result.gisAssetsLnmSub}"/></c:if></td>
			                <td class="cborder" style="border-top-style:none; text-align:right; padding-right: 2px"><c:if test="${result.fee!=0}"><c:out value="${result.grAr}"/></c:if></td>
			                <td class="cborder" style="border-top-style:none; text-align:right; padding-right: 2px"><c:if test="${result.fee!=0}"><c:out value="${result.fee-result.vat}"/></c:if> &nbsp;</td>
			                <td class="cborder" style="border-top-style:none; text-align:right; padding-right: 2px"><c:if test="${result.fee!=0}"><c:out value="${result.fee-result.vat}"/></c:if>  &nbsp;</td>
			                <td class="cborder" style="border-top-style:none; text-align:right; padding-right: 2px"><c:if test="${result.fee!=0}"><c:out value="${result.vat}"/></c:if> &nbsp;</td>
			                <td class="cborder" style="border-top-style:none"></td>
			              </tr>
			              </c:forEach>
			            </table></td>
			        </tr>
			        <tr>
			          <td><table class="w100">
			              <tr height="20">
			                <td class="cborder" style="border-top-style:none;border-left-style:none"><span class="ttxt">합 계 금 액</span></td>
			                <td class="cborder" style="border-top-style:none"><span class="ttxt">현 금</span></td>
			                <td class="cborder" style="border-top-style:none"><span class="ttxt">수 표</span></td>
			                <td class="cborder" style="border-top-style:none"><span class="ttxt">어 음</span></td>
			                <td class="cborder" style="border-top-style:none"><span class="ttxt">외상미수금</span></td>
			                <td class="cborder" style="border-top-style:none;border-bottom-style:none" rowspan="2"><span class="ttxt">이 금액을</span>
			                  <b>청구</b> <span class="ttxt">함</span></td>
			              </tr>
			              <tr height="24">
			                <td class="cborder" style="border-top-style:none;border-left-style:none;border-bottom-style:none; text-align: right;"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${resultList[0].totalNticAmt}" /></td>
			                <td class="cborder" style="border-top-style:none;border-bottom-style:none">&nbsp;</td>
			                <td class="cborder" style="border-top-style:none;border-bottom-style:none">&nbsp;</td>
			                <td class="cborder" style="border-top-style:none;border-bottom-style:none">&nbsp;</td>
			                <td class="cborder" style="border-top-style:none;border-bottom-style:none">&nbsp;</td>
			              </tr>
			            </table></td>
			        </tr>
			      </table>
			    </td>
			  </tr>
			  <tr>
			    <td align="right">
			      <table class="w100">
			        <tr>
			          <td class="ccmt">[직인과 일련번호가 있어야 유효합니다]</td>
			        </tr>
			      </table>
			    </td>
			  </tr>
			  <tr height="70">
			    <td><p>&nbsp; -----  이 하 여 백 -----</p></td>

			  </tr>
		</table>

        </div>
    </div>
</div>
</c:if>
<c:if test="${resultCode!=0 }">
	<h2>인쇄 할 대상이 존재하지 않습니다.</h2>
</c:if>

  </body>
</html>