package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrPdRentFeeSttusInqireVO;

/**
 * @Class Name : GamCmmnCntrPdRentFeeSttusInqireDao.java
 * @Description : 공컨장치장임대기간별사용료현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamCmmnCntrPdRentFeeSttusInqireDao")
public class GamCmmnCntrPdRentFeeSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 공컨장치장임대기간별사용료현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectCmmnCntrPdRentFeeSttusInqireList(GamCmmnCntrPdRentFeeSttusInqireVO searchVO) throws Exception {
        return list("gamCmmnCntrPdRentFeeSttusInqireDao.selectCmmnCntrPdRentFeeSttusInqireList_D", searchVO);
    }
    
    
    /**
	 * 공컨장치장임대기간별사용료현황 전체목록수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public int selectCmmnCntrPdRentFeeSttusInqireListTotCnt(GamCmmnCntrPdRentFeeSttusInqireVO searchVO) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyPdRentFeeSttusInqireDao.selectCmmnCntrPdRentFeeSttusInqireListTotCnt_S", searchVO);
    }
    
    
    /**
	 * 사용료합계, 감면사용료 합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
    public GamCmmnCntrPdRentFeeSttusInqireVO selectCmmnCntrPdRentFeeSttusInqireSum(GamCmmnCntrPdRentFeeSttusInqireVO searchVO) throws Exception {
    	return (GamCmmnCntrPdRentFeeSttusInqireVO) selectByPk("gamPrtFcltyPdRentFeeSttusInqireDao.selectCmmnCntrPdRentFeeSttusInqireSum_D", searchVO);
    }
    
}
