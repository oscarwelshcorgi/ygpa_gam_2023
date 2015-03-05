/**
 *
 */
package egovframework.rte.ygpa.gam.fclty.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.fclty.service.GamFcltsChargerMngVO;

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

@Repository("gamFcltsChargerMngDao")
public class GamFcltsChargerMngDao extends YGPAAbstractDAO {

	/**
	 * @name selectFcltsChargerMngList
	 * @param searchVO
	 * @return List
	 */
	public List selectFcltsChargerMngList(GamFcltsChargerMngVO searchVO) {
		return list("gamFcltsChargerMngDao.selectFcltsChargerMngList_D", searchVO);
	}

	/**
	 * @name insertFcltsChargerMng
	 * @param gamFcltsChargerMngVO
	 * @return void
	 */
	public void insertFcltsChargerMng(GamFcltsChargerMngVO gamFcltsChargerMngVO) {
		insert("gamFcltsChargerMngDao.insertFcltsChargerMng_S", gamFcltsChargerMngVO);
	}

	/**
	 * @name updateFcltsChargerMng
	 * @param gamFcltsChargerMngVO
	 * @return void
	 */
	public void updateFcltsChargerMng(GamFcltsChargerMngVO gamFcltsChargerMngVO) {
		update("gamFcltsChargerMngDao.updateFcltsChargerMng_S", gamFcltsChargerMngVO);
	}

	/**
	 * @name deleteFcltsChargerMng
	 * @param gamFcltsChargerMngVO
	 * @return void
	 */
	public void deleteFcltsChargerMng(GamFcltsChargerMngVO gamFcltsChargerMngVO) {
		delete("gamFcltsChargerMngDao.deleteFcltsChargerMng_S", gamFcltsChargerMngVO);
	}

	/**
	 * @name selectFcltsChargerMngListSum
	 * @param searchVO
	 * @return GamFcltsChargerMngVO
	 */
	public GamFcltsChargerMngVO selectFcltsChargerMngListSum(GamFcltsChargerMngVO searchVO) {
		return (GamFcltsChargerMngVO)selectByPk("gamFcltsChargerMngDao.selectFcltsChargerMngListSum_S", searchVO);
	}

	/**
	 * @name selectFcltsChargerMngPk
	 * @param gamFcltsChargerMngVO
	 * @return EgovMap
	 */
	public EgovMap selectFcltsChargerMngPk(GamFcltsChargerMngVO gamFcltsChargerMngVO) {
		return (EgovMap)selectByPk("gamFcltsChargerMngDao.selectFcltsChargerMngPk_S", gamFcltsChargerMngVO);
	}

}
