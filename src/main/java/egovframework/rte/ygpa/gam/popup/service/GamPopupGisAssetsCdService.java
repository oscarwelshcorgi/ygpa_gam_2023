package egovframework.rte.ygpa.gam.popup.service;

import java.util.List;

/**
 * @Class Name : GamPopupGisAssetsCdService.java
 * @Description : GamPopupGisAssetsCd Business class
 * @Modification Information
 *
 * @author eunsungj
 * @since 2014-01-15
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamPopupGisAssetsCdService {

    /**
	 * GIS_ASSETS_CD_F을 조회한다.
	 * @param vo - 조회할 정보가 담긴 GisAssetsCdVO
	 * @return 조회한 GIS_ASSETS_CD_F
	 * @exception Exception
	 */
    GamPopupGisAssetsCdVO selectGisAssetsCd(GamPopupGisAssetsCdVO vo) throws Exception;

    /**
	 * GIS_ASSETS_CD_F 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return GIS_ASSETS_CD_F 목록
	 * @exception Exception
	 */
    List selectGisAssetsCdList(GamPopupGisAssetsCdDefaultVO searchVO) throws Exception;

    /**
	 * GIS_ASSETS_CD_F 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return GIS_ASSETS_CD_F 총 갯수
	 * @exception
	 */
    int selectGisAssetsCdListTotCnt(GamPopupGisAssetsCdDefaultVO searchVO);

}
