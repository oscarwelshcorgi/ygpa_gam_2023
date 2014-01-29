package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentDetailVO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentLevReqestVO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentVO;

/**
 * @Class Name : GamAssetRentDao.java
 * @Description : 자산임대관리 DAO Class
 * @Modification Information
 *
 * @author 정윤후
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetRentDao")
public class GamAssetRentDao extends YGPAAbstractDAO {
	
	/**
	 * 자산임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentList(GamAssetRentVO searchVO) throws Exception {
        return list("gamAssetRentDao.selectAssetRentList_D", searchVO);
    }

    /**
	 * 자산임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentListTotCnt(GamAssetRentVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentDao.selectAssetRentListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetRentVO selectAssetRentSum(GamAssetRentVO searchVO) throws Exception {
		return (GamAssetRentVO) selectByPk("gamAssetRentDao.selectAssetRentSum_S", searchVO);
	}
    
	/**
	 * 자산임대 최초 신청을 등록한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void insertAssetRentFirst(GamAssetRentVO vo){
		insert("gamAssetRentDao.insertAssetRentFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamAssetRentVO
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public GamAssetRentVO selectAssetRentMaxNo(GamAssetRentVO vo) {
        return (GamAssetRentVO)getSqlMapClientTemplate().queryForObject("gamAssetRentDao.selectAssetRentMaxNo_S", vo);
    }
    
    /**
	 * 자산임대 연장 신청을 등록한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void insertAssetRentRenew(GamAssetRentVO vo){
		insert("gamAssetRentDao.insertAssetRentRenew_S", vo);
	}
	
	/**
	 * 자산임대 정보를 수정한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void updateAssetRent(GamAssetRentVO vo){
		update("gamAssetRentDao.updateAssetRent_S", vo);
	}
	
	/**
	 * 자산임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentDetailList(GamAssetRentVO vo) throws Exception {
        return list("gamAssetRentDao.selectAssetRentDetailList_D", vo);
    }

    /**
	 * 자산임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentDetailListTotCnt(GamAssetRentVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentDao.selectAssetRentDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentLevReqestCnt(GamAssetRentVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentDao.selectAssetRentLevReqestCnt_S", vo);
    }
    
    /**
	 * 자산임대 사진정보를 삭제한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void deleteAssetRentPhoto(GamAssetRentVO vo){
		delete("gamAssetRentDao.deleteAssetRentPhoto_S", vo);
	}
    
	/**
	 * 자산임대 정보를 삭제한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void deleteAssetRent(GamAssetRentVO vo){
		delete("gamAssetRentDao.deleteAssetRent_S", vo);
	}
    
	/**
	 * 자산임대 상세정보를 삭제한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void deleteAssetRentDetail(GamAssetRentVO vo){
		delete("gamAssetRentDao.deleteAssetRentDetail_S", vo);
	}
	
	/**
	 * 자산임대 상세를 등록한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void insertAssetRentDetail(GamAssetRentDetailVO vo){
		insert("gamAssetRentDao.insertAssetRentDetail_S", vo);
	}
	
	/**
	 * 자산임대 상세를 수정한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentDetail(GamAssetRentDetailVO vo){
		update("gamAssetRentDao.updateAssetRentDetail_S", vo);
	}
	
	/**
	 * 자산임대 상세를 삭제한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void deleteAssetRentDetail2(GamAssetRentDetailVO vo){
		delete("gamAssetRentDao.deleteAssetRentDetail2_S", vo);
	}

	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대정보
	 * @exception Exception
	 */
	public GamAssetRentVO selectAssetRentPrmisnInfo(GamAssetRentVO searchVO) throws Exception {
		return (GamAssetRentVO) selectByPk("gamAssetRentDao.selectAssetRentPrmisnInfo_S", searchVO);
	}
	
	/**
	 * 자산임대 허가여부를 수정한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void updateAssetRentPrmisn(GamAssetRentLevReqestVO vo){
		update("gamAssetRentDao.updateAssetRentPrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamAssetRentLevReqestVO
	 * @exception Exception
	 */
	public void insertAssetRentLevReqest(GamAssetRentLevReqestVO vo){
		insert("gamAssetRentDao.insertAssetRentLevReqest_S", vo);
	}
	
	/**
	 * 자산임대 허가여부를 취소한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void updateAssetRentPrmisnCancel(GamAssetRentLevReqestVO vo){
		update("gamAssetRentDao.updateAssetRentPrmisnCancel_S", vo);
	}
	
}
