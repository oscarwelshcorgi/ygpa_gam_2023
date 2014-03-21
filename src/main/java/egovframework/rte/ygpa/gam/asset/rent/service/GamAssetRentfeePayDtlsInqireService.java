package egovframework.rte.ygpa.gam.asset.rent.service;

import java.util.List;

/**
 * @Class Name : GamAssetRentfeePayDtlsInqireService.java
 * @Description : 사용료납부내역조회 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamAssetRentfeePayDtlsInqireService {
	
    /**
	 * 사용료납부내역 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetRentfeePayDtlsInqireList(GamAssetRentfeePayDtlsInqireVO searchVO) throws Exception;
    
    /**
   	 * 사용료납부내역 목록 총 갯수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
    int selectAssetRentfeePayDtlsInqireListTotCnt(GamAssetRentfeePayDtlsInqireVO searchVO) throws Exception;
}
