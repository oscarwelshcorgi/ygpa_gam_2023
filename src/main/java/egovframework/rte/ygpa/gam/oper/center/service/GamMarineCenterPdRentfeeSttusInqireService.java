package egovframework.rte.ygpa.gam.oper.center.service;

import java.util.List;

/**
 * @Class Name : GamMarineCenterPdRentfeeSttusInqireService.java
 * @Description : 마린센터기간별사용료현황조회 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamMarineCenterPdRentfeeSttusInqireService {
	
    /**
	 * 마린센터기간별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectMarineCenterPdRentfeeSttusInqireList(GamMarineCenterPdRentfeeSttusInqireVO searchVO) throws Exception;
    
    /**
   	 * 마린센터기간별사용료현황 목록 총 갯수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
    int selectMarineCenterPdRentfeeSttusInqireListTotCnt(GamMarineCenterPdRentfeeSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 사용료합계, 감면사용료 합계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    GamMarineCenterPdRentfeeSttusInqireVO selectMarineCenterPdRentfeeSttusInqireSum(GamMarineCenterPdRentfeeSttusInqireVO searchVO) throws Exception;
}
