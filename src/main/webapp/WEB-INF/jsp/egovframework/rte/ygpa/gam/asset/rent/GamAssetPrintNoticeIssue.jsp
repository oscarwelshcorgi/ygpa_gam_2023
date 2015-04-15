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

.book {
	margin: 0px;
	padding: 0px;
}

.page {
    width: 21cm;
    min-height: 28.7cm;
    padding: 0cm;
    margin: 0cm auto;
/*     border: 1px #D3D3D3 solid;
    border-radius: 5px; */
    background-image:url('<c:url value="/images/egovframework/ygpa/gam/misc/giro_page.png" />');
    -webkit-background-size: cover; /* For WebKit*/
    -moz-background-size: cover;    /* Mozilla*/
    -o-background-size: cover;      /* Opera*/
    background-size: cover;         /* Generic*/
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

.subpage {
    padding: 0cm;
/*     border: 5px red solid;
 */    height: 297mm;
}

.subpage1 {
	position: relative;
	left: 0cm;
	top: 0cm;
	height: 9.7cm;
}

.subpage2 {
	position: relative;
	left: 0cm;
	top: 0cm;
	height: 9.8cm;
}

.subpage3 {
	position: relative;
	left: 0cm;
	top: 0cm;
	height: 8.9cm;
}

div.sender {
	position: absolute;
	display: block;
	left: 2cm;
	top: 2.5cm;
	width: 10cm;
	height: 1.6cm;
	font-size: 0.35cm;
	font-family: 돋움;
}

#senderAddress {
	display: block;
	position: absolute;
	padding: 0.2cm
}

#senderPostcode {
	display: block;
	position: absolute;
	padding: 0.8cm
}

div.receiver {
	position: absolute;
	display: block;
	left: 8cm;
	top: 6cm;
	width: 11cm;
	height: 2.4cm;
	font-size: 0.35cm;
	font-family: 돋움;
	text-align: right;
}

#companyName {
	position: absolute;
	left: 0cm;
	top: 0cm;
	width: 8cm;
	height: 0.4cm;
	text-align: right;
	font-family: 돋움;
	font-size: 0.4cm;
}

#ceoName {
	position: absolute;
	left: 8cm;
	top: 0cm;
	width: 3cm;
	height: 0.4cm;
	text-align: right;
	font-family: 돋움;
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
	top: 1.6cm;
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
	font-family: 돋움;
	text-align: center;
	vertical-align: middle;
	padding: 0.1cm;
}

div.notice {
	position: absolute;
	display: block;
	left: 0.35cm;
	top: 0.5cm;
	width: 5.2cm;
	height: 8cm;
	font-size: 0.35cm;
	font-family: 돋움;
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
	top: 2.3cm;
	width: 4.4cm;
	height: 0.46cm;
	font-size: 0.4cm;
	font-family: 돋움;
	text-align: right;
 }

 div.feeDetail {
	position: absolute;
	left: 8.55cm;
	top: 3.05cm;
	width: 3.3cm;
	height: 4.8cm;
	font-size: 0.3cm;
	font-family: 돋움;
	padding: 0.1cm;
 }

 div.feeDetail > ul {
	position: relative;
	width: 100%;
 }

 div.feeDetail > ul > li {
	display: block;
	padding-bottom: 0.15cm;
	white-space: nowrap;
	height: 0.5cm;
 }

 div.rmk {
	position: absolute;
	display: block;
	left: 12.5cm;
	top: 1.5cm;
	width: 7.7cm;
	height: 8cm;
	font-size: 0.35cm;
	font-family: 돋움;
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
	top: 0cm;
	width: 14.8cm;
	height: 8.8cm;
	font-size: 0.4cm;
	font-family: 돋움;
 }

 #totalAmount {
	position: absolute;
	left: 9.8cm;
	top: 1.5cm;
	width: 4.6cm;
	height: 0.65cm;
	font-size: 0.4cm;
	font-family: OCR-B-10 BT;
	text-align: right;
	padding: 0.1cm;
 }

 #elecPayNo {
	position: absolute;
	left: 7.8cm;
	top: 4.4cm;
	width: 6.8cm;
	height: 0.8cm;
	font-size: 0.4cm;
	font-family: OCR-B-10 BT;
	text-align: left;
	padding: 0.15cm;
 }

 .girocode {
	position: absolute;
	left: 0.7cm;
	top: 3.2cm;
	width: 13.2cm;
	height: 0.4cm;
	font-size: 0.4cm;
	font-family: OCR-B-10 BT;
 }

 #girono {
	display: inline;
	position: absolute;
	left: 0.8cm;
 }

 #cstmrrefcode {
	display: inline;
	position: absolute;
	left: 3.7cm;
 }

 #giroamount {
	display: inline;
	position: absolute;
	left: 9.6cm;
	width: 2.6cm;
	text-align: right;
 }

 #girocc {
	display: inline;
	position: absolute;
	left: 12.5cm;
 }

 .giroDetail {
	position: absolute;
	left: 2.85cm;
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
	z-index: 999;
}

