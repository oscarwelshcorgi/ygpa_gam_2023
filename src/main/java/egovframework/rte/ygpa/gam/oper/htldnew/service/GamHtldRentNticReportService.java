/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import java.util.List;
import java.util.Map;

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

public interface GamHtldRentNticReportService {
	/**
	 * 고지서 출력 마스터 정보 조회
	 * @param GamHtldRentMngDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	Map<?, ?> selectNticPrintMaster(GamHtldRentNticDefaultVO searchVO) throws Exception;

	/**
	 * 연체 고지서 출력 마스터 정보 조회
	 * @param GamHtldRentMngDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	Map<?, ?> selectArrrgNticPrintMaster(GamHtldRentNticDefaultVO searchVO) throws Exception;

	/**
	 * 출력용 고지 상세 리스트 조회
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List<?> selectNticIssueList(GamHtldRentNticDefaultVO searchVO) throws Exception;

	/**
	 * 고지일자와 시스템 일자와 일수 차이
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	int selectNticDaysDiff(GamHtldRentNticInfoVO nticInfoVO) throws Exception;

	/**
	 * 예약 출력 상태 변경
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	void updateReservePrintState(GamHtldRentNticInfoVO nticInfoVO) throws Exception;

	/**
	 * 즉시 출력 상태 변경
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	void updateImmediatelyPrintState(GamHtldRentNticInfoVO nticInfoVO) throws Exception;
	
	/**
	 * 연체고지 출력 상태 변경
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	void updateArrrgPrintState(GamHtldRentNticInfoVO nticInfoVO) throws Exception;

	/**
	 * 원고지 마스터 (산출내역서 엑셀용)
	 * @param GamHtldRentMngDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	Map<?, ?> selectNticXlsMaster(GamHtldRentNticDefaultVO searchVO) throws Exception;

	/**
	 * 연체고지 마스터 (산출내역서 엑셀용)
	 * @param GamHtldRentMngDefaultVO
	 * @return Map 
	 * @exception Exception
	 */
	Map<?, ?> selectArrrgNticXlsMaster(GamHtldRentNticDefaultVO searchVO) throws Exception;

	/**
	 * 고지 상세 리스트 조회 (산출내역서 엑셀용)
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List<?> selectNticIssueXlsList(GamHtldRentNticDefaultVO searchVO) throws Exception;
	
}
