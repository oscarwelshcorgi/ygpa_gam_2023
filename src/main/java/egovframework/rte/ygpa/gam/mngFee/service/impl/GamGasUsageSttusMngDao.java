/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.mngFee.service.GamGasUsageSttusMngVo;

/**
 *
 * @author Lee
 * @since 2014. 10. 22.
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일 		 수정자		 수정내용
 *  -------		--------	---------------------------
 *  2014. 10. 22.		Lee		최초 생성
 *
 * Copyright (C) 2013 by LFIT  All right reserved.
 * </pre>
 */
@Repository("gamGasUsageSttusMngDao")
public class GamGasUsageSttusMngDao extends YGPAAbstractDAO{

	/**
	 * @param searchVO
	 * @return
	 */
	public int selectGasUsageSttusMngListTotCnt(GamGasUsageSttusMngVo searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamGasUsageSttusMngDao.selectGasUsageSttusMngListTotCnt_S", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectGasUsageSttusMngList(GamGasUsageSttusMngVo searchVO) {
		return list("gamGasUsageSttusMngDao.selectGasUsageSttusMngList_D", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectGasUsageSttusMngChartList(GamGasUsageSttusMngVo searchVO) {
		return list("gamGasUsageSttusMngDao.selectGasUsageSttusMngChartList_D", searchVO);
	}

	/**
	 * @param gamElctyUsageSttusMngVo
	 * @return
	 */
	public String selectGasUsageSttusMngPrevMtUsageQy(GamGasUsageSttusMngVo gamGasUsageSttusMngVo) {
		return (String)getSqlMapClientTemplate().queryForObject("gamGasUsageSttusMngDao.selectGasUsageSttusMngPrevMtUsageQy_S", gamGasUsageSttusMngVo);
	}

	/**
	 * @param gamGasUsageSttusMngVo
	 */
	public void insertGasUsageSttusMng(GamGasUsageSttusMngVo gamGasUsageSttusMngVo) {
		insert("gamGasUsageSttusMngDao.insertGasUsageSttusMng_S",gamGasUsageSttusMngVo);
	}

	/**
	 * @param gamGasUsageSttusMngVo
	 */
	public void updateGasUsageSttusMng(GamGasUsageSttusMngVo gamGasUsageSttusMngVo) {
		insert("gamGasUsageSttusMngDao.updateGasUsageSttusMng_S",gamGasUsageSttusMngVo);
	}

	/**
	 * @param gamGasUsageSttusMngVo
	 */
	public void deleteGasUsageSttusMng(GamGasUsageSttusMngVo gamGasUsageSttusMngVo) {
		delete("gamGasUsageSttusMngDao.deleteGasUsageSttusMng_S",gamGasUsageSttusMngVo);
	}

}
