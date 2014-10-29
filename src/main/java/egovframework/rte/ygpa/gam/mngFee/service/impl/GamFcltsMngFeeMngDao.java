/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngVo;

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
	 * @param gamFcltsMngFeeMngVo
	 * @return
	 */
	public int selectFcltsMngFeeMngListTotCnt(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamFcltsMngFeeMngDao.selectFcltsMngFeeMngListTotCnt_S", gamFcltsMngFeeMngVo);
	}

	/**
	 * @param gamFcltsMngFeeMngVo
	 * @return
	 */
	public List selectFcltsMngFeeMngList(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) {
		return list("gamFcltsMngFeeMngDao.selectFcltsMngFeeMngList_D", gamFcltsMngFeeMngVo);
	}

	/**
	 * @param gamFcltsMngFeeMngVo
	 */
	public void insertFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) {
		insert("gamFcltsMngFeeMngDao.insertFcltsMngFeeMng",gamFcltsMngFeeMngVo);
	}

	/**
	 * @param gamFcltsMngFeeMngVo
	 */
	public void deleteFcltsMngFeeMng(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) {
		delete("gamFcltsMngFeeMngDao.deleteFcltsMngFeeMng",gamFcltsMngFeeMngVo);
	}

}