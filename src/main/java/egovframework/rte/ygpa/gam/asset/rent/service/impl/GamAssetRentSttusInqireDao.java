package egovframework.rte.ygpa.gam.asset.rent.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentSttusInqireDetailVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentSttusInqireLevReqestVO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetRentSttusInqireVO;

/**
 * @Class Name : GamAssetRentSttusInqireDao.java
 * @Description : 자산임대현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamAssetRentSttusInqireDao")
public class GamAssetRentSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 자산임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentSttusInqireList(GamAssetRentSttusInqireVO searchVO) throws Exception {
        return list("gamAssetRentSttusInqireDao.selectAssetRentSttusInqireList_D", searchVO);
    }

    /**
	 * 자산임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentSttusInqireListTotCnt(GamAssetRentSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentSttusInqireDao.selectAssetRentSttusInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetRentSttusInqireVO selectAssetRentSttusInqireSum(GamAssetRentSttusInqireVO searchVO) throws Exception {
		return (GamAssetRentSttusInqireVO) selectByPk("gamAssetRentSttusInqireDao.selectAssetRentSttusInqireSum_S", searchVO);
	}
    
	/**
	 * 자산임대 최초 신청을 등록한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	public void insertAssetRentSttusInqireFirst(GamAssetRentSttusInqireVO vo){
		insert("gamAssetRentSttusInqireDao.insertAssetRentSttusInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamAssetRentSttusInqireVO
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public GamAssetRentSttusInqireVO selectAssetRentSttusInqireMaxNo(GamAssetRentSttusInqireVO vo) {
        return (GamAssetRentSttusInqireVO)getSqlMapClientTemplate().queryForObject("gamAssetRentSttusInqireDao.selectAssetRentSttusInqireMaxNo_S", vo);
    }
    
    /**
	 * 자산임대 연장 신청을 등록한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	public void insertAssetRentSttusInqireRenew(GamAssetRentSttusInqireVO vo){
		insert("gamAssetRentSttusInqireDao.insertAssetRentSttusInqireRenew_S", vo);
	}
	
	/**
	 * 자산임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectAssetRentSttusInqireMaxMngCnt(GamAssetRentSttusInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamAssetRentSttusInqireDao.selectAssetRentSttusInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 자산임대 연장 신청시 자산임대 상세를 복사하여 등록한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	public void insertAssetRentSttusInqireDetailRenew(GamAssetRentSttusInqireDetailVO vo){
		insert("gamAssetRentSttusInqireDao.insertAssetRentSttusInqireDetailRenew_S", vo);
	}
	
	/**
	 * 자산임대 정보를 수정한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	public void updateAssetRentSttusInqire(GamAssetRentSttusInqireVO vo){
		update("gamAssetRentSttusInqireDao.updateAssetRentSttusInqire_S", vo);
	}
	
	/**
	 * 자산임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentSttusInqireDetailList(GamAssetRentSttusInqireVO vo) throws Exception {
        return list("gamAssetRentSttusInqireDao.selectAssetRentSttusInqireDetailList_D", vo);
    }

    /**
	 * 자산임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentSttusInqireDetailListTotCnt(GamAssetRentSttusInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentSttusInqireDao.selectAssetRentSttusInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentSttusInqireLevReqestCnt(GamAssetRentSttusInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentSttusInqireDao.selectAssetRentSttusInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 자산임대 사진정보를 삭제한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	public void deleteAssetRentSttusInqirePhoto(GamAssetRentSttusInqireVO vo){
		delete("gamAssetRentSttusInqireDao.deleteAssetRentSttusInqirePhoto_S", vo);
	}
    
	/**
	 * 자산임대 정보를 삭제한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	public void deleteAssetRentSttusInqire(GamAssetRentSttusInqireVO vo){
		delete("gamAssetRentSttusInqireDao.deleteAssetRentSttusInqire_S", vo);
	}
    
	/**
	 * 자산임대 상세정보를 삭제한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	public void deleteAssetRentSttusInqireDetail(GamAssetRentSttusInqireVO vo){
		delete("gamAssetRentSttusInqireDao.deleteAssetRentSttusInqireDetail_S", vo);
	}
	
	/**
	 * 자산임대 상세를 등록한다.
	 * @param vo GamAssetRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void insertAssetRentSttusInqireDetail(GamAssetRentSttusInqireDetailVO vo){
		insert("gamAssetRentSttusInqireDao.insertAssetRentSttusInqireDetail_S", vo);
	}
	
	/**
	 * 자산임대 상세를 수정한다.
	 * @param vo GamAssetRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentSttusInqireDetail(GamAssetRentSttusInqireDetailVO vo){
		update("gamAssetRentSttusInqireDao.updateAssetRentSttusInqireDetail_S", vo);
	}
	
	/**
	 * 자산임대 상세를 삭제한다.
	 * @param vo GamAssetRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void deleteAssetRentSttusInqireDetail2(GamAssetRentSttusInqireDetailVO vo){
		delete("gamAssetRentSttusInqireDao.deleteAssetRentSttusInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대정보
	 * @exception Exception
	 */
	public GamAssetRentSttusInqireVO selectAssetRentSttusInqirePrmisnInfo(GamAssetRentSttusInqireVO searchVO) throws Exception {
		return (GamAssetRentSttusInqireVO) selectByPk("gamAssetRentSttusInqireDao.selectAssetRentSttusInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 자산임대 허가여부를 수정한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	public void updateAssetRentSttusInqirePrmisn(GamAssetRentSttusInqireLevReqestVO vo){
		update("gamAssetRentSttusInqireDao.updateAssetRentSttusInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamAssetRentSttusInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertAssetRentSttusInqireLevReqest(GamAssetRentSttusInqireLevReqestVO vo){
		insert("gamAssetRentSttusInqireDao.insertAssetRentSttusInqireLevReqest_S", vo);
	}
	
	/**
	 * 자산임대 허가여부를 취소한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	public void updateAssetRentSttusInqirePrmisnCancel(GamAssetRentSttusInqireLevReqestVO vo){
		update("gamAssetRentSttusInqireDao.updateAssetRentSttusInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamAssetRentSttusInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentSttusInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 자산임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대상세 목록
	 * @exception Exception
	 */
    public List selectAssetRentSttusInqireDetailInfo(GamAssetRentSttusInqireVO vo) throws Exception {
        return list("gamAssetRentSttusInqireDao.selectAssetRentSttusInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamAssetRentSttusInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectAssetRentSttusInqireFileList(GamAssetRentSttusInqireVO searchVO) throws Exception {
        return list("gamAssetRentSttusInqireDao.selectAssetRentSttusInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectAssetRentSttusInqireFileListTotCnt(GamAssetRentSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamAssetRentSttusInqireDao.selectAssetRentSttusInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	public void insertAssetRentSttusInqireFile(GamAssetRentSttusInqireVO vo){
		insert("gamAssetRentSttusInqireDao.insertAssetRentSttusInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	public void updateAssetRentSttusInqireFile(GamAssetRentSttusInqireVO vo){
		insert("gamAssetRentSttusInqireDao.updateAssetRentSttusInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamAssetRentSttusInqireVO
	 * @exception Exception
	 */
	public void deleteAssetRentSttusInqirePhotoSingle(GamAssetRentSttusInqireVO vo){
		delete("gamAssetRentSttusInqireDao.deleteAssetRentSttusInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetRentSttusInqireVO selectAssetRentSttusInqireMaxKey(GamAssetRentSttusInqireVO searchVO) throws Exception {
		return (GamAssetRentSttusInqireVO) selectByPk("gamAssetRentSttusInqireDao.selectAssetRentSttusInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamAssetRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentSttusInqireComment(GamAssetRentSttusInqireVO vo){
		update("gamAssetRentSttusInqireDao.updateAssetRentSttusInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetRentSttusInqireVO selectAssetRentSttusInqireRenewInfo(GamAssetRentSttusInqireVO searchVO) throws Exception {
		return (GamAssetRentSttusInqireVO) selectByPk("gamAssetRentSttusInqireDao.selectAssetRentSttusInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentSttusInqireRenewInfo(GamAssetRentSttusInqireVO vo){
		update("gamAssetRentSttusInqireDao.updateAssetRentSttusInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetRentSttusInqireVO selectAssetRentSttusInqireCurrRenewInfo(GamAssetRentSttusInqireVO searchVO) throws Exception {
		return (GamAssetRentSttusInqireVO) selectByPk("gamAssetRentSttusInqireDao.selectAssetRentSttusInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetRentSttusInqireVO selectAssetRentSttusInqireDetailQuaycd(GamAssetRentSttusInqireVO searchVO) throws Exception {
		return (GamAssetRentSttusInqireVO) selectByPk("gamAssetRentSttusInqireDao.selectAssetRentSttusInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamAssetRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateAssetRentSttusInqireQuaycd(GamAssetRentSttusInqireVO vo){
		update("gamAssetRentSttusInqireDao.updateAssetRentSttusInqireQuaycd_S", vo);
	}
	
	 /**
	 * 코픽스 이자율 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectCofixInfo() throws Exception {
        return list("gamAssetRentSttusInqireDao.selectCofixInfo_S", null);
    }
    
    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetRentSttusInqireVO selectAssetRentSttusInqireBeforeQuarterInfo(GamAssetRentSttusInqireVO searchVO) throws Exception {
		return (GamAssetRentSttusInqireVO) selectByPk("gamAssetRentSttusInqireDao.selectAssetRentSttusInqireBeforeQuarterInfo_S", searchVO);
	}
	
	/**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamAssetRentSttusInqireVO selectAssetRentSttusInqireCofixInfo(GamAssetRentSttusInqireVO searchVO) throws Exception {
		return (GamAssetRentSttusInqireVO) selectByPk("gamAssetRentSttusInqireDao.selectAssetRentSttusInqireCofixInfo_S", searchVO);
	}
}
