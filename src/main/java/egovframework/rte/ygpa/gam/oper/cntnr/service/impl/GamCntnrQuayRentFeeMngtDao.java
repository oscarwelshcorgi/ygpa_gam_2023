package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrQuayRentFeeMngtVO;

/**
 * @Class Name : GamCntnrQuayRentFeeMngtDao.java
 * @Description : 컨테이너부두임대료관리 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCntnrQuayRentFeeMngtDao")
public class GamCntnrQuayRentFeeMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 컨테이너부두임대관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
    public List selectCntnrQuayRentFeeMngtList(GamCntnrQuayRentFeeMngtVO searchVO) throws Exception {
        return list("gamCntnrQuayRentFeeMngtDao.selectCntnrQuayRentFeeMngtList_D", searchVO);
    }

    /**
	 * 컨테이너부두임대관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrQuayRentFeeMngtListTotCnt(GamCntnrQuayRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayRentFeeMngtDao.selectCntnrQuayRentFeeMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리 목록
	 * @exception Exception
	 */
	public GamCntnrQuayRentFeeMngtVO selectCntnrQuayRentFeeMngtSum(GamCntnrQuayRentFeeMngtVO searchVO) throws Exception {
		return (GamCntnrQuayRentFeeMngtVO) selectByPk("gamCntnrQuayRentFeeMngtDao.selectCntnrQuayRentFeeMngtSum_S", searchVO);
	}
	
	/**
	 * 컨테이너부두임대관리정보를 수정한다.
	 * @param vo GamCntnrQuayRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateCntnrQuayRentFeeMngt(GamCntnrQuayRentFeeMngtVO vo){
		update("gamCntnrQuayRentFeeMngtDao.updateCntnrQuayRentFeeMngt_S", vo);
	}
	
	/**
	 * 컨테이너부두임대관리정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대관리정보
	 * @exception Exception
	 */
	public GamCntnrQuayRentFeeMngtVO selectCntnrQuayRentFeeMngtInfo(GamCntnrQuayRentFeeMngtVO searchVO) throws Exception {
		return (GamCntnrQuayRentFeeMngtVO) selectByPk("gamCntnrQuayRentFeeMngtDao.selectCntnrQuayRentFeeMngtInfo_S", searchVO);
	}
	
	
	/**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 갯수
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamCntnrQuayRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrQuayRentFeeMngtDao.selectAnlrveLevCnt_S", searchVO);
    }
	
	/**
	 * 세입징수를 등록한다.
	 * @param vo GamCntnrQuayRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamCntnrQuayRentFeeMngtVO vo){
		insert("gamCntnrQuayRentFeeMngtDao.insertAnlrveLev_S", vo);
	}
	
	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamCntnrQuayRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteCntnrQuayRentFeeMngt(GamCntnrQuayRentFeeMngtVO vo){
		delete("gamCntnrQuayRentFeeMngtDao.deleteCntnrQuayRentFeeMngt_S", vo);
	}

	/**
	 * 징수의뢰를 등록한다.
	 * @param vo GamCntnrQuayRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertCntnrQuayRentFeeMngtLevReqest(GamCntnrQuayRentFeeMngtVO vo){
		insert("gamCntnrQuayRentFeeMngtDao.insertCntnrQuayRentFeeMngtLevReqest_S", vo);
	}
}