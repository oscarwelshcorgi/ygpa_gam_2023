package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyUseExprInqireVO;

/**
 * @Class Name : gamPrtFcltyUseExprInqireDao.java
 * @Description : 항만시설사용만기도래자료조회 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamPrtFcltyUseExprInqireDao")
public class GamPrtFcltyUseExprInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 항만시설사용관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyUseExprInqireList(GamPrtFcltyUseExprInqireVO searchVO) throws Exception {
        return list("gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireList_D", searchVO);
    }

    /**
	 * 항만시설사용관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyUseExprInqireListTotCnt(GamPrtFcltyUseExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireSum(GamPrtFcltyUseExprInqireVO searchVO) throws Exception {
		return (GamPrtFcltyUseExprInqireVO) selectByPk("gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireSum_S", searchVO);
	}
    
	/**
	 * 항만시설사용 최초 신청을 등록한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void insertPrtFcltyUseExprInqireFirst(GamPrtFcltyUseExprInqireVO vo){
		insert("gamPrtFcltyUseExprInqireDao.insertPrtFcltyUseExprInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamPrtFcltyUseExprInqireVO
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireMaxNo(GamPrtFcltyUseExprInqireVO vo) {
        return (GamPrtFcltyUseExprInqireVO)getSqlMapClientTemplate().queryForObject("gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireMaxNo_S", vo);
    }
    
    /**
	 * 항만시설사용 연장 신청을 등록한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void insertPrtFcltyUseExprInqireRenew(GamPrtFcltyUseExprInqireVO vo){
		insert("gamPrtFcltyUseExprInqireDao.insertPrtFcltyUseExprInqireRenew_S", vo);
	}
	
	/**
	 * 항만시설사용 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public String selectPrtFcltyUseExprInqireMaxMngCnt(GamPrtFcltyUseExprInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 항만시설사용 연장 신청시 항만시설사용 상세를 복사하여 등록한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void insertPrtFcltyUseExprInqireDetailRenew(GamPrtFcltyUseExprInqireDetailVO vo){
		insert("gamPrtFcltyUseExprInqireDao.insertPrtFcltyUseExprInqireDetailRenew_S", vo);
	}
	
	/**
	 * 항만시설사용 정보를 수정한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqire(GamPrtFcltyUseExprInqireVO vo){
		update("gamPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqire_S", vo);
	}
	
	/**
	 * 항만시설사용관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyUseExprInqireDetailList(GamPrtFcltyUseExprInqireVO vo) throws Exception {
        return list("gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireDetailList_D", vo);
    }

    /**
	 * 항만시설사용관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyUseExprInqireDetailListTotCnt(GamPrtFcltyUseExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyUseExprInqireLevReqestCnt(GamPrtFcltyUseExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 항만시설사용 사진정보를 삭제한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void deletePrtFcltyUseExprInqirePhoto(GamPrtFcltyUseExprInqireVO vo){
		delete("gamPrtFcltyUseExprInqireDao.deletePrtFcltyUseExprInqirePhoto_S", vo);
	}
    
	/**
	 * 항만시설사용 정보를 삭제한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void deletePrtFcltyUseExprInqire(GamPrtFcltyUseExprInqireVO vo){
		delete("gamPrtFcltyUseExprInqireDao.deletePrtFcltyUseExprInqire_S", vo);
	}
    
	/**
	 * 항만시설사용 상세정보를 삭제한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void deletePrtFcltyUseExprInqireDetail(GamPrtFcltyUseExprInqireVO vo){
		delete("gamPrtFcltyUseExprInqireDao.deletePrtFcltyUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 항만시설사용 상세를 등록한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void insertPrtFcltyUseExprInqireDetail(GamPrtFcltyUseExprInqireDetailVO vo){
		insert("gamPrtFcltyUseExprInqireDao.insertPrtFcltyUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 항만시설사용 상세를 수정한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqireDetail(GamPrtFcltyUseExprInqireDetailVO vo){
		update("gamPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 항만시설사용 상세를 삭제한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void deletePrtFcltyUseExprInqireDetail2(GamPrtFcltyUseExprInqireDetailVO vo){
		delete("gamPrtFcltyUseExprInqireDao.deletePrtFcltyUseExprInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
	public GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqirePrmisnInfo(GamPrtFcltyUseExprInqireVO searchVO) throws Exception {
		return (GamPrtFcltyUseExprInqireVO) selectByPk("gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 항만시설사용 허가여부를 수정한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqirePrmisn(GamPrtFcltyUseExprInqireLevReqestVO vo){
		update("gamPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamPrtFcltyUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertPrtFcltyUseExprInqireLevReqest(GamPrtFcltyUseExprInqireLevReqestVO vo){
		insert("gamPrtFcltyUseExprInqireDao.insertPrtFcltyUseExprInqireLevReqest_S", vo);
	}
	
	/**
	 * 항만시설사용 허가여부를 취소한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqirePrmisnCancel(GamPrtFcltyUseExprInqireLevReqestVO vo){
		update("gamPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamPrtFcltyUseExprInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyUseExprInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 항만시설사용상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용상세 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyUseExprInqireDetailInfo(GamPrtFcltyUseExprInqireVO vo) throws Exception {
        return list("gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamPrtFcltyUseExprInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyUseExprInqireFileList(GamPrtFcltyUseExprInqireVO searchVO) throws Exception {
        return list("gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyUseExprInqireFileListTotCnt(GamPrtFcltyUseExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void insertPrtFcltyUseExprInqireFile(GamPrtFcltyUseExprInqireVO vo){
		insert("gamPrtFcltyUseExprInqireDao.insertPrtFcltyUseExprInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqireFile(GamPrtFcltyUseExprInqireVO vo){
		insert("gamPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void deletePrtFcltyUseExprInqirePhotoSingle(GamPrtFcltyUseExprInqireVO vo){
		delete("gamPrtFcltyUseExprInqireDao.deletePrtFcltyUseExprInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireMaxKey(GamPrtFcltyUseExprInqireVO searchVO) throws Exception {
		return (GamPrtFcltyUseExprInqireVO) selectByPk("gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqireComment(GamPrtFcltyUseExprInqireVO vo){
		update("gamPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireRenewInfo(GamPrtFcltyUseExprInqireVO searchVO) throws Exception {
		return (GamPrtFcltyUseExprInqireVO) selectByPk("gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqireRenewInfo(GamPrtFcltyUseExprInqireVO vo){
		update("gamPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireCurrRenewInfo(GamPrtFcltyUseExprInqireVO searchVO) throws Exception {
		return (GamPrtFcltyUseExprInqireVO) selectByPk("gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireDetailQuaycd(GamPrtFcltyUseExprInqireVO searchVO) throws Exception {
		return (GamPrtFcltyUseExprInqireVO) selectByPk("gamPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqireQuaycd(GamPrtFcltyUseExprInqireVO vo){
		update("gamPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqireQuaycd_S", vo);
	}
}
