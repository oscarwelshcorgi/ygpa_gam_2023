package egovframework.rte.ygpa.gam.oper.gnrltest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentFeeSttusByFeeTpService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyRentFeeSttusByFeeTpVO;

/**
 * @Class Name : GamTestPrtFcltyMtRentFeeSttusInqireServiceImpl.java
 * @Description : 항만시설월별사용료현황조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamTestPrtFcltyRentFeeSttusByFeeTpService")
public class GamTestPrtFcltyRentFeeSttusByFeeTpServiceImpl  extends AbstractServiceImpl implements GamTestPrtFcltyRentFeeSttusByFeeTpService {

	@Resource(name="gamTestPrtFcltyRentFeeSttusByFeeTpDao")
    private GamTestPrtFcltyRentFeeSttusByFeeTpDao gamTestPrtFcltyRentFeeSttusByFeeTpDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyRentFeeSttusByFeeTpInqireService#selectPrtFcltyRentFeeSttusByFeeTpInqireList(java.util.Map)
	 */
	@Override
	public List selectPrtFcltyRentFeeSttusByFeeTpList(GamTestPrtFcltyRentFeeSttusByFeeTpVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return gamTestPrtFcltyRentFeeSttusByFeeTpDao.selectPrtFcltyRentFeeSttusByFeeTpList(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyRentFeeSttusByFeeTpInqireService#selectPrtFcltyRentFeeSttusByFeeTpInqireListTotCnt(java.util.Map)
	 */
	@Override
	public int selectPrtFcltyRentFeeSttusByFeeTpListTotCnt(GamTestPrtFcltyRentFeeSttusByFeeTpVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return gamTestPrtFcltyRentFeeSttusByFeeTpDao.selectPrtFcltyRentFeeSttusByFeeTpListTotCnt(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyRentFeeSttusByFeeTpInqireService#selectPrtFcltyRentFeeSttusByFeeTpInqireSum(java.util.Map)
	 */
	@Override
	public int selectPrtFcltyRentFeeSttusByFeeTpSum(GamTestPrtFcltyRentFeeSttusByFeeTpVO searchVO)
			throws Exception {
		// TODO Auto-generated method stub
		return gamTestPrtFcltyRentFeeSttusByFeeTpDao.selectPrtFcltyRentFeeSttusByFeeTpListSum(searchVO);
	}

	/* (non-Javadoc)
	 * @see egovframework.rte.ygpa.gam.oper.gnrl.service.GamTestPrtFcltyRentFeeSttusByFeeTpService#getYears()
	 */
	@Override
	public List getYears() throws Exception {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int currentYear = cal.get(cal.YEAR);
		List result = new ArrayList();
   		for (int i = 2000; i <= currentYear; i++) {
   			result.add(String.valueOf(i));
   		}
   		return result;
	}

	/**
     * 조회기간 월을 가져온다
     *
     */
	@Override
	public List getMonths(){
		List result = new ArrayList();
   		for (int i=1; i<=12; i++) {
   			result.add(new Integer(i));
   		}
   		return result;
   	}

}
