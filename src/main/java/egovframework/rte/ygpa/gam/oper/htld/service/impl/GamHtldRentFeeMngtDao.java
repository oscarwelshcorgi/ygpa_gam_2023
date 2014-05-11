package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeeMngtVO;
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
    public List selectHtldRentFeeMngtList(GamHtldRentFeeMngtVO searchVO) throws Exception {
        return list("gamHtldRentFeeMngtDao.selectHtldRentFeeMngtList_D", searchVO);
    }

    /**
	 * 배후단지임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldRentFeeMngtListTotCnt(GamHtldRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentFeeMngtDao.selectHtldRentFeeMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public GamHtldRentFeeMngtVO selectHtldRentFeeMngtSum(GamHtldRentFeeMngtVO searchVO) throws Exception {
		return (GamHtldRentFeeMngtVO) selectByPk("gamHtldRentFeeMngtDao.selectHtldRentFeeMngtSum_S", searchVO);
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
}