package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtOperRentMngtDetailVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtOperRentMngtVO;

/**
 * @Class Name : GamPrtOperRentMngtDao.java
 * @Description : 항만시설사용목록관리 DAO Class
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamPrtOperRentMngtDao")
public class GamPrtOperRentMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 항만시설 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectPrtOperRentMngtList(GamPrtOperRentMngtVO searchVO) throws Exception {
        return list("gamPrtOperRentMngtDao.selectPrtOperRentMngtList_D", searchVO);
    }

    /**
	 * 항만시설 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtOperRentMngtListTotCnt(GamPrtOperRentMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtOperRentMngtDao.selectPrtOperRentMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
	public GamPrtOperRentMngtVO selectPrtOperRentMngtSum(GamPrtOperRentMngtVO searchVO) throws Exception {
		return (GamPrtOperRentMngtVO) selectByPk("gamPrtOperRentMngtDao.selectPrtOperRentMngtSum_S", searchVO);
	}
    
	/**
	 * 항만시설 최초 신청을 등록한다.
	 * @param vo GamPrtOperRentMngtVO
	 * @exception Exception
	 */
	public void insertPrtOperRentMngtFirst(GamPrtOperRentMngtVO vo){
		insert("gamPrtOperRentMngtDao.insertPrtOperRentMngtFirst_S", vo);
	}
    
	
	/**
	 * 해당건에 대한 (MAX)관리번호
	 * @param searchMap - GamPrtOperRentMngtVO
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public GamPrtOperRentMngtVO selectPrtOperRentMngtMaxNo(GamPrtOperRentMngtVO vo) {
        return (GamPrtOperRentMngtVO)getSqlMapClientTemplate().queryForObject("gamPrtOperRentMngtDao.selectPrtOperRentMngtMaxNo_S", vo);
    }
    
    /**
	 * 항만시설 연장 신청을 등록한다.
	 * @param vo GamPrtOperRentMngtVO
	 * @exception Exception
	 */
	public void insertPrtOperRentMngtRenew(GamPrtOperRentMngtVO vo){
		insert("gamPrtOperRentMngtDao.insertPrtOperRentMngtRenew_S", vo);
	}
	
	/**
	 * 항만시설 정보를 수정한다.
	 * @param vo GamPrtOperRentMngtVO
	 * @exception Exception
	 */
	public void updatePrtOperRentMngt(GamPrtOperRentMngtVO vo){
		update("gamPrtOperRentMngtDao.updatePrtOperRentMngt_S", vo);
	}
	
	/**
	 * 항만시설 상세목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록
	 * @exception Exception
	 */
    public List selectPrtOperRentMngtDetailList(GamPrtOperRentMngtVO vo) throws Exception {
        return list("gamPrtOperRentMngtDao.selectPrtOperRentMngtDetailList_D", vo);
    }

    /**
	 * 항만시설 상세목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectPrtOperRentMngtDetailListTotCnt(GamPrtOperRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtOperRentMngtDao.selectPrtOperRentMngtDetailListTotCnt_S", vo);
    }
	
    /**
	 * 징수의뢰 해당 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 
	 * @exception
	 */
    public int selectPrtOperRentMngtLevReqestCnt(GamPrtOperRentMngtVO vo) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtOperRentMngtDao.selectPrtOperRentMngtLevReqestCnt_S", vo);
    }
    
    /**
	 * 항만시설 사진정보를 삭제한다.
	 * @param vo GamPrtOperRentMngtVO
	 * @exception Exception
	 */
	public void deletePrtOperRentMngtPhoto(GamPrtOperRentMngtVO vo){
		delete("gamPrtOperRentMngtDao.deletePrtOperRentMngtPhoto_S", vo);
	}
    
	/**
	 * 항만시설 정보를 삭제한다.
	 * @param vo GamPrtOperRentMngtVO
	 * @exception Exception
	 */
	public void deletePrtOperRentMngt(GamPrtOperRentMngtVO vo){
		delete("gamPrtOperRentMngtDao.deletePrtOperRentMngt_S", vo);
	}
    
	/**
	 * 항만시설 상세정보를 삭제한다.
	 * @param vo GamPrtOperRentMngtVO
	 * @exception Exception
	 */
	public void deletePrtOperRentMngtDetail(GamPrtOperRentMngtVO vo){
		delete("gamPrtOperRentMngtDao.deletePrtOperRentMngtDetail_S", vo);
	}
	
	/**
	 * 항만시설 상세를 등록한다.
	 * @param vo GamPrtOperRentMngtDetailVO
	 * @exception Exception
	 */
	public void insertPrtOperRentMngtDetail(GamPrtOperRentMngtDetailVO vo){
		insert("gamPrtOperRentMngtDao.insertPrtOperRentMngtDetail_S", vo);
	}
	
	/**
	 * 항만시설 상세를 수정한다.
	 * @param vo GamPrtOperRentMngtDetailVO
	 * @exception Exception
	 */
	public void updatePrtOperRentMngtDetail(GamPrtOperRentMngtDetailVO vo){
		update("gamPrtOperRentMngtDao.updatePrtOperRentMngtDetail_S", vo);
	}
	
	/**
	 * 항만시설 상세를 삭제한다.
	 * @param vo GamPrtOperRentMngtDetailVO
	 * @exception Exception
	 */
	public void deletePrtOperRentMngtDetail2(GamPrtOperRentMngtDetailVO vo){
		delete("gamPrtOperRentMngtDao.deletePrtOperRentMngtDetail2_S", vo);
	}

}