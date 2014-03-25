package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldUseExprInqireVO;

/**
 * @Class Name : gamHtldUseExprInqireDao.java
 * @Description : 배후단지임대만기도래자료조회 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamHtldUseExprInqireDao")
public class GamHtldUseExprInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 배후단지임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
    public List selectHtldUseExprInqireList(GamHtldUseExprInqireVO searchVO) throws Exception {
        return list("gamHtldUseExprInqireDao.selectHtldUseExprInqireList_D", searchVO);
    }

    /**
	 * 배후단지임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldUseExprInqireListTotCnt(GamHtldUseExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldUseExprInqireDao.selectHtldUseExprInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public GamHtldUseExprInqireVO selectHtldUseExprInqireSum(GamHtldUseExprInqireVO searchVO) throws Exception {
		return (GamHtldUseExprInqireVO) selectByPk("gamHtldUseExprInqireDao.selectHtldUseExprInqireSum_S", searchVO);
	}
    
	/**
	 * 배후단지임대 최초 신청을 등록한다.
	 * @param vo GamHtldUseExprInqireVO
	 * @exception Exception
	 */
	public void insertHtldUseExprInqireFirst(GamHtldUseExprInqireVO vo){
		insert("gamHtldUseExprInqireDao.insertHtldUseExprInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamHtldUseExprInqireVO
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public GamHtldUseExprInqireVO selectHtldUseExprInqireMaxNo(GamHtldUseExprInqireVO vo) {
        return (GamHtldUseExprInqireVO)getSqlMapClientTemplate().queryForObject("gamHtldUseExprInqireDao.selectHtldUseExprInqireMaxNo_S", vo);
    }
    
    /**
	 * 배후단지임대 연장 신청을 등록한다.
	 * @param vo GamHtldUseExprInqireVO
	 * @exception Exception
	 */
	public void insertHtldUseExprInqireRenew(GamHtldUseExprInqireVO vo){
		insert("gamHtldUseExprInqireDao.insertHtldUseExprInqireRenew_S", vo);
	}
	
	/**
	 * 배후단지임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectHtldUseExprInqireMaxMngCnt(GamHtldUseExprInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamHtldUseExprInqireDao.selectHtldUseExprInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 배후단지임대 연장 신청시 배후단지임대 상세를 복사하여 등록한다.
	 * @param vo GamHtldUseExprInqireVO
	 * @exception Exception
	 */
	public void insertHtldUseExprInqireDetailRenew(GamHtldUseExprInqireDetailVO vo){
		insert("gamHtldUseExprInqireDao.insertHtldUseExprInqireDetailRenew_S", vo);
	}
	
	/**
	 * 배후단지임대 정보를 수정한다.
	 * @param vo GamHtldUseExprInqireVO
	 * @exception Exception
	 */
	public void updateHtldUseExprInqire(GamHtldUseExprInqireVO vo){
		update("gamHtldUseExprInqireDao.updateHtldUseExprInqire_S", vo);
	}
	
	/**
	 * 배후단지임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
    public List selectHtldUseExprInqireDetailList(GamHtldUseExprInqireVO vo) throws Exception {
        return list("gamHtldUseExprInqireDao.selectHtldUseExprInqireDetailList_D", vo);
    }

    /**
	 * 배후단지임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldUseExprInqireDetailListTotCnt(GamHtldUseExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldUseExprInqireDao.selectHtldUseExprInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldUseExprInqireLevReqestCnt(GamHtldUseExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldUseExprInqireDao.selectHtldUseExprInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 배후단지임대 사진정보를 삭제한다.
	 * @param vo GamHtldUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteHtldUseExprInqirePhoto(GamHtldUseExprInqireVO vo){
		delete("gamHtldUseExprInqireDao.deleteHtldUseExprInqirePhoto_S", vo);
	}
    
	/**
	 * 배후단지임대 정보를 삭제한다.
	 * @param vo GamHtldUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteHtldUseExprInqire(GamHtldUseExprInqireVO vo){
		delete("gamHtldUseExprInqireDao.deleteHtldUseExprInqire_S", vo);
	}
    
	/**
	 * 배후단지임대 상세정보를 삭제한다.
	 * @param vo GamHtldUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteHtldUseExprInqireDetail(GamHtldUseExprInqireVO vo){
		delete("gamHtldUseExprInqireDao.deleteHtldUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 배후단지임대 상세를 등록한다.
	 * @param vo GamHtldUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void insertHtldUseExprInqireDetail(GamHtldUseExprInqireDetailVO vo){
		insert("gamHtldUseExprInqireDao.insertHtldUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 배후단지임대 상세를 수정한다.
	 * @param vo GamHtldUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateHtldUseExprInqireDetail(GamHtldUseExprInqireDetailVO vo){
		update("gamHtldUseExprInqireDao.updateHtldUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 배후단지임대 상세를 삭제한다.
	 * @param vo GamHtldUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void deleteHtldUseExprInqireDetail2(GamHtldUseExprInqireDetailVO vo){
		delete("gamHtldUseExprInqireDao.deleteHtldUseExprInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 배후단지임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대정보
	 * @exception Exception
	 */
	public GamHtldUseExprInqireVO selectHtldUseExprInqirePrmisnInfo(GamHtldUseExprInqireVO searchVO) throws Exception {
		return (GamHtldUseExprInqireVO) selectByPk("gamHtldUseExprInqireDao.selectHtldUseExprInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 배후단지임대 허가여부를 수정한다.
	 * @param vo GamHtldUseExprInqireVO
	 * @exception Exception
	 */
	public void updateHtldUseExprInqirePrmisn(GamHtldUseExprInqireLevReqestVO vo){
		update("gamHtldUseExprInqireDao.updateHtldUseExprInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamHtldUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertHtldUseExprInqireLevReqest(GamHtldUseExprInqireLevReqestVO vo){
		insert("gamHtldUseExprInqireDao.insertHtldUseExprInqireLevReqest_S", vo);
	}
	
	/**
	 * 배후단지임대 허가여부를 취소한다.
	 * @param vo GamHtldUseExprInqireVO
	 * @exception Exception
	 */
	public void updateHtldUseExprInqirePrmisnCancel(GamHtldUseExprInqireLevReqestVO vo){
		update("gamHtldUseExprInqireDao.updateHtldUseExprInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamHtldUseExprInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldUseExprInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 배후단지임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대상세 목록
	 * @exception Exception
	 */
    public List selectHtldUseExprInqireDetailInfo(GamHtldUseExprInqireVO vo) throws Exception {
        return list("gamHtldUseExprInqireDao.selectHtldUseExprInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamHtldUseExprInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
    public List selectHtldUseExprInqireFileList(GamHtldUseExprInqireVO searchVO) throws Exception {
        return list("gamHtldUseExprInqireDao.selectHtldUseExprInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldUseExprInqireFileListTotCnt(GamHtldUseExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldUseExprInqireDao.selectHtldUseExprInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamHtldUseExprInqireVO
	 * @exception Exception
	 */
	public void insertHtldUseExprInqireFile(GamHtldUseExprInqireVO vo){
		insert("gamHtldUseExprInqireDao.insertHtldUseExprInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamHtldUseExprInqireVO
	 * @exception Exception
	 */
	public void updateHtldUseExprInqireFile(GamHtldUseExprInqireVO vo){
		insert("gamHtldUseExprInqireDao.updateHtldUseExprInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamHtldUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteHtldUseExprInqirePhotoSingle(GamHtldUseExprInqireVO vo){
		delete("gamHtldUseExprInqireDao.deleteHtldUseExprInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public GamHtldUseExprInqireVO selectHtldUseExprInqireMaxKey(GamHtldUseExprInqireVO searchVO) throws Exception {
		return (GamHtldUseExprInqireVO) selectByPk("gamHtldUseExprInqireDao.selectHtldUseExprInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamHtldUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateHtldUseExprInqireComment(GamHtldUseExprInqireVO vo){
		update("gamHtldUseExprInqireDao.updateHtldUseExprInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public GamHtldUseExprInqireVO selectHtldUseExprInqireRenewInfo(GamHtldUseExprInqireVO searchVO) throws Exception {
		return (GamHtldUseExprInqireVO) selectByPk("gamHtldUseExprInqireDao.selectHtldUseExprInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamHtldUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateHtldUseExprInqireRenewInfo(GamHtldUseExprInqireVO vo){
		update("gamHtldUseExprInqireDao.updateHtldUseExprInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public GamHtldUseExprInqireVO selectHtldUseExprInqireCurrRenewInfo(GamHtldUseExprInqireVO searchVO) throws Exception {
		return (GamHtldUseExprInqireVO) selectByPk("gamHtldUseExprInqireDao.selectHtldUseExprInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대관리 목록
	 * @exception Exception
	 */
	public GamHtldUseExprInqireVO selectHtldUseExprInqireDetailQuaycd(GamHtldUseExprInqireVO searchVO) throws Exception {
		return (GamHtldUseExprInqireVO) selectByPk("gamHtldUseExprInqireDao.selectHtldUseExprInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamHtldUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateHtldUseExprInqireQuaycd(GamHtldUseExprInqireVO vo){
		update("gamHtldUseExprInqireDao.updateHtldUseExprInqireQuaycd_S", vo);
	}
}
