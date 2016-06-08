/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.LoginVO;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 3.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 3.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamHtldRentNticIssueService {
	/**
	 * 고지 정보 조회
	 * @param GamHtldRentMngDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	Map<?, ?> selectNticIssueMasterl(GamHtldRentMngDefaultVO searchVO) throws Exception;
	
	/**
	 * 고지 상세 목록 조회
	 * @param GamHtldRentMngDefaultVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectNticIssueDetailListl(GamHtldRentMngDefaultVO searchVO) throws Exception;

	/** 고지 실행
	 * @param nticInfo
	 * @param rntfeeList
	 * @param loginVO
	 */
	void execNticIssue(GamHtldRentNticInfoVO nticInfo, List<GamHtldRentRntfeeVO> rntfeeList, LoginVO loginVO) throws Exception;
}
