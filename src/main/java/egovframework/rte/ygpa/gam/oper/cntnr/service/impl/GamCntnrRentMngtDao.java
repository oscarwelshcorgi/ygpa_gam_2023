package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrRentMngtVO;

/**
 * @Class Name : GamCntnrRentMngtDao.java
 * @Description : 컨테이너부두임대목록관리 (항만시설/컨테이너부두/컨테이너부두임대목록관리)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCntnrRentMngtDao")
public class GamCntnrRentMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 항만시설 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectCntnrRentMngtList(GamCntnrRentMngtVO searchVO) throws Exception {
        return list("gamCntnrRentMngtDao.selectCntnrRentMngtList_D", searchVO);
    }

    /**
	 * 항만시설 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrRentMngtListTotCnt(GamCntnrRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrRentMngtDao.selectCntnrRentMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamCntnrRentMngtVO selectCntnrRentMngtSum(GamCntnrRentMngtVO searchVO) throws Exception {
		return (GamCntnrRentMngtVO) selectByPk("gamCntnrRentMngtDao.selectCntnrRentMngtSum_S", searchVO);
	}
    
	/**
	 * 항만시설 최초 신청을 등록한다.
	 * @param vo GamCntnrRentMngtVO
	 * @exception Exception
	 */
	public void insertCntnrRentMngtFirst(GamCntnrRentMngtVO vo){
		insert("gamCntnrRentMngtDao.insertCntnrRentMngtFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamCntnrRentMngtVO
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public GamCntnrRentMngtVO selectCntnrRentMngtMaxNo(GamCntnrRentMngtVO vo) {
        return (GamCntnrRentMngtVO)getSqlMapClientTemplate().queryForObject("gamCntnrRentMngtDao.selectCntnrRentMngtMaxNo_S", vo);
    }
    
    /**
	 * 항만시설 연장 신청을 등록한다.
	 * @param vo GamCntnrRentMngtVO
	 * @exception Exception
	 */
	public void insertCntnrRentMngtRenew(GamCntnrRentMngtVO vo){
		insert("gamCntnrRentMngtDao.insertCntnrRentMngtRenew_S", vo);
	}
	
	/**
	 * 항만시설 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 항만시설관리 목록 총 갯수
	 * @exception
	 */
    public String selectCntnrRentMngtMaxMngCnt(GamCntnrRentMngtVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamCntnrRentMngtDao.selectCntnrRentMngtMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 항만시설 연장 신청시 항만시설 상세를 복사하여 등록한다.
	 * @param vo GamCntnrRentMngtVO
	 * @exception Exception
	 */
	public void insertCntnrRentMngtDetailRenew(GamCntnrRentMngtDetailVO vo){
		insert("gamCntnrRentMngtDao.insertCntnrRentMngtDetailRenew_S", vo);
	}
	
	/**
	 * 항만시설 정보를 수정한다.
	 * @param vo GamCntnrRentMngtVO
	 * @exception Exception
	 */
	public void updateCntnrRentMngt(GamCntnrRentMngtVO vo){
		update("gamCntnrRentMngtDao.updateCntnrRentMngt_S", vo);
	}
	
	/**
	 * 항만시설 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectCntnrRentMngtDetailList(GamCntnrRentMngtVO vo) throws Exception {
        return list("gamCntnrRentMngtDao.selectCntnrRentMngtDetailList_D", vo);
    }

    /**
	 * 항만시설 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrRentMngtDetailListTotCnt(GamCntnrRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrRentMngtDao.selectCntnrRentMngtDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 
	 * @exception
	 */
    public int selectCntnrRentMngtLevReqestCnt(GamCntnrRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrRentMngtDao.selectCntnrRentMngtLevReqestCnt_S", vo);
    }
    
    /**
	 * 항만시설 사진정보를 삭제한다.
	 * @param vo GamCntnrRentMngtVO
	 * @exception Exception
	 */
	public void deleteCntnrRentMngtPhoto(GamCntnrRentMngtVO vo){
		delete("gamCntnrRentMngtDao.deleteCntnrRentMngtPhoto_S", vo);
	}
    
	/**
	 * 항만시설 정보를 삭제한다.
	 * @param vo GamCntnrRentMngtVO
	 * @exception Exception
	 */
	public void deleteCntnrRentMngt(GamCntnrRentMngtVO vo){
		delete("gamCntnrRentMngtDao.deleteCntnrRentMngt_S", vo);
	}
    
	/**
	 * 항만시설 상세정보를 삭제한다.
	 * @param vo GamCntnrRentMngtVO
	 * @exception Exception
	 */
	public void deleteCntnrRentMngtDetail(GamCntnrRentMngtVO vo){
		delete("gamCntnrRentMngtDao.deleteCntnrRentMngtDetail_S", vo);
	}
	
	/**
	 * 항만시설 상세를 등록한다.
	 * @param vo GamCntnrRentMngtDetailVO
	 * @exception Exception
	 */
	public void insertCntnrRentMngtDetail(GamCntnrRentMngtDetailVO vo){
		insert("gamCntnrRentMngtDao.insertCntnrRentMngtDetail_S", vo);
	}
	
	/**
	 * 항만시설 상세를 수정한다.
	 * @param vo GamCntnrRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateCntnrRentMngtDetail(GamCntnrRentMngtDetailVO vo){
		update("gamCntnrRentMngtDao.updateCntnrRentMngtDetail_S", vo);
	}
	
	/**
	 * 항만시설 상세를 삭제한다.
	 * @param vo GamCntnrRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteCntnrRentMngtDetail2(GamCntnrRentMngtDetailVO vo){
		delete("gamCntnrRentMngtDao.deleteCntnrRentMngtDetail2_S", vo);
	}
	
	/**
	 * 승낙할 항만시설 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대정보
	 * @exception Exception
	 */
	public GamCntnrRentMngtVO selectCntnrRentMngtPrmisnInfo(GamCntnrRentMngtVO searchVO) throws Exception {
		return (GamCntnrRentMngtVO) selectByPk("gamCntnrRentMngtDao.selectCntnrRentMngtPrmisnInfo_S", searchVO);
	}
	
	/**
	 * 항만시설 허가여부를 수정한다.
	 * @param vo GamPrtFcltyRentVO
	 * @exception Exception
	 */
	public void updateCntnrRentMngtPrmisn(GamCntnrRentMngtLevReqestVO vo){
		update("gamCntnrRentMngtDao.updateCntnrRentMngtPrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamPrtFcltyRentLevReqestVO
	 * @exception Exception
	 */
	public void insertCntnrRentMngtLevReqest(GamCntnrRentMngtLevReqestVO vo){
		insert("gamCntnrRentMngtDao.insertCntnrRentMngtLevReqest_S", vo);
	}
	
	/**
	 * 항만시설 허가여부를 취소한다.
	 * @param vo GamPrtFcltyRentVO
	 * @exception Exception
	 */
	public void updateCntnrRentMngtPrmisnCancel(GamCntnrRentMngtLevReqestVO vo){
		update("gamCntnrRentMngtDao.updateCntnrRentMngtPrmisnCancel_S", vo);
	}

	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamCntnrRentMngtLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrRentMngtDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 항만시설상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대상세 목록
	 * @exception Exception
	 */
    public List selectCntnrRentMngtDetailInfo(GamCntnrRentMngtVO vo) throws Exception {
        return list("gamCntnrRentMngtDao.selectCntnrRentMngtDetailInfo_S", vo);
    }
}