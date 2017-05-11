package egovframework.rte.ygpa.gam.oper.gnrltest.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyUseExprInqireVO;

/**
 * @Class Name : gamTestPrtFcltyUseExprInqireDao.java
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
@Repository("gamTestPrtFcltyUseExprInqireDao")
public class GamTestPrtFcltyUseExprInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 항만시설사용관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyUseExprInqireList(GamTestPrtFcltyUseExprInqireVO searchVO) throws Exception {
        return list("gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireList_D", searchVO);
    }

    /**
	 * 항만시설사용관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyUseExprInqireListTotCnt(GamTestPrtFcltyUseExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireSum(GamTestPrtFcltyUseExprInqireVO searchVO) throws Exception {
		return (GamTestPrtFcltyUseExprInqireVO) selectByPk("gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireSum_S", searchVO);
	}
    
	/**
	 * 항만시설사용 최초 신청을 등록한다.
	 * @param vo GamTestPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void insertPrtFcltyUseExprInqireFirst(GamTestPrtFcltyUseExprInqireVO vo){
		insert("gamTestPrtFcltyUseExprInqireDao.insertPrtFcltyUseExprInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamTestPrtFcltyUseExprInqireVO
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public GamTestPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireMaxNo(GamTestPrtFcltyUseExprInqireVO vo) {
        return (GamTestPrtFcltyUseExprInqireVO)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireMaxNo_S", vo);
    }
    
    /**
	 * 항만시설사용 연장 신청을 등록한다.
	 * @param vo GamTestPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void insertPrtFcltyUseExprInqireRenew(GamTestPrtFcltyUseExprInqireVO vo){
		insert("gamTestPrtFcltyUseExprInqireDao.insertPrtFcltyUseExprInqireRenew_S", vo);
	}
	
	/**
	 * 항만시설사용 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public String selectPrtFcltyUseExprInqireMaxMngCnt(GamTestPrtFcltyUseExprInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 항만시설사용 연장 신청시 항만시설사용 상세를 복사하여 등록한다.
	 * @param vo GamTestPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void insertPrtFcltyUseExprInqireDetailRenew(GamTestPrtFcltyUseExprInqireDetailVO vo){
		insert("gamTestPrtFcltyUseExprInqireDao.insertPrtFcltyUseExprInqireDetailRenew_S", vo);
	}
	
	/**
	 * 항만시설사용 정보를 수정한다.
	 * @param vo GamTestPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqire(GamTestPrtFcltyUseExprInqireVO vo){
		update("gamTestPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqire_S", vo);
	}
	
	/**
	 * 항만시설사용관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyUseExprInqireDetailList(GamTestPrtFcltyUseExprInqireVO vo) throws Exception {
        return list("gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireDetailList_D", vo);
    }

    /**
	 * 항만시설사용관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyUseExprInqireDetailListTotCnt(GamTestPrtFcltyUseExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyUseExprInqireLevReqestCnt(GamTestPrtFcltyUseExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 항만시설사용 사진정보를 삭제한다.
	 * @param vo GamTestPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void deletePrtFcltyUseExprInqirePhoto(GamTestPrtFcltyUseExprInqireVO vo){
		delete("gamTestPrtFcltyUseExprInqireDao.deletePrtFcltyUseExprInqirePhoto_S", vo);
	}
    
	/**
	 * 항만시설사용 정보를 삭제한다.
	 * @param vo GamTestPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void deletePrtFcltyUseExprInqire(GamTestPrtFcltyUseExprInqireVO vo){
		delete("gamTestPrtFcltyUseExprInqireDao.deletePrtFcltyUseExprInqire_S", vo);
	}
    
	/**
	 * 항만시설사용 상세정보를 삭제한다.
	 * @param vo GamTestPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void deletePrtFcltyUseExprInqireDetail(GamTestPrtFcltyUseExprInqireVO vo){
		delete("gamTestPrtFcltyUseExprInqireDao.deletePrtFcltyUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 항만시설사용 상세를 등록한다.
	 * @param vo GamTestPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void insertPrtFcltyUseExprInqireDetail(GamTestPrtFcltyUseExprInqireDetailVO vo){
		insert("gamTestPrtFcltyUseExprInqireDao.insertPrtFcltyUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 항만시설사용 상세를 수정한다.
	 * @param vo GamTestPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqireDetail(GamTestPrtFcltyUseExprInqireDetailVO vo){
		update("gamTestPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 항만시설사용 상세를 삭제한다.
	 * @param vo GamTestPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void deletePrtFcltyUseExprInqireDetail2(GamTestPrtFcltyUseExprInqireDetailVO vo){
		delete("gamTestPrtFcltyUseExprInqireDao.deletePrtFcltyUseExprInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
	public GamTestPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqirePrmisnInfo(GamTestPrtFcltyUseExprInqireVO searchVO) throws Exception {
		return (GamTestPrtFcltyUseExprInqireVO) selectByPk("gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 항만시설사용 허가여부를 수정한다.
	 * @param vo GamTestPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqirePrmisn(GamTestPrtFcltyUseExprInqireLevReqestVO vo){
		update("gamTestPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamTestPrtFcltyUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertPrtFcltyUseExprInqireLevReqest(GamTestPrtFcltyUseExprInqireLevReqestVO vo){
		insert("gamTestPrtFcltyUseExprInqireDao.insertPrtFcltyUseExprInqireLevReqest_S", vo);
	}
	
	/**
	 * 항만시설사용 허가여부를 취소한다.
	 * @param vo GamTestPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqirePrmisnCancel(GamTestPrtFcltyUseExprInqireLevReqestVO vo){
		update("gamTestPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamTestPrtFcltyUseExprInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyUseExprInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 항만시설사용상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용상세 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyUseExprInqireDetailInfo(GamTestPrtFcltyUseExprInqireVO vo) throws Exception {
        return list("gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamTestPrtFcltyUseExprInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyUseExprInqireFileList(GamTestPrtFcltyUseExprInqireVO searchVO) throws Exception {
        return list("gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyUseExprInqireFileListTotCnt(GamTestPrtFcltyUseExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamTestPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void insertPrtFcltyUseExprInqireFile(GamTestPrtFcltyUseExprInqireVO vo){
		insert("gamTestPrtFcltyUseExprInqireDao.insertPrtFcltyUseExprInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamTestPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqireFile(GamTestPrtFcltyUseExprInqireVO vo){
		insert("gamTestPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamTestPrtFcltyUseExprInqireVO
	 * @exception Exception
	 */
	public void deletePrtFcltyUseExprInqirePhotoSingle(GamTestPrtFcltyUseExprInqireVO vo){
		delete("gamTestPrtFcltyUseExprInqireDao.deletePrtFcltyUseExprInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireMaxKey(GamTestPrtFcltyUseExprInqireVO searchVO) throws Exception {
		return (GamTestPrtFcltyUseExprInqireVO) selectByPk("gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamTestPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqireComment(GamTestPrtFcltyUseExprInqireVO vo){
		update("gamTestPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireRenewInfo(GamTestPrtFcltyUseExprInqireVO searchVO) throws Exception {
		return (GamTestPrtFcltyUseExprInqireVO) selectByPk("gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamTestPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqireRenewInfo(GamTestPrtFcltyUseExprInqireVO vo){
		update("gamTestPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireCurrRenewInfo(GamTestPrtFcltyUseExprInqireVO searchVO) throws Exception {
		return (GamTestPrtFcltyUseExprInqireVO) selectByPk("gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyUseExprInqireVO selectPrtFcltyUseExprInqireDetailQuaycd(GamTestPrtFcltyUseExprInqireVO searchVO) throws Exception {
		return (GamTestPrtFcltyUseExprInqireVO) selectByPk("gamTestPrtFcltyUseExprInqireDao.selectPrtFcltyUseExprInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamTestPrtFcltyUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyUseExprInqireQuaycd(GamTestPrtFcltyUseExprInqireVO vo){
		update("gamTestPrtFcltyUseExprInqireDao.updatePrtFcltyUseExprInqireQuaycd_S", vo);
	}
}
