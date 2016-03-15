/**
 * 
 */
package egovframework.rte.ygpa.gam.fcltyMng.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fcltyMng.service.GamFcltyQcHwpBaseReportInterface;

/**
 * 
 * @author Jongmin
 * @since 2016. 3. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 3. 12.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 * 시설물 점검표(토목, 건축 등등) 한글 문서 생성 클래스
 */

public class GamFcltyQcHwpReport  extends GamFcltyQcHwpBaseReport implements GamFcltyQcHwpBaseReportInterface {
	private List<GamFcltyQcHwpMngResultInfo> qcDataList;
	private String docDate = null;
	
	public GamFcltyQcHwpReport(List<GamFcltyQcHwpMngResultInfo> qcDataList, Map<String, Integer> fileIndexInfo) {
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
			GamFcltyQcHwpMngResultInfo resultInfo = qcDataList.get(i);
			String fcltsJobSe = (resultInfo.getQcDetailData().get("fcltsJobSe") != null) ? (String) resultInfo.getQcDetailData().get("fcltsJobSe") : "";
			if(fcltsJobSe.equals("A")){
				result.append(getArchBodyElement(i)); //건축
			} else if(fcltsJobSe.equals("C")){
				result.append(getCivilBodyElement(i)); //토목
			} else if(fcltsJobSe.equals("E")){
				result.append(getElectyBodyElement(i)); //전력
			} else if(fcltsJobSe.equals("I")){
				result.append(getInfoCommBodyElement(i)); //정보통신
			} else if(fcltsJobSe.equals("M")){
				//기계
				String mechFcltsSe = (resultInfo.getQcDetailData().get("mechFcltsSe") != null) ? (String) resultInfo.getQcDetailData().get("mechFcltsSe") : "";
				if(mechFcltsSe.equals("1")){
					result.append(getCraneMechBodyElement(i)); //하역장비
				} else {
					result.append(getArchMechBodyElement(i)); //건설설비
				}
			} 
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
		sb.append("	<DOCSUMMARY><TITLE>시설물 점검표</TITLE><AUTHOR>YGPA GIS Assets Management System</AUTHOR><DATE>" + docDate + "</DATE></DOCSUMMARY>\n");
		sb.append("	<DOCSETTING><BEGINNUMBER Endnote=\"1\" Equation=\"1\" Footnote=\"1\" Page=\"1\" Picture=\"1\" Table=\"1\"/><CARETPOS List=\"0\" Para=\"0\" Pos=\"16\"/></DOCSETTING>\n");
		sb.append("	<MAPPINGTABLE>\n");
		sb.append(getBinItemListElement());
		sb.append("		<FACENAMELIST><FONTFACE Count=\"5\" Lang=\"Hangul\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"휴먼옛체\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"4\" StrokeVariation=\"1\" Weight=\"5\" XHeight=\"1\"/></FONT><FONT Id=\"3\" Name=\"휴먼명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT><FONT Id=\"4\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"5\" Lang=\"Latin\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT><FONT Id=\"3\" Name=\"한양궁서\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"4\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT><FONT Id=\"4\" Name=\"HCI Poppy\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Hanja\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Japanese\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Other\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"Symbol\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"한양신명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE><FONTFACE Count=\"3\" Lang=\"User\"><FONT Id=\"0\" Name=\"굴림\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"1\" Name=\"바탕\" Type=\"ttf\"><TYPEINFO ArmStyle=\"1\" Contrast=\"0\" FamilyType=\"2\" Letterform=\"1\" Midline=\"1\" Proportion=\"0\" StrokeVariation=\"1\" Weight=\"6\" XHeight=\"1\"/></FONT><FONT Id=\"2\" Name=\"명조\" Type=\"hft\"><TYPEINFO ArmStyle=\"0\" Contrast=\"0\" FamilyType=\"1\" Letterform=\"0\" Midline=\"0\" Proportion=\"0\" StrokeVariation=\"0\" Weight=\"0\" XHeight=\"0\"/></FONT></FONTFACE></FACENAMELIST>\n");
		sb.append("		<BORDERFILLLIST Count=\"25\"><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"1\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"2\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"None\" Width=\"0.1mm\"/><RIGHTBORDER Type=\"None\" Width=\"0.1mm\"/><TOPBORDER Type=\"None\" Width=\"0.1mm\"/><BOTTOMBORDER Type=\"None\" Width=\"0.1mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.1mm\"/><FILLBRUSH><WINDOWBRUSH Alpha=\"0\" FaceColor=\"4294967295\" HatchColor=\"4278190080\"/></FILLBRUSH></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"3\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"4\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"None\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"None\" Width=\"0.12mm\"/><TOPBORDER Type=\"None\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"5\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"6\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"7\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"8\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"None\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"None\" Width=\"0.4mm\"/><TOPBORDER Type=\"None\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"9\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/><FILLBRUSH><WINDOWBRUSH Alpha=\"0\" FaceColor=\"15066597\" HatchColor=\"0\"/></FILLBRUSH></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"10\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"11\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"12\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"13\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"14\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"DoubleSlim\" Width=\"0.5mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"15\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"DoubleSlim\" Width=\"0.5mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"16\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.4mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"17\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"DoubleSlim\" Width=\"0.5mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"18\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"None\" Width=\"0.1mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"19\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"DoubleSlim\" Width=\"0.5mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"20\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"21\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.4mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"22\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"DoubleSlim\" Width=\"0.5mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"23\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"Solid\" Width=\"0.12mm\"/><BOTTOMBORDER Type=\"DoubleSlim\" Width=\"0.5mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"24\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.4mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.12mm\"/><TOPBORDER Type=\"None\" Width=\"0.1mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL><BORDERFILL BackSlash=\"0\" BreakCellSeparateLine=\"0\" CenterLine=\"0\" CounterBackSlash=\"0\" CounterSlash=\"0\" CrookedSlash=\"0\" Id=\"25\" Shadow=\"false\" Slash=\"0\" ThreeD=\"false\"><LEFTBORDER Type=\"Solid\" Width=\"0.12mm\"/><RIGHTBORDER Type=\"Solid\" Width=\"0.4mm\"/><TOPBORDER Type=\"None\" Width=\"0.1mm\"/><BOTTOMBORDER Type=\"Solid\" Width=\"0.12mm\"/><DIAGONAL Type=\"Solid\" Width=\"0.12mm\"/></BORDERFILL></BORDERFILLLIST>\n");
		sb.append("		<CHARSHAPELIST Count=\"22\"><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"0\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"1\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"2\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"3\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"900\" Id=\"4\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-5\" Hanja=\"-5\" Japanese=\"-5\" Latin=\"-5\" Other=\"-5\" Symbol=\"-5\" User=\"-5\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"5\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"6\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"2000\" Id=\"7\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"3\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"2000\" Id=\"8\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"2\" Hanja=\"2\" Japanese=\"2\" Latin=\"3\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1200\" Id=\"9\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"10\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"90\" Hanja=\"90\" Japanese=\"90\" Latin=\"90\" Other=\"90\" Symbol=\"90\" User=\"90\"/><CHARSPACING Hangul=\"-10\" Hanja=\"-10\" Japanese=\"-10\" Latin=\"-10\" Other=\"-10\" Symbol=\"-10\" User=\"-10\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"11\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"3\" Hanja=\"1\" Japanese=\"1\" Latin=\"4\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1200\" Id=\"12\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"3\" Hanja=\"1\" Japanese=\"1\" Latin=\"4\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"13\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"3\" Hanja=\"1\" Japanese=\"1\" Latin=\"4\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"95\" Hanja=\"95\" Japanese=\"95\" Latin=\"95\" Other=\"95\" Symbol=\"95\" User=\"95\"/><CHARSPACING Hangul=\"-10\" Hanja=\"-10\" Japanese=\"-10\" Latin=\"-10\" Other=\"-10\" Symbol=\"-10\" User=\"-10\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"14\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"3\" Hanja=\"1\" Japanese=\"1\" Latin=\"4\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1000\" Id=\"15\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"4\" Hanja=\"2\" Japanese=\"2\" Latin=\"2\" Other=\"2\" Symbol=\"2\" User=\"2\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1300\" Id=\"16\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"3\" Hanja=\"1\" Japanese=\"1\" Latin=\"4\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"-10\" Hanja=\"-10\" Japanese=\"-10\" Latin=\"-10\" Other=\"-10\" Symbol=\"-10\" User=\"-10\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1300\" Id=\"17\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"3\" Hanja=\"1\" Japanese=\"1\" Latin=\"4\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"-10\" Hanja=\"-10\" Japanese=\"-10\" Latin=\"-10\" Other=\"-10\" Symbol=\"-10\" User=\"-10\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"2\" Height=\"1300\" Id=\"18\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"3\" Hanja=\"1\" Japanese=\"1\" Latin=\"4\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"-10\" Hanja=\"-10\" Japanese=\"-10\" Latin=\"-10\" Other=\"-10\" Symbol=\"-10\" User=\"-10\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1300\" Id=\"19\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHARSPACING Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"20\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"90\" Hanja=\"90\" Japanese=\"90\" Latin=\"90\" Other=\"90\" Symbol=\"90\" User=\"90\"/><CHARSPACING Hangul=\"-10\" Hanja=\"-10\" Japanese=\"-10\" Latin=\"-10\" Other=\"-10\" Symbol=\"-10\" User=\"-10\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/><BOLD/></CHARSHAPE><CHARSHAPE BorderFillId=\"0\" Height=\"1400\" Id=\"21\" ShadeColor=\"4294967295\" SymMark=\"0\" TextColor=\"0\" UseFontSpace=\"false\" UseKerning=\"false\"><FONTID Hangul=\"1\" Hanja=\"1\" Japanese=\"1\" Latin=\"1\" Other=\"1\" Symbol=\"1\" User=\"1\"/><RATIO Hangul=\"90\" Hanja=\"90\" Japanese=\"90\" Latin=\"90\" Other=\"90\" Symbol=\"90\" User=\"90\"/><CHARSPACING Hangul=\"-15\" Hanja=\"-15\" Japanese=\"-15\" Latin=\"-15\" Other=\"-15\" Symbol=\"-15\" User=\"-15\"/><RELSIZE Hangul=\"100\" Hanja=\"100\" Japanese=\"100\" Latin=\"100\" Other=\"100\" Symbol=\"100\" User=\"100\"/><CHAROFFSET Hangul=\"0\" Hanja=\"0\" Japanese=\"0\" Latin=\"0\" Other=\"0\" Symbol=\"0\" User=\"0\"/></CHARSHAPE></CHARSHAPELIST>\n");
		sb.append("		<TABDEFLIST Count=\"3\"><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"false\" Id=\"0\"/><TABDEF AutoTabLeft=\"true\" AutoTabRight=\"false\" Id=\"1\"/><TABDEF AutoTabLeft=\"false\" AutoTabRight=\"true\" Id=\"2\"/></TABDEFLIST>\n");
		sb.append("		<NUMBERINGLIST Count=\"1\"><NUMBERING Id=\"1\" Start=\"0\"><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"1\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^1.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"2\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^2.</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"3\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^3)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"4\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^4)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"5\" NumFormat=\"Digit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^5)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"6\" NumFormat=\"HangulSyllable\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">(^6)</PARAHEAD><PARAHEAD Alignment=\"Left\" AutoIndent=\"true\" Level=\"7\" NumFormat=\"CircledDigit\" TextOffset=\"50\" TextOffsetType=\"percent\" UseInstWidth=\"true\" WidthAdjust=\"0\">^7</PARAHEAD></NUMBERING></NUMBERINGLIST>\n");
		sb.append("		<PARASHAPELIST Count=\"36\"><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"0\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"1\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"100\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"2\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"3\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"true\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"4\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"1\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"-2620\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"5\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"3000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"6\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"2000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"7\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"1\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"4000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"8\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"2\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"6000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"9\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"3\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"8000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"10\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"4\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"10000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"11\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"5\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"12000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"20\" FontLineHeight=\"false\" HeadingType=\"Outline\" Id=\"12\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"6\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"14000\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"13\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"2\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER BorderFill=\"2\" Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Right\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"14\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"2000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"15\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"16\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"17\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"110\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"18\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"110\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"19\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"110\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"20\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"21\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"200\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"22\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"23\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"150\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Justify\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"24\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"1000\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"1000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Right\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"25\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"3000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"26\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"27\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"1000\" LineSpacing=\"100\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"1000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"28\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"1000\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"1000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"29\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Right\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"30\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"160\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"3000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"31\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"1000\" LineSpacing=\"100\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"1000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"32\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"1000\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"1000\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"false\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"33\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"50\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"34\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"100\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE><PARASHAPE Align=\"Center\" BreakLatinWord=\"KeepWord\" BreakNonLatinWord=\"true\" Condense=\"0\" FontLineHeight=\"false\" HeadingType=\"None\" Id=\"35\" KeepLines=\"false\" KeepWithNext=\"false\" Level=\"0\" LineWrap=\"Break\" PageBreakBefore=\"false\" SnapToGrid=\"false\" TabDef=\"0\" VerAlign=\"Baseline\" WidowOrphan=\"false\"><PARAMARGIN Indent=\"0\" Left=\"0\" LineSpacing=\"130\" LineSpacingType=\"Percent\" Next=\"0\" Prev=\"0\" Right=\"0\"/><PARABORDER Connect=\"false\" IgnoreMargin=\"false\"/></PARASHAPE></PARASHAPELIST>\n");
		sb.append("		<STYLELIST Count=\"15\"><STYLE CharShape=\"1\" EngName=\"Normal\" Id=\"0\" LangId=\"1042\" LockForm=\"0\" Name=\"바탕글\" NextStyle=\"0\" ParaShape=\"2\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Body\" Id=\"1\" LangId=\"1042\" LockForm=\"0\" Name=\"본문\" NextStyle=\"1\" ParaShape=\"5\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 1\" Id=\"2\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 1\" NextStyle=\"2\" ParaShape=\"6\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 2\" Id=\"3\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 2\" NextStyle=\"3\" ParaShape=\"7\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 3\" Id=\"4\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 3\" NextStyle=\"4\" ParaShape=\"8\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 4\" Id=\"5\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 4\" NextStyle=\"5\" ParaShape=\"9\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 5\" Id=\"6\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 5\" NextStyle=\"6\" ParaShape=\"10\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 6\" Id=\"7\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 6\" NextStyle=\"7\" ParaShape=\"11\" Type=\"Para\"/><STYLE CharShape=\"1\" EngName=\"Outline 7\" Id=\"8\" LangId=\"1042\" LockForm=\"0\" Name=\"개요 7\" NextStyle=\"8\" ParaShape=\"12\" Type=\"Para\"/><STYLE CharShape=\"0\" EngName=\"Page Number\" Id=\"9\" LangId=\"1042\" LockForm=\"0\" Name=\"쪽 번호\" NextStyle=\"9\" ParaShape=\"2\" Type=\"Para\"/><STYLE CharShape=\"2\" EngName=\"Header\" Id=\"10\" LangId=\"1042\" LockForm=\"0\" Name=\"머리말\" NextStyle=\"10\" ParaShape=\"13\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Footnote\" Id=\"11\" LangId=\"1042\" LockForm=\"0\" Name=\"각주\" NextStyle=\"11\" ParaShape=\"4\" Type=\"Para\"/><STYLE CharShape=\"3\" EngName=\"Endnote\" Id=\"12\" LangId=\"1042\" LockForm=\"0\" Name=\"미주\" NextStyle=\"12\" ParaShape=\"4\" Type=\"Para\"/><STYLE CharShape=\"4\" EngName=\"Memo\" Id=\"13\" LangId=\"1042\" LockForm=\"0\" Name=\"메모\" NextStyle=\"13\" ParaShape=\"3\" Type=\"Para\"/><STYLE CharShape=\"7\" Id=\"14\" LangId=\"1042\" LockForm=\"0\" Name=\"중간제목(옛체20)\" NextStyle=\"14\" ParaShape=\"0\" Type=\"Para\"/></STYLELIST>\n");
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
		sb.append("</SCRIPTHEADER><SCRIPTSOURCE>function OnDocument_New() {} \n");
		sb.append("</SCRIPTSOURCE></SCRIPTCODE></TAIL></HWPML>");
		
