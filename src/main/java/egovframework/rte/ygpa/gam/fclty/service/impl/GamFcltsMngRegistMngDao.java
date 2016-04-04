/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsMngRegistMngMntnRprDtlsVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsMngRegistMngQcMngDtlsVO;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsMngRegistMngVO;

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

@Repository("gamFcltsMngRegistMngDao")
public class GamFcltsMngRegistMngDao extends YGPAAbstractDAO {

	/**
	 * @name selectFcltsMngRegistMngList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltsMngRegistMngList(GamFcltsMngRegistMngVO searchVO) {
		return list("gamFcltsMngRegistMngDao.selectFcltsMngRegistMngList_D", searchVO);
	}

	/**
	 * @name insertFcltsMngRegistMng
	 * @param gamFcltsMngRegistMngVO
	 * @return void
	 */
	public void insertFcltsMngRegistMng(GamFcltsMngRegistMngVO gamFcltsMngRegistMngVO) {
		insert("gamFcltsMngRegistMngDao.insertFcltsMngRegistMng_S", gamFcltsMngRegistMngVO);
	}

	/**
	 * @name updateFcltsMngRegistMng
	 * @param gamFcltsMngRegistMngVO
	 * @return void
	 */
	public void updateFcltsMngRegistMng(GamFcltsMngRegistMngVO gamFcltsMngRegistMngVO) {
		update("gamFcltsMngRegistMngDao.updateFcltsMngRegistMng_S", gamFcltsMngRegistMngVO);
	}

	/**
	 * @name deleteFcltsMngRegistMng
	 * @param gamFcltsMngRegistMngVO
	 * @return void
	 */
	public void deleteFcltsMngRegistMng(GamFcltsMngRegistMngVO gamFcltsMngRegistMngVO) {
		delete("gamFcltsMngRegistMngDao.deleteFcltsMngRegistMng_S", gamFcltsMngRegistMngVO);
	}

	/**
	 * @name selectFcltsMngRegistMngListSum
	 * @param searchVO
	 * @return GamFcltsMngRegistMngVO
	 */
	public GamFcltsMngRegistMngVO selectFcltsMngRegistMngListSum(GamFcltsMngRegistMngVO searchVO) {
		return (GamFcltsMngRegistMngVO)selectByPk("gamFcltsMngRegistMngDao.selectFcltsMngRegistMngListSum_S", searchVO);
	}

	/**
	 * @name selectFcltsMngRegistMngPk
	 * @param gamFcltsMngRegistMngVO
	 * @return EgovMap
	 */
	public EgovMap selectFcltsMngRegistMngPk(GamFcltsMngRegistMngVO gamFcltsMngRegistMngVO) {
		return (EgovMap)selectByPk("gamFcltsMngRegistMngDao.selectFcltsMngRegistMngPk_S", gamFcltsMngRegistMngVO);
	}

	/**
	 * @name selectFcltsMngGroupNm
	 * @param searchVO
	 * @return String
	 */
	public String selectFcltsMngGroupNm(Map searchVO) {
		return (String)selectByPk("gamFcltsMngRegistMngDao.selectFcltsMngGroupNm_S", searchVO);
	}

	/**
	 * @name selectFcltsMngRegistMngQcMngDtlsPlanList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltsMngRegistMngQcMngDtlsPlanList(GamFcltsMngRegistMngQcMngDtlsVO searchVO) {
		return list("gamFcltsMngRegistMngDao.selectFcltsMngRegistMngQcMngDtlsPlanList_D", searchVO);
	}

	/**
	 * @name selectFcltsMngRegistMngQcMngDtlsHistList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltsMngRegistMngQcMngDtlsHistList(GamFcltsMngRegistMngQcMngDtlsVO searchVO) {
		return list("gamFcltsMngRegistMngDao.selectFcltsMngRegistMngQcMngDtlsHistList_D", searchVO);
	}

	/**
	 * @name selectFcltsMngRegistMngMntnRprDtlsPlanList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltsMngRegistMngMntnRprDtlsPlanList(GamFcltsMngRegistMngMntnRprDtlsVO searchVO) {
		return list("gamFcltsMngRegistMngDao.selectFcltsMngRegistMngMntnRprDtlsPlanList_D", searchVO);
	}

	/**
	 * @name selectFcltsMngRegistMngMntnRprDtlsHistList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltsMngRegistMngMntnRprDtlsHistList(GamFcltsMngRegistMngMntnRprDtlsVO searchVO) {
		return list("gamFcltsMngRegistMngDao.selectFcltsMngRegistMngMntnRprDtlsHistList_D", searchVO);
	}

	/**
	 * @name selectFcltsMngRegistMngPkHwp
	 * @param gamFcltsMngRegistMngVO
	 * @return EgovMap
	 */
	public EgovMap selectFcltsMngRegistMngPkHwp(GamFcltsMngRegistMngVO gamFcltsMngRegistMngVO) {
		return (EgovMap)selectByPk("gamFcltsMngRegistMngDao.selectFcltsMngRegistMngPkHwp_S", gamFcltsMngRegistMngVO);
	}
	
}
