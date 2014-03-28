package egovframework.rte.ygpa.gam.oper.shed.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrEntrpsRentFeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.shed.service.GamCmmnCntrEntrpsRentFeeSttusInqireVO;

/**
 * @Class Name : GamCmmnCntrEntrpsRentFeeSttusInqireServiceImpl.java
 * @Description : 공컨장치장임대업체별사용료현황조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamCmmnCntrEntrpsRentFeeSttusInqireService")

public class GamCmmnCntrEntrpsRentFeeSttusInqireServiceImpl  extends AbstractServiceImpl implements GamCmmnCntrEntrpsRentFeeSttusInqireService {

	@Resource(name="gamCmmnCntrEntrpsRentFeeSttusInqireDao")
    private GamCmmnCntrEntrpsRentFeeSttusInqireDao gamCmmnCntrEntrpsRentFeeSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 공컨장치장임대업체별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectCmmnCntrEntrpsRentFeeSttusInqireList(GamCmmnCntrEntrpsRentFeeSttusInqireVO searchVO) throws Exception {
        return gamCmmnCntrEntrpsRentFeeSttusInqireDao.selectCmmnCntrEntrpsRentFeeSttusInqireList(searchVO);
    }
    
}
