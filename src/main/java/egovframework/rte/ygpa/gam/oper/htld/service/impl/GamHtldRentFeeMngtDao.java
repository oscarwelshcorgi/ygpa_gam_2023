package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldRentFFeeMngtVO;

/**
 * @Class Name : GamHtldRentFFeeMngtDao.java
 * @Description : 배후단지임대료관리 DAO Class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamHtldRentFFeeMngtDao")
public class GamHtldRentFFeeMngtDao extends YGPAAbstractDAO {
	
	/**
	 * 배후단지임대료고지관리 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대료고지관리 목록
	 * @exception Exception
	 */
    public List selectHtldRentFFeeMngtList(GamHtldRentFFeeMngtVO searchVO) throws Exception {
        return list("gamHtldRentFFeeMngtDao.selectHtldRentFFeeMngtList_D", searchVO);
    }

    /**
	 * 배후단지임대료고지관리 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대료고지관리 목록 총 갯수
	 * @exception
	 */
    public int selectHtldRentFFeeMngtListTotCnt(GamHtldRentFFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentFFeeMngtDao.selectHtldRentFFeeMngtListTotCnt_S", searchVO);
    }
    
    /**
	 * 자료수, 사용료, 연체, 부가세, 고지액을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대료고지관리 목록
	 * @exception Exception
	 */
	public GamHtldRentFFeeMngtVO selectHtldRentFFeeMngtSum(GamHtldRentFFeeMngtVO searchVO) throws Exception {
		return (GamHtldRentFFeeMngtVO) selectByPk("gamHtldRentFFeeMngtDao.selectHtldRentFFeeMngtSum_S", searchVO);
	}
	
	/**
	 * 배후단지임대료고지관리정보를 수정한다.
	 * @param vo GamHtldRentFFeeMngtVO
	 * @exception Exception
	 */
	public void updateHtldRentFFeeMngt(GamHtldRentFFeeMngtVO vo){
		update("gamHtldRentFFeeMngtDao.updateHtldRentFFeeMngt_S", vo);
	}
	
	/**
	 * 배후단지임대료고지관리정보 조회.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 배후단지임대료고지관리정보
	 * @exception Exception
	 */
	public GamHtldRentFFeeMngtVO selectHtldRentFFeeMngtInfo(GamHtldRentFFeeMngtVO searchVO) throws Exception {
		return (GamHtldRentFFeeMngtVO) selectByPk("gamHtldRentFFeeMngtDao.selectHtldRentFFeeMngtInfo_S", searchVO);
	}
	
	
	/**
	 * 세입징수 등록건수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 갯수
	 * @exception
	 */
    public int selectAnlrveLevCnt(GamHtldRentFFeeMngtVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamHtldRentFFeeMngtDao.selectAnlrveLevCnt_S", searchVO);
    }
	
	/**
	 * 세입징수를 등록한다.
	 * @param vo GamHtldRentFFeeMngtVO
	 * @exception Exception
	 */
	public void insertAnlrveLev(GamHtldRentFFeeMngtVO vo){
		insert("gamHtldRentFFeeMngtDao.insertAnlrveLev_S", vo);
	}
	
	/**
	 * 징수의뢰를 삭제한다.
	 * @param vo GamHtldRentFFeeMngtVO
	 * @exception Exception
	 */
	public void deleteHtldRentFFeeMngt(GamHtldRentFFeeMngtVO vo){
		delete("gamHtldRentFFeeMngtDao.deleteHtldRentFFeeMngt_S", vo);
	}

}