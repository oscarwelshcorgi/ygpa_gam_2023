package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentStairStsReportService;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentStairStsReportVO;

/**
 * @Class Name : GamMarineCenterRentStairStsReportServiceImpl.java
 * @Description : 마린센터층별사용료현황조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamMarineCenterRentStairStsReportService")

public class GamMarineCenterRentStairStsReportServiceImpl  extends AbstractServiceImpl implements GamMarineCenterRentStairStsReportService {

	@Resource(name="gamMarineCenterRentStairStsReportDao")
    private GamMarineCenterRentStairStsReportDao gamMarineCenterRentStairStsReportDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 마린센터층별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMarineCenterRentStairStsReportList(GamMarineCenterRentStairStsReportVO searchVO) throws Exception {
        return gamMarineCenterRentStairStsReportDao.selectMarineCenterRentStairStsReportList(searchVO);
    }
    
    /**
   	 * 마린센터층별사용료현황 목록 총 갯수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
       public int selectMarineCenterRentStairStsReportListTotCnt(GamMarineCenterRentStairStsReportVO searchVO) throws Exception {
   		//return gamAssetRentMngtDao.selectAssetRentListTotCnt(searchVO);
    	   return gamMarineCenterRentStairStsReportDao.selectMarineCenterRentStairStsReportListTotCnt(searchVO);
   	}

    
       /**
      	 * 마린센터층별사용료현황 자료수, 사용료, 감면사용료 합계 
      	 * @param searchVO - 조회할 정보가 담긴 VO
      	 * @return 총 합계 정보
      	 * @exception
      	 */
	public GamMarineCenterRentStairStsReportVO selectMarineCenterRentStairStsReportSum(GamMarineCenterRentStairStsReportVO searchVO) throws Exception {
		return gamMarineCenterRentStairStsReportDao.selectMarineCenterRentStairStsReportSum(searchVO);
	}
    
}
