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

public interface GamPopupHtldAddNticService {
	/**
	 * 추가정산 계약정보
	 * @param GamPopupHtldAddNticVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectHtldAddNticCtrtDetail(GamPopupHtldAddNticVO searchVO) throws Exception;

	/**
	 * 추가정산 조회
	 * @param GamPopupHtldAddNticVO
	 * @return Map 
	 * @exception Exception
	 */
	public Map<?, ?> selectHtldAddNticDetail(GamPopupHtldAddNticVO searchVO) throws Exception;

	/**
	 * 추가정산 등록
	 * @param GamPopupHtldAddNticVO
	 * @return 
	 * @exception Exception
	 */
	void insertAddNtic(GamPopupHtldAddNticVO vo) throws Exception;
	
	/**
	 * 추가정산 수정
	 * @param GamPopupHtldAddNticVO
	 * @return 
	 * @exception Exception
	 */
	void updateAddNtic(GamPopupHtldAddNticVO vo) throws Exception;
	
	/**
	 * 추가정산 삭제
	 * @param GamPopupHtldAddNticVO
	 * @return 
	 * @exception Exception
	 */
	void deleteAddNtic(GamPopupHtldAddNticVO vo) throws Exception;

}
