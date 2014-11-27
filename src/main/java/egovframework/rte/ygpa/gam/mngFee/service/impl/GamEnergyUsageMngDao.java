/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.mngFee.service.GamElctyUsageSttusMngVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamEnergyUsageMngVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamGasUsageSttusMngVo;

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
@Repository("gamEnergyUsageMngDao")
public class GamEnergyUsageMngDao extends YGPAAbstractDAO{

	/**
	 * @param searchVO
	 * @return
	 */
	public int selectEnergyUsageMngListTotCnt(GamEnergyUsageMngVo searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamEnergyUsageMngDao.selectEnergyUsageMngListTotCnt_S", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectEnergyUsageMngList(GamEnergyUsageMngVo searchVO) {
		return list("gamEnergyUsageMngDao.selectEnergyUsageMngList_D", searchVO);	}

	/**
	 * @param gamEnergyUsageMngVo
	 * @return
	 */
	public List selectEnergyUsageMngChartList(GamEnergyUsageMngVo gamEnergyUsageMngVo) {
		return list("gamEnergyUsageMngDao.selectEnergyUsageMngChartList_D", gamEnergyUsageMngVo);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectEnergyUsageMngYearCntList(GamEnergyUsageMngVo searchVO) {
		return list("gamEnergyUsageMngDao.selectEnergyUsageMngYearCntList_S", searchVO);
	}

	/**
	 * @param gamEnergyUsageMngVo
	 */
	public void insertEnergyUsageMng(GamEnergyUsageMngVo gamEnergyUsageMngVo) {
		insert("gamEnergyUsageMngDao.insertEnergyUsageMng_S",gamEnergyUsageMngVo);
	}

	/**
	 * @param gamEnergyUsageMngVo
	 */
	public void updateEnergyUsageMng(GamEnergyUsageMngVo gamEnergyUsageMngVo) {
		update("gamEnergyUsageMngDao.updateEnergyUsageMng_S",gamEnergyUsageMngVo);
	}

	/**
	 * @param gamEnergyUsageMngVo
	 */
	public void deleteEnergyUsageMng(GamEnergyUsageMngVo gamEnergyUsageMngVo) {
		delete("gamEnergyUsageMngDao.deleteEnergyUsageMng_S",gamEnergyUsageMngVo);
	}

	/**
	 * @param gamEnergyUsageMngVo
	 */
	public void copyEnergyUsageMng(GamEnergyUsageMngVo gamEnergyUsageMngVo) {
		insert("gamEnergyUsageMngDao.copyEnergyUsageMng_S",gamEnergyUsageMngVo);
	}

}
