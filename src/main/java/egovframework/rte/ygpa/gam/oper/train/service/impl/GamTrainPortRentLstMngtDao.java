package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentLstMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentLstMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortRentLstMngtVO;

/**
 * @Class Name : GamTrainPortRentLstMngtDao.java
 * @Description : 철송장임대목록관리 (철송장/철송장/철송장임대목록관리)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamTrainPortRentLstMngtDao")
public class GamTrainPortRentLstMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 철송장 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectTrainPortRentLstMngtList(GamTrainPortRentLstMngtVO searchVO) throws Exception {
        return list("gamTrainPortRentLstMngtDao.selectTrainPortRentLstMngtList_D", searchVO);
    }

    /**
	 * 철송장 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortRentLstMngtListTotCnt(GamTrainPortRentLstMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentLstMngtDao.selectTrainPortRentLstMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamTrainPortRentLstMngtVO selectTrainPortRentLstMngtSum(GamTrainPortRentLstMngtVO searchVO) throws Exception {
		return (GamTrainPortRentLstMngtVO) selectByPk("gamTrainPortRentLstMngtDao.selectTrainPortRentLstMngtSum_S", searchVO);
	}
    
	/**
	 * 철송장 최초 신청을 등록한다.
	 * @param vo GamTrainPortRentLstMngtVO
	 * @exception Exception
	 */
	public void insertTrainPortRentLstMngtFirst(GamTrainPortRentLstMngtVO vo){
		insert("gamTrainPortRentLstMngtDao.insertTrainPortRentLstMngtFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamTrainPortRentLstMngtVO
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public GamTrainPortRentLstMngtVO selectTrainPortRentLstMngtMaxNo(GamTrainPortRentLstMngtVO vo) {
        return (GamTrainPortRentLstMngtVO)getSqlMapClientTemplate().queryForObject("gamTrainPortRentLstMngtDao.selectTrainPortRentLstMngtMaxNo_S", vo);
    }
    
    /**
	 * 철송장 연장 신청을 등록한다.
	 * @param vo GamTrainPortRentLstMngtVO
	 * @exception Exception
	 */
	public void insertTrainPortRentLstMngtRenew(GamTrainPortRentLstMngtVO vo){
		insert("gamTrainPortRentLstMngtDao.insertTrainPortRentLstMngtRenew_S", vo);
	}
	
	/**
	 * 철송장 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장관리 목록 총 갯수
	 * @exception
	 */
    public String selectTrainPortRentLstMngtMaxMngCnt(GamTrainPortRentLstMngtVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamTrainPortRentLstMngtDao.selectTrainPortRentLstMngtMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 철송장 연장 신청시 철송장 상세를 복사하여 등록한다.
	 * @param vo GamTrainPortRentLstMngtVO
	 * @exception Exception
	 */
	public void insertTrainPortRentLstMngtDetailRenew(GamTrainPortRentLstMngtDetailVO vo){
		insert("gamTrainPortRentLstMngtDao.insertTrainPortRentLstMngtDetailRenew_S", vo);
	}
	
	/**
	 * 철송장 정보를 수정한다.
	 * @param vo GamTrainPortRentLstMngtVO
	 * @exception Exception
	 */
	public void updateTrainPortRentLstMngt(GamTrainPortRentLstMngtVO vo){
		update("gamTrainPortRentLstMngtDao.updateTrainPortRentLstMngt_S", vo);
	}
	
	/**
	 * 철송장 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectTrainPortRentLstMngtDetailList(GamTrainPortRentLstMngtVO vo) throws Exception {
        return list("gamTrainPortRentLstMngtDao.selectTrainPortRentLstMngtDetailList_D", vo);
    }

    /**
	 * 철송장 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectTrainPortRentLstMngtDetailListTotCnt(GamTrainPortRentLstMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentLstMngtDao.selectTrainPortRentLstMngtDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 
	 * @exception
	 */
    public int selectTrainPortRentLstMngtLevReqestCnt(GamTrainPortRentLstMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentLstMngtDao.selectTrainPortRentLstMngtLevReqestCnt_S", vo);
    }
    
    /**
	 * 철송장 사진정보를 삭제한다.
	 * @param vo GamTrainPortRentLstMngtVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentLstMngtPhoto(GamTrainPortRentLstMngtVO vo){
		delete("gamTrainPortRentLstMngtDao.deleteTrainPortRentLstMngtPhoto_S", vo);
	}
    
	/**
	 * 철송장 정보를 삭제한다.
	 * @param vo GamTrainPortRentLstMngtVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentLstMngt(GamTrainPortRentLstMngtVO vo){
		delete("gamTrainPortRentLstMngtDao.deleteTrainPortRentLstMngt_S", vo);
	}
    
	/**
	 * 철송장 상세정보를 삭제한다.
	 * @param vo GamTrainPortRentLstMngtVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentLstMngtDetail(GamTrainPortRentLstMngtVO vo){
		delete("gamTrainPortRentLstMngtDao.deleteTrainPortRentLstMngtDetail_S", vo);
	}
	
	/**
	 * 철송장 상세를 등록한다.
	 * @param vo GamTrainPortRentLstMngtDetailVO
	 * @exception Exception
	 */
	public void insertTrainPortRentLstMngtDetail(GamTrainPortRentLstMngtDetailVO vo){
		insert("gamTrainPortRentLstMngtDao.insertTrainPortRentLstMngtDetail_S", vo);
	}
	
	/**
	 * 철송장 상세를 수정한다.
	 * @param vo GamTrainPortRentLstMngtDetailVO
	 * @exception Exception
	 */
	public void updateTrainPortRentLstMngtDetail(GamTrainPortRentLstMngtDetailVO vo){
		update("gamTrainPortRentLstMngtDao.updateTrainPortRentLstMngtDetail_S", vo);
	}
	
	/**
	 * 철송장 상세를 삭제한다.
	 * @param vo GamTrainPortRentLstMngtDetailVO
	 * @exception Exception
	 */
	public void deleteTrainPortRentLstMngtDetail2(GamTrainPortRentLstMngtDetailVO vo){
		delete("gamTrainPortRentLstMngtDao.deleteTrainPortRentLstMngtDetail2_S", vo);
	}
	
	/**
	 * 승낙할 철송장 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대정보
	 * @exception Exception
	 */
	public GamTrainPortRentLstMngtVO selectTrainPortRentLstMngtPrmisnInfo(GamTrainPortRentLstMngtVO searchVO) throws Exception {
		return (GamTrainPortRentLstMngtVO) selectByPk("gamTrainPortRentLstMngtDao.selectTrainPortRentLstMngtPrmisnInfo_S", searchVO);
	}
	
	/**
	 * 철송장 허가여부를 수정한다.
	 * @param vo GamPrtFcltyRentVO
	 * @exception Exception
	 */
	public void updateTrainPortRentLstMngtPrmisn(GamTrainPortRentLstMngtLevReqestVO vo){
		update("gamTrainPortRentLstMngtDao.updateTrainPortRentLstMngtPrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamPrtFcltyRentLevReqestVO
	 * @exception Exception
	 */
	public void insertTrainPortRentLstMngtLevReqest(GamTrainPortRentLstMngtLevReqestVO vo){
		insert("gamTrainPortRentLstMngtDao.insertTrainPortRentLstMngtLevReqest_S", vo);
	}
	
	/**
	 * 철송장 허가여부를 취소한다.
	 * @param vo GamPrtFcltyRentVO
	 * @exception Exception
	 */
	public void updateTrainPortRentLstMngtPrmisnCancel(GamTrainPortRentLstMngtLevReqestVO vo){
		update("gamTrainPortRentLstMngtDao.updateTrainPortRentLstMngtPrmisnCancel_S", vo);
	}

	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamTrainPortRentLstMngtLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamTrainPortRentLstMngtDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 철송장상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 철송장상세 목록
	 * @exception Exception
	 */
    public List selectTrainPortRentLstMngtDetailInfo(GamTrainPortRentLstMngtVO vo) throws Exception {
        return list("gamTrainPortRentLstMngtDao.selectTrainPortRentLstMngtDetailInfo_S", vo);
    }
}