/**
 *
 */
package egovframework.rte.ygpa.gam.ctrt.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *
 * @author 김종민
 * @since 2014. 10. 29.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 29.		김종민		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

public interface GamFcltyCtrtMngService {

	/**
	 * @name selectFcltyCtrtMngList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltyCtrtMngList(GamFcltyCtrtMngVO searchVO) throws Exception;

	/**
	 * @name insertFcltyCtrtMng
	 * @param gamFcltyCtrtMngVO
	 * @return void
	 * @throws Exception
	 */
	void insertFcltyCtrtMng(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) throws Exception;

	/**
	 * @name updateFcltyCtrtMng
	 * @param gamFcltyCtrtMngVO
	 * @return void
	 * @throws Exception
	 */
	void updateFcltyCtrtMng(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) throws Exception;

	/**
	 * @name deleteFcltyCtrtMng
	 * @param gamFcltyCtrtMngVO
	 * @return void
	 * @throws Exception
	 */
	void deleteFcltyCtrtMng(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) throws Exception;

	/**
	 * @name selectFcltyCtrtMngPk
	 * @param gamFcltyCtrtMngVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectFcltyCtrtMngPk(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) throws Exception;

	/**
	 * @name selectFcltyCtrtMngListSum
	 * @param searchVO
	 * @return GamFcltyCtrtMngVO
	 * @throws Exception
	 */
	GamFcltyCtrtMngVO selectFcltyCtrtMngListSum(GamFcltyCtrtMngVO searchVO) throws Exception;

	/**
	 * @name selectFcltyCtrtMngNewCtrtNo
	 * @param gamFcltyCtrtMngVO
	 * @return String
	 * @throws Exception
	 */
	String selectFcltyCtrtMngNewCtrtNo(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) throws Exception;


	/**
	 * @name selectFcltyCtrtMngJoinContrList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltyCtrtMngJoinContrList(GamFcltyCtrtMngJoinContrVO searchVO) throws Exception;

	/**
	 * @name insertFcltyCtrtMngJoinContr
	 * @param gamFcltyCtrtMngJoinContrVO
	 * @return void
	 * @throws Exception
	 */
	void insertFcltyCtrtMngJoinContr(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) throws Exception;

	/**
	 * @name updateFcltyCtrtMngJoinContr
	 * @param gamFcltyCtrtMngJoinContrVO
	 * @return void
	 * @throws Exception
	 */
	void updateFcltyCtrtMngJoinContr(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) throws Exception;

	/**
	 * @name deleteFcltyCtrtMngJoinContr
	 * @param gamFcltyCtrtMngJoinContrVO
	 * @return void
	 * @throws Exception
	 */
	void deleteFcltyCtrtMngJoinContr(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) throws Exception;

	/**
	 * @name selectFcltyCtrtMngJoinContrPk
	 * @param gamFcltyCtrtMngJoinContrVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectFcltyCtrtMngJoinContrPk(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) throws Exception;

	/**
	 * @name selectFcltyCtrtMngJoinContrMaxSeq
	 * @param gamFcltyCtrtMngJoinContrVO
	 * @return String
	 * @throws Exception
	 */
	String selectFcltyCtrtMngJoinContrMaxSeq(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) throws Exception;


	/**
	 * @name selectFcltyCtrtMngSubctrtList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltyCtrtMngSubctrtList(GamFcltyCtrtMngSubctrtVO searchVO) throws Exception;

	/**
	 * @name insertFcltyCtrtMngSubctrt
	 * @param gamFcltyCtrtMngSubctrtVO
	 * @return void
	 * @throws Exception
	 */
	void insertFcltyCtrtMngSubctrt(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) throws Exception;

	/**
	 * @name updateFcltyCtrtMngSubctrt
	 * @param gamFcltyCtrtMngSubctrtVO
	 * @return void
	 * @throws Exception
	 */
	void updateFcltyCtrtMngSubctrt(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) throws Exception;

	/**
	 * @name deleteFcltyCtrtMngSubctrt
	 * @param gamFcltyCtrtMngSubctrtVO
	 * @return void
	 * @throws Exception
	 */
	void deleteFcltyCtrtMngSubctrt(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) throws Exception;

	/**
	 * @name selectFcltyCtrtMngSubctrtPk
	 * @param gamFcltyCtrtMngSubctrtVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectFcltyCtrtMngSubctrtPk(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) throws Exception;

	/**
	 * @name selectFcltyCtrtMngSubctrtMaxSeq
	 * @param gamFcltyCtrtMngSubctrtVO
	 * @return String
	 * @throws Exception
	 */
	String selectFcltyCtrtMngSubctrtMaxSeq(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) throws Exception;


