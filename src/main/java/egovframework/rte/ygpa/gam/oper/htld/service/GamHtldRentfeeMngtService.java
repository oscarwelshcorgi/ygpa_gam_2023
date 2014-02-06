package egovframework.rte.ygpa.gam.oper.htld.service;

import java.util.List;

/**
 * @Class Name : GamHtldRentfeeMngtService.java
 * @Description : 배후단지임대료관리 (항만시설/배후단지/배후단지임대료관리)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamHtldRentfeeMngtService {
	
	/**
	 * 배후단지임대료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대료 목록
	 * @exception Exception
	 */
    List selectGamHtldRentfeeMngtList(GamHtldRentfeeMngtVO searchVO) throws Exception;
    
    /**
	 * 배후단지임대료 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대료 총 갯수
	 * @exception Exception
	 */
    int selectGamHtldRentfeeMngtListTotCnt(GamHtldRentfeeMngtVO searchVO);
    
    /**
	 * 배후단지임대료 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대료 정보
	 * @exception Exception
	 */
    GamHtldRentfeeMngtVO selectGamHtldRentfeeMngtInfo(GamHtldRentfeeMngtVO searchVO) throws Exception;
    
}
