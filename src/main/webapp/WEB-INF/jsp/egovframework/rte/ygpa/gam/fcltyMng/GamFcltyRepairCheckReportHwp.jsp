<%@ page import="java.net.URLDecoder"%>
<%@ page contentType="text/html; charset=euc-kr" pageEncoding="euc-kr"%>
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
	response.setContentType("application/hwp; charset=EUC-KR");
}
// 한글파일에는 css가 먹지 않음.... 안타깝게도... 테이블에 속성정의를 해주어야 함... 귀찮더라도 작업 바람
// table에 border="1" width="530" 을 추가하면 됨
%>
<?xml version="1.0" encoding="UTF-8" standalone="no" ?>
<HWPML Style="embed" SubVersion="8.0.0.0" Version="2.8">
<HEAD SecCnt="1">
<DOCSUMMARY>
</DOCSUMMARY>
<DOCSETTING>
<BEGINNUMBER Endnote="1" Equation="1" Footnote="1" Page="1" Picture="1" Table="1"/>
<CARETPOS List="0" Para="7" Pos="8"/>
</DOCSETTING>
<MAPPINGTABLE>
<BINITEM APath="http://<%=request.getServerName() %>:<%=request.getServerPort() %><c:url value='/cmm/getPfImage.do?physicalFileNm=${charger.signFileNmPhysicl}' />" RPath="" Type="Link"/>
<BINDATALIST Count="<c:url value="${fn:length(resultList) }" />">
<c:forEach var="resultItem" items="${resultList}" varStatus="status" end="3" step="2">
	<BINITEM APath="http://<%=request.getServerName() %>:<%=request.getServerPort() %><c:url value='/fcltyMng/fdown/getRepairAttachFile.do?physicalFileNm=${resultItem.atchFileNmPhysicl }' />" RPath="" Type="Link"/>
	<c:if test="${fn:length(resultList) gt status.index+1 }">
	<BINITEM APath="http://<%=request.getServerName() %>:<%=request.getServerPort() %><c:url value='/fcltyMng/fdown/getRepairAttachFile.do?physicalFileNm=${resultList[status.index+1].atchFileNmPhysicl }' />" RPath="" Type="Link"/>
	</c:if>
