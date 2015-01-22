/**
 *
 */
package egovframework.rte.cmmn.excel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
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
 * @since 2014. 4. 16.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 4. 16.		EUNSUNGJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class FlexiGridXlsView extends AbstractExcelView {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.document.AbstractExcelView#buildExcelDocument(java.util.Map, org.apache.poi.hssf.usermodel.HSSFWorkbook, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook wb, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		int i, j;
		HSSFCell cell = null;

		String fileName = "GIS.xls";

		HSSFSheet sheet = wb.createSheet("테이블 목록");
		sheet.setDefaultColumnWidth((short) 12);

		// put text in first cell
//		cell = getCell(sheet, 0, 0);
//		setText(cell, "조회 결과 ");
		Map<String, Object> map= (Map<String, Object>) model.get("gridResultMap");

		if(map.containsKey("fileName")) fileName=(String)map.get("fileName");
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1");

		resp.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
		resp.setHeader("Content-Transfer-Encoding", "binary");

		if(map.containsKey("resultCode")) {
			if((Integer)map.get("resultCode")!=0) {
				cell = getCell(sheet, 1, 1);
				setText(cell, "권한이 없습니다.  에러코드 : "+(String)map.get("resultCode"));
				return;
			}
		}
		String title = (String)map.get("title");
		List<Map> headerList= (List<Map>) map.get("header");
		List<Object> resultList = (List<Object>) map.get("resultList");

		boolean isVO = false;

		if (resultList.size() > 0) {
			Object obj = resultList.get(0);
		}

	    Font titleFont = wb.createFont();
	    titleFont.setFontHeightInPoints((short)12);
	    titleFont.setFontName("맑은 고딕");
	    titleFont.setItalic(false);
	    titleFont.setStrikeout(false);
	    titleFont.setBoldweight((short) 1);

	    Font headerFont = wb.createFont();
	    headerFont.setFontHeightInPoints((short)10);
	    headerFont.setFontName("굴림");
	    headerFont.setItalic(false);
	    headerFont.setStrikeout(false);
	    headerFont.setBoldweight((short) 1);

	    Font font = wb.createFont();
	    font.setFontHeightInPoints((short)10);
	    font.setFontName("굴림");
	    font.setItalic(false);
	    font.setStrikeout(false);
	    font.setBoldweight((short) 0);

	    CellStyle titleStyle = wb.createCellStyle();
	    titleStyle.setFont(titleFont);
	    titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
	    titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

	    CellStyle headerStyle = wb.createCellStyle();
	    headerStyle.setFont(headerFont);
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
	    style.setAlignment(CellStyle.ALIGN_LEFT);
	    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    style.setWrapText(true);

	    CellStyle rstyle = wb.createCellStyle();
	    rstyle.setFont(font);
	    rstyle.setBorderBottom(CellStyle.BORDER_THIN);
	    rstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    rstyle.setBorderLeft(CellStyle.BORDER_THIN);
	    rstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    rstyle.setBorderRight(CellStyle.BORDER_THIN);
	    rstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    rstyle.setBorderTop(CellStyle.BORDER_THIN);
	    rstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    rstyle.setAlignment(CellStyle.ALIGN_RIGHT);
	    rstyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    rstyle.setWrapText(true);

	    CellStyle cstyle = wb.createCellStyle();
	    cstyle.setFont(font);
	    cstyle.setBorderBottom(CellStyle.BORDER_THIN);
	    cstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    cstyle.setBorderLeft(CellStyle.BORDER_THIN);
	    cstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    cstyle.setBorderRight(CellStyle.BORDER_THIN);
	    cstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    cstyle.setBorderTop(CellStyle.BORDER_THIN);
	    cstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    cstyle.setAlignment(CellStyle.ALIGN_CENTER);
	    cstyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	    cstyle.setWrapText(true);

	    DataFormat format = wb.createDataFormat();

	    CellStyle numberStyle = wb.createCellStyle();
	    numberStyle.setFont(font);
	    numberStyle.setBorderBottom(CellStyle.BORDER_THIN);
	    numberStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    numberStyle.setBorderLeft(CellStyle.BORDER_THIN);
	    numberStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    numberStyle.setBorderRight(CellStyle.BORDER_THIN);
	    numberStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    numberStyle.setBorderTop(CellStyle.BORDER_THIN);
	    numberStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    numberStyle.setDataFormat(format.getFormat("#,##0"));
	    numberStyle.setAlignment(CellStyle.ALIGN_RIGHT);
	    numberStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		cell = getCell(sheet, 0, 0);
		cell.setCellStyle(titleStyle);
		setText(cell, title);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headerList.size()-1));
		sheet.getRow(0).setHeightInPoints((float) 31.5);
		List<CellStyle> styleList = new ArrayList<CellStyle>();

		for(i=0; i < headerList.size(); i++) {
			Map<String, String> head = (Map<String, String>) headerList.get(i);
			int width = Integer.parseInt((String)head.get("width"));

			sheet.setColumnWidth(i, (width/6)*256);
			cell = getCell(sheet, 1, i);
			cell.setCellStyle(headerStyle);

			if("number".equals(head.get("format"))) {
				styleList.add(numberStyle);
			}
			else {
				if("right".equals(head.get("align"))) {
					styleList.add(rstyle);
				}
				else if("center".equals(head.get("align"))) {
					styleList.add(cstyle);
				}
				else {
					styleList.add(style);
				}
			}

			setText(cell, head.get("title"));
		}

		for (i = 0; i < resultList.size(); i++) {
			Map<String, String> td = (Map<String, String>) resultList.get(i);

			for(j=0; j < headerList.size(); j++) {
				Map<String, String> head = (Map<String, String>) headerList.get(j);
				Object obj= td.get(head.get("abbr"));
				cell = getCell(sheet, 2+i, j);
				cell.setCellStyle(styleList.get(j));

				if(obj instanceof BigDecimal) {
					BigDecimal d = (BigDecimal) obj;
					setText(cell, d.toString());
				}
				else
					setText(cell, (String) obj);
			}
		}
	}

}
