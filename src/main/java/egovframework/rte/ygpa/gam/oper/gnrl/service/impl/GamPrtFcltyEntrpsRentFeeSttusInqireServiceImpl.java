package egovframework.rte.ygpa.gam.oper.gnrl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyEntrpsRentFeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.gnrl.service.GamPrtFcltyEntrpsRentFeeSttusInqireVO;

/**
 * @Class Name : GamPrtFcltyEntrpsRentFeeSttusInqireServiceImpl.java
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
@Service("gamPrtFcltyEntrpsRentFeeSttusInqireService")

public class GamPrtFcltyEntrpsRentFeeSttusInqireServiceImpl  extends AbstractServiceImpl implements GamPrtFcltyEntrpsRentFeeSttusInqireService {

	@Resource(name="gamPrtFcltyEntrpsRentFeeSttusInqireDao")
    private GamPrtFcltyEntrpsRentFeeSttusInqireDao gamPrtFcltyEntrpsRentFeeSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 항만시설업체별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectPrtFcltyEntrpsRentFeeSttusInqireList(GamPrtFcltyEntrpsRentFeeSttusInqireVO searchVO) throws Exception {
        return gamPrtFcltyEntrpsRentFeeSttusInqireDao.selectPrtFcltyEntrpsRentFeeSttusInqireList(searchVO);
    }
    
}
