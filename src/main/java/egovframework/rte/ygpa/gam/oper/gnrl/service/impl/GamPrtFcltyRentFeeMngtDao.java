package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
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
	 * 자산임대료고지관리정보를 수정한다.
	 * @param vo GamPrtFcltyRentFeeMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentFeeMngt(GamPrtFcltyRentFeeMngtVO vo){
		update("gamPrtFcltyRentFeeMngtDao.updatePrtFcltyRentFeeMngt_S", vo);
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
	
	public List selectNpticPrintInfo(Map searchVO) throws Exception {
        return list("gamPrtFcltyRentFeeMngtDao.selectNticPrintFeeList_D", searchVO);
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
	
	
	
}