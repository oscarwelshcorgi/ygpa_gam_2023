package egovframework.rte.ygpa.gam.domain.code.service;

import java.util.List;
import egovframework.rte.ygpa.gam.domain.code.service.GisAssetsCdDefaultVO;
import egovframework.rte.ygpa.gam.domain.code.service.GisAssetsCdVO;

/**
 * @Class Name : GisAssetsCdFService.java
 * @Description : GisAssetsCdF Business class
 * @Modification Information
 *
 * @author eunsungj
 * @since 2014-01-15
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GisAssetsCdService {
	
	/**
	 * GIS_ASSETS_CD_F을 등록한다.
	 * @param vo - 등록할 정보가 담긴 GisAssetsCdFVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertGisAssetsCdF(GisAssetsCdVO vo) throws Exception;
    
    /**
	 * GIS_ASSETS_CD_F을 수정한다.
	 * @param vo - 수정할 정보가 담긴 GisAssetsCdFVO
	 * @return void형
	 * @exception Exception
	 */
    void updateGisAssetsCdF(GisAssetsCdVO vo) throws Exception;
    
    /**
	 * GIS_ASSETS_CD_F을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 GisAssetsCdFVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteGisAssetsCdF(GisAssetsCdVO vo) throws Exception;
    
    /**
	 * GIS_ASSETS_CD_F을 조회한다.
	 * @param vo - 조회할 정보가 담긴 GisAssetsCdFVO
	 * @return 조회한 GIS_ASSETS_CD_F
	 * @exception Exception
	 */
    GisAssetsCdVO selectGisAssetsCdF(GisAssetsCdVO vo) throws Exception;
    
    /**
	 * GIS_ASSETS_CD_F 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return GIS_ASSETS_CD_F 목록
	 * @exception Exception
	 */
    List selectGisAssetsCdFList(GisAssetsCdDefaultVO searchVO) throws Exception;
    
    /**
	 * GIS_ASSETS_CD_F 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return GIS_ASSETS_CD_F 총 갯수
	 * @exception
	 */
    int selectGisAssetsCdFListTotCnt(GisAssetsCdDefaultVO searchVO);
    
}
