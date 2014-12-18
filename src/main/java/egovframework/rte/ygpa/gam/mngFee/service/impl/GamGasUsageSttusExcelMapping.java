/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import egovframework.rte.fdl.excel.EgovExcelMapping;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;
import egovframework.rte.ygpa.gam.mngFee.service.GamGasUsageSttusExcelVo;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 18.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 18.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public class GamGasUsageSttusExcelMapping extends EgovExcelMapping {

	@SuppressWarnings("deprecation")
	@Override
	public Object mappingColumn(HSSFRow row) {

		HSSFCell cell0 = row.getCell((int) 0);
		HSSFCell cell1 = row.getCell((int) 1);
		HSSFCell cell2 = row.getCell((int) 2);
		GamGasUsageSttusExcelVo vo = new GamGasUsageSttusExcelVo();
		vo.setUsageMt(EgovExcelUtil.getValue(cell0));
		vo.setSaidMtUsageQy(EgovExcelUtil.getValue(cell1));
		vo.setApplcCoef(EgovExcelUtil.getValue(cell2));
		return vo;

	}

}
