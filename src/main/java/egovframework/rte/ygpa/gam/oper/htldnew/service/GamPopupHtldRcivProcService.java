/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import java.util.Map;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 15.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 15.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamPopupHtldRcivProcService {
	/**
	 * 수납처리정보 조회
	 * @param GamHtldRentNticDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	Map<?, ?> selectHtldNticDtlsRcivInfo(GamHtldRentNticDefaultVO searchVO) throws Exception;
	
	/**
	 * 수납정보 수정
	 * @param GamPopupHtldRcivProcVO
	 * @return 
	 * @exception Exception
	 */
	void updateHtldRcivInfo(GamPopupHtldRcivProcVO vo) throws Exception;

}
