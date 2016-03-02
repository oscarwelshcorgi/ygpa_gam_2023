<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamHtldPrintNoticeIssue.jsp
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
	min-height: 28.7cm; 
	/*min-height: 267mm;*/
	/* padding: 1cm; */
	margin: 0cm auto;
	border: 1px #D3D3D3 solid;
	border-radius: 5px;
	background-image: url('<c:url value="/images/egovframework/ygpa/gam/misc/giro_page.png" />');
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

.subpage {
	padding: 0.27cm;
	/* border: 5px red solid; */
	height: 296mm;
}

div.postPage {
	position: relative;
	padding: 1.7cm;
	height: 10cm;
}

div.receipPage {
	position: relative;
padding: 0cm;
height: 9cm;
display: block;
}

 div.giro {
	position: relative;
	left: 5.8cm;
	/* top: 19.55cm; */
	width: 14.8cm;
	height: 8.8cm;
	font-size: 0.4cm;
	font-family: 돋움;
 }

div.sender {
	position: relative;
	display: block;
	/* left: 2cm; */
	/* top: 2.6cm; */
	width: 10cm;
	height: 3.0cm;
	font-size: 0.35cm;
	font-family: 돋움;
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

div.letterMsg {
	position: relative;
	display: block;
	left: 6cm;
	/* top: 4.4cm; */
	width: 5.1cm;
	height: 0.7cm;
	border: 0.06cm black solid;
	font-size: 0.4cm;
	font-family: 돋움;
	text-align: center;
	vertical-align: middle;
	padding: 0.1cm;
}

div.receiver {
	position: relative;
	display: block;
	/* left: 8cm;
	top: 1.2cm; */
	width: 16cm;
	height: 3.4cm;
	font-size: 0.35cm;
	font-family: 돋움;
	text-align: right;
	right: 0cm;
	bottom: 0cm;
}

#companyName {
	font-family: 돋움;
	font-size: 0.4cm;
	margin-right: 2cm;
}

#ceoName {
	top: 0cm;
	/* width: 8cm; */
	/* height: 1.4cm; */
	text-align: right;
	font-family: 돋움;
	font-size: 0.4cm;
}

#recAddress {
	text-align: right;
	margin-top: 0.6cm;
}

#recPostcode {
	text-align: right;
	margin-top: 0.6cm;
}

