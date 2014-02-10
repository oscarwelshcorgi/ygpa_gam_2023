package egovframework.rte.ygpa.gam.asset.rent.service;

import java.util.List;
import java.util.Map;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;

/**
 * @Class Name : GamCmpyRecvStsInqireService.java
 * @Description : 업체별수입현황조회 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamCmpyRecvStsInqireService {
	
	/**
	 * 업체별수입현황조회 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmpyRecvStsInqireList(GamCmpyRecvStsInqireVO searchVO) throws Exception;
    
    /**
	 * 업체별수입현황조회 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectCmpyRecvStsInqireListTotCnt(GamCmpyRecvStsInqireVO searchVO) throws Exception;
    
}
