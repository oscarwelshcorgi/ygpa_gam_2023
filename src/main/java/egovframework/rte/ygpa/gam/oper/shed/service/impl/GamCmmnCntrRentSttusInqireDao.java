package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentSttusInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentSttusInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentSttusInqireVO;

/**
 * @Class Name : GamCmmnCntrRentSttusInqireDao.java
 * @Description : 공컨장치장임대현황조회 
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCmmnCntrRentSttusInqireDao")
public class GamCmmnCntrRentSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 자산임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrRentSttusInqireList(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
        return list("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireList_D", searchVO);
    }

    /**
	 * 자산임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrRentSttusInqireListTotCnt(GamCmmnCntrRentSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireSum(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
		return (GamCmmnCntrRentSttusInqireVO) selectByPk("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireSum_S", searchVO);
	}
    
	/**
	 * 자산임대 최초 신청을 등록한다.
	 * @param vo GamCmmnCntrRentSttusInqireVO
	 * @exception Exception
	 */
	public void insertCmmnCntrRentSttusInqireFirst(GamCmmnCntrRentSttusInqireVO vo){
		insert("gamCmmnCntrRentSttusInqireDao.insertCmmnCntrRentSttusInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamCmmnCntrRentSttusInqireVO
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireMaxNo(GamCmmnCntrRentSttusInqireVO vo) {
        return (GamCmmnCntrRentSttusInqireVO)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireMaxNo_S", vo);
    }
    
    /**
	 * 자산임대 연장 신청을 등록한다.
	 * @param vo GamCmmnCntrRentSttusInqireVO
	 * @exception Exception
	 */
	public void insertCmmnCntrRentSttusInqireRenew(GamCmmnCntrRentSttusInqireVO vo){
		insert("gamCmmnCntrRentSttusInqireDao.insertCmmnCntrRentSttusInqireRenew_S", vo);
	}
	
	/**
	 * 자산임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectCmmnCntrRentSttusInqireMaxMngCnt(GamCmmnCntrRentSttusInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 자산임대 연장 신청시 자산임대 상세를 복사하여 등록한다.
	 * @param vo GamCmmnCntrRentSttusInqireVO
	 * @exception Exception
	 */
	public void insertCmmnCntrRentSttusInqireDetailRenew(GamCmmnCntrRentSttusInqireDetailVO vo){
		insert("gamCmmnCntrRentSttusInqireDao.insertCmmnCntrRentSttusInqireDetailRenew_S", vo);
	}
	
	/**
	 * 자산임대 정보를 수정한다.
	 * @param vo GamCmmnCntrRentSttusInqireVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentSttusInqire(GamCmmnCntrRentSttusInqireVO vo){
		update("gamCmmnCntrRentSttusInqireDao.updateCmmnCntrRentSttusInqire_S", vo);
	}
	
	/**
	 * 자산임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrRentSttusInqireDetailList(GamCmmnCntrRentSttusInqireVO vo) throws Exception {
        return list("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireDetailList_D", vo);
    }

    /**
	 * 자산임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrRentSttusInqireDetailListTotCnt(GamCmmnCntrRentSttusInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrRentSttusInqireLevReqestCnt(GamCmmnCntrRentSttusInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 자산임대 사진정보를 삭제한다.
	 * @param vo GamCmmnCntrRentSttusInqireVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrRentSttusInqirePhoto(GamCmmnCntrRentSttusInqireVO vo){
		delete("gamCmmnCntrRentSttusInqireDao.deleteCmmnCntrRentSttusInqirePhoto_S", vo);
	}
    
	/**
	 * 자산임대 정보를 삭제한다.
	 * @param vo GamCmmnCntrRentSttusInqireVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrRentSttusInqire(GamCmmnCntrRentSttusInqireVO vo){
		delete("gamCmmnCntrRentSttusInqireDao.deleteCmmnCntrRentSttusInqire_S", vo);
	}
    
	/**
	 * 자산임대 상세정보를 삭제한다.
	 * @param vo GamCmmnCntrRentSttusInqireVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrRentSttusInqireDetail(GamCmmnCntrRentSttusInqireVO vo){
		delete("gamCmmnCntrRentSttusInqireDao.deleteCmmnCntrRentSttusInqireDetail_S", vo);
	}
	
	/**
	 * 자산임대 상세를 등록한다.
	 * @param vo GamCmmnCntrRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void insertCmmnCntrRentSttusInqireDetail(GamCmmnCntrRentSttusInqireDetailVO vo){
		insert("gamCmmnCntrRentSttusInqireDao.insertCmmnCntrRentSttusInqireDetail_S", vo);
	}
	
	/**
	 * 자산임대 상세를 수정한다.
	 * @param vo GamCmmnCntrRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentSttusInqireDetail(GamCmmnCntrRentSttusInqireDetailVO vo){
		update("gamCmmnCntrRentSttusInqireDao.updateCmmnCntrRentSttusInqireDetail_S", vo);
	}
	
	/**
	 * 자산임대 상세를 삭제한다.
	 * @param vo GamCmmnCntrRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrRentSttusInqireDetail2(GamCmmnCntrRentSttusInqireDetailVO vo){
		delete("gamCmmnCntrRentSttusInqireDao.deleteCmmnCntrRentSttusInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 자산임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대정보
	 * @exception Exception
	 */
	public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqirePrmisnInfo(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
		return (GamCmmnCntrRentSttusInqireVO) selectByPk("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 자산임대 허가여부를 수정한다.
	 * @param vo GamCmmnCntrRentSttusInqireVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentSttusInqirePrmisn(GamCmmnCntrRentSttusInqireLevReqestVO vo){
		update("gamCmmnCntrRentSttusInqireDao.updateCmmnCntrRentSttusInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamCmmnCntrRentSttusInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertCmmnCntrRentSttusInqireLevReqest(GamCmmnCntrRentSttusInqireLevReqestVO vo){
		insert("gamCmmnCntrRentSttusInqireDao.insertCmmnCntrRentSttusInqireLevReqest_S", vo);
	}
	
	/**
	 * 자산임대 허가여부를 취소한다.
	 * @param vo GamCmmnCntrRentSttusInqireVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentSttusInqirePrmisnCancel(GamCmmnCntrRentSttusInqireLevReqestVO vo){
		update("gamCmmnCntrRentSttusInqireDao.updateCmmnCntrRentSttusInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamCmmnCntrRentSttusInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentSttusInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 자산임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대상세 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrRentSttusInqireDetailInfo(GamCmmnCntrRentSttusInqireVO vo) throws Exception {
        return list("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamCmmnCntrRentSttusInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrRentSttusInqireFileList(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
        return list("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrRentSttusInqireFileListTotCnt(GamCmmnCntrRentSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamCmmnCntrRentSttusInqireVO
	 * @exception Exception
	 */
	public void insertCmmnCntrRentSttusInqireFile(GamCmmnCntrRentSttusInqireVO vo){
		insert("gamCmmnCntrRentSttusInqireDao.insertCmmnCntrRentSttusInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamCmmnCntrRentSttusInqireVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentSttusInqireFile(GamCmmnCntrRentSttusInqireVO vo){
		insert("gamCmmnCntrRentSttusInqireDao.updateCmmnCntrRentSttusInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamCmmnCntrRentSttusInqireVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrRentSttusInqirePhotoSingle(GamCmmnCntrRentSttusInqireVO vo){
		delete("gamCmmnCntrRentSttusInqireDao.deleteCmmnCntrRentSttusInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireMaxKey(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
		return (GamCmmnCntrRentSttusInqireVO) selectByPk("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamCmmnCntrRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentSttusInqireComment(GamCmmnCntrRentSttusInqireVO vo){
		update("gamCmmnCntrRentSttusInqireDao.updateCmmnCntrRentSttusInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireRenewInfo(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
		return (GamCmmnCntrRentSttusInqireVO) selectByPk("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamCmmnCntrRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentSttusInqireRenewInfo(GamCmmnCntrRentSttusInqireVO vo){
		update("gamCmmnCntrRentSttusInqireDao.updateCmmnCntrRentSttusInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireCurrRenewInfo(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
		return (GamCmmnCntrRentSttusInqireVO) selectByPk("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireDetailQuaycd(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
		return (GamCmmnCntrRentSttusInqireVO) selectByPk("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamCmmnCntrRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentSttusInqireQuaycd(GamCmmnCntrRentSttusInqireVO vo){
		update("gamCmmnCntrRentSttusInqireDao.updateCmmnCntrRentSttusInqireQuaycd_S", vo);
	}
	
	 /**
	 * 코픽스 이자율 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectCofixInfo() throws Exception {
        return list("gamCmmnCntrRentSttusInqireDao.selectCofixInfo_S", null);
    }
    
    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireBeforeQuarterInfo(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
		return (GamCmmnCntrRentSttusInqireVO) selectByPk("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireBeforeQuarterInfo_S", searchVO);
	}
	
	/**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrRentSttusInqireVO selectCmmnCntrRentSttusInqireCofixInfo(GamCmmnCntrRentSttusInqireVO searchVO) throws Exception {
		return (GamCmmnCntrRentSttusInqireVO) selectByPk("gamCmmnCntrRentSttusInqireDao.selectCmmnCntrRentSttusInqireCofixInfo_S", searchVO);
	}
}
