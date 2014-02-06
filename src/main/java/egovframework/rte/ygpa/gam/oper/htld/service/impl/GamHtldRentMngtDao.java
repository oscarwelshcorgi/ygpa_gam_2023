package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtLevReqestVO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentMngtVO;

/**
 * @Class Name : GamHtldRentMngtDao.java
 * @Description : 배후단지임대목록관리 (배후단지/배후단지/배후단지임대목록관리)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamHtldRentMngtDao")
public class GamHtldRentMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 배후단지 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectHtldRentMngtList(GamHtldRentMngtVO searchVO) throws Exception {
        return list("gamHtldRentMngtDao.selectHtldRentMngtList_D", searchVO);
    }

    /**
	 * 배후단지 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldRentMngtListTotCnt(GamHtldRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentMngtDao.selectHtldRentMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamHtldRentMngtVO selectHtldRentMngtSum(GamHtldRentMngtVO searchVO) throws Exception {
		return (GamHtldRentMngtVO) selectByPk("gamHtldRentMngtDao.selectHtldRentMngtSum_S", searchVO);
	}
    
	/**
	 * 배후단지 최초 신청을 등록한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void insertHtldRentMngtFirst(GamHtldRentMngtVO vo){
		insert("gamHtldRentMngtDao.insertHtldRentMngtFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamHtldRentMngtVO
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public GamHtldRentMngtVO selectHtldRentMngtMaxNo(GamHtldRentMngtVO vo) {
        return (GamHtldRentMngtVO)getSqlMapClientTemplate().queryForObject("gamHtldRentMngtDao.selectHtldRentMngtMaxNo_S", vo);
    }
    
    /**
	 * 배후단지 연장 신청을 등록한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void insertHtldRentMngtRenew(GamHtldRentMngtVO vo){
		insert("gamHtldRentMngtDao.insertHtldRentMngtRenew_S", vo);
	}
	
	/**
	 * 배후단지 연장 신청된 MaxMngCnt 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지관리 목록 총 갯수
	 * @exception
	 */
    public String selectHtldRentMngtMaxMngCnt(GamHtldRentMngtVO searchVO) {
        return (String)getSqlMapClientTemplate().queryForObject("gamHtldRentMngtDao.selectHtldRentMngtMaxMngCnt_S", searchVO);
    }
	
	/**
	 * 배후단지연장 신청시 배후단지 상세를 복사하여 등록한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void insertHtldRentMngtDetailRenew(GamHtldRentMngtDetailVO vo){
		insert("gamHtldRentMngtDao.insertHtldRentMngtDetailRenew_S", vo);
	}
	
	/**
	 * 배후단지 정보를 수정한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void updateHtldRentMngt(GamHtldRentMngtVO vo){
		update("gamHtldRentMngtDao.updateHtldRentMngt_S", vo);
	}
	
	/**
	 * 배후단지 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectHtldRentMngtDetailList(GamHtldRentMngtVO vo) throws Exception {
        return list("gamHtldRentMngtDao.selectHtldRentMngtDetailList_D", vo);
    }

    /**
	 * 배후단지 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldRentMngtDetailListTotCnt(GamHtldRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentMngtDao.selectHtldRentMngtDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 
	 * @exception
	 */
    public int selectHtldRentMngtLevReqestCnt(GamHtldRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentMngtDao.selectHtldRentMngtLevReqestCnt_S", vo);
    }
    
    /**
	 * 배후단지 사진정보를 삭제한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void deleteHtldRentMngtPhoto(GamHtldRentMngtVO vo){
		delete("gamHtldRentMngtDao.deleteHtldRentMngtPhoto_S", vo);
	}
    
	/**
	 * 배후단지 정보를 삭제한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void deleteHtldRentMngt(GamHtldRentMngtVO vo){
		delete("gamHtldRentMngtDao.deleteHtldRentMngt_S", vo);
	}
    
	/**
	 * 배후단지 상세정보를 삭제한다.
	 * @param vo GamHtldRentMngtVO
	 * @exception Exception
	 */
	public void deleteHtldRentMngtDetail(GamHtldRentMngtVO vo){
		delete("gamHtldRentMngtDao.deleteHtldRentMngtDetail_S", vo);
	}
	
	/**
	 * 배후단지 상세를 등록한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	public void insertHtldRentMngtDetail(GamHtldRentMngtDetailVO vo){
		insert("gamHtldRentMngtDao.insertHtldRentMngtDetail_S", vo);
	}
	
	/**
	 * 배후단지 상세를 수정한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	public void updateHtldRentMngtDetail(GamHtldRentMngtDetailVO vo){
		update("gamHtldRentMngtDao.updateHtldRentMngtDetail_S", vo);
	}
	
	/**
	 * 배후단지 상세를 삭제한다.
	 * @param vo GamHtldRentMngtDetailVO
	 * @exception Exception
	 */
	public void deleteHtldRentMngtDetail2(GamHtldRentMngtDetailVO vo){
		delete("gamHtldRentMngtDao.deleteHtldRentMngtDetail2_S", vo);
	}
	
	/**
	 * 승낙할 배후단지 정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대정보
	 * @exception Exception
	 */
	public GamHtldRentMngtVO selectHtldRentMngtPrmisnInfo(GamHtldRentMngtVO searchVO) throws Exception {
		return (GamHtldRentMngtVO) selectByPk("gamHtldRentMngtDao.selectHtldRentMngtPrmisnInfo_S", searchVO);
	}
	
	/**
	 * 배후단지 허가여부를 수정한다.
	 * @param vo GamPrtFcltyRentVO
	 * @exception Exception
	 */
	public void updateHtldRentMngtPrmisn(GamHtldRentMngtLevReqestVO vo){
		update("gamHtldRentMngtDao.updateHtldRentMngtPrmisn_S", vo);
	}
	
	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamPrtFcltyRentLevReqestVO
	 * @exception Exception
	 */
	public void insertHtldRentMngtLevReqest(GamHtldRentMngtLevReqestVO vo){
		insert("gamHtldRentMngtDao.insertHtldRentMngtLevReqest_S", vo);
	}
	
	/**
	 * 배후단지 허가여부를 취소한다.
	 * @param vo GamPrtFcltyRentVO
	 * @exception Exception
	 */
	public void updateHtldRentMngtPrmisnCancel(GamHtldRentMngtLevReqestVO vo){
		update("gamHtldRentMngtDao.updateHtldRentMngtPrmisnCancel_S", vo);
	}

	/**
	 * 해당기간의 월 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 해당기간의 월 갯수
	 * @exception
	 */
    public int selectUsagePdMonthCnt(GamHtldRentMngtLevReqestVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentMngtDao.selectUsagePdMonthCnt_S", vo);
    }
    
	/**
	 * 배후단지상세 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지상세 목록
	 * @exception Exception
	 */
    public List selectHtldRentMngtDetailInfo(GamHtldRentMngtVO vo) throws Exception {
        return list("gamHtldRentMngtDao.selectHtldRentMngtDetailInfo_S", vo);
    }
}