</c:forEach>
</BINDATALIST>
<FACENAMELIST>
<FONTFACE Count="5" Lang="Hangul">
<FONT Id="0" Name="한컴바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="1" Name="굴림" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="2" Name="바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="3" Name="한컴바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="4" Name="휴먼명조" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="4" StrokeVariation="1" Weight="5" XHeight="1"/>
</FONT>
</FONTFACE>
<FONTFACE Count="5" Lang="Latin">
<FONT Id="0" Name="한컴바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="1" Name="굴림" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="2" Name="바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="3" Name="한컴바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="4" Name="HCI Poppy" Type="hft">
<TYPEINFO ArmStyle="0" Contrast="0" FamilyType="1" Letterform="0" Midline="0" Proportion="0" StrokeVariation="0" Weight="0" XHeight="0"/>
</FONT>
</FONTFACE>
<FONTFACE Count="5" Lang="Hanja">
<FONT Id="0" Name="한컴바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="1" Name="굴림" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="2" Name="바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="3" Name="한컴바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="4" Name="한양신명조" Type="hft">
<TYPEINFO ArmStyle="0" Contrast="0" FamilyType="1" Letterform="0" Midline="0" Proportion="0" StrokeVariation="0" Weight="0" XHeight="0"/>
</FONT>
</FONTFACE>
<FONTFACE Count="5" Lang="Japanese">
<FONT Id="0" Name="한컴바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="1" Name="굴림" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="2" Name="바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="3" Name="한컴바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="4" Name="한양신명조" Type="hft">
<TYPEINFO ArmStyle="0" Contrast="0" FamilyType="1" Letterform="0" Midline="0" Proportion="0" StrokeVariation="0" Weight="0" XHeight="0"/>
</FONT>
</FONTFACE>
<FONTFACE Count="5" Lang="Other">
<FONT Id="0" Name="한컴바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="1" Name="굴림" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="2" Name="바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="3" Name="한컴바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="4" Name="한양신명조" Type="hft">
<TYPEINFO ArmStyle="0" Contrast="0" FamilyType="1" Letterform="0" Midline="0" Proportion="0" StrokeVariation="0" Weight="0" XHeight="0"/>
</FONT>
</FONTFACE>
<FONTFACE Count="5" Lang="Symbol">
<FONT Id="0" Name="한컴바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="1" Name="굴림" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="2" Name="바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="3" Name="한컴바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="4" Name="한양신명조" Type="hft">
<TYPEINFO ArmStyle="0" Contrast="0" FamilyType="1" Letterform="0" Midline="0" Proportion="0" StrokeVariation="0" Weight="0" XHeight="0"/>
</FONT>
</FONTFACE>
<FONTFACE Count="5" Lang="User">
<FONT Id="0" Name="한컴바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="1" Name="굴림" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="2" Name="바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="3" Name="한컴바탕" Type="ttf">
<TYPEINFO ArmStyle="1" Contrast="0" FamilyType="2" Letterform="1" Midline="1" Proportion="0" StrokeVariation="1" Weight="6" XHeight="1"/>
</FONT>
<FONT Id="4" Name="명조" Type="hft">
<TYPEINFO ArmStyle="0" Contrast="0" FamilyType="1" Letterform="0" Midline="0" Proportion="0" StrokeVariation="0" Weight="0" XHeight="0"/>
</FONT>
</FONTFACE>
</FACENAMELIST>
<BORDERFILLLIST Count="13">
<BORDERFILL BackSlash="0" BreakCellSeparateLine="0" CenterLine="0" CounterBackSlash="0" CounterSlash="0" CrookedSlash="0" Id="1" Shadow="false" Slash="0" ThreeD="false">
<LEFTBORDER Type="Solid" Width="0.12mm"/>
<RIGHTBORDER Type="Solid" Width="0.12mm"/>
<TOPBORDER Type="Solid" Width="0.12mm"/>
<BOTTOMBORDER Type="Solid" Width="0.12mm"/>
</BORDERFILL>
<BORDERFILL BackSlash="0" BreakCellSeparateLine="0" CenterLine="0" CounterBackSlash="0" CounterSlash="0" CrookedSlash="0" Id="2" Shadow="false" Slash="0" ThreeD="false">
<LEFTBORDER Type="Solid" Width="0.4mm"/>
<RIGHTBORDER Type="Solid" Width="0.12mm"/>
<TOPBORDER Type="Solid" Width="0.12mm"/>
<BOTTOMBORDER Type="Solid" Width="0.4mm"/>
</BORDERFILL>
<BORDERFILL BackSlash="0" BreakCellSeparateLine="0" CenterLine="0" CounterBackSlash="0" CounterSlash="0" CrookedSlash="0" Id="3" Shadow="false" Slash="0" ThreeD="false">
<LEFTBORDER Type="Solid" Width="0.12mm"/>
<RIGHTBORDER Type="Solid" Width="0.4mm"/>
<TOPBORDER Type="Solid" Width="0.12mm"/>
<BOTTOMBORDER Type="Solid" Width="0.4mm"/>
</BORDERFILL>
<BORDERFILL BackSlash="0" BreakCellSeparateLine="0" CenterLine="0" CounterBackSlash="0" CounterSlash="0" CrookedSlash="0" Id="4" Shadow="false" Slash="0" ThreeD="false">
<LEFTBORDER Type="Solid" Width="0.4mm"/>
<RIGHTBORDER Type="Solid" Width="0.4mm"/>
<TOPBORDER Type="Solid" Width="0.12mm"/>
<BOTTOMBORDER Type="Solid" Width="0.12mm"/>
</BORDERFILL>
<BORDERFILL BackSlash="0" BreakCellSeparateLine="0" CenterLine="0" CounterBackSlash="0" CounterSlash="0" CrookedSlash="0" Id="5" Shadow="false" Slash="0" ThreeD="false">
<LEFTBORDER Type="Solid" Width="0.4mm"/>
<RIGHTBORDER Type="Solid" Width="0.4mm"/>
<TOPBORDER Type="Solid" Width="0.4mm"/>
<BOTTOMBORDER Type="Solid" Width="0.4mm"/>
</BORDERFILL>
<BORDERFILL BackSlash="0" BreakCellSeparateLine="0" CenterLine="0" CounterBackSlash="0" CounterSlash="0" CrookedSlash="0" Id="6" Shadow="false" Slash="0" ThreeD="false">
<LEFTBORDER Type="Solid" Width="0.4mm"/>
<RIGHTBORDER Type="Solid" Width="0.12mm"/>
<TOPBORDER Type="Solid" Width="0.4mm"/>
<BOTTOMBORDER Type="Solid" Width="0.12mm"/>
<FILLBRUSH>
<GRADATION Alpha="0" Angle="0" CenterX="50" CenterY="50" ColorNum="2" Step="50" StepCenter="50" Type="Linear">
<COLOR Value="16777215"/>
<COLOR Value="12632256"/>
</GRADATION>
</FILLBRUSH>
</BORDERFILL>
<BORDERFILL BackSlash="0" BreakCellSeparateLine="0" CenterLine="0" CounterBackSlash="0" CounterSlash="0" CrookedSlash="0" Id="7" Shadow="false" Slash="0" ThreeD="false">
<LEFTBORDER Type="Solid" Width="0.12mm"/>
<RIGHTBORDER Type="Solid" Width="0.4mm"/>
<TOPBORDER Type="Solid" Width="0.4mm"/>
<BOTTOMBORDER Type="Solid" Width="0.12mm"/>
<FILLBRUSH>
<GRADATION Alpha="0" Angle="0" CenterX="50" CenterY="50" ColorNum="2" Step="50" StepCenter="50" Type="Linear">
<COLOR Value="16777215"/>
<COLOR Value="12632256"/>
</GRADATION>
</FILLBRUSH>
</BORDERFILL>
<BORDERFILL BackSlash="0" BreakCellSeparateLine="0" CenterLine="0" CounterBackSlash="0" CounterSlash="0" CrookedSlash="0" Id="8" Shadow="false" Slash="0" ThreeD="false">
<LEFTBORDER Type="Solid" Width="0.4mm"/>
<RIGHTBORDER Type="Solid" Width="0.4mm"/>
<TOPBORDER Type="Solid" Width="0.12mm"/>
<BOTTOMBORDER Type="Solid" Width="0.4mm"/>
</BORDERFILL>
<BORDERFILL BackSlash="0" BreakCellSeparateLine="0" CenterLine="0" CounterBackSlash="0" CounterSlash="0" CrookedSlash="0" Id="9" Shadow="false" Slash="0" ThreeD="false">
<LEFTBORDER Type="None" Width="0.1mm"/>
<RIGHTBORDER Type="None" Width="0.1mm"/>
<TOPBORDER Type="None" Width="0.1mm"/>
<BOTTOMBORDER Type="None" Width="0.1mm"/>
<DIAGONAL Type="Solid" Width="0.1mm"/>
<FILLBRUSH>
<WINDOWBRUSH Alpha="0" FaceColor="4294967295" HatchColor="4278190080"/>
</FILLBRUSH>
</BORDERFILL>
<BORDERFILL BackSlash="0" BreakCellSeparateLine="0" CenterLine="0" CounterBackSlash="0" CounterSlash="0" CrookedSlash="0" Id="10" Shadow="false" Slash="0" ThreeD="false">
<LEFTBORDER Type="Solid" Width="0.4mm"/>
<RIGHTBORDER Type="Solid" Width="0.1mm"/>
<TOPBORDER Type="Solid" Width="0.4mm"/>
<BOTTOMBORDER Type="Solid" Width="0.12mm"/>
</BORDERFILL>
<BORDERFILL BackSlash="0" BreakCellSeparateLine="0" CenterLine="0" CounterBackSlash="0" CounterSlash="0" CrookedSlash="0" Id="11" Shadow="false" Slash="0" ThreeD="false">
<LEFTBORDER Type="Solid" Width="0.1mm"/>
<RIGHTBORDER Type="Solid" Width="0.4mm"/>
<TOPBORDER Type="Solid" Width="0.4mm"/>
<BOTTOMBORDER Type="Solid" Width="0.12mm"/>
</BORDERFILL>
<BORDERFILL BackSlash="0" BreakCellSeparateLine="0" CenterLine="0" CounterBackSlash="0" CounterSlash="0" CrookedSlash="0" Id="12" Shadow="false" Slash="0" ThreeD="false">
<LEFTBORDER Type="Solid" Width="0.4mm"/>
<RIGHTBORDER Type="Solid" Width="0.1mm"/>
<TOPBORDER Type="Solid" Width="0.12mm"/>
<BOTTOMBORDER Type="Solid" Width="0.12mm"/>
</BORDERFILL>
<BORDERFILL BackSlash="0" BreakCellSeparateLine="0" CenterLine="0" CounterBackSlash="0" CounterSlash="0" CrookedSlash="0" Id="13" Shadow="false" Slash="0" ThreeD="false">
<LEFTBORDER Type="Solid" Width="0.1mm"/>
<RIGHTBORDER Type="Solid" Width="0.4mm"/>
<TOPBORDER Type="Solid" Width="0.12mm"/>
<BOTTOMBORDER Type="Solid" Width="0.12mm"/>
</BORDERFILL>
</BORDERFILLLIST>
<CHARSHAPELIST Count="23">
<CHARSHAPE BorderFillId="0" Height="1000" Id="0" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="0" Height="1000" Id="1" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="2" Hanja="2" Japanese="2" Latin="2" Other="2" Symbol="2" User="2"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="0" Height="900" Id="2" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="1" Hanja="1" Japanese="1" Latin="1" Other="1" Symbol="1" User="1"/>
<RATIO Hangul="98" Hanja="98" Japanese="98" Latin="98" Other="98" Symbol="98" User="98"/>
<CHARSPACING Hangul="-2" Hanja="-2" Japanese="-2" Latin="-2" Other="-2" Symbol="-2" User="-2"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="0" Height="900" Id="3" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="2" Hanja="2" Japanese="2" Latin="2" Other="2" Symbol="2" User="2"/>
<RATIO Hangul="95" Hanja="95" Japanese="95" Latin="95" Other="95" Symbol="95" User="95"/>
<CHARSPACING Hangul="-5" Hanja="-5" Japanese="-5" Latin="-5" Other="-5" Symbol="-5" User="-5"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="0" Height="1000" Id="4" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="1" Hanja="1" Japanese="1" Latin="1" Other="1" Symbol="1" User="1"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="0" Height="1400" Id="5" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="3" Hanja="3" Japanese="3" Latin="3" Other="3" Symbol="3" User="3"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="0" Height="2000" Id="6" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="3" Hanja="3" Japanese="3" Latin="3" Other="3" Symbol="3" User="3"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<BOLD/>
<UNDERLINE Color="0" Shape="Solid" Type="Bottom"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="0" Height="1200" Id="7" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="4" Hanja="4" Japanese="4" Latin="4" Other="4" Symbol="4" User="4"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="0" Height="1200" Id="8" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="4" Hanja="4" Japanese="4" Latin="4" Other="4" Symbol="4" User="4"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="-6" Hanja="-6" Japanese="-6" Latin="-6" Other="-6" Symbol="-6" User="-6"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="0" Height="1100" Id="9" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="4" Hanja="4" Japanese="4" Latin="4" Other="4" Symbol="4" User="4"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="9" Height="1000" Id="10" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="3" Hanja="3" Japanese="3" Latin="3" Other="3" Symbol="3" User="3"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="9" Height="2000" Id="11" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="3" Hanja="3" Japanese="3" Latin="3" Other="3" Symbol="3" User="3"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="9" Height="2000" Id="12" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="3" Hanja="3" Japanese="3" Latin="3" Other="3" Symbol="3" User="3"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<BOLD/>
<UNDERLINE Color="0" Shape="Solid" Type="Bottom"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="9" Height="1600" Id="13" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="3" Hanja="3" Japanese="3" Latin="3" Other="3" Symbol="3" User="3"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="9" Height="1500" Id="14" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="3" Hanja="3" Japanese="3" Latin="3" Other="3" Symbol="3" User="3"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="9" Height="1500" Id="15" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="3" Hanja="3" Japanese="3" Latin="3" Other="3" Symbol="3" User="3"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<BOLD/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="9" Height="1500" Id="16" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="3" Hanja="3" Japanese="3" Latin="3" Other="3" Symbol="3" User="3"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="-13" Hanja="-13" Japanese="-13" Latin="-13" Other="-13" Symbol="-13" User="-13"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="9" Height="1500" Id="17" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="3" Hanja="3" Japanese="3" Latin="3" Other="3" Symbol="3" User="3"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="-13" Hanja="-13" Japanese="-13" Latin="-13" Other="-13" Symbol="-13" User="-13"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<UNDERLINE Color="0" Shape="Solid" Type="Bottom"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="9" Height="1500" Id="18" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="3" Hanja="3" Japanese="3" Latin="3" Other="3" Symbol="3" User="3"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="-1" Hanja="-1" Japanese="-1" Latin="-1" Other="-1" Symbol="-1" User="-1"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="9" Height="1500" Id="19" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="3" Hanja="3" Japanese="3" Latin="3" Other="3" Symbol="3" User="3"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="-5" Hanja="-5" Japanese="-5" Latin="-5" Other="-5" Symbol="-5" User="-5"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="9" Height="2000" Id="20" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="3" Hanja="3" Japanese="3" Latin="3" Other="3" Symbol="3" User="3"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<BOLD/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="9" Height="1500" Id="21" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="3" Hanja="3" Japanese="3" Latin="3" Other="3" Symbol="3" User="3"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="-20" Hanja="-20" Japanese="-20" Latin="-20" Other="-20" Symbol="-20" User="-20"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
</CHARSHAPE>
<CHARSHAPE BorderFillId="9" Height="1000" Id="22" ShadeColor="4294967295" SymMark="0" TextColor="0" UseFontSpace="false" UseKerning="false">
<FONTID Hangul="3" Hanja="3" Japanese="3" Latin="3" Other="3" Symbol="3" User="3"/>
<RATIO Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHARSPACING Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<RELSIZE Hangul="100" Hanja="100" Japanese="100" Latin="100" Other="100" Symbol="100" User="100"/>
<CHAROFFSET Hangul="0" Hanja="0" Japanese="0" Latin="0" Other="0" Symbol="0" User="0"/>
<ITALIC/>
</CHARSHAPE>
</CHARSHAPELIST>
<TABDEFLIST Count="3">
<TABDEF AutoTabLeft="false" AutoTabRight="false" Id="0"/>
<TABDEF AutoTabLeft="true" AutoTabRight="false" Id="1"/>
<TABDEF AutoTabLeft="false" AutoTabRight="true" Id="2">
<TABITEM Leader="None" Pos="0" Type="Left"/>
</TABDEF>
</TABDEFLIST>
<NUMBERINGLIST Count="1">
<NUMBERING Id="1" Start="0">
<PARAHEAD Alignment="Right" AutoIndent="false" Level="1" NumFormat="Digit" Start="1" TextOffset="50" TextOffsetType="percent" UseInstWidth="false" WidthAdjust="3000">
^1.</PARAHEAD>
<PARAHEAD Alignment="Right" AutoIndent="false" Level="2" NumFormat="HangulSyllable" Start="1" TextOffset="50" TextOffsetType="percent" UseInstWidth="false" WidthAdjust="4000">
^2.</PARAHEAD>
<PARAHEAD Alignment="Right" AutoIndent="false" Level="3" NumFormat="Digit" Start="1" TextOffset="50" TextOffsetType="percent" UseInstWidth="false" WidthAdjust="5000">
(^3)</PARAHEAD>
<PARAHEAD Alignment="Right" AutoIndent="false" Level="4" NumFormat="HangulSyllable" Start="1" TextOffset="50" TextOffsetType="percent" UseInstWidth="false" WidthAdjust="6000">
(^4)</PARAHEAD>
<PARAHEAD Alignment="Right" AutoIndent="false" Level="5" NumFormat="Digit" Start="1" TextOffset="50" TextOffsetType="percent" UseInstWidth="false" WidthAdjust="7000">
^5)</PARAHEAD>
<PARAHEAD Alignment="Right" AutoIndent="false" Level="6" NumFormat="HangulSyllable" Start="1" TextOffset="50" TextOffsetType="percent" UseInstWidth="false" WidthAdjust="8000">
^6)</PARAHEAD>
<PARAHEAD Alignment="Right" AutoIndent="false" Level="7" NumFormat="CircledDigit" Start="1" TextOffset="50" TextOffsetType="percent" UseInstWidth="false" WidthAdjust="9000">
^7</PARAHEAD>
</NUMBERING>
</NUMBERINGLIST>
<PARASHAPELIST Count="16">
<PARASHAPE Align="Left" AutoSpaceEAsianEng="false" AutoSpaceEAsianNum="false" BreakLatinWord="KeepWord" BreakNonLatinWord="false" Condense="0" FontLineHeight="false" HeadingType="None" Id="0" KeepLines="false" KeepWithNext="false" Level="0" LineWrap="Break" PageBreakBefore="false" SnapToGrid="false" TabDef="0" VerAlign="Baseline" WidowOrphan="false">
<PARAMARGIN Indent="0" Left="0" LineSpacing="160" LineSpacingType="Percent" Next="0" Prev="0" Right="0"/>
<PARABORDER BorderFill="9" Connect="false" IgnoreMargin="false"/>
</PARASHAPE>
<PARASHAPE Align="Justify" AutoSpaceEAsianEng="false" AutoSpaceEAsianNum="false" BreakLatinWord="KeepWord" BreakNonLatinWord="true" Condense="0" FontLineHeight="false" HeadingType="None" Id="1" KeepLines="false" KeepWithNext="false" Level="0" LineWrap="Break" PageBreakBefore="false" SnapToGrid="true" TabDef="0" VerAlign="Baseline" WidowOrphan="false">
<PARAMARGIN Indent="0" Left="0" LineSpacing="160" LineSpacingType="Percent" Next="0" Prev="0" Right="0"/>
<PARABORDER Connect="false" IgnoreMargin="false"/>
</PARASHAPE>
<PARASHAPE Align="Justify" AutoSpaceEAsianEng="false" AutoSpaceEAsianNum="false" BreakLatinWord="KeepWord" BreakNonLatinWord="true" Condense="0" FontLineHeight="false" HeadingType="None" Id="2" KeepLines="false" KeepWithNext="false" Level="0" LineWrap="Break" PageBreakBefore="false" SnapToGrid="true" TabDef="1" VerAlign="Baseline" WidowOrphan="false">
<PARAMARGIN Indent="-2624" Left="0" LineSpacing="130" LineSpacingType="Percent" Next="0" Prev="0" Right="0"/>
<PARABORDER Connect="false" IgnoreMargin="false"/>
</PARASHAPE>
<PARASHAPE Align="Justify" AutoSpaceEAsianEng="false" AutoSpaceEAsianNum="false" BreakLatinWord="KeepWord" BreakNonLatinWord="true" Condense="20" FontLineHeight="false" HeadingType="Outline" Id="3" KeepLines="false" KeepWithNext="false" Level="0" LineWrap="Break" PageBreakBefore="false" SnapToGrid="true" TabDef="0" VerAlign="Baseline" WidowOrphan="false">
<PARAMARGIN Indent="0" Left="0" LineSpacing="160" LineSpacingType="Percent" Next="0" Prev="0" Right="0"/>
<PARABORDER Connect="false" IgnoreMargin="false"/>
</PARASHAPE>
<PARASHAPE Align="Justify" AutoSpaceEAsianEng="false" AutoSpaceEAsianNum="false" BreakLatinWord="KeepWord" BreakNonLatinWord="true" Condense="0" FontLineHeight="false" HeadingType="Outline" Id="4" KeepLines="false" KeepWithNext="false" Level="1" LineWrap="Break" PageBreakBefore="false" SnapToGrid="true" TabDef="1" VerAlign="Baseline" WidowOrphan="false">
<PARAMARGIN Indent="0" Left="0" LineSpacing="160" LineSpacingType="Percent" Next="0" Prev="0" Right="0"/>
<PARABORDER Connect="false" IgnoreMargin="false"/>
</PARASHAPE>
<PARASHAPE Align="Justify" AutoSpaceEAsianEng="false" AutoSpaceEAsianNum="false" BreakLatinWord="KeepWord" BreakNonLatinWord="true" Condense="0" FontLineHeight="false" HeadingType="Outline" Id="5" KeepLines="false" KeepWithNext="false" Level="2" LineWrap="Break" PageBreakBefore="false" SnapToGrid="true" TabDef="0" VerAlign="Baseline" WidowOrphan="false">
<PARAMARGIN Indent="0" Left="0" LineSpacing="160" LineSpacingType="Percent" Next="0" Prev="0" Right="0"/>
<PARABORDER Connect="false" IgnoreMargin="false"/>
</PARASHAPE>
<PARASHAPE Align="Justify" AutoSpaceEAsianEng="false" AutoSpaceEAsianNum="false" BreakLatinWord="KeepWord" BreakNonLatinWord="true" Condense="0" FontLineHeight="false" HeadingType="Outline" Id="6" KeepLines="false" KeepWithNext="false" Level="3" LineWrap="Break" PageBreakBefore="false" SnapToGrid="true" TabDef="1" VerAlign="Baseline" WidowOrphan="false">
<PARAMARGIN Indent="0" Left="0" LineSpacing="160" LineSpacingType="Percent" Next="0" Prev="0" Right="0"/>
<PARABORDER Connect="false" IgnoreMargin="false"/>
</PARASHAPE>
<PARASHAPE Align="Justify" AutoSpaceEAsianEng="false" AutoSpaceEAsianNum="false" BreakLatinWord="KeepWord" BreakNonLatinWord="true" Condense="0" FontLineHeight="false" HeadingType="Outline" Id="7" KeepLines="false" KeepWithNext="false" Level="4" LineWrap="Break" PageBreakBefore="false" SnapToGrid="true" TabDef="0" VerAlign="Baseline" WidowOrphan="false">
<PARAMARGIN Indent="0" Left="0" LineSpacing="160" LineSpacingType="Percent" Next="0" Prev="0" Right="0"/>
<PARABORDER Connect="false" IgnoreMargin="false"/>
</PARASHAPE>
<PARASHAPE Align="Justify" AutoSpaceEAsianEng="false" AutoSpaceEAsianNum="false" BreakLatinWord="KeepWord" BreakNonLatinWord="true" Condense="0" FontLineHeight="false" HeadingType="Outline" Id="8" KeepLines="false" KeepWithNext="false" Level="5" LineWrap="Break" PageBreakBefore="false" SnapToGrid="true" TabDef="1" VerAlign="Baseline" WidowOrphan="false">
<PARAMARGIN Indent="0" Left="0" LineSpacing="160" LineSpacingType="Percent" Next="0" Prev="0" Right="0"/>
<PARABORDER Connect="false" IgnoreMargin="false"/>
</PARASHAPE>
<PARASHAPE Align="Justify" AutoSpaceEAsianEng="false" AutoSpaceEAsianNum="false" BreakLatinWord="KeepWord" BreakNonLatinWord="true" Condense="0" FontLineHeight="false" HeadingType="Outline" Id="9" KeepLines="false" KeepWithNext="false" Level="6" LineWrap="Break" PageBreakBefore="false" SnapToGrid="true" TabDef="1" VerAlign="Baseline" WidowOrphan="false">
<PARAMARGIN Indent="0" Left="0" LineSpacing="160" LineSpacingType="Percent" Next="0" Prev="0" Right="0"/>
<PARABORDER Connect="false" IgnoreMargin="false"/>
</PARASHAPE>
<PARASHAPE Align="Justify" AutoSpaceEAsianEng="false" AutoSpaceEAsianNum="false" BreakLatinWord="KeepWord" BreakNonLatinWord="true" Condense="20" FontLineHeight="false" HeadingType="None" Id="10" KeepLines="false" KeepWithNext="false" Level="0" LineWrap="Break" PageBreakBefore="false" SnapToGrid="true" TabDef="0" VerAlign="Baseline" WidowOrphan="false">
<PARAMARGIN Indent="0" Left="3000" LineSpacing="160" LineSpacingType="Percent" Next="0" Prev="0" Right="0"/>
<PARABORDER Connect="false" IgnoreMargin="false"/>
</PARASHAPE>
<PARASHAPE Align="Justify" AutoSpaceEAsianEng="false" AutoSpaceEAsianNum="false" BreakLatinWord="KeepWord" BreakNonLatinWord="false" Condense="0" FontLineHeight="false" HeadingType="None" Id="11" KeepLines="false" KeepWithNext="false" Level="0" LineWrap="Break" PageBreakBefore="false" SnapToGrid="true" TabDef="2" VerAlign="Baseline" WidowOrphan="false">
<PARAMARGIN Indent="0" Left="0" LineSpacing="150" LineSpacingType="Percent" Next="0" Prev="0" Right="0"/>
<PARABORDER Connect="false" IgnoreMargin="false"/>
</PARASHAPE>
<PARASHAPE Align="Center" AutoSpaceEAsianEng="false" AutoSpaceEAsianNum="false" BreakLatinWord="KeepWord" BreakNonLatinWord="false" Condense="0" FontLineHeight="false" HeadingType="None" Id="12" KeepLines="false" KeepWithNext="false" Level="0" LineWrap="Break" PageBreakBefore="false" SnapToGrid="false" TabDef="0" VerAlign="Baseline" WidowOrphan="false">
<PARAMARGIN Indent="0" Left="0" LineSpacing="160" LineSpacingType="Percent" Next="0" Prev="0" Right="0"/>
<PARABORDER Connect="false" IgnoreMargin="false"/>
</PARASHAPE>
<PARASHAPE Align="Justify" AutoSpaceEAsianEng="false" AutoSpaceEAsianNum="false" BreakLatinWord="KeepWord" BreakNonLatinWord="true" Condense="0" FontLineHeight="false" HeadingType="None" Id="13" KeepLines="false" KeepWithNext="false" Level="0" LineWrap="Break" PageBreakBefore="false" SnapToGrid="false" TabDef="0" VerAlign="Baseline" WidowOrphan="false">
<PARAMARGIN Indent="0" Left="0" LineSpacing="160" LineSpacingType="Percent" Next="0" Prev="0" Right="0"/>
<PARABORDER Connect="false" IgnoreMargin="false"/>
</PARASHAPE>
<PARASHAPE Align="Justify" AutoSpaceEAsianEng="false" AutoSpaceEAsianNum="false" BreakLatinWord="KeepWord" BreakNonLatinWord="true" Condense="0" FontLineHeight="false" HeadingType="None" Id="14" KeepLines="false" KeepWithNext="false" Level="0" LineWrap="Break" PageBreakBefore="false" SnapToGrid="false" TabDef="0" VerAlign="Baseline" WidowOrphan="false">
<PARAMARGIN Indent="-4416" Left="0" LineSpacing="160" LineSpacingType="Percent" Next="0" Prev="0" Right="0"/>
<PARABORDER Connect="false" IgnoreMargin="false"/>
</PARASHAPE>
<PARASHAPE Align="Justify" AutoSpaceEAsianEng="false" AutoSpaceEAsianNum="false" BreakLatinWord="KeepWord" BreakNonLatinWord="true" Condense="0" FontLineHeight="false" HeadingType="None" Id="15" KeepLines="false" KeepWithNext="false" Level="0" LineWrap="Break" PageBreakBefore="false" SnapToGrid="false" TabDef="0" VerAlign="Baseline" WidowOrphan="false">
<PARAMARGIN Indent="0" Left="0" LineSpacing="160" LineSpacingType="Percent" Next="0" Prev="0" Right="0"/>
<PARABORDER BorderFill="9" Connect="false" IgnoreMargin="false"/>
</PARASHAPE>
</PARASHAPELIST>
<STYLELIST Count="12">
<STYLE CharShape="10" EngName="Normal" Id="0" LangId="1042" LockForm="0" Name="바탕글" NextStyle="0" ParaShape="15" Type="Para"/>
<STYLE CharShape="1" EngName="Body" Id="1" LangId="1042" LockForm="0" Name="본문" NextStyle="1" ParaShape="10" Type="Para"/>
<STYLE CharShape="1" EngName="Outline 1" Id="2" LangId="1042" LockForm="0" Name="개요 1" NextStyle="2" ParaShape="3" Type="Para"/>
<STYLE CharShape="1" EngName="Outline 2" Id="3" LangId="1042" LockForm="0" Name="개요 2" NextStyle="3" ParaShape="4" Type="Para"/>
<STYLE CharShape="1" EngName="Outline 3" Id="4" LangId="1042" LockForm="0" Name="개요 3" NextStyle="4" ParaShape="5" Type="Para"/>
<STYLE CharShape="1" EngName="Outline 4" Id="5" LangId="1042" LockForm="0" Name="개요 4" NextStyle="5" ParaShape="6" Type="Para"/>
<STYLE CharShape="1" EngName="Outline 5" Id="6" LangId="1042" LockForm="0" Name="개요 5" NextStyle="6" ParaShape="7" Type="Para"/>
<STYLE CharShape="1" EngName="Outline 6" Id="7" LangId="1042" LockForm="0" Name="개요 6" NextStyle="7" ParaShape="8" Type="Para"/>
<STYLE CharShape="1" EngName="Outline 7" Id="8" LangId="1042" LockForm="0" Name="개요 7" NextStyle="8" ParaShape="9" Type="Para"/>
<STYLE CharShape="4" EngName="Page Number" Id="9" LangId="1042" LockForm="0" Name="쪽 번호" NextStyle="9" ParaShape="1" Type="Para"/>
<STYLE CharShape="2" EngName="Header" Id="10" LangId="1042" LockForm="0" Name="머리말" NextStyle="10" ParaShape="11" Type="Para"/>
<STYLE CharShape="3" EngName="Footnote" Id="11" LangId="1042" LockForm="0" Name="각주" NextStyle="11" ParaShape="2" Type="Para"/>
</STYLELIST>
</MAPPINGTABLE>
<COMPATIBLEDOCUMENT TargetProgram="None">
<LAYOUTCOMPATIBILITY AdjustBaselineInFixedLinespacing="false" AdjustLineheightToFont="false" AdjustParaBorderOffsetWithBorder="false" AdjustParaBorderfillToSpacing="false" ApplyAtLeastToPercent100Pct="false" ApplyCharSpacingToCharGrid="false" ApplyExtendHeaderFooterEachSection="false" ApplyFontWeightToBold="false" ApplyFontspaceToLatin="false" ApplyNextspacingOfLastPara="false" ApplyParaBorderToOutside="false" ApplyPrevspacingBeneathObject="false" BaseCharUnitOfIndentOnFirstChar="false" BaseCharUnitOnEAsian="false" BaseLinespacingOnLinegrid="false" ConnectParaBorderfillOfEqualBorder="false" DoNotAdjustEmptyAnchorLine="false" DoNotAdjustWordInJustify="false" DoNotAlignWhitespaceOnRight="false" DoNotApplyAutoSpaceEAsianEng="false" DoNotApplyAutoSpaceEAsianNum="false" DoNotApplyExtensionCharCompose="false" DoNotApplyGridInHeaderFooter="false" DoNotApplyImageEffect="false" DoNotApplyShapeComment="false" DoNotApplyStrikeoutWithUnderline="false" DoNotApplyVertOffsetOfForward="false" DoNotFormattingAtBeneathAnchor="false" DoNotHoldAnchorOfTable="false" ExtendLineheightToOffset="false" ExtendLineheightToParaBorderOffset="false" ExtendVertLimitToPageMargins="false" FixedUnderlineWidth="false" OverlapBothAllowOverlap="false" TreatQuotationAsLatin="false" UseInnerUnderline="false" UseLowercaseStrikeout="false"/>
</COMPATIBLEDOCUMENT>
</HEAD>
<BODY>
<SECTION Id="0">
<P ColumnBreak="false" PageBreak="false" ParaShape="12" Style="0">
<TEXT CharShape="5">
<SECDEF CharGrid="0" FirstBorder="false" FirstFill="false" LineGrid="0" OutlineShape="1" SpaceColumns="1134" TabStop="8000" TextDirection="0" TextVerticalWidthHead="0">
<STARTNUMBER Equation="0" Figure="0" Page="0" PageStartsOn="Both" Table="0"/>
<HIDE Border="false" EmptyLine="false" Fill="false" Footer="false" Header="false" MasterPage="false" PageNumPos="false"/>
<PAGEDEF GutterType="LeftOnly" Height="84188" Landscape="0" Width="59528">
<PAGEMARGIN Bottom="4252" Footer="2835" Gutter="0" Header="2835" Left="7087" Right="7087" Top="5668"/>
</PAGEDEF>
<FOOTNOTESHAPE>
<AUTONUMFORMAT SuffixChar=")" Superscript="false" Type="Digit"/>
<NOTELINE Length="5cm" Type="Solid" Width="0.1mm"/>
<NOTESPACING AboveLine="567" BelowLine="567" BetweenNotes="850"/>
<NOTENUMBERING NewNumber="1" Type="Continuous"/>
<NOTEPLACEMENT BeneathText="false" Place="EachColumn"/>
</FOOTNOTESHAPE>
<ENDNOTESHAPE>
<AUTONUMFORMAT SuffixChar=")" Superscript="false" Type="Digit"/>
<NOTELINE Length="5cm" Type="Solid" Width="0.1mm"/>
<NOTESPACING AboveLine="567" BelowLine="567" BetweenNotes="850"/>
<NOTENUMBERING NewNumber="1" Type="Continuous"/>
<NOTEPLACEMENT BeneathText="false" Place="EndOfDocument"/>
</ENDNOTESHAPE>
<PAGEBORDERFILL FillArea="Paper" FooterInside="false" HeaderInside="false" TextBorder="true" Type="Both">
<PAGEOFFSET Bottom="1417" Left="1417" Right="1417" Top="1417"/>
</PAGEBORDERFILL>
<PAGEBORDERFILL FillArea="Paper" FooterInside="false" HeaderInside="false" TextBorder="true" Type="Even">
<PAGEOFFSET Bottom="1417" Left="1417" Right="1417" Top="1417"/>
</PAGEBORDERFILL>
<PAGEBORDERFILL FillArea="Paper" FooterInside="false" HeaderInside="false" TextBorder="true" Type="Odd">
<PAGEOFFSET Bottom="1417" Left="1417" Right="1417" Top="1417"/>
</PAGEBORDERFILL>
</SECDEF>
<COLDEF Count="1" Layout="Left" SameGap="0" SameSize="true" Type="Newspaper"/>
<TABLE BorderFill="1" CellSpacing="0" ColCount="1" PageBreak="Cell" RepeatHeader="true" RowCount="1">
<SHAPEOBJECT InstId="1340405374" Lock="false" NumberingType="Table" ZOrder="5">
<SIZE Height="66089" HeightRelTo="Absolute" Protect="false" Width="44788" WidthRelTo="Absolute"/>
<POSITION AffectLSpacing="false" AllowOverlap="false" FlowWithText="true" HoldAnchorAndSO="false" HorzAlign="Left" HorzOffset="0" HorzRelTo="Para" TreatAsChar="true" VertAlign="Top" VertOffset="0" VertRelTo="Para"/>
<OUTSIDEMARGIN Bottom="141" Left="141" Right="141" Top="141"/>
</SHAPEOBJECT>
<INSIDEMARGIN Bottom="141" Left="141" Right="141" Top="141"/>
<ROW>
<CELL BorderFill="5" ColAddr="0" ColSpan="1" Dirty="false" Editable="false" HasMargin="false" Header="false" Height="66089" Protect="false" RowAddr="0" RowSpan="1" Width="44788">
<PARALIST LineWrap="Break" LinkListID="0" LinkListIDNext="0" TextDirection="0" VertAlign="Top">
<P ParaShape="15" Style="0">
<TEXT CharShape="11"/>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="11"/>
</P>
<P ParaShape="12" Style="0">
<TEXT CharShape="12">
<CHAR>
하 자 검 사 조 서</CHAR>
</TEXT>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="13"/>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="13"/>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="13">
<CHAR>
 </CHAR>
