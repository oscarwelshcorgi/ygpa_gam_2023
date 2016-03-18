package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeeMngtVO;

/**
 * @Class Name : GamPrtFcltyRentFeeMngtDao.java
 * @Description : 항만시설사용료관리 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Repository("gamPrtFcltyRentFeeMngtDao")
public class GamPrtFcltyRentFeeMngtDao extends YGPAAbstractDAO {

	/**
	 * 자산임대료고지관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료고지관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyRentFeeMngtList(GamPrtFcltyRentFeeMngtVO searchVO) throws Exception {
        return list("gamPrtFcltyRentFeeMngtDao.selectPrtFcltyRentFeeMngtList_D", searchVO);
    }

    /**
	 * 자산임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료고지관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyRentFeeMngtListTotCnt(GamPrtFcltyRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentFeeMngtDao.selectPrtFcltyRentFeeMngtListTotCnt_S", searchVO);
    }

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료고지관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyRentFeeMngtVO selectPrtFcltyRentFeeMngtSum(GamPrtFcltyRentFeeMngtVO searchVO) throws Exception {
		return (GamPrtFcltyRentFeeMngtVO) selectByPk("gamPrtFcltyRentFeeMngtDao.selectPrtFcltyRentFeeMngtSum_S", searchVO);
	}

	/**
	 * 투자비보전 상계처리한다. 2015.12.17 김종민
	 * @param vo GamPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentFeeSaveSingle(GamPrtFcltyRentFeeMngtVO vo){
		update("gamPrtFcltyRentFeeMngtDao.updatePrtFcltyRentFeeSaveSingle_S", vo);
	}
	
	/**
	 * 투자비보전 상계취소한다.
	 * @param vo GamPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void cancelPrtFcltyRentFeeSaveSingle(GamPrtFcltyRentFeeMngtVO vo){
		update("gamPrtFcltyRentFeeMngtDao.cancelPrtFcltyRentFeeSaveSingle_S", vo);
	}

	/**
	 * 자산임대료고지관리정보를 수정한다.
	 * @param vo GamPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentFeeMngt(GamPrtFcltyRentFeeMngtVO vo){
		update("gamPrtFcltyRentFeeMngtDao.updatePrtFcltyRentFeeMngt_S", vo);
	}

	/**
	 * 사용료를 변경 한다.
	 * @param vo
	 */
	public void updatePrtFcltyRentFee(GamPrtFcltyRentFeeMngtVO vo) {
		update("gamPrtFcltyRentFeeMngtDao.updatePrtFcltyRentFeeModifyFee_S", vo);
	}

	/**
	 * 자산임대료고지관리정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료고지관리정보
	 * @exception Exception
	 */
	public GamPrtFcltyRentFeeMngtVO selectPrtFcltyRentFeeMngtInfo(GamPrtFcltyRentFeeMngtVO searchVO) throws Exception {
		return (GamPrtFcltyRentFeeMngtVO) selectByPk("gamPrtFcltyRentFeeMngtDao.selectPrtFcltyRentFeeMngtInfo_S", searchVO);
	}


	/**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 갯수
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamPrtFcltyRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentFeeMngtDao.selectAnlrveLevCnt_S", searchVO);
    }

	/**
	 * 세입징수를 등록한다.
	 * @param vo GamPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamPrtFcltyRentFeeMngtVO vo){
		insert("gamPrtFcltyRentFeeMngtDao.insertAnlrveLev_S", vo);
	}

	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentFeeMngt(GamPrtFcltyRentFeeMngtVO vo){
		delete("gamPrtFcltyRentFeeMngtDao.deletePrtFcltyRentFeeMngt_S", vo);
	}

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentFeeMngtLevReqest(GamPrtFcltyRentFeeMngtVO vo){
		insert("gamPrtFcltyRentFeeMngtDao.insertPrtFcltyRentFeeMngtLevReqest_S", vo);
	}

	/**
	 * 고지서 정보를 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List selectNpticPrintInfo(GamPrtFcltyRentFeeMngtVO searchVO) throws Exception {
        //return (Map) selectByPk("gamPrtFcltyRentFeeMngtDao.selectNticPrintFeeList_S", searchVO);
        return list("gamPrtFcltyRentFeeMngtDao.selectNticPrintFeeList_S", searchVO);
	}

	/**
	 * 연체금만 있는 고지서를 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List selectNpticPrintInfo2(Map searchVO) throws Exception {
        return list("gamPrtFcltyRentFeeMngtDao.selectNticPrintFeeList2_D", searchVO);
	}

	public List selectTaxNtcPrintInfo(Map searchVO) throws Exception {
        return list("gamPrtFcltyRentFeeMngtDao.selectTaxNticPrintFeeList_D", searchVO);
	}

	public void updateAssetRentFeeMngtListDetail(GamPrtFcltyRentFeeMngtVO vo)
			throws Exception {
		update("gamPrtFcltyRentFeeMngtDao.updateAssetRentFeeMngtListDetail_S", vo);
	}

	public List selectAssetRentFeeDetailList(GamPrtFcltyRentFeeMngtVO searchVO) {
		return list("gamPrtFcltyRentFeeMngtDao.selectAssetRentFeeDetailList_D", searchVO);
	}

	public Map selectAssetRentFeeDetailMstPk(GamPrtFcltyRentFeeMngtVO searchVO) {
		return (Map) selectByPk("gamPrtFcltyRentFeeMngtDao.selectAssetRentFeeDetailMstPk_S", searchVO);
	}

	public Map selectAssetRentFeeDetailSumPk(GamPrtFcltyRentFeeMngtVO searchVO) {
		return (Map) selectByPk("gamPrtFcltyRentFeeMngtDao.selectAssetRentFeeDetailSumPk_S", searchVO);
	}

	/**
	 * @param vo
	 */
	public void updateLevReqestNhtPrintYn(GamPrtFcltyRentFeeMngtVO vo) {
        update("gamPrtFcltyRentFeeMngtDao.updateLevReqestNhtPrintYn_S", vo);
	}

	/**
	 * 연체 고지 출력 처리
	 * @param map
	 */
	public void updateUnpaidBillPrintYn(Map map) {
        update("gamPrtFcltyRentFeeMngtDao.updateUnpaidBillPrintYn_S", map);
	}

	/**
	 * @param map
	 */
	public void updateRevCollBillPrintYn(Map map) {
        update("gamPrtFcltyRentFeeMngtDao.updateRevCollBillPrintYn_S", map);
	}

	/**
	 * @param map
	 * @return
	 */
	public Map selectRevCollF(Map map) {
        return (EgovMap) selectByPk("gamPrtFcltyRentFeeMngtDao.selectRevCollF", map);
	}



}