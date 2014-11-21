/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import java.util.List;

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

public interface GamGrHseEmitQyMngService {

	/**
	 * @param gamGrHseEmitQyMngVo
	 * @return
	 */
	int selectGrHseEmitQyMngListTotCnt(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) throws Exception;

	/**
	 * @param gamGrHseEmitQyMngVo
	 * @return
	 */
	List selectGrHseEmitQyMngList(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) throws Exception;

	/**
	 * @param gamGrHseEmitQyMngVo
	 * @return
	 */
	List selectGrHseEmitQyMngChartList(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) throws Exception;

	/**
	 * @param gamGrHseEmitQyMngVo
	 * @return
	 */
	List selectGrHseEmitQyMngMonthCntList(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) throws Exception;

	/**
	 * @param gamGrHseEmitQyMngVo
	 */
	void insertGrHseEmitQyMng(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) throws Exception;

	/**
	 * @param gamGrHseEmitQyMngVo
	 */
	void updateGrHseEmitQyMng(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) throws Exception;

	/**
	 * @param gamGrHseEmitQyMngVo
	 */
	void deleteGrHseEmitQyMng(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) throws Exception;

	/**
	 * @param gamGrHseEmitQyMngVo
	 */
	void copyGrHseEmitQyMng(GamGrHseEmitQyMngVo gamGrHseEmitQyMngVo) throws Exception;

}
