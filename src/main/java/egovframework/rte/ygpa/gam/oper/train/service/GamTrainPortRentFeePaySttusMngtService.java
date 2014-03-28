package egovframework.rte.ygpa.gam.oper.train.service;

import java.util.List;

/**
 * @Class Name : GamTrainPortRentFeePaySttusMngtService.java
 * @Description : 철송장납부현황관리 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-02-05
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamTrainPortRentFeePaySttusMngtService { 
	
	/**
	 * 철송장임대납부현황관리 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectTrainPortRentFeePaySttusMngtList(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception;
    
    /**
	 * 철송장임대납부현황관리 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectTrainPortRentFeePaySttusMngtListTotCnt(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception;
    
    /**
	 * 자료수, 사용료, 부가세, 고지액을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 철송장임대납부현황관리목록
	 * @exception Exception
	 */
    GamTrainPortRentFeePaySttusMngtVO selectTrainPortRentFeePaySttusMngtSum(GamTrainPortRentFeePaySttusMngtVO searchVO) throws Exception;
    
}
