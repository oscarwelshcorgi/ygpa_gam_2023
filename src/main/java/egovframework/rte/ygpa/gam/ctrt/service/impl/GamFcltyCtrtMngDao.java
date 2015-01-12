/**
 *
 */
package egovframework.rte.ygpa.gam.ctrt.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngChangeVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngFulfillCaryFwdVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngJoinContrVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngMoneyPymntVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngScsbidInfoVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngSubctrtVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngVO;

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

@Repository("gamFcltyCtrtMngDao")
public class GamFcltyCtrtMngDao extends YGPAAbstractDAO {

	/**
	 * @name selectFcltyCtrtMngList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltyCtrtMngList(GamFcltyCtrtMngVO searchVO) {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtMngList_D", searchVO);
	}

	/**
	 * @name insertFcltyCtrtMng
	 * @param gamFcltyCtrtMngVO
	 * @return void
	 */
	public void insertFcltyCtrtMng(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtMng_S", gamFcltyCtrtMngVO);
	}

	/**
	 * @name updateFcltyCtrtMng
	 * @param gamFcltyCtrtMngVO
	 * @return void
	 */
	public void updateFcltyCtrtMng(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) {
		update("gamFcltyCtrtMngDao.updateFcltyCtrtMng_S", gamFcltyCtrtMngVO);
	}

	/**
	 * @name deleteFcltyCtrtMng
	 * @param gamFcltyCtrtMngVO
	 * @return void
	 */
	public void deleteFcltyCtrtMng(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtMng_S", gamFcltyCtrtMngVO);
	}

	/**
	 * @name selectFcltyCtrtMngPk
	 * @param gamFcltyCtrtMngVO
	 * @return EgovMap
	 */
	public EgovMap selectFcltyCtrtMngPk(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) {
		return (EgovMap)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtMngPk_S", gamFcltyCtrtMngVO);
	}

	/**
	 * @name selectFcltyCtrtMngListSum
	 * @param searchVO
	 * @return GamFcltyCtrtMngVO
	 */
	public GamFcltyCtrtMngVO selectFcltyCtrtMngListSum(GamFcltyCtrtMngVO searchVO) {
		return (GamFcltyCtrtMngVO)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtMngListSum_S", searchVO);
	}

	/**
	 * @name selectFcltyCtrtMngNewCtrtNo
	 * @param gamFcltyCtrtMngJoinContrVO
	 * @return String
	 */
	public String selectFcltyCtrtMngNewCtrtNo(GamFcltyCtrtMngVO gamFcltyCtrtMngVO) {
		return (String)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtMngNewCtrtNo_S", gamFcltyCtrtMngVO);
	}


	/**
	 * @name selectFcltyCtrtMngJoinContrList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltyCtrtMngJoinContrList(GamFcltyCtrtMngJoinContrVO searchVO) {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtMngJoinContrList_D", searchVO);
	}

	/**
	 * @name insertFcltyCtrtMngJoinContr
	 * @param gamFcltyCtrtMngJoinContrVO
	 * @return void
	 */
	public void insertFcltyCtrtMngJoinContr(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtMngJoinContr_S", gamFcltyCtrtMngJoinContrVO);
	}

	/**
	 * @name updateFcltyCtrtMngJoinContr
	 * @param gamFcltyCtrtMngJoinContrVO
	 * @return void
	 */
	public void updateFcltyCtrtMngJoinContr(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) {
		update("gamFcltyCtrtMngDao.updateFcltyCtrtMngJoinContr_S", gamFcltyCtrtMngJoinContrVO);
	}

	/**
	 * @name deleteFcltyCtrtMngJoinContr
	 * @param gamFcltyCtrtMngJoinContrVO
	 * @return void
	 */
	public void deleteFcltyCtrtMngJoinContr(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtMngJoinContr_S", gamFcltyCtrtMngJoinContrVO);
	}

