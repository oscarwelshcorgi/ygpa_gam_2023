package egovframework.rte.ygpa.gam.oper.gnrltest.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyNticArrvlDtaInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyNticArrvlDtaInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyNticArrvlDtaInqireVO;

/**
 * @Class Name : gamTestPrtFcltyNticArrvlDtaInqireDao.java
 * @Description : 항만시설고지도래현황조회 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamTestPrtFcltyNticArrvlDtaInqireDao")
public class GamTestPrtFcltyNticArrvlDtaInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 항만시설사용관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyNticArrvlDtaInqireList(GamTestPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
        return list("gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireList_D", searchVO);
    }

    /**
	 * 항만시설사용관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyNticArrvlDtaInqireListTotCnt(GamTestPrtFcltyNticArrvlDtaInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireSum(GamTestPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamTestPrtFcltyNticArrvlDtaInqireVO) selectByPk("gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireSum_S", searchVO);
	}
    
	/**
	 * 항만시설사용 최초 신청을 등록한다.
	 * @param vo GamTestPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertPrtFcltyNticArrvlDtaInqireFirst(GamTestPrtFcltyNticArrvlDtaInqireVO vo){
		insert("gamTestPrtFcltyNticArrvlDtaInqireDao.insertPrtFcltyNticArrvlDtaInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamTestPrtFcltyNticArrvlDtaInqireVO
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public GamTestPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireMaxNo(GamTestPrtFcltyNticArrvlDtaInqireVO vo) {
        return (GamTestPrtFcltyNticArrvlDtaInqireVO)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireMaxNo_S", vo);
    }
    
    /**
	 * 항만시설사용 연장 신청을 등록한다.
	 * @param vo GamTestPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertPrtFcltyNticArrvlDtaInqireRenew(GamTestPrtFcltyNticArrvlDtaInqireVO vo){
		insert("gamTestPrtFcltyNticArrvlDtaInqireDao.insertPrtFcltyNticArrvlDtaInqireRenew_S", vo);
	}
	
	/**
	 * 항만시설사용 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public String selectPrtFcltyNticArrvlDtaInqireMaxMngCnt(GamTestPrtFcltyNticArrvlDtaInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 항만시설사용 연장 신청시 항만시설사용 상세를 복사하여 등록한다.
	 * @param vo GamTestPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertPrtFcltyNticArrvlDtaInqireDetailRenew(GamTestPrtFcltyNticArrvlDtaInqireDetailVO vo){
		insert("gamTestPrtFcltyNticArrvlDtaInqireDao.insertPrtFcltyNticArrvlDtaInqireDetailRenew_S", vo);
	}
	
	/**
	 * 항만시설사용 정보를 수정한다.
	 * @param vo GamTestPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updatePrtFcltyNticArrvlDtaInqire(GamTestPrtFcltyNticArrvlDtaInqireVO vo){
		update("gamTestPrtFcltyNticArrvlDtaInqireDao.updatePrtFcltyNticArrvlDtaInqire_S", vo);
	}
	
	/**
	 * 항만시설사용관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyNticArrvlDtaInqireDetailList(GamTestPrtFcltyNticArrvlDtaInqireVO vo) throws Exception {
        return list("gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireDetailList_D", vo);
    }

    /**
	 * 항만시설사용관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyNticArrvlDtaInqireDetailListTotCnt(GamTestPrtFcltyNticArrvlDtaInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyNticArrvlDtaInqireLevReqestCnt(GamTestPrtFcltyNticArrvlDtaInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 항만시설사용 사진정보를 삭제한다.
	 * @param vo GamTestPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deletePrtFcltyNticArrvlDtaInqirePhoto(GamTestPrtFcltyNticArrvlDtaInqireVO vo){
		delete("gamTestPrtFcltyNticArrvlDtaInqireDao.deletePrtFcltyNticArrvlDtaInqirePhoto_S", vo);
	}
    
	/**
	 * 항만시설사용 정보를 삭제한다.
	 * @param vo GamTestPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deletePrtFcltyNticArrvlDtaInqire(GamTestPrtFcltyNticArrvlDtaInqireVO vo){
		delete("gamTestPrtFcltyNticArrvlDtaInqireDao.deletePrtFcltyNticArrvlDtaInqire_S", vo);
	}
    
	/**
	 * 항만시설사용 상세정보를 삭제한다.
	 * @param vo GamTestPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deletePrtFcltyNticArrvlDtaInqireDetail(GamTestPrtFcltyNticArrvlDtaInqireVO vo){
		delete("gamTestPrtFcltyNticArrvlDtaInqireDao.deletePrtFcltyNticArrvlDtaInqireDetail_S", vo);
	}
	
	/**
	 * 항만시설사용 상세를 등록한다.
	 * @param vo GamTestPrtFcltyNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void insertPrtFcltyNticArrvlDtaInqireDetail(GamTestPrtFcltyNticArrvlDtaInqireDetailVO vo){
		insert("gamTestPrtFcltyNticArrvlDtaInqireDao.insertPrtFcltyNticArrvlDtaInqireDetail_S", vo);
	}
	
	/**
	 * 항만시설사용 상세를 수정한다.
	 * @param vo GamTestPrtFcltyNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyNticArrvlDtaInqireDetail(GamTestPrtFcltyNticArrvlDtaInqireDetailVO vo){
		update("gamTestPrtFcltyNticArrvlDtaInqireDao.updatePrtFcltyNticArrvlDtaInqireDetail_S", vo);
	}
	
	/**
	 * 항만시설사용 상세를 삭제한다.
	 * @param vo GamTestPrtFcltyNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void deletePrtFcltyNticArrvlDtaInqireDetail2(GamTestPrtFcltyNticArrvlDtaInqireDetailVO vo){
		delete("gamTestPrtFcltyNticArrvlDtaInqireDao.deletePrtFcltyNticArrvlDtaInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
	public GamTestPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqirePrmisnInfo(GamTestPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamTestPrtFcltyNticArrvlDtaInqireVO) selectByPk("gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 항만시설사용 허가여부를 수정한다.
	 * @param vo GamTestPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updatePrtFcltyNticArrvlDtaInqirePrmisn(GamTestPrtFcltyNticArrvlDtaInqireLevReqestVO vo){
		update("gamTestPrtFcltyNticArrvlDtaInqireDao.updatePrtFcltyNticArrvlDtaInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamTestPrtFcltyNticArrvlDtaInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertPrtFcltyNticArrvlDtaInqireLevReqest(GamTestPrtFcltyNticArrvlDtaInqireLevReqestVO vo){
		insert("gamTestPrtFcltyNticArrvlDtaInqireDao.insertPrtFcltyNticArrvlDtaInqireLevReqest_S", vo);
	}
	
	/**
	 * 항만시설사용 허가여부를 취소한다.
	 * @param vo GamTestPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updatePrtFcltyNticArrvlDtaInqirePrmisnCancel(GamTestPrtFcltyNticArrvlDtaInqireLevReqestVO vo){
		update("gamTestPrtFcltyNticArrvlDtaInqireDao.updatePrtFcltyNticArrvlDtaInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamTestPrtFcltyNticArrvlDtaInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyNticArrvlDtaInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 항만시설사용상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용상세 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyNticArrvlDtaInqireDetailInfo(GamTestPrtFcltyNticArrvlDtaInqireVO vo) throws Exception {
        return list("gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamTestPrtFcltyNticArrvlDtaInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyNticArrvlDtaInqireFileList(GamTestPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
        return list("gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyNticArrvlDtaInqireFileListTotCnt(GamTestPrtFcltyNticArrvlDtaInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamTestPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertPrtFcltyNticArrvlDtaInqireFile(GamTestPrtFcltyNticArrvlDtaInqireVO vo){
		insert("gamTestPrtFcltyNticArrvlDtaInqireDao.insertPrtFcltyNticArrvlDtaInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamTestPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updatePrtFcltyNticArrvlDtaInqireFile(GamTestPrtFcltyNticArrvlDtaInqireVO vo){
		insert("gamTestPrtFcltyNticArrvlDtaInqireDao.updatePrtFcltyNticArrvlDtaInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamTestPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deletePrtFcltyNticArrvlDtaInqirePhotoSingle(GamTestPrtFcltyNticArrvlDtaInqireVO vo){
		delete("gamTestPrtFcltyNticArrvlDtaInqireDao.deletePrtFcltyNticArrvlDtaInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireMaxKey(GamTestPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamTestPrtFcltyNticArrvlDtaInqireVO) selectByPk("gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamTestPrtFcltyNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyNticArrvlDtaInqireComment(GamTestPrtFcltyNticArrvlDtaInqireVO vo){
		update("gamTestPrtFcltyNticArrvlDtaInqireDao.updatePrtFcltyNticArrvlDtaInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireRenewInfo(GamTestPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamTestPrtFcltyNticArrvlDtaInqireVO) selectByPk("gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamTestPrtFcltyNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyNticArrvlDtaInqireRenewInfo(GamTestPrtFcltyNticArrvlDtaInqireVO vo){
		update("gamTestPrtFcltyNticArrvlDtaInqireDao.updatePrtFcltyNticArrvlDtaInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireCurrRenewInfo(GamTestPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamTestPrtFcltyNticArrvlDtaInqireVO) selectByPk("gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamTestPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireDetailQuaycd(GamTestPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamTestPrtFcltyNticArrvlDtaInqireVO) selectByPk("gamTestPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamTestPrtFcltyNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyNticArrvlDtaInqireQuaycd(GamTestPrtFcltyNticArrvlDtaInqireVO vo){
		update("gamTestPrtFcltyNticArrvlDtaInqireDao.updatePrtFcltyNticArrvlDtaInqireQuaycd_S", vo);
	}
}