</TEXT>
<TEXT CharShape="14">
<CHAR>
 </CHAR>
</TEXT>
<TEXT CharShape="15">
<CHAR>
공사명 : <c:out value="${result.flawRprNm }" /></CHAR>
</TEXT>
<TEXT CharShape="14"/>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="14"/>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="14">
<CHAR>
                 </CHAR>
</TEXT>
<TEXT CharShape="14">
<CHAR>
20  년  월  일 준공</CHAR>
</TEXT>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="14">
<CHAR>
                 <c:out value="${result.ctrtDt }" /> <c:out value="${result.flawRprEntrpsNm }" /></CHAR>
</TEXT>
<TEXT CharShape="14">
<CHAR>
</CHAR>
</TEXT>
<TEXT CharShape="14">
</CHAR>
</TEXT>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="14">
<CHAR>
                 도급액 :  <c:out value="${result.ctrtAmt }" />원</CHAR>
</TEXT>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="14"/>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="14">
<CHAR>
     </CHAR>
</TEXT>
<TEXT CharShape="21">
<CHAR>
위 공사의 하자검사의 명을 받아  <c:out value="${result.flawExamDt }" /> 검사한 결과</CHAR>
</TEXT>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="16">
<CHAR>
      </CHAR>
</TEXT>
<TEXT CharShape="17">
<CHAR>
<c:out value="${result.castFlawEnnc }"/></CHAR>
</TEXT>
<TEXT CharShape="16">
<CHAR>
 확인함</CHAR>
