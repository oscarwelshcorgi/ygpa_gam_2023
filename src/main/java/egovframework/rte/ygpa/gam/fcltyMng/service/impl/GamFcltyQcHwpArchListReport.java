/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcHwpBaseReportInterface;

/**
 * 
 * @author Jongmin
 * @since 2016. 2. 20.
 * @version 1.0
 * @see
 * <pre>
 * 건축 시설물 점검표 HML처리 CLASS
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 2. 20.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamFcltyQcHwpArchListReport extends GamFcltyQcHwpBaseReport implements GamFcltyQcHwpBaseReportInterface {
	private List<GamFcltyQcHwpMngResultInfo> qcDataList;
	private String docDate = null;
	
	public GamFcltyQcHwpArchListReport(List<GamFcltyQcHwpMngResultInfo> qcDataList, Map<String, Integer> fileIndexInfo) {
		super(fileIndexInfo);
		this.qcDataList = qcDataList;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM일 dd일 HH시 mm분 ss초", Locale.KOREA);
		docDate = formatter.format(new Date());
	}
	
	public String getHwpReport() throws Exception {
		StringBuilder result = new StringBuilder();

		result.append(getHeadElement());
		
		result.append("<BODY><SECTION Id=\"0\">\n");
		for(int i=0; i<qcDataList.size(); i++) {
			result.append(getBodyElement(i));
		}
		result.append("</SECTION></BODY>\n");

		result.append(getTailElement());
		return result.toString();
	}
	
	//GET HML HEAD ELEMENT
	protected StringBuilder getHeadElement() {
		StringBuilder sb = new StringBuilder();

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n");
		sb.append("<HWPML Style=\"embed\" SubVersion=\"7.0.0.0\" Version=\"2.7\">\n");
		sb.append("<HEAD SecCnt=\"1\">\n");
		sb.append("	<DOCSUMMARY><TITLE>건축 시설물 점검표</TITLE><AUTHOR>YGPA GIS Assets Management System</AUTHOR><DATE>" + docDate + "</DATE></DOCSUMMARY>\n");
		sb.append("	<DOCSETTING><BEGINNUMBER Endnote=\"1\" Equation=\"1\" Footnote=\"1\" Page=\"1\" Picture=\"1\" Table=\"1\"/><CARETPOS List=\"0\" Para=\"0\" Pos=\"29\"/></DOCSETTING>\n");
		sb.append("	<MAPPINGTABLE>\n");
		sb.append(getBinItemListElement());
		sb.append("		<FACENAMELIST><FONTFACE Count=\"3\" Lang=\"Hangul\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"휴먼옛체\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"5\" XHeight=\"1\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Latin\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양궁서\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"4\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Hanja\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Japanese\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Other\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Symbol\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"User\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE></FACENAMELIST>\n");
		sb.append("		<BORDERFILLLIST Count=\"3\"><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"1\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"2\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"None\" Width=\"0.1mm\"/><RIGHTBORDER Type=\"None\" Width=\"0.1mm\"/><TOPBORDER Type=\"None\" Width=\"0.1mm\"/><BOTTOMBORDER Type=\"None\" Width=\"0.1mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.1mm\"/><FILLBRUSH><WINDOWBRUSH Alpha=\"0\" FaceColor=\"4294967295\" HatchColor=\"4278190080\"/></FILLBRUSH></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"3\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL></BORDERFILLLIST>\n");
		sb.append("		<CHARSHAPELIST Count=\"13\"><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"0\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"1\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"2\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"3\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"4\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"2000\" Id=\"5\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"2\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"2000\" Id=\"6\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"2\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"7\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1200\" Id=\"8\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"9\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"90\" Hanja=\"90\" Japanese=\"90\" Latin=\"90\" Other=\"90\" Symbol=\"90\" User=\"90\"/><CHARSPACING Hangul=\"-10\" Hanja=\"-10\" Japanese=\"-10\" Latin=\"-10\" Other=\"-10\" Symbol=\"-10\" User=\"-10\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"10\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"90\" Hanja=\"90\" Japanese=\"90\" Latin=\"90\" Other=\"90\" Symbol=\"90\" User=\"90\"/><CHARSPACING Hangul=\"-15\" Hanja=\"-15\" Japanese=\"-15\" Latin=\"-15\" Other=\"-15\" Symbol=\"-15\" User=\"-15\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"11\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"12\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"90\" Hanja=\"90\" Japanese=\"90\" Latin=\"90\" Other=\"90\" Symbol=\"90\" User=\"90\"/><CHARSPACING Hangul=\"-14\" Hanja=\"-14\" Japanese=\"-14\" Latin=\"-14\" Other=\"-14\" Symbol=\"-14\" User=\"-14\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE></CHARSHAPELIST>\n");
		sb.append("		<TABDEFLIST Count=\"3\"><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"false\" Id=\"0\"/><TABDEF AutoTabLeft=\"true\" AutoTabRight=\"false\" Id=\"1\"/><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"true\" Id=\"2\"/></TABDEFLIST>\n");
		sb.append("		<NUMBERINGLIST Count=\"1\"><NUMBERING Id=\"1\" Start=\"0\"><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"1\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^1.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"2\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^2.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"3\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^3)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"4\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^4)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"5\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^5)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"6\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^6)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"7\" NumFormat=\"CircledDigit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^7</PARAHEAD></NUMBERING></NUMBERINGLIST>\n");
		sb.append("		<PARASHAPELIST Count=\"20\"><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"0\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"1\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"100\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"2\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"3\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"4\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"1\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"-2620\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"5\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"3000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"6\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"2000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"7\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"1\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"4000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"8\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"2\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"6000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"9\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"3\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"8000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"10\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"4\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"10000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"11\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"5\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"12000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"12\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"6\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"14000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"13\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"2\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Right\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"14\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"2000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"15\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"16\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"17\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"110\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"18\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"110\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"19\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"110\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE></PARASHAPELIST>\n");
		sb.append("		<STYLELIST Count=\"15\"><STYLE CharShape=\"1\" EngName=\"Normal\" Id=\"0\" LangId=\"1042\" LockForm=\"0\" Name=\"바탕글\" NextStyle=\"0\" ParaShape=\"2\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Body\" Id=\"1\" LangId=\"1042\" LockForm=\"0\" Name=\"본문\" NextStyle=\"1\" ParaShape=\"5\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 1\" Id=\"2\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 1\" NextStyle=\"2\" ParaShape=\"6\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 2\" Id=\"3\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 2\" NextStyle=\"3\" ParaShape=\"7\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 3\" Id=\"4\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 3\" NextStyle=\"4\" ParaShape=\"8\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 4\" Id=\"5\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 4\" NextStyle=\"5\" ParaShape=\"9\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 5\" Id=\"6\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 5\" NextStyle=\"6\" ParaShape=\"10\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 6\" Id=\"7\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 6\" NextStyle=\"7\" ParaShape=\"11\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 7\" Id=\"8\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 7\" NextStyle=\"8\" ParaShape=\"12\" Type=\"Para\"/><STYLE CharShape=\"0\" EngName=\"Page Number\" Id=\"9\" LangId=\"1042\" LockForm=\"0\" Name=\"쪽 번호\" NextStyle=\"9\" ParaShape=\"2\" Type=\"Para\"/><STYLE CharShape=\"2\" EngName=\"Header\" Id=\"10\" LangId=\"1042\" LockForm=\"0\" Name=\"머리말\" NextStyle=\"10\" ParaShape=\"13\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Footnote\" Id=\"11\" LangId=\"1042\" LockForm=\"0\" Name=\"각주\" NextStyle=\"11\" ParaShape=\"4\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Endnote\" Id=\"12\" LangId=\"1042\" LockForm=\"0\" Name=\"미주\" NextStyle=\"12\" ParaShape=\"4\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Memo\" Id=\"13\" LangId=\"1042\" LockForm=\"0\" Name=\"메모\" NextStyle=\"13\" ParaShape=\"3\" Type=\"Para\"/><STYLE CharShape=\"5\" Id=\"14\" LangId=\"1042\" LockForm=\"0\" Name=\"중간제목(옛체20)\" NextStyle=\"14\" ParaShape=\"0\" Type=\"Para\"/></STYLELIST>\n");
		sb.append("	</MAPPINGTABLE>\n");
		sb.append("</HEAD>\n");
		
		return sb;
	}
	
	//GET HML TAIL ELEMENT
	protected StringBuilder getTailElement() throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append("<TAIL>\n");
		sb.append(getBinDataListElement());
		sb.append("<SCRIPTCODE Type=\"JScript\" Version=\"1.0\"><SCRIPTHEADER>var Documents = XHwpDocuments;\n");
		sb.append("var Document = Documents.Active_XHwpDocument;\n");
		sb.append("</SCRIPTHEADER><SCRIPTSOURCE>function OnDocument_New() {}\n");
		sb.append("</SCRIPTSOURCE></SCRIPTCODE></TAIL></HWPML>\n");
		
		return sb;
	}
			
	//GET HML BODY ELEMENT
	protected StringBuilder getBodyElement(int bodyIndex) {
		StringBuilder sb = new StringBuilder();
		
		GamFcltyQcHwpMngResultInfo resultInfo = qcDataList.get(bodyIndex);
		
		EgovMap qcDetailData = resultInfo.getQcDetailData();
		Map<String, Object> chargerInfo1 = resultInfo.getChargerInfo1();
		Map<String, Object> chargerInfo2 = resultInfo.getChargerInfo2();
		List<EgovMap> qcResultItemList = resultInfo.getQcResultItemList();
		
		String pageBreak = (bodyIndex == 0) ? "false" : "true";
		
		String fcltsMngGroupNm = (String) qcDetailData.get("fcltsMngGroupNm");
		
		sb.append("	<P ColumnBreak=\"false\" PageBreak=\""+ pageBreak + "\" ParaShape=\"0\" Style=\"14\"><TEXT CharShape=\"6\">\n");
		sb.append("		<SECDEF CharGrid=\"0\" FirstBorder=\"false\" FirstFill=\"false\" LineGrid=\"0\" OutlineShape=\"1\" SpaceColumns=\"1134\" TabStop=\"8000\" TextDirection=\"0\" TextVerticalWidthHead=\"0\"><STARTNUMBER Equation=\"0\" Figure=\"0\" Page=\"0\" PageStartsOn=\"Both\" Table=\"0\"/><HIDE Border=\"false\" EmptyLine=\"false\" Fill=\"false\" Footer=\"false\" Header=\"false\" MasterPage=\"false\" PageNumPos=\"false\"/><PAGEDEF GutterType=\"LeftOnly\" Height=\"84188\" Landscape=\"0\" Width=\"59528\"><PAGEMARGIN Bottom=\"0\" Footer=\"4252\" Gutter=\"0\" Header=\"4252\" Left=\"4252\" Right=\"4110\" Top=\"0\"/></PAGEDEF><FOOTNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"5cm\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"283\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EachColumn\"/></FOOTNOTESHAPE><ENDNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"14692344\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"0\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EndOfDocument\"/></ENDNOTESHAPE><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Both\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Even\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Odd\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL></SECDEF>\n");
		sb.append("		<COLDEF Count=\"1\" Layout=\"Left\" SameGap=\"0\" SameSize=\"true\" Type=\"Newspaper\"/>\n");
		sb.append("		<CHAR>건 축 시 설 점 검 표</CHAR>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>○ 시설명 : </CHAR></TEXT><TEXT CharShape=\"11\"><CHAR>" + fcltsMngGroupNm + "</CHAR></TEXT></P>\n");
		sb.append("	<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"8\"><CHAR>※ 정상 : ○ 요주의 : △ 불량 : ×</CHAR></TEXT></P>\n");
		sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"7\">\n");
		sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"4\" PageBreak=\"None\" RepeatHeader=\"true\" RowCount=\"9\">\n");
		sb.append("			<SHAPEOBJECT InstId=\"2052493049\" Lock=\"false\" NumberingType=\"Table\" ZOrder=\"1\"><SIZE Height=\"52080\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"50450\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Column\" TreatAsChar=\"true\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/></SHAPEOBJECT>\n");
		sb.append("			<INSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1680\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"5444\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>구    분</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1680\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"30720\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>점    검    사    항</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1680\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"7143\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>결 과</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"1680\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"7143\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>비 고</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"5444\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>기    초</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"30720\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 구조체 침하</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 구조체 전도</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"7143\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A01010000") + "</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A01020000") + "</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"7143\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4760\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"5444\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>바    닥</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4760\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"30720\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 바닥판 콘크리트 침하</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 콘크리트 파손 및 균열</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 바닥마감재(도자기타일, 아스타일 등) 파손 및 탈락</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4760\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"7143\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A02010000") + "</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A02020000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A02030000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4760\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"7143\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10920\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"5444\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>벽    체</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10920\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"30720\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 조적벽체 균열</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 모르터 균열</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 마감도장 노후</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 경량칸막이 노후 및 파손</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 창틀 및 벽체누수</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 내장 및 외장타일 파손, 탈락</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 외벽 백화현상</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10920\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"7143\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A03010000") + "</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A03020000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A03030000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A03040000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A03050000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A03060000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A03070000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10920\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"7143\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"5444\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>기    둥</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"30720\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 기둥 콘크리트 균열</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 구조물 전도</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"7143\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A04010000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A04020000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"4\" RowSpan=\"1\" Width=\"7143\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6300\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"5444\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>천    정</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6300\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"30720\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 경량철골 천장틀 노후, 파손</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 천정재 탈락, 노후 및 파손</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 천정 흡음재(질석스프레이 등) 부식 및 탈락</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 제치장콘크리트 및 모르터위 도색 노후</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6300\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"7143\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A05010000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A05020000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A05030000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A05040000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"6300\" Protect=\"false\" RowAddr=\"5\" RowSpan=\"1\" Width=\"7143\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10920\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"5444\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>지    붕</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10920\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"30720\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 옥상층 모체(콘크리트) 균열 및 노후</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ </CHAR></TEXT><TEXT CharShape=\"10\"><CHAR>옥상방수층(아스팔트, 시멘트 액체, 쉬트등) 균열 및 파손</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 철골트러스</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR> - 접합부(볼트조임부, 리벳조임부, 용접부) 이격 및 파손</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 형태변형(비틀림, 휨, 처짐)</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 지붕마감재(슬레이트, 석면수지피복강판) 노후 및 탈락</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 도색노후</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10920\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"7143\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A06010000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A06020000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A06030100") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A06040000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A06050000") + "</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A06060000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"10920\" Protect=\"false\" RowAddr=\"6\" RowSpan=\"1\" Width=\"7143\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7840\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"5444\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>창    호</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7840\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"30720\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ </CHAR></TEXT><TEXT CharShape=\"12\"><CHAR>철재(알루미늄 포함) 및 목재창호 후레임 비틀림 및 파손</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 철재창호 부식 및 파손</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 목재창호 부식 및 비틀림</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 창호 여닫이 및 비틀림</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 유리파손</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7840\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"7143\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A07010000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A07020000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A07030000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A07040000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A07050000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"7840\" Protect=\"false\" RowAddr=\"7\" RowSpan=\"1\" Width=\"7143\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"8\" RowSpan=\"1\" Width=\"5444\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>지 하 실</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"8\" RowSpan=\"1\" Width=\"30720\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 모체(콘크리트 옹벽 및 바닥판) 균열 누수</CHAR></TEXT></P>\n");
		sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>○ 흡음재(질적스프레이 등) 부식 및 탈락</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"8\" RowSpan=\"1\" Width=\"7143\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A08010000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>" + getResultItemSymbol(qcResultItemList, "A08020000") + "</CHAR></TEXT><TEXT CharShape=\"7\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3220\" Protect=\"false\" RowAddr=\"8\" RowSpan=\"1\" Width=\"7143\"><PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\"><P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"9\"/></P></PARALIST></CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("		</TABLE>\n");
		sb.append("		<CHAR/>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"7\"/></P>\n");
		sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"7\">\n");
		if(chargerInfo1 != null) {
			if((Boolean)chargerInfo1.get("fileExists")) {
				sb.append("		<PICTURE Reverse=\"false\">\n");
				sb.append("			<SHAPEOBJECT InstId=\"2052618215\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"0\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"38640\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"500\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT>\n");
				sb.append("			<SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"978876392\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT>\n");
				sb.append("			<IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/>\n");
				sb.append("			<IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3840\" Top=\"0\"/>\n");
				sb.append("			<INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
				sb.append("			<IMAGE Alpha=\"0\" BinItem=\"" + getBinId((String)chargerInfo1.get("signFileNmPhysicl")) + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/>\n");
				sb.append("		</PICTURE>\n");
			}
		}
		sb.append("		<CHAR>                                  점검일자 :  </CHAR></TEXT><TEXT CharShape=\"11\"><CHAR>" + ((qcDetailData.get("wrtDtHwp") != null) ? (String) qcDetailData.get("wrtDtHwp") : "" ) + "</CHAR>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"7\"/></P>\n");
		if(chargerInfo1 != null) {
			sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>                                  점 검 자 :  " + ((chargerInfo1.get("chargerNm") != null) ? (String)chargerInfo1.get("chargerNm") :  "      ") + "     (인)</CHAR></TEXT></P>\n");
		} else {
			sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>                                  점 검 자 :             (인)</CHAR></TEXT></P>\n");
		}
		sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"7\">\n");
		if(chargerInfo2 != null) {
			if((Boolean)chargerInfo2.get("fileExists")) {
				sb.append("		<PICTURE Reverse=\"false\">\n");
				sb.append("			<SHAPEOBJECT InstId=\"2055984737\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"2\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"38640\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"740\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT>\n");
				sb.append("			<SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"982242914\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT>\n");
				sb.append("			<IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/>\n");
				sb.append("			<IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3840\" Top=\"0\"/>\n");
				sb.append("			<INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/>\n");
				sb.append("			<IMAGE Alpha=\"0\" BinItem=\"" + getBinId((String)chargerInfo2.get("signFileNmPhysicl")) + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/>\n");
				sb.append("		</PICTURE>\n");
			}
		}
		sb.append("		<CHAR/>\n");
		sb.append("	</TEXT></P>\n");
		if(chargerInfo2 != null) {
			sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR>                                              " + ((chargerInfo2.get("chargerNm") != null) ? (String)chargerInfo2.get("chargerNm") :  "      ") + "     (인)</CHAR></TEXT></P>\n");
		} else {
			sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"7\"><CHAR/></TEXT></P>\n");
		}
		
		return sb;
	}
}
