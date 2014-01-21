package egovframework.rte.ygpa.gam.oper.shed.service;

import java.util.List;

/**
 * @Class Name : GamCmmnCntrRentFeeInqireService.java
 * @Description : 공컨장치장임대료조회 (항만시설/공컨장치장/공컨장치장임대료조회)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamCmmnCntrRentFeeInqireService {
	
	/**
	 * 컨테이너부두임대료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료 목록
	 * @exception Exception
	 */
    List selectGamCmmnCntrRentFeeInqireList(GamCmmnCntrRentFeeInqireVO searchVO) throws Exception;
    
    /**
	 * 컨테이너부두임대료 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료 총 갯수
	 * @exception Exception
	 */
    int selectGamCmmnCntrRentFeeInqireListTotCnt(GamCmmnCntrRentFeeInqireVO searchVO);
    
    /**
	 * 컨테이너부두임대료 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료 정보
	 * @exception Exception
	 */
    GamCmmnCntrRentFeeInqireVO selectGamCmmnCntrRentFeeInqireInfo(GamCmmnCntrRentFeeInqireVO searchVO) throws Exception;
    
}
