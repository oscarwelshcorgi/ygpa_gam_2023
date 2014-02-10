package egovframework.rte.ygpa.gam.asset.rent.service;

import java.util.List;
import java.util.Map;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;

/**
 * @Class Name : GamAssetRentSttusInqireService.java
 * @Description : 자산임대현황조회 Business class
 * @Modification Information
 *
 * @author 정윤후
 * @since 2014-01-22
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamAssetRentSttusInqireService {
	
	/**
	 * 자산임대 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    List selectAssetRentList(GamAssetRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 자료수, 총면적, 총사용료를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamAssetRentSttusInqireVO selectAssetRentSum(GamAssetRentSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 자산임대 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대 목록 총 갯수
	 * @exception
	 */
    int selectAssetRentListTotCnt(GamAssetRentSttusInqireVO searchVO) throws Exception;
	
	/**
	 * 자산임대 상세 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetRentDetailList(GamAssetRentSttusInqireVO vo) throws Exception;

    /**
	 * 자산임대 상세 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetRentDetailListTotCnt(GamAssetRentSttusInqireVO vo) throws Exception;
	
}