	/**
	 * @name selectFcltyCtrtMngJoinContrPk
	 * @param gamFcltyCtrtMngJoinContrVO
	 * @return EgovMap
	 */
	public EgovMap selectFcltyCtrtMngJoinContrPk(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) {
		return (EgovMap)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtMngJoinContrPk_S", gamFcltyCtrtMngJoinContrVO);
	}

	/**
	 * @name selectFcltyCtrtMngJoinContrMaxSeq
	 * @param gamFcltyCtrtMngJoinContrVO
	 * @return String
	 */
	public String selectFcltyCtrtMngJoinContrMaxSeq(GamFcltyCtrtMngJoinContrVO gamFcltyCtrtMngJoinContrVO) {
		return (String)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtMngJoinContrMaxSeq_S", gamFcltyCtrtMngJoinContrVO);
	}


	/**
	 * @name selectFcltyCtrtMngSubctrtList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltyCtrtMngSubctrtList(GamFcltyCtrtMngSubctrtVO searchVO) {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtMngSubctrtList_D", searchVO);
	}

	/**
	 * @name insertFcltyCtrtMngSubctrt
	 * @param gamFcltyCtrtMngSubctrtVO
	 * @return void
	 */
	public void insertFcltyCtrtMngSubctrt(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtMngSubctrt_S", gamFcltyCtrtMngSubctrtVO);
	}

	/**
	 * @name updateFcltyCtrtMngSubctrt
	 * @param gamFcltyCtrtMngSubctrtVO
	 * @return void
	 */
	public void updateFcltyCtrtMngSubctrt(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) {
		update("gamFcltyCtrtMngDao.updateFcltyCtrtMngSubctrt_S", gamFcltyCtrtMngSubctrtVO);
	}

	/**
	 * @name deleteFcltyCtrtMngSubctrt
	 * @param gamFcltyCtrtMngSubctrtVO
	 * @return void
	 */
	public void deleteFcltyCtrtMngSubctrt(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtMngSubctrt_S", gamFcltyCtrtMngSubctrtVO);
	}

	/**
	 * @name selectFcltyCtrtMngSubctrtPk
	 * @param gamFcltyCtrtMngSubctrtVO
	 * @return EgovMap
	 */
	public EgovMap selectFcltyCtrtMngSubctrtPk(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) {
		return (EgovMap)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtMngSubctrtPk_S", gamFcltyCtrtMngSubctrtVO);
	}

	/**
	 * @name selectFcltyCtrtMngSubctrtMaxSeq
	 * @param gamFcltyCtrtMngSubctrtVO
	 * @return String
	 */
	public String selectFcltyCtrtMngSubctrtMaxSeq(GamFcltyCtrtMngSubctrtVO gamFcltyCtrtMngSubctrtVO) {
		return (String)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtMngSubctrtMaxSeq_S", gamFcltyCtrtMngSubctrtVO);
	}


	/**
	 * @name selectFcltyCtrtMngChangeList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltyCtrtMngChangeList(GamFcltyCtrtMngChangeVO searchVO) {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtMngChangeList_D", searchVO);
	}

	/**
	 * @name insertFcltyCtrtMngChange
	 * @param gamFcltyCtrtMngChangeVO
	 * @return void
	 */
	public void insertFcltyCtrtMngChange(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtMngChange_S", gamFcltyCtrtMngChangeVO);
	}

	/**
	 * @name updateFcltyCtrtMngChange
	 * @param gamFcltyCtrtMngChangeVO
	 * @return void
	 */
	public void updateFcltyCtrtMngChange(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) {
		update("gamFcltyCtrtMngDao.updateFcltyCtrtMngChange_S", gamFcltyCtrtMngChangeVO);
	}

	/**
	 * @name deleteFcltyCtrtMngChange
	 * @param gamFcltyCtrtMngChangeVO
	 * @return void
	 */
	public void deleteFcltyCtrtMngChange(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtMngChange_S", gamFcltyCtrtMngChangeVO);
	}

