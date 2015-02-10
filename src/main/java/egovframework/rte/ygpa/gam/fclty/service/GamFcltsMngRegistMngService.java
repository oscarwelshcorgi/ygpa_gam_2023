/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author ACEWOLF
 * @since 2015. 2. 10.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 10.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltsMngRegistMngService {

	/**
	 * @name selectFcltsMngRegistMngList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltsMngRegistMngList(GamFcltsMngRegistMngVO searchVO) throws Exception;

	/**
	 * @name insertFcltsMngRegistMng
	 * @param gamFcltsMngRegistMngVO
	 * @return void
	 * @throws Exception
	 */
	void insertFcltsMngRegistMng(GamFcltsMngRegistMngVO gamFcltsMngRegistMngVO) throws Exception;

	/**
	 * @name updateFcltsMngRegistMng
	 * @param gamFcltsMngRegistMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateFcltsMngRegistMng(GamFcltsMngRegistMngVO gamFcltsMngRegistMngVO) throws Exception;

	/**
	 * @name deleteFcltsMngRegistMng
	 * @param gamFcltsMngRegistMngVO
	 * @return void
	 * @throws Exception
	 */
	void deleteFcltsMngRegistMng(GamFcltsMngRegistMngVO gamFcltsMngRegistMngVO) throws Exception;

	/**
	 * @name selectFcltsMngRegistMngListSum
	 * @param searchVO
	 * @return GamFcltsMngRegistMngVO
	 * @throws Exception
	 */
	GamFcltsMngRegistMngVO selectFcltsMngRegistMngListSum(GamFcltsMngRegistMngVO searchVO) throws Exception;

	/**
	 * @name selectFcltsMngRegistMngPk
	 * @param gamFcltsMngRegistMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectFcltsMngRegistMngPk(GamFcltsMngRegistMngVO gamFcltsMngRegistMngVO) throws Exception;

	/**
	 * @name selectFcltsMngGroupNm
	 * @param searchVO
	 * @return String
	 * @throws Exception
	 */
	String selectFcltsMngGroupNm(Map searchVO) throws Exception;

}
