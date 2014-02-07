package egovframework.rte.ygpa.gam.asset.service;

import java.util.List;
import java.util.Map;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;

/**
 * @Class Name : GamAssetSttusInqireService.java
 * @Description : 자산정보현황조회 Business class
 * @Modification Information
 *
 * @author 정윤후
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamAssetSttusInqireService {
	
    /**
	 * 자산정보현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetSttusInqireList(GamAssetSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 자산정보현황 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetDisUseListTotCnt(GamAssetSttusInqireVO searchVO) throws Exception;
    
}
