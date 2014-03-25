package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterFeeExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterFeeExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterFeeExprInqireVO;

/**
 * @Class Name : gamMarineCenterFeeExprInqireDao.java
 * @Description : 마린센터임대고지도래자료조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamMarineCenterFeeExprInqireDao")
public class GamMarineCenterFeeExprInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 마린센터임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
    public List selectMarineCenterFeeExprInqireList(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {
        return list("gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireList_D", searchVO);
    }

    /**
	 * 마린센터임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterFeeExprInqireListTotCnt(GamMarineCenterFeeExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireSum(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {
		return (GamMarineCenterFeeExprInqireVO) selectByPk("gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireSum_S", searchVO);
	}
    
	/**
	 * 마린센터임대 최초 신청을 등록한다.
	 * @param vo GamMarineCenterFeeExprInqireVO
	 * @exception Exception
	 */
	public void insertMarineCenterFeeExprInqireFirst(GamMarineCenterFeeExprInqireVO vo){
		insert("gamMarineCenterFeeExprInqireDao.insertMarineCenterFeeExprInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamMarineCenterFeeExprInqireVO
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireMaxNo(GamMarineCenterFeeExprInqireVO vo) {
        return (GamMarineCenterFeeExprInqireVO)getSqlMapClientTemplate().queryForObject("gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireMaxNo_S", vo);
    }
    
    /**
	 * 마린센터임대 연장 신청을 등록한다.
	 * @param vo GamMarineCenterFeeExprInqireVO
	 * @exception Exception
	 */
	public void insertMarineCenterFeeExprInqireRenew(GamMarineCenterFeeExprInqireVO vo){
		insert("gamMarineCenterFeeExprInqireDao.insertMarineCenterFeeExprInqireRenew_S", vo);
	}
	
	/**
	 * 마린센터임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectMarineCenterFeeExprInqireMaxMngCnt(GamMarineCenterFeeExprInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 마린센터임대 연장 신청시 마린센터임대 상세를 복사하여 등록한다.
	 * @param vo GamMarineCenterFeeExprInqireVO
	 * @exception Exception
	 */
	public void insertMarineCenterFeeExprInqireDetailRenew(GamMarineCenterFeeExprInqireDetailVO vo){
		insert("gamMarineCenterFeeExprInqireDao.insertMarineCenterFeeExprInqireDetailRenew_S", vo);
	}
	
	/**
	 * 마린센터임대 정보를 수정한다.
	 * @param vo GamMarineCenterFeeExprInqireVO
	 * @exception Exception
	 */
	public void updateMarineCenterFeeExprInqire(GamMarineCenterFeeExprInqireVO vo){
		update("gamMarineCenterFeeExprInqireDao.updateMarineCenterFeeExprInqire_S", vo);
	}
	
	/**
	 * 마린센터임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
    public List selectMarineCenterFeeExprInqireDetailList(GamMarineCenterFeeExprInqireVO vo) throws Exception {
        return list("gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireDetailList_D", vo);
    }

    /**
	 * 마린센터임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterFeeExprInqireDetailListTotCnt(GamMarineCenterFeeExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterFeeExprInqireLevReqestCnt(GamMarineCenterFeeExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 마린센터임대 사진정보를 삭제한다.
	 * @param vo GamMarineCenterFeeExprInqireVO
	 * @exception Exception
	 */
	public void deleteMarineCenterFeeExprInqirePhoto(GamMarineCenterFeeExprInqireVO vo){
		delete("gamMarineCenterFeeExprInqireDao.deleteMarineCenterFeeExprInqirePhoto_S", vo);
	}
    
	/**
	 * 마린센터임대 정보를 삭제한다.
	 * @param vo GamMarineCenterFeeExprInqireVO
	 * @exception Exception
	 */
	public void deleteMarineCenterFeeExprInqire(GamMarineCenterFeeExprInqireVO vo){
		delete("gamMarineCenterFeeExprInqireDao.deleteMarineCenterFeeExprInqire_S", vo);
	}
    
	/**
	 * 마린센터임대 상세정보를 삭제한다.
	 * @param vo GamMarineCenterFeeExprInqireVO
	 * @exception Exception
	 */
	public void deleteMarineCenterFeeExprInqireDetail(GamMarineCenterFeeExprInqireVO vo){
		delete("gamMarineCenterFeeExprInqireDao.deleteMarineCenterFeeExprInqireDetail_S", vo);
	}
	
	/**
	 * 마린센터임대 상세를 등록한다.
	 * @param vo GamMarineCenterFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void insertMarineCenterFeeExprInqireDetail(GamMarineCenterFeeExprInqireDetailVO vo){
		insert("gamMarineCenterFeeExprInqireDao.insertMarineCenterFeeExprInqireDetail_S", vo);
	}
	
	/**
	 * 마린센터임대 상세를 수정한다.
	 * @param vo GamMarineCenterFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterFeeExprInqireDetail(GamMarineCenterFeeExprInqireDetailVO vo){
		update("gamMarineCenterFeeExprInqireDao.updateMarineCenterFeeExprInqireDetail_S", vo);
	}
	
	/**
	 * 마린센터임대 상세를 삭제한다.
	 * @param vo GamMarineCenterFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void deleteMarineCenterFeeExprInqireDetail2(GamMarineCenterFeeExprInqireDetailVO vo){
		delete("gamMarineCenterFeeExprInqireDao.deleteMarineCenterFeeExprInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 마린센터임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대정보
	 * @exception Exception
	 */
	public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqirePrmisnInfo(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {
		return (GamMarineCenterFeeExprInqireVO) selectByPk("gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 마린센터임대 허가여부를 수정한다.
	 * @param vo GamMarineCenterFeeExprInqireVO
	 * @exception Exception
	 */
	public void updateMarineCenterFeeExprInqirePrmisn(GamMarineCenterFeeExprInqireLevReqestVO vo){
		update("gamMarineCenterFeeExprInqireDao.updateMarineCenterFeeExprInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamMarineCenterFeeExprInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertMarineCenterFeeExprInqireLevReqest(GamMarineCenterFeeExprInqireLevReqestVO vo){
		insert("gamMarineCenterFeeExprInqireDao.insertMarineCenterFeeExprInqireLevReqest_S", vo);
	}
	
	/**
	 * 마린센터임대 허가여부를 취소한다.
	 * @param vo GamMarineCenterFeeExprInqireVO
	 * @exception Exception
	 */
	public void updateMarineCenterFeeExprInqirePrmisnCancel(GamMarineCenterFeeExprInqireLevReqestVO vo){
		update("gamMarineCenterFeeExprInqireDao.updateMarineCenterFeeExprInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamMarineCenterFeeExprInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterFeeExprInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 마린센터임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대상세 목록
	 * @exception Exception
	 */
    public List selectMarineCenterFeeExprInqireDetailInfo(GamMarineCenterFeeExprInqireVO vo) throws Exception {
        return list("gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamMarineCenterFeeExprInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
    public List selectMarineCenterFeeExprInqireFileList(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {
        return list("gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterFeeExprInqireFileListTotCnt(GamMarineCenterFeeExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamMarineCenterFeeExprInqireVO
	 * @exception Exception
	 */
	public void insertMarineCenterFeeExprInqireFile(GamMarineCenterFeeExprInqireVO vo){
		insert("gamMarineCenterFeeExprInqireDao.insertMarineCenterFeeExprInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamMarineCenterFeeExprInqireVO
	 * @exception Exception
	 */
	public void updateMarineCenterFeeExprInqireFile(GamMarineCenterFeeExprInqireVO vo){
		insert("gamMarineCenterFeeExprInqireDao.updateMarineCenterFeeExprInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamMarineCenterFeeExprInqireVO
	 * @exception Exception
	 */
	public void deleteMarineCenterFeeExprInqirePhotoSingle(GamMarineCenterFeeExprInqireVO vo){
		delete("gamMarineCenterFeeExprInqireDao.deleteMarineCenterFeeExprInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireMaxKey(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {
		return (GamMarineCenterFeeExprInqireVO) selectByPk("gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamMarineCenterFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterFeeExprInqireComment(GamMarineCenterFeeExprInqireVO vo){
		update("gamMarineCenterFeeExprInqireDao.updateMarineCenterFeeExprInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireRenewInfo(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {
		return (GamMarineCenterFeeExprInqireVO) selectByPk("gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamMarineCenterFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterFeeExprInqireRenewInfo(GamMarineCenterFeeExprInqireVO vo){
		update("gamMarineCenterFeeExprInqireDao.updateMarineCenterFeeExprInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireCurrRenewInfo(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {
		return (GamMarineCenterFeeExprInqireVO) selectByPk("gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterFeeExprInqireVO selectMarineCenterFeeExprInqireDetailQuaycd(GamMarineCenterFeeExprInqireVO searchVO) throws Exception {
		return (GamMarineCenterFeeExprInqireVO) selectByPk("gamMarineCenterFeeExprInqireDao.selectMarineCenterFeeExprInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamMarineCenterFeeExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterFeeExprInqireQuaycd(GamMarineCenterFeeExprInqireVO vo){
		update("gamMarineCenterFeeExprInqireDao.updateMarineCenterFeeExprInqireQuaycd_S", vo);
	}
}
