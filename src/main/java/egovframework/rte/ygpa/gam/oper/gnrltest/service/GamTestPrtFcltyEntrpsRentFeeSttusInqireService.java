package egovframework.rte.ygpa.gam.oper.gnrltest.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.impl.GamTestPrtFcltyEntrpsRentFeeSttusInqireDao;

/**
 * @Class Name : GamTestPrtFcltyEntrpsRentFeeSttusInqireService.java
 * @Description : 항만시설업체별사용료현황조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamTestPrtFcltyEntrpsRentFeeSttusInqireService {
	
    /**
	 * 항만시설업체별사용료현황목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyEntrpsRentFeeSttusInqireList(GamTestPrtFcltyEntrpsRentFeeSttusInqireVO searchVO) throws Exception;
    
    
    /**
	 * 항만시설업체별사용료현황 전체목록수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int
	 * @exception Exception
	 */
    int selectPrtFcltyEntrpsRentFeeSttusInqireListTotCnt(GamTestPrtFcltyEntrpsRentFeeSttusInqireVO searchVO) throws Exception;
    
    
    /**
	 * 전체사용료합계
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return vo
	 * @exception Exception
	 */
    GamTestPrtFcltyEntrpsRentFeeSttusInqireVO selectPrtFcltyEntrpsRentFeeSttusInqireSum(GamTestPrtFcltyEntrpsRentFeeSttusInqireVO searchVO) throws Exception;
    
}
