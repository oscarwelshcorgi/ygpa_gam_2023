package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrRentFeeMngtVO;

/**
 * @Class Name : GamCmmnCntrRentFeeMngtDao.java
 * @Description : 공컨장치장임대료관리 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCmmnCntrRentFeeMngtDao")
public class GamCmmnCntrRentFeeMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 공컨장치장임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrRentFeeMngtList(GamCmmnCntrRentFeeMngtVO searchVO) throws Exception {
        return list("gamCmmnCntrRentFeeMngtDao.selectCmmnCntrRentFeeMngtList_D", searchVO);
    }

    /**
	 * 공컨장치장임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrRentFeeMngtListTotCnt(GamCmmnCntrRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentFeeMngtDao.selectCmmnCntrRentFeeMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrRentFeeMngtVO selectCmmnCntrRentFeeMngtSum(GamCmmnCntrRentFeeMngtVO searchVO) throws Exception {
		return (GamCmmnCntrRentFeeMngtVO) selectByPk("gamCmmnCntrRentFeeMngtDao.selectCmmnCntrRentFeeMngtSum_S", searchVO);
	}
	
	/**
	 * 공컨장치장임대관리정보를 수정한다.
	 * @param vo GamCmmnCntrRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateCmmnCntrRentFeeMngt(GamCmmnCntrRentFeeMngtVO vo){
		update("gamCmmnCntrRentFeeMngtDao.updateCmmnCntrRentFeeMngt_S", vo);
	}
	
	/**
	 * 공컨장치장임대관리정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대관리정보
	 * @exception Exception
	 */
	public GamCmmnCntrRentFeeMngtVO selectCmmnCntrRentFeeMngtInfo(GamCmmnCntrRentFeeMngtVO searchVO) throws Exception {
		return (GamCmmnCntrRentFeeMngtVO) selectByPk("gamCmmnCntrRentFeeMngtDao.selectCmmnCntrRentFeeMngtInfo_S", searchVO);
	}
	
	
	/**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 갯수
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamCmmnCntrRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrRentFeeMngtDao.selectAnlrveLevCnt_S", searchVO);
    }
	
	/**
	 * 세입징수를 등록한다.
	 * @param vo GamCmmnCntrRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamCmmnCntrRentFeeMngtVO vo){
		insert("gamCmmnCntrRentFeeMngtDao.insertAnlrveLev_S", vo);
	}
	
	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamCmmnCntrRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrRentFeeMngt(GamCmmnCntrRentFeeMngtVO vo){
		delete("gamCmmnCntrRentFeeMngtDao.deleteCmmnCntrRentFeeMngt_S", vo);
	}

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamCmmnCntrRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertCmmnCntrRentFeeMngtLevReqest(GamCmmnCntrRentFeeMngtVO vo){
		insert("gamCmmnCntrRentFeeMngtDao.insertCmmnCntrRentFeeMngtLevReqest_S", vo);
	}
}