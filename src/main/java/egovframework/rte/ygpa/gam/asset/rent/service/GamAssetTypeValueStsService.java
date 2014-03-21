package egovframework.rte.ygpa.gam.asset.rent.service;

import java.util.List;

/**
 * @Class Name : GamAssetTypeValueStsService.java
 * @Description : 자산종류별자산가치통계조회 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamAssetTypeValueStsService {
	
    /**
	 * 자산종류별자산가치통계조회 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetTypeValueStsList(GamAssetTypeValueStsVO searchVO) throws Exception;
    
    /**
   	 * 자산종류별자산가치통계조회 목록 총 갯수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
    int selectAssetTypeValueStsListTotCnt(GamAssetTypeValueStsVO searchVO) throws Exception;
}
