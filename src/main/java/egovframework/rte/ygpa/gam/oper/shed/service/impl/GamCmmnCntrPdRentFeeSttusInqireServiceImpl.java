package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrPdRentFeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrPdRentFeeSttusInqireVO;

/**
 * @Class Name : GamCmmnCntrPdRentFeeSttusInqireServiceImpl.java
 * @Description : 공컨장치장임대기간별사용료현황조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCmmnCntrPdRentFeeSttusInqireService")

public class GamCmmnCntrPdRentFeeSttusInqireServiceImpl  extends AbstractServiceImpl implements GamCmmnCntrPdRentFeeSttusInqireService {

	@Resource(name="gamCmmnCntrPdRentFeeSttusInqireDao")
    private GamCmmnCntrPdRentFeeSttusInqireDao gamCmmnCntrPdRentFeeSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 공컨장치장임대기간별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrPdRentFeeSttusInqireList(GamCmmnCntrPdRentFeeSttusInqireVO searchVO) throws Exception {
        return gamCmmnCntrPdRentFeeSttusInqireDao.selectCmmnCntrPdRentFeeSttusInqireList(searchVO);
    }
    
}
