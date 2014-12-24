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
	 * @param searchVO
	 * @return
	 */
	public EgovMap selectFcltsFeeMngNticPk(GamFcltsFeeMngNticVo searchVO) {
		return (EgovMap)selectByPk("gamFcltsFeeMngNticDao.selectFcltsFeeMngNticPk_S", searchVO);
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
	 * @return
	 */
	public String selectFcltsFeeMngNticMaxReqestSeq(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) {
		return (String)getSqlMapClientTemplate().queryForObject("gamFcltsFeeMngNticDao.selectFcltsFeeMngNticMaxReqestSeq_S", gamFcltsFeeMngNticVo);
	}

	/**
	 * @param searchVO
	 * @return
	 */
	public String selectEntrpsNm(Map searchVO) {
		return (String)getSqlMapClientTemplate().queryForObject("gamFcltsFeeMngNticDao.selectEntrpsNm_S", searchVO);
	}

	/**
	 * @param vo
	 */
	public void insertFcltsFeeMngNtic(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) {
		insert("gamFcltsFeeMngNticDao.insertFcltsFeeMngNtic_S", gamFcltsFeeMngNticVo);
	}

	/**
	 * @param vo
	 */
	public void updateFcltsFeeMngNtic(GamFcltsFeeMngNticVo gamFcltsFeeMngNticVo) {
		update("gamFcltsFeeMngNticDao.updateFcltsFeeMngNtic_S", gamFcltsFeeMngNticVo);
	}

	/**
	 * @param vo
	 */
	public void updateFcltsFeeMngNticIssue(Map<String, Object> vo) {
		update("gamFcltsFeeMngNticDao.updateFcltsFeeMngNticIssue_S", vo);
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
	 * @param vo
	 * @return
	 */
	public EgovMap selectRevCollF(Map<String, Object> vo) throws Exception {
		return (EgovMap)selectByPk("gamFcltsFeeMngNticDao.selectRevCollF_S", vo);
	}

	/**
	 * @param vo
	 */
	public void insertRevCollF(Map<String, Object> vo) {
		insert("gamFcltsFeeMngNticDao.insertRevCollF_S", vo);
	}

	/**
	 * @param vo
	 */
	public void updateRevCollFBillPrtYn(Map<String, Object> vo) {
		update("gamFcltsFeeMngNticDao.updateRevCollFBillPrtYn_S", vo);
	}

	/**
	 * @param vo
	 */
	public void deleteRevCollF(Map<String, Object> vo) {
		delete("gamFcltsFeeMngNticDao.deleteRevCollF_S", vo);
	}

	/**
	 * @param vo
	 * @return
	 */
	public EgovMap selectEgiroMaxInfo(Map<String, Object> vo) throws Exception {
		return (EgovMap)selectByPk("gamFcltsFeeMngNticDao.selectEgiroMaxInfo_S", vo);
	}

	/**
	 * @param vo
	 * @return
	 */
	public String insertEgiro(Map<String, Object> vo) throws Exception {
		return (String)insert("gamFcltsFeeMngNticDao.insertEgiro_S", vo);
	}

	/**
	 * @param vo
	 */
	public void updateEgiroCancel(Map<String, Object> vo) throws Exception {
		update("gamFcltsFeeMngNticDao.updateEgiroCancel_S", vo);
	}

	/**
	 * @param vo
	 * @return
	 */
	public String selectRevCollMagamFMagamCheck(Map<String, Object> vo) {
		return (String)getSqlMapClientTemplate().queryForObject("gamFcltsFeeMngNticDao.selectRevCollMagamFMagamCheck_S", vo);
	}

	/**
	 * @param vo
	 * @return
	 */
	public String selectUnpaidFMaxDlySerNo(Map<String, Object> vo) {
		return (String)getSqlMapClientTemplate().queryForObject("gamFcltsFeeMngNticDao.selectUnpaidFMaxDlySerNo_S", vo);
	}

	/**
	 * @param vo
	 * @return
	 */
	public EgovMap calcDlyBillAmnt(Map<String, Object> vo) throws Exception {
		return (EgovMap)selectByPk("gamFcltsFeeMngNticDao.calcDlyBillAmnt_S", vo);
	}

	/**
	 * @param vo
	 * @return
	 */
	public EgovMap selectUnpaidF(Map<String, Object> vo) throws Exception {
		return (EgovMap)selectByPk("gamFcltsFeeMngNticDao.selectUnpaidF_S", vo);
	}

	/**
	 * @param vo
	 */
	public void insertUnpaidF(Map<String, Object> vo) {
		insert("gamFcltsFeeMngNticDao.insertUnpaidF_S", vo);
	}

	/**
	 * @param vo
	 */
	public void updateUnpaidFDlyBillPrtYn(Map<String, Object> vo) {
		update("gamFcltsFeeMngNticDao.updateUnpaidFDlyBillPrtYn_S", vo);
	}

	/**
	 * @param vo
	 */
	public void deleteUnpaidF(Map<String, Object> vo) {
		insert("gamFcltsFeeMngNticDao.deleteUnpaidF_S", vo);
	}

	/**
	 * @param vo
	 */
	public void updateFcltsFeeMngNticArrrgInfo(Map<String, Object> vo) {
		insert("gamFcltsFeeMngNticDao.updateFcltsFeeMngNticArrrgInfo_S", vo);
	}

}
