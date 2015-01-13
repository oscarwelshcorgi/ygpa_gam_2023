package egovframework.rte.ygpa.gam.ctrt.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author HNJ
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 29.		HNJ		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltyCtrtLgerHistService {

	/**
	 * @name selectFcltyCtrtLgerHistList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltyCtrtLgerHistList(GamFcltyCtrtLgerHistVO searchVO) throws Exception;

	/**
	 * @name selectFcltyCtrtLgerHistListSum
	 * @param searchVO
	 * @return GamFcltyCtrtLgerHistVO
	 * @throws Exception
	 */
	GamFcltyCtrtLgerHistVO selectFcltyCtrtLgerHistListSum(GamFcltyCtrtLgerHistVO searchVO) throws Exception;


	/**
	 * @name selectFcltyCtrtLgerHistJoinContrList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltyCtrtLgerHistJoinContrList(GamFcltyCtrtMngJoinContrVO searchVO) throws Exception;


	/**
	 * @name selectFcltyCtrtLgerHistSubctrtList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltyCtrtLgerHistSubctrtList(GamFcltyCtrtMngSubctrtVO searchVO) throws Exception;


	/**
	 * @name selectFcltyCtrtLgerHistChangeList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltyCtrtLgerHistChangeList(GamFcltyCtrtMngChangeVO searchVO) throws Exception;


	/**
	 * @name selectFcltyCtrtLgerHistMoneyPymntList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltyCtrtLgerHistMoneyPymntList(GamFcltyCtrtMngMoneyPymntVO searchVO) throws Exception;


	/**
	 * @name selectFcltyCtrtLgerHistFulfillCaryFwdList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltyCtrtLgerHistFulfillCaryFwdList(GamFcltyCtrtMngFulfillCaryFwdVO searchVO) throws Exception;


	/**
	 * @name selectFcltyCtrtLgerHistScsbidInfoList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltyCtrtLgerHistScsbidInfoList(GamFcltyCtrtMngScsbidInfoVO searchVO) throws Exception;

	/**
	 * @param searchVO
	 * @return
	 */
	EgovMap selectEntrpsInfo(Map searchVO) throws Exception;

}
