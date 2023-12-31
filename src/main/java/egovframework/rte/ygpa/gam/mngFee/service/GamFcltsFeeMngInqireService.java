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

public interface GamFcltsFeeMngInqireService {

	/**
	 * @param searchVO
	 * @return
	 */
	GamFcltsFeeMngInqireVo selectFcltsFeeMngInqireListTotCnt(GamFcltsFeeMngInqireVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectFcltsFeeMngInqireList(GamFcltsFeeMngInqireVo searchVO) throws Exception;

	/**
	 * @param gamFcltsFeeMngInqireVo
	 * @return
	 */
	List selectFcltsFeeMngInqireChartList(GamFcltsFeeMngInqireVo gamFcltsFeeMngInqireVo) throws Exception;

	/**
	 * @param gamFcltsFeeMngInqireMap
	 */
	void updateFcltsFeeMngInqire(Map gamFcltsFeeMngInqireMap) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	String selectEntrpsNm(Map searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectFcltsFeeMngInqireUnpaidList(GamFcltsFeeMngInqireUnpaidVo searchVO) throws Exception;

}
