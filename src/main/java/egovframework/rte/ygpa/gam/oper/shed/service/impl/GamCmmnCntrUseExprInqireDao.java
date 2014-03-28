package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrUseExprInqireVO;

/**
 * @Class Name : gamCmmnCntrUseExprInqireDao.java
 * @Description : 공컨장치장임대만기도래자료조회 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCmmnCntrUseExprInqireDao")
public class GamCmmnCntrUseExprInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 공컨장치장임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrUseExprInqireList(GamCmmnCntrUseExprInqireVO searchVO) throws Exception {
        return list("gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireList_D", searchVO);
    }

    /**
	 * 공컨장치장임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrUseExprInqireListTotCnt(GamCmmnCntrUseExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrUseExprInqireVO selectCmmnCntrUseExprInqireSum(GamCmmnCntrUseExprInqireVO searchVO) throws Exception {
		return (GamCmmnCntrUseExprInqireVO) selectByPk("gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireSum_S", searchVO);
	}
    
	/**
	 * 공컨장치장임대 최초 신청을 등록한다.
	 * @param vo GamCmmnCntrUseExprInqireVO
	 * @exception Exception
	 */
	public void insertCmmnCntrUseExprInqireFirst(GamCmmnCntrUseExprInqireVO vo){
		insert("gamCmmnCntrUseExprInqireDao.insertCmmnCntrUseExprInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamCmmnCntrUseExprInqireVO
	 * @return 공컨장치장임대관리 목록 총 갯수
	 * @exception
	 */
    public GamCmmnCntrUseExprInqireVO selectCmmnCntrUseExprInqireMaxNo(GamCmmnCntrUseExprInqireVO vo) {
        return (GamCmmnCntrUseExprInqireVO)getSqlMapClientTemplate().queryForObject("gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireMaxNo_S", vo);
    }
    
    /**
	 * 공컨장치장임대 연장 신청을 등록한다.
	 * @param vo GamCmmnCntrUseExprInqireVO
	 * @exception Exception
	 */
	public void insertCmmnCntrUseExprInqireRenew(GamCmmnCntrUseExprInqireVO vo){
		insert("gamCmmnCntrUseExprInqireDao.insertCmmnCntrUseExprInqireRenew_S", vo);
	}
	
	/**
	 * 공컨장치장임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectCmmnCntrUseExprInqireMaxMngCnt(GamCmmnCntrUseExprInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 공컨장치장임대 연장 신청시 공컨장치장임대 상세를 복사하여 등록한다.
	 * @param vo GamCmmnCntrUseExprInqireVO
	 * @exception Exception
	 */
	public void insertCmmnCntrUseExprInqireDetailRenew(GamCmmnCntrUseExprInqireDetailVO vo){
		insert("gamCmmnCntrUseExprInqireDao.insertCmmnCntrUseExprInqireDetailRenew_S", vo);
	}
	
	/**
	 * 공컨장치장임대 정보를 수정한다.
	 * @param vo GamCmmnCntrUseExprInqireVO
	 * @exception Exception
	 */
	public void updateCmmnCntrUseExprInqire(GamCmmnCntrUseExprInqireVO vo){
		update("gamCmmnCntrUseExprInqireDao.updateCmmnCntrUseExprInqire_S", vo);
	}
	
	/**
	 * 공컨장치장임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrUseExprInqireDetailList(GamCmmnCntrUseExprInqireVO vo) throws Exception {
        return list("gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireDetailList_D", vo);
    }

    /**
	 * 공컨장치장임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrUseExprInqireDetailListTotCnt(GamCmmnCntrUseExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrUseExprInqireLevReqestCnt(GamCmmnCntrUseExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 공컨장치장임대 사진정보를 삭제한다.
	 * @param vo GamCmmnCntrUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrUseExprInqirePhoto(GamCmmnCntrUseExprInqireVO vo){
		delete("gamCmmnCntrUseExprInqireDao.deleteCmmnCntrUseExprInqirePhoto_S", vo);
	}
    
	/**
	 * 공컨장치장임대 정보를 삭제한다.
	 * @param vo GamCmmnCntrUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrUseExprInqire(GamCmmnCntrUseExprInqireVO vo){
		delete("gamCmmnCntrUseExprInqireDao.deleteCmmnCntrUseExprInqire_S", vo);
	}
    
	/**
	 * 공컨장치장임대 상세정보를 삭제한다.
	 * @param vo GamCmmnCntrUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrUseExprInqireDetail(GamCmmnCntrUseExprInqireVO vo){
		delete("gamCmmnCntrUseExprInqireDao.deleteCmmnCntrUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 공컨장치장임대 상세를 등록한다.
	 * @param vo GamCmmnCntrUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void insertCmmnCntrUseExprInqireDetail(GamCmmnCntrUseExprInqireDetailVO vo){
		insert("gamCmmnCntrUseExprInqireDao.insertCmmnCntrUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 공컨장치장임대 상세를 수정한다.
	 * @param vo GamCmmnCntrUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrUseExprInqireDetail(GamCmmnCntrUseExprInqireDetailVO vo){
		update("gamCmmnCntrUseExprInqireDao.updateCmmnCntrUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 공컨장치장임대 상세를 삭제한다.
	 * @param vo GamCmmnCntrUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrUseExprInqireDetail2(GamCmmnCntrUseExprInqireDetailVO vo){
		delete("gamCmmnCntrUseExprInqireDao.deleteCmmnCntrUseExprInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 공컨장치장임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대정보
	 * @exception Exception
	 */
	public GamCmmnCntrUseExprInqireVO selectCmmnCntrUseExprInqirePrmisnInfo(GamCmmnCntrUseExprInqireVO searchVO) throws Exception {
		return (GamCmmnCntrUseExprInqireVO) selectByPk("gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 공컨장치장임대 허가여부를 수정한다.
	 * @param vo GamCmmnCntrUseExprInqireVO
	 * @exception Exception
	 */
	public void updateCmmnCntrUseExprInqirePrmisn(GamCmmnCntrUseExprInqireLevReqestVO vo){
		update("gamCmmnCntrUseExprInqireDao.updateCmmnCntrUseExprInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamCmmnCntrUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertCmmnCntrUseExprInqireLevReqest(GamCmmnCntrUseExprInqireLevReqestVO vo){
		insert("gamCmmnCntrUseExprInqireDao.insertCmmnCntrUseExprInqireLevReqest_S", vo);
	}
	
	/**
	 * 공컨장치장임대 허가여부를 취소한다.
	 * @param vo GamCmmnCntrUseExprInqireVO
	 * @exception Exception
	 */
	public void updateCmmnCntrUseExprInqirePrmisnCancel(GamCmmnCntrUseExprInqireLevReqestVO vo){
		update("gamCmmnCntrUseExprInqireDao.updateCmmnCntrUseExprInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamCmmnCntrUseExprInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrUseExprInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 공컨장치장임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대상세 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrUseExprInqireDetailInfo(GamCmmnCntrUseExprInqireVO vo) throws Exception {
        return list("gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamCmmnCntrUseExprInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrUseExprInqireFileList(GamCmmnCntrUseExprInqireVO searchVO) throws Exception {
        return list("gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrUseExprInqireFileListTotCnt(GamCmmnCntrUseExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamCmmnCntrUseExprInqireVO
	 * @exception Exception
	 */
	public void insertCmmnCntrUseExprInqireFile(GamCmmnCntrUseExprInqireVO vo){
		insert("gamCmmnCntrUseExprInqireDao.insertCmmnCntrUseExprInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamCmmnCntrUseExprInqireVO
	 * @exception Exception
	 */
	public void updateCmmnCntrUseExprInqireFile(GamCmmnCntrUseExprInqireVO vo){
		insert("gamCmmnCntrUseExprInqireDao.updateCmmnCntrUseExprInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamCmmnCntrUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrUseExprInqirePhotoSingle(GamCmmnCntrUseExprInqireVO vo){
		delete("gamCmmnCntrUseExprInqireDao.deleteCmmnCntrUseExprInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrUseExprInqireVO selectCmmnCntrUseExprInqireMaxKey(GamCmmnCntrUseExprInqireVO searchVO) throws Exception {
		return (GamCmmnCntrUseExprInqireVO) selectByPk("gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamCmmnCntrUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrUseExprInqireComment(GamCmmnCntrUseExprInqireVO vo){
		update("gamCmmnCntrUseExprInqireDao.updateCmmnCntrUseExprInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrUseExprInqireVO selectCmmnCntrUseExprInqireRenewInfo(GamCmmnCntrUseExprInqireVO searchVO) throws Exception {
		return (GamCmmnCntrUseExprInqireVO) selectByPk("gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamCmmnCntrUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrUseExprInqireRenewInfo(GamCmmnCntrUseExprInqireVO vo){
		update("gamCmmnCntrUseExprInqireDao.updateCmmnCntrUseExprInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrUseExprInqireVO selectCmmnCntrUseExprInqireCurrRenewInfo(GamCmmnCntrUseExprInqireVO searchVO) throws Exception {
		return (GamCmmnCntrUseExprInqireVO) selectByPk("gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrUseExprInqireVO selectCmmnCntrUseExprInqireDetailQuaycd(GamCmmnCntrUseExprInqireVO searchVO) throws Exception {
		return (GamCmmnCntrUseExprInqireVO) selectByPk("gamCmmnCntrUseExprInqireDao.selectCmmnCntrUseExprInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamCmmnCntrUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrUseExprInqireQuaycd(GamCmmnCntrUseExprInqireVO vo){
		update("gamCmmnCntrUseExprInqireDao.updateCmmnCntrUseExprInqireQuaycd_S", vo);
	}
}
