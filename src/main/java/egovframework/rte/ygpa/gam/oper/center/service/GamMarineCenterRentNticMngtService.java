package egovframework.rte.ygpa.gam.oper.center.service;

import java.util.List;

/**
 * @Class Name : GamMarineCenterRentNticMngtService.java
 * @Description : 마린센터임대료납부관리 Business class
 * @Modification Information
 *
 * @author heroin
 * @since 2014-02-05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamMarineCenterRentNticMngtService {
	
    /**
	 * 마린센터임대료납부관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectMarineCenterRentNticList(GamMarineCenterRentNticMngtVO searchVO) throws Exception;
    
    /**
	 * 마린센터임대료납부관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectMarineCenterRentNticListTotCnt(GamMarineCenterRentNticMngtVO searchVO) throws Exception;
    
    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 마린센터임대료납부관리목록
	 * @exception Exception
	 */
    GamMarineCenterRentNticMngtVO selectMarineCenterRentNticSum(GamMarineCenterRentNticMngtVO searchVO) throws Exception;
    
}
