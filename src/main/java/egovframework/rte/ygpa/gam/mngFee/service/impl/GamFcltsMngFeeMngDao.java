/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsFeeMngNticVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngDetailVo;
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
@Repository("gamFcltsMngFeeMngDao")
public class GamFcltsMngFeeMngDao extends YGPAAbstractDAO{

	/**
	 * @param searchVO
	 * @return
	 */
	public GamFcltsMngFeeMngVo selectFcltsMngFeeMngListTotCnt(GamFcltsMngFeeMngVo searchVO) {
		return (GamFcltsMngFeeMngVo)selectByPk("gamFcltsMngFeeMngDao.selectFcltsMngFeeMngListTotCnt_S", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectFcltsMngFeeMngList(GamFcltsMngFeeMngVo searchVO) {
		return list("gamFcltsMngFeeMngDao.selectFcltsMngFeeMngList_D", searchVO);
	}

	/**
	 * @param gamFcltsMngFeeMngVo
	 */
	public void insertFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) {
		insert("gamFcltsMngFeeMngDao.insertFcltsMngFeeMng_S", gamFcltsMngFeeMngVo);
	}

	/**
	 * @param gamFcltsMngFeeMngVo
	 */
	public void updateFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) {
		update("gamFcltsMngFeeMngDao.updateFcltsMngFeeMng_S", gamFcltsMngFeeMngVo);
	}

	/**
	 * @param gamFcltsMngFeeMngVo
	 */
	public void deleteFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) {
		delete("gamFcltsMngFeeMngDao.deleteFcltsMngFeeMng_S", gamFcltsMngFeeMngVo);
	}

	/**
	 * @param gamFcltsMngFeeMngVo
	 */
	public void deleteAllFcltsMngFeeMngDetail(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) {
		delete("gamFcltsMngFeeMngDao.deleteAllFcltsMngFeeMngDetail_S", gamFcltsMngFeeMngVo);
	}

	/**
	 * @param gamFcltsMngFeeMngDetailVo
	 * @return
	 */
	public List selectFcltsMngFeeMngDetailList(GamFcltsMngFeeMngDetailVo searchVO) {
		return list("gamFcltsMngFeeMngDao.selectFcltsMngFeeMngDetailList_D", searchVO);
	}

	/**
	 * @param gamMngFeeCodeMngVo
	 * @return
	 */
	public String selectFcltsMngFeeMngDetailMaxMngSeq(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) {
		return (String)getSqlMapClientTemplate().queryForObject("gamFcltsMngFeeMngDao.selectFcltsMngFeeMngDetailMaxMngSeq_S", gamFcltsMngFeeMngDetailVo);
	}

	/**
	 * @param gamFcltsMngFeeMngDetailVo
	 */
	public void insertFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) {
		insert("gamFcltsMngFeeMngDao.insertFcltsMngFeeMngDetail_S", gamFcltsMngFeeMngDetailVo);
	}

	/**
	 * @param gamFcltsMngFeeMngDetailVo
	 */
	public void updateFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) {
		update("gamFcltsMngFeeMngDao.updateFcltsMngFeeMngDetail_S", gamFcltsMngFeeMngDetailVo);
	}

	/**
	 * @param gamFcltsMngFeeMngDetailVo
	 */
	public void deleteFcltsMngFeeMngDetail(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) {
		delete("gamFcltsMngFeeMngDao.deleteFcltsMngFeeMngDetail_S", gamFcltsMngFeeMngDetailVo);
	}

}
