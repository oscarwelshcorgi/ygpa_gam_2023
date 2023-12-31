package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterMonthStsReportVO;

/**
 * @Class Name : GamMarineCenterMonthStsReportDao.java
 * @Description : 마린센터월별사용료현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamMarineCenterMonthStsReportDao")
public class GamMarineCenterMonthStsReportDao extends YGPAAbstractDAO {
	
	/**
	 * 마린센터월별사용료현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectMarineCenterMonthStsReportList(GamMarineCenterMonthStsReportVO searchVO) throws Exception {
        return list("gamMarineCenterMonthStsReportDao.selectMarineCenterMonthStsReportList_D", searchVO);
    }
    
    /**
	 * 마린센터월별사용료현황 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterMonthStsReportListTotCnt(GamMarineCenterMonthStsReportVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterMonthStsReportDao.selectMarineCenterMonthStsReportListTotCnt_S", searchVO);
    }
    
    public GamMarineCenterMonthStsReportVO selectMarineCenterMonthStsReportSum(GamMarineCenterMonthStsReportVO searchVO) {
        return (GamMarineCenterMonthStsReportVO) selectByPk("gamMarineCenterMonthStsReportDao.selectMarineCenterMonthStsReportSum_S", searchVO);
    }
    
}
