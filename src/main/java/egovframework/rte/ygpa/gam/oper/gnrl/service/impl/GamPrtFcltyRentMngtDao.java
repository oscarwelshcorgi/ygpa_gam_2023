package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentMngtVO;

/**
 * @Class Name : GamPrtFcltyRentMngtDao.java
 * @Description : 항만시설사용목록관리 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamPrtFcltyRentMngtDao")
public class GamPrtFcltyRentMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 항만시설 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyRentMngtList(GamPrtFcltyRentMngtVO searchVO) throws Exception {
        return list("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtList_D", searchVO);
    }

    /**
	 * 항만시설 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyRentMngtListTotCnt(GamPrtFcltyRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtSum(GamPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamPrtFcltyRentMngtVO) selectByPk("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtSum_S", searchVO);
	}
    
	/**
	 * 항만시설 최초 신청을 등록한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtFirst(GamPrtFcltyRentMngtVO vo){
		insert("gamPrtFcltyRentMngtDao.insertPrtFcltyRentMngtFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamPrtFcltyRentMngtVO
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtMaxNo(GamPrtFcltyRentMngtVO vo) {
        return (GamPrtFcltyRentMngtVO)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtMaxNo_S", vo);
    }
    
    /**
	 * 항만시설 연장 신청을 등록한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtRenew(GamPrtFcltyRentMngtVO vo){
		insert("gamPrtFcltyRentMngtDao.insertPrtFcltyRentMngtRenew_S", vo);
	}
	
	/**
	 * 항만시설 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설관리 목록 총 갯수
	 * @exception
	 */
    public String selectPrtFcltyRentMngtMaxMngCnt(GamPrtFcltyRentMngtVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 항만시설 연장 신청시 항만시설 상세를 복사하여 등록한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtDetailRenew(GamPrtFcltyRentMngtDetailVO vo){
		insert("gamPrtFcltyRentMngtDao.insertPrtFcltyRentMngtDetailRenew_S", vo);
	}
	
	/**
	 * 항만시설 정보를 수정한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngt(GamPrtFcltyRentMngtVO vo){
		update("gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngt_S", vo);
	}
	
	/**
	 * 항만시설 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyRentMngtDetailList(GamPrtFcltyRentMngtVO vo) throws Exception {
        return list("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtDetailList_D", vo);
    }

    /**
	 * 항만시설 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtFcltyRentMngtDetailListTotCnt(GamPrtFcltyRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 
	 * @exception
	 */
    public int selectPrtFcltyRentMngtLevReqestCnt(GamPrtFcltyRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtLevReqestCnt_S", vo);
    }
    
    /**
	 * 항만시설 사진정보를 삭제한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngtPhoto(GamPrtFcltyRentMngtVO vo){
		delete("gamPrtFcltyRentMngtDao.deletePrtFcltyRentMngtPhoto_S", vo);
	}
    
	/**
	 * 항만시설 정보를 삭제한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngt(GamPrtFcltyRentMngtVO vo){
		delete("gamPrtFcltyRentMngtDao.deletePrtFcltyRentMngt_S", vo);
	}
    
	/**
	 * 항만시설 상세정보를 삭제한다.
	 * @param vo GamPrtFcltyRentMngtVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngtDetail(GamPrtFcltyRentMngtVO vo){
		delete("gamPrtFcltyRentMngtDao.deletePrtFcltyRentMngtDetail_S", vo);
	}
	
	/**
	 * 항만시설 상세를 등록한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtDetail(GamPrtFcltyRentMngtDetailVO vo){
		insert("gamPrtFcltyRentMngtDao.insertPrtFcltyRentMngtDetail_S", vo);
	}
	
	/**
	 * 항만시설 상세를 수정한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtDetail(GamPrtFcltyRentMngtDetailVO vo){
		update("gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngtDetail_S", vo);
	}
	
	/**
	 * 항만시설 상세를 삭제한다.
	 * @param vo GamPrtFcltyRentMngtDetailVO
	 * @exception Exception
	 */
	public void deletePrtFcltyRentMngtDetail2(GamPrtFcltyRentMngtDetailVO vo){
		delete("gamPrtFcltyRentMngtDao.deletePrtFcltyRentMngtDetail2_S", vo);
	}
	
	/**
	 * 승낙할 항만시설 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대정보
	 * @exception Exception
	 */
	public GamPrtFcltyRentMngtVO selectPrtFcltyRentMngtPrmisnInfo(GamPrtFcltyRentMngtVO searchVO) throws Exception {
		return (GamPrtFcltyRentMngtVO) selectByPk("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtPrmisnInfo_S", searchVO);
	}
	
	/**
	 * 항만시설 허가여부를 수정한다.
	 * @param vo GamPrtFcltyRentVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtPrmisn(GamPrtFcltyRentMngtLevReqestVO vo){
		update("gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngtPrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamPrtFcltyRentLevReqestVO
	 * @exception Exception
	 */
	public void insertPrtFcltyRentMngtLevReqest(GamPrtFcltyRentMngtLevReqestVO vo){
		insert("gamPrtFcltyRentMngtDao.insertPrtFcltyRentMngtLevReqest_S", vo);
	}
	
	/**
	 * 항만시설 허가여부를 취소한다.
	 * @param vo GamPrtFcltyRentVO
	 * @exception Exception
	 */
	public void updatePrtFcltyRentMngtPrmisnCancel(GamPrtFcltyRentMngtLevReqestVO vo){
		update("gamPrtFcltyRentMngtDao.updatePrtFcltyRentMngtPrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamPrtFcltyRentMngtLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentMngtDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 항만시설상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대상세 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyRentMngtDetailInfo(GamPrtFcltyRentMngtVO vo) throws Exception {
        return list("gamPrtFcltyRentMngtDao.selectPrtFcltyRentMngtDetailInfo_S", vo);
    }
}