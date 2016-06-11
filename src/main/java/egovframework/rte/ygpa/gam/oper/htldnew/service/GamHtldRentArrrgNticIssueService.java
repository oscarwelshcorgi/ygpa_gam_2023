/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 10.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamHtldRentArrrgNticIssueService {
	/**
	 * 고지 정보 조회
	 * @param GamHtldRentNticDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	Map<?, ?> selectNticIssueMasterl(GamHtldRentNticDefaultVO searchVO) throws Exception;
	
	/**
	 * 고지 상세 목록 조회
	 * @param GamHtldRentNticDefaultVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectNticIssueDetailListl(GamHtldRentNticDefaultVO searchVO) throws Exception;
	
	/**
	 * 연체고지 정보 조회
	 * @param GamHtldRentNticDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	Map<?, ?> selectNticArrrgDetaill(GamHtldRentNticDefaultVO searchVO) throws Exception;

	/**
	 * 연체고지 실행
	 * @param GamHtldRentArrrgNticInfoVO
	 * @return
	 * @exception Exception
	 */
	void execArrrgNticIssue(GamHtldRentArrrgNticInfoVO arrrgVO) throws Exception;
}