</TEXT>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="14"/>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="14"/>
</P>
<P ParaShape="0" Style="0">
<TEXT CharShape="14">
<CHAR>
                                      <c:out value="${result.hwpDate }"/></CHAR>
</TEXT>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="14"/>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="14">
	<PICTURE Reverse="false">
	<SHAPEOBJECT InstId="1897708559" Lock="false" NumberingType="Figure" TextWrap="BehindText" ZOrder="0">
	<SIZE Height="4620" HeightRelTo="Absolute" Protect="false" Width="3960" WidthRelTo="Absolute"/>
	<POSITION AffectLSpacing="false" AllowOverlap="false" FlowWithText="true" HoldAnchorAndSO="false" HorzAlign="Left" HorzOffset="39071" HorzRelTo="Para" TreatAsChar="false" VertAlign="Top" VertOffset="900" VertRelTo="Para"/>
	<OUTSIDEMARGIN Bottom="0" Left="0" Right="0" Top="0"/>
	<SHAPECOMMENT></SHAPECOMMENT>
	</SHAPEOBJECT>
	<SHAPECOMPONENT GroupLevel="0" HorzFlip="false" InstID="823966736" OriHeight="4620" OriWidth="3960" VertFlip="false" XPos="0" YPos="0">
	<ROTATIONINFO Angle="0" CenterX="1980" CenterY="2310"/>
	<RENDERINGINFO>
	<TRANSMATRIX E1="1.00000" E2="0.00000" E3="0.00000" E4="0.00000" E5="1.00000" E6="0.00000"/>
	<SCAMATRIX E1="1.00000" E2="0.00000" E3="0.00000" E4="0.00000" E5="1.00000" E6="0.00000"/>
	<ROTMATRIX E1="1.00000" E2="0.00000" E3="0.00000" E4="0.00000" E5="1.00000" E6="0.00000"/>
	</RENDERINGINFO>
	</SHAPECOMPONENT>
	<IMAGERECT X0="0" X1="3960" X2="3960" X3="0" Y0="0" Y1="0" Y2="4620" Y3="4620"/>
	<IMAGECLIP Bottom="4560" Left="0" Right="3900" Top="0"/>
	<INSIDEMARGIN Bottom="0" Left="0" Right="0" Top="0"/>
	<IMAGE Alpha="0" BinItem="1" Bright="0" Contrast="0" Effect="RealPic"/>
	<EFFECTS/>
	</PICTURE>
