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
  * @Description : 하자검사조서 인쇄 화면
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
if(request.getAttribute("isHwp")!=null){
	String fileName = request.getParameter("filename");
	fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
	response.reset();
	response.setHeader("Content-Disposition", "attachment;filename=\""+fileName + "\"");
	response.setHeader("Content-Description", "JSP Generated Data");
	response.setHeader("Cache-control","private");
	response.setContentType("application/hwp; charset=UTF-8");
}
// 한글파일에는 css가 먹지 않음.... 안타깝게도... 테이블에 속성정의를 해주어야 함... 귀찮더라도 작업 바람
// table에 border="1" width="530" 을 추가하면 됨

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>

<HEAD>
<META NAME="Generator" CONTENT="Haansoft HWP 8.0.0.466">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<TITLE>하 자 내 용</TITLE>

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
	<TD width="597" height="880" valign="top" style='border-left:solid #000000 1.1pt;border-right:solid #000000 1.1pt;border-top:solid #000000 1.1pt;border-bottom:solid #000000 1.1pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:20.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:20.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";";line-height:160%;'><u>하 자 만 료 검 사 조 서</u></SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:16.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:16.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:15.0pt;font-weight:"bold";'>&nbsp;&nbsp;공사명 : <c:out value="${result.flawRprNm }" /></SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:15.0pt;font-weight:"bold";'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </SPAN><SPAN STYLE='font-size:15.0pt;line-height:160%;'>20&nbsp;&nbsp;년&nbsp;&nbsp;월&nbsp;&nbsp;일 준공</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${result.ctrtDt }" /> <c:out value="${result.flawRprEntrpsNm }" /></SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;도급액 : <fmt:formatNumber type="number" maxIntegerDigits="15" value="${result.ctrtAmt }" />원</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><span STYLE='font-size:14.0pt;letter-spacing:-5pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;위 공사의 하자검사의 명을 받아 <c:out value="${result.flawExamDt }" /> 검사한 결과</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:14.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u><c:out value="${result.castFlawEnnc }"/></u>을 확인함</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:130%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:130%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:130%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${result.hwpDate }"/></SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:100%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:100%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:130%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;하자검사원 : <c:out value="${result.flawExamUsrNm }" /> (인)<img src="http://<%=request.getServerName() %>:<%=request.getServerPort() %><c:url value='/cmm/getPfImage.do?physicalFileNm=${charger.signFileNmPhysicl}' />"/></SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:150%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:150%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:150%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:150%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:150%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:150%;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";line-height:160%;'>여수광양항만공사사장 귀하</SPAN></P>
	</TD>
</TR>
</TABLE></P>

  </body>
</html>