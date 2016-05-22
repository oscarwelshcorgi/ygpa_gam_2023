/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import java.util.Map;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 15.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamPopupHtldBizAssessService {
	/**
	 * 배후단지 임대계약 조회
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지 임대계약
	 * @exception Exception
	 */
	Map<?, ?> selectHtldRentBizAssessDetail(GamPopupHtldBizAssessVO searchVO) throws Exception;

	/**
	 * 실적평가 등록 및 수정
	 * @param GamPopupHtldBizAssessVO
	 * @return 
	 * @exception Exception
	 */
	void updateBizAssess(GamPopupHtldBizAssessVO vo, String updUsr) throws Exception;
}
