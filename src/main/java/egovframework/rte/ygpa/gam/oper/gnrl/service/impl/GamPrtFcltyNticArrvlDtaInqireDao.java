package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyNticArrvlDtaInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyNticArrvlDtaInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyNticArrvlDtaInqireVO;

/**
 * @Class Name : gamPrtFcltyNticArrvlDtaInqireDao.java
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
@Repository("gamPrtFcltyNticArrvlDtaInqireDao")
public class GamPrtFcltyNticArrvlDtaInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 항만시설사용관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyNticArrvlDtaInqireList(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
        return list("gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireList_D", searchVO);
    }

    /**
	 * 항만시설사용관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyNticArrvlDtaInqireListTotCnt(GamPrtFcltyNticArrvlDtaInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireSum(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamPrtFcltyNticArrvlDtaInqireVO) selectByPk("gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireSum_S", searchVO);
	}
    
	/**
	 * 항만시설사용 최초 신청을 등록한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertPrtFcltyNticArrvlDtaInqireFirst(GamPrtFcltyNticArrvlDtaInqireVO vo){
		insert("gamPrtFcltyNticArrvlDtaInqireDao.insertPrtFcltyNticArrvlDtaInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamPrtFcltyNticArrvlDtaInqireVO
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public GamPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireMaxNo(GamPrtFcltyNticArrvlDtaInqireVO vo) {
        return (GamPrtFcltyNticArrvlDtaInqireVO)getSqlMapClientTemplate().queryForObject("gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireMaxNo_S", vo);
    }
    
    /**
	 * 항만시설사용 연장 신청을 등록한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertPrtFcltyNticArrvlDtaInqireRenew(GamPrtFcltyNticArrvlDtaInqireVO vo){
		insert("gamPrtFcltyNticArrvlDtaInqireDao.insertPrtFcltyNticArrvlDtaInqireRenew_S", vo);
	}
	
	/**
	 * 항만시설사용 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public String selectPrtFcltyNticArrvlDtaInqireMaxMngCnt(GamPrtFcltyNticArrvlDtaInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 항만시설사용 연장 신청시 항만시설사용 상세를 복사하여 등록한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertPrtFcltyNticArrvlDtaInqireDetailRenew(GamPrtFcltyNticArrvlDtaInqireDetailVO vo){
		insert("gamPrtFcltyNticArrvlDtaInqireDao.insertPrtFcltyNticArrvlDtaInqireDetailRenew_S", vo);
	}
	
	/**
	 * 항만시설사용 정보를 수정한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updatePrtFcltyNticArrvlDtaInqire(GamPrtFcltyNticArrvlDtaInqireVO vo){
		update("gamPrtFcltyNticArrvlDtaInqireDao.updatePrtFcltyNticArrvlDtaInqire_S", vo);
	}
	
	/**
	 * 항만시설사용관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyNticArrvlDtaInqireDetailList(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception {
        return list("gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireDetailList_D", vo);
    }

    /**
	 * 항만시설사용관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyNticArrvlDtaInqireDetailListTotCnt(GamPrtFcltyNticArrvlDtaInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyNticArrvlDtaInqireLevReqestCnt(GamPrtFcltyNticArrvlDtaInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 항만시설사용 사진정보를 삭제한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deletePrtFcltyNticArrvlDtaInqirePhoto(GamPrtFcltyNticArrvlDtaInqireVO vo){
		delete("gamPrtFcltyNticArrvlDtaInqireDao.deletePrtFcltyNticArrvlDtaInqirePhoto_S", vo);
	}
    
	/**
	 * 항만시설사용 정보를 삭제한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deletePrtFcltyNticArrvlDtaInqire(GamPrtFcltyNticArrvlDtaInqireVO vo){
		delete("gamPrtFcltyNticArrvlDtaInqireDao.deletePrtFcltyNticArrvlDtaInqire_S", vo);
	}
    
	/**
	 * 항만시설사용 상세정보를 삭제한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deletePrtFcltyNticArrvlDtaInqireDetail(GamPrtFcltyNticArrvlDtaInqireVO vo){
		delete("gamPrtFcltyNticArrvlDtaInqireDao.deletePrtFcltyNticArrvlDtaInqireDetail_S", vo);
	}
	
	/**
	 * 항만시설사용 상세를 등록한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void insertPrtFcltyNticArrvlDtaInqireDetail(GamPrtFcltyNticArrvlDtaInqireDetailVO vo){
		insert("gamPrtFcltyNticArrvlDtaInqireDao.insertPrtFcltyNticArrvlDtaInqireDetail_S", vo);
	}
	
	/**
	 * 항만시설사용 상세를 수정한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyNticArrvlDtaInqireDetail(GamPrtFcltyNticArrvlDtaInqireDetailVO vo){
		update("gamPrtFcltyNticArrvlDtaInqireDao.updatePrtFcltyNticArrvlDtaInqireDetail_S", vo);
	}
	
	/**
	 * 항만시설사용 상세를 삭제한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void deletePrtFcltyNticArrvlDtaInqireDetail2(GamPrtFcltyNticArrvlDtaInqireDetailVO vo){
		delete("gamPrtFcltyNticArrvlDtaInqireDao.deletePrtFcltyNticArrvlDtaInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 항만시설사용 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용정보
	 * @exception Exception
	 */
	public GamPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqirePrmisnInfo(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamPrtFcltyNticArrvlDtaInqireVO) selectByPk("gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 항만시설사용 허가여부를 수정한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updatePrtFcltyNticArrvlDtaInqirePrmisn(GamPrtFcltyNticArrvlDtaInqireLevReqestVO vo){
		update("gamPrtFcltyNticArrvlDtaInqireDao.updatePrtFcltyNticArrvlDtaInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertPrtFcltyNticArrvlDtaInqireLevReqest(GamPrtFcltyNticArrvlDtaInqireLevReqestVO vo){
		insert("gamPrtFcltyNticArrvlDtaInqireDao.insertPrtFcltyNticArrvlDtaInqireLevReqest_S", vo);
	}
	
	/**
	 * 항만시설사용 허가여부를 취소한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updatePrtFcltyNticArrvlDtaInqirePrmisnCancel(GamPrtFcltyNticArrvlDtaInqireLevReqestVO vo){
		update("gamPrtFcltyNticArrvlDtaInqireDao.updatePrtFcltyNticArrvlDtaInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamPrtFcltyNticArrvlDtaInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyNticArrvlDtaInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 항만시설사용상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용상세 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyNticArrvlDtaInqireDetailInfo(GamPrtFcltyNticArrvlDtaInqireVO vo) throws Exception {
        return list("gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamPrtFcltyNticArrvlDtaInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyNticArrvlDtaInqireFileList(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
        return list("gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyNticArrvlDtaInqireFileListTotCnt(GamPrtFcltyNticArrvlDtaInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertPrtFcltyNticArrvlDtaInqireFile(GamPrtFcltyNticArrvlDtaInqireVO vo){
		insert("gamPrtFcltyNticArrvlDtaInqireDao.insertPrtFcltyNticArrvlDtaInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updatePrtFcltyNticArrvlDtaInqireFile(GamPrtFcltyNticArrvlDtaInqireVO vo){
		insert("gamPrtFcltyNticArrvlDtaInqireDao.updatePrtFcltyNticArrvlDtaInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamPrtFcltyNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deletePrtFcltyNticArrvlDtaInqirePhotoSingle(GamPrtFcltyNticArrvlDtaInqireVO vo){
		delete("gamPrtFcltyNticArrvlDtaInqireDao.deletePrtFcltyNticArrvlDtaInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireMaxKey(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamPrtFcltyNticArrvlDtaInqireVO) selectByPk("gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyNticArrvlDtaInqireComment(GamPrtFcltyNticArrvlDtaInqireVO vo){
		update("gamPrtFcltyNticArrvlDtaInqireDao.updatePrtFcltyNticArrvlDtaInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireRenewInfo(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamPrtFcltyNticArrvlDtaInqireVO) selectByPk("gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyNticArrvlDtaInqireRenewInfo(GamPrtFcltyNticArrvlDtaInqireVO vo){
		update("gamPrtFcltyNticArrvlDtaInqireDao.updatePrtFcltyNticArrvlDtaInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireCurrRenewInfo(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamPrtFcltyNticArrvlDtaInqireVO) selectByPk("gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설사용관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyNticArrvlDtaInqireVO selectPrtFcltyNticArrvlDtaInqireDetailQuaycd(GamPrtFcltyNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamPrtFcltyNticArrvlDtaInqireVO) selectByPk("gamPrtFcltyNticArrvlDtaInqireDao.selectPrtFcltyNticArrvlDtaInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamPrtFcltyNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyNticArrvlDtaInqireQuaycd(GamPrtFcltyNticArrvlDtaInqireVO vo){
		update("gamPrtFcltyNticArrvlDtaInqireDao.updatePrtFcltyNticArrvlDtaInqireQuaycd_S", vo);
	}
}
