package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetUseExprInqireVO;

/**
 * @Class Name : gamAssetUseExprInqireDao.java
 * @Description : 자산임대만기도래자료조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetUseExprInqireDao")
public class GamAssetUseExprInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 자산임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetUseExprInqireList(GamAssetUseExprInqireVO searchVO) throws Exception {
        return list("gamAssetUseExprInqireDao.selectAssetUseExprInqireList_D", searchVO);
    }

    /**
	 * 자산임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetUseExprInqireListTotCnt(GamAssetUseExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetUseExprInqireDao.selectAssetUseExprInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetUseExprInqireVO selectAssetUseExprInqireSum(GamAssetUseExprInqireVO searchVO) throws Exception {
		return (GamAssetUseExprInqireVO) selectByPk("gamAssetUseExprInqireDao.selectAssetUseExprInqireSum_S", searchVO);
	}
    
	/**
	 * 자산임대 최초 신청을 등록한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	public void insertAssetUseExprInqireFirst(GamAssetUseExprInqireVO vo){
		insert("gamAssetUseExprInqireDao.insertAssetUseExprInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamAssetUseExprInqireVO
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public GamAssetUseExprInqireVO selectAssetUseExprInqireMaxNo(GamAssetUseExprInqireVO vo) {
        return (GamAssetUseExprInqireVO)getSqlMapClientTemplate().queryForObject("gamAssetUseExprInqireDao.selectAssetUseExprInqireMaxNo_S", vo);
    }
    
    /**
	 * 자산임대 연장 신청을 등록한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	public void insertAssetUseExprInqireRenew(GamAssetUseExprInqireVO vo){
		insert("gamAssetUseExprInqireDao.insertAssetUseExprInqireRenew_S", vo);
	}
	
	/**
	 * 자산임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectAssetUseExprInqireMaxMngCnt(GamAssetUseExprInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamAssetUseExprInqireDao.selectAssetUseExprInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 자산임대 연장 신청시 자산임대 상세를 복사하여 등록한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	public void insertAssetUseExprInqireDetailRenew(GamAssetUseExprInqireDetailVO vo){
		insert("gamAssetUseExprInqireDao.insertAssetUseExprInqireDetailRenew_S", vo);
	}
	
	/**
	 * 자산임대 정보를 수정한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	public void updateAssetUseExprInqire(GamAssetUseExprInqireVO vo){
		update("gamAssetUseExprInqireDao.updateAssetUseExprInqire_S", vo);
	}
	
	/**
	 * 자산임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetUseExprInqireDetailList(GamAssetUseExprInqireVO vo) throws Exception {
        return list("gamAssetUseExprInqireDao.selectAssetUseExprInqireDetailList_D", vo);
    }

    /**
	 * 자산임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetUseExprInqireDetailListTotCnt(GamAssetUseExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetUseExprInqireDao.selectAssetUseExprInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetUseExprInqireLevReqestCnt(GamAssetUseExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetUseExprInqireDao.selectAssetUseExprInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 자산임대 사진정보를 삭제한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteAssetUseExprInqirePhoto(GamAssetUseExprInqireVO vo){
		delete("gamAssetUseExprInqireDao.deleteAssetUseExprInqirePhoto_S", vo);
	}
    
	/**
	 * 자산임대 정보를 삭제한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteAssetUseExprInqire(GamAssetUseExprInqireVO vo){
		delete("gamAssetUseExprInqireDao.deleteAssetUseExprInqire_S", vo);
	}
    
	/**
	 * 자산임대 상세정보를 삭제한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteAssetUseExprInqireDetail(GamAssetUseExprInqireVO vo){
		delete("gamAssetUseExprInqireDao.deleteAssetUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 자산임대 상세를 등록한다.
	 * @param vo GamAssetUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void insertAssetUseExprInqireDetail(GamAssetUseExprInqireDetailVO vo){
		insert("gamAssetUseExprInqireDao.insertAssetUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 자산임대 상세를 수정한다.
	 * @param vo GamAssetUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetUseExprInqireDetail(GamAssetUseExprInqireDetailVO vo){
		update("gamAssetUseExprInqireDao.updateAssetUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 자산임대 상세를 삭제한다.
	 * @param vo GamAssetUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void deleteAssetUseExprInqireDetail2(GamAssetUseExprInqireDetailVO vo){
		delete("gamAssetUseExprInqireDao.deleteAssetUseExprInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대정보
	 * @exception Exception
	 */
	public GamAssetUseExprInqireVO selectAssetUseExprInqirePrmisnInfo(GamAssetUseExprInqireVO searchVO) throws Exception {
		return (GamAssetUseExprInqireVO) selectByPk("gamAssetUseExprInqireDao.selectAssetUseExprInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 자산임대 허가여부를 수정한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	public void updateAssetUseExprInqirePrmisn(GamAssetUseExprInqireLevReqestVO vo){
		update("gamAssetUseExprInqireDao.updateAssetUseExprInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamAssetUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertAssetUseExprInqireLevReqest(GamAssetUseExprInqireLevReqestVO vo){
		insert("gamAssetUseExprInqireDao.insertAssetUseExprInqireLevReqest_S", vo);
	}
	
	/**
	 * 자산임대 허가여부를 취소한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	public void updateAssetUseExprInqirePrmisnCancel(GamAssetUseExprInqireLevReqestVO vo){
		update("gamAssetUseExprInqireDao.updateAssetUseExprInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamAssetUseExprInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetUseExprInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 자산임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대상세 목록
	 * @exception Exception
	 */
    public List selectAssetUseExprInqireDetailInfo(GamAssetUseExprInqireVO vo) throws Exception {
        return list("gamAssetUseExprInqireDao.selectAssetUseExprInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamAssetUseExprInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetUseExprInqireFileList(GamAssetUseExprInqireVO searchVO) throws Exception {
        return list("gamAssetUseExprInqireDao.selectAssetUseExprInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetUseExprInqireFileListTotCnt(GamAssetUseExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetUseExprInqireDao.selectAssetUseExprInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	public void insertAssetUseExprInqireFile(GamAssetUseExprInqireVO vo){
		insert("gamAssetUseExprInqireDao.insertAssetUseExprInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	public void updateAssetUseExprInqireFile(GamAssetUseExprInqireVO vo){
		insert("gamAssetUseExprInqireDao.updateAssetUseExprInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamAssetUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteAssetUseExprInqirePhotoSingle(GamAssetUseExprInqireVO vo){
		delete("gamAssetUseExprInqireDao.deleteAssetUseExprInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetUseExprInqireVO selectAssetUseExprInqireMaxKey(GamAssetUseExprInqireVO searchVO) throws Exception {
		return (GamAssetUseExprInqireVO) selectByPk("gamAssetUseExprInqireDao.selectAssetUseExprInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamAssetUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetUseExprInqireComment(GamAssetUseExprInqireVO vo){
		update("gamAssetUseExprInqireDao.updateAssetUseExprInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetUseExprInqireVO selectAssetUseExprInqireRenewInfo(GamAssetUseExprInqireVO searchVO) throws Exception {
		return (GamAssetUseExprInqireVO) selectByPk("gamAssetUseExprInqireDao.selectAssetUseExprInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetUseExprInqireRenewInfo(GamAssetUseExprInqireVO vo){
		update("gamAssetUseExprInqireDao.updateAssetUseExprInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetUseExprInqireVO selectAssetUseExprInqireCurrRenewInfo(GamAssetUseExprInqireVO searchVO) throws Exception {
		return (GamAssetUseExprInqireVO) selectByPk("gamAssetUseExprInqireDao.selectAssetUseExprInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetUseExprInqireVO selectAssetUseExprInqireDetailQuaycd(GamAssetUseExprInqireVO searchVO) throws Exception {
		return (GamAssetUseExprInqireVO) selectByPk("gamAssetUseExprInqireDao.selectAssetUseExprInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamAssetUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetUseExprInqireQuaycd(GamAssetUseExprInqireVO vo){
		update("gamAssetUseExprInqireDao.updateAssetUseExprInqireQuaycd_S", vo);
	}
}
