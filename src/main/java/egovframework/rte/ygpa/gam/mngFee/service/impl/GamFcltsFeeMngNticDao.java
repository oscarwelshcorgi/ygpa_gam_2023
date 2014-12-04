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
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsFeeMngNticDetailVo;

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
@Repository("gamFcltsFeeMngNticDao")
public class GamFcltsFeeMngNticDao extends YGPAAbstractDAO{

	/**
	 * @param searchVO
	 * @return
	 */
	public GamFcltsFeeMngNticVo selectFcltsFeeMngNticListTotCnt(GamFcltsFeeMngNticVo searchVO) {
		return (GamFcltsFeeMngNticVo)selectByPk("gamFcltsFeeMngNticDao.selectFcltsFeeMngNticListTotCnt_S", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectFcltsFeeMngNticList(GamFcltsFeeMngNticVo searchVO) {
		return list("gamFcltsFeeMngNticDao.selectFcltsFeeMngNticList_D", searchVO);
	}

	/**
	 * @param vo
	 * @return
	 */
	public EgovMap selectMngFeeLevRequestFByPk(Map<String, Object> vo) throws Exception {
		return (EgovMap)selectByPk("gamFcltsFeeMngNticDao.selectMngFeeLevRequestFByPk_S", vo);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectFcltsFeeMngNticDetailList(GamFcltsFeeMngNticDetailVo searchVO) {
		return list("gamFcltsFeeMngNticDao.selectFcltsFeeMngNticDetailList_D", searchVO);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectFcltsFeeMngNticPrintNoticeIssueList(Map searchVO) {
		return list("gamFcltsFeeMngNticDao.selectFcltsFeeMngNticPrintNoticeIssueList_D", searchVO);
	}

	/**
	 * @param gamFcltsFeeMngNticVo
	 */
	public void insertFcltsFeeMngNtic(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) {
		insert("gamFcltsFeeMngNticDao.insertFcltsFeeMngNtic_S", gamFcltsFeeMngNticVo);
	}

	/**
	 * @param gamFcltsFeeMngNticVo
	 */
	public void updateFcltsFeeMngNtic(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) {
		update("gamFcltsFeeMngNticDao.updateFcltsFeeMngNtic_S", gamFcltsFeeMngNticVo);
	}

	/**
	 * @param vo
	 */
	public void updateFcltsFeeMngNticNhtPrintYn(Map<String, Object> vo) {
		update("gamFcltsFeeMngNticDao.updateFcltsFeeMngNticNhtPrintYn_S", vo);
	}

	/**
	 * @param gamFcltsFeeMngNticVo
	 */
	public void deleteFcltsFeeMngNtic(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) {
		delete("gamFcltsFeeMngNticDao.deleteFcltsFeeMngNtic_S", gamFcltsFeeMngNticVo);
	}

	/**
	 * @param gamFcltsFeeMngNticVo
	 */
	public void insertRevCollF(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) {
		insert("gamFcltsFeeMngNticDao.insertRevCollF_S", gamFcltsFeeMngNticVo);
	}

	/**
	 * @param vo
	 */
	public void updateRevCollFBillPrtYn(Map<String, Object> vo) {
		update("gamFcltsFeeMngNticDao.updateRevCollFBillPrtYn_S", vo);
	}

	/**
	 * @param gamFcltsFeeMngNticVo
	 */
	public void deleteRevCollF(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) {
		delete("gamFcltsFeeMngNticDao.deleteRevCollF_S", gamFcltsFeeMngNticVo);
	}

	/**
	 * @param vo
	 */
	public void updateUnpaidFDlyBillPrtYn(Map<String, Object> vo) {
		update("gamFcltsFeeMngNticDao.updateUnpaidFDlyBillPrtYn_S", vo);
	}

}
