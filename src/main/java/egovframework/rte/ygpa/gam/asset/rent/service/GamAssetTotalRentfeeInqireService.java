package egovframework.rte.ygpa.gam.asset.rent.service;

import java.util.List;

/**
 * @Class Name : GamAssetTotalRentfeeInqireService.java
 * @Description : 자산별사용료현황조회 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamAssetTotalRentfeeInqireService {
	
    /**
	 * 자산별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetTotalRentfeeInqireList(GamAssetTotalRentfeeInqireVO searchVO) throws Exception;
    
    /**
   	 * 자산별사용료현황 목록 총 갯수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
    int selectAssetTotalRentfeeInqireListTotCnt(GamAssetTotalRentfeeInqireVO searchVO) throws Exception;
    
    
    /**
	 * 사용료합계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    GamAssetTotalRentfeeInqireVO selectAssetTotalRentfeeInqireSum(GamAssetTotalRentfeeInqireVO searchVO) throws Exception;
}
