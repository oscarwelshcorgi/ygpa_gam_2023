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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 *
 * @author EUNSUNGJ
 * @since 2014. 9. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 9. 24.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class FlexiGridHtldFeeExcelView extends AbstractExcelView {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.document.AbstractExcelView#buildExcelDocument(java.util.Map, org.apache.poi.hssf.usermodel.HSSFWorkbook, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	    Font titleFont = workbook.createFont();
	    titleFont.setFontHeightInPoints((short)20);
	    titleFont.setFontName("맑은 고딕");
	    titleFont.setItalic(false);
	    titleFont.setStrikeout(false);
	    titleFont.setBoldweight((short) 1);
	    Font headerFont = workbook.createFont();
	    headerFont.setFontHeightInPoints((short)11);
	    headerFont.setFontName("굴림");
	    headerFont.setItalic(false);
	    headerFont.setStrikeout(false);
	    headerFont.setBoldweight((short) 1);
	    Font font = workbook.createFont();
	    font.setFontHeightInPoints((short)12);
	    font.setFontName("굴림");
	    font.setItalic(false);
	    font.setStrikeout(false);
	    font.setBoldweight((short) 0);

	    DataFormat format = workbook.createDataFormat();
	    DataFormat areaFormat = workbook.createDataFormat();

	    CellStyle titleStyle = workbook.createCellStyle();
	    titleStyle.setFont(titleFont);
	    CellStyle headerStyle = workbook.createCellStyle();
	    headerStyle.setFont(headerFont);
	    CellStyle style = workbook.createCellStyle();
	    style.setFont(font);
	    CellStyle currencyStyle = workbook.createCellStyle();
	    currencyStyle.setFont(font);
	    CellStyle areaStyle = workbook.createCellStyle();
	    areaStyle.setFont(font);

//	    titleStyle.setBorderBottom(CellStyle.BORDER_THIN);
//	    titleStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
//	    titleStyle.setBorderLeft(CellStyle.BORDER_THIN);
//	    titleStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
//	    titleStyle.setBorderRight(CellStyle.BORDER_THIN);
//	    titleStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
//	    titleStyle.setBorderTop(CellStyle.BORDER_THIN);
//	    titleStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

//	    titleStyle.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
	    titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
	    titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

//	    titleStyle.setFillPattern(CellStyle.);
//	    titleStyle.setFillPattern(CellStyle.BIG_SPOTS);

	    headerStyle.setBorderBottom(CellStyle.BORDER_THIN);
	    headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    headerStyle.setBorderLeft(CellStyle.BORDER_THIN);
	    headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    headerStyle.setBorderRight(CellStyle.BORDER_THIN);
	    headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    headerStyle.setBorderTop(CellStyle.BORDER_THIN);
	    headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
	    headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    headerStyle.setWrapText(true);

	    headerStyle.setFillBackgroundColor(IndexedColors.SKY_BLUE.getIndex());
//	    headerStyle.setFillBackgroundColor(IndexedColors.getIndex());
//	    headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

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

	    currencyStyle.setBorderBottom(CellStyle.BORDER_THIN);
	    currencyStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    currencyStyle.setBorderLeft(CellStyle.BORDER_THIN);
	    currencyStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    currencyStyle.setBorderRight(CellStyle.BORDER_THIN);
	    currencyStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    currencyStyle.setBorderTop(CellStyle.BORDER_THIN);
	    currencyStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    currencyStyle.setDataFormat(format.getFormat("#,##0"));
	    currencyStyle.setAlignment(CellStyle.ALIGN_RIGHT);
	    currencyStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

	    areaStyle.setBorderBottom(CellStyle.BORDER_THIN);
	    areaStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    areaStyle.setBorderLeft(CellStyle.BORDER_THIN);
	    areaStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    areaStyle.setBorderRight(CellStyle.BORDER_THIN);
	    areaStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    areaStyle.setBorderTop(CellStyle.BORDER_THIN);
	    areaStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    areaStyle.setDataFormat(format.getFormat("#,##0.00"));
	    areaStyle.setAlignment(CellStyle.ALIGN_RIGHT);
	    areaStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

//	    style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
//	    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//	    style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
//	    style.setFillPattern(CellStyle.BIG_SPOTS);

//		int i, j;
		HSSFCell cell = null;

	    HSSFPalette palette = workbook.getCustomPalette();

		String fileName = "임대료 고지.xls";
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1");
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");

		HSSFSheet sheet = workbook.createSheet("임대료 납부금액목록");
		sheet.setDefaultColumnWidth((short) 12);

		// put text in first cell
//		cell = getCell(sheet, 0, 0);
//		setText(cell, "조회 결과 ");
		Map<String, Object> map= (Map<String, Object>) model.get("gridResultMap");

		if(map.containsKey("resultCode")) {
			if((Integer)map.get("resultCode")!=0) {
				cell = getCell(sheet, 1, 1);
				setText(cell, "권한이 없습니다.  에러코드 : "+(String)map.get("resultCode"));
				return;
			}
		}
		List<Map> headerList= (List<Map>) map.get("header");
		List<Object> resultList = (List<Object>) map.get("resultList");

		boolean isVO = false;

		if (resultList.size() > 0) {
			Object obj = resultList.get(0);
		}

		cell = getCell(sheet, 0, 0);
		cell.setCellStyle(titleStyle);
		setText(cell, "광양항 배후단지 입주기업 임대료 납부금액");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));
		sheet.getRow(0).setHeightInPoints((float) 31.5);

		cell = getCell(sheet, 1, 0);
		cell.setCellStyle(titleStyle);
		setText(cell, "");	// insert empty cell

		sheet.getRow(1).setHeightInPoints((float) 7.5);

		// 헤더 입력
		/*
		for(i=0; i < headerList.size(); i++) {
			Map<String, String> head = (Map<String, String>) headerList.get(i);
			cell = getCell(sheet, 1, i);
			cell.setCellStyle(headerStyle);
			setText(cell, head.get("title"));
		}
		*/
		sheet.setColumnWidth(0, 2267);
		sheet.setColumnWidth(1, 4388);
		sheet.setColumnWidth(2, 3547);
		sheet.setColumnWidth(3, 3181);
		sheet.setColumnWidth(4, 2194);
		sheet.setColumnWidth(5, 3876);
		sheet.setColumnWidth(6, 3876);
		sheet.setColumnWidth(7, 3876);
		sheet.setColumnWidth(8, 3876);
		sheet.setColumnWidth(9, 3876);

		cell = getCell(sheet, 2, 0);
		cell.setCellStyle(headerStyle);
		setText(cell, "구  역");

		cell = getCell(sheet, 2, 1);
		cell.setCellStyle(headerStyle);
		setText(cell, "입주기업");

		cell = getCell(sheet, 2, 2);
		cell.setCellStyle(headerStyle);
		setText(cell, "임대면적\n(㎡)");

		cell = getCell(sheet, 2, 3);
		cell.setCellStyle(headerStyle);
		setText(cell, "적용\n임대료\n(월,㎡)");

		cell = getCell(sheet, 2, 4);
		cell.setCellStyle(headerStyle);
		setText(cell, "납부\n시기");

		cell = getCell(sheet, 2, 5);
		cell.setCellStyle(headerStyle);
		setText(cell, "임대료");

		cell = getCell(sheet, 2, 6);
		cell.setCellStyle(headerStyle);
		setText(cell, "분납이자");

		cell = getCell(sheet, 2, 7);
		cell.setCellStyle(headerStyle);
		setText(cell, "공급가액");

		cell = getCell(sheet, 2, 8);
		cell.setCellStyle(headerStyle);
		setText(cell, "부가세");

		cell = getCell(sheet, 2, 9);
		cell.setCellStyle(headerStyle);
		setText(cell, "납부금액");

		sheet.getRow(2).setHeightInPoints((float) 40.5);

		int lastAreaNo=0;
		int lastEntrpsNmNo=0;
		String lastAreaNm="";
		String lastEntrpsNm="";
		String areaNm="";
		String entrpsNm;

		// 목록 입력
		for (int i = 0; i < resultList.size(); i++) {
			Map<String, Object> td = (Map<String, Object>) resultList.get(i);

			cell = getCell(sheet, 3+i, 0);
			cell.setCellStyle(style);
			areaNm = (String)td.get("gisAssetsNm");
			setText(cell, areaNm);

			if(lastAreaNm.equals(areaNm)) {
				sheet.addMergedRegion(new CellRangeAddress(lastAreaNo, i+2, 0, 0));
			}
			else {
				lastAreaNo=i+3;
				lastAreaNm=areaNm;
			}

			cell = getCell(sheet, 3+i, 1);
			cell.setCellStyle(style);
			entrpsNm = (String)td.get("entrpsNm");
			setText(cell, entrpsNm);

			if(lastEntrpsNm.equals(entrpsNm)) {
				sheet.addMergedRegion(new CellRangeAddress(lastEntrpsNmNo, i+2, 1, 1));
			}
			else {
				lastEntrpsNmNo=i+3;
				lastEntrpsNm=entrpsNm;
			}

			BigDecimal bd = (BigDecimal)td.get("grAr");
			double d = bd.doubleValue();

			cell = getCell(sheet, 3+i, 2);
			cell.setCellStyle(areaStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(d);

			bd = (BigDecimal)td.get("applcPrice");
			d = bd.doubleValue();

			cell = getCell(sheet, 3+i, 3);
			cell.setCellStyle(currencyStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(d);

			cell = getCell(sheet, 3+i, 4);
			cell.setCellStyle(style);
			setText(cell, (String)td.get("nticCnt")+"분기");

			bd = (BigDecimal)td.get("fee");
			d = bd.doubleValue();


			cell = getCell(sheet, 3+i, 5);
			cell.setCellStyle(currencyStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(d);

			bd = (BigDecimal)td.get("interest");
			d = bd.doubleValue();

			cell = getCell(sheet, 3+i, 6);
			cell.setCellStyle(currencyStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(d);

			bd = (BigDecimal)td.get("feeAmount");
			d = bd.doubleValue();

			cell = getCell(sheet, 3+i, 7);
			cell.setCellStyle(currencyStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(d);

			bd = (BigDecimal)td.get("vat");
			d = bd.doubleValue();

			cell = getCell(sheet, 3+i, 8);
			cell.setCellStyle(currencyStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(d);

			bd = (BigDecimal)td.get("nticAmt");
			d = bd.doubleValue();

			cell = getCell(sheet, 3+i, 9);
			cell.setCellStyle(currencyStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(d);

			sheet.getRow(i+3).setHeightInPoints((float) 30);

//
//			for(j=0; j < headerList.size(); j++) {
//				Map<String, String> head = (Map<String, String>) headerList.get(j);
//				Object obj= td.get(head.get("abbr"));
//				cell = getCell(sheet, 2+i, j);
//				cell.setCellStyle(style);
//				if(obj instanceof BigDecimal) {
//					BigDecimal d = (BigDecimal) obj;
//					setText(cell, d.toString());
//				}
//				else
//					setText(cell, (String) obj);
//			}
		}
	}

}
