package egovframework.rte.ygpa.gam.asset.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.umt.service.DeptManageVO;
import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentDetailVO;
import egovframework.rte.ygpa.gam.asset.service.GamAssetRentVO;

/**
 * @Class Name : GamAssetRentDao.java
 * @Description : 자산임대관리 DAO Class
 * @Modification Information
 *
 * @author Dev
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
	 * 자산임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentDetailList(GamAssetRentDetailVO vo) throws Exception {
        return list("gamAssetRentDao.selectAssetRentDetailList_D", vo);
    }

    /**
	 * 자산임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentDetailListTotCnt(GamAssetRentDetailVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentDao.selectAssetRentDetailListTotCnt_S", vo);
    }
	
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	
	
	/**
	 * ERP_ASSET_CD을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ErpAssetCdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertErpAssetCd(ErpAssetCdVO vo) throws Exception {
        return (String)insert("erpAssetCdDAO.insertErpAssetCd_S", vo);
    }

    /**
	 * ERP_ASSET_CD을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ErpAssetCdVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateErpAssetCd(ErpAssetCdVO vo) throws Exception {
        update("erpAssetCdDAO.updateErpAssetCd_S", vo);
    }

    /**
	 * ERP_ASSET_CD을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ErpAssetCdVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteErpAssetCd(ErpAssetCdVO vo) throws Exception {
        delete("erpAssetCdDAO.deleteErpAssetCd_S", vo);
    }

    /**
	 * ERP_ASSET_CD을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ErpAssetCdVO
	 * @return 조회한 ERP_ASSET_CD
	 * @exception Exception
	 */
    public ErpAssetCdVO selectErpAssetCd(ErpAssetCdVO vo) throws Exception {
        return (ErpAssetCdVO) selectByPk("erpAssetCdDAO.selectErpAssetCd_S", vo);
    }

}