	/**
	 * @name selectFcltyCtrtMngChangePk
	 * @param gamFcltyCtrtMngChangeVO
	 * @return EgovMap
	 */
	public EgovMap selectFcltyCtrtMngChangePk(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) {
		return (EgovMap)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtMngChangePk_S", gamFcltyCtrtMngChangeVO);
	}

	/**
	 * @name selectFcltyCtrtMngChangeMaxSeq
	 * @param gamFcltyCtrtMngChangeVO
	 * @return String
	 */
	public String selectFcltyCtrtMngChangeMaxSeq(GamFcltyCtrtMngChangeVO gamFcltyCtrtMngChangeVO) {
		return (String)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtMngChangeMaxSeq_S", gamFcltyCtrtMngChangeVO);
	}


	/**
	 * @name selectFcltyCtrtMngMoneyPymntList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltyCtrtMngMoneyPymntList(GamFcltyCtrtMngMoneyPymntVO searchVO) {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtMngMoneyPymntList_D", searchVO);
	}

	/**
	 * @name insertFcltyCtrtMngMoneyPymnt
	 * @param gamFcltyCtrtMngMoneyPymntVO
	 * @return void
	 */
	public void insertFcltyCtrtMngMoneyPymnt(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtMngMoneyPymnt_S", gamFcltyCtrtMngMoneyPymntVO);
	}

	/**
	 * @name updateFcltyCtrtMngMoneyPymnt
	 * @param gamFcltyCtrtMngMoneyPymntVO
	 * @return void
	 */
	public void updateFcltyCtrtMngMoneyPymnt(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) {
		update("gamFcltyCtrtMngDao.updateFcltyCtrtMngMoneyPymnt_S", gamFcltyCtrtMngMoneyPymntVO);
	}

	/**
	 * @name deleteFcltyCtrtMngMoneyPymnt
	 * @param gamFcltyCtrtMngMoneyPymntVO
	 * @return void
	 */
	public void deleteFcltyCtrtMngMoneyPymnt(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtMngMoneyPymnt_S", gamFcltyCtrtMngMoneyPymntVO);
	}

	/**
	 * @name selectFcltyCtrtMngMoneyPymntPk
	 * @param gamFcltyCtrtMngMoneyPymntVO
	 * @return EgovMap
	 */
	public EgovMap selectFcltyCtrtMngMoneyPymntPk(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) {
		return (EgovMap)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtMngMoneyPymntPk_S", gamFcltyCtrtMngMoneyPymntVO);
	}

	/**
	 * @name selectFcltyCtrtMngMoneyPymntMaxSeq
	 * @param gamFcltyCtrtMngMoneyPymntVO
	 * @return String
	 */
	public String selectFcltyCtrtMngMoneyPymntMaxSeq(GamFcltyCtrtMngMoneyPymntVO gamFcltyCtrtMngMoneyPymntVO) {
		return (String)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtMngMoneyPymntMaxSeq_S", gamFcltyCtrtMngMoneyPymntVO);
	}


	/**
	 * @name selectFcltyCtrtMngFulfillCaryFwdList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltyCtrtMngFulfillCaryFwdList(GamFcltyCtrtMngFulfillCaryFwdVO searchVO) {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtMngFulfillCaryFwdList_D", searchVO);
	}

	/**
	 * @name insertFcltyCtrtMngFulfillCaryFwd
	 * @param gamFcltyCtrtMngFulfillCaryFwdVO
	 * @return void
	 */
	public void insertFcltyCtrtMngFulfillCaryFwd(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtMngFulfillCaryFwd_S", gamFcltyCtrtMngFulfillCaryFwdVO);
	}

	/**
	 * @name updateFcltyCtrtMngFulfillCaryFwd
	 * @param gamFcltyCtrtMngFulfillCaryFwdVO
	 * @return void
	 */
	public void updateFcltyCtrtMngFulfillCaryFwd(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) {
		update("gamFcltyCtrtMngDao.updateFcltyCtrtMngFulfillCaryFwd_S", gamFcltyCtrtMngFulfillCaryFwdVO);
	}

