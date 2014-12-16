/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.mngFee.service.GamElctyUsageSttusMngVo;

/**
 *
 * @author Lee
 * @since 2014. 10. 24.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 24.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamElctyUsageSttusMngDao")
public class GamElctyUsageSttusMngDao extends YGPAAbstractDAO{

	/**
	 * @param gamElctyUsageSttusMngVo
	 * @return
	 */
	public int selectElctyUsageSttusMngListTotCnt(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamElctyUsageSttusMngDao.selectElctyUsageSttusMngListTotCnt_S", gamElctyUsageSttusMngVo);
	}

	/**
	 * @param gamElctyUsageSttusMngVo
	 * @return
	 */
	public List selectElctyUsageSttusMngList(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) {
		return list("gamElctyUsageSttusMngDao.selectElctyUsageSttusMngList_D", gamElctyUsageSttusMngVo);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public EgovMap selectElctyUsageSttusMngPk(GamElctyUsageSttusMngVo searchVO) {
		return (EgovMap)selectByPk("gamElctyUsageSttusMngDao.selectElctyUsageSttusMngPk_S", searchVO);
	}

	/**
	 * @param gamElctyUsageSttusMngVo
	 * @return
	 */
	public List selectElctyUsageSttusMngChartList(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) {
		return list("gamElctyUsageSttusMngDao.selectElctyUsageSttusMngChartList_D", gamElctyUsageSttusMngVo);
	}

	/**
	 * @param gamElctyUsageSttusMngVo
	 * @return
	 */
	public String selectElctyUsageSttusMngPrevMtUsageQy(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) {
		return (String)getSqlMapClientTemplate().queryForObject("gamElctyUsageSttusMngDao.selectElctyUsageSttusMngPrevMtUsageQy_S", gamElctyUsageSttusMngVo);
	}

	/**
	 * @param gamElctyUsageSttusMngVo
	 * @return
	 */
	public List selectElctyUsageSttusMngMonthCntList(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) {
		return list("gamElctyUsageSttusMngDao.selectElctyUsageSttusMngMonthCntList_S", gamElctyUsageSttusMngVo);
	}

	/**
	 * @param gamElctyUsageSttusMngVo
	 */
	public void insertElctyUsageSttusMng(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) {
		insert("gamElctyUsageSttusMngDao.insertElctyUsageSttusMng_S",gamElctyUsageSttusMngVo);
	}

	/**
	 * @param gamElctyUsageSttusMngVo
	 */
	public void updateElctyUsageSttusMng(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) {
		update("gamElctyUsageSttusMngDao.updateElctyUsageSttusMng_S",gamElctyUsageSttusMngVo);
	}

	/**
	 * @param gamElctyUsageSttusMngVo
	 */
	public void deleteElctyUsageSttusMng(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) {
		delete("gamElctyUsageSttusMngDao.deleteElctyUsageSttusMng_S",gamElctyUsageSttusMngVo);
	}

	/**
	 * @param gamElctyUsageSttusMngVo
	 */
	public void copyElctyUsageSttusMng(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) {
		insert("gamElctyUsageSttusMngDao.copyElctyUsageSttusMng_S",gamElctyUsageSttusMngVo);
	}

}
