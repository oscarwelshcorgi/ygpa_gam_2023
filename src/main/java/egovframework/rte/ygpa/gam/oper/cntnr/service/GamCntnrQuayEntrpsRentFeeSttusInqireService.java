package egovframework.rte.ygpa.gam.oper.cntnr.service;

import java.util.List;
import java.util.Map;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;
import egovframework.rte.ygpa.gam.oper.cntnr.service.impl.GamCntnrQuayEntrpsRentFeeSttusInqireDao;

/**
 * @Class Name : GamCntnrQuayEntrpsRentFeeSttusInqireService.java
 * @Description : 컨테이너부두임대업체별사용료현황조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamCntnrQuayEntrpsRentFeeSttusInqireService {
	
    /**
	 * 컨테이너부두임대업체별사용료현황목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCntnrQuayEntrpsRentFeeSttusInqireList(GamCntnrQuayEntrpsRentFeeSttusInqireVO searchVO) throws Exception;
    
    
    /**
	 * 컨테이너부두임대업체별사용료현황 전체목록수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @exception Exception
	 */
    int selectCntnrQuayEntrpsRentFeeSttusInqireListTotCnt(GamCntnrQuayEntrpsRentFeeSttusInqireVO searchVO) throws Exception;
    
    
    /**
	 * 전체사용료합계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    GamCntnrQuayEntrpsRentFeeSttusInqireVO selectCntnrQuayEntrpsRentFeeSttusInqireSum(GamCntnrQuayEntrpsRentFeeSttusInqireVO searchVO) throws Exception;
    
}