<CHAR/>
</TEXT>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="14">
<CHAR>
                        하자검사원 : <c:out value="${result.flawExamUsrNm }" /></CHAR>
</TEXT>
<TEXT CharShape="14">
<CHAR>
</CHAR>
</TEXT>
<TEXT CharShape="14">
<CHAR>
  (인)</CHAR>
</TEXT>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="14">
<CHAR>
              </CHAR>
</TEXT>
<TEXT CharShape="18">
<CHAR>
                      </CHAR>
</TEXT>
<TEXT CharShape="14"/>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="14">
<CHAR>
                         </CHAR>
</TEXT>
<TEXT CharShape="19">
<CHAR>
         </CHAR>
</TEXT>
<TEXT CharShape="14">
<CHAR>
   </CHAR>
</TEXT>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="14"/>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="14"/>
</P>
<P ParaShape="12" Style="0">
<TEXT CharShape="20">
<CHAR>
여수광양항만공사사장 귀하</CHAR>
</TEXT>
</P>
</PARALIST>
</CELL>
</ROW>
</TABLE>
<CHAR/>
</TEXT>
</P>
<c:if test="${result.flawEnnc == 'Y'}">
<P ColumnBreak="false" PageBreak="true" ParaShape="12" Style="0">
<TEXT CharShape="6">
<CHAR>
하    자    내    용</CHAR>
</TEXT>
</P>
<P ParaShape="12" Style="0">
<TEXT CharShape="5">
<CHAR>
(<c:out value="${result.flawRprNm }" />)</CHAR>
</TEXT>
</P>
<P ParaShape="12" Style="0">
<TEXT CharShape="5"/>
</P>
<P ParaShape="12" Style="0">
<TEXT CharShape="5">
<TABLE BorderFill="1" CellSpacing="0" ColCount="2" PageBreak="Cell" RepeatHeader="true" RowCount="2">
<SHAPEOBJECT InstId="1851935990" Lock="false" NumberingType="Table" ZOrder="6">
<SIZE Height="13313" HeightRelTo="Absolute" Protect="false" Width="45034" WidthRelTo="Absolute"/>
<POSITION AffectLSpacing="false" AllowOverlap="false" FlowWithText="true" HoldAnchorAndSO="false" HorzAlign="Left" HorzOffset="0" HorzRelTo="Para" TreatAsChar="true" VertAlign="Top" VertOffset="0" VertRelTo="Para"/>
<OUTSIDEMARGIN Bottom="141" Left="141" Right="141" Top="141"/>
</SHAPEOBJECT>
<INSIDEMARGIN Bottom="141" Left="141" Right="141" Top="141"/>
<ROW>
<CELL BorderFill="6" ColAddr="0" ColSpan="1" Dirty="false" Editable="false" HasMargin="false" Header="false" Height="4534" Protect="false" RowAddr="0" RowSpan="1" Width="30724">
<PARALIST LineWrap="Break" LinkListID="0" LinkListIDNext="0" TextDirection="0" VertAlign="Center">
<P ParaShape="12" Style="0">
<TEXT CharShape="7">
<CHAR>
하자내용</CHAR>
</TEXT>
</P>
</PARALIST>
</CELL>
<CELL BorderFill="7" ColAddr="1" ColSpan="1" Dirty="false" Editable="false" HasMargin="false" Header="false" Height="4534" Protect="false" RowAddr="0" RowSpan="1" Width="14310">
<PARALIST LineWrap="Break" LinkListID="0" LinkListIDNext="0" TextDirection="0" VertAlign="Center">
<P ParaShape="12" Style="0">
<TEXT CharShape="7">
<CHAR>
비 고</CHAR>
</TEXT>
</P>
</PARALIST>
</CELL>
</ROW>
<ROW>
<CELL BorderFill="2" ColAddr="0" ColSpan="1" Dirty="false" Editable="false" HasMargin="false" Header="false" Height="8779" Protect="false" RowAddr="1" RowSpan="1" Width="30724">
<PARALIST LineWrap="Break" LinkListID="0" LinkListIDNext="0" TextDirection="0" VertAlign="Center">
<P ParaShape="14" Style="0">
<TEXT CharShape="8">
<CHAR>
<c:out value="${result.flawRprContents}"/></CHAR>
</TEXT>
</P>
</PARALIST>
</CELL>
<CELL BorderFill="3" ColAddr="1" ColSpan="1" Dirty="false" Editable="false" HasMargin="false" Header="false" Height="8779" Protect="false" RowAddr="1" RowSpan="1" Width="14310">
<PARALIST LineWrap="Break" LinkListID="0" LinkListIDNext="0" TextDirection="0" VertAlign="Center">
<P ParaShape="12" Style="0">
<TEXT CharShape="7">
<CHAR>
<c:out value="${result.rm}"/></CHAR>
</TEXT>
</P>
</PARALIST>
</CELL>
</ROW>
</TABLE>
<CHAR/>
</TEXT>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="5"/>
</P>
<P ParaShape="13" Style="0">
<TEXT CharShape="5">
<CHAR>
○ 사진대지</CHAR>
</TEXT>
</P>
<P ParaShape="12" Style="0">
<TEXT CharShape="5">
	<TABLE BorderFill="1" CellSpacing="0" ColCount="2" PageBreak="Cell" RepeatHeader="true" RowCount="4">

