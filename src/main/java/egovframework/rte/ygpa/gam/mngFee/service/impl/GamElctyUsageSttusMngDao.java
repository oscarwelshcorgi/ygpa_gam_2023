/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
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
		return (Integer)getSqlMapClientTemplate().queryForObject("gamElctyUsageSttusMngDao.selectGasUsageSttusMngListTotCnt_S", gamElctyUsageSttusMngVo);
	}

	/**
	 * @param gamElctyUsageSttusMngVo
	 * @return
	 */
	public List selectElctyUsageSttusMngList(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) {
		return list("gamElctyUsageSttusMngDao.selectGasUsageSttusMngList_D", gamElctyUsageSttusMngVo);
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
	public void deleteElctyUsageSttusMng(GamElctyUsageSttusMngVo gamElctyUsageSttusMngVo) {
		delete("gamElctyUsageSttusMngDao.deleteElctyUsageSttusMng_D",gamElctyUsageSttusMngVo);
	}

}
