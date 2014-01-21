package egovframework.rte.ygpa.gam.oper.cntnr.service;

import java.util.List;

/**
 * @Class Name : GamCntnrQuayRentSttusInqireService.java
 * @Description : 컨테이너부두임대현황조회 (항만시설/컨테이너부두/컨테이너부두임대현황조회)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamCntnrQuayRentSttusInqireService {
	
	/**
	 * 컨테이너부두임대현황을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용현황 목록
	 * @exception Exception
	 */
    List selectGamCntnrQuayRentSttusInqireList(GamCntnrQuayRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 컨테이너부두임대현황 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용현황 총 갯수
	 * @exception Exception
	 */
    int selectGamCntnrQuayRentSttusInqireListTotCnt(GamCntnrQuayRentSttusInqireVO searchVO);
    
    /**
	 * 컨테이너부두임대현황 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용현황 정보
	 * @exception Exception
	 */
    GamCntnrQuayRentSttusInqireVO selectGamCntnrQuayRentSttusInqireInfo(GamCntnrQuayRentSttusInqireVO searchVO) throws Exception;
    
}
