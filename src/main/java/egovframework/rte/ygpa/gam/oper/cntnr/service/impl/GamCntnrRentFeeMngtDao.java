package egovframework.rte.ygpa.gam.oper.cntnr.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.GamCntnrRentFeeMngtVO;

/**
 * @Class Name : GamCntnrRentFeeMngtDao.java
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
@Repository("gamCntnrRentFeeMngtDao")
public class GamCntnrRentFeeMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 컨테이너부두임대료고지관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대료고지관리 목록
	 * @exception Exception
	 */
    public List selectCntnrRentFeeMngtList(GamCntnrRentFeeMngtVO searchVO) throws Exception {
        return list("gamCntnrRentFeeMngtDao.selectCntnrRentFeeMngtList_D", searchVO);
    }

    /**
	 * 컨테이너부두임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대료고지관리 목록 총 갯수
	 * @exception
	 */
    public int selectCntnrRentFeeMngtListTotCnt(GamCntnrRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrRentFeeMngtDao.selectCntnrRentFeeMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대료고지관리 목록
	 * @exception Exception
	 */
	public GamCntnrRentFeeMngtVO selectCntnrRentFeeMngtSum(GamCntnrRentFeeMngtVO searchVO) throws Exception {
		return (GamCntnrRentFeeMngtVO) selectByPk("gamCntnrRentFeeMngtDao.selectCntnrRentFeeMngtSum_S", searchVO);
	}
	
	/**
	 * 컨테이너부두임대료고지관리정보를 수정한다.
	 * @param vo GamCntnrRentFeeMngtVO
	 * @exception Exception
	 */
	public void updateCntnrRentFeeMngt(GamCntnrRentFeeMngtVO vo){
		update("gamCntnrRentFeeMngtDao.updateCntnrRentFeeMngt_S", vo);
	}
	
	/**
	 * 컨테이너부두임대료고지관리정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 컨테이너부두임대료고지관리정보
	 * @exception Exception
	 */
	public GamCntnrRentFeeMngtVO selectCntnrRentFeeMngtInfo(GamCntnrRentFeeMngtVO searchVO) throws Exception {
		return (GamCntnrRentFeeMngtVO) selectByPk("gamCntnrRentFeeMngtDao.selectCntnrRentFeeMngtInfo_S", searchVO);
	}
	
	
	/**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 갯수
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamCntnrRentFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamCntnrRentFeeMngtDao.selectAnlrveLevCnt_S", searchVO);
    }
	
	/**
	 * 세입징수를 등록한다.
	 * @param vo GamCntnrRentFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamCntnrRentFeeMngtVO vo){
		insert("gamCntnrRentFeeMngtDao.insertAnlrveLev_S", vo);
	}
	
	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamCntnrRentFeeMngtVO
	 * @exception Exception
	 */
	public void deleteCntnrRentFeeMngt(GamCntnrRentFeeMngtVO vo){
		delete("gamCntnrRentFeeMngtDao.deleteCntnrRentFeeMngt_S", vo);
	}

}