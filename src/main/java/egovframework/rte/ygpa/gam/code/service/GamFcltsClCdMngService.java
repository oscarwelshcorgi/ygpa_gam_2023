/**
 *
 */
package egovframework.rte.ygpa.gam.code.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author ACEWOLF
 * @since 2015. 1. 14.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 1. 14.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltsClCdMngService {

	/**
	 * @name selectFcltsClCdMngList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltsClCdMngList(GamFcltsClCdMngVO searchVO) throws Exception;

	/**
	 * @name insertFcltsClCdMng
	 * @param gamFcltsClCdMngVO
	 * @return void
	 * @throws Exception
	 */
	void insertFcltsClCdMng(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception;

	/**
	 * @name updateFcltsClCdMng
	 * @param gamFcltsClCdMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateFcltsClCdMng(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception;

	/**
	 * @name deleteFcltsClCdMng
	 * @param gamFcltsClCdMngVO
	 * @return void
	 * @throws Exception
	 */
	void deleteFcltsClCdMng(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception;

	/**
	 * @name selectFcltsClCdMngListSum
	 * @param searchVO
	 * @return GamFcltsClCdMngVO
	 * @throws Exception
	 */
	GamFcltsClCdMngVO selectFcltsClCdMngListSum(GamFcltsClCdMngVO searchVO) throws Exception;

	/**
	 * @name selectFcltsClCdMngPk
	 * @param gamFcltsClCdMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectFcltsClCdMngPk(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception;

	/**
	 * @name selectFcltsClCdMngNewCd
	 * @param gamFcltsClCdMngVO
	 * @return String
	 * @throws Exception
	 */
	String selectFcltsClCdMngNewCd(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception;

	/**
	 * @name selectFcltsClCdMngTreeList
	 * @param gamFcltsClCdMngVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltsClCdMngTreeList(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception;

	/**
	 * @name selectFcltsClCdMngLowerDataCnt
	 * @param gamFcltsClCdMngVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltsClCdMngLowerDataCnt(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception;

	/**
	 * @name updateFcltsClCdMngLeafYn
	 * @param gamFcltsClCdMngVO
	 * @throws Exception
	 */
	void updateFcltsClCdMngLeafYn(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception;

	/**
	 * @name deleteFcltsClCdMngLowerData
	 * @param gamFcltsClCdMngVO
	 * @throws Exception
	 */
	void deleteFcltsClCdMngLowerData(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception;

}
