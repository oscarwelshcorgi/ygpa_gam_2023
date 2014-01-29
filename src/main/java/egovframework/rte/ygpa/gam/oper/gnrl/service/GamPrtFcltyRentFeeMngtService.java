package egovframework.rte.ygpa.gam.oper.gnrl.service;

import java.util.List;

/**
 * @Class Name : GamPrtFcltyRentFeeMngtService.java
 * @Description : 항만시설사용료관리 (항만시설/일반부두/항만시설사용료관리)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamPrtFcltyRentFeeMngtService {
	
	/**
	 * 항만시설사용현황을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용현황 목록
	 * @exception Exception
	 */
    List selectGamPrtFcltyRentFeeMngtList(GamPrtFcltyRentFeeMngtVO searchVO) throws Exception;
    
    /**
	 * 항만시설사용현황 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용현황 총 갯수
	 * @exception Exception
	 */
    int selectGamPrtFcltyRentFeeMngtListTotCnt(GamPrtFcltyRentFeeMngtVO searchVO);
}