div.notice {
	position: absolute;
	/* display: inline-block; */
	 left: 0.1cm;
	/* top: 1.3cm; */
	width: 5.2cm;
	height: 8cm;
	font-size: 0.35cm;
	font-family: 돋움;
	float: left;
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

 div.receipDetail {
	position: absolute;
	display: inline-block;
	left: 0.35cm;
	/* top: 1.3cm; */
	width: 5.2cm;
	height: 8cm;
	font-size: 0.35cm;
	font-family: 돋움;
	/* clear: both; */
	margin-left: 6.85cm;
	margin-top: 1.72cm;
 }

 div.feeAmount {
	/* position: relative; */
	/* left: 7.35cm; */
	/* top: 12.0cm; */
	width: 4.4cm;
	height: 0.46cm;
	font-size: 0.4cm;
	font-family: 돋움;
	text-align: right;
 }

 div.feeDetail {
	position: relative;
	/* left: 8.65cm; */
	top: 0.3cm;
	width: 4.2cm;
	height: 4.8cm;
	font-size: 0.27cm;
	font-family: 돋움;
	padding: 0.1cm;
	margin-left: 1.1cm;
 }

 div.feeDetail > ul {
	position: relative;
	width: 100%;
 }

 div.feeDetail > ul > li {
	display: block;
	padding-bottom: 0.15cm;
	height: 0.5cm;
 }

 div.rmk {
	position: absolute;
	display: inline-block;
	/* left: 12.45cm; */
	/* top: 11.3cm; */
	width: 7.85cm;
	height: 8cm;
	font-size: 0.28cm;
	font-family: 돋움;
	margin-top: 0.85cm;
	margin-left: 12.2cm;
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
	padding: 0.05cm 0.05cm;
 }

 .summary {
	padding: 0.1cm 0.1cm;
 }

 #totalAmount {
	position: relative;
	left: 9.6cm;
	top: 1.65cm;
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
	left: 7.4cm;
	top: 4.0cm;
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
	top: 2cm;
	width: 13.2cm;
	height: 0.4cm;
	font-size: 0.4cm;
	font-family: OCR-B-10 BT;
 }

 #girono {
	display: inline;
	position: relative;
	left: 0.2cm;
 }

 #cstmrrefcode {
	display: inline;
	position: relative;
	/* left: 1cm; */
	margin-left: 0.8cm;
 }

 #giroamount {
	display: inline;
	position: relative;
	/* left: 0.3cm; */
	margin-left: 0.8cm;
 }

 #girocc {
	display: inline;
	position: relative;
	/* left: 12.5cm; */
	margin-left: 0.4cm;
 }

 .giroDetail {
	position: relative;
	left: 2.55cm;
	top: 3.8cm;
	width: 11.8cm;
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
	top: 8.25cm;
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
	z-index: 9999;
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
	    /*min-height: 29.7cm;*/
	    min-height: 267mm;
		/* padding: 1cm; */
	    margin: 0cm auto;
	    border: 1px #D3D3D3 solid;
	    border-radius: 5px;
	    background: white;
	    -webkit-background-size: cover; /* For WebKit*/
	    -moz-background-size: cover;    /* Mozilla*/
	    -o-background-size: cover;      /* Opera*/
	    background-size: cover;         /* Generic*/
	    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
	}

	.printSubpage {
		padding: 0.27cm;
		/* border: 5px red solid; */
		height: 256mm;
	}

	div.postPage {
		position: relative;
		padding: 2cm 1.7cm;
		height: 10cm;
	}

	div.receipPage {
		position: relative;
		padding: 0cm;
		height: 9cm;
		display: block;
	}

	 div.giro {
		position: relative;
		left: 5.8cm;
		/* top: 19.55cm; */
		width: 14.8cm;
		height: 8.8cm;
		font-size: 0.4cm;
		font-family: 돋움;
	 }

	div.sender {
		position: relative;
		display: block;
		/* left: 2cm; */
		/* top: 2.6cm; */
		width: 10cm;
		height: 2.8cm;
		font-size: 0.35cm;
		font-family: 돋움;
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

	div.letterMsg {
		position: relative;
		display: block;
		left: 6cm;
		/* top: 4.4cm; */
		width: 5.1cm;
		height: 0.7cm;
		border: 0.06cm black solid;
		font-size: 0.4cm;
		font-family: 돋움;
		text-align: center;
		vertical-align: middle;
		padding: 0.1cm;
	}

	div.receiver {
		position: relative;
		display: block;
		/* left: 8cm;
		top: 1.2cm; */
		width: 16cm;
		height: 3.4cm;
		font-size: 0.35cm;
		font-family: 돋움;
		text-align: right;
		right: 0cm;
		bottom: 0cm;
	}

	#companyName {
		font-family: 돋움;
		font-size: 0.4cm;
		margin-right: 2cm;
	}

	#ceoName {
		top: 0cm;
		/* width: 8cm; */
		/* height: 1.4cm; */
		text-align: right;
		font-family: 돋움;
		font-size: 0.4cm;
	}

	#recAddress {
		text-align: right;
		margin-top: 0.6cm;
	}

	#recPostcode {
		text-align: right;
		margin-top: 0.6cm;
	}

	div.notice {
		position: absolute;
		/* display: inline-block; */
		/* left: 0.35cm; */
		/* top: 1.3cm; */
		width: 4.8cm;
		height: 8cm;
		font-size: 0.35cm;
		font-family: 돋움;
		float: left;
	}

	 div.notice > h2 {
		position: relative;
		text-align: center;
		width: 4.75cm;
		padding: 0.1cm;
		border: 1px black solid;
	 }

	 div.notice > p {
	 	position: relative;
	 	text-align: left;
		padding: 0.2cm 0.1cm;
		font-size: 0.31cm;
	 }

	 .bold {
	 	font-weight: bold;
	 }

	 div.receipDetail {
		position: absolute;
		display: inline-block;
		left: 0.2cm;
		/* top: 1.3cm; */
		width: 5.2cm;
		height: 8cm;
		font-size: 0.35cm;
		font-family: 돋움;
		/* clear: both; */
		margin-left: 6.85cm;
		margin-top: 1.85cm;
	 }

	 div.feeAmount {
		/* position: relative; */
		/* left: 7.35cm; */
		/* top: 12.0cm; */
		width: 4.4cm;
		height: 0.46cm;
		font-size: 0.4cm;
		font-family: 돋움;
		text-align: right;
	 }

	 div.feeDetail {
		position: relative;
		top: 0.3cm;
		width: 4.2cm;
	 }

	 div.feeDetail > ul {
		position: relative;
		width: 100%;
	 }

	 div.feeDetail > ul > li {
		display: block;
		padding-bottom: 0.15cm;
		height: 0.5cm;
	 }

	 div.rmk {
		position: absolute;
		display: inline-block;
		/* left: 12.45cm; */
		/* top: 11.3cm; */
		width: 7.85cm;
		height: 8cm;
		font-size: 0.28cm;
		font-family: 돋움;
		margin-top: 1cm;
		margin-left: 12cm;
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
		padding: 0.05cm 0.05cm;
	 }

	 .summary {
		padding: 0.1cm 0.1cm;
	 }

	 #totalAmount {
		position: relative;
		left: 9.1cm;
		top: 1.8cm;
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
		left: 7.4cm;
		top: 4.0cm;
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
		top: 2cm;
		width: 13.2cm;
		height: 0.4cm;
		font-size: 0.4cm;
		font-family: OCR-B-10 BT;
	 }

	 #girono {
		display: inline;
		position: relative;
		left: 0.2cm;
	 }

	 #cstmrrefcode {
		display: inline;
		position: relative;
		/* left: 1cm; */
		margin-left: 0.8cm;
	 }

	 #giroamount {
		display: inline;
		position: relative;
		/* left: 0.3cm; */
		margin-left: 0.8cm;
	 }

	 #girocc {
		display: inline;
		position: relative;
		/* left: 12.5cm; */
		margin-left: 0.4cm;
	 }

	 .giroDetail {
		position: relative;
		left: 2.45cm;
		top: 3.8cm;
		width: 11.8cm;
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
		top: 8.25cm;
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
	    for(i=0; i<ca.length; i++) {
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
			          {name : 'prtAtCode', value: '<c:out value="${searchOpt.prtAtCode}"/>'},
			          {name : 'workCode', value: '<c:out value="${searchOpt.workCode}"/>'},
			          {name : 'fiscalYr', value: '<c:out value="${searchOpt.fiscalYr}"/>'},
			          {name : 'billNo', value: '<c:out value="${searchOpt.billNo}"/>'},
			          {name : 'dlySerNo', value: '<c:out value="${searchOpt.dlySerNo}"/>'}
			          ];
			var leftPadding = ""+(2.7+Number($('#leftPrintPadding').val()||0))/10;
			var topPadding = ""+(2.7+Number($('#topPrintPadding').val()||0))/10;
			$('.subpage').css('padding-left', leftPadding+"cm");
			$('.subpage').css('padding-top', topPadding+"cm");
			$.ajax({
				url: '<c:url value="/oper/htld/printHtldArrrgNoticeIssue.do"/>',
				type: 'POST',
				module: this,
				dataType: 'json',
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				data: vo
				}).done(function(data) {
	                if(data.resultCode=='0') {
	        			window.print();
	        			$('.subpage').css('padding-left', "0.27cm");
	        			$('.subpage').css('padding-top', "0.27cm");
	                }
	                else alert(data.resultMsg);
			});
		});
	});
	</script>
  </head>
  <body>
  <c:if test="${resultCode==0 }">
  <fmt:parseDate value='${resultList[0].nticDt}' var='nticDate' pattern="yyyy-MM-dd" scope="page"/>
  <fmt:parseDate value='${resultList[0].payTmlmt}' var='payTmlmtDate' pattern="yyyy-MM-dd" scope="page"/>
  <a id="printButton" href="#">인쇄</a>
