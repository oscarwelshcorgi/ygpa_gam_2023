package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentArStsReportVO;

/**
 * @Class Name : GamMarineCenterRentArStsReportDao.java
 * @Description : 마린센터면적별사용료현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamMarineCenterRentArStsReportDao")
public class GamMarineCenterRentArStsReportDao extends YGPAAbstractDAO {
	
	/**
	 * 마린센터면적별사용료현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectMarineCenterRentArStsReportList(GamMarineCenterRentArStsReportVO searchVO) throws Exception {
        return list("gamMarineCenterRentArStsReportDao.selectMarineCenterRentArStsReportList_D", searchVO);
    }
    
    /**
	 * 마린센터면적별사용료현황 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterRentArStsReportListTotCnt(GamMarineCenterRentArStsReportVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentArStsReportDao.selectMarineCenterRentArStsReportListTotCnt_S", searchVO);
    }
    
}
