package egovframework.rte.ygpa.gam.asset.service;

import java.util.List;
import egovframework.rte.ygpa.gam.code.service.GamGisAssetCodeVO;

/**
 * @Class Name : GamAssetDisUseMngtService.java
 * @Description : 자산폐기등록 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamAssetDisUseMngtService {

    /**
	 * GIS자산코드 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetDisUseList(GamGisAssetCodeVO searchVO) throws Exception;

    /**
	 * GIS자산코드 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetDisUseListTotCnt(GamGisAssetCodeVO searchVO) throws Exception;

    /**
	 * ERP 자산 폐기 정보를 수정한다.
	 * @param vo GamAssetDisUseMngtVO
	 * @exception Exception
	 */
	void updateAssetDisUse(GamGisAssetCodeVO vo) throws Exception;

}
