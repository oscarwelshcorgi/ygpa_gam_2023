package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterMonthStsReportService;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterMonthStsReportVO;

/**
 * @Class Name : GamMarineCenterMonthStsReportServiceImpl.java
 * @Description : 마린센터월별사용료현황조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamMarineCenterMonthStsReportService")

public class GamMarineCenterMonthStsReportServiceImpl  extends AbstractServiceImpl implements GamMarineCenterMonthStsReportService {

	@Resource(name="gamMarineCenterMonthStsReportDao")
    private GamMarineCenterMonthStsReportDao gamMarineCenterMonthStsReportDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 마린센터월별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMarineCenterMonthStsReportList(GamMarineCenterMonthStsReportVO searchVO) throws Exception {
        return gamMarineCenterMonthStsReportDao.selectMarineCenterMonthStsReportList(searchVO);
    }
    
    /**
   	 * 마린센터월별사용료현황 목록 총 갯수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
       public int selectMarineCenterMonthStsReportListTotCnt(GamMarineCenterMonthStsReportVO searchVO) throws Exception {
   		//return gamAssetRentMngtDao.selectAssetRentListTotCnt(searchVO);
    	   return gamMarineCenterMonthStsReportDao.selectMarineCenterMonthStsReportListTotCnt(searchVO);
   	}
    
}
