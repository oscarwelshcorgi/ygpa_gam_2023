package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrEntrpsRentFeeSttusInqireVO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrMtRentFeeSttusInqireVO;

/**
 * @Class Name : GamCmmnCntrMtRentFeeSttusInqireDao.java
 * @Description : 공컨장치장임대월별사용료현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCmmnCntrMtRentFeeSttusInqireDao")
public class GamCmmnCntrMtRentFeeSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 공컨장치장임대월별사용료현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrMtRentFeeSttusInqireList(GamCmmnCntrMtRentFeeSttusInqireVO searchVO) throws Exception {
        return list("gamCmmnCntrMtRentFeeSttusInqireDao.selectCmmnCntrMtRentFeeSttusInqireList_D", searchVO);
    }

    /**
	 * 항만시설월별사용료현황 목록 자료수 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int - 자료수
	 * @exception Exception
	 */
    int selectCmmnCntrMtRentFeeSttusInqireListTotCnt(GamCmmnCntrMtRentFeeSttusInqireVO searchVO) throws Exception{
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamCmmnCntrMtRentFeeSttusInqireDao.selectCmmnCntrMtRentFeeSttusInqireListTotCnt_S", searchVO);    
    }

    /**
	 * 항만시설월별사용료현황 자료수, 합계 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return resultVO - 합계정보가 담긴 VO
	 * @exception Exception
	 */
    GamCmmnCntrMtRentFeeSttusInqireVO selectCmmnCntrMtRentFeeSttusInqireSum(GamCmmnCntrMtRentFeeSttusInqireVO searchVO) throws Exception {
    	return (GamCmmnCntrMtRentFeeSttusInqireVO) selectByPk("gamCmmnCntrMtRentFeeSttusInqireDao.selectCmmnCntrMtRentFeeSttusInqireSum_S", searchVO);
    }

}
