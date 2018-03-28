package egovframework.rte.ygpa.gam.oper.gnrltest.service;

import java.util.List;
import java.util.Map;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.impl.GamTestPrtFcltyMtRentFeeSttusInqireDao;

/**
 * @Class Name : GamTestPrtFcltyMtRentFeeSttusInqireService.java
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
public interface GamTestPrtFcltyMtRentFeeSttusInqireService {
	
    /**
	 * 항만시설월별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyMtRentFeeSttusInqireList(GamTestPrtFcltyMtRentFeeSttusInqireVO searchVO) throws Exception;
    
    /**
	 * 항만시설월별사용료현황 목록 자료수 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int - 자료수
	 * @exception Exception
	 */
    int selectPrtFcltyMtRentFeeSttusInqireListTotCnt(GamTestPrtFcltyMtRentFeeSttusInqireVO searchVO) throws Exception;

    /**
	 * 항만시설월별사용료현황 자료수, 합계 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return resultVO - 합계정보가 담긴 VO
	 * @exception Exception
	 */
    GamTestPrtFcltyMtRentFeeSttusInqireVO selectPrtFcltyMtRentFeeSttusInqireSum(GamTestPrtFcltyMtRentFeeSttusInqireVO searchVO) throws Exception;
}