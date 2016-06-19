/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticDefaultVO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticReportService;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 19.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 19.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Service("gamHtldRentNticReportService")
public class GamHtldRentNticReportServiceImpl extends AbstractServiceImpl implements GamHtldRentNticReportService {
	@Resource(name="gamHtldRentNticReportDao")
	GamHtldRentNticReportDao gamHtldRentNticReportDao;
	
	/**
	 * 고지서 출력 마스터 정보 조회
	 * @param GamHtldRentMngDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectNticPrintMaster(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return gamHtldRentNticReportDao.selectNticPrintMaster(searchVO);
	}

}
