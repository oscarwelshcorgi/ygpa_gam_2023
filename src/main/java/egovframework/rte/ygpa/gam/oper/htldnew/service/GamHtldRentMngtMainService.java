/**
 *
 */
package egovframework.rte.ygpa.gam.oper.htldnew.service;

import java.util.List;

import egovframework.rte.ygpa.gam.code.service.GamCofixIntrrateVO;

/**
 *
 * @author Jongmin
 * @since 2016. 5. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2016. 5. 10.		Jongmin		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamHtldRentMngtMainService {

	/**
	 * 배후단지 임대 상세목록 조회
	 * @param
	 * @return List 임대상세목록
	 * @exception Exception
	 */
	List<?> selectHtldRentDetailList(GamHtldRentMngtMainVO vo) throws Exception;

	/**
	 *  Cofix 이자율 조회
	 * @param
	 * @return String 이자율
	 * @exception Exception
	 */
	String selectCofixIntrrate(GamHtldRentMngtMainVO vo) throws Exception;

	/**
	 * 임대료 저장
	 * @param feeInsertList
	 * @param feeUpdateList
	 * @param id
	 */
	void updateHtldRntfee(List<GamHtldRentRntfeeVO> feeInsertList, List<GamHtldRentRntfeeVO> feeUpdateList, String id) throws Exception;

	/**
	 * @param nowYear
	 * @param oldYear
	 */
	void insertCopyAllRentContract(GamHtldRentMngtMainVO searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectHtldQuGtqyList(GamHtldQuGtqyVO searchVO) throws Exception;

	/**
	 * @param createList
	 * @param updateList
	 * @param deleteList
	 */
	void updateHtldQuGtqyList(List<GamHtldQuGtqyVO> createList, List<GamHtldQuGtqyVO> updateList,	List<GamHtldQuGtqyVO> deleteList) throws Exception;

	/**
	 * @param updateList
	 * @param id
	 */
	void updateBizAssessList(List<GamPopupHtldBizAssessVO> updateList, String id) throws Exception;

	/**
	 * @param updateVo
	 * @param id
	 */
	void updateRntfeeBizAssess(GamHtldBizAssessVO updateVo, String id) throws Exception;

}
