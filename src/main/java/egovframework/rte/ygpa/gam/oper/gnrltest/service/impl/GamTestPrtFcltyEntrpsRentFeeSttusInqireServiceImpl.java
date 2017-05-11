package egovframework.rte.ygpa.gam.oper.gnrltest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyEntrpsRentFeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.gnrltest.service.GamTestPrtFcltyEntrpsRentFeeSttusInqireVO;

/**
 * @Class Name : GamTestPrtFcltyEntrpsRentFeeSttusInqireServiceImpl.java
 * @Description : 항만시설업체별사용료현황조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamTestPrtFcltyEntrpsRentFeeSttusInqireService")

public class GamTestPrtFcltyEntrpsRentFeeSttusInqireServiceImpl  extends AbstractServiceImpl implements GamTestPrtFcltyEntrpsRentFeeSttusInqireService {

	@Resource(name="gamTestPrtFcltyEntrpsRentFeeSttusInqireDao")
    private GamTestPrtFcltyEntrpsRentFeeSttusInqireDao gamTestPrtFcltyEntrpsRentFeeSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설업체별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyEntrpsRentFeeSttusInqireList(GamTestPrtFcltyEntrpsRentFeeSttusInqireVO searchVO) throws Exception {
        return gamTestPrtFcltyEntrpsRentFeeSttusInqireDao.selectPrtFcltyEntrpsRentFeeSttusInqireList(searchVO);
    }
    
    
    /**
	 * 항만시설업체별사용료현황 전체목록수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return int
	 * @exception Exception
	 */
    public int selectPrtFcltyEntrpsRentFeeSttusInqireListTotCnt(GamTestPrtFcltyEntrpsRentFeeSttusInqireVO searchVO) throws Exception {
    	return gamTestPrtFcltyEntrpsRentFeeSttusInqireDao.selectPrtFcltyEntrpsRentFeeSttusInqireListTotCnt(searchVO);
    }
    
    
    /**
	 * 전체사용료합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
    public GamTestPrtFcltyEntrpsRentFeeSttusInqireVO selectPrtFcltyEntrpsRentFeeSttusInqireSum(GamTestPrtFcltyEntrpsRentFeeSttusInqireVO searchVO) throws Exception {
    	return gamTestPrtFcltyEntrpsRentFeeSttusInqireDao.selectPrtFcltyEntrpsRentFeeSttusInqireSum(searchVO);
    }
    
}
