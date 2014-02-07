package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrShedRentFeeMngtVO;

/**
 * @Class Name : GamCmmnCntrShedRentFeeMngtDao.java
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
@Repository("gamCmmnCntrShedRentFeeMngtDao")
public class GamCmmnCntrShedRentFeeMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 공컨장치장임대료고지관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대료고지관리 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrShedRentFeeMngtList(GamCmmnCntrShedRentFeeMngtVO searchVO) throws Exception {
        return list("gamCmmnCntrShedRentFeeMngtDao.selectCmmnCntrShedRentFeeMngtList_D", searchVO);
    }

    /**
	 * 공컨장치장임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대료고지관리 목록 총 갯수
	 * @exception
	 */
    public int selectCmmnCntrShedRentFeeMngtListTotCnt(GamCmmnCntrShedRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrShedRentFeeMngtDao.selectCmmnCntrShedRentFeeMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대료고지관리 목록
	 * @exception Exception
	 */
	public GamCmmnCntrShedRentFeeMngtVO selectCmmnCntrShedRentFeeMngtSum(GamCmmnCntrShedRentFeeMngtVO searchVO) throws Exception {
		return (GamCmmnCntrShedRentFeeMngtVO) selectByPk("gamCmmnCntrShedRentFeeMngtDao.selectCmmnCntrShedRentFeeMngtSum_S", searchVO);
	}
	
	/**
	 * 공컨장치장임대료고지관리정보를 수정한다.
	 * @param vo GamCmmnCntrShedRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateCmmnCntrShedRentFeeMngt(GamCmmnCntrShedRentFeeMngtVO vo){
		update("gamCmmnCntrShedRentFeeMngtDao.updateCmmnCntrShedRentFeeMngt_S", vo);
	}
	
	/**
	 * 공컨장치장임대료고지관리정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 공컨장치장임대료고지관리정보
	 * @exception Exception
	 */
	public GamCmmnCntrShedRentFeeMngtVO selectCmmnCntrShedRentFeeMngtInfo(GamCmmnCntrShedRentFeeMngtVO searchVO) throws Exception {
		return (GamCmmnCntrShedRentFeeMngtVO) selectByPk("gamCmmnCntrShedRentFeeMngtDao.selectCmmnCntrShedRentFeeMngtInfo_S", searchVO);
	}
	
	
	/**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 갯수
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamCmmnCntrShedRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrShedRentFeeMngtDao.selectAnlrveLevCnt_S", searchVO);
    }
	
	/**
	 * 세입징수를 등록한다.
	 * @param vo GamCmmnCntrShedRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamCmmnCntrShedRentFeeMngtVO vo){
		insert("gamCmmnCntrShedRentFeeMngtDao.insertAnlrveLev_S", vo);
	}
	
	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamCmmnCntrShedRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteCmmnCntrShedRentFeeMngt(GamCmmnCntrShedRentFeeMngtVO vo){
		delete("gamCmmnCntrShedRentFeeMngtDao.deleteCmmnCntrShedRentFeeMngt_S", vo);
	}

}