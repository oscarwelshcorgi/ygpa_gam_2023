package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortUseExprInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortUseExprInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortUseExprInqireVO;

/**
 * @Class Name : gamTrainPortUseExprInqireDao.java
 * @Description : 철송장임대만기도래자료조회 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamTrainPortUseExprInqireDao")
public class GamTrainPortUseExprInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 철송장임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
    public List selectTrainPortUseExprInqireList(GamTrainPortUseExprInqireVO searchVO) throws Exception {
        return list("gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireList_D", searchVO);
    }

    /**
	 * 철송장임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortUseExprInqireListTotCnt(GamTrainPortUseExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortUseExprInqireVO selectTrainPortUseExprInqireSum(GamTrainPortUseExprInqireVO searchVO) throws Exception {
		return (GamTrainPortUseExprInqireVO) selectByPk("gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireSum_S", searchVO);
	}
    
	/**
	 * 철송장임대 최초 신청을 등록한다.
	 * @param vo GamTrainPortUseExprInqireVO
	 * @exception Exception
	 */
	public void insertTrainPortUseExprInqireFirst(GamTrainPortUseExprInqireVO vo){
		insert("gamTrainPortUseExprInqireDao.insertTrainPortUseExprInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamTrainPortUseExprInqireVO
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public GamTrainPortUseExprInqireVO selectTrainPortUseExprInqireMaxNo(GamTrainPortUseExprInqireVO vo) {
        return (GamTrainPortUseExprInqireVO)getSqlMapClientTemplate().queryForObject("gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireMaxNo_S", vo);
    }
    
    /**
	 * 철송장임대 연장 신청을 등록한다.
	 * @param vo GamTrainPortUseExprInqireVO
	 * @exception Exception
	 */
	public void insertTrainPortUseExprInqireRenew(GamTrainPortUseExprInqireVO vo){
		insert("gamTrainPortUseExprInqireDao.insertTrainPortUseExprInqireRenew_S", vo);
	}
	
	/**
	 * 철송장임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectTrainPortUseExprInqireMaxMngCnt(GamTrainPortUseExprInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 철송장임대 연장 신청시 철송장임대 상세를 복사하여 등록한다.
	 * @param vo GamTrainPortUseExprInqireVO
	 * @exception Exception
	 */
	public void insertTrainPortUseExprInqireDetailRenew(GamTrainPortUseExprInqireDetailVO vo){
		insert("gamTrainPortUseExprInqireDao.insertTrainPortUseExprInqireDetailRenew_S", vo);
	}
	
	/**
	 * 철송장임대 정보를 수정한다.
	 * @param vo GamTrainPortUseExprInqireVO
	 * @exception Exception
	 */
	public void updateTrainPortUseExprInqire(GamTrainPortUseExprInqireVO vo){
		update("gamTrainPortUseExprInqireDao.updateTrainPortUseExprInqire_S", vo);
	}
	
	/**
	 * 철송장임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
    public List selectTrainPortUseExprInqireDetailList(GamTrainPortUseExprInqireVO vo) throws Exception {
        return list("gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireDetailList_D", vo);
    }

    /**
	 * 철송장임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortUseExprInqireDetailListTotCnt(GamTrainPortUseExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortUseExprInqireLevReqestCnt(GamTrainPortUseExprInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 철송장임대 사진정보를 삭제한다.
	 * @param vo GamTrainPortUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteTrainPortUseExprInqirePhoto(GamTrainPortUseExprInqireVO vo){
		delete("gamTrainPortUseExprInqireDao.deleteTrainPortUseExprInqirePhoto_S", vo);
	}
    
	/**
	 * 철송장임대 정보를 삭제한다.
	 * @param vo GamTrainPortUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteTrainPortUseExprInqire(GamTrainPortUseExprInqireVO vo){
		delete("gamTrainPortUseExprInqireDao.deleteTrainPortUseExprInqire_S", vo);
	}
    
	/**
	 * 철송장임대 상세정보를 삭제한다.
	 * @param vo GamTrainPortUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteTrainPortUseExprInqireDetail(GamTrainPortUseExprInqireVO vo){
		delete("gamTrainPortUseExprInqireDao.deleteTrainPortUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 철송장임대 상세를 등록한다.
	 * @param vo GamTrainPortUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void insertTrainPortUseExprInqireDetail(GamTrainPortUseExprInqireDetailVO vo){
		insert("gamTrainPortUseExprInqireDao.insertTrainPortUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 철송장임대 상세를 수정한다.
	 * @param vo GamTrainPortUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortUseExprInqireDetail(GamTrainPortUseExprInqireDetailVO vo){
		update("gamTrainPortUseExprInqireDao.updateTrainPortUseExprInqireDetail_S", vo);
	}
	
	/**
	 * 철송장임대 상세를 삭제한다.
	 * @param vo GamTrainPortUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void deleteTrainPortUseExprInqireDetail2(GamTrainPortUseExprInqireDetailVO vo){
		delete("gamTrainPortUseExprInqireDao.deleteTrainPortUseExprInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 철송장임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대정보
	 * @exception Exception
	 */
	public GamTrainPortUseExprInqireVO selectTrainPortUseExprInqirePrmisnInfo(GamTrainPortUseExprInqireVO searchVO) throws Exception {
		return (GamTrainPortUseExprInqireVO) selectByPk("gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 철송장임대 허가여부를 수정한다.
	 * @param vo GamTrainPortUseExprInqireVO
	 * @exception Exception
	 */
	public void updateTrainPortUseExprInqirePrmisn(GamTrainPortUseExprInqireLevReqestVO vo){
		update("gamTrainPortUseExprInqireDao.updateTrainPortUseExprInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamTrainPortUseExprInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertTrainPortUseExprInqireLevReqest(GamTrainPortUseExprInqireLevReqestVO vo){
		insert("gamTrainPortUseExprInqireDao.insertTrainPortUseExprInqireLevReqest_S", vo);
	}
	
	/**
	 * 철송장임대 허가여부를 취소한다.
	 * @param vo GamTrainPortUseExprInqireVO
	 * @exception Exception
	 */
	public void updateTrainPortUseExprInqirePrmisnCancel(GamTrainPortUseExprInqireLevReqestVO vo){
		update("gamTrainPortUseExprInqireDao.updateTrainPortUseExprInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamTrainPortUseExprInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortUseExprInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 철송장임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대상세 목록
	 * @exception Exception
	 */
    public List selectTrainPortUseExprInqireDetailInfo(GamTrainPortUseExprInqireVO vo) throws Exception {
        return list("gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamTrainPortUseExprInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
    public List selectTrainPortUseExprInqireFileList(GamTrainPortUseExprInqireVO searchVO) throws Exception {
        return list("gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortUseExprInqireFileListTotCnt(GamTrainPortUseExprInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamTrainPortUseExprInqireVO
	 * @exception Exception
	 */
	public void insertTrainPortUseExprInqireFile(GamTrainPortUseExprInqireVO vo){
		insert("gamTrainPortUseExprInqireDao.insertTrainPortUseExprInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamTrainPortUseExprInqireVO
	 * @exception Exception
	 */
	public void updateTrainPortUseExprInqireFile(GamTrainPortUseExprInqireVO vo){
		insert("gamTrainPortUseExprInqireDao.updateTrainPortUseExprInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamTrainPortUseExprInqireVO
	 * @exception Exception
	 */
	public void deleteTrainPortUseExprInqirePhotoSingle(GamTrainPortUseExprInqireVO vo){
		delete("gamTrainPortUseExprInqireDao.deleteTrainPortUseExprInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortUseExprInqireVO selectTrainPortUseExprInqireMaxKey(GamTrainPortUseExprInqireVO searchVO) throws Exception {
		return (GamTrainPortUseExprInqireVO) selectByPk("gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamTrainPortUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortUseExprInqireComment(GamTrainPortUseExprInqireVO vo){
		update("gamTrainPortUseExprInqireDao.updateTrainPortUseExprInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortUseExprInqireVO selectTrainPortUseExprInqireRenewInfo(GamTrainPortUseExprInqireVO searchVO) throws Exception {
		return (GamTrainPortUseExprInqireVO) selectByPk("gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamTrainPortUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortUseExprInqireRenewInfo(GamTrainPortUseExprInqireVO vo){
		update("gamTrainPortUseExprInqireDao.updateTrainPortUseExprInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortUseExprInqireVO selectTrainPortUseExprInqireCurrRenewInfo(GamTrainPortUseExprInqireVO searchVO) throws Exception {
		return (GamTrainPortUseExprInqireVO) selectByPk("gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortUseExprInqireVO selectTrainPortUseExprInqireDetailQuaycd(GamTrainPortUseExprInqireVO searchVO) throws Exception {
		return (GamTrainPortUseExprInqireVO) selectByPk("gamTrainPortUseExprInqireDao.selectTrainPortUseExprInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamTrainPortUseExprInqireDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortUseExprInqireQuaycd(GamTrainPortUseExprInqireVO vo){
		update("gamTrainPortUseExprInqireDao.updateTrainPortUseExprInqireQuaycd_S", vo);
	}
}
