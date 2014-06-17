package egovframework.rte.ygpa.gam.oper.cntnr.service;

import egovframework.rte.ygpa.gam.asset.rent.service.GamAssetPopupInqireVO;

/**
 * @Class Name : GamCntnrQuayPopupInqireService.java
 * @Description : 컨테이너부두 정보현황알림 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  
 *  수정일         수정자                   수정내용
 *  -------    --------    ---------------------------
 *  2014.06.17  sj          최초 생성
 *  Copyright (C)  All right reserved.
 */
public interface GamCntnrQuayPopupInqireService {
    
    /**
	 * 자산정보현황 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산정보현황
	 * @exception Exception
	 */
    GamAssetPopupInqireVO selectCntnrQuayPopupInqire(GamAssetPopupInqireVO searchVO) throws Exception;
}
