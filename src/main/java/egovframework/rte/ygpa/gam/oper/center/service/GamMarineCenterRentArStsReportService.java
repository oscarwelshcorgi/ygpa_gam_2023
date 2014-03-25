package egovframework.rte.ygpa.gam.oper.center.service;

import java.util.List;

/**
 * @Class Name : GamMarineCenterRentArStsReportService.java
 * @Description : 마린센터면적별사용료현황조회 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamMarineCenterRentArStsReportService {
	
    /**
	 * 마린센터면적별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectMarineCenterRentArStsReportList(GamMarineCenterRentArStsReportVO searchVO) throws Exception;
    
    /**
   	 * 마린센터면적별사용료현황 목록 총 갯수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
    int selectMarineCenterRentArStsReportListTotCnt(GamMarineCenterRentArStsReportVO searchVO) throws Exception;
}
