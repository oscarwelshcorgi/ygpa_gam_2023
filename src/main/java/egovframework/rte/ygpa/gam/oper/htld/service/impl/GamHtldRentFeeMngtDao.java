package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeeMngtVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeDefaultVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFeeMngtVO;

/**
 * @Class Name : GamHtldRentFeeMngtDao.java
 * @Description : 배후단지임대료관리 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Repository("gamHtldRentFeeMngtDao")
public class GamHtldRentFeeMngtDao extends YGPAAbstractDAO {

	/**
	 * 배후단지임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
    public List selectHtldRentFeeMngtList(GamHtldRentFeeDefaultVO searchVO) throws Exception {
        return list("gamHtldRentFeeMngtDao.selectHtldRentFeeMngtList_D", searchVO);
    }

    /**
	 * 배후단지임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldRentFeeMngtListTotCnt(GamHtldRentFeeDefaultVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentFeeMngtDao.selectHtldRentFeeMngtListTotCnt_S", searchVO);
    }

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public EgovMap selectHtldRentFeeMngtSum(GamHtldRentFeeDefaultVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamHtldRentFeeMngtDao.selectHtldRentFeeMngtSum_S", searchVO);
	}

	/**
	 * 배후단지임대관리정보를 수정한다.
	 * @param vo GamHtldRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateHtldRentFeeMngt(GamHtldRentFeeMngtVO vo){
		update("gamHtldRentFeeMngtDao.updateHtldRentFeeMngt_S", vo);
	}

	/**
	 * 배후단지임대관리정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리정보
	 * @exception Exception
	 */
	public GamHtldRentFeeMngtVO selectHtldRentFeeMngtInfo(GamHtldRentFeeMngtVO searchVO) throws Exception {
		return (GamHtldRentFeeMngtVO) selectByPk("gamHtldRentFeeMngtDao.selectHtldRentFeeMngtInfo_S", searchVO);
	}


	/**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 갯수
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamHtldRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentFeeMngtDao.selectAnlrveLevCnt_S", searchVO);
    }

	/**
	 * 세입징수를 등록한다.
	 * @param vo GamHtldRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamHtldRentFeeMngtVO vo){
		insert("gamHtldRentFeeMngtDao.insertAnlrveLev_S", vo);
	}

	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamHtldRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteHtldRentFeeMngt(GamHtldRentFeeMngtVO vo){
		delete("gamHtldRentFeeMngtDao.deleteHtldRentFeeMngt_S", vo);
	}

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamHtldRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertHtldRentFeeMngtLevReqest(GamHtldRentFeeMngtVO vo){
		insert("gamHtldRentFeeMngtDao.insertHtldRentFeeMngtLevReqest_S", vo);
	}

	public List selectNpticPrintInfo(Map searchVO) throws Exception {
        return list("gamHtldRentFeeMngtDao.selectNticPrintFeeList_D", searchVO);
	}


	public List selectNticPrintMaster(Map searchVO) throws Exception  {
		return list("gamHtldRentFeeMngtDao.selectNticPrintMaster_D", searchVO);
	}

	public List selectNticPrintDetail(Map searchVO) throws Exception {
        return list("gamHtldRentFeeMngtDao.selectNticPrintDetail_D", searchVO);
	}

	public List selectTaxNtcPrintInfo(Map searchVO) throws Exception {
        return list("gamHtldRentFeeMngtDao.selectTaxNticPrintFeeList_D", searchVO);
	}

	public void updateAssetRentFeeMngtListDetail(GamHtldRentFeeMngtVO vo)
			throws Exception {
		update("gamHtldRentFeeMngtDao.updateAssetRentFeeMngtListDetail_S", vo);
	}

	public List selectAssetRentFeeDetailList(GamHtldRentFeeMngtVO searchVO) {
		return list("gamHtldRentFeeMngtDao.selectAssetRentFeeDetailList_D", searchVO);
	}

	public Map selectAssetRentFeeDetailMstPk(GamHtldRentFeeMngtVO searchVO) {
		return (Map) selectByPk("gamHtldRentFeeMngtDao.selectAssetRentFeeDetailMstPk_S", searchVO);
	}

	public Map selectAssetRentFeeDetailSumPk(GamHtldRentFeeMngtVO searchVO) {
		return (Map) selectByPk("gamHtldRentFeeMngtDao.selectAssetRentFeeDetailSumPk_S", searchVO);
	}

	public List selectCofixIntrrateList(GamHtldRentFeeMngtVO searchVO) {
		return list("gamHtldRentFeeMngtDao.selectCofixIntrrateList_D", searchVO);
	}

	public Map selectNoticeRequest(GamHtldRentFeeMngtVO searchVO) {
		return (Map) selectByPk("gamHtldRentFeeMngtDao.selectNoticeRequest_S", searchVO);
	}

	public Map selectNoticeRequestPk(GamHtldRentFeeMngtVO searchVO) {
		return (Map) selectByPk("gamHtldRentFeeMngtDao.selectNoticeRequestPk_S", searchVO);
	}

    public int selectInsertHtldRentFeeCnt(GamHtldRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentFeeMngtDao.selectInsertHtldRentFee_S", searchVO);
    }

    public void insertHtldRentFee(GamHtldRentFeeMngtVO vo){
		insert("gamHtldRentFeeMngtDao.insertHtldRentFee_S", vo);
	}

	public void updateHtldRentFee(GamHtldRentFeeMngtVO vo){
		update("gamHtldRentFeeMngtDao.updateHtldRentFee_S", vo);
	}

	public EgovMap selectNticNoAccnutYear(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamHtldRentFeeMngtDao.selectNticNoAccnutYear_S", vo);
    }

	public EgovMap selectIntrNticNoAccnutYear(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamHtldRentFeeMngtDao.selectIntrNticNoAccnutYear_S", vo);
    }

	public EgovMap selectNticRequestRcvdTp(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamHtldRentFeeMngtDao.selectNticRequestRcvdTp_S", vo);
	}

	public void updateLevReqestIssueYn(Map<String, Object> vo) throws Exception {
        update("gamHtldRentFeeMngtDao.updateLevReqestIssueYn", vo);
	}

	public void updateLevReqestPrintState(Map<String, Object> vo) throws Exception {
        update("gamHtldRentFeeMngtDao.updateLevReqestNhtPrintYn", vo);
	}

	public void updateRevCollPrintState(Map<String, Object> vo) throws Exception {
        update("gamHtldRentFeeMngtDao.updateRevCollFBillPrintYn", vo);
	}

	public void updateUnpaidPrintState(Map<String, Object> vo) throws Exception {
        update("gamHtldRentFeeMngtDao.updateUnpaidFBillPrintYn", vo);
	}

	/**
	 * 고지 정보를 전송한다.
	 */
	public String insertNticRequestRevCollF(Map<String, Object> vo) throws Exception {
        return (String)insert("gamHtldRentFeeMngtDao.insertNticRequestRevCollF_S", vo);
    }

	public String insertIntrNticRequestRevCollF(Map<String, Object> vo) throws Exception {
        return (String)insert("gamHtldRentFeeMngtDao.insertIntrNticRequestRevCollF_S", vo);
    }


	public void deleteNticRequestRevCollF(Map<String, Object> vo) throws Exception {
        delete("gamHtldRentFeeMngtDao.deleteNticRequestRevCollF_S", vo);
    }

	public void deleteIntrNticRequestRevCollF(Map<String, Object> vo) throws Exception {
        delete("gamHtldRentFeeMngtDao.deleteIntrNticRequestRevCollF_S", vo);
    }

	/**
	 * 징수의뢰 목록을 삭제한다.
	 * @param vo GamHtldRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteHtldRentFeeMngtList(GamHtldRentFeeMngtVO vo){
		delete("gamHtldRentFeeMngtDao.deleteHtldRentFeeMngtList_S", vo);
	}

	/**
	 * @param vo
	 */
	public void clearHtldRentFeeList(GamHtldRentFeeDefaultVO vo) {
		delete("gamHtldRentFeeMngtDao.clearHtldRentFeeList_D", vo);
	}

}