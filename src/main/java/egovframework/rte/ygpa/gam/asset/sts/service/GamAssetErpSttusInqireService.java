package egovframework.rte.ygpa.gam.asset.sts.service;

import java.util.List;

/**
 * @Class Name : GamAssetSttusInqireService.java
 * @Description : 자산정보현황조회 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamAssetErpSttusInqireService {
	
    /**
	 * 자산정보현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetErpSttusInqireList(GamSttusInqireDefaultVO searchVO) throws Exception;
    

}
