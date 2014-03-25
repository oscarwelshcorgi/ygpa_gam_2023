package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterUseExprInqireVO;

/**
 * @Class Name : gamMarineCenterUseExprInqireDao.java
 * @Description : 마린센터임대만기도래자료조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamMarineCenterUseExprInqireDao")
public class GamMarineCenterUseExprInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 마린센터임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
    public List selectMarineCenterUseExprInqireList(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
        return list("gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireList_D", searchVO);
    }

    /**
	 * 마린센터임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterUseExprInqireListTotCnt(GamMarineCenterUseExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireSum(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
		return (GamMarineCenterUseExprInqireVO) selectByPk("gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireSum_S", searchVO);
	}
    
	/**
	 * 마린센터임대 최초 신청을 등록한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	public void insertMarineCenterUseExprInqireFirst(GamMarineCenterUseExprInqireVO vo){
		insert("gamMarineCenterUseExprInqireDao.insertMarineCenterUseExprInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamMarineCenterUseExprInqireVO
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireMaxNo(GamMarineCenterUseExprInqireVO vo) {
        return (GamMarineCenterUseExprInqireVO)getSqlMapClientTemplate().queryForObject("gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireMaxNo_S", vo);
    }
    
    /**
	 * 마린센터임대 연장 신청을 등록한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	public void insertMarineCenterUseExprInqireRenew(GamMarineCenterUseExprInqireVO vo){
		insert("gamMarineCenterUseExprInqireDao.insertMarineCenterUseExprInqireRenew_S", vo);
	}
	
	/**
	 * 마린센터임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectMarineCenterUseExprInqireMaxMngCnt(GamMarineCenterUseExprInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 마린센터임대 연장 신청시 마린센터임대 상세를 복사하여 등록한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	public void insertMarineCenterUseExprInqireDetailRenew(GamMarineCenterUseExprInqireDetailVO vo){
		insert("gamMarineCenterUseExprInqireDao.insertMarineCenterUseExprInqireDetailRenew_S", vo);
	}
	
	/**
	 * 마린센터임대 정보를 수정한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	public void updateMarineCenterUseExprInqire(GamMarineCenterUseExprInqireVO vo){
		update("gamMarineCenterUseExprInqireDao.updateMarineCenterUseExprInqire_S", vo);
	}
	
	/**
	 * 마린센터임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
    public List selectMarineCenterUseExprInqireDetailList(GamMarineCenterUseExprInqireVO vo) throws Exception {
        return list("gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireDetailList_D", vo);
    }

    /**
	 * 마린센터임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterUseExprInqireDetailListTotCnt(GamMarineCenterUseExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterUseExprInqireLevReqestCnt(GamMarineCenterUseExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 마린센터임대 사진정보를 삭제한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteMarineCenterUseExprInqirePhoto(GamMarineCenterUseExprInqireVO vo){
		delete("gamMarineCenterUseExprInqireDao.deleteMarineCenterUseExprInqirePhoto_S", vo);
	}
    
	/**
	 * 마린센터임대 정보를 삭제한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteMarineCenterUseExprInqire(GamMarineCenterUseExprInqireVO vo){
		delete("gamMarineCenterUseExprInqireDao.deleteMarineCenterUseExprInqire_S", vo);
	}
    
	/**
	 * 마린센터임대 상세정보를 삭제한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteMarineCenterUseExprInqireDetail(GamMarineCenterUseExprInqireVO vo){
		delete("gamMarineCenterUseExprInqireDao.deleteMarineCenterUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 마린센터임대 상세를 등록한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void insertMarineCenterUseExprInqireDetail(GamMarineCenterUseExprInqireDetailVO vo){
		insert("gamMarineCenterUseExprInqireDao.insertMarineCenterUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 마린센터임대 상세를 수정한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterUseExprInqireDetail(GamMarineCenterUseExprInqireDetailVO vo){
		update("gamMarineCenterUseExprInqireDao.updateMarineCenterUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 마린센터임대 상세를 삭제한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void deleteMarineCenterUseExprInqireDetail2(GamMarineCenterUseExprInqireDetailVO vo){
		delete("gamMarineCenterUseExprInqireDao.deleteMarineCenterUseExprInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 마린센터임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대정보
	 * @exception Exception
	 */
	public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqirePrmisnInfo(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
		return (GamMarineCenterUseExprInqireVO) selectByPk("gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 마린센터임대 허가여부를 수정한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	public void updateMarineCenterUseExprInqirePrmisn(GamMarineCenterUseExprInqireLevReqestVO vo){
		update("gamMarineCenterUseExprInqireDao.updateMarineCenterUseExprInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamMarineCenterUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertMarineCenterUseExprInqireLevReqest(GamMarineCenterUseExprInqireLevReqestVO vo){
		insert("gamMarineCenterUseExprInqireDao.insertMarineCenterUseExprInqireLevReqest_S", vo);
	}
	
	/**
	 * 마린센터임대 허가여부를 취소한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	public void updateMarineCenterUseExprInqirePrmisnCancel(GamMarineCenterUseExprInqireLevReqestVO vo){
		update("gamMarineCenterUseExprInqireDao.updateMarineCenterUseExprInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamMarineCenterUseExprInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterUseExprInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 마린센터임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대상세 목록
	 * @exception Exception
	 */
    public List selectMarineCenterUseExprInqireDetailInfo(GamMarineCenterUseExprInqireVO vo) throws Exception {
        return list("gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamMarineCenterUseExprInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
    public List selectMarineCenterUseExprInqireFileList(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
        return list("gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterUseExprInqireFileListTotCnt(GamMarineCenterUseExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	public void insertMarineCenterUseExprInqireFile(GamMarineCenterUseExprInqireVO vo){
		insert("gamMarineCenterUseExprInqireDao.insertMarineCenterUseExprInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	public void updateMarineCenterUseExprInqireFile(GamMarineCenterUseExprInqireVO vo){
		insert("gamMarineCenterUseExprInqireDao.updateMarineCenterUseExprInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamMarineCenterUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteMarineCenterUseExprInqirePhotoSingle(GamMarineCenterUseExprInqireVO vo){
		delete("gamMarineCenterUseExprInqireDao.deleteMarineCenterUseExprInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireMaxKey(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
		return (GamMarineCenterUseExprInqireVO) selectByPk("gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterUseExprInqireComment(GamMarineCenterUseExprInqireVO vo){
		update("gamMarineCenterUseExprInqireDao.updateMarineCenterUseExprInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireRenewInfo(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
		return (GamMarineCenterUseExprInqireVO) selectByPk("gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterUseExprInqireRenewInfo(GamMarineCenterUseExprInqireVO vo){
		update("gamMarineCenterUseExprInqireDao.updateMarineCenterUseExprInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireCurrRenewInfo(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
		return (GamMarineCenterUseExprInqireVO) selectByPk("gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterUseExprInqireVO selectMarineCenterUseExprInqireDetailQuaycd(GamMarineCenterUseExprInqireVO searchVO) throws Exception {
		return (GamMarineCenterUseExprInqireVO) selectByPk("gamMarineCenterUseExprInqireDao.selectMarineCenterUseExprInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamMarineCenterUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterUseExprInqireQuaycd(GamMarineCenterUseExprInqireVO vo){
		update("gamMarineCenterUseExprInqireDao.updateMarineCenterUseExprInqireQuaycd_S", vo);
	}
}
