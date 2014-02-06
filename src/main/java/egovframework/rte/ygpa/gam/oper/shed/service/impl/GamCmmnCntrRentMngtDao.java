package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentMngtVO;

/**
 * @Class Name : GamCmmnCntrRentMngtDao.java
 * @Description : 공컨장치장임대목록관리 (공컨장치장/공컨장치장/공컨장치장임대목록관리)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCmmnCntrRentMngtDao")
public class GamCmmnCntrRentMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 공컨장치장 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrRentMngtList(GamCmmnCntrRentMngtVO searchVO) throws Exception {
        return list("gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtList_D", searchVO);
    }

    /**
	 * 공컨장치장 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrRentMngtListTotCnt(GamCmmnCntrRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtSum(GamCmmnCntrRentMngtVO searchVO) throws Exception {
		return (GamCmmnCntrRentMngtVO) selectByPk("gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtSum_S", searchVO);
	}
    
	/**
	 * 공컨장치장 최초 신청을 등록한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	public void insertCmmnCntrRentMngtFirst(GamCmmnCntrRentMngtVO vo){
		insert("gamCmmnCntrRentMngtDao.insertCmmnCntrRentMngtFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamCmmnCntrRentMngtVO
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtMaxNo(GamCmmnCntrRentMngtVO vo) {
        return (GamCmmnCntrRentMngtVO)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtMaxNo_S", vo);
    }
    
    /**
	 * 공컨장치장 연장 신청을 등록한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	public void insertCmmnCntrRentMngtRenew(GamCmmnCntrRentMngtVO vo){
		insert("gamCmmnCntrRentMngtDao.insertCmmnCntrRentMngtRenew_S", vo);
	}
	
	/**
	 * 공컨장치장 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장관리 목록 총 갯수
	 * @exception
	 */
    public String selectCmmnCntrRentMngtMaxMngCnt(GamCmmnCntrRentMngtVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 공컨장치장 연장 신청시 공컨장치장 상세를 복사하여 등록한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	public void insertCmmnCntrRentMngtDetailRenew(GamCmmnCntrRentMngtDetailVO vo){
		insert("gamCmmnCntrRentMngtDao.insertCmmnCntrRentMngtDetailRenew_S", vo);
	}
	
	/**
	 * 공컨장치장 정보를 수정한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentMngt(GamCmmnCntrRentMngtVO vo){
		update("gamCmmnCntrRentMngtDao.updateCmmnCntrRentMngt_S", vo);
	}
	
	/**
	 * 공컨장치장 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrRentMngtDetailList(GamCmmnCntrRentMngtVO vo) throws Exception {
        return list("gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtDetailList_D", vo);
    }

    /**
	 * 공컨장치장 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrRentMngtDetailListTotCnt(GamCmmnCntrRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 
	 * @exception
	 */
    public int selectCmmnCntrRentMngtLevReqestCnt(GamCmmnCntrRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtLevReqestCnt_S", vo);
    }
    
    /**
	 * 공컨장치장 사진정보를 삭제한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrRentMngtPhoto(GamCmmnCntrRentMngtVO vo){
		delete("gamCmmnCntrRentMngtDao.deleteCmmnCntrRentMngtPhoto_S", vo);
	}
    
	/**
	 * 공컨장치장 정보를 삭제한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrRentMngt(GamCmmnCntrRentMngtVO vo){
		delete("gamCmmnCntrRentMngtDao.deleteCmmnCntrRentMngt_S", vo);
	}
    
	/**
	 * 공컨장치장 상세정보를 삭제한다.
	 * @param vo GamCmmnCntrRentMngtVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrRentMngtDetail(GamCmmnCntrRentMngtVO vo){
		delete("gamCmmnCntrRentMngtDao.deleteCmmnCntrRentMngtDetail_S", vo);
	}
	
	/**
	 * 공컨장치장 상세를 등록한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	public void insertCmmnCntrRentMngtDetail(GamCmmnCntrRentMngtDetailVO vo){
		insert("gamCmmnCntrRentMngtDao.insertCmmnCntrRentMngtDetail_S", vo);
	}
	
	/**
	 * 공컨장치장 상세를 수정한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentMngtDetail(GamCmmnCntrRentMngtDetailVO vo){
		update("gamCmmnCntrRentMngtDao.updateCmmnCntrRentMngtDetail_S", vo);
	}
	
	/**
	 * 공컨장치장 상세를 삭제한다.
	 * @param vo GamCmmnCntrRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrRentMngtDetail2(GamCmmnCntrRentMngtDetailVO vo){
		delete("gamCmmnCntrRentMngtDao.deleteCmmnCntrRentMngtDetail2_S", vo);
	}
	
	/**
	 * 승낙할 공컨장치장 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대정보
	 * @exception Exception
	 */
	public GamCmmnCntrRentMngtVO selectCmmnCntrRentMngtPrmisnInfo(GamCmmnCntrRentMngtVO searchVO) throws Exception {
		return (GamCmmnCntrRentMngtVO) selectByPk("gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtPrmisnInfo_S", searchVO);
	}
	
	/**
	 * 공컨장치장 허가여부를 수정한다.
	 * @param vo GamPrtFcltyRentVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentMngtPrmisn(GamCmmnCntrRentMngtLevReqestVO vo){
		update("gamCmmnCntrRentMngtDao.updateCmmnCntrRentMngtPrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamPrtFcltyRentLevReqestVO
	 * @exception Exception
	 */
	public void insertCmmnCntrRentMngtLevReqest(GamCmmnCntrRentMngtLevReqestVO vo){
		insert("gamCmmnCntrRentMngtDao.insertCmmnCntrRentMngtLevReqest_S", vo);
	}
	
	/**
	 * 공컨장치장 허가여부를 취소한다.
	 * @param vo GamPrtFcltyRentVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentMngtPrmisnCancel(GamCmmnCntrRentMngtLevReqestVO vo){
		update("gamCmmnCntrRentMngtDao.updateCmmnCntrRentMngtPrmisnCancel_S", vo);
	}

	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamCmmnCntrRentMngtLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentMngtDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 공컨장치장상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장상세 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrRentMngtDetailInfo(GamCmmnCntrRentMngtVO vo) throws Exception {
        return list("gamCmmnCntrRentMngtDao.selectCmmnCntrRentMngtDetailInfo_S", vo);
    }
}