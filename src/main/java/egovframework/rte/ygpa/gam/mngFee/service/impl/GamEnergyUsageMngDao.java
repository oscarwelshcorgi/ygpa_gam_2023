/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.mngFee.service.GamEnergyUsageMngVo;

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
	public int selectEnergyUsageMngListTotCnt(GamEnergyUsageMngVo gamEnergyUsageMngVo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamEnergyUsageMngDao.selectEnergyUsageMngListTotCnt_S", gamEnergyUsageMngVo);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectEnergyUsageMngList(GamEnergyUsageMngVo gamEnergyUsageMngVo) {
		return list("gamEnergyUsageMngDao.selectEnergyUsageMngList_D", gamEnergyUsageMngVo);	}

	/**
	 * @param gamEnergyUsageMngVo
	 */
	public void InsertEnergyUsageMng(GamEnergyUsageMngVo gamEnergyUsageMngVo) {
		insert("gamEnergyUsageMngDao.InsertEnergyUsageMng",gamEnergyUsageMngVo);
	}

	/**
	 * @param gamEnergyUsageMngVo
	 */
	public void DeleteEnergyUsageMng(GamEnergyUsageMngVo gamEnergyUsageMngVo) {
		delete("gamEnergyUsageMngDao.DeleteEnergyUsageMng",gamEnergyUsageMngVo);
	}

}
