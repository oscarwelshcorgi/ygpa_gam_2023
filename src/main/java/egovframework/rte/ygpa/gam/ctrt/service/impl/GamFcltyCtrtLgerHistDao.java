/**
 *
 */
package egovframework.rte.ygpa.gam.ctrt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtLgerHistVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngChangeVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngFulfillCaryFwdVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngJoinContrVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngMoneyPymntVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngScsbidInfoVO;
import egovframework.rte.ygpa.gam.ctrt.service.GamFcltyCtrtMngSubctrtVO;

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

@Repository("gamFcltyCtrtLgerHistDao")
public class GamFcltyCtrtLgerHistDao extends YGPAAbstractDAO {

	/**
	 * @name selectFcltyCtrtLgerHistList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltyCtrtLgerHistList(GamFcltyCtrtLgerHistVO searchVO) {
		return list("gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistList_D", searchVO);
	}

	/**
	 * @name selectFcltyCtrtLgerHistListSum
	 * @param searchVO
	 * @return GamFcltyCtrtLgerHistVO
	 */
	public GamFcltyCtrtLgerHistVO selectFcltyCtrtLgerHistListSum(GamFcltyCtrtLgerHistVO searchVO) {
		return (GamFcltyCtrtLgerHistVO)selectByPk("gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistListSum_S", searchVO);
	}


	/**
	 * @name selectFcltyCtrtLgerHistJoinContrList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltyCtrtLgerHistJoinContrList(GamFcltyCtrtMngJoinContrVO searchVO) {
		return list("gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistJoinContrList_D", searchVO);
	}


	/**
	 * @name selectFcltyCtrtLgerHistSubctrtList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltyCtrtLgerHistSubctrtList(GamFcltyCtrtMngSubctrtVO searchVO) {
		return list("gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistSubctrtList_D", searchVO);
	}


	/**
	 * @name selectFcltyCtrtLgerHistChangeList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltyCtrtLgerHistChangeList(GamFcltyCtrtMngChangeVO searchVO) {
		return list("gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistChangeList_D", searchVO);
	}


	/**
	 * @name selectFcltyCtrtLgerHistMoneyPymntList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltyCtrtLgerHistMoneyPymntList(GamFcltyCtrtMngMoneyPymntVO searchVO) {
		return list("gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistMoneyPymntList_D", searchVO);
	}


	/**
	 * @name selectFcltyCtrtLgerHistFulfillCaryFwdList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltyCtrtLgerHistFulfillCaryFwdList(GamFcltyCtrtMngFulfillCaryFwdVO searchVO) {
		return list("gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistFulfillCaryFwdList_D", searchVO);
	}


	/**
	 * @name selectFcltyCtrtLgerHistScsbidInfoList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltyCtrtLgerHistScsbidInfoList(GamFcltyCtrtMngScsbidInfoVO searchVO) {
		return list("gamFcltyCtrtLgerHistDao.selectFcltyCtrtLgerHistScsbidInfoList_D", searchVO);
	}

	/**
	 * @name selectEntrpsInfo
	 * @param searchVO
	 * @return
	 */
	public EgovMap selectEntrpsInfo(Map searchVO) {
		return (EgovMap)selectByPk("gamFcltyCtrtLgerHistDao.selectEntrpsInfo_S", searchVO);
	}

}
