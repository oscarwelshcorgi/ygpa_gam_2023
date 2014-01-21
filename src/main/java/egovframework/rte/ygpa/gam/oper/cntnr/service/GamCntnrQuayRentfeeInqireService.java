package egovframework.rte.ygpa.gam.oper.cntnr.service;

import java.util.List;

/**
 * @Class Name : GamCntnrQuayRentfeeInqireService.java
 * @Description : 컨테이너부두임대료조회 (항만시설/컨테이너부두/컨테이너부두임대료조회)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamCntnrQuayRentfeeInqireService {
	
	/**
	 * 컨테이너부두임대료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료 목록
	 * @exception Exception
	 */
    List selectGamCntnrQuayRentfeeInqireList(GamCntnrQuayRentfeeInqireVO searchVO) throws Exception;
    
    /**
	 * 컨테이너부두임대료 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료 총 갯수
	 * @exception Exception
	 */
    int selectGamCntnrQuayRentfeeInqireListTotCnt(GamCntnrQuayRentfeeInqireVO searchVO);
    
    /**
	 * 컨테이너부두임대료 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료 정보
	 * @exception Exception
	 */
    GamCntnrQuayRentfeeInqireVO selectGamCntnrQuayRentfeeInqireInfo(GamCntnrQuayRentfeeInqireVO searchVO) throws Exception;
    
}
