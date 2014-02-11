package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentDetailVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentLevReqestVO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentMngtVO;

/**
 * @Class Name : GamMarineCenterRentMngtDao.java
 * @Description : 마린센터임대목록관리 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-02-11
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamMarineCenterRentMngtDao")
public class GamMarineCenterRentMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 마린센터임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
    public List selectMarineCenterRentList(GamMarineCenterRentMngtVO searchVO) throws Exception {
        return list("gamMarineCenterRentMngtDao.selectMarineCenterRentList_D", searchVO);
    }

    /**
	 * 마린센터임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterRentListTotCnt(GamMarineCenterRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentMngtDao.selectMarineCenterRentListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterRentMngtVO selectMarineCenterRentSum(GamMarineCenterRentMngtVO searchVO) throws Exception {
		return (GamMarineCenterRentMngtVO) selectByPk("gamMarineCenterRentMngtDao.selectMarineCenterRentSum_S", searchVO);
	}
    
	/**
	 * 마린센터임대 최초 신청을 등록한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void insertMarineCenterRentFirst(GamMarineCenterRentMngtVO vo){
		insert("gamMarineCenterRentMngtDao.insertMarineCenterRentFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamMarineCenterRentVO
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public GamMarineCenterRentMngtVO selectMarineCenterRentMaxNo(GamMarineCenterRentMngtVO vo) {
        return (GamMarineCenterRentMngtVO)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentMngtDao.selectMarineCenterRentMaxNo_S", vo);
    }
    
    /**
	 * 마린센터임대 연장 신청을 등록한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void insertMarineCenterRentRenew(GamMarineCenterRentMngtVO vo){
		insert("gamMarineCenterRentMngtDao.insertMarineCenterRentRenew_S", vo);
	}
	
	/**
	 * 마린센터임대 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public String selectMarineCenterRentMaxMngCnt(GamMarineCenterRentMngtVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentMngtDao.selectMarineCenterRentMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 마린센터임대 연장 신청시 마린센터임대 상세를 복사하여 등록한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void insertMarineCenterRentDetailRenew(GamMarineCenterRentDetailVO vo){
		insert("gamMarineCenterRentMngtDao.insertMarineCenterRentDetailRenew_S", vo);
	}
	
	/**
	 * 마린센터임대 정보를 수정한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void updateMarineCenterRent(GamMarineCenterRentMngtVO vo){
		update("gamMarineCenterRentMngtDao.updateMarineCenterRent_S", vo);
	}
	
	/**
	 * 마린센터임대관리 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록
	 * @exception Exception
	 */
    public List selectMarineCenterRentDetailList(GamMarineCenterRentMngtVO vo) throws Exception {
        return list("gamMarineCenterRentMngtDao.selectMarineCenterRentDetailList_D", vo);
    }

    /**
	 * 마린센터임대관리 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterRentDetailListTotCnt(GamMarineCenterRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentMngtDao.selectMarineCenterRentDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterRentLevReqestCnt(GamMarineCenterRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentMngtDao.selectMarineCenterRentLevReqestCnt_S", vo);
    }
    
    /**
	 * 마린센터임대 사진정보를 삭제한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void deleteMarineCenterRentPhoto(GamMarineCenterRentMngtVO vo){
		delete("gamMarineCenterRentMngtDao.deleteMarineCenterRentPhoto_S", vo);
	}
    
	/**
	 * 마린센터임대 정보를 삭제한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void deleteMarineCenterRent(GamMarineCenterRentMngtVO vo){
		delete("gamMarineCenterRentMngtDao.deleteMarineCenterRent_S", vo);
	}
    
	/**
	 * 마린센터임대 상세정보를 삭제한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void deleteMarineCenterRentDetail(GamMarineCenterRentMngtVO vo){
		delete("gamMarineCenterRentMngtDao.deleteMarineCenterRentDetail_S", vo);
	}
	
	/**
	 * 마린센터임대 상세를 등록한다.
	 * @param vo GamMarineCenterRentDetailVO
	 * @exception Exception
	 */
	public void insertMarineCenterRentDetail(GamMarineCenterRentDetailVO vo){
		insert("gamMarineCenterRentMngtDao.insertMarineCenterRentDetail_S", vo);
	}
	
	/**
	 * 마린센터임대 상세를 수정한다.
	 * @param vo GamMarineCenterRentDetailVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentDetail(GamMarineCenterRentDetailVO vo){
		update("gamMarineCenterRentMngtDao.updateMarineCenterRentDetail_S", vo);
	}
	
	/**
	 * 마린센터임대 상세를 삭제한다.
	 * @param vo GamMarineCenterRentDetailVO
	 * @exception Exception
	 */
	public void deleteMarineCenterRentDetail2(GamMarineCenterRentDetailVO vo){
		delete("gamMarineCenterRentMngtDao.deleteMarineCenterRentDetail2_S", vo);
	}

	/**
	 * 승낙할 마린센터임대 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대정보
	 * @exception Exception
	 */
	public GamMarineCenterRentMngtVO selectMarineCenterRentPrmisnInfo(GamMarineCenterRentMngtVO searchVO) throws Exception {
		return (GamMarineCenterRentMngtVO) selectByPk("gamMarineCenterRentMngtDao.selectMarineCenterRentPrmisnInfo_S", searchVO);
	}
	
	/**
	 * 마린센터임대 허가여부를 수정한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentPrmisn(GamMarineCenterRentLevReqestVO vo){
		update("gamMarineCenterRentMngtDao.updateMarineCenterRentPrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamMarineCenterRentLevReqestVO
	 * @exception Exception
	 */
	public void insertMarineCenterRentLevReqest(GamMarineCenterRentLevReqestVO vo){
		insert("gamMarineCenterRentMngtDao.insertMarineCenterRentLevReqest_S", vo);
	}
	
	/**
	 * 마린센터임대 허가여부를 취소한다.
	 * @param vo GamMarineCenterRentVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentPrmisnCancel(GamMarineCenterRentLevReqestVO vo){
		update("gamMarineCenterRentMngtDao.updateMarineCenterRentPrmisnCancel_S", vo);
	}
	
	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamMarineCenterRentLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentMngtDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 마린센터임대상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대상세 목록
	 * @exception Exception
	 */
    public List selectMarineCenterRentDetailInfo(GamMarineCenterRentMngtVO vo) throws Exception {
        return list("gamMarineCenterRentMngtDao.selectMarineCenterRentDetailInfo_S", vo);
    }
	
}