	/**
	 * @name selectFcltyCtrtMngChangeList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltyCtrtMngChangeList(GamFcltyCtrtMngChangeVO searchVO) throws Exception;

	/**
	 * @name insertFcltyCtrtMngChange
	 * @param gamFcltyCtrtMngChangeVO
	 * @return void
	 * @throws Exception
	 */
	void insertFcltyCtrtMngChange(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) throws Exception;

	/**
	 * @name updateFcltyCtrtMngChange
	 * @param gamFcltyCtrtMngChangeVO
	 * @return void
	 * @throws Exception
	 */
	void updateFcltyCtrtMngChange(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) throws Exception;

	/**
	 * @name deleteFcltyCtrtMngChange
	 * @param gamFcltyCtrtMngChangeVO
	 * @return void
	 * @throws Exception
	 */
	void deleteFcltyCtrtMngChange(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) throws Exception;

	/**
	 * @name selectFcltyCtrtMngChangePk
	 * @param gamFcltyCtrtMngChangeVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectFcltyCtrtMngChangePk(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) throws Exception;

	/**
	 * @name selectFcltyCtrtMngChangeMaxSeq
	 * @param gamFcltyCtrtMngChangeVO
	 * @return String
	 * @throws Exception
	 */
	String selectFcltyCtrtMngChangeMaxSeq(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) throws Exception;


	/**
	 * @name selectFcltyCtrtMngMoneyPymntList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltyCtrtMngMoneyPymntList(GamFcltyCtrtMngMoneyPymntVO searchVO) throws Exception;

	/**
	 * @name insertFcltyCtrtMngMoneyPymnt
	 * @param gamFcltyCtrtMngMoneyPymntVO
	 * @return void
	 * @throws Exception
	 */
	void insertFcltyCtrtMngMoneyPymnt(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) throws Exception;

	/**
	 * @name updateFcltyCtrtMngMoneyPymnt
	 * @param gamFcltyCtrtMngMoneyPymntVO
	 * @return void
	 * @throws Exception
	 */
	void updateFcltyCtrtMngMoneyPymnt(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) throws Exception;

	/**
	 * @name deleteFcltyCtrtMngMoneyPymnt
	 * @param gamFcltyCtrtMngMoneyPymntVO
	 * @return void
	 * @throws Exception
	 */
	void deleteFcltyCtrtMngMoneyPymnt(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) throws Exception;

	/**
	 * @name selectFcltyCtrtMngMoneyPymntPk
	 * @param gamFcltyCtrtMngMoneyPymntVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectFcltyCtrtMngMoneyPymntPk(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) throws Exception;

	/**
	 * @name selectFcltyCtrtMngMoneyPymntMaxSeq
	 * @param gamFcltyCtrtMngMoneyPymntVO
	 * @return String
	 * @throws Exception
	 */
	String selectFcltyCtrtMngMoneyPymntMaxSeq(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) throws Exception;


	/**
	 * @name selectFcltyCtrtMngFulfillCaryFwdList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltyCtrtMngFulfillCaryFwdList(GamFcltyCtrtMngFulfillCaryFwdVO searchVO) throws Exception;

	/**
	 * @name insertFcltyCtrtMngFulfillCaryFwd
	 * @param gamFcltyCtrtMngFulfillCaryFwdVO
	 * @return void
	 * @throws Exception
	 */
	void insertFcltyCtrtMngFulfillCaryFwd(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) throws Exception;

	/**
	 * @name updateFcltyCtrtMngFulfillCaryFwd
	 * @param gamFcltyCtrtMngFulfillCaryFwdVO
	 * @return void
	 * @throws Exception
	 */
	void updateFcltyCtrtMngFulfillCaryFwd(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) throws Exception;

	/**
	 * @name deleteFcltyCtrtMngFulfillCaryFwd
	 * @param gamFcltyCtrtMngFulfillCaryFwdVO
	 * @return void
	 * @throws Exception
	 */
	void deleteFcltyCtrtMngFulfillCaryFwd(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) throws Exception;

	/**
	 * @name selectFcltyCtrtMngFulfillCaryFwdPk
	 * @param gamFcltyCtrtMngFulfillCaryFwdVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectFcltyCtrtMngFulfillCaryFwdPk(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) throws Exception;

	/**
	 * @name selectFcltyCtrtMngFulfillCaryFwdMaxSeq
	 * @param gamFcltyCtrtMngFulfillCaryFwdVO
	 * @return String
	 * @throws Exception
	 */
	String selectFcltyCtrtMngFulfillCaryFwdMaxSeq(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) throws Exception;


