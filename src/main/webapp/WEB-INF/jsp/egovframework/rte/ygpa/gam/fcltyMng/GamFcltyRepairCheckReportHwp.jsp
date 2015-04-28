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
<TITLE></TITLE>
<STYLE>
<!--
P.HStyle0, LI.HStyle0, DIV.HStyle0
	{style-name:"바탕글"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:한컴바탕; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle1, LI.HStyle1, DIV.HStyle1
	{style-name:"본문"; margin-left:15.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:바탕; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle2, LI.HStyle2, DIV.HStyle2
	{style-name:"개요 1"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:바탕; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle3, LI.HStyle3, DIV.HStyle3
	{style-name:"개요 2"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:바탕; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle4, LI.HStyle4, DIV.HStyle4
	{style-name:"개요 3"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:바탕; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle5, LI.HStyle5, DIV.HStyle5
	{style-name:"개요 4"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:바탕; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle6, LI.HStyle6, DIV.HStyle6
	{style-name:"개요 5"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:바탕; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle7, LI.HStyle7, DIV.HStyle7
	{style-name:"개요 6"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:바탕; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle8, LI.HStyle8, DIV.HStyle8
	{style-name:"개요 7"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:바탕; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle9, LI.HStyle9, DIV.HStyle9
	{style-name:"쪽 번호"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:160%; font-size:10.0pt; font-family:굴림; letter-spacing:0.0pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle10, LI.HStyle10, DIV.HStyle10
	{style-name:"머리말"; margin-left:0.0pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:0.0pt; line-height:150%; font-size:9.0pt; font-family:굴림; letter-spacing:0.2pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
P.HStyle11, LI.HStyle11, DIV.HStyle11
	{style-name:"각주"; margin-left:13.1pt; margin-right:0.0pt; margin-top:0.0pt; margin-bottom:0.0pt; text-align:justify; text-indent:-13.1pt; line-height:130%; font-size:9.0pt; font-family:바탕; letter-spacing:0.5pt; font-weight:"normal"; font-style:"normal"; color:#000000;}
-->
</STYLE>
</HEAD>

<BODY>

<P CLASS=HStyle0'>
<TABLE border="1" cellspacing="0" cellpadding="0" style='border-collapse:collapse;border:none;'>
<TR>
	<TD width="562" height="870" valign="top" style='border-left:solid #000000 1.1pt;border-right:solid #000000 1.1pt;border-top:solid #000000 1.1pt;border-bottom:solid #000000 1.1pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:20.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:20.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";";line-height:160%;'><u>하 자 검 사 조 서</u></SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:16.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:16.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:15.0pt;font-weight:"bold";'>&nbsp;&nbsp;공사명 : <c:out value="${result.flawRprNm }" /></SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:15.0pt;font-weight:"bold";'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </SPAN><SPAN STYLE='font-size:15.0pt;line-height:160%;'>20&nbsp;&nbsp;년&nbsp;&nbsp;월&nbsp;&nbsp;일 준공</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${result.ctrtDt }" /> <c:out value="${result.flawRprEntrpsNm }" /></SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;도급액 : <c:out value="${result.ctrtAmt }" />원</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><span STYLE='font-size:14.0pt;letter-spacing:-5pt;'>&nbsp;&nbsp;&nbsp;위 공사의 하자검사의 명을 받아 <c:out value="${result.flawExamDt }" /> 검사한 결과</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:140%;'><SPAN STYLE='font-size:14.0pt;'>&nbsp;&nbsp;&nbsp;<u><c:out value="${result.castFlawEnnc }"/></u>을 확인함</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:130%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:130%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:130%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${result.hwpDate }"/></SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:100%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:100%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:130%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;하자검사원 : <c:out value="${result.flawExamUsrNm }" /> (인)<img src="http://<%=request.getServerName() %>:<%=request.getServerPort() %><c:url value='/cmm/getPfImage.do?physicalFileNm=${charger.signFileNmPhysicl}' />"/></SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:150%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:150%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:150%;'><SPAN STYLE='font-size:15.0pt;'>&nbsp;&nbsp;</SPAN></P>
	<P CLASS=HStyle0 STYLE='line-height:150%;text-align:center;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";line-height:160%;'>여수광양항만공사사장 귀하</SPAN></P>
	</TD>
</TR>
</TABLE></P>
<c:if test="${result.flawEnnc == 'Y'}">

<P CLASS=HStyle0 STYLE='text-align:center;line-height:160%;'><SPAN STYLE='font-size:20.0pt;font-weight:"bold";'><u>하&nbsp;&nbsp;&nbsp; 자&nbsp;&nbsp;&nbsp; 내&nbsp;&nbsp;&nbsp; 용</u></SPAN></P>

<P CLASS=HStyle0 STYLE='text-align:center;line-height:160%;'><SPAN STYLE='font-size:14.0pt;'>(<c:out value="${result.flawRprNm }" />)</SPAN></P>

<P CLASS=HStyle0 STYLE='text-align:center;'><SPAN STYLE='font-size:14.0pt;line-height:160%;'><BR></SPAN></P>

<P CLASS=HStyle0 STYLE='text-align:center;'>
<TABLE border="1" cellspacing="0" cellpadding="0" style='border-collapse:collapse;border:none;'>
<TR>
	<TD width="380" height="60" valign="middle" bgcolor="#ffffff" style='border-left:solid #000000 1.1pt;border-right:solid #000000 0.4pt;border-top:solid #000000 1.1pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
	<P CLASS=HStyle0 STYLE='text-align:center;'><SPAN STYLE='font-size:12.0pt;font-family:"휴먼명조";line-height:160%;'>하자내용</SPAN></P>
	</TD>
	<TD width="180" height="60" valign="middle" bgcolor="#ffffff" style='border-left:solid #000000 0.4pt;border-right:solid #000000 1.1pt;border-top:solid #000000 1.1pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
	<P CLASS=HStyle0 STYLE='text-align:center;'><SPAN STYLE='font-size:12.0pt;font-family:"휴먼명조";line-height:160%;'>비 고</SPAN></P>
	</TD>
</TR>
<TR>
	<TD width="380" height="117" valign="middle" style='border-left:solid #000000 1.1pt;border-right:solid #000000 0.4pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 1.1pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
	<P CLASS=HStyle0><SPAN STYLE='font-size:12.0pt;font-family:"휴먼명조";'><pre><c:out value="${result.flawRprContents}"/></pre></SPAN></P>
	</TD>
	<TD width="180" height="117" valign="middle" style='border-left:solid #000000 0.4pt;border-right:solid #000000 1.1pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 1.1pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
	<P CLASS=HStyle0><SPAN STYLE='font-size:12.0pt;font-family:"휴먼명조";'><pre><c:out value="${result.rm}"/></pre></SPAN></P>
	</TD>
</TR>
</TABLE></P>

<P CLASS=HStyle0><SPAN STYLE='font-size:14.0pt;line-height:160%;'><BR></SPAN></P>

<P CLASS=HStyle0><SPAN STYLE='font-size:14.0pt;line-height:160%;'>○ 사진대지</SPAN></P>

<P CLASS=HStyle0 STYLE='margin-bottom:4.0pt;text-align:center;line-height:120%;'>
<TABLE border="1" cellspacing="0" cellpadding="0" style='border-collapse:collapse;border:none;'>
			<c:forEach var="resultItem" items="${resultList}" varStatus="status" end="3" step="2">
				<TR>
					<TD width="280" height="232" valign="middle" style='border-left:solid #000000 1.1pt;border-right:solid #000000 0.4pt;border-top:solid #000000 1.1pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
					<P CLASS=HStyle0 STYLE='text-align:center;'><IMG src="http://<%=request.getServerName() %>:<%=request.getServerPort() %><c:url value='/fcltyMng/fdown/getRepairAttachFile.do?physicalFileNm=${resultItem.atchFileNmPhysicl }' />" width="260" height="195" vspace="0" hspace="0" border="0"></P>
					</TD>
					<TD width="280" height="232" valign="middle" style='border-left:solid #000000 0.4pt;border-right:solid #000000 1.1pt;border-top:solid #000000 1.1pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
					<c:if test="${fn:length(resultList) gt status.index+1 }">
					<P CLASS=HStyle0 STYLE='text-align:center;'><IMG src="http://<%=request.getServerName() %>:<%=request.getServerPort() %><c:url value='/fcltyMng/fdown/getRepairAttachFile.do?physicalFileNm=${resultList[status.index+1].atchFileNmPhysicl }' />" width="260" height="195" vspace="0" hspace="0" border="0"></P>
					</c:if>
					</TD>
				</TR>
				<TR>
					<TD width="280" height="30" valign="middle" style='border-left:solid #000000 1.1pt;border-right:solid #000000 1.1pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
					<P CLASS=HStyle0><SPAN STYLE='font-size:11.0pt;font-family:"휴먼명조";line-height:160%;'>&nbsp;<c:out value="${resultItem.atchFileSj }"/></SPAN></P>
					</TD>
					<TD width="280" height="30" valign="middle" style='border-left:solid #000000 1.1pt;border-right:solid #000000 1.1pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
					<P CLASS=HStyle0><SPAN STYLE='font-size:11.0pt;font-family:"휴먼명조";line-height:160%;'>&nbsp;
											<c:if test="${fn:length(resultList) gt status.index+1 }">
												<c:out value="${resultList[status.index+1].atchFileSj }"/>
											</c:if></SPAN></P>
					</TD>
				</TR>
   			</c:forEach>
</table>

<!-- 첫페이지는 두줄 만 출력하고 이상인 경우 다음 페이지 출력한다. -->
<c:if test="${fn:length(resultList) gt 4 }">

<TABLE border="1" cellspacing="0" cellpadding="0" style='border-collapse:collapse;border:none;'>
			<c:forEach var="resultItem" items="${resultList}" varStatus="status" begin="4" step="2">

	    		<TR>
					<TD width="280" height="232" valign="middle" style='border-left:solid #000000 1.1pt;border-right:solid #000000 0.4pt;border-top:solid #000000 1.1pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
					<P CLASS=HStyle0 STYLE='text-align:center;'><IMG src="http://<%=request.getServerName() %>:<%=request.getServerPort() %><c:url value='/fcltyMng/fdown/getRepairAttachFile.do?physicalFileNm=${resultItem.atchFileNmPhysicl }' />" width="260" height="195" vspace="0" hspace="0" border="0"></P>
					</TD>
					<TD width="280" height="232" valign="middle" style='border-left:solid #000000 0.4pt;border-right:solid #000000 1.1pt;border-top:solid #000000 1.1pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
					<c:if test="${fn:length(resultList) gt status.index+1 }">
					<P CLASS=HStyle0 STYLE='text-align:center;'><IMG src="http://<%=request.getServerName() %>:<%=request.getServerPort() %><c:url value='/fcltyMng/fdown/getRepairAttachFile.do?physicalFileNm=${resultList[status.index+1].atchFileNmPhysicl }' />" width="260" height="195" vspace="0" hspace="0" border="0"></P>
					</c:if>
					</TD>
				</TR>
				<TR>
					<TD width="280" height="30" valign="middle" style='border-left:solid #000000 1.1pt;border-right:solid #000000 1.1pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
					<P CLASS=HStyle0><SPAN STYLE='font-size:11.0pt;font-family:"휴먼명조";line-height:160%;'>&nbsp;<c:out value="${resultItem.atchFileSj }"/></SPAN></P>
					</TD>
					<TD width="280" height="30" valign="middle" style='border-left:solid #000000 1.1pt;border-right:solid #000000 1.1pt;border-top:solid #000000 0.4pt;border-bottom:solid #000000 0.4pt;padding:1.4pt 1.4pt 1.4pt 1.4pt'>
					<P CLASS=HStyle0><SPAN STYLE='font-size:11.0pt;font-family:"휴먼명조";line-height:160%;'>&nbsp;
											<c:if test="${fn:length(resultList) gt status.index+1 }">
												<c:out value="${resultList[status.index+1].atchFileSj }"/>
											</c:if></SPAN></P>
					</TD>
				</TR>

   			</c:forEach>
</table>

</c:if>

</P>

</c:if>

</BODY>

</HTML>