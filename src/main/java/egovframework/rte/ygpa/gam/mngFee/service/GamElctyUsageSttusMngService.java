/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

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

public interface GamElctyUsageSttusMngService {

	/**
	 * @param searchVO
	 * @return
	 */
	int selectElctyUsageSttusMngListTotCnt(GamElctyUsageSttusMngVo searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	List selectElctyUsageSttusMngList(GamElctyUsageSttusMngVo searchVO) throws Exception;

	/**
	 * @param gamElctyUsageSttusMngVo
	 * @return
	 */
	EgovMap selectElctyUsageSttusMngPk(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) throws Exception;

	/**
	 * @param gamElctyUsageSttusMngVo
	 * @return
	 */
	List selectElctyUsageSttusMngChartList(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) throws Exception;

	/**
	 * @param gamElctyUsageSttusMngVo
	 * @return
	 */
	String selectElctyUsageSttusMngPrevMtUsageQy(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) throws Exception;

	/**
	 * @param gamElctyUsageSttusMngVo
	 * @return
	 */
	List selectElctyUsageSttusMngMonthCntList(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) throws Exception;

	/**
	 * @param gamElctyUsageSttusMngVo
	 */
	void insertElctyUsageSttusMng(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) throws Exception;

	/**
	 * @param gamElctyUsageSttusMngVo
	 */
	void updateElctyUsageSttusMng(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) throws Exception;

	/**
	 * @param gamElctyUsageSttusMngVo
	 */
	void deleteElctyUsageSttusMng(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) throws Exception;

	/**
	 * @param gamElctyUsageSttusMngVo
	 */
	void copyElctyUsageSttusMng(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) throws Exception;

}
