package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeDefaultVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMngFeeMngtVO;

/**
 * @Class Name : GamHtldMngFeeMngtDao.java
 * @Description : 배후단지관리비관리 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Repository("gamHtldMngFeeMngtDao")
public class GamHtldMngFeeMngtDao extends YGPAAbstractDAO {

	/**
	 * 배후단지임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
    public List selectHtldMngFeeMngtList(GamHtldMngFeeDefaultVO searchVO) throws Exception {
//    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//    	Date dt = format.parse(searchVO.get)

    	List feeList = list("gamHtldMngFeeMngtDao.selectHtldMngFeeMngtList_D", searchVO);

        return feeList;
    }

    /**
	 * 배후단지임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldMngFeeMngtListTotCnt(GamHtldMngFeeDefaultVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldMngFeeMngtDao.selectHtldMngFeeMngtListTotCnt_S", searchVO);
    }

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public EgovMap selectHtldMngFeeMngtSum(GamHtldMngFeeDefaultVO searchVO) throws Exception {
		return (EgovMap) selectByPk("gamHtldMngFeeMngtDao.selectHtldMngFeeMngtSum_S", searchVO);
	}

	/**
	 * 배후단지임대관리정보를 수정한다.
	 * @param vo GamHtldMngFeeMngtVO
	 * @exception Exception
	 */
	public void updateHtldMngFeeMngt(GamHtldMngFeeMngtVO vo){
		update("gamHtldMngFeeMngtDao.updateHtldMngFeeMngt_S", vo);
	}

	/**
	 * 배후단지임대관리정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리정보
	 * @exception Exception
	 */
	public GamHtldMngFeeMngtVO selectHtldMngFeeMngtInfo(GamHtldMngFeeMngtVO searchVO) throws Exception {
		return (GamHtldMngFeeMngtVO) selectByPk("gamHtldMngFeeMngtDao.selectHtldMngFeeMngtInfo_S", searchVO);
	}


	/**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 갯수
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamHtldMngFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldMngFeeMngtDao.selectAnlrveLevCnt_S", searchVO);
    }

	/**
	 * 세입징수를 등록한다.
	 * @param vo GamHtldMngFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamHtldMngFeeMngtVO vo){
		insert("gamHtldMngFeeMngtDao.insertAnlrveLev_S", vo);
	}

	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamHtldMngFeeMngtVO
	 * @exception Exception
	 */
	public void deleteHtldMngFeeMngt(GamHtldMngFeeMngtVO vo){
		delete("gamHtldMngFeeMngtDao.deleteHtldMngFeeMngt_S", vo);
	}

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamHtldMngFeeMngtVO
	 * @exception Exception
	 */
	public void insertHtldMngFeeMngtLevReqest(GamHtldMngFeeMngtVO vo){
		insert("gamHtldMngFeeMngtDao.insertHtldMngFeeMngtLevReqest_S", vo);
	}

	public List selectNpticPrintInfo(Map searchVO) throws Exception {
        return list("gamHtldMngFeeMngtDao.selectNticPrintFeeList_D", searchVO);
	}


	public List selectNticPrintMaster(Map searchVO) throws Exception  {
		return list("gamHtldMngFeeMngtDao.selectNticPrintMaster_D", searchVO);
	}

	public List selectNticPrintDetail(Map searchVO) throws Exception {
        return list("gamHtldMngFeeMngtDao.selectNticPrintDetail_D", searchVO);
	}

	public List selectTaxNtcPrintInfo(Map searchVO) throws Exception {
        return list("gamHtldMngFeeMngtDao.selectTaxNticPrintFeeList_D", searchVO);
	}

	public void updateAssetMngFeeMngtListDetail(GamHtldMngFeeMngtVO vo)
			throws Exception {
		update("gamHtldMngFeeMngtDao.updateAssetMngFeeMngtListDetail_S", vo);
	}

	public List selectAssetMngFeeDetailList(GamHtldMngFeeMngtVO searchVO) {
		return list("gamHtldMngFeeMngtDao.selectAssetMngFeeDetailList_D", searchVO);
	}

	public Map selectAssetMngFeeDetailMstPk(GamHtldMngFeeMngtVO searchVO) {
		return (Map) selectByPk("gamHtldMngFeeMngtDao.selectAssetMngFeeDetailMstPk_S", searchVO);
	}

	public Map selectAssetMngFeeDetailSumPk(GamHtldMngFeeMngtVO searchVO) {
		return (Map) selectByPk("gamHtldMngFeeMngtDao.selectAssetMngFeeDetailSumPk_S", searchVO);
	}

	public List selectCofixIntrrateList(GamHtldMngFeeMngtVO searchVO) {
		return list("gamHtldMngFeeMngtDao.selectCofixIntrrateList_D", searchVO);
	}

	public Map selectNoticeRequest(GamHtldMngFeeMngtVO searchVO) {
		return (Map) selectByPk("gamHtldMngFeeMngtDao.selectNoticeRequest_S", searchVO);
	}

	public Map selectNoticeRequestPk(GamHtldMngFeeMngtVO searchVO) {
		return (Map) selectByPk("gamHtldMngFeeMngtDao.selectNoticeRequestPk_S", searchVO);
	}

    public int selectInsertHtldMngFeeCnt(GamHtldMngFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldMngFeeMngtDao.selectInsertHtldMngFee_S", searchVO);
    }

    public Map selectHtldCofixPk(GamHtldMngFeeMngtVO searchVO) {
    	return (Map) selectByPk("gamHtldMngFeeMngtDao.selectHtldCofix_S", searchVO);
    }

    public void insertHtldMngFee(GamHtldMngFeeMngtVO vo){
		insert("gamHtldMngFeeMngtDao.insertHtldMngFee_S", vo);
	}

	public void updateHtldMngFee(GamHtldMngFeeMngtVO vo){
		update("gamHtldMngFeeMngtDao.updateHtldMngFee_S", vo);
	}

	public EgovMap selectNticNoAccnutYear(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamHtldMngFeeMngtDao.selectNticNoAccnutYear_S", vo);
    }

	public EgovMap selectIntrNticNoAccnutYear(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamHtldMngFeeMngtDao.selectIntrNticNoAccnutYear_S", vo);
    }

	public EgovMap selectNticRequestRcvdTp(Map<String, Object> vo) throws Exception {
        return (EgovMap) selectByPk("gamHtldMngFeeMngtDao.selectNticRequestRcvdTp_S", vo);
	}

	public void updateLevReqestIssueYn(Map<String, Object> vo) throws Exception {
        update("gamHtldMngFeeMngtDao.updateLevReqestIssueYn", vo);
	}

	public void updateLevReqestPrintState(Map<String, Object> vo) throws Exception {
        update("gamHtldMngFeeMngtDao.updateLevReqestNhtPrintYn", vo);
	}

	public void updateRevCollPrintState(Map<String, Object> vo) throws Exception {
        update("gamHtldMngFeeMngtDao.updateRevCollFBillPrintYn", vo);
	}

	public void updateUnpaidPrintState(Map<String, Object> vo) throws Exception {
        update("gamHtldMngFeeMngtDao.updateUnpaidFBillPrintYn", vo);
	}

	/**
	 * 고지 정보를 전송한다.
	 */
	public String insertNticRequestRevCollF(Map<String, Object> vo) throws Exception {
        return (String)insert("gamHtldMngFeeMngtDao.insertNticRequestRevCollF_S", vo);
    }

	public String insertIntrNticRequestRevCollF(Map<String, Object> vo) throws Exception {
        return (String)insert("gamHtldMngFeeMngtDao.insertIntrNticRequestRevCollF_S", vo);
    }


	public void deleteNticRequestRevCollF(Map<String, Object> vo) throws Exception {
        delete("gamHtldMngFeeMngtDao.deleteNticRequestRevCollF_S", vo);
    }

	public void deleteIntrNticRequestRevCollF(Map<String, Object> vo) throws Exception {
        delete("gamHtldMngFeeMngtDao.deleteIntrNticRequestRevCollF_S", vo);
    }

	/**
	 * 징수의뢰 목록을 삭제한다.
	 * @param vo GamHtldMngFeeMngtVO
	 * @exception Exception
	 */
	public void deleteHtldMngFeeMngtList(GamHtldMngFeeMngtVO vo){
		delete("gamHtldMngFeeMngtDao.deleteHtldMngFeeMngtList_S", vo);
	}

	/**
	 * @param vo
	 */
	public void clearHtldMngFeeList(GamHtldMngFeeDefaultVO vo) {
		delete("gamHtldMngFeeMngtDao.clearHtldMngFeeList_D", vo);
	}

}