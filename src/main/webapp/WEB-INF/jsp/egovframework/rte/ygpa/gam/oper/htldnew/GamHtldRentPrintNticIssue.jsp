<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamHtldPrintNticIssue.jsp
  * @Description : 고지서 출력 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2016.06.19  Jongmin          최초 생성
  *
  * author Jongmin
  * since 2016.06.19
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
	left: -1mm;
	top: 0cm;
	height: 9.8cm;
}

.subpage3 {
	position: relative;
	left: -1mm;
	top: -0.5mm;
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
	top: 0.07cm;
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
	font-family: OCR-B-1;
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
	font-family: OCR-B-1;
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

	.notprint {
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
		left: -1mm;
		height: 9.8cm;
	}

	.subpage3 {
		left: -1mm;
		top: -0.5mm;
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
		top: 0.07cm;
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
	  	  var leftPrintPadding="leftPrintPadding";
	  	  var topPrintPadding="topPrintPadding";
	  	  var leftPrintPad="";
	  	  var topPrintPad="";

	    var ca = document.cookie.split(';');
	    for(var i=0; i<ca.length; i++) {
	        var c = ca[i];
	        while (c.charAt(0)==' ') c = c.substring(1);
	        if (c.indexOf(leftPrintPadding) != -1) leftPrintPad=c.substring(leftPrintPadding.length+1,c.length).split(";")[0];
	        if (c.indexOf(topPrintPadding) != -1) topPrintPad=c.substring(topPrintPadding.length+1,c.length).split(";")[0];
	    }

	    $('#leftPrintPadding').val(leftPrintPad);
	    $('#topPrintPadding').val(topPrintPad);

	    $('#leftPrintPadding').change(function() {
			var d = new Date();
			d.setFullYear(d.getFullYear()+5);
			d.setHours(0);
		  	document.cookie = "leftPrintPadding="+$(this).val()+"; expires=" +d.toUTCString();
	    });
	    $('#topPrintPadding').change(function() {
			var d = new Date();
			d.setFullYear(d.getFullYear()+5);
			d.setHours(0);
		  	document.cookie = "topPrintPadding="+$(this).val()+"; expires=" +d.toUTCString();
	    });

		$('#printButton').button().click(function(){
			var vo = [
			          {name : 'accnutYear', value: '<c:out value="${searchVO.accnutYear}"/>'},
			          {name : 'rntfeeNticNo', value: '<c:out value="${searchVO.rntfeeNticNo}"/>'},
			          {name : 'nticSeq', value: '<c:out value="${searchVO.nticSeq}"/>'},
			          {name : 'dlySerNo', value: '<c:out value="${searchVO.dlySerNo}"/>'},
			          {name : 'nticNo', value: '<c:out value="${master.billNo}"/>'},
			          {name : 'chrgeKndCd', value: '<c:out value="${master.chrgeKndCd}"/>'},
			          {name : 'nticDt', value: '<c:out value="${master.nticDt}"/>'},
			          ];
			var leftPadding = ""+(Number($('#leftPrintPadding').val()||0))/10;
			var topPadding = ""+(Number($('#topPrintPadding').val()||0))/10;
			$('.subpage').css('padding-left', leftPadding+"cm");
			$('.subpage').css('padding-top', topPadding+"cm");
 			
			$.ajax({
				url: '<c:url value="/oper/htldnew/printProcessHtldNticIssue.do"/>',
				type: 'POST',
				module: this,
				dataType: 'json',
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				data: vo
				}).done(function(data) {
	                if(data.resultCode=='0') {
	        			window.print();
	        			$('.subpage').css('padding-left', "0cm");
	        			$('.subpage').css('padding-top', "0cm");
	                }
	                else alert(data.resultMsg);
			});
/* 			
			window.print();
			$('.subpage').css('padding-left', "0cm");
			$('.subpage').css('padding-top', "0cm");
 */
		});
		
		/*
	 		일시 : 2018.06.07
			요청자 : 항만운영팀 조현성
			내용 : 본사/사업소 주소 표기시 선택하여 적용되도록 개선
			처리 : 본사/사업소 선택 select 컴포넌트 추가
	  	*/
		//여수, 광양 selectBox체인지 이벤트
		$('#sPrtAtCode').change(function(){

			var sPrtAtCode = $('#sPrtAtCode').val();
			var text1 = "";
			var text2 = "";

			if(sPrtAtCode == '001'){//여수
				text1 = "전라남도 여수시 봉계4길 10 (여수사업소 1층)";
				text2 = "59631";
			}else if(sPrtAtCode == '002'){//광양
				text1 = "전라남도 광양시 항만대로 465 월드마린센터 1층";
				text2 = "57771";
			}

			$('#senderAddress').text(text1);
			$('#senderPostcode').text(text2);

		});

	});
	</script>
  </head>
  <body>
  <c:if test="${resultCode==0 }">
  <fmt:parseDate value='${master.nticDt}' var='nticDate' pattern="yyyy-MM-dd" scope="page"/>
  <fmt:parseDate value='${master.payTmlmt}' var='payTmlmtDate' pattern="yyyy-MM-dd" scope="page"/>
  <a id="printButton" href="#">인쇄</a>
