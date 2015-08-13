package egovframework.rte.ygpa.gam.oper.gnrl.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.ygpa.erp.code.service.ErpAssetCdDefaultVO;
import egovframework.rte.ygpa.erp.code.service.ErpAssetCdVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.impl.GamPrtFcltyMtRentFeeSttusInqireDao;

/**
 * @Class Name : GamPrtFcltyRentFeeSttusByFeeTpInqireService.java
 * @Description : 항만시설요금종류별고지현황조회
 * @Modification Information
 *
 * @author eunsungj
 * @since 2014-11-12
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public interface GamPrtFcltyRentFeeSttusByFeeTpService {

    /**
	 * 항만시설요금종류별고지현황조회 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    List selectPrtFcltyRentFeeSttusByFeeTpList(GamPrtFcltyRentFeeSttusByFeeTpVO searchVO) throws Exception;

    /**
	 * 항만시설요금종류별고지현황조회 목록 자료수 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int - 자료수
	 * @exception Exception
	 */
    int selectPrtFcltyRentFeeSttusByFeeTpListTotCnt(GamPrtFcltyRentFeeSttusByFeeTpVO searchVO) throws Exception;

    /**
	 * 항만시설요금종류별고지현황조회 자료수, 합계 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return resultVO - 합계정보가 담긴 VO
	 * @exception Exception
	 */
    GamPrtFcltyRentFeeSttusByFeeTpVO selectPrtFcltyRentFeeSttusByFeeTpSum(GamPrtFcltyRentFeeSttusByFeeTpVO searchVO) throws Exception;

    /**
     * 고지 년도 목록을 가져온다.
     * @return
     * @throws Exception
     */
    public List getYears() throws Exception;


    /**
     * 월 목록을 가져온다.
     * @return
     * @throws Exception
     */
    public List getMonths() throws Exception;
}