<SHAPEOBJECT InstId="1851935994" Lock="false" NumberingType="Table" ZOrder="7">
<SIZE Height="39188" HeightRelTo="Absolute" Protect="false" Width="45024" WidthRelTo="Absolute"/>
<POSITION AffectLSpacing="false" AllowOverlap="false" FlowWithText="true" HoldAnchorAndSO="false" HorzAlign="Left" HorzOffset="0" HorzRelTo="Para" TreatAsChar="true" VertAlign="Top" VertOffset="0" VertRelTo="Para"/>
<OUTSIDEMARGIN Bottom="141" Left="141" Right="141" Top="141"/>
</SHAPEOBJECT>
<INSIDEMARGIN Bottom="141" Left="141" Right="141" Top="141"/>

<c:forEach var="resultItem" items="${resultList}" varStatus="status" end="3" step="2">
<ROW>
<CELL BorderFill="10" ColAddr="0" ColSpan="1" Dirty="false" Editable="false" HasMargin="false" Header="false" Height="17413" Protect="false" RowAddr="0" RowSpan="1" Width="22512">
<PARALIST LineWrap="Break" LinkListID="0" LinkListIDNext="0" TextDirection="0" VertAlign="Center">
<P ParaShape="12" Style="0">
<TEXT CharShape="0">
<CHAR>
   </CHAR>
   
	<PICTURE Reverse="false">
	<SHAPEOBJECT InstId="1897708565" Lock="false" NumberingType="Figure" TextFlow="BothSides" ZOrder="1">
	<SIZE Height="13080" HeightRelTo="Absolute" Protect="false" Width="16740" WidthRelTo="Absolute"/>
	<POSITION AffectLSpacing="false" AllowOverlap="false" FlowWithText="true" HoldAnchorAndSO="false" HorzAlign="Left" HorzOffset="11864" HorzRelTo="Para" TreatAsChar="true" VertAlign="Top" VertOffset="0" VertRelTo="Para"/>
	<OUTSIDEMARGIN Bottom="0" Left="0" Right="0" Top="0"/>
	<SHAPECOMMENT></SHAPECOMMENT>
	</SHAPEOBJECT>
	<SHAPECOMPONENT GroupLevel="0" HorzFlip="false" InstID="823966742" OriHeight="13080" OriWidth="16740" VertFlip="false" XPos="0" YPos="0">
	<ROTATIONINFO Angle="0" CenterX="8370" CenterY="6540"/>
	<RENDERINGINFO>
	<TRANSMATRIX E1="1.00000" E2="0.00000" E3="0.00000" E4="0.00000" E5="1.00000" E6="0.00000"/>
	<SCAMATRIX E1="1.00000" E2="0.00000" E3="0.00000" E4="0.00000" E5="1.00000" E6="0.00000"/>
	<ROTMATRIX E1="1.00000" E2="0.00000" E3="0.00000" E4="0.00000" E5="1.00000" E6="0.00000"/>
	</RENDERINGINFO>
	</SHAPECOMPONENT>
	<IMAGERECT X0="0" X1="16740" X2="16740" X3="0" Y0="0" Y1="0" Y2="13080" Y3="13080"/>
	<IMAGECLIP Bottom="13080" Left="0" Right="16740" Top="0"/>
	<INSIDEMARGIN Bottom="0" Left="0" Right="0" Top="0"/>
	<IMAGE Alpha="0" BinItem="${status.index+2 }" Bright="0" Contrast="0" Effect="RealPic"/>
	<EFFECTS/>
	</PICTURE>
	
