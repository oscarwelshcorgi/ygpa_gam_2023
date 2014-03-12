package egovframework.rte.ygpa.gam.oper.gnrl.service;

import java.util.List;
import java.util.Map;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.impl.GamPrtFcltyMtRentFeeSttusInqireDao;

/**
 * @Class Name : GamPrtFcltyMtRentFeeSttusInqireService.java
 * @Description : 항만시설월별사용료현황조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamPrtFcltyMtRentFeeSttusInqireService {
	
    /**
	 * 항만시설월별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyMtRentFeeSttusInqireList(GamPrtFcltyMtRentFeeSttusInqireVO searchVO) throws Exception;
    
}
