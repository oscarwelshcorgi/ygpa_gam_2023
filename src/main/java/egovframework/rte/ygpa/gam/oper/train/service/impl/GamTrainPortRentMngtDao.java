package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentMngtVO;

/**
 * @Class Name : GamTrainPortRentMngtDao.java
 * @Description : 철송장임대목록관리 
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamTrainPortRentMngtDao")
public class GamTrainPortRentMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 철송장임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
    public List selectTrainPortRentMngtList(GamTrainPortRentMngtVO searchVO) throws Exception {
        return list("gamTrainPortRentMngtDao.selectTrainPortRentMngtList_D", searchVO);
    }

    /**
	 * 철송장임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortRentMngtListTotCnt(GamTrainPortRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentMngtDao.selectTrainPortRentMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortRentMngtVO selectTrainPortRentMngtSum(GamTrainPortRentMngtVO searchVO) throws Exception {
		return (GamTrainPortRentMngtVO) selectByPk("gamTrainPortRentMngtDao.selectTrainPortRentMngtSum_S", searchVO);
	}
    
	/**
	 * 철송장임대 최초 신청을 등록한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	public void insertTrainPortRentMngtFirst(GamTrainPortRentMngtVO vo){
		insert("gamTrainPortRentMngtDao.insertTrainPortRentMngtFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamTrainPortRentMngtVO
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public GamTrainPortRentMngtVO selectTrainPortRentMngtMaxNo(GamTrainPortRentMngtVO vo) {
        return (GamTrainPortRentMngtVO)getSqlMapClientTemplate().queryForObject("gamTrainPortRentMngtDao.selectTrainPortRentMngtMaxNo_S", vo);
    }
    
    /**
	 * 철송장임대 연장 신청을 등록한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	public void insertTrainPortRentMngtRenew(GamTrainPortRentMngtVO vo){
		insert("gamTrainPortRentMngtDao.insertTrainPortRentMngtRenew_S", vo);
	}
	
	/**
	 * 철송장임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectTrainPortRentMngtMaxMngCnt(GamTrainPortRentMngtVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamTrainPortRentMngtDao.selectTrainPortRentMngtMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 철송장임대 연장 신청시 철송장임대 상세를 복사하여 등록한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	public void insertTrainPortRentMngtDetailRenew(GamTrainPortRentMngtDetailVO vo){
		insert("gamTrainPortRentMngtDao.insertTrainPortRentMngtDetailRenew_S", vo);
	}
	
	/**
	 * 철송장임대 정보를 수정한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	public void updateTrainPortRentMngt(GamTrainPortRentMngtVO vo){
		update("gamTrainPortRentMngtDao.updateTrainPortRentMngt_S", vo);
	}
	
	/**
	 * 철송장임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
    public List selectTrainPortRentMngtDetailList(GamTrainPortRentMngtVO vo) throws Exception {
        return list("gamTrainPortRentMngtDao.selectTrainPortRentMngtDetailList_D", vo);
    }

    /**
	 * 철송장임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortRentMngtDetailListTotCnt(GamTrainPortRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentMngtDao.selectTrainPortRentMngtDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortRentMngtLevReqestCnt(GamTrainPortRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentMngtDao.selectTrainPortRentMngtLevReqestCnt_S", vo);
    }
    
    /**
	 * 철송장임대 사진정보를 삭제한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentMngtPhoto(GamTrainPortRentMngtVO vo){
		delete("gamTrainPortRentMngtDao.deleteTrainPortRentMngtPhoto_S", vo);
	}
    
	/**
	 * 철송장임대 정보를 삭제한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentMngt(GamTrainPortRentMngtVO vo){
		delete("gamTrainPortRentMngtDao.deleteTrainPortRentMngt_S", vo);
	}
    
	/**
	 * 철송장임대 상세정보를 삭제한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentMngtDetail(GamTrainPortRentMngtVO vo){
		delete("gamTrainPortRentMngtDao.deleteTrainPortRentMngtDetail_S", vo);
	}
	
	/**
	 * 철송장임대 상세를 등록한다.
	 * @param vo GamTrainPortRentMngtDetailVO
	 * @exception Exception
	 */
	public void insertTrainPortRentMngtDetail(GamTrainPortRentMngtDetailVO vo){
		insert("gamTrainPortRentMngtDao.insertTrainPortRentMngtDetail_S", vo);
	}
	
	/**
	 * 철송장임대 상세를 수정한다.
	 * @param vo GamTrainPortRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortRentMngtDetail(GamTrainPortRentMngtDetailVO vo){
		update("gamTrainPortRentMngtDao.updateTrainPortRentMngtDetail_S", vo);
	}
	
	/**
	 * 철송장임대 상세를 삭제한다.
	 * @param vo GamTrainPortRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentMngtDetail2(GamTrainPortRentMngtDetailVO vo){
		delete("gamTrainPortRentMngtDao.deleteTrainPortRentMngtDetail2_S", vo);
	}

	/**
	 * 승낙할 철송장임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대정보
	 * @exception Exception
	 */
	public GamTrainPortRentMngtVO selectTrainPortRentMngtPrmisnInfo(GamTrainPortRentMngtVO searchVO) throws Exception {
		return (GamTrainPortRentMngtVO) selectByPk("gamTrainPortRentMngtDao.selectTrainPortRentMngtPrmisnInfo_S", searchVO);
	}
	
	/**
	 * 철송장임대 허가여부를 수정한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	public void updateTrainPortRentMngtPrmisn(GamTrainPortRentMngtLevReqestVO vo){
		update("gamTrainPortRentMngtDao.updateTrainPortRentMngtPrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamTrainPortRentMngtLevReqestVO
	 * @exception Exception
	 */
	public void insertTrainPortRentMngtLevReqest(GamTrainPortRentMngtLevReqestVO vo){
		insert("gamTrainPortRentMngtDao.insertTrainPortRentMngtLevReqest_S", vo);
	}
	
	/**
	 * 철송장임대 허가여부를 취소한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	public void updateTrainPortRentMngtPrmisnCancel(GamTrainPortRentMngtLevReqestVO vo){
		update("gamTrainPortRentMngtDao.updateTrainPortRentMngtPrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamTrainPortRentMngtLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentMngtDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 철송장임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대상세 목록
	 * @exception Exception
	 */
    public List selectTrainPortRentMngtDetailInfo(GamTrainPortRentMngtVO vo) throws Exception {
        return list("gamTrainPortRentMngtDao.selectTrainPortRentMngtDetailInfo_S", vo);
    }
    
    /**
	 * 공시지가 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectOlnlpInfo() throws Exception {
        return list("gamTrainPortRentMngtDao.selectOlnlpInfo_S", null);
    }
    
    /**
	 * 파일 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
    public List selectTrainPortRentMngtFileList(GamTrainPortRentMngtVO searchVO) throws Exception {
        return list("gamTrainPortRentMngtDao.selectTrainPortRentMngtFileList_D", searchVO);
    }

    /**
	 * 파일 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortRentMngtFileListTotCnt(GamTrainPortRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentMngtDao.selectTrainPortRentMngtFileListTotCnt_S", searchVO);
    }
    
    /**
	 * 파일을 등록한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	public void insertTrainPortRentMngtFile(GamTrainPortRentMngtVO vo){
		insert("gamTrainPortRentMngtDao.insertTrainPortRentMngtFile_S", vo);
	}
	
	/**
	 * 파일을 수정한다.
	 * @param vo GamAssetRentVO
	 * @exception Exception
	 */
	public void updateTrainPortRentMngtFile(GamTrainPortRentMngtVO vo){
		insert("gamTrainPortRentMngtDao.updateTrainPortRentMngtFile_S", vo);
	}
    
	/**
	 * 파일을 삭제한다.(1row)
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentMngtPhotoSingle(GamTrainPortRentMngtVO vo){
		delete("gamTrainPortRentMngtDao.deleteTrainPortRentMngtPhotoSingle_S", vo);
	}
	
	/**
	 * 철송장임대 신규저장시 키값 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortRentMngtVO selectTrainPortRentMngtMaxKey(GamTrainPortRentMngtVO searchVO) throws Exception {
		return (GamTrainPortRentMngtVO) selectByPk("gamTrainPortRentMngtDao.selectTrainPortRentMngtMaxKey_S", searchVO);
	}
	
	/**
	 * 코멘트를 수정한다.
	 * @param vo GamTrainPortRentMngtVO
	 * @exception Exception
	 */
	public void updateTrainPortRentMngtComment(GamTrainPortRentMngtVO vo){
		update("gamTrainPortRentMngtDao.updateTrainPortRentMngtComment_S", vo);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대 목록
	 * @exception Exception
	 */
	public GamTrainPortRentMngtVO selectTrainPortRentMngtRenewInfo(GamTrainPortRentMngtVO searchVO) throws Exception {
		return (GamTrainPortRentMngtVO) selectByPk("gamTrainPortRentMngtDao.selectTrainPortRentMngtRenewInfo_S", searchVO);
	}
	
	/**
	 * 연장신청시 총사용기간, 총사용료 , 총면적을 업데이트 한다.
	 * @param vo GamAssetRentDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortRentMngtRenewInfo(GamTrainPortRentMngtVO vo){
		update("gamTrainPortRentMngtDao.updateTrainPortRentMngtRenewInfo_S", vo);
	}
	
	/**
	 * 신청저장시 총사용기간, 총사용료 , 총면적 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대 목록
	 * @exception Exception
	 */
	public GamTrainPortRentMngtVO selectTrainPortRentMngtCurrRenewInfo(GamTrainPortRentMngtVO searchVO) throws Exception {
		return (GamTrainPortRentMngtVO) selectByPk("gamTrainPortRentMngtDao.selectTrainPortRentMngtCurrRenewInfo_S", searchVO);
	}
	
	/**
	 * 신청저장시 철송장임대상세테이블의 (MIN)순번의 부두코드 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortRentMngtVO selectTrainPortRentMngtDetailQuaycd(GamTrainPortRentMngtVO searchVO) throws Exception {
		return (GamTrainPortRentMngtVO) selectByPk("gamTrainPortRentMngtDao.selectTrainPortRentMngtDetailQuaycd_S", searchVO);
	}
	
	/**
	 * 신청저장시 철송장임대테이블의 부두코드를 업데이트 한다.
	 * @param vo GamTrainPortRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortRentMngtQuaycd(GamTrainPortRentMngtVO vo){
		update("gamTrainPortRentMngtDao.updateTrainPortRentMngtQuaycd_S", vo);
	}
	
	/**
	 * 코픽스 이자율 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공시지가 목록
	 * @exception Exception
	 */
    public List selectCofixInfo() throws Exception {
        return list("gamTrainPortRentMngtDao.selectCofixInfo_S", null);
    }
    
    /**
	 * 현재날짜기준으로 이전 분기의 연도와 시작월과 종료월 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortRentMngtVO selectTrainPortRentMngtBeforeQuarterInfo(GamTrainPortRentMngtVO searchVO) throws Exception {
		return (GamTrainPortRentMngtVO) selectByPk("gamTrainPortRentMngtDao.selectTrainPortRentMngtBeforeQuarterInfo_S", searchVO);
	}
	
	/**
	 * 이전 분기의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortRentMngtVO selectTrainPortRentMngtCofixInfo(GamTrainPortRentMngtVO searchVO) throws Exception {
		return (GamTrainPortRentMngtVO) selectByPk("gamTrainPortRentMngtDao.selectTrainPortRentMngtCofixInfo_S", searchVO);
	}
	
	/**
	 * 가장 마지막데이터의 연도와 월에 해당하는 코픽스 이자율 가져오기.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortRentMngtVO selectTrainPortRentMngtCofixInfoMax(GamTrainPortRentMngtVO searchVO) throws Exception {
		return (GamTrainPortRentMngtVO) selectByPk("gamTrainPortRentMngtDao.selectTrainPortRentMngtCofixInfoMax_S", searchVO);
	}
}