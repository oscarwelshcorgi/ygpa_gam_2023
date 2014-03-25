package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMtRentFeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldMtRentFeeSttusInqireVO;

/**
 * @Class Name : GamHtldMtRentFeeSttusInqireServiceImpl.java
 * @Description : 배후단지임대월별사용료현황조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamHtldMtRentFeeSttusInqireService")

public class GamHtldMtRentFeeSttusInqireServiceImpl  extends AbstractServiceImpl implements GamHtldMtRentFeeSttusInqireService {

	@Resource(name="gamHtldMtRentFeeSttusInqireDao")
    private GamHtldMtRentFeeSttusInqireDao gamHtldMtRentFeeSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 배후단지임대월별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldMtRentFeeSttusInqireList(GamHtldMtRentFeeSttusInqireVO searchVO) throws Exception {
        return gamHtldMtRentFeeSttusInqireDao.selectHtldMtRentFeeSttusInqireList(searchVO);
    }
    
}
