package egovframework.rte.ygpa.gam.oper.htld.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldEntrpsRentFeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.htld.service.GamHtldEntrpsRentFeeSttusInqireVO;

/**
 * @Class Name : GamHtldEntrpsRentFeeSttusInqireServiceImpl.java
 * @Description : 배후단지임대업체별사용료현황조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamHtldEntrpsRentFeeSttusInqireService")

public class GamHtldEntrpsRentFeeSttusInqireImpl  extends AbstractServiceImpl implements GamHtldEntrpsRentFeeSttusInqireService {

	@Resource(name="gamHtldEntrpsRentFeeSttusInqireDao")
    private GamHtldEntrpsRentFeeSttusInqireDao gamHtldEntrpsRentFeeSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 배후단지임대업체별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectHtldEntrpsRentFeeSttusInqireList(GamHtldEntrpsRentFeeSttusInqireVO searchVO) throws Exception {
        return gamHtldEntrpsRentFeeSttusInqireDao.selectHtldEntrpsRentFeeSttusInqireList(searchVO);
    }
    
}
