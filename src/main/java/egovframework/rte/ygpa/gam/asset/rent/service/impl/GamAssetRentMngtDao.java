package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentDetailVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentLevReqestVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentMngtVO;

/**
 * @Class Name : GamAssetRentMngtDao.java
 * @Description : 자산임대관리 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetRentMngtDao")
public class GamAssetRentMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 자산임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentList(GamAssetRentMngtVO searchVO) throws Exception {
        return list("gamAssetRentMngtDao.selectAssetRentList_D", searchVO);
    }

    /**
	 * 자산임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentListTotCnt(GamAssetRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentMngtDao.selectAssetRentListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetRentMngtVO selectAssetRentSum(GamAssetRentMngtVO searchVO) throws Exception {
		return (GamAssetRentMngtVO) selectByPk("gamAssetRentMngtDao.selectAssetRentSum_S", searchVO);
	}
    
	/**
	 * 자산임대 최초 신청을 등록한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void insertAssetRentFirst(GamAssetRentMngtVO vo){
		insert("gamAssetRentMngtDao.insertAssetRentFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamAssetRentVO
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public GamAssetRentMngtVO selectAssetRentMaxNo(GamAssetRentMngtVO vo) {
        return (GamAssetRentMngtVO)getSqlMapClientTemplate().queryForObject("gamAssetRentMngtDao.selectAssetRentMaxNo_S", vo);
    }
    
    /**
	 * 자산임대 연장 신청을 등록한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void insertAssetRentRenew(GamAssetRentMngtVO vo){
		insert("gamAssetRentMngtDao.insertAssetRentRenew_S", vo);
	}
	
	/**
	 * 자산임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectAssetRentMaxMngCnt(GamAssetRentMngtVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamAssetRentMngtDao.selectAssetRentMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 자산임대 연장 신청시 자산임대 상세를 복사하여 등록한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void insertAssetRentDetailRenew(GamAssetRentDetailVO vo){
		insert("gamAssetRentMngtDao.insertAssetRentDetailRenew_S", vo);
	}
	
	/**
	 * 자산임대 정보를 수정한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void updateAssetRent(GamAssetRentMngtVO vo){
		update("gamAssetRentMngtDao.updateAssetRent_S", vo);
	}
	
	/**
	 * 자산임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentDetailList(GamAssetRentMngtVO vo) throws Exception {
        return list("gamAssetRentMngtDao.selectAssetRentDetailList_D", vo);
    }

    /**
	 * 자산임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentDetailListTotCnt(GamAssetRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentMngtDao.selectAssetRentDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentLevReqestCnt(GamAssetRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentMngtDao.selectAssetRentLevReqestCnt_S", vo);
    }
    
    /**
	 * 자산임대 사진정보를 삭제한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void deleteAssetRentPhoto(GamAssetRentMngtVO vo){
		delete("gamAssetRentMngtDao.deleteAssetRentPhoto_S", vo);
	}
    
	/**
	 * 자산임대 정보를 삭제한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void deleteAssetRent(GamAssetRentMngtVO vo){
		delete("gamAssetRentMngtDao.deleteAssetRent_S", vo);
	}
    
	/**
	 * 자산임대 상세정보를 삭제한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void deleteAssetRentDetail(GamAssetRentMngtVO vo){
		delete("gamAssetRentMngtDao.deleteAssetRentDetail_S", vo);
	}
	
	/**
	 * 자산임대 상세를 등록한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void insertAssetRentDetail(GamAssetRentDetailVO vo){
		insert("gamAssetRentMngtDao.insertAssetRentDetail_S", vo);
	}
	
	/**
	 * 자산임대 상세를 수정한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentDetail(GamAssetRentDetailVO vo){
		update("gamAssetRentMngtDao.updateAssetRentDetail_S", vo);
	}
	
	/**
	 * 자산임대 상세를 삭제한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void deleteAssetRentDetail2(GamAssetRentDetailVO vo){
		delete("gamAssetRentMngtDao.deleteAssetRentDetail2_S", vo);
	}

	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대정보
	 * @exception Exception
	 */
	public GamAssetRentMngtVO selectAssetRentPrmisnInfo(GamAssetRentMngtVO searchVO) throws Exception {
		return (GamAssetRentMngtVO) selectByPk("gamAssetRentMngtDao.selectAssetRentPrmisnInfo_S", searchVO);
	}
	
	/**
	 * 자산임대 허가여부를 수정한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void updateAssetRentPrmisn(GamAssetRentLevReqestVO vo){
		update("gamAssetRentMngtDao.updateAssetRentPrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamAssetRentLevReqestVO
	 * @exception Exception
	 */
	public void insertAssetRentLevReqest(GamAssetRentLevReqestVO vo){
		insert("gamAssetRentMngtDao.insertAssetRentLevReqest_S", vo);
	}
	
	/**
	 * 자산임대 허가여부를 취소한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void updateAssetRentPrmisnCancel(GamAssetRentLevReqestVO vo){
		update("gamAssetRentMngtDao.updateAssetRentPrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamAssetRentLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentMngtDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 자산임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대상세 목록
	 * @exception Exception
	 */
    public List selectAssetRentDetailInfo(GamAssetRentMngtVO vo) throws Exception {
        return list("gamAssetRentMngtDao.selectAssetRentDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamAssetRentMngtDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentFileList(GamAssetRentMngtVO searchVO) throws Exception {
        return list("gamAssetRentMngtDao.selectAssetRentFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentFileListTotCnt(GamAssetRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentMngtDao.selectAssetRentFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void insertAssetRentFile(GamAssetRentMngtVO vo){
		insert("gamAssetRentMngtDao.insertAssetRentFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void updateAssetRentFile(GamAssetRentMngtVO vo){
		insert("gamAssetRentMngtDao.updateAssetRentFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void deleteAssetRentPhotoSingle(GamAssetRentMngtVO vo){
		delete("gamAssetRentMngtDao.deleteAssetRentPhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetRentMngtVO selectAssetRentMaxKey(GamAssetRentMngtVO searchVO) throws Exception {
		return (GamAssetRentMngtVO) selectByPk("gamAssetRentMngtDao.selectAssetRentMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentComment(GamAssetRentMngtVO vo){
		update("gamAssetRentMngtDao.updateAssetRentComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetRentMngtVO selectAssetRentRenewInfo(GamAssetRentMngtVO searchVO) throws Exception {
		return (GamAssetRentMngtVO) selectByPk("gamAssetRentMngtDao.selectAssetRentRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentRenewInfo(GamAssetRentMngtVO vo){
		update("gamAssetRentMngtDao.updateAssetRentRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetRentMngtVO selectAssetRentCurrRenewInfo(GamAssetRentMngtVO searchVO) throws Exception {
		return (GamAssetRentMngtVO) selectByPk("gamAssetRentMngtDao.selectAssetRentCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetRentMngtVO selectAssetRentDetailQuaycd(GamAssetRentMngtVO searchVO) throws Exception {
		return (GamAssetRentMngtVO) selectByPk("gamAssetRentMngtDao.selectAssetRentDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentQuaycd(GamAssetRentMngtVO vo){
		update("gamAssetRentMngtDao.updateAssetRentQuaycd_S", vo);
	}
}
