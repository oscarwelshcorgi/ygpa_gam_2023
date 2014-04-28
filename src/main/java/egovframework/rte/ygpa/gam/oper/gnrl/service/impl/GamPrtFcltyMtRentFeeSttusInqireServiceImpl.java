package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyMtRentFeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyMtRentFeeSttusInqireVO;

/**
 * @Class Name : GamPrtFcltyMtRentFeeSttusInqireServiceImpl.java
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
@Service("gamPrtFcltyMtRentFeeSttusInqireService")

public class GamPrtFcltyMtRentFeeSttusInqireServiceImpl  extends AbstractServiceImpl implements GamPrtFcltyMtRentFeeSttusInqireService {

	@Resource(name="gamPrtFcltyMtRentFeeSttusInqireDao")
    private GamPrtFcltyMtRentFeeSttusInqireDao gamPrtFcltyMtRentFeeSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설월별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyMtRentFeeSttusInqireList(GamPrtFcltyMtRentFeeSttusInqireVO searchVO) throws Exception {
        return gamPrtFcltyMtRentFeeSttusInqireDao.selectPrtFcltyMtRentFeeSttusInqireList(searchVO);
    }

    /**
	 * 항만시설월별사용료현황 목록 자료수 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return int - 자료수
	 * @exception Exception
	 */
    public int selectPrtFcltyMtRentFeeSttusInqireListTotCnt(GamPrtFcltyMtRentFeeSttusInqireVO searchVO) throws Exception{
    	return gamPrtFcltyMtRentFeeSttusInqireDao.selectPrtFcltyMtRentFeeSttusInqireListTotCnt(searchVO);
    }

    /**
	 * 항만시설월별사용료현황 자료수, 합계 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return resultVO - 합계정보가 담긴 VO
	 * @exception Exception
	 */
    public GamPrtFcltyMtRentFeeSttusInqireVO selectPrtFcltyMtRentFeeSttusInqireSum(GamPrtFcltyMtRentFeeSttusInqireVO searchVO) throws Exception {
    	return gamPrtFcltyMtRentFeeSttusInqireDao.selectPrtFcltyMtRentFeeSttusInqireSum(searchVO);
    }

}
