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

public class FlexiGridExcelView extends AbstractExcelView {

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
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1");
		resp.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
		resp.setHeader("Content-Transfer-Encoding", "binary");

		HSSFSheet sheet = wb.createSheet("테이블 목록");
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

		for(i=0; i < headerList.size(); i++) {
			Map<String, String> head = (Map<String, String>) headerList.get(i);
			cell = getCell(sheet, 1, i);
			setText(cell, head.get("title"));
		}

		for (i = 0; i < resultList.size(); i++) {
			Map<String, String> td = (Map<String, String>) resultList.get(i);

			for(j=0; j < headerList.size(); j++) {
				Map<String, String> head = (Map<String, String>) headerList.get(j);
				Object obj= td.get(head.get("abbr"));
				cell = getCell(sheet, 2+i, j);
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
