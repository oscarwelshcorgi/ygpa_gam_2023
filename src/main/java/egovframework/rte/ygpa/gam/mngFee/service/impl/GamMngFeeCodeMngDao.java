/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.mngFee.service.GamElctyUsageSttusMngVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeCodeMngVo;

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
@Repository("gamMngFeeCodeMngDao")
public class GamMngFeeCodeMngDao extends YGPAAbstractDAO{

	/**
	 * @param searchVO
	 * @return
	 */
	public int selectMngFeeCodeMngListTotCnt(GamMngFeeCodeMngVo searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamMngFeeCodeMngDao.selectMngFeeCodeMngListTotCnt_S", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectMngFeeCodeMngList(GamMngFeeCodeMngVo searchVO) {
		return list("gamMngFeeCodeMngDao.selectMngFeeCodeMngList_D", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectMngFeeFcltySeMngList(Map searchVO) {
		return list("gamMngFeeCodeMngDao.selectMngFeeFcltySeMngList_D", searchVO);
	}

	/**
	 * @param gamMngFeeCodeMngVo
	 * @return
	 */
	public String selectMngFeeCodeMngMaxFcltyCd(GamMngFeeCodeMngVo gamMngFeeCodeMngVo) {
		return (String)getSqlMapClientTemplate().queryForObject("gamMngFeeCodeMngDao.selectMngFeeCodeMngMaxFcltyCd_S", gamMngFeeCodeMngVo);
	}

	/**
	 * @param gamMngFeeCodeMngVo
	 */
	public void insertMngFeeCodeMng(GamMngFeeCodeMngVo gamMngFeeCodeMngVo) {
		insert("gamMngFeeCodeMngDao.insertMngFeeCodeMng_S",gamMngFeeCodeMngVo);
	}

	/**
	 * @param gamMngFeeCodeMngVo
	 */
	public void updateMngFeeCodeMng(GamMngFeeCodeMngVo gamMngFeeCodeMngVo) {
		update("gamMngFeeCodeMngDao.updateMngFeeCodeMng_S",gamMngFeeCodeMngVo);
	}

	/**
	 * @param gamMngFeeCodeMngVo
	 */
	public void deleteMngFeeCodeMng(GamMngFeeCodeMngVo gamMngFeeCodeMngVo) {
		delete("gamMngFeeCodeMngDao.deleteMngFeeCodeMng_S",gamMngFeeCodeMngVo);
	}

}
