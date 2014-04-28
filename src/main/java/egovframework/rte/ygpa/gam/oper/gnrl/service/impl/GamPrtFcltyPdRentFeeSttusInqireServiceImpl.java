package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyPdRentFeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyPdRentFeeSttusInqireVO;

/**
 * @Class Name : GamPrtFcltyPdRentFeeSttusInqireServiceImpl.java
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
@Service("gamPrtFcltyPdRentFeeSttusInqireService")

public class GamPrtFcltyPdRentFeeSttusInqireServiceImpl  extends AbstractServiceImpl implements GamPrtFcltyPdRentFeeSttusInqireService {

	@Resource(name="gamPrtFcltyPdRentFeeSttusInqireDao")
    private GamPrtFcltyPdRentFeeSttusInqireDao gamPrtFcltyPdRentFeeSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설기간별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyPdRentFeeSttusInqireList(GamPrtFcltyPdRentFeeSttusInqireVO searchVO) throws Exception {
        return gamPrtFcltyPdRentFeeSttusInqireDao.selectPrtFcltyPdRentFeeSttusInqireList(searchVO);
    }
    
    
    /**
	 * 항만시설기간별사용료현황 전체목록수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return int
	 * @exception Exception
	 */
    public int selectPrtFcltyPdRentFeeSttusInqireListTotCnt(GamPrtFcltyPdRentFeeSttusInqireVO searchVO) throws Exception {
    	return gamPrtFcltyPdRentFeeSttusInqireDao.selectPrtFcltyPdRentFeeSttusInqireListTotCnt(searchVO);
    }
    
    
    /**
	 * 사용료합계, 감면사용료 합계
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return vo
	 * @exception Exception
	 */
    public GamPrtFcltyPdRentFeeSttusInqireVO selectPrtFcltyPdRentFeeSttusInqireSum(GamPrtFcltyPdRentFeeSttusInqireVO searchVO) throws Exception {
    	return gamPrtFcltyPdRentFeeSttusInqireDao.selectPrtFcltyPdRentFeeSttusInqireSum(searchVO);
    }
    
}
