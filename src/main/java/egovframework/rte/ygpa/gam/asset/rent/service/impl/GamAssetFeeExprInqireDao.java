package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetFeeExprInqireDetailVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetFeeExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetFeeExprInqireVO;

/**
 * @Class Name : gamAssetFeeExprInqireDao.java
 * @Description : 자산임대고지도래자료조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetFeeExprInqireDao")
public class GamAssetFeeExprInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 자산임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetFeeExprInqireList(GamAssetFeeExprInqireVO searchVO) throws Exception {
        return list("gamAssetFeeExprInqireDao.selectAssetFeeExprInqireList_D", searchVO);
    }

    /**
	 * 자산임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetFeeExprInqireListTotCnt(GamAssetFeeExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetFeeExprInqireDao.selectAssetFeeExprInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetFeeExprInqireVO selectAssetFeeExprInqireSum(GamAssetFeeExprInqireVO searchVO) throws Exception {
		return (GamAssetFeeExprInqireVO) selectByPk("gamAssetFeeExprInqireDao.selectAssetFeeExprInqireSum_S", searchVO);
	}
    
	/**
	 * 자산임대 최초 신청을 등록한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	public void insertAssetFeeExprInqireFirst(GamAssetFeeExprInqireVO vo){
		insert("gamAssetFeeExprInqireDao.insertAssetFeeExprInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamAssetFeeExprInqireVO
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public GamAssetFeeExprInqireVO selectAssetFeeExprInqireMaxNo(GamAssetFeeExprInqireVO vo) {
        return (GamAssetFeeExprInqireVO)getSqlMapClientTemplate().queryForObject("gamAssetFeeExprInqireDao.selectAssetFeeExprInqireMaxNo_S", vo);
    }
    
    /**
	 * 자산임대 연장 신청을 등록한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	public void insertAssetFeeExprInqireRenew(GamAssetFeeExprInqireVO vo){
		insert("gamAssetFeeExprInqireDao.insertAssetFeeExprInqireRenew_S", vo);
	}
	
	/**
	 * 자산임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectAssetFeeExprInqireMaxMngCnt(GamAssetFeeExprInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamAssetFeeExprInqireDao.selectAssetFeeExprInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 자산임대 연장 신청시 자산임대 상세를 복사하여 등록한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	public void insertAssetFeeExprInqireDetailRenew(GamAssetFeeExprInqireDetailVO vo){
		insert("gamAssetFeeExprInqireDao.insertAssetFeeExprInqireDetailRenew_S", vo);
	}
	
	/**
	 * 자산임대 정보를 수정한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	public void updateAssetFeeExprInqire(GamAssetFeeExprInqireVO vo){
		update("gamAssetFeeExprInqireDao.updateAssetFeeExprInqire_S", vo);
	}
	
	/**
	 * 자산임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetFeeExprInqireDetailList(GamAssetFeeExprInqireVO vo) throws Exception {
        return list("gamAssetFeeExprInqireDao.selectAssetFeeExprInqireDetailList_D", vo);
    }

    /**
	 * 자산임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetFeeExprInqireDetailListTotCnt(GamAssetFeeExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetFeeExprInqireDao.selectAssetFeeExprInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetFeeExprInqireLevReqestCnt(GamAssetFeeExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetFeeExprInqireDao.selectAssetFeeExprInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 자산임대 사진정보를 삭제한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	public void deleteAssetFeeExprInqirePhoto(GamAssetFeeExprInqireVO vo){
		delete("gamAssetFeeExprInqireDao.deleteAssetFeeExprInqirePhoto_S", vo);
	}
    
	/**
	 * 자산임대 정보를 삭제한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	public void deleteAssetFeeExprInqire(GamAssetFeeExprInqireVO vo){
		delete("gamAssetFeeExprInqireDao.deleteAssetFeeExprInqire_S", vo);
	}
    
	/**
	 * 자산임대 상세정보를 삭제한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	public void deleteAssetFeeExprInqireDetail(GamAssetFeeExprInqireVO vo){
		delete("gamAssetFeeExprInqireDao.deleteAssetFeeExprInqireDetail_S", vo);
	}
	
	/**
	 * 자산임대 상세를 등록한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void insertAssetFeeExprInqireDetail(GamAssetFeeExprInqireDetailVO vo){
		insert("gamAssetFeeExprInqireDao.insertAssetFeeExprInqireDetail_S", vo);
	}
	
	/**
	 * 자산임대 상세를 수정한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetFeeExprInqireDetail(GamAssetFeeExprInqireDetailVO vo){
		update("gamAssetFeeExprInqireDao.updateAssetFeeExprInqireDetail_S", vo);
	}
	
	/**
	 * 자산임대 상세를 삭제한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void deleteAssetFeeExprInqireDetail2(GamAssetFeeExprInqireDetailVO vo){
		delete("gamAssetFeeExprInqireDao.deleteAssetFeeExprInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대정보
	 * @exception Exception
	 */
	public GamAssetFeeExprInqireVO selectAssetFeeExprInqirePrmisnInfo(GamAssetFeeExprInqireVO searchVO) throws Exception {
		return (GamAssetFeeExprInqireVO) selectByPk("gamAssetFeeExprInqireDao.selectAssetFeeExprInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 자산임대 허가여부를 수정한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	public void updateAssetFeeExprInqirePrmisn(GamAssetFeeExprInqireLevReqestVO vo){
		update("gamAssetFeeExprInqireDao.updateAssetFeeExprInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamAssetFeeExprInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertAssetFeeExprInqireLevReqest(GamAssetFeeExprInqireLevReqestVO vo){
		insert("gamAssetFeeExprInqireDao.insertAssetFeeExprInqireLevReqest_S", vo);
	}
	
	/**
	 * 자산임대 허가여부를 취소한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	public void updateAssetFeeExprInqirePrmisnCancel(GamAssetFeeExprInqireLevReqestVO vo){
		update("gamAssetFeeExprInqireDao.updateAssetFeeExprInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamAssetFeeExprInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetFeeExprInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 자산임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대상세 목록
	 * @exception Exception
	 */
    public List selectAssetFeeExprInqireDetailInfo(GamAssetFeeExprInqireVO vo) throws Exception {
        return list("gamAssetFeeExprInqireDao.selectAssetFeeExprInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamAssetFeeExprInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetFeeExprInqireFileList(GamAssetFeeExprInqireVO searchVO) throws Exception {
        return list("gamAssetFeeExprInqireDao.selectAssetFeeExprInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetFeeExprInqireFileListTotCnt(GamAssetFeeExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetFeeExprInqireDao.selectAssetFeeExprInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	public void insertAssetFeeExprInqireFile(GamAssetFeeExprInqireVO vo){
		insert("gamAssetFeeExprInqireDao.insertAssetFeeExprInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	public void updateAssetFeeExprInqireFile(GamAssetFeeExprInqireVO vo){
		insert("gamAssetFeeExprInqireDao.updateAssetFeeExprInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamAssetFeeExprInqireVO
	 * @exception Exception
	 */
	public void deleteAssetFeeExprInqirePhotoSingle(GamAssetFeeExprInqireVO vo){
		delete("gamAssetFeeExprInqireDao.deleteAssetFeeExprInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetFeeExprInqireVO selectAssetFeeExprInqireMaxKey(GamAssetFeeExprInqireVO searchVO) throws Exception {
		return (GamAssetFeeExprInqireVO) selectByPk("gamAssetFeeExprInqireDao.selectAssetFeeExprInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetFeeExprInqireComment(GamAssetFeeExprInqireVO vo){
		update("gamAssetFeeExprInqireDao.updateAssetFeeExprInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetFeeExprInqireVO selectAssetFeeExprInqireRenewInfo(GamAssetFeeExprInqireVO searchVO) throws Exception {
		return (GamAssetFeeExprInqireVO) selectByPk("gamAssetFeeExprInqireDao.selectAssetFeeExprInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetFeeExprInqireRenewInfo(GamAssetFeeExprInqireVO vo){
		update("gamAssetFeeExprInqireDao.updateAssetFeeExprInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetFeeExprInqireVO selectAssetFeeExprInqireCurrRenewInfo(GamAssetFeeExprInqireVO searchVO) throws Exception {
		return (GamAssetFeeExprInqireVO) selectByPk("gamAssetFeeExprInqireDao.selectAssetFeeExprInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetFeeExprInqireVO selectAssetFeeExprInqireDetailQuaycd(GamAssetFeeExprInqireVO searchVO) throws Exception {
		return (GamAssetFeeExprInqireVO) selectByPk("gamAssetFeeExprInqireDao.selectAssetFeeExprInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamAssetFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetFeeExprInqireQuaycd(GamAssetFeeExprInqireVO vo){
		update("gamAssetFeeExprInqireDao.updateAssetFeeExprInqireQuaycd_S", vo);
	}
}
