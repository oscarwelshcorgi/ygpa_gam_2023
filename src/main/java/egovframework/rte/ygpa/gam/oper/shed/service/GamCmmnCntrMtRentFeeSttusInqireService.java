package egovframework.rte.ygpa.gam.oper.shed.service;

import java.util.List;
import java.util.Map;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;
import egovframework.rte.ygpa.gam.oper.shed.service.impl.GamCmmnCntrMtRentFeeSttusInqireDao;

/**
 * @Class Name : GamCmmnCntrMtRentFeeSttusInqireService.java
 * @Description : 공컨장치장임대월별사용료현황조회 Business class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface GamCmmnCntrMtRentFeeSttusInqireService {
	
    /**
	 * 공컨장치장임대월별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectCmmnCntrMtRentFeeSttusInqireList(GamCmmnCntrMtRentFeeSttusInqireVO searchVO) throws Exception;


    /**
	 * 항만시설월별사용료현황 목록 자료수 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int - 자료수
	 * @exception Exception
	 */
    int selectCmmnCntrMtRentFeeSttusInqireListTotCnt(GamCmmnCntrMtRentFeeSttusInqireVO searchVO) throws Exception;

    /**
	 * 항만시설월별사용료현황 자료수, 합계 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return resultVO - 합계정보가 담긴 VO
	 * @exception Exception
	 */
    GamCmmnCntrMtRentFeeSttusInqireVO selectCmmnCntrMtRentFeeSttusInqireSum(GamCmmnCntrMtRentFeeSttusInqireVO searchVO) throws Exception;


}
