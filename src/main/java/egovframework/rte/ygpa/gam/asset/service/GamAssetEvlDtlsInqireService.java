package egovframework.rte.ygpa.gam.asset.service;

import java.util.List;
import java.util.Map;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;
import egovframework.rte.ygpa.gam.asset.service.impl.GamAssetEvlDtlsInqireDao;

/**
 * @Class Name : GamAssetEvlDtlsInqireService.java
 * @Description : 자산가치평가내역조회 Business class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamAssetEvlDtlsInqireService {
	
    /**
	 * 자산가치평가내역 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectAssetEvlDtlsInqireList(GamAssetEvlDtlsInqireVO searchVO) throws Exception;
    
    /**
	 * 자산가치평가내역 목록 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return cnt
	 * @exception
	 */
    int selectAssetEvlDtlsInqireListTotCnt(GamAssetEvlDtlsInqireVO searchVO) throws Exception;
    
    /**
	 * ERP감가상각내역을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 자산임대목록
	 * @exception Exception
	 */
    GamAssetEvlDtlsInqireVO selectAssetEvlDtlsInqireErp(GamAssetEvlDtlsInqireVO searchVO) throws Exception;
}
