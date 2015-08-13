package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.cmmn.dataaccess.YGPAAbstractDAO;
import egovframework.rte.ygpa.gam.asset.rent.service.GamCmpyRecvStsInqireVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyMtRentFeeSttusInqireVO;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyRentFeeSttusByFeeTpVO;

/**
 * @Class Name : GamPrtFcltyMtRentFeeSttusInqireDao.java
 * @Description : 항만시설월별사용료현황조회 DAO Class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-29
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Repository("gamPrtFcltyRentFeeSttusByFeeTpDao")
public class GamPrtFcltyRentFeeSttusByFeeTpDao extends YGPAAbstractDAO {

	/**
	 * 항만시설월별사용료현황 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 목록
	 * @exception Exception
	 */
    public List selectPrtFcltyRentFeeSttusByFeeTpList(GamPrtFcltyRentFeeSttusByFeeTpVO searchVO) throws Exception {
        return list("gamPrtFcltyRentFeeSttusByFeeTpDao.selectPrtFcltyRentFeeSttusByFeeTpList_D", searchVO);
    }

    /**
	 * 항만시설월별사용료현황 목록 자료수 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int - 자료수
	 * @exception Exception
	 */
    int selectPrtFcltyRentFeeSttusByFeeTpListTotCnt(GamPrtFcltyRentFeeSttusByFeeTpVO searchVO) throws Exception{
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentFeeSttusByFeeTpDao.selectPrtFcltyRentFeeSttusByFeeTpListTotCnt_S", searchVO);
    }

    /**
	 * 항만시설월별사용료현황 자료수, 합계 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return resultVO - 합계정보가 담긴 VO
	 * @exception Exception
	 */
    int selectPrtFcltyRentFeeSttusByFeeTpListSum(GamPrtFcltyRentFeeSttusByFeeTpVO searchVO) throws Exception {
    	return (Integer)getSqlMapClientTemplate().queryForObject("gamPrtFcltyRentFeeSttusByFeeTpDao.selectPrtFcltyRentFeeSttusByFeeTpListSum_S", searchVO);
    }

}
