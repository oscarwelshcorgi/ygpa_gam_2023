<%@ page import="java.net.URLDecoder"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
  /**
  * @Class Name : GamFcltyRepairCheckReportHwp.jsp
  * @Description : 하자검사조서 한글파일 출력
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2015.1.7    HNJ          최초 생성
  *
  * author 정성현
  * since 2015.04.02
  *
  * Copyright (C) 2013 by LFIT  All right reserved.
  */
%>
<%
// 한글 파일 출력일 경우 컨트롤러에 isHwp 에 값을 담아서 리턴한다. fileName 에는 파일명을 넣는다.

	String fileName = request.getParameter("filename");
	fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
	response.reset();
	response.setHeader("Content-Disposition", "attachment;filename=\""+fileName + "\"");
	response.setHeader("Content-Description", "JSP Generated Data");
	response.setHeader("Cache-control","private");
	response.setContentType("application/hwp; charset=UTF-8");

// 한글파일에는 css가 먹지 않음.... 안타깝게도... 테이블에 속성정의를 해주어야 함... 귀찮더라도 작업 바람
// table에 border="1" width="530" 을 추가하면 됨
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>

<HEAD>
<META NAME="Generator" CONTENT="Haansoft HWP 8.0.0.466">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<TITLE>하    자    내    용</TITLE>
<STYLE>
<!--
P.HStyle0, LI.HStyle0, DIV.HStyle0
	{style-name:"바탕글"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:16.0pt; font-size:10.0pt; font-family:한컴바탕; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle1, LI.HStyle1, DIV.HStyle1
	{style-name:"본문"; margin-left:15.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:16.0pt; font-size:10.0pt; font-family:바탕; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle2, LI.HStyle2, DIV.HStyle2
	{style-name:"개요 1"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:16.0pt; font-size:10.0pt; font-family:바탕; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle3, LI.HStyle3, DIV.HStyle3
	{style-name:"개요 2"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:16.0pt; font-size:10.0pt; font-family:바탕; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle4, LI.HStyle4, DIV.HStyle4
	{style-name:"개요 3"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:16.0pt; font-size:10.0pt; font-family:바탕; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle5, LI.HStyle5, DIV.HStyle5
	{style-name:"개요 4"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:16.0pt; font-size:10.0pt; font-family:바탕; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle6, LI.HStyle6, DIV.HStyle6
	{style-name:"개요 5"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:16.0pt; font-size:10.0pt; font-family:바탕; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle7, LI.HStyle7, DIV.HStyle7
	{style-name:"개요 6"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:16.0pt; font-size:10.0pt; font-family:바탕; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle8, LI.HStyle8, DIV.HStyle8
	{style-name:"개요 7"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:16.0pt; font-size:10.0pt; font-family:바탕; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle9, LI.HStyle9, DIV.HStyle9
	{style-name:"쪽 번호"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:16.0pt; font-size:10.0pt; font-family:굴림; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle10, LI.HStyle10, DIV.HStyle10
	{style-name:"머리말"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:15.0pt; font-size:9.0pt; font-family:굴림; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle11, LI.HStyle11, DIV.HStyle11
	{style-name:"각주"; margin-left:13.1pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:-13.1pt; line-height:13.0pt; font-size:9.0pt; font-family:바탕; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
-->
</STYLE>
</HEAD>

<BODY>

<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;line-height:120%;'>
<TABLE border="1" cellspacing="0" cellpadding="0" style='border-collapse:collapse;border:none;'>
<TR>
	<TD width="597" height="851" valign="top" style='border-left:solid #000000 1.1pt;border-right:solid #000000 1.1pt;border-top:solid #000000 1.1pt;border-bottom:solid #000000 1.1pt;padding:1.4pt 5.1pt 1.4pt 5.1pt'>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";'>&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";'>&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";'>하 자 검 사 조 서</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";'>&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";'>&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;'><SPAN STYLE='font-size:15.0pt;font-weight:"bold";'>&nbsp;&nbsp;공사명 : 2013년 광양항 항만시설물 보수보강공사</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;'><SPAN STYLE='font-size:15.0pt;font-weight:"bold";'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2013년 12월 30일 준공</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2013년&nbsp; 5월 29일 ㈜서림 외 1 계약분</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;도급액 :&nbsp; 4,621,000,000원</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";'>&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;'><SPAN STYLE='font-size:15.0pt;letter-spacing:0.3pt;'>&nbsp;위 공사의 하자검사의 명을 받아&nbsp; 2015년 2월 24일 검사한 </SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;'><SPAN STYLE='font-size:15.0pt;letter-spacing:0.3pt;'>&nbsp;결과 하자있음을 확인함</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";'>&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2015년 2월 24일</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;하자검사원 : 4급 봉 만 식&nbsp; (인)</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";'>&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";'>&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";'>&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";'>&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";'>&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";'>&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";'>&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";'>여수광양항만공사사장 귀하</SPAN></P>
	</TD>
</TR>
</TABLE></P>











<!-- 공백 정렬이 제대로 안되네요... -->
<!-- 하자내용 이미지 출력 부분 페이지 -->


<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";'>하&nbsp;&nbsp;&nbsp; 자&nbsp;&nbsp;&nbsp; 내&nbsp;&nbsp;&nbsp; 용</SPAN></P>

<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";'><BR></SPAN></P>

<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:14.0pt;'>(2013년 광양항 항만시설물 보수보강공사)</SPAN></P>

<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;line-height:120%;'>
<TABLE border="1" cellspacing="0" cellpadding="0" style='border-collapse:collapse;border:none;'>
<TR>
	<TD width="410" height="45" valign="middle" bgcolor="#ffffff" style='border-left:solid #000000 1.1pt;border-right:solid #000000 0.4pt;border-top:solid #000000 1.1pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 5.1pt 1.4pt 5.1pt'>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:12.0pt;font-family:"휴먼명조";'>하자내용</SPAN></P>
	</TD>
	<TD width="191" height="45" valign="middle" bgcolor="#ffffff" style='border-left:solid #000000 0.4pt;border-right:solid #000000 1.1pt;border-top:solid #000000 1.1pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 5.1pt 1.4pt 5.1pt'>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;'><SPAN STYLE='font-size:12.0pt;font-family:"휴먼명조";'>비 고</SPAN></P>
	</TD>
</TR>
<TR>
	<TD width="410" height="102" valign="middle" style='border-left:solid #000000 1.1pt;border-right:solid #000000 0.4pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 1.1pt;padding:1.4pt 5.1pt 1.4pt 5.1pt'>
	<P CLASS=HStyle0 STYLE='margin-left:22.1pt;margin-bottom:4.0pt;text-indent:-22.1pt;'><SPAN STYLE='font-size:12.0pt;font-family:"휴먼명조";'>&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-left:22.1pt;margin-bottom:4.0pt;text-indent:-22.1pt;'><SPAN STYLE='font-size:12.0pt;font-family:"휴먼명조";'>&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-left:22.1pt;margin-bottom:4.0pt;text-indent:-22.1pt;'><SPAN STYLE='font-size:12.0pt;font-family:"휴먼명조";'>&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='margin-left:22.1pt;margin-bottom:4.0pt;text-indent:-22.1pt;'><SPAN STYLE='font-size:12.0pt;font-family:"휴먼명조";'>&nbsp;</SPAN></P>
	</TD>
	<TD width="191" height="102" valign="middle" style='border-left:solid #000000 0.4pt;border-right:solid #000000 1.1pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 1.1pt;padding:1.4pt 5.1pt 1.4pt 5.1pt'>
	<P CLASS=HStyle0 STYLE='text-align:center;'>&nbsp;</P>
	</TD>
</TR>
</TABLE></P>

<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;'><SPAN STYLE='font-size:14.0pt;'><BR></SPAN></P>

<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;'><SPAN STYLE='font-size:14.0pt;'>○ 사진대지</SPAN></P>

<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;line-height:120%;'>
<TABLE border="1" cellspacing="0" cellpadding="0" style='border-collapse:collapse;border:none;'>
<TR>
	<TD width="300" height="206" valign="middle" style='border-left:solid #000000 1.1pt;border-right:solid #000000 0.4pt;border-top:solid #000000 1.1pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 5.1pt 1.4pt 5.1pt'>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;line-height:130.5pt;'>&nbsp;&nbsp;&nbsp;<IMG src="file:///C:\Users\Administrator\Desktop\PICA424.png" width="224" height="174" vspace="0" hspace="0" border="0"></P>
	</TD>
	<TD width="300" height="206" valign="middle" style='border-left:solid #000000 0.4pt;border-right:solid #000000 1.1pt;border-top:solid #000000 1.1pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 5.1pt 1.4pt 5.1pt'>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;line-height:127.5pt;'><IMG src="file:///C:\Users\Administrator\Desktop\PICA445.png" width="228" height="170" vspace="0" hspace="0" border="0"></P>
	</TD>
</TR>
<TR>
	<TD width="300" height="42" valign="middle" style='border-left:solid #000000 1.1pt;border-right:solid #000000 1.1pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 5.1pt 1.4pt 5.1pt'>
	<P CLASS=HStyle0>&nbsp;</P>
	</TD>
	<TD width="300" height="42" valign="middle" style='border-left:solid #000000 1.1pt;border-right:solid #000000 1.1pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 5.1pt 1.4pt 5.1pt'>
	<P CLASS=HStyle0>&nbsp;</P>
	</TD>
</TR>
<TR>
	<TD width="300" height="264" valign="middle" style='border-left:solid #000000 1.1pt;border-right:solid #000000 0.4pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 5.1pt 1.4pt 5.1pt'>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;line-height:151.5pt;'>&nbsp;&nbsp;<IMG src="file:///C:\Users\Administrator\Desktop\PICA455.png" width="255" height="202" vspace="0" hspace="0" border="0"></P>
	</TD>
	<TD width="300" height="264" valign="middle" style='border-left:solid #000000 0.4pt;border-right:solid #000000 1.1pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 5.1pt 1.4pt 5.1pt'>
	<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;line-height:134.3pt;'><IMG src="file:///C:\Users\Administrator\Desktop\PICA466.png" width="240" height="179" vspace="0" hspace="0" border="0"></P>
	</TD>
</TR>
<TR>
	<TD width="300" height="42" valign="middle" style='border-left:solid #000000 1.1pt;border-right:solid #000000 1.1pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 1.1pt;padding:1.4pt 5.1pt 1.4pt 5.1pt'>
	<P CLASS=HStyle0>&nbsp;</P>
	<P CLASS=HStyle0>&nbsp;</P>
	</TD>
	<TD width="300" height="42" valign="middle" style='border-left:solid #000000 1.1pt;border-right:solid #000000 1.1pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 1.1pt;padding:1.4pt 5.1pt 1.4pt 5.1pt'>
	<P CLASS=HStyle0>&nbsp;</P>
	</TD>
</TR>
</TABLE></P>

<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;line-height:120%;'><SPAN STYLE='font-family:"굴림";'><BR></SPAN></P>
<BR><BR>
</BODY>

</HTML>


<%--











<div class="book">
    <div class="page">
        <div class="subpage ygpa_report" >
        	<table border="1" class="reportPage" width="530" height="800">
        		<tr>
	        		<td>
       		    		<table class="pageFont">
			        		<thead>
			        			<tr height="60">
			        				<td></td>
			        			</tr>
			        			<tr height="100">
					  				<td><h1><div class="title">하 자 검 사 조 서</div></h1></td>
					 			</tr>
			        		</thead>
			        		<tbody>
			        			<tr height="80">
			       				<td width="530"><div style="font-family:바탕;font-height:26px;font-weight:2px;">공 사 명 : <c:out value="${result.flawRprNm }" /></div></td>

			        			</tr>
			        			<tr height="20">
			        				<td></td>
			        			</tr>
			        			<tr height="30">
			        			<!-- TODO: 현재 DB의 값이 없어 미적용 향후 처리 예정 -->
			        				<td><p class="dateStr">2012년 11월 26일 준공(향후적용)</p></td>
			        			</tr>
			        			<tr height="30">
			        				<td><p class="dateStr"><c:out value="${result.ctrtDt }" /> <c:out value="${result.flawRprEntrpsNm }" /> 대표이사 <c:out value="${result.rprsntv }" />과 계약분</p></td>
			        			</tr>
			        			<tr height="30">
			        				<td><p class="amountStr">도급액 : 일금 <c:out value="${result.ctrtAmtKo }" />원정 (₩<fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.ctrtAmt }" />원)</p></td>
			        			</tr>
			        			<tr height="30">
			        				<td></td>
			        			</tr>
        			<tr height="130px">
        				<td style="vertical-align:top;font-size:15px;">
        					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        					위 공사에 대하여 <c:out value="${result.flawExamDt }" />&nbsp;&nbsp;하자검사한 결과 <span style="text-decoration:underline;">
        			<c:choose>
        				<c:when test="${result.flawEnnc == 'Y'}">하자있음</c:when>
        				<c:otherwise>하자없음</c:otherwise>
        			</c:choose>
        					</span>을 확인함
        				</td>
        			</tr>
			        			<tr height="60">
			        				<td><span id="today" class="dateStr"></span></td>
			        			</tr>
			        			<tr height="60">
			        				<td>
			        					<p class="inspectDate">
						        		하자검사자 : <c:out value="${result.flawExamUsrNm }" />
						        		<div  class="stamp">(인)</div>
						        		<img class="stamp" src="<c:url value='/cmm/getPfImage.do?physicalFileNm=${charger.signFileNmPhysicl}' />"/>
						        		</p>
						       		</td>
			        			</tr>
			        			<tr height="100">
			        				<td></td>
			        			</tr>
			        			<tr>
			        				<td><h3>여수광양항만공사 사장 귀하</h3></td>
			        			</tr>
			        			<tr height="80">
			        				<td></td>
			        			</tr>
			        		</tbody>
			        	</table>
	        		</td>
        		</tr>
        	</table>
        </div>
        </div>
        <c:if test="${result.flawEnnc == 'Y'}">
	        <div class="page">
	                <div class="subpage ygpa_report" >
	           <table class="page2Title" width="530">
	        			<tr height="15">
	        				<td></td>
	        			</tr>
	        			<tr height="15">
			  				<td><h1>하　자　내　용</h1></td>
			 			</tr>
	        			<tr height="80">
	        				<td><h2><c:out value="${result.flawRprNm }" />)</h2></td>
	        			</tr>
        			<tr height="20">
        				<td></td>
       				</tr>
	        	 </table>
	           <table border="1" class="rprReport" width="530">
	           		<tr>
		           		<th>하자내용</th>
		           		<th>비 고</th>
	           		</tr>
	           		<tr>
		           		<td><c:out value="${result.flawRprContents}"/></td>
		           		<td><c:out value="${result.rm}"/></td>
	           		</tr>
	           	</table>

	           	<p class="subtitle">○ 사진대지</p>
	    		<table class="pictureGridTbl" border="1" width="530">
					<c:forEach var="resultItem" items="${resultList}" varStatus="status" end="3" step="2">
	    			<tr>
	    				<td>
		    				<img class="tdFull" src="<c:url value='/fcltyMng/getRepairAttachFile.do?physicalFileNm=${resultItem.atchFileNmPhysicl }' />"/>
						</td>
						<td>
	    				<c:if test="${fn:length(resultList) gt status.index+1 }">
		    				<img class="tdFull" src="<c:url value='/fcltyMng/getRepairAttachFile.do?physicalFileNm=${resultList[status.index+1].atchFileNmPhysicl }' />"/>
						</c:if>
						</td>
	    			</tr>
	    		 <tr class="caption">
    			<td><c:out value="${resultItem.atchFileSj }"/></td>
    			<td>
   					<c:if test="${fn:length(resultList) gt status.index+1 }">
						<c:out value="${resultList[status.index+1].atchFileSj }"/>
					</c:if>
    			</td>
    			</tr>
	    			</c:forEach>

	    	<c:if test="${fn:length(resultList) gt 4 }">
	    			<!-- 첫페이지는 두줄 만 출력하고 이상인 경우 다음 페이지 출력한다. -->
		    		</table>
		        </div>
	    	</div>
        <div id="ttt" class="page">
            <div class="subpage ygpa_report" >
	    		<table class="pictureGridTbl"  width="530">
	    		첫페이지는 두줄 만 출력하고 이상인 경우 다음 페이지 출력한다. 출력한 갯수가 페이지를 벗어나면 위에 페이지 끊기를 추가 한다.
				<c:forEach var="resultItem" items="${resultList}" varStatus="status" begin="4" step="2">
    			<tr>
    				<td>
    				<img class="tdFull" src="<c:url value='/fcltyMng/getRepairAttachFile.do?physicalFileNm=${resultItem.atchFileNmPhysicl}' />"/>
					</td>
					<td>
					<c:if test="${fn:length(resultList) gt status.index+1 }">
	    				<img class="tdFull" src="<c:url value='/fcltyMng/getRepairAttachFile.do?physicalFileNm=${resultItem[status.index+1].atchFileNmPhysicl }' />"/>
					</c:if>
					</td>
    			</tr>

    			<c:if test="${(status.index-status.begin) % 6 == 0 && !status.first && !status.last }">
	    		</table>
	        </div>
    	</div>
        <div id="tet" class="page">	<!--  class="page"> 페이지 누락 됨 -->
            <div class="subpage ygpa_report" >
	    		<table class="pictureGridTbl"  width="530">
	    		첫페이지는 두줄 만 출력하고 이상인 경우 다음 페이지 출력한다. 출력한 갯수가 페이지를 벗어나면 위에 페이지 끊기를 추가 한다.
    			</c:if>
    			</c:forEach>
   			</c:if>
        	   </table>

	        </div>

    	</div>
        </c:if>
</div>
  </c:if>
    <c:if test="${resultCode!=0 }">
    	<h2>서버 오류</h2>
    	<p>에러 메시지 : <c:out value="${ resultMessage}"/></p>
    </c:if>
  </body> --%>
