/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author ACEWOLF
 * @since 2015. 3. 5.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 3. 5.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltsChargerMngService {

	/**
	 * @name selectFcltsChargerMngList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltsChargerMngList(GamFcltsChargerMngVO searchVO) throws Exception;

	/**
	 * @name insertFcltsChargerMng
	 * @param gamFcltsChargerMngVO
	 * @return void
	 * @throws Exception
	 */
	void insertFcltsChargerMng(GamFcltsChargerMngVO gamFcltsChargerMngVO) throws Exception;

	/**
	 * @name updateFcltsChargerMng
	 * @param gamFcltsChargerMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateFcltsChargerMng(GamFcltsChargerMngVO gamFcltsChargerMngVO) throws Exception;

	/**
	 * @name deleteFcltsChargerMng
	 * @param gamFcltsChargerMngVO
	 * @return void
	 * @throws Exception
	 */
	void deleteFcltsChargerMng(GamFcltsChargerMngVO gamFcltsChargerMngVO) throws Exception;

	/**
	 * @name selectFcltsChargerMngListSum
	 * @param searchVO
	 * @return GamFcltsChargerMngVO
	 * @throws Exception
	 */
	GamFcltsChargerMngVO selectFcltsChargerMngListSum(GamFcltsChargerMngVO searchVO) throws Exception;

	/**
	 * @name selectFcltsChargerMngPk
	 * @param gamFcltsChargerMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectFcltsChargerMngPk(GamFcltsChargerMngVO gamFcltsChargerMngVO) throws Exception;

}
