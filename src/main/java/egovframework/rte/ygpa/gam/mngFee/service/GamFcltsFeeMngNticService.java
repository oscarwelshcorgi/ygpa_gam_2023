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

public interface GamFcltsFeeMngNticService {

	/**
	 * @param searchVO
	 * @return
	 */
	GamFcltsFeeMngNticVo selectFcltsFeeMngNticListTotCnt(GamFcltsFeeMngNticVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectFcltsFeeMngNticList(GamFcltsFeeMngNticVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectFcltsFeeMngNticDetailList(GamFcltsFeeMngNticDetailVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectFcltsFeeMngNticPrintNoticeIssueList(Map searchVO) throws Exception;

	/**
	 * @param gamFcltsFeeMngNticVo
	 * @return
	 */
	String selectFcltsFeeMngNticMaxReqestSeq(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) throws Exception;

	/**
	 * @param gamFcltsFeeMngNticVo
	 */
	void insertFcltsFeeMngNtic(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) throws Exception;

	/**
	 * @param gamFcltsFeeMngNticVo
	 */
	void updateFcltsFeeMngNtic(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) throws Exception;

	/**
	 * @param gamFcltsFeeMngNticVo
	 */
	void deleteFcltsFeeMngNtic(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) throws Exception;

	/**
	 * @param vo
	 */
	void processFcltsFeeMngNticIssue(Map<String, Object> vo) throws Exception;

	/**
	 * @param vo
	 */
	void cancelFcltsFeeMngNticIssue(Map<String, Object> vo) throws Exception;

	/**
	 * @param vo
	 */
	void updateFcltsFeeMngNticIssuePrintYn(Map<String, Object> vo) throws Exception;

}