<div class="book">
<c:forEach var="master" items="${resultList }">
    <div class="page">
        <div class="subpage">
        <input id='leftPrintPadding' type="text" value="" class="notprint" style="position:fixed; left: 20px; top: 60px; width: 120px; z-index: 999;" placeholder="가로 보정치 mm">
        <input id='topPrintPadding' type="text" value="" class="notprint" style="position:fixed; left: 20px; top: 90px; width: 120px; z-index: 999;" placeholder="세로 보정치 mm">
        	<div class="postPage">
				<div class="sender">
					<div id="senderAddress">
						전라남도 광양시 항만대로 465 월드마린센터 13층
					</div>
					<div id="senderPostcode">
						545-030
					</div>
					<div class="letterMsg">고 지 서  재 중</div>
				</div>
				<div class="receiver">
					<div id="companyName">
						<c:out value="${master.agentName}"/>
					</div>
					<div id="ceoName">
						<c:out value="${master.rprsntvNm}"/>
					</div>
					<div id="recAddress">
						<c:out value="${master.adres}"/>
					</div>
					<div id="recPostcode">
						<c:out value="${fn:substring(master.zip, 0, 3)}"/>-<c:out value="${fn:substring(master.zip, 3, 6)}"/>
					</div>
				</div>
        	</div>
        	<div class="receipPage">
 	        	<div class="notice">
	        		<h2>안내말씀</h2>
	        		<p>* 전자 세금계산서는 LogisBill을 통하여 E-Mail로 발급됩니다.</p>
	        		<p>* 납기가 지난 고지서는 납부 할 수 없습니다.</p>
	        		<p>* 전자납부이용은 고지서를 발부 받으신 당일 19시 이후부터 인터넷 뱅킹 등을 이용하여 납부하실 수 있습니다.</p>
	        		<p class="bold">* 금융기관에서는 온라인 수납처리 바랍니다.</p>
	       		</div>
	       		<div class="receipDetail">
		       		<div class="feeAmount"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${master.djiroAmnt}" /></div>
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
	       		</div>
	      		<div class="rmk">
	  			    <c:set var="detailItem" value="${detail[0]}"/>
	  			    <c:set var="lnmCnt" value="0" />
	  			    <c:forEach var="dItem" items="${detail }">
	  			    	<c:if test="${ not ((detailItem.gisAssetsLocplc eq dItem.gisAssetsLocplc) and (detailItem.gisAssetsLnm eq dItem.gisAssetsLnm) and (detailItem.gisAssetsLnmSub eq dItem.gisAssetsLnmSub))  }">
	  			    		<c:set var="lnmCnt" value="${lnmCnt+1}" />
	  			    	</c:if>
	  			    </c:forEach>
	      			<h2>부과내역</h2>
		      			<p>소재지 : 
		      				<c:out value="${detailItem.gisAssetsLocplc}"/>&nbsp;<c:out value="${detailItem.gisAssetsLnm}"/><c:if test="${detailItem.gisAssetsLnmSub!=null}">-<c:out value="${detailItem.gisAssetsLnmSub}"/></c:if>
		      				<c:if test="${lnmCnt > 0}">
		      					&nbsp; (외 <c:out value="${lnmCnt}"/>건)
		      				</c:if>		      			
		      			</p>
		      			<p>공급가액 : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${master.supplyAmnt}" /> 원 (임대료<c:if test="${master.intrAmnt>0}">&nbsp;+&nbsp;분납이자</c:if><c:if test="${master.addAmnt>0}">&nbsp;+&nbsp;추가금액</c:if>)</p>
		      			<p>부가세(매출과세(일반)) : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${master.vat}" /> 원</p>
		      			<p>연체료 : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${master.dlyBillAmnt}" /> 원</p>
		      			<p>합계 : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${master.nticAmt}" /> 원</p>
	      			<h2>산출근거</h2>
		      			<p>사용기간 : <c:out value="${master.nticPdFrom}"/> ~ <c:out value="${master.nticPdTo}"/></p>
		      			<c:forEach var="feeHistItem" items="${feeHist }">
		      			<p>
		      				적용단가 : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${feeHistItem.applcPrice}" /> 원<c:if test="${feeHistItem.priceSe eq 2}">/월</c:if>, &nbsp; 면적 : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${feeHistItem.usageAr}" /> m<sup>2</sup>
		      				<br/>임대료 : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${feeHistItem.fee}" /> 원, 분납이자 : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${feeHistItem.intrAmnt}" /> 원
		      			</p>
		      			</c:forEach>
		      			<p>
		      				합계&nbsp;&nbsp;임대료 : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${master.fee}" /> 원
		      				<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;분납이자 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${master.intrAmnt}" /> 원 
		      				<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(이자율 : <fmt:formatNumber type="number" maxIntegerDigits="5" maxFractionDigits="2" value="${master.intrRate}" />% Cofix수신금리)
		      			</p>
					    <c:if test="${master.addAmnt>0}">
		 			    <p>
			      			추가금액 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${master.addAmnt}" /> 원 
				      					<br />&nbsp;<c:out value="${master.addAmntRm}"/>
	      				</p>
		      			</c:if>
	      				<p>
	      					연체료 : <fmt:formatNumber type="number" maxIntegerDigits="15" maxFractionDigits="2" value="${master.dlyBillAmnt}" /> 원 
			      					<c:if test="${master.dlyBillRsn!=null}">
				      					<br />&nbsp;<c:out value="${master.dlyBillRsn}"/>
				      				</c:if>
	      				</P>
		      			<p>국세징수법에 따라 연체료 3%, 중가산금 1.2%/매월 적용</p>
			      		<c:if test="${master.rm!=null}">
	      				<p>비고 : <c:out value="${master.rm}"/></p>
	      				</c:if>
	      		</div>
        	</div>

			<div class="giro">
	       		<div id="totalAmount"><fmt:formatNumber type="number" maxIntegerDigits="15" value="${master.djiroAmnt}" /></div>
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
      				<div id="girormk"><c:out value="${master.chrgeKndNm}"/> <c:out value="${master.nticCnt}"/>회차 사용료</div>
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