		return sb;
	}
	
	// 건축시설점검표 Body
	protected StringBuilder getArchBodyElement(int bodyIndex) {
		StringBuilder sb = new StringBuilder();

		GamFcltyQcHwpMngResultInfo resultInfo = qcDataList.get(bodyIndex);
		
		EgovMap qcDetailData = resultInfo.getQcDetailData();
		Map<String, Object> chargerInfo1 = resultInfo.getChargerInfo1();
		Map<String, Object> chargerInfo2 = resultInfo.getChargerInfo2();
		List<EgovMap> qcResultItemList = resultInfo.getQcResultItemList();
		
		List<HashMap<String, String>> qcItemUpperList = getQcUpperItemList(qcResultItemList);
		
		String pageBreak = (bodyIndex == 0) ? "false" : "true";		
		String fcltsMngGroupNm = (String) qcDetailData.get("fcltsMngGroupNm");

		int rowCount = qcItemUpperList.size() + 2; //제목row 포함한 rowCount 값	

		String wrtDtHwp = (qcDetailData.get("wrtDtHwp") != null) ? (String) qcDetailData.get("wrtDtHwp") : "";
		
		sb.append("	<P ColumnBreak=\"false\" PageBreak=\""+ pageBreak + "\" ParaShape=\"0\" Style=\"14\"><TEXT CharShape=\"8\">\n");
		sb.append("		<SECDEF CharGrid=\"0\" FirstBorder=\"false\" FirstFill=\"false\" LineGrid=\"0\" OutlineShape=\"1\" SpaceColumns=\"1134\" TabStop=\"8000\" TextDirection=\"0\" TextVerticalWidthHead=\"0\"><STARTNUMBER Equation=\"0\" Figure=\"0\" Page=\"0\" PageStartsOn=\"Both\" Table=\"0\"/><HIDE Border=\"false\" EmptyLine=\"false\" Fill=\"false\" Footer=\"false\" Header=\"false\" MasterPage=\"false\" PageNumPos=\"false\"/><PAGEDEF GutterType=\"LeftOnly\" Height=\"84188\" Landscape=\"0\" Width=\"59528\"><PAGEMARGIN Bottom=\"0\" Footer=\"4252\" Gutter=\"0\" Header=\"4252\" Left=\"4252\" Right=\"4110\" Top=\"0\"/></PAGEDEF><FOOTNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"5cm\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"283\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EachColumn\"/></FOOTNOTESHAPE><ENDNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"14692344\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"0\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EndOfDocument\"/></ENDNOTESHAPE><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Both\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Even\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Odd\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL></SECDEF>\n");
		sb.append("		<COLDEF Count=\"1\" Layout=\"Left\" SameGap=\"0\" SameSize=\"true\" Type=\"Newspaper\"/><CHAR>건 축 시 설 점 검 표</CHAR>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"5\"><CHAR>○ 시설명 : </CHAR></TEXT><TEXT CharShape=\"6\"><CHAR>" + fcltsMngGroupNm + "</CHAR></TEXT></P>\n");
		
		sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"5\">\n");
		sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"4\" PageBreak=\"Cell\" RepeatHeader=\"true\" RowCount=\""+rowCount+"\">\n");
		sb.append("			<SHAPEOBJECT InstId=\"2052493049\" Lock=\"false\" NumberingType=\"Table\" TextWrap=\"TopAndBottom\" ZOrder=\"12\"><SIZE Height=\"7429\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"50450\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/></SHAPEOBJECT>\n");
		sb.append("			<INSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"4\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2529\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"50450\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>※ 정상 : ○ 요주의 : △ 불량 : ×</CHAR></TEXT><TEXT CharShape=\"9\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"0\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"5444\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>구    분</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"1680\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"30720\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점    검    사    항</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"1680\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"7143\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>결 과</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"1680\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"7143\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>비 고</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");

		int rowAddr = 2;
		for(int i=0; i<qcItemUpperList.size(); i++) {
			HashMap<String, String> upperItem = qcItemUpperList.get(i);
			String qcItemUpperCd = upperItem.get("qcItemUpperCd");
			String qcItemUpperNm = upperItem.get("qcItemUpperNm");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"5444\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"17\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>"+qcItemUpperNm+"</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"30720\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			int cellHeight = 0;
			for(EgovMap qcResultItem : qcResultItemList) {
				String upperCd = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemUpperCd") : "";
				if(qcItemUpperCd.equals(upperCd)) {
					String qcItemNm = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemNm") : "";
					sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ "+qcItemNm+"</CHAR></TEXT></P>\n");
					cellHeight += 1610;
				}
			}
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\""+cellHeight+"\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"7143\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			for(EgovMap qcResultItem : qcResultItemList) {
				String upperCd = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemUpperCd") : "";
				if(qcItemUpperCd.equals(upperCd)) {
					String inspResultChk = (qcResultItem.get("inspResultChk") != null) ? (String) qcResultItem.get("inspResultChk") : "";
					sb.append("						<P ParaShape=\"19\" Style=\"0\"><TEXT CharShape=\"5\"><CHAR>" + getResultItemSymbol(inspResultChk) + "</CHAR></TEXT></P>\n");
				}
			}
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"7143\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"18\" Style=\"0\"><TEXT CharShape=\"10\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			rowAddr++;
		}
		sb.append("		</TABLE><CHAR/>\n");
		sb.append("	</TEXT></P>\n");
		
		sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"5\"/></P>\n");
		sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"5\">\n");
		if(chargerInfo1 != null) {
			if((Boolean)chargerInfo1.get("fileExists")) {
				sb.append("		<PICTURE Reverse=\"false\"><SHAPEOBJECT InstId=\"2052618215\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"0\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"38640\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"500\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT><SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"978876392\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT><IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/><IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3780\" Top=\"0\"/><INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/><IMAGE Alpha=\"0\" BinItem=\"" + getBinId((String)chargerInfo1.get("signFileNmPhysicl")) + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/></PICTURE>\n");
			}
		}
		sb.append("		<CHAR>                                  점검일자 :  </CHAR></TEXT><TEXT CharShape=\"6\"><CHAR>" + wrtDtHwp + "</CHAR>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"5\"/></P><P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"5\">\n");
		if(chargerInfo1 != null) {
			String chargerNm = (chargerInfo1.get("chargerNm") != null) ? (String)chargerInfo1.get("chargerNm") :  "      ";			
			sb.append("		<CHAR>                                  점 검 자 :  "+chargerNm+"     (인)</CHAR>\n");
		} else {
			sb.append("		<CHAR>                                  점 검 자 :               (인)</CHAR>\n");
		}
		sb.append("	</TEXT></P>\n");

		if(chargerInfo2 != null) {
			sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"5\">\n");
			if((Boolean)chargerInfo2.get("fileExists")) {		
				sb.append("		<PICTURE Reverse=\"false\"><SHAPEOBJECT InstId=\"2055984737\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"1\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"38640\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"740\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT><SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"982242914\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT><IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/><IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3780\" Top=\"0\"/><INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/><IMAGE Alpha=\"0\" BinItem=\"" + getBinId((String)chargerInfo2.get("signFileNmPhysicl")) + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/></PICTURE>\n");
			}
			sb.append("		<CHAR/>\n");
			sb.append("	</TEXT></P>\n");
			sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"5\">\n");
			String chargerNm = (chargerInfo2.get("chargerNm") != null) ? (String)chargerInfo2.get("chargerNm") :  "      ";
			sb.append("		<CHAR>                                              "+chargerNm+"     (인)</CHAR>\n");
			sb.append("	</TEXT></P>\n");
		}
		return sb;
	}

	// 토목시설점검표 Body
	protected StringBuilder getCivilBodyElement(int bodyIndex) {
		StringBuilder sb = new StringBuilder();
		GamFcltyQcHwpMngResultInfo resultInfo = qcDataList.get(bodyIndex);
		
		EgovMap qcDetailData = resultInfo.getQcDetailData();
		EgovMap mngGroupInfo = resultInfo.getMngGroupInfo();
		Map<String, Object> chargerInfo1 = resultInfo.getChargerInfo1();
		Map<String, Object> chargerInfo2 = resultInfo.getChargerInfo2();
		List<EgovMap> qcResultItemList = resultInfo.getQcResultItemList();
		
		List<HashMap<String, String>> qcItemUpperList = getQcUpperItemList(qcResultItemList);
		
		String pageBreak = (bodyIndex == 0) ? "false" : "true";
		
		//String prtAtCodeNm = (mngGroupInfo.get("prtAtCodeNm") != null) ? (String)mngGroupInfo.get("prtAtCodeNm") + "항" : "      ";
		String fcltsMngGroupNm = (mngGroupInfo.get("fcltsMngGroupNm") != null) ? (String) mngGroupInfo.get("fcltsMngGroupNm") : "-";
		String loc = (mngGroupInfo.get("loc") != null) ? (String) mngGroupInfo.get("loc") : "-";
		String fcltsGbnNm = ((mngGroupInfo.get("fcltsGbnNm") != null) ? (String)mngGroupInfo.get("fcltsGbnNm") : "-");
		String bldYear = (mngGroupInfo.get("bldYear") != null) ? (String) mngGroupInfo.get("bldYear") : "-";
		String cnstrtr = (mngGroupInfo.get("cnstrtr") != null) ? (String) mngGroupInfo.get("cnstrtr") : "-";
		String flawEndDt = (mngGroupInfo.get("flawEndDt") != null) ? (String) mngGroupInfo.get("flawEndDtYear") + "년" + (String) mngGroupInfo.get("flawEndDtMonth") + "월" + (String) mngGroupInfo.get("flawEndDtDay") + "일"  : "-";
		String fcltsStateCls = (mngGroupInfo.get("fcltsStateCls") != null) ? (String) mngGroupInfo.get("fcltsStateCls") : "-";
		String fcltsSummary = (mngGroupInfo.get("fcltsSummary") != null) ? (String) mngGroupInfo.get("fcltsSummary") : "-";
		String fcltsScale = (mngGroupInfo.get("fcltsScale") != null) ? (String) mngGroupInfo.get("fcltsScale") : "-";
		
		fcltsGbnNm += " / " + (fcltsStateCls.equals("") ? "-" :  fcltsStateCls) ; 
		String wrtDtHwp = (qcDetailData.get("wrtDtHwp") != null) ? (String) qcDetailData.get("wrtDtHwp") : "";
		
		sb.append("	<P ColumnBreak=\"false\" PageBreak=\"" + pageBreak + "\" ParaShape=\"0\" Style=\"14\"><TEXT CharShape=\"8\">\n");
		sb.append("		<SECDEF CharGrid=\"0\" FirstBorder=\"false\" FirstFill=\"false\" LineGrid=\"0\" OutlineShape=\"1\" SpaceColumns=\"1134\" TabStop=\"8000\" TextDirection=\"0\" TextVerticalWidthHead=\"0\"><STARTNUMBER Equation=\"0\" Figure=\"0\" Page=\"0\" PageStartsOn=\"Both\" Table=\"0\"/><HIDE Border=\"false\" EmptyLine=\"false\" Fill=\"false\" Footer=\"false\" Header=\"false\" MasterPage=\"false\" PageNumPos=\"false\"/><PAGEDEF GutterType=\"LeftOnly\" Height=\"84188\" Landscape=\"0\" Width=\"59528\"><PAGEMARGIN Bottom=\"0\" Footer=\"4252\" Gutter=\"0\" Header=\"4252\" Left=\"4252\" Right=\"4110\" Top=\"0\"/></PAGEDEF><FOOTNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"5cm\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"283\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EachColumn\"/></FOOTNOTESHAPE><ENDNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"14692344\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"0\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EndOfDocument\"/></ENDNOTESHAPE><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Both\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Even\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Odd\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL></SECDEF>\n");
		sb.append("		<COLDEF Count=\"1\" Layout=\"Left\" SameGap=\"0\" SameSize=\"true\" Type=\"Newspaper\"/><CHAR>토목 시설물 점검표</CHAR>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"16\">\n");
		if(chargerInfo1 != null) {
			if((Boolean)chargerInfo1.get("fileExists")) {
				sb.append("		<PICTURE Reverse=\"false\"><SHAPEOBJECT InstId=\"1099298189\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"8\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"44040\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"1060\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT><SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"25556366\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT><IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/><IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3780\" Top=\"0\"/><INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/><IMAGE Alpha=\"0\" BinItem=\"" + getBinId((String)chargerInfo1.get("signFileNmPhysicl")) + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/></PICTURE>\n");
			}
		}
		sb.append("		<CHAR/>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"16\">\n");
		sb.append("		<CHAR>            <TAB/><TAB/><TAB/><TAB/>       <TAB/>     점검일 :  "+wrtDtHwp+"</CHAR>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"16\">\n");
		if(chargerInfo2 != null) {
			if((Boolean)chargerInfo2.get("fileExists")) {
				sb.append("		<PICTURE Reverse=\"false\"><SHAPEOBJECT InstId=\"1099298191\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"9\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"44160\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"1180\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT><SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"25556368\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT><IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/><IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3780\" Top=\"0\"/><INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/><IMAGE Alpha=\"0\" BinItem=\"" + getBinId((String)chargerInfo2.get("signFileNmPhysicl")) + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/></PICTURE>\n");
			}
		}
		if(chargerInfo1 != null) {
			String chargerNm = ((chargerInfo1.get("chargerNm") != null) ? (String)chargerInfo1.get("chargerNm") :  "      ");
			sb.append("		<CHAR><TAB/><TAB/><TAB/><TAB/><TAB/><TAB/><TAB/>     점검자 :<TAB/>"+chargerNm+"         (인)</CHAR>\n");
		} else {
			sb.append("		<CHAR><TAB/><TAB/><TAB/><TAB/><TAB/><TAB/><TAB/>     점검자 :<TAB/>               (인)</CHAR>\n");
		}
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"19\">\n");
		if(chargerInfo2 != null) {	
			String chargerNm = (chargerInfo2.get("chargerNm") != null) ? (String)chargerInfo2.get("chargerNm") :  "      ";
			sb.append("		<CHAR><TAB/><TAB/><TAB/><TAB/><TAB/><TAB/><TAB/><TAB/><TAB/></CHAR></TEXT><TEXT CharShape=\"16\"><CHAR>"+chargerNm+"         (인)</CHAR>\n");
		} else {
			sb.append("		<CHAR/>\n");
		}
		sb.append("	</TEXT></P>\n");
		
		sb.append("	<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"16\">\n");
		sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"5\" PageBreak=\"None\" RepeatHeader=\"true\" RowCount=\"4\">\n");
		sb.append("			<SHAPEOBJECT InstId=\"1099298193\" Lock=\"false\" NumberingType=\"Table\" ZOrder=\"16\"><SIZE Height=\"13888\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"48208\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Column\" TreatAsChar=\"true\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/></SHAPEOBJECT>\n");
		sb.append("			<INSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"9\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"9212\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"17\"><CHAR>시 설 명</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"14040\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"16\"><CHAR>" + fcltsMngGroupNm + "</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"9\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"9496\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"17\"><CHAR>시설물소재지</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"2\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"15460\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"16\"><CHAR>" + loc + "</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"9\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"9212\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"17\"><CHAR>시설개요</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"14040\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"16\"><CHAR>" + fcltsSummary + "</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"9\" ColAddr=\"2\" ColSpan=\"2\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"12336\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"17\"><CHAR>종별 / 상태등급</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"4\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"12620\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"16\"><CHAR>" + fcltsGbnNm + "</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"9\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"9212\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"17\"><CHAR>준공년도</CHAR></TEXT><TEXT CharShape=\"1\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"14040\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"16\"><CHAR>" + bldYear + "</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"9\" ColAddr=\"2\" ColSpan=\"2\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"12336\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"17\"><CHAR>하 자 만 료 일</CHAR></TEXT><TEXT CharShape=\"1\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"4\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"12620\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"16\"><CHAR>" + flawEndDt + "</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"9\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"9212\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"17\"><CHAR>시설규모</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"14040\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"16\"><CHAR>" + fcltsScale + "</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"9\" ColAddr=\"2\" ColSpan=\"2\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"12336\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"17\"><CHAR>시  공  회  사</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"4\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"3284\" Protect=\"false\" RowAddr=\"3\" RowSpan=\"1\" Width=\"12620\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"16\"><CHAR>" + cnstrtr + "</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("		</TABLE><CHAR/>\n");
		sb.append("	</TEXT></P>\n");
		
		int rowCount = qcItemUpperList.size() + 1; //제목을 포함한 rowCount 값
		sb.append("	<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"16\">\n");
		sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"3\" PageBreak=\"Cell\" RepeatHeader=\"true\" RowCount=\""+rowCount+"\">\n");
		sb.append("			<SHAPEOBJECT InstId=\"1099298194\" Lock=\"false\" NumberingType=\"Table\" TextWrap=\"TopAndBottom\" ZOrder=\"17\"><SIZE Height=\"13536\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"48208\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"1332\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/></SHAPEOBJECT>\n");
		sb.append("			<INSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"9\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"4584\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"9212\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"34\" Style=\"0\"><TEXT CharShape=\"17\"><CHAR>점검항목</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"9\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"4584\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"20349\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"34\" Style=\"0\"><TEXT CharShape=\"17\"><CHAR>점 검 세 부 항 목</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"9\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"4584\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"18647\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"34\" Style=\"0\"><TEXT CharShape=\"17\"><CHAR>점  검  내  용</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");
		
		int rowAddr = 1;
		for(int i=0; i<qcItemUpperList.size(); i++) {
			HashMap<String, String> upperItem = qcItemUpperList.get(i);
			String qcItemUpperCd = upperItem.get("qcItemUpperCd");
			String qcItemUpperNm = upperItem.get("qcItemUpperNm");		
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"9212\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"16\"><CHAR>"+qcItemUpperNm+"</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"20349\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			int cellHeight = 0;
			for(EgovMap qcResultItem : qcResultItemList) {
				String upperCd = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemUpperCd") : "";
				if(qcItemUpperCd.equals(upperCd)) {
					String qcItemNm = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemNm") : "";
					sb.append("						<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"18\"><CHAR>ㅇ "+qcItemNm+"</CHAR></TEXT></P>\n");
					cellHeight += 2238;
				}
			}
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\""+cellHeight+"\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"18647\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			for(EgovMap qcResultItem : qcResultItemList) {
				String upperCd = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemUpperCd") : "";
				if(qcItemUpperCd.equals(upperCd)) {
					String inspResultCn = (qcResultItem.get("inspResultCn") != null) ? (String) qcResultItem.get("inspResultCn") : "";
					sb.append("						<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"18\"><CHAR>"+inspResultCn+"</CHAR></TEXT></P>\n");
				}
			}
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			rowAddr++;
		}
		sb.append("		</TABLE><CHAR/>\n");
		sb.append("	</TEXT></P>\n");
		//sb.append("	<P ParaShape=\"0\" Style=\"14\"><TEXT CharShape=\"16\"/></P>\n");
		return sb;
	}

	// 전력시설점검표 Body
	protected StringBuilder getElectyBodyElement(int bodyIndex) {
		StringBuilder sb = new StringBuilder();
		
		GamFcltyQcHwpMngResultInfo resultInfo = qcDataList.get(bodyIndex);
		
		EgovMap qcDetailData = resultInfo.getQcDetailData();
		Map<String, Object> chargerInfo1 = resultInfo.getChargerInfo1();
		Map<String, Object> chargerInfo2 = resultInfo.getChargerInfo2();
		List<EgovMap> qcResultItemList = resultInfo.getQcResultItemList();
		
		List<HashMap<String, String>> qcItemUpperList = getQcUpperItemList(qcResultItemList);
		
		String pageBreak = (bodyIndex == 0) ? "false" : "true";		
		String fcltsMngGroupNm = (String) qcDetailData.get("fcltsMngGroupNm");

		int rowCount = qcItemUpperList.size() + 2; //제목row 포함한 rowCount 값	
	
		String wrtDtHwp = (qcDetailData.get("wrtDtHwp") != null) ? (String) qcDetailData.get("wrtDtHwp") : "";

		sb.append("	<P ColumnBreak=\"false\" PageBreak=\"" + pageBreak + "\" ParaShape=\"0\" Style=\"14\"><TEXT CharShape=\"8\">\n");
		sb.append("		<SECDEF CharGrid=\"0\" FirstBorder=\"false\" FirstFill=\"false\" LineGrid=\"0\" OutlineShape=\"1\" SpaceColumns=\"1134\" TabStop=\"8000\" TextDirection=\"0\" TextVerticalWidthHead=\"0\"><STARTNUMBER Equation=\"0\" Figure=\"0\" Page=\"0\" PageStartsOn=\"Both\" Table=\"0\"/><HIDE Border=\"false\" EmptyLine=\"false\" Fill=\"false\" Footer=\"false\" Header=\"false\" MasterPage=\"false\" PageNumPos=\"false\"/><PAGEDEF GutterType=\"LeftOnly\" Height=\"84188\" Landscape=\"0\" Width=\"59528\"><PAGEMARGIN Bottom=\"0\" Footer=\"4252\" Gutter=\"0\" Header=\"4252\" Left=\"4252\" Right=\"4110\" Top=\"0\"/></PAGEDEF><FOOTNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"5cm\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"283\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EachColumn\"/></FOOTNOTESHAPE><ENDNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"14692344\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"0\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EndOfDocument\"/></ENDNOTESHAPE><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Both\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Even\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Odd\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL></SECDEF>\n");
		sb.append("		<COLDEF Count=\"1\" Layout=\"Left\" SameGap=\"0\" SameSize=\"true\" Type=\"Newspaper\"/><CHAR>전 력 시 설 점 검 표</CHAR>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"11\"><CHAR>   ○ 시설명 : </CHAR></TEXT><TEXT CharShape=\"14\"><CHAR>" + fcltsMngGroupNm + "</CHAR></TEXT></P>\n");
		sb.append("	<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"11\">\n");
		sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"4\" PageBreak=\"Cell\" RepeatHeader=\"true\" RowCount=\""+rowCount+"\">\n");
		sb.append("			<SHAPEOBJECT InstId=\"1099298179\" Lock=\"false\" NumberingType=\"Table\" TextWrap=\"TopAndBottom\" ZOrder=\"14\"><SIZE Height=\"16407\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"49303\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"850\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/></SHAPEOBJECT>\n");
		sb.append("			<INSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"8\" ColAddr=\"0\" ColSpan=\"4\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2246\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"49303\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"25\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>※ 정상 : ○  요주의 : △  불량 : ×</CHAR></TEXT><TEXT CharShape=\"12\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"5\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2529\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"8140\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"27\" Style=\"0\"><TEXT CharShape=\"13\"><CHAR>시 설 명</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"6\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2529\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"24896\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"28\" Style=\"0\"><TEXT CharShape=\"13\"><CHAR>점 검 사 항</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"6\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2529\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"8703\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"28\" Style=\"0\"><TEXT CharShape=\"13\"><CHAR>결    과</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"7\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2529\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"7564\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"28\" Style=\"0\"><TEXT CharShape=\"13\"><CHAR>비 고</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");
		
		int rowAddr = 2;
		for(int i=0; i<qcItemUpperList.size(); i++) {
			HashMap<String, String> upperItem = qcItemUpperList.get(i);
			String qcItemUpperCd = upperItem.get("qcItemUpperCd");
			String qcItemUpperNm = upperItem.get("qcItemUpperNm");		
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"8140\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"27\" Style=\"0\"><TEXT CharShape=\"13\"><CHAR>"+qcItemUpperNm+"</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"6\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			int cellHeight = 0;
			for(EgovMap qcResultItem : qcResultItemList) {
				String upperCd = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemUpperCd") : "";
				if(qcItemUpperCd.equals(upperCd)) {
					String qcItemNm = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemNm") : "";
					sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"13\"><CHAR>" + qcItemNm +"</CHAR></TEXT></P>\n");
					cellHeight += 1939;
				}
			}
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"6\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\""+cellHeight+"\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"8703\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			for(EgovMap qcResultItem : qcResultItemList) {
				String upperCd = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemUpperCd") : "";
				if(qcItemUpperCd.equals(upperCd)) {
					String inspResultChk = (qcResultItem.get("inspResultChk") != null) ? (String) qcResultItem.get("inspResultChk") : "";
					sb.append("						<P ParaShape=\"29\" Style=\"0\"><TEXT CharShape=\"11\"><CHAR>" + getResultItemSymbol(inspResultChk) + "</CHAR></TEXT><TEXT CharShape=\"11\"/></P>\n");
				}
			}
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"7\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"7564\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"13\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			rowAddr++;
		}
		sb.append("		</TABLE><CHAR/>\n");
		sb.append("	</TEXT></P>\n");

		sb.append("	<P ParaShape=\"26\" Style=\"0\"><TEXT CharShape=\"11\"/></P>\n");
		sb.append("	<P ParaShape=\"21\" Style=\"0\"><TEXT CharShape=\"1\">\n");
		if(chargerInfo1 != null) {
			if((Boolean)chargerInfo1.get("fileExists")) {			
				sb.append("		<PICTURE Reverse=\"false\"><SHAPEOBJECT InstId=\"1099298180\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"4\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"38640\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"1940\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT><SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"25556357\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT><IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/><IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3780\" Top=\"0\"/><INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/><IMAGE Alpha=\"0\" BinItem=\"" + getBinId((String)chargerInfo1.get("signFileNmPhysicl")) +  "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/></PICTURE>\n");
			}
		}
		sb.append("		<CHAR/>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"21\" Style=\"0\"><TEXT CharShape=\"5\">\n");
		sb.append("		<CHAR>                                  점검일자 :  </CHAR></TEXT><TEXT CharShape=\"6\"><CHAR>" + wrtDtHwp + "</CHAR>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"5\">\n");
		if(chargerInfo2 != null) {
			if((Boolean)chargerInfo2.get("fileExists")) {			
				sb.append("		<PICTURE Reverse=\"false\"><SHAPEOBJECT InstId=\"1099298182\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"5\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"38820\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"1060\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT><SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"25556359\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT><IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/><IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3780\" Top=\"0\"/><INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/><IMAGE Alpha=\"0\" BinItem=\"" + getBinId((String)chargerInfo2.get("signFileNmPhysicl")) +  "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/></PICTURE>\n");
			}
		}
		if(chargerInfo1 != null) {
			String chargerNm = (chargerInfo1.get("chargerNm") != null) ? (String)chargerInfo1.get("chargerNm") :  "      ";			
			sb.append("		<CHAR>                                  점 검 자 :  "+chargerNm+"    (인)</CHAR>\n");
		} else {
			sb.append("		<CHAR>                                  점 검 자 :              (인)</CHAR>\n");
		}
		sb.append("	</TEXT></P>\n");
		if(chargerInfo2 != null) {
			sb.append("	<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"5\">\n");
			String chargerNm = (chargerInfo2.get("chargerNm") != null) ? (String)chargerInfo2.get("chargerNm") :  "      ";
			sb.append("		<CHAR>                                              "+chargerNm+"    (인)</CHAR>\n");
			sb.append("	</TEXT></P>\n");
		}
		return sb;
	}

	// 정보통신시설점검표 Body
	protected StringBuilder getInfoCommBodyElement(int bodyIndex) {
		StringBuilder sb = new StringBuilder();
		
		GamFcltyQcHwpMngResultInfo resultInfo = qcDataList.get(bodyIndex);
		
		EgovMap qcDetailData = resultInfo.getQcDetailData();
		Map<String, Object> chargerInfo1 = resultInfo.getChargerInfo1();
		Map<String, Object> chargerInfo2 = resultInfo.getChargerInfo2();
		List<EgovMap> qcResultItemList = resultInfo.getQcResultItemList();
		
		List<HashMap<String, String>> qcItemUpperList = getQcUpperItemList(qcResultItemList);
		
		String pageBreak = (bodyIndex == 0) ? "false" : "true";		
		String fcltsMngGroupNm = (String) qcDetailData.get("fcltsMngGroupNm");

		int rowCount = qcItemUpperList.size() + 2; //제목row 포함한 rowCount 값	

		String wrtDtHwp = (qcDetailData.get("wrtDtHwp") != null) ? (String) qcDetailData.get("wrtDtHwp") : "";

		sb.append("	<P ColumnBreak=\"false\" PageBreak=\""+pageBreak+"\" ParaShape=\"0\" Style=\"14\"><TEXT CharShape=\"8\">\n");
		sb.append("		<SECDEF CharGrid=\"0\" FirstBorder=\"false\" FirstFill=\"false\" LineGrid=\"0\" OutlineShape=\"1\" SpaceColumns=\"1134\" TabStop=\"8000\" TextDirection=\"0\" TextVerticalWidthHead=\"0\"><STARTNUMBER Equation=\"0\" Figure=\"0\" Page=\"0\" PageStartsOn=\"Both\" Table=\"0\"/><HIDE Border=\"false\" EmptyLine=\"false\" Fill=\"false\" Footer=\"false\" Header=\"false\" MasterPage=\"false\" PageNumPos=\"false\"/><PAGEDEF GutterType=\"LeftOnly\" Height=\"84188\" Landscape=\"0\" Width=\"59528\"><PAGEMARGIN Bottom=\"0\" Footer=\"4252\" Gutter=\"0\" Header=\"4252\" Left=\"4252\" Right=\"4110\" Top=\"0\"/></PAGEDEF><FOOTNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"5cm\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"283\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EachColumn\"/></FOOTNOTESHAPE><ENDNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"14692344\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"0\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EndOfDocument\"/></ENDNOTESHAPE><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Both\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Even\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Odd\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL></SECDEF>\n");
		sb.append("		<COLDEF Count=\"1\" Layout=\"Left\" SameGap=\"0\" SameSize=\"true\" Type=\"Newspaper\"/><CHAR>정 보 통 신 시 설 점 검 표</CHAR>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"0\" Style=\"14\"><TEXT CharShape=\"8\"/></P>\n");
		sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"11\"><CHAR>   ○ 시설명 : " + fcltsMngGroupNm + "</CHAR></TEXT></P>\n");
		sb.append("	<P ParaShape=\"0\" Style=\"0\"><TEXT CharShape=\"11\">\n");
		sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"4\" PageBreak=\"Cell\" RepeatHeader=\"true\" RowCount=\""+rowCount+"\">\n");
		sb.append("			<SHAPEOBJECT InstId=\"1099298184\" Lock=\"false\" NumberingType=\"Table\" TextWrap=\"TopAndBottom\" ZOrder=\"15\"><SIZE Height=\"19478\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"47888\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"1417\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/></SHAPEOBJECT>\n");
		sb.append("			<INSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"8\" ColAddr=\"0\" ColSpan=\"4\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2529\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"47888\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"30\" Style=\"0\"><TEXT CharShape=\"12\"><CHAR>※ 정상 : ○  요주의 : △  불량 : ×</CHAR></TEXT><TEXT CharShape=\"12\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"5\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2529\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"8140\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"31\" Style=\"0\"><TEXT CharShape=\"13\"><CHAR>시 설 명</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"6\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2529\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"24896\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"32\" Style=\"0\"><TEXT CharShape=\"13\"><CHAR>점 검 사 항</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"6\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2529\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"8703\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"32\" Style=\"0\"><TEXT CharShape=\"13\"><CHAR>결    과</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"7\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2529\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"6149\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"32\" Style=\"0\"><TEXT CharShape=\"13\"><CHAR>비 고</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");

		int rowAddr = 2;
		for(int i=0; i<qcItemUpperList.size(); i++) {
			HashMap<String, String> upperItem = qcItemUpperList.get(i);
			String qcItemUpperCd = upperItem.get("qcItemUpperCd");
			String qcItemUpperNm = upperItem.get("qcItemUpperNm");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"5\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"8140\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"31\" Style=\"0\"><TEXT CharShape=\"13\"><CHAR>"+qcItemUpperNm+"</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"6\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"24896\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			int cellHeight = 1454*2;
			for(EgovMap qcResultItem : qcResultItemList) {
				String upperCd = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemUpperCd") : "";
				if(qcItemUpperCd.equals(upperCd)) {
					String qcItemNm = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemNm") : "";
					sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"13\"><CHAR>"+qcItemNm+"</CHAR></TEXT></P>\n");
					cellHeight += 1454;
				}
			}			
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"6\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\""+cellHeight+"\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"8703\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			for(EgovMap qcResultItem : qcResultItemList) {
				String upperCd = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemUpperCd") : "";
				if(qcItemUpperCd.equals(upperCd)) {
					String inspResultChk = (qcResultItem.get("inspResultChk") != null) ? (String) qcResultItem.get("inspResultChk") : "";
					sb.append("						<P ParaShape=\"32\" Style=\"0\"><TEXT CharShape=\"13\"><CHAR>" + getResultItemSymbol(inspResultChk) + "</CHAR></TEXT></P>\n");
				}
			}
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"7\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"6149\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"24\" Style=\"0\"><TEXT CharShape=\"13\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			rowAddr++;
		}
		sb.append("		</TABLE><CHAR/>\n");
		sb.append("	</TEXT></P>\n");
		
		sb.append("	<P ParaShape=\"33\" Style=\"0\"><TEXT CharShape=\"11\"/></P>\n");
		sb.append("	<P ParaShape=\"2\" Style=\"0\"><TEXT CharShape=\"5\">\n");
		if(chargerInfo1 != null) {
			if((Boolean)chargerInfo1.get("fileExists")) {
				sb.append("		<PICTURE Reverse=\"false\"><SHAPEOBJECT InstId=\"1099298185\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"6\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"42840\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"180\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT><SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"25556362\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT><IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/><IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3780\" Top=\"0\"/><INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/><IMAGE Alpha=\"0\" BinItem=\"" + getBinId((String)chargerInfo1.get("signFileNmPhysicl")) + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/></PICTURE>\n");
			}
		}
		sb.append("		<CHAR>                                         점검일자 : </CHAR></TEXT><TEXT CharShape=\"6\"><CHAR>" + wrtDtHwp + "</CHAR>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"5\">\n");
		if(chargerInfo2 != null) {
			if((Boolean)chargerInfo2.get("fileExists")) {		
				sb.append("		<PICTURE Reverse=\"false\"><SHAPEOBJECT InstId=\"1099298187\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"7\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"42900\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"1870\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT><SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"25556364\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT><IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/><IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3780\" Top=\"0\"/><INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/><IMAGE Alpha=\"0\" BinItem=\"" + getBinId((String)chargerInfo2.get("signFileNmPhysicl")) + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/></PICTURE>\n");
			}
		}
		if(chargerInfo1 != null) {
			String chargerNm = (chargerInfo1.get("chargerNm") != null) ? (String)chargerInfo1.get("chargerNm") :  "      ";			
			sb.append("		<CHAR>                                         점 검 자 : "+chargerNm+"    (인)</CHAR>\n");
		} else {
			sb.append("		<CHAR>                                         점 검 자 :             (인)</CHAR>\n");
		}
		sb.append("	</TEXT></P>\n");
		if(chargerInfo2 != null) {
			sb.append("	<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"5\">\n");
			String chargerNm = (chargerInfo2.get("chargerNm") != null) ? (String)chargerInfo2.get("chargerNm") :  "      ";
			sb.append("		<CHAR>                                                    "+chargerNm+"    (인)</CHAR>\n");
			sb.append("	</TEXT></P>\n"); 
		}
		return sb;
	}

	// 기계(건축설비)시설점검표 Body
	protected StringBuilder getArchMechBodyElement(int bodyIndex) {
		StringBuilder sb = new StringBuilder();

		GamFcltyQcHwpMngResultInfo resultInfo = qcDataList.get(bodyIndex);
		
		EgovMap qcDetailData = resultInfo.getQcDetailData();
		Map<String, Object> chargerInfo1 = resultInfo.getChargerInfo1();
		Map<String, Object> chargerInfo2 = resultInfo.getChargerInfo2();
		List<EgovMap> qcResultItemList = resultInfo.getQcResultItemList();
		
		List<HashMap<String, String>> qcItemUpperList = getQcUpperItemList(qcResultItemList);
		
		String pageBreak = (bodyIndex == 0) ? "false" : "true";		
		String fcltsMngGroupNm = (String) qcDetailData.get("fcltsMngGroupNm");

		int rowCount = qcItemUpperList.size() + 2; //제목row 포함한 rowCount 값	

		String wrtDtHwp = (qcDetailData.get("wrtDtHwp") != null) ? (String) qcDetailData.get("wrtDtHwp") : "";
		
		sb.append("	<P ColumnBreak=\"false\" PageBreak=\"" + pageBreak + "\" ParaShape=\"0\" Style=\"14\"><TEXT CharShape=\"8\">\n");
		sb.append("		<SECDEF CharGrid=\"0\" FirstBorder=\"false\" FirstFill=\"false\" LineGrid=\"0\" OutlineShape=\"1\" SpaceColumns=\"1134\" TabStop=\"8000\" TextDirection=\"0\" TextVerticalWidthHead=\"0\"><STARTNUMBER Equation=\"0\" Figure=\"0\" Page=\"0\" PageStartsOn=\"Both\" Table=\"0\"/><HIDE Border=\"false\" EmptyLine=\"false\" Fill=\"false\" Footer=\"false\" Header=\"false\" MasterPage=\"false\" PageNumPos=\"false\"/><PAGEDEF GutterType=\"LeftOnly\" Height=\"84188\" Landscape=\"0\" Width=\"59528\"><PAGEMARGIN Bottom=\"0\" Footer=\"4252\" Gutter=\"0\" Header=\"4252\" Left=\"4252\" Right=\"4110\" Top=\"0\"/></PAGEDEF><FOOTNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"5cm\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"283\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EachColumn\"/></FOOTNOTESHAPE><ENDNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"14692344\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"0\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EndOfDocument\"/></ENDNOTESHAPE><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Both\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Even\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Odd\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL></SECDEF>\n");
		sb.append("		<COLDEF Count=\"1\" Layout=\"Left\" SameGap=\"0\" SameSize=\"true\" Type=\"Newspaper\"/><CHAR>기 계 설 비 점 검 표</CHAR>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"5\"/></P>\n");
		sb.append("	<P ParaShape=\"1\" Style=\"0\"><TEXT CharShape=\"5\"><CHAR>○ 시설명 : </CHAR></TEXT><TEXT CharShape=\"6\"><CHAR>" + fcltsMngGroupNm + "</CHAR></TEXT></P>\n");
		sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"5\">\n");
		sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"4\" PageBreak=\"Cell\" RepeatHeader=\"true\" RowCount=\""+rowCount+"\">\n");
		sb.append("			<SHAPEOBJECT InstId=\"1099298174\" Lock=\"false\" NumberingType=\"Table\" TextWrap=\"TopAndBottom\" ZOrder=\"13\"><SIZE Height=\"17430\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"49884\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/></SHAPEOBJECT>\n");
		sb.append("			<INSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"4\" ColAddr=\"0\" ColSpan=\"4\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2812\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"49884\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>※ 정상 : ○ 요주의 : △ 불량 : ×</CHAR></TEXT><TEXT CharShape=\"9\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"3402\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"8284\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"22\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>구    분</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"3402\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"25040\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"22\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점    검    내    용</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"3402\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"9131\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"22\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점 검 결 과</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"3402\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"7429\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"22\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>비  고</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");
		
		int rowAddr = 2;
		for(int i=0; i<qcItemUpperList.size(); i++) {
			HashMap<String, String> upperItem = qcItemUpperList.get(i);
			String qcItemUpperCd = upperItem.get("qcItemUpperCd");
			String qcItemUpperNm = upperItem.get("qcItemUpperNm");
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"8284\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"22\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>"+qcItemUpperNm+"</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"25040\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			int cellHeight = 0;
			for(EgovMap qcResultItem : qcResultItemList) {
				String upperCd = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemUpperCd") : "";
				if(qcItemUpperCd.equals(upperCd)) {
					String qcItemNm = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemNm") : "";
					sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>○ "+qcItemNm+"</CHAR></TEXT></P>\n");
					cellHeight += 2243;
				}
			}			
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\""+cellHeight+"\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"9131\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			for(EgovMap qcResultItem : qcResultItemList) {
				String upperCd = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemUpperCd") : "";
				if(qcItemUpperCd.equals(upperCd)) {
					String inspResultChk = (qcResultItem.get("inspResultChk") != null) ? (String) qcResultItem.get("inspResultChk") : "";
					sb.append("						<P ParaShape=\"23\" Style=\"0\"><TEXT CharShape=\"5\"><CHAR>" + getResultItemSymbol(inspResultChk) + "</CHAR></TEXT></P>\n");
				}
			}
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"3\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"7429\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"10\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			rowAddr++;
		}
		sb.append("		</TABLE><CHAR/>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"21\" Style=\"0\"><TEXT CharShape=\"5\"/></P>\n");
		sb.append("	<P ParaShape=\"21\" Style=\"0\"><TEXT CharShape=\"5\">\n");
		if(chargerInfo1 != null) {
			if((Boolean)chargerInfo1.get("fileExists")) {
				sb.append("		<PICTURE Reverse=\"false\"><SHAPEOBJECT InstId=\"1099298175\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"2\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"42720\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"400\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT><SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"25556352\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT><IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/><IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3780\" Top=\"0\"/><INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/><IMAGE Alpha=\"0\" BinItem=\"" + getBinId((String)chargerInfo1.get("signFileNmPhysicl")) + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/></PICTURE>\n");
			}
		}
		sb.append("		<CHAR>                                         점검일자 : </CHAR></TEXT><TEXT CharShape=\"6\"><CHAR>" + wrtDtHwp + "</CHAR>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"5\">\n");
		if(chargerInfo2 != null) {
			if((Boolean)chargerInfo2.get("fileExists")) {		
				sb.append("		<PICTURE Reverse=\"false\"><SHAPEOBJECT InstId=\"1099298177\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"3\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"42900\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"1800\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT><SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"25556354\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT><IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/><IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3780\" Top=\"0\"/><INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/><IMAGE Alpha=\"0\" BinItem=\"" + getBinId((String)chargerInfo2.get("signFileNmPhysicl")) + "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/></PICTURE>\n");
			}
		}
		if(chargerInfo1 != null) {
			String chargerNm = (chargerInfo1.get("chargerNm") != null) ? (String)chargerInfo1.get("chargerNm") :  "      ";			
			sb.append("		<CHAR>                                         점 검 자 : " + chargerNm + "    (인)</CHAR>\n");
		} else {
			sb.append("		<CHAR>                                         점 검 자 :             (인)</CHAR>\n");
		}
		sb.append("	</TEXT></P>\n");
		if(chargerInfo2 != null) {
			sb.append("	<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"5\">\n");
			String chargerNm = (chargerInfo2.get("chargerNm") != null) ? (String)chargerInfo2.get("chargerNm") :  "      ";
			sb.append("		<CHAR>                                                    " + chargerNm + "    (인)</CHAR>\n");
			sb.append("	</TEXT></P>\n");
		}
		return sb;
	}
	
	// 기계(하역장비)시설점검표 Body
	protected StringBuilder getCraneMechBodyElement(int bodyIndex) {
		StringBuilder sb = new StringBuilder();

		GamFcltyQcHwpMngResultInfo resultInfo = qcDataList.get(bodyIndex);
		
		EgovMap qcDetailData = resultInfo.getQcDetailData();
		Map<String, Object> chargerInfo1 = resultInfo.getChargerInfo1();
		Map<String, Object> chargerInfo2 = resultInfo.getChargerInfo2();
		List<EgovMap> qcResultItemList = resultInfo.getQcResultItemList();
		
		List<HashMap<String, String>> qcItemUpperList = getQcUpperItemList(qcResultItemList);

		List<HashMap<String, String>> containerCraneMUpperList = getCraneMechQcUpperItemList(qcItemUpperList, "M0201"); //컨테이너 크레인의 기계부 상위항목 목록
		List<HashMap<String, String>> containerCraneEUpperList = getCraneMechQcUpperItemList(qcItemUpperList, "M0202"); //컨테이너 크레인의 전기부 상위항목 목록
		
		List<HashMap<String, String>> transferCraneMUpperList = getCraneMechQcUpperItemList(qcItemUpperList, "M0203"); //트랜스퍼 크레인의 기계부 상위항목 목록
		List<HashMap<String, String>> transferCraneEUpperList = getCraneMechQcUpperItemList(qcItemUpperList, "M0204"); //트랜스퍼 크레인의 전기부 상위항목 목록
		
		String pageBreak = (bodyIndex == 0) ? "false" : "true";		
		String fcltsMngGroupNm = (String) qcDetailData.get("fcltsMngGroupNm");

		int rowCount = 0; //제목row 포함한 rowCount 값	

		String wrtDtHwp = (qcDetailData.get("wrtDtHwp") != null) ? (String) qcDetailData.get("wrtDtHwp") : "";

		sb.append("	<P ColumnBreak=\"false\" PageBreak=\"" + pageBreak +"\" ParaShape=\"0\" Style=\"14\"><TEXT CharShape=\"8\">\n");
		sb.append("		<SECDEF CharGrid=\"0\" FirstBorder=\"false\" FirstFill=\"false\" LineGrid=\"0\" OutlineShape=\"1\" SpaceColumns=\"1134\" TabStop=\"8000\" TextDirection=\"0\" TextVerticalWidthHead=\"0\"><STARTNUMBER Equation=\"0\" Figure=\"0\" Page=\"0\" PageStartsOn=\"Both\" Table=\"0\"/><HIDE Border=\"false\" EmptyLine=\"false\" Fill=\"false\" Footer=\"false\" Header=\"false\" MasterPage=\"false\" PageNumPos=\"false\"/><PAGEDEF GutterType=\"LeftOnly\" Height=\"84188\" Landscape=\"0\" Width=\"59528\"><PAGEMARGIN Bottom=\"0\" Footer=\"4252\" Gutter=\"0\" Header=\"4252\" Left=\"4252\" Right=\"4110\" Top=\"0\"/></PAGEDEF><FOOTNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"5cm\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"283\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EachColumn\"/></FOOTNOTESHAPE><ENDNOTESHAPE><AUTONUMFORMAT SuffixChar=\")\" Superscript=\"false\" Type=\"Digit\"/><NOTELINE Length=\"14692344\" Type=\"Solid\" Width=\"0.12mm\"/><NOTESPACING AboveLine=\"850\" BelowLine=\"567\" BetweenNotes=\"0\"/><NOTENUMBERING NewNumber=\"1\" Type=\"Continuous\"/><NOTEPLACEMENT BeneathText=\"false\" Place=\"EndOfDocument\"/></ENDNOTESHAPE><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Both\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Even\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL><PAGEBORDERFILL FillArea=\"Paper\" FooterInside=\"false\" HeaderInside=\"false\" TextBorder=\"true\" Type=\"Odd\"><PAGEOFFSET Bottom=\"1417\" Left=\"1417\" Right=\"1417\" Top=\"1417\"/></PAGEBORDERFILL></SECDEF>\n");
		sb.append("		<COLDEF Count=\"1\" Layout=\"Left\" SameGap=\"0\" SameSize=\"true\" Type=\"Newspaper\"/><CHAR>항만 하역장비 안전 점검표</CHAR>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"0\" Style=\"14\"><TEXT CharShape=\"8\"/></P>\n");
		sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"9\">\n");
		sb.append("		<CHAR> </CHAR></TEXT><TEXT CharShape=\"5\"><CHAR>○ 시설명 : </CHAR></TEXT><TEXT CharShape=\"6\"><CHAR>" + fcltsMngGroupNm + "</CHAR>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"5\"/></P>\n");
		sb.append("	<P ParaShape=\"14\" Style=\"0\"><TEXT CharShape=\"9\"><CHAR>※ 정상 ○ 요주의 : △ 불량 : ×</CHAR></TEXT></P>\n");
		sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"5\">\n");
		
		//컨테이너 크레인의 표 출력 start
		rowCount = 4 + containerCraneMUpperList.size() + containerCraneEUpperList.size(); //4 = 제목해더 row + (기계부) + (전기부)
		sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"4\" PageBreak=\"Cell\" RepeatHeader=\"true\" RowCount=\""+rowCount+"\">\n");
		sb.append("			<SHAPEOBJECT InstId=\"1099298195\" Lock=\"false\" NumberingType=\"Table\" TextWrap=\"TopAndBottom\" ZOrder=\"18\"><SIZE Height=\"17128\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"50450\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/></SHAPEOBJECT>\n");
		sb.append("			<INSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"20\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2248\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"11976\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"35\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>장   비   명</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"21\" ColAddr=\"1\" ColSpan=\"3\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2248\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"38474\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"35\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>컨  테  이  너  크  레  인</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"22\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2248\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"11976\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"35\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점 검 개 소</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"19\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2248\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"24200\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"35\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점검사항</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"19\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2248\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"7413\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"35\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점검결과</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"23\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2248\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"6861\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"35\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>비  고</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"17\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"11976\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"35\" Style=\"0\"><TEXT CharShape=\"20\"><CHAR>(기 계 부)</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"14\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"24200\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"21\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"14\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"7413\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"35\" Style=\"0\"><TEXT CharShape=\"10\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"15\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"6861\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"10\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");
		
		//컨테이너 크레인의 기계부 항목 start
		int rowAddr = 3;
		for(int i=0; i<containerCraneMUpperList.size(); i++) {
			HashMap<String, String> upperItem = containerCraneMUpperList.get(i);
			String qcItemUpperCd = upperItem.get("qcItemUpperCd");
			String qcItemUpperNm = upperItem.get("qcItemUpperNm");
			int number = i+1;
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"24\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>"+number+". "+qcItemUpperNm+"</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"18\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"24200\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			int cellHeight = 0;
			for(EgovMap qcResultItem : qcResultItemList) {
				String upperCd = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemUpperCd") : "";
				if(qcItemUpperCd.equals(upperCd)) {
					String qcItemNm = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemNm") : "";
					sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"21\"><CHAR>○ "+qcItemNm+"</CHAR></TEXT></P>\n");
					cellHeight += 2034;
				}
			}			
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"18\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\""+cellHeight+"\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"7413\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			for(EgovMap qcResultItem : qcResultItemList) {
				String upperCd = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemUpperCd") : "";
				if(qcItemUpperCd.equals(upperCd)) {
					String inspResultChk = (qcResultItem.get("inspResultChk") != null) ? (String) qcResultItem.get("inspResultChk") : "";
					sb.append("						<P ParaShape=\"29\" Style=\"0\"><TEXT CharShape=\"5\"><CHAR>" + getResultItemSymbol(inspResultChk) + "</CHAR></TEXT></P>\n");
				}
			}
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"25\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"6861\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"10\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			rowAddr++;
		}
		//컨테이너 크레인의 기계부 항목 end
		
		////컨테이너 크레인의 전기부 항목 start
		int borderFills[][] = {{10, 3, 3, 11}, {16, 12, 12, 13}};
		int borderFill[] = (containerCraneEUpperList.size()>0) ? borderFills[0] : borderFills[1];
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\""+borderFill[0]+"\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"11976\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"35\" Style=\"0\"><TEXT CharShape=\"20\"><CHAR>(전 기 부)</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\""+borderFill[1]+"\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"24200\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"21\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\""+borderFill[2]+"\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"7413\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"29\" Style=\"0\"><TEXT CharShape=\"10\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\""+borderFill[3]+"\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"6861\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"10\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");
		rowAddr++;
		
		//컨테이너 크레인의 전기부 항목 start
		for(int i=0; i<containerCraneEUpperList.size(); i++) {
			HashMap<String, String> upperItem = containerCraneEUpperList.get(i);
			String qcItemUpperCd = upperItem.get("qcItemUpperCd");
			String qcItemUpperNm = upperItem.get("qcItemUpperNm");
			borderFill = (i < containerCraneEUpperList.size() - 1) ? borderFills[0] : borderFills[1];
			int number = i+1;
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\""+borderFill[0]+"\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>"+number+". "+qcItemUpperNm+"</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\""+borderFill[1]+"\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"24200\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			int cellHeight = 0;
			for(EgovMap qcResultItem : qcResultItemList) {
				String upperCd = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemUpperCd") : "";
				if(qcItemUpperCd.equals(upperCd)) {
					String qcItemNm = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemNm") : "";
					sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"21\"><CHAR>○ "+qcItemNm+"</CHAR></TEXT></P>\n");
					cellHeight += 2034;
				}
			}			
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\""+borderFill[2]+"\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\""+cellHeight+"\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"7413\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			for(EgovMap qcResultItem : qcResultItemList) {
				String upperCd = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemUpperCd") : "";
				if(qcItemUpperCd.equals(upperCd)) {
					String inspResultChk = (qcResultItem.get("inspResultChk") != null) ? (String) qcResultItem.get("inspResultChk") : "";
					sb.append("						<P ParaShape=\"29\" Style=\"0\"><TEXT CharShape=\"5\"><CHAR>" + getResultItemSymbol(inspResultChk) + "</CHAR></TEXT></P>\n");
				}
			}
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\""+borderFill[3]+"\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"6861\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"10\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			rowAddr++;
		}
		//컨테이너 크레인의 기계부 항목 end
		
		sb.append("		</TABLE><CHAR/>\n");
		//컨테이너 크레인의 표 출력 end
		
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"5\"/></P>\n");
		sb.append("	<P ColumnBreak=\"false\" PageBreak=\"true\" ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"1\">\n");

		//트렌스퍼 크레인의 표 출력 start
		rowCount = 4 + transferCraneMUpperList.size() + transferCraneEUpperList.size(); //4 = 제목해더 row + (기계부) + (전기부)
		sb.append("		<TABLE BorderFill=\"1\" CellSpacing=\"0\" ColCount=\"4\" PageBreak=\"Cell\" RepeatHeader=\"true\" RowCount=\""+rowCount+"\">\n");
		sb.append("			<SHAPEOBJECT InstId=\"1099298196\" Lock=\"false\" NumberingType=\"Table\" TextWrap=\"TopAndBottom\" ZOrder=\"19\"><SIZE Height=\"17128\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"50450\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"false\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"0\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"0\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/></SHAPEOBJECT>\n");
		sb.append("			<INSIDEMARGIN Bottom=\"140\" Left=\"140\" Right=\"140\" Top=\"140\"/>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"20\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2248\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"11976\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"35\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>장   비   명</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"21\" ColAddr=\"1\" ColSpan=\"3\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2248\" Protect=\"false\" RowAddr=\"0\" RowSpan=\"1\" Width=\"38474\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"35\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>트  란  스  퍼  크  레  인</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"22\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2248\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"11976\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"35\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점 검 개 소</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"19\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2248\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"24200\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"35\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점검사항</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"19\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2248\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"7413\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"35\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>점검결과</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"23\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"true\" Height=\"2248\" Protect=\"false\" RowAddr=\"1\" RowSpan=\"1\" Width=\"6861\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"35\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>비  고</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\"17\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"11976\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"35\" Style=\"0\"><TEXT CharShape=\"20\"><CHAR>(기 계 부)</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"14\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"24200\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"21\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"14\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"7413\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"35\" Style=\"0\"><TEXT CharShape=\"10\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\"15\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\"2\" RowSpan=\"1\" Width=\"6861\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"10\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");

		//트란스퍼 크레인의 기계부 항목 start
		rowAddr = 3;
		for(int i=0; i<transferCraneMUpperList.size(); i++) {
			HashMap<String, String> upperItem = transferCraneMUpperList.get(i);
			String qcItemUpperCd = upperItem.get("qcItemUpperCd");
			String qcItemUpperNm = upperItem.get("qcItemUpperNm");
			int number = i+1;
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\"24\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>"+number+". "+qcItemUpperNm+"</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"18\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"24200\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			int cellHeight = 0;
			for(EgovMap qcResultItem : qcResultItemList) {
				String upperCd = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemUpperCd") : "";
				if(qcItemUpperCd.equals(upperCd)) {
					String qcItemNm = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemNm") : "";
					sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"21\"><CHAR>○ "+qcItemNm+"</CHAR></TEXT></P>\n");
					cellHeight += 2034;
				}
			}			
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"18\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\""+cellHeight+"\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"7413\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			for(EgovMap qcResultItem : qcResultItemList) {
				String upperCd = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemUpperCd") : "";
				if(qcItemUpperCd.equals(upperCd)) {
					String inspResultChk = (qcResultItem.get("inspResultChk") != null) ? (String) qcResultItem.get("inspResultChk") : "";
					sb.append("						<P ParaShape=\"29\" Style=\"0\"><TEXT CharShape=\"5\"><CHAR>" + getResultItemSymbol(inspResultChk) + "</CHAR></TEXT></P>\n");
				}
			}
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\"25\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"6861\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"10\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			rowAddr++;
		}
		//트렌스퍼 크레인의 기계부 항목 end

		//트랜스퍼 크레인의 전기부 항목 start
		borderFill = (transferCraneEUpperList.size()>0) ? borderFills[0] : borderFills[1];
		sb.append("			<ROW>\n");
		sb.append("				<CELL BorderFill=\""+borderFill[0]+"\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"11976\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"35\" Style=\"0\"><TEXT CharShape=\"20\"><CHAR>(전 기 부)</CHAR></TEXT></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\""+borderFill[1]+"\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"24200\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"21\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\""+borderFill[2]+"\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"7413\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"29\" Style=\"0\"><TEXT CharShape=\"10\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("				<CELL BorderFill=\""+borderFill[3]+"\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"2248\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"6861\">\n");
		sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
		sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"10\"/></P>\n");
		sb.append("					</PARALIST>\n");
		sb.append("				</CELL>\n");
		sb.append("			</ROW>\n");
		rowAddr++;
		
		//트란스퍼 크레인의 전기부 항목 start
		for(int i=0; i<transferCraneEUpperList.size(); i++) {
			HashMap<String, String> upperItem = transferCraneEUpperList.get(i);
			String qcItemUpperCd = upperItem.get("qcItemUpperCd");
			String qcItemUpperNm = upperItem.get("qcItemUpperNm");
			borderFill = (i < transferCraneEUpperList.size() - 1) ? borderFills[0] : borderFills[1];
			int number = i+1;
			sb.append("			<ROW>\n");
			sb.append("				<CELL BorderFill=\""+borderFill[0]+"\" ColAddr=\"0\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"11976\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"10\"><CHAR>"+number+". "+qcItemUpperNm+"</CHAR></TEXT></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\""+borderFill[1]+"\" ColAddr=\"1\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"4068\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"24200\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			int cellHeight = 0;
			for(EgovMap qcResultItem : qcResultItemList) {
				String upperCd = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemUpperCd") : "";
				if(qcItemUpperCd.equals(upperCd)) {
					String qcItemNm = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemNm") : "";
					sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"21\"><CHAR>○ "+qcItemNm+"</CHAR></TEXT></P>\n");
					cellHeight += 2034;
				}
			}			
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\""+borderFill[2]+"\" ColAddr=\"2\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\""+cellHeight+"\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"7413\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			for(EgovMap qcResultItem : qcResultItemList) {
				String upperCd = (qcResultItem.get("qcItemUpperCd") != null) ? (String)qcResultItem.get("qcItemUpperCd") : "";
				if(qcItemUpperCd.equals(upperCd)) {
					String inspResultChk = (qcResultItem.get("inspResultChk") != null) ? (String) qcResultItem.get("inspResultChk") : "";
					sb.append("						<P ParaShape=\"29\" Style=\"0\"><TEXT CharShape=\"5\"><CHAR>" + getResultItemSymbol(inspResultChk) + "</CHAR></TEXT></P>\n");
				}
			}
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("				<CELL BorderFill=\""+borderFill[3]+"\" ColAddr=\"3\" ColSpan=\"1\" Dirty=\"false\" Editable=\"false\" HasMargin=\"false\" Header=\"false\" Height=\"0\" Protect=\"false\" RowAddr=\""+rowAddr+"\" RowSpan=\"1\" Width=\"6861\">\n");
			sb.append("					<PARALIST LineWrap=\"Break\" LinkListID=\"0\" LinkListIDNext=\"0\" TextDirection=\"0\" VertAlign=\"Center\">\n");
			sb.append("						<P ParaShape=\"16\" Style=\"0\"><TEXT CharShape=\"10\"/></P>\n");
			sb.append("					</PARALIST>\n");
			sb.append("				</CELL>\n");
			sb.append("			</ROW>\n");
			rowAddr++;
		}
		//트렌스퍼 크레인의 전기부 항목 end
	
		sb.append("		</TABLE><CHAR/>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"15\" Style=\"0\"><TEXT CharShape=\"1\"/></P>\n");
		sb.append("	<P ParaShape=\"21\" Style=\"0\"><TEXT CharShape=\"5\">\n");
		if(chargerInfo1 != null) {
			if((Boolean)chargerInfo1.get("fileExists")) {			
				sb.append("		<PICTURE Reverse=\"false\"><SHAPEOBJECT InstId=\"1099298197\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"10\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"41940\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"480\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT><SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"25556374\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT><IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/><IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3780\" Top=\"0\"/><INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/><IMAGE Alpha=\"0\" BinItem=\"" + getBinId((String)chargerInfo1.get("signFileNmPhysicl")) +  "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/></PICTURE>\n");
			}
		}
		sb.append("		<CHAR>                                        점검일자 : </CHAR></TEXT><TEXT CharShape=\"6\"><CHAR>" + wrtDtHwp + "</CHAR>\n");
		sb.append("	</TEXT></P>\n");
		sb.append("	<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"5\">\n");
		if(chargerInfo2 != null) {
			if((Boolean)chargerInfo2.get("fileExists")) {			
				sb.append("		<PICTURE Reverse=\"false\"><SHAPEOBJECT InstId=\"1099298199\" Lock=\"false\" NumberingType=\"Figure\" TextWrap=\"BehindText\" ZOrder=\"11\"><SIZE Height=\"4500\" HeightRelTo=\"Absolute\" Protect=\"false\" Width=\"3840\" WidthRelTo=\"Absolute\"/><POSITION AffectLSpacing=\"false\" AllowOverlap=\"true\" FlowWithText=\"true\" HoldAnchorAndSO=\"false\" HorzAlign=\"Left\" HorzOffset=\"42060\" HorzRelTo=\"Column\" TreatAsChar=\"false\" VertAlign=\"Top\" VertOffset=\"1880\" VertRelTo=\"Para\"/><OUTSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/></SHAPEOBJECT><SHAPECOMPONENT GroupLevel=\"0\" HorzFlip=\"false\" InstID=\"25556376\" OriHeight=\"4500\" OriWidth=\"3840\" VertFlip=\"false\" XPos=\"0\" YPos=\"0\"><ROTATIONINFO Angle=\"0\" CenterX=\"1920\" CenterY=\"2250\"/><RENDERINGINFO><TRANSMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><SCAMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/><ROTMATRIX E1=\"1.00000\" E2=\"0.00000\" E3=\"0.00000\" E4=\"0.00000\" E5=\"1.00000\" E6=\"0.00000\"/></RENDERINGINFO></SHAPECOMPONENT><IMAGERECT X0=\"0\" X1=\"3840\" X2=\"3840\" X3=\"0\" Y0=\"0\" Y1=\"0\" Y2=\"4500\" Y3=\"4500\"/><IMAGECLIP Bottom=\"4500\" Left=\"0\" Right=\"3780\" Top=\"0\"/><INSIDEMARGIN Bottom=\"0\" Left=\"0\" Right=\"0\" Top=\"0\"/><IMAGE Alpha=\"0\" BinItem=\"" + getBinId((String)chargerInfo2.get("signFileNmPhysicl")) +  "\" Bright=\"0\" Contrast=\"0\" Effect=\"RealPic\"/></PICTURE>\n");
			}
		}
		if(chargerInfo1 != null) {
			String chargerNm = (chargerInfo1.get("chargerNm") != null) ? (String)chargerInfo1.get("chargerNm") :  "      ";			
			sb.append("		<CHAR>                                        점 검 자 : "+chargerNm+"    (인)</CHAR>\n");
		} else {
			sb.append("		<CHAR>                                        점 검 자 :             (인)</CHAR>\n");
		}
		sb.append("	</TEXT></P>\n");
		if(chargerInfo2 != null) {
			sb.append("	<P ParaShape=\"20\" Style=\"0\"><TEXT CharShape=\"5\">\n");
			String chargerNm = (chargerInfo2.get("chargerNm") != null) ? (String)chargerInfo2.get("chargerNm") :  "      ";		
			sb.append("		<CHAR>                                                   "+chargerNm+"    (인) </CHAR>\n");
			sb.append("	</TEXT></P>\n");
		}
		return sb;
	}
}
