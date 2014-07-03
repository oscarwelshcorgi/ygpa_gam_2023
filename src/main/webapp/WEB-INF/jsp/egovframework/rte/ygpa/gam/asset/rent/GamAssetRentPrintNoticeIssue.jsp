<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
    background-image:url('<c:url value="/images/egovframework/ygpa/gam/misc/giro_page.png"/>');
    -webkit-background-size: cover; /* For WebKit*/
    -moz-background-size: cover;    /* Mozilla*/
    -o-background-size: cover;      /* Opera*/
    background-size: cover;         /* Generic*/
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

.subpage {
    padding: 1cm;
/*     border: 5px red solid;
 */    height: 256mm;
}

div.sender {
	position: absolute;
	display: block;
	left: 2cm;
	top: 2.6cm;
	width: 10cm;
	height: 1.6cm;
	font-size: 0.35cm;
	font-family: 굴림;
}

#senderAddress {
	display: block;
	position: relative;
	padding: 0.2cm
}

#senderPostcode {
	display: block;
	position: relative;
	padding: 0.2cm
}

div.receiver {
	position: absolute;
	display: block;
	left: 8cm;
	top: 6cm;
	width: 11cm;
	height: 2.4cm;
	font-size: 0.35cm;
	font-family: 굴림;
	text-align: right;
}

#companyName {
	position: absolute;
	left: 0cm;
	top: 0cm;
	width: 8cm;
	height: 0.4cm;
	text-align: right;
	font-family: 굴림;
	font-size: 0.4cm;
}

#ceoName {
	position: absolute;
	left: 8cm;
	top: 0cm;
	width: 3cm;
	height: 0.4cm;
	text-align: right;
	font-family: 굴림;
	font-size: 0.4cm;
}

#recAddress {
	position: absolute;
	left: 0cm;
	top: 0.6cm;
	width: 11cm;
	height: 0.4cm;
	text-align: right;
}

#recPostcode {
	position: absolute;
	left: 0cm;
	top: 1cm;
	width: 11cm;
	height: 0.4cm;
	text-align: right;
}

div.letterMsg {
	position: absolute;
	display: block;
	left: 8.35cm;
	top: 4.4cm;
	width: 5.1cm;
	height: 0.7cm;
	border: 0.06cm black solid;
	font-size: 0.4cm;
	font-family: 굴림;
	text-align: center;
	vertical-align: middle;
	padding: 0.1cm;
}