<CHAR/>
</TEXT>
</P>
</PARALIST>
</CELL>
<CELL BorderFill="11" ColAddr="1" ColSpan="1" Dirty="false" Editable="false" HasMargin="false" Header="false" Height="17413" Protect="false" RowAddr="0" RowSpan="1" Width="22512">
<PARALIST LineWrap="Break" LinkListID="0" LinkListIDNext="0" TextDirection="0" VertAlign="Center">
<P ParaShape="12" Style="0">
<TEXT CharShape="0">
<c:if test="${fn:length(resultList) gt status.index+1 }">
<PICTURE Reverse="false">
<SHAPEOBJECT InstId="1897742301" Lock="false" NumberingType="Figure" TextFlow="BothSides" ZOrder="2">
<SIZE Height="15960" HeightRelTo="Absolute" Protect="false" Width="21660" WidthRelTo="Absolute"/>
<POSITION AffectLSpacing="false" AllowOverlap="false" FlowWithText="true" HoldAnchorAndSO="false" HorzAlign="Left" HorzOffset="877" HorzRelTo="Para" TreatAsChar="true" VertAlign="Top" VertOffset="0" VertRelTo="Para"/>
<OUTSIDEMARGIN Bottom="0" Left="0" Right="0" Top="0"/>
<SHAPECOMMENT>
그림입니다.
</SHAPECOMMENT>
</SHAPEOBJECT>
<SHAPECOMPONENT GroupLevel="0" HorzFlip="false" InstID="824000478" OriHeight="15960" OriWidth="21660" VertFlip="false" XPos="0" YPos="0">
<ROTATIONINFO Angle="0" CenterX="10830" CenterY="7980"/>
<RENDERINGINFO>
<TRANSMATRIX E1="1.00000" E2="0.00000" E3="0.00000" E4="0.00000" E5="1.00000" E6="0.00000"/>
<SCAMATRIX E1="1.00000" E2="0.00000" E3="0.00000" E4="0.00000" E5="1.00000" E6="0.00000"/>
<ROTMATRIX E1="1.00000" E2="0.00000" E3="0.00000" E4="0.00000" E5="1.00000" E6="0.00000"/>
</RENDERINGINFO>
</SHAPECOMPONENT>
<IMAGERECT X0="0" X1="21660" X2="21660" X3="0" Y0="0" Y1="0" Y2="15960" Y3="15960"/>
<IMAGECLIP Bottom="15960" Left="0" Right="21660" Top="0"/>
<INSIDEMARGIN Bottom="0" Left="0" Right="0" Top="0"/>
<IMAGE Alpha="0" BinItem="${status.index+3 }" Bright="0" Contrast="0" Effect="RealPic"/>
<EFFECTS/>
</PICTURE>
</c:if>
<CHAR/>
</TEXT>
</P>
</PARALIST>
</CELL>
</ROW>

