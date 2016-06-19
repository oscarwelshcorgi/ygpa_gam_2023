/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htldnew.service.GamHtldRentNticDefaultVO;

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
@Repository("gamHtldRentNticReportDao")
public class GamHtldRentNticReportDao extends YGPAAbstractDAO {
	/**
	 * 고지서 출력 마스터 정보 조회
	 * @param GamHtldRentMngDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectNticPrintMaster(GamHtldRentNticDefaultVO searchVO) throws Exception {
		return (Map<?, ?>) selectByPk("gamHtldRentNticReportDao.selectNticPrintMaster_S", searchVO);
	}
	
	
}
