package egovframework.rte.ygpa.gam.oper.gnrltest.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyPdRentFeeSttusInqireVO;
import egovframework.rte.ygpa.gam.port_mis.service.GamFcltyUseSttusInqireVO;

/**
 * @Class Name : GamTestPrtFcltyPdRentFeeSttusInqireDao.java
 * @Description : 항만시설기간별사용료현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamTestPrtFcltyPdRentFeeSttusInqireDao")
public class GamTestPrtFcltyPdRentFeeSttusInqireDao extends YGPAAbstractDAO {
	
	/**
	 * 항만시설기간별사용료현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyPdRentFeeSttusInqireList(GamTestPrtFcltyPdRentFeeSttusInqireVO searchVO) throws Exception {
        return list("gamTestPrtFcltyPdRentFeeSttusInqireDao.selectPrtFcltyPdRentFeeSttusInqireList_D", searchVO);
    }
    
    
    /**
	 * 항만시설기간별사용료현황 전체목록수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public int selectPrtFcltyPdRentFeeSttusInqireListTotCnt(GamTestPrtFcltyPdRentFeeSttusInqireVO searchVO) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamTestPrtFcltyPdRentFeeSttusInqireDao.selectPrtFcltyPdRentFeeSttusInqireListTotCnt_S", searchVO);
    }
    
    
    /**
	 * 사용료합계, 감면사용료 합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
    public GamTestPrtFcltyPdRentFeeSttusInqireVO selectPrtFcltyPdRentFeeSttusInqireSum(GamTestPrtFcltyPdRentFeeSttusInqireVO searchVO) throws Exception {
    	return (GamTestPrtFcltyPdRentFeeSttusInqireVO) selectByPk("gamTestPrtFcltyPdRentFeeSttusInqireDao.selectPrtFcltyPdRentFeeSttusInqireSum_S", searchVO);
    }
    
}
