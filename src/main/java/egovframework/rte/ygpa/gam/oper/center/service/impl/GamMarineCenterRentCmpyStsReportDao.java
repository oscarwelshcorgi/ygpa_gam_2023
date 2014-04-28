package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentCmpyStsReportVO;

/**
 * @Class Name : GamMarineCenterRentCmpyStsReportDao.java
 * @Description : 마린센터업체별사용료현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Repository("gamMarineCenterRentCmpyStsReportDao")
public class GamMarineCenterRentCmpyStsReportDao extends YGPAAbstractDAO {
	
	/**
	 * 마린센터업체별사용료현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectMarineCenterRentCmpyStsReportList(GamMarineCenterRentCmpyStsReportVO searchVO) throws Exception {
        return list("gamMarineCenterRentCmpyStsReportDao.selectMarineCenterRentCmpyStsReportList_D", searchVO);
    }
    
    /**
	 * 마린센터업체별사용료현황 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 자산임대관리 목록 총 갯수
	 * @exception
	 */
    public int selectMarineCenterRentCmpyStsReportListTotCnt(GamMarineCenterRentCmpyStsReportVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("gamMarineCenterRentCmpyStsReportDao.selectMarineCenterRentCmpyStsReportListTotCnt_S", searchVO);
    }
    
    
    /**
   	 * 전체사용료합계
   	 * @param searchMap - 조회할 정보가 담긴 Map
   	 * @return vo
   	 * @exception Exception
   	 */
       public GamMarineCenterRentCmpyStsReportVO selectMarineCenterRentCmpyStsReportSum(GamMarineCenterRentCmpyStsReportVO searchVO) throws Exception {
       	return (GamMarineCenterRentCmpyStsReportVO) selectByPk("gamMarineCenterRentCmpyStsReportDao.selectMarineCenterRentCmpyStsReportSum_S", searchVO);
       }
    
}
