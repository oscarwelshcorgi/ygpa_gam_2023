package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentFeeMngtVO;

/**
 * @Class Name : GamMarineCenterRentFeeMngtDao.java
 * @Description : 마린센터임대료관리 DAO Class
 * @Modification Information
 *
 * @author heroin
 * @since 2014-01-10
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Repository("gamMarineCenterRentFeeMngtDao")
public class GamMarineCenterRentFeeMngtDao extends YGPAAbstractDAO {

	/**
	 * 마린센터임대료관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대료관리 목록
	 * @exception Exception
	 */
    public List selectMarineCenterRentFeeList(GamMarineCenterRentFeeMngtVO searchVO) throws Exception {
        return list("gamMarineCenterRentFeeMngtDao.selectMarineCenterRentFeeList_D", searchVO);
    }

    /**
	 * 마린센터임대료관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대료관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterRentFeeListTotCnt(GamMarineCenterRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentFeeMngtDao.selectMarineCenterRentFeeListTotCnt_S", searchVO);
    }

    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대료관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterRentFeeMngtVO selectMarineCenterRentFeeSum(GamMarineCenterRentFeeMngtVO searchVO) throws Exception {
		return (GamMarineCenterRentFeeMngtVO) selectByPk("gamMarineCenterRentFeeMngtDao.selectMarineCenterRentFeeSum_S", searchVO);
	}

	/**
	 * 마린센터임대료관리정보를 수정한다.
	 * @param vo GamMarineCenterRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentFee(GamMarineCenterRentFeeMngtVO vo){
		update("gamMarineCenterRentFeeMngtDao.updateMarineCenterRentFee_S", vo);
	}

	/**
	 * 마린센터임대료관리정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대료관리정보
	 * @exception Exception
	 */
	public GamMarineCenterRentFeeMngtVO selectMarineCenterRentFeeInfo(GamMarineCenterRentFeeMngtVO searchVO) throws Exception {
		return (GamMarineCenterRentFeeMngtVO) selectByPk("gamMarineCenterRentFeeMngtDao.selectMarineCenterRentFeeInfo_S", searchVO);
	}


	/**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 갯수
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamMarineCenterRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentFeeMngtDao.selectAnlrveLevCnt_S", searchVO);
    }

	/**
	 * 세입징수를 등록한다.
	 * @param vo GamMarineCenterRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamMarineCenterRentFeeMngtVO vo){
		insert("gamMarineCenterRentFeeMngtDao.insertAnlrveLev_S", vo);
	}

	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamMarineCenterRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteMarineCenterRentFee(GamMarineCenterRentFeeMngtVO vo){
		delete("gamMarineCenterRentFeeMngtDao.deleteMarineCenterRentFee_S", vo);
	}

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamAssetRentLevReqestVO
	 * @exception Exception
	 */
	public void insertAssetRentLevReqest(GamMarineCenterRentFeeMngtVO vo){
		insert("gamMarineCenterRentFeeMngtDao.insertAssetRentLevReqest_S", vo);
	}

	public List selectNpticPrintInfo(Map searchVO) throws Exception {
        return list("gamMarineCenterRentFeeMngtDao.selectNticPrintFeeList_D", searchVO);
	}

	public List selectTaxNtcPrintInfo(Map searchVO) throws Exception {
        return list("gamMarineCenterRentFeeMngtDao.selectTaxNticPrintFeeList_D", searchVO);
	}

	public void updateAssetRentFeeMngtListDetail(GamMarineCenterRentFeeMngtVO vo)
			throws Exception {
		update("gamMarineCenterRentFeeMngtDao.updateAssetRentFeeMngtListDetail_S", vo);
	}

	public List selectAssetRentFeeDetailList(GamMarineCenterRentFeeMngtVO searchVO) {
		return list("gamMarineCenterRentFeeMngtDao.selectAssetRentFeeDetailList_D", searchVO);
	}

	public Map selectAssetRentFeeDetailMstPk(GamMarineCenterRentFeeMngtVO searchVO) {
		return (Map) selectByPk("gamMarineCenterRentFeeMngtDao.selectAssetRentFeeDetailMstPk_S", searchVO);
	}

	public Map selectAssetRentFeeDetailSumPk(GamMarineCenterRentFeeMngtVO searchVO) {
		return (Map) selectByPk("gamMarineCenterRentFeeMngtDao.selectAssetRentFeeDetailSumPk_S", searchVO);
	}


	public Map selectAssetRentMngFeeInfo(GamMarineCenterRentFeeMngtVO vo) throws Exception {
        return (Map) selectByPk("gamMarineCenterRentFeeMngtDao.selectAssetRentMngFeeInfo_S", vo);
	}

	public Map selectAssetRentMngFeeData(GamMarineCenterRentFeeMngtVO vo) throws Exception {
        return (Map) selectByPk("gamMarineCenterRentFeeMngtDao.selectAssetRentMngFeeList_S", vo);
	}

	public Map selectAssetRentMngFeeVal(GamMarineCenterRentFeeMngtVO vo) throws Exception {
        return (Map) selectByPk("gamMarineCenterRentFeeMngtDao.selectAssetRentMngFeeVal_S", vo);
	}

	public void deleteAssetRentMngFee(GamMarineCenterRentFeeMngtVO vo) throws Exception {
		delete("gamMarineCenterRentFeeMngtDao.deleteAssetRentMngFee_S", vo);
	}

	public void insertAssetRentMngFee(GamMarineCenterRentFeeMngtVO vo) throws Exception {
		insert("gamMarineCenterRentFeeMngtDao.insertAssetRentMngFee_S", vo);
	}

}