	/**
	 * @name selectFcltyCtrtMngScsbidInfoList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltyCtrtMngScsbidInfoList(GamFcltyCtrtMngScsbidInfoVO searchVO) throws Exception;

	/**
	 * @name insertFcltyCtrtMngScsbidInfo
	 * @param gamFcltyCtrtMngScsbidInfoVO
	 * @return void
	 * @throws Exception
	 */
	void insertFcltyCtrtMngScsbidInfo(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) throws Exception;

	/**
	 * @name updateFcltyCtrtMngScsbidInfo
	 * @param gamFcltyCtrtMngScsbidInfoVO
	 * @return void
	 * @throws Exception
	 */
	void updateFcltyCtrtMngScsbidInfo(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) throws Exception;

	/**
	 * @name deleteFcltyCtrtMngScsbidInfo
	 * @param gamFcltyCtrtMngScsbidInfoVO
	 * @return void
	 * @throws Exception
	 */
	void deleteFcltyCtrtMngScsbidInfo(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) throws Exception;

	/**
	 * @name selectFcltyCtrtMngScsbidInfoPk
	 * @param gamFcltyCtrtMngScsbidInfoVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectFcltyCtrtMngScsbidInfoPk(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) throws Exception;

	/**
	 * @name selectFcltyCtrtMngScsbidInfoMaxSeq
	 * @param gamFcltyCtrtMngScsbidInfoVO
	 * @return String
	 * @throws Exception
	 */
	String selectFcltyCtrtMngScsbidInfoMaxSeq(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) throws Exception;


	/**
	 * @name selectFcltyCtrtMngFlawGrntyList
	 * @param searchVO
	 * @return List
	 * @throws Exception
	 */
	List selectFcltyCtrtMngFlawGrntyList(GamFcltyCtrtMngFlawGrntyVO searchVO) throws Exception;

	/**
	 * @name insertFcltyCtrtMngFlawGrnty
	 * @param gamFcltyCtrtMngFlawGrntyVO
	 * @return void
	 * @throws Exception
	 */
	void insertFcltyCtrtMngFlawGrnty(GamFcltyCtrtMngFlawGrntyVO gamFcltyCtrtMngFlawGrntyVO) throws Exception;

	/**
	 * @name updateFcltyCtrtMngFlawGrnty
	 * @param gamFcltyCtrtMngFlawGrntyVO
	 * @return void
	 * @throws Exception
	 */
	void updateFcltyCtrtMngFlawGrnty(GamFcltyCtrtMngFlawGrntyVO gamFcltyCtrtMngFlawGrntyVO) throws Exception;

	/**
	 * @name deleteFcltyCtrtMngFlawGrnty
	 * @param gamFcltyCtrtMngFlawGrntyVO
	 * @return void
	 * @throws Exception
	 */
	void deleteFcltyCtrtMngFlawGrnty(GamFcltyCtrtMngFlawGrntyVO gamFcltyCtrtMngFlawGrntyVO) throws Exception;

	/**
	 * @name deleteFcltyCtrtMngAllFlawGrnty
	 * @param gamFcltyCtrtMngFlawGrntyVO
	 * @return void
	 * @throws Exception
	 */
	void deleteFcltyCtrtMngAllFlawGrnty(GamFcltyCtrtMngFlawGrntyVO gamFcltyCtrtMngFlawGrntyVO) throws Exception;

	/**
	 * @name selectFcltyCtrtMngFlawGrntyPk
	 * @param gamFcltyCtrtMngFlawGrntyVO
	 * @return EgovMap
	 * @throws Exception
	 */
	EgovMap selectFcltyCtrtMngFlawGrntyPk(GamFcltyCtrtMngFlawGrntyVO gamFcltyCtrtMngFlawGrntyVO) throws Exception;

	/**
	 * @name selectFcltyCtrtMngFlawGrntyMaxSeq
	 * @param gamFcltyCtrtMngFlawGrntyVO
	 * @return String
	 * @throws Exception
	 */
	String selectFcltyCtrtMngFlawGrntyMaxSeq(GamFcltyCtrtMngFlawGrntyVO gamFcltyCtrtMngFlawGrntyVO) throws Exception;


	/**
	 * @param searchVO
	 * @return
	 */
	EgovMap selectEntrpsInfo(Map searchVO) throws Exception;

}
