package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayUseExprInqireVO;

/**
 * @Class Name : gamCntnrQuayUseExprInqireDao.java
 * @Description : 컨테이너부두임대만기도래자료조회 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCntnrQuayUseExprInqireDao")
public class GamCntnrQuayUseExprInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 컨테이너부두임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
    public List selectCntnrQuayUseExprInqireList(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
        return list("gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireList_D", searchVO);
    }

    /**
	 * 컨테이너부두임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrQuayUseExprInqireListTotCnt(GamCntnrQuayUseExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireSum(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
		return (GamCntnrQuayUseExprInqireVO) selectByPk("gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireSum_S", searchVO);
	}
    
	/**
	 * 컨테이너부두임대 최초 신청을 등록한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	public void insertCntnrQuayUseExprInqireFirst(GamCntnrQuayUseExprInqireVO vo){
		insert("gamCntnrQuayUseExprInqireDao.insertCntnrQuayUseExprInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamCntnrQuayUseExprInqireVO
	 * @return 컨테이너부두임대관리 목록 총 갯수
	 * @exception
	 */
    public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireMaxNo(GamCntnrQuayUseExprInqireVO vo) {
        return (GamCntnrQuayUseExprInqireVO)getSqlMapClientTemplate().queryForObject("gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireMaxNo_S", vo);
    }
    
    /**
	 * 컨테이너부두임대 연장 신청을 등록한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	public void insertCntnrQuayUseExprInqireRenew(GamCntnrQuayUseExprInqireVO vo){
		insert("gamCntnrQuayUseExprInqireDao.insertCntnrQuayUseExprInqireRenew_S", vo);
	}
	
	/**
	 * 컨테이너부두임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectCntnrQuayUseExprInqireMaxMngCnt(GamCntnrQuayUseExprInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 컨테이너부두임대 연장 신청시 컨테이너부두임대 상세를 복사하여 등록한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	public void insertCntnrQuayUseExprInqireDetailRenew(GamCntnrQuayUseExprInqireDetailVO vo){
		insert("gamCntnrQuayUseExprInqireDao.insertCntnrQuayUseExprInqireDetailRenew_S", vo);
	}
	
	/**
	 * 컨테이너부두임대 정보를 수정한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	public void updateCntnrQuayUseExprInqire(GamCntnrQuayUseExprInqireVO vo){
		update("gamCntnrQuayUseExprInqireDao.updateCntnrQuayUseExprInqire_S", vo);
	}
	
	/**
	 * 컨테이너부두임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
    public List selectCntnrQuayUseExprInqireDetailList(GamCntnrQuayUseExprInqireVO vo) throws Exception {
        return list("gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireDetailList_D", vo);
    }

    /**
	 * 컨테이너부두임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrQuayUseExprInqireDetailListTotCnt(GamCntnrQuayUseExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrQuayUseExprInqireLevReqestCnt(GamCntnrQuayUseExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 컨테이너부두임대 사진정보를 삭제한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayUseExprInqirePhoto(GamCntnrQuayUseExprInqireVO vo){
		delete("gamCntnrQuayUseExprInqireDao.deleteCntnrQuayUseExprInqirePhoto_S", vo);
	}
    
	/**
	 * 컨테이너부두임대 정보를 삭제한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayUseExprInqire(GamCntnrQuayUseExprInqireVO vo){
		delete("gamCntnrQuayUseExprInqireDao.deleteCntnrQuayUseExprInqire_S", vo);
	}
    
	/**
	 * 컨테이너부두임대 상세정보를 삭제한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayUseExprInqireDetail(GamCntnrQuayUseExprInqireVO vo){
		delete("gamCntnrQuayUseExprInqireDao.deleteCntnrQuayUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 컨테이너부두임대 상세를 등록한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void insertCntnrQuayUseExprInqireDetail(GamCntnrQuayUseExprInqireDetailVO vo){
		insert("gamCntnrQuayUseExprInqireDao.insertCntnrQuayUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 컨테이너부두임대 상세를 수정한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayUseExprInqireDetail(GamCntnrQuayUseExprInqireDetailVO vo){
		update("gamCntnrQuayUseExprInqireDao.updateCntnrQuayUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 컨테이너부두임대 상세를 삭제한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayUseExprInqireDetail2(GamCntnrQuayUseExprInqireDetailVO vo){
		delete("gamCntnrQuayUseExprInqireDao.deleteCntnrQuayUseExprInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 컨테이너부두임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대정보
	 * @exception Exception
	 */
	public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqirePrmisnInfo(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
		return (GamCntnrQuayUseExprInqireVO) selectByPk("gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 컨테이너부두임대 허가여부를 수정한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	public void updateCntnrQuayUseExprInqirePrmisn(GamCntnrQuayUseExprInqireLevReqestVO vo){
		update("gamCntnrQuayUseExprInqireDao.updateCntnrQuayUseExprInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamCntnrQuayUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertCntnrQuayUseExprInqireLevReqest(GamCntnrQuayUseExprInqireLevReqestVO vo){
		insert("gamCntnrQuayUseExprInqireDao.insertCntnrQuayUseExprInqireLevReqest_S", vo);
	}
	
	/**
	 * 컨테이너부두임대 허가여부를 취소한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	public void updateCntnrQuayUseExprInqirePrmisnCancel(GamCntnrQuayUseExprInqireLevReqestVO vo){
		update("gamCntnrQuayUseExprInqireDao.updateCntnrQuayUseExprInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamCntnrQuayUseExprInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayUseExprInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 컨테이너부두임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대상세 목록
	 * @exception Exception
	 */
    public List selectCntnrQuayUseExprInqireDetailInfo(GamCntnrQuayUseExprInqireVO vo) throws Exception {
        return list("gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamCntnrQuayUseExprInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
    public List selectCntnrQuayUseExprInqireFileList(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
        return list("gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrQuayUseExprInqireFileListTotCnt(GamCntnrQuayUseExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	public void insertCntnrQuayUseExprInqireFile(GamCntnrQuayUseExprInqireVO vo){
		insert("gamCntnrQuayUseExprInqireDao.insertCntnrQuayUseExprInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	public void updateCntnrQuayUseExprInqireFile(GamCntnrQuayUseExprInqireVO vo){
		insert("gamCntnrQuayUseExprInqireDao.updateCntnrQuayUseExprInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamCntnrQuayUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayUseExprInqirePhotoSingle(GamCntnrQuayUseExprInqireVO vo){
		delete("gamCntnrQuayUseExprInqireDao.deleteCntnrQuayUseExprInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireMaxKey(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
		return (GamCntnrQuayUseExprInqireVO) selectByPk("gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayUseExprInqireComment(GamCntnrQuayUseExprInqireVO vo){
		update("gamCntnrQuayUseExprInqireDao.updateCntnrQuayUseExprInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireRenewInfo(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
		return (GamCntnrQuayUseExprInqireVO) selectByPk("gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayUseExprInqireRenewInfo(GamCntnrQuayUseExprInqireVO vo){
		update("gamCntnrQuayUseExprInqireDao.updateCntnrQuayUseExprInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireCurrRenewInfo(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
		return (GamCntnrQuayUseExprInqireVO) selectByPk("gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayUseExprInqireVO selectCntnrQuayUseExprInqireDetailQuaycd(GamCntnrQuayUseExprInqireVO searchVO) throws Exception {
		return (GamCntnrQuayUseExprInqireVO) selectByPk("gamCntnrQuayUseExprInqireDao.selectCntnrQuayUseExprInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamCntnrQuayUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateCntnrQuayUseExprInqireQuaycd(GamCntnrQuayUseExprInqireVO vo){
		update("gamCntnrQuayUseExprInqireDao.updateCntnrQuayUseExprInqireQuaycd_S", vo);
	}
}
