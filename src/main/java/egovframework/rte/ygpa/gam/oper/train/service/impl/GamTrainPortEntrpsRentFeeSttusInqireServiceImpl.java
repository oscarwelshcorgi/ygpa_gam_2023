package egovframework.rte.ygpa.gam.oper.train.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortEntrpsRentFeeSttusInqireService;
import egovframework.rte.ygpa.gam.oper.train.service.GamTrainPortEntrpsRentFeeSttusInqireVO;

/**
 * @Class Name : GamTrainPortEntrpsRentFeeSttusInqireServiceImpl.java
 * @Description : 철송장임대업체별사용료현황조회 Business Implement class
 * @Modification Information
 *
 * @author domh
 * @since 2014-01-14
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Service("gamTrainPortEntrpsRentFeeSttusInqireService")

public class GamTrainPortEntrpsRentFeeSttusInqireServiceImpl  extends AbstractServiceImpl implements GamTrainPortEntrpsRentFeeSttusInqireService {

	@Resource(name="gamTrainPortEntrpsRentFeeSttusInqireDao")
    private GamTrainPortEntrpsRentFeeSttusInqireDao gamTrainPortEntrpsRentFeeSttusInqireDao;

	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 철송장임대업체별사용료현황 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return list
	 * @exception Exception
	 */
    public List selectTrainPortEntrpsRentFeeSttusInqireList(GamTrainPortEntrpsRentFeeSttusInqireVO searchVO) throws Exception {
        return gamTrainPortEntrpsRentFeeSttusInqireDao.selectTrainPortEntrpsRentFeeSttusInqireList(searchVO);
    }
    
}
