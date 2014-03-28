package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrNticArrvlDtaInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrNticArrvlDtaInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrNticArrvlDtaInqireVO;

/**
 * @Class Name : gamCmmnCntrNticArrvlDtaInqireDao.java
 * @Description : 공컨장치장임대고지도래현황조회 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCmmnCntrNticArrvlDtaInqireDao")
public class GamCmmnCntrNticArrvlDtaInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 공컨장치장임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrNticArrvlDtaInqireList(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception {
        return list("gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireList_D", searchVO);
    }

    /**
	 * 공컨장치장임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrNticArrvlDtaInqireListTotCnt(GamCmmnCntrNticArrvlDtaInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrNticArrvlDtaInqireVO selectCmmnCntrNticArrvlDtaInqireSum(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamCmmnCntrNticArrvlDtaInqireVO) selectByPk("gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireSum_S", searchVO);
	}
    
	/**
	 * 공컨장치장임대 최초 신청을 등록한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertCmmnCntrNticArrvlDtaInqireFirst(GamCmmnCntrNticArrvlDtaInqireVO vo){
		insert("gamCmmnCntrNticArrvlDtaInqireDao.insertCmmnCntrNticArrvlDtaInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamCmmnCntrNticArrvlDtaInqireVO
	 * @return 공컨장치장임대관리 목록 총 갯수
	 * @exception
	 */
    public GamCmmnCntrNticArrvlDtaInqireVO selectCmmnCntrNticArrvlDtaInqireMaxNo(GamCmmnCntrNticArrvlDtaInqireVO vo) {
        return (GamCmmnCntrNticArrvlDtaInqireVO)getSqlMapClientTemplate().queryForObject("gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireMaxNo_S", vo);
    }
    
    /**
	 * 공컨장치장임대 연장 신청을 등록한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertCmmnCntrNticArrvlDtaInqireRenew(GamCmmnCntrNticArrvlDtaInqireVO vo){
		insert("gamCmmnCntrNticArrvlDtaInqireDao.insertCmmnCntrNticArrvlDtaInqireRenew_S", vo);
	}
	
	/**
	 * 공컨장치장임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectCmmnCntrNticArrvlDtaInqireMaxMngCnt(GamCmmnCntrNticArrvlDtaInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 공컨장치장임대 연장 신청시 공컨장치장임대 상세를 복사하여 등록한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertCmmnCntrNticArrvlDtaInqireDetailRenew(GamCmmnCntrNticArrvlDtaInqireDetailVO vo){
		insert("gamCmmnCntrNticArrvlDtaInqireDao.insertCmmnCntrNticArrvlDtaInqireDetailRenew_S", vo);
	}
	
	/**
	 * 공컨장치장임대 정보를 수정한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updateCmmnCntrNticArrvlDtaInqire(GamCmmnCntrNticArrvlDtaInqireVO vo){
		update("gamCmmnCntrNticArrvlDtaInqireDao.updateCmmnCntrNticArrvlDtaInqire_S", vo);
	}
	
	/**
	 * 공컨장치장임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrNticArrvlDtaInqireDetailList(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception {
        return list("gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireDetailList_D", vo);
    }

    /**
	 * 공컨장치장임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrNticArrvlDtaInqireDetailListTotCnt(GamCmmnCntrNticArrvlDtaInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrNticArrvlDtaInqireLevReqestCnt(GamCmmnCntrNticArrvlDtaInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 공컨장치장임대 사진정보를 삭제한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrNticArrvlDtaInqirePhoto(GamCmmnCntrNticArrvlDtaInqireVO vo){
		delete("gamCmmnCntrNticArrvlDtaInqireDao.deleteCmmnCntrNticArrvlDtaInqirePhoto_S", vo);
	}
    
	/**
	 * 공컨장치장임대 정보를 삭제한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrNticArrvlDtaInqire(GamCmmnCntrNticArrvlDtaInqireVO vo){
		delete("gamCmmnCntrNticArrvlDtaInqireDao.deleteCmmnCntrNticArrvlDtaInqire_S", vo);
	}
    
	/**
	 * 공컨장치장임대 상세정보를 삭제한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrNticArrvlDtaInqireDetail(GamCmmnCntrNticArrvlDtaInqireVO vo){
		delete("gamCmmnCntrNticArrvlDtaInqireDao.deleteCmmnCntrNticArrvlDtaInqireDetail_S", vo);
	}
	
	/**
	 * 공컨장치장임대 상세를 등록한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void insertCmmnCntrNticArrvlDtaInqireDetail(GamCmmnCntrNticArrvlDtaInqireDetailVO vo){
		insert("gamCmmnCntrNticArrvlDtaInqireDao.insertCmmnCntrNticArrvlDtaInqireDetail_S", vo);
	}
	
	/**
	 * 공컨장치장임대 상세를 수정한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrNticArrvlDtaInqireDetail(GamCmmnCntrNticArrvlDtaInqireDetailVO vo){
		update("gamCmmnCntrNticArrvlDtaInqireDao.updateCmmnCntrNticArrvlDtaInqireDetail_S", vo);
	}
	
	/**
	 * 공컨장치장임대 상세를 삭제한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrNticArrvlDtaInqireDetail2(GamCmmnCntrNticArrvlDtaInqireDetailVO vo){
		delete("gamCmmnCntrNticArrvlDtaInqireDao.deleteCmmnCntrNticArrvlDtaInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 공컨장치장임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대정보
	 * @exception Exception
	 */
	public GamCmmnCntrNticArrvlDtaInqireVO selectCmmnCntrNticArrvlDtaInqirePrmisnInfo(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamCmmnCntrNticArrvlDtaInqireVO) selectByPk("gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 공컨장치장임대 허가여부를 수정한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updateCmmnCntrNticArrvlDtaInqirePrmisn(GamCmmnCntrNticArrvlDtaInqireLevReqestVO vo){
		update("gamCmmnCntrNticArrvlDtaInqireDao.updateCmmnCntrNticArrvlDtaInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertCmmnCntrNticArrvlDtaInqireLevReqest(GamCmmnCntrNticArrvlDtaInqireLevReqestVO vo){
		insert("gamCmmnCntrNticArrvlDtaInqireDao.insertCmmnCntrNticArrvlDtaInqireLevReqest_S", vo);
	}
	
	/**
	 * 공컨장치장임대 허가여부를 취소한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updateCmmnCntrNticArrvlDtaInqirePrmisnCancel(GamCmmnCntrNticArrvlDtaInqireLevReqestVO vo){
		update("gamCmmnCntrNticArrvlDtaInqireDao.updateCmmnCntrNticArrvlDtaInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamCmmnCntrNticArrvlDtaInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrNticArrvlDtaInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 공컨장치장임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대상세 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrNticArrvlDtaInqireDetailInfo(GamCmmnCntrNticArrvlDtaInqireVO vo) throws Exception {
        return list("gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamCmmnCntrNticArrvlDtaInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrNticArrvlDtaInqireFileList(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception {
        return list("gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrNticArrvlDtaInqireFileListTotCnt(GamCmmnCntrNticArrvlDtaInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertCmmnCntrNticArrvlDtaInqireFile(GamCmmnCntrNticArrvlDtaInqireVO vo){
		insert("gamCmmnCntrNticArrvlDtaInqireDao.insertCmmnCntrNticArrvlDtaInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updateCmmnCntrNticArrvlDtaInqireFile(GamCmmnCntrNticArrvlDtaInqireVO vo){
		insert("gamCmmnCntrNticArrvlDtaInqireDao.updateCmmnCntrNticArrvlDtaInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamCmmnCntrNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrNticArrvlDtaInqirePhotoSingle(GamCmmnCntrNticArrvlDtaInqireVO vo){
		delete("gamCmmnCntrNticArrvlDtaInqireDao.deleteCmmnCntrNticArrvlDtaInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrNticArrvlDtaInqireVO selectCmmnCntrNticArrvlDtaInqireMaxKey(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamCmmnCntrNticArrvlDtaInqireVO) selectByPk("gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrNticArrvlDtaInqireComment(GamCmmnCntrNticArrvlDtaInqireVO vo){
		update("gamCmmnCntrNticArrvlDtaInqireDao.updateCmmnCntrNticArrvlDtaInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrNticArrvlDtaInqireVO selectCmmnCntrNticArrvlDtaInqireRenewInfo(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamCmmnCntrNticArrvlDtaInqireVO) selectByPk("gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrNticArrvlDtaInqireRenewInfo(GamCmmnCntrNticArrvlDtaInqireVO vo){
		update("gamCmmnCntrNticArrvlDtaInqireDao.updateCmmnCntrNticArrvlDtaInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrNticArrvlDtaInqireVO selectCmmnCntrNticArrvlDtaInqireCurrRenewInfo(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamCmmnCntrNticArrvlDtaInqireVO) selectByPk("gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrNticArrvlDtaInqireVO selectCmmnCntrNticArrvlDtaInqireDetailQuaycd(GamCmmnCntrNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamCmmnCntrNticArrvlDtaInqireVO) selectByPk("gamCmmnCntrNticArrvlDtaInqireDao.selectCmmnCntrNticArrvlDtaInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamCmmnCntrNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrNticArrvlDtaInqireQuaycd(GamCmmnCntrNticArrvlDtaInqireVO vo){
		update("gamCmmnCntrNticArrvlDtaInqireDao.updateCmmnCntrNticArrvlDtaInqireQuaycd_S", vo);
	}
}
