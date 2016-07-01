/**
 * 
 */
package egovframework.rte.cmmn.excel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.joda.time.LocalDate;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 28.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 28.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class HtldRentNticReportExcelView extends AbstractExcelView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook wb, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Map<String, Object> nticData = (Map<String, Object>) model.get("nticData");
		
		HSSFSheet sheet = wb.createSheet();

		if(nticData.containsKey("resultCode")) {
			if((Integer)nticData.get("resultCode")!=0) {
				String fileName = "산출내역서.xls";
				fileName = new String(fileName.getBytes("euc-kr"), "8859_1");
				response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
				response.setHeader("Content-Transfer-Encoding", "binary");
				HSSFCell cell = getCell(sheet, 1, 1);
				setText(cell, "권한이 없습니다.  에러코드 : "+(String)nticData.get("resultCode"));
				return;
			}
		}

		Map<String, Object> master = (Map<String, Object>) nticData.get("master");
		List <EgovMap> detailList = (List<EgovMap>) nticData.get("detailList");

		String agentName = ((String) master.get("agentName")).replace(" ", "");
		String fileNticDt = (String) master.get("fileNticDt");
		String chrgeKndNm = ((String) master.get("chrgeKndNm")).replace(" ", "");
		
		String fileName = agentName + "_" + chrgeKndNm + "_" + fileNticDt + "_산출내역서.xls";
				
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1");
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		setSheet(sheet);
		
		setHeaderData(wb, sheet, master);
		
		setListHeaderTitle(wb, sheet);
		
		String paySe = (String) master.get("paySe");
		String quarter = (String) master.get("quarter");
		//BigDecimal intrRate = (BigDecimal) master.get("intrRate");
		String paySeNm = (String) master.get("paySeNm");
		
		int currentRow = 5;
		for(EgovMap detailItem : detailList) {
			String rntfeeSe = (String) detailItem.get("rntfeeSe");
			if("0".equals(rntfeeSe)) {
				String nticItem = (String) detailItem.get("boundNm");
				String nticPd = detailItem.get("nticBeginDt") + "~" + detailItem.get("nticEndDt");
				String rentArStr = ((String) detailItem.get("rentArStr")).trim();
				String priceSe = (String) detailItem.get("priceSe");
				String applcRntfeeStr = ((String) detailItem.get("applcRntfeeStr")).trim() + (("1".equals(priceSe)) ? "원" : "원/월");
				String rntfeeStr = ((String) detailItem.get("rntfeeStr")).trim() + "원";
				String calcRsn = "";
				if("1".equals(priceSe)) {
					calcRsn = "임대면적(" + rentArStr + ") * 적용단가("  + applcRntfeeStr + ") * 고지기간" ;
				} else {
					calcRsn = "월사용료(" + applcRntfeeStr + ") * 고지기간";
				}
				setListContentStyle(wb, sheet, currentRow);
				setContents(sheet, currentRow, nticItem, nticPd, rentArStr, applcRntfeeStr, paySeNm, rntfeeStr, calcRsn);
				if(("4".equals(paySe)) && (!"4".equals(quarter))) {
					LocalDate detailPdEnd = new LocalDate((String) detailItem.get("detailPdEnd"));
					LocalDate nticEndDt = new LocalDate((String)detailItem.get("nticEndDt"));
					if(detailPdEnd.getYear() != nticEndDt.getYear()) {
						detailPdEnd = new LocalDate(nticEndDt.getYear(), 12, 31);
					}
					if(nticEndDt.compareTo(detailPdEnd) < 0) {
						currentRow++;
						nticItem = "분납이자";
						//nticPd = nticEndDt.plusDays(1).toString() + "~" + detailPdEnd.toString();
						rntfeeStr = ((String) detailItem.get("payinstIntrStr")).trim() + "원";
						//calcRsn = "임대료(" + srcRntfeeStr + ") * COFIX이자율(" + intrRate + "%) * 고지기간 / 12";
						setListContentStyle(wb, sheet, currentRow);
						setContents(sheet, currentRow, nticItem, nticPd, "", "", paySeNm, rntfeeStr, "");					
					}
				}
			} else if("1".equals(rntfeeSe)) {
				String nticItem = (String) detailItem.get("rntfeeSeNm");
				String nticPd = detailItem.get("applcBeginDt") + "~" + detailItem.get("applcEndDt");
				String rentArStr = ((String) detailItem.get("rentArStr")).trim();
				String priceSe = (String) detailItem.get("priceSe");
				String applcRntfeeStr = ((String) detailItem.get("appRntfee")).trim() + (("1".equals(priceSe)) ? "원" : "원/월");
				String rntfeeStr = (String) detailItem.get("rntfeeStr") + "원";
				String calcRsn = "";
				if("1".equals(priceSe)) {
					calcRsn = "임대면적(" + rentArStr + ") * 실적평가변동분("  + applcRntfeeStr + ") * 고지기간" ;
				} else {
					calcRsn = "실적평가변동분(" + applcRntfeeStr + ") * 고지기간(월수)";
				}				
				setListContentStyle(wb, sheet, currentRow);
				setContents(sheet, currentRow, nticItem, nticPd, rentArStr, applcRntfeeStr, paySeNm, rntfeeStr, calcRsn);
			} else if("2".equals(rntfeeSe)) {
				String nticItem = (String) detailItem.get("rntfeeSeNm");
				String nticPd = detailItem.get("applcBeginDt") + "~" + detailItem.get("applcEndDt");
				String rentArStr = ((String) detailItem.get("applcRentArStr")).trim();
				String applcRntfeeStr = ((String) detailItem.get("appRntfee")).trim() + "원";
				String rntfeeStr = (String) detailItem.get("rntfeeStr") + "원";
				String calcRsn = "지적측정변동분(" + rentArStr + ") * 적용단가("  + applcRntfeeStr + ") * 고지기간" ;
				setListContentStyle(wb, sheet, currentRow);
				setContents(sheet, currentRow, nticItem, nticPd, rentArStr, applcRntfeeStr, paySeNm, rntfeeStr, calcRsn);
			} else {
				String nticItem = (String) detailItem.get("rntfeeSeNm");
				String nticPd = "";
				String rentArStr = "";
				String applcRntfeeStr = "";
				String rntfeeStr = (String) detailItem.get("rntfeeStr") + "원";
				String calcRsn = (String) detailItem.get("rm");
				setListContentStyle(wb, sheet, currentRow);
				setContents(sheet, currentRow, nticItem, nticPd, rentArStr, applcRntfeeStr, paySeNm, rntfeeStr, calcRsn);				
			}
			currentRow++;
		}
		
		setListContentStyle(wb, sheet, currentRow);
		setContents(sheet, currentRow, "", "", "", "", "", "", "");
		currentRow++;
		
		String supAmtStr = ((String) master.get("supAmtStr")).trim() + "원";
		setListContentStyle(wb, sheet, currentRow);
		setContents(sheet, currentRow, "공급가액", "", "", "", "", supAmtStr, "고지금액 합계");
		currentRow++;

		String vatStr = ((String) master.get("vatStr")).trim() + "원";
		setListContentStyle(wb, sheet, currentRow);
		setContents(sheet, currentRow, "부가세", "", "", "", "", vatStr, "공급가액(" + supAmtStr + ")의 10%");
		currentRow++;
		
		String arrrgNo = ((String) master.get("arrrgNo"));
		if(!"00".equals(arrrgNo)) {
			int arrrgNoNum = ((BigDecimal) master.get("arrrgNoNum")).intValue();
			String arrrgAmtStr = ((String) master.get("arrrgAmtStr")).trim() + "원";
			String calcRsn = "공급가액(" + supAmtStr + ") * 3%";
			if(arrrgNoNum > 1) {
				calcRsn += " + 증가산금(공급가액(" + supAmtStr + ") * 1.2% * " + (arrrgNoNum - 1) + "차)";
			}
			setListContentStyle(wb, sheet, currentRow);
			setContents(sheet, currentRow, "연체료", "", "", "", "", arrrgAmtStr, calcRsn);
			currentRow++;
		}
		String payAmtStr = ((String) master.get("payAmtStr")).trim() + "원";
		String nticDt = master.get("nticDt") + " 기준";
		setListContentStyle(wb, sheet, currentRow);
		setContents(sheet, currentRow, "고지금액", "", "", "", "", payAmtStr, nticDt);
	}
	
	protected void setHeaderData(HSSFWorkbook wb, HSSFSheet sheet, Map<String, Object> master) {
		CellStyle headerStyle = getHeaderStyle(wb);
		
		for(int i=1; i<=3; i++) {
			for(int j=1; j<=7; j++) {
				setCellStyle(sheet, i, j, headerStyle);
			}
		}
		
		String agentName = (master.get("agentName") != null) ? (String)master.get("agentName") : "";
		String rprsntvNm = (master.get("rprsntvNm") != null) ? (String)master.get("rprsntvNm") : "";
		String bizrno = (master.get("bizrno") != null) ? (String)master.get("bizrno") : "";
		
		setCellText(sheet, 1, 1, "업체명");
		setCellText(sheet, 1, 3, agentName);
		setCellText(sheet, 2, 1, "사업자등록번호");
		setCellText(sheet, 2, 3, bizrno);
		setCellText(sheet, 3, 1, "대표자명");
		setCellText(sheet, 3, 3, rprsntvNm);		
	}
	
	protected void setListHeaderTitle(HSSFWorkbook wb, HSSFSheet sheet) {
		CellStyle listHeaderStyle = getListHeaderStyle(wb);
		for(int i=1; i<=7; i++) {
			setCellStyle(sheet, 4, i, listHeaderStyle);
		}
		
		setCellText(sheet, 4, 1, "고지항목");
		setCellText(sheet, 4, 2, "고지기간");
		setCellText(sheet, 4, 3, "임대면적(㎡)");
		setCellText(sheet, 4, 4, "적용단가");
		setCellText(sheet, 4, 5, "납부구분");
		setCellText(sheet, 4, 6, "고지금액");
		setCellText(sheet, 4, 7, "산출공식(비고)");
	}
	
	protected void setContents(HSSFSheet sheet, int row, String nticItem, String nticPd, String rentArStr, String applcRntfeeStr, String paySeNm, String rntfeeStr, String calcRsn) {
		setCellText(sheet, row, 1, nticItem);
		setCellText(sheet, row, 2, nticPd);
		setCellText(sheet, row, 3, rentArStr);
		setCellText(sheet, row, 4, applcRntfeeStr);
		setCellText(sheet, row, 5, paySeNm);
		setCellText(sheet, row, 6, rntfeeStr);
		setCellText(sheet, row, 7, calcRsn);		
	}
	
	protected void setListContentStyle(HSSFWorkbook wb, HSSFSheet sheet, int currentRow) {
		CellStyle style = getListContentsStyle(wb, CellStyle.ALIGN_CENTER);
		setCellStyle(sheet, currentRow, 1, style);
		style = getListContentsStyle(wb, CellStyle.ALIGN_CENTER);
		setCellStyle(sheet, currentRow, 2, style);
		style = getListContentsStyle(wb, CellStyle.ALIGN_RIGHT);
		setCellStyle(sheet, currentRow, 3, style);
		style = getListContentsStyle(wb, CellStyle.ALIGN_RIGHT);
		setCellStyle(sheet, currentRow, 4, style);
		style = getListContentsStyle(wb, CellStyle.ALIGN_CENTER);
		setCellStyle(sheet, currentRow, 5, style);
		style = getListContentsStyle(wb, CellStyle.ALIGN_RIGHT);
		setCellStyle(sheet, currentRow, 6, style);
		style = getListContentsStyle(wb, CellStyle.ALIGN_LEFT);
		setCellStyle(sheet, currentRow, 7, style);		
	}
	
	protected void setSheet(HSSFSheet sheet) {
		sheet.setColumnWidth(1, 6000); 
		sheet.setColumnWidth(2, 6500);
		sheet.setColumnWidth(3, 5500);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 3000);
		sheet.setColumnWidth(6, 5000);
		sheet.setColumnWidth(7, 20000);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 7));
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 2));
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 3, 7));
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 1, 2));
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 3, 7));
	}
	
	protected void setCellText(HSSFSheet sheet, int row, int col, String str) {
		HSSFCell cell = getCell(sheet, row, col);
		setText(cell, str);
	}

	protected void setCellStyle(HSSFSheet sheet, int row, int col, CellStyle style) {
		HSSFCell cell = getCell(sheet, row, col);
		cell.setCellStyle(style);
	}

	protected CellStyle getHeaderStyle(HSSFWorkbook wb) {
	    Font font = wb.createFont();
	    font.setFontHeightInPoints((short)11);
	    font.setFontName("굴림");
	    font.setItalic(false);
	    font.setStrikeout(false);
	    font.setBoldweight((short) 1);
	    
	    CellStyle style = wb.createCellStyle();
	    style.setFont(font);
	    
	    style.setBorderBottom(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setWrapText(true);
	    
	    return style;
	}

	protected CellStyle getListHeaderStyle(HSSFWorkbook wb) {
	    Font font = wb.createFont();
	    font.setFontHeightInPoints((short)10);
	    font.setFontName("맑은 고딕");
	    font.setColor(IndexedColors.WHITE.index);
	    font.setItalic(false);
	    font.setStrikeout(false);
	    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    
	    CellStyle style = wb.createCellStyle();
	    style.setFont(font);
	    
	    style.setBorderBottom(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setWrapText(true);
	    
	    style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
	    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    
	    return style;
	}

	protected CellStyle getListContentsStyle(HSSFWorkbook wb, short alignment) {
	    Font font = wb.createFont();
	    font.setFontHeightInPoints((short)11);
	    font.setFontName("굴림");
	    font.setItalic(false);
	    font.setStrikeout(false);
	    font.setBoldweight((short) 1);
	    
	    CellStyle style = wb.createCellStyle();
	    style.setFont(font);
	    
	    style.setBorderBottom(CellStyle.BORDER_THIN);
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderTop(CellStyle.BORDER_THIN);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    style.setAlignment(alignment);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setWrapText(true);
	    
	    return style;
	}
			
}
