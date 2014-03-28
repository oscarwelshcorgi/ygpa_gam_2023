package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortNticArrvlDtaInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortNticArrvlDtaInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortNticArrvlDtaInqireVO;

/**
 * @Class Name : gamTrainPortNticArrvlDtaInqireDao.java
 * @Description : 철송장임대고지도래현황조회 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamTrainPortNticArrvlDtaInqireDao")
public class GamTrainPortNticArrvlDtaInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 철송장임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
    public List selectTrainPortNticArrvlDtaInqireList(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception {
        return list("gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireList_D", searchVO);
    }

    /**
	 * 철송장임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortNticArrvlDtaInqireListTotCnt(GamTrainPortNticArrvlDtaInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortNticArrvlDtaInqireVO selectTrainPortNticArrvlDtaInqireSum(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamTrainPortNticArrvlDtaInqireVO) selectByPk("gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireSum_S", searchVO);
	}
    
	/**
	 * 철송장임대 최초 신청을 등록한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertTrainPortNticArrvlDtaInqireFirst(GamTrainPortNticArrvlDtaInqireVO vo){
		insert("gamTrainPortNticArrvlDtaInqireDao.insertTrainPortNticArrvlDtaInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamTrainPortNticArrvlDtaInqireVO
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public GamTrainPortNticArrvlDtaInqireVO selectTrainPortNticArrvlDtaInqireMaxNo(GamTrainPortNticArrvlDtaInqireVO vo) {
        return (GamTrainPortNticArrvlDtaInqireVO)getSqlMapClientTemplate().queryForObject("gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireMaxNo_S", vo);
    }
    
    /**
	 * 철송장임대 연장 신청을 등록한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertTrainPortNticArrvlDtaInqireRenew(GamTrainPortNticArrvlDtaInqireVO vo){
		insert("gamTrainPortNticArrvlDtaInqireDao.insertTrainPortNticArrvlDtaInqireRenew_S", vo);
	}
	
	/**
	 * 철송장임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectTrainPortNticArrvlDtaInqireMaxMngCnt(GamTrainPortNticArrvlDtaInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 철송장임대 연장 신청시 철송장임대 상세를 복사하여 등록한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertTrainPortNticArrvlDtaInqireDetailRenew(GamTrainPortNticArrvlDtaInqireDetailVO vo){
		insert("gamTrainPortNticArrvlDtaInqireDao.insertTrainPortNticArrvlDtaInqireDetailRenew_S", vo);
	}
	
	/**
	 * 철송장임대 정보를 수정한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updateTrainPortNticArrvlDtaInqire(GamTrainPortNticArrvlDtaInqireVO vo){
		update("gamTrainPortNticArrvlDtaInqireDao.updateTrainPortNticArrvlDtaInqire_S", vo);
	}
	
	/**
	 * 철송장임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
    public List selectTrainPortNticArrvlDtaInqireDetailList(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception {
        return list("gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireDetailList_D", vo);
    }

    /**
	 * 철송장임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortNticArrvlDtaInqireDetailListTotCnt(GamTrainPortNticArrvlDtaInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortNticArrvlDtaInqireLevReqestCnt(GamTrainPortNticArrvlDtaInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 철송장임대 사진정보를 삭제한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deleteTrainPortNticArrvlDtaInqirePhoto(GamTrainPortNticArrvlDtaInqireVO vo){
		delete("gamTrainPortNticArrvlDtaInqireDao.deleteTrainPortNticArrvlDtaInqirePhoto_S", vo);
	}
    
	/**
	 * 철송장임대 정보를 삭제한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deleteTrainPortNticArrvlDtaInqire(GamTrainPortNticArrvlDtaInqireVO vo){
		delete("gamTrainPortNticArrvlDtaInqireDao.deleteTrainPortNticArrvlDtaInqire_S", vo);
	}
    
	/**
	 * 철송장임대 상세정보를 삭제한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deleteTrainPortNticArrvlDtaInqireDetail(GamTrainPortNticArrvlDtaInqireVO vo){
		delete("gamTrainPortNticArrvlDtaInqireDao.deleteTrainPortNticArrvlDtaInqireDetail_S", vo);
	}
	
	/**
	 * 철송장임대 상세를 등록한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void insertTrainPortNticArrvlDtaInqireDetail(GamTrainPortNticArrvlDtaInqireDetailVO vo){
		insert("gamTrainPortNticArrvlDtaInqireDao.insertTrainPortNticArrvlDtaInqireDetail_S", vo);
	}
	
	/**
	 * 철송장임대 상세를 수정한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortNticArrvlDtaInqireDetail(GamTrainPortNticArrvlDtaInqireDetailVO vo){
		update("gamTrainPortNticArrvlDtaInqireDao.updateTrainPortNticArrvlDtaInqireDetail_S", vo);
	}
	
	/**
	 * 철송장임대 상세를 삭제한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void deleteTrainPortNticArrvlDtaInqireDetail2(GamTrainPortNticArrvlDtaInqireDetailVO vo){
		delete("gamTrainPortNticArrvlDtaInqireDao.deleteTrainPortNticArrvlDtaInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 철송장임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대정보
	 * @exception Exception
	 */
	public GamTrainPortNticArrvlDtaInqireVO selectTrainPortNticArrvlDtaInqirePrmisnInfo(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamTrainPortNticArrvlDtaInqireVO) selectByPk("gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 철송장임대 허가여부를 수정한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updateTrainPortNticArrvlDtaInqirePrmisn(GamTrainPortNticArrvlDtaInqireLevReqestVO vo){
		update("gamTrainPortNticArrvlDtaInqireDao.updateTrainPortNticArrvlDtaInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertTrainPortNticArrvlDtaInqireLevReqest(GamTrainPortNticArrvlDtaInqireLevReqestVO vo){
		insert("gamTrainPortNticArrvlDtaInqireDao.insertTrainPortNticArrvlDtaInqireLevReqest_S", vo);
	}
	
	/**
	 * 철송장임대 허가여부를 취소한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updateTrainPortNticArrvlDtaInqirePrmisnCancel(GamTrainPortNticArrvlDtaInqireLevReqestVO vo){
		update("gamTrainPortNticArrvlDtaInqireDao.updateTrainPortNticArrvlDtaInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamTrainPortNticArrvlDtaInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortNticArrvlDtaInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 철송장임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대상세 목록
	 * @exception Exception
	 */
    public List selectTrainPortNticArrvlDtaInqireDetailInfo(GamTrainPortNticArrvlDtaInqireVO vo) throws Exception {
        return list("gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamTrainPortNticArrvlDtaInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
    public List selectTrainPortNticArrvlDtaInqireFileList(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception {
        return list("gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortNticArrvlDtaInqireFileListTotCnt(GamTrainPortNticArrvlDtaInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void insertTrainPortNticArrvlDtaInqireFile(GamTrainPortNticArrvlDtaInqireVO vo){
		insert("gamTrainPortNticArrvlDtaInqireDao.insertTrainPortNticArrvlDtaInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void updateTrainPortNticArrvlDtaInqireFile(GamTrainPortNticArrvlDtaInqireVO vo){
		insert("gamTrainPortNticArrvlDtaInqireDao.updateTrainPortNticArrvlDtaInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamTrainPortNticArrvlDtaInqireVO
	 * @exception Exception
	 */
	public void deleteTrainPortNticArrvlDtaInqirePhotoSingle(GamTrainPortNticArrvlDtaInqireVO vo){
		delete("gamTrainPortNticArrvlDtaInqireDao.deleteTrainPortNticArrvlDtaInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortNticArrvlDtaInqireVO selectTrainPortNticArrvlDtaInqireMaxKey(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamTrainPortNticArrvlDtaInqireVO) selectByPk("gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortNticArrvlDtaInqireComment(GamTrainPortNticArrvlDtaInqireVO vo){
		update("gamTrainPortNticArrvlDtaInqireDao.updateTrainPortNticArrvlDtaInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortNticArrvlDtaInqireVO selectTrainPortNticArrvlDtaInqireRenewInfo(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamTrainPortNticArrvlDtaInqireVO) selectByPk("gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortNticArrvlDtaInqireRenewInfo(GamTrainPortNticArrvlDtaInqireVO vo){
		update("gamTrainPortNticArrvlDtaInqireDao.updateTrainPortNticArrvlDtaInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortNticArrvlDtaInqireVO selectTrainPortNticArrvlDtaInqireCurrRenewInfo(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamTrainPortNticArrvlDtaInqireVO) selectByPk("gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortNticArrvlDtaInqireVO selectTrainPortNticArrvlDtaInqireDetailQuaycd(GamTrainPortNticArrvlDtaInqireVO searchVO) throws Exception {
		return (GamTrainPortNticArrvlDtaInqireVO) selectByPk("gamTrainPortNticArrvlDtaInqireDao.selectTrainPortNticArrvlDtaInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamTrainPortNticArrvlDtaInqireDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortNticArrvlDtaInqireQuaycd(GamTrainPortNticArrvlDtaInqireVO vo){
		update("gamTrainPortNticArrvlDtaInqireDao.updateTrainPortNticArrvlDtaInqireQuaycd_S", vo);
	}
}
