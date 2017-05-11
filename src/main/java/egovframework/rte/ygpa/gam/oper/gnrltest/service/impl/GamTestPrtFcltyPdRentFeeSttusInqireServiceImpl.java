package egovframework.rte.ygpa.gam.oper.gnrltest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyPdRentFeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyPdRentFeeSttusInqireVO;

/**
 * @Class Name : GamTestPrtFcltyPdRentFeeSttusInqireServiceImpl.java
 * @Description : 항만시설기간별사용료현황조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamTestPrtFcltyPdRentFeeSttusInqireService")

public class GamTestPrtFcltyPdRentFeeSttusInqireServiceImpl  extends AbstractServiceImpl implements GamTestPrtFcltyPdRentFeeSttusInqireService {

	@Resource(name="gamTestPrtFcltyPdRentFeeSttusInqireDao")
    private GamTestPrtFcltyPdRentFeeSttusInqireDao gamTestPrtFcltyPdRentFeeSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설기간별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyPdRentFeeSttusInqireList(GamTestPrtFcltyPdRentFeeSttusInqireVO searchVO) throws Exception {
        return gamTestPrtFcltyPdRentFeeSttusInqireDao.selectPrtFcltyPdRentFeeSttusInqireList(searchVO);
    }
    
    
    /**
	 * 항만시설기간별사용료현황 전체목록수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return int
	 * @exception Exception
	 */
    public int selectPrtFcltyPdRentFeeSttusInqireListTotCnt(GamTestPrtFcltyPdRentFeeSttusInqireVO searchVO) throws Exception {
    	return gamTestPrtFcltyPdRentFeeSttusInqireDao.selectPrtFcltyPdRentFeeSttusInqireListTotCnt(searchVO);
    }
    
    
    /**
	 * 사용료합계, 감면사용료 합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
    public GamTestPrtFcltyPdRentFeeSttusInqireVO selectPrtFcltyPdRentFeeSttusInqireSum(GamTestPrtFcltyPdRentFeeSttusInqireVO searchVO) throws Exception {
    	return gamTestPrtFcltyPdRentFeeSttusInqireDao.selectPrtFcltyPdRentFeeSttusInqireSum(searchVO);
    }
    
}
