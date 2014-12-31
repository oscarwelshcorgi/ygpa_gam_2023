/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.mngFee.service.GamElctyEquipCapaMngVo;

/**
 *
 * @author ACEWOLF
 * @since 2014. 12. 31.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 12. 31.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamElctyEquipCapaMngDao")
public class GamElctyEquipCapaMngDao extends YGPAAbstractDAO {

	/**
	 * @param searchVO
	 * @return
	 */
	public GamElctyEquipCapaMngVo selectElctyEquipCapaMngListTotCnt(GamElctyEquipCapaMngVo searchVO) {
		return (GamElctyEquipCapaMngVo)selectByPk("gamElctyEquipCapaMngDao.selectElctyEquipCapaMngListTotCnt_S", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectElctyEquipCapaMngList(GamElctyEquipCapaMngVo searchVO) {
		return list("gamElctyEquipCapaMngDao.selectElctyEquipCapaMngList_D", searchVO);
	}

	/**
	 * @param gamElctyEquipCapaMngVo
	 * @return
	 */
	public EgovMap selectElctyEquipCapaMngPk(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) {
		return (EgovMap)selectByPk("gamElctyEquipCapaMngDao.selectElctyEquipCapaMngPk_S", gamElctyEquipCapaMngVo);
	}

	/**
	 * @param gamElctyEquipCapaMngVo
	 * @return
	 */
	public List selectElctyEquipCapaMngChartList(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) {
		return list("gamElctyEquipCapaMngDao.selectElctyEquipCapaMngChartList_D", gamElctyEquipCapaMngVo);
	}

	/**
	 * @param gamElctyEquipCapaMngVo
	 * @return
	 */
	public EgovMap selectElctyEquipCapaMngPrevYearCapa(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) {
		return (EgovMap)selectByPk("gamElctyEquipCapaMngDao.selectElctyEquipCapaMngPrevYearCapa_S", gamElctyEquipCapaMngVo);
	}

	/**
	 * @param gamElctyEquipCapaMngVo
	 * @return
	 */
	public List selectElctyEquipCapaMngYearCntList(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) {
		return list("gamElctyEquipCapaMngDao.selectElctyEquipCapaMngYearCntList_S", gamElctyEquipCapaMngVo);
	}

	/**
	 * @param gamElctyEquipCapaMngVo
	 * @return
	 */
	public String selectElctyEquipCapaMngNewMngSeq(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) {
		return (String)selectByPk("gamElctyEquipCapaMngDao.selectElctyEquipCapaMngMaxMngSeq_S", gamElctyEquipCapaMngVo);
	}

	/**
	 * @param gamElctyEquipCapaMngVo
	 */
	public void insertElctyEquipCapaMng(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) {
		insert("gamElctyEquipCapaMngDao.insertElctyEquipCapaMng_S", gamElctyEquipCapaMngVo);
	}

	/**
	 * @param gamElctyEquipCapaMngVo
	 */
	public void updateElctyEquipCapaMng(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) {
		update("gamElctyEquipCapaMngDao.updateElctyEquipCapaMng_S", gamElctyEquipCapaMngVo);
	}

	/**
	 * @param gamElctyEquipCapaMngVo
	 */
	public void deleteElctyEquipCapaMng(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) {
		delete("gamElctyEquipCapaMngDao.deleteElctyEquipCapaMng_S", gamElctyEquipCapaMngVo);
	}

	/**
	 * @param gamElctyEquipCapaMngVo
	 */
	public void copyElctyEquipCapaMng(GamElctyEquipCapaMngVo gamElctyEquipCapaMngVo) {
		insert("gamElctyEquipCapaMngDao.copyElctyEquipCapaMng_S", gamElctyEquipCapaMngVo);
	}

}