@page {
    size: A4;
    margin: 0;
}
@media print {
	#printButton {
		display:none;
	}

	.page {
	    width: 21cm;
	    height: 28.7cm;
	    padding: 0cm;
	    margin: 0cm auto;
	    background: none;
	    page-break-after: always;
	}

	.subpage {
	     height: 287mm;
	}

	.subpage1 {
		height: 9.7cm;
	}

	.subpage2 {
		height: 9.8cm;
	}

	.subpage3 {
		height: 8.9cm;
	}

	div.sender {
		left: 2cm;
		top: 2.5cm;
	}

	#senderAddress {
		padding: 0.2cm
	}

	#senderPostcode {
		padding: 0.8cm
	}

	div.receiver {
		left: 8cm;
		top: 6cm;
	}

	#companyName {
		left: 0cm;
		top: 0cm;
	}

	#ceoName {
		left: 8cm;
		top: 0cm;
	}

	#recAddress {
		left: 0cm;
		top: 0.6cm;
	}

	#recPostcode {
		left: 0cm;
		top: 1.4cm;
	}

	div.letterMsg {
		left: 8.35cm;
		top: 4.4cm;
	}

	div.notice {
		left: 0.4cm;
		top: 0.5cm;
		width: 4.9cm;
		height: 9cm;
	}

	 div.notice > h2 {
		width: 4.9cm;
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
		left: 7.25cm;
		top: 2.25cm;
	 }

	 div.feeDetail {
		left: 8.6cm;
		top: 3cm;
		width: 3.3cm;
		height: 4.8cm;
		padding: 0.1cm;
	 }

	 div.feeDetail > ul {
		position: relative;
		width: 100%;
	 }

	 div.feeDetail > ul > li {
		display: block;
		padding-bottom: 0.15cm;
		white-space: nowrap;
		height: 0.5cm;
	 }

	 div.rmk {
		left: 12.4cm;
	}

	 .summary {
		padding: 0.1cm 0.1cm;
	 }

	  div.giro {
		left: 5.8cm;
		top: 0cm;
		width: 14.8cm;
		height: 8.8cm;
	 }

	 #totalAmount {
		left: 9.8cm;
		top: 1.35cm;
	 }

	 #elecPayNo {
		top: 4.2cm;
	 }

	 .girocode {
		position: absolute;
		left: 0.55cm;
		top: 3.05cm;
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
		top: 5.2cm;
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
		left: 1.4cm;
		top: 7.8cm;
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
			var vo = [
			          {name : 'prtAtCode', value: '<c:out value="${result.prtAtCode}"/>'},
			          {name : 'mngYear', value: '<c:out value="${result.mngYear}"/>'},
			          {name : 'mngNo', value: '<c:out value="${result.mngNo}"/>'},
			          {name : 'mngCnt', value: '<c:out value="${result.mngCnt}"/>'},
			          {name : 'nticCnt', value: '<c:out value="${result.nticCnt}"/>'},
			          {name : 'chrgeKnd', value: '<c:out value="${result.chrgeKnd}"/>'}
			          ];

			$.ajax({
				url: $('#printUrl').val(),
				type: 'POST',
				module: this,
				dataType: 'json',
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				data: vo
				}).done(function(data) {
	                if(data.resultCode=='0') {
	        			window.print();
	                }
	                else alert(data.resultMsg);
			});
		});
	});
	</script>
  </head>
  <body>
  <c:if test="${resultCode==0 }">
  <c:if test="${arrrgItem!=null }">
  	<input id="printUrl" type="hidden" value="<c:url value='/asset/rent/printRentFeeSepNoticeIssue.do' />"/>
  </c:if>
  <c:if test="${arrrgItem==null }">
  	<input id="printUrl" type="hidden" value="<c:url value='/asset/rent/printRentFeeNoticeIssue.do' />"/>
  </c:if>
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <div class="page">
        <div class="subpage">
        	<div class="subpage1">
				<div class="sender">
					<div id="senderAddress">
						전라남도 광양시 항만대로 465 월드마린센터 1층
					</div>
					<div id="senderPostcode">
						545-030
					</div>
				</div>
				<div class="letterMsg">고 지 서  재 중</div>
				<div class="receiver">
					<div id="companyName">
						<c:out value="${result.entrpsNm}"/>
					</div>
					<div id="ceoName">
						<c:out value="${result.rprsntvNm}"/>
					</div>
					<div id="recAddress">
						<c:out value="${result.adres}"/>
						<c:out value="${result.adres2}"/>
					</div>
					<div id="recPostcode">
						<c:out value="${fn:substring(result.zip, 0, 3)}"/>-<c:out value="${fn:substring(result.zip, 3, 6)}"/>
					</div>
				</div>
			</div>
			<div class="subpage2">
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
	      				<li class="nticDate"><c:out value="${result.nticDt}"/></li>
	      				<li class="payTmlmt"><c:out value="${result.payTmlmt}"/></li>
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
	      			<c:if test="${result.feeA3>0}">
		      			<p>분할납부이자 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.feeA3}" /> 원</p>
	      			</c:if>
	      			<c:if test="${result.feeA4>0}">
		      			<p>관리비 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.feeA4}" /> 원</p>
	      			</c:if>
	      			<c:if test="${result.reimFee>0}">
		      			<p>변상금 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.reimFee}" /> 원</p>
	      			</c:if>
	      			<c:if test="${result.feeD1>0}">
		      			<p>과태료 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.feeD1}" /> 원</p>
	      			</c:if>
	      			<c:if test="${result.arrrgAmt>0}">
		      			<p>연체료 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.arrrgAmt}" /> 원 (연체일 : <c:out value="${result.arrrgPayDates}"/>일)</p>
	      			</c:if>
	      			<p class="summary">소 계 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.fee+result.feeA3+result.feeA4+result.feeD1+result.arrrgAmt}" /> 원</p>
	      			<p class="summary">부가세(<c:out value="${result.taxtSeNm}"/>) : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.vat}" /> 원</p>
	      			<p class="summary">합 계 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.nticAmt}" /> 원</p>
	      		</div>
	   		</div>
	   		<div class="subpage3">
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
	      				<div id="nticDate"><c:out value="${result.nticDt}"/></div>
	      				<div id="payTmlmt"><c:out value="${result.payTmlmt}"/></div>
	      				<div id="girormk">항코드 : <c:out value="${result.prtAtcode}"/> 관리번호 : <c:out value="${result.mngYear}"/>-<c:out value="${result.mngNo}"/>-<c:out value="${result.mngCnt}"/> 고지회차 : <c:out value="${result.nticCnt}"/>회차</div>
		       		</div>
		       		<div id="noticermk">납기가 지난 고지서는 납부할 수 없으며, 금융기관에서는 온라인 수납처리 바랍니다.</div>
				</div>
			</div>
        </div>
    </div>
    <c:if test="${arrrgItem!=null }">
        <div class="page">
        <div class="subpage">
        	<div class="subpage1">
				<div class="sender">
					<div id="senderAddress">
						전라남도 광양시 항만대로 465 월드마린센터 1층
					</div>
					<div id="senderPostcode">
						545-030
					</div>
				</div>
				<div class="letterMsg">고 지 서  재 중</div>
				<div class="receiver">
					<div id="companyName">
						<c:out value="${arrrgItem.entrpsNm}"/>
					</div>
					<div id="ceoName">
						<c:out value="${arrrgItem.rprsntvNm}"/>
					</div>
					<div id="recAddress">
						<c:out value="${arrrgItem.adres}"/>
						<c:out value="${arrrgItem.adres2}"/>
					</div>
					<div id="recPostcode">
						<c:out value="${fn:substring(arrrgItem.zip, 0, 3)}"/>-<c:out value="${fn:substring(arrrgItem.zip, 3, 6)}"/>
					</div>
				</div>
			</div>
			<div class="subpage2">
	        	<div class="notice">
	        		<h2>안내말씀</h2>
	        		<p>* 전자 세금계산서는 LogisBill을 통하여 E-Mail로 발급됩니다.</p>
	        		<p>* 납기가 지난 고지서는 납부 할 수 없습니다.</p>
	        		<p>* 전자납부이용은 고지서를 발부 받으신 당일 19시 이후부터 인터넷 뱅킹 등을 이용하여 납부하실 수 있습니다.</p>
	        		<p class="bold">* 금융기관에서는 온라인 수납처리 바랍니다.</p>
	       		</div>
	       		<div class="feeAmount"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${arrrgItem.nticAmt}" /></div>
	      		<div class="feeDetail">
	      			<ul>
	      				<li class="cstmrRefNo"><c:out value="${fn:substring(arrrgItem.cstmrRefCode, 0,6)}"/>&nbsp;<c:out value="${fn:substring(arrrgItem.cstmrRefCode, 6,10)}"/>&nbsp;<c:out value="${fn:substring(arrrgItem.cstmrRefCode, 10,17)}"/></li>
	      				<li class="chrgeKndNm"><c:out value="${arrrgItem.chrgeKndNm}"/></li>
	      				<li class="entrpsNm"><c:out value="${arrrgItem.entrpsNm}"/></li>
	      				<li class="entrpsNm"><c:out value="${arrrgItem.rprsntvNm}"/></li>
	      				<li class="nticDate"><c:out value="${arrrgItem.nticDt}"/></li>
	      				<li class="payTmlmt"><c:out value="${arrrgItem.payTmlmt}"/></li>
	      				<li class="bizrNo"><c:out value="${fn:substring(arrrgItem.bizrno, 0,3)}"/>-**-*****</li>
	   				</ul>
	      		</div>
	      		<div class="rmk">
	      			<h2>부과내역</h2>
	      			<p>시설명 : <c:out value="${arrrgItem.gisAssetsNm}"/></p>
	      			<p>소재지 : <c:out value="${arrrgItem.gisAssetsLocplc}"/>&nbsp;<c:out value="${arrrgItem.gisAssetsLnm}"/><c:if test="${arrrgItem.gisAssetsLnmSub!=null}">-<c:out value="${arrrgItem.gisAssetsLnmSub}"/></c:if></p>
	      			<p>업 체 : <c:out value="${arrrgItem.entrpsNm}"/> (<c:out value="${arrrgItem.entrpscd}"/>)</p>
	      			<p>면 적 : <fmt:formatNumber type="number" maxIntegerDigits="10" value="${arrrgItem.grAr}" /> m<sup>2</sup></p>
	      			<h2>산출근거</h2>
	      			<p><c:out value="${arrrgItem.chrgeKndNm}"/> : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${arrrgItem.fee}" /> 원</p>
	      			<c:if test="${arrrgItem.feeA3>0}">
		      			<p>분할납부이자 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${arrrgItem.feeA3}" /> 원</p>
	      			</c:if>
	      			<c:if test="${arrrgItem.feeA4>0}">
		      			<p>관리비 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${arrrgItem.feeA4}" /> 원</p>
	      			</c:if>
	      			<c:if test="${arrrgItem.reimFee>0}">
		      			<p>변상금 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${arrrgItem.reimFee}" /> 원</p>
	      			</c:if>
	      			<c:if test="${arrrgItem.feeD1>0}">
		      			<p>과태료 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${arrrgItem.feeD1}" /> 원</p>
	      			</c:if>
	      			<c:if test="${arrrgItem.arrrgAmt>0}">
		      			<p>연체료 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${arrrgItem.arrrgAmt}" /> 원 (연체일 : <c:out value="${arrrgItem.arrrgPayDates}"/>일)</p>
	      			</c:if>
	      			<p class="summary">소 계 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${arrrgItem.fee+arrrgItem.feeA3+arrrgItem.feeA4+arrrgItem.feeD1+arrrgItem.arrrgAmt}" /> 원</p>
	      			<p class="summary">부가세(<c:out value="${arrrgItem.taxtSeNm}"/>) : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${arrrgItem.vat}" /> 원</p>
	      			<p class="summary">합 계 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${arrrgItem.nticAmt}" /> 원</p>
	      		</div>
	   		</div>
	   		<div class="subpage3">
				<div class="giro">
		       		<div id="totalAmount"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${arrrgItem.nticAmt}" /></div>
		       		<div id="elecPayNo"><c:out value="${arrrgItem.elecPayNo}"/></div>
		       		<div class="girocode">
		       			<div id="girono">&lt;${arrrgItem.giroNo}+</div>
			       		<div id="cstmrrefcode">+<c:out value="${arrrgItem.cstmrRefCode}"/>+</div>
			       		<div id="giroamount">+<c:out value="${arrrgItem.elecNticAmt}"/>&lt;</div>
			       		<div id="girocc">&lt;11&lt;</div>
		       		</div>
		       		<div class="giroDetail">
			       		<div id="girobizrno"><c:out value="${fn:substring(arrrgItem.bizrno, 0,3)}"/>-**-*****</div>
	      				<div id="nticno"><c:out value="${arrrgItem.prtAtCode}"/>-<c:out value="${arrrgItem.chrgeKnd}"/>-<c:out value="${arrrgItem.accnutYear}"/>-<c:out value="${arrrgItem.nticCnt}"/></div>
	      				<div id="nticDate"><c:out value="${arrrgItem.nticDt}"/></div>
	      				<div id="payTmlmt"><c:out value="${arrrgItem.payTmlmt}"/></div>
	      				<div id="girormk">항코드 : <c:out value="${arrrgItem.prtAtCode}"/> 관리번호 : <c:out value="${arrrgItem.mngYear}"/>-<c:out value="${arrrgItem.mngNo}"/>-<c:out value="${arrrgItem.mngCnt}"/> 고지회차 : <c:out value="${arrrgItem.nticCnt}"/>회차</div>
		       		</div>
		       		<div id="noticermk">납기가 지난 고지서는 납부할 수 없으며, 금융기관에서는 온라인 수납처리 바랍니다.</div>
				</div>
			</div>
        </div>
    </div>
    </c:if>
</div>
  </c:if>
    <c:if test="${resultCode!=0 }">
    	<h2>인쇄 할 대상이 존재하지 않습니다.</h2>
    </c:if>
  </body>
</html>