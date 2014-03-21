package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentFeeMngtVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentLevReqestVO;

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
    public List selectAssetRentFeeList(GamAssetRentFeeMngtVO searchVO) throws Exception {
        return list("gamAssetRentFeeMngtDao.selectAssetRentFeeList_D", searchVO);
    }

    /**
	 * 자산임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료고지관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentFeeListTotCnt(GamAssetRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentFeeMngtDao.selectAssetRentFeeListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료고지관리 목록
	 * @exception Exception
	 */
	public GamAssetRentFeeMngtVO selectAssetRentFeeSum(GamAssetRentFeeMngtVO searchVO) throws Exception {
		return (GamAssetRentFeeMngtVO) selectByPk("gamAssetRentFeeMngtDao.selectAssetRentFeeSum_S", searchVO);
	}
	
	/**
	 * 자산임대료고지관리정보를 수정한다.
	 * @param vo GamAssetRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateAssetRentFee(GamAssetRentFeeMngtVO vo){
		update("gamAssetRentFeeMngtDao.updateAssetRentFee_S", vo);
	}
	
	/**
	 * 자산임대료고지관리정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대료고지관리정보
	 * @exception Exception
	 */
	public GamAssetRentFeeMngtVO selectAssetRentFeeInfo(GamAssetRentFeeMngtVO searchVO) throws Exception {
		return (GamAssetRentFeeMngtVO) selectByPk("gamAssetRentFeeMngtDao.selectAssetRentFeeInfo_S", searchVO);
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
	public void deleteAssetRentFee(GamAssetRentFeeMngtVO vo){
		delete("gamAssetRentFeeMngtDao.deleteAssetRentFee_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamAssetRentLevReqestVO
	 * @exception Exception
	 */
	public void insertAssetRentLevReqest(GamAssetRentFeeMngtVO vo){
		insert("gamAssetRentFeeMngtDao.insertAssetRentLevReqest_S", vo);
	}

}
