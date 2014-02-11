package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentFeeMngtVO;

/**
 * @Class Name : GamMarineCenterRentFeeMngtDao.java
 * @Description : 마린센터임대료관리 DAO Class
 * @Modification Information
 *
 * @author heroin
 * @since 2014-02-11
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamMarineCenterRentFeeMngtDao")
public class GamMarineCenterRentFeeMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 마린센터임대료관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대료관리 목록
	 * @exception Exception
	 */
    public List selectMarineCenterRentFeeList(GamMarineCenterRentFeeMngtVO searchVO) throws Exception {
        return list("gamMarineCenterRentFeeMngtDao.selectMarineCenterRentFeeList_D", searchVO);
    }

    /**
	 * 마린센터임대료관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대료관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterRentFeeListTotCnt(GamMarineCenterRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentFeeMngtDao.selectMarineCenterRentFeeListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대료관리 목록
	 * @exception Exception
	 */
	public GamMarineCenterRentFeeMngtVO selectMarineCenterRentFeeSum(GamMarineCenterRentFeeMngtVO searchVO) throws Exception {
		return (GamMarineCenterRentFeeMngtVO) selectByPk("gamMarineCenterRentFeeMngtDao.selectMarineCenterRentFeeSum_S", searchVO);
	}
	
	/**
	 * 마린센터임대료관리정보를 수정한다.
	 * @param vo GamMarineCenterRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateMarineCenterRentFee(GamMarineCenterRentFeeMngtVO vo){
		update("gamMarineCenterRentFeeMngtDao.updateMarineCenterRentFee_S", vo);
	}
	
	/**
	 * 마린센터임대료관리정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 마린센터임대료관리정보
	 * @exception Exception
	 */
	public GamMarineCenterRentFeeMngtVO selectMarineCenterRentFeeInfo(GamMarineCenterRentFeeMngtVO searchVO) throws Exception {
		return (GamMarineCenterRentFeeMngtVO) selectByPk("gamMarineCenterRentFeeMngtDao.selectMarineCenterRentFeeInfo_S", searchVO);
	}
	
	
	/**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 갯수
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamMarineCenterRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentFeeMngtDao.selectAnlrveLevCnt_S", searchVO);
    }
	
	/**
	 * 세입징수를 등록한다.
	 * @param vo GamMarineCenterRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamMarineCenterRentFeeMngtVO vo){
		insert("gamMarineCenterRentFeeMngtDao.insertAnlrveLev_S", vo);
	}
	
	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamMarineCenterRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteMarineCenterRentFee(GamMarineCenterRentFeeMngtVO vo){
		delete("gamMarineCenterRentFeeMngtDao.deleteMarineCenterRentFee_S", vo);
	}

}