<div class="book">
    <div class="page">
        <div class="subpage">
        <input id='leftPrintPadding' type="text" value="" class="notprint" style="position:fixed; left: 20px; top: 60px; width: 120px; z-index: 999;" placeholder="가로 보정치 mm">
        <input id='topPrintPadding' type="text" value="" class="notprint" style="position:fixed; left: 20px; top: 90px; width: 120px; z-index: 999;" placeholder="세로 보정치 mm">

        <!--
        	일시 : 2018.06.07
       		요청자 : 항만운영팀 조현성
       		내용 : 본사/사업소 주소 표기시 선택하여 적용되도록 개선
       		처리 : 본사/사업소 선택 select 컴포넌트 추가
         
        <select id="sPrtAtCode" style="position:fixed; left: 20px; top: 120px; width: 120px; z-index: 999;" class="notprint">
			<option value="001" >여수</option>
			<option value="002" selected="selected">광양</option>
		</select>
		-->


        	<div class="subpage1">
				<div class="sender">
					<div id="senderAddress">
						전남 광양시 항만대로 465 월드마린센터 11층 물류단지부
					</div>
					<div id="senderPostcode">
						57771
					</div>
				</div>
				<div class="letterMsg">고 지 서  재 중</div>
				<div class="receiver">
					<div id="companyName">
						<c:out value="${master.agentName}"/>
					</div>
					<div id="ceoName">
						<c:out value="${master.rprsntvNm}"/>
					</div>
					<div id="recAddress">
						<c:out value="${master.adres}"/>&nbsp;<c:out value="${master.adres2}"/>
					</div>
					<div id="recPostcode">
						<c:out value="${fn:substring(master.zip, 0, 3)}"/><c:out value="${fn:substring(master.zip, 3, 6)}"/>
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
	       		<div class="feeAmount"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${master.supAmt+master.vat}" /></div>
	      		<div class="feeDetail">
	      			<ul>
	      				<li class="cstmrRefNo"><c:out value="${fn:substring(master.cstmrRefCode, 0,6)}"/>&nbsp;<c:out value="${fn:substring(master.cstmrRefCode, 6,10)}"/>&nbsp;<c:out value="${fn:substring(master.cstmrRefCode, 10,17)}"/></li>
	      				<li class="chrgeKndNm"><c:out value="${master.chrgeKndNm}"/></li>
	      				<li class="entrpsNm"><c:out value="${master.agentName}"/></li>
	      				<li class="entrpsNm"><c:out value="${master.rprsntvNm}"/></li>
	      				<li class="nticDate"><c:out value="${master.nticDt}"/></li>
	      				<li class="payTmlmt"><c:out value="${master.dueDate}"/></li>
	      				<li class="bizrNo"><c:out value="${fn:substring(master.bzRgstId, 0,3)}"/>-**-*****</li>
	   				</ul>
	       		</div>
	      		<div class="rmk">
	      			<h2>부과내역</h2>
		      			<c:if test="${ master.arrrgNo eq '00'}">
		      			<p>공급가액 : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${master.supAmt}" /> 원</p>
		      			<p>부가세(매출과세(일반)) : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${master.vat}" /> 원</p>
		      			</c:if>
		      			<c:if test="${ master.arrrgNo ne '00'}">
		      				<p>연체료 : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${master.arrrgAmt}" /> 원</p>
		      			</c:if>
		      			<p>합계 : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${master.supAmt+master.vat}" /> 원</p>
	      			<h2>산출근거</h2>
		      			<p> * 사용기간 : <c:out value="${master.nticPdFrom}"/> ~ <c:out value="${master.nticPdTo}"/> </p>
		      			<p> * 공급가액 산출근거
		      				<c:if test="${master.paySe == 4}">
		      					<c:if test="${master.quarter != 4}">
		      						(분납 이자율 : COFIX기준금리 <c:out value="${master.intrRate}"/>% 적용 )
		      					</c:if>
		      				</c:if>
		      			</p>
		      			<c:set var="nItemCount" value="0" />
		      			<c:set var="nItemPrintCount" value="0" />
		      			<c:forEach var="detailItem" items="${detailList}" varStatus="itemStatus">
		      				<c:set var="nItemCount" value="${itemStatus.count}" />
		      				<c:if test="${nItemCount le 3 }">
		      					<c:set var="nItemPrintCount" value="${nItemPrintCount + 1}" />
				      			<c:if test="${detailItem.rntfeeSe eq 0}">
									<p>
											소재지 : <c:out value="${detailItem.gisAssetsLocplc}"/>&nbsp;<c:out value="${detailItem.gisAssetsLnm}"/>
												<c:if test="${detailItem.gisAssetsLnmSub!=null}">-<c:out value="${detailItem.gisAssetsLnmSub}"/></c:if>
												<br/>
											면적 : <c:out value="${detailItem.rentArStr }"/>
												<c:if test="${!(detailItem.rentArSe eq '3')}"> m <sup>2</sup></c:if>
												&nbsp;&nbsp;
											적용단가 : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${detailItem.applcRntfee}" />
												<c:if test="${detailItem.priceSe eq '1' }">원</c:if>
												<c:if test="${detailItem.priceSe eq '2' }">원/월</c:if>
												<c:if test="${detailItem.applcRntfeeSe eq '1'}">(실적)</c:if>
												<br/>
											임대료 : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${detailItem.rntfee}" />원
												&nbsp;&nbsp;
											<c:if test="${ (detailItem.paySe eq '4') && (master.quarter != 4) }">
												분납이자 : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${detailItem.payinstIntr}" />원
											</c:if>
									</p>
				      			</c:if>
				      			<c:if test="${(detailItem.rntfeeSe eq 1) or (detailItem.rntfeeSe eq 2) }">
				      				<p>
				      					<c:out value="${detailItem.rntfeeSeNm}" /> ( <c:out value="${detailItem.applcBeginDt }"/> ~ <c:out value="${detailItem.applcEndDt }"/> )
				      					<br />
				      					<c:if test="${ detailItem.rntfeeSe eq 1 }">
				      					적용면적 : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${detailItem.applcRentAr}" /> m <sup>2</sup>
				      					</c:if>
				      					<c:if test="${ detailItem.rntfeeSe eq 2 }">
				      					변동면적 : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${detailItem.applcRentAr}" /> m <sup>2</sup>
				      					</c:if>
				      					&nbsp;&nbsp;
				      					적용단가 : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${detailItem.appRntfee}" /> 원
				      					<br />
				      					임대료 : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${detailItem.rntfee}" /> 원
				      				</p>
				      			</c:if>
				      			<c:if test="${detailItem.rntfeeSe eq 3}">
				      				<p>
				      					<c:out value="${detailItem.rntfeeSeNm}" /> : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${detailItem.rntfee}" /> 원
				      				</p>
				      			</c:if>
				      		</c:if>
			      		</c:forEach>
			      		<c:if test="${ nItemCount gt nItemPrintCount}">
			      			<p> (외 <c:out value="${(nItemCount - 3)}"/> 건)</p>
			      		</c:if>
			      		<c:if test="${ master.arrrgNo ne '00'}">
			      			<p> * 연체료 산출근거 (공급가액 기준) <br/>
			      			<c:set var="newline" value="<%= \"\n\" %>" />

			      				<c:out value="${fn:replace(master.dlyBillRsn, newline, '<br />')}" escapeXml="false"/> <br/>
			      			</p>
			      		</c:if>

			      		<c:if test="${master.rm!=null}">
	      					<p> * 비고 : <c:out value="${master.rm}"/></p>
	      				</c:if>
	      		</div>
        	</div>
        	<div class="subpage3">
				<div class="giro">
		       		<div id="totalAmount"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${master.supAmt+master.vat}" /></div>
		       		<div id="elecPayNo"><c:out value="${master.elecPayNo}"/></div>
		       		<div class="girocode">
		       			<div id="girono">&lt;${master.giroNo}+</div>
			       		<div id="cstmrrefcode">+<c:out value="${master.cstmrRefCode}"/>+</div>
			       		<div id="giroamount">+<c:out value="${master.elecNticAmt}"/>&lt;</div>
			       		<div id="girocc">&lt;11&lt;</div>
		       		</div>
		       		<div class="giroDetail">
			       		<div id="girobizrno"><c:out value="${fn:substring(master.bzRgstId, 0,3)}"/>-**-*****</div>
	      				<div id="nticno"><c:out value="${master.prtAtCode}"/>-<c:out value="${master.feeTp}"/>-<c:out value="${master.fiscalYr}"/>-<c:out value="${master.billNo}"/></div>
	      				<div id="nticDate"><c:out value="${master.billDt}"/></div>
	      				<div id="payTmlmt"><c:out value="${master.dueDate}"/></div>
	      				<div id="girormk"><c:out value="${master.chrgeKndNm}"/> 사용료</div>
		       		</div>
		       		<div id="noticermk">납기가 지난 고지서는 납부할 수 없으며, 금융기관에서는 온라인 수납처리 바랍니다.</div>
				</div>
			</div>
        </div>
    </div>
</div>
  </c:if>
    <c:if test="${resultCode!=0 }">
    	<h2>인쇄 할 대상이 존재하지 않습니다.</h2>
    </c:if>

  </body>
</html>