/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.mngFee.service.GamMngFeeGubunMngVo;

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
@Repository("gamMngFeeGubunMngDao")
public class GamMngFeeGubunMngDao extends YGPAAbstractDAO{

	/**
	 * @param searchVO
	 * @return
	 */
	public int selectMngFeeGubunMngListTotCnt(GamMngFeeGubunMngVo searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamMngFeeGubunMngDao.selectMngFeeGubunMngListTotCnt_S", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectMngFeeGubunMngList(GamMngFeeGubunMngVo searchVO) {
		return list("gamMngFeeGubunMngDao.selectMngFeeGubunMngList_D", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public EgovMap selectMngFeeGubunMngPk(GamMngFeeGubunMngVo searchVO) {
		return (EgovMap)selectByPk("gamMngFeeGubunMngDao.selectMngFeeGubunMngPk_S", searchVO);
	}

	/**
	 * @param gamMngFeeGubunMngVo
	 */
	public void insertMngFeeGubunMng(GamMngFeeGubunMngVo gamMngFeeGubunMngVo) {
		insert("gamMngFeeGubunMngDao.insertMngFeeGubunMng_S", gamMngFeeGubunMngVo);
	}

	/**
	 * @param gamMngFeeGubunMngVo
	 */
	public void updateMngFeeGubunMng(GamMngFeeGubunMngVo gamMngFeeGubunMngVo) {
		update("gamMngFeeGubunMngDao.updateMngFeeGubunMng_S",gamMngFeeGubunMngVo);
	}

	/**
	 * @param gamMngFeeGubunMngVo
	 */
	public void deleteMngFeeGubunMng(GamMngFeeGubunMngVo gamMngFeeGubunMngVo) {
		delete("gamMngFeeGubunMngDao.deleteMngFeeGubunMng_S", gamMngFeeGubunMngVo);
	}

	/**
	 * @param checkSe
	 * @return
	 */
	public int checkSeFeeGubunMng(String checkSe) {
		return (Integer)getSqlMapClientTemplate().queryForObject("gamMngFeeGubunMngDao.checkSeFeeGubunMng_S", checkSe);
	}

}
