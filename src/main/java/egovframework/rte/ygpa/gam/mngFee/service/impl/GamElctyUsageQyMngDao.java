/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.mngFee.service.GamElctyUsageQyMngVO;

/**
 *
 * @author ACEWOLF
 * @since 2015. 2. 7.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2015. 2. 7.		ACEWOLF		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */

@Repository("gamElctyUsageQyMngDao")
public class GamElctyUsageQyMngDao extends YGPAAbstractDAO {

	/**
	 * @name selectElctyUsageQyMngList
	 * @param searchVO
	 * @return List
	 */
	public List selectElctyUsageQyMngList(GamElctyUsageQyMngVO searchVO) {
		return list("gamElctyUsageQyMngDao.selectElctyUsageQyMngList_D", searchVO);
	}

	/**
	 * @name insertElctyUsageQyMng
	 * @param gamElctyUsageQyMngVO
	 * @return void
	 */
	public void insertElctyUsageQyMng(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) {
		insert("gamElctyUsageQyMngDao.insertElctyUsageQyMng_S", gamElctyUsageQyMngVO);
	}

	/**
	 * @name updateElctyUsageQyMng
	 * @param gamElctyUsageQyMngVO
	 * @return void
	 */
	public void updateElctyUsageQyMng(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) {
		update("gamElctyUsageQyMngDao.updateElctyUsageQyMng_S", gamElctyUsageQyMngVO);
	}

	/**
	 * @name deleteElctyUsageQyMng
	 * @param gamElctyUsageQyMngVO
	 * @return void
	 */
	public void deleteElctyUsageQyMng(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) {
		delete("gamElctyUsageQyMngDao.deleteElctyUsageQyMng_S", gamElctyUsageQyMngVO);
	}

	/**
	 * @name selectElctyUsageQyMngPk
	 * @param gamElctyUsageQyMngVO
	 * @return EgovMap
	 */
	public EgovMap selectElctyUsageQyMngPk(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) {
		return (EgovMap)selectByPk("gamElctyUsageQyMngDao.selectElctyUsageQyMngPk_S", gamElctyUsageQyMngVO);
	}

	/**
	 * @name selectElctyUsageQyMngListSum
	 * @param searchVO
	 * @return GamElctyUsageQyMngVO
	 */
	public GamElctyUsageQyMngVO selectElctyUsageQyMngListSum(GamElctyUsageQyMngVO searchVO) {
		return (GamElctyUsageQyMngVO)selectByPk("gamElctyUsageQyMngDao.selectElctyUsageQyMngListSum_S", searchVO);
	}

	/**
	 * @name selectElctyUsageQyMngChartList
	 * @param gamElctyUsageQyMngVO
	 * @return List
	 */
	public List selectElctyUsageQyMngChartList(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) {
		return list("gamElctyUsageQyMngDao.selectElctyUsageQyMngChartList_D", gamElctyUsageQyMngVO);
	}

	/**
	 * @name selectElctyUsageQyMngMonthChartList
	 * @param gamElctyUsageQyMngVO
	 * @return List
	 */
	public List selectElctyUsageQyMngMonthChartList(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) {
		return list("gamElctyUsageQyMngDao.selectElctyUsageQyMngMonthChartList_D", gamElctyUsageQyMngVO);
	}
	/**
	 * @name selectElctyUsageQyMngMtAmtChart
	 * @param gamElctyUsageQyMngVO
	 * @return List
	 */
	public List selectElctyUsageQyMngMtAmtChart(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) {
		return list("gamElctyUsageQyMngDao.selectElctyUsageQyMngMtAmtChart_D", gamElctyUsageQyMngVO);
	}

	/**
	 * @name selectElctyUsageQyMngYearCntList
	 * @param gamElctyUsageQyMngVO
	 * @return List
	 */
	public List selectElctyUsageQyMngYearCntList(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) {
		return list("gamElctyUsageQyMngDao.selectElctyUsageQyMngYearCntList_S", gamElctyUsageQyMngVO);
	}

	/**
	 * @name copyElctyUsageQyMng
	 * @param gamElctyUsageQyMngVO
	 * @return void
	 */
	public void copyElctyUsageQyMng(GamElctyUsageQyMngVO gamElctyUsageQyMngVO) {
		insert("gamElctyUsageQyMngDao.copyElctyUsageQyMng_S", gamElctyUsageQyMngVO);
	}

	/**
	 * @name selectMngFeeFcltyCdList
	 * @param searchVO
	 * @return List
	 */
	public List selectMngFeeFcltyCdList() {
		return list("gamElctyUsageQyMngDao.selectMngFeeFcltyCdList_D", null);
	}

}
