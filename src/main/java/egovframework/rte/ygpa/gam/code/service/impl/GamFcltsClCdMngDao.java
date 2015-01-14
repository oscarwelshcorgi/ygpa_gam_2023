/**
 *
 */
package egovframework.rte.ygpa.gam.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.code.service.GamFcltsClCdMngVO;

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

@Repository("gamFcltsClCdMngDao")
public class GamFcltsClCdMngDao extends YGPAAbstractDAO {

	/**
	 * @name selectFcltsClCdMngList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltsClCdMngList(GamFcltsClCdMngVO searchVO) {
		return list("gamFcltsClCdMngDao.selectFcltsClCdMngList_D", searchVO);
	}

	/**
	 * @name insertFcltsClCdMng
	 * @param gamFcltsClCdMngVO
	 * @return void
	 */
	public void insertFcltsClCdMng(GamFcltsClCdMngVO gamFcltsClCdMngVO) {
		insert("gamFcltsClCdMngDao.insertFcltsClCdMng_S", gamFcltsClCdMngVO);
	}

	/**
	 * @name updateFcltsClCdMng
	 * @param gamFcltsClCdMngVO
	 * @return void
	 */
	public void updateFcltsClCdMng(GamFcltsClCdMngVO gamFcltsClCdMngVO) {
		update("gamFcltsClCdMngDao.updateFcltsClCdMng_S", gamFcltsClCdMngVO);
	}

	/**
	 * @name deleteFcltsClCdMng
	 * @param gamFcltsClCdMngVO
	 * @return void
	 */
	public void deleteFcltsClCdMng(GamFcltsClCdMngVO gamFcltsClCdMngVO) {
		delete("gamFcltsClCdMngDao.deleteFcltsClCdMng_S", gamFcltsClCdMngVO);
	}

	/**
	 * @name selectFcltsClCdMngListSum
	 * @param searchVO
	 * @return GamFcltsClCdMngVO
	 */
	public GamFcltsClCdMngVO selectFcltsClCdMngListSum(GamFcltsClCdMngVO searchVO) {
		return (GamFcltsClCdMngVO)selectByPk("gamFcltsClCdMngDao.selectFcltsClCdMngListSum_S", searchVO);
	}

	/**
	 * @name selectFcltsClCdMngPk
	 * @param gamFcltsClCdMngVO
	 * @return EgovMap
	 */
	public EgovMap selectFcltsClCdMngPk(GamFcltsClCdMngVO gamFcltsClCdMngVO) {
		return (EgovMap)selectByPk("gamFcltsClCdMngDao.selectFcltsClCdMngPk_S", gamFcltsClCdMngVO);
	}

	/**
	 * @name selectFcltsClCdMngNewCd
	 * @param gamFcltsClCdMngVO
	 * @return String
	 */
	public String selectFcltsClCdMngNewCd(GamFcltsClCdMngVO gamFcltsClCdMngVO) {
		return (String)selectByPk("gamFcltsClCdMngDao.selectFcltsClCdMngNewCd_S", gamFcltsClCdMngVO);
	}

	/**
	 * @name selectFcltsClCdMngTreeList
	 * @param gamFcltsClCdMngVo
	 * @return List
	 */
	public List selectFcltsClCdMngTreeList(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception {
		return list("gamFcltsClCdMngDao.selectFcltsClCdMngTreeList_D", gamFcltsClCdMngVO);
	}

	/**
	 * @name selectFcltsClCdMngLowerDataCnt
	 * @param gamFcltsClCdMngVo
	 * @return List
	 */
	public List selectFcltsClCdMngLowerDataCnt(GamFcltsClCdMngVO gamFcltsClCdMngVO) {
		return list("gamFcltsClCdMngDao.selectFcltsClCdMngLowerDataCnt_S", gamFcltsClCdMngVO);
	}

	/**
	 * @name updateFcltsClCdMngLeafYn
	 * @param gamFcltsClCdMngVO
	 * @return void
	 */
	public void updateFcltsClCdMngLeafYn(GamFcltsClCdMngVO gamFcltsClCdMngVO) {
		update("gamFcltsClCdMngDao.updateFcltsClCdMngLeafYn_S", gamFcltsClCdMngVO);
	}

	/**
	 * @name deleteFcltsClCdMngLowerData
	 * @param gamFcltsClCdMngVO
	 * @return
	 */
	public void deleteFcltsClCdMngLowerData(GamFcltsClCdMngVO gamFcltsClCdMngVO) throws Exception {
		delete("gamFcltsClCdMngDao.deleteFcltsClCdMngLowerData_S", gamFcltsClCdMngVO);
	}

}
