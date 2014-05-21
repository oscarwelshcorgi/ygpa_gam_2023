package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentSttusInqireDetailVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentSttusInqireLevReqestVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentSttusInqireVO;

/**
 * @Class Name : GamTrainPortRentSttusInqireDao.java
 * @Description : 철송장임대현황조회 
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamTrainPortRentSttusInqireDao")
public class GamTrainPortRentSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 철송장임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
    public List selectTrainPortRentSttusInqireList(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
        return list("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireList_D", searchVO);
    }

    /**
	 * 철송장임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortRentSttusInqireListTotCnt(GamTrainPortRentSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireSum(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
		return (GamTrainPortRentSttusInqireVO) selectByPk("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireSum_S", searchVO);
	}
    
	/**
	 * 철송장임대 최초 신청을 등록한다.
	 * @param vo GamTrainPortRentSttusInqireVO
	 * @exception Exception
	 */
	public void insertTrainPortRentSttusInqireFirst(GamTrainPortRentSttusInqireVO vo){
		insert("gamTrainPortRentSttusInqireDao.insertTrainPortRentSttusInqireFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamTrainPortRentSttusInqireVO
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireMaxNo(GamTrainPortRentSttusInqireVO vo) {
        return (GamTrainPortRentSttusInqireVO)getSqlMapClientTemplate().queryForObject("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireMaxNo_S", vo);
    }
    
    /**
	 * 철송장임대 연장 신청을 등록한다.
	 * @param vo GamTrainPortRentSttusInqireVO
	 * @exception Exception
	 */
	public void insertTrainPortRentSttusInqireRenew(GamTrainPortRentSttusInqireVO vo){
		insert("gamTrainPortRentSttusInqireDao.insertTrainPortRentSttusInqireRenew_S", vo);
	}
	
	/**
	 * 철송장임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectTrainPortRentSttusInqireMaxMngCnt(GamTrainPortRentSttusInqireVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 철송장임대 연장 신청시 철송장임대 상세를 복사하여 등록한다.
	 * @param vo GamTrainPortRentSttusInqireVO
	 * @exception Exception
	 */
	public void insertTrainPortRentSttusInqireDetailRenew(GamTrainPortRentSttusInqireDetailVO vo){
		insert("gamTrainPortRentSttusInqireDao.insertTrainPortRentSttusInqireDetailRenew_S", vo);
	}
	
	/**
	 * 철송장임대 정보를 수정한다.
	 * @param vo GamTrainPortRentSttusInqireVO
	 * @exception Exception
	 */
	public void updateTrainPortRentSttusInqire(GamTrainPortRentSttusInqireVO vo){
		update("gamTrainPortRentSttusInqireDao.updateTrainPortRentSttusInqire_S", vo);
	}
	
	/**
	 * 철송장임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
    public List selectTrainPortRentSttusInqireDetailList(GamTrainPortRentSttusInqireVO vo) throws Exception {
        return list("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireDetailList_D", vo);
    }

    /**
	 * 철송장임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortRentSttusInqireDetailListTotCnt(GamTrainPortRentSttusInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortRentSttusInqireLevReqestCnt(GamTrainPortRentSttusInqireVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireLevReqestCnt_S", vo);
    }
    
    /**
	 * 철송장임대 사진정보를 삭제한다.
	 * @param vo GamTrainPortRentSttusInqireVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentSttusInqirePhoto(GamTrainPortRentSttusInqireVO vo){
		delete("gamTrainPortRentSttusInqireDao.deleteTrainPortRentSttusInqirePhoto_S", vo);
	}
    
	/**
	 * 철송장임대 정보를 삭제한다.
	 * @param vo GamTrainPortRentSttusInqireVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentSttusInqire(GamTrainPortRentSttusInqireVO vo){
		delete("gamTrainPortRentSttusInqireDao.deleteTrainPortRentSttusInqire_S", vo);
	}
    
	/**
	 * 철송장임대 상세정보를 삭제한다.
	 * @param vo GamTrainPortRentSttusInqireVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentSttusInqireDetail(GamTrainPortRentSttusInqireVO vo){
		delete("gamTrainPortRentSttusInqireDao.deleteTrainPortRentSttusInqireDetail_S", vo);
	}
	
	/**
	 * 철송장임대 상세를 등록한다.
	 * @param vo GamTrainPortRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void insertTrainPortRentSttusInqireDetail(GamTrainPortRentSttusInqireDetailVO vo){
		insert("gamTrainPortRentSttusInqireDao.insertTrainPortRentSttusInqireDetail_S", vo);
	}
	
	/**
	 * 철송장임대 상세를 수정한다.
	 * @param vo GamTrainPortRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortRentSttusInqireDetail(GamTrainPortRentSttusInqireDetailVO vo){
		update("gamTrainPortRentSttusInqireDao.updateTrainPortRentSttusInqireDetail_S", vo);
	}
	
	/**
	 * 철송장임대 상세를 삭제한다.
	 * @param vo GamTrainPortRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentSttusInqireDetail2(GamTrainPortRentSttusInqireDetailVO vo){
		delete("gamTrainPortRentSttusInqireDao.deleteTrainPortRentSttusInqireDetail2_S", vo);
	}

	/**
	 * 승낙할 철송장임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대정보
	 * @exception Exception
	 */
	public GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqirePrmisnInfo(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
		return (GamTrainPortRentSttusInqireVO) selectByPk("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqirePrmisnInfo_S", searchVO);
	}
	
	/**
	 * 철송장임대 허가여부를 수정한다.
	 * @param vo GamTrainPortRentSttusInqireVO
	 * @exception Exception
	 */
	public void updateTrainPortRentSttusInqirePrmisn(GamTrainPortRentSttusInqireLevReqestVO vo){
		update("gamTrainPortRentSttusInqireDao.updateTrainPortRentSttusInqirePrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamTrainPortRentSttusInqireLevReqestVO
	 * @exception Exception
	 */
	public void insertTrainPortRentSttusInqireLevReqest(GamTrainPortRentSttusInqireLevReqestVO vo){
		insert("gamTrainPortRentSttusInqireDao.insertTrainPortRentSttusInqireLevReqest_S", vo);
	}
	
	/**
	 * 철송장임대 허가여부를 취소한다.
	 * @param vo GamTrainPortRentSttusInqireVO
	 * @exception Exception
	 */
	public void updateTrainPortRentSttusInqirePrmisnCancel(GamTrainPortRentSttusInqireLevReqestVO vo){
		update("gamTrainPortRentSttusInqireDao.updateTrainPortRentSttusInqirePrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamTrainPortRentSttusInqireLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentSttusInqireDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 철송장임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대상세 목록
	 * @exception Exception
	 */
    public List selectTrainPortRentSttusInqireDetailInfo(GamTrainPortRentSttusInqireVO vo) throws Exception {
        return list("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamTrainPortRentSttusInqireDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
    public List selectTrainPortRentSttusInqireFileList(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
        return list("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortRentSttusInqireFileListTotCnt(GamTrainPortRentSttusInqireVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamTrainPortRentSttusInqireVO
	 * @exception Exception
	 */
	public void insertTrainPortRentSttusInqireFile(GamTrainPortRentSttusInqireVO vo){
		insert("gamTrainPortRentSttusInqireDao.insertTrainPortRentSttusInqireFile_S", vo);
	}
	
	/**
	 * 파일을 업데이트한다.
	 * @param vo GamTrainPortRentSttusInqireVO
	 * @exception Exception
	 */
	public void updateTrainPortRentSttusInqireFile(GamTrainPortRentSttusInqireVO vo){
		insert("gamTrainPortRentSttusInqireDao.updateTrainPortRentSttusInqireFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamTrainPortRentSttusInqireVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentSttusInqirePhotoSingle(GamTrainPortRentSttusInqireVO vo){
		delete("gamTrainPortRentSttusInqireDao.deleteTrainPortRentSttusInqirePhotoSingle_S", vo);
	}
	
	/**
	 * 임대신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireMaxKey(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
		return (GamTrainPortRentSttusInqireVO) selectByPk("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamTrainPortRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortRentSttusInqireComment(GamTrainPortRentSttusInqireVO vo){
		update("gamTrainPortRentSttusInqireDao.updateTrainPortRentSttusInqireComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireRenewInfo(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
		return (GamTrainPortRentSttusInqireVO) selectByPk("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamTrainPortRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortRentSttusInqireRenewInfo(GamTrainPortRentSttusInqireVO vo){
		update("gamTrainPortRentSttusInqireDao.updateTrainPortRentSttusInqireRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireCurrRenewInfo(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
		return (GamTrainPortRentSttusInqireVO) selectByPk("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireDetailQuaycd(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
		return (GamTrainPortRentSttusInqireVO) selectByPk("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamTrainPortRentSttusInqireDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortRentSttusInqireQuaycd(GamTrainPortRentSttusInqireVO vo){
		update("gamTrainPortRentSttusInqireDao.updateTrainPortRentSttusInqireQuaycd_S", vo);
	}
	
	 /**
	 * 코픽스 이자율 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectCofixInfo() throws Exception {
        return list("gamTrainPortRentSttusInqireDao.selectCofixInfo_S", null);
    }
    
    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireBeforeQuarterInfo(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
		return (GamTrainPortRentSttusInqireVO) selectByPk("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireBeforeQuarterInfo_S", searchVO);
	}
	
	/**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortRentSttusInqireVO selectTrainPortRentSttusInqireCofixInfo(GamTrainPortRentSttusInqireVO searchVO) throws Exception {
		return (GamTrainPortRentSttusInqireVO) selectByPk("gamTrainPortRentSttusInqireDao.selectTrainPortRentSttusInqireCofixInfo_S", searchVO);
	}
}