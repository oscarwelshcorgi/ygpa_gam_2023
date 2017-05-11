package egovframework.rte.ygpa.gam.oper.gnrltest.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentFeeMngtVO;

/**
 * @Class Name : GamTestPrtFcltyRentFeeMngtDao.java
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
@Repository("gamTestPrtFcltyRentFeeMngtDao")
public class GamTestPrtFcltyRentFeeMngtDao extends YGPAAbstractDAO {

	/**
	 * 자산임대료고지관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료고지관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyRentFeeMngtList(GamTestPrtFcltyRentFeeMngtVO searchVO) throws Exception {
        return list("gamTestPrtFcltyRentFeeMngtDao.selectPrtFcltyRentFeeMngtList_D", searchVO);
    }

    /**
	 * 자산임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료고지관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyRentFeeMngtListTotCnt(GamTestPrtFcltyRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyRentFeeMngtDao.selectPrtFcltyRentFeeMngtListTotCnt_S", searchVO);
    }

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료고지관리 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyRentFeeMngtVO selectPrtFcltyRentFeeMngtSum(GamTestPrtFcltyRentFeeMngtVO searchVO) throws Exception {
		return (GamTestPrtFcltyRentFeeMngtVO) selectByPk("gamTestPrtFcltyRentFeeMngtDao.selectPrtFcltyRentFeeMngtSum_S", searchVO);
	}

	/**
	 * 투자비보전 상계처리한다. 2015.12.17 김종민
	 * @param vo GamTestPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentFeeSaveSingle(GamTestPrtFcltyRentFeeMngtVO vo){
		update("gamTestPrtFcltyRentFeeMngtDao.updatePrtFcltyRentFeeSaveSingle_S", vo);
	}
	
	/**
	 * 투자비보전 상계취소한다.
	 * @param vo GamTestPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void cancelPrtFcltyRentFeeSaveSingle(GamTestPrtFcltyRentFeeMngtVO vo){
		update("gamTestPrtFcltyRentFeeMngtDao.cancelPrtFcltyRentFeeSaveSingle_S", vo);
	}

	/**
	 * 자산임대료고지관리정보를 수정한다.
	 * @param vo GamTestPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentFeeMngt(GamTestPrtFcltyRentFeeMngtVO vo){
		update("gamTestPrtFcltyRentFeeMngtDao.updatePrtFcltyRentFeeMngt_S", vo);
	}

	/**
	 * 사용료를 변경 한다.
	 * @param vo
	 */
	public void updatePrtFcltyRentFee(GamTestPrtFcltyRentFeeMngtVO vo) {
		update("gamTestPrtFcltyRentFeeMngtDao.updatePrtFcltyRentFeeModifyFee_S", vo);
	}

	/**
	 * 자산임대료고지관리정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료고지관리정보
	 * @exception Exception
	 */
	public GamTestPrtFcltyRentFeeMngtVO selectPrtFcltyRentFeeMngtInfo(GamTestPrtFcltyRentFeeMngtVO searchVO) throws Exception {
		return (GamTestPrtFcltyRentFeeMngtVO) selectByPk("gamTestPrtFcltyRentFeeMngtDao.selectPrtFcltyRentFeeMngtInfo_S", searchVO);
	}


	/**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 갯수
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamTestPrtFcltyRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyRentFeeMngtDao.selectAnlrveLevCnt_S", searchVO);
    }

	/**
	 * 세입징수를 등록한다.
	 * @param vo GamTestPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamTestPrtFcltyRentFeeMngtVO vo){
		insert("gamTestPrtFcltyRentFeeMngtDao.insertAnlrveLev_S", vo);
	}

	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamTestPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentFeeMngt(GamTestPrtFcltyRentFeeMngtVO vo){
		delete("gamTestPrtFcltyRentFeeMngtDao.deletePrtFcltyRentFeeMngt_S", vo);
	}

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamTestPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentFeeMngtLevReqest(GamTestPrtFcltyRentFeeMngtVO vo){
		insert("gamTestPrtFcltyRentFeeMngtDao.insertPrtFcltyRentFeeMngtLevReqest_S", vo);
	}

	/**
	 * 고지서 정보를 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List selectNpticPrintInfo(GamTestPrtFcltyRentFeeMngtVO searchVO) throws Exception {
        //return (Map) selectByPk("gamTestPrtFcltyRentFeeMngtDao.selectNticPrintFeeList_S", searchVO);
        return list("gamTestPrtFcltyRentFeeMngtDao.selectNticPrintFeeList_S", searchVO);
	}

	/**
	 * 연체금만 있는 고지서를 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List selectNpticPrintInfo2(Map searchVO) throws Exception {
        return list("gamTestPrtFcltyRentFeeMngtDao.selectNticPrintFeeList2_D", searchVO);
	}

	public List selectTaxNtcPrintInfo(Map searchVO) throws Exception {
        return list("gamTestPrtFcltyRentFeeMngtDao.selectTaxNticPrintFeeList_D", searchVO);
	}

	public void updateAssetRentFeeMngtListDetail(GamTestPrtFcltyRentFeeMngtVO vo)
			throws Exception {
		update("gamTestPrtFcltyRentFeeMngtDao.updateAssetRentFeeMngtListDetail_S", vo);
	}

	public List selectAssetRentFeeDetailList(GamTestPrtFcltyRentFeeMngtVO searchVO) {
		return list("gamTestPrtFcltyRentFeeMngtDao.selectAssetRentFeeDetailList_D", searchVO);
	}

	public Map selectAssetRentFeeDetailMstPk(GamTestPrtFcltyRentFeeMngtVO searchVO) {
		return (Map) selectByPk("gamTestPrtFcltyRentFeeMngtDao.selectAssetRentFeeDetailMstPk_S", searchVO);
	}

	public Map selectAssetRentFeeDetailSumPk(GamTestPrtFcltyRentFeeMngtVO searchVO) {
		return (Map) selectByPk("gamTestPrtFcltyRentFeeMngtDao.selectAssetRentFeeDetailSumPk_S", searchVO);
	}

	/**
	 * @param vo
	 */
	public void updateLevReqestNhtPrintYn(GamTestPrtFcltyRentFeeMngtVO vo) {
        update("gamTestPrtFcltyRentFeeMngtDao.updateLevReqestNhtPrintYn_S", vo);
	}

	/**
	 * 연체 고지 출력 처리
	 * @param map
	 */
	public void updateUnpaidBillPrintYn(Map map) {
        update("gamTestPrtFcltyRentFeeMngtDao.updateUnpaidBillPrintYn_S", map);
	}

	/**
	 * @param map
	 */
	public void updateRevCollBillPrintYn(Map map) {
        update("gamTestPrtFcltyRentFeeMngtDao.updateRevCollBillPrintYn_S", map);
	}

	/**
	 * @param map
	 * @return
	 */
	public Map selectRevCollF(Map map) {
        return (EgovMap) selectByPk("gamTestPrtFcltyRentFeeMngtDao.selectRevCollF", map);
	}



}