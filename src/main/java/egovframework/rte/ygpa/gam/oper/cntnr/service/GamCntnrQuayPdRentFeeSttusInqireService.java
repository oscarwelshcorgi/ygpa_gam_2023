package egovframework.rte.ygpa.gam.oper.cntnr.service;

import java.util.List;
import java.util.Map;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.impl.GamCntnrQuayPdRentFeeSttusInqireDao;

/**
 * @Class Name : GamCntnrQuayPdRentFeeSttusInqireService.java
 * @Description : 컨테이너부두임대기간별사용료현황조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamCntnrQuayPdRentFeeSttusInqireService {
	
    /**
	 * 컨테이너부두임대기간별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCntnrQuayPdRentFeeSttusInqireList(GamCntnrQuayPdRentFeeSttusInqireVO searchVO) throws Exception;
    
}
