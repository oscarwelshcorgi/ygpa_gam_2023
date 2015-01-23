package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import egovframework.com.sym.ccm.zip.service.Zip;
import egovframework.rte.fdl.excel.EgovExcelMapping;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.code.service.GamBupjungdongOlnlpVO;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeMngtService;
import egovframework.rte.ygpa.gam.code.service.GamOlnlpFVO;

/**
 *
 * Excel 공시지가 매핑 클래스
 * @author eunsungj
 * @since 2014.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2014.04.10  eunsungj          최초 생성
 *
 * </pre>
 */
public class GamOlnlpExcelMapping extends EgovExcelMapping {

	/**
	 * 공시지가 엑셀파일 맵핑
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Object mappingColumn(HSSFRow row) {
		HSSFCell cell0 = row.getCell((int) 0);
    	HSSFCell cell1 = row.getCell((int) 1);
    	HSSFCell cell2 = row.getCell((int) 2);
    	HSSFCell cell3 = row.getCell((int) 3);
    	HSSFCell cell4 = row.getCell((int) 4);
    	HSSFCell cell5 = row.getCell((int) 5);
    	HSSFCell cell6 = row.getCell((int) 6);

    	GamBupjungdongOlnlpVO vo = new GamBupjungdongOlnlpVO();


    	vo.setOlnlpApplcYear(EgovExcelUtil.getValue(cell0));
    	vo.setBupjungdongCd(EgovExcelUtil.getValue(cell1));	// 법정동 코드
    	vo.setMt(EgovExcelUtil.getValue(cell2)=="Y"?"Y":"N");	// 산
		vo.setLnm(EgovExcelUtil.getValue(cell3));	// 지번

		vo.setBeginDt(EgovExcelUtil.getValue(cell4));	// 적용시작일자
		vo.setEndDt(EgovExcelUtil.getValue(cell5));	// 적용종료일자
		try {
			vo.setOlnlp(Integer.parseInt(EgovExcelUtil.getValue(cell6)));		// 공시지가
		}
		catch(NumberFormatException e) {
			vo.setOlnlp(0);
		}

		return vo;
	}
}
