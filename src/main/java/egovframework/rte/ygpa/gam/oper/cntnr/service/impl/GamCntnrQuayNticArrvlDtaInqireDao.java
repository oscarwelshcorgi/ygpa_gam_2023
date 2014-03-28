package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayNticArrvlDtaInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayNticArrvlDtaInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayNticArrvlDtaInqireVO;

/**
 * @Class Name : gamCntnrQuayNticArrvlDtaInqireDao.java
 * @Description : 컨테이너부두임대고지도래현황조회 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCntnrQuayNticArrvlDtaInqireDao")
public class GamCntnrQuayNticArrvlDtaInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 컨테이너부두임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
    public List selectCntnrQuayNticArrvlDtaInqireList(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception {
        return list("gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireList_D", searchVO);
    }

    /**
	 * 컨테이너부두임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrQuayNticArrvlDtaInqireListTotCnt(GamCntnrQuayNticArrvlDtaInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayNticArrvlDtaInqireVO selectCntnrQuayNticArrvlDtaInqireSum(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamCntnrQuayNticArrvlDtaInqireVO) selectByPk("gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireSum_S", searchVO);
	}
    
	/**
	 * 컨테이너부두임대 최초 신청을 등록한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertCntnrQuayNticArrvlDtaInqireFirst(GamCntnrQuayNticArrvlDtaInqireVO vo){
		insert("gamCntnrQuayNticArrvlDtaInqireDao.insertCntnrQuayNticArrvlDtaInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamCntnrQuayNticArrvlDtaInqireVO
	 * @return 컨테이너부두임대관리 목록 총 갯수
	 * @exception
	 */
    public GamCntnrQuayNticArrvlDtaInqireVO selectCntnrQuayNticArrvlDtaInqireMaxNo(GamCntnrQuayNticArrvlDtaInqireVO vo) {
        return (GamCntnrQuayNticArrvlDtaInqireVO)getSqlMapClientTemplate().queryForObject("gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireMaxNo_S", vo);
    }
    
    /**
	 * 컨테이너부두임대 연장 신청을 등록한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertCntnrQuayNticArrvlDtaInqireRenew(GamCntnrQuayNticArrvlDtaInqireVO vo){
		insert("gamCntnrQuayNticArrvlDtaInqireDao.insertCntnrQuayNticArrvlDtaInqireRenew_S", vo);
	}
	
	/**
	 * 컨테이너부두임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectCntnrQuayNticArrvlDtaInqireMaxMngCnt(GamCntnrQuayNticArrvlDtaInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 컨테이너부두임대 연장 신청시 컨테이너부두임대 상세를 복사하여 등록한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertCntnrQuayNticArrvlDtaInqireDetailRenew(GamCntnrQuayNticArrvlDtaInqireDetailVO vo){
		insert("gamCntnrQuayNticArrvlDtaInqireDao.insertCntnrQuayNticArrvlDtaInqireDetailRenew_S", vo);
	}
	
	/**
	 * 컨테이너부두임대 정보를 수정한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updateCntnrQuayNticArrvlDtaInqire(GamCntnrQuayNticArrvlDtaInqireVO vo){
		update("gamCntnrQuayNticArrvlDtaInqireDao.updateCntnrQuayNticArrvlDtaInqire_S", vo);
	}
	
	/**
	 * 컨테이너부두임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
    public List selectCntnrQuayNticArrvlDtaInqireDetailList(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception {
        return list("gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireDetailList_D", vo);
    }

    /**
	 * 컨테이너부두임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrQuayNticArrvlDtaInqireDetailListTotCnt(GamCntnrQuayNticArrvlDtaInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrQuayNticArrvlDtaInqireLevReqestCnt(GamCntnrQuayNticArrvlDtaInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 컨테이너부두임대 사진정보를 삭제한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayNticArrvlDtaInqirePhoto(GamCntnrQuayNticArrvlDtaInqireVO vo){
		delete("gamCntnrQuayNticArrvlDtaInqireDao.deleteCntnrQuayNticArrvlDtaInqirePhoto_S", vo);
	}
    
	/**
	 * 컨테이너부두임대 정보를 삭제한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayNticArrvlDtaInqire(GamCntnrQuayNticArrvlDtaInqireVO vo){
		delete("gamCntnrQuayNticArrvlDtaInqireDao.deleteCntnrQuayNticArrvlDtaInqire_S", vo);
	}
    
	/**
	 * 컨테이너부두임대 상세정보를 삭제한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayNticArrvlDtaInqireDetail(GamCntnrQuayNticArrvlDtaInqireVO vo){
		delete("gamCntnrQuayNticArrvlDtaInqireDao.deleteCntnrQuayNticArrvlDtaInqireDetail_S", vo);
	}
	
	/**
	 * 컨테이너부두임대 상세를 등록한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void insertCntnrQuayNticArrvlDtaInqireDetail(GamCntnrQuayNticArrvlDtaInqireDetailVO vo){
		insert("gamCntnrQuayNticArrvlDtaInqireDao.insertCntnrQuayNticArrvlDtaInqireDetail_S", vo);
	}
	
	/**
	 * 컨테이너부두임대 상세를 수정한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayNticArrvlDtaInqireDetail(GamCntnrQuayNticArrvlDtaInqireDetailVO vo){
		update("gamCntnrQuayNticArrvlDtaInqireDao.updateCntnrQuayNticArrvlDtaInqireDetail_S", vo);
	}
	
	/**
	 * 컨테이너부두임대 상세를 삭제한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayNticArrvlDtaInqireDetail2(GamCntnrQuayNticArrvlDtaInqireDetailVO vo){
		delete("gamCntnrQuayNticArrvlDtaInqireDao.deleteCntnrQuayNticArrvlDtaInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 컨테이너부두임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대정보
	 * @exception Exception
	 */
	public GamCntnrQuayNticArrvlDtaInqireVO selectCntnrQuayNticArrvlDtaInqirePrmisnInfo(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamCntnrQuayNticArrvlDtaInqireVO) selectByPk("gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 컨테이너부두임대 허가여부를 수정한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updateCntnrQuayNticArrvlDtaInqirePrmisn(GamCntnrQuayNticArrvlDtaInqireLevReqestVO vo){
		update("gamCntnrQuayNticArrvlDtaInqireDao.updateCntnrQuayNticArrvlDtaInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertCntnrQuayNticArrvlDtaInqireLevReqest(GamCntnrQuayNticArrvlDtaInqireLevReqestVO vo){
		insert("gamCntnrQuayNticArrvlDtaInqireDao.insertCntnrQuayNticArrvlDtaInqireLevReqest_S", vo);
	}
	
	/**
	 * 컨테이너부두임대 허가여부를 취소한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updateCntnrQuayNticArrvlDtaInqirePrmisnCancel(GamCntnrQuayNticArrvlDtaInqireLevReqestVO vo){
		update("gamCntnrQuayNticArrvlDtaInqireDao.updateCntnrQuayNticArrvlDtaInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamCntnrQuayNticArrvlDtaInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayNticArrvlDtaInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 컨테이너부두임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대상세 목록
	 * @exception Exception
	 */
    public List selectCntnrQuayNticArrvlDtaInqireDetailInfo(GamCntnrQuayNticArrvlDtaInqireVO vo) throws Exception {
        return list("gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamCntnrQuayNticArrvlDtaInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
    public List selectCntnrQuayNticArrvlDtaInqireFileList(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception {
        return list("gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrQuayNticArrvlDtaInqireFileListTotCnt(GamCntnrQuayNticArrvlDtaInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertCntnrQuayNticArrvlDtaInqireFile(GamCntnrQuayNticArrvlDtaInqireVO vo){
		insert("gamCntnrQuayNticArrvlDtaInqireDao.insertCntnrQuayNticArrvlDtaInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updateCntnrQuayNticArrvlDtaInqireFile(GamCntnrQuayNticArrvlDtaInqireVO vo){
		insert("gamCntnrQuayNticArrvlDtaInqireDao.updateCntnrQuayNticArrvlDtaInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamCntnrQuayNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayNticArrvlDtaInqirePhotoSingle(GamCntnrQuayNticArrvlDtaInqireVO vo){
		delete("gamCntnrQuayNticArrvlDtaInqireDao.deleteCntnrQuayNticArrvlDtaInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayNticArrvlDtaInqireVO selectCntnrQuayNticArrvlDtaInqireMaxKey(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamCntnrQuayNticArrvlDtaInqireVO) selectByPk("gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayNticArrvlDtaInqireComment(GamCntnrQuayNticArrvlDtaInqireVO vo){
		update("gamCntnrQuayNticArrvlDtaInqireDao.updateCntnrQuayNticArrvlDtaInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayNticArrvlDtaInqireVO selectCntnrQuayNticArrvlDtaInqireRenewInfo(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamCntnrQuayNticArrvlDtaInqireVO) selectByPk("gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayNticArrvlDtaInqireRenewInfo(GamCntnrQuayNticArrvlDtaInqireVO vo){
		update("gamCntnrQuayNticArrvlDtaInqireDao.updateCntnrQuayNticArrvlDtaInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayNticArrvlDtaInqireVO selectCntnrQuayNticArrvlDtaInqireCurrRenewInfo(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamCntnrQuayNticArrvlDtaInqireVO) selectByPk("gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayNticArrvlDtaInqireVO selectCntnrQuayNticArrvlDtaInqireDetailQuaycd(GamCntnrQuayNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamCntnrQuayNticArrvlDtaInqireVO) selectByPk("gamCntnrQuayNticArrvlDtaInqireDao.selectCntnrQuayNticArrvlDtaInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamCntnrQuayNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayNticArrvlDtaInqireQuaycd(GamCntnrQuayNticArrvlDtaInqireVO vo){
		update("gamCntnrQuayNticArrvlDtaInqireDao.updateCntnrQuayNticArrvlDtaInqireQuaycd_S", vo);
	}
}
