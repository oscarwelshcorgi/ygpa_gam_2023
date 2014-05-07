package egovframework.rte.ygpa.gam.popup.service;

import java.util.List;

/**
 * @Class Name : GamPopupFacilInfoService.java
 * @Description : 선석정보 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamPopupFacilInfoService {
	
    /**
	 * 선석정보 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectFacilInfoList(GamPopupFacilInfoVO searchVO) throws Exception;

    /**
	 * 선석정보 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    public int selectFacilInfoListTotCnt(GamPopupFacilInfoVO searchVO) throws Exception;
    
}
