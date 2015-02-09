/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author ACEWOLF
 * @since 2015. 2. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 7.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamElctyUsageQyMngService {

	/**
	 * @name selectElctyUsageQyMngList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectElctyUsageQyMngList(GamElctyUsageQyMngVO searchVO) throws Exception;

	/**
	 * @name insertElctyUsageQyMng
	 * @param gamElctyUsageQyMngVO
	 * @return void
	 * @throws Exception
	 */
	void insertElctyUsageQyMng(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception;

	/**
	 * @name updateElctyUsageQyMng
	 * @param gamElctyUsageQyMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateElctyUsageQyMng(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception;

	/**
	 * @name deleteElctyUsageQyMng
	 * @param gamElctyUsageQyMngVO
	 * @return void
	 * @throws Exception
	 */
	void deleteElctyUsageQyMng(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception;

	/**
	 * @name selectElctyUsageQyMngPk
	 * @param gamElctyUsageQyMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectElctyUsageQyMngPk(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception;

	/**
	 * @name selectElctyUsageQyMngListSum
	 * @param searchVO
	 * @return GamElctyUsageQyMngVO
	 * @throws Exception
	 */
	GamElctyUsageQyMngVO selectElctyUsageQyMngListSum(GamElctyUsageQyMngVO searchVO) throws Exception;

	/**
	 * @name selectElctyUsageQyMngChartList
	 * @param gamElctyUsageQyMngVO
	 * @return List
	 * @throws Exception
	 */
	List selectElctyUsageQyMngChartList(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception;

	/**
	 * @name selectElctyUsageQyMngYearCntList
	 * @param gamElctyUsageQyMngVO
	 * @return List
	 * @throws Exception
	 */
	List selectElctyUsageQyMngYearCntList(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception;

	/**
	 * @name copyElctyUsageQyMng
	 * @param gamElctyUsageQyMngVO
	 * @return void
	 * @throws Exception
	 */
	void copyElctyUsageQyMng(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) throws Exception;

	/**
	 * @name selectMngFeeFcltyCdList
	 * @param
	 * @return List
	 * @throws Exception
	 */
	public List selectMngFeeFcltyCdList() throws Exception;

}
