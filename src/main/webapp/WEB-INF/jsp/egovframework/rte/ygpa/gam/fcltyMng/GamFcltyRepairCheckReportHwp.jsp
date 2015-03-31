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
  * @Class Name : GamFcltyRepairCheckResultPrint.jsp
  * @Description : 인쇄 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2015.1.7    HNJ          최초 생성
  *
  * author HNJ
  * since 2015.1.7
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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>

<HEAD>
<META NAME="Generator" CONTENT="Hancom HWP 8.5.8.1399">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<TITLE>하 자 검 사 조 서</TITLE>
<STYLE type="text/css">
<!--
p.HStyle0
	{style-name:"바탕글"; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
li.HStyle0
	{style-name:"바탕글"; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
div.HStyle0
	{style-name:"바탕글"; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
p.HStyle1
	{style-name:"본문"; margin-left:15.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
li.HStyle1
	{style-name:"본문"; margin-left:15.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
div.HStyle1
	{style-name:"본문"; margin-left:15.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
p.HStyle2
	{style-name:"개요 1"; margin-left:10.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
li.HStyle2
	{style-name:"개요 1"; margin-left:10.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
div.HStyle2
	{style-name:"개요 1"; margin-left:10.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
p.HStyle3
	{style-name:"개요 2"; margin-left:20.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
li.HStyle3
	{style-name:"개요 2"; margin-left:20.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
div.HStyle3
	{style-name:"개요 2"; margin-left:20.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
p.HStyle4
	{style-name:"개요 3"; margin-left:30.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
li.HStyle4
	{style-name:"개요 3"; margin-left:30.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
div.HStyle4
	{style-name:"개요 3"; margin-left:30.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
p.HStyle5
	{style-name:"개요 4"; margin-left:40.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
li.HStyle5
	{style-name:"개요 4"; margin-left:40.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
div.HStyle5
	{style-name:"개요 4"; margin-left:40.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
p.HStyle6
	{style-name:"개요 5"; margin-left:50.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
li.HStyle6
	{style-name:"개요 5"; margin-left:50.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
div.HStyle6
	{style-name:"개요 5"; margin-left:50.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
p.HStyle7
	{style-name:"개요 6"; margin-left:60.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
li.HStyle7
	{style-name:"개요 6"; margin-left:60.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
div.HStyle7
	{style-name:"개요 6"; margin-left:60.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
p.HStyle8
	{style-name:"개요 7"; margin-left:70.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
li.HStyle8
	{style-name:"개요 7"; margin-left:70.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
div.HStyle8
	{style-name:"개요 7"; margin-left:70.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
p.HStyle9
	{style-name:"쪽 번호"; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬돋움; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
li.HStyle9
	{style-name:"쪽 번호"; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬돋움; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
div.HStyle9
	{style-name:"쪽 번호"; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:함초롬돋움; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
p.HStyle10
	{style-name:"머리말"; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:150%; font-size:9.0pt; font-family:함초롬돋움; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
li.HStyle10
	{style-name:"머리말"; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:150%; font-size:9.0pt; font-family:함초롬돋움; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
div.HStyle10
	{style-name:"머리말"; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:150%; font-size:9.0pt; font-family:함초롬돋움; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
p.HStyle11
	{style-name:"각주"; margin-left:13.1pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:-13.1pt; line-height:130%; font-size:9.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
li.HStyle11
	{style-name:"각주"; margin-left:13.1pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:-13.1pt; line-height:130%; font-size:9.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
div.HStyle11
	{style-name:"각주"; margin-left:13.1pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:-13.1pt; line-height:130%; font-size:9.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
p.HStyle12
	{style-name:"미주"; margin-left:13.1pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:-13.1pt; line-height:130%; font-size:9.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
li.HStyle12
	{style-name:"미주"; margin-left:13.1pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:-13.1pt; line-height:130%; font-size:9.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
div.HStyle12
	{style-name:"미주"; margin-left:13.1pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:-13.1pt; line-height:130%; font-size:9.0pt; font-family:함초롬바탕; letter-spacing:0; font-weight:"normal"; font-style:"normal"; color:#000000;}
p.HStyle13
	{style-name:"메모"; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:130%; font-size:9.0pt; font-family:함초롬돋움; letter-spacing:-5%; font-weight:"normal"; font-style:"normal"; color:#000000;}
li.HStyle13
	{style-name:"메모"; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:130%; font-size:9.0pt; font-family:함초롬돋움; letter-spacing:-5%; font-weight:"normal"; font-style:"normal"; color:#000000;}
div.HStyle13
	{style-name:"메모"; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:130%; font-size:9.0pt; font-family:함초롬돋움; letter-spacing:-5%; font-weight:"normal"; font-style:"normal"; color:#000000;}
-->
</STYLE>
</HEAD>

<BODY>

<P CLASS=HStyle0></P>
<TABLE border="1" cellspacing="0" cellpadding="0" style='border-collapse:collapse;border:none;'>
<TR>
	<TD valign="middle" style='width:559;height:104;border-left:solid #000000 0.4pt;border-right:solid #000000 0.4pt;border-top:solid #000000 0.4pt;border-bottom:none;padding:1.4pt 8.5pt 1.4pt 8.5pt'>
	<P CLASS=HStyle0 STYLE='text-align:center;'><SPAN STYLE='font-size:24.0pt;font-weight:"bold";text-decoration:"underline";line-height:160%'>하 자 검 사 조 서</SPAN></P>
	</TD>
</TR>
<TR>
	<TD valign="middle" style='width:559;height:55;border-left:solid #000000 0.4pt;border-right:solid #000000 0.4pt;border-top:none;border-bottom:none;padding:1.4pt 8.5pt 1.4pt 8.5pt'>
	<P CLASS=HStyle0>공사명 : ${result.flawRprNm }</P>
	</TD>
</TR>
<TR>
	<TD valign="middle" style='width:559;height:101;border-left:solid #000000 0.4pt;border-right:solid #000000 0.4pt;border-top:none;border-bottom:none;padding:1.4pt 8.5pt 1.4pt 8.5pt'>
	<P CLASS=HStyle0 STYLE='text-align:right;'>2012년 11월 26일 적용</P>
	<P CLASS=HStyle0 STYLE='text-align:right;'>{1}</P>
	<P CLASS=HStyle0 STYLE='text-align:right;'>도급액 : {2} ({3}원)</P>
	</TD>
</TR>
<TR>
	<TD valign="top" style='width:559;height:183;border-left:solid #000000 0.4pt;border-right:solid #000000 0.4pt;border-top:none;border-bottom:none;padding:1.4pt 8.5pt 1.4pt 8.5pt'>
	<P CLASS=HStyle0>위 공사에 대하여 {4} 하자검사한 결과 <SPAN STYLE='text-decoration:"underline"'>하자없음</SPAN>을 확인 함</P>
	</TD>
</TR>
<TR>
	<TD valign="middle" style='width:559;height:44;border-left:solid #000000 0.4pt;border-right:solid #000000 0.4pt;border-top:none;border-bottom:none;padding:1.4pt 8.5pt 1.4pt 8.5pt'>
	<P CLASS=HStyle0 STYLE='text-align:right;'>{5}</P>
	</TD>
</TR>
<TR>
	<TD valign="middle" style='width:559;height:74;border-left:solid #000000 0.4pt;border-right:solid #000000 0.4pt;border-top:none;border-bottom:none;padding:1.4pt 8.5pt 1.4pt 8.5pt'>
	<P CLASS=HStyle0 STYLE='text-align:right;'>하자검사자 : {6}&nbsp;&nbsp;&nbsp;&nbsp; (인)</P>
	</TD>
</TR>
<TR>
	<TD valign="middle" style='width:559;height:17;border-left:solid #000000 0.4pt;border-right:solid #000000 0.4pt;border-top:none;border-bottom:none;padding:1.4pt 8.5pt 1.4pt 8.5pt'>
	<P CLASS=HStyle0>&nbsp;</P>
	</TD>
</TR>
<TR>
	<TD valign="middle" style='width:559;height:121;border-left:solid #000000 0.4pt;border-right:solid #000000 0.4pt;border-top:none;border-bottom:none;padding:1.4pt 8.5pt 1.4pt 8.5pt'>
	<P CLASS=HStyle0 STYLE='text-align:center;'><SPAN STYLE='font-size:26.0pt;font-weight:"bold";line-height:160%'>여수광양항만공사 사장 귀하</SPAN></P>
	</TD>
</TR>
<TR>
	<TD valign="middle" style='width:559;height:40;border-left:solid #000000 0.4pt;border-right:solid #000000 0.4pt;border-top:none;border-bottom:solid #000000 0.4pt;padding:1.4pt 8.5pt 1.4pt 8.5pt'>
	<P CLASS=HStyle0>&nbsp;</P>
	</TD>
</TR>
</TABLE>
<P CLASS=HStyle0><IMG src="file:///C:\Users\EUNSUNGJ\Documents\PIC9E87.png" alt="그림입니다.
원본 그림의 이름: stamp.gif
원본 그림의 크기: 가로 168pixel, 세로 168pixel" width="48" height="48" vspace="0" hspace="0" border="0"></P>

<P CLASS=HStyle0><BR></P>

<P CLASS=HStyle0><BR></P>

<P CLASS=HStyle0><BR></P>

<P CLASS=HStyle0><BR></P>

<P CLASS=HStyle0><BR></P>

<P CLASS=HStyle0><BR></P>

<P CLASS=HStyle0><BR></P>
        <c:if test="${result.flawEnnc == 'Y'}">

<P CLASS=HStyle0><BR></P>

<P CLASS=HStyle0><BR></P>

<P CLASS=HStyle0 STYLE='text-align:center;'><SPAN STYLE='font-size:18.0pt;text-decoration:"underline";line-height:160%'><BR></SPAN></P>

<P CLASS=HStyle0 STYLE='text-align:center;'><SPAN STYLE='font-size:18.0pt;text-decoration:"underline";line-height:160%'><BR></SPAN></P>

<P CLASS=HStyle0 STYLE='text-align:center;'><SPAN STYLE='font-size:18.0pt;text-decoration:"underline";line-height:160%'><BR></SPAN></P>

<P CLASS=HStyle0 STYLE='text-align:center;'></P>
<TABLE border="1" cellspacing="0" cellpadding="0" style='border-collapse:collapse;border:none;'>
<TR>
	<TD valign="middle" style='width:298;height:59;border-left:solid #000000 0.4pt;border-right:solid #000000 0.4pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 5.1pt 1.4pt 5.1pt'>
	<P CLASS=HStyle0>테스트</P>
	</TD>
	<TD valign="middle" style='width:298;height:59;border-left:solid #000000 0.4pt;border-right:solid #000000 0.4pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 5.1pt 1.4pt 5.1pt'>
	<P CLASS=HStyle0>합니다.</P>
	</TD>
</TR>

<c:forEach var="resultItem" items="${resultList}" varStatus="status" end="3" step="2">

<TR>
	<TD valign="middle" style='width:298;height:116;border-left:solid #000000 0.4pt;border-right:solid #000000 0.4pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 5.1pt 1.4pt 5.1pt'>
	<P CLASS=HStyle0><SPAN STYLE='font-weight:"bold"'><IMG src="<c:url value='/fcltyMng/getRepairAttachFile.do?physicalFileNm=${resultItem.atchFileNmPhysicl }' />" alt="그림입니다." width="94" height="71" vspace="0" hspace="0" border="0"></SPAN></P>
	</TD>
	<TD valign="middle" style='width:298;height:116;border-left:solid #000000 0.4pt;border-right:solid #000000 0.4pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 5.1pt 1.4pt 5.1pt'>
	<P CLASS=HStyle0><IMG src="/fcltyMng/getRepairAttachFile.do?physicalFileNm=${resultList[status.index+1].atchFileNmPhysicl }' />" alt="그림입니다." width="94" height="71" vspace="0" hspace="0" border="0"></P>
	</TD>
</TR>

</c:forEach>

</TABLE>
<P CLASS=HStyle0 STYLE='text-align:center;'></P>

<P CLASS=HStyle0><BR></P>

<P CLASS=HStyle0><BR></P>

</c:if>   <!-- c:if test="${result.flawEnnc == 'Y'}" -->


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
