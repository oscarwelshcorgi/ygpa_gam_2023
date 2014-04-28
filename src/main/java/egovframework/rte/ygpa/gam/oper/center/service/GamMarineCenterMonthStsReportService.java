package egovframework.rte.ygpa.gam.oper.center.service;

import java.util.List;

/**
 * @Class Name : GamMarineCenterMonthStsReportService.java
 * @Description : 마린센터월별사용료현황조회 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamMarineCenterMonthStsReportService {
	
    /**
	 * 마린센터월별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectMarineCenterMonthStsReportList(GamMarineCenterMonthStsReportVO searchVO) throws Exception;
    
    /**
   	 * 마린센터월별사용료현황 목록 총 갯수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
    int selectMarineCenterMonthStsReportListTotCnt(GamMarineCenterMonthStsReportVO searchVO) throws Exception;
    
    GamMarineCenterMonthStsReportVO selectMarineCenterMonthStsReportSum(GamMarineCenterMonthStsReportVO searchVO) throws Exception;
}
