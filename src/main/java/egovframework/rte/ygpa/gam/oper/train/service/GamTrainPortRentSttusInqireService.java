package egovframework.rte.ygpa.gam.oper.train.service;

import java.util.List;

/**
 * @Class Name : GamTrainPortRentSttusInqireService.java
 * @Description : 철송장임대현황조회 (항만시설/철송장/철송장임대현황조회)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamTrainPortRentSttusInqireService {
	
	/**
	 * 컨테이너부두임대현황을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용현황 목록
	 * @exception Exception
	 */
    List selectGamTrainPortRentSttusInqireList(GamTrainPortRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 컨테이너부두임대현황 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용현황 총 갯수
	 * @exception Exception
	 */
    int selectGamTrainPortRentSttusInqireListTotCnt(GamTrainPortRentSttusInqireVO searchVO);
    
    /**
	 * 컨테이너부두임대현황 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 항만시설사용현황 정보
	 * @exception Exception
	 */
    GamTrainPortRentSttusInqireVO selectGamTrainPortRentSttusInqireInfo(GamTrainPortRentSttusInqireVO searchVO) throws Exception;
    
}