<ROW>
<CELL BorderFill="4" ColAddr="0" ColSpan="1" Dirty="false" Editable="false" HasMargin="false" Header="false" Height="1948" Protect="false" RowAddr="1" RowSpan="1" Width="22512">
<PARALIST LineWrap="Break" LinkListID="0" LinkListIDNext="0" TextDirection="0" VertAlign="Center">
<P ParaShape="13" Style="0">
<TEXT CharShape="9">
<CHAR>
<c:out value="${resultItem.atchFileSj }"/></CHAR>
</TEXT>
</P>
</PARALIST>
</CELL>
<CELL BorderFill="4" ColAddr="1" ColSpan="1" Dirty="false" Editable="false" HasMargin="false" Header="false" Height="1948" Protect="false" RowAddr="1" RowSpan="1" Width="22512">
<PARALIST LineWrap="Break" LinkListID="0" LinkListIDNext="0" TextDirection="0" VertAlign="Center">
<P ParaShape="13" Style="0">
<TEXT CharShape="9">
<CHAR>
<c:if test="${fn:length(resultList) gt status.index+1 }">
<c:out value="${resultList[status.index+1].atchFileSj }"/>
</c:if>
</TEXT>
<TEXT CharShape="9"/>
</P>
</PARALIST>
</CELL>
</ROW>
</c:forEach>

</TABLE>

<CHAR/>
</TEXT>
</P>
</SECTION>
</BODY>
</c:if>
<TAIL>
<SCRIPTCODE Type="JScript" Version="1.0">
<SCRIPTHEADER>
</SCRIPTHEADER>
<SCRIPTSOURCE>
</SCRIPTSOURCE>
</SCRIPTCODE>
</TAIL>
</HWPML>