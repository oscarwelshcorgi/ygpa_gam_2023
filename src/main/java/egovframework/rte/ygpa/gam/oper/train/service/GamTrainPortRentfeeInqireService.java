package egovframework.rte.ygpa.gam.oper.train.service;

import java.util.List;

/**
 * @Class Name : GamTrainPortRentfeeInqireService.java
 * @Description : 철송장임대료조회 (항만시설/철송장/철송장임대료조회)
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamTrainPortRentfeeInqireService {
	
	/**
	 * 컨테이너부두임대료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료 목록
	 * @exception Exception
	 */
    List selectGamTrainPortRentfeeInqireList(GamTrainPortRentfeeInqireVO searchVO) throws Exception;
    
    /**
	 * 컨테이너부두임대료 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료 총 갯수
	 * @exception Exception
	 */
    int selectGamTrainPortRentfeeInqireListTotCnt(GamTrainPortRentfeeInqireVO searchVO);
    
    /**
	 * 컨테이너부두임대료 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대료 정보
	 * @exception Exception
	 */
    GamTrainPortRentfeeInqireVO selectGamTrainPortRentfeeInqireInfo(GamTrainPortRentfeeInqireVO searchVO) throws Exception;
    
}