	/**
	 * @name deleteFcltyCtrtMngFulfillCaryFwd
	 * @param gamFcltyCtrtMngFulfillCaryFwdVO
	 * @return void
	 */
	public void deleteFcltyCtrtMngFulfillCaryFwd(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtMngFulfillCaryFwd_S", gamFcltyCtrtMngFulfillCaryFwdVO);
	}

	/**
	 * @name selectFcltyCtrtMngFulfillCaryFwdPk
	 * @param gamFcltyCtrtMngFulfillCaryFwdVO
	 * @return EgovMap
	 */
	public EgovMap selectFcltyCtrtMngFulfillCaryFwdPk(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) {
		return (EgovMap)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtMngFulfillCaryFwdPk_S", gamFcltyCtrtMngFulfillCaryFwdVO);
	}

	/**
	 * @name selectFcltyCtrtMngFulfillCaryFwdMaxSeq
	 * @param gamFcltyCtrtMngFulfillCaryFwdVO
	 * @return String
	 */
	public String selectFcltyCtrtMngFulfillCaryFwdMaxSeq(GamFcltyCtrtMngFulfillCaryFwdVO gamFcltyCtrtMngFulfillCaryFwdVO) {
		return (String)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtMngFulfillCaryFwdMaxSeq_S", gamFcltyCtrtMngFulfillCaryFwdVO);
	}


	/**
	 * @name selectFcltyCtrtMngScsbidInfoList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltyCtrtMngScsbidInfoList(GamFcltyCtrtMngScsbidInfoVO searchVO) {
		return list("gamFcltyCtrtMngDao.selectFcltyCtrtMngScsbidInfoList_D", searchVO);
	}

	/**
	 * @name insertFcltyCtrtMngScsbidInfo
	 * @param gamFcltyCtrtMngScsbidInfoVO
	 * @return void
	 */
	public void insertFcltyCtrtMngScsbidInfo(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) {
		insert("gamFcltyCtrtMngDao.insertFcltyCtrtMngScsbidInfo_S", gamFcltyCtrtMngScsbidInfoVO);
	}

	/**
	 * @name updateFcltyCtrtMngScsbidInfo
	 * @param gamFcltyCtrtMngScsbidInfoVO
	 * @return void
	 */
	public void updateFcltyCtrtMngScsbidInfo(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) {
		update("gamFcltyCtrtMngDao.updateFcltyCtrtMngScsbidInfo_S", gamFcltyCtrtMngScsbidInfoVO);
	}

	/**
	 * @name deleteFcltyCtrtMngScsbidInfo
	 * @param gamFcltyCtrtMngScsbidInfoVO
	 * @return void
	 */
	public void deleteFcltyCtrtMngScsbidInfo(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) {
		delete("gamFcltyCtrtMngDao.deleteFcltyCtrtMngScsbidInfo_S", gamFcltyCtrtMngScsbidInfoVO);
	}

	/**
	 * @name selectFcltyCtrtMngScsbidInfoPk
	 * @param gamFcltyCtrtMngScsbidInfoVO
	 * @return EgovMap
	 */
	public EgovMap selectFcltyCtrtMngScsbidInfoPk(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) {
		return (EgovMap)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtMngScsbidInfoPk_S", gamFcltyCtrtMngScsbidInfoVO);
	}

	/**
	 * @name selectFcltyCtrtMngScsbidInfoMaxSeq
	 * @param gamFcltyCtrtMngScsbidInfoVO
	 * @return String
	 */
	public String selectFcltyCtrtMngScsbidInfoMaxSeq(GamFcltyCtrtMngScsbidInfoVO gamFcltyCtrtMngScsbidInfoVO) {
		return (String)selectByPk("gamFcltyCtrtMngDao.selectFcltyCtrtMngScsbidInfoMaxSeq_S", gamFcltyCtrtMngScsbidInfoVO);
	}

}
