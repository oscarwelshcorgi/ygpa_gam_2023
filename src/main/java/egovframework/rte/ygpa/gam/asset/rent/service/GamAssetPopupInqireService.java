package egovframework.rte.ygpa.gam.asset.rent.service;


/**
 * @Class Name : GamAssetPopupInqireService.java
 * @Description : 자산정보현황알림 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamAssetPopupInqireService {
    
    /**
	 * 자산정보현황 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산정보현황
	 * @exception Exception
	 */
    GamAssetPopupInqireVO selectAssetPopupInqire(GamAssetPopupInqireVO searchVO) throws Exception;
}
