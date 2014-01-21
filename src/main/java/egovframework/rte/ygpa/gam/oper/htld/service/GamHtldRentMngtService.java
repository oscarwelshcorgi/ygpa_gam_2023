package egovframework.rte.ygpa.gam.oper.htld.service;

import java.util.List;

/**
 * @Class Name : GamHtldRentMngtService.java
 * @Description : 배후단지임대목록관리 (항만시설/배후단지/배후단지임대목록관리)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamHtldRentMngtService {
	
	/**
	 * 배후단지임대목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대 목록
	 * @exception Exception
	 */
    List selectGamHtldRentMngtList(GamHtldRentMngtVO searchVO) throws Exception;
    
    /**
	 * 배후단지임대목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록 총 갯수
	 * @exception Exception
	 */
    int selectGamHtldRentMngtListTotCnt(GamHtldRentMngtVO searchVO);
    
    /**
	 * 배후단지임대목록 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 배후단지임대목록 정보
	 * @exception Exception
	 */
    GamHtldRentMngtVO selectGamHtldRentMngtInfo(GamHtldRentMngtVO searchVO) throws Exception;
    
}
