package egovframework.rte.ygpa.gam.oper.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentCmpyStsReportService;
import egovframework.rte.ygpa.gam.oper.center.service.GamMarineCenterRentCmpyStsReportVO;

/**
 * @Class Name : GamMarineCenterRentCmpyStsReportServiceImpl.java
 * @Description : 마린센터업체별사용료현황조회 Business Implement class
 * @Modification Information
 *
 * @author heroine
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamMarineCenterRentCmpyStsReportService")

public class GamMarineCenterRentCmpyStsReportServiceImpl  extends AbstractServiceImpl implements GamMarineCenterRentCmpyStsReportService {

	@Resource(name="gamMarineCenterRentCmpyStsReportDao")
    private GamMarineCenterRentCmpyStsReportDao gamMarineCenterRentCmpyStsReportDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 마린센터업체별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectMarineCenterRentCmpyStsReportList(GamMarineCenterRentCmpyStsReportVO searchVO) throws Exception {
        return gamMarineCenterRentCmpyStsReportDao.selectMarineCenterRentCmpyStsReportList(searchVO);
    }
    
    /**
   	 * 마린센터업체별사용료현황 목록 총 갯수를 조회한다.
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return cnt
   	 * @exception
   	 */
       public int selectMarineCenterRentCmpyStsReportListTotCnt(GamMarineCenterRentCmpyStsReportVO searchVO) throws Exception {
   		//return gamAssetRentMngtDao.selectAssetRentListTotCnt(searchVO);
    	   return gamMarineCenterRentCmpyStsReportDao.selectMarineCenterRentCmpyStsReportListTotCnt(searchVO);
   	}
       
       
       /**
   	 * 전체사용료합계
   	 * @param searchMap - 조회할 정보가 담긴 Map
   	 * @return vo
   	 * @exception Exception
   	 */
       public GamMarineCenterRentCmpyStsReportVO selectMarineCenterRentCmpyStsReportSum(GamMarineCenterRentCmpyStsReportVO searchVO) throws Exception {
       	return gamMarineCenterRentCmpyStsReportDao.selectMarineCenterRentCmpyStsReportSum(searchVO);
       }
    
}
