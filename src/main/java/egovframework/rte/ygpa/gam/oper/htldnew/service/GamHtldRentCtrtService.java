/**
 * 
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Jongmin
 * @since 2016. 5. 2.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 2.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamHtldRentCtrtService {
	
	/**
	 * 배후단지 임대계약 조회
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지 임대계약
	 * @exception Exception
	 */
	Map<?, ?> selectHtldRentCtrt(GamHtldRentCtrtVO searchVO) throws Exception;
	
	/**
	 * 배후단지 임대계약 상세목록 조회
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지 임대계약 상세목록
	 * @exception Exception
	 */
	List<?> selectHtldRentCtrtDetailList(GamHtldRentCtrtVO searchVO) throws Exception;
	
	/**
	 * 배후단지 임대계약 등록
	 * @param insertRentData - 등록할 임대계약, insertDetailList - 등록할 임대계약 상세목록, regUsr - 등록자
	 * @return
	 * @exception Exception
	 */	
	void insertHtldRentCtrt(GamHtldRentCtrtVO insertRentData, List<GamHtldRentCtrtDetailVO> insertDetailList, String regUsr) throws Exception;
	
	/**
	 * 배후단지 임대계약 수정
	 * @param updateRentData - 수정할 임대계약, insertDetailList - 등록할 임대계약 상세목록, updateDetailList - 수정할 임대계약 상세목록, deleteDetailList - 삭제할 임대계약 상세목록, updtUsr - 수정자	 * @return
	 * @exception Exception
	 */	
	void updateHtldRentCtrt(GamHtldRentCtrtVO updateRentData, List<GamHtldRentCtrtDetailVO> insertDetailList, List<GamHtldRentCtrtDetailVO> updateDetailList, List<GamHtldRentCtrtDetailVO> deleteDetailList, String updUsr) throws Exception;
	
	/**
	 * 배후단지 임대계약 해지
	 * @param terminateRentData - 해지할 임대계약, updUsr - 해지자 
	 * @return
	 * @exception Exception
	 */	
	void terminateHtldRentCtrt(GamHtldRentCtrtVO terminateRentData, String updUsr) throws Exception;
}
