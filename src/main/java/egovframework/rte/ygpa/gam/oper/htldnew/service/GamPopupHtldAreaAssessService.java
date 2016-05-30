/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import java.util.Map;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 30.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 30.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamPopupHtldAreaAssessService {
	/**
	 * 면적평가 조회
	 * @param GamPopupHtldBizAssessVO
	 * @return Map 면적평가정산
	 * @exception Exception
	 */
	Map<?, ?> selectHtldAreaAssessDetail(GamPopupHtldAreaAssessVO searchVO) throws Exception;

	/**
	 * 면적평가 등록
	 * @param GamPopupHtldAreaAssessVO
	 * @return 
	 * @exception Exception
	 */
	void insertAreaAssess(GamPopupHtldAreaAssessVO vo) throws Exception;
	
	/**
	 * 면적평가 수정
	 * @param GamPopupHtldAreaAssessVO
	 * @return 
	 * @exception Exception
	 */
	void updateAreaAssess(GamPopupHtldAreaAssessVO vo) throws Exception;
	
	/**
	 * 면적평가 삭제
	 * @param GamPopupHtldAreaAssessVO
	 * @return 
	 * @exception Exception
	 */
	void deleteAreaAssess(GamPopupHtldAreaAssessVO vo) throws Exception;

}
