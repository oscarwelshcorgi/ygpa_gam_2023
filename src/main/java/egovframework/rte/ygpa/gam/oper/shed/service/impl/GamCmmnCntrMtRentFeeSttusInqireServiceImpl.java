package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrMtRentFeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrMtRentFeeSttusInqireVO;

/**
 * @Class Name : GamCmmnCntrMtRentFeeSttusInqireServiceImpl.java
 * @Description : 공컨장치장임대월별사용료현황조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCmmnCntrMtRentFeeSttusInqireService")

public class GamCmmnCntrMtRentFeeSttusInqireServiceImpl  extends AbstractServiceImpl implements GamCmmnCntrMtRentFeeSttusInqireService {

	@Resource(name="gamCmmnCntrMtRentFeeSttusInqireDao")
    private GamCmmnCntrMtRentFeeSttusInqireDao gamCmmnCntrMtRentFeeSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 공컨장치장임대월별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrMtRentFeeSttusInqireList(GamCmmnCntrMtRentFeeSttusInqireVO searchVO) throws Exception {
        return gamCmmnCntrMtRentFeeSttusInqireDao.selectCmmnCntrMtRentFeeSttusInqireList(searchVO);
    }

	public int selectCmmnCntrMtRentFeeSttusInqireListTotCnt(GamCmmnCntrMtRentFeeSttusInqireVO searchVO) throws Exception {
		return gamCmmnCntrMtRentFeeSttusInqireDao.selectCmmnCntrMtRentFeeSttusInqireListTotCnt(searchVO);
	}

	public GamCmmnCntrMtRentFeeSttusInqireVO selectCmmnCntrMtRentFeeSttusInqireSum(GamCmmnCntrMtRentFeeSttusInqireVO searchVO) throws Exception {
		return gamCmmnCntrMtRentFeeSttusInqireDao.selectCmmnCntrMtRentFeeSttusInqireSum(searchVO);
	}
    
}
