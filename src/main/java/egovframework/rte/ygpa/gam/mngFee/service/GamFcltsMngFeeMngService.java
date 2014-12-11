/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Lee
 * @since 2014. 10. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 24.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltsMngFeeMngService {

	/**
	 * @param searchVO
	 * @return
	 */
	GamFcltsMngFeeMngVo selectFcltsMngFeeMngListTotCnt(GamFcltsMngFeeMngVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectFcltsMngFeeMngList(GamFcltsMngFeeMngVo searchVO) throws Exception;

	/**
	 * @param gamFcltsMngFeeMngVo
	 * @return
	 */
	List selectFcltsMngFeeMngMonthCntList(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception;

	/**
	 * @param gamFcltsMngFeeMngVo
	 */
	void insertFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception;

	/**
	 * @param gamFcltsMngFeeMngVo
	 */
	void updateFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception;

	/**
	 * @param gamFcltsMngFeeMngVo
	 */
	void deleteFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectFcltsMngFeeMngDetailList(GamFcltsMngFeeMngDetailVo searchVO) throws Exception;

	/**
	 * @param gamFcltsMngFeeMngDetailVo
	 * @return
	 */
	String selectFcltsMngFeeMngDetailMaxMngSeq(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception;

	/**
	 * @param gamFcltsMngFeeMngDetailVo
	 */
	void insertFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception;

	/**
	 * @param gamFcltsMngFeeMngDetailVo
	 */
	void updateFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception;

	/**
	 * @param gamFcltsMngFeeMngDetailVo
	 */
	void deleteFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectFcltsMngFeeMngPrintNoticeIssueList(Map searchVO) throws Exception;

	/**
	 * @param vo
	 */
	void processFcltsMngFeeMngNticIssue(Map<String, Object> vo) throws Exception;

	/**
	 * @param vo
	 */
	void cancelFcltsMngFeeMngNticIssue(Map<String, Object> vo) throws Exception;

	/**
	 * @param vo
	 */
	void updateFcltsMngFeeMngNticIssuePrintYn(Map<String, Object> vo) throws Exception;

	/**
	 * @param vo
	 */
	void copyFcltsMngFeeMng(Map<String, Object> vo) throws Exception;

	/**
	 * @param vo
	 * @return
	 */
	List selectFcltsMngFeeMngChartList(Map<String, Object> vo) throws Exception;

}