div.notice {
	position: absolute;
	display: block;
	left: 0.35cm;
	top: 10.3cm;
	width: 5.2cm;
	height: 8cm;
	font-size: 0.35cm;
	font-family: 굴림;
}

 div.notice > h2 {
	position: relative;
	text-align: center;
	width: 5cm;
	padding: 0.1cm;
	border: 1px black solid;
 }

 div.notice > p {
 	position: relative;
 	text-align: left;
	padding: 0.2cm 0.1cm;
 }

 .bold {
 	font-weight: bold;
 }

 div.feeAmount {
	position: absolute;
	left: 7.35cm;
	top: 12.0cm;
	width: 4.4cm;
	height: 0.46cm;
	font-size: 0.4cm;
	font-family: 굴림;
	text-align: right;
 }

 div.feeDetail {
	position: absolute;
	left: 8.65cm;
	top: 12.7cm;
	width: 4.2cm;
	height: 4.8cm;
	font-size: 0.32cm;
	font-family: 굴림;
	padding: 0.1cm;
 }

 div.feeDetail > ul {
	position: relative;
	width: 100%;
 }

 div.feeDetail > ul > li {
	display: block;
	padding-bottom: 0.15cm;
 }

 div.rmk {
	position: absolute;
	display: block;
	left: 12.45cm;
	top: 11.3cm;
	width: 7.7cm;
	height: 8cm;
	font-size: 0.35cm;
	font-family: 굴림;
}

 div.rmk > h2 {
	position: relative;
	text-align: center;
	width: 100%;
	padding: 0.1cm;
	border: 1px black solid;
 }

 div.rmk > p {
	position: relative;
	text-align: left;
	padding: 0.1cm 0.1cm;
 }

 .summary {
	padding: 0.1cm 0.1cm;
 }

  div.giro {
	position: absolute;
	left: 5.8cm;
	top: 19.55cm;
	width: 14.8cm;
	height: 8.8cm;
	font-size: 0.4cm;
	font-family: 굴림;
 }

 #totalAmount {
	position: relative;
	left: 9.8cm;
	top: 1.35cm;
	width: 4.6cm;
	height: 0.65cm;
	font-size: 0.4cm;
	font-family: OCR-B-10 BT;
	text-align: right;
	padding: 0.1cm;
	text-align: right;
 }

 #elecPayNo {
	position: relative;
	left: 7.7cm;
	top: 3.7cm;
	width: 6.8cm;
	height: 0.8cm;
	font-size: 0.4cm;
	font-family: OCR-B-10 BT;
	text-align: left;
	padding: 0.15cm;
 }

 .girocode {
	position: relative;
	left: 0.8cm;
	top: 1.7cm;
	width: 13.2cm;
	height: 0.4cm;
	font-size: 0.35cm;
	font-family: OCR-B-1;
 }

 #girono {
	display: inline;
	position: absolute;
	left: 0.8cm;
 }

 #cstmrrefcode {
	display: inline;
	position: absolute;
	left: 4cm;
 }

 #giroamount {
	display: inline;
	position: absolute;
	left: 9.3cm;
 }

 #girocc {
	display: inline;
	position: absolute;
	left: 12.5cm;
 }

 .giroDetail {
	position: absolute;
	left: 2.8cm;
	top: 5.4cm;
	width: 11.9cm;
	height: 1.3cm;
	font-size: 0.35cm;
 }

 #girobizrno {
	position: absolute;
	left: 0cm;
	top: 0cm;
	width: 4.5cm;
	height: 0.5cm;
	padding: 0.04cm;
 }

 #nticDate {
	position: absolute;
	width: 4.5cm;
	height: 0.5cm;
	top: 0cm;
	left: 7cm;
	padding: 0.04cm;
 }

 #nticNo {
	position: absolute;
	left: 0cm;
	top: 0.44cm;
	width: 4.5cm;
	height: 0.4cm;
	padding: 0.04cm;
 }

 #payTmlmt {
	position: absolute;
	width: 4.5cm;
	height: 0.5cm;
	top: 0.44cm;
	left: 7cm;
	padding: 0.04cm;
 }

 #girormk {
 	position: absolute;
	left: 0cm;
	top: 0.85cm;
	width: 11.9cm;
	height: 0.4cm;
	padding: 0.04cm;
 }

 #noticermk {
	position: absolute;
	left: 1.2cm;
	top: 8cm;
	width: 11.9cm;
	height: 0.4cm;
	font-size: 0.3cm;
	font-family: 돋움;
	padding: 0.04cm;
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
	#printButton {
		position:fixed;
		left: 20px;
		top: 10px;
		display:none;
	}

	.page {
	    width: 21cm;
	    min-height: 29.7cm;
	    padding: 1cm;
	    margin: 0cm auto;
	    border: 1px #D3D3D3 solid;
	    border-radius: 5px;
	    -webkit-background-size: cover; /* For WebKit*/
	    -moz-background-size: cover;    /* Mozilla*/
	    -o-background-size: cover;      /* Opera*/
	    background-size: cover;         /* Generic*/
	    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
	}

	.subpage {
	    padding: 1cm;
	/*     border: 5px red solid;
	 */    height: 256mm;
	}

	div.sender {
		position: absolute;
		display: block;
		left: 2cm;
		top: 2.6cm;
		width: 10cm;
		height: 1.6cm;
		font-size: 0.35cm;
		font-family: 굴림;
	}

	#senderAddress {
		display: block;
		position: relative;
		padding: 0.2cm
	}

	#senderPostcode {
		display: block;
		position: relative;
		padding: 0.2cm
	}

	div.receiver {
		position: absolute;
		display: block;
		left: 8cm;
		top: 6cm;
		width: 11cm;
		height: 2.4cm;
		font-size: 0.35cm;
		font-family: 굴림;
		text-align: right;
	}

	#companyName {
		position: absolute;
		left: 0cm;
		top: 0cm;
		width: 8cm;
		height: 0.4cm;
		text-align: right;
		font-family: 굴림;
		font-size: 0.4cm;
	}

	#ceoName {
		position: absolute;
		left: 8cm;
		top: 0cm;
		width: 3cm;
		height: 0.4cm;
		text-align: right;
		font-family: 굴림;
		font-size: 0.4cm;
	}

	#recAddress {
		position: absolute;
		left: 0cm;
		top: 0.6cm;
		width: 11cm;
		height: 0.4cm;
		text-align: right;
	}

	#recPostcode {
		position: absolute;
		left: 0cm;
		top: 1cm;
		width: 11cm;
		height: 0.4cm;
		text-align: right;
	}

	div.letterMsg {
		position: absolute;
		display: block;
		left: 8.35cm;
		top: 4.4cm;
		width: 5.1cm;
		height: 0.7cm;
		border: 0.06cm black solid;
		font-size: 0.4cm;
		font-family: 굴림;
		text-align: center;
		vertical-align: middle;
		padding: 0.1cm;
	}

	div.notice {
		position: absolute;
		display: block;
		left: 0.35cm;
		top: 10.3cm;
		width: 5.2cm;
		height: 8cm;
		font-size: 0.35cm;
		font-family: 굴림;
	}

	 div.notice > h2 {
		position: relative;
		text-align: center;
		width: 5cm;
		padding: 0.1cm;
		border: 1px black solid;
	 }

	 div.notice > p {
	 	position: relative;
	 	text-align: left;
		padding: 0.2cm 0.1cm;
	 }

	 .bold {
	 	font-weight: bold;
	 }

	 div.feeAmount {
		position: absolute;
		left: 7.35cm;
		top: 12.0cm;
		width: 4.4cm;
		height: 0.46cm;
		font-size: 0.4cm;
		font-family: 굴림;
		text-align: right;
	 }

	 div.feeDetail {
		position: absolute;
		left: 8.65cm;
		top: 12.7cm;
		width: 4.2cm;
		height: 4.8cm;
		font-size: 0.32cm;
		font-family: 굴림;
		padding: 0.1cm;
	 }

	 div.feeDetail > ul {
		position: relative;
		width: 100%;
	 }

	 div.feeDetail > ul > li {
		display: block;
		padding-bottom: 0.15cm;
	 }

	 div.rmk {
		position: absolute;
		display: block;
		left: 12.45cm;
		top: 11.3cm;
		width: 7.7cm;
		height: 8cm;
		font-size: 0.35cm;
		font-family: 굴림;
	}

	 div.rmk > h2 {
		position: relative;
		text-align: center;
		width: 100%;
		padding: 0.1cm;
		border: 1px black solid;
	 }

	 div.rmk > p {
		position: relative;
		text-align: left;
		padding: 0.1cm 0.1cm;
	 }

	 .summary {
		padding: 0.1cm 0.1cm;
	 }

	  div.giro {
		position: absolute;
		left: 5.8cm;
		top: 19.55cm;
		width: 14.8cm;
		height: 8.8cm;
		font-size: 0.4cm;
		font-family: 굴림;
	 }

	 #totalAmount {
		position: relative;
		left: 9.8cm;
		top: 1.35cm;
		width: 4.6cm;
		height: 0.65cm;
		font-size: 0.4cm;
	font-family: OCR-B-10 BT;
		text-align: right;
		padding: 0.1cm;
		text-align: right;
	 }

	 #elecPayNo {
		position: relative;
		left: 7.7cm;
		top: 3.7cm;
		width: 6.8cm;
		height: 0.8cm;
		font-size: 0.4cm;
		font-family: OCR-B-10 BT;
		text-align: left;
		padding: 0.15cm;
	 }

	 .girocode {
		position: relative;
		left: 0.8cm;
		top: 1.7cm;
		width: 13.2cm;
		height: 0.4cm;
		font-size: 0.35cm;
		font-family: OCR-B-1;
	 }

	 #girono {
		display: inline;
		position: absolute;
		left: 0.8cm;
	 }

	 #cstmrrefcode {
		display: inline;
		position: absolute;
		left: 4cm;
	 }

	 #giroamount {
		display: inline;
		position: absolute;
		left: 9.3cm;
	 }

	 #girocc {
		display: inline;
		position: absolute;
		left: 12.5cm;
	 }

	 .giroDetail {
		position: absolute;
		left: 2.8cm;
		top: 5.4cm;
		width: 11.9cm;
		height: 1.3cm;
		font-size: 0.35cm;
	 }

	 #girobizrno {
		position: absolute;
		left: 0cm;
		top: 0cm;
		width: 4.5cm;
		height: 0.5cm;
		padding: 0.04cm;
	 }

	 #nticDate {
		position: absolute;
		width: 4.5cm;
		height: 0.5cm;
		top: 0cm;
		left: 7cm;
		padding: 0.04cm;
	 }

	 #nticNo {
		position: absolute;
		left: 0cm;
		top: 0.44cm;
		width: 4.5cm;
		height: 0.4cm;
		padding: 0.04cm;
	 }

	 #payTmlmt {
		position: absolute;
		width: 4.5cm;
		height: 0.5cm;
		top: 0.44cm;
		left: 7cm;
		padding: 0.04cm;
	 }

	 #girormk {
	 	position: absolute;
		left: 0cm;
		top: 0.85cm;
		width: 11.9cm;
		height: 0.4cm;
		padding: 0.04cm;
	 }

	 #noticermk {
		position: absolute;
		left: 1.2cm;
		top: 8cm;
		width: 11.9cm;
		height: 0.4cm;
		font-size: 0.3cm;
		font-family: 돋움;
		padding: 0.04cm;
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
  <c:if test="${resultCode==0 }">
  <fmt:parseDate value='${result.nticDt}' var='nticDate' pattern="yyyy-MM-dd" scope="page"/>
  <fmt:parseDate value='${result.payTmlmt}' var='payTmlmtDate' pattern="yyyy-MM-dd" scope="page"/>
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <c:forEach var="result" items="${resultList }">
    <div class="page">
        <div class="subpage">
			<div class="sender">
				<div id="senderAddress">
					전라남도 광양시 항만대로 465 월드마린센터 15층
				</div>
				<div id="senderPostcode">
					545-030
				</div>
			</div>
			<div class="receiver">
				<div id="companyName">
					<c:out value="${result.entrpsNm}"/>
				</div>
				<div id="ceoName">
					<c:out value="${result.rprsntvNm}"/>
				</div>
				<div id="recAddress">
					<c:out value="${result.adres}"/>
				</div>
				<div id="recPostcode">
					<c:out value="${fn:substring(result.zip, 0, 3)}"/>-<c:out value="${fn:substring(result.zip, 3, 6)}"/>
				</div>
			</div>
			<div class="letterMsg">고 지 서  재 중</div>
        	<div class="notice">
        		<h2>안내말씀</h2>
        		<p>* 전자 세금계산서는 LogisBill을 통하여 E-Mail로 발급됩니다.</p>
        		<p>* 납기가 지난 고지서는 납부 할 수 없습니다.</p>
        		<p>* 전자납부이용은 고지서를 발부 받으신 당일 19시 이후부터 인터넷 뱅킹 등을 이용하여 납부하실 수 있습니다.</p>
        		<p class="bold">* 금융기관에서는 온라인 수납처리 바랍니다.</p>
       		</div>
       		<div class="feeAmount"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.nticAmt}" /></div>
      		<div class="feeDetail">
      			<ul>
      				<li class="cstmrRefNo"><c:out value="${fn:substring(result.cstmrRefCode, 0,6)}"/>&nbsp;<c:out value="${fn:substring(result.cstmrRefCode, 6,10)}"/>&nbsp;<c:out value="${fn:substring(result.cstmrRefCode, 10,17)}"/></li>
      				<li class="chrgeKndNm"><c:out value="${result.chrgeKndNm}"/></li>
      				<li class="entrpsNm"><c:out value="${result.entrpsNm}"/></li>
      				<li class="entrpsNm"><c:out value="${result.rprsntvNm}"/></li>
      				<li class="nticDate"><fmt:formatDate value="${nticDate}" pattern="yyyy년 MM월 dd일"/></li>
      				<li class="payTmlmt"><fmt:formatDate value="${payTmlmtDate}" pattern="yyyy년 MM월 dd일"/></li>
      				<li class="bizrNo"><c:out value="${fn:substring(result.bizrno, 0,3)}"/>-**-*****</li>
   				</ul>
      		</div>
      		<div class="rmk">
      			<h2>부과내역</h2>
      			<p>시설명 : <c:out value="${result.gisAssetsNm}"/></p>
      			<p>소재지 : <c:out value="${result.gisAssetsLocplc}"/>&nbsp;<c:out value="${result.gisAssetsLnm}"/><c:if test="${result.gisAssetsLnmSub!=null}">-<c:out value="${result.gisAssetsLnmSub}"/></c:if></p>
      			<p>업 체 : <c:out value="${result.entrpsNm}"/> (<c:out value="${result.entrpscd}"/>)</p>
      			<p>면 적 : <fmt:formatNumber type="number" maxIntegerDigits="10" value="${result.grAr}" /> m<sup>2</sup></p>
      			<h2>산출근거</h2>
      			<p><c:out value="${result.chrgeKndNm}"/> : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${result.fee}" /> 원</p>
      			<p>산출공식 : <c:out value="${result.computDtls}"/></p>
      			<c:if test="${result.feeA3>0}">
	      			<p>분할납부이자 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.feeA3}" /> 원</p>
      			</c:if>
      			<c:if test="${result.feeA4>0}">
	      			<p>관리비 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.feeA4}" /> 원</p>
      			</c:if>
      			<c:if test="${result.feeD1>0}">
	      			<p>과태료 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.feeD1}" /> 원</p>
      			</c:if>
      			<p class="summary">소 계 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.fee+result.feeA3+result.feeA4+result.feeD1}" /> 원</p>
      			<p class="summary">부가세(<c:out value="${result.taxtSeNm}"/>) : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.vat}" /> 원</p>
      			<p class="summary">합 계 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.nticAmt}" /> 원</p>
      		</div>
			<div class="giro">
	       		<div id="totalAmount"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.nticAmt}" /></div>
	       		<div id="elecPayNo"><c:out value="${result.elecPayNo}"/></div>
	       		<div class="girocode">
	       			<div id="girono">&lt;${result.giroNo}+</div>
		       		<div id="cstmrrefcode">+<c:out value="${result.cstmrRefCode}"/>+</div>
		       		<div id="giroamount">+<c:out value="${result.elecNticAmt}"/>&lt;</div>
		       		<div id="girocc">&lt;11&lt;</div>
	       		</div>
	       		<div class="giroDetail">
		       		<div id="girobizrno"><c:out value="${fn:substring(result.bizrno, 0,3)}"/>-**-*****</div>
      				<div id="nticno"><c:out value="${result.prtAtCode}"/>-<c:out value="${result.chrgeKnd}"/>-<c:out value="${result.accnutYear}"/>-<c:out value="${result.nticCnt}"/></div>
      				<div id="nticDate"><fmt:formatDate value="${nticDate}" pattern="yyyy년 MM월 dd일"/></div>
      				<div id="payTmlmt"><fmt:formatDate value="${payTmlmtDate}" pattern="yyyy년 MM월 dd일"/></div>
      				<div id="girormk">항코드 : <c:out value="${result.prtAtcode}"/> 관리번호 : <c:out value="${result.mngYear}"/>-<c:out value="${result.mngNo}"/>-<c:out value="${result.mngCnt}"/> 고지회차 : <c:out value="${result.nticCnt}"/>회차</div>
	       		</div>
	       		<div id="noticermk">납기가 지난 고지서는 납부할 수 없으며, 금융기관에서는 온라인 수납처리 바랍니다.</div>
			</div>
        </div>
    </div>
    </c:forEach>
</div>
  </c:if>
    <c:if test="${resultCode!=0 }">
    	<h2>인쇄 할 대상이 존재하지 않습니다.</h2>
    </c:if>

  </body>
</html>