package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtVO;


/**
 * @Class Name : GamAssetRentFeeMngtDao.java
 * @Description : 자산임대료고지관리 DAO Class
 * @Modification Information
 *
 * @author heroin
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetRentFeeMngtDao")
public class GamAssetRentFeeMngtDao extends YGPAAbstractDAO {

	/**
	 * 자산임대료고지관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료고지관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentFeeMngtList(GamAssetRentFeeMngtVO searchVO) throws Exception {
        return list("gamAssetRentFeeMngtDao.selectAssetRentFeeMngtList_D", searchVO);
    }

    /**
	 * 자산임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료고지관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentFeeMngtListTotCnt(GamAssetRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentFeeMngtDao.selectAssetRentFeeMngtListTotCnt_S", searchVO);
    }

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료고지관리 목록
	 * @exception Exception
	 */
	public GamAssetRentFeeMngtVO selectAssetRentFeeMngtSum(GamAssetRentFeeMngtVO searchVO) throws Exception {
		return (GamAssetRentFeeMngtVO) selectByPk("gamAssetRentFeeMngtDao.selectAssetRentFeeMngtSum_S", searchVO);
	}

	/**
	 * 자산임대료고지관리정보를 수정한다.
	 * @param vo GamAssetRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateAssetRentFeeMngt(GamAssetRentFeeMngtVO vo){
		update("gamAssetRentFeeMngtDao.updateAssetRentFeeMngt_S", vo);
	}

	/**
	 * 사용료를 변경 한다.
	 * @param vo
	 */
	public void updateAssetRentFee(GamAssetRentFeeMngtVO vo) {
		update("gamAssetRentFeeMngtDao.updateAssetRentFeeModifyFee_S", vo);
	}

	/**
	 * 자산임대료고지관리정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료고지관리정보
	 * @exception Exception
	 */
	public GamAssetRentFeeMngtVO selectAssetRentFeeMngtInfo(GamAssetRentFeeMngtVO searchVO) throws Exception {
		return (GamAssetRentFeeMngtVO) selectByPk("gamAssetRentFeeMngtDao.selectAssetRentFeeMngtInfo_S", searchVO);
	}


	/**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 갯수
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamAssetRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentFeeMngtDao.selectAnlrveLevCnt_S", searchVO);
    }

	/**
	 * 세입징수를 등록한다.
	 * @param vo GamAssetRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamAssetRentFeeMngtVO vo){
		insert("gamAssetRentFeeMngtDao.insertAnlrveLev_S", vo);
	}

	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamAssetRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteAssetRentFeeMngt(GamAssetRentFeeMngtVO vo){
		delete("gamAssetRentFeeMngtDao.deleteAssetRentFeeMngt_S", vo);
	}

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamAssetRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAssetRentFeeMngtLevReqest(GamAssetRentFeeMngtVO vo){
		insert("gamAssetRentFeeMngtDao.insertAssetRentFeeMngtLevReqest_S", vo);
	}

	/**
	 * 고지서 정보를 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public Map selectNpticPrintInfo(GamAssetRentFeeMngtVO searchVO) throws Exception {
        return (Map) selectByPk("gamAssetRentFeeMngtDao.selectNticPrintFeeList_S", searchVO);
	}

	/**
	 * 연체금만 있는 고지서를 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List selectNpticPrintInfo2(Map searchVO) throws Exception {
        return list("gamAssetRentFeeMngtDao.selectNticPrintFeeList2_D", searchVO);
	}

	public List selectTaxNtcPrintInfo(Map searchVO) throws Exception {
        return list("gamAssetRentFeeMngtDao.selectTaxNticPrintFeeList_D", searchVO);
	}

	public void updateAssetRentFeeMngtListDetail(GamAssetRentFeeMngtVO vo)
			throws Exception {
		update("gamAssetRentFeeMngtDao.updateAssetRentFeeMngtListDetail_S", vo);
	}

	public List selectAssetRentFeeDetailList(GamAssetRentFeeMngtVO searchVO) {
		return list("gamAssetRentFeeMngtDao.selectAssetRentFeeDetailList_D", searchVO);
	}

	public Map selectAssetRentFeeDetailMstPk(GamAssetRentFeeMngtVO searchVO) {
		return (Map) selectByPk("gamAssetRentFeeMngtDao.selectAssetRentFeeDetailMstPk_S", searchVO);
	}

	public Map selectAssetRentFeeDetailSumPk(GamAssetRentFeeMngtVO searchVO) {
		return (Map) selectByPk("gamAssetRentFeeMngtDao.selectAssetRentFeeDetailSumPk_S", searchVO);
	}

	/**
	 * @param vo
	 */
	public void updateLevReqestNhtPrintYn(GamAssetRentFeeMngtVO vo) {
        update("gamAssetRentFeeMngtDao.updateLevReqestNhtPrintYn_S", vo);
	}

	/**
	 * 연체 고지 출력 처리
	 * @param map
	 */
	public void updateUnpaidBillPrintYn(Map map) {
        update("gamAssetRentFeeMngtDao.updateUnpaidBillPrintYn_S", map);
	}

	/**
	 * @param map
	 */
	public void updateRevCollBillPrintYn(Map map) {
        update("gamAssetRentFeeMngtDao.updateRevCollBillPrintYn_S", map);
	}

	/**
	 * @param map
	 * @return
	 */
	public Map selectRevCollF(Map map) {
        return (EgovMap) selectByPk("gamAssetRentFeeMngtDao.selectRevCollF", map);
	}



}