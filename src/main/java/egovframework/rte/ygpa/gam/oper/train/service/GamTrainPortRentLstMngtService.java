package egovframework.rte.ygpa.gam.oper.train.service;

import java.util.List;

/**
 * @Class Name : GamTrainPortRentLstMngtService.java
 * @Description : 철송장임대목록관리 (항만시설/철송장/철송장임대목록관리)
 * @Modification Information
 *
 * @author 도명호
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamTrainPortRentLstMngtService {
	
	/**
	 * 컨테이너부두임대목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대 목록
	 * @exception Exception
	 */
    List selectGamTrainPortRentLstMngtList(GamTrainPortRentLstMngtVO searchVO) throws Exception;
    
    /**
	 * 컨테이너부두임대목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록 총 갯수
	 * @exception Exception
	 */
    int selectGamTrainPortRentLstMngtListTotCnt(GamTrainPortRentLstMngtVO searchVO);
    
    /**
	 * 컨테이너부두임대목록 정보 (자료수, 총면적, 총사용료 등)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 컨테이너부두임대목록 정보
	 * @exception Exception
	 */
    GamTrainPortRentLstMngtVO selectGamTrainPortRentLstMngtInfo(GamTrainPortRentLstMngtVO searchVO) throws Exception;
    
}
