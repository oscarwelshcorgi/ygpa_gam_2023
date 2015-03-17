package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentFeeMngtVO;

/**
 * @Class Name : GamTrainPortRentFeeMngtDao.java
 * @Description : 철송장임대료관리 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamTrainPortRentFeeMngtDao")
public class GamTrainPortRentFeeMngtDao extends YGPAAbstractDAO {

	/**
	 * 자산임대료고지관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료고지관리 목록
	 * @exception Exception
	 */
    public List selectTrainPortRentFeeMngtList(GamTrainPortRentFeeMngtVO searchVO) throws Exception {
        return list("gamTrainPortRentFeeMngtDao.selectTrainPortRentFeeMngtList_D", searchVO);
    }

    /**
	 * 자산임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료고지관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortRentFeeMngtListTotCnt(GamTrainPortRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentFeeMngtDao.selectTrainPortRentFeeMngtListTotCnt_S", searchVO);
    }

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료고지관리 목록
	 * @exception Exception
	 */
	public GamTrainPortRentFeeMngtVO selectTrainPortRentFeeMngtSum(GamTrainPortRentFeeMngtVO searchVO) throws Exception {
		return (GamTrainPortRentFeeMngtVO) selectByPk("gamTrainPortRentFeeMngtDao.selectTrainPortRentFeeMngtSum_S", searchVO);
	}

	/**
	 * 자산임대료고지관리정보를 수정한다.
	 * @param vo GamTrainPortRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateTrainPortRentFeeMngt(GamTrainPortRentFeeMngtVO vo){
		update("gamTrainPortRentFeeMngtDao.updateTrainPortRentFeeMngt_S", vo);
	}

	/**
	 * 사용료를 변경 한다.
	 * @param vo
	 */
	public void updateTrainPortRentFee(GamTrainPortRentFeeMngtVO vo) {
		update("gamTrainPortRentFeeMngtDao.updateTrainPortRentFeeModifyFee_S", vo);
	}

	/**
	 * 자산임대료고지관리정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료고지관리정보
	 * @exception Exception
	 */
	public GamTrainPortRentFeeMngtVO selectTrainPortRentFeeMngtInfo(GamTrainPortRentFeeMngtVO searchVO) throws Exception {
		return (GamTrainPortRentFeeMngtVO) selectByPk("gamTrainPortRentFeeMngtDao.selectTrainPortRentFeeMngtInfo_S", searchVO);
	}


	/**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 갯수
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamTrainPortRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentFeeMngtDao.selectAnlrveLevCnt_S", searchVO);
    }

	/**
	 * 세입징수를 등록한다.
	 * @param vo GamTrainPortRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamTrainPortRentFeeMngtVO vo){
		insert("gamTrainPortRentFeeMngtDao.insertAnlrveLev_S", vo);
	}

	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamTrainPortRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentFeeMngt(GamTrainPortRentFeeMngtVO vo){
		delete("gamTrainPortRentFeeMngtDao.deleteTrainPortRentFeeMngt_S", vo);
	}

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamTrainPortRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertTrainPortRentFeeMngtLevReqest(GamTrainPortRentFeeMngtVO vo){
		insert("gamTrainPortRentFeeMngtDao.insertTrainPortRentFeeMngtLevReqest_S", vo);
	}

	/**
	 * 고지서 정보를 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public Map selectNpticPrintInfo(GamTrainPortRentFeeMngtVO searchVO) throws Exception {
        return (Map) selectByPk("gamTrainPortRentFeeMngtDao.selectNticPrintFeeList_S", searchVO);
	}

	/**
	 * 연체금만 있는 고지서를 조회한다.
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List selectNpticPrintInfo2(Map searchVO) throws Exception {
        return list("gamTrainPortRentFeeMngtDao.selectNticPrintFeeList2_D", searchVO);
	}

	public List selectTaxNtcPrintInfo(Map searchVO) throws Exception {
        return list("gamTrainPortRentFeeMngtDao.selectTaxNticPrintFeeList_D", searchVO);
	}

	public void updateAssetRentFeeMngtListDetail(GamTrainPortRentFeeMngtVO vo)
			throws Exception {
		update("gamTrainPortRentFeeMngtDao.updateAssetRentFeeMngtListDetail_S", vo);
	}

	public List selectAssetRentFeeDetailList(GamTrainPortRentFeeMngtVO searchVO) {
		return list("gamTrainPortRentFeeMngtDao.selectAssetRentFeeDetailList_D", searchVO);
	}

	public Map selectAssetRentFeeDetailMstPk(GamTrainPortRentFeeMngtVO searchVO) {
		return (Map) selectByPk("gamTrainPortRentFeeMngtDao.selectAssetRentFeeDetailMstPk_S", searchVO);
	}

	public Map selectAssetRentFeeDetailSumPk(GamTrainPortRentFeeMngtVO searchVO) {
		return (Map) selectByPk("gamTrainPortRentFeeMngtDao.selectAssetRentFeeDetailSumPk_S", searchVO);
	}

	/**
	 * @param vo
	 */
	public void updateLevReqestNhtPrintYn(GamTrainPortRentFeeMngtVO vo) {
        update("gamTrainPortRentFeeMngtDao.updateLevReqestNhtPrintYn_S", vo);
	}

	/**
	 * 연체 고지 출력 처리
	 * @param map
	 */
	public void updateUnpaidBillPrintYn(Map map) {
        update("gamTrainPortRentFeeMngtDao.updateUnpaidBillPrintYn_S", map);
	}

	/**
	 * @param map
	 */
	public void updateRevCollBillPrintYn(Map map) {
        update("gamTrainPortRentFeeMngtDao.updateRevCollBillPrintYn_S", map);
	}

	/**
	 * @param map
	 * @return
	 */
	public Map selectRevCollF(Map map) {
        return (EgovMap) selectByPk("gamTrainPortRentFeeMngtDao.selectRevCollF", map);
	}



}