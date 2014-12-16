/**
 *
 */
package egovframework.rte.ygpa.gam.mngFee.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngVo;
import egovframework.rte.ygpa.gam.mngFee.service.GamFcltsMngFeeMngDetailVo;

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
	 * @param searchVO
	 * @return
	 */
	public EgovMap selectFcltsMngFeeMngPk(GamFcltsMngFeeMngVo searchVO) {
		return (EgovMap)selectByPk("gamFcltsMngFeeMngDao.selectFcltsMngFeeMngPk_S", searchVO);
	}

	/**
	 * @param gamFcltsMngFeeMngVo
	 * @return
	 */
	public List selectFcltsMngFeeMngMonthCntList(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) {
		return list("gamFcltsMngFeeMngDao.selectFcltsMngFeeMngMonthCntList_S", gamFcltsMngFeeMngVo);
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

	/**
	 * @param vo
	 * @return
	 */
	public EgovMap selectMngFeeLevRequestFByPk(Map<String, Object> vo) throws Exception {
		return (EgovMap)selectByPk("gamFcltsMngFeeMngDao.selectMngFeeLevRequestFByPk_S", vo);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public List selectFcltsMngFeeMngPrintNoticeIssueList(Map searchVO) {
		return list("gamFcltsMngFeeMngDao.selectFcltsMngFeeMngPrintNoticeIssueList_D", searchVO);
	}

	/**
	 * @param gamFcltsMngFeeMngDetailVo
	 */
	public void insertMngFeeLevReqestF(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) {
		insert("gamFcltsMngFeeMngDao.insertMngFeeLevReqestF_S", gamFcltsMngFeeMngDetailVo);
	}

	/**
	 * @param gamFcltsMngFeeMngDetailVo
	 */
	public void updateMngFeeLevReqestF(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) {
		update("gamFcltsMngFeeMngDao.updateMngFeeLevReqestF_S", gamFcltsMngFeeMngDetailVo);
	}

	/**
	 * @param vo
	 */
	public void updateMngFeeLevReqestFNticIssue(Map<String, Object> vo) {
		update("gamFcltsMngFeeMngDao.updateMngFeeLevReqestFNticIssue_S", vo);
	}

	/**
	 * @param vo
	 */
	public void updateMngFeeLevReqestFNhtPrintYn(Map<String, Object> vo) {
		update("gamFcltsMngFeeMngDao.updateMngFeeLevReqestFNhtPrintYn_S", vo);
	}

	/**
	 * @param gamFcltsMngFeeMngDetailVo
	 */
	public void deleteMngFeeLevReqestF(GamFcltsMngFeeMngDetailVo gamFcltsMngFeeMngDetailVo) {
		delete("gamFcltsMngFeeMngDao.deleteMngFeeLevReqestF_S", gamFcltsMngFeeMngDetailVo);
	}

	/**
	 * @param gamFcltsMngFeeMngDetailVo
	 */
	public void deleteAllMngFeeLevReqestF(GamFcltsMngFeeMngVo gamFcltsMngFeeMngVo) {
		delete("gamFcltsMngFeeMngDao.deleteAllMngFeeLevReqestF_S", gamFcltsMngFeeMngVo);
	}

	/**
	 * @param vo
	 * @return
	 */
	public EgovMap selectRevCollF(Map<String, Object> vo) throws Exception {
		return (EgovMap)selectByPk("gamFcltsMngFeeMngDao.selectRevCollF_S", vo);
	}

	/**
	 * @param vo
	 */
	public void insertRevCollF(Map<String, Object> vo) {
		insert("gamFcltsMngFeeMngDao.insertRevCollF_S", vo);
	}

	/**
	 * @param vo
	 */
	public void updateRevCollFBillPrtYn(Map<String, Object> vo) {
		update("gamFcltsMngFeeMngDao.updateRevCollFBillPrtYn_S", vo);
	}

	/**
	 * @param vo
	 */
	public void deleteRevCollF(Map<String, Object> vo) {
		delete("gamFcltsMngFeeMngDao.deleteRevCollF_S", vo);
	}

	/**
	 * @param vo
	 */
	public void updateUnpaidFDlyBillPrtYn(Map<String, Object> vo) {
		update("gamFcltsMngFeeMngDao.updateUnpaidFDlyBillPrtYn_S", vo);
	}

	/**
	 * @param vo
	 * @return
	 */
	public EgovMap selectEgiroMaxInfo(Map<String, Object> vo) throws Exception {
		return (EgovMap)selectByPk("gamFcltsMngFeeMngDao.selectEgiroMaxInfo_S", vo);
	}

	/**
	 * @param vo
	 * @return
	 */
	public String insertEgiro(Map<String, Object> vo) throws Exception {
		return (String)insert("gamFcltsMngFeeMngDao.insertEgiro_S", vo);
	}

	/**
	 * @param vo
	 */
	public void updateEgiroCancel(Map<String, Object> vo) throws Exception {
		update("gamFcltsMngFeeMngDao.updateEgiroCancel_S", vo);
	}

	/**
	 * @param vo
	 */
	public void copyFcltsMngFeeF(Map<String, Object> vo) {
		delete("gamFcltsMngFeeMngDao.copyFcltsMngFeeF_S", vo);
	}

	/**
	 * @param vo
	 */
	public void copyFcltsMngFeeDetailF(Map<String, Object> vo) {
		delete("gamFcltsMngFeeMngDao.copyFcltsMngFeeDetailF_S", vo);
	}

	/**
	 * @param vo
	 */
	public void copyMngFeeLevReqestF(Map<String, Object> vo) {
		delete("gamFcltsMngFeeMngDao.copyMngFeeLevReqestF_S", vo);
	}

	/**
	 * @param vo
	 * @return
	 */
	public List selectFcltsMngFeeMngChartList(Map<String, Object> vo) {
		return list("gamFcltsMngFeeMngDao.selectFcltsMngFeeMngChartList_D", vo);
	}

}
