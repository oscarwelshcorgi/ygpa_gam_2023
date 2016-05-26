/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import java.util.Map;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 26.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 26.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamPopupHtldRntfeeBizAssessService {
	/**
	 * 실적평가정산 조회
	 * @param GamPopupHtldBizAssessVO
	 * @return Map 실적평가정산
	 * @exception Exception
	 */
	Map<?, ?> selectHtldRntfeeBizAssessDetail(GamPopupHtldRntfeeBizAssessVO searchVO) throws Exception;

	/**
	 * 실적평가정산 수정
	 * @param GamPopupHtldBizAssessVO
	 * @return 
	 * @exception Exception
	 */
	void updateRntfeeBizAssess(GamPopupHtldRntfeeBizAssessVO vo) throws Exception;
	
	/**
	 * 실적평가정산 삭제
	 * @param GamPopupHtldBizAssessVO
	 * @return 
	 * @exception Exception
	 */
	void deleteRntfeeBizAssess(GamPopupHtldRntfeeBizAssessVO vo) throws Exception;
}
