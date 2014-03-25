package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldNticArrvlDtaInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldNticArrvlDtaInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldNticArrvlDtaInqireVO;

/**
 * @Class Name : gamHtldNticArrvlDtaInqireDao.java
 * @Description : 배후단지임대고지도래현황조회 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamHtldNticArrvlDtaInqireDao")
public class GamHtldNticArrvlDtaInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 배후단지임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
    public List selectHtldNticArrvlDtaInqireList(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception {
        return list("gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireList_D", searchVO);
    }

    /**
	 * 배후단지임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldNticArrvlDtaInqireListTotCnt(GamHtldNticArrvlDtaInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public GamHtldNticArrvlDtaInqireVO selectHtldNticArrvlDtaInqireSum(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamHtldNticArrvlDtaInqireVO) selectByPk("gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireSum_S", searchVO);
	}
    
	/**
	 * 배후단지임대 최초 신청을 등록한다.
	 * @param vo GamHtldNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertHtldNticArrvlDtaInqireFirst(GamHtldNticArrvlDtaInqireVO vo){
		insert("gamHtldNticArrvlDtaInqireDao.insertHtldNticArrvlDtaInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamHtldNticArrvlDtaInqireVO
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public GamHtldNticArrvlDtaInqireVO selectHtldNticArrvlDtaInqireMaxNo(GamHtldNticArrvlDtaInqireVO vo) {
        return (GamHtldNticArrvlDtaInqireVO)getSqlMapClientTemplate().queryForObject("gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireMaxNo_S", vo);
    }
    
    /**
	 * 배후단지임대 연장 신청을 등록한다.
	 * @param vo GamHtldNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertHtldNticArrvlDtaInqireRenew(GamHtldNticArrvlDtaInqireVO vo){
		insert("gamHtldNticArrvlDtaInqireDao.insertHtldNticArrvlDtaInqireRenew_S", vo);
	}
	
	/**
	 * 배후단지임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectHtldNticArrvlDtaInqireMaxMngCnt(GamHtldNticArrvlDtaInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 배후단지임대 연장 신청시 배후단지임대 상세를 복사하여 등록한다.
	 * @param vo GamHtldNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertHtldNticArrvlDtaInqireDetailRenew(GamHtldNticArrvlDtaInqireDetailVO vo){
		insert("gamHtldNticArrvlDtaInqireDao.insertHtldNticArrvlDtaInqireDetailRenew_S", vo);
	}
	
	/**
	 * 배후단지임대 정보를 수정한다.
	 * @param vo GamHtldNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updateHtldNticArrvlDtaInqire(GamHtldNticArrvlDtaInqireVO vo){
		update("gamHtldNticArrvlDtaInqireDao.updateHtldNticArrvlDtaInqire_S", vo);
	}
	
	/**
	 * 배후단지임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
    public List selectHtldNticArrvlDtaInqireDetailList(GamHtldNticArrvlDtaInqireVO vo) throws Exception {
        return list("gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireDetailList_D", vo);
    }

    /**
	 * 배후단지임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldNticArrvlDtaInqireDetailListTotCnt(GamHtldNticArrvlDtaInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldNticArrvlDtaInqireLevReqestCnt(GamHtldNticArrvlDtaInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 배후단지임대 사진정보를 삭제한다.
	 * @param vo GamHtldNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deleteHtldNticArrvlDtaInqirePhoto(GamHtldNticArrvlDtaInqireVO vo){
		delete("gamHtldNticArrvlDtaInqireDao.deleteHtldNticArrvlDtaInqirePhoto_S", vo);
	}
    
	/**
	 * 배후단지임대 정보를 삭제한다.
	 * @param vo GamHtldNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deleteHtldNticArrvlDtaInqire(GamHtldNticArrvlDtaInqireVO vo){
		delete("gamHtldNticArrvlDtaInqireDao.deleteHtldNticArrvlDtaInqire_S", vo);
	}
    
	/**
	 * 배후단지임대 상세정보를 삭제한다.
	 * @param vo GamHtldNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deleteHtldNticArrvlDtaInqireDetail(GamHtldNticArrvlDtaInqireVO vo){
		delete("gamHtldNticArrvlDtaInqireDao.deleteHtldNticArrvlDtaInqireDetail_S", vo);
	}
	
	/**
	 * 배후단지임대 상세를 등록한다.
	 * @param vo GamHtldNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void insertHtldNticArrvlDtaInqireDetail(GamHtldNticArrvlDtaInqireDetailVO vo){
		insert("gamHtldNticArrvlDtaInqireDao.insertHtldNticArrvlDtaInqireDetail_S", vo);
	}
	
	/**
	 * 배후단지임대 상세를 수정한다.
	 * @param vo GamHtldNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateHtldNticArrvlDtaInqireDetail(GamHtldNticArrvlDtaInqireDetailVO vo){
		update("gamHtldNticArrvlDtaInqireDao.updateHtldNticArrvlDtaInqireDetail_S", vo);
	}
	
	/**
	 * 배후단지임대 상세를 삭제한다.
	 * @param vo GamHtldNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void deleteHtldNticArrvlDtaInqireDetail2(GamHtldNticArrvlDtaInqireDetailVO vo){
		delete("gamHtldNticArrvlDtaInqireDao.deleteHtldNticArrvlDtaInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 배후단지임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대정보
	 * @exception Exception
	 */
	public GamHtldNticArrvlDtaInqireVO selectHtldNticArrvlDtaInqirePrmisnInfo(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamHtldNticArrvlDtaInqireVO) selectByPk("gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 배후단지임대 허가여부를 수정한다.
	 * @param vo GamHtldNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updateHtldNticArrvlDtaInqirePrmisn(GamHtldNticArrvlDtaInqireLevReqestVO vo){
		update("gamHtldNticArrvlDtaInqireDao.updateHtldNticArrvlDtaInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamHtldNticArrvlDtaInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertHtldNticArrvlDtaInqireLevReqest(GamHtldNticArrvlDtaInqireLevReqestVO vo){
		insert("gamHtldNticArrvlDtaInqireDao.insertHtldNticArrvlDtaInqireLevReqest_S", vo);
	}
	
	/**
	 * 배후단지임대 허가여부를 취소한다.
	 * @param vo GamHtldNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updateHtldNticArrvlDtaInqirePrmisnCancel(GamHtldNticArrvlDtaInqireLevReqestVO vo){
		update("gamHtldNticArrvlDtaInqireDao.updateHtldNticArrvlDtaInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamHtldNticArrvlDtaInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldNticArrvlDtaInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 배후단지임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대상세 목록
	 * @exception Exception
	 */
    public List selectHtldNticArrvlDtaInqireDetailInfo(GamHtldNticArrvlDtaInqireVO vo) throws Exception {
        return list("gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamHtldNticArrvlDtaInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
    public List selectHtldNticArrvlDtaInqireFileList(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception {
        return list("gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldNticArrvlDtaInqireFileListTotCnt(GamHtldNticArrvlDtaInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamHtldNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertHtldNticArrvlDtaInqireFile(GamHtldNticArrvlDtaInqireVO vo){
		insert("gamHtldNticArrvlDtaInqireDao.insertHtldNticArrvlDtaInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamHtldNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updateHtldNticArrvlDtaInqireFile(GamHtldNticArrvlDtaInqireVO vo){
		insert("gamHtldNticArrvlDtaInqireDao.updateHtldNticArrvlDtaInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamHtldNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deleteHtldNticArrvlDtaInqirePhotoSingle(GamHtldNticArrvlDtaInqireVO vo){
		delete("gamHtldNticArrvlDtaInqireDao.deleteHtldNticArrvlDtaInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public GamHtldNticArrvlDtaInqireVO selectHtldNticArrvlDtaInqireMaxKey(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamHtldNticArrvlDtaInqireVO) selectByPk("gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamHtldNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateHtldNticArrvlDtaInqireComment(GamHtldNticArrvlDtaInqireVO vo){
		update("gamHtldNticArrvlDtaInqireDao.updateHtldNticArrvlDtaInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public GamHtldNticArrvlDtaInqireVO selectHtldNticArrvlDtaInqireRenewInfo(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamHtldNticArrvlDtaInqireVO) selectByPk("gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamHtldNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateHtldNticArrvlDtaInqireRenewInfo(GamHtldNticArrvlDtaInqireVO vo){
		update("gamHtldNticArrvlDtaInqireDao.updateHtldNticArrvlDtaInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public GamHtldNticArrvlDtaInqireVO selectHtldNticArrvlDtaInqireCurrRenewInfo(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamHtldNticArrvlDtaInqireVO) selectByPk("gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public GamHtldNticArrvlDtaInqireVO selectHtldNticArrvlDtaInqireDetailQuaycd(GamHtldNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamHtldNticArrvlDtaInqireVO) selectByPk("gamHtldNticArrvlDtaInqireDao.selectHtldNticArrvlDtaInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamHtldNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateHtldNticArrvlDtaInqireQuaycd(GamHtldNticArrvlDtaInqireVO vo){
		update("gamHtldNticArrvlDtaInqireDao.updateHtldNticArrvlDtaInqireQuaycd_S", vo);
	}
}
