/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Jongmin
 * @since 2016. 6. 12.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 6. 12.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamHtldRentNticHistService {
	/**
	 * 업체정보 조회
	 * @param GamHtldRentNticHistVO
	 * @return List
	 * @exception Exception
	 */
	Map<?, ?> selectEntrpsInfo(GamHtldRentNticHistVO searchVO) throws Exception;
	
	/**
	 * 업체별 고지 이력 목록 조회
	 * @param GamHtldRentNticHistVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectHtldRentNticHistList(GamHtldRentNticHistVO searchVO) throws Exception;

	/**
	 * 고지 상세 조회
	 * @param GamHtldRentNticHistVO
	 * @return List
	 * @exception Exception 
	 */
	List<?> selectHistNticIssueList(GamHtldRentNticHistVO searchVO) throws Exception;	
	
	/**
	 * 연체고지 상세 조회
	 * @param GamHtldRentNticHistVO
	 * @return List
	 * @exception Exception 
	 */
	List<?> selectHistArrrgNticIssueList(GamHtldRentNticHistVO searchVO) throws Exception;

	/**
	 * 해당고지의 연체고지 자료수 조회
	 * @param GamHtldRentNticHistVO
	 * @return int
	 * @exception Exception
	 */
	int selectHistArrrgNticIssueListCnt(GamHtldRentNticHistVO searchVO) throws Exception;
	
	/**
     * 배후단지 원고지 취소 
     * @param GamHtldRentNticHistVO
     * @return
     * @throws Exception
     */
	void cancelSourceNticIssue(GamHtldRentNticHistVO searchVO) throws Exception;
	
	/**
     * 배후단지 연체고지 취소 
     * @param GamHtldRentNticHistVO
     * @return
     * @throws Exception
     */
	void cancelArrrgNticIssue(GamHtldRentNticHistVO searchVO) throws Exception;
	
	/**
	 * 지로 수납된 자료인지 조회
	 * @param GamHtldRentNticDefaultVO
	 * @return String 'Y, N'
	 * @exception Exception
	 */
	String selectCheckOcrResult(GamHtldRentNticDefaultVO searchVO) throws Exception;	
}
